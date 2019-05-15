package com.phkj.dao.shop.read.flow;


import com.phkj.entity.flow.StAppletRecord;
import org.springframework.stereotype.Repository;

@Repository
public interface StAppletRecordDao {
 
	StAppletRecord get(Long id);
	
	Integer insert(StAppletRecord stAppletRecord);
	
	Integer update(StAppletRecord stAppletRecord);
}