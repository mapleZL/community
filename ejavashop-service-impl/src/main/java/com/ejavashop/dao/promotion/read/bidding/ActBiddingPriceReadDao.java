package com.ejavashop.dao.promotion.read.bidding;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.promotion.bidding.ActBiddingPrice;

@Repository
public interface ActBiddingPriceReadDao {

    ActBiddingPrice get(java.lang.Integer id);

    List<ActBiddingPrice> getActBiddingByIds(Integer id);

}