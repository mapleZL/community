package com.phkj.service.notice;

import java.util.List;
import java.util.Map;

import com.phkj.core.PagerInfo;
import com.phkj.core.ServiceResult;
import com.phkj.entity.notice.StAppletActivitySign;

public interface IStAppletActivitySignService {

    /**
     * 根据id取得st_applet_activity_sign对象
     * @param  stAppletActivitySignId
     * @return
     */
    ServiceResult<StAppletActivitySign> getStAppletActivitySignById(Integer stAppletActivitySignId);

    /**
     * 获取活动列表
     * @param queryMap
     * @param start
     * @param size
     * @return
     */
    ServiceResult<List<StAppletActivitySign>> list(Map<String, String> queryMap, PagerInfo pager);

    /**
     * 保存st_applet_activity_sign对象
     * @param  stAppletActivitySign
     * @return
     */
    ServiceResult<Integer> saveStAppletActivitySign(StAppletActivitySign stAppletActivitySign);

    /**
    * 更新st_applet_activity_sign对象
    * @param  stAppletActivitySign
    * @return
    */
    ServiceResult<Integer> updateStAppletActivitySign(StAppletActivitySign stAppletActivitySign);

    /**
     * 根据用户查询对象
     * @param memberId
     * @param rActivityId
     * @return
     */
    ServiceResult<StAppletActivitySign> getActivityByUser(Integer memberId, Integer rActivityId);

    /**
     * 根据用户查询活动列表
     * @param memberId
     * @param pageSize 
     * @param start 
     * @param villageCode 
     * @return
     */
    ServiceResult<List<StAppletActivitySign>> getParticipateList(Integer memberId, Integer start, Integer pageSize, String villageCode);

    Integer getCount(Integer memberId, String villageCode);
}