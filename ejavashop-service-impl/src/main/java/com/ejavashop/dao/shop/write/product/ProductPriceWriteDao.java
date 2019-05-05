package com.ejavashop.dao.shop.write.product;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.product.ProductPrice;

@Repository
public interface ProductPriceWriteDao {
 
	//ProductPrice get(java.lang.Integer id);
	
	Integer insert(ProductPrice productPrice);
	
	Integer update(ProductPrice productPrice);
}