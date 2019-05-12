package com.phkj.web.controller.repair;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
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
import com.phkj.core.response.ResponseUtil;
import com.phkj.echarts.component.RepairStatus;
import com.phkj.entity.repair.StAppletRepair;
import com.phkj.entity.repair.StAppletRepairMember;
import com.phkj.entity.system.SystemAdmin;
import com.phkj.service.repair.IStAppletRepairMemberService;
import com.phkj.service.repair.IStAppletRepairService;
import com.phkj.web.controller.BaseController;
import com.phkj.web.util.WebAdminSession;

/**
 * @author ：zl
 * @date ：Created in 2019/5/9 19:50
 * @description：业务报修controller层
 * @modified By：
 * @version: 0.0.1$
 */
@Controller
@RequestMapping(value = "/admin/property/repair")
public class StAppletRepairController extends BaseController {

    @Autowired
    IStAppletRepairService       stAppletRepairService;
    @Autowired
    IStAppletRepairMemberService stAppletRepairMemberService;

    /**
     * 初始化列表页面
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "", method = { RequestMethod.GET })
    public String getList(Map<String, Object> dataMap) throws Exception {
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return "admin/repair/member/repairrecordlist";
    }
    
    @RequestMapping(value = "/undo", method = { RequestMethod.GET })
    public String getUndoList(Map<String, Object> dataMap) throws Exception {
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return "admin/repair/member/repairundolist";
    }

    /**
     * create by: zl
     * description: 添加物业报修
     * create time:
     *
     * @return
     * @Param: stAppletRepair
     */
    @RequestMapping(value = "/add", method = { RequestMethod.POST })
    @ResponseBody
    public ResponseUtil save(@RequestBody StAppletRepair stAppletRepair) {
        ResponseUtil flag = checkParam(stAppletRepair);
        if (flag != null) {
            return flag;
        }
        ServiceResult<Integer> result;
        if (stAppletRepair.getId() != null && stAppletRepair.getId() != 0) {
            // 编辑
            result = stAppletRepairService.updateStAppletRepair(stAppletRepair);
        } else {
            // 新增
            result = stAppletRepairService.saveStAppletRepair(stAppletRepair);
        }
        return ResponseUtil.createResp(result.getCode(), result.getMessage(), true,
            result.getResult());
    }

    /**
     * 物业维修人员列表查询
     * @param createUserId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/repairList", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<StAppletRepair>> repairList(HttpServletRequest request,
                                                                         ModelMap dataMap) {
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        SystemAdmin adminUser = WebAdminSession.getAdminUser(request);
        //TODO 此处可以优化
        // 维修人员
        if (adminUser.getRoleId() == 5) {
            queryMap.put("q_repair_id", adminUser.getId().toString());
        }
        
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        ServiceResult<List<StAppletRepair>> serviceResult = stAppletRepairService.page(queryMap,
            pager);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }

        HttpJsonResult<List<StAppletRepair>> jsonResult = new HttpJsonResult<List<StAppletRepair>>();
        jsonResult.setRows((List<StAppletRepair>) serviceResult.getResult());
        jsonResult.setTotal(pager.getRowsCount());

        return jsonResult;
    }
  
    
    /**
     * 跳转分配维修人员页面
     * @param request
     * @param dataMap
     * @param id
     * @param source
     * @return
     */
    @RequestMapping(value = "/delivery", method = { RequestMethod.GET })
    public String delivery(HttpServletRequest request, Map<String, Object> dataMap, Integer id) {
        List<StAppletRepairMember> list = stAppletRepairMemberService.list();
        ServiceResult<StAppletRepair> serviceResult = stAppletRepairService
            .getStAppletRepairById(id);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }
        StAppletRepair stAppletRepair = serviceResult.getResult();
        dataMap.put("repair", stAppletRepair);
        dataMap.put("repairMembers", list);
        return "admin/repair/member/delivery";
    }
    
    /**
     * 设置维修人员
     * @param request
     * @param ccName
     * @param ccId
     * @param repairId
     * @return
     */
    @RequestMapping(value = "/setRepairMember", method = RequestMethod.POST)
    public String doDelivery(HttpServletRequest request, String ccName, String ccId,
                             Integer repairId) {
        SystemAdmin adminUser = WebAdminSession.getAdminUser(request);
        StAppletRepair stAppletRepair = new StAppletRepair();
        stAppletRepair.setId(repairId);
        stAppletRepair.setSts(RepairStatus.STATE_2);
        stAppletRepair.setRepairId(ccId);
        stAppletRepair.setRepairName(ccName);
        
        // 审核人信息
        stAppletRepair.setExamineId(adminUser.getId().toString());
        stAppletRepair.setExamineTime(new Date());
        stAppletRepairService.updateStAppletRepair(stAppletRepair);

        return "redirect:/admin/property/repair";
    }
    /**
     * create by: zl
     * description: 查看物业报修
     * create time:
     *
     * @return
     * @Param: stAppletRepair
     */
    @RequestMapping(value = "/detail", method = { RequestMethod.GET })
    @ResponseBody
    public ResponseUtil get(Integer id) {
        if (id == null) {
            return ResponseUtil.createResp(ResponseStateEnum.PARAM_EMPTY.getCode(),
                ResponseStateEnum.PARAM_EMPTY.getMsg(), true, null);
        }
        ServiceResult<StAppletRepair> result = stAppletRepairService.getStAppletRepairById(id);
        return ResponseUtil.createResp(result.getCode(), result.getMessage(), true,
            result.getResult());
    }

    /**
     * create by: zl
     * description: 查看物业报修列表记录
     * create time:
     *
     * @return
     * @Param: stAppletRepair
     */
    @RequestMapping(value = "/list", method = { RequestMethod.GET })
    @ResponseBody
    public ResponseUtil list(String createUserId, int pageNum, int pageSize) {
        if (StringUtils.isBlank(createUserId)) {
            return ResponseUtil.createResp(ResponseStateEnum.PARAM_EMPTY.getCode(),
                "createUserId is blank", true, null);
        }
        pageNum = pageNum == 0 ? 1 : pageNum;
        pageSize = pageSize == 0 ? 10 : pageSize;
        ServiceResult<List<StAppletRepair>> result = stAppletRepairService
            .getStAppletRepairList(createUserId, pageNum, pageSize);
        return ResponseUtil.createResp(result.getCode(), result.getMessage(), true,
            result.getResult());
    }

    private ResponseUtil checkParam(StAppletRepair stAppletRepair) {
        if (stAppletRepair == null) {
            ResponseUtil.createResp(ResponseStateEnum.PARAM_EMPTY.getCode(),
                ResponseStateEnum.PARAM_EMPTY.getMsg(), true, null);
        }
        if (StringUtils.isBlank(stAppletRepair.getCommunityName())
            || StringUtils.isBlank(stAppletRepair.getHouseName())) {
            ResponseUtil.createResp(ResponseStateEnum.PARAM_EMPTY.getCode(),
                "communityName or houseName is blank", true, null);
        }
        if (StringUtils.isBlank(stAppletRepair.getTelPhone())
            || StringUtils.isBlank(stAppletRepair.getUserName())) {
            ResponseUtil.createResp(ResponseStateEnum.PARAM_EMPTY.getCode(),
                "userName or telPhone is blank", true, null);
        }
        if (StringUtils.isBlank(stAppletRepair.getType())) {
            ResponseUtil.createResp(ResponseStateEnum.PARAM_EMPTY.getCode(), "type is blank", true,
                null);
        }
        if (stAppletRepair.getStartTime() == null || stAppletRepair.getEndTime() == null) {
            ResponseUtil.createResp(ResponseStateEnum.PARAM_EMPTY.getCode(),
                "startTime or  endTime is blank", true, null);
        }
        return null;
    }

    /**
     * 审核不通过
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/nopass", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<Boolean> onpass(HttpServletRequest request,
                                                        HttpServletResponse response, Integer id) {
        StAppletRepair stAppletRepair = new StAppletRepair();
        stAppletRepair.setId(id);
        stAppletRepair.setSts(RepairStatus.STATE_3);
        ServiceResult<Integer> serviceResult = stAppletRepairService.updateStAppletRepair(stAppletRepair);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }

        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
        jsonResult.setData(true);
        return jsonResult;
    }
    
    /**
     * 维修人员操作
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/changeStatus", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<Boolean> changeStatus(HttpServletRequest request,
                                                        HttpServletResponse response, Integer id, Integer sts) {
        StAppletRepair stAppletRepair = new StAppletRepair();
        stAppletRepair.setId(id);
        stAppletRepair.setSts(sts);
        ServiceResult<Integer> serviceResult = stAppletRepairService.updateStAppletRepair(stAppletRepair);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }

        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
        jsonResult.setData(true);
        return jsonResult;
    }
    
}
