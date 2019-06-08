package com.phkj.web.util.wx;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.security.MessageDigest;
import java.util.Arrays;

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

    /**
     * create by: zl
     * description: 校验token
     * create time:
     *
     * @return
     * @Param: signature
     * @Param: timestamp
     * @Param: nonce
     */
    public static boolean checkSignature(String signature, String timestamp, String nonce) {

        //1.定义数组存放tooken，timestamp,nonce
        String[] arr = {WXPublicConstants.TOKEN, timestamp, nonce};
        //2.对数组进行排序
        Arrays.sort(arr);
        //3.生成字符串
        StringBuffer sb = new StringBuffer();
        for (String s : arr) {
            sb.append(s);
        }
        //4.sha1加密,网上均有现成代码
        String temp = getSha1(sb.toString());
        log.info("加密后的签名为：" + temp);
        //5.将加密后的字符串，与微信传来的加密签名比较，返回结果
        return temp.equals(signature);
    }

    /**
     * create by: zl
     * description: 加密
     * create time:
     *
     * @return
     * @Param: str
     */
    public static String getSha1(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("SHA1");
            mdTemp.update(str.getBytes("UTF-8"));
            byte[] md = mdTemp.digest();
            int j = md.length;
            char buf[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                buf[k++] = hexDigits[byte0 >>> 4 & 0xf];
                buf[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(buf);
        } catch (Exception e) {
            log.error("getSha1, sha1加密失败", e);
            return null;

        }

    }
}