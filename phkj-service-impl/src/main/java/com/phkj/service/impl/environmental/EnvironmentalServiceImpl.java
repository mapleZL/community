package com.phkj.service.impl.environmental;

import com.alibaba.fastjson.JSON;
import com.phkj.entity.environmental.StAppletEnviron;
import com.phkj.service.environmental.EnvironmentalService;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.wml.WMLUElement;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

@Service
public class EnvironmentalServiceImpl implements EnvironmentalService {

    private final static Logger LOGGER = LogManager.getLogger(EnvironmentalServiceImpl.class);


    /**
     * @param stAppletEnviron
     * @return
     */
    @Override
    public boolean add(StAppletEnviron stAppletEnviron) {

        // 使用httpClient发送请求
        CloseableHttpClient build = HttpClientBuilder.create().build();
//        HttpPost httpPost = new HttpPost("http://localhost:8084/zhjd/event/current/mobile/environment/save");
        HttpPost httpPost = new HttpPost("http://baidu.com");
        String str = JSON.toJSONString(stAppletEnviron);
        StringEntity stringEntity = new StringEntity(str, "UTF-8");
        httpPost.setEntity(stringEntity);
        httpPost.setHeader("Content-Type", "application/json;charset=utf8");
        // 响应模型
        CloseableHttpResponse response = null;
        try {
            // 由客户端执行(发送)Post请求
            response = build.execute(httpPost);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();
            if (responseEntity != null) {
                if (response.getStatusLine().toString().contains("200")) {
                    return true;
                }
            } else {
                return false;
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            return false;
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                // 释放资源
                if (build != null) {
                    build.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Map<String, Object> getAll(String id, Integer pageNum, Integer pageSize) {

        // 使用httpClient发送请求
        CloseableHttpClient build = HttpClientBuilder.create().build();
        StringBuffer str = new StringBuffer();
        try {
            str.append("eventSourceId=" + id);
            str.append(URLEncoder.encode("&", "UTF-8"));
            str.append("pageNum=" + pageNum);
            str.append(URLEncoder.encode("&", "UTF-8"));
            str.append("pageSize=" + pageSize);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        HttpGet httpGet = new HttpGet("http://localhost:8084/zhjd/event/current/mobile/environment/list" + "?" + str);
        // 响应模型
        CloseableHttpResponse response = null;
        try {
            // 配置信息
            RequestConfig requestConfig = RequestConfig.custom()
                    // 设置连接超时时间(单位毫秒)
                    .setConnectTimeout(5000)
                    // 设置请求超时时间(单位毫秒)
                    .setConnectionRequestTimeout(5000)
                    // socket读写超时时间(单位毫秒)
                    .setSocketTimeout(5000)
                    // 设置是否允许重定向(默认为true)
                    .setRedirectsEnabled(true).build();

            // 将上面的配置信息 运用到这个Get请求里
            httpGet.setConfig(requestConfig);

            // 由客户端执行(发送)Get请求
            response = build.execute(httpGet);

            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();
            System.out.println("响应状态为:" + response.getStatusLine());
            if (responseEntity != null) {
                System.out.println("响应内容长度为:" + responseEntity.getContentLength());
                System.out.println("响应内容为:" + EntityUtils.toString(responseEntity));
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 释放资源
                if (build != null) {
                    build.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
