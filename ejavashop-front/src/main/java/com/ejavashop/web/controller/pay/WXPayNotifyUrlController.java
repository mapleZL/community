package com.ejavashop.web.controller.pay;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.util.Hashtable;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.entity.order.OrdersTradeSerial;
import com.ejavashop.service.order.IOrdersService;
import com.ejavashop.service.order.IOrdersTradeSerialService;
import com.ejavashop.web.controller.BaseController;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.weixin.CommonConstants;
import com.weixin.CommonTools;
import com.weixin.RequestHandler;
import com.weixin.bean.WeChatBuyPost;

@Controller
@RequestMapping(value = "/wx")
public class WXPayNotifyUrlController extends BaseController {
    Logger                            log            = Logger.getLogger(this.getClass());
    @Resource
    private IOrdersService            ordersService;
    @Resource
    private IOrdersTradeSerialService ordersTradeSerialService;

    // 微信返回  fail 失败，success 成功
    private static final String       STATUC_SUCCESS = "SUCCESS";
    private static final String       STATUC_FAIL    = "FAIL";
    private static final String       VALIDATE_FAIL  = "验签失败";

    /**
     * 微信支付成功后通知
     * @param req
     * @param resp
     */
    @RequestMapping(value = "/notify.html", method = RequestMethod.POST)
    public void notify(HttpServletRequest req, HttpServletResponse resp) {
        ServletInputStream in = null;
        try {
            log.debug("通知开始....");
            WeChatBuyPost postData = null;
            // post 过来的xml
            in = req.getInputStream();
            // 转换微信post过来的xml内容
            XStream xs = new XStream(new DomDriver());
            xs.alias("xml", WeChatBuyPost.class);
            String xmlMsg = CommonTools.inputStream2String(in);

            postData = (WeChatBuyPost) xs.fromXML(xmlMsg);

            if (!"SUCCESS".equals(postData.getReturn_code())
                || StringUtils.isEmpty(postData.getOut_trade_no())) {
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

//            String out_trade_no = ots.getRelationOrderSn();

            //更改订单状态
            ServiceResult<Boolean> orderPayResult = ordersService.orderPayAfter(tradesn, totalfee.toString(), "wxpay", "微信支付", postData.getTransaction_id(), xmlMsg);
            if (!orderPayResult.getResult()) {
                log.info("更改订单状态失败,支付失败");
                throw new BusinessException("更改订单状态失败,支付失败");
            }

            sendMsg(resp, STATUC_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            sendMsg(resp, STATUC_FAIL);
        }
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

    /**
     * 通知校验<br>
     * 所有接收到的参数,组成集合做签名
     * @param postData
     */
    private void validateSign(WeChatBuyPost postData) throws Exception {
        SortedMap<String, String> parameters = new TreeMap<String, String>();
        parameters.put("appid", postData.getAppid());
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
        reqHandler.init(postData.getAppid(), CommonConstants.APPSECRET, CommonConstants.KEY);

        String sign = reqHandler.createSign(parameters);
        if (!sign.equals(postData.getSign())) {
            throw new BusinessException(VALIDATE_FAIL);
        } else {
            log.debug("签名成功!");
        }
    }

    /**
     * 生成支付二维码
     * 
     * @param response
     * @return
     * @throws ServletRequestBindingException
     * @throws IOException
     * @throws WriterException
     */
    @RequestMapping(value = "/createTDCode.html")
    public void paySuccess(HttpServletResponse response, HttpServletRequest request)
                                                                                    throws ServletRequestBindingException,
                                                                                    IOException,
                                                                                    WriterException {
        String codeUrl = request.getParameter("codeUrl");
        log.debug("-------" + codeUrl);

        OutputStream os = response.getOutputStream();

        response.reset();
        // 设置相应类型,告诉浏览器输出的内容为图片
        response.setContentType("image/jpg");
        // 设置响应头信息，告诉浏览器不要缓存此内容
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0);

        @SuppressWarnings("rawtypes")
        Hashtable hints = new Hashtable();

        // 内容所使用编码
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        BitMatrix bitMatrix = new MultiFormatWriter().encode(codeUrl, BarcodeFormat.QR_CODE, 300,
            300, hints);
        // 生成二维码
        MatrixToImageWriter.writeToStream(bitMatrix, "jpg", os);
        os.close();
    }

    /**
     * @param url
     * @param param
     * @return
     */
    public static String SendGET(String url, String param) {
        String result = "";// 访问返回结果
        BufferedReader read = null;// 读取访问结果

        try {
            // 创建url
            URL realurl = new URL(url + "?" + param);
            // 打开连接
            URLConnection connection = realurl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立连接
            connection.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            read = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String line;// 循环读取
            while ((line = read.readLine()) != null) {
                result += line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (read != null) {// 关闭流
                try {
                    read.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }

}
