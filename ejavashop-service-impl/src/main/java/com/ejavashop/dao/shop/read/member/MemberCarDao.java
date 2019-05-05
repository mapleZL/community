package com.ejavashop.dao.shop.read.member;

import com.ejavashop.entity.member.MemberCar;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberCarDao {
 
	MemberCar get(Integer id);
	
	Integer insert(MemberCar memberCar);
	
	Integer update(MemberCar memberCar);
}