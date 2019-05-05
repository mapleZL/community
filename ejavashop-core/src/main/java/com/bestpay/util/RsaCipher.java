package com.bestpay.util;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.cert.X509Certificate;

/**
 * RSA公钥/私钥/签名工具�?
 * Author: Terence
 * Date:   2014/8/11
 * Time:   10:35
 */
public final class RsaCipher {
    /**
     * 算法/工作模式/填充方式.
     */
    public static final String RSA_ALGORITHM = "RSA/ECB/PKCS1Padding";
    /**
     * 签名算法.
     */
    public static final String SIGNATURE_ALGORITHM = "SHA1withRSA";

    /**
     * 私有构�?�函�?.
     */
    private RsaCipher() {
    }

    /**
     * 用私钥对信息生成数字签名.
     *
     * @param data     待签名信�?
     * @param privateK 私钥
     * @return 数字签名
     * @throws GeneralSecurityException 加密异常
     */
    public static byte[] sign(byte[] data, PrivateKey privateK)
            throws GeneralSecurityException {
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initSign(privateK);
        signature.update(data);

        return signature.sign();
    }

    /**
     * 验证数字签名.
     *
     * @param data        明文数据
     * @param sBase64Cert Base64格式的数字证�?
     * @param sign        签名数据
     * @return 是否验签通过
     * @throws GeneralSecurityException 加密错误
     */
    public static boolean verify(byte[] data, String sBase64Cert, byte[] sign)
            throws GeneralSecurityException {
        // 将cer从base64转换为对�?
        X509Certificate cerObj = CertUtils.base64StrToCert(sBase64Cert);

        PublicKey publicKey = cerObj.getPublicKey();
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initVerify(publicKey);
        signature.update(data);
        return signature.verify(sign);
    }

    public static boolean verify(String data, String sBase64Cert, String sign)
            throws GeneralSecurityException {
        return verify(data.getBytes(), sBase64Cert, Base64Utils.decode(sign));
    }
    public static boolean verifyUtf8(String data, String sBase64Cert, String sign)
            throws GeneralSecurityException {
        try {
            byte[] bytes = data.getBytes("UTF-8");
            return verify(bytes, sBase64Cert, Base64Utils.decode(sign));
        } catch (UnsupportedEncodingException e) {
            throw new GeneralSecurityException("获取utf-8编码异常", e);
        }
    }

    /**
     * 根据RSA算法加解�?.
     *
     * @param data 待加/解密数据
     * @param key  公钥/私钥
     * @param mode 工作模式
     * @return byte[] �?/解密后的数据
     * @throws GeneralSecurityException 加密异常
     */
    public static byte[] enDecryptByRsa(byte[] data, Key key, int mode)
            throws GeneralSecurityException {
        Provider provider = new BouncyCastleProvider();
        ByteArrayOutputStream outputStream = null;
        try {
            outputStream = new ByteArrayOutputStream();
            // 取得加密�?
            Cipher cp = Cipher.getInstance(RSA_ALGORITHM, provider);
            // 设置加密/解密模式
            cp.init(mode, key);
            // RSA算法必须采用分块运算
            int blockSize = cp.getBlockSize(); // 取得RSA加密的块的大小
            // 确定要运算的次数(加密块的个数)
            int blocksNum = (int) Math.ceil((double) data.length / blockSize);

            int calcSize = blockSize; // 每次参与运算的块大小
            byte[] buffer = null; // 每次运算的缓存

            // 对每块数据分别运算
            for (int i = 0; i < blocksNum; i++) {
                if (i == (blocksNum - 1)) {
                    calcSize = data.length - i * blockSize;
                }

                buffer = cp.doFinal(data, i * blockSize, calcSize);
                try {
                    outputStream.write(buffer);
                } catch (IOException e) {
                    throw new GeneralSecurityException("RSA加密/解密时出现异常", e);
                }
            }
            return outputStream.toByteArray();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    //ignore..
                }
            }
        }
    }

    //获取商户自己的证书私钥
    public static PrivateKey getPrivateKey()throws Exception {
        ServerCert serverCert = new ServerCert();
        serverCert.setCertAlias("server");
        serverCert.setKeyStorePwd("123456*");
        serverCert.setKeyStore("bestpay/cert/server.jks");
        return serverCert.getServerPrivateKey();
    }
    //获取支付公司公钥
    public static PublicKey getBestPayPublicKey()throws Exception {
        return CertUtils.base64StrToCert(Constants.PUBLIC_CERT).getPublicKey();
    }
}