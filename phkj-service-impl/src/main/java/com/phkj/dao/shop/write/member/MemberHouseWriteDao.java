package com.phkj.dao.shop.write.member;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.phkj.entity.member.MemberHouse;

@Repository
public interface MemberHouseWriteDao {

	
	Integer insert(MemberHouse memberHouse);
	
	Integer update(MemberHouse memberHouse);

    Boolean changeState(@Param("id")Integer id, @Param("status") int status);
}