package com.ejavashop.dao.shop.write.product;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.product.OrderReceiptDetail;

@Repository
public interface OrderReceiptDetailWriteDao {
 
	OrderReceiptDetail get(java.lang.Integer id);
	
	Integer insert(OrderReceiptDetail orderReceiptDetail);
	
	Integer update(OrderReceiptDetail orderReceiptDetail);
}