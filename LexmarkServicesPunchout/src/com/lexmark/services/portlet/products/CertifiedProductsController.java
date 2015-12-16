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
	
	private static String KAISER_ACNT = ControllerUtil.getConfigProperties().getProperty("ariba.kaiser");
	private static String REPUBLIC_ACNT = ControllerUtil.getConfigProperties().getProperty("ariba.republic");
	
	
	
	/**
	 * @param request 
	 * @param response 
	 * @param model 
	 * @return String 
	 */
	@RequestMapping
	public String showDefaultView(RenderRequest request, RenderResponse response,Model model){
		LOGGER.debug("[ in  showDefaultView ]");
		//List<PunchoutAccount> acntList = allAccountInformation.getAllAccountList();
		
		PortletSession session = request.getPortletSession();
		String currURL=response.createRenderURL().toString();
		boolean fromAriba = currURL.indexOf("aribaParamUrl")!=-1;
		LOGGER.debug("currURL==="+currURL);
		HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));
		String aribaParamURL = httpReq.getParameter("aribaParamUrl");
		LOGGER.debug("aribaParamURL in certified products controller "+aribaParamURL);
		if(null!=aribaParamURL){
			fromAriba=true;
		}
		String acntType = null;
		boolean isKaiser = currURL.indexOf("kaiser")!=-1;
		if(!fromAriba){
			session.setAttribute("fromAriba", false, PortletSession.APPLICATION_SCOPE);	
			if(isKaiser){
				LOGGER.debug("account is kaiser");
				acntType = "KAISER";
			}
			else{
				LOGGER.debug("account is republic");
				acntType = "REPUBLIC";
			}
		}
		else{
			ControllerUtil.setAribaParam(request, session);
			String supplierId = (String) session.getAttribute("supplierId", PortletSession.APPLICATION_SCOPE) != null?(String) session.getAttribute("supplierId", PortletSession.APPLICATION_SCOPE):"";
			LOGGER.debug("supplierId::::"+supplierId);
			
			if(supplierId.equalsIgnoreCase(KAISER_ACNT))
			{
				acntType = "KAISER";
			}
			else if(supplierId.equalsIgnoreCase(REPUBLIC_ACNT))
			{
				acntType = "REPUBLIC";
			}
			session.setAttribute("fromAriba", true, PortletSession.APPLICATION_SCOPE);	
		}
		session.setAttribute(PunchoutConstants.ACNT_TYPE, acntType, PortletSession.APPLICATION_SCOPE);
		model.addAttribute(PunchoutConstants.ACNT_TYPE, acntType);
		//model.addAttribute(PunchoutConstants.ACCOUNT_LIST,acntList);
		LOGGER.debug("[ out  showDefaultView ]");
	
		return "products/certifiedProducts";
	}
	
	
}

