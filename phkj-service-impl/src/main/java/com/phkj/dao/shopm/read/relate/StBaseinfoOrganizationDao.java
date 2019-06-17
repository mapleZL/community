package com.phkj.dao.shopm.read.relate;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.phkj.entity.relate.StBaseinfoOrganization;

@Repository
public interface StBaseinfoOrganizationDao {

    StBaseinfoOrganization get(Long id);

    Integer insert(StBaseinfoOrganization stBaseinfoOrganization);

    Integer update(StBaseinfoOrganization stBaseinfoOrganization);

    List<StBaseinfoOrganization> getOranizations();

    List<StBaseinfoOrganization> getOrganizationByRegion(@Param("region") String region);

    List<String> getRelationOrgations(@Param("orgCode") String villageCode);
}