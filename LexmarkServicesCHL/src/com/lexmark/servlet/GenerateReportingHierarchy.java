package com.lexmark.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.lexmark.CustomerHierarchyGenerator;
import com.lexmark.LexmarkCHLConstants;
import com.lexmark.constants.LexmarkConstants;
import com.lexmark.contract.DAUserListContract;
import com.lexmark.contract.DunsNumberContract;
import com.lexmark.contract.LocationReportingHierarchyContract;
import com.lexmark.contract.PartnerAccountListContract;
import com.lexmark.contract.ServicesUserContract;
import com.lexmark.contract.SiebelAccountListContract;
import com.lexmark.domain.Account;
import com.lexmark.domain.AccountCHLRootNode;
import com.lexmark.domain.CHLNode;
import com.lexmark.domain.MDMAccountNode;
import com.lexmark.domain.ServicesUser;
import com.lexmark.domain.SiebelAccountNode;
import com.lexmark.result.AssetReportingHierarchyResult;
import com.lexmark.result.DAUserListResult;
import com.lexmark.result.DunsNumberResult;
import com.lexmark.service.api.DeviceService;
import com.lexmark.service.api.GlobalService;
import com.lexmark.service.api.ServiceRequestService;
import com.lexmark.service.api.UserAdminService;
import com.lexmark.service.api.UserService;
import com.lexmark.util.ContractFactory;
import com.lexmark.util.PropertiesMessageUtil;
import com.lexmark.util.StringUtil;

public class GenerateReportingHierarchy extends HttpServlet {

	private static final long serialVersionUID = -4895221157616044236L;

	private static Logger logger = Logger.getLogger(GenerateReportingHierarchy.class);
	
	private static final String CONJUCTION = "_";
	
	private HttpSession session;
	private DeviceService deviceService;
	private ServiceRequestService serviceRequestService;
    private GlobalService  globalService;
	private UserAdminService userAdminService;
	private UserService userService;
	private CustomerHierarchyGenerator customerHierarchyGenerator;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		setSession(request.getSession());
		String chlNodeTreeXML = null;
		String selectedNodeId = request.getParameter("id"); // node id that user selects from popup
		
		// initialize customer hierarchy tree in IDM popup
		if (StringUtil.isStringEmpty(selectedNodeId)) {
			
			String operId = request.getParameter(LexmarkCHLConstants.OPERATION_ID);
			String sessionNum = request.getParameter(LexmarkCHLConstants.SESSION_NUMBER);			
			String type = "";
			if(request.getParameter(LexmarkCHLConstants.TYPE)!=null){
				type = request.getParameter(LexmarkCHLConstants.TYPE);
			}
			DAUserListResult daUserListResult = retrieveDAUserList(operId, sessionNum);
			session.setAttribute("daUserListResult", daUserListResult);
			
			// generate customer hierarchy tree xml
			try {
				chlNodeTreeXML = generateTreeXML(daUserListResult, request.getLocale(),type);
			} catch (Exception e) {
				e.printStackTrace();
				throw new IOException("Failed to initialize customer hierarchy tree for users:" +
						daUserListResult.getUserNumberList());
			}
			
		} else {// load child nodes of the node that user selects in IDM popup
			try {
				chlNodeTreeXML = generateChildNodesXML(selectedNodeId);
			} catch (Exception e) {
				if (logger.isDebugEnabled()) {
					logger.debug("Failed to generateChildNodesXML for selectedNodeId:" + selectedNodeId);
				}
				e.printStackTrace();
				throw new IOException("Failed to generate Child nodes of selected Node:" + selectedNodeId);
			}
		}
		
		// print xml content
		response.setContentType("text/xml; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.print(chlNodeTreeXML);
		out.flush();
		out.close();		
	}
	
	// retrieve DA user list by operId and sessionNum
	private DAUserListResult retrieveDAUserList(String operId, String sessionNum) {
		DAUserListContract contract = ContractFactory.getDAUserListContract(Integer.valueOf(operId), sessionNum);
		DAUserListResult result = null;
		try {
			result = getUserAdminService().retrieveDAUserList(contract);

			if (result != null && result.getMdmLevel() != null) {
				List<String> userNumberList = result.getUserNumberList();
	   			// retrieve services user only when one usernumber returned which is in Edit mode
	   			if (result.getUserMode().equalsIgnoreCase(LexmarkConstants.USER_MODE_EDIT) &&
	   					userNumberList.size() == 1) {
	   				String preRestrictedNodeId = null;
	   				ServicesUserContract servicesUserContract = ContractFactory.getServicesUserContract(userNumberList.get(0));
	   				//servicesUserContract.setEmailAddress("test@liferay.com");
	   				ServicesUser user = getUserService().retrieveServicesUser(servicesUserContract).getServicesUser();
	   				if (!StringUtil.isStringEmpty(user.getChlNodeId())) {
	   					preRestrictedNodeId = user.getChlNodeId();
	   				} else if (!StringUtil.isStringEmpty(user.getMdmId()) && !StringUtil.isStringEmpty(user.getMdmLevel())){
	   					preRestrictedNodeId = user.getMdmId() + "_" + user.getMdmLevel();
	   				}
	   				result.setNodeId(preRestrictedNodeId);
	   				if (logger.isDebugEnabled()) {
	   					logger.debug("UserNumber" + result.getUserNumberList().get(0) + "'s current authorization is " + preRestrictedNodeId);
	   				}
	   			}
			} else {
				logger.error("Can not find any record with jsession_num:" +
						contract.getJSessionNum() + ", operid:" + contract.getOperId());
				throw new Exception("Can not find any info by the given parameters!");
			}
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
		return result;
	}
	
	private String generateTreeXML(DAUserListResult daUserListResult, Locale locale, String type) throws Exception {
		String treeXML;
		String mdmLevel = daUserListResult.getMdmLevel();
		List<String> mdmIdList = daUserListResult.getMdmIdList();
		
		String restrictedCHLNodeId = daUserListResult.getNodeId();
		
		AccountCHLRootNode rootNode = new AccountCHLRootNode();
		rootNode.setNodeId(LexmarkConstants.ROOT_NODE_ITEM_ID);
		rootNode.setName(PropertiesMessageUtil.getPropertyMessage(
				LexmarkConstants.IDM_MESSAGE_BUNDLE_NAME, "rootNodeName", locale));
		if (LexmarkConstants.MDM_LEVEL_ACCOUNT.equalsIgnoreCase(mdmLevel)) {
			List<MDMAccountNode> mdmAccountNodeList = new ArrayList<MDMAccountNode>();
			for (String mdmId : mdmIdList) {
				MDMAccountNode mdmAccountNode = new MDMAccountNode();
				mdmAccountNode.setNodeId(mdmId + CONJUCTION + LexmarkConstants.MDM_LEVEL_ACCOUNT);
				// TODO: Need to get the real account number of MDM Account, and set as name
				mdmAccountNode.setName(mdmId + CONJUCTION + LexmarkConstants.MDM_LEVEL_ACCOUNT);
				mdmAccountNode.setSiebelAccountNodeList(getSiebelAccountNodeList(mdmId, mdmLevel, type, restrictedCHLNodeId));
				mdmAccountNodeList.add(mdmAccountNode);
			}
			rootNode.setMdmAccountNodeList(mdmAccountNodeList);
		} else if (LexmarkConstants.MDM_LEVEL_GLOBAL.equalsIgnoreCase(mdmLevel) ||
				LexmarkConstants.MDM_LEVEL_DOMESTIC.equalsIgnoreCase(mdmLevel) ||
				LexmarkConstants.MDM_LEVEL_LEGAL.equalsIgnoreCase(mdmLevel)) {
			rootNode.setSiebelAccountNodeList(getSiebelAccountNodeList(mdmIdList.get(0), mdmLevel, type, restrictedCHLNodeId));
		}
		
		treeXML = getCustomerHierarchyGenerator().generateTreeXML(rootNode, restrictedCHLNodeId);
		return treeXML;
	}
	
	private String generateChildNodesXML(String selectedNodeId) throws Exception {
		logger.debug("-------------generateChildNodesXML started---------NodeId=" + selectedNodeId);
		if (StringUtil.isStringEmpty(selectedNodeId)) {
			if (logger.isDebugEnabled()) {
				logger.debug("Failed to generateChildNodesXML, because the passed selectedNodeId is empty.");
			}
			throw new Exception("The passed selectedNodeId is empty.");
		}
		
		//Amind will perform siebel search to retrieve the child node list of given nodeId
		LocationReportingHierarchyContract contract = ContractFactory.
				getCustomerHierarchyChildNodeContract(selectedNodeId);

		AssetReportingHierarchyResult result = getDeviceService().retrieveAssetReportingHierarchy(contract);

		return getCustomerHierarchyGenerator().generateChildNodesXML(result.getChlNodeList(), selectedNodeId);
	}
	
	private List<CHLNode> getCHLNodeList(String mdmId, String mdmLevel) throws Exception {
		List<CHLNode> chlNodeList = new ArrayList<CHLNode>();
		LocationReportingHierarchyContract contract = ContractFactory.
				getCustomerHierarchyTreeContract(mdmId, mdmLevel, null);
		chlNodeList = getDeviceService().retrieveFullAssetReportingHierarchy(contract).getChlNodeList();
		return chlNodeList;
	}
	
	private List<SiebelAccountNode> getSiebelAccountNodeList(String mdmId,
			String mdmLevel, String type, String restrictedCHLNodeId) throws Exception {
		List<SiebelAccountNode> siebelAccountNodeList = new ArrayList<SiebelAccountNode>();
		SiebelAccountListContract siebelAccountListContract = ContractFactory.
				getSiebelAccountListContract(mdmId, mdmLevel);
		
		PartnerAccountListContract partnerAccountListContract= ContractFactory.
				getPartnerAccountListContract(mdmId, mdmLevel);
		
		if (mdmLevel.equalsIgnoreCase(LexmarkConstants.GLOBAL) || mdmLevel.equalsIgnoreCase(LexmarkConstants.DOMESTIC)){
			DunsNumberContract dunsNumberContract = new DunsNumberContract();
			dunsNumberContract.setMdmId(mdmId);
			dunsNumberContract.setMdmLevel(mdmLevel);
			//DunsNumberResult dunsNumberResult = getGlobalService().retrieveDunsNumber(dunsNumberContract);
			siebelAccountListContract.setMdmId(mdmId);
			//partnerAccountListContract.setMdmId(dunsNumberResult.getDunsNumber());
		}
		
		List<Account> siebelAccountList = null;
		if(type!= null && !StringUtil.isEmpty(type)){
			if(type.equalsIgnoreCase("customer")){
				siebelAccountList = getServiceRequestService().retrieveSiebelAccountList(siebelAccountListContract).getAccountList();
			}else if(type.equalsIgnoreCase("partner")){
				siebelAccountList = getGlobalService().retrievePartnerAccountList(partnerAccountListContract).getAccountList();
			}else{
				siebelAccountList = getServiceRequestService().retrieveSiebelAccountList(siebelAccountListContract).getAccountList();
			}
		}
		
        for (Account siebelAccount : siebelAccountList) {
        	SiebelAccountNode siebelAccountNode = new SiebelAccountNode();
        	siebelAccountNode.setNodeId(siebelAccount.getAccountId() + CONJUCTION + LexmarkConstants.MDM_LEVEL_SIEBEL);
        	siebelAccountNode.setName(siebelAccount.getAccountName());
        	
        	// prepare chl nodes, if the access of user has been authorized.
        	if (!StringUtil.isStringEmpty(restrictedCHLNodeId)) {
            	siebelAccountNode.setChlNodeList(getCHLNodeList(siebelAccount.getAccountId(),
            			LexmarkConstants.MDM_LEVEL_SIEBEL));
        	}
        	siebelAccountNodeList.add(siebelAccountNode);
        }
		return siebelAccountNodeList;
	}
	
	private DeviceService getDeviceService() {
		if (deviceService == null) {
			WebApplicationContext springAppContext = WebApplicationContextUtils.getWebApplicationContext(getSession().getServletContext());
			deviceService = springAppContext.getBean("deviceService", DeviceService.class);
		}
		return deviceService;
	}
	
	private ServiceRequestService getServiceRequestService() {
		if (serviceRequestService == null) {
			WebApplicationContext springAppContext = WebApplicationContextUtils.getWebApplicationContext(getSession().getServletContext());
			serviceRequestService = springAppContext.getBean("serviceRequestService", ServiceRequestService.class);
		}
		return serviceRequestService;
	}
	
	private GlobalService getGlobalService() {
		if (globalService == null) {
			WebApplicationContext springAppContext = WebApplicationContextUtils.getWebApplicationContext(getSession().getServletContext());
			globalService = springAppContext.getBean("globalService", GlobalService.class);
		}
		return globalService;
	}
	
	private UserAdminService getUserAdminService() {
		if (userAdminService == null) {
			WebApplicationContext springAppContext = WebApplicationContextUtils.getWebApplicationContext(getSession().getServletContext());
			userAdminService = springAppContext.getBean("userAdminService", UserAdminService.class);
		}
		return userAdminService;
	}
	
	private UserService getUserService() {
		if (userService == null) {
			WebApplicationContext springAppContext = WebApplicationContextUtils.getWebApplicationContext(getSession().getServletContext());
			userService = springAppContext.getBean("userService", UserService.class);
		}
		return userService;
	}
	
	private void setSession(HttpSession session) {
		this.session = session;
	}
	
	private HttpSession getSession() {
		return session;
	}
	
	private CustomerHierarchyGenerator getCustomerHierarchyGenerator() {
		if (customerHierarchyGenerator == null) {
			customerHierarchyGenerator = new CustomerHierarchyGenerator();
		}
		return customerHierarchyGenerator;
	}
}
