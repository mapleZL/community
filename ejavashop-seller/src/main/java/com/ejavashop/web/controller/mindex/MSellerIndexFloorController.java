package com.ejavashop.web.controller.mindex;

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
import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.WebUtil;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.entity.seller.SellerUser;
import com.ejavashop.entity.shopm.MSellerIndexFloor;
import com.ejavashop.service.mseller.IMSellerIndexService;
import com.ejavashop.web.controller.BaseController;
import com.ejavashop.web.util.WebSellerSession;

/**
 * 移动端首页楼层管理controller
 *                       
 * @Filename: MSellerIndexFloorController.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Controller
@RequestMapping(value = "seller/mindex/floor")
public class MSellerIndexFloorController extends BaseController {

    @Resource
    private IMSellerIndexService mSellerIndexService;

    /**
     * 移动端首页楼层列表页
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "", method = { RequestMethod.GET })
    public String index(Map<String, Object> dataMap) {
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return "seller/mindex/floor/floorlist";
    }

    /**
     * 分页取出数据
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<MSellerIndexFloor>> list(HttpServletRequest request,
                                                                      HttpServletResponse response,
                                                                      Map<String, Object> dataMap) {
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        SellerUser sellerUser = WebSellerSession.getSellerUser(request);
        queryMap.put("q_sellerId", sellerUser.getSellerId() + "");
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);

        ServiceResult<List<MSellerIndexFloor>> serviceResult = mSellerIndexService
            .getMSellerIndexFloors(queryMap, pager);
        pager = serviceResult.getPager();
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }
        List<MSellerIndexFloor> list = serviceResult.getResult();

        HttpJsonResult<List<MSellerIndexFloor>> jsonResult = new HttpJsonResult<List<MSellerIndexFloor>>();
        jsonResult.setRows(list);
        jsonResult.setTotal(pager.getRowsCount());
        return jsonResult;
    }

    @RequestMapping(value = "add", method = { RequestMethod.GET })
    public String add(HttpServletRequest request, Map<String, Object> dataMap) {
        return "seller/mindex/floor/flooradd";
    }

    @RequestMapping(value = "create", method = { RequestMethod.POST })
    public String create(MSellerIndexFloor mSellerIndexFloor, HttpServletRequest request,
                         Map<String, Object> dataMap) {

        SellerUser sellerUser = WebSellerSession.getSellerUser(request);
        Integer userId = sellerUser.getId();
        mSellerIndexFloor.setCreateUserId(userId);
        mSellerIndexFloor.setCreateUserName(sellerUser.getName());
        mSellerIndexFloor.setUpdateUserId(sellerUser.getId());
        mSellerIndexFloor.setUpdateUserName(sellerUser.getName());
        mSellerIndexFloor.setSellerId(sellerUser.getSellerId());

        mSellerIndexFloor.setStatus(MSellerIndexFloor.STATUS_0);

        ServiceResult<Boolean> serviceResult = mSellerIndexService
            .saveMSellerIndexFloor(mSellerIndexFloor);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                dataMap.put("mSellerIndexFloor", mSellerIndexFloor);
                dataMap.put("message", serviceResult.getMessage());
                return "seller/mindex/floor/flooradd";
            }
        }
        return "redirect:";
    }

    @RequestMapping(value = "edit", method = { RequestMethod.GET })
    public String edit(int mSellerIndexFloorId, Map<String, Object> dataMap) {
        ServiceResult<MSellerIndexFloor> serviceResult = mSellerIndexService
            .getMSellerIndexFloorById(mSellerIndexFloorId);

        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                dataMap.put("message", serviceResult.getMessage());
                return "seller/mindex/floor/floorlist";
            }
        }
        MSellerIndexFloor mSellerIndexFloor = serviceResult.getResult();

        dataMap.put("mSellerIndexFloor", mSellerIndexFloor);
        return "seller/mindex/floor/flooredit";
    }

    @RequestMapping(value = "update", method = { RequestMethod.POST })
    public String update(MSellerIndexFloor mSellerIndexFloor, HttpServletRequest request,
                         Map<String, Object> dataMap) {

        SellerUser sellerUser = WebSellerSession.getSellerUser(request);
        mSellerIndexFloor.setUpdateUserId(sellerUser.getId());
        mSellerIndexFloor.setUpdateUserName(sellerUser.getName());
        mSellerIndexFloor.setSellerId(sellerUser.getSellerId());

        ServiceResult<Boolean> serviceResult = mSellerIndexService
            .updateMSellerIndexFloor(mSellerIndexFloor);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                dataMap.put("mSellerIndexFloor", mSellerIndexFloor);
                dataMap.put("message", serviceResult.getMessage());
                return "seller/mindex/floor/flooredit";
            }
        }
        return "redirect:";
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public @ResponseBody HttpJsonResult<Boolean> delete(HttpServletRequest request,
                                                        @RequestParam("id") Integer id) {

        ServiceResult<MSellerIndexFloor> mSellerIndexFloorResult = mSellerIndexService
            .getMSellerIndexFloorById(id);
        if (!mSellerIndexFloorResult.getSuccess()) {
            return new HttpJsonResult<Boolean>(mSellerIndexFloorResult.getMessage());
        }
        if (mSellerIndexFloorResult.getResult() == null) {
            return new HttpJsonResult<Boolean>("楼层信息获取失败");
        }
        if (mSellerIndexFloorResult.getResult().getStatus().equals(MSellerIndexFloor.STATUS_1)) {
            return new HttpJsonResult<Boolean>("正在使用的楼层不能删除");
        }

        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
        ServiceResult<Boolean> serviceResult = mSellerIndexService.deleteMSellerIndexFloor(id);
        if (!serviceResult.getSuccess()) {
            jsonResult.setMessage(serviceResult.getMessage());
        }
        return jsonResult;
    }

    @RequestMapping(value = "up", method = RequestMethod.POST)
    public @ResponseBody HttpJsonResult<Object> up(HttpServletRequest request,
                                                   @RequestParam("id") Integer id) {
        HttpJsonResult<Object> jsonResult = new HttpJsonResult<Object>();

        SellerUser sellerUser = WebSellerSession.getSellerUser(request);
        MSellerIndexFloor mSellerIndexFloor = new MSellerIndexFloor();
        mSellerIndexFloor.setId(id);
        mSellerIndexFloor.setStatus(MSellerIndexFloor.STATUS_1);
        mSellerIndexFloor.setUpdateUserId(sellerUser.getId());
        mSellerIndexFloor.setUpdateUserName(sellerUser.getName());
        ServiceResult<Boolean> serviceResult = mSellerIndexService
            .updateMSellerIndexFloor(mSellerIndexFloor);
        if (!serviceResult.getSuccess()) {
            jsonResult.setMessage(serviceResult.getMessage());
        }
        return jsonResult;
    }

    @RequestMapping(value = "down", method = RequestMethod.POST)
    public @ResponseBody HttpJsonResult<Object> down(HttpServletRequest request,
                                                     @RequestParam("id") Integer id) {
        HttpJsonResult<Object> jsonResult = new HttpJsonResult<Object>();
        SellerUser sellerUser = WebSellerSession.getSellerUser(request);

        MSellerIndexFloor mSellerIndexFloor = new MSellerIndexFloor();
        mSellerIndexFloor.setId(id);
        mSellerIndexFloor.setStatus(MSellerIndexFloor.STATUS_0);
        mSellerIndexFloor.setUpdateUserId(sellerUser.getId());
        mSellerIndexFloor.setUpdateUserName(sellerUser.getName());
        ServiceResult<Boolean> serviceResult = mSellerIndexService
            .updateMSellerIndexFloor(mSellerIndexFloor);
        if (!serviceResult.getSuccess()) {
            jsonResult.setMessage(serviceResult.getMessage());
        }
        return jsonResult;
    }
}
