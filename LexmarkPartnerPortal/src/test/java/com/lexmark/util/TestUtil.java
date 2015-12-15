package com.lexmark.util;

import java.lang.reflect.Field;

import javax.portlet.PortletSession;

import org.springframework.util.ReflectionUtils;

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.contract.LdapUserDataContract;
import com.lexmark.contract.ServicesUserContract;
import com.lexmark.domain.ServicesUser;
import com.lexmark.result.LdapUserDataResult;
import com.lexmark.result.ServicesUserResult;
import com.lexmark.service.impl.mock.UserServiceImpl;

public class TestUtil {

	private final static String MDMID= "1-09OP98";
	private final static String MDMLEVEL= "global";
	private static ServicesUserContract serviceUserContract = new ServicesUserContract();
	private static LdapUserDataContract ldapUserContract = new LdapUserDataContract();
	
	public static  ServicesUser getServiceUser(){
		serviceUserContract.setEmailAddress("bruno@7cogs.com");
		serviceUserContract.setUserNumber("LXK-099");
		return retrieveServicesUser(serviceUserContract).getServicesUser();	
	}
	
	
	public static  LdapUserDataResult getLDAPUser(){
		ldapUserContract.setEmailAddress("bruno@7cogs.com");
		return retrieveLdapUserData(ldapUserContract);
	}
	
	public static void initSession(PortletSession session){
		initServicesUserInSession(session);
		initLDAPUserInSession(session);
	}
	
	
	private static void initServicesUserInSession(PortletSession session){
		session.setAttribute(LexmarkConstants.SERVICES_USER_PHASE2, getServiceUser(),session.APPLICATION_SCOPE);
	}
	
	private static void initLDAPUserInSession(PortletSession session){
		PortalSessionUtil.setLdapUserData(session,getLDAPUser());
	}
	
	private void initRolesInSession(PortletSession session){
		session.setAttribute(LexmarkConstants.USERROLES_PHASE2, this.getLDAPUser(),session.APPLICATION_SCOPE);
	}
	
	
	private static  ServicesUserResult retrieveServicesUser(ServicesUserContract contract){
		ServicesUserResult result = null ;
		try{
		UserServiceImpl userService = new UserServiceImpl();
		result =  userService.retrieveServicesUser(contract);
		}catch(Exception e){
		}finally{
			return result;
		}
	}
	
	private static  LdapUserDataResult retrieveLdapUserData(LdapUserDataContract contract){
		LdapUserDataResult result = null ;
		try{
		UserServiceImpl userService = new UserServiceImpl();
		result =  userService.retrieveLdapUserData(contract);
		}catch(Exception e){
		}finally{
			return result;
		}
	}
	
	public static void setProperty(Object bean, String propertyName, Object value)throws IllegalArgumentException,IllegalAccessException{
		for (Field field : bean.getClass().getDeclaredFields()) {
			if(propertyName.equals(field.getName())){
				if(!field.isAccessible()){
					ReflectionUtils.makeAccessible(field);
				}
				field.set(bean, value);
				break;
			}
		}
	}
}
