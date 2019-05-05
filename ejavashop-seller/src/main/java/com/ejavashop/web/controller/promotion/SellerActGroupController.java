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
import com.ejavashop.entity.product.Product;
import com.ejavashop.entity.promotion.group.ActGroup;
import com.ejavashop.entity.promotion.group.ActGroupType;
import com.ejavashop.entity.seller.SellerUser;
import com.ejavashop.service.product.IProductService;
import com.ejavashop.service.promotion.IActGroupService;
import com.ejavashop.web.controller.BaseController;
import com.ejavashop.web.util.UploadUtil;
import com.ejavashop.web.util.WebSellerSession;

/**
 * 团购相关的操作
 *                       
 * @Filename: SellerActGroupController.java
 * @Version: 1.0
 * @Author: 王朋
 * @Email: wpjava@163.com
 *
 */
@Controller
@RequestMapping(value = "seller/promotion/actgroup")
public class SellerActGroupController extends BaseController {

    @Resource
    private IActGroupService actGroupService;

    @Resource
    private IProductService  productService;

    @RequestMapping(value = "", method = { RequestMethod.GET })
    public String index(Map<String, Object> dataMap) throws Exception {
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);

        ServiceResult<List<ActGroupType>> result = actGroupService.getActGroupTypesAll();
        dataMap.put("actGroupTypes", result.getResult());

        return "seller/promotion/group/actgrouplist";
    }

    /**
     * 集合竞价列表页
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<ActGroup>> list(HttpServletRequest request,
                                                             HttpServletResponse response,
                                                             Map<String, Object> dataMap) {

        SellerUser sellerUser = WebSellerSession.getSellerUser(request);

        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        queryMap.put("q_sellerId", sellerUser.getSellerId() + "");
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        ServiceResult<List<ActGroup>> serviceResult = actGroupService.getActGroups(queryMap, pager);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }

        HttpJsonResult<List<ActGroup>> jsonResult = new HttpJsonResult<List<ActGroup>>();
        List<ActGroup> actGroups = serviceResult.getResult();
        for (ActGroup actGroup : actGroups) {
            ServiceResult<Product> resultProduct = productService.getProductById(actGroup
                .getProductId());
            actGroup.setProductName(resultProduct.getResult().getName1());

            ServiceResult<ActGroupType> resultActGroupType = actGroupService
                .getActGroupTypeById(actGroup.getType());
            actGroup.setTypeName(resultActGroupType.getResult().getName());
        }

        jsonResult.setRows(actGroups);
        jsonResult.setTotal(pager.getRowsCount());
        return jsonResult;
    }

    /**
     * 跳转到添加页面
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "add", method = { RequestMethod.GET })
    public String add(Map<String, Object> dataMap) throws Exception {
        ServiceResult<List<ActGroupType>> result = actGroupService.getActGroupTypesAll();
        dataMap.put("actGroupTypes", result.getResult());
        return "seller/promotion/group/actgroupadd";
    }

    /**
     * 新加
     * @param activityBidding
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "create", method = { RequestMethod.POST })
    public String create(ActGroup actGroup, HttpServletRequest request, Map<String, Object> dataMap) {
        SellerUser sellerUser = WebSellerSession.getSellerUser(request);

        actGroup.setSellerId(sellerUser.getSellerId());
        actGroup.setVirtualSaleNum(0);
        actGroup.setSaleNum(0);
        actGroup.setSortNum(0);

        actGroup.setIsBest(ActGroup.IS_BEST_0);
        actGroup.setActivityState(ActGroup.ACTIVITY_STATE_1);
        actGroup.setState(ActGroup.STATE_1);
        actGroup.setTypeState(ActGroup.TYPE_STATE_1);

        // 上传图片
        String image = UploadUtil.getInstance().productSellerIndexUploadFileImageServer(
            "imageFile", request);
        if (image != null && !"".equals(image)) {
            actGroup.setImage(image);
        }

        ServiceResult<Integer> serviceResult = actGroupService.saveActGroup(actGroup);
        if (!serviceResult.getSuccess()) {
            dataMap.put("actGroup", actGroup);
            dataMap.put("message", serviceResult.getMessage());
            ServiceResult<List<ActGroupType>> result = actGroupService.getActGroupTypesAll();
            dataMap.put("actGroupTypes", result.getResult());
            return "seller/promotion/group/actgroupadd";
        }
        return "redirect:/seller/promotion/actgroup";
    }

    /**
     * 跳转到编辑页面
     * @param id
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "edit", method = { RequestMethod.GET })
    public String edit(int id, Map<String, Object> dataMap) {
        ServiceResult<ActGroup> serviceResult = actGroupService.getActGroupById(id);

        ActGroup actGroup = serviceResult.getResult();
        ServiceResult<Product> resultProduct = productService.getProductById(actGroup
            .getProductId());
        Product product = resultProduct.getResult();
        actGroup.setProductName(product.getName1());

        dataMap.put("actGroup", actGroup);

        ServiceResult<List<ActGroupType>> result = actGroupService.getActGroupTypesAll();
        dataMap.put("actGroupTypes", result.getResult());

        return "seller/promotion/group/actgroupedit";
    }

    /**
     * 查看
     * @param id
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "look", method = { RequestMethod.GET })
    public String look(int id, Map<String, Object> dataMap) {
        ServiceResult<ActGroup> serviceResult = actGroupService.getActGroupById(id);

        ActGroup actGroup = serviceResult.getResult();
        ServiceResult<Product> resultProduct = productService.getProductById(actGroup
            .getProductId());
        Product product = resultProduct.getResult();
        actGroup.setProductName(product.getName1());

        ServiceResult<List<ActGroupType>> result = actGroupService.getActGroupTypesAll();
        dataMap.put("actGroupTypes", result.getResult());

        dataMap.put("actGroup", actGroup);

        return "seller/promotion/group/actgrouplook";
    }

    /**
     * 更新
     * @param activityBidding
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "update", method = { RequestMethod.POST })
    public String update(ActGroup actGroup, HttpServletRequest request, Map<String, Object> dataMap) {

        // 上传图片
        String image = UploadUtil.getInstance().productSellerIndexUploadFileImageServer(
            "imageFile", request);
        if (image != null && !"".equals(image)) {
            actGroup.setImage(image);
        } else {
            actGroup.setImage(null);
        }

        ServiceResult<Integer> serviceResult = actGroupService.updateActGroup(actGroup);
        if (!serviceResult.getSuccess()) {
            return "redirect:edit";
        }
        return "redirect:/seller/promotion/actgroup";
    }

    /**
     * 提交审核
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "auditYes", method = RequestMethod.POST)
    public @ResponseBody HttpJsonResult<Object> auditYes(HttpServletRequest request,
                                                         @RequestParam("id") Integer id) {
        HttpJsonResult<Object> jsonResult = new HttpJsonResult<Object>();

        SellerUser sellerUser = WebSellerSession.getSellerUser(request);

        ServiceResult<ActGroup> resultActGroup = actGroupService.getActGroupById(id);
        ActGroup actGroup = resultActGroup.getResult();
        if (actGroup.getState().intValue() == ActGroup.STATE_2
            || actGroup.getState().intValue() == ActGroup.STATE_3) {
            return null;
        }
        if (actGroup.getSellerId().intValue() != sellerUser.getSellerId()) {
            return null;
        }

        ServiceResult<Boolean> serviceResult = actGroupService.updateActGroupState(id,
            ActGroup.STATE_2);

        if (!serviceResult.getSuccess()) {
            jsonResult.setMessage(serviceResult.getMessage());
        }
        return jsonResult;
    }

    /**
     * 审核撤回
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "auditNo", method = RequestMethod.POST)
    public @ResponseBody HttpJsonResult<Object> auditNo(HttpServletRequest request,
                                                        @RequestParam("id") Integer id) {
        HttpJsonResult<Object> jsonResult = new HttpJsonResult<Object>();

        SellerUser sellerUser = WebSellerSession.getSellerUser(request);

        ServiceResult<ActGroup> resultActGroup = actGroupService.getActGroupById(id);
        ActGroup actGroup = resultActGroup.getResult();
        if (actGroup.getState().intValue() != ActGroup.STATE_2) {
            return null;
        }
        if (actGroup.getSellerId().intValue() != sellerUser.getSellerId()) {
            return null;
        }

        ServiceResult<Boolean> serviceResult = actGroupService.updateActGroupState(id,
            ActGroup.STATE_1);

        if (!serviceResult.getSuccess()) {
            jsonResult.setMessage(serviceResult.getMessage());
        }
        return jsonResult;
    }

    /**
     * 发布
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "release", method = RequestMethod.POST)
    public @ResponseBody HttpJsonResult<Object> release(HttpServletRequest request,
                                                        @RequestParam("id") Integer id) {
        HttpJsonResult<Object> jsonResult = new HttpJsonResult<Object>();

        SellerUser sellerUser = WebSellerSession.getSellerUser(request);

        ServiceResult<ActGroup> resultActGroup = actGroupService.getActGroupById(id);
        ActGroup actGroup = resultActGroup.getResult();

        if (actGroup.getActivityState().intValue() != ActGroup.ACTIVITY_STATE_1) {
            return null;
        }
        if (actGroup.getSellerId().intValue() != sellerUser.getSellerId()) {
            return null;
        }

        ServiceResult<Boolean> serviceResult = actGroupService.updateActGroupActivityState(id,
            ActGroup.ACTIVITY_STATE_2);

        if (!serviceResult.getSuccess()) {
            jsonResult.setMessage(serviceResult.getMessage());
        }
        return jsonResult;
    }

    /**
     * 结束
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "releaseFinal", method = RequestMethod.POST)
    public @ResponseBody HttpJsonResult<Object> releaseFinal(HttpServletRequest request,
                                                             @RequestParam("id") Integer id) {
        HttpJsonResult<Object> jsonResult = new HttpJsonResult<Object>();

        SellerUser sellerUser = WebSellerSession.getSellerUser(request);

        ServiceResult<ActGroup> resultActGroup = actGroupService.getActGroupById(id);
        ActGroup actGroup = resultActGroup.getResult();

        if (actGroup.getActivityState().intValue() != ActGroup.ACTIVITY_STATE_2) {
            return null;
        }
        if (actGroup.getSellerId().intValue() != sellerUser.getSellerId()) {
            return null;
        }

        ServiceResult<Boolean> serviceResult = actGroupService.updateActGroupActivityState(id,
            ActGroup.ACTIVITY_STATE_3);

        if (!serviceResult.getSuccess()) {
            jsonResult.setMessage(serviceResult.getMessage());
        }
        return jsonResult;
    }

}
