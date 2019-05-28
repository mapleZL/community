package com.phkj.model.notice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.phkj.dao.shop.read.notice.StAppletUserBrowseReadDao;
import com.phkj.dao.shop.write.notice.StAppletUserBrowseWriteDao;
import com.phkj.entity.notice.StAppletUserBrowse;

@Component
public class StAppletUserBrowseModel {

    @Autowired
    private StAppletUserBrowseReadDao  stAppletUserBrowsereadDao;
    @Autowired
    private StAppletUserBrowseWriteDao stAppletUserBrowseWriteDao;

    /**
     * 根据id取得st_applet_user_browse对象
     * @param  stAppletUserBrowseId
     * @return
     */
    public StAppletUserBrowse getStAppletUserBrowseById(Integer stAppletUserBrowseId) {
        return stAppletUserBrowsereadDao.get(stAppletUserBrowseId);
    }

    /**
     * 保存st_applet_user_browse对象
     * @param  stAppletUserBrowse
     * @return
     */
    public Integer saveStAppletUserBrowse(StAppletUserBrowse stAppletUserBrowse) {
        return stAppletUserBrowseWriteDao.insert(stAppletUserBrowse);
    }

    /**
    * 更新st_applet_user_browse对象
    * @param  stAppletUserBrowse
    * @return
    */
    public Integer updateStAppletUserBrowse(StAppletUserBrowse stAppletUserBrowse) {
        return stAppletUserBrowseWriteDao.update(stAppletUserBrowse);
    }

    /**
     * 获得用户的浏览信息
     * @param rId
     * @param memebrId
     * @return
     */
    public StAppletUserBrowse getUserBrowse(Integer rId, Integer memebrId) {
        return stAppletUserBrowsereadDao.getUserBrowse(rId, memebrId);
    }

}