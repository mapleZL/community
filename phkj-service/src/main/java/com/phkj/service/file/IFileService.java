package com.phkj.service.file;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

/**
 * @author ：zl
 * @date ：Created in 2019/5/11 21:24
 * @description：文件操作
 * @modified By：
 * @version: 0.0.1$
 */
public interface IFileService {

    /**
     * 批量上传文件
     *
     * @param files
     * @return
     */
    Set<String> uploadFiles(List<MultipartFile> files);

    /**
     * 上传文件
     *
     * @param file
     * @return
     */
    String uploadFile(MultipartFile file);
}
