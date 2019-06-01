package com.phkj.dao.shop.read.member;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.phkj.entity.member.MemberHouse;

@Repository
public interface MemberHouseReadDao {

    MemberHouse get(java.lang.Integer id);

    int getMemberHouseCount(@Param("queryMap") Map<String, String> queryMap);

    List<MemberHouse> getMemberHouseList(@Param("queryMap") Map<String, String> queryMap,
                                         @Param("start") Integer start,
                                         @Param("size") Integer size);

    List<MemberHouse> getAllHouse(@Param("memberId") String memberId, @Param("villageCode") String villageCode);
}