package com.ejavashop.web.controller.order;
//package com.ejavashop.web.controller.order;
//
//import java.io.UnsupportedEncodingException;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.Map;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//import com.javamalls.basic.controller.BaseController;
//import com.javamalls.entity.MemberPredeposit;
//import com.javamalls.front.payment.alipay.util.AlipayNotify;
//import com.javamalls.front.service.member.IFrontMemberPredepositService;
//import com.javamalls.front.service.order.IFrontOrdersService;
//import com.javamalls.front.service.order.IFrontPaymentService;
//import com.javamalls.front.vo.FrontOrderSuccessVO;
//import com.javamalls.util.ConstantsJM;
//import com.javamalls.util.ConvertUtil;
//import com.javamalls.util.ServiceResult;
//
///**
// * 支付宝支付接口
// *                       
// * @Filename: FrontPaymentController.java
// * @Version: 1.0
// * @Author: 王芳
// * @Email: wfcoder@163.com
// *
// */
//@Controller
//public class FrontPaymentController extends BaseController {
//
//    @Resource
//    private IFrontPaymentService          paymentService;
//
//    @Resource
//    private IFrontOrdersService           ordersService;
//
//    @Resource
//    private IFrontMemberPredepositService memberPredepositService;
//
//    /**
//     * 支付宝支付异步消息返回调用地址
//     * @param request
//     * @param response
//     * @param dataMap
//     * @return
//     */
//    @RequestMapping(value = "/alipayNotify.html", method = { RequestMethod.POST })
//    public String alipayNotify(HttpServletRequest request, HttpServletResponse response,
//                               Map<String, Object> dataMap) {
//        //获取支付宝POST过来反馈信息
//        Map<String, String> params = new HashMap<String, String>();
//        Map requestParams = request.getParameterMap();
//        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
//            String name = (String) iter.next();
//            String[] values = (String[]) requestParams.get(name);
//            String valueStr = "";
//            for (int i = 0; i < values.length; i++) {
//                valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i]
//                                                                             + ",";
//            }
//            //乱码解决，如果mysign和sign不相等也可以使用这段代码转化
//            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
//            params.put(name, valueStr);
//        }
//
//        //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
//        //商户订单号
//        try {
//            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes(
//                "ISO-8859-1"), "UTF-8");
//            //支付宝交易号
//            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),
//                "UTF-8");
//            //交易状态
//            String trade_status = new String(request.getParameter("trade_status").getBytes(
//                "ISO-8859-1"), "UTF-8");
//            //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
//            if (AlipayNotify.verify(params)) {//验证成功
//                if (trade_status.equals("TRADE_FINISHED")) {
//                    //判断该笔订单是否在商户网站中已经做过处理
//                    //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
//                    //如果有做过处理，不执行商户的业务程序
//
//                    //注意：
//                    //退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
//                } else if (trade_status.equals("TRADE_SUCCESS")) {
//                    //判断该笔订单是否在商户网站中已经做过处理
//                    //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
//                    //如果有做过处理，不执行商户的业务程序
//
//                    //注意：
//                    //付款完成后，支付宝系统发送该交易状态通知
//                }
//                System.out.println("success"); //请不要修改或删除
//
//            } else {
//                //验证失败
//                dataMap.put("info", "验证失败");
//                return "front/commons/error";
//            }
//
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    /**
//     * 支付宝返回调用地址
//     * @param request
//     * @param response
//     * @param dataMap
//     * @return
//     */
//    @RequestMapping(value = "/alipayReturn.html", method = { RequestMethod.GET })
//    public String alipayReturn(HttpServletRequest request, HttpServletResponse response,
//                               Map<String, Object> dataMap) {
//        //获取支付宝GET过来反馈信息
//        Map<String, String> params = new HashMap<String, String>();
//        Map requestParams = request.getParameterMap();
//        String url = "";
//        try {
//            for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
//                String name = (String) iter.next();
//                String[] values = (String[]) requestParams.get(name);
//                String valueStr = "";
//                for (int i = 0; i < values.length; i++) {
//                    valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr
//                                                                                 + values[i];
//                }
//                //乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
//                if (!name.equals("subject")) {
//                    //如果传递的是中文不需要转码
//                    valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
//                }
//                params.put(name, valueStr);
//            }
//            //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
//            //商户订单号
//            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes(
//                "ISO-8859-1"), "UTF-8");
//            //支付宝交易号
//            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),
//                "UTF-8");
//            //交易状态
//            String trade_status = new String(request.getParameter("trade_status").getBytes(
//                "ISO-8859-1"), "UTF-8");
//            //操作类型
//            String body = new String(request.getParameter("body").getBytes("ISO-8859-1"), "UTF-8");
//
//            //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
//            //计算得出通知验证结果
//            boolean verify_result = AlipayNotify.verify(params);
//            if (verify_result) {//验证成功
//                if (trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")) {
//                    //订单支付
//                    if (body.equals(ConstantsJM.ALI_PAY_TYPE_ORDERS)) {
//                        //支付成功 拆分订单
//                        ServiceResult<FrontOrderSuccessVO> serviceResult = new ServiceResult<FrontOrderSuccessVO>();
//                        serviceResult = ordersService.paySuccessSplitOrder(
//                            ConvertUtil.toInt(out_trade_no, 0), trade_no, request);
//                        if (!serviceResult.getSuccess()) {
//                            if (ConstantsJM.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult
//                                .getCode())) {
//                                throw new RuntimeException(serviceResult.getMessage());
//                            }
//                        }
//                        url = "redirect:/order/success.html?relationOrderSn=" + out_trade_no;
//                    } else if (body.equals(ConstantsJM.ALI_PAY_TYPE_RECHARGE)) {
//                        //充值成功 
//                        ServiceResult<MemberPredeposit> serviceResult = new ServiceResult<MemberPredeposit>();
//                        serviceResult = memberPredepositService.rechargeReturn(
//                            ConvertUtil.toInt(out_trade_no, 0), trade_no, request);
//                        if (!serviceResult.getSuccess()) {
//                            if (ConstantsJM.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult
//                                .getCode())) {
//                                throw new RuntimeException(serviceResult.getMessage());
//                            }
//                        }
//                        url = "redirect:/paysuccess.html?id=" + out_trade_no;
//                    }
//                }
//
//            } else {
//                //验证失败
//                dataMap.put("info", "验证失败");
//                return "front/commons/error";
//            }
//
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//
//        return url;
//    }
//}
