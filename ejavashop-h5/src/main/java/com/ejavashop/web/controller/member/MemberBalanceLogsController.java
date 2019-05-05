package com.ejavashop.web.controller.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.ConvertUtil;
import com.ejavashop.core.HttpJsonResult;
import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.StringUtil;
import com.ejavashop.entity.member.Member;
import com.ejavashop.entity.member.MemberBalanceLogs;
import com.ejavashop.service.member.IMemberBalanceLogsService;
import com.ejavashop.service.member.IMemberService;
import com.ejavashop.web.controller.BaseController;
import com.ejavashop.web.util.WebFrontSession;

/**
 * 余额日志
 *                       
 * @Filename: MemberBalanceLogsController.java
 * @Version: 1.0
 * @Author: 王朋
 * @Email: wpjava@163.com
 *
 */
@Controller
@RequestMapping(value = "member")
public class MemberBalanceLogsController extends BaseController {

    @Resource
    private IMemberService            memberService;

    @Resource
    private IMemberBalanceLogsService memberBalanceLogsService;

    /**
     * 跳转到余额列表页面
     * @param request
     * @param response
     * @param stack
     * @return
     */
    @RequestMapping(value = "/balance.html", method = { RequestMethod.GET })
    public String balancelist(HttpServletRequest request, HttpServletResponse response,
                              Map<String, Object> dataMap) {
        Member sessionMember = WebFrontSession.getLoginedUser(request);

        //查询用户信息
        ServiceResult<Member> memberResult = memberService.getMemberById(sessionMember.getId());

//        Map<String, String> queryMap = new HashMap<String, String>();
//        queryMap.put("memberId", sessionMember.getId().toString());

//        PagerInfo pager = new PagerInfo(ConstantsEJS.DEFAULT_PAGE_SIZE, 1);
//        ServiceResult<List<MemberBalanceLogs>> serviceResult = memberBalanceLogsService.page(queryMap, pager);

//        dataMap.put("pagesize", ConstantsEJS.DEFAULT_PAGE_SIZE);

//        dataMap.put("memberBalanceLogss", serviceResult.getResult());
        dataMap.put("member", memberResult.getResult());
        String list = request.getParameter("type");
        if(!StringUtil.isEmpty(list)){
        	 return "h5v1/member/balance/balancelist";
        }else{
        	return "h5v1/member/balance/balance";
        }
    }

    /**
     * 返回余额 json 数据
     * @param cateId
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "/balanceJson.html", method = RequestMethod.GET)
    public @ResponseBody HttpJsonResult<List<MemberBalanceLogs>> balanceJson(HttpServletRequest request,
                                                                             HttpServletResponse response) {
        HttpJsonResult<List<MemberBalanceLogs>> jsonResult = new HttpJsonResult<List<MemberBalanceLogs>>();
        Member member = WebFrontSession.getLoginedUser(request);

        Map<String, String> queryMap = new HashMap<String, String>();
        queryMap.put("memberId", member.getId().toString());

        String pageNumStr = request.getParameter("pageNum");
        int pageNum = ConvertUtil.toInt(pageNumStr, 1);
        PagerInfo pager = new PagerInfo(ConstantsEJS.DEFAULT_PAGE_SIZE_10, pageNum);

        ServiceResult<List<MemberBalanceLogs>> serviceResult = memberBalanceLogsService.page(queryMap, pager);
        if (!serviceResult.getSuccess()) {
            return jsonResult;
        }

        jsonResult.setData(serviceResult.getResult());
        jsonResult.setTotal(serviceResult.getResult().size());
        if (pager.getRowsCount() == 0) 
            jsonResult.setTotal(1000);             //代表没有下一页
        else if (ConstantsEJS.DEFAULT_PAGE_SIZE_10 * pageNum >= pager.getRowsCount())
            jsonResult.setTotal(0);             //代表没有下一页
//        jsonResult.setTotal(serviceResult.getResult().size());
        return jsonResult;
    }

}
