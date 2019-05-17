package com.phkj.dao.shop.write.notice;

import org.springframework.stereotype.Repository;

import com.phkj.entity.notice.StAppletActivitySign;

@Repository
public interface StAppletActivitySignWriteDao {
 
	Integer insert(StAppletActivitySign stAppletActivitySign);
	
	Integer update(StAppletActivitySign stAppletActivitySign);
}