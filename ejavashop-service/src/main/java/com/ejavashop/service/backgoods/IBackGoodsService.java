package com.ejavashop.service.backgoods;

import java.util.Map;

import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.backgoods.BackGoods;

public interface IBackGoodsService {

	/**
     * 根据id取得back_goods对象
     * @param  backGoodsId
     * @return
     */
    ServiceResult<BackGoods> getBackGoodsById(Integer backGoodsId);
    
    /**
     * 保存back_goods对象
     * @param  backGoods
     * @return
     */
     ServiceResult<Integer> saveBackGoods(BackGoods backGoods);
     
     /**
     * 更新back_goods对象
     * @param  backGoods
     * @return
     */
     ServiceResult<Integer> updateBackGoods(BackGoods backGoods);
}