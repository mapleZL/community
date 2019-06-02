package com.phkj.dao.shop.read.lost;

import com.phkj.entity.lost.StAppletLostFound;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StAppletLostFoundReadMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StAppletLostFound record);

    int insertSelective(StAppletLostFound record);

    StAppletLostFound selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StAppletLostFound record);

    int updateByPrimaryKey(StAppletLostFound record);

    List<StAppletLostFound> getAllLostFound(@Param("type") String type, @Param("villageCode") String villageCode);

    List<StAppletLostFound> getMeLostFound(@Param("type") String type, @Param("villageCode") String villageCode,
                                           @Param("userId") String userId);

    List<StAppletLostFound> getSystemAllLostFound(@Param("type") String type, @Param("sts") String sts,
                                                  @Param("villageCode") String villageCode);

    List<StAppletLostFound> getSystemLostFoundList(@Param("type") String type, @Param("sts") String sts,
                                                   @Param("villageCode") String villageCode);

}