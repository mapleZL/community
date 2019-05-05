package com.ejavashop.model.backgoods;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ejavashop.core.StringUtil;
import com.ejavashop.dao.shop.read.backgoods.BackGoodsReadDao;
import com.ejavashop.dao.shop.write.backgoods.BackGoodsWriteDao;
import com.ejavashop.entity.backgoods.BackGoods;
import com.ejavashop.entity.member.MemberProductBack;

@Component
public class BackGoodsModel {

	private static org.apache.log4j.Logger log = org.apache.log4j.LogManager
                                                   .getLogger(BackGoodsModel.class);
    
    @Resource
    private BackGoodsWriteDao backGoodsWriteDao;
    @Resource
    private BackGoodsReadDao backGoodsReadDao;
    
    /**
     * 根据id取得back_goods对象
     * @param  backGoodsId
     * @return
     */
    public BackGoods getBackGoodsById(Integer backGoodsId) {
    	return backGoodsReadDao.get(backGoodsId);
    }
    
    /**
     * 保存back_goods对象
     * @param  backGoods
     * @return
     */
     public Integer saveBackGoods(BackGoods backGoods) {
     	this.dbConstrains(backGoods);
     	return backGoodsWriteDao.insert(backGoods);
     }
     
     /**
     * 更新back_goods对象
     * @param  backGoods
     * @return
     */
     public Integer updateBackGoods(BackGoods backGoods) {
     	this.dbConstrains(backGoods);
     	return backGoodsWriteDao.update(backGoods);
     }
     
     public Integer pageCount(Map<String, String> queryMap) {
         return backGoodsReadDao.getCount(queryMap);
     }

     public List<BackGoods> page(Map<String, String> queryMap, Integer start, Integer size) {
         List<BackGoods> list = backGoodsReadDao.page(queryMap, start, size);
         return list;
     }
     
     private void dbConstrains(BackGoods backGoods) {
		backGoods.setOrdersSn(StringUtil.dbSafeString(backGoods.getOrdersSn(), false, 32));
		backGoods.setBackMan(StringUtil.dbSafeString(backGoods.getBackMan(), false, 0));
		backGoods.setBackReason(StringUtil.dbSafeString(backGoods.getBackReason(), false, 0));
		backGoods.setCheckMan(StringUtil.dbSafeString(backGoods.getCheckMan(), true, 0));
		backGoods.setCheckReason(StringUtil.dbSafeString(backGoods.getCheckReason(), true, 0));
		backGoods.setMobile(StringUtil.dbSafeString(backGoods.getMobile(), false, 50));
		backGoods.setPhone(StringUtil.dbSafeString(backGoods.getPhone(), false, 50));
		backGoods.setEmail(StringUtil.dbSafeString(backGoods.getEmail(), true, 255));
     }
}