package com.ejavashop.dao.shop.read.backgoods;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.backgoods.BackGoods;

@Repository
public interface BackGoodsReadDao {
 
	BackGoods get(java.lang.Integer id);
	
	Integer getCount(@Param("param1") Map<String, String> queryMap);

    List<BackGoods> page(@Param("param1") Map<String, String> queryMap,
                                 @Param("start") Integer start, @Param("size") Integer size);
	
}