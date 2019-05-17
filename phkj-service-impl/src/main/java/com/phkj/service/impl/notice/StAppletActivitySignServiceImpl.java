package com.phkj.service.impl.notice;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.phkj.core.ConstantsEJS;
import com.phkj.core.PagerInfo;
import com.phkj.core.ServiceResult;
import com.phkj.core.exception.BusinessException;
import com.phkj.entity.notice.StAppletActivitySign;
import com.phkj.model.notice.StAppletActivitySignModel;
import com.phkj.service.notice.IStAppletActivitySignService;

@Service(value = "stAppletActivitySignService")
public class StAppletActivitySignServiceImpl implements IStAppletActivitySignService {
    private static Logger             log = LogManager
        .getLogger(StAppletActivitySignServiceImpl.class);

    @Resource
    private StAppletActivitySignModel stAppletActivitySignModel;

    /**
    * 根据id取得st_applet_activity_sign对象
    * @param  stAppletActivitySignId
    * @return
    */
    @Override
    public ServiceResult<StAppletActivitySign> getStAppletActivitySignById(Integer stAppletActivitySignId) {
        ServiceResult<StAppletActivitySign> result = new ServiceResult<StAppletActivitySign>();
        try {
            result.setResult(
                stAppletActivitySignModel.getStAppletActivitySignById(stAppletActivitySignId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IStAppletActivitySignService][getStAppletActivitySignById]根据id["
                      + stAppletActivitySignId + "]取得st_applet_activity_sign对象时出现未知异常："
                      + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IStAppletActivitySignService][getStAppletActivitySignById]根据id["
                      + stAppletActivitySignId + "]取得st_applet_activity_sign对象时出现未知异常：",
                e);
        }
        return result;
    }

    /**
     * 保存st_applet_activity_sign对象
     * @param  stAppletActivitySign
     * @return
     */
    @Override
    public ServiceResult<Integer> saveStAppletActivitySign(StAppletActivitySign stAppletActivitySign) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(
                stAppletActivitySignModel.saveStAppletActivitySign(stAppletActivitySign));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error(
                "[IStAppletActivitySignService][saveStAppletActivitySign]保存st_applet_activity_sign对象时出现未知异常："
                      + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error(
                "[IStAppletActivitySignService][saveStAppletActivitySign]保存st_applet_activity_sign对象时出现未知异常：",
                e);
        }
        return result;
    }

    /**
    * 更新st_applet_activity_sign对象
    * @param  stAppletActivitySign
    * @return
    * @see com.phkj.service.StAppletActivitySignService#updateStAppletActivitySign(StAppletActivitySign)
    */
    @Override
    public ServiceResult<Integer> updateStAppletActivitySign(StAppletActivitySign stAppletActivitySign) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(
                stAppletActivitySignModel.updateStAppletActivitySign(stAppletActivitySign));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error(
                "[IStAppletActivitySignService][updateStAppletActivitySign]更新st_applet_activity_sign对象时出现未知异常："
                      + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error(
                "[IStAppletActivitySignService][updateStAppletActivitySign]更新st_applet_activity_sign对象时出现未知异常：",
                e);
        }
        return result;
    }

    /**
     * 获取活动报名列表
     * @param queryMap
     * @param pager
     * @return
     * @see com.phkj.service.notice.IStAppletActivitySignService#list(java.util.Map, com.phkj.core.PagerInfo)
     */
    @Override
    public ServiceResult<List<StAppletActivitySign>> list(Map<String, String> queryMap,
                                                          PagerInfo pager) {
        ServiceResult<List<StAppletActivitySign>> result = new ServiceResult<List<StAppletActivitySign>>();
        try {
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(stAppletActivitySignModel.getActivitySignCount(queryMap));
                start = pager.getStart();
                size = pager.getPageSize();
            }
            result.setResult(stAppletActivitySignModel.list(queryMap, start, size));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IStAppletActivitySignService][list]根据queryMap["
                      + JSONObject.toJSONString(queryMap) + "]取得st_applet_activity_sign列表时出现未知异常："
                      + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IStAppletActivitySignService][list]根据queryMap["
                      + JSONObject.toJSONString(queryMap) + "]取得st_applet_activity_sign列表时出现未知异常：",
                e);
        }
        return result;
    }
}