package com.ejavashop.dao.shop.read.product;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.shopm.pcindex.PcIndexFloorProduct;

@Repository
public interface ProductSortReadDao {
	
		Integer getPcIndexFloorsCount(@Param("queryMap") Map<String, String> queryMap);
		
		List<PcIndexFloorProduct> getPcIndexFloors(@Param("queryMap") Map<String, String> queryMap,
				@Param("start")Integer start, @Param("size")Integer size);
		
		String getName(@Param("id")Integer id);

		//void delete(@Param("id")Integer id);

}
