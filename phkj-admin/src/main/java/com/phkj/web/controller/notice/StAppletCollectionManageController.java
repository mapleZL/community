package com.phkj.web.controller.notice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.phkj.core.HttpJsonResult;
import com.phkj.core.NoticeSourceConfig;
import com.phkj.core.ResponseStateEnum;
import com.phkj.core.ServiceResult;
import com.phkj.core.redis.RedisComponent;
import com.phkj.core.response.ResponseUtil;
import com.phkj.echarts.component.RedisSychroKeyConfig;
import com.phkj.entity.notice.StAppletCollectionManage;
import com.phkj.entity.notice.StBrowse;
import com.phkj.entity.relate.StNoticeBulletinReleaseManage;
import com.phkj.entity.relate.SystemAppfile;
import com.phkj.service.notice.IStAppletCollectionManageService;
import com.phkj.service.notice.IStBrowseService;
import com.phkj.service.relate.IStNoticeBulletinReleaseManageService;
import com.phkj.service.relate.ISystemAppfileService;
import com.phkj.service.repair.IStAppletCommentService;
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
    private IStAppletCollectionManageService      collectionManageService;
    @Autowired
    private RedisComponent                        redisComponet;
    @Autowired
    private IStBrowseService                      browseService;
    @Autowired
    private IStAppletCommentService               commentService;
    @Autowired
    private ISystemAppfileService                 systemAppfileService;
    @Autowired
    private IStNoticeBulletinReleaseManageService stNoticeBulletinReleaseManageService;

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
        appletCollectionManage.setCreateTime(new Date());
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
    public ResponseUtil cancel(Integer memberId, Long noticeId, HttpServletRequest request) {
        collectionManageService.cancelCollection(memberId, noticeId);
        return ResponseUtil.createResp(ResponseStateEnum.STATUS_OK.getCode(), null, true, "");
    }

    /**
     * 手机端查询活动列表
     * @param stAppletActivitySign
     * @return
     */
    @RequestMapping(value = "/collectionList", method = RequestMethod.GET)
    public @ResponseBody HttpJsonResult<List<StNoticeBulletinReleaseManage>> getParticipateList(Integer memberId, Integer start, Integer pageSize) {
        HttpJsonResult<List<StNoticeBulletinReleaseManage>> serviceResult = new HttpJsonResult<>();
        List<StNoticeBulletinReleaseManage> returnList = new ArrayList<>();
        try {
            ServiceResult<List<StAppletCollectionManage>> result = collectionManageService
                .getCollectionList(memberId, start, pageSize);
            List<StAppletCollectionManage> list = result.getResult();
            if (list != null) {
                StBrowse stBrowse = null;
                Long collectionManage = null;
                Long comment = null;
                Long browse = 0L;
                String redisKey = RedisSychroKeyConfig.REDIS_CODE_BROWSE_PREFIX;
                Map<String, String> sourceMap = new NoticeSourceConfig().getSourceMap();
                StNoticeBulletinReleaseManage notice = null;
                for (StAppletCollectionManage activitySign : list) {
                    notice = stNoticeBulletinReleaseManageService
                        .getNoticeById(activitySign.getNoticeId()).getResult();
                    // 获取流量，先从redis查询，查询无果从MySQL查询
                    redisKey += notice.getId();
                    browse = redisComponet.increment(redisKey, 0L);
                    if (browse == 0) {
                        stBrowse = browseService.getBrowseByNoticeId(notice.getId()).getResult();
                        if (stBrowse != null) {
                            browse = stBrowse.getBrowseVolume();
                        }
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
                    comment = commentService.getCountByRId(notice.getId(), "notice").getResult();
                    if (comment == null) {
                        comment = 0L;
                    }
                    notice.setComment(comment);

                    // 获取头条图片路径
                    List<SystemAppfile> pics = systemAppfileService
                        .getPicList("notice", notice.getId(), notice.getType()).getResult();
                    // 存在图片取一条作为展示
                    if (pics != null && pics.size() > 0) {
                        notice.setImg(Arrays.asList(pics.get(0).getPath()));
                    }

                    // 设置来源
                    notice.setSourceName(sourceMap.get(notice.getSourceType()));
                    returnList.add(notice);
                }
                serviceResult.setData(returnList);
                Integer count = collectionManageService.getCount(memberId);
                serviceResult.setTotal(count);
            }
        } catch (Exception e) {
            log.error("查询参加活动列表", e);
            serviceResult.setMessage("查询参加活动列表");
        }
        return serviceResult;
    }
}
