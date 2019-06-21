package com.phkj.web.controller.visitor;

import com.github.pagehelper.PageInfo;
import com.phkj.core.HttpJsonResult;
import com.phkj.core.response.ResponseUtil;
import com.phkj.entity.environmental.StAppletOverTime;
import com.phkj.service.environmental.OverTimeService;
import com.phkj.web.controller.environmental.OverTimeController;
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
    @RequestMapping()
    public String system(ModelMap modelMap) {
        modelMap.put("pageSize", "30");
        modelMap.put("pageNum", "1");
        return "/admin/visitor/visnumlist";
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


}
