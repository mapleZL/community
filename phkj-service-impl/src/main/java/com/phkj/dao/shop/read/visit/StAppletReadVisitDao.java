package com.phkj.dao.shop.read.visit;

import com.phkj.entity.visit.StAppletVisitor;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StAppletReadVisitDao {
    int deleteByPrimaryKey(Long id);

    int insert(StAppletVisitor record);

    int insertSelective(StAppletVisitor record);

    StAppletVisitor selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StAppletVisitor record);

    int updateByPrimaryKey(StAppletVisitor record);

    List<StAppletVisitor> getAllVisitor(@Param("sts") String sts, @Param("type") String type,@Param("villageCode") String villageCode);

    List<StAppletVisitor> selectByUserId(@Param("userId") String createUserId);
}