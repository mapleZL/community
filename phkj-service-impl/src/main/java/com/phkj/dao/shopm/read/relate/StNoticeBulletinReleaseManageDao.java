package com.phkj.dao.shopm.read.relate;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.phkj.entity.relate.StNoticeBulletinReleaseManage;

@Repository
public interface StNoticeBulletinReleaseManageDao {

    StNoticeBulletinReleaseManage getNoticeById(@Param("id") Long id);

    List<StNoticeBulletinReleaseManage> pageList(@Param("start") Integer start,
                                                 @Param("pageSize") Integer pageSize,
                                                 @Param("type") String type);

    Integer getCount(@Param("type") String type);
}
