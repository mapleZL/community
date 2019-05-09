package com.phkj.core.sms;

import java.math.BigDecimal;

/**
 * 本类为短信模板管理
 *                       
 * @Filename: MobileMessage.java
 * @Version: 1.0
 * @Author: 陆帅 * @Email: lu1632278229@sina.cn
 *
 */
public class MobileMessage {

    //批处理取消订单时短信模板
    public Object getCancelMessage(String tradeNo) {
        return "亲爱的用户您好，您有一笔订单还未付款，订单提交后3小时之内未付款的将被取消，请及时付款。";
    }

    //忘记登录密码时短信模板
    public Object getForgetMessage(String newPwd) {
        return "【大袜网】您的登录密码为" + newPwd + "，请登录后修改密码，袜业全产业链一站式服务平台。";
    }

    //私人订制短信模板
    public Object getPersonalTailorMessage(String contacts, String mobile) {
        return "有新的订制需求，请注意到后台查收，并即时通知业务部门与客户联系，客户姓名："+ contacts +"，联系电话：" + mobile;
    }

    //入库单回传给供应商发送订单信息
    public StringBuffer getMessageToSeller(BigDecimal moneyProduct) {
        StringBuffer sb = new StringBuffer("【大袜网】亲,您有宝贝下单,金额为:").append(moneyProduct).append(",商品号:");
        return sb;
    }

    public Object getRegister() {
        return "恭喜您，您已成功注册大袜网，请牢记账号与密码。做袜子生意就上大袜网！";
    }

    //给下单成功但30天未登录的人发送短信息
    public Object getMemberLoginMessage() {
        return "【大袜网】亲爱的用户您好，您已30天未登录大袜网，有空记得回大袜网看看哦，春风十里不如你，我们在大袜网等着你！退订回复TD";
    }
    
    

}
