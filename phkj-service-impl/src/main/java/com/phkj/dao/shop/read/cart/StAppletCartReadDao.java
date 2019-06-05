package com.phkj.dao.shop.read.cart;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.phkj.entity.cart.StAppletCart;

@Repository
public interface StAppletCartReadDao {

    StAppletCart get(java.lang.Integer id);

    List<StAppletCart> list(@Param("memberId") Integer memberId,
                            @Param("villageCode") String villageCode, @Param("start") Integer start,
                            @Param("pageSize") Integer pageSize);

    Integer count(@Param("memberId") Integer memberId, @Param("villageCode") String villageCode);

    StAppletCart getCountByProduct(@Param("memberId") Integer memberId,
                                   @Param("productId") Integer productId,
                                   @Param("villageCode") String villageCode);

}