package com.phkj.service.praking;

import com.github.pagehelper.PageInfo;
import com.phkj.entity.praking.StAppletPayment;
import com.phkj.entity.system.SystemAdmin;

public interface PaymentService {

    PageInfo<StAppletPayment> getAllPayment(String page, String rows, String sts, String type, String villageCode);

    boolean add(StAppletPayment stAppletPayment, SystemAdmin adminUser);

    boolean update(String id, String type, SystemAdmin adminUser);
}
