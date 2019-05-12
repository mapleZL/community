package com.phkj.web.controller.repair;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
import com.phkj.echarts.component.MemberPropertyStatus;
import com.phkj.entity.repair.StAppletRepairMember;
import com.phkj.entity.system.SystemAdmin;
import com.phkj.service.repair.IStAppletRepairMemberService;
import com.phkj.service.system.ISystemAdminService;
import com.phkj.web.controller.BaseController;
import com.phkj.web.util.WebAdminSession;

/**
 * 
 *                       
 * @Filename: StAppletRepairMemberController.java
 * @Version: 1.0
 * @Author: 陆帅 * @Email: lu1632278229@sina.cn
 *
 */
@Controller
@RequestMapping(value = "/admin/repair/member")
public class StAppletRepairMemberController extends BaseController {

    @Autowired
    IStAppletRepairMemberService stAppletRepairMemberService;
    @Autowired
    ISystemAdminService systemAdminService;
    
    /**
     * 初始化列表页面
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "", method = { RequestMethod.GET })
    public String getList(Map<String, Object> dataMap) throws Exception {
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return "admin/repair/member/repairmemberlist";
    }    
    
    /**
     * 新增物业报修人员跳转
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "/add", method = {RequestMethod.GET})
    public String add(Map<String, Object> dataMap) {
        // 查询系统维修人员
        ServiceResult<List<SystemAdmin>> result = systemAdminService.getSystemAdminByRoleId("5");
        dataMap.put("repairs", result.getResult());
        return "/admin/repair/member/repairmemberadd";
    }
    
    /**
     * 新增物业报修人员
     * @param stAppletRepair
     * @return
     */
    @RequestMapping(value = "/create", method = {RequestMethod.POST})
    public String create(Map<String, Object> dataMap, HttpServletRequest request) {
        SystemAdmin adminUser = WebAdminSession.getAdminUser(request);
        StAppletRepairMember stAppletRepairMember = new StAppletRepairMember();
        stAppletRepairMember.setUserName(request.getParameter("userName"));
        stAppletRepairMember.setSchedulingDay(Integer.valueOf(request.getParameter("schedulingDay")));
        stAppletRepairMember.setCreateUserId(adminUser.getId());
        stAppletRepairMember.setCreateTime(new Date());
        stAppletRepairMember.setSts(1);
        stAppletRepairMemberService.saveStAppletRepairMember(stAppletRepairMember);
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return "admin/repair/member/repairmemberlist";
    }
    

    @RequestMapping(value = "/get", method = {RequestMethod.GET})
    @ResponseBody
    public ResponseUtil get(Integer id) {
        if (id == null) {
            return ResponseUtil.createResp(ResponseStateEnum.PARAM_EMPTY.getCode(), ResponseStateEnum.PARAM_EMPTY.getMsg(), true, null);
        }
        ServiceResult<StAppletRepairMember> result = stAppletRepairMemberService.getStAppletRepairMemberById(id);
        return ResponseUtil.createResp(result.getCode(), result.getMessage(), true, result.getResult());
    }

    /**
     * 物业维修人员列表查询
     * @param createUserId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<StAppletRepairMember>> list(HttpServletRequest request,
                                                                    ModelMap dataMap) {
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        ServiceResult<List<StAppletRepairMember>> serviceResult = stAppletRepairMemberService.page(queryMap, pager);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }

        HttpJsonResult<List<StAppletRepairMember>> jsonResult = new HttpJsonResult<List<StAppletRepairMember>>();
        jsonResult.setRows((List<StAppletRepairMember>) serviceResult.getResult());
        jsonResult.setTotal(pager.getRowsCount());

        return jsonResult;
    }
    
    /**
     * 启用维修人员
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/enable", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<Boolean> enable(HttpServletRequest request,
                                                      HttpServletResponse response, Integer id) {
        StAppletRepairMember stAppletRepairMember = new StAppletRepairMember();
        stAppletRepairMember.setId(id);
        stAppletRepairMember.setSts(MemberPropertyStatus.STATE_2);
        ServiceResult<Integer> serviceResult = stAppletRepairMemberService.updateRepairMember(stAppletRepairMember);
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
     * 禁用账号
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/forbidden", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<Boolean> forbidden(HttpServletRequest request,
                                                      HttpServletResponse response, Integer id) {
        StAppletRepairMember stAppletRepairMember = new StAppletRepairMember();
        stAppletRepairMember.setId(id);
        stAppletRepairMember.setSts(MemberPropertyStatus.STATE_3);
        ServiceResult<Integer> serviceResult = stAppletRepairMemberService.updateRepairMember(stAppletRepairMember);
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
