package com.phkj.dao.shop.read.environmental;

import com.phkj.entity.environmental.StAppletEnvironment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StAppletEnvironmentReadMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StAppletEnvironment record);

    int insertSelective(StAppletEnvironment record);

    StAppletEnvironment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StAppletEnvironment record);

    int updateByPrimaryKey(StAppletEnvironment record);

    List<StAppletEnvironment> selectMeEnevronList(@Param("userId") String userId);

    List<StAppletEnvironment> selectSystemAllEnviron(@Param("status") String status, @Param("sts") String sts,
                                                     @Param("type") String type, @Param("villageCode") String villageCode);

}