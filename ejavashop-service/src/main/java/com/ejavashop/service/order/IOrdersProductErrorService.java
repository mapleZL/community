package com.ejavashop.service.order;

import java.util.List;

import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.order.OrdersProductError;

public interface IOrdersProductErrorService {

	/**
     * 根据id取得网单表
     * @param  ordersProductErrorId
     * @return
     */
    ServiceResult<OrdersProductError> getOrdersProductErrorById(Integer ordersProductErrorId);
    
    /**
     * 保存网单表
     * @param  ordersProductError
     * @return
     */
     ServiceResult<Integer> saveOrdersProductError(OrdersProductError ordersProductError);
     
     /**
     * 更新网单表
     * @param  ordersProductError
     * @return
     */
     ServiceResult<Integer> updateOrdersProductError(OrdersProductError ordersProductError);
     
     
     
     ServiceResult<List<OrdersProductError>>geterrorordersproduct(String ordersErroeSn);
}