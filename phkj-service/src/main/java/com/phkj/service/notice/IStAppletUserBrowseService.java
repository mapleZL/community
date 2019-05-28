package com.phkj.service.notice;

import com.phkj.core.ServiceResult;
import com.phkj.entity.notice.StAppletUserBrowse;

public interface IStAppletUserBrowseService {

    /**
     * 根据id取得st_applet_user_browse对象
     * @param  stAppletUserBrowseId
     * @return
     */
    ServiceResult<StAppletUserBrowse> getStAppletUserBrowseById(Integer stAppletUserBrowseId);

    /**
     * 保存st_applet_user_browse对象
     * @param  stAppletUserBrowse
     * @return
     */
    ServiceResult<Integer> saveStAppletUserBrowse(StAppletUserBrowse stAppletUserBrowse);

    /**
    * 更新st_applet_user_browse对象
    * @param  stAppletUserBrowse
    * @return
    */
    ServiceResult<Integer> updateStAppletUserBrowse(StAppletUserBrowse stAppletUserBrowse);

    /**
     * 更新或者是生成用户的浏览记录
     * @param rId
     * @param memebrId
     */
    void updateOrCreateBrowse(Integer rId, Integer memebrId);

    /**
     * 获取用户的浏览记录
     * @param rId
     * @param memebrId
     * @return
     */
    ServiceResult<StAppletUserBrowse> getUserBrowse(Integer rId, Integer memebrId);

}