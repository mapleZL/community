package com.ejavashop.dao.shop.write.product;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductSortWriteDao {
	
		void delete(@Param("id")Integer id);

}
