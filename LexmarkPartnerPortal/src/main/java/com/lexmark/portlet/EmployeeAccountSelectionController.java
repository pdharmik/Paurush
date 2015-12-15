package com.lexmark.portlet;

import java.io.PrintWriter;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
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

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.amind.session.StatelessSessionFactory;
import com.lexmark.constants.LexmarkConstants;
import com.lexmark.contract.FSEAccountListContract;
import com.lexmark.contract.PartnerAccountListContract;
import com.lexmark.contract.ProcessCustomerContactContract;
import com.lexmark.contract.SiebelAccountListContract;
import com.lexmark.domain.Account;
import com.lexmark.domain.AccountContact;
import com.lexmark.domain.GlobalAccount;
import com.lexmark.domain.ServicesUser;
import com.lexmark.form.EmployeeAccountSelectionForm;
import com.lexmark.result.FSEAccountListResult;
import com.lexmark.result.PartnerAccountListResult;
import com.lexmark.result.ProcessCustomerContactResult;
import com.lexmark.result.SiebelAccountListResult;
import com.lexmark.service.api.CrmSessionHandle;
import com.lexmark.service.api.GlobalLegalEntityService;
import com.lexmark.service.api.GlobalService;
import com.lexmark.service.api.ServiceRequestService;
import com.lexmark.service.impl.real.domain.PartnerAccountTypeDo;
import com.lexmark.services.hook.RequestCreateFlags;
import com.lexmark.util.ContractFactory;
import com.lexmark.util.ObjectDebugUtil;
import com.lexmark.util.ChangeMgmtConstant;
import com.lexmark.util.AccountUtil;
import com.lexmark.util.CollectionFilter;
import com.lexmark.util.LexmarkUserUtil;
import com.lexmark.util.PortalSessionUtil;
import com.lexmark.util.XMLEncodeUtil;
import com.lexmark.webservice.api.ProcessCustomerContact;
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
    private GlobalLegalEntityService globalLegalEntityService;
	@Autowired
	private ProcessCustomerContact processCustomerContract;
	@Autowired
	private ServiceRequestService serviceRequestService;
	
	@RequestMapping
	public String showEmployeeAccountSelectionPage(Model model, RenderRequest request, RenderResponse response) throws Exception{
		
		PortletSession portletSession = request.getPortletSession();
		
		boolean siebelIdAvailable = true;
		ServicesUser servicesUser = PortalSessionUtil.getServiceUser(portletSession);
		if(servicesUser == null) {
			servicesUser = new ServicesUser();
		}
		
		//added
		//the below line will reset the session to false if a new account has been choosen in the account selector.
		portletSession.setAttribute("pendingDebrief", false, portletSession.APPLICATION_SCOPE);
		//ends here
		
		//Here I need to check if siebel contact id is available
		
		logger.debug("contactIdSession value from request "+(String)request.getPortletSession().getAttribute("siebelIdAvailable", portletSession.APPLICATION_SCOPE));
		String contactId = PortalSessionUtil.getContactId(request.getPortletSession());
		if ((String)request.getPortletSession().getAttribute("siebelIdAvailable", portletSession.APPLICATION_SCOPE)!=null){
			logger.debug("value available from the session");
			siebelIdAvailable = true;
		}else{
			logger.debug("Sieble contact id is "+contactId);
			if("".equals(contactId)){
				siebelIdAvailable = false;
				logger.debug("Siebel contact id is not available");//so call the webservice asynchronously
			}
		}
		model.addAttribute("siebelIdAvailable",siebelIdAvailable);
		//If available go as it is otherwise call some asynchronous method and call the webservice
		
		String employeeMdmId = (String)portletSession.getAttribute("employeeMdmId", portletSession.APPLICATION_SCOPE);
		String employeeMdmLevel = (String)portletSession.getAttribute("employeeMdmLevel", portletSession.APPLICATION_SCOPE);
		String employeeAccountName = (String)portletSession.getAttribute("employeeAccountName", portletSession.APPLICATION_SCOPE);

		boolean employeeProblem = false;
		boolean showFseSelector = false;
		if (PortalSessionUtil.getLexmarkFSEFlag(portletSession)){
			showFseSelector = true;
			List<String> userRoleNameList = LexmarkUserUtil.getUserRoleNameList(request);
			for (String roleName : userRoleNameList) {
			
				
				if (LexmarkConstants.ROLE_ANALYST.equalsIgnoreCase(roleName) ||	
						LexmarkConstants.ROLE_SERVICES_PORTAL_ADMINISTRATOR.equalsIgnoreCase(roleName)){
					//Employee is more than an FSE, need to treat them as not an FSE for this portlet
					showFseSelector = false;
				}
			}
		}
		FSEAccountListResult fSEAccountListResult = new FSEAccountListResult();
		if(showFseSelector){
			if(siebelIdAvailable){
				//Get FSE Account List
				FSEAccountListContract contract = new FSEAccountListContract();
				contract.setSiebelEmployeeId(contactId);
				fSEAccountListResult = globalService.retrieveFSEAccountList(contract);
				model.addAttribute("fSEAccountListResult",fSEAccountListResult);
				
			}else{
				//If the Siebel ID is not available for an FSE Employee, then we have a serious problem
				//Other users will get the Siebel ID populated in the retrieveGlobalLegalEntityList call if it does not exist
				employeeProblem = true;						
			}
		}				
		if(servicesUser.getMdmId() == null || servicesUser.getMdmId().equals("") ||
				servicesUser.getMdmLevel() == null || servicesUser.getMdmLevel().equals("")) {
			if (employeeMdmId == null || employeeMdmId.equals("") ||
					employeeMdmLevel == null || employeeMdmLevel.equals("")){
				//just go to screen
			}else{
				servicesUser.setMdmId(employeeMdmId);
				servicesUser.setMdmLevel(employeeMdmLevel);
				servicesUser.setAccountName(employeeAccountName);
				PortalSessionUtil.setServicesUser(portletSession, servicesUser);

				if(showFseSelector && siebelIdAvailable){
					//Must call FSE Account List because FSE Accounts are not MDMized and the call to retrievePartnerAccountList will not return the accounts
					List<Account> fseAccountList = fSEAccountListResult.getAccountList();
					List<Account> selectedAccountList = new ArrayList<Account>();
					for(Account account: fseAccountList) {
						if (account.getAccountId().equals(employeeMdmId)){
							selectedAccountList.add(account);
						}
					}
					LexmarkUserUtil.setUserFlagsInSession(portletSession, selectedAccountList);
				}else{
					PartnerAccountListContract contract = new PartnerAccountListContract();
					contract.setMdmId(employeeMdmId);
					contract.setMdmLevel(employeeMdmLevel);
					PartnerAccountListResult partnerAccountListResult = globalService.retrievePartnerAccountList(contract);
					LexmarkUserUtil.setUserFlagsInSession(portletSession, partnerAccountListResult.getAccountList());									
				}

			}
		}else{
			if (employeeMdmId == null || employeeMdmId.equals("") ||
					employeeMdmLevel == null || employeeMdmLevel.equals("")){
				//just go to screen
			}else{
				if(!servicesUser.getMdmId().equals(employeeMdmId) || !servicesUser.getMdmLevel().equals(employeeMdmLevel) ) {
					servicesUser.setMdmId(employeeMdmId);
					servicesUser.setMdmLevel(employeeMdmLevel);
					servicesUser.setAccountName(employeeAccountName);
					PortalSessionUtil.setServicesUser(portletSession, servicesUser);

					if(showFseSelector && siebelIdAvailable){
						//Must call FSE Account List because FSE Accounts are not MDMized and the call to retrievePartnerAccountList will not return the accounts
						List<Account> fseAccountList = fSEAccountListResult.getAccountList();
						List<Account> selectedAccountList = new ArrayList<Account>();
						for(Account account: fseAccountList) {
							if (account.getAccountId().equals(employeeMdmId)){
								selectedAccountList.add(account);
							}
						}
						LexmarkUserUtil.setUserFlagsInSession(portletSession, selectedAccountList);
					}else{
						PartnerAccountListContract contract = new PartnerAccountListContract();
						contract.setMdmId(employeeMdmId);
						contract.setMdmLevel(employeeMdmLevel);
						PartnerAccountListResult partnerAccountListResult = globalService.retrievePartnerAccountList(contract);
						LexmarkUserUtil.setUserFlagsInSession(portletSession, partnerAccountListResult.getAccountList());										
					}

				}			 							
			}

		}

		model.addAttribute("employeeProblem",employeeProblem);
		model.addAttribute("showFseSelector",showFseSelector);
		model.addAttribute("selectedAccount",  servicesUser.getAccountName());
		
		return "employeeAccountSelection";
	}
	
	@ResourceMapping
	public void retrieveGlobalLegalEntityList(ResourceRequest request, ResourceResponse response) throws Exception {
		logger.debug("------------- Step 1---retrieveGlobalAccountList started---------["+System.nanoTime()+"]");
		
		String requireServiceCall = (String)request.getParameter("requireLdapUpdate");
		String siebelContactId = "";
		logger.debug("!!!!!!!!!!!!!!!!!!!! requireServiceCall value is "+requireServiceCall);
		if("require".equals(requireServiceCall)){
			logger.debug("!!!!!!!!!!!!!!!!!!!!!!!!! need to update ldap");//that is call the webservice
			PortletSession portletSession =request.getPortletSession();
			Map<String, String> ldapUserData = (Map<String, String>) portletSession.getAttribute(LexmarkConstants.LDAP_USER_DATA_PHASE2, portletSession.APPLICATION_SCOPE);
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
			logger.debug("*********LDAPUserData"+portletSession.getAttribute(LexmarkConstants.LDAP_USER_DATA_PHASE2, portletSession.APPLICATION_SCOPE));
			ProcessCustomerContactResult result;
			try {
				result = processCustomerContract.processCustomerContact(contract);
				siebelContactId = result.getSiebelContactId();
			} catch (SocketTimeoutException socketTimeoutException) {
				// TODO Auto-generated catch block
				socketTimeoutException.printStackTrace();
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
			
			ldapUserData.put(LexmarkConstants.CONTACTID, siebelContactId);
			session.setAttribute(LexmarkConstants.LDAP_USER_DATA_PHASE2, ldapUserData, session.APPLICATION_SCOPE);
			
			logger.debug("*********LDAPUserData"+portletSession.getAttribute(LexmarkConstants.LDAP_USER_DATA_PHASE2, portletSession.APPLICATION_SCOPE));
			logger.debug("Updated siebel contact id is "+siebelContactId);
			
			}else{
			
				logger.debug("!!!!!!!!!!!!!!!!!!!!!!!!! normal ajax call");
				PortletSession session =request.getPortletSession();
				logger.debug("*********LDAPUserData"+session.getAttribute(LexmarkConstants.LDAP_USER_DATA_PHASE2, session.APPLICATION_SCOPE));
				String inputMask = request.getParameter(COMBO_INPUT_MASK);
				List<GlobalAccount> entityList = globalLegalEntityService.getGlobalAccounts();
				if(inputMask != null && !inputMask.equals("")) {
					CollectionFilter filter = new CollectionFilter(Locale.getDefault());
					 HashMap noFuzzyFilter = new HashMap<String, Object>();
					 noFuzzyFilter.put("legalName", inputMask);
					 entityList = filter.filter(entityList, noFuzzyFilter, false);
				}
				PrintWriter out = response.getWriter();
				response.setContentType("text/xml");
				out.print(getXmlContent(entityList));
				out.flush();
				out.close();
			}
		logger.debug("------------- Step 1---retrieveGlobalAccountList end---------["+System.nanoTime()+"]");
	}
	

	
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
	
	@RequestMapping (params = "action=submitEmployeeAccountSelection")
	public void submitEmployeeAccountSelection(ActionRequest request, ActionResponse response,
			@ModelAttribute("employeeAccountSelectionForm") EmployeeAccountSelectionForm
			employeeAccountSelectionForm, BindingResult result,
			Model model) throws Exception {		
		PortletSession portletSession = request.getPortletSession();
		logger.debug("------------- Step 1---submitEmployeeAccountSelection started---------["+System.nanoTime()+"]");
		String mdmId = "";
		String mdmLevel = "";
		String legalName = "";
		String employeeReportMdmId= "";
		boolean createchildSR = false;
		boolean shipToDefault = false;
		boolean showFseSelector = employeeAccountSelectionForm.isShowFseSelector();
		List<Account> accountListPartner = new ArrayList<Account>();
		//Added for 14-07-04 BRD Document lib.
		List<String> countries=new ArrayList<String>();
		List<String> partnerType=new ArrayList<String>();
		//Ends for 14-07-04 BRD Document lib.
		
		if (showFseSelector){
			mdmId = employeeAccountSelectionForm.getAccountNumber();
			mdmLevel = LexmarkConstants.MDM_LEVEL_SIEBEL;
			legalName = employeeAccountSelectionForm.getLegalName();
			//Must set for employee - used by OBIEE
			portletSession.setAttribute("obieeMdmId", mdmId, portletSession.APPLICATION_SCOPE);
			//Must call FSE Account List because FSE Accounts are not MDMized and the call to retrievePartnerAccountList will not return the accounts
			String contactId = PortalSessionUtil.getContactId(request.getPortletSession());
			FSEAccountListContract contract = new FSEAccountListContract();
			contract.setSiebelEmployeeId(contactId);
			FSEAccountListResult fSEAccountListResult = globalService.retrieveFSEAccountList(contract);
			List<Account> fseAccountList = fSEAccountListResult.getAccountList();
			List<Account> selectedAccountList = new ArrayList<Account>();
			for(Account account: fseAccountList) {				
				if (account.getAccountId().equals(mdmId)){
					selectedAccountList.add(account);
				}
				if(account.isCreateChildSR())
				{
					createchildSR = true;
				}
				if("Partner To provide".equalsIgnoreCase(account.getShipToDefault()))
				{
					shipToDefault = true;	
				}
				//Added for 14-07-04 BRD Document lib.
				if(account.getAccountType()!=null){
					partnerType.add(account.getAccountType());
				}
				//Ends for 14-07-04 BRD Document lib.
				
			}
			LexmarkUserUtil.setUserFlagsInSession(portletSession, selectedAccountList);							
			accountListPartner = selectedAccountList;
		}else{
			logger.info("emps1 leagl name"+employeeAccountSelectionForm.getLegalName() );
			GlobalAccount selectedAccount = AccountUtil.retrieveAccountByLegalName(globalLegalEntityService, employeeAccountSelectionForm.getLegalName());
			mdmId = selectedAccount.getMdmId();
			mdmLevel = selectedAccount.getMdmLevel();
			employeeReportMdmId = selectedAccount.getDisplayMdmId();
			logger.info("emps2 mdmId"+mdmId+"--------employeeReportMdmId"+employeeReportMdmId);
			legalName = employeeAccountSelectionForm.getLegalName();
			logger.info("le name"+legalName);
			//OBIEE integration requires MDM ID and not DUNS Number
			portletSession.setAttribute("obieeMdmId", selectedAccount.getDisplayMdmId(), portletSession.APPLICATION_SCOPE);
			logger.info("emps3");
			PartnerAccountListContract contract = new PartnerAccountListContract();
			contract.setMdmId(mdmId);
			contract.setMdmLevel(mdmLevel);
			PartnerAccountListResult partnerAccountListResult = globalService.retrievePartnerAccountList(contract);
			List<Account> accountList = partnerAccountListResult.getAccountList();
			LexmarkUserUtil.setUserFlagsInSession(portletSession, accountList);
			accountListPartner = accountList;
			
			//Added for 14-07-04 BRD Document lib.
			 List<PartnerAccountTypeDo> partnerLst=partnerAccountListResult.getPartnerTypeList();
            for(PartnerAccountTypeDo acc:partnerLst){
                    
                    partnerType.add(acc.getType());
            }
    		//Ends for 14-07-04 BRD Document lib.			
		}
		
		//Added for 14-07-04 BRD Document lib.
		for(Account acc:accountListPartner){
			if(acc.getPartnerCountry()!=null){
				countries.add(acc.getPartnerCountry());
			}
		}
		logger.debug("[All countries = " +countries);
		logger.debug("[All partnertypes = " +partnerType);
		request.getPortletSession().setAttribute(LexmarkConstants.SIEBEL_PARTNER_COUNTRIES, countries, PortletSession.APPLICATION_SCOPE);
		request.getPortletSession().setAttribute(LexmarkConstants.SIEBEL_PARTNER_TYPES, partnerType, PortletSession.APPLICATION_SCOPE);
		//Ends for 14-07-04 BRD Document lib.
		

		ServicesUser servicesUser = PortalSessionUtil.getServiceUser(portletSession);
		if(servicesUser == null) {
			servicesUser = new ServicesUser();
		}
		servicesUser.setMdmId(mdmId);
		servicesUser.setMdmLevel(mdmLevel);
		servicesUser.setAccountName(legalName);
		PortalSessionUtil.setServicesUser(portletSession, servicesUser);
		String siebelIdAvailable = "true";
		model.addAttribute("siebelIdAvailable",siebelIdAvailable);
		request.getPortletSession().setAttribute("siebelIdAvailable", siebelIdAvailable, portletSession.APPLICATION_SCOPE);
		logger.debug("Set MdmId " + mdmId + " in portal session");
		logger.debug("Set MdmLevel " + mdmLevel + " in portal session");
		logger.debug("------------- Step 1---submitEmployeeAccountSelection end---------["+System.nanoTime()+"]");

		request.getPortletSession().setAttribute("employeeMdmId", mdmId, portletSession.APPLICATION_SCOPE);
		request.getPortletSession().setAttribute("employeeMdmLevel", mdmLevel, portletSession.APPLICATION_SCOPE);
		request.getPortletSession().setAttribute("employeeAccountName", legalName, portletSession.APPLICATION_SCOPE);
		request.getPortletSession().setAttribute("employeeReportMdmId", employeeReportMdmId, portletSession.APPLICATION_SCOPE);
		logger.debug("employeeReportMdmId while selection account in the partner portal ---->>>>"+employeeReportMdmId);
		
		
		SiebelAccountListContract siebelAccountListContract = ContractFactory.getSiebelAccountListContract(request);
		ObjectDebugUtil.printObjectContent(siebelAccountListContract, logger);
		CrmSessionHandle crmSessionHandle = globalService.initCrmSessionHandle(PortalSessionUtil.getSiebelCrmSessionHandle(request));
		siebelAccountListContract.setSessionHandle(crmSessionHandle);
		logger.debug("-------------retrieveSiebelAccountList started---------");
		
		SiebelAccountListResult siebelAccountListResult = serviceRequestService.retrieveSiebelAccountList(siebelAccountListContract);
			
		List<Account> accountListCustomer = siebelAccountListResult.getAccountList();
		logger.debug("-------------retrieveSiebelAccountList ended---------accountList Size:"+accountListCustomer.size());
		
		Map<String,String> requestAccessMap=RequestCreateFlags.retrieveReqHistoryAccess4Portal(PortalUtil.getHttpServletRequest(request),
				PortalSessionUtil.getUserSegment(request.getPortletSession()), accountListCustomer, LexmarkUserUtil.getUserRoleNameList(request));
		request.getPortletSession().setAttribute(ChangeMgmtConstant.USERACCESSMAPATTRIBUTEFORSR, requestAccessMap, portletSession.APPLICATION_SCOPE);
		
		boolean processPartnerUpload = false;	
		for(String role :  PortalSessionUtil.getUserRoles(portletSession)) {
			logger.debug("In Employee Account Selection role : " + role);
			if(LexmarkConstants.ROLE_SERVICE_MANAGER.equalsIgnoreCase(role) || 
					LexmarkConstants.ROLE_PARTNER_ADMINISTRATOR.equalsIgnoreCase(role) ||
					LexmarkConstants.ROLE_ANALYST.equalsIgnoreCase(role)) {
				processPartnerUpload = true;
			}
		}
		
		logger.debug("processPartnerUpload Flag 1 ******************** : " + processPartnerUpload);
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
			Long uploadClaimRoleID = roleMap.get(LexmarkConstants.ROLE_UPLOAD_CLAIM);
			logger.debug("uploadClaimRoleID ******************** : " + uploadClaimRoleID);
			if(uploadClaimRoleID!=null)
				UserLocalServiceUtil.deleteRoleUser(uploadClaimRoleID, userIds[0]);
			
			Long uploadRequestRoleID = roleMap.get(LexmarkConstants.ROLE_UPLOAD_REQUEST);
			logger.debug("uploadRequestRoleID ******************** : " + uploadRequestRoleID);
			
			if(uploadRequestRoleID!=null)
				UserLocalServiceUtil.deleteRoleUser(uploadRequestRoleID, userIds[0]);
			
			logger.debug("accountListPartner.size()******************** : " + accountListPartner.size());
 
			if(processPartnerUpload) {
				for (Account account : accountListPartner) {
					logger.debug("account.getAccountName(): " + account.getAccountName());
					logger.debug("account.isUploadClaimFlag(): " + account.isUploadClaimFlag());
					logger.debug("account.isUploadClaimFlag(): " + account.isUploadClaimFlag());
					
					if (account.isUploadClaimFlag()){
							if(uploadClaimRoleID!=null) {
								UserLocalServiceUtil.addRoleUsers(uploadClaimRoleID, userIds);
								logger.debug("Upload Claim");
							}	
					}
					
					if (account.isUploadRequestFlag()){
						if(uploadRequestRoleID!=null) {
							UserLocalServiceUtil.addRoleUsers(uploadRequestRoleID, userIds);
							logger.debug("Upload Request");
						}	
					}
					
					if(account.isCreateChildSR())
					{
						createchildSR = true;
					}
					if("Partner To provide".equalsIgnoreCase(account.getShipToDefault()))
					{
						shipToDefault = true;	
					}
				}
			}
		} catch (Exception e) {
			Throwable t = e;
			while(t.getCause()!=null) t = t.getCause();
			t.printStackTrace();
			throw new RuntimeException("Unable to set user roles based on Siebel data",e);
		}		
		portletSession.setAttribute("SHIP_TO_DEFAULT", shipToDefault,portletSession.APPLICATION_SCOPE);
		portletSession.setAttribute("IS_CREATE_CHILD_SR", createchildSR,portletSession.APPLICATION_SCOPE);
		
	}
	
	public HashSet<String> getRoles() throws Exception {
		HashSet<String> roles = new HashSet<String>();
		roles.add(LexmarkConstants.ROLE_UPLOAD_CLAIM);
		roles.add(LexmarkConstants.ROLE_UPLOAD_REQUEST);
        return roles;
	}
	public HashMap<String,Long> getRoleMap(long companyId) throws com.liferay.portal.kernel.exception.SystemException {
		HashMap<String,Long> roleMap = new HashMap<String,Long>();
		for(Role role: RoleLocalServiceUtil.getRoles(companyId)) {
			roleMap.put(role.getName(),role.getPrimaryKey());
		}
		return roleMap;
	}
	

}
	