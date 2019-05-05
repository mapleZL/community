package com.ejavashop.web.controller.product;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ejavashop.core.ConvertUtil;
import com.ejavashop.core.CookieHelper;
import com.ejavashop.core.HttpJsonResult;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.WebUtil;
import com.ejavashop.core.freemarkerutil.DomainUrlUtil;
import com.ejavashop.entity.search.SearchLogs;
import com.ejavashop.entity.search.SearchRecord;
import com.ejavashop.service.search.ISearchLogsService;
import com.ejavashop.service.search.ISearchRecordService;
import com.ejavashop.web.controller.BaseController;

/**
 * 搜索关键字日志相关的处理
 *                       
 * @Filename: ProductSearchLogsController.java
 * @Version: 1.0
 * @Author: 王朋
 * @Email: wpjava@163.com
 *
 */
@Controller
public class ProductSearchLogsController extends BaseController {

    @Resource
    private ISearchLogsService   searchLogsService;

    @Resource
    private ISearchRecordService searchRecordService;

    /**
     * 根据keyword获取搜索最近N条记录
     * @return
     */
    @RequestMapping(value = "/get_search_record.html", method = RequestMethod.GET)
    public @ResponseBody HttpJsonResult<List<SearchRecord>> getSearchRecord(HttpServletRequest request,
                                                                            HttpServletResponse responsed,
                                                                            String keyword) {
        HttpJsonResult<List<SearchRecord>> jsonResult = new HttpJsonResult<List<SearchRecord>>();

//        System.out.println("--------" + keyword);

        if (keyword != null && !"".equals(keyword.trim())) {
            ServiceResult<List<SearchRecord>> servletResult = searchRecordService
                .getSearchRecordByKeyword(keyword, 8);
            if (!servletResult.getSuccess()) {
                log.error("[ProductSearchLogsController][getSearchLogs]根据cookie获取搜索最近N条记录出现异常");
            }

            List<SearchRecord> searchLogss = servletResult.getResult();
            jsonResult.setData(searchLogss);

            if (searchLogss != null) {
                jsonResult.setTotal(searchLogss.size());
            }

        }
        return jsonResult;
    }

    /**
     * 根据cookie获取搜索最近N条记录
     * @return
     */
    @RequestMapping(value = "/get_search_logs.html", method = RequestMethod.GET)
    public @ResponseBody HttpJsonResult<List<SearchLogs>> getSearchLogs(HttpServletRequest request,
                                                                        HttpServletResponse responsed) {
        HttpJsonResult<List<SearchLogs>> jsonResult = new HttpJsonResult<List<SearchLogs>>();

        Cookie cookie = CookieHelper.getCookieByName(request, DomainUrlUtil.getEJS_COOKIE_NAME());
        String cookieValue = cookie == null ? "" : cookie.getValue();
//        System.out.println("--------" + cookieValue);

        ServiceResult<List<SearchLogs>> servletResult = searchLogsService.getSearchLogsByCookie(
            cookieValue, 8);
        if (!servletResult.getSuccess()) {
            log.error("[ProductSearchLogsController][getSearchLogs]根据cookie获取搜索最近N条记录出现异常");
        }

        List<SearchLogs> searchLogss = servletResult.getResult();
        jsonResult.setData(searchLogss);

        if (searchLogss != null) {
            jsonResult.setTotal(searchLogss.size());
        }
        return jsonResult;
    }

    /**
     * 根据cookie获取搜索最近N条记录
     * @return
     */
    @RequestMapping(value = "/save_search_logs.html", method = RequestMethod.GET)
    public void saveSearchLogs(HttpServletRequest request, HttpServletResponse responsed,
                               String keyword) {
        Cookie cookie = CookieHelper.getCookieByName(request, DomainUrlUtil.getEJS_COOKIE_NAME());
        String cookieValue = cookie == null ? "" : cookie.getValue();

        String memberId = request.getParameter("memberId");

        if (keyword != null && !"".equals(keyword.trim())) {
            SearchLogs searchLogs = new SearchLogs();
            searchLogs.setKeyword(keyword);
            searchLogs.setSiteCookie(cookieValue);
            searchLogs.setIp(WebUtil.getIpAddr(request));
            searchLogs.setMemberId(ConvertUtil.toInt(memberId, ConvertUtil.toInt(memberId, 0)));
            searchLogsService.saveSearchLogs(searchLogs);
        }

    }
}
