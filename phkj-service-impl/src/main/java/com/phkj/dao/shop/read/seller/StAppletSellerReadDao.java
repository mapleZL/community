package com.phkj.dao.shop.read.seller;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.phkj.entity.seller.StAppletSeller;

@Repository
public interface StAppletSellerReadDao {

    StAppletSeller get(java.lang.Integer id);

    Integer insert(StAppletSeller stAppletSeller);

    Integer update(StAppletSeller stAppletSeller);

    StAppletSeller getSellerByMemberId(@Param("memberId") Integer memebrId);
}