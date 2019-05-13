package com.phkj.core.sms;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5工具类
 */
public class MD5Util {
    private MD5Util() {
        throw new UnsupportedOperationException("MD5Util cannot be instantiated");
    }

    public static String getMD5(String str) {
        if (str == null || str.isEmpty()) {
            return "";
        }

        MessageDigest messageDigest = null;

        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(str.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        if (messageDigest == null) {
            return "";
        }

        byte[] byteArray = messageDigest.digest();
        StringBuilder md5StrBuff = new StringBuilder();

        for (byte bt : byteArray) {
            if (Integer.toHexString(0xFF & bt).length() == 1)
                md5StrBuff.append("0").append(Integer.toHexString(0xFF & bt));
            else
                md5StrBuff.append(Integer.toHexString(0xFF & bt));
        }

        return md5StrBuff.toString();
    }

}
