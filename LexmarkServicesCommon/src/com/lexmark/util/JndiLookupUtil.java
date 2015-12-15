package com.lexmark.util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.lexmark.contract.LdapSecurityData;
import com.lexmark.contract.ObieeConnectionData;

public class JndiLookupUtil {
	private static Logger logger = LogManager.getLogger(JndiLookupUtil.class);
	
	public static LdapSecurityData getLdapServiceResult() throws Exception{
		LdapSecurityData ldap = null;
		try{
			Context initCtx = new InitialContext();
			Context envCtx = (Context)initCtx.lookup("java:comp/env");
			// Look up our data source
			ldap = (LdapSecurityData) envCtx.lookup( "globalservices/ldapinformation" );			
		}catch (NamingException e){
			logger.error("Naming exception getting ldap information");
			throw new Exception(e.getMessage());
		}
		
		return ldap;
	}

	public static ObieeConnectionData getObieeServiceResult() throws Exception{
		ObieeConnectionData obiee = null;
		try{
			Context initCtx = new InitialContext();
			Context envCtx = (Context)initCtx.lookup("java:comp/env");
			// Look up our data source
			obiee = (ObieeConnectionData) envCtx.lookup( "globalservices/obieeinformation" );			
		}catch (NamingException e){
			logger.error("Naming exception getting obiee information");
			throw new Exception(e.getMessage());
		}
		
		return obiee;
	}

}
