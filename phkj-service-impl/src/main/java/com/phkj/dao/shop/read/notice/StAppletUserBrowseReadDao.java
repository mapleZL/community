package com.phkj.dao.shop.read.notice;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.phkj.entity.notice.StAppletUserBrowse;

@Repository
public interface StAppletUserBrowseReadDao {

    StAppletUserBrowse get(java.lang.Integer id);

    Integer insert(StAppletUserBrowse stAppletUserBrowse);

    Integer update(StAppletUserBrowse stAppletUserBrowse);

    StAppletUserBrowse getUserBrowse(@Param("rId") Integer rId,
                                     @Param("memberId") Integer memebrId);
}