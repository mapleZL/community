package com.ejavashop.dao.shop.read.wmsinterface;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.wmsinterface.BankCard;

@Repository
public interface BankCardReadDao {
 
	BankCard get(java.lang.Integer id);
	
	BankCard getBankCodeByCardBin(@Param("cardBin")String cardBin);
}