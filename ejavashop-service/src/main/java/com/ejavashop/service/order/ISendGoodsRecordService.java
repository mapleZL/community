package com.ejavashop.service.order;

import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.order.SendGoodsRecord;

public interface ISendGoodsRecordService {

	/**
     * 根据id取得send_goods_record对象
     * @param  sendGoodsRecordId
     * @return
     */
    ServiceResult<SendGoodsRecord> getSendGoodsRecordById(Integer sendGoodsRecordId);
    
    /**
     * 保存send_goods_record对象
     * @param  sendGoodsRecord
     * @return
     */
     ServiceResult<Integer> saveSendGoodsRecord(SendGoodsRecord sendGoodsRecord);
     
     /**
     * 更新send_goods_record对象
     * @param  sendGoodsRecord
     * @return
     */
     ServiceResult<Integer> updateSendGoodsRecord(SendGoodsRecord sendGoodsRecord);
}