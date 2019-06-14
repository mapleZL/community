package com.phkj.service.praking;

import com.github.pagehelper.PageInfo;
import com.phkj.entity.praking.StAppletParking;

import java.util.Map;

/**
 *
 */
public interface ParkingService {

    PageInfo<StAppletParking> getSystemAll(String sts, String page, String rows, String villageCode);

    PageInfo<StAppletParking> getMeParking(String pageNum, String pageSize, String villageCode,
                                           String userId, String sts);

    boolean addParking(StAppletParking stAppletParking);

    Map<String,Object> getPayment(String villageCode);

    boolean updateParking(String id, String userId, String villageCode, String userName);
}
