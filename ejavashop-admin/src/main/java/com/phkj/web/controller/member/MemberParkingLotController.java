package com.phkj.web.controller.member;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.phkj.entity.member.MemberParkingLot;
import com.phkj.service.member.IMemberParkingLotService;
import com.phkj.web.controller.BaseController;

/**
 *                       
 * @Filename: MemberParkingLotController.java
 * @Version: 1.0
 * @Author: 陆帅 * @Email: lu1632278229@sina.cn
 *
 */
@Controller
@RequestMapping(value = "/admin/member/parking/lot")
public class MemberParkingLotController extends BaseController{
    
    @Resource
    IMemberParkingLotService memberParkingLotService;

    
    /**
     * 初始化列表页面
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "", method = { RequestMethod.GET })
    public String getList(Map<String, Object> dataMap) throws Exception {
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return "admin/member/member/memberparkinglot";
    }
    
    /**
     * gridDatalist数据
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "/list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<MemberParkingLot>> list(HttpServletRequest request,
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
     * 车位认证-通过
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/passInfo", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<Boolean> pass(HttpServletRequest request,
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
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/noPassInfo", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<Boolean> noPass(HttpServletRequest request,
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
