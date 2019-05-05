package com.ejavashop.web.controller.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.HttpJsonResult;
import com.ejavashop.core.Md5;
import com.ejavashop.core.RandomUtil;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.StringUtil;
import com.ejavashop.core.WebUtil;
import com.ejavashop.core.freemarkerutil.DomainUrlUtil;
import com.ejavashop.entity.member.Member;
import com.ejavashop.entity.order.Orders;
import com.ejavashop.entity.parter.ParterSign;
import com.ejavashop.service.cart.ICartService;
import com.ejavashop.service.member.IMemberService;
import com.ejavashop.service.order.IOrdersService;
import com.ejavashop.service.parter.IParterSignService;
import com.ejavashop.service.sms.ISenderService;
import com.ejavashop.web.controller.BaseController;
import com.ejavashop.web.util.WebFrontSession;

/**
 * 会员登录controller
 * 
 * @Filename: MemberLoginController.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Controller
public class MemberLoginController extends BaseController {

    @Resource
    private IMemberService memberService;

    @Resource
    private IOrdersService ordersService;

    @Resource
    private ISenderService senderService;
    
    @Resource
    private ICartService cartService;
    
    @Resource
    private IParterSignService parterSignService ;

    /**
     * 跳转到登录页面
     * @param request
     * @param response
     * @param stack
     * @return
     */
    @RequestMapping(value = "/login.html", method = { RequestMethod.GET })
    public String index(HttpServletRequest request, HttpServletResponse response, Map<String, Object> stack) {
        String toUrl = request.getParameter("toUrl");
        stack.put("toUrl", toUrl);
        return "h5v1/member/login";
    }

    /**
     * 登录
     * @param request
     * @param response
     * @param dataMap
     * @return
     * @throws Exception 
     */
    @RequestMapping(value = "dologin.html", method = { RequestMethod.POST })
    public @ResponseBody HttpJsonResult<Member> loginSumbit(HttpServletRequest request,
                                                             HttpServletResponse response) {
        String verifyCode = request.getParameter("verifyCode");
        //获得session中的验证码
        String verify_number = WebFrontSession.getVerifyNumber(request);
        if (verify_number == null || !verify_number.equalsIgnoreCase(verifyCode)) {
            return new HttpJsonResult<Member>("验证码不正确！");
        }

        String ip = WebUtil.getIpAddr(request);
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        // 登录验证
        ServiceResult<Member> serviceResult = memberService.memberLogin(userName, password, ip, ConstantsEJS.SOURCE_2_H5);
        Integer status = 1;
        if (serviceResult.getResult() != null && !"".equals(serviceResult.getResult().getId())) {
            Integer memberId = serviceResult.getResult().getId();
            ServiceResult<Integer> ordersResult = ordersService.getOrdersByMemberId(memberId);
            status = ordersResult.getResult();
        }
        HttpJsonResult<Member> jsonResult = new HttpJsonResult<Member>();
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                jsonResult = new HttpJsonResult<Member>(serviceResult.getMessage());
                jsonResult.setTotal(status);
                return jsonResult;
            }
        }

        try {
            Member member = serviceResult.getResult();
            //add by tzm 查询用户进货单是否存在数据
            if(member!=null){
            	//通过用户ID来查询用户订单数量
            	ServiceResult<Integer> serviceResult2 = cartService.getCartNumberByMId(member.getId());
            	//如果用户进货单中存在数量，则前端底部导航进货单则显示一个提示图标
            	if(serviceResult2.getResult()!=null && serviceResult2.getResult()!=0){
            		request.getSession().setAttribute("memberCartCount",serviceResult2.getResult());
            	}
            }
            //end
            member.setStatus(status);
            // 登录送经验值积分
            memberService.memberLoginSendValue(member.getId(), member.getName());
            // 存入session
            WebFrontSession.putMemberSession(request, member);
            jsonResult.setData(member);
            WebFrontSession.addSessionIdToCookie(request, response);
            
            ServiceResult<List<ParterSign>> parterSignServiceResult = parterSignService.getByMemeberId(""+member.getId());
            if(parterSignServiceResult.getSuccess()){
            	List<ParterSign>  parterSign = parterSignServiceResult.getResult();
            	if(parterSign != null && parterSign.size() > 0){
            		WebFrontSession.putObjToSession(request, "is_parter_sign", "true");
            	}else{
            		WebFrontSession.putObjToSession(request, "is_parter_sign", "false");
            	}
            }
            
            //加入cookie
//            Cookie cookieNew = new Cookie(ConstantsEJS.LOGIN_COOKIE, member.getId() + "");
//            // cookie缓存一年,每次登陆都加入Cookie，防止用户清理手机缓存清理掉Cookie
//            cookieNew.setMaxAge(365 * 30 * 24 * 60 * 60);
//            cookieNew.setDomain(DomainUrlUtil.EJS_COOKIE_DOMAIN);
//            cookieNew.setPath("/");
//            response.addCookie(cookieNew);
        } catch (Exception e) {
            jsonResult = new HttpJsonResult<Member>("登录失败，请重试");
        }

        return jsonResult;
    }

    /**
     * 退出登录
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "logout.html", method = { RequestMethod.GET })
    public String logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession(false);
        if (session != null) {
            WebFrontSession.removeSessionIdToCookie(request, response);
            session.removeAttribute("memberSession");
        }
//        return "h5v1/member/login";
        return "forward:/";
    }

    /**
     * 判断登录
     * @param request
     * @param response
     * @param dataMap
     * @return
     * @throws Exception 
     */
    @RequestMapping(value = "isuserlogin.html", method = { RequestMethod.POST })
    public @ResponseBody HttpJsonResult<Boolean> isUserLogin(HttpServletRequest request,
                                                             HttpServletResponse response) {
        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
        Member loginedUser = WebFrontSession.getLoginedUser(request);
        if (loginedUser == null) {
            jsonResult.setData(false);
        } else {
            jsonResult.setData(true);
        }
        return jsonResult;
    }

    /**
     * 获取登录用户（如果未登录返回null对象）
     * @param request
     * @param response
     * @param dataMap
     * @return
     * @throws Exception 
     */
    @RequestMapping(value = "getloginuser.html", method = { RequestMethod.POST })
    public @ResponseBody HttpJsonResult<Member> getLoginUser(HttpServletRequest request, HttpServletResponse response) {
        HttpJsonResult<Member> jsonResult = new HttpJsonResult<Member>();
        Member loginedUser = WebFrontSession.getLoginedUser(request);
        if (loginedUser == null) {
            jsonResult.setData(null);
        } else {
            jsonResult.setData(loginedUser);
        }
        return jsonResult;
    }

    /**
     * 跳转到忘记密码页面
     * @param request
     * @param response
     * @param stack
     * @return
     */
    @RequestMapping(value = "/forgetpassword.html", method = { RequestMethod.GET })
    public String forgetPassword(HttpServletRequest request, HttpServletResponse response,
                                 Map<String, Object> stack) {
        return "h5v1/member/person/forgetpassword";
    }

    /**
     * 忘记密码发送邮件
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping(value = "/doforgetpassword.html", method = { RequestMethod.POST })
    public @ResponseBody HttpJsonResult doForgetpassword(HttpServletRequest request,
                                                         HttpServletResponse response)
                                                                                      throws Exception {

        HttpJsonResult<String> result = new HttpJsonResult<String>();
        String mobile = request.getParameter("mobile");
        String mobVerfiy = request.getParameter("mobVerfiy");
        HttpSession session = request.getSession();

        if (StringUtil.isEmpty(mobile)) {
            return new HttpJsonResult("手机号不能为空！");
        }
        if (StringUtil.isEmpty(mobVerfiy)) {
            return new HttpJsonResult("手机验证码不能为空！");
        }
        String verifyCode = request.getParameter("verifyCode");
        if (StringUtil.isEmpty(verifyCode)) {
            return new HttpJsonResult("验证码不能为空！");
        }
        if (!mobVerfiy.equals(session.getAttribute("smsCode"))) {
            return new HttpJsonResult("手机验证码错误");
        }
        //获得session中的验证码
        String verify_number = WebFrontSession.getVerifyNumber(request);
        if (verify_number == null || !verify_number.equalsIgnoreCase(verifyCode)) {
            return new HttpJsonResult("验证码输入错误");
        }

        // 根据name取会员信息
        ServiceResult<List<Member>> memberResult = memberService.getMemberByName(mobile);
        if (!memberResult.getSuccess()) {
            return new HttpJsonResult(memberResult.getMessage());
        }
        if (memberResult.getResult() == null || memberResult.getResult().size() == 0) {
            return new HttpJsonResult("您输入的手机号不存在");
        }
        Member member = memberResult.getResult().get(0);

        //新密码
        String newPwd = RandomUtil.randomNumber(6);

        Member memberNew = new Member();
        memberNew.setId(member.getId());
        memberNew.setPassword(Md5.getMd5String(newPwd));

        ServiceResult updateMember = memberService.updateMember(memberNew);
        if (!updateMember.getSuccess()) {
            return new HttpJsonResult(updateMember.getMessage());
        }
//        System.out.println("newPwd:" + newPwd);

        //发送短信
        Map<String, Object> smsparam = new HashMap<String, Object>();
//        System.out.println("newPwd:" + newPwd);
        smsparam.put("mobile", member.getName());
        smsparam.put("content", "【大袜网】您的登录密码为" + newPwd + "，请登录后修改密码，袜业全产业链一站式服务平台。");
        senderService.sendSMS(smsparam);

        result.setData("密码重置成功，请注意查收短信。");
        return result;
    }

    /**
     * 跳转到用户中心页面
     * @param request
     * @param response
     * @param dataMap
     * @return
     * @throws Exception 
     */
    @RequestMapping(value = "/member/index.html", method = { RequestMethod.GET })
    public String memberIndex(HttpServletRequest request, HttpServletResponse response,
                              Map<String, Object> dataMap) {
        Member sessionMember = WebFrontSession.getLoginedUser(request);
        if (sessionMember == null) {
            return "h5v1/member/login";
        }
        Integer memberId = sessionMember.getId();
        // 获取member对象
        ServiceResult<Member> result = memberService.getMemberById(memberId);
        dataMap.put("member", result.getResult());

        //待支付订单数
        ServiceResult<Integer> numResult = ordersService.getOrderNumByMIdAndState(memberId, Orders.ORDER_STATE_1);
        dataMap.put("toBepaidOrders", numResult.getResult());
        //待收货订单数
        numResult = ordersService.getOrderNumByMIdAndState(memberId, Orders.ORDER_STATE_4);
        dataMap.put("toBeReceivedOrders", numResult.getResult());
        //        //待评价订单数
        //        numResult = ordersService.getOrderNumByMIdAndState(memberId, Orders.ORDER_STATE_5);
        //        dataMap.put("toBeCommentedOrders", numResult.getResult());

        return "h5v1/member/memberindex";
    }
}
