package com.phkj.dao.shop.read.order;


import com.phkj.entity.order.StAppletOrders;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StAppletOrdersReadDao {
 
	StAppletOrders get(Integer id);

    List<StAppletOrders> getStAppletOrdersList(@Param("memberId") Integer memberId, @Param("start") int pageNum, @Param("size") int pageSize);
}