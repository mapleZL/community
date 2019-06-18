package com.phkj.web.controller.complaint;

import com.github.pagehelper.PageInfo;
import com.phkj.core.HttpJsonResult;
import com.phkj.core.response.ResponseUtil;
import com.phkj.entity.complaint.StAppletComSugges;
import com.phkj.entity.system.SystemAdmin;
import com.phkj.service.complaint.ComplaintService;
import com.phkj.web.util.WebAdminSession;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author gaowei
 * 投诉
 */
@Controller
@RequestMapping("/admin/complaint")
public class ComplaintController {

    private final static Logger LOGGER = LogManager.getLogger(ComplaintController.class);

    @Autowired
    ComplaintService complaintService;

    /**
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/system")
    public String systemComp(ModelMap modelMap) {
        modelMap.put("pageSize", 30);
        modelMap.put("pageNum", "1");
        return "/admin/complaint/compAndSuggess";
    }


    /**
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/systemall")
    public String systemSugg(ModelMap modelMap) {
        modelMap.put("pageSize", 30);
        modelMap.put("pageNum", "1");
        return "/admin/complaint/systemCompAndSuggess";
    }

    @ResponseBody
    @RequestMapping("/system/updateCom")
    public ResponseUtil updateCom(HttpServletRequest request) {
        ResponseUtil responseUtil = new ResponseUtil();
        String id = request.getParameter("id");
        String type = request.getParameter("type");
        SystemAdmin adminUser = WebAdminSession.getAdminUser(request);
        try {
            if (complaintService.updateComAndSuggess(id, type, adminUser)) {
                responseUtil.setSuccess(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("审核失败!" + e);
        }
        return responseUtil;
    }

    /**
     * 后台管理
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/system/getAllComplaint")
    public HttpJsonResult<List<StAppletComSugges>> getAllComAndSugg(HttpServletRequest request, Integer page,
                                                                    Integer rows, String taskType) {
        HttpJsonResult<List<StAppletComSugges>> resultJson = new HttpJsonResult<>();
        SystemAdmin adminUser = WebAdminSession.getAdminUser(request);
        String type = "2";
        String sts = request.getParameter("q_sts");
        PageInfo<StAppletComSugges> pageInfo = complaintService.getAllComAndSugg(page, rows, type, sts,
                adminUser.getVillageCode());
        String total = String.valueOf(pageInfo.getTotal());
        resultJson.setRows(pageInfo.getList());
        resultJson.setTotal(Integer.valueOf(total));
        return resultJson;
    }


    /**
     * 后台管理
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/system/getAllComAndSugg")
    public HttpJsonResult<List<StAppletComSugges>> getAllCom(HttpServletRequest request, Integer page,
                                                             Integer rows, String taskType) {
        HttpJsonResult<List<StAppletComSugges>> resultJson = new HttpJsonResult<>();
        SystemAdmin adminUser = WebAdminSession.getAdminUser(request);
        String type = request.getParameter("q_type");
        String sts = request.getParameter("q_sts");
        PageInfo<StAppletComSugges> pageInfo = complaintService.getAllComAndSugg(page, rows, type, sts,
                adminUser.getVillageCode());
        String total = String.valueOf(pageInfo.getTotal());
        resultJson.setRows(pageInfo.getList());
        resultJson.setTotal(Integer.valueOf(total));
        return resultJson;
    }


    /**
     * 发布投诉或者建议
     *
     * @param stAppletComSugges
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addComOrSugg", method = RequestMethod.POST)
    public ResponseUtil addComorSuggess(@RequestBody StAppletComSugges stAppletComSugges) {
        ResponseUtil responseUtil = new ResponseUtil();
        if ("1".equals(stAppletComSugges.getType())) {
            if (StringUtils.isBlank(stAppletComSugges.getContent())) {
                responseUtil.setMsg("投诉内容不能为空");
            }
            if (StringUtils.isBlank(stAppletComSugges.getComplaintTarget())) {
                responseUtil.setMsg("投诉对象不能为空");
            }
        } else {
            if (StringUtils.isBlank(stAppletComSugges.getContent())) {
                responseUtil.setMsg("意见内容不能为空");
            }
        }
        try {
            if (complaintService.addComorSuggess(stAppletComSugges)) {
                responseUtil.setSuccess(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("添加失败!" + e);
        }
        return responseUtil;
    }

    /**
     * 查询我的投诉建议
     *
     * @param request
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getAllMeComSugg", method = RequestMethod.GET)
    public ResponseUtil getAllMeComplaint(HttpServletRequest request, Integer pageNum, Integer pageSize) {
        ResponseUtil responseUtil = new ResponseUtil();
        try {
            String type = request.getParameter("type");
            String userId = request.getParameter("userId");
            String villageCode = request.getParameter("villageCode");
            Map<String, Object> returnMap = complaintService.getAllMeComplaint(pageNum, pageSize, type, userId, villageCode);
            responseUtil.setSuccess(true);
            responseUtil.setData(returnMap);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("查询失败!");
        }
        return responseUtil;
    }


    /**
     * 查看详情
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/getDetail")
    public ResponseUtil getDetail(HttpServletRequest request) {
        ResponseUtil responseUtil = new ResponseUtil();
        String id = request.getParameter("id");
        StAppletComSugges stAppletComSugges = complaintService.getDetail(id);
        responseUtil.setSuccess(true);
        responseUtil.setData(stAppletComSugges);
        return responseUtil;
    }
}
