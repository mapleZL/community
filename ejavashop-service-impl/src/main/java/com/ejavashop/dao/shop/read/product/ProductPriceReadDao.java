package com.ejavashop.dao.shop.read.product;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.product.ProductPrice;

@Repository
public interface ProductPriceReadDao {
 
	ProductPrice get(java.lang.Integer id);
	
}