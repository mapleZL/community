package com.phkj.web.controller.product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.phkj.core.HttpJsonResult;
import com.phkj.core.ServiceResult;
import com.phkj.core.StringUtil;
import com.phkj.core.freemarkerutil.DomainUrlUtil;
import com.phkj.core.redis.RedisComponent;
import com.phkj.echarts.component.RedisSychroKeyConfig;
import com.phkj.entity.product.StAppletProduct;
import com.phkj.entity.system.Code;
import com.phkj.entity.system.SystemAdmin;
import com.phkj.service.product.IStAppletProductService;
import com.phkj.web.controller.BaseController;
import com.phkj.web.util.WebAdminSession;

/**
 * 
 *                       
 * @Filename: ProductController.java
 * @Version: 1.0
 * @date: 2019年5月20日
 * @Author: 陆帅 * @Email: lu1632278229@sina.cn
 *
 */
@Controller
@RequestMapping(value = "admin/product")
public class ProductController extends BaseController {

    @Autowired
    private IStAppletProductService productService;
    @Autowired
    private RedisComponent          redisComponent;

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/add", method = { RequestMethod.GET })
    public String getList(Map<String, Object> dataMap) throws Exception {
        List<Code> codeList = new ArrayList<>();
        // 商品分类
        String jsonString = redisComponent.getRedisString(RedisSychroKeyConfig.CODE_VALUE_KEY);
        if (StringUtils.isNotBlank(jsonString)) {
            Map<String, List<Code>> codeMap = JSONObject.parseObject(jsonString, Map.class);
            codeList = codeMap.get("PRODUCT_CATEGORY");
        }
        dataMap.put("productCategory", codeList);
        return "admin/product/pdt/productadd";
    }

    /**
     * 保存商品
     *
     * @param product
     * @param request
     * @param dataMap
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/create", method = { RequestMethod.POST })
    @ResponseBody
    public HttpJsonResult<Object> create(StAppletProduct product, HttpServletRequest request,
                                         Map<String, Object> dataMap) throws IOException {
        HttpJsonResult<Object> jsonResult = new HttpJsonResult<>();
        SystemAdmin user = WebAdminSession.getAdminUser(request);
        if (null == user) {
            jsonResult.setMessage("登录超时，请重新登录");
            jsonResult.setBackUrl(DomainUrlUtil.getEJS_URL_RESOURCES() + "/admin/login.html");
            return jsonResult;
        }

        ServiceResult<Boolean> serviceResult = createOrUpdateProduct(product, request, user);
        if (serviceResult.getSuccess() && serviceResult.getResult() == true) {
        } else {
            jsonResult.setMessage(serviceResult.getMessage());
        }
        return jsonResult;
    }

    private ServiceResult<Boolean> createOrUpdateProduct(StAppletProduct product,
                                                         HttpServletRequest request,
                                                         SystemAdmin user) {
        ServiceResult<Boolean> result = new ServiceResult<>();
        if (product.getId() != null && product.getId() > 0) {
            product.setSellerId(user.getId());
            product.setCreateId(user.getId());
            product.setCreateTime(new Date());
            product.setIsSelf(StAppletProduct.IsSelfEnum.SELF.getValue());//自营
            product.setProductCateState(1);//分类正常
            product.setIsTop(1);//不推荐
        }
        // 审核通过后修改状态重置
        user.setStatus(2);
        String pics = request.getParameter("imageSrc");
        if (!StringUtil.isEmpty(pics)) {
            String[] split = pics.split(";");
            product.setMasterImg(split.toString());
        }
        result = productService.updateOrCreate(product);
        return result;
    }

}
