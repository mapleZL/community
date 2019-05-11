package com.phkj.service.impl.flow;

import com.phkj.core.ConstantsEJS;
import com.phkj.core.ServiceResult;
import com.phkj.core.exception.BusinessException;
import com.phkj.entity.flow.StAppletRecord;
import com.phkj.model.flow.StAppletRecordModel;
import com.phkj.service.flow.IStAppletRecordService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service(value = "stAppletRecordService")
public class StAppletRecordServiceImpl implements IStAppletRecordService {
	private static Logger      log = LogManager.getLogger(StAppletRecordServiceImpl.class);
	
	@Resource
	private StAppletRecordModel stAppletRecordModel;
    
     /**
     * 根据id取得st_applet_record对象
     * @param  stAppletRecordId
     * @return
     */
    @Override
    public ServiceResult<StAppletRecord> getStAppletRecordById(Long stAppletRecordId) {
        ServiceResult<StAppletRecord> result = new ServiceResult<StAppletRecord>();
        try {
            result.setResult(stAppletRecordModel.getStAppletRecordById(stAppletRecordId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IStAppletRecordService][getStAppletRecordById]根据id["+stAppletRecordId+"]取得st_applet_record对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IStAppletRecordService][getStAppletRecordById]根据id["+stAppletRecordId+"]取得st_applet_record对象时出现未知异常：",
                e);
        }
        return result;
    }
    
    /**
     * 保存st_applet_record对象
     * @param  stAppletRecord
     * @return
     */
     @Override
     public ServiceResult<Integer> saveStAppletRecord(StAppletRecord stAppletRecord) {
     	ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(stAppletRecordModel.saveStAppletRecord(stAppletRecord));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IStAppletRecordService][saveStAppletRecord]保存st_applet_record对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IStAppletRecordService][saveStAppletRecord]保存st_applet_record对象时出现未知异常：",
                e);
        }
        return result;
     }
     
     /**
     * 更新st_applet_record对象
     * @param  stAppletRecord
     * @return
     */
     @Override
     public ServiceResult<Integer> updateStAppletRecord(StAppletRecord stAppletRecord) {
     	ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(stAppletRecordModel.updateStAppletRecord(stAppletRecord));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IStAppletRecordService][updateStAppletRecord]更新st_applet_record对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IStAppletRecordService][updateStAppletRecord]更新st_applet_record对象时出现未知异常：",
                e);
        }
        return result;
     }
}