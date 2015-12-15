package com.lexmark.services.portlet;

import javax.portlet.PortletSession;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;
import org.w3c.dom.Document;

import com.lexmark.services.constants.PunchoutConstants;
import com.lexmark.services.dao.PunchoutItemDAO;
import com.lexmark.services.domain.SessionInformation;
import com.lexmark.services.util.ControllerUtil;
import com.liferay.portal.util.PortalUtil;


@Controller
@RequestMapping("VIEW")
public class RequestSubmitController {
	
	private static Logger LOGGER = LogManager.getLogger(RequestSubmitController.class);
	/*@Autowired
	private CreateServiceRequestB2B createServiceRequest;*/
	
	
	
	/**
	 * @param request 
	 * @param response 
	 */
	@ResourceMapping("getCXMLencodedText")
	private void generateCXML(ResourceRequest request,ResourceResponse response){
		 HttpServletRequest httpServletRequest = PortalUtil.getHttpServletRequest(request);
         HttpSession webSession = httpServletRequest.getSession();
         //String formPostURL = (String) webSession.getAttribute("formPostURL") != null?(String) webSession.getAttribute("formPostURL"):"";
         
		 PortletSession session = request.getPortletSession();
		 SessionInformation information=ControllerUtil.populateSessionInformation(session);
		 String formPostURL = (String) information.getFormPostURL() != null?(String) information.getFormPostURL():"";
		 StringBuffer html=new StringBuffer("<form id=\"cXmlForm\" method=\"post\"");
		 html.append("action=\"");
		 html.append(formPostURL);
		 html.append("\">");
		 html.append("<input type=\"hidden\" name=\"cxml-base64\" value=\"");
		 String encodedText=null; 
		 try{
		 Document doc=new PunchoutItemDAO().createCXML(information, request.getParameter("cartType"));
		 encodedText=ControllerUtil.generateCXMLResponse(doc);
		 }catch(Exception e){
			 LOGGER.debug("Exception occured" + e.getMessage());
			 //e.printStackTrace();
			 
		 }
		 html.append(encodedText);
		 html.append("\"/></form>");
		 session.setAttribute(PunchoutConstants.CART_SESSION, ControllerUtil.initShoppingCart());
		ControllerUtil.prepareResponse(response, html.toString());
		
		 
	}
	
	/**
	 * @param request 
	 * @param response 
	 */
	@ResourceMapping("createServiceRequest")
	private void createSR(ResourceRequest request,ResourceResponse response){
		/*HttpServletRequest httpServletRequest = PortalUtil.getHttpServletRequest(request);
        HttpSession webSession = httpServletRequest.getSession();*/
		PortletSession session = request.getPortletSession();
		SessionInformation information=ControllerUtil.populateSessionInformation(session);
		 try{
		// createServiceRequest.createServiceRequestB2B(information,ContractFactory.createServiceRequestContract());
		 
		 }catch(Exception e){
			 LOGGER.debug("Exception occured" + e.getMessage());
			 //e.printStackTrace();
			 
		 }
		 
	}
	
}
