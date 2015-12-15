package com.lexmark.services.listner;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ApplicationContextProvider implements ApplicationContextAware {
    private static ApplicationContext ctx;
    
/**
 * 
 * @param context 
 * @throws BeansException 
 */
	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		ctx=context;
		
	}
	/**
	 * 
	 * @return ctx 
	 */
	public static ApplicationContext getApplicationContext(){
		return ctx;
	}

   
}