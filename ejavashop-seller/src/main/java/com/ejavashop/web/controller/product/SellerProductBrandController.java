package com.ejavashop.web.controller.product;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ejavashop.core.ConstantsEJS;
import com.ejavashop.core.HttpJsonResult;
import com.ejavashop.core.PagerInfo;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.WebUtil;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.core.freemarkerutil.DomainUrlUtil;
import com.ejavashop.entity.product.ProductBrand;
import com.ejavashop.entity.seller.SellerApplyBrand;
import com.ejavashop.entity.seller.SellerUser;
import com.ejavashop.entity.system.Code;
import com.ejavashop.service.product.ISellerApplyBrandService;
import com.ejavashop.web.controller.BaseController;
import com.ejavashop.web.util.UploadUtil;
import com.ejavashop.web.util.WebSellerSession;
import com.ejavashop.web.util.freemarkerutil.CodeManager;

/**
 * 商家商品品牌管理
 */
@Controller
@RequestMapping(value = "seller/product/brand")
public class SellerProductBrandController extends BaseController {

    @Resource
    private ISellerApplyBrandService sellerApplyBrandService;

    /**
     * 默认，显示全部状态的品牌
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "", method = { RequestMethod.GET })
    public String index(Map<String, Object> dataMap) throws Exception {
        dataMap.put("q_useYn", "1");
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return "seller/product/brand/brandlist";
    }

    @RequestMapping(value = "list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<SellerApplyBrand>> list(HttpServletRequest request,
                                                                     Map<String, Object> dataMap) {
        HttpJsonResult<List<SellerApplyBrand>> jsonResult = new HttpJsonResult<List<SellerApplyBrand>>();
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);

        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        SellerUser sellerUser = WebSellerSession.getSellerUser(request);
        if (null == sellerUser) {
            jsonResult.setMessage("登录超时，请重新登录");
            jsonResult.setBackUrl(DomainUrlUtil.getEJS_URL_RESOURCES() + "/seller/login.html");
            return jsonResult;
        }
        queryMap.put("q_sellerId", String.valueOf(sellerUser.getSellerId()));
        ServiceResult<List<SellerApplyBrand>> serviceResult = sellerApplyBrandService.page(queryMap,
            pager);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }

        jsonResult.setRows((List<SellerApplyBrand>) serviceResult.getResult());
        jsonResult.setTotal(pager.getRowsCount());
        return jsonResult;
    }

    @RequestMapping(value = "add", method = { RequestMethod.GET })
    public String add(Map<String, Object> dataMap) {
        Code code = new Code();
        dataMap.put("code", code);
        return "seller/product/brand/brandadd";
    }

    @RequestMapping(value = "create", method = { RequestMethod.POST })
    @ResponseBody
    public HttpJsonResult<Object> create(SellerApplyBrand brand,
                                         MultipartHttpServletRequest request,
                                         Map<String, Object> dataMap) throws IOException {
        HttpJsonResult<Object> jsonResult = new HttpJsonResult<Object>();
        SellerUser sellerUser = WebSellerSession.getSellerUser(request);
        if (null == sellerUser) {
            jsonResult.setMessage("登录超时，请重新登录");
            jsonResult.setBackUrl(DomainUrlUtil.getEJS_URL_RESOURCES() + "/seller/login.html");
            return jsonResult;
        }
        //上传品牌图片
        MultipartFile multipartFile = request.getFile("imageFile");
        if (null != multipartFile && multipartFile.getSize() > 0) {
            String originalFilename = multipartFile.getOriginalFilename();
            File tmpFile = new File(
                buildImgPath(request) + "/" + UUID.randomUUID() + originalFilename
                    .substring(originalFilename.lastIndexOf("."), originalFilename.length()));
            if (!multipartFile.isEmpty()) {
                byte[] bytes = multipartFile.getBytes();
                BufferedOutputStream stream = new BufferedOutputStream(
                    new FileOutputStream(tmpFile));
                stream.write(bytes);
                stream.close();
            }

            //String url = DomainUrlUtil.getEJS_IMAGE_RESOURCES();
            String url = UploadUtil.getInstance().brandUploaderImage(tmpFile, request, true);
            tmpFile.deleteOnExit();
            brand.setImage(url);
        }

        brand.setCreateId(sellerUser.getId());
        brand.setSellerId(sellerUser.getSellerId());

        brand.setState(ProductBrand.Status.SUCCESS.getValue());
        ServiceResult<Boolean> serviceResult = sellerApplyBrandService.save(brand);
        if (!serviceResult.getSuccess()) {
            jsonResult.setMessage(serviceResult.getMessage());
        }
        CodeManager.init();
        return jsonResult;
    }

    private String buildImgPath(HttpServletRequest request) {
        String path = "upload";
        SimpleDateFormat formater = new SimpleDateFormat("yyyyMMdd");
        path += "/" + formater.format(new Date());
        path = request.getRealPath(path);
        File dir = new File(path);
        if (!dir.exists()) {
            try {
                dir.mkdirs();
            } catch (Exception e) {
                log.error("error", e);
            }
        }
        return path;
    }

    @RequestMapping(value = "edit", method = { RequestMethod.GET })
    public String edit(@RequestParam(value = "id", required = true) Integer id,
                       Map<String, Object> dataMap) {
        ServiceResult<SellerApplyBrand> serviceResult = sellerApplyBrandService.getById(id);
        if (!serviceResult.getSuccess()) {
            throw new RuntimeException(serviceResult.getMessage());
        }
        dataMap.put("brand", serviceResult.getResult());
        return "seller/product/brand/brandedit";
    }

    @RequestMapping(value = "update", method = { RequestMethod.POST })
    @ResponseBody
    public HttpJsonResult<Object> update(SellerApplyBrand brand,
                                         MultipartHttpServletRequest request) throws IOException {
        HttpJsonResult<Object> jsonResult = new HttpJsonResult<Object>();
        SellerUser sellerUser = WebSellerSession.getSellerUser(request);
        if (null == sellerUser) {
            jsonResult.setMessage("登录超时，请重新登录");
            jsonResult.setBackUrl(DomainUrlUtil.getEJS_URL_RESOURCES() + "/seller/login.html");
            return jsonResult;
        }
        //品牌图片
        MultipartFile multipartFile = request.getFile("imageFile");
        if (null != multipartFile && multipartFile.getSize() > 0) {
            String originalFilename = multipartFile.getOriginalFilename();
            File tmpFile = new File(
                buildImgPath(request) + "/" + UUID.randomUUID() + originalFilename
                    .substring(originalFilename.lastIndexOf("."), originalFilename.length()));
            if (!multipartFile.isEmpty()) {
                byte[] bytes = multipartFile.getBytes();
                BufferedOutputStream stream = new BufferedOutputStream(
                    new FileOutputStream(tmpFile));
                stream.write(bytes);
                stream.close();
            }

            String url = UploadUtil.getInstance().brandUploaderImage(tmpFile, request, true);//DomainUrlUtil.getEJS_IMAGE_RESOURCES();
            tmpFile.deleteOnExit();
            brand.setImage(url);
        }

        brand.setUpdateId(sellerUser.getId());
        ServiceResult<Boolean> serviceResult = sellerApplyBrandService.update(brand);
        if (!serviceResult.getSuccess()) {
            jsonResult.setMessage(serviceResult.getMessage());
        }
        CodeManager.init();
        return jsonResult;
    }

    @RequestMapping(value = "del", method = RequestMethod.POST)
    public @ResponseBody HttpJsonResult<Object> del(HttpServletRequest request,
                                                    @RequestParam("id") Integer id) {
        HttpJsonResult<Object> jsonResult = new HttpJsonResult<Object>();
        ServiceResult<Boolean> serviceResult = sellerApplyBrandService.del(id);
        if (!serviceResult.getSuccess()) {
            jsonResult.setMessage(serviceResult.getMessage());
        }
        return jsonResult;
    }

    /**
     * 商家提交平台审核品牌
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "commit", method = RequestMethod.POST)
    public @ResponseBody HttpJsonResult<Object> commit(HttpServletRequest request,
                                                       @RequestParam("id") Integer id) {
        HttpJsonResult<Object> jsonResult = new HttpJsonResult<Object>(true);
        ServiceResult<Boolean> serviceResult = sellerApplyBrandService.commit(id);
        if (!serviceResult.getSuccess()) {
            jsonResult.setMessage(serviceResult.getMessage());
        }
        return jsonResult;
    }

}
