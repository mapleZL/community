package com.phkj.dao.shop.write.product;

import org.springframework.stereotype.Repository;

import com.phkj.entity.product.StAppletProduct;

@Repository
public interface StAppletProductWriteDao {
 
	Integer insert(StAppletProduct stAppletProduct);
	
	Integer update(StAppletProduct stAppletProduct);
}