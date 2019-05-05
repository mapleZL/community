package com.ejavashop.web.wmsinterface.controller;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dawa.web.controller.BaseController;
import com.ejavashop.core.ServiceResult;
import com.ejavashop.core.StringUtil;
import com.ejavashop.entity.order.Orders;
import com.ejavashop.entity.product.OrderReceipt;
import com.ejavashop.entity.product.ProductGoods;
import com.ejavashop.service.order.IOrdersProductService;
import com.ejavashop.service.order.IOrdersService;
import com.ejavashop.service.product.IOrderReceiptService;
import com.ejavashop.service.product.IProductGoodsService;
import com.ejavashop.util.interfacewms.HttpRequester;
import com.ejavashop.util.interfacewms.HttpRespons;
import com.ejavashop.util.interfacewms.RemoteParamHelper;
import com.ejavashop.util.interfacewms.XmlConvertUtil;
import com.ejavashop.util.interfacewms.dawa_ttx_config;

/**
 * oms 接口类
 * @author 小甜甜
 *
 */
@Controller
@RequestMapping(value = "dawa_oms")
public class InterfaceOmsController extends BaseController{
	
	private static final Logger  ILog = LogManager.getLogger("oms_interface");
	
	private static final RemoteParamHelper helper = new RemoteParamHelper();
	
	public static final ObjectMapper objectMapper = new ObjectMapper();
	
	private static final HttpRequester httpRequester = new HttpRequester();
	@Resource(name="orderReceiptService")
	private IOrderReceiptService orderReceiptService;
	@Resource(name="ordersService")
	private IOrdersService ordersService;
	@Resource(name = "productGoodsService")
	private IProductGoodsService productGoodsService;
	@Resource(name="ordersProductService")
	private IOrdersProductService ordersProductService;
	
	public static void main(String[] args) {
		Map<String, Object> map = new HashMap<String, Object>();
	
		map.put("userName", "MTY1");
		map.put("password", "1231231");
		
		Map<String, Object> map2 = new HashMap<String, Object>();
		map2.put("code", "10003");
		map2.put("name", "猫头鹰3");
		map2.put("logoUrl", "www.baidu.com");
		map2.put("fullName", "猫头鹰网仓");
		map2.put("attentionTo", "aaaa");
		map2.put("phoneNum1", "123123123");
		map2.put("phoneNum2", "456456456");
		map2.put("mobileNum", "13552438720");
		map2.put("email", "13552438720@163.com");
		map2.put("zip", "000000");
		map2.put("faxNum", "");
		map2.put("country", "china");
		map2.put("state", "浙江省");
		map2.put("city", "绍兴市");
		map2.put("district", "诸暨市");
		map2.put("address1", "千禧路20号");
		map2.put("address2", "大唐袜业市场二楼");
		
		map.put("corporation", map2);
		
		Map<String, String> params = helper.makeRequestDataParam(map,dawa_ttx_config.OMS_CORAPORATION_CREATE_COMPANY);
		try {
			HttpRespons hr = httpRequester.sendPost(dawa_ttx_config.OMS_BASE_URL, params);
			
			System.out.println(hr.getUrlString());     
	        System.out.println(hr.getProtocol());     
	        System.out.println(hr.getHost());     
	        System.out.println(hr.getPort());     
	        System.out.println(hr.getContentEncoding());     
	        System.out.println(hr.getMethod());     
	        
	        System.out.println(hr.getContent());   
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	    }
	
	@RequestMapping(value = "interfacecommon.html", method = { RequestMethod.GET,RequestMethod.POST })
    public void ReceiptsResponse(HttpServletRequest request,HttpServletResponse response){
		 /* BufferedReader bufferReader;
	        try {
	            bufferReader = request.getReader();//获取头部参数信息
	            StringBuffer buffer = new StringBuffer();
	            String line = " ";
	            while ((line = bufferReader.readLine()) != null) {
	                buffer.append(line);
	            }
	            String postData = buffer.toString();
	            System.out.println(postData);
	             
	            if (true)
	                return;
	        } catch (IOException e2) {
	            e2.printStackTrace();
	        }*/
		
		String api = request.getParameter("api");
		String data = request.getParameter("data");
		ILog.info("==================oms调用回传接口【start】，API为:"+api+",data为："+data+"==================");
		response.setCharacterEncoding("utf-8");
		String successReturnStr = "{\"code\":\"0\",\"msg\":\"Success\",\"data\":"+data+"}";
		String failMessage = "";
		try {
			if("".equals(api) || StringUtil.isEmpty(api)){
				failMessage = "api不能为空";
				String failReturnStr = "{\"code\":\"1\",\"msg\":\""+failMessage+"\",\"data\":"+data+"}";
				response.getWriter().write(failReturnStr);
				return ;
			}
			//入库单回传
			if(api.equals(dawa_ttx_config.OMS_RECEIPT_CONFIRM)){
				this.omsReceiptConfirm(data, failMessage, successReturnStr, request, response);
			}
			//出库单回传
			else if(api.equals(dawa_ttx_config.OMS_SHIPMENT_CONFIRM) || api.equals(dawa_ttx_config.OMS_OWNERTRANSFER_CONFIRM)){
				this.omsOrderConfirm(data, failMessage, successReturnStr, request, response);
			}
			//手工单据库存回传（oms.inventory.change）
			else if (api.equals(dawa_ttx_config.OMS_INVENTORY_CHANGE)) {
				this.omsInventoryChange(data, failMessage, successReturnStr, request, response);
			}
			//库存同步
			else if(api.equals(dawa_ttx_config.CORPORATION_WAREHOUSE_INVENTORY)){
				this.corporationWarehouseInventory(data, failMessage, successReturnStr, request, response);
			} else {
				failMessage = "未知的API";
				String failReturnStr = "{\"code\":\"1\",\"msg\":\""+failMessage+"\",\"data\":"+data+"}";
				response.getWriter().write(failReturnStr);
			}
		} catch (Exception e) {
			failMessage = "系统错误";
			String failReturnStr = "{\"code\":\"1\",\"msg\":\""+failMessage+"\",\"data\":"+data+"}";
			try {
				response.getWriter().write(failReturnStr);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			ILog.error("oms调用回传接口出错："+e.getMessage());
		}
		ILog.info("==================oms调用回传接口【end】，API为:"+api+",data为："+data+"==================");
	}
	
	/**
	 * 入库单回传
	 * @param data
	 * @param failMessage
	 * @param successReturnStr
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	
	private void omsReceiptConfirm (String data,String failMessage,String successReturnStr,HttpServletRequest request,HttpServletResponse response) throws IOException {
		Map<String, Object> map = XmlConvertUtil.parseJSON2Map(data);
		Map<String, Object> headerMap = (Map<String, Object>)map.get("header");
		String ordersSn = (String)headerMap.get("refOrderCode");
		if(!"".equals(ordersSn) || !StringUtil.isEmpty(ordersSn)){
			ServiceResult<OrderReceipt>	orderReceiptServiceResult =	orderReceiptService.getOrderReceiptByOrdersSn(ordersSn);
			if(orderReceiptServiceResult.getSuccess()){
				OrderReceipt  orderReceipt = orderReceiptServiceResult.getResult();
				if(orderReceipt != null){
					//设置入库单状态为已收货
					orderReceipt.setStatus(4);
					ServiceResult<Integer> result = orderReceiptService.updateOrderReceipt(orderReceipt);
					if (result.getSuccess()) {
						ILog.info("oms回传入库单（"+ordersSn+"）状态更新成功");
						response.getWriter().write(successReturnStr);
					} else {
						ILog.error("oms回传入库单（"+ordersSn+"）状态更新失败，错误："+result.getMessage());
						failMessage = "oms回传入库单（"+ordersSn+"）状态更新失败";
						String failReturnStr = "{\"code\":\"1\",\"msg\":\""+failMessage+"\",\"data\":"+data+"}";
						response.getWriter().write(failReturnStr);
					}
				}else{
					failMessage = "查询不到入库单号为："+ordersSn+"的入库单";
					String failReturnStr = "{\"code\":\"1\",\"msg\":\""+failMessage+"\",\"data\":"+data+"}";
					response.getWriter().write(failReturnStr);
					ILog.error(failMessage);
				}
			}else{
				failMessage = "系统错误";
				String failReturnStr = "{\"code\":\"1\",\"msg\":\""+failMessage+"\",\"data\":"+data+"}";
				response.getWriter().write(failReturnStr);
				ILog.error(failMessage);
			}
		}else{
			failMessage = "入库单号为空";
			ILog.error(failMessage);
			String failReturnStr = "{\"code\":\"1\",\"msg\":\""+failMessage+"\",\"data\":"+data+"}";
			response.getWriter().write(failReturnStr);
		}
	}
	
	/**
	 * 出库单回传
	 * @param data
	 * @param failMessage
	 * @param successReturnStr
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void omsOrderConfirm (String data,String failMessage,String successReturnStr,HttpServletRequest request,HttpServletResponse response) throws IOException {
		Map<String, Object> map = XmlConvertUtil.parseJSON2Map(data);
		//订单号
		String ordersSn = (String)map.get("header");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//出库时间
		Date shipmentDate;
		try {
			Map<String, Object> containersMap = (Map<String, Object>)map.get("containers");
			Map<String, Object> headerMap = (Map<String, Object>)containersMap.get("header");
			String shipDate = (String)headerMap.get("shipDate");
			shipmentDate = dateFormat.parse(shipDate);
		} catch (Exception e) {
			shipmentDate = new Date();
		}
		if(!"".equals(ordersSn) || !StringUtil.isEmpty(ordersSn)){
			ServiceResult<Orders> ordersServiceResult = ordersService.getOrdersBySn(ordersSn);
			if(ordersServiceResult.getSuccess()){
				Orders orders = ordersServiceResult.getResult();
				if(orders != null){
					orders.setOrderState(4);
					orders.setSyncState("已出库");
					orders.setOutTime(shipmentDate);
					ServiceResult<Integer> result =  ordersService.updateOrders(orders);
					if (result.getSuccess()) {
					    //给供应商发送信息，告知售卖情况
//                      ordersService.sendMessageToSeller(orders);
						ILog.info("oms回传出库单（"+ordersSn+"）状态更新成功");
						response.getWriter().write(successReturnStr);
					} else {
						ILog.error("oms回传出库单（"+ordersSn+"）状态更新失败，错误："+result.getMessage());
						failMessage = "oms回传出库单（"+ordersSn+"）状态更新失败";
						String failReturnStr = "{\"code\":\"1\",\"msg\":\""+failMessage+"\",\"data\":"+data+"}";
						response.getWriter().write(failReturnStr);
					}
					
				}else{
					failMessage = "查询不到订单号为："+ordersSn+"的订单";
					String failReturnStr = "{\"code\":\"1\",\"msg\":\""+failMessage+"\",\"data\":"+data+"}";
					response.getWriter().write(failReturnStr);
					ILog.error(failMessage);
				}
			}else{
				failMessage = "系统错误";
				String failReturnStr = "{\"code\":\"1\",\"msg\":\""+failMessage+"\",\"data\":"+data+"}";
				response.getWriter().write(failReturnStr);
				ILog.error(failMessage);
			}
		}else{
			failMessage = "订单号为空";
			ILog.error(failMessage);
			String failReturnStr = "{\"code\":\"1\",\"msg\":\""+failMessage+"\",\"data\":"+data+"}";
			response.getWriter().write(failReturnStr);
		}
	}
	
	/**
	 * 手工单据库存回传（oms.inventory.change）
	 * @param data
	 * @param failMessage
	 * @param successReturnStr
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void omsInventoryChange (String data,String failMessage,String successReturnStr,HttpServletRequest request,HttpServletResponse response) throws IOException {
		StringBuffer stringBt = new StringBuffer();
		ILog.info("接收到从oms传过来的json为："+data);
		Map<String, Object> map = XmlConvertUtil.parseJSON2Map(data);
		Map<String, Object>  headerMap = (Map<String, Object>)map.get("header");
		//仓库编号
		String warehouseCode = (String)headerMap.get("warehouseCode");
		//入库：PO_RECEIV采购入库; WORK_RECEIV加工入库;NORMAL_RECEIV普通入库;RETURN_RECEIV退货入库;TRANSFER_RECEIV调拨入库;
		//出库：PO_RETUEN_SHIP:采退出库锁定;ORDER_SHIP:手工订单锁定;WORK_SHIP加工出库锁定;TRANSFER_SHIP调拨出库锁定；
		//拦截：SHIP_CANCEL
		//库存调整：ADJUST
		String type = (String)headerMap.get("type");
		if (dawa_ttx_config.WAREHOUSE_CODE.equals(warehouseCode)) {
			List<Map<String, Object>> itemList = (List<Map<String, Object>>) map.get("details");
			for (Map<String, Object> itemMap : itemList) {
				//商品编号
				String skuCode = (String)itemMap.get("skuCode");
				//货主编码
				String companyCode = (String)itemMap.get("companyCode");
				//调整方向 IN:增;OUT:减
				String direction = (String)itemMap.get("direction");
				//数量
				Integer stock = (Integer)itemMap.get("qty");
				if (stock == null) {
					stock = 0;
				}
				//库存状态  AVAILABLE:可售;NOT_AVAILABLE:不可售,默认AVAILABLE
				String inventorySts = (String)itemMap.get("inventorySts");
				//渠道  PLAT:平台;MALL:商城（商城只看MALL）
				String channel = (String)itemMap.get("channel");
				if ("AVAILABLE".equals(inventorySts) && "MALL".equals(channel)) {
					ServiceResult<List<ProductGoods>> productGoodsResult = productGoodsService.getBySkuAndMember(skuCode, companyCode);
					if (productGoodsResult.getSuccess()) {
						List<ProductGoods> productGoodsList = productGoodsResult.getResult();
						for (ProductGoods productGoods : productGoodsList) {
							ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
							if ("IN".equals(direction)) {
								ILog.info("oms("+new Date()+")增加("+type+")供应商("+companyCode+")的sku("+skuCode+")的库存为：++++++++"+stock);
								stringBt.append("@@@@@@oms("+new Date()+")增加("+type+")供应商("+companyCode+")的sku("+skuCode+")的库存为：++++++++"+stock+"@@@@@@");
								stringBt.append("\r\n");
								productGoods.setProductStock(productGoods.getProductStock()+stock);
								serviceResult =	productGoodsService.updateProductGoods(productGoods);
								if (serviceResult.getSuccess() && serviceResult.getResult()) {
									ILog.info("oms("+new Date()+")增加("+type+")供应商("+companyCode+")的sku("+skuCode+")的库存后，库存为："+productGoods.getProductStock());
									stringBt.append("######oms("+new Date()+")增加("+type+")供应商("+companyCode+")的sku("+skuCode+")的库存成功，库存为："+productGoods.getProductStock()+"########");
									stringBt.append("\r\n");
								} else {
									failMessage = "sku("+skuCode+")和供应商编码("+companyCode+")商城更新库存失败";
									stringBt.append("!!!!!!!!!!!oms("+new Date()+")增加("+type+")供应商("+companyCode+")的sku("+skuCode+")的库存失败!!!!!!!!!!!");
									stringBt.append("\r\n");
									String failReturnStr = "{\"code\":\"1\",\"msg\":\""+failMessage+"\",\"data\":"+data+"}";
									ILog.error("oms("+new Date()+")增加("+type+")供应商("+companyCode+")的sku("+skuCode+")的库存失败");
									response.getWriter().write(failReturnStr);
								}
							} else if ("OUT".equals(direction)) {
								ILog.info("oms减少("+type+")供应商("+companyCode+")的sku("+skuCode+")的库存为：-----------"+stock);
								stringBt.append("@@@@@@oms("+new Date()+")减少("+type+")供应商("+companyCode+")的sku("+skuCode+")的库存为：-----------"+stock+"@@@@@@");
								stringBt.append("\r\n");
								Integer endStock = productGoods.getProductStock()-stock;
								if (endStock < 0) {
									endStock = 0;
									ILog.error("oms("+new Date()+")减少("+type+")供应商("+companyCode+")的sku("+skuCode+")的库存后为负值，调整为0");
									stringBt.append("!!!!!!!!!!!oms("+new Date()+")减少("+type+")供应商("+companyCode+")的sku("+skuCode+")的库存后为负值,调整为0!!!!!!!!!!!");
									stringBt.append("\r\n");
									failMessage = "sku("+skuCode+")和供应商编码("+companyCode+")商城扣减库存之后为负数，商城库存调整为0";
									String failReturnStr = "{\"code\":\"1\",\"msg\":\""+failMessage+"\",\"data\":"+data+"}";
									response.getWriter().write(failReturnStr);
								}
								productGoods.setProductStock(endStock);
								serviceResult = productGoodsService.updateProductGoods(productGoods);
								if (serviceResult.getSuccess() && serviceResult.getResult()) {
									ILog.info("oms减少("+type+")供应商("+companyCode+")的sku("+skuCode+")的库存后，库存为："+productGoods.getProductStock());
									stringBt.append("######oms("+new Date()+")减少("+type+")供应商("+companyCode+")的sku("+skuCode+")的库存成功，库存为："+productGoods.getProductStock()+"########");
									stringBt.append("\r\n");
								} else {
									failMessage = "sku("+skuCode+")和供应商编码("+companyCode+")商城更新库存失败";
									String failReturnStr = "{\"code\":\"1\",\"msg\":\""+failMessage+"\",\"data\":"+data+"}";
									stringBt.append("!!!!!!!!!!!oms("+new Date()+")减少("+type+")供应商("+companyCode+")的sku("+skuCode+")的库存失败!!!!!!!!!!!");
									stringBt.append("\r\n");
									response.getWriter().write(failReturnStr);
									ILog.error("oms("+new Date()+")减少("+type+")供应商("+companyCode+")的sku("+skuCode+")的库存失败");
								}
							} else {
								failMessage = "sku("+skuCode+")和供应商编码("+companyCode+")未知的调整方向";
								String failReturnStr = "{\"code\":\"1\",\"msg\":\""+failMessage+"\",\"data\":"+data+"}";
								ILog.error("oms("+new Date()+")调整("+type+")供应商("+companyCode+")的sku("+skuCode+")的库存失败");
								response.getWriter().write(failReturnStr);
								stringBt.append("!!!!!!!!!!!oms("+new Date()+")sku("+skuCode+")和供应商编码("+companyCode+")未知的调整("+type+")方向!!!!!!!!!!!");
								stringBt.append("\r\n");
							}
						}
					} else {
						failMessage = productGoodsResult.getMessage();
						String failReturnStr = "{\"code\":\"1\",\"msg\":\""+failMessage+"\",\"data\":"+data+"}";
						response.getWriter().write(failReturnStr);
						ILog.error("根据sku("+skuCode+")和供应商编码("+companyCode+")查询商品详情失败");
						stringBt.append("!!!!!!!!!!!oms("+new Date()+")根据sku("+skuCode+")和供应商编码("+companyCode+")查询商品详情失败!!!!!!!!!!!");
						stringBt.append("\r\n");
					}
				}
			}
			//更新spu库存
			ServiceResult<Boolean> servieResult1 =productGoodsService.updateSpuStock();
			if (servieResult1.getSuccess() && servieResult1.getResult()) {
				ILog.info("库存同步完成，更新spu库存成功");
			} else {
				ILog.error("库存同步完成，更新spu库存失败");
			}
			response.getWriter().write(successReturnStr);
			com.ejavashop.util.interfacewms.XmlConvertUtil.stockChange(request, stringBt);
		}
	}
	/**
	 * 库存同步接口
	 * @param data
	 * @param failMessage
	 * @param successReturnStr
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void corporationWarehouseInventory (String data,String failMessage,String successReturnStr,HttpServletRequest request,HttpServletResponse response) throws IOException {
		ILog.info("接收到从oms传过来的json为："+data);
		Map<String, Object> map = XmlConvertUtil.parseJSON2Map(data);
		StringBuffer writerStr = new StringBuffer();
		List<Map<String, Object>> itemList = (List) map.get("details");
		String dateTime = (String) map.get("dateTime");
		String wareHouse = (String)map.get("warehouseCode");
		//只同步仓库为“MZJ”的商品库存
		if(!StringUtil.isEmpty(wareHouse) && dawa_ttx_config.WAREHOUSE_CODE.equals(wareHouse)){
			for (Map<String, Object> itemMap : itemList) {
				//供应商编码
				String logStr = "";
				String company = (String) itemMap.get("companyCode");
				/*if("MTY".equals(company)){
					company = "10001";
				}*/
				//商品sku
				String item = (String) itemMap.get("skuCode");
				//商品数量
				String itemQty = itemMap.get("onHandQty").toString();
				//商品状态
				String inventorySts = (String) itemMap.get("inventorySts");
				ILog.info("取得参数为Company:"+company+"@@@@@WareHouse:"+wareHouse+"@@@@@Item:"+item+"@@@@@itemQty:"+itemQty+"@@@@@inventorySts"+inventorySts+"dateTime"+dateTime);
				if("AVAILABLE".equals(inventorySts)){
					if(!"".equals(item)){
						ServiceResult<List<ProductGoods>> productGoodsResult = productGoodsService.getBySkuAndMember(item, company);
						if(productGoodsResult.getSuccess()){
							List<ProductGoods> productGoodsList = productGoodsResult.getResult();
							for (ProductGoods productGoods : productGoodsList) {
								if(productGoods !=null){
									Integer productStock = productGoods.getProductStock();
									productGoods.setProductStock(Integer.parseInt(itemQty));
									ServiceResult<Boolean> serviceResult =  productGoodsService.updateProductGoods(productGoods);
									if(serviceResult.getSuccess()){
										ILog.info("oms于"+new  Date() +"更新商品库存成功，商品sku为:"+item);
										logStr = "####更新库存成功####，sku为："+item+"，货主编码为："+company+"，oms库存为："+itemQty+"，平台库存为："+productStock;
									}else{
										ILog.error("oms于"+new  Date() +"更新商品库存失败，商品sku为:"+item);
										logStr = "####更新库存失败####，sku为："+item+"，货主编码为："+company+"，oms库存为："+itemQty+"，平台库存为："+productStock+"，错误信息："+serviceResult.getMessage();
									}
								}else{
									logStr = "####查询为空（sku和货主匹配错误）####，sku为："+item+"，货主编码为："+company+"，oms库存为："+itemQty+"，平台库存为：";
								}
							}
						}else{
							failMessage = productGoodsResult.getMessage();
							String failReturnStr = "{\"code\":\"1\",\"msg\":\""+failMessage+"\",\"data\":"+data+"}";
							response.getWriter().write(failReturnStr);
							logStr = "####查询出错####，sku为："+item+"，货主编码为："+company+"，oms库存为："+itemQty+"，平台库存为：，错误信息："+productGoodsResult.getMessage();
							ILog.error("根据sku和供应商编码查询商品详情失败");
						}
					}else{
						failMessage = "接收到从oms传过来的item为空";
						String failReturnStr = "{\"code\":\"1\",\"msg\":\""+failMessage+"\",\"data\":"+data+"}";
						logStr = "####传输错误####，sku为："+item+"，货主编码为："+company+"，oms库存为："+itemQty+"，平台库存为：，错误信息：";
						response.getWriter().write(failReturnStr);
						ILog.error("接收到从oms传过来的item为空");
					}
					//记录推送信息
					writerStr.append(logStr);
					writerStr.append("\r\n");
				}	
			}
			response.getWriter().write(successReturnStr);
			com.ejavashop.util.interfacewms.XmlConvertUtil.makeRecord(request, writerStr);
			//在更新spu库存前 先减掉订单状态为1 ，2，3，8库存
			ServiceResult<Boolean> servieResult = ordersProductService.getWaitedStock(request.getSession().getServletContext());
			if (servieResult.getSuccess() && servieResult.getResult()) {
				ILog.info("库存同步完成，扣减待出库库存成功");
			} else {
				ILog.error("库存同步完成，扣减待出库库存失败");
			}
			//更新spu库存
			ServiceResult<Boolean> servieResult1 =productGoodsService.updateSpuStock();
			if (servieResult1.getSuccess() && servieResult1.getResult()) {
				ILog.info("库存同步完成，更新spu库存成功");
			} else {
				ILog.error("库存同步完成，更新spu库存失败");
			}
			return;
		}else{
			//response.getWriter().write("{\"code\":\"1\",\"msg\":\"商城只同步wareHouse为MZJ的商品\",\"data\":"+data+"}");
		}
	
		
	}
	
	 @RequestMapping(value = "omsInventoryRequest", method = { RequestMethod.GET,RequestMethod.POST })
		public void syncProductStock(HttpServletRequest request,HttpServletResponse response){
			ILog.info("oms于"+new Date()+"调用同步商品库存（syncProductStock）接口############################Start################################");
			response.setContentType("text/json");
			String failMessage = "";
			try {
				//String successXml = "{\"code\":\"0\",\"msg\":\"Success\",\"data\":\"{}\"}";
				//String failXml = "{\"code\":\"1\",\"msg\":\"接收库存失败，请稍后再试！\",\"data\":\"{}\"}";
				//PrintWriter out = response.getWriter();
				//BufferedInputStream reader = new BufferedInputStream(request.getInputStream());
				InputStreamReader reader = new InputStreamReader(request.getInputStream(),"utf-8");
				char[] bytes = new char[1024];
				int length = 0;
				StringBuffer sb = new StringBuffer();
				while((length = reader.read(bytes)) >0){
					//xml = new String(bytes, 0, length);
					sb.append(new String(bytes, 0, length));
				  }
				reader.close();
				response.setCharacterEncoding("utf-8");
				String successReturnStr = "{\"code\":\"0\",\"msg\":\"Success\",\"data\":"+sb.toString()+"}";
				String data = URLDecoder.decode(sb.toString(),"utf-8");
				ILog.info("接收到从oms传过来的json为："+data);
				System.out.println(data.toString());
				Map<String, Object> map = XmlConvertUtil.parseJSON2Map(data.toString());
				
				StringBuffer writerStr = new StringBuffer();
				List<Map<String, Object>> itemList = (List) map.get("details");
				String dateTime = (String) map.get("dateTime");
				String wareHouse = (String)map.get("warehouseCode");
				//只同步仓库为“MZJ”的商品库存
				if(!StringUtil.isEmpty(wareHouse) && dawa_ttx_config.WAREHOUSE_CODE.equals(wareHouse)){
					for (Map<String, Object> itemMap : itemList) {
						//供应商编码
						String logStr = "";
						String company = (String) itemMap.get("companyCode");
						/*if("MTY".equals(company)){
							company = "10001";
						}*/
						//商品sku
						String item = (String) itemMap.get("skuCode");
						//商品数量
						String itemQty = itemMap.get("onHandQty").toString();
						//商品状态
						String inventorySts = (String) itemMap.get("inventorySts");
						ILog.info("取得参数为Company:"+company+"@@@@@WareHouse:"+wareHouse+"@@@@@Item:"+item+"@@@@@itemQty:"+itemQty+"@@@@@inventorySts"+inventorySts+"dateTime"+dateTime);
						if("AVAILABLE".equals(inventorySts)){
							if(!"".equals(item)){
								ServiceResult<List<ProductGoods>> productGoodsResult = productGoodsService.getBySkuAndMember(item, company);
								if(productGoodsResult.getSuccess()){
									List<ProductGoods> productGoodsList = productGoodsResult.getResult();
									for (ProductGoods productGoods : productGoodsList) {
										if(productGoods !=null){
											Integer productStock = productGoods.getProductStock();
											productGoods.setProductStock(Integer.parseInt(itemQty));
											ServiceResult<Boolean> serviceResult =  productGoodsService.updateProductGoods(productGoods);
											if(serviceResult.getSuccess()){
												ILog.info("oms于"+new  Date() +"更新商品库存成功，商品sku为:"+item);
												logStr = "####更新库存成功####，sku为："+item+"，货主编码为："+company+"，oms库存为："+itemQty+"，平台库存为："+productStock;
											}else{
												ILog.info("oms于"+new  Date() +"更新商品库存失败，商品sku为:"+item);
												logStr = "####更新库存失败####，sku为："+item+"，货主编码为："+company+"，oms库存为："+itemQty+"，平台库存为："+productStock+"，错误信息："+serviceResult.getMessage();
											}
										}else{
											logStr = "####查询为空（sku和货主匹配错误）####，sku为："+item+"，货主编码为："+company+"，oms库存为："+itemQty+"，平台库存为：";
										}
									}
								}else{
									failMessage = productGoodsResult.getMessage();
									String failReturnStr = "{\"code\":\"1\",\"msg\":\""+failMessage+"\",\"data\":"+data+"}";
									response.getWriter().write(failReturnStr);
									logStr = "####查询出错####，sku为："+item+"，货主编码为："+company+"，oms库存为："+itemQty+"，平台库存为：，错误信息："+productGoodsResult.getMessage();
									ILog.info("根据sku和供应商编码查询商品详情失败");
								}
							}else{
								failMessage = "接收到从oms传过来的item为空";
								String failReturnStr = "{\"code\":\"1\",\"msg\":\""+failMessage+"\",\"data\":"+data+"}";
								logStr = "####传输错误####，sku为："+item+"，货主编码为："+company+"，oms库存为："+itemQty+"，平台库存为：，错误信息：";
								response.getWriter().write(failReturnStr);
								ILog.info("接收到从oms传过来的item为空");
							}
							//记录推送信息
							writerStr.append(logStr);
							writerStr.append("\r\n");
						}	
					}
					com.ejavashop.util.interfacewms.XmlConvertUtil.makeRecord(request, writerStr);
					//在更新spu库存前 先减掉订单状态为1 ，2，3，8库存
					ordersProductService.getWaitedStock(request.getSession().getServletContext());
					//更新spu库存
					productGoodsService.updateSpuStock();
					response.getWriter().write(successReturnStr);
					return;
				}else{
					//response.getWriter().write("{\"code\":\"1\",\"msg\":\"商城只同步wareHouse为MZJ的商品\",\"data\":"+data+"}");
				}
			} catch (IOException e) {
				e.printStackTrace();
				failMessage = "系统错误";
				String failReturnStr = "{\"code\":\"1\",\"msg\":\""+failMessage+"\",\"data\":}";
				try {
					response.getWriter().write(failReturnStr);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				ILog.error(e.getMessage());
			}
			ILog.info("oms于"+new Date()+"调用同步商品库存（syncProductStock）接口############################End################################");
		}
	
	
	

}
