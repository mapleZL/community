package com.ejavashop.dao.shop.read.backgoods;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.backgoods.BadGoods;

@Repository
public interface BadGoodsReadDao {
 
	BadGoods get(java.lang.Integer id);
	
	//Integer insert(BadGoods badGoods);
	
	//Integer update(BadGoods badGoods);
	
	Integer getCount(Map<String, Object> queryMap);

    List<BadGoods> page(Map<String, Object> queryMap);
}