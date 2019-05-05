package com.ejavashop.web.controller.pay;

import java.math.BigDecimal;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.crypto.Cipher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import payment.api.system.PaymentEnvironment;
import payment.api.tx.marketorder.Tx1312Request;

import com.alipay.config.AlipayConfig;
import com.alipay.util.AlipaySubmit;
import com.bestpay.util.AESUtil;
import com.bestpay.util.Base64Utils;
import com.bestpay.util.BestPayConfig;
import com.bestpay.util.RsaCipher;
import com.ejavashop.core.EjavashopConfig;
import com.ejavashop.core.RandomUtil;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.SignUtil;
import com.ejavashop.core.StringUtil;
import com.ejavashop.core.TimeUtil;
import com.ejavashop.core.WebUtil;
import com.ejavashop.core.freemarkerutil.DomainUrlUtil;
import com.ejavashop.entity.member.Member;
import com.ejavashop.entity.order.BookingSendGoods;
import com.ejavashop.entity.order.Orders;
import com.ejavashop.entity.order.OrdersTradeSerial;
import com.ejavashop.entity.order.TerraceSelfCoupon;
import com.ejavashop.model.order.CalculateUtil;
import com.ejavashop.service.order.IBookingSendGoodsService;
import com.ejavashop.service.order.IOrdersService;
import com.ejavashop.service.order.IOrdersTradeSerialService;
import com.ejavashop.service.order.ITerraceSelfCouponService;
import com.ejavashop.vo.order.OrderSuccessVO;
import com.ejavashop.web.controller.BaseController;
import com.ejavashop.web.util.WebFrontSession;
import com.unionpay.acp.SDKConfig;
import com.unionpay.acp.SDKUtil;
import com.weixin.CommonConstants;
import com.weixin.GetWxOrderno;
import com.weixin.MD5Util;

@Controller
public class PayIndexController extends BaseController {

    @Resource
    private IOrdersService            ordersService;
    @Resource
    private IOrdersTradeSerialService ordersTradeSerialService;
    @Resource
    private IBookingSendGoodsService  bookingSendGoodsService;
    @Resource
    private ITerraceSelfCouponService terraceSelfCouponService;
    
    @RequestMapping(value = "/payindex.html", method = RequestMethod.GET)
    public String payindex(HttpServletRequest request, HttpServletResponse response,
                           Map<String, Object> dataMap) {

        String optionsRadios = request.getParameter("optionsRadios");

        if (StringUtil.isEmpty(optionsRadios, true)) {
            dataMap.put("info", "请选择要支付的订单，谢谢！");
            return "front/commons/error";
        }
        Member member = WebFrontSession.getLoginedUser(request);
        if (member == null) {
            //            dataMap.put("info", "用户Session过期，请重新登录");
            dataMap.put("info", "亲爱的用户，由于您长时间未操作，登陆已超时，请重新登录");
            return "front/commons/error";
        }

        //需要支付的总金额
        BigDecimal payOrderAllsVO = new BigDecimal(0);
        //订单号
        StringBuilder orderPaySn = null;
        String payType = request.getParameter("payType");

        // 清除支付标志
        request.getSession().removeAttribute("order_session");

//        String paySessionstr = request.getParameter("paySessionstr");
        String fromType = request.getParameter("fromType");
        String selectOrderBalance = request.getParameter("selectOrderBalance");
        String balancePassword = request.getParameter("balancePassword");
        boolean isBalancePay = "on".equals(selectOrderBalance) ? true : false;
        //关联订单号
        String relationOrderSn = request.getParameter("relationOrderSn");
        /*
        String order_session = CommUtil.null2String(request.getSession(false).getAttribute("order_session"));
        if (!order_session.equals(paySessionstr)) {
            dataMap.put("info", "请稍后再试，谢谢！");
            return "front/commons/error";
        }*/
        OrderSuccessVO orderSuccessVO1 = null;
        //如果是用户中心跳转，直接使用子订单号
        String tradesn = relationOrderSn;

        // 如果是从下单结算也跳转过来且勾选了使用余额，则支付密码从session中取
        if ("1".equals(fromType)) {
            orderSuccessVO1 = (OrderSuccessVO) request.getSession(false).getAttribute("order_success_vo");
            request.getSession().removeAttribute("order_success_vo");
            if (orderSuccessVO1 == null) {
                dataMap.put("info", "请到订单中心支付订单，谢谢！");
                return "front/commons/error";
            }
            // 如果来源是下单后直接跳转，密码取session中保存的值
            if (isBalancePay && !StringUtil.isEmpty(orderSuccessVO1.getBalancePwd(), true)) {
                balancePassword = orderSuccessVO1.getBalancePwd();
            }
        }

        //三方仓储发货订单支付
        if (payType != null && "threesend".equals(payType)) {
            BookingSendGoods bookingSendGoods = orderSuccessVO1.getBookList().get(0);
            if (bookingSendGoods == null) {
                dataMap.put("info", "请到订单中心支付订单，谢谢！");
                return "front/commons/error";
            } else {
                payOrderAllsVO = bookingSendGoods.getMoneyPaidReality();
                orderPaySn = new StringBuilder(bookingSendGoods.getBookOrdersSn());
                //修改订单状态
                bookingSendGoods.setPayStatus(1);
                bookingSendGoods.setPayTime(new Date());
                bookingSendGoods.setStatus(1);
                bookingSendGoodsService.updateBookingSendGoods(bookingSendGoods);
            }
        } else {
            //        if (!order_session.equals(paySessionstr)) {
            //            dataMap.put("info", "请稍后再试，谢谢！");
            //            return "front/commons/error";
            //        }

            // 调用订单支付接口
            ServiceResult<OrderSuccessVO> orderPayResult = ordersService.orderPayBefore(relationOrderSn, isBalancePay, balancePassword, member);
            if (!orderPayResult.getSuccess()) {
                dataMap.put("info", orderPayResult.getMessage());
                return "front/commons/error";
            }

            OrderSuccessVO orderSuccessVO = orderPayResult.getResult();

            orderPaySn = new StringBuilder(String.valueOf((new Date()).getTime()));//拼装订单号（ejavashop可以根据自己的业务逻辑修改），已ejavashop加上订单ID，第二位是否用余额支付，0是含余额支付，1没有用余额

            //---------订单号处理 bg--------//
            StringBuilder ordersn = new StringBuilder();
            List<Orders> ordersList = orderSuccessVO.getOrdersList();
            for (Orders orders : ordersList) {
                ordersn.append(EjavashopConfig.ORDER_SEPARATOR);
                ordersn.append(orders.getId());
            }

            //用户支付订单号，即总订单号，支付成功后再拆单
            OrdersTradeSerial ots = null;
            if ("2".equals(fromType)) {
                tradesn = relationOrderSn;
            } else {
                tradesn = RandomUtil.getOrderSn();
            }
            ots = ordersTradeSerialService.existsByOrders(ordersn.toString()).getResult();
            BigDecimal reduceCoupon = BigDecimal.ZERO;
            ServiceResult<TerraceSelfCoupon> terResult = terraceSelfCouponService.getTerraceSelfCouponByTradeNo(relationOrderSn);
            if(terResult.getSuccess() && terResult.getResult() != null){
                TerraceSelfCoupon reCoupon = terResult.getResult();
                reduceCoupon = reCoupon.getCouponValue();
            }
            if (orderSuccessVO.getBanlancePayVO() == OrderSuccessVO.BANLANCEPAYVO_2) { //余额支付够付款，扣除余额，更改订单状态，跳转到支付成功页面
                log.debug("余额支付，更新记录");
                if (ots == null) {
                    //订单列表跳转，余额状态无需判断
                    StringBuffer sb = new StringBuffer(orderPaySn);
                    sb.append(EjavashopConfig.ORDER_SEPARATOR);
                    sb.append("0");
                    sb.append(ordersn);

                    ots = new OrdersTradeSerial();
                    ots.setTradeSn(tradesn);
                    ots.setPaymentState(1);
                    ots.setRelationOrderSn(sb.toString());
                    ordersTradeSerialService.saveOrdersTradeSerial(ots);
                } else {
                    //存在则更新
                    ots.setPaymentState(1);
                    ordersTradeSerialService.updateOrdersTradeSerial(ots);
                }
                return "front/order/linepay";
            } else if (orderSuccessVO.getBanlancePayVO() == OrderSuccessVO.BANLANCEPAYVO_3) {
                payOrderAllsVO = orderSuccessVO.getPayOrderAllsVO()
                    .subtract(orderSuccessVO.getBanlancePayMoneyVO());
                orderPaySn.append(EjavashopConfig.ORDER_SEPARATOR);
                orderPaySn.append("0");
            } else {
                payOrderAllsVO = orderSuccessVO.getPayOrderAllsVO();
                orderPaySn.append(EjavashopConfig.ORDER_SEPARATOR);
                orderPaySn.append("1");
            }
            //减去全场红包
            payOrderAllsVO = payOrderAllsVO.subtract(reduceCoupon);
            for (Orders orders : ordersList) {
                orderPaySn.append(EjavashopConfig.ORDER_SEPARATOR);
                orderPaySn.append(orders.getId());
            }
//            System.out.println("payOrderAllsVO:" + payOrderAllsVO);
//            System.out.println("orderPaySn:" + orderPaySn.toString());

            if (ots == null) {
                log.debug("不存在映射，生成新记录");
                //如果不存在映射关系，生成新记录
                ots = new OrdersTradeSerial();
                ots.setTradeSn(tradesn);
                ots.setPaymentState(0);
                ots.setRelationOrderSn(orderPaySn.toString());
                ordersTradeSerialService.saveOrdersTradeSerial(ots);
                //Terry 20160920
                for (Orders orders : ordersList) {
                    Orders ordersNew = new Orders();
                    ordersNew.setId(orders.getId());
                    ordersNew.setTradeNo(tradesn);
                    // 1.更新订单
                    ordersService.updateOrders(ordersNew);
                }
                
            } else {
                tradesn = ots.getTradeSn();             //Terry 20160920 if tradesn is exsit, then set tradsn = ots.getTradeSn()
            }
        }

        //Terry for Test
        //        if (request.getRemoteAddr().equals("127.0.0.1"))
        //            payOrderAllsVO = new BigDecimal(0.01);
        DecimalFormat df = new DecimalFormat("0.00");
        String total_fee = df.format(payOrderAllsVO);

        if ("alipay".equals(optionsRadios)) {//支付宝付款
            try {
                //支付类型
                String payment_type = "1";
                //必填，不能修改
                //服务器异步通知页面路径
                String notify_url = DomainUrlUtil.getEJS_URL_RESOURCES()
                                    + "/alipay_result_notify.html";
                //需http://格式的完整路径，不能加?id=123这类自定义参数

                //页面跳转同步通知页面路径
                String return_url = DomainUrlUtil.getEJS_URL_RESOURCES() + "/alipay_result.html";
                //需http://格式的完整路径，不能加?id=123这类自定义参数，不能写成http://localhost/

                //商户订单号
                String out_trade_no = tradesn;
                //                String out_trade_no = new Date().getTime() + "";
                //商户网站订单系统中唯一订单号，必填

                //订单名称
                String subject = EjavashopConfig.ALIPAY_ALL_SUBJECT;
                //必填

                //TODO 正式修改金额 付款金额
                //必填

                //订单描述
                String body = "";
                //商品展示地址
                String show_url = DomainUrlUtil.EJS_URL_RESOURCES;
                //需以http://开头的完整路径，例如：http://www.商户网址.com/myorder.html

                //防钓鱼时间戳
                String anti_phishing_key = "";
                //若要使用请调用类文件submit中的query_timestamp函数

                //客户端的IP地址
                String exter_invoke_ip = WebUtil.getIpAddr(request);

                //把请求参数打包成数组
                Map<String, String> sParaTemp = new HashMap<String, String>();
                sParaTemp.put("service", "create_direct_pay_by_user");
                sParaTemp.put("partner", AlipayConfig.partner);
                sParaTemp.put("seller_email", AlipayConfig.seller_email);
                sParaTemp.put("_input_charset", AlipayConfig.input_charset);
                sParaTemp.put("payment_type", payment_type);
                sParaTemp.put("notify_url", notify_url);
                sParaTemp.put("return_url", return_url);
                sParaTemp.put("out_trade_no", out_trade_no);
                sParaTemp.put("subject", subject);
                sParaTemp.put("total_fee", total_fee);
                sParaTemp.put("body", body);
                sParaTemp.put("show_url", show_url);
                sParaTemp.put("anti_phishing_key", anti_phishing_key);
                sParaTemp.put("exter_invoke_ip", exter_invoke_ip);

                //建立请求
                String sHtmlText = AlipaySubmit.buildRequest(sParaTemp, "get", "确认");
                //                response.getWriter().println(sHtmlText);
                dataMap.put("paytext", sHtmlText);
                return "front/order/alipay";
            } catch (Exception e) {
                log.error("alipay支付出现异常" + e);
            }
        } else if ("chinapay".equals(optionsRadios)) {
            try {
                Map<String, String> requestData = new HashMap<String, String>();
                String txnAmt = total_fee.replace(".", "");//total_fee.split("\\.")[0]; //付款金额，单位为分，不能有小数点，去掉

                requestData.put("MerId", EjavashopConfig.CHINAPAY_MERID);// 商户号码，在收付捷平台中开通的商户编号
                requestData.put("MerOrderNo", tradesn); //必填，变长 32位，同一商户同一交易日期内不可重复
                requestData.put("OrderAmt", txnAmt); //整数，货币种类为人民币，以分为单位
                requestData.put("TranDate", TimeUtil.getNoFormatToday()); //商户提交交易的日期，例如交易日期为2015年1月2日，则值为20150102
                requestData.put("TranTime", TimeUtil.getNoFormatTime()); //商户提交交易的时间，例如交易时间10点11分22秒，则值为101122
                requestData.put("BusiType", EjavashopConfig.CHINAPAY_BUSITYPE); //固定值：0001
                requestData.put("Version", EjavashopConfig.CHINAPAY_VERSION); //商户提交交易的时间，例如交易时间10点11分22秒，则值为101122

                requestData.put("MerPageUrl",
                    DomainUrlUtil.getEJS_URL_RESOURCES() + "/chinapay_pgReturn.html");//页面接受应答地址，用于引导使用者支付后返回商户网站页面
                requestData.put("MerBgUrl",
                    DomainUrlUtil.getEJS_URL_RESOURCES() + "/chinapay_bgReturn.html"); //商户后台交易应答接收地址，ChinaPay会根据后台商户响应来判定是否重新发送后台应答流水，以确保后台应答的接收

                /*
                 * 前台页面方式提交交易：商户可以不填此域，ChinaPay会根据商户交易配置在持卡人页面显示商户开通的交易类型，由持卡人选择商户已开通的交易类型完成支付
                    后台方式提交交易或支付机构号域有值，此域必填
                    此接口TranType填值范围：
                    0001个人网银支付
                    0002企业网银支付
                    0003授信交易
                    0004快捷支付
                    0005账单支付
                    0006认证支付
                    0007分期付款
                    0201预授权交易
                 */
                requestData.put("TranType", "0001");
                //                requestData.put("AcqCode", "000000000000014");
                //                requestData.put("AccessType", "0");         
                //                requestData.put("MerResv", "MerResv");

                String signature = SignUtil.sign(requestData);
                requestData.put("Signature", signature);

                String requestFrontUrl = EjavashopConfig.CHINAPAY_PAY_URL; //获取请求银联的前台地址：对应属性文件acp_sdk.properties文件中的acpsdk.frontTransUrl
//                System.out.println("requestFrontUrl:" + requestFrontUrl);
                String html = SDKUtil.createAutoFormHtml(requestFrontUrl, requestData,
                    SDKUtil.encoding_UTF8); //生成自动跳转的Html表单

                //                System.out.println("打印请求HTML，此为请求报文，为联调排查问题的依据：" + html);
                //将生成的html写到浏览器中完成自动跳转打开银联支付页面；这里调用signData之后，将html写到浏览器跳转到银联页面之前均不能对html中的表单项的名称和值进行修改，如果修改会导致验签不通过
                //                response.getWriter().write(html);
                dataMap.put("paytext", html);
                return "front/order/alipay";
            } catch (Exception e) {
                log.error("chinapay支付出现异常" + e);
            }
        } else if ("cfcapay".equals(optionsRadios)) {
            try {
                String txnAmt = total_fee.replace(".", "");//total_fee.split("\\.")[0]; //付款金额，单位为分，不能有小数点，去掉

                if (!EjavashopConfig.CFCA_ISSIGN) {
                    PaymentEnvironment.initialize(EjavashopConfig.CFCA_CONFIG_PATH);
                    EjavashopConfig.CFCA_ISSIGN = true;
                }
                // 2.创建交易请求对象
                Tx1312Request tx1312Request = new Tx1312Request();
                tx1312Request.setInstitutionID(EjavashopConfig.CFCA_MERID); //机构号码
                tx1312Request.setOrderNo(tradesn); //订单号
                tx1312Request.setPaymentNo(TimeUtil.getNoFormatTime() + tradesn); //支付流水号
                tx1312Request.setAmount(Long.parseLong(txnAmt)); //订单金额
                //            tx1312Request.setPayerID(payerID);                                    //付款者ID
                //            tx1312Request.setPayerName(payerName);                                //付款者名称
                //            tx1312Request.setUsage(usage);                                        //资金用途
                tx1312Request.setRemark(EjavashopConfig.ALIPAY_ALL_SUBJECT); //订单描述
                //            tx1312Request.setNote(note);                                          //用户自定义信息
                tx1312Request.setNotificationURL(
                    DomainUrlUtil.getEJS_URL_RESOURCES() + "/cfca_pgReturn.html"); //通知URL

                //收款人
                /*if (null != payees && payees.length() > 0) {
                    String[] payeeList = payees.split(";");
                    for (int i = 0; i < payeeList.length; i++) {
                        tx1312Request.addPayee(payeeList[i]);
                    }
                }*/

                // 3.执行报文处理
                tx1312Request.process();

                String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>"
                             + "<Request version=\"2.1\">" + "<Head>" + "<TxCode>1312</TxCode>"
                             + "</Head>" + "<Body>" + "<InstitutionID>"
                             + tx1312Request.getInstitutionID() + "</InstitutionID>" + "<OrderNo>"
                             + tx1312Request.getOrderNo() + "</OrderNo>" + "<PaymentNo>"
                             + tx1312Request.getPaymentNo() + "</PaymentNo>" + "<Amount>"
                             + tx1312Request.getAmount() + "</Amount>" + "<Remark>"
                             + tx1312Request.getRemark() + "</Remark>" + "<Note/>"
                             + "<NotificationURL>" + tx1312Request.getNotificationURL()
                             + "</NotificationURL>" + "<PayeeList/>" + "</Body>" + "</Request>";

                Map<String, String> requestData = new HashMap<String, String>();
                requestData.put("RequestPlainText", xml);
                requestData.put("message", tx1312Request.getRequestMessage());
                requestData.put("signature", tx1312Request.getRequestSignature());
                requestData.put("txCode", "1312");
                requestData.put("txName", EjavashopConfig.ALIPAY_ALL_SUBJECT);
                //                System.out.println("requestFrontUrl:" + requestFrontUrl);
                String html = SDKUtil.createAutoFormHtml(PaymentEnvironment.paymentURL, requestData,
                    SDKUtil.encoding_UTF8); //生成自动跳转的Html表单
                dataMap.put("paytext", html);
                return "front/order/alipay";
            } catch (Exception e) {
                log.error("unionpay支付出现异常" + e);
            }
        } else if ("unionpay".equals(optionsRadios)) { //银联支付
            try {
                String merId = EjavashopConfig.UNIONPAY_MERID; //商家ID

                payOrderAllsVO = payOrderAllsVO.multiply(new BigDecimal(100));
                String txnAmt = total_fee.split("\\.")[0]; //付款金额，单位为分，不能有小数点，去掉

                Map<String, Object> requestData = new HashMap<String, Object>();

                /***银联全渠道系统，产品参数，除了encoding自行选择外其他不需修改***/
                requestData.put("version", SDKUtil.version); //版本号，全渠道默认值
                requestData.put("encoding", SDKUtil.encoding_UTF8); //字符集编码，可以使用UTF-8,GBK两种方式
                requestData.put("signMethod", "01"); //签名方法，只支持 01：RSA方式证书加密
                requestData.put("txnType", "01"); //交易类型 ，01：消费
                requestData.put("txnSubType", "01"); //交易子类型， 01：自助消费
                requestData.put("bizType", "000201"); //业务类型，B2C网关支付，手机wap支付
                requestData.put("channelType", "07"); //渠道类型，这个字段区分B2C网关支付和手机wap支付；07：PC,平板  08：手机

                /***商户接入参数***/
                requestData.put("merId", merId); //商户号码，请改成自己申请的正式商户号或者open上注册得来的777测试商户号
                requestData.put("accessType", "0"); //接入类型，0：直连商户 

                requestData.put("orderId", tradesn); //商户订单号，8-40位数字字母，不能含“-”或“_”，可以自行定制规则     
                requestData.put("txnTime", TimeUtil.getCurrentTime()); //订单发送时间，取系统时间，格式为YYYYMMDDhhmmss，必须取当前时间，否则会报txnTime无效
                requestData.put("currencyCode", "156"); //交易币种（境内商户一般是156 人民币）        
                requestData.put("txnAmt", txnAmt); //交易金额，单位分，不要带小数点
                requestData.put("reqReserved", EjavashopConfig.UNIONPAY_REQRESERVED); //请求方保留域，透传字段（可以实现商户自定义参数的追踪）本交易的后台通知,对本交易的交易状态查询交易、对账文件中均会原样返回，商户可以按需上传，长度为1-1024个字节     

                //前台通知地址 （需设置为外网能访问 http https均可），支付成功后的页面 点击“返回商户”按钮的时候将异步通知报文post到该地址
                //如果想要实现过几秒中自动跳转回商户页面权限，需联系银联业务申请开通自动返回商户权限
                //异步通知参数详见open.unionpay.com帮助中心 下载  产品接口规范  网关支付产品接口规范 消费交易 商户通知
                requestData.put("frontUrl",
                    DomainUrlUtil.getEJS_URL_RESOURCES() + "/unionpay_result.html");

                //后台通知地址（需设置为【外网】能访问 http https均可），支付成功后银联会自动将异步通知报文post到商户上送的该地址，失败的交易银联不会发送后台通知
                //后台通知参数详见open.unionpay.com帮助中心 下载  产品接口规范  网关支付产品接口规范 消费交易 商户通知
                //注意:1.需设置为外网能访问，否则收不到通知    2.http https均可  3.收单后台通知后需要10秒内返回http200或302状态码 
                //    4.如果银联通知服务器发送通知后10秒内未收到返回状态码或者应答码非http200，那么银联会间隔一段时间再次发送。总共发送5次，每次的间隔时间为0,1,2,4分钟。
                //    5.后台通知地址如果上送了带有？的参数，例如：http://abc/web?a=b&c=d 在后台通知处理程序验证签名之前需要编写逻辑将这些字段去掉再验签，否则将会验签失败
                requestData.put("backUrl",
                    DomainUrlUtil.getEJS_URL_RESOURCES() + "/unionpay_result_notify.html");

                //////////////////////////////////////////////////
                //
                //       报文中特殊用法请查看 PCwap网关跳转支付特殊用法.txt
                //
                //////////////////////////////////////////////////

                /**请求参数设置完毕，以下对请求参数进行签名并生成html表单，将表单写入浏览器跳转打开银联页面**/
                Map<String, String> submitFromData = SDKUtil.signData(requestData,
                    SDKUtil.encoding_UTF8); //报文中certId,signature的值是在signData方法中获取并自动赋值的，只要证书配置正确即可。

                String requestFrontUrl = SDKConfig.getConfig().getFrontRequestUrl(); //获取请求银联的前台地址：对应属性文件acp_sdk.properties文件中的acpsdk.frontTransUrl
//                System.out.println("requestFrontUrl:" + requestFrontUrl);
                String html = SDKUtil.createAutoFormHtml(requestFrontUrl, submitFromData,
                    SDKUtil.encoding_UTF8); //生成自动跳转的Html表单

//                System.out.println("打印请求HTML，此为请求报文，为联调排查问题的依据：" + html);
                //将生成的html写到浏览器中完成自动跳转打开银联支付页面；这里调用signData之后，将html写到浏览器跳转到银联页面之前均不能对html中的表单项的名称和值进行修改，如果修改会导致验签不通过
                //                response.getWriter().write(html);
                dataMap.put("paytext", html);
                return "front/order/alipay";
            } catch (Exception e) {
                log.error("unionpay支付出现异常" + e);
            }
        } else if ("weixin".equals(optionsRadios)) {
            String showWeiXinMoney = payOrderAllsVO.toString();

            payOrderAllsVO = payOrderAllsVO.multiply(new BigDecimal(100));
            String txnAmt = payOrderAllsVO.toString().split("\\.")[0]; //付款金额，单位为分，不能有小数点，去掉

            // 32位随机字符串
            String nonce_str = StringUtil.getRandomString(32);
            // 获取本机Ip
            String ip = localIp();

            //建立时间格式化对象：
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            //获取当前时间，作为订单开始时间获取到的时间类型是long类型的，单位是毫秒
            long currentTime = System.currentTimeMillis();
            String currentTimeStr = dateFormat.format(new Date(currentTime));
            //在这个基础上加上5分钟：作为订单失效时间
            long currentTimeAddFive = currentTime + 5 * 60 * 1000;
            String currentTimeAddFiveStr = dateFormat.format(new Date(currentTimeAddFive));

            // 加密串
            SortedMap<String, String> packageParams = new TreeMap<String, String>();

            packageParams.put("appid", CommonConstants.APPID);
            packageParams.put("mch_id", CommonConstants.MCHID);
            packageParams.put("product_id", tradesn); // 商户根据自己业务传递的参数 必填
            packageParams.put("nonce_str", nonce_str);
            packageParams.put("body", EjavashopConfig.WEIXIN_ORDER_NAME);
            packageParams.put("out_trade_no", tradesn);
            packageParams.put("total_fee", txnAmt); //支付金额,精确到分 txnAmt
            packageParams.put("spbill_create_ip", ip);
            packageParams.put("notify_url", CommonConstants.NOTIFY_URL);
            packageParams.put("trade_type", "NATIVE");
            packageParams.put("time_start", currentTimeStr);
            packageParams.put("time_expire", currentTimeAddFiveStr);
            String sign = createSign(packageParams, CommonConstants.KEY);
            packageParams.put("sign", sign);

            // xml传输数据
            String xml = "<xml>" + "<appid>" + CommonConstants.APPID + "</appid>" + "<mch_id>"
                         + CommonConstants.MCHID + "</mch_id>" + "<product_id>" + tradesn
                         + "</product_id>" + "<nonce_str>" + nonce_str + "</nonce_str>" + "<body>"
                         + EjavashopConfig.WEIXIN_ORDER_NAME + "</body>" + "<out_trade_no>"
                         + tradesn + "</out_trade_no>" + "<total_fee>" + txnAmt + "</total_fee>"
                         + "<spbill_create_ip>" + ip + "</spbill_create_ip>" + "<notify_url>"
                         + CommonConstants.NOTIFY_URL + "</notify_url>"
                         + "<trade_type>NATIVE</trade_type>" + "<time_start>" + currentTimeStr
                         + "</time_start>" + "<time_expire>" + currentTimeAddFiveStr
                         + "</time_expire>" + "<sign>" + sign + "</sign>" + "</xml>";

            Map<String, Object> map = GetWxOrderno.getPayNo(CommonConstants.CREATEORDERURL, xml);

            if (null == map) {
                dataMap.put("info", "系统维护稍后重试");
                return "front/commons/error";
            }

            String returnCode = (String) map.get("return_code");

            if (!"SUCCESS".equals(returnCode)) {
                dataMap.put("info", "系统维护稍后重试");
                return "front/commons/error";
            }

            String resultCode = (String) map.get("result_code");
            if (!"SUCCESS".equals(resultCode)) {
                dataMap.put("info", "系统维护稍后重试");
                return "front/commons/error";
            }

            // 获取生成二维码的参数
            String codeUrl = (String) map.get("code_url");
            if (com.mysql.jdbc.StringUtils.isNullOrEmpty(codeUrl)) {
                dataMap.put("info", "系统维护稍后重试");
                return "front/commons/error";
            }

            dataMap.put("showWeiXinMoney", showWeiXinMoney);
            dataMap.put("ordersSn", tradesn);
            dataMap.put("codeUrl", codeUrl);

            return "front/order/weixinpay";
            //翼支付
        } else if ("bestpay".equals(optionsRadios)) {
            try {
                //登录号
                String ORGLOGINCODE = BestPayConfig.ORGLOGINCODE;
                //平台号
                String PLATCODE = BestPayConfig.PLATCODE;
                //订单号
                String ORDERID = tradesn;//orderPaySn.toString();
                //支付方式 1:收银台支付 3：非收银台自定义网银支付
                String PAYTYPE = "1";
                //同步通知地址
                String SYNNOTICEURL = DomainUrlUtil.getEJS_URL_RESOURCES() + "/bestpay_result.html";
                //异步通知地址
                String ASYNNOTICEURL = DomainUrlUtil.getEJS_URL_RESOURCES()
                                       + "/bestpay_result_notifyAll.html";
                //String ASYNNOTICEURL = "http://112.65.213.167:8810/bestpay_result_notifyAll.html";
                //银行卡号 
                String PAYACCOUNT = "";
                //户名
                String CARDUSERNAME = "";
                //证件号
                String CERTNO = "";
                //证件类型
                String CERTTYPE = "";
                //银行预留手机号
                String MOBILE = "";
                //对公/对私标志 0:对公   1：对私
                String PERENTFLAG = "1";
                //卡类型
                String CARDTYPE = "";
                //银行编码
                String BANKCODE = "";
                String COMMENT1 = "";
                String COMMENT2 = "";
                //订单金额,翼支付会便宜个0.003
                //翼支付的优惠时间在2015-12-19至2017-01-18
                if (CalculateUtil.isInActiveTime()) {
                    payOrderAllsVO = payOrderAllsVO.multiply(Orders.BESTPAY_DISCOUNT).setScale(2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));
                }else{
                    payOrderAllsVO = payOrderAllsVO.multiply(new BigDecimal(100));
                }
                String ORDERAMOUNT = payOrderAllsVO.toString().split("\\.")[0]; //付款金额，单位为分，不能有小数点，去掉
                Cipher cipher;
                //获取AES密钥
                //String AESKEY =  RsaCipher.getPrivateKey().toString();

                String AESKEY = RandomStringUtils.randomAlphanumeric(16);//获取16位EAS随机数秘钥
//                System.out.println("16位随机数: " + AESKEY);

//                System.out.println("私钥" + AESKEY);
                cipher = AESUtil.initDecryptCipher(AESKEY, AESUtil.AES_CBC_PKCS5,
                    Cipher.ENCRYPT_MODE, AESUtil.IV);
                //加密
                String aesORGLOGINCODE = getAesParam(ORGLOGINCODE, cipher);
                String aesPAYACCOUNT = getAesParam(PAYACCOUNT, cipher);
                String aesCARDUSERNAME = getAesParam(CARDUSERNAME, cipher);
                String aesCERTNO = getAesParam(CERTNO, cipher);
                String aesMOBILE = getAesParam(MOBILE, cipher);

                //用支付公司公钥加密AES密钥，
                //用支付公司公钥加密AES密钥,支付公司公钥配置在Constants.PUBLIC_CERT
                byte[] aesKeyByte = RsaCipher.enDecryptByRsa(AESKEY.getBytes("utf-8"),
                    RsaCipher.getBestPayPublicKey(), Cipher.ENCRYPT_MODE);
                String aesEncodedKey = Base64Utils.encode(aesKeyByte);
//                System.out.println("aesEncodedKey: " + aesEncodedKey);

                //SIGNSTR原串
                StringBuilder data = new StringBuilder();
                //data.append("ORGLOGINCODE=").append(aesORGLOGINCODE);
                data.append("PLATCODE=").append(PLATCODE);
                data.append("&LOGINCODE=").append(aesORGLOGINCODE);
                data.append("&AMOUNT=").append(ORDERAMOUNT);
                //data.append("&ORDERAMOUNT=").append(ORDERAMOUNT);
               // data.append("&PAYTYPE=").append(PAYTYPE);
                //data.append("&SYNNOTICEURL=").append(SYNNOTICEURL);
                data.append("&SYNURL=").append(SYNNOTICEURL);
               // data.append("&ASYNNOTICEURL=").append(ASYNNOTICEURL);
                data.append("&ASYNURL=").append(ASYNNOTICEURL);
                data.append("&ORDERID=").append(ORDERID);
                //data.append("&PAYACCOUNT=").append(aesPAYACCOUNT);
                //data.append("&CARDUSERNAME=").append(aesCARDUSERNAME);
                //data.append("&CERTNO=").append(aesCERTNO);
                //data.append("&CERTTYPE=").append(CERTTYPE);
                //data.append("&MOBILE=").append(aesMOBILE);
                //data.append("&PERENTFLAG=").append(PERENTFLAG);
                //data.append("&CARDTYPE=").append(CARDTYPE);
                //data.append("&BANKCODE=").append(BANKCODE);
                data.append("&COMMENT1=").append(COMMENT1);
                data.append("&COMMENT2=").append(COMMENT2);

                //用商户自己的证书私钥对json格式的data加签得到签名SIGNSTR，需替换商户的证书server.jks
                byte[] signByte = RsaCipher.sign(data.toString().getBytes("utf-8"),
                    RsaCipher.getPrivateKey());
                String SIGNSTR = Base64Utils.encode(signByte);
//                System.out.println("SIGNSTR: " + SIGNSTR);

                //请求参数
                Map<String, String> map = new HashMap<String, String>();
                //map.put("ORGLOGINCODE", aesORGLOGINCODE);
                map.put("PLATCODE", PLATCODE);
                map.put("LOGINCODE", aesORGLOGINCODE);
               //map.put("ORDERAMOUNT", ORDERAMOUNT);
                map.put("AMOUNT", ORDERAMOUNT);
                //map.put("PAYTYPE", PAYTYPE);
                //map.put("SYNNOTICEURL", SYNNOTICEURL);
                map.put("SYNURL", SYNNOTICEURL);
                //map.put("ASYNNOTICEURL", ASYNNOTICEURL);
                map.put("ASYNURL", ASYNNOTICEURL);
                map.put("ORDERID", ORDERID);
               // map.put("PAYACCOUNT", aesPAYACCOUNT);
                //map.put("CARDUSERNAME", aesCARDUSERNAME);
                //map.put("CERTNO", aesCERTNO);
                //map.put("CERTTYPE", CERTTYPE);
               // map.put("MOBILE", aesMOBILE);
               // map.put("PERENTFLAG", PERENTFLAG);
               // map.put("CARDTYPE", CARDTYPE);
               // map.put("BANKCODE", BANKCODE);
                map.put("COMMENT1", COMMENT1);
                map.put("COMMENT2", COMMENT2);
                map.put("SIGNSTR", SIGNSTR);
                map.put("AESKEY", aesEncodedKey);	
                
//                System.out.println(map.toString());

                String html = SDKUtil.createAutoFormHtml(BestPayConfig.BEST_PAY_RECHARGE_true_URL, map,
                    SDKUtil.encoding_UTF8); //生成自动跳转的Html表单

//                System.out.println("打印请求HTML，此为请求报文，为联调排查问题的依据：" + html);
                //将生成的html写到浏览器中完成自动跳转打开银联支付页面；这里调用signData之后，将html写到浏览器跳转到银联页面之前均不能对html中的表单项的名称和值进行修改，如果修改会导致验签不通过
                //                response.getWriter().write(html);
                dataMap.put("paytext", html);

                /*System.out.println("加密后整个请求报文数据:{}" + requestBody);
                HttpPost httpPost = new HttpPost(BestPayConfig.BEST_PAY_TEST_URL);
                httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
                StringEntity se = new StringEntity(requestBody, "UTF-8");
                httpPost.setEntity(se);
                HttpResponse httpResponse = HttpClients.createDefault().execute(httpPost);
                String responseStr = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
                
                System.out.println("请求后反馈报文:{}" + responseStr);
                dataMap.put("paytext", responseStr);*/
                return "front/order/bestpay";

            } catch (Exception e) {
                e.printStackTrace();
                log.error("bestpay支付出现异常" + e);
                dataMap.put("info", "系统维护稍后重试");
                return "front/commons/error";
            }

        } else {

        }

        return "";
    }
        
    //加密
    public static String getAesParam(String param, Cipher cipher) throws Exception {
        String content = String.valueOf(param);
        byte[] encrypt = AESUtil.encrypt(content, cipher);
        String aesParam = Base64Utils.encode(encrypt);
//        System.out.println("参数明文:" + param);
//        System.out.println("参数密文: " + aesParam);
        return aesParam;
    }

    /**
     * 创建md5摘要,规则是:按参数名称a-z排序,遇到空值的参数不参加签名。
     */
    @SuppressWarnings("rawtypes")
    public String createSign(SortedMap<String, String> packageParams, String key) {
        StringBuffer sb = new StringBuffer();
        Set es = packageParams.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            String v = (String) entry.getValue();
            if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        sb.append("key=" + key);

        log.info("WXPay md5 sb:" + sb + "key=" + key);
        String sign = MD5Util.MD5Encode(sb.toString(), "UTF-8").toUpperCase();
        log.info("WXPay packge签名:" + sign);

        return sign;
    }

    /**
       * 获取本机Ip 
       *  
       *  通过 获取系统所有的networkInterface网络接口 然后遍历 每个网络下的InterfaceAddress组。
       *  获得符合 <code>InetAddress instanceof Inet4Address</code> 条件的一个IpV4地址
       * @return
    */
    @SuppressWarnings("rawtypes")
    private String localIp() {
        String ip = null;
        Enumeration allNetInterfaces;
        try {
            allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
                List<InterfaceAddress> InterfaceAddress = netInterface.getInterfaceAddresses();

                for (InterfaceAddress add : InterfaceAddress) {
                    InetAddress Ip = add.getAddress();
                    if (Ip != null && Ip instanceof Inet4Address) {
                        ip = Ip.getHostAddress();
                    }
                }

            }
        } catch (SocketException e) {
            log.warn("获取本机Ip失败:异常信息:" + e.getMessage());
        }
        return ip;
    }
}
