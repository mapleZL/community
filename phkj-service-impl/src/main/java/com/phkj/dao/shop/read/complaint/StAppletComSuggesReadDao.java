package com.phkj.dao.shop.read.complaint;

import com.phkj.entity.complaint.StAppletComSugges;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StAppletComSuggesReadDao {
    int deleteByPrimaryKey(Long id);

    int insert(StAppletComSugges record);

    int insertSelective(StAppletComSugges record);

    StAppletComSugges selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StAppletComSugges record);

    int updateByPrimaryKey(StAppletComSugges record);

    List<StAppletComSugges> selectAllMeComplaint(@Param("type") String type, @Param("userId") String userId);

    List<StAppletComSugges> selectAllComAndSugg(@Param("type") String type,@Param("sts") String sts);
}