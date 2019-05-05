package com.ejavashop.dao.shop.read.order;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.order.OrdersProductReturn;

@Repository
public interface OrdersProductReturnReadDao {
 
	OrdersProductReturn get(java.lang.Integer id);
	
}