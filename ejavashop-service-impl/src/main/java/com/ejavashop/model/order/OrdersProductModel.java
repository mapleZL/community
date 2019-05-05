package com.ejavashop.model.order;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.ejavashop.core.StringUtil;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.dao.shop.read.operate.ProductLabelReadDao;
import com.ejavashop.dao.shop.read.operate.ProductPackageReadDao;
import com.ejavashop.dao.shop.read.order.OrdersProductReadDao;
import com.ejavashop.dao.shop.read.product.ProductGoodsReadDao;
import com.ejavashop.dao.shop.read.seller.SellerReadDao;
import com.ejavashop.dao.shop.write.operate.ProductLabelWriteDao;
import com.ejavashop.dao.shop.write.operate.ProductPackageWriteDao;
import com.ejavashop.dao.shop.write.order.OrdersProductWriteDao;
import com.ejavashop.dao.shop.write.product.ProductGoodsWriteDao;
import com.ejavashop.dao.shop.write.system.CodeWriteDao;
import com.ejavashop.dto.ProductDayDto;
import com.ejavashop.entity.operate.ProductLabel;
import com.ejavashop.entity.operate.ProductPackage;
import com.ejavashop.entity.order.OrdersProduct;
import com.ejavashop.entity.product.ProductGoods;
import com.ejavashop.entity.seller.Seller;
import com.ejavashop.entity.system.Code;
import com.ejavashop.util.FrontProductPictureUtil;

@Component(value = "ordersProductModel")
public class OrdersProductModel {

    @Resource
    private OrdersProductWriteDao   ordersProductWriteDao;
    @Resource
    private OrdersProductReadDao    ordersProductReadDao;
    @Resource
    private SellerReadDao           sellerReadDao;
    @Resource
    private ProductPackageWriteDao  productPackageWriteDao;
    @Resource
    private ProductPackageReadDao  productPackageReadDao;
    @Resource
    private ProductLabelWriteDao    productLabelWriteDao;
    @Resource
    private ProductLabelReadDao    productLabelReadDao;
    @Resource
    private CodeWriteDao            codeWriteDao;
    @Resource
    private ProductGoodsWriteDao    productGoodsWriteDao;
    @Resource
    private ProductGoodsReadDao    productGoodsReadDao;
    private static ServletContext  serlvetContext;
    private static byte[]          sync = new byte[2];
    /**
     * 产品图片获取工具类
     */
    @Resource
    private FrontProductPictureUtil frontProductPictureUtil;

    /**
    * 根据id取得网单表
    * @param  ordersProductId
    * @return
    */
    public OrdersProduct getOrdersProductById(Integer ordersProductId) {
        return ordersProductReadDao.get(ordersProductId);
    }

    /**
     * 保存网单表
     * @param  ordersProduct
     * @return
     */
    public Integer saveOrdersProduct(OrdersProduct ordersProduct) {
        return ordersProductWriteDao.insert(ordersProduct);
    }

    /**
    * 更新网单表
    * @param  ordersProduct
    * @return
    */
    public Integer updateOrdersProduct(OrdersProduct ordersProduct) {
        return ordersProductWriteDao.update(ordersProduct);
    }

    public Integer pageCount(Map<String, Object> queryMap) {
        return ordersProductReadDao.getCount(queryMap);
    }

    public List<OrdersProduct> page(Map<String, Object> queryMap) {
        return ordersProductReadDao.page(queryMap);
    }

    public boolean del(Integer id) {
        if (id == null)
            throw new BusinessException("删除网单表[" + id + "]时出错");
        return ordersProductWriteDao.del(id) > 0;
    }

    /**
     * 根据订单号获取网单
     * @param orderId
     * @return
     */
    public List<OrdersProduct> getOrdersProductByOId(Integer orderId) {
        List<OrdersProduct> oplist = ordersProductReadDao.getByOrderId(orderId);
        for (OrdersProduct op : oplist) {
            //套餐
            ProductPackage propack = productPackageReadDao.get(op.getProductPackageId());
            if (propack != null) {
                op.setPackageName(propack.getName());
                Code code = codeWriteDao.getCode("PRO_PACKAGE_TYPE", propack.getPackingType() + "");
                op.setPackingType(code.getCodeText());
                code = codeWriteDao.getCode("PRO_PACKAGE_UNIT", propack.getUnitType() + "");
                op.setUnitType(code.getCodeText());

                StringBuffer sb = new StringBuffer();
                if (!StringUtil.isNullOrZero(propack.getIsHook())) {
                    sb.append("钩子").append("、");
                }
                if (!StringUtil.isNullOrZero(propack.getIsGlue())) {
                    sb.append("不干胶").append("、");
                }
                if (!StringUtil.isNullOrZero(propack.getIsLiner())) {
                    sb.append("衬板").append("、");
                }
                if (!StringUtil.isNullOrZero(propack.getIsBag())) {
                    sb.append("中包袋").append("、");
                }
                if (!StringUtil.isNullOrZero(propack.getIsLabel())) {
                    sb.append("防伪标").append("、");
                }
                if (!StringUtil.isNullOrZero(propack.getIsGirdle())) {
                    sb.append("腰封").append("、");
                }

                if (sb.length() > 0) {
                    op.setPackOther(sb.substring(0, sb.length() - 1));
                }
            }
            //标签
            ProductLabel label = productLabelReadDao.get(op.getProductLabelId());
            if (label != null) {
                op.setLabelName(label.getName());
            }
        }
        return oplist;
    }

    /**
     * 根据id 取得网单信息
     * @param request
     * @return
     */
    public OrdersProduct getOrdersProductWithImgById(Integer orderProductId) {
        if (orderProductId == null || orderProductId == 0) {
            throw new BusinessException("网单id为空，请重试！");
        }

        OrdersProduct productvo = ordersProductReadDao.get(orderProductId);
        if (productvo != null) {
            String productLeadLittle = frontProductPictureUtil.getproductLeadLittle(productvo
                .getProductId());
            productvo.setProductLeadLittle(productLeadLittle);
        }
        return productvo;
    }

    /**
     * 统计商品每日销量
     * @param queryMap
     * @return
     */
    public List<ProductDayDto> getProductDayDto(Map<String, String> queryMap) {
        List<ProductDayDto> list = ordersProductReadDao.getProductDayDto(queryMap);
        if (list != null && list.size() > 0) {
            Map<Integer, Seller> map = new HashMap<Integer, Seller>();
            for (ProductDayDto productDayDto : list) {
                Seller seller = map.get(productDayDto.getSellerId());
                if (seller == null) {
                    seller = sellerReadDao.get(productDayDto.getSellerId());
                    map.put(productDayDto.getSellerId(), seller);
                }
                if (seller != null) {
                    productDayDto.setSellerName(seller.getSellerName());
                }
            }
        }
        return list;
    }
    
    private static final Logger  ILog = LogManager.getLogger("oms_interface");
    
    /** 
     * @author xiaotiantian
     * @param context
     * @return
     */
    public Boolean getWaitedStock(Object context){
    	//加入最后一次同步库存判断，由于oms 将sku分批次同步过来，导致未推送的订单占用的库存会在每一批次同步的时候，都做了扣减，
    	//加入判断后，同一个小时内的 只会有第一批次去扣减 待出库的库存
    	//WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();    
        //ServletContext servletContext = webApplicationContext.getServletContext();
    	synchronized (sync) {
            if (serlvetContext == null && context != null)
                serlvetContext = (ServletContext) context;
        }
    	DateFormat format = new SimpleDateFormat("yyMMddHH");   
    	String nowTime = format.format(new Date());
    	String lastSyncTime = "";
    	if (serlvetContext.getAttribute("OMS_LAST_SYNC_TIME") == null) {
    	    serlvetContext.setAttribute("OMS_LAST_SYNC_TIME", nowTime);
    	} else {
    		lastSyncTime = (String) serlvetContext.getAttribute("OMS_LAST_SYNC_TIME");
    	}
    	
    	if (!nowTime.equals(lastSyncTime)) {
        	List<OrdersProduct> ordersProductList = ordersProductReadDao.getWaitedStock();
        	if(ordersProductList != null && ordersProductList.size() > 0){
        		for (OrdersProduct ordersProduct : ordersProductList) {
    				if(ordersProduct != null){
    					String productSku = ordersProduct.getProductSku();
    					Integer number = ordersProduct.getNumber();
    					ILog.info("sku为"+productSku+"的商品本次待减库存为"+number);
    					Integer sellerId = ordersProduct.getSellerId();
    					Seller seller = sellerReadDao.get(sellerId);
    					if(seller != null){
    						String sellerCode = seller.getSellerCode();
    						List<ProductGoods> productGoodsList = productGoodsReadDao.getBySkuAndMember(productSku, sellerCode);
    						for (ProductGoods productGoods : productGoodsList) {
    							if(productGoods != null){
    								productGoods.setProductStock((productGoods.getProductStock()-number) < 0 ? 0:(productGoods.getProductStock()-number));
    								ILog.info("sku为"+productGoods.getSku()+"的商品减掉待出库的库存之后，库存量为:"+(productGoods.getProductStock()));
    								productGoodsWriteDao.update(productGoods);
    							}
    						}
    					}
    				}
    			}
        	}
        	serlvetContext.setAttribute("OMS_LAST_SYNC_TIME", nowTime);
    	}
    	return true;
    }
}
