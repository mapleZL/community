package com.phkj.dao.shop.read.product;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.junit.runners.Parameterized.Parameters;
import org.springframework.stereotype.Repository;

import com.phkj.entity.product.StAppletProduct;

@Repository
public interface StAppletProductReadDao {

    StAppletProduct get(java.lang.Integer id);

    int checkProductBySPUAndSellerId(@Param("param") Map<String, String> queryMap);

    Integer count(@Param("param") Map<String, String> queryMap);

    List<StAppletProduct> pageProduct(@Param("param") Map<String, String> queryMap,
                                      @Param("start") Integer start, @Param("size") Integer size);

    List<StAppletProduct> list(@Param("start") Integer start, @Param("pageSize") Integer pageSize,
                               @Param("productCateId") Integer productCateId,
                               @Param("villageCode") String villageCode,
                               @Param("search") String search);

    Integer countWx(@Param("productCateId") Integer productCateId,
                    @Param("villageCode") String villageCode, @Param("search") String search);

}