package com.lexmark.util;

import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.LogManager;
import org.hibernate.HibernateException;

import com.lexmark.domain.LexmarkTransaction;
import com.lexmark.service.impl.real.jdbc.HibernateUtil;
import com.lexmark.service.impl.real.jdbc.InfrastructureException;
import org.apache.logging.log4j.Logger;



/**
 * save performance related information using Hibernate.
 *
 * @author Alex zhou <alexzhou216@gmail.com>
 */
public class PerformanceTracker {
	
	//private static final Log logger = LogFactory.getLog(PerformanceTracker.class);	
	private static final Logger logger = LogManager.getLogger(PerformanceTracker.class);
	private static Properties prop = new Properties();   
	private static String switcher = "off";
    
	static {		
		try{
				InputStream is = PerformanceTracker.class.getResourceAsStream("/tracker.properties");
				prop.load(is);	
				switcher = prop.getProperty("status");
				logger.debug("the switcher is " + prop.getProperty("status"));
			}
		catch(Exception e)
		{	
			e.printStackTrace();
			logger.error("we have problem getting the property file");
		}
	}

	
	/**
	 * start performance tracking
	 *
	 * @param targetSystem, webServiceName, accountID, loginUser 
	 */

	public static LexmarkTransaction startTracking(String targetSystem,
			String webServiceName, String mdmID,String emailAddress)
			throws InfrastructureException {			
		LexmarkTransaction lexmarkTran = new LexmarkTransaction(targetSystem,
			webServiceName,mdmID,emailAddress);		
		return lexmarkTran;
	}
	
	
   /**
	 * end performance tracking  
	 */
	public static void endTracking(LexmarkTransaction lexmarkTran)
			throws InfrastructureException {	
			if (switcher.equals("off")){
				return;
		 }			
		try {			
			lexmarkTran.setEndTime(new Date());
			lexmarkTran.calculateDuration();
			HibernateUtil.beginTransaction();			
			HibernateUtil.getSession().save(lexmarkTran);
			HibernateUtil.commitTransaction();
			HibernateUtil.getSession().flush();
		    HibernateUtil.closeSession();
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
	}
	
	
}