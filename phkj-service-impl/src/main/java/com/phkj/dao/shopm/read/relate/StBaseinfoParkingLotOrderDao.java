package com.phkj.dao.shopm.read.relate;

import com.phkj.entity.relate.StBaseinfoParkingLotOrder;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

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
}