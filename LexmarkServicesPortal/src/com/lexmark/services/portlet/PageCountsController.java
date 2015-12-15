/**
 * Copyright@ 2012. This document has been created & written by
 * Wipro technologies for Lexmark and this is copyright to Lexmark
 *
 * File         	: PageCountsController.java
 * Package     		: com.lexmark.services.portlet
 * Creation Date 	: 2013
 *
 * Modification History:
 *
 * ---------------------------------------------------------------------
 * Author 				Date				Version  		Comments
 * ---------------------------------------------------------------------
 * Wipro		2013 		2.1             Initial Version
 * 
 */

package com.lexmark.services.portlet;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.TimeZone;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.portlet.ResourceURL;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.contract.AssetContract;
import com.lexmark.contract.AssetMeterReadContract;
import com.lexmark.contract.GlobalLegalEntityContract;
import com.lexmark.contract.MeterReadAssetListContract;
import com.lexmark.contract.MeterReadStatusContract;
import com.lexmark.contract.PageCountsContract;
import com.lexmark.contract.ProductImageContract;
import com.lexmark.contract.SiebelAccountListContract;
import com.lexmark.contract.UpdateAssetMeterReadContract;
import com.lexmark.domain.Account;
import com.lexmark.domain.Asset;
import com.lexmark.domain.LexmarkTransaction;
import com.lexmark.domain.MeterReadStatus;
import com.lexmark.domain.PageCounts;
import com.lexmark.domain.ServicesUser;
import com.lexmark.domain.SiebelLocalization.SiebelLocalizationOptionEnum;
import com.lexmark.framework.exception.LGSDBException;
import com.lexmark.result.AssetMeterReadResult;
import com.lexmark.result.AssetResult;
import com.lexmark.result.GlobalLegalEntityResult;
import com.lexmark.result.MeterReadAssetListResult;
import com.lexmark.result.MeterReadStatusFileResult;
import com.lexmark.result.MeterReadStatusListResult;
import com.lexmark.result.PageCountsResult;
import com.lexmark.result.ProductImageResult;
import com.lexmark.result.SiebelAccountListResult;
import com.lexmark.result.UpdateAssetMeterReadResult;
import com.lexmark.service.api.CrmSessionHandle;
import com.lexmark.service.api.GlobalService;
import com.lexmark.service.api.MeterReadService;
import com.lexmark.service.api.OrderSuppliesAssetService;
import com.lexmark.service.api.ProductImageService;
import com.lexmark.service.api.ServiceRequestService;
import com.lexmark.services.LexmarkSPConstants;
import com.lexmark.services.form.PageCountsImportForm;
import com.lexmark.services.form.PageCountsListForm;
import com.lexmark.services.portlet.common.CommonController;
import com.lexmark.services.util.CSV2AssetConverter;
import com.lexmark.services.util.CSVError;
import com.lexmark.services.util.ChangeMgmtConstant;
import com.lexmark.services.util.ContractFactory;
import com.lexmark.services.util.ObjectDebugUtil;
import com.lexmark.services.util.PortalSessionUtil;
import com.lexmark.services.util.ResourceResponseUtil;
import com.lexmark.services.util.ServiceStatusErrorMessageUtil;
import com.lexmark.services.util.ServiceStatusUtil;
import com.lexmark.util.DateUtil;
import com.lexmark.util.DownloadFileLocalizationUtil;
import com.lexmark.util.DownloadFileUtil;
import com.lexmark.util.PerformanceTracker;
import com.lexmark.util.PropertiesMessageUtil;
import com.lexmark.util.StringUtil;
import com.lexmark.util.report.PdfPReportGenerator;

/**
 * This class is used for pageCounts module which extends baseController
 * @version 2.1 
 * @author wipro
 *
 */
@Controller
@RequestMapping("VIEW")
public class PageCountsController extends BaseController {
	
	/**. Instance variable of wrapper logger class **/
	private static Logger logger = LogManager.getLogger(PageCountsController.class);
	
	/** holds AmindContractedMeterReadService bean reference **/
	@Autowired
	private MeterReadService meterReadService;
	
	/** holds AmindContractedServiceRequestService bean reference **/
	@Autowired
	private ServiceRequestService serviceRequestService;
	
	/** holds the reference of SharedPortletController bean **/
	@Autowired
	private SharedPortletController sharedPortletController;
    
	/** This variable holds AmindGlobalService bean reference **/
	@Autowired
    private GlobalService  globalService;
    
	/**. Holds the reference of ProductImageServiceImpl bean **/
	@Autowired
    private ProductImageService  productImageService;
    //brmandal added for Page count changes - MPS phase 2.1
    /** holds AmindOrderSuppliesAssetService bean reference **/
	@Autowired
    private OrderSuppliesAssetService orderSuppliesAssetService;
    //brmandal ended for Page count changes - MPS phase 2.1
	//Added for CI7 BRD 14-06-04 Starts
	@Autowired
	private CommonController commonController;
	//Added for CI7 BRD 14-06-04 Ends
	/**
     * Static variable declaration
     */
    private static final String MESSAGE_PAGE_COUNTS_UPLOAD_CSV = "message.pageCounts.upLoadCSVFile";
    /**
     * Static variable declaration
     */
    
    private static final String EXCEPTION_PAGE_COUNTS_INVALID_DATA = "exception.pageCounts.invalidData";
    /**
     * Static variable declaration
     */
    private static final String EXCEPTION_PAGE_COUNTS_FILE_NOT_FOUND = "exception.pageCounts.fileNotFound";
    /**
     * Static variable declaration
     */
    private static final String EXCEPTION_PAGE_COUNTS_UPLOAD_FAILED = "exception.pageCounts.uploadFailed";
    /**
     * Static variable declaration
     */
    
    private static final String TEMPLATE_FILE_PREFIX = "LexmarkPCUploadTemplate";
    /**
     * Static variable declaration
     */
    private static final String PRIEW_BUNDLE_NAME_COLUMN = "pageCntUploadLegend.column.";
    /**
     * Static variable declaration
     */
    private static final String PRIEW_BUNDLE_NAME_EXAMPLE = "pageCntUploadLegend.example.";
    /**
     * variable declaration
     */
    private String currentDate = "";
    
    //brmandal added for Page count changes - MPS phase 2.1
    /**
     * Static variable declaration
     */
    private static final String METH_GETPAGECOUNTPOPUP = "getPageCountPopUp";
    /**
     * Static variable declaration
     */
    private static final String METH_UPDATEPAGECOUNTS ="updatePageCounts";
    /**
     * Static variable declaration
     */
    public static final String NAME_COLOR = "Color";
    /**
     * Static variable declaration
     */
	public static final String NAME_LTPC = "LTPC";
	/**
     * Static variable declaration
     */
	public static final String NAME_MONO = "Mono";
	/**
     * Static variable declaration
     */
	public static final String NAME_A3COLOR = "A3Color";
	/**
     * Static variable declaration
     */
	public static final String NAME_A3LTPC = "A3LTPC";
	/**
     * Static variable declaration
     */
	public static final String NAME_A4COLOR = "A4Color";
	/**
     * Static variable declaration
     */
	public static final String NAME_A4LTPC = "A4LTPC";
	/**
     * Static variable declaration
     */
	public static final String NAME_SCANS = "Scans";
	/**
     * Static variable declaration
     */
	public static final String NAME_FAX = "Fax";
	/**
     * Static variable declaration
     */
	public static final String NAME_BLACK = "Black";
	/**
     * Static variable declaration
     */
	public static final String NAME_CYAN = "Cyan";
	/**
     * Static variable declaration
     */
	public static final String NAME_SOFTWARE = "Software";
	/**
     * Static variable declaration
     */
	public static final String PGS_SCAN_COPY = "PGS_SCAN_COPY";
	/**
     * Static variable declaration
     */
	public static final String PGS_SCAN_FAX = "PGS_SCAN_FAX";
	/**
     * Static variable declaration
     */
	public static final String PGS_SCAN_NETWORK = "PGS_SCAN_NETWORK";
	/**
     * Static variable declaration
     */
	public static final String PGS_SCAN_USB = "PGS_SCAN_USB";
	/**
     * Static variable declaration
     */
	public static final String TOTAL_SCANS = "TotalScans";
	/**
     * Static variable declaration
     */
	public static final String LETTER_COLOR = "LetterColor";
	/**
     * Static variable declaration
     */
	public static final String LETTER_LTPC = "LetterLTPC";
	/**
     * Static variable declaration
     */
	public static final String NAME_A5COLOR = "A5Color";
	/**
     * Static variable declaration
     */
	public static final String NAME_A5LTPC = "A5LTPC";
	/**
     * Static variable declaration
     */
	public static final String LEGAL_COLOR = "LegalColor";
	/**
     * Static variable declaration
     */
	public static final String LEGAL_LTPC = "LegalLTPC";
	/**
     * Static variable declaration
     */
	public static final String STATEMENT_COLOR = "StatementColor";
	/**
     * Static variable declaration
     */
	public static final String STATEMENT_LTPC = "StatementLTPC";
	/**
     * Static variable declaration
     */
	public static final String TABLOID_COLOR = "TabloidColor";
	/**
     * Static variable declaration
     */
	public static final String TABLOID_LTPC = "TabloidLTPC";
	/**
	 * default method for Page Count portlet
	 * @param model 
	 * @param request 
	 * @param response 
	 * @return String
	 * @throws Exception 
	 */
	
    @RequestMapping
	public String pageCountListPage(Model model, RenderRequest request, RenderResponse response) throws Exception{
    	boolean manualMeterReadFlag = isManualMeterReadFlag(request);
    	ResourceURL resURL = response.createResourceURL();
		resURL.setResourceID("pageCountsLandingPageURL");
		model.addAttribute("pageCountsLandingPageURL", resURL.toString());
		
		ResourceURL resOutPutURL = response.createResourceURL();
		resOutPutURL.setResourceID("outPutFileURL");
		model.addAttribute("outPutFileURL", resOutPutURL.toString());

		model.addAttribute("manualMeterReadFlag", true);
		
		ResourceURL exportPageCountURL = response.createResourceURL();
		exportPageCountURL.setResourceID("exportPageCount");
		model.addAttribute("exportPageCountUrl", exportPageCountURL.toString());
		
		return "pageCounts/pageCountsLandingPage";
	}
    
    /**
     * this method is used to display the make update online page of page count portlet
     * @param model 
     * @param request 
     * @param response 
     * @return String
     * @throws Exception 
     */
	@RequestMapping(params = "action=pageCountsList")
	public String pageCountList(Model model, RenderRequest request, RenderResponse response) throws Exception{
		ResourceURL resURL = response.createResourceURL();
		resURL.setResourceID("downloadMeterReadURL");
		PageCountsListForm pageCountsListForm = new PageCountsListForm();
		//Add Device Location tree
		pageCountsListForm.setDeviceLocationTreeXMLURL(sharedPortletController.getDeviceLocationXMLURL(response));
		//Add CHL node tree
		pageCountsListForm.setChlTreeXMLURL(sharedPortletController.getCHLTreeXMLURL(response));
		//Add download url
		pageCountsListForm.setDownloadMeterReadURL(resURL.toString());
		
		retrieveGridSetting("divMeterReadContainer",request,response,model);
		
		model.addAttribute("pageCountsListForm", pageCountsListForm);

		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		model.addAttribute("serverTodayStr", dateFormat.format(new Date()));

		return "pageCounts/pageCountsList";
	}
    
	/**
	 * this method is used to retrieve page count status list
	 * @param request 
	 * @param response 
	 * @throws Exception 
	 */
	@ResourceMapping("pageCountsLandingPageURL")
	public void retrieveMeterReadStatusList(ResourceRequest request, ResourceResponse response) throws Exception{
		
		PortletSession session = request.getPortletSession();
		String topLevelMdmId = null;
		//Added for Page Count Status Starts CI7 BRD 14-06-04
		Map<String, String> pageCountsStatusMap=null;
		try{
			pageCountsStatusMap=commonController.retrieveLocalizedLOVMap(SiebelLocalizationOptionEnum.PAGE_COUNTS_STATUS.getValue(), request.getLocale());
			logger.debug("pageCountsStatusMap = "+ pageCountsStatusMap);
		}
		catch(LGSDBException e){
			logger.debug("catch"+ e.getMessage());
			
		}
		//Added for Page Count Status Ends CI7 BRD 14-06-04
		
		if (PortalSessionUtil.getMdmLevel(session).equalsIgnoreCase(LexmarkConstants.GLOBAL)){
			//Already at the top level
			topLevelMdmId = PortalSessionUtil.getMdmId(session);
		}else{
			//Need to get the top level for this person's MDM ID and Level
			GlobalLegalEntityContract globalLegalEntityContract = new GlobalLegalEntityContract();
			logger.debug("MDM ID"+PortalSessionUtil.getMdmId(session) + "MDM LEVEL"+PortalSessionUtil.getMdmLevel(session));
			
			globalLegalEntityContract.setMdmId(PortalSessionUtil.getMdmId(session));
			globalLegalEntityContract.setMdmLevel(PortalSessionUtil.getMdmLevel(session));
			
			LexmarkTransaction lexmarkTran = PerformanceTracker.startTracking(LexmarkConstants.TARGET_SYSTEM_SIEBEL, 
					"retrieveGlobalLegalEntity",PortalSessionUtil.getMdmId(session), 
					PortalSessionUtil.getServiceUser(session).getEmailAddress());
			GlobalLegalEntityResult globalLegalEntityResult = globalService.retrieveGlobalLegalEntity(globalLegalEntityContract);
			PerformanceTracker.endTracking(lexmarkTran);
			
			topLevelMdmId = globalLegalEntityResult.getAccount().getMdmId();
			
		}
		
		MeterReadStatusContract contract = ContractFactory.getMeterReadStatusContract();
		contract.setMdmId(topLevelMdmId);
		
		CrmSessionHandle crmSessionHandle = globalService.initCrmSessionHandle(PortalSessionUtil.getSiebelCrmSessionHandle(request));
		try {
		contract.setSessionHandle(crmSessionHandle);
		
		session.setAttribute("pageCountsLandingPageContract", contract);
		LexmarkTransaction lexmarkTran = PerformanceTracker.startTracking(LexmarkConstants.TARGET_SYSTEM_SIEBEL, 
				"retrieveMeterReadStatusList", PortalSessionUtil.getMdmId(session), 
				PortalSessionUtil.getServiceUser(session).getEmailAddress());
		logger.debug("The Alliance Partner flag is---->>>"+(Boolean)session.getAttribute(ChangeMgmtConstant.ALLIANCE_PARTNER, PortletSession.APPLICATION_SCOPE));
		contract.setAlliancePartner((Boolean)session.getAttribute(ChangeMgmtConstant.ALLIANCE_PARTNER, PortletSession.APPLICATION_SCOPE));
		ObjectDebugUtil.printObjectContent(contract, logger);
		MeterReadStatusListResult result = meterReadService.retrieveMeterReadStatusList(contract);
		PerformanceTracker.endTracking(lexmarkTran);
		List<MeterReadStatus>  meterReadStatusList = result.getMeterReadStatusList();
		//Changed for CI7 BRD 14-06-04
		String content = getXmlOutputGenerator(request.getLocale()).convertMeterReadStatusToXML(meterReadStatusList,pageCountsStatusMap);
		PrintWriter out = response.getWriter();
		response.setContentType("text/xml");
		out.print(content);
		out.flush();
		out.close();
		} finally {
			globalService.releaseSessionHandle(crmSessionHandle);
		}

		
	}
    
    //brmandal added for Page count changes - MPS phase 2.1
    /**
     * this method is used for updatePageCount popUp
     * @param request 
     * @param response 
     * @param model 
     * @throws Exception 
     */
	@ResourceMapping (value = "getPageCountPopUp")
	public void getPageCountPopUp(ResourceRequest request, ResourceResponse response, Model model) throws Exception
	{
		
		String METHOD_NAME = "getPageCountPopUp";
		logger.debug(this.getClass().getSimpleName() + METHOD_NAME);
		
		StringBuffer responseBody=new StringBuffer();
		String content = new String("");
		try{
		
			PageCountsContract contract = ContractFactory.getPageCountsContract(request);
		
	
		
		ObjectDebugUtil.printObjectContent(contract,logger);
		
		
		String contextPath = request.getContextPath();
		
		
		PortletSession session = request.getPortletSession();
		
		LexmarkTransaction lexmarkTran = PerformanceTracker.startTracking(LexmarkConstants.TARGET_SYSTEM_SIEBEL, 
				"retrievePageCounts", PortalSessionUtil.getMdmId(session), 
				PortalSessionUtil.getServiceUser(session).getEmailAddress());
		
		
		
		PerformanceTracker.endTracking(lexmarkTran);
		
		
		
		
		PageCountsResult deviceResult = orderSuppliesAssetService.retrievePageCounts(contract);
		
		
		PerformanceTracker.endTracking(lexmarkTran);
		
		
		List<PageCounts> pagecountsList = deviceResult.getPageCounts()==null?new ArrayList<PageCounts>():
			deviceResult.getPageCounts();
		
		
		
		
		content = getXmlOutputGenerator(
					request.getLocale()).convertPageCountToXML(pagecountsList, contextPath);
		
		model.addAttribute("pagecountsList",pagecountsList); 
		
		
		
		}
		catch (Exception ex) {
			logStackTrace(ex);
			responseBody.append("\""+ChangeMgmtConstant.ERROR+":\""+
			PropertiesMessageUtil.getPropertyMessage(LexmarkSPConstants.MESSAGE_BUNDLE_NAME,
					"meterRead.label.meterReadError", request.getLocale())+"\"");			
			request.setAttribute("errorAttribute", "true");
		}
		finally{
			PrintWriter out = response.getWriter();
			response.setContentType(ChangeMgmtConstant.CONTENTTYPEXML);
			
			out.print(content);
			
			out.flush();
			out.close();
			
		}
		
	}
    
    /**
	 * This method invokes the aMind service to update the page counts/meter reads for MPS
	 * @param model 
	 * @param request 
	 * @param response 
	 * @throws Exception 
	 */
	@ResourceMapping(value = "updatePageCounts")
	public void updatePageCounts(Model model, final ResourceRequest request, ResourceResponse response)
								throws Exception {
	logger.debug("Pagecountscontroller "+ METH_UPDATEPAGECOUNTS);
	/*logger.debug("ArrdataForLTPC "+request.getParameter("ArrdataForLTPC") + 
			"ArrdataForColor "+request.getParameter("ArrdataForColor"));*/
	
	
	JSONArray arrdataForLTPC = new JSONArray();
	JSONArray arrdataForColor = new JSONArray();
	JSONArray arrdataForA3Color = new JSONArray();
	JSONArray arrdataForA3LTPC = new JSONArray();
	JSONArray arrdataForA4Color = new JSONArray();
	JSONArray arrdataForA4LTPC = new JSONArray();
	JSONArray arrdataForScan = new JSONArray();
	JSONArray arrdataForFax = new JSONArray();
	JSONArray arrdataForBlack = new JSONArray();
	JSONArray arrdataForCyan = new JSONArray();
	JSONArray arrdataForSoftware = new JSONArray();
	JSONArray arrdataForPgsScanCopy = new JSONArray();
	JSONArray arrdataForPgsScanFax = new JSONArray();
	JSONArray arrdataForPgsScanNetwork = new JSONArray();
	JSONArray arrdataForPgsScanUsb = new JSONArray();
	JSONArray arrdataForTotalScans = new JSONArray();
	JSONArray arrdataForLetterColor = new JSONArray();
	JSONArray arrdataForLetterLTPC = new JSONArray();
	JSONArray arrdataForA5Color = new JSONArray();
	JSONArray arrdataForA5LTPC = new JSONArray();
	JSONArray arrdataForLegalColor = new JSONArray();
	JSONArray arrdataForLegalLTPC = new JSONArray();
	JSONArray arrdataForStatementColor = new JSONArray();
	JSONArray arrdataForStatementLTPC = new JSONArray();
	JSONArray arrdataForTabloidColor = new JSONArray();
	JSONArray arrdataForTabloidLTPC = new JSONArray();
	
	if(!(request.getParameter("ArrdataForLTPC").equalsIgnoreCase(""))){
		arrdataForLTPC = new JSONArray(request.getParameter("ArrdataForLTPC"));
	}
	if(!(request.getParameter("ArrdataForColor").equalsIgnoreCase(""))){
		
		arrdataForColor = new JSONArray(request.getParameter("ArrdataForColor"));
	}
	if(!(request.getParameter("ArrdataForA3Color").equalsIgnoreCase(""))){
			arrdataForA3Color = new JSONArray(request.getParameter("ArrdataForA3Color"));
	}
	if(!(request.getParameter("ArrdataForA3LTPC").equalsIgnoreCase(""))){
		arrdataForA3LTPC = new JSONArray(request.getParameter("ArrdataForA3LTPC"));
	}
	if(!(request.getParameter("ArrdataForA4Color").equalsIgnoreCase(""))){
		arrdataForA4Color = new JSONArray(request.getParameter("ArrdataForA4Color"));
	}
	if(!(request.getParameter("ArrdataForA4LTPC").equalsIgnoreCase(""))){
		arrdataForA4LTPC = new JSONArray(request.getParameter("ArrdataForA4LTPC"));
	}
	if(!(request.getParameter("ArrdataForScan").equalsIgnoreCase(""))){
		arrdataForScan = new JSONArray(request.getParameter("ArrdataForScan"));
	}
	if(!(request.getParameter("ArrdataForFax").equalsIgnoreCase(""))){
		arrdataForFax = new JSONArray(request.getParameter("ArrdataForFax"));
	}
	if(!(request.getParameter("ArrdataForBlack").equalsIgnoreCase(""))){
		arrdataForBlack = new JSONArray(request.getParameter("ArrdataForBlack"));
	}
	if(!(request.getParameter("ArrdataForCyan").equalsIgnoreCase(""))){
		arrdataForCyan = new JSONArray(request.getParameter("ArrdataForCyan"));
	}
	if(!(request.getParameter("ArrdataForSoftware").equalsIgnoreCase(""))){
		arrdataForSoftware = new JSONArray(request.getParameter("ArrdataForSoftware"));
	}
	if(!(request.getParameter("ArrdataForPgsScanCopy").equalsIgnoreCase(""))){
		arrdataForPgsScanCopy = new JSONArray(request.getParameter("ArrdataForPgsScanCopy"));
	}
	if(!(request.getParameter("ArrdataForPgsScanFax").equalsIgnoreCase(""))){
		arrdataForPgsScanFax = new JSONArray(request.getParameter("ArrdataForPgsScanFax"));
	}
	if(!(request.getParameter("ArrdataForPgsScanNetwork").equalsIgnoreCase(""))){
		arrdataForPgsScanNetwork = new JSONArray(request.getParameter("ArrdataForPgsScanNetwork"));
	}
	if(!(request.getParameter("ArrdataForPgsScanUsb").equalsIgnoreCase(""))){
		arrdataForPgsScanUsb = new JSONArray(request.getParameter("ArrdataForPgsScanUsb"));
	}
	if(!(request.getParameter("ArrdataForTotalScans").equalsIgnoreCase(""))){
		arrdataForTotalScans = new JSONArray(request.getParameter("ArrdataForTotalScans"));
	}
	if(!(request.getParameter("ArrdataForLetterColor").equalsIgnoreCase(""))){
		arrdataForLetterColor = new JSONArray(request.getParameter("ArrdataForLetterColor"));
	}
	if(!(request.getParameter("ArrdataForLetterLTPC").equalsIgnoreCase(""))){
		arrdataForLetterLTPC = new JSONArray(request.getParameter("ArrdataForLetterLTPC"));
	}
	if(!(request.getParameter("ArrdataForA5Color").equalsIgnoreCase(""))){
		arrdataForA5Color = new JSONArray(request.getParameter("ArrdataForA5Color"));
	}
	if(!(request.getParameter("ArrdataForA5LTPC").equalsIgnoreCase(""))){
		arrdataForA5LTPC = new JSONArray(request.getParameter("ArrdataForA5LTPC"));
	}
	if(!(request.getParameter("ArrdataForLegalColor").equalsIgnoreCase(""))){
		arrdataForLegalColor = new JSONArray(request.getParameter("ArrdataForLegalColor"));
	}
	if(!(request.getParameter("ArrdataForLegalLTPC").equalsIgnoreCase(""))){
		arrdataForLegalLTPC = new JSONArray(request.getParameter("ArrdataForLegalLTPC"));
	}
	if(!(request.getParameter("ArrdataForStatementColor").equalsIgnoreCase(""))){
		arrdataForStatementColor = new JSONArray(request.getParameter("ArrdataForStatementColor"));
	}
	if(!(request.getParameter("ArrdataForStatementLTPC").equalsIgnoreCase(""))){
		arrdataForStatementLTPC = new JSONArray(request.getParameter("ArrdataForStatementLTPC"));
	}
	if(!(request.getParameter("ArrdataForTabloidColor").equalsIgnoreCase(""))){
		arrdataForTabloidColor = new JSONArray(request.getParameter("ArrdataForTabloidColor"));
	}
	if(!(request.getParameter("ArrdataForTabloidLTPC").equalsIgnoreCase(""))){
		arrdataForTabloidLTPC = new JSONArray(request.getParameter("ArrdataForTabloidLTPC"));
	}
	
	String dateLTPC="";
	String countLTPC="";
	String dateColor="";
	String countColor="";
	String dateA3Color="";
	String countA3Color="";
	String dateA3LTPC="";
	String countA3LTPC="";
	String dateA4Color="";
	String countA4Color="";
	String dateA4LTPC="";
	String countA4LTPC="";
	String dateScan="";
	String countScan="";
	String dateFax="";
	String countFax="";
	String dateBlack="";
	String countBlack="";
	String dateCyan="";
	String countCyan="";
	String dateSoftware="";
	String countSoftware="";
	String datePgsScanCopy="";
	String countPgsScanCopy="";
	String datePgsScanFax="";
	String countPgsScanFax="";
	String datePgsScanNetwork="";
	String countPgsScanNetwork="";
	String datePgsScanUsb="";
	String countPgsScanUsb="";
	String dateTotalScans="";
	String countTotalScans="";
	String dateLetterColor="";
	String countLetterColor="";
	String dateLetterLTPC="";
	String countLetterLTPC="";
	String dateA5Color="";
	String countA5Color="";
	String dateA5LTPC="";
	String countA5LTPC="";
	String dateLegalColor="";
	String countLegalColor="";
	String dateLegalLTPC="";
	String countLegalLTPC="";
	String dateStatementColor="";
	String countStatementColor="";
	String dateStatementLTPC="";
	String countStatementLTPC="";
	String dateTabloidColor="";
	String countTabloidColor="";
	String dateTabloidLTPC="";
	String countTabloidLTPC="";
	
	//added start for pageCount mono update in siebel(LTPC-Color) 15.3 *
	String monoCount="";
	//added end for pageCount mono update in siebel(LTPC-Color) 15.3 *
	
	if(arrdataForLTPC.length() > 0){
		dateLTPC = arrdataForLTPC.getString(0);
		countLTPC = arrdataForLTPC.getString(1);
	}
	
	if(arrdataForColor.length() > 0){
		dateColor = arrdataForColor.getString(0);
		countColor = arrdataForColor.getString(1);
	}
	
	if(arrdataForA3Color.length() > 0){
		dateA3Color = arrdataForA3Color.getString(0);
		countA3Color = arrdataForA3Color.getString(1);
	}
	
	if(arrdataForA3LTPC.length() > 0){
		dateA3LTPC = arrdataForA3LTPC.getString(0);
		countA3LTPC = arrdataForA3LTPC.getString(1);
	}
	if(arrdataForA4Color.length() > 0){
		dateA4Color = arrdataForA4Color.getString(0);
		countA4Color = arrdataForA4Color.getString(1);
	}
	
	if(arrdataForA4LTPC.length() > 0){
		dateA4LTPC = arrdataForA4LTPC.getString(0);
		countA4LTPC = arrdataForA4LTPC.getString(1);
	}
	
	if(arrdataForScan.length() > 0){
		dateScan = arrdataForScan.getString(0);
		countScan = arrdataForScan.getString(1);
	}
	
	if(arrdataForFax.length() > 0){
		dateFax = arrdataForFax.getString(0);
		countFax = arrdataForFax.getString(1);
	}
	if(arrdataForBlack.length() > 0){
		dateBlack = arrdataForBlack.getString(0);
		countBlack = arrdataForBlack.getString(1);
	}
	if(arrdataForCyan.length() > 0){
		dateCyan = arrdataForCyan.getString(0);
		countCyan = arrdataForCyan.getString(1);
	}
	if(arrdataForSoftware.length() > 0){
		dateSoftware = arrdataForSoftware.getString(0);
		countSoftware = arrdataForSoftware.getString(1);
	}
	if(arrdataForPgsScanCopy.length() > 0){
		datePgsScanCopy = arrdataForPgsScanCopy.getString(0);
		countPgsScanCopy = arrdataForPgsScanCopy.getString(1);
	}
	if(arrdataForPgsScanFax.length() > 0){
		datePgsScanFax = arrdataForPgsScanFax.getString(0);
		countPgsScanFax = arrdataForPgsScanFax.getString(1);
	}
	if(arrdataForPgsScanNetwork.length() > 0){
		datePgsScanNetwork = arrdataForPgsScanNetwork.getString(0);
		countPgsScanNetwork = arrdataForPgsScanNetwork.getString(1);
	}
	if(arrdataForPgsScanUsb.length() > 0){
		datePgsScanUsb = arrdataForPgsScanUsb.getString(0);
		countPgsScanUsb = arrdataForPgsScanUsb.getString(1);
	}
	if(arrdataForTotalScans.length() > 0){
		dateTotalScans = arrdataForTotalScans.getString(0);
		countTotalScans = arrdataForTotalScans.getString(1);
	}
	if(arrdataForLetterColor.length() > 0){
		dateLetterColor = arrdataForLetterColor.getString(0);
		countLetterColor = arrdataForLetterColor.getString(1);
	}
	if(arrdataForLetterLTPC.length() > 0){
		dateLetterLTPC = arrdataForLetterLTPC.getString(0);
		countLetterLTPC = arrdataForLetterLTPC.getString(1);
	}
	if(arrdataForA5Color.length() > 0){
		dateA5Color = arrdataForA5Color.getString(0);
		countA5Color = arrdataForA5Color.getString(1);
	}
	if(arrdataForA5LTPC.length() > 0){
		dateA5LTPC = arrdataForA5LTPC.getString(0);
		countA5LTPC = arrdataForA5LTPC.getString(1);
	}
	if(arrdataForLegalColor.length() > 0){
		dateLegalColor = arrdataForLegalColor.getString(0);
		countLegalColor = arrdataForLegalColor.getString(1);
	}
	if(arrdataForLegalLTPC.length() > 0){
		dateLegalLTPC = arrdataForLegalLTPC.getString(0);
		countLegalLTPC = arrdataForLegalLTPC.getString(1);
	}
	if(arrdataForStatementColor.length() > 0){
		dateStatementColor = arrdataForStatementColor.getString(0);
		countStatementColor = arrdataForStatementColor.getString(1);
	}
	if(arrdataForStatementLTPC.length() > 0){
		dateStatementLTPC = arrdataForStatementLTPC.getString(0);
		countStatementLTPC = arrdataForStatementLTPC.getString(1);
	}
	if(arrdataForTabloidColor.length() > 0){
		dateTabloidColor = arrdataForTabloidColor.getString(0);
		countTabloidColor = arrdataForTabloidColor.getString(1);
	}
	if(arrdataForTabloidLTPC.length() > 0){
		dateTabloidLTPC = arrdataForTabloidLTPC.getString(0);
		countTabloidLTPC = arrdataForTabloidLTPC.getString(1);
	}
	
	
	
	
	final String selectedAssetId=request.getParameter(ChangeMgmtConstant.SELECTEDASSETID);
	final String color=request.getParameter("Color");
	final String LTPC=request.getParameter("LTPC");
	logger.debug("Old Color is "+color);
	logger.debug("Old LTPC is "+LTPC);
	
	PortletSession session = request.getPortletSession();
	List<PageCounts> pagecountsList = new ArrayList<PageCounts>();
	SimpleDateFormat converter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
	String defaultTimeZoneID = converter.getTimeZone().getID();
	
	if((countLTPC != null) && (!(countLTPC.equalsIgnoreCase(""))) && (countLTPC.length() > 0))
	{
		
		PageCounts pageCountsLTPC = new PageCounts();
		pageCountsLTPC.setName(NAME_LTPC);
		pageCountsLTPC.setCount(countLTPC);
		logger.debug("ltpcDt toString() "+dateLTPC);
		if(dateLTPC == null || dateLTPC.equalsIgnoreCase("") || dateLTPC.length()==0){
			String dateGMT = DateUtil.getCurrentDateInGMT();
			logger.debug("dateGMT "+dateGMT + "date in str "+DateUtil.getDateInStringFormatWithTimestamp(dateGMT));
			
			pageCountsLTPC.setDate(DateUtil.getDateInStringFormatWithTimestamp(dateGMT));
		}
		else
		{
			
			pageCountsLTPC.setDate(dateLTPC);
			
		}
			
		pagecountsList.add(pageCountsLTPC);
		
	}
	
	if((countColor != null) && (!(countColor.equalsIgnoreCase(""))) && (countColor.length() > 0))
	{
		TimeZone.setDefault(TimeZone.getTimeZone(defaultTimeZoneID));
		
		PageCounts pageCountsColor = new PageCounts();
		pageCountsColor.setName(NAME_COLOR);
		pageCountsColor.setCount(countColor);
		if(dateColor == null || dateColor.equalsIgnoreCase("") || dateColor.length()==0){
			String dateGMT = DateUtil.getCurrentDateInGMT();
			logger.debug("dateGMT "+dateGMT + "date in str "+DateUtil.getDateInStringFormatWithTimestamp(dateGMT));
			
			pageCountsColor.setDate(DateUtil.getDateInStringFormatWithTimestamp(dateGMT));
		}
		else
		{
			
			pageCountsColor.setDate(dateColor);
			
		}
			
		pagecountsList.add(pageCountsColor);
	}
	
	if((countA3Color != null) && (!(countA3Color.equalsIgnoreCase(""))) && (countA3Color.length() > 0))
	{
		TimeZone.setDefault(TimeZone.getTimeZone(defaultTimeZoneID));
		
		PageCounts pageCountsA3Color = new PageCounts();
		pageCountsA3Color.setName(NAME_A3COLOR);
		pageCountsA3Color.setCount(countA3Color);
		if(dateA3Color == null || dateA3Color.equalsIgnoreCase("") || dateA3Color.length()==0){
			String dateGMT = DateUtil.getCurrentDateInGMT();
			
			pageCountsA3Color.setDate(DateUtil.getDateInStringFormatWithTimestamp(dateGMT));
		}
		else
		{
			
			pageCountsA3Color.setDate(dateA3Color);
			
		
		}
			
		pagecountsList.add(pageCountsA3Color);
	}
	
	if((countA3LTPC != null) && (!(countA3LTPC.equalsIgnoreCase(""))) && (countA3LTPC.length() > 0))
	{
		TimeZone.setDefault(TimeZone.getTimeZone(defaultTimeZoneID));
		
		PageCounts pageCountsA3LTPC = new PageCounts();
		pageCountsA3LTPC.setName(NAME_A3LTPC);
		pageCountsA3LTPC.setCount(countA3LTPC);
		if(dateA3LTPC == null || dateA3LTPC.equalsIgnoreCase("") || dateA3LTPC.length()==0){
			String dateGMT = DateUtil.getCurrentDateInGMT();
			
			pageCountsA3LTPC.setDate(DateUtil.getDateInStringFormatWithTimestamp(dateGMT));
		}
		else
		{
			
			pageCountsA3LTPC.setDate(dateA3LTPC);
			
		
		}
			
		pagecountsList.add(pageCountsA3LTPC);
	}
	
	
	if((countA4Color != null) && (!(countA4Color.equalsIgnoreCase(""))) && (countA4Color.length() > 0))
	{
		TimeZone.setDefault(TimeZone.getTimeZone(defaultTimeZoneID));
		
		PageCounts pageCountsA4Color = new PageCounts();
		pageCountsA4Color.setName(NAME_A4COLOR);
		pageCountsA4Color.setCount(countA4Color);
		if(dateA4Color == null || dateA4Color.equalsIgnoreCase("") || dateA4Color.length()==0){
			String dateGMT = DateUtil.getCurrentDateInGMT();
			
			pageCountsA4Color.setDate(DateUtil.getDateInStringFormatWithTimestamp(dateGMT));
		}
		else
		{
			
			pageCountsA4Color.setDate(dateA4Color);
			
		
		}
			
		pagecountsList.add(pageCountsA4Color);
	}
	
	if((countA4LTPC != null) && (!(countA4LTPC.equalsIgnoreCase(""))) && (countA4LTPC.length() > 0))
	{
		TimeZone.setDefault(TimeZone.getTimeZone(defaultTimeZoneID));
		
		PageCounts pageCountsA4LTPC = new PageCounts();
		pageCountsA4LTPC.setName(NAME_A4LTPC);
		pageCountsA4LTPC.setCount(countA4LTPC);
		
		if(dateA4LTPC == null || dateA4LTPC.equalsIgnoreCase("") || dateA4LTPC.length()==0){
			String dateGMT = DateUtil.getCurrentDateInGMT();
			
			pageCountsA4LTPC.setDate(DateUtil.getDateInStringFormatWithTimestamp(dateGMT));
		}
		else
		{
			
			pageCountsA4LTPC.setDate(dateA4LTPC);
			
		
		}
			
		pagecountsList.add(pageCountsA4LTPC);
	}
	
	if((countScan != null) && (!(countScan.equalsIgnoreCase(""))) && (countScan.length() > 0))
	{
		TimeZone.setDefault(TimeZone.getTimeZone(defaultTimeZoneID));
		
		PageCounts pageCountsScan = new PageCounts();
		pageCountsScan.setName(NAME_SCANS);
		pageCountsScan.setCount(countScan);
		if(dateScan == null || dateScan.equalsIgnoreCase("") || dateScan.length()==0){
			String dateGMT = DateUtil.getCurrentDateInGMT();
			
			pageCountsScan.setDate(DateUtil.getDateInStringFormatWithTimestamp(dateGMT));
		}
		else
		{
			
			pageCountsScan.setDate(dateScan);
			
		
		}
			
		pagecountsList.add(pageCountsScan);
	}
	
	if((countFax != null) && (!(countFax.equalsIgnoreCase(""))) && (countFax.length() > 0))
	{
		TimeZone.setDefault(TimeZone.getTimeZone(defaultTimeZoneID));
		
		PageCounts pageCountsFax = new PageCounts();
		pageCountsFax.setName(NAME_FAX);
		pageCountsFax.setCount(countFax);
		
		if(dateFax == null || dateFax.equalsIgnoreCase("") || dateFax.length()==0){
			String dateGMT = DateUtil.getCurrentDateInGMT();
			
			pageCountsFax.setDate(DateUtil.getDateInStringFormatWithTimestamp(dateGMT));
		}
		else
		{
			
			pageCountsFax.setDate(dateFax);
			
		
		}
			
		pagecountsList.add(pageCountsFax);
	}
	if((countBlack != null) && (!(countBlack.equalsIgnoreCase(""))) && (countBlack.length() > 0))
	{
		TimeZone.setDefault(TimeZone.getTimeZone(defaultTimeZoneID));
		
		PageCounts pageCountsBlack = new PageCounts();
		pageCountsBlack.setName(NAME_BLACK);
		pageCountsBlack.setCount(countBlack);
		
		if(dateBlack == null || dateBlack.equalsIgnoreCase("") || dateBlack.length()==0){
			String dateGMT = DateUtil.getCurrentDateInGMT();
			
			pageCountsBlack.setDate(DateUtil.getDateInStringFormatWithTimestamp(dateGMT));
		}
		else
		{
			
			pageCountsBlack.setDate(dateBlack);
			
		
		}
			
		pagecountsList.add(pageCountsBlack);
	}
	if((countCyan != null) && (!(countCyan.equalsIgnoreCase(""))) && (countCyan.length() > 0))
	{
		TimeZone.setDefault(TimeZone.getTimeZone(defaultTimeZoneID));
		
		PageCounts pageCountsCyan = new PageCounts();
		pageCountsCyan.setName(NAME_CYAN);
		pageCountsCyan.setCount(countCyan);
		
		if(dateCyan == null || dateCyan.equalsIgnoreCase("") || dateCyan.length()==0){
			String dateGMT = DateUtil.getCurrentDateInGMT();
			
			pageCountsCyan.setDate(DateUtil.getDateInStringFormatWithTimestamp(dateGMT));
		}
		else
		{
			
			pageCountsCyan.setDate(dateCyan);
			
		
		}
			
		pagecountsList.add(pageCountsCyan);
	}
	if((countSoftware != null) && (!(countSoftware.equalsIgnoreCase(""))) && (countSoftware.length() > 0))
	{
		TimeZone.setDefault(TimeZone.getTimeZone(defaultTimeZoneID));
		
		PageCounts pageCountsSoftware = new PageCounts();
		pageCountsSoftware.setName(NAME_SOFTWARE);
		pageCountsSoftware.setCount(countSoftware);
		
		if(dateSoftware == null || dateSoftware.equalsIgnoreCase("") || dateSoftware.length()==0){
			String dateGMT = DateUtil.getCurrentDateInGMT();
			
			pageCountsSoftware.setDate(DateUtil.getDateInStringFormatWithTimestamp(dateGMT));
		}
		else
		{
			
			pageCountsSoftware.setDate(dateSoftware);
			
		
		}
			
		pagecountsList.add(pageCountsSoftware);
	}
	if((countPgsScanCopy != null) && (!(countPgsScanCopy.equalsIgnoreCase(""))) && (countPgsScanCopy.length() > 0))
	{
		TimeZone.setDefault(TimeZone.getTimeZone(defaultTimeZoneID));
		
		PageCounts pageCountsPgsScanCopy = new PageCounts();
		pageCountsPgsScanCopy.setName(PGS_SCAN_COPY);
		pageCountsPgsScanCopy.setCount(countPgsScanCopy);
		
		if(datePgsScanCopy == null || datePgsScanCopy.equalsIgnoreCase("") || datePgsScanCopy.length()==0){
			String dateGMT = DateUtil.getCurrentDateInGMT();
			
			pageCountsPgsScanCopy.setDate(DateUtil.getDateInStringFormatWithTimestamp(dateGMT));
		}
		else
		{
			
			pageCountsPgsScanCopy.setDate(datePgsScanCopy);
			
		
		}
			
		pagecountsList.add(pageCountsPgsScanCopy);
	}
	if((countPgsScanFax != null) && (!(countPgsScanFax.equalsIgnoreCase(""))) && (countPgsScanFax.length() > 0))
	{
		TimeZone.setDefault(TimeZone.getTimeZone(defaultTimeZoneID));
		
		PageCounts pageCountsPgsScanFax = new PageCounts();
		pageCountsPgsScanFax.setName(PGS_SCAN_FAX);
		pageCountsPgsScanFax.setCount(countPgsScanFax);
		
		if(datePgsScanFax == null || datePgsScanFax.equalsIgnoreCase("") || datePgsScanFax.length()==0){
			String dateGMT = DateUtil.getCurrentDateInGMT();
			
			pageCountsPgsScanFax.setDate(DateUtil.getDateInStringFormatWithTimestamp(dateGMT));
		}
		else
		{
			
			pageCountsPgsScanFax.setDate(datePgsScanFax);
			
		
		}
			
		pagecountsList.add(pageCountsPgsScanFax);
	}
	if((countPgsScanNetwork != null) && (!(countPgsScanNetwork.equalsIgnoreCase(""))) && (countPgsScanNetwork.length() > 0))
	{
		TimeZone.setDefault(TimeZone.getTimeZone(defaultTimeZoneID));
		
		PageCounts pageCountsPgsScanNetwork = new PageCounts();
		pageCountsPgsScanNetwork.setName(PGS_SCAN_NETWORK);
		pageCountsPgsScanNetwork.setCount(countPgsScanNetwork);
		
		if(datePgsScanNetwork == null || datePgsScanNetwork.equalsIgnoreCase("") || datePgsScanNetwork.length()==0){
			String dateGMT = DateUtil.getCurrentDateInGMT();
			
			pageCountsPgsScanNetwork.setDate(DateUtil.getDateInStringFormatWithTimestamp(dateGMT));
		}
		else
		{
			
			pageCountsPgsScanNetwork.setDate(datePgsScanNetwork);
			
		
		}
			
		pagecountsList.add(pageCountsPgsScanNetwork);
	}
	if((countPgsScanUsb != null) && (!(countPgsScanUsb.equalsIgnoreCase(""))) && (countPgsScanUsb.length() > 0))
	{
		TimeZone.setDefault(TimeZone.getTimeZone(defaultTimeZoneID));
		
		PageCounts pageCountsPgsScanUsb = new PageCounts();
		pageCountsPgsScanUsb.setName(PGS_SCAN_USB);
		pageCountsPgsScanUsb.setCount(countPgsScanUsb);
		
		if(datePgsScanUsb == null || datePgsScanUsb.equalsIgnoreCase("") || datePgsScanUsb.length()==0){
			String dateGMT = DateUtil.getCurrentDateInGMT();
			
			pageCountsPgsScanUsb.setDate(DateUtil.getDateInStringFormatWithTimestamp(dateGMT));
		}
		else
		{
			
			pageCountsPgsScanUsb.setDate(datePgsScanUsb);
			
		
		}
			
		pagecountsList.add(pageCountsPgsScanUsb);
	}
	if((countTotalScans != null) && (!(countTotalScans.equalsIgnoreCase(""))) && (countTotalScans.length() > 0))
	{
		TimeZone.setDefault(TimeZone.getTimeZone(defaultTimeZoneID));
		
		PageCounts pageCountsTotalScans = new PageCounts();
		pageCountsTotalScans.setName(TOTAL_SCANS);
		pageCountsTotalScans.setCount(countTotalScans);
		
		if(dateTotalScans == null || dateTotalScans.equalsIgnoreCase("") || dateTotalScans.length()==0){
			String dateGMT = DateUtil.getCurrentDateInGMT();
			
			pageCountsTotalScans.setDate(DateUtil.getDateInStringFormatWithTimestamp(dateGMT));
		}
		else
		{
			
			pageCountsTotalScans.setDate(dateTotalScans);
			
		
		}
			
		pagecountsList.add(pageCountsTotalScans);
	}
	if((countLetterColor != null) && (!(countLetterColor.equalsIgnoreCase(""))) && (countLetterColor.length() > 0))
	{
		TimeZone.setDefault(TimeZone.getTimeZone(defaultTimeZoneID));
		
		PageCounts pageCountsLetterColor = new PageCounts();
		pageCountsLetterColor.setName(LETTER_COLOR);
		pageCountsLetterColor.setCount(countLetterColor);
		
		if(dateLetterColor == null || dateLetterColor.equalsIgnoreCase("") || dateLetterColor.length()==0){
			String dateGMT = DateUtil.getCurrentDateInGMT();
			
			pageCountsLetterColor.setDate(DateUtil.getDateInStringFormatWithTimestamp(dateGMT));
		}
		else
		{
			
			pageCountsLetterColor.setDate(dateLetterColor);
			
		
		}
			
		pagecountsList.add(pageCountsLetterColor);
	}
	if((countLetterLTPC != null) && (!(countLetterLTPC.equalsIgnoreCase(""))) && (countLetterLTPC.length() > 0))
	{
		TimeZone.setDefault(TimeZone.getTimeZone(defaultTimeZoneID));
		
		PageCounts pageCountsLetterLTPC = new PageCounts();
		pageCountsLetterLTPC.setName(LETTER_LTPC);
		pageCountsLetterLTPC.setCount(countLetterLTPC);
		
		if(dateLetterLTPC == null || dateLetterLTPC.equalsIgnoreCase("") || dateLetterLTPC.length()==0){
			String dateGMT = DateUtil.getCurrentDateInGMT();
			
			pageCountsLetterLTPC.setDate(DateUtil.getDateInStringFormatWithTimestamp(dateGMT));
		}
		else
		{
			
			pageCountsLetterLTPC.setDate(dateLetterLTPC);
			
		
		}
			
		pagecountsList.add(pageCountsLetterLTPC);
	}
	if((countA5LTPC != null) && (!(countA5LTPC.equalsIgnoreCase(""))) && (countA5LTPC.length() > 0))
	{
		TimeZone.setDefault(TimeZone.getTimeZone(defaultTimeZoneID));
		
		PageCounts pageCountsA5LTPC = new PageCounts();
		pageCountsA5LTPC.setName(NAME_A5LTPC);
		pageCountsA5LTPC.setCount(countA5LTPC);
		
		if(dateA5LTPC == null || dateA5LTPC.equalsIgnoreCase("") || dateA5LTPC.length()==0){
			String dateGMT = DateUtil.getCurrentDateInGMT();
			
			pageCountsA5LTPC.setDate(DateUtil.getDateInStringFormatWithTimestamp(dateGMT));
		}
		else
		{
			
			pageCountsA5LTPC.setDate(dateA5LTPC);
			
		
		}
			
		pagecountsList.add(pageCountsA5LTPC);
	}
	if((countA5Color != null) && (!(countA5Color.equalsIgnoreCase(""))) && (countA5Color.length() > 0))
	{
		TimeZone.setDefault(TimeZone.getTimeZone(defaultTimeZoneID));
		
		PageCounts pageCountsA5Color = new PageCounts();
		pageCountsA5Color.setName(NAME_A5COLOR);
		pageCountsA5Color.setCount(countA5Color);
		
		if(dateA5Color == null || dateA5Color.equalsIgnoreCase("") || dateA5Color.length()==0){
			String dateGMT = DateUtil.getCurrentDateInGMT();
			
			pageCountsA5Color.setDate(DateUtil.getDateInStringFormatWithTimestamp(dateGMT));
		}
		else
		{
			
			pageCountsA5Color.setDate(dateA5Color);
			
		
		}
			
		pagecountsList.add(pageCountsA5Color);
	}
	if((countLegalColor != null) && (!(countLegalColor.equalsIgnoreCase(""))) && (countLegalColor.length() > 0))
	{
		TimeZone.setDefault(TimeZone.getTimeZone(defaultTimeZoneID));
		
		PageCounts pageCountsLegalColor = new PageCounts();
		pageCountsLegalColor.setName(LEGAL_COLOR);
		pageCountsLegalColor.setCount(countLegalColor);
		
		if(dateLegalColor == null || dateLegalColor.equalsIgnoreCase("") || dateLegalColor.length()==0){
			String dateGMT = DateUtil.getCurrentDateInGMT();
			
			pageCountsLegalColor.setDate(DateUtil.getDateInStringFormatWithTimestamp(dateGMT));
		}
		else
		{
			
			pageCountsLegalColor.setDate(dateLegalColor);
			
		
		}
			
		pagecountsList.add(pageCountsLegalColor);
	}
	if((countLegalLTPC != null) && (!(countLegalLTPC.equalsIgnoreCase(""))) && (countLegalLTPC.length() > 0))
	{
		TimeZone.setDefault(TimeZone.getTimeZone(defaultTimeZoneID));
		
		PageCounts pageCountsLegalLTPC = new PageCounts();
		pageCountsLegalLTPC.setName(LEGAL_LTPC);
		pageCountsLegalLTPC.setCount(countLegalLTPC);
		
		if(dateLegalLTPC == null || dateLegalLTPC.equalsIgnoreCase("") || dateLegalLTPC.length()==0){
			String dateGMT = DateUtil.getCurrentDateInGMT();
			
			pageCountsLegalLTPC.setDate(DateUtil.getDateInStringFormatWithTimestamp(dateGMT));
		}
		else
		{
			
			pageCountsLegalLTPC.setDate(dateLegalLTPC);
			
		
		}
			
		pagecountsList.add(pageCountsLegalLTPC);
	}
	if((countStatementLTPC != null) && (!(countStatementLTPC.equalsIgnoreCase(""))) && (countStatementLTPC.length() > 0))
	{
		TimeZone.setDefault(TimeZone.getTimeZone(defaultTimeZoneID));
		
		PageCounts pageCountsStatementLTPC = new PageCounts();
		pageCountsStatementLTPC.setName(STATEMENT_LTPC);
		pageCountsStatementLTPC.setCount(countStatementLTPC);
		
		if(dateStatementLTPC == null || dateStatementLTPC.equalsIgnoreCase("") || dateStatementLTPC.length()==0){
			String dateGMT = DateUtil.getCurrentDateInGMT();
			
			pageCountsStatementLTPC.setDate(DateUtil.getDateInStringFormatWithTimestamp(dateGMT));
		}
		else
		{
			
			pageCountsStatementLTPC.setDate(dateStatementLTPC);
			
		
		}
			
		pagecountsList.add(pageCountsStatementLTPC);
	}
	if((countStatementColor != null) && (!(countStatementColor.equalsIgnoreCase(""))) && (countStatementColor.length() > 0))
	{
		TimeZone.setDefault(TimeZone.getTimeZone(defaultTimeZoneID));
		
		PageCounts pageCountsStatementColor = new PageCounts();
		pageCountsStatementColor.setName(STATEMENT_COLOR);
		pageCountsStatementColor.setCount(countStatementColor);
		
		if(dateStatementColor == null || dateStatementColor.equalsIgnoreCase("") || dateStatementColor.length()==0){
			String dateGMT = DateUtil.getCurrentDateInGMT();
			
			pageCountsStatementColor.setDate(DateUtil.getDateInStringFormatWithTimestamp(dateGMT));
		}
		else
		{
			
			pageCountsStatementColor.setDate(dateStatementColor);
			
		
		}
			
		pagecountsList.add(pageCountsStatementColor);
	}
	if((countTabloidLTPC != null) && (!(countTabloidLTPC.equalsIgnoreCase(""))) && (countTabloidLTPC.length() > 0))
	{
		TimeZone.setDefault(TimeZone.getTimeZone(defaultTimeZoneID));
		
		PageCounts pageCountsTabloidLTPC = new PageCounts();
		pageCountsTabloidLTPC.setName(TABLOID_LTPC);
		pageCountsTabloidLTPC.setCount(countTabloidLTPC);
		
		if(dateTabloidLTPC == null || dateTabloidLTPC.equalsIgnoreCase("") || dateTabloidLTPC.length()==0){
			String dateGMT = DateUtil.getCurrentDateInGMT();
			
			pageCountsTabloidLTPC.setDate(DateUtil.getDateInStringFormatWithTimestamp(dateGMT));
		}
		else
		{
			
			pageCountsTabloidLTPC.setDate(dateTabloidLTPC);
			
		
		}
			
		pagecountsList.add(pageCountsTabloidLTPC);
	}
	if((countTabloidColor != null) && (!(countTabloidColor.equalsIgnoreCase(""))) && (countTabloidColor.length() > 0))
	{
		TimeZone.setDefault(TimeZone.getTimeZone(defaultTimeZoneID));
		
		PageCounts pageCountsTabloidColor = new PageCounts();
		pageCountsTabloidColor.setName(TABLOID_COLOR);
		pageCountsTabloidColor.setCount(countTabloidColor);
		
		if(dateTabloidColor == null || dateTabloidColor.equalsIgnoreCase("") || dateTabloidColor.length()==0){
			String dateGMT = DateUtil.getCurrentDateInGMT();
			
			pageCountsTabloidColor.setDate(DateUtil.getDateInStringFormatWithTimestamp(dateGMT));
		}
		else
		{
			
			pageCountsTabloidColor.setDate(dateTabloidColor);
			
		
		}
			
		pagecountsList.add(pageCountsTabloidColor);
	}
	
	// added for updating mono in siebel (LTPC-Color) 15.3 start *
		boolean oldLTPC = false;
		String LTPC_Count="";
		
		//boolean newLTPC;
		if(countLTPC.trim().equals("") && !LTPC.trim().equals("")){
			oldLTPC=true;
			LTPC_Count = LTPC.trim();
		}
		if(!countLTPC.trim().equals("") && LTPC.trim().equals("")){
			oldLTPC=false;
			LTPC_Count = countLTPC.trim();
		}
		if(!countLTPC.trim().equals("") && !LTPC.trim().equals("")){
			oldLTPC=false;
			LTPC_Count = countLTPC.trim();
		}
		if(countLTPC.trim().equals("") && LTPC.trim().equals("")){
			oldLTPC=false;
		}
		
		boolean oldColor = false;
		String color_Count="";
		if(countColor.trim().equals("") && !color.trim().equals("")){
			oldColor=true;
			color_Count = color.trim();
		}
		if(!countColor.trim().equals("") && color.trim().equals("")){
			oldColor=false;
			color_Count = countColor.trim();
		}
		if(!countColor.trim().equals("") && !color.trim().equals("")){
			oldColor=false;
			color_Count = countColor.trim();
		}
		if(countColor.trim().equals("") && color.trim().equals("")){
			oldColor=false;
		}
		
		if(!oldColor || !oldLTPC){		
			if(!LTPC_Count.trim().equals("") && !color_Count.equals("")){
				logger.debug("in if mono set");
				PageCounts pageCountMono = new PageCounts();
				pageCountMono.setName(NAME_MONO);
				int mono = Integer.parseInt(LTPC_Count)-Integer.parseInt(color_Count);
				logger.debug("mono "+mono);
				monoCount = Integer.toString(mono);
				logger.debug("monoCount "+monoCount);
				pageCountMono.setCount(monoCount);					
				String dateGMT = DateUtil.getCurrentDateInGMT();				
				pageCountMono.setDate(DateUtil.getDateInStringFormatWithTimestamp(dateGMT));
				logger.debug("date is "+pageCountMono.getDate());
				pagecountsList.add(pageCountMono);
				logger.debug("mono set");
			}
		}
		/*if(!countLTPC.trim().equals("") &&  !countColor.trim().equals("")){
			logger.debug("in if mono set");
			PageCounts pageCountMono = new PageCounts();
			pageCountMono.setName(NAME_MONO);
			int mono = Integer.parseInt(countLTPC)-Integer.parseInt(countColor);
			monoCount = Integer.toString(mono);
			pageCountMono.setCount(monoCount);		
			pagecountsList.add(pageCountMono);
			logger.debug("mono set");
		}*/
		
		for(PageCounts pg : pagecountsList){
			logger.debug("page counts contract start");
			ObjectDebugUtil.printObjectContent(pg, logger);
			logger.debug("Page Counts contract end");
		}
		// added for updating mono in siebel (LTPC-Color) 15.3 end *
	
	final Asset asset = new Asset();			
	asset.setAssetId(selectedAssetId);
	asset.setPageCounts(pagecountsList);
	
	

	
	StringBuffer responseBody=new StringBuffer();	
	
	CrmSessionHandle crmSessionHandle = globalService.initCrmSessionHandle(PortalSessionUtil.getSiebelCrmSessionHandle(request));
	
	try{
		
		
		
		UpdateAssetMeterReadContract contract = ContractFactory.getUpdateAssetMeterReadContract(request);
		
		contract.setSessionHandle(crmSessionHandle);
		contract.setAsset(asset);
		
		
		ObjectDebugUtil.printObjectContent(contract,logger);
		
		
		
		LexmarkTransaction lexmarkTran = PerformanceTracker.startTracking(LexmarkConstants.TARGET_SYSTEM_SIEBEL, 
				"updateAssetMeterRead", PortalSessionUtil.getMdmId(session), 
				PortalSessionUtil.getServiceUser(session).getEmailAddress());
		UpdateAssetMeterReadResult meterReadResult = meterReadService.updateAssetMeterRead(contract);
		PerformanceTracker.endTracking(lexmarkTran);	
		
		globalService.releaseSessionHandle(crmSessionHandle);
		
		
		
		
		
		if(meterReadResult.getResult())
		{
			
		
				responseBody.append("\""+ChangeMgmtConstant.SUCCESS+"\":\"<strong>"+PropertiesMessageUtil.getPropertyMessage
						(LexmarkSPConstants.MESSAGE_BUNDLE_NAME,
					"devicemgmt.meterreads.update.success.mesg", request.getLocale())+"</strong>\"");
			
			
		}	
	
	}catch (Exception e) {
			
				
			logStackTrace(e);
			
			request.setAttribute("errorAttribute", "true");//This is done for the time being
			
						
			
			if(responseBody.length()>0)
			{
			
			responseBody.append(","+"\""+ChangeMgmtConstant.ERROR+"\":\"<strong>"+PropertiesMessageUtil.getPropertyMessage
					(LexmarkSPConstants.MESSAGE_BUNDLE_NAME,
			"devicemgmt.meterreads.update.failure.mesg", request.getLocale())+"</strong>\"");
			}
			else{
			responseBody.append("\""+ChangeMgmtConstant.ERROR+"\":\"<strong>"+PropertiesMessageUtil.getPropertyMessage
					(LexmarkSPConstants.MESSAGE_BUNDLE_NAME,
					"devicemgmt.meterreads.update.failure.mesg", request.getLocale())+"</strong>\"");
			}
		}
	
	finally{
		
		globalService.releaseSessionHandle(crmSessionHandle);
		TimeZone.setDefault(TimeZone.getTimeZone(defaultTimeZoneID));
		
		if(responseBody!=null)
		{
			logger.debug("Response body in the finally before brackets -------------> " + responseBody.toString());
		}
		
		responseBody.insert(0, "{");
		responseBody.insert(responseBody.length(), "}");
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		out.print(responseBody);
		out.flush();
		out.close();
		responseBody.delete(0, responseBody.length());
		
	}
}	
    
    //brmandal ended for Page count changes - MPS phase 2.1
    
    /**
     * This method is used to retrieve the device list in make updates online page in page counts
     * @param request 
     * @param response 
     * @throws Exception 
     */
	@ResourceMapping("retrievePageCountsList")
	public void retriveDeviceList(ResourceRequest request, ResourceResponse response) throws Exception{
		
		
		
		MeterReadAssetListContract contract = ContractFactory.getMeterReadContract(request);
		loadFilterCriteria(request, contract);
		String viewType = request.getParameter("pageName");
		if ("BookMarked".equalsIgnoreCase(viewType)){
			contract.setFavoriteFlag(true);
		}else{
			contract.setFavoriteFlag(false);
		}
		PortletSession session = request.getPortletSession();
		String contactId = PortalSessionUtil.getContactId(request.getPortletSession());
		contract.setContactId(contactId);
		CrmSessionHandle crmSessionHandle = globalService.initCrmSessionHandle
				(PortalSessionUtil.getSiebelCrmSessionHandle(request));
		try { 

			contract.setSessionHandle(crmSessionHandle);
			/*contract.setContactId("1-16UQFAG");
			contract.setMdmID("205529410");
			contract.setMdmLevel("Global");
			contract.setMeterReadType("Manual");*/
			session.setAttribute("downLoadMeterReadContract", contract);
			LexmarkTransaction lexmarkTran = PerformanceTracker.startTracking(LexmarkConstants.TARGET_SYSTEM_SIEBEL, 
					"retrieveMeterReadAssetList", PortalSessionUtil.getMdmId(session), 
					PortalSessionUtil.getServiceUser(session).getEmailAddress());
			logger.debug("mdm level "+contract.getMdmLevel() + "mdm id "+contract.getMdmID());
			
			logger.debug("ContactId: " + contract.getContactId() + "FilterCriteria: "+contract.getFilterCriteria() + 
					"SortCriteria: "+contract.getSortCriteria() + "SearchCriteria: "+contract.getSearchCriteria() + 
					"MeterReadType: "+contract.getMeterReadType());
			logger.debug("The Alliance Partner flag is---->>>"+(Boolean)session.getAttribute(ChangeMgmtConstant.ALLIANCE_PARTNER, PortletSession.APPLICATION_SCOPE));
			contract.setAlliancePartner((Boolean)session.getAttribute(ChangeMgmtConstant.ALLIANCE_PARTNER, PortletSession.APPLICATION_SCOPE));
			ObjectDebugUtil.printObjectContent(contract, logger);
			MeterReadAssetListResult meterReadAssetListResult = meterReadService.retrieveMeterReadAssetList(contract);
			
			PerformanceTracker.endTracking(lexmarkTran);
			List<Asset>  deviceList = meterReadAssetListResult.getAssets();
			for (Asset device : deviceList) {
				ProductImageContract productImageContract = new ProductImageContract();
				productImageContract.setPartNumber(device.getProductTLI());
				ProductImageResult productImageResult = productImageService.retrieveProductImageUrl(productImageContract);
				device.setProductImageURL(productImageResult.getProductImageUrl());
				sharedPortletController.assembleDevice(device, request.getLocale());
			}
			String content = getXmlOutputGenerator(request.getLocale()).convertMeterReadAssetToXML
					(request.getLocale(),deviceList,
					meterReadAssetListResult.getTotalCount(),
					contract.getStartRecordNumber(),
					request.getContextPath(), PortalSessionUtil.getContactId(session));
			PrintWriter out = response.getWriter();
			response.setContentType("text/xml");
			out.print(content);
			out.flush();
			out.close();
		} finally {
			globalService.releaseSessionHandle(crmSessionHandle);
		}

		
	}
	
	/**
	 * This method is called when we want to download the help file in upload csv page in page counts
	 * @param request 
	 * @param response 
	 * @param model 
	 * @throws IOException 
	 */
	@ResourceMapping("downLoadHelpFileURL")
	public void downLoadHelpFile(ResourceRequest request,
			ResourceResponse response, Model model) throws IOException{
		
		
		ResourceBundle aucBundle = ResourceBundle.getBundle("com.lexmark.services.resources.assetUploadColumnsInfo");
		String[] columns = aucBundle.getString("columns").split(",");
		ResourceBundle rsBundle = ResourceBundle.getBundle("com.lexmark.services.resources.messages");
		String csvStr="";
		for(String str:columns){
			csvStr +=rsBundle.getString(PRIEW_BUNDLE_NAME_COLUMN+str)+",";
		}
		csvStr = csvStr.substring(0,(csvStr.length()-1))+"\n";
		for(String str:columns){
			if("readDate".equals(str)){
				csvStr +=currentDate+",";
			}else{
				csvStr +=rsBundle.getString(PRIEW_BUNDLE_NAME_EXAMPLE+str)+",";
			}
		}
		csvStr = csvStr.substring(0,(csvStr.length()-1))+"\n";
		
		String fileSufix = "_" + request.getLocale().getLanguage() + ".csv";
		
		String fileName = TEMPLATE_FILE_PREFIX +fileSufix;
		response.setProperty("Content-disposition", "attachment; filename="
				+ fileName);
		response.setContentType("text/csv");
		PrintWriter out = ResourceResponseUtil.getUTF8PrintWrtierWithBOM(response);
		out.print(csvStr);
		out.flush();
		out.close();
		
	}
	
	/**
	 * Renders the Print page for Page Counts in make updates online page
	 * @param request 
	 * @param response 
	 * @param model 
	 * @return String
	 * @throws Exception 
	 */
	@RequestMapping(params = "action=printMeterReadDeviceList")
	public String showServiceRequestPrintPage(RenderRequest request, RenderResponse response, Model model) throws Exception{
		return "pageCounts/pageCountsListPrint";
	}
	
	/**
	 * This method is called for customer hierarchy link in make updates online page in page counts
	 * @param request 
	 * @param response 
	 * @param model 
	 * @throws Exception 
	 */
	@ResourceMapping("chlTreeXMLURL")
	public void retrieveCHLTreeXML(ResourceRequest request,
			ResourceResponse response, Model model) throws Exception{
		sharedPortletController.retrieveCHLTreeXML(request, response, model);
	}
	
	/**
	 * This method is called for device location link in make updates online page in page counts
	 * @param request 
	 * @param response 
	 * @param model 
	 * @throws Exception 
	 */
	@ResourceMapping("deviceLocationXMLURL")
	public void retrieveDeviceLocationTreeXML(ResourceRequest request,
			ResourceResponse response, Model model) throws Exception{
		String pageFrom="";
		sharedPortletController.retrieveDeviceLocationTreeXML(request, response, model,pageFrom);
	}
	
	/**
	 * This method is called for download csv button to download the meter read list of page counts
	 * @param request 
	 * @param response 
	 * @param model 
	 * @throws Exception 
	 */
	@ResourceMapping("exportPageCount")
	public void exportPageCount(ResourceRequest request,
			ResourceResponse response, Model model) throws Exception{
		MeterReadAssetListContract contract = ContractFactory.getMeterReadContract(request);
		contract.setStartRecordNumber(0);
		contract.setIncrement(Integer.valueOf(MINUES_ONE));
		String exportType = request.getParameter("exportType");
		logger.debug("exportType is "+exportType);
		contract.setFavoriteFlag(false);
		//Changed for CI CR 15.1
		String noOfDays = "";
		if("BookMarked".equals(exportType)){
			contract.setMeterReadType("BookMarked");
			contract.setFavoriteFlag(true);
		}else if("Manual".equals(exportType)){
		}else if("Missing Manual".equals(exportType)){
			contract.setMeterReadType("Missing Manual");
			noOfDays = request.getParameter("noOfDays");
			int numofDays = Integer.parseInt(noOfDays);
			contract.setNumofDays(numofDays);
		}else if("Missing Automated".equals(exportType)){
			contract.setMeterReadType("Missing Automated");
			noOfDays = request.getParameter("noOfDays");
			int numofDays = Integer.parseInt(noOfDays);
			contract.setNumofDays(numofDays);
		}else if("Missing All".equals(exportType)){
			contract.setMeterReadType("Missing All");
		}
		//END
		PortletSession session = request.getPortletSession();
		
		String contactId = PortalSessionUtil.getContactId(request.getPortletSession());
		contract.setContactId(contactId);
		CrmSessionHandle crmSessionHandle = globalService.initCrmSessionHandle
				(PortalSessionUtil.getSiebelCrmSessionHandle(request));
		contract.setSessionHandle(crmSessionHandle);
		try {
			session.setAttribute("downLoadMeterReadContract", contract);
			LexmarkTransaction lexmarkTran = PerformanceTracker.startTracking(LexmarkConstants.TARGET_SYSTEM_SIEBEL, 
					"retrieveMeterReadAssetList", PortalSessionUtil.getMdmId(session), 
					PortalSessionUtil.getServiceUser(session).getEmailAddress());
			logger.debug("The Alliance Partner flag is---->>>"+(Boolean)session.getAttribute(ChangeMgmtConstant.ALLIANCE_PARTNER, PortletSession.APPLICATION_SCOPE));
			contract.setAlliancePartner((Boolean)session.getAttribute(ChangeMgmtConstant.ALLIANCE_PARTNER, PortletSession.APPLICATION_SCOPE));
			ObjectDebugUtil.printObjectContent(contract, logger);
			MeterReadAssetListResult meterReadAssetListResult = meterReadService.retrieveMeterReadAssetList(contract);
			PerformanceTracker.endTracking(lexmarkTran);

			List<Asset>  deviceList = meterReadAssetListResult.getAssets();
			exportPageCounttoExcel(response,deviceList,request.getLocale());
			
			
			
		} finally {
			globalService.releaseSessionHandle(crmSessionHandle);
		}
	}
	
	/**
	 * This method is called during upload csv button for mass upload in page counts
	 * @param request 
	 * @param response 
	 * @param model 
	 * @return String
	 * @throws Exception 
	 */
	@RequestMapping(params = "action=importPageCounts")
	public String importPageCounts(RenderRequest request, RenderResponse response, Model model) throws Exception{
		
		PageCountsImportForm pageCountsImportForm = new PageCountsImportForm();
		DateFormat formater = SimpleDateFormat.getDateInstance(DateFormat.SHORT, request.getLocale());
		doShortYearTo4((SimpleDateFormat)formater);
		currentDate = formater.format(new Date());
		pageCountsImportForm.setCurrentDateStr(currentDate);
		
		ResourceBundle bundle = ResourceBundle.getBundle("com.lexmark.services.resources.assetUploadColumnsInfo");
		String[] columns = bundle.getString("columns").split(",");
		int columnLen = columns.length;
		for (int i = 0; i < columnLen; i++) {
			columns[i] = columns[i].trim();
		}
		model.addAttribute("columns", columns);
		model.addAttribute("pageCountsImportForm",pageCountsImportForm);
		pageCountsImportForm.refreshSubmitToken(request);
		
		return "pageCounts/importPageCountsPage";
	}
	
	/**
	 * This method is called for uploading csv file in upload csv section which is for mass upload in page counts
	 * @param request 
	 * @param response 
	 * @param content 
	 * @param pageCountsImportForm 
	 * @param model 
	 * @throws Exception 
	 */
	@RequestMapping(params = "action=doCSVUpload")
	public void doFileUpload(ActionRequest request, ActionResponse response,
			@RequestParam("fileContent") CommonsMultipartFile content,
			@ModelAttribute("pageCountsImportForm") PageCountsImportForm pageCountsImportForm,
			Model model) throws Exception {
		
		PortletSession session = request.getPortletSession();
		AssetMeterReadContract contract = new AssetMeterReadContract();
		CrmSessionHandle crmSessionHandle = globalService.initCrmSessionHandle
				(PortalSessionUtil.getSiebelCrmSessionHandle(request));
		try {
			contract.setSessionHandle(crmSessionHandle);
			String topLevelMdmId = null;
			String topLevelMdmLevel = null;
			if (PortalSessionUtil.getMdmLevel(session).equalsIgnoreCase(LexmarkConstants.GLOBAL)){
				//Already at the top level
				topLevelMdmId = PortalSessionUtil.getMdmIdOrDunsBasedOnLevel(session);
				topLevelMdmLevel = PortalSessionUtil.getMdmLevel(session);
			}else{
				//Need to get the top level for this person's MDM ID and Level
				GlobalLegalEntityContract globalLegalEntityContract = new GlobalLegalEntityContract();
				globalLegalEntityContract.setMdmId(PortalSessionUtil.getMdmId(session));
				globalLegalEntityContract.setMdmLevel(PortalSessionUtil.getMdmLevel(session));

				LexmarkTransaction lexmarkTran = PerformanceTracker.startTracking(LexmarkConstants.TARGET_SYSTEM_SIEBEL, 
						"retrieveGlobalLegalEntity", PortalSessionUtil.getMdmId(session), 
						PortalSessionUtil.getServiceUser(session).getEmailAddress());
				GlobalLegalEntityResult globalLegalEntityResult = globalService.retrieveGlobalLegalEntity
						(globalLegalEntityContract);
				PerformanceTracker.endTracking(lexmarkTran);

				topLevelMdmId = globalLegalEntityResult.getAccount().getMdmId();
				topLevelMdmLevel = globalLegalEntityResult.getAccount().getMdmLevel();
				
			}
			contract.setMdmId(topLevelMdmId);
			//brmandal added for page count changes - MPS phase 2.1
			contract.setMdmLevel(topLevelMdmLevel);
			contract.setContactId(PortalSessionUtil.getContactId(session));
			//brmandal ended for page count changes - MPS phase 2.1
			
		//Changes for new Filename-Starts
			String dateStr = DateUtil.getDateInStringFormatWithTimestamp(DateUtil.getCurrentDateInGMT());
		    String [] dateTime= dateStr.split("\\s");
		    Format formatter = new SimpleDateFormat("yyyyMMdd");
		    String currentDate=formatter.format(new Date());
		    
		    String fileName=PortalSessionUtil.getFirstName(session)+"_"+PortalSessionUtil.getLastName(session)+"_"+currentDate+"_"+dateTime[1].replaceAll(":", "")+".csv";
		    logger.debug("filename is-->"+fileName);
			//Changes for new Filename-Ends	
			contract.setUserFileName(StringUtil.removeSpaces(fileName.trim()));
			//contract.setUserFileName(StringUtil.removeSpaces(content.getOriginalFilename().trim()));
			//NOTE:  The topLevelMdmId will be part of the filename that will be sent to Siebel.  
			// The filename will be topLevelMdmId~originalfilename~yyyymmddhhmmssmmm.csv
			boolean success = false;
			String targetPage = "";
			CSV2AssetConverter csv2AssetConverter = null;

			if (PortalSessionUtil.isDuplicatedSubmit(request,
					pageCountsImportForm)) {
				logger.debug("duplicated submit, do nothing!");			
			}else if(content.isEmpty()){
				ServiceStatusUtil.checkServiceStatus(model, EXCEPTION_PAGE_COUNTS_FILE_NOT_FOUND, 
						request.getLocale(), true);
				targetPage = "importPageCounts";
			}else{
				try{
					
					InputStreamReader read = new InputStreamReader(content.getInputStream());
					BufferedReader reader=new BufferedReader(read);
					csv2AssetConverter = new CSV2AssetConverter(reader, request.getLocale());
					success=csv2AssetConverter.processData();
				}catch (Exception e) {
					success = false;
				}
				String errorCode;
				if(!success){
					for(CSVError error :csv2AssetConverter.getErrors()){
						if(error.getErrorValue()!=null){
							Object[] locations = new Object[]{error.getErrorValue() ,error.getRow() ,error.getColumn()};
							errorCode = "exception.pageCounts."+error.geterrorCode();
							ServiceStatusUtil.checkServiceStatus(model, errorCode, request.getLocale(), locations,true);
						}
						else {
							errorCode = "exception.pageCounts."+error.geterrorCode();
							ServiceStatusUtil.checkServiceStatus(model, errorCode, request.getLocale(), true);
						}

					}
					targetPage = "importPageCounts";
				}else{
					InputStream insertedStream =  insertUploadHeaderAndMdmIdAndLevelInStream(content.getInputStream(), 
							topLevelMdmId, topLevelMdmLevel);
					contract.setFileStream(insertedStream);
					AssetMeterReadResult result= new AssetMeterReadResult();
					
					ObjectDebugUtil.printObjectContent(contract,logger);
					
					try{
						LexmarkTransaction lexmarkTran = PerformanceTracker.startTracking(LexmarkConstants.TARGET_SYSTEM_SIEBEL, 
								"importAssetMeterRead", PortalSessionUtil.getMdmId(session), 
								PortalSessionUtil.getServiceUser(session).getEmailAddress());
						result = meterReadService.importAssetMeterRead(contract);
						PerformanceTracker.endTracking(lexmarkTran);
					}catch (Exception e) {
						logger.debug("**********"+EXCEPTION_PAGE_COUNTS_FILE_NOT_FOUND+"*****************");
						
					}
					if(result.isUpDateSuccess()){
						errorCode = MESSAGE_PAGE_COUNTS_UPLOAD_CSV;
						ServiceStatusUtil.checkServiceStatus(model, errorCode, request.getLocale(), true);
					}else{
						errorCode = EXCEPTION_PAGE_COUNTS_UPLOAD_FAILED;
						ServiceStatusUtil.checkServiceStatus(model, errorCode, request.getLocale(), true);
						targetPage = "importPageCounts";
					}
				}
			}
			response.setRenderParameter("action", targetPage);
		} finally {
			globalService.releaseSessionHandle(crmSessionHandle);
		}

		
	}
	
	/**
	 * This method is called for inserting mdm id and level 
	 * @param inputStream  
	 * @param mdmId  
	 * @param mdmLevel  
	 * @return InputStream 
	 * @throws IOException  
	 */
	private InputStream  insertUploadHeaderAndMdmIdAndLevelInStream(InputStream inputStream, String mdmId, String mdmLevel)
			throws IOException {
		
		InputStreamReader read = new InputStreamReader(inputStream);
		BufferedReader reader=new BufferedReader(read);
		
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		BufferedWriter out
		   = new BufferedWriter(new OutputStreamWriter(bout));
		
		/*String prefix = (mdmId==null?"," : "" + ",");
		prefix = prefix + (mdmLevel==null? "" : "" + ",");*/
		try {
		
			//boolean headerLine = true;
			String line = null;
			boolean handler_up_header_one = true;
			boolean handler_up_header_two = false;
			
			while ((line = reader.readLine()) != null) {
				logger.debug("line "+line);
				    if(handler_up_header_one) {
			    		handler_up_header_one = false;
				    	handler_up_header_two = true;
				    	if(line.indexOf(CSV2AssetConverter.UP_HEADER_ONE)>-1) {
				    		out.write(line);
					    	out.newLine();
				    		continue;
				    	} 
				    	out.write(CSV2AssetConverter.UP_HEADER_ONE);
				    	out.newLine();
				    }
			    	if(CSV2AssetConverter.HAS_HEADER_LINE && handler_up_header_two ==true){
			    		if(line.indexOf(CSV2AssetConverter.UP_HEADER_TWO) > -1) {
			    			out.write(line);
			    		} else {
				    		out.write(CSV2AssetConverter.UP_HEADER_TWO);
			    		}
			    		out.newLine();
			    		handler_up_header_two = false;
			    		continue;
			    	}
			    
			    	out.write(removeLeadingSingleQuoteForSerialNumberColumn(line));
		    		out.newLine();
			}
			out.flush();
		 byte[] buff = bout.toByteArray();
		 ByteArrayInputStream bin = new ByteArrayInputStream(buff);
		 return bin;
		} catch(IOException ex) {
			throw ex;
		}
		finally {
			read.close();
			reader.close();
			bout.close();
			out.close();
		}
	}
	
	/**
	 * This method is used to remove single quote for serial number column
	 * @param line 
	 * @return String
	 */
	private String removeLeadingSingleQuoteForSerialNumberColumn(String line) {
		if(line == null || !line.contains("'")) {
			return line;
		}
		String[] columns = line.split("[,]");
		StringBuffer sb = new StringBuffer();
		int serialNumberIndex = 1;
		int columnLen = columns.length;
		for(int i=0; i< columnLen; i++) {
			if(i== serialNumberIndex) {
				if(columns[i]!=null && columns[i].startsWith("'")) {
					sb.append(columns[i].substring(1));
				} else {
					sb.append(columns[i]);
				}
			} else {
				sb.append(columns[i]);
			}
			if(i<(columns.length -1)) {
				sb.append(",");
			}
		}
		return sb.toString();
	}
	
	/**
	 * This method is used to download only csv file from page counts
	 * @param request 
	 * @param response 
	 * @param model 
	 * @throws Exception 
	 */
	@ResourceMapping("outPutFileURL")
	public void outPutCSVFile(ResourceRequest request,
			ResourceResponse response, Model model)throws Exception{
		
		MeterReadStatusContract contract = new MeterReadStatusContract();
		contract.setUserFileName(request.getParameter("fileName"));
		CrmSessionHandle crmSessionHandle = globalService.initCrmSessionHandle
				(PortalSessionUtil.getSiebelCrmSessionHandle(request));
		contract.setSessionHandle(crmSessionHandle);
		try{
			PortletSession session = request.getPortletSession();
			LexmarkTransaction lexmarkTran = PerformanceTracker.startTracking(LexmarkConstants.TARGET_SYSTEM_SIEBEL, 
					"retrieveMeterReadStatusFile",PortalSessionUtil.getMdmId(session), 
					PortalSessionUtil.getServiceUser(session).getEmailAddress());
			MeterReadStatusFileResult result = meterReadService.retrieveMeterReadStatusFile(contract);
			PerformanceTracker.endTracking(lexmarkTran);
			InputStreamReader read = new InputStreamReader(result.getFileStream());
			BufferedReader reader=new BufferedReader(read);
			String fileStr = "";
			String line = "";
			while((line = reader.readLine())!=null){
				fileStr = fileStr + line +"\n";
			}
			
			response.setProperty("Content-disposition", "attachment; filename="
					+ request.getParameter("fileName"));
			response.setContentType("text/csv");
			PrintWriter out = ResourceResponseUtil.getUTF8PrintWrtierWithBOM(response);
			out.print(fileStr);
			out.flush();
			out.close();
		}catch (Exception e) {
			
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			out.print("<h1>" +
					PropertiesMessageUtil.getPropertyMessage(null,
							EXCEPTION_PAGE_COUNTS_FILE_NOT_FOUND,
							request.getLocale()) +
					"</h1>");
			out.flush();
			out.close();
		}
		
	}
	
	/**
	 * renders download csv page in page counts
	 * @param request 
	 * @param response 
	 * @param model 
	 * @return String
	 * @throws Exception 
	 */
	// commented for defect 16844
	/*@RequestMapping(params = "action=pageCountExport")
	public String pageCountExport(RenderRequest request, RenderResponse response, Model model) throws Exception{
		return "pageCounts/pageCountExportPage";
	}*/
	
	/**
	 * This method is called to display the meter read list in make updates online in page counts
	 * @param request 
	 * @param response 
	 * @param model 
	 * @return String
	 * @throws Exception 
	 */
	@RequestMapping(params = "action=showMRListAdvancedSearchPage")
	public String showSRListAdvancedSearchOptionPage(RenderRequest request, RenderResponse response, Model model) 
			throws Exception{
		sharedPortletController.retrieveAdvanceSearchData(request, model);
		return "pageCounts/pageCountsAdvancedSearch";
	}
	
	/**
	 * This method is used to display validation warning in page counts
	 * @param request 
	 * @param response 
	 * @param model 
	 * @return String
	 * @throws Exception 
	 */
	@RequestMapping(params = "action=showValidateWarning")
	public String showValidateWarning(RenderRequest request, RenderResponse response, Model model) throws Exception{
		return "pageCounts/validateWarning";
	}
	
	/**
	 * This method is used to download meter read files in page counts
	 * @param request 
	 * @param response 
	 * @param model 
	 * @throws Exception 
	 */
	@ResourceMapping("downloadMeterReadURL")
	public void downloadMeterRead(ResourceRequest request,
			ResourceResponse response, Model model) throws Exception{
		
		String downloadType = request.getParameter("downloadType");
		Locale locale = request.getLocale();
		PortletSession session = request.getPortletSession();
		MeterReadAssetListContract contract =  (MeterReadAssetListContract) session.getAttribute("downLoadMeterReadContract");
		CrmSessionHandle crmSessionHandle = globalService.initCrmSessionHandle
				(PortalSessionUtil.getSiebelCrmSessionHandle(request));
		try {
			contract.setSessionHandle(crmSessionHandle);
			contract.setStartRecordNumber(0);
			contract.setIncrement(Integer.valueOf(BaseController.MINUES_ONE));
			LexmarkTransaction lexmarkTran = PerformanceTracker.startTracking(LexmarkConstants.TARGET_SYSTEM_SIEBEL, 
					"retrieveMeterReadAssetList",PortalSessionUtil.getMdmId(session), 
					PortalSessionUtil.getServiceUser(session).getEmailAddress());
			logger.debug("The Alliance Partner flag is---->>>"+(Boolean)session.getAttribute(ChangeMgmtConstant.ALLIANCE_PARTNER, PortletSession.APPLICATION_SCOPE));
			contract.setAlliancePartner((Boolean)session.getAttribute(ChangeMgmtConstant.ALLIANCE_PARTNER, PortletSession.APPLICATION_SCOPE));
			ObjectDebugUtil.printObjectContent(contract, logger);
			MeterReadAssetListResult meterReadAssetListResult = meterReadService.retrieveMeterReadAssetList(contract);
			PerformanceTracker.endTracking(lexmarkTran);	

			List<Asset> assetList = meterReadAssetListResult.getAssets();
			if("csv".equals(downloadType)){
				downloadMeterReadCSV(response,assetList,locale);
			}else if("pdf".equals(downloadType)){
				downloadMeterReadPDF(response,assetList,locale);
			}else{
				throw new Exception(ServiceStatusErrorMessageUtil.getErrorMessage(LexmarkSPConstants.ERROR_MESSAGE_BUNDLE, 
						"exception.portlet.downloadException", null, request.getLocale()));
			}
		} finally {
			globalService.releaseSessionHandle(crmSessionHandle);
		}

		
	}
	
	/**
	 * Goes to control panel page.
	 * @param request 
	 * @param response 
	 * @param model 
	 * @return the jsp page of control panel page 
	 * @throws Exception 
	 */
	@RequestMapping(params = "action=gotoControlPanel")
	public String gotoControlPanel(RenderRequest request, RenderResponse response,
			Model model) throws Exception {
		model.addAttribute("controlPanelURL",request.getParameter("controlPanelURL"));
		model.addAttribute("pageName", "Page Counts");
		return "controlPanelPage";
	}
	
	
	/**
	 * The format output by this method should be exactly same as the upload file format.   
	 * @param response  
	 * @param deviceList  
	 * @param locale  
	 * @throws IOException  
	 */
	private void exportPageCounttoExcel(ResourceResponse response, List<Asset> deviceList,
			Locale locale) throws IOException {
		String fileName = null;
		fileName = DownloadFileLocalizationUtil.getMeterReadFileName(locale) + ".csv";
		response.setProperty("Content-disposition", "attachment; filename="
				+ fileName);
		response.setContentType("text/csv");
		PrintWriter out = ResourceResponseUtil.getUTF8PrintWrtierWithBOM(response);
		out.print(assetListToCVS(deviceList, locale));
		out.flush();
		out.close();
	}
	
	/**
	 * this method is used to display the asset list in csv format in page counts
	 * @param deviceList 
	 * @param locale 
	 * @return String
	 */
	private String assetListToCVS(
			List<Asset> deviceList, Locale locale) {
		StringBuffer sb = new StringBuffer();
		
		String  productLineHeader = DownloadFileLocalizationUtil.getPropertyLocaleValue
				("pageCntUploadLegend.column.productLine", locale);
		sb.append(productLineHeader);
		sb.append(",");

		String  serialNumberHeader = DownloadFileLocalizationUtil.getPropertyLocaleValue
				("pageCntUploadLegend.column.serialNumber", locale);
		sb.append(serialNumberHeader);
		sb.append(",");

		//brmandal added for Page Counts - MPS phase 2.1
		
		String  newPageCountHeader = DownloadFileLocalizationUtil.getPropertyLocaleValue
				("pageCntUploadLegend.column.newPageCount", locale);
		sb.append(newPageCountHeader);
		sb.append(",");

		String  newColorCountHeader = DownloadFileLocalizationUtil.getPropertyLocaleValue
				("pageCntUploadLegend.column.newColorCount", locale);
		sb.append(newColorCountHeader);
		sb.append(",");
				
		
		
		String  a4LTPCHeader = DownloadFileLocalizationUtil.getPropertyLocaleValue
				("pageCntUploadLegend.column.a4LTPC", locale);
		sb.append(a4LTPCHeader);
		sb.append(",");
		
		String  a4ColorHeader = DownloadFileLocalizationUtil.getPropertyLocaleValue
				("pageCntUploadLegend.column.a4Color", locale);
		sb.append(a4ColorHeader);
		sb.append(",");
		
		String  a5LTPCHeader = DownloadFileLocalizationUtil.getPropertyLocaleValue
				("pageCntUploadLegend.column.a5LTPC", locale);
		sb.append(a5LTPCHeader);
		sb.append(",");
		
		String  a5ColorHeader = DownloadFileLocalizationUtil.getPropertyLocaleValue
				("pageCntUploadLegend.column.a5Color", locale);
		sb.append(a5ColorHeader);
		sb.append(",");
		
		String  scansHeader = DownloadFileLocalizationUtil.getPropertyLocaleValue
				("pageCntUploadLegend.column.scans", locale);
		sb.append(scansHeader);
		sb.append(",");
		
		
		
		
		//brmandal ended for Page counts - MPS phase 2.1
		
		String  readDateHeader = DownloadFileLocalizationUtil.getPropertyLocaleValue
				("pageCntUploadLegend.column.readDate", locale);
		sb.append(readDateHeader);
		sb.append("\n");

		if (deviceList != null) {
			for (int i = 0; i < deviceList.size(); i++) {
				Asset device = null;
				device = deviceList.get(i);
				
				if(device!=null){
					fillDeviceValue(sb, device);
				}
			}
			return sb.toString();
		} else {
			return sb.toString();
		}
	}
	
	
	/**
	 * Same as the upload fields   Serial Number, Product Line,  Read Date, NewPage Count , New Color Count
	 */
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	/**
	 * 
	 * @param sb 
	 * @param asset 
	 */
	@SuppressWarnings("unchecked")
	private void fillDeviceValue(StringBuffer sb, Asset asset) {
		//brmandal added for Page count changes - MPS phase 2.1
		List<PageCounts> pagecountsList = asset.getPageCounts();
		if(pagecountsList == null){
			return;
		}
		String[] listOnlyPageCounts = new String[10];
		String[] listOnlyReadDates = new String[10];
		Date[] dateList	= new Date[7];
		List<String> exceptionList = new ArrayList<String>();
		try{
			
			for(PageCounts pagecounts : pagecountsList){
			
				logger.debug("pagecounts.getName("+pagecounts.getName());
				logger.debug("pagecounts.getDate()"+pagecounts.getDate());
				if(pagecounts.getName().equalsIgnoreCase(NAME_LTPC)){
					listOnlyPageCounts[0] = pagecounts.getCount();
					
					listOnlyReadDates[0] = DateUtil.getDateInStringFormat(pagecounts.getDate());
					if((pagecounts.getDate().isEmpty()) || (pagecounts.getDate().equalsIgnoreCase("null"))){
						dateList[0] = dateFormat.parse("01/01/1900");
					}else{
						dateList[0] = dateFormat.parse(listOnlyReadDates[0]);
					}
				}
				if(pagecounts.getName().equalsIgnoreCase(NAME_COLOR)){
					listOnlyPageCounts[1] = pagecounts.getCount();
					
					listOnlyReadDates[1] = DateUtil.getDateInStringFormat(pagecounts.getDate());
					if((pagecounts.getDate().isEmpty()) || (pagecounts.getDate().equalsIgnoreCase("null"))){
						dateList[1] = dateFormat.parse("01/01/1900");
					}else{
						dateList[1] = dateFormat.parse(DateUtil.getDateInStringFormat(pagecounts.getDate()));
					}
				}
				
				if(pagecounts.getName().equalsIgnoreCase(NAME_A4COLOR)){
					listOnlyPageCounts[3] = pagecounts.getCount();
					listOnlyReadDates[3] = DateUtil.getDateInStringFormat(pagecounts.getDate());
					if((pagecounts.getDate().isEmpty()) || (pagecounts.getDate().equalsIgnoreCase("null"))){
						dateList[3] = dateFormat.parse("01/01/1900");
					}else{
						dateList[3] = dateFormat.parse(DateUtil.getDateInStringFormat(pagecounts.getDate()));
					}
				}
				if(pagecounts.getName().equalsIgnoreCase(NAME_A4LTPC)){
					listOnlyPageCounts[2] = pagecounts.getCount();
					listOnlyReadDates[2] = DateUtil.getDateInStringFormat(pagecounts.getDate());
					if((pagecounts.getDate().isEmpty()) || (pagecounts.getDate().equalsIgnoreCase("null"))){
						dateList[2] = dateFormat.parse("01/01/1900");
					}else{
						dateList[2] = dateFormat.parse(DateUtil.getDateInStringFormat(pagecounts.getDate()));
					}
				}
				if(pagecounts.getName().equalsIgnoreCase(NAME_SCANS)){
					listOnlyPageCounts[6] = pagecounts.getCount();
					listOnlyReadDates[6] = DateUtil.getDateInStringFormat(pagecounts.getDate());
					if((pagecounts.getDate().isEmpty()) || (pagecounts.getDate().equalsIgnoreCase("null"))){
						dateList[6] = dateFormat.parse("01/01/1900");
					}else{
						dateList[6] = dateFormat.parse(DateUtil.getDateInStringFormat(pagecounts.getDate()));
					}
				}
				
				if(pagecounts.getName().equalsIgnoreCase(NAME_A5COLOR)){
					listOnlyPageCounts[5] = pagecounts.getCount();
					listOnlyReadDates[5] = DateUtil.getDateInStringFormat(pagecounts.getDate());
					if((pagecounts.getDate().isEmpty()) || (pagecounts.getDate().equalsIgnoreCase("null"))){
						dateList[5] = dateFormat.parse("01/01/1900");
					}else{
						dateList[5] = dateFormat.parse(DateUtil.getDateInStringFormat(pagecounts.getDate()));
					}
				}
				if(pagecounts.getName().equalsIgnoreCase(NAME_A5LTPC)){
					listOnlyPageCounts[4] = pagecounts.getCount();
					listOnlyReadDates[4] = DateUtil.getDateInStringFormat(pagecounts.getDate());
					if((pagecounts.getDate().isEmpty()) || (pagecounts.getDate().equalsIgnoreCase("null"))){
						dateList[4] = dateFormat.parse("01/01/1900");
					}else{
						dateList[4] = dateFormat.parse(DateUtil.getDateInStringFormat(pagecounts.getDate()));
					}
				}
				
		}
		
		for(int i=0;i<=6;i++)
		{
			
			if(dateList[i] == null){
				dateList[i] = dateFormat.parse("01/01/1900 00:00:00");
			}
		}
		
		List listOfdates = Arrays.asList(dateList);
		
		Collections.sort(listOfdates, new Comparator<Date>(){
			public int compare (Date d1, Date d2){
	               return d1.compareTo(d2);
			}
		});
		
		for(int i=0;i<=6;i++)
		{
			logger.debug("dateListPageCounts in for loop post sort "+dateList[i] +",");
			
		}
		
		}catch(ParseException pe)
		{
			exceptionList.add(pe.getMessage());
			
		}
		
		
		if(asset.getProductTLI() != null){
			sb.append(asset.getProductTLI());
		}
		
		sb.append(",");
		//put leading single quote to prevent EXCEL truncating leading zeros
		sb.append("'");
		
		if(asset.getSerialNumber() != null) {
			
			sb.append(asset.getSerialNumber());
		}
		sb.append(",");
		
		
		//brmandal added for Page count changes - MPS phase 2.1

				for(int i=0;i<=6;i++){
					logger.debug("listOnlyPageCounts[i]"+listOnlyPageCounts[i]);
					if(StringUtils.isBlank(listOnlyPageCounts[i])){
							listOnlyPageCounts[i] = "";
					}
						sb.append(listOnlyPageCounts[i]);
						
						
						sb.append(",");
				}
		
		
					sb.append(DateUtil.getDateInStringFormat(dateList[6].toString()));
		
			
		
		//brmandal ended for Page count changes - MPS phase 2.1
		sb.append("\n");
		
	}
	
	/**
	 * This method is called from downloadMeterReadURL method to downloadMeterRead in CSV format
	 * @param response 
	 * @param deviceList 
	 * @param locale 
	 * @throws IOException 
	 */
	private void downloadMeterReadCSV(ResourceResponse response, List<Asset> deviceList,
			Locale locale) throws IOException {
		String fileName = null;
		fileName = DownloadFileLocalizationUtil.getMeterReadFileName(locale) + ".csv";
		response.setProperty("Content-disposition", "attachment; filename="
				+ fileName);
		response.setContentType("text/csv");
		PrintWriter out = ResourceResponseUtil.getUTF8PrintWrtierWithBOM(response);
		out.print(DownloadFileUtil.assembleToDeviceListCSV(deviceList, locale));
		out.flush();
		out.close();
	}
	
	/**
	 * This method is called from downloadMeterReadURL to downloadMeterRead in pdf format
	 * @param response 
	 * @param deviceList 
	 * @param locale 
	 * @throws IOException 
	 */
	private void downloadMeterReadPDF(ResourceResponse response, List<Asset> deviceList,
			Locale locale) throws IOException {
		String fileName = DownloadFileLocalizationUtil.getMeterReadFileName(locale) + ".pdf";
		response.setProperty("Content-disposition", "attachment; filename="
				+ fileName);
		response.setContentType("application/pdf");
	//Changes for MPS Phase 2.1 defect no. 8103---Start
		String[] headers = new String[]{"Device", "Asset Tag",
				"Serial Number", "IP Address", "Location", "Model", "Address Name", "House Number", "City",
				"State", "Province", "County", "District", "Country", "Hostname",
				"MAC Address", "Machine Type Model", "Primary Contact First Name", "Primary Contact Last Name", "Product PN/TLI", "Last Read Date", 
				"Read Date", "Last Lifetime Count", "New Lifetime Count", "Last Color Count", "New Color Count"};
		
		String[] generatorPatterns = new String[]{"deviceName", "assetTag",
				"serialNumber", "ipAddress", "physicalLocationAddress.physicalLocation1", "productLine", 
				"installAddress.addressName", "installAddress.officeNumber",
				"installAddress.city", "installAddress.state", "installAddress.province", "installAddress.county", 
				"installAddress.district", "installAddress.country", "hostName", "macAddress", 
				"machineTypeModel", "assetContact.firstName", "assetContact.lastName", "productTLI", "lastPageReadDate", "lastColorPageReadDate", 
				"lastPageCount", "newPageCount", "lastColorPageCount", "newColorPageCount"};
				
				//END
		List<String> exceptionList = new ArrayList<String>();
		PdfPReportGenerator generator = new PdfPReportGenerator(headers, generatorPatterns, deviceList);
		generator.setLocale(locale);
		OutputStream responseOut = response.getPortletOutputStream();
		try {
			generator.generate(responseOut);
			responseOut.flush();
		}
		finally {
			if(responseOut != null) {
				try {
					responseOut.close();
				} catch (IOException ignored) {
					exceptionList.add(ignored.getMessage());
				}
			}
		}
	}
	
	/**
	 * This method is used to display the bookmark and unbookmark device in page counts
	 * @param favoriteAssetId 
	 * @param favoriteFlag 
	 * @param request 
	 * @param response 
	 * @throws Exception 
	 */
	@ResourceMapping("updateUserFavoriteAsset")
	public void updateUserFavoriteAsset(@RequestParam("favoriteAssetId") String favoriteAssetId,
			@RequestParam("favoriteFlag") boolean favoriteFlag,
			ResourceRequest request, ResourceResponse response) throws Exception {
		sharedPortletController.updateUserFavoriteAsset(favoriteAssetId, favoriteFlag, request, response);
	}
	
	/**
	 * This method is used to update asset meter read list in page counts 
	 * @param assetId  
	 * @param rowId  
	 * @param newPageCount  
	 * @param newColorPageCount  
	 * @param newReadDate  
	 * @param request  
	 * @param response  
	 * @throws Exception  
	 */
	@ResourceMapping("updateAssetMeterRead")
	public void updateAssetMeterRead(@RequestParam("assetId") String assetId,
			@RequestParam("selectedRowId") String rowId,
			@RequestParam("newPageCount") String newPageCount,
			@RequestParam("newColorPageCount") String newColorPageCount,
			@RequestParam("newReadDate") Date newReadDate,
			@RequestParam("oldPageCount") String oldPageCount,
			@RequestParam("oldColorPageCount") String oldColorPageCount,
			ResourceRequest request, ResourceResponse response) throws Exception {
		boolean success = false;
		String defaultTimeZoneID = new String(""); 
		try{
			
			
			PortletSession session = request.getPortletSession();
			CrmSessionHandle crmSessionHandle = globalService.initCrmSessionHandle
					(PortalSessionUtil.getSiebelCrmSessionHandle(request));
			try {
				Asset asset = new Asset();
				Date currentDate = new Date();
				newReadDate.setHours(currentDate.getHours());
				newReadDate.setMinutes(currentDate.getMinutes());
				
				asset.setAssetId(assetId);
			//brmandal added for Page count changes - MPS phase 2.1	
				// added start for updating mono in siebel 15.3 *
				String monoCount="";
				//String LTPC_count="";
				//String color_count="";
				// added end for updating mono in siebel 15.3 *
				List<PageCounts> pagecountsList = new ArrayList<PageCounts>();
				SimpleDateFormat converter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
				defaultTimeZoneID = converter.getTimeZone().getID();
				if((newPageCount != null) && (!(newPageCount.equalsIgnoreCase(""))) && (newPageCount.length() > 0))
				{
					
					PageCounts pageCountsLTPC = new PageCounts();
					pageCountsLTPC.setName(NAME_LTPC);
					pageCountsLTPC.setCount(newPageCount);
					
					//LTPC_count = newPageCount;
					
					
					
					
					
					String dateStr = DateUtil.getDateInStringFormatWithTimestamp(DateUtil.getCurrentDateInGMT());
					
					pageCountsLTPC.setDate(dateStr);
					pagecountsList.add(pageCountsLTPC);
					
				}
				if((newColorPageCount != null) && (!(newColorPageCount.equalsIgnoreCase(""))) && 
						(newColorPageCount.length() > 0)
						&& ((!newColorPageCount.equalsIgnoreCase("undefined"))))
				{
					TimeZone.setDefault(TimeZone.getTimeZone(defaultTimeZoneID));
					
					PageCounts pageCountsColor = new PageCounts();
					pageCountsColor.setName(NAME_COLOR);
					pageCountsColor.setCount(newColorPageCount);
					//color_count = newColorPageCount;
					
					String dateStr = DateUtil.getDateInStringFormatWithTimestamp(DateUtil.getCurrentDateInGMT());
					
					pageCountsColor.setDate(dateStr);
					pagecountsList.add(pageCountsColor);
				}
				// added start for updating mono in siebel 15.3 *
				String LTPC="";				
				boolean oldLTPC=false;
				if(!oldPageCount.trim().equals("") && newPageCount.trim().equals("")){
					LTPC=oldPageCount.trim();
					oldLTPC=true;
				}
				if(oldPageCount.trim().equals("") && !newPageCount.trim().equals("")){
					LTPC=newPageCount.trim();
					oldLTPC=false;
				}
				if(!oldPageCount.trim().equals("") && !newPageCount.trim().equals("")){
					LTPC=newPageCount.trim();
					oldLTPC=false;
				}
				if(oldPageCount.trim().equals("") && newPageCount.trim().equals("")){
					oldLTPC=false;
				}
				String color="";				
				boolean oldColor=false;
				if(!oldColorPageCount.trim().equals("") && newColorPageCount.trim().equals("")){
					color=oldColorPageCount.trim();
					oldColor=true;
				}
				if(oldColorPageCount.trim().equals("") && !newColorPageCount.trim().equals("")){
					color=newColorPageCount.trim();
					oldColor=false;
				}
				if(!oldColorPageCount.trim().equals("") && !newColorPageCount.trim().equals("")){
					color=newColorPageCount.trim();
					oldColor=false;
				}
				if(oldColorPageCount.trim().equals("") && newColorPageCount.trim().equals("")){
					oldColor=false;
				}
				if(!oldLTPC || !oldColor){
					if(!LTPC.trim().equals("") && !color.trim().equals("") && !"undefined".equalsIgnoreCase(color)){
						logger.debug("in if setting mono");
						int mono=Integer.parseInt(LTPC)-Integer.parseInt(color);
						monoCount = Integer.toString(mono);
						logger.debug("mono to be set value "+monoCount);
						PageCounts pageCountsMono = new PageCounts();
						pageCountsMono.setName(NAME_MONO);
						pageCountsMono.setCount(monoCount);
						String dateGMT = DateUtil.getCurrentDateInGMT();				
						pageCountsMono.setDate(DateUtil.getDateInStringFormatWithTimestamp(dateGMT));
						logger.debug("date is "+pageCountsMono.getDate());
						pagecountsList.add(pageCountsMono);
						pagecountsList.add(pageCountsMono);
						logger.debug("mono set");
						
					}
				}
					
				
				// added end for updating mono in siebel 15.3 *
				
				asset.setPageCounts(pagecountsList);
				
								
				UpdateAssetMeterReadContract contract = ContractFactory.getUpdateAssetMeterReadContract(request);
				contract.setSessionHandle(crmSessionHandle);
				contract.setAsset(asset);
				LexmarkTransaction lexmarkTran = PerformanceTracker.startTracking(LexmarkConstants.TARGET_SYSTEM_SIEBEL, 
						"updateAssetMeterRead", PortalSessionUtil.getMdmId(session), 
						PortalSessionUtil.getServiceUser(session).getEmailAddress());
				UpdateAssetMeterReadResult meterReadResult = meterReadService.updateAssetMeterRead(contract);
				
				PerformanceTracker.endTracking(lexmarkTran);	
				
				success = meterReadResult.getResult();
			} finally {
				globalService.releaseSessionHandle(crmSessionHandle);
				TimeZone.setDefault(TimeZone.getTimeZone(defaultTimeZoneID));
			}

		}catch (Exception e) {
			success = false;
			
		}
		
		
		String errorCode = success?"message.meterRead.updateAssetMeterRead"
				:"exception.meterRead.updateAssetMeterRead";
		boolean isCorlorCapable = true;
		if("undefined".equals(newColorPageCount)){
			isCorlorCapable= false;
		}
		ServiceStatusUtil.responseResult(response,errorCode, request.getLocale(),
				"[\""+ rowId+"\","+isCorlorCapable+","+success+"]");
	}
	
	/**
	 * this method is used for meter read flag as true or false
	 * @param request 
	 * @return 
	 * @throws Exception 
	 */
	private boolean isManualMeterReadFlag(RenderRequest request)
		throws Exception {
	PortletSession session = request.getPortletSession();
	String createServiceRequestFlag = (String) session
			.getAttribute("manualMeterReadFlag");
	if ("true".equals(createServiceRequestFlag)) {
		return true;
	} else if ("false".equals(createServiceRequestFlag)) {
		return false;
	} else {
		SiebelAccountListContract siebelAccountListContract = ContractFactory
				.getSiebelAccountListContract(request);
		CrmSessionHandle crmSessionHandle = globalService
				.initCrmSessionHandle(PortalSessionUtil
						.getSiebelCrmSessionHandle(request));
		try {
			siebelAccountListContract.setSessionHandle(crmSessionHandle);
			
			LexmarkTransaction lexmarkTran = PerformanceTracker.startTracking(LexmarkConstants.TARGET_SYSTEM_SIEBEL, 
					"retrieveSiebelAccountList", 
					PortalSessionUtil.getMdmId(session), PortalSessionUtil.getServiceUser(session).getEmailAddress());
			SiebelAccountListResult siebelAccountListResult = serviceRequestService
			.retrieveSiebelAccountList(siebelAccountListContract);
			PerformanceTracker.endTracking(lexmarkTran);
			List<Account> accountList = siebelAccountListResult
			.getAccountList();
			
			for (Account account : accountList) {
				if (account.getManualMeterRead().equalsIgnoreCase("update")) {
					session.setAttribute("manualMeterReadFlag", "true");
					return true;
				}
			}
		} finally {
			globalService.releaseSessionHandle(crmSessionHandle);
		}

		session.setAttribute("manualMeterReadFlag", "false");
		return false;
	}
}
	
	/**
	 * This method is used for sorting in grid in make updates online page in page counts
	 * @param request 
	 * @param contract 
	 */
	private void loadFilterCriteria(ResourceRequest request, MeterReadAssetListContract contract){
		if (request.getParameter("country") != null) {
			contract.getFilterCriteria().put("installAddress.country", request.getParameter("country"));
		}
		if (request.getParameter("province") != null) {
			contract.getFilterCriteria().put("installAddress.province", request.getParameter("province"));
		}
		if (request.getParameter("state") != null) {
			contract.getFilterCriteria().put("installAddress.state", request.getParameter("state"));
		}
		if (request.getParameter("city") != null) {
			contract.getFilterCriteria().put("installAddress.city", request.getParameter("city"));
		}
		if (request.getParameter("chlNodeId") != null) {
			contract.setChlNodeId(request.getParameter("chlNodeId"));
		}
	}
	
	/**
	 * This method is used for date fields for date format in page counts
	 * @param sdf 
	 */
	private void doShortYearTo4( SimpleDateFormat sdf )
	  {
	    String sTemp = sdf.toPattern();
	    int iLen = sTemp.length();
	    int i = sTemp.lastIndexOf( 'y' ) + 1;
	    sTemp =  sTemp.substring(  0, i ) + 
	            "yy" + 
	            ( i < iLen 
	              ?  sTemp.substring( i, iLen )
	              :  "" );
	    sdf.applyPattern( sTemp );
	  } // end do4
}
