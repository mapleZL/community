package com.ejavashop.dao.shop.read.member;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.member.MemberParkingLot;

@Repository
public interface MemberParkingLotReadDao {
 
	MemberParkingLot get(java.lang.Integer id);
	
	List<MemberParkingLot> getMemberParkingList(@Param("queryMap") Map<String, String> queryMap, @Param("start") Integer start, @Param("size") Integer size);

    int getMemberParkingCount(@Param("queryMap") Map<String, String> queryMap);
	
}