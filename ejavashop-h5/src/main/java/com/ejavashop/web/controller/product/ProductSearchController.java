package com.ejavashop.web.controller.product;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.ConvertUtil;
import com.ejavashop.core.HttpJsonResult;
import com.ejavashop.core.PaginationUtil;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.StringUtil;
import com.ejavashop.core.WebUtil;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.core.freemarkerutil.DomainUrlUtil;
import com.ejavashop.entity.product.Product;
import com.ejavashop.entity.search.SearchSetting;
import com.ejavashop.service.product.IProductFrontService;
import com.ejavashop.service.product.IProductService;
import com.ejavashop.service.search.ISearchSettingService;
import com.ejavashop.vo.search.SearchProductVO;
import com.ejavashop.web.controller.BaseController;

/**
 * 搜索商品
 *                       
 * @Filename: ProductSearchController.java
 * @Version: 1.0
 * @Author: 王朋
 * @Email: wpjava@163.com
 *
 */
@Controller
public class ProductSearchController extends BaseController {

    @Resource
    private IProductFrontService  productFrontService;
    @Resource
    private IProductService       productService;
    @Resource
    private ISearchSettingService searchSettingService;
    
    private static String regEx = "[\u4e00-\u9fa5]";
    private static Pattern pat = Pattern.compile(regEx);
    
    public static SolrClient getSolrClient() {
        String solrUrl = DomainUrlUtil.getEJS_SOLR_URL();
        String solrServer = DomainUrlUtil.getEJS_SOLR_SERVER();
        return new HttpSolrClient(solrUrl + "/" + solrServer);
    }

    @RequestMapping(value = "/search.html", method = RequestMethod.GET)
    public String search(HttpServletRequest request, String keyword,String bysort, Map<String, Object> stack) {
        keyword = StringUtil.trim(StringUtil.nullSafeString(keyword));
        if ("".equals(keyword)) {
            List<String> keywords = getKeywords();
            if (keywords.size() > 0) {
                keyword = keywords.get(0);
            } else {
                keyword = "麦琪";
            }
        }
        int sort = request.getParameter("bysort") == null ? 0 : Integer.valueOf(request.getParameter("bysort"));
      //判定如果搜索关键字中不存在中文，则通过数据库搜索商品，否则走solr搜索
        if(!isContainsChinese(keyword)){
       // if (true) {
        	keyword = keyword.toUpperCase();//全部转化为大写字母
        	ServiceResult<SearchSetting> serviceResult = searchSettingService.getSearchSettingById(ConstantsEJS.SEARCHSETTINGID);
        	if (!serviceResult.getSuccess()) {
        		if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
        			throw new RuntimeException(serviceResult.getMessage());
        		} else {
        			throw new BusinessException(serviceResult.getMessage());
        		}
        	}
        	
        	//关键词过滤
        	SearchSetting searchSetting = serviceResult.getResult();
        	if (searchSetting.getKeywordFilter().intValue() == SearchSetting.KEYWORD_FILTER_2) {
        		ServiceResult<Integer> serviceResultKeyword = searchSettingService
        				.getSearchKeywordsByKeyword(keyword);
        		int countKeyword = serviceResultKeyword.getResult();
        		if (countKeyword > 0) {
        			return "redirect:/error.html";
        		}
        	}
        	int count = 0;
        	Integer pageCount = 0;
        	
        	PaginationUtil page = WebUtil.handlerPaginationUtil(request);
        	int start = (page.getNum() - 1) * ConstantsEJS.DEFAULT_PAGE_SIZE;
        	int size = ConstantsEJS.DEFAULT_PAGE_SIZE;
        	
        	String searchKeyword =keyword;
        	List<Product> products = new ArrayList<Product>();
        	try {
        	    List<Product> product = null;
        	    pageCount = productService.getProductByProductPageCountCode(searchKeyword).getResult();
        	    if(sort == 0){
                    product = productService.getProductByProductCode(searchKeyword,start,size).getResult();
        	    }else{//升序
                    product = productService.getProductByProductCodeBySort(searchKeyword,start,size,sort).getResult();
        	    }
    			if (product.size() > 0) {
    				count = new Integer(product.size());
    				for (Product product2 : product) {
    					String keyName = product2.getName1().replace(searchKeyword,"<font color=\"red\">"+searchKeyword+"</font>");
    					product2.setName1(keyName);
    					products.add(product2);
					}
    			}
        	}catch(Exception e){
        		e.printStackTrace();
        	}
        	page.createPagination(pageCount);
        	stack.put("pagesize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        	stack.put("keywordNumber", 1);
        	stack.put("count", count);
        	stack.put("keyword", keyword);
        	stack.put("producListVOs", products);
        	stack.put("sort", sort);
        	return "h5v1/product/productsearch";
        }else{
        	keyword = StringUtil.stringFilterSpecial(keyword);
        	
        	ServiceResult<SearchSetting> serviceResult = searchSettingService
        			.getSearchSettingById(ConstantsEJS.SEARCHSETTINGID);
        	if (!serviceResult.getSuccess()) {
        		if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
        			throw new RuntimeException(serviceResult.getMessage());
        		} else {
        			throw new BusinessException(serviceResult.getMessage());
        		}
        	}
        	
        	//关键词过滤
        	SearchSetting searchSetting = serviceResult.getResult();
        	if (searchSetting.getKeywordFilter().intValue() == SearchSetting.KEYWORD_FILTER_2) {
        		ServiceResult<Integer> serviceResultKeyword = searchSettingService
        				.getSearchKeywordsByKeyword(keyword);
        		int countKeyword = serviceResultKeyword.getResult();
        		if (countKeyword > 0) {
        			return "redirect:/error.html";
        		}
        	}
        	
        	String searchKeyword = "(" + keyword + ")";
        	int count = 0;
        	SolrClient client = getSolrClient();
        	SolrQuery query = new SolrQuery();
        	
        	int start = 0, size = ConstantsEJS.DEFAULT_PAGE_SIZE;
        	
        	query.setQuery(queryKeyWord(searchKeyword));
        	
        	query.set("start", start);
        	query.set("rows", size);
        	
        	if (sort == 3) {
        		query.addSort(SearchProductVO.MALMOBILEPRICE_, ORDER.asc);
        	} else if (sort == 4) {
        		query.addSort(SearchProductVO.MALMOBILEPRICE_, ORDER.desc);
        	} else if (sort == 5) {
        		query.addSort(SearchProductVO.PRODUCTSTOCK_, ORDER.asc);
        	} else if (sort == 6) {
        		query.addSort(SearchProductVO.PRODUCTSTOCK_, ORDER.desc);
        	} else {
        		query.set("defType", "edismax");
            	query.set("qf", "name1^100 content^0.8 brand^10 keyword^50");
            	query.set("pf", "name1 brand content keyword");
            	String sortModel = "sum(product(if(exists(query({!v='name1:" + keyword + "'})),100000,1),max(id,0)),product(if(exists(query({!v='keyword:" + keyword + "'})),10000,0),max(id,0)),product(if(exists(query({!v='brand:" + keyword + "'})),1000,0),max(id,0)),product(if(exists(query({!v='content:" + keyword + "'})),100,0),max(id,0)))";
           	    query.set("bf",sortModel);
        	}
        	
            //query.set("sort", SearchProductVO.ID_ + " desc, " + SearchProductVO.SORT_ + " desc");
        	
        	query.setHighlight(true);
        	query.setHighlightSimplePre("<font color=\"red\">");
        	query.setHighlightSimplePost("</font>");
        	query.setParam("hl.fl", SearchProductVO.NAME1_);
        	
        	List<Product> products = new ArrayList<Product>();
        	QueryResponse response = null;
        	try {
        		response = client.query(query);
        		SolrDocumentList docs = response.getResults();
        		count = new Integer(docs.getNumFound() + "");
        		for (SolrDocument doc : docs) {
        			Integer id = (Integer) doc.getFieldValue(SearchProductVO.ID_);

                    Product product = productService.getProductById(id).getResult();
                    if (product != null) {
                        Object object = response.getHighlighting().get(""+id).get(SearchProductVO.NAME1_);
                        if (object != null) {
                            product.setName1(object.toString().replace("[", "").replace("]", ""));
                        }
                        products.add(product);
                        
                    }
//        			Product product = queryResult(response, doc, id);
//        			products.add(product);
        		}
        	} catch (SolrServerException e) {
        		e.printStackTrace();
        	} catch (IOException e) {
        		e.printStackTrace();
        	}
        	
        	stack.put("pagesize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        	stack.put("keywordNumber", 1);
        	stack.put("count", count);
        	stack.put("keyword", keyword);
        	stack.put("producListVOs", products);
        	stack.put("sort", sort);
        	return "h5v1/product/productsearch";
        }
        
    }

    private Product queryResult(QueryResponse response, SolrDocument doc, String id) {
        Product product = new Product();
        product.setId(new Integer(id));

        Object object = response.getHighlighting().get(id).get(SearchProductVO.NAME1_);
        if (object != null) {
            product.setName1(object.toString().replace("[", "").replace("]", ""));
        } else {
            product.setName1(doc.getFieldValue(SearchProductVO.NAME1_).toString());
        }
        //                product.setName1(response.getHighlighting().get(id).get(SearchProductVO.NAME1_)
        //                    .toString().replace("[", "").replace("]", ""));

        product.setMasterImg(doc.getFieldValue(SearchProductVO.MASTERIMG_).toString());
        product.setMallPcPrice(new BigDecimal(doc.getFieldValue(SearchProductVO.MALLPCPRICE_)
            .toString()));
        product.setMalMobilePrice(new BigDecimal(doc.getFieldValue(SearchProductVO.MALMOBILEPRICE_)
            .toString()));
        product.setProductStock(new Integer(doc.getFieldValue(SearchProductVO.PRODUCTSTOCK_)
            .toString()));
        product.setCommentsNumber(new Integer(doc.getFieldValue(SearchProductVO.COMMENTSNUMBER_)
            .toString()));
        product.setSellerId(new Integer(doc.getFieldValue(SearchProductVO.SELLERID_).toString()));
        return product;
    }

    /**
     * 拼装查询条件
     * @param searchKeyword
     * @return
     */
    private String queryKeyWord(String searchKeyword) {
        StringBuilder sb = new StringBuilder();
        sb.append(SearchProductVO.CONTENT_);
        sb.append(":");
        sb.append(searchKeyword);
        sb.append(" OR ");
        sb.append(SearchProductVO.NAME1_);
        sb.append(":");
        sb.append(searchKeyword);
        sb.append(" OR ");
        sb.append(SearchProductVO.SELLER_);
        sb.append(":");
        sb.append(searchKeyword);
        sb.append(" OR ");
        sb.append(SearchProductVO.BRAND_);
        sb.append(":");
        sb.append(searchKeyword);
        sb.append(" OR ");
        sb.append(SearchProductVO.SELLER_);
        sb.append(":");
        sb.append(searchKeyword);
        sb.append(" OR ");
        sb.append(SearchProductVO.CATE_);
        sb.append(":");
        sb.append(searchKeyword);
        return sb.toString();
    }

    /**
     * 返回json结果
     * @param request
     * @param stack
     * @return
     */
    @RequestMapping(value = "/searchJson.html", method = RequestMethod.GET)
    public @ResponseBody HttpJsonResult<List<Product>> searchJson(HttpServletRequest request,
                                                                  Map<String, Object> stack) {
        HttpJsonResult<List<Product>> jsonResult = new HttpJsonResult<List<Product>>();
        String keyword = request.getParameter("keyword");
        String pageNumStr = request.getParameter("pageNum");
        Integer sort = Integer.valueOf(request.getParameter("sort"));
        int pageNum = ConvertUtil.toInt(pageNumStr, 1);

        ServiceResult<SearchSetting> serviceResult = searchSettingService
            .getSearchSettingById(ConstantsEJS.SEARCHSETTINGID);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }

        //关键词过滤
        SearchSetting searchSetting = serviceResult.getResult();
        if (searchSetting.getKeywordFilter().intValue() == SearchSetting.KEYWORD_FILTER_2) {
            ServiceResult<Integer> serviceResultKeyword = searchSettingService
                .getSearchKeywordsByKeyword(keyword);
            int countKeyword = serviceResultKeyword.getResult();
            if (countKeyword > 0) {
                return jsonResult;
            }
        }
        int start = 0, size = 0;
        start = (pageNum - 1) * ConstantsEJS.DEFAULT_PAGE_SIZE;
        size = ConstantsEJS.DEFAULT_PAGE_SIZE;
        int count = 0;
        Integer pageCount = 0;
        List<Product> products = new ArrayList<Product>();
        
        try {
            if(true){
                List<Product> product = null;
                pageCount = productService.getProductByProductPageCountCode(keyword).getResult();
                if(sort == 0){
                    product = productService.getProductByProductCode(keyword,start,size).getResult();
                }else{//升序
                    product = productService.getProductByProductCodeBySort(keyword,start,size,sort).getResult();
                }
                if (product.size() > 0) {
                    count = new Integer(product.size());
                    for (Product product2 : product) {
                        String keyName = product2.getName1().replace(keyword,"<font color=\"red\">"+keyword+"</font>");
                        product2.setName1(keyName);
                        products.add(product2);
                    }
                }
                
            }else{
                String searchKeyword = "(" + keyword + ")";
                SolrClient client = getSolrClient();
                SolrQuery query = new SolrQuery();

                query.setQuery(queryKeyWord(searchKeyword));

                query.set("start", start);
                query.set("rows", size);
                query.set("sort", SearchProductVO.ID_ + " desc, " + SearchProductVO.SORT_ + " desc");

                query.setHighlight(true);
                query.setHighlightSimplePre("<font color=\"red\">");
                query.setHighlightSimplePost("</font>");
                query.setParam("hl.fl", SearchProductVO.NAME1_);
                QueryResponse response = null;
                response = client.query(query);
                SolrDocumentList docs = response.getResults();
                count = new Integer(docs.getNumFound() + "");
                for (SolrDocument doc : docs) {
                    String id = (String) doc.getFieldValue(SearchProductVO.ID_);
                    
                    Product product = productService.getProductById(Integer.valueOf(id)).getResult();
                    if (product != null) {
                        Object object = response.getHighlighting().get(id).get(SearchProductVO.NAME1_);
                        if (object != null) {
                            product.setName1(object.toString().replace("[", "").replace("]", ""));
                        }
                        products.add(product);
                        
                    }
    //                Product product = queryResult(response, doc, id);
    //                products.add(product);
                }
                }
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        jsonResult.setRows(products);
        jsonResult.setTotal(products.size());
        return jsonResult;
    }
    /**
     * 判断字符串中是否有中文【仝照美】
     * @param str
     * @return
     */
    public static boolean isContainsChinese(String str){
    	Matcher matcher = pat.matcher(str);
    	boolean flag = false;
    	if(matcher.find()){
    		flag = true;
    	}
    	return flag;
    }
    /**
     * 调整到搜索页面
     * @param request
     * @param keyword
     * @param stack
     * @return
     */
    @RequestMapping(value = "/search-index.html", method = RequestMethod.GET)
    public String searchIndex(HttpServletRequest request, Map<String, Object> stack) {
        List<String> keywords = getKeywords();
        stack.put("keywords", keywords);
        stack.put("keywordNumber", 0);
        stack.put("pagesize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        //默认排序类型
        stack.put("sort", 0);
        return "h5v1/product/productsearch";
    }

    /**
     * 获取设置的查询关键字
     * @return
     */
    private List<String> getKeywords() {
        ServiceResult<SearchSetting> serviceResult = searchSettingService
            .getSearchSettingById(ConstantsEJS.SEARCHSETTINGID);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }
        SearchSetting searchSetting = serviceResult.getResult();
        String keyword = searchSetting.getKeyword();

        keyword = StringUtil.trim(StringUtil.nullSafeString(keyword));

        List<String> keywords = new ArrayList<String>();
        if (!"".equals(keyword)) {
            String[] strings = keyword.split(",");
            for (String string : strings) {
                string = StringUtil.trim(StringUtil.nullSafeString(string));
                if (!"".equals(string)) {
                    keywords.add(string);
                }
            }
        }
        return keywords;
    }

}
