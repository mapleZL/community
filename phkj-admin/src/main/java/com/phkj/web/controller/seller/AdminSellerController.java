package com.phkj.web.controller.seller;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import com.phkj.core.ResponseStateEnum;
import com.phkj.core.ServiceResult;
import com.phkj.core.response.ResponseUtil;
import com.phkj.entity.seller.StAppletSellerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.phkj.core.StringUtil;
import com.phkj.entity.seller.StAppletSeller;
import com.phkj.entity.system.SystemAdmin;
import com.phkj.service.seller.IStAppletSellerService;
import com.phkj.web.controller.BaseController;
import com.phkj.web.util.WebAdminSession;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 *                       
 * @Filename: AdminSellerController.java
 * @Version: 1.0
 * @date: 2019年5月20日
 * @Author: 陆帅 * @Email: lu1632278229@sina.cn
 *
 */
@Controller
@RequestMapping(value = "admin/seller/manage")
public class AdminSellerController extends BaseController {

    @Autowired
    private IStAppletSellerService sellerService;

    @RequestMapping(value = "/edit", method = { RequestMethod.GET })
    public String create(HttpServletRequest request, Map<String, Object> dataMap) {
        SystemAdmin adminUser = WebAdminSession.getAdminUser(request);
        StAppletSeller seller = sellerService.getSellerByMemberId(adminUser.getId());
        dataMap.put("seller", seller);
        String url = "/admin/seller/audit/sellerapplyadd";
        if (seller != null) {
            url = "/admin/seller/audit/sellerapplyedit";
        }
        return url;
    }




    /**
     * 获取商铺详情
     *
     * @param sellerId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/info", method = {RequestMethod.GET})
    @ResponseBody
    public ResponseUtil detail(Integer sellerId) {
        if (sellerId == null || sellerId == 0) {
            return ResponseUtil.createResp(ResponseStateEnum.PARAM_EMPTY.getCode(), "sellerId is blank", false, null);
        }
        ServiceResult<StAppletSellerVO> result = sellerService.getSellerDetail(sellerId);
        return ResponseUtil.createResp(result.getCode(), result.getMessage(), result.getSuccess(), result.getResult());
    }

    /**
     * 商铺完善信息
     * @param seller
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "/create", method = { RequestMethod.POST })
    public String create(StAppletSeller seller, HttpServletRequest request,
                         Map<String, Object> dataMap) {
        SystemAdmin adminUser = WebAdminSession.getAdminUser(request);
        //        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        //        CommonsMultipartFile multipartFile = (CommonsMultipartFile) multipartRequest
        //            .getFile("up_bussinessLicenseImage");
        //营业执照扫描件
        //        String bli = fileService.uploadFile(multipartFile);
        String bli = request.getParameter("picimg2");
        if (!StringUtil.isEmpty(bli)) {
            seller.setBussinessLicense(bli);
        }

        //税务登记证
        //        multipartFile = (CommonsMultipartFile) multipartRequest
        //                .getFile("up_taxLicense");
        //        String bls = fileService.uploadFile(multipartFile);
        String bls = request.getParameter("picimg3");
        if (!StringUtil.isEmpty(bls)) {
            seller.setTaxLicense(bls);
        }

        //组织机构代码证
        //        multipartFile = (CommonsMultipartFile) multipartRequest
        //                .getFile("up_organization");
        //        String org = fileService.uploadFile(multipartFile);
        String org = request.getParameter("picimg4");
        if (!StringUtil.isEmpty(org)) {
            seller.setOrganization(org);
        }
        seller.setAuditStatus(2);
        seller.setMemberId(adminUser.getId());
        seller.setCreateTime(new Date());
        String name = request.getParameter("name");
        String sellerName = request.getParameter("sellerName");
        String imageSrc = request.getParameter("imageSrc");
        seller.setSellerLogo(imageSrc);
        seller.setName(name);
        seller.setSellerName(sellerName);
        seller.setSellerCode(UUID.randomUUID().toString());
        sellerService.saveStAppletSeller(seller);

        dataMap.put("seller", seller);
        return "/admin/seller/audit/sellerapplyedit";
    }

    /**
     * 商铺信息修改
     * @param seller
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "/update", method = { RequestMethod.POST })
    public String update(StAppletSeller seller, HttpServletRequest request,
                         Map<String, Object> dataMap) {
        //        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        //        CommonsMultipartFile multipartFile = (CommonsMultipartFile) multipartRequest
        //                .getFile("up_bussinessLicenseImage");
        //营业执照扫描件
        String bli = request.getParameter("picimg2");
        if (!StringUtil.isEmpty(bli)) {
            seller.setBussinessLicense(bli);
        }

        //税务登记证
        //        multipartFile = (CommonsMultipartFile) multipartRequest
        //                .getFile("up_taxLicense");
        //        String bls = fileService.uploadFile(multipartFile);
        String bls = request.getParameter("picimg3");
        if (!StringUtil.isEmpty(bls)) {
            seller.setTaxLicense(bls);
        }

        //组织机构代码证
        //        multipartFile = (CommonsMultipartFile) multipartRequest
        //                .getFile("up_organization");
        //        String org = fileService.uploadFile(multipartFile);
        String org = request.getParameter("picimg4");
        if (!StringUtil.isEmpty(org)) {
            seller.setOrganization(org);
        }
        String name = request.getParameter("name");
        String sellerName = request.getParameter("sellerName");
        String imageSrc = request.getParameter("imageSrc");
        seller.setSellerLogo(imageSrc);
        seller.setName(name);
        seller.setSellerName(sellerName);
        String id = request.getParameter("id");
        seller.setId(Integer.valueOf(id));
        sellerService.updateStAppletSeller(seller);

        dataMap.put("seller", seller);
        return "/admin/seller/audit/sellerapplyedit";
    }

}
