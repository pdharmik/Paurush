package com.lexmark.commons;

import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;





/**
 * Utility Class for checking whether the customer_id  is present in the property file or not
 * 
 * @author sbag
 * @since 18 Oct 2011.
 */

public class SessionUtility {
	private boolean b;
	private static Logger LOGGER = LogManager.getLogger(SessionUtility.class);
	/**.
	 * 
	 * This method checks the sessionLookUp 
	 * 
	 * @param cust_id 
	 * @param cust_sess_id 
	 * @return boolean 
	  */
	public boolean sessionLookUp(Object cust_id,Object cust_sess_id)
	{ 
		try
		{
				
		String fname="session_clear_cart.properties";
		InputStream is = this.getClass().getClassLoader().getResourceAsStream("com/lexmark/services/resources/"+fname);	
		Properties prop=new Properties();
	    prop.load(is);
	    boolean b1 = false;
	    boolean b2 = false;
	    if (cust_id != null)
	    {
	    	 b1=prop.containsKey(cust_id);
	    }	
	    if (cust_sess_id != null) 
	    {
	    	b2 = prop.containsKey(cust_sess_id);
	    }   	     
		 b= (b1 || b2);
		 
		 is.close();
	    }

		catch(Exception e)
		{
			LOGGER.debug("Exception occured" + e.getMessage());
			//e.printStackTrace();		
		}
		return b;
	}
	

}
