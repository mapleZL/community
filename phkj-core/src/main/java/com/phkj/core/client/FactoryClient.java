package com.phkj.core.client;

import java.io.File;
import java.io.InputStream;

/**
 * @author ：zl
 * @date ：Created in 2019/5/8 11:43
 * @description：工厂client
 * @modified By：
 * @version: 0.0.1$
 */
public interface FactoryClient {

    /**
     * 获取文件url
     *
     * @param key
     * @return
     */
    String getUrl(String key);

    /**
     * 上传文件
     *
     * @param inputStream
     * @param key
     * @return
     */
    String uploadIfNotExits(InputStream inputStream, String key);

    /**
     * 上传文件
     *
     * @param file
     * @param key
     * @return
     */
    String uploadIfNotExits(File file, String key);

    /**
     * 上传文件
     *
     * @param imgString
     * @param dir
     * @return
     */
    String upload(String imgString, String dir);

    /**
     * 删除文件
     *
     * @param key
     * @return
     */
    boolean delete(String key);

    /**
     * 判断文件是否存在文件
     *
     * @param key
     * @return
     */
    boolean containsDoc(String key);

    /**
     * 下载文件
     *
     * @param ossKey
     * @return
     */
    byte[] download(String ossKey) throws Exception;
}
