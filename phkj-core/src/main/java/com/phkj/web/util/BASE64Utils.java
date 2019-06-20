package com.phkj.web.util;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import sun.misc.BASE64Decoder;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * @author ：zl
 * @date ：Created in 2019/6/19 23:40
 * @description：base64工具类
 * @modified By：
 * @version: 0.0.1$
 */
public class BASE64Utils {
    private static Logger log = LogManager.getLogger(BASE64Utils.class);

    public static File base64ToInputStream2(String strBase64) {
        String string = strBase64;
        String fileName = "test.png";
        try {
            // 解码，然后将字节转换为文件
            byte[] bytes = new BASE64Decoder().decodeBuffer(string);   //将字符串转换为byte数组
            InputStream in = new ByteArrayInputStream(bytes);
            byte[] buffer = new byte[1024];
            FileOutputStream out = new FileOutputStream(fileName);
            int bytesum = 0;
            int byteread;
            while ((byteread = in.read(buffer)) != -1) {
                bytesum += byteread;
                out.write(buffer, 0, byteread); //文件写操作
            }
            File file = new File(fileName);
            return file;
        } catch (Exception e) {
            log.error("base64转文件异常,exception: {}", e);
            return null;
        }
    }

    public static InputStream base64ToInputStream(String strBase64) {
        String string = strBase64;
        try {
            // 解码，然后将字节转换为文件
            byte[] bytes = new BASE64Decoder().decodeBuffer(string);   //将字符串转换为byte数组
            InputStream in = new ByteArrayInputStream(bytes);
            return in;
        } catch (Exception e) {
            log.error("base64转文件异常,exception: {}", e);
            return null;
        }
    }
}
