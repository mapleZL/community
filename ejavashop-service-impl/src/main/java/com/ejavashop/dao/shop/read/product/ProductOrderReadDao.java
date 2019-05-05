package com.ejavashop.dao.shop.read.product;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.product.ProductOrder;

@Repository
public interface ProductOrderReadDao {
 
	ProductOrder get(java.lang.Integer id);

	Integer[] selectProductId(String sortType);

	
//	Integer insert(ProductOrder productOrder);
//	
//	Integer update(ProductOrder productOrder);
}