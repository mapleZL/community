package com.phkj.web.util;

import com.alibaba.fastjson.JSONObject;

public class WeChatUtil {
    //公众号唯一标识
    public static final String APPID = "wx3ec96b7fb94e38fa";
    //公众号的appsecret
    public static final String APPSECRET = "29e0f0e85c8135e18193063d3f2fb04c";
    //获取网页授权accessToken的接口
    public static final String GET_WEB_ACCESSTOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
    //获取用户信息的接口
    public static final String GET_USERINFO_URL = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";

    /**
     * 获取网页授权的AccessToken凭据
     * 返回示例：
     *  {
     *     "access_token":"ACCESS_TOKEN",
     *     "expires_in":7200,
     *     "refresh_token":"REFRESH_TOKEN",
     *     "openid":"OPENID",
     *     "scope":"SCOPE"
     *  }
     *
     * @return
     */
    public static JSONObject getWebAccessToken(String code) {
        String result = HttpUtil.get(GET_WEB_ACCESSTOKEN_URL.replace("APPID", APPID).replace("SECRET", APPSECRET).replace("CODE", code));
        JSONObject json = JSONObject.parseObject(result);
        return json;
    }

    /**
     * 获取用户信息
     * 返回示例：
     * {
     *    "openid":" OPENID",
     *    "nickname": "NICKNAME",
     *    "sex":"1",
     *    "province":"PROVINCE",
     *    "city":"CITY",
     *    "country":"COUNTRY",
     *    "headimgurl": "http://thirdwx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/46",
     *    "privilege":[ "PRIVILEGE1" "PRIVILEGE2"  ],
     *    "unionid": "o6_bmasdasdsad6_2sgVt7hMZOPfL"
     * }
     *
     */
    public static JSONObject getUserInfo(String accessToken,String openId){
        String result = HttpUtil.get(GET_USERINFO_URL.replace("ACCESS_TOKEN", accessToken).replace("OPENID",openId));
        JSONObject json = JSONObject.parseObject(result);
        return json;
    }

}