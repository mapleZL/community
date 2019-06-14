package com.phkj.dao.shop.read.praking;

import com.phkj.entity.praking.StAppletParking;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StAppletParkingReadDao {
    int deleteByPrimaryKey(Long id);

    int insert(StAppletParking record);

    int insertSelective(StAppletParking record);

    StAppletParking selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StAppletParking record);

    int updateByPrimaryKey(StAppletParking record);

    List<StAppletParking> getSystemAll(@Param("sts") String sts, @Param("villageCode") String villageCode);

    List<StAppletParking> getMeParking(@Param("villageCode") String villageCode, @Param("userId") String userId,
                                       @Param("sts") String sts);
}