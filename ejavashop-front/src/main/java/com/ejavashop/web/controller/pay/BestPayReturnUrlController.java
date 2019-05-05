package com.ejavashop.web.controller.pay;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.security.GeneralSecurityException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bestpay.util.CertResponse;
import com.bestpay.util.Constants;
import com.bestpay.util.ObjectJsonUtil;
import com.bestpay.util.RsaCipher;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.service.order.IOrdersService;
import com.ejavashop.util.interfacewms.XmlConvertUtil;
import com.ejavashop.web.controller.BaseController;
@Controller
public class BestPayReturnUrlController extends BaseController{
	
	@Resource
    private IOrdersService ordersService;

	@RequestMapping(value = "/bestpay_result.html", method = {RequestMethod.GET,RequestMethod.POST})
	public String bestPaySYNNOTICEURL(HttpServletRequest request, HttpServletResponse response){
		try {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			/*InputStreamReader inputStreamReader = new InputStreamReader(request.getInputStream(), "utf-8");
            BufferedReader in = new BufferedReader(inputStreamReader);
            String line = null;
            StringBuilder stringBuffer = new StringBuilder(255);
            while ((line = in.readLine()) != null) {
                stringBuffer.append(line);
                stringBuffer.append("\n");
            }
            String responseMessage = stringBuffer.toString();*/
			String responseMessage = "";
			//解析 返回报文
			String sign = request.getParameter("SIGNSTR");
			String plainText = "";
            String pubKey = Constants.PUBLIC_CERT;
            StringBuffer sb = new StringBuffer();
            sb.append("ORGLOGINCODE=" + request.getParameter("ORGLOGINCODE"));
            sb.append("&PLATCODE=" + request.getParameter("PLATCODE"));
            sb.append("&CUSTCODE=" + request.getParameter("CUSTCODE"));
            sb.append("&ORDERID=" + request.getParameter("ORDERID"));
            sb.append("&TRSSEQ=" + request.getParameter("TRSSEQ"));
            sb.append("&ORDERAMOUNT=" + request.getParameter("ORDERAMOUNT"));
            sb.append("&FEE=" + request.getParameter("FEE"));
            sb.append("&TRANSDATE=" + request.getParameter("TRANSDATE"));
            sb.append("&ORDERSTATUS=" + request.getParameter("ORDERSTATUS"));
            sb.append("&BANKCODE=" + request.getParameter("BANKCODE"));
            sb.append("&PERENTFLAG=" + request.getParameter("PERENTFLAG"));
            sb.append("&COMMENT1=" + request.getParameter("COMMENT1"));
            sb.append("&COMMENT2=" + request.getParameter("COMMENT2"));
            plainText = sb.toString();
            
            
			
            /*Enumeration<String> requestNames = request.getParameterNames();
            Map<String, String> map = new HashMap<String, String>();
            while (requestNames.hasMoreElements()) {
                String name = requestNames.nextElement();
                String value = request.getParameter(name);
                value = URLDecoder.decode(value, "UTF-8");
                map.put(name, value);
            }*/
            //Map<String, Object> map = XmlConvertUtil.parseJSON2Map(responseMessage);
            //if(map != null){
                //String sign = (String) map.get("SIGNSTR");
                //map.remove("SIGNSTR");
                //CertResponse certResponse = ObjectJsonUtil.jsonToObj(responseMessage, CertResponse.class);
                //String plainText = ObjectJsonUtil.objToJson(map.toString());
                try {
                    //boolean check = RsaCipher.verifyUtf8(plainText, Constants.PUBLIC_CERT, sign);
                	boolean isSuccess = RsaCipher.verifyUtf8(plainText, pubKey, sign);
                    if (!isSuccess) {
                        responseMessage = "报文被窜改";
                    }else{
                    	responseMessage = "验签成功！";
                    	return "front/order/linepay";
                    }
                } catch (GeneralSecurityException ge) {
                    ge.printStackTrace();
                    responseMessage = "验签失败！";
                }
            //}
		} catch (Exception e) {
			 log.error("bestpay同步接口出现异常" + e);
	         try {
	             response.getWriter().write("fail");
	         } catch (IOException e1) {
	             e1.printStackTrace();
	         }
		}
		return "front/order/payError";
	}
	
	@RequestMapping(value = "/bestpay_result_notifyAll.html", method = {RequestMethod.GET,RequestMethod.POST})
	public void bestPayASYNNOTICEURL(HttpServletRequest request, HttpServletResponse response){
		try {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			
			/*InputStreamReader inputStreamReader = new InputStreamReader(request.getInputStream(), "utf-8");
            BufferedReader in = new BufferedReader(inputStreamReader);
            String line = null;
            StringBuilder stringBuffer = new StringBuilder(255);
            while ((line = in.readLine()) != null) {
                stringBuffer.append(line);
                stringBuffer.append("\n");
            }
            String responseMessage = stringBuffer.toString();
            CertResponse certResponse = ObjectJsonUtil.jsonToObj(responseMessage, CertResponse.class);
            String plainText = ObjectJsonUtil.objToJson(certResponse.getData());*/
			
			String sign = request.getParameter("SIGNSTR");
			//订单金额
        	String orderAmount = request.getParameter("ORDERAMOUNT");
        	//订单号
        	String ORDERID = request.getParameter("ORDERID");
        	//订单状态
        	String ORDERSTATUS = request.getParameter("ORDERSTATUS");
        	//交易流水号
        	String TRSSEQ = request.getParameter("TRSSEQ");
//            ordersService.orderPayAfter(ORDERID, orderAmount.toString(), "bestpay", "翼支付", TRSSEQ, "xxx");
//            if (true) return;
			String responseMessage = "";
			
			String plainText = "";
            String pubKey = Constants.PUBLIC_CERT;
            StringBuffer sb = new StringBuffer();
            sb.append("ORGLOGINCODE=" + request.getParameter("ORGLOGINCODE"));
            sb.append("&PLATCODE=" + request.getParameter("PLATCODE"));
            sb.append("&CUSTCODE=" + request.getParameter("CUSTCODE"));
            sb.append("&ORDERID=" + ORDERID);
            sb.append("&TRSSEQ=" + TRSSEQ);
            sb.append("&ORDERAMOUNT=" + orderAmount);
            sb.append("&FEE=" + request.getParameter("FEE"));
            sb.append("&TRANSDATE=" + request.getParameter("TRANSDATE"));
            sb.append("&ORDERSTATUS=" + ORDERSTATUS);
            sb.append("&BANKCODE=" + request.getParameter("BANKCODE"));
            sb.append("&PERENTFLAG=" + request.getParameter("PERENTFLAG"));
            sb.append("&COMMENT1=" + request.getParameter("COMMENT1"));
            sb.append("&COMMENT2=" + request.getParameter("COMMENT2"));
            plainText = sb.toString();
            try {
            	boolean isSuccess = RsaCipher.verifyUtf8(plainText, pubKey, sign);
                if (!isSuccess) {
                    responseMessage = "报文被窜改";
                }else{
                	responseMessage = "验签成功！";
                	if("1".equals(ORDERSTATUS)){
                		BigDecimal orderAmountBD = new BigDecimal(orderAmount);
                		//将分转成园
                		//orderAmountBD = orderAmountBD.divide(new BigDecimal(100));
                		sb.append("&SIGNSTR="+sign);
                		ServiceResult<Boolean> orderPayResult = ordersService.orderPayAfter(ORDERID, orderAmountBD.toString(), "bestpay", "翼支付", TRSSEQ, sb.toString());
                        if (orderPayResult.getResult())
                            response.getWriter().write("BUSINESS_CONFIRM");
                	}
                }
            } catch (GeneralSecurityException ge) {
                ge.printStackTrace();
                responseMessage = "验签失败！";
            }
            response.getWriter().write(responseMessage);
            response.setContentType("text/json; charset=UTF-8");
		}catch (Exception e) {
			log.error("bestpay异步接口出现异常" + e);
		}
	}
}
