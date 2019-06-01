package com.phkj.service.relate;

import java.util.List;

import com.phkj.core.ServiceResult;
import com.phkj.entity.relate.StNoticeBulletinReleaseManage;

public interface IStNoticeBulletinReleaseManageService {

    /**
     * 根据id通告信息
     * @param  id
     * @return
     */
    ServiceResult<StNoticeBulletinReleaseManage> getNoticeById(Long id);

    /**
     * 获取通告列表
     * @param villageCode 
     * @param  stBaseinfoBuilding
     * @return
     */
    ServiceResult<List<StNoticeBulletinReleaseManage>> pageList(Integer start, Integer pageSize,
                                                                String type, String villageCode);

    /**
     * 获取总数
     * @param type
     * @return
     */
    Integer getCount(String type, String villageCode);
}