package com.ejavashop.dao.analysis.write;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.analysis.ProductLookLog;

@Repository
public interface ProductLookLogWriteDao {

//    ProductLookLog get(java.lang.Integer id);

    Integer insert(ProductLookLog productLookLog);

    Integer update(ProductLookLog productLookLog);

    /**
     * 根据cookie更新memberID
     * @param siteCookie
     * @param memberId
     */
    void updateByMemberId(@Param("siteCookie") String siteCookie, @Param("memberId") Integer memberId);

    Integer updateProductLookLogByProductId(ProductLookLog productLookLog);
}