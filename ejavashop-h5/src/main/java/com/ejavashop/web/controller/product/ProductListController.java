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
import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.product.Product;
import com.ejavashop.entity.product.ProductBrand;
import com.ejavashop.entity.product.ProductCate;
import com.ejavashop.entity.product.ProductTypeAttr;
import com.ejavashop.service.product.IProductCateService;
import com.ejavashop.service.product.IProductFrontService;
import com.ejavashop.vo.product.ProductTypeAttrVO;
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
        ServiceResult<ProductCate> prodCatesResult = productCateService.getProductCateById(ConvertUtil.toInt(cateId, 0));

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
            String urlPath = urlProductList(1, 0, 0, 0, 0, prodCate.getId()) + ".html";
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

            sbUrlPath.append(urlProductList(cateId, page, sort, doSelf, store, 0));

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
                                  int brandId) {
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

        sb.append("-0-0-0");
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
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/0-{visitPath}.html", method = RequestMethod.GET)
    public String productList(@PathVariable String visitPath, HttpServletRequest request,
                              HttpServletResponse response, Map<String, Object> stack) {
        String[] arrVisitPath = visitPath.split("-");
        int arrVisitPathLength = arrVisitPath.length;
        if (arrVisitPathLength < 9) { //长度小于9url错误
            return "redirect:/error.html";
        }
        int cateId = ConvertUtil.toInt(arrVisitPath[0], 0); //分类
        stack.put("cateId", cateId);
        //存放参数
        Map<String, Object> mapCondition = new HashMap<String, Object>();

        int page = ConvertUtil.toInt(arrVisitPath[1], 0);//分页
        
        mapCondition.put("page", page);
        stack.put("pageNumber", page);

        int sort = ConvertUtil.toInt(arrVisitPath[2], 0);//排序
        mapCondition.put("sort", sort);
        stack.put("sort", sort);
        
        int doSelf = ConvertUtil.toInt(arrVisitPath[3], 0);//自营非自营
        mapCondition.put("doSelf", doSelf);
        stack.put("doSelf", doSelf);

        int store = ConvertUtil.toInt(arrVisitPath[4], 0);//有货无货
        mapCondition.put("store", store);
        stack.put("store", store);

        int brandId = ConvertUtil.toInt(arrVisitPath[5], 0);//品牌
        mapCondition.put("brandId", brandId);
        stack.put("brandId", brandId);

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
        StringBuilder sbUrlPathAll = new StringBuilder(urlProductList(cateId, page, sort, doSelf, store, brandId));
        if (sbUrlPath.toString() != null && !"".equals(sbUrlPath.toString())) {
            sbUrlPathAll.append("-");
            sbUrlPathAll.append(sbUrlPath.toString());
        }
        stack.put("urlPath", sbUrlPathAll.toString());
        
//        productFrontService.getProducts(cateId, mapCondition, stack, request.getSession().getServletContext());
       /* ServiceResult<List<ProductTypeVO>> productTypeResult = productFrontService.getProductTypeList(1);
        stack.put("typeList", productTypeResult.getResult());*/
        //Terry 20170304加快页面展示速度，第一次不用加载product list
        ServiceResult<Map<String, Object>> conditionResult = productFrontService.getAllSearchContidtion(cateId, mapCondition, stack);
        if (conditionResult.getSuccess()) {
            Map<String, Object> conditionMap = conditionResult.getResult();
            List<ProductTypeAttr> productTypeAttrs = (List<ProductTypeAttr>) conditionMap.get("productTypeAttrs");
            installProductAttr(stack, productTypeAttrs, listFilters);
        }
        if (brandId != 0) {
            List<ProductBrand> ProductBrands = (List<ProductBrand>) stack.get("productBrands");
            for (ProductBrand productBrand : ProductBrands) {
                if (productBrand.getId().intValue() == brandId) {
                    stack.put("productBrandName", productBrand.getName());
                    break;
                }
            }
        }

        return "h5v1/product/productlist";
    }

    /**
     * 拼装列表页查询条件的VO对象
     * @param stack
     * @param productTypeAttrs
     */
    private void installProductAttr(Map<String, Object> stack, List<ProductTypeAttr> productTypeAttrs, List<String> listFilters) {

        List<ProductTypeAttrVO> productTypeAttrVOs = new ArrayList<ProductTypeAttrVO>();

        for (ProductTypeAttr productTypeAttr : productTypeAttrs) {
            ProductTypeAttrVO productTypeAttrVO = new ProductTypeAttrVO();
            productTypeAttrVO.setId(productTypeAttr.getId());
            productTypeAttrVO.setName(productTypeAttr.getName());
            productTypeAttrVO.setValue(productTypeAttr.getValue());

            if (listFilters != null && listFilters.size() > 0) {
                for (int i = 0; i < listFilters.size(); i++) {
                    String whereAll_ = listFilters.get(i);
                    String[] whereAll_s = whereAll_.split("_");
                    if (whereAll_s.length == 2) {
                        int whereAll_0 = ConvertUtil.toInt(whereAll_s[0], 0);
                        int whereAll_1 = ConvertUtil.toInt(whereAll_s[1], 0);
                        if (productTypeAttr.getId().intValue() == whereAll_0) {
                            productTypeAttrVO.setIsChoice(1);//1表示此分类依据被选中
                            productTypeAttrVO.setIsChoiceIndex(whereAll_1); //选中的索引
                            productTypeAttrVO.setChoiceAll(whereAll_); //应该去掉的条件
                            break;
                        }
                    }
                }
            }

            String value = productTypeAttr.getValue();
            if (value != null && !"".equals(value)) {
                List<String> values = new ArrayList<String>();
                String[] str = value.split(",");
                for (int i = 0; i < str.length; i++) {
                    values.add(str[i]);
                }
                productTypeAttrVO.setValues(values);
            }
            productTypeAttrVOs.add(productTypeAttrVO);
        }
        stack.put("productTypeAttrVOsAll", productTypeAttrVOs);
    }

    /**
     * 返回商品列表页 json 数据
     * @param cateId
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "/listjson-{visitPath}.html", method = RequestMethod.GET)
    public @ResponseBody HttpJsonResult<List<Product>> cateTop(@PathVariable String visitPath,
                                                               HttpServletRequest request,
                                                               HttpServletResponse response,
                                                               Map<String, Object> stack) {
        HttpJsonResult<List<Product>> jsonResult = new HttpJsonResult<List<Product>>();

        String[] arrVisitPath = visitPath.split("-");
        int arrVisitPathLength = arrVisitPath.length;
        if (arrVisitPathLength < 9) { //长度小于9url错误
            return jsonResult;
        }
        int cateId = ConvertUtil.toInt(arrVisitPath[0], 0); //分类
        //存放参数
        Map<String, Object> mapCondition = new HashMap<String, Object>();

        int page = ConvertUtil.toInt(arrVisitPath[1], 0);//分页
        mapCondition.put("page", page);

        int sort = ConvertUtil.toInt(arrVisitPath[2], 0);//排序
        mapCondition.put("sort", sort);

        int doSelf = ConvertUtil.toInt(arrVisitPath[3], 0);//自营非自营
        mapCondition.put("doSelf", doSelf);

        int store = ConvertUtil.toInt(arrVisitPath[4], 0);//有货无货
        mapCondition.put("store", store);

        int brandId = ConvertUtil.toInt(arrVisitPath[5], 0);//品牌
        mapCondition.put("brandId", brandId);

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
        stack.put("pagesize", ConstantsEJS.DEFAULT_PAGE_SIZE_10);

        productFrontService.getProducts(cateId, mapCondition, stack, request.getSession().getServletContext());
        @SuppressWarnings("unchecked")
        List<Product> listProduct = (List<Product>) stack.get("producListVOs");
        
        jsonResult.setTotal(Integer.parseInt(stack.get("isEndPage").toString()));           //isEndPage=1 没有下一页，isEndPage=0还有下一页，isEndPage=2栏目下没有数据
        jsonResult.setData(listProduct);
        return jsonResult;
    }
}
