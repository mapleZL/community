package com.phkj.service.impl.parking;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.phkj.core.JsonUtil;
import com.phkj.dao.shop.read.praking.StAppletReadPaymentDao;
import com.phkj.dao.shop.write.praking.StAppletWritePaymentDao;
import com.phkj.entity.praking.StAppletPayment;
import com.phkj.entity.system.SystemAdmin;
import com.phkj.service.praking.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    StAppletReadPaymentDao stAppletReadPaymentDao;

    @Autowired
    StAppletWritePaymentDao stAppletWritePaymentDao;

    /**
     * @param page
     * @param rows
     * @param sts
     * @param type
     * @param villageCode
     * @return
     */
    @Override
    public PageInfo<StAppletPayment> getAllPayment(String page, String rows, String sts, String type, String villageCode) {

        int pageNum = Integer.valueOf(page);
        int pageSize = Integer.valueOf(rows);
        PageInfo<StAppletPayment> pageInfo = PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(new ISelect() {
            @Override
            public void doSelect() {
                stAppletReadPaymentDao.selectAllPayment(villageCode, type, sts);
            }
        });
        return pageInfo;
    }


    /**
     * @param stAppletPayment
     * @param adminUser
     * @return
     */
    @Override
    public boolean add(StAppletPayment stAppletPayment, SystemAdmin adminUser) {

        String qrCode = stAppletPayment.getQrCode();
        String url = qrCode.replace(";", "");
        stAppletPayment.setVillageCode(adminUser.getVillageCode());
        stAppletPayment.setCreateUserId(adminUser.getId().toString());
        stAppletPayment.setCreateUserName(adminUser.getName());
        stAppletPayment.setCreateTime(new Date());
        stAppletPayment.setSts("0");
        // 处理图片信息
        List<String> imgUrl = new ArrayList<>();
        imgUrl.add(url);
        stAppletPayment.setQrCode(JSON.toJSONString(imgUrl));

        int i = stAppletWritePaymentDao.insert(stAppletPayment);
        if (i <= 0) {
            return true;
        }
        return false;
    }

    /**
     * @param id
     * @param type
     * @param adminUser
     * @return
     */
    @Override
    public boolean update(String id, String type, SystemAdmin adminUser) {

        StAppletPayment stAppletPayment = stAppletReadPaymentDao.selectByPrimaryKey(Long.valueOf(id));
        if (null == stAppletPayment) {
            return false;
        }
        stAppletPayment.setModifyTime(new Date());
        stAppletPayment.setModifyUserId(adminUser.getId().toString());
        stAppletPayment.setModifyUserName(adminUser.getName());
        // 根据type判断操作类型 , 0.停用 1.启用 2 删除
        if ("0".equals(type)) {
            stAppletPayment.setSts("0");
        } else if ("1".equals(type)) {
            // 查询所有同类型启用数据全部停用
            List<StAppletPayment> list = stAppletReadPaymentDao.selectByType(stAppletPayment.getPaymentType(), stAppletPayment.getId().toString());
            if (null != list && list.size() > 0) {
                for (StAppletPayment appletPayment : list) {
                    appletPayment.setSts("0");
                    appletPayment.setModifyTime(new Date());
                    appletPayment.setModifyUserId(adminUser.getId().toString());
                    appletPayment.setModifyUserName(adminUser.getName());
                    stAppletReadPaymentDao.updateByPrimaryKeySelective(appletPayment);
                }
            }
            // 开始启用数据
            stAppletPayment.setSts("1");
        } else {
            stAppletPayment.setSts("2"); // 删除
        }
        int i = stAppletWritePaymentDao.updateByPrimaryKeySelective(stAppletPayment);
        if (i > 0) {
            return true;
        }
        return false;
    }
}
