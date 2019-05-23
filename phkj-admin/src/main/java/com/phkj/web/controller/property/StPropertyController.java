package com.phkj.web.controller.property;

import com.phkj.core.ServiceResult;
import com.phkj.core.response.ResponseUtil;
import com.phkj.entity.member.MemberCar;
import com.phkj.entity.property.PropertiItem;
import com.phkj.service.property.IStBaseinfoStaffsService;
import com.phkj.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author ：zl
 * @date ：Created in 2019/5/22 23:55
 * @description：物业
 * @modified By：
 * @version: 0.0.1$
 */
@Controller
@RequestMapping(value = "admin/property")
public class StPropertyController extends BaseController {

    @Autowired
    IStBaseinfoStaffsService iStBaseinfoStaffsService;

    /**
     * 获取物业值班人员信息
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/members", method = {RequestMethod.GET})
    @ResponseBody
    public ResponseUtil getStaffsOnDutyList() {
        ServiceResult<List<PropertiItem>> result = iStBaseinfoStaffsService.getStaffsOnDutyList();
        return ResponseUtil.createResp(result.getCode(), result.getMessage(), true, result.getResult());
    }
}
