package com.phkj.model.relate;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.phkj.dao.shopm.read.relate.StBaseinfoResidentCarportDao;
import com.phkj.entity.relate.StBaseinfoResidentCarport;

@Component
public class StBaseinfoResidentCarportModel {

    @Resource
    private StBaseinfoResidentCarportDao stBaseinfoResidentCarportDao;

    /**
     * 根据id取得居民车位表
     * @param  stBaseinfoResidentCarportId
     * @return
     */
    public StBaseinfoResidentCarport getStBaseinfoResidentCarportById(Long stBaseinfoResidentCarportId) {
        return stBaseinfoResidentCarportDao.get(stBaseinfoResidentCarportId);
    }

    /**
     * 保存居民车位表
     * @param  stBaseinfoResidentCarport
     * @return
     */
    public Integer saveStBaseinfoResidentCarport(StBaseinfoResidentCarport stBaseinfoResidentCarport) {
        return stBaseinfoResidentCarportDao.insert(stBaseinfoResidentCarport);
    }

    /**
    * 更新居民车位表
    * @param  stBaseinfoResidentCarport
    * @return
    */
    public Integer updateStBaseinfoResidentCarport(StBaseinfoResidentCarport stBaseinfoResidentCarport) {
        return stBaseinfoResidentCarportDao.update(stBaseinfoResidentCarport);
    }

    /**
     * 获取居民车位列表
     * @param residentinfoId
     * @return
     */
    public List<StBaseinfoResidentCarport> getCarportList(Long residentinfoId) {
        return stBaseinfoResidentCarportDao.getCarportList(residentinfoId);
    }

}