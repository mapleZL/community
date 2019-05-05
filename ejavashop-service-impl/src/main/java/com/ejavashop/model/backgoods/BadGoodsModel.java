package com.ejavashop.model.backgoods;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ejavashop.core.StringUtil;
import com.ejavashop.dao.shop.read.backgoods.BadGoodsReadDao;
import com.ejavashop.dao.shop.write.backgoods.BadGoodsWriteDao;
import com.ejavashop.entity.backgoods.BadGoods;

@Component
public class BadGoodsModel {

	private static org.apache.log4j.Logger log = org.apache.log4j.LogManager
                                                   .getLogger(BadGoodsModel.class);
    
    @Resource
    private BadGoodsWriteDao badGoodsWriteDao;
    @Resource
    private BadGoodsReadDao badGoodsReadDao;
    
    /**
     * 根据id取得bad_goods对象
     * @param  badGoodsId
     * @return
     */
    public BadGoods getBadGoodsById(Integer badGoodsId) {
    	return badGoodsReadDao.get(badGoodsId);
    }
    
    /**
     * 保存bad_goods对象
     * @param  badGoods
     * @return
     */
     public Integer saveBadGoods(BadGoods badGoods) {
     	this.dbConstrains(badGoods);
     	return badGoodsWriteDao.insert(badGoods);
     }
     
     /**
     * 更新bad_goods对象
     * @param  badGoods
     * @return
     */
     public Integer updateBadGoods(BadGoods badGoods) {
     	this.dbConstrains(badGoods);
     	return badGoodsWriteDao.update(badGoods);
     }
     
     
     public Integer pageCount(Map<String, Object> queryMap) {
         return badGoodsReadDao.getCount(queryMap);
     }

     public List<BadGoods> page(Map<String, Object> queryMap) throws Exception {
    	 return badGoodsReadDao.page(queryMap);
     }
     
     private void dbConstrains(BadGoods badGoods) {
		badGoods.setOrdersSn(StringUtil.dbSafeString(badGoods.getOrdersSn(), false, 50));
		badGoods.setProductSku(StringUtil.dbSafeString(badGoods.getProductSku(), false, 50));
		badGoods.setProductName(StringUtil.dbSafeString(badGoods.getProductName(), false, 255));
		badGoods.setSellerName(StringUtil.dbSafeString(badGoods.getSellerName(), false, 255));
     }
}