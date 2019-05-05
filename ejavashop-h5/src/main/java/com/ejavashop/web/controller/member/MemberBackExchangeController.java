package com.ejavashop.web.controller.member;

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
import com.ejavashop.entity.member.Member;
import com.ejavashop.entity.member.MemberProductBack;
import com.ejavashop.entity.member.MemberProductExchange;
import com.ejavashop.entity.order.Orders;
import com.ejavashop.service.member.IMemberProductBackService;
import com.ejavashop.service.member.IMemberProductExchangeService;
import com.ejavashop.service.order.IOrdersService;
import com.ejavashop.web.controller.BaseController;
import com.ejavashop.web.util.WebFrontSession;

/**
 * 客户服务：退换货
 *                       
 * @Filename: MemberBackExchangeController.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Controller
@RequestMapping(value = "member")
public class MemberBackExchangeController extends BaseController {

    @Resource
    private IOrdersService                ordersService;

    @Resource
    private IMemberProductBackService     memberProductBackService;

    @Resource
    private IMemberProductExchangeService memberProductExchangeService;

    /**
     * 跳转到 退货申请页面 显示该订单的所有商品 然后可以对商品发起退货申请
     * @param request
     * @param response
     * @param dataMap
     * @param id
     * @return
     */
    @RequestMapping(value = "/backapply.html", method = { RequestMethod.GET })
    public String toProductBackApply(HttpServletRequest request, HttpServletResponse response,
                                     Map<String, Object> dataMap,
                                     @RequestParam(value = "id", required = true) Integer id) {
        //查询订单信息
        ServiceResult<Orders> serviceResult = ordersService.getOrderWithOPById(id,"front");

        dataMap.put("order", serviceResult.getResult());

        return "h5v1/member/service/backexchange";
    }

    /**
     * 判断 是否可以发起退换货申请  
     * @param request
     * @param response
     * @param dataMap
     * @param orderProductId
     * @param orderId
     * @return
     */
    @RequestMapping(value = "/canbackorexchange.html", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<Boolean> canApplyProductBackOrExchange(HttpServletRequest request,
                                                                               HttpServletResponse response,
                                                                               Map<String, Object> dataMap,
                                                                               @RequestParam(value = "orderProductId", required = true) Integer orderProductId,
                                                                               @RequestParam(value = "orderId", required = true) Integer orderId) {

        Member member = WebFrontSession.getLoginedUser(request);
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        serviceResult = memberProductBackService.canApplyProductBackOrExchange(orderId,
            orderProductId, member.getId());
        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();

        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                jsonResult = new HttpJsonResult<Boolean>(serviceResult.getMessage());
            }
        } else {
            boolean flag = serviceResult.getResult();
            if (!flag) {
                jsonResult.setMessage(serviceResult.getMessage());
            }
        }
        return jsonResult;
    }

    /**
     * 退货申请信息提交
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "/doproductback.html", method = { RequestMethod.POST })
    public @ResponseBody HttpJsonResult<Boolean> productBackSubmit(HttpServletRequest request,
                                                                   HttpServletResponse response,
                                                                   Map<String, Object> dataMap,
                                                                   MemberProductBack memberProductBack) {
        Member member = WebFrontSession.getLoginedUser(request);
        ServiceResult<Boolean> serviceResult = memberProductBackService
            .saveMemberProductBack(memberProductBack, member);
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

    /**
     * 换货申请信息提交
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "/doproductexchange.html", method = { RequestMethod.POST })
    public @ResponseBody HttpJsonResult<Boolean> productExchangeSubmit(HttpServletRequest request,
                                                                       HttpServletResponse response,
                                                                       Map<String, Object> dataMap,
                                                                       MemberProductExchange memberProductExchange) {
        Member member = WebFrontSession.getLoginedUser(request);
        ServiceResult<Boolean> serviceResult = memberProductExchangeService
            .saveMemberProductExchange(memberProductExchange, member);
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

    /**
     * 跳转到 退货列表页面
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/back.html", method = { RequestMethod.GET })
    public String back(HttpServletRequest request, HttpServletResponse response,
                       Map<String, Object> dataMap) {

        PagerInfo pager = new PagerInfo(ConstantsEJS.DEFAULT_PAGE_SIZE_10, 1);
        Member member = WebFrontSession.getLoginedUser(request);
        ServiceResult<List<MemberProductBack>> serviceResult = memberProductBackService
            .getBackListWithPrdAndOp(pager, member.getId());

        dataMap.put("backList", serviceResult.getResult());
        dataMap.put("backCount", pager.getRowsCount());
        dataMap.put("pageSize", pager.getPageSize());

        return "h5v1/member/service/back/back";
    }

    /**
     * ajax异步加载更多
     * @param request
     * @param response
     * @param dataMap
     * @param pageIndex
     * @return
     */
    @RequestMapping(value = "/moreback.html", method = { RequestMethod.GET })
    public String moreBack(HttpServletRequest request, HttpServletResponse response,
                           Map<String, Object> dataMap, Integer pageIndex) {

        PagerInfo pager = new PagerInfo(ConstantsEJS.DEFAULT_PAGE_SIZE_10, pageIndex);
        Member member = WebFrontSession.getLoginedUser(request);
        ServiceResult<List<MemberProductBack>> serviceResult = memberProductBackService
            .getBackListWithPrdAndOp(pager, member.getId());

        dataMap.put("backList", serviceResult.getResult());

        return "h5v1/member/service/back/backlist";
    }

    /**
     * 跳转到 换货列表页面
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/exchange.html", method = { RequestMethod.GET })
    public String toProductExchangeList(HttpServletRequest request, HttpServletResponse response,
                                        Map<String, Object> dataMap) {

        PagerInfo pager = new PagerInfo(ConstantsEJS.DEFAULT_PAGE_SIZE_10, 1);
        Member member = WebFrontSession.getLoginedUser(request);
        ServiceResult<List<MemberProductExchange>> serviceResult = memberProductExchangeService
            .getExchangeListWithPrdAndOp(pager, member.getId());

        dataMap.put("exchangeList", serviceResult.getResult());
        dataMap.put("exchangeCount", pager.getRowsCount());
        dataMap.put("pageSize", pager.getPageSize());

        return "h5v1/member/service/exchange/exchange";
    }

    /**
     * ajax异步加载更多
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/moreexchange.html", method = { RequestMethod.GET })
    public String moreExchange(HttpServletRequest request, HttpServletResponse response,
                               Map<String, Object> dataMap, Integer pageIndex) {

        PagerInfo pager = new PagerInfo(ConstantsEJS.DEFAULT_PAGE_SIZE_10, pageIndex);
        Member member = WebFrontSession.getLoginedUser(request);
        ServiceResult<List<MemberProductExchange>> serviceResult = memberProductExchangeService
            .getExchangeListWithPrdAndOp(pager, member.getId());

        dataMap.put("exchangeList", serviceResult.getResult());

        return "h5v1/member/service/exchange/exchangelist";
    }

}
