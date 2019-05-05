package com.ejavashop.web.controller.product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.ConvertUtil;
import com.ejavashop.core.HttpJsonResult;
import com.ejavashop.core.PaginationFrontUtil;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.product.Product;
import com.ejavashop.entity.product.ProductBrand;
import com.ejavashop.entity.product.ProductCate;
import com.ejavashop.entity.product.ProductPrice;
import com.ejavashop.entity.product.ProductTypeAttr;
import com.ejavashop.entity.shopm.pcindex.PcTitleKeywordsDescription;
import com.ejavashop.service.pcindex.IPcTitleKeywordsDescriptionService;
import com.ejavashop.service.product.IProductBrandService;
import com.ejavashop.service.product.IProductCateService;
import com.ejavashop.service.product.IProductFrontService;
import com.ejavashop.service.product.IProductPriceService;
import com.ejavashop.service.product.IProductTypeAttrService;
import com.ejavashop.web.controller.BaseController;

/**
 * 商品列表页
 *                       
 * @Filename: ProductListController.java
 * @Version: 1.0
 * @Author: 王朋
 * @Email: wpjava@163.com
 *
 */
@Controller
public class ProductListController extends BaseController {

    @Resource
    private IProductFrontService productFrontService;

    @Resource
    private IProductCateService  productCateService;
    @Resource
    private IProductPriceService productPriceService;
    @Resource
    private IPcTitleKeywordsDescriptionService pcTitleKeywordsDescriptinService;
    @Resource
    private IProductTypeAttrService productTypeAttrService;
    @Resource
    private IProductBrandService    productBrandService;

    /**
     * 列表页第一次跳转，验证cate并组织URL，有利于百度SEO
     * @param request
     * @param response
     * @param cateId 分类ID
     * @return
     */
    @RequestMapping(value = "/cate/{cateId}.html", method = RequestMethod.GET)
    public ModelAndView cate(HttpServletRequest request, HttpServletResponse response,
                             @PathVariable String cateId) {
        ServiceResult<ProductCate> prodCatesResult = productCateService
            .getProductCateById(ConvertUtil.toInt(cateId, 0));

        if (prodCatesResult.getSuccess()) {
            ProductCate prodCate = prodCatesResult.getResult();
            if (prodCate == null) {
                if (log.isInfoEnabled()) {
                    log.info("[ProductListController]:获取分类时，分类不存在或已删除，已跳转至错误页面。当前页面访问路径："
                             + request.getRequestURL() + "?" + request.getQueryString()
                             + ";页面referer:" + request.getHeader("referer"));
                }
                return new ModelAndView("redirect:/error.html");
            }
            String urlPath = urlProductList(prodCate.getId(), 0, 0, 0, 0, 0,0,10000,0,0,1000000000) + ".html";
            return new ModelAndView("redirect:/" + urlPath);
        } else {
            log.error("[ProductListController]:根据分类ID获取分类时Called Service info:ProductCateService.getProductCateById()");
            return new ModelAndView("redirect:/error.html");
        }
    }

    /**
     * 列表页跳转，去除与更换查询条件，然后跳转到列表页
     * @param request
     * @param response
     * @param urlPath
     * @param filter
     * @param brandId
     * @return
     */
    @RequestMapping(value = "/cate.html", method = RequestMethod.GET)
    public ModelAndView cate(HttpServletRequest request, HttpServletResponse response,
                             String urlPath, String filter, String brandIdFilder, String sortFilter) {
        StringBuilder sbUrlPath = new StringBuilder();
        if (filter != null && !"".equals(filter)) {
            sbUrlPath.append(urlPath.replace(filter, ""));
            sbUrlPath.append(".html");
        } else if (brandIdFilder != null && !"".equals(brandIdFilder)) {
            String[] arrVisitPath = urlPath.split("-");
            int arrVisitPathLength = arrVisitPath.length;
            if (arrVisitPathLength < 9) { //长度小于9url错误
                return new ModelAndView("redirect:/error.html");
            }
            int cateId = ConvertUtil.toInt(arrVisitPath[1], 0); //分类
            int page = ConvertUtil.toInt(arrVisitPath[2], 1);//分页
            int sort = ConvertUtil.toInt(arrVisitPath[3], 0);//排序
            int doSelf = ConvertUtil.toInt(arrVisitPath[4], 0);//自营非自营
            int store = ConvertUtil.toInt(arrVisitPath[5], 0);//有货无货
            //            int brandIdStr = ConvertUtil.toInt(arrVisitPath[6], 0);//品牌

            sbUrlPath.append(urlProductList(cateId, page, sort, doSelf, store, 0,0,10000,0,0,1000000000));

            for (int i = 9; i < arrVisitPath.length; i++) {
                sbUrlPath.append("-");
                sbUrlPath.append(arrVisitPath[i]);
            }
            sbUrlPath.append(".html");
        } else {
            sbUrlPath.append("error.html");
        }
        return new ModelAndView("redirect:/" + sbUrlPath.toString());
    }

    /**
     * 拼装列表页访问的URL
     * @param cateId 类别
     * @param number 分页
     * @param sort 排序
     * @param doSelf 自营非自营
     * @param store 有货无货
     * @param brandId 品牌
     * @return
     */
    private String urlProductList(int cateId, int number, int sort, int doSelf, int store,
                                  int brandId,double startPrice,double endPrice,int stock,int startStock,int endStock) {
        StringBuilder sb = new StringBuilder();
        sb.append("0-");
        sb.append(cateId);

        sb.append("-");
        sb.append(number);

        sb.append("-");
        sb.append(sort);

        sb.append("-");
        sb.append(doSelf);

        sb.append("-");
        sb.append(store);

        sb.append("-");
        sb.append(brandId);
        //sb.append("-0-0-0");
        //第七位放置库存排序
        sb.append("-");
        sb.append(stock);
        //第8 位放价格下区间_上区间  默认为0  10000
        sb.append("-");
        sb.append(startPrice);
        sb.append("_");
        sb.append(endPrice);
        //第9位 放库存区间 
        sb.append("-");
        sb.append(startStock);
        sb.append("_");
        sb.append(endStock);
        return sb.toString();
    }

    /**
     * 列表页访问主函数，为了SEO，URL为数字的拼装
     * 完整URL规则www.ejavashop.com/0-cate-分页-排序-自营非自营－有货无货-品牌ID-0-0-0-
     *                 filter1-filter2....html<br/>
     *                 排序sort 0:默认排序；1销量；2评论；3价格从低到高；4、价格从高到低<br/>
     *                 自营非自营：0所有商品；1自营商品<br/>
     *                 有货无货：0所有商品；1有货商品
     * @param visitPath
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "/0-{visitPath}.html", method = RequestMethod.GET)
    public String productList(@PathVariable String visitPath, HttpServletRequest request,
                              HttpServletResponse response, Map<String, Object> stack) {
        String[] arrVisitPath = visitPath.split("-");
        int arrVisitPathLength = arrVisitPath.length;
        if (arrVisitPathLength < 9) { //长度小于9url错误
            return "redirect:/error.html";
        }
        String path = "";
        int cateId = ConvertUtil.toInt(arrVisitPath[0], 0); //分类
        stack.put("cateId", cateId);
        path += cateId + "-";
        //存放参数
        Map<String, Object> mapCondition = new HashMap<String, Object>();

        int page = ConvertUtil.toInt(arrVisitPath[1], 1);//分页
        mapCondition.put("page", page);
        path += page + "-";

        int sort = ConvertUtil.toInt(arrVisitPath[2], 0);//排序
        mapCondition.put("sort", sort);
        path += sort + "-";

        int doSelf = ConvertUtil.toInt(arrVisitPath[3], 0);//自营非自营
        mapCondition.put("doSelf", doSelf);
        stack.put("doSelf", doSelf);
        path += doSelf + "-";

        int store = ConvertUtil.toInt(arrVisitPath[4], 0);//有货无货
        mapCondition.put("store", store);
        stack.put("store", store);
        path += store + "-";

        int brandId = ConvertUtil.toInt(arrVisitPath[5], 0);//品牌
        mapCondition.put("brandId", brandId);
        stack.put("brandId", brandId);
        path += brandId + "-";
        
        int stock = ConvertUtil.toInt(arrVisitPath[6], 0);//库存排序
        mapCondition.put("stock", stock);
        stack.put("stock", stock);
        path += stock + "-";
        //价格区间
        String priceArr[] = arrVisitPath[7].split("_");
        path += arrVisitPath[7] + "-";
        //库存区间
        String stockArr[] = arrVisitPath[8].split("_");
        path += arrVisitPath[8] + "-";
        double startPrice = 0.00;
        double endPrice = 10000.00;
        if(priceArr.length == 2){
        	startPrice = ConvertUtil.toDouble(priceArr[0], startPrice);//价格下区间
        	endPrice = ConvertUtil.toDouble(priceArr[1], endPrice);//价格上区间
        }
        mapCondition.put("startPrice", startPrice);
        stack.put("startPrice", startPrice);
         
        mapCondition.put("endPrice", endPrice);
        stack.put("endPrice", endPrice);
        
        int startStock = 0;
        int endStock = 1000000000;
        
        if(stockArr.length == 2){
	        startStock = ConvertUtil.toInt(stockArr[0], startStock);//库存下区间
	        endStock = ConvertUtil.toInt(stockArr[1], endStock);//库存上区间
        }
        mapCondition.put("startStock", startStock);
        stack.put("startStock", startStock);
        
        
        mapCondition.put("endStock", endStock);
        stack.put("endStock", endStock);

        List<String> listFilters = new ArrayList<String>();
        for (int i = 9; i < arrVisitPath.length; i++) {
            listFilters.add(arrVisitPath[i]);
        }

        StringBuilder sbUrlPath = new StringBuilder("");
        int listFiltersCount = listFilters.size();
        for (int i = 0; i < listFiltersCount; i++) {
            sbUrlPath.append(listFilters.get(i));
            if (listFiltersCount != (i + 1)) {
                sbUrlPath.append("-");
            }
        }
        mapCondition.put("filterAll", sbUrlPath.toString());
        StringBuilder sbUrlPathAll = new StringBuilder(urlProductList(cateId, page, sort, doSelf,
            store, brandId,startPrice,endPrice,stock,startStock,endStock));
        if (sbUrlPath.toString() != null && !"".equals(sbUrlPath.toString())) {
            sbUrlPathAll.append("-");
            sbUrlPathAll.append(sbUrlPath.toString());
        }

        stack.put("urlPath", sbUrlPathAll.toString());
        productFrontService.getProducts(cateId, mapCondition, stack, request.getSession().getServletContext());

        //分页对象
        PaginationFrontUtil pm = new PaginationFrontUtil(
        		(Integer)mapCondition.get("page") == 0 ? 1 : (Integer) mapCondition.get("page"),
        					ConstantsEJS.DEFAULT_PAGE_SIZE, (Integer) stack.get("productSize"));
        stack.put("page", pm);
        
        //不同的页面初始化不同的SEO三要素(title,keywords,description);
        //add by Ls 2017/04/14 ------------------begin-----------------------
        PcTitleKeywordsDescription entity = new PcTitleKeywordsDescription();
        String title = PcTitleKeywordsDescription.TITLE_DEFAULT;
        String keywords = PcTitleKeywordsDescription.KEYWORDS_DEFAULT;
        String description = PcTitleKeywordsDescription.DESCRIPTION_DEFAULT;
        String attr = "";
        Integer attrId = 151;
        Integer attrValueId = 0;
        ServiceResult<PcTitleKeywordsDescription> serviceResult = new ServiceResult<PcTitleKeywordsDescription>();
        if(arrVisitPath.length>9){
            //品牌袜下的品类筛选
            if(arrVisitPath.length > 10 || brandId > 0){
                if(brandId > 0){
                    ServiceResult<ProductBrand> productBrandResult = productBrandService.getById(brandId);
                    ProductBrand productBrand = productBrandResult.getResult();
                    title = "袜子品牌:" + productBrand.getName();
                }else{
                    title = "";
                }
                if(visitPath.contains("-xppw") && visitPath.endsWith("-xppw")){
                    
                }else{
                    serviceResult = pcTitleKeywordsDescriptinService.getPcTitleKeywordsDescriptionById(15);
                    if(serviceResult.getSuccess() && serviceResult.getResult() != null){
                        entity = serviceResult.getResult();
                        keywords = entity.getKeywords();
                        description = entity.getDescription();
                    }
                    for(int i = 9 ; i < arrVisitPath.length ; i++){
                        attr = arrVisitPath[9];
                        attrId = Integer.valueOf(attr.substring(0, 3));
                        attrValueId = Integer.valueOf(attr.substring(4));
                        ServiceResult<ProductTypeAttr> productTypeAttrResult = productTypeAttrService.getProductTypeAttrById(attrId);
                        if(productTypeAttrResult.getSuccess() && productTypeAttrResult.getResult() != null){
                            ProductTypeAttr productAttr = productTypeAttrResult.getResult();
                            String[] attrArr = productAttr.getValue().split(",");
                            title = title + "_" + productAttr.getName() + ":" + attrArr[attrValueId];
                        }
                    }
                }
//                }else{
//                    serviceResult = pcTitleKeywordsDescriptinService.getPcTitleKeywordsDescriptionById(15);
//                    if(serviceResult.getSuccess() && serviceResult.getResult() != null){
//                        entity = serviceResult.getResult();
//                        title = entity.getTitle();
//                        keywords = entity.getKeywords();
//                        description = entity.getDescription();
//                    }
//                }
            }else{
                attr = arrVisitPath[9];
                //增加异常捕获，仅仅为了为没有配置的页面显示默认值
                try {
                    if(attr.equals("xppw")){
                        path += attr;
                        serviceResult = pcTitleKeywordsDescriptinService.getByPath(path);
                    }else{
                        attrId = Integer.valueOf(attr.substring(0, 3));
                        attrValueId = Integer.valueOf(attr.substring(4));
                        path += attrId;
                        serviceResult = pcTitleKeywordsDescriptinService.getByPath(path);
                    }
                    if(serviceResult.getSuccess() && serviceResult.getResult() != null){
                        entity = serviceResult.getResult();
                        title = entity.getTitle();
                        keywords = entity.getKeywords();
                        description = entity.getDescription();
                        ServiceResult<ProductTypeAttr> productTypeAttrResult = productTypeAttrService.getProductTypeAttrById(attrId);
                        if(productTypeAttrResult.getSuccess() && productTypeAttrResult.getResult() != null){
                            ProductTypeAttr productAttr = productTypeAttrResult.getResult();
                            String[] attrArr = productAttr.getValue().split(",");
                            title = title.replace("/*/", attrArr[attrValueId]);
                        }
                      }
                } catch (Exception e) {
                }
            }
        }else{
            serviceResult = pcTitleKeywordsDescriptinService.getPcTitleKeywordsDescriptionById(6);
            if(serviceResult.getSuccess() && serviceResult.getResult() != null){
                entity = serviceResult.getResult();
                ServiceResult<ProductBrand> productBrandResult = productBrandService.getById(brandId);
                ProductBrand productBrand = productBrandResult.getResult();
                if (productBrand != null)
                    title = entity.getTitle().replace("/*/", productBrand.getName());
                keywords = entity.getKeywords();
                description = entity.getDescription();
            }
        }
        
        stack.put("title", title);
        stack.put("keywords", keywords);
        stack.put("description", description);
        //---------------------------------------end-----------------------------------------
        return "front/product/productlist";
    }

    /**
     * 根据分类查询列表页推荐的头部商品
     * @param cateId
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    /*@RequestMapping(value = "cateTop.html", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<Product>> cateTop(int cateId,
                                                               HttpServletRequest request,
                                                               HttpServletResponse response,
                                                               Map<String, Object> dataMap) {
        HttpJsonResult<List<Product>> jsonResult = new HttpJsonResult<List<Product>>();

        ServiceResult<List<Product>> serviceResult = productFrontService.getProductListByCateIdTop(cateId);

        List<Product> productsTop = new ArrayList<Product>();
        List<Product> productsTop2 = new ArrayList<Product>();
        if (serviceResult.getSuccess()) {
            productsTop2 = serviceResult.getResult();
            //分装阶梯价传到前台进行展示，借用product表description字段进行传值
            ProductPrice p_Price = new ProductPrice();
            for (Product product : productsTop2) {
                if (product.getPriceStatus() != null && product.getPriceStatus() == 2) {
                    p_Price = productPriceService.getProductPriceById(product.getPricePid()).getResult();
                    if (p_Price != null)
                        product.setDescription(p_Price.getPrice3() + "-" + p_Price.getPrice1());
                    else 
                        product.setDescription(product.getMallPcPrice().toString());
                }
                productsTop.add(product);
            }
        }
        jsonResult.setData(productsTop);
        jsonResult.setTotal(productsTop.size());
        return jsonResult;
    }*/

    /**
     * 根据分类查询列表页推荐的左边的商品
     * @param cateId
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "cateLeft.html", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<Product>> cateLeft(int cateId,
                                                                HttpServletRequest request,
                                                                HttpServletResponse response,
                                                                Map<String, Object> dataMap) {
        HttpJsonResult<List<Product>> jsonResult = new HttpJsonResult<List<Product>>();

        ServiceResult<List<Product>> serviceResult = productFrontService
            .getProductListByCateIdLeft(cateId);

        List<Product> productsTop = new ArrayList<Product>();
        if (serviceResult.getSuccess()) {
            productsTop = serviceResult.getResult();
        }

        jsonResult.setData(productsTop);
        jsonResult.setTotal(productsTop.size());
        return jsonResult;
    }

}
