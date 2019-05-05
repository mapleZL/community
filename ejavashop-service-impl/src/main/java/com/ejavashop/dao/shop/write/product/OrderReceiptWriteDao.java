package com.ejavashop.dao.shop.write.product;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.product.OrderReceipt;
import com.ejavashop.entity.product.OrderReceiptDetail;

@Repository
public interface OrderReceiptWriteDao {
 
	OrderReceipt get(java.lang.Integer id);
	
	Integer insert(OrderReceipt orderReceipt);
	
	Integer update(OrderReceipt orderReceipt);
	
	Integer getCount(Map<String, Object> queryMap);

    List<OrderReceipt> page(Map<String, Object> queryMap);

    List<OrderReceiptDetail> getReceiptProductDetailByOrdersId(String ordersId);

    Integer auditSendGoods(Map<String, Object> queryMap);
    
    OrderReceipt getByOrdersId(@Param("ordersId")String ordersId);
}