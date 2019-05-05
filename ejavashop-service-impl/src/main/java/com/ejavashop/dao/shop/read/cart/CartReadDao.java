package com.ejavashop.dao.shop.read.cart;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ejavashop.entity.cart.Cart;

@Repository
public interface CartReadDao {

    // 移到write dao
    // List<Cart> getByMemberId(Integer memberId);
	   /**
     * 根据用户ID和使用类型取购物车数据
     * @param memberId 用户ID
     * @param useType 使用类型：1、购物车用（取所有购物车记录），2、订单结算用（只取用户勾选的记录）
     * @return
     */
    List<Cart> getByMemberId(@Param("memberId") Integer memberId,
                             @Param("useType") Integer useType);

    Cart get(java.lang.Integer id);

    Integer queryCount(Map<String, Object> map);

    List<Cart> queryList(Map<String, Object> map);

    /**
     * 根据用户ID获取用户购物车数量
     * @param memberId
     * @return
     */
    Integer getCartNumberByMId(Integer memberId);

    /**
     * 根据member_id获得商品的总重量
     * @param memberId
     * @param sellerId 
     * @return
     */
    Double getWeightByMemberId(@Param("memberId") Integer memberId,@Param("sellerId") Integer sellerId);

    Integer getCountByMemberId(@Param("memberId") Integer memberId, 
                               @Param("productId") Integer productId,
                               @Param("sellerId") Integer sellerId);

    Integer getCountByProductGoodsId(@Param("memberId") Integer memberId,
                                     @Param("productGoodsId") Integer productGoodsId);
    


    List<Cart> getSellersOfCart(java.lang.Integer sellerId);
    
    Integer selectPackage(Integer memberId);
    
}
