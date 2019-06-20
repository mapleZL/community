package com.phkj.web.controller.file;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.phkj.core.HttpJsonResult;
import com.phkj.core.ResponseStateEnum;
import com.phkj.core.response.ResponseUtil;
import com.phkj.service.file.IFileService;
import com.phkj.web.controller.BaseController;
import com.phkj.web.util.BASE64Utils;

/**
 * 操作文件相关动作
 *
 * @Filename: FIleController.java
 * @Version: 1.0
 * @Author: zl
 * @Email:
 */
@Controller
@RequestMapping(value = "/admin/file")
public class FIleController extends BaseController {
    private static Logger log = LogManager.getLogger(FIleController.class);

    @Resource
    private IFileService fileService;

    /**
     * 批量上传图片
     *
     * @return
     */
    @RequestMapping("/uploadFiles")
    @ResponseBody
    public ResponseUtil uploadFiles(@RequestParam List<MultipartFile> files) {
        try {
            if (files == null || files.isEmpty()) {
                return ResponseUtil.createResp(ResponseStateEnum.PARAM_EMPTY.getCode(), ResponseStateEnum.PARAM_EMPTY.getMsg(), true, null);
            }
            Set<String> set = fileService.uploadFiles(files);
            return ResponseUtil.createResp(ResponseStateEnum.STATUS_OK.getCode(), ResponseStateEnum.STATUS_OK.getMsg(), true, set);
        } catch (Exception e) {
            log.error("上传文件发生错误，请联系平台管理人员, exception:{}", e);
            return ResponseUtil.createResp(ResponseStateEnum.STATUS_SERVER_ERROR.getCode(), ResponseStateEnum.STATUS_SERVER_ERROR.getMsg(), false, new ArrayList<>());
        }
    }

    /**
     * 上传带有前缀的base64字符串
     *
     * @return
     */
    @RequestMapping("/upload")
    @ResponseBody
    public ResponseUtil uploadFile(String base64) {
        try {
            if (StringUtils.isBlank(base64)) {
                return ResponseUtil.createResp(ResponseStateEnum.PARAM_EMPTY.getCode(), ResponseStateEnum.PARAM_EMPTY.getMsg(), false, null);
            }
            String url = fileService.upload(base64);
            return ResponseUtil.createResp(ResponseStateEnum.STATUS_OK.getCode(), ResponseStateEnum.STATUS_OK.getMsg(), true, url);
        } catch (Exception e) {
            log.error("上传文件发生错误，请联系平台管理人员, exception:{}", e);
            return ResponseUtil.createResp(ResponseStateEnum.STATUS_SERVER_ERROR.getCode(), ResponseStateEnum.STATUS_SERVER_ERROR.getMsg(), false, null);
        }
    }

//    /**
//     * 上传图片
//     *
//     * @return
//     */
//    @RequestMapping("/upload3")
//    @ResponseBody
//    public ResponseUtil uploadFile2(String base64) {
//        try {
//            if (StringUtils.isBlank(base64)) {
//                return ResponseUtil.createResp(ResponseStateEnum.PARAM_EMPTY.getCode(), ResponseStateEnum.PARAM_EMPTY.getMsg(), false, null);
//            }
//            File file = BASE64Utils.base64ToInputStream(base64);
//            if (file == null) {
//                return ResponseUtil.createResp(ResponseStateEnum.FILE_ERROR.getCode(), ResponseStateEnum.FILE_ERROR.getMsg(), false, null);
//            }
//            String url = fileService.upload(file);
//            return ResponseUtil.createResp(ResponseStateEnum.STATUS_OK.getCode(), ResponseStateEnum.STATUS_OK.getMsg(), true, url);
//        } catch (Exception e) {
//            log.error("上传文件发生错误，请联系平台管理人员, exception:{}", e);
//            return ResponseUtil.createResp(ResponseStateEnum.STATUS_SERVER_ERROR.getCode(), ResponseStateEnum.STATUS_SERVER_ERROR.getMsg(), false, null);
//        }
//    }

    /**
     * 上传不带前缀的base64字符串
     *
     * @return
     */
    @RequestMapping("/upload2")
    @ResponseBody
    public ResponseUtil uploadFile2(String base64) {
        try {
            if (StringUtils.isBlank(base64)) {
                return ResponseUtil.createResp(ResponseStateEnum.PARAM_EMPTY.getCode(), ResponseStateEnum.PARAM_EMPTY.getMsg(), false, null);
            }
            InputStream inputStream = BASE64Utils.base64ToInputStream(base64);
            if (inputStream == null) {
                return ResponseUtil.createResp(ResponseStateEnum.FILE_ERROR.getCode(), ResponseStateEnum.FILE_ERROR.getMsg(), false, null);
            }
            String url = fileService.upload(inputStream);
            return ResponseUtil.createResp(ResponseStateEnum.STATUS_OK.getCode(), ResponseStateEnum.STATUS_OK.getMsg(), true, url);
        } catch (Exception e) {
            log.error("上传文件发生错误，请联系平台管理人员, exception:{}", e);
            return ResponseUtil.createResp(ResponseStateEnum.STATUS_SERVER_ERROR.getCode(), ResponseStateEnum.STATUS_SERVER_ERROR.getMsg(), false, null);
        }
    }

    /**
     * ajax商品图片上传
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/uploadPics", method = {RequestMethod.POST})
    public @ResponseBody
    Object uploadImage(MultipartHttpServletRequest request,
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

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public @ResponseBody
    Object upload(MultipartFile file, HttpServletRequest request,
                  HttpServletResponse response) {
        HttpJsonResult<Map<String, Object>> jsonResult = new HttpJsonResult<Map<String, Object>>();
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            String url = fileService.uploadFile(file);
            result.put("name", file.getOriginalFilename());
            result.put("url", url);
            jsonResult.setData(result);
        } catch (Exception e) {
            log.error("上传文件出错");
            jsonResult.setMessage(e.getMessage());
        }
        return jsonResult;
    }

}
