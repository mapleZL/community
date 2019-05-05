package com.ejavashop.web.controller.pay;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ejavashop.core.EjavashopConfig;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.SignUtil;
import com.ejavashop.entity.order.OrdersTradeSerial;
import com.ejavashop.service.order.IOrdersService;
import com.ejavashop.service.order.IOrdersTradeSerialService;
import com.ejavashop.web.controller.BaseController;

@Controller
public class ChinapayNotifyUrlController extends BaseController {

    @Resource
    private IOrdersService            ordersService;
    private static final String       VERIFY_KEY = "VERIFY_KEY";
    @Resource
    private IOrdersTradeSerialService ordersTradeSerialService;

    @RequestMapping(value = "/chinapay_pgReturn.html", method = RequestMethod.POST)
    public String chinapay_pgReturn(HttpServletRequest request, HttpServletResponse response) {
        try {
            System.out.println("BackRcvResponse接收后台通知开始");

            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("utf-8");
            //解析 返回报文
            Enumeration<String> requestNames = request.getParameterNames();
            Map<String, String> resultMap = new HashMap<String, String>();
            while (requestNames.hasMoreElements()) {
                String name = requestNames.nextElement();
                String value = request.getParameter(name);
                //              value = URLDecoder.decode(value, "UTF-8");
                resultMap.put(name, value);
            }

            //验证签名
            if (SignUtil.verify(resultMap)) {
                String accNo = resultMap.get("OrderStatus"); //获取交易卡号

                if (EjavashopConfig.CHINAPAY_SUCCESS_STATUS.equals(accNo)) {
//                    String orderId = resultMap.get("MerOrderNo");
//                    String settleAmt = resultMap.get("OrderAmt"); //获取金额，单位为分的字符串。
//                    BigDecimal settleAmtB = new BigDecimal(settleAmt);
//                    settleAmtB = settleAmtB.divide(new BigDecimal(100));
////
//                    ordersService.orderPayAfter(orderId, settleAmtB.toString(), "chinapay", "银联支付", accNo, resultMap.toString());
    //                    if (orderPayResult.getResult())
                    return "front/order/linepay";
                }

            } else {
                resultMap.put(VERIFY_KEY, "fail");
            }
        } catch (Exception e) {
            log.error("chinapay异步接口出现异常" + e);
        }
        return "front/order/payError";
    }

    @RequestMapping(value = "/chinapay_bgReturn.html", method = RequestMethod.POST)
    //Terry Test
//    @RequestMapping(value = "/chinapay_bgReturn.html", method = {RequestMethod.POST, RequestMethod.GET})
    public void chinapay_bgReturn(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("utf-8");
            //解析 返回报文
            Enumeration<String> requestNames = request.getParameterNames();
            Map<String, String> resultMap = new HashMap<String, String>();
            while (requestNames.hasMoreElements()) {
                String name = requestNames.nextElement();
                String value = request.getParameter(name);
                value = URLDecoder.decode(value, "UTF-8");
                resultMap.put(name, value);
            }

            //验证签名
            if (SignUtil.verify(resultMap)) {
            //Terry Test
//            if (true) {
                String tradesn = resultMap.get("MerOrderNo");

//                OrdersTradeSerial ots = ordersTradeSerialService.getOrdersTradeSerialById(tradesn).getResult();
//                ots.setPaymentState(1);
//                ordersTradeSerialService.updateOrdersTradeSerial(ots);

//                String orderId = ots.getRelationOrderSn();

                String settleAmt = resultMap.get("OrderAmt"); //获取金额，单位为分的字符串。
                String accNo = resultMap.get("OrderStatus"); //获取交易卡号

                if (EjavashopConfig.CHINAPAY_SUCCESS_STATUS.equals(accNo)) {
                    BigDecimal settleAmtB = new BigDecimal(settleAmt);
                    settleAmtB = settleAmtB.divide(new BigDecimal(100));

                    ServiceResult<Boolean> orderPayResult = ordersService.orderPayAfter(tradesn, settleAmtB.toString(), "chinapay", "银联支付", accNo, resultMap.toString());
                    if (orderPayResult.getResult())
                        response.getWriter().write("success");
                }

            } else {
                response.getWriter().write("fail");
            }

        } catch (Exception e) {
            log.error("chinapay异步接口出现异常" + e);
            try {
                response.getWriter().write("fail");
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
    }

    /**
     * 获取请求参数中所有的信息
     * 
     * @param request
     * @return
     */
    public static Map<String, String> getAllRequestParam(final HttpServletRequest request) {
        Map<String, String> res = new HashMap<String, String>();
        Enumeration<?> temp = request.getParameterNames();
        if (null != temp) {
            while (temp.hasMoreElements()) {
                String en = (String) temp.nextElement();
                String value = request.getParameter(en);
                res.put(en, value);
                //在报文上送时，如果字段的值为空，则不上送<下面的处理为在获取所有参数数据时，判断若值为空，则删除这个字段>
                //System.out.println("ServletUtil类247行  temp数据的键=="+en+"     值==="+value);
                if (null == res.get(en) || "".equals(res.get(en))) {
                    res.remove(en);
                }
            }
        }
        return res;
    }

}
