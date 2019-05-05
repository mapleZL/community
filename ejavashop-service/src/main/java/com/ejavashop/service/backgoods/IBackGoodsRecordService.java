package com.ejavashop.service.backgoods;

import java.util.List;

import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.backgoods.BackGoodsRecord;

public interface IBackGoodsRecordService {

	/**
     * 根据id取得back_goods_record对象
     * @param  backGoodsRecordId
     * @return
     */
    ServiceResult<BackGoodsRecord> getBackGoodsRecordById(Integer backGoodsRecordId);
    
    /**
     * 保存back_goods_record对象
     * @param  backGoodsRecord
     * @return
     */
     ServiceResult<Integer> saveBackGoodsRecord(BackGoodsRecord backGoodsRecord);
     
     /**
     * 更新back_goods_record对象
     * @param  backGoodsRecord
     * @return
     */
     ServiceResult<Integer> updateBackGoodsRecord(BackGoodsRecord backGoodsRecord);
     
     
     ServiceResult<List<BackGoodsRecord>> getBackGoodRecordByGoodsId(Integer goodsId);
     
     
     
}