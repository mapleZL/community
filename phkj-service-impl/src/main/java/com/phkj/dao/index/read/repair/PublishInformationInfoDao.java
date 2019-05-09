package com.phkj.dao.index.read.repair;


import com.phkj.entity.repair.PublishInformationInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface PublishInformationInfoDao {
 
	PublishInformationInfo get(Long id);
	
	Integer insert(PublishInformationInfo publishInformationInfo);
	
	Integer update(PublishInformationInfo publishInformationInfo);
}