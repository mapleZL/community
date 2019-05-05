package com.ejavashop.dao.promotion.read.coupon;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.promotion.coupon.CouponOptLog;

@Repository
public interface CouponOptLogReadDao {

    CouponOptLog get(java.lang.Integer id);

}