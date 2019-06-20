package com.phkj.core;

/**
 * @author ：zl
 * @date ：Created in 2019/4/16 10:50
 * @description：响应数据枚举
 * @modified By：
 * @version: 0.0.1$
 */
public enum ResponseStateEnum {

    STATUS_OK("200", "ok"),
    PARAM_EMPTY("101", "参数错误"),
    FILE_ERROR("103", "文件解析异常"),
    STATUS_SERVER_ERROR("500", "服务器异常"),
    STATUS_SMS_CODE_ERROR("102","验证码错误");

    private String code;
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    ResponseStateEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }}