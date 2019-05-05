package com.ejavashop.model.backgoods;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ejavashop.core.StringUtil;
import com.ejavashop.dao.shop.read.backgoods.BackGoodsRecordReadDao;
import com.ejavashop.dao.shop.write.backgoods.BackGoodsRecordWriteDao;
import com.ejavashop.entity.backgoods.BackGoodsRecord;

@Component(value = "backGoodsRecordModel")
public class BackGoodsRecordModel {

    private static org.apache.log4j.Logger log = org.apache.log4j.LogManager
                                                   .getLogger(BackGoodsRecordModel.class);

    @Resource
    private BackGoodsRecordWriteDao        backGoodsRecordWriteDao;
    @Resource
    private BackGoodsRecordReadDao        backGoodsRecordReadDao;

    /**
     * 根据id取得back_goods_record对象
     * @param  backGoodsRecordId
     * @return
     */
    public BackGoodsRecord getBackGoodsRecordById(Integer backGoodsRecordId) {
        return backGoodsRecordReadDao.get(backGoodsRecordId);
    }

    /**
     * 保存back_goods_record对象
     * @param  backGoodsRecord
     * @return
     */
    public Integer saveBackGoodsRecord(BackGoodsRecord backGoodsRecord) {
        this.dbConstrains(backGoodsRecord);
        return backGoodsRecordWriteDao.insert(backGoodsRecord);
    }

    /**
    * 更新back_goods_record对象
    * @param  backGoodsRecord
    * @return
    */
    public Integer updateBackGoodsRecord(BackGoodsRecord backGoodsRecord) {
        this.dbConstrains(backGoodsRecord);
        return backGoodsRecordWriteDao.update(backGoodsRecord);
    }

    private void dbConstrains(BackGoodsRecord backGoodsRecord) {
        backGoodsRecord.setProductSku(StringUtil.dbSafeString(backGoodsRecord.getProductSku(),
            false, 50));
    }

    public List<BackGoodsRecord> getBackGoodRecordByGoodsId(Integer goodsId) {
        return backGoodsRecordReadDao.getBackGoodRecordByGoodsId(goodsId);
    }
}