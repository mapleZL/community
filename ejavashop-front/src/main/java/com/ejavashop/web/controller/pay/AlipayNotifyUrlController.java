package com.ejavashop.web.controller.pay;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alipay.util.AlipayNotify;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.order.OrdersTradeSerial;
import com.ejavashop.service.order.IOrdersService;
import com.ejavashop.service.order.IOrdersTradeSerialService;
import com.ejavashop.web.controller.BaseController;

@Controller
public class AlipayNotifyUrlController extends BaseController {
    @Resource
    private IOrdersService            ordersService;
    @Resource
    private IOrdersTradeSerialService ordersTradeSerialService;

    @RequestMapping(value = "/alipay_result_notify.html", method = RequestMethod.POST)
    //Terry Test
//    @RequestMapping(value = "/alipay_result_notify.html", method = {RequestMethod.POST, RequestMethod.GET})
    public void alipay_result_notify(HttpServletRequest request, HttpServletResponse response,
                                     Map<String, Object> stack) {
        try {
            //获取支付宝POST过来反馈信息
            Map<String, String> params = new HashMap<String, String>();
            Map requestParams = request.getParameterMap();
            for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
                String name = (String) iter.next();
                String[] values = (String[]) requestParams.get(name);
                String valueStr = "";
                for (int i = 0; i < values.length; i++) {
                    valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr
                                                                                 + values[i] + ",";
                }
                //乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
                //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
                params.put(name, valueStr);
            }

            //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
            //商户订单号
            //            System.out.println("-------------------");
            //            System.out.println(params);

            //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
            //商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"), "UTF-8");

//            OrdersTradeSerial ots = ordersTradeSerialService.getOrdersTradeSerialById(out_trade_no).getResult();
////            String out_trade_no = ots.getRelationOrderSn();
//            ots.setPaymentState(1);
//            ordersTradeSerialService.updateOrdersTradeSerial(ots);

            //            System.out.println("---out_trade_no---" + out_trade_no);

            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),
                "UTF-8");
            //            System.out.println("---trade_no---" + trade_no);

            //交易状态
            String trade_status = new String(request.getParameter("trade_status").getBytes(
                "ISO-8859-1"), "UTF-8");
            //            System.out.println("---trade_status---" + trade_status);

            //交易金额
            String total_fee = new String(request.getParameter("total_fee").getBytes("ISO-8859-1"),
                "UTF-8");
            //            System.out.println("---total_fee---" + total_fee);
            //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//

            if (AlipayNotify.verify(params)) {//验证成功
            //Terry Test
//            if (true) {
                //////////////////////////////////////////////////////////////////////////////////////////
                //请在这里加上商户的业务逻辑程序代码

                //——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
                if (trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")) {
                    //判断该笔订单是否在商户网站中已经做过处理
                    //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                    //请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
                    //如果有做过处理，不执行商户的业务程序
                    ServiceResult<Boolean> orderPayResult = ordersService.orderPayAfter(out_trade_no, total_fee, "pcalipay", "支付宝支付", trade_no, params.toString());
                    if (!orderPayResult.getSuccess()) {
                        response.getWriter().println("fail");
                    } else {
                        response.getWriter().println("success"); //请不要修改或删除
                    }
                    //注意：
                    // TRADE_SUCCESS 付款完成后，支付宝系统发送该交易状态通知
                    //注意：
                    //TRADE_FINISHED 退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
                }

                //——请根据您的业务逻辑来编写程序（以上代码仅作参考）——

                //////////////////////////////////////////////////////////////////////////////////////////
            } else {//验证失败
                response.getWriter().println("fail");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
