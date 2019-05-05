package com.ejavashop.dao.shopm.read.pcindex;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.shopm.pcindex.PcTitleKeywordsDescription;

@Repository
public interface PcTitleKeywordsDescriptionReadDao {
 
	PcTitleKeywordsDescription get(java.lang.Integer id);

    PcTitleKeywordsDescription getByPath(@Param("path") String visitPath);

    int getCount(Map<String, Object> param);

    List<PcTitleKeywordsDescription> page(Map<String, Object> param);
}