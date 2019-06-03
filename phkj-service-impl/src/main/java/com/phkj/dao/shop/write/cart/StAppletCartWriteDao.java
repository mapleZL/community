package com.phkj.dao.shop.write.cart;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.phkj.entity.cart.StAppletCart;

@Repository
public interface StAppletCartWriteDao {
 
	Integer insert(StAppletCart stAppletCart);
	
	Integer update(StAppletCart stAppletCart);

    void delCartById(@Param("id") Integer id);
}