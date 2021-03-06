package com.phkj.entity.member;

import java.io.Serializable;

/**
 * @author ：zl
 * @date ：Created in 2019/5/15 15:37
 * @description：接收参数实体
 * @modified By：
 * @version: 0.0.1$
 */
public class MemberParam implements Serializable {
    /**
     * Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 1L;
    /**
     * 手机号
     */
    private String phoneNum;
    /**
     * 密码
     */
    private String password;
    /**
     * 短信验证码
     */
    private String smsCode;
    /**
     * 微信code码
     */
    private String wxCode;
    /**
     * 名称
     */
    private String userName;
    /**
     * 头像
     */
    private String headIcon;

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    public String getWxCode() {
        return wxCode;
    }

    public void setWxCode(String wxCode) {
        this.wxCode = wxCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getHeadIcon() {
        return headIcon;
    }

    public void setHeadIcon(String headIcon) {
        this.headIcon = headIcon;
    }

    @Override
    public String toString() {
        return "MemberParam{" +
                "phoneNum='" + phoneNum + '\'' +
                ", password='" + password + '\'' +
                ", smsCode='" + smsCode + '\'' +
                ", wxCode='" + wxCode + '\'' +
                ", userName='" + userName + '\'' +
                ", headIcon='" + headIcon + '\'' +
                '}';
    }
}
