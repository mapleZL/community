package com.phkj.dao.shop.read.praking;

import com.phkj.entity.praking.StAppletParkingPrice;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StAppletParkingPriceReadDao {
    int deleteByPrimaryKey(Long id);

    int insert(StAppletParkingPrice record);

    int insertSelective(StAppletParkingPrice record);

    StAppletParkingPrice selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StAppletParkingPrice record);

    int updateByPrimaryKey(StAppletParkingPrice record);

    List<StAppletParkingPrice> selectAll(@Param("villageCode") String villageCode);

    List<StAppletParkingPrice> selectByType(@Param("id") String id, @Param("villageCode") String villageCode);
}