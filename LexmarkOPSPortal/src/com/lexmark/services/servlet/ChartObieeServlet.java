package com.lexmark.services.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.contract.ObieeConnectionData;
import com.lexmark.services.portlet.SharedObieeController;
import com.lexmark.util.JndiLookupUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.PortletDisplay;
import com.liferay.portal.theme.ThemeDisplay;
import com.siebel.analytics.web.soap.v6.AuthResult;
import com.siebel.analytics.web.soap.v6.HtmlViewServiceSoapProxy;
import com.siebel.analytics.web.soap.v6.ReportHTMLLinksMode;
import com.siebel.analytics.web.soap.v6.ReportHTMLOptions;
import com.siebel.analytics.web.soap.v6.ReportParams;
import com.siebel.analytics.web.soap.v6.ReportRef;
import com.siebel.analytics.web.soap.v6.SAWLocale;
import com.siebel.analytics.web.soap.v6.SAWSessionParameters;
import com.siebel.analytics.web.soap.v6.SAWSessionServiceSoapProxy;
import com.siebel.analytics.web.soap.v6.StartPageParams;

public class ChartObieeServlet extends HttpServlet {
	
	private static Logger LOGGER = LogManager.getLogger(ChartObieeServlet.class);
	
	private static ObieeConnectionData obieeConnectionData = getConfigProperties();
	private static String SERVICE_END_POINT=obieeConnectionData.getHostName();
	String SERVICE_USER_NAME=obieeConnectionData.getUserName();//"ssiebel";
	String SERVICE_USER_PASSWORD=obieeConnectionData.getPassword();//"crm4lxk!";
	/**
	 * 
	 */
	private static final long serialVersionUID = -3731357100242652603L;
	 /** Initializes the servlet.
	    */
	   public void init(ServletConfig config) throws ServletException 
	   {
	      
		   LOGGER.info(" ObieeBridge init ");
	        
	   }
	    
	   /** Destroys the servlet.
	    */
	   public void destroy() 
	   {
	        
	   }
	  
  
	   /** Handles the HTTP <code>GET</code> method.
	    * @param request servlet request
	    * @param response servlet response
	    */
	   protected void doGet(HttpServletRequest request, HttpServletResponse response)
	      throws ServletException, IOException 
	   {
		   LOGGER.info("ObieeBridge doGet");
	      processObieeRequest(request,response);
	      
	   }
	    
	   /** Handles the HTTP <code>POST</code> method.
	    * @param request servlet request
	    * @param response servlet response
	    */
	   protected void doPost(HttpServletRequest request, HttpServletResponse response)
	      throws ServletException, IOException 
	   {
		   LOGGER.info(" ObieeBridge doPost");
	      processObieeRequest(request,response);
	      
	   }
	   protected void processObieeRequest(HttpServletRequest request, HttpServletResponse response)
	      throws ServletException, IOException {
		 
			String ua = request.getHeader("User-Agent");
			LOGGER.debug("User-Agent: " + ua);
			SAWLocale sawlocale = new SAWLocale();
			SAWSessionParameters sessionparams = new SAWSessionParameters();
			sessionparams.setUserAgent(ua);						
			sessionparams.setLocale(sawlocale);
			sessionparams.setAsyncLogon(false);
			// Start here real implementation
			//HashMap<String, String> ldapUserData = (HashMap<String, String>)portletSession.getAttribute(LexmarkConstants.LDAP_USER_DATA, portletSession.APPLICATION_SCOPE);
			String userLanguage = request.getParameter("userLanguage");
			String userCountry = request.getParameter("userCountry");
			String userSegment = request.getParameter("userSegment");
			//Per the LDAP API - the following userId will contain short id employees and email address for buspartners
			String userId = request.getParameter("userId");
			boolean mdmParamRequire = Boolean.valueOf(request.getParameter("mdmParamRequire"));
			LOGGER.debug("**Logged in user Country: " + userCountry);
			LOGGER.debug("**Logged in user Language:  " + userLanguage);
			LOGGER.debug("**Logged in user userSegment: " + userSegment);
			LOGGER.debug("**Logged in user userId:  " + userId);
			LOGGER.debug("**Logged in user mdmParamRequire:  " + mdmParamRequire);
			if (userCountry == null || userCountry.equals("") ||
					userLanguage == null || userLanguage.equals("")){
				//Do not set, just use default sawlocale
			}else{
				userLanguage = userLanguage.substring(0, 2);
				LOGGER.debug(">>>>><<<<< Logged in user Country: " + userCountry);
				LOGGER.debug(">>>>><<<<< Logged in user Language:  " + userLanguage);
//				sawlocale.setCountry("IN");  Commented by sankha for LEX:AIR00075691
//				sawlocale.setLanguage("en");	Commented by sankha for LEX:AIR00075691
				/* Added by sankha for LEX:AIR00075691 start */
				sawlocale.setCountry(userCountry);
				sawlocale.setLanguage(userLanguage);
				/* End */
			}
			
			sessionparams.setLocale(sawlocale);
			sessionparams.setAsyncLogon(false);
			
		    AuthResult authResult = null;
			String portAddressHtml = SERVICE_END_POINT+"/saw.dll?SoapImpl=htmlViewService";
			//String portAddressHtml = SERVICE_END_POINT+"/saw.dll?bieehome&startPage=1&SoapImpl=htmlViewService";
			LOGGER.info("OBIEE WSDL portAddressHtml ::: "+portAddressHtml);
			HtmlViewServiceSoapProxy htmlClient = new HtmlViewServiceSoapProxy(portAddressHtml);

			String portAddressSAW = SERVICE_END_POINT+"/saw.dll?SoapImpl=nQSessionService";
			LOGGER.info("portAddressSAW ::::: " + portAddressSAW);
			SAWSessionServiceSoapProxy myPort = new SAWSessionServiceSoapProxy(portAddressSAW);
			//logger.info("After calling myPort =====>");
			String impersonateUserId = userId.toUpperCase();
			//String impersonateUserId = "MPS3PORTAL@MPS.COM";
			/*if (mdmParamRequire == true){
				logger.info("@@@@@@@@@@@@@@@@@@@@ mdmParamRequire value is true ");
				logger.info(" username is "+SERVICE_USER_NAME + " and password is "+SERVICE_USER_PASSWORD+" SERVICE_IMPERSONATE_USER_NAME "+impersonateUserId);
				authResult = myPort.impersonateex(SERVICE_USER_NAME, SERVICE_USER_PASSWORD, impersonateUserId, sessionparams);
			}else{
				logger.info("@@@@@@@@@@@@@@@@@@@@ mdmParamRequire value is false ");
				authResult = myPort.logonex("Administrator", "SADMIN",sessionparams);
			}*/
			
			//logger.info("SERVICE_USER_PASSWORD==>"+SERVICE_USER_PASSWORD);
			LOGGER.debug("impersonateUserId===>"+impersonateUserId);
			LOGGER.debug("sessionparams===>"+sessionparams);
			LOGGER.debug("before calling impersonate =====>SERVICE_USER_NAME===>"+SERVICE_USER_NAME);
			authResult = myPort.impersonateex(SERVICE_USER_NAME, SERVICE_USER_PASSWORD, impersonateUserId, sessionparams);
			//logger.info("after calling impersonate =====>");
			String sessionID=authResult.getSessionID();
			request.getSession().setAttribute("sessionID", sessionID);
			//logger.info("before calling printwriter =====>SessionID"+sessionID);
			PrintWriter out = response.getWriter();
			//logger.info("after calling printwriter & before obieeCookie =====>");
			Cookie obieeCookie = new Cookie("ORA_BIPS_NQID",sessionID);
			
			//logger.info("after calling  obieeCookie =====>"+obieeCookie);
			response.addCookie(obieeCookie);
			response.setContentType("text/html");
			out.println(sessionID);
			out.flush();
			out.close();
	   }
	   private static ObieeConnectionData getConfigProperties(){
			ObieeConnectionData obieeConnectionData = null;
			try{
				obieeConnectionData = JndiLookupUtil.getObieeServiceResult();
				//logger.info("UserName:" + obieeConnectionData.getUserName());
				//logger.info("Password:" + obieeConnectionData.getPassword());
				//logger.info("HostName:" + obieeConnectionData.getHostName());
			}catch (Exception e){
				LOGGER.debug("Exception "+e.getMessage());
			}
			return obieeConnectionData;			
		} 
}
