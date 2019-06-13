package com.phkj.model.event;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.phkj.core.PagerInfo;
import com.phkj.core.StringUtil;
import com.phkj.dao.shop.read.event.StAppletHotEventsReadDao;
import com.phkj.dao.shop.write.event.StAppletHotEventsWriteDao;
import com.phkj.entity.event.StAppletHotEvents;

@Component
public class StAppletHotEventsModel {

    @Resource
    private StAppletHotEventsReadDao  stAppletHotEventsReadDao;
    @Resource
    private StAppletHotEventsWriteDao stAppletHotEventsWriteDao;

    /**
     * 根据id取得st_applet_hot_events对象
     * @param  stAppletHotEventsId
     * @return
     */
    public StAppletHotEvents getStAppletHotEventsById(Integer stAppletHotEventsId) {
        return stAppletHotEventsReadDao.get(stAppletHotEventsId);
    }

    /**
     * 保存st_applet_hot_events对象
     * @param  stAppletHotEvents
     * @return
     */
    public Integer saveStAppletHotEvents(StAppletHotEvents stAppletHotEvents) {
        this.dbConstrains(stAppletHotEvents);
        return stAppletHotEventsWriteDao.insert(stAppletHotEvents);
    }

    /**
    * 更新st_applet_hot_events对象
    * @param  stAppletHotEvents
    * @return
    */
    public Integer updateStAppletHotEvents(StAppletHotEvents stAppletHotEvents) {
        this.dbConstrains(stAppletHotEvents);
        return stAppletHotEventsWriteDao.update(stAppletHotEvents);
    }

    private void dbConstrains(StAppletHotEvents stAppletHotEvents) {
        stAppletHotEvents
            .setTitle(StringUtil.dbSafeString(stAppletHotEvents.getTitle(), true, 500));
        stAppletHotEvents
            .setContent(StringUtil.dbSafeString(stAppletHotEvents.getContent(), true, 65535));
        stAppletHotEvents.setType(StringUtil.dbSafeString(stAppletHotEvents.getType(), true, 10));
        stAppletHotEvents
            .setSourceType(StringUtil.dbSafeString(stAppletHotEvents.getSourceType(), true, 10));
        stAppletHotEvents
            .setReceiveType(StringUtil.dbSafeString(stAppletHotEvents.getReceiveType(), true, 10));
        stAppletHotEvents
            .setVillageCode(StringUtil.dbSafeString(stAppletHotEvents.getVillageCode(), true, 20));
    }

    public List<StAppletHotEvents> pageList(PagerInfo pager, Map<String, Object> dataMap) {
        List<StAppletHotEvents> list = new ArrayList<>();
        Integer start = 0, size = 0;
        if (pager != null) {
            pager.setRowsCount(stAppletHotEventsReadDao.count(dataMap));
            start = pager.getStart();
            size = pager.getPageSize();
        }
        list = stAppletHotEventsReadDao.pageList(dataMap, start, size);
        return list;
    }

    public List<StAppletHotEvents> wxPage(Integer start, Integer size, String villageCode) {
        return stAppletHotEventsReadDao.wxPage(start, size, villageCode);
    }

    public Integer wxCount(String villageCode) {
        return stAppletHotEventsReadDao.wxCount(villageCode);
    }
}