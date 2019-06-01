package com.phkj.model.repair;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.phkj.core.StringUtil;
import com.phkj.dao.shop.read.repaire.StAppletRepairReadDao;
import com.phkj.dao.shop.write.repaire.StAppletRepairWriteDao;
import com.phkj.entity.repair.StAppletRepair;

@Component
public class StAppletRepairModel {

    @Resource
    private StAppletRepairWriteDao stAppletRepairWriteDao;
    @Resource
    private StAppletRepairReadDao stAppletRepairReadDao;

    /**
     * 根据id取得st_applet_repair对象
     *
     * @param stAppletRepairId
     * @return
     */
    public StAppletRepair getStAppletRepairById(Integer stAppletRepairId) {
        return stAppletRepairReadDao.get(stAppletRepairId);
    }

    /**
     * 保存st_applet_repair对象
     *
     * @param stAppletRepair
     * @return
     */
    public Integer saveStAppletRepair(StAppletRepair stAppletRepair) {
        this.dbConstrains(stAppletRepair);
        stAppletRepairWriteDao.insert(stAppletRepair);
        return stAppletRepair.getId();
    }

    /**
     * 更新st_applet_repair对象
     *
     * @param stAppletRepair
     * @return
     */
    public Integer updateStAppletRepair(StAppletRepair stAppletRepair) {
        this.dbConstrains(stAppletRepair);
        return stAppletRepairWriteDao.update(stAppletRepair);
    }

    private void dbConstrains(StAppletRepair stAppletRepair) {
        stAppletRepair.setCommunityName(StringUtil.dbSafeString(stAppletRepair.getCommunityName(), true, 255));
        stAppletRepair.setHouseName(StringUtil.dbSafeString(stAppletRepair.getHouseName(), true, 50));
        stAppletRepair.setCreateUserId(StringUtil.dbSafeString(stAppletRepair.getCreateUserId(), true, 30));
        stAppletRepair.setUserName(StringUtil.dbSafeString(stAppletRepair.getUserName(), true, 30));
        stAppletRepair.setExamineId(StringUtil.dbSafeString(stAppletRepair.getExamineId(), true, 255));
        stAppletRepair.setRepairId(StringUtil.dbSafeString(stAppletRepair.getRepairId(), true, 30));
        stAppletRepair.setType(StringUtil.dbSafeString(stAppletRepair.getType(), true, 20));
        stAppletRepair.setTitle(StringUtil.dbSafeString(stAppletRepair.getTitle(), true, 50));
        stAppletRepair.setTelPhone(StringUtil.dbSafeString(stAppletRepair.getTelPhone(), true, 11));
        stAppletRepair.setDetail(StringUtil.dbSafeString(stAppletRepair.getDetail(), true, 255));
        stAppletRepair.setImg(StringUtil.dbSafeString(stAppletRepair.getImg(), true, 65535));
    }

    /**
     * create by: zl
     * description: 查询报修记录列表
     * create time:
     *
     * @return
     * @Param: createUserId
     * @Param: pageNum
     * @Param: pageSize
     */
    public List<StAppletRepair> getStAppletRepairList(String createUserId, String villageCode, int pageNum, int pageSize) {
        return stAppletRepairReadDao.getStAppletRepairList(createUserId,villageCode, pageNum, pageSize);
    }

    public int getRepairtCount(Map<String, String> queryMap) {
        return stAppletRepairReadDao.getRepairCount(queryMap);
    }

    public List<StAppletRepair> getRepairList(Map<String, String> queryMap, Integer start,
                                                    Integer size) {
        return stAppletRepairReadDao.getRepairList(queryMap, start, size);
    }
}