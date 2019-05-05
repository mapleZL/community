package com.ejavashop.web.controller.pay;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ejavashop.service.order.IOrdersService;
import com.ejavashop.web.controller.BaseController;

import payment.api.notice.Notice1318Request;
import payment.api.notice.NoticeRequest;

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
                    return "h5v1/order/linepay";
                }
                
            }else{
                return "h5v1/order/payError";
            }
        } catch (Exception e) {
            log.error("cfcapay异步接口出现异常" + e);
        }
        return "h5v1/order/payError";
    }
}

