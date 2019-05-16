package com.phkj.model.product;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;

import com.phkj.core.StringUtil;
import com.phkj.core.exception.BusinessException;
import com.phkj.dao.shop.read.product.ProductReadDao;
import com.phkj.dao.shop.read.seller.SellerReadDao;
import com.phkj.dao.shop.write.product.ProductWriteDao;
import com.phkj.dao.shop.write.seller.SellerWriteDao;
import com.phkj.entity.product.Product;
import com.phkj.entity.product.ProductGoods;

@Service(value = "productModel")
public class ProductModel {
    
    @Resource
    private ProductWriteDao              productWriteDao;
    @Resource
    private ProductReadDao               productReadDao;
    
    @Resource(name = "transactionManager")
    private DataSourceTransactionManager transactionManager;
    @Resource
    private SellerReadDao                sellerReadDao;
    @Resource
    private SellerWriteDao               sellerWriteDao;

    public List<Product> getByCateIdTop(Integer cateId, Integer limit) {
        return productReadDao.getByCateIdTop(cateId, limit);
    }

    /**
     * 获取size个商家推荐商品
     * @param sellerId 商家ID
     * @param size 获取条数
     * @return
     */
    public List<Product> getSellerRecommendProducts(Integer sellerId, Integer size) {
        List<Product> list = productReadDao.getSellerRecommendProducts(sellerId, size);
        return list;
    }

    /**
     * 获取size个商家新品
     * @param sellerId 商家ID
     * @param size 获取条数
     * @return
     */
    public List<Product> getSellerNewProducts(Integer sellerId, Integer size) {
        List<Product> list = productReadDao.getSellerNewProducts(sellerId, size);
        return list;
    }

    /**
     * 查询商家所有在售商品（商家列表页）
     * @param sellerId
     * @param sort 0:默认；1、销量从大到小；2、评价从多到少；3、价格从低到高；4、价格从高到低
     * @param sellerCateId
     * @param start
     * @param size
     * @return
     */
    public List<Product> getProductForSellerList(Integer sellerId, Integer sort,
                                                 Integer sellerCateId, Integer start, Integer size) {
        // 排序支持0到4，其他一律按0处理
        if (sort == null || sort > 4) {
            sort = 0;
        }
        List<Product> products = productReadDao.getProductForSellerList(sellerId, sort,
            sellerCateId, start, size);
        return products;
    }

    public Integer getProductForSellerListCount(Integer sellerId, Integer sort, Integer sellerCateId) {
        // 排序支持0到4，其他一律按0处理
        if (sort == null || sort > 4) {
            sort = 0;
        }
        return productReadDao.getProductForSellerListCount(sellerId, sort, sellerCateId);
    }




    public Boolean delProduct(Integer productId) {
        if (null == productId || 0 == productId)
            throw new BusinessException("根据id删除商品表失败，id为空");
        Product product = productReadDao.get(productId);
        if (product.getState().intValue() == Product.STATE_6) {
            throw new BusinessException("已经上架的商品不能删除");
        }
        return productWriteDao.updateState(productId, Product.STATE_5) > 0;
    }



    public List<Product> getProductsBySellerId(Integer sellerid) {
        if (sellerid == null)
            throw new BusinessException("没有商家");
        return productReadDao.getProductsBySellerId(sellerid);
    }

    public Integer updateByIds(Map<String, Object> param, List<Integer> ids) {
        return productWriteDao.updateByIds(param, ids);
    }

    /**
     * 根据商品ID修改商品状态
     * @param id
     * @param state
     * @return
     */
    public boolean updateProductState(Integer id, Integer state) {
        return productWriteDao.updateState(id, state) > 0;
    }

    /**
     * 根据商品ID修改商品推荐状态
     * @param id
     * @param isTop
     * @return
     */
    public boolean updateProductRecommend(Integer id, Integer isTop) {
        return productWriteDao.updateRecommend(id, isTop) > 0;
    }

    /**
     * 以商品id字符串获取商品
     * @param ids
     * @return
     */
    public List<Product> getProductsByIds(List<Integer> ids) {
        return productReadDao.getProductsByIds(ids);
    }

    private void dbConstrainsProduct(Product product) {
        product.setName1(StringUtil.dbSafeString(product.getName1(), false, 200));
        product.setName2(StringUtil.dbSafeString(product.getName2(), false, 200));
        product.setKeyword(StringUtil.dbSafeString(product.getKeyword(), false, 200));
        product.setNormIds(StringUtil.dbSafeString(product.getNormIds(), false, 255));
        product.setNormName(StringUtil.dbSafeString(product.getNormName(), false, 255));
        product.setMasterImg(StringUtil.dbSafeString(product.getMasterImg(), false, 255));

    }

    private void dbConstrainsProductGood(ProductGoods productGoods) {
        productGoods
            .setNormAttrId(StringUtil.dbSafeString(productGoods.getNormAttrId(), false, 255));
        productGoods.setNormName(StringUtil.dbSafeString(productGoods.getNormName(), false, 255));
        productGoods.setSku(StringUtil.dbSafeString(productGoods.getSku(), false, 50));
        productGoods.setImages(StringUtil.dbSafeString(productGoods.getImages(), false, 255));
    }

    /**
     * 查询最大的商品
     * @return
     */
    public Product getProductByMax() {
        return productReadDao.getProductByMax();
    }
    /**
     * 查询商品
     * @param pro
     * @return
     */
    public Integer updateProduct(Product pro) {
        return productWriteDao.update(pro);
    }
    
    /**
     * 查询商品
     * @param map
     * @return
     */
    public List<Product> getProductByKeyWord(String name1, String product_code, Integer seller_id) {
        return productReadDao.getProductsBySPU(name1, product_code, seller_id);
    }

    /**
     * 查询SPU是否重复
     * @param map
     * @return
     */
    public Boolean checkProductBySPUAndSellerId(Map<String, String> queryMap) {
        return productReadDao.checkProductBySPUAndSellerId(queryMap) > 0;
    }

	public List<Product> getProductByProductCode(String searchKeyword,int start,int size) {
		if (null == searchKeyword || "".equals(searchKeyword))
        throw new BusinessException("根据id获取商品表失败，id为空");

		List<Product> product = productReadDao.getProductByProductCode(searchKeyword,start,size);
		return product;
	}
	
	public Integer getProductByProductPageCountCode(String searchKeyword) {
		if (null == searchKeyword || "".equals(searchKeyword))
        throw new BusinessException("根据id获取商品表失败，id为空");

		Integer product = productReadDao.getProductByProductPageCountCode(searchKeyword);
		return product;
	}

	
	public Integer downProduct(){
		Integer productSum = 0;
		//在下架飞猫头鹰的商品
		List<Product> productOthersList =  productReadDao.downOthersProduct();
		if(productOthersList != null && productOthersList.size() > 0){
			productSum = productSum + productOthersList.size();
			for (Product product : productOthersList) {
				if(product != null){
					product.setState(7);
					productWriteDao.update(product);
				}
			}
		}
		return productSum;
	}
/**
 * 查询关联的搭配推荐商品 add by tongzhaomei
 * @param refIdsarr
 * @return
 */
	public List<Product> getProductsByrefIds(String[] refIdsarr) {
		if (null == refIdsarr || "".equals(refIdsarr))
        throw new BusinessException("根据refIdsarr获取商品表失败，refIdsarr为空");

		List<Product> product = productReadDao.getProductsByrefIds(refIdsarr);
		return product;
	}


    
    public Boolean updateActualSales(){
    	productWriteDao.updatePorductGoodsActualSale();
    	productWriteDao.updatePorductActualSale();
    	return true;
    }

    public List<Product> getProductByProductCodeBySort(String searchKeyword, Integer sort,
                                                       int start, int size) {
        if (null == searchKeyword || "".equals(searchKeyword))
            throw new BusinessException("根据id获取商品表失败，id为空");

            List<Product> product = productReadDao.getProductByProductCodebySort(searchKeyword, sort,start,size);
            return product;
    }
	
}
