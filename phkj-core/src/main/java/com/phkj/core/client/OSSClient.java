package com.phkj.core.client;

import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.PutObjectResult;
import com.phkj.core.freemarkerutil.DomainUrlUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;

/**
 * @Author:zl
 * @Despriction:　OSS客户端
 * @Date: Create in 2019/5/8 11:48
 */
public class OSSClient implements FactoryClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(OSSClient.class);

    private static OSSClient client;

    public OSSClient() {

    }

    /**
     * create by: zl
     * description: 获取客户端
     * create time:
     *
     * @return
     * @Param:
     */
    private com.aliyun.oss.OSSClient getClient() {
        com.aliyun.oss.OSSClient ossClient = null;
        try {
            ossClient = new com.aliyun.oss.OSSClient(DomainUrlUtil.OSS_ENDPOINT, DomainUrlUtil.OSS_ACCESSKEYID,
                    DomainUrlUtil.OSS_ACCESSKEYSECRET);
        } catch (Exception e) {
            LOGGER.error("OSSClient bean create error.", e);
            ossClient = null;
        }
        return ossClient;
    }

    /**
     * create by: zl
     * description: 获取指定文档的分享url,若VALIDITY值为-1,表示永久生效
     * create time:
     *
     * @return
     * @Param: objKey
     */
    public String getUrl(String objKey) {
        Date expiration = new Date(System.currentTimeMillis() + ClientCst.validity);
        com.aliyun.oss.OSSClient ossClient = null;
        URL url;
        try {
            ossClient = getClient();
            if (null == ossClient) {
                return null;
            }
            boolean flag = ossClient.doesObjectExist(DomainUrlUtil.OSS_BUCKETNAME, objKey);
            if (!flag) {
                LOGGER.info(objKey + " 代表的object在bucket：" + ClientCst.oss_bucketName + " 中不存在。");
                return null;
            }
            if (ClientCst.validity == -1) {
                expiration = new Date(Long.MAX_VALUE);
            }
            url = ossClient.generatePresignedUrl(DomainUrlUtil.OSS_BUCKETNAME, objKey, expiration);
        } finally {
            if (ossClient != null) {
                try {
                    ossClient.shutdown();
                } catch (Exception e) {
                }
            }
        }
        return url.toString();
    }

    /**
     * create by: zl
     * description: 上传文件
     * create time:
     *
     * @return
     * @Param: inputStream
     * @Param: code
     */
    public String uploadIfNotExits(InputStream inputStream, String code) {
        com.aliyun.oss.OSSClient ossClient = null;
        String url = null;
        try {
            ossClient = getClient();
            if (null == ossClient) {
                return null;
            }
//            boolean isContains = containsDoc(code);
//            if (isContains) {
//                return code;
//            }
            ossClient.putObject(DomainUrlUtil.OSS_BUCKETNAME, code, inputStream);
            url = getUrl(code);
            LOGGER.info("文件上传后url：" + url);
        } catch (Exception e) {
            LOGGER.error("exception:{}", e);
        } finally {
            if (ossClient != null) {
                try {
                    ossClient.shutdown();
                } catch (Exception e) {
                }
            }
        }
        return url;
    }

    /**
     * create by: zl
     * description: 上传单个文件，以code保存
     * create time:
     *
     * @return
     * @Param: inputStream
     * @Param: code
     */
    public boolean upload(InputStream inputStream, String code) {
        com.aliyun.oss.OSSClient ossClient = null;
        try {
            ossClient = getClient();
            if (null == ossClient) {
                return false;
            }
            PutObjectResult putObjectResult = ossClient.putObject(DomainUrlUtil.OSS_BUCKETNAME, code, inputStream);
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        return true;
    }

    /**
     * create by: zl
     * description: 删除文件
     * create time:
     *
     * @return
     * @Param: code
     */
    public boolean delete(String code) {
        com.aliyun.oss.OSSClient ossClient = null;
        try {
            ossClient = getClient();
            if (null == ossClient) {
                return false;
            }
            boolean flag = ossClient.doesObjectExist(DomainUrlUtil.OSS_BUCKETNAME, code);
            if (!flag) {
                LOGGER.info(code + " 代表的object在bucket：" + DomainUrlUtil.OSS_BUCKETNAME + " 中不存在。");
                return false;
            }
            ossClient.deleteObject(DomainUrlUtil.OSS_BUCKETNAME, code);
        } finally {
            if (ossClient != null) {
                try {
                    ossClient.shutdown();
                } catch (Exception e) {
                }
            }
        }
        return true;
    }

    /**
     * create by: zl
     * description: 判断文件是否存在
     * create time:
     *
     * @return
     * @Param: code
     */
    public boolean containsDoc(String code) {
        com.aliyun.oss.OSSClient ossClient = null;
        try {
            ossClient = getClient();
            if (null == ossClient) {
                return false;
            }
            return ossClient.doesObjectExist(DomainUrlUtil.OSS_BUCKETNAME, code);
        } finally {
            try {
                if (ossClient != null) {
                    ossClient.shutdown();
                }
            } catch (Exception e) {
            }
        }
    }

    /**
     * create by: zl
     * description: 下载文件
     * create time:
     *
     * @return
     * @Param: ossKey
     */
    public byte[] download(String ossKey) throws Exception {
        com.aliyun.oss.OSSClient ossClient = null;
        InputStream inputStream = null;
        long startTime = 0;
        long endTime = 0;
        try {
            startTime = System.currentTimeMillis();
            ossClient = getClient();
            endTime = System.currentTimeMillis();
            LOGGER.info("获取oss客户端耗时：", endTime - startTime);

            startTime = System.currentTimeMillis();
            OSSObject ossObject = ossClient.getObject(DomainUrlUtil.OSS_BUCKETNAME, ossKey);
            inputStream = ossObject.getObjectContent();

            ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
            byte[] buff = new byte[1024];
            int rc = 0;
            while ((rc = inputStream.read(buff, 0, 1024)) > 0) {
                swapStream.write(buff, 0, rc);
            }
            endTime = System.currentTimeMillis();
            LOGGER.info("文件大小：" + ossObject.getObjectMetadata().getContentLength() + "，获取oss数据耗时：",
                    endTime - startTime);
            return swapStream.toByteArray();
        } catch (Throwable e) {
            LOGGER.error("download exception!", e);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (ossClient != null) {
                    ossClient.shutdown();
                }
            } catch (IOException e) {
            }
        }
        return null;
    }

}