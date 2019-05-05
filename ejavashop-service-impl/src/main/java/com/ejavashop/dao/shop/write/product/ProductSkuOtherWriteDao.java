package com.ejavashop.dao.shop.write.product;

import com.ejavashop.entity.product.ProductSkuOther;

import org.springframework.stereotype.Repository;

@Repository
public interface ProductSkuOtherWriteDao {
 
	//ProductSkuOther get(java.lang.Long id);
	
	Integer insert(ProductSkuOther productSkuOther);
	
	Integer update(ProductSkuOther productSkuOther);
    
    Integer delete(Integer productId);
    
    //List<ProductSkuOther> queryProductSkuOtherByProductId(int productId);
}