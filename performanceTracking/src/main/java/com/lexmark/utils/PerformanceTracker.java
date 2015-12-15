package com.lexmark.utils;

import java.io.InputStream;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.lexmark.domain.LexmarkTransaction;



/**
 * save performance related information using Hibernate.
 *
 * @author Alex zhou <alexzhou216@gmail.com>
 */
public class PerformanceTracker {
	
	private static final Log logger = LogFactory.getLog(PerformanceTracker.class);	
	private static ThreadLocal threadLexmarkTran = new ThreadLocal();
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

	public static void startTracking(String targetSystem,
			String webServiceName, String accountID,String loginUser)
			throws InfrastructureException {			
		if (switcher.equals("off")){
		  return;
		 }		 
		//Properties props = new Properties();		
		LexmarkTransaction lexmarkTran = new LexmarkTransaction(targetSystem,
			webServiceName,accountID,loginUser);		
		threadLexmarkTran.set(lexmarkTran);		
	}
	
	
   /**
	 * end performance tracking  
	 */
	public static void endTracking()
			throws InfrastructureException {	
			if (switcher.equals("off")){
				return;
		 }			
		try {			
			LexmarkTransaction lexmarkTran = (LexmarkTransaction)threadLexmarkTran.get();
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
	
	/*
	 *this method is supposed to add our analysis capability 
	 * @param loginUser
	 */
	public static Collection<LexmarkTransaction> getLexmarkTransaction(String loginUser){
	try {		
		HibernateUtil.beginTransaction();
		Query q = HibernateUtil.getSession().createQuery("from LexmarkTransaction lt where lt.loginUser = :name");
		q.setParameter("name", loginUser);
		Collection result = new HashSet(q.list());		
         
		HibernateUtil.commitTransaction();
		HibernateUtil.closeSession();
		return result;
		} catch (HibernateException ex) {
			throw new InfrastructureException(ex);
		}
	}
	
	
	
	
	
	
	
}