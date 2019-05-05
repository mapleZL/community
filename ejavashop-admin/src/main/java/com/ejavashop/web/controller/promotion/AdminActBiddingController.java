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
import com.ejavashop.core.StringUtil;
import com.ejavashop.core.WebUtil;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.entity.product.Product;
import com.ejavashop.entity.promotion.bidding.ActBidding;
import com.ejavashop.entity.promotion.bidding.ActBiddingPrice;
import com.ejavashop.entity.promotion.bidding.ActBiddingType;
import com.ejavashop.entity.seller.Seller;
import com.ejavashop.entity.system.SystemAdmin;
import com.ejavashop.service.product.IProductService;
import com.ejavashop.service.promotion.IActBiddingService;
import com.ejavashop.service.seller.ISellerService;
import com.ejavashop.web.controller.BaseController;
import com.ejavashop.web.util.WebAdminSession;

/**
 * 集合竞价的管理
 *                       
 * @Filename: AdminActBiddingController.java
 * @Version: 1.0
 * @Author: 王朋
 * @Email: wpjava@163.com
 *
 */
@Controller
@RequestMapping(value = "admin/promotion/actbidding")
public class AdminActBiddingController extends BaseController {

    @Resource
    private IActBiddingService actBiddingService;

    @Resource
    private IProductService    productService;

    @Resource
    private ISellerService     sellerService;

    @RequestMapping(value = "", method = { RequestMethod.GET })
    public String index(Map<String, Object> dataMap) throws Exception {
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);

        ServiceResult<List<ActBiddingType>> result = actBiddingService.getActBiddingTypesAll();
        dataMap.put("actBiddingTypes", result.getResult());

        return "admin/promotion/bidding/actbiddinglist";
    }

    /**
     * 集合竞价列表页
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<ActBidding>> list(HttpServletRequest request,
                                                               HttpServletResponse response,
                                                               Map<String, Object> dataMap) {
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        ServiceResult<List<ActBidding>> serviceResult = actBiddingService.getActBiddings(queryMap,
            pager);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }

        HttpJsonResult<List<ActBidding>> jsonResult = new HttpJsonResult<List<ActBidding>>();
        List<ActBidding> actBiddings = serviceResult.getResult();
        for (ActBidding actBidding : actBiddings) {
            ServiceResult<Product> resultProduct = productService.getProductById(actBidding
                .getProductId());
            actBidding.setProductName(resultProduct.getResult().getName1());

            ServiceResult<Seller> sellerResult = sellerService.getSellerById(actBidding
                .getSellerId());
            actBidding.setSellerName(sellerResult.getResult().getSellerName());

            ServiceResult<ActBiddingType> resultType = actBiddingService
                .getActBiddingTypeById(actBidding.getType());
            ActBiddingType actBiddingType = resultType.getResult();
            actBidding.setTypeName(actBiddingType.getName());
        }

        jsonResult.setRows(actBiddings);
        jsonResult.setTotal(pager.getRowsCount());
        return jsonResult;
    }

    /**
     * 跳转到编辑页面
     * @param id
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "edit", method = { RequestMethod.GET })
    public String edit(int id, Map<String, Object> dataMap) {
        ServiceResult<ActBidding> serviceResult = actBiddingService.getActBiddingById(id);

        ActBidding actBidding = serviceResult.getResult();
        ServiceResult<Product> resultProduct = productService.getProductById(actBidding
            .getProductId());
        Product product = resultProduct.getResult();
        actBidding.setProductName(product.getName1());

        ServiceResult<Seller> sellerResult = sellerService.getSellerById(actBidding.getSellerId());
        actBidding.setSellerName(sellerResult.getResult().getSellerName());

        dataMap.put("actBidding", actBidding);

        ServiceResult<List<ActBiddingPrice>> actBiddingPricesServiceResult = actBiddingService
            .getActBiddingByIds(actBidding.getId());

        dataMap.put("actBiddingPrices", actBiddingPricesServiceResult.getResult());

        return "admin/promotion/bidding/actbiddingedit";
    }

    /**
     * 查看
     * @param id
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "look", method = { RequestMethod.GET })
    public String look(int id, Map<String, Object> dataMap) {
        ServiceResult<ActBidding> serviceResult = actBiddingService.getActBiddingById(id);

        ActBidding actBidding = serviceResult.getResult();
        ServiceResult<Product> resultProduct = productService.getProductById(actBidding
            .getProductId());
        Product product = resultProduct.getResult();
        actBidding.setProductName(product.getName1());

        ServiceResult<ActBiddingType> resultType = actBiddingService
            .getActBiddingTypeById(actBidding.getType());
        ActBiddingType actBiddingType = resultType.getResult();
        actBidding.setTypeName(actBiddingType.getName());

        ServiceResult<Seller> sellerResult = sellerService.getSellerById(actBidding.getSellerId());
        actBidding.setSellerName(sellerResult.getResult().getSellerName());
        dataMap.put("actBidding", actBidding);

        ServiceResult<List<ActBiddingPrice>> actBiddingPricesServiceResult = actBiddingService
            .getActBiddingByIds(actBidding.getId());

        dataMap.put("actBiddingPrices", actBiddingPricesServiceResult.getResult());

        return "admin/promotion/bidding/actbiddinglook";
    }

    /**
     * 更新
     * @param actBidding
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "update", method = { RequestMethod.POST })
    public String update(ActBidding actBidding, HttpServletRequest request,
                         Map<String, Object> dataMap) {
        ServiceResult<ActBidding> serviceResult = actBiddingService.getActBiddingById(actBidding
            .getId());

        SystemAdmin adminUser = WebAdminSession.getAdminUser(request);

        ActBidding actBiddingNew = serviceResult.getResult();
        actBiddingNew.setIsBest(actBidding.getIsBest());
        actBiddingNew.setSortNum(actBidding.getSortNum());
        actBiddingNew.setAuditId(adminUser.getId());
        actBiddingNew.setAuditName(adminUser.getName());
        actBiddingNew.setVirtualSaleNum(actBidding.getVirtualSaleNum());

        ServiceResult<Integer> result = actBiddingService.updateActBidding(actBiddingNew);
        if (!result.getSuccess()) {
            return "redirect:/admin/promotion/actbidding/edit";
        }

        return "redirect:/admin/promotion/actbidding";
    }

    /**
     * 审核通过
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "auditYes", method = RequestMethod.POST)
    public @ResponseBody HttpJsonResult<Object> auditYes(HttpServletRequest request,
                                                         @RequestParam("id") Integer id) {
        HttpJsonResult<Object> jsonResult = new HttpJsonResult<Object>();

        String auditOpinion = request.getParameter("auditOpinion");
        if (StringUtil.isEmpty(auditOpinion, true)) {
            auditOpinion = "审核通过";
        }
        ServiceResult<Boolean> serviceResult = actBiddingService
            .updateActBiddingStateAndAuditOpinion(id, ActBidding.STATE_3, auditOpinion);

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

        String auditOpinion = request.getParameter("auditOpinion");
        if (StringUtil.isEmpty(auditOpinion, true)) {
            return new HttpJsonResult<Object>("请填写驳回原因。");
        }

        ServiceResult<Boolean> serviceResult = actBiddingService
            .updateActBiddingStateAndAuditOpinion(id, ActBidding.STATE_4, auditOpinion);

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

        ServiceResult<Boolean> serviceResult = actBiddingService.updateActBiddingActState(id,
            ActBidding.ACTIVITY_STATE_2);

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

        ServiceResult<Boolean> serviceResult = actBiddingService.updateActBiddingActState(id,
            ActBidding.ACTIVITY_STATE_3);

        if (!serviceResult.getSuccess()) {
            jsonResult.setMessage(serviceResult.getMessage());
        }
        return jsonResult;
    }
}
