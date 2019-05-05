package com.ejavashop.dao.shop.read.parter;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.parter.ParterArea;

@Repository
public interface ParterAreaReadDao {
 
	ParterArea get(Integer id);
	
	List<ParterArea>getParterAreaByParterSignId(@Param("parterSignId")Integer parterSignId,@Param("memberAreaId")String memberAreaId);
	
}