/**
 * Copyright@ 2012. This document has been created & written by
 * Wipro technologies for Lexmark and this is copyright to Lexmark
 *
 * File         	: ManageAddressesController.java
 * Package     		: com.lexmark.services.portlet.cm
 * Creation Date 	: 
 *
 * Modification History:
 *
 * ---------------------------------------------------------------------
 * Author 			Date				Version  		Comments
 * ---------------------------------------------------------------------
 * wipro							1.0				Initial Version
 *
 */


package com.lexmark.services.portlet.cm;

import static com.lexmark.services.LexmarkSPConstants.ERROR_MESSAGE_BUNDLE;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import javax.annotation.Resource;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.contract.CreateServiceRequestContract;
import com.lexmark.domain.AccountContact;
import com.lexmark.domain.GenericAddress;
import com.lexmark.domain.ServiceRequest;
import com.lexmark.domain.ServiceRequestDTO;
import com.lexmark.exceptionimpl.checked.LGSCheckedException;
import com.lexmark.exceptionimpl.checked.business.LGSBusinessException;
import com.lexmark.exceptionimpl.runtime.LGSRuntimeException;
import com.lexmark.framework.logging.LEXLogger;
import com.lexmark.result.CreateServiceRequestResult;
import com.lexmark.services.api.cm.ManageAddressService;
import com.lexmark.services.form.ManageAddressForm;
import com.lexmark.services.portlet.BaseController;
import com.lexmark.services.portlet.common.CommonController;
import com.lexmark.services.util.ChangeMgmtConstant;
import com.lexmark.services.util.ObjectDebugUtil;
import com.lexmark.services.util.PerformanceConstant;
import com.lexmark.services.util.PerformanceUtil;
import com.lexmark.services.util.PortalSessionUtil;
import com.lexmark.services.util.ServiceStatusErrorMessageUtil;
import com.lexmark.util.DateUtil;
import com.lexmark.util.StringUtil;
import com.liferay.portal.util.PortalUtil;
//Added for 14-02-03
//import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
//import javax.validation.Valid;
import com.lexmark.contract.AttachmentContract;
import com.lexmark.domain.Attachment;
import com.lexmark.services.form.AttachmentForm;
import com.lexmark.services.form.FileUploadForm;
import com.lexmark.service.api.AttachmentService;

/**
 * @author wipro Controls all the request for ADDRESSES
 * @version 2.1
 */
@Controller
@RequestMapping("VIEW")
@SessionAttributes(ChangeMgmtConstant.ADDRESSDETAILSOLD)
public class ManageAddressesController extends BaseController {
	@Autowired
	private CommonController commonController;
	@Autowired
	private ManageAddressService manageAddressService;
	@Autowired
	private AttachmentService attachmentService;//Added for CI 14-02-03
	private static final LEXLogger LOGGER = LEXLogger.getLEXLogger(ManageAddressesController.class);
	private static final LEXLogger LEXLOGGER = LEXLogger.getLEXLogger(ManageAddressesController.class);
	private static Logger perfLogger = LogManager.getLogger("performance");
	private static final String CLASS_NAME = "ManageAddressesController";
	private static final String AREAINSUBMIT = "Area in submit";
	private static final String SUBAREAINSUBMIT = "Sub Area in submit";
	@Resource
	private Map<String, String> addressDetailsOld;
	//Added for CI 14-02-03 START
	private String listOfFileTypes;
	private String attMaxCount;
	/**
	 * @return String 
	 */
	public String getListOfFileTypes() {
		return listOfFileTypes;
	}

	/**
	 * @param listOfFileTypes 
	 */
	public void setListOfFileTypes(String listOfFileTypes) {
		this.listOfFileTypes = listOfFileTypes;
	}

	/**
	 * @return String 
	 */
	public String getAttMaxCount() {
		return attMaxCount;
	}

	/**
	 * @param attMaxCount 
	 */
	public void setAttMaxCount(String attMaxCount) {
		this.attMaxCount = attMaxCount;
	}
	//Added for CI 14-02-03 ENDS

	/**
	 * @param binder
	 */

	/**
	 * @param binder 
	 * @param locale 
	 */
	@InitBinder(value = { ChangeMgmtConstant.ADDRESSFORM})
	protected void initBinder(WebDataBinder binder, Locale locale) {
		String METHOD_NAME = "initBinder";
		LOGGER.enter(CLASS_NAME, METHOD_NAME);
		LOGGER.debug("target is " + binder.getTarget().toString()+"Language is " + locale.getLanguage()
				+"country is " + locale.getCountry()+"format is " + DateUtil.getDateFormatByLang(locale.getLanguage()));
	
			
		DateFormat sdf=new SimpleDateFormat(DateUtil.getDateFormatByLang(locale.getLanguage()));
		sdf.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
		
		LOGGER.exit(CLASS_NAME, METHOD_NAME);
	}

	/**
	 * Displays the initial Address Grid.
	 * 
	 * @param model 
	 * @param request 
	 * @param response 
	 * @return String Grid page 
	 * 
	 */
	@RequestMapping
	public String viewAddressListDefault(Model model, RenderRequest request,RenderResponse response){
		String METHOD_NAME = "viewAddressListDefault";
		LOGGER.enter(CLASS_NAME, METHOD_NAME);
		Boolean isUpdateFlag=false;
		
		//This is for the update of sr
		
		HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));
		String prevSRNoForUpdate = httpReq.getParameter(ChangeMgmtConstant.SERVICEREQNO);
			
		if(!StringUtil.isEmpty(httpReq.getParameter(ChangeMgmtConstant.UPDATEFLAG))){
			isUpdateFlag= Boolean.parseBoolean(httpReq.getParameter(ChangeMgmtConstant.UPDATEFLAG));
			LOGGER.debug("Update flag is " + isUpdateFlag);
					
			request.setAttribute(ChangeMgmtConstant.ISUPDATEFLAG, isUpdateFlag);
		}
				
		//This is for the update request srno
		if(!StringUtil.isEmpty(prevSRNoForUpdate)){
			LOGGER.info("Service req no is " + prevSRNoForUpdate);
			request.setAttribute(ChangeMgmtConstant.SERVICEREQNO, prevSRNoForUpdate);
		}
				
		// Sets Contact id to the model for My Bookmark Address page
		model.addAttribute(ChangeMgmtConstant.CONTACTID, PortalSessionUtil.getContactId(request
				.getPortletSession()));
		
		retrieveGridSetting("serviceAddressGridbox_mainPage",request,response,model);//Loading data from db
		
		LOGGER.exit(CLASS_NAME, METHOD_NAME);
		return ChangeMgmtConstant.VIEWADDRESSLIST;
	}

	
	//		For Email and Print
	
	/**
	 * @return String 
	 */
	@RequestMapping(params = "action=printAddress")
	public String showAddressPrint(){
		String METHOD_NAME = "showAddressPrint";
		LOGGER.enter(CLASS_NAME, METHOD_NAME);
		LOGGER.exit(CLASS_NAME, METHOD_NAME);
		return ChangeMgmtConstant.PRINTADDRESS;
	}
	/**
	 * @return String 
	 */
	@RequestMapping(params = "action=emailAddress")
	public String showAddressEmail(){
		String METHOD_NAME = "showAddressEmail";
		LOGGER.enter(CLASS_NAME, METHOD_NAME);
		LOGGER.exit(CLASS_NAME, METHOD_NAME);
		return ChangeMgmtConstant.ADDRESSEMAIL;
	}
	
	/**
	 * Displays the add new Address page.
	 * 
	 * @param model 
	 * @param request 
	 * @param response 
	 * @return String add New Address page 
	 *
	 * */
	@RenderMapping(params = "action=addAddress")
	public String addAddress(Model model, RenderRequest request,
			RenderResponse response){
		String METHOD_NAME = "addAddress";
		LOGGER.enter(CLASS_NAME, METHOD_NAME);
		//Added for CI 14-02-03 STARTS------
		PortletSession session = request.getPortletSession();
		LOGGER.debug("request.getParameter"+ request.getParameter("flowType"));
		if(!("back".equalsIgnoreCase(request.getParameter("flowType")))){
			session.removeAttribute("attachmentList");
			session.removeAttribute("attachmentForm");
			AttachmentForm attachForm = new AttachmentForm();
			attachForm.setListOfFileTypes("csv,xls,xlsx,vsd,doc,docx,ppt,pptx,pdf,zip");
			attachForm.setAttMaxCount("2");
			model.addAttribute("attachmentForm",attachForm);
		}
		//Added for CI 14-02-03 ENDS--------
		// Below checking is for BACK button as the form will be already set
		// if it is back button.
		if (!model.containsAttribute(ChangeMgmtConstant.ADDRESSFORM)) {
			LOGGER.debug("inside add address");
			try{
			ManageAddressForm addAddressForm = createAddressForm(request,
					response);
			model.addAttribute(ChangeMgmtConstant.ADDRESSFORM, addAddressForm);
			
			}catch(LGSRuntimeException lgsRuntimeException){
				LOGGER.error("Exception occured while creating new address form");
				model.addAttribute(ChangeMgmtConstant.EXCEPTIONOCCURED, ChangeMgmtConstant.TRUEATTR);
				lgsRuntimeException.getMessage();
				return ChangeMgmtConstant.VIEWADDRESSLIST;
			}
			
		}
		
		LOGGER.exit(CLASS_NAME, METHOD_NAME);
		return ChangeMgmtConstant.ADDADDRESSPAGE;
	}

	/**
	 * Displays Address Summary page for Add,Edit,Delete.
	 * 
	 * @param request
	 * @param response
	 * @return String Address Summary page
	 * 
	 * */

	/**
	 * @param request 
	 * @param response 
	 * @return String 
	 */
	@RenderMapping(params = "action=showSummaryPage")
	public String showAddressSummary(RenderRequest request,
			RenderResponse response){
		String METHOD_NAME = "showAddressSummary";
		LOGGER.enter(CLASS_NAME, METHOD_NAME);
		LOGGER.exit(CLASS_NAME, METHOD_NAME);
		return ChangeMgmtConstant.ADDRESSUMMARY;
	}

	/**
	/**
	 * Submit's Address for ADD,Delete,Change Address 
	 * @param model 
	 * @param actRequest 
	 * @param actResponse 
	 * @param sessionStatus 
	 * @param attachForm 
	 * @param addressForm 
	 */ 
	@SuppressWarnings("unchecked")
	@ActionMapping(params = "action=submitAddress")
	public void addressSubmit(Model model, ActionRequest actRequest,
			ActionResponse actResponse, SessionStatus sessionStatus,
			@ModelAttribute ("attachmentForm") AttachmentForm attachForm,//Added for CI 14-02-03
			@ModelAttribute(ChangeMgmtConstant.ADDRESSFORM) ManageAddressForm addressForm){
		String METHOD_NAME = "addressSubmit";
		LOGGER.enter(CLASS_NAME, METHOD_NAME);
		LOGGER.debug(AREAINSUBMIT + addressForm.getArea()+ SUBAREAINSUBMIT + addressForm.getSubArea());
				
		PortletSession session = actRequest.getPortletSession();
		//Added for CI 14-02-03 START
		List<Attachment> attachmentList = (ArrayList<Attachment>) session.getAttribute("attachmentList");
		
		if (attachmentList != null){
			attachForm.setAttachmentList(attachmentList);
		}
		attachForm.setAttachmentDescription(addressForm.getAttachmentDescription());
		
		//Added for CI 14-02-03 ENDS
		Map<String, String> accDetails = (Map<String, String>) session
				.getAttribute(ChangeMgmtConstant.ACNTCURRDETAILS,
						PortletSession.APPLICATION_SCOPE);
		if (accDetails != null) {
			LOGGER.debug("[Account id=" + accDetails.get("accountId")+"Account Name=" + accDetails.get("accountName")
							+"Account Organization="+ accDetails.get("accountOrganization")
							+"Agreement Id=" + accDetails.get("agreementId")
							+"Agreement Name="+ accDetails.get("agreementName")
							+"Country=" + accDetails.get("country"));
		}
		else{
			accDetails=new HashMap<String, String>();//Go for creation of an empty map
		}
		
		// sets the form submitted to the model for showing SUMMARY page
		model.addAttribute(ChangeMgmtConstant.ADDRESSFORM, addressForm);
		
		// Checking done if the user clicks on BACK
		if (ChangeMgmtConstant.TRUE.equals(actRequest.getParameter(ChangeMgmtConstant.ISBACK))) {
			commonController.getContactInformation(actRequest, actResponse);
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
			actResponse.setRenderParameter("flowType","back");
			// checking the flag if its for changeAddress Back page.
			if (ChangeMgmtConstant.TRUE.equals(actRequest.getParameter(ChangeMgmtConstant.ISCHANGE))) {
				// redirecting to appropriate page
				actResponse.setRenderParameter(ChangeMgmtConstant.ACTION, ChangeMgmtConstant.CHANGEADDRESS);
			}
			// checking the flag if its for removeAddress Back page.
			else if (ChangeMgmtConstant.TRUE.equals(actRequest.getParameter(ChangeMgmtConstant.ISREMOVE))) {
				actResponse.setRenderParameter(ChangeMgmtConstant.ACTION, ChangeMgmtConstant.REMOVEADDRESS);
			}
			// checking the flag if its for addAddress Back page.
			else {
				actResponse.setRenderParameter(ChangeMgmtConstant.ACTION, ChangeMgmtConstant.ADDADDRESS); 
			}
		} else {
			// if user has not clicked on BACK. So Submit the ADDress and show
			// the summary page
			// Gathering data into Bean
			model.addAttribute("attachmentForm", attachForm);
			// This is to write the old address data for change address
			/****************This section has been added to prevent resubmit by browser refresh ***********/
			if (PortalSessionUtil.isDuplicatedSubmit(actRequest,addressForm)) {
				LOGGER.debug("ManageAdressController duplicated submit, do nothing!");		
				model.addAttribute(ChangeMgmtConstant.ERROR, ServiceStatusErrorMessageUtil.getErrorMessage(ERROR_MESSAGE_BUNDLE,
						ChangeMgmtConstant.DUPLICATESUBMISSION, null, actRequest.getLocale()));
				
				if (actRequest.getParameter(ChangeMgmtConstant.ADDADDRESS) != null){
					actResponse.setRenderParameter(ChangeMgmtConstant.ADDADDRESS, ChangeMgmtConstant.TRUE);
				}
				if (actRequest.getParameter(ChangeMgmtConstant.ISCHANGEADDRESS) != null){
					actResponse.setRenderParameter(ChangeMgmtConstant.ISCHANGEADDRESS, ChangeMgmtConstant.TRUE);
				}
				
				actResponse.setRenderParameter(ChangeMgmtConstant.ACTION, ChangeMgmtConstant.SUMMARYPAGE);
				
				model.addAttribute(addressForm);
			}
			/**************** Section end to prevent resubmit by browser refresh ***********/
			else{
			ServiceRequestDTO serviceReqDTO = new ServiceRequestDTO();
			// This checking is for if the request is for change address then
			// write the old address
			// otherwise not.. for remove address the data is in the form
			
			
			if (addressDetailsOld.get(ChangeMgmtConstant.ISCHANGE) == ChangeMgmtConstant.TRUE) { 
				LOGGER.debug("address old is not null"
						+ addressDetailsOld.get(ChangeMgmtConstant.ADDRESSID));
				GenericAddress genericAddressService = new GenericAddress();
				genericAddressService.setAddressId(addressDetailsOld.get(ChangeMgmtConstant.ADDRESSID));
				genericAddressService.setAddressName(addressDetailsOld.get(ChangeMgmtConstant.ADDRESSNAME));
				genericAddressService.setAddressLine1(addressDetailsOld.get(ChangeMgmtConstant.ADDRESSLINE1));
				genericAddressService.setAddressLine2(addressDetailsOld.get(ChangeMgmtConstant.ADDRESSLINE2));
				genericAddressService.setCity(addressDetailsOld.get(ChangeMgmtConstant.CITY));
				genericAddressService.setCountry(addressDetailsOld.get(ChangeMgmtConstant.COUNTRY));
				genericAddressService.setState(addressDetailsOld.get(ChangeMgmtConstant.STATEGRID));
				genericAddressService.setProvince(addressDetailsOld	.get(ChangeMgmtConstant.PROVINCE));
				genericAddressService.setPostalCode(addressDetailsOld.get(ChangeMgmtConstant.ZIP));
				genericAddressService.setStoreFrontName(addressDetailsOld.get(ChangeMgmtConstant.STOREFRONTNAME));
				serviceReqDTO.setOldAddress(genericAddressService);// Setting address  for writing to csv	 											// Old
				
			}
				
			LOGGER.debug("address old is  null");

			CreateServiceRequestResult result = null;
			FutureTask<String> future = null;

			try {
				try {
					future = commonController.fillDTO(serviceReqDTO,addressForm, actRequest);
				} catch (LGSCheckedException lgsCheckedException) {
					logStackTrace(lgsCheckedException);
					setParametersOnError(actRequest,actResponse);
				
				}

				LOGGER.debug("----After DTO CALL----");
				try {
					LOGGER.debug("----Web Service call Starts----"
							+"Address form area is " + addressForm.getArea()
							+"Address form sub area is " + addressForm.getSubArea());
					
					CreateServiceRequestContract serviceReqContract = commonController
							.getServiceReqContract(addressForm, actRequest);

					LOGGER.info("start printing lex logger");
					
					ObjectDebugUtil.printObjectContent(serviceReqContract, LOGGER);
					
					LOGGER.info("end printing lex loggger");
					long timeBeforeCall=System.currentTimeMillis();
					result = manageAddressService.createCMRequestService(serviceReqContract,accDetails);
					long timeAfterCall=System.currentTimeMillis();
					LOGGER.info("start printing lex logger");
					
					PerformanceUtil.calcTime(perfLogger, PerformanceConstant.MANAGEADDRESSES_MSG_CREATECMREQUESTSERVICE, timeBeforeCall,timeAfterCall, PerformanceConstant.SYSTEM_WEBMETHODS,serviceReqContract);
					LOGGER.info("end printing lex loggger");
					model.addAttribute(ChangeMgmtConstant.MODELATTRSRNO, result.getServiceRequestNumber());
					//Added for 14-02-03 attachment start
					
					String srRowId = result.getServiceRequestRowId();
					
					AttachmentContract contract = new AttachmentContract();
					List <Attachment> createSRAttachmentList = new ArrayList<Attachment>();
					if(attachmentList!=null && !(attachmentList.isEmpty())){
						LOGGER.debug("No of attachment in the reviewAssetOrderForm "+attachmentList.size());
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
							LEXLOGGER.info("start printing lex logger");
							ObjectDebugUtil.printObjectContent(contract, LEXLOGGER);
							LEXLOGGER.info("end printing lex logger");
							
							long timeBeforeCallAttachment=System.currentTimeMillis();
							try{
							attachmentService.uploadAttachments(contract);
							}
							catch (Exception exception) {
								attachForm.setAttachmentList(null);
								LOGGER.info("Exception in Attachment upload.");
								model.addAttribute("attachmentException", "attachfailed");
								exception.getMessage();
							}
							long timeAfterCallAttachment=System.currentTimeMillis();
							LOGGER.info("start printing lex logger");
							
							PerformanceUtil.calcTime(perfLogger, PerformanceConstant.MSG_ATTACHMENT, timeBeforeCallAttachment,timeAfterCallAttachment, PerformanceConstant.SYSTEM_AMIND,contract);
							LOGGER.info("end printing lex loggger");
							
							LOGGER.debug("After calling upload attachment");
						}
					}
					//Added for 14-02-03 attachment ends
				}catch (LGSRuntimeException lgsRuntimeException) {
					logStackTrace(lgsRuntimeException);
					setParametersOnError(actRequest,actResponse);
					
				}catch(LGSBusinessException lgsBusinessException){
					logStackTrace(lgsBusinessException);
					setParametersOnError(actRequest,actResponse);
				}
				LOGGER.debug("----WEB Service call ends----");

				while (!future.isDone()) {
					LOGGER.debug("CSV writing Task is not yet completed======>");
					try {
						Thread.sleep(500);
					} catch (InterruptedException interruptedException) {
						// if any error do not show the summary page
						logStackTrace(interruptedException);
						setParametersOnError(actRequest,actResponse);
					}
				}
				try {
					LOGGER.debug("Here is the result..." + future.get());
					if (future.get() != null) {
						if (result.getServiceRequestRowId() != null
								&& !future.get().equalsIgnoreCase(ChangeMgmtConstant.CSVFAILURE)) {
							commonController.renameAttachment(future.get(), actRequest,
											result.getServiceRequestRowId());
							// After the service call, forward to the manage asset summary
							// page
							sessionStatus.setComplete();
							addressDetailsOld.clear();
							if (actRequest.getParameter(ChangeMgmtConstant.ADDADDRESS) != null){
								actResponse.setRenderParameter(ChangeMgmtConstant.ADDADDRESS, ChangeMgmtConstant.TRUE);
							}
							if (actRequest.getParameter(ChangeMgmtConstant.ISCHANGEADDRESS) != null){
								actResponse.setRenderParameter(ChangeMgmtConstant.ISCHANGEADDRESS, ChangeMgmtConstant.TRUE);
							}
							actResponse.setRenderParameter(ChangeMgmtConstant.ACTION, ChangeMgmtConstant.SUMMARYPAGE);
							//this is to enable re-submit of SR form on submit/draft exception
							
							Long tokenInSession = (Long)session.getAttribute(LexmarkConstants.SUBMIT_TOKEN, PortletSession.PORTLET_SCOPE);						
							addressForm.setSubmitToken(tokenInSession);
							model.addAttribute(addressForm);
							
						} else {
							setParametersOnError(actRequest,actResponse);
						}
					}
				} catch (ExecutionException executionException) {
					logStackTrace(executionException);
					setParametersOnError(actRequest,actResponse);
				} catch (Exception exception) {
					logStackTrace(exception);
					setParametersOnError(actRequest,actResponse);
				}
			} catch (Exception exception) {
				LOGGER.error("request.getparameter is before forwarding"
						+ actRequest.getParameter("isChangeAddress") + actRequest.getParameter("addAddress"));
							
				setParametersOnError(actRequest,actResponse);
				exception.getMessage();
				
			}

		}
	  }
		LOGGER.exit(CLASS_NAME, METHOD_NAME);
	}
	
	/**
	 * This method sets the parameter for addAddress or changeAddress if any error occured 
	 * @param actRequest 
	 * @param actResponse 
	 */
	private void setParametersOnError(ActionRequest actRequest,ActionResponse actResponse){
		String METHOD_NAME = "setParametersOnError";
		LOGGER.enter(CLASS_NAME, METHOD_NAME);
		if (actRequest.getParameter(ChangeMgmtConstant.ADDADDRESS) != null){
			actResponse.setRenderParameter(ChangeMgmtConstant.ADDADDRESS, ChangeMgmtConstant.TRUE);
		}
		if (actRequest.getParameter(ChangeMgmtConstant.ISCHANGEADDRESS) != null){
			actResponse.setRenderParameter(ChangeMgmtConstant.ISCHANGEADDRESS, ChangeMgmtConstant.TRUE);
		}
		actResponse.setRenderParameter(ChangeMgmtConstant.ERROROCCURED, ChangeMgmtConstant.TRUE);
		actResponse.setRenderParameter(ChangeMgmtConstant.ACTION, ChangeMgmtConstant.CONFIRMADDRESS);
		LOGGER.exit(CLASS_NAME, METHOD_NAME);
	}

	
	/**
	 * Confirmation of Address for ADD,Delete,Change Address
	 * 
	 * @param model 
	 * @param request 
	 * @param response  
	 * @param attachForm 
	 * @param addressForm 
	 * @param bindingResult 
	 * @param modelMap 
	 * @return String 
	 */
	@RenderMapping(params = "action=addressConfirm")
	public String addressConfirmation(
			Model model,
			RenderRequest request,
			RenderResponse response,
			@ModelAttribute ("attachmentForm") AttachmentForm attachForm,//Added for CI 14-02-03
			@ModelAttribute(ChangeMgmtConstant.ADDRESSFORM) ManageAddressForm addressForm,
			BindingResult bindingResult, ModelMap modelMap){
		String METHOD_NAME = "addressConfirmation";
		LOGGER.enter(CLASS_NAME, METHOD_NAME);
		model.addAttribute(ChangeMgmtConstant.ADDRESSFORM, addressForm);
		//Added for CI 14-02-03 START
		String attachmentDescription= request.getParameter("attachmentDescriptionID");
		LOGGER.debug("attachmentDescription" + attachmentDescription);
		//Added for CI 14-02-03 ENDS
		
		/* redirect to appropriate confirmation pages according to the flags set
		 in the URL*/
		StringBuffer path =new StringBuffer(ChangeMgmtConstant.MANAGEADDRESS) ;
		String timezoneOffset=request.getParameter(ChangeMgmtConstant.TIMEZNOFFSET);
		
		//Added for CI 14-02-03 START
		PortletSession session = request.getPortletSession();
		
		
			addressForm.setAttachmentDescription(attachmentDescription);
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
		
		
		if(!StringUtil.isEmpty(timezoneOffset)){
			model.addAttribute(LexmarkConstants.TIMEZONE_OFFSET, Float.valueOf(timezoneOffset));
		}
		
		if (ChangeMgmtConstant.TRUE.equals(request.getParameter(ChangeMgmtConstant.ISCHANGEADDRESS))) {
			/*LOGGER.debug("changeADdress::"
					+ addressForm.getServiceRequest().getSecondaryContact()
							.getFirstName());*/
			path.append(ChangeMgmtConstant.CHANGEADDRESSCONFIRMATION);
			
			
		} else if (ChangeMgmtConstant.TRUE.equals(request.getParameter(ChangeMgmtConstant.ADDADDRESS))) {
			
				/*LOGGER.debug("ADD ADDress"
						+ addressForm.getServiceRequest().getSecondaryContact()
								.getFirstName());*/
				/*This part is to check the mandatory fields for add address
				 * if they are not left blank
				 * */
				ValidationUtils.rejectIfEmptyOrWhitespace(bindingResult,
						ChangeMgmtConstant.GENERICADDRESSNAME, ChangeMgmtConstant.EMPTYADDRNAME);
				
				ValidationUtils.rejectIfEmptyOrWhitespace(bindingResult,
						ChangeMgmtConstant.GENERICADDRESSLINE1, ChangeMgmtConstant.EMPTYADDRLINE1);
				
				ValidationUtils.rejectIfEmptyOrWhitespace(bindingResult,
						ChangeMgmtConstant.GENERICCITY, ChangeMgmtConstant.EMPTYCITY);
				
				ValidationUtils.rejectIfEmptyOrWhitespace(bindingResult,
						ChangeMgmtConstant.GENERICCOUNTRY, ChangeMgmtConstant.EMPTYCOUNTRY);
			
			
				
				ValidationUtils.rejectIfEmptyOrWhitespace(bindingResult,
						ChangeMgmtConstant.GENERICPOSTALCODE, ChangeMgmtConstant.EMPTYZIP);
				//Mandatory fields checking ends...
				
			
				// if mandatory fields has errors thn redirect to addAddress page
				if(bindingResult.hasErrors()){
					commonController.getContactInformation(request, response);
					path.append(ChangeMgmtConstant.ADDADDRESS);
				}else{			
					
					path.append(ChangeMgmtConstant.ADDADDRESSCONFIRMATION);
					
				}
				

		} else {
			
			LOGGER.debug("in remove address confirmation");
			path.append(ChangeMgmtConstant.REMOVEADDRESSCONFIRMATION);
			
		

		}
		addressForm.refreshSubmitToken(request);
		LOGGER.exit(CLASS_NAME, METHOD_NAME);
		return path.toString();
	}

	/**
	 * Set Address to session for Delete,Change Address 
	 * @param model 
	 * @param request 
	 * @param response 
	 */
	@ResourceMapping(value = "setOldAddress")
	public void setOldAddress(Model model, ResourceRequest request,
			ResourceResponse response)  {
		String METHOD_NAME = "setOldAddress";
		LOGGER.enter(CLASS_NAME, METHOD_NAME);
		Enumeration<String> paramNames = request.getParameterNames();
		addressDetailsOld = new HashMap<String, String>();
		while (paramNames.hasMoreElements()) {
			String paramName = paramNames.nextElement();
			
			addressDetailsOld.put(paramName, request.getParameter(paramName)); 
		}
		// check if its removeAddress request or changeAddress request
		if (!ChangeMgmtConstant.DELETEADDRESS.equalsIgnoreCase(request.getParameter(ChangeMgmtConstant.STATE))) {
			// this is set to check the diff btwn change/remove
			addressDetailsOld.put(ChangeMgmtConstant.ISCHANGE, ChangeMgmtConstant.TRUE);
			/* If the request is for change address Address Details old is added to the model to be available for
			 session*/

			model.addAttribute(ChangeMgmtConstant.ADDRESSDETAILSOLD, addressDetailsOld);
		}
		PrintWriter out=null;
		try{
			 out = response.getWriter();
			 response.setContentType(ChangeMgmtConstant.CONTENTTYPE);
			 out.print(ChangeMgmtConstant.CSVSUCCESS);
		}catch(IOException ioexception){
			LOGGER.error("Exception occured while writing the response.");
			ioexception.getMessage();
			
		}finally{
			
			out.flush();
			out.close();
		}
		
		LOGGER.exit(CLASS_NAME, METHOD_NAME);

	}

	/**
	 * Show Edit Address page 
	 * @param model 
	 * @param request 
	 * @param response 
	 * @return String 
	 */
	@RenderMapping(params = "action=changeAddress")
	public String changeAddress(Model model, RenderRequest request,
			RenderResponse response){
		String METHOD_NAME = "changeAddress";
		LOGGER.enter(CLASS_NAME, METHOD_NAME);
		//Added for CI 14-02-03 START
		PortletSession session = request.getPortletSession();
		LOGGER.debug("request.getParameter"+ request.getParameter("flowType"));
		if(!("back".equalsIgnoreCase(request.getParameter("flowType")))){
			session.removeAttribute("attachmentList");
			session.removeAttribute("attachmentForm");
			AttachmentForm attachForm = new AttachmentForm();
			attachForm.setListOfFileTypes("csv,xls,xlsx,vsd,doc,docx,ppt,pptx,pdf,zip");
			attachForm.setAttMaxCount("2");
			model.addAttribute("attachmentForm",attachForm);
		}
		//Added for CI 14-02-03 ENDS
		/* check if user clicked on Back, so address form will be defined
		 already*/
		if (!model.containsAttribute(ChangeMgmtConstant.ADDRESSFORM)) {
			LOGGER.debug("in Edit Address");
			try{
			ManageAddressForm changeAddressForm = createAddressForm(request,
					response);
			model.addAttribute(ChangeMgmtConstant.ADDRESSFORM, changeAddressForm);
			
			}catch(LGSRuntimeException lgsRuntimeException){
				LOGGER.error("Exception occured while creating address form");
				model.addAttribute(ChangeMgmtConstant.EXCEPTIONOCCURED, ChangeMgmtConstant.TRUE);
				lgsRuntimeException.getMessage();
				return ChangeMgmtConstant.VIEWADDRESSLIST;
			}
		}
		
		LOGGER.exit(CLASS_NAME, METHOD_NAME);
		return ChangeMgmtConstant.CHANGEADDRESSPAGE;

	}

	
	/**
	 * Show Delete Address page
	 * @param model 
	 * @param request 
	 * @param response 
	 * @return String 
	 */ 
	@RenderMapping(params = "action=removeAddress")
	public String removeAddress(Model model, RenderRequest request,
			RenderResponse response){
		String METHOD_NAME = "removeAddress";
		LOGGER.enter(CLASS_NAME, METHOD_NAME);
		//Added for CI 14-02-03 START
		PortletSession session = request.getPortletSession();
		LOGGER.debug("request.getParameter"+ request.getParameter("flowType"));
		if(!("back".equalsIgnoreCase(request.getParameter("flowType")))){
			session.removeAttribute("attachmentList");
			session.removeAttribute("attachmentForm");
			AttachmentForm attachForm = new AttachmentForm();
			attachForm.setListOfFileTypes("csv,xls,xlsx,vsd,doc,docx,ppt,pptx,pdf,zip");
			attachForm.setAttMaxCount("2");
			model.addAttribute("attachmentForm",attachForm);
		}
		//Added for CI 14-02-03 ENDS
		if (!model.containsAttribute(ChangeMgmtConstant.ADDRESSFORM)) {
			try{
			ManageAddressForm removeAddressForm = createAddressForm(request,
					response);
			
			removeAddressForm.setGenericAddress(new GenericAddress());
			removeAddressForm.getGenericAddress().setAddressId(
					addressDetailsOld.get(ChangeMgmtConstant.ADDRESSID));
			removeAddressForm.getGenericAddress().setAddressName(
					addressDetailsOld.get(ChangeMgmtConstant.ADDRESSNAME));
			removeAddressForm.getGenericAddress().setAddressLine1(
					addressDetailsOld.get(ChangeMgmtConstant.ADDRESSLINE1));
			removeAddressForm.getGenericAddress().setAddressLine2(
					addressDetailsOld.get(ChangeMgmtConstant.ADDRESSLINE2));
			removeAddressForm.getGenericAddress().setCity(
					addressDetailsOld.get(ChangeMgmtConstant.CITY));
			removeAddressForm.getGenericAddress().setCountry(
					addressDetailsOld.get(ChangeMgmtConstant.COUNTRY));
			removeAddressForm.getGenericAddress().setState(
					addressDetailsOld.get(ChangeMgmtConstant.STATEGRID));
			removeAddressForm.getGenericAddress().setProvince(
					addressDetailsOld.get(ChangeMgmtConstant.PROVINCE));
			removeAddressForm.getGenericAddress().setPostalCode(
					addressDetailsOld.get(ChangeMgmtConstant.ZIP));
			removeAddressForm.getGenericAddress().setStoreFrontName(
					addressDetailsOld.get(ChangeMgmtConstant.STOREFRONTNAME));
			removeAddressForm.getGenericAddress().setOfficeNumber(
					addressDetailsOld.get("houseNumber"));
			removeAddressForm.getGenericAddress().setCounty(
					addressDetailsOld.get("county"));
			removeAddressForm.getGenericAddress().setDistrict(
					addressDetailsOld.get("district"));
			model.addAttribute(ChangeMgmtConstant.ADDRESSFORM, removeAddressForm);
			
			}catch(LGSRuntimeException lgsRuntimeException){
				LOGGER.error("Exception occured while creating form");
				model.addAttribute(ChangeMgmtConstant.EXCEPTIONOCCURED, ChangeMgmtConstant.TRUE);
				lgsRuntimeException.getMessage();
				return ChangeMgmtConstant.VIEWADDRESSLIST;
			}
			
			
		}
				
		LOGGER.exit(CLASS_NAME, METHOD_NAME);
		
		return ChangeMgmtConstant.REMOVEADDRESSPAGE;
	}

	/**
	 * creating a new Address form with primary contact set as requestor 
	 * @param request 
	 * @param response 
	 * @return ManageAddressForm 
	 */
	public ManageAddressForm createAddressForm(RenderRequest request,
			RenderResponse response){
		String METHOD_NAME = "createAddressForm";
		LOGGER.enter(CLASS_NAME, METHOD_NAME);
		
					
		ManageAddressForm addressformNew = new ManageAddressForm();
		
		ServiceRequest serviceRequestNew = new ServiceRequest();
		
		AccountContact accContactInfo = commonController.getContactInformation(request, response);
		
		serviceRequestNew.setPrimaryContact(accContactInfo);
		addressformNew.setServiceRequest(serviceRequestNew);
		
		Boolean isUpdateFlag=false;
		
		if(!StringUtil.isEmpty(request.getParameter(ChangeMgmtConstant.ISUPDATEFLAG))){
			//It will be set to true
			isUpdateFlag=Boolean.parseBoolean(request.getParameter(ChangeMgmtConstant.ISUPDATEFLAG));
		}
		
		if(!StringUtil.isEmpty(request.getParameter(ChangeMgmtConstant.PREVSRNO)) && isUpdateFlag){
			addressformNew.setPrevSrNo(request.getParameter(ChangeMgmtConstant.PREVSRNO));
			addressformNew.setUpdateSrFlag(isUpdateFlag);
		}
		LOGGER.exit(CLASS_NAME, METHOD_NAME);
		return addressformNew;
	}
	
	/**
	 * @param address 
	 */
	private void debugAddress(GenericAddress address){
		String addressArr[]={
				"county","officeNumber",
				"stateCode","stateFullName","district",
				"region","latitude","longitude",
				"countryISOCode","savedErrorMessage",
				"storeFrontName","addressLine1","addressLine2",
				"city","country","state","province",
				"postalCode","addressName","addressId",
				"physicalLocation1","physicalLocation2","physicalLocation3","isAddressCleansed"
				};
		for(String s:addressArr){
			try {
				LOGGER.debug(s+" = "+PropertyUtils.getProperty(address, s));
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				LOGGER.debug("Illegal Access Exception");
				e.getMessage();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				LOGGER.debug("Invocation Target Exception");
				e.getMessage();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				LOGGER.debug("No Such Method Exception");
				e.getMessage();
			}
		}
		
	}
	
	/*Added for CI7 BRD14-02-12*/
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

}
