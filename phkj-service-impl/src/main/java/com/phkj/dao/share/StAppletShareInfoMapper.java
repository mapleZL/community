package com.phkj.dao.share;

import com.phkj.entity.share.StAppletShareInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StAppletShareInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StAppletShareInfo record);

    int insertSelective(StAppletShareInfo record);

    StAppletShareInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StAppletShareInfo record);

    int updateByPrimaryKeyWithBLOBs(StAppletShareInfo record);

    int updateByPrimaryKey(StAppletShareInfo record);

    List<StAppletShareInfo> selectByUserId(@Param("userId") String userId);
}