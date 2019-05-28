package com.phkj.dao.shop.read.notice;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.phkj.entity.notice.StBrowse;

@Repository
public interface StBrowseReadDao {

    StBrowse get(java.lang.Long id);

    StBrowse getBrowseByNoticeId(@Param("rId") Long rId);

}