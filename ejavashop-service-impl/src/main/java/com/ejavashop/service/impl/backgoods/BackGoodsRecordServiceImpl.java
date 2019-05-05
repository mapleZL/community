package com.ejavashop.service.impl.backgoods;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import com.ejavashop.service.backgoods.IBackGoodsRecordService;
import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.entity.backgoods.BackGoodsRecord;
import com.ejavashop.model.backgoods.BackGoodsRecordModel;

@Service(value = "backGoodsRecordService")
public class BackGoodsRecordServiceImpl implements IBackGoodsRecordService {
	private static Logger      log = LogManager.getLogger(BackGoodsRecordServiceImpl.class);
	
	@Resource
	private BackGoodsRecordModel backGoodsRecordModel;
    
     /**
     * 根据id取得back_goods_record对象
     * @param  backGoodsRecordId
     * @return
     */
    @Override
    public ServiceResult<BackGoodsRecord> getBackGoodsRecordById(Integer backGoodsRecordId) {
        ServiceResult<BackGoodsRecord> result = new ServiceResult<BackGoodsRecord>();
        try {
            result.setResult(backGoodsRecordModel.getBackGoodsRecordById(backGoodsRecordId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IBackGoodsRecordService][getBackGoodsRecordById]根据id["+backGoodsRecordId+"]取得back_goods_record对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IBackGoodsRecordService][getBackGoodsRecordById]根据id["+backGoodsRecordId+"]取得back_goods_record对象时出现未知异常：",
                e);
        }
        return result;
    }
    
    /**
     * 保存back_goods_record对象
     * @param  backGoodsRecord
     * @return
     */
     @Override
     public ServiceResult<Integer> saveBackGoodsRecord(BackGoodsRecord backGoodsRecord) {
     	ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(backGoodsRecordModel.saveBackGoodsRecord(backGoodsRecord));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IBackGoodsRecordService][saveBackGoodsRecord]保存back_goods_record对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IBackGoodsRecordService][saveBackGoodsRecord]保存back_goods_record对象时出现未知异常：",
                e);
        }
        return result;
     }
     
     /**
     * 更新back_goods_record对象
     * @param  backGoodsRecord
     * @return
     * @see com.ejavashop.service.BackGoodsRecordService#updateBackGoodsRecord(BackGoodsRecord)
     */
     @Override
     public ServiceResult<Integer> updateBackGoodsRecord(BackGoodsRecord backGoodsRecord) {
     	ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(backGoodsRecordModel.updateBackGoodsRecord(backGoodsRecord));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IBackGoodsRecordService][updateBackGoodsRecord]更新back_goods_record对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IBackGoodsRecordService][updateBackGoodsRecord]更新back_goods_record对象时出现未知异常：",
                e);
        }
        return result;
     }

	@Override
	public ServiceResult<List<BackGoodsRecord>> getBackGoodRecordByGoodsId(Integer goodsId) {
		ServiceResult<List<BackGoodsRecord>> result = new ServiceResult<List<BackGoodsRecord>>();
        try {
            result.setResult(backGoodsRecordModel.getBackGoodRecordByGoodsId(goodsId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IBackGoodsRecordService][getBackGoodRecordByGoodsId]更新back_goods_record对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IBackGoodsRecordService][getBackGoodRecordByGoodsId]更新back_goods_record对象时出现未知异常：",
                e);
        }
        return result;
	}
}