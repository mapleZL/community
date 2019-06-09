package com.phkj.dao.shop.write.resvervation;

import com.phkj.entity.resvervation.StAppletReservation;
import org.springframework.stereotype.Repository;

@Repository
public interface StAppletReservationWriteMapper {
    int insert(StAppletReservation record);

    int insertSelective(StAppletReservation record);

    int updateByPrimaryKey(StAppletReservation reservation);
}