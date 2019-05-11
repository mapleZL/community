package com.phkj.dao.share;

import com.phkj.entity.share.StAppletShareApply;
import org.springframework.stereotype.Repository;

import java.util.List;

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
}