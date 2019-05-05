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
import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.analysis.ProductLookLog;
import com.ejavashop.entity.member.Member;
import com.ejavashop.service.analysis.IAnalysisService;
import com.ejavashop.web.controller.BaseController;
import com.ejavashop.web.util.WebFrontSession;

/**
 * 我的浏览记录
 * 
 * @Filename: MemberViewController.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Controller
@RequestMapping(value = "member")
public class MemberViewController extends BaseController {

    @Resource
    private IAnalysisService analysisService;

    /**
     * 跳转到 商品浏览界面
     * @param request
     * @param response
     * @param map
     * @return
     */
    @RequestMapping(value = "/viewed.html", method = { RequestMethod.GET })
    public String viewed(HttpServletRequest request, HttpServletResponse response,
                         Map<String, Object> dataMap) {

        Member member = WebFrontSession.getLoginedUser(request);

        Map<String, String> queryMap = new HashMap<String, String>();
        queryMap.put("q_memberId", member.getId() + "");
//        PagerInfo pager = new PagerInfo(ConstantsEJS.DEFAULT_PAGE_SIZE_10, 1);

//        ServiceResult<List<ProductLookLog>> serviceResult = analysisService.getProductLookLogs(queryMap, pager);

//        dataMap.put("lookLogList", serviceResult.getResult());
        ServiceResult<Integer> countPage = analysisService.countProductLookLog(queryMap);
        dataMap.put("logCount", countPage.getResult());
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE_10);

        return "h5v1/member/person/productlooklog";
    }

    /**
     * ajax异步加载更多
     * @param request
     * @param response
     * @param map
     * @return
     */
    @RequestMapping(value = "/moreviewed.html", method = { RequestMethod.GET })
    public String moreViewed(HttpServletRequest request, HttpServletResponse response,
                             Map<String, Object> dataMap, Integer pageIndex) {

        Member member = WebFrontSession.getLoginedUser(request);

        Map<String, String> queryMap = new HashMap<String, String>();
        queryMap.put("q_memberId", member.getId() + "");
        PagerInfo pager = new PagerInfo(ConstantsEJS.DEFAULT_PAGE_SIZE_10, pageIndex);

        ServiceResult<List<ProductLookLog>> serviceResult = analysisService
            .getProductLookLogs(queryMap, pager);

        dataMap.put("lookLogList", serviceResult.getResult());

        return "h5v1/member/person/productlookloglist";
    }
    
    /**
     * 删除浏览记录
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "/deleteproductlog.html", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<Boolean> deleteproductlog(HttpServletRequest request,
                                                             HttpServletResponse response,
                                                             Map<String, Object> dataMap,
                                                             @RequestParam(value = "productId", required = true) String productId) {
        Member member = WebFrontSession.getLoginedUser(request);
        ProductLookLog productLookLog = new ProductLookLog();
        productLookLog.setState(2);
        productLookLog.setMemberId(member.getId());
        productLookLog.setProductId(Integer.valueOf(productId));
        //取消订单
        ServiceResult<Boolean> serviceResult = analysisService.updateProductLookLog(productLookLog);

        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                jsonResult = new HttpJsonResult<Boolean>(serviceResult.getMessage());
            }
        }
        return jsonResult;

    }
}
