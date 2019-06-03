package com.phkj.service.order;


import com.phkj.core.ServiceResult;
import com.phkj.entity.order.StAppletOrdersProduct;

public interface IStAppletOrdersProductService {

	/**
     * 根据id取得网单表
     * @param  stAppletOrdersProductId
     * @return
     */
    ServiceResult<StAppletOrdersProduct> getStAppletOrdersProductById(Integer stAppletOrdersProductId);
    
    /**
     * 保存网单表
     * @param  stAppletOrdersProduct
     * @return
     */
     ServiceResult<Integer> saveStAppletOrdersProduct(StAppletOrdersProduct stAppletOrdersProduct);
     
     /**
     * 更新网单表
     * @param  stAppletOrdersProduct
     * @return
     */
     ServiceResult<Integer> updateStAppletOrdersProduct(StAppletOrdersProduct stAppletOrdersProduct);
}