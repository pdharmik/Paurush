package com.lexmark.services.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

 

public class AribaRequestFilter implements Filter {
	private static Logger LOGGER = LogManager.getLogger(AribaRequestFilter.class);
	
    /**
     * @param request 
     * @param response 
     * @param chain 
     * @throws IOException 
     * @throws ServletException 
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
    	LOGGER.debug("[ In AribaRequestFilter ]");
    	HttpServletRequest httpRequest=(HttpServletRequest) request;
    	//HttpServletRequest httpResponse=(HttpServletRequest) request;
    	LOGGER.debug(String.format(" [ req url = %s ]",httpRequest.getRequestURL()));
    	String aribaSetup=null;
    	for(Cookie cookie:httpRequest.getCookies()){
    		LOGGER.debug(String.format("[ Cookie name is %s ]",cookie.getName()));
    		if(cookie.getName().equals("aribaSetup")){
    			LOGGER.debug("[Ariba Cookie found]");
    			aribaSetup=new String(Base64.decodeBase64(cookie.getValue().getBytes()));
    			LOGGER.debug(String.format("[ Ariba Setup String is = { %s}]",aribaSetup));
    			break;
    		}
    	}
    	
    	setupAribaConfig(aribaSetup,httpRequest.getSession());
    	
    	chain.doFilter(request,response);
    	LOGGER.debug("[ Out AribaRequestFilter ]");
    }
    /**
     * @param aribaSetup 
     * @param session 
     */
    public void setupAribaConfig(String aribaSetup,HttpSession session){
    	LOGGER.debug("[ In setupAribaConfig ]");
    	
    	if (aribaSetup != null) {
        	LOGGER.debug("[ Ariba Setup is not null ]");
           String[] aribaSetupValues = aribaSetup.split(";");
           for (int i = 0; i < aribaSetupValues.length; i++) {
              LOGGER.debug(String.format("[ Ariba setup [%s] value ] ", i));
              LOGGER.debug("="+aribaSetupValues[i]);
              String[] nameValue = aribaSetupValues[i].split("=");
              if (nameValue.length > 1){
            	  session.setAttribute(nameValue[0], nameValue[1]);  
              } 
           }
           String supplierId = (String) session.getAttribute("supplierId");
           if (supplierId != null) {
        	  LOGGER.debug("[ Need to setup Supplier Information ] ");
              /*CatalogDTO catalogDTO = PunchoutItemBO.getCatalogInfo(supplierId);
              session.setAttribute("cust_id", catalogDTO.getCustomerNumber());
              session.setAttribute("country", catalogDTO.getCountry());
              session.setAttribute("language", catalogDTO.getLanguage());
              session.setAttribute("siteId", "" + catalogDTO.getSiteId());
              session.setAttribute("siteName", catalogDTO.getSiteName());
              session.setAttribute("catalogURL", filterConfig.getServletContext().getInitParameter("catalogBase") + catalogDTO.getCatalogUrl());*/
           }
        } else {
        	LOGGER.debug("[ Ariba Setup is NULL ]");

        }
    	LOGGER.debug("[ Out setupAribaConfig ]");
    }
	/**
	 * destroy method 
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @param arg0 
	 * @throws ServletException 
	 */
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
	}
 
}