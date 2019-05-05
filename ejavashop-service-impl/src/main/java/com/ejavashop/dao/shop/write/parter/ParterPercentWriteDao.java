package com.ejavashop.dao.shop.write.parter;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.parter.ParterPercent;

@Repository
public interface ParterPercentWriteDao {
 
	//ParterPercent get(Integer id);
	
	Integer insert(ParterPercent parterPercent);
	
	Integer update(ParterPercent parterPercent);
	
	//Integer getParterPercentCount(@Param("queryMap") Map<String, String> queryMap);
	
/*	List<ParterPercent> getParterPercent(@Param("queryMap") Map<String, String> queryMap,
            @Param("start") Integer start, @Param("size") Integer size);*/
	
	Integer percentDelete(@Param("parterPercentId")Integer parterPercentId);
	
	
	//List<ParterPercent>getParterPercentByParterSignId(@Param("parterSignId")Integer parterSignId,@Param("percentType") String percentType);
}