package com.ejavashop.service.operate;

import java.util.List;
import java.util.Map;

import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.operate.ProductPackage;

public interface IProductPackageService {

    /**
     * 根据id取得二次加工套餐设定
     * @param  productPackageId
     * @return
     */
    ServiceResult<ProductPackage> getProductPackageById(Integer productPackageId);

    /**
     * 保存二次加工套餐设定
     * @param  productPackage
     * @return
     */
    ServiceResult<Integer> saveProductPackage(ProductPackage productPackage);

    /**
    * 更新二次加工套餐设定
    * @param  productPackage
    * @return
    */
    ServiceResult<Integer> updateProductPackage(ProductPackage productPackage);

    /**
    * 分页查询
    * @param queryMap
    * @param pager
    * @return
    */
    ServiceResult<List<ProductPackage>> page(Map<String, String> queryMap, PagerInfo pager);
}