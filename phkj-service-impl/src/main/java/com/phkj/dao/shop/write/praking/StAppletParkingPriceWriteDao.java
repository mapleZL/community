package com.phkj.dao.shop.write.praking;

import com.phkj.entity.praking.StAppletParkingPrice;
import org.springframework.stereotype.Repository;

@Repository
public interface StAppletParkingPriceWriteDao {
    int deleteByPrimaryKey(Long id);

    int insert(StAppletParkingPrice record);

    int insertSelective(StAppletParkingPrice record);

    StAppletParkingPrice selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StAppletParkingPrice record);

    int updateByPrimaryKey(StAppletParkingPrice record);
}