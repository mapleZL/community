package com.phkj.model.notice;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.phkj.dao.shop.read.notice.StAppletCollectionManageReadDao;
import com.phkj.dao.shop.write.notice.StAppletCollectionManageWriteDao;
import com.phkj.entity.notice.StAppletCollectionManage;

@Component
public class StAppletCollectionManageModel {

    @Resource
    private StAppletCollectionManageReadDao  stAppletCollectionManageReadDao;
    @Resource
    private StAppletCollectionManageWriteDao stAppletCollectionManageWriteDao;

    /**
     * 根据id取得st_applet_collection_manage对象
     * @param  stAppletCollectionManageId
     * @return
     */
    public StAppletCollectionManage getStAppletCollectionManageById(Integer stAppletCollectionManageId) {
        return stAppletCollectionManageReadDao.get(stAppletCollectionManageId);
    }

    /**
     * 判断用户是否已经收藏该条通告
     * @param memberId
     * @param noticeId
     * @return
     */
    public Integer getCollectionCount(Integer memberId, Long noticeId) {
        return stAppletCollectionManageReadDao.getCollectionCount(memberId, noticeId);
    }

    /**
     * 保存st_applet_collection_manage对象
     * @param  stAppletCollectionManage
     * @return
     */
    public Integer saveStAppletCollectionManage(StAppletCollectionManage stAppletCollectionManage) {
        return stAppletCollectionManageWriteDao.insert(stAppletCollectionManage);
    }

    /**
    * 更新st_applet_collection_manage对象
    * @param  stAppletCollectionManage
    * @return
    */
    public Integer updateStAppletCollectionManage(StAppletCollectionManage stAppletCollectionManage) {
        return stAppletCollectionManageWriteDao.update(stAppletCollectionManage);
    }

    /**
     * 取消收藏
     * @param memberId
     * @param noticeId
     */
    public void cancelCollection(Integer memberId, Long noticeId) {
        stAppletCollectionManageWriteDao.cancelCollection(memberId, noticeId);
    }

    /**
     * 获取某条通告的收藏量
     * @param noticeId
     * @return
     */
    public Long getCountByNoticeid(Long noticeId) {
        return stAppletCollectionManageReadDao.getCountByNoticeid(noticeId);
    }

}