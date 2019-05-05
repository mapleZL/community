package com.ejavashop.model.product;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ejavashop.core.PaginationUtil;
import com.ejavashop.core.StringUtil;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.dao.shop.read.product.ProductGoodsReadDao;
import com.ejavashop.dao.shop.write.product.ProductGoodsWriteDao;
import com.ejavashop.entity.order.OrdersProduct;
import com.ejavashop.entity.product.ProductGoods;

@Component(value = "productGoodsModel")
public class ProductGoodsModel {

    @Resource
    private ProductGoodsWriteDao productGoodsWriteDao;
    @Resource
    private ProductGoodsReadDao  productGoodsReadDao;

    public List<ProductGoods> getGoodSByProductId(Integer productId) {
        return productGoodsReadDao.getByProductId(productId);
    }

    public Boolean saveProductGoods(ProductGoods productGoods) {
        return productGoodsWriteDao.insert(productGoods) > 0;
    }

    public Boolean updateProductGoods(ProductGoods productGoods) {
        return productGoodsWriteDao.update(productGoods) > 0;
    }

    public Boolean delProductGoods(Integer productGoodsId) {
        //1. business check
        if (null == productGoodsId || 0 == productGoodsId)
            throw new BusinessException("根据id删除货品表失败，id为空");

        //2. dbOper
        return productGoodsWriteDao.del(productGoodsId) > 0;
    }

    public ProductGoods getProductGoodsById(Integer productGoodsId) {
        return productGoodsReadDao.get(productGoodsId);
    }

    public List<ProductGoods> pageProductGoods(Map<String, String> queryMap, PaginationUtil page) {
        Integer start = 0, size = 0;
        if (page != null) {
            page.createPagination(productGoodsReadDao.count(queryMap));
            start = page.getStartNumber();
            size = page.getSize();
        }
        return productGoodsReadDao.page(queryMap, start, size);
    }

    public List<ProductGoods> quickSearch(Map<String, String> queryMap, PaginationUtil page) {
        Integer start = 0, size = 0;
        if (page != null) {
            page.createPagination(productGoodsReadDao.quickSearchCount(queryMap));
            start = page.getStartNumber();
            size = page.getSize();
        }
        return productGoodsReadDao.quickSearch(queryMap, start, size);
    }

    /**
     * 根据条件（规格id组合）取得货品信息
     * @param queryMap
     * @return
     */

    public ProductGoods getProductGoodsByCondition(Map<String, String> queryMap) {
        if (queryMap == null) {
            throw new BusinessException("查询条件不能为空");
        } else {
            String productId = (String) queryMap.get("q_productId");
            if (StringUtil.isEmpty(productId)) {
                throw new BusinessException("传入的产品ID不能为空");
            }
        }

        return productGoodsReadDao.getByCondition(queryMap);
    }

    public Boolean updateProductGoods(List<ProductGoods> goodslist) {
        if (goodslist == null || goodslist.size() < 1)
            throw new BusinessException("没有需要更新的货品");
        return productGoodsWriteDao.batchUpdate(goodslist) > 0;
    }

    public Boolean checkProductBySKUAndSeller(Map<String, String> queryMap) {
        return productGoodsReadDao.checkProductBySKUAndSeller(queryMap) > 0;
    }

    public Boolean checkBarCodeIsExsit(Map<String, String> queryMap) {
        return productGoodsReadDao.checkBarCodeIsExsit(queryMap) > 0;
    }

    public List<ProductGoods> getBySkuAndMember(String sku,String memberId){
    	return productGoodsReadDao.getBySkuAndMember(sku,memberId);
    }
    public Boolean updateSpuStock(){
    	return productGoodsWriteDao.updateSpuStock() > 0 ;
    }

    public List<ProductGoods> checkProductBySKUAndProductId(Map<String, String> queryMap) {
        return productGoodsReadDao.checkProductBySKUAndProductId(queryMap);
    }
    
}
