package com.ejavashop.web.controller.promotion;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.ConvertUtil;
import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.PaginationUtil;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.WebUtil;
import com.ejavashop.entity.product.Product;
import com.ejavashop.entity.promotion.bidding.ActBidding;
import com.ejavashop.entity.promotion.bidding.ActBiddingBanner;
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

    private final static int         PAGE_NUMBER = 20;

    @RequestMapping(value = "/bidding-sale.html", method = RequestMethod.GET)
    public String bidding(HttpServletRequest request, HttpServletResponse response,
                          Map<String, Object> dataMap) {
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap, PAGE_NUMBER);
        String typeStr = request.getParameter("type");
        int type = ConvertUtil.toInt(typeStr, 0);

        Map<String, String> queryMap = new HashMap<String, String>();
        queryMap.put("biddingfront", "1"); //1,在售 查询是 activity_state=2，state=3 start_time< now <end_time
        queryMap.put("type", type + "");
        queryMap.put("channel", ConstantsEJS.PC_MOBILE_PC + "");

        ServiceResult<List<ActBidding>> serviceResultBidding = actBiddingService
            .getActBiddingsFront(queryMap, pager);
        List<ActBidding> actBiddings = serviceResultBidding.getResult();
        for (ActBidding actBidding : actBiddings) {
            ServiceResult<Product> resultProduct = productService.getProductById(actBidding
                .getProductId());
            actBidding.setProductName(resultProduct.getResult().getName1());
        }

        ServiceResult<List<ActBiddingBanner>> serviceResult = actBiddingBannerService
            .getActBiddingBannersPcMobile(ConstantsEJS.PC_MOBILE_PC);
        List<ActBiddingBanner> actBiddingBanners = serviceResult.getResult();

        ServiceResult<List<ActBiddingType>> serviceResultType = actBiddingService
            .getActBiddingTypesFront();
        List<ActBiddingType> actBiddingTypes = serviceResultType.getResult();

        String url = request.getRequestURI() + "";
        if (type != 0) {
            url = url + "?type=" + type;
        }

        //分页对象
        PaginationUtil pm = new PaginationUtil(pager.getPageSize(), String.valueOf(pager
            .getPageIndex()), pager.getRowsCount(), url);

        dataMap.put("actBiddings", actBiddings);
        dataMap.put("actBiddingBanners", actBiddingBanners);
        dataMap.put("actBiddingTypes", actBiddingTypes);
        dataMap.put("page", pm);
        dataMap.put("type", type);

        return "front/promotion/actbiddingsalelist";
    }

    @RequestMapping(value = "/bidding-start.html", method = RequestMethod.GET)
    public String biddingStart(HttpServletRequest request, HttpServletResponse response,
                               Map<String, Object> dataMap) {
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap, PAGE_NUMBER);
        String typeStr = request.getParameter("type");
        int type = ConvertUtil.toInt(typeStr, 0);

        Map<String, String> queryMap = new HashMap<String, String>();
        queryMap.put("biddingfront", "3"); //3、即将开始 查询是 activity_state=2，state=3  now < start_time
        queryMap.put("type", type + "");
        queryMap.put("channel", ConstantsEJS.PC_MOBILE_PC + "");

        ServiceResult<List<ActBidding>> serviceResultBidding = actBiddingService
            .getActBiddingsFront(queryMap, pager);
        List<ActBidding> actBiddings = serviceResultBidding.getResult();
        for (ActBidding actBidding : actBiddings) {
            ServiceResult<Product> resultProduct = productService.getProductById(actBidding
                .getProductId());
            actBidding.setProductName(resultProduct.getResult().getName1());
        }

        ServiceResult<List<ActBiddingBanner>> serviceResult = actBiddingBannerService
            .getActBiddingBannersPcMobile(ConstantsEJS.PC_MOBILE_PC);
        List<ActBiddingBanner> actBiddingBanners = serviceResult.getResult();

        ServiceResult<List<ActBiddingType>> serviceResultType = actBiddingService
            .getActBiddingTypesFront();
        List<ActBiddingType> actBiddingTypes = serviceResultType.getResult();

        String url = request.getRequestURI() + "";
        if (type != 0) {
            url = url + "?type=" + type;
        }

        //分页对象
        PaginationUtil pm = new PaginationUtil(pager.getPageSize(), String.valueOf(pager
            .getPageIndex()), pager.getRowsCount(), url);

        dataMap.put("actBiddings", actBiddings);
        dataMap.put("actBiddingBanners", actBiddingBanners);
        dataMap.put("actBiddingTypes", actBiddingTypes);
        dataMap.put("page", pm);
        dataMap.put("type", type);

        return "front/promotion/actbiddingstartlist";
    }

    @RequestMapping(value = "/bidding-end.html", method = RequestMethod.GET)
    public String biddingEnd(HttpServletRequest request, HttpServletResponse response,
                             Map<String, Object> dataMap) {
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap, PAGE_NUMBER);
        String typeStr = request.getParameter("type");
        int type = ConvertUtil.toInt(typeStr, 0);

        Map<String, String> queryMap = new HashMap<String, String>();
        queryMap.put("biddingfront", "2"); //2、已经结束 查询是 activity_state=3，state=3
        queryMap.put("type", type + "");
        queryMap.put("channel", ConstantsEJS.PC_MOBILE_PC + "");

        ServiceResult<List<ActBidding>> serviceResultBidding = actBiddingService
            .getActBiddingsFront(queryMap, pager);
        List<ActBidding> actBiddings = serviceResultBidding.getResult();
        for (ActBidding actBidding : actBiddings) {
            ServiceResult<Product> resultProduct = productService.getProductById(actBidding
                .getProductId());
            actBidding.setProductName(resultProduct.getResult().getName1());
        }

        ServiceResult<List<ActBiddingBanner>> serviceResult = actBiddingBannerService
            .getActBiddingBannersPcMobile(ConstantsEJS.PC_MOBILE_PC);
        List<ActBiddingBanner> actBiddingBanners = serviceResult.getResult();

        ServiceResult<List<ActBiddingType>> serviceResultType = actBiddingService
            .getActBiddingTypesFront();
        List<ActBiddingType> actBiddingTypes = serviceResultType.getResult();

        String url = request.getRequestURI() + "";
        if (type != 0) {
            url = url + "?type=" + type;
        }

        //分页对象
        PaginationUtil pm = new PaginationUtil(pager.getPageSize(), String.valueOf(pager
            .getPageIndex()), pager.getRowsCount(), url);

        dataMap.put("actBiddings", actBiddings);
        dataMap.put("actBiddingBanners", actBiddingBanners);
        dataMap.put("actBiddingTypes", actBiddingTypes);
        dataMap.put("page", pm);
        dataMap.put("type", type);

        return "front/promotion/actbiddingendlist";
    }
}
