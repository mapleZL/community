package com.ejavashop.service.impl.order;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.entity.order.SendGoodsRecord;
import com.ejavashop.model.order.SendGoodsRecordModel;
import com.ejavashop.service.order.ISendGoodsRecordService;

@Service(value = "sendGoodsRecordService")
public class SendGoodsRecordServiceImpl implements ISendGoodsRecordService {
	private static Logger      log = LogManager.getLogger(SendGoodsRecordServiceImpl.class);
	
	@Resource
	private SendGoodsRecordModel sendGoodsRecordModel;
    
     /**
     * 根据id取得send_goods_record对象
     * @param  sendGoodsRecordId
     * @return
     */
    @Override
    public ServiceResult<SendGoodsRecord> getSendGoodsRecordById(Integer sendGoodsRecordId) {
        ServiceResult<SendGoodsRecord> result = new ServiceResult<SendGoodsRecord>();
        try {
            result.setResult(sendGoodsRecordModel.getSendGoodsRecordById(sendGoodsRecordId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[ISendGoodsRecordService][getSendGoodsRecordById]根据id["+sendGoodsRecordId+"]取得send_goods_record对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[ISendGoodsRecordService][getSendGoodsRecordById]根据id["+sendGoodsRecordId+"]取得send_goods_record对象时出现未知异常：",
                e);
        }
        return result;
    }
    
    /**
     * 保存send_goods_record对象
     * @param  sendGoodsRecord
     * @return
     */
     @Override
     public ServiceResult<Integer> saveSendGoodsRecord(SendGoodsRecord sendGoodsRecord) {
     	ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(sendGoodsRecordModel.saveSendGoodsRecord(sendGoodsRecord));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[ISendGoodsRecordService][saveSendGoodsRecord]保存send_goods_record对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[ISendGoodsRecordService][saveSendGoodsRecord]保存send_goods_record对象时出现未知异常：",
                e);
        }
        return result;
     }
     
     /**
     * 更新send_goods_record对象
     * @param  sendGoodsRecord
     * @return
     * @see com.ejavashop.service.SendGoodsRecordService#updateSendGoodsRecord(SendGoodsRecord)
     */
     @Override
     public ServiceResult<Integer> updateSendGoodsRecord(SendGoodsRecord sendGoodsRecord) {
     	ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(sendGoodsRecordModel.updateSendGoodsRecord(sendGoodsRecord));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[ISendGoodsRecordService][updateSendGoodsRecord]更新send_goods_record对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[ISendGoodsRecordService][updateSendGoodsRecord]更新send_goods_record对象时出现未知异常：",
                e);
        }
        return result;
     }
}