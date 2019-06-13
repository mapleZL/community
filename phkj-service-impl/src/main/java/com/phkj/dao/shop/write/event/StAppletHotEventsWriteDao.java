package com.phkj.dao.shop.write.event;

import org.springframework.stereotype.Repository;

import com.phkj.entity.event.StAppletHotEvents;

@Repository
public interface StAppletHotEventsWriteDao {
 
	StAppletHotEvents get(java.lang.Integer id);
	
	Integer insert(StAppletHotEvents stAppletHotEvents);
	
	Integer update(StAppletHotEvents stAppletHotEvents);
}