package com.ejavashop.dao.shop.write.backgoods;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.backgoods.BackGoods;

@Repository
public interface BackGoodsWriteDao {
 
	//BackGoods get(java.lang.Integer id);
	
	Integer insert(BackGoods backGoods);
	
	Integer update(BackGoods backGoods);
	
//	Integer getCount(@Param("param1") Map<String, String> queryMap);
//
//    List<BackGoods> page(@Param("param1") Map<String, String> queryMap,
//                                 @Param("start") Integer start, @Param("size") Integer size);
}