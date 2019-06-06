package com.phkj.web.util.wx;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * @author ：zl
 * @date ：Created in 2019/5/19 19:44
 * @description：微信校验
 * @modified By：
 * @version: 0.0.1$
 */
public class WXPublicUtils {

    private static Logger log = LogManager.getLogger(WXPublicUtils.class);

    /**
     * 验证Token
     *
     * @param msgSignature 签名串，对应URL参数的signature
     * @param timeStamp    时间戳，对应URL参数的timestamp
     * @param nonce        随机串，对应URL参数的nonce
     * @return 是否为安全签名
     * @throws AesException 执行失败，请查看该异常的错误码和具体的错误信息
     */
    public static boolean verifyUrl(String msgSignature, String timeStamp, String nonce)
            throws AesException {
        // 这里的 WXPublicConstants.TOKEN 填写你自己设置的Token就可以了
        String signature = SHA1.getSHA1(WXPublicConstants.TOKEN, timeStamp, nonce);
        if (!signature.equals(msgSignature)) {
            log.error("微信验证失败," + "signature: " + signature + ", nonce: " + nonce);
            throw new AesException(AesException.ValidateSignatureError);
        }
        return true;
    }
}