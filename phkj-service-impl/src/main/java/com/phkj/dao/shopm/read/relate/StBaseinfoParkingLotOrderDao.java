package com.phkj.dao.shopm.read.relate;

import com.phkj.entity.relate.StBaseinfoParkingLotOrder;
import org.springframework.stereotype.Repository;

@Repository
public interface StBaseinfoParkingLotOrderDao {
    int deleteByPrimaryKey(Long id);

    int insert(StBaseinfoParkingLotOrder record);

    int insertSelective(StBaseinfoParkingLotOrder record);

    StBaseinfoParkingLotOrder selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StBaseinfoParkingLotOrder record);

    int updateByPrimaryKey(StBaseinfoParkingLotOrder record);
}