package com.phkj.web.controller.member;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
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
import com.phkj.echarts.component.MemberPropertyStatus;
import com.phkj.entity.member.MemberHouse;
import com.phkj.entity.system.SystemAdmin;
import com.phkj.service.member.IMemberHouseService;
import com.phkj.web.controller.BaseController;
import com.phkj.web.util.WebAdminSession;

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
    
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseUtil add(@RequestBody MemberHouse memberHouse, HttpServletRequest request){
        ResponseUtil result = checkParam(memberHouse);
        if (result != null) {
            return result;
        }
        ServiceResult<Integer> serviceResult = null;
        if (memberHouse.getId() != null) {
            serviceResult = memberHouseService.updateMemberHouse(memberHouse);
        } else {
            serviceResult = memberHouseService.saveMemberHouse(memberHouse);
        }
        return ResponseUtil.createResp(serviceResult.getCode(), serviceResult.getMessage(), true,
            serviceResult.getResult());
    }
    
    /**
     * 校验必填参数
     * @param memberHouse
     * @return
     */
    private ResponseUtil checkParam(MemberHouse memberHouse) {
        if (memberHouse == null) {
            ResponseUtil.createResp(ResponseStateEnum.PARAM_EMPTY.getCode(),
                ResponseStateEnum.PARAM_EMPTY.getMsg(), true, null);
        }
        if (StringUtils.isEmpty(memberHouse.getRegion())) {
            ResponseUtil.createResp(ResponseStateEnum.PARAM_EMPTY.getCode(),
                "region is empity", true, null);
        }
        if (StringUtils.isEmpty(memberHouse.getCommunity())) {
            ResponseUtil.createResp(ResponseStateEnum.PARAM_EMPTY.getCode(),
                "community is empity", true, null);
        }
        if (StringUtils.isEmpty(memberHouse.getBuilding())) {
            ResponseUtil.createResp(ResponseStateEnum.PARAM_EMPTY.getCode(),
                "building is empity", true, null);
        }
        if (StringUtils.isEmpty(memberHouse.getRoom())) {
            ResponseUtil.createResp(ResponseStateEnum.PARAM_EMPTY.getCode(),
                "room is empity", true, null);     
        }
        if (StringUtils.isEmpty(memberHouse.getIdentityInformation())) {
            ResponseUtil.createResp(ResponseStateEnum.PARAM_EMPTY.getCode(),
                "identityInformation is empity", true, null);
        }
        if (StringUtils.isEmpty(memberHouse.getIdNumber())) {
            ResponseUtil.createResp(ResponseStateEnum.PARAM_EMPTY.getCode(),
                "idNumber is empity", true, null);
        }
        if (StringUtils.isEmpty(memberHouse.getStreet())) {
            ResponseUtil.createResp(ResponseStateEnum.PARAM_EMPTY.getCode(),
                "street is empity", true, null);
        }
        if (StringUtils.isEmpty(memberHouse.getVillage())) {
            ResponseUtil.createResp(ResponseStateEnum.PARAM_EMPTY.getCode(),
                "village is empity", true, null);
        }
        if (StringUtils.isEmpty(memberHouse.getUnit())) {
            ResponseUtil.createResp(ResponseStateEnum.PARAM_EMPTY.getCode(),
                "unit is empity", true, null);
        }
        if (StringUtils.isEmpty(memberHouse.getPhone())) {
            ResponseUtil.createResp(ResponseStateEnum.PARAM_EMPTY.getCode(),
                "phone is empity", true, null);
        }
        if (StringUtils.isEmpty(memberHouse.getName())) {
            ResponseUtil.createResp(ResponseStateEnum.PARAM_EMPTY.getCode(),
                "name is empity", true, null);
        }
        if (StringUtils.isEmpty(memberHouse.getImg())) {
            ResponseUtil.createResp(ResponseStateEnum.PARAM_EMPTY.getCode(),
                "img is empity", true, null);
        }
        return null;
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
