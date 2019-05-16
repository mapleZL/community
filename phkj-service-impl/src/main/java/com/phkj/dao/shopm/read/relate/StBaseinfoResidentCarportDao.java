package com.phkj.dao.shopm.read.relate;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.phkj.entity.relate.StBaseinfoResidentCarport;

@Repository
public interface StBaseinfoResidentCarportDao {

    StBaseinfoResidentCarport get(java.lang.Long id);

    Integer insert(StBaseinfoResidentCarport stBaseinfoResidentCarport);

    Integer update(StBaseinfoResidentCarport stBaseinfoResidentCarport);

    List<StBaseinfoResidentCarport> getCarportList(@Param("residentinfoId") Long residentinfoId);
}