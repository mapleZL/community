package com.ejavashop.dao.shop.read.product;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.product.OrderReceipt;
import com.ejavashop.entity.product.WmsClassify;

@Repository
public interface WmsClassifyReadDao {
 
	WmsClassify get(java.lang.Integer id);

    int getCount(Map<String, Object> param);

    List<WmsClassify> page(Map<String, Object> param);

    List<WmsClassify> getWmsCategoryList(@Param("state") Integer state);
	
}