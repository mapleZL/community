package com.phkj.service.notice;

import java.util.List;

import com.phkj.core.ServiceResult;
import com.phkj.entity.notice.StAppletCollectionManage;

/**
 * 
 *                       
 * @Filename: IStAppletCollectionManageService.java
 * @Version: 1.0
 * @date: 2019年5月17日
 * @Author: 陆帅 * @Email: lu1632278229@sina.cn
 *
 */
public interface IStAppletCollectionManageService {

    /**
     * 根据id取得st_applet_collection_manage对象
     * @param  stAppletCollectionManageId
     * @return
     */
    ServiceResult<StAppletCollectionManage> getStAppletCollectionManageById(Integer stAppletCollectionManageId);

    /**
     * 根据用户信息判断是否已经收藏
     * @param memberId
     * @param noticeId
     * @return
     */
    ServiceResult<Integer> getCollectionCount(Integer memberId, Long noticeId);

    /**
     * 保存st_applet_collection_manage对象
     * @param  stAppletCollectionManage
     * @return
     */
    ServiceResult<Integer> saveStAppletCollectionManage(StAppletCollectionManage stAppletCollectionManage);

    /**
    * 更新st_applet_collection_manage对象
    * @param  stAppletCollectionManage
    * @return
    */
    ServiceResult<Integer> updateStAppletCollectionManage(StAppletCollectionManage stAppletCollectionManage);

    /**
     * 取消收藏
     * @param memberId
     * @param noticeId
     */
    Boolean cancelCollection(Integer memberId, Long noticeId);

    /**
     * 获取收藏量
     * @param noticeId
     * @return
     */
    ServiceResult<Long> getCountByNoticeid(Long noticeId);

    /**
     * 获取收藏的列表
     * @param memberId
     * @param pageSize 
     * @param start 
     * @param villageCode 
     * @return
     */
    ServiceResult<List<StAppletCollectionManage>> getCollectionList(Integer memberId, Integer start, Integer pageSize, String villageCode);

    /**
     * 获取数量
     * @param memberId
     * @param villageCode
     * @return
     */
    Integer getCount(Integer memberId, String villageCode);
}