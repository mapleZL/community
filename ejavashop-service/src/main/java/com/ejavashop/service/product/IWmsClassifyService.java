package com.ejavashop.service.product;

import java.util.List;
import java.util.Map;

import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.product.WmsClassify;

public interface IWmsClassifyService {

	/**
     * 根据id取得wms_classify对象
     * @param  wmsClassifyId
     * @return
     */
    ServiceResult<WmsClassify> getWmsClassifyById(Integer wmsClassifyId);
    
    /**
     * 保存wms_classify对象
     * @param  wmsClassify
     * @return
     */
     ServiceResult<Integer> saveWmsClassify(WmsClassify wmsClassify);
     
     /**
     * 更新wms_classify对象
     * @param  wmsClassify
     * @return
     */
     ServiceResult<Integer> updateWmsClassify(WmsClassify wmsClassify);

     ServiceResult<List<WmsClassify>> page(Map<String, String> queryMap, PagerInfo pager);

     ServiceResult<List<WmsClassify>> getWmsCategoryList(Integer state);
}