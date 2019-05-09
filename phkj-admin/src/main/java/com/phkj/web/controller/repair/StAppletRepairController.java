package com.phkj.web.controller.repair;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.phkj.core.ConstantsEJS;
import com.phkj.core.ResponseStateEnum;
import com.phkj.core.ServiceResult;
import com.phkj.core.response.ResponseUtil;
import com.phkj.entity.repair.StAppletRepair;
import com.phkj.service.repair.IStAppletRepairService;
import com.phkj.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
    public String index(@RequestBody StAppletRepair stAppletRepair) {
        boolean flag = checkParam(stAppletRepair);
        if (!flag) {
            return ResponseUtil.responseJSONString(ResponseStateEnum.PARAM_EMPTY.getCode(), ResponseStateEnum.PARAM_EMPTY.getMsg(), null);
        }
        ServiceResult<Integer> result = stAppletRepairService.saveStAppletRepair(stAppletRepair);
        return ResponseUtil.responseJSONString(true);
    }

    private boolean checkParam(StAppletRepair stAppletRepair) {

        return false;
    }
}
