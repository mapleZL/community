package com.ejavashop.dao.shop.read.product;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.product.ProductAttr;

@Repository
public interface ProductAttrReadDao {

    ProductAttr get(Integer id);

    List<ProductAttr> getByProductId(Integer productId);

    ProductAttr getWeight(@Param("productId") Integer productId, @Param("name") String name);

    List<ProductAttr> page(Map<String, String> queryMap);
    

    Integer count(@Param("param1") Map<String, String> queryMap);

    List<ProductAttr> page(@Param("param1") Map<String, String> queryMap,
                           @Param("start") Integer start, @Param("size") Integer size);

    ProductAttr getSize(@Param("productId")Integer productId,@Param("value")Integer value);

    ProductAttr getByProductIdAndName(@Param("productId") Integer id, @Param("name") String name);

    List<ProductAttr> getList(@Param("productId") Integer productId);
}
