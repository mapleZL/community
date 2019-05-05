package com.ejavashop.dao.shop.read.seller;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.seller.SellerTypeLogs;

@Repository
public interface SellerTypeLogsReadDao {

    SellerTypeLogs get(Integer id);

    List<SellerTypeLogs> getByCateId(Integer id);

    Integer count(@Param("param1") Map<String, String> queryMap);

    List<SellerTypeLogs> page(@Param("param1") Map<String, String> queryMap,
                              @Param("start") Integer start, @Param("size") Integer size);
}
