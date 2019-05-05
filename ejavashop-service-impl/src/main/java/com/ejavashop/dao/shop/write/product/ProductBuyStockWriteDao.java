package com.ejavashop.dao.shop.write.product;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.product.ProductBuyStock;

@Repository
public interface ProductBuyStockWriteDao {

    //ProductBuyStock get(java.lang.Integer id);

    Integer insert(ProductBuyStock productBuyStock);

    Integer update(ProductBuyStock productBuyStock);

    /*ProductBuyStock getBySku(String sku);

    ProductBuyStock getByGoodsId(Integer goodsId);

    Integer getCount(Map<String, Object> queryMap);

    List<ProductBuyStock> page(Map<String, Object> queryMap);*/
}