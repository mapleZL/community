package com.phkj.web.controller.member;

import com.phkj.core.*;
import com.phkj.core.exception.BusinessException;
import com.phkj.core.response.ResponseUtil;
import com.phkj.echarts.component.MemberPropertyStatus;
import com.phkj.entity.member.MemberParkingLot;
import com.phkj.service.member.IMemberParkingLotService;
import com.phkj.web.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @Filename: MemberParkingLotController.java
 * @Version: 1.0
 * @date: 2019年5月13日
 * @Author: 陆帅 * @Email: lu1632278229@sina.cn
 */
@Controller
@RequestMapping(value = "/admin/member/parking/lot")
public class MemberParkingLotController extends BaseController {

    @Resource
    IMemberParkingLotService memberParkingLotService;


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
        ServiceResult<Integer> serviceResult;
        if (memberParkingLot.getId() != null && memberParkingLot.getId() != 0) {
            //编辑
            serviceResult = memberParkingLotService.updateMemberParkingLot(memberParkingLot);
        } else {
            //新增
            ServiceResult<List<MemberParkingLot>> result = memberParkingLotService.getMyMemberLotList(memberParkingLot.getMemberId(), 1, 10000);
            List<MemberParkingLot> lots = result.getResult();
            if (lots != null && !lots.isEmpty()) {
                for (MemberParkingLot lot : lots) {
                    if (lot.getPosition().equals(memberParkingLot.getPosition())) {
                        return ResponseUtil.createResp(ResponseStateEnum.PARAM_EMPTY.getCode(), "请勿重复认证", true, null);
                    }
                }
            }
            serviceResult = memberParkingLotService.saveMemberParkingLot(memberParkingLot);
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
    public ResponseUtil myCars(Integer memberId, int pageNum, int pageSize) {
        if (memberId == null || memberId == 0) {
            return ResponseUtil.createResp(ResponseStateEnum.PARAM_EMPTY.getCode(), "memberId is blank", true, null);
        }
        pageNum = pageNum == 0 ? 1 : pageNum;
        pageSize = pageSize == 0 ? 10 : pageSize;
        ServiceResult<List<MemberParkingLot>> result = memberParkingLotService.getMyMemberLotList(memberId, pageNum, pageSize);
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
}
