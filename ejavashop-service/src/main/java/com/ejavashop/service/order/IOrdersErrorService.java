package com.ejavashop.service.order;

import java.util.List;
import java.util.Map;

import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.order.OrdersError;

public interface IOrdersErrorService {

	/**
     * 根据id取得订单
     * @param  ordersErrorId
     * @return
     */
    ServiceResult<OrdersError> getOrdersErrorById(Integer ordersErrorId);
    
    /**
     * 保存订单
     * @param  ordersError
     * @return
     */
     ServiceResult<Integer> saveOrdersError(OrdersError ordersError);
     
     /**
     * 更新订单
     * @param  ordersError
     * @return
     */
     ServiceResult<Integer> updateOrdersError(OrdersError ordersError);
     
     
     ServiceResult<List<OrdersError>> getOrdersErrorByOrdersSn(String ordersSn,Map<String, String>queryMap);
}