package com.ejavashop.model.order;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ctc.wstx.util.StringUtil;
import com.ejavashop.dao.shop.read.order.SendGoodsRecordReadDao;
import com.ejavashop.dao.shop.write.order.SendGoodsRecordWriteDao;
import com.ejavashop.entity.order.SendGoodsRecord;

@Component
public class SendGoodsRecordModel {

	private static org.apache.log4j.Logger log = org.apache.log4j.LogManager
                                                   .getLogger(SendGoodsRecordModel.class);
    
    @Resource
    private SendGoodsRecordWriteDao sendGoodsRecordWriteDao;
    @Resource
    private SendGoodsRecordReadDao sendGoodsRecordReadDao;
    
    /**
     * 根据id取得send_goods_record对象
     * @param  sendGoodsRecordId
     * @return
     */
    public SendGoodsRecord getSendGoodsRecordById(Integer sendGoodsRecordId) {
    	return sendGoodsRecordReadDao.get(sendGoodsRecordId);
    }
    
    /**
     * 保存send_goods_record对象
     * @param  sendGoodsRecord
     * @return
     */
     public Integer saveSendGoodsRecord(SendGoodsRecord sendGoodsRecord) {
     	//this.dbConstrains(sendGoodsRecord);
     	return sendGoodsRecordWriteDao.insert(sendGoodsRecord);
     }
     
     /**
     * 更新send_goods_record对象
     * @param  sendGoodsRecord
     * @return
     */
     public Integer updateSendGoodsRecord(SendGoodsRecord sendGoodsRecord) {
     	this.dbConstrains(sendGoodsRecord);
     	return sendGoodsRecordWriteDao.update(sendGoodsRecord);
     }
     
     private void dbConstrains(SendGoodsRecord sendGoodsRecord) {
		//sendGoodsRecord.setSku(StringUtil.dbSafeString(sendGoodsRecord.getSku(), false, 20));
     }
     
     public List<SendGoodsRecord> getRecordByGoodsId(Integer sendGoodsId){
    	 return sendGoodsRecordReadDao.getRecordByGoodsId(sendGoodsId);
     } 
}