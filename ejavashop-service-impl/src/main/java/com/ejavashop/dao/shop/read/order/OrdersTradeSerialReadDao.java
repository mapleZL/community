package com.ejavashop.dao.shop.read.order;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.order.OrdersTradeSerial;

@Repository
public interface OrdersTradeSerialReadDao {

    OrdersTradeSerial get(java.lang.String tradeSn);

    Integer getCount(Map<String, Object> queryMap);

    List<OrdersTradeSerial> page(Map<String, Object> queryMap);

    OrdersTradeSerial getByRelationOrderSn(String relationOrderSn);

    List<OrdersTradeSerial> existsByOrdersSn(String snnotime);

}