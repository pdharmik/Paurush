package com.lexmark.services.servlet;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.lexmark.contract.GeographyStateContract;
import com.lexmark.result.GeographyListResult;
import com.lexmark.service.api.GeographyService;
import com.liferay.portal.kernel.servlet.PortletContextListener;

/**
 * Application Lifecycle Listener implementation class CacheLoaderListener
 *
 */
public class CacheLoaderListener extends PortletContextListener implements ServletContextListener{
    
	//public static Map<String, List<GeographyStateContract>> allStateMap;
	
	//public static List<GeographyListResult> allCountryList;
	
	private static Logger LOGGER = LogManager.getLogger(CacheLoaderListener.class);
	
	/**
	 * @see PortletContextListener#PortletContextListener()
	 */
	public CacheLoaderListener() {
		super();
		// TODO Auto-generated constructor stub
	}
	
    @SuppressWarnings("unchecked")
	@Override
	public void contextDestroyed(ServletContextEvent event) {
    	
    LOGGER.debug("Context destroyed");
    
    ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
    List<GeographyListResult> allCountryList =(List<GeographyListResult>)ctx.getBean("allCountryList");
    Map<String, List<GeographyStateContract>> allStateMap = (Map<String, List<GeographyStateContract>>)ctx.getBean("allStateMap");
    	
    allCountryList.clear();
    allCountryList=null;
    allStateMap.clear();
    allStateMap=null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void contextInitialized(ServletContextEvent event) {
		
		LOGGER.debug("Context initialized----------> ");
		
    	Map<String, List<GeographyStateContract>> allStateMap=null;
    	List<GeographyListResult> allCountryList=null;
    	GeographyListResult countryListResult = null;
    	
        ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
        
        final long enterTime=System.currentTimeMillis(); 		
 		LOGGER.debug("Going For country details " + enterTime);
 		
 		try{
 			
 		GeographyService geographyService=(GeographyService)ctx.getBean("geographyService");
 		countryListResult = geographyService.getCountryDetails();
 		
 		allCountryList=(List<GeographyListResult>)ctx.getBean("allCountryList");
 		allCountryList.add(countryListResult);
 		
 		if(allCountryList.get(0)!=null)
 		{
 			LOGGER.debug("all country list size is " + allCountryList.get(0).getCountryList().size());
 		}
 		
 		LOGGER.debug("After the country call " + (System.currentTimeMillis()-enterTime));		
 			
 		long timeBeforePCall=System.currentTimeMillis();
 		
 		LOGGER.debug("Going for second call to pull the state " + timeBeforePCall);
 		
 		allStateMap = (Map<String, List<GeographyStateContract>>)ctx.getBean("allStateMap");
 		allStateMap = geographyService.getAllStateDetails();
 		if(allStateMap !=null)
 		{
 			LOGGER.debug("US state list size is " + allStateMap.get("US").size());
 		}
 		
 		LOGGER.debug("time taken to pull the state " + (System.currentTimeMillis()- timeBeforePCall));
 		
 		}catch(Exception ex)
 		{
 			LOGGER.debug("Exception "+ex.getMessage());
 		}
 		finally{
 		LOGGER.debug("Exiting context initialized " + System.currentTimeMillis());
 		}
	}

    
    
	
}
