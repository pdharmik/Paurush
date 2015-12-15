package com.lexmark.services.listner;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class ApplicationContextProvider implements ApplicationContextAware {
    private static ApplicationContext ctx = null;

	public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		ctx=context;
		
	}
	
	public static ApplicationContext getApplicationContext(){
		return ctx;
	}

   
}