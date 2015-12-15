package com.lexmark.services.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.util.Iterator;
import com.lexmark.constants.LexmarkConstants;
import com.lexmark.domain.Account;
import com.lexmark.domain.ServicesUser;
import com.lexmark.result.LdapUserDataResult;
import com.lexmark.service.api.CrmSessionHandle;
import com.lexmark.services.form.BaseForm;
import com.liferay.portal.util.PortalUtil;

public class PortalSessionUtil {
	private static Logger logger = LogManager.getLogger(PortalSessionUtil.class);

	public static CrmSessionHandle getSiebelCrmSessionHandle(PortletRequest portletRequest) {
		HttpServletRequest httpServletRequest = PortalUtil.getHttpServletRequest(portletRequest);
		HttpSession webSession = httpServletRequest.getSession();
		CrmSessionHandle siebelCrmSessionHandle = (CrmSessionHandle) webSession.getAttribute(LexmarkConstants.SIEBEL_SESSION_ID_OPS);
		return siebelCrmSessionHandle;
	}
	
	
	public static String getMdmIdOrDunsBasedOnLevel(PortletSession session){
		String ldapMdmId = null;
		Map<String, Object> ldapUserData =  (Map)session.getAttribute(LexmarkConstants.LDAP_USER_DATA, session.APPLICATION_SCOPE);
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
	
	public static String getMdmId(PortletSession session){
		ServicesUser servicesUser = getServiceUser(session);
		if (servicesUser.getMdmId()!= null && !servicesUser.getMdmId().equals("")){
			return servicesUser.getMdmId();
		}else{
			Map<String, Object> ldapUserData =  (Map)session.getAttribute(LexmarkConstants.LDAP_USER_DATA, session.APPLICATION_SCOPE);
			if(ldapUserData != null){
				return (String)ldapUserData.get(LexmarkConstants.MDMID);
			}else{
				return null;
			}
		}
	}
	
	public static String getMdmLevel(PortletSession session){
		ServicesUser servicesUser = getServiceUser(session);
		
		if(servicesUser.getMdmLevel() != null)
		{
		String mdmLevel = makeProper(servicesUser.getMdmLevel());
		
		// Commented by Arunava for MDMLEVEL Error issue
		/*if (servicesUser.getMdmLevel()!= null && !servicesUser.getMdmLevel().equals("")){
			return servicesUser.getMdmLevel(); */
		  if(mdmLevel != null && !mdmLevel.equals("")){
			  return mdmLevel ;
		   }
		}
			Map<String, Object> ldapUserData =  (Map)session.getAttribute(LexmarkConstants.LDAP_USER_DATA, session.APPLICATION_SCOPE);
			if(ldapUserData != null){
				return (String)ldapUserData.get(LexmarkConstants.MDMLEVEL);
			}else{
				return null;
			}
	}
	
	private static String makeProper(String theString){
		StringBuffer properCase = new StringBuffer();    
		try{ 
		java.io.StringReader in = new java.io.StringReader(theString.toLowerCase());
		 boolean precededBySpace = true;
		     while(true) {      
		 	int i = in.read();
		 	  if (i == -1) { break;}      
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
		}catch(Exception ex)
		{
			logger.debug("Exception "+ex.getMessage());
			return null;//This would return the input value in case of IOException
		}
		return properCase.toString();    
		 
	}
	
	//Added By MPS Offshore Team for Vendor ID pass
	public static String getVendorAccountID(PortletSession session){
		Map<String,String> accDetails=(Map<String,String>)session.getAttribute("accountCurrentDetails",PortletSession.APPLICATION_SCOPE);
		if(accDetails != null){
			return (String)accDetails.get("vendorAccountID");
		}
		else
		{
			return null;
		}
	}
	//End Add
	
	public static String getContactId(PortletSession session){
		Map<String, Object> ldapUserData =  (Map)session.getAttribute(LexmarkConstants.LDAP_USER_DATA, session.APPLICATION_SCOPE);
		if(ldapUserData != null){
			return (String)ldapUserData.get(LexmarkConstants.CONTACTID);
		}else{
			return null;
		}
	}

	public static String getChlNodeId(PortletSession session){
		ServicesUser servicesUser = getServiceUser(session);
		if (servicesUser.getChlNodeId()!= null && !servicesUser.getChlNodeId().equals("")){
			return servicesUser.getChlNodeId();
		}else{
			return null;
		}
	}
	
	public static String getFirstName(PortletSession session){
		Map<String, Object> ldapUserData =  (Map)session.getAttribute(LexmarkConstants.LDAP_USER_DATA, session.APPLICATION_SCOPE);
		if(ldapUserData != null){
			return (String)ldapUserData.get(LexmarkConstants.FIRSTNAME);
		}else{
			return null;
		}
	}
	
	public static String getLastName(PortletSession session){
		Map<String, Object> ldapUserData =  (Map)session.getAttribute(LexmarkConstants.LDAP_USER_DATA, session.APPLICATION_SCOPE);
		if(ldapUserData != null){
			return (String)ldapUserData.get(LexmarkConstants.LASTNAME);
		}else{
			return null;
		}
	}
	
	public static String getWorkPhone(PortletSession session){
		Map<String, Object> ldapUserData =  (Map)session.getAttribute(LexmarkConstants.LDAP_USER_DATA, session.APPLICATION_SCOPE);
		if(ldapUserData != null){
			return (String)ldapUserData.get(LexmarkConstants.WORKPHONE);
		}else{
			return null;
		}
	}
	
	//Added by MPS offshore
	public static String getAlternatePhone(PortletSession session){
		Map<String, Object> ldapUserData =  (Map)session.getAttribute(LexmarkConstants.LDAP_USER_DATA, session.APPLICATION_SCOPE);
		if(ldapUserData != null){
			return (String)ldapUserData.get(LexmarkConstants.ALTERNATEPHONE);
		}else{
			return null;
		}
	}
	
	
	public static String getEmailAddress(PortletSession session){
		ServicesUser servicesUser = getServiceUser(session);
		if (servicesUser.getEmailAddress()!= null && !servicesUser.getEmailAddress().equals("")){
			return servicesUser.getEmailAddress();
		}else{		
				return null;
		}
	}
	
	public static List<String> getUserRoles(PortletSession session){
		List<String> ldapUserRoles =  (List<String>)session.getAttribute(LexmarkConstants.USERROLES, session.APPLICATION_SCOPE);
		if(ldapUserRoles != null){
			return ldapUserRoles;
		}else{
			return null;
		}
	}
	
	public static String getUserSegment(PortletSession session){
		Map<String, Object> ldapUserData =  (Map)session.getAttribute(LexmarkConstants.LDAP_USER_DATA, session.APPLICATION_SCOPE);
		if(ldapUserData != null){
			return (String)ldapUserData.get(LexmarkConstants.USERSEGMENT);
		}else{
			return null;
		}
	}
	
	public static Long createSubmitToken(PortletSession session){
		Long token = new Date().getTime();
		session.setAttribute(LexmarkConstants.SUBMIT_TOKEN, token, session.PORTLET_SCOPE);
		return token;
	}
	
	public static Boolean isDuplicatedSubmit(PortletRequest request, BaseForm form){
		Boolean isDuplicated = false;
		PortletSession session = request.getPortletSession();
		Long tokenInSession = (Long)session.getAttribute(LexmarkConstants.SUBMIT_TOKEN, session.PORTLET_SCOPE);
		if(tokenInSession == null){
			isDuplicated = true;
		}else{
			isDuplicated = !tokenInSession.equals(form.getSubmitToken());
		}
		
		createSubmitToken(session);
		return isDuplicated;
	}

	public static ServicesUser getServiceUser(PortletSession session) {
		ServicesUser serviceUser = (ServicesUser) session.getAttribute(LexmarkConstants.SERVICES_USER_COM, session.APPLICATION_SCOPE);
		return serviceUser;
	}

	public static void setSiebelCrmSessionHandle(PortletRequest portletRequest, CrmSessionHandle crmSessionHandle) {
		//session.setAttribute(LexmarkConstants.SIEBEL_SESSION_ID, crmSessionHandle, session.APPLICATION_SCOPE);
		HttpServletRequest httpServletRequest = PortalUtil.getHttpServletRequest(portletRequest);
		HttpSession webSession = httpServletRequest.getSession();
		webSession.setAttribute(LexmarkConstants.SIEBEL_SESSION_ID_OPS, crmSessionHandle);
		//logger.info("UserInfoInterceptor - crmSessionHandle: " + webSession.getAttribute(LexmarkConstants.SIEBEL_SESSION_ID));

	}
	
	public static void  setServicesUser(PortletSession portletSession, ServicesUser servicesUser) {
		portletSession.setAttribute(LexmarkConstants.SERVICES_USER_COM, servicesUser, portletSession.APPLICATION_SCOPE);
	}	
	
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
		ldapUserData.put(LexmarkConstants.COMPANYNAME, ldapUserDataResult.getCompanyName());
		
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
								LexmarkConstants.COMPANYNAME    +": "+ ldapUserDataResult.getCompanyName()  + "<br>" +
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
		
		portletSession.setAttribute(LexmarkConstants.LDAP_USER_DATA, ldapUserData, portletSession.APPLICATION_SCOPE);
		portletSession.setAttribute(LexmarkConstants.USERROLES, ldapUserDataResult.getUserRoles(), portletSession.APPLICATION_SCOPE);
	}
	
	public static Boolean isAccountAssociated(PortletSession session) {
		if(session == null) {
			return false;
		}
		String employeeMdmId = (String)session.getAttribute("employeeMdmId", session.APPLICATION_SCOPE);
		String employeeMdmLevel = (String)session.getAttribute("employeeMdmLevel", session.APPLICATION_SCOPE);
		String employeeAccountName = (String)session.getAttribute("employeeAccountName", session.APPLICATION_SCOPE);

		ServicesUser serviceUser = (ServicesUser) session.getAttribute(LexmarkConstants.SERVICES_USER_COM, session.APPLICATION_SCOPE);
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
		}
		return true;
	}
	
	/**.
	 * Returns true if the user is vendor 
	 * (if a user has "Service Technician" or "Service Manager" role),
	 *  else returns false
	 * @param session PortletSession
	 * @return boolean
	 * @author Sagar Sarkar
	 */
	public boolean isVendor(PortletSession session){
		List<String> userRoleList = PortalSessionUtil.getUserRoles(session);
		if(userRoleList.contains(LexmarkConstants.ROLE_SERVICE_TECHNICIAN) ||
				userRoleList.contains(LexmarkConstants.ROLE_SERVICE_MANAGER)){
			return true;
		}
		
		return false;
	}
	
	/**.
	 * Returns user type if the user is vendor, customer or partner
	 * 
	 * @param session PortletSession
	 * @return boolean
	 * @author Sandeep Papudesi
	 */
	public static String getUserType(PortletSession session){
		String ldapUserType =(String) getUserSegment(session);
		logger.debug("in getUserType ldapUserType="+ldapUserType);
		String userType="";
		if (ldapUserType != null && ldapUserType.equalsIgnoreCase("Partner")){
			List<String> userRoleList = PortalSessionUtil.getUserRoles(session);
			if(userRoleList.contains(LexmarkConstants.ROLE_SERVICE_TECHNICIAN) ||
					userRoleList.contains(LexmarkConstants.ROLE_SERVICE_MANAGER)||
					userRoleList.contains(LexmarkConstants.ROLE_SERVICE_VIEW)||
					userRoleList.contains(LexmarkConstants.ROLE_PARTNER_ADMINISTRATOR)){
				userType="Vendor";
			}else if(userRoleList.contains(LexmarkConstants.ROLE_SERVICE_SUPPORT) ||
					userRoleList.contains(LexmarkConstants.ROLE_STANDARD_ACCESS) ||
					userRoleList.contains(LexmarkConstants.ROLE_KEY_OPERATOR) ||//Added for new roles for MPS
					userRoleList.contains(LexmarkConstants.ROLE_SERVICE_VIEW) ||//Added for new roles for MPS
					userRoleList.contains(LexmarkConstants.ROLE_ACCOUNT_MANAGEMENT)||
					userRoleList.contains(LexmarkConstants.ROLE_PROJECT_MANAGEMENT)||
					userRoleList.contains(LexmarkConstants.ROLE_BILLING) ||
					userRoleList.contains(LexmarkConstants.ROLE_PURCHASING)){
				userType="Customer";
			}
		}else if (ldapUserType.equalsIgnoreCase("employee")){
			userType="Employee";
		}
		return userType;
	}
	
	// added for BRD 14-07-04
	public static List<String> getPartnerTypes(PortletSession session) throws Exception{
		logger.debug("IN getPartnerTypes of Portal Session Util");
		List<String> partnerTypeList = new ArrayList<String>();
		//List<String> ldapUserRoles =  (List<String>)session.getAttribute(LexmarkConstants.USERROLES, session.APPLICATION_SCOPE);
		List<String> account_partnerTypeList = (List<String>)session.getAttribute(LexmarkConstants.SIEBEL_PARTNER_TYPES,PortletSession.APPLICATION_SCOPE);
		logger.debug("Country List from Session-------------->>"+account_partnerTypeList);
		//Iterator it= partnerTypeList.iterator();
		if(account_partnerTypeList==null){
			return partnerTypeList;
		}
		for(int i=0;i<account_partnerTypeList.size();i++){
			logger.debug("inside for loop of account partnerTypes--------"+account_partnerTypeList.get(i));
			partnerTypeList.add(account_partnerTypeList.get(i));
		}
			if(partnerTypeList != null){
				return partnerTypeList;
			}else{
				return null;
			}
		
		}
	
		
	public static List<String> getCountries(PortletSession session) throws Exception{
		logger.debug("IN getCountries of Portal Session Util");
		List<String> countriesList =  new ArrayList<String>();

		    List<String> account_countriesList = (List<String>)session.getAttribute(LexmarkConstants.SIEBEL_PARTNER_COUNTRIES,PortletSession.APPLICATION_SCOPE);
		    logger.debug("Country List from Session-------------->>"+account_countriesList);
		    if(account_countriesList==null){
		    	return countriesList;
		    }
			for(int i=0;i<account_countriesList.size();i++){
				logger.debug("inside for loop of account countries--------"+account_countriesList.get(i));
				countriesList.add(account_countriesList.get(i));
			}
			


			if(countriesList != null){
				return countriesList;
			}else{
				return null;
			}

	}
	/*Added for 12054 */
	
	public static boolean isUserRoleStandardAccess(PortletSession session){
		
		for(String userRoles : getUserRoles(session)){
			
			if(userRoles.equalsIgnoreCase("Standard Access")){
				return true;
			}
		}
		
		return false;
	}
	//Added for OPS
	public static void setOPSAccountSelected(PortletSession session){
		session.setAttribute(LexmarkConstants.OPS_ACCOUNT_SELECTED, new Boolean(true), PortletSession.APPLICATION_SCOPE);
	}
	public static Boolean getOPSAccountSelected(PortletSession session){
		return (Boolean)session.getAttribute(LexmarkConstants.OPS_ACCOUNT_SELECTED, PortletSession.APPLICATION_SCOPE);
	}
	
}
