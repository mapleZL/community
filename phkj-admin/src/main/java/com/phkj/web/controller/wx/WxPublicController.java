package com.phkj.web.controller.wx;

import com.alibaba.fastjson.JSONObject;
import com.phkj.core.redis.RedisComponent;
import com.phkj.core.response.ResponseUtil;
import com.phkj.web.common.RedisKeyCommon;
import com.phkj.web.util.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author ：zl
 * @date ：Created in 2019/5/19 19:39
 * @description：验证微信URL
 * @modified By：
 * @version: 0.0.1$
 */
@Controller
@RequestMapping(value = "admin/wx")
public class WxPublicController {

    @Autowired
    RedisComponent redisComponent;

    private static Logger log = LogManager.getLogger(WxPublicController.class);

    @RequestMapping("/verifyToken")
    @ResponseBody
    public void verifyWXToken(HttpServletRequest request, HttpServletResponse response) throws AesException {
        PrintWriter out = null;
        try {
            String msgSignature = request.getParameter("signature");
            String msgTimestamp = request.getParameter("timestamp");
            String msgNonce = request.getParameter("nonce");
            String echostr = request.getParameter("echostr");
            log.info("msgSignature = " + msgSignature + ", msgTimestamp = " + msgTimestamp + ", msgNonce = " + msgNonce + ", echostr = " + echostr);
            out = response.getWriter();

            if (WXPublicUtils.checkSignature(msgSignature, msgTimestamp, msgNonce)) {
                log.info("Connect the weixin server is successful.");
                response.getWriter().write(echostr);
            }
        } catch (IOException e) {
            log.error("Failed to verify the signature!", e);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    @RequestMapping("/person")
    public String person(String code, Model model) {
        String url = "redirect:http://zjphtech.com";
        try {
            JSONObject userInfo = new JSONObject();
            if (code != null) {
                //1.通过code来换取access_token
                JSONObject json = WeChatUtil.getWebAccessToken(code);
                //获取网页授权access_token凭据
                log.info("json, " + json);
                String webAccessToken = json.getString("access_token");
                //获取用户openid
                String openid = json.getString("openid");
                //2.通过access_token和openid拉取用户信息
                userInfo = WeChatUtil.getUserInfo(webAccessToken, openid);
                String userName = userInfo.getString("nickname");
                //userName = URLEncoder.encode(userName);
                String headIcon = userInfo.getString("headimgurl");
                //获取json对象中的键值对集合
                Set<Map.Entry<String, Object>> entries = userInfo.entrySet();
                for (Map.Entry<String, Object> entry : entries) {
                    //把键值对作为属性设置到model中
                    model.addAttribute(entry.getKey(), entry.getValue());
                }
                url = "redirect:http://zjphtech.com?userName=" + userName + "&headIcon=" + headIcon;
            }
            return url;
            //return ResponseUtil.createResp("200", "ok", true, userInfo);
        } catch (Exception e) {
            log.error("获取用户微信信息异常,exception:{}", e);
            //return ResponseUtil.createResp("500", "ok", false, null);
            return url;
        }
    }

    /**
     * 获取前端面调用微信公众号js-sdk上传图片的config接口所需参数   
     */
    @RequestMapping("/getWxConfig")
    @ResponseBody
    public ResponseUtil getWxConfig(@RequestParam String url) {
        Map<String, String> map;
        try {
            String ticket;
            String accessToken = redisComponent.getRedisString(RedisKeyCommon.JS_ACCESS_TOKEN);
            if (StringUtils.isBlank(accessToken)) {
                accessToken = WeChatUtil.getJsApiAccessToken();
                redisComponent.setStringExpire(RedisKeyCommon.JS_ACCESS_TOKEN, accessToken, 7200000);
                ticket = WeChatUtil.getJsApiTicket(accessToken);
                redisComponent.setStringExpire(RedisKeyCommon.JS_API_TICKET, ticket, 7200000);
            } else {
                ticket = redisComponent.getRedisString(RedisKeyCommon.JS_API_TICKET);
            }
            String timestamp = WeChatUtil.getTimestamp();
            String nonceStr = WeChatUtil.getNonceStr();
            map = WeChatUtil.jsSdkSign(url, timestamp, nonceStr, ticket);
            return ResponseUtil.createResp("200", "ok", true, map);
        } catch (Exception e) {
            log.error("getWxConfig, 获取config接口所需参数异常", e);
            return ResponseUtil.createResp("500", "ok", false, null);
        }
    }

}
