package com.ejavashop.service.product;

import java.util.List;
import java.util.Map;

import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.product.ProductBuyStock;
import com.ejavashop.entity.product.ProductGoods;

public interface IProductBuyStockService {

    /**
     * 根据id取得product_buy_stock对象
     * @param  productBuyStockId
     * @return
     */
    ServiceResult<ProductBuyStock> getProductBuyStockById(Integer productBuyStockId);

    /**
     * 保存product_buy_stock对象
     * @param  productBuyStock
     * @return
     */
    ServiceResult<Integer> saveProductBuyStock(ProductBuyStock productBuyStock);

    /**
    * 更新product_buy_stock对象
    * @param  productBuyStock
    * @return
    */
    ServiceResult<Integer> updateProductBuyStock(ProductBuyStock productBuyStock);

    /**
     * 保存SKU库存销售限制信息
     * @param productBuyStocks
     * @return
     */
    ServiceResult<Boolean> saveProductBuyStockAll(List<ProductBuyStock> productBuyStocks);

    /**
    * 分页查询
    * @param queryMap
    * @param pager
    * @return
    */
    ServiceResult<List<ProductBuyStock>> page(Map<String, String> queryMap, PagerInfo pager);

    /**
     * 该sku下是否存在库存设定
     * @param productGoodss
     * @return
     */
    ServiceResult<Boolean> ifexists(List<ProductGoods> productGoodss);

}