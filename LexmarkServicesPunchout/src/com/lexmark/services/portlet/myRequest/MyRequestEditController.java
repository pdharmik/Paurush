package com.lexmark.services.portlet.myRequest;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ActionMapping;

import com.lexmark.services.servlet.LoadAccountInformation;

/**
 * Copyright@ 2013. This document has been created & written by
 * Wipro technologies for Lexmark and this is copyright to Lexmark
 *
 * File         	: MyRequest.java
 * Package     		: com.lexmark.portlet.myRequest
 * Creation Date 	: 
 *
 * Modification History:
 *
 * ---------------------------------------------------------------------
 * Author 			Date				Version  		Comments
 * ---------------------------------------------------------------------
 *
 */

/**
 * @author wipro 
 * @version 1.0 
 */
@Controller
@RequestMapping("EDIT")
public class MyRequestEditController {

	private static Logger LOGGER = LogManager.getLogger(MyRequestController.class);
	
	@Autowired
	private LoadAccountInformation allAccountInformation;
	
	/**
	 * @return string 
	 */
	@RequestMapping
	public String showDefaultView(){
		LOGGER.debug("[ In  showDefaultView ]");
		
			LOGGER.debug(String.format("[ account list is not null size of account is %s ] ",allAccountInformation.getAllAccountList().size()));
			
			/*for(Account account:allAccountInformation.getAllAccountList()){
				LOGGER.debug(String.format(" [ account id = %s]",account.getAccountId()));
				LOGGER.debug(String.format(" [ sold to list  = %s]",account.getSoldToNumbers()));			
			}*/
		
		LOGGER.debug("[ Out  showDefaultView ]");	
		return "myRequest/leftNavLinks";
	}
	
	/**
	 * @param request 
	 * @param response 
	 */
	@ActionMapping(params="action=submitForm")
	public void processAction(ActionRequest request,ActionResponse response){
		LOGGER.debug("[ In  processAction ]");
		LOGGER.debug("[ Out  processAction ]");
	}
}

