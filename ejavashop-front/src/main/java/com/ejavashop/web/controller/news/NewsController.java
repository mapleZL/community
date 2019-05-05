package com.ejavashop.web.controller.news;

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

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.ConvertUtil;
import com.ejavashop.core.HttpJsonResult;
import com.ejavashop.core.PaginationUtil;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.WebUtil;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.entity.news.News;
import com.ejavashop.entity.news.NewsType;
import com.ejavashop.entity.shopm.pcindex.PcTitleKeywordsDescription;
import com.ejavashop.service.news.INewsService;
import com.ejavashop.service.pcindex.IPcTitleKeywordsDescriptionService;
import com.ejavashop.vo.news.FrontNewsTypeVO;
import com.ejavashop.vo.news.FrontNewsVO;
import com.ejavashop.web.controller.BaseController;

@Controller
public class NewsController extends BaseController {

    @Resource
    private INewsService newsService;

    @Resource
    private IPcTitleKeywordsDescriptionService pcTitleKeywordsDescriptinService;
    /**
     * 首页footer
     * @param request
     * @param response
     * @param id
     */
    @RequestMapping(value = "news/footerNews.html", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<Map<String, Object>> footerNews(HttpServletRequest request,
                                                                          HttpServletResponse response,
                                                                          Map<String, Object> dataMap) {
        HttpJsonResult<Map<String, Object>> jsonResult = new HttpJsonResult<Map<String, Object>>();

        Map<String, Object> queryMap = new HashMap<String, Object>();
        Map<String, Object> map = new HashMap<String, Object>();
        ServiceResult<List<FrontNewsTypeVO>> newstypes = newsService.getNewsType();
        ServiceResult<String> dataStr = newsService.getDataNews();
        List<FrontNewsTypeVO> newstypesList = newstypes.getResult();
        for (FrontNewsTypeVO newstype : newstypes.getResult()) {
            queryMap.put("typeid", Integer.valueOf(newstype.getId()));

            PaginationUtil page = new PaginationUtil(1, 5);
            newstype.setNews(newsService.getNewsByType(queryMap, page).getResult());
        }
        map.put("newstypesList", newstypesList);
        map.put("dataStr", dataStr.getResult());
        jsonResult.setData(map);
        return jsonResult;
    }
    
    
  /*  @RequestMapping(value = "news/dataNews.html", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<String> dataNews(HttpServletRequest request,
                                                                          HttpServletResponse response,
                                                                          Map<String, Object> dataMap) {
        HttpJsonResult<String> jsonResult = new HttpJsonResult<String>();

        Map<String, Object> queryMap = new HashMap<String, Object>();
        ServiceResult<String> newstypes = newsService.getDataNews();
        jsonResult.setData(newstypes.getResult());
        return jsonResult;
    }
    */

    /**
     * 文章详细页面 
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "news/{id}.html", method = RequestMethod.GET)
    public String article(HttpServletRequest request, Map<String, Object> dataMap,
                          @PathVariable String id) {
        ServiceResult<FrontNewsVO> sr = newsService.get(ConvertUtil.toInt(id, 0));
        if (!sr.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(sr.getCode())) {
                throw new RuntimeException(sr.getMessage());
            } else {
                throw new BusinessException(sr.getMessage());
            }
        }

        ServiceResult<List<NewsType>> serviceRrsult = newsService.getNewsType4Article();
        if (!serviceRrsult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceRrsult.getCode())) {
                throw new RuntimeException(serviceRrsult.getMessage());
            } else {
                throw new BusinessException(serviceRrsult.getMessage());
            }
        }

        ServiceResult<List<News>> resultNews = newsService.getLastedNews();
        if (!resultNews.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(resultNews.getCode())) {
                throw new RuntimeException(resultNews.getMessage());
            } else {
                throw new BusinessException(resultNews.getMessage());
            }
        }
        
        List<News>newList = resultNews.getResult();
        List<News> list = new ArrayList<News>();
        for (News news : newList) {
			if(news.getId() != 41){
				list.add(news);
			}
		}
        dataMap.put("news", sr.getResult());
        dataMap.put("newsTypes", serviceRrsult.getResult());
        dataMap.put("lastedNews", list);
        
      //add by Ls 2017/04/14 ------------------begin-----------------------
        PcTitleKeywordsDescription entity = new PcTitleKeywordsDescription();
        String title = PcTitleKeywordsDescription.TITLE_DEFAULT;
        String keywords = PcTitleKeywordsDescription.KEYWORDS_DEFAULT;
        String description = PcTitleKeywordsDescription.DESCRIPTION_DEFAULT;
        String path = "http://www.dawawang.com/news/*.html";
        path = path.replace("*", id);
        ServiceResult<PcTitleKeywordsDescription> titleResult = pcTitleKeywordsDescriptinService.getByPath(path);
        if(titleResult.getSuccess() && titleResult.getResult() != null){
            entity = titleResult.getResult();
            title = entity.getTitle();
            keywords = entity.getKeywords();
            description = entity.getDescription();
        }
        dataMap.put("title", title);
        dataMap.put("keywords", keywords);
        dataMap.put("description", description);
        //----------------end-------------------------

        return "front/news/article";
    }

    /**
     * 文章列表页面
     * @param request
     * @param dataMap
     * @param typeid
     * @return
     */
    @RequestMapping(value = "news/type_{typeid}.html", method = { RequestMethod.GET })
    public String typeNews(HttpServletRequest request, Map<String, Object> dataMap,
                           @PathVariable String typeid) {
        ServiceResult<List<NewsType>> serviceRrsult = newsService.getNewsType4Article();
        if (!serviceRrsult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceRrsult.getCode())) {
                throw new RuntimeException(serviceRrsult.getMessage());
            } else {
                throw new BusinessException(serviceRrsult.getMessage());
            }
        }

        ServiceResult<List<News>> resultNews = newsService.getLastedNews();
        if (!resultNews.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(resultNews.getCode())) {
                throw new RuntimeException(resultNews.getMessage());
            } else {
                throw new BusinessException(resultNews.getMessage());
            }
        }
        
        List<News>newList = resultNews.getResult();
        List<News> list1 = new ArrayList<News>();
        for (News news : newList) {
			if(news.getId() != 41){
				list1.add(news);
			}
		}

        Map<String, Object> queryMap = WebUtil.handlerQueryMapNoQ(request);
        queryMap.put("typeid", Integer.valueOf(typeid));

        PaginationUtil page = WebUtil.handlerPaginationUtil(request);
        ServiceResult<List<FrontNewsVO>> list = newsService.getNewsByType(queryMap, page);
        dataMap.put("newslist", list.getResult());

        if (list.getSuccess() && list.getResult() != null && list.getResult().size() > 0) {
            dataMap.put("typename", list.getResult().get(0).getTypeName());
        }

        dataMap.put("newsTypes", serviceRrsult.getResult());
        dataMap.put("lastedNews", list1);
        dataMap.put("page", page);
        dataMap.put("typeid", typeid);
        dataMap.put("url4page", "news/type_" + typeid + ".html");

        return "front/news/articlelist";
    }

}
