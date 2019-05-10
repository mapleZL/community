package com.phkj.web.controller.repair;

import com.phkj.core.ResponseStateEnum;
import com.phkj.core.ServiceResult;
import com.phkj.core.response.ResponseUtil;
import com.phkj.entity.repair.StAppletRepair;
import com.phkj.service.repair.IStAppletRepairService;
import com.phkj.web.controller.BaseController;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author ：zl
 * @date ：Created in 2019/5/9 19:50
 * @description：业务报修controller层
 * @modified By：
 * @version: 0.0.1$
 */
@Controller
@RequestMapping(value = "admin/property/repair")
public class StAppletRepairController extends BaseController {

    @Autowired
    IStAppletRepairService stAppletRepairService;

    /**
     * create by: zl
     * description: 添加物业报修
     * create time:
     *
     * @return
     * @Param: stAppletRepair
     */
    @RequestMapping(value = "/add", method = {RequestMethod.POST})
    @ResponseBody
    public ResponseUtil save(@RequestBody StAppletRepair stAppletRepair) {
        ResponseUtil flag = checkParam(stAppletRepair);
        if (flag != null) {
            return flag;
        }
        ServiceResult<Integer> result = stAppletRepairService.saveStAppletRepair(stAppletRepair);
        return ResponseUtil.createResp(result.getCode(), result.getMessage(), true, result.getResult());
    }

    /**
     * create by: zl
     * description: 添加物业报修
     * create time:
     *
     * @return
     * @Param: stAppletRepair
     */
    @RequestMapping(value = "/get", method = {RequestMethod.GET})
    @ResponseBody
    public ResponseUtil get(Integer id) {
        if (id == null) {
            return ResponseUtil.createResp(ResponseStateEnum.PARAM_EMPTY.getCode(), ResponseStateEnum.PARAM_EMPTY.getMsg(), true, null);
        }
        ServiceResult<StAppletRepair> result = stAppletRepairService.getStAppletRepairById(id);
        return ResponseUtil.createResp(result.getCode(), result.getMessage(), true, result.getResult());
    }

    private ResponseUtil checkParam(StAppletRepair stAppletRepair) {
        if (stAppletRepair == null) {
            ResponseUtil.createResp(ResponseStateEnum.PARAM_EMPTY.getCode(), ResponseStateEnum.PARAM_EMPTY.getMsg(), true, null);
        }
        if (StringUtils.isBlank(stAppletRepair.getCommunityName()) || StringUtils.isBlank(stAppletRepair.getHouseName())) {
            ResponseUtil.createResp(ResponseStateEnum.PARAM_EMPTY.getCode(), "communityName or houseName is blank", true, null);
        }
        if (StringUtils.isBlank(stAppletRepair.getTelPhone()) || StringUtils.isBlank(stAppletRepair.getUserName())) {
            ResponseUtil.createResp(ResponseStateEnum.PARAM_EMPTY.getCode(), "userName or telPhone is blank", true, null);
        }
        if (StringUtils.isBlank(stAppletRepair.getType())) {
            ResponseUtil.createResp(ResponseStateEnum.PARAM_EMPTY.getCode(), "type is blank", true, null);
        }
        if (stAppletRepair.getStartTime() == null || stAppletRepair.getEndTime() == null) {
            ResponseUtil.createResp(ResponseStateEnum.PARAM_EMPTY.getCode(), "startTime or  endTime is blank", true, null);
        }
        return null;
    }
}
