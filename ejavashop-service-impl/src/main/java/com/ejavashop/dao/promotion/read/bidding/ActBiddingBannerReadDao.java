package com.ejavashop.dao.promotion.read.bidding;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.promotion.bidding.ActBiddingBanner;

@Repository
public interface ActBiddingBannerReadDao {
	

    ActBiddingBanner get(java.lang.Integer id);

    Integer getActBiddingBannersCount(@Param("queryMap") Map<String, String> queryMap);

    List<ActBiddingBanner> getActBiddingBanners(@Param("queryMap") Map<String, String> queryMap,
                                                @Param("start") Integer start,
                                                @Param("size") Integer size);

    /**
     * 查询团购首页正在使用的轮播图
     * @param pcMobile
     * @return
     */
    List<ActBiddingBanner> getActBiddingBannersPcMobile(@Param("pcMobile") int pcMobile);

}