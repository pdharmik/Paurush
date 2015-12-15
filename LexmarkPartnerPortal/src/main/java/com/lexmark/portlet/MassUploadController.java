/**
 * Copyright@ 2013. This document has been created & written by
 * Wipro technologies for Lexmark and this is copyright to Lexmark
 *
 * File         	: MassUploadController.java
 * Package     		: com.lexmark.portlet
 * Creation Date 	: 
 *
 * Modification History:
 *
 * ---------------------------------------------------------------------
 * Author 			Date				Version  		Comments
 * ---------------------------------------------------------------------
 *
 */
package com.lexmark.portlet;



import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import javax.annotation.Resource;
import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.constants.LexmarkPPConstants;
import com.lexmark.constants.MassUploadFlags;
import com.lexmark.contract.AttachmentContract;
import com.lexmark.contract.MassUploadCreateRequestContract;
import com.lexmark.domain.Attachment;
import com.lexmark.domain.Field;
import com.lexmark.domain.FileObject;
import com.lexmark.domain.SiebelLocalization.SiebelLocalizationOptionEnum;
import com.lexmark.domain.TemplateInformation;
import com.lexmark.domain.Value;
import com.lexmark.enums.PartnerTypeEnum;
import com.lexmark.exporter.ExcelDataExporter;
import com.lexmark.exporter.ExcelExporter;
import com.lexmark.form.FileUploadForm;
import com.lexmark.framework.logging.LEXLogger;
import com.lexmark.portlet.common.AttachmentController;
import com.lexmark.portlet.source.OrderRequestController;
import com.lexmark.result.CreateServiceRequestResult;
import com.lexmark.result.MassUploadTemplateResult;
import com.lexmark.result.UserGridSettingResult;
import com.lexmark.service.api.AttachmentService;
import com.lexmark.service.api.CrmSessionHandle;
import com.lexmark.service.api.GlobalService;
import com.lexmark.service.api.ServiceRequestLocale;
import com.lexmark.service.impl.real.util.AttachmentProperties;
import com.lexmark.util.ControllerUtil;
import com.lexmark.util.ExcelToCSVConverter;
import com.lexmark.util.JsonUtil;
import com.lexmark.util.ObjectDebugUtil;
import com.lexmark.util.PerformanceConstant;
import com.lexmark.util.PerformanceUtil;
import com.lexmark.util.PortalSessionUtil;
import com.lexmark.util.ReadTemplateXml;
import com.lexmark.webservice.api.MassUploadService;
import com.liferay.portal.util.PortalUtil;






/**
 * @author wipro 
 * @version 2.1 
 */
@Controller
@RequestMapping("VIEW")
public class MassUploadController {
	
	private static LEXLogger LOGGER = LEXLogger.getLEXLogger(MassUploadController.class);
	private static Logger perfLogger = LogManager.getLogger("performance");
	private static String CLASS_NAME="MassUploadController.java";
	
	
	@Autowired
	private GlobalService globalService;
	
	@Autowired
	private GridSettingController gridSettingController;
	@Autowired
	private ServiceRequestLocale serviceRequestLocaleService;
	@Autowired
	private MassUploadService massUploadService;
	@Autowired
	private AttachmentService attachmentService;
	@Autowired
	private HardwareDebriefController hardwareDebriefController;
	@Autowired
	private OrderRequestController orderRequestController;
	@Autowired
	private AttachmentController attachmentController;
	@Autowired
	private ReadTemplateXml serviceOrderTemplate;
	@Autowired
	private ReadTemplateXml hardwareOrderTemplate;
	
	@Resource
	private Map<String, String> webServiceCallValuesSO ;
	@Resource
	private Map<String, String> webServiceCallValuesHW ;	
	/**
	 * @param renderRequest 
	 * @param model 
	 * @return jsppage 
	 * */
	@RequestMapping
	public String showMassUpload(RenderRequest renderRequest,Model model){
		
		HttpServletRequest httpReq = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(renderRequest));
		
		
		LOGGER.debug("httpReq.getParameter(LexmarkPPConstants.VENDORACCID_AVAIL)"+httpReq.getParameter(LexmarkPPConstants.VENDORACCID_AVAIL));
		if(httpReq.getParameter(LexmarkPPConstants.VENDORACCID_AVAIL)==null){
			LOGGER.debug("LexmarkPPConstants.ACCOUNT_SELECTION_DONE false");
			model.addAttribute(LexmarkPPConstants.ACCOUNT_SELECTION_DONE,"false");
		}else{
			model.addAttribute(LexmarkPPConstants.ACCOUNT_SELECTION_DONE,"true");
		}
		
		PortletSession session=renderRequest.getPortletSession();
		
		
		try{
			attachmentController.clearUploadedFileFromSession(renderRequest, LexmarkPPConstants.SESSION_FILE_MAP_ForMassUpload);
		}catch(Exception e){
			LOGGER.error("error occured while clearing the map"+e.getMessage());

		}
		
		Boolean isIndirectPartner = PortalSessionUtil.getIndirectPartnerFlag(session);
		Boolean isDirectPartner = PortalSessionUtil.getDirectPartnerFlag(session);
		String partnerType = getPartnerType(isIndirectPartner, isDirectPartner);
		Map<String, String> orderRequestStatus= ControllerUtil.retrieveLocalizedLOVMap(
				SiebelLocalizationOptionEnum.PARTNER_SERVICE_ORDER_STATUS.getValue(), partnerType,
				serviceRequestLocaleService, renderRequest.getLocale());
		
		
		
		
		
		/**
		 * These three maps are for hardware debrief
		 * */
		Map<String, String> hwdebriefRequestStatus = ControllerUtil.retrieveLocalizedLOVMap(
				SiebelLocalizationOptionEnum.PARTNER_SERVICE_REQUEST_STATUS.getValue(), partnerType,
				serviceRequestLocaleService, renderRequest.getLocale());
	
		
		Map<String, String> hwdebriefRequestStatusDetails = ControllerUtil.retrieveLocalizedLOVMap(
				SiebelLocalizationOptionEnum.ACTIVITY_SUB_STATUS.getValue(), partnerType,
				serviceRequestLocaleService, renderRequest.getLocale());
		
		Map<String, String> hwDebriefRequestType = ControllerUtil.retrieveLocalizedLOVMap(
				SiebelLocalizationOptionEnum.PARTNER_REQUEST_LIST.getValue(), null,
				serviceRequestLocaleService, renderRequest.getLocale());
		
		
		
		
		
		
		
		
		
		FileUploadForm fileUploadForm=new FileUploadForm();
		fileUploadForm.setSessionFileKey(LexmarkPPConstants.SESSION_FILE_MAP_ForMassUpload);
		
		
		model.addAttribute("fileUploadForm", fileUploadForm);
		model.addAttribute("orderRequestStatusMap", orderRequestStatus);
		
		model.addAttribute("hwDebriefRequestType", hwDebriefRequestType);
		model.addAttribute("hwdebriefRequestStatus", hwdebriefRequestStatus);
		model.addAttribute("hwdebriefRequestStatusDetails", hwdebriefRequestStatusDetails);
		
		if(httpReq.getParameter(LexmarkPPConstants.VENDORACCID_AVAIL)!=null){
			/*Below section is to get the grid setting according 
			 * to the flags which are set from account selection
			 * 
			 * */
			
			MassUploadFlags[] values=MassUploadFlags.values();
			Map<?,?> accountDetailsMap=(Map<?,?>)session.getAttribute(LexmarkPPConstants.PARTNER_ACCOUNT_SELECTION_DETAILS, PortletSession.APPLICATION_SCOPE);
			LOGGER.debug("map read from session ");
			for(MassUploadFlags flag:values){
				if(StringUtils.isNotBlank((String)accountDetailsMap.get(flag.getFlagName())) && LexmarkPPConstants.TRUE_ATTR.equalsIgnoreCase((String)accountDetailsMap.get(flag.getFlagName()))){
					if(LOGGER.isDebugEnabled()){
					LOGGER.debug(" flag set to true for flag "+flag.getFlagName()+" value ="+(String)accountDetailsMap.get(flag.getFlagName()));
					LOGGER.debug("grid id is ="+flag.getGridID());
					}
					gridSettingController.retrieveGridSetting(flag.getGridID(), renderRequest, model);
					model.addAttribute(LexmarkPPConstants.LINK_ID_, flag.getLinkID());
					break;
				}
			}
		}
			
			
		
		
		
		return "massUpload/massUploadDefaultView";
	}
	/**
	 * @param resourceRequest 
	 * @param gridId    
	 * @param resourceResponse  
	 * */	
	@ResourceMapping("getGridSettingsByAjax")
	public void getGridSetting(ResourceRequest resourceRequest,ResourceResponse resourceResponse,@RequestParam("gridId") String gridId){
		LOGGER.enter("MassUploadController.java", "getGridSetting");
		
		
		//Get Grid settings for particular Grid id
		UserGridSettingResult result =gridSettingController.retrieveGridSettingForMassUpload(gridId, resourceRequest);
		String json=JsonUtil.generateJSONForGridSetting(result,resourceRequest.getLocale());

		
		try {
			final PrintWriter out = resourceResponse.getWriter();
			resourceResponse.setProperty("Cache-Control", "max-age=0,no-cache,no-store");
			resourceResponse.setProperty("Expires", "max-age=0,no-cache,no-store");
			resourceResponse.setContentType("UTF-8");
			out.write(json.toString());
			out.flush();
			out.close();
		} catch (IOException e) {
			LOGGER.error("IOException while invoking response#getWriter(),"
					+ e.getMessage());
		}
		
		LOGGER.exit("MassUploadController.java", "getGridSetting");
		
		
		
	}
	/**
	 * @param resourceRequest  
	 * @param resourceResponse 
	 * @param gridId  
	 * */
	@ResourceMapping("downloadCSVFile")
	public void downloadTemplate(ResourceRequest resourceRequest,ResourceResponse resourceResponse,
			@RequestParam("gridId") String gridId){
		
		final String METHOD_NAME="downloadTemplate";
		
		
		List<?> dataList=null;
		
		ExcelDataExporter exporter = new ExcelDataExporter();
		exporter.setLocale(resourceRequest.getLocale());
		
		
		ExcelExporter exporterData=new ExcelExporter();
		exporterData.setLocale(resourceRequest.getLocale());
		if(gridId.equalsIgnoreCase(LexmarkPPConstants.SERVICEORDERGRIDID)){
			TemplateInformation templateInformation=serviceOrderTemplate.getTemplateInformation();
			
			
			dataList=orderRequestController.getMassUploadTemplate(resourceRequest,resourceResponse);
		
			
			exporterData.generateServiceOrderTemplate(dataList, templateInformation, resourceResponse);
			
		}else if(gridId.equalsIgnoreCase(LexmarkPPConstants.HARDWAREORDERGRIDID)){
			TemplateInformation templateInformation=hardwareOrderTemplate.getTemplateInformation();
			
			
			
			dataList=hardwareDebriefController.getMassUploadTemplate(resourceRequest);
			
			
			
			Map<Integer,String[]> dropDownValuesForExcel=new LinkedHashMap<Integer,String[]>();
			getDropDownValuesForHWExcel(resourceRequest,dropDownValuesForExcel,templateInformation);
			
			exporterData.generateHardwareDebriefTemplate(dataList,templateInformation,dropDownValuesForExcel,resourceResponse);
			
		}
		
		LOGGER.exit(CLASS_NAME, METHOD_NAME );
		
	}
	
	/**
	 * @param resourceRequest   
	 * @param resourceResponse  
	 * @param type 
	 * */	
	@ResourceMapping("submitServiceRequest")
	public void submitRequest(ResourceRequest resourceRequest,ResourceResponse resourceResponse,
			@RequestParam("type")String type){
		String METHOD_NAME = "submitRequest";
		LOGGER.enter(CLASS_NAME, METHOD_NAME);
		StringBuffer json=new StringBuffer("{");
		CrmSessionHandle crmSessionHandle=null;
		
		try{
			String vendorId=null;
			
				Map<String,String> accountSelectionDetails=(Map<String,String>)resourceRequest.getPortletSession().getAttribute(LexmarkPPConstants.PARTNER_ACCOUNT_SELECTION_DETAILS, PortletSession.APPLICATION_SCOPE);
				vendorId=accountSelectionDetails.get(LexmarkPPConstants.VENDORACC_ID);
			
			MassUploadCreateRequestContract contract=new MassUploadCreateRequestContract();
			Map<?,?> webserviceCallValues=null;
			if(StringUtils.isNotBlank(type)&& LexmarkPPConstants.MASSUPLOADHW.equalsIgnoreCase(type)){
				//This is for Hardware
				webserviceCallValues=webServiceCallValuesHW;
				/*
				 * This section is for converting the xls file
				 * for HARDWARE to csv.
				 * */
				convertHWXLSToCSV(resourceRequest);
			}else{
				//This is for service order
				webserviceCallValues=webServiceCallValuesSO;
			}
			contract.setArea((String)webserviceCallValues.get(LexmarkPPConstants.AREA));
			contract.setSubArea((String)webserviceCallValues.get(LexmarkPPConstants.SUBAREA));
			contract.setContactId(PortalSessionUtil.getContactId(resourceRequest.getPortletSession()));
			contract.setRequesterType((String)webserviceCallValues.get(LexmarkPPConstants.REQUESTERTYPE));
			contract.setServiceRequestType((String)webserviceCallValues.get(LexmarkPPConstants.SERVICEREQUESTTYPE));
			contract.setVendorId(vendorId);
			
			LOGGER.info("Start printing lexlogger before webservice call for Mass Upload SR Creation");
			ObjectDebugUtil.printObjectContent(contract, LOGGER);			
			
			long timeBeforeCall=System.currentTimeMillis();
			CreateServiceRequestResult massUploadRequestResult=massUploadService.createMassUploadRequest(contract);
			
			long timeAfterCall=System.currentTimeMillis();
			PerformanceUtil.calcTime(perfLogger, PerformanceConstant.PARTNER_WEBMETHOD_MASS_UPLOAD, timeBeforeCall,timeAfterCall, PerformanceConstant.SYSTEM_WEBMETHODS,contract);	
			LOGGER.info("Ends printing lexlogger after webservice call for Mass Upload SR Creation");
			AttachmentContract atchmntContract = new AttachmentContract();
			crmSessionHandle = globalService
			.initCrmSessionHandle(PortalSessionUtil
					.getSiebelCrmSessionHandle(resourceRequest));
			atchmntContract.setSessionHandle(crmSessionHandle);
			if (massUploadRequestResult != null) {
				atchmntContract.setIdentifier(massUploadRequestResult.getServiceRequestRowId());
			}
			atchmntContract.setRequestType(LexmarkPPConstants.ATTACHMENT_REQUEST_TYPE);// For
																				// service
																				// request
																				// you
																				// have
																				// to
																				// send
			
			PortletSession session=resourceRequest.getPortletSession();
			Map<String, FileObject> fileMap = (Map<String, FileObject>) session.getAttribute(LexmarkPPConstants.SESSION_FILE_MAP_ForMassUpload);
			List<Attachment> attachmentList = new ArrayList<Attachment>();
			if (fileMap != null) {

				
				Set<String> fileNames = fileMap.keySet();
				Attachment file=null;
				String userType = PortalSessionUtil.getUserType(session);
				LOGGER.debug("usertype is "+userType);
				for(String filename:fileNames){
					FileObject fileDetails = fileMap.get(filename);
					file=new Attachment();
					file.setVisibility(userType);
					file.setAttachmentName(fileDetails.getFileName());
					file.setSize(Integer.parseInt(fileDetails
							.getFileSizeInBytes()));
					LOGGER.debug("File name ::: "
							+ fileDetails.getFileName());
					
					attachmentList.add(file);
				}
			}
			
			atchmntContract.setAttachments(attachmentList);
			if(LOGGER.isInfoEnabled()){
				LOGGER.info("start printing lex logger");
				for(Attachment at:atchmntContract.getAttachments()){
					LOGGER.debug(" contract atach file name ="+at.getAttachmentName());
				}
				
				ObjectDebugUtil.printObjectContent(atchmntContract,LOGGER);
				LOGGER.info("end printing lex loggger");
			}
			
			LOGGER.debug("-------------------------- Calling attachment service ----------------------");
			long timeBeforeCallAttachment=System.currentTimeMillis();
			attachmentService.uploadAttachments(atchmntContract);
			long timeAfterCallAttachment=System.currentTimeMillis();
			if(LOGGER.isInfoEnabled()){
				LOGGER.info("start printing lex logger");
				
				PerformanceUtil.calcTime(perfLogger, PerformanceConstant.HISTORY_MSG_RETRIEVEREQUESTLIST, timeBeforeCallAttachment,timeAfterCallAttachment, PerformanceConstant.SYSTEM_AMIND,contract);			
				LOGGER.info("end printing lex loggger");
			}
			LOGGER.debug("-------------------------- After Calling attachment service ----------------------");
			
			JsonUtil.appendToJSON(LexmarkPPConstants.JSON_STATUS,LexmarkPPConstants.JSON_STATUS_AVAILABLE,json,false);
			JsonUtil.appendToJSON(LexmarkPPConstants.SERVICEREQUESTNUMBER,massUploadRequestResult.getServiceRequestNumber(),json,false);
						
		}catch(Exception e){
			JsonUtil.appendToJSON(LexmarkPPConstants.JSON_STATUS,LexmarkPPConstants.JSON_STATUS_FAILURE,json,false);
			LOGGER.error("Exception occured"+e.getMessage());
		}finally{
			json.deleteCharAt(json.length()-1);
			json.append("}");
			globalService.releaseSessionHandle(crmSessionHandle);
			
			
		}
		try{
			final PrintWriter out = resourceResponse.getWriter();
			resourceResponse.setProperty("Cache-Control", "max-age=0,no-cache,no-store");
			resourceResponse.setProperty("Expires", "max-age=0,no-cache,no-store");
			resourceResponse.setContentType("UTF-8");
			out.write(json.toString());
			LOGGER.debug("json uptil is " + json.toString());
			out.flush();
			out.close();
		}catch(IOException exception){
			LOGGER.error("Exception occured while getting the print writer"+exception.getMessage());
		}
		
		
		LOGGER.exit(CLASS_NAME, METHOD_NAME);
		
	}
	
	
	
	
	/**
	 * Retrieve User Roles based on Session to check whether the user has necessary access for updating processed parts section.
	 * 
	 * @param session 
	 * @return boolean 
	 */
	private boolean processedUpdateRole(PortletSession session){
		List<String> userRoleList = PortalSessionUtil.getUserRoles(session);		
		
		if(!userRoleList.isEmpty() && ((userRoleList.contains(LexmarkConstants.ROLE_SERVICE_MANAGER)) || (userRoleList.contains(LexmarkConstants.ROLE_SERVICE_TECHNICIAN))))
		{
			return true;
		}else {
			return false;
		}
	}
	
	
	
	
	/**
	 * @param isIndirectPartner  
	 * @param isDirectPartner  
	 * @return partnerType  
	 * */
	private String getPartnerType(boolean isIndirectPartner, boolean isDirectPartner) {
		String METHOD_NAME = "getPartnerType";
		LOGGER.enter(CLASS_NAME, METHOD_NAME);

		String partnerType = null;
		if (isDirectPartner && isIndirectPartner) {
			partnerType = PartnerTypeEnum.BOTH.getValue();
		} else if (isDirectPartner && !isIndirectPartner) {
			partnerType = PartnerTypeEnum.DIRECT.getValue();
		} else if (!isDirectPartner && isIndirectPartner) {
			partnerType = PartnerTypeEnum.INDIRECT.getValue();
		}
		LOGGER.exit(CLASS_NAME, METHOD_NAME);
		return partnerType;
	}
	
	

	/**
	 * @param request 
	 * @throws IOException 
	 */
	private void convertHWXLSToCSV(PortletRequest request) throws IOException{
	LOGGER.enter("MassUploadController.java", "convertHWXLSToCSV");
	AttachmentProperties fileProperties = new AttachmentProperties(
			LexmarkPPConstants.ATTACHMENT_REQUEST_TYPE);
	Map<String, FileObject> fileMap = (Map<String, FileObject>) request.getPortletSession().getAttribute(LexmarkPPConstants.SESSION_FILE_MAP_ForMassUpload);
	FileObject fileObject=null;
	for(String filename:fileMap.keySet()){
		fileObject=fileMap.get(filename);
	}
	final String CSV=".csv";
	
	if(fileObject.getFileName().endsWith(CSV)){
		/**
		 * This case will occur if there is any exception and 
		 * already the file is converted to csv.
		 * */
		return;
	}
			
			FileInputStream is=new FileInputStream(fileProperties.getFileUploadDestination()+fileObject.getFileName());
			
			
			StringBuffer orgFilename=new StringBuffer(fileObject.getFileName());
			String hWfileName=orgFilename.substring(0,orgFilename.lastIndexOf("."));
			
			fileObject.setFileName(hWfileName+CSV);
			String destinationPath=fileProperties.getFileUploadDestination() + hWfileName;
			try{
				ExcelToCSVConverter.convert(destinationPath, is,hardwareOrderTemplate.getTemplateInformation(),2);
			}catch(Exception e){
				LOGGER.debug("exception occured "+e.getCause());
			}
			
			
	LOGGER.exit("MassUploadController.java", "convertHWXLSToCSV");	
	}
	
	
	/**
	 * @param request 
	 * @param dropDownValuesForExcel 
	 * @param templateInformation 
	 */
	private void getDropDownValuesForHWExcel(PortletRequest request,
			Map<Integer,String[]> dropDownValuesForExcel,
			TemplateInformation templateInformation ){
		//This is for creating dropDowns in Excel 
		Boolean isIndirectPartner = PortalSessionUtil.getIndirectPartnerFlag(request.getPortletSession());
		Boolean isDirectPartner = PortalSessionUtil.getDirectPartnerFlag(request.getPortletSession());
		String partnerType = getPartnerType(isIndirectPartner, isDirectPartner);
				List<Field> fields=templateInformation.getAllFields();
				
		for(Field field:fields){
			//Array values mentioned directly in the xml
			if(field.getConstraint()!=null && field.getConstraint().getValue() !=null){
				LOGGER.debug("[Constraint is not null and request is [Array values mentioned directly in the xml] ]");
				List<String> values=new ArrayList<String>();
				for(Value value:field.getConstraint().getValue()){
					values.add(value.getValue());
				}
				
				String[] arrayString=new String[values.size()];
				int index=0;
				for(String value:values){
					LOGGER.debug("value read from XML: [contraint]="+value);
					arrayString[index++]=value;
				}
				dropDownValuesForExcel.put(field.getColumnNumber(), arrayString);
			}else if(field.getConstraint()!=null && StringUtils.isNotBlank(field.getConstraint().getSiebelLOV())){
				//This is Siebel LOV Value
				LOGGER.debug("[Constraint is not null and request is [This is Siebel LOV Value] ]");
				String siebelLOV=field.getConstraint().getSiebelLOV();
				LOGGER.debug("value read from XML: [constraint]="+siebelLOV);
				Map<String, String> siebelLOVMap = ControllerUtil.retrieveLocalizedLOVMap(
						siebelLOV, partnerType,
						serviceRequestLocaleService, request.getLocale());
				dropDownValuesForExcel.put(field.getColumnNumber(),getArrayFromMap(siebelLOVMap));
			}
		}
		
		
	
		
	}
	
	/**
	 * @param typeMAp 
	 * @return  String 
	 */
	private String[] getArrayFromMap(Map<String,String> typeMAp){
		String[] stringArray=new String[typeMAp.keySet().size()];
		
		int i=0;
		for(String key:typeMAp.keySet()){
			stringArray[i++]=new String(key);	
		}
		return stringArray;
	}
}
