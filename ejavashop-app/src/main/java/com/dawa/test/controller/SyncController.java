package com.dawa.test.controller;


import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ejavashop.core.SyncWayUtil;

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

}
