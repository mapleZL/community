package com.ejavashop.dao.shop.read.product;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.product.ProductTypeAttr;
import com.ejavashop.vo.product.ProductTypeAttrVO;

@Repository
public interface ProductTypeAttrReadDao {

    ProductTypeAttr get(@Param("id") Integer id);

    /**
     * 根据类型查询出类型下面所有的检索属性
     * @param id
     * @return
     */
    List<ProductTypeAttr> getByTypeIdAndQuery(@Param("id") Integer id);
    
    List<ProductTypeAttr> getByTypeId(Integer id);

    Integer count(@Param("param1") Map<String, String> queryMap);

    List<ProductTypeAttrVO> page(@Param("param1") Map<String, String> queryMap,
                                 @Param("start") Integer start, @Param("size") Integer size);

    List<ProductTypeAttr> getProductTypeAttrList();

}
