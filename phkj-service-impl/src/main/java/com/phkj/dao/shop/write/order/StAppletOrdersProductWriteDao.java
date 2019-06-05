package com.phkj.dao.shop.write.order;


import com.phkj.entity.order.StAppletOrdersProduct;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StAppletOrdersProductWriteDao {
 
	Integer insert(StAppletOrdersProduct stAppletOrdersProduct);
	
	Integer update(StAppletOrdersProduct stAppletOrdersProduct);

    void batchInsertToOrdersProduct(List<StAppletOrdersProduct> list);
}