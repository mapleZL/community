package com.phkj.web.controller.sms;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.openservices.log.http.client.ClientException;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.phkj.core.ResponseStateEnum;
import com.phkj.core.redis.RedisComponent;
import com.phkj.core.response.ResponseUtil;
import com.phkj.core.sms.Common;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 申请短信验证码
 */

@Controller
@RequestMapping(value = "admin/sms")
public class SmsController {

    Logger logger = LoggerFactory.getLogger(SmsController.class);

    //产品名称:云通信短信API产品,开发者无需替换
    static final String product = "Dysmsapi";
    //产品域名,开发者无需替换
    static final String domain = "dysmsapi.aliyuncs.com";
    //短信验证码过期时间
    public static final int SMS_CODE_EXPIRE_TIME = 60 * 1000;//短信验证码1分钟过期

    // 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
    static final String accessKeyId = "LTAIbUMpy6LTJi0I";
    static final String accessKeySecret = "Bp6Gz905HLRvufr2P53eh6zx90HllM";

    static final String signName = "仆汇科技";
    static final String templateCode = "SMS_167735160";

    @Autowired
    RedisComponent redisComponent;

    /**
     * 申请短信验证码
     *
     * @param phoneNum
     * @return
     */
    @RequestMapping(value = {"/code"}, method = {RequestMethod.GET})
    @ResponseBody
    public ResponseUtil getSmsCode(String phoneNum) {
        return sendSms(phoneNum);
    }


    /**
     * 发送验证码
     *
     * @param phoneNum 手机号
     * @return
     */
    private ResponseUtil sendSms(String phoneNum) throws ClientException {
        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        try {
            //初始化acsClient,暂不支持region化
            IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
            IAcsClient acsClient = new DefaultAcsClient(profile);

            //组装请求对象-具体描述见控制台-文档部分内容
            SendSmsRequest request = new SendSmsRequest();
            //必填:待发送手机号
            request.setPhoneNumbers(phoneNum);
            //必填:短信签名-可在短信控制台中找到
            request.setSignName(signName);
            //必填:短信模板-可在短信控制台中找到
            request.setTemplateCode(templateCode);
            //随机生成验证码
            String smsCode = Common.getRandomCode();
            JSONObject smsCodeObject = new JSONObject();
            smsCodeObject.put("code", smsCode);
            request.setTemplateParam(smsCodeObject.toJSONString());
            //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
            //request.setSmsUpExtendCode("90997");
            //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
            //request.setOutId("yourOutId");
            //hint 此处可能会抛出异常，注意catch
            SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
            System.out.println("Code=" + sendSmsResponse.getCode());
            System.out.println("Message=" + sendSmsResponse.getMessage());
            String responseCode = sendSmsResponse.getCode();
            String responseMessage = sendSmsResponse.getMessage();
            if (responseCode != null && responseCode.equalsIgnoreCase("ok")) {
                //短信验证码保存redis
                redisComponent.setStringExpire(phoneNum, smsCode, SMS_CODE_EXPIRE_TIME);
                return ResponseUtil.createResp(ResponseStateEnum.STATUS_OK.getCode(), ResponseStateEnum.STATUS_OK.getMsg(), true, smsCode);
            } else {
                return ResponseUtil.createResp(responseCode, responseMessage, true, smsCode);
            }
        } catch (com.aliyuncs.exceptions.ClientException e) {
            logger.error("获取验证码异常,exception:{}", e);
            return ResponseUtil.createResp(ResponseStateEnum.STATUS_SERVER_ERROR.getCode(), ResponseStateEnum.STATUS_SERVER_ERROR.getMsg(), false, null);
        }
    }


    /**
     * 校验验证码是否正确
     *
     * @param request
     * @return
     */
    @RequestMapping(value = {"/check/code"}, method = {RequestMethod.POST})
    @ResponseBody
    public ResponseUtil checkSmsCode(@RequestBody String request) {
        JSONObject object = JSON.parseObject(request);
        String phone_num = object.getString("phoneNum");
        String sms_code = object.getString("smsCode");

        String smsCode = redisComponent.getRedisString(phone_num);

        JSONObject result = new JSONObject();
        if (sms_code.equals(smsCode)) {
            result.put("code", ResponseStateEnum.STATUS_OK.getCode());
            result.put("msg", "ok");
        } else {
            result.put("code", ResponseStateEnum.STATUS_SMS_CODE_ERROR.getCode());
            result.put("msg", ResponseStateEnum.STATUS_SMS_CODE_ERROR.getMsg());
        }

        return ResponseUtil.createResp(result.getString("code"), result.getString("msg"), true, smsCode);
    }

}
