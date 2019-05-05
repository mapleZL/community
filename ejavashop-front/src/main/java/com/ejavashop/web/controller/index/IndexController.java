package com.ejavashop.web.controller.index;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.HttpJsonResult;
import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.WebUtil;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.entity.member.Member;
import com.ejavashop.entity.product.Product;
import com.ejavashop.entity.product.ProductPrice;
import com.ejavashop.entity.shopm.pcindex.PcIndexBanner;
import com.ejavashop.entity.shopm.pcindex.PcIndexFloor;
import com.ejavashop.entity.shopm.pcindex.PcRecommend;
import com.ejavashop.service.cart.ICartService;
import com.ejavashop.service.news.INewsService;
import com.ejavashop.service.pcindex.IPcIndexBannerService;
import com.ejavashop.service.pcindex.IPcIndexFloorService;
import com.ejavashop.service.pcindex.IPcRecommendService;
import com.ejavashop.service.product.IProductCateService;
import com.ejavashop.service.product.IProductFrontService;
import com.ejavashop.vo.cart.CartInfoVO;
import com.ejavashop.vo.product.FrontProductCateVO;
import com.ejavashop.vo.product.ProductTypeVO;
import com.ejavashop.web.controller.BaseController;
import com.ejavashop.web.util.WebFrontSession;

/**
 * 首页controller
 * 首页初始化，以及一些公共的url
 * @Filename: IndexController.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Controller
public class IndexController extends BaseController {

    Logger                        log = Logger.getLogger(this.getClass());

    @Resource
    private INewsService          newsService;
    @Resource
    private IProductCateService   productCateService;
    @Resource
    private ICartService          cartService;
    @Resource
    private IPcIndexBannerService pcIndexBannerService;
    @Resource
    private IPcIndexFloorService  pcIndexFloorService;
    @Resource
    private IPcRecommendService   pcRecommendService;
    @Resource
    private IProductFrontService  productFrontService;

    @RequestMapping(value = "", method = { RequestMethod.GET })
    public String index(HttpServletRequest request, HttpServletResponse response,
                        Map<String, Object> dataMap) throws IOException {
        // 取得定时任务存入ServletContext中的首页缓存html字符串
        Object indexObj = request.getServletContext().getAttribute(ConstantsEJS.PC_INDEX_HTML_CACHE);
        log.info("===================== front index   =====================");
        if (indexObj != null && indexObj.toString().length() > 0) {
            log.info("-------------缓存取得首页html");
            // 如果对象不为空，直接打印内容
            response.setContentType("text/html; charset=utf-8");
            PrintWriter out = response.getWriter();
            out.println(indexObj.toString());
            return null;
        } else {
            log.error("-------------直接打开页面");
            // 如果对象为空，说明ServletContext中还未缓存，则直接取数据库数据返回页面打开首页
            initIndex(dataMap, true);
            return "front/index/index";
        }

    }

    @RequestMapping(value = "index.html", method = { RequestMethod.GET })
    public String def(HttpServletRequest request, HttpServletResponse response,
                      Map<String, Object> dataMap) {
        return "redirect:/";
    }

    /**
     * 缓存时调用
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "cacheIndex.html", method = { RequestMethod.GET })
    public String cacheIndex(HttpServletRequest request, HttpServletResponse response,
                             Map<String, Object> dataMap) {
        initIndex(dataMap, false);
        return "front/index/index";
    }

    @RequestMapping(value = "previewindex", method = { RequestMethod.GET })
    public String previewIndex(HttpServletRequest request, HttpServletResponse response,
                               Map<String, Object> dataMap) {
        initIndex(dataMap, true);
        return "front/index/index";
    }
    
    @RequestMapping(value = "personMade.html", method = { RequestMethod.GET,RequestMethod.POST })
    public String personMade(HttpServletRequest request, HttpServletResponse response,
                               Map<String, Object> dataMap) {
        return "front/custom-made/fwc_succeeed";
    }
    

    @RequestMapping(value = "recommend.html", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<PcRecommend>> recommendByRecommendType(HttpServletRequest request, HttpServletResponse response, Map<String, Object> dataMap) {
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        dataMap.put("q_time", new Date());
        dataMap.put("q_status", "1");
        int size = request.getParameter("q_size") == null ? 4 : Integer.parseInt((String)request.getParameter("q_size"));
        if (size == 0) size = 4;
        PagerInfo pager = new PagerInfo(size, 1);
        ServiceResult<List<PcRecommend>> serviceResult = pcRecommendService.getPcRecommends(queryMap, pager);
        
        HttpJsonResult<List<PcRecommend>> jsonResult = new HttpJsonResult<List<PcRecommend>>();
        jsonResult.setRows(serviceResult.getResult());
        jsonResult.setTotal(pager.getRowsCount());
        return jsonResult;
    }

    /**
     * 首页初始化方法
     * @param dataMap
     * @param isPreview
     */
    private void initIndex(Map<String, Object> dataMap, boolean isPreview) {
        // 首页轮播图
        ServiceResult<List<PcIndexBanner>> bannerResult = pcIndexBannerService
            .getPcIndexBannerForView(isPreview);
        if (!bannerResult.getSuccess()) {
            log.error(bannerResult.getMessage());
        }
        
        //处理 查询出来的banner和弹出层
        List<PcIndexBanner> bList = bannerResult.getResult();
        List<PcIndexBanner> bannerLsit = new ArrayList<PcIndexBanner>();
        for (PcIndexBanner pcIndexBanner : bList) {
        	//轮播图
        	if("0".equals(pcIndexBanner.getType())){
        		bannerLsit.add(pcIndexBanner);
        	}
			
		}
        dataMap.put("bannerList", bannerLsit);
        /*
        // 首页今日推荐
        ServiceResult<List<PcRecommend>> todayRecommendResult = pcRecommendService
            .getPcRecommendForView(PcRecommend.RECOMMEND_TYPE_2, isPreview);
        if (!todayRecommendResult.getSuccess()) {
            log.error(todayRecommendResult.getMessage());
        }
        dataMap.put("todayList", todayRecommendResult.getResult());

        // 首页热销推荐
        ServiceResult<List<PcRecommend>> hotRecommendResult = pcRecommendService
            .getPcRecommendForView(PcRecommend.RECOMMEND_TYPE_1, isPreview);
        if (!hotRecommendResult.getSuccess()) {
            log.error(hotRecommendResult.getMessage());
        }
        dataMap.put("hotList", hotRecommendResult.getResult());

        // 首页红包专区
        ServiceResult<List<PcRecommend>> redRecommendResult = pcRecommendService
            .getPcRecommendForView(3, isPreview);
        if (!hotRecommendResult.getSuccess()) {
            log.error(hotRecommendResult.getMessage());
        }
        dataMap.put("redList", redRecommendResult.getResult());

        // 首页冰价区4
        ServiceResult<List<PcRecommend>> iceRecommendResult = pcRecommendService
            .getPcRecommendForView(4, isPreview);
        if (!hotRecommendResult.getSuccess()) {
            log.error(hotRecommendResult.getMessage());
        }
        dataMap.put("iceList", iceRecommendResult.getResult());
        */
        //首页楼层
        ServiceResult<List<PcIndexFloor>> floorResult = pcIndexFloorService
            .getPcIndexFloorForView(isPreview);
        if (!floorResult.getSuccess()) {
            log.error(floorResult.getMessage());
        }
        dataMap.put("floorList", floorResult.getResult());

        ServiceResult<List<ProductTypeVO>> serviceResult = new ServiceResult<List<ProductTypeVO>>();
        serviceResult = productFrontService.getProductTypeList(1);      //Terry 1 is producttype id

        dataMap.put("typeList", serviceResult.getResult());
        // 分类
        /*Map<String, Object> queryMap = new HashMap<String, Object>();
        ServiceResult<List<FrontProductCateVO>> serviceResult = productCateService
            .getProductCateList(queryMap);

        dataMap.put("cateList", serviceResult.getResult());*/
    }

    /**
     * 首页弹出层
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "/popup.html", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<PcIndexBanner>> popup(HttpServletRequest request, HttpServletResponse response,String type) {
        HttpJsonResult<List<PcIndexBanner>> jsonResult = new HttpJsonResult<List<PcIndexBanner>>();
        ServiceResult<List<PcIndexBanner>> banner = pcIndexBannerService.getPopupForView(type);
        if (banner == null) {
            jsonResult.setData(null);
        } else {
            jsonResult.setData(banner.getResult());
        }
        return jsonResult;
    }
    
    /**
     * 导航所有商品分类 
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "/cateList.html", method = { RequestMethod.GET })
    public String getProductCateList(HttpServletRequest request, HttpServletResponse response, Map<String, Object> dataMap) {

        Map<String, Object> queryMap = new HashMap<String, Object>();
        ServiceResult<List<FrontProductCateVO>> serviceResult = new ServiceResult<List<FrontProductCateVO>>();
        serviceResult = productCateService.getProductCateList(queryMap);

        dataMap.put("cateList", serviceResult.getResult());

        return "front/commons/cateList";
    }

    /**
     * 导航所有商品分类 
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "/productTypeList.html", method = { RequestMethod.GET })
    public String getProductTypeList(HttpServletRequest request, HttpServletResponse response, Map<String, Object> dataMap) {

        ServiceResult<List<ProductTypeVO>> serviceResult = new ServiceResult<List<ProductTypeVO>>();
        serviceResult = productFrontService.getProductTypeList(1);      //Terry 4 is producttype id

        dataMap.put("typeList", serviceResult.getResult());

        return "front/commons/productTypeList";
    }
    

    /**
     * 右上角 我的购物车
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "/previewMyCart.html", method = { RequestMethod.GET })
    public String previewMyCart(HttpServletRequest request, HttpServletResponse response,
                                Map<String, Object> dataMap) {
        Member member = WebFrontSession.getLoginedUser(request);
        //取购物车信息  产品价格 按照商家来区分
        //查询购物车
        if (member != null) {
            ServiceResult<CartInfoVO> serviceResult = cartService.getCartInfoByMId(member.getId(),
                null, ConstantsEJS.SOURCE_1_PC, 1,1);
            dataMap.put("cartInfoVO", serviceResult.getResult());
        }
        return "front/cart/previewcart";
    }

    /**
     * 首页  --> 十项服务
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value="/tenItemsView.html",method=RequestMethod.GET)
    public String tenItemsView(HttpServletRequest request,HttpServletResponse response,Map<String,Object> dataMap){
    	String flag = request.getParameter("flag");
    	if(flag != null && !"".equals(flag)){
    		//根据不同flag 判断点击哪项服务  再定位到指定位置
    		dataMap.put("flag", flag);
    	}
    	return "front/index/_ten_items";
    }
    /**
     * 首页  --> 敬请期待
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value="/wait.html",method=RequestMethod.GET)
    public String wait(HttpServletRequest request,HttpServletResponse response,Map<String,Object> dataMap){
    	String ppgname = request.getParameter("ppgname");
    	if(ppgname!=null){
    		if(ppgname.equals("yuzhaolin")){
    			return "front/index/_ppg_yuzhaolin";
    		}else if(ppgname.equals("beijirong")){
    			return "front/index/_ppg_beijirong";
    		}else if(ppgname.equals("nanjiren")){
    			return "front/index/_ppg_nanjiren";
    		}
    	}
    	return "front/index/wait";
    }
    /**
     * 关于大袜网  about.html
     * @param request
     * @return
     */
    @RequestMapping(value="/about.html",method=RequestMethod.GET)
    public String about(HttpServletRequest request){
    	System.out.println("==========about.html============");
    	return "front/about/about";
    }
    /**
     * 关于大袜网  about-2.html
     * @param request
     * @return
     */
    @RequestMapping(value="/about2.html",method=RequestMethod.GET)
    public String about2(HttpServletRequest request){
    	System.out.println("==========about2.html============");
    	return "front/about/about-2";
    }
    /**
     * 关于大袜网  about-3.html
     * @param request
     * @return
     */
    @RequestMapping(value="/about3.html",method=RequestMethod.GET)
    public String about3(HttpServletRequest request){
    	System.out.println("==========about3.html============");
    	return "front/about/about-3";
    }
    /**
     * 关于大袜网  about-4.html
     * @param request
     * @return
     */
    @RequestMapping(value="/about4.html",method=RequestMethod.GET)
    public String about4(HttpServletRequest request){
    	System.out.println("==========about4.html============");
    	return "front/about/about-4";
    }
    /**
     * 关于大袜网  about-4.html
     * @param request
     * @return
     */
    @RequestMapping(value="/about5.html",method=RequestMethod.GET)
    public String about5(HttpServletRequest request){
    	System.out.println("==========about5.html============");
    	return "front/about/about-5";
    }
    /**
     * 采购定制
     * @param request
     * @return
     */
    @RequestMapping(value="/custom-made.html",method=RequestMethod.GET)
    public String custommade(HttpServletRequest request){
    	System.out.println("==========custom-made.html============");
    	return "front/custom-made/fwc-custom-made";
    }
}
