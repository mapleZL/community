package com.ejavashop.web.controller.member;

import java.io.IOException;
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
import com.ejavashop.core.JsonUtil;
import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.StringUtil;
import com.ejavashop.entity.member.Member;
import com.ejavashop.entity.operate.CourierCompany;
import com.ejavashop.entity.order.OrderLog;
import com.ejavashop.entity.order.Orders;
import com.ejavashop.model.order.OrdersModel;
import com.ejavashop.service.operate.ICourierCompanyService;
import com.ejavashop.service.order.IOrderLogService;
import com.ejavashop.service.order.IOrdersService;
import com.ejavashop.web.controller.BaseController;
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

    private static Logger          log = LogManager.getLogger(OrdersModel.class);

    @Resource
    private IOrdersService         ordersService;
    @Resource
    private IOrderLogService       orderLogService;
    @Resource
    private ICourierCompanyService courierCompanyService;

    /**
     * 跳转到我的订单列表
     * @param request
     * @param response
     * @param dataMap
     * @return
     * @throws Exception 
     */
    @RequestMapping(value = "order.html", method = { RequestMethod.GET })
    public String toUserCenter(HttpServletRequest request, HttpServletResponse response,
                               Map<String, Object> dataMap, String orderState) {

        Map<String, String> queryMap = new HashMap<String, String>();
        PagerInfo pager = new PagerInfo(ConstantsEJS.DEFAULT_PAGE_SIZE_5, 1);

        Member member = WebFrontSession.getLoginedUser(request);
        queryMap.put("q_memberId", String.valueOf(member.getId()));
        if (!StringUtil.isEmpty(orderState, true)) {
            queryMap.put("q_orderState", String.valueOf(orderState));
        }
        pager = ordersService.countOrderWithOrderProduct(queryMap, pager);
       // 查询订单列表
//        ServiceResult<List<Orders>> serviceResult = ordersService.getOrderWithOrderProduct(queryMap, pager);
//        for (Orders ord : serviceResult.getResult()) {
//        	//借用字段存去商品数量
//        	if(ord.getOrderProductList() ==null){
//        		ord.setRemark("0");
//        	}else{
//        		ord.setRemark(""+ord.getOrderProductList().size());
//        	}
//		}
//        dataMap.put("ordersList", serviceResult.getResult());
        dataMap.put("ordersCount", pager.getRowsCount());
        dataMap.put("pageSize", pager.getPageSize());
        dataMap.put("pageIndex", pager.getPageIndex());
        dataMap.put("orderState", orderState);

        return "h5v1/member/order/orderlist";
    }

    /**
     * ajax加载更多订单
     * @param request
     * @param response
     * @param dataMap
     * @return
     * @throws Exception 
     */
    @RequestMapping(value = "moreorder.html", method = { RequestMethod.GET })
    public String moreOrder(HttpServletRequest request, HttpServletResponse response,
                            Map<String, Object> dataMap, String orderState, Integer pageIndex) {

        Map<String, String> queryMap = new HashMap<String, String>();
        PagerInfo pager = new PagerInfo(ConstantsEJS.DEFAULT_PAGE_SIZE_5, pageIndex);

        Member member = WebFrontSession.getLoginedUser(request);
        queryMap.put("q_memberId", String.valueOf(member.getId()));
        if (!StringUtil.isEmpty(orderState, true)) {
            queryMap.put("q_orderState", String.valueOf(orderState));
        }
        // 查询订单列表
        ServiceResult<List<Orders>> serviceResult = ordersService.getOrderWithOrderProduct(queryMap, pager);
        for (Orders ord : serviceResult.getResult()) {
	    	//借用字段存去商品数量
	    	if (ord.getOrderProductList() == null) {
	    		ord.setRemark("0");
	    	} 
	    	else {
	    		ord.setRemark("" + ord.getOrderProductList().size());
	    	}
        }
        dataMap.put("pageIndex", pageIndex);
        dataMap.put("ordersList", serviceResult.getResult());

        return "h5v1/member/order/orderlistmore";
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
        Orders orders;
        List<OrderLog> logList;
        try {
            // 查询订单列表
            ServiceResult<Orders> serviceResult = ordersService.getOrderWithOPById(id,"front");
            // 查询订单日志
            ServiceResult<List<OrderLog>> orderLogResult = orderLogService.getOrderLogByOrderId(id);

            orders = serviceResult.getResult();
            logList = orderLogResult.getResult();
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

        return "h5v1/member/order/orderdetail";
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
    @RequestMapping(value = "/deleteorder.html", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<Boolean> deleteorder(HttpServletRequest request,
                                                             HttpServletResponse response,
                                                             Map<String, Object> dataMap,
                                                             @RequestParam(value = "orderId", required = true) String orderId) {
        Member member = WebFrontSession.getLoginedUser(request);
        //取消订单
        ServiceResult<Boolean> serviceResult = ordersService.deleteOrder(orderId, member.getId(), member.getName());

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
}
