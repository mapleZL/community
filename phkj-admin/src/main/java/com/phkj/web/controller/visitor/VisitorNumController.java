package com.phkj.web.controller.visitor;

import com.github.pagehelper.PageInfo;
import com.phkj.core.HttpJsonResult;
import com.phkj.core.response.ResponseUtil;
import com.phkj.entity.environmental.StAppletOverTime;
import com.phkj.entity.system.SystemAdmin;
import com.phkj.service.environmental.OverTimeService;
import com.phkj.web.util.WebAdminSession;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/visnum")
public class VisitorNumController {


    private final static Logger LOGGER = LogManager.getLogger(VisitorNumController.class);


    @Autowired
    OverTimeService overTimeService;

    /**
     * @param modelMap
     * @return
     */
    @RequestMapping
    public String system(ModelMap modelMap) {
        modelMap.put("pageSize", "30");
        modelMap.put("pageNum", "1");
        return "/admin/visitor/visnumlist";
    }


    /**
     * 访客时间限制
     *
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/addTime", method = RequestMethod.GET)
    public String addTime(ModelMap modelMap) {
        return "/admin/visitor/addTime";
    }


    /**
     * @param request
     * @param page
     * @param rows
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/system/getVisitorNum", method = RequestMethod.GET)
    public HttpJsonResult<List<StAppletOverTime>> getVisitorOverTime(HttpServletRequest request, Integer page, Integer rows) {
        HttpJsonResult<List<StAppletOverTime>> jsonResult = new HttpJsonResult<List<StAppletOverTime>>();
        try {
            String type = "3";
            SystemAdmin adminUser = WebAdminSession.getAdminUser(request);
            PageInfo<StAppletOverTime> pageInfo = overTimeService.getSystemAllOverTime(page, rows, type, adminUser);
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
     * @return
     */
    @RequestMapping(value = "/system/updatenum", method = RequestMethod.GET)
    public String update(HttpServletRequest request) {
        ResponseUtil responseUtil = new ResponseUtil();
        String id = request.getParameter("id");
        String time = request.getParameter("time");
        SystemAdmin adminUser = WebAdminSession.getAdminUser(request);
        if (overTimeService.update(id, time, adminUser)) {
            responseUtil.setSuccess(true);
        }

        return "redirect:/admin/visnum/";
    }

    /**
     * @return
     */
    @RequestMapping("/system/addnum")
    public String addOverTime(StAppletOverTime stAppletOverTime, HttpServletRequest request) {
        try {
            stAppletOverTime.setType("3");
            SystemAdmin adminUser = WebAdminSession.getAdminUser(request);
            if (overTimeService.add(stAppletOverTime, adminUser)) {
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("添加失败!" + e);
        }
        return "redirect:/admin/visnum/";
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
        if (overTimeService.delete(id, type)) {
            responseUtil.setSuccess(true);
        }

        return "redirect:/admin/visnum/";
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
        return "/admin/visitor/updatenum";
    }


}
