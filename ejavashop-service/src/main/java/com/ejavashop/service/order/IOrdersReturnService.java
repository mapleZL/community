package com.ejavashop.service.order;

import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.order.OrdersReturn;

public interface IOrdersReturnService {

	/**
     * 根据id取得orders_return对象
     * @param  ordersReturnId
     * @return
     */
    ServiceResult<OrdersReturn> getOrdersReturnById(Integer ordersReturnId);
    
    /**
     * 保存orders_return对象
     * @param  ordersReturn
     * @return
     */
     ServiceResult<Integer> saveOrdersReturn(OrdersReturn ordersReturn);
     
     /**
     * 更新orders_return对象
     * @param  ordersReturn
     * @return
     */
     ServiceResult<Integer> updateOrdersReturn(OrdersReturn ordersReturn);
}