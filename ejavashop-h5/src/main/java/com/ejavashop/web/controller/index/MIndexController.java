package com.ejavashop.web.controller.index;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.HttpJsonResult;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.shopm.MIndexBanner;
import com.ejavashop.entity.shopm.MIndexFloor;
import com.ejavashop.service.mindex.IMIndexService;
import com.ejavashop.service.pcindex.IPcRecommendService;
import com.ejavashop.web.controller.BaseController;

/**
 * 首页controller
 * 
 * @Filename: MIndexController.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Controller
public class MIndexController extends BaseController {

    @Resource
    private IMIndexService mIndexService;

    @Resource
    private IPcRecommendService   pcRecommendService;
    
    /**
     * 首页
     * @param request
     * @param response
     * @param stack
     * @return
     * @throws IOException 
     */
    @RequestMapping(value = "/", method = { RequestMethod.GET })
    public String indexRedirect(HttpServletRequest request, HttpServletResponse response,
                                Map<String, Object> stack) throws IOException {
        // 取得定时任务存入ServletContext中的首页缓存html字符串
        Object indexObj = request.getServletContext().getAttribute(ConstantsEJS.M_INDEX_HTML_CACHE);
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
            initIndex(stack, true);
            return "h5v1/index/index";
        }

    }

    /**
     * 首页
     * @param request
     * @param response
     * @param stack
     * @return
     */
    @RequestMapping(value = "/index.html", method = { RequestMethod.GET })
    public String index(HttpServletRequest request, HttpServletResponse response,
                        Map<String, Object> stack) {
        return "redirect:/";
    }

    /**
     * 首页缓存调用
     * @param request
     * @param response
     * @param stack
     * @return
     */
    @RequestMapping(value = "/cacheIndex.html", method = { RequestMethod.GET ,RequestMethod.POST})
    public String cacheIndex(HttpServletRequest request, HttpServletResponse response,
                             Map<String, Object> stack) {
        initIndex(stack, false);
        return "h5v1/index/index";
    }

    /**
     * 首页预览
     * @param request
     * @param response
     * @param stack
     * @return
     */
    @RequestMapping(value = "/previewindex.html", method = { RequestMethod.GET })
    public String previewIndex(HttpServletRequest request, HttpServletResponse response,
                               Map<String, Object> stack) {
        initIndex(stack, true);
        return "h5v1/index/index";
    }

    /**
     * 首页初始化方法
     * @param dataMap
     * @param isPreview
     */
    private void initIndex(Map<String, Object> stack, boolean isPreview) {
    	
    	 // 首页今日推荐
       /* ServiceResult<List<PcRecommend>> todayRecommendResult = pcRecommendService
            .getPcRecommendForView(PcRecommend.RECOMMEND_TYPE_2, isPreview);
        if (!todayRecommendResult.getSuccess()) {
            log.error(todayRecommendResult.getMessage());
        }
        stack.put("todayList", todayRecommendResult.getResult());*/

        // 首页热销推荐
        /*ServiceResult<List<PcRecommend>> hotRecommendResult = pcRecommendService
            .getPcRecommendForView(PcRecommend.RECOMMEND_TYPE_1, isPreview);
        if (!hotRecommendResult.getSuccess()) {
            log.error(hotRecommendResult.getMessage());
        }
        stack.put("hotList", hotRecommendResult.getResult());*/
        
        
        ServiceResult<List<MIndexBanner>> bannerResult = mIndexService.getMIndexBannerForView(isPreview);
      //处理 查询出来的banner和弹出层
        List<MIndexBanner> bList = bannerResult.getResult();
        List<MIndexBanner> bannerLsit = new ArrayList<MIndexBanner>();
//        List<MIndexBanner> tanchuLsit = new ArrayList<MIndexBanner>();
        for (MIndexBanner mIndexBanner : bList) {
        	//轮播图
        	if("0".equals(mIndexBanner.getType())){
        		bannerLsit.add(mIndexBanner);
        	}	
        	//弹出层
//        	if("1".equals(mIndexBanner.getType())){
//        		tanchuLsit.add(mIndexBanner);
//        	}
			
		}
        
        stack.put("banners", bannerLsit);
//        if(tanchuLsit != null && tanchuLsit.size() > 0){
//        	stack.put("tanchu", tanchuLsit.get(0));
//        }else{
//        	stack.put("tanchu", "1");
//        }

        ServiceResult<List<MIndexFloor>> floorResult = mIndexService.getMIndexFloorsWithData(isPreview);
        stack.put("floors", floorResult.getResult());
    }
    

    /**
     * 首页弹出层
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "/popup.html", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<MIndexBanner> popup(HttpServletRequest request, HttpServletResponse response,String type) {
        HttpJsonResult<MIndexBanner> jsonResult = new HttpJsonResult<MIndexBanner>();
        ServiceResult<MIndexBanner> banner = mIndexService.getPopupForView(type);
        if (banner == null) {
            jsonResult.setData(null);
        } else {
            jsonResult.setData(banner.getResult());
        }
        return jsonResult;
    }
}
