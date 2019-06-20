package com.phkj.service.impl.file;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.phkj.core.client.FactoryClient;
import com.phkj.core.client.OSSClient;
import com.phkj.service.file.IFileService;

@Service(value = "fileService")
public class FileServiceImpl implements IFileService {
    private static Logger log = LogManager.getLogger(FileServiceImpl.class);

    /**
     * 批量上传文件
     *
     * @param files
     * @return
     */
    @Override
    public Set<String> uploadFiles(List<MultipartFile> files) {
        try {
            FactoryClient client = new OSSClient();
            Set<String> set = new HashSet<>();
            for (MultipartFile file : files) {
                InputStream inputStream = file.getInputStream();
                String originalFilename = file.getOriginalFilename();
                String url = client.uploadIfNotExits(inputStream, originalFilename);
                set.add(url);
            }
            return set;
        } catch (IOException e) {
            log.error("文件上传异常,exception:{}", e);
            return new HashSet<>();
        }
    }

    /**
     * 上传文件
     *
     * @param file
     * @return
     */
    @Override
    public String uploadFile(MultipartFile file) {
        try {
            FactoryClient client = new OSSClient();
            InputStream inputStream = file.getInputStream();
            String originalFilename = file.getOriginalFilename();
            return client.uploadIfNotExits(inputStream, originalFilename);
        } catch (IOException e) {
            log.error("文件上传异常,exception:{}", e);
            return null;
        }
    }

    @Override
    public String upload(String imgString) {
        try {
            FactoryClient client = new OSSClient();
            return client.upload(imgString, "img");
        } catch (Exception e) {
            log.error("文件上传异常,exception:{}", e);
            return null;
        }
    }

    @Override
    public String upload(File file) {
        try {
            FactoryClient client = new OSSClient();
            String filename = getFilename("png");
            return client.uploadIfNotExits(file, filename);
        } catch (Exception e) {
            log.error("文件上传异常,exception:{}", e);
            return null;
        }
    }

    @Override
    public String upload(InputStream inputStream) {
        try {
            FactoryClient client = new OSSClient();
            String filename = getFilename("png");
            return client.uploadIfNotExits(inputStream, filename);
        } catch (Exception e) {
            log.error("文件上传异常,exception:{}", e);
            return null;
        }
    }

    // 生成文件名加目录
    public String getFilename(String prefix) {
        // 使用uuid生成唯一文件名
        String uuid = UUID.randomUUID().toString();
        return uuid + "." + prefix;
    }
}