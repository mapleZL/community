package com.phkj.web.controller.mindex.banner;

import com.phkj.core.*;
import com.phkj.core.exception.BusinessException;
import com.phkj.core.response.ResponseUtil;
import com.phkj.entity.mindex.MIndexBanner;
import com.phkj.entity.system.SystemAdmin;
import com.phkj.service.file.IFileService;
import com.phkj.service.mindex.IMIndexService;
import com.phkj.web.controller.BaseController;
import com.phkj.web.util.WebAdminSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 移动端首页轮播图管理controller
 *                       
 * @Filename: MIndexBannerController.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Controller
@RequestMapping(value = "admin/mindex/banner")
public class MIndexBannerController extends BaseController {

    @Resource
    private IMIndexService mIndexService;

    @Resource
    private IFileService fileService;

    /**
     * 移动端首页轮播图列表页
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "", method = { RequestMethod.GET })
    public String index(Map<String, Object> dataMap) {
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return "admin/mindex/banner/bannerlist";
    }

    /**
     * 分页取出数据
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "list", method = { RequestMethod.GET })
    public @ResponseBody
    HttpJsonResult<List<MIndexBanner>> list(HttpServletRequest request,
                                            HttpServletResponse response,
                                            Map<String, Object> dataMap) {
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);

        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);

        ServiceResult<List<MIndexBanner>> serviceResult = mIndexService.getMIndexBanners(queryMap,
            pager);
        pager = serviceResult.getPager();
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }
        List<MIndexBanner> list = serviceResult.getResult();

        HttpJsonResult<List<MIndexBanner>> jsonResult = new HttpJsonResult<List<MIndexBanner>>();
        jsonResult.setRows(list);
        jsonResult.setTotal(pager.getRowsCount());
        return jsonResult;
    }


    /**
     * 微信 -- 首页轮播图
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/bannerList", method = {RequestMethod.GET})
    @ResponseBody
    public ResponseUtil getBannerList(int pageNum, int pageSize) {
        pageNum = pageNum == 0 ? 1 : pageNum;
        pageSize = pageSize == 0 ? 10 : pageSize;
        ServiceResult<Set<String>> result = mIndexService.getBannerList(pageNum, pageSize);
        return ResponseUtil.createResp(result.getCode(), result.getMessage(), true, result.getResult());
    }

    @RequestMapping(value = "add", method = { RequestMethod.GET })
    public String add(HttpServletRequest request, Map<String, Object> dataMap) {
        return "admin/mindex/banner/banneradd";
    }

    @RequestMapping(value = "create", method = { RequestMethod.POST })
    public String create(MIndexBanner mIndexBanner, HttpServletRequest request,
                         Map<String, Object> dataMap) {

        SystemAdmin adminUser = WebAdminSession.getAdminUser(request);
        Integer userId = adminUser.getId();
        mIndexBanner.setCreateUserId(userId);
        mIndexBanner.setCreateUserName(adminUser.getName());
        mIndexBanner.setUpdateUserId(adminUser.getId());
        mIndexBanner.setUpdateUserName(adminUser.getName());

        mIndexBanner.setStatus(MIndexBanner.STATUS_0);

        // 上传图片
//        String image = UploadUtil.getInstance().mIndexUploadFile2ImageServer("imageFile", request);
        //表单文件流
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        CommonsMultipartFile multipartFile = (CommonsMultipartFile) multipartRequest.getFile("imageFile");
        String image = fileService.uploadFile(multipartFile);
        if (image != null && !"".equals(image)) {
            mIndexBanner.setImage(image);
        }

        ServiceResult<Boolean> serviceResult = mIndexService.saveMIndexBanner(mIndexBanner);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                dataMap.put("mIndexBanner", mIndexBanner);
                dataMap.put("message", serviceResult.getMessage());
                return "admin/mindex/banner/banneradd";
            }
        }
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return "admin/mindex/banner/bannerlist";
    }

    @RequestMapping(value = "edit", method = { RequestMethod.GET })
    public String edit(int mIndexBannerId, Map<String, Object> dataMap) {
        ServiceResult<MIndexBanner> serviceResult = mIndexService
            .getMIndexBannerById(mIndexBannerId);

        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                dataMap.put("message", serviceResult.getMessage());
                return "admin/mindex/banner/bannerlist";
            }
        }
        MIndexBanner mIndexBanner = serviceResult.getResult();

        dataMap.put("mIndexBanner", mIndexBanner);
        return "admin/mindex/banner/banneredit";
    }

    @RequestMapping(value = "update", method = { RequestMethod.POST })
    public String update(MIndexBanner mIndexBanner, HttpServletRequest request,
                         Map<String, Object> dataMap) {

        SystemAdmin adminUser = WebAdminSession.getAdminUser(request);
        mIndexBanner.setUpdateUserId(adminUser.getId());
        mIndexBanner.setUpdateUserName(adminUser.getName());

        // 上传图片
//        String image = UploadUtil.getInstance().mIndexUploadFile2ImageServer("imageFile", request);
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        CommonsMultipartFile multipartFile = (CommonsMultipartFile) multipartRequest.getFile("imageFile");
        String image = fileService.uploadFile(multipartFile);
        if (image != null && !"".equals(image)) {
            mIndexBanner.setImage(image);
        }

        ServiceResult<Boolean> serviceResult = mIndexService.updateMIndexBanner(mIndexBanner);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                dataMap.put("mIndexBanner", mIndexBanner);
                dataMap.put("message", serviceResult.getMessage());
                return "admin/mindex/banner/banneredit";
            }
        }
        return "redirect:";
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public @ResponseBody HttpJsonResult<Boolean> delete(HttpServletRequest request,
                                                        @RequestParam("id") Integer id) {

        ServiceResult<MIndexBanner> mIndexBannerResult = mIndexService.getMIndexBannerById(id);
        if (!mIndexBannerResult.getSuccess()) {
            return new HttpJsonResult<Boolean>(mIndexBannerResult.getMessage());
        }
        if (mIndexBannerResult.getResult() == null) {
            return new HttpJsonResult<Boolean>("轮播图信息获取失败");
        }
        if (mIndexBannerResult.getResult().getStatus().equals(MIndexBanner.STATUS_1)) {
            return new HttpJsonResult<Boolean>("正在使用的轮播图不能删除");
        }

        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
        ServiceResult<Boolean> serviceResult = mIndexService.deleteMIndexBanner(id);
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
        MIndexBanner mIndexBanner = new MIndexBanner();
        mIndexBanner.setId(id);
        mIndexBanner.setStatus(MIndexBanner.STATUS_1);
        mIndexBanner.setUpdateUserId(adminUser.getId());
        mIndexBanner.setUpdateUserName(adminUser.getName());
        ServiceResult<Boolean> serviceResult = mIndexService.updateMIndexBanner(mIndexBanner);
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

        MIndexBanner mIndexBanner = new MIndexBanner();
        mIndexBanner.setId(id);
        mIndexBanner.setStatus(MIndexBanner.STATUS_0);
        mIndexBanner.setUpdateUserId(adminUser.getId());
        mIndexBanner.setUpdateUserName(adminUser.getName());
        ServiceResult<Boolean> serviceResult = mIndexService.updateMIndexBanner(mIndexBanner);
        if (!serviceResult.getSuccess()) {
            jsonResult.setMessage(serviceResult.getMessage());
        }
        return jsonResult;
    }

}
