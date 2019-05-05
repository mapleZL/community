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
import com.ejavashop.dto.OrderDayDto;
import com.ejavashop.dto.OrderFinanceDayDto;
import com.ejavashop.entity.seller.Seller;
import com.ejavashop.service.order.IOrdersService;
import com.ejavashop.service.seller.ISellerService;
import com.ejavashop.web.controller.BaseController;

@Controller
@RequestMapping(value = "admin/report")
public class AdminReportOrderFinanceController extends BaseController {

    @Resource
    private IOrdersService ordersService;
    @Resource
    private ISellerService sellerService;

    @RequestMapping(value = "orderfinanceday", method = { RequestMethod.GET })
    public String index(Map<String, Object> dataMap) throws Exception {
        ServiceResult<List<Seller>> sellers = sellerService.getSellers(
            new HashMap<String, String>(), null);
        dataMap.put("sellers", sellers.getResult());
        return "admin/report/reportorderfinanceday";
    }
/*
    @RequestMapping(value = "orderday/list", method = { RequestMethod.GET })
    @ResponseBody
    public HttpJsonResult<List<OrderDayDto>> list(HttpServletRequest request,
                                                  Map<String, Object> dataMap) {
        HttpJsonResult<List<OrderDayDto>> jsonResult = new HttpJsonResult<List<OrderDayDto>>();
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        if (StringUtil.isEmpty(queryMap.get("q_startTime"), true)
            && StringUtil.isEmpty(queryMap.get("q_endTime"), true)) {
            String day = TimeUtil.getToday();
            queryMap.put("q_startTime", day + " 00:00:00");
            queryMap.put("q_endTime", day + " 23:59:59");
        }

        ServiceResult<List<OrderDayDto>> serviceResult = ordersService.getOrderDayDto(queryMap);
        if (serviceResult.getSuccess() && null != serviceResult.getResult()) {
            List<OrderDayDto> orderDayDtos = serviceResult.getResult();

            BigDecimal moneyProduct = new BigDecimal(0);
            BigDecimal moneyLogistics = new BigDecimal(0);
            BigDecimal moneyOrder = new BigDecimal(0);
            BigDecimal moneyPaidBalance = new BigDecimal(0);
            BigDecimal moneyPaidReality = new BigDecimal(0);
            BigDecimal moneyBack = new BigDecimal(0);
            BigDecimal moneyDiscount = new BigDecimal(0);
            BigDecimal servicePrice = new BigDecimal(0);
            BigDecimal labelPrice = new BigDecimal(0);
            Integer count = 0;

            for (OrderDayDto orderDayDto : orderDayDtos) {
                moneyProduct = moneyProduct.add(orderDayDto.getMoneyProduct());
                moneyLogistics = moneyLogistics.add(orderDayDto.getMoneyLogistics());
                moneyOrder = moneyOrder.add(orderDayDto.getMoneyOrder());
                moneyPaidBalance = moneyPaidBalance.add(orderDayDto.getMoneyPaidBalance());
                moneyPaidReality = moneyPaidReality.add(orderDayDto.getMoneyPaidReality());
                moneyBack = moneyBack.add(orderDayDto.getMoneyBack());

                moneyDiscount = moneyDiscount
                    .add(orderDayDto.getMoneyDiscount() == null ? BigDecimal.ZERO : orderDayDto
                        .getMoneyDiscount());
                servicePrice = servicePrice
                    .add(orderDayDto.getServicePrice() == null ? BigDecimal.ZERO : orderDayDto
                        .getServicePrice());
                labelPrice = labelPrice.add(orderDayDto.getLabelPrice() == null ? BigDecimal.ZERO
                    : orderDayDto.getLabelPrice());

                count += orderDayDto.getCount();
            }

            List<OrderDayDto> listFooter = new ArrayList<OrderDayDto>();
            OrderDayDto orderDayDtoFooter = new OrderDayDto();
            orderDayDtoFooter.setOrderDay("统计：");
            orderDayDtoFooter.setMoneyProduct(moneyProduct);
            orderDayDtoFooter.setMoneyLogistics(moneyLogistics);
            orderDayDtoFooter.setMoneyOrder(moneyOrder);
            orderDayDtoFooter.setMoneyPaidBalance(moneyPaidBalance);
            orderDayDtoFooter.setMoneyPaidReality(moneyPaidReality);

            orderDayDtoFooter.setMoneyBack(moneyBack);
            orderDayDtoFooter.setMoneyDiscount(moneyDiscount);
            orderDayDtoFooter.setServicePrice(servicePrice);
            orderDayDtoFooter.setLabelPrice(labelPrice);

            orderDayDtoFooter.setCount(count);

            listFooter.add(orderDayDtoFooter);

            jsonResult.setRows(orderDayDtos);
            jsonResult.setFooter(listFooter);
        }

        return jsonResult;
    }
    */

    
    @RequestMapping(value = "orderfinanceday/list", method = { RequestMethod.GET })
    @ResponseBody
    public HttpJsonResult<List<OrderFinanceDayDto>> list(HttpServletRequest request,
                                                  Map<String, Object> dataMap) {
    	 HttpJsonResult<List<OrderFinanceDayDto>> jsonResult = new HttpJsonResult<List<OrderFinanceDayDto>>();
         Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
         if (StringUtil.isEmpty(queryMap.get("q_startTime"), true)
             && StringUtil.isEmpty(queryMap.get("q_endTime"), true)) {
             String day = TimeUtil.getToday();
             queryMap.put("q_startTime", day + " 00:00:00");
             queryMap.put("q_endTime", day + " 23:59:59");
         }

        ServiceResult<List<OrderFinanceDayDto>> serviceResult = ordersService.getOrderFinanceDayDto(queryMap);
        if (serviceResult.getSuccess() && null != serviceResult.getResult()) {
            List<OrderFinanceDayDto> orderFinanceDayDtos = serviceResult.getResult();

            BigDecimal moneyPaid = new BigDecimal(0);


            for (OrderFinanceDayDto orderFinanceDayDto : orderFinanceDayDtos) {
        	  moneyPaid = moneyPaid.add(orderFinanceDayDto.getMoneyPaid());
            }

            List<OrderFinanceDayDto> listFooter = new ArrayList<OrderFinanceDayDto>();
            OrderFinanceDayDto orderFinanceDayDtoFooter = new OrderFinanceDayDto();
            orderFinanceDayDtoFooter.setOrderDay("统计：");
            orderFinanceDayDtoFooter.setMoneyPaid(moneyPaid);

            listFooter.add(orderFinanceDayDtoFooter);

            jsonResult.setRows(orderFinanceDayDtos);
            jsonResult.setFooter(listFooter);
        }

        return jsonResult;
    }
    
    
}
