package com.ejavashop.dao.shop.write.order;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.order.OrdersError;

@Repository
public interface OrdersErrorWriteDao {
 
	//OrdersError get(java.lang.Integer id);
	
	Integer insert(OrdersError ordersError);
	
	Integer update(OrdersError ordersError);
	
	/*List<OrdersError>getOrdersErrorByOrdersSn(@Param("ordersSn")String ordersSn,
			@Param("queryMap")Map<String, String> queryMap);*/
}