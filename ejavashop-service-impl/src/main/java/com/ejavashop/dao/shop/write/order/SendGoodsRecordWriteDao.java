package com.ejavashop.dao.shop.write.order;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.order.SendGoodsRecord;

@Repository
public interface SendGoodsRecordWriteDao {
 
	//SendGoodsRecord get(java.lang.Integer id);
	
	Integer insert(SendGoodsRecord sendGoodsRecord);
	
	Integer update(SendGoodsRecord sendGoodsRecord);
	
	//List<SendGoodsRecord> getRecordByGoodsId(Integer bookingGoodsId);
}