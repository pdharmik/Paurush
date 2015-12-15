/**
 * Copyright@ 2012. This document has been created & written by
 * Wipro technologies for Lexmark and this is copyright to Lexmark
 *
 * File         	: ManageReturnsController.java
 * Package     		: com.lexmark.services.portlet.om
 * Creation Date 	: 1st May 2012
 *
 * Modification History:
 *
 * ---------------------------------------------------------------------
 * Author 				Date				Version  		Comments
 * ---------------------------------------------------------------------
 * Sagar Sarkar			1st May 2012 		1.0             Initial Version
 * 
 *
 */
package com.lexmark.services.portlet.om;

import static com.lexmark.services.LexmarkSPConstants.ERROR_MESSAGE_BUNDLE;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.contract.AttachmentContract;
import com.lexmark.contract.source.ReturnServiceRequestContract;
import com.lexmark.domain.AccountContact;
import com.lexmark.domain.Attachment;
import com.lexmark.domain.FileObject;
import com.lexmark.domain.ServiceRequest;
import com.lexmark.exceptionimpl.checked.LGSCheckedException;
import com.lexmark.exceptionimpl.checked.business.LGSBusinessException;
import com.lexmark.exceptionimpl.runtime.LGSRuntimeException;
import com.lexmark.framework.exception.LGSDBException;
import com.lexmark.framework.exception.LGSServiceException;
import com.lexmark.framework.logging.LEXLogger;
import com.lexmark.result.CreateServiceRequestResult;
import com.lexmark.service.api.AttachmentService;
import com.lexmark.service.api.CrmSessionHandle;
import com.lexmark.service.api.GlobalService;
import com.lexmark.service.api.SiebelCrmServiceException;
import com.lexmark.service.impl.real.util.AttachmentProperties;
import com.lexmark.services.api.ReturnOrderService;
import com.lexmark.services.form.BaseForm;
import com.lexmark.services.form.FileUploadForm;
import com.lexmark.services.form.ManageReturnsForm;
import com.lexmark.services.form.validator.FileUploadFormValidator;
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


/**.
 * This Class is used for handling return supplies request.
 * @author Sagar Sarkar
 *
 */
@Controller
@RequestMapping("VIEW")
@SessionAttributes(value={"manageReturnsForm"})
public class ManageReturnsController extends BaseController {
	
	/**. Holds the reference of CommonController bean **/
	@Autowired
	private CommonController commonController;
	/**. This variable holds AmindGlobalService bean reference **/
	@Autowired
    public GlobalService  globalService;
	/**. This variable holds the reference of AmindAttachmentService bean **/
	@Autowired
    public AttachmentService attachmentService;
	/**. This variable holds the reference of FileUploadFormValidator bean **/
	@Autowired
    public FileUploadFormValidator fileUploadFormValidator;
	/**. This variable holds the reference of ReturnOrderServiceImpl bean **/
	@Autowired
	private ReturnOrderService returnOrderService;
	

	/**. Instance variable of wrapper logger class **/
	private static LEXLogger LOGGER = LEXLogger.getLEXLogger(ManageReturnsController.class);
	private static Logger perfLogger = LogManager.getLogger("performance"); //Added for performance logging
	/**. This variable holds the class name  **/
	private static final String CLASS_NAME = "ManageReturnsController.java";
	/**. This variable holds the sub area key for localized 
	 * value of return supplies subarea dropdown list **/
	private static final String LOV_TYPE_RETURN_SUBAREA = "OM_RETURNS_SUBAREA";
	/**. This variable holds the form bean name **/
	private static final String MANAGERETURNSFORM = "manageReturnsForm";
	/**. This variable holds the UploadForm bean name **/
	private static final String FILEUPLOADFORM = "fileUploadForm";
	/**. This variable holds the filemap  name **/
	private static final String FILEMAP = "fileMap";

	/**.
	 * This method binds the form beans
	 * @param binder
	 * @param locale
	 */
	@InitBinder(value={FILEUPLOADFORM, MANAGERETURNSFORM})
	protected void initBinder(WebDataBinder binder, Locale locale) {
		String METHOD_NAME = "initBinder";
		LOGGER.enter(CLASS_NAME, METHOD_NAME);
		
		LOGGER.debug("Language is " + locale.getLanguage()+ 
					"\n Country is " + locale.getCountry()+
					"\n Format is :" + DateUtil.getDateFormatByLang(locale.getLanguage()));
		
		DateFormat sdf=new SimpleDateFormat(DateUtil.getDateFormatByLang(locale.getLanguage()));
		sdf.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
		
		if (binder.getTarget() instanceof ManageReturnsForm) {	
			LOGGER.debug("Setting ManageReturnsForm validator");
			
		}
		else if(binder.getTarget() instanceof FileUploadForm){
			LOGGER.debug("Setting FileUploadForm validator");
			binder.setValidator(fileUploadFormValidator);
		}
		LOGGER.exit(CLASS_NAME, METHOD_NAME);
	}
	
	/**.
	 * Renders the print confirmation page of return request.
	 * @param request RenderRequest
	 * @param response RenderResponse
	 * @param model Model
	 * @return String email confirmation page
	 * @throws Exception
	 */
	@RequestMapping(params = "action=printManageReturns")
	public String printManageReturns(RenderRequest request, RenderResponse response, Model model) throws Exception{
		return "returns/printReturnsConfirmation";
	}

	/**. Renders the email template page
	 * 
	 * @param request RenderRequest
	 * @param response RenderResponse
	 * @param model Model
	 * @return emailReturnsConfirmation.jsp
	 * @throws Exception
	 */
	@RequestMapping(params = "action=emailManageReturns")
	public String emailManageReturns(RenderRequest request, RenderResponse response, Model model) throws Exception{
		return "returns/emailReturnsConfirmation";
	}
	
	
	/**
	 * Default method for Manage Returns portlet
	 * Populates the form, requester information and 
	 * @param model Model
	 * @param request RenderRequest
	 * @param response RenderResponse
	 * @return manageReturnsDetail.jsp
	 * @throws Exception
	 */
	@RequestMapping
	public String showManageReturns(Model model, RenderRequest request, RenderResponse response)
	throws Exception{
		
		String METHOD_NAME = "showManageReturns";
		LOGGER.enter(CLASS_NAME, METHOD_NAME);
		HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));
		
		String pageType = "";
		if(null!=httpReq.getParameter("pageType") && !httpReq.getParameter("pageType").equals("")){
			pageType = httpReq.getParameter("pageType");
		}
		String accountName = "";
		if(null!=httpReq.getParameter("accnName") && !httpReq.getParameter("accnName").equals("")){
			accountName = httpReq.getParameter("accnName");
		}
		LOGGER.debug("PageType is ---------------->>>>>>>> "+pageType);
		LOGGER.debug("accountName is ---------------->>>>>>>> "+accountName);
		
		String accountId = "";
		if(null!=httpReq.getParameter("accnId") && !httpReq.getParameter("accnId").equals("")){
			accountId=httpReq.getParameter("accnId");
		}
		LOGGER.debug("accountId is ---------------->>>>>>>> "+accountId);
		
		//List of exceptions to be displayed to user
		List<String> exceptionList = new ArrayList<String>();
		Map<String, String> returnsSubAreaMap = new LinkedHashMap<String, String>() ;
		ManageReturnsForm manageReturnsForm = new ManageReturnsForm();
		
		//Initializes the form for file upload
		FileUploadForm fileUploadForm = new FileUploadForm();
		AccountContact accContact = null;
		ResourceURL resURL1 = null;
		PortletSession session = request.getPortletSession();
		if(pageType.equalsIgnoreCase("deviceFinder")){
			Map<String,String> accountDetailsValues=new HashMap<String,String>();
			accountDetailsValues.put("accountName", accountName);
			accountDetailsValues.put("accountId", accountId);
			
			//accountDetailsValues.put("agreementName", "AGGRNAME");
			accountDetailsValues.put("rowCount", "2");					
			session.setAttribute(ChangeMgmtConstant.ACNTCURRDETAILS, accountDetailsValues,PortletSession.APPLICATION_SCOPE);
			
		}
		
		try {
			//Requester information is populated from the Portlet session
			accContact=commonController.getContactInformation(request, response);
			
			//Initializes and populates common information about the request like requestor, primary contact		
			ServiceRequest serviceRequest = new ServiceRequest();
			serviceRequest.setRequestor(accContact);
			serviceRequest.setPrimaryContact(accContact);
			manageReturnsForm.setServiceRequest(serviceRequest);
			
			resURL1 = response.createResourceURL();
			resURL1.setResourceID("displayAttachment");
			
			//Removes the Attachment Map if any exists from previous flows
			session.removeAttribute(ChangeMgmtConstant.SESSION_FILE_MAP);
		
		    //Map to populate Sub Area drop down
			
			returnsSubAreaMap = commonController.retrieveLocalizedLOVMap(LOV_TYPE_RETURN_SUBAREA, request.getLocale());
			LOGGER.debug("Retrieved areaMap ::: "+ returnsSubAreaMap);
			
		} catch (LGSDBException dbExcption) {
			LOGGER.error("LGSBusinessException occurred ::: "+ dbExcption.getMessage());			
			exceptionList.add(ServiceStatusErrorMessageUtil.getErrorMessage(ERROR_MESSAGE_BUNDLE,
					"exception.portal.database.10001", null, request.getLocale()));
		} catch (Exception e) {
			LOGGER.debug("LGSRuntimeException occurred ::: "+ e.getMessage());
			exceptionList.add(ServiceStatusErrorMessageUtil.getErrorMessage(ERROR_MESSAGE_BUNDLE,
					"exception.portal.general", null, request.getLocale()));
		}
		if(resURL1!= null){
			model.addAttribute("chlTemplateURL", resURL1.toString());
		}
		if(!exceptionList.isEmpty()){
			model.addAttribute("exceptionList", exceptionList);	
		}
		if(accContact != null){
			model.addAttribute("accountContact", accContact);
		}
		model.addAttribute("returnsSubAreaMap", returnsSubAreaMap);	
		model.addAttribute(MANAGERETURNSFORM, manageReturnsForm);
		model.addAttribute(FILEUPLOADFORM, fileUploadForm);
		LOGGER.exit(CLASS_NAME, METHOD_NAME);
		return "returns/manageReturnsDetail";
	}
	
	
	
	/**
	 * The method is called when "Continue" button is clicked from manageReturns page
	 * @param model Model
	 * @param request ActionRequest
	 * @param response ActionResponse
	 * @param manageReturnsForm
	 * @throws LGSCheckedException
	 * @throws LGSRuntimeException
	 */
	@ActionMapping(params = "action=showManageReturnsReview")
	public void showManageReturnsReview(Model model, ActionRequest request, ActionResponse response,
			@ModelAttribute(MANAGERETURNSFORM) ManageReturnsForm
			manageReturnsForm) throws Exception {
		String METHOD_NAME = "showManageReturnsReview";
		LOGGER.enter(CLASS_NAME, METHOD_NAME);
		PortletSession session = request.getPortletSession();
		@SuppressWarnings("unchecked")
		Map<String, FileObject> fileMap = (Map<String, FileObject>) session.getAttribute(ChangeMgmtConstant.SESSION_FILE_MAP);		
		request.setAttribute(FILEMAP,fileMap);
		model.addAttribute(FILEMAP,fileMap);
		manageReturnsForm.refreshSubmitToken(request);
		model.addAttribute(MANAGERETURNSFORM,manageReturnsForm);
		response.setRenderParameter("action", "showManageReturnsReview");
		LOGGER.exit(CLASS_NAME, METHOD_NAME);
		
	}
	
	/**
	 * The method is called from confirmRequest method to render the confirmation for Returns
	 * @param model Model
	 * @param request RenderRequest
	 * @param response RenderResponse
	 * @return manageReturnsReview.jsp
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RenderMapping(params = "action=showManageReturnsReview")
	public String showManageReturnsReview(Model model, RenderRequest request, 
			RenderResponse response,@ModelAttribute(MANAGERETURNSFORM) ManageReturnsForm manageReturnsForm) 
	throws Exception {
		String METHOD_NAME = "showManageReturnsReview";
		LOGGER.enter(CLASS_NAME, METHOD_NAME);
		
		/*Added for timezone*/
		LOGGER.debug("request param is " + request.getParameter(ChangeMgmtConstant.TIMEZNOFFSET));
		String timezoneOffset=request.getParameter(ChangeMgmtConstant.TIMEZNOFFSET);
		
		if(!StringUtil.isEmpty(timezoneOffset)){
			model.addAttribute(LexmarkConstants.TIMEZONE_OFFSET,timezoneOffset);
		}
		ResourceURL resURL  = response.createResourceURL();
		resURL.setResourceID("displayAttachment");
		model.addAttribute("attachmentURL", resURL.toString());
		PortletSession session = request.getPortletSession();
		Map<String, FileObject> fileMap = (Map<String, FileObject>) session.getAttribute(ChangeMgmtConstant.SESSION_FILE_MAP);		
		request.setAttribute(FILEMAP,fileMap);
		model.addAttribute(FILEMAP,fileMap);
		
		LOGGER.exit(CLASS_NAME, METHOD_NAME);
		return "returns/manageReturnsReview";
	}
	
	
	/**
	 * The method is called when "Back" button is clicked from Manage Returns Review page
	 * @param model Model
	 * @param request ActionRequest
	 * @param response ActionResponse
	 * @param manageReturnsForm ManageReturnsForm
	 * @throws Exception
	 */
	@ActionMapping(params = "action=backToManageReturns")
	public void backToManageReturns(Model model, ActionRequest request, ActionResponse response,
			@ModelAttribute(MANAGERETURNSFORM) ManageReturnsForm
			manageReturnsForm) throws Exception {
		String METHOD_NAME = "backToManageReturns";
		LOGGER.enter(CLASS_NAME, METHOD_NAME);
		FileUploadForm fileUploadForm = new FileUploadForm();
	
		PortletSession session = request.getPortletSession();
		List<String> exceptionList = new ArrayList<String>();
		
		@SuppressWarnings("unchecked")
		Map<String, FileObject> fileMap = (Map<String, FileObject>) session.getAttribute(ChangeMgmtConstant.SESSION_FILE_MAP);
		fileUploadForm.setFileMap(fileMap);
		if (fileMap != null) {
			fileUploadForm.setFileCount(fileMap.size());
		}
		Map<String, String> returnSubAreaMap = new LinkedHashMap<String, String>() ;
	
		try {
			
			returnSubAreaMap = commonController.retrieveLocalizedLOVMap(LOV_TYPE_RETURN_SUBAREA, request.getLocale());
			LOGGER.debug("Retrieved returnSubAreaMap ::: "+ returnSubAreaMap);
			
		} catch (LGSDBException e) {
			LOGGER.error("LGSDBException occurred ::: "+ e.getMessage());
			exceptionList.add(ServiceStatusErrorMessageUtil.getErrorMessage(ERROR_MESSAGE_BUNDLE,
					"exception.portal.database.10001", null, request.getLocale()));
		} catch (Exception e) {
			exceptionList.add(ServiceStatusErrorMessageUtil.getErrorMessage(ERROR_MESSAGE_BUNDLE,
					"exception.portal.general", null, request.getLocale()));
		}
		LOGGER.debug("Retrieved areaMap ::: "+ returnSubAreaMap);
		
		if(!exceptionList.isEmpty()){
			model.addAttribute("exceptionList", exceptionList);	
		}
		model.addAttribute("returnsSubAreaMap", returnSubAreaMap);	
		model.addAttribute(MANAGERETURNSFORM,manageReturnsForm);
		model.addAttribute(FILEUPLOADFORM,fileUploadForm);
		
		commonController.getContactInformation(request, response);
		LOGGER.exit(CLASS_NAME, METHOD_NAME);
		response.setRenderParameter("action", "backToManageReturns");
		
	}
	
	/**
	 * The method is called from render method to render the manage return home page
	 * @param model Model
	 * @param request RenderRequest
	 * @param response RenderResponse
	 * @return manageReturnsDetail.jsp
	 * @throws Exception
	 */
	@RenderMapping(params = "action=backToManageReturns")
	public String backToManageReturns(Model model, RenderRequest request, RenderResponse response)
	throws Exception {
		String METHOD_NAME = "backToManageReturns";
		LOGGER.enter(CLASS_NAME, METHOD_NAME);
		ResourceURL resURL1 = response.createResourceURL();
		resURL1.setResourceID("displayAttachment");
		model.addAttribute("chlTemplateURL", resURL1.toString());
		
		LOGGER.exit(CLASS_NAME, METHOD_NAME);
		return "returns/manageReturnsDetail";
	}
	
	/**
	 * The method is called when "Attach" button is clicked from Manage Returns page
	 * @param model Model
	 * @param request ActionRequest
	 * @param response ActionResponse
	 * @param manageReturnsForm ManageReturnsForm
	 * @param fileUploadForm FileUploadForm
	 * @param result BindingResult
	 * @throws Exception
	 */
	@ActionMapping(params = "action=attachDocument")
	public void attachDocument(Model model,ActionRequest request, ActionResponse response,
			@ModelAttribute(MANAGERETURNSFORM) ManageReturnsForm
			manageReturnsForm, @ModelAttribute(FILEUPLOADFORM) @Valid FileUploadForm 
			fileUploadForm, BindingResult result) throws Exception {
		String METHOD_NAME = "attachDocument";
		LOGGER.enter(CLASS_NAME, METHOD_NAME);
		PortletSession session = request.getPortletSession();
		
		@SuppressWarnings("unchecked")
		Map<String, FileObject> fileMap = (Map<String, FileObject>) session.getAttribute(ChangeMgmtConstant.SESSION_FILE_MAP);
		
		if (result.hasErrors()) {
			LOGGER.debug("Contains Validation Errors");
			fileUploadForm.setFileMap(fileMap);
			session.setAttribute(ChangeMgmtConstant.SESSION_FILE_MAP, fileMap);
			request.setAttribute(FILEUPLOADFORM,fileUploadForm);
			model.addAttribute(FILEUPLOADFORM,fileUploadForm);
			response.setRenderParameter("action", "attachDocument");
			
		} else{
			List<String> exceptionList = new ArrayList<String>();
			try {
				//List of exceptions to be displayed to user
				if(fileMap == null){
					fileMap = new LinkedHashMap<String, FileObject>();
				}
				
				Long fileSize=fileUploadForm.getFileData().getSize();				
								
				LOGGER.debug("File Size is " + fileSize);				
				fileSize=fileSize/1024; 
				
				LOGGER.debug("File Size is " + fileSize);
										
				FileObject fileObject = new FileObject();
				FileItem fileItem=fileUploadForm.getFileData().getFileItem();					
				String fileName = fileItem.getName();
				int index = fileName.indexOf("\\");
									
				if(index != 0){
					int index1 = fileName.lastIndexOf("\\");
					fileName = fileName.substring(index1+1);
						
				}
					
				fileObject.setFileName(setTimestampInAttachment(fileUploadForm.getFileData().getOriginalFilename()));
				fileObject.setFileSize(String.valueOf(fileSize));
				fileObject.setFileSizeInBytes(String.valueOf(fileUploadForm.getFileData().getSize()));
				fileObject.setDisplayFileName(fileName)	;				
				/** Code for file transfer begins */
				AttachmentProperties fileProperties = new AttachmentProperties(ChangeMgmtConstant.ATTACHMENT_REQUEST_TYPE);
				LOGGER.debug("Starting File Transfer--->" + fileProperties.getFileUploadDestination() + fileObject.getFileName());
				fileItem.write(new File(fileProperties.getFileUploadDestination() + fileObject.getFileName()));
				
				LOGGER.debug("File Transfer is completed... ");
				/** Code for file upload ends */
				
				fileMap.put(fileObject.getFileName(),fileObject);
				fileUploadForm.setFileMap(fileMap);
				fileUploadForm.setFileCount(fileMap.size());
				session.setAttribute(ChangeMgmtConstant.SESSION_FILE_MAP, fileMap);
			}
			catch (IOException e) {
				LOGGER.error("Exception occurred while uploading the file in shared location ::: "+ e.getMessage());
				exceptionList.add(ServiceStatusErrorMessageUtil.getErrorMessage(ERROR_MESSAGE_BUNDLE,
						"exception.portal.fileupload", null, request.getLocale()));				
			}catch (Exception e) {
				LOGGER.error("Exception occured : Could not complete File Transfer.");
				exceptionList.add(ServiceStatusErrorMessageUtil.getErrorMessage(ERROR_MESSAGE_BUNDLE,
						"exception.portal.general", null, request.getLocale()));
			}

			if(!exceptionList.isEmpty()){
				model.addAttribute("exceptionList", exceptionList);	
			}
			model.addAttribute(FILEUPLOADFORM,fileUploadForm);
			response.setRenderParameter("action", "attachDocument");
			LOGGER.exit(CLASS_NAME, METHOD_NAME);
		}
		
	}
	
	/**.
	 * Utility method to append timestamp with attachment
	 * @param fName
	 * @return
	 * @throws LGSBusinessException
	 */
	private String setTimestampInAttachment(String fName)
			throws LGSBusinessException {
		String METHOD_NAME = "setTimestampInAttachment";
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
	 * The method is called from attachDocument method to render the target page in the iframe
	 * @param model Model
	 * @param request RenderRequest
	 * @param response RenderResponse
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "action=attachDocument")
	public String attachDocument(Model model, RenderRequest request,
			RenderResponse response) throws Exception {
		
		LOGGER.debug("Inside ManageReturnsController >> attachDocument render method");
		model.addAttribute("source","attachment");
		return "returns/manageReturnsDetail";
	}
	
	/**
	 * The method is called when Remove icon is clicked for any attachment from manageCHL or manageOthers page
	 * @param model Model
	 * @param request ActionRequest
	 * @param response ActionResponse
	 * @param fileUploadForm FileUploadForm
	 * @param fileName String
	 * @throws Exception
	 */
	@ActionMapping(params = "action=removeDocument")
	public void removeDocument(Model model,ActionRequest request, ActionResponse response,  @ModelAttribute(FILEUPLOADFORM) FileUploadForm 
			fileUploadForm,  @RequestParam(value="fileName") String fileName) throws Exception {
		String METHOD_NAME = "removeDocument";
		LOGGER.enter(CLASS_NAME, METHOD_NAME);
		PortletSession session = request.getPortletSession();
		List<String> exceptionList = new ArrayList<String>();
		try{
			@SuppressWarnings("unchecked")
			Map<String, FileObject> fileMap = (Map<String, FileObject>) session.getAttribute(ChangeMgmtConstant.SESSION_FILE_MAP);
			
			if (fileName != null && fileMap != null
					&& fileMap.containsKey(fileName)) {
				AttachmentProperties fileProperties = new AttachmentProperties(
						ChangeMgmtConstant.ATTACHMENT_REQUEST_TYPE);
				File removableFile = new File(fileProperties
						.getFileUploadDestination()
						+ fileName);
				boolean delStatus = removableFile.delete();
				LOGGER.debug("attachment delete status:" + delStatus);
				if (delStatus == false) {
					
					LOGGER.error("Error: The attached file is not deletred");					
					exceptionList.add(ServiceStatusErrorMessageUtil.getErrorMessage(ERROR_MESSAGE_BUNDLE,
							"exception.portal.deleteattachment", null, request.getLocale()));
				} else {
					fileMap.remove(fileName);
				}
			}
			
			
			if(fileMap == null || fileMap.isEmpty()){
				fileUploadForm.setFileMap(null);
				fileUploadForm.setFileCount(0);
				session.removeAttribute(ChangeMgmtConstant.SESSION_FILE_MAP);
			}
			else{
				fileUploadForm.setFileMap(fileMap);
				fileUploadForm.setFileCount(fileMap.size());
				
			}
		}catch (Exception e) {
			LOGGER.error("Exception occurred while deleteing attachment::: "+ e.getMessage());
			exceptionList.add(ServiceStatusErrorMessageUtil.getErrorMessage(ERROR_MESSAGE_BUNDLE,
					"exception.portal.general", null, request.getLocale()));
		}
		
		if(!exceptionList.isEmpty()){
			model.addAttribute("exceptionList", exceptionList);	
		}
		
		model.addAttribute(FILEUPLOADFORM,fileUploadForm);
		response.setRenderParameter("action", "removeDocument");
		LOGGER.exit(CLASS_NAME, METHOD_NAME);
		
	}
	/**
	 * The method is called from removeDocument method to render the target page in the iframe
	 * @param model Model
	 * @param request RenderRequest
	 * @param response RenderResponse
	 * @return manageReturnsDetail.jsp
	 * @throws Exception
	 */
	@RequestMapping(params = "action=removeDocument")
	public String removeDocument(Model model, RenderRequest request,
			RenderResponse response) throws Exception {
		LOGGER.debug("Inside ManageReturnsController >> removeDocument render method");
		model.addAttribute("source","attachment");
		return "returns/manageReturnsDetail";
	}
	
	/**.
	 * Displays the attachment in client browser
	 * @param request ResourceRequest
	 * @param response ResourceResponse
	 * @param model Model
	 * @throws Exception
	 */
	@ResourceMapping(value="displayAttachment")
	public void displayAttachment(ResourceRequest request,ResourceResponse response, Model model) throws Exception{			
		String METHOD_NAME = "displayAttachment";
		LOGGER.enter(CLASS_NAME, METHOD_NAME);
		String fileType = request.getParameter("fileType");
		String fileName = request.getParameter("fileName");
		
		if ("Attachment".equalsIgnoreCase(fileType)) {
			AttachmentProperties fileProperties = new AttachmentProperties(ChangeMgmtConstant.ATTACHMENT_REQUEST_TYPE);
			LOGGER.debug("Starting File Transfer" + fileProperties.getFileUploadDestination() + fileName);
			openAttachment(response, fileName, fileProperties.getFileUploadDestination() + fileName);
		}
		
		LOGGER.exit(CLASS_NAME, METHOD_NAME);
		
	}
	
	/**.
	 * 
	 * @param response
	 * @param fileName
	 * @param fullPath
	 */
	private void openAttachment(ResourceResponse response,String fileName, String fullPath){
		String METHOD_NAME = "openAttachment";
		LOGGER.enter(CLASS_NAME, METHOD_NAME);
		response.setProperty("Content-disposition", "attachment; filename=" + fileName);
		
		
		LOGGER.debug("fileName " + fileName);
		LOGGER.debug("fullPath " + fullPath);
		
		if(fileName.indexOf("csv")>0){
			LOGGER.debug("End of retrieveCHLTemplate...fileName.indexOf(csv)" + fileName.indexOf("csv"));
        	response.setContentType("application/vnd.ms-excel");
        }else if(fileName.indexOf("jpg") > 0 || fileName.indexOf("jpeg") > 0) {
        	LOGGER.debug("End of retrieveCHLTemplate...fileName.indexOf(jpg)" + fileName.indexOf("jpg"));
			response.setContentType("image/jpg");
        }else if(fileName.indexOf("gif") > 0) {
        	LOGGER.debug("End of retrieveCHLTemplate...fileName.indexOf(gif)" + fileName.indexOf("gif"));
        	response.setContentType("image/gif");
        }else if(fileName.indexOf("png") > 0) {
        	LOGGER.debug("End of retrieveCHLTemplate...fileName.indexOf(png)" + fileName.indexOf("png"));
        	response.setContentType("image/png");
        }else if(fileName.indexOf("pdf") > 0) {
        	LOGGER.debug("End of retrieveCHLTemplate...fileName.indexOf(pdf)" + fileName.indexOf("pdf"));
        	response.setContentType("application/pdf");
        	
        }else if(fileName.indexOf("txt") > 0 ){
        	LOGGER.debug("End of retrieveCHLTemplate...fileName.indexOf(txt)" + fileName.indexOf("txt"));
        	response.setContentType("text/plain");
        }else if(fileName.indexOf("xls")>0 || fileName.indexOf("xlsx")>0){
        	LOGGER.debug("End of retrieveCHLTemplate...fileName.indexOf(xls)" + fileName.indexOf("xls"));
        	response.setContentType("application/vnd.ms-excel");
        }else if(fileName.indexOf("doc")>0 || fileName.indexOf("docx")>0){
        	LOGGER.debug("End of retrieveCHLTemplate...fileName.indexOf(doc)" + fileName.indexOf("doc"));
        	response.setContentType("application/msword");
        }

		try {
				LOGGER.debug("fullPath " + fullPath);
			  InputStream inputStream= new FileInputStream(fullPath);
			  LOGGER.debug("fullPath " + fullPath);
			  OutputStream out = response.getPortletOutputStream();
			  byte buf[]=new byte[1024];
			  int len;
			  while((len=inputStream.read(buf))>0)
			  {out.write(buf,0,len);}
			  //out.flush();
			  out.close();
			  inputStream.close();
			  LOGGER.debug("File is created...................................");
			
		} catch (IOException e) {
			LOGGER.error("IOException occurred ::" + e);
			
		}
		LOGGER.exit(CLASS_NAME, METHOD_NAME);
	}
	
	
	/**
	 * The method is called when "Submit" button is clicked from reviewReturnsPage
	 * The create SR service call is executed as well Amind call for attachments is triggered from this method
	 * @param model Model
	 * @param request ActionRequest
	 * @param response ActionResponse
	 * @param manageReturnsForm ManageReturnsForm
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@ActionMapping(params = "action=createManageReturnsRequest")
	public void createManageReturnsRequest(Model model, ActionRequest request,
			ActionResponse response, @ModelAttribute(MANAGERETURNSFORM) ManageReturnsForm
			manageReturnsForm) throws Exception {
		String METHOD_NAME = "createManageReturnsRequest";
		LOGGER.enter(CLASS_NAME, METHOD_NAME);		

		if (PortalSessionUtil.isDuplicatedSubmit(request,
				manageReturnsForm)) {
			LOGGER.debug("ManageTemplateController.submitTemplateSRForm.duplicated submit, do nothing!");		
			model.addAttribute("error", ServiceStatusErrorMessageUtil.getErrorMessage(ERROR_MESSAGE_BUNDLE,
					"exception.serviceRequest.duplicateSubmission", null, request.getLocale()));
			response.setRenderParameter("action", "createManageReturnsRequestError");
		}else{
			
		List<String> exceptionList = new ArrayList<String>();		
		ReturnServiceRequestContract contract = ContractFactory.getReturnOrderRequestContract(manageReturnsForm, request);		
		PortletSession session = request.getPortletSession();
	
		Map<String, FileObject> fileMap = (Map<String, FileObject>) session.getAttribute(ChangeMgmtConstant.SESSION_FILE_MAP);
		
					
			Map<String,String> accountDetailsMap=(Map<String,String>)
			session.getAttribute("accountCurrentDetails",PortletSession.APPLICATION_SCOPE);
			
			List<String> userRoleList = PortalSessionUtil.getUserRoles(session);		
			boolean isVendorFlag = false;
			if(!userRoleList.isEmpty() && ((userRoleList.contains(LexmarkConstants.ROLE_SERVICE_MANAGER)) 
					|| (userRoleList.contains(LexmarkConstants.ROLE_SERVICE_TECHNICIAN))))
			{
				isVendorFlag = true;
			}
			try {
				long timeBeforeCall=System.currentTimeMillis();
				CreateServiceRequestResult result = returnOrderService.createChangeManagementServiceRequest(contract,
						accountDetailsMap, isVendorFlag);
				long timeAfterCall=System.currentTimeMillis();
				
				PerformanceUtil.calcTime(perfLogger, PerformanceConstant.MANAGERETURNS_MSG_CREATECHANGEMANAGEMENTSERVICEREQUEST, timeBeforeCall,timeAfterCall, PerformanceConstant.SYSTEM_WEBMETHODS,contract);
				
				LOGGER.debug(" SR NUMBER IS "+result.getServiceRequestNumber());
				if(result!= null && result.getServiceRequestNumber() != null && result.getServiceRequestRowId()!=null){
					LOGGER.debug("After create service request call SR number::: "+ result.getServiceRequestNumber());
					LOGGER.debug("After create service request call SR row id::: "+ result.getServiceRequestRowId());
					manageReturnsForm.getServiceRequest().setServiceRequestNumber(result.getServiceRequestNumber());
					//call updateAttachment 
					updateAttachmentService(fileMap, request, result);
				}else{
					exceptionList.add(ServiceStatusErrorMessageUtil.getErrorMessage(ERROR_MESSAGE_BUNDLE,
							"exception.webmethods.srNumberOrRowIdNotFound", null, request.getLocale()));
				}
				}catch(LGSServiceException e){
					LOGGER.error("Error in returnOrderService service call");
					exceptionList.add(ServiceStatusErrorMessageUtil.getErrorMessage(ERROR_MESSAGE_BUNDLE,
							"exception.webmethods.webservice", null, request.getLocale()));
					
			}
		request.setAttribute(FILEMAP,fileMap);
		model.addAttribute(FILEMAP,fileMap);
		
		//this is to enable re-submit of SR form on submit/draft exception
		Long tokenInSession = (Long)session.getAttribute(LexmarkConstants.SUBMIT_TOKEN, session.PORTLET_SCOPE);
		BaseForm baseForm = (BaseForm)manageReturnsForm;
		baseForm.setSubmitToken(tokenInSession);
		model.addAttribute(manageReturnsForm);
		model.addAttribute(MANAGERETURNSFORM,manageReturnsForm);
		
		if(exceptionList.isEmpty()){
			response.setRenderParameter("action", "createManageReturnsRequestSuccess");
		}else{
			model.addAttribute("exceptionList", exceptionList);					
			response.setRenderParameter("action", "createManageReturnsRequestError");
		}		
		}
		LOGGER.exit(CLASS_NAME, METHOD_NAME);	
		
	}
	
	
	/**.
	 * This method updates aMind attachmentServices API with SR row ID
	 * @param fileMap Map<String, FileObject>
	 * @param session PortletSession
	 * @param result CreateServiceRequestResult
	 */
	private void updateAttachmentService(Map<String, FileObject> fileMap, 
			ActionRequest request, CreateServiceRequestResult result){
		String METHOD_NAME = "updateAttachmentService";
		LOGGER.enter(CLASS_NAME, METHOD_NAME);	
		
		LOGGER.debug("File upload process begins");
		PortletSession session = request.getPortletSession();
		if(fileMap != null){
			
			try{
				
                List<Attachment> attachmentList = new ArrayList<Attachment>();
                Set<String> fileNames = fileMap.keySet();
				Iterator<String> iterator = fileNames.iterator();
		        while (iterator.hasNext()) {
		        		String fileName =  iterator.next();
		                
		        		FileObject fileDetails = fileMap.get(fileName);
		        		LOGGER.debug("File Details Object" + fileDetails);
		        		Attachment file = new Attachment();
		        		
		        		

		    			if(PortalSessionUtil.getUserType(session).equalsIgnoreCase("Vendor")){
		    				file.setVisibility(ChangeMgmtConstant.USERTYPE_PARTNER);
		    			}else{
		    				file.setVisibility(PortalSessionUtil.getUserType(session));
		    			}
		    			LOGGER.debug("File Visibility ::: " + file.getVisibility());
		        		file.setAttachmentName(fileDetails.getFileName());
		        		
		        		file.setSize(Integer.parseInt(fileDetails.getFileSizeInBytes()));
		        		LOGGER.debug("File name ::: " + fileDetails.getFileName());
		        		LOGGER.debug("File size ::: " + fileDetails.getFileSizeInBytes());
		        		attachmentList.add(file);
		        }
        		CrmSessionHandle crmSessionHandle = globalService.initCrmSessionHandle(PortalSessionUtil.getSiebelCrmSessionHandle(request));
        		AttachmentContract atchmntContract = new AttachmentContract();
        		atchmntContract.setSessionHandle(crmSessionHandle);
        		if(result!= null){
        			atchmntContract.setIdentifier(result.getServiceRequestRowId());
        		}
        		
        		atchmntContract.setRequestType(ChangeMgmtConstant.ATTACHMENT_REQUEST_TYPE); 
        		atchmntContract.setAttachments(attachmentList);	 
        		ObjectDebugUtil.printObjectContent(atchmntContract, LOGGER);
				attachmentService.uploadAttachments(atchmntContract);

				LOGGER.debug("File upload process ends");		
	
		}catch (SiebelCrmServiceException e){
			LOGGER.error("SiebelCrmServiceException occured while updating attachment from aMind to Siebel:" + e.getMessage());
		}catch (Exception e) {
			LOGGER.error("Exception occured while invoking aMind uploadAttachments service:" + e.getMessage());
		}
	}
	LOGGER.exit(CLASS_NAME, METHOD_NAME);	
	}
	
	/**
	 * The method is called from createManageReturnsReques method to render the confirmation page for returns
	 * @param model Model
	 * @param request RenderRequest
	 * @param response RenderResponse
	 * @return manageReturnsConfirmation.jsp
	 */
	@RenderMapping(params = "action=createManageReturnsRequestSuccess")
	public String createManageReturnsRequestSuccess(Model model, RenderRequest request,
			RenderResponse response){
		String METHOD_NAME = "createManageReturnsRequestSuccess";
		LOGGER.enter(CLASS_NAME, METHOD_NAME);	
		LOGGER.debug("in summary method " + request.getParameter("timezoneOffset"));
		LOGGER.exit(CLASS_NAME, METHOD_NAME);
		return "returns/manageReturnsConfirmation";
	}
	/**
	 * The method is called from createManageReturnsReques method to render the confirmation page for returns
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "action=createManageReturnsRequestError")
	public String createManageReturnsRequestError(Model model, RenderRequest request,
			RenderResponse response){
		String METHOD_NAME="createManageReturnsRequestError";
		LOGGER.enter(CLASS_NAME, METHOD_NAME);	
		ResourceURL resURL  = response.createResourceURL();
		resURL.setResourceID("displayAttachment");
		model.addAttribute("attachmentURL", resURL.toString());
		LOGGER.exit(CLASS_NAME, METHOD_NAME);	
		return "returns/manageReturnsReview";
	}
	
	/**
	 * Renders the email confirmation page for multiple SR
	 * @param req
	 * @param resp
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "action=returnSupplyemailConfirmationPage")
	public String renderMultipleSRemailConfirmationPage(RenderRequest req, RenderResponse resp, Model model) {
		
		return "returns/manageReturnSupplyEmailTemplatePage";
	}
	
	/*Added for CI7 BRD14-02-12*/
	/**
	 * @param request 
	 * @param response 
	 * @param model 
	 * @throws Exception 
	 */
	@RequestMapping(params = "action=showReturnsPage")
	public void showReturnsPage(ActionRequest request, ActionResponse response, Model model) throws Exception{
		LOGGER.debug("------------------ RequestMapping of the showReturnsPage -----------------------");
		LOGGER.debug("friendlyURl is"+request.getParameter("friendlyURL"));
		response.sendRedirect(request.getParameter("friendlyURL"));
	}
	/*END*/

}
