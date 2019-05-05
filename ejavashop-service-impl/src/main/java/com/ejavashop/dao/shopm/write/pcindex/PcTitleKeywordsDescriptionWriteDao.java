package com.ejavashop.dao.shopm.write.pcindex;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.shopm.pcindex.PcTitleKeywordsDescription;

@Repository
public interface PcTitleKeywordsDescriptionWriteDao {
 
	Integer insert(PcTitleKeywordsDescription pcTitleKeywordsDescription);
	
	Integer update(PcTitleKeywordsDescription pcTitleKeywordsDescription);

}