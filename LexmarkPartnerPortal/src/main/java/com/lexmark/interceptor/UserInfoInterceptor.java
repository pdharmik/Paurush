package com.lexmark.interceptor;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.portlet.PortletSession;
import javax.portlet.RenderResponse;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.web.portlet.handler.HandlerInterceptorAdapter;

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.constants.LexmarkPPConstants;
import com.lexmark.contract.DunsNumberContract;
import com.lexmark.contract.FSEAccountListContract;
import com.lexmark.contract.LdapUserDataContract;
import com.lexmark.contract.PartnerAccountListContract;
import com.lexmark.contract.ServicesUserContract;
import com.lexmark.domain.Account;
import com.lexmark.domain.ServicesUser;
import com.lexmark.result.DunsNumberResult;
import com.lexmark.result.FSEAccountListResult;
import com.lexmark.result.LdapUserDataResult;
import com.lexmark.result.PartnerAccountListResult;
import com.lexmark.service.api.GlobalService;
import com.lexmark.service.api.SiebelCrmServiceException;
import com.lexmark.service.api.UserService;
import com.lexmark.util.ContractFactory;
import com.lexmark.util.ErrorMessageUtil;
import com.lexmark.util.ExceptionUtil;
import com.lexmark.util.LexmarkUserUtil;
import com.lexmark.util.PortalSessionUtil;
import com.lexmark.util.StringUtil;

public class UserInfoInterceptor extends HandlerInterceptorAdapter {
	private static Logger logger = LogManager.getLogger(UserInfoInterceptor.class);
	
	private UserService userService;
    private GlobalService  globalService;
	
    public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public GlobalService getGlobalService() {
		return globalService;
	}

	public void setGlobalService(GlobalService globalService) {
		this.globalService = globalService;
	}
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
		boolean fseAddressLink = false;	
		try{			
			PortletSession portletSession =portletRequest.getPortletSession();
			if(portletSession.getAttribute(LexmarkConstants.SERVICES_USER_PHASE2, portletSession.APPLICATION_SCOPE )!= null) {
				return;
			}
			
			logger.debug("*********************************************************************"); 
			logger.debug("********* PORTLET_BUILD_VERSION = " + 
					 LexmarkPPConstants.PORTLET_BUILD_VERSION + " ***************");
			logger.debug("*********************************************************************");
			
			String emailAddress = getUserEmail(portletRequest); 
			
			logger.debug("********* Portlet Login Email: "+emailAddress);
			
			LdapUserDataContract ldapUserDataContract = ContractFactory.getLdapUserDataContract(emailAddress);
			
			LdapUserDataResult ldapUserDataResult = userService.retrieveLdapUserData(ldapUserDataContract);
			ServicesUserContract servicesUserContract = ContractFactory.getServicesUserContract(
					ldapUserDataResult.getUserNumber(), emailAddress);
			ServicesUser servicesUser = userService.retrieveServicesUser(servicesUserContract).getServicesUser();

			// Throw exception, if can not get user information by user number. The most possibility is the 
			//  LDAP configured in the context.xml file for user service DOES NOT match the LDAP which Liferay
			// is connection to authenticate 
			if(servicesUser == null) {
				String[] userNumberObject = {ldapUserDataResult.getUserNumber()};
				throw new RuntimeException(
						ErrorMessageUtil.getErrorMessage(
								LexmarkPPConstants.ERROR_MESSAGE_BUNDLE, 
								"exception.interceptor.servicesUserException", 
								userNumberObject, 
								portletRequest.getLocale()
								)
						);
			}
			
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

			String userSegment = ldapUserDataResult.getUserSegment();
			if (StringUtil.isStringEmpty(userSegment) || !userSegment.equalsIgnoreCase(LexmarkConstants.USER_SEGMENT_EMPLOYEE)) {
				PartnerAccountListContract contract = new PartnerAccountListContract();
				if (ldapUserDataResult.getMdmLevel().equalsIgnoreCase(LexmarkConstants.GLOBAL) || ldapUserDataResult.getMdmLevel().equalsIgnoreCase(LexmarkConstants.DOMESTIC)){
					contract.setMdmId(servicesUser.getMdmId());					
					contract.setMdmLevel(servicesUser.getMdmLevel());
				}else{
					if (servicesUser.getMdmLevel() == null || servicesUser.getMdmLevel().equals("")){
						contract.setMdmId(ldapUserDataResult.getMdmId());											
						contract.setMdmLevel(ldapUserDataResult.getMdmLevel());
					}else{
						contract.setMdmId(servicesUser.getMdmId());					
						contract.setMdmLevel(servicesUser.getMdmLevel());
					}
				}
				PartnerAccountListResult partnerAccountListResult = null;
				try {
					partnerAccountListResult = globalService.retrievePartnerAccountList(contract);
				} catch (Exception e) {
					String errorMessage = ExceptionUtil.getLocalizedExceptionMessage("exception.system.error2",
							portletRequest.getLocale(), e);
					throw new Exception(errorMessage);
				}
				LexmarkUserUtil.setUserFlagsInSession(portletSession, partnerAccountListResult.getAccountList());
				PortalSessionUtil.setLexmarkEmployeeFlag(portletSession, false);
                servicesUser.setAccounts(partnerAccountListResult.getAccountList());
			} else if (userSegment.equalsIgnoreCase(LexmarkConstants.USER_SEGMENT_EMPLOYEE)) {
				PortalSessionUtil.setLexmarkEmployeeFlag(portletSession, true);
				if(hasTechnicianRole(ldapUserDataResult)) {
					fseAddressLink = true;
					PortalSessionUtil.setLexmarkFSEFlag(portletSession, true);
					portletSession.setAttribute("FSE_ADDRESS_LINK",fseAddressLink,portletSession.APPLICATION_SCOPE);
					FSEAccountListContract contract = new FSEAccountListContract();
					contract.setSiebelEmployeeId(ldapUserDataResult.getContactId());
					FSEAccountListResult fSEAccountListResult = globalService.retrieveFSEAccountList(contract);
					//Commented out because this will be handled in the Employee Account Selector after account is chosen
					//LexmarkUserUtil.setUserFlagsInSession(portletSession, fSEAccountListResult.getAccountList());
					boolean createchildSR = false;
					boolean shipToDefault = false;
					if (fSEAccountListResult != null && 
							fSEAccountListResult.getAccountList() != null && 
							fSEAccountListResult.getAccountList().size() == 1){
						Account fseAccount = fSEAccountListResult.getAccountList().get(0);
						if(fseAccount.isCreateChildSR())
						{
						createchildSR=true;
						}
						if(("Partner To provide").equalsIgnoreCase(fseAccount.getShipToDefault()))
						{
							shipToDefault = true;	
						}
						servicesUser.setMdmId(fseAccount.getAccountId());
						servicesUser.setMdmLevel(LexmarkConstants.MDM_LEVEL_SIEBEL);
						servicesUser.setAccountName(fseAccount.getAccountName() + " (" + fseAccount.getAccountId() + ")");

						portletSession.setAttribute("employeeMdmId", fseAccount.getAccountId(), portletSession.APPLICATION_SCOPE);
						portletSession.setAttribute("employeeMdmLevel", LexmarkConstants.MDM_LEVEL_SIEBEL, portletSession.APPLICATION_SCOPE);
						portletSession.setAttribute("IS_CREATE_CHILD_SR", createchildSR,portletSession.APPLICATION_SCOPE);
						portletSession.setAttribute("SHIP_TO_DEFAULT", shipToDefault,portletSession.APPLICATION_SCOPE);
						portletSession.setAttribute("employeeAccountName", fseAccount.getAccountName() + " (" + fseAccount.getAccountId() + ")", portletSession.APPLICATION_SCOPE);
						//Must set for employee - used by OBIEE
						portletSession.setAttribute("obieeMdmId", fseAccount.getAccountId(), portletSession.APPLICATION_SCOPE);
						LexmarkUserUtil.setUserFlagsInSession(portletSession, fSEAccountListResult.getAccountList());
						
					}
                    servicesUser.setAccounts(fSEAccountListResult.getAccountList());
				} else {
					PortalSessionUtil.setLexmarkFSEFlag(portletSession, false);
					portletSession.setAttribute("FSE_ADDRESS_LINK",fseAddressLink,portletSession.APPLICATION_SCOPE);
                    servicesUser.setAllAccount(true);
				}
			}
			
			if(logger.isDebugEnabled()) {
				servicesUser = (ServicesUser)portletSession.getAttribute(
						LexmarkConstants.SERVICES_USER_PHASE2, portletSession.APPLICATION_SCOPE);
				logger.debug("*********User Number:"+servicesUser.getUserNumber());
				logger.debug("*********Email Address:"+servicesUser.getEmailAddress());
				logger.debug("*********Siebel Contact Id:"+ ldapUserDataResult.getContactId());
				logger.debug("*********LDAPUserData"+portletSession.getAttribute(
						LexmarkConstants.LDAP_USER_DATA_PHASE2, portletSession.APPLICATION_SCOPE));
			}
		}catch(SiebelCrmServiceException siebelException){
			siebelException.printStackTrace();
			throw new Exception(ErrorMessageUtil.getErrorMessage(
					LexmarkPPConstants.ERROR_MESSAGE_BUNDLE, 
					"exception.siebel.crmServiceException", 
					null, 
					portletRequest.getLocale()));
		}
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
	
	
	
	// In order to unit test,  by pass the  LexmarkUserUtil.getUserEmailAddress, which is static method and difficult to test.
	protected String getUserEmail(PortletRequest portletRequest) throws Exception {
		return LexmarkUserUtil.getUserEmailAddress(portletRequest);
	}
}
