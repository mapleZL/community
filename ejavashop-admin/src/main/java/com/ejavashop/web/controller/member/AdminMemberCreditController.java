package com.ejavashop.web.controller.member;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.HttpJsonResult;
import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.StringUtil;
import com.ejavashop.core.WebUtil;
import com.ejavashop.core.excel.CellConfig;
import com.ejavashop.core.excel.ExcelConfig;
import com.ejavashop.core.excel.ExcelManager;
import com.ejavashop.core.excel.ExcelConfig.CellType;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.entity.member.MemberCredit;
import com.ejavashop.entity.member.MemberCreditLog;
import com.ejavashop.entity.order.OrdersAndOrdersProductVO;
import com.ejavashop.entity.system.SystemAdmin;
import com.ejavashop.service.member.IMemberCreditLogService;
import com.ejavashop.service.member.IMemberCreditService;
import com.ejavashop.service.member.IMemberService;
import com.ejavashop.web.controller.BaseController;
import com.ejavashop.web.util.WebAdminSession;

/**
 * 赊账管理相关action
 *                       
 * @Filename: AdminMemberCreditController.java
 * @Version: 1.0
 * @Author: zhaihl
 * @Email: zhaihl_0@126.com
 *
 */
@Controller
@RequestMapping(value = "/admin/member/memberCredit")
public class AdminMemberCreditController extends BaseController {
    @Resource
    private IMemberCreditService        memberCreditService;
    @Resource
    private IMemberService              memberService;
    @Resource
    private IMemberCreditLogService     memberCreditLogService;
    

    /**
     * 默认页面
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "", method = { RequestMethod.GET })
    public String index(HttpServletRequest request, ModelMap dataMap) throws Exception {
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);

        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        dataMap.put("queryMap", queryMap);
        dataMap.put("memberId", request.getParameter("memberId"));
        if (!isNull(request.getParameter("memberId"))) {
            dataMap.put("q_memberName",
                memberService.getMemberById(Integer.valueOf(request.getParameter("memberId")))
                    .getResult().getName());
        }
        return "/admin/member/membercredit/list";
    }

    /**
     * gridDatalist数据
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<MemberCredit>> list(HttpServletRequest request,
                                                                 ModelMap dataMap) {
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        ServiceResult<List<MemberCredit>> serviceResult = memberCreditService.page(queryMap, pager);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }

        HttpJsonResult<List<MemberCredit>> jsonResult = new HttpJsonResult<List<MemberCredit>>();
        jsonResult.setRows((List<MemberCredit>) serviceResult.getResult());
        jsonResult.setTotal(pager.getRowsCount());

        return jsonResult;
    }

    /**
     * 会员赊账设置
     * @param request
     * @param dataMap
     * @param id
     * @return
     */
    @RequestMapping(value = "creditset", method = { RequestMethod.GET })
    public String creditset(HttpServletRequest request, ModelMap dataMap, Integer memberId) {
        dataMap.put("memberId", memberId);
        ServiceResult<MemberCredit> sr = memberCreditService.getMemberCreditByMemberId(memberId);
        if (sr.getResult() != null) {
            dataMap.put("added", true);
        }
        return "/admin/member/membercredit/creditset";
    }

    @RequestMapping(value = "edit", method = { RequestMethod.GET })
    public String edit(HttpServletRequest request, ModelMap dataMap, Integer id) {
        if (id != null) {
            MemberCredit memberCredit = memberCreditService.getMemberCreditById(id).getResult();
            dataMap.put("obj", memberCredit);
        }

        return "/admin/member/membercredit/edit";
    }

    @RequestMapping(value = "creditlogDialog", method = { RequestMethod.GET })
    public String creditlogDialog(HttpServletRequest request, ModelMap dataMap, Integer id) {
        dataMap.put("creditId", id);
        return "/admin/member/membercredit/creditlogDialog";
    }

    /**
     * 新增/编辑
     * @param request
     * @param response
     * @param cate
     */
    @RequestMapping(value = "doAdd", method = { RequestMethod.POST })
    public @ResponseBody HttpJsonResult<Boolean> doAdd(HttpServletRequest request,
                                                       HttpServletResponse response,
                                                       MemberCredit memberCredit) {
        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
        ServiceResult<Integer> serviceResult = null;
        try {
            if (memberCredit.getId() != null && 0 != memberCredit.getId()) {

                //重新设定到期日和状态
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.DAY_OF_MONTH, memberCredit.getCycle());
                memberCredit.setExpireDate(cal.getTime());

                memberCredit.setState(1);//未到期
                memberCredit.setHasSettled(0);//未结清
                memberCredit.setSource(2);//管理员调整

                serviceResult = memberCreditService.updateMemberCredit(memberCredit);
            } else {
                memberCredit.setUsed(new BigDecimal(0));
                memberCredit.setSurplus(memberCredit.getQuota());

                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.DAY_OF_MONTH, memberCredit.getCycle());
                memberCredit.setExpireDate(cal.getTime());

                memberCredit.setState(1);//未到期
                memberCredit.setHasSettled(0);//未结清
                memberCredit.setSource(2);//管理员调整

                SystemAdmin admin = WebAdminSession.getAdminUser(request);
                memberCredit.setCreateId(admin.getId());
                memberCredit.setCreateName(admin.getName());
                memberCredit.setCreateTime(new Date());

                serviceResult = memberCreditService.saveMemberCredit(memberCredit);
            }
            jsonResult.setData(serviceResult.getResult() > 0);
        } catch (Exception e) {
            jsonResult.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return jsonResult;
    }

    /**
     * 管理员调整
     * @param request
     * @param response
     * @param memberCredit
     * @return
     */
    @RequestMapping(value = "doEdit", method = { RequestMethod.POST })
    public @ResponseBody HttpJsonResult<Boolean> doEdit(HttpServletRequest request,
                                                        HttpServletResponse response,
                                                        String creditinfo) {
        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
        ServiceResult<Boolean> serviceResult = null;
        try {
            SystemAdmin admin = WebAdminSession.getAdminUser(request);
            serviceResult = memberCreditService.doEdit(creditinfo, admin.getId(), admin.getName());
            if (!serviceResult.getSuccess()) {
                jsonResult.setMessage(serviceResult.getMessage());
            }
            jsonResult.setData(serviceResult.getResult());
        } catch (Exception e) {
            jsonResult.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return jsonResult;
    }

    /**
     * 删除
     * @param request
     * @param response
     * @param cate
     */
    @RequestMapping(value = "del", method = { RequestMethod.GET })
    public void del(HttpServletRequest request, HttpServletResponse response, Integer id) {
        response.setContentType("text/plain;charset=utf-8");
        PrintWriter pw = null;
        String msg = "";
        try {
            pw = response.getWriter();
            ServiceResult<Boolean> sr = memberCreditService.del(id);
            msg = sr.getMessage();
        } catch (Exception e) {
            msg = e.getMessage();
            e.printStackTrace();
        }
        pw.print(msg);
    }
    
    @RequestMapping(value = "chageState", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<Boolean> chageState(HttpServletRequest request,
                                                        HttpServletResponse response,
                                                        Integer selectId , Integer state) {
        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
        ServiceResult<Integer> serviceResult = null;
        MemberCredit memberCredit = memberCreditService.getMemberCreditById(selectId).getResult();
        if(state == 1){
            memberCredit.setState(2);
        }else if(state == 2){
            memberCredit.setState(1);
        }
        try {
            serviceResult = memberCreditService.updateMemberCredit(memberCredit);
            if (!serviceResult.getSuccess()) {
                jsonResult.setMessage(serviceResult.getMessage());
            }
        } catch (Exception e) {
            log.debug("修改失败！！！！！！！！！！！！");
            jsonResult.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return jsonResult;
    }
    
    @RequestMapping(value = "doexport", method = { RequestMethod.GET })
    public void doexport(HttpServletRequest request,HttpServletResponse response,String type,
                         @RequestHeader(value = "user-agent") String userAgent) {
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        //导出总表
        if(type.equals("1")){
            ServiceResult<List<MemberCredit>> serviceResult = memberCreditService.getAllMemberCredit(queryMap);
            String busiErrMsg = "";
            if (!serviceResult.getSuccess()) {
                busiErrMsg = serviceResult.getMessage();
            }
            if (serviceResult.getResult() == null) {
                busiErrMsg = "赊账记录表为空。";
            }

            if (!StringUtil.isEmpty(busiErrMsg, true)) {
                try {
                    Cookie msgCookie = new Cookie("busiErrMsg", URLEncoder.encode(busiErrMsg, "utf-8"));
                    msgCookie.setPath("/");
                    response.addCookie(msgCookie);
                    Cookie fileDownloadCookie = new Cookie("fileDownload", "false");
                    fileDownloadCookie.setPath("/");
                    response.addCookie(fileDownloadCookie);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            } else {
                List<MemberCredit> list = serviceResult.getResult();
                //订单总表
                this.exportMemberCredit(userAgent, response, list);
            }
        }else{//导出明细表
            ServiceResult<List<MemberCreditLog>> serviceResult = memberCreditLogService.getAllMemberCreditLog(queryMap);
            String busiErrMsg = "";
            if (!serviceResult.getSuccess()) {
                busiErrMsg = serviceResult.getMessage();
            }
            if (serviceResult.getResult() == null) {
                busiErrMsg = "赊账明细表为空。";
            }

            if (!StringUtil.isEmpty(busiErrMsg, true)) {
                try {
                    Cookie msgCookie = new Cookie("busiErrMsg", URLEncoder.encode(busiErrMsg, "utf-8"));
                    msgCookie.setPath("/");
                    response.addCookie(msgCookie);
                    Cookie fileDownloadCookie = new Cookie("fileDownload", "false");
                    fileDownloadCookie.setPath("/");
                    response.addCookie(fileDownloadCookie);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            } else {
                List<MemberCreditLog> list = serviceResult.getResult();
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                String createTime = "";
                for(MemberCreditLog membercredit:list){
                    Date create_time = membercredit.getCreatTime();
                    createTime = sdf.format(create_time);
                    membercredit.setCreateTime(createTime);
                }
                //订单明细
                this.exportMemberCreditLog(userAgent, response, list);
            }
        }
        
    }

    private void exportMemberCreditLog(String userAgent, HttpServletResponse response,
                                       List<MemberCreditLog> memberCreditLoglist) {
        ExcelConfig<MemberCreditLog> config = new ExcelConfig<MemberCreditLog>();
        config.setData(memberCreditLoglist);
        config.setExcelVersion(ExcelConfig.ExcelVersion.EXECL_VERSION_2007);
        config.setFileName("赊账明细信息"+ "-" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        config.setLineConfig(getLineConfig2());
        config.setSheetName("赊账用户明细列表");
        config.setUserAgent(userAgent);
        ExcelManager.export(response, config);
        
    }

    private void exportMemberCredit(String userAgent, HttpServletResponse response,
                                    List<MemberCredit> memberCreditlist) {
        for(MemberCredit memberCredit : memberCreditlist){
            if(memberCredit.getState() == 1){
                memberCredit.setStatusExp("未到期");
            }else if(memberCredit.getState() == 2){
                memberCredit.setStatusExp("已到期");
            }
        }
        ExcelConfig<MemberCredit> config = new ExcelConfig<MemberCredit>();
        config.setData(memberCreditlist);
        config.setExcelVersion(ExcelConfig.ExcelVersion.EXECL_VERSION_2007);
        config.setFileName("赊账信息"+ "-" + new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        config.setLineConfig(getLineConfig());
        config.setSheetName("赊账用户列表");
        config.setUserAgent(userAgent);
        ExcelManager.export(response, config);
        
    }

    private LinkedHashMap<String, CellConfig> getLineConfig() {
        LinkedHashMap<String, CellConfig> config = new LinkedHashMap<String, CellConfig>();
        CellConfig memberNameConfig = new CellConfig("会员");
        config.put("memberName", memberNameConfig);
        
        CellConfig realNameConfig = new CellConfig("姓名");
        config.put("realName", realNameConfig);
        
        CellConfig quotaConfig = new CellConfig("账期额度");
        config.put("quota", quotaConfig);
        
        CellConfig usedConfig = new CellConfig("已使用额度");
        config.put("used", usedConfig);
        
        CellConfig surplusConfig = new CellConfig("剩余额度");
        config.put("surplus", surplusConfig);
        
        CellConfig statusExpConfig = new CellConfig("状态");
        config.put("statusExp", statusExpConfig);
        
        CellConfig cycleConfig = new CellConfig("账期周期(天)");
        config.put("cycle", cycleConfig);
        
        CellConfig expireDateStrConfig = new CellConfig("到期日");
        config.put("expireDateStr", expireDateStrConfig);
        
        return config;
    }
    
    private LinkedHashMap<String, CellConfig> getLineConfig2() {
        LinkedHashMap<String, CellConfig> config = new LinkedHashMap<String, CellConfig>();
        CellConfig memberNameConfig = new CellConfig("会员");
        config.put("memberName", memberNameConfig);
        
        CellConfig orderSnConfig = new CellConfig("订单号");
        config.put("orderSn", orderSnConfig);
        
        CellConfig orderMoneyConfig = new CellConfig("订单金额");
        config.put("orderMoney", orderMoneyConfig);
        
        CellConfig balanceBeforeConfig = new CellConfig("赊账前额度");
        config.put("balanceBefore", balanceBeforeConfig);
        
        CellConfig balanceAfterConfig = new CellConfig("赊账后额度");
        config.put("balanceAfter", balanceAfterConfig);
        
        CellConfig createTimeConfig = new CellConfig("赊账后额度");
        config.put("createTime", createTimeConfig);
        
        return config;
    }

    
}
