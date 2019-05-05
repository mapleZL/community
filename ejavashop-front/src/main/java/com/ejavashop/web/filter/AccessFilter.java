package com.ejavashop.web.filter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.ejavashop.web.util.MemberSession;
import com.ejavashop.web.util.WebFrontSession;

/**
 * 访问过滤器
 * 
 * @Filename: AccessFilter.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
public class AccessFilter implements Filter {
    /**
     * 日志
     */
    private static Logger log = LogManager.getLogger(AccessFilter.class);

    public void destroy() {
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // 请求的URI
        String uri = request.getRequestURI();
        // Referer从哪个页面链接过来的
        String referer = request.getHeader("Referer");
        log.debug("AccessFilter-getRequestURI:" + request.getRequestURI());
        log.debug("AccessFilter-referer:" + referer);

        try {
            MemberSession memberSession = WebFrontSession.getMemberSession(request);
            // 用户已经登录
            if (memberSession != null && memberSession.getMember() != null) {
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            } else {
                // 用户未登录时，跳转到登录页面
                log.info("AccessFilter：用户未登录访问[" + uri + "]强制跳转到登录页面！");
                String path = request.getContextPath();
                response.sendRedirect(path + "/login.html");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void init(FilterConfig arg0) throws ServletException {
    }

}
