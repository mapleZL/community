package com.ejavashop.model.product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.StringUtil;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.dao.shop.read.product.ProductBuyStockReadDao;
import com.ejavashop.dao.shop.read.product.ProductGoodsReadDao;
import com.ejavashop.dao.shop.read.product.ProductReadDao;
import com.ejavashop.dao.shop.write.product.ProductBuyStockWriteDao;
import com.ejavashop.dao.shop.write.product.ProductGoodsWriteDao;
import com.ejavashop.entity.product.ProductBuyStock;
import com.ejavashop.entity.product.ProductGoods;

@Component
public class ProductBuyStockModel {

    private static org.apache.log4j.Logger log = org.apache.log4j.LogManager
                                                   .getLogger(ProductBuyStockModel.class);

    @Resource
    private ProductBuyStockWriteDao        productBuyStockWriteDao;

    @Resource
    private ProductBuyStockReadDao         productBuyStockReadDao;

    @Resource
    private ProductReadDao                 productReadDao;

    @Resource
    private ProductGoodsWriteDao           productGoodsWriteDao;

    @Resource
    private ProductGoodsReadDao            productGoodsReadDao;

    @Resource
    private DataSourceTransactionManager   transactionManager;

    /**
     * 根据id取得product_buy_stock对象
     * @param  productBuyStockId
     * @return
     */
    public ProductBuyStock getProductBuyStockById(Integer productBuyStockId) {
        ProductBuyStock pbs = productBuyStockReadDao.get(productBuyStockId);
        pbs.setProductName(productReadDao.get(pbs.getProductId()).getName1());
        ProductGoods goods = productGoodsReadDao.get(pbs.getGoodsId());
        pbs.setNormName(goods.getNormName());
        pbs.setProductStock(goods.getProductStock());
        return pbs;
    }

    /**
     * 保存product_buy_stock对象
     * @param  productBuyStock
     * @return
     */
    public Integer saveProductBuyStock(ProductBuyStock productBuyStock) {
        this.dbConstrains(productBuyStock);
        return productBuyStockWriteDao.insert(productBuyStock);
    }

    /**
    * 更新product_buy_stock对象
    * @param  productBuyStock
    * @return
    */
    public Integer updateProductBuyStock(ProductBuyStock productBuyStock) {
        this.dbConstrains(productBuyStock);
        return productBuyStockWriteDao.update(productBuyStock);
    }

    private void dbConstrains(ProductBuyStock productBuyStock) {
        productBuyStock.setSku(StringUtil.dbSafeString(productBuyStock.getSku(), false, 50));
        productBuyStock.setCreateName(StringUtil.dbSafeString(productBuyStock.getCreateName(),
            false, 50));
    }

    /**
     * 保存SKU库存销售限制信息
     * @param productBuyStocks
     * @return
     */
    public Boolean saveProductBuyStockAll(List<ProductBuyStock> productBuyStocks) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);
        boolean flag = false;
        try {
            for (ProductBuyStock productBuyStock : productBuyStocks) {
                ProductBuyStock productBuyStockOld = productBuyStockReadDao
                    .getByGoodsId(productBuyStock.getGoodsId());
                //为空的话，保存，不为空，更新内容
                if (productBuyStockOld == null) {
                    ProductGoods productGoods = productGoodsReadDao.get(productBuyStock
                        .getGoodsId());
                    productBuyStock.setSku(productGoods.getSku());
                    productBuyStockWriteDao.insert(productBuyStock);
                } else {
                    productBuyStockOld.setStock(productBuyStock.getStock());
                    productBuyStockOld.setState(productBuyStock.getState());
                    productBuyStockOld.setGrade1(productBuyStock.getGrade1());
                    productBuyStockOld.setGrade2(productBuyStock.getGrade2());
                    productBuyStockOld.setGrade3(productBuyStock.getGrade3());
                    productBuyStockOld.setGrade4(productBuyStock.getGrade4());
                    productBuyStockOld.setGrade5(productBuyStock.getGrade5());
                    productBuyStockOld.setUpdateId(productBuyStock.getUpdateId());
                    productBuyStockOld.setUpdateName(productBuyStock.getUpdateName());

                    productBuyStockWriteDao.update(productBuyStockOld);
                }
            }
            transactionManager.commit(status);
            flag = true;
        } catch (BusinessException be) {
            transactionManager.rollback(status);
            throw be;
        } catch (Exception e) {
            transactionManager.rollback(status);
            throw e;
        }
        return flag;
    }

    public List<ProductBuyStock> page(Map<String, String> queryMap, PagerInfo pager)
                                                                                    throws Exception {
        Map<String, Object> param = new HashMap<String, Object>(queryMap);
        Integer start = 0, size = 0;
        if (pager != null) {
            pager.setRowsCount(productBuyStockReadDao.getCount(param));
            start = pager.getStart();
            size = pager.getPageSize();
        }
        param.put("start", start);
        param.put("size", size);
        List<ProductBuyStock> list = productBuyStockReadDao.page(param);
        for (ProductBuyStock pb : list) {
            pb.setProductName(productReadDao.get(pb.getProductId()).getName1());
            pb.setNormName(productGoodsReadDao.get(pb.getGoodsId()).getNormName());
        }
        return list;
    }

    /**
     * 只要一个sku存在库存设置，返回true
     * @param productGoodss
     * @return
     */
    public Boolean ifexists(List<ProductGoods> productGoodss) {
        Map<String, Object> map = new HashMap<String, Object>();
        Boolean result = Boolean.FALSE;
        for (ProductGoods goods : productGoodss) {
            map.put("goodsId", goods.getId());
            List<ProductBuyStock> pbslist = productBuyStockReadDao.page(map);
            if (pbslist != null && pbslist.size() > 0) {
                log.debug("存在规格：" + pbslist.get(0).getSku());
                result = true;
                break;
            }
        }
        return result;
    }
}