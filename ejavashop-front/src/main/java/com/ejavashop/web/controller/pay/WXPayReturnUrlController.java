package com.ejavashop.web.controller.pay;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ejavashop.core.EjavashopConfig;
import com.ejavashop.core.HttpJsonResult;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.order.Orders;
import com.ejavashop.service.order.IOrdersService;
import com.ejavashop.web.controller.BaseController;

@Controller
@RequestMapping(value = "/wx")
public class WXPayReturnUrlController extends BaseController {

    @Resource
    private IOrdersService ordersService;

    @RequestMapping(value = "/returnUrl.html")
    public @ResponseBody HttpJsonResult<Boolean> returnUrl(HttpServletResponse response,
                                                           HttpServletRequest request,
                                                           String orderNo) {
        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
        String[] orderNos = orderNo.split(EjavashopConfig.ORDER_SEPARATOR);

        //                                                ServiceResult<Boolean> orderPayResult = ordersService.orderPayAfter(
        //                                                    out_trade_no, total_fee, "pcalipay", "支付宝支付", trade_no, params.toString());
        if (orderNos.length < 3) {
//            System.out.println("-------1---------");
            jsonResult.setData(false);
            return jsonResult;
        }

        String ordersId = orderNos[2];
        ServiceResult<Orders> orderResult = ordersService.getOrdersById(Integer.valueOf(ordersId));
        if (!orderResult.getSuccess()) {
//            System.out.println("-------2---------");
            jsonResult.setData(false);
            return jsonResult;
        }

        Orders orders = orderResult.getResult();
        if (null == orders) {
//            System.out.println("-------3---------");
            jsonResult.setData(false);
            return jsonResult;
        }
        if (orders.getPaymentStatus().intValue() == Orders.PAYMENT_STATUS_1) {
//            System.out.println("-------4---------");
            jsonResult.setData(true);
            return jsonResult;
        }

        jsonResult.setData(false);
        return jsonResult;
    }

    @RequestMapping(value = "/returnUrlSuccess.html")
    public String returnUrl() {
        return "front/order/linepay";
    }

}
