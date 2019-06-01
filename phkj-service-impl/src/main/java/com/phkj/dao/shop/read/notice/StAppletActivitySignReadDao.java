package com.phkj.dao.shop.read.notice;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.phkj.entity.notice.StAppletActivitySign;

@Repository
public interface StAppletActivitySignReadDao {

    StAppletActivitySign get(java.lang.Integer id);

    List<StAppletActivitySign> list(@Param("queryMap") Map<String, String> queryMap,
                                    @Param("start") Integer start, @Param("size") Integer size);

    Integer getActivitySignCount(@Param("queryMap") Map<String, String> queryMap);

    StAppletActivitySign getActivityByUser(@Param("memberId") Integer memberId,
                                           @Param("rActivityId") Integer rActivityId);

    List<StAppletActivitySign> getParticipateList(@Param("memberId") Integer memberId,
                                                  @Param("start") Integer start,
                                                  @Param("pageSize") Integer size,
                                                  @Param("villageCode") String villageCode);

    Integer getCount(@Param("memberId") Integer memberId, @Param("villageCode") String villageCode);
}