package com.lexmark.services.util.cm;

import java.util.List;
import java.util.Locale;

import javax.portlet.PortletSession;
import javax.portlet.ResourceRequest;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.contract.GeographyCountryContract;
import com.lexmark.contract.GeographyStateContract;
import com.lexmark.contract.LocalizedSiebelValueContract;
import com.lexmark.domain.Account;
import com.lexmark.domain.AccountContact;
import com.lexmark.domain.Asset;
import com.lexmark.domain.GenericAddress;
import com.lexmark.domain.ListOfValues;
import com.lexmark.domain.Part;
import com.lexmark.domain.ServiceRequest;
import com.lexmark.exceptionimpl.runtime.LGSRuntimeException;
import com.lexmark.framework.logging.LEXLogger;
import com.lexmark.result.LocalizedSiebelValueResult;
import com.lexmark.service.api.ServiceRequestLocale;
import com.lexmark.service.impl.real.jdbc.InfrastructureException;
import com.lexmark.service.impl.real.jdbc.ServiceRequestLocaleImpl;
import com.lexmark.services.LexmarkSPConstants;
import com.lexmark.services.util.ChangeMgmtConstant;
import com.lexmark.services.util.ContractFactory;
import com.lexmark.services.util.XMLEncodeUtil;
import com.lexmark.util.DateLocalizationUtil;
import com.lexmark.util.PropertiesMessageUtil;
import com.lexmark.util.TimezoneUtil;

/**
 * 
 * This class is for the customized xml generator methods for all assets, contacts, addresses and history
 * to be populated in the grid
 * @author Sourav
 * @version 2.1
 */


public class XMLOutputGeneratorUtil {

	private static final String CHANGE_IMAGE="edit.gif";
	private static final String REMOVE_IMAGE="delete.gif";
	private static final String FAVORIATE_IMAGE_FILE_NAME = "favorite.png";
	private static final String UNFAVORIATE_IMAGE_FILE_NAME = "unfavorite.png";
	private static final String IMAGE_PATH = "/images/";

	private static String LB_BOOKMARKED_DEVICE = "";
	private static String LB_REQUEST_SERVICE = "";
	private static String LB_CONTROL_PANEL = "";
	private static String LB_LOCATION = "";
	private static String LB_SUPPORT_DOWNLOADS = "";
	private static String LB_VIEW_MORE = "";
	private static String LB_PRIMARY_CONTACT = "";
	private static String LB_UNBOOKMARKED_DEVICE = "";
	private static String CHANGE_ASSET_INFORMATION = "";
	private static String DECOMISSION_THIS_ASSET= "";
	private static final Logger LOGGER = LogManager.getLogger(XMLOutputGeneratorUtil.class);
	private static final LEXLogger logger=LEXLogger.getLEXLogger(XMLOutputGeneratorUtil.class);

	private ServiceRequestLocale serviceRequestLocale;
	private Locale locale;
	
	public  XMLOutputGeneratorUtil(Locale locale){
		setLocale(locale);
	}

	public ServiceRequestLocale getServiceRequestLocale() {
    	if(serviceRequestLocale == null) {
    		serviceRequestLocale = new ServiceRequestLocaleImpl();
    	}
		return serviceRequestLocale;
	}
    
    
	public void setServiceRequestLocale(ServiceRequestLocale serviceRequestLocale) {
		this.serviceRequestLocale = serviceRequestLocale;
	}
	
	public Locale getLocale() {
		if(locale ==null) {
			locale = Locale.US;
		}
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}	

	private void initialLocalizedMessage() {
		LB_BOOKMARKED_DEVICE = PropertiesMessageUtil.getPropertyMessage(
				LexmarkSPConstants.MESSAGE_BUNDLE_NAME,
				"deviceFinder.label.bookmarkThisDevice", locale);
		LB_REQUEST_SERVICE = PropertiesMessageUtil.getPropertyMessage(
				LexmarkSPConstants.MESSAGE_BUNDLE_NAME,
				"deviceDetail.label.requestService", locale);
		LB_CONTROL_PANEL = PropertiesMessageUtil.getPropertyMessage(
				LexmarkSPConstants.MESSAGE_BUNDLE_NAME,
				"deviceDetail.label.controlPanel", locale);
		LB_LOCATION = PropertiesMessageUtil.getPropertyMessage(
				LexmarkSPConstants.MESSAGE_BUNDLE_NAME,
				"deviceDetail.label.installAddress", locale);
		
		LB_SUPPORT_DOWNLOADS = PropertiesMessageUtil.getPropertyMessage(
				LexmarkSPConstants.MESSAGE_BUNDLE_NAME,
				"deviceDetail.label.supportAndDownloads", locale);
		LB_VIEW_MORE = PropertiesMessageUtil.getPropertyMessage(
				LexmarkSPConstants.MESSAGE_BUNDLE_NAME,
				"deviceDetail.label.viewMore", locale);
		LB_PRIMARY_CONTACT = PropertiesMessageUtil.getPropertyMessage(
				LexmarkSPConstants.MESSAGE_BUNDLE_NAME,
				"serviceRequest.label.primaryContact", locale);
		
		LB_UNBOOKMARKED_DEVICE = PropertiesMessageUtil.getPropertyMessage(
				LexmarkSPConstants.MESSAGE_BUNDLE_NAME, 
				"orderSupplies.label.unbookmarkThisDevice", locale);
		
		CHANGE_ASSET_INFORMATION = PropertiesMessageUtil.getPropertyMessage(
				LexmarkSPConstants.MESSAGE_BUNDLE_NAME, 
				"requestInfo.cm.manageAsset.heading.changeAssetInformation", locale);

		DECOMISSION_THIS_ASSET= PropertiesMessageUtil.getPropertyMessage(
			LexmarkSPConstants.MESSAGE_BUNDLE_NAME, 
			"requestInfo.cm.manageAsset.heading.decommissionThisAsset", locale);

	}

/**
 * This method is used for populating the device/asset list in the grid
 * 
 * @param request
 * @param assets
 * @param totalCount
 * @param posStart
 * @param contextPath
 * @param userSiebelContactId
 * @param type
 * @return
 */
	public String convertAssetToXML(ResourceRequest request,
			List<Asset> assets, int totalCount, int posStart,
			String contextPath, String userSiebelContactId, String type) {
		initialLocalizedMessage();
		PortletSession session = request.getPortletSession();
		String createServiceRequestFlag = (String) session
				.getAttribute("createServiceRequestFlag");
		StringBuffer xml = new StringBuffer(
				"<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		xml.append("<rows total_count=\"" + totalCount + "\" pos=\"" + posStart
				+ "\">\n");
		int i = 0;
		String imageSrc;
		String imageName;
		String provinceOrState;
		
		String changeAssetImgSrc=contextPath + IMAGE_PATH+ "gridImgs/"+ CHANGE_IMAGE;
		String transparentImageSrc=contextPath + IMAGE_PATH +"transparent.png";
		String removeAssetImgSrc=contextPath + IMAGE_PATH+ "gridImgs/"+ REMOVE_IMAGE;
		boolean deviceBookmarked = false;
		
		String changeTitle = PropertiesMessageUtil.getPropertyMessage(
				LexmarkSPConstants.MESSAGE_BUNDLE_NAME,
				"requestInfo.tooltip.asset.edit", locale);
		String removeTitle = PropertiesMessageUtil.getPropertyMessage(
				LexmarkSPConstants.MESSAGE_BUNDLE_NAME,
				"requestInfo.tooltip.asset.delete", locale);
		
		for (Asset asset : assets) {
			if (asset.getInstallAddress().getProvince() != null
					&& asset.getInstallAddress().getProvince().length() > 0) {
				provinceOrState = XMLEncodeUtil.escapeXMLForDMCM(asset.getInstallAddress().getProvince());
			} else {
				provinceOrState = XMLEncodeUtil.escapeXMLForDMCM(asset.getInstallAddress().getState());
			}
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
										
			xml.append("<cell><![CDATA[<img class=\"helpIconList ui_icon_sprite edit-icon\" title='"+ changeTitle +"' src='"+transparentImageSrc+"'onclick=javascript:manageAssetRequestService('" +
			"changeAssetRequest','"	+asset.getAssetId()+"'); style=\"cursor:pointer\">]]></cell>");//This row generates image for change asset			
			
			xml.append("<cell><![CDATA[<img class=\"helpIconList ui_icon_sprite trash-icon\" title='"+ removeTitle +"' src='"+transparentImageSrc+"'onclick=javascript:manageAssetRequestService('" +
			"decommissionAssetRequest','"	+asset.getAssetId()+"'); style=\"cursor:pointer\">]]></cell>");//This row generates image for change asset
			
			xml.append("<cell><![CDATA[");
			xml.append("<table width='800px' height='140px'>");
			xml.append("<tr><td valign='top' width='15%'>");

			xml.append("<table>");
			
			if(!deviceBookmarked){
			xml.append("<tr><td align=\"center\" class='labelBold'>"
					+ XMLEncodeUtil.escapeXMLForDMCM(asset.getDescriptionLocalLang()) + "</td></tr>");
			xml.append("<tr><td align=\"center\" height='80px'><img src='"
					+ asset.getProductImageURL()
					+ "' class='printer'id=\"rowid_"+(posStart + i)+"\" onError=\"image_error(this.id);\"/></td></tr>");
			xml.append("<tr><td align='left'  height='22px'><img class='ui_icon_sprite removebookmark-icon' id = 'starIMG_"
					+ XMLEncodeUtil.escapeXMLForDMCM(asset.getAssetId())
					+ "' style='cursor:Hand;cursor:pointer' onClick=updateUserFavoriteAsset('"
					+ XMLEncodeUtil.escapeXMLForDMCM(asset.getAssetId())
					+ "') src='"
					+ transparentImageSrc
					+ "' name='"
					+ imageName
					+ "'/>&nbsp;<a id='starTXT_"+asset.getAssetId()+"' href='#' onClick=updateUserFavoriteAsset('"
					+ XMLEncodeUtil.escapeXMLForDMCM(asset.getAssetId())
					+ "')>"
					+ LB_BOOKMARKED_DEVICE
					+ "</a></tr></td>");
			}
			else{
				
				xml.append("<tr><td align=\"center\" class='labelBold'>"
						+ XMLEncodeUtil.escapeXMLForDMCM(asset.getProductLine()) + "</td></tr>");
				xml.append("<tr><td align=\"center\" height='80px'><img src='"
						+ asset.getProductImageURL()
						+ "' class='printer'id=\"rowid_"+(posStart + i)+"\" onError=\"image_error(this.id);\"/></td></tr>");
				xml.append("<tr><td align='left'  height='22px'><img class='ui_icon_sprite bookmark-star-icon' id = 'starIMG_"
						+ XMLEncodeUtil.escapeXMLForDMCM(asset.getAssetId())
						+ "' style='cursor:Hand;cursor:pointer' onClick=updateUserFavoriteAsset('"
						+ XMLEncodeUtil.escapeXMLForDMCM(asset.getAssetId())
						+ "') src='"
						+ transparentImageSrc
						+ "' name='"
						+ imageName
						+ "'/>&nbsp;<a id='starTXT_"+asset.getAssetId()+"' href='#' onClick=updateUserFavoriteAsset('"
						+ XMLEncodeUtil.escapeXMLForDMCM(asset.getAssetId())
						+ "')>"
						+ LB_UNBOOKMARKED_DEVICE
						+ "</a></tr></td>");
			}
			
			xml.append("</table>");
			xml.append("</td>");
			if (!LexmarkSPConstants.ASSET_LIST_TYPE.equals(type)) {
				xml.append("<td valign='top' width='15%'>");
				xml.append("<table>");
				if ("true".equals(createServiceRequestFlag)) {
					xml.append("<tr><td align='left'><a href=\"###\"; onClick=\"requestService('"
							+ asset.getAssetId()
							+ "')\";>&gt;&gt;"
							+ LB_REQUEST_SERVICE + "</a></td></tr>");
				}
				if (asset.getHostName() != null
						&& !asset.getHostName().trim().equals("")
						|| asset.getIpAddress() != null
						&& !asset.getIpAddress().trim().equals("")) {
					xml.append("<tr><td align='left'><a href=\"javascript:void(0)\" onClick=\"gotoControlPanel('"
							+ asset.getControlPanelURL()
							+ "')\">&gt;&gt;"
							+ LB_CONTROL_PANEL + "</a></td></tr>");
				}
				xml.append("<tr><td align='left'><a href=\""
						+ asset.getSupportUrl()
						+ "\" target=\"_blank\">&gt;&gt;"
						+ LB_SUPPORT_DOWNLOADS + "</a></td></tr>");
				xml.append("</table>");
				xml.append("</td>");
			}
			xml.append("<td valign='top' width='15%'>");
			xml.append("<table>");
			xml.append("<tr><td align='left' class='labelBold'>" + LB_LOCATION
					+ ":</td></tr>");
			xml.append("<tr><td align='left'>"
					+ XMLEncodeUtil.escapeXMLForDMCM(asset.getInstallAddress().getAddressLine1())
					+ "</td></tr>");
			xml.append("<tr><td align='left'>"
					+ XMLEncodeUtil.escapeXMLForDMCM(asset.getInstallAddress().getOfficeNumber())
					+ "</td></tr>");
			xml.append("<tr><td align='left'>"
					+ XMLEncodeUtil.escapeXMLForDMCM(asset.getInstallAddress().getAddressLine2())
					+ "</td></tr>");
//			xml.append("<tr><td align='left'>"
//					+ asset.getInstallAddress().getAddressLine3()
//					+ "</td></tr>");
			xml.append("<tr><td align='left'>");
			xml.append(asset.getInstallAddress().getCity());
			if(asset.getInstallAddress().getCounty()!=null && !StringUtils.isEmpty(asset.getInstallAddress().getCounty())){
				xml.append(", " + asset.getInstallAddress().getCounty());
			}
			if(asset.getInstallAddress().getState()!=null && !StringUtils.isEmpty((asset.getInstallAddress().getState()))){
				xml.append(", " + asset.getInstallAddress().getState());
			}
			if(asset.getInstallAddress().getProvince()!=null && !StringUtils.isEmpty((asset.getInstallAddress().getProvince()))){
				if(asset.getInstallAddress().getDistrict()!=null && !StringUtils.isEmpty((asset.getInstallAddress().getDistrict()))){
					xml.append(", "+asset.getInstallAddress().getProvince());
					xml.append(", " + asset.getInstallAddress().getDistrict());
				}else{
					xml.append(", "+asset.getInstallAddress().getProvince() + "</td></tr>");
				}
			}
			
			xml.append("<tr><td align='left'>");
			xml.append(XMLEncodeUtil.escapeXMLForDMCM(asset.getInstallAddress().getPostalCode())+ "</td></tr>" );
			xml.append("<tr><td align='left'>");
			xml.append( XMLEncodeUtil.escapeXMLForDMCM(asset.getInstallAddress().getCountry())  + "</td></tr>");
			xml.append("</table>");
			xml.append("</td>");
			/*Changes*/
			// Below table is for primary Contact
			xml.append("<td valign='top' width='15%'>");
			xml.append("<table>");
			xml.append("<tr><td align='left'class='labelBold'>"
					+ LB_PRIMARY_CONTACT
					+ "</td></tr>");
			xml.append("<tr><td align='left'>"
					+ XMLEncodeUtil.escapeXMLForDMCM(asset.getAssetContact().getFirstName())+" "
					+XMLEncodeUtil.escapeXMLForDMCM(asset.getAssetContact().getLastName())
					+ "</td></tr>");
			xml.append("<tr><td align='left'>"
					+ "Phone:"+XMLEncodeUtil.escapeXMLForDMCM(asset.getAssetContact().getWorkPhone())
					+ "</td></tr>");
			xml.append("</table>");
			xml.append("</td>");
			//Below table is for Decommision and Change Asset Links
			xml.append("<td valign='top' width='15%'>");
			xml.append("<table>");
			xml.append("<tr><td align='left'>"
					+ "<a href=\"javascript:manageAssetRequestService('changeAssetRequest','"
					+ XMLEncodeUtil.escapeXMLForDMCM(asset.getAssetId())+"');\">"+CHANGE_ASSET_INFORMATION+"</a>"
					+ "</td></tr>");
			xml.append("<tr><td align='left'>"
					+ "<a href=\"javascript:manageAssetRequestService('decommissionAssetRequest','"
					+ XMLEncodeUtil.escapeXMLForDMCM(asset.getAssetId())+"');\">"+DECOMISSION_THIS_ASSET+"</a>"
					+ "</td></tr>");
			xml.append("</table>");
			xml.append("</td>");
			/* End Changes*/

			
			if (!LexmarkSPConstants.ASSET_LIST_TYPE.equals(type)) {
				xml.append("<td valign='bottom' width='15%' nowrap>");
				xml.append("<table><tr><td><a href=\"###\"; onClick=\"viewDeviceDetail('"
						+ asset.getAssetId()
						+ "','"
						+ asset.getSerialNumber()
						+ "')\";>" + LB_VIEW_MORE + "</a></td></tr></table>");
				xml.append("</td>");
			}
			xml.append("</tr>");
			xml.append("</table>");
			xml.append("]]></cell>\n");
			if (!LexmarkSPConstants.ASSET_LIST_TYPE.equals(type)) {
				xml.append("  <cell><![CDATA[<a href=\"###\" onClick=\"viewDeviceDetail('"
						+ asset.getAssetId()
						+ "','"
						+ asset.getSerialNumber()
						+ "')\">"
						+ asset.getSerialNumber()
						+ "</a>]]></cell>\n");
			} else {

				xml.append("  <cell><![CDATA[" + XMLEncodeUtil.escapeXMLForDMCM(asset.getSerialNumber())
						+ "]]></cell>\n");
			}
			xml.append("  <cell><![CDATA[" + XMLEncodeUtil.escapeXMLForDMCM(asset.getDescriptionLocalLang())
					+ "]]></cell>\n");
			xml.append("  <cell><![CDATA[" + XMLEncodeUtil.escapeXMLForDMCM(asset.getAssetTag())
					+ "]]></cell>\n");
			xml.append("  <cell><![CDATA[" + XMLEncodeUtil.escapeXMLForDMCM(asset.getDeviceTag())
					+ "]]></cell>\n");
			xml.append("  <cell><![CDATA[" + XMLEncodeUtil.escapeXMLForDMCM(asset.getHostName())
					+ "]]></cell>\n");
			xml.append("  <cell><![CDATA[" + XMLEncodeUtil.escapeXMLForDMCM(asset.getIpAddress())
					+ "]]></cell>\n");
			xml.append("  <cell><![CDATA["
					+ (asset.getDevicePhase() == null ? "" : XMLEncodeUtil.escapeXMLForDMCM(asset
							.getDevicePhase())) + "]]></cell>\n");
			xml.append("  <cell><![CDATA[" + XMLEncodeUtil.escapeXMLForDMCM(asset.getAccount().getAccountName())
					+ "]]></cell>\n");
			xml.append("  <cell><![CDATA["
					+ XMLEncodeUtil.escapeXMLForDMCM(asset.getInstallAddress().getAddressName())
					+ "]]></cell>\n");
			xml.append("  <cell><![CDATA["
					+ XMLEncodeUtil.escapeXMLForDMCM(asset.getInstallAddress().getAddressLine1())
					+ "]]></cell>\n");
			xml.append("  <cell><![CDATA[" + XMLEncodeUtil.escapeXMLForDMCM(asset.getInstallAddress().getOfficeNumber())
					+ "]]></cell>\n");
			xml.append("  <cell><![CDATA["
					+ XMLEncodeUtil.escapeXMLForDMCM(asset.getInstallAddress().getCity()) + "]]></cell>\n");
			if(asset.getInstallAddress().getState() !=null){
				xml.append("  <cell><![CDATA["
						+ XMLEncodeUtil.escapeXMLForDMCM(asset.getInstallAddress().getState())+ "]]></cell>\n");
			}
			if (LexmarkSPConstants.ASSET_LIST_TYPE.equals(type)) {
				xml.append("  <cell><![CDATA["
						+ XMLEncodeUtil.escapeXMLForDMCM(asset.getInstallAddress().getProvince())+ "]]></cell>\n");
			}
			xml.append("  <cell><![CDATA["
					+ XMLEncodeUtil.escapeXMLForDMCM(asset.getInstallAddress().getCounty()) + "]]></cell>\n");
			xml.append("  <cell><![CDATA["
					+ XMLEncodeUtil.escapeXMLForDMCM(asset.getInstallAddress().getDistrict()) + "]]></cell>\n");
			xml.append("  <cell><![CDATA["
					+ XMLEncodeUtil.escapeXMLForDMCM(asset.getInstallAddress().getCountry()) + "]]></cell>\n");
			xml.append("  <cell><![CDATA["
					+ XMLEncodeUtil.escapeXMLForDMCM(asset.getInstallAddress().getPostalCode()) + "]]></cell>\n");
			xml.append("  <cell><![CDATA["
					+ XMLEncodeUtil.escapeXMLForDMCM(asset.getAssetContact().getFirstName()) + "]]></cell>\n");
			xml.append("  <cell><![CDATA["
					+ XMLEncodeUtil.escapeXMLForDMCM(asset.getAssetContact().getLastName()) + "]]></cell>\n");
			xml.append("  <cell><![CDATA["
					+ XMLEncodeUtil.escapeXMLForDMCM(asset.getAssetContact().getEmailAddress()) + "]]></cell>\n");
			xml.append("  <cell><![CDATA["
					+ XMLEncodeUtil.escapeXMLForDMCM(asset.getAssetContact().getWorkPhone()) + "]]></cell>\n");
			xml.append(" </row>\n");
			i++;
		}
		xml.append("</rows>\n");
		//logger.info(xml);
		return xml.toString();
	}

	/**
	 * This method is used for populating the contact list in the grid 
	 * @param contacts
	 * @param totalCount
	 * @param posStart
	 * @param contextPath
	 * @param isPopUp
	 * @return
	 */
	public String convertContactListToXML(List<AccountContact> contacts,
			int totalCount, int posStart, String contextPath, Boolean isPopUp) throws LGSRuntimeException {
		
		LOGGER.debug("convertContactListToXML isPopUp= "+isPopUp);

		StringBuffer xml = new StringBuffer("<?xml version=\"1.0\" ?>\n");
		xml.append("<rows total_count=\"" + totalCount + "\" pos=\"" + posStart
				+ "\">\n");
		int i = 0;
		//String pencilImageSrc;
		String imageSrc;
		String bookmarkTitle;
		String changeTitle = PropertiesMessageUtil.getPropertyMessage(
				LexmarkSPConstants.MESSAGE_BUNDLE_NAME,
				"requestInfo.tooltip.contact.edit", locale);
		String removeTitle = PropertiesMessageUtil.getPropertyMessage(
				LexmarkSPConstants.MESSAGE_BUNDLE_NAME,
				"requestInfo.tooltip.contact.delete", locale);
		//pencilImageSrc = contextPath + IMAGE_PATH + PENCIL_IMAGE_FILE_NAME;
		
		String changeImgSrc=contextPath + IMAGE_PATH+ "gridImgs/"+ CHANGE_IMAGE;	
		String transparentImageSrc=contextPath + IMAGE_PATH +"transparent.png";
		String removeImgSrc=contextPath + IMAGE_PATH+ "gridImgs/"+ REMOVE_IMAGE;
		String bookmarkClassName;
		try {	
		for (AccountContact accountContact : contacts) {
			xml.append(" <row id=\"" + (accountContact.getContactId())
					+ "\">\n");

			/*
			 * If grid is on main page, show the change and remove contact images
			 */		
			if(!isPopUp){
			/*
			 * Changes MPS 2.1
			 * */	
			xml.append("<cell><![CDATA[<table style=\"width:100%;\"><tr><td><div class=\"buttonDivContainer\"><button name=\"cancel\" class=\"button_cancel button_subrow1\" id=\"_"+accountContact.getContactId()+"\">"+
					PropertiesMessageUtil.getPropertyMessage(
							LexmarkSPConstants.MESSAGE_BUNDLE_NAME,"button.cancel", locale)+
					"</button><button class=\"button button_subrow2\" name=\"save\" id=\"_"+accountContact.getContactId()+"\">"+ PropertiesMessageUtil.getPropertyMessage(
					LexmarkSPConstants.MESSAGE_BUNDLE_NAME,"button.save", locale)+"</button></div></td></tr></table>]]></cell>");	
			xml.append("<cell><![CDATA[<img id=\"chngContactDialog_"+accountContact.getContactId()+"\" class=\"changeContactDialog ui_icon_sprite edit-icon\" src='"+transparentImageSrc+"' />]]></cell>");//This row generates image for change contact
			
			xml.append("<cell><![CDATA[<img name=\"remove\" class=\"helpIconList ui_icon_sprite trash-icon\" title=\""+ removeTitle.trim() +"\" src='"+transparentImageSrc+"' id=\"remove_"+accountContact.getContactId()+"\"/>]]></cell>");//This row generates image for remove contact
			/*Ends changes MPS 2.1*/
			}
			
			
			
			
			/*
			 * Show the edit and select button for grid in popup
			 */
			if(isPopUp){
				
				boolean managedContactFlagPopup = accountContact.isManageContactFlag();
				//LOGGER.debug("manageContactFlag="+managedContactFlagPopup);;
				
				if (!managedContactFlagPopup) {
				String editButton=PropertiesMessageUtil.getPropertyMessage(
						LexmarkSPConstants.MESSAGE_BUNDLE_NAME,
						"button.edit", locale);	
				xml.append("<cell><![CDATA[ <input name=\"edit\" class=\"button\" value=\""+editButton+"\"  type=\"button\" onclick=\"changeToEdit('"
						+ accountContact.getContactId() + "',this"
						+ ")\" style=\"cursor:pointer;\"/>]]></cell>\n");	
				}
				else {
					xml.append("<cell></cell>\n");
				}
				
				xml.append("  <cell><![CDATA[ <input id=\"btn_select_"+ XMLEncodeUtil.escapeXMLForDMCM(accountContact.getContactId())
						+"\" class=\"button\"  type=\"button\" onclick=\"selectContact('"
						+ StringEscapeUtils.escapeJavaScript(accountContact.getContactId()!= null ? accountContact.getContactId() : "").replaceAll("\"", "&quot;")
						+ "','"
						+ StringEscapeUtils.escapeJavaScript(accountContact.getLastName()!= null ? accountContact.getLastName() : "").replaceAll("\"", "&quot;")
						+ "','"
						+ StringEscapeUtils.escapeJavaScript(accountContact.getFirstName()!= null ? accountContact.getFirstName() : "").replaceAll("\"", "&quot;")
						+ "','"
						+ StringEscapeUtils.escapeJavaScript(accountContact.getWorkPhone()!= null ? accountContact.getWorkPhone() : "").replaceAll("\\n", " ")
						+ "','"
						+ StringEscapeUtils.escapeJavaScript(accountContact.getEmailAddress()!= null ? accountContact.getEmailAddress() : "").replaceAll("\"", "&quot;")
						+ "','"
						+ accountContact.getUserFavorite()
						+ "','"
						
						//Added for MPS 2.1
						+ StringEscapeUtils.escapeJavaScript(accountContact.getAddress().getAddressId())
						+ "','"
						+ StringEscapeUtils.escapeJavaScript(accountContact.getAddress().getAddressName() != null ? accountContact.getAddress()
								.getAddressName() : "")
						+ "','"
						+ StringEscapeUtils.escapeJavaScript(accountContact.getAddress().getStoreFrontName() != null ? accountContact.getAddress()
								.getStoreFrontName() : "")
						+ "','"
						//Added for MPS2.1 end
						
						+ StringEscapeUtils.escapeJavaScript(accountContact.getAddress().getAddressLine1() != null ? accountContact.getAddress().getAddressLine1() : "").replaceAll("\"", "&quot;")
						+ "','"
						+ StringEscapeUtils.escapeJavaScript(accountContact.getAddress().getAddressLine2() != null ? accountContact.getAddress().getAddressLine2() : "").replaceAll("\"", "&quot;")
						+ "','"
						+ StringEscapeUtils.escapeJavaScript(accountContact.getAddress().getCity() != null ? accountContact.getAddress().getCity() : "").replaceAll("\"", "&quot;")
						+ "','"
						+ StringEscapeUtils.escapeJavaScript(accountContact.getAddress().getState() != null ? accountContact.getAddress().getState() : "").replaceAll("\"", "&quot;")
						+ "','"
						+ StringEscapeUtils.escapeJavaScript(accountContact.getAddress().getProvince() != null ? accountContact.getAddress().getProvince() : "").replaceAll("\"", "&quot;")
						+ "','"
						+ StringEscapeUtils.escapeJavaScript(accountContact.getAddress().getCountry() != null ? accountContact.getAddress().getCountry() : "").replaceAll("\"", "&quot;")
						+ "','"
						+ StringEscapeUtils.escapeJavaScript(accountContact.getAddress().getPostalCode() != null ? accountContact.getAddress().getPostalCode() : "").replaceAll("\"", "&quot;")
						
						//Added for MPS 2.1
						+ "','"
						+ StringEscapeUtils.escapeJavaScript(accountContact.getAddress().getOfficeNumber()!= null ? accountContact.getAddress().getOfficeNumber() : "").replaceAll("\"", "&quot;")
						+ "','"
						+ StringEscapeUtils.escapeJavaScript(accountContact.getAddress().getDistrict()!= null ? accountContact.getAddress().getDistrict(): "").replaceAll("\"", "&quot;")
						+ "','"
						+ StringEscapeUtils.escapeJavaScript(accountContact.getAddress().getCounty()!= null ? accountContact.getAddress().getCounty(): "").replaceAll("\"", "&quot;")
						+"','"
						+StringEscapeUtils.escapeJavaScript(accountContact.getAddress().getCountryISOCode()!= null ? accountContact.getAddress().getCountryISOCode(): "").replaceAll("\"", "&quot;")
						+"','"
						+StringEscapeUtils.escapeJavaScript(accountContact.getAddress().getRegion()!= null ? accountContact.getAddress().getRegion(): "").replaceAll("\"", "&quot;")
						+"','"
						+StringEscapeUtils.escapeJavaScript(accountContact.getAddress().getState()!= null ? accountContact.getAddress().getState(): "").replaceAll("\"", "&quot;")
						//Added for MPS2.1 end
						
						+ "','"
						+ StringEscapeUtils.escapeJavaScript(accountContact.getAddress().getPhysicalLocation1() != null ? accountContact.getAddress().getPhysicalLocation1() : "").replaceAll("\"", "&quot;")
						+ "','"
						+ StringEscapeUtils.escapeJavaScript(accountContact.getAddress().getPhysicalLocation2() != null ? accountContact.getAddress().getPhysicalLocation2() : "").replaceAll("\"", "&quot;")
						+ "','"
						+ StringEscapeUtils.escapeJavaScript(accountContact.getAddress().getPhysicalLocation3() != null ? accountContact.getAddress().getPhysicalLocation3() : "").replaceAll("\"", "&quot;")
						+ "','"
						+ StringEscapeUtils.escapeJavaScript(accountContact.getMiddleName() != null ? accountContact.getMiddleName() : "").replaceAll("\"", "&quot;")
						+ "','"
						+ StringEscapeUtils.escapeJavaScript(accountContact.getAlternatePhone() != null ? accountContact.getAlternatePhone() : "").replaceAll("\"", "&quot;")
						+ "','"
						+StringEscapeUtils.escapeJavaScript(accountContact.getContactId())
						+ "',this"
						+ ");\" value=\""
						+ getLocalizeSelectButton() + "\"/>]]></cell>\n");						
				}
			
			/*
			 * Show the favorite or unfavorite image
			 */
			
			if (accountContact.getUserFavorite()) {
				imageSrc = contextPath + IMAGE_PATH + FAVORIATE_IMAGE_FILE_NAME;
				bookmarkClassName="bookmark-star-icon";
				bookmarkTitle = PropertiesMessageUtil.getPropertyMessage(
						LexmarkSPConstants.MESSAGE_BUNDLE_NAME,
						"requestInfo.tooltip.contact.UnBookmark", locale);
			} else {
				imageSrc = contextPath + IMAGE_PATH + UNFAVORIATE_IMAGE_FILE_NAME;	
				bookmarkClassName=" removebookmark-icon";
				bookmarkTitle = PropertiesMessageUtil.getPropertyMessage(
						LexmarkSPConstants.MESSAGE_BUNDLE_NAME,
						"requestInfo.tooltip.contact.Bookmark", locale);
			}
			//LOGGER.debug("bookmarkTitle= "+bookmarkTitle);
			//LOGGER.debug("bookmarkTitle length = "+bookmarkTitle.length());
			/*Changes for MPS 2.1*/
			xml.append("<cell><![CDATA[<img name=\"bookmark\" class=\"helpIconList ui_icon_sprite "+bookmarkClassName+"\" title=\""+ bookmarkTitle.trim() +"\" src='"+transparentImageSrc+"' id=\"starImg_primary_" +
					XMLEncodeUtil.escapeXMLForDMCM(accountContact.getContactId()) +
					"\" />]]></cell>");//This row generates image for bookmarked contact	
			/*Ends Changes for MPS 2.1*/
			/*
			 * Show the contact details in grid
			 */
			
			String alternatePhone = accountContact.getAlternatePhone() != null ? XMLEncodeUtil.escapeXMLForDMCM(accountContact.getAlternatePhone()) : "";
			
			xml.append("  <cell><![CDATA[" + XMLEncodeUtil.escapeXMLForDMCM(accountContact.getLastName())  
					+ "]]></cell>\n");
			xml.append("  <cell><![CDATA[" + XMLEncodeUtil.escapeXMLForDMCM(accountContact.getFirstName()) 
					+ "]]></cell>\n");
			xml.append("  <cell><![CDATA[" + accountContact.getWorkPhone().replaceAll("\n", "").replaceAll(" ","")  
					+ "]]></cell>\n");
			xml.append("  <cell><![CDATA[" + alternatePhone.replaceAll("\n", "") 
					+ "]]></cell>\n");
			xml.append("  <cell><![CDATA[" + accountContact.getEmailAddress().replaceAll("\n", "").replaceAll(" ", "")  
					+ "]]></cell>\n");
			
			/*
			 * Show the contact address details in main page grid
			 */
			if(!isPopUp){
			xml.append("  <cell><![CDATA[" + XMLEncodeUtil.escapeXMLForDMCM(accountContact.getAddress().getAddressLine1())
					+ "]]></cell>\n");
			xml.append("  <cell><![CDATA[" + XMLEncodeUtil.escapeXMLForDMCM(accountContact.getAddress().getAddressLine2())
					+ "]]></cell>\n");
			/*Added for MPS 2.1 Changes for Contact*/
			xml.append("  <cell><![CDATA[" + XMLEncodeUtil.escapeXMLForDMCM(accountContact.getAddress().getOfficeNumber())
					+ "]]></cell>\n");
			/*Ends for MPS 2.1 Changes for Contact*/
			/*xml.append("  <cell><![CDATA[" + accountContact.getAddress().getAddressLine3()
					+ "]]></cell>\n");*/
			xml.append("  <cell><![CDATA[" + XMLEncodeUtil.escapeXMLForDMCM(accountContact.getAddress().getCity())
					+ "]]></cell>\n");
			xml.append("  <cell><![CDATA[" + XMLEncodeUtil.escapeXMLForDMCM(accountContact.getAddress().getState())
					+ "]]></cell>\n");
			xml.append("  <cell><![CDATA[" + XMLEncodeUtil.escapeXMLForDMCM(accountContact.getAddress().getProvince())
					+ "]]></cell>\n");
			/*Added for MPS 2.1 Changes for Contact*/
			xml.append("  <cell><![CDATA[" + XMLEncodeUtil.escapeXMLForDMCM(accountContact.getAddress().getCounty())
					+ "]]></cell>\n");
			xml.append("  <cell><![CDATA[" + XMLEncodeUtil.escapeXMLForDMCM(accountContact.getAddress().getDistrict())
					+ "]]></cell>\n");
			
			/*Ends for MPS 2.1 Changes for Contact*/
			xml.append("  <cell><![CDATA[" + XMLEncodeUtil.escapeXMLForDMCM(accountContact.getAddress().getCountry())
					+ "]]></cell>\n");
			xml.append("  <cell><![CDATA[" + XMLEncodeUtil.escapeXMLForDMCM(accountContact.getAddress().getPostalCode())
					+ "]]></cell>\n");
			xml.append("  <cell><![CDATA[" + XMLEncodeUtil.escapeXMLForDMCM(accountContact.getAddress().getPhysicalLocation1())
					+ "]]></cell>\n");
			xml.append("  <cell><![CDATA[" + XMLEncodeUtil.escapeXMLForDMCM(accountContact.getAddress().getPhysicalLocation2())
					+ "]]></cell>\n");
			xml.append("  <cell><![CDATA[" + XMLEncodeUtil.escapeXMLForDMCM(accountContact.getAddress().getPhysicalLocation3())
					+ "]]></cell>\n");
			xml.append("  <cell><![CDATA[" + XMLEncodeUtil.escapeXMLForDMCM(accountContact.getMiddleName())
					+ "]]></cell>\n");
			//LOGGER.debug("accountContact.getMiddleName()="+accountContact.getMiddleName());
			}
			
			
				xml.append(" </row>\n");
				i++;
			}
			xml.append("</rows>\n");
			//LOGGER.debug("xml="+xml);
			return xml.toString();
		}
		catch (Exception ex ) {
			throw new LGSRuntimeException(ex.getMessage());
		}
	}

	/**
	 * This method is used for populating the primary address list in the grid
	 * 
	 * @param addresses
	 * @param totalCount
	 * @param posStart
	 * @param contextPath
	 * @return
	 */
	public String convertAddressListToXML(List<GenericAddress> addresses,
			int totalCount, int posStart, String contextPath, Boolean isAddrPopUp) {
		
		//logger.info("portlet name " + portletName);
		//logger.info("Pop up is indeed " + isAddrPopUp);
		
		String pencilImageSrc = contextPath + IMAGE_PATH + CHANGE_IMAGE;
		String deleteImageSrc = contextPath + IMAGE_PATH + REMOVE_IMAGE;
		String isFavoriteImageSrc;
		String transparentImageSrc=contextPath + IMAGE_PATH +"transparent.png"; 
		String bookmarkClassName;
		
		String bookmarkTitle;
		String changeTitle = PropertiesMessageUtil.getPropertyMessage(
				LexmarkSPConstants.MESSAGE_BUNDLE_NAME,
				"requestInfo.tooltip.address.edit", locale);
		String removeTitle = PropertiesMessageUtil.getPropertyMessage(
				LexmarkSPConstants.MESSAGE_BUNDLE_NAME,
				"requestInfo.tooltip.address.delete", locale);

		StringBuffer xml = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		xml.append("<rows total_count=\"" + totalCount + "\" pos=\"" + posStart
				+ "\">\n");
		int i = 0;
		for (GenericAddress genericAddress : addresses) {
		
					
			if (genericAddress.getUserFavorite()) {
						bookmarkClassName="bookmark-star-icon";
						isFavoriteImageSrc = contextPath + IMAGE_PATH + FAVORIATE_IMAGE_FILE_NAME;
						bookmarkTitle = PropertiesMessageUtil.getPropertyMessage(
								LexmarkSPConstants.MESSAGE_BUNDLE_NAME,
								"requestInfo.tooltip.address.UnBookmark", locale);
			} else {
						bookmarkClassName=" removebookmark-icon";
						isFavoriteImageSrc = contextPath + IMAGE_PATH	+ UNFAVORIATE_IMAGE_FILE_NAME;
						bookmarkTitle = PropertiesMessageUtil.getPropertyMessage(
								LexmarkSPConstants.MESSAGE_BUNDLE_NAME,
								"requestInfo.tooltip.address.Bookmark", locale);
			}		
			if (genericAddress.getAddressId()==null){
				continue;
			}
			xml.append(" <row id=\"" + genericAddress.getAddressId() + "\">\n");
			/*
			 * Changes for defect 7975 MPS 2.1
			 * Select button should be the first column
			 * */
			if(isAddrPopUp){
				/*Changes for defect 7960 MPS 2.1*/	
			xml.append("  <cell><![CDATA[<div class=\"relativeR autoWidth\"> <input name=\"btn_select\" class=\"button relativeR\"  type=\"button\" onclick=\"addServiceAddressElement('"
					/* Ends Changes for defect 7960 MPS 2.1*/
					+ StringEscapeUtils.escapeJavaScript(genericAddress.getAddressId())
					+ "','"
					+ StringEscapeUtils.escapeJavaScript(genericAddress.getAddressName() != null ? genericAddress
							.getAddressName() : "").replaceAll("\"", "&quot;")
					+ "','"
					+ StringEscapeUtils.escapeJavaScript(genericAddress.getAddressLine1() != null ? genericAddress
							.getAddressLine1() : "").replaceAll("\"", "&quot;")
					+ "','"
					+ StringEscapeUtils.escapeJavaScript(genericAddress.getAddressLine2() != null ? genericAddress
							.getAddressLine2() : "").replaceAll("\"", "&quot;")
					+ "','"
				/*	+ StringEscapeUtils.escapeJavaScript(addressLine3).replaceAll("\"", "&quot;")
					+ "','"*/
					+ StringEscapeUtils.escapeJavaScript(genericAddress.getCity() != null ? genericAddress
							.getCity() : "").replaceAll("\"", "&quot;")
					+ "','"
					+ StringEscapeUtils.escapeJavaScript(genericAddress.getState() != null ? genericAddress
							.getState() : "").replaceAll("\"", "&quot;")
					+ "','"
					+ StringEscapeUtils.escapeJavaScript(genericAddress.getProvince() != null ? genericAddress
							.getProvince() : "").replaceAll("\"", "&quot;")
					+ "','"
					+ StringEscapeUtils.escapeJavaScript(genericAddress.getCountry() != null ? genericAddress
							.getCountry() : "").replaceAll("\"", "&quot;")
					+ "','"
					+ StringEscapeUtils.escapeJavaScript(genericAddress.getPostalCode() != null ? genericAddress
							.getPostalCode() : "").replaceAll("\"", "&quot;")
					+ "','"
					+ StringEscapeUtils.escapeJavaScript(genericAddress.getStoreFrontName() != null ? genericAddress
							.getStoreFrontName() : "").replaceAll("\"", "&quot;")
					+ "','"
					+ StringEscapeUtils.escapeJavaScript(genericAddress.getPhysicalLocation1() != null ? genericAddress
							.getPhysicalLocation1() : "").replaceAll("\"", "&quot;")
					+ "','"
					+ StringEscapeUtils.escapeJavaScript(genericAddress.getPhysicalLocation2() != null ? genericAddress
							.getPhysicalLocation2() : "").replaceAll("\"", "&quot;")
					+ "','"
					+ StringEscapeUtils.escapeJavaScript(genericAddress.getPhysicalLocation3() != null ? genericAddress
							.getPhysicalLocation3() : "").replaceAll("\"", "&quot;")
					+ "','"
					+ genericAddress.getUserFavorite()
					//Added for MPS 2.1
					+ "','"
					+ StringEscapeUtils.escapeJavaScript(genericAddress.getOfficeNumber()!= null ? genericAddress.getOfficeNumber() : "").replaceAll("\"", "&quot;")
					+ "','"
					+ StringEscapeUtils.escapeJavaScript(genericAddress.getDistrict()!= null ? genericAddress.getDistrict(): "").replaceAll("\"", "&quot;")
					+ "','"
					+ StringEscapeUtils.escapeJavaScript(genericAddress.getCounty()!= null ? genericAddress.getCounty(): "").replaceAll("\"", "&quot;")
					+"','"
					+StringEscapeUtils.escapeJavaScript(genericAddress.getCountryISOCode()!= null ? genericAddress.getCountryISOCode(): "").replaceAll("\"", "&quot;")
					+"','"
					+StringEscapeUtils.escapeJavaScript(genericAddress.getRegion()!= null ? genericAddress.getRegion(): "").replaceAll("\"", "&quot;")
					+"','"
					+StringEscapeUtils.escapeJavaScript(genericAddress.getState()!= null ? genericAddress.getState(): "").replaceAll("\"", "&quot;")
					+"','"
					+genericAddress.getLbsAddressFlag()
					+"','"
					+StringEscapeUtils.escapeJavaScript(genericAddress.getLevelOfDetails()!= null ? genericAddress.getLevelOfDetails(): "").replaceAll("\"", "&quot;")
					//+StringEscapeUtils.escapeJavaScript(genericAddress.getLbsAddressFlag()!= null ? genericAddress.getLbsAddressFlag(): "").replaceAll("\"", "&quot;")
					
					//Ends
					+ "');\" value=\""
					+ getLocalizeSelectButton() + "\"/></div>]]></cell>\n");
			}
			/*Ends changes Select Button*/
			
			
			if(!isAddrPopUp){
				xml.append("  <cell><![CDATA[" +
						"<img class=\"helpIconList ui_icon_sprite edit-icon\" title='"+changeTitle+"' src=\"" + transparentImageSrc + "\"" +
						"onclick=\"editAddress('"+genericAddress.getAddressId()+"')\" style=\"cursor:pointer\"/>"+
						"<img class=\"helpIconList ui_icon_sprite trash-icon\" title='"+removeTitle+"' src=\""+transparentImageSrc+"\" style=\"cursor:pointer\" onclick=\"deleteAddress('"+genericAddress.getAddressId()+"')\"/>]]></cell>\n");
			}
			xml.append("  <cell><![CDATA[<img class=\"helpIconList ui_icon_sprite "+bookmarkClassName+"\" title=\""+bookmarkTitle+"\" src=\""+transparentImageSrc +"\" id=\"starImg_address_"+genericAddress.getAddressId()+"\" onclick=\"setServiceAddressFavourite('"+genericAddress.getAddressId()+"')\" style=\"cursor:pointer;\" />]]></cell>\n");
			if(!isAddrPopUp){
				xml.append("  <cell><![CDATA[" + genericAddress.getAddressId() + "]]></cell>\n");
			}
			xml.append("  <cell><![CDATA[" + (genericAddress.getAddressName() != null ? genericAddress
					.getAddressName() : "")+ "]]></cell>\n");
			xml.append("  <cell><![CDATA[" + (genericAddress.getStoreFrontName() != null ? genericAddress
					.getStoreFrontName() : "")+ "]]></cell>\n");
			xml.append("  <cell><![CDATA[" + (genericAddress.getAddressLine1() != null ? genericAddress
					.getAddressLine1() : "")+ "]]></cell>\n");
			xml.append("  <cell><![CDATA[" + (genericAddress.getAddressLine2() != null ? genericAddress
					.getAddressLine2() : "")+ "]]></cell>\n");
			/*xml.append("  <cell><![CDATA[" + addressLine3+ "]]></cell>\n");*/
			
			/*New Fields Added for Phase 2*/
		
			//Office number	Defect 12995
			xml.append("  <cell><![CDATA[" + (genericAddress.getOfficeNumber() != null ? genericAddress
					.getOfficeNumber() : "") + "]]></cell>\n");
			
			/*Phase 2 Ends*/	
			
			xml.append("  <cell><![CDATA[" + (genericAddress.getCity() != null ? genericAddress
					.getCity() : "")+ "]]></cell>\n");
			xml.append("  <cell><![CDATA[" + (genericAddress.getState() != null ? genericAddress
					.getState() : "")+ "]]></cell>\n");
			xml.append("  <cell><![CDATA[" + (genericAddress.getProvince() != null ? genericAddress
					.getProvince() : "")+ "]]></cell>\n");
			
			/*New Fields Added for Phase 2*/
			
				
					xml.append("  <cell><![CDATA[" + (genericAddress.getCounty() != null ? genericAddress
							.getCounty() : "") + "]]></cell>\n");
					//Changes MPS2.1
					xml.append("  <cell><![CDATA[" + (genericAddress.getDistrict() != null ? genericAddress
							.getDistrict() : "")+ "]]></cell>\n");
					//Ends
					xml.append("  <cell><![CDATA[" + (genericAddress.getRegion() != null ? genericAddress
							.getRegion() : "")+ "]]></cell>\n");
			
				
				
			xml.append("  <cell><![CDATA[" + (genericAddress.getPostalCode() != null ? genericAddress
							.getPostalCode() : "")+ "]]></cell>\n");
			xml.append("  <cell><![CDATA[" + (genericAddress.getCountry() != null ? genericAddress
							.getCountry() : "")+ "]]></cell>\n");
			
			
			xml.append("  <cell><![CDATA[" + (genericAddress.getLbsAddressFlag()!= null && genericAddress.getLbsAddressFlag()==true ? "Yes" : "No")+ "]]></cell>\n");
	        xml.append("  <cell><![CDATA[" + (genericAddress.getLevelOfDetails()!= null ? genericAddress
					.getLevelOfDetails() : "")+ "]]></cell>\n");
			
			/*Phase 2 Ends*/	
			
			
			xml.append(" </row>\n");
			i++;
		}
		xml.append("</rows>\n");
		return xml.toString();
	}
	/*This is used to convert country list to html
	 * */
	public String convertCountriesToHTML(List<GeographyCountryContract> countries){
		StringBuffer html = new StringBuffer("<option></option>\n");
		for(GeographyCountryContract gcc:countries){
			html.append("<option value=\""+gcc.getCountryCode()+"\">"+StringEscapeUtils.escapeHtml(gcc.getCountryName())+"</option>\n");
		}
		return html.toString();
	}
	/*This is used to convert state list to html
	 * */
	public String convertStateToHTML(List<GeographyStateContract> states){
		//StringBuffer html = new StringBuffer("<option>          </option>\n");
		StringBuffer html = new StringBuffer("<option></option>");
		for(GeographyStateContract gsc:states){
			html.append("<option value=\""+gsc.getStateCode()+"\">"+StringEscapeUtils.escapeHtml(gsc.getStateName())+"</option>\n");
		}
		return html.toString();
	}
	
	/*This is used to convert account List to xml
	 * */
//For MPS Phase 2.1 
	public String convertAccountListToXML(List<Account> accountList,int size,int posStart, String isCatalogPage, String isHardwarePage,boolean isVendorFlag){
		StringBuffer xml = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
		xml.append("<rows total_count=\"" + size + "\" pos=\"" + posStart
				+ "\">\n");
		int i=0;
		LOGGER.debug("IN XML -> isCatalogPage="+isCatalogPage+" isVendorFlag="+isVendorFlag);
		for (Account account: accountList) {
			
			String accountId = account.getAccountId() != null ? XMLEncodeUtil.escapeXMLForDMCM(account.getAccountId()) : "";
			String accountName = account.getAccountName() != null ? XMLEncodeUtil.escapeXMLForDMCM(account.getAccountName()) : "";
			// Below field will be changed as Account Organization is not there in Account Bean
			/*String accountOrganization=account.getAccountOrganization() != null ? account.getAccountType() : "";*/
			String country="";
			if(account.getAddress()!=null){
				 country= account.getAddress().getCountry() != null ? XMLEncodeUtil.escapeXMLForDMCM(account.getAddress().getCountry()) : "";
			}
			String agreementId=account.getAgreementId() != null ? XMLEncodeUtil.escapeXMLForDMCM(account.getAgreementId()):"";
			String agreementName=account.getAgreementName() != null ? XMLEncodeUtil.escapeXMLForDMCM(account.getAgreementName()):"";			
			String vendorAccountId = account.getVendorAccountId() != null ?account.getVendorAccountId():"";
			
			
			/*Added for MPS 2.1 Wave 1 Consumables changes*/
			String showPrice =  account.getShowPriceFlag() != null ?XMLEncodeUtil.escapeXMLForDMCM(account.getShowPriceFlag()):"";;
			boolean poFlag = account.isPoNumberFlag();
			boolean creditCardFlag = account.isCreditNumberFlag();
			String contractNumber = account.getContractNumber() != null ?XMLEncodeUtil.escapeXMLForDMCM(account.getContractNumber()):"";
			String contractLine = account.getContractLine() != null ?XMLEncodeUtil.escapeXMLForDMCM(StringEscapeUtils.escapeJavaScript(account.getContractLine())):"";
			String salesOrg = account.getOrganizationName() != null ? XMLEncodeUtil.escapeXMLForDMCM(account.getOrganizationName()):"";
			LOGGER.debug("salesOrg "+ salesOrg);
			boolean accountSplitterFlag = account.isAccountSplitterFlag();
			String soldToList = "";
			if(account.getSoldToNumbers()!=null){
				List<String> soldTo = account.getSoldToNumbers();
				StringBuilder idArrayBuilder = new StringBuilder();
				for( String id : soldTo ) {
				  if( idArrayBuilder.length() > 0 ) {
				    idArrayBuilder.append(",");
				  }
	
				  idArrayBuilder.append(id);
				}
				soldToList = idArrayBuilder.toString();
				if(soldToList.endsWith(",")){
					soldToList = soldToList.substring(0, soldToList.length()-1);
				}
			}else{
				soldToList = "";
			}
			/*End Add*/
			boolean isCreateClaimFlag = account.isCreateClaimFlag();
			boolean isViewOrderFlag = account.isViewOrderFlag();
			
			LOGGER.debug("In Account XML --> accountId="+accountId+" accountName="+accountName+" country="+country+" agreementId="+agreementId+
					" agreementName= "+agreementName+" vendorAccountId="+vendorAccountId+" isCreateClaimFlag="+isCreateClaimFlag+
					" isViewOrderFlag="+isViewOrderFlag+" soldToList="+soldToList);
			if (accountId == ""){
				continue;
			}
			xml.append(" <row id=\"" + (accountId)+i + "\">\n");
			
			xml.append("  <cell><![CDATA[" + account.isCatalogEntitlementFlag() + "]]></cell>\n");
			
			xml.append("  <cell><![CDATA[" + accountId+ "]]></cell>\n");
			xml.append("  <cell><![CDATA[" + country+ "]]></cell>\n");
			xml.append("  <cell><![CDATA[" + accountName+ "]]></cell>\n");
			xml.append("  <cell><![CDATA[" + agreementId + "]]></cell>\n");
			xml.append("  <cell><![CDATA[" + agreementName + "]]></cell>\n");
			xml.append("  <cell><![CDATA[" + contractLine + "]]></cell>\n");
			
			LOGGER.debug("In XML -> account.isCatalogExpediteFlag()= "+account.isCatalogExpediteFlag());
			
			if(isCatalogPage !=null && isCatalogPage.equalsIgnoreCase("true") 
					&& (agreementId.trim().isEmpty()||agreementId.trim().equalsIgnoreCase(LexmarkConstants.NO_MATCH_ROW_ID))){
				xml.append("  <cell><![CDATA[ <input name=\"btn_select\" class=\"button button_disabled\"  type=\"button\"  value=\""
						+ getLocalizeSelectButton() + "\"/>]]></cell>\n");
			}else{
				if(isVendorFlag)
				{					
					xml.append("  <cell><![CDATA[ <input name=\"btn_select\" class=\"button\"  type=\"button\" id=\"button"+accountId+i+"\" onclick=\"setAccount('"					
							+accountId+ "','"+vendorAccountId+ "','"+isCreateClaimFlag+ "','"+isViewOrderFlag+ "','"+(accountId+i)+"','"+account.isCatalogExpediteFlag()+"','"+soldToList+"','"+showPrice+"','"+poFlag+"','"+creditCardFlag+"','"+accountSplitterFlag+"','"+salesOrg+"','"+contractNumber+"','"+contractLine+"');\" value=\""
							+ getLocalizeSelectButton() + "\"/>]]></cell>\n");
				}else{
					
					xml.append("  <cell><![CDATA[ <input name=\"btn_select\" class=\"button\"  type=\"button\" id=\"button"+accountId+i+"\" onclick=\"setAccount('"
							+accountId+ "','"+vendorAccountId+ "','"+isCreateClaimFlag+ "','"+isViewOrderFlag+ "','"+(accountId+i)+"','"+ account.isCatalogExpediteFlag()+"','"+soldToList+"','"+showPrice+"','"+poFlag+"','"+creditCardFlag+"','"+accountSplitterFlag+"','"+salesOrg+"','"+contractNumber+"','"+contractLine+"');\" value=\""
							+ getLocalizeSelectButton() + "\"/>]]></cell>\n");
				}
			}
			
			
			
			xml.append(" </row>\n");
			i++;
			
		}
		xml.append("</rows>\n");
		return xml.toString();	
	}

	private String getLocalizeSelectButton() {
		return (PropertiesMessageUtil
				.getPropertyMessage(LexmarkSPConstants.MESSAGE_BUNDLE_NAME,
						"button.select", locale));
	}
	public String convertRequestHistoryToXML(List<ServiceRequest> serviceRequestList, int totalCount, int startRecordNumber, boolean containsSubRow, float timeZoneOffset) {
		LOGGER.debug("-----------Step 4--convertServiceRequestsToXML started---------[" + System.nanoTime() + "]");
		StringBuffer xml = new StringBuffer("<?xml version=\"1.0\" ?>\n");
		xml.append("<rows total_count=\"" + totalCount + "\" pos=\"" + startRecordNumber
				+ "\">\n");
		
		
		for (ServiceRequest sericeRequest:serviceRequestList) {
			
			String srNumber = sericeRequest.getServiceRequestNumber()==null?"":sericeRequest.getServiceRequestNumber();
			String srType = sericeRequest.getServiceRequestType()==null?"":sericeRequest.getServiceRequestType().getValue();
			LOGGER.debug("srType before trim" + srType);
			
		    xml = xml.append(" <row id=\"" + (srNumber) + "\">\n");
		    xml.append("<cell><![CDATA[<a class=\"lightboxDetails\" title=\"Details\" onclick=\"srDetailsPopup('" +
		    		""+srNumber+"','"+srType.replaceAll("\\s", "_")+"')\"> " +(srNumber) + " </a>]]></cell>\n");
		    
		    if ( sericeRequest.getServiceRequestDate() != null){	
				TimezoneUtil.adjustDate(sericeRequest.getServiceRequestDate(),(timeZoneOffset));
				String dt =  DateLocalizationUtil.localizeDateTime(sericeRequest.getServiceRequestStatusDate(), true, locale);
				xml.append("  <cell><![CDATA["+ dt  +"]]></cell>\n");
				
			}else{
				xml.append("  <cell><![CDATA[ -- -- -- ]]></cell>\n");
			}
		    
		    
		    
		    xml.append("<cell><![CDATA[" + localizeServiceRequestType(srType,locale) + "]]></cell>\n");
		    xml.append("<cell><![CDATA[" + (sericeRequest.getArea()==null?"": sericeRequest.getArea().getValue())+ "]]></cell>\n");
		    xml.append("<cell><![CDATA[" + (sericeRequest.getServiceRequestStatus()==null?"":sericeRequest.getServiceRequestStatus()) + "]]></cell>\n");
		    xml.append("</row>\n");
		}
		xml = xml.append("</rows>");
		LOGGER.debug("-----------Step 4--convertServiceRequestsToXML End---------[" + System.nanoTime() + "]");
		return xml.toString();
	}
	
	public String convertAssociatedRequestToXML(List<ServiceRequest> serviceRequestList, int totalCount, int startRecordNumber, boolean containsSubRow, float timeZoneOffset) {
		LOGGER.debug("-----------Step 4--convertServiceRequestsToXML started---------[" + System.nanoTime() + "]");
		StringBuffer xml = new StringBuffer("<?xml version=\"1.0\" ?>\n");
		xml.append("<rows total_count=\"" + totalCount + "\" pos=\"" + startRecordNumber
				+ "\">\n");
		
		
		for (ServiceRequest sericeRequest:serviceRequestList) {
			String srNumber = sericeRequest.getServiceRequestNumber()==null?"":sericeRequest.getServiceRequestNumber();
			String srType = sericeRequest.getServiceRequestType()==null?"":sericeRequest.getServiceRequestType().getValue();
			String area = sericeRequest.getArea()==null?"":sericeRequest.getArea().getValue();
			String subArea = sericeRequest.getSubArea()==null?"":sericeRequest.getSubArea().getValue();
			
			LOGGER.debug("srType before trim" + srType);
			
		    xml = xml.append(" <row id=\"" + (srNumber) + "\">\n");
		    xml.append("<cell><![CDATA[<a class=\"lightboxDetails\" title=\"Details\" onclick=\"srDetailsPopup('" +
		    		""+srNumber+"','"+srType.replaceAll("\\s", "_")+"','"+area+"','"+subArea+"')\"> " +(srNumber) + " </a>]]></cell>\n");
		    
		    if ( sericeRequest.getServiceRequestDate() != null){	
				TimezoneUtil.adjustDate(sericeRequest.getServiceRequestDate(),(timeZoneOffset));
				String dt =  DateLocalizationUtil.localizeDateTime(sericeRequest.getServiceRequestDate(), true, locale);
				xml.append("  <cell><![CDATA["+ dt  +"]]></cell>\n");
				
			}else{
				xml.append("  <cell><![CDATA[ -- -- -- ]]></cell>\n");
			}
		    
		    
		    
		    xml.append("<cell><![CDATA[" + localizeServiceRequestType(srType,locale) + "]]></cell>\n");
		    xml.append("<cell><![CDATA[" + (sericeRequest.getArea()==null?"": sericeRequest.getArea().getName())+ "]]></cell>\n");
		    xml.append("<cell><![CDATA[" + (sericeRequest.getStatusType()==null?"":sericeRequest.getStatusType()) + "]]></cell>\n");
		    xml.append("</row>\n");
		}
		xml = xml.append("</rows>");
		LOGGER.debug("-----------Step 4--convertServiceRequestsToXML End---------[" + System.nanoTime() + "]");
		return xml.toString();
	}
	
	
	/**.
	 * This method builds the grid xml for all request history for Device Mgmt;
	 * @return
	 * @author Sourav
	 */
	public String generateXMLForAllRequestDM(List<ServiceRequest> serviceRequestList, int totalCount, int startRecordNumber, float timezoneOffset){
		StringBuffer xml = new StringBuffer("<?xml version=\"1.0\" ?>\n");
		xml = xml.append("<rows total_count=\"" + totalCount + "\" pos=\"" + startRecordNumber + "\">\n");
		for (int i = 0; i < serviceRequestList.size(); i++) {
			try{
		    ServiceRequest serviceRequest = serviceRequestList.get(i);
		    xml = xml.append(" <row id=\"" + (startRecordNumber + i) + "\">\n");
		    
		    //Changed
		    ListOfValues reqTypeLOV = serviceRequest.getServiceRequestType();
		    
		    xml.append("<cell><![CDATA[<a href=\"###\" onClick=\"hideSelect();onSRNmbrClick('" + serviceRequest.getServiceRequestNumber() + "','" + reqTypeLOV.getValue() +"')\">" + 
					   serviceRequest.getServiceRequestNumber() + "</a>]]></cell>\n");
			if ( serviceRequest.getServiceRequestDate() != null){	
				
				TimezoneUtil.adjustDate(serviceRequest.getServiceRequestDate(),(timezoneOffset));
				String dt =  DateLocalizationUtil.localizeDateTime(serviceRequest.getServiceRequestDate(), true, locale);
				xml.append("  <cell><![CDATA["+ dt  +"]]></cell>\n");
				
			}else{
				xml.append("  <cell><![CDATA[ -- -- -- ]]></cell>\n");
			}
			ListOfValues areaLOV = serviceRequest.getArea();
			xml.append("  <cell><![CDATA["+ reqTypeLOV.getValue() +"]]></cell>\n");
			
			xml.append("  <cell><![CDATA["+areaLOV.getValue() +"]]></cell>\n");
			
			
								
			//xml.append("  <cell><![CDATA["+ areaLOV.getValue() +"]]></cell>\n");
			
			xml.append("  <cell><![CDATA["+ serviceRequest.getStatusType().getValue() +"]]></cell>\n");
			
			xml.append("  <cell><![CDATA["+ serviceRequest.getAsset().getSerialNumber() +"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getCostCenter() )+"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getAccountName() )+"]]></cell>\n");//For MPS Phase 2.1
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getPrimaryContact().getFirstName() )+"]]></cell>\n");			
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getPrimaryContact().getLastName() )+"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getPrimaryContact().getEmailAddress()) +"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getPrimaryContact().getWorkPhone()) +"]]></cell>\n");			
			
			xml.append("  <cell><![CDATA["+replaceNullWithBlankString(serviceRequest.getRequestor().getFirstName() )+"]]></cell>\n");				
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getRequestor().getLastName() )+"]]></cell>\n");
			xml.append("  <cell><![CDATA["+replaceNullWithBlankString(serviceRequest.getRequestor().getEmailAddress() )+"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getRequestor().getWorkPhone() )+"]]></cell>\n");
			//xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getCoveredService() )+"]]></cell>\n");
			 
			
			xml = xml.append(" </row>\n");
			}catch(Exception ex)
			{
				LOGGER.debug("Exception "+ex.getMessage());
			}
			    
		}
		
		
		xml = xml.append("</rows>\n");
		LOGGER.debug("XmlOutputGeneratorUtil.generateXMLForAllRequestDM.EXIT----------------"+xml);
		
		return xml.toString();
	}
	
	/**.
	 * This method builds the grid xml for Change request history for Device Mgmt;
	 * @return
	 */
	public String generateXMLForChangeRequestDM(List<ServiceRequest> serviceRequestList, int totalCount, int startRecordNumber, float timezoneOffset){
		StringBuffer xml = new StringBuffer("<?xml version=\"1.0\" ?>\n");
		xml = xml.append("<rows total_count=\"" + totalCount + "\" pos=\"" + startRecordNumber + "\">\n");
		for (int i = 0; i < serviceRequestList.size(); i++) {
		    ServiceRequest serviceRequest = serviceRequestList.get(i);
		    xml = xml.append(" <row id=\"" + (startRecordNumber + i) + "\">\n");
		    
		  //Changed
		    xml.append("<cell><![CDATA[<a href=\"###\" onClick=\"hideSelect();onSRNmbrClick('" + serviceRequest.getServiceRequestNumber() + "','Fleet Management')\">" + 
					   serviceRequest.getServiceRequestNumber() + "</a>]]></cell>\n");
			if ( serviceRequest.getServiceRequestDate() != null){					   
				
				TimezoneUtil.adjustDate(serviceRequest.getServiceRequestDate(),(timezoneOffset));
				String dt =  DateLocalizationUtil.localizeDateTime(serviceRequest.getServiceRequestDate(), true, locale);
				xml.append("  <cell><![CDATA["+ dt  +"]]></cell>\n");
			}else{
				xml.append("  <cell><![CDATA[ -- -- -- ]]></cell>\n");
			}
			
			
			ListOfValues areaLOV = serviceRequest.getArea();
			xml.append("  <cell><![CDATA["+ areaLOV.getValue() +"]]></cell>\n");
			
			ListOfValues subAreaLOV = serviceRequest.getSubArea();
			xml.append("  <cell><![CDATA["+ subAreaLOV.getValue() +"]]></cell>\n");
		
			xml.append("  <cell><![CDATA["+ serviceRequest.getAsset().getSerialNumber() +"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getAsset().getDeviceTag() )+"]]></cell>\n");//For MPS Phase 2.1
			xml.append("  <cell><![CDATA["+ serviceRequest.getStatusType().getValue() +"]]></cell>\n");
			
			xml.append("  <cell><![CDATA["+ serviceRequest.getAsset().getModelNumber() +"]]></cell>\n");
			
			xml.append("  <cell><![CDATA["+ serviceRequest.getReferenceNumber() +"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getCostCenter() )+"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getAccountName() )+"]]></cell>\n");//For MPS Phase 2.1
			xml.append("  <cell><![CDATA["+ serviceRequest.getServiceAddress().getAddressName() +"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ serviceRequest.getServiceAddress().getStoreFrontName() +"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ serviceRequest.getServiceAddress().getAddressLine1() +"]]></cell>\n");	
			// *office number , county, district added for MPS Phase 2
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getServiceAddress().getOfficeNumber()) +"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ serviceRequest.getServiceAddress().getCity() +"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ serviceRequest.getServiceAddress().getState() +"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getServiceAddress().getProvince()) +"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getServiceAddress().getCounty()) +"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getServiceAddress().getDistrict()) +"]]></cell>\n");
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
		return xml.toString();
	}
	
	
	
	
	/**.
	 * This method builds the grid xml for Supply request history for Device Mgmt;
	 * @return
	 * @author Sourav
	 */
	public String generateXMLForSupplyRequestDM(List<ServiceRequest> serviceRequestList, int totalCount, int startRecordNumber, float timezoneOffset){
		LOGGER.debug("XmlOutputGeneratorUtil.generateXMLForSupplyRequestDM.ENTER---------------");
		StringBuffer xml = new StringBuffer("<?xml version=\"1.0\" ?>\n");
		xml = xml.append("<rows total_count=\"" + totalCount + "\" pos=\"" + startRecordNumber + "\">\n");
		for (int i = 0; i < serviceRequestList.size(); i++) {
		    ServiceRequest serviceRequest = serviceRequestList.get(i);
		    xml = xml.append(" <row id=\"" + (startRecordNumber + i) + "\">\n");
		    LOGGER.debug("startRecordNumber --->"+startRecordNumber);
		    if(serviceRequest.getExpediteOrder() != null && serviceRequest.getExpediteOrder().booleanValue()){
		    	 xml.append("  <cell><![CDATA[ "+  " <img class='ui_icon_sprite upload-icon' src=\"/LexmarkOPSPortal/images/transparent.png\" alt=\"Expedited Request\" /> " +"]]></cell>\n");//Put image for expedite order
		    }else{
		    	 xml.append("  <cell><![CDATA[ ]]></cell>\n");
		    }
		   
			/*********************************/
		    
		   
		    
		     xml.append("<cell><![CDATA[<div class=\"dhx_sub_row\"><table class=\"displayGrid\">" 
		    + "<thead><tr>" 
			+ "<th class=\"w100\">Part No.</th>"	
			+ "<th>Description</th>"	
			+ "<th class=\"w150\">Part Type</th>"	
			+ "<th class=\"w80 right\">Quantity</th>"				
			+"</tr></thead><tbody>") ;
			
			List<Part> partList=  serviceRequest.getParts();
			for(Part part:partList){
				 xml.append("<tr>" 
							+ "<td>"+part.getPartNumber()+"</td>"	
							+ "<td>"+part.getDescription()+"</td>"	
							+ "<td>"+part.getPartType()+"</td>"	
							+ "<td class=\"right\">"+part.getOrderQuantity()+"</td>"				
							+"</tr>") ;
			}
			
			xml.append("</table>]]></cell>\n");

		     /********************************/
 		    
			
			//Changed
		    xml.append("<cell><![CDATA[<a href=\"###\" onClick=\"hideSelect();onSRNmbrClick('" + serviceRequest.getServiceRequestNumber() + "','Consumables Management')\">" + 
					   serviceRequest.getServiceRequestNumber() + "</a>]]></cell>\n");
			if ( serviceRequest.getServiceRequestDate() != null){					   
				LOGGER.debug("ServiceRequestDate Before adjust--->"+DateLocalizationUtil.localizeDateTime(serviceRequest.getServiceRequestDate(), true, locale));
				
				TimezoneUtil.adjustDate(serviceRequest.getServiceRequestDate(),(timezoneOffset));
				String dt =  DateLocalizationUtil.localizeDateTime(serviceRequest.getServiceRequestDate(), true, locale);
				
				LOGGER.debug("ServiceRequestDate After adjust--->"+dt);
				xml.append("  <cell><![CDATA["+ dt  +"]]></cell>\n");
			}else{
				xml.append("  <cell><![CDATA[ -- -- -- ]]></cell>\n");
			}
			
			
			ListOfValues areaLOV = serviceRequest.getArea();			
								
			xml.append("  <cell><![CDATA["+ areaLOV.getValue() +"]]></cell>\n");
			
			ListOfValues subAreaLOV = serviceRequest.getSubArea();
								
			xml.append("  <cell><![CDATA["+ subAreaLOV.getValue() +"]]></cell>\n");
		
			xml.append("  <cell><![CDATA["+ serviceRequest.getAsset().getSerialNumber() +"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ serviceRequest.getAsset().getDeviceTag() +"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ serviceRequest.getStatusType().getValue() +"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ serviceRequest.getAsset().getModelNumber() +"]]></cell>\n");
			xml.append("  <cell><![CDATA["+replaceNullWithBlankString(serviceRequest.getPoNumber() )+"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getCostCenter() )+"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getAccountName() )+"]]></cell>\n");	
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getServiceAddress().getAddressName()) +"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ serviceRequest.getServiceAddress().getStoreFrontName() +"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ serviceRequest.getServiceAddress().getAddressLine1() +"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getServiceAddress().getOfficeNumber()) +"]]></cell>\n");
			// *office number , county, district added for MPS Phase 2
			xml.append("  <cell><![CDATA["+ serviceRequest.getServiceAddress().getCity() +"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ serviceRequest.getServiceAddress().getState() +"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getServiceAddress().getProvince()) +"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getServiceAddress().getCounty()) +"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getServiceAddress().getDistrict()) +"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ serviceRequest.getServiceAddress().getCountry() +"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ serviceRequest.getServiceAddress().getPostalCode() +"]]></cell>\n");
			
			
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getPrimaryContact().getFirstName() )+"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getPrimaryContact().getLastName() )+"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getPrimaryContact().getEmailAddress()) +"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getPrimaryContact().getWorkPhone()) +"]]></cell>\n");
			
			
			xml.append("  <cell><![CDATA["+replaceNullWithBlankString(serviceRequest.getRequestor().getFirstName() )+"]]></cell>\n");				
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getRequestor().getLastName() )+"]]></cell>\n");
			xml.append("  <cell><![CDATA["+replaceNullWithBlankString(serviceRequest.getRequestor().getEmailAddress() )+"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ replaceNullWithBlankString(serviceRequest.getRequestor().getWorkPhone() )+"]]></cell>\n");
			xml = xml.append(" </row>\n");
			    
		}
		
		
		xml = xml.append("</rows>\n");
		return xml.toString();
	}
	
	/**.
	 * This method returns blank string if the parameter passed is null;
	 * @param value
	 * @return String
	 * @author Sourav
	 */
	private Object replaceNullWithBlankString( Object value){
		if(value == null){
			return "";
		}
		return value;
	}
	
	/**.
	 * This method retrieves the localized SR type from portal DB
	 * @param value
	 * @param columnPattern
	 * @param locale
	 * @return
	 */
	private String localizeServiceRequestType(String siebelValue, Locale locale){
		LocalizedSiebelValueContract contract = ContractFactory.createLocalizedSiebelValueContract(ChangeMgmtConstant.LOV_SR_TYPE,siebelValue,locale);
		
		LocalizedSiebelValueResult result = new LocalizedSiebelValueResult();
		ServiceRequestLocale srlocale = getServiceRequestLocale();
		try {
			result = srlocale.retrieveLocalizedSiebelValue(contract);
		} catch (InfrastructureException ex) {
			
			throw new LGSRuntimeException("Exception while retrieving localized SR_TYPE LOV list from DB",ex);
		}
		ListOfValues localLovValue = result.getLovValue();
		
		if(localLovValue == null || localLovValue.getValue()== null || localLovValue.getValue().isEmpty()){
			return siebelValue;
		}
		return localLovValue.getName();
	}
	
	/**.
	 * This method builds the grid xml for all request history for Device Mgmt;
	 * @return
	 * Done for CI BRD13-10-02
	 */
	public String generateXMLForAllRequestOnAnAsset(List<ServiceRequest> serviceRequestList, int totalCount, int startRecordNumber, float timezoneOffset){
		logger.enter(this.getClass().getSimpleName(), "generateXMLForAllRequestOnAnAsset");
		StringBuffer xml = new StringBuffer("<?xml version=\"1.0\" ?>\n");
		xml = xml.append("<rows total_count=\"" + totalCount + "\" pos=\"" + startRecordNumber + "\">\n");
		for (int i = 0; i < serviceRequestList.size(); i++) {
			try{
		    ServiceRequest serviceRequest = serviceRequestList.get(i);
		    xml = xml.append(" <row id=\"" + (startRecordNumber + i) + "\">\n");
		    
		    ListOfValues reqTypeLOV = serviceRequest.getServiceRequestType();
		    
		    LOGGER.debug("SR Type of CM Details::"+ reqTypeLOV.getValue());
		    
		    xml.append("<cell><![CDATA[<a href=\"###\"  onClick=\"hideSelect();onSRNmbrClick('" + serviceRequest.getServiceRequestNumber() + "','" + reqTypeLOV.getValue() +"')\">" + 
					   serviceRequest.getServiceRequestNumber() + "</a>]]></cell>\n");
			if ( serviceRequest.getServiceRequestDate() != null){	
				LOGGER.debug("ServiceRequestDate Before adjust--->"+DateLocalizationUtil.localizeDateTime(serviceRequest.getServiceRequestDate(), true, locale));
				
				TimezoneUtil.adjustDate(serviceRequest.getServiceRequestDate(),(timezoneOffset));
				String dt =  DateLocalizationUtil.localizeDateTime(serviceRequest.getServiceRequestDate(), true, locale);
				xml.append("  <cell><![CDATA["+ dt  +"]]></cell>\n");
				
			}else{
				xml.append("  <cell><![CDATA[ -- -- -- ]]></cell>\n");
			}
			
			
			xml.append("  <cell><![CDATA["+ localizeServiceRequestType(reqTypeLOV.getValue(),locale) +"]]></cell>\n");
			ListOfValues areaLOV = serviceRequest.getArea();
			xml.append("  <cell><![CDATA["+ areaLOV.getName() +"]]></cell>\n"); //area	
			xml.append("  <cell><![CDATA["+ serviceRequest.getServiceRequestStatus() +"]]></cell>\n");
			xml = xml.append(" </row>\n");
			}catch(Exception ex)
			{
				LOGGER.debug("Exception "+ex.getMessage());
			}
			    
		}
		
		
		xml = xml.append("</rows>\n");
		logger.exit(this.getClass().getSimpleName(), "generateXMLForAllRequestOnAnAsset");
		
		return xml.toString();
	}
}