package com.ejavashop.service.impl.promotion;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.entity.promotion.bidding.ActBiddingBanner;
import com.ejavashop.model.promotion.ActBiddingBannerModel;
import com.ejavashop.service.promotion.IActBiddingBannerService;

@Service(value = "actBiddingBannerService")
public class ActBiddingBannerServiceImpl implements IActBiddingBannerService {
    private static Logger         log = LogManager.getLogger(ActBiddingBannerServiceImpl.class);

    @Resource
    private ActBiddingBannerModel actBiddingBannerModel;

    @Override
    public ServiceResult<ActBiddingBanner> getActBiddingBannerById(Integer actBiddingBannerId) {
        ServiceResult<ActBiddingBanner> result = new ServiceResult<ActBiddingBanner>();
        try {
            result.setResult(actBiddingBannerModel.getActBiddingBannerById(actBiddingBannerId));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error("[IActBiddingBannerService][getActBiddingBannerById]根据id["
                      + actBiddingBannerId + "]取得集合竞价首页轮播图时发生异常:" + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IActBiddingBannerService][getActBiddingBannerById]根据id["
                      + actBiddingBannerId + "]取得集合竞价首页轮播图时发生异常:", e);
        }
        return result;
    }

    @Override
    public ServiceResult<Boolean> saveActBiddingBanner(ActBiddingBanner actBiddingBanner) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(actBiddingBannerModel.saveActBiddingBanner(actBiddingBanner));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error("[IActBiddingBannerService][saveActBiddingBanner]保存集合竞价首页轮播图时发生异常:"
                      + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IActBiddingBannerService][saveActBiddingBanner]保存集合竞价首页轮播图时发生异常:", e);
        }
        return result;
    }

    @Override
    public ServiceResult<Boolean> updateActBiddingBanner(ActBiddingBanner actBiddingBanner) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(actBiddingBannerModel.updateActBiddingBanner(actBiddingBanner));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error("[IActBiddingBannerService][updateActBiddingBanner]更新集合竞价首页轮播图时发生异常:"
                      + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IActBiddingBannerService][updateActBiddingBanner]更新集合竞价首页轮播图时发生异常:", e);
        }
        return result;
    }

    @Override
    public ServiceResult<Boolean> deleteActBiddingBanner(Integer actBiddingBannerId) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(actBiddingBannerModel.deleteActBiddingBanner(actBiddingBannerId));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error("[IActBiddingBannerService][deleteActBiddingBanner]删除集合竞价首页轮播图时发生异常:"
                      + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IActBiddingBannerService][deleteActBiddingBanner]删除集合竞价首页轮播图时发生异常:", e);
        }
        return result;
    }

    @Override
    public ServiceResult<List<ActBiddingBanner>> getActBiddingBanners(Map<String, String> queryMap,
                                                                      PagerInfo pager) {
        ServiceResult<List<ActBiddingBanner>> serviceResult = new ServiceResult<List<ActBiddingBanner>>();
        serviceResult.setPager(pager);
        try {
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(actBiddingBannerModel.getActBiddingBannersCount(queryMap));
                start = pager.getStart();
                size = pager.getPageSize();
            }
            serviceResult.setResult(actBiddingBannerModel.getActBiddingBanners(queryMap, start,
                size));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[IActBiddingBannerService][getActBiddingBanners]根据条件取得集合竞价首页轮播图时出现异常："
                      + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IActBiddingBannerService][getActBiddingBanners]param1:"
                      + JSON.toJSONString(queryMap) + " &param2:" + JSON.toJSONString(pager));
            log.error("[IActBiddingBannerService][getActBiddingBanners]根据条件取得集合竞价首页轮播图时发生异常:", e);
        }
        return serviceResult;
    }

    /**
     * 查询集合竞价首页轮播图
     * @param pcMobile
     * @return
     * @see com.ejavashop.service.promotion.IActBiddingBannerService#getActBiddingBannersPcMobile(int)
     */
    @Override
    public ServiceResult<List<ActBiddingBanner>> getActBiddingBannersPcMobile(int pcMobile) {
        ServiceResult<List<ActBiddingBanner>> serviceResult = new ServiceResult<List<ActBiddingBanner>>();
        try {
            serviceResult.setResult(actBiddingBannerModel.getActBiddingBannersPcMobile(pcMobile));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[IActBiddingBannerService][getActBiddingBannersPcMobile]根据条件取得集合竞价首页轮播图时出现异常："
                      + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error(
                "[IActBiddingBannerService][getActBiddingBannersPcMobile]根据条件取得集合竞价首页轮播图时发生异常:", e);
        }
        return serviceResult;
    }

}