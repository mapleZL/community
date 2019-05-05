package com.ejavashop.dao.shop.read.order;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.order.SendGoodsRecord;

@Repository
public interface SendGoodsRecordReadDao {
 
	SendGoodsRecord get(java.lang.Integer id);
	
	List<SendGoodsRecord> getRecordByGoodsId(Integer bookingGoodsId);
}