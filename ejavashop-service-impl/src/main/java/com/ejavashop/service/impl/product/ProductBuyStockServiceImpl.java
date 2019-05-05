package com.ejavashop.service.impl.product;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.entity.product.ProductBuyStock;
import com.ejavashop.entity.product.ProductGoods;
import com.ejavashop.model.product.ProductBuyStockModel;
import com.ejavashop.service.product.IProductBuyStockService;

@Service(value = "productBuyStockService")
public class ProductBuyStockServiceImpl implements IProductBuyStockService {
    private static Logger        log = LogManager.getLogger(ProductBuyStockServiceImpl.class);

    @Resource
    private ProductBuyStockModel productBuyStockModel;

    /**
    * 根据id取得product_buy_stock对象
    * @param  productBuyStockId
    * @return
    */
    @Override
    public ServiceResult<ProductBuyStock> getProductBuyStockById(Integer productBuyStockId) {
        ServiceResult<ProductBuyStock> result = new ServiceResult<ProductBuyStock>();
        try {
            result.setResult(productBuyStockModel.getProductBuyStockById(productBuyStockId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IProductBuyStockService][getProductBuyStockById]根据id[" + productBuyStockId
                      + "]取得product_buy_stock对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IProductBuyStockService][getProductBuyStockById]根据id[" + productBuyStockId
                      + "]取得product_buy_stock对象时出现未知异常：", e);
        }
        return result;
    }

    /**
     * 保存product_buy_stock对象
     * @param  productBuyStock
     * @return
     */
    @Override
    public ServiceResult<Integer> saveProductBuyStock(ProductBuyStock productBuyStock) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(productBuyStockModel.saveProductBuyStock(productBuyStock));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IProductBuyStockService][saveProductBuyStock]保存product_buy_stock对象时出现未知异常："
                      + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error(
                "[IProductBuyStockService][saveProductBuyStock]保存product_buy_stock对象时出现未知异常：", e);
        }
        return result;
    }

    /**
    * 更新product_buy_stock对象
    * @param  productBuyStock
    * @return
    * @see com.ejavashop.service.ProductBuyStockService#updateProductBuyStock(ProductBuyStock)
    */
    @Override
    public ServiceResult<Integer> updateProductBuyStock(ProductBuyStock productBuyStock) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(productBuyStockModel.updateProductBuyStock(productBuyStock));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IProductBuyStockService][updateProductBuyStock]更新product_buy_stock对象时出现未知异常："
                      + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error(
                "[IProductBuyStockService][updateProductBuyStock]更新product_buy_stock对象时出现未知异常：", e);
        }
        return result;
    }

    /**
     * 保存SKU库存销售限制信息
     * @param productBuyStocks
     * @return
     * @see com.ejavashop.service.product.IProductBuyStockService#saveProductBuyStockAll(java.util.List)
     */
    @Override
    public ServiceResult<Boolean> saveProductBuyStockAll(List<ProductBuyStock> productBuyStocks) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(productBuyStockModel.saveProductBuyStockAll(productBuyStocks));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IProductBuyStockService][saveProductBuyStockAll]保存SKU库存销售限制信息对象时出现未知异常："
                      + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IProductBuyStockService][saveProductBuyStockAll]保存SKU库存销售限制信息对象时出现未知异常：", e);
        }
        return result;
    }

    @Override
    public ServiceResult<List<ProductBuyStock>> page(Map<String, String> queryMap, PagerInfo pager) {
        ServiceResult<List<ProductBuyStock>> serviceResult = new ServiceResult<List<ProductBuyStock>>();
        try {
            serviceResult.setResult(productBuyStockModel.page(queryMap, pager));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            e.printStackTrace();
            log.error("[ProductBuyStockServiceImpl][page] exception:" + e.getMessage());
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> ifexists(List<ProductGoods> productGoodss) {
        ServiceResult<Boolean> result = new ServiceResult<>();
        try {
            result.setResult(productBuyStockModel.ifexists(productGoodss));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
        }
        return result;
    }

}