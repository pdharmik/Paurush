package com.lexmark.services.servlet;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.lexmark.domain.HardwareCatalog;
import com.lexmark.domain.PunchoutAccount;
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
	    List<PunchoutAccount> allAccountList =(List<PunchoutAccount>)ctx.getBean("allAccountList");
	    Map<String, Map<String,List<HardwareCatalog>>> allBundlePriceMap = (Map<String, Map<String,List<HardwareCatalog>>>)ctx.getBean("allBundlePriceMap");
	    	
	    allAccountList.clear();
	    allAccountList=null;
	    allBundlePriceMap.clear();
	    allBundlePriceMap=null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void contextInitialized(ServletContextEvent event) {
		
		LOGGER.debug("Context initialized----------> ");
		
    	Map<String, Map<String,List<HardwareCatalog>>> allBundlePriceMap=null;
    	List<PunchoutAccount> allAccountList=null;
    	
        ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
        
        final long enterTime=System.currentTimeMillis(); 		
 		LOGGER.debug("Going For Account details " + enterTime);
 		
 		try{
 			
 		LoadPriceInformation priceInformation=(LoadPriceInformation)ctx.getBean("loadPriceInformation");
 		
 		allAccountList=(List<PunchoutAccount>)ctx.getBean("allAccountList");
 		allAccountList = priceInformation.getAllAccountList();
 		
 		if(allAccountList.get(0)!=null)
 		{
 			LOGGER.debug("all account list size is " + allAccountList.size());
 		}
 		
 		LOGGER.debug("After the account call " + (System.currentTimeMillis()-enterTime));		
 			
 		long timeBeforePCall=System.currentTimeMillis();
 		
 		LOGGER.debug("Going for second call to pull the price for all the Hardware " + timeBeforePCall);
 		
 		allBundlePriceMap = (Map<String, Map<String,List<HardwareCatalog>>>)ctx.getBean("allBundlePriceMap");
 		allBundlePriceMap = priceInformation.getAllBundlePriceMap();
 		if(allBundlePriceMap !=null)
 		{
 			LOGGER.debug("Price Map Size is " + allBundlePriceMap.size());
 		}
 		
 		LOGGER.debug("time taken to pull the Map " + (System.currentTimeMillis()- timeBeforePCall));
 		
 		}catch(Exception ex)
 		{
 			LOGGER.debug("Exception "+ex.getMessage());
 		}
 		finally{
 		LOGGER.debug("Exiting context initialized " + System.currentTimeMillis());
 		}
	}

    
    
	
}
