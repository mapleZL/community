package com.phkj.service.member;

import java.util.List;
import java.util.Map;

import com.phkj.core.PagerInfo;
import com.phkj.core.ServiceResult;
import com.phkj.entity.member.Member;
import com.phkj.entity.member.MemberCollectionProduct;
import com.phkj.entity.member.MemberCollectionSeller;
import com.phkj.entity.member.MemberLoginLogs;
import com.phkj.entity.member.MemberRule;
import com.phkj.vo.member.FrontCheckPwdVO;
import com.phkj.vo.member.FrontMemberProductBehaveStatisticsVO;

public interface IMemberService {

    /**
     * 根据id取得会员
     * @param  memberId
     * @return
     */
    ServiceResult<Member> getMemberById(Integer memberId);

    /**
     * 根据条件取得会员
     * @param queryMap
     * @param pager
     * @return
     */
    ServiceResult<List<Member>> getMembers(Map<String, String> queryMap, PagerInfo pager);

    /**
     * 保存会员
     * @param  member
     * @return
     */
    ServiceResult<Integer> saveMember(Member member);

    /**
    * 更新会员
    * @param  member
    * @return
    */
    ServiceResult<Boolean> updateMember(Member member);


    /**
     * 根据会员ID获取会员登录日志
     * @param memberId 会员ID
     * @param pager 分页信息
     * @return
     */
    ServiceResult<List<MemberLoginLogs>> getMemberLoginLogs(Integer memberId, PagerInfo pager);

    /**
     * 根据会员ID获取会员收藏商铺
     * @param memberId 会员ID
     * @param pager 分页信息
     * @return
     */
    ServiceResult<List<MemberCollectionSeller>> getMemberCollectionSellers(Integer memberId,
                                                                           PagerInfo pager);

    /**
     * 根据会员ID获取会员收藏商品
     * @param memberId 会员ID
     * @param pager 分页信息
     * @return
     */
    ServiceResult<List<MemberCollectionProduct>> getMemberCollectionProducts(Integer memberId,
                                                                             PagerInfo pager);


    /**
     * 会员登录，修改会员登录信息，记录登录日志
     * @param memberName 用户名
     * @param password 密码
     * @param ip
     * @param source 登录来源
     * @return
     * @throws Exception
     */
    ServiceResult<Member> memberLogin(String memberName, String password, String ip,
                                      Integer source);

    /**
     * 会员注册
     * @param member
     * @return
     * @throws Exception
     */
    ServiceResult<Member> memberRegister(Member member);

    /**
     * 根据会员名称取会员
     * @param name
     * @return
     */
    ServiceResult<List<Member>> getMemberByName(String name);


    /**
     * 根据id取得会员经验值和积分规则
     * <li>当state传入null时根据ID取数据
     * <li>当state不为null时根据ID及状态取数据
     * 
     * @param  memberRuleId 主键
     * @param  state 状态：1、开始；2、关闭
     * @return
     */
    ServiceResult<MemberRule> getMemberRule(Integer memberRuleId, Integer state);

    /**
     * 会员注册时送经验值和积分</br>
     * 涉及表：
     * <li>1、member
     * <li>2、member_grade_integral_logs
     * <li>3、member_grade_up_logs
     * 
     * @param memberId 会员ID，必须
     * @param memberName 会员名，不能为null
     * 
     * @return Member 会员对象
     */
    ServiceResult<Boolean> memberRegistSendValue(Integer memberId, String memberName);

    /**
     * 会员登录时送经验值和积分</br>
     * 用户每日第一次登陆时送积分，之后的登陆不再送积分。</br>
     * 涉及表：
     * <li>1、member
     * <li>2、member_grade_integral_logs
     * <li>3、member_grade_up_logs
     * 
     * @param memberId 会员ID，必须
     * @param memberName 会员名，不能为null
     * 
     * @return Member 会员对象
     */
    ServiceResult<Boolean> memberLoginSendValue(Integer memberId, String memberName);

    /**
     * 会员下单时送经验值和积分</br>
     * 涉及表：
     * <li>1、member
     * <li>2、member_grade_integral_logs
     * <li>3、member_grade_up_logs
     * 
     * @param memberId 会员ID，必须
     * @param memberName 会员名，不能为null
     * @param orderId 订单号
     * 
     * @return Member 会员对象
     */
    ServiceResult<Boolean> memberOrderSendValue(Integer memberId, String memberName,
                                                Integer orderId);

    /**
     * 会员评论时送经验值和积分</br>
     * 涉及表：
     * <li>1、member
     * <li>2、member_grade_integral_logs
     * <li>3、member_grade_up_logs
     * 
     * @param memberId 会员ID，必须
     * @param memberName 会员名，不能为null
     * @param productId 商品ID
     * 
     * @return Member 会员对象
     */
    ServiceResult<Boolean> memberEvaluateSendValue(Integer memberId, String memberName,
                                                   Integer productId);

    /**
     * 修改密码提交
     * @param oldPwd 旧密码
     * @param newPwd 新密码
     * @param request
     * @return
     */
    ServiceResult<Member> editPassword(String oldPwd, String newPwd, Member member);

    /**
     * 根据查询条件取所有的评论 单品页使用 
     * @param request
     * @param pager
     * @return
     */
    public ServiceResult<FrontMemberProductBehaveStatisticsVO> getBehaveStatisticsByProductId(Integer productId,
                                                                                              Member member);

    /**
     * 判断 余额支付密码是否正确
     * @param balancePwd
     * @param request
     * @return  返回错误次数
     */
    public ServiceResult<FrontCheckPwdVO> checkcheckBalancePwd(String balancePwd, Integer memberId);

    /**
     * 支付密码修改
     * @param oldPwd 旧密码
     * @param newPwd 新密码
     * @param request
     * @return
     */
    ServiceResult<Member> editBalancePassword(String oldPwd, String newPwd, Integer memberId);

    /**
     * 设置支付密码
     * @param password 支付密码
     * @param request
     * @return
     */
    ServiceResult<Member> addBalancePassword(String password, Member member);
    
}