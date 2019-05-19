package com.phkj.web.controller.member;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.phkj.core.ConstantsEJS;
import com.phkj.core.HttpJsonResult;
import com.phkj.core.PagerInfo;
import com.phkj.core.ServiceResult;
import com.phkj.core.WebUtil;
import com.phkj.core.exception.BusinessException;
import com.phkj.core.response.ResponseUtil;
import com.phkj.echarts.component.MemberPropertyStatus;
import com.phkj.entity.member.MemberCar;
import com.phkj.entity.system.SystemAdmin;
import com.phkj.service.member.IMemberCarService;
import com.phkj.web.controller.BaseController;
import com.phkj.web.util.WebAdminSession;

/**
 * 我的车辆相关action
 *
 * @Filename: MemberCreditLogController.java
 * @Version: 1.0
 * @Author: zl
 * @Email:
 */
@Controller
@RequestMapping(value = "/admin/member/car")
public class MemberCarController extends BaseController {
    @Resource
    private IMemberCarService memberCarService;

    /**
     * 默认页面
     *
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "", method = {RequestMethod.GET})
    public String index(HttpServletRequest request, ModelMap dataMap) throws Exception {
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);

        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        dataMap.put("queryMap", queryMap);
        return "admin/member/member/membercarlist";
    }

    /**
     * 详情
     *
     * @param memberCarId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/detail", method = {RequestMethod.GET})
    @ResponseBody
    public ResponseUtil detail(Integer memberCarId) {
        ServiceResult<MemberCar> result = memberCarService.getMemberCarById(memberCarId);
        return ResponseUtil.createResp(result.getCode(), result.getMessage(), true, result.getResult());
    }

    /**
     * 新增车辆
     *
     * @param memberCar
     */
    @RequestMapping(value = "/save", method = {RequestMethod.POST})
    @ResponseBody
    public ResponseUtil save(@RequestBody MemberCar memberCar) {
        ServiceResult<Integer> serviceResult;
        if (memberCar.getId() != null && memberCar.getId() != 0) {
            //编辑
            serviceResult = memberCarService.updateMemberCar(memberCar);
        } else {
            //新增
            serviceResult = memberCarService.saveMemberCar(memberCar);
        }
        return ResponseUtil.createResp(serviceResult.getCode(), serviceResult.getMessage(), true, serviceResult.getResult());
    }

    /**
     * gridDatalist数据
     *
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "list", method = {RequestMethod.GET})
    public @ResponseBody
    HttpJsonResult<List<MemberCar>> list(HttpServletRequest request,
                                         ModelMap dataMap) {
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        ServiceResult<List<MemberCar>> serviceResult = memberCarService.page(queryMap,
                pager);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }

        HttpJsonResult<List<MemberCar>> jsonResult = new HttpJsonResult<List<MemberCar>>();
        jsonResult.setRows(serviceResult.getResult());
        jsonResult.setTotal(pager.getRowsCount());

        return jsonResult;
    }

    /**
     * 车辆认证-通过
     *
     * @return
     */
    @RequestMapping(value = "/passInfo", method = {RequestMethod.GET})
    public @ResponseBody
    HttpJsonResult<Boolean> pass(HttpServletRequest request,
                                 HttpServletResponse response, Integer id) {
        SystemAdmin adminUser = WebAdminSession.getAdminUser(request);
        Integer userId = adminUser.getId();
        MemberCar memberCar = new MemberCar();
        memberCar.setId(id);
        memberCar.setStatus(MemberPropertyStatus.STATE_2);
        memberCar.setExamineUserId(userId);
        memberCar.setExamineDate(new Date());
        ServiceResult<Integer> serviceResult = memberCarService.updateMemberCar(memberCar);
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
     * 车辆认证-不通过
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/noPassInfo", method = {RequestMethod.GET})
    public @ResponseBody
    HttpJsonResult<Boolean> noPass(HttpServletRequest request,
                                   HttpServletResponse response, Integer id) {
        SystemAdmin adminUser = WebAdminSession.getAdminUser(request);
        Integer userId = adminUser.getId();
        MemberCar memberCar = new MemberCar();
        memberCar.setId(id);
        memberCar.setStatus(MemberPropertyStatus.STATE_0);
        memberCar.setExamineUserId(userId);
        memberCar.setExamineDate(new Date());
        ServiceResult<Integer> serviceResult = memberCarService.updateMemberCar(memberCar);
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
