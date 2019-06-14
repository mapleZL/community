package com.phkj.service.impl.parking;

import com.github.pagehelper.ISelect;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.phkj.dao.shop.read.praking.StAppletParkingPriceReadDao;
import com.phkj.dao.shop.read.praking.StAppletParkingReadDao;
import com.phkj.dao.shop.read.praking.StAppletReadPaymentDao;
import com.phkj.dao.shop.write.praking.StAppletParkingPriceWriteDao;
import com.phkj.dao.shop.write.praking.StAppletParkingWriteDao;
import com.phkj.entity.praking.StAppletParking;
import com.phkj.entity.praking.StAppletParkingPrice;
import com.phkj.entity.praking.StAppletPayment;
import com.phkj.service.praking.ParkingService;
import jxl.write.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ParkingServiceImpl implements ParkingService {

    @Autowired
    StAppletParkingReadDao parkingReadDao;

    @Autowired
    StAppletParkingWriteDao parkingWriteDao;

    @Autowired
    StAppletReadPaymentDao paymentDao;

    @Autowired
    StAppletParkingPriceReadDao parkingPriceReadDao;


    /**
     * @param sts
     * @param page
     * @param rows
     * @param villageCode
     * @return
     */
    @Override
    public PageInfo<StAppletParking> getSystemAll(String sts, String page, String rows, String villageCode) {

        int pageNum = Integer.valueOf(page);
        int pageSize = Integer.valueOf(rows);
        PageInfo<StAppletParking> pageInfo = PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(new ISelect() {
            @Override
            public void doSelect() {
                parkingReadDao.getSystemAll(sts, villageCode);
            }
        });
        return null;
    }

    /**
     * @param pageNum
     * @param pageSize
     * @param villageCode
     * @param userId
     * @param sts
     * @return
     */
    @Override
    public PageInfo<StAppletParking> getMeParking(String pageNum, String pageSize, String villageCode, String userId,
                                                  String sts) {
        int page = Integer.valueOf(pageNum);
        int size = Integer.valueOf(pageSize);
        PageInfo<StAppletParking> pageInfo = PageHelper.startPage(page, size).doSelectPageInfo(new ISelect() {
            @Override
            public void doSelect() {
                parkingReadDao.getMeParking(villageCode, userId, sts);
            }
        });
        // 开始处理数据,如果当前时间大于到期时间 那么该车位已经到期
        List<StAppletParking> returnList = new ArrayList<>();
        List<StAppletParking> list = pageInfo.getList();
        if (null != list && list.size() > 0) {
            for (StAppletParking parking : list) {
                //  结束时间小于当前时间车位已经到期 更新数据库状态
                Date endTime = parking.getEndTime();
                Date nowDate = new Date();
                long time = endTime.getTime();
                long time1 = nowDate.getTime();
                if (time < time1) {
                    parking.setSts("2");  // 已经过期
                    parking.setModifyTime(new Date());
                    parkingWriteDao.updateByPrimaryKeySelective(parking);
                }
                returnList.add(parking);
            }
        }
        pageInfo.setList(returnList);
        return pageInfo;
    }

    /**
     * @param stAppletParking
     * @return
     */
    @Override
    public boolean addParking(StAppletParking stAppletParking) {
        stAppletParking.setCreateTime(new Date());
        stAppletParking.setSts("0");
        int i = parkingWriteDao.insert(stAppletParking);
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
    public Map<String, Object> getPayment(String villageCode) {
        Map<String, Object> returnMap = new HashMap<>();
        //
        StAppletPayment zfbPay = paymentDao.selectByPaymentType(villageCode, "1");
        //
        StAppletPayment yhkPay = paymentDao.selectByPaymentType(villageCode, "2");
        //
        StAppletPayment wxPay = paymentDao.selectByPaymentType(villageCode, "3");
        returnMap.put("zfb", zfbPay);
        returnMap.put("yhk", yhkPay);
        returnMap.put("wx", wxPay);
        // 查询价格
        StAppletParkingPrice stAppletParkingPrice = parkingPriceReadDao.selectParkingPriceBySts(villageCode, "1");
        returnMap.put("price", stAppletParkingPrice);
        return returnMap;
    }


    /**
     * @param id
     * @param userId
     * @param villageCode
     * @param userName
     * @return
     */
    @Override
    public boolean updateParking(String id, String userId, String villageCode, String userName) {
        StAppletParking parking = parkingReadDao.selectByPrimaryKey(Long.valueOf(id));
        if (null == parking) {
            return false;
        }
        //
        int months = getMonths(parking.getStartTime(), parking.getEndTime());
        Calendar cal = Calendar.getInstance();
        cal.setTime(parking.getEndTime());
        cal.add(Calendar.MONTH, months);
        Date time = cal.getTime();
        //设置起时间
        parking.setStartTime(parking.getEndTime());
        parking.setEndTime(time);
        parking.setSts("0");
        int i = parkingWriteDao.updateByPrimaryKeySelective(parking);
        if (i > 0) {
            return true;
        }
        return false;
    }

    /**
     * @param id
     * @param userId
     * @param userName
     * @return
     */
    @Override
    public boolean deleteParking(String id, String userId, String userName) {
        StAppletParking parking = parkingReadDao.selectByPrimaryKey(Long.valueOf(id));
        if (parking == null) {
            return false;
        }
        parking.setSts("3");
        parking.setModifyTime(new Date());
        parking.setModifyUserId(userId);
        parking.setModifyUserName(userName);
        //
        int i = parkingWriteDao.updateByPrimaryKeySelective(parking);
        if (i > 0) {
            return true;
        }
        return false;
    }


    /**
     * @param startDate
     * @param endDate
     * @return
     */
    public int getMonths(Date startDate, Date endDate) {
        Calendar startCalendar = new GregorianCalendar();
        startCalendar.setTime(startDate);
        Calendar endCalendar = new GregorianCalendar();
        endCalendar.setTime(endDate);

        int diffYear = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
        int diffMonth = diffYear * 12 + endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);
        return diffMonth;
    }
}
