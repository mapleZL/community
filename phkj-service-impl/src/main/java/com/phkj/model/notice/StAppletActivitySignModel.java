package com.phkj.model.notice;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.phkj.dao.shop.read.notice.StAppletActivitySignReadDao;
import com.phkj.dao.shop.write.notice.StAppletActivitySignWriteDao;
import com.phkj.entity.notice.StAppletActivitySign;

/**
 * 
 *                       
 * @Filename: StAppletActivitySignModel.java
 * @Version: 1.0
 * @date: 2019年5月17日
 * @Author: 陆帅 * @Email: lu1632278229@sina.cn
 *
 */
@Component
public class StAppletActivitySignModel {

    @Autowired
    private StAppletActivitySignReadDao  stAppletActivitySignReadDao;
    @Autowired
    private StAppletActivitySignWriteDao stAppletActivitySignWriteDao;

    /**
     * 根据id取得st_applet_activity_sign对象
     * @param  stAppletActivitySignId
     * @return
     */
    public StAppletActivitySign getStAppletActivitySignById(Integer stAppletActivitySignId) {
        return stAppletActivitySignReadDao.get(stAppletActivitySignId);
    }

    /**
     * 查询报名活动列表
     * @param queryMap
     * @param start
     * @param size
     * @return
     */
    public List<StAppletActivitySign> list(Map<String, String> queryMap, Integer start,
                                           Integer size) {
        return stAppletActivitySignReadDao.list(queryMap, start, size);
    }

    /**
     * 保存st_applet_activity_sign对象
     * @param  stAppletActivitySign
     * @return
     */
    public Integer saveStAppletActivitySign(StAppletActivitySign stAppletActivitySign) {
        return stAppletActivitySignWriteDao.insert(stAppletActivitySign);
    }

    /**
    * 更新st_applet_activity_sign对象
    * @param  stAppletActivitySign
    * @return
    */
    public Integer updateStAppletActivitySign(StAppletActivitySign stAppletActivitySign) {
        return stAppletActivitySignWriteDao.update(stAppletActivitySign);
    }

    /**
     * 获取总数量
     * @param queryMap
     * @return
     */
    public Integer getActivitySignCount(Map<String, String> queryMap) {
        return stAppletActivitySignReadDao.getActivitySignCount(queryMap);
    }

    /**
     * 获取参与活动详情
     * @param memberId
     * @param rActivityId
     * @return
     */
    public StAppletActivitySign getActivityByUser(Integer memberId, Integer rActivityId) {
        return stAppletActivitySignReadDao.getActivityByUser(memberId, rActivityId);
    }

    /**
     * 查询列表
     * @param memberId
     * @param pageSize 
     * @param start 
     * @param villageCode 
     * @return
     */
    public List<StAppletActivitySign> getParticipateList(Integer memberId, Integer start, Integer pageSize, String villageCode) {
        return stAppletActivitySignReadDao.getParticipateList(memberId, start, pageSize, villageCode);
    }

    public Integer getCount(Integer memberId, String villageCode) {
        return stAppletActivitySignReadDao.getCount(memberId, villageCode);
    }
}