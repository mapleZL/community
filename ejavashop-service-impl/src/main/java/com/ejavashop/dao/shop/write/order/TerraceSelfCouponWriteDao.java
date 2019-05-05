package com.ejavashop.dao.shop.write.order;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.order.TerraceSelfCoupon;

@Repository
public interface TerraceSelfCouponWriteDao {
 
	TerraceSelfCoupon get(Integer terraceSelfCouponId);
	
	Integer insert(TerraceSelfCoupon terraceSelfCoupon);
	
	Integer update(TerraceSelfCoupon terraceSelfCoupon);
}