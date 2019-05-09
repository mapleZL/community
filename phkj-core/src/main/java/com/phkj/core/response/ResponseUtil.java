package com.phkj.core.response;


import java.io.Serializable;

/**
 * @author ：zl
 * @date ：Created in 2019/5/9 19:58
 * @description：响应数据
 * @modified By：
 * @version: 0.0.1$
 */
public class ResponseUtil implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 3765720967319047788L;

    private String code;
    private String msg;
    private boolean success;
    private Object data;

    public ResponseUtil() {
    }

    public ResponseUtil(String code, String msg, boolean success, Object data) {
        this.code = code;
        this.msg = msg;
        this.success = success;
        this.data = data;
    }

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

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    /**
     * create by: zl
     * description: 返回数据
     * create time:
     *
     * @return
     * @Param: code
     * @Param: msg
     * @Param: data
     */
    public static ResponseUtil createResp(String code, String msg, boolean success, Object data) {
        return new ResponseUtil(code, msg, success, data);
    }

}
