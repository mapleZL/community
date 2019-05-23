package com.phkj.dao.shop.read.product;

import org.springframework.stereotype.Repository;

import com.phkj.entity.product.StAppletProduct;

@Repository
public interface StAppletProductReadDao {
 
	StAppletProduct get(java.lang.Integer id);
	
}