package com.ejavashop.dao.shop.read.product;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.product.ProductCate;
import com.ejavashop.vo.product.ProductCateVO;

@Repository
public interface ProductCateReadDao {

    ProductCate get(Integer id);

    Integer queryCount(Map<String, Object> map);

    List<ProductCate> queryList(Map<String, Object> map);

    /**
     * 根据Pid获取分类列表
     * @param pid
     * @return
     */

    ProductCate getByTypeId(@Param("typeId") Integer typeId);

    Integer count(Map<String, String> queryMap);

    List<ProductCateVO> page(Map<String, Object> queryMap);

    List<ProductCate> getBySellerId(Integer sellerId);
    
    List<ProductCate> getByPid(Integer pid);

}
