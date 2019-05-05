package com.ejavashop.service.operate;

import java.util.List;
import java.util.Map;

import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.operate.ProductLabel;

public interface IProductLabelService {

    /**
     * 根据id取得标签管理
     * @param  productLabelId
     * @return
     */
    ServiceResult<ProductLabel> getProductLabelById(Integer productLabelId);

    /**
     * 保存标签管理
     * @param  productLabel
     * @return
     */
    ServiceResult<Integer> saveProductLabel(ProductLabel productLabel);

    /**
    * 更新标签管理
    * @param  productLabel
    * @return
    */
    ServiceResult<Integer> updateProductLabel(ProductLabel productLabel);

    /**
    * 分页查询
    * @param queryMap
    * @param pager
    * @return
    */
    ServiceResult<List<ProductLabel>> page(Map<String, String> queryMap, PagerInfo pager);

    /**
     * 获得所有标签名称即辅料
     * @param queryMap
     * @param pager
     * @return
     */
    ServiceResult<List<ProductLabel>> getProductLabelName();
    /**
     * 根据sku_other值，来动态获得对应的辅料name
     * @param queryMap
     * @param pager
     * @return
     */
    ServiceResult<List<ProductLabel>> getProductLabelNameByskuother(String[] skuothers);
}