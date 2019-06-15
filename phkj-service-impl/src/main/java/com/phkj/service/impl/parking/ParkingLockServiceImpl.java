package com.phkj.service.impl.parking;

import com.alibaba.fastjson.JSON;
import com.phkj.dao.shop.read.member.MemberParkingLotReadDao;
import com.phkj.dao.shopm.read.relate.StBaseinfoParkingLotDao;
import com.phkj.entity.member.MemberParkingLot;
import com.phkj.entity.relate.StBaseinfoParkingLot;
import com.phkj.service.praking.ParkingLockService;
import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Service
public class ParkingLockServiceImpl implements ParkingLockService {

    private final static Logger LOGGER = LogManager.getLogger(ParkingLockServiceImpl.class);


    @Autowired
    StBaseinfoParkingLotDao stBaseinfoParkingLotDao;

    @Autowired
    MemberParkingLotReadDao memberParkingLotReadDao;

    /**
     * @param id
     * @return
     */
    @Override
    public boolean unLock(String id) {

        // id select
        MemberParkingLot parkingLot = memberParkingLotReadDao.get(Integer.valueOf(id));
        if (null == parkingLot) {
            LOGGER.info("未查询到该认证信息");
            return false;
        }
        //查询车位锁
        Map<String, String> lotMap = stBaseinfoParkingLotDao.selectLockByNum(parkingLot.getPosition(),
                parkingLot.getVillageCode());
        if (null != lotMap && lotMap.size() == 0) {
            LOGGER.info("未查询到车位锁!");
            return false;
        }

        // 发送请求解锁
        Map<String, String> map = new HashMap<>();
        map.put("deviceCode", lotMap.get("lockNum"));
        map.put("type", "poleDown");
        map.put("orgCode", parkingLot.getVillageCode());
        map.put("params", "");
        //开始处理请求
        CloseableHttpClient build = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost("http://lifangwei.natapp1.cc/mobile/operation/parkinglock/poleDown");
        StringEntity stringEntity = new StringEntity(JSON.toJSONString(map), "UTF-8");
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
                if (!(response.getStatusLine().toString().contains("200"))) {
                    return false;
                }
                String string = EntityUtils.toString(responseEntity, "utf-8");
                if (string.equals("true")) {
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
        return false;
    }


    /**
     * @param id
     * @return
     */
    @Override
    public String getLockStatus(String id) {
        // id select
        MemberParkingLot parkingLot = memberParkingLotReadDao.get(Integer.valueOf(id));
        if (null == parkingLot) {
            LOGGER.info("未查询到该认证信息");
            return "false";
        }
        //查询车位锁
        Map<String, String> lotMap = stBaseinfoParkingLotDao.selectLockByNum(parkingLot.getPosition(),
                parkingLot.getVillageCode());
        if (null != lotMap && lotMap.size() == 0) {
            LOGGER.info("未查询到车位锁!");
            return "false";
        }

        // 发送请求解锁
        Map<String, String> map = new HashMap<>();
        map.put("deviceCode", lotMap.get("lockNum"));
        map.put("type", "queryStatus");
        map.put("orgCode", parkingLot.getVillageCode());
        map.put("params", "");
        //开始处理请求
        CloseableHttpClient build = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost("http://lifangwei.natapp1.cc/mobile/operation/parkinglock/queryStatus");
        StringEntity stringEntity = new StringEntity(JSON.toJSONString(map), "UTF-8");
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
                if (!(response.getStatusLine().toString().contains("200"))) {
                    return "false";
                }
                String string = EntityUtils.toString(responseEntity, "utf-8");
                if (string.equals("true")) {
                    return "1";
                } else {
                    return "0";
                }
            } else {
                return "false";
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            return "false";
        } catch (ParseException e) {
            e.printStackTrace();
            return "false";
        } catch (IOException e) {
            e.printStackTrace();
            return "false";
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
    }


}
