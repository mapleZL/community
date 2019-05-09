package com.phkj.dao.index.read.repair;


import com.phkj.entity.repair.StAppletRepair;
import org.springframework.stereotype.Repository;

@Repository
public interface StAppletRepairDao {
 
	StAppletRepair get(Integer id);
	
	Integer insert(StAppletRepair stAppletRepair);
	
	Integer update(StAppletRepair stAppletRepair);
}