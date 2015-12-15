/**
* Copyright@ 2012. This document has been created & written by
 * Wipro technologies for Lexmark and this is copyright to Lexmark
 *
 * File         	: CustomerReportsController
 * Package     		: com.lexmark.services.portlet
 * Creation Date 	: 
 *
 * Modification History:
 *
 * ---------------------------------------------------------------------
 * Author 			Date				Version  		Comments
 * ---------------------------------------------------------------------
 * Wipro						 		1.0             Initial Version
 *
 */
package com.lexmark.services.portlet;

import static com.lexmark.services.LexmarkSPConstants.ERROR_MESSAGE_BUNDLE;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletSession;
import javax.portlet.PortletURL;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.portlet.ResourceURL;
import javax.servlet.http.HttpServletRequest;


import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.contract.ObieeReportParameterContract;
import com.lexmark.contract.ObieeReportParameterListContract;
import com.lexmark.contract.ReportDefinitionDisplayContract;
import com.lexmark.contract.ReportHierarchyContract;
import com.lexmark.contract.ReportListContract;
import com.lexmark.contract.ScheduleReportContract;
import com.lexmark.domain.ObieeReportParameter;
import com.lexmark.domain.Report;
import com.lexmark.domain.ReportParameters;
import com.lexmark.domain.ServicesUser;
import com.lexmark.emc.client.servicesweb.DocumentumWebServiceFacade;
import com.lexmark.properties.schema.sw.document.DocumentProperties.DocumentInfo;
import com.lexmark.result.ObieeReportDefinitionResult;
import com.lexmark.result.ObieeReportParameterListResult;
import com.lexmark.result.ReportDefinitionDisplayResult;
import com.lexmark.result.ReportHierarchyResult;
import com.lexmark.result.ReportListResult;
import com.lexmark.result.ReportListRow;
import com.lexmark.result.ReportParamsResult;
import com.lexmark.result.ScheduleReportResult;
import com.lexmark.service.api.CustomerReportService;
import com.lexmark.service.api.GlobalService;
import com.lexmark.service.api.ReportScheduleService;
import com.lexmark.services.form.OracleReportForm;
import com.lexmark.services.form.RunReportNowForm;
import com.lexmark.services.form.ScheduleReportForm;
import com.lexmark.services.util.ChangeMgmtConstant;
import com.lexmark.services.util.ContractFactory;
import com.lexmark.services.util.DocumentumWebServiceUtil;
import com.lexmark.services.util.LexmarkUserUtil;
import com.lexmark.services.util.PortalSessionUtil;
import com.lexmark.services.util.ReportPathUtil;
import com.lexmark.services.util.ServiceStatusErrorMessageUtil;
import com.lexmark.services.util.XmlOutputGenerator;
import com.lexmark.util.DateLocalizationUtil;
import com.lexmark.util.PropertiesMessageUtil;
import com.lexmark.util.StringUtil;
import com.lexmark.util.TimezoneUtil;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.util.PortalUtil;

@Controller
@RequestMapping("VIEW")
public class CustomerReportsController extends BaseController{

	@Autowired
	private SharedObieeController sharedObieeController;

	private static Logger logger = LogManager.getLogger(CustomerReportsController.class);
	private static final String MUType = "MU";
	private static final String BOType = "BO";
	private static final String OAType = "OA";//Added by mithun for OBIEE report
	private static final String EMPLOYEE = "employee";

	@Autowired
	private CustomerReportService customerReportService;
	
	@Autowired
	private ReportScheduleService reportScheduleService;
	@Autowired
	private GlobalService globalService;
	@Autowired
	private SharedPortletController sharedPortletController;
	

	@RequestMapping
	public String showDefaultView(Model model, RenderRequest request,
            RenderResponse response) throws Exception {
		sharedPortletController.checkAccountTypeFlagsInSession(request);
		ReportHierarchyContract contract = ContractFactory.getReportHierarchyContract(request, globalService);
		ReportHierarchyResult result = customerReportService.retrieveReportHierarchy(contract);
//		added for employee report
		ServicesUser servicesUsr = PortalSessionUtil.getServiceUser(request.getPortletSession());
		String accountName = servicesUsr.getAccountName();
		String employeeAccountName = (String)request.getPortletSession().getAttribute("employeeAccountName", request.getPortletSession().APPLICATION_SCOPE);
		if(null == accountName) {
			accountName = employeeAccountName;
		}
		boolean isEmployee = false;
		if(EMPLOYEE.equalsIgnoreCase(PortalSessionUtil.getUserSegment(request.getPortletSession()))) {
			isEmployee  = true;
		}
		model.addAttribute("accountName", accountName);
		model.addAttribute("isEmployee", isEmployee);
		logger.debug("### name of the account is: " + accountName);
		logger.debug("### name of the emp account is: " + employeeAccountName);
//		end of addition for employee report
		model.addAttribute("definitionName", PropertiesMessageUtil.getPropertyMessage(null, "customerReports.defaultView.latest", request.getLocale()));
		model.addAttribute("definitionDesc", PropertiesMessageUtil.getPropertyMessage(null, "customerReports.defaultView.latestDesc", request.getLocale()));
		model.addAttribute("roleCategories", result.getRoleCategories());
		model.addAttribute("deleteResult", request.getParameter("deleteResult"));
		//Added for 14.9 Potential CR 14722 START
		String roleCategoryIdArrayPlusArray= "";
		String roleCategoryIdArrayMinusArray= "";
		List<String> roleCategoryIdArrayPlusList=Arrays.asList(roleCategoryIdArrayPlusArray.split(",",-1));
		List<String> roleCategoryIdArrayMinusList=Arrays.asList(roleCategoryIdArrayMinusArray.split(",",-1));
		model.addAttribute("roleCategoryIdArrayPlusList", roleCategoryIdArrayPlusList);
		model.addAttribute("roleCategoryIdArrayMinusList", roleCategoryIdArrayMinusList);
		// Added for 14.9 Potential CR 14722 END
		return "customerReports/defaultView";
	}
	
	@RequestMapping(params = "action=showReports")
	public String showReports(Model model, RenderRequest request,
			RenderResponse response) throws Exception {
		logger.debug("----------------starting showReports method-------------------");
		boolean paramAvailable = false;
		boolean editReportParam = false;
		PortletSession session = request.getPortletSession();
		sharedPortletController.checkAccountTypeFlagsInSession(request);
		logger.debug("*********MdmId:" + PortalSessionUtil.getMdmId(session));
		logger.debug("*********MdmLevel:" + PortalSessionUtil.getMdmLevel(session));
		String docDefinitionId = request.getParameter("docDefinitionId");
		String roleCategoryId = request.getParameter("roleCategoryId");
	
		//Added for 14.9 Potential CR 14722 START
		List<String> roleCategoryIdArrayPlusList = new ArrayList<String>();
		List<String> roleCategoryIdArrayMinusList = new ArrayList<String>();
		if(request.getParameter("editReport")!=null){
			if((null != request.getParameter("roleCategoryIdArrayPlusEdit")) && (null != request.getParameter("roleCategoryIdArrayMinusEdit"))){
				String roleCategoryIdArrayPlusArrayEdit= request.getParameter("roleCategoryIdArrayPlusEdit");
				String roleCategoryIdArrayMinusArrayEdit= request.getParameter("roleCategoryIdArrayMinusEdit");
				roleCategoryIdArrayPlusList=Arrays.asList(roleCategoryIdArrayPlusArrayEdit.split(",",-1));
				roleCategoryIdArrayMinusList=Arrays.asList(roleCategoryIdArrayMinusArrayEdit.split(",",-1));
			}
		}
		else{
			HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));
			if((null != httpReq.getParameter("roleCategoryIdArrayPlus")) && (null != httpReq.getParameter("roleCategoryIdArrayMinus"))){
				String roleCategoryIdArrayPlusArray= httpReq.getParameter("roleCategoryIdArrayPlus");
				String roleCategoryIdArrayMinusArray= httpReq.getParameter("roleCategoryIdArrayMinus");
				roleCategoryIdArrayPlusList=Arrays.asList(roleCategoryIdArrayPlusArray.split(",",-1));
				roleCategoryIdArrayMinusList=Arrays.asList(roleCategoryIdArrayMinusArray.split(",",-1));
				}
			
		}
		//Added for 14.9 Potential CR 14722 END
		
		//PARTHA ADDED FOR PAGE REFRESH START
    	if("Schedule".equals(session.getAttribute("navigationFromPage"))){
    	    session.removeAttribute("navigationFromPage");
    	    docDefinitionId = (String)session.getAttribute("docDefinitionIdInSession");
    	    roleCategoryId = (String)session.getAttribute("roleCategoryIdInSession");
    	}
    	if("ReportList".equals(session.getAttribute("refreshPage"))){
    	    session.removeAttribute("refreshPage");
    	    model.addAttribute("refreshStatus", "success");
    	}
    	//Set the value for if navigation comes from schedule page
    	if (docDefinitionId == null){
    	    docDefinitionId = (String)session.getAttribute("docDefinitionIdInSession");
    	    roleCategoryId = (String)session.getAttribute("roleCategoryIdInSession");   		
    	}
    	session.setAttribute("docDefinitionIdInSession", docDefinitionId);
    	session.setAttribute("roleCategoryIdInSession", roleCategoryId);
    	//PARTHA ADDED FOR PAGE REFRESH END
		ReportHierarchyContract contract = ContractFactory.getReportHierarchyContract(request, globalService);
		ReportHierarchyResult result = customerReportService.retrieveReportHierarchy(contract);
		model.addAttribute("roleCategories", result.getRoleCategories());
		model.addAttribute("roleCategoryId", roleCategoryId);
		model.addAttribute("docDefinitionId", docDefinitionId);
		model.addAttribute("deleteResult", request.getParameter("deleteResult"));
		model.addAttribute("allSupportFileExtension", getAllSupportFileExtensionString());
		boolean isPublishing = LexmarkUserUtil.isPublishing(request);
		model.addAttribute("isPublishing", isPublishing);
		// added for employee report
		ServicesUser servicesUsr = PortalSessionUtil.getServiceUser(session);
		String accountName = servicesUsr.getAccountName();
		String employeeAccountName = (String) request.getPortletSession().getAttribute("employeeAccountName", request.getPortletSession().APPLICATION_SCOPE);
		if (accountName== null) {
			accountName = employeeAccountName;
		}
		model.addAttribute("accountName", accountName);
		logger.debug("### account name inside showReports: " + accountName);
		logger.debug("### account name inside showReports for emp: " + employeeAccountName);
		// end of addition for employee report
		
		boolean isEmployee = false;
		if (EMPLOYEE.equalsIgnoreCase(PortalSessionUtil.getUserSegment(session))) {
			isEmployee = true;
		}
		model.addAttribute("isEmployee", isEmployee);
		//Changes for 14.9 CR 14722
		if ("101".equals(docDefinitionId)) {
			model.addAttribute("definitionName", PropertiesMessageUtil.getPropertyMessage(null,
							"customerReports.defaultView.latest", request
									.getLocale()));
			model.addAttribute("definitionDesc", PropertiesMessageUtil.getPropertyMessage(null,
							"customerReports.defaultView.latestDesc", request.getLocale()));
			//Added for 14.9 Potential CR 14722 START
			model.addAttribute("roleCategoryIdArrayPlusList", roleCategoryIdArrayPlusList);
			model.addAttribute("roleCategoryIdArrayMinusList", roleCategoryIdArrayMinusList);
			return "customerReports/defaultView";
		} else {
			ReportDefinitionDisplayContract definitionDisplayContract = ContractFactory.getDefinitionDisplayContract(request);
			logger.debug("@@@@@@@@@@@@ docDefinitionId value is " + docDefinitionId);
			definitionDisplayContract.setDefinitionId(docDefinitionId);
			ReportDefinitionDisplayResult displayResult = customerReportService.getReportDefinitionDisplay(definitionDisplayContract);
			model.addAttribute("definitionDisplayForm", displayResult); // contains
																		// value
																		// to be
																		// displayed
																		// on
																		// scheduledReportList.jsp

			if (MUType.equals(displayResult.getDefinitionType())) {
				model.addAttribute("uploadResult", request.getParameter("uploadResult"));
				ResourceURL url = response.createResourceURL();
				url.setResourceID("manuUploadReportsList");
				displayResult.setDefinitionURL(url.toString());
				//Added for 14.9 Potential CR 14722 START
				model.addAttribute("roleCategoryIdArrayPlusList", roleCategoryIdArrayPlusList);
				model.addAttribute("roleCategoryIdArrayMinusList", roleCategoryIdArrayMinusList);
				return "customerReports/manuUploadReportsList";
			} else if (BOType.equals(displayResult.getDefinitionType())) {
				RunReportNowForm runReportNowForm = new RunReportNowForm();
				runReportNowForm.setRoleCategoryId(roleCategoryId);
				runReportNowForm.setDocDefinitionId(docDefinitionId);
				runReportNowForm.refreshSubmitToken(request);
				model.addAttribute("runReportNowForm", runReportNowForm);
				model.addAttribute("runNowFailed", request.getParameter("runNowFailed"));
				model.addAttribute("runNowSuccess", request.getParameter("runNowSuccess"));
				ResourceURL url = response.createResourceURL();
				url.setResourceID("scheduledReportsList");
				displayResult.setDefinitionURL(url.toString());
				//Added for 14.9 Potential CR 14722 START
				model.addAttribute("roleCategoryIdArrayPlusList", roleCategoryIdArrayPlusList);
				model.addAttribute("roleCategoryIdArrayMinusList", roleCategoryIdArrayMinusList);
				return "customerReports/scheduledReportsList";
			} else if (OAType.equals(displayResult.getDefinitionType())) {
				logger.debug("!!!!!!!!!!!!!!!!!!!!!doc definition id is : " + docDefinitionId);
				String editReport = request.getParameter("editReport");
				logger.debug("###################### editReport value " + editReport);
				if (editReport != null) {
					editReportParam = true;
					logger.debug("########################editParamvalue is " + editReportParam);
				}
				HashMap<String, String> ldapUserData = (HashMap<String, String>)session.getAttribute(LexmarkConstants.LDAP_USER_DATA, session.APPLICATION_SCOPE);
				String contactId = ldapUserData.get(LexmarkConstants.CONTACTID);
 
				ObieeReportParameterListContract obieeReportParameterListContract = new ObieeReportParameterListContract();
				obieeReportParameterListContract.setReportDefinitionId(new Integer(docDefinitionId));
				obieeReportParameterListContract.setContactId(contactId);
				ObieeReportParameterListResult obieeReportParameterListResult = customerReportService.getObieeReportParameterList(obieeReportParameterListContract);
				
				List<ObieeReportParameter> reportParameterList = obieeReportParameterListResult.getObieeReportParameterList();
				boolean requiredParamValued = true;
				for (ObieeReportParameter param : reportParameterList) {
					if (param.getIsParameterRequired() &&
							StringUtil.isStringEmpty(param.getParameterValue())) {
						requiredParamValued = false;
						break;
					}
				}
				
				if (!editReportParam && requiredParamValued) {
					
					PortletSession portletSession = request.getPortletSession();
					
					String userLanguage = ldapUserData.get(LexmarkConstants.LANGUAGE);
					String userCountry = ldapUserData.get(LexmarkConstants.COUNTRY);
					String userSegment = ldapUserData.get(LexmarkConstants.USERSEGMENT);
					//Per the LDAP API - the following userId will contain short id employees and email address for buspartners
					String userId = ldapUserData.get(LexmarkConstants.SHORTID);
					
					model.addAttribute("userLanguage", userLanguage);
					model.addAttribute("userCountry", userCountry);
					model.addAttribute("userSegment", userSegment);
					model.addAttribute("userId", userId);
					model.addAttribute("docDefinitionId", docDefinitionId);
					//Added for 14.9 Potential CR 14722 START
					model.addAttribute("roleCategoryIdArrayPlusList", roleCategoryIdArrayPlusList);
					model.addAttribute("roleCategoryIdArrayMinusList", roleCategoryIdArrayMinusList);
					return "customerReports/showOracleReport";
				} else {
					OracleReportForm form = new OracleReportForm();
					form.setParameters(reportParameterList);
					model.addAttribute("reportOracleForm", form);
					SharedObieeController.populateParameterOptions(model);
					if (editReport != null) {
						model.addAttribute("editReport", editReport);
						logger.debug("########################editReport is " + editReport);
					}
					//Added for 14.9 Potential CR 14722 START
					model.addAttribute("roleCategoryIdArrayPlusList", roleCategoryIdArrayPlusList);
					model.addAttribute("roleCategoryIdArrayMinusList", roleCategoryIdArrayMinusList);
					logger.debug("---------------completed else block of the showReports action--------------------");
					return "customerReports/oracleReport";
				}
			}
		}
		return "customerReports/defaultView";
	}
	
	@ResourceMapping(value="retrieveChart")
	
	public void retrieveChart(ResourceRequest request, ResourceResponse response, Model model) throws Exception{//should be synchronized
		logger.debug("--------------Entry to ChartController method 2------retrieveChart------------");
		String sessionID = request.getParameter("sessionID");
		String docDefinitionId = request.getParameter("docDefinitionId");
		//---------------------------------------BELOW ISD THE ORIGINAL ONE -----------------------------------------------------		 
		String chartPath = "";
		boolean mdmParamRequire = false;
		PortletSession session = request.getPortletSession();
		ObieeReportDefinitionResult obieeReportDefinitionParameterResult = customerReportService.getObieeReportDefinitionByDefinitionId(docDefinitionId);
		chartPath = obieeReportDefinitionParameterResult.getReportDefinition().getReportSourceId();
		logger.info("Chart path is "+chartPath);
		mdmParamRequire = obieeReportDefinitionParameterResult.getReportDefinition().getIsSendMDMParameter();
		logger.debug("######################chartPath is " + chartPath);
		logger.debug("######################mdmParamRequire is " + mdmParamRequire);
		logger.info("######################mdmParamRequire is " + mdmParamRequire);
		logger.debug("######################docDefinitionId is " + docDefinitionId);
		logger.info("######################docDefinitionId is " + docDefinitionId);
		HashMap<String, String> ldapUserData = (HashMap<String, String>)session.getAttribute(LexmarkConstants.LDAP_USER_DATA, session.APPLICATION_SCOPE);
		String contactId = ldapUserData.get(LexmarkConstants.CONTACTID);
		ObieeReportParameterListContract obieeReportParameterListContract = new ObieeReportParameterListContract();
		obieeReportParameterListContract.setReportDefinitionId(Integer.valueOf(docDefinitionId));
		obieeReportParameterListContract.setContactId(contactId);
		ObieeReportParameterListResult obieeReportParameterListResult = customerReportService.getObieeReportParameterList(obieeReportParameterListContract);
		
		List<ObieeReportParameter> reportParameterList = obieeReportParameterListResult.getObieeReportParameterList();
		 try
    {
		String reportHtml;
		if (chartPath == null || chartPath.equals("")){
			reportHtml = "<B>" + ServiceStatusErrorMessageUtil.getErrorMessage(ERROR_MESSAGE_BUNDLE, "analyticsReport.missingReportConfiguration", null, request.getLocale()) + "</B>";
		}else{
		     Thread.sleep(10000L);
			reportHtml = sharedObieeController.getReportHtml(request, mdmParamRequire, chartPath, reportParameterList, sessionID);
			
			model.addAttribute("reportHtml", reportHtml);
			logger.debug("--------------Exit from ChartController------------------");
		}
		logger.debug(reportHtml);
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		logger.debug("Converted content type to HTML");
		out.print(reportHtml);
		out.flush();
		out.close();
		
		}
		
		catch (InterruptedException e)
    {
      logger.debug("Interrupted Exception "+e.getMessage());
    }
		logger.debug("--------------Exit from ChartController method 2------------------");
	}
	
	private String getAllSupportFileExtensionString() {
		StringBuilder sb = new StringBuilder();
		int i = 0;
		
		for(String s :DocumentumWebServiceUtil.getSupportFileExtensions()) {
			if(i!=0) {
				sb.append(",");
			}
			sb.append("\"" + s +"\"");
			i++;
		}
		return sb.toString();
	}
		
	
	@ResourceMapping
	public void retrieveTop10ReportsList(ResourceRequest request, ResourceResponse response) throws Exception{
		sharedPortletController.checkAccountTypeFlagsInSession(request);
		ReportListContract contract = ContractFactory.getReportListContract(request, globalService);
//		added for employee report
		ReportListResult result = null;
		PortletSession sess = request.getPortletSession();
		String acctNm = PortalSessionUtil.getServiceUser(sess).getAccountName();
		if(null == acctNm) {
			acctNm = (String)sess.getAttribute("employeeAccountName", sess.APPLICATION_SCOPE);
		}
		contract.setAccountNm(acctNm);
		String userSgmt = PortalSessionUtil.getUserSegment(sess);

		if(EMPLOYEE.equalsIgnoreCase(userSgmt)) {
			logger.debug("### getting top10 report for employee");
			String employeeMdmId = (String)request.getPortletSession().getAttribute("employeeReportMdmId", request.getPortletSession().APPLICATION_SCOPE);
			contract.setEmployeeMdmId(employeeMdmId);
			result = customerReportService.retrieveTopTenReportListEmployee(contract);
		}	
		else {
			logger.debug("### getting top10 report for cux");
			result = customerReportService.retrieveTopTenReportList(contract);
		}
//		end of addition for employee report

		if(request.getParameter("timezone") != null) {
			// Changed the time zone from int to float
			float clientTimezone = Float.valueOf(request.getParameter("timezone"));
			logger.debug("### request.getParameter :: "+request.getParameter("timezone"));
			logger.debug("### clientTimezone :: "+clientTimezone);
			logger.debug("### DocumentumWebServiceUtil.getDocumentumServerTimeOffset() :: "+DocumentumWebServiceUtil.getDocumentumServerTimeOffset());
			adjustReportsDate(result.getReportListRows(), clientTimezone - DocumentumWebServiceUtil.getDocumentumServerTimeOffset());
		}
		XmlOutputGenerator converter = getXmlOutputGenerator(request.getLocale());
		String xml = converter.convertCustomerReportsListWithStatusToXML(result.getReportListRows(), request.getContextPath(), response, getCanDeleteDocFlag(request));
		PrintWriter out = response.getWriter();
		response.setContentType("text/xml");
		out.print(xml);
		out.flush();
		out.close();		
	}
	
	@ResourceMapping("manuUploadReportsList")
	public void retrieveManuUploadReportsList(ResourceRequest request, ResourceResponse response) throws Exception{
		sharedPortletController.checkAccountTypeFlagsInSession(request);
		ReportListContract contract = ContractFactory.getReportListContract(request, globalService);
		String definitionId = request.getParameter("definitionId");
		contract.setReportDefinitionId(definitionId);
		ReportListResult result = customerReportService.retrieveManuUploadedReportsList(contract);
		if(request.getParameter("timezone") != null) {
			// Changed the time zone from int to float
			float clientTimezone = Float.valueOf(request.getParameter("timezone"));
			logger.debug("### request.getParameter :: "+request.getParameter("timezone"));
			logger.debug("### clientTimezone :: "+clientTimezone);
			logger.debug("### DocumentumWebServiceUtil.getDocumentumServerTimeOffset() :: "+DocumentumWebServiceUtil.getDocumentumServerTimeOffset());
			adjustReportsDate(result.getReportListRows(), clientTimezone - DocumentumWebServiceUtil.getDocumentumServerTimeOffset());
		}
		XmlOutputGenerator converter = getXmlOutputGenerator(request.getLocale());
		String xml = converter.convertCustomerReportsListWithoutStatusToXML(result.getReportListRows(), request.getContextPath(), response, getCanDeleteDocFlag(request));
		PrintWriter out = response.getWriter();
		response.setContentType("text/xml");
		out.print(xml);
		out.flush();
		out.close();		
	}
	
	
	@ResourceMapping("scheduledReportsList")
	public void retrieveScheduledReportsList (ResourceRequest request, ResourceResponse response) throws Exception{
		sharedPortletController.checkAccountTypeFlagsInSession(request);
		ReportListContract contract = ContractFactory.getReportListContract(request, globalService);
		String definitionId = request.getParameter("definitionId");
		contract.setReportDefinitionId(definitionId);
//		added for employee report
		ReportListResult result = null;
		PortletSession sess = request.getPortletSession();
		String accNm = PortalSessionUtil.getServiceUser(sess).getAccountName();
		if(null == accNm) {
			accNm = (String)sess.getAttribute("employeeAccountName", sess.APPLICATION_SCOPE);
		}
		contract.setAccountNm(accNm);
		String userSgmt = PortalSessionUtil.getUserSegment(sess);
		String mdmId = (String)request.getPortletSession().getAttribute("employeeReportMdmId", request.getPortletSession().APPLICATION_SCOPE);
		if(EMPLOYEE.equalsIgnoreCase(userSgmt)) {
			//*******added for LEX:AIR00060472 ********
			String employeeMdmId = (String)request.getPortletSession().getAttribute("employeeReportMdmId", request.getPortletSession().APPLICATION_SCOPE);
			contract.setEmployeeMdmId(employeeMdmId);
			//*********end********
			contract.setMdmId(mdmId);
			result = customerReportService.retrieveScheduledReportsListEmployee(contract);
			logger.debug("### getting particular report for employee with mdmId: " + contract.getMdmId());
		}	
		else {
			logger.debug("### getting particular report for cux with mdmId "+contract.getMdmId());
			result = customerReportService.retrieveScheduledReportsList(contract);
		}
//		end of addition for employee report

		if(request.getParameter("timezone") != null) {
		// Changed the time zone from int to float	
			float clientTimezone = Float.valueOf(request.getParameter("timezone"));
			logger.debug("### request.getParameter :: "+request.getParameter("timezone"));
			logger.debug("### clientTimezone :: "+clientTimezone);
			adjustReportsDate(result.getReportListRows(), clientTimezone - TimezoneUtil.getServerTimeZone());
		}
		XmlOutputGenerator converter = getXmlOutputGenerator(request.getLocale());
		String xml = converter.convertCustomerReportsListWithStatusToXML(result.getReportListRows(), request.getContextPath(), response, getCanDeleteDocFlag(request));
		PrintWriter out = response.getWriter();
		response.setContentType("text/xml");
		out.print(xml);
		out.flush();
		out.close();
	}
	
	@RequestMapping(params = "action=uploadFile")
	public void uploadReport(ActionRequest request, ActionResponse response,
							 @RequestParam("uploadFileInput")
							 CommonsMultipartFile content,
							 @RequestParam("fileContentDate")
							 String fileContentDate,
							 @RequestParam("timezone")
							 float timezone, //Done for CI Defect #8728
							 @RequestParam("definitionIdHidden")
							 String definitionId,
							 @RequestParam("fileNameHidden")
							 String filePath,
							 @RequestParam("roleCategoryIdHidden")
							 String roleCategoryId,
							 Model model) throws Exception{
		logger.debug("Entering method uploadReport");
		SimpleDateFormat defaultDateFormat = new SimpleDateFormat("MM/dd/yyyy");
		logger.debug("fileContentDate-------->>"+fileContentDate);
		Date dateFileContentDate = defaultDateFormat.parse(fileContentDate);
		logger.debug("dateFileContentDate after parsing-------->>"+dateFileContentDate);
		TimezoneUtil.adjustDate(dateFileContentDate, 0 - timezone);
		String reportUniqueId = reportScheduleService.getReportUniqueId().toString();
		DocumentInfo docInfo = DocumentumWebServiceUtil.getDocumentInfo(request, filePath, definitionId, reportUniqueId, DocumentumWebServiceUtil.DOCUMENT_TYPE_REPORT, globalService, dateFileContentDate);
		DocumentumWebServiceFacade facade = DocumentumWebServiceUtil.getDocumentumWebServiceFacade();
		try {
			facade.createDocument(docInfo, content.getBytes());
			response.setRenderParameter("uploadResult", "success");
		}catch(RuntimeException runtimeException) {
			if(!DocumentumWebServiceUtil.isFileFormatSupported(runtimeException)) {
				response.setRenderParameter("uploadResult", "uploadFileNotSupported");
			} else {
				logger.debug("Exception "+runtimeException.getMessage());
				response.setRenderParameter("uploadResult", "fail");
			}
		} catch (Exception e) {
			logger.debug("Exception message "+e.getMessage());
			response.setRenderParameter("uploadResult", "fail");
		}
	    response.setRenderParameter("roleCategoryId", roleCategoryId);
	    response.setRenderParameter("docDefinitionId", definitionId);
		response.setRenderParameter("action", "showReports");
		logger.debug("Exiting method uploadReport");
	}
	
	
	@RequestMapping(params = "action=runNowReport")
	public void runNowReport(ActionRequest request, ActionResponse response,
							@ModelAttribute("runReportNowForm")
							RunReportNowForm runReportNowForm,
							Model model) throws Exception{
		String definitionId = runReportNowForm.getDocDefinitionId();
		String roleCategoryId = runReportNowForm.getRoleCategoryId();

		ReportParamsResult result = null;					// added
		if(!PortalSessionUtil.isDuplicatedSubmit(request, runReportNowForm)){
			String userNumber = PortalSessionUtil.getServiceUser(request.getPortletSession()).getUserNumber();
			String mdmId = PortalSessionUtil.getServiceUser(request.getPortletSession()).getMdmId();
			
			if(EMPLOYEE.equalsIgnoreCase(PortalSessionUtil.getUserSegment(request.getPortletSession()))) {
                mdmId = (String)request.getPortletSession().getAttribute("employeeReportMdmId", request.getPortletSession().APPLICATION_SCOPE);
                logger.info("Setting mdmId for employee");
            }
			
			logger.debug("### retrieving report param for run now report- reportDefId: " + definitionId
					+ " userNumber: " + userNumber
					+ " mdmID: " + mdmId);
			


			result = customerReportService.retrieveParametersByDefinitionId(definitionId, userNumber, mdmId);			// added
			Boolean hasUnassignedRequiredParameter = false;
			
			for(ReportParameters parameter : result.getParameters()){
				if(parameter.getIsRequired() != null && parameter.getIsRequired()){
					String parameterName = parameter.getName();

//					added
					if(!result.getRunNowParametersValue().isEmpty()) {
						String parameterValue = result.getRunNowParametersValue().get(parameterName);	// added
						if(parameterValue == null){
							hasUnassignedRequiredParameter = true;
							break;
						}
					}
					else {
						hasUnassignedRequiredParameter = true;
					}
//					end of addition
				}
			}
			
			if(hasUnassignedRequiredParameter){
			    response.setRenderParameter("runNowFailed", PropertiesMessageUtil.getPropertyMessage(null, "customerReports.scheduleReport.canNotRunNow", request.getLocale()));
			}
			else{
				ScheduleReportContract contract =  ContractFactory.getRunThisReportContract(

						definitionId, result.getReportScheduleId(), request, result.getRunNowParametersValue());// added
				ScheduleReportResult savedScheduleResult = customerReportService.saveReportSchedule(contract);
			    response.setRenderParameter("runNowSuccess", PropertiesMessageUtil.getPropertyMessage(null, "customerReports.scheduleReport.runNowSuccess", request.getLocale()));
			}
		}
	    response.setRenderParameter("roleCategoryId", roleCategoryId);
	    response.setRenderParameter("docDefinitionId", definitionId);
		response.setRenderParameter("action", "showReports");
	}
	

	@RequestMapping(params = "action=showScheduleReportPage")
	public String showScheduleReportPage(Model model, 
										 RenderRequest request,
										 RenderResponse response)throws Exception {
		if("success".equals(request.getParameter("scheduleSuccess"))){
			//PARTHA ADDED FOR PAGE REFRESH START
		    PortletSession session = request.getPortletSession();
	        model.addAttribute("docDefinitionIdInSession", (String)session.getAttribute("docDefinitionIdInSession"));
	        model.addAttribute("roleCategoryIdInSession", (String)session.getAttribute("roleCategoryIdInSession"));
	        session.setAttribute("refreshPage", "ReportList");
	        //PARTHA ADDED FOR PAGE REFRESH END
			model.addAttribute("scheduleSuccess", "success");
		}
		else{
			logger.info("Inside else block");
			String definitionId = request.getParameter("definitionId");
			String userNumber = PortalSessionUtil.getServiceUser(request.getPortletSession()).getUserNumber();
			String mdmId = PortalSessionUtil.getServiceUser(request.getPortletSession()).getMdmId();	// added for employee report
			logger.info("!!!!!!!! PortalSessionUtil.getUserSegment(request.getPortletSession()) "
					+PortalSessionUtil.getUserSegment(request.getPortletSession()));
			if(EMPLOYEE.equalsIgnoreCase(PortalSessionUtil.getUserSegment(request.getPortletSession()))) {
                mdmId = (String)request.getPortletSession().getAttribute("employeeReportMdmId", request.getPortletSession().APPLICATION_SCOPE);
                logger.info("Setting mdmId for employee");
            }
			logger.debug("### retrieving report param for schedule report- reportDefId: " + definitionId
						+ " userNumber: " + userNumber
						+ " mdmID: " + mdmId);
			
 
			ReportParamsResult result = customerReportService.retrieveParametersByDefinitionId(definitionId, userNumber, mdmId);	// added, changed for employee report
			ScheduleReportForm form = new ScheduleReportForm();
			form.assemble(result, request.getLocale());
			model.addAttribute("reportScheduleForm", form);	
			//Added for CI-7 Defect #11656
			model.addAttribute("portalLocale", request.getLocale());
		}
		return "customerReports/scheduleReport";
	}
		
	@RequestMapping(params = "action=saveReportSchedule")
	public void saveReportSchedule(ActionRequest request, 
								   ActionResponse response,
								   Model model, 
								   @ModelAttribute("reportScheduleForm")
								   ScheduleReportForm scheduleReportForm) throws Exception{
		logger.debug("---------------------------in saveReportSchedule------------------------------ ");
		ScheduleReportContract contract = ContractFactory.getScheduleReportContract(scheduleReportForm, request);
		logger.debug("---------------------------in saveReportSchedule b4 api call------------------------------ ");
		// checking LBS Account Flag start
		PortletSession session = request.getPortletSession();
		Map<String, String> requestAccessMap= (Map<String, String>)session.getAttribute(ChangeMgmtConstant.USERACCESSMAPATTRIBUTEFORSR, PortletSession.APPLICATION_SCOPE);
		String fleetManageFlag = requestAccessMap.get("FLEET MANAGER FLAG");
		logger.debug("LBS account flag is -------------------->>>>>>>>> "+fleetManageFlag);
		String createMapRequest = requestAccessMap.get("CREATE MAP REQUEST");
		logger.debug("createMapRequest account flag is -------------------->>>>>>>>> "+createMapRequest);
		if(createMapRequest.trim().equalsIgnoreCase("True")){
			contract.getReportSchedule().setIsLBSAccount("T");
		}
		else{
			contract.getReportSchedule().setIsLBSAccount("F");
		}
		
		// checking LBS Account Flag end
		ScheduleReportResult result = customerReportService.saveReportSchedule(contract);
		response.setRenderParameter("scheduleSuccess", "success");
		response.setRenderParameter("action", "showScheduleReportPage");
		//PARTHA ADDED FOR PAGE REFRESH START
    	request.getPortletSession().setAttribute("navigationFromPage", "Schedule");		
    	//PARTHA ADDED FOR PAGE REFRESH END
	}
	
	@RequestMapping(params = "action=saveReportOracle")
	public void saveOracleSchedule(ActionRequest request, 
								   ActionResponse response,
									 @RequestParam("docDefinitionId") String definitionId,
									 @RequestParam("roleCategoryId") String categoryId,
								   @RequestParam("editReport") 
								   String editReport,
								   Model model, 
								   @ModelAttribute("reportOracleForm")
								   OracleReportForm oracleReportForm) throws Exception{
		logger.debug("-----------------------finally saveOracleSchedule--------------");
		//Added for 14.9 Potential CR 14722 START
				String roleCategoryIdArrayPlusArrayEdit= request.getParameter("roleCategoryIdArrayPlus");
				String roleCategoryIdArrayMinusArrayEdit= request.getParameter("roleCategoryIdArrayMinus");
		//Added for 14.9 Potential CR 14722 ENDS		
		PortletSession session = request.getPortletSession();
		for(ObieeReportParameter parameter : oracleReportForm.getParameters()){
			logger.debug("############# values are DisplayName "+parameter.getDisplayName()+
					" ParameterName "+parameter.getParameterName()+" Parameter value "+parameter.getParameterValue());
			logger.debug("!!!!!!!!!!!!!!!!!!!!!editReport value is "+editReport);
			HashMap<String, String> ldapUserData = (HashMap<String, String>)session.getAttribute(LexmarkConstants.LDAP_USER_DATA, session.APPLICATION_SCOPE);
			String contactId = ldapUserData.get(LexmarkConstants.CONTACTID);
			logger.debug("############# values are contactId "+contactId);
			ObieeReportParameterContract contract = ContractFactory.getOracleReportContract(parameter, contactId);
			customerReportService.saveObieeReportParameter(contract);
		}
		//insert the data in the table ORACLE_REPORT_PARAMETER
		
		response.setRenderParameter("docDefinitionId", definitionId);
		response.setRenderParameter("roleCategoryId", categoryId);
		//Added for 14.9 Potential CR 14722 START
		response.setRenderParameter("roleCategoryIdArrayPlus", roleCategoryIdArrayPlusArrayEdit);
		response.setRenderParameter("roleCategoryIdArrayMinus", roleCategoryIdArrayMinusArrayEdit);
		//Added for 14.9 Potential CR 14722 ENDS
		response.setRenderParameter("action", "showReports");
		logger.debug("-----------------------finally saveOracleSchedule exit--------------");
	}
	
	@RequestMapping(params = "action=editOracleReport")
	public void editOracleReport(ActionRequest request, 
								 ActionResponse response,
								 @RequestParam("docDefinitionId") String definitionId,
								 @RequestParam("roleCategoryId") String categoryId,
								 Model model) throws Exception{
		logger.debug("---------Entry to the editOracleReport page--------------");
		//Added for 14.9 Potential CR 14722 START
		String roleCategoryIdArrayPlusArrayEdit= request.getParameter("roleCategoryIdArrayPlus");
		String roleCategoryIdArrayMinusArrayEdit= request.getParameter("roleCategoryIdArrayMinus");
		//Added for 14.9 Potential CR 14722 ENDS
		response.setRenderParameter("docDefinitionId", definitionId);
		response.setRenderParameter("roleCategoryId", categoryId);
		//Added for 14.9 Potential CR 14722 START
		response.setRenderParameter("roleCategoryIdArrayPlusEdit", roleCategoryIdArrayPlusArrayEdit);
		response.setRenderParameter("roleCategoryIdArrayMinusEdit", roleCategoryIdArrayMinusArrayEdit);
		//Added for 14.9 Potential CR 14722 ENDS
	    response.setRenderParameter("action", "showReports");
	    response.setRenderParameter("editReport", "editReport");
	    logger.debug("---------Exit to the editOracleReport page--------------");
	}
	
	
	@RequestMapping(params = "action=showEmailPage")
	public String showEmailPage(Model model, 
								RenderRequest request, 
								RenderResponse response) throws Exception{
		String reportId = request.getParameter("reportId");
		String reportDefinitionName = request.getParameter("reportDefinitionName");
		Report report = customerReportService.retrieveReportById(reportId, null);
		PortletURL reportURL = response.createRenderURL();
		reportURL.setWindowState(LiferayWindowState.NORMAL);
		reportURL.setParameter("action", "showReportEmaiLinkelPage");
		reportURL.setParameter("reportId", reportId);
		
		
		
		if(report != null){
			model.addAttribute("reportName", reportDefinitionName);
			model.addAttribute("reportDate", DateLocalizationUtil.formatDateLocale(report.getLastUpdateTime(), request.getLocale()));
			model.addAttribute("reportId", report.getFileObjectId());
			String reportEmailLinkUrl = getReportEmailLinkURL(reportURL);
			model.addAttribute("reportURL", reportEmailLinkUrl);
			logger.debug("reportEmailLink: " + reportEmailLinkUrl);
		}
		return "customerReports/sendReport";
	}
	
	private String getReportEmailLinkURL(PortletURL reportURL) {
		String url = reportURL.toString();
		url = url.replaceAll("global-services/reports", "global-services/downloadConsole");
		url = url.replaceAll("CustomerReports", "DownloadConsole");
		return url;
	}
	
	@RequestMapping(params = "action=retrieveReportById")
	public void retrieveReportById(ActionRequest request, 
									 ActionResponse response,
									 Model model) throws Exception{
		String reportId = request.getParameter("reportId");
		Report report = customerReportService.retrieveReportById(reportId, LexmarkUserUtil.getUserRoleNameList(request));
		if(report == null){
			response.setRenderParameter("action", "noPermission");
		}
		else{
			String url = ReportPathUtil.getReportFullURL(report.getFilePath());
			response.sendRedirect(url);
		}
	}
	
	
	
	@RequestMapping(params = "action=noPermission")
	public String showNoPermissionPage(Model model, 
										 RenderRequest request,
										 RenderResponse response)throws Exception {
		return "customerReports/noPermission";
	}
	// Changed the parameter timeZoneOffset type from int to float
	private void adjustReportsDate(List<ReportListRow> reportListRows, float timeZoneOffset) {
		for (ReportListRow row : reportListRows) {
			Date lastUpdated = row.getLastUpdated();
			TimezoneUtil.adjustDate(lastUpdated, timeZoneOffset);
			row.setLastUpdated(lastUpdated);
		}
	}
	
	@RequestMapping(params = "action=deleteReport")	
	public void deleteReport(ActionRequest request, 
						 	 ActionResponse response,
							 @RequestParam("docDefinitionId") 
							 String definitionId,
							 @RequestParam("roleCategoryId") 
							 String roleCategoryId,
						 	 Model model) throws Exception{
		
		String[] reportIds = request.getParameter("reportId").split(",");
		customerReportService.deleteReports(reportIds);
		if(roleCategoryId == null || roleCategoryId.trim().length() == 0){
		    response.setRenderParameter("deleteResult", "success");
			response.setRenderParameter("action", "");
		}
		else{
		    response.setRenderParameter("roleCategoryId", roleCategoryId);
		    response.setRenderParameter("docDefinitionId", definitionId);
		    response.setRenderParameter("deleteResult", "success");
			response.setRenderParameter("action", "showReports");			
		}
	}
	public String substringAfter(String str, String separator) {
	      if (isEmpty(str)) {
	          return str;
	      }
	      if (separator == null) {
	          return "";
	      }
	      int pos = str.indexOf(separator);
	      if (pos == -1) {
	          return "";
	      }
	      return str.substring(pos + separator.length());
	  }
	public String substringBefore(String str, String separator) {
	      if (isEmpty(str) || isEmpty(separator)) {
	          return str;
	      }
	      int pos = str.indexOf(separator);
	      if (pos == -1) {
	          return str;
	      }
	      return str.substring(0, pos);
	  }

	 public boolean isEmpty(String str) {
	      return str == null || str.length() == 0;
	  }
	 
	 private boolean getCanDeleteDocFlag(ResourceRequest request) throws Exception{
		 boolean isPublishing = LexmarkUserUtil.isPublishing(request);
		 return isPublishing;
	 }

}
