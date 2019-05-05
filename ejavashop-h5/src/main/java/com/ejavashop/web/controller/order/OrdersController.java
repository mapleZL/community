package com.ejavashop.web.controller.order;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.ConvertUtil;
import com.ejavashop.core.HttpJsonResult;
import com.ejavashop.core.HttpJsonResultForAjax;
import com.ejavashop.core.Md5;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.StringUtil;
import com.ejavashop.core.WebUtil;
import com.ejavashop.entity.member.Member;
import com.ejavashop.entity.member.MemberAddress;
import com.ejavashop.entity.member.MemberCredit;
import com.ejavashop.entity.operate.Config;
import com.ejavashop.entity.order.Invoice;
import com.ejavashop.entity.order.Orders;
import com.ejavashop.entity.order.TerraceSelfCoupon;
import com.ejavashop.entity.product.Product;
import com.ejavashop.entity.product.ProductGoods;
import com.ejavashop.entity.promotion.bidding.ActBidding;
import com.ejavashop.entity.promotion.coupon.CouponUser;
import com.ejavashop.entity.promotion.flash.ActFlashSale;
import com.ejavashop.entity.promotion.group.ActGroup;
import com.ejavashop.entity.seller.Seller;
import com.ejavashop.service.cart.ICartService;
import com.ejavashop.service.member.IInvoiceService;
import com.ejavashop.service.member.IMemberAddressService;
import com.ejavashop.service.member.IMemberCreditService;
import com.ejavashop.service.member.IMemberService;
import com.ejavashop.service.operate.IConfigService;
import com.ejavashop.service.order.IOrdersProductService;
import com.ejavashop.service.order.IOrdersService;
import com.ejavashop.service.order.ITerraceSelfCouponService;
import com.ejavashop.service.product.IProductGoodsService;
import com.ejavashop.service.product.IProductService;
import com.ejavashop.service.promotion.IActBiddingService;
import com.ejavashop.service.promotion.IActFlashSaleService;
import com.ejavashop.service.promotion.IActGroupService;
import com.ejavashop.service.promotion.ICouponService;
import com.ejavashop.service.seller.ISellerService;
import com.ejavashop.service.seller.ISellerTransportService;
import com.ejavashop.vo.cart.CartInfoVO;
import com.ejavashop.vo.member.FrontCheckPwdVO;
import com.ejavashop.vo.order.OrderCommitVO;
import com.ejavashop.vo.order.OrderCouponVO;
import com.ejavashop.vo.order.OrderSuccessVO;
import com.ejavashop.web.controller.BaseController;
import com.ejavashop.web.csrf.CsrfTokenManager;
import com.ejavashop.web.util.CommUtil;
import com.ejavashop.web.util.WebFrontSession;

/**
 * 购物流程-订单<br>
 * 本controller中得请求都需要登录才能访问
 * 
 * @Filename: FrontOrdersController.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Controller
public class OrdersController extends BaseController {

    @Resource
    private IOrdersService          ordersService;
    @Resource
    private IOrdersProductService   ordersProductService;
    @Resource
    private ICartService            cartService;
    @Resource
    private IMemberAddressService   memberAddressService;
    @Resource
    private IInvoiceService         invoiceService;
    @Resource
    private IMemberService          memberService;
    @Resource
    private IConfigService          configservice;
    @Resource
    private ICouponService          couponService;
    @Resource
    private IActFlashSaleService    actFlashSaleService;
    @Resource
    private ISellerTransportService sellerTransportService;
    @Resource
    private ISellerService          sellerService;
    @Resource
    private IProductService         productService;
    @Resource
    private IProductGoodsService    productGoodsService;
    @Resource
    private IMemberCreditService    memberCreditService;
    @Resource
    private IActGroupService        actGroupService;
    @Resource
    private IActBiddingService      actBiddingService;
    @Resource
    private ITerraceSelfCouponService terraceSelfCouponService;

    /**
     * 跳转到提交订单页面 计算总金额,运费、货品小计，按店铺拆分订单
     * @param request
     * @param response
     * @param map
     * @return
     * @throws IOException 
     */
    @RequestMapping(value = "order/info.html", method = { RequestMethod.GET })
    public String toOrderSubmit(HttpServletRequest request, ModelMap map,
                                HttpServletResponse response) {
        Member member = WebFrontSession.getLoginedUser(request);

        MemberAddress memberAddress = null;

        Integer addressId = ConvertUtil.toInt(request.getParameter("addressId"), 0);
        Integer transportType = ConvertUtil.toInt(request.getParameter("transportType"), 8);
        if (addressId > 0) {
            ServiceResult<MemberAddress> memberAddressResult = memberAddressService
                .getMemberAddressById(addressId);
            memberAddress = memberAddressResult.getResult();
        }

        if (memberAddress == null) {
            // 收货地址信息
            ServiceResult<List<MemberAddress>> serviceResult = memberAddressService
                .getMemberAddressByMId(member.getId());
            // 获取默认收货地址，如果没有取第一个
            if (serviceResult.getSuccess()) {
                List<MemberAddress> addressList = serviceResult.getResult();
                if (addressList != null && addressList.size() > 0) {
                    memberAddress = addressList.get(0);
                    for (MemberAddress address : addressList) {
                        if (address.getState() == MemberAddress.STATE_1) {
                            memberAddress = address;
                            break;
                        }
                    }
                }
            }
        }
        map.put("transportType", transportType);
        map.put("address", memberAddress);
        // 构建默认值 ，默认在线支付。收货地址为默认地址，发票默认为不开发票
        OrderCommitVO orderCommitVO = new OrderCommitVO();
        orderCommitVO.setInvoiceType("");
        orderCommitVO.setInvoiceTitle("");
        orderCommitVO.setPaymentName("在线支付");
        orderCommitVO.setPaymentCode(ConstantsEJS.PAYMENT_CODE_ONLINE);
        map.put("orderCommitVO", orderCommitVO);

        // 取购物车信息  产品价格 按照商家来区分
        // 查询购物车
        ServiceResult<CartInfoVO> cartServiceResult = cartService.getCartInfoByMId(member.getId(),
            memberAddress, ConstantsEJS.SOURCE_2_H5, 2, transportType);
        map.put("cartInfoVO", cartServiceResult.getResult());

        // 获取发票信息
        ServiceResult<List<Invoice>> invoiceResult = invoiceService.getInvoiceByMId(member.getId());
        map.put("invoiceList", invoiceResult.getResult());

        //取会员余额信息
        ServiceResult<Member> memberResult = memberService.getMemberById(member.getId());
        if (memberResult.getResult() == null) {
            map.put("info", "会员信息获取失败。");
            return "h5v1/commons/error";
        }
        map.put("member", memberResult.getResult());

        ServiceResult<MemberCredit> mcresult = memberCreditService
            .getMemberCreditByMemberId(member.getId());
        MemberCredit mc = mcresult.getResult();
        if (!isNull(mc) && mc.getState() == 1) {
            map.put("memberCredit", mc);
        }
        ServiceResult<Config> configById = configservice.getConfigById(ConstantsEJS.CONFIG_ID);
        if (configById.getResult() != null) {
            Config config = configById.getResult();
            if (config.getIntegralScale() > 0) {
                map.put("config", config);
            }
        }
        //add by Ls 查询该用户名下可使用的全场红包
        ServiceResult<List<CouponUser>> couponUser = couponService.getEffectiveByMemberIdAndSellerId(member.getId(), 0);
        if(couponUser.getResult() != null){
            map.put("couponUser", couponUser.getResult());
        }
        return "h5v1/order/order";
    }

    //    /**
    //     * 根据地址ID计算新的运费信息
    //     * @param request
    //     * @param response
    //     * @param map
    //     * @param addressId
    //     * @return
    //     */
    @RequestMapping(value = "order/calculateTransFee.html", method = { RequestMethod.POST })
    public @ResponseBody HttpJsonResult<CartInfoVO> orderSubmit(HttpServletRequest request,
                                                                HttpServletResponse response,
                                                                ModelMap map, Integer addressId,
                                                                Integer transportType) {
        Member member = WebFrontSession.getLoginedUser(request);
        ServiceResult<MemberAddress> memberAddressRlt = memberAddressService
            .getMemberAddressById(addressId);
        if (!memberAddressRlt.getSuccess() || memberAddressRlt.getResult() == null) {
            return new HttpJsonResult<>("收获地址信息获取失败！");
        }
        // 查询购物车
        ServiceResult<CartInfoVO> cartServiceResult = cartService.getCartInfoByMId(member.getId(),
            memberAddressRlt.getResult(), ConstantsEJS.SOURCE_2_H5, 2, transportType);
        if (!cartServiceResult.getSuccess() || cartServiceResult.getResult() == null) {
            return new HttpJsonResult<>("计算运费信息失败！");
        }
        HttpJsonResult<CartInfoVO> jsonResult = new HttpJsonResult<CartInfoVO>();
        jsonResult.setData(cartServiceResult.getResult());
        return jsonResult;
    }

    /**
     * 提交订单 计算总金额 按店铺拆分订单
     * @param request
     * @param response
     * @param map
     * @return
     * @throws IOException 
     */
    @RequestMapping(value = "order/ordercommit.html", method = { RequestMethod.POST })
    public @ResponseBody HttpJsonResultForAjax<OrderSuccessVO> orderSubmit(HttpServletRequest request,
                                                                           HttpServletResponse response,
                                                                           OrderCommitVO orderCommitVO,
                                                                           ModelMap map,
                                                                           String ordersType) {
        Member member = WebFrontSession.getLoginedUser(request);
        orderCommitVO.setMemberId(member.getId());

        if (orderCommitVO.getInvoiceStatus() == null) {
            // 默认不开发票
            orderCommitVO.setInvoiceStatus(Orders.INVOICE_STATUS_0);
        }
        // 设定IP地址
        orderCommitVO.setIp(WebUtil.getIpAddr(request));
        // 设定来源
        orderCommitVO.setSource(ConstantsEJS.SOURCE_2_H5);
        orderCommitVO.setRemark(request.getParameter("remark"));
        orderCommitVO.setLogisticsId(Integer.valueOf(
            request.getParameter("transId") == null ? "8" : request.getParameter("transId")));
        // 获取优惠券使用信息
        Map<Integer, OrderCouponVO> sellerCouponMap = new HashMap<Integer, OrderCouponVO>();
        String useCouponSellerIds = request.getParameter("useCouponSellerIds");
        if (!StringUtil.isEmpty(useCouponSellerIds, true)) {
            String[] split = useCouponSellerIds.split(",");
            for (String sellerIdStr : split) {
                if (!StringUtil.isEmpty(sellerIdStr, true)) {
                    Integer sellerId = ConvertUtil.toInt(sellerIdStr, 0);
                    String couponTypeStr = request.getParameter("couponType" + sellerId);
                    Integer couponType = ConvertUtil.toInt(couponTypeStr, 0);
                    String couponSn = request.getParameter("couponSn" + sellerId);
                    String couponPassword = request.getParameter("couponPassword" + sellerId);
                    OrderCouponVO orderCouponVO = new OrderCouponVO();
                    orderCouponVO.setSellerId(sellerId);
                    orderCouponVO.setCouponType(couponType);
                    orderCouponVO.setCouponSn(couponSn);
                    orderCouponVO.setCouponPassword(couponPassword);
                    sellerCouponMap.put(sellerId, orderCouponVO);
                }
            }
        }
        orderCommitVO.setSellerCouponMap(sellerCouponMap);
        orderCommitVO.setSendType("0");

        // 提交订单
        ServiceResult<OrderSuccessVO> serviceResult = ordersService.orderCommit(orderCommitVO, ordersType);
        HttpJsonResultForAjax<OrderSuccessVO> jsonResult = new HttpJsonResultForAjax<OrderSuccessVO>();
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                jsonResult = new HttpJsonResultForAjax<OrderSuccessVO>(null,
                    CsrfTokenManager.getTokenForSession(
                        CsrfTokenManager.getMemkeyFromRequest(request), request.getSession()));
                jsonResult.setMessage(serviceResult.getMessage());
                return jsonResult;
            }
        }

        //订单提交后返回结果
        OrderSuccessVO orderSuccessVO = serviceResult.getResult();
        if (orderSuccessVO.getIsPaid()) {
            // 如果已经付过款，则调用下单送积分方法
            for (Orders order : orderSuccessVO.getOrdersList()) {
                memberService.memberOrderSendValue(member.getId(), member.getName(), order.getId());
            }
        }
        //支付随机码 避免重复提交
        String order_session = CommUtil.randomString(32);
        // 存入session，支付时取出后与参数传入的对比，判断是否二次提交
        request.getSession(false).setAttribute("order_session", order_session);
        request.getSession(false).setAttribute("order_success_vo", orderSuccessVO);
        orderSuccessVO.setPaySessionstr(order_session);

        jsonResult.setData(orderSuccessVO);

        return jsonResult;
    }

    /**
     * 跳转到提交订单成功页面 （货到付款）
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "order/success.html", method = { RequestMethod.GET })
    public String toOrderSuccess(HttpServletRequest request, HttpServletResponse response,
                                 Map<String, Object> dataMap,
                                 @RequestParam(value = "relationOrderSn", required = true) String relationOrderSn) {
        Member member = WebFrontSession.getLoginedUser(request);
        //根据关联订单 查询订单信息
        ServiceResult<OrderSuccessVO> serviceResult = ordersService
            .getOrdersByRelationOrderSn(relationOrderSn, member.getId());
        dataMap.put("ordervo", serviceResult.getResult());
        //为二次加工订单定制页面
        if (StringUtil.isEmpty(relationOrderSn, true)) {
            dataMap.put("info", "请选择要支付的订单，谢谢！");
            return "front/commons/error";
        } else {
            ServiceResult<List<Orders>> serviceResult2 = ordersService.getOrdersByTradeNo(relationOrderSn);
            List<Orders> ordersList = serviceResult2.getResult();
            for (Orders orders : ordersList) {
            	if (orders != null && (orders.getPaymentStatus() == Orders.PAYMENT_STATUS_2 || orders.getPaymentStatus() == Orders.PAYMENT_STATUS_3)) {//此订单为二次加工订单则跳转新的页面
            	    dataMap.put("relationOrderSn", relationOrderSn);
                    return "h5v1/order/twojiagong2";
                }
			}
        }
        return "h5v1/order/ordersuccess";
    }

    /**
     * 跳转到支付页面 （在线支付）
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "order/pay.html", method = { RequestMethod.GET })
    public String toPayfor(HttpServletRequest request, HttpServletResponse response,
                           Map<String, Object> dataMap, String relationOrderSn,
                           String paySessionstr) {

        if (StringUtil.isEmpty(relationOrderSn, true)) {
            dataMap.put("info", "请选择要支付的订单，谢谢！");
            return "h5v1/commons/error";
        } else {
            if (ifNeedMoreOperation(dataMap, relationOrderSn)) {
                return "front/order/twojiagong";
            }
        }
        Member member = WebFrontSession.getLoginedUser(request);
        ServiceResult<Member> memberResult = memberService.getMemberById(member.getId());
        if (memberResult.getResult() == null) {
            dataMap.put("info", "会员信息获取失败。");
            return "h5v1/commons/error";
        }
        dataMap.put("member", memberResult.getResult());

        ServiceResult<MemberCredit> mcresult = memberCreditService
            .getMemberCreditByMemberId(member.getId());
        MemberCredit mc = mcresult.getResult();
        //状态为未到期且到期日在当前时间之后
        if (!isNull(mc) && mc.getState() == 1 && mc.getExpireDate().after(new Date())) {
            dataMap.put("memberCredit", mc);
        }
        BigDecimal reduceCoupon = BigDecimal.ZERO;
        ServiceResult<TerraceSelfCoupon> terResult = terraceSelfCouponService.getTerraceSelfCouponByTradeNo(relationOrderSn);
        if(terResult.getSuccess() && terResult.getResult() != null){
            TerraceSelfCoupon reCoupon = terResult.getResult();
            reduceCoupon = reCoupon.getCouponValue();
        }
        
        // 如果paySessionstr不为空，则是从下单后直接跳转过来，否则是从订单列表页跳转而来
        if (!StringUtil.isEmpty(paySessionstr, true)) {

            String order_session = CommUtil
                .null2String(request.getSession(false).getAttribute("order_session"));
            if (!order_session.equals(paySessionstr)) {
                dataMap.put("info", "请使用正常方式支付订单，谢谢！");
                return "h5v1/commons/error";
            }
            OrderSuccessVO orderSuccessVO = (OrderSuccessVO) request.getSession(false)
                .getAttribute("order_success_vo");
            if (orderSuccessVO == null) {
                dataMap.put("info", "session异常，请到订单中心支付订单，谢谢！");
                return "h5v1/commons/error";
            }
            
            dataMap.put("fromType", 1);

            dataMap.put("paySessionstr", paySessionstr);
            dataMap.put("relationOrderSn", relationOrderSn);
            dataMap.put("payAmount", orderSuccessVO.getPayAmount().subtract(reduceCoupon));
            dataMap.put("orderSuccessVO", orderSuccessVO);
        } else {

            dataMap.put("fromType", 2);

            // 支付随机码 避免重复提交
            String order_session = CommUtil.randomString(32);
            request.getSession(false).setAttribute("order_session", order_session);

            dataMap.put("paySessionstr", order_session);

            ServiceResult<OrderSuccessVO> orderSuccessVOResult = ordersService
                .getOrdersByRelationOrderSn(relationOrderSn, member.getId());
            if (!orderSuccessVOResult.getSuccess()) {
                dataMap.put("info", "订单信息获取出错，请稍后再试！");
                return "h5v1/commons/error";
            }
            OrderSuccessVO orderSuccessVO = orderSuccessVOResult.getResult();
            dataMap.put("relationOrderSn", relationOrderSn);
            dataMap.put("payAmount", orderSuccessVO.getPayAmount().subtract(reduceCoupon));
        }

        return "h5v1/order/payselect";

    }
    
    /**
     * 判定订单中是否存在二次加工订单
     * @param orders
     * @return
     */
    private boolean ifNeedMoreOperation(Map<String, Object> dataMap, String relationOrderSn) {
        ServiceResult<List<Orders>> serviceResult = ordersService.getOrdersByTradeNo(relationOrderSn);
        List<Orders> orders = serviceResult.getResult();
        boolean isMoreOperation = false;
        if (orders != null) {
            for (Orders order : orders) {
                if (order.getPaymentStatus() == Orders.PAYMENT_STATUS_2 || order.getPaymentStatus() == Orders.PAYMENT_STATUS_3) {//此订单为二次加工订单则跳转新的页面
                    dataMap.put("paymentStatus", order.getPaymentStatus());
                    isMoreOperation = true;
                }
            }
        }
        return isMoreOperation;
    }

    /**
     * 保存发票抬头
     * @param request
     * @param response
     * @param map
     * @return
     * @throws IOException 
     */
    @RequestMapping(value = "order/saveinvoice.html", method = { RequestMethod.POST })
    public @ResponseBody HttpJsonResult<Boolean> saveInvoice(HttpServletRequest request,
                                                             HttpServletResponse response,
                                                             Invoice invoice) {

        Member member = WebFrontSession.getLoginedUser(request);

        ServiceResult<Integer> serviceResult = new ServiceResult<Integer>();
        serviceResult = invoiceService.saveInvoice(invoice, member.getId());

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

    //    /**
    //     * 更新发票抬头
    //     * @param request
    //     * @param response
    //     * @param map
    //     * @return
    //     * @throws IOException 
    //     */
    //    @RequestMapping(value = "order/updateinvoice.html", method = { RequestMethod.POST })
    //    public @ResponseBody HttpJsonResult<Boolean> updateInvoice(HttpServletRequest request,
    //                                                               HttpServletResponse response,
    //                                                               Invoice invoice) {
    //        ServiceResult<Integer> serviceResult = new ServiceResult<Integer>();
    //        serviceResult = invoiceService.updateInvoice(invoice);
    //
    //        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
    //        if (!serviceResult.getSuccess()) {
    //            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
    //                throw new RuntimeException(serviceResult.getMessage());
    //            } else {
    //                jsonResult = new HttpJsonResult<Boolean>(serviceResult.getMessage());
    //            }
    //        }
    //        return jsonResult;
    //    }

    //    /**
    //     * 删除发票
    //     * @param request
    //     * @param response
    //     * @param map
    //     * @return
    //     * @throws IOException 
    //     */
    //    @RequestMapping(value = "order/deleteinvoice.html", method = { RequestMethod.GET })
    //    public @ResponseBody HttpJsonResult<Boolean> delInvoice(HttpServletRequest request,
    //                                                            HttpServletResponse response,
    //                                                            Invoice invoice,
    //                                                            @RequestParam(value = "invoiceId", required = true) Integer invoiceId) {
    //        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
    //        serviceResult = invoiceService.delInvoice(invoiceId);
    //
    //        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
    //        if (!serviceResult.getSuccess()) {
    //            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
    //                throw new RuntimeException(serviceResult.getMessage());
    //            } else {
    //                jsonResult = new HttpJsonResult<Boolean>(serviceResult.getMessage());
    //            }
    //        }
    //        return jsonResult;
    //    }

    /**
     * 判断 余额支付密码是否正确
     * @param request
     * @param response
     * @param map
     * @return
     * @throws IOException 
     */
    @RequestMapping(value = "order/checkbalancepwd.html", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<FrontCheckPwdVO> checkcheckBalancePwd(HttpServletRequest request,
                                                                              HttpServletResponse response,
                                                                              @RequestParam(value = "balancePwd", required = true) String balancePwd) {

        Member member = WebFrontSession.getLoginedUser(request);

        ServiceResult<FrontCheckPwdVO> serviceResult = new ServiceResult<FrontCheckPwdVO>();
        serviceResult = memberService.checkcheckBalancePwd(balancePwd, member.getId());

        HttpJsonResult<FrontCheckPwdVO> jsonResult = new HttpJsonResult<FrontCheckPwdVO>();
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                jsonResult = new HttpJsonResult<FrontCheckPwdVO>(serviceResult.getMessage());
            }
        }
        jsonResult.setData(serviceResult.getResult());
        return jsonResult;
    }

    /**
     * 获取用户当前可用的已绑定的优惠券
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "order/getsellercoupon.html", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<CouponUser>> getSellerCoupon(HttpServletRequest request,
                                                                          HttpServletResponse response) {

        Member member = WebFrontSession.getLoginedUser(request);

        Integer sellerId = ConvertUtil.toInt(request.getParameter("sellerId"), 0);
        //获得商品总金额（原价）
        /*  String totalWarePrice = request.getParameter("totalPrice");
        BigDecimal priceBigDecimal = null;
        if(!"".equals(totalWarePrice)){
        	priceBigDecimal = new BigDecimal(totalWarePrice);
        }*/
        ServiceResult<List<CouponUser>> serviceResult = couponService
            .getEffectiveByMemberIdAndSellerId(member.getId(), sellerId);

        /* ServiceResult<List<CouponUser>> serviceResult = couponService
            .getEffectiveByMemberIdAndSellerIdAndTotalPrice(member.getId(), sellerId, priceBigDecimal);*/

        HttpJsonResult<List<CouponUser>> jsonResult = new HttpJsonResult<List<CouponUser>>();
        if (!serviceResult.getSuccess()) {
            jsonResult = new HttpJsonResult<List<CouponUser>>(serviceResult.getMessage());
        }
        jsonResult.setData(serviceResult.getResult());
        return jsonResult;
    }

    /**
     * 检查优惠券的可用性
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "order/checksellercoupon.html", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<CouponUser> checkSellerCoupon(HttpServletRequest request,
                                                                      HttpServletResponse response) {

        Member member = WebFrontSession.getLoginedUser(request);

        String servicePrice = isNull(request.getParameter("servicePrice")) ? "0"
            : request.getParameter("servicePrice");
        String orderAmount = request.getParameter("orderAmount");
        String couponTypeStr = request.getParameter("couponType");
        Integer couponType = ConvertUtil.toInt(couponTypeStr, 0);
        String couponSn = request.getParameter("couponSn");
        String couponPassword = request.getParameter("couponPassword");
        Integer sellerId = ConvertUtil.toInt(request.getParameter("sellerId"), 0);

        ServiceResult<CouponUser> couponUserRlt = couponService
            .getCouponUserOnlyByCouponSn(couponSn);
        if (!couponUserRlt.getSuccess()) {
            return new HttpJsonResult<CouponUser>(couponUserRlt.getMessage());
        }
        if (couponUserRlt.getResult() == null) {
            return new HttpJsonResult<CouponUser>("优惠券不存在，请确认是否输入正确。");
        }
        CouponUser couponUser = couponUserRlt.getResult();

        Integer memberId = member.getId();

        if (!sellerId.equals(couponUser.getSellerId())) {
            return new HttpJsonResult<CouponUser>(
                "优惠券【" + couponUser.getCouponSn() + "】只能购买" + couponUser.getSellerName() + "的商品。");
        }

        if (couponType == OrderCouponVO.COUPON_TYPE_1) {
            // 检查优惠券所属用户
            if (!memberId.equals(couponUser.getMemberId())) {
                return new HttpJsonResult<CouponUser>(
                    "优惠券【" + couponUser.getCouponSn() + "】不是属于您的优惠券，不能使用。");
            }
        } else if (couponType == OrderCouponVO.COUPON_TYPE_2) {
            // 校验密码
            if (couponUser.getPassword() == null
                || !couponUser.getPassword().equals(Md5.getMd5String(couponPassword))) {
                return new HttpJsonResult<CouponUser>(
                    "优惠券【" + couponUser.getCouponSn() + "】密码不对，请重新输入。");
            }
            // 检查优惠券所属用户
            if (couponUser.getMemberId() > 0 && !couponUser.getMemberId().equals(memberId)) {
                return new HttpJsonResult<CouponUser>(
                    "优惠券【" + couponUser.getCouponSn() + "】不是属于您的优惠券，不能使用。");
            }
        }

        // 优惠券可使用次数
        if (couponUser.getCanUse() < 1) {
            return new HttpJsonResult<CouponUser>(
                "优惠券【" + couponUser.getCouponSn() + "】已使用过，不能再次使用。");
        }

        // 优惠券用户关联的优惠券信息校验
        // 适用最低金额校验
        BigDecimal minAmount = new BigDecimal(orderAmount).subtract(new BigDecimal(servicePrice));
        if (couponUser.getMinAmount().compareTo(minAmount) > 0) {
            return new HttpJsonResult<CouponUser>(
                "优惠券【" + couponUser.getCouponSn() + "】最低订单金额（不包含套餐费）不得小于<b>"
                                                  + couponUser.getMinAmount() + "</b>元。");
        }

        // 优惠券使用时间校验
        if (couponUser.getUseStartTime().after(new Date())) {
            return new HttpJsonResult<CouponUser>(
                "优惠券【" + couponUser.getCouponSn() + "】还没有到可使用时间。");
        }
        if (couponUser.getUseEndTime().before(new Date())) {
            return new HttpJsonResult<CouponUser>("优惠券【" + couponUser.getCouponSn() + "】已过期。");
        }

        // 使用渠道校验
        if (couponUser.getChannel().intValue() != ConstantsEJS.CHANNEL_1
            && ConstantsEJS.CHANNEL_3 != couponUser.getChannel().intValue()) {
            String channelStr = couponUser.getChannel().intValue() == ConstantsEJS.CHANNEL_2 ? "电脑端"
                : "移动端";
            return new HttpJsonResult<CouponUser>(
                "优惠券【" + couponUser.getCouponSn() + "】只能在" + channelStr + "使用。");
        }

        HttpJsonResult<CouponUser> jsonResult = new HttpJsonResult<CouponUser>();
        jsonResult.setData(couponUser);
        return jsonResult;
    }
    
    @RequestMapping(value = "order/getmarketcoupon.html", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<CouponUser> getMarketCoupon(HttpServletRequest request,
                                                                      HttpServletResponse response) {
        HttpJsonResult<CouponUser> jsonResult = new HttpJsonResult<CouponUser>();
        String orderAmount = request.getParameter("orderAmount");
        String couponTypeStr = request.getParameter("couponType");
        Integer couponType = ConvertUtil.toInt(couponTypeStr, 0);
        String couponSn = request.getParameter("couponSn");
        Integer sellerId = ConvertUtil.toInt(request.getParameter("sellerId"), 0);
        String servicePrice = isNull(request.getParameter("servicePrice")) ? "0"
            : request.getParameter("servicePrice");
        ServiceResult<CouponUser> couponUserRlt = couponService
                .getCouponUserOnlyByCouponSn(couponSn);
        if (!couponUserRlt.getSuccess()) {
            return new HttpJsonResult<CouponUser>(couponUserRlt.getMessage());
        }
        if (couponUserRlt.getResult() == null) {
            return new HttpJsonResult<CouponUser>("优惠券不存在，请确认是否输入正确。");
        }
        CouponUser couponUser = couponUserRlt.getResult();
        // 优惠券用户关联的优惠券信息校验
        // 适用最低金额校验
        BigDecimal minAmount = new BigDecimal(orderAmount).subtract(new BigDecimal(servicePrice));
        if (couponUser.getMinAmount().compareTo(minAmount) > 0) {
            return new HttpJsonResult<CouponUser>(
                "优惠券【" + couponUser.getCouponSn() + "】最低订单金额（不包含套餐费）不得小于<b>"
                                                  + couponUser.getMinAmount() + "</b>元。");
        }

        // 优惠券使用时间校验
        if (couponUser.getUseStartTime().after(new Date())) {
            return new HttpJsonResult<CouponUser>(
                "优惠券【" + couponUser.getCouponSn() + "】还没有到可使用时间。");
        }
        if (couponUser.getUseEndTime().before(new Date())) {
            return new HttpJsonResult<CouponUser>("优惠券【" + couponUser.getCouponSn() + "】已过期。");
        }

        // 使用渠道校验
        if (couponUser.getChannel().intValue() != ConstantsEJS.CHANNEL_1
            && ConstantsEJS.CHANNEL_3 != couponUser.getChannel().intValue()) {
            String channelStr = couponUser.getChannel().intValue() == ConstantsEJS.CHANNEL_2 ? "电脑端"
                : "移动端";
            return new HttpJsonResult<CouponUser>(
                "优惠券【" + couponUser.getCouponSn() + "】只能在" + channelStr + "使用。");
        }
        jsonResult.setData(couponUser);
        
        return jsonResult;
    }

    // -------------------------限时抢购开始---------------------------------------------------
    /**
     * 限时抢购时跳转到提交订单页面 计算总金额,运费、货品小计，按店铺拆分订单
     * @param request
     * @param response
     * @param map
     * @return
     * @throws IOException 
     */
    @RequestMapping(value = "order/flashsale-{visitPath}.html", method = { RequestMethod.GET })
    public String flashSale(@PathVariable String visitPath, HttpServletRequest request,
                            ModelMap map, HttpServletResponse response) {

        // visitPath为1-1-1形式，第一个为商品ID，第二个为货品ID，第三个为商家ID
        String[] arrVisitPath = visitPath.split("-");
        int arrVisitPathLength = arrVisitPath.length;
        if (arrVisitPathLength != 3) { //长度不等于3url错误
            return "redirect:/error.html";
        }

        String productIdStr = arrVisitPath[0];
        String productGoodsIdStr = arrVisitPath[1];
        String sellerIdStr = arrVisitPath[2];
        Integer productId = ConvertUtil.toInt(productIdStr, 0);
        Integer productGoodsId = ConvertUtil.toInt(productGoodsIdStr, 0);
        Integer sellerId = ConvertUtil.toInt(sellerIdStr, 0);

        String errorUrl = this.toOrderForActivity(request, map, productId, productGoodsId, sellerId,
            null, 1, Orders.ORDER_TYPE_2);
        if (!StringUtil.isEmpty(errorUrl, true)) {
            // 如果出错跳到出错页面
            return errorUrl;
        }

        return "h5v1/order/orderflash";
    }

    /**
     * 限时抢购提交订单
     * @param request
     * @param response
     * @param map
     * @return
     * @throws IOException 
     */
    @RequestMapping(value = "order/ordercommitforflash.html", method = { RequestMethod.POST })
    public @ResponseBody HttpJsonResultForAjax<OrderSuccessVO> orderSubmitForFlash(HttpServletRequest request,
                                                                                   HttpServletResponse response,
                                                                                   OrderCommitVO orderCommitVO,
                                                                                   ModelMap map) {
        // 数量默认为1
        orderCommitVO.setNumber(1);
        return this.commonSubmit(request, orderCommitVO, Orders.ORDER_TYPE_2);
    }

    // -------------------------限时抢购开始---------------------------------------------------

    // -------------------------活动公共开始---------------------------------------------------

    /**
     * 提交订单方法
     * @param request
     * @param orderCommitVO
     * @param orderType
     * @return
     */
    private HttpJsonResultForAjax<OrderSuccessVO> commonSubmit(HttpServletRequest request,
                                                               OrderCommitVO orderCommitVO,
                                                               int orderType) {
        Member member = WebFrontSession.getLoginedUser(request);
        orderCommitVO.setMemberId(member.getId());

        if (orderCommitVO.getInvoiceStatus() == null) {
            // 默认不开发票
            orderCommitVO.setInvoiceStatus(Orders.INVOICE_STATUS_0);
        }
        // 设定IP地址
        orderCommitVO.setIp(WebUtil.getIpAddr(request));
        // 设定来源
        orderCommitVO.setSource(ConstantsEJS.SOURCE_2_H5);
        orderCommitVO.setRemark("");

        // 提交订单
        ServiceResult<OrderSuccessVO> serviceResult = null;
        if (orderType == Orders.ORDER_TYPE_2) {
            // 2、限时抢购订单
            serviceResult = ordersService.orderCommitForFlash(orderCommitVO);
        } else if (orderType == Orders.ORDER_TYPE_8) {
            // 3、团购订单
            serviceResult = ordersService.orderCommitForGroup(orderCommitVO);
        } else if (orderType == Orders.ORDER_TYPE_9) {
            // 4、竞价定金订单
            serviceResult = ordersService.orderCommitForBidding(orderCommitVO);
        }
        HttpJsonResultForAjax<OrderSuccessVO> jsonResult = new HttpJsonResultForAjax<OrderSuccessVO>();
        if (!serviceResult.getSuccess()) {
            jsonResult = new HttpJsonResultForAjax<OrderSuccessVO>(null,
                CsrfTokenManager.getTokenForSession(CsrfTokenManager.getMemkeyFromRequest(request),
                    request.getSession()));
            jsonResult.setMessage(serviceResult.getMessage());
            return jsonResult;
        }

        //订单提交后返回结果
        OrderSuccessVO orderSuccessVO = serviceResult.getResult();
        if (orderSuccessVO.getIsPaid()) {
            // 如果已经付过款，则调用下单送积分方法
            for (Orders order : orderSuccessVO.getOrdersList()) {
                memberService.memberOrderSendValue(member.getId(), member.getName(), order.getId());
            }
        }
        //支付随机码 避免重复提交
        String order_session = CommUtil.randomString(32);
        // 存入session，支付时取出后与参数传入的对比，判断是否二次提交
        request.getSession(false).setAttribute("order_session", order_session);
        request.getSession(false).setAttribute("order_success_vo", orderSuccessVO);
        orderSuccessVO.setPaySessionstr(order_session);

        jsonResult.setData(orderSuccessVO);

        return jsonResult;
    }

    /**
     * 活动时跳转到活动订单结算页
     * @param request
     * @param map
     * @param productId 商品ID
     * @param productGoodsId 货品ID
     * @param sellerId 商家ID
     * @param activityId 活动ID（限时抢购、团购、集合竞价）
     * @param number 购买数量
     * @param orderType 订单类型：Orders.ORDER_TYPE_2、限时抢购订单，Orders.ORDER_TYPE_3、团购订单，Orders.ORDER_TYPE_4、竞价定金订单
     * @return 返回String字符串，如果字符串长度大于0，表示出错跳转到错误提示页面，返回空表示正常执行
     */
    private String toOrderForActivity(HttpServletRequest request, ModelMap map, Integer productId,
                                      Integer productGoodsId, Integer sellerId, Integer activityId,
                                      Integer number, Integer orderType) {
        Member member = WebFrontSession.getLoginedUser(request);

        MemberAddress memberAddress = null;

        Integer addressId = ConvertUtil.toInt(request.getParameter("addressId"), 0);
        if (addressId > 0) {
            ServiceResult<MemberAddress> memberAddressResult = memberAddressService
                .getMemberAddressById(addressId);
            memberAddress = memberAddressResult.getResult();
        }

        if (memberAddress == null) {
            // 收货地址信息
            ServiceResult<List<MemberAddress>> serviceResult = memberAddressService
                .getMemberAddressByMId(member.getId());
            // 获取默认收货地址，如果没有取第一个
            if (serviceResult.getSuccess()) {
                List<MemberAddress> addressList = serviceResult.getResult();
                if (addressList != null && addressList.size() > 0) {
                    memberAddress = addressList.get(0);
                    for (MemberAddress address : addressList) {
                        if (address.getState() == MemberAddress.STATE_1) {
                            memberAddress = address;
                            break;
                        }
                    }
                }
            }
        }

        map.put("address", memberAddress);
        // 构建默认值 ，默认在线支付。收货地址为默认地址，发票默认为不开发票
        OrderCommitVO orderCommitVO = new OrderCommitVO();
        orderCommitVO.setInvoiceType("");
        orderCommitVO.setInvoiceTitle("");
        orderCommitVO.setPaymentName("在线支付");
        orderCommitVO.setPaymentCode(ConstantsEJS.PAYMENT_CODE_ONLINE);
        map.put("orderCommitVO", orderCommitVO);

        map.put("number", number);

        // 取商家信息
        ServiceResult<Seller> sellerRlt = sellerService.getSellerById(sellerId);
        if (!sellerRlt.getSuccess()) {
            map.put("info", sellerRlt.getMessage());
            return "h5v1/commons/error";
        }
        map.put("seller", sellerRlt.getResult());

        // 取商品信息
        ServiceResult<Product> productRlt = productService.getProductById(productId);
        if (!productRlt.getSuccess()) {
            map.put("info", productRlt.getMessage());
            return "h5v1/commons/error";
        }
        map.put("product", productRlt.getResult());

        // 取货品信息
        ServiceResult<ProductGoods> goodRlt = productGoodsService
            .getProductGoodsById(productGoodsId);
        if (!goodRlt.getSuccess()) {
            map.put("info", goodRlt.getMessage());
            return "h5v1/commons/error";
        }
        map.put("productGoods", goodRlt.getResult());

        if (orderType == Orders.ORDER_TYPE_2) {
            // 2、限时抢购订单
            // 根据商品ID、渠道取得当前时间点的该商品参加的限时抢购活动、阶段、活动商品信息（上架的活动，如果有多个，只取最新的一个）
            ServiceResult<ActFlashSale> flashSaleResult = actFlashSaleService
                .getCurrEffectiveActFlashSale(productId, ConstantsEJS.CHANNEL_3);
            if (!flashSaleResult.getSuccess()) {
                map.put("info", flashSaleResult.getMessage());
                return "h5v1/commons/error";
            }

            // 抢购活动信息，不为空则显示，为空则提示当前时间无抢购活动
            ActFlashSale actFlashSale = flashSaleResult.getResult();
            if (actFlashSale != null) {
                map.put("actFlashSale", actFlashSale);
                map.put("actFlashSaleStage", actFlashSale.getStageList().get(0));
                map.put("actFlashSaleProduct",
                    actFlashSale.getStageList().get(0).getProductList().get(0));
            } else {
                map.put("info", "当前没有该商品的整点秒杀活动");
                return "h5v1/commons/error";
            }
        } else if (orderType == Orders.ORDER_TYPE_8) {
            // 3、团购订单
            // 获取团购信息
            ServiceResult<ActGroup> actGroupResult = actGroupService.getActGroupById(activityId);
            if (!actGroupResult.getSuccess()) {
                map.put("info", actGroupResult.getMessage());
                return "h5v1/commons/error";
            }
            ActGroup actGroup = actGroupResult.getResult();
            if (actGroup != null) {
                map.put("actGroup", actGroup);
            } else {
                map.put("info", "团购活动信息获取失败。");
                return "h5v1/commons/error";
            }
        } else if (orderType == Orders.ORDER_TYPE_9) {
            // 4、竞价定金订单
            // 获取集合竞价信息
            ServiceResult<ActBidding> actBiddingResult = actBiddingService
                .getActBiddingById(activityId);
            if (!actBiddingResult.getSuccess()) {
                map.put("info", actBiddingResult.getMessage());
                return "h5v1/commons/error";
            }
            ActBidding actBidding = actBiddingResult.getResult();
            if (actBidding != null) {
                map.put("actBidding", actBidding);
            } else {
                map.put("info", "集合竞价活动信息获取失败。");
                return "h5v1/commons/error";
            }
        }

        // 计算运费
        ServiceResult<BigDecimal> transFeeRlt = sellerTransportService.calculateTransFee(sellerId,
            number, memberAddress.getCityId());
        if (!transFeeRlt.getSuccess()) {
            map.put("info", transFeeRlt.getMessage());
            return "h5v1/commons/error";
        }
        BigDecimal transFee = transFeeRlt.getResult();
        map.put("transFee", transFee.compareTo(BigDecimal.ZERO) < 1 ? BigDecimal.ZERO : transFee);

        // 获取发票信息
        ServiceResult<List<Invoice>> invoiceResult = invoiceService.getInvoiceByMId(member.getId());
        map.put("invoiceList", invoiceResult.getResult());

        //取会员余额信息
        ServiceResult<Member> memberResult = memberService.getMemberById(member.getId());
        if (memberResult.getResult() == null) {
            map.put("info", "会员信息获取失败。");
            return "h5v1/commons/error";
        }
        map.put("member", memberResult.getResult());

        ServiceResult<Config> configById = configservice.getConfigById(ConstantsEJS.CONFIG_ID);
        if (configById.getResult() != null) {
            Config config = configById.getResult();
            if (config.getIntegralScale() > 0) {
                map.put("config", config);
            }
        }

        return "";
    }

    // -------------------------活动公共结束---------------------------------------------------

    // -------------------------团购开始------------------------------------------------------

    /**
     * 团购时跳转到提交订单页面 计算总金额,运费、货品小计，按店铺拆分订单
     * @param request
     * @param response
     * @param map
     * @return
     * @throws IOException 
     */
    @RequestMapping(value = "order/tuan-{visitPath}.html", method = { RequestMethod.GET })
    public String tuan(@PathVariable String visitPath, HttpServletRequest request, ModelMap map,
                       HttpServletResponse response) {

        // visitPath为1-1-1-1-1形式，第一个为商品ID，第二个为货品ID，第三个为商家ID，第四个未团购ID，第五个为购买数量
        String[] arrVisitPath = visitPath.split("-");
        int arrVisitPathLength = arrVisitPath.length;
        if (arrVisitPathLength != 5) { //长度不等于5url错误
            return "redirect:/error.html";
        }

        String productIdStr = arrVisitPath[0];
        String productGoodsIdStr = arrVisitPath[1];
        String sellerIdStr = arrVisitPath[2];
        String actGroupIdStr = arrVisitPath[3];
        String numberStr = arrVisitPath[4];
        Integer productId = ConvertUtil.toInt(productIdStr, 0);
        Integer productGoodsId = ConvertUtil.toInt(productGoodsIdStr, 0);
        Integer sellerId = ConvertUtil.toInt(sellerIdStr, 0);
        Integer actGroupId = ConvertUtil.toInt(actGroupIdStr, 0);
        Integer number = ConvertUtil.toInt(numberStr, 0);

        String errorUrl = this.toOrderForActivity(request, map, productId, productGoodsId, sellerId,
            actGroupId, number, Orders.ORDER_TYPE_8);
        if (!StringUtil.isEmpty(errorUrl, true)) {
            // 如果出错跳到出错页面
            return errorUrl;
        }

        return "h5v1/order/ordergroup";
    }

    /**
     * 团购提交订单
     * @param request
     * @param response
     * @param map
     * @return
     * @throws IOException 
     */
    @RequestMapping(value = "order/ordercommitforgroup.html", method = { RequestMethod.POST })
    public @ResponseBody HttpJsonResultForAjax<OrderSuccessVO> orderSubmitForGroup(HttpServletRequest request,
                                                                                   HttpServletResponse response,
                                                                                   OrderCommitVO orderCommitVO,
                                                                                   ModelMap map) {
        return this.commonSubmit(request, orderCommitVO, Orders.ORDER_TYPE_8);
    }

    // -------------------------团购开始------------------------------------------------------

    // -------------------------集合竞价开始---------------------------------------------------

    /**
     * 集合竞价时跳转到提交订单页面 计算总金额,运费、货品小计，按店铺拆分订单
     * @param request
     * @param response
     * @param map
     * @return
     * @throws IOException 
     */
    @RequestMapping(value = "order/bidding-{visitPath}.html", method = { RequestMethod.GET })
    public String bidding(@PathVariable String visitPath, HttpServletRequest request, ModelMap map,
                          HttpServletResponse response) {

        // visitPath为1-1-1-1-1形式，第一个为商品ID，第二个为货品ID，第三个为商家ID，第四个为集合竞价ID，第五个为购买数量
        String[] arrVisitPath = visitPath.split("-");
        int arrVisitPathLength = arrVisitPath.length;
        if (arrVisitPathLength != 5) { //长度不等于5url错误
            return "redirect:/error.html";
        }

        String productIdStr = arrVisitPath[0];
        String productGoodsIdStr = arrVisitPath[1];
        String sellerIdStr = arrVisitPath[2];
        String actBiddingIdStr = arrVisitPath[3];
        String numberStr = arrVisitPath[4];
        Integer productId = ConvertUtil.toInt(productIdStr, 0);
        Integer productGoodsId = ConvertUtil.toInt(productGoodsIdStr, 0);
        Integer sellerId = ConvertUtil.toInt(sellerIdStr, 0);
        Integer actBiddingId = ConvertUtil.toInt(actBiddingIdStr, 0);
        Integer number = ConvertUtil.toInt(numberStr, 0);

        String errorUrl = this.toOrderForActivity(request, map, productId, productGoodsId, sellerId,
            actBiddingId, number, Orders.ORDER_TYPE_9);
        if (!StringUtil.isEmpty(errorUrl, true)) {
            // 如果出错跳到出错页面
            return errorUrl;
        }

        return "h5v1/order/orderbidding";
    }

    /**
     * 集合竞价提交订单
     * @param request
     * @param response
     * @param map
     * @return
     * @throws IOException 
     */
    @RequestMapping(value = "order/ordercommitforbidding.html", method = { RequestMethod.POST })
    public @ResponseBody HttpJsonResultForAjax<OrderSuccessVO> orderSubmitForBidding(HttpServletRequest request,
                                                                                     HttpServletResponse response,
                                                                                     OrderCommitVO orderCommitVO,
                                                                                     ModelMap map) {
        return this.commonSubmit(request, orderCommitVO, Orders.ORDER_TYPE_9);
    }

    // -------------------------集合竞价结束---------------------------------------------------
}
