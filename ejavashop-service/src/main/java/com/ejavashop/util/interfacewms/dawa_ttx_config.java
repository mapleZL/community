package com.ejavashop.util.interfacewms;

public class dawa_ttx_config {
	 //仓库代码
	public static final String WAREHOUSE_CODE="MZJ";
	 //请求地址http://114.55.88.189:8907
	//wms 测试
//	public static final String SEND_TTX_URL = "http://114.55.88.189:88/InterFace_MTY/HttpListener.aspx";
	//wms生产
//	public static final String SEND_TTX_URL = "http://121.40.96.166/InterFaceApp_MTY/HttpListener.aspx";
	//平台生产tomcate
	 //public static final String SEND_TTX_URL = "http://121.43.151.221:8089/wi/dawa/shipmentsResponse1";
	 //平台生产jetty
	//public static final String SEND_TTX_URL = "http://121.43.151.221:8090/wi/dawa/shipmentsResponse1";
	 //消息发送方
	 public static final String MESSAGE_FROM = "MTY";
	 //出库单标记
	 public static final String SUBJECT_HEADER1 = "UpdateShipmentStatus";
	 //出库单取消
	 public static final String SUBJECT_HEADER2 = "ShipmentCancelRequest";
	 //获取出库单结果
	 public static final String SUBJECT_HEADER3 = "GetShipments";
	 //获取出库单状态
	 public static final String SUBJECT_HEADER4 = "GetShipmentStatus";
	 //库存查询
	 public static final String SUBJECT_HEADER5 = "InventoryRequest";
	 //入库单反馈
	 public static final String SUBJECT_HEADER6 = "GetReceipts";
	 //入库单标记
	 public static final String SUBJECT_HEADER7 = "UpdateReceiptStatus";
	 //入库单取消
	 public static final String SUBJECT_HEADER8 = "ReceiptCancelRequest";
	 //推送出库单反馈
	 public static final String SUBJECT_HEADER9 = "ShipmentsResponse";
	 //推送出库单状态流
	 public static final String SUBJECT_HEADER10 = "ShipmentStatusResponse";
	 //推送给入库单反馈
	 public static final String SUBJECT_HEADER11 = "ReceiptsResponse";
	 //推送库存
	 public static final String SUBJECT_HEADER12= "InventorysResponse";
	 //货主信息同步
	 public static final String SUBJECT_HEADER13= "CompanyRequest";
	 //商品信息同步接口
	 public static final String SUBJECT_HEADER14= "ItemRequest";
     //入库单同步
	 public static final String SUBJECT_HEADER15= "ReceiptRequest";
	 //发货单接口
	 public static final String SUBJECT_HEADER16= "ShipmentRequest";
	 
	 
	 public static final String APPLICATION_JSON = "application/json";
	    
	 public static final String CONTENT_TYPE_TEXT_JSON = "text/json";
	 //oms基础推送地址
	 public static final String OMS_BASE_URL = "http://gshanyun.6655.la/api/oms/in";
	 
	 
	 public static final String SELLER_USER="seller";
	 public static final String ORDERS="orders";
	 public static final String PRODUCT="product";
	 public static final String PRODUCT_GOODS="product_goods";
	 public static final String BOOKING_SEND_GOODS="booking_send_goods";
	 public static final String MEMBER="member";
	 public static final String ORDER_RECEIPT="order_receipt";
	 
	 public static final String RELATION_ID="relation_id";
	 public static final String RELATION_TABLE="relation_table";
	 
	 //1.货主信息同步 品台字段
     public static final String DAWA_SELLER_USER_WORDS="seller_name,name,member_name,address1,address2,phone,mobile";
     // 货主信息同步 wms字段   货主,名称,联系人,地址1,地址2,座机,手机
     public static final String TTX_SELLER_USER_WORDS="COMPANY,NAME,ATTENTION_TO,ADDRESS1,ADDRESS2,PHONE_NUM,MOBILE";

      //2.商品信息同步 平台字段
     public static final String DAWA_GOODS_INFO_WORDS="Company,WareHouse,Item,BrandId,Name,ItemDesc,BarCode,GoodSpec,Color,Length,Width,"
     		+ "Height,Weight,Unit,Sprice,ControlId,PackingClass,ItemClass,PackageQty";
     //商品信息同步 wms字段   货主,仓库,商品编码,品牌,商品名称,商品描述 ,商品条码,尺码/容积（ml）,颜色 ,长度 ,宽度 , 
     //高度 , 重量,单位：默认“EA” ,单价,是否批次控制，用N、Y表示,盒/双/个 ,商品大类 （中筒袜、短袜、裸袜、辅料）,包装数量（单位EA默认1，非EA为个数）
     public static final String TTX_GOODS_INFO_WORDS="Company,WareHouse,Item,BrandId,Name,ItemDesc,BarCode,GoodSpec,Color,Length,Width,"
     		+ "Height,Weight,Unit,Sprice,ControlId,PackingClass,ItemClass,PackageQty";
     
     //3 入库单同步 平台字段
     public static final String DAWA_OUT_SYNC_WORDS = "Company,WareHouse,TotalQty,TotalLines,ReceiptId,ReceiptType,OrderDate,VendorCode"
     		+ "VendorName,Item,ItemName,ItemCount,Sprice,Lot,Remark,UserDef1,UserDef2,UserDef3,UserDef4";
     // 入库单同步  wms字段  货主编码,仓库编码,入库单入库总数量,订单明细行数,入库单编码,入库类型（采购入库、退货入库）,预计到货日期（如：2010-01-01）,
     //供应商编码,供应商名称,货品编码（商品需要提前同步）,货品名称,入库数量,商品单价,入库批号,备注,自定义字段1,自定义字段2,自定义字段3,自定义字段4
     public static final String TTX_OUT__SYNC_WORDS = "Company,WareHouse,TotalQty,TotalLines,ReceiptId,ReceiptType,OrderDate,VendorCode"
     		+ "VendorName,Item,ItemName,ItemCount,Sprice,Lot,Remark,UserDef1,UserDef2,UserDef3,UserDef4";
     
     //4 出库单同步接口 inventoryRequest 平台字段
     public static final String DAWA_SEND_GOODS_WORDS="Company,WareHouse,TotalQty,TotalLines,ShipmentId,TorderCode,EbillNumber,ShipmentType,Carrier,Name,"
     		+ "PostCode,Phone,Mobile,State,City,District,Address,ShipDate,Cod,CodValue,Invoice,InvoiceAmount,InvoiceType,InvoiceName,InvoiceContent,ShopName,Remark,"
     		+ "CheckDate,ShipmentNote,Item,ItemName,ERP_OrderLine,ItemCount,Sprice,Lot,TagCode,PackType,UserDef1,UserDef2,UserDef3,UserDef4";
     
     // 出库单同步  wms字段 货主编码,仓库编码,订单出库总数量,订单明细行数,出库单编码,淘宝订单号,电子面单,订单类型（销售出库、调拨出库）,承运商代码（如：EMS，双方系统的承运商代码要求一致）
     //收货人姓名,收货人邮编,收货人固话,收货人手机号码,收货人所在地（省）,收货人所在地（市）,收货人所在地（区）,收货人详细地址,计划发货日期（如：2010-01-01）,是否cod
     //Cod金额,是否需要打印发票(用Y、N表示),发票总金额,发票类型,发票标题,发票内容（若要打印订单明细，请把明细以“；”隔开）,订单来源的店铺名称,订单备注,ERP审核时间,
     //摘要,货品编码,货品名称,ERP行号,出库数量,商品单价,出库批号,标签编码,贴标/装盒,自定义字段1,自定义字段2,自定义字段3,自定义字段4
     public static final String TTX_SEND_GOODS_WORDS="Company,WareHouse,TotalQty,TotalLines,ShipmentId,TorderCode,EbillNumber,ShipmentType,Carrier,Name,"
     		+ "PostCode,Phone,Mobile,State,City,District,Address,ShipDate,Cod,CodValue,Invoice,InvoiceAmount,InvoiceType,InvoiceName,InvoiceContent,ShopName,Remark,"
     		+ "CheckDate,ShipmentNote,Item,ItemName,ERP_OrderLine,ItemCount,Sprice,Lot,TagCode,PackType,UserDef1,UserDef2,UserDef3,UserDef4";
     
     
     /**
      * OMS 接口链接
      */
     //用户创建
     public static final String  OMS_CORAPORATION_CREATE_COMPANY =  "oms.corporation.create.company";
     //商品创建
     public static final String  OMS_SKU_CREATE = "oms.sku.create";
     //入库单创建
     public static final String  OMS_RECEIPT_CREATE = "oms.receipt.create";
     //入库单取消
     public static final String  OMS_RECEIPT_CANCEL = "oms.receipt.cancel";
     //订单创建
     public static  final String OMS_ORDER_CREATE = "oms.order.create";
     //订单取消
     public static  final String OMS_ORDER_CANCEL = "oms.order.cancel";
     //货权转移单创建
     public static final String  OMS_OWNERTRANSFER_CREATE = "oms.ownerTransfer.create";
     //货权转移单取消
     public static final String  OMS_OWNERTRANSFER_CANCEL = "oms.ownerTransfer.cancel";
     //入库单回传
     public static final String OMS_RECEIPT_CONFIRM = "oms.receipt.confirm";
     //出库单回传
     public static final String OMS_SHIPMENT_CONFIRM = "oms.shipment.confirm"; 
     //货权转移确认回传
     public static final String OMS_OWNERTRANSFER_CONFIRM = "oms.ownerTransfer.confirm";
     //oms库存异动接口
     public static final String OMS_INVENTORY_CHANGE = "oms.inventory.change";
     //库存同步接口corporation.warehouse.inventory
     public static final String CORPORATION_WAREHOUSE_INVENTORY = "corporation.warehouse.inventory";
     
}
