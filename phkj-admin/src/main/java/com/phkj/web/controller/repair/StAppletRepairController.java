package com.phkj.web.controller.repair;

import com.phkj.core.ResponseStateEnum;
import com.phkj.core.ServiceResult;
import com.phkj.core.response.ResponseUtil;
import com.phkj.entity.repair.StAppletRepair;
import com.phkj.service.repair.IStAppletRepairService;
import com.phkj.web.controller.BaseController;
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
        boolean flag = checkParam(stAppletRepair);
        if (!flag) {
            return ResponseUtil.createResp(ResponseStateEnum.PARAM_EMPTY.getCode(), ResponseStateEnum.PARAM_EMPTY.getMsg(), true,null);
        }
        ServiceResult<Integer> result = stAppletRepairService.saveStAppletRepair(stAppletRepair);
        return ResponseUtil.createResp(ResponseStateEnum.STATUS_OK.getCode(),ResponseStateEnum.STATUS_OK.getMsg(),true,result.getResult());
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
            return ResponseUtil.createResp(ResponseStateEnum.PARAM_EMPTY.getCode(), ResponseStateEnum.PARAM_EMPTY.getMsg(), true,null);
        }
        ServiceResult<StAppletRepair> result = stAppletRepairService.getStAppletRepairById(1);
        return ResponseUtil.createResp(ResponseStateEnum.STATUS_OK.getCode(),ResponseStateEnum.STATUS_OK.getMsg(),true,result.getResult());
    }

    private boolean checkParam(StAppletRepair stAppletRepair) {

        return false;
    }
}
