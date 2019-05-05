package com.ejavashop.dao.shop.read.product;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.product.ProductBrand;

/**
 * 品牌的接口
 *                       
 * @Filename: ProductBrandReadDao.java
 * @Version: 1.0
 * @Author: 王朋
 * @Email: wpjava@163.com
 *
 */
@Repository
public interface ProductBrandReadDao {

    /**
     * 根据id查询
     * @param id
     * @return
     */
    ProductBrand getById(@Param("id") Integer id);

    /**
     * 根据ID的集合统计品牌
     * @param ids
     * @return
     */
    List<ProductBrand> getByIds(@Param("ids") String ids);
    
    /**
     * 根据ID的集合统计品牌
     * @param ids
     * @return
     */
    List<ProductBrand> findAll();
    /**
     * 根据name查询ProductBrand，用于检测name是否唯一
     * @param name
     * @return
     */
    Integer getByName(@Param("name") String name);
    
    /**
     * 根据条件查询brand的count
     * @param queryMap
     * @return
     */
    Integer count(@Param("param1") Map<String, String> queryMap);

    /**
     * 分页查询
     * @param queryMap
     * @param start
     * @param size
     * @return
     */
    List<ProductBrand> page(@Param("param1") Map<String, String> queryMap,
                            @Param("start") Integer start, @Param("size") Integer size);

    
    /**
     * 取出所有的品牌
     * @return
     */
    List<ProductBrand> listNoPage();

    /**
     * 获得最大的品牌ID
     * @return
     */
    Integer getMaxId();

}
