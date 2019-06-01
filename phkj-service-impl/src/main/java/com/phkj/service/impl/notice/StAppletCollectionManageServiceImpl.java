package com.phkj.service.impl.notice;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.phkj.core.ConstantsEJS;
import com.phkj.core.ServiceResult;
import com.phkj.core.exception.BusinessException;
import com.phkj.entity.notice.StAppletCollectionManage;
import com.phkj.model.notice.StAppletCollectionManageModel;
import com.phkj.service.notice.IStAppletCollectionManageService;

/**
 * 
 *                       
 * @Filename: StAppletCollectionManageServiceImpl.java
 * @Version: 1.0
 * @date: 2019年5月17日
 * @Author: 陆帅 * @Email: lu1632278229@sina.cn
 *
 */
@Service(value = "stAppletCollectionManageService")
public class StAppletCollectionManageServiceImpl implements IStAppletCollectionManageService {
	private static Logger      log = LogManager.getLogger(StAppletCollectionManageServiceImpl.class);
	
	@Resource
	private StAppletCollectionManageModel stAppletCollectionManageModel;
    
     /**
     * 根据id取得st_applet_collection_manage对象
     * @param  stAppletCollectionManageId
     * @return
     */
    @Override
    public ServiceResult<StAppletCollectionManage> getStAppletCollectionManageById(Integer stAppletCollectionManageId) {
        ServiceResult<StAppletCollectionManage> result = new ServiceResult<StAppletCollectionManage>();
        try {
            result.setResult(stAppletCollectionManageModel.getStAppletCollectionManageById(stAppletCollectionManageId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IStAppletCollectionManageService][getStAppletCollectionManageById]根据id["+stAppletCollectionManageId+"]取得st_applet_collection_manage对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IStAppletCollectionManageService][getStAppletCollectionManageById]根据id["+stAppletCollectionManageId+"]取得st_applet_collection_manage对象时出现未知异常：",
                e);
        }
        return result;
    }
    
    /**
     * 保存st_applet_collection_manage对象
     * @param  stAppletCollectionManage
     * @return
     */
     @Override
     public ServiceResult<Integer> saveStAppletCollectionManage(StAppletCollectionManage stAppletCollectionManage) {
     	ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(stAppletCollectionManageModel.saveStAppletCollectionManage(stAppletCollectionManage));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IStAppletCollectionManageService][saveStAppletCollectionManage]保存st_applet_collection_manage对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IStAppletCollectionManageService][saveStAppletCollectionManage]保存st_applet_collection_manage对象时出现未知异常：",
                e);
        }
        return result;
     }
     
     /**
     * 更新st_applet_collection_manage对象
     * @param  stAppletCollectionManage
     * @return
     * @see com.phkj.service.StAppletCollectionManageService#updateStAppletCollectionManage(StAppletCollectionManage)
     */
     @Override
     public ServiceResult<Integer> updateStAppletCollectionManage(StAppletCollectionManage stAppletCollectionManage) {
     	ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(stAppletCollectionManageModel.updateStAppletCollectionManage(stAppletCollectionManage));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IStAppletCollectionManageService][updateStAppletCollectionManage]更新st_applet_collection_manage对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IStAppletCollectionManageService][updateStAppletCollectionManage]更新st_applet_collection_manage对象时出现未知异常：",
                e);
        }
        return result;
     }

     /**
      * 获取用户是否已经收藏
      * @param memberId
      * @param noticeId
      * @return
      * @see com.phkj.service.notice.IStAppletCollectionManageService#getCollectionCount(java.lang.Integer, java.lang.Long)
      */
    @Override
    public ServiceResult<Integer> getCollectionCount(Integer memberId, Long noticeId) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(stAppletCollectionManageModel.getCollectionCount(memberId, noticeId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IStAppletCollectionManageService][getCollectionCount]根据noticeId["+noticeId+"]取得st_applet_collection_manage对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IStAppletCollectionManageService][getCollectionCount]根据noticeId["+noticeId+"]取得st_applet_collection_manage对象时出现未知异常：",
                e);
        }
        return result;
    }

    @Override
    public Boolean cancelCollection(Integer memberId, Long noticeId) {
        try {
            stAppletCollectionManageModel.cancelCollection(memberId, noticeId);
        } catch (BusinessException e) {
            log.error("[IStAppletCollectionManageService][cancelCollection]取消收藏时出现未知异常：" + e.getMessage());
            return false;
        } catch (Exception e) {
            log.error("[IStAppletCollectionManageService][cancelCollection]根据noticeId["+noticeId+"]和memberId["+memberId+"]取消收藏时出现未知异常：",
                e);
            return false;
        }
        return true;
    }

    @Override
    public ServiceResult<Long> getCountByNoticeid(Long noticeId) {
        ServiceResult<Long> result = new ServiceResult<Long>();
        try {
            result.setResult(stAppletCollectionManageModel.getCountByNoticeid(noticeId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IStAppletCollectionManageService][getCountByNoticeid]根据noticeId["+noticeId+"]取得收藏量时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IStAppletCollectionManageService][getCountByNoticeid]根据noticeId["+noticeId+"]取得收藏量时出现未知异常：",
                e);
        }
        return result;
    }

    @Override
    public ServiceResult<List<StAppletCollectionManage>> getCollectionList(Integer memberId, Integer start, Integer pageSize, String villageCode) {
        ServiceResult<List<StAppletCollectionManage>> result = new ServiceResult<>();
        try {
            start = (start - 1) * pageSize;
            result.setResult(stAppletCollectionManageModel.getCollectionList(memberId, start, pageSize, villageCode));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IStAppletCollectionManageService][getCollectionList]根据memberId["+memberId+"]取得收藏量时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IStAppletCollectionManageService][getCollectionList]根据memberId["+memberId+"]取得收藏量时出现未知异常：",
                e);
        }
        return result;
    }

    @Override
    public Integer getCount(Integer memberId, String villageCode) {
        return stAppletCollectionManageModel.getCount(memberId, villageCode);
    }
}