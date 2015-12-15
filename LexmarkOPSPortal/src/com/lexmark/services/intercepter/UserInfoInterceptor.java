package com.lexmark.services.intercepter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.PortletSession;
import javax.portlet.RenderResponse;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.portlet.handler.HandlerInterceptorAdapter;

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.contract.DunsNumberContract;
import com.lexmark.contract.FSEAccountListContract;
import com.lexmark.contract.LdapUserDataContract;
import com.lexmark.contract.ServicesUserContract;
import com.lexmark.contract.SiebelAccountListContract;
import com.lexmark.domain.Account;
import com.lexmark.domain.ServicesUser;
import com.lexmark.result.FSEAccountListResult;
import com.lexmark.result.LdapUserDataResult;
import com.lexmark.result.SiebelAccountListResult;
import com.lexmark.service.api.CrmSessionHandle;
import com.lexmark.service.api.GlobalService;
import com.lexmark.service.api.ServiceRequestService;
import com.lexmark.service.api.SiebelCrmServiceException;
import com.lexmark.service.api.UserService;
import com.lexmark.services.LexmarkSPConstants;
import com.lexmark.services.util.ContractFactory;
import com.lexmark.services.util.LexmarkUserUtil;
import com.lexmark.services.util.PortalSessionUtil;
import com.lexmark.services.util.ServiceStatusErrorMessageUtil;
//import com.lexmark.util.PerformanceTracker;
import com.lexmark.util.StringUtil;
//Added for MPS 2.1
import com.lexmark.domain.UserDetails;

public class UserInfoInterceptor extends HandlerInterceptorAdapter {
	private static Logger logger = LogManager.getLogger(UserInfoInterceptor.class);
	
	private UserService userService;
    @Autowired
    private GlobalService  globalService;
    @Autowired
	private ServiceRequestService serviceRequestService;
    
	/**
	 * Default callback that both <code>preHandleRender
	 * and <code>preHandleAction delegate to.
	 * <p>This implementation always returns true.
	 * @see #preHandleRender
	 * @see #preHandleAction
	 */
	protected boolean preHandle(PortletRequest request, PortletResponse response, Object handler)
			throws Exception {
		if(response instanceof RenderResponse) {
			((RenderResponse)response).setContentType("text/html; charset=UTF-8");
		}
		handleUserInforToSession(request);
		return true;
	}
	
	/**
	 *  retrieve Services User and Ldap User Data information and stores into Session.
	 * @param request
	 * @throws Exception
	 */
	private void handleUserInforToSession(PortletRequest portletRequest)throws Exception{
		try{
			
			//Added in MPS2.1 to set user roles to session
			logger.debug("################## IN OPS PORTAL PRINTING Role in UserInfoInterceptor STARTS ##########################");		
			//com.lexmark.domain.UserDetails userDetails = (com.lexmark.domain.UserDetails)portletRequest.getPortletSession().getAttribute("USERDETAILSCOM", javax.portlet.PortletSession.APPLICATION_SCOPE);
/*			logger.debug("roleList.isAccMgmtRqstShow in UserInfoInterceptor======== "+userDetails.isShowAccMgmtReq());
			logger.debug("roleList.isAccMgmtRqstUpdate in UserInfoInterceptor======== "+userDetails.isCreateAccMgmtReq());
			logger.debug("roleList.isCreateServiceReq in UserInfoInterceptor======== "+userDetails.isCreateServiceReq());
			logger.debug("roleList.isUpdateConsumableReq in UserInfoInterceptor======== "+userDetails.isUpdateConsumableReq());	
			logger.debug("roleList.isUpdatePageCount in UserInfoInterceptor======== "+userDetails.isUpdatePageCount());	*/
			logger.debug("################## PRINTING Role in UserInfoInterceptor ENDS ##########################");
			
			Boolean flag=(Boolean)portletRequest.getPortletSession().getAttribute("isViewAllCustomerOrderFlag", javax.portlet.PortletSession.APPLICATION_SCOPE);
			logger.debug("FLAG----->>"+flag);
			portletRequest.getPortletSession().setAttribute("isViewAllCustomerOrderFlag", flag, javax.portlet.PortletSession.APPLICATION_SCOPE);
			PortletSession portletSession =portletRequest.getPortletSession();
			
			//portletSession.setAttribute("LOGINUSERDETAILS", userDetails ,PortletSession.APPLICATION_SCOPE); //Added in MPS2.1 to set user roles to session
			
			if(portletSession.getAttribute(LexmarkConstants.SERVICES_USER_COM, portletSession.APPLICATION_SCOPE )!= null) {
				return;
			}
			
			logger.debug("*********************************************************************"); 
			logger.debug("********* PORTLET_BUILD_VERSION = " + 
					 LexmarkConstants.PORTLET_BUILD_VERSION + " ***************");
			logger.debug("*********************************************************************");
			
			String emailAddress = LexmarkUserUtil.getUserEmailAddress(portletRequest);
			
			logger.debug("********* Portlet Login Email: "+emailAddress);
			
			LdapUserDataContract ldapUserDataContract = ContractFactory.getLdapUserDataContract(emailAddress);
			
			LdapUserDataResult ldapUserDataResult = userService.retrieveLdapUserData(ldapUserDataContract);
			
			/*********Added for Level 5 user*****************/
			
			getLevel5UserDetails(ldapUserDataResult, portletRequest);
			
			
			ServicesUserContract servicesUserContract = ContractFactory.getServicesUserContract(
					ldapUserDataResult.getUserNumber(), emailAddress);
			ServicesUser servicesUser = userService.retrieveServicesUser(servicesUserContract).getServicesUser();
			// Throw exception, if can not get user information by user number. The most possibility is the LDAP configured 
			// in the context.xml file for user service DOES NOT match the LDAP which Liferay is connection to authenticate 
			
			if(servicesUser == null) {
				String[] userNumberObject = {ldapUserDataResult.getUserNumber()};
				throw new RuntimeException(ServiceStatusErrorMessageUtil.getErrorMessage(LexmarkSPConstants.ERROR_MESSAGE_BUNDLE, "exception.interceptor.servicesUserException", userNumberObject, portletRequest.getLocale()));
				
			}
			CrmSessionHandle crmSessionHandle = globalService.initCrmSessionHandle(null);        
			if (servicesUser.getMdmLevel() == null || servicesUser.getMdmLevel().equals("")){
				if (ldapUserDataResult.getMdmLevel().equalsIgnoreCase(LexmarkConstants.GLOBAL) || ldapUserDataResult.getMdmLevel().equalsIgnoreCase(LexmarkConstants.DOMESTIC)){
					DunsNumberContract dunsNumberContract = new DunsNumberContract();
					dunsNumberContract.setMdmId(ldapUserDataResult.getMdmId());
					dunsNumberContract.setMdmLevel(ldapUserDataResult.getMdmLevel());
					//DunsNumberResult dunsNumberResult = globalService.retrieveDunsNumber(dunsNumberContract);//From MPS release IDM will have DUNS number for these two levels
					servicesUser.setMdmId(ldapUserDataResult.getMdmId());
					servicesUser.setMdmLevel(ldapUserDataResult.getMdmLevel());
					logger.debug("*********MDM ID - LDAP: "+ldapUserDataResult.getMdmId());
					logger.debug("*********MDM ID - DUNS: "+servicesUser.getMdmId());
				}				
			}
	
			PortalSessionUtil.setServicesUser(portletSession, servicesUser);
			PortalSessionUtil.setLdapUserData(portletSession, ldapUserDataResult);
			
			PortalSessionUtil.setSiebelCrmSessionHandle(portletRequest, crmSessionHandle);
			
			//Added this for the OBIEE Chart Portlet and preselecting the account for FSE
			//Without this the load order of the page could cause the OBIEE Chart portlet to not know that the account was preselected
			String userSegment = ldapUserDataResult.getUserSegment();
			if (userSegment.equalsIgnoreCase(LexmarkConstants.USER_SEGMENT_EMPLOYEE) && hasTechnicianRole(ldapUserDataResult)) {
				FSEAccountListContract contract = new FSEAccountListContract();
				contract.setSiebelEmployeeId(ldapUserDataResult.getContactId());
				FSEAccountListResult fSEAccountListResult = globalService.retrieveFSEAccountList(contract);
				if (fSEAccountListResult != null && 
						fSEAccountListResult.getAccountList() != null && 
						fSEAccountListResult.getAccountList().size() == 1){
					Account fseAccount = fSEAccountListResult.getAccountList().get(0);
					servicesUser.setMdmId(fseAccount.getAccountId());
					servicesUser.setMdmLevel(LexmarkConstants.MDM_LEVEL_SIEBEL);
					servicesUser.setAccountName(fseAccount.getAccountName() + " (" + fseAccount.getAccountId() + ")");

					portletSession.setAttribute("employeeMdmId", fseAccount.getAccountId(), portletSession.APPLICATION_SCOPE);
					portletSession.setAttribute("employeeMdmLevel", LexmarkConstants.MDM_LEVEL_SIEBEL, portletSession.APPLICATION_SCOPE);
					portletSession.setAttribute("employeeAccountName", fseAccount.getAccountName() + " (" + fseAccount.getAccountId() + ")", portletSession.APPLICATION_SCOPE);
					//Must set for employee - used by OBIEE
					portletSession.setAttribute("obieeMdmId", fseAccount.getAccountId(), portletSession.APPLICATION_SCOPE);
				}
			}
			if(logger.isDebugEnabled()) {
				servicesUser = (ServicesUser)portletSession.getAttribute(LexmarkConstants.SERVICES_USER_COM, portletSession.APPLICATION_SCOPE);
				logger.debug("9");
				//logger.debug("*********User Number:"+servicesUser.getUserNumber());
				//logger.debug("10");
				//logger.debug("*********Email Address:"+servicesUser.getEmailAddress());
				//logger.debug("*********Siebel Contact Id:"+ ldapUserDataResult.getContactId());
				//logger.debug("*********LDAPUserData"+portletSession.getAttribute(LexmarkConstants.LDAP_USER_DATA, portletSession.APPLICATION_SCOPE));
			}
		}catch(SiebelCrmServiceException siebelException){
			throw new Exception(ServiceStatusErrorMessageUtil.getErrorMessage(LexmarkSPConstants.ERROR_MESSAGE_BUNDLE, "exception.siebel.crmServiceException", null, portletRequest.getLocale()));
		}

	}

	/*
	 * This method is for checking level 5 users and in turn set the values in the map
	 */
	private void getLevel5UserDetails(LdapUserDataResult ldapUserDataResult, PortletRequest request) {
		
		final String mdmLevelOfTheUsr=ldapUserDataResult.getMdmLevel();
		
		logger.debug("mdmLevelOfTheUsr="+mdmLevelOfTheUsr);
		
		Map<String, String> userDetailsMap=new HashMap<String, String>();
		
		if(!StringUtil.isEmpty(mdmLevelOfTheUsr))
		{
			if(mdmLevelOfTheUsr.trim().equalsIgnoreCase("Account"))
			{
				PortletSession portletSession = request.getPortletSession();
				SiebelAccountListContract siebelAccountListContract = ContractFactory
																	  .getSiebelAccountListContract(request);
				CrmSessionHandle crmSessionHandle = globalService
													.initCrmSessionHandle(PortalSessionUtil
													.getSiebelCrmSessionHandle(request));
				siebelAccountListContract.setSessionHandle(crmSessionHandle);
				logger.debug("-------------retrieveSiebelAccountList started---------");
				/*LexmarkTransaction lexmarkTran = PerformanceTracker.startTracking(LexmarkConstants.TARGET_SYSTEM_SIEBEL,
						"retrieveSiebelAccountList", PortalSessionUtil
						.getMdmId(portletSession), PortalSessionUtil.getServiceUser(
								portletSession).getEmailAddress());*/
				SiebelAccountListResult siebelAccountListResult = serviceRequestService
																  .retrieveSiebelAccountList(siebelAccountListContract);
				//PerformanceTracker.endTracking(lexmarkTran);
				
				List<Account> accountList = siebelAccountListResult.getAccountList();
				
				logger.debug("acount list size is " + accountList.size());
				
				for(Account account:accountList)
				{
					userDetailsMap.put("accountId", account.getAccountId());
					userDetailsMap.put("accountName", account.getAccountName());
					userDetailsMap.put("agreementId", account.getAccountName());
					userDetailsMap.put("agreementName", account.getAccountName());
					userDetailsMap.put("country", account.getCountryCode());
				
					logger.debug("accountId in loop="+userDetailsMap.get("accountId"));
				}
				
				logger.debug("accountId from map="+userDetailsMap.get("accountId"));
				
				portletSession.setAttribute("accountCurrentDetails", userDetailsMap ,PortletSession.APPLICATION_SCOPE);
			}
			
		}
		
		
		
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	private boolean hasTechnicianRole(LdapUserDataResult ldapUserDataResult) {
		if(ldapUserDataResult == null || ldapUserDataResult.getUserRoles() == null) {
			return false;
		}
		for(String role :  ldapUserDataResult.getUserRoles()) {
			if(LexmarkConstants.ROLE_SERVICE_TECHNICIAN.equalsIgnoreCase(role)) {
				return true;
			}
		}
		return false;
	}

}
