package com.phkj.entity.seller;

import java.io.Serializable;

/**
 * @author ：zl
 * @date ：Created in 2019/6/9 0:31
 * @description：
 * @modified By：
 * @version: $
 */
public class StAppletSellerVO implements Serializable {
    private java.lang.String     sellerName;
    private java.lang.String     sellerLogo;
    private java.lang.String     phone;

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getSellerLogo() {
        return sellerLogo;
    }

    public void setSellerLogo(String sellerLogo) {
        this.sellerLogo = sellerLogo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
