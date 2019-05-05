package com.ejavashop.service.order;

import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.order.TerraceSelfCoupon;

public interface ITerraceSelfCouponService {

	/**
     * 根据id取得terrace_self_coupon对象
     * @param  terraceSelfCouponId
     * @return
     */
    ServiceResult<TerraceSelfCoupon> getTerraceSelfCouponById(Integer terraceSelfCouponId);
    
    /**
     * 保存terrace_self_coupon对象
     * @param  terraceSelfCoupon
     * @return
     */
     ServiceResult<Integer> saveTerraceSelfCoupon(TerraceSelfCoupon terraceSelfCoupon);
     
     /**
     * 更新terrace_self_coupon对象
     * @param  terraceSelfCoupon
     * @return
     */
     ServiceResult<Integer> updateTerraceSelfCoupon(TerraceSelfCoupon terraceSelfCoupon);

     
     ServiceResult<TerraceSelfCoupon> getTerraceSelfCouponByTradeNo(String tradeNo);
}