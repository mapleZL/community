package com.phkj.web.controller.order;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.phkj.service.order.IOrdersService;
import com.phkj.service.seller.ISellerService;
import com.phkj.web.controller.BaseController;

/**
 * 
 *                       
 * @Filename: AdminOrdersController.java
 * @Version: 1.0
 * @date: 2019年5月13日
 * @Author: 陆帅 * @Email: lu1632278229@sina.cn
 *
 */
@Controller
@RequestMapping(value = "admin/order/orders")
public class AdminOrdersController extends BaseController {
    @Resource(name = "ordersService")
    private IOrdersService orderService;
    Logger                 log = Logger.getLogger(this.getClass());
    
    @Resource(name = "sellerService")
    ISellerService sellerService;
}
