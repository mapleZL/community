package com.phkj.dao.shopm.read.relate;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.phkj.entity.relate.StBaseinfoUnits;

@Repository
public interface StBaseinfoUnitsDao {
 
	StBaseinfoUnits get(java.lang.Long id);
	
	Integer insert(StBaseinfoUnits stBaseinfoUnits);
	
	Integer update(StBaseinfoUnits stBaseinfoUnits);

    List<StBaseinfoUnits> getUnits();
}