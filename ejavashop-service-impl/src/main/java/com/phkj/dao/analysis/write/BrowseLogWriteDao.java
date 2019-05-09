package com.phkj.dao.analysis.write;

import org.springframework.stereotype.Repository;

import com.phkj.entity.analysis.BrowseLog;

@Repository
public interface BrowseLogWriteDao {

//    BrowseLog get(java.lang.Integer id);

    Integer insert(BrowseLog browseLog);

    Integer update(BrowseLog browseLog);
}