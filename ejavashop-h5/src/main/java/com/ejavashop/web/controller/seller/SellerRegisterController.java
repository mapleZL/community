package com.ejavashop.web.controller.seller;

import java.util.Date;
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
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.ConvertUtil;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.StringUtil;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.entity.member.Member;
import com.ejavashop.entity.seller.SellerApply;
import com.ejavashop.entity.system.Regions;
import com.ejavashop.service.member.IMemberService;
import com.ejavashop.service.seller.ISellerApplyService;
import com.ejavashop.service.seller.ISellerService;
import com.ejavashop.service.system.IRegionsService;
import com.ejavashop.web.controller.BaseController;
import com.ejavashop.web.util.UploadUtil;
import com.ejavashop.web.util.WebFrontSession;

/**
 * 商家入驻controller
 * 
 * @Filename: FrontSellerRegisterController.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Controller
public class SellerRegisterController extends BaseController {

    @Resource
    private ISellerApplyService sellerApplyService;
    @Resource
    private ISellerService      sellerService;
    @Resource
    private IMemberService      memberService;
    @Resource
    private IRegionsService     regionsService;

    /**
     * 商家入驻：入驻协议
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "/storeregister/apply.html", method = { RequestMethod.GET,RequestMethod.POST })
    public String step1(HttpServletRequest request, HttpServletResponse response,
                        Map<String, Object> dataMap) {
        String returnURL = "h5v1/seller/selleragreement";
        //Member member = WebFrontSession.getLoginedUser(request);
       // Member member = (Member) WebFrontSession.getObjFromSession(request, "h5Member");
        //WebFrontSession.putObjToSession(request, "h5Member", member);
        //是否已申请过
        //ServiceResult<SellerApply> sellerApplyResult = sellerApplyService
           // .getSellerApplyByUser(member.getId());
        //if (sellerApplyResult.getResult() != null) {
         //   SellerApply sellerApply = sellerApplyResult.getResult();
            // 如果已经申请过，显示申请的信息
          //  ServiceResult<Seller> sellerResult = sellerService
             //   .getSellerByMemberId(sellerApply.getUserId());
           // Seller seller = sellerResult.getResult();
         //   this.setDataMap(dataMap, sellerApply, seller.getName(), seller.getSellerName());
         //   returnURL = "h5/seller/sellerregister";
      //  } else {
            WebFrontSession.putObjToSession(request, "safekey", new Date());
      //  }
        return returnURL;
    }

    /**
     * 商家入驻：公司资质信息
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/storeregister/info.html", method = { RequestMethod.GET })
    public String step2(HttpServletRequest request, HttpServletResponse response, Map<String, Object> dataMap) {
        String returnURL = "h5v1/seller/sellerregister";
        if (request.getSession().getAttribute("safekey") == null) {
            return "redirect:/storeregister/apply.html";
        } else {
            request.getSession().removeAttribute("safekey");
        }

        ServiceResult<List<Regions>> provinceResult = regionsService.getRegionsByParentId(0);
        dataMap.put("provinceList", provinceResult.getResult());

        return returnURL;
    }

    @ExceptionHandler
    public @ResponseBody ModelAndView ExceptionHandler(Exception e, HttpServletRequest request) throws Exception {
        Map<String,Object> map = new HashMap<String,Object>(); 
        if (e instanceof MaxUploadSizeExceededException) {
            long maxSize = ((MaxUploadSizeExceededException) e).getMaxUploadSize();
            map.put("info", "上传文件太大，不能超过" + maxSize / 1024 + "k");
            map.put("backUrl", "javascript:history.back();");
            return new ModelAndView("/h5v1/commons/error",map);
        }
        else if (e instanceof RuntimeException) {
            map.put("info", "未选中文件");
            map.put("backUrl", "javascript:history.back();");
            return new ModelAndView("/h5v1/commons/error",map);
        }
        else {
            map.put("info", "上传失败");
            map.put("backUrl", "javascript:history.back();");
            return new ModelAndView("/h5v1/commons/error",map);
        }
    }

    /**
     * 商家入住申请保存信息<br>
     * @param request
     * @param response
     * @param sellerApply
     * @return
     */
    @RequestMapping(value = "/storeregister/doregister.html", method = { RequestMethod.POST })
    public String step2Save(HttpServletRequest request, HttpServletResponse response, Map<String, Object> dataMap, SellerApply sellerApply) {
    	Member member = (Member) WebFrontSession.getObjFromSession(request, "h5Member");
    	ServiceResult<Member> serviceResult = memberService.memberRegister(member);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            }
        }

        //营业执照扫描件
        String bli = UploadUtil.getInstance().uploadFile2ImageServerWithNologin("up_bussinessLicenseImage", request,member);
//        UploadUtil.getInstance().sellerApplyUploadFile2ImageServer("up_bussinessLicenseImage", request);
        //身份证正面
        String pcu = UploadUtil.getInstance().uploadFile2ImageServerWithNologin("up_organization", request,member);
//        UploadUtil.getInstance().sellerApplyUploadFile2ImageServer("up_personCardUp", request);
        //身份证反面
        String pdw = UploadUtil.getInstance().uploadFile2ImageServerWithNologin("up_taxLicense",request,member);
//        UploadUtil.getInstance().sellerApplyUploadFile2ImageServer("up_personCardDown", request);

        if (!StringUtil.isEmpty(bli, true)) {
            sellerApply.setBussinessLicenseImage(bli);
        }
        if (!StringUtil.isEmpty(pcu, true)) {
            sellerApply.setOrganization(pcu);
        }
        if (!StringUtil.isEmpty(pdw, true)) {
            sellerApply.setTaxLicense(pdw);
        }

        //Member member = WebFrontSession.getLoginedUser(request);

        Integer state = sellerApply.getState();
        ServiceResult<Boolean> serviceResult1 = null;
       // if (sellerApply.getId() != null && sellerApply.getId() > 0) {
            // 修改商家申请
           // sellerApply.setState(null);
           // serviceResult1 = sellerApplyService.updateSellerApplyForFront(sellerApply, userName,
              //  sellerName);
       // } else {
            sellerApply.setState(SellerApply.STATE_1_SEND);
            sellerApply.setType(2);
            // 保存商家申请
            serviceResult1 = sellerApplyService.saveSellerApplyForFront(sellerApply, member.getName(),"", member.getId());
      //  }

        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                sellerApply.setState(state);
                //this.setDataMap(dataMap, sellerApply, userName, sellerName); 
                this.setDataMap(dataMap, sellerApply, "", ""); 
                dataMap.put("message", serviceResult1.getMessage());
                return "h5v1/seller/sellerregister";
            }
        }
        try {
        	//注册成功 自动登录
			WebFrontSession.putMemberSession(request, member);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
        dataMap.put("message", "申请保存成功，请耐心等待。");
        return "h5v1/seller/sellerregistermsg";

    }

    private void setDataMap(Map<String, Object> dataMap, SellerApply sellerApply, String userName, String sellerName) {
        ServiceResult<List<Regions>> provinceResult = regionsService.getRegionsByParentId(0);
        dataMap.put("provinceList", provinceResult.getResult());

        ServiceResult<List<Regions>> companyCityResult = regionsService
            .getRegionsByParentId(ConvertUtil.toInt(sellerApply.getCompanyProvince(), null));
        dataMap.put("companyCityList", companyCityResult.getResult());

        ServiceResult<List<Regions>> bankCityResult = regionsService
            .getRegionsByParentId(ConvertUtil.toInt(sellerApply.getBankProvince(), null));
        dataMap.put("bankCityList", bankCityResult.getResult());

        dataMap.put("sellerApply", sellerApply);
        dataMap.put("userName", userName);
        dataMap.put("sellerName", sellerName);
    }

}
