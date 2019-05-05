package com.ejavashop.util.interfacewms;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.CharsetEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.output.FileWriterWithEncoding;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import net.sf.json.xml.XMLSerializer;

/** 
 * 
 *                       
 * @Filename: XmlConvertUtil.java
 * @Version: 1.0
 * @Author: 张斌 * @Email: 46584649@qq.com
 *
 */
public class XmlConvertUtil {
    private static Logger logger = LogManager.getLogger(XmlConvertUtil.class);
    /** 
     * map to xml xml <node><key label="key1">value1</key><key 
     * label="key2">value2</key>......</node> 
     *  
     * @param map 
     * @return 
     */
    public static String maptoXml(Map map) {
        Document document = DocumentHelper.createDocument();
        Element nodeElement = document.addElement("node");
        for (Object obj : map.keySet()) {
            Element keyElement = nodeElement.addElement("key");
            keyElement.addAttribute("label", String.valueOf(obj));
            keyElement.setText(String.valueOf(map.get(obj)));
        }
        return doc2String(document);
    }

    /** 
     * list to xml xml <nodes><node><key label="key1">value1</key><key 
     * label="key2">value2</key>......</node><node><key 
     * label="key1">value1</key><key 
     * label="key2">value2</key>......</node></nodes> 
     *  
     * @param list 
     * @return 
     */
    public static String listtoXml(List list) throws Exception {
        Document document = DocumentHelper.createDocument();
        Element nodesElement = document.addElement("nodes");
        int i = 0;
        for (Object o : list) {
            Element nodeElement = nodesElement.addElement("node");
            if (o instanceof Map) {
                for (Object obj : ((Map) o).keySet()) {
                    Element keyElement = nodeElement.addElement("key");
                    keyElement.addAttribute("label", String.valueOf(obj));
                    keyElement.setText(String.valueOf(((Map) o).get(obj)));
                }
            } else {
                Element keyElement = nodeElement.addElement("key");
                keyElement.addAttribute("label", String.valueOf(i));
                keyElement.setText(String.valueOf(o));
            }
            i++;
        }
        return doc2String(document);
    }

    /** 
     * json to xml {"node":{"key":{"@label":"key1","#text":"value1"}}} conver 
     * <o><node class="object"><key class="object" 
     * label="key1">value1</key></node></o> 
     *  
     * @param json 
     * @return 
     */
    public static String jsontoXml(String json) {
        try {
            XMLSerializer serializer = new XMLSerializer();
            JSON jsonObject = JSONSerializer.toJSON(json);
            return serializer.write(jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /** 
     * xml to map xml <node><key label="key1">value1</key><key 
     * label="key2">value2</key>......</node> 
     *  
     * @param xml 
     * @return 
     */
    public static Map xmltoMap(String xml) {
        try {
            Map map = new HashMap();
            Document document = DocumentHelper.parseText(xml);
            Element nodeElement = document.getRootElement();
            List node = nodeElement.elements();
            for (Iterator it = node.iterator(); it.hasNext();) {
                Element elm = (Element) it.next();
                map.put(elm.attributeValue("label"), elm.getText());
                elm = null;
            }
            node = null;
            nodeElement = null;
            document = null;
            return map;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /** 
     * xml to list xml <nodes><node><key label="key1">value1</key><key 
     * label="key2">value2</key>......</node><node><key 
     * label="key1">value1</key><key 
     * label="key2">value2</key>......</node></nodes> 
     *  
     * @param xml 
     * @return 
     */
    public static List xmltoList(String xml) {
        try {
            List<Map> list = new ArrayList<Map>();
            Document document = DocumentHelper.parseText(xml);
            Element nodesElement = document.getRootElement();
            List nodes = nodesElement.elements();
            for (Iterator its = nodes.iterator(); its.hasNext();) {
                Element nodeElement = (Element) its.next();
                Map map = xmltoMap(nodeElement.asXML());
                list.add(map);
                map = null;
            }
            nodes = null;
            nodesElement = null;
            document = null;
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /** 
     * xml to json <node><key label="key1">value1</key></node> 转化为 
     * {"key":{"@label":"key1","#text":"value1"}} 
     *  
     * @param xml 
     * @return 
     */
    public static String xmltoJson(String xml) {
        XMLSerializer xmlSerializer = new XMLSerializer();
        return xmlSerializer.read(xml).toString();
    }
    
    public static List<Map<String, Object>> parseJSON2List(String jsonStr){
        jsonStr = xmltoJson(jsonStr);
        if (!jsonStr.substring(0, 1).equals("[")) {
            jsonStr = "[" + jsonStr + "]";
        }
        JSONArray jsonArr = JSONArray.fromObject(jsonStr);
        List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
        Iterator<JSONObject> it = jsonArr.iterator();
        while(it.hasNext()){
          JSONObject json2 = it.next();
          list.add(parseJSON2Map(json2.toString()));
        }
        return list;
      }
    
    public static Map<String, Object> parseJSON2Map(String jsonStr){
        Map<String, Object> map = new HashMap<String, Object>();
        //最外层解析
        JSONObject json = JSONObject.fromObject(jsonStr);
        for(Object k : json.keySet()){
          Object v = json.get(k); 
          //如果内层还是数组的话，继续解析
          if(v instanceof JSONArray){
            List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
            Iterator<JSONObject> it = ((JSONArray)v).iterator();
            while(it.hasNext()){
              JSONObject json2 = it.next();
              list.add(parseJSON2Map(json2.toString()));
            }
            map.put(k.toString(), list);
          } else {
            map.put(k.toString(), v);
          }
        }
        return map;
      }
    
    /** 
     *  
     * @param document 
     * @return 
     */
    public static String doc2String(Document document) {
        String s = "";
        try {
            // 使用输出流来进行转化  
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            // 使用UTF-8编码  
            OutputFormat format = new OutputFormat("   ", true, "UTF-8");
            XMLWriter writer = new XMLWriter(out, format);
            writer.write(document);
            s = out.toString("UTF-8");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return s;
    }
    
    public static String callMapToXML(Map map, String xmlName) {  
        logger.info("将Map转成Xml, Map：" + map.toString());  
        StringBuffer sb = new StringBuffer();  
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?><" + xmlName + ">");  
        mapToXMLTest2(map, sb);  
        sb.append("</" + xmlName + ">");  
        logger.info("将Map转成Xml, Xml：" + sb.toString());  
        try {  
            return sb.toString();//.getBytes("UTF-8");  
        } catch (Exception e) {  
            logger.error(e);  
        }  
        return null;  
    }  
  
    private static void mapToXMLTest2(Map map, StringBuffer sb) {  
        Set set = map.keySet();  
        for (Iterator it = set.iterator(); it.hasNext();) {  
            String key = (String) it.next();  
            Object value = map.get(key);  
            if (null == value)  
                value = "";  
            if (value.getClass().getName().equals("java.util.ArrayList")) {  
                ArrayList list = (ArrayList) map.get(key);  
                sb.append("<" + key + ">");  
                for (int i = 0; i < list.size(); i++) {  
                    HashMap hm = (HashMap) list.get(i);  
                    mapToXMLTest2(hm, sb);  
                }  
                sb.append("</" + key + ">");  
  
            } else {  
                if (value instanceof HashMap) {  
                    sb.append("<" + key + ">");  
                    mapToXMLTest2((HashMap) value, sb);  
                    sb.append("</" + key + ">");  
                } else {  
                    sb.append("<" + key + ">" + value + "</" + key + ">");  
                }  
  
            }  
  
        }  
    }  
    
    public static void main(String[] args) {
    	
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("Company", "MTY");
    	map.put("WareHouse", "c2");
    	map.put("ProductName", "xxxxx");
    	List<Map<String, Object>> list1 = new ArrayList<Map<String, Object>>();
    	Map<String, Object> map1 = new HashMap<String, Object>();
    	map1.put("SKU", "M.M.555555");
    	map1.put("Account", "111111");
    	list1.add(map1) ;
    	map.put("ProductDetails", list1);
    	
    	JSONObject  jsonObject = JSONObject.fromObject(map);
    	
    	System.out.println(jsonObject);
    	
    	
    	
    	
    	
    	/*
		String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
						 +"<Inventorys>"
						 +"<Warehouse>bbb</Warehouse>"
						 +"<DateTime>2016-05-26 00:11:44</DateTime>"
						 +"<Items>"
						 +"<Item>"
						 +"<Company>EIRIHOME</Company>"
						 +"<Item>E364B1-01025</Item>"
						 +"<ItemQty>1</ItemQty>"
						 +"<InventorySts>可售</InventorySts>"
						 +"</Item>"
						 +"<Item>"
						 +"<Company>EIRIHOME</Company>"
						 +"<Item>2797979</Item>"
						 +"<ItemQty>1</ItemQty>"
						 +"<InventorySts>可售</InventorySts>"
						 +"</Item>"
						 +"<Item>"
						 +"<Company>EIRIHOME</Company>"
						 +"<Item>EB66E1-451L1</Item>"
						 +"<ItemQty>1</ItemQty>"
						 +"<InventorySts>可售</InventorySts>"
						 +"</Item>"
						 +"</Items>"
						 +"</Inventorys>";
		
		String xml1 = "<RECEIPTS><RECEIPT><Company>MTY</Company><WareHouse>MZJ</WareHouse><ReceiptId>20160726503999</ReceiptId><ReceiptType>采购入库</ReceiptType><ReceiptDate>2016-07-26 17:32:01</ReceiptDate><Remark></Remark><TotalCount>100</TotalCount><Items><Item><Item>W.M.704901F</Item><ItemName>W.M.704901F</ItemName><ItemCount>10</ItemCount><InventorySts></InventorySts><ExpirationDate></ExpirationDate><Lot>1</Lot></Item><Item><Item>W.M.704902F</Item><ItemName>W.M.704902F</ItemName><ItemCount>10</ItemCount><InventorySts></InventorySts><ExpirationDate></ExpirationDate><Lot>1</Lot></Item><Item><Item>W.M.704903F</Item><ItemName>W.M.704903F</ItemName><ItemCount>10</ItemCount><InventorySts></InventorySts><ExpirationDate></ExpirationDate><Lot>1</Lot></Item><Item><Item>W.M.704904F</Item><ItemName>W.M.704904F</ItemName><ItemCount>10</ItemCount><InventorySts></InventorySts><ExpirationDate></ExpirationDate><Lot>1</Lot></Item><Item><Item>W.M.706501</Item><ItemName>W.M.706501</ItemName><ItemCount>10</ItemCount><InventorySts></InventorySts><ExpirationDate></ExpirationDate><Lot>1</Lot></Item><Item><Item>W.M.706502</Item><ItemName>W.M.706502</ItemName><ItemCount>10</ItemCount><InventorySts></InventorySts><ExpirationDate></ExpirationDate><Lot>1</Lot></Item><Item><Item>W.M.706503</Item><ItemName>W.M.706503</ItemName><ItemCount>10</ItemCount><InventorySts></InventorySts><ExpirationDate></ExpirationDate><Lot>1</Lot></Item><Item><Item>W.M.706504</Item><ItemName>W.M.706504</ItemName><ItemCount>10</ItemCount><InventorySts></InventorySts><ExpirationDate></ExpirationDate><Lot>1</Lot></Item><Item><Item>W.M.706505</Item><ItemName>W.M.706505</ItemName><ItemCount>10</ItemCount><InventorySts></InventorySts><ExpirationDate></ExpirationDate><Lot>1</Lot></Item><Item><Item>W.M.706601</Item><ItemName>W.M.706601</ItemName><ItemCount>10</ItemCount><InventorySts></InventorySts><ExpirationDate></ExpirationDate><Lot>1</Lot></Item></Items></RECEIPT></RECEIPTS>";
		//Map<String, Object>map =xmltoMap(xml);
		//parseJSON2List(xml);
		List<Map<String, Object>> list = XmlConvertUtil.parseJSON2List(xml1);
		//调用接口查询库存
		if(list !=null && list.size() > 0){
			for (Map<String, Object> map : list) {
				//货主代码
				String company = (String) map.get("Company");
				//仓库代码
				String wareHouse = (String) map.get("WareHouse");
				//入库单号
				String receiptId = (String) map.get("ReceiptId");
				//入库类型
				String receiptType = (String) map.get("ReceiptType");
				//入库时间
				String receiptDate = (String) map.get("ReceiptDate");
				//备注
				String Remark = (String) map.get("Remark");
				@SuppressWarnings("unchecked")
				List<Map<String, 	Object>> itemList =  (List<Map<String, Object>>) map.get("Items");
				if(itemList != null && itemList.size() > 0){
					for (Map<String, 	Object> newMap : itemList) {
						//商品编码
						String item = (String) newMap.get("Item"); 
						//商品描述
						String itemName = (String) newMap.get("ItemName"); 
						//收货数量
						String itemCount = (String) newMap.get("ItemCount"); 
						//商品状态
						String itemStatus = (String) newMap.get("ItemStatus"); 
						//收货批次
						String lot = (String) newMap.get("Lot"); 
						
						*//**
						 * 更新平台商品库存
						 *//*
					}
				}
			}
	 }
    */
    	
    
    
    }
    
    
    public static String getWebAppRootPath(Class clazz){
		String result = clazz.getResource("/").getPath();
		int index = result.indexOf("WEB-INF");
		if(index == -1) return result;
		result = result.substring(0, index);
		if (result.startsWith("zip")) { // 当class文件在war中时，返回"zip:D:/ ..."样的路径
			result = result.substring(4);
		} else if (result.startsWith("file")) { // 当class文件在class文件中时，返回"file:/F:/ ..."样的路径
			result = result.substring(6);
		}else if(result.startsWith("jar")){ // 当class文件在jar文件中时，返回"jar:file:/F:/ ..."样的路径
			result = result.substring(10);
		}
		if (result.endsWith("/")) 
			result = result.substring(0, result.length() - 1);// 不包含最后的"/"
		result = result.replace("%20", " "); //Window下替换空格
		return result;
	}
	
	
	/***
	 * 生成文件名
	 * @param fileName
	 * @return
	 */
	public static String generateFileName() {   
	    DateFormat format = new SimpleDateFormat("yyMMddHHMMss");   
	    String formatDate = format.format(new Date());   
	   // int random = new Random().nextInt(10000);   
	    return formatDate; //+ random;   
	}
	
	public static  Boolean  makeRecord(HttpServletRequest request,StringBuffer content){
		DateFormat format = new SimpleDateFormat("yyMMdd"); 
		String formatDate = format.format(new Date()); 
		String path = "/userfiles/" + formatDate+"/";
		String fileName = generateFileName();
		String qrpath = path + fileName + ".csv";
		String root  = "/home/sync";//request.getSession().getServletContext().getRealPath("");
		//String root  =request.getSession().getServletContext().getRealPath("");
		File fileqr = null;
		try {
			fileqr = new File(root+path);
		}catch (Exception e) {
			root  = request.getSession().getServletContext().getRealPath("");
			fileqr = new File(root+path);
		}
		File file = null;
		if(!fileqr.exists()){
			path.replace("\\", "/");
			fileqr.mkdir();
		}
			try {
				String ppath = root+qrpath;
				file =  new File(ppath);
				if(!file.exists()){
					file.createNewFile();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		try {
			   OutputStreamWriter fw = new OutputStreamWriter(new FileOutputStream(file),"gbk");
			   BufferedWriter bw = new BufferedWriter(fw);
			   bw.write(content.toString());
			   bw.newLine();
			   bw.flush();
			   bw.close();
			   fw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	public static  Boolean  stockChange(HttpServletRequest request,StringBuffer content){
		DateFormat format = new SimpleDateFormat("yyMMdd"); 
		String formatDate = format.format(new Date()); 
		String path = "/changes/";
		String qrpath = path + formatDate + ".csv";
		String root  = "/home/sync";
		File fileqr = null;
		try {
			fileqr = new File(root+path);
		} catch (Exception e) {
			root  = request.getSession().getServletContext().getRealPath("");
			fileqr = new File(root+path);
		}
		
		File file = null;
		if (!fileqr.exists()) {
			fileqr.mkdir();
		}
	   file = new File(root+qrpath);
       FileWriter fw;
	   try {
			//设置成true就是追加
			fw = new FileWriter(file,true);
			fw.write(content.toString());
			fw.close();
	   } catch (IOException e) {
		   e.printStackTrace();
	   } 
		return true;
	}
}