package com.bestpay.util;

import java.net.URL;


public class BestPayConfig {
	//登录号
	public static final String ORGLOGINCODE = "dww123";
	//平台号
	public static final String PLATCODE = "0020000000025037";
	//测试地址
	public static final String BEST_PAY_TEST_URL = "http://183.62.49.171:8091/payment_plugin/payRequestV3.do";
	//生产地址
	public static final String BEST_PAY_true_URL = "https://corporation.bestpay.com.cn/payment_plugin/payRequestV3.do";
	//网银充值接口
	public static final String BEST_PAY_RECHARGE_true_URL = "https://corporation.bestpay.com.cn/payment_plugin/rechargerequestV3.do";
	
	//手机端测试地址
	public static final String BEST_PAY_MOBILE_TEST="http://183.62.49.171:8091/payment_plugin/bestpay/wapBankPayV3.do";
	//手机端生产地址
	public static final String BEST_PAY_MOBILE_true="https://corporation.bestpay.com.cn/payment_plugin/bestpay/wapBankPayV3.do";
	//手机端生产地址
	public static final String BEST_PAY_MOBILE_WAP = "https://corporation.bestpay.com.cn/payment_plugin/bestpay/wapBankPayV3.do";
}
