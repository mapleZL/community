package com.phkj.dao.shop.read.order;


import com.phkj.entity.order.StAppletOrders;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface StAppletOrdersReadDao {

    StAppletOrders get(Integer id);

    List<StAppletOrders> getStAppletOrdersList(@Param("memberId") Integer memberId, @Param("start") int pageNum, @Param("size") int pageSize);

    int getOrdersCount(@Param("queryMap") Map<String, String> queryMap);

    List<StAppletOrders> getOrdersList(@Param("queryMap") Map<String, String> queryMap,  @Param("start") Integer start,  @Param("size") Integer size);
}