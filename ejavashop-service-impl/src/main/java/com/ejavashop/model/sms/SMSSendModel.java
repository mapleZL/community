package com.ejavashop.model.sms;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.stereotype.Component;

import com.ejavashop.core.EjavashopConfig;
import com.ejavashop.core.exception.ArgumentException;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.core.sms.AbstractSMSSender;
import com.ejavashop.core.sms.TemplateSMSSender;
import com.ejavashop.core.sms.VerifyCodeSender;
import com.ejavashop.entity.order.OrdersProduct;

/**
 * 验证码短信发送<br/>
 * <i>注意:所有短信发送都是异步的</i>
 *                       
 * @Filename: SMSVerifyCodeModel.java
 * @Version: 1.0
 * @Author: zhaihl
 * @Email: zhaihl_0@126.com
 *
 */
@Component(value = "SMSSendModel")
public class SMSSendModel {
    /**
     * 验证码短信
     */
    private static final int VERIFY_CODE  = 0;
    /**
     * 模板短信
     */
    private static final int TEMPLATE_SMS = 1;

    /**
     * 发送验证码短信
     * @param map
     * @return
     * @throws Exception
     */
    public Object sendVerifyCode(Map<String, Object> map) throws Exception {
        //创建一个线程池
        ExecutorService pool = Executors.newFixedThreadPool(EjavashopConfig.DEFAULT_RUN_THREAD_NUM);
        Object obj = null;
        if (map.get("mobile") == null) {
            throw new BusinessException("请指定要发送的手机号码");
        }
        obj = pool.submit(new SMSCall(map, VERIFY_CODE)).get();			//Terry 20160701
        //关闭线程池
        pool.shutdown();
        return obj;
    }

    /**
     * 发送模板短信,并返回该短信的执行结果
     * @param map
     * @return
     * @throws Exception
     */
    public Object sendSMSWidthCallable(Map<String, Object> map) throws Exception {
        //创建一个线程池
        ExecutorService pool = Executors.newFixedThreadPool(EjavashopConfig.DEFAULT_RUN_THREAD_NUM);
        Object obj = null;
        if (map.get("mobile") == null) {
            throw new BusinessException("请指定要发送的手机号码");
        }
        if (map.get("content") == null) {
            throw new BusinessException("请指定短信参数");
        }

        obj = pool.submit(new SMSCall(map, TEMPLATE_SMS)).get();
        //关闭线程池
        pool.shutdown();
        return obj;
    }

    /**
     * 发送模板短信
     * @param map
     * @throws Exception
     */
    public void sendSMS(Map<String, Object> map) throws Exception {
        if (map.get("mobile") == null) {
            throw new BusinessException("请指定要发送的手机号码");
        }
        if (map.get("content") == null) {
            throw new BusinessException("请指定短信参数");
        }
        new Thread(new SMSThread(map, TEMPLATE_SMS)).start();
    }

    class SMSCall implements Callable<Object> {
        private Map<String, Object> param;
        private int                 type;

        SMSCall(Map<String, Object> param, int type) {
            this.param = param;
            this.type = type;
        }

        @Override
        public Object call() throws Exception {
            AbstractSMSSender sender = null;
            if (type == VERIFY_CODE)
                sender = new VerifyCodeSender(param.get("mobile").toString());
            else if (type == TEMPLATE_SMS)
                sender = new TemplateSMSSender(param.get("mobile").toString(), param.get("content")
                    .toString());
            if (sender == null) {
                throw new ArgumentException("参数错误");
            }
            return sender.sendSMSYP();
//            return sender.sendSMS();
        }
    }

    class SMSThread implements Runnable {
        private Map<String, Object> param;
        private int                 type;

        SMSThread(Map<String, Object> map, int type) {
            this.param = map;
            this.type = type;
        }

        @Override
        public void run() {
            AbstractSMSSender sender = null;
            if (type == VERIFY_CODE)
                sender = new VerifyCodeSender(param.get("mobile").toString());
            else if (type == TEMPLATE_SMS)
                sender = new TemplateSMSSender(param.get("mobile").toString(), param.get("content")
                    .toString());
            if (sender == null) {
                throw new ArgumentException("参数错误");
            }
            sender.sendSMSYP();
//            sender.sendSMS();
        }
    }
    
    public static void main(String[] args) throws Exception {
       /* Map<String, Object> map = new HashMap<String, Object>();
        map.put("mobile", "13816524067");
        Object obj = new SMSSendModel().sendVerifyCode(map);
        System.out.println(obj);
        

        

        StringBuffer sb = new StringBuffer("【大袜网】亲,您有宝贝下单,金额为:").append("130").append(",商品号:");
        String[] str = {"W.M.201928001", "W.M.201928002",  "W.M.201928003",  "W.M.201928004"};
        for (String s : str) {
            if ((sb.length() + s.length()) >= 70) {
                if (sb.lastIndexOf(",") == (sb.length() - 1)) {
                    sb.delete(sb.length() - 1, sb.length());
                }
                sb.append("等");
                break;
            } else {
                sb.append(s).append(",");
            }
        }
        if (sb.lastIndexOf(",") == sb.length() - 1) {
            sb.delete(sb.length() - 1, sb.length());
        }

        System.out.println(sb);
        */
      AbstractSMSSender send = new TemplateSMSSender("13816524067, 13506850360, 13567372018, 15167388835, 13615855867, 18969517555, 13858522547, 13758579218, 13867333078, 15800782208, 13665833351, 18969526688, 18657587377, 13567571766, 13858525977, 13777302658, 15068585288, 13506850839, 15257340007, 13516755198, 13857500388, 13456866960, 13735313140, 57587923788, 13989597660, 13957333737, 13586344710, 18758512023, 13216852833, 13600637138, 13837983129, 18969517555, 13858510299, 13806701275, 18858511868, 13819521810, 13586371550, 15967330009, 15967591355, 13957359310, 13185585008, 13429428882, 15158195894, 15167388835, 13575571532, 15988280239, 15988232695, 18258531998, 18257545550, 18758515598, 15957522661, 13777302658, 18969526688, 18367532289", "【大袜网】亲，我们将为您免费开通订单提醒功能，可以实时了解出货情况。如有疑问和建议请联系平台商品部，如需退订请回复TD。");
      send.sendSMSYP();
        System.out.println(">>>>>>> ");
//        System.out.println("【大袜网】亲，我们将为您免费开通订单提醒功能，可以实时了解出货情况。如有疑问和建议请联系平台商品部，如需退订请回复TD。");
        
        /*
        String sb = "【大袜网】亲,您有宝贝下单,金额为:1820.01,商品号:";
        String[] str = {"W.M.201928001", "W.M.201928002",  "W.M.201928003",  "W.M.201928004",  "W.M.201928005", "W.M.201928006", "W.M.201928007", "W.M.201928008"};
        for (String s : str) {
            if ((sb.length() + s.length()) >= 70) {
                if (sb.endsWith(",")) {
                    sb = sb.substring(0,sb.length()-1);
                }
                sb = sb + "等";
                break;
            } else {
                sb = sb + s+",";
            }
        }
        if (sb.endsWith(",")) {
            sb = sb.substring(0,sb.length()-1);
        }
        System.out.println(sb);
        Map<String, Object> smsparam = new HashMap<String, Object>();
        smsparam.put("mobile", "18916705605");
        smsparam.put("content", sb);
        */
//        Object obj = new SMSSendModel().sendSMSWidthCallable(smsparam);

//        AbstractSMSSender send = new TemplateSMSSender("13816524067", sb);
//        send.sendSMSYP();
        
    }
}
