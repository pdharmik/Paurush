package com.lexmark.services.tests;

import java.util.Locale;

import com.lexmark.services.portlet.ServiceRequestController;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestUtil {
         

	public  Locale getLocale(){
		return Locale.US;
	}
	
	public  ApplicationContext getApplicationContext(){
		String[] locations = {"com/lexmark/services/tests/testApplicationContext.xml"};
	    ApplicationContext ctx = 
			    new ClassPathXmlApplicationContext(locations);
	    return ctx;
	}
	
	public String getServiceRequestNumber(){
		return "123";
	}
	
	public  ServiceRequestController getServiceRequestController(){
		return (ServiceRequestController) getApplicationContext().getBean("serviceRequestController");
	}
	

}
