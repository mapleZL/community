package com.ejavashop.service.product;

import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.product.ProductPrice;

public interface IProductPriceService {

	/**
     * 根据id取得product_price对象
     * @param  productPriceId
     * @return
     */
    ServiceResult<ProductPrice> getProductPriceById(Integer productPriceId);
    
    /**
     * 保存product_price对象
     * @param  productPrice
     * @return
     */
     ServiceResult<Integer> saveProductPrice(ProductPrice productPrice);
     
     /**
     * 更新product_price对象
     * @param  productPrice
     * @return
     */
     ServiceResult<Integer> updateProductPrice(ProductPrice productPrice);
}