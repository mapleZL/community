package com.phkj.web.controller.wx;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.phkj.core.Md5;
import com.phkj.entity.system.SystemAdmin;
import com.phkj.service.system.ISystemAdminService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
 * @description：用户登录
 * @modified By：
 * @version: 0.0.1$
 */
@Controller
@RequestMapping(value = "admin/user")
public class LoginController {

    Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    IMemberService memberService;

    @Autowired
    ISystemAdminService systemAdminService;

    @RequestMapping(value = {"/login"}, method = {RequestMethod.POST})
    @ResponseBody
    public ResponseUtil login(@RequestBody MemberParam memberParam, HttpServletRequest httpServletRequest, HttpServletResponse response) {
        try {
            if (memberParam == null) {
                return ResponseUtil.createResp(ResponseStateEnum.PARAM_EMPTY.getCode(), ResponseStateEnum.PARAM_EMPTY.getMsg(), false, null);
            }
            String phoneNum = memberParam.getPhoneNum();
            String password = memberParam.getPassword();
            if (StringUtils.isBlank(phoneNum) || StringUtils.isBlank(password)) {
                return ResponseUtil.createResp(ResponseStateEnum.PARAM_EMPTY.getCode(), "请输入账号密码", false, null);
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

    /**
     * create by: zl
     * description: 物业登录
     * create time:
     *
     * @return
     * @Param: memberParam
     * @Param: httpServletRequest
     * @Param: response
     */
    @RequestMapping(value = {"/wy/login"}, method = {RequestMethod.GET})
    @ResponseBody
    public ResponseUtil wyLogin(@RequestParam String name, @RequestParam String password) {
        try {
            if (StringUtils.isBlank(name) || StringUtils.isBlank(password)) {
                return ResponseUtil.createResp(ResponseStateEnum.PARAM_EMPTY.getCode(), "请输入账号密码", false, null);
            }
            password = Md5.getMd5String(password);
            ServiceResult<SystemAdmin> result = systemAdminService.getSystemAdminByNamePwd(name, password);
            SystemAdmin admin = result.getResult();
            JSONObject jsonObject = new JSONObject();
            if (admin != null) {
                jsonObject.put("id", admin.getId());
                jsonObject.put("name", admin.getName());
                jsonObject.put("idNo", admin.getIdNo());
                jsonObject.put("villageCode", admin.getVillageCode());
            } else {
                return ResponseUtil.createResp(ResponseStateEnum.PARAM_EMPTY.getCode(), "用户名或密码错误", false, jsonObject);
            }
            return ResponseUtil.createResp(result.getCode(), result.getMessage(), true, jsonObject);
        } catch (Exception e) {
            logger.error("login error, exception:{}", e);
            return ResponseUtil.createResp(ResponseStateEnum.STATUS_SERVER_ERROR.getCode(), ResponseStateEnum.STATUS_SERVER_ERROR.getMsg(), false, null);
        }
    }
}
