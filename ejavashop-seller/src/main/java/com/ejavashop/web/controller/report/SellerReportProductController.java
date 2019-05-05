package com.ejavashop.web.controller.report;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ejavashop.core.HttpJsonResult;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.StringUtil;
import com.ejavashop.core.TimeUtil;
import com.ejavashop.core.WebUtil;
import com.ejavashop.dto.ProductDayDto;
import com.ejavashop.entity.seller.Seller;
import com.ejavashop.entity.seller.SellerUser;
import com.ejavashop.service.order.IOrdersProductService;
import com.ejavashop.service.seller.ISellerService;
import com.ejavashop.web.controller.BaseController;
import com.ejavashop.web.util.WebSellerSession;

@Controller
@RequestMapping(value = "seller/report")
public class SellerReportProductController extends BaseController {

    @Resource
    private IOrdersProductService ordersProductService;
    @Resource
    private ISellerService        sellerService;

    @RequestMapping(value = "productday", method = { RequestMethod.GET })
    public String index(Map<String, Object> dataMap) throws Exception {
        ServiceResult<List<Seller>> sellers = sellerService
            .getSellers(new HashMap<String, String>(), null);
        dataMap.put("sellers", sellers.getResult());
        return "seller/report/reportproductday";
    }

    @RequestMapping(value = "productday/list", method = { RequestMethod.GET })
    @ResponseBody
    public HttpJsonResult<List<ProductDayDto>> productDayList(HttpServletRequest request,
                                                              Map<String, Object> dataMap) {
        HttpJsonResult<List<ProductDayDto>> jsonResult = new HttpJsonResult<List<ProductDayDto>>();
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        if (StringUtil.isEmpty(queryMap.get("q_startTime"), true)
            && StringUtil.isEmpty(queryMap.get("q_endTime"), true)) {
            String day = TimeUtil.getToday();
            queryMap.put("q_startTime", day + " 00:00:00");
            queryMap.put("q_endTime", day + " 23:59:59");
        }
        SellerUser sellerUser = WebSellerSession.getSellerUser(request);
        queryMap.put("q_sellerId", sellerUser.getSellerId() + "");

        ServiceResult<List<ProductDayDto>> serviceResult = ordersProductService
            .getProductDayDto(queryMap);
        if (serviceResult.getSuccess() && null != serviceResult.getResult()) {
            List<ProductDayDto> productDayDtos = serviceResult.getResult();

            BigDecimal moneyAmount = new BigDecimal(0);
            Integer number = 0;

            for (ProductDayDto productDayDto : productDayDtos) {
                moneyAmount = moneyAmount.add(productDayDto.getMoneyAmount());
                number += productDayDto.getNumber();
            }

            List<ProductDayDto> listFooter = new ArrayList<ProductDayDto>();
            ProductDayDto productDayDtoFooter = new ProductDayDto();
            productDayDtoFooter.setOrderDay("统计：");
            productDayDtoFooter.setMoneyAmount(moneyAmount);
            productDayDtoFooter.setNumber(number);

            listFooter.add(productDayDtoFooter);

            jsonResult.setRows(productDayDtos);
            jsonResult.setFooter(listFooter);
        }

        return jsonResult;
    }
}
