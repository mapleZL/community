package com.ejavashop.dao.promotion.write.bidding;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.promotion.bidding.ActBiddingBanner;

@Repository
public interface ActBiddingBannerWriteDao {



    Integer insert(ActBiddingBanner actBiddingBanner);

    Integer update(ActBiddingBanner actBiddingBanner);

    Integer delete(Integer id);
}