package com.phkj.web.controller.wx;

import com.phkj.web.util.wx.AesException;
import com.phkj.web.util.wx.WXPublicUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

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
//        if (WXPublicUtils.verifyUrl(msgSignature, msgTimestamp, msgNonce)) {
//            return echostr;
//        }
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
}
