package com.phkj.web.controller.notice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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
import com.phkj.core.NoticeSourceConfig;
import com.phkj.core.ResponseStateEnum;
import com.phkj.core.ServiceResult;
import com.phkj.core.exception.BusinessException;
import com.phkj.core.redis.RedisComponent;
import com.phkj.core.response.ResponseUtil;
import com.phkj.echarts.component.RedisSychroKeyConfig;
import com.phkj.entity.notice.StAppletUserBrowse;
import com.phkj.entity.notice.StBrowse;
import com.phkj.entity.relate.StNoticeBulletinReleaseManage;
import com.phkj.entity.relate.SystemAppfile;
import com.phkj.service.notice.IStAppletCollectionManageService;
import com.phkj.service.notice.IStAppletUserBrowseService;
import com.phkj.service.notice.IStBrowseService;
import com.phkj.service.relate.IStNoticeBulletinReleaseManageService;
import com.phkj.service.relate.ISystemAppfileService;
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
    private ISystemAppfileService                 systemAppfileService;
    @Autowired
    private IStBrowseService                      browseService;
    @Autowired
    private IStAppletCommentService               commentService;
    @Autowired
    private IStNoticeBulletinReleaseManageService stNoticeBulletinReleaseManageService;
    @Autowired
    private IStAppletUserBrowseService            stAppletUserBrowseService;

    @RequestMapping("/add")
    public @ResponseBody ResponseUtil add(@RequestParam("id") Integer id, Integer memberId,
                                          HttpServletResponse response) {
        Long count = 0L;
        try {
            String key = RedisSychroKeyConfig.REDIS_CODE_BROWSE_PREFIX + id;
            count = redisComponet.increment(key, 1L);
            if (memberId != null) {
                stAppletUserBrowseService.updateOrCreateBrowse(id, memberId);
            }
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
        Map<String, String> sourceMap = new NoticeSourceConfig().getSourceMap();
        if (list != null) {
            String redisKey = null;
            for (StNoticeBulletinReleaseManage notice : list) {
                // 获取流量，先从redis查询，查询无果从MySQL查询
                redisKey = RedisSychroKeyConfig.REDIS_CODE_BROWSE_PREFIX + notice.getId();
                browse = redisComponet.increment(redisKey, 0L);
                if (browse == 0) {
                    stBrowse = browseService.getBrowseByNoticeId(notice.getId()).getResult();
                    if (stBrowse != null) {
                        browse = stBrowse.getBrowseVolume();
                    }
                }

                notice.setRate(browse);
                StAppletUserBrowse stAppletUserBrowse = stAppletUserBrowseService.getUserBrowse(notice.getId().intValue(), memberId).getResult();
                if (stAppletUserBrowse != null && stAppletUserBrowse.getBrowse() > 0) {
                    notice.setHasBrowse(true);
                }

                // 获取收藏数量
                collectionManage = collectionManageService.getCountByNoticeid(notice.getId())
                    .getResult();
                if (collectionManage == null) {
                    collectionManage = 0L;
                }
                notice.setCollect(collectionManage);
                Integer count = collectionManageService.getCollectionCount(memberId, notice.getId())
                    .getResult();
                if (count != null && count > 0) {
                    notice.setHasCollect(true);
                }

                // 获取评论数量
                comment = commentService.getCountByRId(notice.getId(), "notice").getResult();
                if (comment == null) {
                    comment = 0L;
                }
                notice.setComment(comment);
                count = commentService.getCommentCount(memberId, notice.getId());
                if (count != null && count > 0) {
                    notice.setHasComment(true);
                }

                // 获取头条图片路径
                List<SystemAppfile> pics = systemAppfileService
                    .getPicList("notice", notice.getId(), notice.getType()).getResult();
                // 存在图片取一条作为展示
                if (pics != null && pics.size() > 0) {
                    notice.setImg(Arrays.asList(pics.get(0).getPath()));
                }

                // 设置来源
                notice.setSourceName(sourceMap.get(notice.getSourceType()));
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
     * 获取活动或者是头条详情
     * @param id
     * @param response
     * @return
     */
    @RequestMapping(value = "/detail", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<StNoticeBulletinReleaseManage> list(Long id,
                                                                            Integer memberId,
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
        Map<String, String> sourceMap = new NoticeSourceConfig().getSourceMap();
        StNoticeBulletinReleaseManage releaseManage = serviceResult.getResult();
        releaseManage.setSourceName(sourceMap.get(releaseManage.getSourceType()));
        StBrowse stBrowse = null;
        Long collectionManage = null;
        Long comment = null;
        Long browse = 0L;

        // 获取头条图片路径
        List<SystemAppfile> pics = systemAppfileService
            .getPicList("notice", releaseManage.getId(), releaseManage.getType()).getResult();
        // 存在图片取一条作为展示
        List<String> list = new ArrayList<>();
        if (pics != null && pics.size() > 0) {
            for (SystemAppfile systemAppfile : pics) {
                list.add(systemAppfile.getPath());
            }
            releaseManage.setImg(list);
        }

        // 获取流量，先从redis查询，查询无果从MySQL查询
        String redisKey = RedisSychroKeyConfig.REDIS_CODE_BROWSE_PREFIX + id;
        browse = redisComponet.increment(redisKey, 0L);
        if (browse == 0) {
            stBrowse = browseService.getBrowseByNoticeId(id).getResult();
            if (stBrowse != null) {
                browse = stBrowse.getBrowseVolume();
            }
        }

        releaseManage.setRate(browse);
        StAppletUserBrowse stAppletUserBrowse = stAppletUserBrowseService.getUserBrowse(id.intValue(), memberId).getResult();
        if (stAppletUserBrowse != null && stAppletUserBrowse.getBrowse() > 0) {
            releaseManage.setHasBrowse(true);
        }

        // 获取收藏数量
        collectionManage = collectionManageService.getCountByNoticeid(id)
            .getResult();
        if (collectionManage == null) {
            collectionManage = 0L;
        }
        releaseManage.setCollect(collectionManage);
        Integer count = collectionManageService.getCollectionCount(memberId, id)
            .getResult();
        if (count != null && count > 0) {
            releaseManage.setHasCollect(true);
        }

        // 获取评论数量
        comment = commentService.getCountByRId(id, "notice").getResult();
        if (comment == null) {
            comment = 0L;
        }
        releaseManage.setComment(comment);
        count = commentService.getCommentCount(memberId, id);
        if (count != null && count > 0) {
            releaseManage.setHasComment(true);
        }

        HttpJsonResult<StNoticeBulletinReleaseManage> jsonResult = new HttpJsonResult<StNoticeBulletinReleaseManage>();
        jsonResult.setData(releaseManage);

        return jsonResult;
    }

}
