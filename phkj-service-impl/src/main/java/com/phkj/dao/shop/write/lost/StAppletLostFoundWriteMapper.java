package com.phkj.dao.shop.write.lost;

import com.phkj.entity.lost.StAppletLostFound;
import org.springframework.stereotype.Repository;

@Repository
public interface StAppletLostFoundWriteMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StAppletLostFound record);

    int insertSelective(StAppletLostFound record);

    StAppletLostFound selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StAppletLostFound record);

    int updateByPrimaryKey(StAppletLostFound record);
}