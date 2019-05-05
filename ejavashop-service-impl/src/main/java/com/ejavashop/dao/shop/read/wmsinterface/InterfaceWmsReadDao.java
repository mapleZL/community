package com.ejavashop.dao.shop.read.wmsinterface;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.wmsinterface.InterfaceWms;

@Repository
public interface InterfaceWmsReadDao {
 
	InterfaceWms get(java.lang.Integer id);
	
	InterfaceWms getInterfaceByRelationIdAndRelationTable(@Param("relationId")String relationId,@Param("relationTable")String relationTable);
	
	Integer getCount(@Param("queryMap")Map<String, String> queryMap);
	
	List<InterfaceWms> page(@Param("queryMap")Map<String, String> queryMap,@Param("start")Integer start,@Param("size")Integer size);
	
	List<InterfaceWms> getIntegerfaceWmsWithSendNoUp3(@Param("relationTable") String relationTable,@Param("sendNo") Integer sendNo);
	
}