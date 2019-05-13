package com.phkj.web.controller.file;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.phkj.core.ResponseStateEnum;
import com.phkj.core.response.ResponseUtil;
import com.phkj.service.file.IFileService;
import com.phkj.web.controller.BaseController;

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
            if(files == null || files.isEmpty()){
                return ResponseUtil.createResp(ResponseStateEnum.PARAM_EMPTY.getCode(), ResponseStateEnum.PARAM_EMPTY.getMsg(), true, null);
            }
            Set<String> set = fileService.uploadFiles(files);
            return ResponseUtil.createResp(ResponseStateEnum.STATUS_OK.getCode(), ResponseStateEnum.STATUS_OK.getMsg(), true, set);
        } catch (Exception e) {
            log.error("上传文件发生错误，请联系平台管理人员, exception:{}", e);
            return ResponseUtil.createResp(ResponseStateEnum.STATUS_SERVER_ERROR.getCode(), ResponseStateEnum.STATUS_SERVER_ERROR.getMsg(), false, new ArrayList<>());
        }
    }

}
