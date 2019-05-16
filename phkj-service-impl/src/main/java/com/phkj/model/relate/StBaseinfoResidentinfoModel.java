package com.phkj.model.relate;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.phkj.dao.shopm.read.relate.StBaseinfoResidentinfoDao;
import com.phkj.entity.relate.StBaseinfoResidentinfo;

@Component
public class StBaseinfoResidentinfoModel {

    @Resource
    private StBaseinfoResidentinfoDao stBaseinfoResidentinfoDao;

    /**
     * 根据id取得居民信息
     * @param  stBaseinfoResidentinfoId
     * @return
     */
    public StBaseinfoResidentinfo getStBaseinfoResidentinfoById(Long stBaseinfoResidentinfoId) {
        return stBaseinfoResidentinfoDao.get(stBaseinfoResidentinfoId);
    }

    /**
     * 保存居民信息
     * @param  stBaseinfoResidentinfo
     * @return
     */
    public Integer saveStBaseinfoResidentinfo(StBaseinfoResidentinfo stBaseinfoResidentinfo) {
        return stBaseinfoResidentinfoDao.insert(stBaseinfoResidentinfo);
    }

    /**
    * 更新居民信息
    * @param  stBaseinfoResidentinfo
    * @return
    */
    public Integer updateStBaseinfoResidentinfo(StBaseinfoResidentinfo stBaseinfoResidentinfo) {
        return stBaseinfoResidentinfoDao.update(stBaseinfoResidentinfo);
    }

    /**
     * 查询居民信息
     * @param phone
     * @return
     */
    public StBaseinfoResidentinfo getResidentinfo(String phone) {
        return stBaseinfoResidentinfoDao.getResidentinfo(phone);
    }

}