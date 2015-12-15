package com.lexmark.services.util;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.lexmark.SAPWebServices.AP.invoice.client.ZG_AP_GET_DATAProxy;
import com.lexmark.SAPWebServices.AP.serviceRequest.client.ZG_GET_AP_DETAILSProxy;
import com.lexmark.SAPWebServices.AR.serviceRequests.client.ZG_GET_AR_DETAILSProxy;

public class ProxyManager {
	private static Logger logger = LogManager.getLogger(ProxyManager.class);

	
	/**
	 * @param endpoint 
	 * @param username 
	 * @param password 
	 * @return ZG_AP_GET_DATAProxy 
	 * @throws Exception 
	 */
	public static ZG_AP_GET_DATAProxy getInvoiceListProxy(String endpoint, String username, String password)throws Exception{
		ZG_AP_GET_DATAProxy proxy = new ZG_AP_GET_DATAProxy(endpoint);
		logger.debug("afer ZG_AP_GET_DATAProxy");
		org.apache.axis.client.Stub stub = (org.apache.axis.client.Stub)proxy.getZG_AP_GET_DATA_PortType();
		stub.setUsername(username);
		stub.setPassword(password);
		//ObjectDebugUtil.printObjectContent(stub, logger);
		//ObjectDebugUtil.printObjectContent(proxy, logger);
		return proxy;
	}

	/**
	 * @param endpoint 
	 * @param username 
	 * @param password 
	 * @return ZG_GET_AR_DETAILSProxy 
	 * @throws Exception 
	 */
	public static ZG_GET_AR_DETAILSProxy getARSRListProxy(String endpoint, String username, String password)throws Exception{
		ZG_GET_AR_DETAILSProxy proxy = new ZG_GET_AR_DETAILSProxy(endpoint);
		org.apache.axis.client.Stub stub = (org.apache.axis.client.Stub)proxy.getZG_GET_AR_DETAILS_PortType();
		stub.setUsername(username);
		stub.setPassword(password);
		//ObjectDebugUtil.printObjectContent(stub, logger);
		//ObjectDebugUtil.printObjectContent(proxy, logger);
		return proxy;
	}
	
	/**
	 * @param endpoint 
	 * @param username 
	 * @param password 
	 * @return ZG_GET_AP_DETAILSProxy 
	 * @throws Exception 
	 */
	public static ZG_GET_AP_DETAILSProxy getAPSRListProxy(String endpoint, String username, String password)throws Exception{
		ZG_GET_AP_DETAILSProxy proxy = new ZG_GET_AP_DETAILSProxy(endpoint);
		org.apache.axis.client.Stub stub = (org.apache.axis.client.Stub)proxy.getZG_GET_AP_DETAILS_PortType();
		stub.setUsername(username);
		stub.setPassword(password);
		//ObjectDebugUtil.printObjectContent(stub, logger);
		//ObjectDebugUtil.printObjectContent(proxy, logger);
		return proxy;
	}
	
}
