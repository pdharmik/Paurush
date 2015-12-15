/**
* Copyright@ 2012. This document has been created & written by
 * Wipro technologies for Lexmark and this is copyright to Lexmark
 *
 * File         	: CustomerReportsAdminController
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

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.portlet.ResourceURL;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.contract.CategoryAdministrationDetailContract;
import com.lexmark.contract.CategoryAdministrationListContract;
import com.lexmark.contract.CategoryDeleteContract;
import com.lexmark.contract.DeleteReportAdministrationContract;
import com.lexmark.contract.GlobalLegalEntityContract;
import com.lexmark.contract.ReportDefinitionDetailContract;
import com.lexmark.contract.SaveCategoryAdministrationDetailContract;
import com.lexmark.contract.SaveReportDefinitionDetailContract;
import com.lexmark.contract.SwapCategoryOrderNumberContract;
import com.lexmark.domain.GlobalAccount;
import com.lexmark.domain.LexmarkTransaction;
import com.lexmark.domain.ReportCustomers;
import com.lexmark.domain.ReportDefinition;
import com.lexmark.domain.ReportParameters;
import com.lexmark.domain.Role;
import com.lexmark.domain.RoleCategory;
import com.lexmark.domain.SupportedLocale;
import com.lexmark.enums.ReportParameterTypeEnum;
import com.lexmark.framework.logging.LEXLogger;
import com.lexmark.result.CategoryAdministrationListResult;
import com.lexmark.result.CategoryDeleteResult;
import com.lexmark.result.DeleteReportAdministrationResult;
import com.lexmark.result.GlobalLegalEntityResult;
import com.lexmark.result.ReportAdministrationListResult;
import com.lexmark.result.SaveCategoryAdministrationDetailResult;
import com.lexmark.result.SaveReportDefinitionDetailResult;
import com.lexmark.result.SupportedLocaleListResult;
import com.lexmark.result.SwapCategoryOrderNumberResult;
import com.lexmark.service.api.CustomerReportService;
import com.lexmark.service.api.GlobalLegalEntityService;
import com.lexmark.service.api.GlobalService;
import com.lexmark.service.api.RoleService;
import com.lexmark.service.api.ServiceRequestLocale;
import com.lexmark.service.impl.real.jdbc.CustomerReportServiceImpl;
import com.lexmark.service.impl.real.jdbc.HibernateUtil;
import com.lexmark.service.impl.real.jdbc.InfrastructureException;
import com.lexmark.services.LexmarkSPConstants;
import com.lexmark.services.form.CategoryDisplayForm;
import com.lexmark.services.form.ReportDefinitionDetailForm;
import com.lexmark.services.util.AccountUtil;
import com.lexmark.services.util.ContractFactory;
import com.lexmark.services.util.ObjectDebugUtil;
import com.lexmark.services.util.PortalSessionUtil;
import com.lexmark.services.util.ServiceStatusErrorMessageUtil;
import com.lexmark.services.util.ServiceStatusUtil;
import com.lexmark.util.PerformanceTracker;
import com.lexmark.util.StringUtil;
import com.liferay.portal.kernel.servlet.PortletServlet;
import com.liferay.portal.kernel.util.JavaConstants;
import com.lexmark.framework.logging.LEXLogger;

@Controller
@RequestMapping("VIEW")
public class CustomerReportsAdminController extends BaseController {
	private static final String MESSAGE_PREFIX = "message.customerReportsAdmin.";

	private static final String ERROR_PREFIX = "exception.customerReportsAdmin.";
	
	private static final String ADD_OR_EDIT = "addOrEdit";
	/* Added for BRD 14-02-14 start */
	private static final String mdmId = "mdm_id";
	private static final String mdmLevel = "mdm_level";
	private static final String report_customer_table = "report_customers";
	private static final String reportDefinition_Id = "report_definition_id";
	private static final String companyDetail_table_Name = "COMPANY_DETAIL";
	private static final String companyLegalName = "COMPANY_LEGAL_NAME";
	private static final String companyDAId = "COMPANY_DA_ID";
	private static final String companyMDMId = "COMPANY_MDM_ID";
	private static final String companyDomesticName = "COMPANY_MDM_DOMESTIC_ULT_NAME";
	private static final String companyDomesticId = "COMPANY_MDM_DOMESTIC_ULT_NO";
	private static final String companygGlobalName = "COMPANY_MDM_GLOBAL_ULT_NAME";
	private static final String companyGlobalId = "COMPANY_MDM_GLOBAL_ULT_NO";
	/* Added for BRD 14-02-14 end */
	private static Logger logger = LogManager.getLogger(CustomerReportsAdminController.class);
	private static LEXLogger LOGGER = LEXLogger.getLEXLogger(CustomerReportsAdminController.class);
	private static final String CLASS_NAME="CustomerReportsAdminController";

	@Autowired
	private CustomerReportService customerReportService;
	
	@Autowired
	private ServiceRequestLocale serviceRequestLocaleService;
	
	@Autowired
	private RoleService roleService;

	@Autowired
	private GlobalService globalService;

	public GlobalService getGlobalService() {
		return globalService;
	}

	public void setGlobalService(GlobalService globalService) {
		this.globalService = globalService;
	}

	@Autowired
	private SharedPortletController sharedPortletController;

    @Autowired
    private GlobalLegalEntityService globalLegalEntityService;
	
	/**
	 * @param model 
	 * @param request 
	 * @param response 
	 * @return String  
	 * @throws Exception 
	 */
	@RequestMapping
	public String showDefaultView(Model model, RenderRequest request,
            RenderResponse response) throws Exception {
		return "customerReportsAdmin/defaultView";
	}
	
	/**
	 * @param model 
	 * @param request 
	 * @param response 
	 * @return String  
	 * @throws Exception 
	 */
	@RequestMapping(params = "action=showCategoryDisplay")
	public String showCategoryDisplay(Model model, RenderRequest request,
            RenderResponse response) throws Exception {
		String specificAction = request.getParameter("specificAction");
		boolean addOrEdit = false;
		if (ADD_OR_EDIT.equals(specificAction)){//added this block to find out the specific action
			logger.debug("Action is addOrEdit");
			addOrEdit = true;
		}
		model.addAttribute("addOrEdit", addOrEdit);
		SupportedLocaleListResult supportedLocaleListResult = serviceRequestLocaleService
				.retrieveSupportedLocaleList();
		List<SupportedLocale> supportedLocales = supportedLocaleListResult.getSupportedLocales();
		List<Role> roleList = roleService.retrieveRoleList();
		CategoryDisplayForm categoryDisplayForm = new CategoryDisplayForm();
		if (!StringUtil.isStringEmpty(request.getParameter("categoryId"))) {
			Integer categoryId = Integer.valueOf(request.getParameter("categoryId"));
			CategoryAdministrationDetailContract contract = ContractFactory.getCategoryAdministrationDetailContract(categoryId);
			RoleCategory selectedCategory = customerReportService.
					retrieveCategoryAdministrationDetail(contract).getRoleCategory();
			categoryDisplayForm.setCategory(selectedCategory);
		}
		ResourceURL url = response.createResourceURL();
		url.setResourceID("categoryListURL");
		categoryDisplayForm.setCategoryListURL(url.toString());
		categoryDisplayForm.assemble(supportedLocales, roleList);
		model.addAttribute("categoryDisplayForm", categoryDisplayForm);
		
		return "customerReportsAdmin/categoryDisplay";
	}
	
	/**
	 * @param model 
	 * @param request 
	 * @param response 
	 * @return String  
	 * @throws Exception 
	 */
	@RequestMapping(params = "action=showReportDefinition")  // This is modified as per of BRD #14-07-03
	public String showReportDefinition(Model model, RenderRequest request,
            RenderResponse response) throws Exception {
		LOGGER.enter(CLASS_NAME, "showReportDefinition");
		SupportedLocaleListResult supportedLocaleListResult = serviceRequestLocaleService
				.retrieveSupportedLocaleList();
		CategoryAdministrationListContract categoryListContract = ContractFactory.
				getCategoryAdministrationListContract(LexmarkConstants.CATEGORY_TYPE_REPORT);
		LOGGER.info("start printing lex logger");
		ObjectDebugUtil.printObjectContent(categoryListContract, LOGGER);
		LOGGER.info("end printing lex loggger");
		List<ReportParameters> reportParams= new ArrayList<ReportParameters>();//Added for CI BRD 13-10-27
		//Changes done for CI BRD 13-10-27--STARTS
		Boolean newReportFlag=false;
		if(request.getParameter("newReport")!=null)
		{
			newReportFlag=Boolean.valueOf(request.getParameter("newReport"));
			LOGGER.debug("-------newReportFlag coming from New Report Addition-->>"+newReportFlag);
		}
		String prevCompMdmIdLevel = request.getParameter("prevCompanyMdmIdLevel");
		String assoc="";
		
		String localeIndex="0";
		if(request.getParameter("localeIndex")!=null)
		{
		localeIndex=request.getParameter("localeIndex");
		}
		LOGGER.debug("localeIndex Inside showReportDefinition---->>"+localeIndex);
		//Changes done for CI BRD 13-10-27--ENDS
		List<RoleCategory> categoryList = customerReportService.retrieveCategoryAdministrationList(
				categoryListContract).getRoleCategoryList();
		List<SupportedLocale> supportedLocales = supportedLocaleListResult.getSupportedLocales();
		ReportDefinitionDetailForm reportDefinitionDetailForm = new ReportDefinitionDetailForm();
		List<ReportCustomers> reportCustomerList = new ArrayList<ReportCustomers>();	// added for multiple report customers
		List<String> customerNameList  = new ArrayList<String>();					    // added for multiple report customers
		List<String> customerNameListExclude  = new ArrayList<String>();
		boolean checkInclude = false;
		boolean checkExclude = false;
		if (!StringUtil.isStringEmpty(request.getParameter("reportDefinitionId"))) {
			Integer reportDefinitionId = Integer.valueOf(request.getParameter("reportDefinitionId"));
			LOGGER.debug("reportDefinitionId in showReportDefinition-->>"+reportDefinitionId);
			ReportDefinitionDetailContract contract = ContractFactory.getReportDefinitionDetailContract(reportDefinitionId);
			LOGGER.info("start printing lex logger");
			ObjectDebugUtil.printObjectContent(contract, LOGGER);
			LOGGER.info("end printing lex loggger");
			ReportDefinition reportDefinition = customerReportService.
					retrieveReportDefinitionDetail(contract).getReportDefinition();		// coming frm db.. 
			reportCustomerList = reportDefinition.getReportCustomersList();
			//DB call for CI Defect 10111 under BRD 27--STARTS
			reportParams=customerReportService.getReportParametersList_showDefinition(reportDefinition);
			//Commented for Local 
			List<GlobalAccount> siebelAccountList = globalLegalEntityService.getGlobalAccounts();		// code added for CI5 customer multiple reports for optimizing performance
			/*iterating through the customer list for a report... for CI5...
			..checking for a customer in siebel by mdmid and level*/
			for(ReportCustomers customer : reportCustomerList) {
				if (!StringUtil.isStringEmpty(customer.getMdmId()) && !StringUtil.isStringEmpty(customer.getMdmLevel()) && ((null==customer.getCheckRestrictExclude()) || customer.getCheckRestrictExclude().equals("T"))) {	// added for CI5 multiple customer report
					GlobalLegalEntityContract accountContract = ContractFactory.getGlobalLegalEntityContract(customer.getMdmId(), customer.getMdmLevel());						// added for CI5 multiple customer report
					LOGGER.info("start printing lex logger Restrict flag is T");
					ObjectDebugUtil.printObjectContent(accountContract, LOGGER);
					LOGGER.info("end printing lex loggger");
					GlobalLegalEntityResult accountResult = AccountUtil.getReportCustomer(accountContract, siebelAccountList);														// code added for CI5 customer multiple reports for optimizing performance
					if (accountResult.getAccount() != null && !StringUtil.isStringEmpty(accountResult.getAccount().getLegalName())) {
						String legalName = accountResult.getAccount().getLegalName();
						String displayMdmId = accountResult.getAccount().getDisplayMdmId();	
//						added  for CI5 multiple customer report
						String accountLegalName = legalName + " (" + displayMdmId + ")";
						customerNameList.add(accountLegalName);
						checkInclude = true;						
//						end of addition for CI5 multiple customer report
					}
				}
				else
				{
					if (!StringUtil.isStringEmpty(customer.getMdmId()) && !StringUtil.isStringEmpty(customer.getMdmLevel()) && customer.getCheckRestrictExclude().equals("F")) {														// added for CI5 multiple customer report
						GlobalLegalEntityContract accountContract = ContractFactory.getGlobalLegalEntityContract(customer.getMdmId(), customer.getMdmLevel());						// added for CI5 multiple customer report
						LOGGER.info("start printing lex logger Exclude Flag is F");
						ObjectDebugUtil.printObjectContent(accountContract, LOGGER);
						LOGGER.info("end printing lex loggger");
						GlobalLegalEntityResult accountResult = AccountUtil.getReportCustomer(accountContract, siebelAccountList);														// code added for CI5 customer multiple reports for optimizing performance
						if (accountResult.getAccount() != null && !StringUtil.isStringEmpty(accountResult.getAccount().getLegalName())) {
							String legalName = accountResult.getAccount().getLegalName();
							String displayMdmId = accountResult.getAccount().getDisplayMdmId();	
//							added  for CI5 multiple customer report
							String accountLegalName = legalName + " (" + displayMdmId + ")";
							customerNameListExclude.add(accountLegalName);
							checkExclude= true;
//							end of addition for CI5 multiple customer report
						}
					}
				}
			}
			logger.debug("customerNameList.size---->>>"+customerNameList.size()+"checkInclude-->>"+checkInclude);
			logger.debug("customerNameListExclude.size---->>>"+customerNameListExclude.size()+"checkExclude--->>>"+checkExclude);
			reportDefinitionDetailForm.setLegalNameList(customerNameList);  // added for CI5 multiple customer report
			reportDefinitionDetailForm.setLegalNameListExclude(customerNameListExclude);
			reportDefinitionDetailForm.setReportDefinition(reportDefinition);
		}
		reportDefinitionDetailForm.assemble(supportedLocales);
		reportDefinitionDetailForm.setCategoryList(categoryList);
		model.addAttribute("localeIndex", "0");//Added for CI BRD 13-10-27
		model.addAttribute("newReportFlag", newReportFlag);//Added for CI BRD 13-10-27
		model.addAttribute("reportParams", reportParams);
		model.addAttribute("reportDefinitionDetailForm", reportDefinitionDetailForm);
		model.addAttribute("checkInclude",checkInclude);
		model.addAttribute("checkExclude",checkExclude);
		
		// Added for BRD 14-02-14 start
		String showLimitCustomer = "false";
		String companyLevelAssociation="";
		
		if(reportDefinitionDetailForm.getReportDefinition().getReportType()!=null){
			
		if(reportDefinitionDetailForm.getReportDefinition().getReportType().equals("MU")){			
			
			StringBuffer sbMdmList = new StringBuffer();
			StringBuffer sb4 = new StringBuffer();
			StringBuffer sb3 = new StringBuffer();
			StringBuffer sb2 = new StringBuffer();
			StringBuffer sb1 = new StringBuffer();			
	        
	        int cnt1 = 0;
	        int cnt2 = 0;
	        int cnt3 = 0;
	        int cnt4 = 0;
	        int cnt5 = 0;
	        String str4 = "";
	        String str3 = "";
	        String str2 = "";
	        String str1 = "";
	        
	        sbMdmList.append(" SELECT ");
	        sbMdmList.append(mdmId + ", " + mdmLevel);
	        sbMdmList.append(" FROM ");
	        sbMdmList.append(report_customer_table);
	        sbMdmList.append(" WHERE ");
	        sbMdmList.append(reportDefinition_Id + "='" + request.getParameter("reportDefinitionId") + "'");	        
	        try {
				Query query = HibernateUtil.getSession().createSQLQuery(sbMdmList.toString());
				List list = query.list();	
				
				if(list != null && !list.isEmpty()) {
					showLimitCustomer = "true";
					for(int i = 0; i < list.size(); i++){
						Object[] mdmAcount = (Object[]) list.get(i);
						String mdmId = (String)mdmAcount[0];
						String mdmLevel = (String)mdmAcount[1];
						
						if(mdmId!=null &&  mdmLevel!=null){
						if("Account".equals(mdmLevel)){
							cnt4++;
							str4 = mdmId;
							assoc = str4+"/"+"Account"+",";
							
						}else if("Legal".equals(mdmLevel)){
							cnt3++;
							str3 = mdmId;
							assoc += str3+"/"+"Legal"+",";
							
						}else if ("Domestic".equals(mdmLevel)){
							cnt2++;
							str2 = mdmId;
							if(list.size()==2){
								assoc += ","+str2+"/"+"Domestic"+",";
							}else{
								assoc += str2+"/"+"Domestic"+",";
							}							
							
						}else if("Global".equals(mdmLevel)){
							cnt1++;
							str1 = mdmId;
							if(list.size()==1){
								assoc += ",,"+str1+"/"+"Global"+",";
							}else{
								assoc += str1+"/"+"Global"+",";
							}
							
							
						}
						}else{
							cnt5++;
							
						}
					}
					
					if(cnt4 > 0){
						
						sb4.append(" SELECT ");
				        sb4.append(companyLegalName);
				        sb4.append(" FROM ");
				        sb4.append(companyDetail_table_Name);
				        sb4.append(" WHERE ");
				        sb4.append(companyDAId + "='" + str4 + "'");	
				        try {
							Query query4 = HibernateUtil.getSession().createSQLQuery(sb4.toString());
							List list4 = query4.list();
							
							if(!list4.isEmpty()){
							String L4Name = (String)list4.get(0);
							companyLevelAssociation = L4Name+ " (" + str4 + ")";
							
							}else{
								companyLevelAssociation = str4;
							}
							
				        }catch (HibernateException ex) {
							throw new InfrastructureException(ex);
						}finally {
						    HibernateUtil.closeSession();
						}				        
					}
					else if(cnt3 > 0){
						sb3.append(" SELECT ");
						sb3.append(companyLegalName);
						sb3.append(" FROM ");
						sb3.append(companyDetail_table_Name);
						sb3.append(" WHERE ");
						sb3.append(companyMDMId + "='" + str3 + "'");	
				        try {
							Query query3 = HibernateUtil.getSession().createSQLQuery(sb3.toString());
							List list3 = query3.list();
							
							if(!list3.isEmpty()){
							String L3Name = (String)list3.get(0);
							companyLevelAssociation = L3Name+ " (" + str3 + ")";
							
							}else{
								companyLevelAssociation = str3;
							}
				        }catch (HibernateException ex) {
							throw new InfrastructureException(ex);
						}finally {
						    HibernateUtil.closeSession();
						}		
					}
					else if(cnt2 > 0){
						sb2.append(" SELECT ");
						sb2.append(companyDomesticName);
						sb2.append(" FROM ");
						sb2.append(companyDetail_table_Name);
						sb2.append(" WHERE ");
						sb2.append(companyDomesticId + "='" + str2 + "'");	
				        try {
							Query query2 = HibernateUtil.getSession().createSQLQuery(sb2.toString());
							List list2 = query2.list();
							
							if(!list2.isEmpty()){
							String L2Name = (String)list2.get(0);
							companyLevelAssociation = L2Name+ " (" + str2 + ")";
							
							}else{
								companyLevelAssociation = str2;
							}
							
				        }catch (HibernateException ex) {
							throw new InfrastructureException(ex);
						}finally {
						    HibernateUtil.closeSession();
						}		
					}
					else if (cnt1 > 0){
						sb1.append(" SELECT ");
						sb1.append(companygGlobalName);
						sb1.append(" FROM ");
						sb1.append(companyDetail_table_Name);
						sb1.append(" WHERE ");
						sb1.append(companyGlobalId + "='" + str1 + "'");	
				        try {
							Query query1 = HibernateUtil.getSession().createSQLQuery(sb1.toString());
							List list1 = query1.list();
							
							if(!list1.isEmpty()){
								String L1Name = (String)list1.get(0);
								companyLevelAssociation = L1Name+ " (" + str1 + ")";
								
							}else{
								companyLevelAssociation = str1;
							}
							
				        }catch (HibernateException ex) {
							throw new InfrastructureException(ex);
						}finally {
						    HibernateUtil.closeSession();
						}		
					}
					else if (cnt5 >0){
						companyLevelAssociation = "";
						showLimitCustomer = "false";
						
					}
				}
	        } catch (HibernateException ex) {
				throw new InfrastructureException(ex);
			}finally {
			    HibernateUtil.closeSession();
			}
			
		}
		else if(reportDefinitionDetailForm.getReportDefinition().getReportType().equals("BO") || reportDefinitionDetailForm.getReportDefinition().getReportType().equals("OA")){
			showLimitCustomer = "true";
		}
		}
		
		if(prevCompMdmIdLevel==null){
			
			prevCompMdmIdLevel = assoc;
		}else if("".equals(prevCompMdmIdLevel)){
			
			prevCompMdmIdLevel = assoc;
		}
		model.addAttribute("companyLevelAssociation",companyLevelAssociation);
		model.addAttribute("showLimitCustomer",showLimitCustomer);
		model.addAttribute("prevCompMdmIdLevel", prevCompMdmIdLevel);
		// Added for BRD 14-02-14 ends
		LOGGER.exit(CLASS_NAME, "showReportDefinition");
		return "customerReportsAdmin/reportDefinition";
	}
	
	/**
	 * @param model 
	 * @param request 
	 * @param response 
	 * @param categoryDisplayForm 
	 * @throws Exception 
	 */
	@RequestMapping(params = "action=saveCategory")
	public void saveCategory(Model model, ActionRequest request,
			ActionResponse response, @ModelAttribute("categoryDisplayForm")
			CategoryDisplayForm categoryDisplayForm) throws Exception{
		boolean success = false;
		boolean isNameDuplicated = false;
		assembleCategory(categoryDisplayForm, request);
		RoleCategory category = categoryDisplayForm.getCategory();
		SaveCategoryAdministrationDetailContract contract = ContractFactory.
				getSaveCategoryAdministrationDetailContract(category);
		try {
			SaveCategoryAdministrationDetailResult result = customerReportService.saveCategoryAdministrationDetail(contract);
			success = result.getResult();
		} catch (Exception ex) {
			logger.debug(ex.getMessage());
			if (ex.getMessage().equals(LexmarkConstants.DUPLICATED_CATEGORY_NAME)) {
				isNameDuplicated = true;
			}
			success = false;
		}
		String status;
		if (success) {
			status = MESSAGE_PREFIX + "saveCategory";
			response.setRenderParameter("categoryId", category.getCategoryId().toString());
		} else if (isNameDuplicated) {
			status = ERROR_PREFIX + "saveCategoryDuplicatedName";
		} else {
			status = ERROR_PREFIX + "saveCategory";
		}
		ServiceStatusUtil.checkServiceStatus(model, status, request.getLocale(), true);
		response.setRenderParameter("action", "showCategoryDisplay");
	}

	/**
	 * @param model 
	 * @param request 
	 * @param response 
	 * @param reportDefinitionDetailForm 
	 * @throws Exception  
	 */
	@RequestMapping(params = "action=saveReportDefinition")  //This is modified as per of BRD #14-07-03
	public void saveReportDefinition(Model model, ActionRequest request,
			ActionResponse response, @ModelAttribute("reportDefinitionDetailForm")
			ReportDefinitionDetailForm reportDefinitionDetailForm) throws Exception{
		LOGGER.enter(this.getClass().getSimpleName(), "saveReportDefinition");
		boolean success = false;
		boolean isNameDuplicated = false;
		String submitType = request.getParameter("submitType");
		String prevCompanyMdmIdLevel = request.getParameter("prevCompanyMdmIdLevel");
		String isRestrict  =  request.getParameter("isRestrict");
		logger.debug("The restrict account flag is----------->>>"+isRestrict);
		logger.debug("prevCompanyMdmIdLevel is ::"+prevCompanyMdmIdLevel);
		ReportDefinition reportDefinition = reportDefinitionDetailForm.getReportDefinition();
		String reportDefinitionName =reportDefinitionDetailForm.getReportDefinition().getName();
		//Added for CI BRD 13-10-27--STARTS
		String localeIndex = request.getParameter("localeIndex");
		logger.debug("locale Index-------->>"+localeIndex);
		//Added for CI BRD 13-10-27--ENDS
		//Condition Added for CI BRD 13-10-27
		if(!reportDefinitionDetailForm.getReportDefinition().getReportType().equals("MU"))
		{
			mergeReportParameters(reportDefinition, request);
		}
		
		logger.debug("** Report type :: "+reportDefinitionDetailForm.getReportDefinition().getReportType());
//		added by for for CI5 multiple customers report
		Integer reportDefinitionId = reportDefinition.getId();
		logger.info("########report param id: " + reportDefinitionId);
		/* Changed for (BRD 14-02-14) */
		List<String> custList = new ArrayList<String>();
		List<ReportCustomers> reportCustomersList = new ArrayList<ReportCustomers>();
		logger.debug(reportDefinitionDetailForm.getReportDefinition().getReportType());
		if("MU".equals(reportDefinitionDetailForm.getReportDefinition().getReportType()) && (!isRestrict.equals("F"))){
			logger.debug("Inside Manual Upload (saveReportDefinition)------------>>>>");
			custList = getListOfReportCustomersForManualUpload(request);
			if(custList==null || custList.isEmpty()){
				ReportCustomers nullCustomers = new ReportCustomers();
				nullCustomers.setId(null);
				nullCustomers.setMdmId("");
				nullCustomers.setMdmLevel("");
				nullCustomers.setReportDefinitionId(reportDefinitionId);
				reportCustomersList.add(nullCustomers);
			}
			else{
			for(String c : custList) {
				logger.info("##### cust: " + c);
				if(c!=""){
				String[] mdmidLevel = c.split("/");
				logger.debug(mdmidLevel[0]+" , "+mdmidLevel[1]);
				ReportCustomers reportCustomers = new ReportCustomers();
				reportCustomers.setId(null);
				reportCustomers.setMdmId(mdmidLevel[0]);
				reportCustomers.setMdmLevel(mdmidLevel[1]);
				reportCustomers.setReportDefinitionId(reportDefinitionId);
				reportCustomers.setCheckRestrictExclude(isRestrict);
				reportCustomersList.add(reportCustomers);
				}
			}
		
			}
		}else{
			logger.debug("Inserting the Data");
			custList = getListOfReportCustomers(request);
			String customerCheck = request.getParameter("legalName");
			logger.debug("legalName ---"+customerCheck);
			if("customerList".equalsIgnoreCase(customerCheck)|| "customerListExclude".equalsIgnoreCase(customerCheck)) {
				// fetch customer details from siebel and save
				List<GlobalAccount> accountList = getSelectedAccountListFromSiebel(custList);
				for(GlobalAccount acc : accountList) {
					ReportCustomers reportCustomers = new ReportCustomers();
					reportCustomers.setId(null);
					reportCustomers.setMdmId(acc.getMdmId());
					reportCustomers.setMdmLevel(acc.getMdmLevel());
					reportCustomers.setReportDefinitionId(reportDefinitionId);
					reportCustomers.setCheckRestrictExclude(isRestrict);
					reportCustomersList.add(reportCustomers);
				}
			}
			else {
				// save null values in new table
				ReportCustomers nullCustomers = new ReportCustomers();
				nullCustomers.setId(null);
				nullCustomers.setMdmId("");
				nullCustomers.setMdmLevel("");
				nullCustomers.setReportDefinitionId(reportDefinitionId);
				reportCustomersList.add(nullCustomers);
			}
		}
		/* End */	
		logger.info("########size of customer list: " + reportCustomersList.size());

		for(ReportCustomers c : reportCustomersList) {
			logger.info("######mdm id/level 2b saved to db: " + c.getMdmId() + "/" + c.getMdmLevel());
		}

		reportDefinition.setReportCustomersList(reportCustomersList);
//		end of addition for CI5 multiple customers report
		
		try {
			SaveReportDefinitionDetailContract contract = ContractFactory.
					getSaveReportDefinitionDetailContract(reportDefinition);
			if(localeIndex!=null)
			{
				contract.setLocaleId(localeIndex);
			}
			LOGGER.info("start printing lex logger");
			ObjectDebugUtil.printObjectContent(contract, LOGGER);
			LOGGER.info("end printing lex loggger");
			SaveReportDefinitionDetailResult result = customerReportService.saveReportDefinition(contract);
			success = result.getResult();
			LOGGER.debug("SUCCESS message after saveReportDefinition------------>>"+success);
		} catch (Exception ex) {
			LOGGER.debug("EXCEPTION IN SAVING"+success);
			logger.debug(ex.getMessage());
			if (ex.getMessage().equals(LexmarkConstants.DUPLICATED_REPORT_NAME)) {
				isNameDuplicated = true;
			}
			success = false;
		}

		String status;
		//Changes done for CI BRD 13-10-27--STARTS
		if (success && "yes".equals(submitType)) {
			ReportAdministrationListResult result = customerReportService.retrieveReportAdministrationList();
			List<ReportDefinition> reportDefinitionList =result.getReportDefinitionList();
			Integer reportDefId=null;
			for (ReportDefinition reportDefinitionFetched : reportDefinitionList) {
				
				if(reportDefinitionFetched.getName().equals(reportDefinitionName.trim())){
					reportDefId=reportDefinitionFetched.getId();
					
					break;
				}
			}
			String list_of_cust_prev = request.getParameter("companyMdmIdLevel");
			logger.debug("list_of_cust_prev :"+list_of_cust_prev);
			if(list_of_cust_prev.length()>0){
				prevCompanyMdmIdLevel = list_of_cust_prev;
			}
			
			status = MESSAGE_PREFIX + "saveReportDefinition";
			logger.debug("String.valueOf(reportDefId)"+String.valueOf(reportDefId));
			logger.debug("prevCompanyMdmIdLevel: "+prevCompanyMdmIdLevel);
			response.setRenderParameter("localeIndex",localeIndex);//Done for CI BRD 13-10-27 KRISHNENDU
			response.setRenderParameter("reportDefinitionId",String.valueOf(reportDefId));
			response.setRenderParameter("prevCompanyMdmIdLevel",prevCompanyMdmIdLevel);
			response.setRenderParameter("action", "showReportDefinition");	
		} 
		else if (success && "no".equals(submitType)) {
			status = MESSAGE_PREFIX + "saveReportDefinition";
			response.setRenderParameter("action", "");
		}
		
		else if (isNameDuplicated) {
			status = ERROR_PREFIX + "saveReportDefinitionDuplicatedName";
		} else {
			status = ERROR_PREFIX + "saveReportDefinition";
		}
		
		ServiceStatusUtil.checkServiceStatus(model, status, request.getLocale(), true);
		LOGGER.exit(this.getClass().getSimpleName(), "saveReportDefinition");
	}
	
	/**
	 * @param model 
	 * @param request 
	 * @param response 
	 * @throws Exception 
	 */
	@RequestMapping(params = "action=viewSchedulerMaintenance")
	public void viewSchedulerMaintenance(Model model, ActionRequest request,
			ActionResponse response) throws Exception{
		putParametersIntoSession(request, response);
		response.sendRedirect(request.getParameter("schedulerMaintenanceURL"));
	}

	/**
	 * @param request 
	 * @param response 
	 * @throws Exception 
	 */
	@ResourceMapping
	public void retrieveReportDefinitionList(ResourceRequest request,
			ResourceResponse response) throws Exception{
		logger.debug("------------- Step 1---retrieveReportDefinitionList started---------["+System.nanoTime()+"]");

		ReportAdministrationListResult result = customerReportService.retrieveReportAdministrationList();
		
		String content = getXmlOutputGenerator(request.getLocale()).convertCustomerReportListToXML(
				result.getReportDefinitionList(), request.getContextPath());
		PrintWriter out = response.getWriter();
		response.setContentType("text/xml");
		out.print(content);
		out.flush();
		out.close();
		logger.debug("------------- Step 1---retrieveReportDefinitionList end---------["+System.nanoTime()+"]");
	}
	
	/**
	 * @param request 
	 * @param response 
	 * @throws Exception 
	 */
	@ResourceMapping("categoryListURL")
	public void retrieveCategoryList(ResourceRequest request,
			ResourceResponse response) throws Exception{
		CategoryAdministrationListContract contract = ContractFactory.
				getCategoryAdministrationListContract(LexmarkConstants.CATEGORY_TYPE_REPORT);
		CategoryAdministrationListResult result = customerReportService.retrieveCategoryAdministrationList(contract);
		String xmlContent = getXmlOutputGenerator(request.getLocale()).
				convertCategoryListToXML(result.getRoleCategoryList(), request.getContextPath());
		PrintWriter out = response.getWriter();
		response.setContentType("text/xml");
		out.print(xmlContent);
		out.flush();
		out.close();
	}
	
	/**
	 * @param reportDefinitionId 
	 * @param request 
	 * @param response 
	 * @throws Exception 
	 */
	@ResourceMapping("deleteReportDefinition")
	public void deleteReportDefinition(@RequestParam("reportDefinitionId") Integer reportDefinitionId,
			ResourceRequest request, ResourceResponse response) throws Exception {
		boolean success = false;
		try {
			DeleteReportAdministrationContract contract = ContractFactory.
			        getDeleteReportAdministrationContract(reportDefinitionId);
			DeleteReportAdministrationResult result = customerReportService.deleteReportAdministration(contract); 
			success = result.getResult();
		} catch (Exception e) {
			success = false;
			logger.debug(e.getMessage());
		}
		String errorCode = success?"message.customerReportsAdmin.deleteReportDefinition"
				:"exception.customerReportsAdmin.deleteReportDefinition";
		ServiceStatusUtil.responseResult(response, errorCode, request.getLocale());
	}
	
	/**
	 * @param categoryId 
	 * @param request 
	 * @param response 
	 * @throws Exception 
	 */
	@ResourceMapping("deleteCategory")
	public void deleteCategory(@RequestParam("categoryId") int categoryId,
			ResourceRequest request, ResourceResponse response) throws Exception {
		try {
			CategoryDeleteContract contract = ContractFactory.getCategoryDeleteContract(categoryId);
			CategoryDeleteResult result = customerReportService.deleteCategory(contract);
			if (result.isSucceed()) {
				ServiceStatusUtil.responseResult(response,
						"message.customerReportsAdmin.deleteCategory", request.getLocale());
			} else {
				ServiceStatusUtil.responseResult(response,
						"exception.customerReportsAdmin.deleteCategory", request.getLocale());
			}
		} catch (Exception e) {
			if (e.getMessage().equals(LexmarkConstants.REPORT_DEFINITION_ASSOCIATED)) {
				ServiceStatusUtil.responseResult(response,
					"exception.customerReportsAdmin.deleteCategoryDefinitionAssociated", request.getLocale());
			} else {
			ServiceStatusUtil.responseResult(response,
					"exception.customerReportsAdmin.deleteCategory", request.getLocale());
			}
		}
	}
	
	/**
	 * @param orderNumber 
	 * @param increment 
	 * @param request 
	 * @param response 
	 * @throws Exception 
	 */
	@ResourceMapping("swapCategory")
	public void swapCategoryOrderNumber(@RequestParam("orderNumber") int orderNumber, 
			@RequestParam("increment") int increment, ResourceRequest request, 
			ResourceResponse response) throws Exception {
		SwapCategoryOrderNumberContract contract = ContractFactory.
				getSwapCategoryOrderNumberContract(orderNumber, increment, LexmarkConstants.CATEGORY_TYPE_REPORT);
		try {
			SwapCategoryOrderNumberResult result = customerReportService.swapCategoryOrderNumber(contract);
			if (result.isSucceed()) {
				ServiceStatusUtil.responseResult(response,
						"message.customerReportsAdmin.updateCategoryOrder", request.getLocale());
			} else {
				ServiceStatusUtil.responseResult(response,
						"exception.customerReportsAdmin.updateCategoryOrder", request.getLocale());
			}
		} catch (Exception e) {
			ServiceStatusUtil.responseResult(response,
					"exception.customerReportsAdmin.updateCategoryOrder", request.getLocale());
			logger.debug(e.getMessage());
		}
	}
	
	/**
	 * @param defaultValue 
	 * @param request 
	 * @param response 
	 * @throws Exception 
	 */
	@ResourceMapping("accountSelection")
	public void retrieveGlobalLegalEntityList(@RequestParam(value = "default", required = false) String defaultValue,
			ResourceRequest request, ResourceResponse response) throws Exception {
		sharedPortletController.retrieveGlobalLegalEntityList(defaultValue, request, response);
	}
/* Added for BRD 14-02-14	*/
	/**
	 * @param renderrequest 
	 * @param renderresponse 
	 * @param model 
	 * @return String  
	 * @throws Exception 
	 */
	@ResourceMapping(value="getCustomerTreeDetails")
	public String getCustomerTreeDetails(ResourceRequest renderrequest,
			ResourceResponse renderresponse,Model model) throws Exception {
		HttpServletRequest request = (HttpServletRequest) renderrequest.getAttribute(PortletServlet.PORTLET_SERVLET_REQUEST);
		String compName = renderrequest.getParameter("compName");
        String formName = renderrequest.getParameter("formName");
        logger.debug("compName:formName :: "+compName+" "+formName); 
        request.setAttribute("compName",compName);
        request.setAttribute("formName",formName);
		return "common/companyTreeView";
	}
	/**
	 * @param renderrequest 
	 * @param renderresponse 
	 * @param model 
	 * @return String  
	 * @throws Exception 
	 */
	@ResourceMapping(value="getCustomerMdmAccountDetails")
	public String getCustomerMdmAccountDetails(ResourceRequest renderrequest,
			ResourceResponse renderresponse,Model model) throws Exception {
		HttpServletRequest request = (HttpServletRequest) renderrequest.getAttribute(PortletServlet.PORTLET_SERVLET_REQUEST);
		
		String searchName = renderrequest.getParameter("search");
		String expression="";
        logger.debug("search :: "+searchName); 
        ArrayList companyMdmAccountList = new ArrayList();
        CustomerReportServiceImpl ctdao = new CustomerReportServiceImpl();        
        companyMdmAccountList = ctdao.getMdmAccountForReportAdmin(searchName);
        if(companyMdmAccountList==null || companyMdmAccountList.isEmpty() || companyMdmAccountList.size()==0){
        	expression = "blank";
        	logger.debug("expression :: "+expression);
        }
       
        else{
        
        	request.setAttribute("list", companyMdmAccountList);
        }
        request.setAttribute("expression",expression);	
        return "common/companyMdmAccountAssociation";
	}
	
	/**
	 * @param renderrequest 
	 * @param renderresponse 
	 * @param model 
	 * @return String  
	 * @throws Exception 
	 */
	@ResourceMapping(value="getCustomerL4AccountDetailsFromL3")
	public String getCustomerL4AccountDetailsFromL3(ResourceRequest renderrequest,
			ResourceResponse renderresponse,Model model) throws Exception {
		
		HttpServletRequest request = (HttpServletRequest) renderrequest.getAttribute(PortletServlet.PORTLET_SERVLET_REQUEST);
		
		String searchId = renderrequest.getParameter("search");
        logger.debug("searchId :: "+searchId); 
        String expression="";
        ArrayList companyMdmAccountList = new ArrayList();
        CustomerReportServiceImpl ctdao = new CustomerReportServiceImpl();
        companyMdmAccountList = ctdao.getL4AccountForL3ReportsAdmin(searchId);
        
        if(companyMdmAccountList==null || companyMdmAccountList.isEmpty() || companyMdmAccountList.size()==0){
        	expression = "blank";
        	logger.debug("expression :: "+expression);
        }else{
        
        	request.setAttribute("list", companyMdmAccountList);
        }		
        request.setAttribute("expression",expression);
        return "common/companyMdmAccountAssociation";
	}
	
	
	/**
	 * @param reportDefinition 
	 * @param request 
	 * @throws Exception 
	 */
	private void mergeReportParameters(ReportDefinition reportDefinition,
			ActionRequest request) throws Exception {
		logger.debug("--------ENTERING mergeReportParameters of CustomerReportsAdminController-------");
		List<ReportParameters> parameterList = new ArrayList<ReportParameters>();
		List reportParametersList=null;
		Boolean newReportFlag = false;
		if(request.getParameter("newReportFlag")!=null)
		{
			newReportFlag=Boolean.valueOf(request.getParameter("newReportFlag"));
		}
		if(request.getParameter("newReportFlag")!=null)
		{
			newReportFlag=Boolean.valueOf(request.getParameter("newReportFlag"));
			if(newReportFlag.equals(false))
			{
				reportParametersList=customerReportService.getReportParametersList(reportDefinition);
			}
			
		}
		
		String paramPrefix = null;
		Integer reportDefinitionId = reportDefinition.getId();
		boolean isRequired = false;
		Object[] row=null;
		if(reportParametersList!=null && !reportParametersList.isEmpty())
		{
			 row=(Object[]) reportParametersList.get(0);
		}
		
		for (int i = 0; request.getParameter("parameterList[" + i + "].name") != null; i ++) {
			
			if(reportParametersList!=null)
			{
				for(int j=0;j<reportParametersList.size();j++)
				{
					if(row!=null)
					{
						row = (Object[]) reportParametersList.get(j);
					}
					
				}
			}
			
			
			if (request.getParameter("parameterList[" + i + "].name").length() == 0) {
				continue;
			}
			paramPrefix = "parameterList[" + i + "].";
			ReportParameters parameter = new ReportParameters();
			parameter.setReportDefinitionId(reportDefinitionId);
			if (!StringUtil.isStringEmpty(request.getParameter(paramPrefix + "reportParameterId"))) {
				parameter.setReportParameterId(Integer.valueOf(
						request.getParameter(paramPrefix + "reportParameterId")));
			}
			parameter.setName(request.getParameter(paramPrefix + "name"));
			//If values coming from form
			if(request.getParameter(paramPrefix + "displayName_hidden")!=null)
			{
			parameter.setDisplayName(request.getParameter(paramPrefix + "displayName_hidden"));
			}
			if(request.getParameter(paramPrefix + "displayNameSpanish_hidden")!=null)
			{
				parameter.setDisplayNameSpanish(request.getParameter(paramPrefix + "displayNameSpanish_hidden"));
			}
			if(request.getParameter(paramPrefix + "displayNameGerman_hidden")!=null)
			{
				parameter.setDisplayNameGerman(request.getParameter(paramPrefix + "displayNameGerman_hidden"));
			}
			if(request.getParameter(paramPrefix + "displayNameFrench_hidden")!=null)
			{
				parameter.setDisplayNameFrench(request.getParameter(paramPrefix + "displayNameFrench_hidden"));
			}
			if(request.getParameter(paramPrefix + "displayNameChina_hidden")!=null)
			{
				parameter.setDisplayNameChina(request.getParameter(paramPrefix + "displayNameChina_hidden"));
			}
			if(request.getParameter(paramPrefix + "displayNameChinaTw_hidden")!=null)
			{
				parameter.setDisplayNameChinaTw(request.getParameter(paramPrefix + "displayNameChinaTw_hidden"));
			}
			if(request.getParameter(paramPrefix + "displayNamePortugalBrazil_hidden")!=null)
			{
				parameter.setDisplayNamePortugalBrazil(request.getParameter(paramPrefix + "displayNamePortugalBrazil_hidden"));
			}
			if(request.getParameter(paramPrefix + "displayNamePortugal_hidden")!=null)
			{
				parameter.setDisplayNamePortugal(request.getParameter(paramPrefix + "displayNamePortugal_hidden"));
			}
			if(request.getParameter(paramPrefix + "displayNameItaly_hidden")!=null)
			{
				parameter.setDisplayNameItaly(request.getParameter(paramPrefix + "displayNameItaly_hidden"));
			}
			if(request.getParameter(paramPrefix + "displayNameKorea_hidden")!=null)
			{
				parameter.setDisplayNameKorea(request.getParameter(paramPrefix + "displayNameKorea_hidden"));
			}
			if(request.getParameter(paramPrefix + "displayNameRussia_hidden")!=null)
			{
				parameter.setDisplayNameRussia(request.getParameter(paramPrefix + "displayNameRussia_hidden"));
			}
			if(request.getParameter(paramPrefix + "displayNameJapan_hidden")!=null)
			{
				parameter.setDisplayNameJapan(request.getParameter(paramPrefix + "displayNameJapan_hidden"));
			}
			if(request.getParameter(paramPrefix + "displayNameTurkey_hidden")!=null)
			{
				parameter.setDisplayNameTurkey(request.getParameter(paramPrefix + "displayNameTurkey_hidden"));
			}
			
			//If values not being sent from form pick from database
			if(row!=null)
			{
				if(request.getParameter(paramPrefix + "displayName_hidden")==null)
				{
					if((String)row[2]!=null)
					{
						parameter.setDisplayName((String)row[2]);
					}
				
				}
				if(request.getParameter(paramPrefix + "displayNameSpanish_hidden")==null)
				{
					if((String)row[3]!=null)
					{
						parameter.setDisplayNameSpanish((String)row[3]);
					}
					
				}
				if(request.getParameter(paramPrefix + "displayNameGerman_hidden")==null)
				{
					if((String)row[4]!=null)
					{
						parameter.setDisplayNameGerman((String)row[4]);
					}
					
				}
				if(request.getParameter(paramPrefix + "displayNameFrench_hidden")==null)
				{
					if((String)row[5]!=null)
					{
						parameter.setDisplayNameFrench((String)row[5]);
					}
					
				}
				if(request.getParameter(paramPrefix + "displayNameChina_hidden")==null)
				{
					if((String)row[6]!=null)
					{
						parameter.setDisplayNameChina((String)row[6]);
					}
					
				}
				if(request.getParameter(paramPrefix + "displayNameChinaTw_hidden")==null)
				{
					if((String)row[7]!=null)
					{
						parameter.setDisplayNameChinaTw((String)row[7]);
					}
					
				}
				if(request.getParameter(paramPrefix + "displayNamePortugalBrazil_hidden")==null)
				{
					if((String)row[8]!=null)
					{
						parameter.setDisplayNamePortugalBrazil((String)row[8]);
					}
					
				}
				if(request.getParameter(paramPrefix + "displayNamePortugal_hidden")==null)
				{
					if((String)row[9]!=null)
					{
						parameter.setDisplayNamePortugal((String)row[9]);
					}
					
				}
				if(request.getParameter(paramPrefix + "displayNameItaly_hidden")==null)
				{
					if((String)row[10]!=null)
					{
						parameter.setDisplayNameItaly((String)row[10]);
					}
					
				}
				if(request.getParameter(paramPrefix + "displayNameKorea_hidden")==null)
				{
					if((String)row[11]!=null)
					{
						parameter.setDisplayNameKorea((String)row[11]);
					}
					
				}
				if(request.getParameter(paramPrefix + "displayNameRussia_hidden")==null)
				{
					if((String)row[12]!=null)
					{
						parameter.setDisplayNameRussia((String)row[12]);
					}
					
				}
				if(request.getParameter(paramPrefix + "displayNameJapan_hidden")==null)
				{
					if((String)row[13]!=null)
					{
						parameter.setDisplayNameJapan((String)row[13]);
					}
					
				}
				if(request.getParameter(paramPrefix + "displayNameTurkey_hidden")==null)
				{
					if((String)row[14]!=null)
					{
						parameter.setDisplayNameTurkey((String)row[14]);
					}
					
				}
			}
			
			//Added for CI BRD 13-10-27 --ENDS
			parameter.setOrderNumber(i + 1);
			String dataType = request.getParameter(paramPrefix + "dataType");
			if (StringUtil.isStringEmpty(dataType)) {
				throw new Exception(ServiceStatusErrorMessageUtil.getErrorMessage(LexmarkSPConstants.ERROR_MESSAGE_BUNDLE, "exception.customerReport.dataTypeException", null, request.getLocale()));
			}
			parameter.setDataType(dataType);
			if (dataType.equals(ReportParameterTypeEnum.STRING.getType()) &&
					!StringUtil.isStringEmpty(request.getParameter(paramPrefix + "maxSize"))) {
				parameter.setMaxSize(Integer.valueOf(request.getParameter(paramPrefix + "maxSize")));
			} else if (dataType.equals(ReportParameterTypeEnum.LIST.getType())) {
				parameter.setListValues(request.getParameter(paramPrefix + "listValues"));
			}
			if (LexmarkConstants.CHECKBOX_CHECKED_VALUE.equals(request.getParameter(paramPrefix + "isRequired"))) {
				isRequired = true;
			} else {
				isRequired = false;
			}
			parameter.setIsRequired(isRequired);
			parameterList.add(parameter);
		}
		reportDefinition.setParameterList(parameterList);
	}
	
	/**
	 * @param categoryDisplayForm 
	 * @param request 
	 */
	private void assembleCategory(CategoryDisplayForm categoryDisplayForm, ActionRequest request) {
		RoleCategory category = categoryDisplayForm.getCategory();
		List<Role> roleList = new ArrayList<Role>();
		for (Role role : categoryDisplayForm.getCustomerExternalRoleList()) {
			if (LexmarkConstants.CHECKBOX_CHECKED_VALUE.equals(request.getParameter("customerExternalRoleList_" + role.getId()))) {
				roleList.add(role);
			}
		}
		for (Role role : categoryDisplayForm.getCustomerInternalRoleList()) {
			if (LexmarkConstants.CHECKBOX_CHECKED_VALUE.equals(request.getParameter("customerInternalRoleList_" + role.getId()))) {
				roleList.add(role);
			}
		}
		for (Role role : categoryDisplayForm.getPartnerExternalRoleList()) {
			if (LexmarkConstants.CHECKBOX_CHECKED_VALUE.equals(request.getParameter("partnerExternalRoleList_" + role.getId()))) {
				roleList.add(role);
			}
		}
		for (Role role : categoryDisplayForm.getPartnerInternalRoleList()) {
			if (LexmarkConstants.CHECKBOX_CHECKED_VALUE.equals(request.getParameter("partnerInternalRoleList_" + role.getId()))) {
				roleList.add(role);
			}
		}
		category.setRoles(roleList);
	}
	
	/**
	 * @param request 
	 * @param response 
	 */
	private void putParametersIntoSession(ActionRequest request, ActionResponse response) {
        PortletRequest portletRequest = (PortletRequest)request.getAttribute(JavaConstants.JAVAX_PORTLET_REQUEST);
        PortletSession portletSession = portletRequest.getPortletSession();
        portletSession.setAttribute("definitionName", request.getParameter("definitionName"), PortletSession.APPLICATION_SCOPE);
	}
	
//	added for CI5 multiple customer report
	/**
	 * @author wipro  
	 * @category CI5  
	 * @param request  
	 * @return List<String>   
	 * 
	 * for multiple customers report req 2116
	 */
	/* Added for BRD 14-02-14	*/
	private List<String> getListOfReportCustomersForManualUpload(ActionRequest request) {
		List<String> legalNamesList = new ArrayList<String>();

		String list_of_cust = request.getParameter("companyMdmIdLevel");
		
		if(list_of_cust.length()>0){
		String splitted[] = list_of_cust.split(",");
        StringBuffer sb = new StringBuffer();
        String retrieveData = "";
        for(int i =0; i<splitted.length; i++){
            retrieveData = splitted[i];
            if((retrieveData.trim()).length()>0){

                if(i!=0){
                    sb.append(",");
                }
                sb.append(retrieveData);

            }
        }
        
        sb.append(",");
        list_of_cust = sb.toString();
        String new_list = "," + list_of_cust;
        list_of_cust = list_of_cust.startsWith(",") ? list_of_cust : new_list;
        int firstIndex = list_of_cust.indexOf(",");
		int lastIndex = list_of_cust.lastIndexOf(",");
		list_of_cust = list_of_cust.substring(firstIndex+1, lastIndex);
		Set<String> hs = new HashSet<String>(Arrays.asList(list_of_cust.split(",")));
		legalNamesList.clear();
		legalNamesList.addAll(hs);
		}
		return legalNamesList;
	}
	/* End */
	/**
	 * @param request 
	 * @return  returns list of string
	 */
	private List<String> getListOfReportCustomers(ActionRequest request) {
		List<String> legalNamesList = new ArrayList<String>();
		logger.debug("isRestrict"+request.getParameter("isRestrict"));
		if("F".equals(request.getParameter("isRestrict")))
		{
			logger.debug("Inside the IF loop--->>>");
			for (int i = 0; request.getParameter("customerListExclude[" + i + "]") != null; i ++) 
			{
				String custName = request.getParameter("customerListExclude[" + i + "]");
				legalNamesList.add(custName.trim());
			}
		}
		else
		{
			logger.debug("Inside the ELSE loop--->>>");	
		for (int i = 0; request.getParameter("customerList[" + i + "]") != null; i ++) {
			String custName = request.getParameter("customerList[" + i + "]");
			legalNamesList.add(custName.trim());
		}
		}
		logger.info("######getting from jsp page: " + legalNamesList.size());
		return legalNamesList;
	}
	
	/**
	 * @author wipro 
	 * @category CI5 
	 * @param legalNameList 
	 * @return list of selected accounts from siebel
	 * 
	 * for multiple customers report req 2116
	 */
	private List<GlobalAccount> getSelectedAccountListFromSiebel(List<String> legalNameList) {
		List<GlobalAccount> accountList = AccountUtil.getReportCustomersListByLegalName(globalLegalEntityService, legalNameList);
//		
		for(GlobalAccount ga : accountList) {
			logger.info("#######account in siebel is: " + ga.getLegalName());
		}

		return accountList;
	}
//	end of addition for CI5 multiple customer report
}
