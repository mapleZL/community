package com.ejavashop.web.controller.search;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ejavashop.service.product.IProductService;
import com.ejavashop.service.search.ISearchSettingService;
import com.ejavashop.web.controller.BaseController;

/**
 * 索引初始化
 *                       
 * @Filename: CacheIndexesController.java
 * @Version: 1.0
 * @Author: 陆帅
 * @Email: wpjava@163.com
 *
 */
@Controller
@RequestMapping(value = "admin/cache")
public class CacheIndexesController extends BaseController {

    @Resource
    private ISearchSettingService searchSettingService;

    @Resource
    private IProductService       productService;

    @RequestMapping(value="pcacheIndexes" ,method = { RequestMethod.GET })
    public String pcacheIndexes(Map<String, Object> dataMap){
        return "admin/search/pcacheIndex";
    }
    
    @RequestMapping(value="refreshProductAttrCache" ,method = { RequestMethod.GET })
    public String refreshProductAttrCache(Map<String, Object> dataMap){
        return "admin/search/refreshProductAttr";
    }
    
    @RequestMapping(value="mcacheIndexes" ,method = { RequestMethod.GET })
    public String mcacheIndexes(Map<String, Object> dataMap){
        return "admin/search/mcacheIndex";
    }
    
}
