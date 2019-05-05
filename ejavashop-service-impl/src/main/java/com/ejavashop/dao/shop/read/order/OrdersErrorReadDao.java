package com.ejavashop.dao.shop.read.order;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.order.OrdersError;

@Repository
public interface OrdersErrorReadDao {
 
	OrdersError get(java.lang.Integer id);
	
	List<OrdersError>getOrdersErrorByOrdersSn(@Param("ordersSn")String ordersSn,
			@Param("queryMap")Map<String, String> queryMap);
}