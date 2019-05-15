package com.phkj.web.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.util.Arrays;
import java.util.HashMap;

public class WXBiz {
    private final static String hostUrl = "https://api.weixin.qq.com/sns/jscode2session";
    private final static String appid = "wx3ec96b7fb94e38fa";
    private final static String appsecret = "29e0f0e85c8135e18193063d3f2fb04c";

    // 算法名称
    final String KEY_ALGORITHM = "AES";
    // 加解密算法/模式/填充方式
    final String algorithmStr = "AES/CBC/PKCS7Padding";

    private Key key;
    private Cipher cipher;

    Logger logger = LoggerFactory.getLogger(WXBiz.class);

    public void init(byte[] keyBytes) {
        // 如果密钥不足16位，那么就补足. 这个if 中的内容很重要
        int base = 16;
        if (keyBytes.length % base != 0) {
            int groups = keyBytes.length / base + (keyBytes.length % base != 0 ? 1 : 0);
            byte[] temp = new byte[groups * base];
            Arrays.fill(temp, (byte) 0);
            System.arraycopy(keyBytes, 0, temp, 0, keyBytes.length);
            keyBytes = temp;
        }
        // 初始化
        Security.addProvider(new BouncyCastleProvider());
        // 转化成JAVA的密钥格式
        key = new SecretKeySpec(keyBytes, KEY_ALGORITHM);
        try {
            // 初始化cipher
            cipher = Cipher.getInstance(algorithmStr, "BC");
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 解密方法
     *
     * @param
     * @param
     * @return
     */
    public byte[] decrypt(String encryptedDataStr, String keyBytesStr, String ivStr) {
        byte[] encryptedText = null;
        byte[] encryptedData;
        byte[] sessionkey;
        byte[] iv;

        try {
            sessionkey = Base64.decodeBase64(keyBytesStr);
            encryptedData = Base64.decodeBase64(encryptedDataStr);
            iv = Base64.decodeBase64(ivStr);
            init(sessionkey);
            cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv));
            encryptedText = cipher.doFinal(encryptedData);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return encryptedText;
    }

    private String getWXValue(String wx_code) {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("appid", appid);
        params.put("secret", appsecret);
        params.put("js_code", wx_code);
        params.put("grant_type", "authorization_code");
        return HttpUtil.get(hostUrl, params);//https://api.weixin.qq.com/sns/jscode2session?appid='+d.appid+'&secret='+d.secret+'&js_code='+res.code+'&grant_type=authorization_code
    }

    /**
     * 获取微信unionid
     */
    public String getWXUnionId(String code, String encryptedData, String iv) throws UnsupportedEncodingException {
        try {
            String value = getWXValue(code);
            logger.info("getWXUnionId,value:{}", value);
            JSONObject jsonObject = JSON.parseObject(value);
            if (jsonObject != null) {
                String unionId = jsonObject.getString("unionid");
                if (StringUtils.isBlank(unionId)
                        && jsonObject.containsKey("session_key")) {
                    String sessionKey = jsonObject.getString("session_key");
                    String wxDecryptData = new String(decrypt(encryptedData, sessionKey, iv), "UTF-8");
                    unionId = JSONObject.parseObject(wxDecryptData).getString("unionId");
                }
                return unionId;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("getWXUnionId null,code:{}", code);
        return null;
    }


//    public static void main(String[] args) throws UnsupportedEncodingException {
//
//
//        WXBiz d = new WXBiz();
//        String wxResult = d.getWXValue("011OlWgd0M04TA13cajd07Ibhd0OlWgi");
//        String content = "kCDK1Suj+FfcuXMVsQKQHe8C2JFh822EuQnPJKMGk+jRanwS2GL5ApV1eMD8D25hLalQabEOjA7j1NEjE98GLDRZDvqXyP95IRIyfRCsvvbK0UXKzlHpKfVXxftXpYdIB/P5chsrKgaMqFCuxOdC1+kAFUFfAzPEt1d0fJM4z9yaGPCjuFRNsar/ImpKBlA6W4fUmKhdpAQhOlqNorrv5irFcGAz8Fqi7umqu60o14x4VQxEAzI75V0cpbZNPsH3dG/5e/maw4jeoHKkKuUm5vJ2er558dIq/7fDGXcsXtpR8SfeMiD5YWzqlWOo66p9TgU3nL1Ksr85KYLYWqL/MX8gHdEEKpLaho7SIY8IolG0FDpVWKDE3hYpmOYCjozRiEBEiry8Rmhlx/dUSBFYwLuRLXDse0G1cN9SmeNqcsChsXlKOJDkYPHbeIkmeQFqK1rZVEGDsLaamUgwiSYeTIedtIdxHQEKCgpv8UoPGxShFRtGDgoHPySCwIGt70haCLdiJ+PZ28wEdb/AfL6w1A==";
//        String key = JSON.parseObject(wxResult).getString("session_key");
//        String iv = "C+ZCNgtK3Ry1z40S5GxA2g==";
//        byte[] result = d.decrypt(content, key, iv);
//        System.out.println(new String(result, "UTF-8"));
//    }
}
