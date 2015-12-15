package com.lexmark.util;

import static com.lexmark.constants.LexmarkConstants.ROLE_PUBLISHING;
import static com.lexmark.constants.LexmarkConstants.ROLE_SERVICE_TECHNICIAN;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;

import com.lexmark.constants.LexmarkPPConstants;
import com.lexmark.domain.Account;
import com.lexmark.exception.LoginException;
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
			throw new Exception(ErrorMessageUtil.getErrorMessage(LexmarkPPConstants.ERROR_MESSAGE_BUNDLE, "exception.user.noEmailAddressException", userIdObejct, request.getLocale()));			
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
	
	public static List<String> getUserRoleNameList(PortletRequest request){
		// LDAP Roles
		PortletSession portletSession =request.getPortletSession();
		return PortalSessionUtil.getUserRoles(portletSession);
	}

	public static boolean isPublishing(PortletRequest request) {
		List<String> userRoleNameList = getUserRoleNameList(request);
		for (String roleName : userRoleNameList) {
			if (ROLE_PUBLISHING.equalsIgnoreCase(roleName)) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean isTechnician(PortletRequest request) {
		List<String> userRoleNameList = getUserRoleNameList(request);
		for (String roleName : userRoleNameList) {
			if (ROLE_SERVICE_TECHNICIAN.equalsIgnoreCase(roleName)) {
				return true;
			}
		}
		return false;
	}
	
	public static void  setUserFlagsInSession(PortletSession portletSession, List<Account> accountList) {
		if(accountList == null) {
			return;
		}
		Boolean directPartnerFlag = false;
		Boolean indirectPartnerFlag = false;
		Boolean logisticsPartnerFlag = false;
		Boolean createClaimFlag = false;
		Boolean allowAdditionalPaymentRequestFlag = false;
		Boolean uploadClaimFlag = false;
		Boolean uploadRequestFlag = false;
		/*
		 * Added for MPS 2.1 Hardware debrief
		 * */
		Boolean isHardwareDebrief =false;
		for(Account account: accountList) {
			if(account.isDirectPartnerFlag()) {
				directPartnerFlag = true;
			}
			if(account.isIndirectPartnerFlag()) {
				indirectPartnerFlag = true;
			}
			if(account.isLogisticsPartnerFlag()) {
				logisticsPartnerFlag = true;
			}
			if(account.isCreateClaimFlag()) {
				createClaimFlag = true;
			}
			if(account.isAllowAdditionalPaymentRequestFlag()) {
				allowAdditionalPaymentRequestFlag = true;
			}
			if(account.isUploadClaimFlag()) {
				uploadClaimFlag = true;
			}
			if(account.isUploadRequestFlag()) {
				uploadRequestFlag = true;
			}
			/*Changes for hardware debrief*/
			if(account.isDebriefInstallFlag()){
				isHardwareDebrief=true;
			}
		}
		PortalSessionUtil.setDirectPartnerFlag(portletSession, directPartnerFlag);
		PortalSessionUtil.setIndirectPartnerFlag(portletSession, indirectPartnerFlag);
		PortalSessionUtil.setlogisticsPartnerFlag(portletSession, logisticsPartnerFlag);
		PortalSessionUtil.setCreateClaimFlag(portletSession, createClaimFlag);
		PortalSessionUtil.setAllowAdditionalPaymentRequestFlag(portletSession, allowAdditionalPaymentRequestFlag);
		PortalSessionUtil.setUploadClaimFlag(portletSession, uploadClaimFlag);
		PortalSessionUtil.setUploadRequestFlag(portletSession, uploadRequestFlag);
		/*Changes for hardware debrief*/
		PortalSessionUtil.setHardwareDebriefFlag(portletSession, isHardwareDebrief);
	}
}
