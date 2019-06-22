package com.phkj.dao.shop.read.order;


import com.phkj.entity.order.StAppletOrdersProduct;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StAppletOrdersProductReadDao {
 
	StAppletOrdersProduct get(Integer id);

    List<StAppletOrdersProduct> getStAppletOrdersProductList(@Param("ordersSn") String orderSn);

    List<StAppletOrdersProduct> getProductList(@Param("ordersSn") String orderSn);
}