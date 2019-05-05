package com.ejavashop.dao.shop.write.member;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.member.MemberHouse;

@Repository
public interface MemberHouseWriteDao {

	
	Integer insert(MemberHouse memberHouse);
	
	Integer update(MemberHouse memberHouse);
}