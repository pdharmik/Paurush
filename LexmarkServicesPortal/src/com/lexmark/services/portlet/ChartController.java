package com.lexmark.services.portlet;

import static com.lexmark.services.LexmarkSPConstants.ERROR_MESSAGE_BUNDLE;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.contract.ChartParameterDetailContract;
import com.lexmark.domain.ChartInfo;
import com.lexmark.domain.ChartParameter;
import com.lexmark.domain.ChartParameterDetail;
import com.lexmark.domain.ObieeReportParameter;
import com.lexmark.service.impl.real.jdbc.CustomizeChartServiceImpl;
import com.lexmark.services.util.LexmarkUserUtil;
import com.lexmark.services.util.PortalSessionUtil;
import com.lexmark.services.util.ServiceStatusErrorMessageUtil;
import com.liferay.portal.kernel.util.WebKeys;


@Controller
@RequestMapping("VIEW")
public class ChartController extends BaseController{
	@Autowired
	private SharedObieeController sharedObieeController;

	private static Logger logger = LogManager.getLogger(ChartController.class);

	/**
	 * @param model 
	 * @param request 
	 * @param response 
	 * @return String  
	 */
	@RequestMapping
	public String getChartName(Model model, RenderRequest request, RenderResponse response) {
		logger.debug("--------------Entry to ChartController method 1------------------");
		boolean isPublishing=false;
		try {
		isPublishing = LexmarkUserUtil.isPublishing(request);
		PortletPreferences prefs = request.getPreferences();
		boolean mdmParamRequire = Boolean.parseBoolean(prefs.getValue("requireMdmParams","noValue"));
		
		PortletSession portletSession = request.getPortletSession();
		HashMap<String, String> ldapUserData = (HashMap<String, String>)portletSession.getAttribute(LexmarkConstants.LDAP_USER_DATA, portletSession.APPLICATION_SCOPE);
		String userLanguage = ldapUserData.get(LexmarkConstants.LANGUAGE);
		String userCountry = ldapUserData.get(LexmarkConstants.COUNTRY);
		String userSegment = ldapUserData.get(LexmarkConstants.USERSEGMENT);
		//Per the LDAP API - the following userId will contain short id employees and email address for buspartners
		String userId = ldapUserData.get(LexmarkConstants.SHORTID);
		model.addAttribute("mdmParamRequire", mdmParamRequire);
		model.addAttribute("isPublishing", isPublishing);
		model.addAttribute("userLanguage", userLanguage);
		model.addAttribute("userCountry", userCountry);
		model.addAttribute("userSegment", userSegment);
		model.addAttribute("userId", userId);
		
		if(null == portletSession.getAttribute("analyticsSessionId", PortletSession.APPLICATION_SCOPE) || "".equals(portletSession.getAttribute("analyticsSessionId", PortletSession.APPLICATION_SCOPE).toString().trim())){
			model.addAttribute("analyticsSessionID", false);
		}else{
			model.addAttribute("analyticsSessionID", true);
		}
		
		} catch (Exception e) {
			logger.debug("Exception message "+e.getMessage());
		}
		logger.debug("--------------Exit from ChartController method 1------------------");
		return "chart/showChart";
	}


	/**
	 * @param request 
	 * @param response 
	 * @param model 
	 * @throws Exception 
	 */
	@ResourceMapping(value="retrieveChart")
	public void retrieveChart(ResourceRequest request, ResourceResponse response, Model model) throws Exception{//should be synchronized
		logger.debug("--------------Entry to ChartController method 2------------------");
		
		
		//---------------------------------------BELOW ISD THE ORIGINAL ONE -----------------------------------------------------		 
		
		/*PortletPreferences prefs = request.getPreferences();
		String chartPath = prefs.getValue("chartPath", "");
		boolean mdmParamRequire = Boolean.parseBoolean(prefs.getValue("requireMdmParams","noValue"));
		logger.debug("mdmParamRequire is "+mdmParamRequire);
		List<ObieeReportParameter> reportParameterList = getOracleReportParameters(prefs);

		String reportHtml;
		
		if (chartPath == null || chartPath.equals("")){
			reportHtml = "<B>" + ServiceStatusErrorMessageUtil.getErrorMessage(ERROR_MESSAGE_BUNDLE, "analyticsReport.missingReportConfiguration", null, request.getLocale()) + "</B>";
		}else{
			reportHtml = sharedObieeController.getReportHtml(request, mdmParamRequire, chartPath, reportParameterList);
			model.addAttribute("reportHtml", reportHtml);
			logger.debug("--------------Exit from ChartController------------------");
		}
		boolean isPublishing = LexmarkUserUtil.isPublishing(request);
		model.addAttribute("isPublishing",isPublishing);
		logger.debug(reportHtml);
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		logger.debug("Converted content type to HTML");
		out.print(reportHtml);
		out.flush();
		out.close();
		logger.debug("--------------Exit from ChartController method 2------------------");*/
		
		
		//------------------------- CI-6 Added : Start --------------------
		PortletSession session = request.getPortletSession();
		String sessionID = "";
		if(null == session.getAttribute("analyticsSessionId", PortletSession.APPLICATION_SCOPE) || "".equals(session.getAttribute("analyticsSessionId", PortletSession.APPLICATION_SCOPE).toString().trim())){
	      sessionID = request.getParameter("sessionID");
	      logger.debug("session id is --------->>>>>>>>> "+sessionID);
	      session.setAttribute("analyticsSessionId", sessionID, PortletSession.APPLICATION_SCOPE);
		}
		else{
			sessionID = session.getAttribute("analyticsSessionId", PortletSession.APPLICATION_SCOPE).toString();
			 logger.debug("session id is --------->>>>>>>>> "+sessionID);
		}		
		

		String chartPath = "";
		boolean mdmParamRequire = true;
		CustomizeChartServiceImpl customizeChartService = new CustomizeChartServiceImpl();
		String portletId = (String)request.getAttribute(WebKeys.PORTLET_ID);
		logger.debug("----- PORTLET INSTANCE ID----- "+portletId);
		
		String mdmId = (String)PortalSessionUtil.getMdmId(request.getPortletSession());
		String dunsNumber = (String)PortalSessionUtil.getMdmIdOrDunsBasedOnLevel(request.getPortletSession());
		logger.info("===user MdmId : "+mdmId);
		logger.info("===duns : "+dunsNumber);
		if(dunsNumber != null && !"".equals(dunsNumber)){
			mdmId = dunsNumber;
		}
		/*String mdmLevel = (String)PortalSessionUtil.getMdmLevel(request.getPortletSession());
		GlobalServiceFacadeImpl globalServiceFacade = new GlobalServiceFacadeImpl();
		GlobalAccount account = globalServiceFacade.retriveGlobalAccount(mdmId, mdmLevel);
		if(account != null){
			mdmId = account.getMdmId();
			logger.debug("Inside account section");
		}
		logger.info("======================================Account MdmId : "+mdmId);
		*/
		ChartInfo contract = new ChartInfo();
			contract.setMdmId(mdmId);
			contract.setWindow(customizeChartService.getWindowPosition(portletId));
			
		ChartInfo returnResult = customizeChartService.getChartDetails(contract);
		
		//Fetch the default chart if by default no chart selcted for any window
		if(returnResult == null){
			if("L".equals(contract.getWindow())){
				contract.setChartName(LexmarkConstants.DEFAULT_CHART_MAP.get("LEFT_CHART"));
			}else
			if("R".equals(contract.getWindow())){
				contract.setChartName(LexmarkConstants.DEFAULT_CHART_MAP.get("RIGHT_CHART"));
			}
			returnResult = customizeChartService.getChartDetailsByChartName(contract);
			//logger.info("^^^^^^^^^^^^^^^^^^^^^^^^^ 4 ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ : "+returnResult);
		}
		
		if(returnResult != null){
			chartPath = returnResult.getChartUrl();
			try {
				mdmParamRequire = Boolean.parseBoolean(returnResult.getMdmRequired().toLowerCase());
			} catch (Exception e) {
				logger.error("-------------- Error in string to boolean conversion -------"+e);
			}
		}
		//------------------------- CI-6 Added : End --------------------
		
		PortletPreferences prefs = request.getPreferences();
		List<ObieeReportParameter> reportParameterList = getOracleReportParameters(prefs);

		String reportHtml;
		
		logger.debug("========= chartPath           : "+chartPath);
		logger.debug("========= chartName           : "+returnResult.getChartName());
		logger.debug("========= mdmId               : "+mdmId);
		logger.debug("========= mdmParamRequire     : "+mdmParamRequire);
		logger.debug("========= reportParameterList : "+reportParameterList);
		logger.debug("========= Window              : "+contract.getWindow());
		logger.debug("========= Portal Instance Id  : "+portletId);
		
		if (chartPath == null || chartPath.equals("")){
			reportHtml = "<B>" + ServiceStatusErrorMessageUtil.getErrorMessage(ERROR_MESSAGE_BUNDLE, "analyticsReport.missingReportConfiguration", null, request.getLocale()) + "</B>";
		}else{
			reportHtml = sharedObieeController.getReportHtml(request, mdmParamRequire, chartPath, reportParameterList, sessionID);
			if("L".equals(contract.getWindow())){
				logger.debug("holding the time for 15s");
				Thread.sleep(15000L);
			}else{
				logger.debug("holding the time for 10s");
				Thread.sleep(10000L);
			}
			reportHtml = sharedObieeController.getReportHtml(request, mdmParamRequire, chartPath, reportParameterList, sessionID);
			model.addAttribute("reportHtml", reportHtml);
		}
		logger.debug("========= reportHtml : "+reportHtml);
		
		boolean isPublishing = LexmarkUserUtil.isPublishing(request);
		model.addAttribute("isPublishing",isPublishing);
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		logger.debug("Whether HTML will published or not ? : "+ isPublishing);
		logger.debug("Converted content type to HTML : "+ reportHtml);
		out.print(reportHtml);
		out.flush();
		out.close();
		logger.debug("--------------Exit from ChartController method 2------------------");
	}


	/**
	 * @param model 
	 * @param request 
	 * @param response 
	 * @return String 
	 * @throws Exception 
	 */
	@RequestMapping(params = "action=showConfigurationPage")
	public String showChartConfiguration(Model model, 
			RenderRequest request,
			RenderResponse response
	)throws Exception {
		logger.debug("--------------Entry to showChartConfiguration------------------");
		PortletPreferences prefs = request.getPreferences();
		String[] noValue = new String[0];
		String paramType;
		String paramName;
		String paramValue;
		String paramValue2;
		String chartPath = prefs.getValue("chartPath", "");
		boolean mdmParamRequire = Boolean.parseBoolean(prefs.getValue("requireMdmParams","noValue"));
		logger.debug("In showConfigurationPage mdmParamRequire is "+mdmParamRequire);
		logger.debug("In showConfigurationPage chartPath is "+chartPath);
		List <ChartParameter> parameterList = new ArrayList<ChartParameter>();
		ChartParameterDetail chartParameterDetail = new ChartParameterDetail();
		ChartParameterDetailContract chartParameterDetailContract = new ChartParameterDetailContract();
		String[] paramTypeArray = prefs.getValues("paramTypeArray", noValue);
		String[] paramNameArray = prefs.getValues("paramNameArray", noValue);
		int paramArrayLen = paramNameArray.length;
		for(int i=0;i<paramArrayLen;i++){
			paramType = paramTypeArray[i];
			paramName = paramNameArray[i];
			paramValue = prefs.getValue(paramName, "");
			ChartParameter chartParameter = new ChartParameter();
			chartParameter.setParamType(paramType);
			chartParameter.setParamName(paramName);
			chartParameter.setParamValue(paramValue);
			logger.debug("In showDeviceListPage paramType is " + paramType + " paramValue is "+paramValue+" for paramName "+paramName);
			if (paramType.equalsIgnoreCase(SharedObieeController.PARAM_TYPE_ANALYTICS_DATE_BETWEEN)|| 
					paramType.equalsIgnoreCase(SharedObieeController.PARAM_TYPE_ANALYTICS_QUARTER_BETWEEN)){
				paramValue2 = prefs.getValue(paramName + "_2", "");
				chartParameter.setParamValue2(paramValue2);
			}
			parameterList.add(chartParameter);
		}
		chartParameterDetail.setParameterList(parameterList);
		chartParameterDetail.setChartPath(chartPath);
		chartParameterDetail.setIsRequireMdmParams(mdmParamRequire);

		chartParameterDetailContract.setChartParameterDetail(chartParameterDetail);
		model.addAttribute("chartParameterDetailContract", chartParameterDetailContract);
		SharedObieeController.populateParameterOptions(model);
		return "chart/addChartParams";
	}

	/**
	 * @param model 
	 * @param request 
	 * @param response 
	 * @param chartParameterDetailContract 
	 * @param result 
	 * @throws Exception 
	 */
	@RequestMapping(params = "action=saveChartParameter")
	public void saveChartParameter(Model model, 
			ActionRequest request,
			ActionResponse response,
			@ModelAttribute("chartParameterDetailContract")
			ChartParameterDetailContract chartParameterDetailContract,
			BindingResult result)throws Exception {
		ChartParameterDetail chartParameterDetail = chartParameterDetailContract.getChartParameterDetail();
		logger.debug("Values of chart path "+chartParameterDetail.getChartPath());
		PortletPreferences prefs = request.getPreferences();
		prefs.setValue("chartPath", chartParameterDetail.getChartPath());
		logger.debug("isRequireMdmParams value is "+chartParameterDetail.getIsRequireMdmParams());
		String paramType;
		String paramName;
		String paramValue;
		String paramValue2;
		if(chartParameterDetail.getIsRequireMdmParams()){
			prefs.setValue("requireMdmParams","true");
			logger.debug("requiremdm param value set is true");
		}else{
			prefs.setValue("requireMdmParams","false");
			logger.debug("requiremdm param value set is false");
		}
		int i =0;
		for (i = 0; request.getParameter("parameterList[" + i + "].paramName") != null; i ++) {
			paramType = request.getParameter("parameterList[" + i + "].paramType");
			paramName = request.getParameter("parameterList[" + i + "].paramName");
			paramValue = request.getParameter("parameterList[" + i + "].paramValue");
			if (request.getParameter("parameterList[" + i + "].paramName").length() == 0) {
				continue;
			}
			logger.debug("setting following values "+paramName+" and "+paramValue);
			prefs.setValue(paramName, paramValue);
			
			if (paramType.equalsIgnoreCase(SharedObieeController.PARAM_TYPE_ANALYTICS_DATE_BETWEEN)|| 
					paramType.equalsIgnoreCase(SharedObieeController.PARAM_TYPE_ANALYTICS_QUARTER_BETWEEN)){
				paramValue2 = request.getParameter("parameterList[" + i + "].paramValue2");
				prefs.setValue(paramName + "_2", paramValue2);
			}
		}
		int noOfParams = i;
		logger.debug("No of parameters "+noOfParams);
		String[] paramTypeArray = new String[noOfParams];
		String[] paramNameArray = new String[noOfParams];
		for (int j=0;j<noOfParams;j++){
			paramNameArray[j]=request.getParameter("parameterList[" + j + "].paramName");
			logger.debug("setting values "+request.getParameter("parameterList[" + j + "].paramName"));
			paramTypeArray[j] = request.getParameter("parameterList[" + j + "].paramType");
		}
		prefs.setValues("paramNameArray", paramNameArray);
		prefs.setValues("paramTypeArray", paramTypeArray);
		prefs.store();
	}
	
	/**
	 * @param prefs 
	 * @return  List<ObieeReportParameter> 
	 */
	private List<ObieeReportParameter> getOracleReportParameters(PortletPreferences prefs){
		List<ObieeReportParameter> reportParameterList = new ArrayList<ObieeReportParameter>();
		String[] noValue = new String[0];
		String[] paramTypeArray = prefs.getValues("paramTypeArray", noValue);
		String[] paramNameArray = prefs.getValues("paramNameArray", noValue);
		int paramArrayLen = paramNameArray.length;
		for (int j=0;j<paramArrayLen;j++){
			ObieeReportParameter oracleReportParameter = new ObieeReportParameter();
			String parameterType ;
			String parameterName ;
			String parameterValue ;
			String parameterValue2 ;
			parameterType = paramTypeArray[j];
			parameterName = paramNameArray[j];
			parameterValue = prefs.getValue(parameterName, "");
			logger.debug("report parametername is : "+parameterName);
			logger.debug("param value is : "+parameterValue);
			oracleReportParameter.setParameterType(parameterType);
			oracleReportParameter.setParameterName(parameterName);
			oracleReportParameter.setParameterValue(parameterValue);

			if (parameterType.equalsIgnoreCase(SharedObieeController.PARAM_TYPE_ANALYTICS_DATE_BETWEEN) || 
					parameterType.equalsIgnoreCase(SharedObieeController.PARAM_TYPE_ANALYTICS_QUARTER_BETWEEN)){
				logger.debug(parameterType);		
				parameterValue2 = prefs.getValue(parameterName + "_2", "");
				oracleReportParameter.setParameterValue2(parameterValue2);
			}
			reportParameterList.add(oracleReportParameter);

		}
		
		return reportParameterList;
	}

}
