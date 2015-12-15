/**
* Copyright@ 2012. This document has been created & written by
 * Wipro technologies for Lexmark and this is copyright to Lexmark
 *
 * File         	: ServiseRequestHistoryController
 * Package     		: com.lexmark.services.portlet.common
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
package com.lexmark.services.portlet.common;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.portlet.PortletSession;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.amind.common.util.StringUtils;
import com.lexmark.constants.LexmarkConstants;
import com.lexmark.contract.AttachmentContract;
import com.lexmark.contract.FavoriteContract;
import com.lexmark.contract.RequestListContract;
import com.lexmark.domain.ServiceRequest;
import com.lexmark.result.AttachmentResult;
import com.lexmark.result.FavoriteResult;
import com.lexmark.result.RequestListResult;
import com.lexmark.service.api.AttachmentService;
import com.lexmark.service.api.ContactService;
import com.lexmark.service.api.CrmSessionHandle;
import com.lexmark.service.api.DeviceService;
import com.lexmark.service.api.GlobalService;
import com.lexmark.service.api.ProductImageService;
import com.lexmark.service.api.RequestTypeService;
import com.lexmark.service.api.ServiceRequestService;
import com.lexmark.services.LexmarkSPConstants;
import com.lexmark.services.form.ServiceRequestListForm;
import com.lexmark.services.portlet.BaseController;
import com.lexmark.services.portlet.SharedPortletController;
import com.lexmark.services.util.ChangeMgmtConstant;
import com.lexmark.services.util.ContractFactory;
import com.lexmark.services.util.ObjectDebugUtil;
import com.lexmark.services.util.PortalSessionUtil;
import com.lexmark.services.util.ResourceResponseUtil;
import com.lexmark.services.util.ServiceStatusErrorMessageUtil;
import com.lexmark.services.util.ServiceStatusUtil;
import com.lexmark.util.DownloadFileLocalizationUtil;
import com.lexmark.util.DownloadFileUtil;
import com.liferay.portal.util.PortalUtil;

/**
 * @author gsarkar
 *
 */
@Controller
@RequestMapping("VIEW")
public class ServiseRequestHistoryController extends BaseController{
	
	private static Logger logger = LogManager.getLogger(ServiseRequestHistoryController.class);
	private static String FILENAME_CSV;
	private static String[] SERVICEREQUEST_HEADER;
	
	@Autowired
	private SharedPortletController sharedPortletController;
	@Autowired
	private ServiceRequestService serviceRequestService;
	@Autowired
	private DeviceService deviceService;
	@Autowired
	private ContactService contactService;
    @Autowired
    private GlobalService  globalService;
    @Autowired
    private ProductImageService  productImageService;
	@Autowired
	private CommonController commonController; //Added for CI-7 Defect #8217
    
    @Autowired
	private RequestTypeService requestTypeService;
    
    @Autowired
    private AttachmentService attachmentService;
    

    // Added for LEX:AIR00075895 Started
    private static final String[] serviceRequestColumns = new String[] { "serviceRequestNumber", "serviceRequestNumber", "serviceRequestDate", "asset.serialNumber", "productModel", "problemDescription", "resolutionCode", "serviceRequestStatus", "accountName", "serviceAddress.addressName", "serviceAddress.officeNumber", "serviceAddress.city", "serviceAddress.state", "serviceAddress.province", "serviceAddress.county", "serviceAddress.district", "serviceAddress.postalCode", "serviceAddress.country", "custRefNumber", "primaryContact.firstName", "primaryContact.lastName", "primaryContact.emailAddress", "primaryContact.workPhone", "contractType", "asset.deviceTag", "asset.hostName", "asset.assetCostCenter"};
 // Added for LEX:AIR00075895 Ended
    
	@RequestMapping(params="action=showBreakFixSRList")//RequestMapping params added by sagar
	public String showMpsServiceRequestListPage(Model model, RenderRequest request, RenderResponse response) throws Exception{
		logger.debug("ServiseRequestHistoryController.showMpsServiceRequestListPage.Enter :");
		boolean createServiceRequestFlag = sharedPortletController.isCreateServiceRequestFlag(request);
		sharedPortletController.checkAccountTypeFlagsInSession(request);
		PortletSession session = request.getPortletSession();
		String accountTypeMPSFlag = (String) session.getAttribute(SharedPortletController.ACCOUNT_TYPE_MPS_FLAG);
		String accountTypeCSSFlag = (String) session.getAttribute(SharedPortletController.ACCOUNT_TYPE_CSS_FLAG);
		model.addAttribute(SharedPortletController.ACCOUNT_TYPE_MPS_FLAG, accountTypeMPSFlag);
		model.addAttribute(SharedPortletController.ACCOUNT_TYPE_CSS_FLAG, accountTypeCSSFlag);

	
		ServiceRequestListForm serviceRequestListForm = new ServiceRequestListForm();
		
		ResourceURL resURL = response.createResourceURL();
		resURL.setResourceID("downloadServiceRequestsURL");
		model.addAttribute("downloadServiceRequestsURL", resURL.toString());
		serviceRequestListForm.setCreateServiceRequestFlag(createServiceRequestFlag);
		//Add Device Location tree
		serviceRequestListForm.setDeviceLocationTreeXMLURL(sharedPortletController.getDeviceLocationXMLURL(response));
		//Add CHL node tree
		serviceRequestListForm.setChlTreeXMLURL(sharedPortletController.getCHLTreeXMLURL(response));
		// Retrieve Grid Setting
		retrieveGridSetting("serviceRequestListGrid",request,response,model);
		model.addAttribute("serviceRequestListForm", serviceRequestListForm);
		logger.debug("ServiseRequestHistoryController.showMpsServiceRequestListPage.Exit :");
		return "serviceRequest/serviceRequestListPage";
	}
	
	
	@ResourceMapping("retrieveBreakfixSRList")
	public void retrieveServiceRequestList(ResourceRequest request, ResourceResponse response) throws Exception{
		logger.debug("------------- Step 1---retrieveServiceRequestList started---------["+System.nanoTime()+"]");
		

		//CI-6 Changes Start --------- PARTHA ---------
		

               // Added for mps Phase 2 (account name)
		//customerReportingName fetched instead of productModel CI #10118
		String[] generatorPatterns = new String[] { "serviceRequestNumber", "serviceRequestDate", 
				"asset.serialNumber", "asset.customerReportingName", "problemDescription", "resolutionCode", "serviceRequestStatus","accountName",
				"serviceAddress.addressName", "serviceAddress.officeNumber", "serviceAddress.city", "serviceAddress.state", 
				"serviceAddress.province","serviceAddress.county", "serviceAddress.district", "serviceAddress.postalCode", "serviceAddress.country", 
				"helpdeskReferenceNumber", "primaryContact.firstName", "primaryContact.lastName", "primaryContact.emailAddress", 
				"primaryContact.workPhone", "contractType", "asset.deviceTag", "asset.hostName", "asset.assetCostCenter","requestStatus","subStatus","severity"};
		//CI-6 Changes End ----------- PARTHA ----------
		
		
		
		RequestListContract contract = ContractFactory
		.getHistoryListContract(request, "ServiceRequestList",
				serviceRequestColumns);
		
		loadFilterCriteria(request,contract);
		
		PortletSession session = request.getPortletSession();

		/*if(pageType != null) {			
				if((LexmarkConstants.VIEWTYPE_MANAGED_DEVICES).equalsIgnoreCase(pageType)) {
				contract.setAssetType(LexmarkConstants.VIEWTYPE_MANAGED_DEVICES);
			} else if((LexmarkConstants.VIEWTYPE_NON_MANAGED_DEVICES).equalsIgnoreCase(pageType)) {
				contract.setAssetType(LexmarkConstants.VIEWTYPE_NON_MANAGED_DEVICES);
			}
		}*/
		session.setAttribute("downLoadContract", contract);
		
		
		sharedPortletController.retrieveServiceRequestList(contract, request, response, generatorPatterns,true);

		logger.debug("------------- Step 1---retrieveServiceRequestList end---------["+System.nanoTime()+"]");
	}
	
	
	

	@ResourceMapping(value="retrieveAssociatedServiceTicketList")
	public void retrieveAssociatedServiceTicketList(ResourceRequest request,
			ResourceResponse response, Model model)throws Exception{

		sharedPortletController.retrieveAssociatedServiceTicketList(request, response, model);
	}
	
	@ResourceMapping(value="retrieveServiceRequestHistoryList")
	public void retrieveServiceRequestHistoryList(ResourceRequest request,
			ResourceResponse response, Model model)throws Exception{

		sharedPortletController.retrieveServiceRequestHistoryList(request, response, model);
	}
	
	@ResourceMapping(value="retrieveServiceRequestHistoryListDrillDown")
	public void retrieveServiceRequestHistoryListDrillDown(ResourceRequest request,
			ResourceResponse response, Model model)throws Exception{

		sharedPortletController.retrieveServiceRequestHistoryListDrillDown(request, response, model);
	}

	

	/**
	 *  retrieve Services Requests and down load it csv and pdf format.

	 * @param request
	 * @throws Exception
	 */
	@ResourceMapping("downloadServiceRequestsURL")
	public void downloadServiceRequestsURL(ResourceRequest request,
			ResourceResponse response, Model model) throws Exception{

		logger.debug("-------------download request service started---------");
		String downloadType = request.getParameter("downloadType");
		Locale locale = request.getLocale();
		PortletSession session = request.getPortletSession();
		//Earlier contract has been changed for MPS (by sagar)
		
		RequestListContract contract =  (RequestListContract) session.getAttribute("downLoadContract");
		
		contract.setStartRecordNumber(0);
		contract.setIncrement(Integer.valueOf(MINUES_ONE));
		
		/******This is done to resolve the break fix csv issue ************/
		logger.debug("Going for printing the contract before csv for break fix");
		List<String> requestTypeList = new ArrayList<String>();
		requestTypeList.add( ChangeMgmtConstant.SRTYPE_BREAKFIX);
		contract.getFilterCriteria().put("requestType",requestTypeList);
		
		ObjectDebugUtil.printObjectContent(contract, logger);
		
		CrmSessionHandle crmSessionHandle = globalService.initCrmSessionHandle(PortalSessionUtil.getSiebelCrmSessionHandle(request));
		try {
			contract.setSessionHandle(crmSessionHandle);

			//Earlier service call has been changed for MPS(by Sagar)
			
			
			
			RequestListResult serviceRequestListResult = requestTypeService.retrieveRequestList(contract);
		
			List<ServiceRequest> serviceRequests = serviceRequestListResult.getRequestList();
			logger.debug("ServiceRequestHistoryController.downloadServiceRequestsURL.downloadType---->"+downloadType);
			if("csv".equals(downloadType)){
				downloadServiceRequestCSV(response,serviceRequests,locale);
			}else if("pdf".equals(downloadType)){
				downloadPDF(response,serviceRequests,locale);
			}else{
				throw new Exception(ServiceStatusErrorMessageUtil.getErrorMessage(LexmarkSPConstants.ERROR_MESSAGE_BUNDLE, "exception.portlet.downloadException", null, request.getLocale()));
			}
		} finally {
			globalService.releaseSessionHandle(crmSessionHandle);
		}
		logger.debug("-------------download service request end---------");
	}
	
	@ResourceMapping("chlTreeXMLURL")
	public void retrieveCHLTreeXML(ResourceRequest request,
			ResourceResponse response, Model model) throws Exception{

		sharedPortletController.retrieveCHLTreeXML(request, response, model);
	}

	@ResourceMapping("deviceLocationXMLURL")
	public void retrieveDeviceLocationTreeXML(ResourceRequest request,
			ResourceResponse response, Model model) throws Exception{
      String pageFrom="";
		sharedPortletController.retrieveDeviceLocationTreeXML(request, response, model,pageFrom);
	}

	@ResourceMapping("getState")
	public void getState(ResourceRequest request,
			ResourceResponse response, Model model) throws Exception{

		logger.debug("Request value inside the controller "+request.getParameter("countryCode"));
		sharedPortletController.getState(request, response, model);
	}
	//*********************** END OF RESOURCE MAPPING ***************************//
	



	private void downloadServiceRequestCSV(ResourceResponse response, List<ServiceRequest> serviceRequestList,
			Locale locale) throws IOException {

		FILENAME_CSV = DownloadFileLocalizationUtil.getServiceRequestsCSVFileName(locale);
		response.setProperty("Content-disposition", "attachment; filename="
				+ FILENAME_CSV);

		response.setContentType("text/csv");
		PrintWriter out = ResourceResponseUtil.getUTF8PrintWrtierWithBOM(response);
		out.print(DownloadFileUtil.assembleToServiceRequestCSV(serviceRequestList, locale));
		out.flush();
		out.close();
	}
	


	private void downloadPDF(ResourceResponse response, List<ServiceRequest> serviceRequestList,
			Locale locale) throws IOException {
		logger.debug("ServiceRequestHistoryController.downloadServiceRequestsURL.downloadPDF Enter---->");
		String fileName = DownloadFileLocalizationUtil.getServiceRequestsPDFFileName(locale);
		SERVICEREQUEST_HEADER = DownloadFileLocalizationUtil.getServiceRequestsPDFHeaders(locale);
		//TODO figure out where to configure generatorPatterns
		
		//CI-6 Update the last four columns
		
		//For MPS Phase 2.1
		String[] generatorPatterns = new String[]{"serviceRequestNumber","serviceRequestDate",
				"asset.serialNumber", "asset.modelNumber","problemDescription","resolutionCode", "serviceRequestStatus","serviceAddress.addressName","serviceAddress.officeNumber",
				"serviceAddress.city","serviceAddress.state",
				"serviceAddress.province","serviceAddress.county","serviceAddress.district","serviceAddress.postalCode","serviceAddress.country",
				"helpdeskReferenceNumber","primaryContact.firstName","primaryContact.lastName","primaryContact.emailAddress",
				"primaryContact.workPhone","contractType","asset.deviceTag","asset.hostName","asset.assetCostCenter","accountName","requestStatus","subStatus","severity"};
		
		logger.debug("ServiceRequestHistoryController.downloadServiceRequestsURL.downloadPDF Exit...invoking sharedPortletController.downloadPDF---->");
		
		sharedPortletController.downloadPDF(response, serviceRequestList, locale, SERVICEREQUEST_HEADER, generatorPatterns, fileName);

	}
	
	

	
	@ResourceMapping("setContactFavouriteFlag")
	public void setContactFavouriteFlag(@RequestParam("contactId") String contactId,
			@RequestParam("favoriteContactId") String favoriteContactId,
			@RequestParam("flagStatus") boolean flagStatus,
			ResourceRequest request, ResourceResponse response) throws Exception {

		boolean success = false;
		try {
			logger.debug("-------------setContactFavourite started---------");		
			logger.debug("contactId is "+ contactId);
				


			FavoriteContract favoriteContract = ContractFactory.getFavoriteContract(request);
			
			FavoriteResult favoriteResult;
			favoriteContract.setFavoriteContactId(favoriteContactId);
			favoriteContract.setContactId(contactId);
			favoriteContract.setFavoriteFlag(!flagStatus);
			
			PortletSession session = request.getPortletSession();
			
			logger.debug("-------------before setting contract favourite---------");		
			

			favoriteResult = contactService.updateUserFavoriteContact(favoriteContract);
			
			logger.debug("-------------setContactFavourite end---------");
			success = favoriteResult.isResult();
		} catch (Exception e) {
			success = false;
		}
		logger.debug("success: "+success);
		String errorCode = success?"message.servicerequest.setContactFavouriteFlag"
				:"exception.servicerequest.setContactFavouriteFlag";
		ServiceStatusUtil.responseResult(response, errorCode, request.getLocale(),"\""+favoriteContactId+"\"");
	}
	
	

	
	private void loadFilterCriteria(ResourceRequest request, RequestListContract contract){
		if (request.getParameter("country") != null) {
			contract.getFilterCriteria().put("serviceAddress.country", request.getParameter("country"));
		}
		if (request.getParameter("province") != null) {
			contract.getFilterCriteria().put("serviceAddress.province", request.getParameter("province"));
		}
		if (request.getParameter("state") != null) {
			contract.getFilterCriteria().put("serviceAddress.state", request.getParameter("state"));
		}
		if (request.getParameter("city") != null) {
			contract.getFilterCriteria().put("serviceAddress.city", request.getParameter("city"));
		}
		if (request.getParameter("chlNodeId") != null) {
			contract.setChlNodeId(request.getParameter("chlNodeId"));
		}
	}
	
	@RequestMapping(params = "action=showServiceRequestDrillDownPage")
	public String showServiceRequestDrillDownPage(RenderRequest request, RenderResponse response, Model model) throws Exception{
		//logger.debug("-------------serviceRequestDrillDown started---------");
		//String timezoneOffsetStr = request.getParameter(LexmarkConstants.TIMEZONE_OFFSET);
		//logger.debug("timezoneOffset is "+request.getParameter(request.getParameter(LexmarkConstants.TIMEZONE_OFFSET)));
		//logger.debug("timezone offset string is "+timezoneOffsetStr);
		HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));
		String timeZoneOffset = httpReq.getParameter(ChangeMgmtConstant.TIMEZNOFFSET);
		logger.debug("time zone offset is "+timeZoneOffset);
		model.addAttribute("timezoneOffset",timeZoneOffset);
		sharedPortletController.showServiceRequestDrillDownPage(request, response, model);
		// Retrieve Grid Setting
		retrieveGridSetting("servicesHistoryGrid",request,response,model);
		//Changes for back page from DeviceFinder
		if(request.getParameter(ChangeMgmtConstant.ASSET_ROWID)!=null && request.getParameter(ChangeMgmtConstant.ASSET_ROWID)!=""
			&& request.getParameter(ChangeMgmtConstant.ISDEVICE_BOOKMARKED)!=null && request.getParameter(ChangeMgmtConstant.ISDEVICE_BOOKMARKED)!=""){
			model.addAttribute(ChangeMgmtConstant.ASSET_ROWID,request.getParameter(ChangeMgmtConstant.ASSET_ROWID) );
			model.addAttribute(ChangeMgmtConstant.ISDEVICE_BOOKMARKED,request.getParameter(ChangeMgmtConstant.ISDEVICE_BOOKMARKED));
			model.addAttribute(ChangeMgmtConstant.MODELLINKCLICKED, request.getParameter(ChangeMgmtConstant.LINKCLICKED));
			request.removeAttribute(ChangeMgmtConstant.ASSET_ROWID);
			request.removeAttribute(ChangeMgmtConstant.ISDEVICE_BOOKMARKED);
			request.removeAttribute(ChangeMgmtConstant.LINKCLICKED);
		}
		//Ends
		return "serviceRequest/serviceRequestDrillDown";
	}
	
	@RequestMapping(params = "action=serviceRequestDrillDownLightBox")
	public String showServiceRequestDrillDownLightBox(RenderRequest request, RenderResponse response, Model model) throws Exception{
		logger.debug("-------------serviceRequestDrillDownLightBox started---------");
		sharedPortletController.showServiceRequestDrillDownPage(request, response, model);
		String gridReq=request.getParameter("g");
		logger.debug("include"+gridReq);
		if(StringUtils.isNotBlank(gridReq) && "true".equalsIgnoreCase(gridReq)){
			model.addAttribute("grid",true);
		}else{
			model.addAttribute("grid",false);
		}
		return "serviceRequest/serviceRequestDrillDownLightBox";
	}
	
	@RequestMapping(params = "action=serviceHistoryList")
	public String showRequestHistoryPrintPage(RenderRequest request, RenderResponse response, Model model) throws Exception{
		return "lgsHistoryDetails/serviceRequestsPrint";
	}
	
@ResourceMapping(value="displayAttachmentBreakFix")
	
	public void displayAttachment(ResourceRequest request, ResourceResponse response, Model model)throws Exception{
		logger.debug("-------------displayAttachmentBreakFix SR HISTORY CONTROLLER :::: ---------["+System.nanoTime()+"]");
		
		AttachmentContract contract = new AttachmentContract();
		logger.debug("------------- --attachmentName---------"+request.getParameter("attachmentName"));
		logger.debug("------------- --activityId---------"+request.getParameter("activityId"));
		logger.debug("------------- --displayAttachmentName---------"+request.getParameter("displayAttachmentName"));

		String displayAttachmentName=request.getParameter("displayAttachmentName");
		
		String attachmentName=request.getParameter("attachmentName");
		
		contract.setUserFileName(request.getParameter("attachmentName"));
		contract.setIdentifier(request.getParameter("activityId"));
		
		// CI6 Download Attachment
		contract.setRequestType("Service Request");
		logger.debug("Contract value we are sending actualFileName "+request.getParameter("attachmentName")+" and activity id is "+request.getParameter("activityId"));
		CrmSessionHandle crmSessionHandle = globalService.initCrmSessionHandle(PortalSessionUtil.getSiebelCrmSessionHandle(request));
		contract.setSessionHandle(crmSessionHandle);
		
		logger.debug(" Contract :: contract.getIdentifier() :: "+contract.getIdentifier());
		logger.debug(" Contract :: contract.getParentId() :: "+contract.getParentId());
		logger.debug(" Contract :: contract.getRequestType() :: "+contract.getRequestType());
		logger.debug(" Contract :: contract.getUserFileName() :: "+contract.getUserFileName());
		
		
		try {
			logger.debug("Calling the amind service for download attachment");
			
			AttachmentResult attachmentResult = attachmentService.downloadAttachment(contract);
			logger.debug("------------- attachmentResult "+attachmentResult);
			
			
			String fileName = attachmentResult.getFileName();
			InputStream fileStream = attachmentResult.getFileStream();

			logger.debug("service call done "+fileName);
			logger.debug("service call done "+fileStream.toString());

			
			// Reading the File Stream CI6
			
					if(fileStream!=null){
					response.setProperty("Content-disposition", "attachment; filename=" + displayAttachmentName);
					
					if(attachmentName.indexOf("jpg") > 0) {
						response.setContentType("image/jpg");
			        }else if(attachmentName.indexOf("gif") > 0) {
			        	response.setContentType("image/gif");
			        }else if(attachmentName.indexOf("pdf") > 0) {
			        	response.setContentType("application/pdf");
			        	
			        }else if(attachmentName.indexOf("html") > 0) {
			        	response.setContentType("text/html");
			        }else if(attachmentName.indexOf("zip") > 0) {
			        	response.setContentType("application/zip");
			        }else if(attachmentName.indexOf("txt") > 0 ){
			        	response.setContentType("text/plain");
			        }else if(attachmentName.indexOf("xls")>0){
			        	response.setContentType("application/vnd.ms-excel");
			        }else if(attachmentName.indexOf("pdf")>0){
			        	response.setContentType("application/pdf");
			        }else if(attachmentName.indexOf("doc")>0){
			        	response.setContentType("application/msword");
			        }else if(attachmentName.indexOf("xlsx")>0){
			        	response.setContentType("application/vnd.ms-excel");
			        }else if(attachmentName.indexOf("docx")>0){
			        	response.setContentType("application/msword");
			        }else if(attachmentName.indexOf("csv")>0){
			        	response.setContentType("application/vnd.ms-excel");
			        } 
					 InputStream inputStream = fileStream;
					
					  OutputStream out = response.getPortletOutputStream();
					  byte buf[]=new byte[1024];
					  int len;
					  while((len=inputStream.read(buf))>0)
					  {out.write(buf,0,len);}
					
					  out.close();
					  inputStream.close();
					  logger.debug("\nFile is created...................................");
				}
				else{
					response.setProperty("Content-disposition", "attachment; filename=Error_in_file_download.doc");
					response.setContentType("application/msword");
					OutputStream out = response.getPortletOutputStream();
					byte buf[]=("The file "+ attachmentName + " could not downloaded.").getBytes();
					out.write(buf);
					out.flush();
					out.close();
					logger.debug("\nFile is created...................................");
				}
				
			}catch (IOException e){
				logger.debug("RequestController exception : " + e.getMessage());
			}
			catch(Exception exception){
				logger.debug("RequestController  exception : " + exception.getMessage());
				response.getPortletOutputStream();
				
			}
	    finally {
	        	logger.debug("--------------- Reached finally block -----------------");
	    }
		logger.debug("----------------outPutDownloadFile ended---------["+System.nanoTime()+"]");
	}

	


// changes 13/11/2012
@RequestMapping(params = "action=showSRDrillDownEmailSendingPage")
public String showEmailNotificationSendingPage(RenderRequest request, RenderResponse response, Model model)
		throws Exception {
	logger.debug("email");
	return "serviceRequest/serviceRequestDrillDownEmail";
}
@RequestMapping(params = "action=showServiceRequestDrillDownPrintPage")
public String showServiceRequestDrillDownPrintPage(RenderRequest request, RenderResponse response, Model model) throws Exception{
	logger.debug("Print");
	return "serviceRequest/serviceRequestDrillDownPrint";
}
    //Done for defect - 3924
	@RequestMapping(params = "action=gotoControlPanel")
	public String gotoControlPanel(RenderRequest request, RenderResponse response,
		Model model) throws Exception {
		model.addAttribute("controlPanelURL",request.getParameter("controlPanelURL"));
		model.addAttribute(ChangeMgmtConstant.PAGENAME, "serviceRequestDrillDownLightBox");
		return "controlPanelPage";
}
	
	//Added for CI BRD13-10-02
	/**
	 * @param request 
	 * @param response 
	 * @param model 
	 */
	@ResourceMapping(value="retrieveHistoryList")
	public void retrieveHistoryList(ResourceRequest request, ResourceResponse response, Model model)
	{
		commonController.retrieveHistoryListByAssetId(request, response, model);
	}

}
