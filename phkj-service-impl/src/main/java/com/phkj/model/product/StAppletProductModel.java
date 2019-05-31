package com.phkj.model.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.alibaba.fastjson.JSON;
import com.phkj.core.PagerInfo;
import com.phkj.core.exception.BusinessException;
import com.phkj.dao.shop.read.product.StAppletProductReadDao;
import com.phkj.dao.shop.write.product.ProductPictureWriteDao;
import com.phkj.dao.shop.write.product.StAppletProductWriteDao;
import com.phkj.entity.product.ProductPicture;
import com.phkj.entity.product.StAppletProduct;

@Component
public class StAppletProductModel {

    private static Logger                log = LogManager.getLogger(StAppletProductModel.class);

    @Resource(name = "transactionManager")
    private DataSourceTransactionManager transactionManager;
    @Autowired
    private StAppletProductReadDao       stAppletProductReadDao;
    @Autowired
    private StAppletProductWriteDao      stAppletProductWriteDao;
    @Autowired
    private ProductPictureWriteDao       pictureWriteDao;

    /**
     * 根据id取得商品表
     * @param  stAppletProductId
     * @return
     */
    public StAppletProduct getStAppletProductById(Integer stAppletProductId) {
        return stAppletProductReadDao.get(stAppletProductId);
    }

    /**
     * 保存商品表
     * @param  stAppletProduct
     * @return
     */
    public Boolean saveStAppletProduct(StAppletProduct stAppletProduct,
                                       List<ProductPicture> pictures) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);
        try {
            stAppletProductWriteDao.insert(stAppletProduct);
            if (null != pictures && pictures.size() > 0) {
                for (ProductPicture picture : pictures) {
                    picture.setProductId(stAppletProduct.getId());
                    pictureWriteDao.insert(picture);
                }
            }
            transactionManager.commit(status);
            return true;
        } catch (Exception e) {
            transactionManager.rollback(status);
            log.error("ProductServiceImpl saveProduct param:" + JSON.toJSONString(stAppletProduct));
            log.error("ProductServiceImpl saveProduct exception:", e);
            throw e;
        }
    }

    /**
    * 更新商品表
    * @param  stAppletProduct
     * @param pictures 
    * @return
    */
    public Boolean updateStAppletProduct(StAppletProduct stAppletProduct,
                                         List<ProductPicture> productPictureList) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);
        try {
            stAppletProductWriteDao.update(stAppletProduct);

            /**更新商品图片**/
            if (null != productPictureList && productPictureList.size() > 0) {
                pictureWriteDao.delByProductId(stAppletProduct.getId());
                for (ProductPicture picture : productPictureList) {
                    picture.setProductId(stAppletProduct.getId());
                    pictureWriteDao.insert(picture);
                }
            }
            transactionManager.commit(status);
            return true;
        } catch (Exception e) {
            transactionManager.rollback(status);
            log.error(
                "ProductServiceImpl updateProduct param:" + JSON.toJSONString(stAppletProduct));
            log.error("ProductServiceImpl updateProduct exception:", e);
            throw e;
        }
    }

    /**
     * 校验SPU是否重复
     * @param queryMap
     * @return
     */
    public Boolean checkProductBySPUAndSellerId(Map<String, String> queryMap) {
        return stAppletProductReadDao.checkProductBySPUAndSellerId(queryMap) > 0;
    }

    /**
     * 列表查询
     * @param queryMap
     * @param pager
     * @return
     */
    public List<StAppletProduct> pageProduct(Map<String, String> queryMap, PagerInfo pager) {
        List<StAppletProduct> list = new ArrayList<>();
        Integer start = 0, size = 0;
        if (pager != null) {
            pager.setRowsCount(stAppletProductReadDao.count(queryMap));
            start = pager.getStart();
            size = pager.getPageSize();
        }
        list = stAppletProductReadDao.pageProduct(queryMap, start, size);

        return list;
    }

    /**
     * 删除商品
     * @param id
     * @return
     */
    public Boolean delProduct(Integer productId) {
        if (null == productId || 0 == productId)
            throw new BusinessException("根据id删除商品表失败，id为空");
        StAppletProduct product = stAppletProductReadDao.get(productId);
        if (product.getState().intValue() == StAppletProduct.STATE_6) {
            throw new BusinessException("已经上架的商品不能删除");
        }
        return stAppletProductWriteDao.updateState(productId, StAppletProduct.STATE_5) > 0;
    }

    /**
     * 批量更新
     * @param param
     * @param ids
     * @return
     */
    public Integer updateByIds(Map<String, Object> param, List<Integer> ids) {
        return stAppletProductWriteDao.updateByIds(param, ids);
    }

    /**
     * 单个更新
     * @param stAppletProduct
     * @return
     */
    public Integer update(StAppletProduct stAppletProduct) {
        return stAppletProductWriteDao.update(stAppletProduct);
    }

}