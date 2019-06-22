package com.phkj.web.controller.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.phkj.core.HttpJsonResult;
import com.phkj.core.ServiceResult;
import com.phkj.entity.order.StAppletOrdersProduct;
import com.phkj.service.order.IStAppletOrdersProductService;

@Controller
@RequestMapping("/admin/order/ordersProduct")
public class OrdersProductController {

    @Autowired
    private IStAppletOrdersProductService ordersProductService;
    
    @RequestMapping(value = "/getOrdersProduct", method = {RequestMethod.GET})
    @ResponseBody
    public HttpJsonResult<List<StAppletOrdersProduct>> detail(String orderSn) {
        HttpJsonResult<List<StAppletOrdersProduct>> httpJsonResult = new HttpJsonResult<>();
        ServiceResult<List<StAppletOrdersProduct>> result = ordersProductService.getProductList(orderSn);
        httpJsonResult.setRows(result.getResult());
        httpJsonResult.setTotal(result.getResult().size());
        return httpJsonResult;
    }
}
