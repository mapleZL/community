package com.phkj.web.controller.notice;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.phkj.core.ConstantsEJS;
import com.phkj.core.HttpJsonResult;
import com.phkj.core.ResponseStateEnum;
import com.phkj.core.ServiceResult;
import com.phkj.core.exception.BusinessException;
import com.phkj.core.redis.RedisComponent;
import com.phkj.core.response.ResponseUtil;
import com.phkj.echarts.component.RedisSychroKeyConfig;
import com.phkj.entity.notice.StBrowse;
import com.phkj.entity.relate.StNoticeBulletinReleaseManage;
import com.phkj.service.notice.IStAppletCollectionManageService;
import com.phkj.service.notice.IStBrowseService;
import com.phkj.service.relate.IStNoticeBulletinReleaseManageService;
import com.phkj.service.repair.IStAppletCommentService;

/**
 * 小区头条操作类
 *                       
 * @Filename: NoticeBulletinReleaseManageController.java
 * @Version: 1.0
 * @date: 2019年5月17日
 * @Author: 陆帅 * @Email: lu1632278229@sina.cn
 *
 */
@Controller
@RequestMapping("/obtain/notice")
public class NoticeBulletinReleaseManageController {

    private static final Logger                   log = LogManager
        .getLogger(NoticeBulletinReleaseManageController.class);

    @Autowired
    private RedisComponent                        redisComponet;
    @Autowired
    private IStAppletCollectionManageService      collectionManageService;
    @Autowired
    private IStBrowseService                      browseService;
    @Autowired
    private IStAppletCommentService               commentService;
    @Autowired
    private IStNoticeBulletinReleaseManageService stNoticeBulletinReleaseManageService;

    @RequestMapping("/add")
    public @ResponseBody ResponseUtil add(@RequestParam("id") Integer id,
                                          HttpServletResponse response) {
        Long count = 0L;
        try {
            String key = RedisSychroKeyConfig.REDIS_CODE_BROWSE_PREFIX + "_" + id;
            count = redisComponet.increment(key, 1L);
        } catch (Exception e) {
            log.error("流量操作失败", e);
            return ResponseUtil.createResp(ResponseStateEnum.STATUS_SERVER_ERROR.getCode(), null,
                false, null);
        }
        return ResponseUtil.createResp(ResponseStateEnum.STATUS_OK.getCode(), null, true, count);
    }

    /**
     * 头条通用查询
     * @param start
     * @param pageNum
     * @param type
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "/pageList", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<StNoticeBulletinReleaseManage>> list(Integer pageNum,
                                                                                  Integer pageSize,
                                                                                  String type,
                                                                                  Integer memberId,
                                                                                  HttpServletRequest request,
                                                                                  ModelMap dataMap) {
        int start = (pageNum - 1) * pageSize;
        ServiceResult<List<StNoticeBulletinReleaseManage>> serviceResult = stNoticeBulletinReleaseManageService
            .pageList(start, pageSize, type);
        List<StNoticeBulletinReleaseManage> list = serviceResult.getResult();
        StBrowse stBrowse = null;
        Long collectionManage = null;
        Long comment = null;
        Long browse = 0L;
        if (list != null) {
            String redisKey = RedisSychroKeyConfig.REDIS_CODE_BROWSE_PREFIX;
            for (StNoticeBulletinReleaseManage notice : list) {
                // 获取流量
                redisKey += notice.getId();
                browse = redisComponet.increment(redisKey, 0L);
                if (browse == 0) {
                    stBrowse = browseService.getBrowseByNoticeId(notice.getId()).getResult();
                    if (stBrowse != null && stBrowse.getBrowseVolume() > 0) {
                        redisComponet.increment(redisKey, stBrowse.getBrowseVolume());
                    }
                    browse = stBrowse.getBrowseVolume();
                }
                
                notice.setRate(browse);
                    
                // 获取收藏数量
                collectionManage = collectionManageService.getCountByNoticeid(notice.getId())
                    .getResult();
                if (collectionManage == null) {
                    collectionManage = 0L;
                }
                notice.setCollect(collectionManage);
                // 获取评论数量
                comment = commentService.getCountByRId(notice.getId(), "Notice").getResult();
                if (comment == null) {
                    comment = 0L;
                }
                notice.setComment(comment);
            }
        }
        Integer count = stNoticeBulletinReleaseManageService.getCount(type);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }

        HttpJsonResult<List<StNoticeBulletinReleaseManage>> jsonResult = new HttpJsonResult<List<StNoticeBulletinReleaseManage>>();
        jsonResult.setTotal(count);
        jsonResult.setData(serviceResult.getResult());
        return jsonResult;
    }

    /**
     * 获取活动或者是头像详情
     * @param id
     * @param response
     * @return
     */
    @RequestMapping(value = "/detail", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<StNoticeBulletinReleaseManage> list(Long id,
                                                                            HttpServletResponse response) {
        ServiceResult<StNoticeBulletinReleaseManage> serviceResult = stNoticeBulletinReleaseManageService
            .getNoticeById(id);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }

        HttpJsonResult<StNoticeBulletinReleaseManage> jsonResult = new HttpJsonResult<StNoticeBulletinReleaseManage>();
        jsonResult.setData(serviceResult.getResult());

        return jsonResult;
    }
}
