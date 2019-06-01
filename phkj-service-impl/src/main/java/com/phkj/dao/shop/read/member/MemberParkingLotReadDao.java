package com.phkj.dao.shop.read.member;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.phkj.entity.member.MemberParkingLot;

@Repository
public interface MemberParkingLotReadDao {
 
	MemberParkingLot get(java.lang.Integer id);
	
	List<MemberParkingLot> getMemberParkingList(@Param("queryMap") Map<String, String> queryMap, @Param("start") Integer start, @Param("size") Integer size);

    int getMemberParkingCount(@Param("queryMap") Map<String, String> queryMap);

    List<MemberParkingLot> getMyMemberLotList(@Param("memberId") Integer memberId, @Param("villageCode") String villageId, @Param("start") int pageNum,
                                              @Param("size") int pageSize);
}