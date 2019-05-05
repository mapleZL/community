package com.ejavashop.dao.shop.write.wmsinterface;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.wmsinterface.BankCard;

@Repository
public interface BankCardWriteDao {
 
	//BankCard get(java.lang.Integer id);
	
	Integer insert(BankCard bankCard);
	
	Integer update(BankCard bankCard);
	
//	BankCard getBankCodeByCardBin(@Param("cardBin")String cardBin);
}