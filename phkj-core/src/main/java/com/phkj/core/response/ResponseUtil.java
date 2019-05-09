package com.phkj.core.response;

import com.alibaba.fastjson.JSONObject;

/**
 * @author ：zl
 * @date ：Created in 2019/5/9 19:58
 * @description：响应数据
 * @modified By：
 * @version: 0.0.1$
 */
public class ResponseUtil {

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
    public static String responseJSONString(String code, String msg, Object data) {
        JSONObject object = new JSONObject();
        object.put(CommonFields.CODE, code);
        object.put(CommonFields.MSG, msg);
        object.put(CommonFields.DATA, data);
        return object.toJSONString();
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
    public static String responseJSONString(Object data) {
        JSONObject object = new JSONObject();
        object.put(CommonFields.CODE, "200");
        object.put(CommonFields.SUCCESS, true);
        object.put(CommonFields.MSG, "ok");
        object.put(CommonFields.DATA, data);
        return object.toJSONString();
    }
}
