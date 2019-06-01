package com.phkj.web.controller.environmental;

import com.github.pagehelper.PageInfo;
import com.phkj.core.HttpJsonResult;
import com.phkj.core.response.ResponseUtil;
import com.phkj.entity.environmental.Comment;
import com.phkj.entity.environmental.StAppletEnvironment;
import com.phkj.entity.system.SystemAdmin;
import com.phkj.service.environmental.EnvironmentalService;
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

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


/**
 * 环境秩序接口
 */
@Controller
@RequestMapping("/admin/environ")
public class EnvironmentalController {

    private final static Logger LOGGER = LogManager.getLogger(EnvironmentalController.class);

    @Autowired
    private EnvironmentalService environmentalService;


    @RequestMapping("/")
    public String system(ModelMap modelMap) {
        modelMap.put("pageNum", "1");
        modelMap.put("pageSize", "20");
        return "/admin/environmental/environList";
    }


    /**
     * 后台管理--审核接口,接受上报, 完成上报
     */
    @ResponseBody
    @RequestMapping(value = "/system/update")
    public ResponseUtil update(HttpServletRequest request) {
        ResponseUtil responseUtil = new ResponseUtil();
        String id = request.getParameter("id");
        String type = request.getParameter("type");
        SystemAdmin adminUser = WebAdminSession.getAdminUser(request);
        try {
            if (environmentalService.update(id, adminUser,type)) {
                responseUtil.setSuccess(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("删除失败!");
        }
        return responseUtil;
    }

    /**
     * 后台管理删除
     */
    @ResponseBody
    @RequestMapping(value = "/system/delete")
    public ResponseUtil delete(HttpServletRequest request) {
        ResponseUtil responseUtil = new ResponseUtil();
        String id = request.getParameter("id");
        SystemAdmin adminUser = WebAdminSession.getAdminUser(request);
        try {
            if (environmentalService.delete(id, adminUser)) {
                responseUtil.setSuccess(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("删除失败!");
        }
        return responseUtil;
    }


    /**
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getSystemAllEnviron", method = RequestMethod.GET)
    public HttpJsonResult<List<StAppletEnvironment>> getSystemAllEnviron(HttpServletRequest request) {
        HttpJsonResult<List<StAppletEnvironment>> jsonResult = new HttpJsonResult<List<StAppletEnvironment>>();
        try {
            SystemAdmin adminUser = WebAdminSession.getAdminUser(request);
            String status = request.getParameter("q_status");
            String sts = request.getParameter("q_sts");
            String type = request.getParameter("q_type");
            String page = request.getParameter("page");
            String rows = request.getParameter("rows");
            String villageCode = adminUser.getVillageCode();
            PageInfo<StAppletEnvironment> pageInfo = environmentalService.getSystemAllEnviron(status, sts, type,
                    page, rows, villageCode);
            String total = String.valueOf(pageInfo.getTotal());
            jsonResult.setTotal(Integer.valueOf(total));
            jsonResult.setRows(pageInfo.getList());
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("查询失败!" + e);
        }
        return jsonResult;
    }


    /**
     * 上报环境秩序
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/add")
    public ResponseUtil add(@RequestBody StAppletEnvironment stAppletEnviron) {
        ResponseUtil responseUtil = new ResponseUtil();
        if (!checkParam(stAppletEnviron)) {
            return responseUtil;
        }
        ;
        try {
            if (environmentalService.add(stAppletEnviron)) {
                responseUtil.setSuccess(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("添加失败" + e);
        }
        return responseUtil;
    }

    /**
     * 我的上报
     *
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping("/getMeEnvironList")
    public ResponseUtil getMeEnvironList(String userId, Integer pageNum, Integer pageSize,String villageCode) {
        ResponseUtil responseUtil = new ResponseUtil();
        try {
            Map<String, Object> returnMap = environmentalService.getAll(userId, pageNum, pageSize,villageCode);
            responseUtil.setSuccess(true);
            responseUtil.setData(returnMap);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("查询列表失败!" + e);
        }
        return responseUtil;
    }

    /**
     * 查看详情
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/getEnvironDetail")
    public ResponseUtil getEnvironDetail(String id) {
        ResponseUtil responseUtil = new ResponseUtil();
        try {
            StAppletEnvironment stAppletEnvironment = environmentalService.getEnvironDetail(id);
            responseUtil.setData(stAppletEnvironment);
            responseUtil.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("查询列表失败!" + e);
        }
        ;
        return responseUtil;
    }

    /**
     * @return
     */
    @ResponseBody
    @RequestMapping("/addComment")
    public ResponseUtil addComment(@RequestBody Comment comment) {
        ResponseUtil responseUtil = new ResponseUtil();
        try {
            if (environmentalService.addComment(comment)) {
                responseUtil.setSuccess(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("评论失败!" + e);
        }
        return responseUtil;
    }


    public boolean checkParam(StAppletEnvironment stAppletEnvironment) {
        if (StringUtils.isBlank(stAppletEnvironment.getTitle())) {
            return false;
        }
        if (StringUtils.isBlank(stAppletEnvironment.getVillageCode())) {
            return false;
        }
        return true;
    }
}
