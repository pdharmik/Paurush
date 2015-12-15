package com.lexmark.services.hook;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeansException;

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.domain.Account;
import com.lexmark.domain.AccountAccess;
import com.lexmark.domain.EntitlementRoleMapping;
import com.lexmark.service.api.EntitlementRoleMappingService;
import com.lexmark.service.impl.real.jdbc.EntitlementRoleMappingServiceImpl;
import com.lexmark.services.listner.ApplicationContextProvider;
import com.lexmark.services.util.ChangeMgmtConstant;
import com.lexmark.services.util.EntitlementRolesUtil;
import com.lexmark.services.util.PortalSessionUtil;

public class RequestCreateFlags {

	
	private static final String METH_RETRIEVEREQHISTORYACCESS4PORTAL ="retrieveReqHistoryAccess4Portal";
	private static final String CREATE_SERVICE_REQUEST = "CREATE SERVICE REQUEST";
	private static final String CREATE_SUPPLIES_REQUEST ="CREATE SUPPLIES REQUEST";
	private static final String CREATE_CHANGE_MGMT_REQUEST ="CREATE CHANGE MGMT REQUEST";
	private static final String SHOW_SERVICE_REQUEST = "SHOW SERVICE REQUEST";
	private static final String SHOW_SUPPLIES_REQUEST ="SHOW SUPPLIES REQUEST";
	private static final String SHOW_CHANGE_MGMT_REQUEST ="SHOW CHANGE MGMT REQUEST";
	private static final String CREATE_HARDWARE_REQUEST = "CREATE HARDWARE REQUEST";
	private static final String SHOW_HARDWARE_REQUEST = "SHOW HARDWARE REQUEST";
	private static final String SHOW_ALL_REQUEST = "SHOW ALL REQUEST" ;	
	private static final String CREATE_MAP_REQUEST = "CREATE MAP REQUEST";
	private static final String FLEET_MANAGER_FLAG = "FLEET MANAGER FLAG";
	
	private static Logger logger = LogManager.getLogger(RequestCreateFlags.class);
	public static Map<String,String>  retrieveReqHistoryAccess4Portal(HttpServletRequest request,
			String ldapUserType,
			List<Account> accountList,List<String> userRoleList
			) {
		logger.debug("[ in retrieveReqHistoryAccess4Portal]");
		Map<String, String> requestAccessMap= new HashMap<String, String>(); 
	try{
				HttpSession session = request.getSession();
				
				
				
				
				
				
				
				String userType=getUserType(ldapUserType, userRoleList);
				logger.debug("user Type is = "+userType);
				AccountAccess accountReqAccess = null;
				String showServiceReq ="";
				String showSuppliesReq ="";
				String showAcctMgmtReq ="";
				String showHardwareReq ="";
				
				 String createServiceReq ="";
				 String createSuppliesReq ="";
				 String createAcctMgmtReq ="";
				 String createHardwareReq ="";
				 String createMapSRFlag ="";
				 
				
				logger.debug("userType-->"+userType);
				//This logic has been put temporarylly, need to verify later based on access role
				if(userType != null && (userType.equalsIgnoreCase("Employee"))){
					logger.info("inside check for employee");
					accountReqAccess = (AccountAccess)verifyRequestAccessFlag4Acct(request,accountList);
					requestAccessMap.put(ChangeMgmtConstant.SHOW_ASSET_ACCEPTANCE, ChangeMgmtConstant.TRUE);
				}else if(userType != null && userType.equalsIgnoreCase("Customer")){
				  //This logic is for customer
					logger.debug("*****User is a Customer*****");
					
					 Map<String, EntitlementRoleMapping> entitleMentroleMapping=getRolesForEntitlement();
					showServiceReq = getEntitlementStatus( request, SHOW_SERVICE_REQUEST,userRoleList,entitleMentroleMapping);
					showSuppliesReq = getEntitlementStatus( request, SHOW_SUPPLIES_REQUEST,userRoleList,entitleMentroleMapping);
					showAcctMgmtReq = getEntitlementStatus( request, SHOW_CHANGE_MGMT_REQUEST,userRoleList,entitleMentroleMapping);
					showHardwareReq = getEntitlementStatus( request, SHOW_HARDWARE_REQUEST,userRoleList,entitleMentroleMapping); // Added for Hardware Request in MPS2.1
					
					
					createServiceReq = getEntitlementStatus( request, CREATE_SERVICE_REQUEST,userRoleList,entitleMentroleMapping);
					createSuppliesReq = getEntitlementStatus( request, CREATE_SUPPLIES_REQUEST,userRoleList,entitleMentroleMapping);
					createAcctMgmtReq = getEntitlementStatus( request, CREATE_CHANGE_MGMT_REQUEST,userRoleList,entitleMentroleMapping);
					createHardwareReq = getEntitlementStatus( request, CREATE_HARDWARE_REQUEST,userRoleList,entitleMentroleMapping); // Added for Hardware Request in MPS2.1
					createMapSRFlag = getEntitlementStatus( request, CREATE_MAP_REQUEST,userRoleList,entitleMentroleMapping);
					
					logger.debug("createServiceReq from db " + createServiceReq);
					logger.debug("createSuppliesReq from db " + createSuppliesReq);
					logger.debug("createAcctMgmtReq from db " + createAcctMgmtReq);
					logger.debug("createHardwareReq from db " + createHardwareReq);
					logger.debug("createMapSRFlag from db " + createMapSRFlag);
					
					logger.debug("showServiceReq--->"+showServiceReq);
					logger.debug("showSuppliesReq--->"+showSuppliesReq);
					logger.debug("showAcctMgmtReq--->"+showAcctMgmtReq);
					logger.debug("showHardwareReq--->"+showHardwareReq);
		
					
					
					
					
					
					
					if(createServiceReq != null && createServiceReq.equalsIgnoreCase("False")){
						logger.debug("***Create Service req is false*****");
						requestAccessMap.put(CREATE_SERVICE_REQUEST, "False");
					}
					
					if(createSuppliesReq != null && createSuppliesReq.equalsIgnoreCase("False"))
					{
						logger.debug("***createSuppliesReq is false*****");
						requestAccessMap.put(CREATE_SERVICE_REQUEST, "False");
					}
					
					if(createAcctMgmtReq != null && createAcctMgmtReq.equalsIgnoreCase("False"))
					{
						logger.debug("***createAcctMgmtReq is false*****");
						requestAccessMap.put(CREATE_CHANGE_MGMT_REQUEST, "False");
					}
					
					if(createHardwareReq != null && createHardwareReq.equalsIgnoreCase("False"))
					{
						logger.debug("***createHardwareReq is false*****");
						requestAccessMap.put(CREATE_HARDWARE_REQUEST, "False");
					}
					
					if ((createServiceReq != null && createServiceReq.equalsIgnoreCase("True")) ||
							(createSuppliesReq != null && createSuppliesReq.equalsIgnoreCase("True")) || 
							(createAcctMgmtReq != null && createAcctMgmtReq.equalsIgnoreCase("True")) ||
							(createHardwareReq != null && createHardwareReq.equalsIgnoreCase("True")) ||
							(showServiceReq != null && showServiceReq.equalsIgnoreCase("true")) ||
							(showSuppliesReq != null && showSuppliesReq.equalsIgnoreCase("true")) || 
							(showAcctMgmtReq != null && showAcctMgmtReq.equalsIgnoreCase("true")) ||
							(showHardwareReq != null && showHardwareReq.equalsIgnoreCase("true"))){
						logger.debug("***Before calling account access*****");
						accountReqAccess = (AccountAccess)verifyRequestAccessFlag4Acct(request,accountList);				
					}
					
				
				}
				
				if (accountReqAccess != null){
					logger.debug("***Setting the Request History flags*****");
		
					logger.debug("Show Service Req Flag---->"+accountReqAccess.getShowServiceReqFlag());
					logger.debug("Show Supply Req Flag---->"+accountReqAccess.getShowSuppliesReqFlag());
					logger.debug("Show Acc mngmt Flag---->"+accountReqAccess.getShowAccountMgmtReqFlag());
					logger.debug("Show Hardware Req Flag---->"+accountReqAccess.getShowHardwareReqFlag());// Added for Hardware Request in MPS2.1
					if(userType != null && (userType.equalsIgnoreCase("Employee"))){
						requestAccessMap.put(SHOW_SERVICE_REQUEST, accountReqAccess.getShowServiceReqFlag());
						requestAccessMap.put(SHOW_SUPPLIES_REQUEST, accountReqAccess.getShowSuppliesReqFlag());
						requestAccessMap.put(SHOW_CHANGE_MGMT_REQUEST, accountReqAccess.getShowAccountMgmtReqFlag());
						requestAccessMap.put(SHOW_HARDWARE_REQUEST, accountReqAccess.getShowHardwareReqFlag());// Added for Hardware Request in MPS2.1
						
					}else if(userType != null && userType.equalsIgnoreCase("Customer")){
						if((showServiceReq != null && showServiceReq.equalsIgnoreCase("true")) && accountReqAccess.getShowServiceReqFlag().equalsIgnoreCase("true")){
							requestAccessMap.put(SHOW_SERVICE_REQUEST, "true");
						}else{
							requestAccessMap.put(SHOW_SERVICE_REQUEST, "false");
						}
						
						if((showSuppliesReq != null && showSuppliesReq.equalsIgnoreCase("true")) && accountReqAccess.getShowSuppliesReqFlag().equalsIgnoreCase("true")){
							requestAccessMap.put(SHOW_SUPPLIES_REQUEST, "true");
						}else{
							requestAccessMap.put(SHOW_SUPPLIES_REQUEST, "false");
						}
						
						if((showAcctMgmtReq != null && showAcctMgmtReq.equalsIgnoreCase("true")) && accountReqAccess.getShowAccountMgmtReqFlag().equalsIgnoreCase("true")){
							requestAccessMap.put(SHOW_CHANGE_MGMT_REQUEST, "true");
						}else{
							requestAccessMap.put(SHOW_CHANGE_MGMT_REQUEST, "false");
						}
						
						if((showHardwareReq != null && showHardwareReq.equalsIgnoreCase("true")) && accountReqAccess.getShowHardwareReqFlag().equalsIgnoreCase("true")){
							requestAccessMap.put(SHOW_HARDWARE_REQUEST, "true");
						}else{
							requestAccessMap.put(SHOW_HARDWARE_REQUEST, "false");
						}
					}
				}else{
					logger.debug("***Customer does not have Request History access*****");
					requestAccessMap.put(SHOW_SERVICE_REQUEST, "false");
					requestAccessMap.put(SHOW_SUPPLIES_REQUEST, "false");
					requestAccessMap.put(SHOW_CHANGE_MGMT_REQUEST, "false");
					requestAccessMap.put(SHOW_HARDWARE_REQUEST, "false");// Added for Hardware Request in MPS2.1
					
				}
				
				
				Iterator<String> itr = requestAccessMap.keySet().iterator();
				int count = 0;
				while(itr.hasNext()){
					String key = itr.next();
					if((key.equals(SHOW_SERVICE_REQUEST) || key.equals(SHOW_SUPPLIES_REQUEST) || key.equals(SHOW_CHANGE_MGMT_REQUEST) || key.equals(SHOW_HARDWARE_REQUEST))
						&& requestAccessMap.get(key).equalsIgnoreCase("true")){
							count ++;
					}
					
				}
					if(count>=1){
						requestAccessMap.put(SHOW_ALL_REQUEST, "true");
					}else{
						requestAccessMap.put(SHOW_ALL_REQUEST, "false");
					}
					logger.debug("requestAccessMap is -->" + requestAccessMap);
				
				
				//Below are the check for rolesss..
				
				if(userType != null && (userType.equalsIgnoreCase(LexmarkConstants.USER_SEGMENT_EMPLOYEE) || userType.equalsIgnoreCase(ChangeMgmtConstant.VENDORREQTYPE))){
					if (accountReqAccess != null){
						logger.debug("***Setting the CREATE access flags*****");
						logger.debug("before if ");
						if(requestAccessMap.containsKey(CREATE_SUPPLIES_REQUEST))
							{
								logger.debug("***Map contains supplies req*****");
								requestAccessMap.put(CREATE_SERVICE_REQUEST, accountReqAccess.getCreateServiceReqFlag());
								requestAccessMap.put(CREATE_CHANGE_MGMT_REQUEST, accountReqAccess.getCreateAccountMgmtReqFlag());
							}
							else if(requestAccessMap.containsKey(CREATE_CHANGE_MGMT_REQUEST))
							{
								logger.debug("***map contains cm req*****");
								requestAccessMap.put(CREATE_SERVICE_REQUEST, accountReqAccess.getCreateServiceReqFlag());
								requestAccessMap.put(CREATE_SUPPLIES_REQUEST, accountReqAccess.getCreateSuppliesReqFlag());
							}
							else if(requestAccessMap.containsKey(CREATE_SERVICE_REQUEST))
							{
								logger.debug("***map contains service req*****");
								requestAccessMap.put(CREATE_SUPPLIES_REQUEST, accountReqAccess.getCreateSuppliesReqFlag());
								requestAccessMap.put(CREATE_CHANGE_MGMT_REQUEST, accountReqAccess.getCreateAccountMgmtReqFlag());
							}
							else{
								requestAccessMap.put(CREATE_SERVICE_REQUEST, accountReqAccess.getCreateServiceReqFlag());
								requestAccessMap.put(CREATE_SUPPLIES_REQUEST, accountReqAccess.getCreateSuppliesReqFlag());
								requestAccessMap.put(CREATE_CHANGE_MGMT_REQUEST, accountReqAccess.getCreateAccountMgmtReqFlag());
							}
						
						
						
						requestAccessMap.put(CREATE_HARDWARE_REQUEST, accountReqAccess.getCreateHardwareReqFlag());// Added for Hardware Request in MPS2.1
						requestAccessMap.put(CREATE_MAP_REQUEST, accountReqAccess.getCreateMapSRFlag());//LBS MapSR Flag
						requestAccessMap.put(FLEET_MANAGER_FLAG, accountReqAccess.getFleetManagerFlag());//LBS MapSR Flag
					}else{
						logger.debug("***Customer does not have Create access*****");
						requestAccessMap.put(CREATE_SERVICE_REQUEST, "False");
						requestAccessMap.put(CREATE_SUPPLIES_REQUEST, "False");
						requestAccessMap.put(CREATE_CHANGE_MGMT_REQUEST, "False");
						requestAccessMap.put(CREATE_HARDWARE_REQUEST, "False");// Added for Hardware Request in MPS2.1
						requestAccessMap.put(CREATE_MAP_REQUEST, "False");//LBS MapSR Flag
						requestAccessMap.put(FLEET_MANAGER_FLAG, "False");//Fleet Manager Flag
					}
				}
				
				if(userType != null && userType.equalsIgnoreCase(LexmarkConstants.PORTAL_CUSTOMER)){
					if (accountReqAccess != null){
						logger.debug("***Setting the CREATE access flags*****");
						
						if(requestAccessMap.containsKey(CREATE_SUPPLIES_REQUEST))
							{
								logger.debug("***Map contains supplies req*****");
								if(createServiceReq != null && createServiceReq.equalsIgnoreCase("true") && accountReqAccess.getCreateServiceReqFlag().equalsIgnoreCase("true")){
									requestAccessMap.put(CREATE_SERVICE_REQUEST, "true");
								}else{
									requestAccessMap.put(CREATE_SERVICE_REQUEST, "false");
								}
								
								if(createAcctMgmtReq != null && createAcctMgmtReq.equalsIgnoreCase("true") && accountReqAccess.getCreateAccountMgmtReqFlag().equalsIgnoreCase("true")){
									requestAccessMap.put(CREATE_CHANGE_MGMT_REQUEST, "true");
								}else{
									requestAccessMap.put(CREATE_CHANGE_MGMT_REQUEST, "false");
								}
							}
							else if(requestAccessMap.containsKey(CREATE_CHANGE_MGMT_REQUEST))
							{
								logger.debug("***map contains cm req*****");
								
								if(createServiceReq != null && createServiceReq.equalsIgnoreCase("true") && accountReqAccess.getCreateServiceReqFlag().equalsIgnoreCase("true")){
									requestAccessMap.put(CREATE_SERVICE_REQUEST, "true");
								}else{
									requestAccessMap.put(CREATE_SERVICE_REQUEST, "false");
								}
								
								if(createSuppliesReq != null && createSuppliesReq.equalsIgnoreCase("true") && accountReqAccess.getCreateSuppliesReqFlag().equalsIgnoreCase("true")){
									requestAccessMap.put(CREATE_SUPPLIES_REQUEST, "true");
								}else{
									requestAccessMap.put(CREATE_SUPPLIES_REQUEST, "false");
								}
							}
							else if(requestAccessMap.containsKey(CREATE_SERVICE_REQUEST))
							{
								logger.debug("***map contains service req*****");
								
								if(createAcctMgmtReq != null && createAcctMgmtReq.equalsIgnoreCase("true") && accountReqAccess.getCreateAccountMgmtReqFlag().equalsIgnoreCase("true")){
									requestAccessMap.put(CREATE_CHANGE_MGMT_REQUEST, "true");
								}else{
									requestAccessMap.put(CREATE_CHANGE_MGMT_REQUEST, "false");
								}
								
								if(createSuppliesReq != null && createSuppliesReq.equalsIgnoreCase("true") && accountReqAccess.getCreateSuppliesReqFlag().equalsIgnoreCase("true")){
									requestAccessMap.put(CREATE_SUPPLIES_REQUEST, "true");
								}else{
									requestAccessMap.put(CREATE_SUPPLIES_REQUEST, "false");
								}
							}
							else{
								requestAccessMap.put(CREATE_SERVICE_REQUEST, accountReqAccess.getCreateServiceReqFlag());
								requestAccessMap.put(CREATE_SUPPLIES_REQUEST, accountReqAccess.getCreateSuppliesReqFlag());
								requestAccessMap.put(CREATE_CHANGE_MGMT_REQUEST, accountReqAccess.getCreateAccountMgmtReqFlag());
							}
						
							if(createHardwareReq != null && createHardwareReq.equalsIgnoreCase("true") && accountReqAccess.getCreateHardwareReqFlag().equalsIgnoreCase("true")){
								requestAccessMap.put(CREATE_HARDWARE_REQUEST, "true");
							}else{
								requestAccessMap.put(CREATE_HARDWARE_REQUEST, "false");
							}// Added for Hardware Request in MPS2.1
							
							//LBS MapSR Flag
							if(createMapSRFlag != null && createMapSRFlag.equalsIgnoreCase("true") && accountReqAccess.getCreateMapSRFlag().equalsIgnoreCase("true")){
								requestAccessMap.put(CREATE_MAP_REQUEST, "true");
							}else{
								requestAccessMap.put(CREATE_MAP_REQUEST, "false");
							}
							
							//Fleet manager Flag
							if(accountReqAccess.getFleetManagerFlag()!=null && accountReqAccess.getFleetManagerFlag().equalsIgnoreCase("true")){
								requestAccessMap.put(FLEET_MANAGER_FLAG, "true");
							}else{
								requestAccessMap.put(FLEET_MANAGER_FLAG, "false");
							}
					}else{
						logger.debug("***Customer does not have Create access*****");
						requestAccessMap.put(CREATE_SERVICE_REQUEST, "False");
						requestAccessMap.put(CREATE_SUPPLIES_REQUEST, "False");
						requestAccessMap.put(CREATE_CHANGE_MGMT_REQUEST, "False");
						requestAccessMap.put(CREATE_HARDWARE_REQUEST, "False");// Added for Hardware Request in MPS2.1
						requestAccessMap.put(CREATE_MAP_REQUEST, "False");//LBS MapSR Flag
						requestAccessMap.put(FLEET_MANAGER_FLAG, "False");//Fleet Manager Flag
					}
				}
				
				if(userType != null && userType.equalsIgnoreCase(ChangeMgmtConstant.VENDORREQTYPE))
				{
					logger.debug("User is a partner----->");
					
					if(userRoleList.contains(LexmarkConstants.ROLE_SERVICE_VIEW) 
					&& !userRoleList.contains(LexmarkConstants.ROLE_SERVICE_TECHNICIAN)
					&& !userRoleList.contains(LexmarkConstants.ROLE_SERVICE_MANAGER)
					&& !userRoleList.contains(LexmarkConstants.ROLE_PARTNER_ADMINISTRATOR)){
						
						logger.debug("Vendor has role service view ");
						requestAccessMap.put(CREATE_SERVICE_REQUEST, "False");
						requestAccessMap.put(CREATE_SUPPLIES_REQUEST, "False");
						requestAccessMap.put(CREATE_CHANGE_MGMT_REQUEST, "False");
						requestAccessMap.put(SHOW_SUPPLIES_REQUEST, "True");
					}
					else{
						logger.debug("***Vendor with Create access for Supplies Request and Service Request*****");
						requestAccessMap.put(CREATE_SERVICE_REQUEST, "False");
						requestAccessMap.put(CREATE_SUPPLIES_REQUEST, "True");
						requestAccessMap.put(CREATE_CHANGE_MGMT_REQUEST, "False");
						requestAccessMap.put(SHOW_SUPPLIES_REQUEST, "True");
					}
				}
				
				/*
				 * MPS 2.1 Changes below section is for Showing Asset Acceptance
				 * Link in create new request popup
				 * */
				if( userRoleList.contains(ChangeMgmtConstant.ACCOUNT_MANAGEMENT)){
					requestAccessMap.put(ChangeMgmtConstant.SHOW_ASSET_ACCEPTANCE, ChangeMgmtConstant.TRUE);
					
					if(userRoleList.size()==1){
						requestAccessMap.put(ChangeMgmtConstant.ONLY_ASSET_ACCEPTANCE, ChangeMgmtConstant.TRUE);
						requestAccessMap.put(CREATE_SERVICE_REQUEST, "False");
						requestAccessMap.put(CREATE_SUPPLIES_REQUEST, "False");
						requestAccessMap.put(CREATE_HARDWARE_REQUEST, "False");
					}
				}
				
				
				if(requestAccessMap!=null) {
					logger.debug("serviceRequestsAccessMap size is " + requestAccessMap.size());
				}
				
				
				if(requestAccessMap!= null && requestAccessMap.containsKey(FLEET_MANAGER_FLAG)){
					logger.debug("FLEET_MANAGER_FLAG is there" + requestAccessMap.get(FLEET_MANAGER_FLAG));
					//session.setAttribute("fleetManageDropFlag", requestAccessMap.get(FLEET_MANAGER_FLAG));
				}
				logger.debug("requestAccessMap in chk user roles is -->" + requestAccessMap);
				//session.setAttribute(ChangeMgmtConstant.USERACCESSMAPATTRIBUTEFORSR, requestAccessMap);
				
		}catch(Exception ex){
			ex.printStackTrace();
			logger.debug("Exception "+ex.getMessage());
		}
		finally{
			logger.debug("[out]"+METH_RETRIEVEREQHISTORYACCESS4PORTAL);
			return requestAccessMap;
		}
		
	}
	
	public static AccountAccess verifyRequestAccessFlag4Acct(HttpServletRequest request,List<Account> accountList) throws Exception {
		logger.debug("in verifyRequestAccessFlag4Acct");
		
		
		
		
		String createSuppliesReqFlag = "false";
		String createServiceReqFlag = "false";
		String createAcctMgmtReqFlag = "false";
		String showSuppliesReqFlag = "false";
		String showServiceReqFlag = "false";
		String showAcctMgmtReqFlag = "false";
		
		/* Added for Hardware Request in MPS2.1 */
		String showHardwareReqFlag = "false";
		String createHardwareReqFlag = "false";
		String createMapSRFlag = "false";
		//String showFleetManagerFlag = "false";
			
		
	    	
		AccountAccess acctRequestAccess = new AccountAccess();
			logger.debug("-------------retrieveSiebelAccountList ended---------accountList Size:"+accountList.size());
				for (Account account : accountList) {
					logger.debug("ACCOUNT FLAGS--::SERVICE REQUEST="+account.getCreateServiceRequest()+
							"::SUPPLIES REQUEST="+account.getUserConsumables()+"::ACCOUNT MANAGEMENT="+
							account.getAccountMgmtRequest()+ "::HARDWARE"+account.isHardwareRequestFlag());
					//Verify for Create Service Request
					if (account.getCreateServiceRequest()!=null && account.getCreateServiceRequest().equalsIgnoreCase("update")) {
						createServiceReqFlag = "true";
						showServiceReqFlag = "true";
					}else if (account.getCreateServiceRequest()!=null && account.getCreateServiceRequest().equalsIgnoreCase("show") ) {
						showServiceReqFlag = "true";
					}
					//Verify for Create supplies Request
					if (account.getUserConsumables()!=null && account.getUserConsumables().equalsIgnoreCase("update")) {
						createSuppliesReqFlag = "true";
						showSuppliesReqFlag = "true";
					}else if (account.getUserConsumables()!=null && account.getUserConsumables().equalsIgnoreCase("show")) {
						showSuppliesReqFlag = "true";
					}
					
					//Verify for Account Management Request
					logger.debug("account.getAccountMgmtRequest()="+account.getAccountMgmtRequest());
					if (account.getAccountMgmtRequest()!=null && account.getAccountMgmtRequest().equalsIgnoreCase("update")) {
						createAcctMgmtReqFlag = "true";
						showAcctMgmtReqFlag = "true";
					}else if (account.getAccountMgmtRequest()!=null && account.getAccountMgmtRequest().equalsIgnoreCase("show")) {
						showAcctMgmtReqFlag = "true";
					} 
					
					//Verify for Hardware Request
					if (account.isHardwareRequestFlag()!=null && account.isHardwareRequestFlag().equalsIgnoreCase("update")) {
						createHardwareReqFlag = "true";
						showHardwareReqFlag = "true";
					}else if (account.isHardwareRequestFlag()!=null && account.isHardwareRequestFlag().equalsIgnoreCase("show")) {
						showHardwareReqFlag = "true";
					} 
					
					//Ends for JAN RELEASE catalog entitlement 
					
					//LBS Flags
					if (account.isLbsDisplayWeb()) {
						//showFleetManagerFlag = "true";
						createMapSRFlag = "true";
					}
				}
				acctRequestAccess.setCreateSuppliesReqFlag(createSuppliesReqFlag);
				acctRequestAccess.setCreateServiceReqFlag(createServiceReqFlag);
				acctRequestAccess.setCreateAccountMgmtReqFlag(createAcctMgmtReqFlag);
				
				acctRequestAccess.setShowSuppliesReqFlag(showSuppliesReqFlag);
				acctRequestAccess.setShowServiceReqFlag(showServiceReqFlag);
				acctRequestAccess.setShowAccountMgmtReqFlag(showAcctMgmtReqFlag);
				
				//Added for Hardware Request on MPS2.1
				acctRequestAccess.setCreateHardwareReqFlag(createHardwareReqFlag);
				acctRequestAccess.setShowHardwareReqFlag(showHardwareReqFlag);
				//acctRequestAccess.setShowFleetManagerFlag(showFleetManagerFlag);
				acctRequestAccess.setCreateMapSRFlag(createMapSRFlag);
				
				
				logger.debug("MPS*** Verify SHOW Account Level Flags Starts"); 
				logger.debug(
						"	SHOW Supplies Req:"+acctRequestAccess.getShowSuppliesReqFlag()+
						"	SHOW Service Req:"+acctRequestAccess.getCreateServiceReqFlag()+
						"	SHOW Account Management Req:"+acctRequestAccess.getCreateAccountMgmtReqFlag());
				logger.debug("NPS*** Verify Create Account Level Flags Ends");
				
				logger.debug("NPS*** Verify Create Account Level Flags Starts"); 
				logger.debug(
						"	CREATE Supplies Req:"+acctRequestAccess.getCreateSuppliesReqFlag()+
						"	CREATE Service Req:"+acctRequestAccess.getCreateServiceReqFlag()+
						"	CREATE Account Management Req:"+acctRequestAccess.getCreateAccountMgmtReqFlag()+
						"	CREATE Map SR Flag :"+acctRequestAccess.getCreateMapSRFlag()+
						"	show Fleet Manager Flag :"+acctRequestAccess.getFleetManagerFlag());
				logger.debug("NPS*** Verify Create Account Level Flags Ends");
				
				
			 
	    
	    logger.debug("[out verifyRequestAccessFlag4Acct]");
		return acctRequestAccess;
	}
	
	

	public static String getEntitlementStatus(HttpServletRequest request, String entitlementShortDesc,List<String> userRoleList,
			Map<String, EntitlementRoleMapping> roles4EntitlementMap) throws Exception{
		String entitlementStatus = "False";
		HttpSession session =request.getSession();
		
			Map<String, String> entitlementMap = EntitlementRolesUtil.retrieveEntitlement4User(userRoleList, (HashMap)roles4EntitlementMap);	
			entitlementStatus = (String)entitlementMap.get(entitlementShortDesc);
		
				
		return entitlementStatus;
	}
	
	private static Map<String, EntitlementRoleMapping> getRolesForEntitlement() throws BeansException, Exception{
		HashMap<String, EntitlementRoleMapping> roles4EntitlementMap =((EntitlementRoleMappingService)ApplicationContextProvider.getApplicationContext().getBean("entitlementRoleMappingService")).retrieveRoles4Entitlement();
		return roles4EntitlementMap;
	}
	
	/**.
	 * Returns user type if the user is vendor, customer or partner
	 * 
	 * @param session PortletSession
	 * @return boolean
	 * @author Wipro
	 */
	public static String getUserType(String ldapUserType,List<String> userRoleList ){
		
		logger.debug("in getUserType ldapUserType="+ldapUserType);
		String userType="";
		if (ldapUserType != null && ldapUserType.equalsIgnoreCase("Partner")){
			
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
	
}
