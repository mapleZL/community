package com.ejavashop.web.controller.promotion;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.ConvertUtil;
import com.ejavashop.core.HttpJsonResult;
import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.WebUtil;
import com.ejavashop.entity.product.Product;
import com.ejavashop.entity.promotion.bidding.ActBidding;
import com.ejavashop.entity.promotion.bidding.ActBiddingBanner;
import com.ejavashop.entity.promotion.bidding.ActBiddingPrice;
import com.ejavashop.entity.promotion.bidding.ActBiddingType;
import com.ejavashop.service.product.IProductService;
import com.ejavashop.service.promotion.IActBiddingBannerService;
import com.ejavashop.service.promotion.IActBiddingService;
import com.ejavashop.web.controller.BaseController;

/**
 * 集合竞价列表页
 *                       
 * @Filename: ActGroupListController.java
 * @Version: 1.0
 * @Author: 王朋
 * @Email: wpjava@163.com
 *
 */
@Controller
public class ActBiddingListController extends BaseController {

    @Resource
    private IActBiddingService       actBiddingService;

    @Resource
    private IActBiddingBannerService actBiddingBannerService;

    @Resource
    private IProductService          productService;

    private final static int         BIDDING_PAGESIZE = 20;

    @RequestMapping(value = "/bidding-sale.html", method = RequestMethod.GET)
    public String bidding(HttpServletRequest request, HttpServletResponse response,
                          Map<String, Object> dataMap) {
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap, BIDDING_PAGESIZE);
        String typeStr = request.getParameter("type");
        int type = ConvertUtil.toInt(typeStr, 0);

        Map<String, String> queryMap = new HashMap<String, String>();
        queryMap.put("biddingfront", "1"); //1,在售 查询是 activity_state=2，state=3 start_time< now <end_time
        queryMap.put("type", type + "");
        queryMap.put("channel", ConstantsEJS.PC_MOBILE_MOBILE + "");

        ServiceResult<List<ActBidding>> serviceResultBidding = actBiddingService
            .getActBiddingsFront(queryMap, pager);
        List<ActBidding> actBiddings = serviceResultBidding.getResult();
        for (ActBidding actBidding : actBiddings) {
            ServiceResult<Product> resultProduct = productService.getProductById(actBidding
                .getProductId());
            actBidding.setProductName(resultProduct.getResult().getName1());

            lowestPrice(actBidding);
        }

        ServiceResult<List<ActBiddingBanner>> serviceResult = actBiddingBannerService
            .getActBiddingBannersPcMobile(ConstantsEJS.PC_MOBILE_MOBILE);
        List<ActBiddingBanner> actBiddingBanners = serviceResult.getResult();

        ServiceResult<List<ActBiddingType>> serviceResultType = actBiddingService
            .getActBiddingTypesFront();
        List<ActBiddingType> actBiddingTypes = serviceResultType.getResult();

        String typeName = "全部";
        if (type != 0) {
            ActBiddingType actBiddingType = null;
            for (int i = 0; i < actBiddingTypes.size(); i++) {
                actBiddingType = actBiddingTypes.get(i);
                if (actBiddingType.getId().intValue() == type) {
                    typeName = actBiddingType.getName();
                }
            }
        }

        dataMap.put("actBiddings", actBiddings);
        dataMap.put("actBiddingBanners", actBiddingBanners);
        dataMap.put("actBiddingTypes", actBiddingTypes);
        dataMap.put("type", type);
        dataMap.put("pagesize", BIDDING_PAGESIZE);
        dataMap.put("biddingfront", 1);
        dataMap.put("typeName", typeName);

        return "h5v1/promotion/actbiddinglist";
    }

    @RequestMapping(value = "/bidding-start.html", method = RequestMethod.GET)
    public String biddingStart(HttpServletRequest request, HttpServletResponse response,
                               Map<String, Object> dataMap) {
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap, BIDDING_PAGESIZE);
        String typeStr = request.getParameter("type");
        int type = ConvertUtil.toInt(typeStr, 0);

        Map<String, String> queryMap = new HashMap<String, String>();
        queryMap.put("biddingfront", "3"); //3、即将开始 查询是 activity_state=2，state=3  now < start_time
        queryMap.put("type", type + "");
        queryMap.put("channel", ConstantsEJS.PC_MOBILE_MOBILE + "");

        ServiceResult<List<ActBidding>> serviceResultBidding = actBiddingService
            .getActBiddingsFront(queryMap, pager);
        List<ActBidding> actBiddings = serviceResultBidding.getResult();
        for (ActBidding actBidding : actBiddings) {
            ServiceResult<Product> resultProduct = productService.getProductById(actBidding
                .getProductId());
            actBidding.setProductName(resultProduct.getResult().getName1());

            lowestPrice(actBidding);
        }

        ServiceResult<List<ActBiddingBanner>> serviceResult = actBiddingBannerService
            .getActBiddingBannersPcMobile(ConstantsEJS.PC_MOBILE_MOBILE);
        List<ActBiddingBanner> actBiddingBanners = serviceResult.getResult();

        ServiceResult<List<ActBiddingType>> serviceResultType = actBiddingService
            .getActBiddingTypesFront();
        List<ActBiddingType> actBiddingTypes = serviceResultType.getResult();

        String typeName = "全部";
        if (type != 0) {
            ActBiddingType actBiddingType = null;
            for (int i = 0; i < actBiddingTypes.size(); i++) {
                actBiddingType = actBiddingTypes.get(i);
                if (actBiddingType.getId().intValue() == type) {
                    typeName = actBiddingType.getName();
                }
            }
        }

        dataMap.put("actBiddings", actBiddings);
        dataMap.put("actBiddingBanners", actBiddingBanners);
        dataMap.put("actBiddingTypes", actBiddingTypes);
        dataMap.put("pagesize", BIDDING_PAGESIZE);
        dataMap.put("type", type);
        dataMap.put("biddingfront", 3);
        dataMap.put("typeName", typeName);

        return "h5v1/promotion/actbiddinglist";
    }

    @RequestMapping(value = "/bidding-end.html", method = RequestMethod.GET)
    public String biddingEnd(HttpServletRequest request, HttpServletResponse response,
                             Map<String, Object> dataMap) {
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap, BIDDING_PAGESIZE);
        String typeStr = request.getParameter("type");
        int type = ConvertUtil.toInt(typeStr, 0);

        Map<String, String> queryMap = new HashMap<String, String>();
        queryMap.put("biddingfront", "2"); //2、已经结束 查询是 activity_state=3，state=3
        queryMap.put("type", type + "");
        queryMap.put("channel", ConstantsEJS.PC_MOBILE_MOBILE + "");

        ServiceResult<List<ActBidding>> serviceResultBidding = actBiddingService
            .getActBiddingsFront(queryMap, pager);
        List<ActBidding> actBiddings = serviceResultBidding.getResult();
        for (ActBidding actBidding : actBiddings) {
            ServiceResult<Product> resultProduct = productService.getProductById(actBidding
                .getProductId());
            actBidding.setProductName(resultProduct.getResult().getName1());

            ServiceResult<List<ActBiddingPrice>> resultActBiddingPrices = actBiddingService
                .getActBiddingByIds(actBidding.getId());
            List<ActBiddingPrice> actBiddingPrices = resultActBiddingPrices.getResult();
            actBidding.setLowestPrice(getPriceNow(actBidding, actBiddingPrices));
        }

        ServiceResult<List<ActBiddingBanner>> serviceResult = actBiddingBannerService
            .getActBiddingBannersPcMobile(ConstantsEJS.PC_MOBILE_MOBILE);
        List<ActBiddingBanner> actBiddingBanners = serviceResult.getResult();

        ServiceResult<List<ActBiddingType>> serviceResultType = actBiddingService
            .getActBiddingTypesFront();
        List<ActBiddingType> actBiddingTypes = serviceResultType.getResult();

        String typeName = "全部";
        if (type != 0) {
            ActBiddingType actBiddingType = null;
            for (int i = 0; i < actBiddingTypes.size(); i++) {
                actBiddingType = actBiddingTypes.get(i);
                if (actBiddingType.getId().intValue() == type) {
                    typeName = actBiddingType.getName();
                }
            }
        }

        dataMap.put("actBiddings", actBiddings);
        dataMap.put("actBiddingBanners", actBiddingBanners);
        dataMap.put("actBiddingTypes", actBiddingTypes);
        dataMap.put("pagesize", BIDDING_PAGESIZE);
        dataMap.put("type", type);
        dataMap.put("typeName", typeName);
        dataMap.put("biddingfront", 2);

        return "h5v1/promotion/actbiddinglist";
    }

    /**
     * 返回json结果
     * @param request
     * @param stack
     * @return
     */
    @RequestMapping(value = "/biddingJson.html", method = RequestMethod.GET)
    public @ResponseBody HttpJsonResult<List<ActBidding>> biddingJson(HttpServletRequest request,
                                                                      Map<String, Object> dataMap) {
        HttpJsonResult<List<ActBidding>> jsonResult = new HttpJsonResult<List<ActBidding>>();
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap, BIDDING_PAGESIZE);
        String typeStr = request.getParameter("type");
        int type = ConvertUtil.toInt(typeStr, 0);

        String biddingfrontStr = request.getParameter("biddingfront");
        int biddingfront = ConvertUtil.toInt(biddingfrontStr, 0);

        if (biddingfront != 1 && biddingfront != 2 && biddingfront != 3) {
            return jsonResult;
        }

        Map<String, String> queryMap = new HashMap<String, String>();
        queryMap.put("biddingfront", biddingfrontStr);
        queryMap.put("type", type + "");
        queryMap.put("channel", ConstantsEJS.PC_MOBILE_MOBILE + "");

        ServiceResult<List<ActBidding>> serviceResultBidding = actBiddingService
            .getActBiddingsFront(queryMap, pager);
        List<ActBidding> actBiddings = serviceResultBidding.getResult();
        for (ActBidding actBidding : actBiddings) {
            ServiceResult<Product> resultProduct = productService.getProductById(actBidding
                .getProductId());
            actBidding.setProductName(resultProduct.getResult().getName1());

            lowestPrice(actBidding);
        }

        jsonResult.setRows(actBiddings);
        jsonResult.setTotal(actBiddings.size());
        return jsonResult;
    }

    /**
     * 算出阶梯价格的最低价格
     * @param actBidding
     */
    private void lowestPrice(ActBidding actBidding) {
        ServiceResult<List<ActBiddingPrice>> resultActBiddingPrices = actBiddingService
            .getActBiddingByIds(actBidding.getId());
        List<ActBiddingPrice> actBiddingPrices = resultActBiddingPrices.getResult();
        int actBiddingPricesSize = actBiddingPrices.size();
        if (actBiddingPrices != null && actBiddingPricesSize > 0) {
            actBidding.setLowestPrice(actBiddingPrices.get(actBiddingPricesSize - 1).getPrice());
        } else {
            actBidding.setLowestPrice(actBidding.getPrice());
        }
    }

    /**
     * 获取阶梯竞价的价格
     * @param actBidding
     * @param actBiddingPrices
     * @return
     */
    private BigDecimal getPriceNow(ActBidding actBidding, List<ActBiddingPrice> actBiddingPrices) {
        BigDecimal priceNow = null;
        if (actBiddingPrices == null || actBiddingPrices.size() == 0) {
            return actBidding.getPrice();
        }
        int number = actBidding.getVirtualSaleNum().intValue() + actBidding.getSaleNum().intValue();
        if (actBiddingPrices.get(0).getSaleNum().intValue() > number) {
            return actBidding.getPrice();
        }
        int count = actBiddingPrices.size();
        if (actBiddingPrices.get(count - 1).getSaleNum() <= number) {
            return actBiddingPrices.get(count - 1).getPrice();
        }

        for (ActBiddingPrice actBiddingPrice : actBiddingPrices) {
            if (actBiddingPrice.getSaleNum().intValue() <= number) {
                priceNow = actBiddingPrice.getPrice();
            }
        }
        return priceNow;
    }
}
