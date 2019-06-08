package com.phkj.web.controller.wx;

import com.phkj.web.util.wx.AesException;
import com.phkj.web.util.wx.WXPublicUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

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

    @RequestMapping("/wxpublic/verify_wx_token")
    @ResponseBody
    public String verifyWXToken(HttpServletRequest request) throws AesException {
        String msgSignature = request.getParameter("signature");
        String msgTimestamp = request.getParameter("timestamp");
        String msgNonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        log.info("msgSignature = " + msgSignature + ", msgTimestamp = " + msgTimestamp + ", msgNonce = " + msgNonce + ", echostr = " + echostr);
//        if (WXPublicUtils.verifyUrl(msgSignature, msgTimestamp, msgNonce)) {
//            return echostr;
//        }
        if (WXPublicUtils.checkSignature(msgSignature, msgTimestamp, msgNonce)) {
            return echostr;
        }
        return null;
    }
}
