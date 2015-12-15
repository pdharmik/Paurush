/**
 * Copyright@ 2012. This document has been created & written by
 * Wipro technologies for Lexmark and this is copyright to Lexmark
 *
 * File         	: ManageContactsController.java
 * Package     		: com.lexmark.services.portlet.cm
 * Creation Date 	: 2013
 *
 * Modification History:
 *
 * ---------------------------------------------------------------------
 * Author 				Date				Version  		Comments
 * ---------------------------------------------------------------------
 * wipro		2013 		2.1             Initial Version
 * 
 */

package com.lexmark.services.portlet.cm;

import static com.lexmark.services.LexmarkSPConstants.ERROR_MESSAGE_BUNDLE;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.portlet.ResourceURL;
import javax.servlet.http.HttpServletRequest;

import org.apache.axis.utils.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.contract.AttachmentContract;
import com.lexmark.contract.CreateServiceRequestContract;
import com.lexmark.contract.ProcessCustomerContactContract;
import com.lexmark.domain.AccountContact;
import com.lexmark.domain.Attachment;
import com.lexmark.domain.GenericAddress;
import com.lexmark.domain.ServiceRequest;
import com.lexmark.domain.ServiceRequestDTO;
import com.lexmark.exceptionimpl.checked.LGSCheckedException;
import com.lexmark.exceptionimpl.checked.business.LGSBusinessException;
import com.lexmark.exceptionimpl.runtime.LGSRuntimeException;
import com.lexmark.framework.logging.LEXLogger;
import com.lexmark.result.CreateServiceRequestResult;
import com.lexmark.result.ProcessCustomerContactResult;
import com.lexmark.service.api.AttachmentService;
import com.lexmark.service.impl.real.util.AttachmentProperties;
import com.lexmark.services.api.cm.ManageContactService;
import com.lexmark.services.form.AttachmentForm;
import com.lexmark.services.form.FileUploadForm;
import com.lexmark.services.form.ManageContactForm;
import com.lexmark.services.form.validator.CommonValidator;
import com.lexmark.services.portlet.BaseController;
import com.lexmark.services.portlet.common.CommonController;
import com.lexmark.services.util.ChangeMgmtConstant;
import com.lexmark.services.util.ContractFactory;
import com.lexmark.services.util.ObjectDebugUtil;
import com.lexmark.services.util.PerformanceConstant;
import com.lexmark.services.util.PortalSessionUtil;
import com.lexmark.services.util.ServiceStatusErrorMessageUtil;
import com.lexmark.services.util.PerformanceUtil; //Added for performance logging
import com.lexmark.util.DateUtil;
import com.lexmark.util.StringUtil;
import com.liferay.portal.util.PortalUtil;


/**
 * This is class is used for contact section in change management
 * @author wipro
 * @version 2.1
 */
@Controller
@RequestMapping("VIEW")
@SessionAttributes(value={ChangeMgmtConstant.MANAGECONTACTFORM})
public class ManageContactsController extends BaseController{
	
	/** Holds ManageContactServiceImpl bean reference **/
	@Autowired
	private ManageContactService contactService;
	
	/** holds the reference of CommonController bean **/
	@Autowired
	private CommonController commonController;
	
	/** holds the reference of CommonValidator bean **/
	@Autowired
	private CommonValidator commonValidator;
	@Autowired
	private AttachmentService attachmentService;//Added for CI 14-02-03
	
	/**. Instance variable of wrapper logger class **/
	private static final LEXLogger LOGGER = LEXLogger.getLEXLogger(ManageContactsController.class);
	private static Logger perfLogger = LogManager.getLogger("performance"); //Added for performance logging
	private static final String CLASS_NAME = "ManageContactsController";
	//Added for CI 14-02-03 START
		private String listOfFileTypes;
		private String attMaxCount;
		public String getListOfFileTypes() {
			return listOfFileTypes;
		}

		public void setListOfFileTypes(String listOfFileTypes) {
			this.listOfFileTypes = listOfFileTypes;
		}

		public String getAttMaxCount() {
			return attMaxCount;
		}

		public void setAttMaxCount(String attMaxCount) {
			this.attMaxCount = attMaxCount;
		}
		//Added for CI 14-02-03 ENDS
	
	/**
	 * This method is used for validation in manage contact
	 * @param binder 
	 * @param locale 
	 */
	@InitBinder (value={ChangeMgmtConstant.MANAGECONTACTFORM})
	protected void initBinder(WebDataBinder binder, Locale locale) {
		String METHOD_NAME = "initBinder";
		LOGGER.enter(CLASS_NAME, METHOD_NAME);
		LOGGER.debug("initBinder: Language is " + locale.getLanguage()+" country is " + locale.getCountry()+
					 " format is "+ DateUtil.getDateFormatByLang(locale.getLanguage()));
		
		DateFormat sdf=new SimpleDateFormat(DateUtil.getDateFormatByLang(locale.getLanguage()));
		sdf.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
		
		/*
		 * Bind the form with validator
		 */
		if (binder.getTarget() instanceof ManageContactForm) {	
			binder.setValidator(new CommonValidator());
		}
		LOGGER.exit(CLASS_NAME, METHOD_NAME);
	}
	
	/**
	 * This method is used to print contact 
	 * @param request 
	 * @param response 
	 * @param model 
	 * @return String 
	 * @throws Exception 
	 */
	@RequestMapping(params = "action=printContact")
	public String showContactSummary(RenderRequest request, RenderResponse response, Model model) throws Exception{
		return ChangeMgmtConstant.PRINTCONTACTPATH;
	}
	
	/**
	 * This method is used to email contact 
	 * @param request 
	 * @param response 
	 * @param model 
	 * @return String 
	 * @throws Exception 
	 */
	@RequestMapping(params = "action=emailContact")
	public String showContactEmail(RenderRequest request, RenderResponse response, Model model) throws Exception{
		
		return ChangeMgmtConstant.CONTACTEMAILPATH;
	}
	/**
	 * Retrieve List of all contacts 
	 * @param model 
	 * @param request 
	 * @param response 
	 * @return String 
	 * @throws LGSCheckedException 
	 * @throws LGSRuntimeException 
	 */
	@RequestMapping
	public String viewContactList(Model model, RenderRequest request, RenderResponse response) 
			throws LGSCheckedException, LGSRuntimeException {
		String METHOD_NAME = "viewContactList";
		
		/*
		 * Implementation of render request goes here
		 */
		Boolean isUpdateFlag=false;
		LOGGER.enter(CLASS_NAME, METHOD_NAME);
		try {
			/*
			 * Add the previous SR no with form data. This is for update contact.
			 */
			HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));
			String prevSrNumber = httpReq.getParameter(ChangeMgmtConstant.SERVICEREQNO);
			
			
			
			if(!StringUtil.isEmpty(httpReq.getParameter(ChangeMgmtConstant.UPDATEFLAG)))
			{
				isUpdateFlag= Boolean.parseBoolean(httpReq.getParameter(ChangeMgmtConstant.UPDATEFLAG));
				
				
				request.setAttribute(ChangeMgmtConstant.ISUPDATEFLAG, isUpdateFlag);
			}
			
			if(prevSrNumber!= null){
				
				request.setAttribute(ChangeMgmtConstant.SERVICEREQNO, prevSrNumber);
			}
		
			model.addAttribute(ChangeMgmtConstant.CONTACTID,PortalSessionUtil.getContactId(request.getPortletSession()));
			getResourceURL(response, model);//This is for the contact list population		
		} catch(LGSCheckedException ex)
		{
			request.setAttribute(ChangeMgmtConstant.EXCEPTION,ChangeMgmtConstant.TRUEATTR);
			return ChangeMgmtConstant.ALLREQHISTPAGENM;
		}
		
		try {
		retrieveGridSetting(ChangeMgmtConstant.CONTACTGRIDCONTAINER,request,response,model);//Loading data from db
		}catch(Exception ex)
		{
			request.setAttribute(ChangeMgmtConstant.EXCEPTION,ChangeMgmtConstant.TRUEATTR);
			return ChangeMgmtConstant.ALLREQHISTPAGENM;
		}
		LOGGER.exit(CLASS_NAME, METHOD_NAME);
		return ChangeMgmtConstant.CONTACTLISTPATH;
	}
	
	/**
	 * Get the resource URL 
	 * @param response 
	 * @param model 
	 * @throws LGSCheckedException 
	 */
	private void getResourceURL(RenderResponse response, Model model) throws LGSCheckedException {
		String METHOD_NAME = "getResourceURL";
		LOGGER.enter(CLASS_NAME, METHOD_NAME);
		try {
		ResourceURL resURL =response.createResourceURL();
		resURL.setResourceID("primarycontactListPopulate");//This mapping is present in the common controller
		model.addAttribute(ChangeMgmtConstant.CONTACTRESULTLIST, resURL.toString());
		}catch(Exception ex)
		{
			throw new LGSCheckedException("Error while creating resource URL");
		}
		LOGGER.exit(CLASS_NAME, METHOD_NAME);
	}
	
	/**
	 * When the user clicks the add contact button in list page, this method is used to redirect to add contact page
	 * @param request 
	 * @param response 
	 * @param selectedVal 
	 * @param modelMap 
	 * @return String 
	 * @throws LGSCheckedException 
	 * @throws LGSRuntimeException 
	 */
	@RenderMapping(params="action=redirectToAddContact")
	public String redirectAsPerSelectedValue(RenderRequest request,
			RenderResponse response, @RequestParam(ChangeMgmtConstant.SELECTEDVALUE) String selectedVal,
			ModelMap modelMap) throws LGSCheckedException,
			LGSRuntimeException {
		String METHOD_NAME= "redirectAsPerSelectedValue";
		LOGGER.enter(CLASS_NAME, METHOD_NAME);
		Boolean addContactFlag=false;
		Boolean isUpdateFlag=false;
		
		
		try {
			if(selectedVal!="" && selectedVal.equalsIgnoreCase(ChangeMgmtConstant.ADDONE))
			{
				/*
				 * Execute block if flow is for add contact
				 */
				
				/*
				 * Changes for MPS 2.1 Contact
				 * */
				ManageContactForm contactForm=null;
				
				
				if(ChangeMgmtConstant.TRUE.equalsIgnoreCase(request.getParameter(ChangeMgmtConstant.ISBACK))){
					contactForm=(ManageContactForm)modelMap.get(ChangeMgmtConstant.MANAGECONTACTFORM);
					
				}else{
					contactForm=new ManageContactForm();
					ServiceRequest serviceReq=new ServiceRequest();
					contactForm.setServiceRequest(serviceReq);
				}
				/*ENDS Changes for MPS 2.1 Contact
				 * */	
					
					
					
					/*
					 * It is the information of the requestor
					 */
					AccountContact accContact=commonController.getContactInformation(request, response);
					contactForm.getServiceRequest().setPrimaryContact(accContact);
					/*
					 * Add the previous SR no with form data. This is for update contact.
					 */
					
					if(!StringUtil.isEmpty(request.getParameter(ChangeMgmtConstant.ISUPDATEFLAG)))
					{
						//It will be set to true
						isUpdateFlag=Boolean.parseBoolean(request.getParameter(ChangeMgmtConstant.UPDATEFLAG));
					}
					
					if(!StringUtil.isEmpty(request.getParameter(ChangeMgmtConstant.PREVSRNO))&& isUpdateFlag)
					{
						contactForm.setPrevSRNumber(request.getParameter(ChangeMgmtConstant.PREVSRNO));
						contactForm.setUpdateSrFlag(isUpdateFlag);
					}
					
					modelMap.addAttribute(ChangeMgmtConstant.MANAGECONTACTFORM, contactForm);
					addContactFlag=true;				
			}
		}
		catch (LGSRuntimeException ex) {
			
			request.setAttribute(ChangeMgmtConstant.EXCEPTION,ChangeMgmtConstant.TRUEATTR);
			return ChangeMgmtConstant.CONTACTLISTPATH; //If exception, return to Contact List
		}
		LOGGER.exit(CLASS_NAME, METHOD_NAME);
		if(addContactFlag) {	
			return ChangeMgmtConstant.ADDCONTACTPATH;//Forwarding to addContact JSP Page
		}
		else {
			return ChangeMgmtConstant.CONTACTLISTPATH; //If error, return to Contact List
		}
		
	}

	/**
	 * When user clicks selects a row from contact list, this method is used to retrieve and set the value from the selected row.
	 * @param model 
	 * @param request 
	 * @param response 
	 * @param requestType 
	 * @throws LGSRuntimeException 
	 */
	@RenderMapping(params="action=setOldContact")
	public void setOldContact(Model model,RenderRequest request, RenderResponse response,
							  @RequestParam(value = "requestType") String requestType)
							  throws LGSRuntimeException {
				
		/*
		 * Get the field values from selected row of contact list
		 */	
		String METHOD_NAME= "setOldContact";
		LOGGER.enter(CLASS_NAME, METHOD_NAME);
		final Enumeration<String> paramNames=request.getParameterNames();		
		Map<String, String> contactOld=new HashMap<String,String>();
		while(paramNames.hasMoreElements()){
			final String paramName=paramNames.nextElement();
			contactOld.put(paramName, request.getParameter(paramName));
		}
		contactOld.put(ChangeMgmtConstant.FLOWDIRECTION, ChangeMgmtConstant.FORWARD);

		PortletSession session = request.getPortletSession();
			if (requestType.equalsIgnoreCase(ChangeMgmtConstant.REMOVECONTACTRQST)) {
				session.setAttribute(ChangeMgmtConstant.OLDCONTACTREMOVE, contactOld, PortletSession.APPLICATION_SCOPE);
				
			}
			else if (requestType.equalsIgnoreCase(ChangeMgmtConstant.CHANGECONTACTRQST)) {
				session.setAttribute(ChangeMgmtConstant.OLDCONTACTCHANGE, contactOld, PortletSession.APPLICATION_SCOPE);
				
			}
		LOGGER.exit(CLASS_NAME, METHOD_NAME);
	}


	/**
	 * When user clicks on change or remove contact icon from contact list, 
	 * this method is used to take the user to change or remove contact page.
	 * @param request 
	 * @param response 
	 * @param contactId 
	 * @param requestType 
	 * @param modelMap 
	 * @return String 
	 * @throws LGSCheckedException 
	 * @throws LGSRuntimeException 
	 */
	@RenderMapping(params = "action=changeRemoveContact")
	public String changeRemoveContact(RenderRequest request,
			Model model, //Added for CI 14-02-03
			RenderResponse response,
			@RequestParam(value = "contactId") String contactId,
			@RequestParam(value = "requestType") String requestType,
			ModelMap modelMap) throws LGSCheckedException, LGSRuntimeException {
		String METHOD_NAME= "changeRemoveContact";
		LOGGER.enter(CLASS_NAME, METHOD_NAME);
				//Added for CI 14-02-03 START
				PortletSession session = request.getPortletSession();
				session.removeAttribute("attachmentList");
				session.removeAttribute("attachmentForm");
				AttachmentForm attachForm = new AttachmentForm();
				//Added for CI 14-02-03 ENDS
		Boolean changeContactFlag = false;
		Boolean isUpdateFlag=false;
		
		
		
		try {
		if (requestType != null) {
			ManageContactForm contactForm=new ManageContactForm();
			
			ServiceRequest serviceReq=new ServiceRequest();
			contactForm.setServiceRequest(serviceReq);
			
			/*
			 * It is the information of the requestor
			 */
			AccountContact accContact=commonController.getContactInformation(request, response);
			contactForm.getServiceRequest().setPrimaryContact(accContact);
			
			/*
			 * Add the previous SR no with form data. This is for update contact.
			 */
			
			if(!StringUtil.isEmpty(request.getParameter(ChangeMgmtConstant.ISUPDATEFLAG)))
			{
				//It will be set to true
				isUpdateFlag=Boolean.parseBoolean(request.getParameter(ChangeMgmtConstant.UPDATEFLAG));
			}
			
			if(!StringUtil.isEmpty(request.getParameter(ChangeMgmtConstant.PREVSRNO))&& isUpdateFlag)
			{
				contactForm.setPrevSRNumber(request.getParameter(ChangeMgmtConstant.PREVSRNO));
				contactForm.setUpdateSrFlag(isUpdateFlag);
			}
					
			modelMap.addAttribute(ChangeMgmtConstant.MANAGECONTACTFORM, contactForm);

			/*
			 * Set the flag for change or remove contact
			 */
			if (requestType.equalsIgnoreCase(ChangeMgmtConstant.CHANGECONTACTRQST)) {
				changeContactFlag = true;
			} else if (requestType.equalsIgnoreCase(ChangeMgmtConstant.REMOVECONTACTRQST)) {
				changeContactFlag = false;
			}
		}
		}
		catch (LGSRuntimeException ex) {
			logStackTrace(ex);
			request.setAttribute(ChangeMgmtConstant.EXCEPTION,ChangeMgmtConstant.TRUEATTR);
			return ChangeMgmtConstant.CONTACTLISTPATH; //If exception, return to Contact List
		}
		//Added for CI 14-02-03 START
		attachForm.setListOfFileTypes("csv,xls,xlsx,vsd,doc,docx,ppt,pptx,pdf,zip");
		attachForm.setAttMaxCount("2");
		model.addAttribute("attachmentForm",attachForm);
		//Added for CI 14-02-03 ENDS
		LOGGER.exit(CLASS_NAME, METHOD_NAME);
		if (changeContactFlag) 
		{
			// The request is for Change Contact
			return ChangeMgmtConstant.CHANGECONTACTPATH;
		} else {
			// The request is for Remove Contact
			return ChangeMgmtConstant.REMOVECONTACTPATH;
		}
	}
	

	/**
	 * This method is used for review of Add, Change and Remove contact
	 * @param request 
	 * @param response 
	 * @param requestType 
	 * @param manageContactForm 
	 * @param result 
	 * @param modelMap 
	 * @return String 
	 * @throws LGSCheckedException 
	 * @throws LGSRuntimeException 
	 */
	@RenderMapping(params = "action=manageContactReview")
	public String showManageContactReview(Model model,//Added for CI 14-02-03
			RenderRequest request, RenderResponse response,
			@RequestParam(value = ChangeMgmtConstant.REQUESTTYPE) String requestType,
			@ModelAttribute(ChangeMgmtConstant.MANAGECONTACTFORM) ManageContactForm manageContactForm,
			@ModelAttribute ("attachmentForm") AttachmentForm attachForm,//Added for CI 14-02-03
			BindingResult result, ModelMap modelMap) throws LGSCheckedException,
			LGSRuntimeException {
		String METHOD_NAME= "showManageContactReview";
		LOGGER.enter(CLASS_NAME, METHOD_NAME);
		model.addAttribute(ChangeMgmtConstant.MANAGECONTACTFORM, manageContactForm);
				//Added for CI 14-02-03 START
				String attachmentDescription= request.getParameter("attachmentDescriptionID");
				LOGGER.debug("attachmentDescription" + attachmentDescription);
				//Added for CI 14-02-03 ENDS
		String pageURL = "";
		
		
		String timezoneOffset=request.getParameter(ChangeMgmtConstant.TIMEZNOFFSET);
		//Added for CI 14-02-03 START
				PortletSession session = request.getPortletSession();
				
				
					manageContactForm.setAttachmentDescription(attachmentDescription);
					attachForm.setAttachmentDescription(attachmentDescription);
				
				
				LOGGER.debug("request param is " + request.getParameter(ChangeMgmtConstant.TIMEZNOFFSET));
				List<Attachment> attachmentList = (ArrayList<Attachment>) session.getAttribute("attachmentList");
				if (attachmentList != null){
					attachForm.setAttachmentList(attachmentList);
				}
					
				FileUploadForm fileUploadForm = new FileUploadForm();
				//changes for mps2.1
				if(attachForm.getAttachmentList() !=null){
					fileUploadForm.setFileCount(attachForm.getAttachmentList().size());
					}
					//changes for mps2.1
				model.addAttribute("attachmentForm", attachForm);
				model.addAttribute("fileUploadForm", fileUploadForm);
				//Added for CI 14-02-03 ENDS
		
		if(!StringUtil.isEmpty(timezoneOffset))
		{
			modelMap.addAttribute(LexmarkConstants.TIMEZONE_OFFSET, Float.valueOf(timezoneOffset));
		}
		
		/*
		 * If contain errors, navigate to same page itself
		 */
		try {
			if (requestType!=null && requestType.equalsIgnoreCase(ChangeMgmtConstant.ADDCONTACTRQST)) {
				/* This part is to check the mandatory fields for add contact
				 * if they are blank
				 */
				ValidationUtils.rejectIfEmptyOrWhitespace(result,
						ChangeMgmtConstant.AC_FIRSTNAME, ChangeMgmtConstant.AC_FIRSTNAMEEMPTY);
				
				ValidationUtils.rejectIfEmptyOrWhitespace(result,
						ChangeMgmtConstant.AC_LASTNAME, ChangeMgmtConstant.AC_LASTNAMEEMPTY);
				
				ValidationUtils.rejectIfEmptyOrWhitespace(result,
						ChangeMgmtConstant.AC_WORKPHN, ChangeMgmtConstant.AC_WORKPHNEMPTY);
				
				ValidationUtils.rejectIfEmptyOrWhitespace(result,
						ChangeMgmtConstant.AC_EMAILID, ChangeMgmtConstant.AC_EMAILIDEMPTY);
				
				ValidationUtils.rejectIfEmptyOrWhitespace(result,
						ChangeMgmtConstant.AC_ADDLINE1, ChangeMgmtConstant.AC_ADDLINE1EMPTY);
				
				ValidationUtils.rejectIfEmptyOrWhitespace(result,
						ChangeMgmtConstant.AC_CITY, ChangeMgmtConstant.AC_CITYEMPTY);
				
				ValidationUtils.rejectIfEmptyOrWhitespace(result,
						ChangeMgmtConstant.AC_COUNTRY, ChangeMgmtConstant.AC_COUNTRYEMPTY);
				
				ValidationUtils.rejectIfEmptyOrWhitespace(result,
						ChangeMgmtConstant.AC_POSTALCODE, ChangeMgmtConstant.AC_POSTALCODEEMPTY);
			}
			if (requestType!=null && (requestType.equalsIgnoreCase(ChangeMgmtConstant.ADDCONTACTRQST) || 
					requestType.equalsIgnoreCase(ChangeMgmtConstant.CHANGECONTACTRQST))) {
				commonValidator.validate(manageContactForm, result);
			}
			
			if (requestType!=null) {
				/*
				 * If error in validation, show error
				 */
				if (result.hasErrors()) {	
								
					commonController.getContactInformation(request, response);
			
					if (requestType.equalsIgnoreCase(ChangeMgmtConstant.REMOVECONTACTRQST)) {
					pageURL = ChangeMgmtConstant.REMOVECONTACTPATH;
					}
					else if (requestType.equalsIgnoreCase(ChangeMgmtConstant.CHANGECONTACTRQST)) {
					pageURL = ChangeMgmtConstant.CHANGECONTACTPATH;
					}
					else {
					pageURL = ChangeMgmtConstant.ADDCONTACTPATH;
					}							
				}
				
				/*
				 * If no errors, navigate to corresponding review page
				 */	
				else {						
					if (requestType.equalsIgnoreCase(ChangeMgmtConstant.REMOVECONTACTRQST)) {
						manageContactForm.refreshSubmitToken(request);
						pageURL = ChangeMgmtConstant.REMOVECONTACTREVIEWPATH;
					}
					else if (requestType.equalsIgnoreCase(ChangeMgmtConstant.CHANGECONTACTRQST)) {
						manageContactForm.refreshSubmitToken(request);
						pageURL = ChangeMgmtConstant.CHANGECONTACTREVIEWPATH;
					}
					else {
						manageContactForm.refreshSubmitToken(request);
						pageURL = ChangeMgmtConstant.ADDCONTACTREVIEWPATH;
					}
				}
			}
			LOGGER.exit(CLASS_NAME, METHOD_NAME);
			return pageURL;
		}
		/*
		 * If Exception has occured, show error
		 */
		catch (LGSRuntimeException ex) {
			logStackTrace(ex);
			request.setAttribute(ChangeMgmtConstant.EXCEPTION,ChangeMgmtConstant.TRUEATTR);
			if (requestType!=null && requestType.equalsIgnoreCase(ChangeMgmtConstant.REMOVECONTACTRQST)) {
				pageURL = ChangeMgmtConstant.REMOVECONTACTPATH;
			}
			else if (requestType!=null && requestType.equalsIgnoreCase(ChangeMgmtConstant.CHANGECONTACTRQST)) {
				pageURL = ChangeMgmtConstant.CHANGECONTACTPATH;
			}
			else {
				pageURL = ChangeMgmtConstant.ADDCONTACTPATH;
			}
			LOGGER.exit(CLASS_NAME, METHOD_NAME);
			return pageURL;
		}
	}
	

	
	/**
	 * @param request 
	 * @param response 
	 * @param requestType 
	 * @param manageContactForm 
	 * @param modelMap 
	 * @param model 
	 * @param sessionStatus 
	 * @throws LGSCheckedException 
	 * @throws LGSRuntimeException 
	 */
	@SuppressWarnings({ "unchecked"})
	@ActionMapping(params = "action=contactSummary")
	public void contactSummary(
			ActionRequest request, ActionResponse response,
			@RequestParam(value = ChangeMgmtConstant.REQUESTTYPE) String requestType,
			@ModelAttribute ("attachmentForm") AttachmentForm attachForm,//Added for CI 14-02-03
			@ModelAttribute(ChangeMgmtConstant.MANAGECONTACTFORM) ManageContactForm manageContactForm,
			ModelMap modelMap, Model model, SessionStatus sessionStatus) throws LGSCheckedException,
			LGSRuntimeException {
		String METHOD_NAME= "contactSummary";	
		LOGGER.enter(CLASS_NAME, METHOD_NAME);
		//Added for CI 14-02-03 START
		PortletSession session1 = request.getPortletSession();
				List<Attachment> attachmentList = (ArrayList<Attachment>) session1.getAttribute("attachmentList");
				
				if (attachmentList != null){
					attachForm.setAttachmentList(attachmentList);
				}
				attachForm.setAttachmentDescription(manageContactForm.getAttachmentDescription());
				model.addAttribute("attachmentForm", attachForm);
				//Added for CI 14-02-03 ENDS
		CreateServiceRequestResult result=null;
		FutureTask<String> future=null;
		modelMap.addAttribute(ChangeMgmtConstant.MANAGECONTACTFORM, manageContactForm);
		
		
		if (requestType.equalsIgnoreCase(ChangeMgmtConstant.REMOVECONTACTRQST)) {
			
			manageContactForm.setArea(ChangeMgmtConstant.DATAMANAGEMENTAREA);
			manageContactForm.setSubArea(ChangeMgmtConstant.REMOVECONTACTSUBAREA);	
		}
			else if (requestType.equalsIgnoreCase(ChangeMgmtConstant.CHANGECONTACTRQST)) {
				manageContactForm.setArea(ChangeMgmtConstant.DATAMANAGEMENTAREA);
				manageContactForm.setSubArea(ChangeMgmtConstant.CHANGECONTACTSUBAREA);	
		}
			else {
				manageContactForm.setArea(ChangeMgmtConstant.DATAMANAGEMENTAREA);
				manageContactForm.setSubArea(ChangeMgmtConstant.ADDCONTACTSUBAREA);	
		}
		
		/****************This section has been added to prevent resubmit by browser refresh ***********/
		if (PortalSessionUtil.isDuplicatedSubmit(request,manageContactForm)) {
			LOGGER.debug("ManageContactsController duplicated submit, do nothing!");		
			model.addAttribute(ChangeMgmtConstant.ERROR, ServiceStatusErrorMessageUtil.getErrorMessage(ERROR_MESSAGE_BUNDLE,
					ChangeMgmtConstant.DUPLICATESUBMISSION, null, request.getLocale()));
			
			response.setRenderParameter(ChangeMgmtConstant.ACTION, ChangeMgmtConstant.CONTACTSUMMARYSHOW);
			model.addAttribute(manageContactForm);
		}
		/**************** Section end to prevent resubmit by browser refresh ***********/
		else{		
		//This is done for writing csv
		ServiceRequestDTO serviceReqDTO=new ServiceRequestDTO();
		Map<String,String> contactOld = (Map<String, String>) request.getPortletSession().getAttribute
				(ChangeMgmtConstant.OLDCONTACTCHANGE, PortletSession.APPLICATION_SCOPE);	
		try {
			if (requestType!=null && requestType.equalsIgnoreCase(ChangeMgmtConstant.CHANGECONTACTRQST) 
					&& contactOld.get("contactId")!=null)
			{
					
					AccountContact accContact=new AccountContact();
					
					accContact.setContactId(contactOld.get(ChangeMgmtConstant.CONTACTID));
					
					accContact.setFirstName(contactOld.get(ChangeMgmtConstant.FIRSTNAME));
					accContact.setLastName(contactOld.get(ChangeMgmtConstant.LASTNAME));
					accContact.setWorkPhone(contactOld.get(ChangeMgmtConstant.WORKPHONE));
					accContact.setAlternatePhone(contactOld.get(ChangeMgmtConstant.ALTPHONE));
					accContact.setEmailAddress(contactOld.get(ChangeMgmtConstant.EMAILID));
					
					serviceReqDTO.setOldContactData(accContact);
					
			}

			/*
			 * Fill the DTO for change contact csv writing
			 */
			
			try {
				future=commonController.fillDTO(serviceReqDTO, manageContactForm, request);		
			} catch(LGSCheckedException ex)
			{
				
				response.setRenderParameter(ChangeMgmtConstant.ACTION, ChangeMgmtConstant.MANAGECONTACTREVIEW);
				response.setRenderParameter(ChangeMgmtConstant.REQUESTTYPE, requestType);
				request.setAttribute(ChangeMgmtConstant.EXCEPTION,ChangeMgmtConstant.TRUEATTR);
			}
				
			try {
				
				result=doWebServiceCall(manageContactForm,request);				
				model.addAttribute(ChangeMgmtConstant.MODELATTRSRNO, result.getServiceRequestNumber());//To be displayed on summary page	
				
				//Added for 14-02-03 attachment start
				
				String srRowId = result.getServiceRequestRowId();
				
				AttachmentContract contract = new AttachmentContract();
				List <Attachment> createSRAttachmentList = new ArrayList<Attachment>();
				if(attachmentList!=null && !(attachmentList.isEmpty())){
					LOGGER.debug("No of attachment in the manage contact "+attachmentList.size());
					for (int i=0;i<attachmentList.size();i++){
						if(attachmentList.get(i).getId()==null || attachmentList.get(i).getId().equalsIgnoreCase("")){//newly created attachments
							LOGGER.debug("Attachment id is either null or blank");
							createSRAttachmentList.add(attachmentList.get(i));
						}
						LOGGER.debug("$$$$$$$$$$$$$$$$$$$$$$$$ Attachment ban is "+attachmentList.size());
					}
					if(createSRAttachmentList!=null && !(createSRAttachmentList.isEmpty())){
						for(int i=0;i<createSRAttachmentList.size();i++){
							LOGGER.debug("Create SR will be called for following attachments "+createSRAttachmentList.get(i).getAttachmentName());
						}
						contract.setAttachments(createSRAttachmentList);
						contract.setRequestType("Service Request");
						contract.setIdentifier(srRowId);
						
						LOGGER.debug("Contract value sending for create attachment ");
						LOGGER.debug("attachment name "+contract.getAttachments().get(0).getAttachmentName());
						LOGGER.info("start printing lex logger");
						ObjectDebugUtil.printObjectContent(contract, LOGGER);
						LOGGER.info("end printing lex logger");
						
						long timeBeforeCallAttachment=System.currentTimeMillis();	
						try{
							attachmentService.uploadAttachments(contract);
						}
						catch (Exception excepetion) {
							attachForm.setAttachmentList(null);
							LOGGER.info("Exception in Attachment upload.");
							model.addAttribute("attachmentException", "attachfailed");
						}
						long timeAfterCallAttachment=System.currentTimeMillis();
						LOGGER.info("start printing lex logger");
						
						PerformanceUtil.calcTime(perfLogger, PerformanceConstant.MSG_ATTACHMENT, timeBeforeCallAttachment,timeAfterCallAttachment, PerformanceConstant.SYSTEM_AMIND,contract);
						
						LOGGER.info("end printing lex loggger");
						LOGGER.debug("After calling upload attachment");
					}
				}
				//Added for 14-02-03 attachment ends
				
				
			} catch(LGSRuntimeException ex)
			{
				
				response.setRenderParameter(ChangeMgmtConstant.ACTION, ChangeMgmtConstant.MANAGECONTACTREVIEW);
				response.setRenderParameter(ChangeMgmtConstant.REQUESTTYPE, requestType);
				request.setAttribute(ChangeMgmtConstant.EXCEPTION,ChangeMgmtConstant.TRUEATTR);
			}
			
			//This should happen after the web service call
			while (!future.isDone())
	        {
	            
	            try
	            {
	                Thread.sleep(500);
	            } catch (InterruptedException ie)
	            {
	            	//if any error do not show the summary page
	            	response.setRenderParameter(ChangeMgmtConstant.ACTION, ChangeMgmtConstant.MANAGECONTACTREVIEW);
	            	response.setRenderParameter(ChangeMgmtConstant.REQUESTTYPE, requestType);
	            	request.setAttribute(ChangeMgmtConstant.EXCEPTION,ChangeMgmtConstant.TRUEATTR);
	            	
	            }
	        }
			
			
            
			try {
	            if(future.get()!=null)
				{
	            	
					if(result.getServiceRequestRowId().length()>0 && !future.get().equalsIgnoreCase
							(ChangeMgmtConstant.CSVFAILURE))
					{
						commonController.renameAttachment(future.get(), request, result.getServiceRequestRowId().trim());
						sessionStatus.setComplete();
						response.setRenderParameter(ChangeMgmtConstant.ACTION, ChangeMgmtConstant.CONTACTSUMMARYSHOW);
					
						//this is to enable re-submit of SR form on submit/draft exception
						PortletSession session = request.getPortletSession();
						Long tokenInSession = (Long)session.getAttribute(LexmarkConstants.SUBMIT_TOKEN, 
								PortletSession.PORTLET_SCOPE);						
						manageContactForm.setSubmitToken(tokenInSession);
						model.addAttribute(manageContactForm);
					}
					else if(future.get().equalsIgnoreCase(ChangeMgmtConstant.CSVFAILURE))
					{
						
						response.setRenderParameter(ChangeMgmtConstant.ACTION, ChangeMgmtConstant.MANAGECONTACTREVIEW);
						response.setRenderParameter(ChangeMgmtConstant.REQUESTTYPE, requestType);
						request.setAttribute(ChangeMgmtConstant.EXCEPTION,ChangeMgmtConstant.TRUEATTR);
					}
				}
			} catch (ExecutionException ex)
			{
				//if any error do not show the summary page
				response.setRenderParameter(ChangeMgmtConstant.ACTION, ChangeMgmtConstant.MANAGECONTACTREVIEW);
				response.setRenderParameter(ChangeMgmtConstant.REQUESTTYPE, requestType);
				request.setAttribute(ChangeMgmtConstant.EXCEPTION,ChangeMgmtConstant.TRUEATTR);	
			}
			
		} catch(Exception ex)
		{
			//If any error do not show the summary page
			response.setRenderParameter(ChangeMgmtConstant.ACTION, ChangeMgmtConstant.MANAGECONTACTREVIEW);
			response.setRenderParameter(ChangeMgmtConstant.REQUESTTYPE, requestType);
			request.setAttribute(ChangeMgmtConstant.EXCEPTION,ChangeMgmtConstant.TRUEATTR);
		}
	  }	
	  LOGGER.exit(CLASS_NAME, METHOD_NAME);
	}
	
	/**
	 * @param request 
	 * @param response 
	 * @param model 
	 * @param manageContactForm 
	 * @param srNo 
	 * @return String 
	 * @throws LGSCheckedException 
	 * @throws LGSRuntimeException 
	 */
	@RenderMapping(params = "action=contactSummaryShow")
	public String contactSummaryShow(RenderRequest request,
			RenderResponse response, Model model,
			@ModelAttribute(ChangeMgmtConstant.MANAGECONTACTFORM) ManageContactForm manageContactForm,
			@ModelAttribute(ChangeMgmtConstant.MODELATTRSRNO) String srNo) throws LGSCheckedException,
			LGSRuntimeException {
		String METHOD_NAME="contactSummaryShow";
		LOGGER.enter(CLASS_NAME, METHOD_NAME);
		
		model.addAttribute(ChangeMgmtConstant.SRNUMBER,srNo);
		LOGGER.exit(CLASS_NAME, METHOD_NAME);
		return ChangeMgmtConstant.CONTACTSUMMARYPATH;
		
	}
	
	/**
	 * This method is called for service call in manage contact
	 * @param manageContactForm 
	 * @param request 
	 * @return CreateServiceRequestResult 
	 * @throws LGSRuntimeException 
	 * @throws Exception 
	 */
	private CreateServiceRequestResult doWebServiceCall(ManageContactForm manageContactForm, 
			ActionRequest request) throws  LGSRuntimeException, Exception{
		String METHOD_NAME="doWebServiceCall";
		LOGGER.enter(CLASS_NAME, METHOD_NAME);
		try {
			
			
			PortletSession session = request.getPortletSession();
			Map<String,String> accDetails=(Map<String,String>)session.getAttribute
					(ChangeMgmtConstant.ACNTCURRDETAILS,PortletSession.APPLICATION_SCOPE);
			if(accDetails!=null)
			{
			commonController.printAccountDetail(accDetails);
			}
			else{
				
				accDetails=new HashMap<String, String>();//Go for creation of an empty map
			}
			
			
		CreateServiceRequestContract servReqContract=commonController.getServiceReqContract(manageContactForm, request);
		
		ObjectDebugUtil.printObjectContent(servReqContract, LOGGER);
		
		long timeBeforeCall=System.currentTimeMillis();
		final CreateServiceRequestResult result= contactService.createCMRequestService(servReqContract,accDetails);
		long timeAfterCall=System.currentTimeMillis();
		LOGGER.info("start printing lex logger");
		
		PerformanceUtil.calcTime(perfLogger, PerformanceConstant.MANAGECONTACTS_MSG_CREATECMREQUESTSERVICE_CONTACT, timeBeforeCall,timeAfterCall, PerformanceConstant.SYSTEM_WEBMETHODS,servReqContract);
		
		LOGGER.info("end printing lex loggger");
		LOGGER.debug("Service req no is "+result.getServiceRequestNumber());//This is the real sr no
		
		LOGGER.exit(CLASS_NAME, METHOD_NAME);
		return result;
		} catch(LGSRuntimeException ex)
		{
			
			throw new LGSRuntimeException(ChangeMgmtConstant.SAVEDATA_ERROR);
		}	
		
	}

	/**
	 * This method is used when the user clicks the back button from confirmation page
	 * @param request 
	 * @param response 
	 * @param requestType 
	 * @param manageContactForm 
	 * @param modelMap 
	 * @return String 
	 * @throws LGSCheckedException 
	 * @throws LGSRuntimeException 
	 */
	@RenderMapping(params = "action=backToManage")
	public String backToManage(
			RenderRequest request, RenderResponse response,
			@RequestParam(value = ChangeMgmtConstant.REQUESTTYPE) String requestType,
			@ModelAttribute ("attachmentForm") AttachmentForm attachForm,//Added for CI 14-02-03
			@ModelAttribute(ChangeMgmtConstant.MANAGECONTACTFORM) ManageContactForm manageContactForm,
			ModelMap modelMap,Model model) throws LGSCheckedException, LGSRuntimeException {
		String METHOD_NAME = "backToManage";
		LOGGER.enter(CLASS_NAME, METHOD_NAME);
		modelMap.addAttribute(ChangeMgmtConstant.MANAGECONTACTFORM,manageContactForm);
		
		commonController.getContactInformation(request, response);
		/*
		 * Back to add, change or remove contact page as per the request type
		 */
		LOGGER.exit(CLASS_NAME, METHOD_NAME);
		//Added for CI 14-02-03
		PortletSession session = request.getPortletSession();
		List<Attachment> attachmentList = (ArrayList<Attachment>) session.getAttribute("attachmentList");
		if (attachmentList != null){
			
			attachForm.setAttachmentList(attachmentList);
		}
		LOGGER.debug("listOfFileTypes  = "+ listOfFileTypes + attMaxCount);
		attachForm.setListOfFileTypes("csv,xls,xlsx,vsd,doc,docx,ppt,pptx,pdf,zip");
		attachForm.setAttMaxCount("2");
		
		FileUploadForm fileUploadForm = new FileUploadForm();
		//changes for mps2.1
		if(attachForm.getAttachmentList() !=null){
			fileUploadForm.setFileCount(attachForm.getAttachmentList().size());
			}
			//changes for mps2.1
		model.addAttribute("attachmentForm", attachForm);
		model.addAttribute("fileUploadForm", fileUploadForm);
		if(requestType.equalsIgnoreCase(ChangeMgmtConstant.CHANGECONTACTRQST)){
			return ChangeMgmtConstant.CHANGECONTACTPATH;
		}
		else if(requestType.equalsIgnoreCase(ChangeMgmtConstant.REMOVECONTACTRQST)){
			return ChangeMgmtConstant.REMOVECONTACTPATH;
		}
		else {
			return ChangeMgmtConstant.ADDCONTACTPATH;
		}		
	}
	
	/*
	 * Added for MPS 2.1
	 * */
	
	/**
	 * This method is used to add contact in manage contact section in change management
	 * @param model 
	 * @param actRequest 
	 * @param actResponse 
	 * @param sessionStatus 
	 * @param manageContactForm 
	 * @param bindingResult 
	 * @param modelMap 
	 * @throws LGSBusinessException 
	 */
	@ActionMapping(params = "action=submitAddressWithSR")
	public void addContact(Model model, ActionRequest actRequest,
			ActionResponse actResponse,SessionStatus sessionStatus,
			@ModelAttribute(ChangeMgmtConstant.MANAGECONTACTFORM) ManageContactForm manageContactForm,
			BindingResult bindingResult, ModelMap modelMap) throws LGSBusinessException{
		String METHOD_NAME = "addContact";
		
		LOGGER.enter(CLASS_NAME, METHOD_NAME);
		
		LOGGER.debug("addr1... "+manageContactForm.getAccountContact().getAddress().getAddressLine1() + 
				"officenumber "+manageContactForm.getAccountContact().getAddress().getOfficeNumber() + 
				"city "+manageContactForm.getAccountContact().getAddress().getCity());
		
		
		
		
		PortletSession session = actRequest.getPortletSession();
		
		model.addAttribute(ChangeMgmtConstant.MANAGECONTACTFORM, manageContactForm);
		
		
		try {
			
			ProcessCustomerContactContract contract=ContractFactory.getProcessCustomerContactContract(actRequest);
			AccountContact customerContact=manageContactForm.getAccountContact();
			customerContact.setUpdateContactFlag(false);
			customerContact.setNewContactFlag(true);
			contract.setCustomerContact(customerContact);
			
			ObjectDebugUtil.printObjectContent(contract, LOGGER);
			
			long timeBeforeCall=System.currentTimeMillis();
			LOGGER.debug("----WEB Service call starts----");
			final ProcessCustomerContactResult result= contactService.addAndUpdateContactService(contract);
			long timeAfterCall=System.currentTimeMillis();
			LOGGER.info("start printing lex logger");
			
			PerformanceUtil.calcTime(perfLogger, PerformanceConstant.MANAGECONTACTS_MSG_ADDANDUPDATECONTACTSERVICE, timeBeforeCall,timeAfterCall, PerformanceConstant.SYSTEM_WEBMETHODS,contract);
			
			LOGGER.info("end printing lex loggger");
			
			LOGGER.debug("contact reference id is  "+result.getSiebelContactId());
			model.addAttribute(ChangeMgmtConstant.SUCCESS, ChangeMgmtConstant.TRUE);
			
		}catch (Exception exception) {
			
			model.addAttribute(ChangeMgmtConstant.EXCEPTION, ChangeMgmtConstant.TRUE);
		}finally{
			Long tokenInSessionNew = (Long)session.getAttribute(LexmarkConstants.SUBMIT_TOKEN, 
					PortletSession.PORTLET_SCOPE);
			manageContactForm.setSubmitToken(tokenInSessionNew);
			model.addAttribute(ChangeMgmtConstant.MANAGECONTACTFORM,manageContactForm);
			actResponse.setRenderParameter(ChangeMgmtConstant.SELECTEDVALUE, ChangeMgmtConstant.ADDONE);
			actResponse.setRenderParameter(ChangeMgmtConstant.ISBACK,ChangeMgmtConstant.TRUE);
			actResponse.setRenderParameter("action", "redirectToAddContact");
		}
		
		LOGGER.exit(CLASS_NAME, METHOD_NAME);
		
	}
	
	/**Added for Phase 2.1
	 * */
	/**
	 * This method is used to submit the updated contact in manage contact section
	 */
	/**
	 * @param resourceRequest 
	 * @param resourceResponse 
	 * @param workPhone 
	 * @param alternatePhone 
	 * @param emailAddress 
	 * @param contactrowId 
	 * @param firstName 
	 * @param lastName 
	 * @param country 
	 */
	@ResourceMapping("submitUpdatedContact")
	public void submitChangedContact(ResourceRequest resourceRequest,ResourceResponse resourceResponse,
			@RequestParam("workPhone")String workPhone,@RequestParam("alternatePhone")String alternatePhone,
			@RequestParam("emailAddr")String emailAddress,@RequestParam("contactId")String contactrowId,
			@RequestParam("firstName")String firstName,@RequestParam("lastName")String lastName,@RequestParam("country")String country){
		PrintWriter out=null;
		
		resourceResponse.setProperty("Cache-Control", "max-age=0,no-cache,no-store");
		resourceResponse.setProperty("Expires", "max-age=0,no-cache,no-store");
		
		try {
			
			LOGGER.debug(" data coming as rowid="+contactrowId+"  work phone="+workPhone+"  alternate phone="+alternatePhone
					+"email addr="+ emailAddress + " firlst name = "+ firstName+ " last name= "+lastName);
			
			ProcessCustomerContactContract contract=ContractFactory.getProcessCustomerContactContract(resourceRequest);
			AccountContact customerContact=new AccountContact();
			GenericAddress address=new GenericAddress();
			if(country==null || StringUtils.isEmpty(country)){
				Map<String,String> ldapdata=(Map<String,String>)resourceRequest.getPortletSession().getAttribute(LexmarkConstants.LDAP_USER_DATA, PortletSession.APPLICATION_SCOPE);
				
				address.setCountry(ldapdata.get(LexmarkConstants.COUNTRY));
			}else{
				address.setCountry(country);
			}
			
			
			customerContact.setAddress(address);
			customerContact.setContactId(contactrowId.trim());
			customerContact.setWorkPhone(workPhone.trim());
			customerContact.setAlternatePhone(alternatePhone.trim());
			customerContact.setEmailAddress(emailAddress.trim());
			customerContact.setFirstName(firstName.trim());
			customerContact.setLastName(lastName.trim());
			customerContact.setUpdateContactFlag(true);
			customerContact.setNewContactFlag(false);
			contract.setCustomerContact(customerContact);
			
			ObjectDebugUtil.printObjectContent(contract, LOGGER);
			out = resourceResponse.getWriter();
			long timeBeforeCall=System.currentTimeMillis();
			final ProcessCustomerContactResult result= contactService.addAndUpdateContactService(contract);
			long timeAfterCall=System.currentTimeMillis();
			LOGGER.info("start printing lex logger");
			
			PerformanceUtil.calcTime(perfLogger, PerformanceConstant.MANAGECONTACTS_MSG_ADDANDUPDATECONTACTSERVICE, timeBeforeCall,timeAfterCall, PerformanceConstant.SYSTEM_WEBMETHODS,contract);		
			
			LOGGER.info("end printing lex loggger");
			out.write("{\"error\":\"none\"}");
			
		}catch (IOException e) {
			
			LOGGER.error("IOException while invoking response#getWriter()," + e.getMessage());
		}catch(Exception e){
			out.write("{\"error\":\"yes\"}");
		}finally{
			out.flush();
			out.close();
		}
		
	}
	//Ends MPS 2.1
	//Added for CI 14-02-03
	@ResourceMapping(value="displayAttachment")
	public void displayAttachment(ResourceRequest request,ResourceResponse response, Model model) throws LGSRuntimeException{			LOGGER.debug("Inside displayAttachment...");
		
		String fileName = request.getParameter("fileName");
		AttachmentProperties fileProperties = new AttachmentProperties(ChangeMgmtConstant.ATTACHMENT_REQUEST_TYPE);
		LOGGER.debug("Starting File Transfer" + fileProperties.getFileUploadDestination() + fileName);
		openAttachment(request,response, fileName, fileProperties.getFileUploadDestination() + fileName);
		LOGGER.debug("End of displayAttachment...");
		
	}
	/**
	 * This method is used to opening any attachment and called from displayAttachment method 
	 * @param request 
	 * @param response 
	 * @param fileName 
	 * @param fullPath 
	 */
	private void openAttachment(ResourceRequest request, ResourceResponse response,String fileName, String fullPath){
		
		HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));
		String userAgent = httpReq.getHeader("User-Agent");
	    if (userAgent.contains("MSIE 7.0")) {
	    	fileName = fileName.replace(" ", "%20");   
	    }   
	    response.setProperty("Content-disposition", "attachment; filename=\"" + fileName +"\"");
		LOGGER.debug("fileName " + fileName);
		LOGGER.debug("fullPath " + fullPath);
		if(fileName.indexOf("csv")>0){
			LOGGER.debug("...fileName.indexOf(csv)" + fileName.indexOf("csv"));
        	response.setContentType("application/vnd.ms-excel");
        }else if(fileName.indexOf("pdf") > 0) {
        	LOGGER.debug("...fileName.indexOf(pdf)" + fileName.indexOf("pdf"));
        	response.setContentType("application/pdf");
        }else if(fileName.indexOf("xls")>0){
        	LOGGER.debug("...fileName.indexOf(xls)" + fileName.indexOf("xls"));
        	response.setContentType("application/vnd.ms-excel");
        }else if(fileName.indexOf("doc")>0){
        	LOGGER.debug("...fileName.indexOf(doc)" + fileName.indexOf("doc"));
        	response.setContentType("application/msword");
        }else if(fileName.indexOf("zip") > 0) {
        	response.setContentType("application/zip");
        }else if(fileName.indexOf("xlsx")>0){
        	response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        }else if(fileName.indexOf("docx")>0){
        	response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        }
        else if(fileName.indexOf("ppt")>0){
        	response.setContentType("application/vnd.ms-powerpoint");
        }
        else if(fileName.indexOf("pptx")>0){
        	response.setContentType("application/vnd.openxmlformats-officedocument.presentationml.presentation");
        }

		try {
			
			  InputStream inputStream= new FileInputStream(fullPath);
			  LOGGER.debug("fullPath " + fullPath);
			  OutputStream out = response.getPortletOutputStream();
			  byte buf[]=new byte[1024];
			  int len;
			  while((len=inputStream.read(buf))>0){
			  out.write(buf,0,len);
			  }
			  out.close();
			  inputStream.close();
			  LOGGER.debug("File is created...................................");
			
		} catch (IOException e) {
			LOGGER.debug("IOException occurred ::" + e);
		}
	}

/*Added for CI7 BRD14-02-12*/
	/**
	 * @param request 
	 * @param response 
	 * @param model 
	 * @throws Exception 
	 */
	@RequestMapping(params = "action=showContactPage")
	public void showContactPage(ActionRequest request, ActionResponse response, Model model) throws Exception{
		LOGGER.debug("------------------ RequestMapping of the showContactPage -----------------------");
		LOGGER.debug("friendlyURl is"+request.getParameter("friendlyURL"));
		response.sendRedirect(request.getParameter("friendlyURL"));
	}
	/*END*/
}