package com.phkj.service.notice;

import com.phkj.core.ServiceResult;
import com.phkj.entity.notice.StBrowse;

public interface IStBrowseService {

    /**
     * 根据id取得st_browse对象
     * @param  stBrowseId
     * @return
     */
    ServiceResult<StBrowse> getStBrowseById(Long stBrowseId);

    /**
     * 保存st_browse对象
     * @param  stBrowse
     * @return
     */
    ServiceResult<Integer> saveStBrowse(StBrowse stBrowse);

    /**
    * 更新st_browse对象
    * @param  stBrowse
    * @return
    */
    ServiceResult<Integer> updateStBrowse(StBrowse stBrowse);

    /**
     * 获取某条头条的浏览量
     * @param id
     * @return
     */
    ServiceResult<StBrowse> getBrowseByNoticeId(Long id);
}