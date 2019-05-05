package com.ejavashop.web.controller.pay;

import java.io.PrintWriter;
import java.math.BigDecimal;
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
import com.ejavashop.service.order.IOrdersService;
import com.ejavashop.web.controller.BaseController;

import payment.api.notice.Notice1318Request;
import payment.api.notice.NoticeRequest;
import payment.api.notice.NoticeResponse;
import payment.api.system.PaymentEnvironment;
import payment.tools.util.Base64;

@Controller
public class CfcaNotifyUrlController extends BaseController {

    @Resource
    private IOrdersService ordersService;

    @RequestMapping(value = "/cfca_pgReturn.html", method = RequestMethod.POST)
    public String chinapay_pgReturn(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("utf-8");
            
            // 获得参数message和signature
            String message = request.getParameter("message");
            String signature = request.getParameter("signature");
            // 定义变量
            String txName = "";

            // 生成交易结果对象
            NoticeRequest noticeRequest = new NoticeRequest(message, signature);
            
            //验证签名
            if ("1318".equals(noticeRequest.getTxCode())) {
                Notice1318Request nr = new Notice1318Request(noticeRequest.getDocument());
                String accNo = String.valueOf(nr.getStatus()); 
                
                if ("20".equals(accNo)) {
//                    BigDecimal settleAmtB = new BigDecimal(settleAmt);
//                    settleAmtB = settleAmtB.divide(new BigDecimal(100));
//
//                    ServiceResult<Boolean> orderPayResult = ordersService.orderPayAfter(orderId, settleAmtB.toString(), "cfcaapay", "中金支付", accNo, nr.toString());
//                    if (orderPayResult.getResult())
                    return "front/order/linepay";
                }
                
            }else{
                return "front/order/payError";
            }
        } catch (Exception e) {
            log.error("cfcapay异步接口出现异常" + e);
        }
        return "front/order/payError";
    }

    @RequestMapping(value = "/cfca_bgReturn.html", method = RequestMethod.POST)
    public String chinapay_bgReturn(HttpServletRequest request, HttpServletResponse response) {
        try {
            System.out.println("BackRcvResponse接收后台通知开始");

            request.setCharacterEncoding("utf-8");
            response.setCharacterEncoding("utf-8");
            
            // 获得参数message和signature
            String message = request.getParameter("message");
            String signature = request.getParameter("signature");

            // 生成交易结果对象
            if (!EjavashopConfig.CFCA_ISSIGN) {
                PaymentEnvironment.initialize(EjavashopConfig.CFCA_CONFIG_PATH);
                EjavashopConfig.CFCA_ISSIGN = true;
            }
            NoticeRequest noticeRequest = new NoticeRequest(message, signature);
            
            //验证签名
            if ("1318".equals(noticeRequest.getTxCode())) {
                Notice1318Request nr = new Notice1318Request(noticeRequest.getDocument());
                // ！！！ 在这里添加商户处理逻辑！！！
                // 以下为演示代码
                String orderId = nr.getPaymentNo();
                String settleAmt = String.valueOf(nr.getAmount()); //获取金额，单位为分的字符串。
                String accNo = String.valueOf(nr.getStatus()); 
                
                if ("20".equals(accNo)) {
                    BigDecimal settleAmtB = new BigDecimal(settleAmt);
                    settleAmtB = settleAmtB.divide(new BigDecimal(100));
                    ServiceResult<Boolean> orderPayResult = ordersService.orderPayAfter(orderId.substring(6), settleAmtB.toString(), "cfcaapay", "中金支付", orderId, nr.toString());
                    if (orderPayResult.getResult()) {
                        PrintWriter out = response.getWriter();
                        String xmlString = "";
                        xmlString = new NoticeResponse().getMessage();

                        String base64String = new String(Base64.encode(xmlString.getBytes("UTF-8")));

                        out.print(base64String);
                        out.flush();
                        out.close();
                    }
                }
                
            }else{
                return "front/order/payError";
            }
        } catch (Exception e) {
            log.error("cfcapay同步接口出现异常" + e);
        }
        return "front/order/payError";
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
