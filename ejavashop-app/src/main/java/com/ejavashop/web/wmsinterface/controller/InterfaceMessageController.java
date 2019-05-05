package com.ejavashop.web.wmsinterface.controller;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.dawa.web.controller.BaseController;
import com.ejavashop.service.sms.ISenderService;

import net.sf.json.JSONArray;

/**
 * 收发短信接口
 *                       
 * @Filename: InterfaceMessageController.java
 * @Version: 1.0
 * @Author: 陆帅 * @Email: lu1632278229@sina.cn
 *
 */
@Controller
@RequestMapping(value = "/dawa_message")
public class InterfaceMessageController extends BaseController{

    private static Logger log = LogManager.getLogger(InterfaceMessageController.class);
    
    @Resource
    ISenderService senderService;
    
    @RequestMapping(value = "sendandreceive.html", method = { RequestMethod.GET,RequestMethod.POST })
    public void ReceiptsResponse(HttpServletRequest request,HttpServletResponse response,String sms_status,
                                 String sms_reply, String voice_status, String flow_status){
        log.debug("短信回传开始！！！！！！！！！！！！！！");
        try {
            sms_reply = URLDecoder.decode(sms_reply);
            System.out.println(sms_reply);
            @SuppressWarnings({ "unchecked", "rawtypes" })
            List<Map<String, Object>> list = (List)JSON.parseArray(sms_reply);
            for(Map<String, Object> str:list){
                System.out.println(str.get("text"));
            }
            if (true)
                return;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @RequestMapping(value = "test.html", method = { RequestMethod.GET,RequestMethod.POST })
    public void test(HttpServletRequest request,HttpServletResponse response){
        log.debug("开始测试发送！！！");
        Map<String, Object> smsparam = new HashMap<String, Object>();
        smsparam.put("mobile", "13636659863");
        smsparam.put("content", "【大袜网】亲,您有宝贝下单,金额为:1.2,商品号:12222");
        senderService.sendSMSWidthCallable(smsparam);
    }
    
    @RequestMapping(value = "test2.html", method = { RequestMethod.GET,RequestMethod.POST })
    public void test2(HttpServletRequest request,HttpServletResponse response){
        log.debug("开始测试发送！！！");
        Map<String, Object> smsparam = new HashMap<String, Object>();
        smsparam.put("mobile", "13636659863");
        smsparam.put("content", "【大袜网】亲，您在大袜网的自提订单已经备货完成，具体到货时间请等待提货点通知，及时到千禧路提货！");
        senderService.sendSMSWidthCallable(smsparam);
    }
    
}
