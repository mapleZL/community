package com.ejavashop.core.email;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.bestpay.util.main;
import com.ejavashop.core.EjavashopConfig;
import com.ejavashop.core.TimeUtil;

public class SendMail {

    /**
     * 发送邮件
     * @param to 接受者邮箱地址 
     * @param subject 标题
     * @param body 内容
     * @param recipient  抄送地址
     * @return
     */
    public boolean send163Email(String to, String subject, String body,List<String> recipient) {
        MailSenderInfo mailInfo = new MailSenderInfo();
        mailInfo.setToAddress(to); // 设置接受者邮箱地址  
        mailInfo.setSubject(subject);//标题
        mailInfo.setContent(body);//内容

        //这个类主要是设置邮件  
        mailInfo.setMailServerHost(EjavashopConfig.MAIL_SERVER_HOST);
        mailInfo.setMailServerPort(EjavashopConfig.MAIL_SERVER_PORT);
        mailInfo.setValidate(true);

        String sender = EjavashopConfig.SEND_EMAIL_NAME;
        mailInfo.setUserName(sender); // 实际发送者
        mailInfo.setPassword(EjavashopConfig.SEND_EMAIL_PASSWORD);// 您的邮箱密码 
        mailInfo.setFromAddress(sender); // 设置发送人邮箱地址  
        //抄送人
        mailInfo.setRecipient(recipient);
        // 这个类主要来发送邮件  
        MailSender sms = new MailSender();
        //sms.sendTextMail(mailInfo); // 发送文体格式  
        return sms.sendHtmlMail(mailInfo); // 发送html格式  
    }
    
    public boolean sendErrorLog(String relationId, Date syncTime, String sendNo, String errorMsg) {
        StringBuffer body = new StringBuffer();
        body.append("尊敬的领导：");
        body.append("<br/>");
        body.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
        body.append("您好，很抱歉通知您，商城的订单推送出现问题了！");
        body.append("<br/>");
        body.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
        body.append("订单号为：").append(relationId).append(",同步时间为：").append(TimeUtil.getDateTimeString(syncTime));
        body.append(",失败次数：").append(sendNo).append(",失败原因：").append(errorMsg);
        body.append("<br/>");
        List<String> recipientList = new ArrayList<String>();
        recipientList.add("lvxiaojun@dawawang.com");
        recipientList.add("yuliyong@dawawang.com");
        recipientList.add("tiandexu@dawawang.com");
        recipientList.add("lushuai@dawawang.com");
        recipientList.add("zhuxiaolu@dawawang.com");
        recipientList.add("zhangzhenyan@dawawang.com");
        return send163Email("zhangbin@dawawang.com", "推送失败订单", body.toString(), recipientList);
    }
    
    public static void main(String[] args) {
		String to = "tiandexu@dawawang.com";
		String subject = "工作计划";
		StringBuffer body = new StringBuffer("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;逗你玩逗你玩逗你玩逗你玩逗你玩逗你玩逗你玩逗你玩逗你玩逗你玩逗你玩逗你玩逗你玩");
		body.append("逗你玩逗你玩逗你玩逗你玩逗你玩逗你玩逗你玩逗你玩逗你玩逗你玩逗你玩逗");
		body.append("<br/>");
		body.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;逗你玩逗你玩逗你玩逗你玩逗你玩逗你玩逗你玩逗你玩逗你玩逗你玩逗你玩逗");
		body.append("逗你玩逗你玩逗你玩逗你玩逗你玩逗你玩逗你玩逗你玩逗你玩逗你玩逗你玩逗");
		SendMail sendMail = new SendMail();
		List<String>list = new ArrayList<String>();
		list.add("lushuai@dawawang.com");
		list.add("hehoufei@dawawang.com");
		list.add("tiandexu@dawawang.com");
		sendMail.send163Email(to,subject,body.toString(),list);
	}
}
