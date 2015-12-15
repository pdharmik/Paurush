package com.lexmark.services.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class ReportNotifierServlet extends HttpServlet {
	private static Logger logger = LogManager.getLogger(ReportNotifierServlet.class);
	 private static final String REPORT_NOTIFIER_PROPERTIES_FILE = "/reportNotifier.properties";
	 private static final String AUTH_KEY_PROPERTY = "AuthKey";
	 private static String authKey;
	
	 static {
		 Properties authKeyProperty = getAuthKeyPropertier();
		 authKey = authKeyProperty.getProperty(AUTH_KEY_PROPERTY);
	 }
	 
	 
	private static final long serialVersionUID = -1752878732668489277L;
	private ReportNotifierServletService  reportNotifierService;
	private HttpSession session;
	
	 /**
	 * @return Properties 
	 */
	private static Properties getAuthKeyPropertier() {
	      Properties props = new Properties();
	      InputStream in = null;
	      try {
	        in = ReportNotifierServlet.class.getResourceAsStream(REPORT_NOTIFIER_PROPERTIES_FILE);
	        props.load(in);
	      } catch (IOException e) {
	        new RuntimeException("Fail to get Report Notifier configuration file.", e);
	      }
	      finally {
	    	  if(in != null) {
	    		  try {
	    		  in.close();
	    		  } catch(Exception e) {
	    			  logger.debug("Exception occured");
	    			  e.getMessage();
	    		  }
	    	  }
	      }
	      return props;
	} 
	/**
	 * @param request 
	 * @param response 
	 * @throws IOException 
	 * @throws ServletException 
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		logger.debug("Publish Notifier Servlet begins to run");
		String authenticateKey = (String) request.getParameter(AUTH_KEY_PROPERTY);
		if(authenticateKey == null || !authenticateKey.equals(authKey)) {
			response.getWriter().write("Fail to authenticate");
			response.getWriter().flush();
			return;
		}
		session = request.getSession();
		getReportNotifierServletService().notifyPublishing();
		response.getWriter().write("Successfully publish");
		response.getWriter().flush();
	}
	
	/**
	 * @return ReportNotifierServletService 
	 */
	private ReportNotifierServletService getReportNotifierServletService() {
		if (reportNotifierService == null) {
			WebApplicationContext springAppContext = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext());
			reportNotifierService = springAppContext.getBean("reportNotifierService", ReportNotifierServletService.class);
		}
		return reportNotifierService;
	}
}