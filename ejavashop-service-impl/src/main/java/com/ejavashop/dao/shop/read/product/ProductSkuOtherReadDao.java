package com.ejavashop.dao.shop.read.product;

import com.ejavashop.entity.product.ProductSkuOther;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface ProductSkuOtherReadDao {
 
	ProductSkuOther get(java.lang.Long id);
	
	//Integer insert(ProductSkuOther productSkuOther);
	
	//Integer update(ProductSkuOther productSkuOther);
    
    //Integer delete(Integer productId);
    
    List<ProductSkuOther> queryProductSkuOtherByProductId(int productId);
}