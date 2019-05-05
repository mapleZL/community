package com.ejavashop.web.controller.operate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ejavashop.vo.seller.TransportJson;
import com.ejavashop.web.controller.BaseController;

@Controller
@RequestMapping(value = "/admin/operate/transport")
public class AdminTransportController extends BaseController{

    @RequestMapping(value = "")
    public String initTransport(HttpServletRequest request, HttpServletResponse response,
                           Map<String, Object> dataMap) {
        System.out.println("运费页面设置初始化开始");
        List<Map<String,String>> tranList = new ArrayList<Map<String,String>>();
        Map<String ,String> tranlist = new HashMap<String, String>();
        tranlist.put("1", "圆通");
        tranlist.put("2", "中通");
        tranlist.put("3", "顺丰");
        tranlist.put("4", "EMS");
        tranlist.put("5", "德邦物流");
        tranlist.put("6", "中铁物流");
        tranList.add(tranlist);
        dataMap.put("tranlist", tranlist);
        
        List<TransportJson> jsonlist = new ArrayList<TransportJson>();
        dataMap.put("mailList", jsonlist);
        dataMap.put("expressList", jsonlist);
        dataMap.put("emsList", jsonlist);
        return "admin/operate/transport/edit";
    }
    
}
