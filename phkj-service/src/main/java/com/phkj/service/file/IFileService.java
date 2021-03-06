package com.phkj.service.file;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;
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

    /**
     * 上传base64文件
     *
     * @param imgString
     * @return
     */
    String upload(String imgString);

    /**
     * 上传base64文件
     *
     * @param file
     * @return
     */
    String upload(File file);

    /**
     * 上传文件
     *
     * @param inputStream
     * @return
     */
    String upload(InputStream inputStream);
}
