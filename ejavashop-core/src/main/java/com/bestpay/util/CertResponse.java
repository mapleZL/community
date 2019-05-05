package com.bestpay.util;

import java.io.Serializable;
import java.util.Map;

/**
 * 翼支付前置调用返回的加签对象
 */
public class CertResponse implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String sign;
    private Map data;

    public Map getData() {
        return this.data;
    }

    public void setData(Map data) {
        this.data = data;
    }

    public String getSign() {
        return this.sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}