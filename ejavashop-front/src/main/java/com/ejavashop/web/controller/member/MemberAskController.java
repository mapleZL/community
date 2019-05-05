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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.HttpJsonResult;
import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.PaginationUtil;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.StringUtil;
import com.ejavashop.core.WebUtil;
import com.ejavashop.entity.member.Member;
import com.ejavashop.entity.product.ProductAsk;
import com.ejavashop.service.analysis.IAnalysisService;
import com.ejavashop.service.member.IMemberCollectionProductService;
import com.ejavashop.service.member.IMemberCollectionSellerService;
import com.ejavashop.service.product.IProductAskService;
import com.ejavashop.web.controller.BaseController;
import com.ejavashop.web.util.WebFrontSession;

/**
 * 用户中心：我的咨询
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
    private IMemberCollectionSellerService  memberCollectionSellerService;

    @Resource
    private IMemberCollectionProductService memberCollectionProductService;

    @Resource
    private IProductAskService              productAskService;

    @Resource
    private IAnalysisService                analysisService;

    /**
     * 跳转到 我的咨询界面
     * @param request
     * @param response
     * @param map
     * @return
     */
    @RequestMapping(value = "/question.html", method = { RequestMethod.GET })
    public String toMyConsultation(HttpServletRequest request, HttpServletResponse response,
                                   Map<String, Object> dataMap) {

        Map<String, String> queryMap = new HashMap<String, String>();
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        Member member = WebFrontSession.getLoginedUser(request);
        queryMap.put("q_userId", member.getId() + "");
        ServiceResult<List<ProductAsk>> serviceResult = productAskService
            .getProductAsksWithInfo(queryMap, pager);

        //分页对象
        PaginationUtil pm = new PaginationUtil(pager.getPageSize(),
            String.valueOf(pager.getPageIndex()), pager.getRowsCount(),
            request.getRequestURI() + "");

        dataMap.put("askList", serviceResult.getResult());
        dataMap.put("page", pm);

        return "front/member/usercenter/ordercenter/myconsultation";
    }

    /**
     * 添加或查看商品咨询界面
     * @param request
     * @param response
     * @param dataMap
     * @param productId 产品id
     * @return
     */
    @RequestMapping(value = "/addquestion.html", method = { RequestMethod.GET })
    public String getProductAskByProductId(HttpServletRequest request, HttpServletResponse response,
                                           Map<String, Object> dataMap,
                                           @RequestParam(value = "sellerId", required = true) String sellerId,
                                           @RequestParam(value = "productId", required = true) String productId) {
        if (StringUtil.isEmpty(productId)) {
            throw new RuntimeException("产品id为空");
        }
        if (StringUtil.isEmpty(sellerId)) {
            throw new RuntimeException("商家id为空");
        }
        Member member = WebFrontSession.getLoginedUser(request);
        ServiceResult<ProductAsk> serviceResult = productAskService
            .getProductAskByProductId(productId, member.getId());
        dataMap.put("askinfo", serviceResult.getResult());
        dataMap.put("productId", productId);
        dataMap.put("sellerId", sellerId);

        return "front/member/usercenter/ordercenter/editconsultation";
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
    public @ResponseBody HttpJsonResult<ProductAsk> askinfoSubmit(HttpServletRequest request,
                                                                  HttpServletResponse response,
                                                                  Map<String, Object> dataMap,
                                                                  ProductAsk productAsk) {
        Member member = WebFrontSession.getLoginedUser(request);
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
