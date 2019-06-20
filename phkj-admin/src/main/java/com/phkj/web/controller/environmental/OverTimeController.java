package com.phkj.web.controller.environmental;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.phkj.core.HttpJsonResult;
import com.phkj.core.response.ResponseUtil;
import com.phkj.entity.environmental.StAppletOverTime;
import com.phkj.entity.system.SystemAdmin;
import com.phkj.service.environmental.OverTimeService;
import com.phkj.web.util.WebAdminSession;

/**
 * gaowei
 */
@Controller
@RequestMapping("/admin/overtime")
public class OverTimeController {

    private final static Logger LOGGER = LogManager.getLogger(OverTimeController.class);


    @Autowired
    OverTimeService overTimeService;

    /**
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String system(ModelMap modelMap) {
        modelMap.put("pageNum", "1");
        modelMap.put("pageSize", "30");
        return "/admin/environmental/overTime";
    }


    /**
     * 访客时间限制
     *
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/visitor", method = RequestMethod.GET)
    public String visitor(ModelMap modelMap) {
        modelMap.put("pageNum", "1");
        modelMap.put("pageSize", "30");
        return "/admin/visitor/timelist";
    }

    /**
     * 访客时间限制
     *
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/addTime", method = RequestMethod.GET)
    public String addTime(ModelMap modelMap) {
        modelMap.put("pageNum", "1");
        modelMap.put("pageSize", "30");
        return "/admin/visitor/addTime";
    }


    /**
     * @param request
     * @param page
     * @param rows
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/system/getVisitorOverTime", method = RequestMethod.GET)
    public HttpJsonResult<List<StAppletOverTime>> getVisitorOverTime(HttpServletRequest request, Integer page, Integer rows) {
        HttpJsonResult<List<StAppletOverTime>> jsonResult = new HttpJsonResult<List<StAppletOverTime>>();
        try {
            String type = "2";
            PageInfo<StAppletOverTime> pageInfo = overTimeService.getSystemAllOverTime(page, rows, type);
            jsonResult.setRows(pageInfo.getList());
            String total = String.valueOf(pageInfo.getTotal());
            jsonResult.setTotal(Integer.valueOf(total));
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("查询失败!" + e);
        }
        return jsonResult;
    }

    /**
     * @param request
     * @param page
     * @param rows
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/system/getAllOverTime", method = RequestMethod.GET)
    public HttpJsonResult<List<StAppletOverTime>> getSystemOverTime(HttpServletRequest request, Integer page, Integer rows) {
        HttpJsonResult<List<StAppletOverTime>> jsonResult = new HttpJsonResult<List<StAppletOverTime>>();
        try {
            String type = "1";
            PageInfo<StAppletOverTime> pageInfo = overTimeService.getSystemAllOverTime(page, rows, type);
            jsonResult.setRows(pageInfo.getList());
            String total = String.valueOf(pageInfo.getTotal());
            jsonResult.setTotal(Integer.valueOf(total));
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("查询失败!" + e);
        }
        return jsonResult;
    }


    /**
     * @return
     */
    @RequestMapping("/system/add")
    public String add(StAppletOverTime stAppletOverTime, HttpServletRequest request) {
        try {
            SystemAdmin adminUser = WebAdminSession.getAdminUser(request);
            if (overTimeService.add(stAppletOverTime, adminUser)) {
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("添加失败!" + e);
        }
        return "redirect:/admin/overtime/visitor";
    }


    /**
     * 后台管理详情
     *
     * @param request
     * @param
     * @param id
     * @return
     */
    @RequestMapping("/system/detail")
    public String getDetail(HttpServletRequest request, Map<String, Object> dataMap, Integer id, String type) {

        StAppletOverTime stAppletOverTime = overTimeService.getSystemDetail(id);
        dataMap.put("overTime", stAppletOverTime);

        if ("2".equals(type)) {
            return "/admin/visitor/updateOvertime";
        }
        return "/admin/environmental/updateOvertime";
    }

    /**
     * @param request
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String update(HttpServletRequest request) {
        ResponseUtil responseUtil = new ResponseUtil();
        String id = request.getParameter("id");
        String time = request.getParameter("time");
        SystemAdmin adminUser = WebAdminSession.getAdminUser(request);
        if (overTimeService.update(id, time, adminUser)) {
            responseUtil.setSuccess(true);
        }

        return "redirect:/admin/overtime/";
    }


    /**
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/system/delete", method = RequestMethod.GET)
    public String delete(HttpServletRequest request) {
        ResponseUtil responseUtil = new ResponseUtil();
        String id = request.getParameter("id");
        String type = request.getParameter("type");
        if (overTimeService.delete(id,type)) {
            responseUtil.setSuccess(true);
        }

        return "redirect:/admin/overtime/";
    }


    /**
     * @param request
     * @return
     */
    @RequestMapping(value = "/updateVistime", method = RequestMethod.GET)
    public String updateVisitor(HttpServletRequest request) {
        ResponseUtil responseUtil = new ResponseUtil();
        String id = request.getParameter("id");
        String time = request.getParameter("time");
        SystemAdmin adminUser = WebAdminSession.getAdminUser(request);
        if (overTimeService.update(id, time, adminUser)) {
            responseUtil.setSuccess(true);
        }

        return "redirect:/admin/overtime/visitor";
    }


}
