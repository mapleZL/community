package com.ejavashop.web.controller.pay;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.entity.order.OrdersTradeSerial;
import com.ejavashop.service.order.IOrdersService;
import com.ejavashop.service.order.IOrdersTradeSerialService;
import com.ejavashop.web.controller.BaseController;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.wxpay.bean.WeChatBuyPost;
import com.wxpay.util.CommonTools;
import com.wxpay.util.ConfigKit;
import com.wxpay.util.HttpUtil;
import com.wxpay.util.RequestHandler;

/**
 * 微信支付通知结果Controller
 *                       
 * @Filename: WxpayNotifyController.java
 * @Version: 1.0
 * @Author: zhaihl
 * @Email: zhaihl_0@126.com
 *
 */
@Controller
@RequestMapping(value = "/wxpay")
public class WxpayNotifyController extends BaseController {
    // 微信返回  fail 失败，success 成功
    private static final String       STATUC_SUCCESS = "SUCCESS";
    private static final String       STATUC_FAIL    = "FAIL";
    private static final String       VALIDATE_FAIL  = "验签失败";

    @Resource
    private IOrdersService            ordersService;
    @Resource
    private IOrdersTradeSerialService ordersTradeSerialService;

    /**
     * 支付成功后台通知
     * @param request
     * @param response
     */
    @RequestMapping(value = "notify", method = { RequestMethod.GET, RequestMethod.POST })
    public void wxNotify(HttpServletRequest req, HttpServletResponse resp) {
        ServletInputStream in = null;
        try {
            WeChatBuyPost postData = null;
            // post 过来的xml
            in = req.getInputStream();
            // 转换微信post过来的xml内容
            XStream xs = new XStream(new DomDriver());
            xs.alias("xml", WeChatBuyPost.class);
            String xmlMsg = CommonTools.inputStream2String(in);
            postData = (WeChatBuyPost) xs.fromXML(xmlMsg);

            if (!"SUCCESS".equals(postData.getReturn_code())
                || StringUtils.isEmpty(postData.getAttach())) {
                throw new BusinessException(VALIDATE_FAIL);
            }

            //安全校验
            validateSign(postData);

            //微信支付金额单位为分，转换为元
            BigDecimal totalfee = postData.getTotal_fee();
            totalfee = totalfee.divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP);

            String tradesn = postData.getOut_trade_no();

//            OrdersTradeSerial ots = ordersTradeSerialService.getOrdersTradeSerialById(tradesn).getResult();
//            ots.setPaymentState(1);
//            ordersTradeSerialService.updateOrdersTradeSerial(ots);
//
//            String out_trade_no = ots.getRelationOrderSn();

            //更改订单状态
            ServiceResult<Boolean> orderPayResult = ordersService.orderPayAfter(tradesn, totalfee.toString(), "wxpay", "微信支付", postData.getTransaction_id(), xmlMsg);
            if (!orderPayResult.getResult()) {
                log.info("更改订单状态失败,支付失败");
                throw new BusinessException("更改订单状态失败,支付失败");
            }

            String accessToken = (String) req.getServletContext().getAttribute(
                ConstantsEJS.WX_ACCESS_TOKEN);
            // 发送公众号客服消息
            HttpUtil.message.sendText(accessToken, postData.getOpenid(),
                ConfigKit.get("wxpay.paysuccess.msg").replace("ORERID", postData.getOut_trade_no())
                    .replace("SUFFIX", "^_^"));
            sendMsg(resp, STATUC_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            sendMsg(resp, STATUC_FAIL);
        }
    }

    /**
     * 通知校验<br>
     * 所有接收到的参数,组成集合做签名
     * @param postData
     */
    private void validateSign(WeChatBuyPost postData) throws Exception {
        SortedMap<String, String> parameters = new TreeMap<String, String>();
        parameters.put("appid", postData.getAppid());
        parameters.put("attach", postData.getAttach());
        parameters.put("bank_type", postData.getBank_type());
        parameters.put("cash_fee", postData.getCash_fee());
        parameters.put("fee_type", postData.getFee_type());
        parameters.put("is_subscribe", postData.getIs_subscribe());
        parameters.put("mch_id", postData.getMch_id());
        parameters.put("nonce_str", postData.getNonce_str());
        parameters.put("openid", postData.getOpenid());
        parameters.put("out_trade_no", postData.getOut_trade_no());
        parameters.put("result_code", postData.getResult_code());
        parameters.put("return_code", postData.getResult_code());
        parameters.put("time_end", postData.getTime_end() + "");
        parameters.put("total_fee", postData.getTotal_fee() + "");
        parameters.put("trade_type", postData.getTrade_type());
        parameters.put("transaction_id", postData.getTransaction_id());

        RequestHandler reqHandler = new RequestHandler(null, null);
        reqHandler.init(postData.getAppid(), ConfigKit.get("wxpay.app_secret"),
            ConfigKit.get("wxpay.pay_api_key"));

        String sign = reqHandler.createSign(parameters);
        if (!sign.equals(postData.getSign()))
            throw new BusinessException(VALIDATE_FAIL);
    }

    public static void sendMsg(HttpServletResponse resp, String return_code) {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter out = null;
        try {
            out = resp.getWriter();
            out.print(return_code);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    @RequestMapping(value = "test", method = RequestMethod.GET)
    public String test(HttpServletRequest request, HttpServletResponse response, ModelMap dataMap,
                       String res, Integer state) {
        return "h5v1/order/test";
    }

    /**
     * 前台支付成功页面
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "payresult", method = RequestMethod.GET)
    public String paysuccess(HttpServletRequest request, HttpServletResponse response,
                             ModelMap dataMap, String res, Integer state) {
        try {
            res = URLDecoder.decode(res, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        dataMap.put("state", state != null && state == 1 ? "success" : "fail");
        dataMap.put("info", res);
        return "h5v1/order/payresult";
    }

}
