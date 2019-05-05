package com.ejavashop.util.interfacewms;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.StringUtil;
import com.ejavashop.core.email.SendMail;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.core.freemarkerutil.DomainUrlUtil;
import com.ejavashop.entity.member.Member;
import com.ejavashop.entity.order.Orders;
import com.ejavashop.entity.wmsinterface.InterfaceWms;
import com.ejavashop.service.member.IMemberService;
import com.ejavashop.service.order.IOrdersService;
import com.ejavashop.service.wmsinterface.IInterfaceWmsService;
import com.ejavashop.web.util.SpringContextUtil;

/**
 * 入库接口
 * @author 小甜甜
 */

public class EnteringWarehouse {
	/*
	@Resource
	private static IInterfaceWmsService interfaceWmsService;
	*/
	
	private static Logger log = LogManager.getLogger(EnteringWarehouse.class);
	
	private static Logger iLog = LogManager.getLogger("oms_interface");
	
	 /**
	  * @param paramMap 
	  * @param willReplaceKeyWords 将要替换的字符串
	  * @param replacedKeyWords 被替换的字符串
	  * @param Subject 要请求的方法头
	  * @return
	  */
	public static synchronized void goodsOwnerSync(Map<String, Object> paramMap,String willReplaceKeyWords,String replacedKeyWords,String Subject){
		IInterfaceWmsService interfaceWmsService =null;
		try {
			interfaceWmsService = (IInterfaceWmsService)SpringContextUtil.getBean("interfaceWmsService");
		} catch (Exception e) {
			if(interfaceWmsService == null){
				ServletRequestAttributes attr = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
				HttpServletRequest request = attr.getRequest();
				ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
				interfaceWmsService = (IInterfaceWmsService)context.getBean("interfaceWmsService");
			}
		}
		
		String relationId = "";
		String relationTable ="";
		if(paramMap != null  && paramMap.size() >0 ){
			relationId= (String) paramMap.get(dawa_ttx_config.RELATION_ID);
			relationTable = (String) paramMap.get(dawa_ttx_config.RELATION_TABLE);
			paramMap.remove(dawa_ttx_config.RELATION_ID);
			paramMap.remove(dawa_ttx_config.RELATION_TABLE);
			if("".equals(relationId) || "".equals(relationTable)){
				throw new BusinessException("关联id（relationId）和关联表名（relationTable）传输为空");
			}
			
			//将map 转换成xml
			String xml = XmlConvertUtil.callMapToXML(paramMap, Subject);
			
//			iLog.info("##########################"+xml+"$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
			
			
			//发送前将平台字段转换成wms需要的字段
			//String sendXML = keyWordsReplaceUtil.replaceStr(xml, willReplaceKeyWords ,replacedKeyWords);
			
			//请求链接
			log.info("提交开始subject = " + Subject + ", xml = "  + xml);
			String returnStr = InterfaceClientUtil.sendPost(DomainUrlUtil.EJS_WMS_URL, xml, Subject);
			log.info("提交成功："+ returnStr);
			//为空表示发送成功
			if(returnStr != null){
				log.info("[EnteringWarehouse][goodsOwnerSync][" + paramMap.toString()
				+ "]货主信息同步成功：");
				ServiceResult<InterfaceWms> serviceResult= interfaceWmsService.getInterfaceByRelationIdAndRelationTable(relationId,relationTable);
				if(serviceResult.getSuccess()){
					InterfaceWms interfaceWms =serviceResult.getResult();
					//第一次推送失败
					if(interfaceWms == null){
						InterfaceWms interfaceWmsNew = new InterfaceWms();
						interfaceWmsNew.setRalationTable(relationTable);
						interfaceWmsNew.setRelationId(relationId);
						interfaceWmsNew.setSendNo(1);
						interfaceWmsNew.setSendResult("true");
						interfaceWmsNew.setSyncTime(new Date());
						interfaceWmsNew.setSyncType(Subject);
						interfaceWmsService.saveInterfaceWms(interfaceWmsNew);
						log.info("[EnteringWarehouse][saveInterfaceWms]插入数据成功，relationId为："+relationId+"relationTable为："+ relationTable);
						}else{
							interfaceWms.setSendResult("true");
							interfaceWms.setSyncTime(new Date());
							interfaceWms.setSyncType(Subject);
							interfaceWmsService.updateInterfaceWms(interfaceWms);
							log.info("[EnteringWarehouse][updateInterfaceWms]更新数据成功，relationId为："+relationId+"relationTable为："+ relationTable);
						}
					}else{
						log.error("[EnteringWarehouse][getInterfaceByRelationIdAndRelationTable][根据" + relationId
								+ "和"+relationTable+"]查询失败信息时出现未知异常："+serviceResult.getMessage());
					}
			}else{
				log.error("[EnteringWarehouse][goodsOwnerSync][" + paramMap.toString() + "]货主信息同步时出现未知异常：");
				ServiceResult<InterfaceWms> serviceResult= interfaceWmsService.getInterfaceByRelationIdAndRelationTable(relationId,relationTable);
				if(serviceResult.getSuccess()){
					InterfaceWms interfaceWms =serviceResult.getResult();
					//第一次推送失败
					if(interfaceWms == null){
						InterfaceWms interfaceWmsNew = new InterfaceWms();
						interfaceWmsNew.setRalationTable(relationTable);
						interfaceWmsNew.setRelationId(relationId);
						interfaceWmsNew.setSendNo(1);
						interfaceWmsNew.setSendResult("false");
						interfaceWmsNew.setSyncTime(new Date());
						interfaceWmsNew.setSyncType(Subject);
						interfaceWmsService.saveInterfaceWms(interfaceWmsNew);
						log.info("[EnteringWarehouse][saveInterfaceWms]插入数据成功，relationId为："+relationId+"relationTable为："+ relationTable);
					}else{
						interfaceWms.setSendNo(interfaceWms.getSendNo()+1);
						interfaceWmsService.updateInterfaceWms(interfaceWms);
						log.info("[EnteringWarehouse][updateInterfaceWms]更新数据成功，relationId为："+relationId+"relationTable为："+ relationTable);
					}
				}else{
					log.error("[EnteringWarehouse][getInterfaceByRelationIdAndRelationTable][根据" + relationId
					+ "和"+relationTable+"]查询失败信息时出现未知异常："+serviceResult.getMessage());
				}
			}
		}else{
			throw new BusinessException("货主信息同步时参数传输为空");
		}
	}
	
	//oms同步方法
	/**
	 * @param paramMap 传递的map参数
	 * @param api 调用的接口名称
	 */
//	public static synchronized String  commonSyncOMS(Map<String, Object> paramMap,String api){
//		
//		iLog.info("--------商城调用同步接口开始：参数为"+paramMap.toString()+",接口名称为："+api+"-------");
////		System.out.println(paramMap.toString());
//		String relationId = "";
//		String relationTable = "";
//		String errorMsg = "";
//		IInterfaceWmsService interfaceWmsService =null;
//		IOrdersService ordersService = null;
//		IMemberService memberService = null;
//		try {
//			interfaceWmsService = (IInterfaceWmsService)SpringContextUtil.getBean("interfaceWmsService");
//			ordersService = (IOrdersService)SpringContextUtil.getBean("ordersService");
//			memberService = (IMemberService)SpringContextUtil.getBean("memberService");
//		} catch (Exception e) {
//			if(interfaceWmsService == null){
//				ServletRequestAttributes attr = (ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
//				HttpServletRequest request = attr.getRequest();
//				ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
//				interfaceWmsService = (IInterfaceWmsService)context.getBean("interfaceWmsService");
//				ordersService = (IOrdersService)context.getBean("ordersService");
//				memberService = (IMemberService)context.getBean("memberService");
//			}
//		}
//		
//		if(paramMap != null  && paramMap.size() >0 ){
//			relationId = (String) paramMap.get(dawa_ttx_config.RELATION_ID);
//			relationTable = (String) paramMap.get(dawa_ttx_config.RELATION_TABLE);
//			paramMap.remove(dawa_ttx_config.RELATION_ID);
//			paramMap.remove(dawa_ttx_config.RELATION_TABLE);
//			if("".equals(relationId) || "".equals(relationTable)){
//				throw new BusinessException("关联id（relationId）和关联表名（relationTable）传输为空");
//			}
//		
//			try {
//				Map<String, Object> returnMap = InterfaceClientUtil.httpPostWithJSON(paramMap, api);
//				if(returnMap != null && returnMap.size() > 0){
//					String code = (String)returnMap.get("code");
//					errorMsg = (String)returnMap.get("msg");
//					
//					ServiceResult<InterfaceWms> serviceResult= interfaceWmsService.getInterfaceByRelationIdAndRelationTable(relationId,relationTable);
//					//成功
//					if(!"".equals(code) && code.equals("0")){
//						iLog.info("[EnteringWarehouse][commonSyncOMS][" + paramMap.toString() + "]接口["+api+"]同步成功：");
//						if(serviceResult.getSuccess()){
//							InterfaceWms interfaceWms = serviceResult.getResult();
//							//第一次推送失败
//							if(interfaceWms == null){
//								InterfaceWms interfaceWmsNew = new InterfaceWms();
//								interfaceWmsNew.setRalationTable(relationTable);
//								interfaceWmsNew.setRelationId(relationId);
//								interfaceWmsNew.setSendNo(1);
//								interfaceWmsNew.setSendResult("1");
//								interfaceWmsNew.setSyncTime(new Date());
//								interfaceWmsNew.setSyncType(api);
//								if(!StringUtil.isEmpty(errorMsg) && errorMsg.length() >= 1000){
//									interfaceWmsNew.setErrorMsg(errorMsg.substring(0, 1000));
//								}else{
//									interfaceWmsNew.setErrorMsg(errorMsg);
//								}
//								interfaceWmsService.saveInterfaceWms(interfaceWmsNew);
//								iLog.info("[commonSyncOMS][saveInterfaceWms]接口["+api+"]插入数据成功，relationId为："+relationId+"relationTable为："+ relationTable);
//							}else{
//								interfaceWms.setSendNo(interfaceWms.getSendNo()+1);
//								if(!StringUtil.isEmpty(errorMsg) && errorMsg.length() >= 1000){
//									interfaceWms.setErrorMsg(errorMsg.substring(0, 1000));
//								}else{
//									interfaceWms.setErrorMsg(errorMsg);
//								}
//								interfaceWmsService.updateInterfaceWms(interfaceWms);
//								iLog.info("[commonSyncOMS][updateInterfaceWms]接口["+api+"]更新数据成功，relationId为："+relationId+"relationTable为："+ relationTable);
//							}
//						}else{
//							iLog.error("[commonSyncOMS][getInterfaceByRelationIdAndRelationTable][根据" + relationId
//									+ "和"+relationTable+"]接口["+api+"]查询失败信息时出现未知异常："+serviceResult.getMessage());
//							throw new BusinessException(serviceResult.getMessage());
//						}
//						try {
//							if(dawa_ttx_config.ORDERS.equals(relationTable)){
//								Orders orders = ordersService.getOrdersBySn(relationId).getResult();
//								if(orders != null){
//									orders.setSyncState("已推送");
//									ordersService.updateOrders(orders);
//									//给供应商发送信息，告知售卖情况
//									ordersService.sendMessageToSeller(orders);
//									iLog.info("订单"+relationId+"已推送成功，状态改为已推送");
//								}
//							}
//							
//							if(dawa_ttx_config.MEMBER.equals(relationTable)){
//								Member member = memberService.getMemberById(Integer.parseInt(relationId)).getResult();
//								if(member != null){
//									member.setIsSyncOms("1");
//									memberService.updateMember(member);
//									iLog.info("会员"+member.getName()+"已推送成功，状态改为已同步oms");
//								}
//							}
//						} catch (Exception e) {
//							iLog.error("[commonSyncOMS]根据" + relationId
//									+ "和"+relationTable+"]接口["+api+"]更新状态出现未知异常："+serviceResult.getMessage());
//						}
//						//推送失败
//					}else{
//						iLog.error("[EnteringWarehouse][goodsOwnerSync][" + paramMap.toString()
//								+ "]接口["+api+"]同步时出现异常："+returnMap.get("msg").toString());
//						if(serviceResult.getSuccess()){
//							InterfaceWms interfaceWms =serviceResult.getResult();
//							//第一次推送失败
//							if(interfaceWms == null){
//								InterfaceWms interfaceWmsNew = new InterfaceWms();
//								interfaceWmsNew.setRalationTable(relationTable);
//								interfaceWmsNew.setRelationId(relationId);
//								interfaceWmsNew.setSendNo(1);
//								interfaceWmsNew.setSendResult("0");
//								interfaceWmsNew.setSyncTime(new Date());
//								interfaceWmsNew.setSyncType(api);
//								if(!StringUtil.isEmpty(errorMsg) && errorMsg.length() >= 1000){
//									interfaceWmsNew.setErrorMsg(errorMsg.substring(0, 1000));
//								}else{
//									interfaceWmsNew.setErrorMsg(errorMsg);
//								}
//								interfaceWmsService.saveInterfaceWms(interfaceWmsNew);
//								iLog.info("[EnteringWarehouse][saveInterfaceWms]接口["+api+"]插入数据成功，relationId为："+relationId+"relationTable为："+ relationTable);
//							}else{
//								interfaceWms.setSendNo(interfaceWms.getSendNo()+1);
//								if(!StringUtil.isEmpty(errorMsg) && errorMsg.length() >= 1000){
//									interfaceWms.setErrorMsg(errorMsg.substring(0, 1000));
//								}else{
//									interfaceWms.setErrorMsg(errorMsg);
//								}
//								interfaceWmsService.updateInterfaceWms(interfaceWms);
//								//订单多次推送三次以上发送邮件提醒 并修改订单 让该订单不再同步oms
//								//邮件提醒以后 需要客服 后台手动去改单
//								if (dawa_ttx_config.ORDERS.equals(relationTable)) {
//									if (interfaceWms.getSendNo()%3 == 0 ) {
//					                    SendMail send = new SendMail();
//					                    if (send.sendErrorLog(interfaceWms.getRelationId(), interfaceWms.getSyncTime(), interfaceWms.getSendNo()+"", interfaceWms.getErrorMsg())) {
//							    			 //设置为error 下次定时器就不会再去同步了
//							    			 Orders orders = ordersService.getOrdersBySn(relationId).getResult();
//											 if(orders != null){
//												orders.setSyncState("error");
//												ordersService.updateOrders(orders);
//											 }
//							    		 }
//									}
//								}
//								iLog.info("[EnteringWarehouse][updateInterfaceWms]接口["+api+"]更新数据成功，relationId为："+relationId+"relationTable为："+ relationTable);
//							}
//						} else {
//							iLog.error("[EnteringWarehouse][getInterfaceByRelationIdAndRelationTable][根据" + relationId
//							+ "和"+relationTable+"]接口["+api+"]查询失败信息时出现未知异常："+serviceResult.getMessage());
//							throw new BusinessException(serviceResult.getMessage());
//						}
//					}
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//				iLog.error("商城接口["+api+"]推送出错:"+e.getMessage());
//				throw new BusinessException(e.getMessage());
//			}
//		}else{
//			throw new BusinessException("接口["+api+"]同步出现异常");
//		}		
//		iLog.info("---------------------商城调用同步接口["+api+"]结束--------------------");
//		return errorMsg;
//	}
	
	
	//实例
	public static void main(String[] args) {
		Map<String, Object> map = new HashMap<String,Object>();
		//往map中put值的key 一定要与dawa_ttx_config中平台字段的名称一致，否则替换会出问题111
		//如：public static final String DAWA_SELLER_USER_WORDS="seller_name,name,member_id,address1,address2,phone,mobile";
		map.put("seller_name", "张三");
		map.put("name", "张三");
		map.put("member_id", "1");
		map.put("address1", "张三");
		map.put("address2", "张三");
		map.put("phone", "张三");
		map.put("mobile", "张三");
		//最后一定要放入该条记录的id和table名称
		map.put(dawa_ttx_config.RELATION_ID, "1");
		//名称需要从dawa_ttx_config拿 避免输入错误影响查询结果
		map.put(dawa_ttx_config.RELATION_TABLE, dawa_ttx_config.SELLER_USER);
		try {
			EnteringWarehouse.goodsOwnerSync(map, dawa_ttx_config.DAWA_SELLER_USER_WORDS, dawa_ttx_config.TTX_SELLER_USER_WORDS, dawa_ttx_config.SUBJECT_HEADER13);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
