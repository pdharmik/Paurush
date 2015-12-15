/**
 * Copyright@ 2012. This document has been created & written by 
 * Wipro technologies for Lexmark and this is copyright to Lexmark 
 *
 * File         	: SharedPortletController 
 * Package     		: com.lexmark.services.portlet 
 * Creation Date 	: 
 *
 * Modification History:
 *
 * ---------------------------------------------------------------------
 * Author 			Date				Version  		Comments 
 * ---------------------------------------------------------------------
 * Wipro 						 		              
 *
 */
package com.lexmark.services.portlet;

import static com.lexmark.services.LexmarkSPConstants.ERROR_MESSAGE_BUNDLE;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.TimeZone;
import java.util.Vector;

import javax.imageio.ImageIO;
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

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestParam;

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.contract.AddressListContract;
import com.lexmark.contract.AssetContract;
import com.lexmark.contract.AttachmentContract;
import com.lexmark.contract.CheckedEntitlementServiceDetailContract;
import com.lexmark.contract.ContactListContract;
import com.lexmark.contract.CreateServiceRequestContract;
import com.lexmark.contract.FSEAccountListContract;
import com.lexmark.contract.GeographyCountryContract;
import com.lexmark.contract.GeographyStateContract;
import com.lexmark.contract.LocalizedEntitlementServiceDetailContract;
import com.lexmark.contract.LocalizedEntitlementServiceListContract;
import com.lexmark.contract.LocalizedServiceStatusContract;
import com.lexmark.contract.LocationReportingHierarchyContract;
import com.lexmark.contract.PartnerAccountListContract;
import com.lexmark.contract.ProductImageContract;
import com.lexmark.contract.RequestContract;
import com.lexmark.contract.RequestListContract;
import com.lexmark.contract.SRAdministrationListContract;
import com.lexmark.contract.ServiceRequestAssociatedListContract;
import com.lexmark.contract.ServiceRequestContract;
import com.lexmark.contract.ServiceRequestHistoryListContract;
import com.lexmark.contract.SiebelAccountListContract;
import com.lexmark.contract.UserFavoriteAssetContract;
import com.lexmark.domain.Account;
import com.lexmark.domain.AccountContact;
import com.lexmark.domain.Asset;
import com.lexmark.domain.Attachment;
import com.lexmark.domain.CHLNode;
import com.lexmark.domain.Entitlement;
import com.lexmark.domain.EntitlementServiceDetail;
import com.lexmark.domain.FileObject;
import com.lexmark.domain.GenericAddress;
import com.lexmark.domain.GlobalAccount;
import com.lexmark.domain.ServiceRequest;
import com.lexmark.domain.ServiceRequestActivity;
import com.lexmark.domain.ServiceRequestOrderLineItem;
import com.lexmark.domain.ServicesUser;
import com.lexmark.domain.SiebelLocalization;
import com.lexmark.exceptionimpl.checked.business.LGSBusinessException;
import com.lexmark.framework.logging.LEXLogger;
import com.lexmark.result.AddressListResult;
import com.lexmark.result.AssetLocationsResult;
import com.lexmark.result.AssetReportingHierarchyResult;
import com.lexmark.result.AssetResult;
import com.lexmark.result.CheckedEntitlementServiceDetailResult;
import com.lexmark.result.ContactListResult;
import com.lexmark.result.CreateServiceRequestResult;
import com.lexmark.result.FSEAccountListResult;
import com.lexmark.result.GeographyListResult;
import com.lexmark.result.LocalizedEntitlementServiceListResult;
import com.lexmark.result.LocalizedServiceStatusResult;
import com.lexmark.result.PartnerAccountListResult;
import com.lexmark.result.ProductImageResult;
import com.lexmark.result.RequestListResult;
import com.lexmark.result.RequestLocationsResult;
import com.lexmark.result.RequestResult;
import com.lexmark.result.SRAdministrationListResult;
import com.lexmark.result.ServiceRequestListResult;
import com.lexmark.result.ServiceRequestResult;
import com.lexmark.result.SiebelAccountListResult;
import com.lexmark.service.api.AttachmentService;
import com.lexmark.service.api.ContactService;
import com.lexmark.service.api.CrmSessionHandle;
import com.lexmark.service.api.DeviceService;
import com.lexmark.service.api.GeographyService;
import com.lexmark.service.api.GlobalLegalEntityService;
import com.lexmark.service.api.GlobalService;
import com.lexmark.service.api.OrderSuppliesAssetService;
import com.lexmark.service.api.ProductImageService;
import com.lexmark.service.api.RequestTypeService;
import com.lexmark.service.api.ServiceRequestLocale;
import com.lexmark.service.api.ServiceRequestService;
import com.lexmark.service.api.SiebelCrmServiceException;
import com.lexmark.services.LexmarkSPConstants;
import com.lexmark.services.api.CreateServiceRequest;
import com.lexmark.services.form.AdvancedSearchForm;
import com.lexmark.services.form.CreateNewAddressForm;
import com.lexmark.services.form.DeviceDetailForm;
import com.lexmark.services.form.FileUploadForm;
import com.lexmark.services.form.ServiceRequestConfirmationForm;
import com.lexmark.services.form.ServiceRequestForm;
import com.lexmark.services.form.ShipmentForm;
import com.lexmark.services.util.ChangeMgmtConstant;
import com.lexmark.services.util.ContractFactory;
import com.lexmark.services.util.LexmarkUserUtil;
import com.lexmark.services.util.MailUtil;
import com.lexmark.services.util.ObjectDebugUtil;
import com.lexmark.services.util.PaginationUtil;
import com.lexmark.services.util.PerformanceConstant;
import com.lexmark.services.util.PerformanceUtil;
import com.lexmark.services.util.PortalSessionUtil;
import com.lexmark.services.util.ServiceStatusErrorMessageUtil;
import com.lexmark.services.util.ServiceStatusUtil;
import com.lexmark.services.util.TreeGenerator;
import com.lexmark.services.util.XMLEncodeUtil;
import com.lexmark.services.util.XmlOutputGenerator;
import com.lexmark.util.CollectionFilter;
import com.lexmark.util.CollectionSorter;
import com.lexmark.util.DateLocalizationUtil;
import com.lexmark.util.DateUtil;
import com.lexmark.util.PropertiesMessageUtil;
import com.lexmark.util.StringUtil;
import com.lexmark.util.report.PdfPReportGenerator;
import com.liferay.portal.util.PortalUtil;

public class SharedPortletController {

	@Autowired
	private ServiceRequestService serviceRequestService;
	@Autowired
	private RequestTypeService requestTypeService;
	@Autowired
	private ContactService contactService;
	@Autowired
	private DeviceService deviceService;
	@Autowired
	private GlobalService globalService;
	@Autowired
	private CreateServiceRequest createServiceRequest;
	@Autowired
	private ProductImageService productImageService;
	@Autowired
	private ServiceRequestLocale serviceRequestLocaleService;
	@Autowired 
	private GeographyService geographyService; 
    @Autowired
    private ServiceRequestLocale serviceRequestLocale;
    @Autowired
    private GlobalLegalEntityService globalLegalEntityService;
    @Autowired
    private OrderSuppliesAssetService orderSuppliesAssetService;
    @Autowired
    public AttachmentService attachmentService;		// added for MPS breakfix

	private TreeGenerator treeGenerator;
	private String chlNodeXMLURL;
	private String deviceLocationXMLURL;
	private static final String EXCEPTION_DEVICE_FINDER_UPDATE_USER_FAVORITE_ASSET = "exception.deviceFinder.updateUserFavoriteAsset";
	private static final String MESSAGE_DEVICE_FINDER_UPDATE_USER_FAVORITE_ASSET = "message.deviceFinder.updateUserFavoriteAsset";
	public static final String ACCOUNT_TYPE_MPS_FLAG = "accountTypeMPSFlag";
	public static final String ACCOUNT_TYPE_CSS_FLAG = "accountTypeCSSFlag";
	public static final String DIRECT_PARTNER_FLAG = "directPartnerFlag";
	public static final String INDIRECT_PARTNER_FLAG = "indirectPartnerFlag";
	private static final String SHIPMENT = "shipment";
	private static final String RETURN = "return";

	private static String CONFIG_FILE_NAME = "/TrackingLink.properties";
	
	private static Logger logger = LogManager
			.getLogger(SharedPortletController.class);
	private static LEXLogger LEXLOGGER = LEXLogger.getLEXLogger(SharedPortletController.class);
	private static Logger perfLogger = LogManager.getLogger("performance");
	private static String COUNTRYBUNDLE = "com.lexmark.services.resources.location";

	public void showServiceRequestDrillDownPage(RenderRequest request,
			RenderResponse response, Model model) throws Exception {
		logger.debug("-------------serviceRequestDrillDown started---------");
		String serviceRequestNumber = request
				.getParameter("serviceRequestNumber");
		RequestContract contract = new RequestContract();
		contract.setServiceRequestNumber(serviceRequestNumber.trim());
		//added to handle session
		CrmSessionHandle crmSessionHandle = globalService
				.initCrmSessionHandle(PortalSessionUtil
						.getSiebelCrmSessionHandle(request));
		contract.setSessionHandle(crmSessionHandle);
		//ends here
		PortletSession session = request.getPortletSession();
		LEXLOGGER.info("start printing lex logger");
		ObjectDebugUtil.printObjectContent(contract, LEXLOGGER);
		LEXLOGGER.info("end printing lex loggger");
		
		Long startTime = System.currentTimeMillis();

		RequestResult serviceRequestResult = requestTypeService.retrieveSupplyRequestDetail(contract);
		
		LEXLOGGER.logTime("** MPS PERFORMANCE TESTING RETRIEVE Device DETAIL ==>: " + (System.currentTimeMillis()- startTime)/1000.0);
		
		PerformanceUtil.calcTime(perfLogger, PerformanceConstant.HISTORY_MSG_RETRIEVESUPPLYREQUESTDETAIL, startTime,System.currentTimeMillis(), PerformanceConstant.SYSTEM_SIEBEL,contract);
		
		if (serviceRequestResult.getServiceRequest().getAsset() != null) {
		    String imageUrl = retrieveProductImageUrl(serviceRequestResult.getServiceRequest().
			        getAsset().getProductTLI());
		    if(LexmarkConstants.PRODUCT_NOT_FOUND_IMAGE_URL.equalsIgnoreCase(imageUrl)) {
		    	imageUrl = getServerURLPrefix(request) + imageUrl;
		    }
			serviceRequestResult.getServiceRequest().getAsset().setProductImageURL(imageUrl);
		}
		
		serviceRequestResult.getServiceRequest().setServiceRequestStatus(
				localizeServiceRequestStatus(serviceRequestResult.getServiceRequest().getServiceRequestStatus()
						                     , request.getLocale()));
		// localize entitlement service detail
		serviceRequestResult.getServiceRequest().setOtherRequestedService(
				localizeEntitlementServiceDetail(serviceRequestResult.getServiceRequest().
						getOtherRequestedService(), request.getLocale()));
		ServiceRequestForm serviceRequestForm = new ServiceRequestForm();
		//Done for Defect 3924-Jan Release----START
		assembleDevice(serviceRequestResult.getServiceRequest().getAsset(), request.getLocale());
		//Done for Defect 3924-Jan Release----END
		
		//-- CI-6 Changes: Start
					
			List<Attachment> attachmentList = serviceRequestResult.getServiceRequest().getAttachments();
			List<Attachment> modifiedAttachmentList = new ArrayList<Attachment>();
			
			if(attachmentList!=null){
				for(Attachment attachment:attachmentList){
					Attachment modifiedAttachment = new Attachment();
					String displayAttachment = "";
					logger.debug("Attachment name : "+attachment.getAttachmentName());
					logger.debug("Attachment ActivityId : "+attachment.getActivityId());
					logger.debug("Attachment Extension : "+attachment.getExtension());
					logger.debug("Attachment Size : "+attachment.getSize());
					logger.debug("Attachment Size : "+attachment.getStatus());
					logger.debug("Attachment Visibility : "+attachment.getVisibility());
					logger.debug("Attachment CompletedOn : "+attachment.getCompletedOn());
					modifiedAttachment.setAttachmentName(attachment.getAttachmentName()+"."+attachment.getExtension());
					modifiedAttachment.setActivityId(attachment.getActivityId());
					modifiedAttachment.setExtension(attachment.getExtension());
					modifiedAttachment.setSize(attachment.getSize());
					modifiedAttachment.setStatus(attachment.getStatus());
					modifiedAttachment.setVisibility(attachment.getVisibility());
					modifiedAttachment.setCompletedOn(attachment.getCompletedOn());
					modifiedAttachment.setId(attachment.getId());
					double fileSizeDisplay=attachment.getSize();
					logger.debug("File Size is " + fileSizeDisplay);
					fileSizeDisplay=fileSizeDisplay/1024;
					BigDecimal roundedFileSizeDisplay = new BigDecimal(fileSizeDisplay);
					roundedFileSizeDisplay = roundedFileSizeDisplay.divide(new BigDecimal("1"), 2, BigDecimal.ROUND_HALF_UP);
					logger.debug("roundedFileSizeDisplay value is "+roundedFileSizeDisplay);
					modifiedAttachment.setSizeForDisplay(String.valueOf(roundedFileSizeDisplay));
					//start doing the manipulation for display name
					String attachName = attachment.getAttachmentName();
					/* Change Started for LEX:AIR00077062 */
					if(attachName.lastIndexOf('_') > 0){
						String timeStamp = attachName.substring(attachName.lastIndexOf('_')+1, attachName.length());
						if(timeStamp.matches("[0-9]+") && timeStamp.length() == 13){
							displayAttachment = attachName.substring(0,attachName.lastIndexOf('_'));
						}
					}
					 else{
						displayAttachment = attachName ;
					  }
					logger.debug("displayAttachment::"+displayAttachment);
					/* Change Ended for LEX:AIR00077062 */
					displayAttachment = displayAttachment+"."+attachment.getExtension();
					logger.debug("Final displayFileName is "+displayAttachment);
					//end completing the manipulation for display name
					modifiedAttachment.setDisplayAttachmentName(displayAttachment);
					modifiedAttachmentList.add(modifiedAttachment);
				}
			}
			serviceRequestForm.setAttachmentList(modifiedAttachmentList);
		//-- CI-6 Changes: End
		
		serviceRequestForm.setServiceRequest(serviceRequestResult.getServiceRequest());
		setBreadCrumbToSRForm(serviceRequestForm, request.getParameter("pageType"));

		boolean createServiceRequestFlag = isCreateServiceRequestFlag(request);
		serviceRequestForm.setCreateServiceRequestFlag(createServiceRequestFlag);
		
		// retrieveAssociatedServiceTicketList
		ResourceURL srAssociatedURL = response.createResourceURL();
		srAssociatedURL.setResourceID("retrieveAssociatedServiceTicketList");
		if ("lightBox".equals(request.getParameter("lightBox"))) {
			serviceRequestForm.setAssociatedServiceTicketXML(srAssociatedURL
					.toString()
					+ "&serviceRequestNumber="
					+ serviceRequestNumber
					+ "&lightBox=lightBox");
		} else {
			serviceRequestForm.setAssociatedServiceTicketXML(srAssociatedURL
					.toString()
					+ "&serviceRequestNumber=" + serviceRequestNumber);
		}

		// retrieveServiceRequestHistoryList
		ResourceURL srHistoryURL = response.createResourceURL();
		srHistoryURL.setResourceID("retrieveServiceRequestHistoryListDrillDown");
		DeviceDetailForm deviceDetailForm = new DeviceDetailForm();
		String accountRowId = serviceRequestResult.getServiceRequest().getAccountId();
		String assetRowId = serviceRequestResult.getServiceRequest().getAsset().getAssetId();
		deviceDetailForm.setServiceRequestHistoryListXML(srHistoryURL
				.toString()
				+ "&assetRowId="
				+ assetRowId
				+ "&accountRowId="
				+ accountRowId
				+ "&serviceRequestNumber="
				+ serviceRequestNumber);

		// retrieve Service Request Status List
		String[] statusGeneratorPatterns = new String[] { "activityDate",
				"activityStatus", "activityDescription" };
		if (serviceRequestResult.getServiceRequest()
				.getServicewebUpdateActivities() != null
				&& serviceRequestResult.getServiceRequest()
						.getServicewebUpdateActivities().size() > 0) {
			List<ServiceRequestActivity> activityList = assembleServiceRequestActivity(serviceRequestResult.getServiceRequest().getServicewebUpdateActivities());
			String serviceRequestStausXML = getXmlOutputGenerator(request
					.getLocale()).generate(activityList,
					serviceRequestResult.getServiceRequest()
							.getServicewebUpdateActivities().size(), 0,
					statusGeneratorPatterns);
			serviceRequestStausXML = serviceRequestStausXML.replace("\n", "");
			serviceRequestForm
					.setServiceRequestStausXML(serviceRequestStausXML);
		}else{
			serviceRequestForm.setServiceRequestStausXML(getXmlOutputGenerator(request.getLocale()).generateEmptyXML());
		}
		// retrieve Technician List
		
		String[] technicianGeneratorPatterns = new String[] { "activityDate",
				"activityStatus", "activityDescription" };

		if (serviceRequestResult.getServiceRequest()
				.getActivitywebUpdateActivities() != null
				&& serviceRequestResult.getServiceRequest()
						.getActivitywebUpdateActivities().size() > 0) {
			logger.info("!!!!!!!!!!!!!!!That means activity web update activity value is not null");
			List<ServiceRequestActivity> sActivityList = assembleServiceRequestActivity(serviceRequestResult.getServiceRequest().getActivitywebUpdateActivities());
			String serviceRequestTechnicianXML = getXmlOutputGenerator(request
					.getLocale()).generate(sActivityList,
					serviceRequestResult.getServiceRequest()
							.getActivitywebUpdateActivities().size(), 0,
					technicianGeneratorPatterns);
			serviceRequestTechnicianXML = serviceRequestTechnicianXML.replace(
					"\n", "");
			serviceRequestForm
					.setServiceRequestTechnicianXML(serviceRequestTechnicianXML);

		}
		if(serviceRequestResult.getServiceRequest().getServiceActivityStatus()!= null){
			serviceRequestForm.setServiceRequestTechnicianProgress(checkTechnicianProgress(serviceRequestResult.getServiceRequest().getServiceActivityStatus()));
			logger.debug("Activity status:: "+serviceRequestResult.getServiceRequest().getServiceActivityStatus());
		}
		String[] notificationsGeneratorPatterns = new String[] {
				"activityDate", "recipientEmail", "activityDescription" };
		if (serviceRequestResult.getServiceRequest().getEmailActivities() != null
				&& serviceRequestResult.getServiceRequest()
						.getEmailActivities().size() > 0) {
			String serviceRequestNotificationsXML = getXmlOutputGenerator(request.getLocale()).convertServiceRequestNotificationToXML(serviceRequestResult.getServiceRequest().getEmailActivities());

			serviceRequestNotificationsXML = serviceRequestNotificationsXML
					.replace("\n", "");
			serviceRequestForm
					.setServiceRequestNotificationsXML(serviceRequestNotificationsXML);
		}else{
			serviceRequestForm.setServiceRequestNotificationsXML(getXmlOutputGenerator(request.getLocale()).generateEmptyXML());
		}
		// serviceRequestShipmentXML
		Map shipments = getServiceRequestShipments(serviceRequestResult
				.getServiceRequest().getShipmentOrderLines(), request
				.getLocale());

		List<ShipmentForm> serviceRequestShipments = (List<ShipmentForm>) shipments
				.get("shipmentForm");
		if (serviceRequestShipments != null
				&& serviceRequestShipments.size() > 0) {
			serviceRequestForm
					.setServiceRequestShipments(serviceRequestShipments);
		}
		ShipmentForm inProcessForm = (ShipmentForm) shipments
				.get("inProcessForm");
		if (inProcessForm != null && inProcessForm.getShipmentXML() != null) {
			serviceRequestForm.setInProcessForm(inProcessForm);
		}
		Map returnShipments = getServiceRequestReturns(serviceRequestResult
				.getServiceRequest().getReturnOrderLines(), request.getLocale());
		List<ShipmentForm> returnsWithTrackNum = (List<ShipmentForm>) returnShipments
				.get("returnsWithTrackNum");
		if (returnsWithTrackNum != null
				&& returnsWithTrackNum.size() > 0) {
			serviceRequestForm
					.setServiceRequestReturnShipments(returnsWithTrackNum);
		}
		
		ShipmentForm inProgressReturnNoTrackNumForm = (ShipmentForm) returnShipments
				.get("inProgressReturnNoTrackNumForm");
		if (inProgressReturnNoTrackNumForm != null &&
				inProgressReturnNoTrackNumForm.getShipmentXML() != null) {
			serviceRequestForm.setInProgressReturnNoTrackNumForm(inProgressReturnNoTrackNumForm);
		}

		ShipmentForm deliveredReturnNoTrackNumForm = (ShipmentForm) returnShipments
				.get("deliveredReturnNoTrackNumForm");
		if (deliveredReturnNoTrackNumForm != null &&
				deliveredReturnNoTrackNumForm.getShipmentXML() != null) {
			serviceRequestForm.setDeliveredReturnNoTrackNumForm(deliveredReturnNoTrackNumForm);
		}
		
		//productImage ratio correction start
		String productImageUrl = serviceRequestResult.getServiceRequest().getAsset().getProductImageURL();
		logger.info("productImageUrl value is "+productImageUrl);
		int originalImageHeight = 75;
		int originalmageWidth = 75;
		int newImageHeight = 75;
		int newImageWidth = 75;
		try {
			URL url = new URL(productImageUrl);
			BufferedImage originalImage = ImageIO.read(url);
			originalImageHeight = originalImage.getHeight();
			originalmageWidth = originalImage.getWidth();
			
			if (originalImageHeight > originalmageWidth){
				newImageHeight = Math.round((originalImageHeight * 75/originalmageWidth));//Added this line to keep the aspect ratio ramain same
			}else{
				newImageWidth = Math.round((originalmageWidth * 75/originalImageHeight));
			}
			logger.info("originalImageHeight "+originalImageHeight+" originalmageWidth "+originalmageWidth+" " +
					" newImageHeight "+newImageHeight+" newImageWidth "+newImageWidth);
		} catch (Exception e) {
			String noImageUrl = getServerURLPrefix(request) + LexmarkConstants.PRODUCT_NOT_FOUND_IMAGE_URL;
			serviceRequestResult.getServiceRequest().getAsset().setProductImageURL(noImageUrl);
			logger.info("Image url for exception is "+noImageUrl);
			logger.debug("Exception "+e.getMessage());
		}
		model.addAttribute("newImageHeight",newImageHeight);
		model.addAttribute("newImageWidth",newImageWidth);
		//productImage ratio correction end
		
		serviceRequestForm.setServiceHost(getServerURLPrefix(request));
		serviceRequestForm.getServiceRequest().setServiceRequestETA("");
		
		List<ServiceRequestActivity> serviceActivities = serviceRequestResult.getServiceRequest().getServicewebUpdateActivities();
		if(null != serviceActivities){
			for(ServiceRequestActivity serviceReqAcc : serviceActivities){
				if(null != serviceReqAcc.getServiceRequestETA() || !"".equals(serviceReqAcc.getServiceRequestETA())){
					//logger.debug("ETA is not null and blank "+serviceReqAcc.getServiceRequestETA());
					//serviceRequestForm.getServiceRequest().setServiceRequestETA(serviceReqAcc.getServiceRequestETA());
					logger.debug("ETA is not null and blank 11 "+serviceReqAcc.getServiceRequestETA());
					logger.debug("DATE ETA value is  "+serviceReqAcc.getServiceRequestDateETA());
					if(null != serviceReqAcc.getServiceRequestDateETA() && !"".equals(serviceReqAcc.getServiceRequestDateETA().toString())){
						serviceRequestForm.getServiceRequest().setServiceRequestDateETA(serviceReqAcc.getServiceRequestDateETA());
						logger.debug("setting DATE value in form is "+serviceRequestForm.getServiceRequest().getServiceRequestDateETA());
						HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));
						String timeZoneOffset = httpReq.getParameter(ChangeMgmtConstant.TIMEZNOFFSET);
						logger.debug("timeZOne is 1 "+timeZoneOffset);
						Float timeZoneOffset1 = Float.valueOf(timeZoneOffset);
						logger.debug("timeZOne is 2 "+timeZoneOffset1);
						String dt =  DateLocalizationUtil.localizeDateTime(DateUtil.adjustDateWithOffset(serviceRequestForm.getServiceRequest().getServiceRequestDateETA(), timeZoneOffset1), true, request.getLocale());
						logger.debug("localized date is "+dt);
						//model.addAttribute("serviceProviderETADate", dt);
						//serviceRequestETA
						serviceRequestForm.getServiceRequest().setServiceRequestETA(dt);
					}
					
					
				}else{
					logger.debug("ETA is null and blank "+serviceReqAcc.getServiceRequestSLA());
					serviceRequestForm.getServiceRequest().setServiceRequestSLA(serviceReqAcc.getServiceRequestSLA());
				}
			}
		}
		
		
		// Down Load Attachments CI6		
		ResourceURL resOutPutURL = response.createResourceURL();
        resOutPutURL.setResourceID("displayAttachmentBreakFix");
        model.addAttribute("displayAttachmentBreakFix", resOutPutURL.toString());
        
		model.addAttribute("deviceDetailForm", deviceDetailForm);
		model.addAttribute("serviceRequestForm", serviceRequestForm);
		//This is added when user clicks on back
		if(request.getParameter("sourcePage")!=null&&request.getParameter("sourcePage")!="")
			{model.addAttribute("requestedFrom",request.getParameter("sourcePage"));}
		
		logger.debug("-------------serviceRequestDrillDown end---------");
	}

	private String getServerURLPrefix(RenderRequest request) {
		HttpServletRequest httpReq = PortalUtil.getHttpServletRequest(request);   
		String url = httpReq.getRequestURL().toString();
		String subUrl = getSubStringFromZeroToNthPattern(url , "/" , 3);
		return subUrl;
	}
	
	private void setBreadCrumbToSRForm(ServiceRequestForm serviceRequestForm,
			String pageType) {
		if ((LexmarkConstants.SERVICE_REQUEST_BREADCRUMB_RECENT)
				.equals(pageType))
			{serviceRequestForm
					.setBreadCrumb(LexmarkConstants.PAGETYPE_RECENTSRLIST);}
		else if ((LexmarkConstants.SERVICE_REQUEST_BREADCRUMB_MY)
				.equals(pageType))
			{serviceRequestForm
					.setBreadCrumb(LexmarkConstants.PAGETYPE_MYSRLIST);}
		else
			{serviceRequestForm
					.setBreadCrumb(LexmarkConstants.PAGETYPE_RECENTSRLIST);}
	}

	public void downloadPDF(ResourceResponse response,
			List<ServiceRequest> serviceRequestList, Locale locale,
			String[] headers, String[] generatorPatterns, String fileName)
			throws IOException {
		logger.debug("SharedPortletController.downloadPDF Enter---->");
		response.setProperty("Content-disposition", "attachment; filename="
				+ fileName);
		response.setContentType("application/pdf");
		PdfPReportGenerator generator = new PdfPReportGenerator(headers,
				generatorPatterns, serviceRequestList);
		OutputStream responseOut = response.getPortletOutputStream();
		try {
			generator.generate(responseOut);
			responseOut.flush();
		} finally {
			if (responseOut != null) {
				try {
					responseOut.close();
				} catch (IOException ignored) {
					throw new IOException(ignored.getLocalizedMessage());
				}
			}
		}
	}

	public void showServiceRequestSubmitPage2(RenderRequest request,
			RenderResponse response, String assetId, Model model)
			throws Exception {
		ServiceRequestConfirmationForm serviceRequestConfirmationForm = new ServiceRequestConfirmationForm();
		ServiceRequest serviceRequest = new ServiceRequest();
		List<EntitlementServiceDetail> selectedServiceDetails = new ArrayList<EntitlementServiceDetail>();
		serviceRequest.setSelectedServiceDetails(selectedServiceDetails);
		AccountContact primaryContact = new AccountContact();
		serviceRequest.setPrimaryContact(primaryContact);
		AccountContact secondaryContact = new AccountContact();
		serviceRequest.setSecondaryContact(secondaryContact);
		GenericAddress serviceAddress = new GenericAddress();
		serviceRequest.setServiceAddress(serviceAddress);
		serviceRequestConfirmationForm.setServiceRequest(serviceRequest);

		AssetContract contract = new AssetContract();
		contract.setAssetId(assetId);
		contract.setPageName(ChangeMgmtConstant.BREAKFIX_DEVICE_DETAIL);
		try{
			PortletSession session = request.getPortletSession();
			
			LEXLOGGER.info("start printing lex logger");
			ObjectDebugUtil.printObjectContent(contract, LEXLOGGER);
			LEXLOGGER.info("end printing lex loggger");
			// retrieve device by contract
			Long startTime = System.currentTimeMillis();
			
			AssetResult assetResult = orderSuppliesAssetService.retrieveDeviceDetail(contract);		//  test mock call
			LEXLOGGER.logTime("** MPS PERFORMANCE TESTING RETRIEVE Device DETAIL ==>: " + (System.currentTimeMillis()- startTime)/1000.0);
			
			PerformanceUtil.calcTime(perfLogger, PerformanceConstant.ORDERSUPPLIES_MSG_RETRIEVEDEVICEDETAIL, startTime,System.currentTimeMillis(), PerformanceConstant.SYSTEM_SIEBEL,contract);
			
			if (assetResult != null && assetResult.getAsset() != null) {
				logger.debug("the asset is not null!!!!!!!!!!!!!!!!!!!!!!!!!!![IN SHAREDPORTLETCONTROLLER]");
				Asset asset = assetResult.getAsset();
				
				assembleDevice(asset, request.getLocale()); /*Done for Defect 3924 -Jan Release*/
				// if the asset is not entitled, process the situation like not my printer
				if (asset.getEntitlement() == null || asset.getEntitlement().getServiceDetails() == null || asset.getEntitlement().getServiceDetails().size() == 0) {
					model.addAttribute("notEntitledFlag", true);
					return;
				}
				LocalizedEntitlementServiceListContract localizedEntitlementServiceListContract = new LocalizedEntitlementServiceListContract();
				localizedEntitlementServiceListContract
						.setEntitlementServiceDetails(asset.getEntitlement()
								.getServiceDetails());
				localizedEntitlementServiceListContract.setLocale(request
						.getLocale());
				LocalizedEntitlementServiceListResult result = serviceRequestLocaleService
						.retrieveLocalizedEntitlementServiceList(localizedEntitlementServiceListContract);
				asset.getEntitlement().setServiceDetails(
						result.getEntitlementServiceDetails());
				String imageUrl = retrieveProductImageUrl(asset.getProductTLI());
			    if(LexmarkConstants.PRODUCT_NOT_FOUND_IMAGE_URL.equalsIgnoreCase(imageUrl)) {
			    	imageUrl = getServerURLPrefix(request) + imageUrl;
			    }
			    asset.setProductImageURL(imageUrl);
				
				//remove work phone "\n" character if any
				String workPhone = asset.getAssetContact().getWorkPhone();
				logger.debug("the work phone is ******************************" + workPhone);			
				if(workPhone !=null && workPhone != "") {
					int idx = workPhone.indexOf("\n");
					if(idx!=-1){					
						logger.debug("workphone number has return character, and we will replace that with space");
						workPhone = workPhone.substring(0, idx) + " " + workPhone.substring(idx+1);
						logger.debug("now the work phone is ******************************" + workPhone);
						asset.getAssetContact().setWorkPhone(workPhone);
					}
				}
				serviceRequestConfirmationForm.setAsset(asset);
				logger.debug("IP fetched from form--------------------->>"+serviceRequestConfirmationForm.getAsset().getControlPanelURL());
				
			} else {
				logger.debug("the asset is null!!!!!!!!!!!!!!!!!!!!!!!!!!!");
				serviceRequestConfirmationForm.setAsset(new Asset());
			}
			ResourceURL resURL = response.createResourceURL();
			resURL.setResourceID("serviceAddressListURL");
			logger.debug("the serviceAddressListURL is " + resURL.toString());
			serviceRequestConfirmationForm.setServiceAddressListURL(resURL
					.toString());
	
			ResourceURL resURL2 = response.createResourceURL();
			resURL2.setResourceID("contactListURL");
			logger.debug("the contactListURL is " + resURL2.toString());
			serviceRequestConfirmationForm.setContactListURL(resURL2.toString());
	
			ResourceURL resURL3 = response.createResourceURL();
			resURL3.setResourceID("secContactListURL");
			logger.debug("the secContactListURL is " + resURL3.toString());
			serviceRequestConfirmationForm.setSecContactListURL(resURL3.toString());
			logger.debug("the name space of this portlet is "
					+ response.getNamespace());
			if (response.getNamespace().indexOf(
					LexmarkConstants.SERVICE_REQUEST_PORTLET_NAME) > -1) {
				logger.debug("SERVICE_REQUEST_PORTLET_NAME FOUND");
				serviceRequestConfirmationForm
						.setHostingPortletName(LexmarkConstants.SERVICE_REQUEST_PORTLET_NAME);
			}
			if (response.getNamespace().indexOf(
					LexmarkConstants.DEVICE_FINDER_PORTLET_NAME) > -1) {
				logger.debug("DEVICE_FINDER_PORTLET_NAME FOUND");
				serviceRequestConfirmationForm
						.setHostingPortletName(LexmarkConstants.DEVICE_FINDER_PORTLET_NAME);
			}
			
			
			// set login user contact information
			
			AccountContact accountContact = new AccountContact();
			accountContact.setContactId(PortalSessionUtil.getContactId(request
					.getPortletSession()));
			accountContact.setFirstName(PortalSessionUtil.getFirstName(request
					.getPortletSession()));
			accountContact.setLastName(PortalSessionUtil.getLastName(request
					.getPortletSession()));
			accountContact.setWorkPhone(PortalSessionUtil.getWorkPhone(request
					.getPortletSession()));
			accountContact.setEmailAddress(PortalSessionUtil
					.getEmailAddress(request.getPortletSession()));
			serviceRequestConfirmationForm.setLoginAccountContact(accountContact);
			
			serviceRequestConfirmationForm.getServiceRequest().setServiceAddress(serviceRequestConfirmationForm.getAsset().getInstallAddress());
			serviceRequestConfirmationForm.getServiceRequest().setPrimaryContact(serviceRequestConfirmationForm.getAsset().getAssetContact());
			
			model.addAttribute("serviceRequestConfirmationForm", serviceRequestConfirmationForm);
			serviceRequestConfirmationForm.refreshSubmitToken(request);
			model.addAttribute("attachmentFormDisplay", getAttachmentDetails());	// added for MPS breakfix
		}catch(Exception e){
			String errorMessage = ServiceStatusErrorMessageUtil.getErrorMessage(LexmarkSPConstants.ERROR_MESSAGE_BUNDLE, "exception.unableToRetrieveDeviceDetail", null, request.getLocale());
			throw new Exception(errorMessage+"^"+e.getMessage());
		}
	}

	public String showCreateNewPage(RenderRequest request,
			RenderResponse response, @RequestParam("gridName") String gridName,
			Model model)throws Exception {
		logger.debug("------Inside showCreateNewPage Started----------");
		String returnPage = "";
		if("primary".equals(gridName)){
			returnPage = "serviceRequest/serviceRequestCreateNewContact";
		}else if("secondary".equals(gridName)){
			returnPage = "serviceRequest/serviceRequestCreateNewContact";
		}else if("serviceAddress".equals(gridName)){
			CreateNewAddressForm createNewAddressForm = new CreateNewAddressForm();
			GeographyListResult countryListResult = geographyService.getCountryDetails();
			List<GeographyCountryContract> countries = countryListResult.getCountryList();
			createNewAddressForm.setCountries(countries);
			String stateData = "<select id=\"state\" onChange=\"removeErrMsg();\"><option value=\"\">Please Select</option></select>";
			model.addAttribute("stateData", stateData);
			model.addAttribute("createNewAddressForm", createNewAddressForm);
			returnPage = "serviceRequest/serviceRequestCreateNewAddress";
		}
		model.addAttribute("gridName", gridName);
		logger.debug("------Inside showCreateNewPage (gridName="+gridName+") End----------");
		return returnPage;
	}
	
	public void getState(ResourceRequest request, ResourceResponse response, Model model) throws Exception {
		logger.debug("----------------------- Started getState method---------------------");
		String countryCode = request.getParameter("countryCode");
		if(countryCode==null||countryCode==""){
			String responseString="<select id=\"state\" onChange=\"removeErrMsg();\"><option value=\"\">Please Select</option></select>";
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			out.print(responseString);
			out.flush();
			out.close();
		}else{
			logger.debug("Country name received from request is "+countryCode);
			GeographyListResult countryListResult = geographyService.getStateDetails(countryCode);
			List<GeographyStateContract> states = countryListResult.getStateList();
			logger.debug("List size is "+states.size());
			
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			out.print(getXmlContent(states));
			out.flush();
			out.close();
		}
		logger.debug("----------------------- Ended getState method---------------------");
	}
	
	private String getXmlContent(List<GeographyStateContract> result) {
		StringBuilder sb = new StringBuilder();
		if(result.size()!=0){
		sb.append("<select id=\"state\" onChange=\"removeErrMsg();\">");
		sb.append("<option value=\"\">Please Select</option>");
		for(int i=0;i<result.size();i++) {
			sb.append("<option value=\""+ 	XMLEncodeUtil.escapeXML(result.get(i).getStateCode())  + "\">");
			sb.append(result.get(i).getStateName());
			sb.append("</option>");
		}
		sb.append("</select>");
		}else{
			sb.append("<select id=\"state\"><option value=\"nostate\">No State Available</option></select>");
		}
		logger.debug("xml content is "+sb.toString());
		return sb.toString();
	}
	
	public String showEditContactPage(RenderRequest request,
			RenderResponse response, @RequestParam("gridName") String gridName, @RequestParam("contactId") String contactId,
			@RequestParam("lastName") String lastName, @RequestParam("firstName") String firstName, 
			@RequestParam("workPhone") String workPhone, @RequestParam("emailAddress") String emailAddress,
			Model model) {
		logger.debug("------Render: serviceRequestCreateNewContact Started----------");
		String returnPage = "";
		model.addAttribute("contactId", contactId);
		model.addAttribute("lastName", lastName);
		model.addAttribute("firstName", firstName);
		model.addAttribute("workPhone", workPhone);
		model.addAttribute("emailAddress", emailAddress);
		logger.debug("the contact id is *****************" + contactId);
		if("primary".equals(gridName)){
			returnPage = "serviceRequest/serviceRequestCreateNewContact";
		}else if("secondary".equals(gridName)){
			returnPage = "serviceRequest/serviceRequestCreateNewContact";
		}else if("serviceAddress".equals(gridName)){
			returnPage = "serviceRequest/serviceRequestCreateNewServiceAddress";
		}
		logger.debug("===============the GRID=======NAME========IS======="+gridName);
		model.addAttribute("gridName", gridName);
		logger.debug("------Render: serviceRequestCreateNewContact End----------");
		return returnPage;
	}
	
	public void showServiceRequestSubmitPage2FromDeviceNotFoundPage(
			RenderRequest request, RenderResponse response, String assetId,
			String serialNumber, String machineType, String productTLI, Model model)
			throws Exception {
		ServiceRequestConfirmationForm serviceRequestConfirmationForm = new ServiceRequestConfirmationForm();
		ServiceRequest serviceRequest = new ServiceRequest();
		List<EntitlementServiceDetail> selectedServiceDetails = new ArrayList<EntitlementServiceDetail>();
		serviceRequest.setSelectedServiceDetails(selectedServiceDetails);
		AccountContact primaryContact = new AccountContact();
		serviceRequest.setPrimaryContact(primaryContact);
		AccountContact secondaryContact = new AccountContact();
		serviceRequest.setSecondaryContact(secondaryContact);
		GenericAddress serviceAddress = new GenericAddress();
		serviceRequest.setServiceAddress(serviceAddress);
		serviceRequestConfirmationForm.setServiceRequest(serviceRequest);
		try{
			AssetContract contract = new AssetContract();
			if (assetId != null && !assetId.equals("")) {
				contract.setAssetId(assetId);
				contract.setPageName(ChangeMgmtConstant.BREAKFIX_DEVICE_DETAIL);
				PortletSession session = request.getPortletSession();
				
				AssetResult assetResult = orderSuppliesAssetService.retrieveDeviceDetail(contract);
				
				if (assetResult != null && assetResult.getAsset() != null) {
					Asset asset = assetResult.getAsset();					
					assembleDevice(asset, request.getLocale());/*Done for Defect 3924-Jan Release*/
					logger.debug("the asset is not null!!!!!!!!!!!!!!!!!!!!!!!!!!!");
					CheckedEntitlementServiceDetailContract checkedEntitlementServiceDetailContract = new CheckedEntitlementServiceDetailContract();
					checkedEntitlementServiceDetailContract.setLocale(request
							.getLocale());
					CheckedEntitlementServiceDetailResult checkedEntitlementServiceDetailResult = serviceRequestLocaleService
							.retrieveCheckedEntitlementServiceDetail(checkedEntitlementServiceDetailContract);
					Entitlement e = new Entitlement();
					e.setServiceDetails(checkedEntitlementServiceDetailResult
							.getEntitlementServiceDetails());
					asset.setEntitlement(e);
					asset.setNotMyPrinter(true);
					logger.debug("not my printer flag is *******************"
							+ asset.isNotMyPrinter());
					asset.setProductImageURL(retrieveProductImageUrl(asset.getProductTLI()));
					//added to remove the new line charecter from the phone no
					asset.getAssetContact().setWorkPhone(asset.getAssetContact().getWorkPhone().replace(" ", "").replace("\n", ""));
					logger.debug("------correct-------->>>"+asset.getAssetContact().getWorkPhone());
					serviceRequestConfirmationForm.setAsset(asset);
				} else {
					logger.debug("the asset is nulll!!!!!!!!!!!!!!!!!!!!!!!!!!!");
					Asset a = new Asset();
					a.setNotMyPrinter(true);
					serviceRequestConfirmationForm.setAsset(a);
				}
			} else {
				Asset newAsset = new Asset();
				newAsset.setSerialNumber(serialNumber);
				CheckedEntitlementServiceDetailContract checkedEntitlementServiceDetailContract = new CheckedEntitlementServiceDetailContract();
				checkedEntitlementServiceDetailContract.setLocale(request
						.getLocale());
				CheckedEntitlementServiceDetailResult checkedEntitlementServiceDetailResult = serviceRequestLocaleService
						.retrieveCheckedEntitlementServiceDetail(checkedEntitlementServiceDetailContract);
				Entitlement e = new Entitlement();
				e.setServiceDetails(checkedEntitlementServiceDetailResult
						.getEntitlementServiceDetails());
				newAsset.setEntitlement(e);
				if (machineType != null && !machineType.equals(""))
					{newAsset.setModelNumber(machineType);}
				if (productTLI != null && !productTLI.equals("")) {
					newAsset.setProductTLI(productTLI);
				}
				newAsset.setProductImageURL(retrieveProductImageUrl(productTLI));
				newAsset.setNotMyPrinter(true);
				logger.debug("machineType is ***********************"
						+ newAsset.getModelNumber());
				logger.debug("productTLI is ***********************"
						+ newAsset.getProductTLI());
				logger.debug("not my printer flag is *******************"
						+ newAsset.isNotMyPrinter());
				serviceRequestConfirmationForm.setAsset(newAsset);
			}
	
			ResourceURL resURL = response.createResourceURL();
			resURL.setResourceID("serviceAddressListURL");
			logger.debug("the serviceAddressListURL is " + resURL.toString());
			serviceRequestConfirmationForm.setServiceAddressListURL(resURL
					.toString());
	
			ResourceURL resURL2 = response.createResourceURL();
			resURL2.setResourceID("contactListURL");
			logger.debug("the contactListURL is " + resURL2.toString());
			serviceRequestConfirmationForm.setContactListURL(resURL2.toString());
	
			ResourceURL resURL3 = response.createResourceURL();
			resURL3.setResourceID("secContactListURL");
			logger.debug("the secContactListURL is " + resURL3.toString());
			serviceRequestConfirmationForm.setSecContactListURL(resURL3.toString());
			logger.debug("the name space of this portlet is "
					+ response.getNamespace());
			if (response.getNamespace().indexOf(
					LexmarkConstants.SERVICE_REQUEST_PORTLET_NAME) > -1) {
				logger.debug("SERVICE_REQUEST_PORTLET_NAME FOUND");
				serviceRequestConfirmationForm
						.setHostingPortletName(LexmarkConstants.SERVICE_REQUEST_PORTLET_NAME);
			}
			if (response.getNamespace().indexOf(
					LexmarkConstants.DEVICE_FINDER_PORTLET_NAME) > -1) {
				logger.debug("DEVICE_FINDER_PORTLET_NAME FOUND");
				serviceRequestConfirmationForm
						.setHostingPortletName(LexmarkConstants.DEVICE_FINDER_PORTLET_NAME);
			}
	
			// set login user contact information
			AccountContact accountContact = new AccountContact();
			accountContact.setContactId(PortalSessionUtil.getContactId(request
					.getPortletSession()));
			accountContact.setFirstName(PortalSessionUtil.getFirstName(request
					.getPortletSession()));
			accountContact.setLastName(PortalSessionUtil.getLastName(request
					.getPortletSession()));
			accountContact.setWorkPhone(PortalSessionUtil.getWorkPhone(request
					.getPortletSession()));
			accountContact.setEmailAddress(PortalSessionUtil
					.getEmailAddress(request.getPortletSession()));
			serviceRequestConfirmationForm.setLoginAccountContact(accountContact);	
			
			serviceRequestConfirmationForm.getServiceRequest().setServiceAddress(serviceRequestConfirmationForm.getAsset().getInstallAddress());
			serviceRequestConfirmationForm.getServiceRequest().setPrimaryContact(serviceRequestConfirmationForm.getAsset().getAssetContact());
			
			model.addAttribute("serviceRequestConfirmationForm", serviceRequestConfirmationForm);
			serviceRequestConfirmationForm.refreshSubmitToken(request);
			model.addAttribute("attachmentFormDisplay", getAttachmentDetails());	// added for MPS breakfix
		}catch(Exception e){
			String errorMessage = ServiceStatusErrorMessageUtil.getErrorMessage(LexmarkSPConstants.ERROR_MESSAGE_BUNDLE, "exception.unableToRetrieveDeviceDetail", null, request.getLocale());
			throw new Exception(errorMessage+"^"+e.getMessage());
		}
	}

	public void submitServiceRequest(ActionRequest request,
			ActionResponse response,
			ServiceRequestConfirmationForm serviceRequestConfirmationForm,
			BindingResult result, Model model) throws LGSBusinessException, SiebelCrmServiceException, Exception {		// changed for MPS breakfix
		logger.debug("*******************submit service request logic started******************************");
		try{
			
						
			if (PortalSessionUtil.isDuplicatedSubmit(request,
					serviceRequestConfirmationForm)) {
				logger.debug("duplicated submit, do nothing!");			
				throw new RuntimeException(
						ServiceStatusErrorMessageUtil.getErrorMessage(ERROR_MESSAGE_BUNDLE, "exception.serviceRequest.duplicateSubmission", null, request.getLocale()));
			} else {
				Asset asset = serviceRequestConfirmationForm.getAsset();
				if (asset != null) {
					serviceRequestConfirmationForm.getServiceRequest().setAsset(
							asset);
				}
				AccountContact ac = serviceRequestConfirmationForm
						.getLoginAccountContact();
				if (ac != null) {
					serviceRequestConfirmationForm.getServiceRequest()
							.setRequestor(ac);
				}
				String primaryContactId = serviceRequestConfirmationForm.getServiceRequest().getAsset().getAssetContact().getContactId();
				String secondaryContactId = serviceRequestConfirmationForm.getServiceRequest().getSecondaryContact().getContactId();
				String serviceAddressId = serviceRequestConfirmationForm.getServiceRequest().getAsset().getInstallAddress().getAddressId();
			

				logger.debug("the primaryContactId is " + primaryContactId);
				logger.debug("the secondaryContactId is " + secondaryContactId);
				logger.debug("the serviceAddressId is " + serviceAddressId);
				logger.debug("the number of selected service detail is "+ serviceRequestConfirmationForm.getSelectedServiceDetails().length);
				
				for (String serviceDetailId : serviceRequestConfirmationForm.getSelectedServiceDetails()) {
					for (EntitlementServiceDetail esd : asset.getEntitlement().getServiceDetails()) {
						if (esd.getServiceDetailId().equals(serviceDetailId)) {
							serviceRequestConfirmationForm.getServiceRequest().getSelectedServiceDetails().add(esd);
							esd.setPrimaryFlag(true);
						}
					}
				}

				for (EntitlementServiceDetail esd : serviceRequestConfirmationForm
						.getServiceRequest().getSelectedServiceDetails()) {
					logger.debug("the onsiteRepair is *******"+ esd.getServiceDetailDescription());
				}

				logger.debug("the other request service is "+ serviceRequestConfirmationForm.getServiceRequest().getOtherRequestedService());

				if (primaryContactId == null || primaryContactId.equals("")) {
					serviceRequestConfirmationForm.getServiceRequest().getPrimaryContact().setNewContactFlag(true);
					serviceRequestConfirmationForm.getServiceRequest().getPrimaryContact().setUpdateContactFlag(false);
				} else {
					serviceRequestConfirmationForm.getServiceRequest().getPrimaryContact().setNewContactFlag(false);
					serviceRequestConfirmationForm.getServiceRequest().getPrimaryContact().setUpdateContactFlag(true);
				}
				// make sure the user create a new contact other than doing nothing
				if ((secondaryContactId == null || secondaryContactId.equals(""))&& (!serviceRequestConfirmationForm.getServiceRequest().getSecondaryContact().getLastName().equals(""))) {
					serviceRequestConfirmationForm.getServiceRequest().getSecondaryContact().setNewContactFlag(true);
					serviceRequestConfirmationForm.getServiceRequest().getSecondaryContact().setUpdateContactFlag(false);
				}
				// the user didn't select or create the secondary contact at all
				else if ((secondaryContactId == null || secondaryContactId.equals(""))&& (serviceRequestConfirmationForm.getServiceRequest().getSecondaryContact().getLastName().equals(""))) {
					serviceRequestConfirmationForm.getServiceRequest().getSecondaryContact().setNewContactFlag(true);
					serviceRequestConfirmationForm.getServiceRequest().getSecondaryContact().setUpdateContactFlag(false);
				}
				// the user update an existing contact
				else {
					serviceRequestConfirmationForm.getServiceRequest().getSecondaryContact().setNewContactFlag(false);
					serviceRequestConfirmationForm.getServiceRequest().getSecondaryContact().setUpdateContactFlag(true);
				}
				if (serviceAddressId == null || "-1".equals(serviceAddressId) || serviceAddressId.equals("")) {
					serviceRequestConfirmationForm.getServiceRequest().getServiceAddress().setNewAddressFlag("true");
					serviceRequestConfirmationForm.getServiceRequest().getServiceAddress().setAddressId(null);
				} else {
					serviceRequestConfirmationForm.getServiceRequest().getServiceAddress().setNewAddressFlag("false");
				}
				logger.debug("State value is "+serviceRequestConfirmationForm.getServiceRequest().getServiceAddress().getState());
				logger.debug("State/Province value is "+serviceRequestConfirmationForm.getServiceRequest().getServiceAddress().getStateProvince());
				
				
				
				
				CreateServiceRequestContract contract = new CreateServiceRequestContract();
					contract.setServiceRequest(serviceRequestConfirmationForm.getServiceRequest());
					contract.setMdmId(PortalSessionUtil.getMdmId(request.getPortletSession()));
					contract.setMdmLevel(PortalSessionUtil.getMdmLevel(request.getPortletSession()));
					
					
					if(serviceRequestConfirmationForm.getFleetManagementFlag()!=null&&serviceRequestConfirmationForm.getFleetManagementFlag()!="" &&serviceRequestConfirmationForm.getFleetManagementFlag().equalsIgnoreCase("true")){
						logger.debug("Setting LBS Flag to true ");
						contract.setFleetManagementFlag(serviceRequestConfirmationForm.getFleetManagementFlag());
					}
					else{
						logger.debug("Setting LBS Flag to false");
						contract.setFleetManagementFlag("false");

					}
					
					/*String fleetManagementFlag = (String)request.getAttribute("fleetManagementFlag");
					if(fleetManagementFlag != null && fleetManagementFlag.equalsIgnoreCase("true")){
						logger.debug("Setting LBS Flag to true ");
						//request.setAttribute("fleetManagementFlag", "true");
						//serviceReqContract.setFleetManagementFlag(((ManageAssetForm) formData).getFleetManagementFlag());
						contract.setFleetManagementFlag(fleetManagementFlag);
					}
					else{
						logger.debug("Setting LBS Flag to false");
						contract.setFleetManagementFlag("false");
					}*/
					
					LEXLOGGER.info("start printing lex logger");
					ObjectDebugUtil.printObjectContent(contract, LEXLOGGER);
					LEXLOGGER.info("end printing lex loggger");
				Long startTime = System.currentTimeMillis();
				

				CreateServiceRequestResult csresult = createServiceRequest.createServiceRequest(contract);	// test mock
				LEXLOGGER.logTime("** MPS PERFORMANCE TESTING creating service request ==>: " + (System.currentTimeMillis()- startTime)/1000.0);
				
				
				PerformanceUtil.calcTime(perfLogger, PerformanceConstant.SHAREDPORTLET_MSG_CREATESERVICEREQUEST, startTime,System.currentTimeMillis(), PerformanceConstant.SYSTEM_WEBMETHODS,contract);
				
				logger.debug("the service request number is "+ csresult.getServiceRequestNumber());

				serviceRequestConfirmationForm.getServiceRequest().setServiceRequestNumber(csresult.getServiceRequestNumber());
				
				//CI-6 : Start : Upload Attachment Attachment--
				logger.debug("Calling the attachment service "+ csresult.getServiceRequestNumber());
					doAttachmentFilesSubmit(request, csresult, serviceRequestConfirmationForm);
				//CI-6 : End : Attachment
				
				model.addAttribute(serviceRequestConfirmationForm);
			}
			
		}
		catch (LGSBusinessException e) {
			throw new LGSBusinessException(e);
		}
		catch (SiebelCrmServiceException e) {
			throw new LGSBusinessException(e);
		}
		catch (Exception e){
			logger.debug("Exception "+e.getMessage());
			String errorMessage = ServiceStatusErrorMessageUtil.getErrorMessage(ERROR_MESSAGE_BUNDLE, "exception.serviceRequest.createFailure", null, request.getLocale()) + " " + e.getMessage();
			MailUtil.sendEmail("nsextus@lexmark.com", "Error while creating Service Request", e.getMessage(), false);
			
			throw new LGSBusinessException(e);		// changed by for MPS breakfix
		}
	}

	public void retrievePrimaryContactListURL(ResourceRequest request,
			ResourceResponse response, Model model) throws Exception {
		logger.debug("-------------primaryContactListURL started---------");
		ContactListContract contactListContract = ContractFactory
				.getContactListContract(request);

		PortletSession session = request.getPortletSession();
		CrmSessionHandle crmSessionHandle = globalService.initCrmSessionHandle(PortalSessionUtil.getSiebelCrmSessionHandle(request));
		try {
		    contactListContract.setSessionHandle(crmSessionHandle);
		/*
		 * we'll set the favorite to true if the criteria is available. which
		 * will allow us to get the favorite contact list.
		 */
		String searchCriterias = request
				.getParameter(PaginationUtil.SEARCH_CRITERIAS);

		if (searchCriterias != null) {
			logger.debug("***********the searchCriterias is not null******************");
			String contactId = searchCriterias;
			contactListContract.setContactId(contactId);
			contactListContract.setFavoriteFlag(true);
		} else {
			contactListContract.setContactId(PortalSessionUtil
					.getContactId(session));
			contactListContract.setFavoriteFlag(false);
		}

		LEXLOGGER.info("start printing lex logger");
		ObjectDebugUtil.printObjectContent(contactListContract, LEXLOGGER);
		LEXLOGGER.info("end printing lex loggger");
		
		Long startTime = System.currentTimeMillis();
		
		ContactListResult contactListResult = contactService
				.retrieveContactList(contactListContract);
		LEXLOGGER.logTime("** MPS PERFORMANCE TESTING RETRIEVE Contact LIST ==>: " + (System.currentTimeMillis()- startTime)/1000.0);
		
		
		PerformanceUtil.calcTime(perfLogger, PerformanceConstant.CONTACT_MSG_RETRIEVECONTACTLIST, startTime,System.currentTimeMillis(), PerformanceConstant.SYSTEM_SIEBEL,contactListContract);
		
		if (searchCriterias != null) {
			List<AccountContact> starredContactList = new Vector<AccountContact>();
			List<AccountContact> allContacts = contactListResult.getContacts();
			for (AccountContact ac : allContacts) {
				if (ac.getUserFavorite()) {
					starredContactList.add(ac);
				}
			}
			contactListResult.setContacts(starredContactList);
		}
		String contextPath = request.getContextPath();
		logger.debug("the contextPath is" + contextPath);
		String content;
		if (searchCriterias == null) {
			content = getXmlOutputGenerator(request.getLocale())
					.convertContactListToXML(contactListResult.getContacts(),
						 contactListResult.getTotalCount(), contactListContract.getStartRecordNumber(), contextPath);
		    } else {
			content = getXmlOutputGenerator(request.getLocale())
					.convertMyContactListToXML(contactListResult.getContacts(),
							contactListResult.getTotalCount(), contactListContract.getStartRecordNumber(), contextPath);
		    }
		PrintWriter out = response.getWriter();
		response.setContentType("text/xml");

		out.print(content);
		out.flush();
		out.close();
		} catch (Exception _ignore) {
		    logger.error(_ignore);
		} finally {
		    globalService.releaseSessionHandle(crmSessionHandle);
		}
		logger.debug("-------------primaryContactListURL end---------");
	}

	public void retrieveSecondaryContactListURL(ResourceRequest request,
			ResourceResponse response, Model model) throws Exception {
		logger.debug("-------------primaryContactListURL started---------");
		ContactListContract contactListContract = ContractFactory
				.getContactListContract(request);

		PortletSession session = request.getPortletSession();
		/*
		 * we'll set the favorite to true if the criteria is available. which
		 * will allow us to get the favorite contact list.
		 */
		String searchCriterias = request
				.getParameter(PaginationUtil.SEARCH_CRITERIAS);

		if (searchCriterias != null) {
			logger.debug("***********the searchCriterias is not null******************");
			String contactId = searchCriterias;
			contactListContract.setContactId(contactId);
			contactListContract.setFavoriteFlag(true);
		} else {
			contactListContract.setContactId(PortalSessionUtil
					.getContactId(session));
			contactListContract.setFavoriteFlag(false);
		}

		ObjectDebugUtil.printObjectContent(contactListContract, logger);
		ContactListResult contactListResult = contactService
				.retrieveContactList(contactListContract);
		if (searchCriterias != null) {
			List<AccountContact> starredContactList = new Vector<AccountContact>();
			List<AccountContact> allContacts = contactListResult.getContacts();
			for (AccountContact ac : allContacts) {
				if (ac.getUserFavorite()) {
					starredContactList.add(ac);
				}
			}
			contactListResult.setContacts(starredContactList);
		}
		
		String contextPath = request.getContextPath();
		logger.debug("the contextPath is" + contextPath);
		String content;
		if (searchCriterias == null) {
			content = getXmlOutputGenerator(request.getLocale())
    				.convertSecondaryContactListToXML(contactListResult
    						.getContacts(), contactListResult.getContacts().size(),
    						0, contextPath);
		} else {
			content = getXmlOutputGenerator(request.getLocale())
					.convertMySecondaryContactListToXML(contactListResult
							.getContacts(), contactListResult.getContacts()
							.size(), 0, contextPath);
		}
		PrintWriter out = response.getWriter();
		response.setContentType("text/xml");
		out.print(content);
		out.flush();
		out.close();
		logger.debug("-------------primaryContactListURL end---------");
	}

	public void retrieveServiceAddressListURL(ResourceRequest request,
			ResourceResponse response, Model model) throws Exception {
		logger.debug("-------------serviceAddressListURL started---------");
		String contextPath = request.getContextPath();
		logger.debug("the contextPath is" + contextPath);
		AddressListContract addressListContract = ContractFactory
				.getAddressListContract(request);

		PortletSession session = request.getPortletSession();
		/*
		 * we'll set the favorite to true if the criteria is available. which
		 * will allow us to get the favorite address list.
		 */
		String searchCriterias = request
				.getParameter(PaginationUtil.SEARCH_CRITERIAS);
		if (searchCriterias != null) {
			logger
					.debug("***********the searchCriterias is not null******************");
			String contactId = searchCriterias;
			addressListContract.setContactId(contactId);
			addressListContract.setFavoriteFlag(true);
		} else {
			addressListContract.setContactId(PortalSessionUtil
					.getContactId(session));
			addressListContract.setFavoriteFlag(false);
		}

		
		AddressListResult addressListResult = serviceRequestService
				.retrieveAddressList(addressListContract);
		
		String content = getXmlOutputGenerator(request.getLocale())
				.convertAddressListToXML(addressListResult.getAddressList(),
						addressListResult.getAddressList().size(), 0,
						contextPath);
		if (searchCriterias != null) {
			content = getXmlOutputGenerator(request.getLocale())
					.convertMyAddressListToXML(addressListResult
							.getAddressList(), addressListResult
							.getAddressList().size(), 0, contextPath);
		}
		PrintWriter out = response.getWriter();
		response.setContentType("text/xml");
		out.print(content);
		out.flush();
		out.close();
		logger.debug("-------------serviceAddressListURL end---------");
	}

	// Advance Search
	public void retrieveAdvanceSearchData(RenderRequest request, Model model)
			throws Exception {
		List<String> countries = PropertiesMessageUtil.getLocationList(
				COUNTRYBUNDLE, "country", request.getLocale());
		List<String> states = PropertiesMessageUtil.getLocationList(
				COUNTRYBUNDLE, "state", request.getLocale());
		List<String> provinces = PropertiesMessageUtil.getLocationList(
				COUNTRYBUNDLE, "province", request.getLocale());
		List<String> cities = PropertiesMessageUtil.getLocationList(
				COUNTRYBUNDLE, "city", request.getLocale());
		AdvancedSearchForm advancedSearchForm = new AdvancedSearchForm();
		advancedSearchForm.setCountries(countries);
		advancedSearchForm.setProvinces(provinces);
		advancedSearchForm.setStates(states);
		advancedSearchForm.setCities(cities);
		model.addAttribute("advancedSearchForm", advancedSearchForm);
	}

	
	public void retrieveServiceRequestList(RequestListContract contract,
					ResourceRequest request, ResourceResponse response,
					String[] generatorPatterns, boolean subRowFlag) throws Exception {
	
		logger.debug("------------- Step 1.1---retrieveServiceRequestList started---------["
						+ System.nanoTime() + "]");
		
		float timezoneOffset = 0;
		if(request.getParameter(LexmarkConstants.TIMEZONE_OFFSET)!= null){
			 timezoneOffset = Float.valueOf(request.getParameter(LexmarkConstants.TIMEZONE_OFFSET)).floatValue();
		}
		
		PortletSession session = request.getPortletSession();
		CrmSessionHandle crmSessionHandle = globalService
				.initCrmSessionHandle(PortalSessionUtil
						.getSiebelCrmSessionHandle(request));
		try {
			contract.setSessionHandle(crmSessionHandle);
			
							
			logger.debug("Request type is----->>>>"+contract.getFilterCriteria().get("requestType").toString());
			if(contract.getFilterCriteria().get("requestType").toString().equals("[BreakFix]"))
			{				
				contract.setHardwareRequestsPermission(false);
				contract.setChangeRequestsPermission(false);
			}				
			LEXLOGGER.info("start printing lex logger");
			ObjectDebugUtil.printObjectContent(contract,LEXLOGGER);
			LEXLOGGER.info("end printing lex loggger");
			
			Long startTime = System.currentTimeMillis();
		
			// commented for MPS 2.1 Mock Call
			RequestListResult serviceRequestListResult = requestTypeService.retrieveRequestList(contract); 
			
			LEXLOGGER.logTime("** MPS PERFORMANCE TESTING RETRIEVE Service request list ==>: " + (System.currentTimeMillis()- startTime)/1000.0);
			
			PerformanceUtil.calcTime(perfLogger, PerformanceConstant.SHAREDPORTLET_MSG_RETRIEVEREQUESTLIST, startTime,System.currentTimeMillis(), PerformanceConstant.SYSTEM_SIEBEL,contract);
			
			List<ServiceRequest> serviceRequestList = serviceRequestListResult.getRequestList();   //Commented for MPS 2.1 Mock Call
			int totalCount = serviceRequestListResult.getTotalCount();  // Commented for MPS 2.1 Mock Call
			
			String xmlContent = getXmlOutputGenerator(request.getLocale()).
			     convertServiceRequestsToXML(serviceRequestList, totalCount,
					contract.getStartRecordNumber(), generatorPatterns,
					subRowFlag, timezoneOffset);
			PrintWriter out = response.getWriter();
			response.setContentType("text/xml");
			out.print(xmlContent);
			out.flush();
			out.close();
		} finally {
			globalService.releaseSessionHandle(crmSessionHandle);
		}
		logger.debug("------------- Step 1.1---retrieveServiceRequestList end---------["
						+ System.nanoTime() + "]");
	}

	public void checkAccountTypeFlagsInSession(PortletRequest request)	throws Exception {
		PortletSession session = request.getPortletSession();
		String accountTypeMPSFlag = (String) session
		.getAttribute(ACCOUNT_TYPE_MPS_FLAG);
		String accountTypeCSSFlag = (String) session
		.getAttribute(ACCOUNT_TYPE_CSS_FLAG);
				
			SiebelAccountListContract siebelAccountListContract = ContractFactory
			.getSiebelAccountListContract(request);
			CrmSessionHandle crmSessionHandle = globalService
			.initCrmSessionHandle(PortalSessionUtil
					.getSiebelCrmSessionHandle(request));
			try {
				siebelAccountListContract.setSessionHandle(crmSessionHandle);
				logger.debug("-------------retrieveSiebelAccountList started---------");
				
				SiebelAccountListResult siebelAccountListResult = serviceRequestService
				.retrieveSiebelAccountList(siebelAccountListContract);
				
				List<Account> accountList = siebelAccountListResult
				.getAccountList();
				logger.debug("-------------retrieveSiebelAccountList ended---------");
				//Because this is used for Phase 1 and Phase 2, must get the phase 2 data and add to the list
				if (LexmarkUserUtil.isFSETechnician(request)){
					String contactId = PortalSessionUtil.getContactId(request.getPortletSession());
					FSEAccountListContract contract = new FSEAccountListContract();
					contract.setSiebelEmployeeId(contactId);
					FSEAccountListResult fSEAccountListResult = globalService.retrieveFSEAccountList(contract);
					if (fSEAccountListResult != null && fSEAccountListResult.getAccountList() != null){
						accountList.addAll(fSEAccountListResult.getAccountList());
					}
					
				}else{
					PartnerAccountListContract contract = new PartnerAccountListContract();
					contract.setMdmId(PortalSessionUtil.getMdmId(session));
					contract.setMdmLevel(PortalSessionUtil.getMdmLevel(session));
					PartnerAccountListResult partnerAccountListResult = globalService.retrievePartnerAccountList(contract);
					if (partnerAccountListResult != null && partnerAccountListResult.getAccountList() != null){
						accountList.addAll(partnerAccountListResult.getAccountList());
					}

				}
				boolean hasAccountTypeMPS = false;
				boolean hasAccountTypeCSS = false;
				boolean directPartnerFlag = false;
				boolean indirectPartnerFlag = false;
				for (Account account : accountList) {
					if (account.getAccountType()!= null && !account.getAccountType().isEmpty()) {
						if(LexmarkConstants.VIEWTYPE_MANAGED_DEVICES.equalsIgnoreCase(account.getAccountType())) {
							session.setAttribute(ACCOUNT_TYPE_MPS_FLAG, LexmarkConstants.VIEWTYPE_MANAGED_DEVICES);
							hasAccountTypeMPS = true;
						} else if(LexmarkConstants.VIEWTYPE_NON_MANAGED_DEVICES.equalsIgnoreCase(account.getAccountType())) {
							session.setAttribute(ACCOUNT_TYPE_CSS_FLAG, LexmarkConstants.VIEWTYPE_NON_MANAGED_DEVICES);
							hasAccountTypeCSS = true;
						}
					}
					
					if (!directPartnerFlag && account.isDirectPartnerFlag()) {
						session.setAttribute(DIRECT_PARTNER_FLAG, LexmarkConstants.DIRECT_PARTNER);
						directPartnerFlag = true;
					}
					
					if (!indirectPartnerFlag && account.isIndirectPartnerFlag()) {
						session.setAttribute(INDIRECT_PARTNER_FLAG, LexmarkConstants.INDIRECT_PARTNER);
						indirectPartnerFlag = true;
					}

				}
				if(!hasAccountTypeMPS) {
					session.setAttribute(ACCOUNT_TYPE_MPS_FLAG, "");
				}
				if(!hasAccountTypeCSS) {
					session.setAttribute(ACCOUNT_TYPE_CSS_FLAG, "");
				}
			} finally {
				globalService.releaseSessionHandle(crmSessionHandle);
			}
	}
	
	public boolean isCreateServiceRequestFlag(RenderRequest request)
			throws Exception {
		PortletSession session = request.getPortletSession();
		String createServiceRequestFlag = (String) session
				.getAttribute("createServiceRequestFlag");
			
			//Set processCustomer flag to indicate if the Siebel call should be made.
			boolean processSR = false;
			for (String ldapRole : PortalSessionUtil.getUserRoles(session)){
				logger.info("###### role: " + ldapRole);

				if (ldapRole.equalsIgnoreCase(LexmarkConstants.ROLE_ACCOUNT_MANAGEMENT)||ldapRole.equalsIgnoreCase(LexmarkConstants.ROLE_SERVICE_SUPPORT)) {		// added for CI5
					processSR = true;
				}
			}

			if (processSR){
				SiebelAccountListContract siebelAccountListContract = ContractFactory
				.getSiebelAccountListContract(request);
				CrmSessionHandle crmSessionHandle = globalService
						.initCrmSessionHandle(PortalSessionUtil
								.getSiebelCrmSessionHandle(request));
				try {
					siebelAccountListContract.setSessionHandle(crmSessionHandle);
					logger.debug("-------------retrieveSiebelAccountList started---------");
					
					SiebelAccountListResult siebelAccountListResult = serviceRequestService
					.retrieveSiebelAccountList(siebelAccountListContract);
					
					List<Account> accountList = siebelAccountListResult
					.getAccountList();
					logger.debug("-------------retrieveSiebelAccountList ended---------");
					for (Account account : accountList) {
						if (account.getCreateServiceRequest().equalsIgnoreCase("update")) {
							session.setAttribute("createServiceRequestFlag", "true");
							return true;
						}
					}
				} finally {
					globalService.releaseSessionHandle(crmSessionHandle);
				}
				
			}
			session.setAttribute("createServiceRequestFlag", "false");
			return false;
//		}
	}

	public void updateUserFavoriteAsset(
			@RequestParam("favoriteAssetId") String favoriteAssetId,
			@RequestParam("favoriteFlag") boolean favoriteFlag,
			ResourceRequest request, ResourceResponse response)
			throws Exception {
		boolean success = false;
		try {
			logger.debug("-------------updateUserFavoriteAsset started---------");
			UserFavoriteAssetContract contract = new UserFavoriteAssetContract();

			PortletSession session = request.getPortletSession();
			contract.setContactId(PortalSessionUtil.getContactId(session));
			contract.setFavoriteAssetId(favoriteAssetId);
			contract.setFavoriteFlag(favoriteFlag);
			logger.debug("favoriteAssetId: " + favoriteAssetId
					+ ", favoriteFlag: " + favoriteFlag+" cotact id is: "+contract.getContactId());
			
			LEXLOGGER.info("start printing lex logger");
			ObjectDebugUtil.printObjectContent(contract, LEXLOGGER);
			LEXLOGGER.info("end printing lex loggger");
			Long startTime = System.currentTimeMillis();
			
			success = deviceService.updateUserFavoriteAsset(contract).isResult();
			LEXLOGGER.logTime("** MPS PERFORMANCE TESTING FAVOURITE/UNFAVOURITE Asset==>: " + (System.currentTimeMillis()- startTime)/1000.0);
			
			
			PerformanceUtil.calcTime(perfLogger, PerformanceConstant.SHAREDPORTLET_MSG_UPDATEUSERFAVORITEASSET, startTime,System.currentTimeMillis(), PerformanceConstant.SYSTEM_AMIND,contract);
			
			logger.debug("-------------updateUserFavoriteAsset end---------");
		} catch (Exception e) {
			logger.debug("Exception "+e.getMessage());
			success = false;
		}
		logger.debug("success: " + success);
		String errorCode = success ? MESSAGE_DEVICE_FINDER_UPDATE_USER_FAVORITE_ASSET
				: EXCEPTION_DEVICE_FINDER_UPDATE_USER_FAVORITE_ASSET;
		ServiceStatusUtil.responseResult(response, errorCode, request
				.getLocale(),"\""+favoriteAssetId+"\"");
	}

	public void retrieveAssociatedServiceTicketList(ResourceRequest request,
			ResourceResponse response, Model model) throws Exception {
		logger.debug("-------------SR  retrieveAssociatedServiceTicketList started---------");
		String serviceRequestNumber = request
				.getParameter("serviceRequestNumber");
		ServiceRequestAssociatedListContract contract = ContractFactory
				.getAssociatedServiceRequestListContract(request);
		PortletSession session = request.getPortletSession();
		CrmSessionHandle crmSessionHandle = globalService
				.initCrmSessionHandle(PortalSessionUtil
						.getSiebelCrmSessionHandle(request));
		
		float timezoneOffset = 0;
		if(request.getParameter(LexmarkConstants.TIMEZONE_OFFSET)!= null){
			 timezoneOffset = Float.valueOf(request.getParameter(LexmarkConstants.TIMEZONE_OFFSET)).floatValue();
		}
		
		try {
			contract.setSessionHandle(crmSessionHandle);
			contract.setServiceRequestNumber(serviceRequestNumber);
			
			ServiceRequestListResult serviceRequestListResult = serviceRequestService
			.retrieveAssociatedServiceRequestList(contract);
			
			List<ServiceRequest> serviceRequestList = serviceRequestListResult
			.getServiceRequests();
			int totalCount = serviceRequestListResult.getTotalCount();
			String[] generatorPatterns = new String[] { "serviceRequestNumber",
					"serviceRequestStatusDate", "asset.serialNumber",
					"asset.modelNumber", "asset.installAddress.addressName",
					"serviceAddress.city", "serviceAddress.state",
					"serviceAddress.province", "serviceAddress.stateProvince", "serviceAddress.country" };
			String xmlContent = "";
			if ("lightBox".equals(request.getParameter("lightBox"))) {
				xmlContent = getXmlOutputGenerator(request.getLocale()).generate(
						serviceRequestList, totalCount, contract
						.getStartRecordNumber(), generatorPatterns);
			} else {
				xmlContent = getXmlOutputGenerator(request.getLocale())
				.convertServiceRequestsToXML(serviceRequestList,
						totalCount, contract.getStartRecordNumber(),
						generatorPatterns, false, timezoneOffset);
			}
			PrintWriter out = response.getWriter();
			response.setContentType("text/xml");
			out.print(xmlContent);
			out.flush();
			out.close();
		} finally {
			globalService.releaseSessionHandle(crmSessionHandle);
		}


		logger.debug("-------------SR  retrieveAssociatedServiceTicketList ended---------");
	}

	public void retrieveServiceRequestHistoryList(ResourceRequest request,
			ResourceResponse response, Model model) throws Exception {
		logger.debug("-------------SR  retrieveServiceRequestHistoryList started---------");
		String assetId = request.getParameter("assetRowId");
		String accountId = request.getParameter("accountRowId");
		String serviceRequestNumber = request.getParameter("serviceRequestNumber");

		String[] generatorPatterns = new String[] { "serviceRequestNumber",
				"serviceRequestStatusDate", "asset.serialNumber",
				"asset.modelNumber", "problemDescription", "serviceAddress.addressName","serviceRequestStatus",
				"serviceAddress.city", "serviceAddress.state",
				"serviceAddress.province", "serviceAddress.postalCode","serviceAddress.country",
				"primaryContact.firstName","primaryContact.lastName","primaryContact.emailAddress",
				"primaryContact.workPhone"};
		ServiceRequestHistoryListContract contract = ContractFactory
				.getServiceRequestHistoryListContract(request);
		LEXLOGGER.info("start printing lex logger");
		ObjectDebugUtil.printObjectContent(contract, LEXLOGGER);
		LEXLOGGER.info("end printing lex loggger");
		
		Map<String, Object> searchCriterias = new HashMap<String, Object>();
		searchCriterias.put("asset.assetId", assetId);
		PortletSession session = request.getPortletSession();
		CrmSessionHandle crmSessionHandle = globalService
				.initCrmSessionHandle(PortalSessionUtil
						.getSiebelCrmSessionHandle(request));
		
		float timezoneOffset = 0;
		if(request.getParameter(LexmarkConstants.TIMEZONE_OFFSET)!= null){
			 timezoneOffset = Float.valueOf(request.getParameter(LexmarkConstants.TIMEZONE_OFFSET)).floatValue();
		}
		
		try {
			contract.setSessionHandle(crmSessionHandle);
			contract.setAssetId(assetId);
			if (accountId != null){
				contract.setAccountId(accountId);
				logger.info("XXXXXXXXX accountId: " + accountId);
			}
			if (serviceRequestNumber != null){
				contract.setServiceRequestNumber(serviceRequestNumber);
				logger.info("XXXXXXXXX serviceRequestNumber: " + serviceRequestNumber);
			}
			
			Long startTime = System.currentTimeMillis();
		
			ServiceRequestListResult serviceRequestListResult = serviceRequestService
			.retrieveServiceRequestHistoryList(contract);
			LEXLOGGER.logTime("** MPS PERFORMANCE TESTING RETRIEVE SERVICE REQUEST HISTORY ==>: " + (System.currentTimeMillis()- startTime)/1000.0);
			
			
			PerformanceUtil.calcTime(perfLogger, PerformanceConstant.HISTORY_MSG_SERVICEREQUESTSERVICE, startTime,System.currentTimeMillis(), PerformanceConstant.SYSTEM_SIEBEL,contract);
			
			//PerformanceTracker.endTracking(lexmarkTran);
			List<ServiceRequest> serviceRequestList = serviceRequestListResult
			.getServiceRequests();
			int totalCount = serviceRequestListResult.getTotalCount();
			String xmlContent = getXmlOutputGenerator(request.getLocale())
			.convertServiceRequestsToXML(serviceRequestList, totalCount,
					contract.getStartRecordNumber(), generatorPatterns,
					false, timezoneOffset);
			PrintWriter out = response.getWriter();
			response.setContentType("text/xml");
			out.print(xmlContent);
			out.flush();
			out.close();
		} finally {
			globalService.releaseSessionHandle(crmSessionHandle);
		}
		logger.debug("-------------SR  retrieveServiceRequestHistoryList ended---------");
	}
	
	//This is for ServiceRequestDetail page
	public void retrieveServiceRequestHistoryListDrillDown(ResourceRequest request,
			ResourceResponse response, Model model) throws Exception {
		logger.debug("-------------SR  retrieveServiceRequestHistoryListDrillDown started---------");
		String assetId = request.getParameter("assetRowId");
		String accountId = request.getParameter("accountRowId");
		String serviceRequestNumber = request.getParameter("serviceRequestNumber");

		String[] generatorPatterns = new String[] { "serviceRequestNumber",
				"serviceRequestStatusDate", "asset.serialNumber",

				"asset.modelNumber", "resolutionCode","serviceRequestStatus", "serviceAddress.addressName","serviceAddress.city", "serviceAddress.state",	// changed for CI5
				"serviceAddress.province", "serviceAddress.postalCode","serviceAddress.country",
				"primaryContact.firstName","primaryContact.lastName","primaryContact.emailAddress",
				"primaryContact.workPhone"};
		ServiceRequestHistoryListContract contract = ContractFactory
				.getServiceRequestHistoryListContract(request);
		LEXLOGGER.info("start printing lex logger");
		ObjectDebugUtil.printObjectContent(contract, LEXLOGGER);
		LEXLOGGER.info("end printing lex loggger");
		
		Map<String, Object> searchCriterias = new HashMap<String, Object>();
		searchCriterias.put("asset.assetId", assetId);
		PortletSession session = request.getPortletSession();
		CrmSessionHandle crmSessionHandle = globalService
				.initCrmSessionHandle(PortalSessionUtil
						.getSiebelCrmSessionHandle(request));
		
		float timezoneOffset = 0;
		if(request.getParameter(LexmarkConstants.TIMEZONE_OFFSET)!= null){
			 timezoneOffset = Float.valueOf(request.getParameter(LexmarkConstants.TIMEZONE_OFFSET)).floatValue();
		}
		
		try {
			contract.setSessionHandle(crmSessionHandle);
			contract.setAssetId(assetId);
			if (accountId != null){
				contract.setAccountId(accountId);
				logger.info("XXXXXXXXX accountId: " + accountId);
			}
			if (serviceRequestNumber != null){
				contract.setServiceRequestNumber(serviceRequestNumber);
				logger.info("XXXXXXXXX serviceRequestNumber: " + serviceRequestNumber);
			}
			
			Long startTime = System.currentTimeMillis();

			ServiceRequestListResult serviceRequestListResult = serviceRequestService
			.retrieveServiceRequestHistoryList(contract);
			LEXLOGGER.logTime("** MPS PERFORMANCE TESTING RETRIEVE CONSUMABLE ASSET DETAIL ==>: " + (System.currentTimeMillis()- startTime)/1000.0);
			
			
			PerformanceUtil.calcTime(perfLogger, PerformanceConstant.SHAREDPORTLET_MSG_RETRIEVESERVICEREQUESTHISTORYLIST, startTime,System.currentTimeMillis(), PerformanceConstant.SYSTEM_SIEBEL,contract);
			
			List<ServiceRequest> serviceRequestList = serviceRequestListResult
			.getServiceRequests();
			int totalCount = serviceRequestListResult.getTotalCount();
			String xmlContent = getXmlOutputGenerator(request.getLocale())
			.convertServiceRequestsToXML(serviceRequestList, totalCount,
					contract.getStartRecordNumber(), generatorPatterns,
					false, timezoneOffset);
			PrintWriter out = response.getWriter();
			response.setContentType("text/xml");
			out.print(xmlContent);
			out.flush();
			out.close();
		} finally {
			globalService.releaseSessionHandle(crmSessionHandle);
		}
		logger.debug("-------------SR  retrieveServiceRequestHistoryListDrillDown ended---------");
	}
	
	public void retrieveCHLTreeXML(ResourceRequest request,
			ResourceResponse response, Model model) throws Exception {
		String nodeId = request.getParameter("id");
		boolean isRootRequired = true;
		logger.debug("-------------retrieveCHLTreeXML started---------NodeId="+nodeId);
		LocationReportingHierarchyContract contract = ContractFactory.getLocationReportingHierarchyContract(request);
		LEXLOGGER.info("start printing lex logger");
		
		ObjectDebugUtil.printObjectContent(contract,LEXLOGGER );
		
		LEXLOGGER.info("end printing lex loggger");
		if(nodeId == null){
			//Amind will retrieve the top level
			nodeId = LexmarkSPConstants.ROOT_NODE_ID;
			isRootRequired = true;
		}else if(nodeId.equals(LexmarkSPConstants.TREE_LEVEL_1)){
			//Amind will retrieve the top level
			isRootRequired = false;
		}else{
			//Amind will perform siebel search to retrieve the child node list of given nodeId
			contract.setChlNodeId(nodeId);
			isRootRequired = false;
		}
			
		PortletSession session = request.getPortletSession();
		
		
		LEXLOGGER.info("start printing lex logger");
		ObjectDebugUtil.printObjectContent(contract, LEXLOGGER);
		LEXLOGGER.info("end printing lex loggger");
		Long startTime = System.currentTimeMillis();
		
		AssetReportingHierarchyResult result = deviceService.retrieveAssetReportingHierarchy(contract);
		LEXLOGGER.logTime("** MPS PERFORMANCE TESTING RETRIEVE CHL TREE  ==>: " + (System.currentTimeMillis()- startTime)/1000.0);
		
		
		PerformanceUtil.calcTime(perfLogger, PerformanceConstant.SHAREDPORTLET_MSG_RETRIEVEASSETREPORTINGHIERARCHY, startTime,System.currentTimeMillis(), PerformanceConstant.SYSTEM_SIEBEL,contract);
		
		if(result == null || result.getChlNodeList() == null || result.getChlNodeList().size() == 0)
			{isRootRequired = false;}
		
		if(isRootRequired){
			result.setChlNodeList(generateRootNode(result, request.getLocale()));
		}
		String chlNodeTreeXML = getTreeGenerator(request.getLocale()).generateReportingHierarchyXML(result.getChlNodeList(),nodeId);
		PrintWriter out = response.getWriter();
		response.setContentType("text/xml");
		out.print(chlNodeTreeXML);
		out.flush();
		out.close();
		logger.debug("-------------retrieveCHLTreeXML end---------");
	}

	private List<CHLNode> generateRootNode(AssetReportingHierarchyResult result, Locale locale){
		boolean hasChild = false;
		List<CHLNode> chlNodeList = new ArrayList<CHLNode>();
		CHLNode rootNode = new CHLNode();
		if(result != null && result.getChlNodeList() != null && result.getChlNodeList().size() > 0)
			{hasChild = true;}
		rootNode.setCHLNodeId(LexmarkSPConstants.TREE_LEVEL_1);
		rootNode.setChlNodeName(PropertiesMessageUtil.getPropertyMessage(
				LexmarkSPConstants.MESSAGE_BUNDLE_NAME,"tree.label.customerHierarchy",locale));
		rootNode.setHasChild(hasChild);
		chlNodeList.add(rootNode);
		return chlNodeList;
	}
	
	public void retrieveDeviceLocationTreeXML(ResourceRequest request,
			ResourceResponse response, Model model,String pageFrom) throws Exception {
		logger.debug("-------------retrieveDeviceLocationTreeXML started---------");
		LocationReportingHierarchyContract contract = ContractFactory
				.getLocationReportingHierarchyContract(request);
		PortletSession session = request.getPortletSession();
		
		String DATEFORMAT = "MM/dd/yyyy" ;
	    final SimpleDateFormat sdf = new SimpleDateFormat(DATEFORMAT);
	    sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
	    final String utcTime = sdf.format(new Date());
		contract.setEntitlementEndDate(utcTime);
		LEXLOGGER.debug("pagefrom is"+pageFrom);
		contract.setPageName(pageFrom);
		
		LEXLOGGER.info("start printing lex logger");
		ObjectDebugUtil.printObjectContent(contract, LEXLOGGER);
		LEXLOGGER.info("end printing lex loggger");
		Long startTime = System.currentTimeMillis();
		AssetLocationsResult result = deviceService
		.retrieveAssetLocations(contract);
		LEXLOGGER.logTime("** MPS PERFORMANCE TESTING RETRIEVE Device Locations  ==>: " + (System.currentTimeMillis()- startTime)/1000.0);
		
		
		PerformanceUtil.calcTime(perfLogger, PerformanceConstant.SHAREDPORTLET_MSG_RETRIEVEASSETLOCATIONS, startTime,System.currentTimeMillis(), PerformanceConstant.SYSTEM_SIEBEL,contract);
		
		
		String xml = getTreeGenerator(request.getLocale()).
				generateDeviceLocationTreeXML(result.getAddressList());
		PrintWriter out = response.getWriter();
		response.setContentType("text/xml");
		out.print(xml);
		out.flush();
		out.close();
		logger.debug("-------------retrieveDeviceLocationTreeXML end---------");
	}
	
	
	/**.
	 * This method retrieves device location data for history page.
	 * @param request ResourceRequest
	 * @param response ResourceResponse
	 * @param model Model
	 * @throws Exception
	 * @author gsarkar
	 */
	public void retrieveRequestListDeviceLocationTreeXML(ResourceRequest request,
			ResourceResponse response, Model model) throws Exception {
		logger.debug("-------------retrieveRequestListDeviceLocationTreeXML started---------");
		LocationReportingHierarchyContract contract = ContractFactory
				.getLocationReportingHierarchyContract(request);
		/*********** This has been added for start date and end date in Location contract ****************/
		Date today = new Date();
		Calendar cal = new GregorianCalendar();
		cal.setTime(today);
		cal.add(Calendar.DAY_OF_MONTH, -30);
		Date before30Days = cal.getTime();
		before30Days = LexmarkConstants.DEFAULT_DATE_FORMAT.parse(LexmarkConstants.DEFAULT_DATE_FORMAT.format(before30Days));
		float timezoneOffset = Float.valueOf(request.getParameter(LexmarkConstants.TIMEZONE_OFFSET)).floatValue();
				
		contract.getFilterCriteria().put(ChangeMgmtConstant.SEARCHTYPE_DATERANGE_STARTDATE,DateUtil.convertDateToGMTString(DateUtil.adjustDateWithOffset(before30Days, timezoneOffset)));
		contract.getFilterCriteria().put(ChangeMgmtConstant.SEARCHTYPE_DATERANGE_ENDDATE,DateUtil.convertDateToGMTString(DateUtil.adjustDateWithOffset(today, timezoneOffset)));
		
		PortletSession session = request.getPortletSession();
		
		String DATEFORMAT = "MM/dd/yyyy" ;
	    final SimpleDateFormat sdf = new SimpleDateFormat(DATEFORMAT);
	    sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
	    final String utcTime = sdf.format(new Date());
		contract.setEntitlementEndDate(utcTime);
		
		LEXLOGGER.info("start printing lex logger");
		ObjectDebugUtil.printObjectContent(contract, LEXLOGGER);
		LEXLOGGER.info("end printing lex loggger");
		
		
		logger.debug("EntitlementEndDate is set to :"+utcTime);
		
		Long startTime = System.currentTimeMillis();
		
		RequestLocationsResult result = requestTypeService.retrieveRequestLocations(contract);
		LEXLOGGER.logTime("** MPS PERFORMANCE TESTING RETRIEVE Device Locations ==>: " + (System.currentTimeMillis()- startTime)/1000.0);
		
		
		PerformanceUtil.calcTime(perfLogger, PerformanceConstant.SHAREDPORTLET_MSG_RETRIEVEREQUESTLOCATIONS, startTime,System.currentTimeMillis(), PerformanceConstant.SYSTEM_SIEBEL,contract);
				
		String xml = getTreeGenerator(request.getLocale()).
				generateDeviceLocationTreeXML(result.getAddressList());
		PrintWriter out = response.getWriter();
		response.setContentType("text/xml");
		out.print(xml);
		out.flush();
		out.close();
		logger.debug("-------------retrieveRequestListDeviceLocationTreeXML end---------");
	}
	
	
	private TreeGenerator getTreeGenerator(Locale locale) {
		if (treeGenerator == null) {
			treeGenerator = new TreeGenerator(locale);
		} else {
			treeGenerator.setLocale(locale);
		}
		return treeGenerator;
	}

	public String getCHLTreeXMLURL(RenderResponse response) {
		ResourceURL url = response.createResourceURL();
		url.setResourceID("chlTreeXMLURL");
		chlNodeXMLURL = url.toString();
		return chlNodeXMLURL;
	}

	public String getDeviceLocationXMLURL(RenderResponse response) {
		ResourceURL url = response.createResourceURL();
		url.setResourceID("deviceLocationXMLURL");
		deviceLocationXMLURL = url.toString();
		return deviceLocationXMLURL;
	}

	public void retrieveGlobalLegalEntityList(String defaultValue,
			ResourceRequest request, ResourceResponse response)
			throws Exception {
		logger.debug("------------- Step 1---retrieveGlobalAccountList started---------["
						+ System.nanoTime() + "]");

		PortletSession session = request.getPortletSession();
		String inputMask = request.getParameter("mask");
		
		List<GlobalAccount> entityList = globalLegalEntityService.getGlobalAccounts();
		
		boolean hasMask = inputMask != null && !inputMask.equals("");
		if (hasMask) {
			CollectionFilter filter = new CollectionFilter(Locale.getDefault());
			HashMap<String, Object> noFuzzyFilter = new HashMap<String, Object>();
			noFuzzyFilter.put("legalName", inputMask);
			entityList = filter.filter(entityList, noFuzzyFilter, false);
		}
		PrintWriter out = response.getWriter();
		response.setContentType("text/xml");
		out.print(getXmlContent(entityList, defaultValue, hasMask));
		out.flush();
		out.close();
		logger.debug("------------- Step 1---retrieveGlobalAccountList end---------["
						+ System.nanoTime() + "]");
	}

	public Map getServiceRequestShipments(
			List<ServiceRequestOrderLineItem> shipmentOrderLines, Locale locale) {
		Map shipment = new HashMap();
		if (shipmentOrderLines != null) {
			List<List<ServiceRequestOrderLineItem>> returnShipments = listShipments(shipmentOrderLines);
			List<ShipmentForm> shipmentForms = new ArrayList<ShipmentForm>();
			ShipmentForm inProcessForm = new ShipmentForm();
			for (int i = 0; i < returnShipments.size(); i++) {
				ShipmentForm shipmentForm = new ShipmentForm();
				List<ServiceRequestOrderLineItem> orderLineItems = returnShipments
						.get(i);
			
					String[] requestShipmentGeneratorPatterns = new String[] {
							"productTLI", "productDescription", "serialNumber" };
					String requestShipmentNotificationsXML = getXmlOutputGenerator(
							locale).generate(orderLineItems, orderLineItems.size(),
							0, requestShipmentGeneratorPatterns);
					requestShipmentNotificationsXML = requestShipmentNotificationsXML
							.replace("\n", "");
					shipmentForm.setShipmentXML(StringUtil.encodeSingleQuote(
							requestShipmentNotificationsXML));
					if(orderLineItems.get(0) !=null){
						shipmentForm.setTrackingNumber(orderLineItems.get(0)
								.getTrackingNumber());
						shipmentForm.setCarrier(orderLineItems.get(0).getCarrier());
						//Get the carrier link here
						String carrier = orderLineItems.get(0).getCarrier();
						String trackingNumber = orderLineItems.get(0).getTrackingNumber();
						String trackingUrl="";
						boolean hasProperUrl=false;
						logger.info("Carrier name is "+carrier+" and trackingNumber is "+trackingNumber);
						//Get the carrier link from property file
						trackingUrl = getTrackingUrl(carrier, trackingNumber);
						shipmentForm.setCarrierLink(trackingUrl);
						hasProperUrl = trackingUrl.contains(trackingNumber);
						shipmentForm.setHasProperUrl(hasProperUrl);
						logger.info("Checking if proper url is there "+hasProperUrl);
						//Complete getting carrier link here
						shipmentForm.setShipmentProgress(checkProgress(orderLineItems
								.get(0).getStatus(), SHIPMENT));
					}
					
					
					if (shipmentForm.getTrackingNumber() != null
							&& !shipmentForm.getTrackingNumber().equals("")) {
						shipmentForms.add(shipmentForm);
					} else {
						inProcessForm = shipmentForm;
					}
				
				
				
			}
			shipment.put("shipmentForm", shipmentForms);
			shipment.put("inProcessForm", inProcessForm);

		}
		return shipment;
	}

	private Map getServiceRequestReturns(
			List<ServiceRequestOrderLineItem> shipmentOrderLines, Locale locale) {
		Map shipment = new HashMap();
		if (shipmentOrderLines != null) {
			List<List<ServiceRequestOrderLineItem>> returnShipments = listReturns(shipmentOrderLines);
			List<ShipmentForm> returnForms = new ArrayList<ShipmentForm>();
			ShipmentForm inProgressReturnNoTrackNumForm = new ShipmentForm();
			ShipmentForm deliveredReturnNoTrackNumForm = new ShipmentForm();
			for (int i = 0; i < returnShipments.size(); i++) {
				ShipmentForm shipmentForm = new ShipmentForm();
				List<ServiceRequestOrderLineItem> orderLineItems = returnShipments
						.get(i);
				String[] requestShipmentGeneratorPatterns = new String[] {
						"productTLI", "productDescription", "serialNumber" };
				String requestShipmentNotificationsXML = getXmlOutputGenerator(
						locale).generate(orderLineItems, orderLineItems.size(),
						0, requestShipmentGeneratorPatterns);
				requestShipmentNotificationsXML = requestShipmentNotificationsXML
						.replace("\n", "");
				shipmentForm.setShipmentXML(StringUtil.encodeSingleQuote(
						requestShipmentNotificationsXML));
				shipmentForm.setTrackingNumber(orderLineItems.get(0)
						.getTrackingNumber());
				shipmentForm.setCarrier(orderLineItems.get(0).getCarrier());
				//Get the carrier link here
				String carrier = orderLineItems.get(0).getCarrier();
				String trackingNumber = orderLineItems.get(0).getTrackingNumber();
				String trackingUrl="";
				logger.info("Carrier name is "+carrier+" and trackingNumber is "+trackingNumber);
				logger.info("Carrier name is "+carrier);
				//Get the carrier link from property file
				trackingUrl = getTrackingUrl(carrier, trackingNumber);
				boolean hasProperUrl = trackingUrl.contains(trackingNumber);
				shipmentForm.setHasProperUrl(hasProperUrl);
				logger.info("Checking if proper url is there "+hasProperUrl);
				shipmentForm.setCarrierLink(trackingUrl);
				//Complete getting carrier link here*/
				
				shipmentForm.setShipmentProgress(checkProgress(orderLineItems
						.get(0).getStatus(), RETURN));
				if (StringUtil.isStringEmpty(shipmentForm.getTrackingNumber())) {
					if (LexmarkConstants.SHIPMENTSTATUS_INPROCESS.equals(orderLineItems
							.get(0).getStatus())) {
						inProgressReturnNoTrackNumForm = shipmentForm; 
					} else if (LexmarkConstants.SHIPMENTSTATUS_DELIVERED.equals(orderLineItems
							.get(0).getStatus())) {
						deliveredReturnNoTrackNumForm = shipmentForm;
					}
				} else {
					returnForms.add(shipmentForm);
				}
			}
			shipment.put("returnsWithTrackNum", returnForms);
			if (inProgressReturnNoTrackNumForm.getShipmentProgress() > 0) {
				shipment.put("inProgressReturnNoTrackNumForm", inProgressReturnNoTrackNumForm);
			}
			if (deliveredReturnNoTrackNumForm.getShipmentProgress() > 0) {
				shipment.put("deliveredReturnNoTrackNumForm", deliveredReturnNoTrackNumForm);
			}
		}
		return shipment;
	}

	//lists return lines by tracking number.
	public List<List<ServiceRequestOrderLineItem>> listShipments(
			List<ServiceRequestOrderLineItem> shipmentOrderLines) {
		List<List<ServiceRequestOrderLineItem>> returnShipments = new ArrayList<List<ServiceRequestOrderLineItem>>();
		if (shipmentOrderLines != null) {
			List<ServiceRequestOrderLineItem> srOrderLineItems = new ArrayList<ServiceRequestOrderLineItem>();
			returnShipments.add(srOrderLineItems);
			for (int i = 0; i < shipmentOrderLines.size(); i++) {
				ServiceRequestOrderLineItem serviceRequestOrderLineItem = shipmentOrderLines
						.get(i);
				
				///Create the serial number comma separated String
				String serialNumberStr = "";
				int count = 0;
				if(serviceRequestOrderLineItem.getSerialNumbers() != null){
					for(String serialNumber: serviceRequestOrderLineItem.getSerialNumbers()){
						if(count==0){
							serialNumberStr = serialNumber;
						}else{
							serialNumberStr += ", " + serialNumber;
						}
						count += 1;
					}
					serviceRequestOrderLineItem.setSerialNumber(serialNumberStr);
				}
				
				
				boolean isContaint = false;
				for (int j = 0; j < returnShipments.size(); j++) {
					for (int k = 0; k < returnShipments.get(j).size(); k++) {
						if (serviceRequestOrderLineItem
								.getTrackingNumber()!=null && returnShipments.get(j).get(k).getTrackingNumber()
								.equals(
										serviceRequestOrderLineItem
												.getTrackingNumber()))
							{isContaint = true;}
						

					}
					if (returnShipments.size() == 0
							|| j == (returnShipments.size() - 1)) {
						if (isContaint == false) {
							List<ServiceRequestOrderLineItem> srOrderLineItem = new ArrayList<ServiceRequestOrderLineItem>();
							srOrderLineItem.add(serviceRequestOrderLineItem);
							returnShipments.add(srOrderLineItem);
							break;
						}
					}
					if (isContaint) {
						returnShipments.get(j).add(serviceRequestOrderLineItem);
						break;
					}
				}
			}
			returnShipments.remove(0);
		}
		return returnShipments;
	}

	//lists return lines by tracking number, or status when tracking number is null.
	private List<List<ServiceRequestOrderLineItem>> listReturns(
			List<ServiceRequestOrderLineItem> shipmentOrderLines) {
		List<List<ServiceRequestOrderLineItem>> returnShipments = new ArrayList<List<ServiceRequestOrderLineItem>>();
		if (shipmentOrderLines != null) {
			List<ServiceRequestOrderLineItem> inProgressReturnsNoTrackNumList = new ArrayList<ServiceRequestOrderLineItem>();
			List<ServiceRequestOrderLineItem> deliveredReturnsNoTrackNumList = new ArrayList<ServiceRequestOrderLineItem>();
			List<ServiceRequestOrderLineItem> srOrderLineItems = new ArrayList<ServiceRequestOrderLineItem>();
			returnShipments.add(srOrderLineItems);
			for (int i = 0; i < shipmentOrderLines.size(); i++) {
				ServiceRequestOrderLineItem serviceRequestOrderLineItem = shipmentOrderLines
						.get(i);
				
				// list returns without tracking number.
				if (StringUtil.isStringEmpty(serviceRequestOrderLineItem.getTrackingNumber())) {
					if (LexmarkConstants.SHIPMENTSTATUS_INPROCESS.equals(serviceRequestOrderLineItem.getStatus()) ||
							StringUtil.isStringEmpty(serviceRequestOrderLineItem.getStatus())) {
						inProgressReturnsNoTrackNumList.add(serviceRequestOrderLineItem);
					} else if (LexmarkConstants.SHIPMENTSTATUS_DELIVERED.equals(serviceRequestOrderLineItem.getStatus())) {
						deliveredReturnsNoTrackNumList.add(serviceRequestOrderLineItem);
					}
					continue;
				}
				
				// list returns with tracking number.
				boolean isContaint = false;
				for (int j = 0; j < returnShipments.size(); j++) {
					for (int k = 0; k < returnShipments.get(j).size(); k++) {
						if (returnShipments.get(j).get(k).getTrackingNumber()
								.equals(
										serviceRequestOrderLineItem
												.getTrackingNumber()))
							{isContaint = true;}
					}
					if (returnShipments.size() == 0
							|| j == (returnShipments.size() - 1)) {
						if (isContaint == false) {
							List<ServiceRequestOrderLineItem> srOrderLineItem = new ArrayList<ServiceRequestOrderLineItem>();
							srOrderLineItem.add(serviceRequestOrderLineItem);
							returnShipments.add(srOrderLineItem);
							break;
						}
					}
					if (isContaint) {
						returnShipments.get(j).add(serviceRequestOrderLineItem);
						break;
					}
				}
			}
			if (inProgressReturnsNoTrackNumList.size() > 0) {
				returnShipments.add(inProgressReturnsNoTrackNumList);
			}
			if (deliveredReturnsNoTrackNumList.size() > 0) {
				returnShipments.add(deliveredReturnsNoTrackNumList);
			}
			returnShipments.remove(0);
		}
		return returnShipments;
	}


	
	private int checkTechnicianProgress(String status) {
		if (LexmarkConstants.TECHNICIANSTATUS_ASSIGNED.equals(status)) {
			return 66;
		} else if (LexmarkConstants.TECHNICIANSTATUS_COMPLETE.equals(status)) {
			return 100;
		} else if (LexmarkConstants.TECHNICIANSTATUS_CANCELLED.equals(status)){
			return 0;
		} else {
			return 33;
		}
	}

	public int checkProgress(String status, String type) {
		int progress = 0;
		if (SHIPMENT.equals(type)) {
			if (LexmarkConstants.SHIPMENTSTATUS_SHIPPED.equals(status)) {
				progress = 66;
			} else if (LexmarkConstants.SHIPMENTSTATUS_DELIVERED.equals(status)) {
				progress = 100;
			} else if (LexmarkConstants.TECHNICIANSTATUS_CANCELLED.equals(status)){
				return 0;
			} else {
				progress = 33;
			}
		} else if (RETURN.equals(type)) { // return line only has two status: develivered, and in progress.one more added cancelled
			if (LexmarkConstants.SHIPMENTSTATUS_DELIVERED.equals(status)) {
				progress = 100;
			} else if (LexmarkConstants.TECHNICIANSTATUS_CANCELLED.equals(status)){
				return 1;
			} else {
				progress = 50;
			}
		}
		return progress;
	}
	
	public void assembleDevice(Asset device, Locale locale) {
		// construct supportURL and downloadsURL
		String urlHost = PropertiesMessageUtil.getPropertyMessage(
				"com.lexmark.services.resources.hostConfig",
				"markDirectHost", Locale.US);
		String URLPart2 = "&lang=" + locale.getLanguage() + "&country=" +
                locale.getLanguage() + "_" + locale.getCountry() + "&partnumber=" +
                device.getProductTLI();
		device.setSupportUrl(urlHost + "support" + URLPart2);
		device.setDownloadsUrl(urlHost + "downloads" + URLPart2);
		
		// populate control panel URL
		if (!StringUtil.isStringEmpty(device.getHostName())) {
			device.setControlPanelURL("http://" + device.getHostName());
		} else if (!StringUtil.isStringEmpty(device.getIpAddress())) {
			device.setControlPanelURL("http://" + device.getIpAddress());
			
		}
	}
	private String getXmlContent(List<GlobalAccount> result,
			String defaultValue, boolean hasMask) {
		StringBuffer sb = new StringBuffer();
		sb.append("<complete>");
		boolean alreadyIn = false;
		for (GlobalAccount le : result) {

			String legalName = le.getLegalName() + " (" + le.getDisplayMdmId() + ")";	// added for CI5 multiple customer report
			sb.append("<option value=\"");
			XMLEncodeUtil.appendXML(sb, legalName);
			sb.append( "\"");
			if (legalName.equals(defaultValue)) {
				sb.append(" checked=\"1\"");
				alreadyIn = true;
			}
			sb.append(">");
			sb.append("<![CDATA[");
 			sb.append(le.getLegalName() + " (" + le.getDisplayMdmId() + ")");
			sb.append("]]>");
			sb.append("</option>");
		}
		if ((!hasMask) && (!alreadyIn)) {
			sb.append("<option value=\"");
			XMLEncodeUtil.appendXML(sb, defaultValue);
			sb.append("\" checked=\"1\">");
			sb.append("<![CDATA[");
 			sb.append(defaultValue);
			sb.append("]]>");
			sb.append("</option>");
		}
		sb.append("</complete>");
		return sb.toString();
	}
	
	private String retrieveProductImageUrl(String productTLI) throws Exception {
		ProductImageContract productImageContract = new ProductImageContract();
		productImageContract.setPartNumber(productTLI);
		ProductImageResult productImageResult = productImageService
				.retrieveProductImageUrl(productImageContract);
		return productImageResult.getProductImageUrl();
	}
	private List<ServiceRequestActivity> assembleServiceRequestActivity (List<ServiceRequestActivity> list){
		SRAdministrationListContract contract = ContractFactory.getSRAdministrationListContract();
		SRAdministrationListResult result = serviceRequestLocaleService.retrieveSRAdministrationList(contract);
		Map orderMap = new HashMap<Object, Object>();
		if (result.isSucceed()) {
			List<SiebelLocalization> siebelLocalizations = result.getSiebelLocalizations();
			for (SiebelLocalization siebelLocalization : siebelLocalizations){
				orderMap.put(siebelLocalization.getSiebelValue(), siebelLocalization.getStatusOrder());
			}
		}
		for(ServiceRequestActivity serviceRequestActivity : list){
			serviceRequestActivity.setStatusOrder((Integer)orderMap.get(serviceRequestActivity.getActivityStatus()));
		}
		return list;
	}
	private List orderListByStatusOrder(List list){
		CollectionSorter sorter = new CollectionSorter();
		String sortCriteria = "statusOrder:" + sorter.SORT_ASCENDING;
		return sorter.sort(list , sortCriteria);
	}
	public String createXmlForOtherParams (PortletSession session, String xmlContent, boolean mdmParamRequire){
	 	//parameter 1 cntry
		String cntry = getCountry(session);
		logger.debug("cntry is "+ cntry);
		//parameter 2 language
		String Lang = getLanguage(session);
		logger.debug("Language is "+Lang);
		
		if(mdmParamRequire){//That means the checkbox is checked and need to send other paremeters like mdmid,mdmlevel and chl
			
			//parameter 3 mdm id
			String MDM_ID = getMdmId(session);
			//parameter 4 mdm level
			String MDM_LEVEL = getMdmLevel(session);
			//parameter 5 chl
			String CHL = getChlNodeId(session)!=null?getChlNodeId(session):"NOVALUE";
			logger.debug("Checkbox checked so sending mdmid,level and chlnode id");
			logger.debug("Mdm id is "+MDM_ID);
			logger.debug("Mdm id is "+MDM_LEVEL);
			logger.debug("CHL node is "+CHL);
			xmlContent= xmlContent +
			"<sawx:expr xsi:type=\"sawx:sqlExpression\">MDM_ID</sawx:expr>" +
			"<sawx:expr xsi:type=\"xsd:string\">" + MDM_ID + "</sawx:expr>" +
			"<sawx:expr xsi:type=\"sawx:sqlExpression\">MDM_LEVEL</sawx:expr>" +
			"<sawx:expr xsi:type=\"xsd:string\">" + MDM_LEVEL + "</sawx:expr>"+
			"<sawx:expr xsi:type=\"sawx:sqlExpression\">CHL</sawx:expr>" +
			"<sawx:expr xsi:type=\"xsd:string\">" + CHL + "</sawx:expr>";
		}
		xmlContent= xmlContent +
		"<sawx:expr xsi:type=\"sawx:sqlExpression\">cntry</sawx:expr>" +
		"<sawx:expr xsi:type=\"xsd:string\">" + cntry + "</sawx:expr>" +
		"<sawx:expr xsi:type=\"sawx:sqlExpression\">Lang</sawx:expr>" +
		"<sawx:expr xsi:type=\"xsd:string\">" + Lang + "</sawx:expr>";
	 return xmlContent;
	}
 
	public String getMdmId(PortletSession session){
		ServicesUser servicesUser = (ServicesUser) session.getAttribute(LexmarkConstants.SERVICES_USER, session.APPLICATION_SCOPE);
		if (servicesUser.getMdmId()!= null && !servicesUser.getMdmId().equals("")){
			return servicesUser.getMdmId();
		}else{
			Map<String, Object> ldapUserData =  (Map)session.getAttribute(LexmarkConstants.LDAP_USER_DATA, session.APPLICATION_SCOPE);
			if(ldapUserData != null)
				{return (String)ldapUserData.get(LexmarkConstants.MDMID);}
			else
				{return null;}
		}
	}
	public String getMdmLevel(PortletSession session){
	 	ServicesUser servicesUser = (ServicesUser) session.getAttribute(LexmarkConstants.SERVICES_USER, session.APPLICATION_SCOPE);
		if (servicesUser.getMdmLevel()!= null && !servicesUser.getMdmLevel().equals("")){
			return servicesUser.getMdmLevel();
		}else{
			Map<String, Object> ldapUserData =  (Map)session.getAttribute(LexmarkConstants.LDAP_USER_DATA, session.APPLICATION_SCOPE);
			if(ldapUserData != null)
				{return (String)ldapUserData.get(LexmarkConstants.MDMLEVEL);}
			else
				{return null;}
		}
	}
	public String getChlNodeId(PortletSession session){
		ServicesUser servicesUser = (ServicesUser) session.getAttribute(LexmarkConstants.SERVICES_USER, session.APPLICATION_SCOPE);
		if (servicesUser.getChlNodeId()!= null && !servicesUser.getChlNodeId().equals("")){
			return servicesUser.getChlNodeId();
		}else{
			return null;
		}
	}
	public String getLanguage(PortletSession session){
		Map<String, Object> ldapUserData =  (Map)session.getAttribute(LexmarkConstants.LDAP_USER_DATA, session.APPLICATION_SCOPE);
		if(ldapUserData != null)
			{return (String)ldapUserData.get(LexmarkConstants.LANGUAGE);}
		else
			{return null;}
	}
	public String getCountry(PortletSession session){
		Map<String, Object> ldapUserData =  (Map)session.getAttribute(LexmarkConstants.LDAP_USER_DATA, session.APPLICATION_SCOPE);
		if(ldapUserData != null)
			{return (String)ldapUserData.get(LexmarkConstants.COUNTRY);}
		else
			{return null;}
	}
	private String getSubStringFromZeroToNthPattern(String str , String pattern , int index){
		if(str==null)
			{return "";}
		int start = -1;
		int i = 0;
		int last = 0;
		int size = str.length();
		for (i = 0 ;i<index ;i++){
			if(size>start+1)
				{start = str.indexOf(pattern, (start+1));}
			else{
				start = -1;
				break;
				}
			if(start ==-1)
				{break;}
			
			last = start;
		}
		if(start!= -1)
			{return str.substring(0, last);}
		if(start==-1&& i==(index-1))
			{return str;}
		else
			{return "";}
	}

	private String localizeServiceRequestStatus(String sievalue, Locale locale){
		   if(sievalue != null){
 
		    LocalizedServiceStatusResult result = new LocalizedServiceStatusResult();
		    LocalizedServiceStatusContract contract = new LocalizedServiceStatusContract();
		    contract.setLocale(locale);
		    contract.setSiebelValue(sievalue);
		    try{
		     result = serviceRequestLocale.retrieveLocalizedServiceStatus(contract);
		     sievalue = result.getLocalizedString();
		    }catch(Exception e){
		     //Do not throw out exception
		     logger.debug("Failed to retrieve localized service request status!"+e.getMessage());
		    }
		   }
		  return sievalue;
    }
	
	private String localizeEntitlementServiceDetail(String siebelValue, Locale locale) {
		String localizedEntitlementServiceDetail = null;
		if (!StringUtil.isStringEmpty(siebelValue)) {
			LocalizedEntitlementServiceDetailContract contract = ContractFactory.
					getLocalizedEntitlementServiceDetailContract(siebelValue, locale);
			try {
				localizedEntitlementServiceDetail = serviceRequestLocale.
						retrieveLocalizedEntitlementServiceDetail(contract).getLocalizedString();
			} catch(Exception e){
			     //Do not throw out exception
			     logger.debug("Failed to retrieve localized entitlement service detail!"+e.getMessage());
			}
		}
		return localizedEntitlementServiceDetail;
	}

	private XmlOutputGenerator getXmlOutputGenerator(Locale locale) {
		XmlOutputGenerator xmlOutputGenerator = new XmlOutputGenerator(locale);
		xmlOutputGenerator.setServiceRequestLocale(serviceRequestLocale);
		return xmlOutputGenerator;
	}
	private static Properties bulkUploadProperty;
	 public static Properties getConfigProperties() {
			if(bulkUploadProperty == null) {
				Properties props = new Properties();
				InputStream in;
				try {
					in = SharedPortletController.class.getResourceAsStream(CONFIG_FILE_NAME);
					props.load(in);
					in.close();
				} catch (IOException e) {
					new RuntimeException("Fail to get TrackingLink.properties Property configuration file.", e);
				}
				bulkUploadProperty =  props;
			}
			return bulkUploadProperty;
		}
	 public String getTrackingUrl(String carrierName, String trackingNumber) {		
			String shipmentTrackingUrl = "";
			ResourceBundle res = ResourceBundle.getBundle("TrackingLink");
			String carrierLinkFromProperty="";
			try {
				carrierLinkFromProperty = res.getString(carrierName);
			} catch (Exception e) {
				logger.debug("Exception "+e.getMessage());
			}
			String tracknum = "";
			if(StringUtils.isNotBlank(trackingNumber)){
				tracknum = trackingNumber.substring(2);	
			}
			
			logger.info("Carrier link from property is "+carrierLinkFromProperty+"last appended-------------"+ tracknum);
			logger.debug(" Carrier name Passed is" + carrierName+"last appended-------------"+ tracknum);
			if (carrierLinkFromProperty!=null && carrierLinkFromProperty!=""){
				shipmentTrackingUrl = carrierLinkFromProperty.replaceAll("%trackingnumber%", trackingNumber).replaceAll("%tracknum%", tracknum);
				
			}
			logger.info("Final tracking url is "+shipmentTrackingUrl);
			return shipmentTrackingUrl;
		}

//	 added for MPS breakfix
	 private FileUploadForm getAttachmentDetails() {
		 FileUploadForm f = new FileUploadForm();
		 f.setAttachmentFileMap(new LinkedHashMap<String, FileObject>());
		 return f;
	 }
	 
	 /**
	  * @wipro : CI-6 : Changes
	  * @param serviceRequestNumber
	  */
	 private void doAttachmentFilesSubmit(ActionRequest request, CreateServiceRequestResult srResult, ServiceRequestConfirmationForm form) throws Exception {
			PortletSession session = request.getPortletSession();
			Map<String, FileObject> fileMap = (Map<String, FileObject>)session.getAttribute(ChangeMgmtConstant.SESSION_FILE_MAP);
			try {
				logger.debug("---------------- Calling the UPLOAD ATTACHMENT FOR CREATE SR.... --------------");

				if(fileMap != null) {
					List<Attachment> attachmentList = new LinkedList<Attachment>();
					Iterator<String> fileNameIterator = fileMap.keySet().iterator();
					while(fileNameIterator.hasNext()) {
							String 		fileName 	= fileNameIterator.next();
							FileObject 	fileObject 	= fileMap.get(fileName);
								String fileSize	=	fileObject.getFileSize();
								float floatFileSize=Float.parseFloat(fileSize);
								// File Size
								double fileSizeDisplay=Double.valueOf(fileObject.getFileSizeInBytes());
								logger.debug("File Size is " + fileSizeDisplay);
								fileSizeDisplay=fileSizeDisplay/1024;
								BigDecimal roundedFileSizeDisplay = new BigDecimal(fileSizeDisplay);
								roundedFileSizeDisplay = roundedFileSizeDisplay.divide(new BigDecimal("1"), 2, BigDecimal.ROUND_HALF_UP);
								logger.debug("roundedFileSizeDisplay value is "+roundedFileSizeDisplay);
								
								// File Name for display
								String fileNameWithTimestamp=fileObject.getFileName();
								logger.debug("fileNameWithTimestamp is "+fileNameWithTimestamp);
								String displayAttachment = fileNameWithTimestamp.substring(0,fileNameWithTimestamp.lastIndexOf('_'));
								
								String fileExt = fileNameWithTimestamp.substring(fileNameWithTimestamp.lastIndexOf(".")+1, fileNameWithTimestamp.length()).toLowerCase();
								logger.debug("fileExt is :::  :: " + fileExt);
								
								String displayAttachmentName = displayAttachment+"."+fileExt;
								logger.debug("Final displayFileName is "+displayAttachmentName);
								

							Attachment file = new Attachment();
								file.setAttachmentName(fileObject.getFileName());
								file.setSize((int)floatFileSize);
								file.setSizeForDisplay(String.valueOf(roundedFileSizeDisplay));
								file.setDisplayAttachmentName(displayAttachmentName);
						attachmentList.add(file);
					}
					
					for(int i=0;i<attachmentList.size();i++){
						logger.debug("---------------- Printing attachment name --------------");
						logger.debug("----------"+attachmentList.get(i).getAttachmentName());
					}
					
					CrmSessionHandle crmSessionHandle = globalService.initCrmSessionHandle(PortalSessionUtil.getSiebelCrmSessionHandle(request));
	        			AttachmentContract atchmntContract = new AttachmentContract();
	        					atchmntContract.setSessionHandle(crmSessionHandle);
	        		logger.info("--------SERVICE REQUEST ROW ID---------->>"+srResult.getServiceRequestRowId());
				        		atchmntContract.setIdentifier(srResult.getServiceRequestRowId());
				        		atchmntContract.setRequestType(ChangeMgmtConstant.ATTACHMENT_REQUEST_TYPE);		//For service request you have to send 
				        		atchmntContract.setAttachments(attachmentList);
	        		attachmentService.uploadAttachments(atchmntContract);
				}
			}
			catch (LGSBusinessException e) {
				throw new LGSBusinessException();
			}
			catch (SiebelCrmServiceException e) {
				throw new SiebelCrmServiceException();
			}
			catch (Exception e) {
				logger.debug("Exception "+e.getMessage());
			}
			session.removeAttribute(ChangeMgmtConstant.SESSION_FILE_MAP);
			form.setAttachmentFileMap(fileMap);
		}
	 

	 	@Async
		public void sendAttachmentList(String serviceRequestNumber){
			logger.debug("---------------- Inside the resourcemapping for sending attachment ---------------------");
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				logger.debug("Exception "+e.getMessage());
			}
			logger.debug("------------------ SR number is "+serviceRequestNumber);
			logger.debug("------------ Need to check if we can retrieve the SR number other than the session -----------------");
			logger.debug("---------------- Exiting the resourcemapping for sending attachment ---------------------");
		}
		
		

}
