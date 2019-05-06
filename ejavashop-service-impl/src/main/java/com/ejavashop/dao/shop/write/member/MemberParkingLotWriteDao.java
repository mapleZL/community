package com.ejavashop.dao.shop.write.member;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.member.MemberParkingLot;

@Repository
public interface MemberParkingLotWriteDao {
	
	Integer insert(MemberParkingLot memberParkingLot);
	
	Integer update(MemberParkingLot memberParkingLot);

    Boolean updateStatus(@Param("id")Integer id, @Param("status")int status);
}