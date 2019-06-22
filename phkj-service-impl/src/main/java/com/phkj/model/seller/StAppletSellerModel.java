package com.phkj.model.seller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.phkj.core.StringUtil;
import com.phkj.dao.shop.read.seller.StAppletSellerReadDao;
import com.phkj.dao.shop.write.seller.StAppletSellerWriteDao;
import com.phkj.entity.seller.StAppletSeller;

@Component
public class StAppletSellerModel {

    @Autowired
    private StAppletSellerReadDao stAppletSellerReadDao;
    @Autowired
    private StAppletSellerWriteDao stAppletSellerWriteDao;

    /**
     * 根据id取得商家表
     *
     * @param stAppletSellerId
     * @return
     */
    public StAppletSeller getStAppletSellerById(Integer stAppletSellerId) {
        return stAppletSellerReadDao.get(stAppletSellerId);
    }

    /**
     * 保存商家表
     *
     * @param stAppletSeller
     * @return
     */
    public Integer saveStAppletSeller(StAppletSeller stAppletSeller) {
        this.dbConstrains(stAppletSeller);
        return stAppletSellerWriteDao.insert(stAppletSeller);
    }

    /**
     * 更新商家表
     *
     * @param stAppletSeller
     * @return
     */
    public Integer updateStAppletSeller(StAppletSeller stAppletSeller) {
        this.dbConstrains(stAppletSeller);
        return stAppletSellerWriteDao.update(stAppletSeller);
    }

    private void dbConstrains(StAppletSeller stAppletSeller) {
        stAppletSeller.setName(StringUtil.dbSafeString(stAppletSeller.getName(), false, 50));
        stAppletSeller
                .setSellerName(StringUtil.dbSafeString(stAppletSeller.getSellerName(), false, 200));
        stAppletSeller
                .setSellerLogo(StringUtil.dbSafeString(stAppletSeller.getSellerLogo(), true, 255));
        stAppletSeller
                .setScoreService(StringUtil.dbSafeString(stAppletSeller.getScoreService(), true, 20));
        stAppletSeller.setScoreDeliverGoods(
                StringUtil.dbSafeString(stAppletSeller.getScoreDeliverGoods(), true, 20));
        stAppletSeller.setScoreDescription(
                StringUtil.dbSafeString(stAppletSeller.getScoreDescription(), true, 20));
        stAppletSeller.setSellerKeyword(
                StringUtil.dbSafeString(stAppletSeller.getSellerKeyword(), true, 255));
        stAppletSeller
                .setSellerDes(StringUtil.dbSafeString(stAppletSeller.getSellerDes(), true, 255));
        stAppletSeller
                .setStoreSlide(StringUtil.dbSafeString(stAppletSeller.getStoreSlide(), true, 65535));
        stAppletSeller
                .setSellerCode(StringUtil.dbSafeString(stAppletSeller.getSellerCode(), true, 50));
        stAppletSeller
                .setTaxLicense(StringUtil.dbSafeString(stAppletSeller.getTaxLicense(), true, 500));
        stAppletSeller
                .setOrganization(StringUtil.dbSafeString(stAppletSeller.getOrganization(), true, 500));
        stAppletSeller.setBussinessLicense(
                StringUtil.dbSafeString(stAppletSeller.getBussinessLicense(), true, 500));
    }

    public StAppletSeller getSellerByMemberId(Integer memebrId) {
        return stAppletSellerReadDao.getSellerByMemberId(memebrId);
    }

    public StAppletSeller getSellerById(Integer sellerId) {
        return stAppletSellerReadDao.get(sellerId);
    }
}