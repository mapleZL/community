package com.ejavashop.model.product;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
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
import org.springframework.util.Assert;

import com.alibaba.fastjson.JSON;
import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.StringUtil;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.dao.shop.read.product.ProductAttrReadDao;
import com.ejavashop.dao.shop.read.product.ProductBrandReadDao;
import com.ejavashop.dao.shop.read.product.ProductCateReadDao;
import com.ejavashop.dao.shop.read.product.ProductGoodsReadDao;
import com.ejavashop.dao.shop.read.product.ProductNormAttrOptReadDao;
import com.ejavashop.dao.shop.read.product.ProductNormReadDao;
import com.ejavashop.dao.shop.read.product.ProductPictureReadDao;
import com.ejavashop.dao.shop.read.product.ProductPriceReadDao;
import com.ejavashop.dao.shop.read.product.ProductReadDao;
import com.ejavashop.dao.shop.read.product.ProductSkuOtherReadDao;
import com.ejavashop.dao.shop.read.seller.SellerCateReadDao;
import com.ejavashop.dao.shop.read.seller.SellerReadDao;
import com.ejavashop.dao.shop.read.wmsinterface.InterfaceWmsReadDao;
import com.ejavashop.dao.shop.write.product.ProductAttrWriteDao;
import com.ejavashop.dao.shop.write.product.ProductBrandWriteDao;
import com.ejavashop.dao.shop.write.product.ProductCateWriteDao;
import com.ejavashop.dao.shop.write.product.ProductGoodsWriteDao;
import com.ejavashop.dao.shop.write.product.ProductNormAttrOptWriteDao;
import com.ejavashop.dao.shop.write.product.ProductNormWriteDao;
import com.ejavashop.dao.shop.write.product.ProductPictureWriteDao;
import com.ejavashop.dao.shop.write.product.ProductSkuOtherWriteDao;
import com.ejavashop.dao.shop.write.product.ProductTypeWriteDao;
import com.ejavashop.dao.shop.write.product.ProductWriteDao;
import com.ejavashop.dao.shop.write.seller.SellerCateWriteDao;
import com.ejavashop.dao.shop.write.seller.SellerWriteDao;
import com.ejavashop.dao.shop.write.wmsinterface.InterfaceWmsWriteDao;
import com.ejavashop.entity.product.Product;
import com.ejavashop.entity.product.ProductAttr;
import com.ejavashop.entity.product.ProductCate;
import com.ejavashop.entity.product.ProductGoods;
import com.ejavashop.entity.product.ProductNormAttrOpt;
import com.ejavashop.entity.product.ProductPicture;
import com.ejavashop.entity.product.ProductPrice;
import com.ejavashop.entity.product.ProductSkuOther;
import com.ejavashop.entity.seller.Seller;
import com.ejavashop.entity.seller.SellerCate;
import com.ejavashop.entity.wmsinterface.InterfaceWms;
import com.ejavashop.util.FrontProductPictureUtil;
import com.ejavashop.util.interfacewms.EnteringWarehouse;
import com.ejavashop.util.interfacewms.dawa_ttx_config;
import com.ejavashop.vo.product.ListProductPriceVO1;
import com.ejavashop.vo.product.ListProductPriceVO2;
import com.ejavashop.vo.product.ListProductPriceVO3;

@Service(value = "productModel")
public class ProductModel {
    private static Logger                log = LogManager.getLogger(ProductModel.class);
    
    private static final Logger  ILog = LogManager.getLogger("oms_interface");
    @Resource
    private ProductWriteDao              productWriteDao;
    @Resource
    private ProductReadDao               productReadDao;
    @Resource
    private ProductPictureWriteDao       productPictureWriteDao;
    @Resource
    private ProductPictureReadDao        productPictureReadDao;
    @Resource
    private ProductAttrWriteDao          productAttrWriteDao;
    @Resource
    private ProductGoodsWriteDao         productGoodsWriteDao;
    @Resource
    private ProductGoodsReadDao         productGoodsReadDao;
    
    @Resource(name = "transactionManager")
    private DataSourceTransactionManager transactionManager;
    @Resource
    private ProductBrandWriteDao         productBrandWriteDao;
    @Resource
    private ProductBrandReadDao         productBrandReadDao;
    @Resource
    private InterfaceWmsReadDao            interfaceWmsReadDao;
    @Resource
    private ProductCateWriteDao          productCateWriteDao;
    @Resource
    private ProductCateReadDao          productCateReadDao;
    @Resource
    private SellerCateWriteDao           sellerCateWriteDao;
    @Resource
    private SellerCateReadDao           sellerCateReadDao;
    @Resource
    private ProductNormWriteDao          productNormWriteDao;
    @Resource
    private ProductNormReadDao          productNormReadDao;
    @Resource
    private SellerReadDao                sellerReadDao;
    @Resource
    private ProductSkuOtherWriteDao      productSkuOtherWriteDao;
    @Resource
    private ProductSkuOtherReadDao      productSkuOtherReadDao;
    @Resource
    private SellerWriteDao               sellerWriteDao;
    @Resource
    private ProductTypeWriteDao          productTypeWriteDao;
    @Resource
    private ProductNormAttrOptWriteDao   productNormAttrOptWriteDao;
    @Resource
    private ProductAttrReadDao           productAttrReadDao;
    @Resource
    private ProductNormAttrOptReadDao    productNormAttrOptReadDao;
    @Resource
    private InterfaceWmsWriteDao interfaceWmsWriteDao;
    @Resource
    private ProductPriceReadDao productPriceReadDao;
    /**
     * 产品图片获取工具类
     */
    @Resource
    private FrontProductPictureUtil      frontProductPictureUtil;

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
        this.setProductMiddleImg(list);
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
        this.setProductMiddleImg(list);
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
        this.setProductMiddleImg(products);
        return products;
    }

    public Integer getProductForSellerListCount(Integer sellerId, Integer sort, Integer sellerCateId) {
        // 排序支持0到4，其他一律按0处理
        if (sort == null || sort > 4) {
            sort = 0;
        }
        return productReadDao.getProductForSellerListCount(sellerId, sort, sellerCateId);
    }

    /**
     * 给商品设置中图路径
     * @param list
     */
    private void setProductMiddleImg(List<Product> list) {
        if (list != null && list.size() > 0) {
            for (Product product : list) {
                //中图路径
                String productLeadMiddle = frontProductPictureUtil.getproductLeadMiddle(product
                    .getId());
                product.setImagePath(productLeadMiddle);
            }
        }
    }

    /**
     * 获取size个推荐商品
     * @return
     */
    public List<Product> getRecommendProducts(Integer size) {
        List<Product> list = productReadDao.getRecommendProducts(size);
        this.setProductMiddleImg(list);
        return list;
    }

    public Boolean saveProduct(Product product, List<ProductPicture> productPictureList,
                               List<ProductAttr> productAttrList) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);
        try {
            //1.check

            //2.save product
            this.dbConstrainsProduct(product);
            productWriteDao.insert(product);
            //3.save productPicture
            if (null != productPictureList && productPictureList.size() > 0) {
                for (ProductPicture picture : productPictureList) {
                    picture.setProductId(product.getId());
                    productPictureWriteDao.insert(picture);
                }
            }
            //4.save productAttr
            if (null != productAttrList && productAttrList.size() > 0) {
                for (ProductAttr attr : productAttrList) {
                    attr.setProductId(product.getId());
                    productAttrWriteDao.insert(attr);
                }
            }
            //5.save productGood
            if (null != product.getGoodsList() && product.getGoodsList().size() > 0) {
                for (ProductGoods goods : product.getGoodsList()) {
                    //goods.setNormName();颜色:黄色,尺码:XL normName:normAttrName,normName:normAttrName,
                    //                    String normName = "";
                    //                    String normAttrId = goods.getNormAttrId();
                    //                    if (!StringUtil.isEmpty(normAttrId)) {
                    //                        String[] normAttrIds = normAttrId.split(",");
                    //                        for (String attrId : normAttrIds) {
                    //                            ProductNormAttr normAttr = productNormWriteDao.getNormAttrById(Integer
                    //                                .valueOf(attrId));
                    //                            if (null != normAttr && normAttr.getProductNormId() != null) {
                    //                                ProductNorm norm = productNormWriteDao.getNormById(normAttr
                    //                                    .getProductNormId());
                    //                                if (null != norm && !StringUtil.isEmpty(norm.getName())) {
                    //                                    normName += norm.getName() + "," + normAttr.getName() + ";";
                    //                                }
                    //                            }
                    //                        }
                    //                        normName = normName.substring(0, normName.length() - 1);
                    //                        goods.setNormName(normName);
                    //                    }
                    goods.setProductId(product.getId());
                    this.dbConstrainsProductGood(goods);
                    productGoodsWriteDao.insert(goods);
                    
                  //商品添加成功推送给wms
        			
		            //商品信息同步 wms字段   货主,仓库,商品编码,品牌,商品名称,商品描述 ,商品条码,尺码/容积（ml）,颜色 ,长度 ,宽度 , 
		            //高度 , 重量,单位：默认“EA” ,单价,是否批次控制，用N、Y表示,盒/双/个 ,商品大类 （中筒袜、短袜、裸袜、辅料）,包装数量（单位EA默认1，非EA为个数）
		           // public static final String TTX_GOODS_INFO_WORDS="Company,WareHouse,Item,BrandId,Name,ItemDesc,BarCode,GoodSpec,Color,Length,Width,"
		            //		+ "Height,Weight,Unit,Sprice,ControlId,PackingClass,ItemClass,PackageQty";
//		            Map<String, Object> map = new HashMap<String,Object>();
//		            Map<String, Object> map1 = new HashMap<String,Object>();
//		            /**
//		             * 必填字段
//		             */
//		            map.put(dawa_ttx_config.RELATION_ID,product.getProductCode());
//		            map.put(dawa_ttx_config.RELATION_TABLE, dawa_ttx_config.PRODUCT);
//		            //货主
//		           // map.put("Company",""+sellerWriteDao.get(product.getSellerId()).getSellerCode().intValue());
//		            Seller seller = sellerWriteDao.get(product.getSellerId());
//		            if("10001".equals(seller.getSellerCode())){
//		            	map.put("Company", "MTY");
//					}else{
//						map.put("Company", seller.getSellerCode());
//					}
//		            //仓库
//		            map.put("WareHouse", dawa_ttx_config.WAREHOUSE_CODE);
//		            //商品编码
//		            map1.put("Item", goods.getSku().trim());
//		            //品牌
//		            map1.put("BrandId", productBrandWriteDao.getById(product.getProductBrandId()).getName());
//		            //商品名称
//		            map1.put("Name", product.getName1());
//		            //单位
//		            map1.put("Unit","EA");
//		            //是否批次控制，用N、Y表示
//		            map1.put("ControlId", "N");
//		            //盒/双/个
//		            map1.put("PackingClass", "双");
//		            String sex = productAttrWriteDao.getSize(product.getId(),ProductAttr.SOCKET_SEX).getValue();
//		            String tongHigh = productAttrWriteDao.getSize(product.getId(),ProductAttr.SOCKET_TONG_HIGH).getValue();
//		            if(sex.equals("")){
//		            	
//		            }
//		            //商品大类 （中筒袜、短袜、裸袜、辅料）
//		            if("".equals(product.getWmsCategory())){
//		            	map1.put("ItemClass","裸袜");
//		            }else{
//		            	map1.put("ItemClass",product.getWmsCategory());
//		            }
//		           // map1.put("ItemClass", productTypeWriteDao.get(productCateWriteDao.get(product.getProductCateId()).getProductTypeId()).getName());
//		            //包装数量（单位EA默认1，非EA为个数）
//		            map1.put("PackageQty", "1");
//		            //商品条码
//		            map1.put("BarCode", goods.getBarCode());
//		            //价格
//		            map1.put("Sprice",""+ goods.getMallPcPrice());
//		            //商品描述 需要转译
//		            map1.put("ItemDesc",product.getPacking());
//		            //map1.put("ItemDesc", "");
//		            //尺码/容积（ml）
//		            map1.put("GoodSpec", productAttrWriteDao.getSize(product.getId(), ProductAttr.SOCKET_GUIGE));
//		            //颜色
//		            map1.put("Color", goods.getNormName().split(",")[1]);
//		            String sizeStr = productAttrWriteDao.getSize(product.getId(),ProductAttr.SOCKET_SIZE).getValue();
//		            try {
//		            	if(!StringUtil.isEmpty(sizeStr)){
//			            	String [] size = (String [])sizeStr.split("\\*");
//			            	if(size != null  && size.length > 0){
//			            		//长度
//			                	map1.put("Length", size[0]);
//			                	//宽度
//			                	map1.put("Width",size[1]);
//			                	//高度
//					            map1.put("Height", size[2]);
//			            	}
//			            }else{
//			            	//长度
//			            	map1.put("Length", "20");
//			            	//宽度
//			            	map1.put("Width", "20");
//			            	//高度
//				            map1.put("Height", "20");
//			            }
//					} catch (Exception e) {
//						//长度
//		            	map1.put("Length", "20");
//		            	//宽度
//		            	map1.put("Width", "20");
//		            	//高度
//			            map1.put("Height", "20");
//					}
//		            //重量
//		            if( productAttrReadDao.getWeight(product.getId(), "克重（净重）") !=null ){
//		            	map1.put("Weight", productAttrReadDao.getWeight(product.getId(), "克重（净重）").getValue());
//		            }else{
//		            	map1.put("Weight","1");
//		            }
//		            map.put("Items", map1);
//		            /**
//		             * 非必填字段
//		             */
//		            //商品描述
//		            //map.put("ItemDesc", product.getDescription());
//		            //商品条码
//		            //map.put("BarCode", product.getProductCode());
//		            try {
//		    			EnteringWarehouse.goodsOwnerSync(map, dawa_ttx_config.TTX_GOODS_INFO_WORDS, dawa_ttx_config.DAWA_GOODS_INFO_WORDS, 
//		dawa_ttx_config.SUBJECT_HEADER14);
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
                }
            } else {
                //默认货品
                ProductGoods goods = new ProductGoods();
                goods.setProductId(product.getId());
                goods.setMallPcPrice(product.getMallPcPrice());
                goods.setMallMobilePrice(product.getMalMobilePrice());
                goods.setProductStock(product.getProductStock());
                goods.setProductStockWarning(5);
                goods.setActualSales(0);
                goods.setSku(product.getSku());
                goods.setImages("");
                this.dbConstrainsProductGood(goods);
                productGoodsWriteDao.insert(goods);
            }
            if(product.getSkuOther() != null && !"".equals(product.getSkuOther())){
                String arr[] = product.getSkuOther().split(",");
                if (arr.length > 0) {
                    for (int i = 0; i < arr.length; i++) {
                        ProductSkuOther productSkuOther = new ProductSkuOther();
                        productSkuOther.setProductId(product.getId());
                        productSkuOther.setSkuOther(arr[i]);
                        productSkuOtherWriteDao.insert(productSkuOther);
                    }
                }
            }
            transactionManager.commit(status);
            return true;
        } catch (BusinessException e) {
            transactionManager.rollback(status);
            throw e;
        } catch (Exception e) {
            transactionManager.rollback(status);
            log.error("ProductServiceImpl saveProduct param:" + JSON.toJSONString(product));
            log.error("ProductServiceImpl saveProduct exception:", e);
            throw e;
        }
    }
    
    /**
     * 推送商品给wms
     * @param product
     * @param goods
     */
//    public String omsProductCreate(Product product,ProductGoods goods){
//    	try {
//            InterfaceWms interfaceWms = interfaceWmsReadDao.getInterfaceByRelationIdAndRelationTable(goods.getId()+"", dawa_ttx_config.PRODUCT_GOODS);
//            //订单推送过并且推送成功
//            if (interfaceWms != null && "1".equals(interfaceWms.getSendResult())) {
//                ILog.info("商品id" + goods.getId() + ":" + product.getSeller() + ":" + goods.getSku() + "已经存在");
//                return "SKU商品已经存在";
//            }
//        	Map<String, Object> mainMap = new HashMap<String, Object>();
//        	mainMap.put(dawa_ttx_config.RELATION_ID, "" + goods.getId());
//        	mainMap.put(dawa_ttx_config.RELATION_TABLE, dawa_ttx_config.PRODUCT_GOODS);
//        	Seller seller = sellerReadDao.get(product.getSellerId());
//        	//货主编码
//        	if(seller != null ){
//        		if ("10001".equals(seller.getSellerCode())) {
//        			mainMap.put("companyCode", "MTY");
//        		}else{
//        			mainMap.put("companyCode",seller.getSellerCode());
//        		}
//        	}else{
//        		mainMap.put("companyCode", "MTY");
//        	}
//        	//商品编码
//        	mainMap.put("itemCode", product.getProductCode());
//			//商品名称
//        	mainMap.put("itemName", product.getName1());
//        	//商品分类
//        	//Map.put("itemClass",product.getProductCateId());
//            if("".equals(product.getWmsCategory())){
//            	mainMap.put("itemClass","裸袜");
//            }else{
//            	mainMap.put("itemClass",product.getWmsCategory());
//            }
//            //品牌
//            //mainMap.put("brand", productBrandWriteDao.getById(product.getProductBrandId()).getName());
//            mainMap.put("brand", product.getProductBrandId());
//        	//sku编码
//            mainMap.put("skuCode", goods.getSku());
//            //sku名称
//            mainMap.put("skuName", goods.getSku());
//            //sku简称
//            mainMap.put("shortName", "");
//            //sku 英文名称
//            mainMap.put("englishName", "");
//            //厂商货号
//            mainMap.put("mfrCode", "");
//            //颜色
//            mainMap.put("color", goods.getNormName().split(",")[1]);
//            //尺码
//            mainMap.put("size",  productAttrReadDao.getSize(product.getId(),ProductAttr.SOCKET_SIZE).getValue());
//            //款式
//            mainMap.put("style", "");
//            //图片地址
//            mainMap.put("imgeUrl", goods.getImages());
//            //保质期
//            mainMap.put("daysToExpire", "0");
//            //近效期
//            mainMap.put("daysBeforeExpire", "0");
//            //sku分类
//            //mainMap.put("skuClass", productCateWriteDao.get(product.getProductCateId()).getName());
//            mainMap.put("skuClass", product.getWmsCategory());
//            //商品货号
//            mainMap.put("itemMfrCode", goods.getSku());
//            //sku零售价 20170418 吕晓君(先判断是否有阶梯价，有的话取最高价，没有的话判断整箱价，有则取最高价，最后取一口价)
//            //价格类型 1：一口价，2：阶梯价，3:整箱价
//            if (product.getPriceStatus() == 2) {
//            	ProductPrice productPrice = productPriceReadDao.get(product.getPricePid());
//            	if (productPrice != null) {
//            		List<Double>list = new ArrayList<Double>();
//                	list.add(productPrice.getPrice1().doubleValue());
//                	list.add(productPrice.getPrice2().doubleValue());
//                	list.add(productPrice.getPrice3().doubleValue());
//                	Collections.sort(list);
//                	mainMap.put("itemListPrice", list.get(2));
//            	} else {
//            		throw new BusinessException("阶梯价不存在");
//            	}
//            	//帅帅说 整箱价 对应的就是mall_pc_price
//            } else if (product.getPriceStatus() == 3) {
//            	if (product.getMallPcPrice().compareTo(product.getScatteredPrice())  == 1) {
//            		mainMap.put("itemListPrice", product.getMallPcPrice());
//            	} else {
//            		mainMap.put("itemListPrice", product.getScatteredPrice());
//            	}
//            } else {
//            	//帅帅 说的  而且也跟晓君确认了 一口价 对应的就是sku 的单价
//            	mainMap.put("itemListPrice", goods.getMallPcPrice());
//            }
//            //标准批发价 20170418 吕晓君(product表里cost_price字段。)
//            mainMap.put("listPrice", product.getCostPrice());
//            //是否虚拟套件
//            if(goods.getIsVirtualBom() == 1){
//            	mainMap.put("isVirtualBom", "1");
//            	//是否组套
//                mainMap.put("isBom", "1");
//            }else{
//            	mainMap.put("isVirtualBom", "0");
//            	//是否组套
//                mainMap.put("isBom", "0");
//            }
//            
//            //是否共享
//            mainMap.put("shared", "0");
//            //是否抛货
//            mainMap.put("isLightGoods", "0");
//            //记录批号
//            mainMap.put("trackLot", "0");
//            //记录生产日期
//            mainMap.put("trackmfgDate", "0");
//            //记录有效期
//            mainMap.put("trackExpirationDate", "0");
//            //记录入库日期
//            mainMap.put("trackAgingDate", "0");
//            //记录批次
//            mainMap.put("trackBatch", "0");
//            //是否保税
//            mainMap.put("bonded", "0");
//            //走单标识
//            mainMap.put("singled", "0");
//            //行邮税率
//            mainMap.put("postalTaxRate", "0");
//            //企业名称
//            mainMap.put("manufacturer", "");
//            //海关备案编码
//            mainMap.put("hsCode", "");
//            //序列号控制方式
//            mainMap.put("trackSN", "none");
//            //商品备注
//            mainMap.put("remark", "");
//            //商品原产地代码
//            mainMap.put("countryOfOrigin", "");
//            //商品生产商
//            mainMap.put("addressOfOrigin", "");
//            //指定发货仓库
//            mainMap.put("warehouseCode", "");
//            //辅料换算数量
//            //mainMap.put("packageUM","");
//            //是否辅料
//            mainMap.put("fitting","0");
//            //包装方式(晓君说默认传PA)
//            mainMap.put("packType", "PA");
//            /*if(!"".equals(product.getUnit()) || StringUtil.isEmpty(product.getUnit())){
//            	if("双".equals(product.getUnit())){
//            		mainMap.put("packType", "EA");
//            	}
//            	if("袋".equals(product.getUnit())){
//            		mainMap.put("packType", "PA");
//            	}
//            	if("盒".equals(product.getUnit())){
//            		mainMap.put("packType", "BOX");
//            	}
//            }*/
//            //单品净重
//            ProductAttr pa = productAttrReadDao.getWeight(product.getId(), "克重（净重）");
//            if (pa != null){
//                try {
//                    mainMap.put("eaNetWeight", Double.parseDouble(pa.getValue()));
//                }
//                catch (Exception e) {
//                    log.error("ID为" + product.getId() + "的克重（净重），不为数字!当前值为：" + pa.getValue() );
//                    mainMap.put("eaNetWeight","1");
//                }
//            }
//            else {
//                mainMap.put("eaNetWeight","1");
//            }
//            //单品毛重
//            mainMap.put("countryOfOrigin", "");
//            //单品体积
//            String sizeStr = productAttrReadDao.getSize(product.getId(),ProductAttr.SOCKET_SIZE).getValue();
//	            try {
//	            	if(!StringUtil.isEmpty(sizeStr)){
//		            	String [] size = (String [])sizeStr.split("\\*");
//		            	if(size != null  && size.length > 0){
//		            		mainMap.put("eaVolume",""+ Double.parseDouble(size[0])*Double.parseDouble(size[1])*Double.parseDouble(size[2]));
//		            		//长度
//		            		mainMap.put("eaLength", size[0]);
//		                	//宽度
//		                	mainMap.put("eaWidth",size[1]);
//		                	//高度
//		                	mainMap.put("eaHeight", size[2]);
//		            	}
//		            }else{
//		            	mainMap.put("eaVolume", "");
//		            	//长度
//		            	mainMap.put("eaLength", "20");
//		            	//宽度
//		            	mainMap.put("eaWidth", "20");
//		            	//高度
//		            	mainMap.put("eaHeight", "20");
//		            }
//			} catch (Exception e) {
//				mainMap.put("eaVolume", "");
//				//长度
//				mainMap.put("eaLength", "20");
//            	//宽度
//				mainMap.put("eaWidth", "20");
//            	//高度
//				mainMap.put("eaHeight", "20");
//			}
//	            //重量单位
//            mainMap.put("eaWeightUm", "g");
//            //体积单位
//            mainMap.put("eaVolumeUm", "cm3");
//            //重量单位
//            mainMap.put("eaDimensionUm", "cm");
//            //单品条码
//            mainMap.put("eaBarcodes",goods.getBarCode());
//            //箱换算数量
//            mainMap.put("csConversionQty", product.getFullContainerQuantity());
//            //箱净重
//            mainMap.put("csNetWeight", "");
//            //箱毛重
//            mainMap.put("csGrossWeight", "");
//            //箱体积
//            mainMap.put("csVolume", "");
//            //箱长
//            mainMap.put("csLength", "");
//            //箱宽
//            mainMap.put("csWidth", "");
//            //箱高
//            mainMap.put("csHeight", "");
//            //箱重量单位
//            mainMap.put("csWeightUm", "");
//            //箱体积单位
//            mainMap.put("csVolumeUm", "");
//            //箱长度单位
//            mainMap.put("csDimensionUm", "");
//            //箱条码
//            mainMap.put("csBarcodes", goods.getBarCodeCS());
//            //中包装数量
//            mainMap.put("ipQty", "");
//            //中包装条码
//            mainMap.put("ipBarcodes", goods.getBarCodePL());
//            
//            return EnteringWarehouse.commonSyncOMS(mainMap, dawa_ttx_config.OMS_SKU_CREATE);
//            
//		} catch (Exception e) {
//			e.printStackTrace();
//			String relationId = "" + goods.getId();
//			String relationTable = dawa_ttx_config.PRODUCT_GOODS;
//			String api = dawa_ttx_config.OMS_SKU_CREATE ;
//			String errorMsg = "拼装商品信息出错：" + e.getMessage();
//			InterfaceWms interfaceWms  = interfaceWmsReadDao.getInterfaceByRelationIdAndRelationTable(relationId,relationTable);
//			if(interfaceWms == null){
//				InterfaceWms interfaceWmsNew = new InterfaceWms();
//				interfaceWmsNew.setRalationTable(relationTable);
//				interfaceWmsNew.setRelationId(relationId);
//				interfaceWmsNew.setSendNo(1);
//				interfaceWmsNew.setSendResult("0");
//				interfaceWmsNew.setSyncTime(new Date());
//				interfaceWmsNew.setSyncType(api);
//				if(!StringUtil.isEmpty(errorMsg) && errorMsg.length() >= 1000){
//					interfaceWmsNew.setErrorMsg(errorMsg.substring(0, 1000));
//				}else{
//					interfaceWmsNew.setErrorMsg(errorMsg);
//				}
//				interfaceWmsWriteDao.insert(interfaceWmsNew);
//				ILog.error("拼装商品信息出错，sku为：" + goods.getSku() + "，错误信息为" + interfaceWmsNew.getErrorMsg());
//			}else{
//				interfaceWms.setSendNo(interfaceWms.getSendNo()+1);
//				if(!StringUtil.isEmpty(errorMsg) && errorMsg.length() >= 1000){
//					interfaceWms.setErrorMsg(errorMsg.substring(0, 1000));
//				}else{
//					interfaceWms.setErrorMsg(errorMsg);
//				}
//				interfaceWmsWriteDao.update(interfaceWms);
//				ILog.error("拼装商品信息出错，sku为：" + goods.getSku() + "，错误信息为" + interfaceWms.getErrorMsg());
//			}
//			return "系统错误："+e.getMessage();
//		}
//    }
    

    public Boolean updateProduct(Product product, List<ProductPicture> productPictureList,
                                 List<ProductAttr> productAttrList) {
        //如果商品被删除,取消推荐
        if (product != null && null != product.getState() && product.getState() == Product.STATE_5) {
            product.setIsTop(ConstantsEJS.PRODUCT_IS_TOP_1);
        }

        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);
        try {
            if (null == product)
                throw new BusinessException("更新商品失败，商品信息为空");
            if (null == product.getId() || 0 == product.getId())
                throw new BusinessException("更新商品失败，商品id为空");

            /**更新商品**/
            productWriteDao.update(product);
            if(product.getSkuOther() != null && !"".equals(product.getSkuOther())){
                String arr[] = product.getSkuOther().split(",");
                productSkuOtherWriteDao.delete(product.getId());
                if(arr.length>0){
                    for(int i = 0;i<arr.length;i++){
                        ProductSkuOther productSkuOther = new ProductSkuOther();
                        productSkuOther.setProductId(product.getId());
                        productSkuOther.setSkuOther(arr[i]);
                        productSkuOtherWriteDao.insert(productSkuOther);
                    }
                }
            }
            /**更新商品图片**/
            if (null != productPictureList && productPictureList.size() > 0) {
                productPictureWriteDao.delByProductId(product.getId());
                for (ProductPicture picture : productPictureList) {
                    picture.setProductId(product.getId());
                    productPictureWriteDao.insert(picture);
                }
            }

            /**更新商品**/
            if (null != productAttrList && productAttrList.size() > 0) {
                productAttrWriteDao.delByProductId(product.getId());
                for (ProductAttr attr : productAttrList) {
                    attr.setProductId(product.getId());
                    productAttrWriteDao.insert(attr);
                }
            }

            /**更新货品**/
            if (null != product.getGoodsList() && product.getGoodsList().size() > 0) {
                for (ProductGoods goods : product.getGoodsList()) {
                    //goods.setNormName();颜色:黄色,尺码:XL normName:normAttrName,normName:normAttrName,
                    //                    String normName = "";
                    //                    String normAttrId = goods.getNormAttrId();
                    //                    if (!StringUtil.isEmpty(normAttrId)) {
                    //                        String[] normAttrIds = normAttrId.split(",");
                    //                        for (String attrId : normAttrIds) {
                    //                            ProductNormAttr normAttr = productNormWriteDao.getNormAttrById(Integer
                    //                                .valueOf(attrId));
                    //                            if (null != normAttr && normAttr.getProductNormId() != null) {
                    //                                ProductNorm norm = productNormWriteDao.getNormById(normAttr
                    //                                    .getProductNormId());
                    //                                if (null != norm && !StringUtil.isEmpty(norm.getName())) {
                    //                                    normName += norm.getName() + "," + normAttr.getName() + ";";
                    //                                }
                    //                            }
                    //                        }
                    //                        normName = normName.substring(0, normName.length() - 1);
                    //                        goods.setNormName(normName);
                    //                    }
                    goods.setProductId(product.getId());
                    this.dbConstrainsProductGood(goods);
                    ProductGoods dbGood = productGoodsReadDao.getByProductIdAndAttrId(
                        product.getId(), goods.getNormAttrId());
                    if (null != dbGood) {
                        goods.setId(dbGood.getId());
                        productGoodsWriteDao.update(goods);
                    } else {
                        productGoodsWriteDao.insert(goods);
                    }
                    
                   /* 
                  //商品添加成功推送给wms
        			
		            //商品信息同步 wms字段   货主,仓库,商品编码,品牌,商品名称,商品描述 ,商品条码,尺码/容积（ml）,颜色 ,长度 ,宽度 , 
		            //高度 , 重量,单位：默认“EA” ,单价,是否批次控制，用N、Y表示,盒/双/个 ,商品大类 （中筒袜、短袜、裸袜、辅料）,包装数量（单位EA默认1，非EA为个数）
		           // public static final String TTX_GOODS_INFO_WORDS="Company,WareHouse,Item,BrandId,Name,ItemDesc,BarCode,GoodSpec,Color,Length,Width,"
		            //		+ "Height,Weight,Unit,Sprice,ControlId,PackingClass,ItemClass,PackageQty";
		            Map<String, Object> map = new HashMap<String,Object>();
		            Map<String, Object> map1 = new HashMap<String,Object>();
		            *//**
		             * 必填字段
		             *//*
		            map.put(dawa_ttx_config.RELATION_ID,""+ product.getId().intValue());
		            map.put(dawa_ttx_config.RELATION_TABLE, dawa_ttx_config.PRODUCT);
		            //货主
		           // map.put("Company",""+sellerWriteDao.get(product.getSellerId()).getSellerCode().intValue());
		            Seller seller = sellerWriteDao.get(product.getSellerId());
		            if("10001".equals(seller.getSellerCode())){
		            	map.put("Company", "MTY");
					}else{
						map.put("Company", seller.getSellerCode());
					}
		            //仓库
		            map.put("WareHouse", dawa_ttx_config.WAREHOUSE_CODE);
		            //商品编码
		            map1.put("Item", goods.getSku().trim());
		            //品牌
		            map1.put("BrandId", productBrandWriteDao.getById(product.getProductBrandId()).getName());
		            //商品名称
		            map1.put("Name", goods.getProductName());
		            //单位
		            map1.put("Unit","EA");
		            //是否批次控制，用N、Y表示
		            map1.put("ControlId", "N");
		            //盒/双/个
		            map1.put("PackingClass", "双");
		            String sex = productAttrWriteDao.getSize(product.getId(),ProductAttr.SOCKET_SEX).getValue();
		            String tongHigh = productAttrWriteDao.getSize(product.getId(),ProductAttr.SOCKET_TONG_HIGH).getValue();
		            if(sex.equals("")){
		            	
		            }
		            //商品大类 （中筒袜、短袜、裸袜、辅料）
		            map1.put("ItemClass", productTypeWriteDao.get(productCateWriteDao.get(product.getProductCateId()).getProductTypeId()).getName());
		            //包装数量（单位EA默认1，非EA为个数）
		            map1.put("PackageQty", "1");
		            //商品条码
		            map1.put("BarCode", goods.getBarCode());
		            //价格
		            map1.put("Sprice",""+ goods.getMallPcPrice());
		            //商品描述
		           // map1.put("ItemDesc", product.getDescription());
		            map1.put("ItemDesc", product.getPacking());
		            //尺码/容积（ml）
		            map1.put("GoodSpec", productAttrWriteDao.getSize(product.getId(), ProductAttr.SOCKET_GUIGE));
		            //颜色
		            map1.put("Color", goods.getNormName().split(",")[1]);
		            String sizeStr = productAttrWriteDao.getSize(product.getId(),ProductAttr.SOCKET_SIZE).getValue();
		            try {
		            	if(!StringUtil.isEmpty(sizeStr)){
			            	String [] size = (String [])sizeStr.split("\\*");
			            	if(size != null  && size.length > 0){
			            		//长度
			                	map1.put("Length", size[0]);
			                	//宽度
			                	map1.put("Width",size[1]);
			                	//高度
					            map1.put("Height", size[2]);
			            	}
			            }else{
			            	//长度
			            	map1.put("Length", "20");
			            	//宽度
			            	map1.put("Width", "20");
			            	//高度
				            map1.put("Height", "20");
			            }
					 } catch (Exception e) {
						//长度
		            	map1.put("Length", "20");
		            	//宽度
		            	map1.put("Width", "20");
		            	//高度
			            map1.put("Height", "20");
					}
		            //重量
		            if( productAttrReadDao.getWeight(product.getId(), "克重（净重）") !=null ){
		            	map1.put("Weight", productAttrReadDao.getWeight(product.getId(), "克重（净重）").getValue());
		            }else{
		            	map1.put("Weight","1");
		            }
		            map.put("Items", map1);
		            *//**
		             * 非必填字段
		             *//*
		            //商品描述
		            //map.put("ItemDesc", product.getDescription());
		            //商品条码
		            //map.put("BarCode", product.getProductCode());
		            try {
		    			EnteringWarehouse.goodsOwnerSync(map, dawa_ttx_config.TTX_GOODS_INFO_WORDS, dawa_ttx_config.DAWA_GOODS_INFO_WORDS, 
		dawa_ttx_config.SUBJECT_HEADER14);
					} catch (Exception e) {
						e.printStackTrace();
					}*/
                }
            } else {
                // 默认货品
                ProductGoods goods = productGoodsWriteDao.getByProductId(product.getId());
                goods.setProductId(product.getId());
                goods.setMallPcPrice(product.getMallPcPrice());
                goods.setMallMobilePrice(product.getMalMobilePrice());
                goods.setProductStock(product.getProductStock());
                goods.setProductStockWarning(5);
                // goods.setActualSales(0);
                goods.setSku(product.getSku());
                goods.setImages("");
                this.dbConstrainsProductGood(goods);
                productGoodsWriteDao.update(goods);
            }
            transactionManager.commit(status);
            return true;
        } catch (BusinessException e) {
            transactionManager.rollback(status);
            throw e;
        } catch (Exception e) {
            transactionManager.rollback(status);
            log.error("ProductServiceImpl updateProduct param:" + JSON.toJSONString(product));
            log.error("ProductServiceImpl updateProduct exception:", e);
            throw e;
        }
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

    public Product getProductById(Integer productId) {
        if (null == productId || 0 == productId)
            throw new BusinessException("根据id获取商品表失败，id为空");

        Product product = productReadDao.get(productId);
        if (product != null && product.getIsNorm() != 2) {
            ProductGoods goods = productGoodsWriteDao.getByProductId(productId);
            product.setSku(goods.getSku());
        }

        List<ProductSkuOther> psoList = productSkuOtherReadDao
            .queryProductSkuOtherByProductId(product.getId());
        if (psoList != null && psoList.size() > 0) {
            String skuOther = "";
            for (ProductSkuOther pso : psoList) {
                skuOther += pso.getSkuOther() + ",";
            }
            //product.setSkuOther(skuOther.substring(0, skuOther.length() - 1));
        }
        return product;
    }

    public List<Product> pageProduct(Map<String, String> queryMap, PagerInfo pager)
                                                                                   throws Exception {
        List<Product> list = new ArrayList<Product>();
        try {
            Integer start = 0, size = 0;
            String state = queryMap.get("q_state");
            String priceStatus = queryMap.get("q_priceStatus")==null?"":queryMap.get("q_priceStatus");
            if (!StringUtil.isEmpty(state) && state.indexOf(",") != -1) {
                if (pager != null) {
                    pager.setRowsCount(productReadDao.count1(queryMap));
                    start = pager.getStart();
                    size = pager.getPageSize();
                }
                if(!priceStatus.equals("") && priceStatus.equals("2")){
                    list = productReadDao.page2(queryMap, start, size);
                }else{
                    list = productReadDao.page1(queryMap, start, size);
                }
                
            } else {
                if (pager != null) {
                    pager.setRowsCount(productReadDao.count(queryMap));
                    start = pager.getStart();
                    size = pager.getPageSize();
                }
                if(!priceStatus.equals("") && priceStatus.equals("2")){
                    list = productReadDao.page2(queryMap, start, size);
                }else{
                    list = productReadDao.page(queryMap, start, size);
                }
            }

            for (Product pro : list) {
                ProductCate pcate = productCateReadDao.get(pro.getProductCateId());
                pro.setProductCateName(pcate == null ? null : pcate.getName());
                pro.setProductBrandName(productBrandReadDao.getById(pro.getProductBrandId())
                    .getName());
                SellerCate cate = sellerCateReadDao.get(pro.getSellerCateId());
                pro.setSellerCateName(cate == null ? null : cate.getName());

                Seller seller = sellerReadDao.get(pro.getSellerId());
                if (null != seller && !StringUtil.isEmpty(seller.getSellerName())) {
                    pro.setSeller(seller.getSellerName());
                }
            }

        } catch (Exception e) {
            throw new Exception(e);
        }
        return list;
    }

    public List<Product> getProductsBySellerId(Integer sellerid) {
        if (sellerid == null)
            throw new BusinessException("没有商家");
        return productReadDao.getProductsBySellerId(sellerid);
    }

    public boolean saveOrupdate(Product product, List<ProductPicture> productPictureList,
                                List<ProductAttr> productAttrList,
                                List<ProductGoods> productGoodsList,
                                List<ProductNormAttrOpt> optlist) throws Exception {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);
        try {

            if (product.getId() == null || product.getId() == 0) {
                productWriteDao.insert(product);
            } else {
                //更新商品前删除之前所有关联
                deleteProRelation(product.getId());
                productWriteDao.update(product);
            }
            //3.save productPicture
            if (null != productPictureList && productPictureList.size() > 0) {
                for (ProductPicture picture : productPictureList) {
                    picture.setProductId(product.getId());
                    productPictureWriteDao.insert(picture);
                }
            }
            //4.save productAttr
            if (null != productAttrList && productAttrList.size() > 0) {
                for (ProductAttr attr : productAttrList) {
                    attr.setProductId(product.getId());
                    productAttrWriteDao.insert(attr);
                }
            }
            //5.save productGoods
            if (null != productGoodsList && productGoodsList.size() > 0) {
                for (ProductGoods goods : productGoodsList) {
                    goods.setProductId(product.getId());
                    productGoodsWriteDao.insert(goods);
                }
            }
            //6.save productNormAttrOpt
            if (null != optlist && optlist.size() > 0) {
                for (ProductNormAttrOpt opt : optlist) {
                    opt.setProductId(product.getId());
                    productNormWriteDao.insertNormAttrOpt(opt);
                }
            }
            transactionManager.commit(status);
        } catch (BusinessException e) {
            transactionManager.rollback(status);
            log.error("ProductServiceImpl saveProduct exception:", e);
            throw e;
        } catch (Exception e) {
            transactionManager.rollback(status);
            log.error("ProductServiceImpl saveProduct param:" + JSON.toJSONString(product));
            log.error("ProductServiceImpl saveProduct exception:", e);
            throw e;
        }
        return true;
    }

    /**
     * 删除商品关联信息
     * @param proid
     */
    private void deleteProRelation(Integer proid) throws Exception {
        Assert.notNull(proid);

        //1.delete productPicture
        productPictureWriteDao.delByProductId(proid);

        //2.delete productAttr
        productAttrWriteDao.delByProductId(proid);

        //3.delete productGoods
        productGoodsWriteDao.deleteByProductId(proid);

        //4.delete productNormAttrOpt
        productNormWriteDao.deleteProductNormOptByProductId(proid);

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
	/**
     * 分页  楼层数据添加商品时，过滤已经是楼层数据的商品
     * @param queryMap
     * @param pager
     * @return
     */
	public List<Product> pageProductByh5fllordata(Map<String, String> queryMap,
			PagerInfo pager,List<Integer> productIds) throws Exception {
        List<Product> list = new ArrayList<Product>();
        try {
            Integer start = 0, size = 0;
            String state = queryMap.get("q_state");
            if (!StringUtil.isEmpty(state) && state.indexOf(",") != -1) {
                if (pager != null) {
                    pager.setRowsCount(productReadDao.count1(queryMap));
                    start = pager.getStart();
                    size = pager.getPageSize();
                }
                list = productReadDao.pageProductByh5fllordata(queryMap, start, size,productIds);
            } else {
                if (pager != null) {
                    pager.setRowsCount(productReadDao.count(queryMap));
                    start = pager.getStart();
                    size = pager.getPageSize();
                }
                list = productReadDao.pageProductByh5fllordata(queryMap, start, size,productIds);
            }

            for (Product pro : list) {
                ProductCate pcate = productCateReadDao.get(pro.getProductCateId());
                pro.setProductCateName(pcate == null ? null : pcate.getName());
                pro.setProductBrandName(productBrandReadDao.getById(pro.getProductBrandId())
                    .getName());
                SellerCate cate = sellerCateReadDao.get(pro.getSellerCateId());
                pro.setSellerCateName(cate == null ? null : cate.getName());

                Seller seller = sellerReadDao.get(pro.getSellerId());
                if (null != seller && !StringUtil.isEmpty(seller.getSellerName())) {
                    pro.setSeller(seller.getSellerName());
                }
            }

        } catch (Exception e) {
            throw new Exception(e);
        }
        return list;
    }

	public List<Product> pageproductBypcfloordata(Map<String, String> queryMap,
			PagerInfo pager, List<Integer> productIds) throws Exception  {
        List<Product> list = new ArrayList<Product>();
        try {
            Integer start = 0, size = 0;
            String state = queryMap.get("q_state");
            if (!StringUtil.isEmpty(state) && state.indexOf(",") != -1) {
                if (pager != null) {
                    pager.setRowsCount(productReadDao.count1(queryMap));
                    start = pager.getStart();
                    size = pager.getPageSize();
                }
                list = productReadDao.pageproductBypcfloordata(queryMap, start, size,productIds);
            } else {
                if (pager != null) {
                    pager.setRowsCount(productReadDao.count(queryMap));
                    start = pager.getStart();
                    size = pager.getPageSize();
                }
                list = productReadDao.pageproductBypcfloordata(queryMap, start, size,productIds);
            }

            for (Product pro : list) {
                ProductCate pcate = productCateReadDao.get(pro.getProductCateId());
                pro.setProductCateName(pcate == null ? null : pcate.getName());
                pro.setProductBrandName(productBrandReadDao.getById(pro.getProductBrandId())
                    .getName());
                SellerCate cate = sellerCateReadDao.get(pro.getSellerCateId());
                pro.setSellerCateName(cate == null ? null : cate.getName());

                Seller seller = sellerReadDao.get(pro.getSellerId());
                if (null != seller && !StringUtil.isEmpty(seller.getSellerName())) {
                    pro.setSeller(seller.getSellerName());
                }
            }

        } catch (Exception e) {
            throw new Exception(e);
        }
        return list;
    }
	
	public Integer downProduct(){
		Integer productSum = 0;
		//首先下架猫头鹰的
		//Terry 20160923 小于100的不自动下架
		/*List<Product> productList =  productWriteDao.downMTYProduct();
		if(productList != null && productList.size() > 0){
			productSum = productList.size();
			for (Product product : productList) {
				if(product != null){
					product.setState(7);
					productWriteDao.update(product);
				}
			}
		}*/
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

    public void saveGoodsAndAttr(Map<String, String> dataMap, int c, Integer productId,
                                 Integer productGoodsId) {
        ProductGoods productGoods = productGoodsReadDao.get(productGoodsId);
        if(productGoods != null){
            productGoods.setSku(dataMap.get("q_sku"+c));
            productGoods.setNormName(dataMap.get("q_normAttr"+c));
            Integer normAttrId = this.getNormAttrId(dataMap.get("q_normAttr"+c),productId);
            productGoods.setNormAttrId(normAttrId.toString());
            productGoods.setProductStock(0);
            productGoods.setImages("-1");
            productGoods.setBarCode(dataMap.get("q_sku"+c));
            productGoods.setId(null);
            productGoods.setBarCode(dataMap.get("q_barCode"+c));
            productGoods.setBarCodePL(dataMap.get("q_barCodePL"+c));
            productGoods.setBarCodeCS(dataMap.get("q_barCodeCS"+c));
            productGoods.setIsVirtualBom(Integer.parseInt(dataMap.get("q_isVirtualBom"+c)));
            productGoodsWriteDao.insert(productGoods);
            
            ProductNormAttrOpt productNormAttrOpt = productNormAttrOptReadDao.getAttrOptByProductId(productId);
            if(productNormAttrOpt!=null){
                productNormAttrOpt.setId(null);
                productNormAttrOpt.setName(dataMap.get("q_normAttr"+c).substring(4));
                productNormAttrOpt.setImage("-1");
                productNormAttrOpt.setAttrId(normAttrId);
                if(normAttrId<=10){
                    productNormAttrOpt.setTypeAttr(1);
                }else{
                    productNormAttrOpt.setTypeAttr(2);
                }
                productNormAttrOptWriteDao.save(productNormAttrOpt);
            }
        }
    }

    private Integer getNormAttrId(String productGoodsAttr,Integer productId) {
        Integer attr_id = 0;
        String norm_color = productGoodsAttr.substring(4);
        if(norm_color.equals("黑色")){
            attr_id = 1;
        }else if(norm_color.equals("红色")){
            attr_id = 2;
        }else if(norm_color.equals("酒红")){
            attr_id = 3;
        }else if(norm_color.equals("棕色")){
            attr_id = 4;
        }else if(norm_color.equals("蓝色")){
            attr_id = 5;
        }else if(norm_color.equals("卡其")){
            attr_id = 6;
        }else if(norm_color.equals("白色")){
            attr_id = 7;
        }else if(norm_color.equals("灰色")){
            attr_id = 8;
        }else if(norm_color.equals("丈青")){
            attr_id = 9;
        }else if(norm_color.equals("墨绿")){
            attr_id = 10;
        }else{
            attr_id = productGoodsReadDao.getMaxAttrIdByProductId(productId);
            attr_id +=1;
        }
        return attr_id;
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
    
    public List<ListProductPriceVO1> listProductPrice1 () {
    	return productReadDao.listProductPrice1();
    }
    public List<ListProductPriceVO2> listProductPrice2 () {
    	return productReadDao.listProductPrice2();
    }
    public List<ListProductPriceVO3> listProductPrice3 () {
    	return productReadDao.listProductPrice3();
    }
	
}
