package com.lexmark.exporter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.lexmark.constants.LexmarkPPConstants;
import com.lexmark.domain.AccountContact;
import com.lexmark.domain.Activity;
import com.lexmark.domain.ActivityNote;
import com.lexmark.domain.AdditionalPaymentRequest;
import com.lexmark.domain.Asset;
import com.lexmark.domain.Debrief;
import com.lexmark.domain.GenericAddress;
import com.lexmark.domain.ListOfValues;
import com.lexmark.domain.Order;
import com.lexmark.domain.PartLineItem;
import com.lexmark.domain.Payment;
import com.lexmark.domain.ServiceRequestActivity;
import com.lexmark.domain.ServiceRequestOrderLineItem;
import com.lexmark.domain.TechnicianInstruction;
import com.lexmark.portlet.source.OrderRequestController;
import com.lexmark.util.DateLocalizationUtil;
import com.lexmark.util.DateUtil;
import com.lexmark.util.DownloadFileLocalizationUtil;
import com.lexmark.util.ObjectDebugUtil;
import com.lexmark.util.PropertiesMessageUtil;
import com.lexmark.util.StringUtil;
import com.lexmark.util.TimezoneUtil;
import com.lexmark.util.XMLEncodeUtil;

public class PartnerPDFTemplate {
	private Locale locale = null;
	private float timezoneOffset = 0;	
	private static Logger logger = LogManager.getLogger(PartnerPDFTemplate.class);
	public String generateClaimDetailString(Activity activity,List<ServiceRequestActivity> serviceRequestActivityList,List<Activity> serviceHistoryList) throws Exception{
		 StringBuffer buf = new StringBuffer();
		 generatePDFHead(buf,10);
		 buf.append(generateClaimInformationString(activity));
		 buf.append(generateDeviceInformationString(activity));
		 if(serviceHistoryList != null && serviceHistoryList.size() > 0){
			buf.append(generateServiceHistoryString(serviceHistoryList));
		 }
		 buf.append(generateCustomerInformationString(activity));
		 buf.append(generateProblemInformationString(activity));
		 buf.append(generateTecnicianString(activity));
		 Debrief debrief = activity.getDebrief();
		 if(debrief != null && !StringUtil.isStringEmpty(debrief.getDebriefStatus())){
			 buf.append(generateDebriefClaimString(debrief));
		 }
		 
		 boolean isShowPendingPartList = false;
		 boolean isShowOrderPartList = false;
		 boolean isReturnPartList = false;
		 
		 List<PartLineItem> partList = activity.getPendingPartList();
		 if(partList != null && !partList.isEmpty()){
			 isShowPendingPartList = true;
		 }
		 partList = activity.getOrderPartList();
		 if(partList != null && !partList.isEmpty()){
			 isShowOrderPartList = true;
		 }
		 partList = activity.getReturnPartList();
		 if(partList != null && !partList.isEmpty()){
			 isReturnPartList = true;
		 }
		 if(isShowPendingPartList || isShowOrderPartList || isReturnPartList){
			 buf.append("<br/><div id ='bd'>");
			 buf.append("<div id='st'>").append(localizeMessage("claim.label.partsAndTools")).append("</div>");
			 if(isShowPendingPartList){
				 buf.append(generatePendingPartListString(activity.getPendingPartList()));
			 }
			 if(isShowOrderPartList){
				 buf.append(generateOrderPartListString(activity.getOrderPartList()));
			 }
			 if(isReturnPartList){
				 buf.append(generateReturnPartListString(activity.getReturnPartList()));
			 }
			 buf.append("</div>");
		 }
		 List<AdditionalPaymentRequest> additionalPaymentRequestList = activity.getAdditionalPaymentRequestList();
		 if(additionalPaymentRequestList != null && !additionalPaymentRequestList.isEmpty()){
			 buf.append(generateAdditionalPaymentRequestListString(additionalPaymentRequestList));
		 }
		 List<ActivityNote> activityNoteList = activity.getActivityNoteList();
		 if(activityNoteList != null && !activityNoteList.isEmpty()){
			 buf.append(generateNoteListString(activityNoteList));
		 }
		 if(serviceRequestActivityList != null && !serviceRequestActivityList.isEmpty()){
			 buf.append(generateEmailNotificationsListString(serviceRequestActivityList));
		 }
		 
		 generatePDFFoot(buf);
		 
		return buf.toString();
	}
	
	//Added By MPS Offshore Team for Service Order Detail PDF creation
	public String generateOrderDetailString(Order order) throws Exception{
		 StringBuffer buf = new StringBuffer();
		 generatePDFHead(buf,10);
		 buf.append(generateOrderInformationString(order));		
		 
		 boolean isShowPendingShipmentPartList = false;
		 boolean isShowProcessedPartList = false;
		 
		 List<ServiceRequestOrderLineItem> partList = order.getPendingShipments();
		 if(partList != null && !partList.isEmpty()){
			 isShowPendingShipmentPartList = true;
		 }
		 partList = order.getProcessedParts();
		 if(partList != null && !partList.isEmpty()){
			 isShowProcessedPartList = true;
		 }
		 
		 if(isShowPendingShipmentPartList || isShowProcessedPartList){
			 buf.append("<br/><div id ='bd'>");
			 buf.append("<div id='st'>").append(localizeMessage("requestInfo.heading.serviceOrder.fulfillmentInformation")).append("</div>");
			 
			 if(isShowPendingShipmentPartList){
				 buf.append(generatePendingShipmentPartListString(order.getPendingShipments(),order.getOrderDate()));
			 }
			 if(isShowProcessedPartList){
		
				 buf.append(generateProcessedPartListString(order.getProcessedParts()));
			 }
			 buf.append("</div>");
		 }
		 
		 buf.append(generateDeliveryInformationString(order));
		 if(order.getEmailActivities()!=null && !order.getEmailActivities().isEmpty()){
			 buf.append(generateEmailNotificationsListString(order.getEmailActivities()));
		 }

/*		 if(serviceRequestActivityList != null && !serviceRequestActivityList.isEmpty()){
			 buf.append(generateEmailNotificationsListString(serviceRequestActivityList));
		 }*/
		 
		 generatePDFFoot(buf);
		 
		return buf.toString();
	}
	
	
	public String generateServiceRequestDetailString(Activity activity,List<ServiceRequestActivity> serviceRequestActivityList,List<Activity> serviceHistoryList) throws Exception{
		 StringBuffer buf = new StringBuffer();
		 generatePDFHead(buf,10);
		
		 buf.append(generateRequestInformationString(activity));
		 buf.append(generateDeviceInformationString(activity));
		 if(serviceHistoryList != null && !serviceHistoryList.isEmpty()){
			buf.append(generateServiceHistoryString(serviceHistoryList));
		 }
		 buf.append(generateRequestCustomerInformationString(activity));
		 buf.append(generateRequestProblemInformationString(activity));
		 
		 buf.append(generateServiceInformationString(activity));
		
		 Debrief debrief = activity.getDebrief();
		 if(debrief != null && !StringUtil.isStringEmpty(debrief.getDebriefStatus())){
			 buf.append(generateCloseOutActivityString(activity));
		 }
		 buf.append(generateTecnicianString(activity));
		 buf.append(generateTecnicianInstructions(activity)); 
		 List<TechnicianInstruction> technicianInstructions = activity.getServiceInstructionList();
		 if (technicianInstructions != null && !technicianInstructions.isEmpty()){
			 buf.append(generateTechnicianInstructionsListString(technicianInstructions));
		 }
		 
		 boolean isShowPendingPartList = false;
		 boolean isShowOrderPartList = false;
		 boolean isReturnPartList = false;
		 
		 List<PartLineItem> partList = activity.getPendingPartList();
		 if(partList != null && !partList.isEmpty()){
			 isShowPendingPartList = true;
		 }
		 partList = activity.getOrderPartList();
		 if(partList != null && !partList.isEmpty()){
			 isShowOrderPartList = true;
		 }
		 partList = activity.getReturnPartList();
		 if(partList != null && !partList.isEmpty()){
			 isReturnPartList = true;
		 }
		
		 if(isShowPendingPartList || isShowOrderPartList || isReturnPartList){
			 buf.append("<br/><div id ='bd'>");
			 buf.append("<div id='st'>").append(localizeMessage("claim.label.partsAndTools")).append("</div>");
			 if(isShowPendingPartList){
				 buf.append(generatePendingPartListString(activity.getPendingPartList()));
			 }
			 if(isShowOrderPartList){
				 buf.append(generateOrderPartListString(activity.getOrderPartList()));
			 }
			 if(isReturnPartList){
				 buf.append(generateReturnPartListString(activity.getReturnPartList()));
			 }
			 buf.append("</div>");
		 }
		 partList = activity.getRecommendedPartList();
		 if(partList != null && !partList.isEmpty()){
			 buf.append(generateRecommendedPartsListString(partList));
		 }
		 List<AdditionalPaymentRequest> additionalPaymentRequestList = activity.getAdditionalPaymentRequestList();
		 if(additionalPaymentRequestList != null && !additionalPaymentRequestList.isEmpty()){
			 buf.append(generateAdditionalPaymentRequestListString(additionalPaymentRequestList));
		 }
		 List<ActivityNote> activityNoteList = activity.getActivityNoteList();
		 if(activityNoteList != null && !activityNoteList.isEmpty()){
			 buf.append(generateNoteListString(activityNoteList));
		 }
		 if(serviceRequestActivityList != null && !serviceRequestActivityList.isEmpty()){
			 buf.append(generateEmailNotificationsListString(serviceRequestActivityList));
		 }
		   		 
		 generatePDFFoot(buf);
		 return buf.toString();
	}
	
	
	public String generatePaymentDetailString(Payment payment ,List<Activity> activity) throws Exception{
		 StringBuffer buf = new StringBuffer();
		 generatePDFHead(buf,7);
		 buf.append(generatePaymentInformationString(payment));
		 buf.append(generatePaymentLineItemListString(activity));
		 generatePDFFoot(buf);
		 return buf.toString();
	}
	
	
	private void generatePDFHead(StringBuffer buf,int fontSize){
		 int thSize = fontSize + 1;
		 buf.append("<html><head>"); 
		 buf.append("<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />");
		 // put in some style
		 buf.append("<style language='text/css'>" +
		 		"#bd { border: 1px solid #000000;}" +
		 		"#st {background-color: #009ACF; font-size:15px; color:white;}" +
		 		"td {font-size:" + fontSize + "px}" +
		 		"#tf {font-size:" + thSize + "px;}"+
		 		"th {font-size:" + thSize + "px}");
		 buf.append(" .dir{ border: 1px solid #000000;border-collapse:collapse;}");
		 buf.append(" .dir td{border: 1px solid #000000;}");
		 buf.append("  .dir th{border: 1px solid #000000;}");
		 buf.append(" body {font-family: Arial Unicode MS;}");
		 buf.append(" </style>");
		 // end of head
		 buf.append("</head>");
		 // generate the body
		 buf.append("<body>");
	}
	private void generatePDFFoot(StringBuffer buf){
		 buf.append("</body>");
		 buf.append("</html>");
	}
	
	private String generateClaimInformationString(Activity activity){
		StringBuffer buf = new StringBuffer();
		buf.append("<br/><div id ='bd'>");
		buf.append("<div id='st'>").append(localizeMessage("claim.label.claimInformation")).append("</div>");
		buf.append("<div><table ><tr>");										
		buf.append("<td width='300px' align='right'>").append(localizeMessage("claim.label.claimNumber")).append("</td>");
		buf.append("<td width='10px'/>");
		buf.append("<td>" + showString(activity.getServiceRequest().getServiceRequestNumber()) + "</td></tr><tr>");									
		buf.append("<td align='right'>").append(localizeMessage("claim.label.claimStatus")).append("</td><td/>");				
		buf.append("<td>" + showString(activity.getActivityStatus().getName()) + "</td></tr><tr>");									
		buf.append("<td align='right'>").append(localizeMessage("claim.label.openedDate")).append("</td><td/>");
		buf.append("<td>" + localizeDateTimeString(activity.getServiceRequest().getServiceRequestDate(),false) + "</td></tr></table></div></div>");
		return buf.toString();
	}
	
	private String generateOrderInformationString(Order order){
		StringBuffer buf = new StringBuffer();
		buf.append("<br/><div id ='bd'>");
		buf.append("<div id='st'>").append(localizeMessage("requestInfo.heading.serviceOrder.orderInformation")).append("</div>");
		buf.append("<div><table ><tr>");										
		buf.append("<td width='300px' align='right'>").append(localizeMessage("requestInfo.label.serviceOrder.requestNumber")).append("</td>");
		buf.append("<td width='10px'/>");
		buf.append("<td>" + showString(order.getServiceRequestNumber()) + "</td></tr><tr>");		
		buf.append("<td align='right'>").append(localizeMessage("requestInfo.label.serviceOrder.orderNumber")).append("</td><td/>");				
		buf.append("<td>" + showString(order.getOrderNumber()) + "</td></tr><tr>");
		/*Added for Jan Release*/
		buf.append("<td align='right'>").append(localizeMessage("requestInfo.label.serviceOrder.serviceType")).append("</td><td/>");				
		buf.append("<td>" + showString(order.getOrderType()) + "</td></tr><tr>");
		/*End Add*/
		buf.append("<td align='right'>").append(localizeMessage("requestInfo.label.serviceOrder.serviceProviderOrderRefNo")).append("</td><td/>");				
		buf.append("<td>" + showString(order.getServiceProviderReferenceNumber()) + "</td></tr><tr>");										
		buf.append("<td align='right'>").append(localizeMessage("requestInfo.label.serviceOrder.openedDateAndTime")).append("</td><td/>");		
		buf.append("<td>" + localizeDateTimeString(order.getOrderDate(),false)+ "</td></tr><tr>");
		buf.append("<td align='right'>").append(localizeMessage("requestInfo.label.serviceOrder.requestStatus")).append("</td><td/>");				
		buf.append("<td>" + showString(order.getStatus()) + "</td></tr><tr>");		
		/*Added for Jan Release*/
		buf.append("<td align='right'>").append(localizeMessage("requestInfo.label.serviceOrder.serialNumber")).append("</td><td/>");				
		buf.append("<td>" + showString(order.getAsset().getSerialNumber()) + "</td></tr></table></div></div>");
		/*End Add*/
		return buf.toString();
	}
	
	private String generateDeviceInformationString(Activity activity){
		StringBuffer buf = new StringBuffer();
		buf.append("<br/><div style='width:100%; overflow:hidden;' id ='bd'>");
		buf.append("<div id='st'>").append(localizeMessage("claim.label.deviceInformation")).append("</div>");
		buf.append("<div style='float:left;width: 47%;'>" +
		 	    "<table width='80%' border='0'><tr>" +
		 	    "<td align='center'><h3>" + showString(activity.getServiceRequest().getAsset().getProductLine()) + "</h3></td></tr>" +
		 	    "<tr><td align='center'><img width =\"75px\" src='" + activity.getServiceRequest().getAsset().getProductImageURL() + "'/></td></tr></table>" +
		 	    "</div><div style='float:left;width: 47%;'><br/><br/>" +
		 	    "<table height='100%'  border='0'><tr>" +
		 	    "<td width='150px' align='right'>").append(localizeMessage("claim.label.serialNumber")).append("</td>" +
		 	    "<td width='10px' /><td>" + showString(activity.getServiceRequest().getAsset().getSerialNumber()) + "</td></tr><tr>" +
		 	    "<td align='right'>").append(localizeMessage("claim.label.machineTypeModel")).append("</td><td/>" +
		 	    "<td >" + showString(activity.getServiceRequest().getAsset().getModelNumber()) + "</td></tr><tr>" +
		 	    "<td align='right'>").append(localizeMessage("claim.label.productPN/TLI")).append("</td><td/>" +
		 	    "<td>" + showString(activity.getServiceRequest().getAsset().getProductTLI()) + "</td></tr></table></div></div>");
		return buf.toString();
	}
	
	private String generateServiceHistoryString(List<Activity> serviceHistoryList){
		StringBuffer buf = new StringBuffer();
		buf.append("<br/><div style='width:100%; overflow:hidden;' id ='bd'>");
		buf.append("<div id='st'>").append(localizeMessage("claim.label.serviceHistory")).append("</div>" +
 		"<div style='padding: 10px 5px 20px 5px; '>" +
 		"<table width='100%' cellspacing='0px' class='dir' style='text-align: left;'>" +
 				"<tbody>").append(localizeGridHeadMessage("claim.headerList.serviceHistory"));
 				
		for(Activity serviceHistoryActivity : serviceHistoryList){
			//TODO
			buf.append("<tr>" +
			 				"<td>" + localizeDateTimeString(serviceHistoryActivity.getServiceRequest().getServiceRequestDate(),false) + "</td>" +
			 				"<td>" + showString(serviceHistoryActivity.getServiceRequest().getServiceRequestNumber()) + "</td>" +
			 				"<td>" + showString(serviceHistoryActivity.getServiceRequest().getServiceRequestType().getName()) + "</td>" +
			 				"<td>" + showString(serviceHistoryActivity.getActivityStatus().getName()) + "</td>" +
			 				"<td>" + showString(serviceHistoryActivity.getActivitySubStatus().getName()) + "</td>" +
			 				"<td>" + showString(serviceHistoryActivity.getServiceRequest().getServiceRequestor()) + "</td>" +
			 				"<td>" + showString(serviceHistoryActivity.getActualFailureCode().getName()) + "</td>" +
			 				"</tr>");
		 }
		 buf.append("</tbody>" +
			 				"</table>" +
			 				"</div>" +
			 		"</div>");
		return buf.toString();
	}
	
	private String generateCustomerInformationString(Activity activity){
		StringBuffer buf = new StringBuffer();
		String addressString = activity.getNewCustomerAddressCombined();
		if(StringUtil.isStringEmpty(addressString)){
			addressString = generateAddressString(activity.getServiceRequest().getAsset().getInstallAddress());
		}else{
			addressString = addressString.replaceAll("\r", " ").replaceAll("\n", "<br/>");
		}
		 
		AccountContact primaryContact = activity.getServiceRequest().getPrimaryContact();
		if(primaryContact == null){
			primaryContact = new AccountContact();
		}
		 buf.append("<br/><div style='width:100%; overflow:hidden;' id ='bd'>" +
			 		"<div id='st'>").append(localizeMessage("claim.label.customerInformation")).append("</div>" +
			 		"<div style='padding: 10px 5px 20px 5px; float:left;widtd: 33%;'>" +
			 		"<table border='0'>" +
			 			"<tbody>" +
			 			"<tr>" +
			 			"<td></td>" +
			 			"<td width='10px' rowspan='3'> </td><td><b><u>").append(localizeMessage("claim.label.customer")).append("</u></b></td></tr>" +
			 			"<tr><td align='right'><b>").append(localizeMessage("claim.label.name")).append("</b></td>" +
			 			"<td>" + showString(activity.getCustomerAccount().getAccountName()) + "</td>" +
			 			"</tr>" +
			 			"<tr>" +
			 			"<td valign='top' align='right' rowspan='2'>" +
			 			"<b>").append(localizeMessage("claim.label.address")).append("</b>" +
			 			"</td>" +
			 			"<td rowspan='2'> " + addressString + "</td>" +
			 			"</tr>" +
			 			"</tbody>" +
			 			"</table>" +
			 			"</div>" +
			 			"<div style='padding: 10px 5px 20px 5px; float:left;width: 33%;'>" +
			 			"<table border='0'>" +
			 			"<tbody>" +
			 			"<tr>" +
			 			"<td> </td>" +
			 			"<td width='10px' rowspan='4'> </td>" +
			 			"<td><b><u>").append(localizeMessage("claim.label.primaryContact")).append("</u></b></td></tr>" +
			 			"<tr>" +
			 			"<td align='right'>" +
			 			"<b>").append(localizeMessage("claim.label.name")).append("</b>" +
			 			"</td>" +
			 			"<td>" + showString(primaryContact.getFirstName()) + " " + showString(primaryContact.getLastName()) + "</td>" +
			 			"</tr>" +
			 			"<tr>" +
			 			"<td align='right'>" +
			 			"<b>").append(localizeMessage("claim.label.phone")).append("</b>" +
			 			"</td>" +
			 			"<td>" + showString(primaryContact.getWorkPhone()) + "</td></tr>" +
			 			"<tr>" +
			 			"<td valign='top' align='right'>" +
			 			"<b>").append(localizeMessage("claim.label.email")).append("</b>" +
			 			"</td>" +
			 			"<td valign='top'>" + showString(primaryContact.getEmailAddress()) + "</td>" +
			 			"</tr>" +
			 			"</tbody></table></div></div>");
		return buf.toString();
	}
	
	//Added By MPS Offshore Team for Delivery Information generation
	private String generateDeliveryInformationString(Order order){
		StringBuffer buf = new StringBuffer();
		
		String addressString = generateAddressStringForOrder(order.getShippingAddress());
		
		//Changes
		//AccountContact primaryContact = order.getCustomerContact();
		AccountContact primaryContact = order.getPrimaryContact();
		//Ends
		if(primaryContact == null){
			primaryContact = new AccountContact();
		}
		//Changes
		//AccountContact secondaryContact = order.getCustomerContact();
		AccountContact secondaryContact = order.getSecondaryContact();
		//Ends
		if(secondaryContact == null){
			secondaryContact = new AccountContact();
		}
		 buf.append("<br/><div style='width:100%; overflow:hidden;' id ='bd'>" +
			 		"<div id='st'>").append(localizeMessage("requestInfo.heading.serviceOrder.deliveryInformation")).append("</div>" +
			 		"<div style='padding: 10px 5px 20px 5px; float:left;widtd: 33%;'>" +
			 		"<table border='0'>" +
			 			"<tbody>" +
			 			"<tr>" +
			 			"<td></td>" +
			 			"<td width='10px' rowspan='3'> </td><td><b><u>").append(localizeMessage("requestInfo.heading.serviceOrder.shipToAddress")).append("</u></b></td></tr>" +
			 			"<tr>" +
			 			"<td valign='top' align='right' rowspan='2'>" +
			 			"<b>").append(localizeMessage("claim.label.address")).append("</b>" +
			 			"</td>" +
			 			"<td rowspan='2'> " + addressString + "</td>" +
			 			"</tr>" +
			 			"</tbody>" +
			 			"</table>" +
			 			"</div>" +
			 			"<div style='padding: 10px 5px 20px; float:left;width: 33%;'>" +
			 			"<table border='0'>" +
			 			"<tbody>" +
			 			"<tr>" +
			 			"<td> </td>" +
			 			"<td width='10px' rowspan='4'> </td>" +
			 			"<td><b><u>").append(localizeMessage("claim.label.primaryContact")).append("</u></b></td></tr>" +
			 			"<tr>" +
			 			"<td align='right'>" +
			 			"<b>").append(localizeMessage("claim.label.name")).append("</b>" +
			 			"</td>" +
			 			"<td>" + showString(primaryContact.getFirstName()) + " " + showString(primaryContact.getLastName()) + "</td>" +
			 			"</tr>" +
			 			"<tr>" +
			 			"<td align='right'>" +
			 			"<b>").append(localizeMessage("claim.label.phone")).append("</b>" +
			 			"</td>" +
			 			"<td>" + showString(primaryContact.getWorkPhone()) + "</td></tr>" +
			 			"<tr>" +
			 			"<td valign='top' align='right'>" +
			 			"<b>").append(localizeMessage("claim.label.email")).append("</b>" +
			 			"</td>" +
			 			"<td valign='top'>" + showString(primaryContact.getEmailAddress()) + "</td>" +
			 			"</tr>" +
			 			"</tbody></table></div>"+
			 			"<div style='padding: 10px 5px 20px 5px; float:left;width: 33%;'>" +
			 			"<table border='0'>" +
			 			"<tbody>" +
			 			"<tr>" +
			 			"<td> </td>" +
			 			"<td width='10px' rowspan='4'> </td>" +
			 			"<td><b><u>").append(localizeMessage("requestInfo.heading.serviceOrder.secondaryContact")).append("</u></b></td></tr>" +
			 			"<tr>" +
			 			"<td align='right'>" +
			 			"<b>").append(localizeMessage("claim.label.name")).append("</b>" +
			 			"</td>" +
			 			"<td>" + showString(secondaryContact.getFirstName()) + " " + showString(secondaryContact.getLastName()) + "</td>" +
			 			"</tr>" +
			 			"<tr>" +
			 			"<td align='right'>" +
			 			"<b>").append(localizeMessage("claim.label.phone")).append("</b>" +
			 			"</td>" +
			 			"<td>" + showString(secondaryContact.getWorkPhone()) + "</td></tr>" +
			 			"<tr>" +
			 			"<td valign='top' align='right'>" +
			 			"<b>").append(localizeMessage("claim.label.email")).append("</b>" +
			 			"</td>" +
			 			"<td valign='top'>" + showString(secondaryContact.getEmailAddress()) + "</td>" +
			 			"</tr>" +"</tbody></table></div>" +"<div style=' float: left; width: 100%;'><hr></hr></div>"+
			 			"<br/><div style='padding: 10px 140px  20px ; float: left; width: 33%;'><table border='0'>"+
			 			/*"<tr><td><b>"+localizeMessage("requestInfo.label.serviceOrder.specialInstructions")+"</b></td><td>"+ 
			 				showString(order.getSpecialInstructions()) +"</td></tr>"+*/
			 			"<tr><td><b>"+localizeMessage("requestInfo.label.serviceOrder.requestedDeliveryDate")+"</b></td><td>"+
			 				showString(order.getRequestedDeliveryDate()==null ? "":order.getRequestedDeliveryDate().toString())+"</td></tr>"+
			 			"<tr><td><b>"+localizeMessage("requestInfo.label.serviceOrder.mustResolveBy")+"</b></td><td>"+
			 				showString(order.getMustResolveBy())+"</td></tr>"+
			 			"<tr><td><b>"+localizeMessage("requestInfo.label.serviceOrder.specialHandling")+"</b></td><td>"+
			 				showString(order.getSpecialHandling())+"</td></tr>"+
			 			"<tr><td><b>"+localizeMessage("requestInfo.label.serviceOrder.insideLocation")+"</b></td><td>"+
			 				showString(order.getInsideLocation())+"</td></tr>"+
			 			"<tr><td><b>"+localizeMessage("requestInfo.label.serviceOrder.attentionTo")+"</b></td><td>"+
			 				showString(order.getAttentionTo())+"</td></tr>"+
			 			"<tr><td><b>"+localizeMessage("requestInfo.label.serviceOrder.assetIdentifier")+"</b></td><td>"+
			 				showString(order.getAssetIdentifier())+"</td></tr>"+
			 			"</table></div>"+"</div>");
		return buf.toString();
	}
	
	private String generateProblemInformationString(Activity activity){
		StringBuffer buf = new StringBuffer();
		buf.append("<br/><div id ='bd'><div id='st' style='background-color: #009ACF'><b>").append(localizeMessage("claim.label.problemInformation")).append("</b></div>" +
		 		"<div style='width: 100%;' >" +
		 		"<table width='80%'>" +
		 				"<tbody>" +
		 				"<tr>" +
		 				"<td>" +
		 				"<b>").append(localizeMessage("claim.label.problemCode")).append("</b>" +
		 				"</td>" +
		 				"</tr>" +
		 				"<tr>" +
		 				"<td height='5px'></td>" +
		 				"</tr>" +
		 				"<tr>" +
		 				"<td>" + showString(activity.getActualFailureCode().getName()) + "</td>" +
		 				"</tr>" +
		 				"<tr>" +
		 				"<td height='10px'></td>" +
		 				"</tr>" +
		 				"<tr>" +
		 				"<td>" +
		 				"<b>").append(localizeMessage("claim.label.problemDetails")).append("</b>" +
		 				"</td>" +
		 				"</tr>" +
		 				"<tr>" +
		 				"<td height='5px'></td>" +
		 				"</tr>" +
		 				"<tr>" +
		 				"<td>" + showString(activity.getServiceRequest().getProblemDescription()) + "</td>" +
		 				"</tr>" +
		 				"<tr>" +
		 				"<td height='10px'></td>" +
		 				"</tr>" +
		 				"<tr>" +
		 				"<td>" +
		 				"<b>").append(localizeMessage("claim.label.serviceProviderReferenceNumber")).append("</b>" +
		 				"</td>" +
		 				"</tr>" +
		 				"<tr>" +
		 				"<td height='5px'></td>" +
		 				"</tr>" +
			 			"<tr>" +
			 			"<td>" + showString(activity.getServiceProviderReferenceNumber()) + "</td>" +
			 			"</tr>" +
			 			"<tr>" +
			 			"<td height='10px'></td>" +
			 			"</tr>");
	 
		if(!StringUtil.isStringEmpty(activity.getReviewComments())){
			 buf.append("<tr>" +
		 				"<td>" +
		 				"<b>").append(localizeMessage("claim.label.reviewComments")).append("</b>" +
		 				"</td>" +
		 				"</tr>" +
		 				"<tr>" +
		 				"<td height='5px'></td>" +
		 				"</tr>" +
			 			"<tr>" +
			 			"<td>" + activity.getReviewComments() + "</td>" +
			 			"</tr>" +
			 			"<tr>" +
			 			"<td height='10px'></td>" +
			 			"</tr>");
		}
		 
		buf.append("</tbody>" +
				 	"</table>" +
				 	"</div></div>");
		
		return buf.toString();
	}
	
	private String generateTecnicianString(Activity activity){
		if(activity.getTechnician() == null){
			return "";
		}
		StringBuffer buf = new StringBuffer();
		buf.append("<br/><div id ='bd'>" +
			 		"<div id='st'>").append(localizeMessage("claim.label.technicianInformation")).append("</div>" +
			 		"<div id='tf' style='padding: 10px 5px 20px 5px; '>" +
			 		"<b>").append(localizeMessage("claim.label.technician")).append("</b>" +
			 		activity.getTechnician().getLastName() +
			 		" , " + activity.getTechnician().getFirstName() +
			 		"</div>" +
			 	"</div>");
		return buf.toString();
	}
	
	private String generateTecnicianInstructions(Activity activity){
		System.out.println("inside generateTecnicianInstructions"+activity.getActivityServiceInstructions());
		if(activity.getActivityServiceInstructions() == null){
			return "";
		}
		StringBuffer buf = new StringBuffer();
		buf.append("<br/><div id ='bd'>" +
			 		"<div id='st'>").append(localizeMessage("claim.label.technicianInstructions")).append("</div>" +
			 		"<div id='tf' style='padding: 10px 5px 20px 5px; '>" +
			 		"<b>").append("</b>" +
			 		activity.getActivityServiceInstructions()+
			 		"</div>" +
			 	"</div>");
		return buf.toString();
	}
	
	private String generateDebriefClaimString(Debrief debrief){
		StringBuffer buf = new StringBuffer();
		buf.append("<br/><div id ='bd'>" +
			 		"<div id='st'>").append(localizeMessage("claim.label.debriefInformation")).append("</div>" +
			 		"<div style='padding: 5px 5px 5px 5px; width: 100%;' >" +
			 		"<table width='80%'>" +
			 				"<tbody>" +
			 				"<tr><td>" +
			 				"<b>").append(localizeMessage("claim.label.repairDescription")).append("</b>" +
			 				"</td></tr>" +
			 				"<tr>" +
			 				"<td height='5px'></td>" +
			 				"</tr>" +
			 				"<tr>" +
			 				"<td>"+ showString(debrief.getRepairDescription()) + "</td>" +
			 				"</tr>" +
			 				"<tr>" +
			 				"<td height='10px'></td>" +
			 				"</tr>" +
			 				"<tr>" +
			 				"<td>" +
			 				"<b>").append(localizeMessage("claim.label.resolutionCode")).append("</b>" +
			 				"</td></tr>" +
			 				"<tr>" +
			 				"<td height='5px'></td>" +
			 				"</tr>" +
			 				"<tr>" +
			 				"<td>"+ showString(debrief.getResolutionCode().getName()) + "</td>" +
			 				"</tr>" +
			 				"<tr>" +
			 				"<td height='10px'></td>" +
			 				"</tr><tr>" +
			 				"<td>" +
			 				"<b>").append(localizeMessage("claim.label.dateServiced(Start)")).append("</b>" +
			 				"</td>" +
			 				"</tr>" +
			 				"<tr>" +
			 				"<td height='5px'></td>" +
			 				"</tr><tr>" +
			 				"<td>"+ showString(localizeDateTimeString(debrief.getServiceStartDate(),false)) + "</td>" +
			 				"</tr><tr>" +
			 				"<td height='10px'></td>" +
			 				"</tr>" +
			 				"<tr><td>" +
			 				"<b>").append(localizeMessage("claim.label.dateServiced(End)")).append("</b>" +
			 				"</td>" +
			 				"</tr><tr>" +
			 				"<td height='5px'></td>" +
			 				"</tr><tr>" +
			 				"<td>"+ showString(localizeDateTimeString(debrief.getServiceEndDate(),false)) + "</td>" +
			 				"</tr><tr>" +
			 				"<td height='10px'></td>" +
			 				"</tr><tr>" +
			 				"<td>" +
			 				"<b>").append(localizeMessage("claim.label.printerWorkingCondition")).append("</b>" +
			 				"</td>" +
			 				"</tr><tr>" +
			 				"<td height='5px'></td>" +
			 				"</tr><tr>" +
			 				"<td>"+ showString(debrief.getDeviceCondition().getName()) + "</td>" +
			 				"</tr><tr>" +
			 				"<td height='10px'></td>" +
			 				"</tr>" +
			 				"</tbody>" +
			 				"</table>" +
			 				"</div></div>");
		return buf.toString();
	}

	
	private String generatePendingPartListString(List<PartLineItem> partList){
		 StringBuffer buf = new StringBuffer();
		 buf.append("<br/><div  style='padding: 0 5px 10px 5px; '><div><h4>").append(localizeMessage("claim.label.requestedParts")).append("</h4>" +
			 		"<div style='width: 100%;' >" +
			 		"<table width='100%' cellspacing='0px' class='dir' style='text-align: left;'>" +
			 				"<tbody>").append(localizeGridHeadMessage("claim.headerList.partsAndTools.detail"));
		 
		for(PartLineItem partLineItem : partList){
			 buf.append("<tr>" +
				 				"<td>" + localizeDateTimeString(partLineItem.getRequestedDate(),false) + "</td>" +
				 				"<td>" + showString(partLineItem.getPartNumber()) +"</td>" +
				 				"<td>" + showString(partLineItem.getPartName()) +"</td>" +
				 				"<td>" + partLineItem.getQuantity() + "</td>" +
				 				"<td>" + localizeBooleanMessage(partLineItem.isReturnRequiredFlag()) + "</td>" +
				 				"</tr>");
		}
		 buf.append("</tbody>" +
	 				"</table>" +
	 				"</div></div></div>");
		return buf.toString();
	}
	
	private String generateOrderPartListString(List<PartLineItem> partList){
		StringBuffer buf = new StringBuffer();
		buf.append("<br/><div style='padding: 0 5px 20px 5px;'><div><h4>").append(localizeMessage("claim.label.ordered.parts")).append("</h4>" +
			 		"<div style='width: 100%;' >" +
			 		"<table width='100%' cellspacing='0px' class='dir' style='text-align: left;'>" +
			 		"<tbody>").append(localizeGridHeadMessage("claim.headerList.orderedParts.detail"));
		for(PartLineItem partLineItem : partList){
			try{
				String shipdate = partLineItem.getShipDate().toString();
				StringTokenizer shipDateToken = new StringTokenizer(shipdate, ",");
				String formattedShipDate = "";
				while(shipDateToken.hasMoreElements())
				{					
					 formattedShipDate=formattedShipDate+DateLocalizationUtil.localizeDateTime(DateUtil.convertToGMTDate(shipDateToken.nextElement().toString()), false, locale);
					 formattedShipDate = formattedShipDate.concat(",");
					
				}
				formattedShipDate=formattedShipDate.substring(0, formattedShipDate.lastIndexOf(","));
			
			buf.append("<tr>" +
						"<td>" + localizeDateTimeString(partLineItem.getPartOrderedDate(),false) + "</td>" +
						"<td>" + partLineItem.getQuantity() + "</td>" +
						"<td>" + showString(partLineItem.getExpedite()) +"</td>" + //AMS 15.11 manish
						"<td>" + showString(partLineItem.getPartNumber()) +"</td>" +
						"<td>" + showString(partLineItem.getPartName()) +"</td>" +
						"<td>" + showString(partLineItem.getStatus()) +"</td>" +
						"<td>" + showString(partLineItem.getLineStatus().getName()) +"</td>" +   //manish
					    "<td>" + showString(partLineItem.getSerialNumber()) +"</td>" +
						"<td>" + formattedShipDate + "</td>" +
						"<td>" + showString(partLineItem.getCarrier().getName()) +"</td>" +
						"<td>" + showString(partLineItem.getTrackingNumber()) + "</td>" +
						 "<td>" + showString("")+"</td>"+     //manish
						"</tr>");
			}
			catch(Exception e)
			{
				
				logger.debug("In Exception"+e.getMessage());;
			}
		}
			 		
		buf.append("</tbody></table></div></div></div>");
		return buf.toString();
	}
	
	private String generateReturnPartListString(List<PartLineItem> partList){
		StringBuffer buf = new StringBuffer();
		buf.append("<br/><div  style='padding: 0 5px 20px 5px; '><div><h4>").append(localizeMessage("claim.label.parts.to.be.returned")).append("</h4>" +
		 		"<div style='width: 100%;' >" +
		 		"<table width='100%' cellspacing='0px' class='dir' style='text-align: left;'>" +
		 		"<tbody>").append(localizeGridHeadMessage("claim.headerList.partsToBeReturned"));
		 		for(PartLineItem partLineItem : partList){
					buf.append("<tr>" +
					 		"<td>" + partLineItem.getQuantity() + "</td>" +
					 		"<td>" + showString(partLineItem.getPartNumber()) +"</td>" +
					 		"<td>" + showString(partLineItem.getPartName()) +"</td>" +
					 		"<td>" + showString(partLineItem.getLineStatus().getName()) +"</td>" +
					 		"<td>" + showString(partLineItem.getSerialNumber()) +"</td>" +
					 		"<td>" + localizeDateTimeString(partLineItem.getPartReceivedDate(),false) + "</td>" +
					 		"<td>" + showString(partLineItem.getCarrier().getName()) +"</td>" +
					 		"<td>" + showString(partLineItem.getTrackingNumber()) + "</td>" +
					 		"</tr>");
				}
		 		buf.append("</tbody>" +
		 		"</table>" +
		 		"</div></div></div>");
		return buf.toString();
	}
	
	//Added By MPS Offshore Team for generate Pending Shipment Part List for showing in PDF
	private String generatePendingShipmentPartListString(List<ServiceRequestOrderLineItem> partList, Date orderDate){
		 StringBuffer buf = new StringBuffer();
		 buf.append("<br/><div  style='padding: 0 5px 10px 5px; '><div><h4>").append(localizeMessage("requestInfo.heading.serviceOrder.pendingShipment")).append("</h4>" +
			 		"<div style='width: 100%;' >" +
			 		"<table width='100%' cellspacing='0px' class='dir' style='text-align: left;'>" +
			 				"<tbody>").append(localizeGridHeadMessage("requestInfo.serviceOrder.listHeader.pendingShipment.detail"));
		 /*"<td>" + localizeDateTimeString(orderDate,false) + "</td>" +
			"<td>" + partLineItem.getPartnumber() +"</td>" +
//			"<td>" + showString(partLineItem.getPartName()) +"</td>" +
			"<td></td>" +
			"<td>" + showString(partLineItem.getOrderLineType()) +"</td>" +
			"<td>" + partLineItem.getQuantity() + "</td>" +
			"<td>" + partLineItem.getBackOrderQuantity() + "</td>" +
			"<td>" + partLineItem.getEta() + "</td>" +*/
		for(ServiceRequestOrderLineItem partLineItem : partList){
			buf.append("<tr>" +		
					 			"<td>" + localizeDateTimeString(partLineItem.getOrderedDate(),false) + "</td>" +
					 			"<td>" + showString(partLineItem.getPartnumber())+ "</td>" +
					 			"<td>" + showString(partLineItem.getPartName())+ "</td>" +
					 			"<td>" + showString(partLineItem.getPartType())+ "</td>" +
					 			"<td>" + partLineItem.getPendingQuantity()+ "</td>" +
					 			"<td>" + showString(String.valueOf(partLineItem.getBackOrderQuantity()))+ "</td>" +
					 			"<td>" + showString(partLineItem.getEta())+ "</td>" +
			 					"</tr>");
		}
		 buf.append("</tbody>" +
	 				"</table>" +
	 				"</div></div></div>");
		return buf.toString();
	}
	
	//Added By MPS Offshore Team for generate Processed Shipment Part List for showing in PDF
	private List<List<ServiceRequestOrderLineItem>> listProcessedShipments(
			List<ServiceRequestOrderLineItem> shipmentOrderLines) {
		logger.debug("in the begining");
		List<List<ServiceRequestOrderLineItem>> returnShipments = new ArrayList<List<ServiceRequestOrderLineItem>>();
		if (shipmentOrderLines != null) {
			logger.debug("Processed line is not null");
			List<ServiceRequestOrderLineItem> srOrderLineItems = new ArrayList<ServiceRequestOrderLineItem>();
			returnShipments.add(srOrderLineItems);
			logger.debug("Processed List Size" + shipmentOrderLines.size());
			for (int i = 0; i < shipmentOrderLines.size(); i++) {
				ServiceRequestOrderLineItem partLineItem = shipmentOrderLines.get(i);
				logger.debug("****************Processed Shipment line*************************");
				ObjectDebugUtil.printObjectContent(shipmentOrderLines.get(i), logger);
				logger.debug("still here");
				boolean isContaint = false;
				for (int j = 0; j < returnShipments.size(); j++) {
					for (int k = 0; k < returnShipments.get(j).size(); k++) {
						if(returnShipments.get(j).get(k).getTrackingNumber()!=null)
						{
							if (returnShipments.get(j).get(k).getTrackingNumber().equals(partLineItem.getTrackingNumber()))
								isContaint = true;
						}
						
					}
					logger.debug("outside for");
					if (returnShipments.size() == 0
							|| j == (returnShipments.size() - 1)) {
						if (isContaint == false) {
							List<ServiceRequestOrderLineItem> srOrderLineItem = new ArrayList<ServiceRequestOrderLineItem>();
							srOrderLineItem.add(partLineItem);
							returnShipments.add(srOrderLineItem);
							logger.info("inside iscontaint false");
							break;
						}
					}
					if (isContaint) {
						returnShipments.get(j).add(partLineItem);
						break;
					}
				}
				logger.debug("inside for");
			}
			logger.debug("finally out");
			returnShipments.remove(0);
		}
		return returnShipments;
	}
	
	
	//Added By MPS Offshore Team for generate Processed Shipment Part List for showing in PDF
	private String generateProcessedPartListString(List<ServiceRequestOrderLineItem> partList){		 
		 List<List<ServiceRequestOrderLineItem>> processedPartList = listProcessedShipments(partList);
		 StringBuffer buf = new StringBuffer();
		 buf.append("<br/><div  style='padding: 0 5px 10px 5px; '><div><h4>").append(localizeMessage("requestInfo.heading.serviceOrder.processedParts")).append("</h4>" +
			 		"<div style='width: 100%;' >" +
			 		"<table width='100%' cellspacing='0px' class='dir' style='text-align: left;'>" +
			 				"<tbody>").append(localizeGridHeadMessage("requestInfo.serviceOrder.listHeader.processedParts.pdf.detail"));
		 int listSize = processedPartList.size();
		 ServiceRequestOrderLineItem partLineItem = null;
		 int i=0;
		 
		 for(List<ServiceRequestOrderLineItem> processedList:processedPartList)
		 {
			 int miniListSize = processedList.size();
			 partLineItem = processedList.get(0);
			 if(miniListSize > 1)
			 {
				 buf.append("<tr>" +
			 				"<td rowspan=\""+miniListSize+"\">" + showString(processedList.get(0).getTrackingNumber()) + "</td>"); 
				for(int j=0;j<miniListSize;j++)
				{
					if(j>0)
					{
						buf.append("<tr>");
					}
					buf.append("<td>"+showString(processedList.get(j).getPartnumber())+"</td>");
					buf.append("<td>"+showString(processedList.get(j).getPartName())+"</td>");
					buf.append("<td>"+showString(processedList.get(j).getPartType())+"</td>");
					buf.append("<td>"+processedList.get(j).getShippedQuantity()+"</td>");
					buf.append(
				 				"<td>" + showString(processedList.get(j).getCarrier()) +"</td>" +
				 				"<td>" + showString(processedList.get(j).getStatus()) +"</td>" +
				 				"<td>" + localizeDateTimeString(processedList.get(j).getActualShipDate(),false) + "</td>" +
				 				"<td>" + localizeDateTimeString(processedList.get(j).getActualDeliveryDate(),false) + "</td>" +
				 				"</tr>");
				}
				 
			 }
			 else
			 {
				 buf.append("<tr>" +
			 				"<td>" + showString(partLineItem.getTrackingNumber()) + "</td>");
				 buf.append("<td>"+showString(partLineItem.getPartnumber())+"</td>");
				 buf.append("<td>"+showString(partLineItem.getPartName())+"</td>");
				 buf.append("<td>"+showString(partLineItem.getPartType())+"</td>");
				 buf.append("<td>"+partLineItem.getShippedQuantity()+"</td>");
				 buf.append(
			 				"<td>" + showString(partLineItem.getCarrier()) +"</td>" +
			 				"<td>" + showString(partLineItem.getStatus()) +"</td>" +
			 				"<td>" + localizeDateTimeString(partLineItem.getActualShipDate(),false) + "</td>" +
			 				"<td>" + localizeDateTimeString(partLineItem.getActualDeliveryDate(),false) + "</td>" +
			 				"</tr>");
			 }
			 
		 }
		 buf.append("</tbody>" +
	 				"</table>" +
	 				"</div></div></div>");
		 logger.info(buf.toString());
		return buf.toString();
	}
	
	private String generateAdditionalPaymentRequestListString(List<AdditionalPaymentRequest> list){
		 StringBuffer buf = new StringBuffer();
		 buf.append("<br/><div style='width:100%; overflow:hidden;' id ='bd'>" +
			 		"<div id='st'>").append(localizeMessage("claim.label.additionalPaymentRequests")).append("</div>" +
			 		"<div style='padding: 10px 5px 20px 5px; '>" +
			 		"<table width='100%' cellspacing='0px' class='dir' style='text-align: left;'>" +
			 				"<tbody>").append(localizeGridHeadMessage("claim.headerList.additionalPaymentRequests"));
		 
		 for(AdditionalPaymentRequest additionalPaymentRequest : list){
			 buf.append("<tr>" +
		 				"<td>" + showString(additionalPaymentRequest.getPaymentType().getName()) + "</td>" +
		 				"<td>" + additionalPaymentRequest.getQuantity() + "</td>" +
		 				"<td>" + additionalPaymentRequest.getUnitPrice() + "</td>" +
		 				"<td>" + additionalPaymentRequest.getTotalAmount() + "</td>" +
		 				"<td>" + showString(additionalPaymentRequest.getPaymentCurrency()) + "</td>" +
		 				"<td>" + showString(additionalPaymentRequest.getDescription()) + "</td>" +
			 		"</tr>");
		 }
		 buf.append("</tbody>" +
			 				"</table>" +
			 				"</div>" +
			 				"</div>");
		return buf.toString();
	}
	private String generateNoteListString(List<ActivityNote> list){
		StringBuffer buf = new StringBuffer();
		buf.append("<br/><div style='width:100%; overflow:hidden;' id ='bd'>" +
				"<div id='st'>").append(localizeMessage("claim.label.notes")).append("</div>" +
				"<div style='padding: 10px 5px 20px 5px; '>" +
				"<table width='100%' cellspacing='0px' class='dir' style='text-align: left;'>" +
				"<tbody>").append(localizeGridHeadMessage("claim.headerList.notes.detail"));
		String noteDetail;
		for(ActivityNote activityNote : list){
			noteDetail = activityNote.getNoteDetails();
			if(StringUtil.isStringEmpty(noteDetail)){
				noteDetail = "";
			}else{
				noteDetail = noteDetail.replaceAll("\r", " ").replaceAll("\n", "<br/>");
			}
			
			buf.append("<tr>" +
					"<td>" + localizeDateTimeString(activityNote.getNoteDate(),false) + "</td>" +
					"<td>" + showString(activityNote.getNoteAuthor().getFirstName()) + " " + showString(activityNote.getNoteAuthor().getLastName()) + "</td>" +
					"<td>" + noteDetail + "</td>" +
					"</tr>");
		 }
		 buf.append("</tbody>" +
				"</table>" +
				"</div>" +
				"</div>");
		return buf.toString();
	}
	private String generateEmailNotificationsListString(List<ServiceRequestActivity> list){
		StringBuffer buf = new StringBuffer();
		buf.append("<br/><div style='width:100%; overflow:hidden;' id ='bd'>" +
				"<div id='st'>").append(localizeMessage("claim.label.emailNotifications")).append("</div>" +
				"<div style='padding: 10px 5px 20px 5px; '>" +
				"<table width='100%' cellspacing='0px' class='dir' style='text-align: left;'>" +
						"<tbody>").append(localizeGridHeadMessage("claim.headerList.emailNotifications"));
		String description;
		for(ServiceRequestActivity serviceRequestActivity : list){
			description = serviceRequestActivity.getActivityDescription();
			if(StringUtil.isStringEmpty(description)){
				description = "";
			}else{
				description = description.replaceAll("\r", " ").replaceAll("\n", "<br/>");
			}
			
			buf.append("<tr>" +
					"<td>" + localizeDateTimeString(serviceRequestActivity.getActivityDate(),false) + "</td>" +
					"<td>" + showString(serviceRequestActivity.getRecipientEmail()) + "</td>" +
					"<td>" + description + "</td>" +
					"</tr>");
		 }
		 buf.append("</tbody>" +
					"</table>" +
					"</div>" +
					"</div>");
		
		return buf.toString();
	}
	private String generateRequestInformationString(Activity activity){
		StringBuffer buf = new StringBuffer();
		 buf.append("<div id ='bd'>" +
			 		"<div id='st'>").append(localizeMessage("claim.label.RequestInformation")).append("</div>" +
			 		"<div><table>" +
			 		"<tbody>" +
			 		"<tr height='25px'>" +
			 				"<td align='right'>" +
			 				"<b>").append(localizeMessage("claim.label.requestNumber")).append("</b></td>" +
			 				"<td width='20px'></td>" +
			 				"<td>" + showString(activity.getServiceRequest().getServiceRequestNumber()) + "</td></tr>" +
			 				"<tr height='25px'>" +
			 				"<td align='right'>" +
			 			    "<b>").append(localizeMessage("claim.label.serviceProviderReferenceNumber")).append("</b></td>" +
			 			    "<td width='20px'></td>" +
			 			    "<td>" + showString(activity.getServiceRequest().getReferenceNumber()) + "</td></tr>" +
			 			    "<tr height='25px'>" +
			 			    "<td align='right'>" +
			 			    "<b>").append(localizeMessage("claim.label.openedDateTime")).append("</b></td>" +
			 			    "<td width='20px'></td>" +
			 			    "<td>" + localizeDateTimeString(activity.getServiceRequest().getServiceRequestDate(),true) + "</td></tr>" +
			 			    "<tr height='25px'>" +
			 			    "<td align='right'>" +
			 			    "<b>").append(localizeMessage("claim.label.requestStatus")).append("</b></td>" +
			 			    "<td width='20px'></td>" +
			 			    "<td>" + showString(activity.getActivityStatus().getName()) + "</td></tr>" +
			 			    "<tr height='25px'>" +
			 			    "<td align='right'>" +
			 			    "<b>").append(localizeMessage("claim.label.statusDetail")).append("</b></td>" +
			 			    "<td width='20px'></td>" +
			 			    "<td>" + showString(activity.getActivitySubStatus().getName()) + "</td></tr>" +
			 "</tbody></table></div></div>");
		return buf.toString();
	}
	
	private String generateRequestCustomerInformationString(Activity activity){
		StringBuffer buf = new StringBuffer();
		
		String addressString = activity.getNewCustomerAddressCombined();
			if(StringUtil.isStringEmpty(addressString)){
				addressString = generateAddressString(activity.getCustomerAccount().getAddress());
			}else{
				addressString = addressString.replaceAll("\r", " ").replaceAll("\n", "<br/>");
			}
		
		AccountContact primaryContact = activity.getServiceRequest().getPrimaryContact();
		if(primaryContact == null){
			primaryContact = new AccountContact();
		}
		AccountContact secondaryContact = activity.getServiceRequest().getSecondaryContact();
		if(secondaryContact == null){
			secondaryContact = new AccountContact();
		}
			
		buf.append("<br/><div style='width:100%; overflow:hidden;' id ='bd'>" +
			 		"<div id='st'>").append(localizeMessage("claim.label.customerInformation")).append("</div>" +
			 		"<div style='padding: 10px 5px 20px 5px; float:left;width: 30%;'>" +
			 		"<table><tbody>" +
			 		"<tr><td></td>" +
			 		"<td width='20px'></td><td>" +
			 		"<b><u>").append(localizeMessage("claim.label.customerAccount")).append("</u></b></td>" +
			 		"</tr><tr>" +
			 		"<td align='right'>" +
			 		"<b>").append(localizeMessage("claim.label.name")).append("</b></td>" +
			 		"<td width='20px'></td>" +
			 		"<td>" + showString(activity.getCustomerAccount().getAccountName()) + "</td>" +
			 		"</tr><tr>" +
			 		"<td align='right' valign='top'>" +
			 		"<b>").append(localizeMessage("claim.label.address")).append("</b></td>" +
			 		"<td width='20px'></td>" +
			 		"<td valign='bottom'>" + addressString + "</td>" +
			 		"</tr><tr>" +
			 		"<td align='right' valign='top'>" +
			 		"<b>").append(localizeMessage("claim.label.helpDeskReference")).append("</b></td>" +
			 		"<td width='20px'></td>" +
			 		"<td valign='bottom'>" + showString(activity.getServiceRequest().getCustomerReferenceNumber()) + "</td></tr>" +
			 		"</tbody></table>" +
			 		"</div>" +
			 			"<div style='padding: 10px 5px 20px 5px; float:left;width: 30%;'>" +
			 			"<table border='0'>" +
			 			"<tbody>" +
			 			"<tr>" +
			 			"<td> </td>" +
			 			"<td width='10px' rowspan='4'> </td>" +
			 			"<td><b><u>").append(localizeMessage("claim.label.primaryContact")).append("</u></b></td></tr>" +
			 			"<tr>" +
			 			"<td align='right'>" +
			 			"<b>").append(localizeMessage("claim.label.name")).append("</b>" +
			 			"</td>" +
			 			"<td>" + showString(primaryContact.getFirstName()) + " " + showString(primaryContact.getLastName()) + "</td>" +
			 			"</tr>" +
			 			"<tr>" +
			 			"<td align='right'>" +
			 			"<b>").append(localizeMessage("claim.label.phone")).append("</b>" +
			 			"</td>" +
			 			"<td>" + showString(primaryContact.getWorkPhone()) + "</td></tr>" +
			 			"<tr>" +
			 			"<td valign='top' align='right'>" +
			 			"<b>").append(localizeMessage("claim.label.email")).append("</b>" +
			 			"</td>" +
			 			"<td valign='top'>" + showString(primaryContact.getEmailAddress()) + "</td>" +
			 			"</tr>" +
			 			"</tbody></table></div>" +
			 			"<div style='padding: 10px 5px 20px 5px;float:left;width: 30%;'>" +
			 			"<table >" +
			 			"<tbody>" +
			 			"<tr>" +
			 			"<td></td>" +
			 			"<td width='20px'></td>" +
			 			"<td><u><b>").append(localizeMessage("claim.label.secondaryContact")).append("</b></u></td></tr>" +
			 			"<tr>" +
			 			"<td align='right'>" +
			 			"<b>").append(localizeMessage("claim.label.name")).append("</b></td>" +
			 			"<td width='20px'></td>" +
			 			"<td>" + showString(secondaryContact.getFirstName()) + " " + showString(secondaryContact.getLastName()) + "</td></tr>" +
			 			"<tr>" +
			 			"<td align='right' style='top: 0px;'>" +
			 			"<b>").append(localizeMessage("claim.label.phone")).append("</b></td>" +
			 			"<td width='20px'></td>" +
			 			"<td>" + showString(secondaryContact.getWorkPhone()) + "</td>" +
			 			"</tr><tr>" +
			 			"<td align='right' style='top: 0px;'>" +
			 			"<b>").append(localizeMessage("claim.label.email")).append("</b></td>" +
			 			"<td width='20px'></td>" +
			 			"<td>" + showString(secondaryContact.getEmailAddress()) + "</td></tr>" +
			 			"</tbody></table></div>" +
			 			"</div>");
		return buf.toString();
	}
	
	private String generateRequestProblemInformationString(Activity activity){
		StringBuffer buf = new StringBuffer();
		buf.append("<br/><div id ='bd'><div id='st' style='background-color: #009ACF'><b>").append(localizeMessage("claim.label.problemInformation")).append("</b></div>" +
		 		"<div style='width: 100%;' >" +
		 		"<table width='80%'>" +
		 				"<tbody>" +
		 				"<tr>" +
		 				"<td>" +
		 				"<b>").append(localizeMessage("claim.label.problemCode")).append("</b>" +
		 				"</td>" +
		 				"</tr>" +
		 				"<tr>" +
		 				"<td height='5px'></td>" +
		 				"</tr>" +
		 				"<tr>" +
		 				"<td>" + showString(activity.getActualFailureCode().getName()) + "</td>" +
		 				"</tr>" +
		 				"<tr>" +
		 				"<td height='10px'></td>" +
		 				"</tr>" +
		 				"<tr>" +
		 				"<td>" +
		 				"<b>").append(localizeMessage("claim.label.problemDetails")).append("</b>" +
		 				"</td>" +
		 				"</tr>" +
		 				"<tr>" +
		 				"<td height='5px'></td>" +
		 				"</tr>" +
		 				"<tr>" +
		 				"<td>" + showString(activity.getServiceRequest().getProblemDescription()) + "</td>" +
		 				"</tr>" +
		 				"<tr>" +
		 				"<td height='10px'></td>" +
		 				"</tr>");
	 
		if(!StringUtil.isStringEmpty(activity.getReviewComments())){
			 buf.append("<tr>" +
		 				"<td>" +
		 				"<b>").append(localizeMessage("claim.label.reviewComments")).append("</b>" +
		 				"</td>" +
		 				"</tr>" +
		 				"<tr>" +
		 				"<td height='5px'></td>" +
		 				"</tr>" +
			 			"<tr>" +
			 			"<td>" + activity.getReviewComments() + "</td>" +
			 			"</tr>" +
			 			"<tr>" +
			 			"<td height='10px'></td>" +
			 			"</tr>");
		}
		 
		buf.append("</tbody>" +
				 	"</table>" +
				 	"</div></div>");
		
		return buf.toString();
	}
	
	private String generateTechnicianInstructionsListString(List<TechnicianInstruction> technicianInstructions){
		StringBuffer buf = new StringBuffer();
		buf.append("<br/><div style='width:100%; overflow:hidden;' id ='bd'>" +
				"<div id='st'>").append(localizeMessage("claim.label.technicianInformation")).append("</div>" +
				"<div style='padding: 10px 5px 20px 5px; '>" +
				"<table width='100%' cellspacing='0px' class='dir' style='text-align: left;'>" +
						"<tbody>").append(localizeGridHeadMessage("claim.headerList.technicianInstructions"));
		for(TechnicianInstruction technicianInstruction : technicianInstructions){
			buf.append("<tr>" +
			 		"<td>" + localizeDateTimeString(technicianInstruction.getInstructionDate(),false) + "</td>" +
			 		"<td>" + showString(technicianInstruction.getInstructionType()) + "</td>" +
			 		"<td>" + showString(technicianInstruction.getActualInstruction()) + "</td>" +
			 		"</tr>");
		 }
		 buf.append("</tbody>" +
					"</table>" +
					"</div>" +
					"</div>");
		return buf.toString();
	}
	
	private String generateRecommendedPartsListString(List<PartLineItem> partList){
		StringBuffer buf = new StringBuffer();
		buf.append("<br/><div style='width:100%; overflow:hidden;' id ='bd'>" +
				"<div id='st'>").append(localizeMessage("claim.label.recommendedParts")).append("</div>" +
				"<div style='padding: 10px 5px 20px 5px; '>" +
				"<table width='100%' cellspacing='0px' class='dir' style='text-align: left;'>" +
						"<tbody>").append(localizeGridHeadMessage("claim.headerList.recommendedParts"));
		for(PartLineItem partLineItem : partList){
		//	String recommendedQuantity = partLineItem.getRecommendedQuantity()==null?"":partLineItem.getRecommendedQuantity();
			buf.append("<tr>" +
					"<td>" + partLineItem.getRecommendedQuantity() + "</td>" +
					"<td>" + showString(partLineItem.getPartNumber()) + "</td>" +
					"<td>" + showString(partLineItem.getPartName()) + "</td>" +
					"<td>" + localizeBooleanMessage(partLineItem.isReturnRequiredFlag()) + "</td>" +
					"</tr>");
		 }
		 buf.append("</tbody>" +
					"</table>" +
					"</div>" +
					"</div>");
		
		return buf.toString();
	}
	
	private String generateServiceInformationString(Activity activity){
		StringBuffer buf = new StringBuffer();
		 buf.append("<br/><div id ='bd'>" +
				 	"<div id='st'>").append(localizeMessage("claim.label.serviceInformation")).append("</div>" +
				 	"<div style='padding: 5px 5px 5px 5px; width: 100%;' >" +
				 	"<table ><tbody>" +
				 	"<tr height='30'>");
		 if(!StringUtil.isStringEmpty(activity.getResolutionMetric())){
			 buf.append("<td align='right' width='25%' valign='top'>" +
			 	"<b>").append(localizeMessage("claim.label.mustResolveWithin")).append("</b></td>" +
					 	"<td width='25%' valign='top' style='padding-left: 10px;'>" + showString(activity.getResolutionMetric()) + "</td>");
		 }else{
			 buf.append("<td align='right' width='25%' valign='top'>" +
				 	"<b>").append(localizeMessage("claim.label.respondWithin")).append("</b></td>" +
				 	"<td width='25%' valign='top' style='padding-left: 10px;'>" + showString(activity.getResponseMetric()) + "</td>");
		 }
		 
				 	
		 buf.append("<td align='right' width='25%' valign='top'>" +
				 	"<b>").append(localizeMessage("claim.label.serviceActivityWithin30Days")).append("</b></td>" +
				 	"<td width='25%' valign='top' style='padding-left: 10px;'>" + showString(activity.getServiceActivityWithin30Days()) + "</td>" +
				 	"</tr><tr height='30'>");
		if(activity.getResolutionDate() != null){
			 buf.append("<td align='right' width='25%' valign='top'>" +
			 	"<b>").append(localizeMessage("claim.label.mustResolveBy")).append("</b></td>" +
					 	"<td width='25%' valign='top' style='padding-left: 10px;'>" + localizeDateTimeString(activity.getResolutionDate(),true) + "</td>");
		}else{
			 buf.append("<td align='right' width='25%' valign='top'>" +
			 	"<b>").append(localizeMessage("claim.label.customerRequestedResponse")).append("</b></td>" +
					 	"<td width='25%' valign='top' style='padding-left: 10px;'>" + localizeDateTimeString(activity.getCustomerRequestedResponseDate(),true) + "</td>");
		} 
				 	
		
		buf.append("<td align='right' width='25%' valign='top'>" +
					"<b>").append(localizeMessage("claim.label.serviceProviderAttemptNumber")).append("</b></td>" +
					"<td width='25%' valign='top' style='padding-left: 10px;'>" + showString(activity.getServiceProviderAttemptNumber()) + "</td>" +
				 	"</tr><tr height='30'>" +
				 	"<td align='right' width='25%' valign='top'>" +
				 	"<b>").append(localizeMessage("claim.label.problemDescription")).append("</b></td>" +
				 	"<td width='75%' valign='top' style='padding-left: 10px;' colspan='3'>" + showString(activity.getServiceSummary()) + "</td>" +
			 	
				 	"</tr><tr height='30'>" +
			 	
				 	"<td align='right' width='25%' valign='top'>" +
				 	"<b>").append(localizeMessage("claim.label.accountSpecialHandling")).append("</b></td>" +
				 	"<td width='75%' valign='top' style='padding-left: 10px;' colspan='3'>" + showString(activity.getAccountSpecialHandling()) + "</td>" +
			 	
				 	"</tr><tr height='30'>" +
				 	"<td align='right' width='25%' valign='top'>" +
				 	"<b>").append(localizeMessage("claim.label.productAndAssetWarningMessage")).append("</b></td>" +
				 	"<td width='75%' valign='top' style='padding-left: 10px;' colspan='3'>" + showString(activity.getAssetWarningMessage()) + "</td>" +
				 	"</tr></tbody></table>" +
				 	"</div></div>");
	 
		return buf.toString();
	}
	
	private String generateCloseOutActivityString(Activity activity){
		//	").append(localizeMessage("claim.label.problemCode")).append("
		// " + partLineItem.getPartNumber() + "
		Debrief debrief = activity.getDebrief();
		Asset installedAsset = debrief.getInstalledAsset();
		
		ListOfValues travelUnitOfMeasure = debrief.getTravelUnitOfMeasure();
		if(travelUnitOfMeasure == null){
			travelUnitOfMeasure = new ListOfValues();
		}
		ListOfValues actualFailureCode = debrief.getActualFailureCode();
		if(actualFailureCode == null){
			actualFailureCode = new ListOfValues();
		}
		StringBuffer buf = new StringBuffer();
		buf.append("<br/><div id ='bd'>" +
				"<div id='st'>").append(localizeMessage("claim.label.closeOutActivity")).append("</div>" +
				"<div style='padding: 5px 5px 5px 5px; width: 100%;' >" +
				"<table ><tbody>" +
				"<tr height='30'>" +
				"<td align='right' width='20%'>" +
				"<b>").append(localizeMessage("claim.label.actualStartDate")).append("</b></td>" +
				"<td align='left' width='30%' style='padding-left: 10px;'>" + localizeDateTimeString(debrief.getServiceStartDate(),true) + "</td>" +
				"<td align='right' width='20%'>" +
				"<b>").append(localizeMessage("claim.label.resolutionCode")).append("</b></td>" +
				"<td align='left' width='30%' style='padding-left: 10px;'>" + showString(debrief.getResolutionCode().getName()) + "</td>" +
				"</tr><tr height='30'>" +
				"<td align='right'>" +
				"<b>").append(localizeMessage("claim.label.actualEndDate")).append("</b></td>" +
				"<td align='left' style='padding-left: 10px;'>" + localizeDateTimeString(debrief.getServiceEndDate(),true) + "</td>" +
				"<td align='right' valign='top' style='padding: 5px;'>" +
				"<b>").append(localizeMessage("claim.label.repairDescription")).append("</b></td>" +
				"<td align='left' valign='top' style='padding: 5px;' rowspan='4'>" + showString(debrief.getRepairDescription()) + "</td>" +
				"</tr><tr height='30'>" +
				"<td align='right'>" +
				"<b>").append(localizeMessage("claim.label.travelDistance")).append("</b></td>" +
				"<td align='left' style='padding-left: 10px;'>" + showString(debrief.getTravelDistance()) + " " + showString(travelUnitOfMeasure.getName()) + "</td>" +
				"<td></td></tr>" +
				"<tr height='30'>" +
				"<td align='right'>" +
				"<b>").append(localizeMessage("claim.label.travelDuration")).append("</b></td>" +
				"<td align='left' style='padding-left: 10px;'>" + showString(debrief.getTravelDuration()) + "</td>" +
				"</tr><tr style='height: auto;'>" +
				"<td></td><td></td><td></td></tr>" +
				"<tr height='30'>" +
				"<td align='right'>" +
				"<b>").append(localizeMessage("claim.label.problemCode")).append("</b></td>" +
				"<td align='left' style='padding-left: 10px;'>" + showString(actualFailureCode.getName()) + "</td>" +
				"<td align='right'>" +
				"<b>").append(localizeMessage("claim.label.additionalServiceRequired")).append("</b></td>" +
				"<td align='left' style='padding-left: 10px;'>" + showString(debrief.getDebriefActionStatus()) + "</td>" +
				"</tr><tr height='30'>" +
				"<td align='right' valign='top' style='padding: 5px;' rowspan='3'>" +
				"<b>").append(localizeMessage("claim.label.problemDetails")).append("</b></td>" +
				"<td align='left' valign='top' style='padding: 5px;' rowspan='3'>" + showString(debrief.getProblemDescription()) + "</td>" +
				"<td align='right'>" +
				"<b>").append(localizeMessage("claim.label.nonLexmarkSuppliesUsed")).append("</b></td>" +
				"<td align='left' style='padding-left: 10px;'>" + localizeBooleanMessage(debrief.isGenuineLexmarkSuppliesUsedFlag()) + "</td>" +
				"</tr><tr height='30'>" +
				"<td align='right'>" +
				"<b>").append(localizeMessage("claim.label.supplyManufacturer")).append("</b></td>" +
				"<td align='left' style='padding-left: 10px;'>" + showString(debrief.getSupplyManufacturer()) + "</td>" +
				"<td align='right'><br/>" +
				"</td><td align='left'><br/></td></tr>" +
				"<tr><td><br/></td><td><br/></td><td><br/></td><td><br/></td></tr>" +
				"<tr height='30'>" +
				"<td align='left' width='200' style='padding-left: 10px;' colspan='4'>" +
				"<h3>").append(localizeMessage("claim.label.repairedDeviceInformation")).append("</h3></td></tr>" +
				"<tr height='30'>" +
				"<td align='right'>" +
				"<b>").append(localizeMessage("claim.label.printerWorkingCondition")).append("</b>" +
				"</td>" +
				"<td align='left' style='padding-left: 10px;'>" + showString(debrief.getDeviceCondition().getName()) + "</td>");
				
			boolean networkConnected = true;
			if(installedAsset == null){
				if(StringUtil.isStringEmpty(activity.getServiceRequest().getAsset().getIpAddress()) && StringUtil.isStringEmpty(activity.getServiceRequest().getAsset().getMacAddress())){
					networkConnected = false;
				}
				
				buf.append("<td align='right'>"+
						"<b>").append(localizeMessage("claim.label.networkConnected")).append("</b></td>" +
						"<td align='left' style='padding-left: 10px;'>" + localizeBooleanMessage(networkConnected) + "</td>");	
			}else{
				buf.append("<td align='right'></td><td align='left' style='padding-left: 10px;'></td>");	
			}
	
			buf.append("</tr>" +
				"<tr height='30'>" +
				"<td align='right'>" +
				"<b>").append(localizeMessage("claim.label.pageCountAll")).append("</b></td>" +
				"<td align='left' style='padding-left: 10px;'>" + showString(activity.getServiceRequest().getAsset().getNewColorPageCount()) + "</td>");
			if(installedAsset == null){	
				buf.append("<td align='right'>" +
					"<b>").append(localizeMessage("claim.label.ipAddress")).append("</b></td>" +
					"<td align='left' style='padding-left: 10px;'>" + showString(activity.getServiceRequest().getAsset().getIpAddress()) + "</td>");
			}else{
				buf.append("<td align='right'></td><td align='left' style='padding-left: 10px;'></td>");	
			}	
			buf.append("</tr><tr height='30'>" +
				"<td align='right'>" +
				"<b>").append(localizeMessage("claim.label.lexmarkTag")).append("</b>" +
				"</td><td align='left' style='padding-left: 10px;'>" + showString(activity.getServiceRequest().getAsset().getDeviceTag()) + "</td>");
			if(installedAsset == null){
				buf.append("<td align='right'>" +
					"<b>").append(localizeMessage("claim.label.macAddress")).append("</b></td>" +
					"<td align='left' style='padding-left: 10px;'>" + showString(activity.getServiceRequest().getAsset().getMacAddress()) + "</td>");
			}else{
				buf.append("<td align='right'></td><td align='left' style='padding-left: 10px;'></td>");	
			}
			buf.append("</tr>");
			if(installedAsset != null){	
				networkConnected = true;
				if(StringUtil.isStringEmpty(installedAsset.getIpAddress()) && StringUtil.isStringEmpty(installedAsset.getMacAddress())){
					networkConnected = false;
				}
				
				buf.append("<tr height='30'><td>" +
					"<br/></td><td><br/></td><td><br/></td><td><br/></td></tr>" +
					"<tr height='30'>" +
					"<td align='left' width='200' style='padding-left: 10px;' colspan='4'>" +
					"<h3>").append(localizeMessage("claim.label.intalledDeviceInformation")).append("</h3>" +
					"</td></tr>" +
					"<tr height='30'>" +
					"<td align='right'>" +
					"<b>").append(localizeMessage("claim.label.machineTypeModel")).append("</b></td>" +
					"<td align='left' style='padding-left: 10px;'>" + showString(installedAsset.getModelNumber()) + "</td>" +
					"<td align='right'>" +
					"<b>").append(localizeMessage("claim.label.networkConnected")).append("</b></td>" +
					"<td align='left' style='padding-left: 10px;'>" + localizeBooleanMessage(networkConnected) + "</td></tr>" +
					"<tr height='30'>" +
					"<td align='right'>" +
					"<b>").append(localizeMessage("claim.label.serialNumber")).append("</b></td>" +
					"<td align='left' style='padding-left: 10px;'>" + showString(installedAsset.getSerialNumber()) + "</td>" +
					"<td align='right'>" +
					"<b>").append(localizeMessage("claim.label.ipAddress")).append("</b></td>" +
					"<td align='left' style='padding-left: 10px;'>" + showString(installedAsset.getIpAddress()) + "</td>" +
					"</tr><tr height='30'>" +
					"<td align='right'>" +
					"<b>").append(localizeMessage("claim.label.pageCountAll")).append("</b></td>" +
					"<td align='left' style='padding-left: 10px;'>" + showString(installedAsset.getNewPageCount()) + "</td>" +
					"<td align='right'>" +
					"<b>").append(localizeMessage("claim.label.macAddress")).append("</b></td>" +
					"<td align='left' style='padding-left: 10px;'>" + showString(installedAsset.getMacAddress()) + "</td>" +
					"</tr><tr height='30'>" +
					"<td align='right'>" +
					"<b>").append(localizeMessage("claim.label.lexmarkTag")).append("</b></td>" +
					"<td align='left' style='padding-left: 10px;'>" + showString(installedAsset.getDeviceTag()) + "</td>" +
					"<td></td><td></td></tr>");
			}
			buf.append("</tbody></table></div></div>");
		return buf.toString();
	}
	
	private String generateAddressString(GenericAddress value){
		if(value == null){
			return "";
		}
		
		StringBuilder fragment = new StringBuilder();
		fragment.append(value.getAddressLine1()).append("<br/>");
		if (StringUtils.isNotEmpty(value.getAddressLine2()))
			fragment.append(value.getAddressLine2()).append("<br/>");

		if (StringUtils.isNotEmpty(value.getAddressLine3()))
			fragment.append(value.getAddressLine3()).append("<br/>");

		if(StringUtils.isNotEmpty(value.getCity())){
			fragment.append(value.getCity()).append(" ");
		}

		if (StringUtils.isNotEmpty(value.getState())) {
			fragment.append(value.getState());
			fragment.append(" ");
		} else {
			fragment.append(value.getProvince());
			fragment.append(" ");
		}
		
		if(StringUtils.isNotEmpty(value.getCountry())){
			fragment.append(value.getCountry()).append(" ");
		}
		
		fragment.append(value.getPostalCode());
		return fragment.toString();
	}
	private String generateAddressStringForOrder(GenericAddress value){
		if(value == null){
			return "";
		}
		
		StringBuilder fragment = new StringBuilder();
		if(StringUtils.isNotEmpty(value.getStoreFrontName()))
			fragment.append(value.getStoreFrontName()).append("<br/>");
		if(StringUtils.isNotEmpty(value.getAddressLine1()))
			fragment.append(value.getAddressLine1()).append("<br/>");
		
		if (StringUtils.isNotEmpty(value.getAddressLine2()))
			fragment.append(value.getAddressLine2()).append("<br/>");

		if(StringUtils.isNotEmpty(value.getCity())){
			fragment.append(value.getCity()).append(" ");
		}

		if (StringUtils.isNotEmpty(value.getState())) {
			fragment.append(value.getState());
			fragment.append(" ");
		}
		if(StringUtils.isNotEmpty(value.getProvince())) {
			fragment.append(value.getProvince());
			fragment.append(" ");
		}
		
		if(StringUtils.isNotEmpty(value.getCountry())){
			fragment.append(value.getCountry()).append(" ");
		}
		if(StringUtils.isNotEmpty(value.getPostalCode()))
			fragment.append(value.getPostalCode());
		
		return fragment.toString();
	}
	


	
	private String generatePaymentInformationString(Payment payment){
		StringBuffer buf = new StringBuffer();

		buf.append("<br/><div id ='bd'>");
		buf.append("<div id='st'>").append(localizeMessage("paymentDetail.label.PaymentInformation")).append("</div>");
		buf.append("<div style='padding: 5px 5px 5px 5px; width: 100%;'><table><tbody><tr height='30'><td align='right' width='25%' valign='top'>");
		buf.append("<b>").append(localizeMessage("paymentDetail.label.dateCreated")).append("</b></td>");
		buf.append("<td width='25%' valign='top' style='padding-left: 10px;'>" + localizeDateTimeString(payment.getDateCreated(),false) + "</td>");
		buf.append("<td align='right' width='25%' valign='top'>" );
		buf.append("<b>").append(localizeMessage("paymentDetail.label.paymentAccount")).append("</b></td>" );
		buf.append("<td width='25%' valign='top' style='padding-left: 10px;'>" + showString(payment.getPartnerAccount().getAccountName()) + "</td>" );
		buf.append("</tr><tr height='30'>" );
		buf.append("<td align='right' width='25%' valign='top'>" );
		buf.append("<b>").append(localizeMessage("paymentDetail.label.paymentNumber")).append("</b></td>" );
		buf.append("<td width='25%' valign='top' style='padding-left: 10px;'>" + showString(payment.getPaymentNumber()) + "</td>" );
		buf.append("<td align='right' width='25%' valign='top'>" );
		buf.append("<b>").append(localizeMessage("paymentDetail.label.paymentAgreement")).append("</b></td>" );
		buf.append("<td width='25%' valign='top' style='padding-left: 10px;'>" + showString(payment.getPartnerAgreement()) + "</td>" );
		buf.append("</tr><tr height='30'>" );
		buf.append("<td align='right' width='25%' valign='top'>" );
		buf.append("<b>").append(localizeMessage("paymentDetail.label.paymentStatus")).append("</b></td>" );
		buf.append("<td width='25%' valign='top' style='padding-left: 10px;'>" + showString(payment.getPaymentStatus().getName()) + "</td>" );
		buf.append("<td align='right' width='25%' valign='top'>");
		buf.append("<b>").append(localizeMessage("paymentDetail.label.payableTo")).append("</b></td>" );
		buf.append("<td width='25%' valign='top' style='padding-left: 10px;'>" + showString(payment.getPayableToName()) + "</td>" );
		buf.append("</tr><tr height='30'>" );
		buf.append("<td align='right' width='25%' valign='top'>" );
		buf.append("<b>").append(localizeMessage("paymentDetail.label.paymentTotal")).append("</b></td>" );
		buf.append("<td width='25%' valign='top' style='padding-left: 10px;'>" + payment.getPaymentTotal() + "</td>" );
		buf.append("<td align='right' width='25%' valign='top'>" );
		buf.append("<b>").append(localizeMessage("paymentDetail.label.payableAddress")).append("</b></td>" );
		buf.append("<td width='25%' valign='top' style='padding-left: 10px;'>" + generateAddressString(payment.getPartnerAccount().getAddress()) + "</td>" );
		buf.append("</tr>" );
		buf.append("</tbody></table></div></div>");		 
		 
		return buf.toString();
	}
	
	private String generatePaymentLineItemListString(List<Activity> activityList){
		StringBuffer buf = new StringBuffer();
		buf.append("<br/><div style='width:100%; overflow:hidden;' id ='bd'>" +
				"<div id='st'>").append(localizeMessage("paymentDetail.label.PaymentDetails")).append("</div>" +
				"<div style='padding: 10px 5px 20px 5px; '>" +
				"<table width='100%' cellspacing='0px' class='dir' style='text-align: left;'>" +
				"<tbody>").append(localizeGridHeadMessage("claim.headerList.paymentDetails"));
		for(Activity paymentActivity : activityList){
			buf.append("<tr>" +
					"<td>" + localizeDateTimeString(paymentActivity.getActivityDate(),true) + "</td>" +
					"<td>" + showString(paymentActivity.getServiceRequest().getServiceRequestNumber()) + "</td>" +
					"<td>" + showString(paymentActivity.getServiceProviderReferenceNumber()) + "</td>" +
					"<td>" + showString(paymentActivity.getPaymentServiceType()) + "</td>" +
					"<td>" + paymentActivity.getLaborPayment() + "</td>" +
					"<td>" + paymentActivity.getPartsPayment() + "</td>" +
					"<td>" + paymentActivity.getAdditionalPayments() + "</td>" +
					"<td>" + paymentActivity.getPartnerFee() + "</td>" +
					"<td>" + paymentActivity.getPayment().getSupplyAgreementFee() + "</td>" +
					"<td>" + paymentActivity.getTotalPayment() + "</td>" +
					"<td>" + showString(paymentActivity.getCustomerAccount().getAccountName())+ "</td>" +
					"<td>" + showString(paymentActivity.getServiceRequest().getAsset().getSerialNumber()) + "</td>" +
					"<td>" + showString(paymentActivity.getServiceRequest().getAsset().getProductTLI()) + "</td>" +
					"<td>" + showString(paymentActivity.getServiceRequest().getAsset().getModelNumber()) + "</td>" +
					"<td>" + showString(paymentActivity.getServiceRequest().getAsset().getProductLine()) + "</td>" +
					"</tr>");
		 }
		 buf.append("</tbody>" +
				"</table>" +
				"</div>" +
				"</div>");
		return buf.toString();
	}
	
	private String localizeDateTimeString(Date date ,boolean showTime){
		if(date == null){
			return "";
		}
		TimezoneUtil.adjustDate(date, (0 - timezoneOffset));
		return DateLocalizationUtil.localizeDateTime(date, showTime, locale);
	}
	
	private String showString(String value){
		if(StringUtil.isStringEmpty(value)){
			return "";
		}else{
			return value.replaceAll("&", "&amp;");
		}
	}
	private String localizeMessage(String propertyAttribute) {
		return (PropertiesMessageUtil.getPropertyMessage(LexmarkPPConstants.MESSAGE_BUNDLE_NAME, propertyAttribute, locale));
	}
	private String localizeGridHeadMessage(String propertyAttribute) {
		StringBuffer buf = new StringBuffer();
		String headStr = PropertiesMessageUtil.getPropertyMessage(LexmarkPPConstants.MESSAGE_BUNDLE_NAME, propertyAttribute, locale);
		String[] headArr = headStr.split(",");
		if(headArr != null && headArr.length >0){
			buf.append("<tr>");
			for(int i = 0 ; i < headArr.length; i++){
				if(StringUtil.isStringEmpty(headArr[i]))
					continue;
				if("&nbsp;".equals(headArr[i])){
					if(i == 0){
						continue;
					}else{
						buf.append("<th></th>");
					}
				}else{
					buf.append("<th>").append(headArr[i]).append("</th>");
				}
			}
			buf.append("</tr>");
		}
 		return buf.toString();
	}

	public void setTimezoneOffset(float timezoneOffset) {
		this.timezoneOffset = timezoneOffset;
	}
	
	private String localizeBooleanMessage(boolean value){
		String returnRequiredStr;
		if(value){
			returnRequiredStr = localizeMessage("claim.lebel.returnRequired.yes");
		}else{
			returnRequiredStr = localizeMessage("claim.lebel.returnRequired.no");
		}
		return returnRequiredStr;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}
	
}
