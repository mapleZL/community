package com.ejavashop.dao.shop.read.parter;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.parter.ParterSign;

@Repository
public interface ParterSignReadDao {
 
	ParterSign get(Integer id);
	
	Integer getParterSignCount(@Param("queryMap") Map<String, String> queryMap,@Param("type")String type);
	
	List<ParterSign> getParterSign(@Param("queryMap") Map<String, String> queryMap,
            @Param("start") Integer start, @Param("size") Integer size,@Param("type")String type);
	
	ParterSign getByMemeberSignNo(@Param("signNo")String signNo);
	
	List<ParterSign> getByMemeberId(@Param("memberId")String memberId);
	
}