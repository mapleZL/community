package com.phkj.web.controller.product;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.phkj.core.ConstantsEJS;
import com.phkj.core.HttpJsonResult;
import com.phkj.service.file.IFileService;
import com.phkj.service.product.IStAppletProductService;
import com.phkj.web.controller.BaseController;

/**
 * 
 *                       
 * @Filename: ProductController.java
 * @Version: 1.0
 * @date: 2019年5月20日
 * @Author: 陆帅 * @Email: lu1632278229@sina.cn
 *
 */
@Controller
@RequestMapping(value = "admin/product")
public class ProductController extends BaseController {

    @Autowired
    IStAppletProductService productService;
    @Resource
    private IFileService    fileService;

    @RequestMapping(value = "/add", method = { RequestMethod.GET })
    public String getList(Map<String, Object> dataMap) throws Exception {
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return "admin/product/pdt/productadd";
    }

    /**
     * ajax商品图片上传
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/uploadFiles", method = { RequestMethod.POST })
    public @ResponseBody Object uploadImage(MultipartHttpServletRequest request,
                                            HttpServletResponse response, String fileIndex) {
        log.info("UploadImageController uploadImage start");
        HttpJsonResult<Map<String, Object>> jsonResult = new HttpJsonResult<Map<String, Object>>();
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            MultiValueMap<String, MultipartFile> map = request.getMultiFileMap();
            if (map != null) {
                Iterator<String> iter = map.keySet().iterator();
                while (iter.hasNext()) {
                    String str = (String) iter.next();
                    List<MultipartFile> fileList = map.get(str);
                    for (MultipartFile multipartFile : fileList) {
                        String url = fileService.uploadFile(multipartFile);

                        result.put("url", url);
                        result.put("fileIndex", fileIndex);
                        jsonResult.setData(result);

                        return jsonResult;
                    }
                }
            }
        } catch (Exception e) {
            log.error("[shoppingmall-memer-web][UploadImageController][uploadImage] exception:", e);
            jsonResult.setMessage(e.getMessage());
            return jsonResult;
        }
        return null;
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
}
