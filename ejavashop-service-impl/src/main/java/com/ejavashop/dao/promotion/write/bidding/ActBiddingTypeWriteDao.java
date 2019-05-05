package com.ejavashop.dao.promotion.write.bidding;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.promotion.bidding.ActBiddingType;

@Repository
public interface ActBiddingTypeWriteDao {

    Integer insert(ActBiddingType actBiddingType);

    Integer update(ActBiddingType actBiddingType);

    Integer audit(@Param("id") Integer id, @Param("state") Integer state);

    Integer del(Integer id);
}