package com.phkj.dao.shop.write.environmental;

import com.phkj.entity.environmental.StAppletEnvironment;
import org.springframework.stereotype.Repository;

@Repository
public interface StAppletEnvironmentWriteMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StAppletEnvironment record);

    int insertSelective(StAppletEnvironment record);

    StAppletEnvironment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StAppletEnvironment record);

    int updateByPrimaryKey(StAppletEnvironment record);
}