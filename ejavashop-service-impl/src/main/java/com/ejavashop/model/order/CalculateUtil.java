package com.ejavashop.model.order;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CalculateUtil {

	public static int BEGIN_TIME = 20161219;

	public static int END_TIME = 20170630;
	
    
    /**
     * 判断当前支付时间是否在规定时间内
     * @return
     */
    public static Boolean isInActiveTime() {
        Date date = new Date();
        DateFormat format=new SimpleDateFormat("yyyyMMdd");
        String time1=format.format(date);
        int payTime = Integer.parseInt(time1);
        return (payTime >= BEGIN_TIME && payTime <= END_TIME);
     }
    
    public static void main(String args[]) {
        System.out.println(isInActiveTime());
    }

}
