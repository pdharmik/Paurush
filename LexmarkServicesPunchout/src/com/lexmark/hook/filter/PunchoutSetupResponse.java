package com.lexmark.hook.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lexmark.commons.LexmarkIDManager;
import com.lexmark.services.constants.PunchoutConstants;
import com.lexmark.services.portlet.RequestPrintersController;
import com.lexmark.services.util.ControllerUtil;

public class PunchoutSetupResponse implements Filter{
	
	   private String duns;
	   private String KAISER_HOME_URL;
	   private String REPUBLIC_HOME_URL;
	   private String KAISER_ACNT;
	   private String REPUBLIC_ACNT;
	   private static Logger LOGGER = LogManager.getLogger(PunchoutSetupResponse.class);
	@Override
	public void destroy() {
		// TODO Auto-generated method stub		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain arg2) throws IOException, ServletException {
		  
		      String buyerCookie = request.getParameter("buyerCookie");
		      String supplierCookie = request.getParameter("supplierCookie");
		      String identity = request.getParameter("identity");
		      String operation = request.getParameter("operation");
		      String formPostURL = request.getParameter("formPostURL");
		      String networkUserId = request.getParameter("networkUserId");
		      String sharedSecret  = request.getParameter("sharedSecret");      
			  String user=request.getParameter("user");
		      String  origin=request.getParameter("origin"); 
		     
		     
		      HttpServletRequest httpReq=(HttpServletRequest) request;
		      HttpServletResponse httpResp=(HttpServletResponse) response;
		      
		      if ("lxkstatenj".equalsIgnoreCase(identity))
		      {
		    	  StringBuffer strbuff=new StringBuffer();
		    	  strbuff.append("&").append("user=").append(user).append("&").append("origin=").append(origin);
		    	  formPostURL +=strbuff;
		      }
		      
		      String userId=LexmarkIDManager.getLexmarkID(httpReq, httpResp);  
		     
		     HttpSession session=httpReq.getSession();
		     Map<String,String> aribaParamMap=new HashMap<>();
		     
		     aribaParamMap.put("supplierCookie", supplierCookie);
		     aribaParamMap.put("buyerCookie", buyerCookie);
		     aribaParamMap.put("supplierId", identity);
		     aribaParamMap.put("operation", operation);
		     aribaParamMap.put("userId", userId);
		     aribaParamMap.put("duns", duns);
		     aribaParamMap.put("networkUserId", networkUserId);
		     aribaParamMap.put("sharedSecret", sharedSecret);
		     aribaParamMap.put("formPostURL", formPostURL);
		     aribaParamMap.put("formAriba", String.valueOf(true));
		     session.setAttribute(PunchoutConstants.ARIBA_PARAMS, aribaParamMap);
		      String homeUrl = "";
		      
		      if(identity.equalsIgnoreCase(KAISER_ACNT)){
		    	  homeUrl = KAISER_HOME_URL;
		    	  aribaParamMap.put("isKaiser", String.valueOf(true));
		      }else if(identity.equalsIgnoreCase(REPUBLIC_ACNT)){
		    	  homeUrl = REPUBLIC_HOME_URL;
		    	  aribaParamMap.put("isRepublic", String.valueOf(true));
		      }else{
		    	  LOGGER.error("[ Home URL could not be set as identity ]");
		      }
		     
		      httpResp.setHeader("X-FRAME-OPTIONS", "ALLOW-FROM https://service.ariba.com");
			  httpResp.sendRedirect(homeUrl);	
		    
		   
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		   duns=ControllerUtil.getConfigProperties().getProperty("ariba.duns");	   
		   KAISER_HOME_URL = ControllerUtil.getConfigProperties().getProperty("ariba.kaiserhomeurl");  
		   REPUBLIC_HOME_URL = ControllerUtil.getConfigProperties().getProperty("ariba.republichomeurl");
		   KAISER_ACNT = ControllerUtil.getConfigProperties().getProperty("ariba.kaiser");
		   REPUBLIC_ACNT = ControllerUtil.getConfigProperties().getProperty("ariba.republic");
		
	}

}
