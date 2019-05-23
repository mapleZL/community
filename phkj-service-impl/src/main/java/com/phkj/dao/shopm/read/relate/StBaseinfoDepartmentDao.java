package com.phkj.dao.shopm.read.relate;


import com.phkj.entity.property.StBaseinfoDepartment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StBaseinfoDepartmentDao {
 
	StBaseinfoDepartment get(Integer id);
	
	Integer insert(StBaseinfoDepartment stBaseinfoDepartment);
	
	Integer update(StBaseinfoDepartment stBaseinfoDepartment);

    List<StBaseinfoDepartment> getDepartmentList(List<Long> list);
}