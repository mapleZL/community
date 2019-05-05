package com.ejavashop.web.controller.promotion;

import java.math.BigDecimal;
import java.util.ArrayList;
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
import com.ejavashop.entity.promotion.bidding.ActBidding;
import com.ejavashop.entity.promotion.bidding.ActBiddingPrice;
import com.ejavashop.entity.promotion.bidding.ActBiddingType;
import com.ejavashop.entity.seller.SellerUser;
import com.ejavashop.service.product.IProductService;
import com.ejavashop.service.promotion.IActBiddingService;
import com.ejavashop.web.controller.BaseController;
import com.ejavashop.web.util.UploadUtil;
import com.ejavashop.web.util.WebSellerSession;

/**
 * 集合竞价
 *                       
 * @Filename: SellerActBiddingController.java
 * @Version: 1.0
 * @Author: 王朋
 * @Email: wpjava@163.com
 *
 */
@Controller
@RequestMapping(value = "seller/promotion/actbidding")
public class SellerActBiddingController extends BaseController {

    @Resource
    private IActBiddingService actBiddingService;

    @Resource
    private IProductService    productService;

    @RequestMapping(value = "", method = { RequestMethod.GET })
    public String index(Map<String, Object> dataMap) throws Exception {
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);

        ServiceResult<List<ActBiddingType>> result = actBiddingService.getActBiddingTypesAll();
        dataMap.put("actBiddingTypes", result.getResult());

        return "seller/promotion/bidding/actbiddinglist";
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

        SellerUser sellerUser = WebSellerSession.getSellerUser(request);

        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        queryMap.put("q_sellerId", sellerUser.getSellerId() + "");
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
     * 跳转到添加页面
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "add", method = { RequestMethod.GET })
    public String add(Map<String, Object> dataMap) throws Exception {

        ServiceResult<List<ActBiddingType>> result = actBiddingService.getActBiddingTypesAll();
        dataMap.put("actBiddingTypes", result.getResult());

        return "seller/promotion/bidding/actbiddingadd";
    }

    /**
     * 新加
     * @param actBidding
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "create", method = { RequestMethod.POST })
    public String create(ActBidding actBidding, HttpServletRequest request,
                         Map<String, Object> dataMap) {
        SellerUser sellerUser = WebSellerSession.getSellerUser(request);

        actBidding.setSellerId(sellerUser.getSellerId());
        actBidding.setVirtualSaleNum(0);
        actBidding.setSaleNum(0);
        actBidding.setSortNum(0);
        actBidding.setChannel(1);

        actBidding.setIsBest(ActBidding.IS_BEST_0);
        actBidding.setActivityState(ActBidding.ACTIVITY_STATE_1);
        actBidding.setState(ActBidding.STATE_1);
        actBidding.setExecuteState(ActBidding.EXECUTE_STATE_0);
        actBidding.setTypeState(ActBidding.TYPE_STATE_1);

        //        saleNumber priceNmber
        List<ActBiddingPrice> actBiddingPrices = new ArrayList<ActBiddingPrice>();
        String[] saleNumbers = request.getParameterValues("saleNumberAll");
        String[] priceNmbers = request.getParameterValues("priceNmberAll");
        if (saleNumbers != null && priceNmbers != null) {
            if (saleNumbers.length == priceNmbers.length) {
                ActBiddingPrice actBiddingPrice = null;
                for (int i = 0; i < priceNmbers.length; i++) {
                    actBiddingPrice = new ActBiddingPrice();
                    actBiddingPrice.setPrice(new BigDecimal(priceNmbers[i]));
                    actBiddingPrice.setSaleNum(new Integer(saleNumbers[i]));
                    actBiddingPrices.add(actBiddingPrice);
                }
            }
        }

        // 上传图片
        String image = UploadUtil.getInstance().productSellerIndexUploadFileImageServer(
            "imageFile", request);
        if (image != null && !"".equals(image)) {
            actBidding.setImage(image);
        }

        ServiceResult<Boolean> serviceResult = actBiddingService.saveActBiddingAndPrice(actBidding,
            actBiddingPrices);
        if (!serviceResult.getSuccess()) {
            dataMap.put("actBidding", actBidding);
            dataMap.put("message", serviceResult.getMessage());
            return "seller/promotion/bidding/actbiddingadd";
        }
        return "redirect:/seller/promotion/actbidding";
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

        dataMap.put("actBidding", actBidding);

        ServiceResult<List<ActBiddingPrice>> actBiddingPricesServiceResult = actBiddingService
            .getActBiddingByIds(actBidding.getId());

        dataMap.put("actBiddingPrices", actBiddingPricesServiceResult.getResult());

        ServiceResult<List<ActBiddingType>> result = actBiddingService.getActBiddingTypesAll();
        dataMap.put("actBiddingTypes", result.getResult());

        return "seller/promotion/bidding/actbiddingedit";
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

        dataMap.put("actBidding", actBidding);

        ServiceResult<List<ActBiddingPrice>> actBiddingPricesServiceResult = actBiddingService
            .getActBiddingByIds(actBidding.getId());

        dataMap.put("actBiddingPrices", actBiddingPricesServiceResult.getResult());

        return "seller/promotion/bidding/actbiddinglook";
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
        SellerUser sellerUser = WebSellerSession.getSellerUser(request);

        ActBidding actBiddingOld = actBiddingService.getActBiddingById(actBidding.getId())
            .getResult();
        if (actBiddingOld.getSellerId().intValue() != sellerUser.getSellerId()) {
            return null;
        }

        List<ActBiddingPrice> actBiddingPrices = new ArrayList<ActBiddingPrice>();
        String[] saleNumbers = request.getParameterValues("saleNumberAll");
        String[] priceNmbers = request.getParameterValues("priceNmberAll");
        if (saleNumbers != null && priceNmbers != null) {
            if (saleNumbers.length == priceNmbers.length) {
                ActBiddingPrice actBiddingPrice = null;
                for (int i = 0; i < priceNmbers.length; i++) {
                    actBiddingPrice = new ActBiddingPrice();
                    actBiddingPrice.setPrice(new BigDecimal(priceNmbers[i]));
                    actBiddingPrice.setSaleNum(new Integer(saleNumbers[i]));
                    actBiddingPrices.add(actBiddingPrice);
                }
            }
        }

        // 上传图片
        String image = UploadUtil.getInstance().productSellerIndexUploadFileImageServer(
            "imageFile", request);
        if (image != null && !"".equals(image)) {
            actBidding.setImage(image);
        }

        ServiceResult<Boolean> serviceResult = actBiddingService.updateActBiddingAndPrice(
            actBidding, actBiddingPrices);
        if (!serviceResult.getSuccess()) {
            return "redirect:/seller/promotion/actbidding/edit";
        }
        return "redirect:/seller/promotion/actbidding";
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

        ServiceResult<ActBidding> resultActBidding = actBiddingService.getActBiddingById(id);
        ActBidding actBidding = resultActBidding.getResult();
        if (actBidding.getState().intValue() == ActBidding.STATE_2
            || actBidding.getState().intValue() == ActBidding.STATE_3) {
            return null;
        }
        if (actBidding.getSellerId().intValue() != sellerUser.getSellerId()) {
            return null;
        }

        ServiceResult<Boolean> serviceResult = actBiddingService.updateActBiddingState(id,
            ActBidding.STATE_2);

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

        ServiceResult<ActBidding> resultActBidding = actBiddingService.getActBiddingById(id);
        ActBidding actBidding = resultActBidding.getResult();
        if (actBidding.getState().intValue() != ActBidding.STATE_2) {
            return null;
        }
        if (actBidding.getSellerId().intValue() != sellerUser.getSellerId()) {
            return null;
        }

        ServiceResult<Boolean> serviceResult = actBiddingService.updateActBiddingState(id,
            ActBidding.STATE_1);

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

        ServiceResult<ActBidding> resultActBidding = actBiddingService.getActBiddingById(id);
        ActBidding actBidding = resultActBidding.getResult();
        if (actBidding.getActivityState().intValue() != ActBidding.ACTIVITY_STATE_1) {
            return null;
        }
        if (actBidding.getSellerId().intValue() != sellerUser.getSellerId()) {
            return null;
        }

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

        SellerUser sellerUser = WebSellerSession.getSellerUser(request);

        ServiceResult<ActBidding> resultActBidding = actBiddingService.getActBiddingById(id);
        ActBidding actBidding = resultActBidding.getResult();
        if (actBidding.getSellerId().intValue() != sellerUser.getSellerId()) {
            return null;
        }

        ServiceResult<Boolean> serviceResult = actBiddingService.updateActBiddingActState(id,
            ActBidding.ACTIVITY_STATE_3);

        if (!serviceResult.getSuccess()) {
            jsonResult.setMessage(serviceResult.getMessage());
        }
        return jsonResult;
    }
}
