package com.phkj.service.impl.file;

import com.phkj.core.client.FactoryClient;
import com.phkj.core.client.OSSClient;
import com.phkj.service.file.IFileService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
}