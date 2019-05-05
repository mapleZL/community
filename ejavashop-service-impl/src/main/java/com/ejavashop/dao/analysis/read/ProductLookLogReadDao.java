package com.ejavashop.dao.analysis.read;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.analysis.ProductLookLog;

@Repository
public interface ProductLookLogReadDao {

    ProductLookLog get(java.lang.Integer id);

    /**
     * 根据条件获取用户商品浏览信息
     * @param queryMap
     * @param start
     * @param size
     * @return
     */
    List<ProductLookLog> getProductLookLogs(@Param("queryMap") Map<String, String> queryMap,
                                            @Param("start") Integer start,
                                            @Param("size") Integer size);

    /**
     * 根据条件获取用户商品浏览数量
     * @param queryMap
     * @return
     */
    Integer getProductLookLogsCount(@Param("queryMap") Map<String, String> queryMap);

    List<ProductLookLog> getProductPV(@Param("queryMap") Map<String, Object> queryMap);

    /**
     * 统计当天有没有记录商品ID
     * @param siteCookie
     * @param productId
     * @return
     */
    int countBySiteCookieAndProductId(@Param("siteCookie") String siteCookie,
                                      @Param("productId") Integer productId);

    /**
     * 根据cookie统计用户还没有登录的情况下所录入记录
     * @param siteCookie
     * @return
     */
    int countBySiteCookie(String siteCookie);
}