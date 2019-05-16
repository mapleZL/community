package com.phkj.service.impl.relate;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.phkj.core.ConstantsEJS;
import com.phkj.core.ServiceResult;
import com.phkj.core.exception.BusinessException;
import com.phkj.entity.relate.StBaseinfoResidentinfo;
import com.phkj.model.relate.StBaseinfoResidentinfoModel;
import com.phkj.service.relate.IStBaseinfoResidentinfoService;

@Service(value = "stBaseinfoResidentinfoService")
public class StBaseinfoResidentinfoServiceImpl implements IStBaseinfoResidentinfoService {
    private static Logger               log = LogManager
        .getLogger(StBaseinfoResidentinfoServiceImpl.class);

    @Resource
    private StBaseinfoResidentinfoModel stBaseinfoResidentinfoModel;

    /**
    * 根据id取得居民信息
    * @param  stBaseinfoResidentinfoId
    * @return
    */
    @Override
    public ServiceResult<StBaseinfoResidentinfo> getStBaseinfoResidentinfoById(Long stBaseinfoResidentinfoId) {
        ServiceResult<StBaseinfoResidentinfo> result = new ServiceResult<StBaseinfoResidentinfo>();
        try {
            result.setResult(stBaseinfoResidentinfoModel
                .getStBaseinfoResidentinfoById(stBaseinfoResidentinfoId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IStBaseinfoResidentinfoService][getStBaseinfoResidentinfoById]根据id["
                      + stBaseinfoResidentinfoId + "]取得居民信息时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IStBaseinfoResidentinfoService][getStBaseinfoResidentinfoById]根据id["
                      + stBaseinfoResidentinfoId + "]取得居民信息时出现未知异常：",
                e);
        }
        return result;
    }

    /**
     * 保存居民信息
     * @param  stBaseinfoResidentinfo
     * @return
     */
    @Override
    public ServiceResult<Integer> saveStBaseinfoResidentinfo(StBaseinfoResidentinfo stBaseinfoResidentinfo) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(
                stBaseinfoResidentinfoModel.saveStBaseinfoResidentinfo(stBaseinfoResidentinfo));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IStBaseinfoResidentinfoService][saveStBaseinfoResidentinfo]保存居民信息时出现未知异常："
                      + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IStBaseinfoResidentinfoService][saveStBaseinfoResidentinfo]保存居民信息时出现未知异常：",
                e);
        }
        return result;
    }

    /**
    * 更新居民信息
    * @param  stBaseinfoResidentinfo
    * @return
    * @see com.phkj.service.StBaseinfoResidentinfoService#updateStBaseinfoResidentinfo(StBaseinfoResidentinfo)
    */
    @Override
    public ServiceResult<Integer> updateStBaseinfoResidentinfo(StBaseinfoResidentinfo stBaseinfoResidentinfo) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(
                stBaseinfoResidentinfoModel.updateStBaseinfoResidentinfo(stBaseinfoResidentinfo));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IStBaseinfoResidentinfoService][updateStBaseinfoResidentinfo]更新居民信息时出现未知异常："
                      + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error(
                "[IStBaseinfoResidentinfoService][updateStBaseinfoResidentinfo]更新居民信息时出现未知异常：", e);
        }
        return result;
    }

    @Override
    public StBaseinfoResidentinfo getResidentinfo(String phone) {
        return stBaseinfoResidentinfoModel.getResidentinfo(phone);
    }
}