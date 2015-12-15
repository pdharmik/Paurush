/**
* Copyright@ 2012. This document has been created & written by
 * Wipro technologies for Lexmark and this is copyright to Lexmark
 *
 * File         	: CustomerAccountInfoController
 * Package     		: com.lexmark.portlet
 * Creation Date 	: 
 *
 * Modification History:
 *
 * ---------------------------------------------------------------------
 * Author 			Date				Version  		Comments
 * ---------------------------------------------------------------------
 * Wipro						 		1.0             Initial Version
 *
 */
package com.lexmark.portlet;


import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.lexmark.contract.CustomerAccountListContract;
import com.lexmark.framework.logging.LEXLogger;
import com.lexmark.result.CustomerAccountListResult;
import com.lexmark.service.api.PartnerRequestsService;
import com.lexmark.util.ObjectDebugUtil;
import com.lexmark.util.ContractFactory;


@Controller
@RequestMapping("VIEW")
public class CustomerAccountInfoController {
	private static LEXLogger logger = LEXLogger.getLEXLogger(CustomerAccountInfoController.class);

	@Autowired
	private PartnerRequestsService partnerRequestsService;
	
	@RequestMapping(params = "action=customerAccountInfo")
	public String showCustomerAccountInfo(RenderRequest request,RenderResponse response,Model model) {
		logger.enter("CustomerAccountInfoController", "showCustomerAccountInfo");
		logger.exit("CustomerAccountInfoController", "showCustomerAccountInfo");
		return "claims/customerAccountInfoPopup";
	}
	
	@ResourceMapping("getCustomerAccountInfoList")
	public String getCustomerAccountInfoList(ResourceRequest request,
			ResourceResponse response,Model model) throws Exception {
		logger.enter("CustomerAccountInfoController", "getCustomerAccountInfoList");
		
		CustomerAccountListContract contract = ContractFactory.createCustomerAccountListContract(request);
		
		//Done by CI BRD13-10-01--START
		logger.info("start printing lex LOGGER");
		com.lexmark.util.ObjectDebugUtil.printObjectContent(contract, logger);
		logger.info("end printing lex loggger");
		//Done by CI BRD13-10-01--END
		
		CustomerAccountListResult customerAccountListResult = partnerRequestsService.retrieveCustomerAccountList(contract);
		
		model.addAttribute("customerAccountList", customerAccountListResult.getAccountList());
		response.setContentType("text/xml");

		logger.exit("CustomerAccountInfoController", "getCustomerAccountInfoList");
		
		return "claims/xml/customerAccountXML";
	}

	public void setPartnerRequestsService(
			PartnerRequestsService partnerRequestsService) {
		logger.enter("CustomerAccountInfoController", "setPartnerRequestsService");
		this.partnerRequestsService = partnerRequestsService;
		logger.exit("CustomerAccountInfoController", "setPartnerRequestsService");
	}

}
