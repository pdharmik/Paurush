package com.lexmark.services.portlet;

import static com.lexmark.services.LexmarkSPConstants.ERROR_MESSAGE_BUNDLE;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.contract.ObieeConnectionData;
import com.lexmark.contract.ObieeImpersonateContract;
import com.lexmark.contract.SiebelAccountIdContract;
import com.lexmark.domain.ObieeImpersonate;
import com.lexmark.domain.ObieeReportParameter;
import com.lexmark.framework.logging.LEXLogger;
import com.lexmark.result.SiebelAccountIdResult;
import com.lexmark.service.api.CustomerReportService;
import com.lexmark.service.api.GlobalService;
import com.lexmark.services.util.PortalSessionUtil;
import com.lexmark.services.util.ServiceStatusErrorMessageUtil;
import com.lexmark.util.JndiLookupUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.PortletDisplay;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
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


public class SharedObieeController{

	@Autowired
	private CustomerReportService customerReportService;
	@Autowired
	private GlobalService globalService;

	private static LEXLogger logger = LEXLogger.getLEXLogger(SharedObieeController.class);
	private static final String DATE_SEPERATOR = " / ";
	private static final String QUARTER_SEPERATOR = " Q ";
	private static final int QUARTER_FOUR = 4;

	public static final String PARAM_TYPE_ANALYTICS_DATE = "analyticsDate";
	public static final String PARAM_TYPE_ANALYTICS_DATE_BETWEEN = "analyticsDateBetween";
	public static final String PARAM_TYPE_ANALYTICS_QUARTER = "analyticsQuarter";
	public static final String PARAM_TYPE_ANALYTICS_QUARTER_BETWEEN = "analyticsQuarterBetween";
	public static final String PARAM_TYPE_ANALYTICS_STRING = "string";
	public static final String PARAM_TYPE_ANALYTICS_LIST = "list";
	
	private static ObieeConnectionData obieeConnectionData = getConfigProperties();
	private static String SERVICE_END_POINT=obieeConnectionData.getHostName();
	private static String SERVICE_USER_NAME=obieeConnectionData.getUserName();
	private static String SERVICE_USER_PASSWORD=obieeConnectionData.getPassword();
	
	private static Properties analyticsProperty;
	
	public String getReportHtml(PortletRequest portletRequest, boolean mdmParamRequire, String chartPath, 
			List<ObieeReportParameter> reportParameterList, String sessionID){
		PortletSession portletSession =portletRequest.getPortletSession();
		StringBuffer reportHtmlBuffer = new StringBuffer();
		logger.debug("-------------------------in shared Obiee Controller-----------------------------");
		try {
			String[] filter = new String[1];

			HttpServletRequest httpReq = PortalUtil.getHttpServletRequest(portletRequest);   
			String ua = httpReq.getHeader("User-Agent");
			logger.debug("in shared Obiee Controller ua is -------------------- "+ua);
			//OBIEE 11g will support every browser
			/*if (!isSupportedBrowserType(ua)){
				//Give error message and do not continue
				logger.debug("Not supported browser type.");
				reportHtmlBuffer.append("<B>" + ServiceStatusErrorMessageUtil.getErrorMessage(ERROR_MESSAGE_BUNDLE, "analyticsReport.browserNotSupported", null, portletRequest.getLocale()) + "</B>");				
			}else{*/
				/*if (!isSupportedBrowserVersion(ua)){
					//Give warning message and continue
					logger.debug("Not supported browser version.");
					reportHtmlBuffer.append("<B>" + ServiceStatusErrorMessageUtil.getErrorMessage(ERROR_MESSAGE_BUNDLE, "analyticsReport.browserVersionNotSupported", null, portletRequest.getLocale()) + "</B><br/><br/>");											
				}*/
				
				//ThemeDisplay themeDisplay= (ThemeDisplay)portletRequest.getAttribute(WebKeys.THEME_DISPLAY);
				ThemeDisplay themeDisplay= (ThemeDisplay)portletRequest.getAttribute("LIFERAY_SHARED_THEME_DISPLAY");
				logger.debug("Breaking After this Line ----------------------------------------------------1");
				PortletDisplay portletDisplay= themeDisplay.getPortletDisplay();
				logger.debug("in shared Obiee Controller ua is -------------------- 2 ");
				//Must begin with an underscore to prevent illegal javascript id when the porlet id begins with a numerical value
				String portletId= "_" + portletDisplay.getInstanceId();

				String portAddressHtml = SharedObieeController.SERVICE_END_POINT+"/saw.dll?SoapImpl=htmlViewService";
				
				logger.debug("------------------------- > "+portAddressHtml);
				
				HtmlViewServiceSoapProxy htmlClient = new HtmlViewServiceSoapProxy(portAddressHtml);
				String reportID = "_7lE2"; 
				StartPageParams startpageparams = new StartPageParams();
				ReportParams newreportParams = new ReportParams();
				startpageparams.setDontUseHttpCookies(false);
				startpageparams.setIdsPrefix(portletId);

				//String sessionID = new String();
				String pageID = new String();
				//String reportID = new String();

				ReportRef reportref = new ReportRef();

				SAWLocale sawlocale = new SAWLocale();
				SAWSessionParameters sessionparams = new SAWSessionParameters();

				logger.debug("User-Agent: " + ua);
				sessionparams.setUserAgent(ua);						

				HashMap<String, String> ldapUserData = (HashMap<String, String>)portletSession.getAttribute(LexmarkConstants.LDAP_USER_DATA, portletSession.APPLICATION_SCOPE);
				String userLanguage = ldapUserData.get(LexmarkConstants.LANGUAGE);
				String userCountry = ldapUserData.get(LexmarkConstants.COUNTRY);
				String userSegment = ldapUserData.get(LexmarkConstants.USERSEGMENT);
				//Per the LDAP API - the following userId will contain short id employees and email address for buspartners
				String userId = ldapUserData.get(LexmarkConstants.SHORTID);
				
				if (userCountry == null || userCountry.equals("") ||
						userLanguage == null || userLanguage.equals("")){
					//Do not set, just use default sawlocale
				}else{
					userLanguage = userLanguage.substring(0, 2);
					logger.debug("Logged in user Country: " + userCountry);
					logger.debug("Logged in user Language:  " + userLanguage);
					sawlocale.setCountry(userCountry);
					sawlocale.setLanguage(userLanguage);					
				}
				
				sessionparams.setLocale(sawlocale);
				sessionparams.setAsyncLogon(false);

				ReportHTMLOptions htmlOptions = new ReportHTMLOptions();
				htmlOptions.setEnableDelayLoading(true);
				htmlOptions.setLinkMode(ReportHTMLLinksMode._InPlace);
				
				sessionID = getObieeSession(portletSession, sessionparams, mdmParamRequire, userSegment, userId, sessionID);
				
				logger.debug("sessionID is coming from session is "+sessionID);
				logger.debug("=============== sessionparams: "+sessionparams);
				logger.debug("=============== mdmParamRequire: "+mdmParamRequire);
				logger.debug("=============== userSegment: "+userSegment);
				logger.debug("=============== userId: "+userId);
				logger.debug("=============== sessionID: "+sessionID);
				
				reportID = portletId; // this uniquely identifies the portlet instance
				logger.debug("after session created line 1");
				if (reportID==null){
					reportID = "Report2";
					logger.debug("report id is hardcoded");
				}
				pageID = htmlClient.startPage(startpageparams, sessionID);
				reportref.setReportPath(chartPath);
				logger.debug("report path is : "+reportref.getReportPath());
				
				filter[0] = getReportFilterCriteria(reportParameterList);
				newreportParams.setFilterExpressions(filter);
				if(reportref.getReportPath()==null||reportref.getReportPath()==""){
					reportHtmlBuffer.append("<B>" + ServiceStatusErrorMessageUtil.getErrorMessage(ERROR_MESSAGE_BUNDLE, "chartPortlet.label.chartPath.notConfigured", null, portletRequest.getLocale()) + "</B>");
				}else{
					logger.debug("Starting params ....................................................");
					Long startTime = System.currentTimeMillis();
					htmlClient.setBridge("/LexmarkOPSPortal/ObieeBridge", sessionID);
					logger.logTime("** PORTAL PERFORMANCE TESTING RETRIEVE CHART FROM OBIEE==>: " + (System.currentTimeMillis()- startTime)/1000.0); /* Added by Sabya for Obiee Report testing */
					logger.debug("after session created line 2");
					logger.debug("sending following parameters pageID "+pageID+ " reportID "+reportID+" reportref path "+reportref.getReportPath()+" xml "+reportref.getReportXml()
							+" portletId "+portletId);
					logger.debug("filter parameter is "+filter[0]);
					htmlClient.addReportToPage(pageID, reportID, reportref, portletId, newreportParams, htmlOptions, sessionID);
					//Changes by Ranjan for 13.4 Release					
					//reportHtmlBuffer.append(htmlClient.getHeadersHtml(pageID, sessionID));
					//reportHtmlBuffer.append(htmlClient.getHtmlForReport(pageID, reportID, sessionID));
					//reportHtmlBuffer.append(htmlClient.getCommonBodyHtml(pageID, sessionID));
					reportHtmlBuffer.append(htmlClient.getHtmlForPageWithOneReport(reportID, reportref,"",newreportParams, htmlOptions, startpageparams,sessionID));
				}
			//}
		} catch (Exception ex) {
			logger.debug("Message:  " + ex.getMessage());
			ex.printStackTrace();
			reportHtmlBuffer.append("<B>" + ServiceStatusErrorMessageUtil.getErrorMessage(ERROR_MESSAGE_BUNDLE, "chartPortlet.label.chartPath.criticalSystemException", null, portletRequest.getLocale()) + "</B>");
			reportHtmlBuffer.append("<br/><br/><B>Error:  " + ex.getMessage() + "</B>");
		}
		
		return reportHtmlBuffer.toString();
	}

	private String getObieeSession(PortletSession portletSession, SAWSessionParameters sessionparams, 
			boolean mdmParamRequire, String userSegment, String userId, String sessionID) throws Exception{
         logger.debug("in [getObieeSession]");
		AuthResult authResult = null;
		String portAddressSAW = SERVICE_END_POINT+"/saw.dll?SoapImpl=nQSessionService";
		SAWSessionServiceSoapProxy myPort = new SAWSessionServiceSoapProxy(portAddressSAW);
		if (sessionID == null || sessionID.equals("")){
			sessionID = (String) portletSession.getAttribute(LexmarkConstants.OBIEE_SESSION, portletSession.APPLICATION_SCOPE);
		}
		ObieeImpersonate obieeImpersonate = new ObieeImpersonate();
		ObieeImpersonateContract obieeImpersonateContract = new ObieeImpersonateContract();
		//Check to see if siebelAccountId is in session
		String siebelAccountId = (String) portletSession.getAttribute(LexmarkConstants.SIEBEL_ACCOUNT_ID, portletSession.APPLICATION_SCOPE);
		logger.debug("siebelAccountId before loop"+siebelAccountId);
		if (siebelAccountId == null || siebelAccountId.equals("")){
			//Not in session, call getSiebelAccountId
			siebelAccountId = getSiebelAccountId(portletSession);
			//Clear sessionID to ensure that authentication below
			sessionID = null;
			logger.debug("siebelAccountId: " + siebelAccountId + " and userId: "+ userId);
			obieeImpersonate.setUserId(userId.toUpperCase());
			obieeImpersonate.setSiebelAccountId(siebelAccountId);
			obieeImpersonateContract.setObieeImpersonate(obieeImpersonate);
			logger.debug("saving obiee data in portal_entry");
			customerReportService.saveObieeImpersonateData(obieeImpersonateContract);
		}else{
			if (userSegment.equalsIgnoreCase("employee")){
				String currentSiebelAccountId = getSiebelAccountId(portletSession);
				if (!siebelAccountId.equals(currentSiebelAccountId)){
					//employee users can change MDM ID and Level so call getSiebelAccountId
					siebelAccountId = currentSiebelAccountId;
					//Clear sessionID to ensure that authentication below
					myPort.logoff(sessionID);
					sessionID = null;
					logger.debug("siebelAccountId: " + siebelAccountId + " and userId: "+ userId);				
					obieeImpersonate.setUserId(userId.toUpperCase());
					obieeImpersonate.setSiebelAccountId(siebelAccountId);
					obieeImpersonateContract.setObieeImpersonate(obieeImpersonate);
					customerReportService.saveObieeImpersonateData(obieeImpersonateContract);					
				}
			}
		}
		String impersonateUserId = userId.toUpperCase();
		//String impersonateUserId = "TSINHA";
//		String impersonateUserId = "GCUMMINS@CUMMINS.COM";

		if (sessionID == null || sessionID.equals("")){
			if (mdmParamRequire == true){
				logger.debug("@@@@@@@@@@@@@@@@@@@@ mdmParamRequire value is true ");
				logger.debug(" username is "+SERVICE_USER_NAME + " and password is "+SERVICE_USER_PASSWORD+" SERVICE_IMPERSONATE_USER_NAME "+impersonateUserId);
				authResult = myPort.impersonateex(SERVICE_USER_NAME, SERVICE_USER_PASSWORD, impersonateUserId, sessionparams);
			}else{
				logger.debug("@@@@@@@@@@@@@@@@@@@@ mdmParamRequire value is false ");
				authResult = myPort.logonex("Administrator", "SADMIN",sessionparams);
			}
			logger.debug("Authentication successful");
			logger.debug("Before session created session id is "+sessionID);
			sessionID = authResult.getSessionID();
			logger.debug("Session created so session id is "+sessionID);	
			portletSession.setAttribute(LexmarkConstants.OBIEE_SESSION, sessionID, portletSession.APPLICATION_SCOPE);
		}else{
			try{
				//Must put in try catch because the method will throw an exception if not logged in
				logger.debug("Before getting current user");
				String currentUser = myPort.getCurUser(sessionID);
				logger.debug("Current User: " + currentUser);						
				logger.debug("************** User is active - use existing session *********");						
			}catch(Exception e){
				logger.debug("************** User is null - Need new session *********");
				if (mdmParamRequire == true){
					logger.debug("@@@@@@@@@@@@@@@@@@@@ mdmParamRequire value is true ");
					logger.debug(" username is "+SERVICE_USER_NAME + " and password is "+SERVICE_USER_PASSWORD+" SERVICE_IMPERSONATE_USER_NAME "+impersonateUserId);
					authResult = myPort.impersonateex(SERVICE_USER_NAME, SERVICE_USER_PASSWORD, impersonateUserId, sessionparams);
				}else{
					logger.debug("@@@@@@@@@@@@@@@@@@@@ mdmParamRequire value is false ");
					authResult = myPort.logonex("Administrator", "SADMIN",sessionparams);
				}
				logger.debug("Authentication successful");
				logger.debug("Before session created session id is "+sessionID);
				sessionID = authResult.getSessionID();
				logger.debug("Session created so session id is "+sessionID);	
				portletSession.setAttribute(LexmarkConstants.OBIEE_SESSION, sessionID, portletSession.APPLICATION_SCOPE);												
			}
			logger.debug("Session retrieved from session is "+sessionID);										
		}
		return sessionID;

	}
	
	private String getSiebelAccountId(PortletSession portletSession) throws Exception{
		Map<String, Object> ldapUserData =  (Map)portletSession.getAttribute(LexmarkConstants.LDAP_USER_DATA, portletSession.APPLICATION_SCOPE);
		String mdmId = "";
		String mdmLevel = "";
		String chlNodeId = PortalSessionUtil.getChlNodeId(portletSession);
		String segment = (String)ldapUserData.get(LexmarkConstants.USERSEGMENT);
		if (segment.equalsIgnoreCase("employee")){
			mdmId = (String)portletSession.getAttribute("obieeMdmId", portletSession.APPLICATION_SCOPE);
			mdmLevel = (String)portletSession.getAttribute("employeeMdmLevel", portletSession.APPLICATION_SCOPE);
		}else{
			mdmLevel = PortalSessionUtil.getMdmLevel(portletSession);
			if (mdmLevel.equalsIgnoreCase(LexmarkConstants.MDM_LEVEL_GLOBAL)|| 
					mdmLevel.equalsIgnoreCase(LexmarkConstants.MDM_LEVEL_DOMESTIC)){
				//Get MDM ID
				mdmId = (String)ldapUserData.get(LexmarkConstants.MDMID);
			}else{
				if (chlNodeId == null || chlNodeId.equals("")){
					mdmId = PortalSessionUtil.getMdmId(portletSession);								
				}else{
					mdmId = chlNodeId;
				}
			}	
		}

		logger.debug("*********MdmId:"+ mdmId);
		logger.debug("*********MdmLevel:"+mdmLevel);
		logger.debug("*********ChlNodeId:"+chlNodeId);
		String siebelAccountId = "";
		if ((chlNodeId == null || chlNodeId.equals(""))&& (mdmLevel.equalsIgnoreCase(LexmarkConstants.GLOBAL) || 
				mdmLevel.equalsIgnoreCase(LexmarkConstants.DOMESTIC) ||
				mdmLevel.equalsIgnoreCase(LexmarkConstants.LEGAL) ||
				mdmLevel.equalsIgnoreCase(LexmarkConstants.ACCOUNT))){			

			SiebelAccountIdContract siebelAccountIdContract = new SiebelAccountIdContract();
			siebelAccountIdContract.setMdmId(mdmId);
			siebelAccountIdContract.setMdmLevel(mdmLevel);
			SiebelAccountIdResult siebelAccountIdResult = globalService.retrieveSiebelAccountId(siebelAccountIdContract);
			if (siebelAccountIdResult.getSiebelAccountId() == null || siebelAccountIdResult.getSiebelAccountId().equals("")){
				throw new Exception("Critical error getting Siebel Account ID - null or empty");
			}
			siebelAccountId = siebelAccountIdResult.getSiebelAccountId();
			logger.debug("*********Siebel Account ID from Siebel:"+ siebelAccountId);
		}else{
			siebelAccountId = mdmId;
		}
		portletSession.setAttribute(LexmarkConstants.SIEBEL_ACCOUNT_ID, siebelAccountId, portletSession.APPLICATION_SCOPE);

		return siebelAccountId;
	}
	
	private String getReportFilterCriteria(List<ObieeReportParameter> reportParameterList){
		StringBuffer filterCriteria = new StringBuffer();
		//Header
		filterCriteria.append("<sawx:expr xsi:type=\"sawx:logical\" op=\"and\" " +
				"xmlns:saw=\"com.siebel.analytics.web/report/v1\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" " +
				"xmlVersion=\"200705140\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" " +
		"xmlns:sawx=\"com.siebel.analytics.web/expression/v1\">");
		
		ObieeReportParameter oracleReportParameter = new ObieeReportParameter();
		for (int j = 0; j < reportParameterList.size(); j++) {
			String parameterType ;
			String parameterName ;
			String parameterValue ;
			String parameterValue2 ;
			oracleReportParameter = reportParameterList.get(j);
			parameterType = oracleReportParameter.getParameterType();
			parameterName = oracleReportParameter.getParameterName();
			parameterValue = oracleReportParameter.getParameterValue();
			
			logger.debug("report parametername is : "+parameterName);
			logger.debug("param value is : "+parameterValue);

			// -- Start Criteria
			filterCriteria.append("<sawx:expr xsi:type=\"sawx:special\" op=\"prompted\">");
			filterCriteria.append("<sawx:expr xsi:type=\"sawx:sqlExpression\">" + parameterName + "</sawx:expr>");
			filterCriteria.append("</sawx:expr>");
			// -- End Criteria
			// -- Start Filter Values
			if (parameterType.equalsIgnoreCase(PARAM_TYPE_ANALYTICS_DATE) || 
					parameterType.equalsIgnoreCase(PARAM_TYPE_ANALYTICS_QUARTER) || 
					parameterType.equalsIgnoreCase(PARAM_TYPE_ANALYTICS_STRING) || 
					parameterType.equalsIgnoreCase(PARAM_TYPE_ANALYTICS_LIST)){
				logger.debug(parameterType);
				filterCriteria.append("<sawx:expr xsi:type=\"sawx:comparison\" op=\"equal\">");
				filterCriteria.append("<sawx:expr xsi:type=\"sawx:sqlExpression\">" + parameterName + "</sawx:expr>");
				filterCriteria.append("<sawx:expr xsi:type=\"xsd:string\">" + parameterValue + "</sawx:expr>");
				filterCriteria.append("</sawx:expr>");
			}else if (parameterType.equalsIgnoreCase(PARAM_TYPE_ANALYTICS_DATE_BETWEEN) || 
					parameterType.equalsIgnoreCase(PARAM_TYPE_ANALYTICS_QUARTER_BETWEEN)){
				logger.debug(parameterType);		
				parameterValue2 = oracleReportParameter.getParameterValue2();
				filterCriteria.append("<sawx:expr xsi:type=\"sawx:comparison\" op=\"between\">");
				filterCriteria.append("<sawx:expr xsi:type=\"sawx:sqlExpression\">" + parameterName + "</sawx:expr>");
				filterCriteria.append("<sawx:expr xsi:type=\"xsd:string\">" + parameterValue + "</sawx:expr>");
				filterCriteria.append("<sawx:expr xsi:type=\"xsd:string\">" + parameterValue2 + "</sawx:expr>");
				filterCriteria.append("</sawx:expr>");
			}
			
		}
		//Closure
		filterCriteria.append("</sawx:expr>");
		logger.debug("*************************** filter param value is "+filterCriteria.toString()+"**********************************");

		return filterCriteria.toString();
	}

	private static ObieeConnectionData getConfigProperties(){
		ObieeConnectionData obieeConnectionData = null;
		try{
			obieeConnectionData = JndiLookupUtil.getObieeServiceResult();
			logger.debug("UserName:" + obieeConnectionData.getUserName());
			logger.debug("Password:" + obieeConnectionData.getPassword());
			logger.debug("HostName:" + obieeConnectionData.getHostName());
		}catch (Exception e){
			logger.debug("Exception "+e.getMessage());
		}
		return obieeConnectionData;			
	} 
	
	private boolean isSupportedBrowserType(String ua){
		
		String browserName = null;
		String [] userAgent = null;

		Pattern p = Pattern.compile("(MSIE|Lynx|Opera|Chrome|Flock|SeaMonkey|Camino|Firefox|Konqueror|Version)[\\s|/]([\\w].[\\w]*)");
		Matcher m = p.matcher((CharSequence)ua);
		if (m.find()){
			//If we have a hit the result will be in the style of Firefox/3.0 or MSIE 8.0, therefore split on a space or /
			userAgent = m.group().split("[\\s|/]");
	 
			//First item in the array is always browser name
			browserName = userAgent[0];
			logger.debug("browserName: " + browserName);

			if (browserName.equalsIgnoreCase("MSIE")){
				return true;
			}
			/*Commented for AIR64576 by WIPRO AMS
			if (browserName.equalsIgnoreCase("Firefox")){
				return true;
			}
			*/
			
		}
		return false;

	}
	
	private boolean isSupportedBrowserVersion(String ua){
		String browserName = null;
		String browserMajorVersion = null;
		String browserMinorVersion = null;
		String [] userAgent = null;

		Pattern p = Pattern.compile("(MSIE|Lynx|Opera|Chrome|Flock|SeaMonkey|Camino|Firefox|Konqueror|Version)[\\s|/]([\\w].[\\w]*)");
		Matcher m = p.matcher((CharSequence)ua);
		if (m.find()){
			//If we have a hit the result will be in the style of Firefox/3.0 or MSIE 8.0, therefore split on a space or /
			userAgent = m.group().split("[\\s|/]");
	 
			//First item in the array is always browser name
			browserName = userAgent[0];
			//Second item is the version number, get number before period as major version and number after period as minor version
			browserMajorVersion = userAgent[1].substring(0, userAgent[1].indexOf("."));
			browserMinorVersion = userAgent[1].substring(userAgent[1].indexOf(".") + 1, userAgent[1].length());
			
			logger.debug("browserMajorVersion: " + browserMajorVersion);
			logger.debug("browserMinorVersion: " + browserMinorVersion);

			int versionMajor = new Integer(browserMajorVersion).intValue();
			int versionMinor = new Integer(browserMinorVersion).intValue();
			
			//CI_6 Changes Start
			/*if (browserName.equalsIgnoreCase("MSIE")){
				if (versionMajor < 6 || versionMajor > 7){
					return false;											
				}
				if (versionMajor == 7){
					if (versionMinor > 0){
						return false;																		
					}
				}
				return true;
			}*/
			if (browserName.equalsIgnoreCase("MSIE")){
				if (versionMajor < 6 || versionMajor > 9){
					return false;											
				}
				if (versionMajor == 9){
					if (versionMinor > 0){
						return false;																		
					}
				}
				return true;
			}
			//CI-6Chnages End
			if (browserName.equalsIgnoreCase("Firefox")){
				if (versionMajor < 1 || versionMajor > 2){
					return false;
				}
				if (versionMajor == 1){
					if (versionMinor != 5){
						return false;
					}
				}
				if (versionMajor == 2){
					if (versionMinor > 0){
						return false;
					}
				}
				return true;
			}
		}
		return false;
	}
	
	public static void populateParameterOptions(Model model) {
		List<String> rolling24Months = getRolling24Months();
		List<String> rollingQuarters = getRollingQuarters();
		model.addAttribute("rolling24Months", rolling24Months);
		model.addAttribute("rollingQuarters", rollingQuarters);
	}
	
	private static List<String> getRolling24Months() {
		Calendar today = Calendar.getInstance();
		List<String> rollingMonths = new ArrayList<String>();
		
		int month;
		String monthStr;
		for (int i = 0; i < 24; i ++) {
			today.add(Calendar.MONTH, -1);
			month = today.get(Calendar.MONTH) + 1;
			if (month < 10) {
				monthStr = "0" + month;
			} else {
				monthStr = String.valueOf(month);
			}
			rollingMonths.add(today.get(Calendar.YEAR) + DATE_SEPERATOR + monthStr);
		}
		return rollingMonths;
	}
	
	private static List<String> getRollingQuarters() {
		Calendar today = Calendar.getInstance();
		List<String> rollingQuarters = new ArrayList<String>();
		
		int currentYear = today.get(Calendar.YEAR);
		int currentQuarter = today.get(Calendar.MONTH) / 3 + 1;
		
		for (int i = 0; i < 8; i ++) {
			if (currentQuarter > 1) {
				rollingQuarters.add(currentYear + QUARTER_SEPERATOR + (currentQuarter - 1));
				currentQuarter --;
			} else {
				rollingQuarters.add((currentYear - 1) + QUARTER_SEPERATOR + QUARTER_FOUR);
				currentQuarter = QUARTER_FOUR;
				currentYear --;
			}
		}
		return rollingQuarters;
	}

}
