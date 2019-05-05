package com.ejavashop.dao.shop.read.seller;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.seller.SellerApplyBrand;

@Repository
public interface SellerApplyBrandReadDao {

    SellerApplyBrand get(java.lang.Integer id);

    Integer queryCount(Map<String, Object> map);

    List<SellerApplyBrand> queryList(Map<String, Object> map);
    
    /**
     * 根据id查询
     * @param id
     * @return
     */
    SellerApplyBrand getById(Integer id);
    /**
     * 根据name查询SellerApplyBrand，用于检测name是否唯一
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
    List<SellerApplyBrand> page(@Param("param1") Map<String, String> queryMap,
                                @Param("start") Integer start, @Param("size") Integer size);

    /**
     * 待提交审核数
     * @param queryMap
     * @return
     */
    Integer todoCount(@Param("param1") Map<String, String> queryMap);

    /**
     * 待提交审核列表
     * @param queryMap
     * @param start
     * @param size
     * @return
     */
    List<SellerApplyBrand> todoList(@Param("param1") Map<String, String> queryMap,
                                    @Param("start") Integer start, @Param("size") Integer size);

}
