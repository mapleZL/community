package com.phkj.dao.shopm.read.relate;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.phkj.entity.relate.StBaseinfoParkingLot;

@Repository
public interface StBaseinfoParkingLotDao {

    StBaseinfoParkingLot get(java.lang.Long id);

    Integer insert(StBaseinfoParkingLot stBaseinfoParkingLot);

    Integer update(StBaseinfoParkingLot stBaseinfoParkingLot);

    List<StBaseinfoParkingLot> getRelatedParkingLot(@Param("residentinfoId") Long residentinfoId);
}