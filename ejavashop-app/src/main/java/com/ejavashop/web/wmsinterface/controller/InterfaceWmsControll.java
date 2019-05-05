package com.ejavashop.web.wmsinterface.controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ejavashop.core.ServiceResult;
import com.ejavashop.entity.order.Orders;
import com.ejavashop.entity.product.OrderReceipt;
import com.ejavashop.entity.product.ProductGoods;
import com.ejavashop.service.order.IOrdersProductService;
import com.ejavashop.service.order.IOrdersService;
import com.ejavashop.service.product.IOrderReceiptService;
import com.ejavashop.service.product.IProductGoodsService;
import com.ejavashop.util.interfacewms.XmlConvertUtil;
/**
 * @author lenovo
 *接受wms推送接口
 */
@Controller
@RequestMapping(value = "dawa")
public class InterfaceWmsControll {
	
	private static Logger log = LogManager.getLogger(InterfaceWmsControll.class);
	@Resource(name = "productGoodsService")
	private IProductGoodsService productGoodsService;
	@Resource(name="orderReceiptService")
	private IOrderReceiptService orderReceiptService;
	@Resource(name="ordersService")
	private IOrdersService ordersService;
	@Resource(name="ordersProductService")
	private IOrdersProductService ordersProductService;
	
	/**
	 * @param request
	 * @param response
	 * @return
	 * 接受wms推送商品库存
	 */
//	@RequestMapping(value = "/inventoryRequest", method = { RequestMethod.GET, RequestMethod.POST})
    @RequestMapping(value = "inventoryRequest", method = { RequestMethod.GET,RequestMethod.POST })
	public void syncProductStock(HttpServletRequest request,HttpServletResponse response){
		log.info("wms于"+new Date()+"调用同步商品库存（syncProductStock）接口############################Start################################");
		response.setContentType("text/xml");
		response.setCharacterEncoding("utf-8");
		try {
			String failXml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
					+"<Response>"
					+"<Success>false</Success>"
					+"<Code>103</Code>"
					+"<Description>接收库存失败，请稍后再试！</Description>"
					+"</Response>";
			String successXml ="<?xml version=\"1.0\" encoding=\"utf-8\"?>"
					+"<Response>"
					+"<Success>true</Success>"
					+"</Response>";
			PrintWriter out = response.getWriter();
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
			String xml = sb.toString();
				if("".equals(xml)){
					out.print(failXml);
					log.info("接收到从wms传过来的xml为空");
					return;
				}else{
						log.info("接收到从wms传过来的xml为："+xml);
					    List<Map<String, Object>> list = XmlConvertUtil.parseJSON2List(xml);
						//调用接口查询库存
					    StringBuffer writerStr = new StringBuffer();
						if(list !=null && list.size() > 0){
							for (Map<String, Object> map : list) {

						        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>执行不良品，将库存同步为0。开始于："+new Date());
						        writerStr.append(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>执行不良品，将库存同步为0。开始于："+new Date());
				                writerStr.append("\r\n");
                                syncStock(map, "不良品", writerStr, failXml, out);
                                log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>执行不良品结束于："+new Date());
                                writerStr.append(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>执行不良品结束于："+new Date());
                                writerStr.append("\r\n");

                                log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>执行良品，将库存同步为WMS实际库存。开始于："+new Date());
                                writerStr.append(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>执行良品，将库存同步为WMS实际库存。开始于："+new Date());
                                writerStr.append("\r\n");
							    syncStock(map, "良品", writerStr, failXml, out);
                                log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>执行良品结束于："+new Date());
                                writerStr.append(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>执行良品结束于："+new Date());
                                writerStr.append("\r\n");
								
							}
							XmlConvertUtil.makeRecord(request, writerStr);
							//在更新spu库存前 先减掉订单状态为1 ，2，3，8库存
							ordersProductService.getWaitedStock(request.getSession().getServletContext());
							//更新spu库存
							productGoodsService.updateSpuStock();
							out.print(successXml);
							return;
						}else{
							log.info("xml转化map为空");
							out.print(failXml);
							return;
						}
			 }	
			
		} catch (IOException e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
		log.info("wms于"+new Date()+"调用同步商品库存（syncProductStock）接口############################End################################");
	}
    
    
    /**
     * 同步库存，
     * @param map
     * @param writerStr
     * @param failXml
     */
    private void syncStock(Map<String, Object> map, String key, StringBuffer writerStr, String failXml, PrintWriter out) {
        List<Map<String, Object>> itemList = (List) map.get("Items");
        String dateTime = (String) map.get("DateTime");
        String wareHouse = (String)map.get("WareHouse");
        for (Map<String, Object> itemMap : itemList) {
            //供应商编码
            StringBuffer logStr = new StringBuffer();
            String company = (String) itemMap.get("Company");
            /*if("MTY".equals(company)){
                company = "10001";
            }*/
            //商品sku
            String item = (String) itemMap.get("Item");
            //商品数量
            String itemQty = (String) itemMap.get("ItemQty");
            //商品状态
            String inventorySts = "";
            try {
                inventorySts = (String) itemMap.get("InventorySts");
            } catch (Exception e) {
                inventorySts = "良品";
            }
            if(key.equals(inventorySts)){
                log.info("取得参数为Company:"+company+"@@@@@WareHouse:"+wareHouse+"@@@@@Item:"+item+"@@@@@itemQty:"+itemQty+"@@@@@inventorySts"+inventorySts+"dateTime"+dateTime);
                if(!"".equals(item)){
                    ServiceResult<List<ProductGoods>> productGoodsResult = productGoodsService.getBySkuAndMember(item, company);
                    if(productGoodsResult.getSuccess()){
                        List<ProductGoods> productGoodsList = productGoodsResult.getResult();
                        if (productGoodsList != null && productGoodsList.size() > 0) {
                            for (ProductGoods productGoods : productGoodsList) {
                                if(productGoods !=null){
                                    Integer productStock = productGoods.getProductStock();
                                    
                                    if ("不良品".equals(key)) 
                                        productGoods.setProductStock(0);                                
                                    else
                                        productGoods.setProductStock(Integer.parseInt(itemQty));
                                    
                                    ServiceResult<Boolean> serviceResult =  productGoodsService.updateProductGoods(productGoods);
                                    if(serviceResult.getSuccess()){
                                        log.info("wms于"+new  Date() +"更新商品库存成功，商品sku为:"+item);
                                        logStr.append("####更新库存成功####，sku为："+item+"，货主编码为："+company+"，wms库存为："+itemQty+"，平台库存为："+productStock+"商品状态为："+productGoods.getState());
                                    }else{
                                        log.info("wms于"+new  Date() +"更新商品库存失败，商品sku为:"+item);
                                        logStr.append("####更新库存失败####，sku为："+item+"，货主编码为："+company+"，wms库存为："+itemQty+"，平台库存为："+productStock+"，错误信息："+serviceResult.getMessage());
                                    }
                                }else{
                                    logStr.append("####查询为空（sku和货主匹配错误）####，sku为："+item+"，货主编码为："+company+"，wms库存为："+itemQty+"，平台库存为：");
                                }
                            }
                        }
                        else {
                            logStr.append("####无查询结果####，sku为："+item+"，货主编码为："+company+"，wms库存为："+itemQty);
                            log.info("根据sku和供应商编码无查询结果");
                        }
                    }else{
                        out.print(failXml);
                        logStr.append("####查询出错####，sku为："+item+"，货主编码为："+company+"，wms库存为："+itemQty+"，平台库存为：，错误信息："+productGoodsResult.getMessage());
                        log.info("根据sku和供应商编码查询商品详情失败");
                    }
                }else{
                    logStr.append("####传输错误####，sku为："+item+"，货主编码为："+company+"，wms库存为："+itemQty+"，平台库存为：，错误信息：");
                    out.print(failXml);
                    log.info("接收到从wms传过来的item为空");
                }
                //记录推送信息
                writerStr.append(logStr);
                writerStr.append("\r\n");
            }   
        }
    }
    
    /**
     * 推送入库单反馈
     * @param request
     * @param response
     */
    
    @RequestMapping(value = "receiptsResponse", method = { RequestMethod.GET,RequestMethod.POST })
    public void ReceiptsResponse(HttpServletRequest request,HttpServletResponse response){
    	log.info("wms于"+new Date()+"调用推送入库单反馈（ReceiptsResponse）接口#########################Start############################");
		response.setContentType("text/xml");
		response.setCharacterEncoding("utf-8");
		String failXml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
				+"<Response>"
				+"<Success>false</Success>"
				+"<Code>103</Code>"
				+"<Description>接收入库单反馈失败！</Description>"
				+"</Response>";
		String successXml ="<?xml version=\"1.0\" encoding=\"utf-8\"?>"
				+"<Response>"
				+"<Success>true</Success>"
				+"</Response>";
		try {
			PrintWriter out = response.getWriter();
			InputStreamReader reader = new InputStreamReader(request.getInputStream(),"utf-8");
			//InputStreamReader reader = new InputStreamReader(request.getInputStream(),"utf-8");
			char[] bytes = new char[1024];
			int length = 0;
			String xml = null;
			StringBuffer sb =  new StringBuffer();
			while((length = reader.read(bytes)) !=-1){
				sb.append(new String(bytes, 0, length));
			}
			xml = sb.toString();
				 if("".equals(xml)){
						out.print(failXml);
						log.info("接收到从wms传过来的xml为空");
						return;
					}else{
							log.info("接收到从wms传过来的xml为："+xml);
						    List<Map<String, Object>> list = XmlConvertUtil.parseJSON2List(xml);
							//调用接口查询库存
							if(list !=null && list.size() > 0){
								for (Map<String, Object> map : list) {
									//货主代码
									Map<String, Object> newMap1 = (Map<String, Object> )map.get("RECEIPT");
									String company = (String) newMap1.get("Company");
									//仓库代码
									String wareHouse = (String) newMap1.get("WareHouse");
									//入库单号
									String receiptId = (String) newMap1.get("ReceiptId");
									//入库类型
									String receiptType = (String) newMap1.get("ReceiptType");
									//入库时间
									String receiptDate = (String) newMap1.get("ReceiptDate");
									//备注
									// [] Remark = (String[]) newMap1.get("Remark");
									@SuppressWarnings("unchecked")
									List<Map<String, 	Object>> itemList =  (List<Map<String, Object>>) newMap1.get("Items");
									if(itemList != null && itemList.size() > 0){
										for (Map<String, 	Object> newMap : itemList) {
											//商品编码
											String item = (String) newMap.get("Item"); 
											//商品描述
											String itemName = (String) newMap.get("ItemName"); 
											//收货数量
											String itemCount = (String) newMap.get("ItemCount"); 
											//商品状态
											//JSONArray itemStatus = (JSONArray) newMap.get("ItemStatus"); 
											 String itemStatus = (String) newMap.get("ItemStatus"); 
											//收货批次
											//String lot = (String) newMap.get("Lot"); 
											
											/**
											 * 更新平台商品库存
											 */
											ServiceResult<List<ProductGoods>> productGoodsServiceResult = productGoodsService.getBySkuAndMember(item, company);
											if(productGoodsServiceResult.getSuccess()){
												List<ProductGoods> productGoodsList = productGoodsServiceResult.getResult();
												for (ProductGoods productGoods : productGoodsList) {
													if(productGoods !=null){
														productGoods.setProductStock(productGoods.getProductStock()+Integer.parseInt(itemCount));
														ServiceResult<Boolean> serviceResult =  productGoodsService.updateProductGoods(productGoods);
														if(serviceResult.getSuccess()){
															log.info("wms于"+new  Date() +"更新商品库存成功，商品sku为:"+item);
														}else{
															log.info("wms于"+new  Date() +"更新商品库存失败，商品sku为:"+item);
														}
													}
												}
											}else{
												log.info("查询商品信息出错");
											}
										}
									}else{
										out.print(failXml);
										log.info("解析xml商品详情为空");
										return;
									}
								}
								out.print(successXml);
								return;
								}else{
									out.print(failXml);
									log.info("解析xml为空");
									return;
								}
							}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
		log.info("wms于"+new Date()+"调用推送入库单反馈（ReceiptsResponse）接口#########################End############################");
    }
    /**
     * 推送出库单状态流
     * @param request
     * @param response
     */
    @RequestMapping(value = "shipmentStatusResponse", method = { RequestMethod.GET,RequestMethod.POST })
    public void ShipmentStatusResponse(HttpServletRequest request,HttpServletResponse response){
    	log.info("wms于"+new Date()+"推送出库单状态流（ShipmentStatusResponse）接口#####################Start##################################");
		response.setContentType("text/xml");
		response.setCharacterEncoding("utf-8");
		try {
			PrintWriter out = response.getWriter();
			//InputStreamReader reader = new InputStreamReader(request.getInputStream(),"utf-8");
			InputStreamReader reader = new InputStreamReader(request.getInputStream(),"utf-8");
			//InputStreamReader reader = new InputStreamReader(request.getInputStream(),"utf-8");
			char[] bytes = new char[1024];
			int length = 0;
			String xml = null;
			StringBuffer sb =  new StringBuffer();
			while((length = reader.read(bytes)) !=-1){
				sb.append(new String(bytes, 0, length));
			}
			xml = sb.toString();
				String failXml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
						   +"<Response>"
				           +"<Success>false</Success>"
				           +"<Code>100</Code>"
				           +"<Description>接收订单状态流失败！</Description>"
				           +"</Response>";
				String successXml ="<?xml version=\"1.0\" encoding=\"utf-8\"?>"
											+"<Response>"
											+"<Success>true</Success>"
											+"</Response>";
				if("".equals(xml)){
					out.print(failXml);
					log.info("接收到从wms传过来的xml为空");
					return;
				}else{
						log.info("接收到从wms传过来的xml为："+xml);
					    List<Map<String, Object>> list = XmlConvertUtil.parseJSON2List(xml);
					  //调用接口查询库存
						if(list !=null && list.size() > 0){
							for (Map<String, Object> map : list) {
								//仓库
								String wareHouse = (String)map.get("WareHouse");
								//货主
								String company = (String)map.get("Company");
								try {
									List<Map<String, 	Object>> itemList =  (List<Map<String, Object>>) map.get("ShipmentStatus");
									if(itemList != null  && itemList.size() > 0){
										for (Map<String, Object> map2 : itemList) {
											//订单号
											String shipmentId = (String)map2.get("ShipmentId");
											//订单状态
											String status = (String)map2.get("Status");
											//订单状态中文描述
											String stepName = (String)map2.get("StepName");
											//操作时间
											String operationTime = (String)map2.get("OperationTime");
											//操作人
											String operationBy = (String)map2.get("OperationBy");
											
											//更新入库单状态
											ServiceResult<Orders> ordersResult = ordersService.getOrdersBySn(shipmentId);
											if(ordersResult.getSuccess()){
												Orders orders = ordersResult.getResult();
												if(orders != null){
													orders.setSyncState(stepName);
													ordersService.updateOrders(orders);
													log.info("更新入库单状态成功，单号为"+shipmentId);
												}
											}else{
												log.info("查询入库单失败");
											}
										}
									}
								} catch (Exception e) {
									Map<String, 	Object> map2 =  (Map<String, Object>) map.get("ShipmentStatus");
											//订单号
											String shipmentId = (String)map2.get("ShipmentId");
											//订单状态
											String status = (String)map2.get("Status");
											//订单状态中文描述
											String stepName = (String)map2.get("StepName");
											//操作时间
											String operationTime = (String)map2.get("OperationTime");
											//操作人
											String operationBy = (String)map2.get("OperationBy");
											
											//更新入库单状态
											ServiceResult<Orders> ordersResult = ordersService.getOrdersBySn(shipmentId);
											if(ordersResult.getSuccess()){
												Orders orders = ordersResult.getResult();
												if(orders != null){
													orders.setSyncState(stepName);
													ordersService.updateOrders(orders);
													log.info("更新入库单状态成功，单号为"+shipmentId);
												}
											}else{
												log.info("查询入库单失败");
											}
								}
								}
							out.print(successXml);
							return;
							}else{
								out.print(failXml);
								log.info("解析xml为空");
								return;
							}
				}
		}catch(IOException e){
			e.printStackTrace();
			log.error(e.getMessage());
		}
		log.info("wms于"+new Date()+"推送出库单状态流（ShipmentStatusResponse）接口#####################End##################################");
    }
    /**
     * 推送出库单反馈-ShipmentsResponse
     * @return 
     */
    @RequestMapping(value = "shipmentsResponse", method = { RequestMethod.GET,RequestMethod.POST })
    public  synchronized void ShipmentsResponse(HttpServletRequest request,HttpServletResponse response){
    	log.info("wms于"+new Date()+"推送出库单反馈（ShipmentsResponse）接口#####################Start##################################");
    	response.setContentType("text/xml");
		response.setCharacterEncoding("utf-8");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		PrintWriter out = null;
		InputStream in = null;
		try {
			out = response.getWriter();
			in = request.getInputStream();
			InputStreamReader reader = new InputStreamReader(request.getInputStream(),"utf-8");
			//InputStreamReader reader = new InputStreamReader(request.getInputStream(),"utf-8");
			char[] bytes = new char[1024];
			int length = 0;
			StringBuffer sb =  new StringBuffer();
			while((length = reader.read(bytes)) !=-1){
				sb.append(new String(bytes, 0, length));
			}
			String failXml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
					+"<Response>"
					+"<Success>false</Success>"
					+"<Code>100</Code>"
					+"<Description>接收出库单反馈失败！</Description>"
					+"</Response>";
			String successXml ="<?xml version=\"1.0\" encoding=\"utf-8\"?>"
					+"<Response>"
					+"<Success>true</Success>"
					+"</Response>";
				String xml = sb.toString();
				if("".equals(xml)){
					out.print(failXml);
					log.info("接收到从wms传过来的xml为空");
					return;
				}else{
					log.info("接收到从wms传过来的xml为："+xml);
				    List<Map<String, Object>> list = XmlConvertUtil.parseJSON2List(xml);
				  //调用接口查询库存
					if(list !=null && list.size() > 0){
						//for (Map<String, Object> map : list) {
						Map<String, Object> map = list.get(0);
							Map<String, Object> mainMap = (Map<String, Object>)map.get("SHIPMENT");
							//货主代码
							//String company = (String)mainMap.get("Company");
							//仓库代码
							//String wareHouse = (String)mainMap.get("WareHouse");
							//出库单号
							String shipmentId = (String)mainMap.get("ShipmentId");
							
							//try {
								//Map<String, Object> orderLinessMap = (Map<String, Object>)mainMap.get("OrderLiness");
								//Map<String, Object> orderLineMap = (Map<String, Object>)orderLinessMap.get("OrderLine");
								//单据行号
								//String orderLineNo = (String)orderLineMap.get("OrderLineNo");
								//商品编码
								//String itemPar= (String)orderLineMap.get("Item");
								//商品名称
								//String itemNamePar = (String)orderLineMap.get("ItemName");
								//请求状态
								//String itemStatusPar = (String)orderLineMap.get("ItemStatus");
								//请求数量
								//String itemCountPar = (String)orderLineMap.get("ItemCount");
								//请求批次
								//String lotPar = (String)orderLineMap.get("Lot");
								try {
									//未拆包 单包裹
									Map<String, Object> map2= (Map<String, Object>)mainMap.get("Packages");
									Map<String, Object>packagesMap = (Map<String, Object>)map2.get("Package");
									//--耗材ID
									//String suppliesId = (String)packagesMap.get("SuppliesId");
									//承运人
									//String carrier = (String)packagesMap.get("Carrier");
									//运单号
									String trackingNumber = (String)packagesMap.get("TrackingNumber");
									//重量
									//String weight = (String)packagesMap.get("Weight");
									//备注
									//String remark = (String)mainMap.get("Remark");
									//出库时间
									Date shipmentDate;
									try {
										 shipmentDate = dateFormat.parse((String)packagesMap.get("ShipmentDate"));
									} catch (Exception e) {
										 shipmentDate  = new Date();
									}
									//try {
										//Map<String, Object>packagesMap = (Map<String, Object>)map2.get("Package");
										//Map<String, Object> map3 = (Map<String, Object>)packagesMap.get("Items");
										//Map<String, Object>itemMap = (Map<String, Object>)map3.get("Item");
										//商品编码
									//	String item = (String)itemMap.get("Item");
										//商品描述
										//String itemName = (String)itemMap.get("ItemName");
										//出库数量
										//String itemCount = (String)itemMap.get("ItemCount");
										//出库状态
										//String itemStatus = (String)itemMap.get("ItemStatus");
										//批次
										//String lot = (String)itemMap.get("Lot");
										ServiceResult<Orders> ordersResult =  ordersService.getOrdersBySn(shipmentId);
										if(ordersResult.getSuccess()){
											Orders orders = ordersResult.getResult();
											if(orders != null){
												//订单已发货
												orders.setOrderState(4);
												orders.setOutTime(shipmentDate);
												if("".equals(orders.getLogisticsNumber()) || "null".equals(orders.getLogisticsNumber())){
													orders.setLogisticsNumber(trackingNumber);
												}else{
													orders.setLogisticsNumber(orders.getLogisticsNumber()+","+trackingNumber);
												}
												Integer result =  ordersService.updateOrders(orders).getResult();
												if(result != null){
													log.info("更新订单状态成功，订单号为："+shipmentId);
												}
											}
										}else{
											log.info("根据订单id获取订单信息失败，订单号为："+shipmentId);
										}
									/*} catch (Exception e) {
										//Map<String, Object>packagesMap = (Map<String, Object>)map2.get("Package");
										List<Map<String, Object>> itemsList = (List<Map<String, Object>>)packagesMap.get("Items");
										for (Map<String, Object> map3 : itemsList) {
											Map<String, Object>itemMap = (Map<String, Object>)map3.get("Item");
											//商品编码
											String item = (String)itemMap.get("Item");
											//商品描述
											//String itemName = (String)itemMap.get("ItemName");
											//出库数量
											//String itemCount = (String)itemMap.get("ItemCount");
											//出库状态
											//String itemStatus = (String)itemMap.get("ItemStatus");
											//批次
											//String lot = (String)itemMap.get("Lot");
											ServiceResult<Orders> ordersResult =  ordersService.getOrdersBySn(shipmentId);
											if(ordersResult.getSuccess()){
												Orders orders = ordersResult.getResult();
												if(orders != null){
													//订单已发货
													orders.setOrderState(4);
													if("".equals(orders.getLogisticsNumber()) || "null".equals(orders.getLogisticsNumber())){
														orders.setLogisticsNumber(trackingNumber);
													}else{
														orders.setLogisticsNumber(orders.getLogisticsNumber()+","+trackingNumber);
													}
													Integer result =  ordersService.updateOrders(orders).getResult();
													if(result != null){
														log.info("更新订单状态成功，订单号为："+shipmentId);
													}
												}
											}else{
												log.info("根据订单id获取订单信息失败，订单号为："+shipmentId);
											}
										}
									}*/
									//拆包  多包裹
								} catch (Exception e1) {
									List<Map<String, Object>> packagesList= (List<Map<String, Object>>)mainMap.get("Packages");
									for (Map<String, Object> map2 : packagesList) {
										Map<String, Object>packagesMap = (Map<String, Object>)map2.get("Package");
										//--耗材ID
										//String suppliesId = (String)packagesMap.get("SuppliesId");
										//承运人
										//String carrier = (String)packagesMap.get("Carrier");
										//运单号
										String trackingNumber = (String)packagesMap.get("TrackingNumber");
										//重量
										//String weight = (String)packagesMap.get("Weight");
										//备注
										//String remark = (String)mainMap.get("Remark");
										//出库时间
										Date shipmentDate;
										try {
											 shipmentDate = dateFormat.parse((String)packagesMap.get("ShipmentDate"));
										} catch (Exception e) {
											 shipmentDate  = new Date();
										}
										//try {
											//Map<String, Object> map3 = (Map<String, Object>)packagesMap.get("Items");
											//Map<String, Object>itemMap = (Map<String, Object>)map3.get("Item");
											//商品编码
											//String item = (String)itemMap.get("Item");
											//商品描述
											//String itemName = (String)itemMap.get("ItemName");
											//出库数量
											//String itemCount = (String)itemMap.get("ItemCount");
											//出库状态
											//String itemStatus = (String)itemMap.get("ItemStatus");
											//批次
											//String lot = (String)itemMap.get("Lot");
											ServiceResult<Orders> ordersResult =  ordersService.getOrdersBySn(shipmentId);
											if(ordersResult.getSuccess()){
												Orders orders = ordersResult.getResult();
												if(orders != null){
													//订单已发货
													orders.setOrderState(4);
													orders.setOutTime(shipmentDate);
													if("".equals(orders.getLogisticsNumber()) || "null".equals(orders.getLogisticsNumber())){
														orders.setLogisticsNumber(trackingNumber);
													}else{
														orders.setLogisticsNumber(orders.getLogisticsNumber()+","+trackingNumber);
													}
													Integer result =  ordersService.updateOrders(orders).getResult();
													if(result != null){
														log.info("更新订单状态成功，订单号为："+shipmentId);
													}
												}
											}else{
												log.info("根据订单id获取订单信息失败，订单号为："+shipmentId);
											}
										/*} catch (Exception e) {
											List<Map<String, Object>> itemsList = (List<Map<String, Object>>)packagesMap.get("Items");
											for (Map<String, Object> map3 : itemsList) {
												Map<String, Object>itemMap = (Map<String, Object>)map3.get("Item");
												//商品编码
												String item = (String)itemMap.get("Item");
												//商品描述
												//String itemName = (String)itemMap.get("ItemName");
												//出库数量
												//String itemCount = (String)itemMap.get("ItemCount");
												//出库状态
												//String itemStatus = (String)itemMap.get("ItemStatus");
												//批次
												//String lot = (String)itemMap.get("Lot");
												
												ServiceResult<Orders> ordersResult =  ordersService.getOrdersBySn(shipmentId);
												if(ordersResult.getSuccess()){
													Orders orders = ordersResult.getResult();
													if(orders != null){
														//订单已发货
														orders.setOrderState(4);
														if("".equals(orders.getLogisticsNumber()) || "null".equals(orders.getLogisticsNumber())){
															orders.setLogisticsNumber(trackingNumber);
														}else{
															orders.setLogisticsNumber(orders.getLogisticsNumber()+","+trackingNumber);
														}
														Integer result =  ordersService.updateOrders(orders).getResult();
														if(result != null){
															log.info("更新订单状态成功，订单号为："+shipmentId);
														}
													}
												}else{
													log.info("根据订单id获取订单信息失败，订单号为："+shipmentId);
												}
											}
										}*/
									}
								}
								
							/*} catch (Exception e4) {
								List<Map<String, Object>> orderLinessMaps = (List<Map<String, Object>>)mainMap.get("OrderLiness");
								for (Map<String, Object> orderLinessMap : orderLinessMaps) {
									Map<String, Object>orderLineMap = (Map<String, Object>)orderLinessMap.get("OrderLine");
									//单据行号
									//String orderLineNo = (String)orderLineMap.get("OrderLineNo");
									//商品编码
									//String itemPar= (String)orderLineMap.get("Item");
									//商品名称
									//String itemNamePar = (String)orderLineMap.get("ItemName");
									//请求状态
									//String itemStatusPar = (String)orderLineMap.get("ItemStatus");
									//请求数量
									//String itemCountPar = (String)orderLineMap.get("ItemCount");
									//请求批次
									//String lotPar = (String)orderLineMap.get("Lot");
								}
									try {
										//未拆包 单包裹
										Map<String, Object> map2= (Map<String, Object>)mainMap.get("Packages");
										Map<String, Object>packagesMap = (Map<String, Object>)map2.get("Package");
										//--耗材ID
										//String suppliesId = (String)packagesMap.get("SuppliesId");
										//承运人
										//String carrier = (String)packagesMap.get("Carrier");
										//运单号
										String trackingNumber = (String)packagesMap.get("TrackingNumber");
										//重量
										//String weight = (String)packagesMap.get("Weight");
										//备注
										//String remark = (String)mainMap.get("Remark");
										//出库时间
										//String shipmentDate = (String)packagesMap.get("ShipmentDate");
										try {
											//Map<String, Object>packagesMap = (Map<String, Object>)map2.get("Package");
											Map<String, Object> map3 = (Map<String, Object>)packagesMap.get("Items");
											Map<String, Object>itemMap = (Map<String, Object>)map3.get("Item");
											//商品编码
											String item = (String)itemMap.get("Item");
											//商品描述
											//String itemName = (String)itemMap.get("ItemName");
											//出库数量
											//String itemCount = (String)itemMap.get("ItemCount");
											//出库状态
											//String itemStatus = (String)itemMap.get("ItemStatus");
											//批次
											//String lot = (String)itemMap.get("Lot");
											ServiceResult<Orders> ordersResult =  ordersService.getOrdersBySn(shipmentId);
											if(ordersResult.getSuccess()){
												Orders orders = ordersResult.getResult();
												if(orders != null){
													//订单已发货
													orders.setOrderState(4);
													if("".equals(orders.getLogisticsNumber()) || "null".equals(orders.getLogisticsNumber())){
														orders.setLogisticsNumber(trackingNumber);
													}else{
														orders.setLogisticsNumber(orders.getLogisticsNumber()+","+trackingNumber);
													}
													Integer result =  ordersService.updateOrders(orders).getResult();
													if(result != null){
														log.info("更新订单状态成功，订单号为："+shipmentId);
													}
												}
											}else{
												log.info("根据订单id获取订单信息失败，订单号为："+shipmentId);
											}
										} catch (Exception e) {
											//Map<String, Object>packagesMap = (Map<String, Object>)map2.get("Package");
											List<Map<String, Object>> itemsList = (List<Map<String, Object>>)packagesMap.get("Items");
											for (Map<String, Object> map3 : itemsList) {
												Map<String, Object>itemMap = (Map<String, Object>)map3.get("Item");
												//商品编码
												String item = (String)itemMap.get("Item");
												//商品描述
												//String itemName = (String)itemMap.get("ItemName");
												//出库数量
												//String itemCount = (String)itemMap.get("ItemCount");
												//出库状态
												String itemStatus = (String)itemMap.get("ItemStatus");
												//批次
												//String lot = (String)itemMap.get("Lot");
												ServiceResult<Orders> ordersResult =  ordersService.getOrdersBySn(shipmentId);
												if(ordersResult.getSuccess()){
													Orders orders = ordersResult.getResult();
													if(orders != null){
														//订单已发货
														orders.setOrderState(4);
														if("".equals(orders.getLogisticsNumber()) || "null".equals(orders.getLogisticsNumber())){
															orders.setLogisticsNumber(trackingNumber);
														}else{
															orders.setLogisticsNumber(orders.getLogisticsNumber()+","+trackingNumber);
														}
														Integer result =  ordersService.updateOrders(orders).getResult();
														if(result != null){
															log.info("更新订单状态成功，订单号为："+shipmentId);
														}
													}
												}else{
													log.info("根据订单id获取订单信息失败，订单号为："+shipmentId);
												}
											}
										}
										//拆包  多包裹
									} catch (Exception e1) {
										List<Map<String, Object>> packagesList= (List<Map<String, Object>>)mainMap.get("Packages");
										for (Map<String, Object> map2 : packagesList) {
											Map<String, Object>packagesMap = (Map<String, Object>)map2.get("Package");
											//--耗材ID
											String suppliesId = (String)packagesMap.get("SuppliesId");
											//承运人
											String carrier = (String)packagesMap.get("Carrier");
											//运单号
											String trackingNumber = (String)packagesMap.get("TrackingNumber");
											//重量
											String weight = (String)packagesMap.get("Weight");
											//备注
											//String remark = (String)mainMap.get("Remark");
											//出库时间
											String shipmentDate = (String)packagesMap.get("ShipmentDate");
											try {
												Map<String, Object> map3 = (Map<String, Object>)packagesMap.get("Items");
												Map<String, Object>itemMap = (Map<String, Object>)map3.get("Item");
												//商品编码
												String item = (String)itemMap.get("Item");
												//商品描述
												String itemName = (String)itemMap.get("ItemName");
												//出库数量
												String itemCount = (String)itemMap.get("ItemCount");
												//出库状态
												String itemStatus = (String)itemMap.get("ItemStatus");
												//批次
												//String lot = (String)itemMap.get("Lot");
												ServiceResult<Orders> ordersResult =  ordersService.getOrdersBySn(shipmentId);
												if(ordersResult.getSuccess()){
													Orders orders = ordersResult.getResult();
													if(orders != null){
														//订单已发货
														orders.setOrderState(4);
														if("".equals(orders.getLogisticsNumber()) || "null".equals(orders.getLogisticsNumber())){
															orders.setLogisticsNumber(trackingNumber);
														}else{
															orders.setLogisticsNumber(orders.getLogisticsNumber()+","+trackingNumber);
														}
														Integer result =  ordersService.updateOrders(orders).getResult();
														if(result != null){
															log.info("更新订单状态成功，订单号为："+shipmentId);
														}
													}
												}else{
													log.info("根据订单id获取订单信息失败，订单号为："+shipmentId);
												}
												
											} catch (Exception e) {
												List<Map<String, Object>> itemsList = (List<Map<String, Object>>)packagesMap.get("Items");
												for (Map<String, Object> map3 : itemsList) {
													Map<String, Object>itemMap = (Map<String, Object>)map3.get("Item");
													//商品编码
													String item = (String)itemMap.get("Item");
													//商品描述
													String itemName = (String)itemMap.get("ItemName");
													//出库数量
													String itemCount = (String)itemMap.get("ItemCount");
													//出库状态
													String itemStatus = (String)itemMap.get("ItemStatus");
													//批次
													//String lot = (String)itemMap.get("Lot");
													
													ServiceResult<Orders> ordersResult =  ordersService.getOrdersBySn(shipmentId);
													if(ordersResult.getSuccess()){
														Orders orders = ordersResult.getResult();
														if(orders != null){
															//订单已发货
															orders.setOrderState(4);
															if("".equals(orders.getLogisticsNumber()) || "null".equals(orders.getLogisticsNumber())){
																orders.setLogisticsNumber(trackingNumber);
															}else{
																orders.setLogisticsNumber(orders.getLogisticsNumber()+","+trackingNumber);
															}
															Integer result =  ordersService.updateOrders(orders).getResult();
															if(result != null){
																log.info("更新订单状态成功，订单号为："+shipmentId);
															}
														}
													}else{
														log.info("根据订单id获取订单信息失败，订单号为："+shipmentId);
													}
												}
											}
										}
									}
							}*/
							//}
						out.print(successXml);
						return;
						}else{
							out.print(failXml);
							return;
						}
				}
		}catch(IOException e){
			e.printStackTrace();
		}
    	log.info("wms于"+new Date()+"推送出库单反馈（ShipmentsResponse）接口#####################End##################################");
    }
    
    
    @RequestMapping(value = "shipmentsResponse1", method = { RequestMethod.GET,RequestMethod.POST })
    public  synchronized void ShipmentsResponse1(HttpServletRequest request,HttpServletResponse response){
    	log.info("wms于"+new Date()+"推送出库单反馈（ShipmentsResponse1）接口#####################Start##################################");
    	response.setContentType("text/xml");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = null;
		InputStream in = null;
		BufferedInputStream reader = null;
		String failXml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
				   +"<Response>"
		           +"<Success>false</Success>"
		           +"<Code>100</Code>"
		           +"<Description>接收出库单反馈失败！</Description>"
		           +"</Response>";
		String successXml ="<?xml version=\"1.0\" encoding=\"utf-8\"?>"
									+"<Response>"
									+"<Success>true</Success>"
									+"</Response>";
		try {
			out = response.getWriter();
			in = request.getInputStream();
			reader = new BufferedInputStream(in);
			//InputStreamReader reader = new InputStreamReader(request.getInputStream(),"utf-8");
			byte [] bytes = new byte[reader.available()];
			int length = 0;
			String xml = "";
			StringBuffer sb = new StringBuffer();
			while((length = reader.read(bytes)) !=-1){
				sb.append(new String(bytes, 0, length));
			}
			xml = sb.toString();
			reader.close();
			in.close();
			System.out.println("###################1"+xml);
			System.out.println("###################2"+xml);
			System.out.println("###################3"+xml);
			System.out.println("###################4"+xml);
			out.write(successXml);
		}catch(IOException e){
			e.printStackTrace();
			out.write(failXml);
		}finally{
			if(out != null){
				out.close();
			}
		}
	}
}
