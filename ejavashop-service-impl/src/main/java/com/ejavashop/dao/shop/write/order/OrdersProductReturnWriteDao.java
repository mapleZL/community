package com.ejavashop.dao.shop.write.order;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.order.OrdersProductReturn;

@Repository
public interface OrdersProductReturnWriteDao {
 
	//OrdersProductReturn get(java.lang.Integer id);
	
	Integer insert(OrdersProductReturn ordersProductReturn);
	
	Integer update(OrdersProductReturn ordersProductReturn);
}