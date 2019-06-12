package com.phkj.service.impl.parking;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.phkj.dao.shop.read.praking.StAppletReadPaymentDao;
import com.phkj.dao.shop.write.praking.StAppletWritePaymentDao;
import com.phkj.entity.praking.StAppletPayment;
import com.phkj.service.praking.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
