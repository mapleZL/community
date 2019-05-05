package com.ejavashop.dao.promotion.read.flash;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.promotion.flash.ActFlashSaleLog;

@Repository
public interface ActFlashSaleLogReadDao {

    ActFlashSaleLog get(java.lang.Integer id);

}