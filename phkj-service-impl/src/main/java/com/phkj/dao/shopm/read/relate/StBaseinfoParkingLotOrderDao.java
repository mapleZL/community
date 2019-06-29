package com.phkj.dao.shopm.read.relate;

import com.phkj.entity.relate.StBaseinfoParkingLotOrder;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface StBaseinfoParkingLotOrderDao {
    int deleteByPrimaryKey(Long id);

    int insert(StBaseinfoParkingLotOrder record);

    int insertSelective(StBaseinfoParkingLotOrder record);

    StBaseinfoParkingLotOrder selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StBaseinfoParkingLotOrder record);

    int updateByPrimaryKey(StBaseinfoParkingLotOrder record);

    List<StBaseinfoParkingLotOrder> getAll(@Param("sts") String sts, @Param("type") String type,
                                           @Param("villageCode") String villageCode);


    List<Map<String, Object>> systemParkingLot(@Param("orgCode") String villageCode,
                                               @Param("userId") String userId);
}