package com.phkj.dao.shop.read.event;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.phkj.entity.event.StAppletHotEvents;

@Repository
public interface StAppletHotEventsReadDao {

    StAppletHotEvents get(java.lang.Integer id);

    Integer insert(StAppletHotEvents stAppletHotEvents);

    Integer update(StAppletHotEvents stAppletHotEvents);

    int count(@Param("param") Map<String, Object> dataMap);

    List<StAppletHotEvents> pageList(@Param("param") Map<String, Object> dataMap,
                                     @Param("start") Integer start, @Param("size") Integer size);

    List<StAppletHotEvents> wxPage(@Param("start") Integer start, @Param("size") Integer size,
                                   @Param("villageCode") String villageCode);

    Integer wxCount(@Param("villageCode") String villageCode);
}