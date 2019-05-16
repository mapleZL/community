package com.phkj.web.controller.wx;

import com.alibaba.fastjson.JSONObject;
import com.phkj.core.Md5;
import com.phkj.core.ResponseStateEnum;
import com.phkj.core.ServiceResult;
import com.phkj.core.ip.IPUtil;
import com.phkj.core.redis.RedisComponent;
import com.phkj.core.response.ResponseUtil;
import com.phkj.entity.member.Member;
import com.phkj.entity.member.MemberParam;
import com.phkj.service.member.IMemberService;
import com.phkj.web.util.WeChatUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author ：zl
 * @date ：Created in 2019/5/13 17:22
 * @description：微信用户注册
 * @modified By：
 * @version: 0.0.1$
 */
@Controller
@RequestMapping(value = "admin/user")
public class RegisterController {

    Logger logger = LoggerFactory.getLogger(RegisterController.class);

    @Autowired
    RedisComponent redisComponent;

    @Autowired
    IMemberService memberService;

    @RequestMapping(value = {"/register"}, method = {RequestMethod.POST})
    @ResponseBody
    public ResponseUtil register(@RequestBody MemberParam memberParam, HttpServletRequest httpServletRequest) {
        try {
            if (memberParam == null) {
                return ResponseUtil.createResp(ResponseStateEnum.PARAM_EMPTY.getCode(), ResponseStateEnum.PARAM_EMPTY.getMsg(), true, null);
            }
            String phoneNum = memberParam.getPhoneNum();
            String password = memberParam.getPassword();
            String smsCode = memberParam.getSmsCode();
            String wxCode = memberParam.getWxCode();
            ResponseUtil response = checkParam(phoneNum, password, smsCode);
            if (response != null) {
                return response;
            }
            //1.通过code来换取access_token
            JSONObject json = WeChatUtil.getWebAccessToken(wxCode);
            logger.info("通过code获取信息为：" + json);
            //获取网页授权access_token凭据
            String webAccessToken = json.getString("access_token");
            //获取用户openid
            String openid = json.getString("openid");
            //2.通过access_token和openid拉取用户信息
            JSONObject userInfo = WeChatUtil.getUserInfo(webAccessToken, openid);
            //logger.info("获取的用户信息userInfo: " + userInfo);
            Member member = new Member();
            member.setName(userInfo.getString("nickname"));
            member.setHeadIcon(userInfo.getString("headimgurl"));
            member.setWechatNum(userInfo.getString("openid"));
//            member.setName("zl");
//            member.setHeadIcon("");
//            member.setWechatNum("wx1084845808");
            member.setPassword(Md5.getMd5String(password));
            member.setPhone(phoneNum);
            setMemberData(httpServletRequest, member);
            ServiceResult<Member> result = memberService.memberRegister(member);
            return ResponseUtil.createResp(result.getCode(), result.getMessage(), true, result.getSuccess());
        } catch (Exception e) {
            logger.error("用户注册账号异常, exception:{}", e);
            return ResponseUtil.createResp(ResponseStateEnum.STATUS_SERVER_ERROR.getCode(), ResponseStateEnum.STATUS_SERVER_ERROR.getMsg(), false, null);
        }
    }

    private void setMemberData(HttpServletRequest httpServletRequest, Member member) {
        member.setGrade(1);
        member.setGradeValue(0);
        member.setIntegral(0);
        member.setRegisterTime(new Date());
        member.setLastLoginTime(new Date());
        member.setLoginNumber(0);
        String ip = IPUtil.getIpAddr(httpServletRequest);
        member.setLastLoginIp(ip);
        member.setPwdErrCount(0);
        member.setSource(6);
        member.setBalancePwd("");
        BigDecimal bigDecimal = new BigDecimal(0);
        member.setBalance(bigDecimal);
        member.setIsEmailVerify(0);
        member.setIsSmsVerify(0);
        member.setEmailVerifyCode("");
        member.setSmsVerifyCode("");
        member.setCanReceiveEmail(1);
        member.setCanReceiveSms(1);
        member.setStatus(1);
        member.setUpdateTime(new Date());
        member.setMemberType(3);
        member.setIsSyncOms("0");
        member.setIsTransferBussiness("0");
    }

    /**
     * create by: zl
     * description: 参数校验
     * create time:
     *
     * @return
     * @Param: phoneNum
     * @Param: name
     * @Param: password
     * @Param: smsCode
     */
    private ResponseUtil checkParam(String phoneNum, String password, String smsCode) {
        if (StringUtils.isBlank(password)) {
            return ResponseUtil.createResp(ResponseStateEnum.PARAM_EMPTY.getCode(), "password is blank", true, null);
        }
        if (StringUtils.isBlank(phoneNum) || StringUtils.isBlank(smsCode)) {
            return ResponseUtil.createResp(ResponseStateEnum.PARAM_EMPTY.getCode(), "phoneNum or smsCode is blank", true, null);
        }
        String code = redisComponent.getRedisString(phoneNum);
        if (StringUtils.isBlank(code)) {
            return ResponseUtil.createResp(ResponseStateEnum.PARAM_EMPTY.getCode(), "您的验证码已失效", true, null);
        }
        if (!code.equals(smsCode)) {
            return ResponseUtil.createResp(ResponseStateEnum.PARAM_EMPTY.getCode(), "验证码错误", true, null);
        }
        ServiceResult<Member> memberByPhone = memberService.getMemberByPhone(phoneNum);
        if (memberByPhone.getSuccess() && memberByPhone.getResult() != null
                && StringUtils.isNotBlank(memberByPhone.getResult().getPhone())) {
            return ResponseUtil.createResp(ResponseStateEnum.PARAM_EMPTY.getCode(), "该手机号已注册", true, null);
        }
//        ServiceResult<List<Member>> memberByName = memberService.getMemberByName(name);
//        if (memberByName.getSuccess() && memberByName.getResult() != null
//                && memberByName.getResult().size() > 0) {
//            return ResponseUtil.createResp(ResponseStateEnum.PARAM_EMPTY.getCode(), "用户名已存在", true, null);
//        }
        return null;
    }
}
