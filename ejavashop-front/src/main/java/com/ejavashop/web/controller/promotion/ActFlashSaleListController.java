package com.ejavashop.web.controller.promotion;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.TimeUtil;
import com.ejavashop.entity.product.Product;
import com.ejavashop.entity.promotion.flash.ActFlashSale;
import com.ejavashop.entity.promotion.flash.ActFlashSaleProduct;
import com.ejavashop.entity.promotion.flash.ActFlashSaleStage;
import com.ejavashop.service.product.IProductService;
import com.ejavashop.service.promotion.IActFlashSaleProductService;
import com.ejavashop.service.promotion.IActFlashSaleService;
import com.ejavashop.web.controller.BaseController;

/**
 * 整点秒杀 列表页
 *                       
 * @Filename: ActFlashSaleListController.java
 * @Version: 1.0
 * @Author: 王朋
 * @Email: wpjava@163.com
 *
 */
@Controller
public class ActFlashSaleListController extends BaseController {

    @Resource
    private IActFlashSaleService        actFlashSaleService;

    @Resource
    private IActFlashSaleProductService actFlashSaleProductService;

    @Resource
    private IProductService             productService;

    @RequestMapping(value = "/promotion/act-flash-sale-{stageId}.html", method = RequestMethod.GET)
    public String saleList(HttpServletRequest request, HttpServletResponse response,
                           @PathVariable int stageId, Map<String, Object> stack) {
        String today = TimeUtil.getToday();
        ServiceResult<ActFlashSale> result = actFlashSaleService.getByActDate(TimeUtil
            .strToDate(today + " 00:00:00"));

        ActFlashSale actFlashSale = result.getResult();
        if (actFlashSale == null) {
            return "front/promotion/actflashsalelist";
        }
        int hour = TimeUtil.getHour();
        int minute = TimeUtil.getMinute();
        int second = TimeUtil.getSecond();

        ActFlashSaleStage actFlashSaleStageNow = null;
        //活动阶段
        List<ActFlashSaleStage> stageList = actFlashSale.getStageList();
        for (ActFlashSaleStage actFlashSaleStage : stageList) {
            if (actFlashSaleStage.getId().intValue() == stageId) {
                actFlashSaleStageNow = actFlashSaleStage;
                break;
            }
        }

        if (actFlashSaleStageNow == null) {
            return "redirect:/error.html";
        }

        int state = 1;//1表示未开始，2表示正在抢购，3已经结束

        int startTime = actFlashSaleStageNow.getStartTime();
        int endTime = actFlashSaleStageNow.getEndTime();
        if (hour >= startTime && hour < endTime) {
            state = 2;
        } else if (hour >= endTime) {
            state = 3;
        } else {
            state = 1;
        }

        ServiceResult<List<ActFlashSaleProduct>> servletActFlashSaleProduct = actFlashSaleProductService
            .getActFlashSaleProductsByStageId(actFlashSaleStageNow.getId());
        List<ActFlashSaleProduct> actFlashSaleProducts = servletActFlashSaleProduct.getResult();
        for (ActFlashSaleProduct actFlashSaleProduct : actFlashSaleProducts) {
            Product product = productService.getProductById(actFlashSaleProduct.getProductId())
                .getResult();
            actFlashSaleProduct.setProduct(product);
        }
        actFlashSaleStageNow.setProductList(actFlashSaleProducts);

        if (actFlashSaleStageNow != null) {
            //计算倒计时
            int hour1 = endTime - hour - 1;
            int minute1 = 60 - minute;
            int second1 = 60 - second;
            int countTime = hour1 * 3600 + minute1 * 60 + second1;
            stack.put("countTime", countTime);
        }

        stack.put("actFlashSaleStage", actFlashSaleStageNow);
        stack.put("actFlashSale", actFlashSale);
        stack.put("state", state);

        return "front/promotion/actflashsalelist";
    }

}
