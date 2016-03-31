package com.lexmark.services.portlet;

import java.util.HashMap;
import java.util.List;

import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lexmark.domain.PunchoutAccount;
import com.lexmark.service.api.OrderSuppliesCatalogService;
import com.lexmark.services.constants.PunchoutConstants;
import com.lexmark.services.mock.GenerateMockData;
import com.lexmark.services.servlet.LoadAccountInformation;
import com.lexmark.services.util.ControllerUtil;
import com.liferay.portal.util.PortalUtil;

/**
 * Copyright@ 2013. This document has been created & written by
 * Wipro technologies for Lexmark and this is copyright to Lexmark
 *
 * File         	: RequestHistoryController.java
 * Package     		: com.lexmark.portlet
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
 * This controller is suppose to provide 
 * the default page for Right Navigation
 * */
/**
 * @author wipro
 * @version 1.0
 */
@Controller
@RequestMapping("VIEW")
public class RightNavController {
	private static Logger LOGGER = LogManager
			.getLogger(RightNavController.class);
	
	@Autowired
	private OrderSuppliesCatalogService orderSuppliesCatalogService;
	
	@Autowired
	private LoadAccountInformation allAccountInformation;
	
	   
	/**
	 * @param request 
	 * @param response 
	 * @param model 
	 * @param session 
	 * @return String 
	 */
	@RequestMapping
	public String showDefaultView(RenderRequest request, RenderResponse response,Model model,PortletSession session) {
		LOGGER.debug(("[ In  showDefaultView ]"));
		System.out.println("Supplier Cookie" +session.getAttribute("supplierCookie" , PortletSession.APPLICATION_SCOPE));
		session.setAttribute(PunchoutConstants.CART_SESSION,ControllerUtil.initShoppingCart());
		LOGGER.debug(("[ Before Account Call]"));
		
		return "requests/rightNavHome";
	}	
}