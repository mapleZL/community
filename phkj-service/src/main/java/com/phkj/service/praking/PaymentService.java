package com.phkj.service.praking;

import com.github.pagehelper.PageInfo;
import com.phkj.entity.praking.StAppletPayment;

public interface PaymentService {

    PageInfo<StAppletPayment> getAllPayment(String page, String rows, String sts, String type, String villageCode);
}
