package com.ejavashop.web.controller.product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ejavashop.core.ServiceResult;
import com.ejavashop.service.product.IProductCateService;
import com.ejavashop.service.product.IProductFrontService;
import com.ejavashop.vo.product.FrontProductCateVO;
import com.ejavashop.vo.product.ProductTypeVO;
import com.ejavashop.web.controller.BaseController;

/**
 * 
 *                       
 * @Filename: ProductCateController.java
 * @Version: 1.0
 * @Author: 王朋
 * @Email: wpjava@163.com
 *
 */
@Controller
public class ProductCateController extends BaseController {

    @Resource
    private IProductCateService productCateService;

    @Resource
    private IProductFrontService  productFrontService;
    
    /**
     * 导航所有商品分类 
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "/catelist.html", method = { RequestMethod.GET })
    public String getProductCateList(HttpServletRequest request, HttpServletResponse response,
                                     Map<String, Object> dataMap) {
        Map<String, Object> queryMap = new HashMap<String, Object>();
        ServiceResult<List<FrontProductCateVO>> serviceResult = new ServiceResult<List<FrontProductCateVO>>();
        serviceResult = productCateService.getProductCateList(queryMap);

        dataMap.put("cateList", serviceResult.getResult());

        return "h5v1/product/productcate";
    }

    /**
     * 导航所有商品分类  来自terry
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "/productTypeList.html", method = { RequestMethod.GET })
    public String getProductTypeList(HttpServletRequest request, HttpServletResponse response, Map<String, Object> dataMap) {

        ServiceResult<List<ProductTypeVO>> serviceResult = new ServiceResult<List<ProductTypeVO>>();
        serviceResult = productFrontService.getProductTypeList(1);      //Terry 4 is producttype id

        dataMap.put("typeList", serviceResult.getResult());

        return "h5v1/product/productcate";
    }

    /**
     * 所有促销列表
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "/campaign.html", method = { RequestMethod.GET })
    public String campaign(HttpServletRequest request, HttpServletResponse response, Map<String, Object> dataMap) {


        return "h5v1/product/campagin";
    }
}
