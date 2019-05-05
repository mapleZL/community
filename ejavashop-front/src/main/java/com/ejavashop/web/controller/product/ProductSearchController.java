package com.ejavashop.web.controller.product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.HttpJsonResult;
import com.ejavashop.core.PaginationUtil;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.StringUtil;
import com.ejavashop.core.WebUtil;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.core.freemarkerutil.DomainUrlUtil;
import com.ejavashop.entity.product.Product;
import com.ejavashop.entity.search.SearchSetting;
import com.ejavashop.entity.shopm.pcindex.PcTitleKeywordsDescription;
import com.ejavashop.service.pcindex.IPcTitleKeywordsDescriptionService;
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
    
    @Resource
    private IPcTitleKeywordsDescriptionService pcTitleKeywordsDescriptinService;

    private static String regEx = "[\u4e00-\u9fa5]";
    private static Pattern pat = Pattern.compile(regEx);
    
    public static SolrClient getSolrClient() {
        String solrUrl = DomainUrlUtil.getEJS_SOLR_URL();
        String solrServer = DomainUrlUtil.getEJS_SOLR_SERVER();
        return new HttpSolrClient(solrUrl + "/" + solrServer);
    }
    
    /**
     * 
     * @param request
     * @param keyword
     * @param stack
     * @param priceSort DESC或者ASC
     * @param stockSort DESC或者ASC
     * @return
     */
    @RequestMapping(value = "/search.html", method = RequestMethod.GET)
    public String search(HttpServletRequest request, String keyword, Map<String, Object> stack,String priceSort,String stockSort) {
        keyword = StringUtil.trim(StringUtil.nullSafeString(keyword));
        if ("".equals(keyword)) {
            List<String> keywords = getKeywords();
            if (keywords.size() > 0) {
                keyword = keywords.get(0);
            } else {
                keyword = "裸袜";
            }
        }
        
        //add by Ls 2017/04/14 ------------------begin-----------------------
        PcTitleKeywordsDescription entity = new PcTitleKeywordsDescription();
        String title = PcTitleKeywordsDescription.TITLE_DEFAULT;
        String keywords = PcTitleKeywordsDescription.KEYWORDS_DEFAULT;
        String description = PcTitleKeywordsDescription.DESCRIPTION_DEFAULT;
        ServiceResult<PcTitleKeywordsDescription> titleResult = pcTitleKeywordsDescriptinService.getByPath("http://www.dawawang.com/search.html?keyword=");
        if(titleResult.getSuccess() && titleResult.getResult() != null){
            entity = titleResult.getResult();
            title = entity.getTitle().replace("/*/", keyword);
            keywords = entity.getKeywords();
            description = entity.getDescription();
        }
        stack.put("title", title);
        stack.put("keywords", keywords);
        stack.put("description", description);
        //----------------end-------------------------
        
        //判定如果搜索关键字中不存在中文，则通过数据库搜索商品，否则走solr搜索
       if(!isContainsChinese(keyword)){
//        if (true) {
        	keyword = keyword.toUpperCase();//全部转化为大写字母
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
        		ServiceResult<Integer> serviceResultKeyword = searchSettingService.getSearchKeywordsByKeyword(keyword);
        		int countKeyword = serviceResultKeyword.getResult();
        		if (countKeyword > 0) {
        			return "redirect:/error.html";
        		}
        	}
        	int count = 0;
        	
        	PaginationUtil page = WebUtil.handlerPaginationUtil(request);
        	int start = (page.getNum() - 1) * ConstantsEJS.DEFAULT_PAGE_SIZE;
        	int size = ConstantsEJS.DEFAULT_PAGE_SIZE;
        	/*page.setNum(1);
        	page.setSize(20);
        	*/
        	
        	String searchKeyword =keyword;
        	Integer pageCount = 0;
        	List<Product> products = new ArrayList<Product>();
        	try {
        	    pageCount = productService.getProductByProductPageCountCode(searchKeyword).getResult();
    			List<Product> product = productService.getProductByProductCode(searchKeyword,start,size).getResult();
    			if(product.size() > 0){
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
        	stack.put("page", page);
        	stack.put("count", products.size());
        	stack.put("keyword", keyword);
        	stack.put("url4page", "search.html?keyword=" + keyword);
        	stack.put("producListVOs", products);
        	if (products.size() == 0) {
        		//当没有搜索到商品时
        		return "front/product/productsearchlist";
        	}
        	return "front/product/productsearchlist";
        	//end
       }
       else {
        	keyword = StringUtil.stringFilterSpecial(keyword);
        	 
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
        	
        	String searchKeyword = "(" + keyword + ")";
        	int count = 0;
        	SolrClient client = getSolrClient();
        	SolrQuery query = new SolrQuery();
        	
        	int start = 0, size = 0;
        	PaginationUtil page = WebUtil.handlerPaginationUtil(request);
        	start = (page.getNum() - 1) * ConstantsEJS.DEFAULT_PAGE_SIZE;
        	size = ConstantsEJS.DEFAULT_PAGE_SIZE;
        	
        	String searchIndexAssemblingString = SearchProductVO.searchIndexAssembling(searchKeyword);
        	query.setQuery(searchIndexAssemblingString);
        	
        	query.set("start", start);
        	query.set("rows", size);
        	if ("DESC".equals(priceSort)) {
        		stack.put("sortPrice", priceSort);
        		stack.put("sortStock", stockSort);
        		query.addSort(SearchProductVO.MALLPCPRICE_, ORDER.desc);
        	} else if ("ASC".equals(priceSort)) {
        		stack.put("sortPrice", priceSort);
        		stack.put("sortStock", stockSort);
        		query.addSort(SearchProductVO.MALLPCPRICE_, ORDER.asc);
        	} else if ("DESC".equals(stockSort)) {
        		stack.put("sortPrice", priceSort);
        		stack.put("sortStock", stockSort);
        		query.addSort(SearchProductVO.PRODUCTSTOCK_, ORDER.desc);
        	} else if ("ASC".equals(stockSort)) {
        		stack.put("sortPrice", priceSort);
        		stack.put("sortStock", stockSort);
        		query.addSort(SearchProductVO.PRODUCTSTOCK_, ORDER.asc);
        	} else {
        		stack.put("sortStock", "normal");
        		stack.put("sortPrice", "normal");
	        	query.set("defType", "edismax");
	        	query.set("qf", "name1^100 content^0.8 brand^10 keyword^50");
	        	query.set("pf", "name1 brand content keyword");
	        	String sortModel = "sum(product(if(exists(query({!v='name1:" + keyword + "'})),100000,1),max(id,0)),product(if(exists(query({!v='keyword:" + keyword + "'})),10000,0),max(id,0)),product(if(exists(query({!v='brand:" + keyword + "'})),1000,0),max(id,0)),product(if(exists(query({!v='content:" + keyword + "'})),100,0),max(id,0)))";
	       	    query.set("bf",sortModel);
        	}
        	query.setHighlight(true);
        	query.setParam("hl.fl", SearchProductVO.NAME1_);
        	query.setHighlightSimplePre("<font color=\"red\">");
        	query.setHighlightSimplePost("</font>");
        	
        	List<Product> products = new ArrayList<Product>();
        	QueryResponse response = null;
        	try {
        		response = client.query(query);
        		SolrDocumentList docs = response.getResults();
        		count = new Integer(docs.getNumFound() + "");
        		for (SolrDocument doc : docs) {
        			Integer id =  (Integer) doc.getFieldValue(SearchProductVO.ID_);
        			
        			Product product = productService.getProductById(id).getResult();
        			//                product.setId(new Integer(id));
        			//String keyName = product.getName1().replace(keyword,"<font color=\"red\">"+keyword+"</font>");
        			//product.setName1(keyName);
        			if (product != null) {
        				Object object = response.getHighlighting().get(""+id).get(SearchProductVO.NAME1_);
        				if (object != null) {
        					product.setName1(object.toString().replace("[", "").replace("]", ""));
        				}
        				//                    else {
        				//                        product.setName1(doc.getFieldValue(SearchProductVO.NAME1_).toString());
        				//                    }
        				//                product.setName1(response.getHighlighting().get(id).get(SearchProductVO.NAME1_)
        				//                    .toString().replace("[", "").replace("]", ""));
        				
        				//                product.setMasterImg(doc.getFieldValue(SearchProductVO.MASTERIMG_).toString());
        				//                product.setMallPcPrice(new BigDecimal(doc.getFieldValue(
        				//                    SearchProductVO.MALLPCPRICE_).toString()));
        				//                product.setProductStock(new Integer(doc
        				//                    .getFieldValue(SearchProductVO.PRODUCTSTOCK_).toString()));
        				//                product.setCommentsNumber(new Integer(doc.getFieldValue(
        				//                    SearchProductVO.COMMENTSNUMBER_).toString()));
        				//                product.setSellerId(new Integer(doc.getFieldValue(SearchProductVO.SELLERID_)
        				//                    .toString()));
        				
        				products.add(product);
        				
        			}
        		}
        		
        	} catch (SolrServerException e) {
        		e.printStackTrace();
        	} catch (IOException e) {
        		e.printStackTrace();
        	}
        	page.createPagination(count);
        	
        	stack.put("page", page);
        	stack.put("count", count);
        	stack.put("keyword", keyword);
        	stack.put("url4page", "search.html?keyword=" + keyword);
        	stack.put("producListVOs", products);
        	if (products.size() == 0) {
        		//当没有搜索到商品时
        		return "front/product/productsearchlist";
        	}
        	return "front/product/productsearchlist";
        }
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
     * 根据分类查询列表页推荐的头部商品
     * is_top in 99 或 2显示在顶部
     * @param cateId
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
/*    @RequestMapping(value = "searchTop.html", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<Product>> searchTop(HttpServletRequest request,
                                                                 HttpServletResponse response,
                                                                 Map<String, Object> dataMap) {
        HttpJsonResult<List<Product>> jsonResult = new HttpJsonResult<List<Product>>();

        ServiceResult<List<Product>> serviceResult = productFrontService.getProductSearchByTop();

        List<Product> productsTop = new ArrayList<Product>();
        if (serviceResult.getSuccess()) {
            productsTop = serviceResult.getResult();
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
/*    @RequestMapping(value = "searchLeft.html", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<Product>> searchLeft(HttpServletRequest request,
                                                                  HttpServletResponse response,
                                                                  Map<String, Object> dataMap) {
        HttpJsonResult<List<Product>> jsonResult = new HttpJsonResult<List<Product>>();

        ServiceResult<List<Product>> serviceResult = productFrontService.getProductSearchByLeft();

        List<Product> productsTop = new ArrayList<Product>();
        if (serviceResult.getSuccess()) {
            productsTop = serviceResult.getResult();
        }

        jsonResult.setData(productsTop);
        jsonResult.setTotal(productsTop.size());
        return jsonResult;
    }*/

    /**
     * 异步获取关键词
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "searchKeyword.html", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<String>> cateTop(HttpServletRequest request,
                                                              HttpServletResponse response,
                                                              Map<String, Object> dataMap) {
        HttpJsonResult<List<String>> jsonResult = new HttpJsonResult<List<String>>();
        List<String> keywords = getKeywords();

        jsonResult.setData(keywords);
        jsonResult.setTotal(keywords.size());
        return jsonResult;
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
