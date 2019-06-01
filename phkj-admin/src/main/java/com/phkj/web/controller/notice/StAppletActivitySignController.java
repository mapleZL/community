package com.phkj.web.controller.notice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.phkj.core.ConstantsEJS;
import com.phkj.core.HttpJsonResult;
import com.phkj.core.NoticeSourceConfig;
import com.phkj.core.PagerInfo;
import com.phkj.core.ResponseStateEnum;
import com.phkj.core.ServiceResult;
import com.phkj.core.WebUtil;
import com.phkj.core.exception.BusinessException;
import com.phkj.core.redis.RedisComponent;
import com.phkj.echarts.component.MemberPropertyStatus;
import com.phkj.echarts.component.RedisSychroKeyConfig;
import com.phkj.entity.notice.StAppletActivitySign;
import com.phkj.entity.notice.StAppletUserBrowse;
import com.phkj.entity.notice.StBrowse;
import com.phkj.entity.relate.StNoticeBulletinReleaseManage;
import com.phkj.entity.relate.SystemAppfile;
import com.phkj.entity.system.SystemAdmin;
import com.phkj.service.notice.IStAppletActivitySignService;
import com.phkj.service.notice.IStAppletCollectionManageService;
import com.phkj.service.notice.IStAppletUserBrowseService;
import com.phkj.service.notice.IStBrowseService;
import com.phkj.service.relate.IStNoticeBulletinReleaseManageService;
import com.phkj.service.relate.ISystemAppfileService;
import com.phkj.service.repair.IStAppletCommentService;
import com.phkj.web.util.WebAdminSession;

@Controller
@RequestMapping("/notice/activity")
public class StAppletActivitySignController {

    private static final Logger                   log = LogManager
        .getLogger(StAppletActivitySignController.class);

    @Autowired
    IStAppletActivitySignService                  activitySignService;
    @Autowired
    private RedisComponent                        redisComponet;
    @Autowired
    private IStBrowseService                      browseService;
    @Autowired
    private IStAppletCommentService               commentService;
    @Autowired
    private IStAppletCollectionManageService      collectionManageService;
    @Autowired
    private ISystemAppfileService                 systemAppfileService;
    @Autowired
    private IStNoticeBulletinReleaseManageService stNoticeBulletinReleaseManageService;
    @Autowired
    private IStAppletUserBrowseService            stAppletUserBrowseService;

    /**
     * 初始化列表页面
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "", method = { RequestMethod.GET })
    public String getList(Map<String, Object> dataMap) throws Exception {
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return "admin/notice/noticeactivitylist";
    }

    /**
     * 保存报名信息
     * @param stAppletActivitySign
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public @ResponseBody ServiceResult<Integer> add(@RequestBody StAppletActivitySign stAppletActivitySign) {
        ServiceResult<Integer> serviceResult = new ServiceResult<>();
        try {
            stAppletActivitySign.setCreateTime(new Date());
            stAppletActivitySign.setSts(MemberPropertyStatus.STATE_1);
            activitySignService.saveStAppletActivitySign(stAppletActivitySign);
            serviceResult.setResult(MemberPropertyStatus.STATE_1);
        } catch (Exception e) {
            log.error("活动报名参与失败", e);
            serviceResult.setError(ResponseStateEnum.STATUS_SERVER_ERROR.getCode(), "活动报名参与失败");
            serviceResult.setSuccess(false);
        }
        return serviceResult;
    }

    /**
     * 查询报名活动详情
     * @param stAppletActivitySign
     * @return
     */
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public @ResponseBody ServiceResult<StAppletActivitySign> detail(Integer memberId,
                                                                    Integer rActivityId) {
        ServiceResult<StAppletActivitySign> serviceResult = new ServiceResult<>();
        try {
            serviceResult = activitySignService.getActivityByUser(memberId, rActivityId);
        } catch (Exception e) {
            log.error("查询报名活动详情", e);
            serviceResult.setError(ResponseStateEnum.STATUS_SERVER_ERROR.getCode(), "查询报名活动详情");
            serviceResult.setSuccess(false);
        }
        return serviceResult;
    }

    /**
     * 手机端查询活动列表
     * @param stAppletActivitySign
     * @return
     */
    @RequestMapping(value = "/participateList", method = RequestMethod.GET)
    public @ResponseBody HttpJsonResult<List<StNoticeBulletinReleaseManage>> getParticipateList(Integer memberId,
                                                                                                Integer start,
                                                                                                String villageCode,
                                                                                                Integer pageSize) {
        HttpJsonResult<List<StNoticeBulletinReleaseManage>> serviceResult = new HttpJsonResult<>();
        List<StNoticeBulletinReleaseManage> returnList = new ArrayList<>();
        try {
            ServiceResult<List<StAppletActivitySign>> result = activitySignService
                .getParticipateList(memberId, start, pageSize, villageCode);
            List<StAppletActivitySign> list = result.getResult();
            if (list != null) {
                StBrowse stBrowse = null;
                Long collectionManage = null;
                Long comment = null;
                Long browse = 0L;
                String redisKey = RedisSychroKeyConfig.REDIS_CODE_BROWSE_PREFIX;
                Map<String, String> sourceMap = new NoticeSourceConfig().getSourceMap();
                StNoticeBulletinReleaseManage notice = null;
                for (StAppletActivitySign activitySign : list) {
                    notice = stNoticeBulletinReleaseManageService
                        .getNoticeById(activitySign.getrActivityId()).getResult();
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
                    StAppletUserBrowse stAppletUserBrowse = stAppletUserBrowseService
                        .getUserBrowse(activitySign.getrActivityId().intValue(), memberId).getResult();
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
                    Integer count = collectionManageService.getCollectionCount(memberId, activitySign.getrActivityId())
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
                    count = commentService.getCommentCount(memberId, activitySign.getrActivityId());
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
                    returnList.add(notice);
                }
                serviceResult.setData(returnList);
                Integer count = activitySignService.getCount(memberId, villageCode);
                serviceResult.setTotal(count);
            }
        } catch (Exception e) {
            log.error("查询参加活动列表", e);
            serviceResult.setMessage("查询参加活动列表");
        }
        return serviceResult;
    }

    /**
     * 审核活动
     * @param id
     * @param sts
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/changeSts", method = RequestMethod.POST)
    public @ResponseBody ServiceResult<Integer> examine(Integer id, Integer sts,
                                                        HttpServletRequest request,
                                                        HttpServletResponse response) {
        ServiceResult<Integer> serviceResult = new ServiceResult<Integer>();
        try {
            SystemAdmin adminUser = WebAdminSession.getAdminUser(request);
            StAppletActivitySign stAppletActivitySign = activitySignService
                .getStAppletActivitySignById(id).getResult();
            stAppletActivitySign.setExamineId(adminUser.getId());
            stAppletActivitySign.setExamineTime(new Date());
            stAppletActivitySign.setSts(sts);
            serviceResult = activitySignService.updateStAppletActivitySign(stAppletActivitySign);
        } catch (Exception e) {
            log.error("审核活动失败", e);
            serviceResult.setError(ResponseStateEnum.STATUS_SERVER_ERROR.getCode(), "审核活动失败");
            serviceResult.setSuccess(false);
        }
        return serviceResult;
    }

    @RequestMapping(value = "/list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<StAppletActivitySign>> list(HttpServletRequest request,
                                                                         ModelMap dataMap) {
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        SystemAdmin adminUser = WebAdminSession.getAdminUser(request);
        // 后台管理人员只能查看对应自己小区的参与情况
        queryMap.put("q_village_code", adminUser.getVillageCode());
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        ServiceResult<List<StAppletActivitySign>> serviceResult = activitySignService.list(queryMap,
            pager);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }

        HttpJsonResult<List<StAppletActivitySign>> jsonResult = new HttpJsonResult<List<StAppletActivitySign>>();
        jsonResult.setRows((List<StAppletActivitySign>) serviceResult.getResult());
        jsonResult.setTotal(pager.getRowsCount());

        return jsonResult;
    }
}
