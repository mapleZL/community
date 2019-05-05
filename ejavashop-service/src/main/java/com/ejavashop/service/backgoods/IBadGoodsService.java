package com.ejavashop.service.backgoods;

import java.util.List;
import java.util.Map;

import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.backgoods.BadGoods;
import com.ejavashop.entity.order.BookingSendGoods;

public interface IBadGoodsService {

	/**
     * 根据id取得bad_goods对象
     * @param  badGoodsId
     * @return
     */
    ServiceResult<BadGoods> getBadGoodsById(Integer badGoodsId);
    
    /**
     * 保存bad_goods对象
     * @param  badGoods
     * @return
     */
     ServiceResult<Integer> saveBadGoods(BadGoods badGoods);
     
     /**
     * 更新bad_goods对象
     * @param  badGoods
     * @return
     */
     ServiceResult<Integer> updateBadGoods(BadGoods badGoods);
     
     
     /**
      * 分页查询商品预约发货
      */
     ServiceResult<List<BadGoods>> page(Map<String, String> queryMap, PagerInfo pager); 
}