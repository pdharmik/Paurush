package com.lexmark.service.impl.real.jdbc;

import java.util.Iterator;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.lexmark.contract.LdapSecurityData;
import com.lexmark.contract.LdapUserDataContract;
import com.lexmark.contract.ServicesUserContract;
import com.lexmark.domain.ServicesUser;
import com.lexmark.result.LdapUserDataResult;
import com.lexmark.result.ServicesUserResult;
import com.lexmark.userservice.User;
import com.lexmark.util.JndiLookupUtil;

public class UserServiceImpl implements com.lexmark.service.api.UserService {

	private static Logger logger = LogManager.getLogger(UserServiceImpl.class);
	private static final String HQL_GET_SERVICES_USER_BY_USER_NUMBER =
		"from ServicesUser where usernumber = :userNumber";

	public LdapUserDataResult retrieveLdapUserData(LdapUserDataContract contract)
			throws Exception {
 		LdapUserDataResult result = new LdapUserDataResult();

		LogManager.getLogger(UserServiceImpl.class.getName()).info("In retrieveLdapUserData");
		LdapSecurityData ldapServiceResult = JndiLookupUtil.getLdapServiceResult();
		if(logger.isDebugEnabled()){
			logger.debug("UserName:" + ldapServiceResult.getUserName());
			logger.debug("Password:" + ldapServiceResult.getPassword());
			logger.debug("LdapUrl:" + ldapServiceResult.getLdapUrl());
			logger.debug("JdbcJndiUrl:" + ldapServiceResult.getJdbcJndiUrl());
		}
		com.lexmark.userservice.UserService ldapUserService = new com.lexmark.userservice.UserService(ldapServiceResult.getUserName(), ldapServiceResult.getPassword(), ldapServiceResult.getLdapUrl(), ldapServiceResult.getJdbcJndiUrl());
		
        User userbean = ldapUserService.getUserByEmail(contract.getEmailAddress()); 
        //User userbean = ldapUserService.getUserByEmail("portal1@lexmark.com"); 
        if(userbean != null && userbean.getUserid()!=null) {
	    	 logger.info("Valid email id or user number for login attempt by: ");
	    	 
	 		result.setMdmId(userbean.getCompanyId());
	 		//result.setMdmLevel(userbean.getUserLevel());
	 		result.setMdmLevel(makeProper(userbean.getUserLevel()));
	 		result.setContactId(userbean.getSiebelContactId());
	 		result.setEmailAddress(userbean.getEmail());
	 		result.setFirstName(userbean.getFirstname());
	 		result.setLastName(userbean.getLastname());
	 		result.setWorkPhone(userbean.getWorkPhone());
	 		result.setUserNumber(userbean.getId());
	 		result.setLanguage(userbean.getLanguage());
	 		result.setCountry(userbean.getCountry());
	 		result.setUserSegment(makeProper(userbean.getUserSegment()));
	 		result.setShortId(userbean.getUserid());
	 		result.setCompanyName(userbean.getCompanyName());
	 		if (userbean.getUserSegment().equalsIgnoreCase("employee")){
	 			result.setUserRoles(userbean.getGroups());
	 		}else{
	 			result.setUserRoles(userbean.getRoles());
	 		}
	 		
	 	}else{
	    	  logger.info( "Invalid email id or user number based on LDAP response for login attempt by: " + contract.getEmailAddress() );
	    	  throw new Exception("Invalid email id or user number based on LDAP response for login attempt by: " + contract.getEmailAddress() );
	    }

       	printUserDetails(result);
		
 		return result;
	}

	public ServicesUserResult retrieveServicesUser(ServicesUserContract contract)
			throws Exception {
		ServicesUserResult result = new ServicesUserResult();
		ServicesUser servicesUser = null;
		
		try {
			Query query = HibernateUtil.getSession().createQuery(HQL_GET_SERVICES_USER_BY_USER_NUMBER);
			query.setParameter("userNumber", contract.getUserNumber());
			Iterator it = query.iterate();
			// UserNumber already exists
			if (it.hasNext()) {
				servicesUser = (ServicesUser) it.next();
				servicesUser.setEmailAddress(contract.getEmailAddress());
			} else {
				servicesUser = new ServicesUser();
				servicesUser.setUserNumber(contract.getUserNumber());
				servicesUser.setEmailAddress(contract.getEmailAddress());
				
				if (logger.isDebugEnabled()) {
					logger.debug("Can not find ServicesUser with userNumber:" + contract.getUserNumber());
				}
			}
		} catch (HibernateException ex) {
			ex.printStackTrace();
			logger.error("Failed to retrieveServicesUser with userNumber:" + contract.getUserNumber());
		} finally {
			HibernateUtil.closeSession();
		}
		result.setServicesUser(servicesUser);
		return result;
	}

    public static void printUserDetails(LdapUserDataResult ldapUserDataResult){
  	
    	//printStr("RemoteUser", userbean.getRemoteUser());
    	
    	printStr("Email", ldapUserDataResult.getEmailAddress());
        printStr("First Name", ldapUserDataResult.getFirstName());
        printStr("Last Name", ldapUserDataResult.getLastName());
        printStr("User Number", ldapUserDataResult.getUserNumber());
        printStr("MDM Id", ldapUserDataResult.getMdmId());
        printStr("MDM Level", ldapUserDataResult.getMdmLevel());
        printStr("Work Phone", ldapUserDataResult.getWorkPhone());
        printStr("Language", ldapUserDataResult.getLanguage());
        printStr("Country", ldapUserDataResult.getCountry());
        printStr("Company name", ldapUserDataResult.getCompanyName());
    	
    	logger.debug("Email: " +  ldapUserDataResult.getEmailAddress());
    	logger.debug("First Name: "+ ldapUserDataResult.getFirstName());
    	logger.debug("Last Name: "+ ldapUserDataResult.getLastName());
    	logger.debug("User Number: "+ ldapUserDataResult.getUserNumber());
    	logger.debug("MDM Id: "+ ldapUserDataResult.getMdmId());
    	logger.debug("MDM Level: "+ ldapUserDataResult.getMdmLevel());
        logger.debug("Work Phone: "+ ldapUserDataResult.getWorkPhone());
        logger.debug("Company Name: "+ ldapUserDataResult.getCompanyName());
        for(String role: ldapUserDataResult.getUserRoles()){
        	 printStr("Role: ", role);
        	logger.debug("Role: "+ role);

        }
        
        logger.debug("Seibel Contact Id: "+ ldapUserDataResult.getContactId());
        logger.debug("-----------------------------");
    	
    	printStr("Seibel Contact Id", ldapUserDataResult.getContactId());
        logger.info("-----------------------------");
    	
    	
    }
    private static void printStr(String name, String value) {
    	logger.debug(name + ": " + value);
    }
    
	public String makeProper(String theString) throws java.io.IOException{
		 
		java.io.StringReader in = new java.io.StringReader(theString.toLowerCase());
		 boolean precededBySpace = true;
		 StringBuffer properCase = new StringBuffer();    
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
		 
		return properCase.toString();    
		 
	}


}
