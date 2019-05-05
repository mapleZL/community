package com.ejavashop.dao.shop.write.order;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.order.OrdersProductError;

@Repository
public interface OrdersProductErrorWriteDao {
 
	//OrdersProductError get(java.lang.Integer id);
	
	Integer insert(OrdersProductError ordersProductError);
	
	Integer update(OrdersProductError ordersProductError);
	
	/*List<OrdersProductError> geterrorordersproduct(
			String ordersErroeSn);
*/}