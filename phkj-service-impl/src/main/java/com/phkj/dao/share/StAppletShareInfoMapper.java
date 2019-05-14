package com.phkj.dao.share;

import com.phkj.entity.share.StAppletShareInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface StAppletShareInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StAppletShareInfo record);

    int insertSelective(StAppletShareInfo record);

    StAppletShareInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StAppletShareInfo record);

    int updateByPrimaryKeyWithBLOBs(StAppletShareInfo record);

    int updateByPrimaryKey(StAppletShareInfo record);

    List<Map> selectByUserId(@Param("userId")String id, @Param("taskType") String taskType);

    List<Map> selectAllShareInfo(@Param("taskType") String taskType);


    List<Map> selectByTaskType(@Param("taskType") String taskType, @Param("status") String status);

    List<Map> selectComShareInfoList(@Param("userId") String userId, @Param("taskType") String taskType,
                                     @Param("status") String status);

    StAppletShareInfo selectShareByInfo(@Param("infoId") Long infoId);
}