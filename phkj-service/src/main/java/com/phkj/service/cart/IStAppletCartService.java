package com.phkj.service.cart;

import java.util.List;

import com.phkj.core.ServiceResult;
import com.phkj.entity.cart.StAppletCart;

public interface IStAppletCartService {

    /**
     * 根据id取得商城购物车
     * @param  stAppletCartId
     * @return
     */
    ServiceResult<StAppletCart> getStAppletCartById(Integer stAppletCartId);

    /**
     * 保存商城购物车
     * @param  stAppletCart
     * @return
     */
    ServiceResult<Integer> saveStAppletCart(StAppletCart stAppletCart);

    /**
    * 更新商城购物车
    * @param  stAppletCart
    * @return
    */
    ServiceResult<Integer> updateStAppletCart(StAppletCart stAppletCart);

    /**
     * 修改购物车
     * @param id
     * @param num
     * @return
     */
    ServiceResult<Boolean> changeCart(Integer id, Integer num);

    /**
     * 根据购物车id删除商品
     * @param id
     * @return
     */
    Boolean delCartById(Integer id);

    /**
     * 查询购物车列表
     * @param memberId
     * @param villageCode
     * @param start
     * @param pageSize
     * @return
     */
    ServiceResult<List<StAppletCart>> list(Integer memberId, String villageCode, Integer start,
                                           Integer pageSize);

    /**
     * 查询数量
     * @param memberId
     * @param villageCode
     * @return
     */
    Integer getCount(Integer memberId, String villageCode);
}