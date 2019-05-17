package com.phkj.dao.shop.write.notice;

import org.springframework.stereotype.Repository;

import com.phkj.entity.notice.StBrowse;

@Repository
public interface StBrowseWriteDao {
	
	Integer insert(StBrowse stBrowse);
	
	Integer update(StBrowse stBrowse);
}