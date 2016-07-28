
package com.lexmark.util;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;

import javax.portlet.ResourceRequest;

import org.apache.axis.utils.StringUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.constants.LexmarkPPConstants;
import com.lexmark.contract.GeographyCountryContract;
import com.lexmark.contract.GeographyStateContract;
import com.lexmark.domain.AccountPayable;
import com.lexmark.domain.AccountReceivable;
import com.lexmark.domain.Activity;
import com.lexmark.domain.ActivityNote;
import com.lexmark.domain.AdditionalPaymentRequest;
import com.lexmark.domain.AttachmentFile;
import com.lexmark.domain.BulkUploadStatus;
import com.lexmark.domain.GenericAddress;
import com.lexmark.domain.Invoice;
import com.lexmark.domain.Order;
import com.lexmark.domain.Part;
import com.lexmark.domain.PartLineItem;
import com.lexmark.domain.PartnerAgreement;
import com.lexmark.domain.ServiceRequestAP;
import com.lexmark.domain.ServiceRequestActivity;
import com.lexmark.domain.ServiceRequestOrderLineItem;
import com.lexmark.domain.TechnicianInstruction;
import com.lexmark.enums.LineStatusEnum;
import com.lexmark.service.impl.real.domain.SupplyRequestRecommendedPartsDo;
import com.lexmark.util.TreeXmlBuilder.Item;

/**
 * @author wipro
 * @version 2.1
 *
 */

public class XmlOutputGenerator {
	/** Static Variables */
	private static final Logger LOGGER = LogManager.getLogger(XmlOutputGenerator.class);
	private static final String IMAGE_PATH = "/images/";
	private static final String FAVORIATE_IMAGE_FILE_NAME = "favorite.png";
	private static final String UNFAVORIATE_IMAGE_FILE_NAME = "unfavorite.png";
	final static String DATEFORMAT_US = "MM/dd/yyyy";
	private Locale locale;
	public static final String MESSAGE_BUNDLE_NAME = "com.lexmark.resources.messages";
	/**
	 * 
	 * @param locale
	 * 
	 */

	public  XmlOutputGenerator(Locale locale){
		setLocale(locale);
	}
	/**
	 * 
	 * @param 
	 * @return Locale
	 */
	public Locale getLocale() {
		if(locale ==null) {
			locale = Locale.US;
		}
		return locale;
	}
	
	/**
	 * 
	 * @param locale
	 * 
	 */
	
	public void setLocale(Locale locale) {
		this.locale = locale;
	}
	
	/**
	 * 
	 * @param pendingPartList 
	 * @return
	 */
	public String convertPendingPartListToXML(List<PartLineItem> pendingPartList) {
		if(pendingPartList == null || pendingPartList.isEmpty()){
			return "<?xml version=\\\"1.0\\\" ?><rows></rows>";
		} 
		
		int listSize = pendingPartList.size();
		GridXmlBuilder gridXmlBuilder = new GridXmlBuilder(listSize);
		String cellValue = null;
		PartLineItem partLineItem = null;
		for(int i = 0;i < listSize;i++){
			gridXmlBuilder.nextRow(String.valueOf(i));
			partLineItem = pendingPartList.get(i);
			cellValue = DateLocalizationUtil.localizeDateTime(partLineItem.getRequestedDate(), false, locale);
			gridXmlBuilder.addCells(cellValue);
			cellValue = XMLEncodeUtil.escapeXML(partLineItem.getPartNumber());
			gridXmlBuilder.addCells(cellValue);
			cellValue = XMLEncodeUtil.escapeXML(partLineItem.getPartName());
			gridXmlBuilder.addCells(cellValue);
			//Changes for CI7 14-07-06 Start
			if((partLineItem.getLineStatus())!=null)
			{
				cellValue = XMLEncodeUtil.escapeXML(partLineItem.getLineStatus().getValue());
			}
			else
			{
				cellValue = XMLEncodeUtil.escapeXML("");
			}
			
			gridXmlBuilder.addCells(cellValue);
			//Changes for CI7 BRD 14-07-06 END
			if(!String.valueOf(partLineItem.getQuantity()).equals("0")){
				cellValue = String.valueOf(partLineItem.getQuantity());
			}else{
				cellValue = String.valueOf(partLineItem.getRecommendedQuantity());
			}
			
			gridXmlBuilder.addCells(cellValue);
			
			//CI-6 Changes Start
			String flagCellValuee = partLineItem.getExpedite();
			if(flagCellValuee != null && ("Y".equals(flagCellValuee.toUpperCase()) || "YES".equals(flagCellValuee.toUpperCase()))){
				gridXmlBuilder.addCells("Yes");
			}else{
				gridXmlBuilder.addCells("No");
			}
			//CI-6 Changes End
			
			if(partLineItem.isReturnRequiredFlag()){
				cellValue = localizeMessage("claim.lebel.returnRequired.yes",null);
			}else{
				cellValue = localizeMessage("claim.lebel.returnRequired.no",null);
			}
			gridXmlBuilder.addCells(cellValue);
		}
		gridXmlBuilder.finish();
		String contentXML = gridXmlBuilder.getGridXmlString();
		return contentXML;
	}
	
	/**
	 * Added by MPS Offshore Team for Pending Shipment Part List 
	 * @param pendingShipmentPartList
	 * @return pendingShipmentPartList is XML String format 
	 */
	
	public String convertPendingShipmentPartListToXML(List<ServiceRequestOrderLineItem> pendingShipmentPartList,Order orderDetail, String timeZoneOffset) {
		LOGGER.debug("XmlOutputGenerator.convertPendingShipmentPartListToXML--Enter:"+pendingShipmentPartList);
		if(pendingShipmentPartList == null || pendingShipmentPartList.size() ==0){
			return "<?xml version=\\\"1.0\\\" ?><rows></rows>";
		} 
		
		int listSize = pendingShipmentPartList.size();
		GridXmlBuilder gridXmlBuilder = new GridXmlBuilder(listSize);
		String cellValue = null;
		ServiceRequestOrderLineItem partLineItem = null;
		for(int i = 0;i < listSize;i++){
			gridXmlBuilder.nextRow(String.valueOf(i));
			partLineItem = pendingShipmentPartList.get(i);
			cellValue = DateLocalizationUtil.localizeDateTime(orderDetail.getOrderDate(), false, locale);
			gridXmlBuilder.addCells(cellValue);
			//Updated by sagar
			//if(partLineItem.getProductTLI()!=null)
			if(partLineItem.getPartnumber()!=null)
			{
				cellValue = partLineItem.getPartnumber();
			}else{
				cellValue = "";
			}
			
			gridXmlBuilder.addCells(cellValue);
			
			if(partLineItem.getPartName()!=null)
			{
				cellValue = XMLEncodeUtil.escapeXML(partLineItem.getPartName());
			}else{
				cellValue = "";
			}
			
			gridXmlBuilder.addCells(cellValue);
			//Updated by sagar
			//if(partLineItem.getOrderLineType()!=null)
			if(partLineItem.getPartType()!=null)
			{
				cellValue = XMLEncodeUtil.escapeXML(partLineItem.getPartType());
			}else{
				cellValue = "";
			}
			gridXmlBuilder.addCells(cellValue);
			
			cellValue = String.valueOf(partLineItem.getPendingQuantity());
			
			gridXmlBuilder.addCells(cellValue);		
			
			if(String.valueOf(partLineItem.getBackOrderQuantity())!="null" || String.valueOf(partLineItem.getBackOrderQuantity())!=null)
			{
				cellValue = String.valueOf(partLineItem.getBackOrderQuantity());		
			}else{
				cellValue = "";
			}
			gridXmlBuilder.addCells(cellValue);
			
			if(partLineItem.getEta()!=null && partLineItem.getEta().trim()!="")
			{
				
				try {
					cellValue = DateLocalizationUtil.localizeDateTime(DateUtil.adjustDateWithOffset(DateUtil.getStringToLocalizedDate(partLineItem.getEta(), true, locale), Float.valueOf(timeZoneOffset)),true, locale);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					LOGGER.error("Parse exception occured"+e.getMessage());
				}
			}else{
				cellValue = "";
			}
			gridXmlBuilder.addCells(cellValue);
		}
		gridXmlBuilder.finish();
		String contentXML = gridXmlBuilder.getGridXmlString();
		LOGGER.debug("XmlOutputGenerator.convertPendingShipmentPartListToXML--Exit:");
		return contentXML;
	}
	
	/**
	 * Added by MPS Offshore Team for creating Pending Shipment Part List for Edit
	 * @param pendingShipmentPartList
	 * @param carrierDropDown
	 * @param contextPath
	 * @return pendingShipmentPartList in edit and XML String format 
	 * @throws Exception
	 */
	public String convertPendingShipmentPartListForEditToXML(List<ServiceRequestOrderLineItem> pendingShipmentPartList, Map<String, String> carrierDropDown, String contextPath, String timeZoneOffset) throws Exception {
		if(pendingShipmentPartList == null || pendingShipmentPartList.size() ==0){
			return "";
		} 
		LOGGER.debug("Enter Method convertPendingShipmentPartListForEditToXML");
		StringBuilder strBuilder = null;
		int listSize = pendingShipmentPartList.size();
		String cellValue = null;
		ServiceRequestOrderLineItem partLineItem = null;
		String contentTable="";
		if (listSize>0){
			strBuilder = new StringBuilder();
			strBuilder.append("<div>" +
					"<table class=\"displayGrid\" id=\"pendingShipmentTable\">" +
					"<thead>" +
						"<tr>" +
							"<th class=\"rowtext\">&nbsp;</th>" +
							"<th class=\"rowtext\">&nbsp;</th>" +
							"<th class=\"rowtext\">" +localizeMessage("requestInfo.serviceOrder.listHeader.pendingShipment.quantity", null) + "</th>" +
							"<th width=\"50\" class=\"rowtext\">" +localizeMessage("requestInfo.serviceOrder.listHeader.pendingShipment.partNumber", null) + "</th>" +
							"<th class=\"rowtext w200\">" +localizeMessage("requestInfo.serviceOrder.listHeader.pendingShipment.partName", null) + "</th>" +
							"<th width=\"60\" class=\"rowtext\">" +localizeMessage("requestInfo.serviceOrder.listHeader.pendingShipment.shipQuantity", null) + "</th>" +
							"<th width=\"60\">" +localizeMessage("requestInfo.serviceOrder.listHeader.pendingShipment.carrier", null) + "</th>" +
							"<th class=\"w100\">" +localizeMessage("requestInfo.serviceOrder.listHeader.pendingShipment.trackingNum", null) + "</th>" +
							"<th class=\"w200\">" +localizeMessage("requestInfo.serviceOrder.listHeader.pendingShipment.actualShipDate", null) + "</th>" +
							"<th width=\"100\">" +localizeMessage("requestInfo.serviceOrder.listHeader.pendingShipment.backOrderQty", null) + "</th>" +
							"<th class=\"w200\">" +localizeMessage("requestInfo.serviceOrder.listHeader.pendingShipment.expShipDate", null) + "</th>" +
						"</tr>" +
					"</thead>" +
						"<tbody>");
			contentTable=contentTable+strBuilder.toString();
			LOGGER.debug("---------Content Table--- "+ contentTable);
			
		}
		
		for(int i = 0;i < listSize;i++){
			partLineItem = pendingShipmentPartList.get(i);
			LOGGER.debug(" listSize-- " +listSize);
			String labelMessage = localizeMessage("requestInfo.label.serviceOrder.serialNumber", null);
			String serialNumberMessage = localizeMessage("requestInfo.message.serviceOrder.enterSerialNumber", null);
			StringBuilder newRowBuilder=new StringBuilder();
			newRowBuilder.append("<tr id=\"newRowData_"+i+"\"><input type=\"hidden\" class=\"rowNum\" id=\"row_ID_"+i+"\" value=\""+i+"\" />");
			StringBuilder subRowAdd = new StringBuilder();
			subRowAdd.append("<tr id=\"newSubRowData_"+i+"\" style=\"display:none;\">" +
							"<td colspan=\"5\"><strong>"+ labelMessage +"</strong></td>"+
							"<td colspan=\"3\"><textarea class=\"w200\" id=\"serialNo_row"+i+"\"></textarea><br />"+
							"<p class=\"note\">"+serialNumberMessage+"</p></td>"+
							"<td colspan=\"3\" class=\"btnSelectGrid\">");
			subRowAdd.append("<button type=\"button\" id=\"anotherRow_"+i+"\"class=\"button\" onclick=\"addRowToGrid('"+i+"','"+listSize+"');\">").append(getLocalizeAddLineBtn()).append("</button></td></tr>");

			cellValue=newRowBuilder.toString();
			LOGGER.debug(" NewRowBuilder--- "+newRowBuilder.toString());
			contentTable=contentTable+cellValue;
			if(String.valueOf(partLineItem.getPendingQuantity())!=null && String.valueOf(partLineItem.getPendingQuantity()) != "null")
			{
				cellValue=String.valueOf(partLineItem.getPendingQuantity());
			}else{
				cellValue="";
			}
			contentTable=contentTable+"<td style=\"width:50px;\"class=\"rowtext\" onClick=\"changeCellStatus("+i+");\"><img id=\"subrow_img_"+i+"\" src=\"/LexmarkPartnerPortal/images/gridImgs/plus.gif\" /></td><td></td><td>"+cellValue+"</td>";

			cellValue = String.valueOf(partLineItem.getPartnumber());//updated by sagar
			contentTable=contentTable+"<td class=\"rowtext\">"+cellValue+"</td>";

			/* To be changed*/
			
			/* Added for defect 2129*/
			cellValue = String.valueOf(partLineItem.getPartName());//updated by sagar
			contentTable=contentTable+"<td class=\"rowtext\">"+cellValue+"</td>";
			cellValue = "<input type=\"hidden\" id=\"lineId_"+i+"\" value=\""+partLineItem.getId()+"\" /><input type=\"text\"  id=\"ship_quantity_row"+i+"\"class=\"w50 right\" value=\""+String.valueOf(partLineItem.getShippedQuantity())+"\" />";			
			contentTable=contentTable+"<td class=\"rowtext\">"+cellValue+"</td>";
			
			StringBuilder tempString=new StringBuilder();
			tempString.append("<select id=\"carrier_row"+i+"\"class=\"w150\">");
			tempString.append("<option></option>");
			for (Map.Entry<String, String> entry : carrierDropDown.entrySet()) {
				String key = entry.getKey().toString();
				String value = entry.getValue().toString();
				if(value != null){
					if(value.equals(partLineItem.getCarrier())){
						tempString.append("<option value=\""+key+"\" selected >"+value+"</option>");
					}
					else{
						tempString.append("<option value=\""+key+"\">"+value+"</option>");
					}
				}
			}
			tempString.append("</select>");
			cellValue=tempString.toString();
			contentTable=contentTable+"<td class=\"rowtext\">"+cellValue+"</td>";
			
			if(partLineItem.getTrackingNumber()!= null)
			{
				cellValue="<input type=\"text\"  id=\"tracking_number_row"+i+"\" class=\"w100\" value=\""+ partLineItem.getTrackingNumber() +"\"/>";
			}else{
				cellValue="<input type=\"text\"  id=\"tracking_number_row"+i+"\" class=\"w100\" value=\"\"/>";
			}
			contentTable=contentTable+"<td class=\"rowtext\">"+cellValue+"</td>";
			
			if(partLineItem.getActualShipDate()!= null)
			{
				cellValue="<input type=\"text\"  id=\"actual_shipped_date_row"+i+"\"class=\"w100\" style=\"width:100px!important; float:left\" " +
					" value=\"" +DateLocalizationUtil.localizeDateTime(partLineItem.getActualShipDate(), true, locale) +"\" readOnly=\"readonly\"/>" +
					"<img class=\"ui-icon calendar-icon\" src=\""+contextPath+IMAGE_PATH+"transparent.png\" title=\"Select a Date\" width=\"23\" height=\"23\" " +
							"onClick=\"showcal('actual_shipped_date_row"+i+"',null,null,true)\"/>";
			}else{
				cellValue="<input type=\"text\"  id=\"actual_shipped_date_row"+i+"\"class=\"w100\" style=\"width:100px!important; float:left\" " +
						" value=\"\" readOnly=\"readonly\"/>" +
						"<img class=\"ui-icon calendar-icon\" src=\""+contextPath+IMAGE_PATH+"transparent.png\" title=\"Select a Date\" width=\"23\" height=\"23\" " +
								"onClick=\"showcal('actual_shipped_date_row"+i+"',null,null,true)\"/>";
			}
			
			contentTable=contentTable+"<td width=\"150\" class=\"rowtext\">"+cellValue+"</td>";
			
			/* To be changed*/
			
			if(String.valueOf(partLineItem.getBackOrderQuantity()) != null && String.valueOf(partLineItem.getBackOrderQuantity()) != "null")
			{
				cellValue = "<input type=\"hidden\" id=\"back_order_orig_"+i+"\" value=\""+String.valueOf(partLineItem.getBackOrderQuantity())+"\" /><input type=\"text\"  id=\"back_order_quantity_row"+i+"\" class=\"w50\" value=\""+String.valueOf(partLineItem.getBackOrderQuantity())+"\" />";
			}else{
				cellValue = "<input type=\"hidden\" id=\"back_order_orig_"+i+"\" value=\"0\" /> ";
			}
			
			contentTable=contentTable+"<td class=\"rowtext\">"+cellValue+"</td>";
			if(partLineItem.getEta()!= null && partLineItem.getEta().trim()!="")
			{
				String etaDate = null;
				try {
				 etaDate = DateLocalizationUtil.localizeDateTime(DateUtil.adjustDateWithOffset(DateUtil.getStringToLocalizedDate(partLineItem.getEta(), true, locale), Float.valueOf(timeZoneOffset)),true, locale);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					LOGGER.error("Parse Excepiont "+e.getMessage());
				}
				
				
				
				cellValue="<input type=\"text\"  id=\"eta_row"+i+"\" class=\"w100\" style=\"width:100px!important; float:left\" " +
				" value=\"" +etaDate +"\" readOnly=\"readonly\"/>" +
				"<img class=\"ui-icon calendar-icon\" src=\""+contextPath+IMAGE_PATH+"transparent.png\" title=\"Select a Date\" width=\"23\" height=\"23\" " +
						"onClick=\"showcal('eta_row"+i+"',null,null,true)\"/>";
			}else{
				cellValue="<input type=\"text\"  id=\"eta_row"+i+"\" class=\"w100\" style=\"width:100px!important; float:left\" " +
						" value=\"\" readOnly=\"readonly\"/>" +
						"<img class=\"ui-icon calendar-icon\" src=\""+contextPath+IMAGE_PATH+"transparent.png\" title=\"Select a Date\" width=\"23\" height=\"23\" " +
								"onClick=\"showcal('eta_row"+i+"',null,null,true)\"/>";
			}
						
			contentTable=contentTable+"<td width=\"150\">"+cellValue+"</td></tr>";
			contentTable=contentTable+subRowAdd.toString();
			
		}
		contentTable=contentTable+("</tbody></table></div>");
		LOGGER.debug("---------Exit Method convertPendingShipmentPartListForEditToXML");
		return StringEscapeUtils.escapeJavaScript(contentTable);
	}
	
	
	
	/**
	 * Added by MPS Offshore Team for creating Pending Order Part List
	 * @param pendingShipmentPartList
	 * @param carrierDropDown
	 * @param contextPath
	 * @return pendingShipmentPartList in edit and XML String format 
	 */
	public String convertPendingOrderPartListToXML(List<PartLineItem> pendingShipmentPartList) {
		if(pendingShipmentPartList == null || pendingShipmentPartList.size() ==0){
			return "<?xml version=\\\"1.0\\\" ?><rows></rows>";
		} 
		
		int listSize = pendingShipmentPartList.size();
		GridXmlBuilder gridXmlBuilder = new GridXmlBuilder(listSize);
		String cellValue = null;
		PartLineItem partLineItem = null;
		for(int i = 0;i < listSize;i++){
			gridXmlBuilder.nextRow(String.valueOf(i));
			partLineItem = pendingShipmentPartList.get(i);			
			cellValue = XMLEncodeUtil.escapeXML(partLineItem.getPartNumber());
			gridXmlBuilder.addCells(cellValue);
			cellValue = XMLEncodeUtil.escapeXML(partLineItem.getPartName());
			gridXmlBuilder.addCells(cellValue);
			cellValue = XMLEncodeUtil.escapeXML(partLineItem.getPartType());
			gridXmlBuilder.addCells(cellValue);		
			cellValue = String.valueOf(partLineItem.getOrderQuantity());
			gridXmlBuilder.addCells(cellValue);
		}
		gridXmlBuilder.finish();
		String contentXML = gridXmlBuilder.getGridXmlString();
		return contentXML;
	}
	
	
	/**
	 * Added by MPS Offshore Team for creating Processed Parts List
	 * @param processedPartList
	 * @return processedPartList in XML String format 
	 */
	public String convertProcessedPartListToXML(List<List<ServiceRequestOrderLineItem>> processedPartList, String timeZoneOffset) {
		LOGGER.debug("XmlOutputGenerator.convertProcessedPartListToXML.Enter:"+processedPartList);
		if(processedPartList == null || processedPartList.size() ==0){
			return "<?xml version=\\\"1.0\\\" ?><rows></rows>";
		} 
		
		int listSize = processedPartList.size();
		GridXmlBuilder gridXmlBuilder = new GridXmlBuilder(listSize);
		String cellValue = null;
		StringBuilder strBulder = null;
		StringBuilder serialNoList = null;
		ServiceRequestOrderLineItem partLineItem = null;
		int i=0;
		
		for(List<ServiceRequestOrderLineItem> processedList:processedPartList)
		{			
			int miniListSize = processedList.size();
			gridXmlBuilder.nextRow(String.valueOf(i));
			partLineItem = processedList.get(0);
			String labelMessage = localizeMessage("requestInfo.label.serviceOrder.serialNumber", null);
			String partNumber = localizeMessage("requestInfo.label.serviceOrder.partNumber", null);
			String partName = localizeMessage("requestInfo.label.serviceOrder.partName", null);
			String partType = localizeMessage("requestInfo.label.serviceOrder.partType", null);
			String orderedQty = localizeMessage("requestInfo.label.serviceOrder.quantity", null);
			strBulder = new StringBuilder();
			serialNoList = new StringBuilder();
			strBulder.append("<div class=\"dhx_sub_row\">");
			
			if(miniListSize > 1)
			{
				strBulder.append("<table class=\"displayGrid\"><thead><tr><th class=\"wAuto\">"+partNumber+"</th><th>"+partName+"</th><th class=\"wAuto\">"+partType+"</th><th class=\"wAuto right\">"+orderedQty+"</th></tr></thead><tbody>");
//				strBulder.append("<table class=\"displayGrid\"><thead><tr><th class=\"w100\">Part No.</th><th>Part Name</th><th class=\"w150\">Part Type</th><th class=\"w100 right\">Quantity</th></tr></thead><tbody>");
				for(int j=0;j<miniListSize;j++)
				{
					if(j%2==0)
					{
						strBulder.append("<tr class=\"altRow\">");
					}
					else{
						strBulder.append("<tr>");
					}
					strBulder.append("<td class=\"wAuto\">"+processedList.get(j).getPartnumber()+"</td>");//updated by sagar
					strBulder.append("<td>"+XMLEncodeUtil.escapeXML(processedList.get(j).getPartName())+"</td>");//updated by sagar
					strBulder.append("<td>"+XMLEncodeUtil.escapeXML(processedList.get(j).getPartType())+"</td>");//updated by sagar
					strBulder.append("<td class=\"wAuto right\">"+String.valueOf(processedList.get(j).getShippedQuantity())+"</td></tr>");
					if(processedList.get(j).getSerialNumbers()!=null)
					{
						for(int l=0;l<processedList.get(j).getSerialNumbers().size();l++)
						{
							serialNoList.append(processedList.get(j).getSerialNumbers().get(l));
							if(processedList.get(j).getSerialNumbers().size()!=l+1){
								serialNoList.append(",");							
							}
						}
						serialNoList.append(" ");
					}
				}
				strBulder.append("</tbody><table class=\"dataGrid wHalf\"><tbody><tr><td class=\"label w30p\">");	
				strBulder.append(labelMessage);
				strBulder.append("</td><td>");
				strBulder.append(serialNoList);
				strBulder.append("</td></tr></tbody></table></div>");	
			}
			else
			{				
				strBulder.append("<table class=\"displayGrid\"><thead><tr><th class=\"wAuto\">"+partNumber+"</th><th>"+partName+"</th><th class=\"wAuto\">"+partType+"</th><th class=\"wAuto\">"+orderedQty+"</th></tr></thead><tbody>");
//				strBulder.append("<table class=\"displayGrid\"><thead><tr><th class=\"w100\">Part No.</th><th>Part Name</th><th class=\"w150\">Part Type</th><th class=\"w100 right\">Quantity</th></tr></thead><tbody>");
				strBulder.append("<tbody><tr><td class=\"wAuto\">"+partLineItem.getPartnumber()+"</td>");//updated by sagar
				strBulder.append("<td>"+XMLEncodeUtil.escapeXML(partLineItem.getPartName())+"</td>");//updated by sagar
				strBulder.append("<td>"+XMLEncodeUtil.escapeXML(partLineItem.getPartType())+"</td>");//updated by sagar
				strBulder.append("<td class=\"wAuto right\">"+String.valueOf(partLineItem.getShippedQuantity())+"</td></tr></tbody>");
				strBulder.append("<table class=\"dataGrid wHalf\"><tbody><tr><td class=\"label w30p\">");	
				strBulder.append(labelMessage);
				strBulder.append("</td><td>");
				if(partLineItem.getSerialNumbers()!=null)
				{
					for(int l=0;l<partLineItem.getSerialNumbers().size();l++)
					{
						strBulder.append(partLineItem.getSerialNumbers().get(l));
						if(!(l==partLineItem.getSerialNumbers().size()-1))
						{
							strBulder.append(",");
						}
					}
				}
				strBulder.append("</td></tr></tbody></table></div>");				
			}
			
			cellValue = strBulder.toString();
			gridXmlBuilder.addCells(cellValue);
			
			if (LexmarkConstants.CARRIER_LIST.contains(partLineItem.getCarrier())) {
				StringBuilder strBulder1 = new StringBuilder();
				strBulder1.append("<a href=\"###\" onClick=\"onTrackingNumberClick('").append(partLineItem.getCarrier())
						.append("','").append(partLineItem.getTrackingNumber()).append("')\">").append(
								partLineItem.getTrackingNumber()).append("</a>");
				gridXmlBuilder.addCells(strBulder1.toString());
			} else {
				gridXmlBuilder.addCells(partLineItem.getTrackingNumber());
			}
			gridXmlBuilder.addCells(partLineItem.getCarrier());
			
			gridXmlBuilder.addCells(partLineItem.getStatus());		
			if (partLineItem.getActualShipDate() == null){
				cellValue = "";
			}else{
				//cellValue = DateLocalizationUtil.localizeDateTime(partLineItem.getActualShipDate(), true, locale);
				if(timeZoneOffset!=null){					
					cellValue = DateLocalizationUtil.localizeDateTime(DateUtil.adjustDateWithOffset(partLineItem.getActualShipDate(), Float.valueOf(timeZoneOffset)), true, locale);
				}
				else{
					cellValue = DateLocalizationUtil.localizeDateTime(partLineItem.getActualShipDate(), true, locale);
				}
			}
			gridXmlBuilder.addCells(cellValue);
			if (partLineItem.getActualDeliveryDate() == null){
				cellValue = "";
			}else{
				//cellValue = DateLocalizationUtil.localizeDateTime(partLineItem.getActualDeliveryDate(), true, locale);
				if(timeZoneOffset!=null){					
					cellValue = DateLocalizationUtil.localizeDateTime(DateUtil.adjustDateWithOffset(partLineItem.getActualDeliveryDate(), Float.valueOf(timeZoneOffset)), true, locale);
				}
				else{
					cellValue = DateLocalizationUtil.localizeDateTime(partLineItem.getActualDeliveryDate(), true, locale);
				}
			}
			gridXmlBuilder.addCells(cellValue);
			i++;
		}
		gridXmlBuilder.finish();
		String contentXML = gridXmlBuilder.getGridXmlString();
		return contentXML;
	}
	
	/**
	 * Added by MPS Offshore Team for creating Processed Part List for Edit
	 * @param processedPartList
	 * @param statusDropDown
	 * @param contextPath
	 * @return processedPartList in edit and XML String format 
	 */
		public String convertProcessedPartListForEditToXML(List<List<ServiceRequestOrderLineItem>> processedPartList, Map<String, String> statusDropDown, String contextPath , List<String> userRoleList, String timeZoneOffset) {
		if(processedPartList == null || processedPartList.size() ==0){
			return "<?xml version=\\\"1.0\\\" ?><rows></rows>";
		} 
		
		
		int listSize = processedPartList.size();
		GridXmlBuilder gridXmlBuilder = new GridXmlBuilder(listSize);
		String cellValue = null;
		StringBuilder strBulder = null;
		StringBuilder serialNoList = null;
		StringBuilder partList = null;
		StringBuilder lineList = null;
		ServiceRequestOrderLineItem partLineItem = null;
		int i=0;
		
		for(List<ServiceRequestOrderLineItem> processedList:processedPartList)
		{			
			int miniListSize = processedList.size();
			
			gridXmlBuilder.nextRow(String.valueOf(i));
			partLineItem = processedList.get(0);
			String labelMessage = localizeMessage("requestInfo.label.serviceOrder.serialNumber", null);
			String partNumber = localizeMessage("requestInfo.label.serviceOrder.partNumber", null);
			String partName = localizeMessage("requestInfo.label.serviceOrder.partName", null);
			String partType = localizeMessage("requestInfo.label.serviceOrder.partType", null);
			String orderedQty = localizeMessage("requestInfo.label.serviceOrder.quantity", null);
			strBulder = new StringBuilder();
			serialNoList = new StringBuilder();
			partList = new StringBuilder();
			lineList = new StringBuilder();
			strBulder.append("<div class=\"dhx_sub_row\">");
			
			if(miniListSize > 1)
			{
				strBulder.append("<table class=\"displayGrid\"><thead><tr><th class=\"w100\">"+partNumber+"</th><th>"+partName+"</th><th class=\"w150\">"+partType+"</th><th class=\"w100 right\">"+orderedQty+"</th></tr></thead><tbody>");
				for(int j=0;j<miniListSize;j++)
				{
					if(j%2==0)
					{
						strBulder.append("<tr class=\"altRow\">");
					}
					else{
						strBulder.append("<tr>");
					}
					
					lineList.append(processedList.get(j).getId());
					if(processedList.get(j).getPartnumber()!=null)//updated by sagar
					{
						strBulder.append("<td class=\"w80\">"+processedList.get(j).getPartnumber()+"</td>");//updated by sagar
						partList.append(processedList.get(j).getPartnumber());
					}else{
						strBulder.append("<td class=\"w80\"></td>");
					}
					
					if(j+1<miniListSize)
					{
						partList.append(",");
						lineList.append(",");
					}
//					strBulder.append("<input type=\"hidden\" id=\"partNumber_"+i+"\" value=\""+processedList.get(j).getPartnumber()+"\">");
					
					if(processedList.get(j).getPartName()!=null)
					{
						strBulder.append("<td>"+XMLEncodeUtil.escapeXML(processedList.get(j).getPartName())+"</td>");//updated by sagar
					}else{
						strBulder.append("<td></td>");
					}
					
					if(processedList.get(j).getPartType()!=null)					
					{
						strBulder.append("<td>"+XMLEncodeUtil.escapeXML(processedList.get(j).getPartType())+"</td>");
					}else{
						strBulder.append("<td></td>");
					}
					
					strBulder.append("<td class=\"w100 right\">"+String.valueOf(processedList.get(j).getShippedQuantity())+"</td></tr>");					
					
					if(processedList.get(j).getSerialNumbers()!=null)
					{
						for(int l=0;l<processedList.get(j).getSerialNumbers().size();l++)
						{
							serialNoList.append(processedList.get(j).getSerialNumbers().get(l));
							if(processedList.get(j).getSerialNumbers().size()!=l+1){
								serialNoList.append(",");							
							}
						}
						serialNoList.append(" ");
					}
					
				}
				
				strBulder.append("</tbody><table class=\"dataGrid wHalf\"><tbody><tr><td class=\"label w30p\">");	
				strBulder.append(labelMessage);
				strBulder.append("</td><td>");				
				strBulder.append(serialNoList);
				strBulder.append("</td></tr></tbody></table></div>");	
			}
			else
			{				
				strBulder.append("<table class=\"displayGrid\"><thead><tr><th class=\"w100\">"+partNumber+"</th><th>"+partName+"</th><th class=\"w150\">"+partType+"</th><th class=\"w100 right\">"+orderedQty+"</th></tr></thead><tbody>");
				lineList.append(partLineItem.getId());
				if(partLineItem.getPartnumber()!=null)
				{
					strBulder.append("<tbody><tr><td class=\"w80\">"+partLineItem.getPartnumber()+"</td>");//updated by sagar
					partList.append(partLineItem.getPartnumber());
				}else{
					strBulder.append("<tbody><tr><td class=\"w80\"></td>");
				}
				
				if(partLineItem.getPartName()!=null)	
				{
					strBulder.append("<td>"+XMLEncodeUtil.escapeXML(partLineItem.getPartName())+"</td>");//updated by sagar
				}else{
					strBulder.append("<td></td>");
				}
				
				if(partLineItem.getPartType()!=null)	
				{
					strBulder.append("<td>"+XMLEncodeUtil.escapeXML(partLineItem.getPartType())+"</td>");//updated by sagar
				}else{
					strBulder.append("<td></td>");
				}
				
				if(String.valueOf(partLineItem.getShippedQuantity())!=null && String.valueOf(partLineItem.getShippedQuantity())!="null")
				{
					strBulder.append("<td class=\"w100 right\">"+String.valueOf(partLineItem.getShippedQuantity())+"</td></tr></tbody>");
				}else{
					strBulder.append("<td class=\"w100 right\"></td></tr></tbody>");
				}
				
				
				strBulder.append("<table class=\"dataGrid wHalf\"><tbody><tr><td class=\"label w30p\">");	
				strBulder.append(labelMessage);
				strBulder.append("</td><td>");
				
				/*if(partLineItem.getSerialNumber()!=null)
				{	
					strBulder.append(partLineItem.getSerialNumber());
				}*/
				
				if(partLineItem.getSerialNumbers()!=null)
				{
					for(int l=0;l<partLineItem.getSerialNumbers().size();l++)
					{
						strBulder.append(partLineItem.getSerialNumbers().get(l));
						if(!(l==partLineItem.getSerialNumbers().size()-1)){
							strBulder.append(",");
						}
						
					}
					
					/*if(strBulder.charAt(partLineItem.getSerialNumbers().size()) == (',')){
						strBulder = strBulder.insert(strBulder.length()-1,"");
					}*/
				}
				
				strBulder.append("</td></tr></tbody></table></div>");				
			}
			
			cellValue = strBulder.toString();
			gridXmlBuilder.addCells(cellValue);
			
			if(partLineItem.getTrackingNumber()!=null)
			{
				if (LexmarkConstants.CARRIER_LIST.contains(partLineItem.getCarrier())) {
					StringBuilder strBulder1 = new StringBuilder();
					strBulder1.append("<a href=\"###\" onClick=\"onTrackingNumberClick('").append(partLineItem.getCarrier())
							.append("','").append(partLineItem.getTrackingNumber()).append("')\">").append(
									partLineItem.getTrackingNumber()).append("</a>");
					gridXmlBuilder.addCells(strBulder1.toString());
				} else {
					gridXmlBuilder.addCells(partLineItem.getTrackingNumber());
				}
			}else{
				gridXmlBuilder.addCells("");
			}
			
			if(partLineItem.getCarrier()!=null)
			{
				gridXmlBuilder.addCells(partLineItem.getCarrier());
			}else{
				gridXmlBuilder.addCells("");
			}
			
			StringBuilder strBuilder1 = new StringBuilder();
		
			if(!userRoleList.isEmpty() && ((userRoleList.contains(LexmarkConstants.ROLE_SERVICE_MANAGER)) || (userRoleList.contains(LexmarkConstants.ROLE_SERVICE_TECHNICIAN))))
			{
				strBuilder1.append("<select class=\"w100\" id=\"status_"+i+"\" style=\"width:130px;\" >");
				strBuilder1.append("<option></option>");
				/*for (Map.Entry<String, String> entry : statusDropDown.entrySet()) {
					String key = entry.getKey().toString();
					String value = entry.getValue().toString();
//					if(value.equalsIgnoreCase(localizeMessage("requestInfo.label.serviceOrder.shipped",null)) || value.equalsIgnoreCase(localizeMessage("requestInfo.label.serviceOrder.delivered",null)))
					
					if(value.equalsIgnoreCase("Shipped") || value.equalsIgnoreCase("Delivered"))
					{
						if(partLineItem.getStatus()!=null)
						{
							if(value.equals(partLineItem.getStatus()))
							{
								strBuilder1.append("<option value=\""+key+"\" selected>"+value+"</option>");
							}
							else
							{
								strBuilder1.append("<option value=\""+key+"\">"+value+"</option>");
							}
						}else{
							strBuilder1.append("<option value=\""+key+"\">"+value+"</option>");
						}
					}
				}*/
				
				if(partLineItem.getStatus()!=null)
				{
					if(partLineItem.getStatus().equalsIgnoreCase("Shipped"))
					{
						strBuilder1.append("<option value=\"Shipped\" selected>Shipped</option>");
						strBuilder1.append("<option value=\"Delivered\">Delivered</option>");
					}else if(partLineItem.getStatus().equalsIgnoreCase("Delivered")){
						strBuilder1.append("<option value=\"Shipped\">Shipped</option>");
						strBuilder1.append("<option value=\"Delivered\" selected>Delivered</option>");
					}else{
						strBuilder1.append("<option value=\"Shipped\">Shipped</option>");
						strBuilder1.append("<option value=\"Delivered\">Delivered</option>");
					}
				}else{
					strBuilder1.append("<option value=\"Shipped\">Shipped</option>");
					strBuilder1.append("<option value=\"Delivered\">Delivered</option>");
				}
				strBuilder1.append("</select>");		
			}else{
				strBuilder1.append(partLineItem.getStatus());
			}
			if(partLineItem.getStatus()!=null)
			{
				/*commented for testing*/
				/*if(partLineItem.getStatus().equalsIgnoreCase(localizeMessage("requestInfo.label.serviceOrder.delivered",null)))
				{
					cellValue = partLineItem.getStatus();
				}
				else
				{*/
					cellValue = strBuilder1.toString();
					
				/*}*/
			}else{
				cellValue = strBuilder1.toString();
			}
			
			gridXmlBuilder.addCells(cellValue);			
			if(partLineItem.getActualShipDate() == null){
				cellValue = "";
			}else{
				//cellValue = DateLocalizationUtil.localizeDateTime(partLineItem.getActualShipDate(), true, locale);
				if(timeZoneOffset!=null){					
					cellValue = DateLocalizationUtil.localizeDateTime(DateUtil.adjustDateWithOffset(partLineItem.getActualShipDate(), Float.valueOf(timeZoneOffset)), true, locale);
				}
				else{
					cellValue = DateLocalizationUtil.localizeDateTime(partLineItem.getActualShipDate(), true, locale);
				}
			}
			gridXmlBuilder.addCells(cellValue);
			
			
			String actualDelDate = null;
			if(timeZoneOffset!=null){
				
				actualDelDate = DateLocalizationUtil.localizeDateTime(DateUtil.adjustDateWithOffset(partLineItem.getActualDeliveryDate(), Float.valueOf(timeZoneOffset)), true, locale);
			}
			else{
				actualDelDate = DateLocalizationUtil.localizeDateTime(partLineItem.getActualDeliveryDate(), true, locale);
			}
			
			if(!userRoleList.isEmpty() && ((userRoleList.contains(LexmarkConstants.ROLE_SERVICE_MANAGER)) || (userRoleList.contains(LexmarkConstants.ROLE_SERVICE_TECHNICIAN))))
			{
				cellValue="<input type=\"hidden\" id=\"partNumber_"+i+"\" value=\""+partList+"\" /><input type=\"hidden\" id=\"processedLineId_"+i+"\" value=\""+lineList+"\" /><input type=\"hidden\" id=\"trackingNo_"+i+"\" value=\""+partLineItem.getTrackingNumber()+"\" /><input type=\"text\" class=\"w100\" style=\"width:100px!important; float:left\" id=\""+i+"_deliveredDateCal\" " +
	                    " value=\"" +actualDelDate +"\" readOnly=\"readonly\"/>" +
	                    "<img class=\"ui-icon calendar-icon\" src=\""+contextPath+IMAGE_PATH+"transparent.png\" title=\"Select a Date\" width=\"23\" height=\"23\" " +
	                                    "onClick=\"showcal('"+i+"_deliveredDateCal',null,null,true)\"/>";
			
			}else{
				cellValue="<input type=\"hidden\" id=\"partNumber_"+i+"\" value=\""+partList+"\" /><input type=\"hidden\" id=\"processedLineId_"+i+"\" value=\""+lineList+"\" /><input type=\"hidden\" id=\"trackingNo_"+i+"\" value=\""+partLineItem.getTrackingNumber()+"\" />"+
			               actualDelDate;
			}
			gridXmlBuilder.addCells(cellValue);
			i++;
		}				
		
		gridXmlBuilder.finish();
		String contentXML = gridXmlBuilder.getGridXmlString();
		LOGGER.debug("convertProcessedPartListForEditToXML.contentXML--->"+contentXML);
		return contentXML;
	}	
	
		
	/**
	 * Added by MPS Offshore Team for Email NOtification List
	 * @param emailActivitiesList
	 * @return emailActivitiesList in XML String format 
	 */
	public String convertEmailNotificationListToXML(List<ServiceRequestActivity> emailActivitiesList) {
		if(emailActivitiesList == null || emailActivitiesList.size() ==0){
			return "<?xml version=\\\"1.0\\\" ?><rows></rows>";
		} 
		String labelMessage = localizeMessage("claim.label.message", null);
		int listSize = emailActivitiesList.size();
		GridXmlBuilder gridXmlBuilder = new GridXmlBuilder(listSize);
		String cellValue = null;
		String detail = "";
		String subDetail = "";
		ServiceRequestActivity emailActivity = null;
		StringBuilder strBulder = null;
		for(int i = 0;i < listSize;i++){
			gridXmlBuilder.nextRow(String.valueOf(i));
			emailActivity = emailActivitiesList.get(i);
			if (emailActivity.getActivityDescription() == null){
				detail = "";
				subDetail = "";
			}
			else
			{
				detail = emailActivity.getActivityDescription();
				subDetail = emailActivity.getActivityDescription();
			}
			
			detail = (detail==null?"":detail.replaceAll("\r", ""));
			detail = detail.replaceAll("\n", " ");
			subDetail = (subDetail==null?"":subDetail.replaceAll("\r", ""));
			subDetail = subDetail.replaceAll("\n", "<br>");
			subDetail = subDetail.replaceAll(" ", "&nbsp;");
			
			strBulder = new StringBuilder();
			strBulder.append("<table>").append("<tr>")
				.append("<td><B>").append(labelMessage).append("</B></td></tr><tr><td>")
					.append(subDetail).append("</td></tr></table>");
			cellValue = strBulder.toString();
			gridXmlBuilder.addCells(cellValue);
			cellValue = DateLocalizationUtil.localizeDateTime(emailActivity.getActivityDate(), false, locale);
			gridXmlBuilder.addCells(cellValue);
			cellValue = XMLEncodeUtil.escapeXML(emailActivity.getRecipientEmail());
			gridXmlBuilder.addCells(cellValue);
			cellValue = detail;
			gridXmlBuilder.addCells(StringUtil.appendEllipsis(cellValue, 50));
		}
		gridXmlBuilder.finish();
		String contentXML = gridXmlBuilder.getGridXmlString();
		return contentXML;
	}
	
	/**
	 * Added by MPS Offshore Team for the implementation of Payments CR
	 * @param AccountsPayableList
	 * @param size
	 * @param posStart
	 * @return AccountsPayableList in XML String format 
	 */
	public String convertAccountsPayableListToXML(ResourceRequest request,List<AccountPayable> accountsPayableList,int size,int posStart){
		StringBuffer xml = new StringBuffer("<?xml version=\"1.0\" ?>\n");
		xml.append("<rows total_count=\"" + size + "\" pos=\"" + posStart+ "\">\n");
		int i=0;		
	
		for (AccountPayable accountsPayable: accountsPayableList) {
			
			String vendorId = accountsPayable.getVendorId() != null ? XMLEncodeUtil.escapeXML(accountsPayable.getVendorId()) : "";
			String vendorName = accountsPayable.getVendorName() != null ? XMLEncodeUtil.escapeXML(accountsPayable.getVendorName()) : "";			
			String country= accountsPayable.getCountry() != null ? XMLEncodeUtil.escapeXML(accountsPayable.getCountry()) : "";			
			String companyCode=accountsPayable.getCompanyCode() != null ? XMLEncodeUtil.escapeXML(accountsPayable.getCompanyCode()):"";
			String payeeName=accountsPayable.getPayeeName() != null ? XMLEncodeUtil.escapeXML(accountsPayable.getPayeeName()):"";
			String payeeAddressLine1= accountsPayable.getPayeeAddress() != null ? XMLEncodeUtil.escapeXML(accountsPayable.getPayeeAddress().getAddressLine1()) : "";			
			String payeeAddressLine2= accountsPayable.getPayeeAddress() != null ? XMLEncodeUtil.escapeXML(accountsPayable.getPayeeAddress().getAddressLine2()) : "";
			String payeeAddressLine3= accountsPayable.getPayeeAddress() != null ? XMLEncodeUtil.escapeXML(accountsPayable.getPayeeAddress().getAddressLine3()) : "";
			String payeeCity= accountsPayable.getPayeeAddress() != null ? XMLEncodeUtil.escapeXML(accountsPayable.getPayeeAddress().getCity()) : "";
			String payeeState= accountsPayable.getPayeeAddress() != null ? XMLEncodeUtil.escapeXML(accountsPayable.getPayeeAddress().getState()) : "";
			String payeeProvince= accountsPayable.getPayeeAddress() != null ? XMLEncodeUtil.escapeXML(accountsPayable.getPayeeAddress().getProvince()) : "";
			String payeeCountry= accountsPayable.getPayeeAddress() != null ? XMLEncodeUtil.escapeXML(accountsPayable.getPayeeAddress().getCountry()) : "";
			String payeePostalCode= accountsPayable.getPayeeAddress() != null ? XMLEncodeUtil.escapeXML(accountsPayable.getPayeeAddress().getPostalCode()) : "";	
			/*if(accountsPayable.getPayeeAddress()!=null)
			{
				PortletSession portletSession = request.getPortletSession();
				portletSession.setAttribute("vendorPayableAddress", accountsPayable.getPayeeAddress() ,PortletSession.APPLICATION_SCOPE);				
			}*/
			
			/*if (vendorId == "")
				continue;*/
			xml.append(" <row id=\""+i+"\">\n");
			xml.append("  <cell><![CDATA[" + vendorName+ "]]></cell>\n");
			xml.append("  <cell><![CDATA[" + vendorId+ "]]></cell>\n");
			xml.append("  <cell><![CDATA[" + country+ "]]></cell>\n");
			xml.append("  <cell><![CDATA[ <input name=\"btn_select\" class=\"button\"  type=\"button\" id=\"button"+i+"\" onclick=\"setVendorAccount('"
							+vendorId+ "','"+companyCode+"','"+i+"','"+payeeName+"','"+payeeAddressLine1+"','"+payeeAddressLine2+"','"+payeeAddressLine3+"','"+payeeCity+"','"+payeeState+"','"+payeeProvince+"','"+payeeCountry+"','"+payeePostalCode+"');\" value=\""
							+ getLocalizeSelectButton() + "\"/>]]></cell>\n");
			xml.append(" </row>\n");
			i++;
		}			
				
		xml.append("</rows>\n");
		return xml.toString();	
	}
	
	/**
	 * Added by MPS Offshore Team for the implementation of Payments CR
	 * @param invoiceList
	 * @return AccountsPayableList in XML String format 
	 * @throws ParseException 
	 */
	//changed by shilpa CI7-13.10.06 for sorting and searching of AP and AR
	public String convertInvoiceAPListToXML(List<Invoice> invoiceList,boolean isAR, int totalCount, String fromDateFilter, String toDateFilter) throws ParseException {
		
		//added for Pagination LEX:AIR00077257
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat localeSdf = new SimpleDateFormat(DateLocalizationUtil.getDateFormatByLanguage(locale.getLanguage()));
		
		
		Invoice invoicePagi = null;
		int listSizePagi = invoiceList.size();
		int actualCountPagi = 0;
		
		
		
		for (int i = 0;i < listSizePagi;i++) 
		{	
			invoicePagi = invoiceList.get(i);
			//String invoiceNoPagi = invoicePagi.getInvoiceNumber() != null ? XMLEncodeUtil.escapeXML(invoicePagi.getInvoiceNumber()) : "";
			Date invoiceDatePagi = invoicePagi.getInvoiceDate();
			String invoiceDateToBeShownPagi = sdf.format(invoiceDatePagi);
			Date invoiceDateToBeShownPagiFinal = sdf.parse(invoiceDateToBeShownPagi);
			
			
			
			Date paidDatePagi = invoicePagi.getPaidDate();
			String paidDateToBeShownPagi = null;
			Date paidDateToBeShownPagiFinal = null;
			
			if(paidDatePagi!=null && !"".equals(paidDatePagi)){
				paidDateToBeShownPagi = sdf.format(paidDatePagi);
				paidDateToBeShownPagiFinal = sdf.parse(paidDateToBeShownPagi);
			}
			
			
			
			//FROM FILTER : PARTHA
			String fromDateValuePagi = fromDateFilter;
			String fromDatePagi="";
			if(fromDateValuePagi!=null && !"".equals(fromDateValuePagi)){
				
				fromDatePagi = sdf.format(localeSdf.parse(fromDateValuePagi));
				
			}else{
				
				Calendar currentDate = Calendar.getInstance();
				currentDate.add(Calendar.MONTH, -6);
				
				fromDateValuePagi = sdf.format(currentDate.getTime());
				
				fromDatePagi = sdf.format(sdf.parse(fromDateValuePagi));
				
			}
			Date fromDatePagiFinal = sdf.parse(fromDatePagi);
			
			
			
			//TO FILTER : PARTHA
			String toDateValuePagi = toDateFilter;
			String toDatePagi = null;
			if(toDateValuePagi!=null && !"".equals(toDateValuePagi)){
				toDatePagi = sdf.format(localeSdf.parse(toDateValuePagi));
			}else{
				toDateValuePagi = sdf.format(new Date());
				toDatePagi = sdf.format(sdf.parse(toDateValuePagi));
			}
			Date toDateFinalPagi = sdf.parse(toDatePagi);
			
			
			
			if(!isAR){
				if(paidDateToBeShownPagiFinal ==  null){
					actualCountPagi++;
				}else{
					LOGGER.debug("Inside else---->>>"+paidDateToBeShownPagiFinal);
					if((fromDatePagiFinal.before(paidDateToBeShownPagiFinal) || fromDatePagiFinal.equals(paidDateToBeShownPagiFinal)) && (paidDateToBeShownPagiFinal.before(toDateFinalPagi) || paidDateToBeShownPagiFinal.equals(toDateFinalPagi))){
						actualCountPagi++;
					}
				}
			}else{
				if((fromDatePagiFinal.before(invoiceDateToBeShownPagiFinal) || fromDatePagiFinal.equals(invoiceDateToBeShownPagiFinal)) && (invoiceDateToBeShownPagiFinal.before(toDateFinalPagi) || invoiceDateToBeShownPagiFinal.equals(toDateFinalPagi))){
					actualCountPagi++;
				}
			}
			
			
		}
		totalCount = actualCountPagi;
		
		
		
		//ended here
		StringBuffer xml=null;
		try{
		LOGGER.debug("XmlOutputGenerator.convertInvoiceAPListToXML--Enter:");
		LOGGER.debug("INSIDE------>>>>");
		
		if(invoiceList == null || invoiceList.size() ==0 && totalCount == 0){
			return "<?xml version=\"1.0\" ?><rows></rows>";
		} 
		xml = new StringBuffer("<?xml version=\"1.0\" ?>\n");
		
		if(invoiceList != null && invoiceList.size()>0 && totalCount != 0 )
		{
			LOGGER.debug("inside Invoice List AR/AP");
			String currency=invoiceList.get(0).getCurrencyType()!=null?invoiceList.get(0).getCurrencyType():"USD";
			xml.append("<rows total_count=\"" + totalCount + "\" pos=\"0"+ "\">\n");
			if(!isAR)
			{
			//changed by shilpa CI7-13.10.06 for sorting and searching of Ap and Ar	
			xml.append("<head>"+
			"<column width=\"110\" type=\"ro\"  sort=\"str\">").append(localizeMessage("partnerPayments.label.invoiceNo", null)).append("</column>" +
			"<column width=\"120\" type=\"ro\" sort=\"date\">").append(localizeMessage("partnerPayments.label.invoiceDate", null)).append("</column>" +
			"<column width=\"110\" type=\"ro\" sort=\"date\">").append(localizeMessage("partnerPayments.label.dueDate", null)).append("</column>" +
					"<column width=\"190\" type=\"ro\" sort=\"str\">").append(localizeMessage("partnerPayments.label.paymentNo", null)).append("</column>" +
					"<column width=\"110\" type=\"ro\" sort=\"date\">").append(localizeMessage("partnerPayments.label.paidDate", null)).append("</column>" +
					"<column width=\"120\" type=\"ro\"  sort=\"int\">").append(localizeMessage("partnerPayments.label.amount", null)).append(" ").append(currency).append("</column>").append ("<afterInit>").append("<call command=\"attachHeader\"><param >").append("#text_filter,,,#text_filter,,#text_filter").append("</param></call></afterInit></head>");
			}else{
				xml.append("<head><column width=\"100\" type=\"ro\" sort=\"str\">").append(localizeMessage("partnerPayments.label.invoiceNo", null)).append("</column>" +
						"<column width=\"*\" type=\"ro\" sort=\"date\">").append(localizeMessage("partnerPayments.label.invoiceDate", null)).append("</column>" +
						"<column width=\"100\" type=\"ro\"  sort=\"int\" align=\"right\">").append(localizeMessage("partnerPayments.label.amount", null)).append(" ").append(currency).append("</column>").append ("<afterInit>").append("<call command=\"attachHeader\"><param >").append("#text_filter,,#text_filter").append("</param></call></afterInit></head>");	//changed by shilpa CI7-13.10.06 for sorting and searching of AP
			}
			//logger.info(xml.toString());
			Invoice invoice = null;
			int listSize = invoiceList.size();
			
			for (int i = 0;i < listSize;i++) {	
				
				invoice = invoiceList.get(i);
				String invoiceNo = invoice.getInvoiceNumber() != null ? XMLEncodeUtil.escapeXML(invoice.getInvoiceNumber()) : "";
				
				//Invoice Date
				String invoiceDate = invoice.getInvoiceDate() != null ? DateLocalizationUtil.localizeDateTime(invoice.getInvoiceDate(), false, locale) : "";
				Date invoiceDateDummy = invoice.getInvoiceDate();
				String invoiceDateToBeShown = sdf.format(invoiceDateDummy);	
				Date invoiceDateToBeShownFinal = sdf.parse(invoiceDateToBeShown);
				
				
				//Paid date will be used for filter as per #9871
				Date paidDateDummy = invoice.getPaidDate();
				String paidDateToBeShown = null;	
				Date paidDateToBeShownFinal = null;
				if(paidDateDummy!=null && !"".equals(paidDateDummy)){
					paidDateToBeShown = sdf.format(paidDateDummy);	
					paidDateToBeShownFinal = sdf.parse(paidDateToBeShown);
				}
				
				
				
				//FROM DATE FILTER : PARTHA
				String fromDateValue = fromDateFilter;
				
				String fromDate="";
				if(fromDateValue!=null && !"".equals(fromDateValue)){
					
					fromDate = sdf.format(localeSdf.parse(fromDateValue));
					
				}else{
					
					Calendar currentDate = Calendar.getInstance();
					currentDate.add(Calendar.MONTH, -6);
					fromDateValue = sdf.format(currentDate.getTime());
					
					fromDate = sdf.format(sdf.parse(fromDateValue));
					
				}
				Date fromDateFinal = sdf.parse(fromDate);
				
				//TO DATE FILTER : PARTHA
				String toDateValue = toDateFilter;
				String toDate = null;
				if(toDateValue!=null && !"".equals(toDateValue)){
					toDate = sdf.format(localeSdf.parse(toDateValue));
				}else{
					toDateValue = sdf.format(new Date());
					toDate = sdf.format(sdf.parse(toDateValue));
				}
				Date toDateFinal = sdf.parse(toDate);
					
				if (invoiceNo == ""){
					continue;
				}
				
				if(isAR){
					
					
					
					if((fromDateFinal.before(invoiceDateToBeShownFinal) || fromDateFinal.equals(invoiceDateToBeShownFinal)) && (invoiceDateToBeShownFinal.before(toDateFinal) || invoiceDateToBeShownFinal.equals(toDateFinal))){
						
						xml.append(" <row id=\"" + (invoiceNo)+i + "\">\n");
						xml.append("  <cell><![CDATA[<A href=\"#\" onclick=\"showInvoiceRequestDetail('"+invoiceNo+"','"+invoiceDate+
								"','"+ invoice.getAmount()+" ("+currency +")','"+currency+"')\">"+invoiceNo+"</A>]]></cell>\n");
			
						xml.append("  <cell><![CDATA[" + invoiceDate + "]]></cell>\n");
						xml.append("  <cell><![CDATA[" + invoice.getAmount() + "]]></cell>\n");
						xml.append(" </row>\n");
					}
				}// END of AR
				else{
					
					boolean flag = false;
					if(paidDateToBeShownFinal ==  null){
						flag = true;
					}else{
						if((fromDateFinal.before(paidDateToBeShownFinal) || fromDateFinal.equals(paidDateToBeShownFinal)) && (paidDateToBeShownFinal.before(toDateFinal) || paidDateToBeShownFinal.equals(toDateFinal))){
							flag = true;
						}
					}
					if(flag){
						String dueDate = invoice.getDueDate() != null ? DateLocalizationUtil.localizeDateTime(invoice.getDueDate(), false, locale) : "";				
						String paidDate = invoice.getPaidDate() != null ? DateLocalizationUtil.localizeDateTime(invoice.getPaidDate(), false, locale) : "";
						String chequeNo = invoice.getChequeNumber() != null ? XMLEncodeUtil.escapeXML(invoice.getChequeNumber()) : "";
						
						xml.append(" <row id=\"" + (invoiceNo)+i + "\">\n");
						xml.append("  <cell><![CDATA[<A href=\"#\" onclick=\"showInvoiceRequestDetail('"+invoiceNo+"','"+invoiceDate+ "','" + dueDate + "','" + paidDate + 
								"','" + chequeNo + "','"+invoice.getAmount()+" ("+currency+")','"+currency+"')\">"+invoiceNo+"</A>]]></cell>\n");
				
						xml.append("  <cell><![CDATA[" + invoiceDate + "]]></cell>\n");	
						
						xml.append("  <cell><![CDATA[" + dueDate + "]]></cell>\n");
						xml.append("  <cell><![CDATA[" + chequeNo + "]]></cell>\n");
						xml.append("  <cell><![CDATA[" + paidDate + "]]></cell>\n");
						xml.append("  <cell><![CDATA[" + invoice.getAmount() + "]]></cell>\n");
						xml.append(" </row>\n");
					}								
				}// End of AP		
				
				} //
			}
			xml.append("</rows>\n");
		
			LOGGER.debug("XmlOutputGenerator.convertInvoiceAPListToXML--Exit:");
		
		}catch(Exception ex)		{
			//ex.printStackTrace();
			LOGGER.error("error occured");
		}

		
		
		
		
		return xml.toString();
	}
		
	/** Added By MPS Offshore Team for Editing Activity Notes List **/
	public String convertNoteListToXMLForEdit(List<ActivityNote> activityNoteList, String currentContactId) {
		if(activityNoteList == null || activityNoteList.size() ==0){
			return "<?xml version=\\\"1.0\\\" ?><rows></rows>";
		} 
		String labelNote = localizeMessage("claim.label.note", null);
		String  buttonEdit = localizeMessage("button.edit",null);
		int listSize = activityNoteList.size();
		GridXmlBuilder gridXmlBuilder = new GridXmlBuilder(listSize);
		String cellValue = null;
		ActivityNote activityNote = null;
		StringBuilder strBulder = null;
		
		for(int i = 0;i < listSize;i++){
			gridXmlBuilder.nextRow(String.valueOf(i));
			activityNote = activityNoteList.get(i);
			
			String detail = activityNote.getNoteDetails()==null?"":activityNote.getNoteDetails();
			detail = detail.replaceAll("\r", "");
			detail = detail.replaceAll("\n", "\\\\n");
			String shortDetail = activityNote.getNoteDetails()==null?"":activityNote.getNoteDetails();
			shortDetail = shortDetail.replaceAll("\r", "");
			shortDetail = shortDetail.replaceAll("\n", " ");
			String subRow =  activityNote.getNoteDetails()==null?"":activityNote.getNoteDetails().replaceAll("\r", "");
			subRow = subRow.replaceAll("\n", "<br>");
			subRow = subRow.replaceAll(" ", "&nbsp;");
			
			strBulder = new StringBuilder();
			strBulder.append("<strong>").append(labelNote).append("</strong><br><div  style=\"word-wrap: break-word;width:80%;\">")
					.append(subRow).append("</div>");
			cellValue = strBulder.toString();
			gridXmlBuilder.addCells(cellValue);
			cellValue = DateLocalizationUtil.localizeDateTime(activityNote.getNoteDate(), false, locale);
			gridXmlBuilder.addCells(cellValue);
			cellValue = activityNote.getNoteAuthor().getFirstName() + " " + activityNote.getNoteAuthor().getLastName();
			gridXmlBuilder.addCells(cellValue);
			cellValue = shortDetail;
			gridXmlBuilder.addCells(StringUtil.appendEllipsis(cellValue, 50));
			
			strBulder = new StringBuilder();
			strBulder.append("<input id=\"").append(i).append("_noteDetail\" type=\"hidden\" value=\"")
				.append(XMLEncodeUtil.escapeXML(detail)).append("\">");
			
			strBulder.append("<input id=\"").append(i).append("_contactId\" type=\"hidden\" value=\"")
				.append(activityNote.getNoteAuthor().getContactId()).append("\">");
			
			strBulder.append("<input id=\"").append(i).append("_noteId\" type=\"hidden\" value=\"")
				.append(activityNote.getNoteId()).append("\">");
			
			/*for note edit test*/
			//String contactID = "123";
			//try{
				//if (activityNote.getNoteAuthor().getContactId().equals(currentContactId)) {
				//if (contactID.equals("123")) {
					strBulder.append("<div class=\"buttonContainerIn center\"><button class=\"button\" onclick=\"editRow('")
						.append(i).append("')\">").append(buttonEdit).append("</button>");
				//}
			/*}catch(Exception e){
				throw new RuntimeException("the Notes Author Contact id is null");
			}*/
			cellValue = strBulder.toString();
			gridXmlBuilder.addCells(cellValue);
		}
		
		gridXmlBuilder.finish();
		String contentXML = gridXmlBuilder.getGridXmlString();
		return contentXML;
	}
	
	public String convertPartListToXML(List<Part> partList){
		StringBuilder xml = new StringBuilder("<?xml version=\"1.0\" ?>\n");
		xml.append("<rows>\n");
		if(partList!=null){
			for (Part part : partList) {
				String partId = part.getPartId()==null?"":part.getPartId();
				String replacementPartNumber = part.getReplacementPartNumber()==null?"":part.getReplacementPartNumber();
				if(StringUtils.isEmpty(partId)){
					continue;
				}
				xml.append("<row id=\""+(partId)+"\">\n");
				xml.append("	<cell><![CDATA[" + XMLEncodeUtil.escapeXML(part.getPartNumber()) + "]]></cell>\n");
				xml.append("	<cell><![CDATA[" + XMLEncodeUtil.escapeXML(part.getPartName()) +" ]]></cell>\n");
				xml.append("	<cell><![CDATA[" + part.isReturnRequiredFlag() + "]]></cell>\n");
				xml.append("    <cell><![CDATA[ <input id=\"btn_select\" name=\"btn_select\" class=\"button\"  type=\"button\" value=\""+localizeMessage("button.select", null)+"\" onclick=\"selectRow('" +partId + "',this)\"/> <input id=\"partReplaceNumber_"+partId+"\" type=\"hidden\" value=\""+replacementPartNumber+"\"/>]]></cell>\n");
				xml.append(" </row>\n");
			}	
		}		
		xml.append("</rows>\n");
		LOGGER.debug("convertPartListToXML.xml--->"+xml.toString());
		return xml.toString();
	}
	
	/**
	 * 
	 * @param orderPartList
	 * @return
	 */
	public String convertOrderPartListToXML(List<PartLineItem> orderPartList) {
		LOGGER.debug("in convertOrderPartListToXML");
		if(orderPartList == null || orderPartList.size() ==0){
			return "<?xml version=\\\"1.0\\\" ?><rows></rows>";
		} 
		String labelShipToAdress = localizeMessage("claim.label.shipToAdress", null);
		int listSize = orderPartList.size();
		LOGGER.debug("List Size = " + listSize);
		GridXmlBuilder gridXmlBuilder = new GridXmlBuilder(listSize);
		LOGGER.debug("GridXmlBuilder = " + gridXmlBuilder);
		String cellValue = null;
		PartLineItem partLineItem = null;
		StringBuilder strBulder = null;
		String carrierValue = null;
		String provinceOrState = null;
		try{
		for(int i = 0;i < listSize;i++){
			LOGGER.debug("Inside FOR lopp 00 = " + orderPartList.size());
			gridXmlBuilder.nextRow(String.valueOf(i));
			partLineItem = orderPartList.get(i);
			LOGGER.debug("Inside FOR lopp 11 = " + partLineItem);
			strBulder = new StringBuilder();
			if(partLineItem.getShipToAdress() != null && !"".equals(partLineItem.getShipToAdress())){
			GenericAddress genericAddress = partLineItem.getShipToAdress();
			LOGGER.debug("GenericAddress = " + genericAddress);
			if (genericAddress.getProvince() != null && genericAddress.getProvince().length() > 0) {
				provinceOrState = genericAddress.getProvince();
				LOGGER.debug("genericAddress.getProvince() not null = " + provinceOrState);
			} else {
				provinceOrState = genericAddress.getState();
				LOGGER.debug("genericAddress.getProvince() null = " + provinceOrState);
			}
			
			strBulder.append("<table id=\"shipToAddr123\">");
			strBulder.append("<tr>");
			strBulder.append("<td valign=\"top\" style=\"padding-left:10px;\"><B>").append(labelShipToAdress).append("</B></td>");
			strBulder.append("<td valign=\"top\" style=\"padding-left:10px;\" id=\"existingAddrLine1").append(i).append("\">");
			if(genericAddress.getAddressLine1() != null && !"".equals(genericAddress.getAddressLine1())){
				LOGGER.debug("genericAddress.getAddressLine1() not null = " + genericAddress.getAddressLine1());
			strBulder.append(XMLEncodeUtil.escapeXML(genericAddress.getAddressLine1())).append("<br/>");
			}
			strBulder.append("</td>");
			strBulder.append("</tr>");
			strBulder.append("<tr>");
			strBulder.append("<td valign=\"top\" style=\"padding-left:10px;\"><B>").append("</B></td>");
			strBulder.append("<td valign=\"top\" style=\"padding-left:10px;\" id=\"existingAddrLine2").append(i).append("\">");
			if(genericAddress.getAddressLine2() != null && !"".equals(genericAddress.getAddressLine2())){
				LOGGER.debug("genericAddress.getAddressLine2() not null = " + genericAddress.getAddressLine2());
				strBulder.append(XMLEncodeUtil.escapeXML(genericAddress.getAddressLine2())).append("<br/>");				
			}
			strBulder.append("</td>");
			strBulder.append("</tr>");
			strBulder.append("<tr>");
			strBulder.append("<td valign=\"top\" style=\"padding-left:10px;\"><B>").append("</B></td>");
			strBulder.append("<td valign=\"top\" style=\"padding-left:10px;\" id=\"existingAddrLine3").append(i).append("\">");
			if(genericAddress.getAddressLine3() != null && !"".equals(genericAddress.getAddressLine3())){
				LOGGER.debug("genericAddress.getAddressLine3() not null = " + genericAddress.getAddressLine3());
				strBulder.append(XMLEncodeUtil.escapeXML(genericAddress.getAddressLine3())).append("<br/>");				
			}
			strBulder.append("</td>");
			strBulder.append("</tr>");
			strBulder.append("<tr>");
			strBulder.append("<td valign=\"top\" style=\"padding-left:10px;\"><B>").append("</B></td>");
			//StringBuilder sb = new StringBuilder("");
			strBulder.append("<td valign=\"top\" style=\"padding-left:10px;\">");
			strBulder.append("<span id=\"existingCity").append(i).append("\">");
			if(genericAddress.getCity() != null && !"".equalsIgnoreCase(genericAddress.getCity().trim())){	
				LOGGER.debug("genericAddress.getCity() not null = " + genericAddress.getCity());
				strBulder.append(XMLEncodeUtil.escapeXML(genericAddress.getCity()));	
				//LOGGER.debug("city="+XMLEncodeUtil.escapeXML(genericAddress.getCity()));
			}
			strBulder.append("</span>");
			strBulder.append("<span id=\"existingProvince").append(i).append("\">");
			if(provinceOrState != null && !"".equalsIgnoreCase(provinceOrState.trim())){
				LOGGER.debug("provinceOrState not null = " + provinceOrState);
				if(genericAddress.getCity() != null && !"".equalsIgnoreCase(genericAddress.getCity().trim())){
					LOGGER.debug("genericAddress.getCity() not null 2 = " + genericAddress.getCity());
					strBulder.append(",&nbsp;");
				}
				strBulder.append(XMLEncodeUtil.escapeXML(provinceOrState));
				LOGGER.debug("provinceOrState null = " + provinceOrState);
			}
			strBulder.append("</span>");
			strBulder.append("<span id=\"existingCountry").append(i).append("\">");
			if(genericAddress.getCountry() != null && !"".equalsIgnoreCase(genericAddress.getCountry().trim())){
				LOGGER.debug("genericAddress.getCountry() not null = " + genericAddress.getCountry());
				if((genericAddress.getCity() != null && !"".equalsIgnoreCase(genericAddress.getCity().trim()))
						||(provinceOrState != null && !"".equalsIgnoreCase(provinceOrState.trim()))){
					LOGGER.debug("genericAddress.getCity() not null 3 = " + genericAddress.getCity());
					LOGGER.debug("provinceOrState not null 2 = " + provinceOrState);
					strBulder.append(",&nbsp;");
				}
				LOGGER.debug("genericAddress.getCountry() null = " + genericAddress.getCountry());
				strBulder.append(XMLEncodeUtil.escapeXML(genericAddress.getCountry()));
				//LOGGER.debug("getCountry="+XMLEncodeUtil.escapeXML(genericAddress.getCountry()));
			}
			strBulder.append("</span>");
			strBulder.append("<span id=\"existingPostalCode").append(i).append("\">");
			if(genericAddress.getPostalCode() != null && !"".equalsIgnoreCase(genericAddress.getPostalCode().trim())){
				LOGGER.debug("genericAddress.getPostalCode() not null = " + genericAddress.getPostalCode());
				if((genericAddress.getCity() != null && !"".equalsIgnoreCase(genericAddress.getCity().trim()))
						||(provinceOrState != null && !"".equalsIgnoreCase(provinceOrState.trim()))
						||(genericAddress.getCountry() != null && !"".equalsIgnoreCase(genericAddress.getCountry().trim()))){
					LOGGER.debug("genericAddress.getCity() not null 4 = " + genericAddress.getCity());
					LOGGER.debug("provinceOrState not null 3 = " + provinceOrState);
					LOGGER.debug("genericAddress.getCountry() not null 2 = " + genericAddress.getCountry());
					strBulder.append(",&nbsp;");
				}
				LOGGER.debug("genericAddress.getPostalCode() null = " + genericAddress.getPostalCode());
				strBulder.append(XMLEncodeUtil.escapeXML(genericAddress.getPostalCode()));	
				//LOGGER.debug("getPostalCode="+XMLEncodeUtil.escapeXML(genericAddress.getPostalCode()));
			}
			strBulder.append("</span>");
			strBulder.append("</td>");
			
			
			
		//	strBulder.append(XMLEncodeUtil.escapeXML(genericAddress.getCity())).append(",&nbsp;").append(XMLEncodeUtil.escapeXML(provinceOrState)).append(",&nbsp;").append(XMLEncodeUtil.escapeXML(genericAddress.getCountry())).append(",&nbsp;").append(genericAddress.getPostalCode()).append("</td>");
			
			//strBulder.append(sb);
			strBulder.append("</tr>");
			strBulder.append("</table>");
			cellValue = strBulder.toString();
			gridXmlBuilder.addCells(cellValue);
			if(partLineItem.getPartOrderedDate() != null && !"".equals(partLineItem.getPartOrderedDate())){
				LOGGER.debug("partLineItem.getPartOrderedDate() not null = " + partLineItem.getPartOrderedDate());
			cellValue = DateLocalizationUtil.localizeDateTime(partLineItem.getPartOrderedDate(), false, locale);
			LOGGER.debug("cellValue 11 = " + cellValue);
			}else{
				cellValue="";
			}
			gridXmlBuilder.addCells(cellValue);
			
			cellValue = String.valueOf(partLineItem.getQuantity());
			LOGGER.debug("cellValue 22 = " + partLineItem.getQuantity());
			
			gridXmlBuilder.addCells(cellValue);
			//CI-6 Changes Start
			String flagCellValuee = partLineItem.getExpedite();
			LOGGER.debug("flagCellValuee = " + flagCellValuee);
				if(flagCellValuee != null && ("Y".equals(flagCellValuee.toUpperCase()) || "YES".equals(flagCellValuee.toUpperCase()))){
					LOGGER.debug("flagCellValuee not null = " + flagCellValuee);
					gridXmlBuilder.addCells("Yes");
				}else{
					LOGGER.debug("flagCellValuee null = " + flagCellValuee);
					gridXmlBuilder.addCells("No");
				}
			//CI-6 Changes End
				if(partLineItem.getPartNumber() != null && !"".equals(partLineItem.getPartNumber())){
					LOGGER.debug("partLineItem.getPartNumber() not null = " + partLineItem.getPartNumber());
			cellValue = XMLEncodeUtil.escapeXML(partLineItem.getPartNumber());
			LOGGER.debug("cellValue 33 = " + cellValue);
			
				}else{
					cellValue="";
				}
			
			gridXmlBuilder.addCells(cellValue);
			
			if(partLineItem.getPartName() != null && !"".equals(partLineItem.getPartName())){
					LOGGER.debug("partLineItem.getPartName() not null = " + partLineItem.getPartName());
			cellValue = XMLEncodeUtil.escapeXML(partLineItem.getPartName());
			LOGGER.debug("cellValue 44 = " + cellValue);
			
				}else{
					cellValue="";
				}
			gridXmlBuilder.addCells(cellValue);
			
			//Changes for CI7 14-07-06 START
			if((partLineItem.getPartStatus())!=null && !"".equals(partLineItem.getPartStatus().toString().trim()))
			{
				LOGGER.debug("partLineItem.getPartStatus() not null = " + partLineItem.getPartStatus());
				cellValue = XMLEncodeUtil.escapeXML(partLineItem.getPartStatus().getValue());
				LOGGER.debug("cellValue 55 = " + cellValue);
			}
			else
			{
				LOGGER.debug("partLineItem.getPartStatus() null = " + partLineItem.getPartStatus());
				cellValue = XMLEncodeUtil.escapeXML("");
				LOGGER.debug("cellValue 66 = " + cellValue);
			}
			
			gridXmlBuilder.addCells(cellValue);
			//Changes for CI7 14-07-06 END
			
			if(partLineItem.getLineStatus().getName() != null && !"".equals(partLineItem.getLineStatus().getName())){
				LOGGER.debug("partLineItem.getLineStatus().getName() not null = " + partLineItem.getLineStatus().getName());
				cellValue = partLineItem.getLineStatus().getName();
				LOGGER.debug("cellValue 77 = " + cellValue);
				//ToDo Localization
				
			}else{
				cellValue="";
			}
			gridXmlBuilder.addCells(cellValue);
			/*cellValue = partLineItem.getStatus();
			if(cellValue == null){
				cellValue = "";
			}
			gridXmlBuilder.addCells(cellValue);*/
			if(partLineItem.getSerialNumber() != null && !"".equals(partLineItem.getSerialNumber())){
				LOGGER.debug("partLineItem.getSerialNumber() not null = " + partLineItem.getSerialNumber());
			cellValue = partLineItem.getSerialNumber();
			
			
			}else{
				
				cellValue="";
			}
			LOGGER.debug("cellValue 88 = " + cellValue);
			gridXmlBuilder.addCells(cellValue);
			
			try{
				LOGGER.debug("entering into ship date");
				LOGGER.debug("going to ship date::::::::::"+partLineItem.getShipDate()+partLineItem.getShipDate().toString());
			if (partLineItem.getShipDate().toString().trim() != null && !"".equals(partLineItem.getShipDate().toString().trim())){
				LOGGER.debug("partLineItem.getShipDate() not null = " + partLineItem.getShipDate());
				String shipdate = partLineItem.getShipDate().toString().trim();
				LOGGER.debug("shipdate 11 = " + shipdate);
				shipdate = DateLocalizationUtil.localizeDateTimeObject(partLineItem.getShipDate(), true, locale);
				LOGGER.debug("shipdate 22 = " + shipdate);
				StringTokenizer shipDateToken = new StringTokenizer(shipdate, ",");
				String formattedShipDate = "";
				while(shipDateToken.hasMoreElements())
				{	
					 formattedShipDate=formattedShipDate+DateLocalizationUtil.localizeDateTime(DateUtil.getStringToLocalizedDate(shipDateToken.nextElement().toString(),true,locale), false, locale);
					 LOGGER.debug("formattedShipDate = " + formattedShipDate);
					 formattedShipDate = formattedShipDate.concat(",");	
				}
				if(formattedShipDate.contains(",")){
				cellValue = formattedShipDate.substring(0, formattedShipDate.lastIndexOf(","));
				LOGGER.debug("cellValue 99 = " + cellValue);
				}
				else{
					cellValue="";
					LOGGER.debug("cellValue 100 = " + cellValue);
				}
				
			}else{
				LOGGER.debug("partLineItem.getShipDate() null = " + partLineItem.getShipDate());
				cellValue = "";
			}
			
			
			}catch(Exception e){
				cellValue="";
				LOGGER.debug("in shipdate exception catch");
			}
			
			gridXmlBuilder.addCells(cellValue);
			LOGGER.debug("cellValue 111 = " + cellValue);
			
			if(null != partLineItem.getCarrier() && !"".equals(partLineItem.getCarrier().toString().trim())){
				LOGGER.debug("partLineItem.getCarrier() not null = " + partLineItem.getCarrier());
			carrierValue = XMLEncodeUtil.escapeXML(partLineItem.getCarrier().getName());
			LOGGER.debug("carrierValue = " + carrierValue);
			}
			else
			{
				carrierValue = "";
				LOGGER.debug("carrierValue 11 = " + carrierValue);
			}
			gridXmlBuilder.addCells(carrierValue);
			if(partLineItem.getTrackingNumber() != null && !"".equals(partLineItem.getTrackingNumber().toString().trim())){
				LOGGER.debug("partLineItem.getTrackingNumber() not null = " + partLineItem.getTrackingNumber());
			cellValue = partLineItem.getTrackingNumber();
			LOGGER.debug("cellValue 122 = " + cellValue);
			
			if(partLineItem.getCarrier() != null && !"".equals(partLineItem.getCarrier().toString().trim())){
				LOGGER.debug("partLineItem.getCarrier() not null = " + partLineItem.getCarrier());
			if(LexmarkConstants.CARRIER_LIST.contains(partLineItem.getCarrier().getValue())){
				strBulder = new StringBuilder();
				strBulder.append("<a href=\"###\" onClick=\"onTrackingNumberClick('").append(carrierValue)
				.append("','").append(partLineItem.getTrackingNumber()).append("')\">").append(partLineItem.getTrackingNumber()).append("</a>");
				cellValue = strBulder.toString();	
			}
			}
			
			}else{
				cellValue="";
			}
			gridXmlBuilder.addCells(cellValue);
			String cellValueFlag = "";
			if((genericAddress.getAddressLine1()!=null && genericAddress.getAddressLine1().trim()!="") && (genericAddress.getCity()!=null && genericAddress.getCity().trim()!="")){
				LOGGER.debug("in if");
				cellValueFlag="false";				
			}
			else{
				LOGGER.debug("in else");
				cellValueFlag="true";				
			}
			LOGGER.debug("cellValueFlag boolean "+cellValueFlag);
			gridXmlBuilder.addCells(cellValueFlag);
			LOGGER.debug("cellValueFlag set "+cellValueFlag);
			
		}
		}
		}
		catch(Exception e){
			//e.printStackTrace();
			LOGGER.debug("Catch Exception-------");
		}
		gridXmlBuilder.finish();
		String contentXML = gridXmlBuilder.getGridXmlString();
		LOGGER.debug("convertOrderPartListToXML.contentXML--->"+contentXML);
		return contentXML;
	}
	
	public String convertUpdateReturnPartListToXML(List<PartLineItem> returnPartList,Map<String, String> carrierDropDown) {
		return convertUpdateReturnPartListToXML(returnPartList,true,carrierDropDown);
	}
	
	/**
	 * 
	 * @param returnPartList
	 * @return
	 */
	public String convertUpdateReturnPartListToXML(List<PartLineItem> returnPartList, boolean subRow,Map<String, String> carrierDropDown) {
		if (returnPartList == null || returnPartList.size() == 0) {
			return "<?xml version=\\\"1.0\\\" ?><rows></rows>";
		}
		int listSize = returnPartList.size();
		GridXmlBuilder gridXmlBuilder = new GridXmlBuilder(listSize);
		for (int i = 0; i < listSize; i++) {
			gridXmlBuilder.nextRow(String.valueOf(i));
			PartLineItem partLineItem = returnPartList.get(i);
			if (subRow) {
				GenericAddress address = partLineItem.getShipToAdress();
				final StringBuilder subRowContent = new StringBuilder();
				subRowContent.append("<div><table>");
				subRowContent.append("<tr><td align=\"right\"><B>");
				subRowContent.append(localizeMessage("claim.label.returnTo", null));
				subRowContent.append("&nbsp;</B></td><td>");
				subRowContent.append(XMLEncodeUtil.escapeXML(address.getAddressName()));
				subRowContent.append("</td></tr>");
				subRowContent.append("<tr><td Valign=\"top\" align=\"right\"><B>");
				subRowContent.append(localizeMessage("claim.label.address", null));
				subRowContent.append("&nbsp;</B></td><td Valign=\"top\">");
				subRowContent.append(XMLEncodeUtil.escapeXML(address.getAddressLine1())).append("<br/>");
				if(address.getAddressLine2() != null && !"".equals(address.getAddressLine2())){
					subRowContent.append(XMLEncodeUtil.escapeXML(address.getAddressLine2())).append("<br/>");
				}
				if(address.getAddressLine3() != null && !"".equals(address.getAddressLine3())){
					subRowContent.append(XMLEncodeUtil.escapeXML(address.getAddressLine3())).append("<br/>");
				}
				subRowContent.append(XMLEncodeUtil.escapeXML(address.getCity())).append(",");
				if (address.getState() != null) {
					subRowContent.append(XMLEncodeUtil.escapeXML(address.getState()));
				} else {
					subRowContent.append(XMLEncodeUtil.escapeXML(address.getProvince()));
				}
				subRowContent.append(",&nbsp;").append(XMLEncodeUtil.escapeXML(address.getCountry())).append(",&nbsp;").append(address.getPostalCode());
				subRowContent.append("</td><tr>");
				subRowContent.append("</table></div>");
				gridXmlBuilder.addCells(subRowContent.toString());
			}

			gridXmlBuilder.addCells(String.valueOf(partLineItem.getQuantity()));
			gridXmlBuilder.addCells(XMLEncodeUtil.escapeXML(partLineItem.getPartNumber()));
			gridXmlBuilder.addCells(XMLEncodeUtil.escapeXML(partLineItem.getPartName()));
			// ToDo Localization
			gridXmlBuilder.addCells(partLineItem.getLineStatus().getName());
			//TODO for change the carrier status like return and shipped and in process
			if(!LineStatusEnum.RETURNED.getValue().equalsIgnoreCase(partLineItem.getLineStatus().getValue())){// not Returned
				gridXmlBuilder.addCells("");//SeriaNumber
				gridXmlBuilder.addCells("");//Date
				StringBuilder strBuilder = new StringBuilder();
				strBuilder.append("<table><tr><td><select id=\"carrier_"+i+"\" name=\"claimDetail.returnPartList["+i+"].carrier.value\" style=\"width:130px;\"  onchange=\"carrierOnChange("+i+");\">");
				strBuilder.append("<option value=\"\"></option>");
				for (Map.Entry<String, String> entry : carrierDropDown.entrySet()) {
					String key = entry.getKey().toString();
					String value = entry.getValue().toString();
					strBuilder.append("<option value=\""+key+"\">"+value+"</option>");
				}
				strBuilder.append("</select></td></tr></table>");// add drop down list
				gridXmlBuilder.addCells(strBuilder.toString());//Carrier 
				strBuilder = new StringBuilder();
				strBuilder.append("<table><tr><td><input type=\"text\" name=\"claimDetail.returnPartList["+i+"].trackingNumber\" id=\"trackingNumber_"+i+"\" maxlength=\"30\" onchange=\"validateLength(0,30,this);trackingNumberOnChange("+i+")\" ></input></td></tr></table>");
				strBuilder.append("<input type=\"hidden\" name=\"claimDetail.returnPartList["+i+"].partLineItemUpdateFlag\" id=\"partLineItemUpdateFlag_"+i+"\" value = \""+partLineItem.isPartLineItemUpdateFlag()+"\"  ></input>");
				strBuilder.append("<input type=\"hidden\" id=\"tackingNumberFlag_"+i+"\" value = \"false\"  ></input>");
				strBuilder.append("<input type=\"hidden\" id=\"carrierFlag_"+i+"\" value = \"false\"  ></input>");
				gridXmlBuilder.addCells(strBuilder.toString());//trackingNumber
			}else{
				gridXmlBuilder.addCells(partLineItem.getSerialNumber());
				gridXmlBuilder.addCells(DateLocalizationUtil.localizeDateTime(partLineItem.getPartReceivedDate(), false, locale));
				gridXmlBuilder.addCells(partLineItem.getCarrier().getName());
				if (LexmarkConstants.CARRIER_LIST.contains(partLineItem.getCarrier().getValue())) {
					StringBuilder strBulder = new StringBuilder();
					strBulder.append("<a href=\"###\" onClick=\"onTrackingNumberClick('").append(partLineItem.getCarrier().getName())
							.append("','").append(partLineItem.getTrackingNumber()).append("')\">").append(
									partLineItem.getTrackingNumber()).append("</a>");
					gridXmlBuilder.addCells(strBulder.toString());
				} else {
					gridXmlBuilder.addCells(partLineItem.getTrackingNumber());
				}
			}
		}
		gridXmlBuilder.finish();
		LOGGER.debug("convertUpdateReturnPartListToXML.GridXmlString--->"+gridXmlBuilder.getGridXmlString());
		return gridXmlBuilder.getGridXmlString();
	}
	
	
	
	public String convertReturnPartListToXML(List<PartLineItem> returnPartList) {
		return convertReturnPartListToXML(returnPartList, false);
	}
	
	/**
	 * 
	 * @param returnPartList
	 * @return
	 */
	public String convertReturnPartListToXML(List<PartLineItem> returnPartList, boolean subRow) {
		if (returnPartList == null || returnPartList.size() == 0) {
			return "<?xml version=\\\"1.0\\\" ?><rows></rows>";
		}
		int listSize = returnPartList.size();
		GridXmlBuilder gridXmlBuilder = new GridXmlBuilder(listSize);
		
		for (int i = 0; i < listSize; i++) {
			gridXmlBuilder.nextRow(String.valueOf(i));
			PartLineItem partLineItem = returnPartList.get(i);
			if (subRow) {
				GenericAddress address = partLineItem.getShipToAdress();
				final StringBuilder subRowContent = new StringBuilder();
				subRowContent.append("<div><table>");
				subRowContent.append("<tr><td align=\"right\"><B>");
				subRowContent.append(localizeMessage("claim.label.returnTo", null));
				subRowContent.append("&nbsp;</B></td><td>");
				subRowContent.append(XMLEncodeUtil.escapeXML(address.getAddressName()));
				subRowContent.append("</td></tr>");
				subRowContent.append("<tr><td Valign=\"top\" align=\"right\"><B>");
				subRowContent.append(localizeMessage("claim.label.address", null));
				subRowContent.append("&nbsp;</B></td><td Valign=\"top\">");
				subRowContent.append(XMLEncodeUtil.escapeXML(address.getAddressLine1())).append("<br/>");
				if(address.getAddressLine2() != null && !"".equals(address.getAddressLine2())){
					subRowContent.append(XMLEncodeUtil.escapeXML(address.getAddressLine2())).append("<br/>");
				}
				if(address.getAddressLine3() != null && !"".equals(address.getAddressLine3())){
					subRowContent.append(XMLEncodeUtil.escapeXML(address.getAddressLine3())).append("<br/>");
				}
				subRowContent.append(XMLEncodeUtil.escapeXML(address.getCity())).append(",&nbsp;");
				if (address.getState() != null) {
					subRowContent.append(XMLEncodeUtil.escapeXML(address.getState()));
				} else {
					subRowContent.append(XMLEncodeUtil.escapeXML(address.getProvince()));
				}
				subRowContent.append(",&nbsp;").append(XMLEncodeUtil.escapeXML(address.getCountry())).append(",&nbsp;").append(address.getPostalCode());
				subRowContent.append("</td><tr>");
				subRowContent.append("</table></div>");
				gridXmlBuilder.addCells(subRowContent.toString());
			}

			gridXmlBuilder.addCells(String.valueOf(partLineItem.getQuantity()));
			gridXmlBuilder.addCells(XMLEncodeUtil.escapeXML(partLineItem.getPartNumber()));
			gridXmlBuilder.addCells(XMLEncodeUtil.escapeXML(partLineItem.getPartName()));
			// ToDo Localization
			
			gridXmlBuilder.addCells(partLineItem.getLineStatus().getName());
            gridXmlBuilder.addCells(partLineItem.getSerialNumber());
			if (partLineItem.getPartReceivedDate() == null){
				gridXmlBuilder.addCells("");				
			}else{
				gridXmlBuilder.addCells(DateLocalizationUtil.localizeDateTime(partLineItem.getPartReceivedDate(), false, locale));				
			}
			if(partLineItem.getCarrier() != null)
			{
			gridXmlBuilder.addCells(partLineItem.getCarrier().getName());
			}
			else
			{
				gridXmlBuilder.addCells("");				
			}
			if(partLineItem.getCarrier() != null){
			if (LexmarkConstants.CARRIER_LIST.contains(partLineItem.getCarrier().getValue())) {
				StringBuilder strBulder = new StringBuilder();
				strBulder.append("<a href=\"###\" onClick=\"onTrackingNumberClick('").append(partLineItem.getCarrier().getName())
						.append("','").append(partLineItem.getTrackingNumber()).append("')\">").append(
								partLineItem.getTrackingNumber()).append("</a>");
				gridXmlBuilder.addCells(strBulder.toString());
			} 
			}else {
				gridXmlBuilder.addCells(partLineItem.getTrackingNumber());
			}
		}
		gridXmlBuilder.finish();
		LOGGER.debug("convertReturnPartListToXML.GridXmlString--->"+gridXmlBuilder.getGridXmlString());
		return gridXmlBuilder.getGridXmlString();
	}
	
	/**
	 * 
	 * @param thankYouReturnPartList
	 * @return
	 */
	public String convertThankYouReturnPartListToXML(List<PartLineItem> pendingPartList) {
		if(pendingPartList == null || pendingPartList.size() ==0){
			return "<?xml version=\\\"1.0\\\" ?><rows></rows>";
		} 
		int listSize = pendingPartList.size();
		GridXmlBuilder gridXmlBuilder = new GridXmlBuilder(listSize);
		PartLineItem partLineItem = null;
		for(int i = 0;i < listSize;i++){
			gridXmlBuilder.nextRow(String.valueOf(i));
			partLineItem = pendingPartList.get(i);
			gridXmlBuilder.addCells(String.valueOf(DateLocalizationUtil.localizeDateTime(partLineItem.getPartOrderedDate(), false, locale)));
			gridXmlBuilder.addCells(String.valueOf(partLineItem.getQuantity()));
			gridXmlBuilder.addCells(XMLEncodeUtil.escapeXML(partLineItem.getPartNumber()));
			gridXmlBuilder.addCells(XMLEncodeUtil.escapeXML(partLineItem.getPartName()));
			gridXmlBuilder.addCells(partLineItem.getPartDisposition().getName());
			gridXmlBuilder.addCells(partLineItem.getErrorCode1()==null?"":partLineItem.getErrorCode1().getName());
			gridXmlBuilder.addCells(partLineItem.getErrorCode2()==null?"":partLineItem.getErrorCode2().getName());
			gridXmlBuilder.addCells(partLineItem.isReturnRequiredFlag()?localizeMessage("claim.lebel.returnRequired.yes",null):localizeMessage("claim.lebel.returnRequired.no",null));
			gridXmlBuilder.addCells((partLineItem.getCarrier()==null || partLineItem.getCarrier().getName() == null)?"":partLineItem.getCarrier().getName());
			gridXmlBuilder.addCells(String.valueOf(partLineItem.getTrackingNumber()==null?"":partLineItem.getTrackingNumber()));
		}
		gridXmlBuilder.finish();
		String contentXML = gridXmlBuilder.getGridXmlString();
		return contentXML;
	}
	
	/**
	 * 
	 * @param thankYouReturnPartList
	 * @return
	 */
	public String convertThankYouReturnPartListNOToXML(List<PartLineItem> pendingPartList, String expedite) { //CI-6 Change Made
		if(pendingPartList == null || pendingPartList.size() ==0){
			return "<?xml version=\\\"1.0\\\" ?><rows></rows>";
		} 
		int listSize = pendingPartList.size();
		GridXmlBuilder gridXmlBuilder = new GridXmlBuilder(listSize);
		PartLineItem partLineItem = null;
		for(int i = 0;i < listSize;i++){
			gridXmlBuilder.nextRow(String.valueOf(i));
			partLineItem = pendingPartList.get(i);
			if(partLineItem.getPartOrderedDate() != null){
				gridXmlBuilder.addCells(String.valueOf(DateLocalizationUtil.localizeDateTime(partLineItem.getPartOrderedDate(), false, locale)));
			}
			gridXmlBuilder.addCells(partLineItem.getPartNumber()==null?"":XMLEncodeUtil.escapeXML(partLineItem.getPartNumber()));
			gridXmlBuilder.addCells(partLineItem.getPartName()==null?"":XMLEncodeUtil.escapeXML(partLineItem.getPartName()));
			gridXmlBuilder.addCells(String.valueOf(partLineItem.getQuantity()));
			//CI-6 Changes Start 
				if(expedite != null && ("Y".equals(expedite.toUpperCase()) || "YES".equals(expedite.toUpperCase()))){
					gridXmlBuilder.addCells(XMLEncodeUtil.escapeXML("Yes"));
				}else{
					gridXmlBuilder.addCells(XMLEncodeUtil.escapeXML("No"));
				}
			//CI-6 Changes End
			gridXmlBuilder.addCells(partLineItem.isReturnRequiredFlag()?localizeMessage("claim.lebel.returnRequired.yes",null):localizeMessage("claim.lebel.returnRequired.no",null));
		}
		gridXmlBuilder.finish();
		String contentXML = gridXmlBuilder.getGridXmlString();
		return contentXML;
	}
	
	public String convertTechnicianInformationListToXML(List<TechnicianInstruction> technicianInstructionList){
		if(technicianInstructionList == null || technicianInstructionList.size() ==0){
			return "<?xml version=\"1.0\" ?><rows></rows>";
		} 
		int listSize = technicianInstructionList.size();
		GridXmlBuilder gridXmlBuilder = new GridXmlBuilder(listSize);
		TechnicianInstruction technicianInstruction = null;
		for(int i = 0;i < listSize;i++){
			gridXmlBuilder.nextRow(String.valueOf(i));
			technicianInstruction = technicianInstructionList.get(i);
			gridXmlBuilder.addCells(String.valueOf(DateLocalizationUtil.localizeDateTime(technicianInstruction.getInstructionDate(), false, locale)));
			gridXmlBuilder.addCells(technicianInstruction.getInstructionType());
			gridXmlBuilder.addCells(XMLEncodeUtil.escapeXML(technicianInstruction.getActualInstruction()));
		}
		gridXmlBuilder.finish();
		String contentXML = gridXmlBuilder.getGridXmlString();
		contentXML = removeReturnControlFlag(contentXML);
		return contentXML;
	}
	
	/**
	 * 
	 * @param additionalPaymentRequestList
	 * @return
	 */
	public String convertAdditionalPaymentListToXML(List<AdditionalPaymentRequest> additionalPaymentRequestList) throws Exception { 
		if(additionalPaymentRequestList == null || additionalPaymentRequestList.size() ==0){
			return "<?xml version=\\\"1.0\\\" ?><rows></rows>";
		} 
		int listSize = additionalPaymentRequestList.size();
		GridXmlBuilder gridXmlBuilder = new GridXmlBuilder(listSize);
		AdditionalPaymentRequest addtionalPaymentRequest = null;
		for(int i = 0;i < listSize;i++){
			gridXmlBuilder.nextRow(String.valueOf(i));
			addtionalPaymentRequest = additionalPaymentRequestList.get(i);
			gridXmlBuilder.addCells(addtionalPaymentRequest.getPaymentType().getName());
			gridXmlBuilder.addCells(String.valueOf(addtionalPaymentRequest.getQuantity()));
			gridXmlBuilder.addCells(String.valueOf(addtionalPaymentRequest.getUnitPrice()));
			gridXmlBuilder.addCells(String.valueOf(addtionalPaymentRequest.getTotalAmount()));
			gridXmlBuilder.addCells(addtionalPaymentRequest.getPaymentCurrency());
			gridXmlBuilder.addCells(XMLEncodeUtil.escapeXML(addtionalPaymentRequest.getDescription()));
		}
		gridXmlBuilder.finish();
		String contentXML = gridXmlBuilder.getGridXmlString();
		return contentXML;
	}
	
	private void addCellsByStringBuilder(StringBuilder strBulder, GridXmlBuilder gridXmlBuilder, String cellValue){
		cellValue = strBulder.toString();
		strBulder.delete(0,strBulder.length());
		gridXmlBuilder.addCells(cellValue); 
	}
	/**
	 *  build the Additional Payment List which can be edit.
	 * @param additionalPaymentRequestList
	 * @return
	 */
	public String convertAdditionalPaymentListToXMLForEdit(List<AdditionalPaymentRequest> additionalPaymentRequestList, Map<String, String> paymentTypes) { 
		if(additionalPaymentRequestList == null || additionalPaymentRequestList.size() ==0){
			return "<?xml version=\\\"1.0\\\" ?><rows></rows>";
		} 
		int listSize = additionalPaymentRequestList.size();
		GridXmlBuilder gridXmlBuilder = new GridXmlBuilder(listSize);
		AdditionalPaymentRequest addtionalPaymentRequest = null;
		String cellValue = null;
		StringBuilder strBulder = new StringBuilder();
		for(int i = 0;i < listSize;i++){
			gridXmlBuilder.nextRow(String.valueOf(i));
			addtionalPaymentRequest = additionalPaymentRequestList.get(i);
			
			strBulder.append("<table><tr><td>").append(addtionalPaymentRequest.getPaymentType().getName()).append("</td></tr></table>");
			addCellsByStringBuilder(strBulder, gridXmlBuilder, cellValue);
			
			strBulder.append("<table><tr><td>").append(addtionalPaymentRequest.getQuantity()).append("</td></tr></table>");
			addCellsByStringBuilder(strBulder, gridXmlBuilder, cellValue);
			
			strBulder.append("<table><tr><td>").append(new BigDecimal(addtionalPaymentRequest.getUnitPrice()).setScale(2, BigDecimal.ROUND_HALF_UP)).append("</td></tr></table>");
			addCellsByStringBuilder(strBulder, gridXmlBuilder, cellValue);
			
			gridXmlBuilder.addCells(String.valueOf(new BigDecimal(addtionalPaymentRequest.getTotalAmount()).setScale(2, BigDecimal.ROUND_HALF_UP)));
			gridXmlBuilder.addCells(addtionalPaymentRequest.getPaymentCurrency());
			
			strBulder.append("<table><tr><td>").append(XMLEncodeUtil.escapeXML(addtionalPaymentRequest.getDescription())).append("</td></tr></table>");
			addCellsByStringBuilder(strBulder, gridXmlBuilder, cellValue);
			
			strBulder.append("<input id=\"").append(i).append("_isNew\"  type=\"hidden\"  value=\"")
				.append("true").append("\"/>");
			addCellsByStringBuilder(strBulder, gridXmlBuilder, cellValue);
		}
		gridXmlBuilder.finish();
		String contentXML = gridXmlBuilder.getGridXmlString();
		return contentXML;
	}

	
	/**
	 * 
	 * @param activityNoteList
	 * @return
	 */
	public String convertActivityNoteListToXML(List<ActivityNote> activityNoteList) {
		if(activityNoteList == null || activityNoteList.size() ==0){
			return "<?xml version=\\\"1.0\\\" ?><rows></rows>";
		} 
		String labelNote = localizeMessage("claim.label.note", null);
		int listSize = activityNoteList.size();
		GridXmlBuilder gridXmlBuilder = new GridXmlBuilder(listSize);
		String cellValue = null;
		ActivityNote activityNote = null;
		StringBuilder strBulder = null;
		for(int i = 0;i < listSize;i++){
			gridXmlBuilder.nextRow(String.valueOf(i));
			activityNote = activityNoteList.get(i);
			
			String detail = activityNote.getNoteDetails();
			detail = (detail==null?"":detail.replaceAll("\r", ""));
			detail = detail.replaceAll("\n", " ");
			String subDetail = activityNote.getNoteDetails();
			subDetail = (subDetail==null?"":subDetail.replaceAll("\r", ""));
			subDetail = subDetail.replaceAll("\n", "<br>");
			subDetail = subDetail.replaceAll(" ", "&nbsp;");
			subDetail = subDetail.replaceAll("'", "&acute;");
			strBulder = new StringBuilder();
			
			/*changes for LEX:AIR00070474 start*/
			
			/*strBulder.append("<table>").append("<tr>")
				.append("<td><B>").append(labelNote).append("</B></td></tr><tr><td>")
					.append(subDetail).append("</td></tr></table>");*/
			strBulder.append("<strong>").append(labelNote).append("</strong><br><div  style=\"word-wrap: break-word;width:50%;\">")
			.append(subDetail).append("</div>");
			
			/*changes for LEX:AIR00070474 end*/
			cellValue = strBulder.toString();
			gridXmlBuilder.addCells(cellValue);
			cellValue = DateLocalizationUtil.localizeDateTime(activityNote.getNoteDate(), false, locale);
			gridXmlBuilder.addCells(cellValue);
			cellValue = XMLEncodeUtil.escapeXML(activityNote.getNoteAuthor().getFirstName()) + " " + XMLEncodeUtil.escapeXML(activityNote.getNoteAuthor().getLastName());
			gridXmlBuilder.addCells(cellValue);
			cellValue = detail;
			gridXmlBuilder.addCells(StringUtil.appendEllipsis(cellValue, 50));
		}
		gridXmlBuilder.finish();
		String contentXML = gridXmlBuilder.getGridXmlString();
		return contentXML;
	}
	
	/**
	 * build the activity note list which can be edit, if the note was created by the login user.
	 * @param activityNoteList
	 * @return
	 */
	public String convertActivityNoteListToXMLForEdit(List<ActivityNote> activityNoteList, String currentContactId) {
		if(activityNoteList == null || activityNoteList.size() ==0){
			return "<?xml version=\\\"1.0\\\" ?><rows></rows>";
		} 
		String labelNote = localizeMessage("claim.label.note", null);
		String  buttonEdit = localizeMessage("button.edit",null);
		int listSize = activityNoteList.size();
		GridXmlBuilder gridXmlBuilder = new GridXmlBuilder(listSize);
		String cellValue = null;
		ActivityNote activityNote = null;
		StringBuilder strBulder = null;
		
		for(int i = 0;i < listSize;i++){
			gridXmlBuilder.nextRow(String.valueOf(i));
			activityNote = activityNoteList.get(i);
			
			String detail = activityNote.getNoteDetails()==null?"":activityNote.getNoteDetails();
			detail = detail.replaceAll("\r", "");
			detail = detail.replaceAll("\n", "\\\\n");
			String shortDetail = activityNote.getNoteDetails()==null?"":activityNote.getNoteDetails();
			//shortDetail = XMLEncodeUtil.escapeXML(shortDetail);
			shortDetail = shortDetail.replaceAll("\r", "");
			shortDetail = shortDetail.replaceAll("\n", " ");
			shortDetail = shortDetail.replaceAll("&quot;", "\"");
			String subRow =  activityNote.getNoteDetails()==null?"":activityNote.getNoteDetails().replaceAll("\r", "");
			subRow = subRow.replaceAll("\n", "<br>");
			subRow = subRow.replaceAll(" ", "&nbsp;");
			
			strBulder = new StringBuilder();
			strBulder.append("<strong>").append(labelNote).append("</strong><br><div  style=\"word-wrap: break-word;width:80%;\">")
					.append(subRow).append("</div>");
			cellValue = strBulder.toString();
			gridXmlBuilder.addCells(cellValue);
			cellValue = DateLocalizationUtil.localizeDateTime(activityNote.getNoteDate(), false, locale);
			gridXmlBuilder.addCells(cellValue);
			cellValue = activityNote.getNoteAuthor().getFirstName() + " " + activityNote.getNoteAuthor().getLastName();
			gridXmlBuilder.addCells(cellValue);
			cellValue = shortDetail;
			gridXmlBuilder.addCells(StringUtil.appendEllipsis(cellValue, 50));
			
			strBulder = new StringBuilder();
			strBulder.append("<input id=\"").append(i).append("_noteDetail\" type=\"hidden\" value=\"")
				.append(XMLEncodeUtil.escapeXML(detail)).append("\">");
			
			strBulder.append("<input id=\"").append(i).append("_contactId\" type=\"hidden\" value=\"")
				.append(activityNote.getNoteAuthor().getContactId()).append("\">");
			
			strBulder.append("<input id=\"").append(i).append("_activityUpdateFlag\" type=\"hidden\" value=\"")
				.append(activityNote.isActivityUpdateFlag()).append("\">");
			
			strBulder.append("<input id=\"").append(i).append("_noteId\" type=\"hidden\" value=\"")
				.append(activityNote.getNoteId()).append("\">");
			try{
				if (activityNote.getNoteAuthor().getContactId().equals(currentContactId)) {
					strBulder.append("<button class=\"button\" type=\"button\" onclick=\"editRow('")
						.append(i).append("')\">").append(buttonEdit).append("</button>");
				}
			}catch(Exception e){
				throw new RuntimeException("the Notes Author Contact id is null"+e.getMessage());
			}
			cellValue = strBulder.toString();
			gridXmlBuilder.addCells(cellValue);
		}
		
		gridXmlBuilder.finish();
		String contentXML = gridXmlBuilder.getGridXmlString();
		return contentXML;
	}
	
	
	public String convertActivityNoteListToXMLForEditSRCloseOut(List<ActivityNote> activityNoteList, String currentContactId) {
		if(activityNoteList == null || activityNoteList.size() ==0){
			return "<?xml version=\\\"1.0\\\" ?><rows></rows>";
		} 
		String labelNote = localizeMessage("claim.label.note", null);
		String  buttonEdit = localizeMessage("button.edit",null);
		int listSize = activityNoteList.size();
		GridXmlBuilder gridXmlBuilder = new GridXmlBuilder(listSize);
		String cellValue = null;
		ActivityNote activityNote = null;
		StringBuilder strBulder = null;
		
		for(int i = 0;i < listSize;i++){
			gridXmlBuilder.nextRow(String.valueOf(i));
			activityNote = activityNoteList.get(i);
			
			String detail = activityNote.getNoteDetails()==null?"":activityNote.getNoteDetails();
			detail = (detail==null?"":detail.replaceAll("\r", ""));
			detail = detail.replaceAll("\n", " ");		
			String subRow =  activityNote.getNoteDetails()==null?"":activityNote.getNoteDetails().replaceAll("\r", "");
			subRow = subRow.replaceAll("\n", "<br>");
			subRow = subRow.replaceAll(" ", "&nbsp;");
			
			strBulder = new StringBuilder();
			strBulder.append("<strong>").append(labelNote).append("</strong><br><div  style=\"word-wrap: break-word;width:80%;\">")
					.append(subRow).append("</div>");
			cellValue = strBulder.toString();
			gridXmlBuilder.addCells(cellValue);
			cellValue = DateLocalizationUtil.localizeDateTime(activityNote.getNoteDate(), false, locale);
			gridXmlBuilder.addCells(cellValue);
			cellValue = activityNote.getNoteAuthor().getFirstName() + " " + activityNote.getNoteAuthor().getLastName();
			gridXmlBuilder.addCells(cellValue);
			cellValue = detail;
			gridXmlBuilder.addCells(StringUtil.appendEllipsis(cellValue, 50));
			
			strBulder = new StringBuilder();
			strBulder.append("<input id=\"").append(i).append("_noteDetail\" type=\"hidden\" value=\"")
				.append(XMLEncodeUtil.escapeXML(detail)).append("\">");
			
			strBulder.append("<input id=\"").append(i).append("_contactId\" type=\"hidden\" value=\"")
				.append(activityNote.getNoteAuthor().getContactId()).append("\">");
			
			strBulder.append("<input id=\"").append(i).append("_activityUpdateFlag\" type=\"hidden\" value=\"")
				.append(activityNote.isActivityUpdateFlag()).append("\">");
			
			strBulder.append("<input id=\"").append(i).append("_noteId\" type=\"hidden\" value=\"")
				.append(activityNote.getNoteId()).append("\">");
			try{
				if (activityNote.getNoteAuthor().getContactId().equals(currentContactId)) {
					strBulder.append("<button class=\"button\" type=\"button\" onclick=\"editRow('")
						.append(i).append("')\">").append(buttonEdit).append("</button>");
				}
			}catch(Exception e){
				throw new RuntimeException("the Notes Author Contact id is null"+e.getMessage());
			}
			cellValue = strBulder.toString();
			gridXmlBuilder.addCells(cellValue);
		}
		
		gridXmlBuilder.finish();
		String contentXML = gridXmlBuilder.getGridXmlString();
		return contentXML;
	}
	
   
	/**
	 * 
	 * @param addresses
	 * @param totalCount
	 * @param posStart
	 * @param contextPath
	 * @return
	 */
	//Added for CI Address Cleansing
	public String convertAddressListToXML(List<GenericAddress> addresses, int totalCount, 
			int posStart, String contextPath) {
		LOGGER.debug("---------------Inside xmloutput generator---------------");
		
		/*Commented for Wipro style
		 * String pencilImageSrc = contextPath + IMAGE_PATH + CHANGE_IMAGE;
		String deleteImageSrc = contextPath + IMAGE_PATH + REMOVE_IMAGE;*/
		String isFavoriteImageSrc;
		
		String bookmarkTitle;
		
		StringBuffer xml = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		xml.append("<rows total_count=\"" + totalCount + "\" pos=\"" + posStart
				+ "\">\n");
		int i = 0;
		for (GenericAddress genericAddress : addresses) {
			/*Commented for Wipro style
			 * String addressId = genericAddress.getAddressId()!=null?genericAddress.getAddressId():"";
			String addressName = genericAddress.getAddressName()!=null?genericAddress.getAddressName():"";
			String addressLine1 = genericAddress.getAddressLine1()!=null?genericAddress.getAddressLine1():"";
			String addressLine2 = genericAddress.getAddressLine2()!=null?genericAddress.getAddressLine2():"";
			String addressLine3 = genericAddress.getAddressLine3()!=null?genericAddress.getAddressLine3():"";
			String addressCity = genericAddress.getCity()!=null?genericAddress.getCity():"";
			String addressState = genericAddress.getState()!=null?genericAddress.getState():"";
			String addressProvince = genericAddress.getProvince()!=null?genericAddress.getProvince():"";
			String addressStateProvince = genericAddress.getState()!=null && genericAddress.getProvince()!=null?genericAddress.getState()+genericAddress.getProvince():"";
			String addressCountry = genericAddress.getCountry()!=null?genericAddress.getCountry():"";
			String addressPostCode = genericAddress.getPostalCode()!=null?genericAddress.getPostalCode():"";*/
					
			if (genericAddress.getUserFavorite()) {
						isFavoriteImageSrc = contextPath + IMAGE_PATH + FAVORIATE_IMAGE_FILE_NAME;
						bookmarkTitle = PropertiesMessageUtil.getPropertyMessage(
								MESSAGE_BUNDLE_NAME,
								"requestInfo.tooltip.address.UnBookmark", locale);
			} else {
						isFavoriteImageSrc = contextPath + IMAGE_PATH	+ UNFAVORIATE_IMAGE_FILE_NAME;
						bookmarkTitle = PropertiesMessageUtil.getPropertyMessage(
								MESSAGE_BUNDLE_NAME,
								"requestInfo.tooltip.address.Bookmark", locale);
			}		
			if (genericAddress.getAddressId()==null){
				continue;
			}
			xml.append(" <row id=\"" + genericAddress.getAddressId() + "\">\n");
			
//			Added for CI7-start
			//changes for CI BRD 13-10-08 --STARTS
			xml.append("  <cell><![CDATA[<div class=\"relativeR autoWidth\"> <input name=\"btn_select\" class=\"button relativeR\"  type=\"button\" onclick=\"addPartnerAddressElement('"
					/* Ends Changes for defect 7960 MPS 2.1*/
					+ StringEscapeUtils.escapeJavaScript(genericAddress.getAddressId())
					+ "','"
					+ StringEscapeUtils.escapeJavaScript(genericAddress.getStoreFrontName() != null ? genericAddress
							.getStoreFrontName() : "").replaceAll("\"", "&quot;")
					+ "','"
					+ genericAddress.getUserFavorite()
					+ "','"
					+ StringEscapeUtils.escapeJavaScript(genericAddress.getAddressName() != null ? genericAddress
							.getAddressName() : "").replaceAll("\"", "&quot;")
					+ "','"
					+ StringEscapeUtils.escapeJavaScript(genericAddress.getAddressLine1() != null ? genericAddress
							.getAddressLine1() : "").replaceAll("\"", "&quot;")
					+ "','"
					+ StringEscapeUtils.escapeJavaScript(genericAddress.getOfficeNumber() != null ? genericAddress
							.getOfficeNumber() : "").replaceAll("\"", "&quot;")
					+ "','"
					+ StringEscapeUtils.escapeJavaScript(genericAddress.getAddressLine2() != null ? genericAddress
							.getAddressLine2() : "").replaceAll("\"", "&quot;")
					+ "','"
					+ StringEscapeUtils.escapeJavaScript(genericAddress.getCity() != null ? genericAddress
							.getCity() : "").replaceAll("\"", "&quot;")
					+ "','"
					+ StringEscapeUtils.escapeJavaScript(genericAddress.getCounty()!= null ? genericAddress.getCounty(): "").replaceAll("\"", "&quot;")
					+ "','"
					+ StringEscapeUtils.escapeJavaScript(genericAddress.getState() != null ? genericAddress
							.getState() : "").replaceAll("\"", "&quot;")
					+ "','"
					+ StringEscapeUtils.escapeJavaScript(genericAddress.getProvince() != null ? genericAddress
							.getProvince() : "").replaceAll("\"", "&quot;")
					+ "','"
					+ StringEscapeUtils.escapeJavaScript(genericAddress.getRegion() != null ? genericAddress
							.getRegion() : "").replaceAll("\"", "&quot;")
					+ "','"
					+ StringEscapeUtils.escapeJavaScript(genericAddress.getCountry() != null ? genericAddress
							.getCountry() : "").replaceAll("\"", "&quot;")
					+ "','"
					+ StringEscapeUtils.escapeJavaScript(genericAddress.getPostalCode() != null ? genericAddress
							.getPostalCode() : "").replaceAll("\"", "&quot;")
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
					+ StringEscapeUtils.escapeJavaScript(genericAddress.getDistrict()!= null ? genericAddress.getDistrict(): "").replaceAll("\"", "&quot;")
					+ "','"
					+ StringEscapeUtils.escapeJavaScript(genericAddress.getCountryISOCode()!= null ? genericAddress.getCountryISOCode(): "").replaceAll("\"", "&quot;")
					+ "','"
					+ StringEscapeUtils.escapeJavaScript(genericAddress.getStateCode()!= null ? genericAddress.getStateCode(): "").replaceAll("\"", "&quot;")
					
					+ "');\" value=\""
					+ getLocalizeSelectButton() + "\"/></div>]]></cell>\n");
			//changes for CI BRD 13-10-08 --ENDS
//			Added for CI7-end			
			
			xml.append("  <cell><![CDATA[<img class=\"helpIconList\" title=\""+bookmarkTitle+"\" src=\""+isFavoriteImageSrc +"\" id=\"starImg_address_"+genericAddress.getAddressId()+"\" onclick=\"setServiceAddressFavourite('"+genericAddress.getAddressId()+"')\" style=\"cursor:pointer;\" />]]></cell>\n");
			
			xml.append("  <cell><![CDATA[" + (genericAddress.getAddressName() != null ? genericAddress
					.getAddressName() : "")+ "]]></cell>\n");
			xml.append("  <cell><![CDATA[" + (genericAddress.getStoreFrontName() != null ? genericAddress
					.getStoreFrontName() : "")+ "]]></cell>\n");
			xml.append("  <cell><![CDATA[" + (genericAddress.getAddressLine1() != null ? genericAddress
					.getAddressLine1() : "")+ "]]></cell>\n");
			xml.append("  <cell><![CDATA[" + (genericAddress.getAddressLine2() != null ? genericAddress
					.getAddressLine2() : "")+ "]]></cell>\n");
			xml.append("  <cell><![CDATA[" + (genericAddress.getOfficeNumber() != null ? genericAddress
					.getOfficeNumber() : "")+ "]]></cell>\n");   // added to put the fields aligned to the headers in the address pop up of the create claim section
			xml.append("  <cell><![CDATA[" + (genericAddress.getCity() != null ? genericAddress
					.getCity() : "")+ "]]></cell>\n");
			xml.append("  <cell><![CDATA[" + (genericAddress.getState() != null ? genericAddress
					.getState() : "")+ "]]></cell>\n");
			xml.append("  <cell><![CDATA[" + (genericAddress.getProvince() != null ? genericAddress
					.getProvince() : "")+ "]]></cell>\n");
			xml.append("  <cell><![CDATA[" + (genericAddress.getCounty() != null ? genericAddress
					.getCounty() : "")+ "]]></cell>\n");
			xml.append("  <cell><![CDATA[" + (genericAddress.getDistrict() != null ? genericAddress
					.getDistrict() : "")+ "]]></cell>\n");
			
			/*New Fields Added for CR 14718*/
			xml.append("  <cell><![CDATA[" + (genericAddress.getRegion() != null ? genericAddress
					.getRegion() : "")+ "]]></cell>\n");
			/*New Fields Added for Phase 2*/
			
				
				
			xml.append("  <cell><![CDATA[" + (genericAddress.getPostalCode() != null ? genericAddress
							.getPostalCode() : "")+ "]]></cell>\n");
			xml.append("  <cell><![CDATA[" + (genericAddress.getCountry() != null ? genericAddress
							.getCountry() : "")+ "]]></cell>\n");
			
			
			
			xml.append(" </row>\n");
			i++;
		}
		xml.append("</rows>\n");
		return xml.toString();
	}
	
	private String getLocalizeSelectButton() {
		return (PropertiesMessageUtil.getPropertyMessage(LexmarkConstants.IDM_MESSAGE_BUNDLE_NAME,"button.select", locale));
	}
	private String getLocalizeAddLineBtn()
	{
		return (PropertiesMessageUtil.getPropertyMessage(LexmarkConstants.IDM_MESSAGE_BUNDLE_NAME,"button.addAnotherLine", locale));
	}
	
	/**
	 * Converts a list of open claims to XML String in multiple claims view.
	 * @param claimList
	 * @return XML String
	 */
	public String convertOpenClaimListToXML(List<Activity> claimList, float timezoneOffset) {
		int listSize = claimList.size();
		GridXmlBuilder gridXmlBuilder = new GridXmlBuilder(listSize);
		Activity claim = null;
		for (int i = 0; i < listSize; i++) {
			gridXmlBuilder.nextRow(String.valueOf(i));
			claim = claimList.get(i);
			if (claim.getActivityDate() == null){
				gridXmlBuilder.addCells("");				
			}else{
				TimezoneUtil.adjustDate(claim.getActivityDate(), 0 - timezoneOffset);
				gridXmlBuilder.addCells(DateLocalizationUtil.localizeDateTime(claim.getActivityDate(), true, locale));				
			}

			String popupTitle = localizeMessage("title.claimRequestDetail", null);
			
			gridXmlBuilder.addCells("<a href=\"###\" onClick=\"viewServiceHistoryPopup('" + popupTitle + "','"
					+ claim.getActivityId() + "','"+claim.getServiceRequest().getId()+"')\">" + claim.getServiceRequest().getServiceRequestNumber() + "</a>");
			gridXmlBuilder.addCells(claim.getServiceProviderReferenceNumber());
			gridXmlBuilder.addCells(claim.getPartnerAccount().getAccountName());
			gridXmlBuilder.addCells(XMLEncodeUtil.escapeXML(claim.getActualFailureCode().getName()));
			gridXmlBuilder.addCells("<button name=\"btn_select\" class=\"button\"  type=\"button\"  onclick=\"showClaimUpdateView('"
					+ claim.getActivityId() + "')\">" + localizeMessage("button.select",null) + "</button>");
		}
		gridXmlBuilder.finish();
		return gridXmlBuilder.getGridXmlString();
	}
	
	public String convertRecommendedPartListToXML(List<PartLineItem> partList, Boolean shipToDefault){
		StringBuffer xml = new StringBuffer("<?xml version=\"1.0\" ?>");
		xml.append("<rows>");
		if(partList!=null){
			for (PartLineItem rePartLineItem : partList) {
				String partId = rePartLineItem.getPartId()==null?"":rePartLineItem.getPartId();
				if(StringUtils.isEmpty(partId)) {
					continue;
				}
			//	String recommendedQuantity = rePartLineItem.getRecommendedQuantity()==null?"":rePartLineItem.getRecommendedQuantity();
					xml.append(" <row id=\""+ (partId)+"\">");
					if(!shipToDefault){
					xml.append("  <cell><![CDATA[<table><tr><td><select id=\"qty_"+partId+"\" disabled ="+!shipToDefault+" onchange=\"selectQty(this);\" onclick=\"saveOption(this);\" >"+generateOption()+"</select></td></tr></table>]]></cell>");
					}
					else
					{
					xml.append("  <cell><![CDATA[<table><tr><td><select id=\"qty_"+partId+"\" onchange=\"selectQty(this);\" onclick=\"saveOption(this);\" >"+generateOption()+"</select></td></tr></table>]]></cell>");
					}
					xml.append("  <cell><![CDATA["+ rePartLineItem.getRecommendedQuantity() +"]]></cell>");
					xml.append("  <cell><![CDATA["+ XMLEncodeUtil.escapeXML(rePartLineItem.getPartNumber())+"]]></cell>");
					xml.append("  <cell><![CDATA["+ XMLEncodeUtil.escapeXML(rePartLineItem.getPartName()) +"]]></cell>");
					xml.append("  <cell><![CDATA["+ (rePartLineItem.isReturnRequiredFlag()==true?localizeMessage("claim.lebel.returnRequired.yes",null):localizeMessage("claim.lebel.returnRequired.no",null)) +"]]></cell>");
					xml.append("  <cell></cell>");
					xml.append("  <cell><![CDATA["+(rePartLineItem.getPartLineItemId()==null?"":rePartLineItem.getPartLineItemId())+"]]></cell>");
					xml.append(" </row>");
			}
		}
		xml.append("</rows>");
		return xml.toString();
	}
	
	/*Added for CI CR's 15.1*/
	public String convertRecommendedPartToXML(List<Part> part){
		StringBuffer xml = new StringBuffer("<?xml version=\"1.0\" ?>");
		xml.append("<rows>");
		if(part!=null){
			for (Part rePartLineItem : part) {
				
					LOGGER.debug("Part Id" + rePartLineItem.getPartId());
					LOGGER.debug("Order Quantity" + rePartLineItem.getOrderQuantity());
					LOGGER.debug("Recommended Quantity" + rePartLineItem.getRecommendedQuantity());
					LOGGER.debug("Part Number" + rePartLineItem.getPartNumber());
					LOGGER.debug("Part Name" + rePartLineItem.getPartName());
					LOGGER.debug("Return Required" + rePartLineItem.isReturnRequiredFlag());
					if(null ==rePartLineItem.getPartId()){
						rePartLineItem.setPartId("");
					}
					xml.append(" <row id=\""+ rePartLineItem.getPartId() +"\">");
					if(null ==rePartLineItem.getOrderQuantity()){
						rePartLineItem.setOrderQuantity("");
					}
					xml.append("  <cell><![CDATA["+ rePartLineItem.getOrderQuantity() +"]]></cell>");
					xml.append("  <cell><![CDATA["+ rePartLineItem.getRecommendedQuantity() +"]]></cell>");
					if(null ==rePartLineItem.getPartNumber()){
						rePartLineItem.setPartNumber("");
					}
					xml.append("  <cell><![CDATA["+ XMLEncodeUtil.escapeXML(rePartLineItem.getPartNumber())+"]]></cell>");
					if(null == rePartLineItem.getPartName()){
						rePartLineItem.setPartName("");
					}
					xml.append("  <cell><![CDATA["+ XMLEncodeUtil.escapeXML(rePartLineItem.getPartName()) +"]]></cell>");
					xml.append("  <cell><![CDATA["+ (rePartLineItem.isReturnRequiredFlag()==true?localizeMessage("claim.lebel.returnRequired.yes",null):localizeMessage("claim.lebel.returnRequired.no",null)) +"]]></cell>");
					xml.append("  <cell></cell>");
					xml.append("  <cell></cell>");
					xml.append(" </row>");
			}
		}
		xml.append("</rows>");
		return xml.toString();
	}
	/*END*/
	
	public String convertNoteListToXML(List<ActivityNote> noteList,String authorContactId){
		//changed for LEX:AIR00078768 
		StringBuffer xml = new StringBuffer("<?xml version=\"1.0\" ?>");
		xml.append("<rows>");
		if(noteList == null || noteList.size() ==0){
			return "<?xml version=\\\"1.0\\\" ?><rows></rows>";
		} 
			String labelNote = localizeMessage("claim.label.note", null);
			int listSize = noteList.size();
			GridXmlBuilder gridXmlBuilder = new GridXmlBuilder(listSize);
			String cellValue = null;
			ActivityNote note = null;
			StringBuilder strBulder = null;
			for(int i = 0;i < listSize;i++){
				note = noteList.get(i);
				String noteId = note.getNoteId()==null?"":note.getNoteId();
				String activityAuthor="";
				String contactId = "";
				gridXmlBuilder.nextRow(String.valueOf(i));
				//activityNote = noteList.get(i);
				String shortDetail = note.getNoteDetails()==null?"":note.getNoteDetails().replaceAll("\r", "");
				shortDetail =shortDetail.replaceAll("\n", " ");
				String detail = note.getNoteDetails()==null?"":note.getNoteDetails().replaceAll("\r", "\\\\r");
				detail = detail.replaceAll("\n", "\\\\n");
				String subRow =  note.getNoteDetails()==null?"":note.getNoteDetails().replaceAll("\r", "");
				subRow = subRow.replaceAll("\n", "<br>");
				subRow = subRow.replaceAll(" ", "&nbsp;");
				subRow = subRow.replaceAll("'", "&#39;");//Added for CI-7 Defect #11682
				strBulder = new StringBuilder();
				
				strBulder.append("<strong>").append(labelNote).append("</strong><br><div  style=\"word-wrap: break-word;width:50%;\">")
				.append(subRow).append("</div>");
				cellValue = strBulder.toString();
				gridXmlBuilder.addCells(cellValue);
				cellValue=DateLocalizationUtil.localizeDateTime(note.getNoteDate(), false, locale);
				gridXmlBuilder.addCells(cellValue);
				cellValue = XMLEncodeUtil.escapeXML(note.getNoteAuthor().getFirstName()) + " " + XMLEncodeUtil.escapeXML(note.getNoteAuthor().getLastName());
				gridXmlBuilder.addCells(cellValue);
				cellValue=StringUtil.appendEllipsis(shortDetail, 50);
				gridXmlBuilder.addCells(cellValue);
				//Change done for CI-7 Defect #11682
				//cellValue=authorContactId.equals(contactId)?"<button class=\"button\" type=\"button\" onclick=\"editRow(\\'"+i+"\\')\">"+localizeMessage("button.edit",null)+"</button>":"";
				cellValue="<button class=\"button\" type=\"button\" onclick=\"editRow(\\'"+i+"\\')\">"+localizeMessage("button.edit",null)+"</button>";
				gridXmlBuilder.addCells(cellValue);
				cellValue=noteId;
				gridXmlBuilder.addCells(cellValue);
				cellValue="<input id=\""+i+"_noteDetail\" type=\"hidden\" value=\""+ XMLEncodeUtil.escapeXML(detail)+"\">]]";
				gridXmlBuilder.addCells(cellValue);
				cellValue=authorContactId;
				gridXmlBuilder.addCells(cellValue);
				cellValue=XMLEncodeUtil.escapeXML(note.getNoteAuthor().getFirstName());
				gridXmlBuilder.addCells(cellValue);
				cellValue=XMLEncodeUtil.escapeXML(note.getNoteAuthor().getLastName());
				gridXmlBuilder.addCells(cellValue);
				cellValue="false";
                gridXmlBuilder.addCells(cellValue);
				
			}
				
		gridXmlBuilder.finish();
		String contentXML = gridXmlBuilder.getGridXmlString();
		
		return contentXML;
	}
	
	public String convertServiceRequestActivityListToXML(List<ServiceRequestActivity> serviceRequestActivityList){
		StringBuffer xml = new StringBuffer("<?xml version=\"1.0\" ?>");
		xml.append("<rows>");
		if(serviceRequestActivityList!=null){
			for (ServiceRequestActivity serviceRequestActivity : serviceRequestActivityList) {
				String serviceRequestActivityId = serviceRequestActivity.getActivityId()==null?"":serviceRequestActivity.getActivityId();
				if(StringUtils.isEmpty(serviceRequestActivityId)){
					continue;
				}
				
				xml.append(" <row id=\""+ (serviceRequestActivityId)+"\">");
				xml.append("  <cell><![CDATA[<strong>"+ localizeMessage("claim.label.message", null)+"</strong><br><div style=\"word-wrap: break-word;width:80%\">"+serviceRequestActivity.getMessage() +"</div>]]></cell>");
				xml.append("  <cell><![CDATA["+ DateLocalizationUtil.localizeDateTime(serviceRequestActivity.getActivityDate(),false,getLocale())+"]]></cell>");
				xml.append("  <cell><![CDATA["+ serviceRequestActivity.getRecipientEmail()+"]]></cell>");
				xml.append("  <cell><![CDATA["+ StringUtil.appendEllipsis(serviceRequestActivity.getMessage(), 50) +"]]></cell>");
				xml.append(" </row>");
			}
		}
		xml.append("</rows>");
		return xml.toString();
	}
	
	private static String generateOption(){
		String option = "<option value=\"0\">0</option><option value=\"1\">1</option><option value=\"2\">2</option>"
						+"<option value=\"3\">3</option><option value=\"4\">4</option><option value=\"5\">5</option>";
		return option;
	}

	public String convertRecommendedPartList(List<PartLineItem> recommendedPartList) {
		if (recommendedPartList == null || recommendedPartList.size() == 0) {
			return "<?xml version=\\\"1.0\\\" ?><rows></rows>";
		}

		GridXmlBuilder gridXmlBuilder = new GridXmlBuilder(recommendedPartList.size());
		int i = 0;
		for (PartLineItem partLineItem : recommendedPartList) {
			gridXmlBuilder.nextRow(String.valueOf(i++));
			gridXmlBuilder.addCells(String.valueOf(partLineItem.getRecommendedQuantity()));
			gridXmlBuilder.addCells(XMLEncodeUtil.escapeXML(partLineItem.getPartNumber()));
			gridXmlBuilder.addCells(XMLEncodeUtil.escapeXML(partLineItem.getPartName()));
			//Changes for CI7 14-07-06 START
			if((partLineItem.getLineStatus())!=null)
			{
				gridXmlBuilder.addCells(XMLEncodeUtil.escapeXML(partLineItem.getLineStatus().getValue()));
			}
			else
			{
				gridXmlBuilder.addCells(XMLEncodeUtil.escapeXML(""));
			}
			//Changes for CI7 14-07-06 END


			if (partLineItem.isReturnRequiredFlag()){
				gridXmlBuilder.addCells(localizeMessage("selection.label.yes", null));
			}
			else{
				gridXmlBuilder.addCells(localizeMessage("selection.label.No", null));
			}
		}
		return gridXmlBuilder.getGridXmlString();
	}

	public String convertPartnerAgreementToXML(List<PartnerAgreement> partnerAgreementList) {
		partnerAgreementList = partnerAgreementList == null ? new ArrayList<PartnerAgreement>() : partnerAgreementList;
		final TreeXmlBuilder treeXmlBuilder = new TreeXmlBuilder();
		Item rootItem = new Item("1", localizeMessage("paymentRequest.filter.byPartnerAgreement", null));
		for (PartnerAgreement partnerAgreement : partnerAgreementList) {
			rootItem.addChildItem(new TreeXmlBuilder.Item(partnerAgreement.getPartnerAgreementId(), partnerAgreement
					.getPartnerAgreementName()));
		}
		treeXmlBuilder.addItem(rootItem);
		return treeXmlBuilder.toXML();
	}
	
	public String convertBulkUploadStatusToXML(List<BulkUploadStatus> list){
		StringBuffer xml = new StringBuffer("<?xml version=\"1.0\" ?>\n");
		xml.append("<rows>\n");
		int i = 0;
		if (list != null){
			for (BulkUploadStatus bulkUploadStatus : list) {
				xml.append(" <row id=\""+i+"\">\n");
				xml.append("  <cell><![CDATA["+ formatAttachmentName(bulkUploadStatus.getAttachmentName())+"]]></cell>\n");
				xml.append("  <cell><![CDATA["+ bulkUploadStatus.getUploadFileType() +"]]></cell>\n");
				xml.append("  <cell><![CDATA["+ bulkUploadStatus.getSize() +"]]></cell>\n");
				xml.append("  <cell><![CDATA["+ bulkUploadStatus.getSubmittedOn() +"]]></cell>\n");
				xml.append("  <cell><![CDATA["+ bulkUploadStatus.getCompletedOn() +"]]></cell>\n");
				xml.append("<cell><![CDATA[<a href=\"###\" onClick=\"outPutFile('"+bulkUploadStatus.getAttachmentName()+"')\">" + bulkUploadStatus.getStatus() + "</a>]]></cell>\n");
				xml.append("  <cell>"+ bulkUploadStatus.getComment() +"</cell>\n");
				xml.append(" </row>\n");
				i ++;
			}			
		}
		xml.append("</rows>\n");
		return xml.toString();
	}
	
	private String formatAttachmentName(String attachmentName){
		//String[] names = attachmentName.split("~");
		return attachmentName;
	}
	
	private String localizeMessage(String propertyAttribute, String bundleName) {
		if (StringUtil.isStringEmpty(bundleName)) {
			return (PropertiesMessageUtil.getPropertyMessage(LexmarkPPConstants.MESSAGE_BUNDLE_NAME, propertyAttribute, locale));
		}
		return (PropertiesMessageUtil.getPropertyMessage(bundleName, propertyAttribute, locale));
	}
	private String removeReturnControlFlag(String str){
		if(str == null){
			return "";
		}
		else{
			str = str.replaceAll("\r", "");
			str = str.replaceAll("\n", " ");
			
			return str;
		}
	}

// for CI5 attachment file
	public String convertAttachmentFileListToXML(List<AttachmentFile> fileList) {
		int rowId = 0;
		StringBuffer xml = new StringBuffer("<?xml version=\"1.0\" ?>\n");
		xml.append("<rows>\n");
		for(AttachmentFile file : fileList) {
			xml.append(" <row id=\""+ rowId +"\">\n");
			xml.append("  <cell><![CDATA[<a href=\"###\" onClick=\"openFile('"+file.getFileName()+"')\">" + file.getFileName() + "</a>]]></cell>\n");
			xml.append("  <cell><![CDATA["+ file.getUploadDate() +"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ file.getFileSize() +"]]></cell>\n");
			xml.append("  <cell><![CDATA["+ file.getFileFormat() +"]]></cell>\n");
			xml.append(" </row>\n");
			rowId ++;
		}
		xml.append("</rows>\n");
		return xml.toString();
	}
//	end of addition for CI5 attachment file 
	// RMA LINE ITEM STATUS
	/**
	 * 
	 * @param returnPartList
	 * @return
	 */
	public String convertRMAReturnPartListToXML(List<PartLineItem> returnPartList, boolean subRow) {
		if (returnPartList == null || returnPartList.size() == 0) {
			return "<?xml version=\\\"1.0\\\" ?><rows></rows>";
		}
		int listSize = returnPartList.size();
		GridXmlBuilder gridXmlBuilder = new GridXmlBuilder(listSize);
		
		for (int i = 0; i < listSize; i++) {
			gridXmlBuilder.nextRow(String.valueOf(i));
			PartLineItem partLineItem = returnPartList.get(i);
			if (subRow) {
				GenericAddress address = partLineItem.getShipToAdress();
				final StringBuilder subRowContent = new StringBuilder();
				subRowContent.append("<div><table>");
				subRowContent.append("<tr><td align=\"right\"><B>");
				subRowContent.append(localizeMessage("claim.label.returnTo", null));
				subRowContent.append("&nbsp;</B></td><td>");
				subRowContent.append(address.getAddressName());
				subRowContent.append("</td></tr>");
				subRowContent.append("<tr><td Valign=\"top\" align=\"right\"><B>");
				subRowContent.append(localizeMessage("claim.label.address", null));
				subRowContent.append("&nbsp;</B></td><td Valign=\"top\">");
				subRowContent.append(address.getAddressLine1()).append("<br/>");
				if(address.getAddressLine2() != null && !"".equals(address.getAddressLine2())){
					subRowContent.append(address.getAddressLine2()).append("<br/>");
				}
				if(address.getAddressLine3() != null && !"".equals(address.getAddressLine3())){
					subRowContent.append(address.getAddressLine3()).append("<br/>");
				}
				subRowContent.append(address.getCity()).append(",&nbsp;");
				if (address.getState() != null) {
					subRowContent.append(address.getState());
				} else {
					subRowContent.append(address.getProvince());
				}
				subRowContent.append(",&nbsp;").append(address.getCountry()).append(",&nbsp;").append(address.getPostalCode());
				subRowContent.append("</td><tr>");
				subRowContent.append("</table></div>");
				gridXmlBuilder.addCells(subRowContent.toString());
			}
			//Changes for CI7 Defect # 14235 Start

			gridXmlBuilder.addCells(String.valueOf(partLineItem.getQuantity()));
			gridXmlBuilder.addCells(XMLEncodeUtil.escapeXML(partLineItem.getPartNumber()));
			gridXmlBuilder.addCells(XMLEncodeUtil.escapeXML(partLineItem.getPartName()));
			// ToDo Localization
			// RMA
			gridXmlBuilder.addCells(XMLEncodeUtil.escapeXML(partLineItem.getLineRMAStatus().getName()));
            gridXmlBuilder.addCells(partLineItem.getSerialNumber());
			if (partLineItem.getPartReceivedDate() == null){
				gridXmlBuilder.addCells("");				
			}else{
				gridXmlBuilder.addCells(DateLocalizationUtil.localizeDateTime(partLineItem.getPartReceivedDate(), false, locale));				
			}
			if(null !=partLineItem.getCarrier()){
			gridXmlBuilder.addCells(XMLEncodeUtil.escapeXML(partLineItem.getCarrier().getName()));
			}
			else
			{
			gridXmlBuilder.addCells("");
			}
			//Changes for CI7 Defect # 14235 End
			if(partLineItem.getCarrier() != null){
			if (LexmarkConstants.CARRIER_LIST.contains(partLineItem.getCarrier().getValue())) {
				StringBuilder strBulder = new StringBuilder();
				strBulder.append("<a href=\"###\" onClick=\"onTrackingNumberClick('").append(partLineItem.getCarrier().getName())
						.append("','").append(partLineItem.getTrackingNumber()).append("')\">").append(
								partLineItem.getTrackingNumber()).append("</a>");
				
				gridXmlBuilder.addCells(strBulder.toString());
			} else{
				gridXmlBuilder.addCells(partLineItem.getTrackingNumber());
			}
			}else {
				gridXmlBuilder.addCells(partLineItem.getTrackingNumber());
			}
		}
		gridXmlBuilder.finish();
	
		return gridXmlBuilder.getGridXmlString();
	}
	/**
	 * Added by MPS Offshore Team for the implementation of Payments CR - AR
	 * @param accountsRcvblList
	 * @param size
	 * @param i
	 * @return
	 */
	public String convertAccountsRcvblListToXML(List<AccountReceivable> accountsRcvblList, int size, int posStart) {
		StringBuffer xml = new StringBuffer("<?xml version=\"1.0\" ?>\n");
		xml.append("<rows total_count=\"" + size + "\" pos=\"" + posStart+ "\">\n");
		int i=0;		
	
		for (AccountReceivable accountsRcvbl: accountsRcvblList) {
			
			String accName = accountsRcvbl.getAccountName() != null ? XMLEncodeUtil.escapeXML(accountsRcvbl.getAccountName()) : "";			
			String billToAddressLine1= accountsRcvbl.getBillAddress() != null ? XMLEncodeUtil.escapeXML(accountsRcvbl.getBillAddress().getAddressLine1()) : "";			
			String billToAddressLine2= accountsRcvbl.getBillAddress() != null ? XMLEncodeUtil.escapeXML(accountsRcvbl.getBillAddress().getAddressLine2()) : "";
			String billToAddressLine3= accountsRcvbl.getBillAddress() != null ? XMLEncodeUtil.escapeXML(accountsRcvbl.getBillAddress().getAddressLine3()) : "";
			String billToCity= accountsRcvbl.getBillAddress() != null ? XMLEncodeUtil.escapeXML(accountsRcvbl.getBillAddress().getCity()) : "";
			String billToState= accountsRcvbl.getBillAddress() != null ? XMLEncodeUtil.escapeXML(accountsRcvbl.getBillAddress().getState()) : "";
			String billToProvince= accountsRcvbl.getBillAddress() != null ? XMLEncodeUtil.escapeXML(accountsRcvbl.getBillAddress().getProvince()) : "";
			String billToCountry= accountsRcvbl.getBillAddress() != null ? XMLEncodeUtil.escapeXML(accountsRcvbl.getBillAddress().getCountry()) : "";
			String billToPostalCode= accountsRcvbl.getBillAddress() != null ? XMLEncodeUtil.escapeXML(accountsRcvbl.getBillAddress().getPostalCode()) : "";			
			String soldTo=accountsRcvbl.getSoldToNumber() != null ? XMLEncodeUtil.escapeXML(accountsRcvbl.getSoldToNumber()):"";
			String companyCode=accountsRcvbl.getCompanyCode() != null ? XMLEncodeUtil.escapeXML(accountsRcvbl.getCompanyCode()):"";			
			xml.append(" <row id=\"" + i + "\">\n");			
			xml.append("  <cell><![CDATA[" + accName+ "]]></cell>\n");
			xml.append("  <cell><![CDATA[" + billToAddressLine1 + " "+billToAddressLine2+" "+ billToCity+","+ billToState+","+ billToProvince+","+ billToCountry+" "+ billToPostalCode+"]]></cell>\n");
			xml.append("  <cell><![CDATA[" + soldTo+ "]]></cell>\n");
			xml.append("  <cell><![CDATA[ <input name=\"btn_select\" class=\"button\"  type=\"button\" id=\"button"+i+"\" " +
			"onclick=\"setPayeeAccount(1234,"+companyCode+","+i+",'"+billToAddressLine1+"','"+billToAddressLine2+"','"+billToAddressLine3+"','"+billToCity+"','"+billToState+"','"+billToProvince+"','"+billToCountry+"','"+billToPostalCode+"');\" value=\""	+ getLocalizeSelectButton() + "\"/>]]></cell>\n");
			xml.append(" </row>\n");
			i++;
		}			
				
		xml.append("</rows>\n");
		return xml.toString();
		
	}

	public String convertSRListToXML(List<ServiceRequestAP> serviceRequestList,int posStart,int totalCount) {
		LOGGER.debug("XmlOutputGenerator.convertSRListToXMLMock--Enter:");
		
		if(serviceRequestList == null || serviceRequestList.size() ==0){
			return "<?xml version=\"1.0\" ?><rows>" +
			"</rows>";
		}
		StringBuffer xml = new StringBuffer("<?xml version=\"1.0\" ?>\n");
		
		xml.append("<rows total_count=\"" + totalCount + "\" pos=\"" + posStart+ "\">\n");
		ServiceRequestAP serviceRequest = null;
		int listSize = serviceRequestList.size();
		for (int i = 0;i < listSize;i++) {	
			serviceRequest = serviceRequestList.get(i);
			String requestNumber = serviceRequest.getRequestNumber() != null ? XMLEncodeUtil.escapeXML(serviceRequest.getRequestNumber()) : "";
			requestNumber=requestNumber.replaceAll("^0*", "");
			
			
			
			if (StringUtils.isEmpty(requestNumber)){
				continue;
			}
			xml.append(" <row id=\"" + (requestNumber)+i + "\">\n");
			xml.append("  <cell><![CDATA[<A href=\"#\" onclick=\"showSRRequestDetail('"+requestNumber+"')\">"+requestNumber+"</A>]]></cell>\n");
			xml.append("  <cell><![CDATA[" +  new BigDecimal(serviceRequest.getAmount(), new MathContext(15, RoundingMode.FLOOR)).setScale(2, BigDecimal.ROUND_HALF_DOWN).toPlainString()+ "]]></cell>\n");
			xml.append(" </row>\n");
		}	
		xml.append("</rows>\n");
		LOGGER.debug("XmlOutputGenerator.convertSRListToXMLMock--Exit:");
		return xml.toString();
	}
	
	public String convertSRListToXMLReal(List<ServiceRequestAP> serviceRequestList) {
		LOGGER.debug("XmlOutputGenerator.convertSRListToXML--Enter:");
		if(serviceRequestList == null || serviceRequestList.size() ==0){
			return "<?xml version=\\\"1.0\\\" ?><rows></rows>";
		} 
		
		int listSize = serviceRequestList.size();
		GridXmlBuilder gridXmlBuilder = new GridXmlBuilder(listSize);
		String cellValue = null;
		ServiceRequestAP serviceRequest = null;
		for(int i = 0;i < listSize;i++){
			gridXmlBuilder.nextRow(String.valueOf(i));
			serviceRequest = serviceRequestList.get(i);
			
			if(serviceRequest.getRequestNumber()!=null)
			{
				cellValue = "<A href=\"#\" onclick=showSRRequestDetail('"+ serviceRequest.getRequestNumber()+"')>"
			+serviceRequest.getRequestNumber()+"</A>";
			}else{
				cellValue = "";
			}
			gridXmlBuilder.addCells(cellValue);
			
			cellValue = String.valueOf(serviceRequest.getAmount());
			gridXmlBuilder.addCells(cellValue);
		}
		gridXmlBuilder.finish();
		String contentXML = gridXmlBuilder.getGridXmlString();
		//LOGGER.debug(contentXML);
		LOGGER.debug("XmlOutputGenerator.convertInvoiceAPListToXML--Exit:");
		return contentXML;
	}
	//Added for Address Cleansing-CI
	
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
}
