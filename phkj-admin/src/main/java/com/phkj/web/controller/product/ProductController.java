package com.phkj.web.controller.product;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.phkj.core.ConstantsEJS;
import com.phkj.service.product.IStAppletProductService;
import com.phkj.web.controller.BaseController;

/**
 * 
 *                       
 * @Filename: ProductController.java
 * @Version: 1.0
 * @date: 2019年5月20日
 * @Author: 陆帅 * @Email: lu1632278229@sina.cn
 *
 */
@Controller
@RequestMapping(value = "admin/product")
public class ProductController extends BaseController {
    
    @Autowired
    IStAppletProductService productService;
    
    @RequestMapping(value = "/add", method = { RequestMethod.GET })
    public String getList(Map<String, Object> dataMap) throws Exception {
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return "admin/product/pdt/productadd";
    }
    
    
}
