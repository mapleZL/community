package com.ejavashop.dao.shop.write.parter;

import org.springframework.stereotype.Repository;

import com.ejavashop.entity.parter.ParterArea;

@Repository
public interface ParterAreaWriteDao {
 
	//ParterArea get(Integer id);
	
	Integer insert(ParterArea parterArea);
	
	Integer update(ParterArea parterArea);
	
	//List<ParterArea>getParterAreaByParterSignId(@Param("parterSignId")Integer parterSignId,@Param("memberAreaId")String memberAreaId);
	
	Integer deleteParterAreaByParterSignId(String parterSignId);
}