package com.lexmark.services.portlet;

import java.io.PrintWriter;
import java.net.SocketTimeoutException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.contract.ProcessCustomerContactContract;
import com.lexmark.contract.SiebelAccountListContract;
import com.lexmark.domain.Account;
import com.lexmark.domain.AccountAccess;
import com.lexmark.domain.AccountContact;
import com.lexmark.domain.GlobalAccount;
import com.lexmark.domain.LexmarkTransaction;
import com.lexmark.domain.ServicesUser;
import com.lexmark.domain.UserDetails;
import com.lexmark.result.ProcessCustomerContactResult;
import com.lexmark.result.SiebelAccountListResult;
import com.lexmark.service.api.GlobalLegalEntityService;
import com.lexmark.service.api.GlobalService;
import com.lexmark.service.api.ServiceRequestService;
import com.lexmark.services.api.ProcessCustomerContact;
import com.lexmark.services.form.EmployeeAccountSelectionForm;
import com.lexmark.services.hook.LBSAccess;
import com.lexmark.services.hook.RequestCreateFlags;
import com.lexmark.services.hook.SiebelRoleImportAction;
import com.lexmark.services.util.AccountUtil;
import com.lexmark.services.util.ChangeMgmtConstant;
import com.lexmark.services.util.LexmarkUserUtil;
import com.lexmark.services.util.ObjectDebugUtil;
import com.lexmark.services.util.PortalSessionUtil;
import com.lexmark.services.util.XMLEncodeUtil;
import com.lexmark.util.CollectionFilter;
import com.lexmark.util.PerformanceTracker;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;

@Controller
@RequestMapping("VIEW")
public class EmployeeAccountSelectionController extends BaseController {
	
	private static Logger logger = LogManager.getLogger(EmployeeAccountSelectionController.class);
	private static final String COMBO_INPUT_MASK = "mask";
	@Autowired
	private GlobalService globalService;	
	@Autowired
	private ProcessCustomerContact processCustomerContract;
    @Autowired
    private GlobalLegalEntityService globalLegalEntityService;
	@Autowired
	private ServiceRequestService serviceRequestService;

	/**
	 * @param model 
	 * @param request 
	 * @param response 
	 * @return String 
	 * @throws Exception 
	 */
	@RequestMapping
	public String showEmployeeAccountSelectionPage(Model model, RenderRequest request, RenderResponse response) throws Exception{
		PortletSession portletSession =request.getPortletSession();
		Cookie cookies[]=request.getCookies();
		int cookieLen = cookies.length;
		for(int c = 0; c <cookieLen;c++)
		{
		
			logger.info("cookie"+((Cookie)cookies[c]).getName()+"----val-----"+((Cookie)cookies[c]).getValue());
			//((Cookie)cookies[c]).setMaxAge(0);
		}
			
		boolean siebelIdAvailable = true;
		ServicesUser servicesUser = PortalSessionUtil.getServiceUser(portletSession);
		if(servicesUser == null) {
			servicesUser = new ServicesUser();
		}
		
		String employeeMdmId = (String)portletSession.getAttribute("employeeMdmId", portletSession.APPLICATION_SCOPE);
		String employeeMdmLevel = (String)portletSession.getAttribute("employeeMdmLevel", portletSession.APPLICATION_SCOPE);
		String employeeAccountName = (String)portletSession.getAttribute("employeeAccountName", portletSession.APPLICATION_SCOPE);

		if(servicesUser.getMdmId() == null || servicesUser.getMdmId().equals("") ||
				servicesUser.getMdmLevel() == null || servicesUser.getMdmLevel().equals("")) {
			if (employeeMdmId == null || employeeMdmId.equals("") ||
					employeeMdmLevel == null || employeeMdmLevel.equals("")){
				//do nothing				
			}else{
				servicesUser.setMdmId(employeeMdmId);
				servicesUser.setMdmLevel(employeeMdmLevel);
				servicesUser.setAccountName(employeeAccountName);
				PortalSessionUtil.setServicesUser(portletSession, servicesUser);

			}
		}else{
			if (employeeMdmId == null || employeeMdmId.equals("") ||
					employeeMdmLevel == null || employeeMdmLevel.equals("")){
				//do nothing				
			}else{
				if(!servicesUser.getMdmId().equals(employeeMdmId) || !servicesUser.getMdmLevel().equals(employeeMdmLevel) ) {
					servicesUser.setMdmId(employeeMdmId);
					servicesUser.setMdmLevel(employeeMdmLevel);
					servicesUser.setAccountName(employeeAccountName);
					PortalSessionUtil.setServicesUser(portletSession, servicesUser);

				}			 							
			}

		}
		model.addAttribute("selectedAccount",  servicesUser.getAccountName());
		//Here I need to check if siebel contact id is available
		//boolean contactIdSession = Boolean.parseBoolean((String)request.getPortletSession().getAttribute("siebelIdAvailable", portletSession.APPLICATION_SCOPE));
		logger.debug("contactIdSession value from request "+(String)request.getPortletSession().getAttribute("siebelIdAvailable", portletSession.APPLICATION_SCOPE));
		if ((String)request.getPortletSession().getAttribute("siebelIdAvailable", portletSession.APPLICATION_SCOPE)!=null){
			logger.debug("value available from the session");
			siebelIdAvailable = true;
		}else{
		String contactId = PortalSessionUtil.getContactId(request.getPortletSession());
		logger.debug("Sieble contact id is "+contactId);
		if("".equals(contactId)){
			siebelIdAvailable = false;
			logger.debug("Siebel contact id is not available");//so call the webservice asynchronously
		}
		}
		model.addAttribute("siebelIdAvailable",siebelIdAvailable);
		//If available go as it is otherwise call some asynchronous method and call the webservice
		
		return "employeeAccountSelection";
	}
	
	/**
	 * @param request 
	 * @param response 
	 * @throws Exception 
	 */
	@ResourceMapping
	public void retrieveGlobalLegalEntityList(ResourceRequest request, ResourceResponse response) throws Exception {
		logger.debug("------------- Step 1---retrieveGlobalAccountList started---------["+System.nanoTime()+"]");
		
		String requireServiceCall = (String)request.getParameter("requireLdapUpdate");
		String siebelContactId = "";
		logger.debug("!!!!!!!!!!!!!!!!!!!! requireServiceCall value is "+requireServiceCall);
		if("require".equals(requireServiceCall)){
			logger.debug("!!!!!!!!!!!!!!!!!!!!!!!!! need to update ldap");//that is call the webservice
			PortletSession portletSession =request.getPortletSession();
			Map<String, String> ldapUserData = (Map<String, String>) portletSession.getAttribute(LexmarkConstants.LDAP_USER_DATA, portletSession.APPLICATION_SCOPE);
			ProcessCustomerContactContract contract = new ProcessCustomerContactContract();
			AccountContact contact = new AccountContact();
			contact.setCountry(ldapUserData.get(LexmarkConstants.COUNTRY));
			contact.setContactId(ldapUserData.get(LexmarkConstants.USERNUMBER));
			contact.setFirstName(ldapUserData.get(LexmarkConstants.FIRSTNAME));
			contact.setLastName(ldapUserData.get(LexmarkConstants.LASTNAME));
			contact.setEmailAddress(ldapUserData.get(LexmarkConstants.EMAIL));
			contact.setWorkPhone(ldapUserData.get(LexmarkConstants.WORKPHONE));
			contact.setShortId(ldapUserData.get(LexmarkConstants.SHORTID));
			contract.setEmployeeContact(contact);
			logger.debug("*********LDAPUserData"+portletSession.getAttribute(LexmarkConstants.LDAP_USER_DATA, portletSession.APPLICATION_SCOPE));
			ProcessCustomerContactResult result;
			try {
				result = processCustomerContract.processCustomerContact(contract);
				siebelContactId = result.getSiebelContactId();
			} catch (SocketTimeoutException socketTimeoutException) {
				// TODO Auto-generated catch block
				logger.debug("Socket Timeout Exception");
				PrintWriter out = response.getWriter();
				response.setContentType("text/html");
				out.print("socketTimeoutException");
				out.flush();
				out.close();
				return;
			} catch (Exception e){
				logger.debug("Exception other than Socket Timeout exception");				
				PrintWriter out = response.getWriter();
				response.setContentType("text/html");
				out.print("exception");
				out.flush();
				out.close();
				return;
			}
			
			PortletSession session =request.getPortletSession();
			//Map<String, Object> ldapUserData =  (Map)session.getAttribute(LexmarkConstants.LDAP_USER_DATA, session.APPLICATION_SCOPE);
			ldapUserData.put(LexmarkConstants.CONTACTID, siebelContactId);
			session.setAttribute(LexmarkConstants.LDAP_USER_DATA, ldapUserData, session.APPLICATION_SCOPE);
			//PortalSessionUtil.setLdapUserData(portletSession, ldapUserDataResult);
			logger.debug("*********LDAPUserData"+portletSession.getAttribute(LexmarkConstants.LDAP_USER_DATA, portletSession.APPLICATION_SCOPE));
			logger.debug("Updated siebel contact id is "+siebelContactId);
			
			}else{
			
				logger.debug("!!!!!!!!!!!!!!!!!!!!!!!!! normal ajax call");
				PortletSession session =request.getPortletSession();
				logger.debug("*********LDAPUserData"+session.getAttribute(LexmarkConstants.LDAP_USER_DATA, session.APPLICATION_SCOPE));
				String inputMask = request.getParameter(COMBO_INPUT_MASK);
				LexmarkTransaction lexmarkTran = PerformanceTracker.startTracking(LexmarkConstants.TARGET_SYSTEM_SIEBEL, "retrieveGlobalLegalEntityList", 
						PortalSessionUtil.getMdmId(session), PortalSessionUtil.getServiceUser(session).getEmailAddress());
				List<GlobalAccount> entityList = globalLegalEntityService.getGlobalAccounts();
				PerformanceTracker.endTracking(lexmarkTran);
				if(inputMask != null && !inputMask.equals("")) {
					CollectionFilter filter = new CollectionFilter(Locale.getDefault());
					 HashMap noFuzzyFilter = new HashMap<String, Object>();
					 noFuzzyFilter.put("legalName", inputMask);
				//	 entityList = filter.filter(entityList, noFuzzyFilter, false); changed for LEX:AIR00059709 
					 entityList = filter.filterAccounts(entityList, noFuzzyFilter, false);
				}
				PrintWriter out = response.getWriter();
				response.setContentType("text/xml");
				out.print(getXmlContent(entityList));
				out.flush();
				out.close();
			}
		logger.debug("------------- Step 1---retrieveGlobalAccountList end---------["+System.nanoTime()+"]");
	}
	
	/*private String getXmlContent(List<GlobalAccount> result) {
		StringBuilder sb = new StringBuilder();
		sb.append("<complete>");
		for(GlobalAccount le : result) {
			sb.append("<option value=\""+ 	XMLEncodeUtil.escapeXML(le.getLegalName())  + "\">");
			sb.append("<![CDATA[");
 			sb.append(le.getLegalName() + " (" + le.getDisplayMdmId() + ")");
			sb.append("]]>");
			sb.append("</option>");
		}
		sb.append("</complete>");
		return sb.toString();
	}*/
	
	/**
	 * @param result 
	 * @return String 
	 */
	private String getXmlContent(List<GlobalAccount> result) {
		StringBuilder sb = new StringBuilder();
		sb.append("<complete>");
		for(GlobalAccount le : result) {
			sb.append("<option value=\""+ 	XMLEncodeUtil.escapeXML(le.getLegalName() + " (" + le.getDisplayMdmId() + ")")  + "\">");
			sb.append("<![CDATA[");
 			sb.append(le.getLegalName() + " (" + le.getDisplayMdmId() + ")");
			sb.append("]]>");
			sb.append("</option>");
	}
		sb.append("</complete>");
		return sb.toString();
	}
	
	/**
	 * @param request 
	 * @param response 
	 * @param employeeAccountSelectionForm 
	 * @param result 
	 * @param model 
	 * @throws Exception 
	 */
	@RequestMapping (params = "action=submitEmployeeAccountSelection")
	public void submitEmployeeAccountSelection(ActionRequest request, ActionResponse response,
			@ModelAttribute("employeeAccountSelectionForm") EmployeeAccountSelectionForm
			employeeAccountSelectionForm, BindingResult result,
			Model model) throws Exception {		
		logger.debug("------------- Step 1---submitEmployeeAccountSelection started---------["+System.nanoTime()+"]");
		
		String legalName = employeeAccountSelectionForm.getLegalName();
		boolean alliancePartner = false;
		PortletSession portletSession =request.getPortletSession();
		
	/*	Map<String, String> requestAccessMap= (Map<String, String>)portletSession.getAttribute(ChangeMgmtConstant.USERACCESSMAPATTRIBUTEFORSR, PortletSession.APPLICATION_SCOPE);		
		AccountAccess acctRequestAccess = (AccountAccess) portletSession.getAttribute("ACCOUNT_REQUEST_ACCESS_FLAGS",PortletSession.APPLICATION_SCOPE);
		if(requestAccessMap!=null){
			logger.debug("before removing userAccessMapForSr");
			portletSession.setAttribute(ChangeMgmtConstant.USERACCESSMAPATTRIBUTEFORSR,null,PortletSession.APPLICATION_SCOPE);
			logger.debug("after removing userAccessMapForSr");
		}
		if(acctRequestAccess!=null){
			logger.debug("before removing ACCOUNT_REQUEST_ACCESS_FLAGS");
			portletSession.setAttribute("ACCOUNT_REQUEST_ACCESS_FLAGS",null,PortletSession.APPLICATION_SCOPE);
			portletSession.setAttribute("ACCESS_FLAGS_REQUEST",null,PortletSession.APPLICATION_SCOPE);
			logger.debug("after removing ACCOUNT_REQUEST_ACCESS_FLAGS");
		}*/

		LexmarkTransaction lexmarkTran = PerformanceTracker.startTracking(LexmarkConstants.TARGET_SYSTEM_SIEBEL, "retrieveGlobalLegalEntityList", 
				PortalSessionUtil.getMdmId(portletSession), PortalSessionUtil.getServiceUser(portletSession).getEmailAddress());
		GlobalAccount selectedAccount = AccountUtil.retrieveAccountByLegalName(globalLegalEntityService, legalName);
		PerformanceTracker.endTracking(lexmarkTran);
		ServicesUser servicesUser = PortalSessionUtil.getServiceUser(portletSession);
		if(servicesUser == null) {
			servicesUser = new ServicesUser();
		}
		servicesUser.setMdmId(selectedAccount.getMdmId());
		servicesUser.setMdmLevel(selectedAccount.getMdmLevel());
		//servicesUser.setAccountName(legalName + " (" + selectedAccount.getDisplayMdmId() + ")");
		servicesUser.setAccountName(legalName);
		PortalSessionUtil.setServicesUser(portletSession, servicesUser);
		//OBIEE integration requires MDM ID and not DUNS Number
		portletSession.setAttribute("obieeMdmId", selectedAccount.getDisplayMdmId(), portletSession.APPLICATION_SCOPE);

		String siebelIdAvailable = "true";
		model.addAttribute("siebelIdAvailable",siebelIdAvailable);
		request.getPortletSession().setAttribute("siebelIdAvailable", siebelIdAvailable, portletSession.APPLICATION_SCOPE);
		logger.debug("Set MdmId " + selectedAccount.getMdmId() + " in portal session");
		logger.debug("Set MdmLevel " + selectedAccount.getMdmLevel() + " in portal session");
		logger.debug("------------- Step 1---submitEmployeeAccountSelection end---------["+System.nanoTime()+"]");
		
		request.getPortletSession().setAttribute("employeeMdmId", selectedAccount.getMdmId(), portletSession.APPLICATION_SCOPE);
		request.getPortletSession().setAttribute("employeeMdmLevel", selectedAccount.getMdmLevel(), portletSession.APPLICATION_SCOPE);
		request.getPortletSession().setAttribute("employeeAccountName", legalName + " (" + selectedAccount.getDisplayMdmId() + ")", portletSession.APPLICATION_SCOPE);
		request.getPortletSession().setAttribute("employeeReportMdmId", selectedAccount.getDisplayMdmId(), portletSession.APPLICATION_SCOPE);

		//For defect #2902 - dynamically add user to role for MPS and CSS accounts
		User user = null;
		try {
			user = PortalUtil.getUser(request);
			logger.debug("user1  ******************** : " + user);
		} catch (Exception e) {
			logger.debug("Can't get User after login.");
			return;
		}
		try {
			long[] userIds = new long[]{user.getUserId()};
			logger.debug("userIds ******************** : " + userIds);
			logger.debug("userIds[0] ******************** : " + userIds[0]);
			HashMap<String, Long> roleMap = getRoleMap(user.getCompanyId());
			Long mpsCustomerRoleID = roleMap.get(SiebelRoleImportAction.MPS_CUSTOMER);
			logger.debug("mpsCustomerRoleID ******************** : " + mpsCustomerRoleID);
			if(mpsCustomerRoleID!=null)
				{
				UserLocalServiceUtil.deleteRoleUser(mpsCustomerRoleID, userIds[0]);
				}
			
			Long cssCustomerRoleID = roleMap.get(SiebelRoleImportAction.CSS_CUSTOMER);
			logger.debug("cssCustomerRoleID ******************** : " + cssCustomerRoleID);
			
			if(cssCustomerRoleID!=null)
				{
				UserLocalServiceUtil.deleteRoleUser(cssCustomerRoleID, userIds[0]);
				}
			
			SiebelAccountListContract siebelAccountListContract = new SiebelAccountListContract();
			siebelAccountListContract.setMdmId(selectedAccount.getMdmId());
			siebelAccountListContract.setMdmLevel(selectedAccount.getMdmLevel());
			logger.info("start printing lex logger");
			ObjectDebugUtil.printObjectContent(siebelAccountListContract,logger);
			logger.info("end printing lex loggger");
			SiebelAccountListResult siebelAccountListResult = serviceRequestService.retrieveSiebelAccountList(siebelAccountListContract);
			List<Account> accountListCustomer = siebelAccountListResult.getAccountList();
		
			
			
			Map<String,String> requestAccessMap=RequestCreateFlags.retrieveReqHistoryAccess4Portal(PortalUtil.getHttpServletRequest(request),
					PortalSessionUtil.getUserSegment(request.getPortletSession()), accountListCustomer, LexmarkUserUtil.getUserRoleNameList(request));
			request.getPortletSession().setAttribute(ChangeMgmtConstant.USERACCESSMAPATTRIBUTEFORSR, requestAccessMap, portletSession.APPLICATION_SCOPE);
			//Added for LBS 1.5 access for Device status and Device Utilization
			UserDetails userDetails=(UserDetails)request.getPortletSession().getAttribute("USERDETAILS",PortletSession.APPLICATION_SCOPE);
			userDetails.setShowDeviceStatus(true);
			userDetails.setShowDeviceUtilizationTerms(true);
			boolean isMPSAccountType = false;
			boolean isCSSAccountType = false;

			for (Account account : accountListCustomer) {				
				if (account.getAccountType() != null){
					if (account.getAccountType().equalsIgnoreCase(LexmarkConstants.VIEWTYPE_MANAGED_DEVICES)){
						isMPSAccountType = true;
					}
					if (account.getAccountType().equalsIgnoreCase(LexmarkConstants.VIEWTYPE_NON_MANAGED_DEVICES)){
						isCSSAccountType = true;
					}
					if (account.isAlliancePartner()){
						alliancePartner = true;
					}
				}
			}
			
			if (isMPSAccountType){
				Long roleId = roleMap.get(SiebelRoleImportAction.MPS_CUSTOMER);
				if(roleId!=null) {
					UserLocalServiceUtil.addRoleUsers(roleId, userIds);
					logger.debug("MPS Customer");				
				}						
			}
			if (isCSSAccountType){
				Long roleId = roleMap.get(SiebelRoleImportAction.CSS_CUSTOMER);
				if(roleId!=null) {
					UserLocalServiceUtil.addRoleUsers(roleId, userIds);
					logger.debug("CSS Customer");				
				}						
			}		
		logger.debug(" in employee  account selection controller  !!! "+request.getPortletSession().getAttribute(ChangeMgmtConstant.USERACCESSMAPATTRIBUTEFORSR, portletSession.APPLICATION_SCOPE))	;
		request.getPortletSession().setAttribute("IS_ALLIANCE_PARTNER", alliancePartner, portletSession.APPLICATION_SCOPE);
		} catch (Exception e) {
			Throwable t = e;
			while(t.getCause()!=null) {
				t = t.getCause();
			}
			throw new RuntimeException("Unable to set user roles based on Siebel data",e);
		}

	}
	
	/**
	 * @param companyId 
	 * @return HashMap 
	 * @throws SystemException 
	 */
	public HashMap<String,Long> getRoleMap(long companyId) throws SystemException  {
		HashMap<String,Long> roleMap = new HashMap<String,Long>();
		for(Role role: RoleLocalServiceUtil.getRoles(companyId)) {
			roleMap.put(role.getName(),role.getPrimaryKey());
		}
		return roleMap;
	}

}
	