package com.ejavashop.web.controller.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ejavashop.core.ConvertUtil;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.product.Product;
import com.ejavashop.entity.product.ProductCate;
import com.ejavashop.service.product.IProductCateService;
import com.ejavashop.service.product.IProductFrontService;
import com.ejavashop.vo.product.ProductListCate2;
import com.ejavashop.web.controller.BaseController;

/**
 * 二级分类列表页
 *                       
 * @Filename: ProductCateListController.java
 * @Version: 1.0
 * @Author: 王朋
 * @Email: wpjava@163.com
 *
 */
@Controller
public class ProductCateListController extends BaseController {

    @Resource
    private IProductFrontService productFrontService;

    @Resource
    private IProductCateService  productCateService;

    @RequestMapping(value = "/list/{cateId}.html", method = RequestMethod.GET)
    public String cate(HttpServletRequest request, HttpServletResponse response,
                       @PathVariable String cateId, Map<String, Object> stack) {
        ServiceResult<ProductCate> prodCatesResult = productCateService
            .getProductCateById(ConvertUtil.toInt(cateId, 0));
        if (!prodCatesResult.getSuccess()) {
            log.error("[ProductListController]:根据分类ID获取分类时Called Service ProductCateListController.cate()");
            return "redirect:/error.html";
        }
        ProductCate prodCate = prodCatesResult.getResult();
        if (prodCate == null) {
            log.error("[ProductListController]:根据分类ID获取分类时Called Service ProductCateListController.cate()");
            return "redirect:/error.html";
        }

        ServiceResult<ProductCate> prodCatesResultPid = productCateService
            .getProductCateById(prodCate.getPid());

        ServiceResult<List<ProductCate>> prodCatesResultSon = productCateService.getByPid(prodCate
            .getId());
        List<ProductCate> productCates = prodCatesResultSon.getResult();

        int count = 5;
        int number = productCates.size();
        if (number < 5) {
            count = number;
        }

        List<ProductListCate2> productListCate2s = new ArrayList<ProductListCate2>();
        ProductListCate2 productListCate2;
        for (int i = 0; i < count; i++) {
            ProductCate productCate = productCates.get(i);
            productListCate2 = new ProductListCate2();
            ServiceResult<List<Product>> serviceResult = productFrontService
                .getProductListByCateId2(productCate.getId());
            productListCate2.setProducts(serviceResult.getResult());
            productListCate2.setProductCate(productCate);
            productListCate2s.add(productListCate2);
        }

        stack.put("productCatePid", prodCatesResultPid.getResult());
        stack.put("productCate", prodCate);
        stack.put("productCates", productCates);
        stack.put("productListCate2s", productListCate2s);

        return "front/product/productcatelist";
    }

}
