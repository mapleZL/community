package com.phkj.dao.shop.read.share;

import com.phkj.entity.share.StAppletShareApply;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface StAppletShareApplyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(StAppletShareApply record);

    int insertSelective(StAppletShareApply record);

    StAppletShareApply selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(StAppletShareApply record);

    int updateByPrimaryKeyWithBLOBs(StAppletShareApply record);

    int updateByPrimaryKey(StAppletShareApply record);

    List<StAppletShareApply> selectApplyByInfoId(Long id);

    List<StAppletShareApply> selectSUCCESSApplyByInfoId(Long id);

    List<Map> selectMeApplyInfoList(@Param("status") String status, @Param("userId") String userId, String villageCode);

    List<StAppletShareApply> selectSystemApplyByInfoId(Long id);

    List<StAppletShareApply> selectNOTINApplyById(@Param("infoId") Long infoId,@Param("id") Long id);

    StAppletShareApply selectApplyByUserId(@Param("userId") String userId, @Param("infoId") String id);
}