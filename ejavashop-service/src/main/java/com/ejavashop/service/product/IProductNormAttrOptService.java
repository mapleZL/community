package com.ejavashop.service.product;

import java.util.List;
import java.util.Map;

import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.product.ProductNormAttrOpt;

public interface IProductNormAttrOptService {

    /**
     * 根据id取得商品选定的规格属性(保存商品插入，暂时不用)
     * @param  productNormAttrOptId
     * @return
     */
    ServiceResult<ProductNormAttrOpt> getProductNormAttrOptById(Integer productNormAttrOptId);

    /**
     * 保存商品选定的规格属性(保存商品插入，暂时不用)
     * @param  productNormAttrOpt
     * @return
     */
    ServiceResult<Integer> saveProductNormAttrOpt(ProductNormAttrOpt productNormAttrOpt);

    /**
    * 更新商品选定的规格属性(保存商品插入，暂时不用)
    * @param  productNormAttrOpt
    * @return
    */
    ServiceResult<Integer> updateProductNormAttrOpt(ProductNormAttrOpt productNormAttrOpt);

    /**
    * 分页查询
    * @param queryMap
    * @param pager
    * @return
    */
    ServiceResult<List<ProductNormAttrOpt>> page(Map<String, String> queryMap, PagerInfo pager);

    /**
     * 删除
     * @param id
     * @return
     */
    ServiceResult<Boolean> del(Integer id);
}