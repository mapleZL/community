package com.phkj.dao.shop.read.repaire;

import com.phkj.entity.repair.StAppletComment;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StAppletCommentDao {

    StAppletComment get(Long id);

    Integer insert(StAppletComment stAppletComment);

    Integer update(StAppletComment stAppletComment);

    List<StAppletComment> getStAppletCommentList(@Param("rId") Long rId,
                                                 @Param("start") int pageNum,
                                                 @Param("size") int pageSize);

    Long getCountByRId(@Param("noticeId") Long noticeId, @Param("rType") String rType);
}