package com.phkj.web.util.freemarkerutil;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class FreemarkerUtil {

    private static Calendar startTime = new GregorianCalendar();
    private static Calendar endTime   = new GregorianCalendar();

    static {
        startTime.set(Calendar.YEAR, 2014);
        startTime.set(Calendar.MONTH, 7);
        startTime.set(Calendar.DAY_OF_MONTH, 15);
        startTime.set(Calendar.HOUR_OF_DAY, 10);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.SECOND, 0);

        endTime.set(Calendar.YEAR, 2014);
        endTime.set(Calendar.MONTH, 8);
        endTime.set(Calendar.DAY_OF_MONTH, 19);
        endTime.set(Calendar.HOUR_OF_DAY, 00);
        endTime.set(Calendar.MINUTE, 0);
        endTime.set(Calendar.SECOND, 0);

    }

    public static String formatter920Price(String price) {
        Date now = new Date();
        Date startDate = startTime.getTime();
        Date endDate = endTime.getTime();

        if (now.getTime() >= startDate.getTime() && now.getTime() <= endDate.getTime()) {
            if (price.length() == 3) {
                price = "?" + price.substring(1, price.length());
            } else if (price.length() == 4) {
                price = price.substring(0, 1) + "?" + price.substring(2, price.length());
            }
        }
        return price;
    }

    public static void main(String[] args) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        System.out.println(sdf.format(startTime.getTime()));
        System.out.println(sdf.format(endTime.getTime()));

        System.out.println(formatter920Price("1789"));
    }
}
