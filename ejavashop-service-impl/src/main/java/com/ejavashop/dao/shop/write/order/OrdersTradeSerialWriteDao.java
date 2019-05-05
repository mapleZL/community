package com.ejavashop.dao.shop.write.order;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.order.OrdersTradeSerial;

@Repository
public interface OrdersTradeSerialWriteDao {

    //OrdersTradeSerial get(java.lang.String tradeSn);

    Integer save(OrdersTradeSerial ordersTradeSerial);

    Integer update(OrdersTradeSerial ordersTradeSerial);

    //Integer getCount(Map<String, Object> queryMap);

    //List<OrdersTradeSerial> page(Map<String, Object> queryMap);

    Integer del(Integer id);

    //OrdersTradeSerial getByRelationOrderSn(String relationOrderSn);

   // List<OrdersTradeSerial> existsByOrdersSn(String snnotime);

}