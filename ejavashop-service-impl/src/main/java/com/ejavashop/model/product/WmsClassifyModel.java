package com.ejavashop.model.product;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ejavashop.core.StringUtil;
import com.ejavashop.dao.shop.read.product.WmsClassifyReadDao;
import com.ejavashop.dao.shop.write.product.WmsClassifyWriteDao;
import com.ejavashop.entity.product.OrderReceipt;
import com.ejavashop.entity.product.WmsClassify;
import com.ejavashop.entity.seller.Seller;
import com.ejavashop.entity.system.SystemAdmin;

@Component
public class WmsClassifyModel {

	private static org.apache.log4j.Logger log = org.apache.log4j.LogManager
                                                   .getLogger(WmsClassifyModel.class);
    
    @Resource
    private WmsClassifyReadDao wmsClassifyReadDao;
    
    @Resource
    private WmsClassifyWriteDao wmsClassifyWriteDao;
    
    /**
     * 根据id取得wms_classify对象
     * @param  wmsClassifyId
     * @return
     */
    public WmsClassify getWmsClassifyById(Integer wmsClassifyId) {
    	return wmsClassifyReadDao.get(wmsClassifyId);
    }
    
    /**
     * 保存wms_classify对象
     * @param  wmsClassify
     * @return
     */
     public Integer saveWmsClassify(WmsClassify wmsClassify) {
     	this.dbConstrains(wmsClassify);
     	return wmsClassifyWriteDao.insert(wmsClassify);
     }
     
     /**
     * 更新wms_classify对象
     * @param  wmsClassify
     * @return
     */
     public Integer updateWmsClassify(WmsClassify wmsClassify) {
     	this.dbConstrains(wmsClassify);
     	return wmsClassifyWriteDao.update(wmsClassify);
     }
     
     private void dbConstrains(WmsClassify wmsClassify) {
		wmsClassify.setWmsCategory(StringUtil.dbSafeString(wmsClassify.getWmsCategory(), true, 255));
     }

    public int pageCount(Map<String, Object> param) {
        return wmsClassifyReadDao.getCount(param);
    }

    public List<WmsClassify> page(Map<String, Object> param) {
        return wmsClassifyReadDao.page(param);
    }

    public List<WmsClassify> getWmsCategoryList(Integer state) {
        return wmsClassifyReadDao.getWmsCategoryList(state);
    }
}