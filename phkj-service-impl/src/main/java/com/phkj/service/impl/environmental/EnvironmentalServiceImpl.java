package com.phkj.service.impl.environmental;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.phkj.dao.shop.read.environmental.StAppletEnvironmentReadMapper;
import com.phkj.dao.shop.read.environmental.StAppletOverTimeReadMapper;
import com.phkj.dao.shop.read.repaire.StAppletCommentDao;
import com.phkj.dao.shop.write.environmental.StAppletEnvironmentWriteMapper;
import com.phkj.entity.environmental.Comment;
import com.phkj.entity.environmental.StAppletEnviron;
import com.phkj.entity.environmental.StAppletEnvironment;
import com.phkj.entity.environmental.StAppletOverTime;
import com.phkj.entity.repair.StAppletComment;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

@Service
public class EnvironmentalServiceImpl implements EnvironmentalService {

    private final static Logger LOGGER = LogManager.getLogger(EnvironmentalServiceImpl.class);

    @Autowired
    StAppletEnvironmentWriteMapper stAppletEnvironmentWriteMapper;

    @Autowired
    StAppletEnvironmentReadMapper stAppletEnvironmentReadMapper;

    @Autowired
    StAppletOverTimeReadMapper timeReadMapper;

    @Autowired
    StAppletCommentDao stAppletCommentDao;

    /**
     * @param stAppletEnviron
     * @return
     */
    @Override
    public boolean add(StAppletEnvironment stAppletEnviron) {

        // 查询超时时间
        List<StAppletOverTime> stAppletOverTimes = timeReadMapper.selectAllOverTime();
        StAppletOverTime stAppletOverTime = stAppletOverTimes.get(0);
        // 开始处理数据
        stAppletEnviron.setOverTime(getOverTime(new Date(), stAppletOverTime.getOverTime()));
        stAppletEnviron.setSts("0");
        stAppletEnviron.setStatus("1");
        stAppletEnviron.setCreateTime(new Date());
        int i = stAppletEnvironmentWriteMapper.insert(stAppletEnviron);
        if (i <= 0) {
            return false;
        }
        if ("6".equals(stAppletEnviron.getClassify())) {
            return true;
        }
        StringBuffer str = new StringBuffer();
        try {
            str.append("title=" + stAppletEnviron.getTitle());
            str.append(URLEncoder.encode("&", "UTF-8"));
            str.append("reportType=" + stAppletEnviron.getClassify());
            str.append(URLEncoder.encode("&", "UTF-8"));
            str.append("position=" + "");
            str.append(URLEncoder.encode("&", "UTF-8"));
            str.append("content=" + stAppletEnviron.getContent());
            str.append(URLEncoder.encode("&", "UTF-8"));
            str.append("eventSourceId=" + stAppletEnviron.getCreateUserId());
            str.append(URLEncoder.encode("&", "UTF-8"));
            str.append("eventSourceName=" + stAppletEnviron.getCreateUserName());
            str.append(URLEncoder.encode("&", "UTF-8"));
            str.append("orgCode=" + stAppletEnviron.getVillageCode());
            str.append(URLEncoder.encode("&", "UTF-8"));
            str.append("topOrgCode=" + "");
            str.append(URLEncoder.encode("&", "UTF-8"));
            str.append("fileList=" + stAppletEnviron.getImgUrl());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        // 使用httpClient发送请求发送到物业管理系统
        CloseableHttpClient build = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost("http://lifangwei.natapp1.cc/event/current/mobile/environment/save?" + str);
//        HttpPost httpPost = new HttpPost("http://baidu.com");
//        String str = JSON.toJSONString(stAppletEnviron);
//        StringEntity stringEntity = new StringEntity(str, "UTF-8");
//        httpPost.setEntity(stringEntity);
        httpPost.setHeader("Content-Type", "application/json;charset=utf8");
        // 响应模型
        CloseableHttpResponse response = null;
        try {
            // 由客户端执行(发送)Post请求
            response = build.execute(httpPost);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();
            if (responseEntity != null) {
                if (!(response.getStatusLine().toString().contains("200"))) {
                    return false;
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
     * @param
     * @return
     */
    @Override
    public Map<String, Object> getAll(String userId, Integer pageNum, Integer pageSize) {

        PageInfo<StAppletEnvironment> pageInfo = PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(new ISelect() {
            @Override
            public void doSelect() {
                stAppletEnvironmentReadMapper.selectMeEnevronList(userId);
            }
        });

        Map<String, Object> returnMap = new HashMap<>();
        returnMap.put("list", pageInfo.getList());
        returnMap.put("total", pageInfo.getTotal());
//        // 使用httpClient发送请求
//        CloseableHttpClient build = HttpClientBuilder.create().build();
//        StringBuffer str = new StringBuffer();
//        try {
//            str.append("eventSourceId=" + id);
//            str.append(URLEncoder.encode("&", "UTF-8"));
//            str.append("pageNum=" + pageNum);
//            str.append(URLEncoder.encode("&", "UTF-8"));
//            str.append("pageSize=" + pageSize);
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        HttpGet httpGet = new HttpGet("http://localhost:8084/zhjd/event/current/mobile/environment/list" + "?" + str);
//        // 响应模型
//        CloseableHttpResponse response = null;
//        try {
//            // 配置信息
//            RequestConfig requestConfig = RequestConfig.custom()
//                    // 设置连接超时时间(单位毫秒)
//                    .setConnectTimeout(5000)
//                    // 设置请求超时时间(单位毫秒)
//                    .setConnectionRequestTimeout(5000)
//                    // socket读写超时时间(单位毫秒)
//                    .setSocketTimeout(5000)
//                    // 设置是否允许重定向(默认为true)
//                    .setRedirectsEnabled(true).build();
//
//            // 将上面的配置信息 运用到这个Get请求里
//            httpGet.setConfig(requestConfig);
//
//            // 由客户端执行(发送)Get请求
//            response = build.execute(httpGet);
//
//            // 从响应模型中获取响应实体
//            HttpEntity responseEntity = response.getEntity();
//            System.out.println("响应状态为:" + response.getStatusLine());
//            if (responseEntity != null) {
//                System.out.println("响应内容长度为:" + responseEntity.getContentLength());
//                System.out.println("响应内容为:" + EntityUtils.toString(responseEntity));
//            }
//        } catch (ClientProtocolException e) {
//            e.printStackTrace();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                // 释放资源
//                if (build != null) {
//                    build.close();
//                }
//                if (response != null) {
//                    response.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
        return returnMap;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public StAppletEnvironment getEnvironDetail(String id) {
        StAppletEnvironment stAppletEnvironment = stAppletEnvironmentReadMapper.selectByPrimaryKey(Long.valueOf(id));
        // 查询所有评论
        return stAppletEnvironment;
    }


    /**
     * @param comment
     * @return
     */
    @Override
    public boolean addComment(Comment comment) {
        StAppletComment stAppletComment = new StAppletComment();
        stAppletComment.setRId(Long.valueOf(comment.getId()));
        stAppletComment.setSts(1);
        stAppletComment.setCreateUserName(comment.getCreateUserName());
        stAppletComment.setCreateUserId(Long.valueOf(comment.getCreateUserId()));
        stAppletComment.setrType("environ");
        stAppletComment.setParentId(Integer.valueOf(comment.getParentId()));
        stAppletComment.setCreateTime(new Date());
        stAppletComment.setContent(comment.getContent());
        Integer i = stAppletCommentDao.insert(stAppletComment);
        if (i <= 0) {
            return false;
        }
        return true;
    }


    /**
     * @param status
     * @param sts
     * @param type
     * @param page
     * @param rows
     * @param villageCode
     * @return
     */
    @Override
    public PageInfo<StAppletEnvironment> getSystemAllEnviron(String status, String sts, String type, String page,
                                                             String rows, String villageCode) {

        PageInfo<StAppletEnvironment> pageInfo = PageHelper.startPage(Integer.valueOf(page), Integer.valueOf(rows)).doSelectPageInfo(new ISelect() {
            @Override
            public void doSelect() {
                stAppletEnvironmentReadMapper.selectSystemAllEnviron(status, sts,type,villageCode);
            }
        });
        return pageInfo;
    }


    /**
     * 设置当前时间加时分秒
     *
     * @param time
     * @return
     */
    public static Date getOverTime(Date time, String str) {
        Calendar c = Calendar.getInstance();
        c.setTime(time);   //设置时间
        c.add(Calendar.HOUR, Integer.valueOf(str));   //日期小时加23小时
        c.add(Calendar.MINUTE, 0); //日期分钟加59分钟
        c.add(Calendar.SECOND, 0); //日期分钟加59分钟
        Date date = c.getTime(); //结果
        return date;
    }

}
