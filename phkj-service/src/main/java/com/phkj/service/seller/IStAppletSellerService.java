package com.phkj.service.seller;

import com.phkj.core.ServiceResult;
import com.phkj.entity.seller.StAppletSeller;
import com.phkj.entity.seller.StAppletSellerVO;

public interface IStAppletSellerService {

    /**
     * 根据id取得商家表
     * @param  stAppletSellerId
     * @return
     */
    ServiceResult<StAppletSeller> getStAppletSellerById(Integer stAppletSellerId);

    /**
     * 保存商家表
     * @param  stAppletSeller
     * @return
     */
    ServiceResult<Integer> saveStAppletSeller(StAppletSeller stAppletSeller);

    /**
    * 更新商家表
    * @param  stAppletSeller
    * @return
    */
    ServiceResult<Integer> updateStAppletSeller(StAppletSeller stAppletSeller);

    StAppletSeller getSellerByMemberId(Integer memberId);

    ServiceResult<StAppletSellerVO> getSellerDetail(Integer sellerId);
}