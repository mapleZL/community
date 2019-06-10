package com.phkj.service.product;

import java.util.List;
import java.util.Map;

import com.phkj.core.PagerInfo;
import com.phkj.core.ServiceResult;
import com.phkj.entity.product.ProductPicture;
import com.phkj.entity.product.StAppletProduct;

public interface IStAppletProductService {

    /**
     * 根据id取得商品表
     * @param  stAppletProductId
     * @return
     */
    ServiceResult<StAppletProduct> getStAppletProductById(Integer stAppletProductId);

    /**
     * 更新呢或者是保存
     * @param product
     * @param picList 
     * @return
     */
    ServiceResult<Boolean> updateOrCreate(StAppletProduct product, List<ProductPicture> picList);

    /**
     * 校验商品spu是否存在
     * @param queryMap
     * @return
     */
    ServiceResult<Boolean> checkProductBySPUAndSellerId(Map<String, String> queryMap);

    /**
     * 商品列表查询通用方法
     * @param queryMap
     * @param pager
     * @return
     */
    ServiceResult<List<StAppletProduct>> pageProduct(Map<String, String> queryMap, PagerInfo pager);

    /**
     * 删除商品
     * @param id
     * @return
     */
    ServiceResult<Boolean> delProduct(Integer id);

    /**
     * 批量上下架
     * @param map
     * @param string2IntegerList
     */
    ServiceResult<Integer> updateByIds(Map<String, Object> map, List<Integer> string2IntegerList);

    /**
     * 根据id更新商品表
     * @param stAppletProduct
     * @return
     */
    ServiceResult<Integer> update(StAppletProduct stAppletProduct);

    /**
     * 前端查询列表
     * @param start
     * @param pageSize
     * @param productCateId
     * @param villageCode
     * @return
     */
    List<StAppletProduct> list(Integer start, Integer pageSize, Integer productCateId,
                               String villageCode, String search);

    /**
     * 查询总数
     * @param productCateId
     * @param villageCode
     * @return
     */
    Integer count(Integer productCateId, String villageCode, String search);
}