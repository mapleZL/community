package com.ejavashop.service.order;

import java.util.List;
import java.util.Map;

import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.order.OrdersTradeSerial;

public interface IOrdersTradeSerialService {

    /**
     * 根据id取得订单交易序列
     * @param  ordersTradeSerialId
     * @return
     */
    ServiceResult<OrdersTradeSerial> getOrdersTradeSerialById(String ordersTradeSerialId);

    /**
     * 以关联订单号获取该订单交易号
     * @param relationOrderSn
     * @return
     */
    ServiceResult<OrdersTradeSerial> getByRelationOrderSn(String relationOrderSn);

    /**
     * 保存订单交易序列
     * @param  ordersTradeSerial
     * @return
     */
    ServiceResult<Integer> saveOrdersTradeSerial(OrdersTradeSerial ordersTradeSerial);

    /**
    * 更新订单交易序列
    * @param  ordersTradeSerial
    * @return
    */
    ServiceResult<Integer> updateOrdersTradeSerial(OrdersTradeSerial ordersTradeSerial);

    /**
    * 分页查询
    * @param queryMap
    * @param pager
    * @return
    */
    ServiceResult<List<OrdersTradeSerial>> page(Map<String, String> queryMap, PagerInfo pager);

    /**
     * 删除
     * @param id
     * @return
     */
    ServiceResult<Boolean> del(Integer id);

    /**
     * 指定订单集合是否存在交易号记录
     * @param orderPaySn
     * @return
     */
    ServiceResult<OrdersTradeSerial> existsByOrders(String orderPaySn);
}