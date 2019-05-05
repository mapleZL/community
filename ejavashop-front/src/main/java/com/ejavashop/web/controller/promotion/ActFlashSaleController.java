package com.ejavashop.web.controller.promotion;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
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
 * 整点秒杀
 *                       
 * @Filename: ActFlashSaleController.java
 * @Version: 1.0
 * @Author: 王朋
 * @Email: wpjava@163.com
 *
 */
@Controller
public class ActFlashSaleController extends BaseController {

    @Resource
    private IActFlashSaleService        actFlashSaleService;

    @Resource
    private IActFlashSaleProductService actFlashSaleProductService;

    @Resource
    private IProductService             productService;

    @RequestMapping(value = "/promotion/act-flash-sale.html", method = RequestMethod.GET)
    public String sale(HttpServletRequest request, HttpServletResponse response,
                       Map<String, Object> stack) {
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

        List<ActFlashSaleStage> stageListOver = new ArrayList<ActFlashSaleStage>();//已经结束
        ActFlashSaleStage actFlashSaleStageNow = null;//正在开始
        List<ActFlashSaleStage> stageList = actFlashSale.getStageList();
        for (ActFlashSaleStage actFlashSaleStage : stageList) {
            ServiceResult<List<ActFlashSaleProduct>> servletActFlashSaleProduct = actFlashSaleProductService
                .getActFlashSaleProductsByStageId(actFlashSaleStage.getId());
            List<ActFlashSaleProduct> actFlashSaleProducts = servletActFlashSaleProduct.getResult();
            for (ActFlashSaleProduct actFlashSaleProduct : actFlashSaleProducts) {
                Product product = productService.getProductById(actFlashSaleProduct.getProductId())
                    .getResult();
                actFlashSaleProduct.setProduct(product);
            }
            actFlashSaleStage.setProductList(actFlashSaleProducts);
        }

        for (Iterator iterator = stageList.iterator(); iterator.hasNext();) {
            ActFlashSaleStage actFlashSaleStage = (ActFlashSaleStage) iterator.next();
            int startTime = actFlashSaleStage.getStartTime();
            int endTime = actFlashSaleStage.getEndTime();
            if (hour >= startTime && hour < endTime) {
                actFlashSaleStageNow = actFlashSaleStage;
                iterator.remove();
                break;
            }
            if (hour >= endTime) {
                stageListOver.add(actFlashSaleStage);
                iterator.remove();
            }
        }

        if (actFlashSaleStageNow != null) {
            //计算倒计时
            int endTime = actFlashSaleStageNow.getEndTime();
            int hour1 = endTime - hour - 1;
            int minute1 = 60 - minute;
            int second1 = 60 - second;
            int countTime = hour1 * 3600 + minute1 * 60 + second1;
            stack.put("countTime", countTime);
        }

        stack.put("actFlashSaleStageNow", actFlashSaleStageNow);
        stack.put("stageList", stageList);
        stack.put("stageListOver", stageListOver);
        stack.put("actFlashSale", actFlashSale);

        return "front/promotion/actflashsale";
    }
}
