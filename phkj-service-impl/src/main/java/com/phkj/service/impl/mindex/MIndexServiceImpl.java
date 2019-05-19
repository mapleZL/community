package com.phkj.service.impl.mindex;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.phkj.core.ConstantsEJS;
import com.phkj.core.PagerInfo;
import com.phkj.core.ServiceResult;
import com.phkj.core.TimeUtil;
import com.phkj.core.exception.BusinessException;
import com.phkj.entity.mindex.MIndexBanner;
import com.phkj.model.mindex.MIndexBannerModel;
import com.phkj.service.mindex.IMIndexService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

@Service(value = "mIndexService")
public class MIndexServiceImpl implements IMIndexService {
    private static Logger        log = LogManager.getLogger(MIndexServiceImpl.class);

    @Resource
    private MIndexBannerModel mIndexBannerModel;

    @Override
    public ServiceResult<MIndexBanner> getMIndexBannerById(Integer mIndexBannerId) {
        ServiceResult<MIndexBanner> result = new ServiceResult<MIndexBanner>();
        try {
            result.setResult(mIndexBannerModel.getMIndexBannerById(mIndexBannerId));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error("[IMIndexService][getMIndexBannerById]根据id[" + mIndexBannerId
                      + "]取得移动端首页轮播图时发生异常:" + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error(
                "[IMIndexService][getMIndexBannerById]根据id[" + mIndexBannerId + "]取得移动端首页轮播图时发生异常:",
                e);
        }
        return result;
    }

    @Override
    public ServiceResult<Boolean> saveMIndexBanner(MIndexBanner mIndexBanner) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(mIndexBannerModel.saveMIndexBanner(mIndexBanner));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error("[IMIndexService][saveMIndexBanner]保存移动端首页轮播图时发生异常:" + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IMIndexService][saveMIndexBanner]保存移动端首页轮播图时发生异常:", e);
        }
        return result;
    }

    @Override
    public ServiceResult<Boolean> updateMIndexBanner(MIndexBanner mIndexBanner) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(mIndexBannerModel.updateMIndexBanner(mIndexBanner));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error("[IMIndexService][updateMIndexBanner]更新移动端首页轮播图时发生异常:" + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IMIndexService][updateMIndexBanner]更新移动端首页轮播图时发生异常:", e);
        }
        return result;
    }

    @Override
    public ServiceResult<Boolean> deleteMIndexBanner(Integer mIndexBannerId) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(mIndexBannerModel.deleteMIndexBanner(mIndexBannerId));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error("[IMIndexService][deleteMIndexBanner]删除移动端首页轮播图时发生异常:" + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IMIndexService][deleteMIndexBanner]删除移动端首页轮播图时发生异常:", e);
        }
        return result;
    }

    @Override
    public ServiceResult<List<MIndexBanner>> getMIndexBanners(Map<String, String> queryMap,
                                                              PagerInfo pager) {
        ServiceResult<List<MIndexBanner>> serviceResult = new ServiceResult<List<MIndexBanner>>();
        serviceResult.setPager(pager);
        try {
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(mIndexBannerModel.getMIndexBannersCount(queryMap));
                start = pager.getStart();
                size = pager.getPageSize();
            }
            serviceResult.setResult(mIndexBannerModel.getMIndexBanners(queryMap, start, size));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[IMIndexService][getMIndexBanners]根据条件取得移动端首页轮播图时出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IMIndexService][getMIndexBanners]param1:" + JSON.toJSONString(queryMap)
                      + " &param2:" + JSON.toJSONString(pager));
            log.error("[IMIndexService][getMIndexBanners]根据条件取得移动端首页轮播图时发生异常:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<MIndexBanner>> getMIndexBannerForView(Boolean isPreview) {
        ServiceResult<List<MIndexBanner>> result = new ServiceResult<List<MIndexBanner>>();
        try {
            result.setResult(mIndexBannerModel.getMIndexBannerForView(isPreview));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error("[IMIndexService][getMIndexBannerForView]取得移动端首页轮播图时发生异常:" + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IMIndexService][getMIndexBannerForView]取得移动端首页轮播图时发生异常:", e);
        }
        return result;
    }

    @Override
    public ServiceResult<MIndexBanner> getPopupForView(String type) {
        ServiceResult<MIndexBanner> result = new ServiceResult<MIndexBanner>();
        try {
            Map<String, String> queryMap = new HashMap<String, String>();
            queryMap.put("q_type", type);
            queryMap.put("q_time", TimeUtil.getDateTimeString(new Date()));
            List<MIndexBanner> list = mIndexBannerModel.getMIndexBanners(queryMap);

            if (list != null && list.size() > 0) {
                result.setResult(list.get(0));
            }
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error("[IMIndexService][getMIndexBannerById]取得移动端首页弹出层图时发生异常:" + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error(
                "[IMIndexService][getMIndexBannerById][getMIndexBannerById]取得移动端首页弹出层发生异常:", e);
        }
        return result;
    }
    
}