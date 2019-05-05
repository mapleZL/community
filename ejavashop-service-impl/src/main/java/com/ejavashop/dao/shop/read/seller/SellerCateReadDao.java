package com.ejavashop.dao.shop.read.seller;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.seller.SellerCate;

@Repository
public interface SellerCateReadDao {

    SellerCate get(java.lang.Integer id);

    Integer queryCount(Map<String, Object> map);

    List<SellerCate> queryList(Map<String, Object> map);

    List<SellerCate> getByPid(@Param("pid") Integer pid, @Param("sellerId") Integer sellerId);
    
    Integer count(@Param("param1") Map<String, String> queryMap);
    
    List<SellerCate> page(@Param("param1") Map<String, String> queryMap,
            @Param("start") Integer start, @Param("size") Integer size);
    /**
     * 统计分类下面有没有子分类
     * @param sellerCateId
     * @return
     */
    int countByPid(Integer sellerCateId);


}
