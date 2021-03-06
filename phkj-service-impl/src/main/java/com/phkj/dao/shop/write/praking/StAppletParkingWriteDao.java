package com.phkj.dao.shop.write.praking;

import com.phkj.entity.praking.StAppletParking;
import org.springframework.stereotype.Repository;

@Repository
public interface StAppletParkingWriteDao {
    int deleteByPrimaryKey(Long id);

    int insert(StAppletParking record);

    int insertSelective(StAppletParking record);

    StAppletParking selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StAppletParking record);

    int updateByPrimaryKey(StAppletParking record);
}