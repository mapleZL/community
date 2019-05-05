package com.ejavashop.web.controller.member;

import java.util.HashMap;
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
import com.ejavashop.entity.member.Member;
import com.ejavashop.entity.product.Product;
import com.ejavashop.entity.product.ProductAsk;
import com.ejavashop.service.product.IProductAskService;
import com.ejavashop.service.product.IProductService;
import com.ejavashop.web.controller.BaseController;
import com.ejavashop.web.util.WebFrontSession;

/**
 * 咨询管理
 * 
 * @Filename: MemberAskController.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Controller
@RequestMapping(value = "member")
public class MemberAskController extends BaseController {

    @Resource
    private IProductAskService productAskService;

    @Resource
    private IProductService    productService;

    /**
     * 跳转到 我的咨询界面
     * @param request
     * @param response
     * @param map
     * @return
     */
    @RequestMapping(value = "/question.html", method = { RequestMethod.GET })
    public String question(HttpServletRequest request, HttpServletResponse response,
                           Map<String, Object> dataMap) {

        Map<String, String> queryMap = new HashMap<String, String>();
        PagerInfo pager = new PagerInfo(ConstantsEJS.DEFAULT_PAGE_SIZE_10, 1);
        Member member = WebFrontSession.getLoginedUser(request);
        queryMap.put("q_userId", member.getId() + "");
        ServiceResult<List<ProductAsk>> serviceResult = productAskService
            .getProductAsksWithInfo(queryMap, pager);

        dataMap.put("askList", serviceResult.getResult());
        dataMap.put("askCount", pager.getRowsCount());
        dataMap.put("pageIndex", pager.getPageIndex());
        dataMap.put("pageSize", pager.getPageSize());

        return "h5v1/member/ask/ask";
    }

    /**
     * ajax异步加载更多咨询
     * @param request
     * @param response
     * @param map
     * @return
     */
    @RequestMapping(value = "/morequestion.html", method = { RequestMethod.GET })
    public String moreQuestion(HttpServletRequest request, HttpServletResponse response,
                               Map<String, Object> dataMap, Integer pageIndex) {

        Map<String, String> queryMap = new HashMap<String, String>();
        PagerInfo pager = new PagerInfo(ConstantsEJS.DEFAULT_PAGE_SIZE_10, pageIndex);
        Member member = WebFrontSession.getLoginedUser(request);
        queryMap.put("q_userId", member.getId() + "");
        ServiceResult<List<ProductAsk>> serviceResult = productAskService
            .getProductAsksWithInfo(queryMap, pager);

        dataMap.put("askList", serviceResult.getResult());

        return "h5v1/member/ask/asklist";
    }

    /**
     * 商品咨询提交
     * @param request
     * @param response
     * @param dataMap
     * @param productAsk
     * @return
     */
    @RequestMapping(value = "/savequestion.html", method = { RequestMethod.POST })
    public @ResponseBody HttpJsonResult<ProductAsk> saveQuestion(HttpServletRequest request,
                                                                 HttpServletResponse response,
                                                                 Map<String, Object> dataMap,
                                                                 Integer productId,
                                                                 String askContent) {
        Member member = WebFrontSession.getLoginedUser(request);

        ServiceResult<Product> productResult = productService.getProductById(productId);
        if (!productResult.getSuccess()) {
            return new HttpJsonResult<ProductAsk>(productResult.getMessage());
        }

        Product product = productResult.getResult();
        if (product == null) {
            return new HttpJsonResult<ProductAsk>("商品信息获取失败");
        }

        ProductAsk productAsk = new ProductAsk();
        productAsk.setSellerId(product.getSellerId());
        productAsk.setProductId(productId);
        productAsk.setUserId(member.getId());
        productAsk.setUserName(member.getName());
        productAsk.setAskContent(askContent);
        productAsk.setState(ProductAsk.STATE_1);
        ServiceResult<ProductAsk> serviceResult = productAskService.saveProductAsk(productAsk,
            member);
        HttpJsonResult<ProductAsk> jsonResult = new HttpJsonResult<ProductAsk>();

        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                jsonResult = new HttpJsonResult<ProductAsk>(serviceResult.getMessage());
            }
        }
        return jsonResult;
    }

}
