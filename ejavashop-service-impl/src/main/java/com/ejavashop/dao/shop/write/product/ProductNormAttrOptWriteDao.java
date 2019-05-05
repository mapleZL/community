package com.ejavashop.dao.shop.write.product;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.product.ProductNormAttrOpt;

@Repository
public interface ProductNormAttrOptWriteDao {

    //ProductNormAttrOpt get(java.lang.Integer id);

    Integer save(ProductNormAttrOpt productNormAttrOpt);

    Integer update(ProductNormAttrOpt productNormAttrOpt);

    /*Integer getCount(Map<String, Object> queryMap);

    List<ProductNormAttrOpt> page(Map<String, Object> queryMap);*/

    Integer del(Integer id);

}