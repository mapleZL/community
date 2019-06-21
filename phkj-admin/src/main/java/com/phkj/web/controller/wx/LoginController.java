package com.phkj.web.controller.wx;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.phkj.core.ResponseStateEnum;
import com.phkj.core.ServiceResult;
import com.phkj.core.ip.IPUtil;
import com.phkj.core.response.ResponseUtil;
import com.phkj.entity.member.Member;
import com.phkj.entity.member.MemberParam;
import com.phkj.service.member.IMemberService;

import java.net.URLDecoder;

/**
 * @author ：zl
 * @date ：Created in 2019/5/13 17:22
 * @description：微信用户注册
 * @modified By：
 * @version: 0.0.1$
 */
@Controller
@RequestMapping(value = "admin/user")
public class LoginController {

    Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    IMemberService memberService;

    @RequestMapping(value = {"/login"}, method = {RequestMethod.POST})
    @ResponseBody
    public ResponseUtil login(@RequestBody MemberParam memberParam, HttpServletRequest httpServletRequest, HttpServletResponse response) {
        logger.info("login param: " + memberParam);
        try {
            if (memberParam == null) {
                return ResponseUtil.createResp(ResponseStateEnum.PARAM_EMPTY.getCode(), ResponseStateEnum.PARAM_EMPTY.getMsg(), true, null);
            }
            String phoneNum = memberParam.getPhoneNum();
            String password = memberParam.getPassword();
            if (StringUtils.isBlank(phoneNum) || StringUtils.isBlank(password)) {
                return ResponseUtil.createResp(ResponseStateEnum.PARAM_EMPTY.getCode(), "phoneNum or password is blank", true, null);
            }
            String ip = IPUtil.getIpAddr(httpServletRequest);
            // source为6表示微信登录
            ServiceResult<Member> result = memberService.memberLogin(phoneNum, password, ip, 6);
            Member member = result.getResult();
            JSONObject jsonObject = new JSONObject();
            if (member != null) {
                jsonObject.put("id", member.getId());
                jsonObject.put("name", URLDecoder.decode(member.getName(), "utf-8"));
                jsonObject.put("phoneNum", member.getPhone());
                jsonObject.put("headIcon", member.getHeadIcon());
            } else {
                return ResponseUtil.createResp(ResponseStateEnum.STATUS_SERVER_ERROR.getCode(), "用户名或密码错误", false, jsonObject);
            }
            return ResponseUtil.createResp(result.getCode(), result.getMessage(), true, jsonObject);
        } catch (Exception e) {
            logger.error("login error, exception:{}", e);
            return ResponseUtil.createResp(ResponseStateEnum.STATUS_SERVER_ERROR.getCode(), ResponseStateEnum.STATUS_SERVER_ERROR.getMsg(), false, null);
        }
    }
}
