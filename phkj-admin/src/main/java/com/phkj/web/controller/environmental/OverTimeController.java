package com.phkj.web.controller.environmental;

import com.github.pagehelper.PageInfo;
import com.phkj.core.HttpJsonResult;
import com.phkj.core.response.ResponseUtil;
import com.phkj.entity.environmental.StAppletOverTime;
import com.phkj.service.environmental.OverTimeService;
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
import java.util.List;
import java.util.Map;

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
            PageInfo<StAppletOverTime> pageInfo = overTimeService.getSystemAllOverTime(page, rows);
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
    @ResponseBody
    @RequestMapping("/system/add")
    public ResponseUtil add(@RequestBody StAppletOverTime stAppletOverTime) {
        ResponseUtil responseUtil = new ResponseUtil();
        try {
            if (overTimeService.add(stAppletOverTime)) {
                responseUtil.setSuccess(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("添加失败!" + e);
        }
        return responseUtil;
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
    public String getDetail(HttpServletRequest request, Map<String, Object> dataMap, Integer id) {

        StAppletOverTime stAppletOverTime = overTimeService.getSystemDetail(id);
        dataMap.put("overTime", stAppletOverTime);
        return "/admin/environmental/updateOvertime";
    }

    /**
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String update(HttpServletRequest request) {
        ResponseUtil responseUtil = new ResponseUtil();
        String id = request.getParameter("id");
        String time = request.getParameter("time");
        if (overTimeService.update(id, time)) {
            responseUtil.setSuccess(true);
        }
        ;
        return "redirect:/admin/overtime/";
    }
}
