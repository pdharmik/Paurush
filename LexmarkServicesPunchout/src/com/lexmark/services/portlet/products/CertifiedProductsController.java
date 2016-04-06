package com.lexmark.services.portlet.products;

import java.util.List;

import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lexmark.domain.PunchoutAccount;
import com.lexmark.services.constants.PunchoutConstants;
import com.lexmark.services.servlet.LoadAccountInformation;
import com.lexmark.services.util.ControllerUtil;
import com.liferay.portal.util.PortalUtil;

/**
 * Copyright@ 2013. This document has been created & written by
 * Wipro technologies for Lexmark and this is copyright to Lexmark
 *
 * File         	: CertifiedProductsController.java
 * Package     		: com.lexmark.services.portlet.products
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
@RequestMapping("VIEW")
public class CertifiedProductsController {

	private static Logger LOGGER = LogManager.getLogger(CertifiedProductsController.class);
	

	
	
	/**
	 * @param request 
	 * @param response 
	 * @param model 
	 * @return String 
	 */
	@RequestMapping
	public String showDefaultView(RenderRequest request, RenderResponse response,Model model){
		LOGGER.debug("[ in  showDefaultView ]");
		LOGGER.debug("[ out  showDefaultView ]");	
		return "products/certifiedProducts";
	}
	
	
}

