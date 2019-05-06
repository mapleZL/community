package com.ejavashop.web.controller.member;

import com.ejavashop.core.*;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.entity.member.MemberCar;
import com.ejavashop.entity.member.MemberCreditLog;
import com.ejavashop.service.member.IMemberCarService;
import com.ejavashop.service.member.IMemberCreditLogService;
import com.ejavashop.web.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 我的车辆相关action
 *                       
 * @Filename: MemberCreditLogController.java
 * @Version: 1.0
 * @Author: zl
 * @Email:
 *
 */
@Controller
@RequestMapping(value = "/admin/member/car")
public class MemberCarController extends BaseController {
    @Resource
    private IMemberCarService memberCarService;

    /**
     * 默认页面
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "", method = { RequestMethod.GET })
    public String index(HttpServletRequest request, ModelMap dataMap) throws Exception {
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);

        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        dataMap.put("queryMap", queryMap);
        return "/admin/member/car/list";
    }

    /**
     * gridDatalist数据
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<MemberCar>> list(HttpServletRequest request,
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
        jsonResult.setRows((List<MemberCar>) serviceResult.getResult());
        jsonResult.setTotal(pager.getRowsCount());

        return jsonResult;
    }

}
