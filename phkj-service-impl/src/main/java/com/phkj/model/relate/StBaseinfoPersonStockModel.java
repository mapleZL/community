package com.phkj.model.relate;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.phkj.dao.shopm.read.relate.StBaseinfoPersonStockDao;
import com.phkj.entity.relate.StBaseinfoPersonStock;

@Component
public class StBaseinfoPersonStockModel {

    @Resource
    private StBaseinfoPersonStockDao stBaseinfoPersonStockDao;

    /**
     * 根据id取得人员库
     * @param  stBaseinfoPersonStockId
     * @return
     */
    public StBaseinfoPersonStock getStBaseinfoPersonStockById(Long stBaseinfoPersonStockId) {
        return stBaseinfoPersonStockDao.get(stBaseinfoPersonStockId);
    }

    /**
     * 保存人员库
     * @param  stBaseinfoPersonStock
     * @return
     */
    public Integer saveStBaseinfoPersonStock(StBaseinfoPersonStock stBaseinfoPersonStock) {
        return stBaseinfoPersonStockDao.insert(stBaseinfoPersonStock);
    }

    /**
    * 更新人员库
    * @param  stBaseinfoPersonStock
    * @return
    */
    public Integer updateStBaseinfoPersonStock(StBaseinfoPersonStock stBaseinfoPersonStock) {
        return stBaseinfoPersonStockDao.update(stBaseinfoPersonStock);
    }

    public StBaseinfoPersonStock getStBaseinfoPersonStock(String phone) {
        return stBaseinfoPersonStockDao.getStBaseinfoPersonStock(phone);
    }

}