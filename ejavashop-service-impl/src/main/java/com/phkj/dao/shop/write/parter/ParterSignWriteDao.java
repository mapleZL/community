package com.phkj.dao.shop.write.parter;

import org.springframework.stereotype.Repository;

import com.phkj.entity.parter.ParterSign;

@Repository
public interface ParterSignWriteDao {
 
	//ParterSign get(Integer id);
	
	Integer insert(ParterSign parterSign);
	
	Integer update(ParterSign parterSign);
	
	/*Integer getParterSignCount(@Param("queryMap") Map<String, String> queryMap,@Param("type")String type);
	
	List<ParterSign> getParterSign(@Param("queryMap") Map<String, String> queryMap,
            @Param("start") Integer start, @Param("size") Integer size,@Param("type")String type);
	
	ParterSign getByMemeberSignNo(@Param("signNo")String signNo);
	
	List<ParterSign> getByMemeberId(@Param("memberId")String memberId);*/
	
}