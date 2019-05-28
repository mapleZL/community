package com.phkj.service.impl.notice;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.phkj.core.ConstantsEJS;
import com.phkj.core.ServiceResult;
import com.phkj.core.exception.BusinessException;
import com.phkj.entity.notice.StAppletUserBrowse;
import com.phkj.model.notice.StAppletUserBrowseModel;
import com.phkj.service.notice.IStAppletUserBrowseService;

@Service(value = "stAppletUserBrowseService")
public class StAppletUserBrowseServiceImpl implements IStAppletUserBrowseService {
    private static Logger           log = LogManager.getLogger(StAppletUserBrowseServiceImpl.class);

    @Resource
    private StAppletUserBrowseModel stAppletUserBrowseModel;

    /**
    * 根据id取得st_applet_user_browse对象
    * @param  stAppletUserBrowseId
    * @return
    */
    @Override
    public ServiceResult<StAppletUserBrowse> getStAppletUserBrowseById(Integer stAppletUserBrowseId) {
        ServiceResult<StAppletUserBrowse> result = new ServiceResult<StAppletUserBrowse>();
        try {
            result
                .setResult(stAppletUserBrowseModel.getStAppletUserBrowseById(stAppletUserBrowseId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IStAppletUserBrowseService][getStAppletUserBrowseById]根据id["
                      + stAppletUserBrowseId + "]取得st_applet_user_browse对象时出现未知异常："
                      + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IStAppletUserBrowseService][getStAppletUserBrowseById]根据id["
                      + stAppletUserBrowseId + "]取得st_applet_user_browse对象时出现未知异常：",
                e);
        }
        return result;
    }

    /**
     * 保存st_applet_user_browse对象
     * @param  stAppletUserBrowse
     * @return
     */
    @Override
    public ServiceResult<Integer> saveStAppletUserBrowse(StAppletUserBrowse stAppletUserBrowse) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(stAppletUserBrowseModel.saveStAppletUserBrowse(stAppletUserBrowse));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error(
                "[IStAppletUserBrowseService][saveStAppletUserBrowse]保存st_applet_user_browse对象时出现未知异常："
                      + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error(
                "[IStAppletUserBrowseService][saveStAppletUserBrowse]保存st_applet_user_browse对象时出现未知异常：",
                e);
        }
        return result;
    }

    /**
    * 更新st_applet_user_browse对象
    * @param  stAppletUserBrowse
    * @return
    * @see com.phkj.service.StAppletUserBrowseService#updateStAppletUserBrowse(StAppletUserBrowse)
    */
    @Override
    public ServiceResult<Integer> updateStAppletUserBrowse(StAppletUserBrowse stAppletUserBrowse) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(stAppletUserBrowseModel.updateStAppletUserBrowse(stAppletUserBrowse));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error(
                "[IStAppletUserBrowseService][updateStAppletUserBrowse]更新st_applet_user_browse对象时出现未知异常："
                      + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error(
                "[IStAppletUserBrowseService][updateStAppletUserBrowse]更新st_applet_user_browse对象时出现未知异常：",
                e);
        }
        return result;
    }

    /**
     * 增加个人用户的浏览信息
     * @param rId
     * @param memebrId
     * @see com.phkj.service.notice.IStAppletUserBrowseService#updateOrCreateBrowse(java.lang.Integer, java.lang.Integer)
     */
    @Override
    public void updateOrCreateBrowse(Integer rId, Integer memebrId) {
        
        try {
            StAppletUserBrowse stAppletUserBrowse = stAppletUserBrowseModel.getUserBrowse(rId, memebrId);
            if (stAppletUserBrowse != null && stAppletUserBrowse.getId() > 0) {
                // 浏览量默认加1
                stAppletUserBrowse.setBrowse(stAppletUserBrowse.getBrowse() + 1);
                stAppletUserBrowse.setModifyId(memebrId);
                stAppletUserBrowse.setModifyTime(new Date());
                stAppletUserBrowseModel.updateStAppletUserBrowse(stAppletUserBrowse);
            } else {
                stAppletUserBrowse = new StAppletUserBrowse();
                stAppletUserBrowse.setRId(rId);
                stAppletUserBrowse.setBrowse(1);
                stAppletUserBrowse.setCreateId(memebrId);
                stAppletUserBrowse.setCreateTime(new Date());
                stAppletUserBrowseModel.saveStAppletUserBrowse(stAppletUserBrowse);
            }
        } catch (Exception e) {
            log.error(
                "[IStAppletUserBrowseService][updateOrCreateBrowse]更新st_applet_user_browse对象时出现未知异常：",
                e);
        }
    }

    /**
     * 获取用户针对的浏览量记录
     * @param rId
     * @param memebrId
     * @return
     * @see com.phkj.service.notice.IStAppletUserBrowseService#getUserBrowse(java.lang.Integer, java.lang.Integer)
     */
    @Override
    public ServiceResult<StAppletUserBrowse> getUserBrowse(Integer rId, Integer memebrId) {
        ServiceResult<StAppletUserBrowse> result = new ServiceResult<>();
        try {
            StAppletUserBrowse stAppletUserBrowse = stAppletUserBrowseModel.getUserBrowse(rId, memebrId);
            result.setResult(stAppletUserBrowse);
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error(
                "[IStAppletUserBrowseService][getUserBrowse]获取st_applet_user_browse对象时出现未知异常：",
                e);
        }
        return result;
    }
    
}