package com.ejavashop.web.controller.analysis;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ejavashop.core.ConvertUtil;
import com.ejavashop.core.CookieHelper;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.StringUtil;
import com.ejavashop.core.WebUtil;
import com.ejavashop.core.freemarkerutil.DomainUrlUtil;
import com.ejavashop.entity.analysis.BrowseLog;
import com.ejavashop.service.analysis.IAnalysisService;
import com.ejavashop.web.controller.BaseController;

/**
 * 记录浏览记录
 *                       
 * @Filename: AnalysisLogController.java
 * @Version: 1.0
 * @Author: 王朋
 * @Email: wpjava@163.com
 *
 */
@Controller
public class AnalysisLogController extends BaseController {

    @Resource
    private IAnalysisService analysisService;

    /**
     * 记录用户查浏览记录
     * @param request
     * @param response
     * @param productId
     * @param sku
     */
    @RequestMapping(value = "/browse_Logs.html", method = RequestMethod.GET)
    public void browseLog(HttpServletRequest request, HttpServletResponse responsed) {
        String ref = request.getParameter("ref");
        String memberId = request.getParameter("memberId");
        String hrf = request.getParameter("hrf");
        String browser = request.getParameter("browser");
        String verinfo = request.getParameter("verinfo");

        Cookie cookie = CookieHelper.getCookieByName(request, DomainUrlUtil.getEJS_COOKIE_NAME());
        String cookieValue = cookie == null ? "" : cookie.getValue();

        Cookie cookieSession = CookieHelper.getCookieByName(request, "JSESSIONID");
        String cookieValueSession = cookieSession == null ? "" : cookieSession.getValue();

        BrowseLog browseLog = new BrowseLog();
        browseLog.setSiteCookie(StringUtil.nullSafeString(cookieValue));
        browseLog.setSessionId(StringUtil.nullSafeString(cookieValueSession));
        browseLog.setBrowseName(StringUtil.nullSafeString(browser));
        browseLog.setBrowserVersion(StringUtil.nullSafeString(verinfo));
        browseLog.setUserAgent(StringUtil.nullSafeString(request.getHeader("user-agent")));
        browseLog.setIpAddress(WebUtil.getIpAddr(request));
        browseLog.setUrlReferer(StringUtil.nullSafeString(ref));
        browseLog.setAccessedPage(StringUtil.nullSafeString(hrf));
        browseLog.setMemberId(ConvertUtil.toInt(memberId, ConvertUtil.toInt(memberId, 0)));

        ServiceResult<Integer> servletResult = analysisService.saveBrowseLog(browseLog);
        if (!servletResult.getSuccess()) {
            log.error("[AnalysisLogController][browseLog]记录用户行为轨迹出现异常");
        }
    }

}
