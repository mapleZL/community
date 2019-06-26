package com.phkj.dao.shopm.read.relate;

import com.phkj.entity.property.StBaseinfoStaffs;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface StBaseinfoStaffsDao {

    StBaseinfoStaffs get(Integer id);

    Integer insert(StBaseinfoStaffs stBaseinfoStaffs);

    Integer update(StBaseinfoStaffs stBaseinfoStaffs);

    List<StBaseinfoStaffs> getStaffsOnDutyList();

    List<StBaseinfoStaffs> getStaffsByJobsId(List<Long> list);

    int getStaffsByParam(@Param("param") Map<String, Object> param);
}