package com.phkj.model.notice;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.phkj.dao.shop.read.notice.StBrowseReadDao;
import com.phkj.dao.shop.write.notice.StBrowseWriteDao;
import com.phkj.entity.notice.StBrowse;
/**
 * 
 *                       
 * @Filename: StBrowseModel.java
 * @Version: 1.0
 * @date: 2019年5月17日
 * @Author: 陆帅 * @Email: lu1632278229@sina.cn
 *
 */
@Component
public class StBrowseModel {

    @Resource
    private StBrowseReadDao  stBrowseReadDao;
    @Resource
    private StBrowseWriteDao stBrowseWriteDao;

    /**
     * 根据id取得st_browse对象
     * @param  stBrowseId
     * @return
     */
    public StBrowse getStBrowseById(Long stBrowseId) {
        return stBrowseReadDao.get(stBrowseId);
    }

    /**
     * 保存st_browse对象
     * @param  stBrowse
     * @return
     */
    public Integer saveStBrowse(StBrowse stBrowse) {
        return stBrowseWriteDao.insert(stBrowse);
    }

    /**
    * 更新st_browse对象
    * @param  stBrowse
    * @return
    */
    public Integer updateStBrowse(StBrowse stBrowse) {
        return stBrowseWriteDao.update(stBrowse);
    }

    /**
     * 获取保存的流量信息
     * @param id
     * @return
     */
    public StBrowse getBrowseByNoticeId(Long noticeId) {
        return stBrowseReadDao.getBrowseByNoticeId(noticeId);
    }

}