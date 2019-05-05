package com.ejavashop.dao.shop.read.system;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.system.Regions;

@Repository
public interface RegionsReadDao {
	
    List<Regions> getParterSignArea(@Param("memberId") Integer memberId,@Param("signNo")String signNo);

	 List<Regions> page(Map<String, String> queryMap);
	
	 Integer getCount(Map<String, String> queryMap);

    Regions get(java.lang.Integer id);

    List<Regions> getProvince();

    List<Regions> getChildrenArea(Map<String, Object> map);

    List<Regions> getByParentId(Integer parentId);

    Regions getRegionsByRegionName1(String regionName);

    Regions getRegionsByRegionName2(@Param("regionName") String regionName, @Param("parentId")Integer parentId);
}
