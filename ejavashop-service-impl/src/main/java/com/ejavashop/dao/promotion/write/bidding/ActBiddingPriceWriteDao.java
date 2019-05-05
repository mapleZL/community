package com.ejavashop.dao.promotion.write.bidding;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.promotion.bidding.ActBiddingPrice;

@Repository
public interface ActBiddingPriceWriteDao {


    Integer insert(ActBiddingPrice actBiddingPrice);

    Integer update(ActBiddingPrice actBiddingPrice);

    Integer delActBiddingByIds(Integer id);

}