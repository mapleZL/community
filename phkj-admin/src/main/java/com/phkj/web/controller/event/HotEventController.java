package com.phkj.web.controller.event;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.phkj.core.ConstantsEJS;
import com.phkj.core.HttpJsonResult;
import com.phkj.core.PagerInfo;
import com.phkj.core.ServiceResult;
import com.phkj.core.StringUtil;
import com.phkj.core.WebUtil;
import com.phkj.core.exception.BusinessException;
import com.phkj.core.freemarkerutil.DomainUrlUtil;
import com.phkj.core.redis.RedisComponent;
import com.phkj.echarts.component.RedisSychroKeyConfig;
import com.phkj.entity.event.StAppletHotEvents;
import com.phkj.entity.notice.StAppletUserBrowse;
import com.phkj.entity.notice.StBrowse;
import com.phkj.entity.system.SystemAdmin;
import com.phkj.service.event.IStAppletHotEventsService;
import com.phkj.service.notice.IStAppletCollectionManageService;
import com.phkj.service.notice.IStAppletUserBrowseService;
import com.phkj.service.notice.IStBrowseService;
import com.phkj.service.repair.IStAppletCommentService;
import com.phkj.web.util.WebAdminSession;

/**
 * 
 *                       
 * @Filename: HotEventController.java
 * @Version: 1.0
 * @date: 2019年6月13日
 * @Author: 陆帅 * @Email: lu1632278229@sina.cn
 *
 */
@Controller
@RequestMapping(value = "/admin/event")
public class HotEventController {

    @Autowired
    private IStAppletHotEventsService        hotEventsService;
    @Autowired
    private IStBrowseService                 browseService;
    @Autowired
    private IStAppletCommentService          commentService;
    @Autowired
    private RedisComponent                   redisComponet;
    @Autowired
    private IStAppletUserBrowseService       stAppletUserBrowseService;
    @Autowired
    private IStAppletCollectionManageService collectionManageService;
    
    @RequestMapping(value = "/add", method = { RequestMethod.GET })
    public String getList(Map<String, Object> dataMap) throws Exception {
        return "/admin/event/hoteventadd";
    }
    
    //待审核商品列表
    @RequestMapping(value = "/view", method = { RequestMethod.GET })
    public String examineSale(HttpServletRequest request, Map<String, Object> dataMap) {
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return "/admin/event/eventexamine";
    }

    /**
     * 新增或修改热门活动
     * @param event
     * @param request
     * @param dataMap
     * @return
     * @throws IOException
     * @throws ParseException 
     */
    @ResponseBody
    @RequestMapping(value = "/create", method = { RequestMethod.POST })
    public HttpJsonResult<Object> create(StAppletHotEvents event, HttpServletRequest request,
                                         Map<String, Object> dataMap) throws IOException, ParseException {
        HttpJsonResult<Object> jsonResult = new HttpJsonResult<>();
        SystemAdmin user = WebAdminSession.getAdminUser(request);
        if (null == user) {
            jsonResult.setMessage("登录超时，请重新登录");
            jsonResult.setBackUrl(DomainUrlUtil.getEJS_URL_RESOURCES() + "/admin/login.html");
            return jsonResult;
        }
        ServiceResult<Integer> serviceResult = createOrUpdateEvent(event, request, user);
        if (!serviceResult.getSuccess()) {
            jsonResult.setMessage(serviceResult.getMessage());
        }
        return jsonResult;
    }

    private ServiceResult<Integer> createOrUpdateEvent(StAppletHotEvents event,
                                                       HttpServletRequest request,
                                                       SystemAdmin user) throws ParseException {
        ServiceResult<Integer> result = new ServiceResult<>();
        if (event.getId() > 0) {
            event.setCreateUserId(user.getId());
            event.setCreateTime(new Date());
        }
        event.setVillageCode(user.getVillageCode());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String postBegin = request.getParameter("begin");
        String postEnd = request.getParameter("end");
        
        event.setPostBegin(sdf.parse(postBegin));
        event.setPostEnd(sdf.parse(postEnd));
        
        String pics = request.getParameter("imageSrc");
        if (!StringUtil.isEmpty(pics)) {
            event.setImg(pics);
        }
        if (event.getId() > 0) {
            result = hotEventsService.updateStAppletHotEvents(event);
        } else {
            result = hotEventsService.saveStAppletHotEvents(event);
        }

        return result;
    }

    /**
     * 后台列表查询
     * @param request
     * @return
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping(value = "/list", method = { RequestMethod.GET })
    public HttpJsonResult<List<StAppletHotEvents>> list(HttpServletRequest request,
                                                        Map<String, Object> dataMap) throws IOException {
        HttpJsonResult<List<StAppletHotEvents>> jsonResult = new HttpJsonResult<>();
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        String title = request.getParameter("q_title");
        String sourceType = request.getParameter("q_sourceId");
        dataMap.put("q_title", title);
        if (StringUtils.isNotBlank(sourceType) && !"0".equals(sourceType)) {
            dataMap.put("q_sourceType", sourceType);
        }
        SystemAdmin user = WebAdminSession.getAdminUser(request);
        dataMap.put("q_villageCode", user.getVillageCode());
        ServiceResult<List<StAppletHotEvents>> serviceResult = hotEventsService.getPageList(pager,
            dataMap);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }
        jsonResult.setRows(serviceResult.getResult());
        jsonResult.setTotal(pager.getRowsCount());
        return jsonResult;
    }

    /**
     * 公众号端查询活动列表
     * @param start
     * @param size
     * @param villageCode
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/page", method = { RequestMethod.GET })
    public HttpJsonResult<List<StAppletHotEvents>> wxPage(Integer start, Integer size,
                                                          String villageCode, Integer memberId,
                                                          HttpServletRequest request) {
        HttpJsonResult<List<StAppletHotEvents>> jsonResult = new HttpJsonResult<>();
        ServiceResult<List<StAppletHotEvents>> serviceResult = hotEventsService.wxPage(start, size,
            villageCode);
        Integer count = hotEventsService.wxCount(villageCode);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }
        List<StAppletHotEvents> list = serviceResult.getResult();
        if (list != null) {
            StBrowse stBrowse = null;
            String redisKey = null;
            Long collectionManage = null;
            Long comment = null;
            Long browse = 0L;
            for (StAppletHotEvents event : list) {
                if (StringUtils.isNotBlank(event.getImg())) {
                    String[] imgs = event.getImg().split(",");
                    event.setImgs(Arrays.asList(imgs));
                }

                // 获取流量，先从redis查询，查询无果从MySQL查询
                redisKey = RedisSychroKeyConfig.REDIS_CODE_BROWSE_PREFIX + event.getId();
                browse = redisComponet.increment(redisKey, 0L);
                if (browse == 0) {
                    stBrowse = browseService.getBrowseByNoticeId(Long.valueOf(event.getId()))
                        .getResult();
                    if (stBrowse != null) {
                        browse = stBrowse.getBrowseVolume();
                    }
                }

                event.setRate(browse);
                StAppletUserBrowse stAppletUserBrowse = stAppletUserBrowseService
                    .getUserBrowse(event.getId().intValue(), memberId).getResult();
                if (stAppletUserBrowse != null && stAppletUserBrowse.getBrowse() > 0) {
                    event.setHasBrowse(true);
                }

                // 获取收藏数量
                collectionManage = collectionManageService
                    .getCountByNoticeid(Long.valueOf(Long.valueOf(event.getId()))).getResult();
                if (collectionManage == null) {
                    collectionManage = 0L;
                }
                event.setCollect(collectionManage);
                Integer num = collectionManageService
                    .getCollectionCount(memberId, Long.valueOf(event.getId())).getResult();
                if (num != null && num > 0) {
                    event.setHasCollect(true);
                }

                // 获取评论数量
                comment = commentService.getCountByRId(Long.valueOf(event.getId()), "notice")
                    .getResult();
                if (comment == null) {
                    comment = 0L;
                }
                event.setComment(comment);
                num = commentService.getCommentCount(memberId, Long.valueOf(event.getId()));
                if (num != null && num > 0) {
                    event.setHasComment(true);
                }
            }
        }
        jsonResult.setRows(serviceResult.getResult());
        jsonResult.setTotal(count);
        return jsonResult;
    }

    /**
     * 查询详情
     * @param id
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/detail", method = { RequestMethod.GET })
    public HttpJsonResult<StAppletHotEvents> detail(Long id, Integer memberId, HttpServletRequest request) {
        HttpJsonResult<StAppletHotEvents> result = new HttpJsonResult<>();
        StAppletHotEvents event = hotEventsService.getStAppletHotEventsById(id.intValue()).getResult();
        
        StBrowse stBrowse = null;
        String redisKey = null;
        Long collectionManage = null;
        Long comment = null;
        Long browse = 0L;
        
        if (StringUtils.isNotBlank(event.getImg())) {
            String[] imgs = event.getImg().split(",");
            event.setImgs(Arrays.asList(imgs));
        }

        // 获取流量，先从redis查询，查询无果从MySQL查询
        redisKey = RedisSychroKeyConfig.REDIS_CODE_BROWSE_PREFIX + event.getId();
        browse = redisComponet.increment(redisKey, 0L);
        if (browse == 0) {
            stBrowse = browseService.getBrowseByNoticeId(id)
                .getResult();
            if (stBrowse != null) {
                browse = stBrowse.getBrowseVolume();
            }
        }

        event.setRate(browse);
        StAppletUserBrowse stAppletUserBrowse = stAppletUserBrowseService
            .getUserBrowse(id.intValue(), memberId).getResult();
        if (stAppletUserBrowse != null && stAppletUserBrowse.getBrowse() > 0) {
            event.setHasBrowse(true);
        }

        // 获取收藏数量
        collectionManage = collectionManageService
            .getCountByNoticeid(id).getResult();
        if (collectionManage == null) {
            collectionManage = 0L;
        }
        event.setCollect(collectionManage);
        Integer num = collectionManageService
            .getCollectionCount(memberId, id).getResult();
        if (num != null && num > 0) {
            event.setHasCollect(true);
        }

        // 获取评论数量
        comment = commentService.getCountByRId(id, "notice")
            .getResult();
        if (comment == null) {
            comment = 0L;
        }
        event.setComment(comment);
        num = commentService.getCommentCount(memberId, id);
        if (num != null && num > 0) {
            event.setHasComment(true);
        }
        
        result.setData(event);
        return result;
    }

    /**
     * 热门活动上线操作
     * @param request
     * @param response
     * @param id
     * @param status
     */
    @RequestMapping(value = "/handler", method = { RequestMethod.POST })
    public void handler(HttpServletRequest request, HttpServletResponse response, Integer id,
                        Integer status) {
        response.setContentType("text/plain;charset=utf-8");
        String msg = "";
        PrintWriter pw = null;
        try {
            StAppletHotEvents events = new StAppletHotEvents();
            if (id != null) {
                events.setId(id);
                events.setStatus(status);
                hotEventsService.updateStAppletHotEvents(events);
                msg = "上线成功！";
            }
            pw = response.getWriter();
        } catch (Exception e) {
            e.printStackTrace();
            msg = e.getMessage();
        }
        pw.print(msg);
    }
}
