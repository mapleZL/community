package com.ejavashop.web.controller.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.product.ProductTypeAttr;
import com.ejavashop.service.product.IProductTypeAttrService;
import com.ejavashop.web.controller.BaseController;


@Controller
@RequestMapping(value = "admin/productattr")
public class ProductAttrController extends BaseController {
    
    @Resource
    private IProductTypeAttrService        productTypeAttrService;
    
    @RequestMapping(value = "", method = { RequestMethod.GET })
    public String personalTailor(HttpServletRequest request, Map<String, Object> dataMap) {
        ServiceResult<List<ProductTypeAttr>> serviceResult = productTypeAttrService.getProductTypeAttrList();
        if(serviceResult.getSuccess()){
            //将自定义属性和检索属性区分
            List<ProductTypeAttr> productTypeAttr = serviceResult.getResult();
            //检索属性
            List<ProductTypeAttr> productTypeAttr1 = new ArrayList<>();
            //自定义属性
            List<ProductTypeAttr> productTypeAttr2 = new ArrayList<>();
            ProductTypeAttr attr = new ProductTypeAttr();
            for(int i=0 ; i<productTypeAttr.size();i++){
                attr = productTypeAttr.get(i);
                if(attr.getType() != null && attr.getType() == 1){
                    productTypeAttr1.add(attr);
                }else{
                    productTypeAttr2.add(attr);
                }
            }
            dataMap.put("productTypeAttr1", productTypeAttr1);
            dataMap.put("productTypeAttr2", productTypeAttr2);
        }
        return "admin/product/productattr/producttypeattr";
    }
    
    @RequestMapping(value = "edit", method = { RequestMethod.POST })
    public String edit(HttpServletRequest request, Map<String, Object> dataMap) throws Exception {
        ServiceResult<List<ProductTypeAttr>> serviceResult = productTypeAttrService.getProductTypeAttrList();
        List<ProductTypeAttr> productTypeAttr = serviceResult.getResult();
        List<ProductTypeAttr> productTypeAttr_new = new ArrayList<>();
        String changeAttr = "";
        for(ProductTypeAttr attr : productTypeAttr){
            changeAttr = request.getParameter("type_" + attr.getId())==null?"":request.getParameter("type_" + attr.getId());
            attr.setValue(changeAttr);
            productTypeAttr_new.add(attr);
        }
        boolean flag = true;
        flag = productTypeAttrService.updateProductTypeAttrList(productTypeAttr_new);
        if( !flag ){
            throw new Exception("保存失败！");
        }else{
            ServiceResult<List<ProductTypeAttr>> result = productTypeAttrService.getProductTypeAttrList();
            if(result.getSuccess()){
                //将自定义属性和检索属性区分
                productTypeAttr = result.getResult();
                //检索属性
                List<ProductTypeAttr> productTypeAttr1 = new ArrayList<>();
                //自定义属性
                List<ProductTypeAttr> productTypeAttr2 = new ArrayList<>();
                ProductTypeAttr attr = new ProductTypeAttr();
                for(int i=0 ; i<productTypeAttr.size();i++){
                    attr = productTypeAttr.get(i);
                    if(attr.getType() != null && attr.getType() == 1){
                        productTypeAttr1.add(attr);
                    }else{
                        productTypeAttr2.add(attr);
                    }
                }
                dataMap.put("productTypeAttr1", productTypeAttr1);
                dataMap.put("productTypeAttr2", productTypeAttr2);
            }
            return "admin/product/productattr/producttypeattr";
        }
    }

}
