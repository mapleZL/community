package com.phkj.dao.analysis.read;

import org.springframework.stereotype.Repository;

import com.phkj.entity.analysis.BrowseLogMobile;

@Repository
public interface BrowseLogMobileReadDao {

    BrowseLogMobile get(java.lang.Integer id);

}