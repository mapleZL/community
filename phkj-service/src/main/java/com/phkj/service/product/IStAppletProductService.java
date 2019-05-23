package com.phkj.service.product;

import com.phkj.core.ServiceResult;
import com.phkj.entity.product.StAppletProduct;

public interface IStAppletProductService {

	/**
     * 根据id取得商品表
     * @param  stAppletProductId
     * @return
     */
    ServiceResult<StAppletProduct> getStAppletProductById(Integer stAppletProductId);
    
    /**
     * 保存商品表
     * @param  stAppletProduct
     * @return
     */
     ServiceResult<Integer> saveStAppletProduct(StAppletProduct stAppletProduct);
     
     /**
     * 更新商品表
     * @param  stAppletProduct
     * @return
     */
     ServiceResult<Integer> updateStAppletProduct(StAppletProduct stAppletProduct);
}