//package com.ejavashop.web.util;
//
//import java.math.BigDecimal;
//
//import javax.annotation.Resource;
//import javax.servlet.ServletContext;
//import javax.servlet.http.HttpServletRequest;
//
//import org.apache.log4j.LogManager;
//import org.apache.log4j.Logger;
//import org.springframework.stereotype.Controller;
//
//import com.ejavashop.core.ConstantsJM;
//import com.ejavashop.core.ServiceResult;
//import com.ejavashop.core.exception.BusinessException;
//import com.ejavashop.entity.member.Member;
//import com.ejavashop.entity.member.MemberGradeConfig;
//import com.ejavashop.entity.member.MemberRule;
//import com.ejavashop.service.member.IFrontMemberService;
//
///**
// * TODO 注入方式修改
// *                       
// * @Filename: MemberUtil.java
// * @Version: 1.0
// * @Author: 陈万海
// * @Email: chenwanhai@sina.com
// *
// */
//@Controller(value = "memberUtil")
//public class MemberUtil {
//
//    private static Logger       log = LogManager.getLogger(MemberUtil.class);
//
//    @Resource(name = "frontMemberService")
//    private IFrontMemberService frontMemberService;
//
//    /**
//     * 从request中获取会员等级配置，如果request中不存在，则从数据库取并存入request中
//     * @param request
//     * @return
//     */
//    public MemberGradeConfig getMemberGradeConfig(HttpServletRequest request) {
//        // 从ServletContext中获取会员等级配置
//        ServletContext context = request.getServletContext();
//        Object config = context.getAttribute(ConstantsJM.MEMBER_GRADE_CONFIG_KEY);
//        if (config != null) {
//            try {
//                // TODO 类型强转有时候会报错，类型是完全一样的
//                // 错误：java.lang.ClassCastException: com.javamalls.entity.MemberGradeConfig cannot be cast to com.javamalls.entity.MemberGradeConfig
//                // 为什么？
//                // 如果有返回
//                return (MemberGradeConfig) config;
//            } catch (Exception e) {
//                log.error(e);
//            }
//        }
//        // 如果没有，从数据库取，并存入ServletContext中，返回
//        ServiceResult<MemberGradeConfig> result = frontMemberService
//            .getMemberGradeConfig(ConstantsJM.MEMBER_GRADE_CONFIG_ID);
//        if (!result.getSuccess()) {
//            log.error(result.getMessage());
//            return null;
//        }
//        context.setAttribute(ConstantsJM.MEMBER_GRADE_CONFIG_KEY, result.getResult());
//        return result.getResult();
//    }
//
//    /**
//     * 从request中获取会员经验值规则配置，如果request中不存在，则从数据库取并存入request中
//     * @param request
//     * @return
//     */
//    public MemberRule getMemberGradeRule(HttpServletRequest request) {
//        // 从ServletContext中获取会员经验值规则配置
//        ServletContext context = request.getServletContext();
//        Object rule = context.getAttribute(ConstantsJM.MEMBER_RULE_GRADE_KEY);
//        if (rule != null) {
//            try {
//                // TODO 类型强转有时候会报错，类型是完全一样的
//                // 错误：与getMemberGradeConfig()一样的错误
//                // 为什么？
//                // 如果有返回
//                return (MemberRule) rule;
//            } catch (Exception e) {
//                log.error(e);
//            }
//        }
//        // 如果没有，从数据库取，并存入ServletContext中，返回
//        ServiceResult<MemberRule> result = frontMemberService
//            .getMemberRule(ConstantsJM.MEMBER_RULE_GRADE_ID, 1);
//        if (!result.getSuccess()) {
//            log.error(result.getMessage());
//            return null;
//        }
//        context.setAttribute(ConstantsJM.MEMBER_RULE_GRADE_KEY, result.getResult());
//        return result.getResult();
//    }
//
//    /**
//     * 从request中获取会员积分值规则配置，如果request中不存在，则从数据库取并存入request中
//     * @param request
//     * @return
//     */
//    public MemberRule getMemberIntegralRule(HttpServletRequest request) {
//        // 从ServletContext中获取会员积分值规则配置
//        ServletContext context = request.getServletContext();
//        Object rule = context.getAttribute(ConstantsJM.MEMBER_RULE_INTEGRAL_KEY);
//        if (rule != null) {
//            try {
//                // TODO 类型强转有时候会报错，类型是完全一样的，要不要存json串然后再转回来
//                // 错误：与getMemberGradeConfig()一样的错误
//                // 为什么？
//                // 如果有返回
//                return (MemberRule) rule;
//            } catch (Exception e) {
//                log.error(e);
//            }
//        }
//        // 如果没有，从数据库取，并存入ServletContext中，返回
//        ServiceResult<MemberRule> result = frontMemberService
//            .getMemberRule(ConstantsJM.MEMBER_RULE_INTEGRAL_ID, 1);
//        if (!result.getSuccess()) {
//            log.error(result.getMessage());
//            return null;
//        }
//        context.setAttribute(ConstantsJM.MEMBER_RULE_INTEGRAL_KEY, result.getResult());
//        return result.getResult();
//    }
//
//    /**
//     * 注册时调用，根据经验值和积分配置，给会员送积分和经验值</br>
//     * 默认此时在session中还没有保存会员信息
//     * TODO 可否考虑注册时传入member对象，构造好会员的经验值、积分值、等级后直接返回对象，日志在插入会员对象时记录，以减少访问数据库连接次数
//     * @param request
//     * @return Member 返回最新的会员对象
//     * @throws Exception 
//     */
//    public Member memberRegistSendValue(HttpServletRequest request, Integer memberId,
//                                        String memberName) throws Exception {
//        // 会员等级规则配置
//        MemberGradeConfig gradeConfig = this.getMemberGradeConfig(request);
//        if (gradeConfig == null) {
//            log.error("会员等级规则配置不能为空。");
//            throw new BusinessException("会员等级规则配置不能为空，请重试！");
//        }
//        // 经验值规则
//        MemberRule gradeRule = this.getMemberGradeRule(request);
//        if (gradeRule == null) {
//            log.error("会员经验值规则不能为空。");
//            throw new BusinessException("会员经验值规则不能为空，请重试！");
//        }
//        // 积分规则
//        MemberRule integralRule = this.getMemberIntegralRule(request);
//        if (integralRule == null) {
//            log.error("会员积分规则不能为空。");
//            throw new BusinessException("会员积分规则不能为空，请重试！");
//        }
//        // 注册送经验值数
//        Integer gradeValue = gradeRule.getRegister();
//        // 注册送积分数
//        Integer integralValue = integralRule.getRegister();
//        ServiceResult<Member> result = frontMemberService.memberRegistSendValue(memberId,
//            memberName, gradeValue, integralValue, gradeConfig);
//        if (!result.getSuccess()) {
//            log.error("会员注册送经验值和积分时失败：" + result.getMessage());
//            throw new BusinessException(result.getMessage());
//        }
//        return result.getResult();
//    }
//
//    /**
//     * 会员登录时调用，根据经验值和积分配置，给会员送积分和经验值</br>
//     * 
//     * TODO 必须在执行了WebFrontSession.putMemberSession()之后执行此方法，否则在此方法加memberId、memberName两个参数
//     * 
//     * @param request
//     * @return Member 返回最新的会员对象
//     * @throws Exception 
//     */
//    public Member memberLoginSendValue(HttpServletRequest request) throws Exception {
//        MemberSession memberSession = WebFrontSession.getMemberSession(request);
//        if (memberSession == null) {
//            log.error("会员信息获取失败。");
//            throw new BusinessException("会员信息获取失败，请重试！");
//        }
//        Member member = memberSession.getMember();
//        if (member == null) {
//            log.error("会员信息获取失败。");
//            throw new BusinessException("会员信息获取失败，请重试！");
//        }
//
//        // 会员等级规则配置
//        MemberGradeConfig gradeConfig = this.getMemberGradeConfig(request);
//        if (gradeConfig == null) {
//            log.error("会员等级规则配置不能为空。");
//            throw new BusinessException("会员等级规则配置不能为空，请重试！");
//        }
//        // 经验值规则
//        MemberRule gradeRule = this.getMemberGradeRule(request);
//        if (gradeRule == null) {
//            log.error("会员经验值规则不能为空。");
//            throw new BusinessException("会员经验值规则不能为空，请重试！");
//        }
//        // 积分规则
//        MemberRule integralRule = this.getMemberIntegralRule(request);
//        if (integralRule == null) {
//            log.error("会员积分规则不能为空。");
//            throw new BusinessException("会员积分规则不能为空，请重试！");
//        }
//        // 登录送经验值数
//        Integer gradeValue = gradeRule.getLogin();
//        // 登录送积分数
//        Integer integralValue = integralRule.getLogin();
//        ServiceResult<Member> result = frontMemberService.memberLoginSendValue(member.getId(),
//            member.getName(), gradeValue, integralValue, gradeConfig);
//        if (!result.getSuccess()) {
//            log.error("会员登录送经验值和积分时失败：" + result.getMessage());
//            throw new BusinessException(result.getMessage());
//        }
//        return result.getResult();
//    }
//
//    /**
//     * 会员下单时调用，根据经验值和积分配置，给会员送积分和经验值</br>
//     * 
//     * @param request
//     * @return Member 返回最新的会员对象
//     * @throws Exception 
//     */
//    public Member memberOrderSendValue(HttpServletRequest request, Integer orderId,
//                                       BigDecimal amount) throws Exception {
//        MemberSession memberSession = WebFrontSession.getMemberSession(request);
//        if (memberSession == null) {
//            log.error("会员信息获取失败。");
//            throw new BusinessException("会员信息获取失败，请重试！");
//        }
//        Member member = memberSession.getMember();
//        if (member == null) {
//            log.error("会员信息获取失败。");
//            throw new BusinessException("会员信息获取失败，请重试！");
//        }
//
//        // 会员等级规则配置
//        MemberGradeConfig gradeConfig = this.getMemberGradeConfig(request);
//        if (gradeConfig == null) {
//            log.error("会员等级规则配置不能为空。");
//            throw new BusinessException("会员等级规则配置不能为空，请重试！");
//        }
//        // 经验值规则
//        MemberRule gradeRule = this.getMemberGradeRule(request);
//        if (gradeRule == null) {
//            log.error("会员经验值规则不能为空。");
//            throw new BusinessException("会员经验值规则不能为空，请重试！");
//        }
//        // 积分规则
//        MemberRule integralRule = this.getMemberIntegralRule(request);
//        if (integralRule == null) {
//            log.error("会员积分规则不能为空。");
//            throw new BusinessException("会员积分规则不能为空，请重试！");
//        }
//        // 下单送经验值数比例
//        Integer gradeValuePro = gradeRule.getOrderBuy();
//        // 送经验值上限
//        Integer gradeValuemax = gradeRule.getOrderMax();
//        // 计算送经验值，如果比例是0，则直接设定送0经验值，否则计算（订单总额/比例）
//        // 如果（订单总额/比例）大于max送max分
//        // 如果（订单总额/比例）小于max送（订单总额/比例）分
//        Integer gradeValue = 0;
//        if (!gradeValuePro.equals(0)) {
//            gradeValue = amount.intValue() / gradeValuePro;
//            gradeValue = gradeValue > gradeValuemax ? gradeValuemax : gradeValue;
//        }
//        // 下单送积分数比例
//        Integer integralValuePro = integralRule.getOrderBuy();
//        // 送积分数上限
//        Integer integralValuemax = integralRule.getOrderMax();
//        Integer integralValue = 0;
//        // 计算送积分数，原则跟经验值一样
//        if (!integralValuePro.equals(0)) {
//            integralValue = amount.intValue() / integralValuePro;
//            integralValue = integralValue > integralValuemax ? integralValuemax : integralValue;
//        }
//
//        ServiceResult<Member> result = frontMemberService.memberOrderSendValue(member.getId(),
//            member.getName(), gradeValue, integralValue, gradeConfig, orderId);
//        if (!result.getSuccess()) {
//            log.error("会员下单送经验值和积分时失败：" + result.getMessage());
//            throw new BusinessException(result.getMessage());
//        }
//        return result.getResult();
//    }
//
//    /**
//     * 会员下单时调用，根据经验值和积分配置，给会员送积分和经验值</br>
//     * 
//     * @param request
//     * @return Member 返回最新的会员对象
//     * @throws Exception 
//     */
//    public Member memberEvaluateSendValue(HttpServletRequest request,
//                                          Integer productId) throws Exception {
//        MemberSession memberSession = WebFrontSession.getMemberSession(request);
//        if (memberSession == null) {
//            log.error("会员信息获取失败。");
//            throw new BusinessException("会员信息获取失败，请重试！");
//        }
//        Member member = memberSession.getMember();
//        if (member == null) {
//            log.error("会员信息获取失败。");
//            throw new BusinessException("会员信息获取失败，请重试！");
//        }
//
//        // 会员等级规则配置
//        MemberGradeConfig gradeConfig = this.getMemberGradeConfig(request);
//        if (gradeConfig == null) {
//            log.error("会员等级规则配置不能为空。");
//            throw new BusinessException("会员等级规则配置不能为空，请重试！");
//        }
//        // 经验值规则
//        MemberRule gradeRule = this.getMemberGradeRule(request);
//        if (gradeRule == null) {
//            log.error("会员经验值规则不能为空。");
//            throw new BusinessException("会员经验值规则不能为空，请重试！");
//        }
//        // 积分规则
//        MemberRule integralRule = this.getMemberIntegralRule(request);
//        if (integralRule == null) {
//            log.error("会员积分规则不能为空。");
//            throw new BusinessException("会员积分规则不能为空，请重试！");
//        }
//        // 评论送经验值数
//        Integer gradeValue = gradeRule.getOrderEvaluate();
//        // 评论送积分数
//        Integer integralValue = integralRule.getOrderEvaluate();
//        ServiceResult<Member> result = frontMemberService.memberEvaluateSendValue(member.getId(),
//            member.getName(), gradeValue, integralValue, gradeConfig, productId);
//        if (!result.getSuccess()) {
//            log.error("会员评论送经验值和积分时失败：" + result.getMessage());
//            throw new BusinessException(result.getMessage());
//        }
//        return result.getResult();
//    }
//
//}