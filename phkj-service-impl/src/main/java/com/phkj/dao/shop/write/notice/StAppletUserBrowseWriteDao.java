package com.phkj.dao.shop.write.notice;

import org.springframework.stereotype.Repository;

import com.phkj.entity.notice.StAppletUserBrowse;

@Repository
public interface StAppletUserBrowseWriteDao {
 
	StAppletUserBrowse get(java.lang.Integer id);
	
	Integer insert(StAppletUserBrowse stAppletUserBrowse);
	
	Integer update(StAppletUserBrowse stAppletUserBrowse);
}