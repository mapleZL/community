package com.phkj.dao.shopm.read.relate;


import com.phkj.entity.property.StBaseinfoStaffs;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StBaseinfoStaffsDao {
 
	StBaseinfoStaffs get(Integer id);
	
	Integer insert(StBaseinfoStaffs stBaseinfoStaffs);
	
	Integer update(StBaseinfoStaffs stBaseinfoStaffs);

	List<StBaseinfoStaffs> getStaffsOnDutyList();

    List<StBaseinfoStaffs> getStaffsByJobsId(List<Long> list);
}