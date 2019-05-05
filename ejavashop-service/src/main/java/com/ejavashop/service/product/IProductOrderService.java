package com.ejavashop.service.product;

import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.product.ProductOrder;

public interface IProductOrderService {

	/**
     * 根据id取得楼层排序表
     * @param  productOrderId
     * @return
     */
    ServiceResult<ProductOrder> getProductOrderById(Integer productOrderId);
    
    /**
     * 保存楼层排序表
     * @param  productOrder
     * @return
     */
     ServiceResult<Integer> saveProductOrder(ProductOrder productOrder);
     
     /**
     * 更新楼层排序表
     * @param  productOrder
     * @return
     */
     ServiceResult<Integer> updateProductOrder(ProductOrder productOrder);
}