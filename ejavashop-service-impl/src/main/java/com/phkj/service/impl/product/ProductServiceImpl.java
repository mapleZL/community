package com.phkj.service.impl.product;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.phkj.core.ConstantsEJS;
import com.phkj.core.PagerInfo;
import com.phkj.core.ServiceResult;
import com.phkj.core.exception.BusinessException;
import com.phkj.entity.product.Product;
import com.phkj.entity.product.ProductAttr;
import com.phkj.entity.product.ProductGoods;
import com.phkj.entity.product.ProductNormAttrOpt;
import com.phkj.entity.product.ProductPicture;
import com.phkj.model.product.ProductModel;
import com.phkj.service.product.IProductService;
import com.phkj.vo.product.ListProductPriceVO1;
import com.phkj.vo.product.ListProductPriceVO2;
import com.phkj.vo.product.ListProductPriceVO3;

@Service(value = "productService")
public class ProductServiceImpl implements IProductService {
    private static Logger log = LogManager.getLogger(ProductServiceImpl.class);
    private static  Logger  ILog = LogManager.getLogger("oms_interface");

    @Resource
    private ProductModel  productModel;

    @Override
    public ServiceResult<List<Product>> getByCateIdTop(Integer cateId, Integer limit) {
        ServiceResult<List<Product>> serviceResult = new ServiceResult<List<Product>>();
        try {
            serviceResult.setResult(productModel.getByCateIdTop(cateId, limit));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("ProductServiceImpl getByCateIdTop cateId:" + cateId);
            log.error("ProductServiceImpl getByCateIdTop exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<Product>> getSellerRecommendProducts(Integer sellerId, Integer size) {
        ServiceResult<List<Product>> serviceResult = new ServiceResult<List<Product>>();
        try {
            serviceResult.setResult(productModel.getSellerRecommendProducts(sellerId, size));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[ProductService][getSellerRecommendProducts]获取商家推荐商品时出现异常:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<Product>> getSellerNewProducts(Integer sellerId, Integer size) {
        ServiceResult<List<Product>> serviceResult = new ServiceResult<List<Product>>();
        try {
            serviceResult.setResult(productModel.getSellerNewProducts(sellerId, size));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[ProductService][getSellerNewProducts]获取商家新品时出现异常:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<Product>> getProductForSellerList(Integer sellerId, Integer sort,
                                                                Integer sellerCateId,
                                                                PagerInfo pager) {
        ServiceResult<List<Product>> serviceResult = new ServiceResult<List<Product>>();
        serviceResult.setPager(pager);
        try {
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(productModel.getProductForSellerListCount(sellerId, sort,
                    sellerCateId));
                start = pager.getStart();
                size = pager.getPageSize();
            }
            serviceResult.setResult(productModel.getProductForSellerList(sellerId, sort,
                sellerCateId, start, size));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[ProductService][getProductForSellerList]查询店铺商品时出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[ProductService][getProductForSellerList]查询店铺商品时发生异常:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<Product>> getRecommendProducts(Integer size) {
        ServiceResult<List<Product>> result = new ServiceResult<List<Product>>();
        try {
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error("[ProductService][getIndexRecProduct]获取首页推荐商品时发生异常:" + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[ProductService][getIndexRecProduct]获取首页推荐商品时发生异常:", e);
        }
        return result;
    }

    @Override
    public ServiceResult<Boolean> saveProduct(Product product,
                                              List<ProductPicture> productPictureList,
                                              List<ProductAttr> productAttrList) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("ProductServiceImpl saveProduct param:" + JSON.toJSONString(product));
            log.error("ProductServiceImpl saveProduct exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> updateProduct(Product product,
                                                List<ProductPicture> productPictureList,
                                                List<ProductAttr> productAttrList) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("ProductServiceImpl updateProduct param:" + JSON.toJSONString(product));
            log.error("ProductServiceImpl updateProduct exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> delProduct(Integer productId) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(productModel.delProduct(productId));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("ProductServiceImpl delProduct productId:" + productId);
            log.error("ProductServiceImpl delProduct exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Product> getProductById(Integer productId) {
        ServiceResult<Product> serviceResult = new ServiceResult<Product>();
        try {
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("ProductServiceImpl getProductById id:" + productId);
            log.error("ProductServiceImpl getProductById exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<Product>> pageProduct(Map<String, String> queryMap, PagerInfo pager) {
        ServiceResult<List<Product>> serviceResult = new ServiceResult<List<Product>>();
        try {
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(false);
        } catch (Exception e) {
            e.printStackTrace();
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("ProductServiceImpl pageProduct queryMap:" + JSON.toJSONString(queryMap)
                      + " pager:" + JSON.toJSONString(pager));
            log.error("ProductServiceImpl pageProduct exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<Product>> getProductsBySellerId(Integer sellerid) {
        ServiceResult<List<Product>> sr = new ServiceResult<List<Product>>();
        try {
            sr.setResult(productModel.getProductsBySellerId(sellerid));
        } catch (BusinessException e) {
            sr.setMessage(e.getMessage());
            sr.setSuccess(false);
        }
        return sr;
    }

    @Override
    public ServiceResult<Boolean> saveOrupdate(Product product,
                                               List<ProductPicture> productPictureList,
                                               List<ProductAttr> productAttrList,
                                               List<ProductGoods> productGoodsList,
                                               List<ProductNormAttrOpt> optlist) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {

        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("ProductServiceImpl saveProduct param:" + JSON.toJSONString(product));
            log.error("ProductServiceImpl saveProduct exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Integer> updateByIds(Map<String, Object> param, List<Integer> ids) {

        ServiceResult<Integer> serviceResult = new ServiceResult<Integer>();
        try {
            serviceResult.setResult(productModel.updateByIds(param, ids));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("ProductServiceImpl updateByIds param1:" + JSON.toJSONString(param)
                      + System.lineSeparator() + "param2:" + JSON.toJSONString(ids));
            log.error("ProductServiceImpl updateByIds exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> updateProductState(Integer productId, Integer state) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(productModel.updateProductState(productId, state));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("ProductServiceImpl updateProductState productId:" + productId);
            log.error("ProductServiceImpl updateProductState exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> updateProductRecommend(Integer productId, Integer isTop) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(productModel.updateProductRecommend(productId, isTop));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("ProductServiceImpl updateProductRecommend productId:" + productId);
            log.error("ProductServiceImpl updateProductRecommend exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<Product>> getProductsByIds(List<Integer> ids) {
        ServiceResult<List<Product>> serviceResult = new ServiceResult<List<Product>>();
        try {
            serviceResult.setResult(productModel.getProductsByIds(ids));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[ProductServiceImpl][getProductsByIds] exception:", e);
        }
        return serviceResult;
    }

    /**
     * 查询最大的商品
     * @return
     * @see com.phkj.service.product.IProductService#getProductByMax()
     */
    @Override
    public ServiceResult<Product> getProductByMax() {
        ServiceResult<Product> serviceResult = new ServiceResult<Product>();
        try {
            serviceResult.setResult(productModel.getProductByMax());
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("ProductServiceImpl getProductByMax");
            log.error("ProductServiceImpl getProductByMax exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> updateProduct(Product product) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(productModel.updateProduct(product) > 0);
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("ProductServiceImpl updateProduct exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<Product>> getProductsByKeyWord(String name1,String product_code,Integer seller_id) {
        ServiceResult<List<Product>> serviceResult = new ServiceResult<List<Product>>();
        try {
            serviceResult.setResult(productModel.getProductByKeyWord(name1,product_code,seller_id));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("ProductServiceImpl getProductBykeyWord keyWords:");
            log.error("ProductServiceImpl getProductById exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> checkProductBySPUAndSellerId(Map<String, String> queryMap) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(productModel.checkProductBySPUAndSellerId(queryMap));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("ProductServiceImpl getProductBykeyWord keyWords:");
            log.error("ProductServiceImpl getProductById exception:", e);
        }
        return serviceResult;
    }

	@Override
	public ServiceResult<List<Product>> getProductByProductCode(String searchKeyword,int start,int size) {
		 ServiceResult<List<Product>> serviceResult = new ServiceResult<List<Product>>();
	        try {
	            serviceResult.setResult(productModel.getProductByProductCode(searchKeyword,start,size));
	        } catch (BusinessException e) {
	            serviceResult.setMessage(e.getMessage());
	            serviceResult.setSuccess(Boolean.FALSE);
	        } catch (Exception e) {
	            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
	                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
	            log.error("ProductServiceImpl getProductById id:" + searchKeyword);
	            log.error("ProductServiceImpl getProductById exception:", e);
	        }
	        return serviceResult;
	}
	
	@Override
    public ServiceResult<List<Product>> getProductByProductCodeBySort(String searchKeyword,int start,int size,Integer sort) {
         ServiceResult<List<Product>> serviceResult = new ServiceResult<List<Product>>();
            try {
                serviceResult.setResult(productModel.getProductByProductCodeBySort(searchKeyword,sort,start,size));
            } catch (BusinessException e) {
                serviceResult.setMessage(e.getMessage());
                serviceResult.setSuccess(Boolean.FALSE);
            } catch (Exception e) {
                serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                    ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
                log.error("ProductServiceImpl getProductById id:" + searchKeyword);
                log.error("ProductServiceImpl getProductById exception:", e);
            }
            return serviceResult;
    }
	
	@Override
	public ServiceResult<Integer> getProductByProductPageCountCode(String searchKeyword) {
		 ServiceResult<Integer> serviceResult = new ServiceResult<Integer>();
	        try {
	            serviceResult.setResult(productModel.getProductByProductPageCountCode(searchKeyword));
	        } catch (BusinessException e) {
	            serviceResult.setMessage(e.getMessage());
	            serviceResult.setSuccess(Boolean.FALSE);
	        } catch (Exception e) {
	            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
	                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
	            log.error("ProductServiceImpl getProductById id:" + searchKeyword);
	            log.error("ProductServiceImpl getProductById exception:", e);
	        }
	        return serviceResult;
	}
	
	/**
     * 分页  楼层数据添加商品时，过滤已经是楼层数据的商品
     * @param queryMap
     * @param pager
     * @return
     */
	@Override
	public ServiceResult<List<Product>> pageProductByh5fllordata(
			Map<String, String> queryMap, PagerInfo pager,List<Integer> productIds) {
        ServiceResult<List<Product>> serviceResult = new ServiceResult<List<Product>>();
        try {
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(false);
        } catch (Exception e) {
            e.printStackTrace();
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("ProductServiceImpl pageProduct queryMap:" + JSON.toJSONString(queryMap)
                      + " pager:" + JSON.toJSONString(pager));
            log.error("ProductServiceImpl pageProduct exception:", e);
        }
        return serviceResult;
    }

	@Override
	public ServiceResult<List<Product>> pageproductBypcfloordata(
			Map<String, String> queryMap, PagerInfo pager,
			List<Integer> productIds) {
        ServiceResult<List<Product>> serviceResult = new ServiceResult<List<Product>>();
        try {
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(false);
        } catch (Exception e) {
            e.printStackTrace();
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("ProductServiceImpl pageProduct queryMap:" + JSON.toJSONString(queryMap)
                      + " pager:" + JSON.toJSONString(pager));
            log.error("ProductServiceImpl pageProduct exception:", e);
        }
        return serviceResult;
    }

	@Override
	public ServiceResult<Integer> downProduct() {
		 ServiceResult<Integer> serviceResult = new ServiceResult<Integer>();
	        try {
	            serviceResult.setResult(productModel.downProduct());
	        } catch (BusinessException e) {
	            serviceResult.setMessage(e.getMessage());
	            serviceResult.setSuccess(false);
	        } catch (Exception e) {
	            e.printStackTrace();
	            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
	                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
	            log.error("ProductServiceImpl downProduct exception:", e);
	        }
	        return serviceResult;
	}
/**
 * 查询关联的搭配推荐商品 add by tongzhaomei
 */
	@Override
	public ServiceResult<List<Product>> getProductsByrefIds(String[] refIdsarr) {
		 ServiceResult<List<Product>> serviceResult = new ServiceResult<List<Product>>();
	        try {
	            serviceResult.setResult(productModel.getProductsByrefIds(refIdsarr));
	        } catch (BusinessException e) {
	            serviceResult.setMessage(e.getMessage());
	            serviceResult.setSuccess(false);
	        } catch (Exception e) {
	            e.printStackTrace();
	            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
	                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
	            log.error("ProductServiceImpl downProduct exception:", e);
	        }
	        return serviceResult;
	}



    @Override
    public void saveGoodsAndAttr(Map<String, String> dataMap, int c, Integer productId,
                                 Integer productGoodsId) {
        try {
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	@Override
	public ServiceResult<Boolean> updateActualSales() {
		ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(productModel.updateActualSales());
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(false);
        } catch (Exception e) {
            e.printStackTrace();
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("ProductServiceImpl updateActualSales exception:", e);
        }
        return serviceResult;
	}
	


	@Override
	public ServiceResult<String> omsProductCreate(Product product,
			ProductGoods goods) {
		ServiceResult<String> serviceResult = new ServiceResult<String>();
        try {
//            serviceResult.setResult(productModel.omsProductCreate(product,goods));
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
            ILog.error("[ProductServiceImpl][omsProductCreate]推送商品到oms发生错误:" + be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "推送商品到oms发生错误。");
            ILog.error("[ProductServiceImpl][omsProductCreate]推送商品到oms发生错误:", e);
        }
        return serviceResult;
	}

	@Override
	public ServiceResult<List<ListProductPriceVO1>> listProductPrice1() {
		ServiceResult<List<ListProductPriceVO1>> serviceResult = new ServiceResult<List<ListProductPriceVO1>>();
        try {
            serviceResult.setResult(productModel.listProductPrice1());
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
            ILog.error("[ProductServiceImpl][omsProductCreate]推送商品到oms发生错误:" + be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "推送商品到oms发生错误。");
            ILog.error("[ProductServiceImpl][omsProductCreate]推送商品到oms发生错误:", e);
        }
        return serviceResult;
	}

	@Override
	public ServiceResult<List<ListProductPriceVO2>> listProductPrice2() {
		ServiceResult<List<ListProductPriceVO2>> serviceResult = new ServiceResult<List<ListProductPriceVO2>>();
        try {
            serviceResult.setResult(productModel.listProductPrice2());
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
            ILog.error("[ProductServiceImpl][omsProductCreate]推送商品到oms发生错误:" + be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "推送商品到oms发生错误。");
            ILog.error("[ProductServiceImpl][omsProductCreate]推送商品到oms发生错误:", e);
        }
        return serviceResult;
	}

	@Override
	public ServiceResult<List<ListProductPriceVO3>> listProductPrice3() {
		ServiceResult<List<ListProductPriceVO3>> serviceResult = new ServiceResult<List<ListProductPriceVO3>>();
        try {
            serviceResult.setResult(productModel.listProductPrice3());
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
            ILog.error("[ProductServiceImpl][omsProductCreate]推送商品到oms发生错误:" + be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "推送商品到oms发生错误。");
            ILog.error("[ProductServiceImpl][omsProductCreate]推送商品到oms发生错误:", e);
        }
        return serviceResult;
	}
}