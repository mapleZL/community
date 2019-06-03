package com.phkj.dao.shop.write.order;


import com.phkj.entity.order.StAppletOrdersProduct;
import org.springframework.stereotype.Repository;

@Repository
public interface StAppletOrdersProductWriteDao {
 
	Integer insert(StAppletOrdersProduct stAppletOrdersProduct);
	
	Integer update(StAppletOrdersProduct stAppletOrdersProduct);
}