package com.ejavashop.service.product;

import java.util.List;
import java.util.Map;

import com.ejavashop.core.PaginationUtil;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.order.OrdersProduct;
import com.ejavashop.entity.product.ProductGoods;

public interface IProductGoodsService {

    /**
     * 根据商品ID取其下所有货品
     * @param productId
     * @return
     */
    ServiceResult<List<ProductGoods>> getGoodSByProductId(Integer productId);

    /**
    * 保存货品表
    * @param  productGoods
    * @return
    */
    ServiceResult<Boolean> saveProductGoods(ProductGoods productGoods);

    /**
    * 更新货品表
    * @param  productGoods
    * @return
    */
    ServiceResult<Boolean> updateProductGoods(ProductGoods productGoods);

    /**
    * 删除货品表
    * @param  id
    * @return
    */
    ServiceResult<Boolean> delProductGoods(Integer id);

    /**
    * 根据id取得货品表
    * @param productGoodsId
    * @return
    */
    ServiceResult<ProductGoods> getProductGoodsById(Integer productGoodsId);

    /**
     * 分页
     * @param queryMap
     * @param page
     * @return
     */
    ServiceResult<List<ProductGoods>> pageProductGoods(Map<String, String> queryMap,
                                                       PaginationUtil page);
    
    ServiceResult<List<ProductGoods>> quickSearch(Map<String, String> queryMap,
                                                       PaginationUtil page);
    
    ServiceResult<ProductGoods> getProductGoodsByCondition(Map<String, String> queryMap);

    /**
     * 以给定的货品信息批量更新这些货品<br>
     * @return
     */
    ServiceResult<Boolean> updateProductGoods(List<ProductGoods> productgoods);

    /**
     * 以SKU为条件查询<br>
     * @return
     */
    ServiceResult<Boolean> checkProductBySKUAndSeller(Map<String, String> queryMap);

    /**
     * 检查商品编码是否重复<br>
     * @return
     */
    ServiceResult<Boolean> checkBarCodeIsExsit(Map<String, String> queryMap);
	
	/**
	 * 根据sku和货主查询商品信息
	 * @return
	 */
	ServiceResult<List<ProductGoods>>getBySkuAndMember(String sku,String memberId);
	
	
	
	ServiceResult<Boolean>updateSpuStock();

    ServiceResult<List<ProductGoods>> checkProductBySKUAndProductId(Map<String, String> queryMap);
	
	}
