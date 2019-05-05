package com.ejavashop.util.interfacewms;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.UnsupportedCharsetException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

import com.ejavashop.core.StringUtil;
import com.ejavashop.core.exception.BusinessException;
import com.ejavashop.core.freemarkerutil.DomainUrlUtil;

public class InterfaceClientUtil {
	/*
	 * wms 信息同步get方法
	 */
	 public static String sendGet(String url) {
	        HttpGet get = null;
	        CloseableHttpResponse resp = null;
	        CloseableHttpClient client = null;
	        try {
	            client = HttpClients.createDefault();
	            get = new HttpGet(url);
	            resp = client.execute(get);
	            int statusCode = resp.getStatusLine().getStatusCode();
	            if (statusCode >= 200 && statusCode < 300) {
	                HttpEntity entity = resp.getEntity();
	                String content = EntityUtils.toString(entity, "utf-8");
	                return content;
	            }
	        } catch (ClientProtocolException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                if (resp != null) {
	                    resp.close();
	                }
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	            try {
	                if (client != null) {
	                    client.close();
	                }
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	        return null;
	    }
	 
	 /*
	  * *wms 信息同步post方法
	  */
	 public static String sendPost(String url, String content,String subject) {
	        CloseableHttpClient client = null;
	        CloseableHttpResponse resp = null;
	        try {
	            System.out.println(content);
	            client = HttpClients.createDefault();
	            HttpPost post = new HttpPost(url);
	            //HttpPost post = new HttpPost("http://127.0.0.1:8080/dawa/inventoryRequest");
	            post.addHeader("Message-From", dawa_ttx_config.MESSAGE_FROM);
	            post.addHeader("Subject", subject);
	            post.addHeader("length" , String.valueOf(content.length()));
	            StringEntity entity = new StringEntity(content, ContentType.create("text/xml","utf-8"));
	            // StringEntity entity = new StringEntity(content);
	            post.setEntity(entity);
	            resp = client.execute(post);
	            int statusCode = resp.getStatusLine().getStatusCode();
	            if (statusCode >= 200 && statusCode < 300) {
	                String str = EntityUtils.toString(resp.getEntity(), "utf-8"); 	
	                return str;
	            }
	        } catch (UnsupportedCharsetException e) {
	            e.printStackTrace();
	        } catch (ClientProtocolException e) {
	            e.printStackTrace();
	        } catch (ParseException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                if (client != null)
	                    client.close();
	            } catch (IOException e1) {
	                e1.printStackTrace();
	            }
	            try {
	                if (resp != null)
	                    resp.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	        return null;
	    }
	    
	 
	    /**
	     * json 解析对象，线程安全
	     */
	    public static final ObjectMapper objectMapper = new ObjectMapper();
	    
	    private static final HttpRequester httpRequester = new HttpRequester();
	    
	    private static Logger log = LogManager.getLogger(InterfaceClientUtil.class);
		 /**
		  * OMS post 同步方法
		  */
//		public static Map<String, Object> httpPostWithJSON(Map<String, Object> paramMap,String api) throws Exception {
//			
//			if (paramMap == null || paramMap.size() == 0) {
//				throw new BusinessException("参数paramMap为空");
//	        }
//			
//			if ("".equals(api) || StringUtil.isEmpty(api)) {
//				throw new BusinessException("参数api为空");
//	        }
//	
//	        TreeMap<String, Object> treeMap = null;
//	        if (paramMap instanceof TreeMap) {
//	            treeMap = (TreeMap) paramMap;
//	        } else {
//	            treeMap = new TreeMap<String, Object>(paramMap);
//	        }
//	        String data = null;
//	        try {
//	            data = objectMapper.writeValueAsString(treeMap);
//	        } catch (IOException e) {
//	        	throw new BusinessException(e.getMessage());
//	        }
//	        Map<String, String> params = new HashMap<String, String>(2, 1.0f);
//	        params.put("data", data);
//	        params.put("api", api);
//	        try {
//				HttpRespons httpRespons = httpRequester.sendPost(DomainUrlUtil.EJS_OMS_URL, params);
//				String urlString  = httpRespons.getUrlString();
//				String protocol = httpRespons.getProtocol();
//				String content = httpRespons.getContent();
//				String host = httpRespons.getHost();
//			    String contentEncoding = httpRespons.getContentEncoding();
//			    int port = httpRespons.getPort();
//			    Map<String, Object> map = XmlConvertUtil.parseJSON2Map(content);
//			    log.info("商城调用OMS接口返回链接为："+urlString+",线程id为："+protocol+",主机为："+host+",端口号为:"+port+",编码格式为："+contentEncoding+",返回内容为："+content);
//			    return map;
//	        }catch (Exception e) {
//	        	e.printStackTrace();
//	        	log.error(e.getMessage());
//	        	throw new BusinessException(e.getMessage());
//			}
//		}
}
