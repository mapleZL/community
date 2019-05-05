package com.ejavashop.service.impl.product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.alibaba.fastjson.JSON;
import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.dao.shop.read.product.ProductNormAttrOptReadDao;
import com.ejavashop.dao.shop.write.product.ProductNormAttrOptWriteDao;
import com.ejavashop.entity.product.ProductNormAttrOpt;
import com.ejavashop.service.product.IProductNormAttrOptService;

/**
 * 商品选定的颜色规格服务
 *                       
 * @Filename: ProductNormAttrOptServiceImpl.java
 * @Version: 1.0
 * 
 */
@Service(value = "productNormAttrOptService")
public class ProductNormAttrOptServiceImpl implements IProductNormAttrOptService {
    private static Logger                log = LogManager
                                                 .getLogger(ProductNormAttrOptServiceImpl.class);

    @Resource
    private ProductNormAttrOptWriteDao   productNormAttrOptWriteDao;
    @Resource
    private ProductNormAttrOptReadDao   productNormAttrOptReadDao;
    @Resource
    private DataSourceTransactionManager transactionManager;

    /**
    * 根据id取得商品选定的颜色规格
    * @param  productNormAttrOptId
    * @return
    * @see com.ejavashop.service.ProductNormAttrOptService#getProductNormAttrOptById(java.lang.Integer)
    */
    @Override
    public ServiceResult<ProductNormAttrOpt> getProductNormAttrOptById(Integer productNormAttrOptId) {
        ServiceResult<ProductNormAttrOpt> result = new ServiceResult<ProductNormAttrOpt>();
        try {
            result.setResult(productNormAttrOptReadDao.get(productNormAttrOptId));
        } catch (Exception e) {
            log.error("根据id[" + productNormAttrOptId + "]取得商品选定的颜色规格时出现未知异常：" + e);
            result.setSuccess(false);
            result.setMessage("根据id[" + productNormAttrOptId + "]取得商品选定的颜色规格时出现未知异常");
        }
        return result;
    }

    /**
     * 保存商品选定的颜色规格
     * @param  productNormAttrOpt
     * @return
     * @see com.ejavashop.service.ProductNormAttrOptService#saveProductNormAttrOpt(ProductNormAttrOpt)
     */
    @Override
    public ServiceResult<Integer> saveProductNormAttrOpt(ProductNormAttrOpt productNormAttrOpt) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(productNormAttrOptWriteDao.save(productNormAttrOpt));
            result.setMessage("保存成功");
        } catch (Exception e) {
            log.error("保存商品选定的颜色规格时出现未知异常：" + e);
            result.setSuccess(false);
            result.setMessage("保存商品选定的颜色规格时出现未知异常");
        }
        return result;
    }

    /**
    * 更新商品选定的颜色规格
    * @param  productNormAttrOpt
    * @return
    * @see com.ejavashop.service.ProductNormAttrOptService#updateProductNormAttrOpt(ProductNormAttrOpt)
    */
    @Override
    public ServiceResult<Integer> updateProductNormAttrOpt(ProductNormAttrOpt productNormAttrOpt) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(productNormAttrOptWriteDao.update(productNormAttrOpt));
            result.setMessage("更新成功");
        } catch (Exception e) {
            log.error("更新商品选定的颜色规格时出现未知异常：" + e);
            result.setSuccess(false);
            result.setMessage("更新商品选定的颜色规格时出现未知异常");
        }
        return result;
    }

    @Override
    public ServiceResult<List<ProductNormAttrOpt>> page(Map<String, String> queryMap,
                                                        PagerInfo pager) {
        ServiceResult<List<ProductNormAttrOpt>> serviceResult = new ServiceResult<List<ProductNormAttrOpt>>();
        Map<String, Object> param = new HashMap<String, Object>(queryMap);
        try {
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(productNormAttrOptReadDao.getCount(param));
                start = pager.getStart();
                size = pager.getPageSize();
            }
            param.put("start", start);
            param.put("size", size);
            List<ProductNormAttrOpt> list = productNormAttrOptReadDao.page(param);
            serviceResult.setResult(list);
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            e.printStackTrace();
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[ProductNormAttrOptServiceImpl][page] param1:" + JSON.toJSONString(queryMap)
                      + " &param2:" + JSON.toJSONString(pager));
            log.error("[ProductNormAttrOptServiceImpl][page] exception:" + e.getMessage());
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> del(Integer id) {

        ServiceResult<Boolean> sr = new ServiceResult<Boolean>();
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);
        try {
            if (id == null)
                throw new BusinessException("删除商品选定的颜色规格[" + id + "]时出错");
            sr.setResult(this.productNormAttrOptWriteDao.del(id) > 0);
            sr.setMessage("更新成功");
            transactionManager.commit(status);
        } catch (Exception e) {
            log.error("[ProductNormAttrOptServiceImpl][del] exception:" + e.getMessage());
            transactionManager.rollback(status);
            e.printStackTrace();
        }
        return sr;
    }
}