package com.bestpay.util;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;

/**
 * Created by Chenkh on 2015/11/21.
 */
public class ServerCert {
    public ServerCert() {
    }
    private String keyStore;
    private String keyStorePwd;
    private String certAlias;

    public String getKyStore() {
        return keyStore;
    }

    public void setKeyStore(String keyStore) {
        this.keyStore = keyStore;
    }

    public String getKeyStorePwd() {
        return keyStorePwd;
    }

    public void setKeyStorePwd(String keyStorePwd) {
        this.keyStorePwd = keyStorePwd;
    }

    public String getCertAlias() {
        return certAlias;
    }

    public void setCertAlias(String certAlias) {
        this.certAlias = certAlias;
    }

    /**
     * 本地证书私钥.
     */
    private static PrivateKey serverPrivateKey;

    /**
     * 本地证书.
     */
    private static X509Certificate cert;

    /**
     * 获取本地证书.
     *
     * @return 本地证书
     * @throws java.security.GeneralSecurityException 加密异常
     */
    public X509Certificate getServerCert()
            throws GeneralSecurityException {
        try {
            if (cert == null) {
                checkConfig();
                cert = CertUtils.getCertFromKeyStore(keyStore, keyStorePwd, certAlias);
            }
        } catch (IOException ex) {
            throw new GeneralSecurityException("获取证书异常", ex);
        }

        return cert;
    }

    /**
     * 本地证书私钥.
     *
     * @return Key 本地证书私钥
     * @throws java.security.GeneralSecurityException 通用安全异常
     */
    public PrivateKey getServerPrivateKey()
            throws GeneralSecurityException {
        try {
            if (serverPrivateKey == null) {
                checkConfig();
                serverPrivateKey = CertUtils.getPrivateKey(keyStore, keyStorePwd, certAlias);
            }
        } catch (IOException ex) {
            throw new GeneralSecurityException("获取私钥异常", ex);
        }

        return serverPrivateKey;
    }

    private void checkConfig() throws GeneralSecurityException {
        if (keyStore.length() <= 0) {
            throw new GeneralSecurityException("配置文件中keystore未配置");
        }
        if (certAlias.length() <= 0) {
            throw new GeneralSecurityException("配置文件中alias未配置");
        }
        if (keyStorePwd.length() <= 0) {
            throw new GeneralSecurityException("配置文件中password未配置");
        }
    }
}
