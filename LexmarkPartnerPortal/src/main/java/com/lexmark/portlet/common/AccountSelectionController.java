/**
 * Copyright@ 2013. This document has been created & written by
 * Wipro technologies for Lexmark and this is copyright to Lexmark
 *
 * File         	: AccountSelectionController.java
 * Package     		: com.lexmark.portlet.common
 * Creation Date 	: 
 *
 * Modification History:
 *
 * ---------------------------------------------------------------------
 * Author 			Date				Version  		Comments
 * ---------------------------------------------------------------------
 *
 */
package com.lexmark.portlet.common;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.lexmark.constants.LexmarkPPConstants;
import com.lexmark.contract.PartnerAccountListContract;
import com.lexmark.domain.Account;
import com.lexmark.framework.logging.LEXLogger;
import com.lexmark.result.PartnerAccountListResult;
import com.lexmark.service.api.CrmSessionHandle;
import com.lexmark.service.api.GlobalService;
import com.lexmark.util.PerformanceUtil;
import com.lexmark.util.ContractFactory;
import com.lexmark.util.JsonUtil;
import com.lexmark.util.ObjectDebugUtil;
import com.lexmark.util.PerformanceConstant;
import com.lexmark.util.PortalSessionUtil;

/**
 * @author wipro 
 * @version 2.1 
 */
@Controller
@RequestMapping("VIEW")
public class AccountSelectionController {
	
	private static LEXLogger LOGGER = LEXLogger.getLEXLogger(AccountSelectionController.class);
	private static Logger perfLogger = LogManager.getLogger("performance");
	@Autowired
	private GlobalService globalService;
	
	
	
	/**
	 * This method let us open the Account Selection Popup page for MPS
	 * @param renderRequest 
	 * @param model 
	 * @return string 
	 */
	@RequestMapping(params="action=showAccountPopup")
	public String showAccountSelectionPopup(RenderRequest renderRequest,Model model){
		
		model.addAttribute("mdmLevel", PortalSessionUtil.getMdmLevel(renderRequest.getPortletSession()));
		LOGGER.debug("Enter account selection popup");
		return "accountSelection/selectAccountPopUp";
	}
	
	/**
	 * This method let us retrieve the account lists for account selection popup
	 * @param model 
	 * @param resourceRequest 
	 * @param resourceResponse 
	 * @return string 
	 */
	@ResourceMapping(value="populateAccountList")
	public String retrieveAccountList(Model model,ResourceRequest resourceRequest,ResourceResponse resourceResponse){
		final String METH_RETRIEVEACCOUNTLIST="retrieveAccountList";
		LOGGER.enter(this.getClass().getSimpleName(), METH_RETRIEVEACCOUNTLIST);
		
		
		PartnerAccountListContract partnerAccountListContract = ContractFactory
															  .getPartnerAccountListContract(resourceRequest);
		
		
		
		
		
		
	
		LOGGER.info("start printing lex logger");
		ObjectDebugUtil.printObjectContent(partnerAccountListContract, LOGGER);
		LOGGER.info("end printing lex loggger");
		
		
		PartnerAccountListResult partnerAccountListResult=null;
		try{
			partnerAccountListResult=amindAccountListCall(partnerAccountListContract);
		
		}catch(Exception e){
			LOGGER.info("Error occured while retrieving Account List when request "+e.getMessage());
		}
		
		
		
		model.addAttribute(LexmarkPPConstants.STARTPOS, "0");
		model.addAttribute(LexmarkPPConstants.TOTALCOUNT,partnerAccountListResult.getAccountList().size() );	
		model.addAttribute("accountList", partnerAccountListResult.getAccountList());
		resourceResponse.setContentType(LexmarkPPConstants.XMLCONTENT);
		LOGGER.exit(this.getClass().getSimpleName(), METH_RETRIEVEACCOUNTLIST);
		return LexmarkPPConstants.ACCOUNTSELECTIONLISTXML;
		
	}
	
	/**
	 * This method let us set the current account details
	 * @param resourceRequest 
	 * @param resourceResponse 
	 */
	@ResourceMapping(value="setCurrentAccountValuesToSession")
	public void setCurrentAccount(ResourceRequest resourceRequest,ResourceResponse resourceResponse){
		final String METH_SETCURRENTACCOUNT="setCurrentAccount";
		LOGGER.enter(this.getClass().getSimpleName(), METH_SETCURRENTACCOUNT);
			
	
		PortletSession portletSession = resourceRequest.getPortletSession();
		Map<String,String> accountSelectionDetails=(Map<String,String>)portletSession.getAttribute(LexmarkPPConstants.PARTNER_ACCOUNT_SELECTION_DETAILS,PortletSession.APPLICATION_SCOPE);
		if(accountSelectionDetails==null){
			accountSelectionDetails=new HashMap<String, String>();
		}
		
		Enumeration<String> paramNames=resourceRequest.getParameterNames();
		
		while(paramNames.hasMoreElements()){
			String paramName=paramNames.nextElement();
			LOGGER.debug("session:"+paramName+ " " +resourceRequest.getParameter(paramName) );
			accountSelectionDetails.put(paramName, resourceRequest.getParameter(paramName));
		}
		
		portletSession.setAttribute(LexmarkPPConstants.PARTNER_ACCOUNT_SELECTION_DETAILS, accountSelectionDetails,PortletSession.APPLICATION_SCOPE);
			
		
		LOGGER.debug("request values"+accountSelectionDetails.toString());
		
		try{
			PrintWriter out = resourceResponse.getWriter();
			resourceResponse.setContentType("text/plain");
			out.print("success");
			out.flush();
			out.close();
		}catch(IOException ie){
			LOGGER.error("exception occured "+ie.getMessage());
		}
		LOGGER.exit(this.getClass().getSimpleName(), METH_SETCURRENTACCOUNT);
	}
	/**
	 * This method let us retrieve the organization Id 
	 * @param request  
	 * @param response
	 * @param partnerAccID     
	 */
	@ResourceMapping(value="retrieveOrganizationIdForHW")
	public void getDebriefOrgId(ResourceRequest request,ResourceResponse response,
								@RequestParam("partnerAccountID")String partnerAccID){
		LOGGER.enter(this.getClass().getSimpleName(), "getDebriefOrgId");
		StringBuffer json=new StringBuffer("{");
		final String jsonKey="orgId";
		if(StringUtils.isBlank(partnerAccID)){
			JsonUtil.appendToJSON(jsonKey, "", json, false);
			json.append("}");
			writeToResponse(response,json.toString());
		}else{
		
				PartnerAccountListContract 
				partnerAccountListContract = ContractFactory
										  .getPartnerAccountListContract(request);
			
				LOGGER.info("start printing lex logger");
				ObjectDebugUtil.printObjectContent(partnerAccountListContract, LOGGER);
				LOGGER.info("end printing lex loggger");
				
				PartnerAccountListResult partnerAccountListResult=null;
				try{
					partnerAccountListResult=amindAccountListCall(partnerAccountListContract);
				
				}catch(Exception e){
					JsonUtil.appendToJSON(jsonKey, "", json, false);
					LOGGER.info("Error occured while retrieving Account List when request "+e.getMessage());
				}
				if(partnerAccountListResult.getAccountList()!=null){
					List<Account> accounts=partnerAccountListResult.getAccountList();
					for(Account account:accounts){
						if(account.getAccountId().equalsIgnoreCase(partnerAccID)){
							JsonUtil.appendToJSON(jsonKey, StringUtils.isNotBlank(account.getOrganizationID())==true?account.getOrganizationID():"", json, false);
							break;
						}
					}
				}
				json.append("}");
				writeToResponse(response,json.toString());
		}
		
	}
	/**
	 * This method let us set the String to response
	 * @param resourceResponse     
	 * @param value      
	 */
	private void writeToResponse(ResourceResponse resourceResponse ,String value){
		try {
			final PrintWriter out = resourceResponse.getWriter();
			resourceResponse.setProperty("Cache-Control", "max-age=0,no-cache,no-store");
			resourceResponse.setProperty("Expires", "max-age=0,no-cache,no-store");
			resourceResponse.setContentType("UTF-8");
			out.write(value);
			out.flush();
			out.close();
		} catch (IOException e) {
			LOGGER.error("IOException while invoking response#getWriter(),"
					+ e.getMessage());
		}
	}
	
	/**
	 * This method let us retrieve partner account list
	 * @param partnerAccountListContract     
	 * @return  PartnerAccountListResult  
	 */
	private PartnerAccountListResult  amindAccountListCall(PartnerAccountListContract partnerAccountListContract) throws Exception{
		LOGGER.debug("-------------retrieveSiebelAccountList started---------");
		PartnerAccountListResult partnerAccountListResult=null; 
		/*
		 * This section will execute if
		 * request is from cAtalog page
		 * */
	
		long timeBeforeCall=System.currentTimeMillis();
		partnerAccountListResult=globalService.retrievePartnerAccountList(partnerAccountListContract);
		
		PerformanceUtil.calcTime(perfLogger, PerformanceConstant.MSG_RETRIEVE_PARTNERACCOUNTLIST, timeBeforeCall,System.currentTimeMillis(), PerformanceConstant.SYSTEM_SIEBEL,partnerAccountListContract);
		
		return partnerAccountListResult;
	}

}
