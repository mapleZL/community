package com.phkj.dao.shopm.read.relate;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.phkj.entity.relate.StBaseinfoResidentinfo;

@Repository
public interface StBaseinfoResidentinfoDao {

    StBaseinfoResidentinfo get(java.lang.Long id);

    Integer insert(StBaseinfoResidentinfo stBaseinfoResidentinfo);

    Integer update(StBaseinfoResidentinfo stBaseinfoResidentinfo);

    StBaseinfoResidentinfo getResidentinfo(@Param("phone") String phone);
}