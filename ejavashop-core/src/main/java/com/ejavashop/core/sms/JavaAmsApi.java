package com.ejavashop.core.sms;

/**
 * Created by bingone on 15/12/16.
 */

    import org.apache.http.HttpEntity;
    import org.apache.http.NameValuePair;
    import org.apache.http.client.entity.UrlEncodedFormEntity;
    import org.apache.http.client.methods.CloseableHttpResponse;
    import org.apache.http.client.methods.HttpPost;
    import org.apache.http.impl.client.CloseableHttpClient;
    import org.apache.http.impl.client.HttpClients;
    import org.apache.http.message.BasicNameValuePair;
    import org.apache.http.util.EntityUtils;
    import java.io.IOException;
    import java.net.URISyntaxException;
    import java.net.URLEncoder;
    import java.util.ArrayList;
    import java.util.HashMap;
    import java.util.List;
    import java.util.Map;

/**
 * 短信http接口的java代码调用示例
 * 基于Apache HttpClient 4.3
 *
 * @author songchao
 * @since 2015-04-03
 */

public class JavaAmsApi {

    //查账户信息的http地址
    private static String URI_GET_USER_INFO = "https://sms.yunpian.com/v1/user/get.json";

    //智能匹配模板发送接口的http地址
    private static String URI_SEND_SMS = "https://sms.yunpian.com/v1/sms/send.json";

    //模板发送接口的http地址
    private static String URI_TPL_SEND_SMS = "https://sms.yunpian.com/v1/sms/tpl_send.json";

    //发送语音验证码接口的http地址
    private static String URI_SEND_VOICE = "https://voice.yunpian.com/v1/voice/send.json";
    
    private static String APIKEY = "2c7125f26035b0a038b6261d28fa118e";

    //编码格式。发送编码格式统一用UTF-8
    private static String ENCODING = "UTF-8";

    public static void main(String[] args) throws IOException, URISyntaxException {

        //修改为您要发送的手机号
        String mobile = "13816524067";

        /**************** 查账户信息调用示例 *****************/
        System.out.println(JavaAmsApi.getUserInfo(APIKEY));

        /**************** 使用智能匹配模板接口发短信(推荐) *****************/
        //设置您要发送的内容(内容必须和某个模板匹配。以下例子匹配的是系统提供的1号模板）
        String text = "【大袜网】您的验证码是1234，袜业全产业链一站式服务平台。";
        //发短信调用示例
        System.out.println(JavaAmsApi.sendSms(APIKEY, text, mobile));

        /**************** 使用指定模板接口发短信(不推荐，建议使用智能匹配模板接口) *****************/
//        //设置模板ID，如使用1号模板:【#company#】您的验证码是#code#
//        long tpl_id = 1;
//        //设置对应的模板变量值
//
//        String tpl_value = URLEncoder.encode("#code#",ENCODING) +"="
//            + URLEncoder.encode("1234", ENCODING) + "&"
//            + URLEncoder.encode("#company#",ENCODING) + "="
//            + URLEncoder.encode("云片网",ENCODING);
//        //模板发送的调用示例
//        System.out.println(tpl_value);
//        System.out.println(JavaAmsApi.tplSendSms(apikey, tpl_id, tpl_value, mobile));
//
//        /**************** 使用接口发语音验证码 *****************/
//        String code = "1234";
        //System.out.println(JavaAmsApi.sendVoice(apikey, mobile ,code));
    }

    /**
     * 取账户信息
     *
     * @return json格式字符串
     * @throws java.io.IOException
     */

    public static String getUserInfo(String apikey) throws IOException, URISyntaxException {
        Map<String, String> params = new HashMap<String, String>();
        params.put("apikey", apikey);
        return post(URI_GET_USER_INFO, params);
    }

    
    public static String sendResetCode(String content, String mobile) throws IOException {
        String text = content;
        return sendSms(APIKEY, text, mobile);
    }

    public static String sendValidCode(String code, String mobile) throws IOException {
        String text = "【大袜网】您的验证码是" + code + "，袜业全产业链一站式服务平台。";
        return sendSms(APIKEY, text, mobile);
    }

    /**
     * 智能匹配模板接口发短信
     *
     * @param apikey apikey
     * @param text   　短信内容
     * @param mobile 　接受的手机号
     * @return json格式字符串
     * @throws IOException
     */

    public static String sendSms(String apikey, String text, String mobile) throws IOException {
        Map<String, String> params = new HashMap<String, String>();
        params.put("apikey", apikey);
        params.put("text", text);
        params.put("mobile", mobile);
        return post(URI_SEND_SMS, params);
    }

    /**
     * 通过模板发送短信(不推荐)
     *
     * @param apikey    apikey
     * @param tpl_id    　模板id
     * @param tpl_value 　模板变量值
     * @param mobile    　接受的手机号
     * @return json格式字符串
     * @throws IOException
     */

    public static String tplSendSms(String apikey, long tpl_id, String tpl_value, String mobile) throws IOException {
        Map<String, String> params = new HashMap<String, String>();
        params.put("apikey", apikey);
        params.put("tpl_id", String.valueOf(tpl_id));
        params.put("tpl_value", tpl_value);
        params.put("mobile", mobile);
        return post(URI_TPL_SEND_SMS, params);
    }

    /**
     * 通过接口发送语音验证码
     * @param apikey apikey
     * @param mobile 接收的手机号
     * @param code   验证码
     * @return
     */

    public static String sendVoice(String apikey, String mobile, String code) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("apikey", apikey);
        params.put("mobile", mobile);
        params.put("code", code);
        return post(URI_SEND_VOICE, params);
    }

    /**
     * 基于HttpClient 4.3的通用POST方法
     *
     * @param url       提交的URL
     * @param paramsMap 提交<参数，值>Map
     * @return 提交响应
     */

    public static String post(String url, Map<String, String> paramsMap) {
        CloseableHttpClient client = HttpClients.createDefault();
        String responseText = "";
        CloseableHttpResponse response = null;
        try {
            HttpPost method = new HttpPost(url);
            if (paramsMap != null) {
                List<NameValuePair> paramList = new ArrayList<NameValuePair>();
                for (Map.Entry<String, String> param : paramsMap.entrySet()) {
                    NameValuePair pair = new BasicNameValuePair(param.getKey(), param.getValue());
                    paramList.add(pair);
                }
                method.setEntity(new UrlEncodedFormEntity(paramList, ENCODING));
            }
            response = client.execute(method);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                responseText = EntityUtils.toString(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return responseText;
    }
}

