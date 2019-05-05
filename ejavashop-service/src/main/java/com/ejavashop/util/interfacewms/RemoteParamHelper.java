package com.ejavashop.util.interfacewms;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * 远程端口参数解析工具类
 *
 * @author liwei
 *         16/3/7 上午11:40
 */
public class RemoteParamHelper {


    /**
     * 存放md5散列值的参数名
     /*
    public static final String SIGN_PARAM_KEY = "sign_m";
    /**
     * 存放实际数据的参数名
     /*
    public static final String DATA_PARAM_KEY = "data";
    /**
     * MD5 散列附加字符串
     */
    private String md5Key = "cFfz2016lIoN100W";

    /**
     * 结果参数
     */
    private static final String RESULT_SPLITER = "__@#&__";


    //    public static final String CODE_RESULT_KEY = "code";
    //    public static final String DATA_RESULT_KEY = "data";
    //    public static final String MESSAGE_RESULT_KEY = "message";

    /**
     * json 解析对象，线程安全
     */
    public static final ObjectMapper objectMapper = new ObjectMapper();

    /*
    *//**
     * 解析参数
     *
     * @param jsonStr         参数中data的值，不包含sign内容
     * @param md5Verification true:需要md5进行散列比对 false: 不进行MD5散列值比对
     *
     * @return
     *//*
    public Map<String, Object> parseParams(String jsonStr, String sign, boolean md5Verification) throws QuException {
        Map<String, Object> params = null;
        if (StringUtils.isBlank(jsonStr)) {
            throw new ParamInvalidException(ResultCode.INVALID_REMOTE_PARAM, "jsonStr");
        }
        if (StringUtils.isBlank(sign) && md5Verification) {
            throw new ParamInvalidException(ResultCode.INVALID_REMOTE_PARAM, "sign");
        }

        //解析参数字符串
        try {
            params = objectMapper.readValue(jsonStr, Map.class);
        } catch (IOException e) {
            throw new QuException(ResultCode.INVALID_REMOTE_PARAM, e);
        }
        if (md5Verification) {
            //            verifyMd5(params, sign);
            String md5 = MD5Utils.getMD5String(jsonStr + md5Key);
            if (!StringUtils.equalsIgnoreCase(md5, sign)) {
                throw new QuException(ResultCode.MD5_VERIFICATION_FAILED);
            }

        }

        return params;
    }

    *//**
     * 组装返回数据
     *
     * @param result
     *
     * @return
     *
     * @throws QuException
     *//*
    public String makeResponseParams(Result result) throws QuException {
        if (result == null) {
            throw new ParamInvalidException(ResultCode.INVALID_PARAM, "result");
        }

        String json = null;
        try {
            json = objectMapper.writeValueAsString(result);
        } catch (IOException e) {
            throw new QuException(ResultCode.INVALID_REMOTE_PARAM, e);
        }
        String md5 = MD5Utils.getMD5String(json + md5Key);
        return json + RESULT_SPLITER + md5;

    }*/


    /**
     * 解析返回参数，并验证MD5散列值
     *
     * @param resultString
     * @param md5Verification
     *
     * @return
     *
     * @throws QuException
     */
   /* public Result parseResponseResult(String resultString, boolean md5Verification) throws QuException {
        if (StringUtils.isBlank(resultString)) {
            throw new ParamInvalidException(ResultCode.INVALID_REMOTE_PARAM, "resultString");
        }

        String[] resultParams = resultString.split(RESULT_SPLITER);
        if (resultParams == null || resultParams.length != 2) {
            throw new ParamInvalidException(ResultCode.INVALID_REMOTE_PARAM, "resultString");
        }

        if (md5Verification) {
            String md5 = MD5Utils.getMD5String(resultParams[0] + md5Key);
            if (!StringUtils.equalsIgnoreCase(md5, resultParams[1])) {
                throw new QuException(ResultCode.MD5_VERIFICATION_FAILED);
            }
        }


        try {
            return objectMapper.readValue(resultParams[0], Result.class);
        } catch (IOException e) {
            throw new QuException(ResultCode.INVALID_REMOTE_PARAM, e);
        }

    }*/


    /**
     * 组装请求参数
     *
     * @param paramMap 若无组装参数，需传size=0的map实例
     *
     * @return 返回请求参数Map, data:实际参数值;sign_m:md5散列后的值
     *
     * @throws QuException
     */
    public Map<String, String> makeRequestDataParam(Map<String, Object> paramMap,String url){
        /*if (paramMap == null) {
            //throw new ParamInvalidException(ResultCode.INVALID_REMOTE_PARAM, "paramMap");
        }*/

        TreeMap<String, Object> treeMap = null;
        if (paramMap instanceof TreeMap) {
            treeMap = (TreeMap) paramMap;
        } else {
            treeMap = new TreeMap<String, Object>(paramMap);
        }

        String data = null;
        try {
            data = objectMapper.writeValueAsString(treeMap);
        } catch (IOException e) {
        	e.printStackTrace();
            //throw new QuException(ResultCode.INVALID_REMOTE_PARAM, e);
        }
        /*StringBuilder toSign = new StringBuilder();

        toSign.append(data).append(this.md5Key);
        String sign = MD5Utils.getMD5String(toSign.toString());*/

        Map<String, String> result = new HashMap<String, String>(2, 1.0f);
        result.put("data", data);
        result.put("api", url);
        return result;
    }






    /**
     * MD5散列值验证
     *
     * @param params
     * @param oriMD5Str
     *
     * @throws QuException
     */
    /*private void verifyMd5(Map<String, Object> params, String oriMD5Str) throws QuException {
        if (params == null || params.size() == 0) {
            throw new ParamInvalidException(ResultCode.INVALID_REMOTE_PARAM, "params");
        }

        if (StringUtils.isBlank(oriMD5Str)) {
            throw new ParamInvalidException(ResultCode.INVALID_REMOTE_PARAM, "oriMD5Str");
        }

        //遍历生成字符串
        params.remove(SIGN_PARAM_KEY);


        //sort params key by alpha-beta order
        TreeMap<String, Object> treeMap = null;
        if (params instanceof TreeMap) {
            treeMap = (TreeMap) params;
        } else {
            treeMap = new TreeMap<String, Object>(params);
        }

        String actualSign = makeMd5Value(treeMap);
        if (!StringUtils.equalsIgnoreCase(actualSign, oriMD5Str)) {
            throw new QuException(ResultCode.MD5_VERIFICATION_FAILED);
        }
    }

    *//**
     * 生成MD5散列值
     *
     * @param treeMap
     *
     * @return
     *
     * @throws QuException
     *//*
    private String makeMd5Value(TreeMap<String, Object> treeMap) throws QuException {
        StringBuilder builder = new StringBuilder();
        try {
            builder.append(objectMapper.writeValueAsString(treeMap)).append(this.md5Key);
        } catch (IOException e) {
            throw new QuException(ResultCode.INVALID_REMOTE_PARAM, e);
        }

        return MD5Utils.getMD5String(builder.toString());
    }


    public String getMd5Key() {
        return md5Key;
    }

    public void setMd5Key(String md5Key) {
        this.md5Key = md5Key;
    }*/
}
