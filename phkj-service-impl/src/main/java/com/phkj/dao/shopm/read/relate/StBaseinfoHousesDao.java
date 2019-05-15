package com.phkj.dao.shopm.read.relate;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.phkj.entity.relate.StBaseinfoHouses;

@Repository
public interface StBaseinfoHousesDao {
 
	StBaseinfoHouses get(java.lang.Long id);
	
	Integer insert(StBaseinfoHouses stBaseinfoHouses);
	
	Integer update(StBaseinfoHouses stBaseinfoHouses);

    List<StBaseinfoHouses> getHouse();
}