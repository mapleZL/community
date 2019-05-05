package com.ejavashop.dao.shop.write.product;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.product.WmsClassify;

@Repository
public interface WmsClassifyWriteDao {
 
	Integer insert(WmsClassify wmsClassify);
	
	Integer update(WmsClassify wmsClassify);
}