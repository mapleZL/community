package com.phkj.dao.shop.write.repaire;

import org.springframework.stereotype.Repository;

import com.phkj.entity.repair.StAppletRepairMember;

@Repository
public interface StAppletRepairMemberWriteDao {
 
	StAppletRepairMember get(java.lang.Integer id);
	
	Integer insert(StAppletRepairMember stAppletRepairMember);
	
	Integer update(StAppletRepairMember stAppletRepairMember);
}