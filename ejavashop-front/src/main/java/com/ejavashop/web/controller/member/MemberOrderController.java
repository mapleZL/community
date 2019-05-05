package com.ejavashop.web.controller.member;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.EjavashopConfig;
import com.ejavashop.core.HttpClientUtil;
import com.ejavashop.core.HttpJsonResult;
import com.ejavashop.core.HttpJsonResultForAjax;
import com.ejavashop.core.JsonUtil;
import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.PaginationUtil;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.StringUtil;
import com.ejavashop.core.WebUtil;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.entity.member.Member;
import com.ejavashop.entity.member.MemberAddress;
import com.ejavashop.entity.member.MemberCredit;
import com.ejavashop.entity.operate.Config;
import com.ejavashop.entity.operate.CourierCompany;
import com.ejavashop.entity.operate.ProductPackage;
import com.ejavashop.entity.order.Invoice;
import com.ejavashop.entity.order.OrderLog;
import com.ejavashop.entity.order.Orders;
import com.ejavashop.model.order.OrdersModel;
import com.ejavashop.service.cart.ICartService;
import com.ejavashop.service.member.IInvoiceService;
import com.ejavashop.service.member.IMemberAddressService;
import com.ejavashop.service.member.IMemberCreditService;
import com.ejavashop.service.member.IMemberService;
import com.ejavashop.service.operate.IConfigService;
import com.ejavashop.service.operate.ICourierCompanyService;
import com.ejavashop.service.operate.IProductPackageService;
import com.ejavashop.service.order.IBookingSendGoodsService;
import com.ejavashop.service.order.IOrderLogService;
import com.ejavashop.service.order.IOrdersService;
import com.ejavashop.vo.order.BookingSendGoodsVO;
import com.ejavashop.vo.order.OrderSuccessVO;
import com.ejavashop.vo.order.SendingGoodsVO;
import com.ejavashop.web.controller.BaseController;
import com.ejavashop.web.csrf.CsrfTokenManager;
import com.ejavashop.web.util.CommUtil;
import com.ejavashop.web.util.WebFrontSession;

/**
 * 会员订单中心controller
 *
 * @Filename: MemberOrderController.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Controller
@RequestMapping(value = "member")
public class MemberOrderController extends BaseController {
    private static Logger            log = LogManager.getLogger(OrdersModel.class);

    @Resource
    private IOrdersService           ordersService;
    @Resource
    private IOrderLogService         orderLogService;
    @Resource
    private ICourierCompanyService   courierCompanyService;
    @Resource
    private IMemberAddressService    memberAddressService;
    @Resource
    private IInvoiceService          invoiceService;
    @Resource
    private IMemberService           memberService;
    @Resource
    private IConfigService           configservice;
    @Resource
    private IMemberCreditService     memberCreditService;
    @Resource
    private ICartService             cartService;
    @Resource(name = "bookingSendGoodsService")
    private IBookingSendGoodsService bookingSendGoodsService;
    @Resource
    private IProductPackageService   productPackageService;

    /**
     * 跳转到我的订单
     * @param request
     * @param response
     * @param dataMap
     * @return
     * @throws Exception 
     */
    @RequestMapping(value = "order.html", method = { RequestMethod.GET })
    public String toUserCenter(HttpServletRequest request, HttpServletResponse response,
                               Map<String, Object> dataMap, String orderState) {

        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);

        pager.setPageSize(ConstantsEJS.DEFAULT_ORDER_PAGE_SIZE);
        Member member = WebFrontSession.getLoginedUser(request);
        queryMap.put("q_memberId", String.valueOf(member.getId()));
        if (!StringUtil.isEmpty(orderState, true)) {
            queryMap.put("q_orderState", String.valueOf(orderState));
        }
        //查询订单列表
        ServiceResult<List<Orders>> serviceResult = ordersService.getOrderWithOrderProduct(
            queryMap, pager);

        String url = request.getRequestURI() + "";
        if (!StringUtil.isEmpty(orderState)) {
            url = url + "?orderState=" + orderState;
        }
        //分页对象
        PaginationUtil pm = new PaginationUtil(pager.getPageSize(), String.valueOf(pager
            .getPageIndex()), pager.getRowsCount(), url);

        //支付随机码,对于未付款的订单
        String order_session = CommUtil.randomString(32);
        request.getSession().setAttribute("order_session", order_session);
        dataMap.put("sessionRandomStr", order_session);

        dataMap.put("ordersList", serviceResult.getResult());
        dataMap.put("page", pm);
        return "front/member/usercenter/ordercenter/orderlist";
    }

    /**
     * 根据不同的筛选条件查询订单
     * 
     */
    @RequestMapping(value = "ordersquery.html", method = { RequestMethod.GET })
    public String getOrderList(HttpServletRequest request, HttpServletResponse response,
                               Map<String, Object> dataMap) {
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        Map<String, String> queryMap1 = new HashMap<>();
        String str = request.getParameter("q_strs");
        String queryMap[] = null;
        try {
            queryMap = str.split(";");
        } catch (Exception e) {
        }
        for (int i = 0; i < str.length(); i++) {
            String param = "";
            try {
                param = queryMap[i];
            } catch (Exception e) {
                param = "";
            }

            if (!"".equals(param) && i == 0) {
                queryMap1.put("q_tradeNo", param);
            }
            if ("".equals(param) && i == 0) {
                queryMap1.put("q_tradeNo", null);
            }
            if (!"".equals(param) && i == 1) {
                if ("1".equals(param)) {
                    queryMap1.put("q_orderType", param);
                } else {
                    queryMap1.put("q_servicePrice", param);
                }
            }
            if ("".equals(param) && i == 1) {
                queryMap1.put("q_orderType", null);
            }
            if (!"".equals(param) && i == 2) {
                queryMap1.put("q_logisticsId", param);
            }
            if ("".equals(param) && i == 2) {
                queryMap1.put("q_logisticsId", null);
            }
            if (!"".equals(param) && i == 3) {
                queryMap1.put("q_paymentCode", param);
            }
            if ("".equals(param) && i == 3) {
                queryMap1.put("q_paymentCode", null);
            }
            if (!"".equals(param) && i == 4 && "2".equals(param)) {
                queryMap1.put("q_moneyDiscount", param);
            }
            if ("".equals(param) && i == 4) {
                queryMap1.put("q_moneyDiscount", null);
            }
            if (!"".equals(param) && i == 5) {
                queryMap1.put("q_startTime", param + " 00:00:00");
            }
            if ("".equals(param) && i == 5) {
                queryMap1.put("q_startTime", null);
            }
            if (!"".equals(param) && i == 6) {
                queryMap1.put("q_endTime", param + " 23:59:59");
            }
            if ("".equals(param) && i == 6) {
                queryMap1.put("q_endTime", null);
            }
        }
        String orderState = null;
        Member member = WebFrontSession.getLoginedUser(request);
        queryMap1.put("q_memberId", String.valueOf(member.getId()));
        //查询订单列表
        ServiceResult<List<Orders>> serviceResult = ordersService.getOrderWithOrderProduct(
            queryMap1, pager);

        String url = request.getRequestURI() + "?q_strs=;;;;;;";
        if (!StringUtil.isEmpty(orderState)) {
            url = url + "?orderState=" + orderState;
        }
        //分页对象
        PaginationUtil pm = new PaginationUtil(pager.getPageSize(), String.valueOf(pager
            .getPageIndex()), pager.getRowsCount(), url);

        //支付随机码,对于未付款的订单
        String order_session = CommUtil.randomString(32);
        request.getSession().setAttribute("order_session", order_session);
        dataMap.put("sessionRandomStr", order_session);

        dataMap.put("ordersList", serviceResult.getResult());
        dataMap.put("page", pm);
        return "front/member/usercenter/ordercenter/orderlist";
    }

    /**
     * 跳转到 订单详情页面 显示物流、付款信息、网单信息
     * @param request
     * @param response
     * @param dataMap
     * @param id
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/orderdetail.html", method = { RequestMethod.GET })
    public String toOrdersDetail(HttpServletRequest request, HttpServletResponse response,
                                 Map<String, Object> dataMap,
                                 @RequestParam(value = "id", required = true) Integer id) {
        String errorMsg = "";
        //套餐
        try {
            Map<String, String> packMap = new HashMap<String, String>();
            packMap.put("state", "1");
            ServiceResult<List<ProductPackage>> packsr = productPackageService.page(packMap, null);
            dataMap.put("productPackage", packsr.getResult());

            // 查询订单列表
            ServiceResult<Orders> serviceResult = ordersService.getOrderWithOPById(id,"front");
            // 查询订单日志
            ServiceResult<List<OrderLog>> orderLogResult = orderLogService.getOrderLogByOrderId(id);

            Orders orders = serviceResult.getResult();
            Integer userid = WebFrontSession.getMemberSession(request).getMember().getId();

            if (orders == null) {
                throw new BusinessException("订单不存在");
            } else if (orders.getMemberId().intValue() != userid.intValue()) {
                throw new BusinessException("您没有权限查看他人订单");
            }
            List<OrderLog> logList = orderLogResult.getResult();
            if (orders != null && orders.getLogisticsId() > 0) {
                // 快递100查询物流信息
                ServiceResult<CourierCompany> courierResult = courierCompanyService
                    .getCourierCompanyById(orders.getLogisticsId());
                CourierCompany courierCompany = courierResult.getResult();
                if (courierCompany != null) {
                    String url = "http://api.kuaidi100.com/api?id=" + EjavashopConfig.KUAIDI100_KEY;
                    url += "&com=" + courierCompany.getCompanyMark();
                    url += "&nu=" + orders.getLogisticsNumber();
                    url += "&show=0";
                    url += "&muti=1";
                    url += "&order=asc";

                    String sendGet = HttpClientUtil.sendGet(url);

                    Map<String, Object> fromJson = JsonUtil.fromJson(sendGet);
                    Object status = fromJson.get("status");
                    // 查询结果状态： 0：物流单暂无结果， 1：查询成功， 2：接口出现异常
                    if (status != null && "1".equals(status.toString())) {
                        List<Map<String, String>> list = (List<Map<String, String>>) fromJson
                            .get("data");
                        for (Map<String, String> map : list) {
                            OrderLog orderLog = new OrderLog();
                            SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat(
                                "yyyy-MM-dd HH:mm:ss");
                            try {
                                orderLog.setCreateTime(simpleDateFormat.parse(map.get("time")));
                                orderLog.setContent(map.get("context"));
                                orderLog.setOperatingName(courierCompany.getCompanyName());
                            } catch (ParseException e) {
                                log.error(e.getMessage(), e);
                            }
                            logList.add(orderLog);
                        }
                    } else {
                        log.error("物流信息查询错误：status=" + status.toString());
                        log.error("物流信息查询错误：message=" + fromJson.get("message"));
                        log.error("物流公司：" + courierCompany.getCompanyName());
                        log.error("物流单号：" + orders.getLogisticsNumber());
                    }

                    Collections.sort(logList, new Comparator<OrderLog>() {
                        public int compare(OrderLog arg0, OrderLog arg1) {
                            return arg0.getCreateTime().compareTo(arg1.getCreateTime());
                        }
                    });
                }
            }

            dataMap.put("orderLogList", logList);
            dataMap.put("order", orders);
        } catch (Exception e) {
            e.printStackTrace();
            errorMsg = e.getMessage();
        }
        dataMap.put("errorMsg", errorMsg);

        return "front/member/usercenter/ordercenter/orderdetail";
    }

    /**
     * 取消订单
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "/cancalorder.html", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<Boolean> cancalOrder(HttpServletRequest request,
                                                             HttpServletResponse response,
                                                             Map<String, Object> dataMap,
                                                             @RequestParam(value = "tradeNo", required = true) String tradeNo) {
        Member member = WebFrontSession.getLoginedUser(request);
        //取消订单
        ServiceResult<Boolean> serviceResult = ordersService.cancelOrder(tradeNo, member.getId(),
            member.getName());

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
     * 删除订单
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    //add by Ls 2017/04/07
    @RequestMapping(value = "/deleteorder.html", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<Boolean> deleteOrder(HttpServletRequest request,
                                                             HttpServletResponse response,
                                                             Map<String, Object> dataMap,
                                                             @RequestParam(value = "orderId", required = true) String orderId) {
        Member member = WebFrontSession.getLoginedUser(request);
        //删除订单,此处删除为逻辑删除 更新状态为10
        ServiceResult<Boolean> serviceResult = ordersService.deleteOrder(orderId, member.getId(),
            member.getName());

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
     * 确认收货
     * @param request
     * @param response
     * @param map
     * @return
     * @throws IOException 
     */
    @RequestMapping(value = "/goodreceive.html", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<Boolean> goodsReceipt(HttpServletRequest request,
                                                              HttpServletResponse response,
                                                              @RequestParam(value = "ordersId", required = true) Integer ordersId) {
        ServiceResult<Boolean> serviceResult = ordersService.goodsReceipt(ordersId);

        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                jsonResult = new HttpJsonResult<Boolean>(serviceResult.getMessage());
            }
        }
        jsonResult.setData(serviceResult.getResult());
        return jsonResult;
    }

    @RequestMapping(value = "/getFee.html", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<BigDecimal> getFee(@RequestParam(required = false, value = "productIds") String productIds,
                                                           @RequestParam(required = false, value = "numstrs") String numstrs,
                                                           @RequestParam(required = false, value = "transportType") String transportType,
                                                           @RequestParam(required = false, value = "memberAddressId") String memberAddressId,
                                                           HttpServletRequest request,
                                                           HttpServletResponse response,
                                                           Map<String, Object> dataMap,
                                                           String orderState) {
        ServiceResult<BigDecimal> serviceResult = ordersService.getTransFee(productIds, numstrs,
            transportType, memberAddressId);
        HttpJsonResult<BigDecimal> jsonResult = new HttpJsonResult<BigDecimal>();
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                jsonResult = new HttpJsonResult<BigDecimal>(serviceResult.getMessage());
            }
        }
        jsonResult.setData(serviceResult.getResult());
        return jsonResult;
    }

    @RequestMapping(value = "/threesend.html", method = { RequestMethod.GET })
    public String threeSend(HttpServletRequest request, HttpServletResponse response,
                            Map<String, Object> dataMap,
                            @RequestParam(value = "ordersId", required = true) Integer ordersId) {
        ServiceResult<BookingSendGoodsVO> userInfoResult = ordersService.listUserInfo(ordersId);
        ServiceResult<List<SendingGoodsVO>> goodsInfoResult = ordersService.listGoodsInfo(ordersId);

        Member member = WebFrontSession.getLoginedUser(request);
        // 收货地址信息
        ServiceResult<List<MemberAddress>> serviceResult = memberAddressService
            .getMemberAddressByMId(member.getId());
        List<MemberAddress> addressList = null;
        MemberAddress defaultAddress = null;
        // 是否有默认地址
        String hasDefaultAdd = "no";
        // 获取默认收货地址，如果没有取第一个
        if (serviceResult.getSuccess()) {
            addressList = serviceResult.getResult();
            if (addressList != null && addressList.size() > 0) {
                defaultAddress = addressList.get(0);
                for (MemberAddress address : addressList) {
                    if (address.getState() == MemberAddress.STATE_1) {
                        defaultAddress = address;
                        hasDefaultAdd = "yes";
                        break;
                    }
                }
            }
        }

        // 获取发票信息
        ServiceResult<List<Invoice>> invoiceResult = invoiceService.getInvoiceByMId(member.getId());
        dataMap.put("invoiceList", invoiceResult.getResult());

        //取会员余额信息
        ServiceResult<Member> memberResult = memberService.getMemberById(member.getId());
        if (memberResult.getResult() == null) {
            dataMap.put("info", "会员信息获取失败。");
            return "front/commons/error";
        }
        dataMap.put("member", memberResult.getResult());
        ServiceResult<MemberCredit> mcresult = memberCreditService.getMemberCreditByMemberId(member
            .getId());
        MemberCredit mc = mcresult.getResult();
        if (!isNull(mc) && mc.getState() == 1) {
            dataMap.put("memberCredit", mc);
        }

        ServiceResult<Config> configById = configservice.getConfigById(ConstantsEJS.CONFIG_ID);
        if (configById.getResult() != null) {
            Config config = configById.getResult();
            if (config.getIntegralScale() > 0) {
                dataMap.put("config", config);
            }
        }
        dataMap.put("hasDefaultAdd", hasDefaultAdd);
        dataMap.put("addressList", addressList);
        dataMap.put("defaultAddress", defaultAddress);
        dataMap.put("userInfo", userInfoResult.getResult());
        dataMap.put("goodsInfo", goodsInfoResult.getResult());
        dataMap.put("ordersId", ordersId);
        return "front/member/usercenter/ordercenter/threesend";
    }

    @RequestMapping(value = "saveThreeSendOrders.html", method = { RequestMethod.GET,
            RequestMethod.POST })
    public @ResponseBody HttpJsonResultForAjax<OrderSuccessVO> saveThreeSendOrders(HttpServletRequest request,
                                                                                   HttpServletResponse response) {
        //String ordersId = request.getParameter("ordersId");
        Map<String, String[]> map = request.getParameterMap();
        String num = request.getParameter("num");
        String productSku = request.getParameter("productSku");
        String orderGoodsId = request.getParameter("orderGoodsId");
        String transportType = request.getParameter("transportType");
        //从session中拿用户
        Member member = WebFrontSession.getLoginedUser(request);
        ServiceResult<OrderSuccessVO> serviceResult = bookingSendGoodsService.saveThreeSendOrders(
            map, member, num, productSku, orderGoodsId, transportType);
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
        //支付随机码 避免重复提交
        String order_session = CommUtil.randomString(32);
        // 存入session，支付时取出后与参数传入的对比，判断是否二次提交
        request.getSession(false).setAttribute("order_session", order_session);
        request.getSession(false).setAttribute("order_success_vo", orderSuccessVO);
        orderSuccessVO.setPaySessionstr(order_session);
        jsonResult.setData(orderSuccessVO);
        return jsonResult;
    }

}
