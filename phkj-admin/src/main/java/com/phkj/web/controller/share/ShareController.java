package com.phkj.web.controller.share;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.phkj.web.controller.system.AdminCodeController;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.phkj.core.HttpJsonResult;
import com.phkj.core.response.ResponseUtil;
import com.phkj.entity.share.StAppletShareInfo;
import com.phkj.entity.system.SystemAdmin;
import com.phkj.service.share.ShareService;
import com.phkj.web.util.WebAdminSession;

/**
 * gaowei
 */
@Controller
@RequestMapping("/admin/share")
public class ShareController {
    private final static Logger LOGGER = LogManager.getLogger(ShareController.class);

    @Autowired
    private ShareService shareService;

    @InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request) {

        //转换日期
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));// CustomDateEditor为自定义日期编辑器
    }


    /**
     * 后台管理页面
     *
     * @param request
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getShareInfo(HttpServletRequest request, Integer pageNum,
                               Integer pageSize, ModelMap modelMap) {
        modelMap.put("pageNum", "1");
        modelMap.put("pageSize", "30");
        return "/admin/share/shareInfoList";
    }


    /**
     * 后台管理页面
     *
     * @param request
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/system", method = RequestMethod.GET)
    public String getSystemShareInfo(HttpServletRequest request, Integer pageNum,
                                     Integer pageSize, ModelMap modelMap) {
        modelMap.put("pageNum", "1");
        modelMap.put("pageSize", "30");
        return "/admin/share/comShareInfoList";
    }

    /**
     * 跳转链接
     *
     * @return
     */
    @RequestMapping(value = "/system/add", method = RequestMethod.GET)
    public String addShareInfo() {

        return "/admin/share/shareadd";

    }

    @ResponseBody
    @RequestMapping(value = "/getMeApplyDetail")
    public ResponseUtil getMeApplyDetail(HttpServletRequest request) {
        ResponseUtil responseUtil = new ResponseUtil();
        String id = request.getParameter("id");
        String userId = request.getParameter("userId");
        Map<String, Object> returnMap = shareService.getMeApplyDetail(id, userId);
        responseUtil.setSuccess(true);
        responseUtil.setData(returnMap);
        return responseUtil;
    }

    /**
     * 根据id删除 假删除
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/stopShareInfo", method = RequestMethod.GET)
    public ResponseUtil stopShareInfo(HttpServletRequest request) {
        ResponseUtil responseUtil = new ResponseUtil();
        String id = request.getParameter("id");
        try {
            if (shareService.stopShareInfo(id)) {
                responseUtil.setSuccess(true);
            } else {
                responseUtil.setSuccess(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("停止发布! 错误信息" + e);
            responseUtil.setSuccess(false);
        }
        return responseUtil;
    }


    @ResponseBody
    @RequestMapping(value = "/system/examineApplyInfo")
    public ResponseUtil examineApplyInfo(HttpServletRequest request) {
        ResponseUtil responseUtil = new ResponseUtil();
        String type = request.getParameter("type");
        String id = request.getParameter("id");
        SystemAdmin adminUser = WebAdminSession.getAdminUser(request);
        try {
            if (shareService.examineApplyInfo(id, type, adminUser)) {
                responseUtil.setSuccess(true);
            } else {
                responseUtil.setSuccess(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("审核失败! 错误信息" + e);
            responseUtil.setSuccess(false);
        }
        return responseUtil;
    }

    /**
     * 后台管理-物业发布-获取当前发布所有申请
     *
     * @param request
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getMeApplyInfoList", method = RequestMethod.GET)
    public ResponseUtil getMeApplyInfoList(HttpServletRequest request, Integer pageNum, Integer pageSize) {
        ResponseUtil responseUtil = new ResponseUtil();
        String status = request.getParameter("status");
        String userId = request.getParameter("userId");
        String villageCode = request.getParameter("villageCode");
        try {
            Map<String, Object> returnMap = shareService.getMeApplyInfoList(status, userId, pageNum, pageSize,villageCode);
            responseUtil.setData(returnMap);
            responseUtil.setSuccess(true);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("查询失败! 错误信息" + e);
            responseUtil.setSuccess(false);
        }
        return responseUtil;
    }


    /**
     * 发布新任务
     *
     * @param request
     * @param shareInfo
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/system/addShare", method = RequestMethod.POST)
    public String systemAddShare(HttpServletRequest request, StAppletShareInfo shareInfo, ModelMap modelMap) {

        SystemAdmin adminUser = WebAdminSession.getAdminUser(request);
        shareInfo.setVillageCode(adminUser.getVillageCode());
        shareInfo.setCreateTime(new Date());
        shareInfo.setShareStatus("0");
        shareInfo.setCreateUserId(Long.valueOf(adminUser.getId()));
        shareInfo.setCreateUserName(adminUser.getRoleName());
        shareInfo.setShareType("2");  // 物业发布信息
        boolean flag = shareService.createShareInfo(shareInfo);
        modelMap.put("pageSize", "30");
        return "/admin/share/comShareInfoList";
    }

    /**
     * 后台管理 获取发布大厅信息
     *
     * @param request
     * @param page
     * @param rows
     * @return
     */
    @ResponseBody
    @RequestMapping("/system/getComShareInfoList")
    public HttpJsonResult<List<Map>> getComShareInfoList(HttpServletRequest request, Integer page,
                                                         Integer rows) {
        HttpJsonResult<List<Map>> jsonResult = new HttpJsonResult<List<Map>>();
        String taskType = request.getParameter("q_taskType");
        String status = request.getParameter("q_status");
        String createId = request.getParameter("q_createId");
        Integer userId = null;
        // 如果是查询自己发布 增加userId
        SystemAdmin adminUser = WebAdminSession.getAdminUser(request);
        if (StringUtils.isNotBlank(createId) && "2".equals(createId)) {
            userId = adminUser.getId();
        }
        String villageCode = adminUser.getVillageCode();
        Map<String, Object> returnMap = shareService.getComShareInfoList(userId, taskType, status, page, rows,villageCode);
        String total = (String) returnMap.get("total");
        List<Map> list = (List<Map>) returnMap.get("list");
        jsonResult.setRows(list);
        jsonResult.setTotal(Integer.valueOf(total));
        return jsonResult;
    }

    /**
     * @return
     */
    @ResponseBody
    @RequestMapping("/system/getAllApplyByPage")
    public HttpJsonResult<List<Map>> getAllApplyByPage(HttpServletRequest request, Integer page, Integer rows) {

        HttpJsonResult<List<Map>> jsonResult = new HttpJsonResult<List<Map>>();
        try {
            String infoId = request.getParameter("infoId");
            Map<String, Object> returnMap = shareService.getAllApplyByPage(page, rows, infoId);
            String total = (String) returnMap.get("total");
            List<Map> list = (List<Map>) returnMap.get("list");
            jsonResult.setRows(list);
            jsonResult.setTotal(Integer.valueOf(total));
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("查询失败!! " + e);
        }
        return jsonResult;
    }

    /**
     * 后台管理页面
     *
     * @param request
     * @param page
     * @param rows
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getShareInfoList", method = RequestMethod.GET)
    public HttpJsonResult<List<Map>> getShareInfoList(HttpServletRequest request, Integer page,
                                                      Integer rows) {
        HttpJsonResult<List<Map>> jsonResult = new HttpJsonResult<List<Map>>();
        try {
            SystemAdmin adminUser = WebAdminSession.getAdminUser(request);
            String taskType = request.getParameter("q_taskType");
            String status = request.getParameter("q_status");
            String villageCode = adminUser.getVillageCode();
            Map<String, Object> returnMap = shareService.getShareInfoList(taskType, status, page, rows,villageCode);
            String total = (String) returnMap.get("total");
            List<Map> list = (List<Map>) returnMap.get("list");
            jsonResult.setRows(list);
            jsonResult.setTotal(Integer.valueOf(total));
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("查询失败! 错误信息" + e);
        }
        return jsonResult;
    }

    /**
     * 后台管理查看详情
     *
     * @param request
     * @return
     */
    @RequestMapping("/system/getShareInfoDetail")
    public String systemGetShareInfoDetail(HttpServletRequest request, ModelMap modelMap) {
        String id = request.getParameter("id");
        String pageType = request.getParameter("pageType");
        Map<String, Object> returnMap = shareService.getShareDetail(id);
        Object shareInfo = returnMap.get("shareInfo");
        Object applyList = returnMap.get("applyList");
        modelMap.put("shareInfo", shareInfo);
        modelMap.put("applyList", applyList);
        if ("dt".equals(pageType)) {
            return "/admin/share/comShareDetail";
        }
        return "/admin/share/shareDetail";
    }

    /**
     * 查询我的发布信息
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/getMeShareInfo")
    public ResponseUtil getMeShareInfo(HttpServletRequest request, Integer pageNum,
                                       Integer pageSize) {
        ResponseUtil responseUtil = new ResponseUtil();
        try {
            String userId = request.getParameter("userId");
            String taskType = request.getParameter("taskType");
            String villageCode = request.getParameter("villageCode");
            Map<String, Object> returnMap = shareService.getMeShareInfo(userId, taskType, pageNum, pageSize,villageCode);
            responseUtil.setSuccess(true);
            responseUtil.setData(returnMap);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("查询失败! 错误信息" + e);
            responseUtil.setSuccess(false);

        }
        return responseUtil;
    }


    /**
     * 根据id删除 假删除
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/deleteShareInfo", method = RequestMethod.GET)
    public ResponseUtil deleteShareInfo(HttpServletRequest request) {
        ResponseUtil responseUtil = new ResponseUtil();
        String id = request.getParameter("id");
        String type = request.getParameter("type");
        try {
            if (shareService.deleteShareInfo(id, type)) {
                responseUtil.setSuccess(true);
            } else {
                responseUtil.setSuccess(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("查询失败! 错误信息" + e);
            responseUtil.setSuccess(false);
        }
        return responseUtil;
    }


    /**
     * 共享大厅
     *
     * @param request
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getAllShareInfo", method = RequestMethod.GET)
    public ResponseUtil getAllShareInfo(HttpServletRequest request, Integer pageNum, Integer pageSize) {
        ResponseUtil responseUtil = new ResponseUtil();
        try {
            String taskType = request.getParameter("taskType");
            String villageCode = request.getParameter("villageCode");
            Map<String, Object> resultMap = shareService.getAllShareInfo(taskType, pageNum, pageSize);
            responseUtil.setSuccess(true);
            responseUtil.setData(resultMap);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("查询失败! 错误信息 :" + e);
            responseUtil.setSuccess(false);

        }
        return responseUtil;
    }

    /**
     * 获取详情页面
     */
    @ResponseBody
    @RequestMapping(value = "/getShareInfoDetail", method = RequestMethod.GET)
    public ResponseUtil getShareInfoDetail(HttpServletRequest request) {
        ResponseUtil responseUtil = new ResponseUtil();
        try {
            String id = request.getParameter("id");
            StAppletShareInfo shareInfo = shareService.getShareInfoDetail(id);
            responseUtil.setSuccess(true);
            responseUtil.setData(shareInfo);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("查询失败! 错误信息 :" + e);
            responseUtil.setSuccess(false);
        }
        return responseUtil;
    }


    /**
     * app用户端口发布共享信息
     *
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/publishShareInfo", method = RequestMethod.POST)
    public ResponseUtil publishShareInfo(@RequestBody StAppletShareInfo shareInfo) {
        ResponseUtil responseUtil = new ResponseUtil();
        try {

            String msg = checkParam(shareInfo);
            if (StringUtils.isNotBlank(msg)) {
                responseUtil.setSuccess(false);
                responseUtil.setMsg(msg);
                return responseUtil;
            }
            if (shareService.addShareInfo(shareInfo)) {
                responseUtil.setSuccess(true);
            } else {
                responseUtil.setSuccess(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("用户发布信息失败! 错误信息 :" + e);
            responseUtil.setSuccess(false);
        }
        return responseUtil;
    }


    @ResponseBody
    @RequestMapping(value = "/applyShareInfo", method = RequestMethod.GET)
    public ResponseUtil applyShareInfo(HttpServletRequest request) {
        String id = request.getParameter("id");
        String userId = request.getParameter("userId");
        String userName = request.getParameter("userName");
        String telePhone = request.getParameter("telePhone");
        String address = request.getParameter("address");
        String IDCard = request.getParameter("IDCard");

        ResponseUtil responseUtil = new ResponseUtil();
        try {
            String msg = shareService.applyShareInfo(id, userId, userName, telePhone, address, IDCard);
            if (StringUtils.isBlank(msg)) {
                responseUtil.setSuccess(true);
                responseUtil.setMsg("申请成功!");
            } else {
                responseUtil.setSuccess(false);
                responseUtil.setMsg(msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("用户发布信息失败! 错误信息 :" + e);
            responseUtil.setSuccess(false);
        }
        return responseUtil;
    }

    /**
     * 参数校验
     *
     * @param shareInfo
     * @return
     */
    private String checkParam(StAppletShareInfo shareInfo) {
        String msg = "";
        String taskType = shareInfo.getTaskType();
        if (StringUtils.isBlank(shareInfo.getTitle())) {
            msg = "标题不能为空!";
        }
        if (StringUtils.isBlank(shareInfo.getTelephone())) {
            msg = "联系方式不能为空!";
        }
        if (StringUtils.isBlank(shareInfo.getContact())) {
            msg = "联系人不能为空!";
        }

        if (StringUtils.isBlank(shareInfo.getVillageCode())) {
            msg = "用户信息异常!";
        }
        if ("1".equals(taskType)) {
            if (StringUtils.isBlank(shareInfo.getCarNum())) {
                msg = "车牌号不能为空!";
            }
            if (StringUtils.isBlank(shareInfo.getDepartPlace())) {
                msg = "出发地不能为空!";
            }
            if (StringUtils.isBlank(shareInfo.getDestination())) {
                msg = "目的地不能为空!";
            }
            if (null == shareInfo.getStartTime()) {
                msg = "发车时间不能为空!";
            }
        } else if ("2".equals(taskType)) {
            if (null == shareInfo.getStartTime()) {
                msg = "预约开始时间不能为空!";
            }
            if (null == shareInfo.getEndTime()) {
                msg = "预约结束时间不能为空!";
            }
        } else if ("3".equals(taskType)) {
            if (StringUtils.isBlank(shareInfo.getSkill())) {
                msg = "技能不能为空!";
            }
            if (null == shareInfo.getStartTime()) {
                msg = "互换开始时间不能为空!";
            }
            if (null == shareInfo.getEndTime()) {
                msg = "互换结束时间不能为空!";
            }
        } else if ("4".equals(taskType)) {
            if (StringUtils.isBlank(shareInfo.getContent())) {
                msg = "共享内容!";
            }
            if (null == shareInfo.getStartTime()) {
                msg = "共享开始时间不能为空!";
            }
            if (null == shareInfo.getEndTime()) {
                msg = "共享结束时间不能为空!";
            }

        }
        return msg;
    }


}
