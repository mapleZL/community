package com.phkj.dao.shopm.read.relate;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.phkj.entity.relate.StBaseinfoOrganization;

@Repository
public interface StBaseinfoOrganizationDao {
 
	StBaseinfoOrganization get(Long id);
	
	Integer insert(StBaseinfoOrganization stBaseinfoOrganization);
	
	Integer update(StBaseinfoOrganization stBaseinfoOrganization);

    List<StBaseinfoOrganization> getOranizations();
}