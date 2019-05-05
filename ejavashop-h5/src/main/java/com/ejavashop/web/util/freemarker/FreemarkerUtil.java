package com.ejavashop.web.util.freemarker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class FreemarkerUtil {
    private static org.apache.log4j.Logger log = org.apache.log4j.LogManager
        .getLogger(FreemarkerUtil.class);

    private static Calendar startTime = new GregorianCalendar();
    private static Calendar endTime   = new GregorianCalendar();

    static {
        startTime.set(GregorianCalendar.YEAR, 2014);
        startTime.set(GregorianCalendar.MONTH, 7);
        startTime.set(GregorianCalendar.DAY_OF_MONTH, 15);
        startTime.set(GregorianCalendar.HOUR_OF_DAY, 10);
        startTime.set(GregorianCalendar.MINUTE, 0);
        startTime.set(GregorianCalendar.SECOND, 0);

        endTime.set(GregorianCalendar.YEAR, 2014);
        endTime.set(GregorianCalendar.MONTH, 8);
        endTime.set(GregorianCalendar.DAY_OF_MONTH, 19);
        endTime.set(GregorianCalendar.HOUR_OF_DAY, 00);
        endTime.set(GregorianCalendar.MINUTE, 0);
        endTime.set(GregorianCalendar.SECOND, 0);

    }

    public static String handleImagePath(String url, String sign) throws InterruptedException {
        if (sign.equals("little")) {
            Thread.sleep(100);
        } else {
            Thread.sleep(100);
        }
        return url;
    }


    public static void main(String[] args) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        System.out.println(sdf.format(startTime.getTime()));
        System.out.println(sdf.format(endTime.getTime()));

    }
}
