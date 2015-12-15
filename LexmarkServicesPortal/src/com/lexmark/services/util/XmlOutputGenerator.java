package com.lexmark.services.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;
import javax.portlet.PortletURL;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.xml.sax.SAXException;

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.contract.LocalizedServiceActivityStatusContract;
import com.lexmark.contract.LocalizedServiceStatusContract;
import com.lexmark.contract.LocalizedSiebelValueContract;
import com.lexmark.domain.AccountContact;
import com.lexmark.domain.AccountCustomerReceivable;
import com.lexmark.domain.Asset;
import com.lexmark.domain.Bundle;
import com.lexmark.domain.EmailNotification;
import com.lexmark.domain.EmailNotificationLocale;
import com.lexmark.domain.GenericAddress;
import com.lexmark.domain.Invoice;
import com.lexmark.domain.ListOfValues;
import com.lexmark.domain.MeterReadStatus;
import com.lexmark.domain.Notification;
import com.lexmark.domain.OrderPart;
import com.lexmark.domain.PageCounts;
import com.lexmark.domain.Part;
import com.lexmark.domain.Price;
import com.lexmark.domain.Report;
import com.lexmark.domain.ReportDefinition;
import com.lexmark.domain.Role;
import com.lexmark.domain.RoleCategory;
import com.lexmark.domain.SapPortlet;
import com.lexmark.domain.ServiceRequest;
import com.lexmark.domain.ServiceRequestActivity;
import com.lexmark.domain.ServiceRequestOrderLineItem;
import com.lexmark.domain.SiebelLocalization;
import com.lexmark.domain.SiebelLocalization.SiebelLocalizationOptionEnum;
import com.lexmark.enums.RunLogStatusEnum;
import com.lexmark.exceptionimpl.runtime.LGSRuntimeException;
import com.lexmark.result.HardwareCatalogResult;
import com.lexmark.result.LocalizedServiceActivityStatusResult;
import com.lexmark.result.LocalizedServiceStatusResult;
import com.lexmark.result.LocalizedSiebelValueResult;
import com.lexmark.result.PriceResult;
import com.lexmark.result.ReportInstanceListResult;
import com.lexmark.result.ReportListRow;
import com.lexmark.service.api.ServiceRequestLocale;
import com.lexmark.service.impl.real.jdbc.InfrastructureException;
import com.lexmark.service.impl.real.jdbc.ServiceRequestLocaleImpl;
import com.lexmark.services.LexmarkSPConstants;
import com.lexmark.services.form.AssetDetailPageForm;
import com.lexmark.services.reports.bean.ReportListOutput;
import com.lexmark.services.reports.bean.ReportListRecord;
import com.lexmark.util.BusinessObjectUtil;
import com.lexmark.util.DateLocalizationUtil;
import com.lexmark.util.DateUtil;
import com.lexmark.util.PropertiesMessageUtil;
import com.lexmark.util.StringUtil;
import com.lexmark.util.TimezoneUtil;
import com.lexmark.util.URLEncryptUtilForCustomerInvoice;
import com.lexmark.domain.CompanyCode;
/**
 * @author Wipro
 * @version 2.1
 */
public class XmlOutputGenerator {
	/**
	 * Variable Declaration
	 */
	private static Logger logger = LogManager.getLogger(XmlOutputGenerator.class);
	/**
	 * Variable Declaration
	 */
	private static final String FAVORIATE_IMAGE_FILE_NAME = "favorite.png";
	/**
	 * Variable Declaration
	 */
	private static final String UNFAVORIATE_IMAGE_FILE_NAME = "unfavorite.png";
	/**
	 * Variable Declaration
	 */
	private static final String LOADING_IMAGE_FILE_NAME="loading-icon.gif";
	/**
	 * Variable Declaration
	 */
	private static final String PENCIL_IMAGE_FILE_NAME = "pencil.png";
	/**
	 * Variable Declaration
	 */
	private static final String WRENCH_IMAGE_FILE_NAME = "wrench_sm.png";
	/**
	 * Variable Declaration
	 */
	private static final String FILE_ICON_PATH = "/images/fileIcons/";
	/**
	 * Variable Declaration
	 */
	private static final String IMAGE_PATH = "/images/";
	/**
	 * Variable Declaration
	 */
	private static final String ARROW_UP_FILE_NAME = "arrow-up.png";
	/**
	 * Variable Declaration
	 */
	private static final String ARROW_DOWN_FILE_NAME = "arrow-down.png";
	/**
	 * Variable Declaration
	 */
	private static final String ARROW_UP_GRAYED_FILE_NAME = "arrow-up-grayed.png";
	/**
	 * Variable Declaration
	 */
	private static final String ARROW_DOWN_GRAYED_FILE_NAME = "arrow-down-grayed.png";
	/**
	 * Variable Declaration
	 */
	private static final String DELETE_FILE_NAME = "delete.png";
	/**
	 * Variable Declaration
	 */
	private static final String TRANSPARENT_FILE_NAME = "transparent.png";
	/**
	 * Variable Declaration
	 */
	private static final String EMAIL_FILE_NAME = "email_sm.png";
	/**
	 * Variable Declaration
	 */
	private static final String CALENDAR_ICON_NAME = "ic_cal.png";
	/**
	 * Variable Declaration
	 */
	private static final String IMG_OPEN = "<img onMouseOver=\"this.style.cursor='pointer'\" src=\"";
	/**
	 * Variable Declaration
	 */
	private static final String IMG_GRAYED_OPEN = "<img style=\"width:14px; height:14px;\" src=\"";
	/**
	 * Variable Declaration
	 */
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	/**
	 * Variable Declaration
	 */
	private static final String INDENT = "    ";
	/**
	 * Variable Declaration
	 */
	private static final String DATE_TIME_FORMAT = "MM/dd/yyyy HH:mm:ss";
	/**
	 * Variable Declaration
	 */
	private static final String IMAGE_NOT_FOUND = "Not found";
	/**
	 * Variable Declaration
	 */
	private static String LB_BOOKMARKED_DEVICE = "";
	/**
	 * Variable Declaration
	 */
    private static String LB_CONTROL_PANEL = "";
    /**
	 * Variable Declaration
	 */
    private static String LB_LOCATION = "";
    /**
	 * Variable Declaration
	 */
    
    private static String LB_BUILDING = "";
    /**
	 * Variable Declaration
	 */
	private static String LB_FLOOR = "";
	/**
	 * Variable Declaration
	 */
	private static String LB_OFFICE = "";
	/**
	 * Variable Declaration
	 */
    private static String LB_SUPPORT_DOWNLOADS = "";
    /**
	 * Variable Declaration
	 */
    private static String LB_VIEW_MORE = "";
    /**
	 * Variable Declaration
	 */
    private static String LB_PHYSICAL_LOCATION = "";
    /**
	 * Variable Declaration
	 */
    private static String LB_INSTALLED_ADDRESS = "";
    /**
	 * Variable Declaration
	 */
    private static String LB_PROBLEM_DESCRIPTION = "";
    /**
	 * Variable Declaration
	 */
    private static String LB_SERVICE_LOCATION = "";  
    /**
	 * Variable Declaration
	 */
    private static String LB_PRIMARY_CONTACT = "";
    /**
	 * Variable Declaration
	 */
    private static String LB_ETA_DETL="";
    /**
	 * Variable Declaration
	 */
    private static String LB_RESOLUTION_CODE = "";	// added for CI5
    /**
	 * Variable Declaration
	 */
    private static String LB_SERVICE_ADDRESS = "";
    /**
	 * Variable Declaration
	 */
    private static String LB_UNBOOKMARKED_DEVICE = "";
    /**
	 * Variable Declaration
	 */
    private static String LB_CREATESERVICE_REQ ="";
    /**
	 * Variable Declaration
	 */
    private static String BUNDLENAME_BUTTON_MSG = "com.lexmark.services.resources.messages";
    /**
	 * Variable Declaration
	 */
    private static String LAB_BUTTON_SAVE = "button.save";
    /**
	 * Variable Declaration
	 */
    private static String LB_UPDATE_PAGECOUNT = "deviceDetail.link.updatePageCounts";
    /**
	 * Variable Declaration
	 */
    private Locale locale;
    /**
	 * Variable Declaration
	 */
	private ServiceRequestLocale  serviceRequestLocale;
	
	/**. This variable holds the url to open the meter reads popup from device list grid */
	private static String meterReadsPopUpURL;
	/**
	 * Variable Declaration
	 */
	private String expaditeImgSrc= IMAGE_PATH+ "gridImgs/expdt.gif";
	/**
	 * Variable Declaration
	 */
	private static final String LEXMARK_RECOMENDED_MESSAGEKY ="orderSupplies.label.lexmarkRecommended";
	/**
	 * Variable Declaration
	 */
	//added for defect 8083 , MPS 2.1 
	private static final String REQUESTTYPEHARDWARE = "Hardware Request";
	
	private static String LB_DEVICEDESC = "";
	private static String LB_CUSTASSETTAG = "";
	private static String LB_INSTALLDATE = "";
	private static String LB_IPADDRESS = "";
	private static String LB_HOSTNAME = "";
	/**
	 * 
	 */
	public static DateFormat INVOICE_DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");
	/**
	 * @return the meterReadsPopUpURL
	 */
	public static String getMeterReadsPopUpURL() {
		return meterReadsPopUpURL;
	}
	/**
	 * @param meterReadsPopUpURL the meterReadsPopUpURL to set
	 */
	public static void setMeterReadsPopUpURL(String meterReadsPopUpURL) {
		XmlOutputGenerator.meterReadsPopUpURL = meterReadsPopUpURL;
	}
	/**
	 * @return ServiceRequestLocale 
	 */
	public ServiceRequestLocale getServiceRequestLocale() {
    	if(serviceRequestLocale == null) {
    		serviceRequestLocale = new ServiceRequestLocaleImpl();
    	}
		return serviceRequestLocale;
	}
	/**
	 * @param serviceRequestLocale 
	 */
	public void setServiceRequestLocale(ServiceRequestLocale serviceRequestLocale) {
		this.serviceRequestLocale = serviceRequestLocale;
	}
    
	/**
	 * @param locale 
	 */
	public  XmlOutputGenerator(Locale locale){
		setLocale(locale);
	}

	/**
	 * @return Locale 
	 */
	public Locale getLocale() {
		if(locale ==null) {
			locale = Locale.US;
		}
		return locale;
	}

	/**
	 * @param locale 
	 */
	public void setLocale(Locale locale) {
		this.locale = locale;
	}
	
	/**
	 * @param a 
	 * @param totalCount 
	 * @param posStart 
	 * @param columnsPattern 
	 * @return String
	 */
	public String generate(List<?> a, int totalCount, int posStart, String[] columnsPattern) {
			logger.debug("-----------Step 4--convert2XML started---------["+System.nanoTime()+"]");
			StringBuffer xml = new StringBuffer("<?xml version=\"1.0\" ?>\n");
			xml = xml.append("<rows total_count=\""+totalCount+"\" pos=\""+posStart+"\">\n");
			for(int i = 0; i < a.size(); i ++){
				xml = xml.append("<row id=\""+ (posStart + i)+"\">\n");
				for(String columnPattern: columnsPattern) {
				    xml = xml.append("<cell><![CDATA[");
				    
				    String value = "";
				    if(a.get(i)!=null){
				    	value = BusinessObjectUtil.formatColumn(a.get(i), columnPattern, locale);
				    }
				   
				    value = localizeServiceRequestStatus(value, columnPattern, locale);
				    if(value == null){ value = "";}
					xml = xml.append(value);
					xml = xml.append("]]></cell>\n");
				}
				xml = xml.append(" </row>\n");				
			}
			xml = xml.append("</rows>\n");
			logger.debug("-----------Step 4--convert2XML End---------["+System.nanoTime()+"]");
			return xml.toString();
	}
	
	/**
	 * @return String 
	 */
	public String generateEmptyXML(){
		StringBuffer xml = new StringBuffer("<?xml version=\"1.0\" ?><rows></rows>");
		return xml.toString();
	}
	
	/**
	 * @param list 
	 * @return String 
	 */
	public String convertServiceRequestNotificationToXML(List<ServiceRequestActivity> list) {
		StringBuffer xml = new StringBuffer("<?xml version=\"1.0\" ?>\n");
		xml.append("<rows>\n");
		int i = 0;
		if (list != null){
			for (ServiceRequestActivity sra : list) {
				xml.append("<row id=\""+i+"\">\n");
				xml.append("	<cell><![CDATA["+ DateLocalizationUtil.formatDateLocale(sra.getActivityDate(),locale) +"]]></cell>\n");
				xml.append("	<cell><![CDATA["+ sra.getRecipientEmail() +"]]></cell>\n");
				xml.append("	<cell><![CDATA[<a href=\"###\" onClick=hideSelect();popupNotificationDecDetail(\""+i+"\")>" + StringUtil.appendEllipsis(sra.getActivityDescription(),50) + "</a>]]></cell>\n");
				xml.append("	<cell><![CDATA["+ sra.getActivityDescription() +"]]></cell>\n");
				xml.append("	<cell><![CDATA["+ XMLEncodeUtil.escapeXMLForDMCM(sra.getComment()) +"]]></cell>\n"); //Added for defect 4515
				xml.append(" </row>\n");
				i ++;
			}			
		}
		xml.append("</rows>\n");
		return xml.toString();
	}
	
	/**
	 * @param request 
	 * @param assets 
	 * @param totalCount 
	 * @param posStart 
	 * @param contextPath 
	 * @param userSiebelContactId 
	 * @param type 
	 * @return String 
	 */
	public String convertAssetToXML(ResourceRequest request,List<Asset> assets, int totalCount, int posStart, String contextPath, String userSiebelContactId, String type) {
		String transparentImageSrc=contextPath + IMAGE_PATH +"transparent.png";
		boolean hidePgCnts = true;
		List<String> userRoleList = PortalSessionUtil.getUserRoles(request.getPortletSession());
		
		if(userRoleList!=null && (userRoleList.contains(LexmarkConstants.ROLE_SERVICE_SUPPORT))){			
			hidePgCnts = false;
		}
		String showViewMore=(String)request.getAttribute("showLinksMore");
		initialLocalizedMessage();
		PortletSession session = request.getPortletSession();
		String createServiceRequestFlag = (String) session.getAttribute("createServiceRequestFlag");
		
		/********Below section is for role based access - create new request link ****/
		
		final Map<String, String> roleAccessMap = (HashMap<String,String>)session.getAttribute(ChangeMgmtConstant.USERACCESSMAPATTRIBUTEFORSR,
				PortletSession.APPLICATION_SCOPE);
		
		final String createSuppliesReqFlag = roleAccessMap.get("CREATE SUPPLIES REQUEST");
		final String createServiceReqFlag = roleAccessMap.get("CREATE SERVICE REQUEST");      // Changes done for LEX:AIR00074846
		final String createAcctMgmtReqFlag = roleAccessMap.get("CREATE CHANGE MGMT REQUEST");
		
		/******** End of the section for role based access ****/
		
		String updateMeterReadsMsg = PropertiesMessageUtil.getPropertyMessage(BUNDLENAME_BUTTON_MSG, LB_UPDATE_PAGECOUNT, locale);
		
		StringBuffer xml = new StringBuffer(
				"<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		xml.append("<rows total_count=\"" + totalCount + "\" pos=\"" + posStart
				+ "\">\n");
		int i = 0;
		String imageSrc;
		String imageName;
		boolean deviceBookmarked = false;
		for (Asset asset : assets) {
			if (asset.getUserFavoriteFlag()) {
				imageSrc = contextPath + IMAGE_PATH + FAVORIATE_IMAGE_FILE_NAME;
				imageName = FAVORIATE_IMAGE_FILE_NAME;
				deviceBookmarked=true;
			} else {
				imageSrc = contextPath + IMAGE_PATH
						+ UNFAVORIATE_IMAGE_FILE_NAME;
				imageName = UNFAVORIATE_IMAGE_FILE_NAME;
				deviceBookmarked=false;
			}
			if (!LexmarkSPConstants.ASSET_LIST_TYPE.equals(type)) {
				xml.append("<row id=\"" + (posStart + i) + "\">\n");
			} else {
				xml.append("<row id=\"" + asset.getAssetId() + "\">\n");
			}
										
			/**********Start of Sub Row Data***********/
			
			xml.append("<cell><![CDATA[");			
			xml.append("<table cellspacing=\"0\" cellpadding=\"0\" class=\"grid-inner-body\">");			
			xml.append("<tr><td class=\"pModel\"><ul><li class=\"strong\">"+(StringUtils.isNotBlank(asset.getDescriptionLocalLang())==true?asset.getDescriptionLocalLang():""));		
			xml.append("</li><li><img width=\"100\" height=\"100\" id=\"rowid_"+ (posStart + i)+"\" onError=\"image_error(this.id);\" src=\"" + asset.getProductImageURL());			
			xml.append("\"/></li></ul></td>");			
			xml.append("<td width=\"30%\"><ul><li class=\"strong\">" + LB_LOCATION);
			xml.append("</li><li>" + asset.getInstallAddress().getAddressLine1()+"</li>");
			/*
			 * Changes for MPS 2.1
			 * */
			if(asset.getInstallAddress().getOfficeNumber()!=null && !StringUtils.isEmpty(asset.getInstallAddress().getOfficeNumber())){
				xml.append("<li>" + asset.getInstallAddress().getOfficeNumber()+"</li>");
			}
			if(asset.getInstallAddress().getAddressLine2()!=null && !StringUtils.isEmpty(asset.getInstallAddress().getAddressLine2())){
				xml.append("<li>" + asset.getInstallAddress().getAddressLine2()+"</li>");
			}
			xml.append("<li>" + asset.getInstallAddress().getCity());
			if(asset.getInstallAddress().getCounty()!=null && !StringUtils.isEmpty(asset.getInstallAddress().getCounty())){
				xml.append(", " + asset.getInstallAddress().getCounty());
			}
			if(asset.getInstallAddress().getState()!=null && !StringUtils.isEmpty((asset.getInstallAddress().getState()))){
				xml.append(", " + asset.getInstallAddress().getState());
			}
			xml.append("</li>");
			if(asset.getInstallAddress().getProvince()!=null && !StringUtils.isEmpty((asset.getInstallAddress().getProvince()))){
				if(asset.getInstallAddress().getDistrict()!=null && !StringUtils.isEmpty((asset.getInstallAddress().getDistrict()))){
					xml.append("<li>" + asset.getInstallAddress().getProvince());
					xml.append(", " + asset.getInstallAddress().getDistrict()+"</li>");
				}else{
					xml.append("<li>" + asset.getInstallAddress().getProvince()+"</li>");
				}
			}			
			if(asset.getInstallAddress().getPostalCode()!=null && !StringUtils.isEmpty((asset.getInstallAddress().getPostalCode()))){
				xml.append("<li>"+asset.getInstallAddress().getPostalCode()+"</li>");
			}
			xml.append("<li>" + asset.getInstallAddress().getCountry());
			/*
			 * ENDS Changes for MPS 2.1
			 * */
			
			if(!deviceBookmarked){
				xml.append("</li><li class=\"fav\"><img id = 'starIMG_" + asset.getAssetId()
				+ "' style='cursor:Hand;cursor:pointer' onClick=updateUserFavoriteAsset('" + asset.getAssetId()
				+ "') src='" + transparentImageSrc + "'" + "class=\"ui_icon_sprite removebookmark-icon\" name=" + imageName +	"width=\"15\" height=\"15\"/>&nbsp;" +
				"<a id='starTXT_"+asset.getAssetId()+ "' href='#' onClick=updateUserFavoriteAsset('"+ asset.getAssetId()+"')>"	+ 
				LB_BOOKMARKED_DEVICE);
				
			}else{
				xml.append("</li><li class=\"fav\"><img id = 'starIMG_" + asset.getAssetId()
				+ "' style='cursor:Hand;cursor:pointer' onClick=updateUserFavoriteAsset('" + asset.getAssetId()
				+ "')  src='" + transparentImageSrc + "'" + " class=\"ui_icon_sprite bookmark-star-icon\" name=" + imageName +	"width=\"15\" height=\"15\"/>&nbsp;" +
				"<a id='starTXT_"+asset.getAssetId()+ "' href='#' onClick=updateUserFavoriteAsset('"	+ asset.getAssetId()+ "')>"	+
				LB_UNBOOKMARKED_DEVICE);
				
				}
			
			xml.append("</a></li></ul></td>");
			if(!"false".equalsIgnoreCase(showViewMore)){
				xml.append("<td><table class=\"wFull\">");	
				xml.append("<tr><td><ul><li>&nbsp;</li><li>" + LB_BUILDING + replaceNullWithBlankString(asset.getPhysicalLocationAddress()==null?"":asset.getPhysicalLocationAddress().getPhysicalLocation1()));
				xml.append("</li><li>" + LB_FLOOR + replaceNullWithBlankString(asset.getPhysicalLocationAddress()==null?"":asset.getPhysicalLocationAddress().getPhysicalLocation2()));
				xml.append("</li><li>" + LB_OFFICE + replaceNullWithBlankString(asset.getPhysicalLocationAddress()==null?"":asset.getPhysicalLocationAddress().getPhysicalLocation3()) + "</li></ul></td></tr>");
				
				xml.append("<tr class=\"moreLink\"><td><ul class=\"link\" style=\"margin-top:0px!important;\"><li>" +
						"<a href=\"###\"; onClick=\"viewDeviceDetail('"
							+ asset.getAssetId()
							+ "','"
							+ asset.getSerialNumber()
							+ "','"+deviceBookmarked+ "')\";>"+LB_VIEW_MORE+"</a></li></ul></tr></td>");
				xml.append("</table></td>");//For MPS Phase 2.1 
				xml.append("<td class=\"actionLinks\"><ul class=\"link\">");
				if (asset.getHostName() != null
						&& !asset.getHostName().trim().equals("")
						|| asset.getIpAddress() != null
						&& !asset.getIpAddress().trim().equals("")) {
				
					xml.append("<li><a href=\"javascript:void(0)\" onClick=\"gotoControlPanel('" 
					+ asset.getControlPanelURL()+ "')\">"+LB_CONTROL_PANEL+"</a></li>");			
				}		
				
				xml.append("<li><a href=\""+ asset.getSupportUrl()
							+ "\" target=\"_blank\">" +LB_SUPPORT_DOWNLOADS+"</a></li>");
			}
			
			if("false".equalsIgnoreCase(showViewMore)){
				xml.append("<td class=\"actionLinks\"><ul class=\"link\">");
			}
			/********Below section is for role based access - create new request link ****/
			if((createSuppliesReqFlag!=null && createSuppliesReqFlag.equalsIgnoreCase("True")) 
			|| (createServiceReqFlag!=null && createServiceReqFlag.equalsIgnoreCase("True")) 
			|| (createAcctMgmtReqFlag !=null && createAcctMgmtReqFlag.equalsIgnoreCase("True")))
			{	
				logger.debug("Account id is coming as "+asset.getAccount().getAccountId());
				xml.append("<li><a href=\"###\" onClick=\"requestService('"+ asset.getAssetId()+ "','" + createServiceRequestFlag + "','" + asset.isConsumableAssetFlag() +
						 "','" + asset.getSerialNumber() +
						 "','" + asset.getAssetTag() +
						 "','" + asset.getIpAddress() +
						 "','" + asset.getModelNumber() +
						 "','" + asset.getDeviceTag() +
						 "','" + asset.getAccount().getAccountId() +
						 "','" + StringEscapeUtils.escapeJavaScript(asset.getAccount().getAccountName()) +
						 "','" + StringEscapeUtils.escapeJavaScript(asset.getAccount().getAgreementName()) +
						"');\">" +
				LB_CREATESERVICE_REQ + "</a></li>");
			}
			// if(!"false".equalsIgnoreCase(showViewMore) && !hidePgCnts){ // commented as part of INC0092674/CRM-JFoster201503271546
			if(!"false".equalsIgnoreCase(showViewMore)){
				logger.debug("show pg cnts");
				xml.append("<li><a href=\"###\" id=\"meterReadsCountLink\" onClick=\"return openPopUp('"+
				asset.getAssetId()+ "','" + asset.getSerialNumber()+"','" + asset.getIpAddress()+"','" + asset.getProductLine() +"','"+ asset.getAssetTag()+
								"');\">"+updateMeterReadsMsg+"</a></li></ul></td>");
			}
			if("false".equalsIgnoreCase(showViewMore)){
				xml.append("</ul></td>");
			}
			xml.append("</tr></table>");			
			xml.append("]]></cell>\n");			
			/**********End of Sub Row Data***********/
			
			xml.append("  <cell><![CDATA[<a href=\"###\" onClick=\"viewDeviceDetail('"+XMLEncodeUtil.escapeXMLForDMCM(asset.getAssetId())+"','"
			+ XMLEncodeUtil.escapeXMLForDMCM(asset.getSerialNumber())+"','"+deviceBookmarked+ "')\">" + 
			XMLEncodeUtil.escapeXMLForDMCM(asset.getSerialNumber()) + "</a>]]></cell>\n");
			xml.append("  <cell><![CDATA[" + XMLEncodeUtil.escapeXMLForDMCM(asset.getDeviceTag()) + "]]></cell>\n");
			xml.append("  <cell><![CDATA[" + XMLEncodeUtil.escapeXMLForDMCM(asset.getDescriptionLocalLang()) + "]]></cell>\n");
			
			xml.append("  <cell><![CDATA[" + XMLEncodeUtil.escapeXMLForDMCM(asset.getAssetTag()) + "]]></cell>\n");			
			xml.append("  <cell><![CDATA[" + XMLEncodeUtil.escapeXMLForDMCM(asset.getHostName()) + "]]></cell>\n");			
			xml.append("  <cell><![CDATA[" + XMLEncodeUtil.escapeXMLForDMCM(asset.getIpAddress()) + "]]></cell>\n");
			//Added for MPS Phase-2
			xml.append("  <cell><![CDATA[" + XMLEncodeUtil.escapeXMLForDMCM(asset.getAccount().getAccountName()) + "]]></cell>\n");			
			xml.append("  <cell><![CDATA[" + XMLEncodeUtil.escapeXMLForDMCM(asset.getAssetType()) + "]]></cell>\n");			
			xml.append("  <cell><![CDATA[" + (asset.getDevicePhase() == null ? "" : XMLEncodeUtil.escapeXMLForDMCM(asset.getDevicePhase())) + "]]></cell>\n");
			xml.append("  <cell><![CDATA[" + (asset.getAssetCostCenter() == null ? "" : XMLEncodeUtil.escapeXMLForDMCM(asset.getAssetCostCenter())) + "]]></cell>\n");
			//xml.append("  <cell><![CDATA[" + (XMLEncodeUtil.escapeXMLForDMCM(asset.getAssetCostCenter()) + "]]></cell>\n");
			xml.append("  <cell><![CDATA[" + XMLEncodeUtil.escapeXMLForDMCM(asset.getInstallAddress().getAddressName()) + "]]></cell>\n");				
			xml.append("  <cell><![CDATA[" + XMLEncodeUtil.escapeXMLForDMCM(asset.getInstallAddress().getAddressLine1())+ "]]></cell>\n");
			//House Number Added for CI BRD 13-10-08 
			xml.append("  <cell><![CDATA[" + XMLEncodeUtil.escapeXMLForDMCM(asset.getInstallAddress().getOfficeNumber())+ "]]></cell>\n");			
			xml.append("  <cell><![CDATA[" + XMLEncodeUtil.escapeXMLForDMCM(asset.getInstallAddress().getCity()) + "]]></cell>\n");			
			xml.append("  <cell><![CDATA[" + XMLEncodeUtil.escapeXMLForDMCM(asset.getInstallAddress().getState())+  "]]></cell>\n");			
			xml.append("  <cell><![CDATA[" + XMLEncodeUtil.escapeXMLForDMCM(asset.getInstallAddress().getProvince())+ "]]></cell>\n");			
			//County and District Added for CI BRD 13-10-8
			xml.append("  <cell><![CDATA[" + XMLEncodeUtil.escapeXMLForDMCM(asset.getInstallAddress().getCounty())+ "]]></cell>\n");
			xml.append("  <cell><![CDATA[" + XMLEncodeUtil.escapeXMLForDMCM(asset.getInstallAddress().getDistrict())+ "]]></cell>\n");			
			xml.append("  <cell><![CDATA[" + XMLEncodeUtil.escapeXMLForDMCM(asset.getInstallAddress().getCountry()) + "]]></cell>\n");			
			xml.append("  <cell><![CDATA[" + XMLEncodeUtil.escapeXMLForDMCM(asset.getInstallAddress().getPostalCode()) + "]]></cell>\n");			
			xml.append("  <cell><![CDATA[" + XMLEncodeUtil.escapeXMLForDMCM(asset.getAssetContact().getFirstName()) + "]]></cell>\n");
			xml.append("  <cell><![CDATA[" + XMLEncodeUtil.escapeXMLForDMCM(asset.getAssetContact().getLastName()) + "]]></cell>\n");
			
			xml.append("  <cell><![CDATA[" + XMLEncodeUtil.escapeXMLForDMCM(asset.getAssetContact().getEmailAddress()) + "]]></cell>\n");
			
			xml.append("  <cell><![CDATA[" + XMLEncodeUtil.escapeXMLForDMCM(asset.getAssetContact().getWorkPhone()) + "]]></cell>\n");			
			if(asset.getInstallAddress().getLevelOfDetails()=="Mix-See-Floor"){
				xml.append("  <cell><![CDATA[" + XMLEncodeUtil.escapeXMLForDMCM("Mix") + "]]></cell>\n");	
			}
			else{
				String value=""; //asset.getInstallAddress().getLevelOfDetails();
				if(null != asset.getInstallAddress().getLevelOfDetails()){
					value = asset.getInstallAddress().getLevelOfDetails();
				}
				if(value.contains("Level")){
					xml.append("  <cell><![CDATA[" + XMLEncodeUtil.escapeXMLForDMCM(value.replace("Level",""))+ "]]></cell>\n");		
				}
				else{
					xml.append("  <cell><![CDATA[" + XMLEncodeUtil.escapeXMLForDMCM(value)+ "]]></cell>\n");
				}	
			}
			if(asset.isLbsAddressFlag()){
				xml.append("  <cell><![CDATA[" + XMLEncodeUtil.escapeXMLForDMCM("Yes") + "]]></cell>\n");
			}else{
				xml.append("  <cell><![CDATA[" + XMLEncodeUtil.escapeXMLForDMCM("No") + "]]></cell>\n");
			}
			logger.debug("Account name is coming as "+asset.getAccount().getAccountName());
			xml.append(" </row>\n");
			i++;
		}
		xml.append("</rows>\n");
		return xml.toString();
	}
	//Translate the CatalogPart list to Data View XML still not used
	/**
	 * @param catalogPartList 
	 * @param totalCount 
	 * @param posStart 
	 * @param session 
	 * @param priceResult 
	 * @param paymentType 
	 * @return String 
	 * @throws ParserConfigurationException 
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws FileNotFoundException 
	 */
	public  String catalogToDataViewXml(List<OrderPart> catalogPartList,int totalCount, int posStart, PortletSession session, PriceResult priceResult, String paymentType,boolean isTonerPurchase) throws FileNotFoundException, SAXException, IOException, ParserConfigurationException
	{

		logger.debug ("Enter catalogToDataViewXml"+catalogPartList);
		
		
		boolean partAddedTOCart = false;
		String quantity = "";
			if(catalogPartList==null||catalogPartList.isEmpty())
			{
				return "<data></data>";
			}
			
			List<OrderPart> catalogOrderListToSession = (ArrayList<OrderPart>) session.getAttribute("catalogOrderListToSession");
			Map<String,String> accDetails =(Map<String,String>)session.getAttribute(ChangeMgmtConstant.ACNTCURRDETAILS,PortletSession.APPLICATION_SCOPE);
			String splitterFlag = "false";
			Boolean finalShowPriceFlag = false;
			Map<String,String> catalogPriceMap= new HashMap<String,String>();
			splitterFlag = accDetails.get("splitterFlag");
			if(splitterFlag.equalsIgnoreCase("true")){				
				Map<String,Boolean> catalogFinalFlags=(Map<String,Boolean>)session.getAttribute(ChangeMgmtConstant.CATFINALFLAGS,PortletSession.APPLICATION_SCOPE);
				finalShowPriceFlag = catalogFinalFlags.get("finalShowPriceFlag");				
				if(session.getAttribute(ChangeMgmtConstant.CATPRICEMAP,PortletSession.APPLICATION_SCOPE)!=null){
					catalogPriceMap = (Map<String,String>)session.getAttribute(ChangeMgmtConstant.CATPRICEMAP,PortletSession.APPLICATION_SCOPE);
				}
			}
			
			StringBuffer xml = new StringBuffer("<?xml version=\"1.0\" ?>\n");			
			xml.append("<rows total_count=\""+totalCount+"\" pos=\""+posStart+"\">\n");
			logger.debug ("totalCount==>"+totalCount+"posStart===>"+posStart);
			int count=0;
			for(OrderPart orderPart : catalogPartList)
			{
				logger.debug ("orderPart.getPartNumber()==>"+orderPart.getPartNumber()+" is ---> "+orderPart.getMpsQuantity());
				
				String price = "";
				String currency = "";
				String catalogId = "";
				String partNumber = "";
				String description = "";
				String partType = "";
				String yield = "";
				String consumableType = "";
				String supplyId = "";
				String productId = "";
				String model = "";
				String contractLineItemId = "";
				String salesOrg = "";
				String providerOrderNumber="";
				/*Commenting ImplicitFlag codes as part of MPS 2.1*/
				/*String implicitPartDesc = PropertiesMessageUtil.getPropertyMessage(BUNDLENAME_BUTTON_MSG, "requestInfo.label.implicitPartDesc", locale);
				boolean implicitFlag = orderPart.isImplicitFlag();*/
				/*End Commenting*/
				quantity = "";
				String maxQuantity = "";
				String servicePartQuantity = "";
				String suppliesPartQuantity = "";
				
				partAddedTOCart = false;
				//logger.debug("orderPart.getCatalogId() "+orderPart.getCatalogId());				
				
				if(orderPart.getCatalogId()!=null){
					//logger.debug("catalogid is not null");
					catalogId = orderPart.getCatalogId().replaceAll(" ", "%20");
				}
				//logger.debug("orderPart.getPartNumber() "+orderPart.getPartNumber());
				if(orderPart.getPartNumber()!=null){
					//logger.debug("part number is not null");
					//partNumber = XMLEncodeUtil.escapeXMLForDMCM(orderPart.getPartNumber());
					partNumber = orderPart.getPartNumber().replaceAll("'", "");
					partNumber = partNumber.replaceAll(" ", "%20");
				}
				//logger.debug("orderPart.getDescription() "+orderPart.getDescription());
				if(orderPart.getDescription()!=null){
					//logger.debug("part desc is not null");
					description = orderPart.getDescription().replaceAll("'", "");
					description = description.replaceAll(" ", "%20");
				}
				//logger.debug("orderPart.getPartType() "+orderPart.getPartType());
				if(orderPart.getPartType()!=null){
					//logger.debug("part type is not null");
					partType = orderPart.getPartType().replaceAll(" ", "%20");
				}
				//logger.debug("orderPart.getYield() "+orderPart.getYield());
				if(orderPart.getYield()!=null){
					//logger.debug("yield is not null");
					yield = orderPart.getYield().replaceAll(" ", "%20");
				}
				//logger.debug("orderPart.getConsumableType() "+orderPart.getConsumableType());
				if(orderPart.getConsumableType()!=null){
					//logger.debug("consumable type is not null");
					consumableType = orderPart.getConsumableType().replaceAll(" ", "%20");
				}
				if(orderPart.getSupplyId()!=null){
					//logger.debug("SupplyId is not null "+orderPart.getSupplyId());
					supplyId = orderPart.getSupplyId().replaceAll(" ", "%20");
				}
				if(orderPart.getProductId()!=null){
					//logger.debug("ProductId is not null "+orderPart.getProductId());
					productId = orderPart.getProductId().replaceAll(" ", "%20");
				}
				if(orderPart.getModel()!=null){
					//logger.debug("Model is not null "+orderPart.getModel());
					model = orderPart.getModel().replaceAll(" ", "%20");
				}
				if(orderPart.getContractLineItemId()!=null){
					//logger.debug("Model is not null "+orderPart.getModel());
					contractLineItemId = orderPart.getContractLineItemId();
				}
				if(orderPart.getSalesOrg()!=null){
					//logger.debug("Model is not null "+orderPart.getModel());
					salesOrg = orderPart.getSalesOrg();
				}
				if(orderPart.getProviderContractNo()!=null){
					//logger.debug("part number is not null");
					//partNumber = XMLEncodeUtil.escapeXMLForDMCM(orderPart.getPartNumber());
					providerOrderNumber = orderPart.getProviderContractNo();
				}
				if(orderPart.getMpsQuantity() != null && orderPart.getMpsQuantity() != ""){
				    quantity = orderPart.getMpsQuantity().replaceAll(" ", "%20");
				    logger.debug("Siebel field is not blank/null = " + quantity);
				    maxQuantity = orderPart.getMpsQuantity().trim();
				}
				/*else 
				{
					quantity = maxQuantity;
					maxQuantity = "5";
					logger.debug("Siebel field is blank = " + quantity);
				}*/
				// mps quantity getting from session
				if(null != session.getAttribute("servicePartQuantity",PortletSession.APPLICATION_SCOPE)){
					servicePartQuantity = session.getAttribute("servicePartQuantity",PortletSession.APPLICATION_SCOPE).toString();
					logger.debug("servicePartQuantity from session is ==================== "+servicePartQuantity);
				}
				if(null != session.getAttribute("suppliesPartQuantity",PortletSession.APPLICATION_SCOPE)){
					suppliesPartQuantity = session.getAttribute("suppliesPartQuantity",PortletSession.APPLICATION_SCOPE).toString();
					logger.debug("suppliesPartQuantity from session is ==================== "+suppliesPartQuantity);
				}
				
				Map<String, String> quantityServicesMap = (Map<String,String>)session.getAttribute("quantityServicesMap",PortletSession.APPLICATION_SCOPE);
				Map<String, String> quantitySuppliesMap =(Map<String,String>)session.getAttribute("quantitySuppliesMap",PortletSession.APPLICATION_SCOPE);
				
				String agreementId = (String) session.getAttribute("agreementId");
				if(null != quantityServicesMap.get(agreementId) && !"".equals(quantityServicesMap.get(agreementId))){
					servicePartQuantity = quantityServicesMap.get(agreementId);
				}
				if(null != quantitySuppliesMap.get(agreementId) && !"".equals(quantitySuppliesMap.get(agreementId))){
					suppliesPartQuantity = quantitySuppliesMap.get(agreementId);
				}
				logger.debug("final servicePartQuantity from session is ==================== "+servicePartQuantity);
				logger.debug("final suppliesPartQuantity from session is ==================== "+suppliesPartQuantity);
				logger.debug("aggrement id = "+agreementId);
				
				logger.debug("quantity is"+quantity);
				/*Added for MPS 2.1 Wave 1 Consumables changes*/
				if(splitterFlag.equalsIgnoreCase("true")){
					if(priceResult!=null){
						for(Price priceOutputLine:priceResult.getPriceOutputList()){
							if(isTonerPurchase){
								if(priceOutputLine.getPartNumber().equalsIgnoreCase(partNumber)){
									if(priceOutputLine.getPrice()!=null && priceOutputLine.getPrice()!=""){
										price = priceOutputLine.getPrice();
										currency = priceOutputLine.getCurrency();
										break;
									}
								}
							}else{
								if(priceOutputLine.getContractLineItemId().equalsIgnoreCase(contractLineItemId)){
									if(priceOutputLine.getPrice()!=null && priceOutputLine.getPrice()!=""){
										price = priceOutputLine.getPrice();
										currency = priceOutputLine.getCurrency();
										break;
									}
								}
							}
						}
					}
					catalogPriceMap.put(catalogId, price);
				}
				/*End Add*/
				
				if(catalogOrderListToSession!=null){
					for(int i=0;i<catalogOrderListToSession.size();i++){
						if(catalogId.equalsIgnoreCase(catalogOrderListToSession.get(i).getCatalogId())){
							//Update is required. So need to add existing quantity with new one
							logger.debug("Update is required for this catalog id. so it will show update order for part number "+partNumber);
							if(catalogOrderListToSession.get(i).getUnitPrice()!=null && catalogOrderListToSession.get(i).getUnitPrice()!=""){
								price = catalogOrderListToSession.get(i).getUnitPrice();
							}
							if(catalogOrderListToSession.get(i).getUnitPrice()!=null && catalogOrderListToSession.get(i).getUnitPrice()!=""){
								currency = catalogOrderListToSession.get(i).getCurrency();
							}
							quantity = catalogOrderListToSession.get(i).getPartQuantity();
							logger.debug("Quantity value to be there is "+quantity);
							partAddedTOCart = true;
							break;
						}
					}
					
				}
				logger.debug("quantity last me "+quantity);

				xml.append("<row id=\""+ catalogId +"\">\n");
				
				
				xml.append("<cell><![CDATA[");
				/*Remove code for implicit flag for MPS 2.1 Wave 1 Consumable changes*/
				/*if(implicitFlag){
					//add image for implicit part
					xml.append("<div class=\"pModel w100\"><img src=\"/LexmarkServicesPortal/images/printer_implicit_part.png\" width=\"100\" height=\"100\"/></div>");
				}*/
					
				String partImage =null;
				//String partImage = URLImageUtil.getPartImage(partNumber);getPartImageFromLocal
				try{
				partImage = URLImageUtil.getPartImageFromLocal(partNumber);
				logger.debug("partImage is "+partImage);
				}catch(Exception e){
				logger.debug("Error retrieving part image");
				}
				if(partImage!=IMAGE_NOT_FOUND){
					xml.append("<div class=\"pModel w100\"><img src='"+partImage+"' width=\"100\" height=\"100\"/></div>");
				}else{
					xml.append("<div class=\"pModel w100\"><img src=\"/LexmarkServicesPortal/images/part_na_color.png\" width=\"100\" height=\"100\"/></div>");
				}				
				xml.append("]]></cell>\n");
				
	
				xml.append("<cell><![CDATA[");
				/*Remove code for implicit flag for MPS 2.1 Wave 1 Consumable changes*/
				xml.append("<div><ul class=\"form\"><li><label>"+PropertiesMessageUtil.getPropertyMessage(BUNDLENAME_BUTTON_MSG, "requestInfo.label.partNumber", locale)+"</label>"+
						"<span>"+(orderPart.getPartNumber()!=null?orderPart.getPartNumber():"")+"</span></li><li><label>"+
						PropertiesMessageUtil.getPropertyMessage(BUNDLENAME_BUTTON_MSG, "requestInfo.label.description", locale)
						+"</label><span class=\"multiLine\">"+
						(orderPart.getDescription()!=null?orderPart.getDescription():"")+"</span></li></ul></div>");				
				xml.append("]]></cell>\n");
				xml.append("<cell><![CDATA[");
				xml.append("<div><ul class=\"form\"><li><label>"+PropertiesMessageUtil.getPropertyMessage(BUNDLENAME_BUTTON_MSG, "requestInfo.label.partType", locale)+"</label><span>"+
						(orderPart.getPartType()!=null?orderPart.getPartType():"")+
						"</span></li><li><label>"+PropertiesMessageUtil.getPropertyMessage(BUNDLENAME_BUTTON_MSG, "requestInfo.label.yield", locale)+"</label><span>"+(orderPart.getYield()!=null?orderPart.getYield():"")+"</span></li>" +
								"<li><label>"+PropertiesMessageUtil.getPropertyMessage(BUNDLENAME_BUTTON_MSG, "requestInfo.label.model", locale)+"</label><span>"+(orderPart.getModel()!=null?orderPart.getModel():"")+"</span></li>" +
								"</ul></div>");
				xml.append("]]></cell>\n");
				if(partAddedTOCart){
					//need update order button here
					xml.append("<cell><![CDATA[");
					if(splitterFlag.equalsIgnoreCase("true") && finalShowPriceFlag){
						if("".equals(price) || "0".equals(price)||"0.0".equals(price)||"0.00".equals(price)){//changed on june 11,2013 for adding bracket
							//Msg displayed if price is not available for the part and no text field to enter qty to be shown
							xml.append("<div id=\"priceDiv"+catalogId+"\" class=\"errorText\">"+PropertiesMessageUtil.getPropertyMessage(BUNDLENAME_BUTTON_MSG, "requestInfo.error.priceUnavailable", locale)+"</div><div class=\"lineClear\"/>"); //Changed currency font on 11June
							
							if(isTonerPurchase){
								logger.debug("part number is = "+orderPart.getPartNumber()+" and part type is ==== "+orderPart.getConsumableType());
								if("Consumables Supplies Request".equalsIgnoreCase(orderPart.getConsumableType())){
									maxQuantity = suppliesPartQuantity; 
								}
								else{
									maxQuantity = servicePartQuantity; 
								}
								xml.append("<div id=\"partQuantityDiv"+catalogId+"\"><input type=\"text\" class=\"w50\" onclick=\"focusOnInput('partQuantity"+catalogId+"');\" onkeyup=\"checkParts(this.id,"+maxQuantity+",'"+catalogId+"',"+"'"+count+"');\" onchange=\"removeErrorMessage('"+catalogId+"');\"  id=\"partQuantity"+catalogId+"\" value=\"" + quantity + "\"/></td>");
								
								xml.append("<span class=\"spaceClear\"/><input class=\"button\" value=\""+PropertiesMessageUtil.getPropertyMessage(BUNDLENAME_BUTTON_MSG, "requestInfo.button.updateOrder", locale)+"\"  type=\"button\"  " +
										"onclick=\"addToCart('"+catalogId+"', '"+partNumber+
										"', '"+description+"', '"+partType+
										"', '"+yield+"', '"+consumableType+"', '"+supplyId+"', '"+productId+"', '"+model+"', 'partQuantity"+catalogId+"','"+contractLineItemId+"','"+currency+"', '"+salesOrg+"', '"+providerOrderNumber+"','"+quantity+"',this);\"/></div>");
								xml.append("<div style='display: none; color: red;' id='MsgassetPartList["+catalogId+"].orderQuantity'><B>"+PropertiesMessageUtil.
										getPropertyMessage(BUNDLENAME_BUTTON_MSG, "maxOrderQtyValidation", locale)+" "+maxQuantity+"</B></div>");
								
							}
							
						}else{
							if(price.indexOf(".")>0){
								
								String priceSplit[] = price.split("\\.");
								
								logger.debug("price1 -- > "+priceSplit[0]);
								logger.debug("price2 -- > "+priceSplit[1]);
								xml.append("<div id=\'priceDiv"+catalogId+"\'><span class=\'f200\'>"+priceSplit[0]+"</span><span class=\'minimum\'>.</span><span  class=\'f150\'>"+priceSplit[1]+"</span>"+"&nbsp;"+"<span class=\'f200\'>("+currency+")<span>"+"</div><div class=\'lineClear\'/>");
								
							}else{
								xml.append("<div id=\'priceDiv"+catalogId+"\'><span class=\'f200\'>"+price+"</span><span class=\'f200\'>("+currency+")</span></div><div class=\'lineClear\'/>");
							}
							logger.debug("part number is = "+orderPart.getPartNumber()+" and part type is ==== "+orderPart.getConsumableType());
							if("Consumables Supplies Request".equalsIgnoreCase(orderPart.getConsumableType())){
								maxQuantity = suppliesPartQuantity; 
							}
							else{
								maxQuantity = servicePartQuantity; 
							}
							xml.append("<div id=\"partQuantityDiv"+catalogId+"\"><input type=\"text\" class=\"w50\" onclick=\"focusOnInput('partQuantity"+catalogId+"');\" onkeyup=\"checkParts(this.id,"+maxQuantity+",'"+catalogId+"',"+"'"+count+"');\" onchange=\"removeErrorMessage('"+catalogId+"');\"  id=\"partQuantity"+catalogId+"\" value=\"" + quantity + "\"/></td>");
							xml.append("<span class=\"spaceClear\"/><input class=\"button\" value=\""+PropertiesMessageUtil.getPropertyMessage(BUNDLENAME_BUTTON_MSG, "requestInfo.button.updateOrder", locale)+"\"  type=\"button\"  " +
									"onclick=\"addToCart('"+catalogId+"', '"+partNumber+
									"', '"+description+"', '"+partType+
									"', '"+yield+"', '"+consumableType+"', '"+supplyId+"', '"+productId+"', '"+model+"', 'partQuantity"+catalogId+"','"+contractLineItemId+"','"+currency+"', '"+salesOrg+"','"+providerOrderNumber+"', '"+maxQuantity+"',this);\"/></div>");
							xml.append("<div style='display: none; color: red;' id='MsgassetPartList["+catalogId+"].orderQuantity'><B>"+PropertiesMessageUtil.
									getPropertyMessage(BUNDLENAME_BUTTON_MSG, "maxOrderQtyValidation", locale)+" "+maxQuantity+"</B></div>");
						}
					}else{	
						logger.debug("part number is = "+orderPart.getPartNumber()+" and part type is ==== "+orderPart.getConsumableType());
						if("Consumables Supplies Request".equalsIgnoreCase(orderPart.getConsumableType())){
							maxQuantity = suppliesPartQuantity; 
						}
						else{
							maxQuantity = servicePartQuantity; 
						}
						xml.append("<div id=\"partQuantityDiv"+catalogId+"\"><input type=\"text\" class=\"w50\" onclick=\"focusOnInput('partQuantity"+catalogId+"');\" onkeyup=\"checkParts(this.id,"+maxQuantity+",'"+catalogId+"',"+"'"+count+"');\" onchange=\"removeErrorMessage('"+catalogId+"');\" id=\"partQuantity"+catalogId+"\" value=\"" + quantity + "\"/></td>");						
						xml.append("<span class=\"spaceClear\"/><input class=\"button\" value=\""+PropertiesMessageUtil.getPropertyMessage(BUNDLENAME_BUTTON_MSG, "requestInfo.button.updateOrder", locale)+"\"  type=\"button\"  " +
								"onclick=\"addToCart('"+catalogId+"', '"+partNumber+
								"', '"+description+"', '"+partType+
								"', '"+yield+"', '"+consumableType+"', '"+supplyId+"', '"+productId+"', '"+model+"', 'partQuantity"+catalogId+"','"+contractLineItemId+"','"+currency+"', '"+salesOrg+"','"+providerOrderNumber+"', '"+maxQuantity+"',this);\"/></div>");
						xml.append("<div style='display: none; color: red;' id='MsgassetPartList["+catalogId+"].orderQuantity'><B>"+PropertiesMessageUtil.
										getPropertyMessage(BUNDLENAME_BUTTON_MSG, "maxOrderQtyValidation", locale)+" "+maxQuantity+"</B></div>");
					}
					xml.append("]]></cell>\n");
				}else{
					xml.append("<cell><![CDATA[");
					if(splitterFlag.equalsIgnoreCase("true") && finalShowPriceFlag){
						if("".equals(price) || "0".equals(price)|| "0.0".equals(price)||"0.00".equals(price)){//changed on june 11,2013 for adding bracket
							
							//Msg displayed if price is not avaailable for the part and no text field to enter qty to be shown
							xml.append("<div id=\"priceDiv"+catalogId+"\" class=\"errorText\">"+PropertiesMessageUtil.getPropertyMessage(BUNDLENAME_BUTTON_MSG, "requestInfo.error.priceUnavailable", locale)+"</div><div class=\"lineClear\"/>"); //Changed currency font on 11June
							if(isTonerPurchase){
								logger.debug("part number is = "+orderPart.getPartNumber()+" and part type is ==== "+orderPart.getConsumableType());
								if("Consumables Supplies Request".equalsIgnoreCase(orderPart.getConsumableType())){
									maxQuantity = suppliesPartQuantity; 
								}
								else{
									maxQuantity = servicePartQuantity; 
								}
								xml.append("<div id=\"partQuantityDiv"+catalogId+"\"><input type=\"text\" class=\"w50\" onclick=\"focusOnInput('partQuantity"+catalogId+"');\" onkeyup=\"checkParts(this.id,"+maxQuantity+",'"+catalogId+"',"+"'"+count+"');\" onchange=\"removeErrorMessage('"+catalogId+"');\"  id=\"partQuantity"+catalogId+"\"/></td>");								
								xml.append("<span class=\"spaceClear\"/><input class=\"button\" value=\""+PropertiesMessageUtil.getPropertyMessage(BUNDLENAME_BUTTON_MSG, "requestInfo.button.addToCart", locale)+"\"  type=\"button\"  " +
										"onclick=\"addToCart('"+catalogId+"', '"+partNumber+
										"', '"+description+"', '"+partType+
										"', '"+yield+"', '"+consumableType+"', '"+supplyId+"', '"+productId+"', '"+model+"', 'partQuantity"+catalogId+"','"+contractLineItemId+"','"+currency+"', '"+salesOrg+"', '"+providerOrderNumber+"','"+maxQuantity+"',this);\"/></div>");
								xml.append("<div style='display: none; color: red;' id='MsgassetPartList["+catalogId+"].orderQuantity'><B>"+PropertiesMessageUtil.
										getPropertyMessage(BUNDLENAME_BUTTON_MSG, "maxOrderQtyValidation", locale)+" "+maxQuantity+"</B></div>");
							}
							
						}else{
							/*Changes for defect 7954 MPS 2.1
							 * Changed the class of Price div to f200
							 * */
							if(price.indexOf(".")>0){
								
								String priceSplit[] = price.split("\\.");
								
								logger.debug("price1 -- > "+priceSplit[0]);
								logger.debug("price2 -- > "+priceSplit[1]);
								xml.append("<div id=\'priceDiv"+catalogId+"\'><span class=\'f200\'>"+priceSplit[0]+"</span><span class=\'minimum\'>.</span><span  class=\'f150\'>"+priceSplit[1]+"</span>"+"&nbsp;"+"<span class=\'f200\'>("+currency+")<span>"+"</div><div class=\'lineClear\'/>");
								
							}else{
								xml.append("<div id=\'priceDiv"+catalogId+"\'><span class=\'f200\'>"+price+"</span><span class=\'f200\'>("+currency+")</span></div><div class=\'lineClear\'/>");
							} 
							//Changed currency font on 11June
							//Ends Changes for Defect 7954
							logger.debug("part number is = "+orderPart.getPartNumber()+" and part type is ==== "+orderPart.getConsumableType());
							if("Consumables Supplies Request".equalsIgnoreCase(orderPart.getConsumableType())){
								maxQuantity = suppliesPartQuantity; 
							}
							else{
								maxQuantity = servicePartQuantity; 
							}
							xml.append("<div id=\"partQuantityDiv"+catalogId+"\"><input type=\"text\" class=\"w50\" onclick=\"focusOnInput('partQuantity"+catalogId+"');\" onkeyup=\"checkParts(this.id,"+maxQuantity+",'"+catalogId+"',"+"'"+count+"');\" onchange=\"removeErrorMessage('"+catalogId+"');\"  id=\"partQuantity"+catalogId+"\"/></td>");							
							xml.append("<span class=\"spaceClear\"/><input class=\"button\" value=\""+PropertiesMessageUtil.getPropertyMessage(BUNDLENAME_BUTTON_MSG, "requestInfo.button.addToCart", locale)+"\"  type=\"button\"  " +
									"onclick=\"addToCart('"+catalogId+"', '"+partNumber+
									"', '"+description+"', '"+partType+
									"', '"+yield+"', '"+consumableType+"', '"+supplyId+"', '"+productId+"', '"+model+"', 'partQuantity"+catalogId+"','"+contractLineItemId+"','"+currency+"','"+salesOrg+"', '"+providerOrderNumber+"','"+maxQuantity+"', this);\"/></div>");
							xml.append("<div style='display: none; color: red;' id='MsgassetPartList["+catalogId+"].orderQuantity'><B>"+PropertiesMessageUtil.
									getPropertyMessage(BUNDLENAME_BUTTON_MSG, "maxOrderQtyValidation", locale)+" "+maxQuantity+"</B></div>");
						}						
					}else{
						logger.debug("part number is = "+orderPart.getPartNumber()+" and part type is ==== "+orderPart.getConsumableType());
						if("Consumables Supplies Request".equalsIgnoreCase(orderPart.getConsumableType())){
							maxQuantity = suppliesPartQuantity; 
						}
						else{
							maxQuantity = servicePartQuantity; 
						}
						xml.append("<div id=\"partQuantityDiv"+catalogId+"\"><input type=\"text\" class=\"w50\" onclick=\"focusOnInput('partQuantity"+catalogId+"');\" onkeyup=\"checkParts(this.id,"+maxQuantity+",'"+catalogId+"',"+"'"+count+"');\" onchange=\"removeErrorMessage('"+catalogId+"');\"  id=\"partQuantity"+catalogId+"\"/></td>");						
						xml.append("<span class=\"spaceClear\"/><input class=\"button\" value=\""+PropertiesMessageUtil.getPropertyMessage(BUNDLENAME_BUTTON_MSG, "requestInfo.button.addToCart", locale)+"\"  type=\"button\"  " +
								"onclick=\"addToCart('"+catalogId+"', '"+partNumber+
								"', '"+description+"', '"+partType+
								"', '"+yield+"', '"+consumableType+"', '"+supplyId+"', '"+productId+"', '"+model+"', 'partQuantity"+catalogId+"','"+contractLineItemId+"','"+currency+"','"+salesOrg+"', '"+providerOrderNumber+"', '"+maxQuantity+"',this);\"/></div>");												}
					    xml.append("<div style='display: none; color: red;' id='MsgassetPartList["+catalogId+"].orderQuantity'><B>"+PropertiesMessageUtil.
							getPropertyMessage(BUNDLENAME_BUTTON_MSG, "maxOrderQtyValidation", locale)+" "+maxQuantity+"</B></div>");
					xml.append("]]></cell>\n");
				}
				
				xml.append(" </row>\n");
				
				posStart = posStart+1;
			
				count++;		
			}
			xml.append(" </rows>\n");			
			session.setAttribute(ChangeMgmtConstant.CATPRICEMAP, catalogPriceMap ,PortletSession.APPLICATION_SCOPE);
			return xml.toString();
		}
	
	/**
	 * @param hardwareCatalogResult 
	 * @param session 
	 * @param priceResult 
	 * @param paymentType  
	 * @return String 
	 */
	public String bundleViewXml(HardwareCatalogResult hardwareCatalogResult, PortletSession session, PriceResult priceResult, String paymentType)
	{
		logger.debug ("Enter bundleViewXml");
		boolean partAddedTOCart = false;
		String quantity = "";
		boolean nullFlag=false;
		if(hardwareCatalogResult.getHardwareCatalog()==null)
		{	nullFlag=true;
					
		}else{
			if(hardwareCatalogResult.getHardwareCatalog().getBundleList()==null){
				nullFlag=true;
			}else{
				if(hardwareCatalogResult.getHardwareCatalog().getBundleList().isEmpty()){
					nullFlag=true;
				}
			}
		}
		
		if(nullFlag){
			StringBuffer blankXml = new StringBuffer("<?xml version=\'1.0\' ?>\n");
			blankXml.append("<rows>\n");
			blankXml.append("<row>\n");
			blankXml.append("<cell><![CDATA[");				
			blankXml.append("<p>No Records Found</p>");
			blankXml.append("]]></cell>\n");
			blankXml.append(" </row>\n");
			blankXml.append(" </rows>\n");
			return blankXml.toString();			
		}
		//List<OrderPart> catalogOrderListToSession = (ArrayList<OrderPart>) session.getAttribute("hardwareOrderListToSession");
		//Part hardware = hardwareListResult.getHardware();
		StringBuffer xml = new StringBuffer("<?xml version=\'1.0\' ?>\n");			
		xml.append("<rows>\n");
		
		List<Bundle> bundleList = hardwareCatalogResult.getHardwareCatalog().getBundleList();
		List<OrderPart> hardwareOrderListToSession = (ArrayList<OrderPart>) session.getAttribute("hardwareOrderListToSession");
		
				
		Map<String,Boolean> hardwareFinalFlags=(Map<String,Boolean>)session.getAttribute(ChangeMgmtConstant.HWFINALFLAGS,PortletSession.APPLICATION_SCOPE);
		boolean finalShowPriceFlag = hardwareFinalFlags.get("finalShowPriceFlag");
		int index = 0;
		Map<String, List<Part>> bundlePartMap = new HashMap<String, List<Part>>();
		Map<String,String> hardwarePriceMap= new HashMap<String,String>();
		if(session.getAttribute(ChangeMgmtConstant.HWPRICEMAP,PortletSession.APPLICATION_SCOPE)!=null){
			hardwarePriceMap = (Map<String,String>)session.getAttribute(ChangeMgmtConstant.HWPRICEMAP,PortletSession.APPLICATION_SCOPE);
		}
		for(Bundle bundle:bundleList){
			String longDescription = "";
			String localDescription = "";
			String price = "";
			String currency = "";				
			partAddedTOCart = false;	
			String bundleId = "";
			String contractLineItemId = "";
			String salesOrg = "";
			String bundlePartId = "";
			String bundleMaterialID = "";
			String sapLineID = "";
			String deviceType=hardwareCatalogResult.getHardwareCatalog().getDeviceType(); 
			String model=hardwareCatalogResult.getHardwareCatalog().getProductModel();
			logger.debug ("deviceType -->" + deviceType + "--model -->"+model);
			if(bundle.getBundleId()!=null){
				bundleId = bundle.getBundleId();
			}else{
				bundleId = Integer.toString(index);
			}
			if(bundle.getMpsDescription()!=null){
				longDescription = bundle.getMpsDescription();
			}
			if(bundle.getAssetId()!=null){
				localDescription = bundle.getAssetId();				
			}
			if(bundle.getBundleMaterialID()!=null){
				bundleMaterialID = bundle.getBundleMaterialID();
			}
			if(bundle.getContractLineItemId()!=null){
				contractLineItemId = bundle.getContractLineItemId();
			}
			if(bundle.getSapLineID()!=null){
				sapLineID = bundle.getSapLineID();
			}
			if(bundle.getSalesOrg()!=null){
				salesOrg = bundle.getSalesOrg();
			}
			if(bundle.getBundleProductId()!=null){
				bundlePartId = bundle.getBundleProductId();
			}
			
			
			if(priceResult!=null){
				for(Price priceOutputLine:priceResult.getPriceOutputList()){
					if(priceOutputLine.getContractLineItemId().equalsIgnoreCase(contractLineItemId)){
						if(priceOutputLine.getPrice()!=null && priceOutputLine.getPrice()!=""){							
							price = priceOutputLine.getPrice();
							currency = priceOutputLine.getCurrency();
							logger.debug("bundle price is "+price);
							logger.debug("bundle currency is "+currency);
							break;
						}
					}
				}
			}
			
			
			hardwarePriceMap.put(bundleId, price);
			
			if(hardwareOrderListToSession!=null){
				for(int i=0;i<hardwareOrderListToSession.size();i++){
					if(bundleId.equalsIgnoreCase(hardwareOrderListToSession.get(i).getCatalogId())){
						//Update is required. So need to add existing quantity with new one
						logger.debug("Update is required for this catalog id. so it will show update order for bundle with ID "+bundleId);
						quantity = hardwareOrderListToSession.get(i).getPartQuantity();
						logger.debug("Quantity value to be there is "+quantity);
						partAddedTOCart = true;
						break;
					}
				}				
			}
			
			String partNumberArr = "";
			String descriptionArr = "";
			String qtyArr = "";			
			
			if(bundle.getPartList()!=null){
				bundlePartMap.put(bundleId, bundle.getPartList());				
			}
			
			
			xml.append("<row id=\'"+ bundleId +"\'>\n");
			xml.append("<cell><![CDATA[");
			xml.append("<p class=\'noWordBreak\'>"+localDescription+"</p>");
			xml.append("]]></cell>\n");
			xml.append("<cell><![CDATA[" + localDescription+ "]]></cell>\n");
			xml.append("<cell><![CDATA[" + longDescription+ "]]></cell>\n");
			xml.append("<cell><![CDATA[");
			xml.append("<div><input type=\'hidden\' id=\'hardwareTypeBundle\' value=\'bundle\'/>");
			String partNo = PropertiesMessageUtil.getPropertyMessage(LexmarkSPConstants.MESSAGE_BUNDLE_NAME,
					"requestInfo.heading.partNumber",locale);
			String description = PropertiesMessageUtil.getPropertyMessage(LexmarkSPConstants.MESSAGE_BUNDLE_NAME,
					"requestInfo.heading.description", locale);
			String qty = PropertiesMessageUtil.getPropertyMessage(LexmarkSPConstants.MESSAGE_BUNDLE_NAME,
					"requestInfo.heading.Qty", locale);
			if(bundle.getPartList()!=null){
				if(bundle.getPartList().size()>0){
					xml.append("<table class=\'displayGrid rounded shadow wFull\' id=\'partTableBundle\'><thead><tr style=\'font-size:12px!important;\'><th class=\'w100\' style=\'width: 12%\'>"+partNo+"</th><th style=\'width: 76%\'>"+description+"</th><th class=\'w100\' style=\'width: 12%\'>"+qty+"</th></tr></thead><tbody>");
					int i=0;
					for(Part bundlePart:bundle.getPartList()){
						if(i%2==0){
							xml.append("<tr class=\'altRow\'>");
						}else{
							xml.append("<tr>");
						}i++;
						xml.append("<td class=\'w100\'>"+bundlePart.getPartNumber()+"</td><td>"+bundlePart.getDescription()+"</td><td class=\'w100\'>"+bundlePart.getOrderQuantity()+"</td></tr>");
					}
				}
			}
			xml.append("]]></cell>\n");
			xml.append("<cell><![CDATA[");
			if(partAddedTOCart){
				if(finalShowPriceFlag){
					if("".equals(price) || "0".equals(price) || "0.0".equals(price) || "0.00".equals(price)){
						//Msg displayed if price is not available for the part and no text field to enter qty to be shown
						xml.append("<div id=\'priceDiv"+bundleId+"\' class=\'errorText\'>"+PropertiesMessageUtil.getPropertyMessage(BUNDLENAME_BUTTON_MSG, "requestInfo.error.priceUnavailable", locale)+"</div><div class=\'lineClear\'/>"); //Changed currency font on 11June
						if(ChangeMgmtConstant.HW_PAYMENT_TYPE_PAY_LATER.equalsIgnoreCase(paymentType)){
							xml.append("<input class=\'w50\' type=\'text\' name=\'quantity1\' onclick=\'focusOnInput('partQuantity"+bundleId+"');\' id=\'partQuantity"+bundleId+"\' value=\'" + quantity + "\'><span class=\'spaceClear\'/><input class=\'button \' value=\'"+PropertiesMessageUtil.getPropertyMessage(BUNDLENAME_BUTTON_MSG, "requestInfo.button.addToCart", locale)+"\' type=\'button\'  " +				
									"onclick=\'addToCart('"+bundleId+"', '"+bundlePartId+
							"', '"+bundleMaterialID+"','','"+deviceType+"', '"+model+"', 'partQuantity"+bundleId+"','"+sapLineID+"','"+currency+"','bundle','"+partNumberArr+"','"+descriptionArr+"','"+qtyArr+"','"+salesOrg+"','', this);\'/></div>");
						}
						
					}else{
						if(price.indexOf(".")>0){
						
							String priceSplit[] = price.split("\\.");
							
							logger.debug("price1 -- > "+priceSplit[0]);
							logger.debug("price2 -- > "+priceSplit[1]);
							xml.append("<div id=\'priceDiv"+bundleId+"\'><span class=\'f200\'>"+priceSplit[0]+"</span><span class=\'minimum\'>.</span><span  class=\'f150\'>"+priceSplit[1]+"</span>"+"&nbsp;"+"<span class=\'f200\'>("+currency+")<span>"+"</div><div class=\'lineClear\'/>");
							
						}else{
							xml.append("<div id=\'priceDiv"+bundleId+"\'><span class=\'f200\'>"+price+"</span><span class=\'f200\'>("+currency+")</span></div><div class=\'lineClear\'/>");
						}
						
						xml.append("<input class=\'w50\' type=\'text\' name=\'quantity1\' onclick=\'focusOnInput('partQuantity"+bundleId+"');\' id=\'partQuantity"+bundleId+"\' value=\'" + quantity + "\'><span class=\'spaceClear\'/><input class=\'button \' value=\'"+PropertiesMessageUtil.getPropertyMessage(BUNDLENAME_BUTTON_MSG, "requestInfo.button.updateOrder", locale)+"\' type=\'button\'  " +				
								"onclick=\'addToCart('"+bundleId+"', '"+bundlePartId+
						"', '"+bundleMaterialID+"' ,'', '"+deviceType+"', '"+model+"', 'partQuantity"+bundleId+"','"+sapLineID+"','"+currency+"','bundle','"+partNumberArr+"','"+descriptionArr+"','"+qtyArr+"','"+salesOrg+"','', this);\'/></div>");
						
					}
				}else{
					xml.append("<input class=\'w50\' type=\'text\' name=\'quantity1\' onclick=\'focusOnInput('partQuantity"+bundleId+"');\' id=\'partQuantity"+bundleId+"\' value=\'" + quantity + "\'><span class=\'spaceClear\'/><input class=\'button \' value=\'"+PropertiesMessageUtil.getPropertyMessage(BUNDLENAME_BUTTON_MSG, "requestInfo.button.updateOrder", locale)+"\' type=\'button\'  " +				
							"onclick=\'addToCart('"+bundleId+"', '"+bundlePartId+"','"+bundleMaterialID+"', '','"+deviceType+"', '"+model+"', 'partQuantity"+bundleId+"','"+sapLineID+"','"+currency+"','bundle','"+partNumberArr+"','"+descriptionArr+"','"+qtyArr+"','"+salesOrg+"','', this);\'/></div>");
				}
				
			}else{
				if(finalShowPriceFlag){
					if("".equals(price) || "0".equals(price) || "0.0".equals(price) || "0.00".equals(price)){
						//Msg displayed if price is not available for the part and no text field to enter qty to be shown
						xml.append("<div id=\'priceDiv"+bundleId+"\' class=\'errorText\'>"+PropertiesMessageUtil.getPropertyMessage(BUNDLENAME_BUTTON_MSG, "requestInfo.error.priceUnavailable", locale)+"</div><div class=\'lineClear\'/>"); //Changed currency font on 11June
						if(ChangeMgmtConstant.HW_PAYMENT_TYPE_PAY_LATER.equalsIgnoreCase(paymentType)){
							xml.append("<input class=\'w50\' type=\'text\' name=\'quantity1\' onclick=\'focusOnInput('partQuantity"+bundleId+"');\' id=\'partQuantity"+bundleId+"\' ><span class=\'spaceClear\'/><input class=\'button \' value=\'"+PropertiesMessageUtil.getPropertyMessage(BUNDLENAME_BUTTON_MSG, "requestInfo.button.addToCart", locale)+"\' type=\'button\'  " +				
									"onclick=\'addToCart('"+bundleId+"', '"+bundlePartId+
							"', '"+bundleMaterialID+"','','"+deviceType+"', '"+model+"', 'partQuantity"+bundleId+"','"+sapLineID+"','"+currency+"','bundle','"+partNumberArr+"','"+descriptionArr+"','"+qtyArr+"','"+salesOrg+"','', this);\'/></div>");
							
						}
						
					}else{
						if(price.indexOf(".")>0){
							
							String priceSplit[] = price.split("\\.");
							
							logger.debug("price1 -- > "+priceSplit[0]);
							logger.debug("price2 -- > "+priceSplit[1]);
							xml.append("<div id=\'priceDiv"+bundleId+"\'><span class=\'f200\'>"+priceSplit[0]+"</span><span class=\'minimum\'>.</span><span  class=\'f150\'>"+priceSplit[1]+"</span>"+"&nbsp;"+"<span class=\'f200\'>("+currency+")<span>"+"</div><div class=\'lineClear\'/>");
							
						}else{
							xml.append("<div id=\'priceDiv"+bundleId+"\'><span class=\'f200\'>"+price+"</span><span class=\'f200\'>("+currency+")</span></div><div class=\'lineClear\'/>");
						}
						xml.append("<input class=\'w50\' type=\'text\' name=\'quantity1\' onclick=\'focusOnInput('partQuantity"+bundleId+"');\' id=\'partQuantity"+bundleId+"\'><span class=\'spaceClear\'/><input class=\'button \' value=\'"+PropertiesMessageUtil.getPropertyMessage(BUNDLENAME_BUTTON_MSG, "requestInfo.button.addToCart", locale)+"\' type=\'button\'  " +				
								"onclick=\'addToCart('"+bundleId+"', '"+bundlePartId+
						"', '"+bundleMaterialID+"','','"+deviceType+"', '"+model+"', 'partQuantity"+bundleId+"','"+sapLineID+"','"+currency+"','bundle','"+partNumberArr+"','"+descriptionArr+"','"+qtyArr+"','"+salesOrg+"','', this);\'/></div>");
						
					}
				}else{
					xml.append("<input class=\'w50\' type=\'text\' name=\'quantity1\' onclick=\'focusOnInput('partQuantity"+bundleId+"');\' id=\'partQuantity"+bundleId+"\'><span class=\'spaceClear\'/><input class=\'button \' value=\'"+PropertiesMessageUtil.getPropertyMessage(BUNDLENAME_BUTTON_MSG, "requestInfo.button.addToCart", locale)+"\' type=\'button\'  " +				
							"onclick=\'addToCart('"+bundleId+"', '"+bundlePartId+"', '"+bundleMaterialID+"','','"+deviceType+"', '"+model+"', 'partQuantity"+bundleId+"','"+sapLineID+"','"+currency+"','bundle','"+partNumberArr+"','"+descriptionArr+"','"+qtyArr+"','"+salesOrg+"','', this);\'/></div>");
				}	
			}
			xml.append("]]></cell>\n");
			xml.append(" </row>\n");
			index++;
		}
			//posStart = posStart+1;
		
			xml.append(" </rows>\n");
			session.setAttribute("bundlePartListMap", bundlePartMap ,PortletSession.APPLICATION_SCOPE);
			session.setAttribute(ChangeMgmtConstant.HWPRICEMAP, hardwarePriceMap ,PortletSession.APPLICATION_SCOPE);
			return xml.toString();
	}
	
		//Translate the HardwarePart list to Data View XML still not used
		/**
		 * @param accessoriesList 
		 * @param session 
		 * @param priceResult 
		 * @param paymentType 
		 * @param totalCount 
		 * @param posStart 
		 * @return String 
		 * @throws ParserConfigurationException 
		 * @throws IOException 
		 * @throws SAXException 
		 * @throws FileNotFoundException 
		 */
		public  String accessoriesViewXml(List<OrderPart> accessoriesList, PortletSession session, PriceResult priceResult, String paymentType,int totalCount, int posStart) throws FileNotFoundException, SAXException, IOException, ParserConfigurationException
		{

		logger.debug ("Enter accessoriesViewXml");
		boolean partAddedTOCart = false;
		String quantity = "";
		boolean nullFlag=false;
			if(accessoriesList==null)
			{
				nullFlag=true;			
			}else{
				if(accessoriesList.isEmpty()){
					nullFlag=true;
				}
			}
			if(nullFlag){

				StringBuffer blankXml = new StringBuffer("<?xml version=\'1.0\' ?>\n");
				blankXml.append("<rows total_count=\""+totalCount+"\" pos=\""+posStart+"\">\n");
				blankXml.append("<row>\n");
				blankXml.append("<cell><![CDATA[");				
				blankXml.append("<p>"+PropertiesMessageUtil.getPropertyMessage(BUNDLENAME_BUTTON_MSG, "deviceDetail.description.norecordsfound", locale)+"</p>");
				blankXml.append("]]></cell>\n");
				blankXml.append(" </row>\n");
				blankXml.append(" </rows>\n");
				return blankXml.toString();			
			}
			//List<OrderPart> catalogOrderListToSession = (ArrayList<OrderPart>) session.getAttribute("hardwareOrderListToSession");
			StringBuffer xml = new StringBuffer("<?xml version=\'1.0\' ?>\n");			
			xml.append("<rows total_count=\'"+totalCount+"\' pos=\'"+posStart+"\'>\n");
			
			
			List<OrderPart> hardwareOrderListToSession = (ArrayList<OrderPart>) session.getAttribute("hardwareOrderListToSession");
			Map<String,Boolean> hardwareFinalFlags=(Map<String,Boolean>)session.getAttribute(ChangeMgmtConstant.HWFINALFLAGS,PortletSession.APPLICATION_SCOPE);
			boolean finalShowPriceFlag = hardwareFinalFlags.get("finalShowPriceFlag");
			Map<String,String> hardwarePriceMap= new HashMap<String,String>();
			if(session.getAttribute(ChangeMgmtConstant.HWPRICEMAP,PortletSession.APPLICATION_SCOPE)!=null){
				hardwarePriceMap = (Map<String,String>)session.getAttribute(ChangeMgmtConstant.HWPRICEMAP,PortletSession.APPLICATION_SCOPE);
			}				
			for(OrderPart accessory : accessoriesList)
			{
				logger.debug ("Accessory Part Number==>"+accessory.getPartNumber());
				
			
				String catalogId = "";
				String partNumber = "";
				String description = "";
				String deviceType = "";
				String model = "";
				String price = "";
				String currency = "";
				String contractLineItemId = "";
				String salesOrg = "";
				String partImage = "";
				String supplyId = "";
				quantity = "";
				
				
				
				partAddedTOCart = false;
				
				
				if(accessory.getCatalogId()!=null){
					catalogId = accessory.getCatalogId();
				}
				if(accessory.getPartNumber()!=null){
					partNumber = accessory.getPartNumber();
				}
				if(accessory.getSupplyId()!=null){
					supplyId = accessory.getSupplyId();
				}
				if(accessory.getDescription()!=null){
					description = accessory.getDescription();
				}				
				if(accessory.getDeviceType()!=null){
					deviceType = accessory.getDeviceType();
				}
				if(accessory.getContractLineItemId()!=null){
					contractLineItemId = accessory.getContractLineItemId();
				}
				if(accessory.getSalesOrg()!=null){
					salesOrg = accessory.getSalesOrg();
				}
				if(accessory.getModel()!=null){
					model = accessory.getModel();
				}
				
				if(priceResult!=null){
					for(Price priceOutputLine:priceResult.getPriceOutputList()){
						if(priceOutputLine.getContractLineItemId().equalsIgnoreCase(contractLineItemId)){
							if(priceOutputLine.getPrice()!=null && priceOutputLine.getPrice()!=""){
								price = priceOutputLine.getPrice();
								currency = priceOutputLine.getCurrency();
								break;
							}
						}
					}
				}
				
				hardwarePriceMap.put(catalogId, price);
				
				if(hardwareOrderListToSession!=null){
					for(int i=0;i<hardwareOrderListToSession.size();i++){
						if(catalogId.equalsIgnoreCase(hardwareOrderListToSession.get(i).getCatalogId())){
							//Update is required. So need to add existing quantity with new one
							logger.debug("Update is required for this catalog id. so it will show update order for part number "+partNumber);
							quantity = hardwareOrderListToSession.get(i).getPartQuantity();
							logger.debug("Quantity value to be there is "+quantity);
							partAddedTOCart = true;
							break;
						}
					}
					
				}				
				
				xml.append("<row id=\'"+ catalogId +"\'>\n");
				xml.append("<cell><![CDATA[");
				//partImage = URLImageUtil.getPartImage(partNumber);getPartImageFromLocal
				try{
				partImage = URLImageUtil.getPartImageFromLocal(partNumber);
				logger.debug("partImage is "+partImage);
				}catch(Exception e){
					logger.debug("Error retrieving part image");
					e.getMessage();
				}
				
				if(partImage!=IMAGE_NOT_FOUND){
					xml.append("<img src='"+partImage+"' alt=\'Change\' width=\'100\' height=\'100\'/>");
				}else{
					partImage = "";
					xml.append("<img src=\'/LexmarkServicesPortal/images/part_na_color.png\' width=\'100\' height=\'100\'/>");
				}
				xml.append("<input type=\'hidden\' id=\'hardwareTypeAccessories\' value=\'accessories\'/>]]></cell>\n");
				xml.append("<cell><![CDATA[");
				xml.append("<ul class=\'form\'><li><label for=\'refId\'>"+PropertiesMessageUtil.getPropertyMessage(BUNDLENAME_BUTTON_MSG, "requestInfo.heading.hardwarePartNo", locale)+"</label><span>"+partNumber+"</span></li><li><label for=\'refId\'>"+PropertiesMessageUtil.getPropertyMessage(BUNDLENAME_BUTTON_MSG, "requestInfo.label.description", locale)+"</label><span>"+description+"</span></li></ul>");
				xml.append("]]></cell>\n");
				xml.append("<cell><![CDATA[");
				xml.append("<ul class=\'form\'><li><label for=\'refId\'>"+PropertiesMessageUtil.getPropertyMessage(BUNDLENAME_BUTTON_MSG, "requestInfo.heading.deviceType", locale)+"</label><span>"+deviceType+"</span></li><li><label for=\'refId\'>"+PropertiesMessageUtil.getPropertyMessage(BUNDLENAME_BUTTON_MSG, "requestInfo.label.model", locale)+"</label><span>"+model+"</span></li></ul>");
				xml.append("]]></cell>\n");
				xml.append("<cell><![CDATA[");
				
				if(partAddedTOCart)
				{
					if(finalShowPriceFlag){
						if("".equals(price) || "0".equals(price) || "0.0".equals(price) || "0.00".equals(price)){
							//Msg displayed if price is not available for the part and no text field to enter qty to be shown
							xml.append("<div id=\'priceDiv"+catalogId+"\' class=\'errorText\'>"+PropertiesMessageUtil.getPropertyMessage(BUNDLENAME_BUTTON_MSG, "requestInfo.error.priceUnavailable", locale)+"</div><div class=\'lineClear\'/>"); //Changed currency font on 11June
							if(ChangeMgmtConstant.HW_PAYMENT_TYPE_PAY_LATER.equalsIgnoreCase(paymentType)){
								xml.append("<input class=\'w50\' type=\'text\' name=\'quantity1\'  onclick=\"focusOnInput('partQuantity"+catalogId+"');\"  id=\'partQuantity"+catalogId+"\' value=\'" + quantity + "\'><span class=\'spaceClear\'/><input class=\'button \' value=\'"+PropertiesMessageUtil.getPropertyMessage(BUNDLENAME_BUTTON_MSG, "requestInfo.button.updateOrder", locale)+"\' type=\'button\'  " +				
										"onclick=\"addToCart('"+catalogId+"', '"+partNumber+"', '"+supplyId+"','"+description+"','', '"+deviceType+"', '"+model+"', 'partQuantity"+catalogId+"','"+contractLineItemId+"','"+currency+"','accessories','','','','"+salesOrg+"','"+partImage+"', this);\"/></div>");
							}
						}else{
							if(price.indexOf(".")>0){
								
								String priceSplit[] = price.split("\\.");
								
								logger.debug("price1 -- > "+priceSplit[0]);
								logger.debug("price2 -- > "+priceSplit[1]);
								xml.append("<div id=\'priceDiv"+catalogId+"\'><span class=\'f200\'>"+priceSplit[0]+"</span><span class=\'minimum\'>.</span><span  class=\'f150\'>"+priceSplit[1]+"</span>"+"&nbsp;"+"<span class=\'f200\'>("+currency+")<span>"+"</div><div class=\'lineClear\'/>");
								
							}else{
								xml.append("<div id=\'priceDiv"+catalogId+"\'><span class=\'f200\'>"+price+"</span><span class=\'f200\'>("+currency+")</span></div><div class=\'lineClear\'/>");
							}
							xml.append("<input class=\'w50\' type=\'text\' name=\'quantity1\' onclick=\"focusOnInput('partQuantity"+catalogId+"');\" id=\'partQuantity"+catalogId+"\' value=\'" + quantity + "\'><span class=\'spaceClear\'/><input class=\'button \' value=\'"+PropertiesMessageUtil.getPropertyMessage(BUNDLENAME_BUTTON_MSG, "requestInfo.button.updateOrder", locale)+"\' type=\'button\'  " +				
									"onclick=\"addToCart('"+catalogId+"', '"+partNumber+"', '"+supplyId+"','"+description+"','"+deviceType+"', '"+model+"', 'partQuantity"+catalogId+"','"+contractLineItemId+"','"+currency+"','accessories','','','','"+salesOrg+"','"+partImage+"', this);\"/></div>");
							
						}
					}else{
						xml.append("<input class=\'w50\' type=\'text\' name=\'quantity1\' onclick=\"focusOnInput('partQuantity"+catalogId+"');\" id=\'partQuantity"+catalogId+"\' value=\'" + quantity + "\'><span class=\'spaceClear\'/><input class=\'button \' value=\'"+PropertiesMessageUtil.getPropertyMessage(BUNDLENAME_BUTTON_MSG, "requestInfo.button.updateOrder", locale)+"\' type=\'button\'  " +				
								"onclick=\"addToCart('"+catalogId+"', '"+partNumber+"','"+supplyId+"', '"+description+"','"+deviceType+"', '"+model+"', 'partQuantity"+catalogId+"','"+contractLineItemId+"','"+currency+"','accessories','','','','"+salesOrg+"','"+partImage+"', this);\"/></div>");
					}
					
				}else{
					if(finalShowPriceFlag){
						if("".equals(price) || "0".equals(price) || "0.0".equals(price) || "0.00".equals(price)){
							//Msg displayed if price is not available for the part and no text field to enter qty to be shown
							xml.append("<div id=\'priceDiv"+catalogId+"\' class=\'errorText\'>"+PropertiesMessageUtil.getPropertyMessage(BUNDLENAME_BUTTON_MSG, "requestInfo.error.priceUnavailable", locale)+"</div><div class=\'lineClear\'/>"); //Changed currency font on 11June
							if(ChangeMgmtConstant.HW_PAYMENT_TYPE_PAY_LATER.equalsIgnoreCase(paymentType)){
								xml.append("<input class=\'w50\' type=\'text\' name=\'quantity1\' onclick=\"focusOnInput('partQuantity"+catalogId+"');\" id=\'partQuantity"+catalogId+"\'><span class=\'spaceClear\'/><input class=\'button \' value=\'"+PropertiesMessageUtil.getPropertyMessage(BUNDLENAME_BUTTON_MSG, "requestInfo.button.addToCart", locale)+"\' type=\'button\'  " +				
										"onclick=\"addToCart('"+catalogId+"', '"+partNumber+"', '"+supplyId+"', '"+description+"','"+deviceType+"', '"+model+"', 'partQuantity"+catalogId+"','"+contractLineItemId+"','"+currency+"','accessories','','','','"+salesOrg+"','"+partImage+"', this);\"/></div>");
							}
						}else{
							if(price.indexOf(".")>0){
								
								String priceSplit[] = price.split("\\.");
								
								logger.debug("price1 -- > "+priceSplit[0]);
								logger.debug("price2 -- > "+priceSplit[1]);
								xml.append("<div id=\'priceDiv"+catalogId+"\'><span class=\'f200\'>"+priceSplit[0]+"</span><span class=\'minimum\'>.</span><span  class=\'f150\'>"+priceSplit[1]+"</span>"+"&nbsp;"+"<span class=\'f200\'>("+currency+")<span>"+"</div><div class=\'lineClear\'/>");
								
							}else{
								xml.append("<div id=\'priceDiv"+catalogId+"\'><span class=\'f200\'>"+price+"</span><span class=\'f200\'>("+currency+")</span></div><div class=\'lineClear\'/>");
							}
							xml.append("<input class=\'w50\' type=\'text\' name=\'quantity1\' onclick=\"focusOnInput('partQuantity"+catalogId+"');\" id=\'partQuantity"+catalogId+"\'><span class=\'spaceClear\'/><input class=\'button \' value=\'"+PropertiesMessageUtil.getPropertyMessage(BUNDLENAME_BUTTON_MSG, "requestInfo.button.addToCart", locale)+"\' type=\'button\'  " +				
									"onclick=\"addToCart('"+catalogId+"', '"+partNumber+"', '"+supplyId+"', '"+description+"','"+deviceType+"', '"+model+"', 'partQuantity"+catalogId+"','"+contractLineItemId+"','"+currency+"','accessories','','','','"+salesOrg+"','"+partImage+"', this);\"/></div>");
							
						}
					}else{
						xml.append("<input class=\'w50\' type=\'text\' name=\'quantity1\' onclick=\"focusOnInput('partQuantity"+catalogId+"');\" id=\'partQuantity"+catalogId+"\'><span class=\'spaceClear\'/><input class=\'button \' value=\'"+PropertiesMessageUtil.getPropertyMessage(BUNDLENAME_BUTTON_MSG, "requestInfo.button.addToCart", locale)+"\' type=\'button\'  " +				
								"onclick=\"addToCart('"+catalogId+"', '"+partNumber+"', '"+supplyId+"', '"+description+"','"+deviceType+"', '"+model+"', 'partQuantity"+catalogId+"','"+contractLineItemId+"','"+currency+"','accessories','','','','"+salesOrg+"','"+partImage+"', this);\"/></div>");
					}
					
				}
				xml.append("]]></cell>\n");				
				xml.append(" </row>\n");
			}
			xml.append(" </rows>\n");			
			session.setAttribute(ChangeMgmtConstant.HWPRICEMAP, hardwarePriceMap ,PortletSession.APPLICATION_SCOPE);
			return xml.toString();
		}
		
	
		
	 
	/**
	 * @param contacts 
	 * @param totalCount 
	 * @param posStart 
	 * @param contextPath 
	 * @return String 
	 */
	public String convertContactListToXML(List<AccountContact> contacts, int totalCount, 
			int posStart, String contextPath) {

		StringBuffer xml = new StringBuffer("<?xml version=\"1.0\" ?>\n");
		xml.append("<rows total_count=\""+totalCount+"\" pos=\""+posStart+"\">\n");
		int i = 0;
		String pencilImageSrc=null;
		pencilImageSrc = contextPath + IMAGE_PATH + PENCIL_IMAGE_FILE_NAME;
		//Fix by Service Portal AMS LEX:AIR00062572
		for (AccountContact accountContact : contacts) {
			xml.append(" <row id=\""+ (accountContact.getContactId())+"\">\n");
			xml.append("  <cell><![CDATA["+ XMLEncodeUtil.escapeXML(accountContact.getContactId()) +"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ XMLEncodeUtil.escapeXML(accountContact.getLastName()) +"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ XMLEncodeUtil.escapeXML(accountContact.getFirstName()) +"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ XMLEncodeUtil.escapeXML(accountContact.getWorkPhone()) +"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ XMLEncodeUtil.escapeXML(accountContact.getEmailAddress()) +"]]></cell>\n");
			xml.append("  <cell><![CDATA[ <input name=\"btn_select\" class=\"table_button\"  type=\"button\" onclick=\"addPrimaryContactElement('"
						+XMLEncodeUtil.escapeXML(accountContact.getContactId())+"','" +XMLEncodeUtil.escapeXML(accountContact.getLastName())+ "','" + XMLEncodeUtil.escapeXML(accountContact.getFirstName())
						+"','" +XMLEncodeUtil.escapeXML(accountContact.getWorkPhone())+"','" + XMLEncodeUtil.escapeXML(accountContact.getEmailAddress())
						+ "','" + accountContact.getUserFavorite() + "');\" value=\"" + XMLEncodeUtil.escapeXML(getLocalizeSelectButton()) + "\"/>");
			xml.append("&nbsp;<img src=\"" + pencilImageSrc +"\" onClick=\"hideSelect();popupEditContactPage('primary','" +
					XMLEncodeUtil.escapeXML(accountContact.getContactId()) + "','" +
					XMLEncodeUtil.escapeXML(accountContact.getLastName()) + "','" +
					XMLEncodeUtil.escapeXML(accountContact.getFirstName()) + "','" +
					XMLEncodeUtil.escapeXML(accountContact.getWorkPhone()) + "','" +
					XMLEncodeUtil.escapeXML(accountContact.getEmailAddress()) + "')\" style=\"cursor:pointer;\"/>]]></cell>\n");
			xml.append(" </row>\n");
			i ++;
		}
		xml.append("</rows>\n");
		return xml.toString();
	}
	
	/**
	 * @param contacts 
	 * @param totalCount 
	 * @param posStart 
	 * @param contextPath 
	 * @return String 
	 */
	public String convertSecondaryContactListToXML(List<AccountContact> contacts, int totalCount, 
			int posStart, String contextPath) {

		StringBuffer xml = new StringBuffer("<?xml version=\"1.0\" ?>\n");
		xml.append("<rows total_count=\""+totalCount+"\" pos=\""+posStart+"\">\n");
		int i = 0;	
		String pencilImageSrc=null;
		pencilImageSrc = contextPath + IMAGE_PATH + PENCIL_IMAGE_FILE_NAME;
		//Fix by Service Portal AMS LEX:AIR00062572
		for (AccountContact accountContact : contacts) {
			xml.append(" <row id=\""+ (accountContact.getContactId())+"\">\n");
			xml.append("  <cell><![CDATA["+ XMLEncodeUtil.escapeXML(accountContact.getContactId()) +"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ XMLEncodeUtil.escapeXML(accountContact.getLastName()) +"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ XMLEncodeUtil.escapeXML(accountContact.getFirstName()) +"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ XMLEncodeUtil.escapeXML(accountContact.getWorkPhone()) +"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ XMLEncodeUtil.escapeXML(accountContact.getEmailAddress()) +"]]></cell>\n");
			xml.append("  <cell><![CDATA[ <input name=\"btn_select\" class=\"table_button\"  type=\"button\" onclick=\"addSecondaryContactElement('"
						+XMLEncodeUtil.escapeXML(accountContact.getContactId())+"','" +XMLEncodeUtil.escapeXML(accountContact.getLastName())+ "','" + XMLEncodeUtil.escapeXML(accountContact.getFirstName())
						+"','" +XMLEncodeUtil.escapeXML(accountContact.getWorkPhone())+"','" + XMLEncodeUtil.escapeXML(accountContact.getEmailAddress())
						+ "','" + accountContact.getUserFavorite() + "');\" value=\"" + getLocalizeSelectButton() + "\"/>");
			xml.append("&nbsp;<img src=\"" + pencilImageSrc +"\" onClick=\"hideSelect();popupEditContactPage('secondary','" +
					XMLEncodeUtil.escapeXML(accountContact.getContactId()) + "','" +
					XMLEncodeUtil.escapeXML(accountContact.getLastName()) + "','" +
					XMLEncodeUtil.escapeXML(accountContact.getFirstName()) + "','" +
					XMLEncodeUtil.escapeXML(accountContact.getWorkPhone()) + "','" +
					XMLEncodeUtil.escapeXML(accountContact.getEmailAddress()) + "')\" style=\"cursor:pointer;\"/>]]></cell>\n");
			xml.append(" </row>\n");
			i ++;
		}
		xml.append("</rows>\n");
		return xml.toString();
	}
	
	/**
	 * @param contacts 
	 * @param totalCount 
	 * @param posStart 
	 * @param contextPath 
	 * @return String 
	 */
	public String convertMyContactListToXML(List<AccountContact> contacts, int totalCount, 
			int posStart, String contextPath) {

		StringBuffer xml = new StringBuffer("<?xml version=\"1.0\" ?>\n");
		xml.append("<rows total_count=\""+totalCount+"\" pos=\""+posStart+"\">\n");
		int i = 0;
		String imageSrc=null;
		String pencilImageSrc=null;
		pencilImageSrc = contextPath + IMAGE_PATH + PENCIL_IMAGE_FILE_NAME;
		//Fix by Service Portal AMS LEX:AIR00062572
		for (AccountContact accountContact : contacts) {
			xml.append(" <row id=\""+ (accountContact.getContactId())+"\">\n");
			if(accountContact.getUserFavorite()){
				imageSrc = contextPath + IMAGE_PATH + FAVORIATE_IMAGE_FILE_NAME;
			} else {
				imageSrc = contextPath + IMAGE_PATH + UNFAVORIATE_IMAGE_FILE_NAME;
			}
			xml.append("  <cell><![CDATA["+ XMLEncodeUtil.escapeXML(accountContact.getContactId()) +"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ XMLEncodeUtil.escapeXML(accountContact.getLastName()) +"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ XMLEncodeUtil.escapeXML(accountContact.getFirstName()) +"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ XMLEncodeUtil.escapeXML(accountContact.getWorkPhone()) +"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ XMLEncodeUtil.escapeXML(accountContact.getEmailAddress()) +"]]></cell>\n");
			xml.append("  <cell><![CDATA[ <input name=\"btn_select\" class=\"table_button\"  type=\"button\" onclick=\"addPrimaryContactElement('"
						+XMLEncodeUtil.escapeXML(accountContact.getContactId())+"','" +XMLEncodeUtil.escapeXML(accountContact.getLastName())+ "','" + XMLEncodeUtil.escapeXML(accountContact.getFirstName())
						+"','" + XMLEncodeUtil.escapeXML(accountContact.getWorkPhone())+"','" + XMLEncodeUtil.escapeXML(accountContact.getEmailAddress())
						+"','" + accountContact.getUserFavorite() + "');\" value=\"" + getLocalizeSelectButton() + "\"/>");
			xml.append("&nbsp;<img src=\"" + pencilImageSrc +"\" onClick=\"hideSelect();popupEditContactPage('primary','" +
					XMLEncodeUtil.escapeXML(accountContact.getContactId()) + "','" +
					XMLEncodeUtil.escapeXML(accountContact.getLastName()) + "','" +
					XMLEncodeUtil.escapeXML(accountContact.getFirstName()) + "','" +
					XMLEncodeUtil.escapeXML(accountContact.getWorkPhone()) + "','" +
					XMLEncodeUtil.escapeXML(accountContact.getEmailAddress()) + "')\" style=\"cursor:pointer;\"/>");
			xml.append("&nbsp;<img src=\"" + imageSrc +"\" id=\"starImg_primary_" +
					XMLEncodeUtil.escapeXML(accountContact.getContactId()) +
					"\" onClick=\"setPrimaryContactFavourite('" +
					XMLEncodeUtil.escapeXML(accountContact.getContactId()) +
					"', false)\" style=\"cursor:pointer;\"/>]]></cell>\n");
			xml.append(" </row>\n");
			i ++;
		}
		xml.append("</rows>\n");
		//logger.info(xml.toString());
		return xml.toString();
	}
	
	/**
	 * @param contacts 
	 * @param totalCount 
	 * @param posStart 
	 * @param contextPath 
	 * @return String 
	 */
	public String convertMySecondaryContactListToXML(List<AccountContact> contacts, int totalCount, 
			int posStart, String contextPath) {

		StringBuffer xml = new StringBuffer("<?xml version=\"1.0\" ?>\n");
		xml.append("<rows total_count=\""+totalCount+"\" pos=\""+posStart+"\">\n");
		int i = 0;
		String imageSrc=null;
		String pencilImageSrc=null;
		pencilImageSrc = contextPath + IMAGE_PATH + PENCIL_IMAGE_FILE_NAME;
		for (AccountContact accountContact : contacts) {
			xml.append(" <row id=\""+ (accountContact.getContactId())+"\">\n");
			if(accountContact.getUserFavorite()){
				imageSrc = contextPath + IMAGE_PATH + FAVORIATE_IMAGE_FILE_NAME;
			} else {
				imageSrc = contextPath + IMAGE_PATH + UNFAVORIATE_IMAGE_FILE_NAME;
			}
			//Fix by Service Portal AMS LEX:AIR00062572
			xml.append("  <cell><![CDATA["+ XMLEncodeUtil.escapeXML(accountContact.getContactId()) +"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ XMLEncodeUtil.escapeXML(accountContact.getLastName()) +"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ XMLEncodeUtil.escapeXML(accountContact.getFirstName()) +"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ XMLEncodeUtil.escapeXML(accountContact.getWorkPhone()) +"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ XMLEncodeUtil.escapeXML(accountContact.getEmailAddress()) +"]]></cell>\n");
			xml.append("  <cell><![CDATA[ <input name=\"btn_select\" class=\"table_button\"  type=\"button\" onclick=\"addSecondaryContactElement('"
						+XMLEncodeUtil.escapeXML(accountContact.getContactId())+"','" +XMLEncodeUtil.escapeXML(accountContact.getLastName())+ "','" + XMLEncodeUtil.escapeXML(accountContact.getFirstName())
						+"','" +XMLEncodeUtil.escapeXML(accountContact.getWorkPhone())+"','" + XMLEncodeUtil.escapeXML(accountContact.getEmailAddress())
						+"','" +accountContact.getUserFavorite()+  "');\" value=\"" + getLocalizeSelectButton() + "\"/>");
			xml.append("&nbsp;<img src=\"" + pencilImageSrc +"\" onClick=\"hideSelect();popupEditContactPage('secondary','" +
					XMLEncodeUtil.escapeXML(accountContact.getContactId()) + "','" +
					XMLEncodeUtil.escapeXML(accountContact.getLastName()) + "','" +
					XMLEncodeUtil.escapeXML(accountContact.getFirstName()) + "','" +
					XMLEncodeUtil.escapeXML(accountContact.getWorkPhone()) + "','" +
					XMLEncodeUtil.escapeXML(accountContact.getEmailAddress()) + "')\" style=\"cursor:pointer;\"/>");
			xml.append("&nbsp;<img src=\"" + imageSrc +"\" id=\"starImg_secondary_" +
					XMLEncodeUtil.escapeXML(accountContact.getContactId()) +
					"\" onClick=\"setSecondaryContactFavourite('" +
					XMLEncodeUtil.escapeXML(accountContact.getContactId()) +
					"', false)\" style=\"cursor:pointer;\"/>]]></cell>\n");
			xml.append(" </row>\n");
			i ++;
		}
		xml.append("</rows>\n");
		return xml.toString();
	}
	
	/**
	 * @param addresses 
	 * @param totalCount 
	 * @param posStart 
	 * @param contextPath 
	 * @return String 
	 */
	public String convertAddressListToXML(List<GenericAddress> addresses, int totalCount, 
			int posStart, String contextPath) {

		StringBuffer xml = new StringBuffer("<?xml version=\"1.0\" ?>\n");
		xml.append("<rows total_count=\""+totalCount+"\" pos=\""+posStart+"\">\n");
		int i = 0;
		for (GenericAddress genericAddress : addresses) {
			String addressId = genericAddress.getAddressId()!=null?genericAddress.getAddressId():"";
			String addressName = genericAddress.getAddressName()!=null?genericAddress.getAddressName():"";
			String addressLine1 = genericAddress.getAddressLine1()!=null?genericAddress.getAddressLine1():"";
			String addressLine2 = genericAddress.getAddressLine2()!=null?genericAddress.getAddressLine2():"";
			String addressLine3 = genericAddress.getAddressLine3()!=null?genericAddress.getAddressLine3():"";
			String addressCity = genericAddress.getCity()!=null?genericAddress.getCity():"";
			String addressState = genericAddress.getState()!=null?genericAddress.getState():"";
			String addressProvince = genericAddress.getProvince()!=null?genericAddress.getProvince():"";
			String addressCountry = genericAddress.getCountry()!=null?genericAddress.getCountry():"";
			String addressPostCode = genericAddress.getPostalCode()!=null?genericAddress.getPostalCode():"";
			//Fix by Service Portal AMS LEX:AIR00062572
			if(addressId==""){
				continue;
			}
			xml.append(" <row id=\""+ (addressId)+"\">\n");
			xml.append("  <cell><![CDATA["+ XMLEncodeUtil.escapeXML(addressId) +"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ XMLEncodeUtil.escapeXML(addressName) +"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ XMLEncodeUtil.escapeXML(addressLine1) +"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ XMLEncodeUtil.escapeXML(addressLine2) +"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ XMLEncodeUtil.escapeXML(addressLine3) +"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ XMLEncodeUtil.escapeXML(addressCity) +"]]></cell>\n");			
			xml.append("  <cell><![CDATA["+ XMLEncodeUtil.escapeXML(addressState) +"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ XMLEncodeUtil.escapeXML(addressProvince) +"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ XMLEncodeUtil.escapeXML(addressPostCode) +"]]></cell>\n");
			
			
			xml.append("  <cell><![CDATA[ <input name=\"btn_select\" class=\"table_button\"  type=\"button\" onclick=\"addServiceAddressElement('"
					+XMLEncodeUtil.escapeXML(addressId)+"','" +XMLEncodeUtil.escapeXML(addressName)+ "','" + XMLEncodeUtil.escapeXML(addressLine1)
					+"','" +XMLEncodeUtil.escapeXML(addressLine2)+"','" + XMLEncodeUtil.escapeXML(addressLine3)+"','" + XMLEncodeUtil.escapeXML(addressCity)
					+ "','" + XMLEncodeUtil.escapeXML(addressState) + "','" + XMLEncodeUtil.escapeXML(addressProvince) + "',null,'"+ XMLEncodeUtil.escapeXML(addressCountry) + "','" + XMLEncodeUtil.escapeXML(addressPostCode)
					+ "','" + genericAddress.getUserFavorite() +"');\" value=\"" + getLocalizeSelectButton() + "\"/>]]></cell>\n");
			xml.append(" </row>\n");
			i ++;
		}
		xml.append("</rows>\n");
		return xml.toString();
	}
	
	/**
	 * @param addresses 
	 * @param totalCount 
	 * @param posStart 
	 * @param contextPath 
	 * @return String 
	 */
	public String convertMyAddressListToXML(List<GenericAddress> addresses, int totalCount, 
			int posStart, String contextPath) {

		StringBuffer xml = new StringBuffer("<?xml version=\"1.0\" ?>\n");
		xml.append("<rows total_count=\""+totalCount+"\" pos=\""+posStart+"\">\n");
		int i = 0;
		String imageSrc=null;
		
		for (GenericAddress genericAddress : addresses) {
			String addressId = genericAddress.getAddressId()!=null?genericAddress.getAddressId():"";
			String addressName = genericAddress.getAddressName()!=null?genericAddress.getAddressName():"";
			String addressLine1 = genericAddress.getAddressLine1()!=null?genericAddress.getAddressLine1():"";
			String addressLine2 = genericAddress.getAddressLine2()!=null?genericAddress.getAddressLine2():"";
			String addressLine3 = genericAddress.getAddressLine3()!=null?genericAddress.getAddressLine3():"";
			String addressCity = genericAddress.getCity()!=null?genericAddress.getCity():"";
			String addressState = genericAddress.getState()!=null?genericAddress.getState():"";
			String addressProvince = genericAddress.getProvince()!=null?genericAddress.getProvince():"";
			String addressCountry = genericAddress.getCountry()!=null?genericAddress.getCountry():"";
			String addressPostCode = genericAddress.getPostalCode()!=null?genericAddress.getPostalCode():"";
			//Fix by Service Portal AMS LEX:AIR00062572
			if(addressId==""){
				continue;
			}
			xml.append(" <row id=\""+ (addressId)+"\">\n");
			if(genericAddress.getUserFavorite()){
				imageSrc = contextPath + IMAGE_PATH + FAVORIATE_IMAGE_FILE_NAME;
			} else {
				imageSrc = contextPath + IMAGE_PATH + UNFAVORIATE_IMAGE_FILE_NAME;
			}
			xml.append("  <cell><![CDATA["+ XMLEncodeUtil.escapeXML(addressId) +"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ XMLEncodeUtil.escapeXML(addressName) +"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ XMLEncodeUtil.escapeXML(addressLine1) +"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ XMLEncodeUtil.escapeXML(addressLine2) +"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ XMLEncodeUtil.escapeXML(addressLine3) +"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ XMLEncodeUtil.escapeXML(addressCity) +"]]></cell>\n");			
			xml.append("  <cell><![CDATA["+ XMLEncodeUtil.escapeXML(addressState) +"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ XMLEncodeUtil.escapeXML(addressProvince) +"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ XMLEncodeUtil.escapeXML(addressPostCode) +"]]></cell>\n");
			
			
			xml.append("  <cell><![CDATA[ <input name=\"btn_select\" class=\"table_button\"  type=\"button\" onclick=\"addServiceAddressElement('"
					+XMLEncodeUtil.escapeXML(addressId)+"','" +XMLEncodeUtil.escapeXML(addressName)+ "','" + XMLEncodeUtil.escapeXML(addressLine1)
					+"','" +XMLEncodeUtil.escapeXML(addressLine2)+"','" + XMLEncodeUtil.escapeXML(addressLine3)+"','" + XMLEncodeUtil.escapeXML(addressCity)
					+ "','" + XMLEncodeUtil.escapeXML(addressState) + "','" + XMLEncodeUtil.escapeXML(addressProvince) + "',null,'"+ XMLEncodeUtil.escapeXML(addressCountry) + "','" + XMLEncodeUtil.escapeXML(addressPostCode)
					+ "','" + genericAddress.getUserFavorite() +"');\" value=\"" + getLocalizeSelectButton() + "\"/>");
			xml.append("&nbsp;<img src=\"" + imageSrc + "\" id=\"starImg_address_" +
					XMLEncodeUtil.escapeXML(addressId) + "\" onClick=\"setServiceAddressFavourite('" +
					XMLEncodeUtil.escapeXML(addressId) + "', false)\" style=\"cursor:pointer;\"/>]]></cell>\n");

			xml.append(" </row>\n");
			i ++;
		}
		xml.append("</rows>\n");
		return xml.toString();
	}
	
	
	//Translate the asset list to Data View XML 
	/**
	 * @param assetList 
	 * @param totalCount 
	 * @param posStart 
	 * @return String 
	 */
	public  String assetsToDataViewXml(List<Asset> assetList,int totalCount, int posStart)
		{

			if(assetList==null||assetList.isEmpty())
			{
				return "<data></data>";
			}
			StringBuffer sb = new StringBuffer();
			sb.append("<data total_count='" + totalCount +  "' pos='"+posStart+"'>");
			for(Asset asset : assetList)
			{

				sb.append("<item id=\""+asset.getAssetId()+"\">");
				sb.append("<img>"+asset.getProductImageURL()+"</img>");
				sb.append("<model>"+asset.getProductLine()+"</model>");
				sb.append("<productTLI>"+asset.getProductTLI()+"</productTLI>");
				sb.append("<select>"+asset.getAssetId()+"</select></item>");
			}
			sb.append("</data>");
			return sb.toString();
		}
	
	//o	Printer installed location (Address1, Address 2, State/Province, Country)
	//o	Physical Location (Physical Location 1, Physical Location 2, Physical Location 3) 


	/**
	 * @param locale 
	 * @param assets 
	 * @param totalCount 
	 * @param posStart 
	 * @param contextPath 
	 * @param userSiebelContactId 
	 * @return String 
	 */
	public String convertMeterReadAssetToXML(Locale locale,List<Asset> assets,
			int totalCount, int posStart, String contextPath, String userSiebelContactId) {

		initialLocalizedMessage();
		String btnMsg = PropertiesMessageUtil.getPropertyMessage(BUNDLENAME_BUTTON_MSG, LAB_BUTTON_SAVE, locale);
		StringBuffer xml = new StringBuffer("<?xml version=\"1.0\" ?>\n");
		xml.append("<rows total_count=\""+totalCount+"\" pos=\""+posStart+"\">\n");
		int i = 0;
		//String imageSrc="";
		//String imageName;
		String imageUpdatingSrc= contextPath + IMAGE_PATH + LOADING_IMAGE_FILE_NAME;
		String imageCalendarSrc = contextPath + IMAGE_PATH + CALENDAR_ICON_NAME;
		String transparentImageSrc=contextPath + IMAGE_PATH +"transparent.png";
		String className="";
		//brmandal added the below line for MPS phase 2 -- page count changes
		String updateMeterReadsMsg = PropertiesMessageUtil.getPropertyMessage(BUNDLENAME_BUTTON_MSG, LB_UPDATE_PAGECOUNT, locale);
		String provinceOrState="";
		if (assets != null){
			logger.debug("convertMeterReadAssetToXML...in assets not null");
			for (Asset asset : assets) {
				logger.debug("convertMeterReadAssetToXML...in for loop");
				int rowId = posStart + i;
				String buttonId = "btn_save"+rowId;
				String imgUpdatingId = "img_update"+rowId;
				String inputCountId = "input_count" + rowId;
				String ltpcCount = "label_ltpcCount" + rowId;
				String lastColorCountId = "label_lccCount" + rowId;
				String inputColorCountId= "input_colorCount" + rowId;
				String readDateId = "readDate" + rowId;
				String localizedReadDateId = "localizedReadDate" + rowId;
				String prefixLocalizedReadDateId = "LocalizedReadDate" + rowId;
				String fName = "";
				String lName = "";
				String phoneNumber = "";
				String pEmail ="";
				boolean deviceBookmarked = false;
				String bookmarkLabel=null;
				logger.debug("before user favorite");
				if (asset.getUserFavoriteFlag()) {
					
					//imageSrc = contextPath + IMAGE_PATH + FAVORIATE_IMAGE_FILE_NAME;
					//imageName = FAVORIATE_IMAGE_FILE_NAME;
					className="ui_icon_sprite bookmark-star-icon";
					deviceBookmarked=true;
					bookmarkLabel=LB_UNBOOKMARKED_DEVICE;
				} else {
					
					//imageSrc = contextPath + IMAGE_PATH + UNFAVORIATE_IMAGE_FILE_NAME;
				//	imageName = UNFAVORIATE_IMAGE_FILE_NAME;
					className="ui_icon_sprite removebookmark-icon";
					deviceBookmarked=false;
					bookmarkLabel=LB_BOOKMARKED_DEVICE;
				}
				
				//logger.debug("after user favorite");
				String installAddressLine1 = "";
				String installAddressLine2 = "";
				String installAddressCity = "";
				String installAddressState = "";
				String installAddressProvince = "";
				String installAddressCountry = "";
				String installAddressName = "";
				//brmandal added for page count changes - MPS phase 2.1 
				String installAddressOfficeNumber = "";
				String installAddressCounty = "";
				String installAddressDistrict = "";
				String installPostalCode="";
				//logger.debug("after variables set");
				//brmandal ended for page count changes - MPS phase 2.1
				if (asset.getInstallAddress()!=null){
					logger.debug("in install address ");
					if (StringUtils.isNotBlank(asset.getInstallAddress().getProvince())) {
						//logger.debug("in if install address province");
						provinceOrState = asset.getInstallAddress().getProvince();
					} else {
						//logger.debug("in else install address state");
						provinceOrState = asset.getInstallAddress().getState();
					}
					installAddressLine1 = asset.getInstallAddress().getAddressLine1();
					installAddressLine2 = asset.getInstallAddress().getAddressLine2();
					installAddressCity = asset.getInstallAddress().getCity();
					//brmandal added for page count changes - MPS phase 2.1 
					installAddressOfficeNumber = asset.getInstallAddress().getOfficeNumber();
					installAddressCounty = asset.getInstallAddress().getCounty();
					installAddressDistrict = asset.getInstallAddress().getDistrict();
					//brmandal ended for page count changes - MPS phase 2.1
					installAddressState = asset.getInstallAddress().getState();
					installAddressProvince = asset.getInstallAddress().getProvince();
					installAddressCountry = asset.getInstallAddress().getCountry();
					installAddressName = asset.getInstallAddress().getAddressName();
					installPostalCode=asset.getInstallAddress().getPostalCode();
				}
				if (asset.getAssetContact()!=null){
					fName = asset.getAssetContact().getFirstName();
					lName = asset.getAssetContact().getLastName();
					//phoneNumber = asset.getPhysicalLocation2();
					phoneNumber = asset.getAssetContact().getWorkPhone();
					pEmail = asset.getAssetContact().getEmailAddress();
				}
				//logger.debug("after contact");
				//Fix by Service Portal AMS LEX:AIR00062572
				//brmandal added for Page Count changes - MPS phase 2.1
				logger.debug("asset.getPageCounts()"+asset.getPageCounts());
				List<PageCounts> pagecountsList = asset.getPageCounts();
				String lastPageCount = new String("");
				String lastColorPageCount = new String("");
				String lastReadDate = new String("");
				String lastColorReadDate = new String("");
				if(pagecountsList!=null){
					for(PageCounts pagecounts : pagecountsList){
						//logger.debug(pagecounts);
						if((pagecounts.getName() != null) && (pagecounts.getName().equalsIgnoreCase("LTPC"))) {
							if(pagecounts.getCount() != null){
								lastPageCount = pagecounts.getCount();
							}
						if(pagecounts.getDate() != null){
								lastReadDate = pagecounts.getDate();
						}
							logger.debug("lastPageCount "+lastPageCount);
						}
						if((pagecounts.getName() != null) && (pagecounts.getName().equalsIgnoreCase("Color"))) {
							if(pagecounts.getCount() != null){
								lastColorPageCount = pagecounts.getCount();
							}
							if(pagecounts.getDate() != null){
								lastColorReadDate = pagecounts.getDate();
							}
							logger.debug("lastColorPageCount "+lastColorPageCount);
						}
							
					}
				}
				
				
				//logger.debug("here");
				//brmandal ended for Page Count changes - MPS phase 2.1
				xml.append(" <row id=\""+ rowId +"\">\n");
				//if(asset.getHostName() != null && !asset.getHostName().trim().equals("") || asset.getIpAddress() != null && !asset.getIpAddress().trim().equals("")){
					logger.debug("convertMeterReadAssetToXML... in if..");
					xml.append("<cell><![CDATA[<table width='900px'>" +
							"<tr valign='top'>" +
							"<td width=\"20%\"><table><tr><td width=\"100%\" align=\"center\" class='labelBold'>"+XMLEncodeUtil.escapeXML(asset.getDescriptionLocalLang())+"</td></tr>" +
								"<tr><td align=\"center\" height='76px'><img src='"+XMLEncodeUtil.escapeXML(asset.getProductImageURL())+"' class='printer' /></td></tr>" +
								"<tr><td align='left' height='22px'><img id = 'starIMG_"+XMLEncodeUtil.escapeXML(asset.getAssetId())+"' style='cursor:Hand;cursor:pointer' onClick=updateUserFavoriteAsset('"+XMLEncodeUtil.escapeXML(asset.getAssetId())+"') class=\""+className+"\" src='" + transparentImageSrc + "' name=''/>&nbsp;<a href='#' id='starTXT_"+asset.getAssetId()+ "'onClick=updateUserFavoriteAsset('"+asset.getAssetId()+"')>"+bookmarkLabel+"</a></tr></td></tr>" +
								"</table></td>" +
							"<td width=\"80%\"><table width=\"100%\" class=\"grid-inner-body\">" +
								"<tr width=\"100%\"><td width=\"20%\" class='labelBold' align=\"left\">"+LB_INSTALLED_ADDRESS+":</td><td width=\"20%\" class='labelBold' align=\"left\">"+LB_PHYSICAL_LOCATION+":</td><td width=\"20%\" class='labelBold' align=\"left\">"+LB_PRIMARY_CONTACT+":</td><td width=\"40%\"><a href=\"javascript:void(0)\" style=\"color:#336699; font-weight:bold;\" onClick=\"gotoControlPanel('" + XMLEncodeUtil.escapeXML(asset.getControlPanelURL())  + "')\">" + LB_CONTROL_PANEL + "</td></tr>" +							
								/*
								 * Commented out physicalLocation1, 2 and 3 from Asset bean
								 */
								/*"<tr><td>&nbsp;</td><td>"+ installAddressLine1+"</td><td>"+ asset.getPhysicalLocation1()+"</td><td>"+ pName +"</td></tr>" +
								"<tr><td>&nbsp;</td><td>"+ installAddressLine2+"</td><td>"+ asset.getPhysicalLocation2()+"</td><td>"+ phoneNumber+"</td></tr>" +
								"<tr><td>&nbsp;</td><td>"+ provinceOrState+"</td><td>"+ asset.getPhysicalLocation3()+"</td><td>"+ pEmail +"</td></tr>" +*/
							//	"<tr><td>"+ installAddressLine1+"</td><td>"+ XMLEncodeUtil.escapeXML(asset.getPhysicalLocationAddress().getPhysicalLocation1())+"</td><td>"+ pName +"</td>" +
								//brmandal added the below line for Page Counts - MPS phase 2
									
								/*"</tr>" +
								"<tr><td>"+ installAddressLine2+"</td><td>"+ XMLEncodeUtil.escapeXML(asset.getPhysicalLocationAddress().getPhysicalLocation2())+"</td><td>"+ phoneNumber+"</td></tr>" +
								"<tr><td>"+ provinceOrState+"</td><td>"+ XMLEncodeUtil.escapeXML(asset.getPhysicalLocationAddress().getPhysicalLocation3())+"</td><td>"+ pEmail +"</td></tr>" +
								"<tr><td>"+ installAddressCountry+"</td><td colspan=\"3\"></td></tr></table></td>");*/
								"<tr><td><ul>");
					if(StringUtils.isNotBlank(installAddressLine1)){
						xml.append("<li>"+XMLEncodeUtil.escapeXML(installAddressLine1)+"</li>");
					}
					if(StringUtils.isNotBlank(installAddressOfficeNumber)){
						xml.append("<li>"+XMLEncodeUtil.escapeXML(installAddressOfficeNumber)+"</li>");
					}
					if(StringUtils.isNotBlank(installAddressLine2)){
						xml.append("<li>"+XMLEncodeUtil.escapeXML(installAddressLine2)+"</li>");
					}
					xml.append("<li>" + XMLEncodeUtil.escapeXML(installAddressCity));
					if(StringUtils.isNotBlank(installAddressCounty)){
						xml.append(", " + XMLEncodeUtil.escapeXML(installAddressCounty));
					}
					if(StringUtils.isNotBlank(provinceOrState)){
						xml.append(", " + XMLEncodeUtil.escapeXML(provinceOrState));
						
					}
					xml.append("</li>");
					if(StringUtils.isNotBlank(installAddressProvince)){
						if(StringUtils.isNotBlank(installAddressDistrict)){
							xml.append("<li>" + installAddressProvince);
							xml.append(", " + installAddressDistrict+"</li>");
						}else{
							xml.append("<li>" + installAddressProvince+"</li>");
						}
					}
					
					if(StringUtils.isNotBlank(installPostalCode)){
						xml.append("<li>"+installPostalCode+"</li>");
					}
					
					if(StringUtils.isNotBlank(installAddressCountry)){
						xml.append("<li>"+XMLEncodeUtil.escapeXML(installAddressCountry)+"</li>");
					}
					xml.append("</ul></td><td><ul>");
					//logger.debug("here2");
					
					if(StringUtils.isNotBlank(asset.getPhysicalLocationAddress().getPhysicalLocation1())){
						xml.append("<li>"+XMLEncodeUtil.escapeXML(asset.getPhysicalLocationAddress().getPhysicalLocation1())+"</li>");
					}
					if(StringUtils.isNotBlank(asset.getPhysicalLocationAddress().getPhysicalLocation2())){
						xml.append("<li>"+XMLEncodeUtil.escapeXML(asset.getPhysicalLocationAddress().getPhysicalLocation2())+"</li>");
					}
					if(StringUtils.isNotBlank(asset.getPhysicalLocationAddress().getPhysicalLocation3())){
						xml.append("<li>"+XMLEncodeUtil.escapeXML(asset.getPhysicalLocationAddress().getPhysicalLocation3())+"</li>");
					}
					
					xml.append("</ul></td><td><ul>");		
					
					if(StringUtils.isNotBlank(fName) && StringUtils.isNotBlank(lName)){
						xml.append("<li>"+XMLEncodeUtil.escapeXML(fName) + "&nbsp;" + XMLEncodeUtil.escapeXML(lName)+"</li>");
					}
					if(StringUtils.isNotBlank(phoneNumber)){
						xml.append("<li>"+XMLEncodeUtil.escapeXML(phoneNumber)+"</li>");
					}
					if(StringUtils.isNotBlank(pEmail)){
						xml.append("<li>"+XMLEncodeUtil.escapeXML(pEmail)+"</li>");
					}

					xml.append("</ul></td><td width=\"15%\"> <a href=\"###\" id=\"meterReadsCountLink\" style=\"color:#336699; font-weight:bold;\" onClick=\"return openPopUp('"+
																asset.getAssetId()+"','" + asset.getSerialNumber()+"','" + asset.getIpAddress()+"','" + asset.getProductLine() +"','"+ asset.getAssetTag()+
																"');\">"+updateMeterReadsMsg+"</a></td></tr></table></td>");
					
					//logger.debug("here3");
								
				//}
			
				//Fix by Service Portal AMS LEX:AIR00062572
				xml.append("</tr></table>]]></cell>\n");
				xml.append("  <cell><![CDATA["+ XMLEncodeUtil.escapeXML(asset.getSerialNumber()) +"]]></cell>\n");
				xml.append("  <cell><![CDATA["+ XMLEncodeUtil.escapeXML(asset.getDescriptionLocalLang()) +"]]></cell>\n");
				xml.append("  <cell><![CDATA["+ XMLEncodeUtil.escapeXML(asset.getAssetTag()) +"]]></cell>\n");
				xml.append("  <cell><![CDATA["+ XMLEncodeUtil.escapeXML(asset.getIpAddress()) +"]]></cell>\n");
				xml.append("  <cell><![CDATA["+ XMLEncodeUtil.escapeXML(installAddressName) +"]]></cell>\n");
				Date readDate = null;
				Date readDatePlus1 = null;
				//logger.debug("here4");
				//if(asset.getColorCapableFlag()!= null && asset.getColorCapableFlag()){
				try{
					//logger.debug("in tryyy");
					if((lastColorPageCount != null) && (lastColorPageCount.length() > 0) || (!lastColorPageCount.isEmpty())){
						readDate = dateFormat.parse(DateUtil.getDateInStringFormat(lastColorReadDate));
					}else{
						logger.debug("read date is"+lastReadDate);
						if(StringUtils.isNotBlank(lastReadDate)){
							readDate = dateFormat.parse(DateUtil.getDateInStringFormat(lastReadDate));
						}
					}
					//logger.debug("out try");
					logger.debug("readDate "+readDate);
				}catch(ParseException pe)
				{
					logger.debug("Parse Exception "+pe.getMessage());
				}
			//	logger.debug("here5");
				readDatePlus1 = readDate;
				//String readDateStr = "null";
				String readDatePlus1Str = "null";
				String reloadPrevAction = null;
				if (readDate != null) {
					Calendar c1 = Calendar.getInstance(); 
					c1.setTime(readDatePlus1);
					c1.add(Calendar.DATE,1);

					readDatePlus1 = c1.getTime();

					//readDateStr = "'" + dateFormat.format(readDate) + "'";
					readDatePlus1Str = "'" + dateFormat.format(readDatePlus1) + "'";
					reloadPrevAction = "reloadDateAction('" + localizedReadDateId + "', " + readDatePlus1Str + ", 'PREV');";
					//logger.debug("read date if ");
				} else {
					reloadPrevAction = "reloadDateAction('" + localizedReadDateId + "', null, 'PREV');";
					//logger.debug("read date else ");
				}
				//logger.debug("here6");
				xml.append("  <cell><![CDATA["+ (readDate == null ? "" : dateFormat.format(readDate))+"]]></cell>\n");
				xml.append("  <cell><![CDATA[<a id=\"prev" + prefixLocalizedReadDateId + "\" onClick=\"clickDateAction(" + localizedReadDateId + ",'PREV')\"; class=\"validDateAction\" title=\"Previous Day\";><<</a>&nbsp;" +
						"<input type=\"hidden\" id=\"" + readDateId + "\" value=\"" + (readDate == null ? "" : dateFormat.format(readDatePlus1)) + "\"/>" +
						"<input type=\"text\" style= \"height: 15px; padding: 0px\" id=\""+localizedReadDateId+"\" size=\"7\" readonly=\"readonly\" onMouseUp=\"show_cal('" + localizedReadDateId + "', " + readDatePlus1Str + ", serverTodayStr)\" onFocus=\"" + reloadPrevAction + "reloadDateAction('" + localizedReadDateId + "', serverTodayStr, 'NEXT');\" onBlur=\"this.className='';\" />" +
						"<img id=\"img" + prefixLocalizedReadDateId + "\" class=\"cal_date ui_icon_sprite calendar-icon\" src=\"" + transparentImageSrc + "\" onClick=\"show_cal('" + localizedReadDateId + "', " + readDatePlus1Str + ", serverTodayStr)\"/>&nbsp;" +
						"<a id=\"next" + prefixLocalizedReadDateId + "\" onClick=\"clickDateAction(" + localizedReadDateId + ",'NEXT')\"; class=\"validDateAction\" title=\"Next Day\";>>></a>]]></cell>\n");

				//String lastPageCount = (asset.getLastPageCount()== null? "": asset.getLastPageCount());
				logger.debug("lastPageCount "+lastPageCount);
				xml.append("  <cell><![CDATA[<label id=\""+ltpcCount+"\" value=\""+ lastPageCount+"\" />"+lastPageCount+"</label>]]></cell>\n");
				xml.append("  <cell><![CDATA[<input type=\"text\" style= \"height:15px;padding: 0px\" size=\"7\" onclick=\"focusOnInput('"+inputCountId+"');\" id=\""+inputCountId+"\"/>]]></cell>\n");
				//if(asset.getColorCapableFlag()!= null && asset.getColorCapableFlag()){
				//brmandal changed for Page count changes - MPS phase 2.1
				if((lastColorPageCount != null) && (lastColorPageCount.length() > 0) || (!lastColorPageCount.isEmpty())){
					//String lastColorPageCount = (asset.getLastColorPageCount()== null? "": asset.getLastColorPageCount());
					xml.append("  <cell><![CDATA[<label id=\""+lastColorCountId+"\" value=\""+ lastColorPageCount+"\" />"+lastColorPageCount+"</label>]]></cell>\n");
					xml.append("  <cell><![CDATA[<input type=\"text\" style= \"height:15px;padding: 0px\"  size=\"7\" onclick=\"focusOnInput('"+inputColorCountId+"');\" id=\""+inputColorCountId+"\"/>]]></cell>\n");
				}else{
					xml.append("  <cell>---</cell>\n");
					xml.append("  <cell>---</cell>\n");
				}
				//logger.debug("here 7 ");
				//Fix by Service Portal AMS LEX:AIR00062572
				xml.append("  <cell><![CDATA[ <button name=\"btn_save\"  type=\"button\" class=\"button\" id=\""+buttonId+"\" onclick=\"updateMeterReadAsset('"+XMLEncodeUtil.escapeXML(asset.getSerialNumber())+"','"+XMLEncodeUtil.escapeXML(asset.getAssetId())+"','"+ rowId +"');\" value=\"save\" ><span>"+btnMsg+"</span></button>" +
						"&nbsp;<img id = '"+imgUpdatingId+"' style='cursor:Hand;cursor:pointer;display:none' src='" + imageUpdatingSrc + "' />]]></cell>\n");
				//brmandal added the below line for Page count changes - MPS phase 2.1
				xml.append("  <cell><![CDATA["+ XMLEncodeUtil.escapeXML(installAddressOfficeNumber)+"]]></cell>\n");
				xml.append("  <cell><![CDATA["+ XMLEncodeUtil.escapeXML(installAddressCity)+"]]></cell>\n");
				xml.append("  <cell><![CDATA["+ XMLEncodeUtil.escapeXML(installAddressState) +"]]></cell>\n");
				xml.append("  <cell><![CDATA["+ XMLEncodeUtil.escapeXML(installAddressProvince) +"]]></cell>\n");
				//brmandal added the below 2 lines for Page count changes - MPS phase 2.1
				xml.append("  <cell><![CDATA["+ XMLEncodeUtil.escapeXML(installAddressCounty)+"]]></cell>\n");
				xml.append("  <cell><![CDATA["+ XMLEncodeUtil.escapeXML(installAddressDistrict)+"]]></cell>\n");
				xml.append("  <cell><![CDATA["+ XMLEncodeUtil.escapeXML(installAddressCountry) +"]]></cell>\n");
				xml.append("  <cell><![CDATA["+ XMLEncodeUtil.escapeXML(asset.getHostName()) +"]]></cell>\n");
				xml.append("  <cell><![CDATA["+ XMLEncodeUtil.escapeXML(asset.getMacAddress()) +"]]></cell>\n");
				xml.append("  <cell><![CDATA["+ XMLEncodeUtil.escapeXML(asset.getDeviceTag()) +"]]></cell>\n");
				xml.append("  <cell><![CDATA["+ XMLEncodeUtil.escapeXML(asset.getModelNumber()) +"]]></cell>\n");
				xml.append("  <cell><![CDATA["+ XMLEncodeUtil.escapeXML(asset.getProductTLI()) +"]]></cell>\n");
				xml.append("  <cell><![CDATA["+ XMLEncodeUtil.escapeXML(asset.getPhysicalLocationAddress().getPhysicalLocation1()) +"]]></cell>\n");
				xml.append("  <cell><![CDATA["+ XMLEncodeUtil.escapeXML(fName) +"]]></cell>\n");
				xml.append("  <cell><![CDATA["+ XMLEncodeUtil.escapeXML(lName) +"]]></cell>\n");
				xml.append(" </row>\n");
				i ++;
			}
		}
			
		xml.append("</rows>\n");
		return xml.toString();
	}

	/**
	 * @param list 
	 * @param pageCountsStatusMap 
	 * @return String 
	 */
	//Changed for CI7 BRD 14-06-04
	public String convertMeterReadStatusToXML(List<MeterReadStatus> list, Map<String, String> pageCountsStatusMap) {
		StringBuffer xml = new StringBuffer("<?xml version=\"1.0\" ?>\n");
		xml.append("<rows>\n");
		int i = 0;
		if (list != null){
			for (MeterReadStatus meterReadStatus : list) {
				xml.append(" <row id=\""+i+"\">\n");
				xml.append("  <cell><![CDATA["+ formatAttachmentName(meterReadStatus.getAttachmentName())+".csv]]></cell>\n");
				xml.append("  <cell><![CDATA["+ meterReadStatus.getSize() +"]]></cell>\n");
				xml.append("  <cell><![CDATA["+ meterReadStatus.getSubmittedOn() +"]]></cell>\n");
				xml.append("  <cell><![CDATA["+ meterReadStatus.getCompletedOn() +"]]></cell>\n");
				xml.append("<cell><![CDATA[<a href=\"###\" onClick=\"outPutFile('"+meterReadStatus.getAttachmentName()+"')\">" + pageCountsStatusMap.get(meterReadStatus.getStatus()) + "</a>]]></cell>\n");
				xml.append("  <cell>"+ meterReadStatus.getComment() +"</cell>\n");
				xml.append(" </row>\n");
				i ++;
			}			
		}
		xml.append("</rows>\n");
		return xml.toString();
	}

	/**
	 * @param attachmentName 
	 * @return String 
	 */
	private String formatAttachmentName(String attachmentName){
		String[] names = attachmentName.split("~");
		return names[1];
	}

	/**
	 * @param notificationList 
	 * @param totalCount 
	 * @param posStart 
	 * @param contextPath 
	 * @return String 
	 */
	public String convertNotificationListToXML(List<Notification> notificationList,
			int totalCount, int posStart, String contextPath) {


		StringBuffer xml = new StringBuffer("<?xml version=\"1.0\" ?>\n");
		xml.append("<rows total_count=\""+totalCount+"\" pos=\""+posStart+"\">\n");
		int i = 0;
		String imageArrowUpSrc = contextPath + IMAGE_PATH + ARROW_UP_FILE_NAME;
		String imageArrowDownSrc = contextPath + IMAGE_PATH + ARROW_DOWN_FILE_NAME;
		//String imageDeleteSrc = contextPath + IMAGE_PATH + DELETE_FILE_NAME;
		String imageTransparentSrc = contextPath + IMAGE_PATH + TRANSPARENT_FILE_NAME;
		String imageArrowUpGrayedSrc = contextPath + IMAGE_PATH + ARROW_UP_GRAYED_FILE_NAME;
		String imageArrowDownGrayedSrc = contextPath + IMAGE_PATH + ARROW_DOWN_GRAYED_FILE_NAME;
		for (Notification notification : notificationList) {
			xml.append(" <row id=\""+ (posStart + i)+"\">\n");			
			xml.append("  <cell><![CDATA[<a href=\"###\" onClick=\"viewNotificationDetail(" +
					notification.getNotificationId() + ")\">" + notification.getAdminName() + "</a>]]></cell>\n");

			xml.append("  <cell>"+ notification.getDisplayOrder() +"</cell>\n");
			if (i == 0) {
				xml.append("  <cell><![CDATA[" + IMG_GRAYED_OPEN + imageTransparentSrc +
						"\" class=\"ui_icon_sprite arrow_up-icon\" />&nbsp;");

			} else {
				xml.append("  <cell><![CDATA[" + IMG_OPEN + imageTransparentSrc +
						"\" class=\"ui_icon_sprite arrow_up-icon\" onClick=\"increaseDisplayOrder("+(posStart + i)+")\"/>&nbsp;");
			}
			if (i == totalCount -1) {
				xml.append(IMG_GRAYED_OPEN + imageTransparentSrc + "\" class=\"ui_icon_sprite arrow_down-icon\" />&nbsp;");
			} else {
				xml.append(IMG_OPEN + imageTransparentSrc +
                "\" class=\"ui_icon_sprite arrow_down-icon\" onClick=\"decreaseDisplayOrder("+(posStart + i)+")\"/>&nbsp;");
			}
			xml.append(IMG_OPEN + imageTransparentSrc +
                    "\" class=\"ui_icon_sprite trash-icon\" onClick=\"deleteNotification(" + notification.getNotificationId()+",&quot;"+notification.getAdminName() + "&quot;)\"/>]]></cell>\n");
			xml.append("  <cell>"+ dateFormat.format(notification.getDisplayDate()) +"</cell>\n");
			xml.append("  <cell>"+ dateFormat.format(notification.getRemoveDate()) +"</cell>\n");
			xml.append(" </row>\n");
			i ++;
		}
		xml.append("</rows>\n");
		return xml.toString();
	}

	/**
	 * @param siebelLocalizations 
	 * @param request 
	 * @param response 
	 * @return String 
	 */
	public String siebelLocalizations2XML(
			List<SiebelLocalization> siebelLocalizations, ResourceRequest request, ResourceResponse response) {

		StringBuffer buffer = new StringBuffer(200);
		buffer.append("<rows total_count=\"").append(siebelLocalizations.size()).append("\">\n");
		int i = 0;
		PortletURL detailsURL = response.createActionURL();
		detailsURL.setParameter("action", "details");

		//String imgPath = request.getContextPath() + "/images/delete.png";
		String imgPath = request.getContextPath() + "/images/transparent.png";
		
		for (SiebelLocalization siebelLocalization : siebelLocalizations) {
			rowLead(buffer, i);

			appendCell(buffer, getOptionDisplayType(siebelLocalization.getOptionType()));
			Integer id = siebelLocalization.getSiebelLocalizationId();

			cellLead(buffer);
			{
				detailsURL.setParameter("id", id.toString());
				buffer.append("<![CDATA[<a href=\"").append(detailsURL.toString()).append("\">");
				buffer.append(siebelLocalization.getSiebelValue());
				buffer.append("</a>]]>");
			}
			cellTail(buffer);

			Boolean flag = siebelLocalization.getShowEntitlementFlag();
			appendBooleanCell(buffer, flag, locale);
			
			String partnerType = siebelLocalization.getPartnerType();
			Boolean directPartnerFlag = null;
			if (SiebelLocalizationOptionEnum.PARTNER_SERVICE_REQUEST_STATUS.getValue().equals(siebelLocalization.getOptionType()) ||
					SiebelLocalizationOptionEnum.PARTNER_SERVICE_REQUEST_SUBSTATUS.getValue().equals(siebelLocalization.getOptionType()) && null !=siebelLocalization.getPartnerType()) {
				if(partnerType.equals(LexmarkConstants.PARTNER_TYPE_DIRECT) ||
						partnerType.equals(LexmarkConstants.PARTNER_TYPE_BOTH)) {
					directPartnerFlag = true;
				} else {
					directPartnerFlag = false;
				}
			}
			appendBooleanCell(buffer, directPartnerFlag, locale);
			
			Boolean indirectPartnerFlag = null;
			if (SiebelLocalizationOptionEnum.PARTNER_SERVICE_REQUEST_STATUS.getValue().equals(siebelLocalization.getOptionType()) ||
					SiebelLocalizationOptionEnum.PARTNER_SERVICE_REQUEST_SUBSTATUS.getValue().equals(siebelLocalization.getOptionType())) {
				if (LexmarkConstants.PARTNER_TYPE_INDIRECT.equals(partnerType) ||
						LexmarkConstants.PARTNER_TYPE_BOTH.equals(partnerType)) {
					indirectPartnerFlag = true;
				} else {
					indirectPartnerFlag = false;
				}
			}
			appendBooleanCell(buffer, indirectPartnerFlag, locale);

			
			Integer order = siebelLocalization.getStatusOrder();
			appendCell(buffer, order == null? localizeMessage("serviceRequest.label.n/a", locale) : String.valueOf(order));
			
			cellLead(buffer);
			{
				buffer.append("<![CDATA[<a href=\"javascript:doDelete(").append(siebelLocalization.getSiebelLocalizationId()).append(")\">");
				buffer.append("<img class=\"ui_icon_sprite trash-icon\" src=\"").append(imgPath).append("\">");
				buffer.append("</a>]]>");
			}
			cellTail(buffer);
			rowTail(buffer);
			i++;
		}
		buffer.append("</rows>");
		return buffer.toString();
	}
	
	/**
	 * @param buffer 
	 * @param flag 
	 * @param locale 
	 */
	private void appendBooleanCell(StringBuffer buffer, Boolean flag, Locale locale) {
		appendCell(buffer, flag == null ? localizeMessage("serviceRequest.label.n/a", locale) :
			flag ? localizeMessage("button.yes", locale) : localizeMessage("button.no", locale));
	}
	
	
	/**
	 * @param serviceRequestList 
	 * @param totalCount 
	 * @param startRecordNumber 
	 * @param timezoneOffset 
	 * @return String 
	 */
	public String buildXMLForAllRequestType(List<ServiceRequest> serviceRequestList, int totalCount, int startRecordNumber, float timezoneOffset){
		logger.debug("buildXMLForAllRequestType Enter ----------------------");
		StringBuffer xml = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		xml = xml.append("<rows total_count=\"" + totalCount + "\" pos=\"" + startRecordNumber + "\">\n");
		for (int i = 0; i < serviceRequestList.size(); i++) {
			try{
		    ServiceRequest serviceRequest = serviceRequestList.get(i);
		    xml = xml.append(" <row id=\"" + (startRecordNumber + i) + "\">\n");
		    
		    //Changed
		    ListOfValues reqTypeLOV = serviceRequest.getServiceRequestType();
		   
		    xml.append("<cell><![CDATA[<a href=\"###\" id=\"srNumber\" onClick=\"hideSelect();onSRNmbrClick('" + serviceRequest.getServiceRequestNumber() 
		    		+ "','" + reqTypeLOV.getValue()+ "','" + serviceRequest.getStatusType().getValue()
		    		+"','"+serviceRequest.getSubArea().getValue()+"','"+serviceRequest.getArea().getValue()+"','"+serviceRequest.getCoveredService()+"')\">" + 
					   serviceRequest.getServiceRequestNumber() + "</a>]]></cell>\n");
		    
		    
		   // logger.debug(" serviceRequest.getServiceRequestDate() -->"+ serviceRequest.getServiceRequestDate() );
			if ( serviceRequest.getServiceRequestDate() != null){	
				//logger.debug("ServiceRequestDate Before adjust--->"+DateLocalizationUtil.localizeDateTime(serviceRequest.getServiceRequestDate(), true, locale));
				//TimezoneUtil.adjustDate(serviceRequest.getServiceRequestDate(),(timezoneOffset));
				
				String dt =  DateLocalizationUtil.localizeDateTime(DateUtil.adjustDateWithOffset(serviceRequest.getServiceRequestDate(), timezoneOffset), true, locale);
				//logger.debug("ServiceRequestDate After adjust--->"+dt);
				xml.append("  <cell><![CDATA["+ dt  +"]]></cell>\n");
				
			}else{
				xml.append("  <cell><![CDATA[ -- -- -- ]]></cell>\n");
			}
			//changes for defect 8083 , MPS2.1 
			ListOfValues areaLOV = serviceRequest.getArea();
			ListOfValues subAreaLOV = serviceRequest.getSubArea();
			//logger.debug("Localozed SR type--->"+ localizeServiceRequestType(reqTypeLOV.getValue(),locale));
			if(reqTypeLOV.getValue().equalsIgnoreCase("Fleet Management")&& (areaLOV.getValue().equalsIgnoreCase("HW Order")|| (areaLOV.getValue().equalsIgnoreCase("Hardware-Ship and Install") && subAreaLOV.getValue().equalsIgnoreCase("BAU")))){
				xml.append("  <cell><![CDATA["+ getLocalizeSiebelValue(REQUESTTYPEHARDWARE,ChangeMgmtConstant.LOV_SR_TYPE,locale) +"]]></cell>\n");
			}else{
				xml.append("  <cell><![CDATA["+ getLocalizeSiebelValue(reqTypeLOV.getValue(),ChangeMgmtConstant.LOV_SR_TYPE,locale) +"]]></cell>\n");
			}
			//End changes for defect 8083 , MPS2.1 
			//String requestArea = "Localized Area";//localizeRequestArea(areaLOV, columnPattern, locale);					
			//xml.append("  <cell><![CDATA["+ areaLOV.getValue() +"]]></cell>\n");ChangeMgmtConstant
			xml.append("  <cell><![CDATA["+getLocalizeSiebelValue(areaLOV.getValue(),SiebelLocalizationOptionEnum.REQUEST_AREA.getValue(),locale) +"]]></cell>\n");
			//xml.append("  <cell><![CDATA["+ serviceRequest.getServiceRequestStatus() +"]]></cell>\n");	
			
			//xml.append("  <cell><![CDATA["+ serviceRequest.getStatusType().getValue() +"]]></cell>\n");
			xml.append("  <cell><![CDATA["+getLocalizeSiebelValue( serviceRequest.getStatusType().getValue(),SiebelLocalizationOptionEnum.REQUEST_STATUS.getValue(),locale) +"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ serviceRequest.getAsset().getSerialNumber() +"]]></cell>\n");
			if(reqTypeLOV.getValue().equalsIgnoreCase(ChangeMgmtConstant.SRTYPE_CHANGEMANAGEMENT) || 
					reqTypeLOV.getValue().equalsIgnoreCase(ChangeMgmtConstant.SRTYPE_SUPPLYMANAGEMENT) ||
							reqTypeLOV.getValue().equalsIgnoreCase(ChangeMgmtConstant.SRTYPE_RETURNSUPPLIES)){
				
				xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getCostCenter() )+"]]></cell>\n");
			}else{
				xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getAsset().getAssetCostCenter() )+"]]></cell>\n");
			}
			
			// - Added for Mps Phase 2
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getAccountName() )+"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getPrimaryContact().getFirstName() )+"]]></cell>\n");			
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getPrimaryContact().getLastName() )+"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getPrimaryContact().getEmailAddress()) +"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getPrimaryContact().getWorkPhone()) +"]]></cell>\n");			
			
			xml.append("  <cell><![CDATA["+replaceNullWithBlankString(serviceRequest.getRequestor().getFirstName() )+"]]></cell>\n");				
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getRequestor().getLastName() )+"]]></cell>\n");
			xml.append("  <cell><![CDATA["+replaceNullWithBlankString(serviceRequest.getRequestor().getEmailAddress() )+"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getRequestor().getWorkPhone() )+"]]></cell>\n");
			
			
			
			xml = xml.append(" </row>\n");
			}catch(Exception ex)
			{
				logger.debug("Exception "+ex.getMessage());
			}
			    
		}
		
		
		xml = xml.append("</rows>\n");
		logger.debug("XmlOutputGenerator.buildXMLForAllRequestType.EXIT----------------");
		
		return xml.toString();
	}
	
	/**
	 * @param serviceRequestList 
	 * @param totalCount 
	 * @param startRecordNumber 
	 * @param timezoneOffset 
	 * @return String 
	 */
	public String buildXMLForChangeRequestType(List<ServiceRequest> serviceRequestList, int totalCount, int startRecordNumber, float timezoneOffset){
		logger.debug("XmlOutputGenerator.buildXMLForChangeRequestType.ENTER---------------");
		StringBuffer xml = new StringBuffer("<?xml version=\"1.0\" ?>\n");
		xml = xml.append("<rows total_count=\"" + totalCount + "\" pos=\"" + startRecordNumber + "\">\n");
		for (int i = 0; i < serviceRequestList.size(); i++) {
		    ServiceRequest serviceRequest = serviceRequestList.get(i);
		    xml = xml.append(" <row id=\"" + (startRecordNumber + i) + "\">\n");
		    
		    //Changed
		    xml.append("<cell><![CDATA[<a id=\"srNumber\" href=\"###\" onClick=\"hideSelect();onSRNmbrClick('" + serviceRequest.getServiceRequestNumber()
		    		+ "','Fleet Management','"+ (serviceRequest.getStatusType()==null ? "":replaceNullWithBlankString(serviceRequest.getStatusType().getValue()))
		    		+"','"+replaceNullWithBlankString(serviceRequest.getSubArea().getValue())+"','"+replaceNullWithBlankString(serviceRequest.getArea().getValue())+"','"+replaceNullWithBlankString(serviceRequest.getCoveredService())+"')\">" + 
					   serviceRequest.getServiceRequestNumber() + "</a>]]></cell>\n");
		    
			if ( serviceRequest.getServiceRequestDate() != null){					   
				//logger.debug("ServiceRequestDate Before adjust--->"+DateLocalizationUtil.localizeDateTime(serviceRequest.getServiceRequestDate(), true, locale));
				//TimezoneUtil.adjustDate(serviceRequest.getServiceRequestDate(),(timezoneOffset));
				//String dt =  DateLocalizationUtil.localizeDateTime(serviceRequest.getServiceRequestDate(), true, locale);
				String dt =  DateLocalizationUtil.localizeDateTime(DateUtil.adjustDateWithOffset(serviceRequest.getServiceRequestDate(), timezoneOffset), true, locale);
				//logger.debug("ServiceRequestDate After adjust--->"+dt);
				xml.append("  <cell><![CDATA["+ dt  +"]]></cell>\n");
			}else{
				xml.append("  <cell><![CDATA[ -- -- -- ]]></cell>\n");
			}
			
			
			ListOfValues areaLOV = serviceRequest.getArea();
			//String requestArea = "Localized Area";//localizeRequestArea(areaLOV, columnPattern, locale);					
			//xml.append("  <cell><![CDATA["+ areaLOV.getValue() +"]]></cell>\n");
		//	logger.debug("areaLOV---->["+areaLOV.getValue()+"]");
			xml.append("  <cell><![CDATA["+getLocalizeSiebelValue(areaLOV.getValue(),SiebelLocalizationOptionEnum.REQUEST_AREA.getValue(),locale) +"]]></cell>\n");
			ListOfValues subAreaLOV = serviceRequest.getSubArea();
			//logger.debug("subAreaLOV---->["+subAreaLOV.getValue()+"]");
			//String requestSubArea = "Localized SubArea";//localizeRequestSubArea(subAreaLOV, columnPattern, locale);	
			xml.append("  <cell><![CDATA["+getLocalizeSiebelValue(subAreaLOV.getValue(),SiebelLocalizationOptionEnum.REQUEST_SUBAREA.getValue(),locale) +"]]></cell>\n");
			//xml.append("  <cell><![CDATA["+ subAreaLOV.getValue() +"]]></cell>\n");
			
			xml.append("  <cell><![CDATA["+ (serviceRequest.getAsset()==null?"":replaceNullWithBlankString(serviceRequest.getAsset().getSerialNumber())) +"]]></cell>\n");
			
			
		
			
			
			xml.append("  <cell><![CDATA["+ (serviceRequest.getAsset()==null?"":replaceNullWithBlankString(serviceRequest.getAsset().getDeviceTag()))+"]]></cell>\n");
			//xml.append("  <cell><![CDATA["+ serviceRequest.getServiceRequestStatus() +"]]></cell>\n");
			//xml.append("  <cell><![CDATA["+replaceNullWithBlankString (serviceRequest.getStatusType().getValue()) +"]]></cell>\n");
			//xml.append("  <cell><![CDATA["+serviceRequest.getStatusType()==null?"":getLocalizeSiebelValue(serviceRequest.getStatusType().getValue(),SiebelLocalizationOptionEnum.REQUEST_STATUS.getValue(),locale) +"]]></cell>\n");
			xml.append("  <cell><![CDATA["+(serviceRequest.getStatusType()==null?"":replaceNullWithBlankString(serviceRequest.getStatusType().getValue()) )+"]]></cell>\n");
			//customerReportingName fetched for CI Defect #10118
			xml.append("  <cell><![CDATA["+ (serviceRequest.getAsset()==null?"":replaceNullWithBlankString(serviceRequest.getAsset().getCustomerReportingName())) +"]]></cell>\n");
			xml.append("  <cell><![CDATA["+replaceNullWithBlankString (serviceRequest.getHelpdeskReferenceNumber()) +"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getCostCenter() )+"]]></cell>\n");
			// - Account name : Added for Mps Phase 2
			
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getAccountName())+"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getServiceAddress().getAddressName()) +"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getServiceAddress().getStoreFrontName()) +"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getServiceAddress().getAddressLine1()) +"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getServiceAddress().getOfficeNumber()) +"]]></cell>\n");//For MPS Phase 2.1 
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getServiceAddress().getCity()) +"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getServiceAddress().getState()) +"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getServiceAddress().getProvince()) +"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getServiceAddress().getCounty()) +"]]></cell>\n");//For MPS Phase 2.1 
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getServiceAddress().getDistrict()) +"]]></cell>\n");//For MPS Phase 2.1 
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getServiceAddress().getCountry()) +"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getServiceAddress().getPostalCode()) +"]]></cell>\n");			
			
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getPrimaryContact().getFirstName() )+"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getPrimaryContact().getLastName() )+"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getPrimaryContact().getEmailAddress()) +"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getPrimaryContact().getWorkPhone()) +"]]></cell>\n");
			
			xml.append("  <cell><![CDATA["+replaceNullWithBlankString(serviceRequest.getRequestor().getFirstName() )+"]]></cell>\n");				
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getRequestor().getLastName() )+"]]></cell>\n");
			xml.append("  <cell><![CDATA["+replaceNullWithBlankString(serviceRequest.getRequestor().getEmailAddress() )+"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getRequestor().getWorkPhone() )+"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getCoveredService() )+"]]></cell>\n");			
			xml = xml.append(" </row>\n");
			    
		}
		
		
		xml = xml.append("</rows>\n");
		logger.debug("XmlOutputGenerator.buildXMLForChangeRequestType.EXIT---------------");
		return xml.toString();
	}
	
	
	
	
	
	/**
	 * @param serviceRequestList 
	 * @param totalCount 
	 * @param startRecordNumber 
	 * @param timezoneOffset 
	 * @param contextPath 
	 * @return String 
	 */
	public String buildXMLForSupplyRequestType(List<ServiceRequest> serviceRequestList, int totalCount, int startRecordNumber, float timezoneOffset, String contextPath){
		logger.debug("XmlOutputGenerator.buildXMLForSupplyRequestType.ENTER---------------");
		StringBuffer xml = new StringBuffer("<?xml version=\"1.0\" ?>\n");
		xml = xml.append("<rows total_count=\"" + totalCount + "\" pos=\"" + startRecordNumber + "\">\n");
		for (int i = 0; i < serviceRequestList.size(); i++) {
		    ServiceRequest serviceRequest = serviceRequestList.get(i);
		    xml = xml.append(" <row id=\"" + (startRecordNumber + i) + "\">\n");
		    
		    if(serviceRequest.getExpediteOrder() != null && serviceRequest.getExpediteOrder().booleanValue()){
		    	xml.append("<cell><![CDATA[<img src='"+contextPath+expaditeImgSrc+"' alt=\"Expedited Request\" width=\"16\" height=\"16\" />]]></cell>");
		    }else{
		    	 xml.append("  <cell><![CDATA[ ]]></cell>\n");
		    }
		   
		    String area = serviceRequest.getArea().getValue();
		    logger.debug("XmlOutputGenerator.buildXMLForSupplyRequestType.area--->"+area);
		    
		    List<Part> partList=  serviceRequest.getParts();
		    
		    if((area!=null && area.trim().equalsIgnoreCase("Consumables Return")) || (partList.isEmpty())){
		    	 xml.append("  <cell><![CDATA[ ]]></cell>\n");
		    }else{
					    
		     xml.append("<cell><![CDATA[<div class=\"dhx_sub_row\"><table class=\"displayGrid\">" 
		    + "<thead><tr>" 
			+ "<th class=\"w100\">"+PropertiesMessageUtil.getPropertyMessage(BUNDLENAME_BUTTON_MSG, "requestInfo.heading.partNumber", locale)+"</th>"	
			+ "<th>"+PropertiesMessageUtil.getPropertyMessage(BUNDLENAME_BUTTON_MSG, "requestInfo.heading.description", locale)+"</th>"	
			+ "<th class=\"w150\">"+PropertiesMessageUtil.getPropertyMessage(BUNDLENAME_BUTTON_MSG, "requestInfo.heading.partType", locale)+"</th>"	
			+ "<th class=\"w80 right\">"+PropertiesMessageUtil.getPropertyMessage(BUNDLENAME_BUTTON_MSG, "requestInfo.heading.quantity", locale)+"</th>"				
			+"</tr></thead><tbody>") ;
			
			//List<Part> partList=  serviceRequest.getParts();
			int count = 0;
			for(Part part:partList){
				//logger.debug("[PartNumber|Description|PartType|OrderQuantity]:"+part.getPartNumber()+"|"+part.getDescription()+"|"+part.getPartType()+"|"+part.getOrderQuantity());
				 
					if(count % 2 == 0){
						xml.append("<tr class=\"altRow\">") ;
					}else{
						xml.append("<tr>") ;
					}
					logger.debug("Implicit flag-->"+part.getImplicitFlag());
					logger.debug("Message -->"+PropertiesMessageUtil.getPropertyMessage(BUNDLENAME_BUTTON_MSG, LEXMARK_RECOMENDED_MESSAGEKY, locale));
					
					if(part.getImplicitFlag() != null && part.getImplicitFlag()){
						
						xml.append("<td></td>"	
								+ "<td>"+PropertiesMessageUtil.getPropertyMessage(BUNDLENAME_BUTTON_MSG, LEXMARK_RECOMENDED_MESSAGEKY, locale)+"</td>"	
								+ "<td>"+part.getPartType()+"</td>"	
								+ "<td class=\"right\">"+part.getOrderQuantity()+"</td>"				
								+"</tr>") ;
					}else{
						xml.append("<td>"+part.getPartNumber()+"</td>"	
									+ "<td>"+part.getDescription()+"</td>"	
									+ "<td>"+part.getPartType()+"</td>"	
									+ "<td class=\"right\">"+part.getOrderQuantity()+"</td>"				
									+"</tr>") ;
					}
				count ++ ;
			}
			
			xml.append("</table>]]></cell>\n");
		    }
		   //Changed
		    xml.append("<cell><![CDATA[<a href=\"###\" id=\"srNumber\" onClick=\"hideSelect();onSRNmbrClick('" + serviceRequest.getServiceRequestNumber() 
		    		+ "','Consumables Management','"+serviceRequest.getStatusType().getValue()
		    		+"','"+serviceRequest.getSubArea().getValue()+"','"+serviceRequest.getArea().getValue()+"','')\">" + 
					   serviceRequest.getServiceRequestNumber() + "</a>]]></cell>\n");
		    
		  if ( serviceRequest.getServiceRequestDate() != null){					   
				//logger.debug("ServiceRequestDate Before adjust--->"+DateLocalizationUtil.localizeDateTime(serviceRequest.getServiceRequestDate(), true, locale));
				//TimezoneUtil.adjustDate(serviceRequest.getServiceRequestDate(),(timezoneOffset));
				//String dt =  DateLocalizationUtil.localizeDateTime(serviceRequest.getServiceRequestDate(), true, locale);
			    String dt =  DateLocalizationUtil.localizeDateTime(DateUtil.adjustDateWithOffset(serviceRequest.getServiceRequestDate(), timezoneOffset), true, locale);
				//logger.debug("ServiceRequestDate After adjust--->"+dt);
				xml.append("  <cell><![CDATA["+ dt  +"]]></cell>\n");
			}else{
				xml.append("  <cell><![CDATA[ -- -- -- ]]></cell>\n");
			}
			
			
			ListOfValues areaLOV = serviceRequest.getArea();			
			//String requestArea = "Localized Area";//localizeRequestArea(areaLOV, columnPattern, locale);					
			//xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(areaLOV.getValue()) +"]]></cell>\n");
			logger.debug("Area -->"+areaLOV.getValue());
			xml.append("  <cell><![CDATA["+getLocalizeSiebelValue(areaLOV.getValue(),SiebelLocalizationOptionEnum.REQUEST_AREA.getValue(),locale) +"]]></cell>\n");
			ListOfValues subAreaLOV = serviceRequest.getSubArea();
			//String requestSubArea = "Localized SubArea";//localizeRequestSubArea(subAreaLOV, columnPattern, locale);					
			//xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(subAreaLOV.getValue()) +"]]></cell>\n");
			logger.debug("subAreaLOV -->"+subAreaLOV.getValue());
			xml.append("  <cell><![CDATA["+getLocalizeSiebelValue(subAreaLOV.getValue(),SiebelLocalizationOptionEnum.REQUEST_SUBAREA.getValue(),locale) +"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getAsset().getSerialNumber() )+"]]></cell>\n");
			//- Added for Mps Phase 2
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getAsset().getDeviceTag())+"]]></cell>\n");
			//xml.append("  <cell><![CDATA["+ serviceRequest.getServiceRequestStatus() +"]]></cell>\n");
			//xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getStatusType().getValue()) +"]]></cell>\n");
			xml.append("  <cell><![CDATA["+getLocalizeSiebelValue(serviceRequest.getStatusType().getValue(),SiebelLocalizationOptionEnum.REQUEST_STATUS.getValue(),locale) +"]]></cell>\n");
			//fetched CustomerReportingName for CI Defect #10118
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getAsset().getCustomerReportingName()) +"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getPoNumber() )+"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getCostCenter() )+"]]></cell>\n");
			// - Added for Mps Phase 2
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getAccountName())+"]]></cell>\n");
						
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString( serviceRequest.getServiceAddress().getAddressName()) +"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getServiceAddress().getStoreFrontName()) +"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getServiceAddress().getAddressLine1()) +"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getServiceAddress().getOfficeNumber()) +"]]></cell>\n");//For MPS Phase 2.1 
			
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getServiceAddress().getCity() )+"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getServiceAddress().getState() )+"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getServiceAddress().getProvince()) +"]]></cell>\n");
			// - office number,county,district,account name,device tag : Added for Mps Phase 2
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getServiceAddress().getCounty()) +"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getServiceAddress().getDistrict()) +"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getServiceAddress().getCountry()) +"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getServiceAddress().getPostalCode()) +"]]></cell>\n");
						
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getPrimaryContact().getFirstName() )+"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getPrimaryContact().getLastName() )+"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getPrimaryContact().getEmailAddress()) +"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getPrimaryContact().getWorkPhone()) +"]]></cell>\n");
						
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getRequestor().getFirstName() )+"]]></cell>\n");				
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getRequestor().getLastName() )+"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getRequestor().getEmailAddress() )+"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getRequestor().getWorkPhone() )+"]]></cell>\n");
			
		
			
			xml = xml.append(" </row>\n");
			    
		}
		
		
		xml = xml.append("</rows>\n");
		logger.debug("XmlOutputGenerator.buildXMLForSupplyRequestType.EXIT---------------");
		return xml.toString();
	}
	
	
		
	/**.
	 * This method returns blank string if the parameter passed is null;
	 * @param value 
	 * @return String 
	 * @author Sagar Sarkar 
	 */
	private Object replaceNullWithBlankString( Object value){
		if(value == null ){
			return "";
		}
		return value;
	}
	
	
	
    // Added Dwijen
    /**
     * @param serviceRequestList 
     * @param totalCount 
     * @param startRecordNumber 
     * @param generatorPatterns 
     * @param containsSubRow 
     * @param timezoneOffset 
     * @return String 
     */
    public String convertServiceRequestsToXML(List<ServiceRequest> serviceRequestList, int totalCount, int startRecordNumber, String[] generatorPatterns, boolean containsSubRow, float timezoneOffset) {
	logger.debug("-----------Step 4--convertServiceRequestsToXML started---------[" + System.nanoTime() + "]");
	initialLocalizedMessage();
	String etaValue = "";
	StringBuffer xml = new StringBuffer("<?xml version=\"1.0\" ?>\n");
	xml = xml.append("<rows total_count=\"" + totalCount + "\" pos=\"" + startRecordNumber + "\">\n");
	for (int i = 0; i < serviceRequestList.size(); i++) {
	
	    ServiceRequest sericeRequst = serviceRequestList.get(i);
	    xml = xml.append(" <row id=\"" + (startRecordNumber + i) + "\">\n");
	   
	    if (containsSubRow) {
	    	etaValue = ((sericeRequst.getServiceRequestETA()!=null||sericeRequst.getServiceRequestETA()!="")?sericeRequst.getServiceRequestETA():sericeRequst.getServiceRequestSLA());
	    	
	    	xml.append("<cell><![CDATA[<div><table class=\"displayGrid\">" 
	    		    + "<thead><tr>" 
	    			+ "<th class=\"w150\">"+LB_PROBLEM_DESCRIPTION+"</th>"	
	    			+ "<th class=\"w100\">"+LB_RESOLUTION_CODE+"</th>"
	    			+ "<th class=\"w100\">"+LB_ETA_DETL+"</th>"
	    			+ "<th class=\"w150\">"+LB_SERVICE_LOCATION+"</th>"
	    			+ "<th class=\"w150\">"+LB_PRIMARY_CONTACT+"</th>"
	    			+"</tr></thead><tbody>") ;
	    	xml.append("<tr>") ;
		    	xml.append("<td>"+ 	XMLEncodeUtil.escapeXML(sericeRequst.getProblemDescription())+"</td>");
		    	xml.append("<td>"+ 	XMLEncodeUtil.escapeXML(sericeRequst.getResolutionCode())+"</td>");
		    	xml.append("<td>"+ 	XMLEncodeUtil.escapeXML(etaValue)+"</td>");
		    	xml.append("<td>") ;
			    	xml.append(XMLEncodeUtil.escapeXML(sericeRequst.getServiceAddress().getAddressLine1())+"<br/>");
	    			xml.append(XMLEncodeUtil.escapeXML(sericeRequst.getServiceAddress().getCity())+"<br/>");
					xml.append(XMLEncodeUtil.escapeXML(sericeRequst.getServiceAddress().getState())+" , &nbsp;");
					xml.append(XMLEncodeUtil.escapeXML(sericeRequst.getServiceAddress().getCountry())+"<br/>");
				xml.append("</td>") ;
				xml.append("<td>") ;
			    	xml.append(XMLEncodeUtil.escapeXML(sericeRequst.getPrimaryContact().getFirstName()) + "&nbsp;" + XMLEncodeUtil.escapeXML(sericeRequst.getPrimaryContact().getLastName())+"<br/>");
	    			xml.append(XMLEncodeUtil.escapeXML(sericeRequst.getPrimaryContact().getWorkPhone())+"<br/>");
					xml.append(XMLEncodeUtil.escapeXML(sericeRequst.getPrimaryContact().getEmailAddress())+"<br/>");
				xml.append("</td>") ;
	    	xml.append("</tr>") ;
	    	xml.append("</tbody></table></div>]]></cell>\n");
	    }
	    
	    serviceRequestList.get(i).setServiceRequestStatusDate(serviceRequestList.get(i).getServiceRequestDate());
	    serviceRequestList.get(i).setServiceRequestStatus(serviceRequestList.get(i).getStatusType().getValue());
	    
	    for (String columnPattern : generatorPatterns) {
		
		if ("serviceRequestNumber".equals(columnPattern)) {

			//Changed
		   xml.append("<cell><![CDATA[<a href=\"###\" id=\"srNumber\" onClick=\"hideSelect();onSRNmbrClick('" + XMLEncodeUtil.escapeXML(sericeRequst.getServiceRequestNumber()) + "','Break Fix')\">" + XMLEncodeUtil.escapeXML(sericeRequst.getServiceRequestNumber()) + "</a>]]></cell>\n");
		}else if("assetType".equals(columnPattern)){
			xml = xml.append("<cell><![CDATA[");
			xml = xml.append(sericeRequst.getAsset().getAssetType());
			xml = xml.append("]]></cell>\n");
		}else {
		
		    xml = xml.append("<cell><![CDATA[");
		    String value = BusinessObjectUtil.formatColumn(serviceRequestList.get(i), columnPattern, locale);
		    // If block is to add a label named with date.getTime() in order to perform sorting in the Grid as a String.
		    if ("serviceRequestDate".equals(columnPattern)) {
				if (serviceRequestList.get(i) != null && serviceRequestList.get(i).getServiceRequestDate() != null){
					//xml.append("<label name=" + serviceRequestList.get(i).getServiceRequestDate().getTime() + "/>");
				    String dt =  DateLocalizationUtil.localizeDateTime(DateUtil.adjustDateWithOffset(serviceRequestList.get(i).getServiceRequestDate(), timezoneOffset), true, locale);
				    xml.append("<label name=" + dt + "/>");
				    value = dt;
				}
		    }else{
		    	value = localizeServiceRequestStatus(value, columnPattern, locale);
		    }

		    if (value == null){
			value = "";
		    }
		    xml = xml.append(value);
		    xml = xml.append("]]></cell>\n");
		}
	    }
	    xml = xml.append(" </row>\n");
	}
	xml = xml.append("</rows>\n");
	logger.debug("-----------Step 4--convertServiceRequestsToXML End---------[" + System.nanoTime() + "]");
	return xml.toString();
    }
    // End Dwijen
    
	
	/**
	 * @param reportDefinitionList 
	 * @param contextPath 
	 * @return String 
	 */
	public String convertCustomerReportListToXML(List<ReportDefinition> reportDefinitionList, String contextPath) {
		StringBuffer xml = new StringBuffer("<?xml version=\"1.0\" ?>\n");
		xml.append("<rows total_count=\"" + reportDefinitionList.size() + "\" pos=\"0\">\n");
		int i = 0;
		for (ReportDefinition reportDefinition : reportDefinitionList) {
			xml.append(" <row id=\""+ i +"\">\n");
			xml.append("  <cell><![CDATA[<a name=\"" + reportDefinition.getName() + "\" href=\"javascript:void(0)\" onClick=\"editReportDefinition(" +
					reportDefinition.getId() + ")\">"+ reportDefinition.getName() +"</a>]]></cell>\n");
			xml.append("  <cell>"+ reportDefinition.getRoleCategory().getName() +"</cell>\n");
			xml.append("  <cell>"+ getReportTypeDisplayValue(reportDefinition.getReportType()) +"</cell>\n");
			xml.append("  <cell>"+ (reportDefinition.getIsSchedulable() ? "Yes" : "No") +"</cell>\n");
			xml.append("  <cell><![CDATA[" + IMG_OPEN + contextPath + IMAGE_PATH + WRENCH_IMAGE_FILE_NAME + "\" onClick=\"gotoSchedulerMaintenance('"
					+ reportDefinition.getName() + "')\"/>&nbsp;&nbsp;");
			xml.append(IMG_OPEN + contextPath + IMAGE_PATH + TRANSPARENT_FILE_NAME + "\" class=\"ui_icon_sprite trash-icon\" onClick=\"deleteReportAdministration("
					+ reportDefinition.getId() + ",'" + reportDefinition.getName() + "')\"/>]]></cell>\n");


			xml.append(" </row>\n");
			i ++;
		}
		xml.append("</rows>\n");
		return xml.toString();
	}
	
	
	/**
	 * @param reportListRows 
	 * @param contextPath 
	 * @param response 
	 * @param canDeleteReport 
	 * @return String 
	 */
	public String convertCustomerReportsListWithoutStatusToXML(List<ReportListRow> reportListRows, String contextPath, ResourceResponse response, boolean canDeleteReport){
		StringBuffer xml = new StringBuffer("<?xml version=\"1.0\" ?>\n");
		xml.append("<rows total_count=\"" + reportListRows.size() + "\" pos=\"0\">\n");
		int i = 0;
		for (ReportListRow row : reportListRows) {
			xml.append(" <row id=\""+ i +"\">\n");
			xml.append("  <cell><![CDATA["+ row.getReportDefinitionName() +"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ DateLocalizationUtil.localizeDateTime(row.getLastUpdated(), false, locale) +"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ getReportActionsString(row.getReportCategoryName(), row.getReportDefinitionName(),row, contextPath, canDeleteReport) +"]]></cell>\n");
			xml.append(" </row>\n");
			i++;
		}
		xml.append("</rows>\n");
		return xml.toString();
	}
	
	
	
	/**
	 * @param reportListRows 
	 * @param contextPath 
	 * @param response 
	 * @param canDeleteReport 
	 * @return String 
	 */
	public String convertCustomerReportsListWithStatusToXML(List<ReportListRow> reportListRows, String contextPath, ResourceResponse response, boolean canDeleteReport){
		StringBuffer xml = new StringBuffer("<?xml version=\"1.0\" ?>\n");
		xml.append("<rows total_count=\"" + reportListRows.size() + "\" pos=\"0\">\n");
		int i = 0;
		for (ReportListRow row : reportListRows) {
			xml.append(" <row id=\""+ i +"\">\n");
			xml.append("  <cell><![CDATA["+ row.getReportDefinitionName() +"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ DateLocalizationUtil.localizeDateTime(row.getLastUpdated(), false, locale) +"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ localizeReportStatus(row.getStatus(), locale) +"]]></cell>\n" );
			xml.append("  <cell><![CDATA["+ getReportActionsString(row.getReportCategoryName(), row.getReportDefinitionName(),row, contextPath, canDeleteReport) +"]]></cell>\n");
			xml.append(" </row>\n");
			i++;
		}
		xml.append("</rows>\n");
		
		return xml.toString();
	}
	
	
	/**
	 * @param categoryName 
	 * @param reportDefinitionName 
	 * @param reportRow 
	 * @param contextPath 
	 * @param canDeleteReport 
	 * @return String 
	 */
	private String getReportActionsString(String categoryName, String reportDefinitionName,ReportListRow reportRow, String contextPath, boolean canDeleteReport){
		String html = "<table><tr><td width=''30''>{0}</td><td width=''30''>{1}</td><td width=''30''>{2}</td><td width=''30''>{3}</td></tr></table>";
		String fileLinkPattern = "<a target=''_blank'' title=''{3}'' onclick=\"javascript:downloadReport(''" + categoryName + "'',''" + reportDefinitionName + "'',''{1}'');\"" + " href={0}>" + IMG_GRAYED_OPEN + contextPath + FILE_ICON_PATH +  "{2}\"/>" + "</a>";
		String emptyLinkPattern = "<a title=''{0}'' > </a>";
		String mailLinkPartA = "<a href='#' onclick=\"hideSelect();openSendEmailPage('"; 
		String mailLinkPartB = "')\">" + IMG_GRAYED_OPEN + contextPath + IMAGE_PATH + TRANSPARENT_FILE_NAME + "\"  class=\"ui_icon_sprite email-icon\"/></a>";
		String delLinkPartAPattern = "<a href='#' title=''{0}''onclick=\"deleteReport(";
		String delLinkPartB = ")\">" + IMG_GRAYED_OPEN + contextPath + IMAGE_PATH + TRANSPARENT_FILE_NAME + "\" class=\"ui_icon_sprite trash-icon\" /></a>";
		String[] links = new String[]{" "," "," ", " "};
		String toolTipText = "";
		for(Report report : reportRow.getReportList()){
			String fileLink = "";
			if(report.getFilePath() != null && !report.getFilePath().isEmpty()) {
				String reportFileType = DocumentumWebServiceUtil.getDocumentTypeFromFilePath(report.getFilePath());
				MessageFormat mf = new MessageFormat(fileLinkPattern);
				String[] params = new String[]{
						"\"" + ReportPathUtil.getReportFullURL(report.getFilePath()) + "\"",  
						reportFileType,
						DocumentumWebServiceUtil.getFileTypeIcon(reportFileType),
						report.getTooltipText()};
				
				fileLink = mf.format(params);
				if(LexmarkConstants.PDF_TYPE.equalsIgnoreCase(reportFileType)){
					links[0] = fileLink;
				} else {
					links[1] = fileLink;
				}
				
			} else {
				String[] params = new String[]{report.getTooltipText()};
				MessageFormat mf = new MessageFormat(emptyLinkPattern);
				fileLink = mf.format(params);
				if(LexmarkConstants.PDF_TYPE.equalsIgnoreCase(report.getFileType())){
					links[0] = fileLink;
				} else {
					links[1] = fileLink;
				}
			}

			if(report.getFileObjectId()!= null && !report.getFileObjectId().isEmpty()) {
				links[2] = mailLinkPartA + reportDefinitionName+"','"+report.getFileObjectId() + mailLinkPartB;
			}
			String definitionType = reportRow.getDefinitionType();
			String deleteId = ("MU".equalsIgnoreCase(definitionType)?report.getFileObjectId():report.getJobRunLogId());
			
			if(links[3].trim().length() == 0){
				links[3] = "'" + deleteId + "'";
			}
			else{

				links[3] = links[3] + ", " + "'" + deleteId + "'";
			}
			
			if (!RunLogStatusEnum.PUBLISHOK.getStatusCode().equals(reportRow.getStatus())) {
				toolTipText = report.getTooltipText();
			}
		}
		String[] params = new String[]{toolTipText};
		MessageFormat mfTemp = new MessageFormat(delLinkPartAPattern);
		links[3] = mfTemp.format(params) + links[3] + delLinkPartB;
		// one of report is empty, the status should be pending,  should be no email sending icon.
		if(links[0].startsWith("<a title=") || links[1].startsWith("<a title=")) {
			links[2] = " ";
		}
		
		boolean manualyReport = (reportRow.getDefinitionType()!= null && "MU".equalsIgnoreCase(reportRow.getDefinitionType())); 
		// if don't have publishing role, can not delete manually upload report
		if(!canDeleteReport && manualyReport) {
			links[3] = " ";
		}
		
		MessageFormat mf = new MessageFormat(html);
		return mf.format(links);
	}
	
	
	/**
	 * @param categoryList 
	 * @param contextPath 
	 * @return String 
	 */
	public String convertCategoryListToXML(List<RoleCategory> categoryList, String contextPath) {
		StringBuffer xml = new StringBuffer("<?xml version=\"1.0\" ?>\n");
		int i = 0;
		int totalCount = categoryList.size();
		xml.append("<rows total_count=\"" + totalCount + "\" pos=\"0\">\n");
		for (RoleCategory category : categoryList) {
			xml.append(" <row id=\""+ i +"\">\n");
			xml.append("  <cell><![CDATA["+ category.getName() +"]]></cell>\n");
			xml.append("  <cell>"+ category.getOrderNumber() +"</cell>\n");
			if (i == 0) {
				xml.append("  <cell><![CDATA[" + IMG_GRAYED_OPEN + contextPath + IMAGE_PATH + ARROW_UP_GRAYED_FILE_NAME +
						"\" />&nbsp;");

			} else {
				xml.append("  <cell><![CDATA[" + IMG_OPEN + contextPath + IMAGE_PATH + ARROW_UP_FILE_NAME +
						"\" onClick=\"swapCategoryOrder(" + category.getOrderNumber() + ", -1)\" />&nbsp;");

			}
			if (i == totalCount -1) {
				xml.append(IMG_GRAYED_OPEN + contextPath + IMAGE_PATH + ARROW_DOWN_GRAYED_FILE_NAME +
						"\" />&nbsp;");

			} else {
				xml.append(IMG_OPEN + contextPath + IMAGE_PATH + ARROW_DOWN_FILE_NAME +
						"\" onClick=\"swapCategoryOrder(" + category.getOrderNumber() + ", 1)\" />&nbsp;");

			}
			xml.append("<a href=\"javascript:void(0)\" onClick=\"editCategory('" + category.getCategoryId() + "')\">Edit</a>" + "&nbsp;");
			xml.append(IMG_OPEN + contextPath + IMAGE_PATH + TRANSPARENT_FILE_NAME + "\" class=\"ui_icon_sprite trash-icon\" onClick=\"doDelete('" + category.getCategoryId() + "','" + category.getName()+ "')\"/>]]></cell>\n");
			xml.append("  <cell><![CDATA[");
			xml.append(getAuthorizedRoleNames(category.getRoles()));
			;xml.append("]]></cell>\n");


			xml.append(" </row>\n");
			i ++;
		}
		xml.append("</rows>\n");
		return xml.toString();
	}
	
	/**
	 * @param list
	 * @param contextPath 
	 * @return String 
	 */
	public String convertEmailNotificationListToXML(List<EmailNotification> list ,String contextPath) {
		StringBuffer xml = new StringBuffer("<?xml version=\"1.0\" ?>\n");
		String imageDeleteSrc = contextPath + IMAGE_PATH + DELETE_FILE_NAME;
		String imageTransparentSrc = contextPath + IMAGE_PATH + "transparent.png";
		xml.append("<rows>\n");
		int i = 0;
		for (EmailNotification emailNotification : list) {
			xml.append(" <row id=\""+i+"\">\n");
			xml.append("<cell><![CDATA[<a href=\"###\" onClick=\"viewEmailNotificationDetail(\'" +
					emailNotification.getEmailNotificationId() + "\')\">" + emailNotification.getEmailName() + "</a>]]></cell>\n");

			xml.append("  <cell>"+ (emailNotification.getEmailDescription()==null?"":emailNotification.getEmailDescription()) +"</cell>\n");
			xml.append("  <cell><![CDATA[" +IMG_OPEN + imageTransparentSrc +
                    "\" class=\"ui_icon_sprite trash-icon\" onClick=\"deleteEmailNotification('" + emailNotification.getEmailNotificationId()+"','"+XMLEncodeUtil.escapeJSON(emailNotification.getEmailName()) + "')\"/>]]></cell>\n");
			xml.append(" </row>\n");
			i ++;
		}
		xml.append("</rows>\n");
		return xml.toString();
	}
	
	/**
	 * @param list 
	 * @param contextPath 
	 * @return String 
	 */
	public String convertEmailNotificationDetailToXML(List<EmailNotificationLocale> list ,String contextPath){
		StringBuffer xml = new StringBuffer("<?xml version=\"1.0\" ?>\n");
		String imageDeleteSrc = contextPath + IMAGE_PATH + TRANSPARENT_FILE_NAME;
		xml.append("<rows>\n");
		for (int i = 0;i< list.size() ; i++) {
			xml.append("<row id=\""+i+"\">\n");
			xml.append("<cell>"+list.get(i).getLocale().getSupportedLocaleName()+"</cell>\n");
			if((!"".equals(list.get(i).getEmailSubject()) && list.get(i).getEmailSubject()!=null)
				||(!"".equals(list.get(i).getEmailHeader()) && list.get(i).getEmailHeader()!=null)
				||(!"".equals(list.get(i).getEmailBody()) && list.get(i).getEmailBody()!=null)
				||(!"".equals(list.get(i).getEmailFooter()) && list.get(i).getEmailFooter()!=null)){
				xml.append("<cell>Yes</cell>\n");
				String editMessage = PropertiesMessageUtil.getPropertyMessage(BUNDLENAME_BUTTON_MSG, "serviceRequest.label.edit", locale);
				xml.append("<cell><![CDATA[<a href=\"javascript:void(0)\" onClick=\"editEmailNotificationLocale('" +list.get(i).getEmailNotificationLocaleId()+"')\">"+ editMessage + "</a>&nbsp;"+IMG_OPEN + imageDeleteSrc +
		                "\" onClick=\"deleteEmailNotificationLocale('"+list.get(i).getEmailNotificationLocaleId()+"')\" class=\"ui_icon_sprite trash-icon\" />]]></cell>\n");
			}else{
				String addMessage = PropertiesMessageUtil.getPropertyMessage(BUNDLENAME_BUTTON_MSG, "serviceRequest.label.add", locale);
				xml.append("<cell>No</cell>\n");
				xml.append("<cell><![CDATA[<a href=\"javascript:void(0)\" onClick=\"addEmailNotificationLocale('"+list.get(i).getLocale().getSupportedLocaleId()+"')\">"+ addMessage + "</a>]]></cell>\n");
			}
			xml.append(" </row>\n");
		}
		xml.append("</rows>\n");
		return xml.toString();
	}

	/**
	 * @param result 
	 * @param totalCount 
	 * @param posStart 
	 * @return String 
	 */
	public String convertReportsToXML(ReportListOutput result, int totalCount, int posStart){

		logger.debug("-----------Step 4--convertReportsToXML started---------[" + System.nanoTime() + "]");
		List<ReportListRecord> reportList = result.getReportListRows();
		// Map<String,String> rJMap = result.getRJMap();
		StringBuffer xml = new StringBuffer("<?xml version=\"1.0\" ?>\n");
		xml = xml.append("<rows total_count=\"" + totalCount + "\" pos=\"" + posStart + "\">\n");
		String errorMessage;
		for (int i = 0; i < reportList.size(); i++) {
			xml = xml.append("<row id=\"" + (posStart + i) + "\">\n");
			String userEmail = reportList.get(i).getUserEmail() == null ? "" : reportList.get(i).getUserEmail();
			String runTime = formatDateTime(reportList.get(i).getCreatedDate());
			xml = xml.append("<cell><![CDATA[" + runTime + "]]></cell>\n");
			xml = xml.append("<cell><![CDATA[" + userEmail + "]]></cell>\n");
			xml = xml.append("<cell><![CDATA[" + reportList.get(i).getReportName() + "]]></cell>\n");
			xml = xml.append("<cell><![CDATA[" + reportList.get(i).getStatus() + "]]></cell>\n");
			if (StringUtil.isStringEmpty(reportList.get(i).getErrorMessage())) {
				errorMessage = " ";
			} else {
				errorMessage = reportList.get(i).getErrorMessage();
			}
			xml = xml.append("<cell>" + errorMessage + "</cell>\n");
			xml = xml.append("<cell><![CDATA[<a href=\"javascript:void(0)\" onClick=\"goDetail('" + reportList.get(i).getCreatedDate() + "','" + reportList.get(i).getReportName() + "','"
					+ reportList.get(i).getReportScheduleId() + "')\"; title=\" Batch Id: " + reportList.get(i).getBatchId() + "\">Details</a>]]></cell>\n");
			if (reportList.get(i).getStatus() != null && reportList.get(i).getStatus().equals("Complete")) {
				xml = xml.append(" <cell><![CDATA[ <button class=\"button\" onclick=\"reRun('" + reportList.get(i).getBatchId().toString() + "');\">"
						+ PropertiesMessageUtil.getPropertyMessage(LexmarkSPConstants.MESSAGE_BUNDLE_NAME, "reportAdmin.label.rerun", locale) + "</button>]]></cell>\n");
			} else {
				xml.append("<cell></cell>\n");
			}
			xml = xml.append(" </row>\n");
		}

		xml = xml.append("</rows>\n");
		logger.debug("-----------Step 4--convertReportsToXML End---------[" + System.nanoTime() + "]");
		return xml.toString();}
	
	/**
	 * @param r 
	 * @return String 
	 */
	private String getDetailToolTip(Report r) {
		return "Run Log Id: " +  r.getJobRunLogId();
	}
	
	/**
	 * @param optionType 
	 * @return String 
	 */
	private String getOptionDisplayType(String optionType) {
		for(SiebelLocalization.SiebelLocalizationOptionEnum e:SiebelLocalization.SiebelLocalizationOptionEnum.values()) {
			if(e.getValue().equals(optionType)) {
				return e.getDisplayName();
			}
		}
		return "";
	}

	/**
	 * @param buffer 
	 * @param cellText 
	 */
	private static void appendCell(StringBuffer buffer, String cellText) {
		cellLead(buffer);
		buffer.append(cellText);
		cellTail(buffer);
	}

	/**
	 * @param buffer 
	 * @return String 
	 */
	private static StringBuffer cellTail(StringBuffer buffer) {
		return buffer.append("</cell>\n");
	}

	/**
	 * @param buffer 
	 */
	private static void cellLead(StringBuffer buffer) {
		buffer.append(INDENT).append(INDENT).append("<cell>");
	}

	/**
	 * @param buffer 
	 */
	private static void rowTail(StringBuffer buffer) {
		buffer.append(INDENT).append("</row>\n");
	}

	/**
	 * @param buffer 
	 * @param i 
	 */
	private static void rowLead(StringBuffer buffer, int i) {
		buffer.append(INDENT);
		buffer.append("<row id=\"").append(i).append("\">\n");
	}
	
	/**
	 * initialLocalizedMessage
	 */
	private void initialLocalizedMessage(){
		LB_BOOKMARKED_DEVICE = PropertiesMessageUtil.getPropertyMessage(LexmarkSPConstants.MESSAGE_BUNDLE_NAME,"deviceFinder.label.bookmarkThisDevice", locale);
		//LB_REQUEST_SERVICE = PropertiesMessageUtil.getPropertyMessage(LexmarkSPConstants.MESSAGE_BUNDLE_NAME, "deviceDetail.label.requestService", locale);
		LB_CONTROL_PANEL = PropertiesMessageUtil.getPropertyMessage(LexmarkSPConstants.MESSAGE_BUNDLE_NAME, "deviceDetail.label.controlPanel", locale);
		LB_LOCATION = PropertiesMessageUtil.getPropertyMessage(LexmarkSPConstants.MESSAGE_BUNDLE_NAME, "deviceDetail.label.installAddress", locale);
		//For MPS Phase 2.1 
		LB_BUILDING = PropertiesMessageUtil.getPropertyMessage(LexmarkSPConstants.MESSAGE_BUNDLE_NAME, "requestInfo.addressInfo.label.building", locale);
		LB_FLOOR = PropertiesMessageUtil.getPropertyMessage(LexmarkSPConstants.MESSAGE_BUNDLE_NAME, "requestInfo.addressInfo.label.floor", locale);
		LB_OFFICE = PropertiesMessageUtil.getPropertyMessage(LexmarkSPConstants.MESSAGE_BUNDLE_NAME, "requestInfo.addressInfo.label.office", locale);
		//LB_SUPPORT = PropertiesMessageUtil.getPropertyMessage(LexmarkSPConstants.MESSAGE_BUNDLE_NAME, "deviceDetail.label.support", locale);
		//LB_DOWNLOADS = PropertiesMessageUtil.getPropertyMessage(LexmarkSPConstants.MESSAGE_BUNDLE_NAME, "deviceDetail.label.downloads", locale);
		LB_SUPPORT_DOWNLOADS = PropertiesMessageUtil.getPropertyMessage(LexmarkSPConstants.MESSAGE_BUNDLE_NAME, "deviceDetail.label.supportAndDownloads", locale);
		LB_VIEW_MORE = PropertiesMessageUtil.getPropertyMessage(LexmarkSPConstants.MESSAGE_BUNDLE_NAME, "deviceDetail.label.viewMore", locale);
		LB_INSTALLED_ADDRESS = PropertiesMessageUtil.getPropertyMessage(LexmarkSPConstants.MESSAGE_BUNDLE_NAME, "meterRead.label.installedLocation", locale);
		LB_PHYSICAL_LOCATION = PropertiesMessageUtil.getPropertyMessage(LexmarkSPConstants.MESSAGE_BUNDLE_NAME, "meterRead.label.physicalLocation", locale);
		LB_PROBLEM_DESCRIPTION = PropertiesMessageUtil.getPropertyMessage(LexmarkSPConstants.MESSAGE_BUNDLE_NAME, "serviceRequest.label.problemDescription", locale);
		LB_ETA_DETL = PropertiesMessageUtil.getPropertyMessage(LexmarkSPConstants.MESSAGE_BUNDLE_NAME, "serviceRequest.label.eta", locale);
		LB_SERVICE_LOCATION = PropertiesMessageUtil.getPropertyMessage(LexmarkSPConstants.MESSAGE_BUNDLE_NAME, "serviceRequest.label.serviceLocation", locale);
		LB_PRIMARY_CONTACT = PropertiesMessageUtil.getPropertyMessage(LexmarkSPConstants.MESSAGE_BUNDLE_NAME, "serviceRequest.label.primaryContact", locale);
		LB_RESOLUTION_CODE = PropertiesMessageUtil.getPropertyMessage(LexmarkSPConstants.MESSAGE_BUNDLE_NAME, "serviceRequest.label.resolutionCode", locale);	// added for CI5
		LB_SERVICE_ADDRESS = PropertiesMessageUtil.getPropertyMessage(LexmarkSPConstants.MESSAGE_BUNDLE_NAME, "requestInfo.heading.serviceAddress", locale);
		LB_UNBOOKMARKED_DEVICE = PropertiesMessageUtil.getPropertyMessage(LexmarkSPConstants.MESSAGE_BUNDLE_NAME, "requestInfo.link.unbookmarkThisDevice", locale);
		
		//Added for MPS Device Management
		LB_CREATESERVICE_REQ=PropertiesMessageUtil.getPropertyMessage(LexmarkSPConstants.MESSAGE_BUNDLE_NAME, "deviceDetail.link.createARequest", locale);
		//LB_REMOVE_FROM_BOOKMARK=PropertiesMessageUtil.getPropertyMessage(LexmarkSPConstants.MESSAGE_BUNDLE_NAME,"deviceDetail.label.removeFromBookmark", locale);
		
		LB_DEVICEDESC=PropertiesMessageUtil.getPropertyMessage(LexmarkSPConstants.MESSAGE_BUNDLE_NAME, "deviceDetail.label.deviceDesc", locale);
		LB_CUSTASSETTAG=PropertiesMessageUtil.getPropertyMessage(LexmarkSPConstants.MESSAGE_BUNDLE_NAME, "changemanagement.changeAsset.label.customerAssetTag", locale);
		LB_INSTALLDATE=PropertiesMessageUtil.getPropertyMessage(LexmarkSPConstants.MESSAGE_BUNDLE_NAME, "changemanagement.addAsset.label.installDate", locale);
		LB_IPADDRESS=PropertiesMessageUtil.getPropertyMessage(LexmarkSPConstants.MESSAGE_BUNDLE_NAME, "changemanagement.addAsset.label.ipAddress", locale);
		LB_HOSTNAME=PropertiesMessageUtil.getPropertyMessage(LexmarkSPConstants.MESSAGE_BUNDLE_NAME, "changemanagement.addAsset.label.hostName", locale);
	}
	
	/**
	 * @param statusCode 
	 * @param locale 
	 * @return String 
	 */
	private String localizeReportStatus(String statusCode, Locale locale) {
		return (PropertiesMessageUtil.getPropertyMessage(LexmarkSPConstants.MESSAGE_BUNDLE_NAME,"reportStatus." + statusCode, locale));
	}
	
	/**
	 * @return String 
	 */
	private String getLocalizeSelectButton() {
		return (PropertiesMessageUtil.getPropertyMessage(LexmarkSPConstants.MESSAGE_BUNDLE_NAME,"button.select", locale));
	}
	
	/**
	 * @param attribute 
	 * @param locale 
	 * @return String 
	 */
	private String localizeMessage(String attribute, Locale locale) {
		return (PropertiesMessageUtil.getPropertyMessage(LexmarkSPConstants.MESSAGE_BUNDLE_NAME, attribute, locale));
	}

	/**
	 * @param roleList 
	 * @return String 
	 */
	private String getAuthorizedRoleNames(List<Role> roleList) {
		StringBuffer strBuffer = new StringBuffer();
		int i = 0;
		for (Role role : roleList) {
			if (i > 0) {
				strBuffer.append(",");
			}
			strBuffer.append(role.getName());
			i ++;
		}
		return strBuffer.toString();
	}
	

	
	/**
	 * @param reportType 
	 * @return String 
	 */
	private String getReportTypeDisplayValue(String reportType) {
		for(ReportDefinition.ReportType e : ReportDefinition.ReportType.values()) {
			if(e.getValue().equals(reportType)) {
				return e.getDisplayName();
			}
		}
		return "";
	}
	
	/**
	 * @param date 
	 * @return String 
	 */
	private static String formatDateTime(Date date) {
		if(date == null) {
			return "";
		}
		SimpleDateFormat datetimeFormatter = new SimpleDateFormat(DATE_TIME_FORMAT);
//		datetimeFormatter.setTimeZone(TimeZone.getTimeZone("GMT"));
		return datetimeFormatter.format(date);
	}
	
	/**.
	 * This method retrieves the localized SR type from portal DB 
	 * @param siebelValue 
	 * @param LOVType 
	 * @param locale 
	 * @return String 
	 * @author Sagar Sarkar 
	 */
	private String getLocalizeSiebelValue(String siebelValue, String LOVType, Locale locale){
		logger.debug("getLocalizeSiebelValue.LOVType-->["+LOVType+"] siebelValue-->["+siebelValue+"]"+" local-->"+locale.getLanguage());
		///LocalizedSiebelLOVListContract contract = ContractFactory.createLocalizedSiebelLOVListContract(ChangeMgmtConstant.LOV_SR_TYPE, null, locale);
		LocalizedSiebelValueContract contract = ContractFactory.createLocalizedSiebelValueContract(LOVType,siebelValue,locale);
		
		LocalizedSiebelValueResult result = new LocalizedSiebelValueResult();
		ServiceRequestLocale srlocale = getServiceRequestLocale();
		try {
			result = srlocale.retrieveLocalizedSiebelValue(contract);
		} catch (InfrastructureException ex) {
			
			throw new LGSRuntimeException("Exception while retrieving localized SR_TYPE LOV list from DB",ex);
		}
		ListOfValues localLovValue = result.getLovValue();
		//logger.debug("localLovValue--->"+localLovValue);
		
		if(localLovValue == null || localLovValue.getName()== null || localLovValue.getName().isEmpty()){
			return siebelValue;
		}
		return localLovValue.getName();
	}
	
	/**
	 * @param value 
	 * @param columnPattern 
	 * @param locale 
	 * @return String 
	 */
	private String localizeServiceRequestStatus(String value, String columnPattern, Locale locale){
		  if(columnPattern.equalsIgnoreCase("serviceRequestStatus")){
		   if(value != null){
   
		    LocalizedServiceStatusResult result = new LocalizedServiceStatusResult();
		    LocalizedServiceStatusContract contract = new LocalizedServiceStatusContract();
		    ServiceRequestLocale srlocale = getServiceRequestLocale();
		    contract.setLocale(locale);
		    contract.setSiebelValue(value);
		    try{
		     result = srlocale.retrieveLocalizedServiceStatus(contract);
		     value = result.getLocalizedString();
		    }catch(Exception e){
		     //Do not throw out exception
		     logger.debug("Failed to retrieve localized service request status!"+e.getMessage());
		    }
		   }
		  } else if(columnPattern.equalsIgnoreCase("activityDescription")){
			  if(value != null){

				  LocalizedServiceActivityStatusResult result = new LocalizedServiceActivityStatusResult();
				  LocalizedServiceActivityStatusContract contract = new LocalizedServiceActivityStatusContract();
				  contract.setLocale(locale);
				  contract.setSiebelValue(value);
				  try{
					  result = serviceRequestLocale.retrieveLocalizedServiceActivityStatus(contract);
					  value = result.getLocalizedValue();
				  }catch(Exception e){
					  //Do not throw out exception
					  logger.debug("Failed to retrieve localized service request activity status!"+e.getMessage());
				  }
			  }
		  }
		  return value;
   }
	
//	added for CI5 multiple customer report 
	/**
	 * @param customerNameList 
	 * @param contextPath 
	 * @return String 
	 */
	public String convertCustomerNameListToXML(List<String> customerNameList, String contextPath) {
		StringBuffer xml = new StringBuffer("<?xml version=\"1.0\" ?>\n");
		xml.append("<rows total_count=\"" + customerNameList.size() + "\" pos=\"0\">\n");
		int i = 0;
		for (String customerName : customerNameList) {
			xml.append(" <row id=\""+ i +"\">\n");
			xml.append("  <cell>"+ customerName +"</cell>\n");
			xml.append("  <cell><![CDATA[");
//			xml.append(IMG_OPEN + contextPath + IMAGE_PATH + DELETE_FILE_NAME + "\" onClick=\"deleteRowInGrid()\"/>]]></cell>\n");
			xml.append(IMG_OPEN + contextPath + IMAGE_PATH + TRANSPARENT_FILE_NAME + "\" class=\"ui_icon_sprite trash-icon\" />]]></cell>\n");
			xml.append(" </row>\n");
			i ++;
		}
		xml.append("</rows>\n");
		return xml.toString();
	}
//	end of addition for CI5 multiple customer report 
	
	
	/**
	 * @param list 
	 * @return String 
	 */
	public String convertAssetPartListToXML(List<OrderPart> list) {
		StringBuffer xml = new StringBuffer("<?xml version=\"1.0\" ?>\n");
		xml.append("<rows>\n");
		int i = 0;
		if (list != null){
			for (OrderPart assetPartList : list) {
				xml.append(" <row id=\""+i+"\">\n");
				xml.append("  <cell><![CDATA["+ assetPartList.getPartNumber()+"]]></cell>\n");
				xml.append("  <cell><![CDATA["+ assetPartList.getDescription()+"]]></cell>\n");
				xml.append("  <cell><![CDATA[2]]></cell>\n");
				xml.append(" </row>\n");
				i ++;
			}			
		}
		xml.append("</rows>\n");
		
		return xml.toString();
	}
	/**
	 * @param request 
	 * @param assets 
	 * @param totalCount 
	 * @param posStart 
	 * @param contextPath 
	 * @param type 
	 * @return String
	 */
	public String convertContractedAssetToXML(ResourceRequest request,List<Asset> assets, int totalCount, int posStart, String contextPath, String type) {
		initialLocalizedMessage();
		StringBuffer xml = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		xml.append("<rows total_count=\""+totalCount+"\" pos=\""+posStart+"\">\n");
		int i = 0;
		String imageSrc=contextPath + IMAGE_PATH + "transparent.png";
		String imageName="";
		String provinceOrState="";
		String imageClass="bookmark-star-icon";
		boolean deviceBookmarked = false;
		for (Asset asset : assets) {			
			if(asset.getInstallAddress()!=null){
				if (asset.getInstallAddress().getProvince() != null && asset.getInstallAddress().getProvince().length() > 0) {
					provinceOrState = asset.getInstallAddress().getProvince();
				} else {
					if(asset.getInstallAddress().getState()!=null && asset.getInstallAddress().getProvince().length() > 0){
						provinceOrState = asset.getInstallAddress().getState();
					}
				}
			}
			if (asset.getUserFavoriteFlag()) {
				//imageSrc = contextPath + IMAGE_PATH + FAVORIATE_IMAGE_FILE_NAME;
				imageName = FAVORIATE_IMAGE_FILE_NAME;
				deviceBookmarked = true;
				imageClass="bookmark-star-icon";
			} else {
				//imageSrc = contextPath + IMAGE_PATH + UNFAVORIATE_IMAGE_FILE_NAME;
				imageName = UNFAVORIATE_IMAGE_FILE_NAME;
				deviceBookmarked = false;
				imageClass="removebookmark-icon";
			}
			if(!LexmarkSPConstants.ASSET_LIST_TYPE.equals(type)){
				xml.append("<row id=\""+ (posStart + i)+"\">\n");
			}else{
				xml.append("<row id=\"" +replaceNullWithBlankString(asset.getAssetId()) +"\">\n");
			}
			xml.append("  <cell><![CDATA[ <div class=\"buttonFix\"><input name=\"btn_select\" class=\"button\"  type=\"button\" onclick=\"viewAssetDetails('"
					+replaceNullWithBlankString(asset.getAssetId())+ "','"+replaceNullWithBlankString(asset.getSerialNumber())+"','"+replaceNullWithBlankString(asset.getAssetTag())+"','"+replaceNullWithBlankString(asset.getIpAddress().trim())+"','"+replaceNullWithBlankString(asset.getProductLine())+"','"+replaceNullWithBlankString(asset.getDeviceTag())+"','"+replaceNullWithBlankString(asset.getContractNumber())+"');\" value=\""+ getLocalizeSelectButton() + "\"/></div>]]></cell>\n");
			
			xml.append("<cell><![CDATA[");
			xml.append("<table width='800px' height='140px'>");
			xml.append("<tr><td valign='top' width='35%'>");
			xml.append("<table>");
			xml.append("<tr><td align=\"center\" class='labelBold'>"+replaceNullWithBlankString(asset.getDescriptionLocalLang())+"</td></tr>");
			xml.append("<tr><td align=\"center\" height='80px'><img src='"+replaceNullWithBlankString(asset.getProductImageURL())+"' class='printer' id=\"rowid_"+ (posStart + i)+"\" onError=\"image_error(this.id);\"/></td></tr>");
			//logger.debug("Before entering to the block showBookmarkButton value is "+deviceBookmarked);
			logger.debug("After setting product line and image");
			if(!deviceBookmarked){
			xml.append("<tr><td align='left'  height='22px'><img id = 'starIMG_"+replaceNullWithBlankString(asset.getAssetId())+
					"' style='cursor:Hand;cursor:pointer' onClick=updateUserFavoriteAsset('"+replaceNullWithBlankString(asset.getAssetId())+"') src='" + imageSrc +
					"' name='" + imageName + "' class='ui_icon_sprite "+imageClass+"'/>&nbsp;<a id = 'starTXT_"+replaceNullWithBlankString(asset.getAssetId())+"' href='#' onClick=updateUserFavoriteAsset('"+replaceNullWithBlankString(asset.getAssetId())+"')>"+
					LB_BOOKMARKED_DEVICE+"</a></tr></td>");
			}else{
				xml.append("<tr><td align='left'  height='22px'><img id = 'starIMG_"+replaceNullWithBlankString(asset.getAssetId())+
						"' style='cursor:Hand;cursor:pointer' onClick=updateUserFavoriteAsset('"+replaceNullWithBlankString(asset.getAssetId())+"') src='" + imageSrc + 
						"' name='" + imageName + "' class='ui_icon_sprite "+imageClass+"'/>&nbsp;<a id = 'starTXT_"+replaceNullWithBlankString(asset.getAssetId())+"' href='#' onClick=updateUserFavoriteAsset('"+replaceNullWithBlankString(asset.getAssetId())+"')>"+
						LB_UNBOOKMARKED_DEVICE+"</a></tr></td>");
			}
					
			xml.append("</table>");
			xml.append("</td>");
			xml.append("<td valign='top' width='35%'>");
				xml.append("<table>");
					xml.append("<tr><td align='left' class='labelBold'>"+LB_SERVICE_ADDRESS+":</td></tr>");
					xml.append("<tr><td align='left'>"+replaceNullWithBlankString(asset.getInstallAddress().getAddressLine1())+"</td></tr>");
					xml.append("<tr><td align='left'>"+replaceNullWithBlankString(asset.getInstallAddress().getAddressLine2())+"</td></tr>");
					xml.append("<tr><td align='left'>"+replaceNullWithBlankString(asset.getInstallAddress().getAddressLine3())+"</td></tr>");
					xml.append("<tr><td align='left'>"+replaceNullWithBlankString(asset.getInstallAddress().getCity())+"&nbsp;" + provinceOrState + "," + replaceNullWithBlankString(asset.getInstallAddress().getPostalCode())+"</td></tr>");
					xml.append("<tr><td align='left'>"+replaceNullWithBlankString(asset.getInstallAddress().getCountry())+"</td></tr>");	
				xml.append("</table>");
			xml.append("</td>");
			xml.append("<td valign='top' width='30%'>");
			if(asset.getAssetContact()!=null){
				xml.append("<table>");
				xml.append("<tr><td align='left' class='labelBold'>"+LB_PRIMARY_CONTACT+":</td></tr>");
				xml.append("<tr><td align='left'>"+replaceNullWithBlankString(asset.getAssetContact().getFirstName())+" "+replaceNullWithBlankString(asset.getAssetContact().getLastName())+"</td></tr>");
				xml.append("<tr><td align='left'>"+replaceNullWithBlankString(asset.getAssetContact().getWorkPhone())+"</td></tr>");
				xml.append("</table>");
			}
			xml.append("</td>");
					
			xml.append("</tr>");
			xml.append("</table>");
			xml.append("]]></cell>\n");
			xml.append("  <cell><![CDATA[" + replaceNullWithBlankString(asset.getSerialNumber()) + "]]></cell>\n");//Serial Number
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(asset.getDeviceTag()) +"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(asset.getDescriptionLocalLang()) +"]]></cell>\n");//Product model
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(asset.getAssetTag()) +"]]></cell>\n");//Asset tag
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(asset.getHostName()) +"]]></cell>\n");//Device host name
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(asset.getIpAddress().trim()) +"]]></cell>\n");//ipaddress
			logger.debug("After setting other fields");
			//For MPS Phase 2.1 
			if(asset.getAccount()==null)
			{
				logger.debug("Asset is null");
			
				xml.append("  <cell><![CDATA["+ "]]></cell>\n");
			}
			else
			{
			    logger.debug("Asset is not null"+replaceNullWithBlankString(asset.getAccount().getAccountName()));
			
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(asset.getAccount().getAccountName())+"]]></cell>\n");
			}
			
			if(asset.getInstallAddress()!=null){
				xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(asset.getInstallAddress().getAddressName()) +"]]></cell>\n");//address name
				xml.append("  <cell><![CDATA["+ (asset.getDevicePhase()==null?"":asset.getDevicePhase()) +"]]></cell>\n");//Device phase
				xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(asset.getInstallAddress().getAddressLine1()) +"]]></cell>\n");//Address line 1
				xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(asset.getInstallAddress().getCounty()) +"]]></cell>\n");//For MPS Phase 2.1 
				xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(asset.getInstallAddress().getCity()) +"]]></cell>\n");//city
				xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(asset.getInstallAddress().getState()) +"]]></cell>\n");//state
				xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(asset.getInstallAddress().getProvince()) +"]]></cell>\n");//province
				xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(asset.getInstallAddress().getDistrict()) +"]]></cell>\n");//For MPS Phase 2.1 
				xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(asset.getInstallAddress().getOfficeNumber()) +"]]></cell>\n");//For MPS Phase 2.1 
				
				xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(asset.getInstallAddress().getCountry()) +"]]></cell>\n");//country
				xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(asset.getInstallAddress().getPostalCode()) +"]]></cell>\n");//postal code
			}
			if(asset.getAssetContact()!=null){
				xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(asset.getAssetContact().getFirstName()) +"]]></cell>\n");//primary contact first name
				xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(asset.getAssetContact().getLastName()) +"]]></cell>\n");//primary contact last name
				xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(asset.getAssetContact().getEmailAddress()) +"]]></cell>\n");//primary contact first name
				xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(asset.getAssetContact().getWorkPhone()) +"]]></cell>\n");//primary contact first name
			}
			logger.debug("<<----------------------CUSTOMER DEVICE TAG----------------------->>" + replaceNullWithBlankString(asset.getDeviceTag()));
			xml.append(" </row>\n");
			i ++;
		}
		xml.append("</rows>\n");
		return xml.toString();
	}

	/**
	 * This is done temporarily to show all history with some test data 
	 * @param serviceRequestList 
	 * @param totalCount 
	 * @param startRecordNumber 
	 * @param timezoneOffset 	 
	 * @return String 
	 */
	public String generateXMLForAllRequestType(List<ServiceRequest> serviceRequestList, int totalCount, int startRecordNumber, float timezoneOffset){
		logger.debug("buildXMLForAllRequestType Enter ----------------------");
		StringBuffer xml = new StringBuffer("<?xml version=\"1.0\" ?>\n");
		xml = xml.append("<rows total_count=\"" + totalCount + "\" pos=\"" + startRecordNumber + "\">\n");
		for (int i = 0; i < serviceRequestList.size(); i++) {
			try{
		    ServiceRequest serviceRequest = serviceRequestList.get(i);
		    xml = xml.append(" <row id=\"" + (startRecordNumber + i) + "\">\n");
		    
		   
		    ListOfValues reqTypeLOV = serviceRequest.getServiceRequestType();
		    xml.append("<cell><![CDATA[<a href=\"###\" id=\"srNumber\" onClick=\"hideSelect();onSRNmbrClick('" + serviceRequest.getServiceRequestNumber() + "','" + reqTypeLOV.getValue() +"')\">" + 
					   serviceRequest.getServiceRequestNumber() + "</a>]]></cell>\n");
		    
		    xml = xml.append("<cell><![CDATA[");
		    if ( serviceRequest.getServiceRequestDate() != null){	
				logger.debug("ServiceRequestDate Before adjust--->"+DateLocalizationUtil.localizeDateTime(serviceRequest.getServiceRequestDate(), true, locale));
				TimezoneUtil.adjustDate(serviceRequest.getServiceRequestDate(),(0-timezoneOffset));
				String dt =  DateLocalizationUtil.localizeDateTime(serviceRequest.getServiceRequestDate(), true, locale);
				xml.append(dt  +"]]></cell>\n");
				
			}else{
				xml.append("-- -- -- ]]></cell>\n");
			}
			xml.append("  <cell><![CDATA["+ reqTypeLOV.getValue() +"]]></cell>\n");
			
			ListOfValues areaLOV = serviceRequest.getArea();
			xml.append("  <cell><![CDATA["+ areaLOV.getValue() +"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ serviceRequest.getServiceRequestStatus() +"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ "TEST SERIAL NO" +"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ "TEST FIRST NAME" +"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ "TEST LAST NAME" +"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ "TEST EMAIL ADDRESS" +"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ "TEST WORK PHONE" +"]]></cell>\n");
			xml.append("  <cell><![CDATA["+"TEST REQUESTOR FIRST NAME" +"]]></cell>\n");				
			xml.append("  <cell><![CDATA["+ "TEST REQUESTOR LAST NAME" +"]]></cell>\n");
			xml.append("  <cell><![CDATA["+"TEST REQUESTOR EMAIL ADDRESS "+"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ "TEST REQUESTOR WORK PHONE"+"]]></cell>\n");
			xml = xml.append(" </row>\n");
			}catch(Exception ex)
			{
				logger.debug("Exception Occured");
			}
			    
		}
		xml = xml.append("</rows>\n");
		logger.debug("XmlOutputGenerator.generateXMLForAllRequestType.EXIT----------------");
		
		return xml.toString();
	}
	
	/**
	 * @param a 
	 * @param totalCount 
	 * @param posStart 
	 * @param columnsPattern 
	 * @param type 
	 * @return String 
	 */
	public String generate(List<?> a, int totalCount, int posStart, String[] columnsPattern, String type) {
			logger.debug("-----------Step 4--convert2XML started---------["+System.nanoTime()+"]");
			StringBuffer xml = new StringBuffer("<?xml version=\"1.0\" ?>\n");
			xml = xml.append("<rows total_count=\""+totalCount+"\" pos=\""+posStart+"\">\n");
			for(int i = 0; i < a.size(); i ++){
				xml = xml.append("<row id=\""+ (posStart + i)+"\">\n");
				for(String columnPattern: columnsPattern) {
					
					if(columnPattern.equalsIgnoreCase("serialNumber")){
						String value = "";
					    if(a.get(i)!=null){
					    	value = BusinessObjectUtil.formatColumn(a.get(i), columnPattern, locale);
					    }
					   
					    value = localizeServiceRequestStatus(value, columnPattern, locale);
					    if(value == null){ value = "";}
					    //defect# 10059
					    if("price".equalsIgnoreCase(columnPattern)){
							   if(value!= null){
								   if("0".equals(value)||"0.0".equals(value) ||"0.00".equals(value) ||"".equals(value)){
							    		value = PropertiesMessageUtil.getPropertyMessage(BUNDLENAME_BUTTON_MSG, "requestInfo.error.priceUnavailable", locale);
							    	}
							   }
						   }
						/**********Start of Sub Row Data***********/
						xml = xml.append("<cell><![CDATA[");
						xml.append("<table cellspacing=\"0\" cellpadding=\"0\" class=\"grid-inner-body\">");			
						xml.append("<tr><td class=\"labelBold\" valign=\"top\" style=\"padding-left:60px;\" width=\"90px\" >");
						xml = xml.append(PropertiesMessageUtil.getPropertyMessage(BUNDLENAME_BUTTON_MSG, "serviceRequest.listSubHeader.shipment", locale) );
						xml.append("</td>");
						xml.append("<td valign=\"top\" style=\"padding-left:10px;\"  >");
						xml = xml.append(value );
						xml.append("</td>");
						xml.append("</tr></table>");
						xml.append("]]></cell>\n");
						/**********End of Sub Row Data***********/
					}else{
					    xml = xml.append("<cell><![CDATA[");
					    
					    String value = "";
					    if(a.get(i)!=null){
					    	value = BusinessObjectUtil.formatColumn(a.get(i), columnPattern, locale);
					    }
					    value = localizeServiceRequestStatus(value, columnPattern, locale);
					    if(value == null){ value = "";}
						xml = xml.append(value);
						xml = xml.append("]]></cell>\n");
					}
				}
				xml = xml.append(" </row>\n");				
			}
			xml = xml.append("</rows>\n");
			logger.debug("-----------Step 4--convert2XML End---------["+System.nanoTime()+"]");
			return xml.toString();
	}
	
	/**
	 * @param items 
	 * @param totalCount 
	 * @param posStart 
	 * @param columnsPattern 
	 * @return String 
	 */
	public String generateXMLPendingShip(List<ServiceRequestOrderLineItem> items, int totalCount, int posStart, String[] columnsPattern) {
		logger.debug("-----------Step 4--convert2XML started---------["+System.nanoTime()+"]");
		StringBuffer xml = new StringBuffer("<?xml version=\"1.0\" ?>\n");
		int rowCount = 0;
		xml = xml.append("<rows total_count=\""+totalCount+"\" pos=\""+posStart+"\">\n");
		for(int i = 0; i < items.size(); i ++){
				rowCount += 1; 
				xml = xml.append("<row id=\""+ (posStart + i)+"\">\n");				
				for(String columnPattern: columnsPattern) {
				    xml = xml.append("<cell><![CDATA[");
				    
				    String value = "";
				    if(items.get(i)!=null){
				    	value = BusinessObjectUtil.formatColumn(items.get(i), columnPattern, locale);
				    }
				    value = localizeServiceRequestStatus(value, columnPattern, locale);
				    if(value == null){ value = "";}
				    //defect# 10059
				    if("price".equalsIgnoreCase(columnPattern)){
					   if(value!= null){
						   if("0".equals(value)|| "0.0".equals(value) || "0.00".equals(value) ||"".equals(value)){
					    		value = PropertiesMessageUtil.getPropertyMessage(BUNDLENAME_BUTTON_MSG, "requestInfo.error.priceUnavailable", locale);
					    	}else{
					    		value=String.format( "%.2f", Float.parseFloat(value)) ;
					    		if(!"".equals(items.get(i).getCurrency()) && items.get(i).getCurrency() != null){
					    		value=value + " ("+items.get(i).getCurrency()+")";
					    		}
					    	}
					   }
				   }
				   xml = xml.append(value);
				   xml = xml.append("]]></cell>\n");
				}
				xml = xml.append(" </row>\n");	
		}
		xml = xml.append("</rows>\n");
		if(rowCount == 0){
			logger.debug("-----------Step 4--convert2XML End---------["+System.nanoTime()+"]");
			return null;
		}
		logger.debug("-----------Step 4--convert2XML End---------["+System.nanoTime()+"]");
		return xml.toString();
	}
	
	/*
	 * This method is used to call sap url list
	 * in the edit page
	 */
	/**
	 * @param session 
	 * @param billerDirectList 
	 * @param contextPath 
	 * @return String 
	 */
	public String sapURLListViewXml(PortletSession session, List<SapPortlet> billerDirectList, String contextPath){
		logger.info("---------------- Entered sapURLListViewXml method -------------------");
		Map<String, String> languageMap = (Map<String, String>)session.getAttribute("languageMap");
		String imageDeleteSrc = contextPath + IMAGE_PATH + TRANSPARENT_FILE_NAME;
		int totalCount = billerDirectList.size();//Hardcoded value, need to check
		int posStart = 0;
		StringBuffer xml = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		xml.append("<rows total_count=\""+totalCount+"\" pos=\""+posStart+"\">\n");
		for(int i=0;i<totalCount;i++){
		xml.append("<row id=\""+ (posStart + i)+"\">\n");
		//sub_row cell started
		xml.append("<cell><![CDATA[");
		xml.append("<table id=\"billerDirectSubTab"+i+"\" width='800px' height='140px' class=\"displayGrid rounded shadow\">");
		xml.append("<thead><tr><th class=\"w100\">Language</th><th class=\"w80\">Header Name</th><th class=\"w200\">URL</th>" +
					"<th class=\"w15 \">Delete</th></tr></thead><tbody>");
		if(billerDirectList.get(i).getSapPortletURLs()!=null){
			for(int j=0;j<billerDirectList.get(i).getSapPortletURLs().size();j++){
				String funcName = billerDirectList.get(i).getSapPortletURLs().get(j).getFunctionalityName();
				String funcUrl = billerDirectList.get(i).getSapPortletURLs().get(j).getFunctionalityURL();
				String language = billerDirectList.get(i).getSapPortletURLs().get(j).getLanguage();
				Integer funcId = billerDirectList.get(i).getSapPortletURLs().get(j).getId();
				xml.append("<tr>");
				xml.append("<td><select class=\"w100\" name=\"billerDirectURLList["+i+"].billerDirectList["+j+"].language\" id=\"billerDirectURLList["+i+"].billerDirectList["+j+"].language\">");
				for (Map.Entry<String, String> entry : languageMap.entrySet()) {
					String key = entry.getKey().toString();
					String value = entry.getValue().toString();
					logger.info("Language value is "+language);
					if(key.equalsIgnoreCase(language)){
						xml.append("<option value=\""+key+"\" selected >"+value+"</option>");
					}else{
						xml.append("<option value=\""+key+"\">"+value+"</option>");
					}
				}
				xml.append("</select></td>");
				xml.append("<td><input type=\"text\" id=\"billerDirectURLList["+i+"].billerDirectList["+j+"].funcName\" name=\"billerDirectURLList["+i+"].billerDirectList["+j+"].funcName\" value=\"" + funcName + "\" class=\"w200\"/><input type=\"hidden\" id=\"billerDirectURLList["+i+"].billerDirectList["+j+"].funcId\" name=\"billerDirectURLList["+i+"].billerDirectList["+j+"].funcId\" value=\"" + funcId + "\"/></td>");
				xml.append("<td><input type=\"text\" id=\"billerDirectURLList["+i+"].billerDirectList[" + j +"].funcUrl\" name=\"billerDirectURLList["+i+"].billerDirectList["+j+"].funcUrl\" value=\"" + funcUrl + "\" class=\"w400\"/></td>");
				xml.append("<td><img class=\"ui_icon_sprite trash-icon\" id=\"billerDirectURLList["+i+"].billerDirectList["+j+"]Img\" src=\'"+imageDeleteSrc+"' onClick=\"deleteSubTabRow(this,"+i+","+j+")\" style=\"cursor:pointer;\"></td>");
				xml.append("</tr>");
			}
		}
		xml.append("<tr><td colspan=\"4\" align=\"left\"><button class=\"floatR\" id=\"addLanguage\" onclick=\"addLanguageSubRow('"+i+"');return false;\">Add Another Language</button></td></tr>");
		xml.append("</tbody></table>");
		xml.append("]]></cell>\n");
		//sub row cell ended
		//per cell value
		int serialNumber = i+1;
		String gridFuncName = billerDirectList.get(i).getGridFunctionalityName();
		String gridFuncURL = billerDirectList.get(i).getGridFunctionalityURL();
		xml.append("<cell><![CDATA[" + serialNumber + "<input type=\"hidden\" id=\"funcURLId"+i+"\" value=\"" + billerDirectList.get(i).getId() + "\"/>]]></cell>\n");//Serial Number
		xml.append("<cell><![CDATA[");
		xml.append("<div id=\"funcNameDiv"+i+"\"><input type=\"text\" class=\"w150\" onclick=\"focusOnInput('funcName"+i+"');\" id=\"funcName"+i+"\" value=\"" + gridFuncName + "\"/></div>");	
		xml.append("]]></cell>\n");//Functionality Name
		xml.append("<cell><![CDATA[");
		xml.append("<div id=\"funcURLDiv"+i+"\"><input type=\"text\" class=\"w500\" onclick=\"focusOnInput('funcURL"+i+"');\" id=\"funcURL"+i+"\" value=\"" + gridFuncURL + "\"/></div>");
		xml.append("]]></cell>\n");//URL
		xml.append("<cell><![CDATA[<input type=\"checkbox\" id=\"langSupportCheckBox"+i+"\"/>]]></cell>\n");//Language Support
		xml.append("<cell><![CDATA[<a href=\"javascript:deleteGridRow(\'"+i+"');\"><img class=\"ui_icon_sprite trash-icon\" style=\"float:none;\" src=\'"+imageDeleteSrc+"'  width=\"15\" height=\"15\"/>]]></cell>\n");//Delete
		xml.append(" </row>\n");
		}
		xml.append("</rows>\n");
		logger.info("---------------- Exit sapURLListViewXml method -------------------");
		logger.info("xml is "+xml.toString());
		return xml.toString();
	}
	
	/**
	 * @param assetDetailPageForm 
	 * @param totalCount 
	 * @param startRecordnumber 
	 * @param currentLocale 
	 * @return String 
	 * @throws ParseException 
	 */
	public String convertOrderHistoryPopup(AssetDetailPageForm assetDetailPageForm,int totalCount,int startRecordnumber,Locale currentLocale)throws ParseException{		
		logger.info("currentLocale-->>"+currentLocale.getLanguage());
		StringBuffer xml = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		xml.append("<rows total_count=\""+totalCount+"\" pos=\""+startRecordnumber+"\">\n");
		List<Part>  assetPartList=assetDetailPageForm.getAssetPartList();
		int i=0;
		//added to sort the records in the popup as per the order numbers
		Collections.sort((List)assetPartList,new Comparator<Part>(){
			public int compare(Part part1, Part part2) { 
				return (part1.getOrderNumber()).compareTo((part2.getOrderNumber())); 
		    }
		});
		//ends here
		for(Part part:assetPartList){
		xml.append("<row id=\""+ (startRecordnumber + i)+"\">\n");
		//sub_row cell started
		xml.append("<cell><![CDATA[");
		xml.append((part.getPartNumber()== null ? "":part.getPartNumber()));
		xml.append("]]></cell>\n");
		xml.append("<cell><![CDATA[");
		xml.append((part.getDescription()== null ? "":part.getDescription()));
		xml.append("]]></cell>\n");
		xml.append("<cell><![CDATA[");
		xml.append((part.getPartType()== null ? "":part.getPartType()));
		xml.append("]]></cell>\n");
		xml.append("<cell><![CDATA[");
		logger.info("currentLocale-->>"+part.getLastOrderDate().toString());
		xml.append((part.getLastOrderDate()== null ? "":getDateInGMTFormat(part.getLastOrderDate(),currentLocale)));
		xml.append("]]></cell>\n");
		xml.append("<cell><![CDATA[");
		xml.append((part.getOrderNumber()== null ? "":part.getOrderNumber()));
		xml.append("]]></cell>\n");
		xml.append("<cell><![CDATA[");
		xml.append((part.getOrderQuantity()== null ? "":part.getOrderQuantity()));
		xml.append("]]></cell>\n");
		xml.append("<cell><![CDATA[");
		xml.append((part.getShippedDate()== null ? "":getDateInGMTFormat(part.getShippedDate(),currentLocale)));
		xml.append("]]></cell>\n");
		xml.append(" </row>\n");
		i++; 
		}
		xml.append("</rows>\n");
		logger.info("---------------- Exit sapURLListViewXml method -------------------");
		logger.info("xml is "+xml.toString());
		return xml.toString();
		
	}
	
	/**
	 * @param lastOrderDate 
	 * @param currentLocale 
	 * @return String 
	 * @throws ParseException 
	 */
	public String getDateInGMTFormat(Date lastOrderDate,Locale currentLocale) throws ParseException
	{
		logger.info("The locale is --->>>"+currentLocale);			
		String dateFormatter = DateUtil.getDateFormatByLang(currentLocale.getLanguage());
		DateFormat dateFormat = new SimpleDateFormat(dateFormatter);
		String localeSpecificDate = null;
		logger.info("The LAST order date --->>>"+lastOrderDate.toString());
		localeSpecificDate = dateFormat.format(lastOrderDate);	
		logger.info("The LAST order date --->>>"+localeSpecificDate.toString());
		Date currentDateObj = DateUtil.getStringToLocalizedDate(localeSpecificDate, false, currentLocale);
		logger.info("currentDateObj after conversion :"+currentDateObj);
		DateFormat dateFormatObj = new SimpleDateFormat(dateFormatter);
		String current_GMT_Date = dateFormatObj.format(currentDateObj);
		logger.info("The GMT --------->>>>"+current_GMT_Date);
		return current_GMT_Date;
	}

	//For MPS Phase 2.1 
	/**
	 * @param serviceRequestList 
	 * @param totalCount 
	 * @param startRecordNumber 
	 * @param timezoneOffset 
	 * @param contextPath 
	 * @return String 
	 */
	public String buildXMLForHardwareRequestType(List<ServiceRequest> serviceRequestList, int totalCount, int startRecordNumber, float timezoneOffset, String contextPath){
		logger.debug("XmlOutputGenerator.buildXMLForSupplyRequestType.ENTER---------------");
		StringBuffer xml = new StringBuffer("<?xml version=\"1.0\" ?>\n");
		xml = xml.append("<rows total_count=\"" + totalCount + "\" pos=\"" + startRecordNumber + "\">\n");
		for (int i = 0; i < serviceRequestList.size(); i++) {
		    ServiceRequest serviceRequest = serviceRequestList.get(i);
		    xml = xml.append(" <row id=\"" + (startRecordNumber + i) + "\">\n");
		    String area = serviceRequest.getArea().getValue();
		    logger.debug("XmlOutputGenerator.buildXMLForSupplyRequestType.area--->"+area);
		    List<Part> partList=  serviceRequest.getParts();
		    
		    if((area!=null && area.trim().equalsIgnoreCase("Consumables Return")) || (partList.isEmpty())){
		    	 xml.append("  <cell><![CDATA[ ]]></cell>\n");
		    }else{
					    
		     xml.append("<cell><![CDATA[<div class=\"dhx_sub_row\"><table class=\"displayGrid\">" 
		    + "<thead><tr>" 
			+ "<th class=\"w100\">"+PropertiesMessageUtil.getPropertyMessage(BUNDLENAME_BUTTON_MSG, "requestInfo.heading.partNumber", locale)+"</th>"	
			+ "<th>"+PropertiesMessageUtil.getPropertyMessage(BUNDLENAME_BUTTON_MSG, "requestInfo.heading.description", locale)+"</th>"	
			+ "<th class=\"w150\">"+PropertiesMessageUtil.getPropertyMessage(BUNDLENAME_BUTTON_MSG, "requestInfo.heading.partType", locale)+"</th>"	
			+ "<th class=\"w80 right\">"+PropertiesMessageUtil.getPropertyMessage(BUNDLENAME_BUTTON_MSG, "requestInfo.heading.quantity", locale)+"</th>"				
			+"</tr></thead><tbody>") ;
			
			int count = 0;
			for(Part part:partList){
					if(count % 2 == 0){
						xml.append("<tr class=\"altRow\">") ;
					}else{
						xml.append("<tr>") ;
					}
					xml.append("<td>"+part.getPartNumber()+"</td>"	
								+ "<td>"+part.getDescription()+"</td>"	
								+ "<td>"+part.getPartType()+"</td>"	
								+ "<td class=\"right\">"+part.getOrderQuantity()+"</td>"				
								+"</tr>") ;
				count ++ ;
			}
			
			xml.append("</table>]]></cell>\n");
		    }
		    xml.append("<cell><![CDATA[<a href=\"###\" id=\"srNumber\" onClick=\"hideSelect();onSRNmbrClick('" + serviceRequest.getServiceRequestNumber() 
		    		+ "','hardware','"+serviceRequest.getStatusType().getValue()
		    		+"','"+serviceRequest.getSubArea().getValue()+"','"+serviceRequest.getArea().getValue()+"','"+serviceRequest.getCoveredService()+"')\">" + 
					   serviceRequest.getServiceRequestNumber() + "</a>]]></cell>\n");
		    
		    if ( serviceRequest.getServiceRequestDate() != null){					   
				String dt =  DateLocalizationUtil.localizeDateTime(DateUtil.adjustDateWithOffset(serviceRequest.getServiceRequestDate(), timezoneOffset), true, locale);
				xml.append("  <cell><![CDATA["+ dt  +"]]></cell>\n");
			}else{
				xml.append("  <cell><![CDATA[ -- -- -- ]]></cell>\n");
			}
			ListOfValues areaLOV = serviceRequest.getArea();
			logger.debug("Area -->"+areaLOV.getValue());
			xml.append("  <cell><![CDATA["+getLocalizeSiebelValue(areaLOV.getValue(),SiebelLocalizationOptionEnum.REQUEST_AREA.getValue(),locale) +"]]></cell>\n");
			ListOfValues subAreaLOV = serviceRequest.getSubArea();
			logger.debug("subAreaLOV -->"+subAreaLOV.getValue());
			xml.append("  <cell><![CDATA["+getLocalizeSiebelValue(subAreaLOV.getValue(),SiebelLocalizationOptionEnum.REQUEST_SUBAREA.getValue(),locale) +"]]></cell>\n");
			xml.append("  <cell><![CDATA["+getLocalizeSiebelValue(serviceRequest.getStatusType().getValue(),SiebelLocalizationOptionEnum.REQUEST_STATUS.getValue(),locale) +"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getPoNumber()) +"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getCostCenter()) +"]]></cell>\n");
			//-- changes for phase 2.1
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getAccountName())+"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getServiceAddress().getAddressName() )+"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getServiceAddress().getOfficeNumber() )+"]]></cell>\n"); //Added for CI Defect #9183
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getServiceAddress().getStoreFrontName() )+"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getServiceAddress().getAddressLine1() )+"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getServiceAddress().getCity() )+"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getServiceAddress().getState() )+"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getServiceAddress().getProvince()) +"]]></cell>\n");
			/*added for mps2.1*/
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getServiceAddress().getCounty())+"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getServiceAddress().getDistrict())+"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getServiceAddress().getCountry()) +"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getServiceAddress().getPostalCode()) +"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getPrimaryContact().getFirstName() )+"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getPrimaryContact().getLastName() )+"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getPrimaryContact().getEmailAddress()) +"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getPrimaryContact().getWorkPhone()) +"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getRequestor().getFirstName() )+"]]></cell>\n");				
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getRequestor().getLastName() )+"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getRequestor().getEmailAddress() )+"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getRequestor().getWorkPhone() )+"]]></cell>\n");
			xml = xml.append(" </row>\n");
		}
		xml = xml.append("</rows>\n");
		logger.debug("XmlOutputGenerator.buildXMLForSupplyRequestType.EXIT---------------");
		return xml.toString();
	}
	//For B2B Requests
	/**
	 * @param serviceRequestList 
	 * @param totalCount 
	 * @param startRecordNumber 
	 * @param timezoneOffset 
	 * @param contextPath 
	 * @return String 
	 */
	public String buildXMLForB2BRequestType(List<ServiceRequest> serviceRequestList, int totalCount, int startRecordNumber, float timezoneOffset, String contextPath){
		logger.debug("XmlOutputGenerator.buildXMLForB2BRequestType.ENTER---------------");
		StringBuffer xml = new StringBuffer("<?xml version=\"1.0\" ?>\n");
		xml = xml.append("<rows total_count=\"" + totalCount + "\" pos=\"" + startRecordNumber + "\">\n");
		for (int i = 0; i < serviceRequestList.size(); i++) {
		    ServiceRequest serviceRequest = serviceRequestList.get(i);
		    ListOfValues reqTypeLOV = serviceRequest.getServiceRequestType();
		    xml = xml.append(" <row id=\"" + (startRecordNumber + i) + "\">\n");
		  
		    xml.append("<cell><![CDATA[<a href=\"###\" id=\"srNumber\" onClick=\"hideSelect();onB2bSRNmbrClick('" + serviceRequest.getServiceRequestNumber() 
		    		+ "','" + reqTypeLOV.getValue()+ "','" + serviceRequest.getStatusType().getValue()
		    		+"','"+serviceRequest.getSubArea().getValue()+"','"+serviceRequest.getArea().getValue()+"','"+serviceRequest.getCoveredService()+"')\">" +
					   serviceRequest.getServiceRequestNumber() + "</a>]]></cell>\n");
		    
		    if ( serviceRequest.getServiceRequestDate() != null){					   
				String dt =  DateLocalizationUtil.localizeDateTime(DateUtil.adjustDateWithOffset(serviceRequest.getServiceRequestDate(), timezoneOffset), true, locale);
				xml.append("  <cell><![CDATA["+ dt  +"]]></cell>\n");
			}else{
				xml.append("  <cell><![CDATA[ -- -- -- ]]></cell>\n");
			}
		    xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getRequestType()) +"]]></cell>\n");
			ListOfValues areaLOV = serviceRequest.getArea();
			logger.debug("Area -->"+areaLOV.getValue());
			xml.append("  <cell><![CDATA["+getLocalizeSiebelValue(areaLOV.getValue(),SiebelLocalizationOptionEnum.REQUEST_AREA.getValue(),locale) +"]]></cell>\n");
			
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getServiceRequestStatus()) +"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getWebOrderNumber()) +"]]></cell>\n");
			
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getPrimaryContact().getFirstName() )+"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getPrimaryContact().getLastName() )+"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getPrimaryContact().getEmailAddress()) +"]]></cell>\n");
			
			xml = xml.append(" </row>\n");
		}
		xml = xml.append("</rows>\n");
		logger.debug("XmlOutputGenerator.buildXMLForB2BRequestType.EXIT---------------");
		return xml.toString();
	}
	/**
	 * @param pagecountsList 
	 * @param contextPath 
	 * @return String 
	 */
	public String convertPageCountToXML(List<PageCounts> pagecountsList,String contextPath ){
		logger.debug("WITHIN ---------------->>>>>> convertPageCountToXML");
		String imageCalendar = contextPath + IMAGE_PATH + CALENDAR_ICON_NAME;
		String transparentImageSrc=contextPath + IMAGE_PATH +"transparent.png";
		String imageDelete = contextPath + IMAGE_PATH + DELETE_FILE_NAME;
		
		StringBuffer xml = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		xml.append("<rows>\n");
		for (int i = 0; i < pagecountsList.size(); i++) {
			PageCounts pageCounts = pagecountsList.get(i);
			
			xml = xml.append(" <row id=\"" + i + "\">\n");
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(pageCounts.getName())+"]]></cell>\n");				
			if(StringUtil.isEmpty(pageCounts.getDate())){
				xml.append("  <cell><![CDATA[]]></cell>\n");
				
			}else{
				xml.append("  <cell><![CDATA["+ pageCounts.getDate()+"]]></cell>\n");
			}
			xml.append("  <cell><![CDATA[<div id=\""+pageCounts.getName()+"\">"+ replaceNullWithBlankString(pageCounts.getCount())+"</div>]]></cell>\n");
			xml.append("  <cell><![CDATA[<div><div  class=\"pageCountInput w160 floatL\"><input type=\"text\" readonly=\"true\" " +
					"style=\"float:left;\" id=\"rwid_"+ (i+1) +"\" class=\"w150\" onchange=\"shwDate('rwid_"+ (i+1) +"', 'rwid_img"+ (i+1) +"');\"  " +
							"onblur=\"shwDate('rwid_"+ (i+1) +"','rwid_img"+ (i+1) +"');\" /><img id=\"img\" class=\"cal_date floatL ui_icon_sprite calendar-icon\" style=\"position:static;\" src=\"" + transparentImageSrc + "\" " +
									"onClick=\"showCal('rwid_"+ (i+1)+"' , 'Date', 'Dates', true);\" onFocus=\"this.className='';\">"+
										"<img id=\"rwid_img"+ (i+1) +"\" style=\"display:none;margin-top:3px;float:left;\" class=\"ui_icon_sprite trash-icon\" src=\"" + transparentImageSrc + "\" onClick=\"removeDate('rwid_"+ (i+1) +"', 'rwid_img"+ (i+1) +"');\"></div></div>]]></cell>\n");
			xml.append("  <cell><![CDATA[<input type=\"text\" id=\"currentPageCount"+ i +"\" value=\"\" class=\"w150\"></input>]]></cell>\n");
			xml = xml.append(" </row>\n");
			
		}
		xml = xml.append(" </rows>\n");
		
		return xml.toString();
	}

	
	
	
	/**
	 * @param partList 
	 * @param selectedPaymentType 
	 * @param showPrice 
	 * @return String 
	 */
	public  String createPartListXML(List<Part> partList, boolean showPrice, String selectedPaymentType)
	{

		logger.debug ("Inside createPartListXML in XMLOutputGenerator::::");
		logger.debug ("showPrice ::::" + showPrice);
		int rowNumber = 0;
		String partNumberHeader = PropertiesMessageUtil.getPropertyMessage(BUNDLENAME_BUTTON_MSG, "requestInfo.heading.partNumber", locale);
		//String preferredPartHeader = PropertiesMessageUtil.getPropertyMessage(BUNDLENAME_BUTTON_MSG, "requestInfo.heading.preferredPart", locale);
		String descHeader = PropertiesMessageUtil.getPropertyMessage(BUNDLENAME_BUTTON_MSG, "requestInfo.heading.description", locale);
		String partTypeHeader = PropertiesMessageUtil.getPropertyMessage(BUNDLENAME_BUTTON_MSG, "requestInfo.heading.partType", locale);
		String partPriceHeader = PropertiesMessageUtil.getPropertyMessage(BUNDLENAME_BUTTON_MSG, "requestInfo.heading.unitPrice", locale);
		String partQuantityHeader = PropertiesMessageUtil.getPropertyMessage(BUNDLENAME_BUTTON_MSG, "requestInfo.heading.orderQuantity", locale);
		
		StringBuffer content = new StringBuffer("<table>");
		//Style added for Preferred column CI Defect #8280
		if(showPrice){
			content.append("<thead><tr><th class='w90'>"+partNumberHeader+"</th><th>"+descHeader+"</th><th class='w200'>"+partTypeHeader+"</th><th class='w80'>"+partPriceHeader
					+"</th><th class='w80'>"+partQuantityHeader+"</th></tr></thead>");
		}else{
			content.append("<thead><tr><th class='w90'>"+partNumberHeader+"</th><th>"+descHeader+"</th><th class='w200'>"+partTypeHeader+"</th><th class='w80'>"+
					partQuantityHeader+"</th></tr></thead>");
		}
			
		for(Part parts : partList)
		{
			logger.debug("MAX ORDER QTY for "+parts.getPartNumber()+" is ---> "+parts.getMaxQuantity());
			//Added for CI 14-02-16 START
			if(parts.getVendorPartNumber()==null){
				logger.debug("parts.getPartNumber()---->>"+parts.getPartNumber());
				parts.setVendorPartNumber(parts.getPartNumber());
				
			}else{
				if(parts.getVendorPartNumber().equals("")||parts.getVendorPartNumber().isEmpty()){
					logger.debug("parts.getPartNumber()---->>"+parts.getPartNumber());
					parts.setVendorPartNumber(parts.getPartNumber());
				}
				else{
					logger.debug("parts.getVendorPartNumber()INSIDE ELSE---->>"+parts.getVendorPartNumber());
					//parts.setVendorPartNumber(parts.getPartNumber());   //modified defect #10550
					parts.setVendorPartNumber(parts.getVendorPartNumber());
				}
			}
			//Added for CI 14-02-16 ENDS
			/*boolean preferredFlag = parts.isPrefferedPartFlag();
			logger.debug("The preferred flag is -->> "+preferredFlag);*/
			if(rowNumber%2==0){
				content.append("<tbody><tr>");
			}else{
				content.append("<tbody><tr  class='altRow'>");
			}
			//Changed for CI 14-02-16
			content.append("<td class='w90'>" + replaceNullWithBlankString(parts.getVendorPartNumber()) + "</td>");
             /*Here we need to check whether the preferred flag from Siebel is set to 1 or not
			 * if 1 then we'll show the Preferreed column with *
			 * else we won't show
			 * modified BRD #13.10.05
			 */
			/*if(preferredFlag){
			content.append("<td class='w10'><font size = 4px><b>" +PropertiesMessageUtil.getPropertyMessage(BUNDLENAME_BUTTON_MSG, 
					"requestInfo.heading.preferredSymbol", locale)+ "</b></font></td>");
			}
			else
			{
			content.append("<td class='w10'></td>");	
			}*/
			
			if(parts.getDescription() != null){
				if(parts.getDescription().indexOf("\n") > -1){
					String desc = parts.getDescription().replaceAll("\\r\\n|\\n", " ");
					
					content.append("<td>" + desc + "</td>");
				}else{
					content.append("<td>" + parts.getDescription() + "</td>");
				}
			}else{
				content.append("<td> </td>");
			}
			
			
			content.append("<td class='w200'>"+ replaceNullWithBlankString(parts.getPartType()) + "</td>");
			if(showPrice){
				if(parts.getUnitPrice()!= null){
					if(parts.getUnitPrice().equals("0")||parts.getUnitPrice().equals("0.0")||parts.getUnitPrice().equals("0.00")||parts.getUnitPrice().equals("")){
						//Msg displayed if price is not avaailable for the part and no text field to enter qty to be shown
						content.append("<td class='150 right' ><span class='errorText'>" + PropertiesMessageUtil.getPropertyMessage(BUNDLENAME_BUTTON_MSG, "requestInfo.error.priceUnavailable", locale) + "</span></td>");
						
						if(ChangeMgmtConstant.CONSUMABLE_PAYMENT_TYPE_PAY_LATER.equalsIgnoreCase(selectedPaymentType) || "Invoice".equalsIgnoreCase(selectedPaymentType)){
							String maxOrderQty = parts.getMaxQuantity();
							if(null == maxOrderQty || maxOrderQty.trim().equalsIgnoreCase("")){
								maxOrderQty = "5";
							}
							content.append("<td class='w90 right' id='assetPartListDiv" +rowNumber+ "'>" +
									"<input type='text' class='w90 right' id='assetPartListDiv["+rowNumber+"].orderQuantity' onkeyup='checkParts(this.id,"+maxOrderQty+")' onchange='removeErrorMessage()' " +
											"name='assetPartList["+rowNumber+"].orderQuantity' value='"+
												replaceNullWithBlankString(parts.getOrderQuantity())+"'>"
												+"<div style='display: none; color: red;' id='MsgassetPartList["+rowNumber+"].orderQuantity'><B>"+PropertiesMessageUtil.getPropertyMessage(BUNDLENAME_BUTTON_MSG, "maxOrderQtyValidation", locale)+" "+maxOrderQty+"</B></div></td>");
						}else{
							content.append("<td class='w100 right'></td>");
						}						
					}else{
						String maxOrderQty = parts.getMaxQuantity();
						if(null == maxOrderQty || maxOrderQty.trim().equalsIgnoreCase("")){
							maxOrderQty = "5";
						}
						content.append("<td class='w90 right' id='assetUnitPrice" +rowNumber+ "'>"+ replaceNullWithBlankString(parts.getUnitPrice()) + " ("+
								replaceNullWithBlankString(parts.getCurrency()) +")"+"</td>");
						content.append("<td class='w90 right' id='assetPartListDiv" +rowNumber+ "'><input type='text' class='w90 right' id='assetPartList["
								+rowNumber+"].orderQuantity' onkeyup='checkParts(this.id,"+maxOrderQty+")' onchange='removeErrorMessage()' name='assetPartList["+rowNumber+"].orderQuantity' value='"+
								replaceNullWithBlankString(parts.getOrderQuantity())+"'>"
								+"<div style='display: none; color: red;' id='MsgassetPartList["+rowNumber+"].orderQuantity'><B>"+PropertiesMessageUtil.getPropertyMessage(BUNDLENAME_BUTTON_MSG, "maxOrderQtyValidation", locale)+" "+maxOrderQty+"</B></div></td>");
					}
				}else{
					String maxOrderQty = parts.getMaxQuantity();
					if(null == maxOrderQty || maxOrderQty.trim().equalsIgnoreCase("")){
						maxOrderQty = "5";
					}
					//Msg displayed if price is not avaailable for the part and no text field to enter qty to be shown
					content.append("<td class='150 right' ><span class='errorText'>" + PropertiesMessageUtil.getPropertyMessage(BUNDLENAME_BUTTON_MSG, "requestInfo.error.priceUnavailable", locale) + "</span></td>");
					if(ChangeMgmtConstant.CONSUMABLE_PAYMENT_TYPE_PAY_LATER.equalsIgnoreCase(selectedPaymentType)|| "Invoice".equalsIgnoreCase(selectedPaymentType)){
						content.append("<td class='w90 right' id='assetPartListDiv" +rowNumber+ "'><input type='text' class='w90 right' id='assetPartList["+rowNumber+"].orderQuantity' onkeyup='checkParts(this.id,"+maxOrderQty+")' onchange='removeErrorMessage()' name='assetPartList["+rowNumber+"].orderQuantity' value='"+replaceNullWithBlankString(parts.getOrderQuantity())+"'>"
								+"<div style='display: none; color: red;' id='MsgassetPartList["+rowNumber+"].orderQuantity'><B>"+PropertiesMessageUtil.getPropertyMessage(BUNDLENAME_BUTTON_MSG, "maxOrderQtyValidation", locale)+" "+maxOrderQty+"</B></div></td>");
					}else{
						content.append("<td class='w100 right'></td>");
					}	
				}
				
			}else{
				String maxOrderQty = parts.getMaxQuantity();
				if(null == maxOrderQty || maxOrderQty.trim().equalsIgnoreCase("")){
					maxOrderQty = "5";
				}
				content.append("<td class='w90 right' id='assetPartListDiv" +rowNumber+ "'><input type='text' class='w90 right' id='assetPartList["+rowNumber+"].orderQuantity' onkeyup='checkParts(this.id,"+maxOrderQty+")' onchange='removeErrorMessage()' name='assetPartList["+rowNumber+"].orderQuantity' value='"+replaceNullWithBlankString(parts.getOrderQuantity())+"'>"
						+"<div style='display: none; color: red;' id='MsgassetPartList["+rowNumber+"].orderQuantity'><B>"+PropertiesMessageUtil.getPropertyMessage(BUNDLENAME_BUTTON_MSG, "maxOrderQtyValidation", locale)+" "+maxOrderQty+"</B></div></td>");
			}
			content.append("</tr>");
			rowNumber ++;
		}
		content.append("</tbody></table>");	
		return content.toString();
	}
	
	//added for customerARInvoice-Parth
	/**
	 * @param invoiceList 
	 * @param lovValues 
	 * @param totalCount 
	 * @param timezoneOffset 
	 * @return String 
	 * @throws ParseException 
	 */
	// map argument added 
	public String convertInvoiceAPListToXML(List<Invoice> invoiceList,int totalCount,float timezoneOffset,Map<String,String> lovValues) throws ParseException {
		StringBuffer xml=null;
		try{
			logger.debug("XmlOutputGenerator.convertInvoiceAPListToXML--Enter:");
			if(invoiceList == null || invoiceList.isEmpty()){
				return "<?xml version=\"1.0\" ?><rows></rows>";
			} 
			xml = new StringBuffer("<?xml version=\"1.0\" ?>\n");
			if(invoiceList != null && invoiceList.size()>0)
			{
				xml.append("<rows total_count=\"" + totalCount + "\" pos=\"0"+ "\">\n");
				int listSize = invoiceList.size();
				for (int i = 0;i < listSize;i++) {	
					Invoice invoice = invoiceList.get(i);
					final String invoiceDetail = localizeMessage("invoice.grid.invoiceDetail",locale);
					String invoiceNum=invoice.getInvoiceNumber();	
					if (StringUtils.isBlank(invoiceNum)){
						continue;
					}
					logger.debug("sap doc id 1"+invoice.getSapDocId1());
					logger.debug("sap doc id 2"+invoice.getSapDocId2());
					xml.append(" <row id=\""+invoiceNum+"\">\n");
					if(StringUtils.isNotBlank(invoice.getSapDocId1())){
						xml.append("  <cell><![CDATA[ <a class=\"invoicelink\" target=\"_blank\" href=\""+URLEncryptUtilForCustomerInvoice.encrypt(invoice.getSapDocId1())+"\" >"+ invoiceNum +"</a>]]></cell>\n");	
					}else{
						xml.append("  <cell><![CDATA[ "+invoiceNum +"]]></cell>\n");
					}
					String paidDate ="";
					String invoiceDate="";
					String dueDate="";
					if(invoice.getInvoiceDate()!=null){
//						invoiceDate =  DateLocalizationUtil.localizeDateTime(DateUtil.adjustDateWithOffset(invoice.getInvoiceDate(), timezoneOffset), false, locale);
						invoiceDate =  INVOICE_DATE_FORMAT.format(invoice.getInvoiceDate());
					}
					xml.append("  <cell><![CDATA[" + invoiceDate + "]]></cell>\n");	
					if(invoice.getDueDate()!=null){
//						dueDate =  DateLocalizationUtil.localizeDateTime(DateUtil.adjustDateWithOffset(invoice.getDueDate(), timezoneOffset), false, locale);
						dueDate = INVOICE_DATE_FORMAT.format(invoice.getDueDate());
					}
					xml.append("  <cell><![CDATA[" + dueDate + "]]></cell>\n");
					if(invoice.getPaidDate()!=null){
						logger.debug("paid date "+invoice.getPaidDate().toString());
//						paidDate =  DateLocalizationUtil.localizeDateTime(DateUtil.adjustDateWithOffset(invoice.getPaidDate(), timezoneOffset), false, locale);
						paidDate = INVOICE_DATE_FORMAT.format(invoice.getPaidDate());
					}
					xml.append("  <cell><![CDATA[" + paidDate + "]]></cell>\n");
					
				
					//xml.append("  <cell><![CDATA[" + invoice.getStatus() + "]]></cell>\n");
					if("close".equalsIgnoreCase(invoice.getStatus())){
						xml.append("  <cell><![CDATA[" + lovValues.get("CLOSE") + "]]></cell>\n");	
					}
					else if("open".equalsIgnoreCase(invoice.getStatus())){
						xml.append("  <cell><![CDATA[" + lovValues.get("OPEN") + "]]></cell>\n");
					}
					else if(invoice.getStatus() !=null){
						xml.append("  <cell><![CDATA[" + invoice.getStatus() + "]]></cell>\n");
					}else{
						xml.append("  <cell><![CDATA[" + "" + "]]></cell>\n");
					}
					
					xml.append("  <cell><![CDATA[" + invoice.getAmount()+"(" +invoice.getCurrencyType()+")]]></cell>\n");
					if(StringUtils.isNotBlank(invoice.getSapDocId2())){
						xml.append("  <cell><![CDATA[<a class=\"invoicelink\" target=\"_blank\" href=\""+URLEncryptUtilForCustomerInvoice.encrypt(invoice.getSapDocId2())+"\">"+invoiceDetail+"</A>]]></cell>\n");
					}else{
						xml.append("  <cell><![CDATA["+invoiceDetail+"]]></cell>\n");
					}
					
					
					xml.append(" </row>\n");
				}
				xml.append("</rows>\n");
			}
			logger.debug("XmlOutputGenerator.convertInvoiceAPListToXML--Exit:");
		}catch(Exception ex){
			logger.debug("Exception Occured");
			ex.getMessage();
		}
		return xml.toString();
	}
	/**
	 * added for customerInvoice
	 * @param accountsRcvblList 
	 * @param size 
	 * @param posStart 
	 * @return String 
	 */
	public String convertAccountsRcvblListToXML(List<AccountCustomerReceivable> accountsRcvblList, int size, int posStart) {
		StringBuffer xml = new StringBuffer("<?xml version=\"1.0\" ?>\n");
		xml.append("<rows total_count=\"" + size + "\" pos=\"" + posStart+ "\">\n");
		int i=0;		
		for (AccountCustomerReceivable accountsRcvbl: accountsRcvblList) {
			for (CompanyCode compCode: accountsRcvbl.getCompanyCodes()) {
			String accName = accountsRcvbl.getAccountName() != null ? XMLEncodeUtil.escapeXML(accountsRcvbl.getAccountName()) : "";			
			String billToAddressLine1= accountsRcvbl.getBillAddress() != null ? XMLEncodeUtil.escapeXML(accountsRcvbl.getBillAddress().getAddressLine1()) : "";			
			String billToAddressLine2= accountsRcvbl.getBillAddress() != null ? XMLEncodeUtil.escapeXML(accountsRcvbl.getBillAddress().getAddressLine2()) : "";
			String officeNumber= accountsRcvbl.getBillAddress() != null ? XMLEncodeUtil.escapeXML(accountsRcvbl.getBillAddress().getOfficeNumber()) : "";
			String billToCity= accountsRcvbl.getBillAddress() != null ? XMLEncodeUtil.escapeXML(accountsRcvbl.getBillAddress().getCity()) : "";
			String billToCounty=accountsRcvbl.getBillAddress() != null ? XMLEncodeUtil.escapeXML(accountsRcvbl.getBillAddress().getCounty()) : "";
			String billToState= accountsRcvbl.getBillAddress() != null ? XMLEncodeUtil.escapeXML(accountsRcvbl.getBillAddress().getState()) : "";
			String billToProvince= accountsRcvbl.getBillAddress() != null ? XMLEncodeUtil.escapeXML(accountsRcvbl.getBillAddress().getProvince()) : "";
			String billToCountry= accountsRcvbl.getBillAddress() != null ? XMLEncodeUtil.escapeXML(accountsRcvbl.getBillAddress().getCountry()) : "";
			String billToPostalCode= accountsRcvbl.getBillAddress() != null ? XMLEncodeUtil.escapeXML(accountsRcvbl.getBillAddress().getPostalCode()) : "";
			String billToDistrict= accountsRcvbl.getBillAddress() != null ? XMLEncodeUtil.escapeXML(accountsRcvbl.getBillAddress().getDistrict()) : "";
			String companycode=accountsRcvbl.getSoldTo() != null ? XMLEncodeUtil.escapeXML(accountsRcvbl.getSoldTo()):"";
			//String customerNumber=accountsRcvbl.getCustomerNumber() != null ? XMLEncodeUtil.escapeXML(accountsRcvbl.getCustomerNumber()):"";
			String companyDesc=compCode.getCompanyDescription()!= null ? XMLEncodeUtil.escapeXML(compCode.getCompanyDescription()):"";
			String companyValue=compCode.getCompanyValue()!= null ? XMLEncodeUtil.escapeXML(compCode.getCompanyValue()):"";
			StringBuilder address=new StringBuilder();
			if(StringUtils.isNotBlank(billToAddressLine1)){
				address.append("<div>").append(billToAddressLine1).append("</div>");
			}
			if(StringUtils.isNotBlank(officeNumber)){
				address.append("<div>").append(officeNumber).append("</div>");
			}
			
			if(StringUtils.isNotBlank(billToAddressLine2)){
				address.append("<div>").append(billToAddressLine2).append("</div>");
			}
			address.append("<div>");
			if(StringUtils.isNotBlank(billToCity)){
				address.append(billToCity);
			}
			if(StringUtils.isNotBlank(billToCounty)){
				address.append(",&nbsp;").append(billToCounty);
			}
			if (StringUtils.isNotBlank(billToState)) {
				address.append(",&nbsp;").append(billToState);
				
			}
			if(StringUtils.isNotBlank(billToProvince)) {
				address.append(",&nbsp;").append(billToProvince);
				
			}
			if(StringUtils.isNotBlank(billToDistrict)) {
				address.append(",&nbsp;").append(billToDistrict);			
			}
			address.append("</div><div>").append(billToPostalCode).append("</div>");
			if(StringUtils.isNotBlank(billToCountry)){
				address.append("<div>").append(billToCountry).append("</div>");
			}
			xml.append(" <row id=\"" + i + "\">\n");			
			xml.append("  <cell><![CDATA[" + accName+ "]]></cell>\n");
			xml.append("  <cell><![CDATA[" +address+"]]></cell>\n");
			xml.append("  <cell><![CDATA[" + companycode+ "]]></cell>\n");
			xml.append("  <cell><![CDATA[" + companyValue+ "]]></cell>\n");
			xml.append("  <cell><![CDATA[" + companyDesc+ "]]></cell>\n");
			
			
			
			xml.append("  <cell><![CDATA[ <input name=\"btn_select\" class=\"button\"  type=\"button\" id=\"button"+i+"\" " +
			"onclick=\"setPayeeAccount("+i+");\" value=\""	+ getLocalizeSelectButton() + "\"/>]]></cell>\n");
			xml.append(" </row>\n");
			i++;
		}
		}			
				
		xml.append("</rows>\n");
		return xml.toString();
		
	}
	/**
	 * @param parts 
	 * @param size 
	 * @param posStart 
	 * @param patters 
	 * @param request 
	 * @return String 
	 */
	public String generatePendingSHipment(List<Part> parts, int size, int posStart,String patters[],PortletRequest request) {
		int sizeCount=0;
		StringBuffer xml = new StringBuffer();
		int i=0;
		for (int j=0;j<parts.size();j++) {
			Part parentPart=parts.get(j);
			String partBundleId=parentPart.getCatalogId();
			if(parentPart.getCatalogType()!=null){
				logger.debug("parentPart.getCatalogType()"+parentPart.getCatalogType());
				StringBuffer xmlRow=new StringBuffer();
				StringBuffer tempXmlRow=new StringBuffer();
				if(parentPart.getCatalogType().equalsIgnoreCase("Hardware Bundles")){
					
					if(parentPart.getBundleParentLineId()==null || parentPart.getBundleParentLineId().equals("")){
						//sizeCount++;
						boolean sectionAppear = false;
						logger.debug("Parent Bundle");
						
//							if(size>1){
//								xmlRow.append("  <cell><![CDATA[<div class=\"dhx_sub_row\"><table class=\"displayGrid\"><thead><tr><th>Part Number</th><th>Description</th><th>Part Type</th><th>Quantity</th></tr></thead>");
//								}
								for(int k=0;k<parts.size();k++){
									Part part=parts.get(k);
									if(partBundleId !=null && part.getBundleParentLineId()!=null && partBundleId.equalsIgnoreCase(part.getBundleParentLineId())){
										sectionAppear = true;
										logger.debug("sectionAppear ::: " + sectionAppear);
										tempXmlRow.append("<tr>");
										tempXmlRow.append("<td>"+part.getPartNumber() +"</td>");
										tempXmlRow.append("<td>"+part.getDescription() +"</td>");
										tempXmlRow.append("<td>"+part.getDeviceType() +"</td>");
										tempXmlRow.append("<td>"+Integer.parseInt(part.getOrderQuantity()) / Integer.parseInt(parentPart.getOrderQuantity()) +"</td>");								
										tempXmlRow.append("</tr>");
										part.setBundleParentLineId("donee");	
									}
								}

						if(sectionAppear){	
							sizeCount++;
							if(size>1){
								xmlRow.append("  <cell><![CDATA[<div class=\"dhx_sub_row\"><table class=\"displayGrid\"><thead><tr><th>Part Number</th><th>Description</th><th>Part Type</th><th>Quantity</th></tr></thead>");
								xmlRow.append(tempXmlRow.toString());
								xmlRow.append("</table></div>]]></cell>\n");
							}
							xmlRow.append("  <cell><![CDATA[" + parentPart.getPartNumber()+ "]]></cell>\n");
							xmlRow.append("  <cell><![CDATA[" + parentPart.getDescription()+ "]]></cell>\n");
							xmlRow.append("  <cell><![CDATA[" + parentPart.getDeviceType()+ "]]></cell>\n");
							xmlRow.append("  <cell><![CDATA[" + parentPart.getOrderQuantity()+ "]]></cell>\n");
							xmlRow.append("  <cell><![CDATA[" + parentPart.getDeviceType()+ "]]></cell>\n");
							if("0".equalsIgnoreCase(parentPart.getTotalPrice()) || parentPart.getTotalPrice() == null){
								xmlRow.append("  <cell><![CDATA[" + parentPart.getTotalPrice()+ "]]></cell>\n");
							}else{
								xmlRow.append("  <cell><![CDATA[" + String.format("%.2f",Float.parseFloat(parentPart.getTotalPrice()))+" ("+parentPart.getCurrency()+")"+ "]]></cell>\n");
							}
						}else{
							sizeCount++;
							xmlRow.append("  <cell><![CDATA[]]></cell>\n");
							xmlRow.append("  <cell><![CDATA[" + parentPart.getPartNumber()+ "]]></cell>\n");
							xmlRow.append("  <cell><![CDATA[" + parentPart.getDescription()+ "]]></cell>\n");
							xmlRow.append("  <cell><![CDATA[" + parentPart.getDeviceType()+ "]]></cell>\n");
							xmlRow.append("  <cell><![CDATA[" + parentPart.getOrderQuantity()+ "]]></cell>\n");
							xmlRow.append("  <cell><![CDATA[" + parentPart.getDeviceType()+ "]]></cell>\n");
							if("0".equalsIgnoreCase(parentPart.getTotalPrice()) || parentPart.getTotalPrice() == null){
								xmlRow.append("  <cell><![CDATA[" + parentPart.getTotalPrice()+ "]]></cell>\n");
							}else{
								xmlRow.append("  <cell><![CDATA[" + String.format("%.2f",Float.parseFloat(parentPart.getTotalPrice()))+" ("+ parentPart.getCurrency()+ ")"+ "]]></cell>\n");
							}
						}
						
					}						
				}else{
					sizeCount++;
					xmlRow.append("  <cell><![CDATA[]]></cell>\n");
					xmlRow.append("  <cell><![CDATA[" + parentPart.getPartNumber()+ "]]></cell>\n");
					xmlRow.append("  <cell><![CDATA[" + parentPart.getDescription()+ "]]></cell>\n");
					xmlRow.append("  <cell><![CDATA[" + parentPart.getDeviceType()+ "]]></cell>\n");
					xmlRow.append("  <cell><![CDATA[" + parentPart.getOrderQuantity()+ "]]></cell>\n");
					xmlRow.append("  <cell><![CDATA[" + parentPart.getDeviceType()+ "]]></cell>\n");
					if("0".equalsIgnoreCase(parentPart.getTotalPrice()) || parentPart.getTotalPrice() == null){
						xmlRow.append("  <cell><![CDATA[" + parentPart.getTotalPrice()+ "]]></cell>\n");
					}else{
						xmlRow.append("  <cell><![CDATA[" + String.format("%.2f",Float.parseFloat(parentPart.getTotalPrice()))+" ("+ parentPart.getCurrency()+ ")"+ "]]></cell>\n");
					}
				}
				
				if(xmlRow.length()!=0){
					xmlRow.insert(0,"<row id=\"" + (posStart + i++) + "\">\n");
					xmlRow.insert(xmlRow.length(),"</row>\n");
					
					xml.append(xmlRow);
				}
			}
		}
		xml.append("</rows>\n");
		xml.insert(0, "<?xml version=\"1.0\" ?>\n <rows total_count=\"" + sizeCount + "\" pos=\"" + posStart+ "\">\n");
		return xml.toString();
	}
	
	/**
	 * 
	 * @param pageCountsdata 
	 * @param totalCount 
	 * @param posStart 
	 * @param pageCountsMap 
	 * @return String 
	 */
	public String pageCountsXML(List<PageCounts> pageCountsdata, int totalCount,int posStart, Map<String, String> pageCountsMap) {
		StringBuffer xml = new StringBuffer("<?xml version=\"1.0\" ?>\n");
		xml = xml.append("<rows total_count=\""+totalCount+"\" pos=\""+posStart+"\">\n");
		
		for(int i = 0; i < pageCountsdata.size(); i ++){
			String LocalTypeList =null;
			String DateList =pageCountsdata.get(i).getDate();
			String TypeList =pageCountsdata.get(i).getName();
			String ValueList =pageCountsdata.get(i).getCount();
			Iterator it = pageCountsMap.entrySet().iterator();
		    while (it.hasNext()) {
		        Map.Entry pairs = (Map.Entry)it.next();
		        if(TypeList.equalsIgnoreCase(pairs.getKey().toString())){
		        	LocalTypeList=pairs.getValue().toString();
		        }
		    }
			xml = xml.append("<row id=\""+ (posStart + i)+"\">\n");
			xml.append("  <cell><![CDATA[" +LocalTypeList + "]]></cell>\n");
			if(DateList!=null){
				xml.append("  <cell><![CDATA[" +DateList+"]]></cell>\n");
			}else{
				xml.append("  <cell><![CDATA[" +""+"]]></cell>\n");
			}
			if(ValueList!=null){
				xml.append("  <cell><![CDATA[" +ValueList + "]]></cell>\n");
			}else{
				xml.append("  <cell><![CDATA[" +""+"]]></cell>\n");
			}
			
			xml = xml.append(" </row>\n");	
		}
		xml.append("</rows>\n");
		
		return xml.toString();
	}
/**
 * 
 * @param pageCountsdata 
 * @param totalCount 
 * @param posStart 
 * @param pageCountsMap 
 * @param timeZoneOffset 
 * @param locale 
 * @return String 
 */
	public String pageCountsXMLForAsset(List<PageCounts> pageCountsdata, int totalCount,int posStart, Map<String, String> pageCountsMap,String timeZoneOffset,Locale locale) {
		StringBuffer xml = new StringBuffer("<?xml version=\"1.0\" ?>\n");
		xml = xml.append("<rows total_count=\""+totalCount+"\" pos=\""+posStart+"\">\n");
		
		for(int i = 0; i < pageCountsdata.size(); i ++){
			String LocalTypeList =null;
			String DateList =pageCountsdata.get(i).getDate();
			Date formatter = null;
			try {
				formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss", Locale.ENGLISH).parse(DateList);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				logger.debug("Parse Exception "+e.getMessage());
			}
			DateList=DateLocalizationUtil.localizeDateTime(DateUtil.adjustDateWithOffset(formatter, Float.parseFloat(timeZoneOffset)), true, locale);
			String TypeList =pageCountsdata.get(i).getName();
			String ValueList =pageCountsdata.get(i).getCount();
			Iterator it = pageCountsMap.entrySet().iterator();
		    while (it.hasNext()) {
		        Map.Entry pairs = (Map.Entry)it.next();
		        if(TypeList.equalsIgnoreCase(pairs.getKey().toString())){
		        	LocalTypeList=pairs.getValue().toString();
		        }
		    }
			xml = xml.append("<row id=\""+ (posStart + i)+"\">\n");
			xml.append("  <cell><![CDATA[" +LocalTypeList + "]]></cell>\n");
			if(DateList!=null){
				xml.append("  <cell><![CDATA[" +DateList+"]]></cell>\n");
			}else{
				xml.append("  <cell><![CDATA[" +""+"]]></cell>\n");
			}
			if(ValueList!=null){
				xml.append("  <cell><![CDATA[" +ValueList + "]]></cell>\n");
			}else{
				xml.append("  <cell><![CDATA[" +""+"]]></cell>\n");
			}
			
			xml = xml.append(" </row>\n");	
		}
		xml.append("</rows>\n");
		
		return xml.toString();
	}
	//Added for MPS 2.1 WAVE 4
	/**
	 * @param sActivityList 
	 * @param size 
	 * @param i 
	 * @param activityGeneratorPattern 
	 * @return String 
	 */
	public String getActivityDetails(List<ServiceRequestActivity> sActivityList, int size, int i, String[] activityGeneratorPattern) {
		logger.debug("XmlOutputGenerator.getActivityDetails.ENTER---------------");
		initialLocalizedMessage();
		int sizeCount=0;
		int posStart = 0;
		StringBuffer xml=new StringBuffer();
		sizeCount++;
		xml.append("<?xml version=\"1.0\" ?>\n <rows total_count=\"" + sizeCount + "\" pos=\"" + posStart+ "\">\n");
		
		for(ServiceRequestActivity srActivity:sActivityList){
			for(Asset assets:srActivity.getAssetDetails()){		    		
				xml.append("<row id=\"" + (posStart + i++) + "\">\n");
				xml.append("<cell><![CDATA[<div class=\"dhx_sub_row\"><div class=\"wHalf\" style=\"float: left; width: 400px ! important;\">");
				 xml.append("<table class=\"displayGrid\" style=\"width: 400px\"><thead><tr><th>"+PropertiesMessageUtil.getPropertyMessage(BUNDLENAME_BUTTON_MSG, "requestInfo.heading.partNumber", locale)+"</th><th>"+PropertiesMessageUtil.getPropertyMessage(BUNDLENAME_BUTTON_MSG, "requestInfo.heading.description", locale)+"</th><th>"+PropertiesMessageUtil.getPropertyMessage(BUNDLENAME_BUTTON_MSG, "requestInfo.heading.quantity", locale)+"</th><th>"+PropertiesMessageUtil.getPropertyMessage(BUNDLENAME_BUTTON_MSG, "requestInfo.tooltip.additionalInformation", locale)+"</th></tr></thead><tbody>");
		    		for(Part part:assets.getPartList()){
		    			
						xml.append("<tr>");
						xml.append("<td>"+ replaceNullWithBlankString(part.getPartNumber()==null?"":part.getPartNumber()) +"</td>");
						xml.append("<td>"+ replaceNullWithBlankString(part.getDescription()==null?"":part.getDescription()) +"</td>");
						xml.append("<td>"+ replaceNullWithBlankString(part.getOrderQuantity()==null?"":assets.getDescription()) +"</td>");
						//xml.append("</tr></table></div>");						
						xml.append("<td style=\"width: 150px;\">");	
						xml.append("<ul class=\"form\"><li style=\"padding-left: 0 !important;\">" + LB_DEVICEDESC + replaceNullWithBlankString(assets.getDescription()==null?"":assets.getDescription()));
						xml.append("</li><li style=\"padding-left: 0 !important;\">" + LB_CUSTASSETTAG + replaceNullWithBlankString(assets.getDeviceTag()==null?"":assets.getDeviceTag()));
						xml.append("</li><li style=\"padding-left: 0 !important;\">" + LB_INSTALLDATE + replaceNullWithBlankString(assets.getInstallDate()==null?"":assets.getInstallDate())); 
						xml.append("</li><li style=\"padding-left: 0 !important;\">" + LB_IPADDRESS + replaceNullWithBlankString(assets.getIpAddress()==null?"":assets.getIpAddress()));
						xml.append("</li><li style=\"padding-left: 0 !important;\">" + LB_HOSTNAME + replaceNullWithBlankString(assets.getHostName()==null?"":assets.getHostName())+ "</li></ul>");					
						xml.append("</td>");
						xml.append("</tr>");
						//xml.append("</table></div><div style=\"clear:both;\"></div></td></div>]]></cell>");
				
		    	}
		    	xml.append("</div></div>]]></cell>\n");
		    	xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(assets.getSerialNumber()==null?"":assets.getSerialNumber()) +"]]></cell>\n");
		    	xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(assets.getDeviceType()==null?"":assets.getDeviceType())+"]]></cell>\n");
		    	xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(assets.getActivityNumber()==null?"":assets.getActivityNumber())+"]]></cell>\n");
		    	xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(assets.getStatusDetail()==null?"":assets.getStatusDetail())+"]]></cell>\n");
		    	xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(assets.getBuilding()==null?"":assets.getBuilding())+"]]></cell>\n");
		    	xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(assets.getFloor()==null?"":assets.getFloor())  +"]]></cell>\n");
		    	xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(assets.getOffice()==null?"":assets.getOffice())+"]]></cell>\n");
		    	xml.append(" </row>\n");
		    	i++;
				}
		    	
		  }
		
		xml.append("</rows>\n");
		return xml.toString();
}
	
			
			
	
}