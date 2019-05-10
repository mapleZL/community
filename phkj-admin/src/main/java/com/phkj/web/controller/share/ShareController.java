package com.phkj.web.controller.share;


import com.phkj.core.response.ResponseUtil;
import com.phkj.entity.share.StAppletShareInfo;
import com.phkj.service.share.ShareService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
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
    @RequestMapping("/getShareInfoDetail")
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

}
