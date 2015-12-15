package com.lexmark.services.hook;

import java.util.*;

import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.amind.session.StatelessSessionFactory;
import com.lexmark.constants.LexmarkConstants;
import com.lexmark.contract.DunsNumberContract;
import com.lexmark.contract.LdapSecurityData;
import com.lexmark.contract.PartnerAccountListContract;
import com.lexmark.contract.SiebelAccountListContract;
import com.lexmark.domain.Account;
import com.lexmark.domain.AccountAccess;
import com.lexmark.domain.UserDetails;//Added in MPS2.1
import com.lexmark.result.PartnerAccountListResult;
import com.lexmark.result.SiebelAccountListResult;
import com.lexmark.service.api.GlobalService;
import com.lexmark.service.api.ServiceRequestService;
import com.lexmark.service.impl.real.AmindContractedServiceRequestService;
import com.lexmark.service.impl.real.AmindGlobalService;
import com.lexmark.service.impl.real.domain.PartnerAccountTypeDo;
import com.lexmark.services.util.ChangeMgmtConstant;
import com.lexmark.services.util.ObjectDebugUtil;
import com.lexmark.util.JndiLookupUtil;
//import com.lexmark.util.PerformanceTracker;
import com.liferay.portal.kernel.events.Action;
import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.Role;
import com.liferay.portal.model.User;
import com.liferay.portal.service.RoleLocalServiceUtil;
import com.liferay.portal.util.PortalUtil;
import com.lexmark.userservice.*;

public class SiebelRoleImportAction extends Action {
	private static Logger logger = LogManager.getLogger(SiebelRoleImportAction.class);
	private AmindContractedServiceRequestService serviceRequestService;
	private AmindGlobalService globalService;
	private StatelessSessionFactory statelessSessionFactory;

	public static final String PAGE_COUNTS_SHOW = "Global Services Page Counts Show";
	public static final String PAGE_COUNTS_UPDATE = "Global Services Page Counts Update";
	public static final String SERVICE_REQUESTS_SHOW = "Global Services Service Requests Show";
	public static final String SERVICE_REQUESTS_UPDATE = "Global Services Service Requests Update";
	public static final String CONSUMABLES_SHOW = "Global Services Consumables Show";
	public static final String CONSUMABLES_UPDATE = "Global Services Consumables Update";
	public static final String SHOW_FLAG = "Show";
	public static final String UPDATE_FLAG = "Update";
	public static final String MPS_CUSTOMER = "Global Services MPS Customer";
	public static final String CSS_CUSTOMER = "Global Services CSS Customer";
	//Added for MPS Account Mgmt request
	public static final String ACCOUNT_MANAGEMENT_REQUESTS_SHOW = "Global Services Account Mgmt Requests Show";
	public static final String ACCOUNT_MANAGEMENT_REQUESTS_UPDATE = "Global Services Account Mgmt Requests Update";
	public static final String KEY_OPERATOR = "Global Services Key Operator";
	public static final String CMS_CONSUMABLES_SHOW = "Global Services CMS Consumables Show";
	
	//Added for Hardware Request in MPS 2.1
	public static final String HARDWARE_REQUEST_SHOW = "Global Services Hardware Request Show";
	public static final String HARDWARE_REQUEST_UPDATE = "Global Services Hardware Request Update";
	public static final String MASS_UPLOAD_SERVICE_ORDER = "Global Services Mass Upload Service Order";
	public static final String CUSTOMER_INVOICES = "Global Services Customer Invoices";
	//Added for RequestTabHide in CI-7 : Partha
	public static final String PARTNER_REQUEST = "Global Services Partner Request";
	//This role is for debrief hardware request offline
	public static final String HARDWARE_DEBRIEF_OFFLINE = "Global Services Request Hardware Offline";
	public static final String FLEETMNGT_FLAG = "Global Services Fleet Manager";
	public static final String DEVICEFINDER_FLAG = "Global Services Device Finder";	
	// This login hook is used to dynamically set roles for customers based on Account data in Siebel
	@Override
	public void run(HttpServletRequest req, HttpServletResponse resp) throws ActionException {
		User user = null;
		try {
			user = PortalUtil.getUser(req);
			//setStatelessSessionFactory(new StatelessSessionFactory());
			statelessSessionFactory = aMindStatelessSessionFactory.getInstance();
			setStatelessSessionFactory(statelessSessionFactory);
			
		} catch (Exception e) {
			logger.debug("Can't get User after login.");
			return;
		}
		if(user.isDefaultUser()){ return;}
		try {
			
			//Adding Roles to add roles in session for MPS2.1
			UserDetails userDetails = new UserDetails();
			
			HashSet<String> roles = getRoles();
			HashMap<String, Long> roleMap = getRoleMap(user.getCompanyId());
			
			for (Map.Entry<String, Long> entry : roleMap.entrySet()) {
				logger.debug("key=" + entry.getKey() + ", value=" + entry.getValue());
			}
			//unset for all dynamic roles
			HashSet<Long> dynamicRoles = new HashSet<Long>();
			int roleCount = 0;
			for(String role: roles) {
				Long roleId = roleMap.get(role);
				if(roleId!=null) {
					dynamicRoles.add(roleId);
				}
				roleCount++;
			}
            HashSet<Long> userRoles = new HashSet<Long>();
            for(Long roleid: user.getRoleIds()){
                if(dynamicRoles.contains(roleid)){
                    userRoles.add(roleid);
                }
            }
			HashSet<Long> newUserRoles = new HashSet<Long>();
			/*LexmarkTransaction lexmarkTran = PerformanceTracker.startTracking(LexmarkConstants.TARGET_SYSTEM_SIEBEL,
					"ldapUserService", "",
					user.getEmailAddress());*/

			LdapSecurityData ldapServiceResult = JndiLookupUtil.getLdapServiceResult();
			com.lexmark.userservice.UserService ldapUserService = new com.lexmark.userservice.UserService(ldapServiceResult.getUserName(), ldapServiceResult.getPassword(), ldapServiceResult.getLdapUrl(), ldapServiceResult.getJdbcJndiUrl());
			
			logger.debug("Email address user logged in with: " + user.getEmailAddress());
			com.lexmark.userservice.User ldapuser = ldapUserService.getUserByEmail(user.getEmailAddress());
			logger.debug("Language of the user is " + ldapuser.getLanguage());
			//PerformanceTracker.endTracking(lexmarkTran);
			
			if(ldapuser==null){ throw new RuntimeException("User not found in LDAP!");}
			
			logger.debug("After ldapuser: " + ldapuser.getEmail());
			printUserDetails(ldapuser);
			
			//Set processCustomer and processPartner flag to indicate if the Siebel call should be made.
			boolean processCustomerSR = false;
			boolean processCustomerPC = false;
			boolean processCustomer = false;
			boolean processPartnerUpload = false;
			//Added for MPS Account Mgmt/Consumable request
			boolean processCustomerAcctMgmtReq = false;
			boolean processCustomerConsumableReq = false;
			
			//Added for MPS 2.1
			boolean processCustomerHardwareReq = false;
			boolean processCustomerInvoices = false;
					
			//Added for Partner Portal
			boolean processPartner = false;
			boolean processPartnerCustConsumabelReq = false;
			boolean processPartnerServiceOrders = false;
			//Added in MPS 2.1 for Mass Upload
			boolean processMassUpload4SvcOrders = false;
			boolean processOfflineDebrief = false;
			
			
			
			
			if (ldapuser.getUserSegment().equalsIgnoreCase("customer")||ldapuser.getUserSegment().equalsIgnoreCase("partner")){
				logger.debug("User Roles :::: " +ldapuser.getRoles());
				for (String ldapRole : ldapuser.getRoles()){
					logger.debug("ldapRole ******************************: "+ ldapRole);
					if (ldapRole.equalsIgnoreCase(LexmarkConstants.ROLE_STANDARD_ACCESS)||ldapRole.equalsIgnoreCase(LexmarkConstants.ROLE_KEY_OPERATOR)){
						processCustomer = true;
						processCustomerSR = true;
						
						//Added for MPS Account Mgmt/Consumable request
						processCustomerAcctMgmtReq = true;
						processCustomerConsumableReq = true;
						logger.debug("User is customer and should be processed for all");
					}
					/********** Modified for new roles in IDM **********/
					/*if(ldapRole.equalsIgnoreCase(LexmarkConstants.ROLE_KEY_OPERATOR)){
						processCustomer = true;						
						processCustomerAcctMgmtReq = true;
						logger.debug("User is Key operator and would not create break fix and page counts");
					}*/
					
					if (ldapRole.equalsIgnoreCase(LexmarkConstants.ROLE_SERVICE_SUPPORT)){
						processCustomer = true;
						processCustomerSR = true;
						processCustomerPC = true;						
						processCustomerAcctMgmtReq = true;
						processCustomerConsumableReq = true;
						logger.debug("User is customer and should be processed for all");
					}
					//Added as part of MPS 2.1
					if (ldapRole.equalsIgnoreCase(LexmarkConstants.ROLE_PURCHASING)){
						processCustomer = true;	
						processCustomerHardwareReq = true;
						}
										
					if (ldapRole.equalsIgnoreCase(LexmarkConstants.ROLE_ACCOUNT_MANAGEMENT)){
						processCustomer = true;
						processCustomerAcctMgmtReq = true;
						processCustomerPC = true;
						logger.debug("User is customer and should be processed for PC and Consumables");
					}
					if (ldapRole.equalsIgnoreCase(LexmarkConstants.ROLE_BILLING) ||
							ldapRole.equalsIgnoreCase(LexmarkConstants.ROLE_PROJECT_MANAGEMENT) ||ldapRole.equalsIgnoreCase(LexmarkConstants.ROLE_ANALYST) ||
							ldapRole.equalsIgnoreCase(LexmarkConstants.ROLE_ACCOUNT_ADMINISTRATOR) ||ldapRole.equalsIgnoreCase(LexmarkConstants.ROLE_SERVICES_PORTAL_ADMINISTRATOR) ||
							ldapRole.equalsIgnoreCase(LexmarkConstants.ROLE_PUBLISHING)){
						processCustomer = true;
						logger.debug("User is customer and should be processed for Consumables");
					}
					
					if (ldapRole.equalsIgnoreCase(LexmarkConstants.ROLE_BILLING)){
						processCustomer = true;		
						processCustomerInvoices = true;
						logger.debug("User has standard access and can only view in customer portal");
					}
				
					if (ldapRole.equalsIgnoreCase(LexmarkConstants.ROLE_SERVICE_VIEW)|| 
							ldapRole.equalsIgnoreCase(LexmarkConstants.ROLE_SERVICE_TECHNICIAN)|| 
							ldapRole.equalsIgnoreCase(LexmarkConstants.ROLE_ACCOUNTS_RECEIVABLE)){
						processPartner = true;
						//processPartnerUpload = true;
						processPartnerCustConsumabelReq = true;
						processPartnerServiceOrders = true;
						logger.debug("User has service view and can only view in partner portal");
					}
					/*if (ldapRole.equalsIgnoreCase(LexmarkConstants.ROLE_SERVICE_TECHNICIAN)){
						processPartner = true;
						//processPartnerUpload = true;
						processPartnerCustConsumabelReq = true;
						processPartnerServiceOrders = true;
						logger.debug("User has service technician view and can only view in partner portal");
					}*/
					
					if (ldapRole.equalsIgnoreCase(LexmarkConstants.ROLE_SERVICE_MANAGER ) || 
							ldapRole.equalsIgnoreCase(LexmarkConstants.ROLE_PARTNER_ADMINISTRATOR) || 
							ldapRole.equalsIgnoreCase(LexmarkConstants.ROLE_ANALYST)){
						processPartner = true;
						processPartnerUpload = true;
						processPartnerCustConsumabelReq = true;
						processPartnerServiceOrders = true;
						logger.debug("User is partner and should be processed for Claims or Request Upload");
					}
					
					if (ldapRole.equalsIgnoreCase(LexmarkConstants.ROLE_SERVICE_MANAGER ) || ldapRole.equalsIgnoreCase(LexmarkConstants.ROLE_SERVICE_TECHNICIAN)){
						processMassUpload4SvcOrders = true; //Added in MPS 2.1 for mass upload
						processOfflineDebrief = true; //Added in MPS 2.1 for Offline Debrief
						logger.debug("User is partner and should be processed for Claims or Request Upload");
					}
					
					
				}
			}
			
			logger.debug("processCustomer---------> " + processCustomer);
			logger.debug("processCustomerPC---------> " + processCustomerPC);
			logger.debug("processCustomerSR---------> " + processCustomerSR);
			logger.debug("processCustomerAcctMgmtReq---------> " + processCustomerAcctMgmtReq);
			logger.debug("processCustomerConsumableReq---------> " + processCustomerConsumableReq);
			logger.debug("processCustomerHardwareReq---------> " + processCustomerHardwareReq);
			logger.debug("processMassUpload4SvcOrders---------> " + processMassUpload4SvcOrders);
			logger.debug("processOfflineDebrief---------> " + processOfflineDebrief);
			logger.debug("processCustomerInvoices---------> " + processCustomerInvoices);

			//Process Customers, Partners for Global Services Roles and Partner Portal Roles
			//Modified for MPS Account Mgmt request
			processCustomerAndPartner(userDetails, ldapuser, user, processCustomer,processCustomerInvoices, processCustomerHardwareReq,processCustomerConsumableReq, processCustomerSR, processCustomerAcctMgmtReq, 
										processCustomerPC, processPartner,processPartnerCustConsumabelReq, processMassUpload4SvcOrders, processOfflineDebrief, processPartnerServiceOrders,processPartnerUpload,
										roleMap, newUserRoles, req);
			
			//Process employees
			boolean processEmployeeSR = false;
			boolean processEmployeePC = false;
			boolean processEmployee = false;
			//Added for MPS Account Mgmt request
			boolean processEmployeeAcctMgmtReq = false;
			boolean processEmployeeConsumableReq = false;
			boolean processEmployeeHardwareReq = false;
			boolean processEmployeeCustomerInvoices = false;
			
			if (ldapuser.getUserSegment().equalsIgnoreCase("employee")){
				for (String ldapRole : ldapuser.getGroups()){
					if (ldapRole.equalsIgnoreCase(LexmarkConstants.ROLE_STANDARD_ACCESS)||ldapRole.equalsIgnoreCase(LexmarkConstants.ROLE_KEY_OPERATOR)){
						processEmployee = true;
						processEmployeeSR = true;
						processEmployeePC = true;
						//Added for MPS Account Mgmt/ Consumable request
						processEmployeeAcctMgmtReq = true;
						processEmployeeConsumableReq = true;
						
						logger.debug("User is employee and should be processed for all");
						break;
					}

					if (ldapRole.equalsIgnoreCase(LexmarkConstants.ROLE_SERVICE_SUPPORT)){
						processEmployee = true;
						processEmployeeSR = true;
						processEmployeePC = true;
						//Added for MPS Account Mgmt/Consumable request
						processEmployeeAcctMgmtReq = true;
						processEmployeeConsumableReq = true;
						logger.debug("User is customer and should be processed for all");
						break;
					}
					
					/*if (ldapRole.equalsIgnoreCase(LexmarkConstants.ROLE_STANDARD_ACCESS)){
						processEmployee = true;						
						logger.debug("User has standard access and can only view in customer portal");
					}
					*/
					
					//Added as part of MPS 2.1
					if (ldapRole.equalsIgnoreCase(LexmarkConstants.ROLE_PURCHASING)){
						processEmployee = true;		
						processEmployeeHardwareReq = true;
						logger.debug("User has standard access and can only view in customer portal");
						break;
					}

					if (ldapRole.equalsIgnoreCase(LexmarkConstants.ROLE_ACCOUNT_MANAGEMENT)){
						processEmployee = true;
						processEmployeePC = true;
						logger.debug("User is employee and should be processed for PC and Consumables");
						break;
					}
					
					if (ldapRole.equalsIgnoreCase(LexmarkConstants.ROLE_BILLING)){
						processEmployee = true;
						processEmployeeCustomerInvoices = true;//Added as part of MPS 2.1
						logger.debug("User is employee and should be processed for Customer Invoices");
						break;
					}
					
					if (ldapRole.equalsIgnoreCase(LexmarkConstants.ROLE_BILLING) ||
							ldapRole.equalsIgnoreCase(LexmarkConstants.ROLE_PROJECT_MANAGEMENT) ||ldapRole.equalsIgnoreCase(LexmarkConstants.ROLE_ANALYST) ||
							ldapRole.equalsIgnoreCase(LexmarkConstants.ROLE_ACCOUNT_ADMINISTRATOR) ||ldapRole.equalsIgnoreCase(LexmarkConstants.ROLE_SERVICES_PORTAL_ADMINISTRATOR) ||
							ldapRole.equalsIgnoreCase(LexmarkConstants.ROLE_PUBLISHING)){
						processEmployee = true;
						logger.debug("User is employee and should be processed for Consumables");
						break;
					}
					
					//Roles created for Partners
					if (ldapRole.equalsIgnoreCase(LexmarkConstants.ROLE_SERVICE_VIEW)||
							ldapRole.equalsIgnoreCase(LexmarkConstants.ROLE_SERVICE_MANAGER)||
							ldapRole.equalsIgnoreCase(LexmarkConstants.ROLE_SERVICE_TECHNICIAN)||
							ldapRole.equalsIgnoreCase(LexmarkConstants.ROLE_PARTNER_ADMINISTRATOR)||
							ldapRole.equalsIgnoreCase(LexmarkConstants.ROLE_ACCOUNTS_RECEIVABLE)){
						processEmployee = true;
						
						logger.debug("User is employee and should be processed for all");
						break;
					}
					
					

				}
			}
			if (processEmployee){
				if (processEmployeeSR){
                    userDetails.setShowServiceReq(true);
					Long roleId = roleMap.get(SERVICE_REQUESTS_SHOW);
					if(roleId!=null) {
                        newUserRoles.add(roleId);
						logger.debug("Show SR");
					}						
				}
				//Added for MPS Account Mgmt request
				if (processEmployeeAcctMgmtReq){
                    userDetails.setShowAccMgmtReq(true);
					Long roleId = roleMap.get(ACCOUNT_MANAGEMENT_REQUESTS_SHOW);
					if(roleId!=null) {
                        newUserRoles.add(roleId);
						logger.debug("Show Account Mgmt Requests");
					}						
				}
				
				//Added as part of MPS 2.1
				if (processEmployeeHardwareReq){
                    userDetails.setShowHardwareReq(true);
					Long roleId = roleMap.get(HARDWARE_REQUEST_SHOW);
					if(roleId!=null) {
                        newUserRoles.add(roleId);
						logger.debug("Show Hardware Requests");
					}
					Long roleId2 = roleMap.get(HARDWARE_REQUEST_UPDATE);
					if(roleId2!=null) {
                        newUserRoles.add(roleId2);
                        userDetails.setUpdateHardwareReq(true);
						logger.debug("Show Hardware Requests Update");
					}
				}
				
				if (processEmployeePC){
                    userDetails.setShowPageCount(true);
					Long roleId = roleMap.get(PAGE_COUNTS_SHOW);
					if(roleId!=null) {
                        newUserRoles.add(roleId);
						logger.debug("Show Page Count");
					}						
				}
				if (processEmployeeConsumableReq){
					Long roleId = roleMap.get(CONSUMABLES_SHOW);
					if(roleId!=null) {
	                    newUserRoles.add(roleId);
	                    userDetails.setShowConsumableReq(true);
						logger.debug("Show Consumables");
					}
					//Adding for CMS Consumable Show.
					Long roleId1 = roleMap.get(CMS_CONSUMABLES_SHOW);
					if(roleId1!=null){
						newUserRoles.add(roleId1);
						userDetails.setShowCmsConsumableReq(true);
						logger.debug("CMS consumable show");
					}
				}
				
                userDetails.setFulfillServiceOrder(true);
                userDetails.setCreateConsumableReq4Customer(true);
                userDetails.setMassUploadServiceOrders(true);
                userDetails.setShowCustomerInvoices(true);
                //Added for RequestTabHide in CI-7 : Partha
                userDetails.setPartnerRequestTabHide(true); 
                
				Long roleIdFulfilOrders = roleMap.get(LexmarkConstants.ROLE_FULFILL_ORDERS);
				if(roleIdFulfilOrders!=null) {
                    newUserRoles.add(roleIdFulfilOrders);
					logger.debug("Show and create fulfill orders");
				}
				
				Long roleIdCustReqOrders = roleMap.get(LexmarkConstants.ROLE_CUSTREQ_ORDERS);
				if(roleIdCustReqOrders!=null) {
                    newUserRoles.add(roleIdCustReqOrders);
					logger.debug("Show and create customer req orders");
				}
				
				// Added for MPS 2.1 for Mass Upload
				Long roleMassUpload = roleMap.get(MASS_UPLOAD_SERVICE_ORDER);
				if(roleMassUpload!=null) {
                    newUserRoles.add(roleMassUpload);
					logger.debug("Mass upload of Service orders");
				}
				Long roleCustomerInvoices = roleMap.get(CUSTOMER_INVOICES);
				if (processEmployeeCustomerInvoices){					
					if(roleCustomerInvoices!=null) {
	                    newUserRoles.add(roleCustomerInvoices);
	                    logger.debug("Customer Invoices");
					}
				}				
				// added for LBS
				
				Long roleDeviceFinder = roleMap.get(DEVICEFINDER_FLAG);									
				if(roleDeviceFinder!=null) {
                    newUserRoles.add(roleDeviceFinder);
                    logger.debug("device finder");
				}
				Long roleFleetManageamnt = roleMap.get(FLEETMNGT_FLAG);									
					if(roleFleetManageamnt!=null) {
	                    newUserRoles.add(roleFleetManageamnt);
	                    logger.debug("Fleet management");
					}
				
				/*/Added for RequestTabHide in CI-7 : Partha */
				Long rolePartnerRequest = roleMap.get(PARTNER_REQUEST);
				if(rolePartnerRequest!=null) {
                    newUserRoles.add(rolePartnerRequest);
					logger.debug("Request Tab for Partner");
				}
				
				
			}
			
			//setting user details in session in MPS 2.1		
			req.getSession().setAttribute("USERDETAILS", userDetails);	
					
			logger.debug("before fixUserRoles");
			
            fixUserRoles(user.getUserId(), userRoles, newUserRoles);
		} catch (Exception e) {
			Throwable t = e;
			while(t.getCause()!=null){ t = t.getCause();}
			throw new RuntimeException("Unable to set user roles based on Siebel data",e);
		}
	}

    private void fixUserRoles(long userId, HashSet<Long> oldUserRoles, HashSet<Long> newUserRoles) throws SystemException, PortalException {
    	
    	
    	
    	
    	HashSet<Long> deleteRoles = new HashSet<Long>();
        deleteRoles.addAll(oldUserRoles);
        deleteRoles.removeAll(newUserRoles);
        HashSet<Long> newRoles = new HashSet<Long>();
        logger.debug("newRoles ="+newUserRoles);
        newRoles.addAll(newUserRoles);
        newRoles.removeAll(oldUserRoles);
        if(deleteRoles.size()>0){
            RoleLocalServiceUtil.unsetUserRoles(userId,primitiveArrayFromSet(deleteRoles));
        }
        logger.debug("newRoles.size()="+newRoles.size());
        if(newRoles.size()>0){
            RoleLocalServiceUtil.addUserRoles(userId,primitiveArrayFromSet(newRoles));
        }
    }

    private long[] primitiveArrayFromSet(Set<Long> set) {
        int i=0;
        long[] result = new long[set.size()];
        for(long v: set){ result[i++] = v; }
        return result;
    }

	public HashSet<String> getRoles() throws Exception {
		HashSet<String> roles = new HashSet<String>();
		roles.add(PAGE_COUNTS_SHOW);
		roles.add(PAGE_COUNTS_UPDATE);
		roles.add(SERVICE_REQUESTS_SHOW);
		roles.add(SERVICE_REQUESTS_UPDATE);
		//Added for MPS Account Mgmt request
		roles.add(ACCOUNT_MANAGEMENT_REQUESTS_SHOW);
		roles.add(ACCOUNT_MANAGEMENT_REQUESTS_UPDATE);
		roles.add(CONSUMABLES_SHOW);
		roles.add(CONSUMABLES_UPDATE);
		roles.add(KEY_OPERATOR);
		roles.add(CMS_CONSUMABLES_SHOW);
		roles.add(LexmarkConstants.ROLE_UPLOAD_CLAIM);
		roles.add(LexmarkConstants.ROLE_UPLOAD_REQUEST);
		
		roles.add(LexmarkConstants.ROLE_FULFILL_ORDERS);
		roles.add(LexmarkConstants.ROLE_CUSTREQ_ORDERS);
		
		//Added as part of MPS2.1
		roles.add(HARDWARE_REQUEST_SHOW);
		roles.add(HARDWARE_REQUEST_UPDATE);
		roles.add(MASS_UPLOAD_SERVICE_ORDER);
		roles.add(CUSTOMER_INVOICES);
		//Added for RequestTabHide in CI-7 : Partha
		roles.add(PARTNER_REQUEST);
		
		roles.add(MPS_CUSTOMER);
		roles.add(CSS_CUSTOMER);
		roles.add(FLEETMNGT_FLAG);
		roles.add(DEVICEFINDER_FLAG);		
        return roles;
	}

	public HashMap<String,Long> getRoleMap(long companyId) throws SystemException {
		HashMap<String,Long> roleMap = new HashMap<String,Long>();
		for(Role role: RoleLocalServiceUtil.getRoles(companyId)) {
			roleMap.put(role.getName(),role.getPrimaryKey());
		}
		return roleMap;
	}
	
	public StatelessSessionFactory getStatelessSessionFactory() {
		return statelessSessionFactory;
	}

	public void setStatelessSessionFactory(
			StatelessSessionFactory statelessSessionFactory) {
		statelessSessionFactory.setStatelessSessionTimeout(300000);
		statelessSessionFactory.setStatelessWaitTime(120000);
		statelessSessionFactory.setMaxConnections(20);
		this.statelessSessionFactory = statelessSessionFactory;
	}

	private ServiceRequestService getServiceRequestService() {
		serviceRequestService = new AmindContractedServiceRequestService();
		serviceRequestService.setSessionFactory(getStatelessSessionFactory());
		return serviceRequestService;
	}

	private GlobalService getGlobalService() {
		globalService = new AmindGlobalService();
		globalService.setStatelessSessionFactory(getStatelessSessionFactory());
		return globalService;
	}

	public String makeProper(String theString) throws java.io.IOException{
		 
		java.io.StringReader in = new java.io.StringReader(theString.toLowerCase());
		 boolean precededBySpace = true;
		 StringBuffer properCase = new StringBuffer();    
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
		 
		return properCase.toString();    
		 
	}

	public static void printUserDetails(com.lexmark.userservice.User userbean){
        printStr("Email", userbean.getEmail());
        printStr("First Name", userbean.getFirstname());
        printStr("Last Name", userbean.getLastname());
        printStr("Customer Number", userbean.getId());
        printStr("Company Id", userbean.getCompanyId());
        printStr("Company Name", userbean.getCompanyName());
        printStr("Address 1", userbean.getAddress1());
        printStr("Address 2", userbean.getAddress2());
        printStr("City", userbean.getCity());
        printStr("State/Province", userbean.getState());
        printStr("Postal Code", userbean.getPostalCode());
        printStr("Country", userbean.getCountry());
        printStr("Work Phone", userbean.getWorkPhone());
        printStr("Prefferd Language", userbean.getLanguage());
        printStr("Level", userbean.getUserLevel());
        printStr("Seibel Contact Id", userbean.getSiebelContactId());
        printStr("User Segment", userbean.getUserSegment());
        logger.debug("-----------------------------");
    }
	
	private static void printStr(String name, String value) {
        logger.debug(name + ": " + value);
    }
	
	private void processCustomerAndPartner(UserDetails userDetails, com.lexmark.userservice.User ldapuser, User user, 
				boolean processCustomer, boolean processCustomerInvoices, boolean processCustomerHardwareReq, boolean processCustomerConsumableReq, boolean processCustomerSR, 
				boolean processCustomerAcctMgmtReq, boolean processCustomerPC, 
				boolean processPartner,boolean processPartnerCustConsumabelReq, boolean processMassUpload4SvcOrders, boolean processOfflineDebrief, boolean processPartnerServiceOrders,
				boolean processPartnerUpload, HashMap<String, Long> roleMap, HashSet<Long> newUserRoles, HttpServletRequest req) throws Exception {
		
		String mdmId;
		String mdmLevel = makeProper(ldapuser.getUserLevel());
		SiebelAccountListContract siebelAccountListContract = new SiebelAccountListContract();
		siebelAccountListContract.setMdmLevel(mdmLevel);
		logger.debug("processPartnerUpload  Value *******************   " + processPartnerUpload );
		
		if (ldapuser.getUserLevel().equalsIgnoreCase(LexmarkConstants.GLOBAL) || ldapuser.getUserLevel().equalsIgnoreCase(LexmarkConstants.DOMESTIC)){
			DunsNumberContract dunsNumberContract = new DunsNumberContract();
			dunsNumberContract.setMdmId(ldapuser.getCompanyId());
			dunsNumberContract.setMdmLevel(makeProper(ldapuser.getUserLevel()));
			
			/*LexmarkTransaction lexmarkTran2 = PerformanceTracker.startTracking(LexmarkConstants.TARGET_SYSTEM_SIEBEL,
					"retrieveDunsNumber", ldapuser.getCompanyId(),
					user.getEmailAddress());*/
			
			//DunsNumberResult dunsNumberResult = getGlobalService().retrieveDunsNumber(dunsNumberContract);//From MPS release IDM will have DUNS number for these two levels
			
			//PerformanceTracker.endTracking(lexmarkTran2);
			
			//logger.debug("dunsNumberResult.getDunsNumber(): " + dunsNumberResult.getDunsNumber());
			mdmId = ldapuser.getCompanyId();
			//siebelAccountListContract.setMdmId(dunsNumberResult.getDunsNumber());
			siebelAccountListContract.setMdmId(mdmId);
		}else{
			mdmId = ldapuser.getCompanyId();
			//siebelAccountListContract.setMdmId(ldapuser.getCompanyId());
			siebelAccountListContract.setMdmId(mdmId);
		}
		logger.debug("MDM Used: " + siebelAccountListContract.getMdmId());
		
		/*LexmarkTransaction lexmarkTran3 = PerformanceTracker.startTracking(LexmarkConstants.TARGET_SYSTEM_SIEBEL,
				"retrieveSiebelAccountList", ldapuser.getCompanyId(),
				user.getEmailAddress());*/

		if (processCustomer){
					
			ObjectDebugUtil.printObjectContent(siebelAccountListContract, logger);
			SiebelAccountListResult siebelAccountListResult = getServiceRequestService().retrieveSiebelAccountList(siebelAccountListContract);
			
			List<Account> accountListCustomer = siebelAccountListResult.getAccountList();
			addRolesToUserCustomer(userDetails, accountListCustomer,roleMap,processCustomerConsumableReq,processCustomerSR,processCustomerAcctMgmtReq, processCustomerPC, processCustomerHardwareReq,processCustomerInvoices, newUserRoles);
			HttpSession session = req.getSession(true);
			session.setAttribute("IS_ALLIANCE_PARTNER", userDetails.isAlliancePartner());
			
			//Added for Request TAB History roles..
			Map<String,String> requestAccessMap=RequestCreateFlags.retrieveReqHistoryAccess4Portal(req, ldapuser.getUserSegment(), accountListCustomer, ldapuser.getRoles());
			session.setAttribute(ChangeMgmtConstant.USERACCESSMAPATTRIBUTEFORSR, requestAccessMap);
		}
		if (processPartner){
			PartnerAccountListContract contract = new PartnerAccountListContract();
			logger.debug(" ProcessPartnerUpload is TRUE  and mdmId: " + mdmId);
			logger.debug(" ProcessPartnerUpload is TRUE  and mdmLevel: " + mdmLevel);
			contract.setMdmId(mdmId);
			contract.setMdmLevel(mdmLevel);
			/*PartnerAccountListResult partnerAccountListResult = globalService.retrievePartnerAccountList(contract);*/
			ObjectDebugUtil.printObjectContent(contract,logger);
			PartnerAccountListResult partnerAccountListResult = getGlobalService().retrievePartnerAccountList(contract);
			logger.debug(" After partnerAccountListResult " + partnerAccountListResult);
			 List<Account> accountListPartner = partnerAccountListResult.getAccountList();
             //Done for CI BRD 14-07-04--STARTS
             List<String> countryList= new ArrayList<String>();
             List<String> partnerTypes= new ArrayList<String>();
             for(int i=0;i<accountListPartner.size();i++){
                     countryList.add(accountListPartner.get(i).getPartnerCountry());
             }
             List<PartnerAccountTypeDo> partnerLst=partnerAccountListResult.getPartnerTypeList();
             for(int i=0;i<partnerLst.size();i++){
                     
                     partnerTypes.add(partnerLst.get(i).getType());
             }
             // For Testing
            /* Iterator it_country= countryList.iterator();
             Iterator it_partnerType= partnerTypes.iterator();
             while(it_partnerType.hasNext()){
                     logger.debug(" List of Partner Types--from Siebel--->>" + it_partnerType.next().toString());
             }
             while(it_country.hasNext()){
                     logger.debug(" List of countries--from Siebel--->>" + it_country.next().toString());
             }*/
			logger.debug(" call to addRolesToUserPartner---->>");
			addRolesToUserPartner(userDetails, accountListPartner, roleMap, newUserRoles,processPartnerCustConsumabelReq, processMassUpload4SvcOrders, processOfflineDebrief, processPartnerServiceOrders, processPartnerUpload );
			HttpSession session = req.getSession(true);
			session.setAttribute("SHIP_TO_DEFAULT", userDetails.getShipToDefault());
			session.setAttribute("IS_CREATE_CHILD_SR", userDetails.isCreateChildSR());
			if(countryList!=null){
				logger.debug("  countryList is NOT NULL------------");
				session.setAttribute(LexmarkConstants.SIEBEL_PARTNER_COUNTRIES, countryList);
			}
			
			if(partnerTypes!=null){
				logger.debug("  partnerTypes is NOT NULL------------");
				session.setAttribute(LexmarkConstants.SIEBEL_PARTNER_TYPES, partnerTypes);
			}
			Map<String,String> requestAccessMap=RequestCreateFlags.retrieveReqHistoryAccess4Portal(req, 
					ldapuser.getUserSegment(), accountListPartner, ldapuser.getRoles());
			session.setAttribute(ChangeMgmtConstant.USERACCESSMAPATTRIBUTEFORSR, requestAccessMap);
			//Done for CI BRD 14-07-04-- ENDS
			
		}
		//PerformanceTracker.endTracking(lexmarkTran3);
	}
	
	private void addRolesToUserCustomer (UserDetails userDetails, List<Account> accountList, HashMap<String, Long> roleMap, boolean processCustomerConsumableReq, boolean processCustomerSR, boolean processCustomerAcctMgmtReq, boolean processCustomerPC, boolean processCustomerHardwareReq, boolean processCustomerInvoices, HashSet<Long> newUserRoles) throws Exception{
	
		boolean isMPSAccountType = false;
		boolean isCSSAccountType = false;
			
		for (Account account : accountList) {
			if(account.isViewAllCustomerOrderFlag())
			{
				userDetails.setViewAllCustomerOrderFlag(true);
			}
			if(account.isAlliancePartner())
			{
				userDetails.setAlliancePartner(true);
			}
			if (account.getAccountType() != null){
				if (account.getAccountType().equalsIgnoreCase(LexmarkConstants.VIEWTYPE_MANAGED_DEVICES)){
					isMPSAccountType = true;
				}
				if (account.getAccountType().equalsIgnoreCase(LexmarkConstants.VIEWTYPE_NON_MANAGED_DEVICES)){
					isCSSAccountType = true;
				}				
			}
			
			logger.debug("account.getCreateServiceRequest(): " + account.getCreateServiceRequest());
			logger.debug("account.getAccountMgmtRequest(): "+account.getAccountMgmtRequest());
			logger.debug("account.getManualMeterRead(): " + account.getManualMeterRead());
			logger.debug("account.getUserConsumables(): " + account.getUserConsumables());
			logger.debug("account.getUpsCode(): "+account.getUpsCode());
			logger.debug("account.isInvoiceFlag(): "+account.isInvoiceFlag());
			logger.debug("processCustomerSR="+processCustomerSR);
			logger.debug("account.getAccountId(): " + account.getAccountId());			
			logger.debug("account.getAccountName(): " + account.getAccountName());	
			
			logger.debug("account.isLbsDisplayWeb(): " + account.isLbsDisplayWeb());
			
			if (processCustomerSR){
				if (account.getCreateServiceRequest().equalsIgnoreCase(SHOW_FLAG)){
                    userDetails.setShowServiceReq(true);
					Long roleId = roleMap.get(SERVICE_REQUESTS_SHOW);
					if(roleId!=null) {
                        newUserRoles.add(roleId);
						logger.debug("Show SR");
					}						
				}else if (account.getCreateServiceRequest().equalsIgnoreCase(UPDATE_FLAG)){
					userDetails.setCreateServiceReq(true);
					Long roleId = roleMap.get(SERVICE_REQUESTS_UPDATE);
					if(roleId!=null) {
                        newUserRoles.add(roleId);
						logger.debug("Update SR");
					}					
				}						
			}
					
			//Added for MPS Account Mgmt request
			if (processCustomerAcctMgmtReq){
				if (account.getAccountMgmtRequest().equalsIgnoreCase(SHOW_FLAG)){
                    userDetails.setShowAccMgmtReq(true);
					Long roleId = roleMap.get(ACCOUNT_MANAGEMENT_REQUESTS_SHOW);
					if(roleId!=null) {
                        newUserRoles.add(roleId);
						logger.debug("Show Account Mgmt Request");
					}						
				}else if (account.getAccountMgmtRequest().equalsIgnoreCase(UPDATE_FLAG)){
					Long roleId = roleMap.get(ACCOUNT_MANAGEMENT_REQUESTS_UPDATE);
					userDetails.setCreateAccMgmtReq(true);
					if(roleId!=null) {
                        newUserRoles.add(roleId);
						logger.debug("Update Account Mgmt Request");
					}					
				}						
			}
			
			if (processCustomerPC){
				if (account.getManualMeterRead().equalsIgnoreCase(SHOW_FLAG)){
                    userDetails.setShowPageCount(true);
					Long roleId = roleMap.get(PAGE_COUNTS_SHOW);
					if(roleId!=null) {
                        newUserRoles.add(roleId);
						logger.debug("Show Page Count");
					}						
				}else if (account.getManualMeterRead().equalsIgnoreCase(UPDATE_FLAG)){
                    userDetails.setUpdatePageCount(true);					
					Long roleId = roleMap.get(PAGE_COUNTS_UPDATE);
					if(roleId!=null) {
                        newUserRoles.add(roleId);
						logger.debug("Update Page Count");
					}					
				}						
			}
			if (processCustomerConsumableReq){
				if (account.getUserConsumables().equalsIgnoreCase(SHOW_FLAG)){
                    userDetails.setShowConsumableReq(true);					
					Long roleId = roleMap.get(CONSUMABLES_SHOW);
					if(roleId!=null) {
	                    newUserRoles.add(roleId);
						logger.debug("Show Consumables");
					}						
				}else if (account.getUserConsumables().equalsIgnoreCase(UPDATE_FLAG)){
					userDetails.setUpdateConsumableReq(true);
					Long roleId = roleMap.get(CONSUMABLES_UPDATE);
					if(roleId!=null) {
	                    newUserRoles.add(roleId);
						logger.debug("Update Consumables");
					}					
				}
				//Adding for CMS Consumable Show.
		      logger.info("hiii getUpsCode"+account.getUpsCode()+"and acoutn type"+account.getAccountType());
				// Changed by Tanya for LEX:AIR00073077  .removed not sign from infront of   ( //"DFM".equalsIgnoreCase(account.getAccountType()))  )
				if(("".equalsIgnoreCase(account.getUpsCode())||account.getUpsCode()==null)&& ( "DFM".equalsIgnoreCase(account.getAccountType()))  ){
					Long roleId = roleMap.get(CMS_CONSUMABLES_SHOW);
					if(roleId!=null){
						newUserRoles.add(roleId);
						logger.debug("CMS consumable show");
					}
				}
				
			}
			logger.debug("account.isHardwareRequestFlag()"+account.isHardwareRequestFlag());
			//Added as part of MPS 2.1 for Hardware Role
			if (processCustomerHardwareReq){
				logger.debug("account.isHardwareRequestFlag()"+account.isHardwareRequestFlag());
				if (account.isHardwareRequestFlag().equalsIgnoreCase("SHOW")){
                    userDetails.setShowHardwareReq(true);					
					Long roleId = roleMap.get(HARDWARE_REQUEST_SHOW);
					if(roleId!=null) {
                        newUserRoles.add(roleId);
						logger.debug("Show Hardware Request");
					}						
				}else if (account.isHardwareRequestFlag().equalsIgnoreCase("UPDATE")){
                    userDetails.setUpdateHardwareReq(true);					
					Long roleId = roleMap.get(HARDWARE_REQUEST_UPDATE);
					if(roleId!=null) {
                        newUserRoles.add(roleId);
						logger.debug("Update Hardware Request");
					}					
				}						
			}
			
			//Added as part of MPS 2.1 for Customer Invoices Role
			if (processCustomerInvoices){
				if (account.isInvoiceFlag()){
	                userDetails.setShowCustomerInvoices(true);
					Long roleId = roleMap.get(CUSTOMER_INVOICES);
					if(roleId!=null) {
		                newUserRoles.add(roleId);
						logger.debug("Show Customer Invoices");
					}
				}
			}	
			if (account.isLbsDisplayWeb()){
                userDetails.setFleetMngtFlag(true);
				Long roleId = roleMap.get(FLEETMNGT_FLAG);
				if(roleId!=null) {
	                newUserRoles.add(roleId);
					logger.debug("Fleet management tab");
				}
			}else{
				userDetails.setDeviceFinder(true);
				Long roleId = roleMap.get(DEVICEFINDER_FLAG);
				if(roleId!=null) {
	                newUserRoles.add(roleId);
					logger.debug("Device Finder tab");
				}
			}
		}		
		if (isMPSAccountType){
            userDetails.setMpsCust(true);			
			Long roleId = roleMap.get(MPS_CUSTOMER);
			if(roleId!=null) {
                newUserRoles.add(roleId);
				logger.debug("MPS Customer");				
			}						
		}
		if (isCSSAccountType){
            userDetails.setCssCust(true);			
			Long roleId = roleMap.get(CSS_CUSTOMER);
			if(roleId!=null) {
                newUserRoles.add(roleId);
				logger.debug("CSS Customer");				
			}						
		}	

	}


	private void addRolesToUserPartner (UserDetails userDetails, List<Account> accountList, HashMap<String, Long> roleMap, HashSet<Long> newUserRoles,
										boolean processPartnerCustConsumabelReq, boolean processMassUpload4SvcOrders, boolean processOfflineDebrief, boolean processPartnerServiceOrders,
										boolean processPartnerUpload) throws Exception {
			
		for (Account account : accountList) {
			logger.debug("account.isUploadClaimFlag(): " + account.isUploadClaimFlag());
			logger.debug("account.isUploadRequestFlag(): " + account.isUploadRequestFlag());
			logger.debug("account.isAccessServiceOrderFlag(): " + account.isAccessServiceOrderFlag());
			logger.debug("account.isCreateRequestsForCustomerFlag(): " + account.isCreateRequestsForCustomerFlag());
			logger.debug("account.isDebriefInstallFlag(): " + account.isDebriefInstallFlag());
			logger.debug("account.getAccountId(): " + account.getAccountId());			
			logger.debug("account.getAccountName(): " + account.getAccountName());
			logger.debug("account.isViewAllCustomerOrderFlag(): "+account.isViewAllCustomerOrderFlag());
			logger.debug("account.getShipToDefault(): "+account.getShipToDefault());
			logger.debug("account.isCreateChildSR(): "+account.isCreateChildSR());
			
			if(account.isViewAllCustomerOrderFlag()){
				userDetails.setViewAllCustomerOrderFlag(true);
			}
			
			if(account.isCreateChildSR())
			{
				userDetails.setCreateChildSR(true);
			}
			if(processPartnerUpload){
				if (account.isUploadClaimFlag()){
                    userDetails.setUploadClaim(true);					
					Long roleId = roleMap.get(LexmarkConstants.ROLE_UPLOAD_CLAIM);
					if(roleId!=null) {
                        newUserRoles.add(roleId);
						logger.debug("Upload Claim");
					}	
				}
				
				if (account.isUploadRequestFlag()){
                    userDetails.setUploadRequest(true);					
					Long roleId = roleMap.get(LexmarkConstants.ROLE_UPLOAD_REQUEST);
					if(roleId!=null) {
	                    newUserRoles.add(roleId);
						logger.debug("Upload Request");
					}	
				}
			}
			
			if(processPartnerServiceOrders){
				if (account.isAccessServiceOrderFlag()){
                    userDetails.setFulfillServiceOrder(true);					
					Long roleId = roleMap.get(LexmarkConstants.ROLE_FULFILL_ORDERS);
					if(roleId!=null) {
	                    newUserRoles.add(roleId);
	                    logger.debug("Fulfill orders request added to portal role dynamically");
					}	
				}
			}
			
			if (processPartnerCustConsumabelReq){
				if (account.isCreateRequestsForCustomerFlag()){
                    userDetails.setCreateConsumableReq4Customer(true);					
					Long roleId = roleMap.get(LexmarkConstants.ROLE_CUSTREQ_ORDERS);
					if(roleId!=null) {
	                    newUserRoles.add(roleId);
						logger.debug("Create Customer Orders added to portal role dynamically");
					}	
				}
			}
			
			//Added for Mass Upload Role in MPS2.1
			if (processMassUpload4SvcOrders){
				/*Commented for MAssupload Debrief changes 
				 * MPS 2.1
				 *if (account.isMassUploadFlag()){
				 * */
				logger.debug("account.isMassUploadInstallDebrief()"+account.isMassUploadInstallDebriefFlag());
				logger.debug("account.isMassUploadConsumablesFlag()"+account.isMassUploadConsumablesFlag());
				if (account.isMassUploadConsumablesFlag() || account.isMassUploadInstallDebriefFlag()){
					
					userDetails.setMassUploadServiceOrders(true);
					Long roleId = roleMap.get(MASS_UPLOAD_SERVICE_ORDER);
					if(roleId!=null) {
	                    newUserRoles.add(roleId);	                    
						logger.debug("Mass Upload Service Orders added to portal role dynamically");
					}	
				}
			}
			
			//Added for RequestTabHide in CI-7 : Partha
			//If PartnerRequestTabHideFlag = true then tab will be hide otherwise tab will be open for all partner.
			logger.debug("account.isPartnerRequestTabHideFlag()"+account.isPartnerRequestTabHideFlag());
			if (!account.isPartnerRequestTabHideFlag()){				
				userDetails.setPartnerRequestTabHide(true);
				Long roleId = roleMap.get(PARTNER_REQUEST);
				if(roleId!=null) {
                    newUserRoles.add(roleId);	                    
					logger.debug("Partner Request added to portal role dynamically");
				}	
			}
			
			/**
			 * Added for offline hardware debrief 
			 * */
			if(processOfflineDebrief){
				if(account.isDebriefInstallFlag() ){
					Long roleId = roleMap.get(HARDWARE_DEBRIEF_OFFLINE);
					if(roleId!=null) {
	                    newUserRoles.add(roleId);	                    
						logger.debug("Offline hardware debrief added to portal role dynamically");
					}	
				}
			}
			logger.debug("account.getShipToDefault()" + account.getShipToDefault());
			if(account.getShipToDefault().equalsIgnoreCase("Partner To provide")){
				userDetails.setShipToDefault(true);
			}
		}
	}
	
	
	
	
}
