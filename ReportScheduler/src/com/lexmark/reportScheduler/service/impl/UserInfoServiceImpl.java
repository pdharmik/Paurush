package com.lexmark.reportScheduler.service.impl;

import java.io.IOException;
import java.sql.Types;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.lexmark.domain.ServicesUser;
import com.lexmark.service.api.UserInfoService;
import com.lexmark.service.impl.real.jdbc.DBConnection;
import com.lexmark.service.impl.real.jdbc.DBParameter;
import com.lexmark.service.impl.real.jdbc.StringDBParameter;
import com.lexmark.userservice.User;
import com.lexmark.util.report.PropertiesUtil;

public class UserInfoServiceImpl implements UserInfoService {
	private static Logger logger = LogManager.getLogger(UserInfoServiceImpl.class);
	private static final String QUERY_SERVICES_USER = "select RESTRICT_MDM_ID, RESTRICT_MDM_LEVEL, CHL_NODE_ID from SERVICES_USER " +
		" WHERE USERNUMBER = ?";
	public ServicesUser getUserInfo(String userNumber, String userEmail) {
		ServicesUser serviceUser = getUserInfoFromDB(userNumber);
		ServicesUser serviceUserFromLDAP = getUserInfoFromLDAP(userEmail);
		if(serviceUserFromLDAP != null) {
			if(serviceUser.getMdmId() == null || serviceUser.getMdmLevel() == null) {
				serviceUser.setMdmId(serviceUserFromLDAP.getMdmId());
				serviceUser.setMdmLevel(serviceUserFromLDAP.getMdmLevel());
			}
			 serviceUser.setCountry(serviceUserFromLDAP.getCountry());
	    	 serviceUser.setLanguage(serviceUserFromLDAP.getLanguage());
		}
		return serviceUser;
	}
	
	private ServicesUser getUserInfoFromLDAP(String userEmail) {
		String  ldapUrl=PropertiesUtil.LDAP_URL;
        String  userName=PropertiesUtil.LDAP_USERNAME;
        String password=PropertiesUtil.LDAP_PASSWORD;
		com.lexmark.userservice.UserService ldapUserService = new com.lexmark.userservice.UserService(userName, password, ldapUrl, null);
		User userbean = ldapUserService.getUserByEmail(userEmail);
		ServicesUser serviceUser = new ServicesUser();
		if(userbean != null & userbean.getUserid()!=null) {
			logger.debug("Valid email id or user number for login attempt by: ");
	    	 serviceUser.setMdmId(userbean.getCompanyId());
	 		//result.setMdmLevel(userbean.getUserLevel());
	    	 serviceUser.setMdmLevel(makeProper(userbean.getUserLevel()));
	    	 serviceUser.setCountry(userbean.getCountry());
	    	 serviceUser.setLanguage(userbean.getLanguage());
	    	 logger.debug("**** " + userName + " LDAP : MdmId  :" + serviceUser.getMdmId());
	    	 logger.debug("**** " + userName + " LDAP : MdmLevel  :" + serviceUser.getMdmLevel());
	    	 logger.debug("**** " + userName + " LDAP : CHLNode  :" + serviceUser.getChlNodeId());
	    	 logger.debug("**** " + userName + " LDAP : Language  :" + serviceUser.getLanguage());
	    	 logger.debug("**** " + userName + " LDAP : Country  :" + serviceUser.getCountry());
	 	}else{
	    	  throw new RuntimeException("Invalid email id or user number based on LDAP response for login attempt by email : " + userEmail );
	    }
		return serviceUser;
	}
	
	private ServicesUser getUserInfoFromDB(String userName) {
		DBParameter[] params = new DBParameter[1]; 
		int i=0;
		params[i++]= new StringDBParameter(userName, Types.VARCHAR);
		DBConnection dbconn= new DBConnection();
		ServicesUser serviceUser = new ServicesUser();
		try
		{
			dbconn.open();
			dbconn.queryPrepareStatement(QUERY_SERVICES_USER, params);
			while(dbconn.rs.next()) {
				serviceUser.setMdmId(dbconn.rs.getString("RESTRICT_MDM_ID"));
				serviceUser.setMdmLevel(dbconn.rs.getString("RESTRICT_MDM_LEVEL"));
				serviceUser.setChlNodeId(dbconn.rs.getString("CHL_NODE_ID"));
			}
		} catch(Exception ex) {
			throw new RuntimeException(ex);
		} finally {
			dbconn.close();
		}
		logger.debug("**** " + userName + " service_user : MdmId  :" + serviceUser.getMdmId());
		logger.debug("**** " + userName + " service_user : MdmLevel  :" + serviceUser.getMdmLevel());
		logger.debug("**** " + userName + " service_user : CHLNode  :" + serviceUser.getChlNodeId());
		return serviceUser;
	}
	
	
	private String makeProper(String theString){
		 
		java.io.StringReader in = new java.io.StringReader(theString.toLowerCase());
	
		 boolean precededBySpace = true;
		 StringBuffer properCase = new StringBuffer();    
		 try {
		 while(true) {      
		 	int i = in.read();
		 	  if (i == -1)  break;      
		 	    char c = (char)i;
		 	    if (c == ' ' || c == '"' || c == '(' || c == '.' || c == '/' || c == '\\' || c == ',') {
		 	      properCase.append(c);
		 	      precededBySpace = true;
		 	   } else {
		 	      if (precededBySpace) { 
		 		 properCase.append(Character.toUpperCase(c));
		 	   } else { 
		 	         properCase.append(c); 
		 	   }
		 	   precededBySpace = false;
		 	}
		    }
		 } catch(IOException ex) {
			 return "";
		 }
		return properCase.toString();    
		 
	}

}
