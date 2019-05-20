package com.phkj.web.controller.notice;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.phkj.core.ResponseStateEnum;
import com.phkj.core.ServiceResult;
import com.phkj.core.response.ResponseUtil;
import com.phkj.entity.notice.StAppletCollectionManage;
import com.phkj.service.notice.IStAppletCollectionManageService;
import com.phkj.web.controller.BaseController;

/**
 * 收藏处理类
 *                       
 * @Filename: StAppletCollectionManageController.java
 * @Version: 1.0
 * @date: 2019年5月17日
 * @Author: 陆帅 * @Email: lu1632278229@sina.cn
 *
 */
@Controller
@RequestMapping(value = "/collection/notice")
public class StAppletCollectionManageController extends BaseController {

    @Autowired
    private IStAppletCollectionManageService collectionManageService;

    /**
     * 头条活动收藏
     * @param appletCollectionManage
     * @param request
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public ResponseUtil add(@RequestBody StAppletCollectionManage appletCollectionManage,
                            HttpServletRequest request) {
        if (appletCollectionManage.getMemberId() == null
            || appletCollectionManage.getNoticeId() == null) {
            return ResponseUtil.createResp(ResponseStateEnum.STATUS_SERVER_ERROR.getCode(),
                "用户id或者关联通告id失效", false, null);
        }
        ServiceResult<Integer> serviceResult = collectionManageService
            .saveStAppletCollectionManage(appletCollectionManage);
        return ResponseUtil.createResp(ResponseStateEnum.STATUS_OK.getCode(), null, true,
            serviceResult.getResult());
    }

    /**
     * 取消收藏
     * @param memberId
     * @param noticeId
     * @param request
     * @return
     */
    @RequestMapping(value = "/cancel", method = RequestMethod.POST)
    @ResponseBody
    public ResponseUtil cancel(Integer memberId, Long noticeId,
                               HttpServletRequest request) {
        collectionManageService.cancelCollection(memberId, noticeId);
        return ResponseUtil.createResp(ResponseStateEnum.STATUS_OK.getCode(), null, true, "");
    }
}
