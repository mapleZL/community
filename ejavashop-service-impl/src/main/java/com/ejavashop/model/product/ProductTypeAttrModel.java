package com.ejavashop.model.product;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.dao.shop.read.product.ProductTypeAttrReadDao;
import com.ejavashop.dao.shop.read.product.ProductTypeReadDao;
import com.ejavashop.dao.shop.write.product.ProductTypeAttrWriteDao;
import com.ejavashop.dao.shop.write.product.ProductTypeWriteDao;
import com.ejavashop.entity.product.ProductType;
import com.ejavashop.entity.product.ProductTypeAttr;
import com.ejavashop.vo.product.ProductTypeAttrVO;

@Service(value = "productTypeAttrModel")
public class ProductTypeAttrModel {

    @Resource
    private ProductTypeAttrWriteDao productTypeAttrWriteDao;
    @Resource
    private ProductTypeAttrReadDao productTypeAttrReadDao;
    @Resource
    private ProductTypeWriteDao     productTypeWriteDao;
    @Resource
    private ProductTypeReadDao     productTypeReadDao;
    @Resource(name = "transactionManager")
    private DataSourceTransactionManager transactionManager;

    public Boolean saveProductTypeAttr(ProductTypeAttr productTypeAttr) {
        return productTypeAttrWriteDao.insert(productTypeAttr) > 0;
    }

    public Boolean updateProductTypeAttr(ProductTypeAttr productTypeAttr) {
        return productTypeAttrWriteDao.update(productTypeAttr) > 0;
    }

    public Boolean delProductTypeAttr(Integer productTypeAttrId) {
        //1. business check
        if (null == productTypeAttrId || 0 == productTypeAttrId)
            throw new BusinessException("根据id删除商品分类属性表失败，id为空");

        //2. dbOper
        return productTypeAttrWriteDao.del(productTypeAttrId) > 0;
    }

    public ProductTypeAttr getProductTypeAttrById(Integer productTypeAttrId) {
        if (null == productTypeAttrId || 0 == productTypeAttrId)
            throw new BusinessException("根据id获取商品分类属性表失败，id为空");

        return productTypeAttrReadDao.get(productTypeAttrId);
    }

    public List<ProductTypeAttr> getProductTypeAttrByTypeId(Integer productTypeId) {
        if (null == productTypeId)
            throw new BusinessException("根据商品类型id获取商品类型属性失败，商品类型id为空");
        return productTypeAttrReadDao.getByTypeId(productTypeId);
    }

    public List<ProductTypeAttrVO> pageProductTypeAttr(Map<String, String> queryMap,
                                                       PagerInfo pager) {
        Integer start = 0, size = 0;
        if (pager != null) {
            pager.setRowsCount(productTypeAttrReadDao.count(queryMap));
            start = pager.getStart();
            size = pager.getPageSize();
        }
        List<ProductTypeAttrVO> volist = productTypeAttrReadDao.page(queryMap, start, size);
        for (ProductTypeAttrVO vo : volist) {
            ProductType pt = productTypeReadDao.get(vo.getProductTypeId());
            if (pt != null)
                vo.setProductTypeName(pt.getName());
        }
        return volist;
    }

    public List<ProductTypeAttr> getProductTypeAttrList() {
        return productTypeAttrReadDao.getProductTypeAttrList();
    }

    public boolean updateProductTypeAttrList(List<ProductTypeAttr> productTypeAttr_new) {
        boolean flag = true;
        try {
            for(ProductTypeAttr attr : productTypeAttr_new){
                productTypeAttrWriteDao.update(attr);
            }
        } catch (Exception e) {
            flag = false;
            throw e;
        }
        return flag;
    }
}
