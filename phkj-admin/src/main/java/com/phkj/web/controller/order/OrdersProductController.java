package com.phkj.web.controller.order;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.phkj.core.ResponseStateEnum;
import com.phkj.core.ServiceResult;
import com.phkj.core.response.ResponseUtil;
import com.phkj.entity.order.StAppletOrdersProduct;
import com.phkj.entity.order.StAppletOrdersVO;
import com.phkj.service.order.IStAppletOrdersProductService;

@Controller
@RequestMapping("/admin/order/ordersProduct")
public class OrdersProductController {

    @Autowired
    private IStAppletOrdersProductService ordersProductService;
    
    @RequestMapping(value = "/getOrdersProduct", method = {RequestMethod.GET})
    @ResponseBody
    public ResponseUtil detail(String orderSn) {
        if (StringUtils.isBlank(orderSn)) {
            return ResponseUtil.createResp(ResponseStateEnum.PARAM_EMPTY.getCode(), "orderSn is blank", false, null);
        }
        ServiceResult<List<StAppletOrdersProduct>> result = ordersProductService.getProductList(orderSn);
        return ResponseUtil.createResp(result.getCode(), result.getMessage(), result.getSuccess(), result.getResult());
    }
}
