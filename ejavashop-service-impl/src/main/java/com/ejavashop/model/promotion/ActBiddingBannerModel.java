package com.ejavashop.model.promotion;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ejavashop.dao.promotion.read.bidding.ActBiddingBannerReadDao;
import com.ejavashop.dao.promotion.write.bidding.ActBiddingBannerWriteDao;
import com.ejavashop.entity.promotion.bidding.ActBiddingBanner;

@Component(value = "ActBiddingBannerModel")
public class ActBiddingBannerModel {

    @Resource
    private ActBiddingBannerReadDao  actBiddingBannerReadDao;

    @Resource
    private ActBiddingBannerWriteDao actBiddingBannerWriteDao;

    /**
     * 根据id和渠道取得集合竞价首页轮播图
     * @param  ActBiddingBannerId
     * @return
     */
    public ActBiddingBanner getActBiddingBannerById(Integer actBiddingBannerId) {
        return actBiddingBannerReadDao.get(actBiddingBannerId);
    }

    /**
     * 保存团过首页轮播图
     * @param  actBiddingBanner
     * @return
     */
    public boolean saveActBiddingBanner(ActBiddingBanner actBiddingBanner) {
        return actBiddingBannerWriteDao.insert(actBiddingBanner) > 0;
    }

    /**
     * 更新集合竞价首页轮播图
     * @param actBiddingBanner
     * @return
     */
    public boolean updateActBiddingBanner(ActBiddingBanner actBiddingBanner) {
        return actBiddingBannerWriteDao.update(actBiddingBanner) > 0;
    }

    /**
     * 删除集合竞价首页轮播图
     * @param actBiddingBanner
     * @return
     */
    public boolean deleteActBiddingBanner(Integer actBiddingBannerId) {
        return actBiddingBannerWriteDao.delete(actBiddingBannerId) > 0;
    }

    public Integer getActBiddingBannersCount(Map<String, String> queryMap) {
        return actBiddingBannerReadDao.getActBiddingBannersCount(queryMap);
    }

    public List<ActBiddingBanner> getActBiddingBanners(Map<String, String> queryMap, Integer start,
                                                       Integer size) {
        return actBiddingBannerReadDao.getActBiddingBanners(queryMap, start, size);
    }

    /**
     * 查询集合竞价首页轮播图
     * @param pcMobile
     * @return
     */
    public List<ActBiddingBanner> getActBiddingBannersPcMobile(int pcMobile) {
        return actBiddingBannerReadDao.getActBiddingBannersPcMobile(pcMobile);
    }

}