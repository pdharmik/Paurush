package com.lexmark.services.util;

import static com.lexmark.constants.LexmarkConstants.ROLE_PUBLISHING;

import java.util.List;
import java.util.Map;

import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.services.LexmarkSPConstants;
import com.lexmark.services.exception.LoginException;
import com.liferay.portal.service.UserLocalServiceUtil;

public class LexmarkUserUtil {
	
	public static String getUserEmailAddress(PortletRequest request) throws Exception {
	    Map uinfo = (Map) request.getAttribute(PortletRequest.USER_INFO);
		if(uinfo == null) {
			throw new LoginException();
		}
		int userId = Integer.valueOf(uinfo.get("liferay.user.id").toString());
		String emailAddress = UserLocalServiceUtil.getUserById(userId).getEmailAddress();
		if(emailAddress == null || emailAddress.equals("")) {
			String[] userIdObejct = {uinfo.get("liferay.user.id").toString()}; 
			throw new Exception(ServiceStatusErrorMessageUtil.getErrorMessage(LexmarkSPConstants.ERROR_MESSAGE_BUNDLE, "exception.user.noEmailAddressException", userIdObejct, request.getLocale()));			
		}
		return emailAddress;
	}
	
	public static String getUserId(PortletRequest request) throws Exception {
	    Map uinfo = (Map) request.getAttribute(PortletRequest.USER_INFO);
		if(uinfo == null) {
			throw new LoginException();
		}
		String userId = uinfo.get("liferay.user.id").toString();
		return userId;
	}
	
	public static List<String> getUserRoleNameList(PortletRequest request)throws Exception{
		// LDAP Roles
		PortletSession portletSession =request.getPortletSession();
		return PortalSessionUtil.getUserRoles(portletSession);
	}

	public static boolean isEmployee(PortletRequest request)throws Exception{
		Map uinfo = (Map) request.getAttribute(PortletRequest.USER_INFO);
		if(uinfo == null) {
			throw new LoginException();
		}
		int userId = Integer.valueOf(uinfo.get("liferay.user.id").toString());
		List<com.liferay.portal.model.Role> roles = UserLocalServiceUtil.getUserById(userId).getRoles();
		
		boolean isEmployee = false;
		for(com.liferay.portal.model.Role role:roles){
			if (role.getName().equalsIgnoreCase("Lexmark Employees")){
				isEmployee = true;
			}
		}
		return isEmployee;
	}

	public static boolean isPublishing(PortletRequest request) throws Exception{
		List<String> userRoleNameList = getUserRoleNameList(request);
		for (String roleName : userRoleNameList) {
			if(ROLE_PUBLISHING.equalsIgnoreCase(roleName)){
				return true;
			}
		}
		return false;
	}
	
	public static boolean isFSETechnician(PortletRequest request) throws Exception{
		PortletSession portletSession =request.getPortletSession();
		if(PortalSessionUtil.getUserSegment(portletSession).equalsIgnoreCase(LexmarkConstants.USER_SEGMENT_EMPLOYEE)){
			List<String> userRoleNameList = getUserRoleNameList(request);
			for (String roleName : userRoleNameList) {
				if(LexmarkConstants.ROLE_SERVICE_TECHNICIAN.equalsIgnoreCase(roleName)){
					return true;
				}
			}			
		}
		return false;
	}
	
	//added for BRD 14-07-04
	public static List<String> getPartnerTypeList(PortletRequest request)throws Exception{
		// LDAP Roles
		PortletSession portletSession =request.getPortletSession();
		return PortalSessionUtil.getPartnerTypes(portletSession);
	}
	
	public static List<String> getCountriesList(PortletRequest request)throws Exception{
		// LDAP Roles
		PortletSession portletSession =request.getPortletSession();
		return PortalSessionUtil.getCountries(portletSession);
	}
}
