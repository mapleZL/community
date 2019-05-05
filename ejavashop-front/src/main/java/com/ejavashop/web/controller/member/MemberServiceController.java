package com.ejavashop.web.controller.member;

import java.math.BigDecimal;
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
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.HttpJsonResult;
import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.PaginationUtil;
import com.ejavashop.core.RandomUtil;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.WebUtil;
import com.ejavashop.entity.member.Member;
import com.ejavashop.entity.member.MemberApplyDrawing;
import com.ejavashop.entity.member.MemberProductBack;
import com.ejavashop.entity.member.MemberProductExchange;
import com.ejavashop.entity.order.Orders;
import com.ejavashop.entity.order.OrdersProduct;
import com.ejavashop.entity.promotion.coupon.CouponUser;
import com.ejavashop.entity.seller.SellerComplaint;
import com.ejavashop.service.member.IMemberApplyDrawingService;
import com.ejavashop.service.member.IMemberProductBackService;
import com.ejavashop.service.member.IMemberProductExchangeService;
import com.ejavashop.service.member.IMemberService;
import com.ejavashop.service.order.IOrdersProductService;
import com.ejavashop.service.order.IOrdersService;
import com.ejavashop.service.promotion.ICouponService;
import com.ejavashop.service.seller.ISellerComplaintService;
import com.ejavashop.vo.seller.FrontSellerComplaintVO;
import com.ejavashop.web.controller.BaseController;
import com.ejavashop.web.util.UploadUtil;
import com.ejavashop.web.util.WebFrontSession;

/**
 * 客户服务：退换货、提款申请、申诉
 *                       
 * @Filename: MemberServiceController.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Controller
@RequestMapping(value = "member")
public class MemberServiceController extends BaseController {

    @Resource
    private IOrdersService                ordersService;

    @Resource
    private IOrdersProductService         ordersProductService;

    @Resource
    private IMemberProductBackService     memberProductBackService;

    @Resource
    private IMemberProductExchangeService memberProductExchangeService;

    @Resource
    private ISellerComplaintService       sellerComplaintService;

    @Resource
    private IMemberApplyDrawingService    memberApplyDrawingService;

    @Resource
    private IMemberService                memberService;

    @Resource
    private ICouponService                couponService;

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

        return "front/member/usercenter/memberservice/productbackapply";
    }

    /**
     * 跳转到 退货申请录入界面
     * @param request
     * @param response
     * @param dataMap
     * @param orderProductId  网单id
     * @return
     */
    @RequestMapping(value = "/productbackapply.html", method = { RequestMethod.GET })
    public String productBackApply(HttpServletRequest request, HttpServletResponse response,
                                   Map<String, Object> dataMap,
                                   @RequestParam(value = "orderProductId", required = true) Integer orderProductId,
                                   @RequestParam(value = "orderId", required = true) Integer orderId) {

        ServiceResult<OrdersProduct> serviceResult = ordersProductService
            .getOrdersProductWithImgById(orderProductId);

        ServiceResult<Orders> orderResult = new ServiceResult<Orders>();
        //查询订单信息
        orderResult = ordersService.getOrderWithOPById(orderId,"front");

        dataMap.put("order", orderResult.getResult());
        dataMap.put("orderProduct", serviceResult.getResult());

        return "front/member/usercenter/memberservice/productbackapplyinput";
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
     * 跳转到 退货列表页面
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/back.html", method = { RequestMethod.GET })
    public String toProductBackList(HttpServletRequest request, HttpServletResponse response,
                                    Map<String, Object> dataMap) {

        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        Member member = WebFrontSession.getLoginedUser(request);
        ServiceResult<List<MemberProductBack>> serviceResult = new ServiceResult<List<MemberProductBack>>();
        serviceResult = memberProductBackService.getMemberProductBackList(pager, member.getId());

        //分页对象
        PaginationUtil pm = new PaginationUtil(pager.getPageSize(),
            String.valueOf(pager.getPageIndex()), pager.getRowsCount(),
            request.getRequestURI() + "");

        dataMap.put("backList", serviceResult.getResult());
        dataMap.put("page", pm);

        return "front/member/usercenter/memberservice/productbacklist";
    }

    /**
     * 跳转到 退货 查看页面
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/backdetail.html", method = { RequestMethod.GET })
    public String toProductBackDetail(HttpServletRequest request, HttpServletResponse response,
                                      Map<String, Object> dataMap,
                                      @RequestParam(value = "backid", required = true) Integer backid,
                                      @RequestParam(value = "orderProductId", required = true) Integer orderProductId,
                                      @RequestParam(value = "orderId", required = true) Integer orderId) {

        //根据网单id 查询网单信息
        ServiceResult<OrdersProduct> result = ordersProductService
            .getOrdersProductWithImgById(orderProductId);

        ServiceResult<Orders> orderResult = new ServiceResult<Orders>();
        //查询订单信息
        orderResult = ordersService.getOrderWithOPById(orderId,"front");

        //查询退货信息
        ServiceResult<MemberProductBack> serviceResult = new ServiceResult<MemberProductBack>();
        serviceResult = memberProductBackService.getMemberProductBackById(backid);

        MemberProductBack memberProductBack = serviceResult.getResult();
        if (memberProductBack != null && memberProductBack.getBackCouponUserId() != null
            && memberProductBack.getBackCouponUserId() > 0) {
            ServiceResult<CouponUser> couponUserResult = couponService
                .getCouponUserById(memberProductBack.getBackCouponUserId());
            dataMap.put("couponUser", couponUserResult.getResult());
        }

        dataMap.put("orderProduct", result.getResult());
        dataMap.put("info", memberProductBack);
        dataMap.put("order", orderResult.getResult());
        return "front/member/usercenter/memberservice/productbackdetail";
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
     * 跳转到 换货列表页面
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/exchange.html", method = { RequestMethod.GET })
    public String toProductExchangeList(HttpServletRequest request, HttpServletResponse response,
                                        Map<String, Object> dataMap) {

        Map<String, Object> queryMap = WebUtil.handlerQueryMapNoQ(request);
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        Member member = WebFrontSession.getLoginedUser(request);
        ServiceResult<List<MemberProductExchange>> serviceResult = memberProductExchangeService
            .getMemberProductExchangeList(queryMap, pager, member.getId());

        //分页对象
        PaginationUtil pm = new PaginationUtil(pager.getPageSize(),
            String.valueOf(pager.getPageIndex()), pager.getRowsCount(),
            request.getRequestURI() + "");

        dataMap.put("exchangeList", serviceResult.getResult());
        dataMap.put("page", pm);

        return "front/member/usercenter/memberservice/productexchangelist";
    }

    /**
     * 跳转到 换货 查看页面
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/exchangedetail.html", method = { RequestMethod.GET })
    public String toProductExchangeDetail(HttpServletRequest request, HttpServletResponse response,
                                          Map<String, Object> dataMap,
                                          @RequestParam(value = "backid", required = true) Integer backid,
                                          @RequestParam(value = "orderProductId", required = true) Integer orderProductId,
                                          @RequestParam(value = "orderId", required = true) Integer orderId) {

        //根据网单id 查询网单信息
        ServiceResult<OrdersProduct> result = ordersProductService
            .getOrdersProductWithImgById(orderProductId);

        //查询订单信息
        ServiceResult<Orders> orderResult = ordersService.getOrderWithOPById(orderId,"front");

        //查询换货信息
        ServiceResult<MemberProductExchange> serviceResult = new ServiceResult<MemberProductExchange>();
        serviceResult = memberProductExchangeService.getMemberProductExchangeById(backid);

        dataMap.put("orderProduct", result.getResult());
        dataMap.put("info", serviceResult.getResult());
        dataMap.put("order", orderResult.getResult());
        return "front/member/usercenter/memberservice/productExchangedetail";
    }

    /**
     * 跳转到 申诉申请录入界面 
     * @param request
     * @param response
     * @param dataMap
     * @param orderProductId 网单ID
     * @param productBackId  退货申请ID
     * @param productExchangeId 换货申请ID
     * @param orderId 订单ID
     * @return
     */
    @RequestMapping(value = "/addcomplain.html", method = { RequestMethod.GET })
    public String toComplainApply(HttpServletRequest request, HttpServletResponse response,
                                  Map<String, Object> dataMap,
                                  @RequestParam(value = "orderProductId", required = true) Integer orderProductId,
                                  Integer productBackId, Integer productExchangeId,
                                  @RequestParam(value = "orderId", required = true) Integer orderId) {

        ServiceResult<OrdersProduct> serviceResult = ordersProductService
            .getOrdersProductWithImgById(orderProductId);

        ServiceResult<Orders> orderResult = new ServiceResult<Orders>();
        //查询订单信息
        orderResult = ordersService.getOrderWithOPById(orderId,"front");

        dataMap.put("order", orderResult.getResult());
        dataMap.put("orderProduct", serviceResult.getResult());
        dataMap.put("productBackId", productBackId);
        dataMap.put("productExchangeId", productExchangeId);

        return "front/member/usercenter/memberservice/complainapply";
    }

    /**
     * 申诉提交
     * @param request
     * @param response
     * @param dataMap
     * @param sellerComplaint
     * @return
     */
    @RequestMapping(value = "/savecomplain.html", method = { RequestMethod.POST })
    public @ResponseBody HttpJsonResult<SellerComplaint> complainSubmit(MultipartHttpServletRequest request,
                                                                        HttpServletResponse response,
                                                                        Map<String, Object> dataMap,
                                                                        SellerComplaint sellerComplaint) {
        Member member = WebFrontSession.getLoginedUser(request);
        HttpJsonResult<SellerComplaint> jsonResult = new HttpJsonResult<SellerComplaint>();

        try {
            sellerComplaint
                .setImage(UploadUtil.getInstance().uploadFile2ImageServer("pic", request));
            //保存申诉
            ServiceResult<SellerComplaint> serviceResult = sellerComplaintService
                .saveSellerComplaint(member, sellerComplaint);
            if (!serviceResult.getSuccess()) {
                if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                    throw new RuntimeException(serviceResult.getMessage());
                } else {
                    jsonResult = new HttpJsonResult<SellerComplaint>(serviceResult.getMessage());
                }
            }
        } catch (Exception e) {
            log.error(
                "[shoppingmall-front-web][MemberServiceController][complainSubmit] exception:", e);
            jsonResult.setMessage(e.getMessage());
            return jsonResult;
        }

        return jsonResult;
    }

    /**
     * 跳转到 申诉申请 查看  查看是针对哪个网单进行的申请，并且查出其退换货信息
     * @param request
     * @param response
     * @param dataMap
     * @param orderProductId
     * @param orderId
     * @return
     */
    @RequestMapping(value = "/complaindetail.html", method = { RequestMethod.GET })
    public String toComplainApplyDetail(HttpServletRequest request, HttpServletResponse response,
                                        Map<String, Object> dataMap,
                                        @RequestParam(value = "orderProductId", required = true) Integer orderProductId,
                                        @RequestParam(value = "orderId", required = true) Integer orderId,
                                        @RequestParam(value = "infoId", required = true) Integer infoId) {

        ServiceResult<OrdersProduct> serviceResult = ordersProductService
            .getOrdersProductWithImgById(orderProductId);

        //查询申诉信息
        ServiceResult<SellerComplaint> scResult = new ServiceResult<SellerComplaint>();
        scResult = sellerComplaintService.getSellerComplaintById(infoId);

        //根据申诉信息取退换货信息
        if (scResult.getResult() != null) {
            SellerComplaint sellerComplaint = scResult.getResult();
            Integer backId = sellerComplaint.getProductBackId();
            Integer exchangeId = sellerComplaint.getProductExchangeId();
            if (backId != null && backId != 0) {
                MemberProductBack memberProductBack = (memberProductBackService
                    .getMemberProductBackById(backId)).getResult();
                dataMap.put("backInfo", memberProductBack);
            } else if (exchangeId != null && exchangeId != 0) {
                MemberProductExchange memberProductExchange = (memberProductExchangeService
                    .getMemberProductExchangeById(exchangeId)).getResult();
                dataMap.put("exchangeInfo", memberProductExchange);
            }
        }

        dataMap.put("orderProduct", serviceResult.getResult());
        dataMap.put("info", scResult.getResult());

        return "front/member/usercenter/memberservice/complainapplydetail";
    }

    /**
     * 跳转到 申诉列表界面 
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "/complain.html", method = { RequestMethod.GET })
    public String toComplainApply(HttpServletRequest request, HttpServletResponse response,
                                  Map<String, Object> dataMap) {

        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        Member member = WebFrontSession.getLoginedUser(request);
        ServiceResult<List<FrontSellerComplaintVO>> serviceResult = sellerComplaintService
            .getSellerComplaintList(pager, member.getId());

        //分页对象
        PaginationUtil pm = new PaginationUtil(pager.getPageSize(),
            String.valueOf(pager.getPageIndex()), pager.getRowsCount(),
            request.getRequestURI() + "");

        dataMap.put("complaintList", serviceResult.getResult());
        dataMap.put("page", pm);

        return "front/member/usercenter/memberservice/complainlist";
    }

    /**
     * 跳转到 提现申请列表界面 
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "/withdraw.html", method = { RequestMethod.GET })
    public String toWithdrawDepositList(HttpServletRequest request, HttpServletResponse response,
                                        Map<String, Object> dataMap) {

        Member member = WebFrontSession.getLoginedUser(request);

        Map<String, String> queryMap = new HashMap<String, String>();
        queryMap.put("q_memberId", member.getId().toString());
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);

        ServiceResult<List<MemberApplyDrawing>> serviceResult = new ServiceResult<List<MemberApplyDrawing>>();
        serviceResult = memberApplyDrawingService.getMemberApplyDrawings(queryMap, pager);

        //分页对象
        PaginationUtil pm = new PaginationUtil(pager.getPageSize(),
            String.valueOf(pager.getPageIndex()), pager.getRowsCount(),
            request.getRequestURI() + "");

        dataMap.put("infoList", serviceResult.getResult());
        dataMap.put("page", pm);

        return "front/member/usercenter/memberservice/withdrawdepositlist";
    }

    /**
     * 跳转到 提现申请 查看界面
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "/withdrawdetail.html", method = { RequestMethod.GET })
    public String toWithdrawDepositDetail(HttpServletRequest request, HttpServletResponse response,
                                          Map<String, Object> dataMap,
                                          @RequestParam(value = "infoId", required = true) Integer infoId) {

        ServiceResult<MemberApplyDrawing> serviceResult = new ServiceResult<MemberApplyDrawing>();
        serviceResult = memberApplyDrawingService.getMemberApplyDrawing(infoId);

        dataMap.put("info", serviceResult.getResult());

        return "front/member/usercenter/memberservice/withdrawdepositdetail";
    }

    /**
     * 跳转到 提现申请录入界面 取账户余额 判断是否可以提现
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "/withdrawapply.html", method = { RequestMethod.GET })
    public String toWithdrawDepositApply(HttpServletRequest request, HttpServletResponse response,
                                         Map<String, Object> dataMap) {
        Member member = WebFrontSession.getLoginedUser(request);
        ServiceResult<Member> serviceResult = new ServiceResult<Member>();
        serviceResult = memberService.getMemberById(member.getId());

        //账户余额 默认为0
        BigDecimal balance = new BigDecimal(0);
        if (serviceResult.getResult() != null) {
            Member memberDb = serviceResult.getResult();
            balance = memberDb.getBalance();

        }
        dataMap.put("balance", balance);

        return "front/member/usercenter/memberservice/withdrawdepositapply";
    }

    /**
     * 跳转到 提现申请提交 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/dowithdrawapply.html", method = { RequestMethod.POST })
    public @ResponseBody HttpJsonResult<Integer> withdrawDepositSubmit(HttpServletRequest request,
                                                                       HttpServletResponse response,
                                                                       MemberApplyDrawing memberApplyDrawing) {

        Member member = WebFrontSession.getLoginedUser(request);
        memberApplyDrawing.setMemberId(member.getId());
        memberApplyDrawing.setMemberName(member.getName());
        //设置提现编号
        memberApplyDrawing.setCode(RandomUtil.getOrderSn());
        memberApplyDrawing.setState(ConstantsEJS.MEMBER_DRAWING_STATE_1);
        ServiceResult<Integer> serviceResult = memberApplyDrawingService
            .saveMemberApplyDrawing(memberApplyDrawing);
        HttpJsonResult<Integer> jsonResult = new HttpJsonResult<Integer>();

        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                jsonResult = new HttpJsonResult<Integer>(serviceResult.getMessage());
            }
        }
        return jsonResult;
    }

}
