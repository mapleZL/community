package com.phkj.dao.shopm.read.relate;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.phkj.entity.relate.StBaseinfoBuilding;

@Repository
public interface StBaseinfoBuildingDao {
 
	StBaseinfoBuilding get(java.lang.Long id);
	
	Integer insert(StBaseinfoBuilding stBaseinfoBuilding);
	
	Integer update(StBaseinfoBuilding stBaseinfoBuilding);

    List<StBaseinfoBuilding> getBuildings();
}