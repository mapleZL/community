package com.phkj.web.controller.wx;

import com.phkj.web.controller.sms.SmsController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

    Logger logger = LoggerFactory.getLogger(SmsController.class);

    @RequestMapping(value = {"/register"}, method = {RequestMethod.POST})
    @ResponseBody
    public String register(@RequestBody String request) {

        return null;
    }
}
