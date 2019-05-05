package com.ejavashop.dao.shop.read.order;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.order.BookingSendGoods;

@Repository
public interface BookingSendGoodsReadDao {
 
	BookingSendGoods get(java.lang.Integer id);
	
	Integer getCount(Map<String, Object> queryMap);

    List<BookingSendGoods> page(Map<String, Object> queryMap);
    
    List<BookingSendGoods>getSendGoodsByOrdersId(Integer ordersId);
    
    List<BookingSendGoods>getBookList(@Param("cancelTime")String cancelTime);
}