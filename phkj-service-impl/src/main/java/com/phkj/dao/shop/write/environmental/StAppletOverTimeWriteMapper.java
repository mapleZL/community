package com.phkj.dao.shop.write.environmental;

import com.phkj.entity.environmental.StAppletOverTime;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StAppletOverTimeWriteMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StAppletOverTime record);

    int insertSelective(StAppletOverTime record);

    StAppletOverTime selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StAppletOverTime record);

    int updateByPrimaryKey(StAppletOverTime record);

    int updateTimeById(@Param("id") String id,@Param("time") String time);

}