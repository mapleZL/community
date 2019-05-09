package com.phkj.web.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringContextUtil implements ApplicationContextAware {
	//Spring应用上下文环境    
	private static ApplicationContext applicationContext; 
	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		SpringContextUtil.applicationContext = arg0; 
	}
	
	public static ApplicationContext getApplicationContext() { 
         return applicationContext; 
    }
	
	/**   
     * 获取对象      
     * @param name   
     * @return Object 一个以所给名字注册的bean的实例   
     * @throws BeansException   
     */ 
	public static Object getBean(String name) throws BeansException { 
        return applicationContext.getBean(name); 
    }
}
