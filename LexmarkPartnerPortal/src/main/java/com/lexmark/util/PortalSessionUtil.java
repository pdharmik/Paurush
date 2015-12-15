package com.lexmark.util;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;
import javax.portlet.ResourceRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.domain.AccountContact;
import com.lexmark.domain.ServicesUser;
import com.lexmark.form.BaseForm;
import com.lexmark.result.LdapUserDataResult;
import com.lexmark.service.api.CrmSessionHandle;
import com.liferay.portal.model.User;
import com.liferay.portal.util.PortalUtil;

public class PortalSessionUtil {
	private static Logger logger = LogManager.getLogger(PortalSessionUtil.class);

	private static final String DIRECT_PARTNET_FLAG = "flag.directPartnerFlag";
	private static final String INDIRECT_PARTNET_FLAG = "flag.indirectPartnerFlag";
	private static final String LOGISTICS_PARTNET_FLAG = "flag.logiticsPartnerFlag";
	private static final String LEXMARK_EMPLOYEE_FLAG = "flag.lexmarkEmployeeFlag";
	private static final String LEXMARK_FSE_FLAG = "flag.lexmarkFSEFlag";
	private static final String CREATE_CLAIM_FLAG = "flag.createClaimFlag";
	private static final String ALLOW_ADDITIONAL_PAYMENT_REQUEST_FLAG = "flag.allowAdditionalPaymentRequestFlag";
	private static final String CREATE_CLAIM_START_TIME = "omniture.createClaimStart";
	private static final String UPLOAD_CLAIM_FLAG = "flag.uploadClaimFlag";
	private static final String UPLOAD_REQUEST_FLAG = "flag.uploadRequestFlag";
	/**
	 * Added for MPS 2.1 HArdware Debrief
	 */	 
	private static final String HARDWARE_DEBRIEF_FLAG = "flag.hardwareDebrief";
	
	
	/**
	 * @param portletRequest 
	 * @return CrmSessionHandle 
	 * */
	public static CrmSessionHandle getSiebelCrmSessionHandle(PortletRequest portletRequest) {
		HttpServletRequest httpServletRequest = PortalUtil.getHttpServletRequest(portletRequest);
		HttpSession webSession = httpServletRequest.getSession();
		CrmSessionHandle siebelCrmSessionHandle = (CrmSessionHandle) webSession.getAttribute(LexmarkConstants.SIEBEL_SESSION_ID_PHASE2);
		return siebelCrmSessionHandle;
	}
	
	/**
	 * @param session 
	 * @return String 
	 * */
	public static String getMdmIdOrDunsBasedOnLevel(PortletSession session){
		String ldapMdmId = null;
		Map<String, Object> ldapUserData =  (Map)session.getAttribute(LexmarkConstants.LDAP_USER_DATA_PHASE2, session.APPLICATION_SCOPE);
		if(ldapUserData != null){
			ldapMdmId = (String)ldapUserData.get(LexmarkConstants.MDMID);
		}

		ServicesUser servicesUser = getServiceUser(session);
		if (servicesUser.getMdmId()!= null && !servicesUser.getMdmId().equals("")){
			if (servicesUser.getMdmLevel().equalsIgnoreCase(LexmarkConstants.MDM_LEVEL_GLOBAL)|| 
					servicesUser.getMdmLevel().equalsIgnoreCase(LexmarkConstants.MDM_LEVEL_DOMESTIC)){
				//This will be the DUNS Number and is set in the UserInfoInterceptor
				return servicesUser.getMdmId();
			}else{
				//This will be the MDM ID
				return ldapMdmId;				
			}
		}else{
			//This will be the MDM ID
			return ldapMdmId;
		}
	}
	
	/**
	 * @param session 
	 * @return String 
	 * */
	public static String getMdmId(PortletSession session){
		ServicesUser servicesUser = getServiceUser(session);
		if (servicesUser.getMdmId()!= null && !servicesUser.getMdmId().equals("")){
			return servicesUser.getMdmId();
		}else{
			Map<String, Object> ldapUserData =  (Map)session.getAttribute(LexmarkConstants.LDAP_USER_DATA_PHASE2, session.APPLICATION_SCOPE);
			if(ldapUserData != null){
				return (String)ldapUserData.get(LexmarkConstants.MDMID);
			}
			
			else{
				return null;
			}
		}
	}
	
	/**
	 * @param session 
	 * @return String 
	 * */
	public static String getMdmLevel(PortletSession session){
		ServicesUser servicesUser = getServiceUser(session);
		if (servicesUser.getMdmLevel()!= null && !servicesUser.getMdmLevel().equals("")){
			return servicesUser.getMdmLevel();
		}else{
			Map<String, Object> ldapUserData =  (Map)session.getAttribute(LexmarkConstants.LDAP_USER_DATA_PHASE2, session.APPLICATION_SCOPE);
			if(ldapUserData != null){
				return (String)ldapUserData.get(LexmarkConstants.MDMLEVEL);
			}
			else{
				return null;
			}
		}
	}
	
	/**
	 * @param session 
	 * @return String 
	 * */
	public static String getContactId(PortletSession session){
		Map<String, Object> ldapUserData =  (Map)session.getAttribute(LexmarkConstants.LDAP_USER_DATA_PHASE2, session.APPLICATION_SCOPE);
		if(ldapUserData != null){
			return (String)ldapUserData.get(LexmarkConstants.CONTACTID);
		}
		else{
			return null;
		}
	}

	/**
	 * @param session 
	 * @return String 
	 * */
	public static String getChlNodeId(PortletSession session){
		ServicesUser servicesUser = getServiceUser(session);
		if (servicesUser.getChlNodeId()!= null && !servicesUser.getChlNodeId().equals("")){
			return servicesUser.getChlNodeId();
		}else{
			return null;
		}
	}
	
	/**
	 * @param session 
	 * @return String 
	 * */
	public static String getFirstName(PortletSession session){
		Map<String, Object> ldapUserData =  (Map)session.getAttribute(LexmarkConstants.LDAP_USER_DATA_PHASE2, session.APPLICATION_SCOPE);
		if(ldapUserData != null){
			return (String)ldapUserData.get(LexmarkConstants.FIRSTNAME);
		}
		else{
			return null;
		}
	}
	
	/**
	 * @param session 
	 * @return String 
	 * */
	public static String getLastName(PortletSession session){
		Map<String, Object> ldapUserData =  (Map)session.getAttribute(LexmarkConstants.LDAP_USER_DATA_PHASE2, session.APPLICATION_SCOPE);
		if(ldapUserData != null){
			return (String)ldapUserData.get(LexmarkConstants.LASTNAME);
		}
		else{
			return null;
		}
	}
	
	/**
	 * @param session 
	 * @return String 
	 * */
	public static String getWorkPhone(PortletSession session){
		Map<String, Object> ldapUserData =  (Map)session.getAttribute(LexmarkConstants.LDAP_USER_DATA_PHASE2, session.APPLICATION_SCOPE);
		if(ldapUserData != null){
			return (String)ldapUserData.get(LexmarkConstants.WORKPHONE);
		}
		else{
			return null;
		}
	}
	
	/**
	 * @param session 
	 * @return String 
	 * */
	public static String getEmailAddress(PortletSession session){
		ServicesUser servicesUser = getServiceUser(session);
		if (servicesUser.getEmailAddress()!= null && !servicesUser.getEmailAddress().equals("")){
			return servicesUser.getEmailAddress();
		}else{		
				return null;
		}
	}
	
	/**
	 * @param session 
	 * @return List 
	 * */
	public static List<String> getUserRoles(PortletSession session){
		List<String> ldapUserRoles =  (List<String>)session.getAttribute(LexmarkConstants.USERROLES_PHASE2, session.APPLICATION_SCOPE);
		if(ldapUserRoles != null){
			return ldapUserRoles;
		}
		else{
			return null;
		}
	}
	
	/**
	 * @param session 
	 * @return String 
	 * */
	public static String getUserSegment(PortletSession session){
		Map<String, Object> ldapUserData =  (Map)session.getAttribute(LexmarkConstants.LDAP_USER_DATA_PHASE2, PortletSession.APPLICATION_SCOPE);
		if(ldapUserData != null){
			return (String)ldapUserData.get(LexmarkConstants.USERSEGMENT);
		}
		else{
			return null;
		}
	}
	
	/**
	 * @param portletRequest 
	 * @return Long 
	 * */
	public static Long createSubmitToken(PortletRequest portletRequest){
		Long token = new Date().getTime();
		PortletSession portletSession = portletRequest.getPortletSession();
		portletSession.setAttribute(LexmarkConstants.SUBMIT_TOKEN, token, PortletSession.PORTLET_SCOPE);
		
		//This token will be used in ExpirePageFilter
		String uniqueToken = portletSession.getId() + new Date().getTime();
		portletRequest.setAttribute("UNIQUETOKENFORPAGEREFRESH", uniqueToken);
		return token;
	}
	
	/**
	 * @param session 
	 * @return ServicesUser 
	 * */
	public static ServicesUser getServiceUser(PortletSession session) {
		ServicesUser serviceUser = (ServicesUser) session.getAttribute(LexmarkConstants.SERVICES_USER_PHASE2, PortletSession.APPLICATION_SCOPE);
		return serviceUser;
	}

	/**
	 * @param portletRequest 
	 * @param crmSessionHandle 
	 * */
	public static void setSiebelCrmSessionHandle(PortletRequest portletRequest, CrmSessionHandle crmSessionHandle) {
		//session.setAttribute(LexmarkConstants.SIEBEL_SESSION_ID, crmSessionHandle, session.APPLICATION_SCOPE);
		HttpServletRequest httpServletRequest = PortalUtil.getHttpServletRequest(portletRequest);
		HttpSession webSession = httpServletRequest.getSession();
		webSession.setAttribute(LexmarkConstants.SIEBEL_SESSION_ID_PHASE2, crmSessionHandle);
		//logger.info("UserInfoInterceptor - crmSessionHandle: " + webSession.getAttribute(LexmarkConstants.SIEBEL_SESSION_ID));

	}
	
	/**
	 * @param portletSession 
	 * @param servicesUser 
	 * */
	public static void  setServicesUser(PortletSession portletSession, ServicesUser servicesUser) {
		portletSession.setAttribute(LexmarkConstants.SERVICES_USER_PHASE2, servicesUser, portletSession.APPLICATION_SCOPE);
	}	
	
	/**
	 * @param portletSession 
	 * @param ldapUserDataResult 
	 * */
	public static void setLdapUserData(PortletSession portletSession, LdapUserDataResult ldapUserDataResult) {
		Map<String, String> ldapUserData = new HashMap<String, String>();
		ldapUserData.put(LexmarkConstants.MDMID, ldapUserDataResult.getMdmId());//Amit is setting this one up for testing and should have SR, Contact, Addresses, and createSRFlag set
		ldapUserData.put(LexmarkConstants.MDMLEVEL, ldapUserDataResult.getMdmLevel());
		ldapUserData.put(LexmarkConstants.CONTACTID, ldapUserDataResult.getContactId());
		ldapUserData.put(LexmarkConstants.USERNUMBER, ldapUserDataResult.getUserNumber());
		ldapUserData.put(LexmarkConstants.FIRSTNAME, ldapUserDataResult.getFirstName());
		ldapUserData.put(LexmarkConstants.LASTNAME, ldapUserDataResult.getLastName());
		ldapUserData.put(LexmarkConstants.WORKPHONE, ldapUserDataResult.getWorkPhone());
		ldapUserData.put(LexmarkConstants.LANGUAGE, ldapUserDataResult.getLanguage());
		ldapUserData.put(LexmarkConstants.COUNTRY, ldapUserDataResult.getCountry());
		ldapUserData.put(LexmarkConstants.EMAIL, ldapUserDataResult.getEmailAddress());
		ldapUserData.put(LexmarkConstants.USERSEGMENT, ldapUserDataResult.getUserSegment());
		ldapUserData.put(LexmarkConstants.SHORTID, ldapUserDataResult.getShortId());
		
		for(String userDataKey : ldapUserData.keySet()) {
			String userDataItem = ldapUserData.get(userDataKey);
			
//			if (ldapUserDataResult.getUserSegment().equalsIgnoreCase("employee") && (userDataKey.equals(LexmarkConstants.MDMID) || userDataKey.equals(LexmarkConstants.MDMLEVEL))){
			if (ldapUserDataResult.getUserSegment().equalsIgnoreCase("employee")){
				//Do nothing
				logger.info("Employee User Type");
			}else{
				//** Provide in-screen information on missing or omitted credentials.
		        // Not expected to occur in Prod, will be useful in Textin
					if(userDataItem == null || userDataItem.isEmpty()) {
						throw new RuntimeException(
								"**** Incomplete or Mismatched LDAP and/or Siebel Credentials ****<br>" +
								"Error - Missing LdapUserData: "  + userDataKey + " is empty" + "<br>" +
								"<br>" +
								"EMAIL"                      +": "+ ldapUserDataResult.getEmailAddress() + "<br>" +
								LexmarkConstants.MDMID       +": "+ ldapUserDataResult.getMdmId()  + "<br>" +
								LexmarkConstants.MDMLEVEL    +": "+ ldapUserDataResult.getMdmLevel()  + "<br>" +
								//"MdmIdOrDunsBasedOnLevel"    +": "+ ldapUserDataResult.getMdmIdOrDunsBasedOnLevel() + "<br>" +
								//"ChannelNodeId"              +": "+ ldapUserDataResult.getChlNodeId() + "<br>" +
								LexmarkConstants.CONTACTID   +": "+ ldapUserDataResult.getContactId() + "<br>" +
								LexmarkConstants.USERNUMBER  +": "+ ldapUserDataResult.getUserNumber() + "<br>" + 
								LexmarkConstants.USERROLES   +": "+ ldapUserDataResult.getUserRoles() + "<br>" + 
								LexmarkConstants.FIRSTNAME	 +": "+	ldapUserDataResult.getFirstName() + "<br>" +
								LexmarkConstants.LASTNAME    +": "+ ldapUserDataResult.getLastName()  + "<br>" +
								LexmarkConstants.WORKPHONE   +": "+ ldapUserDataResult.getWorkPhone() + "<br>" +
								LexmarkConstants.LANGUAGE    +": "+ ldapUserDataResult.getLanguage()  + "<br>" +
								LexmarkConstants.SHORTID    +": "+ ldapUserDataResult.getShortId()  + "<br>" +
								LexmarkConstants.LANGUAGE    +": "+ ldapUserDataResult.getLanguage() + "<br>" 
							);
					}
				
			}
		}
		
		portletSession.setAttribute(LexmarkConstants.LDAP_USER_DATA_PHASE2, ldapUserData, portletSession.APPLICATION_SCOPE);
		portletSession.setAttribute(LexmarkConstants.USERROLES_PHASE2, ldapUserDataResult.getUserRoles(), portletSession.APPLICATION_SCOPE);
	}
	
	/**
	 * @param session 
	 * @return Boolean 
	 * */
	public static Boolean isAccountAssociated(PortletSession session) {
		if(session == null) {
			return false;
		}
		String employeeMdmId = (String)session.getAttribute("employeeMdmId", session.APPLICATION_SCOPE);
		String employeeMdmLevel = (String)session.getAttribute("employeeMdmLevel", session.APPLICATION_SCOPE);
		String employeeAccountName = (String)session.getAttribute("employeeAccountName", session.APPLICATION_SCOPE);
		
		ServicesUser serviceUser = (ServicesUser) session.getAttribute(LexmarkConstants.SERVICES_USER_PHASE2, session.APPLICATION_SCOPE);
		if(serviceUser == null){
			if (employeeMdmId == null || employeeMdmId.equals("") ||
					employeeMdmLevel == null || employeeMdmLevel.equals("")){
				return false;				
			}else{
				serviceUser = new ServicesUser();
				serviceUser.setMdmId(employeeMdmId);
				serviceUser.setMdmLevel(employeeMdmLevel);
				serviceUser.setAccountName(employeeAccountName);
				PortalSessionUtil.setServicesUser(session, serviceUser);

			}
			return true;
		}else{
			if(getMdmId(session) == null || getMdmId(session).equals("") ||
					getMdmLevel(session) == null || getMdmLevel(session).equals("")) {
				if (employeeMdmId == null || employeeMdmId.equals("") ||
						employeeMdmLevel == null || employeeMdmLevel.equals("")){
					return false;				
				}else{
					serviceUser.setMdmId(employeeMdmId);
					serviceUser.setMdmLevel(employeeMdmLevel);
					serviceUser.setAccountName(employeeAccountName);
					PortalSessionUtil.setServicesUser(session, serviceUser);

				}
			}else{
				if (employeeMdmId == null || employeeMdmId.equals("") ||
						employeeMdmLevel == null || employeeMdmLevel.equals("")){
					//do nothing				
				}else{
					if(!getMdmId(session).equals(employeeMdmId) || !getMdmLevel(session).equals(employeeMdmLevel) ) {
						serviceUser.setMdmId(employeeMdmId);
						serviceUser.setMdmLevel(employeeMdmLevel);
						serviceUser.setAccountName(employeeAccountName);
						PortalSessionUtil.setServicesUser(session, serviceUser);

					}			 								
				}

			}
			return true;			
		}
	}
	
	/**
	 * @param session 
	 * @return Boolean 
	 * */
	public static Boolean getDirectPartnerFlag(PortletSession session) {
		if(session == null) {
			return Boolean.FALSE;
		}
		Boolean  flag = (Boolean) session.getAttribute(DIRECT_PARTNET_FLAG, session.APPLICATION_SCOPE);
		if(flag == null || flag.equals(Boolean.FALSE)) {
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}

	/**
	 * @param session 
	 * @param directPartnerFlag 
	 * */
	public static void setDirectPartnerFlag(PortletSession session,  Boolean directPartnerFlag) {
		if(session == null) {
			return ;
		}
		session.setAttribute(DIRECT_PARTNET_FLAG, directPartnerFlag, session.APPLICATION_SCOPE);
	}
	
	/**
	 * @param session 
	 * @return Boolean 
	 * */
	public static Boolean getIndirectPartnerFlag(PortletSession session) {
		if(session == null) {
			return Boolean.FALSE;
		}
		Boolean  flag = (Boolean) session.getAttribute(INDIRECT_PARTNET_FLAG, session.APPLICATION_SCOPE);
		if(flag == null || flag.equals(Boolean.FALSE)) {
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}

	/**
	 * @param session 
	 * @param indirectPartnerFlag 
	 * */
	public static void setIndirectPartnerFlag(PortletSession session,  Boolean indirectPartnerFlag) {
		if(session == null) {
			return ;
		}
		session.setAttribute(INDIRECT_PARTNET_FLAG, indirectPartnerFlag, session.APPLICATION_SCOPE);
	}
	
	/**
	 * @param session 
	 * @return Boolean 
	 * */
	public static Boolean getlogisticsPartnerFlag(PortletSession session) {
		if(session == null) {
			return Boolean.FALSE;
		}
		Boolean  flag = (Boolean) session.getAttribute(LOGISTICS_PARTNET_FLAG, session.APPLICATION_SCOPE);
		if(flag == null || flag.equals(Boolean.FALSE)) {
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}

	/**
	 * @param session 
	 * @param logisticsPartnerFlag 
	 * */
	public static void setlogisticsPartnerFlag(PortletSession session,  Boolean logisticsPartnerFlag) {
		if(session == null) {
			return ;
		}
		session.setAttribute(LOGISTICS_PARTNET_FLAG, logisticsPartnerFlag, session.APPLICATION_SCOPE);
	}
	
	/**
	 * @param session 
	 * @return Boolean 
	 * */
	public static Boolean getLexmarkEmployeeFlag(PortletSession session) {
		if(session == null) {
			return Boolean.FALSE;
		}
		Boolean  flag = (Boolean) session.getAttribute(LEXMARK_EMPLOYEE_FLAG, session.APPLICATION_SCOPE);
		if(flag == null || flag.equals(Boolean.FALSE)) {
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}

	/**
	 * @param session 
	 * @param lexmarkEmployeeFlag 
	 * */
	public static void setLexmarkEmployeeFlag(PortletSession session,  Boolean lexmarkEmployeeFlag) {
		if(session == null) {
			return ;
		}
		session.setAttribute(LEXMARK_EMPLOYEE_FLAG, lexmarkEmployeeFlag, session.APPLICATION_SCOPE);
	}
	
	/**
	 * @param session 
	 * @return Boolean 
	 * */
	public static Boolean getLexmarkFSEFlag(PortletSession session) {
		if(session == null) {
			return Boolean.FALSE;
		}
		Boolean  flag = (Boolean) session.getAttribute(LEXMARK_FSE_FLAG, session.APPLICATION_SCOPE);
		if(flag == null || flag.equals(Boolean.FALSE)) {
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}

	/**
	 * @param session 
	 * @param lexmarkFSEFlag 
	 * */
	public static void setLexmarkFSEFlag(PortletSession session,  Boolean lexmarkFSEFlag) {
		if(session == null) {
			return ;
		}
		session.setAttribute(LEXMARK_FSE_FLAG, lexmarkFSEFlag, session.APPLICATION_SCOPE);
	}
	
	/**
	 * @param session 
	 * @return Boolean 
	 * */
	public static Boolean getCreateClaimFlag(PortletSession session) {
		if(session == null) {
			return Boolean.FALSE;
		}
		Boolean  flag = (Boolean) session.getAttribute(CREATE_CLAIM_FLAG, session.APPLICATION_SCOPE);
		if(flag == null || flag.equals(Boolean.FALSE)) {
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}

	/**
	 * @param session 
	 * @param createClaimFlag 
	 * */
	public static void setCreateClaimFlag(PortletSession session,  Boolean createClaimFlag) {
		if(session == null) {
			return ;
		}
		session.setAttribute(CREATE_CLAIM_FLAG, createClaimFlag, session.APPLICATION_SCOPE);
	}

	/**
	 * @param session 
	 * @return Boolean 
	 * */
	public static Boolean getAllowAdditionalPaymentRequestFlag(PortletSession session) {
		if(session == null) {
			return Boolean.FALSE;
		}
		Boolean  flag = (Boolean) session.getAttribute(ALLOW_ADDITIONAL_PAYMENT_REQUEST_FLAG, session.APPLICATION_SCOPE);
		if(flag == null || flag.equals(Boolean.FALSE)) {
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}

	/**
	 * @param session 
	 * @param allowAdditionalPaymentRequestFlag 
	 * */
	public static void setAllowAdditionalPaymentRequestFlag(PortletSession session,  Boolean allowAdditionalPaymentRequestFlag) {
		if(session == null) {
			return ;
		}
		session.setAttribute(ALLOW_ADDITIONAL_PAYMENT_REQUEST_FLAG, allowAdditionalPaymentRequestFlag, session.APPLICATION_SCOPE);
	}
	
	/**
	 * @param request 
	 * @param form 
	 * @return Boolean 
	 * */
	public static Boolean isDuplicatedSubmit(PortletRequest request, BaseForm form){
		Boolean isDuplicated = false;
		PortletSession session = request.getPortletSession();
		Long tokenInSession = (Long)session.getAttribute(LexmarkConstants.SUBMIT_TOKEN, session.PORTLET_SCOPE);
		if(tokenInSession == null){
			isDuplicated = true;
		}
		else{
			isDuplicated = !tokenInSession.equals(form.getSubmitToken());
		}
		
		createSubmitToken(request);
		return isDuplicated;
	}
	
	/**
	 * @param request 
	 * @return AccountContact 
	 * */
	public static AccountContact retrieveLoginAccountContact(PortletRequest request) {
		AccountContact accountContact = new AccountContact();
		accountContact.setContactId(PortalSessionUtil.getContactId(request.getPortletSession()));
		accountContact.setFirstName(PortalSessionUtil.getFirstName(request
				.getPortletSession()));
		accountContact.setLastName(PortalSessionUtil.getLastName(request
				.getPortletSession()));
		accountContact.setWorkPhone(PortalSessionUtil.getWorkPhone(request
				.getPortletSession()));
		accountContact.setEmailAddress(PortalSessionUtil
				.getEmailAddress(request.getPortletSession()));
		return accountContact;
	}
	
	/**
	 * @param session 
	 * @param startTime 
	 * */
	public static void setCreateClaimStartTime(PortletSession session,  Calendar startTime) {
		if(session == null) {
			return ;
		}
		session.setAttribute(CREATE_CLAIM_START_TIME, startTime, session.APPLICATION_SCOPE);
	}
	
	/**
	 * @param session 
     * @return Calendar 
	 * */
	public static Calendar getCreateClaimStartTime(PortletSession session) {
		if(session == null) {
			return null;
		}
		Calendar  startTime = (Calendar) session.getAttribute(CREATE_CLAIM_START_TIME, session.APPLICATION_SCOPE);
		return startTime;
	}

	/**
	 * @param session 
     * @return Boolean 
	 * */
	public static Boolean getUploadClaimFlag(PortletSession session) {
		if(session == null) {
			return Boolean.FALSE;
		}
		Boolean  flag = (Boolean) session.getAttribute(UPLOAD_CLAIM_FLAG, session.APPLICATION_SCOPE);
		if(flag == null || flag.equals(Boolean.FALSE)) {
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}

	/**
	 * @param session 
	 * @param uploadClaimFlag 
	 * */
	public static void setUploadClaimFlag(PortletSession session,  Boolean uploadClaimFlag) {
		if(session == null) {
			return ;
		}
		session.setAttribute(UPLOAD_CLAIM_FLAG, uploadClaimFlag, session.APPLICATION_SCOPE);
	}

	/**
	 * @param session 
     * @return Boolean 
	 * */
	public static Boolean getUploadRequestFlag(PortletSession session) {
		if(session == null) {
			return Boolean.FALSE;
		}
		Boolean  flag = (Boolean) session.getAttribute(UPLOAD_REQUEST_FLAG, session.APPLICATION_SCOPE);
		if(flag == null || flag.equals(Boolean.FALSE)) {
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}

	/**
	 * @param session 
	 * @param uploadRequestFlag 
	 * */
	public static void setUploadRequestFlag(PortletSession session,  Boolean uploadRequestFlag) {
		if(session == null) {
			return ;
		}
		session.setAttribute(UPLOAD_REQUEST_FLAG, uploadRequestFlag, session.APPLICATION_SCOPE);
	}
	
	/**
	 * Added for MPS 2.1
	 * @param session 
	 * @param hardwareDebriefFlag 
	 * */
	public static void setHardwareDebriefFlag(PortletSession session,  Boolean hardwareDebriefFlag) {
		if(session == null) {
			return ;
		}
		session.setAttribute(HARDWARE_DEBRIEF_FLAG, hardwareDebriefFlag, session.APPLICATION_SCOPE);
	}	
	
	/**
	 * @param session 
     * @return Boolean 
	 * */
	public static Boolean getHardwareDebriefFlag(PortletSession session) {
		if(session == null) {
			return Boolean.FALSE;
		}
		Boolean  flag = (Boolean) session.getAttribute(HARDWARE_DEBRIEF_FLAG, session.APPLICATION_SCOPE);
		if(flag == null || flag.equals(Boolean.FALSE)) {
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}
	
	/**
	 * @param actionRequest 
	 * @param resourceRequest 
     * @return String 
	 * */
    public static String getUserIDFromPortalUtil(ActionRequest actionRequest, ResourceRequest resourceRequest){
		User user = null;
		String userId = null;
		try {
			if (actionRequest != null){
				user = PortalUtil.getUser(actionRequest);
			}else{
				user = PortalUtil.getUser(resourceRequest); 
			}
			long[] userIds = new long[]{user.getUserId()};
			userId = Long.toString(userIds[0]);
		} catch (Exception e) {
			logger.error("error occured"+e.getMessage());
		}
		return userId;
    }
    
    /**
	 * @param session 
     * @return String 
	 * */
	public static String getUserNumber(PortletSession session){
			Map<String, Object> ldapUserData =  (Map)session.getAttribute(LexmarkConstants.LDAP_USER_DATA_PHASE2, session.APPLICATION_SCOPE);
			if(ldapUserData != null){
				return (String)ldapUserData.get(LexmarkConstants.USERNUMBER);
			}
			else{
				return null;
			}
	}
	/**.
	 * Returns user type if the user is vendor, customer or partner
	 * 
	 * @param session PortletSession
	 * @return boolean
	 * @author wipro
	 */
	public static String getUserType(PortletSession session){
		String ldapUserType =(String) getUserSegment(session);
		String userType="";
		if (ldapUserType != null && ldapUserType.equalsIgnoreCase("Partner")){
			List<String> userRoleList = PortalSessionUtil.getUserRoles(session);
			if(userRoleList.contains(LexmarkConstants.ROLE_SERVICE_TECHNICIAN) ||
					userRoleList.contains(LexmarkConstants.ROLE_SERVICE_MANAGER)||
					userRoleList.contains(LexmarkConstants.ROLE_SERVICE_VIEW)||
					userRoleList.contains(LexmarkConstants.ROLE_PARTNER_ADMINISTRATOR)){
				userType="Partner";
			}else if(userRoleList.contains(LexmarkConstants.ROLE_SERVICE_SUPPORT) ||
					userRoleList.contains(LexmarkConstants.ROLE_STANDARD_ACCESS) ||
					userRoleList.contains(LexmarkConstants.ROLE_KEY_OPERATOR) ||//Added for new roles for MPS
					userRoleList.contains(LexmarkConstants.ROLE_SERVICE_VIEW) ||//Added for new roles for MPS
					userRoleList.contains(LexmarkConstants.ROLE_ACCOUNT_MANAGEMENT)||
					userRoleList.contains(LexmarkConstants.ROLE_PROJECT_MANAGEMENT)||
					userRoleList.contains(LexmarkConstants.ROLE_BILLING)){
				userType="Customer";
			}
		}else if (ldapUserType.equalsIgnoreCase("employee")){
			userType="Employee";
		}
		return userType;
	}
}
