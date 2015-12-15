package com.lexmark.exporter;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.portlet.ResourceResponse;

import com.lexmark.constants.LexmarkPPConstants;

public interface DataExporter {

	// key:dataType,value:the label name in the message.properties
	public static final Map<String, String> FILE_NAME_RESOURCE_LABELS_MAP = new HashMap<String, String>() {
		private static final long serialVersionUID = 1701211252019386862L;
		{
			put("activity", "request.fileName");
			
			//Added By MPS offshore team
			put("order", "requestInfo.order.fileName");
			put("payment.payment", "payment.payment.fileName");
			put("payment.request","payment.request.fileName");
			
			put("downloadClaim", "download.claim");
			put("downloadRequest", "download.request");		
			
			//ADded for AP AR
			put("AP","requestInfo.APInvoice.fileName");
			put("AR","requestInfo.ARInvoice.fileName");
			
			// Request History 
			put("reqHistoryAP", "requestInfo.APInvoice.fileName");
			put("reqHistoryAR", "requestInfo.ARInvoice.fileName");
			
			//Mass Upload
			put("massUpload_serviceOrder", "requestInfo.massUploadTemplate.fileName");
			put(LexmarkPPConstants.HARDWAREORDER, "requestInfo.hardwareOrder.fileName");
			put(LexmarkPPConstants.HARDWAREORDER_OFFLINEDEBRIEFS, "requestInfo.hardwareOrder.fileName");
			put(LexmarkPPConstants.MASSUPLOAD_EXPORT, "requestInfo.hardwareOrder.fileName");
		}
	};

	// key:dataType,value:the label name in the message.properties
	public static final Map<String, String> HEADER_RESOURCE_LABELS_MAP = new HashMap<String, String>() {
		private static final long serialVersionUID = 1701211252019386862L;
		{
			put("activity", "request.headers");
			
			//Added By MPS offshore team
			put("order", "requestInfo.serviceOrder.listHeader.orderList");
			
			put("payment.payment", "payment.payment.headers");
			put("payment.request","paymentRequest.headers");
			
			put("downloadClaim", "download.claim.headers");
			put("downloadRequest", "download.request.headers");
			
			//ADded for AP AR
			put("AP","requestInfo.APInvoice.headers");
			put("AR","requestInfo.ARInvoice.headers");
			
			put("reqHistoryAP", "requestInfo.APHistory.headers");
			put("reqHistoryAR", "requestInfo.ARHistory.headers");

			//Mass Upload
			put("massUpload_serviceOrder", "serviceOrder_CSVHeader");
			put(LexmarkPPConstants.HARDWAREORDER, "requestInfo.hardwareOrder.listHeader.hardwareList");
			put(LexmarkPPConstants.HARDWAREORDER_OFFLINEDEBRIEFS, "requestInfo.hardwareOrder.listHeader.hardwareList.offlineDebriefs");
			put(LexmarkPPConstants.MASSUPLOAD_EXPORT, "requestInfo.hardwareOrder.listHeader.hardwareList.massUpload");
		}
	};

	/**
	 * @param locale 
	 */
	public void setLocale(Locale locale);

	/**
	 * @param response 
	 * @param dataType 
	 * @param columnPatterns 
	 * @param dataList 
	 * @throws IOException 
	 */
	@SuppressWarnings("unchecked")
	public void export(ResourceResponse response, String dataType, String[] columnPatterns, List dataList)
			throws IOException;
	
	//Aded by MPS Offshore Team for source
	/**
	 * @param response 
	 * @param dataType 
	 * @param columnPatterns 
	 * @param dataList 
	 * @throws IOException 
	 */
	@SuppressWarnings("unchecked")
	public void exportOrder(ResourceResponse response, String dataType, String[] columnPatterns, List dataList)
			throws IOException;
	//End Add
	
	/**
	 * @param response 
	 * @param dataType 
	 * @param columnPatterns 
	 * @param dataList 
	 * @throws IOException 
	 */
	@SuppressWarnings("unchecked")
	public void exportRequestClaims(ResourceResponse response, String dataType, String[] columnPatterns, List dataList)
			throws IOException;
	


}
