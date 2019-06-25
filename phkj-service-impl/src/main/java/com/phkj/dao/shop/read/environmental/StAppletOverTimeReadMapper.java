package com.phkj.dao.shop.read.environmental;

import com.phkj.entity.environmental.StAppletOverTime;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StAppletOverTimeReadMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StAppletOverTime record);

    int insertSelective(StAppletOverTime record);

    StAppletOverTime selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StAppletOverTime record);

    int updateByPrimaryKey(StAppletOverTime record);

    List<StAppletOverTime> selectAllOverTime(@Param("type") String type, @Param("villageCode") String villageCode);

    List<StAppletOverTime> getAllEnableTimeByType(@Param("id") String id);

    List<StAppletOverTime> getDataByType(@Param("type") String type,@Param("villageCode") String villageCode);
}