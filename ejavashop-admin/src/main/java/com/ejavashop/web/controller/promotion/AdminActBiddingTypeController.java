package com.ejavashop.web.controller.promotion;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.HttpJsonResult;
import com.ejavashop.core.HttpJsonResultForAjax;
import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.WebUtil;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.entity.promotion.bidding.ActBiddingType;
import com.ejavashop.entity.system.SystemAdmin;
import com.ejavashop.service.promotion.IActBiddingService;
import com.ejavashop.web.controller.BaseController;
import com.ejavashop.web.util.WebAdminSession;

/**
 * 集合竞价类型相关处理
 *                       
 * @Filename: AdminActBiddingTypeController.java
 * @Version: 1.0
 * @Author: 王朋
 * @Email: wpjava@163.com
 *
 */
@Controller
@RequestMapping(value = "admin/promotion/biddingtype")
public class AdminActBiddingTypeController extends BaseController {

    @Resource
    private IActBiddingService actBiddingService;

    /**
     * 集合竞价类型列表页
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "", method = { RequestMethod.GET })
    public String index(Map<String, Object> dataMap) {
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return "admin/promotion/bidding/biddingtypelist";
    }

    /**
     * 分页取出数据
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<ActBiddingType>> list(HttpServletRequest request,
                                                                   HttpServletResponse response,
                                                                   Map<String, Object> dataMap) {
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);

        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);

        ServiceResult<List<ActBiddingType>> serviceResult = actBiddingService.getActBiddingTypes(
            queryMap, pager);
        pager = serviceResult.getPager();
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }
        List<ActBiddingType> list = serviceResult.getResult();

        HttpJsonResult<List<ActBiddingType>> jsonResult = new HttpJsonResult<List<ActBiddingType>>();
        jsonResult.setRows(list);
        jsonResult.setTotal(pager.getRowsCount());
        return jsonResult;
    }

    @RequestMapping(value = "add", method = { RequestMethod.GET })
    public String add(Map<String, Object> dataMap) {
        return "admin/promotion/bidding/biddingtypeadd";
    }

    @RequestMapping(value = "create", method = { RequestMethod.POST })
    public String create(ActBiddingType actBiddingType, HttpServletRequest request,
                         Map<String, Object> dataMap) {
        SystemAdmin user = WebAdminSession.getAdminUser(request);
        actBiddingType.setCreateId(user.getId());
        actBiddingType.setCreateName(user.getName());

        actBiddingType.setState(ActBiddingType.ACTGROUPTYPE_STATE_0);

        ServiceResult<Integer> serviceResult = actBiddingService.saveActBiddingType(actBiddingType);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                dataMap.put("actBiddingType", actBiddingType);
                dataMap.put("message", serviceResult.getMessage());
                return "admin/promotion/bidding/biddingtypeadd";
            }
        }
        return "redirect:";
    }

    @RequestMapping(value = "edit", method = { RequestMethod.GET })
    public String edit(Integer id, Map<String, Object> dataMap) {
        ServiceResult<ActBiddingType> serviceResult = actBiddingService.getActBiddingTypeById(id);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            }
        }
        dataMap.put("actBiddingType", serviceResult.getResult());
        return "admin/promotion/bidding/biddingtypeedit";
    }

    @RequestMapping(value = "update", method = { RequestMethod.POST })
    public String update(ActBiddingType actBiddingType, HttpServletRequest request,
                         Map<String, Object> dataMap) {
        SystemAdmin user = WebAdminSession.getAdminUser(request);
        actBiddingType.setUpdateId(user.getId());
        actBiddingType.setUpdateName(user.getName());

        ServiceResult<Integer> serviceResult = actBiddingService
            .updateActBiddingType(actBiddingType);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                dataMap.put("actBiddingType", actBiddingType);
                dataMap.put("message", serviceResult.getMessage());
                return "admin/promotion/bidding/biddingtypeadd";
            }
        }
        return "redirect:";
    }

    /**
     * 删除集合竞价分类
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "del", method = RequestMethod.POST)
    public @ResponseBody HttpJsonResult<Object> del(HttpServletRequest request,
                                                    @RequestParam("id") Integer id) {
        HttpJsonResult<Object> jsonResult = new HttpJsonResultForAjax<Object>();
        ServiceResult<Boolean> serviceResult = actBiddingService.delActBiddingType(id);
        if (!serviceResult.getSuccess()) {
            jsonResult.setMessage(serviceResult.getMessage());
        }
        return jsonResult;
    }

    /**
     * 集合竞价分类启用
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "auditYes", method = RequestMethod.POST)
    public @ResponseBody HttpJsonResult<Object> auditYes(HttpServletRequest request,
                                                         @RequestParam("id") Integer id) {
        HttpJsonResult<Object> jsonResult = new HttpJsonResultForAjax<Object>();
        ServiceResult<Boolean> serviceResult = actBiddingService.auditYesActBiddingType(id);
        if (!serviceResult.getSuccess()) {
            jsonResult.setMessage(serviceResult.getMessage());
        }
        return jsonResult;
    }

    /**
     * 集合竞价分类停用
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "auditNo", method = RequestMethod.POST)
    public @ResponseBody HttpJsonResult<Object> auditNo(HttpServletRequest request,
                                                        @RequestParam("id") Integer id) {
        HttpJsonResult<Object> jsonResult = new HttpJsonResultForAjax<Object>();
        ServiceResult<Boolean> serviceResult = actBiddingService.auditNoActBiddingType(id);
        if (!serviceResult.getSuccess()) {
            jsonResult.setMessage(serviceResult.getMessage());
        }
        return jsonResult;
    }

}
