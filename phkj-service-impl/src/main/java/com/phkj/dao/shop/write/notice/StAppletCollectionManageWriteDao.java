package com.phkj.dao.shop.write.notice;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.phkj.entity.notice.StAppletCollectionManage;

@Repository
public interface StAppletCollectionManageWriteDao {

    StAppletCollectionManage get(java.lang.Integer id);

    Integer insert(StAppletCollectionManage stAppletCollectionManage);

    Integer update(StAppletCollectionManage stAppletCollectionManage);

    void cancelCollection(@Param("memberId") Integer memberId, @Param("noticeId") Long noticeId);
}