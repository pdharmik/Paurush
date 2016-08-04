package com.lexmark.services.portlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import javax.annotation.Resource;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.portlet.ResourceURL;

import com.lexmark.service.api.*;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.contract.CategoryAdministrationListContract;
import com.lexmark.contract.CategoryDeleteContract;
import com.lexmark.contract.CategoryDetailContract;
import com.lexmark.contract.CategorySaveContract;
import com.lexmark.contract.DocumentAdministrationListContract;
import com.lexmark.contract.DocumentDefinitionDeleteContract;
import com.lexmark.contract.DocumentDefinitionDetailsContract;
import com.lexmark.contract.DocumentDefinitionSaveContract;
import com.lexmark.contract.GeographyCountryContract;
import com.lexmark.contract.GlobalLegalEntityContract;
import com.lexmark.contract.SwapCategoryOrderNumberContract;
import com.lexmark.domain.Category_PartnerType;
import com.lexmark.domain.DefinitionLocale;
import com.lexmark.domain.DocumentDefinition;
import com.lexmark.domain.GenericLocale;
import com.lexmark.domain.GlobalAccount;
import com.lexmark.domain.LexmarkTransaction;
import com.lexmark.domain.PageCounts;
import com.lexmark.domain.Role;
import com.lexmark.domain.RoleCategory;
import com.lexmark.domain.SupportedLocale;
import com.lexmark.domain.SiebelLocalization.SiebelLocalizationOptionEnum;
import com.lexmark.framework.exception.LGSDBException;
import com.lexmark.framework.logging.LEXLogger;
import com.lexmark.result.CategoryAdministrationListResult;
import com.lexmark.result.DocumentAdministrationListResult;
import com.lexmark.result.DocumentDefinitionDetailsResult;
import com.lexmark.result.GlobalLegalEntityResult;
import com.lexmark.result.SupportedLocaleListResult;
import com.lexmark.result.SwapCategoryOrderNumberResult;
import com.lexmark.services.form.CategoryDisplayForm;
import com.lexmark.services.form.SaveDocumentDefinitionForm;
import com.lexmark.services.portlet.common.CommonController;
import com.lexmark.result.GeographyListResult;
import com.lexmark.services.util.AccountUtil;
import com.lexmark.services.util.ContractFactory;
import com.lexmark.services.util.ObjectDebugUtil;
import com.lexmark.services.util.PortalSessionUtil;
import com.lexmark.services.util.ServiceStatusUtil;
import com.lexmark.services.util.XmlOutputGeneratorForDocument;
import com.lexmark.util.LocaleUtil;
import com.lexmark.util.PerformanceTracker;
import com.lexmark.util.StringUtil;

@Controller
@RequestMapping("VIEW")
public class DocumentAdminController extends BaseController{
	private static final Logger logger = LogManager.getLogger(DocumentAdminController.class);
	private static LEXLogger LOGGER = LEXLogger.getLEXLogger(DocumentAdminController.class);

	@Autowired
	private GlobalService globalService;
	
	@Autowired
	private CommonController commonController;

	@Autowired
	private DocumentManagementService documentManagementService;
	
	@Autowired
	private CustomerReportService customerReportService;

	@Autowired
	ServiceRequestLocale serviceRequestLocaleService;
	
	@Autowired
	private RoleService roleService;

	@Autowired
	private SharedPortletController sharedPortletController;

    @Autowired
    private GlobalLegalEntityService globalLegalEntityService;
    
    @Autowired 
	private GeographyService geographyService; 
    
	@Resource
	private List<GeographyListResult> allCountryList;

	/**
	 * @param model 
	 * @param request 
	 * @param response 
	 * @return String  
	 */
	@RequestMapping()
	public String defaultPage(Model model, RenderRequest request, RenderResponse response) {
		return "documentManagement/admin/documentAdmin";
	}

	/**
	 * @param model 
	 * @param request 
	 * @param response 
	 * @return String 
	 * @throws Exception 
	 */
	@RequestMapping(params = "action=categoryDisplay")
	public String categoryDisplayRender(Model model, RenderRequest request,
			RenderResponse response) throws Exception {
		LOGGER.enter(this.getClass().getSimpleName(), "categoryDisplayRender");
		SupportedLocaleListResult supportedLocaleListResult = serviceRequestLocaleService
				.retrieveSupportedLocaleList();
		List<SupportedLocale> supportedLocales = supportedLocaleListResult.getSupportedLocales();
		List<Role> roleList = roleService.retrieveRoleList();
		CategoryDisplayForm categoryDisplayForm = new CategoryDisplayForm();
		Integer categoryId = 0;
		if (!StringUtil.isStringEmpty(request.getParameter("categoryId"))) {
			categoryId = Integer.valueOf(request.getParameter("categoryId"));
			CategoryDetailContract contract = ContractFactory.getCategoryDetailContract(categoryId);
			RoleCategory selectedCategory = documentManagementService.retrieveCategoryDetail(contract).
					getCategory();
			categoryDisplayForm.setCategory(selectedCategory);
		}
		ResourceURL url = response.createResourceURL();
		url.setResourceID("categoryListURL");
		categoryDisplayForm.setCategoryListURL(url.toString());
		categoryDisplayForm.assemble(supportedLocales, roleList);
		model.addAttribute("categoryDisplayForm", categoryDisplayForm);
		
		List<String> countryListForUser = new ArrayList<String>();
		logger.debug("b4 calling countryListForUser");
		countryListForUser =documentManagementService.getDB_CountryList(categoryId.toString());
		Map<String, String> countryMap = new HashMap<String, String>();
		try{
			
			GeographyListResult countryListResult = geographyService.getCountryDetails();
			List<GeographyCountryContract> savedCountryList = countryListResult.getCountryList();	
		//	List<GeographyCountryContract> savedCountryList = (List<GeographyCountryContract>)allCountryList.get(0).getCountryList();
			for(int i =0;i<countryListForUser.size();i++){
				logger.debug("countryListForUser are "+countryListForUser.size());
				logger.debug("countryListForUser are "+countryListForUser.get(i));
				for(int j =0;j<savedCountryList.size();j++){
					if(savedCountryList.get(j).getCountryCode().equals(countryListForUser.get(i))){
						countryMap.put(savedCountryList.get(j).getCountryCode(), savedCountryList.get(j).getCountryName());
					}
								
				}
			}
			logger.debug("countryMap is "+countryMap);
		}catch (Exception e) {
			logger.debug("Exception "+e.getMessage());
		}
		
		model.addAttribute("countryListForUser",countryMap);
		
		/* This section is for retrieving partner type from Siebel Localization 
		 * Added July 2014*/
		Map<String, String> partnerTypeLov =null;
		try {
			partnerTypeLov =  commonController.retrieveLocalizedLOVMap(SiebelLocalizationOptionEnum.CATEGORY_PARTNER_TYPE_DOCUMENT_LIB.getValue(), request.getLocale());						
		}catch(LGSDBException e){
			LOGGER.error("Unable to retrieve partnerTypeLov "+e.getMessage());
			partnerTypeLov=new HashMap<String,String>();
		}
	
		model.addAttribute("partnerTypeLovList",partnerTypeLov);
		//Ends July 2014 Changes
		
		List<String> partnerTypeForUser = new ArrayList<String>();
		partnerTypeForUser =documentManagementService.getDB_PartnerTypeList(categoryId.toString());
		for(int i =0;i<partnerTypeForUser.size();i++){
			logger.debug("partnerTypeListForUser are "+partnerTypeForUser.get(i));
			}
			model.addAttribute("partnerTypeListForUser",partnerTypeForUser);
		LOGGER.exit(this.getClass().getSimpleName(), "categoryDisplayRender");
		return "documentManagement/admin/categoryDisplay";
	}

	/**
	 * @param categoryDisplayForm 
	 * @param model 
	 * @param request 
	 * @param response 
	 * @throws Exception 
	 */
	@RequestMapping(params = "action=saveCategory")
	public void saveCategory(@ModelAttribute("categoryDisplayForm") CategoryDisplayForm categoryDisplayForm,
			Model model, ActionRequest request, ActionResponse response) throws Exception {
		LOGGER.enter(this.getClass().getSimpleName(), "saveCategory");
		String partnerTypes="";
		if(request.getParameter("partnerTypeList") != null){
			partnerTypes = request.getParameter("partnerTypeList");		
		}		
		LOGGER.debug("value for part list===>"+partnerTypes);
		List<String> partnerTypeList=new ArrayList<String>();
		if(StringUtils.isNotBlank(partnerTypes)){
			partnerTypeList	=Arrays.asList(partnerTypes.split(","));	
		}
		
		for(int i = 0;i<partnerTypeList.size();i++){
		LOGGER.debug("partnerTypeList is ===>"+partnerTypeList.get(i));
		}
		String countryList="";
		if(request.getParameter("countryList") != null){
			countryList = request.getParameter("countryList");		
		}
		LOGGER.debug("value for country list===>"+countryList);
		List<String> countryListArray = new ArrayList<String>();
		if(!countryList.equals("")){
		if(countryList.contains(",")){
			LOGGER.debug("INSIDE IF");
		 countryListArray=Arrays.asList(countryList.split(","));
		}
		else{
			LOGGER.debug("INSIDE ELSE");
			countryListArray.add(0, countryList);
			LOGGER.debug(countryListArray.get(0));
		}
		}
		for(int i = 0;i<countryListArray.size();i++){
		LOGGER.debug("countryListArray is ===>"+countryListArray.get(i));
		}
		assembleCategory(categoryDisplayForm, request);		
		RoleCategory category = categoryDisplayForm.getCategory();
		CategorySaveContract contract = ContractFactory.getCategorySaveContract(category);
		documentManagementService.saveCategory(contract);
		
		String categoryId = categoryDisplayForm.getCategory().getCategoryId().toString();
		LOGGER.debug("Category Id "+categoryId);
		documentManagementService.savePartnerType(partnerTypeList,categoryId);
		documentManagementService.saveCountry(countryListArray,categoryId);
		response.setRenderParameter("action", "categoryDisplay");
		LOGGER.exit(this.getClass().getSimpleName(), "saveCategory");
	}
	
	/**
	 * @param categoryDisplayForm 
	 * @param request 
	 */
	private void assembleCategory(CategoryDisplayForm categoryDisplayForm, ActionRequest request) {
		LOGGER.enter(this.getClass().getSimpleName(), "assembleCategory");
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
		LOGGER.exit(this.getClass().getSimpleName(), "assembleCategory");
	}

	/**
	 * @param form 
	 * @param model 
	 * @param request 
	 * @param response 
	 */
	@RequestMapping(params = "action=saveDocumentDefinition")
	public void saveDocumentDefinition(SaveDocumentDefinitionForm form, Model model, ActionRequest request,
			ActionResponse response) {
		LOGGER.enter(this.getClass().getSimpleName(), "saveDocumentDefinition");
		logger.debug("saveDocumentDefinition: \n" + form);
		String categoryId = form.getCategoryId().toString();
		String maxDisplayOrder=documentManagementService.retrieveMaxDisplayOrder(categoryId);
		logger.debug("prev maxDisplayOrder"+maxDisplayOrder);
		 int maxOrder=Integer.parseInt(maxDisplayOrder);
		 logger.debug("after maxDisplayOrder"+maxDisplayOrder);
         maxOrder=maxOrder+1;
		
		DocumentDefinition definition = null;
		Integer definitionId = form.getDefinitionId();
		if (definitionId != null && definitionId != 0) {
			DocumentDefinitionDetailsContract contract = ContractFactory.getDocumentDefinitionDetailsContract(definitionId);
			DocumentDefinitionDetailsResult result = documentManagementService.getDocumentDefinitionDetails(contract);
			definition = result.getDocumentDefinition();
		} else {
			definition = new DocumentDefinition();
		}
		if(maxDisplayOrder!=null){
            mergeDocumentDefinitionInfo(form, definition, maxOrder);
    }
		DocumentDefinitionSaveContract contract = ContractFactory.getDocumentDefinitionSaveContract(definition);
		documentManagementService.saveDocumentDefinition(contract);
		LOGGER.exit(this.getClass().getSimpleName(), "saveDocumentDefinition");
	}

	/**
	 * @param form 
	 * @param definition 
	 * @param maxDisplayOrder 
	 */
	private void mergeDocumentDefinitionInfo(SaveDocumentDefinitionForm form, DocumentDefinition definition, int maxDisplayOrder) {
		LOGGER.enter(this.getClass().getSimpleName(), "mergeDocumentDefinitionInfo");
		definition.setName(form.getDefinitionName());
		definition.setDisplay_order(String.valueOf(maxDisplayOrder));
		logger.debug("maxDisplayOrder: " + String.valueOf(maxDisplayOrder));

		List<DefinitionLocale> localeList = new ArrayList<DefinitionLocale>();
		for (int i = 0; i < form.getLocaleIds().length; i++) {
			String display = form.getDisplayValues()[i];
			if (!StringUtil.isStringEmpty(display)) {
				DefinitionLocale locale = new DefinitionLocale();
				locale.setName(display);
				SupportedLocale supportedLocale = new SupportedLocale();
				supportedLocale.setSupportedLocaleId(form.getLocaleIds()[i]);
				locale.setSupportedLocale(supportedLocale);
				localeList.add(locale);
			}
		}

		definition.setLocaleList(localeList);
		String legalName = "";
		
		String prevLegalName = form.getLegalName();
		logger.debug("********************* prevLegalName: " + form.getLegalName() + "************");
		logger.debug("********************* displayMdmId: " + form.getDisplayMdmId() + "************");
		int index1 = prevLegalName.indexOf(" (" + form.getDisplayMdmId() + ")");
		if (index1 == -1){
			legalName = form.getLegalName();
		}else{
			logger.debug("********************* indexOf1: " + index1 + "************");
			logger.debug("********************* length: " + prevLegalName.length() + "************");
			legalName =  StringUtils.left(prevLegalName, prevLegalName.length()-(prevLegalName.length() - index1));
			logger.debug("********************* legalName: " + legalName + "************");
		}
		logger.debug("LegalName: " + legalName + ", isLimitAcc() " + form.isLimitAcc());
		//******* added for DOC Lib CR *******
		definition.setCustomerAccount(legalName);
		if (form.isLimitAcc()) {
			//******* added for DOC Lib CR *******
			logger.debug("in limit check");
			definition.setLimitFlag(true);
			// call to be uncommented
			GlobalAccount account = AccountUtil.retrieveAccountByLegalName(globalLegalEntityService, legalName);
			if (account != null) {
				logger.debug("account is not null");
				if(null != account.getMdmId()){
					definition.setMdmId(account.getMdmId());
				}
				if(null != account.getMdmLevel()){
					definition.setMdmLevel(account.getMdmLevel());
				}
				logger.debug("mdm id here is "+account.getMdmId());
				//definition.setMdmId("402000004");
				//definition.setMdmLevel("Global");
				logger.debug("mdmID: " + account.getMdmId() + ", mdmLevel:" + account.getMdmLevel());
			}
		} else {
			definition.setMdmId(null);
			definition.setMdmLevel(null);
			//******* added for DOC Lib CR *******
			definition.setLimitFlag(false);
			definition.setCustomerAccount("");
			logger.debug("mdmID: null, mdmLevel: null");
		}

		RoleCategory category = new RoleCategory();
		category.setCategoryId(form.getCategoryId());
		definition.setRoleCategory(category);
		LOGGER.exit(this.getClass().getSimpleName(), "mergeDocumentDefinitionInfo");
	}

	/**
	 * @param model 
	 * @param request 
	 * @param response 
	 * @return String 
	 */
	@RequestMapping(params = "action=documentName")
	public String documentNameRender(Model model, RenderRequest request, RenderResponse response) {
		LOGGER.enter(this.getClass().getSimpleName(), "documentNameRender");
		logger.debug("documentName: render phase");
		LOGGER.exit(this.getClass().getSimpleName(), "documentNameRender");
		return "documentManagement/admin/documentName";
	}

	/**
	 * @param id 
	 * @param model 
	 * @param request 
	 * @param response 
	 */
	@RequestMapping(params = "action=documentName")
	public void documentNameAction(@RequestParam(value = "id", required = false) Integer id, Model model, ActionRequest request,
			ActionResponse response) {
		LOGGER.enter(this.getClass().getSimpleName(), "documentNameAction");
		DocumentDefinition documentDefinition = null;
		if (id != null) {
			DocumentDefinitionDetailsContract contract = ContractFactory.getDocumentDefinitionDetailsContract(id);
			DocumentDefinitionDetailsResult result = documentManagementService.getDocumentDefinitionDetails(contract);
			documentDefinition = result.getDocumentDefinition();
			model.addAttribute("definition", documentDefinition);
			logger.debug("already in ===:" + documentDefinition.getLocaleList());

			String mdmId = documentDefinition.getMdmId();
			String mdmLevel = documentDefinition.getMdmLevel();
			//******* added for DOC Lib CR *******
			logger.debug("mdm id 111111111 is "+mdmId);
			logger.debug("mdmLevel 111111111 is "+mdmLevel);
			//logger.debug("limit flag from portal db is "+documentDefinition.getLimitFlag());
			//logger.debug("account name from portal db is "+documentDefinition.getCustomerAccount());
			String customerAccount = documentDefinition.getCustomerAccount();
			String legalName = "";
			String displayMdmId = "";
			if(!StringUtil.isStringEmpty(mdmId) && !StringUtil.isStringEmpty(mdmLevel)) {
				GlobalLegalEntityContract accountContract = ContractFactory.getGlobalLegalEntityContract(mdmId, mdmLevel);
				PortletSession session = request.getPortletSession();
				LexmarkTransaction lexmarkTran = PerformanceTracker.startTracking(LexmarkConstants.TARGET_SYSTEM_SIEBEL, "retrieveGlobalLegalEntity"
						, PortalSessionUtil.getMdmId(session), PortalSessionUtil.getServiceUser(session).getEmailAddress());
				logger.debug("Contract start");
				ObjectDebugUtil.printObjectContent(accountContract, logger);
				logger.debug("Contract end");
				GlobalLegalEntityResult accountResult = globalService.retrieveGlobalLegalEntity(accountContract);
				PerformanceTracker.endTracking(lexmarkTran);
				GlobalAccount account = accountResult.getAccount();
				displayMdmId = (account == null ? "" : account.getDisplayMdmId());
				legalName = (account == null ? "" : account.getLegalName() + " (" + displayMdmId + ")");
			}
			logger.debug("legalName: " + legalName);
			// legal name changed to customer name because legal name doesn't include the mdm id
			model.addAttribute("legalName", customerAccount);
			model.addAttribute("displayMdmId", displayMdmId);
			model.addAttribute("customerAccount", customerAccount);
		}
		CategoryAdministrationListContract contract = ContractFactory.getDocumentCategoryAdministrationListContract();
		CategoryAdministrationListResult result = documentManagementService.retrieveCategoryAdministrationList(contract);
		List<RoleCategory> categories = result.getRoleCategoryList();

		SupportedLocaleListResult supportedLocaleListResult = serviceRequestLocaleService.retrieveSupportedLocaleList();
		List<SupportedLocale> supportedLocales = supportedLocaleListResult.getSupportedLocales();

		List<? extends GenericLocale> localeList = documentDefinition == null ? null : documentDefinition.getLocaleList();
		logger.debug("already in:" + localeList);
		logger.debug("supportedLocales" + supportedLocales);
		localeList = LocaleUtil.expandLocaleList(localeList, supportedLocales);
		logger.debug("expaned:" + localeList);
		model.addAttribute("locales", localeList);
		model.addAttribute("categories", categories);
		response.setRenderParameter("action", "documentName");
		LOGGER.exit(this.getClass().getSimpleName(), "documentNameAction");
	}

	/**
	 * @param id 
	 * @param request 
	 * @param response 
	 * @throws IOException 
	 */
	@ResourceMapping("deleteDocumentName")
	public void deleteDocumentName(@RequestParam("id") int id, ResourceRequest request, ResourceResponse response)
			throws IOException {
		LOGGER.enter(this.getClass().getSimpleName(), "deleteDocumentName");
		try {
			DocumentDefinitionDeleteContract contract = ContractFactory.getDocumentDefinitionDeleteContract(id);
			documentManagementService.deleteDocumentDefinition(contract);
			ServiceStatusUtil.responseResult(response, "message.documentManagement.deleteDocumentName", request.getLocale());
		} catch (Exception e) {
			ServiceStatusUtil.responseResult(response, "exception.documentManagement.deleteDocumentName", request.getLocale());
			logger.debug(e.getMessage());
		}
		LOGGER.exit(this.getClass().getSimpleName(), "deleteDocumentName");
	}

	/**
	 * @param request 
	 * @param response 
	 * @throws IOException 
	 */
	@ResourceMapping("documentListGrid")
	public void documentListGridXML(ResourceRequest request, ResourceResponse response) throws IOException {
		LOGGER.enter(this.getClass().getSimpleName(), "documentListGridXML");
		DocumentAdministrationListContract contract = ContractFactory.getDocumentAdministrationListContract();
		DocumentAdministrationListResult result = documentManagementService.retrieveDocumentAdministrationList(contract);
		List<DocumentDefinition> documentDefinitions = result.getDocumentDefinitions();
		XmlOutputGeneratorForDocument outputGenerator = getXmlOutputGeneratorForDocument(request);
		String xml = outputGenerator.generateDocumentAdminListGrid(documentDefinitions);
		PrintWriter out = response.getWriter();
		response.setContentType("text/xml");
		out.print(xml);
		out.flush();
		out.close();
		LOGGER.exit(this.getClass().getSimpleName(), "documentListGridXML");
	}
	
	/* displayOrderGridXML fetches the display Orders for the category Name selected
	 * CI BRD 14-06-01
	 */
	/**
	 * @param request 
	 * @param response 
	 * @throws IOException 
	 */
	@ResourceMapping("displayOrderGrid")
	public void displayOrderGridXML(ResourceRequest request, ResourceResponse response) throws IOException {
		LOGGER.enter(this.getClass().getSimpleName(), "displayOrderGridXML");
		String categoryName= request.getParameter("categoryName");
		String[] nameList;
		LOGGER.debug("Category Name selected from the Admin Grid-->>"+categoryName);
		String result_category = documentManagementService.retrieveCategoryIDForPopup(categoryName);
		LOGGER.debug("Category ID fetched from DB result_category-->>"+result_category);
		DocumentAdministrationListContract contract = ContractFactory.getDocumentAdministrationListContract();
		DocumentAdministrationListResult result = documentManagementService.retrieveDocumentAdministrationDisplayOrder(contract,result_category);
		if(result!=null){
			List<DocumentDefinition> documentDefinitions = result.getDocumentDefinitions();
			XmlOutputGeneratorForDocument outputGenerator = getXmlOutputGeneratorForDocument(request);
			String xml = outputGenerator.generateDocumentDisplayOrderGrid(documentDefinitions,categoryName);
		
		
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/xml");
		out.print(xml);
		out.flush();
		out.close();
		}
		else{
			logger.debug("result is NULL----------------");
		}
		LOGGER.exit(this.getClass().getSimpleName(), "displayOrderGridXML");
	}
	
	/* updateOrderGridXML fetches the display Orders for the category Name selected
	 * CI BRD 14-06-01
	 */
	/**
	 * @param request 
	 * @param response 
	 * @param docIds 
	 * @param orderValues 
	 * @throws IOException 
	 */
	@ResourceMapping("updateOrderGrid")
	public void updateOrderGridXML(ResourceRequest request, ResourceResponse response
			,@RequestParam(value="docIds", required=false)String []docIds,@RequestParam(value="orderValues", required=false)String []orderValues ) throws IOException {
		LOGGER.enter(this.getClass().getSimpleName(), "updateOrderGridXML");
		try{
			//String docIds= request.getParameter("docIds");
			LOGGER.debug("docIds from jsp ------------"+Arrays.toString(docIds));
			LOGGER.debug("orderValues from jsp ------------"+Arrays.toString(orderValues));
			
			
			List<String> docIdList=Arrays.asList(Arrays.toString(docIds).replace("[", "").replace("]", "").split(",",-1));
			
			List<String> orderValuesList=Arrays.asList(Arrays.toString(orderValues).replace("[", "").replace("]", "").split(",",-1));
			
			Boolean result= documentManagementService.updateDisplayOrder(docIdList,orderValuesList);
			
			LOGGER.debug("SUCCESS---->>"+result);
			
			for(int i=0; i<docIdList.size();i++){
				
				LOGGER.debug("docIds from jsp ------------"+docIdList.get(i).toString());
				LOGGER.debug("orderValuesList from jsp ------------"+orderValuesList.get(i).toString());
			}
			//ListIterator litr = docIdList.listIterator();
			//LOGGER.debug("docIds from jsp ------------"+docIds);
//		      while(litr.hasNext()) {
//		        // String element = litr.;
//		         LOGGER.debug("Element ------------"+docIdList);
//		      }
		}
		
		catch(Exception ex){
			LOGGER.debug("Exception "+ex.getMessage());
		}
		LOGGER.exit(this.getClass().getSimpleName(), "updateOrderGridXML");
	}

	/**
	 * @param request 
	 * @param response 
	 * @throws Exception 
	 */
	@ResourceMapping("categoryListURL")
	public void categoryListGridXML(ResourceRequest request, ResourceResponse response) throws Exception {
		LOGGER.enter(this.getClass().getSimpleName(), "categoryListGridXML");
		CategoryAdministrationListContract contract = ContractFactory.getDocumentCategoryAdministrationListContract();
		CategoryAdministrationListResult result = documentManagementService.retrieveCategoryAdministrationList(contract);
		String xml = getXmlOutputGenerator(request.getLocale()).
		convertCategoryListToXML(result.getRoleCategoryList(), request.getContextPath());
		PrintWriter out = response.getWriter();
		response.setContentType("text/xml");
		out.print(xml);
		out.flush();
		out.close();
		LOGGER.exit(this.getClass().getSimpleName(), "categoryListGridXML");
	}

	/**
	 * @param orderNumber 
	 * @param increment 
	 * @param request 
	 * @param response 
	 * @throws Exception 
	 */
	@ResourceMapping("swapCategory")
	public void swapOrder(@RequestParam("orderNumber") int orderNumber, 
			@RequestParam("increment") int increment, ResourceRequest request, 
			ResourceResponse response) throws Exception {
		LOGGER.enter(this.getClass().getSimpleName(), "swapOrder");
		SwapCategoryOrderNumberContract contract = ContractFactory.
				getSwapCategoryOrderNumberContract(orderNumber, increment, LexmarkConstants.CATEGORY_TYPE_DOCUMENT);
		try {
			SwapCategoryOrderNumberResult result = customerReportService.swapCategoryOrderNumber(contract);
			if (result.isSucceed()) {
				ServiceStatusUtil.responseResult(response,
						"message.documentManagement.changeOrder", request.getLocale());
			} else {
				ServiceStatusUtil.responseResult(response,
						"exception.documentManagement.changeOrder", request.getLocale());
			}
		} catch (Exception e) {
			ServiceStatusUtil.responseResult(response,
					"exception.documentManagement.changeOrder", request.getLocale());
		}
		LOGGER.exit(this.getClass().getSimpleName(), "swapOrder");
	}

	/**
	 * @param categoryId 
	 * @param request 
	 * @param response 
	 * @throws IOException 
	 */
	@ResourceMapping("deleteCategory")
	public void deleteCategory(@RequestParam("categoryId") int categoryId, ResourceRequest request, ResourceResponse response) throws IOException {
		LOGGER.enter(this.getClass().getSimpleName(), "deleteCategory");
		try {
			CategoryDeleteContract contract = ContractFactory.getCategoryDeleteContract(categoryId);
			documentManagementService.deleteCategory(contract);
			ServiceStatusUtil.responseResult(response, "message.documentManagement.deleteCategory", request.getLocale());
		} catch (Exception e) {
			if (e.getMessage().equals(LexmarkConstants.DOC_DEFINITION_ASSOCIATED)) {
				ServiceStatusUtil.responseResult(response, "exception.documentManagement.deleteCategoryDefinitionAssociated", request.getLocale());
			} else {
				logger.debug("Exception "+e.getMessage());
				ServiceStatusUtil.responseResult(response, "exception.documentManagement.deleteCategory", request.getLocale());
			}
		}
		LOGGER.exit(this.getClass().getSimpleName(), "deleteCategory");
	}

	/**
	 * @param defaultValue 
	 * @param request 
	 * @param response 
	 * @throws Exception 
	 */
	@ResourceMapping("acccountSelection")
	public void retrieveGlobalLegalEntityList(@RequestParam(value = "default", required = false) String defaultValue,
			ResourceRequest request, ResourceResponse response) throws Exception {
		LOGGER.enter(this.getClass().getSimpleName(), "retrieveGlobalLegalEntityList");
		sharedPortletController.retrieveGlobalLegalEntityList(defaultValue, request, response);
		LOGGER.exit(this.getClass().getSimpleName(), "retrieveGlobalLegalEntityList");
	}

}