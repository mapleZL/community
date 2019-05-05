package com.ejavashop.web.controller.pcindex;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.StringUtil;
import com.ejavashop.entity.seller.SellerUser;
import com.ejavashop.entity.shopm.pcseller.PcSellerIndex;
import com.ejavashop.service.pcseller.IPcSellerIndexService;
import com.ejavashop.web.controller.BaseController;
import com.ejavashop.web.util.UploadUtil;
import com.ejavashop.web.util.WebSellerSession;

/**
 * PC端商家首页信息管理controller
 *
 * @Filename: SellerUserController.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Controller
@RequestMapping(value = "seller/pcindex/sellerinfo")
public class SellerPcIndexController extends BaseController {
    Logger                        log = Logger.getLogger(this.getClass());
    @Resource
    private IPcSellerIndexService pcSellerIndexService;

    /**
     * PC端商家首页信息
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "", method = { RequestMethod.GET })
    public String updatePassword(HttpServletRequest request,
                                 Map<String, Object> dataMap) throws Exception {
        SellerUser sellerUser = WebSellerSession.getSellerUser(request);

        ServiceResult<PcSellerIndex> indexResult = pcSellerIndexService
            .getPcSellerIndexBySellerId(sellerUser.getSellerId());
        dataMap.put("pcSellerIndex", indexResult.getResult());

        return "seller/pcindex/sellerinfo";
    }

    /**
     * PC端商家首页信息修改
     * @param config
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "update", method = { RequestMethod.POST })
    public String create(PcSellerIndex pcSellerIndex, HttpServletRequest request,
                         Map<String, Object> dataMap) {

        SellerUser sellerUser = WebSellerSession.getSellerUser(request);
        Integer userId = sellerUser.getId();
        pcSellerIndex.setCreateUserId(userId);
        pcSellerIndex.setCreateUserName(sellerUser.getName());
        pcSellerIndex.setUpdateUserId(sellerUser.getId());
        pcSellerIndex.setUpdateUserName(sellerUser.getName());
        pcSellerIndex.setSellerId(sellerUser.getSellerId());

        // 上传图片
        String image = UploadUtil.getInstance().advUploadFile2ImageServer("imageFile", request);
        if (image != null && !"".equals(image)) {
            pcSellerIndex.setLogo(image);
        }

        ServiceResult<Boolean> serviceResult = null;

        if (!StringUtil.isEmpty(pcSellerIndex.getDetail())) {
            String description = pcSellerIndex.getDetail();
            description = description.replaceAll(System.getProperty("line.separator"), "");
            pcSellerIndex.setDetail(description);
        }

        if (pcSellerIndex.getId() == null || pcSellerIndex.getId().intValue() == 0) {
            pcSellerIndex.setId(null);
            serviceResult = pcSellerIndexService.savePcSellerIndex(pcSellerIndex);
        } else {
            serviceResult = pcSellerIndexService.updatePcSellerIndex(pcSellerIndex);
        }

        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                dataMap.put("pcSellerIndex", pcSellerIndex);
                dataMap.put("message", serviceResult.getMessage());
                return "seller/pcindex/sellerinfo";
            }
        }
        return "redirect:";
    }
}
