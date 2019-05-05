package com.ejavashop.dao.shop.write.product;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.product.ProductComments;

@Repository
public interface ProductCommentsWriteDao {

    //ProductComments get(Integer id);

    Integer insert(ProductComments productComments);

    Integer update(ProductComments productComments);

    /*Integer getProductCommentsCount(@Param("queryMap") Map<String, String> query);

    List<ProductComments> getProductComments(@Param("queryMap") Map<String, String> queryMap,
                                             @Param("start") Integer start,
                                             @Param("size") Integer size);*/

}
