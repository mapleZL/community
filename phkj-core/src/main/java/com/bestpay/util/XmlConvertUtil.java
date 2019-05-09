package com.bestpay.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import net.sf.json.xml.XMLSerializer;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

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
    
    public static void main(String args[]) throws Exception {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><RECEIPTS><RECEIPT><Company>OMEY</Company><WareHouse>WS01</WareHouse><ReceiptId>CGDD00013234</ReceiptId><ReceiptType>采购入库</ReceiptType><ReceiptDate>2013-05-20T11:14:52.513</ReceiptDate><Remark></Remark><Items><Item><Item>3A1112Gxl</Item><ItemName>乔伊思 2013年夏季新款三件套装7789 藏青色</ItemName><ItemCount>9</ItemCount><ItemStatus>1</ItemStatus><Lot>20141116</Lot></Item><Item><Item>3A1112RXXL</Item><ItemName>乔伊思 2013年夏季新款三件套装7789 藏青色</ItemName><ItemCount>9</ItemCount><ItemStatus>1</ItemStatus><Lot>20150116</Lot></Item></Items></RECEIPT><RECEIPT><Company>OMEY</Company><WareHouse>WS01</WareHouse><ReceiptId>CGDD00013235</ReceiptId><ReceiptType>采购入库</ReceiptType><ReceiptDate>2013-05-20T11:14:52.513</ReceiptDate><Remark></Remark><Items><Item><Item>3A1112Gxl</Item><ItemName>乔伊思 2013年夏季新款三件套装7789 藏青色</ItemName><ItemCount>9</ItemCount><ItemStatus>1</ItemStatus><Lot>20141116</Lot></Item><Item><Item>3A1112RXXL</Item><ItemName>乔伊思 2013年夏季新款三件套装7789 藏青色</ItemName><ItemCount>9</ItemCount><ItemStatus>1</ItemStatus><Lot>20150116</Lot></Item></Items></RECEIPT></RECEIPTS>";

        System.out.println(">>>>>>>> " + parseJSON2List(xml));
        xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
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
      System.out.println(">>>>>>>> " + parseJSON2List(xml));
        
//        String json = "[{\"Company\":\"OMEY\",\"WareHouse\":\"WS01\",\"ReceiptId\":\"CGDD00013234\",\"ReceiptType\":\"采购入库\",\"ReceiptDate\":\"2013-05-20T11:14:52.513\",\"Remark\":[],\"Items\":[{\"Item\":\"3A1112Gxl\",\"ItemName\":\"乔伊思 2013年夏季新款三件套装7789 藏青色\",\"ItemCount\":\"9\",\"ItemStatus\":\"1\",\"Lot\":\"20141116\"},{\"Item\":\"3A1112RXXL\",\"ItemName\":\"乔伊思 2013年夏季新款三件套装7789 藏青色\",\"ItemCount\":\"9\",\"ItemStatus\":\"1\",\"Lot\":\"20150116\"}]},{\"Company\":\"OMEY\",\"WareHouse\":\"WS01\",\"ReceiptId\":\"CGDD00013235\",\"ReceiptType\":\"采购入库\",\"ReceiptDate\":\"2013-05-20T11:14:52.513\",\"Remark\":[],\"Items\":[{\"Item\":\"3A1112Gxl\",\"ItemName\":\"乔伊思 2013年夏季新款三件套装7789 藏青色\",\"ItemCount\":\"9\",\"ItemStatus\":\"1\",\"Lot\":\"20141116\"},{\"Item\":\"3A1112RXXL\",\"ItemName\":\"乔伊思 2013年夏季新款三件套装7789 藏青色\",\"ItemCount\":\"9\",\"ItemStatus\":\"1\",\"Lot\":\"20150116\"}]}]";
//        System.out.println(jsontoXml(json));
    }
}