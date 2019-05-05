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
import com.ejavashop.entity.shopm.MSellerIndexBanner;
import com.ejavashop.service.mseller.IMSellerIndexService;
import com.ejavashop.web.controller.BaseController;
import com.ejavashop.web.util.UploadUtil;
import com.ejavashop.web.util.WebSellerSession;

/**
 * 移动端首页轮播图管理controller
 *                       
 * @Filename: MSellerIndexBannerController.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Controller
@RequestMapping(value = "seller/mindex/banner")
public class MSellerIndexBannerController extends BaseController {

    @Resource
    private IMSellerIndexService mSellerIndexService;

    /**
     * 移动端首页轮播图列表页
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "", method = { RequestMethod.GET })
    public String index(Map<String, Object> dataMap) {
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return "seller/mindex/banner/bannerlist";
    }

    /**
     * 分页取出数据
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<MSellerIndexBanner>> list(HttpServletRequest request,
                                                                       HttpServletResponse response,
                                                                       Map<String, Object> dataMap) {
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);

        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        SellerUser sellerUser = WebSellerSession.getSellerUser(request);
        queryMap.put("q_sellerId", sellerUser.getSellerId() + "");

        ServiceResult<List<MSellerIndexBanner>> serviceResult = mSellerIndexService
            .getMSellerIndexBanners(queryMap, pager);
        pager = serviceResult.getPager();
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }
        List<MSellerIndexBanner> list = serviceResult.getResult();

        HttpJsonResult<List<MSellerIndexBanner>> jsonResult = new HttpJsonResult<List<MSellerIndexBanner>>();
        jsonResult.setRows(list);
        jsonResult.setTotal(pager.getRowsCount());
        return jsonResult;
    }

    @RequestMapping(value = "add", method = { RequestMethod.GET })
    public String add(HttpServletRequest request, Map<String, Object> dataMap) {
        return "seller/mindex/banner/banneradd";
    }

    @RequestMapping(value = "create", method = { RequestMethod.POST })
    public String create(MSellerIndexBanner mSellerIndexBanner, HttpServletRequest request,
                         Map<String, Object> dataMap) {

        SellerUser sellerUser = WebSellerSession.getSellerUser(request);
        Integer userId = sellerUser.getId();
        mSellerIndexBanner.setCreateUserId(userId);
        mSellerIndexBanner.setCreateUserName(sellerUser.getName());
        mSellerIndexBanner.setUpdateUserId(sellerUser.getId());
        mSellerIndexBanner.setUpdateUserName(sellerUser.getName());
        mSellerIndexBanner.setSellerId(sellerUser.getSellerId());

        mSellerIndexBanner.setStatus(MSellerIndexBanner.STATUS_0);

        // 上传图片
        String image = UploadUtil.getInstance().mSellerIndexUploadFile2ImageServer("imageFile",
            request);
        if (image != null && !"".equals(image)) {
            mSellerIndexBanner.setImage(image);
        }

        ServiceResult<Boolean> serviceResult = mSellerIndexService
            .saveMSellerIndexBanner(mSellerIndexBanner);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                dataMap.put("mSellerIndexBanner", mSellerIndexBanner);
                dataMap.put("message", serviceResult.getMessage());
                return "seller/mindex/banner/banneradd";
            }
        }
        return "redirect:";
    }

    @RequestMapping(value = "edit", method = { RequestMethod.GET })
    public String edit(int mSellerIndexBannerId, Map<String, Object> dataMap) {
        ServiceResult<MSellerIndexBanner> serviceResult = mSellerIndexService
            .getMSellerIndexBannerById(mSellerIndexBannerId);

        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                dataMap.put("message", serviceResult.getMessage());
                return "seller/mindex/banner/bannerlist";
            }
        }
        MSellerIndexBanner mSellerIndexBanner = serviceResult.getResult();

        dataMap.put("mSellerIndexBanner", mSellerIndexBanner);
        return "seller/mindex/banner/banneredit";
    }

    @RequestMapping(value = "update", method = { RequestMethod.POST })
    public String update(MSellerIndexBanner mSellerIndexBanner, HttpServletRequest request,
                         Map<String, Object> dataMap) {

        SellerUser sellerUser = WebSellerSession.getSellerUser(request);
        mSellerIndexBanner.setUpdateUserId(sellerUser.getId());
        mSellerIndexBanner.setUpdateUserName(sellerUser.getName());
        mSellerIndexBanner.setSellerId(sellerUser.getSellerId());

        // 上传图片
        String image = UploadUtil.getInstance().mSellerIndexUploadFile2ImageServer("imageFile",
            request);
        if (image != null && !"".equals(image)) {
            mSellerIndexBanner.setImage(image);
        }

        ServiceResult<Boolean> serviceResult = mSellerIndexService
            .updateMSellerIndexBanner(mSellerIndexBanner);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                dataMap.put("mSellerIndexBanner", mSellerIndexBanner);
                dataMap.put("message", serviceResult.getMessage());
                return "seller/mindex/banner/banneredit";
            }
        }
        return "redirect:";
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public @ResponseBody HttpJsonResult<Boolean> delete(HttpServletRequest request,
                                                        @RequestParam("id") Integer id) {

        ServiceResult<MSellerIndexBanner> mSellerIndexBannerResult = mSellerIndexService
            .getMSellerIndexBannerById(id);
        if (!mSellerIndexBannerResult.getSuccess()) {
            return new HttpJsonResult<Boolean>(mSellerIndexBannerResult.getMessage());
        }
        if (mSellerIndexBannerResult.getResult() == null) {
            return new HttpJsonResult<Boolean>("轮播图信息获取失败");
        }
        if (mSellerIndexBannerResult.getResult().getStatus().equals(MSellerIndexBanner.STATUS_1)) {
            return new HttpJsonResult<Boolean>("正在使用的轮播图不能删除");
        }

        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
        ServiceResult<Boolean> serviceResult = mSellerIndexService.deleteMSellerIndexBanner(id);
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
        MSellerIndexBanner mSellerIndexBanner = new MSellerIndexBanner();
        mSellerIndexBanner.setId(id);
        mSellerIndexBanner.setStatus(MSellerIndexBanner.STATUS_1);
        mSellerIndexBanner.setUpdateUserId(sellerUser.getId());
        mSellerIndexBanner.setUpdateUserName(sellerUser.getName());
        ServiceResult<Boolean> serviceResult = mSellerIndexService
            .updateMSellerIndexBanner(mSellerIndexBanner);
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

        MSellerIndexBanner mSellerIndexBanner = new MSellerIndexBanner();
        mSellerIndexBanner.setId(id);
        mSellerIndexBanner.setStatus(MSellerIndexBanner.STATUS_0);
        mSellerIndexBanner.setUpdateUserId(sellerUser.getId());
        mSellerIndexBanner.setUpdateUserName(sellerUser.getName());
        ServiceResult<Boolean> serviceResult = mSellerIndexService
            .updateMSellerIndexBanner(mSellerIndexBanner);
        if (!serviceResult.getSuccess()) {
            jsonResult.setMessage(serviceResult.getMessage());
        }
        return jsonResult;
    }

}
