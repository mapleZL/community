package com.ejavashop.service.order;

import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.order.OrdersProductReturn;

public interface IOrdersProductReturnService {

	/**
     * 根据id取得orders_product_return对象
     * @param  ordersProductReturnId
     * @return
     */
    ServiceResult<OrdersProductReturn> getOrdersProductReturnById(Integer ordersProductReturnId);
    
    /**
     * 保存orders_product_return对象
     * @param  ordersProductReturn
     * @return
     */
     ServiceResult<Integer> saveOrdersProductReturn(OrdersProductReturn ordersProductReturn);
     
     /**
     * 更新orders_product_return对象
     * @param  ordersProductReturn
     * @return
     */
     ServiceResult<Integer> updateOrdersProductReturn(OrdersProductReturn ordersProductReturn);
}