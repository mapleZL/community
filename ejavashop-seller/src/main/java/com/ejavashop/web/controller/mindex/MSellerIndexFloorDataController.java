package com.ejavashop.web.controller.mindex;

import java.util.HashMap;
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
import com.ejavashop.entity.product.Product;
import com.ejavashop.entity.seller.SellerUser;
import com.ejavashop.entity.shopm.MSellerIndexFloor;
import com.ejavashop.entity.shopm.MSellerIndexFloorData;
import com.ejavashop.service.mseller.IMSellerIndexService;
import com.ejavashop.service.product.IProductService;
import com.ejavashop.web.controller.BaseController;
import com.ejavashop.web.util.UploadUtil;
import com.ejavashop.web.util.WebSellerSession;

/**
 * 移动端首页楼层数据管理controller
 *                       
 * @Filename: MSellerIndexFloorDataController.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Controller
@RequestMapping(value = "seller/mindex/floordata")
public class MSellerIndexFloorDataController extends BaseController {

    @Resource
    private IMSellerIndexService mSellerIndexService;

    @Resource
    private IProductService      productService;

    /**
     * 移动端首页楼层数据列表页
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "", method = { RequestMethod.GET })
    public String index(HttpServletRequest request, Map<String, Object> dataMap) {

        SellerUser sellerUser = WebSellerSession.getSellerUser(request);

        Map<String, String> queryMap = new HashMap<String, String>();
        queryMap.put("q_sellerId", sellerUser.getSellerId() + "");

        ServiceResult<List<MSellerIndexFloor>> serviceResult = mSellerIndexService
            .getMSellerIndexFloors(queryMap, null);
        dataMap.put("floors", serviceResult.getResult());

        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return "seller/mindex/floordata/floordatalist";
    }

    /**
     * 分页取出数据
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<MSellerIndexFloorData>> list(HttpServletRequest request,
                                                                          HttpServletResponse response,
                                                                          Map<String, Object> dataMap) {
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        SellerUser sellerUser = WebSellerSession.getSellerUser(request);
        queryMap.put("q_sellerId", sellerUser.getSellerId() + "");

        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);

        ServiceResult<List<MSellerIndexFloorData>> serviceResult = mSellerIndexService
            .getMSellerIndexFloorDatas(queryMap, pager);
        pager = serviceResult.getPager();
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }
        List<MSellerIndexFloorData> list = serviceResult.getResult();

        HttpJsonResult<List<MSellerIndexFloorData>> jsonResult = new HttpJsonResult<List<MSellerIndexFloorData>>();
        jsonResult.setRows(list);
        jsonResult.setTotal(pager.getRowsCount());
        return jsonResult;
    }

    @RequestMapping(value = "add", method = { RequestMethod.GET })
    public String add(HttpServletRequest request, Map<String, Object> dataMap) {

        SellerUser sellerUser = WebSellerSession.getSellerUser(request);

        Map<String, String> queryMap = new HashMap<String, String>();
        queryMap.put("q_sellerId", sellerUser.getSellerId() + "");
        ServiceResult<List<MSellerIndexFloor>> serviceResult = mSellerIndexService
            .getMSellerIndexFloors(queryMap, null);
        dataMap.put("floors", serviceResult.getResult());

        dataMap.put("sellerId", sellerUser.getSellerId());

        return "seller/mindex/floordata/floordataadd";
    }

    @RequestMapping(value = "create", method = { RequestMethod.POST })
    public String create(MSellerIndexFloorData mSellerIndexFloorData, HttpServletRequest request,
                         Map<String, Object> dataMap) {

        SellerUser sellerUser = WebSellerSession.getSellerUser(request);
        Integer userId = sellerUser.getId();
        mSellerIndexFloorData.setCreateUserId(userId);
        mSellerIndexFloorData.setCreateUserName(sellerUser.getName());
        mSellerIndexFloorData.setUpdateUserId(sellerUser.getId());
        mSellerIndexFloorData.setUpdateUserName(sellerUser.getName());
        mSellerIndexFloorData.setSellerId(sellerUser.getSellerId());

        if (mSellerIndexFloorData.getDataType() == MSellerIndexFloorData.DATA_TYPE_1) {
            mSellerIndexFloorData.setImage(null);
            mSellerIndexFloorData.setLinkUrl(null);
        } else if (mSellerIndexFloorData.getDataType() == MSellerIndexFloorData.DATA_TYPE_2) {
            // 上传图片
            String image = UploadUtil.getInstance().mSellerIndexUploadFile2ImageServer("imageFile",
                request);
            if (image != null && !"".equals(image)) {
                mSellerIndexFloorData.setImage(image);
            }
            mSellerIndexFloorData.setRefId(0);
        }

        ServiceResult<Boolean> serviceResult = mSellerIndexService
            .saveMSellerIndexFloorData(mSellerIndexFloorData);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                dataMap.put("mSellerIndexFloorData", mSellerIndexFloorData);
                dataMap.put("message", serviceResult.getMessage());
                return "seller/mindex/floordata/floordataadd";
            }
        }
        return "redirect:";
    }

    @RequestMapping(value = "edit", method = { RequestMethod.GET })
    public String edit(int mSellerIndexFloorDataId, Map<String, Object> dataMap,
                       HttpServletRequest request) {

        SellerUser sellerUser = WebSellerSession.getSellerUser(request);
        dataMap.put("sellerId", sellerUser.getSellerId());

        Map<String, String> queryMap = new HashMap<String, String>();
        queryMap.put("q_sellerId", sellerUser.getSellerId() + "");
        ServiceResult<List<MSellerIndexFloor>> floorResult = mSellerIndexService
            .getMSellerIndexFloors(queryMap, null);
        dataMap.put("floors", floorResult.getResult());

        ServiceResult<MSellerIndexFloorData> serviceResult = mSellerIndexService
            .getMSellerIndexFloorDataById(mSellerIndexFloorDataId);

        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                dataMap.put("message", serviceResult.getMessage());
                return "seller/mindex/floordata/floordatalist";
            }
        }
        MSellerIndexFloorData mSellerIndexFloorData = serviceResult.getResult();

        if (mSellerIndexFloorData.getDataType() == MSellerIndexFloorData.DATA_TYPE_1) {
            ServiceResult<Product> productResult = productService
                .getProductById(mSellerIndexFloorData.getRefId());
            mSellerIndexFloorData.setProduct(productResult.getResult());
        }

        dataMap.put("mSellerIndexFloorData", mSellerIndexFloorData);
        return "seller/mindex/floordata/floordataedit";
    }

    @RequestMapping(value = "update", method = { RequestMethod.POST })
    public String update(MSellerIndexFloorData mSellerIndexFloorData, HttpServletRequest request,
                         Map<String, Object> dataMap) {

        SellerUser sellerUser = WebSellerSession.getSellerUser(request);
        mSellerIndexFloorData.setUpdateUserId(sellerUser.getId());
        mSellerIndexFloorData.setUpdateUserName(sellerUser.getName());
        mSellerIndexFloorData.setSellerId(sellerUser.getSellerId());

        if (mSellerIndexFloorData.getDataType() == MSellerIndexFloorData.DATA_TYPE_1) {
            mSellerIndexFloorData.setImage("");
            mSellerIndexFloorData.setLinkUrl("");
        } else if (mSellerIndexFloorData.getDataType() == MSellerIndexFloorData.DATA_TYPE_2) {
            // 上传图片
            String image = UploadUtil.getInstance().mSellerIndexUploadFile2ImageServer("imageFile",
                request);
            if (image != null && !"".equals(image)) {
                mSellerIndexFloorData.setImage(image);
            }
            mSellerIndexFloorData.setRefId(0);
        }

        ServiceResult<Boolean> serviceResult = mSellerIndexService
            .updateMSellerIndexFloorData(mSellerIndexFloorData);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                dataMap.put("mSellerIndexFloorData", mSellerIndexFloorData);
                dataMap.put("message", serviceResult.getMessage());
                return "seller/mindex/floordata/floordataedit";
            }
        }
        return "redirect:";
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public @ResponseBody HttpJsonResult<Boolean> delete(HttpServletRequest request,
                                                        @RequestParam("id") Integer id) {
        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
        ServiceResult<Boolean> serviceResult = mSellerIndexService.deleteMSellerIndexFloorData(id);
        if (!serviceResult.getSuccess()) {
            jsonResult.setMessage(serviceResult.getMessage());
        }
        return jsonResult;
    }

}
