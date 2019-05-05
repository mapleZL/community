package com.ejavashop.dao.shop.read.order;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.order.TerraceSelfCoupon;

@Repository
public interface TerraceSelfCouponReadDao {
 
	TerraceSelfCoupon get(Integer id);
	
	TerraceSelfCoupon getTerraceSelfCouponByTradeNo(String tradeNo);
}