package com.phkj.web.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.phkj.core.redis.RedisComponent;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.HashMap;

import static com.phkj.core.StringUtil.buildRandom;

public class WeChatUtil {
    private static Logger log = LogManager.getLogger(WeChatUtil.class);

    /**
     * 获取网页授权的AccessToken凭据
     * 返回示例：
     * {
     * "access_token":"ACCESS_TOKEN",
     * "expires_in":7200,
     * "refresh_token":"REFRESH_TOKEN",
     * "openid":"OPENID",
     * "scope":"SCOPE"
     * }
     *
     * @return
     */
    public static JSONObject getWebAccessToken(String code) {
        String result = HttpUtil.get(WXPublicConstants.GET_WEB_ACCESSTOKEN_URL.replace("APPID", WXPublicConstants.APPID).replace("SECRET", WXPublicConstants.APPSECRET).replace("CODE", code));
        JSONObject json = JSONObject.parseObject(result);
        return json;
    }

    /**
     * 获取用户信息
     * 返回示例：
     * {
     * "openid":" OPENID",
     * "nickname": "NICKNAME",
     * "sex":"1",
     * "province":"PROVINCE",
     * "city":"CITY",
     * "country":"COUNTRY",
     * "headimgurl": "http://thirdwx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/46",
     * "privilege":[ "PRIVILEGE1" "PRIVILEGE2"  ],
     * "unionid": "o6_bmasdasdsad6_2sgVt7hMZOPfL"
     * }
     */
    public static JSONObject getUserInfo(String accessToken, String openId) {
        String result = HttpUtil.get(WXPublicConstants.GET_USERINFO_URL.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId));
        JSONObject json = JSONObject.parseObject(result);
        return json;
    }

    /**
     * 公众号获取ticket
     *
     * @param access_token
     * @return
     * @throws IOException
     */
    public static String getJsApiTicket(String access_token) {
        String url = WXPublicConstants.SIGN_TICKET_CREATE_URL.replace("ACCESS_TOKEN", access_token);
        String ticketStr = HttpUtil.get(url);
        JSONObject jsonObj = JSON.parseObject(ticketStr);
        log.info("getJsApiTicket, 获取Jsapi的ticket为：" + jsonObj);
        String ticket = jsonObj.getString("ticket");
        return ticket;
    }

    /**
     *      * 获取当前时间 yyyyMMddHHmmss   
     */
    public static String getCurrTime() {
        Date now = new Date();
        SimpleDateFormat outFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String s = outFormat.format(now);
        return s;
    }

    /**
     *  生成随机字符串   
     */

    public static String getNonceStr() {
        String currTime = getCurrTime();
        String strTime = currTime.substring(8);
        String strRandom = buildRandom(4) + "";
        return strTime + strRandom;
    }

    /**
     * 获取微信Jsapi的accessToken   
     * 这里获取的获取微信Jsapi的accessToken跟小程序以及其他的不一样   
     */
    public static String getJsApiAccessToken() {
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&&secret=APPSECRET";
        url = url.replace("APPID", WXPublicConstants.APPID).replace("APPSECRET", WXPublicConstants.APPSECRET);
        String str = HttpUtil.get(url);
        JSONObject jsonObj = JSON.parseObject(str);
        log.info("getJsApiAccessToken, 获取Jsapi的accessToken为：" + jsonObj);
        String accessToken = jsonObj.getString("access_token");
        return accessToken;
    }

    /**
     * 获取时间戳(秒)
     */

    public static String getTimestamp() {
        return String.valueOf(System.currentTimeMillis() / 1000);
    }

    /**
     * @param @return hashmap {appid,timestamp,nonceStr,signature}
     * @param @throws Exception
     * @Description: 前端jssdk页面配置需要用到的配置参数
     * @author dapengniao
     * @date 2016年3月19日 下午3:53:23
     */
    public static HashMap<String, String> jsSdkSign(String url, String timestamp, String nonce_str, String jsapi_ticket) throws Exception {
        // 注意这里参数名必须全部小写，且必须有序
        String string1 = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + nonce_str
                + "&timestamp=" + timestamp + "&url=" + url;
        MessageDigest crypt = MessageDigest.getInstance("SHA-1");
        crypt.reset();
        crypt.update(string1.getBytes("UTF-8"));
        String signature = byteToHex(crypt.digest());
        HashMap<String, String> jssdk = new HashMap<>();
        jssdk.put("appId", WXPublicConstants.APPID);
        jssdk.put("timestamp", timestamp);
        jssdk.put("nonceStr", nonce_str);
        jssdk.put("signature", signature);
        return jssdk;
    }

    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }

}