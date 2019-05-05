package com.ejavashop.web.controller.member;

import java.util.Date;
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
import com.ejavashop.entity.product.ProductAsk;
import com.ejavashop.entity.seller.SellerUser;
import com.ejavashop.service.product.IProductAskService;
import com.ejavashop.web.controller.BaseController;
import com.ejavashop.web.util.WebSellerSession;

/**
 * 产品咨询管理controller
 * 
 * @Filename: SellerProdcutAskController.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Controller
@RequestMapping(value = "seller/member/productask")
public class SellerProdcutAskController extends BaseController {

    @Resource
    private IProductAskService productAskService;

    /**
     * 产品咨询管理页面初始化controller接口
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "", method = { RequestMethod.GET })
    public String index(Map<String, Object> dataMap) throws Exception {
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return "seller/member/productasklist";
    }

    /**
     * 产品咨询管理页面查询按钮controller接口
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<ProductAsk>> list(HttpServletRequest request,
                                                               HttpServletResponse response,
                                                               Map<String, Object> dataMap) {

        SellerUser sellerUser = WebSellerSession.getSellerUser(request);

        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        queryMap.put("q_sellerId", sellerUser.getSellerId() + "");
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        ServiceResult<List<ProductAsk>> serviceResult = productAskService
            .getProductAsksWithInfo(queryMap, pager);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }

        HttpJsonResult<List<ProductAsk>> jsonResult = new HttpJsonResult<List<ProductAsk>>();
        jsonResult.setRows(serviceResult.getResult());
        jsonResult.setTotal(pager.getRowsCount());
        return jsonResult;
    }

    @RequestMapping(value = "reply", method = { RequestMethod.GET })
    public String reply(Integer id, Map<String, Object> dataMap) {
        ServiceResult<ProductAsk> serviceResult = productAskService.getProductAskById(id);

        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                dataMap.put("message", serviceResult.getMessage());
                return "seller/member/productasklist";
            }
        }
        ProductAsk productAsk = serviceResult.getResult();

        dataMap.put("productAsk", productAsk);
        return "seller/member/productaskedit";
    }

    @RequestMapping(value = "doreply", method = { RequestMethod.POST })
    public String doReply(ProductAsk productAsk, HttpServletRequest request,
                          Map<String, Object> dataMap) {

        SellerUser sellerUser = WebSellerSession.getSellerUser(request);

        ProductAsk productAskNew = new ProductAsk();
        productAskNew.setId(productAsk.getId());
        productAskNew.setReplyContent(productAsk.getReplyContent());
        productAskNew.setReplyId(sellerUser.getId());
        productAskNew.setReplyName(sellerUser.getName());
        productAskNew.setReplyTime(new Date());

        ServiceResult<Boolean> serviceResult = productAskService.updateProductAsk(productAskNew);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                dataMap.put("productAsk", productAsk);
                dataMap.put("message", serviceResult.getMessage());
                return "seller/member/productasklist";
            }
        }
        return "redirect:";
    }
}
