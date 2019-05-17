package com.phkj.service.impl.notice;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.phkj.core.ConstantsEJS;
import com.phkj.core.ServiceResult;
import com.phkj.core.exception.BusinessException;
import com.phkj.entity.notice.StBrowse;
import com.phkj.model.notice.StBrowseModel;
import com.phkj.service.notice.IStBrowseService;
/**
 * 
 *                       
 * @Filename: StBrowseServiceImpl.java
 * @Version: 1.0
 * @date: 2019年5月17日
 * @Author: 陆帅 * @Email: lu1632278229@sina.cn
 *
 */
@Service(value = "stBrowseService")
public class StBrowseServiceImpl implements IStBrowseService {
	private static Logger      log = LogManager.getLogger(StBrowseServiceImpl.class);
	
	@Resource
	private StBrowseModel stBrowseModel;
    
     /**
     * 根据id取得st_browse对象
     * @param  stBrowseId
     * @return
     */
    @Override
    public ServiceResult<StBrowse> getStBrowseById(Long stBrowseId) {
        ServiceResult<StBrowse> result = new ServiceResult<StBrowse>();
        try {
            result.setResult(stBrowseModel.getStBrowseById(stBrowseId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IStBrowseService][getStBrowseById]根据id["+stBrowseId+"]取得st_browse对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IStBrowseService][getStBrowseById]根据id["+stBrowseId+"]取得st_browse对象时出现未知异常：",
                e);
        }
        return result;
    }
    
    /**
     * 保存st_browse对象
     * @param  stBrowse
     * @return
     */
     @Override
     public ServiceResult<Integer> saveStBrowse(StBrowse stBrowse) {
     	ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(stBrowseModel.saveStBrowse(stBrowse));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IStBrowseService][saveStBrowse]保存st_browse对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IStBrowseService][saveStBrowse]保存st_browse对象时出现未知异常：",
                e);
        }
        return result;
     }
     
     /**
     * 更新st_browse对象
     * @param  stBrowse
     * @return
     * @see com.phkj.service.StBrowseService#updateStBrowse(StBrowse)
     */
     @Override
     public ServiceResult<Integer> updateStBrowse(StBrowse stBrowse) {
     	ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(stBrowseModel.updateStBrowse(stBrowse));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IStBrowseService][updateStBrowse]更新st_browse对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IStBrowseService][updateStBrowse]更新st_browse对象时出现未知异常：",
                e);
        }
        return result;
     }

     /**
      * 获取浏览量信息
      * @param id
      * @return
      * @see com.phkj.service.notice.IStBrowseService#getBrowseByNoticeId(java.lang.Long)
      */
    @Override
    public ServiceResult<StBrowse> getBrowseByNoticeId(Long noticeId) {
        ServiceResult<StBrowse> serviceResult = new ServiceResult<StBrowse>();
        try {
            StBrowse stBrowse = stBrowseModel.getBrowseByNoticeId(noticeId);
            serviceResult.setResult(stBrowse);
        } catch (Exception e) {
            log.error("查询浏览信息失败:noticeId=" + noticeId, e);
        }
        return serviceResult;
    }
}