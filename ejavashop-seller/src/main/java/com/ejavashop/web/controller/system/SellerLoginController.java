package com.ejavashop.web.controller.system;

import java.util.Enumeration;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.Md5;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.entity.seller.Seller;
import com.ejavashop.entity.seller.SellerUser;
import com.ejavashop.service.seller.ISellerResourcesRolesService;
import com.ejavashop.service.seller.ISellerService;
import com.ejavashop.service.seller.ISellerUserService;
import com.ejavashop.web.controller.BaseController;
import com.ejavashop.web.util.WebSellerSession;

@Controller
@RequestMapping(value = "seller")
//@Scope("prototype")
public class SellerLoginController extends BaseController {

    @Resource
    private ISellerService               sellerService;
    @Resource
    private ISellerUserService           sellerUserService;
    @Resource
    private ISellerResourcesRolesService sellerResourcesRolesService;
    private Logger                       log = Logger.getLogger(this.getClass());

    @RequestMapping(value = "/login.html", method = { RequestMethod.GET })
    public String login(Map<String, Object> dataMap, HttpServletRequest request,
                        HttpServletResponse response) throws Exception {
        String message = request.getParameter("message");
        dataMap.put("message", message);
        return "seller/login";
    }

    @RequestMapping(value = "/exit", method = { RequestMethod.GET })
    public String exit(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Subject subject = SecurityUtils.getSubject();
        subject.logout();

        HttpSession session = request.getSession();
        Enumeration<?> em = session.getAttributeNames();
        //清空session
        while (em.hasMoreElements()) {
            session.removeAttribute(em.nextElement().toString());
        }
        //清除cookie
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            cookie.setMaxAge(0);
        }
        //重定向
        return "redirect:/seller/login.html";
    }

    /**
     * 登陆
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/doLogin", method = { RequestMethod.POST })
    public String doLogin(Map<String, Object> dataMap, HttpServletRequest request,
                          HttpServletResponse response) throws Exception {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String verifyCode = request.getParameter("verifyCode");

        try {
            String verify_number = WebSellerSession.getVerifyNumber(request);
            if (verify_number == null || !verify_number.equalsIgnoreCase(verifyCode)) {
                throw new BusinessException("验证码不正确！");
            }

            if (name == null) {
                throw new BusinessException("用户名不能为空");
            }
            if (password == null) {
                throw new BusinessException("密码不能为空");
            }

            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(name, Md5
                .getMd5String(password).toCharArray());
            // token.setRememberMe(true);
            subject.login(token);

            SellerUser sellerUser = (SellerUser) subject.getPrincipal();

            if (sellerUser.getState().intValue() != SellerUser.STATE_NORM) {
                throw new BusinessException("账户冻结，不能登录！");
            }

            ServiceResult<Seller> serviceResult = sellerService.getSellerById(sellerUser
                .getSellerId());
            if (!serviceResult.getSuccess()) {
                if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                    throw new RuntimeException(serviceResult.getMessage());
                } else {
                    throw new BusinessException(serviceResult.getMessage());
                }
            }

            Seller seller = serviceResult.getResult();
            if (seller == null) {
                throw new BusinessException("所属店铺信息获取失败，请重试！");
            }
            if (seller.getAuditStatus().equals(Seller.AUDIT_STATE_1_SEND)) {
                throw new BusinessException("所属店铺未被审核通过！");
            }
            sellerUser.setSeller(seller);

            WebSellerSession.putSellerUser(request, sellerUser);
        } catch (UnknownAccountException e) {
            log.error("账号不存在：{}", e);
            dataMap.put("message", "账号不存在");
            return "seller/login";
        } catch (DisabledAccountException e) {
            log.error("账号未启用：{}", e);
            dataMap.put("message", "账号未启用");
            return "seller/login";
        } catch (IncorrectCredentialsException e) {
            log.error("密码错误：{}", e);
            dataMap.put("message", "账号或密码错误");
            return "seller/login";
        } catch (BusinessException e) {
            dataMap.put("message", e.getMessage());
            return "seller/login";
        } catch (RuntimeException e) {
            log.error("未知错误,请联系管理员：{}", e);
            dataMap.put("message", "未知错误,请联系管理员");
            return "seller/login";
        }
        return "redirect:/seller/index.html";
    }

    //    /**
    //     * 查找当前用户角色拥有的菜单权限  并生成左边的tree
    //     * @param paramsMap
    //     * @param request
    //     * @param roleId
    //     * @return
    //     */
    //    public String createTree(List<SystemResources> listResources, HttpServletRequest request) {
    //        SystemResources parentRes = generateResTree(listResources, new SystemResources(),
    //            ConstantsEJS.SELLER_RESOURCE_ROOT);
    //
    //        List<SystemResources> children = parentRes.getChildren();
    //        StringBuilder html = new StringBuilder();
    //        for (SystemResources tr : children) {
    //
    //            log.info("父资源菜单:" + tr.getContent());
    //            html.append("<div title=\"").append(tr.getContent()).append("\" class=\"ra_div\">");
    //
    //            for (SystemResources tbr : tr.getChildren()) {
    //                log.info("子资源菜单:" + tbr.getContent());
    //
    //                //                html.append(
    //                //                    "<a id='" + tbr.getId() + "' href=\"javascript:void(0);\" onclick=\"addTab('")
    //                //                    .append(tbr.getContent()).append("', '").append(WebUtil.getURL(request))
    //                //                    .append(tbr.getUrl().indexOf("?") > 0 ? tbr.getUrl() + "&id=" + tbr.getId()
    //                //                        : tr.getUrl() + "?id=" + tbr.getId())
    //                //                    .append("')\">").append(tbr.getContent()).append("</a>");
    //                // 用逗号分隔资源表的url字段，该字段可存储多个url，并用英文逗号（，）分隔
    //                String[] split = tbr.getUrl().split(",");
    //                html.append(
    //                    "<a id='" + tbr.getId() + "' href=\"javascript:void(0);\" onclick=\"addTab('")
    //                    .append(tbr.getContent()).append("', '").append(WebUtil.getURL(request))
    //                    .append(split[0]).append("')\">").append(tbr.getContent()).append("</a>");
    //            }
    //            html.append("</div>");
    //        }
    //        return html.toString();
    //    }

    //    /**递归 treeGrid节点
    //     * @param listResources
    //     * @param tree
    //     * @param id
    //     * @return
    //     */
    //    private SystemResources generateResTree(List<SystemResources> listResources,
    //                                            SystemResources parentRes, Integer id) {
    //        for (SystemResources res : listResources) {
    //            if (res.getPid().intValue() == id.intValue()) {
    //                parentRes.getChildren().add(res);
    //
    //                generateResTree(listResources, res, res.getId());
    //            }
    //        }
    //        return parentRes;
    //    }

    @RequestMapping(value = "/index.html", method = { RequestMethod.GET })
    public String index(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Subject subject = SecurityUtils.getSubject();
        // 如果没有登录，去登录页
        if (!subject.isAuthenticated()) {
            response.getWriter().print(
                "<script>top.window.location.href='" + request.getContextPath()
                        + "/seller/login.html'</script>");
            return null;
        }
        return "seller/index";
    }

    /**
     * 访问无权限URL时跳转路径
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/unauth.html", method = { RequestMethod.GET })
    public String unAuth() throws Exception {
        return "seller/unauth";
    }

}
