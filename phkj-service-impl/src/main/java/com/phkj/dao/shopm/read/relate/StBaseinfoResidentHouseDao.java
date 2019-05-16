package com.phkj.dao.shopm.read.relate;

import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.phkj.entity.relate.StBaseinfoResidentHouse;

@Repository
public interface StBaseinfoResidentHouseDao {
 
	StBaseinfoResidentHouse get(java.lang.Long id);
	
	Integer insert(StBaseinfoResidentHouse stBaseinfoResidentHouse);
	
	Integer update(StBaseinfoResidentHouse stBaseinfoResidentHouse);

    StBaseinfoResidentHouse getResidentBouseByParam(@Param("queryMap")Map<String, Object> queryMap);
}