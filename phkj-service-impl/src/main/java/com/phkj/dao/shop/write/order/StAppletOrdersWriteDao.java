package com.phkj.dao.shop.write.order;


import com.phkj.entity.order.StAppletOrders;
import org.springframework.stereotype.Repository;

@Repository
public interface StAppletOrdersWriteDao {
 
	Integer insert(StAppletOrders stAppletOrders);
	
	Integer update(StAppletOrders stAppletOrders);
}