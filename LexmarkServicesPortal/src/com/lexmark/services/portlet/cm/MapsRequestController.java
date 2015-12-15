/**
 * Copyright@ 2012. This document has been created & written by
 * Wipro technologies for Lexmark and this is copyright to Lexmark
 *
 * File         	: MapsRequestController
 * Package     		: com.lexmark.services.portlet.cm
 * Creation Date 	: 
 *
 * Modification History:
 *
 * ---------------------------------------------------------------------
 * Author 			Date				Version  		Comments
 * ---------------------------------------------------------------------
 * wipro						 		1.0             Initial Version
 *
 */
package com.lexmark.services.portlet.cm;

import static com.lexmark.services.LexmarkSPConstants.ERROR_MESSAGE_BUNDLE;
//import static com.lexmark.services.LexmarkSPConstants.MESSAGE_BUNDLE_NAME;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
//import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
//import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
//import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.portlet.ResourceURL;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.fileupload.FileItem;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.amind.common.util.StringUtils;
import com.lexmark.constants.LexmarkConstants;
import com.lexmark.contract.AttachmentContract;
import com.lexmark.contract.CreateServiceRequestContract;
//import com.lexmark.contract.LocalizedSiebelLOVListContract;
import com.lexmark.domain.AccountContact;
import com.lexmark.domain.Attachment;
import com.lexmark.domain.FileObject;
//import com.lexmark.domain.ListOfValues;
import com.lexmark.domain.ServiceRequest;
import com.lexmark.exceptionimpl.checked.LGSCheckedException;
import com.lexmark.exceptionimpl.checked.business.LGSBusinessException;
import com.lexmark.exceptionimpl.runtime.LGSRuntimeException;
import com.lexmark.framework.logging.LEXLogger;
import com.lexmark.result.CreateServiceRequestResult;
//import com.lexmark.result.LocalizedSiebelLOVListResult;
import com.lexmark.service.api.AttachmentService;
import com.lexmark.service.api.CrmSessionHandle;
import com.lexmark.service.api.GlobalService;
import com.lexmark.service.api.ServiceRequestLocale;
import com.lexmark.service.api.SiebelCrmServiceException;
//import com.lexmark.service.impl.real.jdbc.InfrastructureException;
import com.lexmark.service.impl.real.util.AttachmentProperties;
import com.lexmark.services.LexmarkSPConstants;

import com.lexmark.services.api.cm.MapsRequestService;
import com.lexmark.services.form.FileUploadForm;

import com.lexmark.services.form.MapsRequestForm;

import com.lexmark.services.form.validator.FileUploadFormValidator;
import com.lexmark.services.form.validator.MapsRequestFormValidator;
import com.lexmark.services.portlet.BaseController;
import com.lexmark.services.portlet.common.CommonController;
import com.lexmark.services.util.ChangeMgmtConstant;
//import com.lexmark.services.util.ContractFactory;
import com.lexmark.services.util.ObjectDebugUtil;
import com.lexmark.services.util.PerformanceConstant;
import com.lexmark.services.util.PortalSessionUtil;
import com.lexmark.services.util.ServiceStatusErrorMessageUtil;
import com.lexmark.services.util.PerformanceUtil; //Added for performance logging
import com.lexmark.util.DateUtil;
//import com.lexmark.util.LOVComparator;
import com.lexmark.util.PropertiesMessageUtil;
import com.lexmark.util.StringUtil;
import com.liferay.portal.util.PortalUtil;

/**
 * @author wipro
 * @version 2.1
 *
 */

@Controller
@RequestMapping("VIEW")
public class MapsRequestController extends BaseController {

	@Autowired
	private MapsRequestService mapsRequestService;
	@Autowired
	private CommonController commonController;
	@Autowired
	private ServiceRequestLocale serviceRequestLocaleService;
	/**
	 * 
	 */
	@Autowired
	public GlobalService globalService;
	/**
	 * 
	 */
	@Autowired
	public AttachmentService attachmentService;
	/**
	 * 
	 */
	@Autowired
	public FileUploadFormValidator fileUploadFormValidator;
	
    /** templatePath */
	private String templatePath;
	 /** massTemplatePath */
	private String massTemplatePath;
	 /** hwMassTemplatePath */
	private String hwMassTemplatePath;
	/** listOfFileTypes */
	private String listOfFileTypes;
	/** attMaxCount */
	private String attMaxCount;
	/** chlTempMaxCount */
	private String chlTempMaxCount;

	private static final LEXLogger LOGGER = LEXLogger
			.getLEXLogger(MapsRequestController.class);
	private static Logger perfLogger = LogManager.getLogger("performance");
	private static String CLASS_NAME = "MapsRequestController.java" ;

	/** REQ_FILE_MAP */
	public static final String REQ_FILE_MAP = "fileMap";
	
	/** REQ_ACCOUNT_CONTACT */
	public static final String REQ_ACCOUNT_CONTACT = "accountContact";
	/** PARAM_FILE_NAME */
	public static final String PARAM_FILE_NAME = "fileName";
	/** PARAM_FILE_TYPE */
	public static final String PARAM_FILE_TYPE = "fileType";
	/** URL_ATTACHMENT */
	public static final String URL_ATTACHMENT = "attachmentURL";
	/** URL_CHL_TEMPLATE */
	public static final String URL_CHL_TEMPLATE = "chlTemplateURL";
	
	/** URL_DISPLAY_ATTACHMENT */
	public static final String URL_DISPLAY_ATTACHMENT = "displayAttachment";
	/** FILE_TYPE_CHL_TEMPLATE */
	public static final String FILE_TYPE_CHL_TEMPLATE = "CHLTemplate";
	/** FILE_TYPE_ATTACHMENT */
	public static final String FILE_TYPE_ATTACHMENT = "Attachment";
	/** FILE_TYPE_ATTACHMENT */
	public static final String FILE_TYPE_MASS_TEMPLATE = "massUploadTemplate";
	/** FILE_TYPE_ATTACHMENT */
	public static final String FILE_TYPE_HW_MASS_TEMPLATE = "hwMassUploadTemplate";
	/** REQ_FILE_MAP */
	public static final String MAPSREQUEST_FORM = "mapsRequestForm";
	/** FILE_UPLOAD_FORM */
	public static final String FILE_UPLOAD_FORM = "fileUploadForm";
	/** HARDWARE_SR */
	public static final String HARDWARE_SR = "Hardware SR";
	/** CONSUMABLES_SR */
	public static final String CONSUMABLES_SR = "Consumables SR";
	/** CANCELREQUEST */
	public static final String CANCELREQUEST = "Cancel Request";
	/** PAGETYPE */
	public static final String PAGETYPE= "pagetype";
	/**
	 * 
	 */
	public String CHANGE_ACCOUNT_LINK ="changeAccountLink";
	
	private static String[] addressInputFields={"maps_req_addressId","maps_req_addressName","maps_req_addressLine1","maps_req_addressLine2",
        "maps_req_addressCity","maps_req_addressState","maps_req_addressProvince",
        "maps_req_addressPostCode","maps_req_addressCountry","maps_req_addressSite","maps_req_addressBuilding","maps_req_addressFloor","maps_req_addressZone"};
	/**
	 * This method is used to bind the validator for form beans
	 * @param binder 
	 * @param locale 
	 */
	@InitBinder(value = { FILE_UPLOAD_FORM, MAPSREQUEST_FORM })
	protected void initBinder(WebDataBinder binder, Locale locale) {
		String METHOD_NAME="initBinder";
		LOGGER.enter(CLASS_NAME, METHOD_NAME);
		LOGGER.debug("Language is " + locale.getLanguage() + " country is "
				+ locale.getCountry() + " format is "
				+ DateUtil.getDateFormatByLang(locale.getLanguage()));
		DateFormat sdf = new SimpleDateFormat(
				DateUtil.getDateFormatByLang(locale.getLanguage()));
		sdf.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));

		if (binder.getTarget() instanceof MapsRequestForm) {
			LOGGER.debug("Setting MapsRequestForm validator");
			binder.setValidator(new MapsRequestFormValidator());
		}
		else if (binder.getTarget() instanceof FileUploadForm) {
			LOGGER.debug("Setting FileUploadForm validator");
			binder.setValidator(fileUploadFormValidator);
		}
		LOGGER.exit(CLASS_NAME, METHOD_NAME);

	}

	/**
	 * @param request 
	 * @param response 
	 * @param model 
	 * @return String 
	 * @throws Exception 
	 */
	@RequestMapping(params = "action=printMapsRequest")
	public String showPrintMapsRequest(RenderRequest request,
			RenderResponse response, Model model) throws Exception {
		String METHOD_NAME="showPrintMapsRequest";
		LOGGER.enter(CLASS_NAME, METHOD_NAME);
		LOGGER.debug("------------- Inside Show Print ---------");
		LOGGER.exit(CLASS_NAME, METHOD_NAME);
		return "changemanagement/mapsRequest/mapsRequestPrint";
	}

	/**
	 * @param request 
	 * @param response 
	 * @param model 
	 * @return String 
	 * @throws Exception 
	 */
	@RequestMapping(params = "action=emailMapsRequest")
	public String showEmailMapsRequest(RenderRequest request,
			RenderResponse response, Model model) throws Exception {
		String METHOD_NAME="showEmailMapsRequest";
		LOGGER.enter(CLASS_NAME, METHOD_NAME);
		LOGGER.debug("-------------showEmail started---------");
		
		LOGGER.exit(CLASS_NAME, METHOD_NAME);
		return "changemanagement/mapsRequest/mapsRequestEmail";
	}

	/**
	 * Default method for Maps Request portlet 
	 * populates the form, requester information and
	 * 
	 * @param model 
	 * @param request 
	 * @param response 
	 * @return String 
	 * @throws LGSCheckedException 
	 * @throws LGSRuntimeException 
	 */
	@RequestMapping
	public String viewMapsRequest(Model model, RenderRequest request,
			RenderResponse response) throws LGSCheckedException,
			LGSRuntimeException {
		String METHOD_NAME="viewMapsRequest";
		LOGGER.enter(CLASS_NAME, METHOD_NAME);
		LOGGER.debug("Inside MapsRequestController >> viewMapsRequest");

		// List of exceptions to be displayed to user
		List<String> exceptionList = new ArrayList<String>();
		MapsRequestForm mapsRequestForm = new MapsRequestForm();
		
		// Initializes the form for file upload
		FileUploadForm fileUploadForm = new FileUploadForm();
		AccountContact accContact = null;
		ResourceURL resURL1 = null;
		PortletSession session = request.getPortletSession();

		try {
			
			HttpServletRequest httpReq = PortalUtil
					.getOriginalServletRequest(PortalUtil
							.getHttpServletRequest(request));
			
			String srNumber = httpReq
					.getParameter(ChangeMgmtConstant.SERVICEREQNO);
			if (srNumber != null) {
				mapsRequestForm.setPrevSRNumber(srNumber);
			}
			
						
			// Requester information is populated from the Portlet session
			accContact = commonController.getContactInformation(request,
					response);
			// Initializes and populates the form for the Maps Request flow
			ServiceRequest serviceRequest = new ServiceRequest();
			serviceRequest.setRequestor(accContact);
			serviceRequest.setPrimaryContact(accContact);
			mapsRequestForm.setServiceRequest(serviceRequest);

			// Populated values for attachment validation
			mapsRequestForm.setAttMaxCount(attMaxCount);
			mapsRequestForm.setChlTempMaxCount(chlTempMaxCount);
			mapsRequestForm.setListOfFileTypes(listOfFileTypes);

			resURL1 = response.createResourceURL();
			resURL1.setResourceID(URL_DISPLAY_ATTACHMENT);

			// Removes the Attachment Map if any exists from previous flows
			session.removeAttribute(ChangeMgmtConstant.SESSION_FILE_MAP);

			
		} catch (LGSRuntimeException e) {
			LOGGER.error("LGSRuntimeException occurred ::: " + e.getMessage());
			exceptionList.add(e.getMessage());
		}
		
		if (resURL1 != null) {
			model.addAttribute(URL_CHL_TEMPLATE, resURL1.toString());
		}
		if (!exceptionList.isEmpty()) {
			model.addAttribute(ChangeMgmtConstant.LIST_EXCEPTION, exceptionList);
		}
		if (accContact != null) {
			model.addAttribute(REQ_ACCOUNT_CONTACT, accContact);
		}
		
		//This section is for adding address to comments if the request comes from fleet management
		HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));
		//checking for addressId if its present then get other values.
		//Other wise ignore....
		LOGGER.debug("address Id="+httpReq.getParameter(addressInputFields[0]));
		if(StringUtils.isNotBlank(httpReq.getParameter(addressInputFields[0]))){
			StringBuffer addressString=new StringBuffer();
			for(String field:addressInputFields){
				if(StringUtils.isNotBlank(httpReq.getParameter(field))){
					addressString.append(httpReq.getParameter(field));
					addressString.append(",");	
				}
				
			}
			addressString.deleteCharAt(addressString.length()-1);
			mapsRequestForm.setNotesOrComments(addressString.toString());
			LOGGER.debug("address String is "+addressString);
		}
		
		
		model.addAttribute(MAPSREQUEST_FORM, mapsRequestForm);
		model.addAttribute(FILE_UPLOAD_FORM, fileUploadForm);
		model.addAttribute("fleetManagementFlag","true");
		LOGGER.exit(CLASS_NAME, METHOD_NAME);
		return "changemanagement/mapsRequest/mapsRequestDetails";
	}

	/**
	 * The method is called when "Continue" button is clicked from mapsRequestDetails page
	 * 
	 * @param model 
	 * @param request 
	 * @param response 
	 * @param result 
	 * @param mapsRequestForm 
	 * @throws LGSCheckedException 
	 * @throws LGSRuntimeException 
	 */
	@ActionMapping(params = "action=confirmRequest")
	public void confirmRequest(Model model, ActionRequest request,
			ActionResponse response,
			@Valid @ModelAttribute(MAPSREQUEST_FORM) MapsRequestForm mapsRequestForm, BindingResult result)
			throws LGSCheckedException, LGSRuntimeException {
		String METHOD_NAME="confirmRequest";
		LOGGER.enter(CLASS_NAME, METHOD_NAME);
		LOGGER.debug("Inside MapsRequestController >> confirmRequest");
		PortletSession session = request.getPortletSession();
		

		@SuppressWarnings("unchecked")
		Map<String, FileObject> fileMap = (Map<String, FileObject>) session
				.getAttribute(ChangeMgmtConstant.SESSION_FILE_MAP);
		model.addAttribute(MAPSREQUEST_FORM, mapsRequestForm);
		request.setAttribute(REQ_FILE_MAP, fileMap);
		mapsRequestForm.refreshSubmitToken(request);
		FileUploadForm fileUploadForm = new FileUploadForm();
		List<String> exceptionList = new ArrayList<String>();
		
		//LBS
		String fleet=request.getParameter("fleet");
		if(fleet != null && fleet.equalsIgnoreCase("true")){
			request.setAttribute("fleet", "true");
			LOGGER.debug("Settinf fleet to true");
		}
		else{
			request.setAttribute("fleet", "false");
			LOGGER.debug("Settinf fleet to false");
		}
		if(result.hasErrors()){
			LOGGER.debug("Contains Validation Errors");
			fileUploadForm.setFileMap(fileMap);
			if (fileMap != null) {
				fileUploadForm.setFileCount(fileMap.size());
			}
		
		
			if (!exceptionList.isEmpty()) {
				model.addAttribute(ChangeMgmtConstant.LIST_EXCEPTION, exceptionList);
			}
			model.addAttribute(MAPSREQUEST_FORM, mapsRequestForm);
			model.addAttribute(FILE_UPLOAD_FORM, fileUploadForm);
			model.addAttribute("fleetManagementFlag","true");

			commonController.getContactInformation(request, response);
			
						
			response.setRenderParameter("action", "backToEdit");
			
		}
		
				response.setRenderParameter("action", "showMapsRequestReview");
			
		LOGGER.exit(CLASS_NAME, METHOD_NAME);		
	}

	

	/**
	 * The method is called from confirmRequest method to render the
	 * confirmation for MapsRequest
	 * 
	 * @param model 
	 * @param request 
	 * @param response 
	 * @return String 
	 * @throws LGSCheckedException 
	 * @throws LGSRuntimeException 
	 */
	@RequestMapping(params = "action=showMapsRequestReview")
	public String showMapsRequestReview(Model model, RenderRequest request,
			RenderResponse response) throws LGSCheckedException,
			LGSRuntimeException {
		String METHOD_NAME="showMapsRequestReview";
		LOGGER.enter(CLASS_NAME, METHOD_NAME);
		LOGGER.debug("Inside MapsRequestController >> confirmRequest render method for MapsRequest");

		
		LOGGER.debug("request param is "
				+ request.getParameter(ChangeMgmtConstant.TIMEZNOFFSET));
		String timezoneOffset = request
				.getParameter(ChangeMgmtConstant.TIMEZNOFFSET);

		if (!StringUtil.isEmpty(timezoneOffset)) {
			model.addAttribute(LexmarkConstants.TIMEZONE_OFFSET,
					Float.valueOf(timezoneOffset));
		}
		ResourceURL resURL = response.createResourceURL();
		resURL.setResourceID(URL_DISPLAY_ATTACHMENT);
		model.addAttribute(URL_ATTACHMENT, resURL.toString());
		model.addAttribute("fleetManagementFlag","true");
		LOGGER.exit(CLASS_NAME, METHOD_NAME);
		return "changemanagement/mapsRequest/mapsRequestReview";
	}

	/**
	 * The method is called when "Back" button is clicked from mapsRequestReview page
	 * 
	 * @param model 
	 * @param request 
	 * @param response 
	 * @param mapsRequestForm 
	 * @throws LGSCheckedException 
	 * @throws LGSRuntimeException 
	 */
	@ActionMapping(params = "action=backToEdit")
	public void backToEdit(Model model, ActionRequest request,
			ActionResponse response,
			@ModelAttribute(MAPSREQUEST_FORM) MapsRequestForm mapsRequestForm)
			throws LGSCheckedException, LGSRuntimeException {
		String METHOD_NAME="backToEdit";
		LOGGER.enter(CLASS_NAME, METHOD_NAME);
		LOGGER.debug("Inside MapsRequestController >> backToEdit  method");
		FileUploadForm fileUploadForm = new FileUploadForm();
		PortletSession session = request.getPortletSession();
		
		List<String> exceptionList = new ArrayList<String>();
		//LBS
		String fleet=request.getParameter("fleet");
		if(fleet != null && fleet.equalsIgnoreCase("true")){
			request.setAttribute("fleet", "true");
			LOGGER.debug("Settinf fleet to true");
		}
		else{
			request.setAttribute("fleet", "false");
			LOGGER.debug("Settinf fleet to false");
		}
		@SuppressWarnings("unchecked")
		Map<String, FileObject> fileMap = (Map<String, FileObject>) session
				.getAttribute(ChangeMgmtConstant.SESSION_FILE_MAP);
		fileUploadForm.setFileMap(fileMap);
		if (fileMap != null) {
			fileUploadForm.setFileCount(fileMap.size());
		}

		

		if (!exceptionList.isEmpty()) {
			model.addAttribute(ChangeMgmtConstant.LIST_EXCEPTION, exceptionList);
		}
		model.addAttribute(MAPSREQUEST_FORM, mapsRequestForm);
		model.addAttribute(FILE_UPLOAD_FORM, fileUploadForm);

		commonController.getContactInformation(request, response);

		// Forward to the respective render method
		response.setRenderParameter("action", "backToEdit");
		LOGGER.exit(CLASS_NAME, METHOD_NAME);

	}

	

	/**
	 * The method is called from backToEdit method to render the confirmation
	 * for MapsRequest
	 * 
	 * @param model 
	 * @param request 
	 * @param response 
	 * @return String 
	 * @throws LGSCheckedException 
	 * @throws LGSRuntimeException 
	 */
	@RequestMapping(params = "action=backToEdit")
	public String backToEdit(Model model, RenderRequest request,
			RenderResponse response) throws LGSCheckedException,
			LGSRuntimeException {
		String METHOD_NAME="backToEdit";
		LOGGER.enter(CLASS_NAME, METHOD_NAME);
		LOGGER.debug("Inside MapsRequestController >> backToEdit render method for MapsRequest");
		
		ResourceURL resURL1 = response.createResourceURL();
		resURL1.setResourceID(URL_DISPLAY_ATTACHMENT);
		model.addAttribute(URL_CHL_TEMPLATE, resURL1.toString());
		model.addAttribute("fleetManagementFlag","true");

		LOGGER.exit(CLASS_NAME, METHOD_NAME);
		return "changemanagement/mapsRequest/mapsRequestDetails";
	}

	/**
	 * The method is called when "Attach" button is clicked from mapsRequestDetails page
	 * 
	 * @param model 
	 * @param request  
	 * @param response 
	 * @param mapsRequestForm  
	 * @param fileUploadForm 
	 * @param result 
	 * @throws LGSCheckedException 
	 * @throws LGSRuntimeException 
	 */
	@ActionMapping(params = "action=attachDocument")
	public void attachDocument(
			Model model,
			ActionRequest request,
			ActionResponse response,
			@ModelAttribute(MAPSREQUEST_FORM) MapsRequestForm mapsRequestForm,
			@ModelAttribute(FILE_UPLOAD_FORM) @Valid FileUploadForm fileUploadForm,
			BindingResult result) throws LGSCheckedException,
			LGSRuntimeException {
		String METHOD_NAME="attachDocument";
		LOGGER.enter(CLASS_NAME, METHOD_NAME);
		LOGGER.debug("Inside MapsRequestController >> attachDocument method");
		PortletSession session = request.getPortletSession();

		@SuppressWarnings("unchecked")
		Map<String, FileObject> fileMap = (Map<String, FileObject>) session
				.getAttribute(ChangeMgmtConstant.SESSION_FILE_MAP);

		if (result.hasErrors()) {
			LOGGER.debug("Contains Validation Errors");
			fileUploadForm.setFileMap(fileMap);
			session.setAttribute(ChangeMgmtConstant.SESSION_FILE_MAP, fileMap);
			request.setAttribute(FILE_UPLOAD_FORM, fileUploadForm);
			model.addAttribute(FILE_UPLOAD_FORM, fileUploadForm);
			response.setRenderParameter("action", "attachDocument");

		} else {
			List<String> exceptionList = new ArrayList<String>();
			try {
				// List of exceptions to be displayed to user
				if (fileMap == null) {
					fileMap = new LinkedHashMap<String, FileObject>();
				}
				double fileSize = fileUploadForm.getFileData().getSize();
				fileSize = fileSize / 1024;
				LOGGER.debug("File Size is " + fileSize);
				
				int fileSizeDis=(int) fileUploadForm.getFileData().getSize();
				LOGGER.debug("File Size for vALIDATION IS " + fileSizeDis);
				
				boolean fileSizeLimit=false;
				if(fileSizeDis>1572864){
					LOGGER.debug("fileSizeLimit----------> "+fileSize);
					fileSizeLimit=true;
					model.addAttribute("fileSizeError","error");
				}
				FileObject fileObject = new FileObject();
				FileItem fileItem = fileUploadForm.getFileData().getFileItem();
				String fileName = fileItem.getName();
				int index = fileName.indexOf("\\");

				if (index != 0) {
					int index1 = fileName.lastIndexOf("\\");
					fileName = fileName.substring(index1 + 1);
				}
				String diplayFileName = fileName.toString();
				fileObject.setDisplayFileName(diplayFileName);
				
				//Added to prevent upload of duplicate attachment
				Set keys = fileMap.keySet();
				Iterator i = keys.iterator();
				while (i.hasNext())
				{
				    String key = (String) i.next();
				    FileObject value = (FileObject) fileMap.get(key);
				     if(value.getDisplayFileName().equalsIgnoreCase(diplayFileName)){
				    	 fileUploadForm.setFileMap(fileMap);
						 session.setAttribute(ChangeMgmtConstant.SESSION_FILE_MAP, fileMap);
						 request.setAttribute(FILE_UPLOAD_FORM, fileUploadForm);
						 model.addAttribute(FILE_UPLOAD_FORM, fileUploadForm);
						 throw new LGSRuntimeException(PropertiesMessageUtil.getPropertyMessage(LexmarkSPConstants.MESSAGE_BUNDLE_NAME, "validation.fileUpload.attachmentDuplicate.errorMsg", request.getLocale()));
				     }
				       
				}
				if(!fileSizeLimit){
					fileName = setTimestampInAttachment(fileName);

					fileObject.setFileName(fileName);
					fileObject.setFileSize(String.valueOf(fileSize));
					fileObject.setFileSizeInBytes(String.valueOf(fileUploadForm
							.getFileData().getSize()));

					/** Code for file transfer begins */
					AttachmentProperties fileProperties = new AttachmentProperties(
							ChangeMgmtConstant.ATTACHMENT_REQUEST_TYPE);
					LOGGER.debug("Starting File Transfer"
							+ fileProperties.getFileUploadDestination() + fileName);
					fileItem.write(new File(fileProperties
							.getFileUploadDestination() + fileName));
					LOGGER.debug("File Transfer is complete ");
					/** Code for file upload ends */

					fileMap.put(fileName, fileObject);
					fileUploadForm.setFileMap(fileMap);
					fileUploadForm.setFileCount(fileMap.size());
					session.setAttribute(ChangeMgmtConstant.SESSION_FILE_MAP,
							fileMap);
				}else{
					LOGGER.debug("Error in Attaching File ");
					model.addAttribute("errorFileMsg","error");
				}
				

			} catch (LGSRuntimeException e) {
				LOGGER.error("LGSRuntimeException occurred ::: "
						+ e.getMessage());
				exceptionList.add(e.getMessage());
			} catch (Exception e) {
				LOGGER.error("Exception occured : Could not complete File Transfer.");
				exceptionList.add(e.getMessage());
			}

			if (!exceptionList.isEmpty()) {
				model.addAttribute(ChangeMgmtConstant.LIST_EXCEPTION,
						exceptionList);
			}
			model.addAttribute(FILE_UPLOAD_FORM, fileUploadForm);
			response.setRenderParameter("action", "attachDocument");
		}
		LOGGER.exit(CLASS_NAME, METHOD_NAME);

	}

	/**
	 * The method is called from attachDocument method to render the target page
	 * in the iframe
	 * 
	 * @param model 
	 * @param request  
	 * @param response 
	 * @return String 
	 * @throws LGSCheckedException 
	 * @throws LGSRuntimeException 
	 */
	@RequestMapping(params = "action=attachDocument")
	public String attachDocument(Model model, RenderRequest request,
			RenderResponse response) throws LGSCheckedException,
			LGSRuntimeException {
		String METHOD_NAME= "attachDocument";
		LOGGER.enter(CLASS_NAME, METHOD_NAME);
		LOGGER.debug("Inside MapsRequestController >> attachDocument render method");
		model.addAttribute("source", "attachment");
		LOGGER.exit(CLASS_NAME, METHOD_NAME);
		return "changemanagement/mapsRequest/mapsRequestDetails";
	}

	/**
	 * The method is called when Remove icon is clicked for any attachment from
	 * mapsRequestDetails page
	 * 
	 * @param model 
	 * @param request  
	 * @param response  
	 * @param mapsRequestForm 
	 * @param fileUploadForm 
	 * @param fileName 
	 * @throws LGSCheckedException 
	 * @throws LGSRuntimeException 
	 */
	@ActionMapping(params = "action=removeDocument")
	public void removeDocument(Model model, ActionRequest request,
			ActionResponse response,
			@ModelAttribute(MAPSREQUEST_FORM) MapsRequestForm mapsRequestForm,
			@ModelAttribute(FILE_UPLOAD_FORM) FileUploadForm fileUploadForm,
			@RequestParam(value = PARAM_FILE_NAME) String fileName)
			throws LGSCheckedException, LGSRuntimeException {
		String METHOD_NAME="removeDocument";
		LOGGER.enter(CLASS_NAME, METHOD_NAME);
		LOGGER.debug("Inside MapsRequestController >> removeDocument method");
		PortletSession session = request.getPortletSession();
		List<String> exceptionList = new ArrayList<String>();
		try {
			@SuppressWarnings("unchecked")
			Map<String, FileObject> fileMap = (Map<String, FileObject>) session
					.getAttribute(ChangeMgmtConstant.SESSION_FILE_MAP);

			if (fileMap == null) {
				throw new LGSRuntimeException("There is no file to be removed.");
			}

			fileMap.remove(fileName);
			AttachmentProperties fileProperties = new AttachmentProperties(
					ChangeMgmtConstant.ATTACHMENT_REQUEST_TYPE);
			File removableFile = new File(
					fileProperties.getFileUploadDestination() + fileName);
			boolean delStatus = removableFile.delete();

			if (delStatus == false) {
				throw new LGSRuntimeException(
						"The attachment could not be removed.");
			}

			if (fileMap.isEmpty()) {
				fileUploadForm.setFileMap(null);
				fileUploadForm.setFileCount(0);
				session.removeAttribute(ChangeMgmtConstant.SESSION_FILE_MAP);
			} else {
				fileUploadForm.setFileMap(fileMap);
				fileUploadForm.setFileCount(fileMap.size());

			}
		} catch (LGSRuntimeException e) {
			LOGGER.error("LGSRuntimeException occurred ::: " + e.getMessage());
			exceptionList.add(e.getMessage());
		}

		if (!exceptionList.isEmpty()) {
			model.addAttribute(ChangeMgmtConstant.LIST_EXCEPTION, exceptionList);
		}
		model.addAttribute(FILE_UPLOAD_FORM, fileUploadForm);
		response.setRenderParameter("action", "removeDocument");
		LOGGER.exit(CLASS_NAME, METHOD_NAME);

	}

	/**
	 * The method is called from removeDocument method to render the target page
	 * in the iframe
	 * 
	 * @param model 
	 * @param request 
	 * @param response 
	 * @return String 
	 * @throws LGSRuntimeException 
	 * @throws LGSCheckedException 
	 */
	@RequestMapping(params = "action=removeDocument")
	public String removeDocument(Model model, RenderRequest request,
			RenderResponse response) throws LGSCheckedException,
			LGSRuntimeException {
		String METHOD_NAME="removeDocument";
		LOGGER.enter(CLASS_NAME, METHOD_NAME);
		LOGGER.debug("Inside MapsRequestController >> removeDocument render method");
		model.addAttribute("source", "attachment");
		LOGGER.exit(CLASS_NAME, METHOD_NAME);
		return "changemanagement/mapsRequest/mapsRequestDetails";
	}

	/**
	 * The method is called when "Submit" button is clicked from mapsRequestReview page The create SR service call is executed as well Amind
	 * call for attachments is triggered from this method
	 * 
	 * @param model 
	 * @param request 
	 * @param response 
	 * @param mapsRequestForm 
	 * @throws LGSCheckedException 
	 * @throws LGSRuntimeException 
	 */
	@RequestMapping(params = "action=createCMRequest")
	public void createCMRequest(Model model, ActionRequest request,
			ActionResponse response,
			@ModelAttribute(MAPSREQUEST_FORM) MapsRequestForm mapsRequestForm)
			throws LGSRuntimeException, LGSCheckedException {
		String METHOD_NAME="createCMRequest";
		LOGGER.enter(CLASS_NAME, METHOD_NAME);
		
		
		/**************** This section has been added to prevent resubmit by browser refresh ***********/
		if (PortalSessionUtil.isDuplicatedSubmit(request, mapsRequestForm)) {
			LOGGER.debug("MapsRequestController duplicated submit, do nothing!");
			model.addAttribute("error", ServiceStatusErrorMessageUtil
					.getErrorMessage(ERROR_MESSAGE_BUNDLE,
							"exception.serviceRequest.duplicateSubmission",
							null, request.getLocale()));

			response.setRenderParameter("action", "createCMRequestSuccess");
			model.addAttribute(mapsRequestForm);
			
			
		}
		/**************** Section end to prevent resubmit by browser refresh ***********/
		else {
			LOGGER.debug("Inside MapsRequestController >> createCMRequest method");
			List<String> exceptionList = new ArrayList<String>();
			ServiceRequest serviceRequest = mapsRequestForm.getServiceRequest();

			serviceRequest.setServiceRequestNumber(mapsRequestForm
					.getPrevSRNumber());
			serviceRequest.setNotes(mapsRequestForm.getNotesOrComments());

			
			PortletSession session = request.getPortletSession();
			@SuppressWarnings("unchecked")
			Map<String, FileObject> fileMap = (Map<String, FileObject>) session
					.getAttribute(ChangeMgmtConstant.SESSION_FILE_MAP);
			CrmSessionHandle crmSessionHandle = globalService
					.initCrmSessionHandle(PortalSessionUtil
							.getSiebelCrmSessionHandle(request));

			try {
				CreateServiceRequestContract contract = commonController
						.getServiceReqContract(mapsRequestForm, request);
				LOGGER.debug("-------------- getting account information in do webservice call for contacts --------------");
				Map<String, String> accDetails = (Map<String, String>) session
						.getAttribute(ChangeMgmtConstant.ACNTCURRDETAILS,
								PortletSession.APPLICATION_SCOPE);

				if (accDetails != null) {
					LOGGER.debug("Account id ::::   "
							+ accDetails.get("accountId"));
					LOGGER.debug("Account Name ::::   "
							+ accDetails.get("accountName"));
					LOGGER.debug("Account Organization ::::   "
							+ accDetails.get("accountOrganization"));
					LOGGER.debug("Agreement Id::::   "
							+ accDetails.get("agreementId"));
					LOGGER.debug("Agreement Name ::::   "
							+ accDetails.get("agreementName"));
					LOGGER.debug("Country ::::   " + accDetails.get("country"));
				} else {
					LOGGER.debug("acc details map is null, so creating an empty map");
					accDetails = new HashMap<String, String>();// Go for
																// creation of
																// an empty map
				}
				
				LOGGER.info("mapsRequestForm.getRequestedFrom() :: "+ mapsRequestForm.getRequestedFrom());
				if(mapsRequestForm.getRequestedFrom()!=null && mapsRequestForm.getRequestedFrom()!= ""){
					contract.setRequestedFrom(mapsRequestForm.getRequestedFrom());
				}
				
				LOGGER.info("start printing lex logger");
				ObjectDebugUtil.printObjectContent(contract,LOGGER);
				LOGGER.info("end printing lex loggger");

				String userType = PortalSessionUtil.getUserType(session);
				LOGGER.debug("User Type ---->" + userType);
				long timeBeforeCall=System.currentTimeMillis();
				CreateServiceRequestResult result = mapsRequestService
						.createCMRequest(contract, accDetails);
				long timeAfterCall=System.currentTimeMillis();
				
				LOGGER.info("start printing lex logger");
				
				//PerformanceUtil.calcTime(perfLogger, PerformanceConstant.MANAGECHLOTHERS_MSG_MANAGECHLOTHERSSERVICE, timeBeforeCall,timeAfterCall, PerformanceConstant.SYSTEM_WEBMETHODS,contract);
				
				LOGGER.logTime(" SR NUMBER IS "+result.getServiceRequestNumber());
				LOGGER.info("end printing lex loggger");
				if (result != null) {
					LOGGER.debug("After create service request call::: "
							+ result.getServiceRequestNumber());
					mapsRequestForm.getServiceRequest().setServiceRequestNumber(
							result.getServiceRequestNumber());
				}

				/** Code for File Upload begins */
				LOGGER.debug("File upload process begins");
				if (fileMap != null) {

					List<Attachment> attachmentList = new ArrayList<Attachment>();
					Set<String> fileNames = fileMap.keySet();
					Iterator<String> iterator = fileNames.iterator();
					Attachment file=null;
					while (iterator.hasNext()) {
						String fileName = iterator.next();
						FileObject fileDetails = fileMap.get(fileName);
						file = new Attachment();
						file.setVisibility(userType);
						file.setAttachmentName(fileDetails.getFileName());
						file.setSize(Integer.parseInt(fileDetails
								.getFileSizeInBytes()));
						LOGGER.debug("File name ::: "
								+ fileDetails.getFileName());
						LOGGER.debug("File size ::: "
								+ fileDetails.getFileSize());
						attachmentList.add(file);
					}

					AttachmentContract atchmntContract = new AttachmentContract();
					atchmntContract.setSessionHandle(crmSessionHandle);

					LOGGER.debug("----------------------------- activityid we are sending ----------------------");
					if (result != null) {
						atchmntContract.setIdentifier(result
								.getServiceRequestRowId());
					}

					atchmntContract
							.setRequestType(ChangeMgmtConstant.ATTACHMENT_REQUEST_TYPE);// For
																						// service
																						// request
																						// you
																						// have
																						// to
																						// send
					atchmntContract.setAttachments(attachmentList);
					LOGGER.info("start printing lex logger");
					ObjectDebugUtil.printObjectContent(atchmntContract,LOGGER);
					LOGGER.info("end printing lex loggger");
					
					LOGGER.debug("-------------------------- Calling attachment service ----------------------");
					long timeBeforeAttachmentCall=System.currentTimeMillis();
					attachmentService.uploadAttachments(atchmntContract);
					long timeAfterAttachmentCall=System.currentTimeMillis();
					LOGGER.info("start printing lex logger");
					
					PerformanceUtil.calcTime(perfLogger, PerformanceConstant.MSG_ATTACHMENT, timeBeforeAttachmentCall,timeAfterAttachmentCall, PerformanceConstant.SYSTEM_AMIND,contract);
					
					LOGGER.info("end printing lex loggger");
					LOGGER.debug("-------------------------- After Calling attachment service ----------------------");
				}

				LOGGER.debug("File upload process ends");

			} catch (SiebelCrmServiceException e) {
				LOGGER.error("SiebelCrmServiceException occured :"
						+ e.getMessage());
				exceptionList.add(e.getMessage());
			} catch (LGSBusinessException e) {
				LOGGER.error("LGSBusinessException occured :" + e.getMessage());
				if(e.getMessage().trim().equalsIgnoreCase("Error creating service request - SR Number is null")){
					exceptionList.add(PropertiesMessageUtil.getPropertyMessage(LexmarkSPConstants.MESSAGE_BUNDLE_NAME,"requestInfo.manageCHL.errorMsg1", request.getLocale()));	
				}
				else if(e.getMessage().trim().equalsIgnoreCase("Error creating service request - SR Number is blank")){
					exceptionList.add(PropertiesMessageUtil.getPropertyMessage(LexmarkSPConstants.MESSAGE_BUNDLE_NAME,"requestInfo.manageCHL.errorMsg2", request.getLocale()));	
				}
				else if(e.getMessage().trim().equalsIgnoreCase("Error creating service request - SR Row id is null")){
					exceptionList.add(PropertiesMessageUtil.getPropertyMessage(LexmarkSPConstants.MESSAGE_BUNDLE_NAME,"requestInfo.manageCHL.errorMsg3", request.getLocale()));	
				}
				else if(e.getMessage().trim().equalsIgnoreCase("Error creating service request - SR Row Id not received")){
					exceptionList.add(PropertiesMessageUtil.getPropertyMessage(LexmarkSPConstants.MESSAGE_BUNDLE_NAME,"requestInfo.manageCHL.errorMsg4", request.getLocale()));	
				}
				else if(e.getMessage().trim().equalsIgnoreCase("Could not create SR for the Change Management Request.")){
					exceptionList.add(PropertiesMessageUtil.getPropertyMessage(LexmarkSPConstants.MESSAGE_BUNDLE_NAME,"requestInfo.manageCHL.errorMsg5", request.getLocale()));	
				}
				else{
				exceptionList.add(e.getMessage());
				}
			} catch (Exception e) {
				LOGGER.error("Exception occured :" + e.getMessage());
				exceptionList.add(e.getMessage());
			} finally {
				globalService.releaseSessionHandle(crmSessionHandle);
			}
			/** Code for File Upload ends */

			request.setAttribute(REQ_FILE_MAP, fileMap);
			model.addAttribute(MAPSREQUEST_FORM, mapsRequestForm);
			model.addAttribute("fleetManagementFlag","true");
			
			if (exceptionList.isEmpty()) {
				response.setRenderParameter("action", "createCMRequestSuccess");

				// this is to enable re-submit of SR form on submit/draft
				// exception
				Long tokenInSession = (Long) session.getAttribute(
						LexmarkConstants.SUBMIT_TOKEN,
						PortletSession.PORTLET_SCOPE);
				mapsRequestForm.setSubmitToken(tokenInSession);
			} else {
				model.addAttribute(ChangeMgmtConstant.LIST_EXCEPTION,
						exceptionList);
				response.setRenderParameter("action", "createCMRequestError");
			}
		}
		LOGGER.exit(CLASS_NAME, METHOD_NAME);

	}

	/**
	 * The method is called from createCMRequest method to render the common
	 * summary page for MapsRequest
	 * 
	 * @param model 
	 * @param request 
	 * @param response 
	 * @return String 
	 */
	@RequestMapping(params = "action=createCMRequestSuccess")
	public String createCMRequestSuccess(Model model, RenderRequest request,
			RenderResponse response) {
		String METHOD_NAME="createCMRequestSuccess";
		LOGGER.enter(CLASS_NAME, METHOD_NAME);
		LOGGER.debug("In createCMRequestSuccess method "
				+ request.getParameter(LexmarkConstants.TIMEZONE_OFFSET));
		
		LOGGER.exit(CLASS_NAME, METHOD_NAME);
		return "changemanagement/mapsRequest/mapsRequestSummary";
	}

	/**
	 * The method is called from createCMRequest method to render the common
	 * summary page for MapsRequest
	 * 
	 * @param model 
	 * @param request 
	 * @param response 
	 * @return String 
	 */
	@RequestMapping(params = "action=createCMRequestError")
	public String createCMRequestError(Model model, RenderRequest request,
			RenderResponse response) {
		String METHOD_NAME="createCMRequestError";
		LOGGER.enter(CLASS_NAME, METHOD_NAME);
		LOGGER.debug("Inside MapsRequestController >> createCMRequest render method");
		

		
		ResourceURL resURL = response.createResourceURL();
		resURL.setResourceID(URL_DISPLAY_ATTACHMENT);
		model.addAttribute(URL_ATTACHMENT, resURL.toString());
		LOGGER.exit(CLASS_NAME, METHOD_NAME);
		return "changemanagement/mapsRequest/mapsRequestReview";
	}



	/**
	 * @param request 
	 * @param response 
	 * @param model 
	 * @throws LGSRuntimeException 
	 */
	@ResourceMapping(value = "displayAttachment")
	public void displayAttachment(ResourceRequest request,
			ResourceResponse response, Model model) throws LGSRuntimeException {
		String METHOD_NAME="displayAttachment";
		LOGGER.enter(CLASS_NAME, METHOD_NAME);
		LOGGER.debug("Inside MapsRequestController >> displayAttachment...");
		String fileType = request.getParameter(PARAM_FILE_TYPE);
		String fileName = request.getParameter(PARAM_FILE_NAME);

		if (FILE_TYPE_CHL_TEMPLATE.equalsIgnoreCase(fileType)) {

			LOGGER.debug("templatePath..." + templatePath);
			int index = this.templatePath.indexOf("/");
			String fileName1 = "";
			if (index != -1) {
				int index1 = this.templatePath.lastIndexOf("/");
				fileName1 = this.templatePath.substring(index1 + 1);
				LOGGER.debug("fileName value " + fileName1);
			}
			openAttachment(request, response, fileName1, templatePath);
		} else if (FILE_TYPE_ATTACHMENT.equalsIgnoreCase(fileType)) {

			AttachmentProperties fileProperties = new AttachmentProperties(
					ChangeMgmtConstant.ATTACHMENT_REQUEST_TYPE);
			LOGGER.debug("Starting File Transfer"
					+ fileProperties.getFileUploadDestination() + fileName);
			openAttachment(request, response, fileName,
					fileProperties.getFileUploadDestination() + fileName);
		}else if (FILE_TYPE_MASS_TEMPLATE.equalsIgnoreCase(fileType)) {

			LOGGER.debug("mass upload templatePath..." + massTemplatePath);
			int index = this.massTemplatePath.indexOf("/");
			String fileName1 = "";
			if (index != -1) {
				int index1 = this.massTemplatePath.lastIndexOf("/");
				fileName1 = this.massTemplatePath.substring(index1 + 1);
				LOGGER.debug("fileName value " + fileName1);
			}
			openAttachment(request, response, fileName1, massTemplatePath);
		}else if (FILE_TYPE_HW_MASS_TEMPLATE.equalsIgnoreCase(fileType)) {

			LOGGER.debug("harware mass upload templatePath..." + hwMassTemplatePath);
			int index = this.hwMassTemplatePath.indexOf("/");
			String fileName1 = "";
			if (index != -1) {
				int index1 = this.hwMassTemplatePath.lastIndexOf("/");
				fileName1 = this.hwMassTemplatePath.substring(index1 + 1);
				LOGGER.debug("fileName value " + fileName1);
			}
			openAttachment(request, response, fileName1, hwMassTemplatePath);
		}
		LOGGER.debug("End of retrieveCHLTemplate...");
		LOGGER.exit(CLASS_NAME, METHOD_NAME);
	}
/**
 * 
 * @param request 
 * @param response 
 * @param fileName 
 * @param fullPath 
 */
	private void openAttachment(ResourceRequest request,
			ResourceResponse response, String fileName, String fullPath) {
		String METHOD_NAME="openAttachment";
		LOGGER.enter(CLASS_NAME, METHOD_NAME);
		HttpServletRequest httpReq = PortalUtil
				.getOriginalServletRequest(PortalUtil
						.getHttpServletRequest(request));
		String userAgent = httpReq.getHeader("User-Agent");
		if (userAgent.contains("MSIE 7.0")) {
			fileName = fileName.replace(" ", "%20");
		}
		response.setProperty("Content-disposition", "attachment; filename=\""
				+ fileName + "\"");

		LOGGER.debug("fileName " + fileName + " fullPath " + fullPath);
		if (fileName.indexOf("csv") > 0) {
			response.setContentType("application/vnd.ms-excel");
		} else if (fileName.indexOf("pdf") > 0) {
			response.setContentType("application/pdf");
		} else if (fileName.indexOf("xls") > 0) {
			response.setContentType("application/vnd.ms-excel");
		} else if (fileName.indexOf("doc") > 0) {
			response.setContentType("application/msword");
		} else if (fileName.indexOf("zip") > 0) {
			response.setContentType("application/zip");
		} else if (fileName.indexOf("xlsx") > 0) {
			response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		} else if (fileName.indexOf("docx") > 0) {
			response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
		} else if (fileName.indexOf("ppt") > 0) {
			response.setContentType("application/vnd.ms-powerpoint");
		} else if (fileName.indexOf("pptx") > 0) {
			response.setContentType("application/vnd.openxmlformats-officedocument.presentationml.presentation");
		}

		try {
			LOGGER.debug("fullPath " + fullPath);
			InputStream inputStream = new FileInputStream(fullPath);
			OutputStream out = response.getPortletOutputStream();
			byte buf[] = new byte[1024];
			int len=0;
			while ((len = inputStream.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
			out.close();
			inputStream.close();
			LOGGER.debug("File is created...................................");

		} catch (IOException e) {
			LOGGER.error("IOException occurred ::" + e);
		}
		LOGGER.exit(CLASS_NAME, METHOD_NAME);
	}

	
	
/**
 * 
 * @param fName 
 * @return String 
 * @throws LGSBusinessException 
 */
	private String setTimestampInAttachment(String fName)
			throws LGSBusinessException {
		String METHOD_NAME="setTimestampInAttachment";
		LOGGER.enter(CLASS_NAME, METHOD_NAME);
		LOGGER.debug("Inside setTimestampInAttachment method start ");
		int index = fName.lastIndexOf(".");
		String fExtension = fName.substring(index);
		fName = fName.substring(0, index);
		String fNameFinal = fName + "_" + System.currentTimeMillis()
				+ fExtension;
		LOGGER.debug("Inside setTimestampInAttachment method end ");
		LOGGER.exit(CLASS_NAME, METHOD_NAME);
		return fNameFinal;
	}
	
	
	/**
	 * @param request 
	 * @param response 
	 * @param model 
	 * @throws Exception 
	 */
	@RequestMapping(params = "action=showMapsRequestPage")
	public void showMapsRequestPage(ActionRequest request, ActionResponse response, Model model) throws Exception{
		LOGGER.debug("------------------ RequestMapping of the showMapsRequestPage -----------------------");
		LOGGER.debug("friendlyURl is"+request.getParameter("friendlyURL"));
		response.sendRedirect(request.getParameter("friendlyURL"));
	}
/**	
 * 
 * @return String 
 */

	public String getTemplatePath() {
		return templatePath;
	}
/**
 *  
 * @param templatePath 
 */
	public void setTemplatePath(String templatePath) {
		this.templatePath = templatePath;
	}
/**
 *  
 * @return String 
 */
	public String getListOfFileTypes() {
		return listOfFileTypes;
	}
/**
 * 
 * @param listOfFileTypes 
 */
	public void setListOfFileTypes(String listOfFileTypes) {
		this.listOfFileTypes = listOfFileTypes;
	}
/**
 * 
 * @return String 
 */
	public String getAttMaxCount() {
		return attMaxCount;
	}
/**
 * 
 * @param attMaxCount 
 */
	public void setAttMaxCount(String attMaxCount) {
		this.attMaxCount = attMaxCount;
	}
/**
 * 
 * @return String 
 */
	public String getChlTempMaxCount() {
		return chlTempMaxCount;
	}
/**
 * 
 * @param chlTempMaxCount 
 */
	public void setChlTempMaxCount(String chlTempMaxCount) {
		this.chlTempMaxCount = chlTempMaxCount;
	}
/**
 * 
 * @return String 
 */
	public String getMassTemplatePath() {
		return massTemplatePath;
	}
/**
 * 
 * @param massTemplatePath 
 */
	public void setMassTemplatePath(String massTemplatePath) {
		this.massTemplatePath = massTemplatePath;
	}
/**
 * 
 * @return String 
 */
	public String getHwMassTemplatePath() {
		return hwMassTemplatePath;
	}
/**
 * 
 * @param hwMassTemplatePath 
 */
	public void setHwMassTemplatePath(String hwMassTemplatePath) {
		this.hwMassTemplatePath = hwMassTemplatePath;
	}
	
}