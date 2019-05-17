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
}