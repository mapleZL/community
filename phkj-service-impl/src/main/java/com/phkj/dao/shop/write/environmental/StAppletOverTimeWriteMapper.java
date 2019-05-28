package com.phkj.dao.shop.write.environmental;

import com.phkj.entity.environmental.StAppletOverTime;
import org.springframework.stereotype.Repository;

@Repository
public interface StAppletOverTimeWriteMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StAppletOverTime record);

    int insertSelective(StAppletOverTime record);

    StAppletOverTime selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StAppletOverTime record);

    int updateByPrimaryKey(StAppletOverTime record);

    int updateTimeById(String id, String time);

}