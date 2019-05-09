package com.phkj.dao.shop.read.system;

import org.springframework.stereotype.Repository;

import com.phkj.entity.system.SystemLogs;

@Repository
public interface SystemLogsReadDao {
 
	SystemLogs get(java.lang.Integer id);
	
	//Integer insert(SystemLogs systemLogs);
	
	//Integer update(SystemLogs systemLogs);
}