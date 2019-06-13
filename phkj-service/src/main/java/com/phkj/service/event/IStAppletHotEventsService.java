package com.phkj.service.event;

import java.util.List;
import java.util.Map;

import com.phkj.core.PagerInfo;
import com.phkj.core.ServiceResult;
import com.phkj.entity.event.StAppletHotEvents;

public interface IStAppletHotEventsService {

    /**
     * 根据id取得st_applet_hot_events对象
     * @param  stAppletHotEventsId
     * @return
     */
    ServiceResult<StAppletHotEvents> getStAppletHotEventsById(Integer stAppletHotEventsId);

    /**
     * 保存st_applet_hot_events对象
     * @param  stAppletHotEvents
     * @return
     */
    ServiceResult<Integer> saveStAppletHotEvents(StAppletHotEvents stAppletHotEvents);

    /**
    * 更新st_applet_hot_events对象
    * @param  stAppletHotEvents
    * @return
    */
    ServiceResult<Integer> updateStAppletHotEvents(StAppletHotEvents stAppletHotEvents);

    /**
     * 获取活动列表
     * @param pager
     * @param dataMap
     * @return
     */
    ServiceResult<List<StAppletHotEvents>> getPageList(PagerInfo pager,
                                                       Map<String, Object> dataMap);

    /**
     * 公众号查询列表
     * @param start
     * @param size
     * @param villageCode
     * @return
     */
    ServiceResult<List<StAppletHotEvents>> wxPage(Integer start, Integer size, String villageCode);

    Integer wxCount(String villageCode);
}