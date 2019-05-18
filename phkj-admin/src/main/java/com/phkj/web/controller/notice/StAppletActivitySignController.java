package com.phkj.web.controller.notice;

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
import com.phkj.core.PagerInfo;
import com.phkj.core.ResponseStateEnum;
import com.phkj.core.ServiceResult;
import com.phkj.core.WebUtil;
import com.phkj.core.exception.BusinessException;
import com.phkj.echarts.component.MemberPropertyStatus;
import com.phkj.entity.notice.StAppletActivitySign;
import com.phkj.entity.system.SystemAdmin;
import com.phkj.service.notice.IStAppletActivitySignService;
import com.phkj.web.util.WebAdminSession;

@Controller
@RequestMapping("/notice/activity")
public class StAppletActivitySignController {

    private static final Logger  log = LogManager.getLogger(StAppletActivitySignController.class);

    @Autowired
    IStAppletActivitySignService activitySignService;

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
            serviceResult = activitySignService.saveStAppletActivitySign(stAppletActivitySign);
        } catch (Exception e) {
            log.error("活动报名参与失败", e);
            serviceResult.setError(ResponseStateEnum.STATUS_SERVER_ERROR.getCode(), "活动报名参与失败");
            serviceResult.setSuccess(false);
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
