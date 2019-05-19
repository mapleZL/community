package com.phkj.service.mindex;

import com.phkj.core.PagerInfo;
import com.phkj.core.ServiceResult;
import com.phkj.entity.mindex.MIndexBanner;

import java.util.List;
import java.util.Map;


public interface IMIndexService {

    /**
     * 根据id取得移动端首页轮播图
     *
     * @param mIndexBannerId
     * @return
     */
    ServiceResult<MIndexBanner> getMIndexBannerById(Integer mIndexBannerId);

    /**
     * 保存移动端首页轮播图
     *
     * @param mIndexBanner
     * @return
     */
    ServiceResult<Boolean> saveMIndexBanner(MIndexBanner mIndexBanner);

    /**
     * 更新移动端首页轮播图
     *
     * @param mIndexBanner
     * @return
     */
    ServiceResult<Boolean> updateMIndexBanner(MIndexBanner mIndexBanner);

    /**
     * 删除移动端首页轮播图
     *
     * @param mIndexBannerId
     * @return
     */
    ServiceResult<Boolean> deleteMIndexBanner(Integer mIndexBannerId);

    /**
     * 根据条件取得移动端首页轮播图
     *
     * @param queryMap
     * @param pager
     * @return
     */
    ServiceResult<List<MIndexBanner>> getMIndexBanners(Map<String, String> queryMap,
                                                       PagerInfo pager);

    /**
     * 取得移动端首页轮播图<br>
     * <li>如果isPreview=true取所有轮播图
     * <li>如果isPreview=false取使用状态且当前时间在展示时间范围内的轮播图
     *
     * @param isPreview
     * @return
     */
    ServiceResult<List<MIndexBanner>> getMIndexBannerForView(Boolean isPreview);

    ServiceResult<MIndexBanner> getPopupForView(String type);

}