package com.ejavashop.web.controller.product;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.ConvertUtil;
import com.ejavashop.core.HttpJsonResult;
import com.ejavashop.core.HttpJsonResultForAjax;
import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.StringUtil;
import com.ejavashop.core.WebUtil;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.core.freemarkerutil.DomainUrlUtil;
import com.ejavashop.entity.operate.ProductLabel;
import com.ejavashop.entity.operate.ProductPackage;
import com.ejavashop.entity.product.Product;
import com.ejavashop.entity.product.ProductAttr;
import com.ejavashop.entity.product.ProductBrand;
import com.ejavashop.entity.product.ProductCate;
import com.ejavashop.entity.product.ProductGoods;
import com.ejavashop.entity.product.ProductNorm;
import com.ejavashop.entity.product.ProductNormAttr;
import com.ejavashop.entity.product.ProductNormAttrOpt;
import com.ejavashop.entity.product.ProductPicture;
import com.ejavashop.entity.product.ProductPrice;
import com.ejavashop.entity.product.ProductType;
import com.ejavashop.entity.product.ProductTypeAttr;
import com.ejavashop.entity.product.WmsClassify;
import com.ejavashop.entity.seller.Seller;
import com.ejavashop.entity.seller.SellerUser;
import com.ejavashop.entity.system.SystemLogs;
import com.ejavashop.entity.system.SystemLogsConstants;
import com.ejavashop.service.operate.IProductLabelService;
import com.ejavashop.service.operate.IProductPackageService;
import com.ejavashop.service.product.IProductAttrService;
import com.ejavashop.service.product.IProductBrandService;
import com.ejavashop.service.product.IProductCateService;
import com.ejavashop.service.product.IProductGoodsService;
import com.ejavashop.service.product.IProductNormAttrOptService;
import com.ejavashop.service.product.IProductNormService;
import com.ejavashop.service.product.IProductPictureService;
import com.ejavashop.service.product.IProductPriceService;
import com.ejavashop.service.product.IProductService;
import com.ejavashop.service.product.IProductTypeAttrService;
import com.ejavashop.service.product.IProductTypeService;
import com.ejavashop.service.product.ISellerApplyBrandService;
import com.ejavashop.service.product.ISellerCateService;
import com.ejavashop.service.product.ISellerManageCateService;
//import com.ejavashop.service.product.IWmsClassifyService;
import com.ejavashop.service.seller.ISellerService;
import com.ejavashop.service.seller.ISellerTransportService;
import com.ejavashop.service.system.ISystemLogsService;
import com.ejavashop.web.basic.CsrfTokenManager;
import com.ejavashop.web.controller.BaseController;
import com.ejavashop.web.util.UploadUtil;
import com.ejavashop.web.util.WebSellerSession;

import net.sf.json.JSONObject;

/**
 * 商品管理controller
 *                       
 * @Filename: SellerProductController.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Controller
@RequestMapping(value = "seller/product")
public class SellerProductController extends BaseController {
    @Resource
    private ISellerManageCateService   sellerManageCateService;
    @Resource
    private ISellerApplyBrandService   sellerApplyBrandService;
    @Resource
    private IProductCateService        productCateService;
    @Resource
    private IProductTypeAttrService    sellerProductTypeAttrService;
    @Resource
    private IProductTypeService        sellerProductTypeService;
    @Resource
    private IProductNormService        sellerProductNormService;
    @Resource
    private IProductBrandService       productBrandService;
    @Resource
    private ISellerCateService         sellerCateService;
    @Resource
    private IProductService            productService;
    @Resource
    private ISellerTransportService    sellerTransportService;
    @Resource
    private IProductPictureService     productPicService;
    @Resource
    private IProductAttrService        productAttrService;
    @Resource
    private IProductGoodsService       productGoodsService;
    @Resource
    private IProductNormAttrOptService productNormAttrOptService;
    @Resource
    private IProductPriceService       productPriceService;
    @Resource
    private ISellerService             sellerService;
    @Resource
    private IProductPackageService     productPackageService;
    @Resource
    private IProductLabelService       productLabelService;
    @Resource
    private ISystemLogsService         systemLogsService;
//    @Resource
//    private IWmsClassifyService        wmsClassifyService;
    
    private String                     baseUrl = "seller/product/pdt/";
    private Logger                     log     = Logger.getLogger(SellerProductController.class);

    /**
     * 库存价格设置
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "goodsSet", method = { RequestMethod.GET })
    public String goodsSet(HttpServletRequest request, Map<String, Object> dataMap, Integer id,
                           String from) {
        //查询商品信息 
        ServiceResult<Product> productServiceResult = productService.getProductById(id);
        if (!productServiceResult.getSuccess() || null == productServiceResult.getResult()) {
            dataMap.put("error", "商品不存在");
        }
        Product product = productServiceResult.getResult();

        ServiceResult<List<ProductGoods>> productGoodsServiceResult = productGoodsService
            .getGoodSByProductId(id);
        List<ProductGoods> goodslist = productGoodsServiceResult.getResult();
        if (product.getPriceStatus() != null && product.getPricePid() != null) {
            ServiceResult<ProductPrice> productPrice = productPriceService
                .getProductPriceById(product.getPricePid());
            dataMap.put("productPrice", productPrice.getResult());
        }
        Integer num = 10;
        ServiceResult<ProductAttr> productAttrResult = productAttrService.getByProductIdAndName(id, "包装规格");
        ProductAttr productAttr = productAttrResult.getResult();
        try {
            if(productAttr != null){
                String val = productAttr.getValue();
                String v[] = val.split("双");
                num = Integer.valueOf(v[0]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        dataMap.put("product", product);
        dataMap.put("goodslist", goodslist);
        dataMap.put("number", num);
        dataMap.put("from", from);

        return baseUrl + "goodsset";
    }

    /**
     * 打标价格设置
     * @param request
     * @param dataMap
     * @param id
     * @param from
     * @return
     */
    @RequestMapping(value = "goodsSet2", method = { RequestMethod.GET })
    public String goodsSet2(HttpServletRequest request, Map<String, Object> dataMap, Integer id,
                           String from) {
    	String rtnPath = baseUrl + "goodsSet2";
        //查询商品信息 
        ServiceResult<Product> productServiceResult = productService.getProductById(id);
        if (!productServiceResult.getSuccess() || null == productServiceResult.getResult()) {
            dataMap.put("error", "商品不存在");
        }
        Product product = productServiceResult.getResult();
        if(product != null){
        	//通过关联的打标方式ID，taocanId，查询二次加工对象
        	if(product.getTaocanId()!=null && !"".equals(product.getTaocanId())){
        		ServiceResult<ProductPackage> ProductPackageServiceResult = productPackageService.getProductPackageById(product.getTaocanId());
        		dataMap.put("productPackage", ProductPackageServiceResult.getResult());
        	}
        	//通过关联的商标ID，查询商标对象
        	if(product.getSkuOther()!=null && !"".equals(product.getSkuOther())){
        		ServiceResult<ProductLabel> ProductLabelserviceResult = productLabelService.getProductLabelById(Integer.valueOf(product.getSkuOther()));
        		dataMap.put("productLabel", ProductLabelserviceResult.getResult());
        	}
        }
        ServiceResult<List<ProductGoods>> productGoodsServiceResult = productGoodsService
            .getGoodSByProductId(id);
        List<ProductGoods> goodslist = productGoodsServiceResult.getResult();
        if (product.getPrice2Status() != null && product.getPrice2Pid() != null) {
            ServiceResult<ProductPrice> productPrice = productPriceService
                .getProductPriceById(product.getPrice2Pid());
            dataMap.put("productPrice", productPrice.getResult());
        }
        
        /**2.判断是否选择商品分类*/
        String cateId = request.getParameter("cateId") == null ? "" : "1";
        dataMap.put("cateId", cateId);
        /**4.获取商家可以经营的品牌**/
        ServiceResult<ProductCate> cateResult = productCateService.getProductCateById(Integer
            .valueOf(cateId));
        if (!cateResult.getSuccess() || cateResult.getResult() == null) {
            dataMap.put("message", "该分类下无可以经营的品牌，请重新选择分类，或者联系商城管理员");
            return rtnPath;
        }
        ServiceResult<List<ProductBrand>> brandResult = productBrandService
                .getBrandByTypeId(cateResult.getResult().getProductTypeId());
            if (brandResult.getSuccess() && brandResult.getResult() != null) {
                dataMap.put("brand", brandResult.getResult());
            }
            
        dataMap.put("product", product);
        dataMap.put("goodslist", goodslist);
        dataMap.put("from", from);
        /**商品图片**/
        ServiceResult<List<ProductPicture>> pictureServiceResult = productPicService
            .getProductPictureByProductId(product.getId());
        String flagforaddorupdateIMG = "not";
        if (pictureServiceResult.getSuccess() && pictureServiceResult.getResult() != null) {
            for (ProductPicture pic : pictureServiceResult.getResult()) {
                String path = pic.getImagePath();
                path = DomainUrlUtil.getEJS_IMAGE_RESOURCES() + path;
                pic.setImagePath(path);
                if(pic.getSort()!=null && pic.getSort()==5){
                	//说明已经存在一张打标图片了
                	flagforaddorupdateIMG = "yes";
                }
            }
            dataMap.put("flagforaddorupdateIMG", flagforaddorupdateIMG);
            dataMap.put("pic", pictureServiceResult.getResult());
        }
        
        return baseUrl + "goodsset2";
    }
    
    @RequestMapping(value = "addSKU", method = { RequestMethod.GET })
    public String addSKU(HttpServletRequest request, Map<String, Object> dataMap, Integer id) {
        ServiceResult<Product> result = productService.getProductById(id);
        Product product = result.getResult();
        dataMap.put("product", product);
        if(product!=null){
            ServiceResult<List<ProductGoods>>  pgResult = productGoodsService.getGoodSByProductId(id);
            dataMap.put("productGoods", pgResult.getResult());
            dataMap.put("maxrow", pgResult.getResult().size());
        }
        return "seller/product/pdt/addproductsku";
    }
    
    /**
     * 保存新增的sku
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "save", method = { RequestMethod.POST })
    public @ResponseBody HttpJsonResult<Object> save(HttpServletRequest request, Map<String, Object> dataMap) {
        HttpJsonResult<Object> jsonResult = new HttpJsonResult<Object>();
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        Integer count_s = Integer.valueOf(request.getParameter("maxrow_old"));
        Integer count_e = Integer.valueOf(request.getParameter("maxrow"));
        Integer productId = Integer.valueOf(request.getParameter("productId"));
        Integer productGoodsId = Integer.valueOf(request.getParameter("productGoodsId"));
        try {
            for(int i=count_s ; i<count_e ; i++){
                Integer c = i+1;
                productService.saveGoodsAndAttr(queryMap,c,productId,productGoodsId);
            }
            jsonResult.setMessage("保存成功");
            SellerUser user = WebSellerSession.getSellerUser(request);
            systemLogsService.saveSystemLogs(user.getId(), user.getName(), JSONObject.fromObject(queryMap).toString(), productId, "product", SystemLogsConstants.ADD_SKU, "seller_user");
            jsonResult.setBackUrl("waitSale");
        } catch (Exception e) {
            e.printStackTrace();
            jsonResult.setMessage(e.getMessage());
        }
        
        ServiceResult<Product> result = productService.getProductById(productId);
        Product product = result.getResult();
        dataMap.put("product", product);
        if(product!=null){
            ServiceResult<List<ProductGoods>>  pgResult = productGoodsService.getGoodSByProductId(productId);
            dataMap.put("productGoods", pgResult.getResult());
            dataMap.put("maxrow", pgResult.getResult().size());
        }
        return jsonResult;
    }
    
    
    @RequestMapping(value = "valide", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<Object> valide(HttpServletRequest request,
                                                            @RequestParam("productId") String productId,
                                                            @RequestParam("sku") String sku) {
        HttpJsonResult<Object> jsonResult = new HttpJsonResult<Object>();
        jsonResult.setMessage("");
        if (!"".equals(sku)) {//sku校验
            Map<String, String> queryMap = new HashMap<String, String>();
            queryMap.put("productId", productId);
            queryMap.put("sku", sku);
            try {
                ServiceResult<List<ProductGoods>> serviceResult = productGoodsService
                        .checkProductBySKUAndProductId(queryMap);
                    if (serviceResult.getResult().size()>0) {
                        jsonResult.setTotal(1);
                        jsonResult.setMessage("该商品对应SKU已存在，请重新输入！");
                        return jsonResult;
                    }
                
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        } 
        return jsonResult;
    }
    
    /**
     * @param request
     * @param dataMap
     * @param productinfo
     * @param from
     * @param productgoodsInfo 货品信息<br>
     * <i>格式：货品之间以"!@|@!"分隔，
     * 货品信息格式为：id,productStock,mallPcPrice,mallMobilePrice</i>
     * @return
     */
    @RequestMapping(value = "goodssetSumit", method = { RequestMethod.POST })
    public String goodssetSumit(HttpServletRequest request, Map<String, Object> dataMap, Product productinfo, @RequestParam(value = "goodsinfo") String productgoodsInfo, String from) {
        Product product = productService.getProductById(productinfo.getId()).getResult();
        //判断前端选择的是一口价，阶梯价或者是整箱价
        String type = request.getParameter("price");
        
        SellerUser user = WebSellerSession.getSellerUser(request);
        SystemLogs systemLogs = new SystemLogs();
        systemLogs.setOptId(user.getId());
        systemLogs.setOptName(user.getName());
        systemLogs.setOptObjectId(productinfo.getId());
        systemLogs.setOptObject("product");
        systemLogs.setOptType(SystemLogsConstants.SET_PRICE);
        systemLogs.setCreateTime(new Date());
        if ("1".equals(type)) {//一口价
            String price_only = request.getParameter("price_only");
            product.setMallPcPrice(BigDecimal.valueOf(Double.valueOf(price_only)));
            product.setMalMobilePrice(BigDecimal.valueOf(Double.valueOf(price_only)));
            product.setPriceStatus(1);
            systemLogs.setOptContent("{\"price_only\": " + price_only + "}");
            systemLogs.setOptOther("seller_user");
        }
        ProductPrice productPrice = new ProductPrice();
        if ("2".equals(type)) {//阶梯价
            String price1 = request.getParameter("price_step_1");
            String price1S = request.getParameter("stone_step_1s");
            String price1E = request.getParameter("stone_step_1e");
            String price2 = request.getParameter("price_step_2");
            String price2S = request.getParameter("stone_step_2s");
            String price2E = request.getParameter("stone_step_2e");
            String price3 = request.getParameter("price_step_3");
            String price3S = request.getParameter("stone_step_3s");
            String price3E = request.getParameter("stone_step_3e");
            String percentageScale1 = request.getParameter("percentageScale1");
            String percentageScale2 = request.getParameter("percentageScale2");
            String percentageScale3 = request.getParameter("percentageScale3");
            productPrice.setPrice1(BigDecimal.valueOf(Double.valueOf(price1)));
            productPrice.setPrice1S(Integer.valueOf(price1S));
            productPrice.setPrice1E(Integer.valueOf(price1E));
            productPrice.setPrice2(BigDecimal.valueOf(Double.valueOf(price2)));
            productPrice.setPrice2S(Integer.valueOf(price2S));
            productPrice.setPrice2E(Integer.valueOf(price2E));
            productPrice.setPrice3(BigDecimal.valueOf(Double.valueOf(price3)));
            productPrice.setPrice3S(Integer.valueOf(price3S));
            productPrice.setPrice3E(Integer.valueOf(price3E));
            productPrice.setPercentageScale1(BigDecimal.valueOf(Double.valueOf(percentageScale1)));
            productPrice.setPercentageScale2(BigDecimal.valueOf(Double.valueOf(percentageScale2)));
            productPrice.setPercentageScale3(BigDecimal.valueOf(Double.valueOf(percentageScale3)));
            if (product.getPricePid() == null || !"2".equals(product.getPriceStatus())) {
                productPriceService.saveProductPrice(productPrice);
                product.setPricePid(productPrice.getId());
            } else {
                productPriceService.updateProductPrice(productPrice);
            }
            product.setPriceStatus(2);
            systemLogs.setOptContent(JSONObject.fromObject(productPrice).toString());
            systemLogs.setOptOther("seller_user");
        }
        if ("3".equals(type)) {//整箱价
            String price_quantity = request.getParameter("quantity_price");
            String container_quantity = request.getParameter("container_quantity");
            String scattered_price = request.getParameter("scattered_price");
            product.setPriceStatus(3);
            product.setMallPcPrice(BigDecimal.valueOf(Double.valueOf(price_quantity)));
            product.setMalMobilePrice(BigDecimal.valueOf(Double.valueOf(price_quantity)));
            product.setFullContainerQuantity(Integer.valueOf(container_quantity));
            product.setScatteredPrice(BigDecimal.valueOf(Double.valueOf(scattered_price)));
            systemLogs.setOptContent("{\"price_quantity\": " + price_quantity + ",\"container_quantity\": " + container_quantity + ",\"scattered_price\": " + scattered_price + "}");
            systemLogs.setOptOther("seller_user");
        }
        product.setName2(productinfo.getName2());
        //        product.setMallPcPrice(productinfo.getMallPcPrice());
        //        product.setMalMobilePrice(productinfo.getMalMobilePrice());
        product.setProductStock(productinfo.getProductStock());
        //更新此商品价格库存信息
        productService.updateProduct(product);
        if (!isNull(productgoodsInfo)) {
            //组装货品信息
            String[] goodsinfo = productgoodsInfo.split("!@\\|@!");
            List<ProductGoods> goodslist = new ArrayList<ProductGoods>(goodsinfo.length);
            for (String data : goodsinfo) {
                String[] str = data.split(",");
                ProductGoods goods = new ProductGoods();
                goods.setId(Integer.valueOf(str[0]));
                //                goods.setSku(str[1]);
                goods.setProductStock(Integer.valueOf(str[1]));
                goods.setMallPcPrice(new BigDecimal(str[2]));
                goods.setMallMobilePrice(new BigDecimal(str[2]));

                goodslist.add(goods);
            }
            systemLogs.setOptContent(systemLogs.getOptContent() + " productgoodsInfo:" + productgoodsInfo);
            //更新此商品对应的所有货品信息
            productGoodsService.updateProductGoods(goodslist);
        } else {
            //单个货品
            List<ProductGoods> progoods = productGoodsService.getGoodSByProductId(productinfo.getId()).getResult();
            for (ProductGoods goods : progoods) {
                goods.setProductStock(productinfo.getProductStock());
                goods.setMallPcPrice(productinfo.getMallPcPrice());
                goods.setMallMobilePrice(productinfo.getMallPcPrice());
                productGoodsService.updateProductGoods(goods);
            }
            systemLogs.setOptContent(systemLogs.getOptContent() + " progoods:" + JSONObject.fromObject(progoods).toString());
        }
        systemLogsService.saveSystemLogs(systemLogs);

        //根据from重定向至相应页面
        return "redirect:/seller/product/" + from;
    }
/**
 * 打标价设置
 * @param request
 * @param dataMap
 * @param productinfo
 * @param productgoodsInfo
 * @param from
 * @return
 */
    @RequestMapping(value = "goodssetSumit2", method = { RequestMethod.POST })
    public String goodssetSumit2(HttpServletRequest request, Map<String, Object> dataMap,
                                Product productinfo,
                                @RequestParam(value = "goodsinfo") String productgoodsInfo,
                                String from) {
    	SellerUser sellerUser = WebSellerSession.getSellerUser(request);
        Product product = productService.getProductById(productinfo.getId()).getResult();
        //product picture
        String pics = request.getParameter("imageSrc");
        if (!StringUtil.isEmpty(pics)) {
        	ServiceResult<List<ProductPicture>> pictureServiceResult = productPicService.getProductPictureByProductId(product.getId());
        	boolean saveorupdateflag = false;
        	Integer productPicId = null;
        	if (pictureServiceResult.getSuccess() && pictureServiceResult.getResult() != null) {
                for (ProductPicture pic : pictureServiceResult.getResult()) {
                	if(pic.getSort()!=null && pic.getSort()==5){
                		saveorupdateflag = true;
                		productPicId = pic.getId();
                	}
                }
            }
        	if(saveorupdateflag){//更新操作
        		ProductPicture picture = new ProductPicture();
        		picture.setId(productPicId);
        		pics = pics.replace(DomainUrlUtil.getEJS_IMAGE_RESOURCES(), "");
        		picture.setProductId(product.getId());
        		picture.setImagePath(pics);
        		picture.setSort(5);
        		picture.setCreateId(sellerUser.getId());
        		picture.setState(1);
        		picture.setSellerId(sellerUser.getSeller().getId());
        		picture.setProductLead(2);
        		productPicService.updateProductPicture(picture);
        	}else{//新增操作
        		ProductPicture picture = new ProductPicture();
        		pics = pics.replace(DomainUrlUtil.getEJS_IMAGE_RESOURCES(), "");
        		picture.setProductId(product.getId());
        		picture.setImagePath(pics);
        		picture.setSort(5);
        		picture.setCreateId(sellerUser.getId());
        		picture.setState(1);
        		picture.setSellerId(sellerUser.getSeller().getId());
        		picture.setProductLead(2);
        		productPicService.saveProductPicture(picture);
        	}
        }
        //打标方式和商标一定是要获取并且更新到对应的Porduct商品表中.
        String taocanId = request.getParameter("taocanId");
        String sku_other = request.getParameter("sku_other");
        if(taocanId != null && !"".equals(taocanId)){
        	product.setTaocanId(Integer.valueOf(taocanId));
        }
        if(sku_other != null && !"".equals(sku_other)){
        	product.setSkuOther(sku_other);
        }
        //判断前端选择的是一口价，阶梯价或者是整箱价
        String type = request.getParameter("price");
        ProductPrice productPrice = new ProductPrice();
        if ("1".equals(type)) {//一口价
        	String price_only = request.getParameter("price_only");
        	productPrice.setPriceOnly(BigDecimal.valueOf(Double.valueOf(price_only)));
        	if (product.getPrice2Pid() == null || !"1".equals(String.valueOf(product.getPrice2Status()))) {
        		//新增操作
                productPriceService.saveProductPrice(productPrice);
                product.setPrice2Pid(productPrice.getId());
            } else {
            	//更新操作
            	productPrice.setId(product.getPrice2Pid());
                productPriceService.updateProductPrice(productPrice);
            }
        	product.setDabiaowaFlag(Integer.valueOf(1));
        	product.setPrice2Status(1);
        }
        if ("2".equals(type)) {//阶梯价
            String price1 = request.getParameter("price_step_1");
            String price1S = request.getParameter("stone_step_1s");
            String price1E = request.getParameter("stone_step_1e");
            String price2 = request.getParameter("price_step_2");
            String price2S = request.getParameter("stone_step_2s");
            String price2E = request.getParameter("stone_step_2e");
            String price3 = request.getParameter("price_step_3");
            String price3S = request.getParameter("stone_step_3s");
            String price3E = request.getParameter("stone_step_3e");
            productPrice.setPrice1(BigDecimal.valueOf(Double.valueOf(price1)));
            productPrice.setPrice1S(Integer.valueOf(price1S));
            productPrice.setPrice1E(Integer.valueOf(price1E));
            productPrice.setPrice2(BigDecimal.valueOf(Double.valueOf(price2)));
            productPrice.setPrice2S(Integer.valueOf(price2S));
            productPrice.setPrice2E(Integer.valueOf(price2E));
            productPrice.setPrice3(BigDecimal.valueOf(Double.valueOf(price3)));
            productPrice.setPrice3S(Integer.valueOf(price3S));
            productPrice.setPrice3E(Integer.valueOf(price3E));
            if (product.getPrice2Pid() == null || !"2".equals(String.valueOf(product.getPrice2Status()))) {
            	//新增操作
                productPriceService.saveProductPrice(productPrice);
                product.setPrice2Pid(productPrice.getId());
            } else {
            	//更新操作
            	productPrice.setId(product.getPrice2Pid());
                productPriceService.updateProductPrice(productPrice);
            }
            //设置此商品是打标袜
            product.setDabiaowaFlag(Integer.valueOf(1));
            product.setPrice2Status(2);
        }
        //更新此商品价格库存信息
        productService.updateProduct(product);
        //根据from重定向至相应页面
        return "redirect:/seller/product/" + from;
    }
    
    /**
     * SKU图片上传
     * @param request
     * @param response
     * @param attrid 属性id
     * @param colortype 颜色类型 custom-自定义
     * @param uploadtype 上传类型 1-创建 2-更新
     * @param productId 商品id（仅更新时有效）
     * @return
     */
    @RequestMapping(value = "uploadSKUImage", method = { RequestMethod.POST })
    public @ResponseBody Object uploadSKUImage(MultipartHttpServletRequest request,
                                               HttpServletResponse response, Integer attrid,
                                               String colortype, Integer uploadtype,
                                               Integer productId) {
        HttpJsonResult<Map<String, Object>> jsonResult = new HttpJsonResult<Map<String, Object>>();
        Map<String, Object> result = new HashMap<String, Object>();
        MultiValueMap<String, MultipartFile> map = request.getMultiFileMap();
        try {
            if (map != null) {
                Iterator<String> iter = map.keySet().iterator();
                while (iter.hasNext()) {
                    String str = (String) iter.next();
                    List<MultipartFile> fileList = map.get(str);
                    for (MultipartFile mpf : fileList) {
                        String originalFilename = mpf.getOriginalFilename();
                        File tmpFile = new File(buildImgPath(request)
                                                + "/"
                                                + UUID.randomUUID()
                                                + originalFilename.substring(
                                                    originalFilename.lastIndexOf("."),
                                                    originalFilename.length()));
                        String url = null;
                        try {
                            if (!mpf.isEmpty()) {
                                byte[] bytes = mpf.getBytes();
                                BufferedOutputStream stream = new BufferedOutputStream(
                                    new FileOutputStream(tmpFile));
                                stream.write(bytes);
                                stream.close();
                            }

                            url = UploadUtil.getInstance().productUploaderImage(tmpFile, request);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        tmpFile.deleteOnExit();
                        //删除目录
                        UploadUtil.deleteDir(tmpFile.getParentFile().getParentFile());

                        //                        Integer sellerId = WebSellerSession.getSellerUser(request).getSellerId();

                        //保存属性url关系
                        //如果已上传过，则略过
                        //                        Map<String, String> queryMap = new HashMap<String, String>();
                        //                        queryMap.put("sellerId", WebSellerSession.getSellerUser(request)
                        //                            .getSellerId() + "");
                        //                        queryMap.put("attrId", attrid + "");
                        //                        if (!isNull(uploadtype) && uploadtype.intValue() == 1) {
                        //                            queryMap.put("flag", "1");
                        //                        } else {
                        //                            queryMap.put("flag", "2");
                        //                            queryMap.put("productId", productId + "");
                        //                        }
                        //                        ServiceResult<List<ProductNormAttrOpt>> optservice = productNormAttrOptService
                        //                            .page(queryMap, null);
                        //                        if (optservice.getResult().size() > 0) {
                        //                            ProductNormAttrOpt pat = new ProductNormAttrOpt();
                        //                            pat.setSellerId(sellerId);
                        //                            pat.setCreateId(sellerId);
                        //                            pat.setTypeAttr(!isNull(colortype) && "custom".equals(colortype) ? 2
                        //                                : 1);
                        //                            pat.setFlag(1);//未创建
                        //                            pat.setCreateTime(new Date());
                        //                            pat.setType(2);//图片
                        //                            pat.setAttrId(attrid);//属性值
                        //                            pat.setImage(url);//图片地址
                        //                            productNormAttrOptService.saveProductNormAttrOpt(pat);
                        //                        } else {
                        //如果存在，则更新
                        //                            for (ProductNormAttrOpt opt : optservice.getResult()) {
                        //                                if (opt.getAttrId().intValue() == attrid.intValue()
                        //                                    && opt.getSellerId().intValue() == sellerId.intValue()) {
                        //                                    opt.setImage(url);
                        //                                    productNormAttrOptService.updateProductNormAttrOpt(opt);
                        //                                }
                        //                            }
                        //                        }

                        //返回值
                        result.put("attrid", attrid);
                        result.put("colortype", colortype);
                        jsonResult.setData(result);

                        //上传失败
                        if (isNull(url))
                            throw new BusinessException("上传失败");
                        //规范路径,以避免浏览器兼容问题
                        url = url.replaceAll("\\\\", "/");
                        result.put("url", url);

                    }
                }
            }
        } catch (Exception e) {
            jsonResult.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return jsonResult;
    }

    /**
     * 保存sku
     * @param request
     * @param response
     * @param attrid
     * @param colortype
     * @param uploadtype
     * @param productId
     * @return
     */
    @RequestMapping(value = "saveSKU", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<Boolean> saveSKU(HttpServletRequest request,
                                                         HttpServletResponse response,
                                                         Integer attrid, String colortype,
                                                         Integer uploadtype, Integer productId) {
        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
        try {
            Integer sellerId = WebSellerSession.getSellerUser(request).getSellerId();

            //保存属性url关系
            //如果已上传过，则略过
            Map<String, String> queryMap = new HashMap<String, String>();
            queryMap.put("sellerId", WebSellerSession.getSellerUser(request).getSellerId() + "");
            queryMap.put("attrId", attrid + "");
            if (!isNull(uploadtype) && uploadtype.intValue() == 1) {
                queryMap.put("flag", "1");
            } else {
                queryMap.put("flag", "2");
                queryMap.put("productId", productId + "");
            }
            ServiceResult<List<ProductNormAttrOpt>> optservice = productNormAttrOptService.page(
                queryMap, null);
            if (optservice.getResult().size() < 1) {
                ProductNormAttrOpt pat = new ProductNormAttrOpt();
                pat.setSellerId(sellerId);
                pat.setCreateId(sellerId);
                pat.setTypeAttr(!isNull(colortype) && "custom".equals(colortype) ? 2 : 1);
                pat.setFlag(1);//未创建
                pat.setCreateTime(new Date());
                pat.setType(2);//图片
                pat.setAttrId(attrid);//属性值
                //            pat.setImage(url);//图片地址
                productNormAttrOptService.saveProductNormAttrOpt(pat);
            }
        } catch (Exception e) {
            jsonResult.setMessage(e.getMessage());
            e.printStackTrace();
        }

        log.debug("保存成功");
        return jsonResult;
    }

    /**
     * 删除sku
     * @param request
     * @param response
     * @param attrid
     * @param uploadtype
     * @param productId
     */
    @RequestMapping(value = "deleteSku", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<Boolean> deleteSku(HttpServletRequest request,
                                                           HttpServletResponse response,
                                                           Integer attrid, Integer uploadtype,
                                                           Integer productId) {
        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
        try {
            Map<String, String> queryMap = new HashMap<String, String>();
            queryMap.put("sellerId", WebSellerSession.getSellerUser(request).getSellerId() + "");
            queryMap.put("attrId", attrid + "");
            if (!isNull(uploadtype) && uploadtype.intValue() == 1) {
                queryMap.put("flag", "1");
            } else {
                queryMap.put("flag", "2");
                queryMap.put("productId", productId + "");
            }
            ServiceResult<List<ProductNormAttrOpt>> optservice = productNormAttrOptService.page(
                queryMap, null);
            if (optservice.getResult().size() > 0) {
                for (ProductNormAttrOpt opt : optservice.getResult()) {
                    productNormAttrOptService.del(opt.getId());
                }
            }
        } catch (Exception e) {
            jsonResult.setMessage(e.getMessage());
            e.printStackTrace();
        }
        log.debug("删除成功");
        return jsonResult;
    }

    //待售商品(刚创建、提交审核、审核通过、下架)
    @RequestMapping(value = "waitSale", method = { RequestMethod.GET })
    public String waitSale(HttpServletRequest request, Map<String, Object> dataMap) {
        dataMap.put("q_useYn", "1");
        dataMap.put("q_state", "1,2,3,4,7");//1、刚创建；2、提交审核；3、审核通过；4、申请驳回；7、下架
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        ServiceResult<ProductCate> cateResult = productCateService.getProductCateById(Integer
            .valueOf(1));
        ServiceResult<List<ProductBrand>> brandResult = productBrandService
            .getBrandByTypeId(cateResult.getResult().getProductTypeId());
        if (brandResult.getSuccess() && brandResult.getResult() != null) {
            dataMap.put("brand", brandResult.getResult());
        }
        return baseUrl + "listwaitsale";
    }

    @RequestMapping(value = "saleAll", method = { RequestMethod.GET })
    public String saleAll(HttpServletRequest request, Map<String, Object> dataMap) {
        dataMap.put("q_useYn", "1");
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        dataMap.put("q_state", "1,2,3,4,5,6,7");//1、刚创建；2、提交审核；3、审核通过；4、申请驳回；7、下架
        ServiceResult<ProductCate> cateResult = productCateService.getProductCateById(Integer
            .valueOf(1));
        ServiceResult<List<ProductBrand>> brandResult = productBrandService
            .getBrandByTypeId(cateResult.getResult().getProductTypeId());
        if (brandResult.getSuccess() && brandResult.getResult() != null) {
            dataMap.put("brand", brandResult.getResult());
        }
        return baseUrl + "listall";
    }

    //在售商品(上架商品) 可以下架
    @RequestMapping(value = "onSale", method = { RequestMethod.GET })
    public String onSale(HttpServletRequest request, Map<String, Object> dataMap) {
        dataMap.put("q_useYn", "1");
        dataMap.put("q_state", "6");//6、上架；
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        ServiceResult<ProductCate> cateResult = productCateService.getProductCateById(Integer
            .valueOf(1));
        ServiceResult<List<ProductBrand>> brandResult = productBrandService
            .getBrandByTypeId(cateResult.getResult().getProductTypeId());
        if (brandResult.getSuccess() && brandResult.getResult() != null) {
            dataMap.put("brand", brandResult.getResult());
        }
        return baseUrl + "listonsale";
    }

    //已经删除商品(删除) 只读
    @RequestMapping(value = "delSale", method = { RequestMethod.GET })
    public String delSale(HttpServletRequest request, Map<String, Object> dataMap) {
        dataMap.put("q_useYn", "1");
        dataMap.put("q_state", "5");//5、商品删除；
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return baseUrl + "listdelsale";
    }

    //恢复已删除的商品
    @RequestMapping(value = "revertProduct", method = { RequestMethod.GET })
    public String revertProduct(HttpServletRequest request, Map<String, Object> dataMap) {
        String id = request.getParameter("id");
        System.out.println(id);
        ServiceResult<Boolean> serviceResult = productService.updateProductState(
            Integer.valueOf(id), 7);
        dataMap.put("q_useYn", "1");
        dataMap.put("q_state", "5");//5、商品删除；
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return baseUrl + "listdelsale";
    }

    /**
     * 商品列表
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<Product>> list(HttpServletRequest request,
                                                            Map<String, Object> dataMap) {
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        String state = request.getParameter("q_state1");
        if (!StringUtil.isEmpty(state)) {
            queryMap.put("q_state", state);
        }
        String product_code = request.getParameter("q_product_code");
        if (!StringUtil.isEmpty(product_code)) {
            queryMap.put("q_productCode", product_code);
        }
        String name2 = request.getParameter("q_name2");
        if (!StringUtil.isEmpty(name2)) {
            if (name2.equals("1")) {
                queryMap.put("q_name2", "促销中");
            } else {
                queryMap.put("q_name2", "未促销");
            }

        }
        String brand_id = request.getParameter("q_brandId");
        if (!StringUtil.isEmpty(brand_id)) {
            queryMap.put("q_brandId", brand_id);
        }
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        queryMap.put("q_sellerId", WebSellerSession.getSellerUser(request).getSellerId() + "");
        ServiceResult<List<Product>> serviceResult = productService.pageProduct(queryMap, pager);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }

        HttpJsonResult<List<Product>> jsonResult = new HttpJsonResult<List<Product>>();
        jsonResult.setRows((List<Product>) serviceResult.getResult());
        jsonResult.setTotal(pager.getRowsCount());
        return jsonResult;
    }

    /**
     * 根据商品ID查询货品
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "list_goods", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<ProductGoods>> listGoods(Integer productId,
                                                                      HttpServletRequest request,
                                                                      Map<String, Object> dataMap) {
        ServiceResult<List<ProductGoods>> serviceResult = productGoodsService
            .getGoodSByProductId(productId);

        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }

        HttpJsonResult<List<ProductGoods>> jsonResult = new HttpJsonResult<List<ProductGoods>>();
        jsonResult.setRows(serviceResult.getResult());
        return jsonResult;
    }

    /**
     * 提交审核
     * @param request
     * @return
     */
    @RequestMapping(value = "commit", method = { RequestMethod.POST })
    public void commit(HttpServletRequest request, HttpServletResponse response, String ids) {
        response.setContentType("text/plain;charset=utf-8");
        String msg = "提交成功,请耐心等待,审核通过后商品才能上架";
        PrintWriter pw = null;
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("state", Product.STATE_2);
        try {
            productService.updateByIds(map, StringUtil.string2IntegerList(ids));
            handleProductOpt(request, ids, SystemLogsConstants.AUDIT);
            pw = response.getWriter();
        } catch (Exception e) {
            e.printStackTrace();
            msg = "提交失败,请稍后重试";
        }
        pw.print(msg);
    }

    /**
     * 发布商品-选择分类
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "chooseCate", method = { RequestMethod.GET })
    public String chooseCate(HttpServletRequest request, Map<String, Object> dataMap) {
        SellerUser sellerUser = WebSellerSession.getSellerUser(request);
        if (null == sellerUser) {
            return DomainUrlUtil.getEJS_URL_RESOURCES() + "/seller/login.html";
        }
        //此处改动所有商家调用同一显示结果sellerUser.getSellerId()

        ServiceResult<List<ProductCate>> serviceResult = productCateService.getCateBySellerId(0);
        if (serviceResult.getSuccess() == true && serviceResult.getResult() != null) {
            dataMap.put("cate", serviceResult.getResult());
        }
        
        return baseUrl + "choosecate";
    }

    /**
     * 根据商品分类id组装商品类型属性、商品规格属性
     *
     * @param productCateId
     * @param dataMap
     */
    private void assembleProp(Integer productCateId, Map<String, Object> dataMap, Integer productId) {
        ServiceResult<ProductCate> cate = productCateService.getProductCateById(productCateId);
        if (cate.getSuccess() == true && cate.getResult() != null) {

            Integer typeId = cate.getResult().getProductTypeId();
            //初始化商品属性信息
            typeId = 1;
            ServiceResult<List<ProductTypeAttr>> typeAttr = sellerProductTypeAttrService
                .getProductTypeAttrByTypeId(typeId);

            Map<String, String> sellerMap = new HashMap<String, String>();
            sellerMap.put("q_auditStatus", "2");
            ServiceResult<List<Seller>> sellers = sellerService.getSellers(sellerMap, null);
            dataMap.put("sellers", sellers.getResult());

            /**组装商品类型属性*/
            if (typeAttr.getSuccess() == true && typeAttr.getResult() != null
                && typeAttr.getResult().size() > 0) {
                List<ProductTypeAttr> attrList = typeAttr.getResult();
                List<Map<String, Object>> queryTypeAttr = new ArrayList<Map<String, Object>>();
                List<Map<String, Object>> custTypeAttr = new ArrayList<Map<String, Object>>();

                List<ProductAttr> productAttrList = null;
                if (null != productId) {
                    //编辑商品时候使用
                    ServiceResult<List<ProductAttr>> attrServiceResult = productAttrService
                        .getProductAttrByProductId(productId);
                    if (attrServiceResult.getSuccess() && attrServiceResult.getResult() != null) {
                        productAttrList = attrServiceResult.getResult();
                    }
                }
                for (ProductTypeAttr attr1 : attrList) {
                    Map<String, Object> attrMap = new HashMap<String, Object>();
                    if (attr1.getType() == 1) {
                        processAttr(attr1, attrMap, productAttrList);
                        queryTypeAttr.add(attrMap);//查询属性
                    } else {
                        processAttr(attr1, attrMap, productAttrList);
                        custTypeAttr.add(attrMap);//自定义属性
                    }
                }
                dataMap.put("queryTypeAttr", queryTypeAttr);
                dataMap.put("custTypeAttr", custTypeAttr);
            }

            /**组装商品属性信息**/
            ServiceResult<ProductType> serviceResult = sellerProductTypeService
                .getProductTypeById(typeId);
            if (serviceResult.getSuccess() && serviceResult.getResult() != null) {
                ProductType productType = serviceResult.getResult();
                String productNormIds = productType.getProductNormIds();
                if (!StringUtil.isEmpty(productNormIds)) {
                    String[] normIds = productNormIds.split(",");
                    List<Map<String, Object>> normList = new ArrayList<Map<String, Object>>(
                        normIds.length);
                    for (String normId : normIds) {
                        Map<String, Object> attrMap = null;
                        Integer id = Integer.valueOf(normId);
                        ServiceResult<ProductNorm> normResult = sellerProductNormService
                            .getNormById(id);

                        if (normResult.getSuccess() && normResult.getResult() != null) {
                            attrMap = new HashMap<String, Object>(2);
                            ProductNorm result = normResult.getResult();
                            List<ProductNormAttr> list = result.getAttrList();
                            List<Map<String, Object>> attrList = new ArrayList<Map<String, Object>>(
                                result.getAttrList().size());

                            /**构造货品属性**/
                            List<String> normAttrIdList = new ArrayList<String>();
                            if (null != productId) {
                                Map<String, String> queryMap = new HashMap<String, String>(1);
                                queryMap.put("q_productId", String.valueOf(productId));
                                ServiceResult<List<ProductGoods>> listServiceResult = productGoodsService.pageProductGoods(queryMap, null);
                                if (listServiceResult.getSuccess()
                                    && listServiceResult.getResult() != null
                                    && listServiceResult.getResult().size() > 0) {
                                    List<ProductGoods> goodsList = listServiceResult.getResult();

                                    processGoods(goodsList, dataMap);//货品信息

                                    for (ProductGoods goods : goodsList) {
                                        String normAttrId = goods.getNormAttrId();
                                        if (!StringUtil.isEmpty(normAttrId)) {
                                            String[] split = normAttrId.split(",");
                                            for (String str : split) {
                                                if (!StringUtil.isEmpty(str)) {
                                                    normAttrIdList.add(str);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            if (normAttrIdList.size() > 0) {
                                Set<String> set = new HashSet<String>();
                                set.addAll(normAttrIdList);
                                normAttrIdList.clear();
                                normAttrIdList.addAll(set);
                            }

                            attrMap.put("id", result.getId());
                            attrMap.put("name", result.getName());
                            attrMap.put("attrList", attrList);
                            if (null != list && list.size() > 0) {
                                Map<String, Object> map = null;
                                for (ProductNormAttr attr : list) {
                                    map = new HashMap<String, Object>(3);
                                    map.put("id", attr.getId());
                                    map.put("name", attr.getName());
                                    if (normAttrIdList.size() > 0) {
                                        for (String str : normAttrIdList) {
                                            if (String.valueOf(attr.getId()).equals(str)) {
                                                map.put("checked", "true");
                                            }
                                        }
                                    }
                                    attrList.add(map);
                                }
                            }
                            normList.add(attrMap);
                        }
                    }
                    dataMap.put("normList", normList);
                }
            }
        }
    }

    /**
     * 构造货品信息
     *
     * @param goodsList
     * @param dataMap
     */
    private void processGoods(List<ProductGoods> goodsList, Map<String, Object> dataMap) {
        if (null != goodsList && goodsList.size() > 0) {
            List<Map<String, Object>> goods = new ArrayList<Map<String, Object>>(goodsList.size());
            Map<String, Object> goodMap = null;
            List<String> columnList = new ArrayList<String>();

            for (ProductGoods good : goodsList) {
                goodMap = new HashMap<String, Object>();
                String normAttrId = good.getNormAttrId();
                if (!StringUtil.isEmpty(normAttrId)) {
                    String[] split = normAttrId.split(",");

                    if (null != split && split.length == 1) {//一列规格
                        goodMap.put("normId1", normAttrId);
                        String normName = good.getNormName();
                        String[] arr = normName.split(",");
                        goodMap.put("normName1", arr.length > 1 ? arr[1] : arr[0]);
                        columnList.add(normName.split(",")[0]);
                    } else if (null != split && split.length == 2) {//两列规格
                        goodMap.put("normId1", split[0]);
                        goodMap.put("normId2", split[1]);
                        String normName = good.getNormName();
                        String column1 = normName.split(";")[0];
                        column1 = column1.substring(0, column1.indexOf(","));
                        String column2 = normName.split(";")[1];
                        column2 = column2.substring(0, column2.indexOf(","));
                        String normName1 = normName.split(";")[0];
                        normName1 = normName1.substring(normName1.indexOf(",") + 1,
                            normName1.length());
                        String normName2 = normName.split(";")[1];
                        normName2 = normName2.substring(normName2.indexOf(",") + 1,
                            normName2.length());

                        goodMap.put("normName1", normName1);
                        goodMap.put("normName2", normName2);
                        columnList.add(column1);
                        columnList.add(column2);
                    }

                    goodMap.put("sku", good.getSku());
                    goodMap.put("stock", good.getProductStock());
                    goodMap.put("mobilePrice", good.getMallMobilePrice());
                    goodMap.put("pcPrice", good.getMallPcPrice());
                    goodMap.put("barCode", good.getBarCode());
                    goodMap.put("barCodeCS",good.getBarCodeCS());
                    goodMap.put("barCodePL",good.getBarCodePL());
                    goodMap.put("isVirtualBom",good.getIsVirtualBom());

                    goods.add(goodMap);

                    Set<String> set = new HashSet<String>();
                    set.addAll(columnList);
                    columnList.clear();
                    columnList.addAll(set);
                    dataMap.put("goods", goods);
                    dataMap.put("column", columnList);
                }
            }
        }
    }

    private void processAttr(ProductTypeAttr attr1, Map<String, Object> attrMap,
                             List<ProductAttr> productAttrList) {
        List<String> attrValList = new ArrayList<String>();
        String attrVal = attr1.getValue();
        if (!StringUtil.isEmpty(attrVal)) {
            String[] split = attrVal.split(",");
            for (String str : split) {
                if (StringUtil.isEmpty(str))
                    continue;
                attrValList.add(str);
            }
        }

        attrMap.put("attrId", attr1.getId());
        attrMap.put("attrName", attr1.getName());
        attrMap.put("attrValList", attrValList);

        //编辑商品时候使用
        if (null != productAttrList && productAttrList.size() > 0) {
            for (ProductAttr attr : productAttrList) {
                if (attr.getProductTypeAttrId().equals(attr1.getId())) {
                    attrMap.put("dbAttr", attr.getValue());
                }
            }
        }
    }

    /**
     * 添加商品详细信息
     *
     * @param request
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "add", method = { RequestMethod.GET })
    public String add(HttpServletRequest request, Map<String, Object> dataMap) throws Exception {
        String rtnPath = baseUrl + "productadd";
        /**1.判断是否登录*/
        SellerUser user = WebSellerSession.getSellerUser(request);
        if (null == user) {
            return DomainUrlUtil.getEJS_URL_RESOURCES() + "/seller/login.html";
        }
        /**2.判断是否选择商品分类*/
        String cateId = request.getParameter("cateId") == null ? "" : "1";
        dataMap.put("cateId", cateId);
        //        if (StringUtil.isEmpty(cateId)) {
        //            dataMap
        //                .put("cate", DomainUrlUtil.getEJS_URL_RESOURCES() + "/seller/product/chooseCate");
        //            return baseUrl + "choosecate";
        //        }
        //        /**3.构造商品分类路径*/
        //        ServiceResult<String> catePathResult = productCateService.getCatePathStrById(Integer
        //            .valueOf(cateId));
        //        if (catePathResult.getSuccess() && catePathResult.getResult() != null) {
        //            dataMap.put("catePath", catePathResult.getResult());
        //            dataMap.put("cateId", cateId);
        //        }

        //商品二级分类
        ServiceResult<List<ProductCate>> serviceResult = productCateService.getByPid(1);
        if (serviceResult.getSuccess() == true && serviceResult.getResult() != null) {
            dataMap.put("cate", serviceResult.getResult());
        }

        /**4.获取商家可以经营的品牌**/
        ServiceResult<ProductCate> cateResult = productCateService.getProductCateById(Integer
            .valueOf(cateId));
        if (!cateResult.getSuccess() || cateResult.getResult() == null) {
            dataMap.put("message", "该分类下无可以经营的品牌，请重新选择分类，或者联系商城管理员");
            return rtnPath;
        }
        ServiceResult<List<ProductBrand>> brandResult = productBrandService
            .getBrandByTypeId(cateResult.getResult().getProductTypeId());
        if (brandResult.getSuccess() && brandResult.getResult() != null) {
            dataMap.put("brand", brandResult.getResult());
        }
        /**5.组装商品类型信息（单品页商品介绍下的内容，商品属性）、组装商品规格信息（单品页商品图片右侧选择信息）*/
        assembleProp(Integer.valueOf(cateId), dataMap, null);

        /**6.本店分类(1级)**/
        //        ServiceResult<List<SellerCate>> serviceResult = sellerCateService.getByPid(0,
        //            user.getSellerId());
        //        if (serviceResult.getSuccess() && null != serviceResult.getResult()) {
        //            dataMap.put("sellerCate", serviceResult.getResult());
        //        }

        /**7.判断自营还是商家 1自营**/
        if (user.getSellerId() == 1) {
            dataMap.put("seller", 1);
        }

        //        /**8.是否存在上一次未保存的sku信息*/
        //        Map<String, String> queryMap = new HashMap<String, String>();
        //        queryMap.put("sellerId", WebSellerSession.getSellerUser(request).getSellerId() + "");
        //        queryMap.put("flag", "1");
        //        ServiceResult<List<ProductNormAttrOpt>> optservice = productNormAttrOptService.page(
        //            queryMap, null);
        //        if (optservice.getResult().size() > 0) {
        //            //删除临时信息
        //            for (ProductNormAttrOpt opt : optservice.getResult()) {
        //                productNormAttrOptService.del(opt.getId());
        //            }
        //        }
        dataMap.put("transport", sellerTransportService.getTransportBySellerId(user.getSellerId()));
        
      //查询wms分类 使用中的
//        Integer state = 1;
//        ServiceResult<List<WmsClassify>> serviceResult2 = wmsClassifyService.getWmsCategoryList(state);
//        if (serviceResult2.getSuccess() == true && serviceResult2.getResult() != null) {
//            dataMap.put("wmsClassify", serviceResult2.getResult());
//        }

        return rtnPath;
    }

    /**
     * 保存商品
     *
     * @param product
     * @param request
     * @param dataMap
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "create", method = { RequestMethod.POST })
    @ResponseBody
    public HttpJsonResult<Object> create(Product product, HttpServletRequest request, Map<String, Object> dataMap) throws IOException {
        HttpJsonResult<Object> jsonResult = new HttpJsonResultForAjax<Object>(true, CsrfTokenManager.createTokenForSession(request.getSession()));
        SellerUser user = WebSellerSession.getSellerUser(request);
        String wmsCategory = request.getParameter("wmsCategory");
        String unit = request.getParameter("unit");
        product.setWmsCategory(wmsCategory);
        product.setUnit(unit);
        if (null == user) {
            jsonResult.setMessage("登录超时，请重新登录");
            jsonResult.setBackUrl(DomainUrlUtil.getEJS_URL_RESOURCES() + "/seller/login.html");
            return jsonResult;
        }

        ServiceResult<Boolean> serviceResult = createOrUpdateProduct(product, request, user.getSeller(), "C", user);
        systemLogsService.saveSystemLogs(user.getId(), user.getName(), JSONObject.fromObject(product).toString(), product.getId(), "product", SystemLogsConstants.INSERT, "seller_user");
        if (serviceResult.getSuccess() && serviceResult.getResult() == true) {
        } else {
            jsonResult.setMessage(serviceResult.getMessage());
        }
        return jsonResult;
    }

    /**
     * 编辑商品
     */
    @RequestMapping(value = "edit", method = { RequestMethod.GET })
    public String edit(HttpServletRequest request, @RequestParam(value = "id", required = true) Integer id, Map<String, Object> dataMap) {
        String rtnPath = baseUrl + "productedit";
        /**1.判断是否登录*/
        SellerUser user = WebSellerSession.getSellerUser(request);
        //if (null == user) {
        //    return DomainUrlUtil.getJM_URL_RESOURCES() + "/seller/login.html";
        //}
        /**查询商品信息**/
        ServiceResult<Product> productServiceResult = productService.getProductById(id);
        if (!productServiceResult.getSuccess() || null == productServiceResult.getResult()) {
            dataMap.put("message", "编辑商品失败，商品不存在");
            return rtnPath;//
        }
        Product product = productServiceResult.getResult();
        dataMap.put("product", product);

        //商品二级分类
        ServiceResult<List<ProductCate>> serviceResult = productCateService.getByPid(1);
        if (serviceResult.getSuccess() == true && serviceResult.getResult() != null) {
            dataMap.put("cate", serviceResult.getResult());
        }
        /**分类名称**/
        //        ServiceResult<ProductCate> catePathResult = productCateService.getProductCateById(product.getProductCateId());
        //        if (catePathResult.getSuccess() && catePathResult.getResult() != null) {
        //            String pathResult = catePathResult.getResult().getPath();
        //            String pr[] = pathResult.split("/");
        //            dataMap.put("productCateTwo", Integer.parseInt(pr[2].trim()));
        //            
        //            serviceResult = productCateService.getByPid(Integer.parseInt(pr[2].trim()));
        //            if (serviceResult.getSuccess() == true && serviceResult.getResult() != null) {
        //                dataMap.put("cateThree", serviceResult.getResult());
        //            }
        //            dataMap.put("productCateThree", product.getProductCateId());
        //        }
        /**品牌名称**/
        ServiceResult<ProductBrand> brandResult = productBrandService.getById(product
            .getProductBrandId());
        if (brandResult.getSuccess() && brandResult.getResult() != null) {
            dataMap.put("brand", brandResult.getResult().getNameFirst() + " "
                                 + brandResult.getResult().getName());
        }
        /**商品类型*/
        assembleProp(Integer.valueOf(product.getProductCateId()), dataMap, product.getId());

        /**商品图片**/
        ServiceResult<List<ProductPicture>> pictureServiceResult = productPicService
            .getProductPictureByProductId(product.getId());
        if (pictureServiceResult.getSuccess() && pictureServiceResult.getResult() != null) {
            for (ProductPicture pic : pictureServiceResult.getResult()) {
                String path = pic.getImagePath();
                path = DomainUrlUtil.getEJS_IMAGE_RESOURCES() + path;
                pic.setImagePath(path);
            }
            dataMap.put("pic", pictureServiceResult.getResult());
        }

        //        Integer sellerCateId = product.getSellerCateId();
        //        boolean isFirst = false;
        /**本店分类**/
        //        ServiceResult<List<SellerCate>> serviceResult = sellerCateService.getByPid(0,user.getSellerId());
        //        if (serviceResult.getSuccess() && null != serviceResult.getResult()) {
        //            dataMap.put("sellerCate", serviceResult.getResult());//一级分类
        //            //过滤一级目录
        //            for (SellerCate sellerCate : serviceResult.getResult()) {
        //                if (sellerCate.getId() == sellerCateId) {
        //                    dataMap.put("cateId", sellerCate.getId());//一级分类id
        //                    isFirst = true;
        //                }
        //            }
        //        }
        //
        //        if (!isFirst) {
        //            ServiceResult<SellerCate> sellerCateResult = sellerCateService
        //                .getSellerCateById(sellerCateId);
        //            if (sellerCateResult.getSuccess() && sellerCateResult.getResult() != null) {
        //                SellerCate sellerCate = sellerCateResult.getResult();//当前分类信息
        //                ServiceResult<List<SellerCate>> secondCateResult = sellerCateService.getByPid(
        //                    sellerCate.getPid(), user.getSellerId());//二级分类
        //                if (secondCateResult.getSuccess() && secondCateResult.getResult() != null) {
        //                    dataMap.put("secondSellerCate", secondCateResult.getResult());
        //                    dataMap.put("cateId", sellerCate.getPid());//一级分类id
        //                    dataMap.put("secondCateId", sellerCateId);//二级分类id
        //                }
        //            }
        //        }

        /**判断自营还是商家 1自营**/
        if (user.getSellerId() == 1) {
            dataMap.put("seller", 1);
        }

        //选中的颜色规格 
        Map<String, String> queryMap = new HashMap<String, String>();
        queryMap.put("sellerId", WebSellerSession.getSellerUser(request).getSellerId() + "");
        queryMap.put("productId", id + "");
        queryMap.put("flag", "2");
        ServiceResult<List<ProductNormAttrOpt>> optservice = productNormAttrOptService.page(queryMap, null);
        dataMap.put("customAttr", optservice.getResult());

        dataMap.put("edit", "edit");

        return baseUrl + "productedit";
        //        return baseUrl + "productadd";
    }

    /**
     * 更新商品
     *
     * @param product
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "update", method = { RequestMethod.POST })
    @ResponseBody
    public HttpJsonResult<Object> update(Product product, HttpServletRequest request) throws IOException {
        HttpJsonResult<Object> jsonResult = new HttpJsonResultForAjax<Object>(true, CsrfTokenManager.createTokenForSession(request.getSession()));
        SellerUser user = WebSellerSession.getSellerUser(request);
        if (null == user) {
            jsonResult.setMessage("登录超时，请重新登录");
            jsonResult.setBackUrl(DomainUrlUtil.getEJS_URL_RESOURCES() + "/seller/login.html");
            return jsonResult;
        }

        ServiceResult<Boolean> serviceResult = createOrUpdateProduct(product, request, user.getSeller(), "U", user);
        systemLogsService.saveSystemLogs(user.getId(), user.getName(), JSONObject.fromObject(product).toString(), product.getId(), "product", SystemLogsConstants.UPDATE, "seller_user");
        if (!serviceResult.getSuccess()) {
            jsonResult.setMessage(serviceResult.getMessage());
        }
        return jsonResult;
    }

    /**
     * 删除商品
     *
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "del", method = RequestMethod.POST)
    public @ResponseBody HttpJsonResult<Object> del(HttpServletRequest request,
                                                    @RequestParam("id") Integer id) {
        HttpJsonResult<Object> jsonResult = new HttpJsonResult<Object>();
        ServiceResult<Boolean> serviceResult = productService.delProduct(id);
        SellerUser user = WebSellerSession.getSellerUser(request);
        systemLogsService.saveSystemLogs(user.getId(), user.getName(), "", id, "product", SystemLogsConstants.DELETE, "seller_user");
        if (!serviceResult.getSuccess()) {
            jsonResult.setMessage(serviceResult.getMessage());
        }
        return jsonResult;
    }

    /**
     * 上下架操作
     *
     * @param request
     * @param response
     * @param ids
     */
    @RequestMapping(value = "handler", method = { RequestMethod.POST })
    public void handler(HttpServletRequest request, HttpServletResponse response, String ids, Integer type) {
        response.setContentType("text/plain;charset=utf-8");
        String msg = "";
        PrintWriter pw = null;
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("state", Product.STATE_2);
            if (type == Product.STATE_6) {
                map.put("state", Product.STATE_6);
//                map.put("upTime", "update");            //当商品上架时，实时更新上架时间;
                handleProductOpt(request, ids, SystemLogsConstants.UP);
                msg = "上架成功";
            } else if (type == Product.STATE_7) {
                map.put("state", Product.STATE_7);
                handleProductOpt(request, ids, SystemLogsConstants.DOWN);
                msg = "下架成功";
            } else
                throw new BusinessException("未知操作");
            productService.updateByIds(map, StringUtil.string2IntegerList(ids));
            pw = response.getWriter();
        } catch (Exception e) {
            e.printStackTrace();
            msg = e.getMessage();
        }
        pw.print(msg);
    }
    
    
    @RequestMapping(value = "syncoms", method = { RequestMethod.POST })
    public void syncoms(HttpServletRequest request, HttpServletResponse response, String ids) {
        response.setContentType("text/plain;charset=utf-8");
        String msg = "操作成功!";
        PrintWriter pw = null;
        try {
            List<Integer > idlist =  StringUtil.string2IntegerList(ids);
            ServiceResult<List<Product>> productListServiceResult = productService.getProductsByIds(idlist);
            if (productListServiceResult.getSuccess()) {
            	List<Product> productList = productListServiceResult.getResult();
            	for (Product product : productList) {
            		  ServiceResult<List<ProductGoods>> productGoodsListServiceResult = productGoodsService.getGoodSByProductId(product.getId());
            		  if (productGoodsListServiceResult.getSuccess()) {
            			  List<ProductGoods> productGoodsList = productGoodsListServiceResult.getResult();
            			  for (ProductGoods productGoods : productGoodsList) {
            				  ServiceResult<String> strResult = productService.omsProductCreate(product, productGoods);
            				  if (strResult.getSuccess()) {
            					  if ("Success".equals(strResult.getResult())) {
            					  } else {
            						  msg += "(" + productGoods.getSku() + ")oms" + strResult.getResult() + ";";
            					  }
            				  } else {
            					  msg = strResult.getMessage();
            				  }
						  }
            		  } else {
            			  msg = "根据商品id查询商品详情失败";
            		  }
    			}
            } else {
            	msg = "根据商品id查询商品失败";
            } 
            pw = response.getWriter();
        } catch (Exception e) {
            e.printStackTrace();
            msg = e.getMessage();
        }
        pw.print(msg);
    }
    
    /**
     * 
     * @param ids
     * @param optType
     */
    private void handleProductOpt(HttpServletRequest request, String ids, String optType) {
        SellerUser user = WebSellerSession.getSellerUser(request);
        String is[] = ids.split(",");
        for (String i: is) {
            systemLogsService.saveSystemLogs(user.getId(), user.getName(), "", Integer.parseInt(i.trim()), "product", optType, "seller_user");
        }
    }

    /**
     * ajax获取二级、三级分类
     *
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "getCateById", method = { RequestMethod.GET })
    @ResponseBody
    public Object getCateById(HttpServletRequest request, @RequestParam("id") Integer id) {
        HttpJsonResult<List<ProductCate>> jsonResult = new HttpJsonResult<List<ProductCate>>();
        SellerUser user = WebSellerSession.getSellerUser(request);
        if (null == user) {
            //DomainUrlUtil.getEJS_URL_RESOURCES() + "/seller/login.html"

        }
        //所有商家初始化列表一致user.getSellerId()
        ServiceResult<List<ProductCate>> serviceResult = productCateService
            .getCateBySellerId(0, id);
        if (serviceResult.getSuccess() == true && serviceResult.getResult() != null) {
            jsonResult.setRows(serviceResult.getResult());
        }

        return jsonResult;
    }

    /**
     * ajax商品图片上传
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "uploadFiles", method = { RequestMethod.POST })
    public @ResponseBody Object uploadImage(MultipartHttpServletRequest request,
                                            HttpServletResponse response, String fileIndex) {
        log.info("UploadImageController uploadImage start");
        HttpJsonResult<Map<String, Object>> jsonResult = new HttpJsonResult<Map<String, Object>>();
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            MultiValueMap<String, MultipartFile> map = request.getMultiFileMap();
            if (map != null) {
                Iterator<String> iter = map.keySet().iterator();
                while (iter.hasNext()) {
                    String str = (String) iter.next();
                    List<MultipartFile> fileList = map.get(str);
                    for (MultipartFile mpf : fileList) {
                        String originalFilename = mpf.getOriginalFilename();
                        File tmpFile = new File(buildImgPath(request)
                                                + "/"
                                                + UUID.randomUUID()
                                                + originalFilename.substring(
                                                    originalFilename.lastIndexOf("."),
                                                    originalFilename.length()));
                        if (!mpf.isEmpty()) {
                            byte[] bytes = mpf.getBytes();
                            BufferedOutputStream stream = new BufferedOutputStream(
                                new FileOutputStream(tmpFile));
                            stream.write(bytes);
                            stream.close();
                        }

                        String url = UploadUtil.getInstance()
                            .productUploaderImage(tmpFile, request);

                        tmpFile.deleteOnExit();

                        //规范路径,以避免浏览器兼容问题
                        url = url.replaceAll("\\\\", "/");
                        result.put("url", DomainUrlUtil.getEJS_IMAGE_RESOURCES() + url);
                        result.put("fileIndex", fileIndex);
                        jsonResult.setData(result);

                        log.debug("url==================" + url);
                        log.debug("fileIndex==================" + fileIndex);

                        return jsonResult;
                    }
                }
            }
        } catch (Exception e) {
            log.error("[shoppingmall-memer-web][UploadImageController][uploadImage] exception:", e);
            jsonResult.setMessage(e.getMessage());
            return jsonResult;
        }
        return null;
    }

    /**
     * 保存或者更新商品
     *
     * @param product
     * @param request
     * @param seller
     * @param type    C:保存 U:更新
     * @return
     */
    private ServiceResult<Boolean> createOrUpdateProduct(Product product,
                                                         HttpServletRequest request, Seller seller,
                                                         String type, SellerUser sellerUser) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            if (seller.getId() == 1) {
                product.setIsSelf(Product.IsSelfEnum.SELF.getValue());//自营
                product.setVirtualSales(Integer.valueOf(request.getParameter("virtualSales")));
            } else {
                product.setIsSelf(Product.IsSelfEnum.SELLER.getValue());//商家
            }
            Integer productBrandId = ConvertUtil.toInt(request.getParameter("productBrandId"), 0);
            if (0 == productBrandId) {
                //请选择品牌
                //return;
            }
            Integer sellerCateId = ConvertUtil.toInt(request.getParameter("sellerCateId"), 0);
            if (0 == sellerCateId) {
                //请选择商家分类
                //return;
            }
            String isNormStr = request.getParameter("isNorm");
            if (isNormStr == null) {
                // 编辑商品时表单为disabled，取不到值
                isNormStr = request.getParameter("isNormHidden");
            }
            product.setSellerId(seller.getId());
            Integer isNorm = ConvertUtil.toInt(isNormStr, 1);
            if (2 == isNorm) {
                processGoods(request.getParameter("productGoods"), product);
            } else {
                product.setSku(request.getParameter("sku"));
            }
            product.setProductUrl(request.getParameter("productUrl"));
            String inStockWarning = request.getParameter("inStockWarning");
            int sw = 0;
            if(inStockWarning!=null && !"".equals(inStockWarning)){
                sw = Integer.valueOf(inStockWarning);
                product.setInStockWarning(sw);
            }
            product.setMalMobilePrice(product.getMallPcPrice());
            product.setProductBrandId(productBrandId);
            product.setKeyword(request.getParameter("keyword1"));
            Integer auditStatus = seller.getAuditStatus();
            if (auditStatus.intValue() == Seller.AUDIT_STATE_2_DONE) {
                auditStatus = Product.SELLER_STATE_1;
            } else {
                auditStatus = Product.SELLER_STATE_2;
            }
            String productStock1 =  request.getParameter("productStockHidden");
            if(productStock1!=null && !"".equals(productStock1)){
            	product.setProductStock(Integer.valueOf(productStock1));
            }
            product.setSellerState(auditStatus);// 店铺状态
            product.setCommentsNumber(ConvertUtil.toInt(request.getParameter("commentsNumber"), 0));
            product.setSellerCateId(sellerCateId);
            product.setVirtualSales(ConvertUtil.toInt(request.getParameter("virtualSales"), 0));
            product.setActualSales(ConvertUtil.toInt(request.getParameter("actualSales"), 0));
            product.setCreateId(sellerUser.getId());
            product.setKeyword(request.getParameter("keyword1"));
            BigDecimal percentageScale = ConvertUtil.toDecimal(
                request.getParameter("percentageScale"), new BigDecimal(0.00));
            product.setPercentageScale(percentageScale);
            product.setProductCateState(1);//分类正常
            product.setIsTop(1);//不推荐
            String isTop = request.getParameter("is_top") == null ?"1":request.getParameter("is_top");
            if(!"".equals(isTop)&&!"1".equals(isTop)){
                product.setIsTop(Integer.valueOf(isTop));
            }
            if (!StringUtil.isEmpty(product.getDescription())) {
                String description = product.getDescription();
                description = description.replaceAll(System.getProperty("line.separator"), "");
                product.setDescription(description);
            }
            // 是否是虚拟商品
            product.setIsInventedProduct(ConvertUtil.toInt(
                request.getParameter("isInventedProduct"), 1));

            List<ProductPicture> picList = new ArrayList<ProductPicture>();
            List<ProductAttr> attrList = new ArrayList<ProductAttr>();
            //product picture
            String pics = request.getParameter("imageSrc");
            if (!StringUtil.isEmpty(pics)) {
                String[] split = pics.split(";");
                for (int i = 0; i < split.length; i++) {
                    ProductPicture picture = new ProductPicture();
                    String img = split[i];
                    //String tmpStr = DomainUrlUtil.getEJS_IMAGE_RESOURCES().substring(
                    //    DomainUrlUtil.getEJS_IMAGE_RESOURCES().lastIndexOf("/"),
                    //    DomainUrlUtil.getEJS_IMAGE_RESOURCES().length());
                    //img = img.substring(img.indexOf(tmpStr) + 9, img.length());
                    img = img.replace(DomainUrlUtil.getEJS_IMAGE_RESOURCES(), "");
                    picture.setImagePath(img);
                    picture.setSort(i);
                    picture.setCreateId(sellerUser.getId());
                    picture.setState(1);
                    picture.setSellerId(seller.getId());
                    if (i == 0) {
                        picture.setProductLead(1);
                        product.setMasterImg(img);
                    } else {
                        picture.setProductLead(2);
                    }
                    picList.add(picture);
                }
            }
            //商品查询属性
            String queryType = request.getParameter("queryType");
            if (!StringUtil.isEmpty(queryType)) {
                String[] split = queryType.split(";");//productTypeAttrId,name,value
                for (String str : split) {
                    String[] split1 = str.split(",");
                    if (split1.length != 3)
                        continue;
                    ProductAttr productAttr = new ProductAttr();
                    productAttr.setProductTypeAttrId(Integer.valueOf(split1[0]));
                    productAttr.setName(split1[1]);
                    if ("-1".equals(split1[2])) {
                        productAttr.setValue("");
                    } else {
                        productAttr.setValue(split1[2]);
                    }
                    productAttr.setState(1);//检索属性
                    attrList.add(productAttr);
                }
            }
            //商品自定义属性
            String custAttr = request.getParameter("custAttr");
            if (!StringUtil.isEmpty(custAttr)) {
                String[] split = custAttr.split(";");//productTypeAttrId,name,value
                for (String str : split) {
                    String[] split1 = str.split(",");
                    ProductAttr productAttr = new ProductAttr();
                    productAttr.setProductTypeAttrId(Integer.valueOf(split1[0]));
                    productAttr.setName(split1[1]);
                    if (split1.length == 2) {
                        if(split1[1].equals("克重（净重）")){
                            productAttr.setValue("45.00");
                        }else{
                            productAttr.setValue("");
                        }
                    } else {
                        productAttr.setValue(split1[2]);
                    }
                    if(split1[1].equals("包装规格")&&"-1".equals(split1[2])){
                        productAttr.setValue("10双");
                    }
                    productAttr.setState(3);//商品自定义属性
                    attrList.add(productAttr);
                }
            }

            String sku_others = request.getParameter("sku_other");
            if ("C".equals(type)) {
                //新建商品默认价格是一口价
                product.setPriceStatus(1);
                int state = ConvertUtil.toInt(request.getParameter("state"),
                    Product.StateEnum.CREATE.getValue());
                product.setState(state);
                product.setSkuOther(sku_others);
                result = productService.saveProduct(product, picList, attrList);
            }
            if ("U".equals(type)) {
                product.setSkuOther(sku_others);
                //审核通过后重置商品状态
                Product p = productService.getProductById(product.getId()).getResult();
                int state = p.getState();
                if(state == 3){
                    state = 2;
                }
                product.setState(state);
                result = productService.updateProduct(product, picList, attrList);
            }

            //保存sku图片信息
            if (isNorm == 2) {
                if (result.getSuccess() == true)
                    processSKUPic(request, product);
            }
        } catch (BusinessException e) {
            result.setMessage(e.getMessage());
            result.setSuccess(false);
        } catch (Exception e) {
            e.printStackTrace();
            result.setMessage("操作商品发生错误,请联系管理员");
            result.setSuccess(false);
        }
        return result;
    }

    private void processSKUPic(HttpServletRequest request, Product product) throws Exception {
        String skupics = request.getParameter("skupics");
        if (isNull(skupics)) {
            return;
        }

        Map<String, String> queryMap = new HashMap<String, String>();
        queryMap.put("sellerId", WebSellerSession.getSellerUser(request).getSellerId() + "");
        queryMap.put("flag", "2");
        queryMap.put("productId", product.getId() + "");
        ServiceResult<List<ProductNormAttrOpt>> optservice = productNormAttrOptService.page(
            queryMap, null);
        List<ProductNormAttrOpt> optlist = optservice.getResult();

        if (skupics.length() < 1) {
            throw new BusinessException("sku规格错误");
        }
        for (String info : skupics.split(",")) {
            //normid_@_normname_@_attrid_@_name_@_url
            String[] optarr = info.split("_@_");
            String normid = optarr[0];
            String normname = optarr[1];
            String id = optarr[2];
            String name = optarr[3];
            String url = optarr[4];
            String colortype = optarr[5];

            if (optlist.size() > 0) {
                //编辑
                for (ProductNormAttrOpt opt1 : optlist) {
                    if (opt1.getAttrId().intValue() == Integer.valueOf(id).intValue()) {
                        //                        opt1.setProductNormId(Integer.valueOf(normid));
                        //                        opt1.setProductNormName(normname);
                        opt1.setImage(url);
                        //                        opt1.setName(name);
                        //                        opt1.setProductId(proid);
                        //                        opt1.setFlag(2);
                        productNormAttrOptService.updateProductNormAttrOpt(opt1);
                    }
                }
            } else {
                //新增
                ProductNormAttrOpt opt = new ProductNormAttrOpt();
                Integer sellerId = WebSellerSession.getSellerUser(request).getSellerId();
                opt.setSellerId(sellerId);
                opt.setCreateId(sellerId);
                opt.setTypeAttr(!isNull(colortype) && "custom".equals(colortype) ? 2 : 1);
                opt.setCreateTime(new Date());
                opt.setType(2);//图片
                opt.setAttrId(Integer.valueOf(id));//属性值

                opt.setProductNormId(Integer.valueOf(normid));
                opt.setProductNormName(normname);
                opt.setImage(url);
                opt.setName(name);
                opt.setProductId(product.getId());
                opt.setFlag(2);//已创建
                productNormAttrOptService.saveProductNormAttrOpt(opt);
            }
            product.getGoodsList();
            //货品图片
            for (ProductGoods pg : product.getGoodsList()) {
                if (Integer.valueOf(pg.getNormAttrId()).intValue() == Integer.valueOf(id)
                    .intValue()) {
                    pg.setImages(url);

                    productGoodsService.updateProductGoods(pg);
                }
            }

        }
    }

    @SuppressWarnings("deprecation")
    private String buildImgPath(HttpServletRequest request) {
        String path = "upload";
        SimpleDateFormat formater = new SimpleDateFormat("yyyyMMdd");
        path += "/" + formater.format(new Date());
        path = request.getRealPath(path);
        File dir = new File(path);
        if (!dir.exists()) {
            try {
                dir.mkdirs();
            } catch (Exception e) {
                log.error("error", e);
            }
        }
        return path;
    }

    /**
     * 组装货品信息
     *
     * @param productGoodStr
     * @param product
     */
    private void processGoods(String productGoodStr, Product product) throws BusinessException {
        if (!StringUtil.isEmpty(productGoodStr)) {
            String[] goods = productGoodStr.split(";");
            ArrayList<ProductGoods> goodList = new ArrayList<ProductGoods>();
            product.setGoodsList(goodList);
            if (null != goods && goods.length > 0 && goods.length == 1) {
                //一个货品.例如:67,12,21
                goodList.add(processProductGoods(product.getSellerId(), new ProductGoods(),
                    goods[0]));
            } else if (null != goods && goods.length > 0 && goods.length > 1) {
                //多个货品.例如:67,72,12,21;67,73,23,32......
                for (String str : goods) {
                    goodList
                        .add(processProductGoods(product.getSellerId(), new ProductGoods(), str));
                }
            }
        }
    }

    private ProductGoods processProductGoods(Integer sellerId, ProductGoods productGoods, String str)
                                                                                                     throws BusinessException {
        String[] split = str.split(",");
        if (split.length == 9) {
            String normattr = split[0].trim();
            String[] norminfo = normattr.split("!@#");
            productGoods.setNormAttrId(norminfo[0]);//规格属性值ID
            productGoods.setNormName(norminfo[1] + "," + norminfo[2]);

            //            //设置货品图片
            //            Map<String, String> queryMap = new HashMap<String, String>();
            //            queryMap.put("sellerId", sellerId + "");
            //            //只有编辑时才会有productId,
            //            queryMap.put("productId", productGoods.getProductId() + "");
            //            queryMap.put("attrId", norminfo[0]);
            //            ServiceResult<List<ProductNormAttrOpt>> optservice = productNormAttrOptService.page(
            //                queryMap, null);
            //            if (optservice.getResult() != null && optservice.getResult().size() > 0) {
            //                ProductNormAttrOpt opt = optservice.getResult().get(0);
            //                productGoods.setImages(opt.getImage());
            //            }

            productGoods.setSku(split[1].trim());
            Integer stock = ConvertUtil.toInt(split[2].trim(), -1);
            if (-1 == stock) {
                throw new BusinessException("库存输入有误,请重新输入");
            }
            productGoods.setProductStock(stock);//库存
            try {
                BigDecimal bigDecimal = new BigDecimal(split[3].trim());
                productGoods.setMallPcPrice(bigDecimal);//PC价格
            } catch (Exception e) {
                throw new BusinessException("PC价格输入有误,请重新输入");
            }
            try {
                BigDecimal bigDecimal = new BigDecimal(split[4].trim());
                productGoods.setMallMobilePrice(bigDecimal);//mobile价格
            } catch (Exception e) {
                throw new BusinessException("mobile价格输入有误,请重新输入");
            }
            try {
                productGoods.setBarCode(split[5].trim());//商品条码（EA）
            } catch (Exception e) {
                throw new BusinessException("商品条码（EA）输入有误,请重新输入");
            }
            try {
                productGoods.setBarCodePL(split[6].trim());//商品条码（PL）
            } catch (Exception e) {
                throw new BusinessException("商品条码（PL）输入有误,请重新输入");
            }
            try {
                productGoods.setBarCodeCS(split[7].trim());//商品条码（CS）
            } catch (Exception e) {
                throw new BusinessException("商品条码（CS）输入有误,请重新输入");
            }
            try {
                productGoods.setIsVirtualBom(Integer.parseInt(split[8].trim()));
            } catch (Exception e) {
            	productGoods.setIsVirtualBom(0);
                //throw new BusinessException("商品编码输入有误,请重新输入");
            }
        } else if (split.length == 7) {
            String normattr1 = split[0].trim();
            String[] norminfo1 = normattr1.split("!@#");
            String normattr2 = split[1].trim();
            String[] norminfo2 = normattr2.split("!@#");

            productGoods.setNormAttrId(norminfo1[0] + "," + norminfo2[0]);//规格属性值ID
            productGoods.setNormName(norminfo1[1] + "," + norminfo1[2] + ";" + norminfo2[1] + ","
                                     + norminfo2[2]);

            //            //设置货品图片
            //            Map<String, String> queryMap = new HashMap<String, String>();
            //            queryMap.put("sellerId", sellerId + "");
            //            ServiceResult<List<ProductNormAttrOpt>> optservice = productNormAttrOptService.page(
            //                queryMap, null);
            //            if (optservice.getResult() != null && optservice.getResult().size() > 0) {
            //                for (ProductNormAttrOpt opt : optservice.getResult()) {
            //                    //因规格类型是图片，attrid为两个规格中的一个时，设置图片
            //                    if (opt.getAttrId() == Integer.valueOf(norminfo1[0])
            //                        || opt.getAttrId() == Integer.valueOf(norminfo2[0]))
            //                        productGoods.setImages(opt.getImage());
            //                }
            //            }

            productGoods.setSku(split[2].trim());
            Integer stock = ConvertUtil.toInt(split[3].trim(), -1);
            if (-1 == stock) {
                throw new BusinessException("库存输入有误,请重新输入");
            }
            productGoods.setProductStock(stock);//库存
            try {
                BigDecimal bigDecimal = new BigDecimal(split[4].trim());
                productGoods.setMallPcPrice(bigDecimal);//PC价格
            } catch (Exception e) {
                throw new BusinessException("PC价格输入有误,请重新输入");
            }
            try {
                BigDecimal bigDecimal = new BigDecimal(split[5].trim());
                productGoods.setMallMobilePrice(bigDecimal);//mobile价格
            } catch (Exception e) {
                throw new BusinessException("mobile价格输入有误,请重新输入");
            }
            try {
                productGoods.setBarCode(split[6].trim());//商品编码
            } catch (Exception e) {
                throw new BusinessException("商品编码输入有误,请重新输入");
            }
        }
        productGoods.setProductStockWarning(0);
        productGoods.setActualSales(0);
        return productGoods;
    }

    @RequestMapping(value = "addsuccess", method = { RequestMethod.GET })
    public String createSuc(HttpServletRequest request) {
        return baseUrl + "addsuccess";
    }

    /**
     * 商品列表无分页
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "listnopage", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<Product>> listNoPage(HttpServletRequest request,
                                                                  Map<String, Object> dataMap) {

        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        ServiceResult<List<Product>> serviceResult = productService.pageProduct(queryMap, null);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }

        HttpJsonResult<List<Product>> jsonResult = new HttpJsonResult<List<Product>>();
        jsonResult.setRows(serviceResult.getResult());
        return jsonResult;
    }

    @RequestMapping(value = "validate", method = { RequestMethod.POST })
    public @ResponseBody HttpJsonResult<Object> validate(HttpServletRequest request,
                                                         @RequestParam("spu") String spu,
                                                         @RequestParam("sku") String sku) {
        HttpJsonResult<Object> jsonResult = new HttpJsonResult<Object>();
        jsonResult.setMessage("");
        Map<String, String> queryMap = new HashMap<String, String>();
        Integer sellerId = WebSellerSession.getSellerUser(request).getSellerId();
        queryMap.put("sellerId", sellerId.toString());
        if (!"".equals(spu)) {//spu校验
            queryMap.put("spu", spu);
            ServiceResult<Boolean> serviceResult = productService
                .checkProductBySPUAndSellerId(queryMap);
            if (serviceResult.getResult()) {
                jsonResult.setMessage("该商品对应SPU已存在，请重新输入！");
                return jsonResult;
            }
        }
        if (!"".equals(sku)) {//sku校验
            queryMap.put("sku", sku);
            ServiceResult<Boolean> serviceResult = productGoodsService
                .checkProductBySKUAndSeller(queryMap);
            if (serviceResult.getResult()) {
                jsonResult.setMessage("该商品对应SKU已存在，请重新输入！");
                return jsonResult;
            }
        }
        return jsonResult;
    }

    @RequestMapping(value = "validatesku", method = { RequestMethod.POST })
    public @ResponseBody HttpJsonResult<Object> validatesku(HttpServletRequest request,
                                                            @RequestParam("sku") String sku) {
        HttpJsonResult<Object> jsonResult = new HttpJsonResult<Object>();
        jsonResult.setMessage("");
        if (!"".equals(sku)) {//sku校验
            Map<String, String> queryMap = new HashMap<String, String>();
            Integer sellerId = WebSellerSession.getSellerUser(request).getSellerId();
            queryMap.put("sellerId", sellerId.toString());
            queryMap.put("sku", sku);
            ServiceResult<Boolean> serviceResult = productGoodsService
                .checkProductBySKUAndSeller(queryMap);
            if (serviceResult.getResult()) {
                jsonResult.setMessage("该商品对应SKU已存在，请重新输入！");
                return jsonResult;
            }
        } else {
            String barCode = StringUtil.nullSafeString(request.getParameter("barCode"));
            if (!"".equals(barCode)) {//barCode校验
                Map<String, String> queryMap = new HashMap<String, String>();
                queryMap.put("barCode", barCode);
                String id = StringUtil.nullSafeString(request.getParameter("id"));
                if (!StringUtil.isEmpty(id)) {
                    queryMap.put("id", id);
                }
                ServiceResult<Boolean> serviceResult = productGoodsService.checkBarCodeIsExsit(queryMap);
                if (serviceResult.getResult()) {
                    jsonResult.setMessage("商品编码已存在，请重新输入！");
                    return jsonResult;
                }
            }
        }
        return jsonResult;
    }

    /**
     * 在发布商品页中，SKU辅料数据动态渲染
     * @param request
     * @param response
     * @author 仝照美
     */
    @RequestMapping(value = "getProductLabelName", method = { RequestMethod.GET })
    @ResponseBody
    public void getProductLabelName(HttpServletRequest request, HttpServletResponse response) {
        try {
            String skuOther = request.getParameter("skuOther");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out;
            String str = "";
            out = response.getWriter();
            //skuOther 如果 为null则是点击浏览触发 否则 是其他触发 
            if (skuOther == null || "".equals(skuOther)) {
                //新增  浏览
                ServiceResult<List<ProductLabel>> serviceResult = productLabelService
                    .getProductLabelName();
                List<ProductLabel> productLabels = serviceResult.getResult();
                if (productLabels.size() > 0) {
                    str += "<div style=\"padding:15px;\">";
                    str += "<form id=\"productLabelNameFormId\">";
                    for (int i = 0; i < productLabels.size(); i++) {
                        str += "<label><input name=\"productLabelName\" type=\"checkbox\" value=\""
                               + productLabels.get(i).getSku() + "\" />"
                               + productLabels.get(i).getName() + "</label> ";
                        if (i != 0 && i % 5 == 0) {// 是5的倍数则换行
                            str += "<br><br>";
                        }
                    }
                    str += "</form>";
                    str += "</div>";
                }
            } else {
                String[] skuothers = skuOther.split(",");
                ServiceResult<List<ProductLabel>> serviceResult = productLabelService
                    .getProductLabelNameByskuother(skuothers);
                List<ProductLabel> productLabels = serviceResult.getResult();
                str += JSON.toJSONString(productLabels, true);
            }
            out.write(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
