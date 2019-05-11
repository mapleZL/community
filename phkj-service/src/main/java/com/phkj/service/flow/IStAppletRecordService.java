package com.phkj.service.flow;


import com.phkj.core.ServiceResult;
import com.phkj.entity.flow.StAppletRecord;

public interface IStAppletRecordService {

	/**
     * 根据id取得st_applet_record对象
     * @param  stAppletRecordId
     * @return
     */
    ServiceResult<StAppletRecord> getStAppletRecordById(Long stAppletRecordId);
    
    /**
     * 保存st_applet_record对象
     * @param  stAppletRecord
     * @return
     */
     ServiceResult<Integer> saveStAppletRecord(StAppletRecord stAppletRecord);
     
     /**
     * 更新st_applet_record对象
     * @param  stAppletRecord
     * @return
     */
     ServiceResult<Integer> updateStAppletRecord(StAppletRecord stAppletRecord);
}