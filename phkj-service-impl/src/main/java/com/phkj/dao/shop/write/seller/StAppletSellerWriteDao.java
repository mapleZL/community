package com.phkj.dao.shop.write.seller;

import org.springframework.stereotype.Repository;

import com.phkj.entity.seller.StAppletSeller;

@Repository
public interface StAppletSellerWriteDao {
 
	StAppletSeller get(java.lang.Integer id);
	
	Integer insert(StAppletSeller stAppletSeller);
	
	Integer update(StAppletSeller stAppletSeller);
}