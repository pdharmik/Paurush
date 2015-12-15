package com.lexmark.services.tests;

import java.util.Locale;

import com.lexmark.services.portlet.ServiceRequestController;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestUtil {
         

	/**
	 * @return Locale 
	 */
	public  Locale getLocale(){
		return Locale.US;
	}
	
	/**
	 * @return ApplicationContext 
	 */
	public  ApplicationContext getApplicationContext(){
		String[] locations = {"com/lexmark/services/tests/testApplicationContext.xml"};
	    ApplicationContext ctx = 
			    new ClassPathXmlApplicationContext(locations);
	    return ctx;
	}
	
	/**
	 * @return String 
	 */
	public String getServiceRequestNumber(){
		return "123";
	}
	
	/**
	 * @return ServiceRequestController 
	 */
	public  ServiceRequestController getServiceRequestController(){
		return (ServiceRequestController) getApplicationContext().getBean("serviceRequestController");
	}
	

}
