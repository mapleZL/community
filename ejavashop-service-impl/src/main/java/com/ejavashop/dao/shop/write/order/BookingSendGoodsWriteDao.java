package com.ejavashop.dao.shop.write.order;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.order.BookingSendGoods;

@Repository
public interface BookingSendGoodsWriteDao {
 
	//BookingSendGoods get(java.lang.Integer id);
	
	Integer insert(BookingSendGoods bookingSendGoods);
	
	Integer update(BookingSendGoods bookingSendGoods);
	
	//Integer getCount(Map<String, Object> queryMap);

    //List<BookingSendGoods> page(Map<String, Object> queryMap);
    
    Integer auditSendGoods(Map<String, Object> queryMap);
    
    Integer delGoods(Integer id);    
    
    Integer returngoods(@Param("id")Integer id,@Param("status")Integer status,@Param("checkNote")String checkNote);
    
    //List<BookingSendGoods>getSendGoodsByOrdersId(Integer ordersId);
    
    //List<BookingSendGoods>getBookList(@Param("cancelTime")String cancelTime);
}