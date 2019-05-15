package com.phkj.model.relate;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.phkj.dao.shopm.read.relate.StBaseinfoUnitsDao;
import com.phkj.entity.relate.StBaseinfoUnits;

@Component
public class StBaseinfoUnitsModel {

    @Resource
    private StBaseinfoUnitsDao stBaseinfoUnitsDao;
    
    /**
     * 根据id取得楼幢单元信息
     * @param  stBaseinfoUnitsId
     * @return
     */
    public StBaseinfoUnits getStBaseinfoUnitsById(Long stBaseinfoUnitsId) {
    	return stBaseinfoUnitsDao.get(stBaseinfoUnitsId);
    }
    
    /**
     * 保存楼幢单元信息
     * @param  stBaseinfoUnits
     * @return
     */
     public Integer saveStBaseinfoUnits(StBaseinfoUnits stBaseinfoUnits) {
     	return stBaseinfoUnitsDao.insert(stBaseinfoUnits);
     }
     
     /**
     * 更新楼幢单元信息
     * @param  stBaseinfoUnits
     * @return
     */
     public Integer updateStBaseinfoUnits(StBaseinfoUnits stBaseinfoUnits) {
     	return stBaseinfoUnitsDao.update(stBaseinfoUnits);
     }

    public List<StBaseinfoUnits> getUnits() {
        return stBaseinfoUnitsDao.getUnits();
    }
     
}