package com.ejavashop.dao.shop.read.seller;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.seller.Seller;

@Repository
public interface SellerReadDao {

    Seller get(java.lang.Integer id);

    /**
     * 获取结算商家
     * @return
     */
    List<Seller> getSettlementSeller();

    /**
     * 根据用户ID获取商家
     * @param memberId
     * @return
     */
    Seller getByMemberId(@Param("memberId") Integer memberId);

    Integer getSellersCount(@Param("queryMap") Map<String, String> queryMap);

    List<Seller> getSellers(@Param("queryMap") Map<String, String> queryMap,
                            @Param("start") Integer start, @Param("size") Integer size);
    
    /**
     * 根据名称取商家
     * @param name
     * @return
     */
    List<Seller> getSellerByName(@Param("name") String name);

    /**
     * 根据店铺名称取商家
     * @param name
     * @return
     */
    List<Seller> getSellerBySellerName(@Param("sellerName") String sellerName);
    
    
    
    Seller checkSellerCode(String sellerCode);

    /**
     * 获取商家列表初始化查询页面
     * @return
     */
    List<Seller> getSellerListAll();
    
    /**
     * 以用户ID获取商家
     * @param userid
     * @return
     */
    Seller getSellerByMemberId(Integer memberId);

}
