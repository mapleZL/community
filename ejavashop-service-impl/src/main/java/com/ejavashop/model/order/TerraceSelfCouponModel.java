package com.ejavashop.model.order;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ejavashop.core.StringUtil;
import com.ejavashop.dao.shop.read.order.TerraceSelfCouponReadDao;
import com.ejavashop.dao.shop.write.order.TerraceSelfCouponWriteDao;
import com.ejavashop.entity.order.TerraceSelfCoupon;

@Component
public class TerraceSelfCouponModel {

	@Resource
    private TerraceSelfCouponWriteDao terraceSelfCouponWriteDao;
	@Resource
	private TerraceSelfCouponReadDao  terraceSelfCouponReadDao;
    
    /**
     * 根据id取得terrace_self_coupon对象
     * @param  terraceSelfCouponId
     * @return
     */
    public TerraceSelfCoupon getTerraceSelfCouponById(Integer terraceSelfCouponId) {
    	return terraceSelfCouponWriteDao.get(terraceSelfCouponId);
    }
    
    /**
     * 保存terrace_self_coupon对象
     * @param  terraceSelfCoupon
     * @return
     */
     public Integer saveTerraceSelfCoupon(TerraceSelfCoupon terraceSelfCoupon) {
     	this.dbConstrains(terraceSelfCoupon);
     	return terraceSelfCouponWriteDao.insert(terraceSelfCoupon);
     }
     
     /**
     * 更新terrace_self_coupon对象
     * @param  terraceSelfCoupon
     * @return
     */
     public Integer updateTerraceSelfCoupon(TerraceSelfCoupon terraceSelfCoupon) {
     	this.dbConstrains(terraceSelfCoupon);
     	return terraceSelfCouponWriteDao.update(terraceSelfCoupon);
     }
     
     private void dbConstrains(TerraceSelfCoupon terraceSelfCoupon) {
		terraceSelfCoupon.setTradeNo(StringUtil.dbSafeString(terraceSelfCoupon.getTradeNo(), true, 60));
		terraceSelfCoupon.setCouponSn(StringUtil.dbSafeString(terraceSelfCoupon.getCouponSn(), true, 60));
     }

    public TerraceSelfCoupon getTerraceSelfCouponByTradeNo(String tradeNo) {
        return terraceSelfCouponReadDao.getTerraceSelfCouponByTradeNo(tradeNo);
    }
}