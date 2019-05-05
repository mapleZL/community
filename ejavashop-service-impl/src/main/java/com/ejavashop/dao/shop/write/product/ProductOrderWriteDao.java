package com.ejavashop.dao.shop.write.product;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.product.ProductOrder;

@Repository
public interface ProductOrderWriteDao {
 
	//ProductOrder get(java.lang.Integer id);
	
	Integer insert(ProductOrder productOrder);
	
	Integer update(ProductOrder productOrder);

	void deleteById(@Param("epId") int epId, @Param("sortType") String sortType);
}