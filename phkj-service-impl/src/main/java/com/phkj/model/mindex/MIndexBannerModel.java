package com.phkj.model.mindex;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.phkj.core.TimeUtil;
import com.phkj.dao.shop.read.mindex.MIndexBannerReadDao;
import com.phkj.dao.shop.write.mindex.MIndexBannerWriteDao;
import com.phkj.entity.mindex.MIndexBanner;
import org.springframework.stereotype.Component;

@Component(value = "mIndexBannerModel")
public class MIndexBannerModel {

    @Resource
    private MIndexBannerReadDao mIndexBannerReadDao;
    @Resource
    private MIndexBannerWriteDao mIndexBannerWriteDao;

    /**
     * 根据id取得移动端首页轮播图
     * @param  mIndexBannerId
     * @return
     */
    public MIndexBanner getMIndexBannerById(Integer mIndexBannerId) {
        return mIndexBannerReadDao.get(mIndexBannerId);
    }

    /**
     * 保存移动端首页轮播图
     * @param  mIndexBanner
     * @return
     */
    public boolean saveMIndexBanner(MIndexBanner mIndexBanner) {
        return mIndexBannerWriteDao.insert(mIndexBanner) > 0;
    }

    /**
     * 更新移动端首页轮播图
     * @param mIndexBanner
     * @return
     */
    public boolean updateMIndexBanner(MIndexBanner mIndexBanner) {
        return mIndexBannerWriteDao.update(mIndexBanner) > 0;
    }

    /**
     * 删除移动端首页轮播图
     * @param mIndexBannerId
     * @return
     */
    public boolean deleteMIndexBanner(Integer mIndexBannerId) {
        return mIndexBannerWriteDao.delete(mIndexBannerId) > 0;
    }

    public Integer getMIndexBannersCount(Map<String, String> queryMap) {
        return mIndexBannerReadDao.getMIndexBannersCount(queryMap);
    }

    public List<MIndexBanner> getMIndexBanners(Map<String, String> queryMap, Integer start,
                                               Integer size) {
        return mIndexBannerReadDao.getMIndexBanners(queryMap, start, size);
    }
    
    public List<MIndexBanner> getMIndexBanners(Map<String, String> queryMap) {
        return mIndexBannerReadDao.getMIndexBanners(queryMap, 0, 10);
    }

    /**
     * 取得移动端首页轮播图<br>
     * <li>如果isPreview=true取所有轮播图
     * <li>如果isPreview=false取使用状态且当前时间在展示时间范围内的轮播图
     * 
     * @param isPreview
     * @return
     */
    public List<MIndexBanner> getMIndexBannerForView(Boolean isPreview) {
        Map<String, String> queryMap = new HashMap<String, String>();
        if (queryMap != null && !isPreview) {
            queryMap.put("q_status", MIndexBanner.STATUS_1 + "");
        }
        queryMap.put("q_time", TimeUtil.getDateTimeString(new Date()));
        return mIndexBannerReadDao.getMIndexBannersForView(queryMap);
    }
}