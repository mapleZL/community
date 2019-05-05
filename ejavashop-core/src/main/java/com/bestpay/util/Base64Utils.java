package com.bestpay.util;

import org.bouncycastle.util.encoders.Base64;

/**
 * Created by Chenkh on 2015/11/21.
 */
public final class Base64Utils {
    private Base64Utils() {
    }

    /**
     * <p>
     * BASE64瀛楃涓茶В鐮佷负浜岃繘鍒舵暟鎹�
     * </p>
     *
     * @param base64
     * @return
     * @throws Exception
     */
    public static byte[] decode(String base64) {
        return Base64.decode(base64.getBytes());
    }

    /**
     * <p>
     * 浜岃繘鍒舵暟鎹紪鐮佷负BASE64瀛楃涓�
     * </p>
     *
     * @param bytes
     * @return
     * @throws Exception
     */
    public static String encode(byte[] bytes) {
        return new String(Base64.encode(bytes));
    }
}