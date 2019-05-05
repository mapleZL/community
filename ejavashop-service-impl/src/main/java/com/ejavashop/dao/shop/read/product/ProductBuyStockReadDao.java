package com.ejavashop.dao.shop.read.product;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.product.ProductBuyStock;

@Repository
public interface ProductBuyStockReadDao {
 
	ProductBuyStock get(java.lang.Integer id);
	
	ProductBuyStock getBySku(String sku);

    ProductBuyStock getByGoodsId(Integer goodsId);

    Integer getCount(Map<String, Object> queryMap);

    List<ProductBuyStock> page(Map<String, Object> queryMap);
	
}