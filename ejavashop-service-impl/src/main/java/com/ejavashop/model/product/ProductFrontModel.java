package com.ejavashop.model.product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.springframework.stereotype.Component;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.dao.promotion.read.single.ActSingleReadDao;
import com.ejavashop.dao.shop.read.product.ProductAttrReadDao;
import com.ejavashop.dao.shop.read.product.ProductBrandReadDao;
import com.ejavashop.dao.shop.read.product.ProductCateReadDao;
import com.ejavashop.dao.shop.read.product.ProductPriceReadDao;
import com.ejavashop.dao.shop.read.product.ProductReadDao;
import com.ejavashop.dao.shop.read.product.ProductTypeAttrReadDao;
import com.ejavashop.dao.shop.read.product.ProductTypeReadDao;
import com.ejavashop.dao.shop.read.seller.SellerCateReadDao;
import com.ejavashop.dao.shop.write.product.ProductPriceWriteDao;
import com.ejavashop.dao.shop.write.product.ProductWriteDao;
import com.ejavashop.entity.product.Product;
import com.ejavashop.entity.product.ProductAttr;
import com.ejavashop.entity.product.ProductBrand;
import com.ejavashop.entity.product.ProductCate;
import com.ejavashop.entity.product.ProductPrice;
import com.ejavashop.entity.product.ProductType;
import com.ejavashop.entity.product.ProductTypeAttr;
import com.ejavashop.entity.promotion.single.ActSingle;
import com.ejavashop.entity.seller.SellerCate;
import com.ejavashop.vo.product.ProductTypeAttrVO;
import com.ejavashop.vo.product.ProductTypeVO;

@Component(value = "productFrontModel")
public class ProductFrontModel {

    @Resource
    private ProductReadDao         productReadDao;
    @Resource
    private ProductCateReadDao     productCateReadDao;
    @Resource
    private ProductBrandReadDao    productBrandReadDao;
    @Resource
    private ProductAttrReadDao     productAttrReadDao;
    @Resource
    private ProductTypeReadDao     productTypeReadDao;
    @Resource
    private ProductTypeAttrReadDao productTypeAttrReadDao;

    @Resource
    private SellerCateReadDao      sellerCateReadDao;
    @Resource
    private ProductPriceWriteDao   productPriceWriteDao;
    @Resource
    private ProductPriceReadDao   productPriceReadDao;
    @Resource
    private ProductWriteDao        productWriteDao;
    @Resource
    private ActSingleReadDao       actSingleReadDao;    
    private static ServletContext  serlvetContext;
    private static byte[]          sync = new byte[2];

    public List<Product> getProductsBySellerId(Integer sellerid) {
        if (sellerid == null)
            throw new BusinessException("没有商家");
        return productReadDao.getProductsBySellerId(sellerid);
    }

    /**
     * 根据分类列表页查询商品
     * @param cateId
     * @param mapCondition
     * @param stack
     * @param context 
     */
    public void getProducts(Integer cateId, Map<String, Object> mapCondition,
                            Map<String, Object> stack, Object context) {
        synchronized (sync) {
            if (serlvetContext == null && context != null)
                serlvetContext = (ServletContext) context;
        }
        //取得分类
        if (cateId == null) {
            throw new BusinessException("传入的分类路径不能为空");
        }

        Map<String, Object> initSearchMap = getAllSearchContidtion(cateId, mapCondition, stack);
        List<ProductTypeAttr> productTypeAttrs = (List<ProductTypeAttr>)initSearchMap.get("productTypeAttrs");
        Map<String, ProductTypeAttr> mapProductTypeAttrs = (Map<String, ProductTypeAttr>)initSearchMap.get("mapProductTypeAttrs");
        
        //查询出商品
        Integer sort = 0;
        try {
            sort = (Integer) mapCondition.get("sort");
        } catch (Exception e) {
            sort = 0;
        }
        if (sort == null || sort > 9) {
            sort = 0;
        }
        stack.put("sort", sort);
        
        Integer doSelf = (Integer) mapCondition.get("doSelf");
        Integer store = (Integer) mapCondition.get("store");
        Double startPrice = (Double) mapCondition.get("startPrice");
        Double endPrice = (Double) mapCondition.get("endPrice");
        Integer brandId= (Integer)mapCondition.get("brandId");
        
        Integer startStock = (Integer)mapCondition.get("startStock");
        Integer endStock = (Integer)mapCondition.get("endStock");
        //库存排序
        Integer stock = (Integer)mapCondition.get("stock");
        List<Product> products = new ArrayList<Product>();
        
        String is_top = mapCondition.get("filterAll").toString();
        boolean isSpecial = false;              //如果filterAll只有当前999M、888M等，则不需要走filterQueryAttr
        if(is_top.startsWith("p")){//以p开头的要通过其他分类字段、或者从pc楼层分类进行的查询
        	 products.clear();
        	 String isTopstr = is_top.substring(1, is_top.length());
             String[] wherealls = splitFilterParams(is_top);
             if (wherealls.length > 1) {
            	isTopstr = wherealls[0];
             	mapCondition.put("filterAll", mapCondition.get("filterAll").toString().replace("999M-", ""));
             }
             else {
                 isSpecial = true;
             }
        	 Integer intFlag = Integer.valueOf(isTopstr);
        	 products = productReadDao.getProductByOtherCategory(intFlag, sort, doSelf, store,startPrice,endPrice,stock,startStock,endStock);//分别对应猫头鹰，推荐商品
        }else if(is_top.contains("999M")){
            if (sort == 0){
                sort = 999;
            }
            products.clear();
            Integer sellerId = null;
            Integer isTop = 2;
            products = productReadDao.getProductByIsTop(sellerId,sort,isTop,startPrice,endPrice,stock,startStock,endStock);//分别对应猫头鹰，推荐商品
            if (splitFilterParams(is_top).length == 1) 
            	isSpecial = true;
            else
            	mapCondition.put("filterAll", mapCondition.get("filterAll").toString().replace("999M-", ""));
        }
        else if(is_top.contains("777M")){//九折疯抢商品即单品立减打九折商品
        	products.clear();
        	Integer state = 5;
        	List<ActSingle> actSingles = actSingleReadDao.getSingleByState(state);
        	List<Product> productsList = new ArrayList<Product>();
        	
        	if(actSingles != null && actSingles.size()>0){
            	String singleProductId = "";
            	for (ActSingle actSingle : actSingles) {
            		singleProductId += actSingle.getProductIds().substring(1, actSingle.getProductIds().length());
    			}
            	singleProductId = singleProductId.substring(0, singleProductId.lastIndexOf(","));
            	String[] singleProductIds = singleProductId.split(",");
            	productsList = productReadDao.getProductBySingle(sort,singleProductIds,startPrice,endPrice,stock,startStock,endStock);
            	
            	handleActSingle(productsList, actSingles);
            	
        	}
        	products.addAll(productsList);
            if (splitFilterParams(is_top).length == 1) 
            	isSpecial = true;
            else
            	mapCondition.put("filterAll", mapCondition.get("filterAll").toString().replace("777M-", ""));
        }
        //获得除了裸袜之外的所有品牌袜
        else if(is_top.contains("xppw")){
        	products.clear();
        	products = productReadDao.getNoLuowaByBrandId(cateId, sort, doSelf, store,brandId,startPrice,endPrice,stock,startStock,endStock);
            if (splitFilterParams(is_top).length == 1) 
            	isSpecial = true;
            else
            	mapCondition.put("filterAll", mapCondition.get("filterAll").toString().replace("xppw-", ""));
        }
        else if(is_top.contains("155_6")||is_top.contains("155_14")){
            products.clear();
            products = productReadDao.getProductYangMao(cateId, sort, doSelf, store,brandId,startPrice,endPrice,stock,startStock,endStock);
            if (splitFilterParams(is_top).length == 1) 
            	isSpecial = true;
        }
        else{
            products = productReadDao.getByCateId(cateId, sort, doSelf, store,startPrice,endPrice,stock,startStock,endStock, brandId);
        }

        //拼装列表页查询条件的VO对象
        installProductAttr(stack, productTypeAttrs, mapCondition.get("filterAll").toString());
        if (products.size() > 0 && !isSpecial) {
	        //根据查询条件过滤商品的到查询条件的Name的集合
            filterQueryAttr(mapCondition, stack, mapProductTypeAttrs, products);
	        //取消商城首页热卖推荐
	//      noQueryProductAttr(mapCondition, stack, mapProductTypeAttrs, productTypeAttrIds);
	    }

        //筛选品牌
        filterBrand(mapCondition, stack, products);
        //分页
        pageProductsList(mapCondition, stack, products);
    }
    
    /**
     * 处理h5端，只需要加载一次查询条件，不需要每次都进行加载
     */
    public Map<String, Object> getAllSearchContidtion(Integer cateId, Map<String, Object> mapCondition, Map<String, Object> stack) {
        //品牌  
        Map<String, Object> map = new HashMap<String, Object>();
        ProductCate productCate = findCateAll(cateId, stack);

        //取得类型
        ProductType productType = productTypeReadDao.get(productCate.getProductTypeId());

        //查询出类型下面关联的品牌，并对首字母进行排序
        findBrandAll(stack, productType.getProductBrandIds());

        //查询出类型下面关联的查询属性
        List<ProductTypeAttr> productTypeAttrs = productTypeAttrReadDao.getByTypeIdAndQuery(productType.getId());
//        Terry 20170323 remark
        /*
        List<ProductTypeAttr> productTypeAttrsAll = new ArrayList<ProductTypeAttr>();
        for (ProductTypeAttr productTypeAttr : productTypeAttrs) {
            productTypeAttrsAll.add(productTypeAttr);
        }
        */
//        mapCondition.put("productTypeAttrsAll", productTypeAttrs);

        //组装类型查询Map对象，键是ID，值是查询的对象
        Map<String, ProductTypeAttr> mapProductTypeAttrs = new HashMap<String, ProductTypeAttr>();
        for (ProductTypeAttr productTypeAttr : productTypeAttrs) {
            mapProductTypeAttrs.put(productTypeAttr.getId().toString(), productTypeAttr);
        }
        map.put("productTypeAttrs", productTypeAttrs);
//        mapCondition.put("productTypeAttrsAll", productTypeAttrs);
        map.put("mapProductTypeAttrs", mapProductTypeAttrs);
        return map;
    }
    
    /**
     * 
     * @param productsList
     * @param actSingles
     */
    private void handleActSingle(List<Product> productsList, List<ActSingle> actSingles) {
        if (productsList != null && productsList.size() > 0) {
            for (Product pd : productsList) {
                for (ActSingle as : actSingles) {
                    if (as.getProductIds().contains("," + pd.getId() + ",")) {
                        BigDecimal discount = as.getDiscount();
                        if (discount != null && discount.compareTo(BigDecimal.ZERO) == 0) continue;
                        BigDecimal currentPrice = pd.getMallPcPrice();
                        BigDecimal currentMobilePrice = pd.getMalMobilePrice();
//                        System.out.println(">>>>>>>> " + pd.getId() +">>>>>>>> " + currentPrice +">>>>>>>> " + currentMobilePrice + "  --------- " + as.getProductIds() + "  ========= " + discount + " xx = " + (discount.equals(BigDecimal.ZERO)));
                        pd.setMarketPrice(currentPrice);
                        if (as.getType() == ActSingle.TYPE_1) {
                            pd.setMallPcPrice(currentPrice.subtract(discount));
                            pd.setMalMobilePrice(currentMobilePrice.subtract(discount));
                        }
                        else if (as.getType() == ActSingle.TYPE_2) {
                            pd.setMallPcPrice(currentPrice.multiply(discount).setScale(2, BigDecimal.ROUND_HALF_UP));
                            pd.setMalMobilePrice(currentMobilePrice.multiply(discount).setScale(2, BigDecimal.ROUND_HALF_UP));
                        }
                        continue;
                    }
                }
            }
        }
    }

    /**
     * 没有查询出商品，点击过滤条件的筛选功能
     * @param mapCondition
     * @param stack
     * @param mapProductTypeAttrs
     * @param productTypeAttrIds
     */
    private void noQueryProductAttr(Map<String, Object> mapCondition, Map<String, Object> stack,
                                    Map<String, ProductTypeAttr> mapProductTypeAttrs, Set<Integer> productTypeAttrIds) {
        if (productTypeAttrIds.size() == 0) {
            String whereall = (String) mapCondition.get("filterAll");
            if (whereall != null && !"".equals(whereall.trim())) {
                Map<String, String> productTypeAttrWhereAlls = new HashMap<String, String>();
                String[] wherealls = whereall.split("-");
                for (String strings : wherealls) {
                    String[] wherealls_ = strings.split("_");
                    if (wherealls_.length == 2) {
                        int attrId = Integer.valueOf(wherealls_[0]).intValue(); //属性ID
                        int attrValueIndex = Integer.valueOf(wherealls_[1]).intValue(); //属性值所属的ID位置
                        productTypeAttrIds.add(attrId);
                        String attrName = null;//查询属性名称
                        String attrValue = null;//存放属性值
                        ProductTypeAttr productTypeAttr = mapProductTypeAttrs.get(attrId + "");
                        if (productTypeAttr != null) {
                            String[] trypeValues = productTypeAttr.getValue().split(",");
                            attrValue = trypeValues[attrValueIndex];
                            attrName = productTypeAttr.getName();
                        }
                        if (attrValue != null && attrName != null) {
                            productTypeAttrWhereAlls.put(strings, attrName + ":" + attrValue);
                        }
                    }
                }
                stack.put("productTypeAttrWhereAlls", productTypeAttrWhereAlls);
            }
        }
    }

    /**
     * 分页
     * @param mapCondition
     * @param stack
     * @param products
     * @param copier
     */
    private void pageProductsList(Map<String, Object> mapCondition, Map<String, Object> stack, List<Product> products) {
        int productSize = products.size();
        stack.put("productSize", productSize);
        Integer page = 0;
        try {
            page = (Integer) mapCondition.get("page");
        } catch (Exception e) {
            page = 0;
        }
        if (page == null || page.intValue() == 0 || page.intValue() == 1) {
            page = 1;
        }
        int pagesize = stack.get("pagesize") == null ? ConstantsEJS.DEFAULT_PAGE_SIZE : Integer.parseInt(stack.get("pagesize").toString());
        int startNumber = (page - 1) * pagesize;
        int endNumber = page * pagesize;
        int pageEndNumber = (productSize > endNumber) ? endNumber : productSize;
        //isEndPage=1 没有下一页，isEndPage=0还有下一页，isEndPage=2栏目下没有数据
        if (productSize == 0)
            stack.put("isEndPage", 2);
        else if (productSize <= endNumber) 
            stack.put("isEndPage", 1);
        else
            stack.put("isEndPage", 0);
        List<Product> productsResult = new ArrayList<Product>();
        ProductPrice pPrice = new ProductPrice();
        Product product = new Product();
        for (int i = startNumber; i < pageEndNumber; i++) {
            //分装前段价格显示，阶梯价显示最小值到最大值，一口价正常显示，整箱价显示整箱价
            product = products.get(i);
            if (product.getPriceStatus() != null && product.getPriceStatus() == 2) {//阶梯价时借用字段保存，仅在页面进行展示
                pPrice = productPriceReadDao.get(product.getPricePid());
                if (pPrice != null)
                    product.setDescription(pPrice.getPrice3() + "-" + pPrice.getPrice1());
            }
            //          productsResult.add(products.get(i));
            productsResult.add(product);
        }

        stack.put("producListVOs", productsResult);
    }

    /**
     * 拼装列表页查询条件的VO对象
     * @param stack
     * @param productTypeAttrs
     */
    private void installProductAttr(Map<String, Object> stack, List<ProductTypeAttr> productTypeAttrs, String whereall) {
        List<ProductTypeAttrVO> productTypeAttrVOs = new ArrayList<ProductTypeAttrVO>();
        Map<String, String> productTypeAttrWhereAlls = new HashMap<String, String>();
        String[] wherealls = null;
        if (whereall != null && !"".equals(whereall.trim())) wherealls = whereall.split("-");
        Set<Integer> productTypeAttrIds = new HashSet<Integer>();
        for (ProductTypeAttr productTypeAttr : productTypeAttrs) {
            int productTypeId = productTypeAttr.getId();
            String productTypeName = productTypeAttr.getName();
            String value = productTypeAttr.getValue();
            ProductTypeAttrVO productTypeAttrVO = new ProductTypeAttrVO();
            productTypeAttrVO.setId(productTypeId);
            productTypeAttrVO.setName(productTypeName);
            productTypeAttrVO.setValue(value);

            if (value != null && !"".equals(value)) {
                List<String> values = new ArrayList<String>();
                String[] str = value.split(",");
                boolean isCondition = false;
                for (int i = 0; i < str.length; i++) {
                    String val = str[i];
                    values.add(val);
                    String key = filterCondition(wherealls, i, productTypeId);
                    if (key != null) {
                        productTypeAttrWhereAlls.put(key, productTypeName + ":" + val);
                        productTypeAttrIds.add(productTypeId);
                        isCondition = true;
                        break;
                    }
                }
                if (isCondition) continue;
                productTypeAttrVO.setValues(values);
            }
            productTypeAttrVOs.add(productTypeAttrVO);
        }
        
        stack.put("productTypeAttrWhereAlls", productTypeAttrWhereAlls);
        stack.put("productTypeAttrVOs", productTypeAttrVOs);

        //在查询条件中去掉已经存在的条件
//        filterRemoveQueryAttr(productTypeAttrs, productTypeAttrIds);
    }
    
    private String filterCondition(String[] wherealls, int i, int productTypeId) {
        if (wherealls == null) return null;
        for (String string : wherealls) {
            if (wherealls.length > 0) {
                String[] strings = string.split("_");
                if (strings.length == 2) {
                    int attrId = Integer.valueOf(strings[0]); //属性IproductTypeAttrWhereAlls
                    int attrValueIndex = Integer.valueOf(strings[1]); //属性值所属的ID位置'
                    if (attrId == productTypeId && i == attrValueIndex) {
                        return string;
                    }
                }
            }
        }
        return null;
    }
        

    /**
     * 删除已经选择的查询条件
     * @param productTypeAttrs 类型查询属性的集合
     * @param productTypeAttrIds 已经选择查询条件ID的集合
     */
    private void filterRemoveQueryAttr(List<ProductTypeAttr> productTypeAttrs, Set<Integer> productTypeAttrIds) {
        if (productTypeAttrIds.size() > 0) {
            for (Iterator<ProductTypeAttr> iterator = productTypeAttrs.iterator(); iterator.hasNext();) {
                ProductTypeAttr productTypeAttr = iterator.next();
                for (Integer productTypeAttrId : productTypeAttrIds) {
                    if (productTypeAttrId.intValue() == productTypeAttr.getId().intValue()) {
                        iterator.remove();
                        break;
                    }
                }
            }
        }
    }

    /**
     * 根据查询条件过滤商品
     * @param mapCondition
     * @param stack
     * @param mapProductTypeAttrs 类型查询属性的Map集合
     * @param products 商品
     * @return
     */
    private void filterQueryAttr(Map<String, Object> mapCondition, Map<String, Object> stack, Map<String, ProductTypeAttr> mapProductTypeAttrs, List<Product> products) {
         //存放已经查找的Product
        List<Product> productWhereall = new ArrayList<Product>();
        String whereall = (String) mapCondition.get("filterAll");
        if (whereall != null && !"".equals(whereall.trim())) {
            String[] wherealls = whereall.split("-");
            if (wherealls.length > 0) {
                //数据优化，使用serlvetContext缓存全部数据，以减少数据库连接
                List<ProductAttr> productAttrall = null;
                if (serlvetContext.getAttribute("PRODUCT_ATTR_ALL_CACHE") == null) {
                    //查询
                    Map<String, String> wheres = new HashMap<String, String>();
                    String ptaIds = "";
                    java.util.Iterator it = mapProductTypeAttrs.entrySet().iterator();
                    while(it.hasNext()){
                        java.util.Map.Entry entry = (java.util.Map.Entry)it.next();
                        ptaIds += entry.getKey() + ",";
                    }
                    wheres.put("productTypeAttrIds", ptaIds.substring(0, ptaIds.length() - 1));
                    productAttrall = productAttrReadDao.page(wheres);
                    //缓存数据
                    serlvetContext.setAttribute("PRODUCT_ATTR_ALL_CACHE", productAttrall);
                } else {
                    //直接使用已缓存数据
                    productAttrall = (List<ProductAttr>) serlvetContext.getAttribute("PRODUCT_ATTR_ALL_CACHE");
                }
                
                for (Iterator<Product> iterator = products.iterator(); iterator.hasNext();) {
                    Product product = iterator.next();
                    int countAttr = filterAttrCount(productAttrall, wherealls, product, mapProductTypeAttrs);     //Terry 
                    if (countAttr == wherealls.length) {
                        productWhereall.add(product);
                    }
                }
                products.clear();
                products.addAll(productWhereall);
            }
        }
    }
    
    
    public  boolean updateProductAttrCache(int typeId,Object context) {
    	if (serlvetContext == null && context != null)
            serlvetContext = (ServletContext) context;
        //数据优化，使用serlvetContext缓存全部数据，以减少数据库连接
        List<ProductAttr> productAttrall = null;
    	Map<String, String> wheres = new HashMap<String, String>();
    	String ptaIds = "";
    	
        //查询出类型下面关联的查询属性
        List<ProductTypeAttr> productTypeAttrs = productTypeAttrReadDao.getByTypeIdAndQuery(typeId);
        for (ProductTypeAttr productTypeAttr : productTypeAttrs) {
        	ptaIds += productTypeAttr.getId() + ",";
        }
    	
    	wheres.put("productTypeAttrIds", ptaIds.substring(0, ptaIds.length() - 1));
        productAttrall = productAttrReadDao.page(wheres);
        //缓存数据
        serlvetContext.setAttribute("PRODUCT_ATTR_ALL_CACHE", productAttrall);
        
        return true;
    }

    /**
     * 判断商品是否存在查询条件中
     * @param productTypeAttrIds 查询条件的Id集合
     * @param productTypeAttrWhereAlls 查询条件name和value
     * @param wherealls 查询条件
     * @param product 商品
     * @param productTypeAttrs 分类的查询属性
     * @return
     */
    @SuppressWarnings("unchecked")
    private int filterAttrCount(List<ProductAttr> productAttrall, String[] wherealls,
                                Product product, Map<String, ProductTypeAttr> mapProductTypeAttrs) {
        int countAttr = 0;
        //因效率问题暂时废弃
        //        List<ProductAttr> productAttrs = productAttrReadDao.getByProductId(product.getId());
        for (ProductAttr attr : productAttrall) {
            if (attr.getProductId().intValue() == product.getId().intValue()) {
                int productTypeAttrId = attr.getProductTypeAttrId().intValue();
                String value = attr.getValue();
                for (String string : wherealls) {
                    String[] strings = string.split("_");
                    if (strings.length == 2) {
                        int attrId = Integer.valueOf(strings[0]).intValue(); //属性ID
                        int attrValueIndex = Integer.valueOf(strings[1]).intValue(); //属性值所属的ID位置
                        //                    Integer attrIdNew = null;//查询属性ID
                        String attrName = null;//查询属性名称
                        String attrValue = null;//存放属性值
                        if (productTypeAttrId == attrId) { //查询属性ID和商品所有的查询属性ID相同，继续判断属性值是否存在，存在包含
                            ProductTypeAttr productTypeAttr = mapProductTypeAttrs.get(attrId + "");
                            if (productTypeAttr != null) {
                                String[] trypeValues = productTypeAttr.getValue().split(",");
                                attrValue = trypeValues[attrValueIndex];
                                attrName = productTypeAttr.getName();
                                //                            attrIdNew = productTypeAttr.getId();
                                if (attrValue != null && value.contains(attrValue.trim())) {
                                    countAttr++;
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }

        /*
        for (ProductAttr productAttr : productAttrs) {
            int productTypeAttrId = productAttr.getProductTypeAttrId().intValue();
            String value = productAttr.getValue();
            for (String string : wherealls) {
                String[] strings = string.split("_");
                if (strings.length == 2) {
                    int attrId = Integer.valueOf(strings[0]).intValue(); //属性ID
                    int attrValueIndex = Integer.valueOf(strings[1]).intValue(); //属性值所属的ID位置
                    productTypeAttrIds.add(attrId);
                    //                    Integer attrIdNew = null;//查询属性ID
                    String attrName = null;//查询属性名称
                    String attrValue = null;//存放属性值
                    if (productTypeAttrId == attrId) { //查询属性ID和商品所有的查询属性ID相同，继续判断属性值是否存在，存在包含
                        ProductTypeAttr productTypeAttr = mapProductTypeAttrs.get(attrId + "");
                        if (productTypeAttr != null) {
                            String[] trypeValues = productTypeAttr.getValue().split(",");
                            attrValue = trypeValues[attrValueIndex];
                            attrName = productTypeAttr.getName();
                            //                            attrIdNew = productTypeAttr.getId();
                        }
                    }

                    if (attrValue != null && value.equals(attrValue.trim())) {
                        countAttr++;
                        productTypeAttrWhereAlls.put(string, attrName + ":" + attrValue);
                    }
                }
            }
        }
        */
        return countAttr;
    }
    
    

    /**
     * 查询分类下面所关联的品牌
     * @param stack
     * @param brandIds
     */
    private void findBrandAll(Map<String, Object> stack, String brandIds) {
        List<ProductBrand> productBrands = productBrandReadDao.getByIds(brandIds);
        stack.put("productBrands", productBrands);
        //Terry 20170323 remark
        /*
        List<String> productBrandNameFirsts = new ArrayList<String>();
        for (ProductBrand productBrand : productBrands) {
            if (!productBrandNameFirsts.contains(productBrand.getNameFirst())) {
                productBrandNameFirsts.add(productBrand.getNameFirst());
            }
        }
        Collections.sort(productBrandNameFirsts);
        stack.put("productBrandNameFirsts", productBrandNameFirsts);
        */
    }

    /**
     * 查询三级分类、二级分类、一级分类
     * @param cateId
     * @param stack
     * @return 三级分类
     */
    private ProductCate findCateAll(Integer cateId, Map<String, Object> stack) {
        ProductCate productCate = productCateReadDao.get(cateId);
        if (productCate == null) {
            throw new BusinessException("传入的类目不正确不能为空");
        }
        stack.put("productCate", productCate);
        if (productCate.getPid().intValue() != 0) {
            ProductCate productCatePid = productCateReadDao.get(productCate.getPid());
            stack.put("productCatePid", productCatePid);

            List<ProductCate> productCate3s = productCateReadDao.getByPid(productCate.getPid());
            productCate3s.remove(productCate);
            stack.put("productCate3s", productCate3s);

            if (productCatePid.getPid() != 0) {
                ProductCate productCatePidPid = productCateReadDao.get(productCatePid.getPid());
                stack.put("productCatePidPid", productCatePidPid);

                List<ProductCate> productCate2s = productCateReadDao.getByPid(productCatePid
                    .getPid());
                productCate2s.remove(productCatePid);
                stack.put("productCate2s", productCate2s);
            }
        }
        return productCate;
    }

    /**
     * 筛选品牌
     * @param mapCondition
     * @param stack
     * @param products 商品
     */
    private void filterBrand(Map<String, Object> mapCondition, Map<String, Object> stack,
                             List<Product> products) {
        Integer brandId = 0;
        try {
            brandId = (Integer) mapCondition.get("brandId");
        } catch (Exception e) {
            brandId = 0;
        }
        if (brandId != null && brandId != 0) {
            ProductBrand productBrand = productBrandReadDao.getById(brandId);
            stack.put("brandName", "品牌：" + productBrand.getName());
            for (Iterator<Product> iterator = products.iterator(); iterator.hasNext();) {
                Product product = iterator.next();
                if (brandId.intValue() != product.getProductBrandId()) {
                    iterator.remove();
                }
            }
        } else {
            brandId = 0;
        }
        stack.put("brandId", brandId);
    }

    /**
     * 根据分类查询列表页推荐的头部商品列表
     * @param cateId
     * @return
     */
    public List<Product> getProductListByCateIdTop(int cateId) {
        return productReadDao.getByCateIdTop(cateId, 4);
    }

    /**
     * 根据分类查询列表页推荐的左侧商品列表
     * @param cateId
     * @return
     */
    public List<Product> getProductListByCateIdLeft(int cateId) {
        return productReadDao.getByCateIdLeft(cateId);
    }

    /**
     * 查询二级分类下三级分类的商品
     * @param cateId
     * @return
     */
    public List<Product> getProductListByCateId2(int cateId) {
        return productReadDao.getByCateIdTop(cateId, 5);
    }

    /**
     * 搜索页面推荐商品信息头部
     * @return
     */
    public List<Product> getProductSearchByTop() {
        return productReadDao.getByCateIdTop(0, 4);
    }

    /**
     * 搜索页面推荐商品信息左部
     * @return
     */
    public List<Product> getProductSearchByLeft() {
        return productReadDao.getByCateIdLeft(0);
    }

    /**
      * 查询商家列表页 商品信息
     * @param start 
     * @param size
     * @param cateId 商家分类
     * @param sellerId 商家ID
     * @param sort 排序
     * @return
     */
    public List<Product> getProductListBySellerCateId(int start, int size, int cateId,
                                                      int sellerId, int sort) {
        StringBuilder sb = new StringBuilder();
        if (cateId != 0) {
            SellerCate sellerCate = sellerCateReadDao.get(cateId);
            if (sellerCate.getPid().intValue() != 0) {
                sb.append(sellerCate.getId());
            } else {
                List<SellerCate> listCate = sellerCateReadDao.getByPid(cateId, sellerId);
                sb.append(sellerCate.getId());
                for (SellerCate sellerCate2 : listCate) {
                    sb.append(",");
                    sb.append(sellerCate2.getId());
                }
            }
        }

        return productReadDao.getProductListBySellerCateId(start, size, sb.toString(), sellerId,
            sort);
    }

    /**
     * 根据商家商品分类统计商家商品
     * @param cateId 商家分类
     * @param sellerId 商家ID
     * @return
     */
    public Integer getProductListBySellerCateIdCount(int cateId, int sellerId) {
        StringBuilder sb = new StringBuilder();

        if (cateId != 0) {
            SellerCate sellerCate = sellerCateReadDao.get(cateId);
            if (sellerCate.getPid().intValue() != 0) {
                sb.append(sellerCate.getId());
            } else {
                List<SellerCate> listCate = sellerCateReadDao.getByPid(cateId, sellerId);
                sb.append(sellerCate.getId());
                for (SellerCate sellerCate2 : listCate) {
                    sb.append(",");
                    sb.append(sellerCate2.getId());
                }
            }
        }

        return productReadDao.getProductListBySellerCateIdCount(sb.toString(), sellerId);
    }

    /**
     * 取得所有显示状态的商品分类
     * @param  queryMap
     * @return
     */
    public List<ProductTypeVO> getProductTypeList(int productTypeId) {

        List<ProductBrand> productBrands = productBrandReadDao.findAll();

        List<ProductTypeVO> reList = new ArrayList<ProductTypeVO>();
        List<ProductTypeVO> productTypeVOs = new ArrayList<ProductTypeVO>();
        ProductTypeVO productTypeVO1 = new ProductTypeVO();
        productTypeVO1.setId(0);
        productTypeVO1.setName("品牌");
        for (ProductBrand productBrand : productBrands) {

            ProductTypeVO productTypeVO = new ProductTypeVO();
            productTypeVO.setId(productBrand.getId());
            productTypeVO.setName(productBrand.getNameFirst() + " " + productBrand.getName());
            productTypeVO.setImgUrl(productBrand.getImage());
            if (!productTypeVOs.contains(productTypeVO)) {
                productTypeVOs.add(productTypeVO);
            }
        }

        productTypeVO1.setChildList(productTypeVOs);
        reList.add(productTypeVO1);

        List<ProductTypeAttr> productTypeAttrs = productTypeAttrReadDao.getByTypeIdAndQuery(productTypeId);
        for (ProductTypeAttr productTypeAttr : productTypeAttrs) {

            productTypeVOs = new ArrayList<ProductTypeVO>();
            productTypeVO1 = new ProductTypeVO();
            productTypeVO1.setId(productTypeId);
            productTypeVO1.setName(productTypeAttr.getName());
            String value = productTypeAttr.getValue();
            if (value != null && !"".equals(value)) {
                String[] str = value.split(",");
                for (int i = 0; i < str.length; i++) {

                    ProductTypeVO productTypeVO = new ProductTypeVO();
                    productTypeVO.setId(productTypeAttr.getId());
                    productTypeVO.setName(str[i]);
                    productTypeVOs.add(productTypeVO);

                }
            }
            productTypeVO1.setChildList(productTypeVOs);
            reList.add(productTypeVO1);
        }

        return reList;
        /*
        
        List<FrontProductCateVO> returnList = new ArrayList<FrontProductCateVO>();

        //状态为1：显示
        queryMap.put("status", ConstantsEJS.PRODUCT_CATE_STATUS_1);

        //1、取第一级分类
        queryMap.put("pid", "0");
        List<ProductCate> beanList = productCateReadDao.queryList(queryMap);
        //取第二级分类
        for (ProductCate bean : beanList) {
            FrontProductCateVO vo = new FrontProductCateVO();
            final BeanCopier copier = BeanCopier.create(ProductCate.class, FrontProductCateVO.class,
                false);
            copier.copy(bean, vo, null);

            //取第二级 
            queryMap.put("pid", bean.getId());
            List<ProductCate> beanListLevel2 = productCateReadDao.queryList(queryMap);
            List<FrontProductCateVO> cateList2 = new ArrayList<FrontProductCateVO>();
            for (ProductCate temp : beanListLevel2) {
                FrontProductCateVO vo2 = new FrontProductCateVO();
                copier.copy(temp, vo2, null);

                //取第三级
                queryMap.put("pid", temp.getId());
                List<ProductCate> beanListLevel3 = productCateReadDao.queryList(queryMap);
                List<FrontProductCateVO> cateList3 = new ArrayList<FrontProductCateVO>();
                for (ProductCate cate : beanListLevel3) {
                    FrontProductCateVO vo3 = new FrontProductCateVO();
                    copier.copy(cate, vo3, null);
                    cateList3.add(vo3);
                }

                vo2.setChilds(cateList3);
                cateList2.add(vo2);
            }

            vo.setChilds(cateList2);
            returnList.add(vo);
        }

        return returnList;
        */
    }
    
    private String[] splitFilterParams(String is_top) {
   	 	String isTopstr = is_top.substring(1, is_top.length());
        return isTopstr.split("-");
        
    }

    public void getProducts(Integer sellerId, Integer isTop) {
        // TODO Auto-generated method stub
        
    }
}
