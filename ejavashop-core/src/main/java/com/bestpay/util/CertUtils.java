package com.bestpay.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import com.ejavashop.core.EjavashopConfig;

import sun.misc.BASE64Decoder;

/**
 * Created by Chenkh on 2015/11/21.
 */
public class CertUtils {
    private CertUtils() {
    }

    /**
     * 比较证书指纹以及证书是否匹配
     *
     * @param thumbprint 证书指纹
     * @param base64Cert 证书
     * @return 匹配结果
     * @throws java.security.GeneralSecurityException 加密异常
     */
    public static boolean compare(String thumbprint, String base64Cert) throws GeneralSecurityException {
        X509Certificate cert = base64StrToCert(base64Cert);
        return thumbprint.equals(getThumbprint(cert));
    }

    /**
     * 获取证书指纹
     *
     * @param cert
     * @return
     * @throws java.security.GeneralSecurityException 加密异常
     */
    public static String getThumbprint(X509Certificate cert) throws GeneralSecurityException {
        MessageDigest md = MessageDigest.getInstance("SHA");
        byte ab[] = md.digest(cert.getEncoded());
        return Base64Utils.encode(ab);
    }

    /**
     * 根据对端证书Base64串生成证书.
     *
     * @param base64Cert Base64编码的证书字符串
     * @return 证书对象
     * @throws java.security.GeneralSecurityException 加密异常
     */
    public static X509Certificate base64StrToCert(String base64Cert)
            throws GeneralSecurityException {
        try {
            CertificateFactory factory = CertificateFactory.getInstance("X.509");
            ByteArrayInputStream streamCert = new ByteArrayInputStream(
                    new BASE64Decoder().decodeBuffer(base64Cert));

            X509Certificate cert = (X509Certificate) factory.generateCertificate(streamCert);
            if (cert == null) {
                throw new GeneralSecurityException("将cer从base64转换为对象失败");
            }
            return cert;
        } catch (IOException ex) {
            throw new GeneralSecurityException("将cer从base64转换为对象失败", ex);
        }
    }

    /*
    * X509Certificate转化成base64编码
    */
    public static String certToBase64Str(
            X509Certificate cert) throws CertificateEncodingException {
        return Base64Utils.encode(cert.getEncoded());
    }

    /**
     * 获取服务器证书
     */
    public static X509Certificate getCertFromKeyStore(String keyStorePath, String keyStorePwd, String alias)
            throws IOException, GeneralSecurityException {
        KeyStore ks = getKeyStore(keyStorePath, keyStorePwd);
        return (X509Certificate) ks.getCertificate(alias);
    }

    public static PrivateKey getPrivateKey(String keyStorePath, String keyStorePwd, String alias) throws GeneralSecurityException,
            IOException {
        KeyStore ks = getKeyStore(keyStorePath, keyStorePwd);
        PrivateKey pk = (PrivateKey) ks.getKey(alias, keyStorePwd.toCharArray());
        return pk;
    }

    public static String getCertStringFromCer(String cerFilePath) throws GeneralSecurityException {
        X509Certificate cert = getCertFromCer(cerFilePath);
        String base64Str = null;
            base64Str = certToBase64Str(cert);
        return base64Str;
    }

    public static X509Certificate getCertFromCer(String cerFilePath) throws GeneralSecurityException {
        CertificateFactory f = CertificateFactory.getInstance("X.509");
        File file = getCertFile(cerFilePath);
        InputStream fs = null;
        try {
            fs = new FileInputStream(file);
            X509Certificate certificate = (X509Certificate) f
                    .generateCertificate(fs);
            return certificate;
        } catch (IOException ex) {
            throw new GeneralSecurityException("获取证书异常");
        } finally {
            try {
                if (fs != null) {
                    fs.close();
                }
            } catch (Exception ex) {
                //ignore..
            }
        }
    }

    private static File getCertFile(String path) throws GeneralSecurityException {
//        System.out.println(CertUtils.class.getClassLoader().getResource("/"));
//        File file = new File(CertUtils.class.getClassLoader().getResource(path).getFile());
//        String filePath = CertUtils.class.getClassLoader().getResource("/")+path;
//        File file = new File(filePath);
        File file = new File(EjavashopConfig.BESTPAY_CONFIG_PATH);
        if (!file.exists()) {
            throw new GeneralSecurityException("证书文件不存在：" + EjavashopConfig.BESTPAY_CONFIG_PATH);
        }
        return file;
    }

    private static KeyStore getKeyStore(String keyStorePath, String keyStorePwd) throws GeneralSecurityException {
        File file = getCertFile(keyStorePath);

        KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
        InputStream fs = null;
        try {
            fs = new FileInputStream(file);
            ks.load(fs, keyStorePwd.toCharArray());
        } catch (IOException ex) {
            throw new GeneralSecurityException("获取证书异常");
        } finally {
            try {
                if (fs != null) {
                    fs.close();
                }
            } catch (Exception ex) {
                //ignore..
            }
        }
        return ks;
    }
}
