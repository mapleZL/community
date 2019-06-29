package com.phkj.web.controller.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.pagehelper.PageInfo;
import com.phkj.entity.relate.StBaseinfoParkingLotOrder;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
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
import com.phkj.entity.member.MemberParkingLot;
import com.phkj.entity.relate.StBaseinfoParkingLot;
import com.phkj.service.member.IMemberParkingLotService;
import com.phkj.service.relate.IStBaseinfoParkingLotService;
import com.phkj.web.controller.BaseController;

/**
 * @Filename: MemberParkingLotController.java
 * @Version: 1.0
 * @date: 2019年5月13日
 * @Author: 陆帅 * @Email: lu1632278229@sina.cn
 */
@Controller
@RequestMapping(value = "/admin/member/parking/lot")
public class MemberParkingLotController extends BaseController {

    private static Logger log = LogManager.getLogger(MemberParkingLotController.class);

    @Resource
    IMemberParkingLotService memberParkingLotService;

    @Resource
    IStBaseinfoParkingLotService stBaseinfoParkingLotService;


    /**
     * 初始化列表页面
     *
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "", method = {RequestMethod.GET})
    public String getList(Map<String, Object> dataMap) throws Exception {
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return "admin/member/member/memberparkinglot";
    }


    /**
     * 新增房屋
     *
     * @param memberParkingLot
     */
    @RequestMapping(value = "/save", method = {RequestMethod.POST})
    @ResponseBody
    public ResponseUtil save(@RequestBody MemberParkingLot memberParkingLot) {
        ResponseUtil responseUtil = checkParam(memberParkingLot);
        if (null != responseUtil) {
            return responseUtil;
        }
        ServiceResult<Integer> serviceResult;
        if (memberParkingLot.getId() != null && memberParkingLot.getId() != 0) {
            //编辑
            serviceResult = memberParkingLotService.updateMemberParkingLot(memberParkingLot);
        } else {
            //新增
            ServiceResult<List<MemberParkingLot>> result = memberParkingLotService.getMyMemberLotList(memberParkingLot.getMemberId(), memberParkingLot.getVillageCode(), 1, 10000);
            List<MemberParkingLot> lots = result.getResult();
            if (lots != null && !lots.isEmpty()) {
                for (MemberParkingLot lot : lots) {
                    if (lot.getPosition().equals(memberParkingLot.getPosition())) {
                        return ResponseUtil.createResp(ResponseStateEnum.PARAM_EMPTY.getCode(), "请勿重复认证", false, null);
                    }
                }
            }
            serviceResult = memberParkingLotService.saveMemberParkingLot(memberParkingLot);
        }
        return ResponseUtil.createResp(serviceResult.getCode(), serviceResult.getMessage(), true, serviceResult.getResult());
    }

    /**
     * create by: zl
     * description: 参数校验
     * create time:
     *
     * @return
     * @Param: memberParkingLot
     */
    private ResponseUtil checkParam(MemberParkingLot memberParkingLot) {
        if (StringUtils.isBlank(memberParkingLot.getVehicleNumber()) || memberParkingLot.getMemberId() == null || memberParkingLot.getMemberId() == 0) {
            return ResponseUtil.createResp(ResponseStateEnum.PARAM_EMPTY.getCode(), "memberId or vehicleNumber is blank", false, null);
        }
        if (StringUtils.isBlank(memberParkingLot.getPhoneNum()) || StringUtils.isBlank(memberParkingLot.getPosition())) {
            return ResponseUtil.createResp(ResponseStateEnum.PARAM_EMPTY.getCode(), "phoneNum or position is blank", false, null);
        }
        // villageCode必填
        if (StringUtils.isBlank(memberParkingLot.getVillageCode())) {
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
    @RequestMapping(value = "/list", method = {RequestMethod.GET})
    public @ResponseBody
    HttpJsonResult<List<MemberParkingLot>> list(HttpServletRequest request,
                                                ModelMap dataMap) {
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        ServiceResult<List<MemberParkingLot>> serviceResult = memberParkingLotService.page(queryMap, pager);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }

        HttpJsonResult<List<MemberParkingLot>> jsonResult = new HttpJsonResult<List<MemberParkingLot>>();
        jsonResult.setRows((List<MemberParkingLot>) serviceResult.getResult());
        jsonResult.setTotal(pager.getRowsCount());

        return jsonResult;
    }


    /**
     * 我的车位 -- 微信
     *
     * @param memberId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/my/lots", method = {RequestMethod.GET})
    @ResponseBody
    public ResponseUtil myCars(Integer memberId, String villageCode, int pageNum, int pageSize) {
        if (memberId == null || memberId == 0) {
            return ResponseUtil.createResp(ResponseStateEnum.PARAM_EMPTY.getCode(), "memberId is blank", true, null);
        }
        pageNum = pageNum == 0 ? 1 : pageNum;
        pageSize = pageSize == 0 ? 10 : pageSize;
        ServiceResult<List<MemberParkingLot>> result = memberParkingLotService.getMyMemberLotList(memberId, villageCode, pageNum, pageSize);
        return ResponseUtil.createResp(result.getCode(), result.getMessage(), true, result.getResult());
    }

    /**
     * 车位认证-通过
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/passInfo", method = {RequestMethod.GET})
    public @ResponseBody
    HttpJsonResult<Boolean> pass(HttpServletRequest request,
                                 HttpServletResponse response, Integer id) {

        ServiceResult<Boolean> serviceResult = memberParkingLotService.changeStatus(id, MemberPropertyStatus.STATE_2);
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
     * 车位认证-不通过
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/noPassInfo", method = {RequestMethod.GET})
    public @ResponseBody
    HttpJsonResult<Boolean> noPass(HttpServletRequest request,
                                   HttpServletResponse response, Integer id) {

        ServiceResult<Boolean> serviceResult = memberParkingLotService.changeStatus(id, MemberPropertyStatus.STATE_0);
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
     * @param villageCode
     * @return
     */
    @ResponseBody
    @RequestMapping("/getAllNearbyParkingLot")
    public ResponseUtil getAllNearbyParkingLot(String villageCode) {
        ResponseUtil responseUtil = new ResponseUtil();
        try {
            if (StringUtils.isBlank(villageCode)) {
                return ResponseUtil.createResp(ResponseStateEnum.PARAM_EMPTY.getCode(), "orgCode is blank", false, null);
            }
            ServiceResult<List<Map<String, Object>>> result = stBaseinfoParkingLotService.getNearbyParkingLot(villageCode);
            return ResponseUtil.createResp(result.getCode(), result.getMessage(), true, result.getResult());
        } catch (Exception e) {
            log.error("获取剩余附近车位异常, exception:{}", e);
            return ResponseUtil.createResp(ResponseStateEnum.STATUS_SERVER_ERROR.getCode(), ResponseStateEnum.STATUS_SERVER_ERROR.getMsg(), false, null);
        }
    }


    /**
     * 获取当前小区剩余车位
     *
     * @param villageCode
     * @return
     */
    @RequestMapping(value = "/surplus/getSurplusParkingLotNum", method = {RequestMethod.GET})
    public @ResponseBody
    ResponseUtil getSurplusParkingLotNum(String villageCode) {
        try {
            if (StringUtils.isBlank(villageCode)) {
                return ResponseUtil.createResp(ResponseStateEnum.PARAM_EMPTY.getCode(), "orgCode is blank", false, null);
            }
            ServiceResult<Integer> result = stBaseinfoParkingLotService.getSurplusParkingLotNum(villageCode);
            return ResponseUtil.createResp(result.getCode(), result.getMessage(), true, result.getResult());
        } catch (Exception e) {
            log.error("获取剩余车位异常, exception:{}", e);
            return ResponseUtil.createResp(ResponseStateEnum.STATUS_SERVER_ERROR.getCode(), ResponseStateEnum.STATUS_SERVER_ERROR.getMsg(), false, null);
        }
    }

    /**
     * 获取当前小区剩余车位
     *
     * @param villageCode
     * @return
     */
    @RequestMapping(value = "/surplus/lots", method = {RequestMethod.GET})
    public @ResponseBody
    ResponseUtil getSurplusParkingLot(String villageCode, String userId) {
        try {
            if (StringUtils.isBlank(villageCode)) {
                return ResponseUtil.createResp(ResponseStateEnum.PARAM_EMPTY.getCode(), "orgCode is blank", false, null);
            }
            ServiceResult<List<StBaseinfoParkingLot>> result = stBaseinfoParkingLotService.getSurplusParkingLot(villageCode, userId);
            return ResponseUtil.createResp(result.getCode(), result.getMessage(), true, result.getResult());
        } catch (Exception e) {
            log.error("获取剩余车位异常, exception:{}", e);
            return ResponseUtil.createResp(ResponseStateEnum.STATUS_SERVER_ERROR.getCode(), ResponseStateEnum.STATUS_SERVER_ERROR.getMsg(), false, null);
        }
    }


    /**
     * 新版立即预约车位
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/applyParkingLot")
    public ResponseUtil applyParking(@RequestBody StBaseinfoParkingLotOrder parkingLot) {
        ResponseUtil responseUtil = new ResponseUtil();
        try {
            String str = stBaseinfoParkingLotService.applyParking(parkingLot);
            responseUtil.setSuccess(true);
            if (StringUtils.isNotBlank(str)) {
                responseUtil.setSuccess(false);
                responseUtil.setMsg(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("预约车位失败!, exception:{}", e);
        }
        return responseUtil;
    }


    /**
     *
     */
    @ResponseBody
    @RequestMapping("/systemParkingLot")
    public ResponseUtil systemParkingLot(HttpServletRequest request) {
        ResponseUtil responseUtil = new ResponseUtil();
        try {
            String villageCode = request.getParameter("villageCode");
            String userId = request.getParameter("userId");
            String page = request.getParameter("page");
            String rows = request.getParameter("rows");
            PageInfo<Map<String, Object>> pageInfo = stBaseinfoParkingLotService.systemParkingLot(villageCode, userId, page, rows);
            // 处理数据
            Map<String, Object> returnMap = new HashMap<>();
            returnMap.put("total", pageInfo.getTotal());
            returnMap.put("list", pageInfo.getList());
            responseUtil.setSuccess(true);
            responseUtil.setData(returnMap);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("查询车位失败!, exception:{}", e);
        }
        return responseUtil;
    }

    /**
     *
     */
    @ResponseBody
    @RequestMapping("/systemApply")
    public ResponseUtil systemApply(@RequestBody StBaseinfoParkingLotOrder parkingLotOrder) {
        ResponseUtil responseUtil = new ResponseUtil();
        if (stBaseinfoParkingLotService.systemApply(parkingLotOrder)) {
            responseUtil.setSuccess(true);
        }
        return responseUtil;
    }

}
