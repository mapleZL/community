package com.phkj.web.controller.member;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
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
        ResponseUtil responseUtil = checkParam(memberCar);
        if (null != responseUtil) {
            return responseUtil;
        }
        ServiceResult<Integer> serviceResult;
        if (memberCar.getId() != null && memberCar.getId() != 0) {
            //编辑
            serviceResult = memberCarService.updateMemberCar(memberCar);
        } else {
            //新增
            ServiceResult<List<MemberCar>> result = memberCarService.getMyMemberCarList(memberCar.getMemberId(), memberCar.getVillageCode(), 1, 10000);
            if (result.getResult() != null && !result.getResult().isEmpty()) {
                List<MemberCar> cars = result.getResult();
                for (MemberCar car : cars) {
                    if (car.getVehicleNumber().equals(memberCar.getVehicleNumber())) {
                        return ResponseUtil.createResp(ResponseStateEnum.PARAM_EMPTY.getCode(), "请勿重复认证", false, null);
                    }
                }
            }
            serviceResult = memberCarService.saveMemberCar(memberCar);
        }
        return ResponseUtil.createResp(serviceResult.getCode(), serviceResult.getMessage(), true, serviceResult.getResult());
    }

    /**
     * create by: zl
     * description: 参数校验
     * create time:
     *
     * @return
     * @Param: memberCar
     */
    private ResponseUtil checkParam(MemberCar memberCar) {
        if (StringUtils.isBlank(memberCar.getVehicleNumber()) || memberCar.getMemberId() == null || memberCar.getMemberId() == 0) {
            return ResponseUtil.createResp(ResponseStateEnum.PARAM_EMPTY.getCode(), "memberId or vehicleNumber is blank", false, null);
        }
        if (StringUtils.isBlank(memberCar.getVehicleType()) || StringUtils.isBlank(memberCar.getVehicleStructure())) {
            return ResponseUtil.createResp(ResponseStateEnum.PARAM_EMPTY.getCode(), "vehicleType or vehicleStructure is blank", false, null);
        }
        if (memberCar.getVillageCode() == null || memberCar.getVillageCode() == 0) {
            return ResponseUtil.createResp(ResponseStateEnum.PARAM_EMPTY.getCode(), "villageCode is blank", false, null);
        }
        return null;
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
     * 我的车辆 -- 微信
     *
     * @param memberId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/my/cars", method = {RequestMethod.GET})
    @ResponseBody
    public ResponseUtil myCars(Integer memberId, Integer villageCode, int pageNum, int pageSize) {
        if (memberId == null || memberId == 0) {
            return ResponseUtil.createResp(ResponseStateEnum.PARAM_EMPTY.getCode(), "memberId is blank", true, null);
        }
        // villageCode必填
        if (villageCode == null || villageCode == 0) {
            return ResponseUtil.createResp(ResponseStateEnum.PARAM_EMPTY.getCode(), "villageId is blank", true, null);
        }
        pageNum = pageNum == 0 ? 1 : pageNum;
        pageSize = pageSize == 0 ? 10 : pageSize;
        ServiceResult<List<MemberCar>> result = memberCarService.getMyMemberCarList(memberId, villageCode, pageNum, pageSize);
        return ResponseUtil.createResp(result.getCode(), result.getMessage(), true, result.getResult());
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
