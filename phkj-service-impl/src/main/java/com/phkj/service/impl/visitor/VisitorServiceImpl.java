package com.phkj.service.impl.visitor;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.phkj.dao.shop.read.environmental.StAppletOverTimeReadMapper;
import com.phkj.dao.shop.read.visit.StAppletReadVisitDao;
import com.phkj.dao.shop.write.visit.StAppletWriteVisitDao;
import com.phkj.dao.shopm.read.relate.StBaseinfoParkingLotOrderDao;
import com.phkj.entity.environmental.StAppletOverTime;
import com.phkj.entity.relate.StBaseinfoParkingLotOrder;
import com.phkj.entity.system.SystemAdmin;
import com.phkj.entity.visit.StAppletVisitor;
import com.phkj.service.visit.VisitorService;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class VisitorServiceImpl implements VisitorService {

    @Autowired
    StAppletReadVisitDao stAppletReadVisitDao;

    @Autowired
    StAppletWriteVisitDao stAppletWriteVisitDao;

    @Autowired
    StAppletOverTimeReadMapper overTimeReadMapper;

    @Autowired
    StBaseinfoParkingLotOrderDao parkingLotOrderDao;


    /**
     * @param page
     * @param rows
     * @param sts
     * @param type
     * @param adminUser
     * @return
     */
    @Override
    public PageInfo<StBaseinfoParkingLotOrder> getAllVisParkinigLot(String page, String rows, String sts, String type, SystemAdmin adminUser) {

        PageInfo<StBaseinfoParkingLotOrder> pageInfo = PageHelper.startPage(Integer.valueOf(page),
                Integer.valueOf(rows)).doSelectPageInfo(new ISelect() {
            @Override
            public void doSelect() {
                parkingLotOrderDao.getAll(sts, type,adminUser.getVillageCode());
            }
        });
        return pageInfo;
    }


    @Override
    public PageInfo<StAppletVisitor> getAllVisitor(String sts, String type, String page, String rows,
                                                   String villageCode) {

        PageInfo<StAppletVisitor> pageInfo = PageHelper.startPage(Integer.valueOf(page), Integer.valueOf(rows)).doSelectPageInfo(new ISelect() {
            @Override
            public void doSelect() {
                stAppletReadVisitDao.getAllVisitor(sts, type, villageCode);
            }
        });
        return pageInfo;
    }


    /**
     * @param id
     * @param adminUser
     * @return
     */
    @Override
    public boolean delete(String id, SystemAdmin adminUser) {
        StAppletVisitor visitor = stAppletReadVisitDao.selectByPrimaryKey(Long.valueOf(id));
        if (null == visitor) {
            return false;
        }
        visitor.setSts("0");
        visitor.setModifyTime(new Date());
        visitor.setModifyUserId(adminUser.getId().toString());
        visitor.setModifyUserName(adminUser.getName());
        int i = stAppletWriteVisitDao.updateByPrimaryKey(visitor);
        if (i > 0) {
            return true;
        }
        return false;
    }


    /**
     * @param villageCode
     * @return
     */
    @Override
    public Map<String, Object> getVisitTime(String villageCode) {

        // 查询时间
        List<StAppletOverTime> timeList = overTimeReadMapper.getDataByType("2", villageCode);

        // 访问次数  只有一条记录
        List<StAppletOverTime> numList = overTimeReadMapper.getDataByType("3", villageCode);
        StAppletOverTime overTime = null;
        if (null != numList && numList.size() > 0) {
            overTime = numList.get(0);
        }
        Map<String, Object> returnMap = new HashMap<String, Object>();
        returnMap.put("visTime", timeList);
        returnMap.put("applyNum", overTime);
        return returnMap;
    }


    /**
     * @param visitor
     * @return
     */
    @Override
    public Map<String, Object> addVisit(StAppletVisitor visitor) {
        Map<String, Object> returnMap = new HashMap<>();
        // 拼接数据
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("codeType", visitor.getPasswordType());
        paramMap.put("effectiveTime", visitor.getTime());
        paramMap.put("houseName", visitor.getHouseId());
        paramMap.put("name", visitor.getVisitorName());
        paramMap.put("relationShip", visitor.getVisitorType());
        paramMap.put("residentiaName", visitor.getHouseName());
        paramMap.put("sex", visitor.getGender());
        paramMap.put("times", visitor.getOverNum());
        paramMap.put("visitTime", visitor.getApplyTime());
        String urlStr = visitor.getImgurl();
        List<String> urlList = JSON.parseArray(urlStr, String.class);
        String url = "";
        if (null != urlList && urlList.size() > 0) {
            url = urlList.get(0);
        }
        paramMap.put("imgUrl", url);
        // 发送请求
        CloseableHttpClient build = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost("http://114.55.170.236:8856/applet/visitorempower/getVisitorCipher");
        StringEntity stringEntity = new StringEntity(JSON.toJSONString(paramMap), "UTF-8");
        httpPost.setEntity(stringEntity);
        httpPost.setHeader("Content-Type", "application/json;charset=utf8");
        // 响应模型
        CloseableHttpResponse response = null;
        String password = "";
        try {
            response = build.execute(httpPost);
            HttpEntity responseEntity = response.getEntity();
            if (responseEntity != null) {
                if (!(response.getStatusLine().toString().contains("200"))) {
                    return null;
                }
                //解析参数
                String jsonStr = EntityUtils.toString(responseEntity, "utf-8");
                Map<String, Object> map = JSON.parseObject(jsonStr, Map.class);
                if (null != map && map.size() > 0) {
                    password = String.valueOf(map.get("resultData"));
                    returnMap.put("type", visitor.getPasswordType());
                    returnMap.put("password", password);
                }
            } else {
                return null;
            }
            // 开始写入数据  -- 计算到期时间
            visitor.setOverTime(getOverTime(new Date(), visitor.getTime()));
            visitor.setCreateTime(new Date());
            visitor.setSts("1");
            visitor.setPassword(password);
            stAppletWriteVisitDao.insert(visitor);

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
        return returnMap;
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
        c.add(Calendar.HOUR, 0);   //日期小时加23小时
        c.add(Calendar.MINUTE, Integer.valueOf(str)); //日期分钟加59分钟
        c.add(Calendar.SECOND, 0); //日期分钟加59分钟
        Date date = c.getTime(); //结果
        return date;
    }
}
