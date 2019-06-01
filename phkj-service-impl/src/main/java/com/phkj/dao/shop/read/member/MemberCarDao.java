package com.phkj.dao.shop.read.member;

import com.phkj.entity.member.MemberCar;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface MemberCarDao {

    MemberCar get(Integer id);

    Integer insert(MemberCar memberCar);

    Integer update(MemberCar memberCar);

    Integer getMemberCarCount(@Param("queryMap") Map<String, String> queryMap);

    List<MemberCar> getMemberCarList(@Param("queryMap") Map<String, String> queryMap, @Param("start") Integer start, @Param("size") Integer size);

    Boolean changeState(@Param("id") Integer id, @Param("status") int status);

    List<MemberCar> getMyMemberCarList(@Param("memberId") Integer memberId, @Param("villageCode") String villageId, @Param("start") int pageNum,
                                       @Param("size") int pageSize);
}