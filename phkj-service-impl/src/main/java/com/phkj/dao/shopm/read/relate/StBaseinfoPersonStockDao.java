package com.phkj.dao.shopm.read.relate;

import org.springframework.stereotype.Repository;

import com.phkj.entity.relate.StBaseinfoPersonStock;

@Repository
public interface StBaseinfoPersonStockDao {
 
	StBaseinfoPersonStock get(java.lang.Long id);
	
	Integer insert(StBaseinfoPersonStock stBaseinfoPersonStock);
	
	Integer update(StBaseinfoPersonStock stBaseinfoPersonStock);
}