package com.phkj.service.impl.parking;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.phkj.dao.shop.read.praking.StAppletParkingPriceReadDao;
import com.phkj.dao.shop.write.praking.StAppletParkingPriceWriteDao;
import com.phkj.entity.praking.StAppletParkingPrice;
import com.phkj.entity.system.SystemAdmin;
import com.phkj.service.praking.ParkingPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ParkingPriceServiceImpl implements ParkingPriceService {

    @Autowired
    StAppletParkingPriceReadDao parkingPriceReadDao;

    @Autowired
    StAppletParkingPriceWriteDao parkingPriceWriteDao;

    /**
     * @param page
     * @param rows
     * @param villageCode
     * @return
     */
    @Override
    public PageInfo<StAppletParkingPrice> selectAllPrice(String page, String rows, String villageCode) {
        int pageNum = Integer.valueOf(page);
        int pageSize = Integer.valueOf(rows);
        PageInfo<StAppletParkingPrice> pageInfo = PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(new ISelect() {
            @Override
            public void doSelect() {
                parkingPriceReadDao.selectAll(villageCode);
            }
        });
        return pageInfo;
    }

    /**
     * @param stAppletParkingPrice
     * @param adminUser
     */
    @Override
    public void save(StAppletParkingPrice stAppletParkingPrice, SystemAdmin adminUser) {

        stAppletParkingPrice.setCreateTime(new Date());
        stAppletParkingPrice.setCreateUserId(adminUser.getId().toString());
        stAppletParkingPrice.setSts("2");
        stAppletParkingPrice.setVillageCode(adminUser.getVillageCode());
        parkingPriceWriteDao.insert(stAppletParkingPrice);
    }

    /**
     * @param id
     * @param type
     * @param villageCode
     * @return
     */
    @Override
    public boolean update(String id, String type, String villageCode) {
        StAppletParkingPrice price = parkingPriceReadDao.selectByPrimaryKey(Long.valueOf(id));
        if (null == price) {
            return true;
        }
        if ("0".equals(type)) {
            price.setSts("0");
        } else if ("1".equals(type)) {
            List<StAppletParkingPrice> list = parkingPriceReadDao.selectByType(id, villageCode);
            if (null != list && list.size() > 0) {
                for (StAppletParkingPrice parkingPrice : list) {
                    parkingPrice.setSts("2");
                    parkingPriceWriteDao.updateByPrimaryKey(parkingPrice);
                }
            }
            price.setSts("1");
        } else {
            price.setSts("2");
        }
        int i = parkingPriceWriteDao.updateByPrimaryKeySelective(price);
        if (i > 0) {
            return true;
        }
        return false;
    }
}
