package com.ejavashop.dao.shop.write.order;

import java.math.BigDecimal;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.order.OrdersReturn;

@Repository
public interface OrdersReturnWriteDao {
 
	//OrdersReturn get(java.lang.Integer id);
	
	Integer insert(OrdersReturn ordersReturn);
	
	Integer update(OrdersReturn ordersReturn);
	
	/*BigDecimal getParterTuijianSales(@Param("startTime")String startTime,@Param("endTime")String endTime,@Param("areaId")String areaId,@Param("type") String type
			,@Param("listStartTime")String listStartTime,@Param("listEndTime")String listEndTime);*/
}