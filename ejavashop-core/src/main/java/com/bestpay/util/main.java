package com.bestpay.util;
import javax.crypto.Cipher;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class main {

	public static void main(String[] args) {

		try {
			String URL="http://183.62.49.171:8091/payment_plugin/payRequestV3.do";//测试地址
//			String URL="https://corporation.bestpay.com.cn/payment_plugin/payRequestV3.do";//生产地址
			String  PLATCODE="0020000000081001";//平台号
			String ORGLOGINCODE="qiuwenjun";//登录号 Y
			String ORDERID="201607110586545621152211";//订单号
			String ORDERAMOUNT="1";//订单金额
			String PAYTYPE="1";//支付方式
			String SYNNOTICEURL="http://172.25.132.22:8787/testfas/sendNews/returnMerchantV3.do";//同步通知地址
			String ASYNNOTICEURL="http://172.25.132.22:8787/testfas/sendNews/requestSendNewsV3.do";//回调地址
			String PAYACCOUNT="";//银行卡号  Y
			String CARDUSERNAME="";//户名  Y
			String CERTNO="";//证件号  Y
			String CERTTYPE="";//证件类型
			String MOBILE="";//银行预留手机号 Y
			String PERENTFLAG="";//对公/对私标志
			String CARDTYPE="";//卡类型
			String BANKCODE="";//银行编码
			String COMMENT1="";//备注1
			String COMMENT2="";//COMMENT2

			Cipher cipher;
			//随机生成AES密钥
			String AESKEY = "4N629t11cxg61WFr";//获取16位EAS随机数秘钥
			System.out.println("16位随机数: " + AESKEY);
			cipher = AESUtil.initDecryptCipher(AESKEY, AESUtil.AES_CBC_PKCS5, Cipher.ENCRYPT_MODE, AESUtil.IV);

			//ORGLOGINCODE加密
			String aesORGLOGINCODE=getAesParam(ORGLOGINCODE,cipher);
			String aesPAYACCOUNT=getAesParam(PAYACCOUNT,cipher);
			String aesCARDUSERNAME=getAesParam(CARDUSERNAME,cipher);
			String aesCERTNO=getAesParam(CERTNO,cipher);
			String aesMOBILE=getAesParam(MOBILE,cipher);

			//用支付公司公钥加密AES密钥,支付公司公钥配置在Constants.PUBLIC_CERT
			byte[] aesKeyByte = RsaCipher.enDecryptByRsa(AESKEY.getBytes("utf-8"), RsaCipher.getBestPayPublicKey(), Cipher.ENCRYPT_MODE);
			String aesEncodedKey = Base64Utils.encode(aesKeyByte);
			System.out.println("aesEncodedKey: " +aesEncodedKey);

			//SIGNSTR原串
			StringBuilder data = new StringBuilder();
			data.append("ORGLOGINCODE=").append(aesORGLOGINCODE);
			data.append("&PLATCODE=").append(PLATCODE);
			data.append("&ORDERID=").append(ORDERID);
			data.append("&ORDERAMOUNT=").append(ORDERAMOUNT);
			data.append("&PAYTYPE=").append(PAYTYPE);
			data.append("&SYNNOTICEURL=").append(SYNNOTICEURL);
			data.append("&ASYNNOTICEURL=").append(ASYNNOTICEURL);
			data.append("&PAYACCOUNT=").append(aesPAYACCOUNT);
			data.append("&CARDUSERNAME=").append(aesCARDUSERNAME);
			data.append("&CERTNO=").append(aesCERTNO);
			data.append("&CERTTYPE=").append(CERTTYPE);
			data.append("&MOBILE=").append(aesMOBILE);
			data.append("&PERENTFLAG=").append(PERENTFLAG);
			data.append("&CARDTYPE=").append(CARDTYPE);
     		data.append("&BANKCODE=").append(BANKCODE);
			data.append("&COMMENT1=").append(COMMENT1);
    		data.append("&COMMENT2=").append(COMMENT2);



			//用商户自己的证书私钥对json格式的data加签得到签名SIGNSTR，需替换商户的证书server.jks
			byte[] signByte = RsaCipher.sign(data.toString().getBytes("utf-8"), RsaCipher.getPrivateKey());
			String SIGNSTR = Base64Utils.encode(signByte);
			System.out.println("SIGNSTR: " + SIGNSTR);

			//请求参数
			StringBuilder perpareParam = new StringBuilder();
			perpareParam.append("ORGLOGINCODE=").append(aesORGLOGINCODE);
			perpareParam.append("&PLATCODE=").append(PLATCODE);
			perpareParam.append("&ORDERID=").append(ORDERID);
			perpareParam.append("&ORDERAMOUNT=").append(ORDERAMOUNT);
			perpareParam.append("&PAYTYPE=").append(PAYTYPE);
			perpareParam.append("&SYNNOTICEURL=").append(SYNNOTICEURL);
			perpareParam.append("&ASYNNOTICEURL=").append(ASYNNOTICEURL);
			perpareParam.append("&PAYACCOUNT=").append(aesPAYACCOUNT);
			perpareParam.append("&CARDUSERNAME=").append(aesCARDUSERNAME);
			perpareParam.append("&CERTNO=").append(aesCERTNO);
			perpareParam.append("&CERTTYPE=").append(CERTTYPE);
			perpareParam.append("&MOBILE=").append(aesMOBILE);
			perpareParam.append("&PERENTFLAG=").append(PERENTFLAG);
			perpareParam.append("&CARDTYPE=").append(CARDTYPE);
			perpareParam.append("&BANKCODE=").append(BANKCODE);
			perpareParam.append("&COMMENT1=").append(COMMENT1);
			perpareParam.append("&COMMENT2=").append(COMMENT2);
			perpareParam.append("&SIGNSTR=").append(SIGNSTR);
			perpareParam.append("&AESKEY=").append(aesEncodedKey);


			String requestBody = perpareParam.toString();
			//+号替换成%2B
			requestBody=requestBody.replaceAll("\\+", "%2B");

			System.out.println("加密后整个请求报文数据:{}" + requestBody);
			HttpPost httpPost = new HttpPost(URL);
			httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
			StringEntity se = new StringEntity(requestBody, "UTF-8");
			httpPost.setEntity(se);
			HttpResponse httpResponse = HttpClients.createDefault().execute(httpPost);
			String responseStr = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
			System.out.println("请求后反馈报文:{}" + responseStr);

		} catch (Exception e) {
			e.printStackTrace();
		}


	}
	public static String getAesParam(String param,Cipher cipher) throws Exception {
		String content = String.valueOf(param);
		byte[] encrypt = AESUtil.encrypt(content, cipher);
		String aesParam = Base64Utils.encode(encrypt);
		System.out.println("参数明文:" + param);
		System.out.println("参数密文: " + aesParam);
		return  aesParam;
	}
}

