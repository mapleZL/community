package com.phkj.dao.shop.write.complaint;

import com.phkj.entity.complaint.StAppletComSugges;
import org.springframework.stereotype.Repository;

@Repository
public interface StAppletComSuggesWriteDao {
    int deleteByPrimaryKey(Long id);

    int insert(StAppletComSugges record);

    int insertSelective(StAppletComSugges record);

    StAppletComSugges selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StAppletComSugges record);

    int updateByPrimaryKey(StAppletComSugges record);
}