package com.ejavashop.service.product;

import java.util.List;

import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.product.ProductSkuOther;

public interface IProductSkuOtherService {

	/**
     * 根据id取得product_sku_other对象
     * @param  productSkuOtherId
     * @return
     */
    ServiceResult<ProductSkuOther> getProductSkuOtherById(long productSkuOtherId);

    /**
     * 根据productId取得product_sku_other
     * @param  productSkuOtherId
     * @return
     */
    ServiceResult<List<ProductSkuOther>> queryProductSkuOtherByProductId(int productSkuOtherId);
    
    /**
     * 保存product_sku_other对象
     * @param  productSkuOther
     * @return
     */
     ServiceResult<Integer> saveProductSkuOther(ProductSkuOther productSkuOther);
     
     /**
     * 更新product_sku_other对象
     * @param  productSkuOther
     * @return
     */
     ServiceResult<Integer> updateProductSkuOther(ProductSkuOther productSkuOther);
}