package com.phkj.dao.shopm.read.relate;

import org.springframework.stereotype.Repository;

import com.phkj.entity.relate.StBaseinfoParkingLot;

@Repository
public interface StBaseinfoParkingLotDao {
 
	StBaseinfoParkingLot get(java.lang.Long id);
	
	Integer insert(StBaseinfoParkingLot stBaseinfoParkingLot);
	
	Integer update(StBaseinfoParkingLot stBaseinfoParkingLot);
}