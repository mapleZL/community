package com.phkj.web.controller.seller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.phkj.service.seller.ISellerService;
import com.phkj.web.controller.BaseController;

/**
 * 
 *                       
 * @Filename: AdminSellerController.java
 * @Version: 1.0
 * @date: 2019年5月20日
 * @Author: 陆帅 * @Email: lu1632278229@sina.cn
 *
 */
@Controller
@RequestMapping(value = "admin/seller/manage")
public class AdminSellerController extends BaseController {

    @Resource(name = "sellerService")
    private ISellerService sellerService;
    
    
}
