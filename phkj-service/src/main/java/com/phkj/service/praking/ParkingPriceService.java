package com.phkj.service.praking;

import com.github.pagehelper.PageInfo;
import com.phkj.entity.praking.StAppletParkingPrice;
import com.phkj.entity.system.SystemAdmin;

public interface ParkingPriceService {

    PageInfo<StAppletParkingPrice> selectAllPrice(String page, String rows, String villageCode);

    void save(StAppletParkingPrice stAppletParkingPrice, SystemAdmin adminUser);

    boolean update(String id, String type, String villageCode);
}
