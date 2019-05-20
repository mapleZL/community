package com.phkj.service.impl.product;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.phkj.model.product.ProductModel;
import com.phkj.service.product.IProductService;

@Service(value = "productService")
public class ProductServiceImpl implements IProductService {

    @Resource
    private ProductModel  productModel;

  

}