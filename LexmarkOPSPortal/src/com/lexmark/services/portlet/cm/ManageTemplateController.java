/*
* Copyright:
* <Customer specific copyright notice (if any) >
* File Name: ManageTemplateController.java
* Description:	This controller serves the requests for template based SR.
* Version: 0.1
* Created Date:	16/01/2012
* Modification History:
*/

package com.lexmark.services.portlet.cm;

import static com.lexmark.services.LexmarkSPConstants.ERROR_MESSAGE_BUNDLE;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
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

import javax.annotation.Resource;
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
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.contract.AttachmentContract;
import com.lexmark.contract.CreateServiceRequestContract;
import com.lexmark.domain.AccountContact;
import com.lexmark.domain.Attachment;
import com.lexmark.domain.FileObject;
import com.lexmark.domain.ListOfValues;
import com.lexmark.domain.ServiceRequest;
import com.lexmark.exceptionimpl.checked.LGSCheckedException;
import com.lexmark.exceptionimpl.checked.business.LGSBusinessException;
import com.lexmark.exceptionimpl.runtime.LGSRuntimeException;
import com.lexmark.framework.logging.LEXLogger;
import com.lexmark.result.CreateServiceRequestResult;
import com.lexmark.service.api.AttachmentService;
import com.lexmark.service.api.CrmSessionHandle;
import com.lexmark.service.api.GlobalService;
import com.lexmark.service.api.SiebelCrmServiceException;
import com.lexmark.service.impl.real.util.AttachmentProperties;
import com.lexmark.services.LexmarkSPConstants;
import com.lexmark.services.api.CreateLgsRequest;
import com.lexmark.services.form.BaseForm;
import com.lexmark.services.form.CMTemplateRquestForm;
import com.lexmark.services.form.FileUploadForm;
import com.lexmark.services.form.validator.CMTemplateFormValidator;
import com.lexmark.services.portlet.common.CommonController;
import com.lexmark.services.util.ChangeMgmtConstant;
import com.lexmark.services.util.ObjectDebugUtil;
import com.lexmark.services.util.PerformanceConstant;
import com.lexmark.services.util.PortalSessionUtil;
import com.lexmark.services.util.ServiceStatusErrorMessageUtil;
import com.lexmark.services.util.PerformanceUtil; //Added for performance logging
import com.lexmark.util.DateUtil;
import com.lexmark.util.DownloadFileLocalizationUtil;
import com.lexmark.util.PropertiesMessageUtil;
import com.liferay.portal.util.PortalUtil;



/**.
 * This class handles the requests for template based SR.
 * @author wipro
 * @version 2.1
 */
@Controller
@RequestMapping("VIEW")
public class ManageTemplateController{
	
	private static Logger LOGGER = LogManager.getLogger(ManageTemplateController.class);
	private static LEXLogger logger = LEXLogger.getLEXLogger(ManageTemplateController.class);
	private static Logger perfLogger = LogManager.getLogger("performance"); //Added for performance logging
	
	@Autowired
	private CommonController commonController;	
	@Autowired
	private CreateLgsRequest createLgsServiceRequest;
	@Autowired
    public GlobalService  globalService;
	@Autowired
    public AttachmentService attachmentService;	
	/** private variable */
	private String srTemplateDownloadLocation;
	@Resource
	private  Map<String,String> templateNameMap;
	@Autowired
	CMTemplateFormValidator cmTemplateFormValidator ;
	
	private static final DecimalFormat df = new DecimalFormat("#.####");
	
	
	
	
	@InitBinder(value={ "fileUploadForm","templateRequestForm"})
	protected void initBinder(WebDataBinder binder, Locale locale) {
		
		LOGGER.debug("Language is " + locale.getLanguage());
		LOGGER.debug("country is " + locale.getCountry());		
		LOGGER.info("format is " + DateUtil.getDateFormatByLang(locale.getLanguage()));
		
		DateFormat sdf=new SimpleDateFormat(DateUtil.getDateFormatByLang(locale.getLanguage()));
		sdf.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
		binder.setValidator(cmTemplateFormValidator);
		
		
	}
	
	/**.
	 * This method renders the change management history page.
	 * 
	 * @param model Model object
	 * @param request RenderRequest object
	 * @param response RenderResponse object
	 * @return String The view page name
	 * @throws Exception Exception
	 */
	@RequestMapping(params = "action=showManageTemplateSRPage")
	public String showTemplatePage(Model model, RenderRequest request,
			RenderResponse response) throws Exception {

		LOGGER.debug("ManageTemplateController.showCMHistoryPage Enter : ");
		String msgKey1 = null;
		String msgKey2 = null;
		String localizedRequestType = null;
		String localizedRequestSubType = null;
		
		//Removes the Attachment Map if any exists from previous flows
		request.getPortletSession().removeAttribute(ChangeMgmtConstant.SESSION_FILE_MAP);
		String requestType = request.getParameter("requestType");
		String requestSubType = request.getParameter("requestSubType");
		
		/*Start-------Request type and sub type localization*/
		if(requestType!=null && requestSubType!=null)
		{
			if(requestType.equalsIgnoreCase("Add"))
			{
				msgKey1 = "requestInfo.cm.manageAssets.heading.add";
			}
			else if(requestType.equalsIgnoreCase("Change"))
			{
				msgKey1 = "requestInfo.cm.manageAssets.heading.change";
			}
			else if(requestType.equalsIgnoreCase("Decommission"))
			{
				msgKey1 = "requestInfo.cm.manageAssets.heading.decommision";
			}
			else if(requestType.equalsIgnoreCase("Swap"))
			{
				msgKey1 = "requestInfo.cm.manageAssets.heading.swap";
			}
			else if(requestType.equalsIgnoreCase("Remove"))
			{
				msgKey1 = "requestInfo.link.remove";
			}
		}
		
		if(requestSubType!=null)
		{
			if(requestSubType.equalsIgnoreCase("Assets"))
			{
				msgKey2 = "requestInfo.cm.manageAssets.heading.assets";
			}
			else if(requestSubType.equalsIgnoreCase("Addresses"))
			{
				msgKey2 = "requestInfo.cm.manageAddress.heading.addresses";
			}
			else if(requestSubType.equalsIgnoreCase("Contacts"))
			{
				msgKey2 = "requestInfo.cm.manageContact.heading.contacts";
			}
		}
		
		
		HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));
		String prevSRNumber = httpReq.getParameter("prevSrNumber");
		LOGGER.debug("ManageTemplateController.showCMHistoryPage prevSRNumber : "+prevSRNumber);
		
		if(msgKey1!=null && msgKey2!=null)
		{
			localizedRequestType = PropertiesMessageUtil.getPropertyMessage(LexmarkSPConstants.MESSAGE_BUNDLE_NAME, msgKey1, request.getLocale());
			localizedRequestSubType = PropertiesMessageUtil.getPropertyMessage(LexmarkSPConstants.MESSAGE_BUNDLE_NAME, msgKey2, request.getLocale());
		}
		
	
		model.addAttribute("requestTypeNew",requestType );
		model.addAttribute("requestSubTypeNew",requestSubType);
		model.addAttribute("requestType",localizedRequestType );
		model.addAttribute("requestSubType",localizedRequestSubType);
		
		/*END-------Request type and sub type localization*/
		
		CMTemplateRquestForm cmTemplateRequestForm=new CMTemplateRquestForm();
		ServiceRequest serviceRequest = new ServiceRequest();
		AccountContact accContact=commonController.getContactInformation(request, response);
		serviceRequest.setPrimaryContact(accContact);
		cmTemplateRequestForm.setServiceRequest(serviceRequest);
		cmTemplateRequestForm.setRequestType(requestType);
		cmTemplateRequestForm.setRequestSubType(requestSubType);
		cmTemplateRequestForm.setPrevSRNumber(prevSRNumber);
		model.addAttribute("templateRequestForm", cmTemplateRequestForm);
		FileUploadForm fileUploadForm = new FileUploadForm();
		model.addAttribute("fileUploadForm", fileUploadForm);
		
		if(templateNameMap.isEmpty()){
			LOGGER.warn("WARNING : templateNameMap is not set in portlet context file !");
		}
		
		ResourceURL downloadTemplateUrl  = response.createResourceURL(); 
		downloadTemplateUrl.setResourceID("downloadTemplate");
		model.addAttribute("downloadTemplateUrl", downloadTemplateUrl.toString());
		
		LOGGER.debug("ManageTemplateController.showCMHistoryPage Exit : ");
		
		return "changemanagement/multipleRequest/manageMultipleSRHome";
		
	}
	
	
	/**.
	 * This method downloads the multiple request templates to client system.
	 * Template file path and file names need to be set in portlet context file.
	 * @param request
	 * @param response
	 * @param model
	 * @throws Exception 
	 */
	@ResourceMapping(value="downloadTemplate")
	public void downloadTemplate(ResourceRequest request,ResourceResponse response, Model model) throws Exception{
		LOGGER.debug("ManageTemplateController.downloadTemplate Enter : ");
		
		
		String requestType = request.getParameter("requestType");
		String requestSubType = request.getParameter("requestSubType");
		LOGGER.debug("ManageTemplateController.downloadTemplate Enter : requestType--"+requestType);
		LOGGER.debug("ManageTemplateController.downloadTemplate Enter : requestSubType--"+requestSubType);
		String downloadPath = "";
		String fileToBeDownloaded = "";
		
		 if (request.getParameter("attachedFileName") !=null) {
				
				AttachmentProperties fileProperties = new AttachmentProperties(ChangeMgmtConstant.ATTACHMENT_REQUEST_TYPE);
				LOGGER.debug("File to be downloaded-->" + fileProperties.getFileUploadDestination() + request.getParameter("attachedFileName").trim());
				
				fileToBeDownloaded = request.getParameter("attachedFileName").trim();
				downloadPath = fileProperties.getFileUploadDestination() +fileToBeDownloaded;
				
		 }else{
			 
			 if(requestType != null && requestSubType != null){
				 if(requestSubType.equalsIgnoreCase("Assets")){ 
					 String fileNameKy = "requestInfo.template.name";
					 fileToBeDownloaded = PropertiesMessageUtil.getPropertyMessage(LexmarkSPConstants.MESSAGE_BUNDLE_NAME,fileNameKy, request.getLocale());
					 String fileNameKey = requestType.concat("_").concat(requestSubType);
					 LOGGER.debug("fileNameKey --->"+fileNameKey);
					 fileToBeDownloaded = fileToBeDownloaded+"_"+request.getLocale().toString()+".xls";
					 LOGGER.debug("Downloading file -->"+ fileToBeDownloaded);
					 LOGGER.debug("Here file name is "+fileToBeDownloaded);
					 LOGGER.debug("LOCALE IS ------------------- "+request.getLocale().toString());
					 downloadPath = getSrTemplateDownloadLocation()+fileToBeDownloaded;
				 }
				 else if(requestSubType.equalsIgnoreCase("Addresses") || requestSubType.equalsIgnoreCase("Contacts")){
				 String fileNameKey = requestType.concat("_").concat(requestSubType);
				 LOGGER.debug("fileNameKey --->"+fileNameKey);
				 if(! templateNameMap.containsKey(fileNameKey)){
					 throw new Exception("Template file not found !");
				 }
				 fileToBeDownloaded = templateNameMap.get(fileNameKey).trim();
				 LOGGER.debug("Downloading file -->"+templateNameMap.get(fileNameKey).trim());
				 downloadPath = getSrTemplateDownloadLocation()+fileToBeDownloaded;
				 }
				 
			 }
			 			 
		 }
		 		LOGGER.debug("downloadPath --->"+downloadPath);
		 		openAttachment(request,response,fileToBeDownloaded,downloadPath);
		 		LOGGER.debug("ManageTemplateController.downloadTemplate Exit : ");
			}
	
	

	/**.
	 * This method submits the template based SR along with the attachment to 
	 * the back end system.
	 * @param request ActionRequest
	 * @param response ActionResponse
	 * @param templateRequestForm CMTemplateRquestForm
	 * @param bindingResult BindingResult
	 * @param model Model
	 * @throws IOException
	 */	
	@ActionMapping(params = "action=reviewForm")
	public void actionReviewTemplateRequest(Model model,ActionRequest request,ActionResponse response,
			@ModelAttribute("templateRequestForm") CMTemplateRquestForm templateRequestForm ) throws IOException {
		
		LOGGER.debug("ManageTemplateController.actionReviewTemplateRequest Enter : ");		
		LOGGER.debug("ManageTemplateController.actionReviewTemplateRequest.request type::: "+ request.getParameter("requestType"));		
		LOGGER.debug("ManageTemplateController.actionReviewTemplateRequest.request sub type::: "+ request.getParameter("requestSubType"));
		LOGGER.debug("ManageTemplateController.actionReviewTemplateRequest.uploadedFileName::: "+ request.getParameter("uploadedFileName"));
		templateRequestForm.refreshSubmitToken(request);
		model.addAttribute("templateRequestForm",templateRequestForm);
		model.addAttribute("uploadedFileName",request.getParameter("uploadedFileName"));
		model.addAttribute("requestType",request.getParameter("requestType"));
		model.addAttribute("requestSubType",request.getParameter("requestSubType"));
		LOGGER.debug("ManageTemplateController.actionReviewTemplateRequest Exit : ");
		
		response.setRenderParameter("action", "reviewForm");

	}
		
	
	
	
	/**.
	 * This method submits the template based SR along with the attachment to 
	 * the back end system.
	 * @param request ActionRequest
	 * @param response ActionResponse
	 * @param templateRequestForm CMTemplateRquestForm
	 * @param bindingResult BindingResult
	 * @param model Model
	 * @throws IOException
	 */	
	@RenderMapping(params = "action=reviewForm")
	public String renderReviewTemplateRequest(
			RenderRequest request,
			RenderResponse response,
			@ModelAttribute("templateRequestForm") CMTemplateRquestForm templateRequestForm,		
			BindingResult bindingResult, Model model) throws IOException {
		
		LOGGER.debug("ManageTemplateController.renderReviewTemplateRequest Enter : ");		
		LOGGER.debug("request type::: "+ request.getParameter("requestType"));		
		LOGGER.debug("request sub type::: "+ request.getParameter("requestSubType"));
		LOGGER.debug("uploadedFileName::: "+ request.getParameter("uploadedFileName"));

		LOGGER.debug("ManageTemplateController.renderReviewTemplateRequest Exit : ");
		return "changemanagement/multipleRequest/manageMultipleSRReview";
	
	}
	
	/**.
	 * This method submits the SR data.
	 * @param request
	 * @param response
	 * @param templateRequestForm
	 * @param model
	 * @throws IOException
	 */
	@RequestMapping(params = "action=submitTemplateSRForm")
	public void submitTemplateSRForm(ActionRequest request,ActionResponse response,
			@ModelAttribute("templateRequestForm") CMTemplateRquestForm templateRequestForm,
			Model model) throws IOException {
		LOGGER.debug("ManageTemplateController.submitTemplateSRForm Enter : "+templateRequestForm);		

		LOGGER.debug("request type::: "+ templateRequestForm.getRequestType());		
		LOGGER.debug("request sub type::: "+ templateRequestForm.getRequestSubType());
		
		LOGGER.debug("Previous SR number::: "+ templateRequestForm.getPrevSRNumber());
		
		if (PortalSessionUtil.isDuplicatedSubmit(request,
				templateRequestForm)) {
			LOGGER.debug("ManageTemplateController.submitTemplateSRForm.duplicated submit, do nothing!");		
			model.addAttribute("error", ServiceStatusErrorMessageUtil.getErrorMessage(ERROR_MESSAGE_BUNDLE,
					"exception.serviceRequest.duplicateSubmission", null, request.getLocale()));
			response.setRenderParameter("action", "manageMultipleSRConfirmation");
			
		}else{
		
		
		CrmSessionHandle crmSessionHandle = null;
		PortletSession session = request.getPortletSession();
		
		
		CreateServiceRequestContract contract = commonController.getServiceReqContract(templateRequestForm, request);
		logger.info("start printing lex logger");
		ObjectDebugUtil.printObjectContent(contract, logger);
		logger.info("end printing lex logger");
		
		populateContractData(contract, request,templateRequestForm.getRequestType(),templateRequestForm.getRequestSubType());
		
		LOGGER.debug("Area -->"+contract.getServiceRequest().getArea().getValue());
		LOGGER.debug("SubArea -->"+contract.getServiceRequest().getSubArea().getValue());
		if(templateRequestForm.getPrevSRNumber()!=null && !templateRequestForm.getPrevSRNumber().isEmpty()){
			contract.getServiceRequest().setServiceRequestNumber(templateRequestForm.getPrevSRNumber());
		}
		
		try {
			LOGGER.debug("ManageTemplateController.submitTemplateSRForm.Creating SERVICE-REQUEST ----->");
			LOGGER.debug("-------------- getting account information in do webservice call for templates --------------");
			
			@SuppressWarnings("unchecked")
			Map<String, FileObject> fileMap = (Map<String, FileObject>) session.getAttribute(ChangeMgmtConstant.SESSION_FILE_MAP);
			
			Map<String,String> accDetails=(Map<String,String>)session.getAttribute("accountCurrentDetails",PortletSession.APPLICATION_SCOPE);
			if(accDetails!=null)
			{	
			LOGGER.debug("Account id ::::   "+accDetails.get("accountId"));
			LOGGER.debug("Account Name ::::   "+accDetails.get("accountName"));
			LOGGER.debug("accountOrganization ::::   "+accDetails.get("accountOrganization"));
			LOGGER.debug("agreementId ::::   "+accDetails.get("agreementId"));
			}
			else{
				LOGGER.debug("acc details map is null, so creating an empty map");
				accDetails=new HashMap<String, String>();//Go for creation of an empty map
			}
			
			long timeBeforeCall=System.currentTimeMillis();
			CreateServiceRequestResult result = createLgsServiceRequest.createChangeManagementServiceRequest(contract,accDetails);
			long timeAfterCall=System.currentTimeMillis();
			
			PerformanceUtil.calcTime(perfLogger, PerformanceConstant.MANAGETEMPLATE_MSG_CREATECHANGEMANAGEMENTSERVICEREQUEST, timeBeforeCall,timeAfterCall, PerformanceConstant.SYSTEM_WEBMETHODS,contract);
			
			if( result==null || result.getServiceRequestNumber()==null){
				LOGGER.debug("Exception occured while creating Template based SR : Redirecting back to review page");
				model.addAttribute("templateRequestForm",templateRequestForm);
				model.addAttribute("uploadedFileName",fileMap);
				model.addAttribute("error","System could not create the request. Please try again.");
				response.setRenderParameter("action", "reviewForm");
			}
					
			
			LOGGER.debug("ManageTemplateController.submitTemplateSRForm.ServiceRequestRowId---->"+result.getServiceRequestRowId());
			LOGGER.debug("ManageTemplateController.submitTemplateSRForm.ServiceRequestNumber---->"+result.getServiceRequestNumber());
			model.addAttribute("requestNumber", result.getServiceRequestNumber());
			
			
			/** Code for File Upload begins */	
			
			LOGGER.debug("File upload process begins");
			if(fileMap != null && result!=null && result.getServiceRequestRowId()!= null){
					
	                List<Attachment> attachmentList = new ArrayList<Attachment>();
	                Set<String> fileNames = fileMap.keySet();
					Iterator<String> iterator = fileNames.iterator();
			        while (iterator.hasNext()) {
			        		String fileName =  iterator.next();
			                
			        		FileObject fileDetails = fileMap.get(fileName);
			        		LOGGER.debug("File Details Object" + fileDetails);
			        		Attachment file = new Attachment();
			        		file.setVisibility(PortalSessionUtil.getUserType(session));
			        		
			        		file.setAttachmentName(fileDetails.getFileName());
			        		file.setSize(Integer.parseInt(fileDetails.getFileSizeInBytes()));
			        		LOGGER.debug("File name ::: " + fileDetails.getFileName());
			        		LOGGER.debug("File size ::: " + fileDetails.getFileSize());
			        		attachmentList.add(file);
			        }
	        		crmSessionHandle = globalService.initCrmSessionHandle(PortalSessionUtil.getSiebelCrmSessionHandle(request));
	        		AttachmentContract atchmntContract = new AttachmentContract();
	        		atchmntContract.setSessionHandle(crmSessionHandle);
	        		if(result!= null){
	        			atchmntContract.setIdentifier(result.getServiceRequestRowId());
	        		}
	        		
	        		atchmntContract.setRequestType(ChangeMgmtConstant.ATTACHMENT_REQUEST_TYPE);//For service request you have to send 
	        		atchmntContract.setAttachments(attachmentList);
	        		
	        		LOGGER.debug("-------------------------- Calling attachment service ----------------------");
					attachmentService.uploadAttachments(atchmntContract);
					LOGGER.debug("-------------------------- After Calling attachment service ----------------------");
		        } 

		}catch (SiebelCrmServiceException e) {
			LOGGER.error("SiebelCrmServiceException occured :" + e.getMessage());		
		} catch (LGSBusinessException e) {
			LOGGER.error("LGSBusinessException occured :" + e.getMessage());			
		} catch (Exception e) {
			LOGGER.error("Exception occured :" + e.getMessage());	
		}finally{
			globalService.releaseSessionHandle(crmSessionHandle);
		}
		
		model.addAttribute("requestSubType",templateRequestForm.getRequestSubType());		

		//this is to enable re-submit of SR form on submit/draft exception
		Long tokenInSession = (Long)session.getAttribute(LexmarkConstants.SUBMIT_TOKEN, session.PORTLET_SCOPE);
		BaseForm baseForm = (BaseForm)templateRequestForm;
		baseForm.setSubmitToken(tokenInSession);
		model.addAttribute(templateRequestForm);
		LOGGER.debug("ManageTemplateController.submitTemplateSRForm.exit :");
		
		response.setRenderParameter("action", "manageMultipleSRConfirmation");
		}
	}
	
	/**.
	 * This method populates the necessary data in contract object.
	 * @param serviceReqContract
	 * @param request
	 */
	private void populateContractData( CreateServiceRequestContract serviceReqContract,
			ActionRequest request,String requestType, String requestSubType){
		LOGGER.debug("ManageTemplateController.populateContractData.Enter : requestType-->"+requestType+"---requestSubType--->"+requestSubType);
		
		
		serviceReqContract.getServiceRequest().setServiceRequestDate(new Date());
		ListOfValues serviceRequestLov = new ListOfValues();
		serviceRequestLov.setValue("Fleet Management");
		serviceReqContract.getServiceRequest().setServiceRequestType(serviceRequestLov);
		
		
		/*
		 * Request type: Fleet Management				Area						Sub Area
		 * -------------------------------			---------------------		----------------------
		 * Asset Multiple -Add							Add Asset Data				Asset Acquisition
		 * Asset Multiple -Change						Change Fleet Data			Asset Data Management 
		 * Asset Multiple -Decomission					Decomission Asset			Asset Decomission 
		 * Asset Multiple -Swap							Change Fleet Data			Asset Data Swap
		 * 
		 * Address Multiple -Add						Add Fleet Data				Address Creation
		 * Address Multiple -Change						Change Fleet Data			Address Data Management 
		 * Address Multiple -Decomission				Decomission Data			Address Decomission
		 * 
		 * Contact Multiple -Add						Add Fleet data				Contact Creation
		 * Contact Multiple -Change						Change Fleet Data			Contact Management 
		 * Contact Multiple -Decomission				Decomission Data			Contact Decomission
		 * 
		 */
		
		ListOfValues areaLov = new ListOfValues();
		ListOfValues subAreaLov = new ListOfValues();
		if(requestType != null && requestSubType != null){
			if(requestSubType.trim().equalsIgnoreCase(ChangeMgmtConstant.REQ_TYPE_ASSETS)){				
				if(requestType.trim().equalsIgnoreCase(ChangeMgmtConstant.REQ_TYPE_ADD)){
					/*Changed by Sourav - as per latest Area SubArea values
					 * areaLov.setValue("Add Asset Data");
					subAreaLov.setValue("Asset Acquisition");*/
					areaLov.setValue(ChangeMgmtConstant.ADDASSETAREA);
					subAreaLov.setValue(ChangeMgmtConstant.ADDASSETSUBAREA);
					
				}else if(requestType.trim().equalsIgnoreCase(ChangeMgmtConstant.REQ_TYPE_CHANGE)){
					/*Changed by Sourav - as per latest Area SubArea values
					*areaLov.setValue("Change Fleet Data");
					subAreaLov.setValue("Asset Data Management");*/
					areaLov.setValue(ChangeMgmtConstant.CHANGEASSETAREA);
					subAreaLov.setValue(ChangeMgmtConstant.CHANGEASSETSUBAREA);
					
				}else if(requestType.trim().equalsIgnoreCase(ChangeMgmtConstant.REQ_TYPE_DECOMM)){
					/*Changed by Sourav - as per latest Area SubArea values
					areaLov.setValue("Decommission Asset");
					subAreaLov.setValue("Asset Decommission");*/
					areaLov.setValue(ChangeMgmtConstant.DECOMMASSETAREA);
					subAreaLov.setValue(ChangeMgmtConstant.DECOMMASSETSUBAREA);
					
				}else if(requestType.trim().equalsIgnoreCase(ChangeMgmtConstant.REQ_TYPE_SWAP)){
					/*Changed by Sourav - as per latest Area SubArea values
					areaLov.setValue("Change Fleet Data");
					subAreaLov.setValue("Asset Data Swap");*/
					areaLov.setValue(ChangeMgmtConstant.SWAPASSETAREA);
					subAreaLov.setValue(ChangeMgmtConstant.SWAPASSETSUBAREA);
				}
			}else if(requestSubType.trim().equalsIgnoreCase(ChangeMgmtConstant.REQ_TYPE_ADDRESS)){				
				if(requestType.trim().equalsIgnoreCase(ChangeMgmtConstant.REQ_TYPE_ADD)){
					/*Changed by Sourav - as per latest Area SubArea values
					areaLov.setValue("Add Fleet Data");
					subAreaLov.setValue("Address Creation");*/
					areaLov.setValue(ChangeMgmtConstant.ADDACCNTDATA);
					subAreaLov.setValue(ChangeMgmtConstant.ADDADDRESSSUBAREA);
					
				}else if(requestType.trim().equalsIgnoreCase(ChangeMgmtConstant.REQ_TYPE_CHANGE)){
					/*Changed by Sourav - as per latest Area SubArea values
					areaLov.setValue("Change Fleet Data");
					subAreaLov.setValue("Address Data Management");*/
					areaLov.setValue(ChangeMgmtConstant.CHANGEACCNTDATA);
					subAreaLov.setValue(ChangeMgmtConstant.CHANGEADDRESSSUBAREA);
					
				}else if(requestType.trim().equalsIgnoreCase(ChangeMgmtConstant.REQ_TYPE_REMOVE)){
					/*Changed by Sourav - as per latest Area SubArea values
					areaLov.setValue("Decommission Data");
					subAreaLov.setValue("Address Decommission");*/
					areaLov.setValue(ChangeMgmtConstant.DECOMMACCNTDATA);
					subAreaLov.setValue(ChangeMgmtConstant.REMOVEADDRESSSUBAREA);
					
				}
			}else if(requestSubType.trim().equalsIgnoreCase(ChangeMgmtConstant.REQ_TYPE_CONTACTS)){				
				if(requestType.trim().equalsIgnoreCase(ChangeMgmtConstant.REQ_TYPE_ADD)){
					/*Changed by Sourav - as per latest Area SubArea values
					areaLov.setValue("Add Fleet Data");
					subAreaLov.setValue("Contact Creation");*/
					areaLov.setValue(ChangeMgmtConstant.ADDACCNTDATA);
					subAreaLov.setValue(ChangeMgmtConstant.ADDCONTACTSUBAREA);
					
				}else if(requestType.trim().equalsIgnoreCase(ChangeMgmtConstant.REQ_TYPE_CHANGE)){
					/*Changed by Sourav - as per latest Area SubArea values
					areaLov.setValue("Change Fleet Data");
					subAreaLov.setValue("Contact Management");*/
					areaLov.setValue(ChangeMgmtConstant.CHANGEACCNTDATA);
					subAreaLov.setValue(ChangeMgmtConstant.CHANGECONTACTSUBAREA);
					
				}else if(requestType.trim().equalsIgnoreCase(ChangeMgmtConstant.REQ_TYPE_REMOVE)){
					/*Changed by Sourav - as per latest Area SubArea values
					areaLov.setValue("Decommission Data");
					subAreaLov.setValue("Contact Decommission");*/
					areaLov.setValue(ChangeMgmtConstant.DECOMMACCNTDATA);
					subAreaLov.setValue(ChangeMgmtConstant.REMOVECONTACTSUBAREA);					
				}
			}
			
		}
		
		serviceReqContract.getServiceRequest().setArea(areaLov);
		serviceReqContract.getServiceRequest().setSubArea(subAreaLov);		
		
	}
	
	/**
	 * The method renders the confirmation page of template based SR.
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "action=manageMultipleSRConfirmation")
	public String showMultipleSRConfirmationPage(Model model, RenderRequest request,
			RenderResponse response) throws LGSCheckedException, LGSRuntimeException {
		LOGGER.debug("Inside showMultipleSRConfirmationPage >> ");
		return "changemanagement/multipleRequest/manageMultipleSRConfirmation";
	}
	
	
	/**.
	 * This method uploads the template to FTP location and sends the file size and
	 * file name back to the view.
	 * 
	 * @param request
	 * @param response
	 * @param content
	 * @param model
	 * @throws IOException
	 * @throws LGSBusinessException 
	 */
	@ActionMapping(params = "action=uploadAttachment")
	public void uploadAttachment(ActionRequest request,ActionResponse response,
			Model model, @ModelAttribute("fileUploadForm")  @Valid  FileUploadForm 
			fileUploadForm , BindingResult result) throws IOException, LGSBusinessException {
		
			LOGGER.debug("ManageTemplateController.uploadAttachment.Enter.....");
			
			cmTemplateFormValidator.validate(fileUploadForm, result);
			if(result.hasErrors()){
				List<ObjectError> allErrorList = result.getAllErrors();
				for(ObjectError err:allErrorList){
					LOGGER.debug("Error --"+err.getCode());
				}
				
			}
				
					
			PortletSession session = request.getPortletSession();
			
			@SuppressWarnings("unchecked")
			Map<String, FileObject> fileMap = (Map<String, FileObject>) session.getAttribute(ChangeMgmtConstant.SESSION_FILE_MAP);
			LOGGER.debug("fileMap --->"+fileMap);
			
			if(fileMap == null){
				fileMap = new LinkedHashMap<String, FileObject>();
			}
			
			LOGGER.debug("fileUploadForm filedata --->"+fileUploadForm.getFileData());
			
			if( fileUploadForm.getFileData() != null ){
				
				String fileSize="0"; 
				LOGGER.debug("File size in Byte---->"+fileUploadForm.getFileData().getSize());	
				
				LOGGER.debug("Original File Name ---->"+fileUploadForm.getFileData().getOriginalFilename());
				if(fileUploadForm.getFileData().getSize()>0){
					fileSize = df.format(new Double(fileUploadForm.getFileData().getSize())/1024);
					LOGGER.debug("File size in KB---->"+fileSize);
					
				}			
				
				FileObject fileObject = new FileObject();
				FileItem fileItem=fileUploadForm.getFileData().getFileItem();
				
				fileObject.setFileName(setTimestampInAttachment(fileUploadForm.getFileData().getOriginalFilename()));				
				fileObject.setFileSize(fileSize);
				fileObject.setFileSizeInBytes(String.valueOf(fileUploadForm.getFileData().getSize()));
				
				if(fileUploadForm.getFileData().getSize()>0 && (fileUploadForm.getFileData().getSize()<1024*1024) &&!result.hasErrors()){
					
					AttachmentProperties fileProperties = new AttachmentProperties(ChangeMgmtConstant.ATTACHMENT_REQUEST_TYPE);
					LOGGER.debug("Starting File Transfer--->" + fileProperties.getFileUploadDestination() + fileObject.getFileName());
					try {
						fileItem.write(new File(fileProperties.getFileUploadDestination() + fileObject.getFileName()));
					} catch (Exception e) {
						LOGGER.debug("Error while uploading file : "+e.getMessage());
					}
					LOGGER.debug("File Transfer is completed.......... ");
				}
				

				fileMap.put(fileUploadForm.getFileData().getOriginalFilename(),fileObject);
				fileUploadForm.setFileMap(fileMap);
				fileUploadForm.setFileCount(fileMap.size());
				session.setAttribute(ChangeMgmtConstant.SESSION_FILE_MAP, fileMap);
				
				
				
				model.addAttribute("errors",result.getAllErrors());
				model.addAttribute("fileUploadForm",fileUploadForm);
				
			}
			
			response.setRenderParameter("action", "backToManageTemplateSRPage");
	
	}
	
	
	private String setTimestampInAttachment(String fName) throws LGSBusinessException{
		
		int index = fName.lastIndexOf(".");
		
		String fExtension = fName.substring(index);
		LOGGER.debug("fExtension ----> "+fExtension);
		
		fName = fName.substring(0, index);
		LOGGER.debug("fName----> "+fName);
		
		String fNameFinal = fName + "_" + System.currentTimeMillis()+ fExtension;
		LOGGER.debug("fNameFinal----> "+fNameFinal);
		
		return fNameFinal;
	}



	/**
	 * The method is called from uploadAttachment method to render the target page in the iframe
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ActionMapping(params = "action=backToManageTemplateSRPage")
	public void backToManageTemplateSRPage(Model model, ActionRequest request,
			ActionResponse response, @ModelAttribute("templateRequestForm") CMTemplateRquestForm
			templateRequestForm, BindingResult result) throws LGSCheckedException, LGSRuntimeException {
		LOGGER.debug("ManageTemplateController.backToManageTemplateSRPage Action phase>> Enter");
		
		
		
		
		model.addAttribute("templateRequestForm", templateRequestForm);
		PortletSession session = request.getPortletSession();
		FileUploadForm fileUploadForm = new FileUploadForm();
		@SuppressWarnings("unchecked")
		Map<String, FileObject> fileMap = (Map<String, FileObject>) session.getAttribute(ChangeMgmtConstant.SESSION_FILE_MAP);
		fileUploadForm.setFileMap(fileMap);
		if(fileMap != null){
			fileUploadForm.setFileCount(fileMap.size());
		}
		
		commonController.getContactInformation(request, response);//Added for requestor info
		
		model.addAttribute("fileUploadForm",fileUploadForm);
		
		LOGGER.debug(" ManageTemplateController.backToManageTemplateSRPage Action phase>> Exit");
		response.setRenderParameter("action", "backToManageTemplateSRPage");
	}
	
	
	/**
	 * The method is called from uploadAttachment method to render the target page in the iframe
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(params = "action=backToManageTemplateSRPage")
	public String backToManageTemplateSRPage(Model model, RenderRequest request,
			RenderResponse response, @ModelAttribute("templateRequestForm") CMTemplateRquestForm
			templateRequestForm, BindingResult result) throws LGSCheckedException, LGSRuntimeException {
		LOGGER.debug("ManageTemplateController.backToManageTemplateSRPage Render phase>> Enter");
		LOGGER.debug("TESTTTT-->"+templateRequestForm);
		LOGGER.debug("Inside backToManageTemplateSRPage >> requestType:"+templateRequestForm.getRequestType());
		LOGGER.debug("Inside backToManageTemplateSRPage >> requestSubType:"+templateRequestForm.getRequestSubType());
		model.addAttribute("requestType",templateRequestForm.getRequestType() );
		model.addAttribute("requestSubType",templateRequestForm.getRequestSubType());
		model.addAttribute("requestTypeNew",templateRequestForm.getRequestType() );
		model.addAttribute("requestSubTypeNew",templateRequestForm.getRequestSubType());
		model.addAttribute("templateRequestForm", templateRequestForm);
		PortletSession session = request.getPortletSession();
		FileUploadForm fileUploadForm = new FileUploadForm();
		@SuppressWarnings("unchecked")
		Map<String, FileObject> fileMap = (Map<String, FileObject>) session.getAttribute(ChangeMgmtConstant.SESSION_FILE_MAP);
		fileUploadForm.setFileMap(fileMap);
		if(fileMap != null){
			fileUploadForm.setFileCount(fileMap.size());
		}
		
		
		commonController.getContactInformation(request, response);
		
		model.addAttribute("fileUploadForm",fileUploadForm);
		
		LOGGER.debug("ManageTemplateController.backToManageTemplateSRPage Render phase>> Exit");
		return "changemanagement/multipleRequest/manageMultipleSRHome";
	}
	
	
	/**.
	 * This method delete the attachment from ftp location.
	 * @param request
	 * @param response
	 * @param templateRequestForm
	 * @param fileUploadForm
	 * @param fileName
	 * @throws IOException
	 */
	@RequestMapping(params = "action=deleteAttachment")
	public void deleteAttachment(ActionRequest request,ActionResponse response,@ModelAttribute("templateRequestForm") CMTemplateRquestForm
			templateRequestForm,@ModelAttribute("fileUploadForm") FileUploadForm 
			fileUploadForm,  @RequestParam(value="fileName") String fileName,Model model) throws IOException {
		LOGGER.debug("ManageTemplateController.deleteAttachment.Enter-->"+fileName);
		
		PortletSession session = request.getPortletSession();
		try{
			
			AttachmentProperties fileProperties = new AttachmentProperties(ChangeMgmtConstant.ATTACHMENT_REQUEST_TYPE);
			File removableFile = new File(fileProperties.getFileUploadDestination() + fileName);
			LOGGER.debug("Deleting file --->"+fileProperties.getFileUploadDestination() + fileName);
			
			if( removableFile.delete()){
				
				fileUploadForm.setFileMap(null);
				fileUploadForm.setFileCount(0);
				session.removeAttribute(ChangeMgmtConstant.SESSION_FILE_MAP);
				model.addAttribute("fileUploadForm",fileUploadForm);
				
			}else{
				throw new LGSRuntimeException("The attachment could not be removed.");
			}
		}catch (LGSRuntimeException e) {
			LOGGER.debug("LGSRuntimeException occurred ::: "+ e.getMessage());
			
		}
		
		
		LOGGER.debug("ManageTemplateController.deleteAttachment.Exit.");	
		response.setRenderParameter("action", "backToManageTemplateSRPage");
	}

	
	/**.
	 * 
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
	    
	    String requestType = request.getParameter("requestType");
		String requestSubType = request.getParameter("requestSubType");
		LOGGER.debug("ManageTemplateController.downloadTemplate Enter : requestType--"+requestType);
		LOGGER.debug("ManageTemplateController.downloadTemplate Enter : requestSubType--"+requestSubType);
		
		if(requestType != null && requestSubType != null){
			 if(requestSubType.equalsIgnoreCase("Assets")){
				 String localizedFileName = DownloadFileLocalizationUtil.encodeDecodeFileName(PropertiesMessageUtil.getPropertyMessage(LexmarkSPConstants.MESSAGE_BUNDLE_NAME,"multiple.template.sr", request.getLocale()));
				 response.setProperty("Content-disposition", "attachment; filename=\"" + localizedFileName +"\"");	
				 response.setCharacterEncoding("utf-8");
			 }
			 else if(requestSubType.equalsIgnoreCase("Addresses") || requestSubType.equalsIgnoreCase("Contacts")){
			 response.setProperty("Content-disposition", "attachment; filename=\"" + fileName +"\"");
			 }
		}
				
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
        	response.setContentType("application/vnd.ms-excel;charset=utf-8");
        	LOGGER.debug("Content Type = " + response.getContentType());
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
			  LOGGER.debug("fullPath " + fullPath);
			  InputStream inputStream= new FileInputStream(fullPath);
			  LOGGER.debug("fullPath " + fullPath);
			  OutputStream out = response.getPortletOutputStream();
			  byte buf[]=new byte[1024];
			  int len=0;
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
	
	
	/**
	 * Renders the email confirmation page for multiple SR
	 * @param req
	 * @param resp
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "action=multipleSRemailConfirmationPage")
	public String renderMultipleSRemailConfirmationPage(RenderRequest req, RenderResponse resp, Model model) {
		
		return "changemanagement/multipleRequest/multipleSREmailTemplatePage";
	}
	
	/*Added for CI7 BRD14-02-12*/
	/**
	 * @param request 
	 * @param response 
	 * @param model 
	 * @throws Exception 
	 */
	@RequestMapping(params = "action=showAssetPage")
	public void showAssetPage(ActionRequest request, ActionResponse response, Model model) throws Exception{
		LOGGER.debug("------------------ RequestMapping of the showAssetPage -----------------------");
		LOGGER.debug("friendlyURl is"+request.getParameter("friendlyURL"));
		response.sendRedirect(request.getParameter("friendlyURL"));
	}
	
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
	
	/**
	 * @param request 
	 * @param response 
	 * @param model 
	 * @throws Exception 
	 */
	@RequestMapping(params = "action=showAddressPage")
	public void showAddressPage(ActionRequest request, ActionResponse response, Model model) throws Exception{
		LOGGER.debug("------------------ RequestMapping of the showAddressPage -----------------------");
		LOGGER.debug("friendlyURl is"+request.getParameter("friendlyURL"));
		response.sendRedirect(request.getParameter("friendlyURL"));
	}
	/*END*/


	/**
	 * @return the templateNameMap
	 */
	public Map<String, String> getTemplateNameMap() {
		return templateNameMap;
	}

	/**
	 * @param templateNameMap the templateNameMap to set
	 */
	public  void setTemplateNameMap(Map<String, String> templateNameMap) {
		this.templateNameMap = templateNameMap;
	}

	

	/**
	 * @return the srTemplateDownloadLocation
	 */
	public String getSrTemplateDownloadLocation() {
		return srTemplateDownloadLocation;
	}

	/**
	 * @param srTemplateDownloadLocation the srTemplateDownloadLocation to set
	 */
	public void setSrTemplateDownloadLocation(String srTemplateDownloadLocation) {
		this.srTemplateDownloadLocation = srTemplateDownloadLocation;
	}

	/**
	 * @return the cmTemplateFormValidator
	 */
	public CMTemplateFormValidator getCmTemplateFormValidator() {
		return cmTemplateFormValidator;
	}

	/**
	 * @param cmTemplateFormValidator the cmTemplateFormValidator to set
	 */
	public void setCmTemplateFormValidator(
			CMTemplateFormValidator cmTemplateFormValidator) {
		this.cmTemplateFormValidator = cmTemplateFormValidator;
	}
	
}
