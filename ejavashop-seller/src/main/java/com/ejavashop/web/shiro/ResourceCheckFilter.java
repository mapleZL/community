package com.ejavashop.web.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;

import com.ejavashop.core.StringUtil;

public class ResourceCheckFilter extends AccessControlFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response,
                                      Object mappedValue) throws Exception {
        Subject subject = getSubject(request, response);
        String url = getPathWithinApplication(request);
        if (StringUtil.isEmpty(url) || "/".equals(url)) {
            HttpServletResponse hsp = (HttpServletResponse) response;
            HttpServletRequest hReq = (HttpServletRequest) request;
            hsp.getWriter().print(
                "<script>top.window.location.href='" + hReq.getContextPath()
                        + "/seller/login.html'</script>");
        }
        if (url != null && url.endsWith("/")) {
            // 截去url最后一个/
            url = url.substring(0, url.length() - 1);
        }
        return subject.isAuthenticated() && subject.isPermitted(url);
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response)
                                                                                      throws Exception {
        HttpServletResponse hsp = (HttpServletResponse) response;
        HttpServletRequest hReq = (HttpServletRequest) request;
        Subject subject = getSubject(request, response);
        //Terry change syncway 20170231
        String url = getPathWithinApplication(request);
        if (url.contains("/sync_way_set/sync_way_set")) {
            return true;
        }
        // 如果没有登录，去登录页
        if (!subject.isAuthenticated()) {
            hsp.getWriter().print(
                "<script>top.window.location.href='" + hReq.getContextPath()
                        + "/seller/login.html'</script>");
        } else {
            // 如果已登录，提示没有权限
            hsp.sendRedirect(hReq.getContextPath() + "/seller/unauth.html");
        }
        return false;
    }

}
