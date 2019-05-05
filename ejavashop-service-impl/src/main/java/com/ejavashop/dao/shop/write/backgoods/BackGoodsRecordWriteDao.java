package com.ejavashop.dao.shop.write.backgoods;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.backgoods.BackGoodsRecord;

@Repository
public interface BackGoodsRecordWriteDao {
 
	//BackGoodsRecord get(java.lang.Integer id);
	
	Integer insert(BackGoodsRecord backGoodsRecord);
	
	Integer update(BackGoodsRecord backGoodsRecord);
	
	//List<BackGoodsRecord>getBackGoodRecordByGoodsId(Integer goodsId);
}