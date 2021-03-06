package com.phkj.dao.shopm.read.relate;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.phkj.entity.relate.StBaseinfoParkingLot;

@Repository
public interface StBaseinfoParkingLotDao {

    StBaseinfoParkingLot get(java.lang.Long id);

    Integer insert(StBaseinfoParkingLot stBaseinfoParkingLot);

    Integer update(StBaseinfoParkingLot stBaseinfoParkingLot);

    List<StBaseinfoParkingLot> getRelatedParkingLot(@Param("residentinfoId") Long residentinfoId);

    Map<String, String> selectLockByNum(@Param("carNum") String position, @Param("villageCode") String villageCode);

    List<StBaseinfoParkingLot> getSurplusParkingLot(@Param("orgCode") String orgCode);

    List<Map<String, Object>> getNearbyParkingLot(@Param("orgCode") String orgCode);

    int getNearbyParkingLotNum(@Param("orgCode") String orgCode);

    List<StBaseinfoParkingLot> getSurplusParkingLotAndMeParking(@Param("orgCode") String orgCode, @Param("userId") String userId);

    Map<String, String> selectParkingLotById(@Param("id") String id);
}