package com.phkj.model.cart;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.phkj.dao.shop.read.cart.StAppletCartReadDao;
import com.phkj.dao.shop.write.cart.StAppletCartWriteDao;
import com.phkj.entity.cart.StAppletCart;

@Component
public class StAppletCartModel {

    @Autowired
    private StAppletCartReadDao            stAppletCartReadDao;
    @Autowired
    private StAppletCartWriteDao           stAppletCartWriteDao;

    /**
     * 根据id取得商城购物车
     * @param  stAppletCartId
     * @return
     */
    public StAppletCart getStAppletCartById(Integer stAppletCartId) {
        return stAppletCartReadDao.get(stAppletCartId);
    }

    /**
     * 保存商城购物车
     * @param  stAppletCart
     * @return
     */
    public Integer saveStAppletCart(StAppletCart stAppletCart) {
        return stAppletCartWriteDao.insert(stAppletCart);
    }

    /**
    * 更新商城购物车
    * @param  stAppletCart
    * @return
    */
    public Integer updateStAppletCart(StAppletCart stAppletCart) {
        return stAppletCartWriteDao.update(stAppletCart);
    }

    /**
     * 删除商品
     * @param id
     */
    public void delCartById(Integer id) {
        stAppletCartWriteDao.delCartById(id);
    }

    public List<StAppletCart> list(Integer memberId, String villageCode, Integer start,
                                   Integer pageSize) {
        return stAppletCartReadDao.list(memberId, villageCode, start, pageSize);
    }
    
    public Integer count(Integer memberId, String villageCode){
        return stAppletCartReadDao.count(memberId, villageCode);
    }

}