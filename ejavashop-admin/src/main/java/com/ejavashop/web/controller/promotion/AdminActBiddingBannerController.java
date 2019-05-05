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
import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.WebUtil;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.entity.promotion.bidding.ActBiddingBanner;
import com.ejavashop.entity.system.SystemAdmin;
import com.ejavashop.service.promotion.IActBiddingBannerService;
import com.ejavashop.web.controller.BaseController;
import com.ejavashop.web.util.UploadUtil;
import com.ejavashop.web.util.WebAdminSession;

/**
 * 集合竞价首页轮播图管理
 *                       
 * @Filename: AdminActBiddingBannerController.java
 * @Version: 1.0
 * @Author: 王朋
 * @Email: wpjava@163.com
 *
 */
@Controller
@RequestMapping(value = "admin/actbiddingbanner/banner")
public class AdminActBiddingBannerController extends BaseController {

    @Resource
    private IActBiddingBannerService actBiddingBannerService;

    /**
     * PC端首页轮播图列表页
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "", method = { RequestMethod.GET })
    public String index(Map<String, Object> dataMap) {
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return "admin/promotion/bidding/biddingbannerlist";
    }

    /**
     * 分页取出数据
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<ActBiddingBanner>> list(HttpServletRequest request,
                                                                     HttpServletResponse response,
                                                                     Map<String, Object> dataMap) {
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);

        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);

        ServiceResult<List<ActBiddingBanner>> serviceResult = actBiddingBannerService
            .getActBiddingBanners(queryMap, pager);
        pager = serviceResult.getPager();
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }
        List<ActBiddingBanner> list = serviceResult.getResult();

        HttpJsonResult<List<ActBiddingBanner>> jsonResult = new HttpJsonResult<List<ActBiddingBanner>>();
        jsonResult.setRows(list);
        jsonResult.setTotal(pager.getRowsCount());
        return jsonResult;
    }

    @RequestMapping(value = "add", method = { RequestMethod.GET })
    public String add(HttpServletRequest request, Map<String, Object> dataMap) {
        return "admin/promotion/bidding/biddingbanneradd";
    }

    @RequestMapping(value = "create", method = { RequestMethod.POST })
    public String create(ActBiddingBanner actBiddingBanner, HttpServletRequest request,
                         Map<String, Object> dataMap) {

        SystemAdmin adminUser = WebAdminSession.getAdminUser(request);
        Integer userId = adminUser.getId();
        actBiddingBanner.setCreateUserId(userId);
        actBiddingBanner.setCreateUserName(adminUser.getName());
        actBiddingBanner.setUpdateUserId(adminUser.getId());
        actBiddingBanner.setUpdateUserName(adminUser.getName());

        actBiddingBanner.setState(ConstantsEJS.YES_NO_0);

        // 上传图片
        String image = UploadUtil.getInstance().advUploadFile2ImageServer("imageFile", request);
        if (image != null && !"".equals(image)) {
            actBiddingBanner.setImage(image);
        }

        ServiceResult<Boolean> serviceResult = actBiddingBannerService
            .saveActBiddingBanner(actBiddingBanner);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                dataMap.put("actBiddingBanner", actBiddingBanner);
                dataMap.put("message", serviceResult.getMessage());
                return "admin/promotion/bidding/biddingbanneradd";
            }
        }
        return "redirect:/admin/actbiddingbanner/banner";
    }

    @RequestMapping(value = "edit", method = { RequestMethod.GET })
    public String edit(int id, Map<String, Object> dataMap) {
        ServiceResult<ActBiddingBanner> serviceResult = actBiddingBannerService
            .getActBiddingBannerById(id);

        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                dataMap.put("message", serviceResult.getMessage());
                return "admin/promotion/bidding/biddingbannerlist";
            }
        }
        ActBiddingBanner actBiddingBanner = serviceResult.getResult();

        dataMap.put("actBiddingBanner", actBiddingBanner);
        return "admin/promotion/bidding/biddingbanneredit";
    }

    @RequestMapping(value = "update", method = { RequestMethod.POST })
    public String update(ActBiddingBanner actBiddingBanner, HttpServletRequest request,
                         Map<String, Object> dataMap) {
        SystemAdmin adminUser = WebAdminSession.getAdminUser(request);
        actBiddingBanner.setUpdateUserId(adminUser.getId());
        actBiddingBanner.setUpdateUserName(adminUser.getName());

        // 上传图片
        String image = UploadUtil.getInstance().advUploadFile2ImageServer("imageFile", request);
        if (image != null && !"".equals(image)) {
            actBiddingBanner.setImage(image);
        }

        ServiceResult<Boolean> serviceResult = actBiddingBannerService
            .updateActBiddingBanner(actBiddingBanner);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                dataMap.put("actBiddingBanner", actBiddingBanner);
                dataMap.put("message", serviceResult.getMessage());
                return "admin/promotion/bidding/biddingbanneredit";
            }
        }
        return "redirect:/admin/actbiddingbanner/banner";
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public @ResponseBody HttpJsonResult<Boolean> delete(HttpServletRequest request,
                                                        @RequestParam("id") Integer id) {

        ServiceResult<ActBiddingBanner> actBiddingBannerResult = actBiddingBannerService
            .getActBiddingBannerById(id);
        if (!actBiddingBannerResult.getSuccess()) {
            return new HttpJsonResult<Boolean>(actBiddingBannerResult.getMessage());
        }
        if (actBiddingBannerResult.getResult() == null) {
            return new HttpJsonResult<Boolean>("轮播图信息获取失败");
        }
        if (actBiddingBannerResult.getResult().getState().equals(ConstantsEJS.YES_NO_1)) {
            return new HttpJsonResult<Boolean>("正在使用的轮播图不能删除");
        }

        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
        ServiceResult<Boolean> serviceResult = actBiddingBannerService.deleteActBiddingBanner(id);
        if (!serviceResult.getSuccess()) {
            jsonResult.setMessage(serviceResult.getMessage());
        }
        return jsonResult;
    }

    @RequestMapping(value = "up", method = RequestMethod.POST)
    public @ResponseBody HttpJsonResult<Object> up(HttpServletRequest request,
                                                   @RequestParam("id") Integer id) {
        HttpJsonResult<Object> jsonResult = new HttpJsonResult<Object>();

        SystemAdmin adminUser = WebAdminSession.getAdminUser(request);
        ActBiddingBanner actBiddingBanner = new ActBiddingBanner();
        actBiddingBanner.setId(id);
        actBiddingBanner.setState(ConstantsEJS.YES_NO_1);
        actBiddingBanner.setUpdateUserId(adminUser.getId());
        actBiddingBanner.setUpdateUserName(adminUser.getName());
        ServiceResult<Boolean> serviceResult = actBiddingBannerService
            .updateActBiddingBanner(actBiddingBanner);
        if (!serviceResult.getSuccess()) {
            jsonResult.setMessage(serviceResult.getMessage());
        }
        return jsonResult;
    }

    @RequestMapping(value = "down", method = RequestMethod.POST)
    public @ResponseBody HttpJsonResult<Object> down(HttpServletRequest request,
                                                     @RequestParam("id") Integer id) {
        HttpJsonResult<Object> jsonResult = new HttpJsonResult<Object>();
        SystemAdmin adminUser = WebAdminSession.getAdminUser(request);

        ActBiddingBanner actBiddingBanner = new ActBiddingBanner();
        actBiddingBanner.setId(id);
        actBiddingBanner.setState(ConstantsEJS.YES_NO_0);
        actBiddingBanner.setUpdateUserId(adminUser.getId());
        actBiddingBanner.setUpdateUserName(adminUser.getName());
        ServiceResult<Boolean> serviceResult = actBiddingBannerService
            .updateActBiddingBanner(actBiddingBanner);
        if (!serviceResult.getSuccess()) {
            jsonResult.setMessage(serviceResult.getMessage());
        }
        return jsonResult;
    }

}
