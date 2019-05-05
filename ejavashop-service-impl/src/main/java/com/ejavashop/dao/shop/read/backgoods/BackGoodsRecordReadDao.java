package com.ejavashop.dao.shop.read.backgoods;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.backgoods.BackGoodsRecord;

@Repository
public interface BackGoodsRecordReadDao {
 
	BackGoodsRecord get(java.lang.Integer id);
	
	List<BackGoodsRecord>getBackGoodRecordByGoodsId(Integer goodsId);
}