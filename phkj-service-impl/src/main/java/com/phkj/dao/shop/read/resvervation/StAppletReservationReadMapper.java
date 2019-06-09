package com.phkj.dao.shop.read.resvervation;

import com.phkj.entity.resvervation.StAppletReservation;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StAppletReservationReadMapper {
    int insert(StAppletReservation record);

    int insertSelective(StAppletReservation record);

    List<StAppletReservation> getAllReservation(@Param("userId") String userId, @Param("villageCode")
            String villageCode, @Param("sts") String sts, @Param("status") String status,@Param("type") String type);

    StAppletReservation getReservation(@Param("id") Long id);

}