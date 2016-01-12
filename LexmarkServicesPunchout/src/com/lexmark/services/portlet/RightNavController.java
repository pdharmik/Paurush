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
	
	private static String KAISER_ACNT = ControllerUtil.getConfigProperties().getProperty("ariba.kaiser");
	private static String REPUBLIC_ACNT = ControllerUtil.getConfigProperties().getProperty("ariba.republic");
	   
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
		String currURL=response.createRenderURL().toString();		
		boolean fromAriba = false;
		String supplierId="";
		LOGGER.debug("currURL==="+currURL);
		HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));
		String aribaParamURL = httpReq.getParameter("aribaParamUrl");
		LOGGER.debug("aribaParamURL in right nav controller "+aribaParamURL);
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
			 supplierId = (String) session.getAttribute("supplierId", PortletSession.APPLICATION_SCOPE) != null?(String) session.getAttribute("supplierId", PortletSession.APPLICATION_SCOPE):"";
			
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
			
			if(session.getAttribute(PunchoutConstants.ACCOUNT_LIST, PortletSession.APPLICATION_SCOPE) != null){
				session.setAttribute(PunchoutConstants.ACCOUNT_LIST, null, PortletSession.APPLICATION_SCOPE);			
			}
			
			if(session.getAttribute(PunchoutConstants.ACNT_TYPE, PortletSession.APPLICATION_SCOPE) != null){
				session.setAttribute(PunchoutConstants.ACNT_TYPE, null, PortletSession.APPLICATION_SCOPE);			
			}
			
			
		
		session.setAttribute(PunchoutConstants.ACNT_TYPE, acntType, PortletSession.APPLICATION_SCOPE);
		model.addAttribute(PunchoutConstants.ACNT_TYPE, acntType);
		model.addAttribute("fromAriba", fromAriba);
		
		/*allAccountInformation.acntType = "Deutsche Postbank AG";
		allAccountInformation.forceRefresh();*/
		List<PunchoutAccount> acntList = allAccountInformation.getAllAccountList(supplierId);
		session.setAttribute(PunchoutConstants.ACCOUNT_LIST, acntList, PortletSession.APPLICATION_SCOPE);
		request.setAttribute("callType", "showDefaultView");
		
		session.setAttribute(PunchoutConstants.CART_SESSION,ControllerUtil.initShoppingCart());
		LOGGER.debug(("[ Before Account Call]"));
		
		return "requests/rightNavHome";
	}	
}