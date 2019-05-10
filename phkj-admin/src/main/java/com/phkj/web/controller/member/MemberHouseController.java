package com.phkj.web.controller.member;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.phkj.entity.system.SystemAdmin;
import com.phkj.web.util.WebAdminSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.phkj.core.ConstantsEJS;
import com.phkj.core.HttpJsonResult;
import com.phkj.core.PagerInfo;
import com.phkj.core.ServiceResult;
import com.phkj.core.WebUtil;
import com.phkj.core.exception.BusinessException;
import com.phkj.echarts.component.MemberPropertyStatus;
import com.phkj.entity.member.MemberHouse;
import com.phkj.service.member.IMemberHouseService;
import com.phkj.web.controller.BaseController;

@Controller
@RequestMapping(value = "/admin/member/house")
public class MemberHouseController extends BaseController{
    
    @Resource
    IMemberHouseService memberHouseService;

    
    /**
     * 初始化列表页面
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "", method = { RequestMethod.GET })
    public String getList(Map<String, Object> dataMap) throws Exception {
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return "admin/member/member/memberhouselist";
    }
    
    /**
     * gridDatalist数据
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "/list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<MemberHouse>> list(HttpServletRequest request,
                                                                    ModelMap dataMap) {
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        ServiceResult<List<MemberHouse>> serviceResult = memberHouseService.page(queryMap, pager);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }

        HttpJsonResult<List<MemberHouse>> jsonResult = new HttpJsonResult<List<MemberHouse>>();
        jsonResult.setRows((List<MemberHouse>) serviceResult.getResult());
        jsonResult.setTotal(pager.getRowsCount());

        return jsonResult;
    }
    
    /**
     * 房屋认证-通过
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/passInfo", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<Boolean> pass(HttpServletRequest request,
                                                      HttpServletResponse response, Integer id) {
        SystemAdmin adminUser = WebAdminSession.getAdminUser(request);
        Integer userId = adminUser.getId();
        MemberHouse memberHouse = new MemberHouse();
        memberHouse.setId(id);
        memberHouse.setExamineDate(new Date());
        memberHouse.setExamineUserId(userId);
        memberHouse.setStatus(MemberPropertyStatus.STATE_2);
        ServiceResult<Integer> serviceResult = memberHouseService.updateMemberHouse(memberHouse);
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
     * 房屋认证-不通过
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/noPassInfo", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<Boolean> noPass(HttpServletRequest request,
                                                      HttpServletResponse response, Integer id) {
        SystemAdmin adminUser = WebAdminSession.getAdminUser(request);
        Integer userId = adminUser.getId();
        MemberHouse memberHouse = new MemberHouse();
        memberHouse.setId(id);
        memberHouse.setExamineDate(new Date());
        memberHouse.setExamineUserId(userId);
        memberHouse.setStatus(MemberPropertyStatus.STATE_0);
        ServiceResult<Integer> serviceResult = memberHouseService.updateMemberHouse(memberHouse);
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
