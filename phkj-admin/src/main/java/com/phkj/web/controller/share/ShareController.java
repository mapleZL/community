package com.phkj.web.controller.share;


import com.phkj.core.HttpJsonResult;
import com.phkj.core.response.ResponseUtil;
import com.phkj.entity.member.MemberCar;
import com.phkj.entity.share.StAppletShareInfo;
import com.phkj.service.share.ShareService;
import com.sun.corba.se.spi.ior.ObjectKey;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * gaowei
 */
@Controller
@RequestMapping("/share")
public class ShareController {
    private final static Logger LOGGER = LogManager.getLogger(ShareController.class);

    @Autowired
    private ShareService shareService;

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
        try {
            modelMap.put("pageNum", "1");
            modelMap.put("pageSize", "30");
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("查询失败! 错误信息" + e);
        }
        return "/admin/share/shareInfoList";
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
            String taskType = request.getParameter("q_taskType");
            String status = request.getParameter("q_status");
            Map<String, Object> returnMap = shareService.getShareInfoList(taskType, status, page, rows);
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
            Map<String, Object> returnMap = shareService.getMeShareInfo(userId, pageNum, pageSize);
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
        try {
            if (shareService.deleteShareInfo(id)) {
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
            if (shareService.applyShareInfo(id, userId, userName, telePhone, address, IDCard)) {
                responseUtil.setSuccess(true);
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
