package com.phkj.dao.shop.read.notice;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.phkj.entity.notice.StAppletCollectionManage;

@Repository
public interface StAppletCollectionManageReadDao {

    StAppletCollectionManage get(java.lang.Integer id);

    Integer getCollectionCount(@Param("memberId") Integer memberId,
                               @Param("noticeId") Long noticeId);

    Long getCountByNoticeid(@Param("noticeId") Long noticeId);

    List<StAppletCollectionManage> getCollectionList(@Param("memberId") Integer memberId,
                                                     @Param("start") Integer start,
                                                     @Param("pageSize") Integer pageSize);

    Integer getCount(@Param("memberId") Integer memberId);
}