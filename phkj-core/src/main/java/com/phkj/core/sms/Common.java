package com.phkj.core.sms;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class Common {

    private final static List<String> UserTypeList = Arrays.asList("trainee", "coach");//私教:coach,健身者:trainee

    /**
     *
     *判断用户类型是否正确
     *
     **/
    public static boolean isUserTypeLegal(String userType){
        return UserTypeList.contains(userType);
    }

    /**
     * 生成uid
     */
    public static String generateUid(String phoneNum, String userType) {
        return MD5Util.getMD5(phoneNum + ":link:" + userType);
    }


    /**
     * 生成sessionid
     */

    public static String generateSessionId(String uid) {
        return MD5Util.getMD5(uid + ":" + System.currentTimeMillis());
    }


    /**
     * 手机号码校验
     */
    public static boolean isPhoneLegal(String str) throws PatternSyntaxException {
        String regExp = "^((13[0-9])|(15[^4])|(18[0,2,3,5-9])|(17[0-8])|(147)|(199))\\d{8}$";
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 随机生成6个数字
     */
    public static String getRandomCode() {
        Random random = new Random();
        String result = "";
        for (int i = 0; i < 4; i++) {
            result += random.nextInt(10);
        }
        return result;
    }

}
