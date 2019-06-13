package com.phkj.web.util;

/**
 * @author ：zl
 * @date ：Created in 2019/5/19 19:44
 * @description：微信常量字段
 * @modified By：
 * @version: 0.0.1$
 */
public class WXPublicConstants {
    public static String TOKEN = "phkj";

    //公众号唯一标识
    public static final String APPID = "wx3ec96b7fb94e38fa";
    //公众号的appsecret
    public static final String APPSECRET = "29e0f0e85c8135e18193063d3f2fb04c";
    //获取网页授权accessToken的接口
    public static final String GET_WEB_ACCESSTOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
    //获取用户信息的接口
    public static final String GET_USERINFO_URL = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";

    public final static String SIGN_TICKET_CREATE_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";

}
