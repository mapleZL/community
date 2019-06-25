package com.phkj.web.controller.visitor;

import com.aliyun.openservices.log.common.Logs;
import com.github.pagehelper.PageInfo;
import com.phkj.core.HttpJsonResult;
import com.phkj.core.response.ResponseUtil;
import com.phkj.entity.system.SystemAdmin;
import com.phkj.entity.visit.StAppletVisitor;
import com.phkj.service.visit.VisitorService;
import com.phkj.web.util.WebAdminSession;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 *
 */
@Controller
@RequestMapping("/admin/visit")
public class VisitorController {

    private final static Logger LOGGER = LogManager.getLogger(VisitorController.class);

    @Autowired
    VisitorService visitorService;

    @RequestMapping
    public String system(ModelMap modelMap) {
        modelMap.put("pageNum", "1");
        modelMap.put("pageSize", "30");
        return "/admin/visitor/visitorlist";
    }


    /**
     * @return
     */
    @ResponseBody
    @RequestMapping("/system/delete")
    public ResponseUtil delete(HttpServletRequest request) {
        ResponseUtil responseUtil = new ResponseUtil();
        SystemAdmin adminUser = WebAdminSession.getAdminUser(request);
        String id = request.getParameter("id");
        WebAdminSession.getAdminUser(request);
        if (visitorService.delete(id,adminUser)) {
            responseUtil.setSuccess(true);
        }
        return responseUtil;
    }


    /**
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/system/getAlVisitor")
    public HttpJsonResult<StAppletVisitor> getAllVisitor(HttpServletRequest request) {
        HttpJsonResult httpJsonResult = new HttpJsonResult();
        try {
            String sts = request.getParameter("q_sts");
            String type = request.getParameter("q_type");
            String page = request.getParameter("page");
            String rows = request.getParameter("rows");
            SystemAdmin adminUser = WebAdminSession.getAdminUser(request);
            PageInfo<StAppletVisitor> pageInfo = visitorService.getAllVisitor(sts, type, page, rows,
                    adminUser.getVillageCode());
            String total = String.valueOf(pageInfo.getTotal());
            httpJsonResult.setTotal(Integer.valueOf(total));
            httpJsonResult.setRows(pageInfo.getList());
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("查询失败!" + e);
        }
        return httpJsonResult;
    }

    /**
     * 获取时间
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/getVisitTime")
    public ResponseUtil getVisitTime(String villageCode) {
        ResponseUtil responseUtil = new ResponseUtil();
        try {
            Map<String, Object> returnMap = visitorService.getVisitTime(villageCode);
            responseUtil.setSuccess(true);
            responseUtil.setData(returnMap);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("查询失败!");
        }
        return responseUtil;
    }

    /**
     * 我的预约
     */
    @ResponseBody
    @RequestMapping(value = "/addVisit", method = RequestMethod.POST)
    public ResponseUtil addVisit(@RequestBody StAppletVisitor visitor) {
        ResponseUtil responseUtil = new ResponseUtil();
        try {
            Map<String, Object> returnMap = visitorService.addVisit(visitor);
            responseUtil.setSuccess(true);
            responseUtil.setData(returnMap);
            if (null == returnMap && returnMap.size() == 0) {
                responseUtil.setSuccess(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("添加失败!" + e);
        }
        return responseUtil;
    }


}