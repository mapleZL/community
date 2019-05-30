package com.phkj.dao.shop.write.product;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.phkj.entity.product.StAppletProduct;

@Repository
public interface StAppletProductWriteDao {

    Integer insert(StAppletProduct stAppletProduct);

    Integer update(StAppletProduct stAppletProduct);

    int updateState(@Param("id") Integer productId, @Param("state") int state);
    
    Integer updateByIds(Map<String, Object> param, List<Integer> ids);
}