package com.ejavashop.dao.shop.write.backgoods;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.backgoods.BadGoods;

@Repository
public interface BadGoodsWriteDao {
 
	//BadGoods get(java.lang.Integer id);
	
	Integer insert(BadGoods badGoods);
	
	Integer update(BadGoods badGoods);
	
	//Integer getCount(Map<String, Object> queryMap);

   // List<BadGoods> page(Map<String, Object> queryMap);
}