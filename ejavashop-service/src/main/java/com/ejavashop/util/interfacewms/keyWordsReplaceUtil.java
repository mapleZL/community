package com.ejavashop.util.interfacewms;

import com.ejavashop.core.exception.BusinessException;

public class keyWordsReplaceUtil {
	
	/**
	 * 将WMS传过来的xml字符串中的wms字段用平台字段替换
	 * @param replaceXML  被替换的xml字符串
	 * @param willReplaceKeyWords 将要替换成的字符串
	 * @param replacedKeyWordsObj  被替换的字符串
	 * @return  returnStr
	 */
	public static String replaceStr(String replaceXML,String willReplaceKeyWords,String replacedKeyWords){
		if("".equals(replaceXML) || "".equals(replacedKeyWords) || "".equals(willReplaceKeyWords)){
			throw new BusinessException("参数有误");
		}
		String [] willReplaceKeyWordsObj = willReplaceKeyWords.split(",");
		String [] replacedKeyWordsObj = replacedKeyWords.split(",");
		String returnStr = "";
	     for (int i = 0; i < replacedKeyWordsObj.length; i++) {
	    	 returnStr = returnStr+ replaceXML.replaceAll(replacedKeyWordsObj[i], willReplaceKeyWordsObj[i]);
		}
		return returnStr;
	}

}
