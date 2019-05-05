package com.ejavashop.web.controller;


import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ejavashop.core.SyncWayUtil;
import com.ejavashop.model.order.CalculateUtil;

/**
 * 
 *                       
 * @Filename: SyncController.java
 * @Version: 1.0
 * @Author: 张斌 * @Email: 46584649@qq.com
 *
 */
@Controller
@RequestMapping(value = "sync/way/sync_way_set")
public class SyncController {

    @RequestMapping(value = "sync_way_set", method = { RequestMethod.GET })
    public void syncWaySet(HttpServletRequest request, HttpServletResponse response, Map<String, Object> dataMap) throws Exception {
        StringBuffer bf = new StringBuffer();
        String syncWay = request.getParameter("syncWay") == null ? "" : (String)request.getParameter("syncWay");
        bf = SyncWayUtil.changeSyncWay(syncWay);
        response.setContentType("text/html;charset=utf-8");

        PrintWriter pw = null;
        try {
            pw = response.getWriter();
        } catch (Exception e) {
            bf = new StringBuffer();
            bf.append(e.getMessage());
            e.printStackTrace();
            pw.close();
        }
        pw.print(bf.toString());
        pw.close();
    }

    @RequestMapping(value = "setActiveEndTime", method = { RequestMethod.GET })
    public void setActiveEndTime(HttpServletRequest request, HttpServletResponse response) throws Exception {
        StringBuffer bf = new StringBuffer();
        String endDate = request.getParameter("endDate") == null ? "" : (String)request.getParameter("endDate");
        bf = changeSetEndTime(endDate);
        response.setContentType("text/html;charset=utf-8");

        PrintWriter pw = null;
        try {
            pw = response.getWriter();
        } catch (Exception e) {
            bf = new StringBuffer();
            bf.append(e.getMessage());
            e.printStackTrace();
            pw.close();
        }
        pw.print(bf.toString());
        pw.close();
    }

    public static StringBuffer changeSetEndTime(String endDate) {
        StringBuffer bf = new StringBuffer();
        bf.append("<center>");
        if (!"".equals(endDate)) {
            try {  
            	CalculateUtil.END_TIME = Integer.parseInt(endDate);
            }
            catch(Exception e){  
            	CalculateUtil.END_TIME = 20170630;  
            }
            bf.append("<br/><br/><br/><br/><font color='red' size='6'>操作成功，翼支付活动结束时间调整至[" + endDate + "]</font>");
            
        }
        PrintWriter pw = null;
        bf.append("<br/><br/>商城当前活动结束时间为：");
        bf.append(CalculateUtil.END_TIME);
        bf.append("<br/><br/>请填写最新活动结束时间：<br/><br/>");
        bf.append("<input type='text' name='endDate' id='endDate' value='20170731' style='width: 150px;height: 40px;margin-right:20px'>");
        bf.append("<input type='button' name='' value='调整活动结束时间' style='width: 150px;height: 40px;' onclick='syncOms()'>");
        bf.append("</center><script>");
        bf.append("function syncOms() {if (confirm('确定调整当前活动结束时间吗?')) {location.href = '/sync/way/sync_way_set/setActiveEndTime?endDate='+document.getElementById('endDate').value;}}");
        bf.append("</script>");
        return bf;
    }
}
