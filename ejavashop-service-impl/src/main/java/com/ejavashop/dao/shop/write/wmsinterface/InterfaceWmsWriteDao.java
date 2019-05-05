package com.ejavashop.dao.shop.write.wmsinterface;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.wmsinterface.InterfaceWms;

@Repository
public interface InterfaceWmsWriteDao {
 
//	InterfaceWms get(java.lang.Integer id);
	
	Integer insert(InterfaceWms interfaceWms);
	
	Integer update(InterfaceWms interfaceWms);
	
//	InterfaceWms getInterfaceByRelationIdAndRelationTable(@Param("relationId")String relationId,@Param("relationTable")String relationTable);
//	
//	Integer getCount(@Param("queryMap")Map<String, String> queryMap);
//	
//	List<InterfaceWms> page(@Param("queryMap")Map<String, String> queryMap,@Param("start")Integer start,@Param("size")Integer size);
//	
}