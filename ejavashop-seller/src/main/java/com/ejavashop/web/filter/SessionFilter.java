//package com.ejavashop.web.filter;
//
//import java.io.IOException;
//import java.util.List;
//import java.util.Map;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.log4j.Logger;
//import org.springframework.context.ApplicationContext;
//import org.springframework.util.Assert;
//import org.springframework.web.context.support.WebApplicationContextUtils;
//
//import com.ejavashop.core.WebUtil;
//import com.ejavashop.entity.seller.SellerUser;
//import com.ejavashop.entity.system.SystemResources;
//import com.ejavashop.service.seller.ISellerResourcesRolesService;
//import com.ejavashop.web.util.WebSellerSession;
//
///**
// * session过滤器
// *                       
// */
//public class SessionFilter implements Filter {
//    Logger log = Logger.getLogger(this.getClass());
//
//    @Override
//    public void destroy() {
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequset, ServletResponse servletResonpse,
//                         FilterChain chain) throws IOException, ServletException {
//        HttpServletRequest request = (HttpServletRequest) servletRequset;
//        HttpServletResponse response = (HttpServletResponse) servletResonpse;
//        SellerUser sellerUser = WebSellerSession.getSellerUser(request);
//
//        response.setHeader("Pragma", "no-cache");
//        response.addHeader("Cache-Control", "must-revalidate");
//        response.addHeader("Cache-Control", "no-cache");
//        response.addHeader("Cache-Control", "no-store");
//        response.setDateHeader("Expires", 0);
//
//        String uri = request.getServletPath();
//        Map<String, String> queryMap = WebUtil.getParamMap(request);
//
//        // 此项目没有规定请求的扩展名,所以只排除特定的uri
//        if (!uri.equals("/seller/login.html") && !uri.equals("/seller/doLogin")) {
//            if (sellerUser == null) {
//                log.info("没有登录");
//                String path = request.getContextPath();
//                response.sendRedirect(path + "/seller/login.html");
//            } else {
//                // 生成菜单下的按钮
//                if ("menu".equals(queryMap.get("type"))) {
//                    String id = queryMap.get("id");
//                    Assert.notNull(id);
//                    //spring上下文
//                    ApplicationContext context = WebApplicationContextUtils
//                        .getRequiredWebApplicationContext(request.getSession().getServletContext());
//                    ISellerResourcesRolesService serv = (ISellerResourcesRolesService) context
//                        .getBean("sellerResourcesRolesService");
//
//                    // 此资源下的有权限的子资源
//                    List<SystemResources> list = serv.getResourceByPid(Integer.valueOf(id),
//                        WebSellerSession.getSellerUser(request).getRoleId(),
//                        SystemResources.SCOPE_SELLER);
//                    log.info("菜单uri:[" + uri + "] 此资源下有权限的子资源数为:[" + list.size() + "]");
//
//                    // 资源按钮
//                    StringBuffer sb = new StringBuffer();
//                    for (SystemResources res : list) {
//                        sb.append("<a id=\"" + res.getResId() + "\"")
//                            .append(" href=\"" + res.getUrl() + "\"")
//                            .append(" class=\"easyui-linkbutton\"")
//                            .append(" iconCls=\"" + res.getResIcon() + "\" plain=\"true\"")
//                            .append(">" + res.getContent() + "</a>");
//                    }
//                    // 放到此次会话请求
//                    request.getSession().setAttribute("buttons", sb.toString());
//                }
//                chain.doFilter(servletRequset, servletResonpse);
//            }
//        } else {
//            chain.doFilter(servletRequset, servletResonpse);
//        }
//    }
//
//    @Override
//    public void init(FilterConfig arg0) throws ServletException {
//
//    }
//
//}
