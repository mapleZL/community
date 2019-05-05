package com.ejavashop.service.promotion;

import java.util.List;
import java.util.Map;

import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.promotion.bidding.ActBiddingBanner;

public interface IActBiddingBannerService {

    /**
     * 根据id取得集合竞价首页轮播图
     * @param  pcIndexBannerId
     * @return
     */
    ServiceResult<ActBiddingBanner> getActBiddingBannerById(Integer pcIndexBannerId);

    /**
     * 保存集合竞价首页轮播图
     * @param  pcIndexBanner
     * @return
     */
    ServiceResult<Boolean> saveActBiddingBanner(ActBiddingBanner pcIndexBanner);

    /**
     * 更新集合竞价首页轮播图
     * @param pcIndexBanner
     * @return
     */
    ServiceResult<Boolean> updateActBiddingBanner(ActBiddingBanner pcIndexBanner);

    /**
     * 删除集合竞价首页轮播图
     * @param  pcIndexBanner
     * @return
     */
    ServiceResult<Boolean> deleteActBiddingBanner(Integer pcIndexBannerId);

    /**
     * 根据条件取得集合竞价首页轮播图
     * @param queryMap
     * @param pager
     * @return
     */
    ServiceResult<List<ActBiddingBanner>> getActBiddingBanners(Map<String, String> queryMap,
                                                               PagerInfo pager);

    /**
     * 查询集合竞价首页轮播图
     * @param pcMobilePc
     * @param channel2
     * @return
     */
    ServiceResult<List<ActBiddingBanner>> getActBiddingBannersPcMobile(int pcMobile);

}