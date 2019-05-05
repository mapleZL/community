package com.ejavashop.dao.shop.read.order;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.order.OrdersProductError;

@Repository
public interface OrdersProductErrorReadDao {
 
	OrdersProductError get(java.lang.Integer id);
	
	//Integer insert(OrdersProductError ordersProductError);
	
	//Integer update(OrdersProductError ordersProductError);
	
	List<OrdersProductError> geterrorordersproduct(
			String ordersErroeSn);
}