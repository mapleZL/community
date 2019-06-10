package com.phkj.web.controller.wx;

import com.alibaba.fastjson.JSONObject;
import com.phkj.web.util.WeChatUtil;
import com.phkj.web.util.wx.AesException;
import com.phkj.web.util.wx.WXPublicUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
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
    @ResponseBody
    public String person(String code, Model model) {
        JSONObject userInfo = new JSONObject();
        if (code != null) {
            //1.通过code来换取access_token
            JSONObject json = WeChatUtil.getWebAccessToken(code);
            //获取网页授权access_token凭据
            String webAccessToken = json.getString("access_token");
            log.info("webAccessToken, " + webAccessToken);
            //获取用户openid
            String openid = json.getString("openid");
            log.info("openid, " + openid);
            //2.通过access_token和openid拉取用户信息
            userInfo = WeChatUtil.getUserInfo(webAccessToken, openid);
            log.info("userInfo, " + userInfo);
            //获取json对象中的键值对集合
            Set<Map.Entry<String, Object>> entries = userInfo.entrySet();
            for (Map.Entry<String, Object> entry : entries) {
                //把键值对作为属性设置到model中
                model.addAttribute(entry.getKey(), entry.getValue());
            }
        }
        return userInfo.toJSONString();
    }
}
