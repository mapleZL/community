package com.ejavashop.web.controller.member;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.HttpJsonResult;
import com.ejavashop.core.Md5;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.member.Member;
import com.ejavashop.service.member.IMemberService;
import com.ejavashop.web.controller.BaseController;
import com.ejavashop.web.util.WebFrontSession;

/**
 * 用户中心：资料管理
 *                       
 */
@Controller
@RequestMapping(value = "member")
public class MemberPasswordController extends BaseController {

    @Resource
    private IMemberService memberService;

    /**
     * 跳转到 修改密码界面
     * @param request
     * @param response
     * @param map
     * @return
     * @throws Exception 
     */
    @RequestMapping(value = "/editpassword.html", method = { RequestMethod.GET })
    public String toEditPassword(HttpServletRequest request, HttpServletResponse response,
                                 ModelMap map) throws Exception {
        Member sessionMember = WebFrontSession.getLoginedUser(request);
        ServiceResult<Member> serviceResult = memberService.getMemberById(sessionMember.getId());

        Member member = null;
        if (serviceResult.getSuccess()) {
            member = serviceResult.getResult();
        }
        map.put("member", member);

        return "h5v1/member/person/editpassword";
    }

    /**
     * 修改密码提交
     * @param request
     * @param response
     * @param map
     * @return
     * @throws Exception 
     */
    @RequestMapping(value = "/updatepassword.html", method = { RequestMethod.POST })
    public @ResponseBody HttpJsonResult<Boolean> editPasswordSumbit(HttpServletRequest request,
                                                                    HttpServletResponse response,
                                                                    @RequestParam(value = "oldPwd", required = true) String oldPwd,
                                                                    @RequestParam(value = "newPwd", required = true) String newPwd,
                                                                    String confirmPwd)
                                                                                      throws Exception {

        if (!newPwd.equals(confirmPwd)) {
            return new HttpJsonResult<Boolean>("确认密码不对");
        }
        Member sessionMember = WebFrontSession.getLoginedUser(request);
        ServiceResult<Member> serviceResult = memberService.editPassword(oldPwd, newPwd,
            sessionMember);
        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                jsonResult = new HttpJsonResult<Boolean>(serviceResult.getMessage());
            }
        }
        return jsonResult;
    }

    /**
     * 跳转到设置支付密码页面
     * @param request
     * @param response
     * @param stack
     * @return
     */
    @RequestMapping(value = "/setbalancepassword.html", method = { RequestMethod.GET })
    public String balancepwdadd(HttpServletRequest request, HttpServletResponse response,
                                Map<String, Object> dataMap) {

        Member sessionMember = WebFrontSession.getLoginedUser(request);

        //查询用户信息
        ServiceResult<Member> memberResult = new ServiceResult<Member>();
        memberResult = memberService.getMemberById(sessionMember.getId());

        dataMap.put("member", memberResult.getResult());
        return "h5v1/member/person/balancepwdadd";
    }

    /**
     * 支付密码提交
     * @param request
     * @param response
     * @param map
     * @return
     * @throws Exception 
     */
    @RequestMapping(value = "/savebalancepassword.html", method = { RequestMethod.POST })
    public @ResponseBody HttpJsonResult<Boolean> addBalancePwdSumbit(HttpServletRequest request,
                                                                     HttpServletResponse response,
                                                                     @RequestParam(value = "password", required = true) String password,
                                                                     String confirmPwd)
                                                                                       throws Exception {
        Member sessionMember = WebFrontSession.getLoginedUser(request);

        if (!password.equals(confirmPwd)) {
            return new HttpJsonResult<Boolean>("确认密码不对");
        }

        ServiceResult<Member> serviceResult = new ServiceResult<Member>();
        serviceResult = memberService.addBalancePassword(password, sessionMember);
        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                jsonResult = new HttpJsonResult<Boolean>(serviceResult.getMessage());
            }
        }
        return jsonResult;
    }

    /**
     * 跳转到 修改支付密码页面
     * @param request
     * @param response
     * @param stack
     * @return
     */
    @RequestMapping(value = "/editbalancepassword.html", method = { RequestMethod.GET })
    public String toEditBalancePwd(HttpServletRequest request, HttpServletResponse response,
                                   Map<String, Object> dataMap) {

        Member sessionMember = WebFrontSession.getLoginedUser(request);
        //查询用户信息
        ServiceResult<Member> memberResult = new ServiceResult<Member>();
        memberResult = memberService.getMemberById(sessionMember.getId());

        dataMap.put("member", memberResult.getResult());
        return "h5v1/member/person/balancepwdedit";
    }

    /**
     * 修改支付密码提交
     * @param request
     * @param response
     * @param map
     * @return
     * @throws Exception 
     */
    @RequestMapping(value = "/updatebalancepassword.html", method = { RequestMethod.POST })
    public @ResponseBody HttpJsonResult<Boolean> editBalancePasswordSumbit(HttpServletRequest request,
                                                                           HttpServletResponse response,
                                                                           @RequestParam(value = "oldPwd", required = true) String oldPwd,
                                                                           @RequestParam(value = "newPwd", required = true) String newPwd,
                                                                           String confirmPwd)
                                                                                             throws Exception {

        if (!newPwd.equals(confirmPwd)) {
            return new HttpJsonResult<Boolean>("确认密码不对");
        }
        Member sessionMember = WebFrontSession.getLoginedUser(request);

        ServiceResult<Member> serviceResult = new ServiceResult<Member>();
        serviceResult = memberService.editBalancePassword(oldPwd, newPwd, sessionMember.getId());
        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                jsonResult = new HttpJsonResult<Boolean>(serviceResult.getMessage());
            }
        }
        return jsonResult;
    }

    /**
     * 跳转到 修改支付密码页面
     * @param request
     * @param response
     * @param stack
     * @return
     */
    @RequestMapping(value = "/resetbalancepassword.html", method = { RequestMethod.GET })
    public String resetbalancepassword(HttpServletRequest request, HttpServletResponse response,
                                       Map<String, Object> dataMap) {

        Member sessionMember = WebFrontSession.getLoginedUser(request);
        //查询用户信息
        ServiceResult<Member> memberResult = new ServiceResult<Member>();
        memberResult = memberService.getMemberById(sessionMember.getId());

        dataMap.put("member", memberResult.getResult());
        return "h5v1/member/person/resetbalancepassword";
    }

    /**
     * 重置支付密码
     * @param request
     * @param response
     * @param newPwd
     * @param confirmPwd
     * @param mobVerify
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/resetbalancepassword.html", method = { RequestMethod.POST })
    public @ResponseBody HttpJsonResult<Member> resetbalancepassword(HttpServletRequest request,
                                                                     HttpServletResponse response,
                                                                     @RequestParam(value = "newPwd", required = true) String newPwd,
                                                                     @RequestParam(value = "confirmPwd", required = true) String confirmPwd,
                                                                     @RequestParam(value = "mobVerify", required = true) String mobVerify)
                                                                                                                                          throws Exception {

        Member sessionMember = WebFrontSession.getLoginedUser(request);

        ServiceResult<Member> serviceResult = new ServiceResult<Member>();
        serviceResult = memberService.getMemberById(sessionMember.getId());
        HttpJsonResult<Member> jsonResult = new HttpJsonResult<Member>();
        HttpSession session = request.getSession();

        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                jsonResult = new HttpJsonResult<Member>(serviceResult.getMessage());
            }
        }
        Member member = serviceResult.getResult();
        if (isNull(mobVerify)) {
            return new HttpJsonResult<Member>("请输入手机验证码");
        }

        if (isNull(newPwd)) {
            return new HttpJsonResult<Member>("请输入密码");
        }

        if (isNull(confirmPwd)) {
            return new HttpJsonResult<Member>("请输入确认密码");
        }

        if (!mobVerify.equals(session.getAttribute("smsCode"))) {
            return new HttpJsonResult<Member>("手机验证码错误");
        }

        if (!newPwd.equals(confirmPwd)) {
            return new HttpJsonResult<Member>("两次密码输入不一致");
        }

        member.setBalancePwd(Md5.getMd5String(newPwd));
        //重置密码输入错误次数
        member.setPwdErrCount(0);
        ServiceResult<Boolean> servlet = memberService.updateMember(member);
        if(!servlet.getSuccess()){
            return new HttpJsonResult<Member>("更新密码失败");  
        }
        return jsonResult;
    }
}
