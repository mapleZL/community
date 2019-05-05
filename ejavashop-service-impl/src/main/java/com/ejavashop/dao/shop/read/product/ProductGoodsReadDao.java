package com.ejavashop.dao.shop.read.product;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.product.ProductGoods;

@Repository
public interface ProductGoodsReadDao {

    List<ProductGoods> getByProductId(@Param("productId") Integer productId);
    

    ProductGoods get(java.lang.Integer id);

    Integer queryCount(Map<String, Object> map);

    List<ProductGoods> queryList(Map<String, Object> map);

    /**
     * 根据条件获取货品，只返回一条或零条货品
     * @param map
     * @return
     */
    ProductGoods getByCondition(Map<String, String> map);

    ProductGoods getProductGoodsBySKU(@Param("sku") String sku);

    ProductGoods getByProductIdAndAttrId(@Param("productId") Integer productId,
            @Param("attrId") String attrId);

    Integer count(@Param("param1") Map<String, String> queryMap);

    List<ProductGoods> page(@Param("param1") Map<String, String> queryMap,
                            @Param("start") Integer start, @Param("size") Integer size);

    Integer quickSearchCount(@Param("param1") Map<String, String> queryMap);

    List<ProductGoods> quickSearch(@Param("param1") Map<String, String> queryMap,
                            @Param("start") Integer start, @Param("size") Integer size);

    Integer checkProductBySKUAndSeller(@Param("param1") Map<String, String> queryMap);

    List<ProductGoods> getBySkuAndMember(@Param("sku")String sku,@Param("memberId")String memberId);
    
    Integer getMaxAttrIdByProductId(@Param("productId")Integer productId);

    List<ProductGoods> checkProductBySKUAndProductId(@Param("param1") Map<String, String> queryMap);

	Integer checkBarCodeIsExsit(@Param("param1") Map<String, String> queryMap);
    

}
