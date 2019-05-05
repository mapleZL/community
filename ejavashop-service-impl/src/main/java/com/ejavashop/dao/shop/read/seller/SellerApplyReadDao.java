package com.ejavashop.dao.shop.read.seller;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.seller.SellerApply;

@Repository
public interface SellerApplyReadDao {

    SellerApply get(java.lang.Integer id);

    /**
     * 以用户获取其商家申请
     * @param userid
     * @return
     */
    SellerApply getSellerApplyByUser(Integer userid);

    List<SellerApply> getSellerApplyByCondition(Map<String, Object> map);
    
    /**
     * 根据条件查询count
     * @param queryMap
     * @return
     */
    Integer getSellerApplysCount(@Param("queryMap") Map<String, String> queryMap);

    /**
     * 分页查询
     * @param queryMap
     * @param start
     * @param size
     * @return
     */
    List<SellerApply> getSellerApplys(@Param("queryMap") Map<String, String> queryMap,
                                      @Param("start") Integer start, @Param("size") Integer size);
    /**
     * 根据商家ID查询商家申请
     * @param userId
     * @return
     */
    SellerApply getSellerApplyByUserId(Integer userId);
    
    List<SellerApply> getSellerApplyByCompany(@Param("company") String company);

}
