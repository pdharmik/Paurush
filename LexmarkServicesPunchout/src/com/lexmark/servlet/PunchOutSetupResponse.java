package com.lexmark.servlet;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;

import com.lexmark.commons.LexmarkIDManager;
import com.lexmark.services.util.ControllerUtil;

/**
 * Created by IntelliJ IDEA.
 * User: jkalathi
 * Date: Aug 20, 2008
 * Time: 9:45:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class PunchOutSetupResponse extends HttpServlet {

   
   private String duns;

   
   private static String KAISER_HOME_URL = ControllerUtil.getConfigProperties().getProperty("ariba.kaiserhomeurl");  
   private static String REPUBLIC_HOME_URL = ControllerUtil.getConfigProperties().getProperty("ariba.republichomeurl");
   private static String KAISER_ACNT = ControllerUtil.getConfigProperties().getProperty("ariba.kaiser");
   private static String REPUBLIC_ACNT = ControllerUtil.getConfigProperties().getProperty("ariba.republic");

   /**
 * @param servletConfig 
 * @throws ServletException 
 */
public void init(ServletConfig servletConfig) throws ServletException {
      super.init(servletConfig);
     
	  duns=ControllerUtil.getConfigProperties().getProperty("ariba.duns");
   }

   /**
 * @param request 
 * @param response 
 * @throws ServletException 
 * @throws IOException 
 */
public void doGet(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
      execute(request, response);
   }

   /**
 * @param request 
 * @param response 
 * @throws ServletException 
 * @throws IOException 
 */
public void doPost(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {
      execute(request, response);
   }

   /**
 * @param request 
 * @param response 
 * @throws ServletException 
 * @throws IOException 
 */
public void execute(HttpServletRequest request, HttpServletResponse response)
           throws ServletException, IOException {  
		
      String buyerCookie = request.getParameter("buyerCookie");
      String supplierCookie = request.getParameter("supplierCookie");
      String identity = request.getParameter("identity");
      String operation = request.getParameter("operation");
      String formPostURL = request.getParameter("formPostURL");
      String networkUserId = request.getParameter("networkUserId");
      String sharedSecret  = request.getParameter("sharedSecret");      
	  String user=request.getParameter("user");
      String  origin=request.getParameter("origin"); 
      String ldsSupplierId = "";
     
      if ("lxkstatenj".equalsIgnoreCase(identity))
      {
    	  StringBuffer strbuff=new StringBuffer();
    	  strbuff.append("&").append("user=").append(user).append("&").append("origin=").append(origin);
    	  formPostURL +=strbuff;
      }
      /*code added by sbag for testing*/     
      String userId= LexmarkIDManager.getLexmarkID(request, response);           
     HttpSession session=request.getSession();
      session.setAttribute("supplierCookie", supplierCookie);
      session.setAttribute("buyerCookie", buyerCookie);
      session.setAttribute("supplierId", identity);
      session.setAttribute("operation", operation);
      session.setAttribute("userId", userId);
      session.setAttribute("duns", duns);
      session.setAttribute("networkUserId", networkUserId);
      session.setAttribute("sharedSecret", sharedSecret);
      session.setAttribute("formPostURL", formPostURL);
      this.getServletContext().setAttribute("formPostURL",formPostURL);
      String homeUrl = null;
      
      /*if(sharedSecret.equalsIgnoreCase(KAISER_ACNT))
    	  homeUrl = KAISER_HOME_URL;
      else if(sharedSecret.equalsIgnoreCase(REPUBLIC_ACNT))
    	  homeUrl = REPUBLIC_HOME_URL;*/
      if(identity.equalsIgnoreCase(KAISER_ACNT))
    	  homeUrl = KAISER_HOME_URL;
      else if(identity.equalsIgnoreCase(REPUBLIC_ACNT))
    	  homeUrl = REPUBLIC_HOME_URL;
     
      String aribaSetup = "supplierCookie=" + supplierCookie + ";" + "buyerCookie=" + buyerCookie + ";" + "supplierId=" + identity + ";" +
            "formPostURL=" + formPostURL + ";" + "operation=" + operation + ";" + "userId=" + userId + ";" + "duns=" + duns + ";" +
            "networkUserId=" + networkUserId + ";" + "sharedSecret=" + sharedSecret ;
     
      Cookie aribaCookie = new Cookie("aribaSetup", new String(Base64.encodeBase64(aribaSetup.getBytes())));   
      aribaCookie.setMaxAge(60*60*24);
      aribaCookie.setPath("/");
      response.addCookie(aribaCookie);
      String url ="supplierCookie="+supplierCookie+"&buyerCookie="+buyerCookie+"&identity="+identity+"&operation="+operation+"&userId="+userId+"&duns="+duns+"&networkUserId="+networkUserId+"&sharedSecret="+sharedSecret;
      byte[] encoded = Base64.encodeBase64(url.getBytes());
      String encodedurl = new String(encoded);
      response.setHeader("X-FRAME-OPTIONS", "ALLOW-FROM https://service.ariba.com");
	  response.sendRedirect(homeUrl+"?aribaParamUrl="+encodedurl);	
    
   }

}
