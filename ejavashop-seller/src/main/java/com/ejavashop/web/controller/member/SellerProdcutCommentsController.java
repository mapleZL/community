package com.ejavashop.web.controller.member;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.HttpJsonResult;
import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.WebUtil;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.entity.product.ProductComments;
import com.ejavashop.entity.seller.SellerUser;
import com.ejavashop.service.product.IProductCommentsService;
import com.ejavashop.web.controller.BaseController;
import com.ejavashop.web.util.WebSellerSession;

/**
 * 产品评价管理controller
 * 
 * @Filename: SellerProdcutCommentsController.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Controller
@RequestMapping(value = "seller/member/productcomments")
public class SellerProdcutCommentsController extends BaseController {

    @Resource
    private IProductCommentsService productCommentsService;

    /**
     * 产品评价管理页面初始化controller接口
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "", method = { RequestMethod.GET })
    public String index(Map<String, Object> dataMap) throws Exception {
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return "seller/member/productcommentslist";
    }

    /**
     * 产品评价管理页面查询按钮controller接口
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<ProductComments>> list(HttpServletRequest request,
                                                                    HttpServletResponse response,
                                                                    Map<String, Object> dataMap) {

        SellerUser sellerUser = WebSellerSession.getSellerUser(request);
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        queryMap.put("q_sellerId", sellerUser.getSellerId() + "");
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        ServiceResult<List<ProductComments>> serviceResult = productCommentsService
            .getProductCommentsWithInfo(queryMap, pager);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }

        HttpJsonResult<List<ProductComments>> jsonResult = new HttpJsonResult<List<ProductComments>>();
        jsonResult.setRows(serviceResult.getResult());
        jsonResult.setTotal(pager.getRowsCount());
        return jsonResult;
    }

}
