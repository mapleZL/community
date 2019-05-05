package com.ejavashop.dao.shop.read.member;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.member.MemberHouse;

@Repository
public interface MemberHouseReadDao {
 
	MemberHouse get(java.lang.Integer id);
	
	List<MemberHouse> getPageList(@Param("memberName") String memberName);
}