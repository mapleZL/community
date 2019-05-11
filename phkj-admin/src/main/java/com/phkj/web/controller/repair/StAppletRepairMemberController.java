package com.phkj.web.controller.repair;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
import com.phkj.entity.repair.StAppletRepairMember;
import com.phkj.service.repair.IStAppletRepairMemberService;
import com.phkj.web.controller.BaseController;

/**
 * 
 *                       
 * @Filename: StAppletRepairMemberController.java
 * @Version: 1.0
 * @Author: 陆帅 * @Email: lu1632278229@sina.cn
 *
 */
@Controller
@RequestMapping(value = "admin/repair/member/")
public class StAppletRepairMemberController extends BaseController {

    @Autowired
    IStAppletRepairMemberService iStAppletRepairMemberService;
    
    /**
     * 初始化列表页面
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "", method = { RequestMethod.GET })
    public String getList(Map<String, Object> dataMap) throws Exception {
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return "admin/repair/member/repairmemberlist";
    }    
    
    /**
     * 新增物业报修人员
     * @param stAppletRepair
     * @return
     */
    @RequestMapping(value = "/add", method = {RequestMethod.POST})
    @ResponseBody
    public ResponseUtil save(StAppletRepairMember stAppletRepairMember) {
        ServiceResult<Integer> result = iStAppletRepairMemberService.saveStAppletRepairMember(stAppletRepairMember);
        return ResponseUtil.createResp(result.getCode(), result.getMessage(), true, result.getResult());
    }

    @RequestMapping(value = "/get", method = {RequestMethod.GET})
    @ResponseBody
    public ResponseUtil get(Integer id) {
        if (id == null) {
            return ResponseUtil.createResp(ResponseStateEnum.PARAM_EMPTY.getCode(), ResponseStateEnum.PARAM_EMPTY.getMsg(), true, null);
        }
        ServiceResult<StAppletRepairMember> result = iStAppletRepairMemberService.getStAppletRepairMemberById(id);
        return ResponseUtil.createResp(result.getCode(), result.getMessage(), true, result.getResult());
    }

    /**
     * 物业维修人员列表查询
     * @param createUserId
     * @param pageNum
     * @param pageSize
     * @return
     */
    /**
     * gridDatalist数据
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "/list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<StAppletRepairMember>> list(HttpServletRequest request,
                                                                    ModelMap dataMap) {
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        ServiceResult<List<StAppletRepairMember>> serviceResult = iStAppletRepairMemberService.page(queryMap, pager);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }

        HttpJsonResult<List<StAppletRepairMember>> jsonResult = new HttpJsonResult<List<StAppletRepairMember>>();
        jsonResult.setRows((List<StAppletRepairMember>) serviceResult.getResult());
        jsonResult.setTotal(pager.getRowsCount());

        return jsonResult;
    }

}
