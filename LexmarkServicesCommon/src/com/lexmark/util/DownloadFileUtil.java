package com.lexmark.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.lexmark.domain.Asset;
import com.lexmark.domain.Invoice;
import com.lexmark.domain.ServiceRequest;

public class DownloadFileUtil {
	private static Logger LOGGER = LogManager.getLogger(DownloadFileUtil.class);

	private static String CSV_HEAD = null;

	public static String assembleToServiceRequestCSV(
			List<ServiceRequest> serviceRequestList, Locale locale) {
		CSV_HEAD = DownloadFileLocalizationUtil
				.getServiceRequestsCSVFileHeader(locale);


		StringBuffer sb = new StringBuffer();
		sb.append(CSV_HEAD);
		sb.append("\n");
		if (serviceRequestList != null) {
			for (int i = 0; i < serviceRequestList.size(); i++) {
				ServiceRequest serviceRequest = null;
				serviceRequest = serviceRequestList.get(i);
				fillServiceRequestValue(sb, serviceRequest, locale);
			}
			return sb.toString();
		} else
			return sb.toString();
	}

	/**
	 * Assemble csv with device list.

	 * @param deviceList a list of device

	 * @param locale
	 * @return the StringBuffer for the csv file
	 */
	public static String assembleToDeviceListCSV(
			List<Asset> deviceList, Locale locale) {

		// TODO since the we are not quite clear about the detail of Device List, not
		// all the fields are populated.
		CSV_HEAD = DownloadFileLocalizationUtil
				.getPageCountsListCSVFileHeader(locale);

		StringBuffer sb = new StringBuffer();
		sb.append(CSV_HEAD);
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
		} else
			return sb.toString();
	}
	
	/**
	 * This method is used for Device Management CSV Creation for MPS
	 * @param deviceList
	 * @param locale
	 * @return
	 */
	public static String createDeviceListCSV(
			List<Asset> deviceList, Locale locale,String gVPage) {

		// TODO since the we are not quite clear about the detail of Device List, not
		// all the fields are populated.
		if(gVPage.equalsIgnoreCase("true")){
			CSV_HEAD = DownloadFileLocalizationUtil
			.getDeviceListGridViewCSVFileHeader(locale);
		}
		else{
			CSV_HEAD = DownloadFileLocalizationUtil
			.getDeviceListCSVFileHeader(locale);
		}
		
		
		
		StringBuffer sb = new StringBuffer();
		sb.append(CSV_HEAD);
		sb.append("\n");
		if (deviceList != null) {
			for (int i = 0; i < deviceList.size(); i++) {
				Asset device = null;
				device = deviceList.get(i);
				fillDeviceCSV(sb, device, gVPage);
			}
			return sb.toString();
		} else
			return sb.toString();
	}
//////////////////////added for BRD 14-02-01///////////////////////////////
/**
* This method is used for Device Management CSV Creation for MPS
* @param requestList
* @param locale
* @return
*/
public static String createRequestListCSV(List<ServiceRequest> requestList, Locale locale,
		String csvFileHeader,String requestType,float timeZoneOffset) {

	CSV_HEAD = DownloadFileLocalizationUtil.getLocalizedFileHeader(
			csvFileHeader, locale);
	

StringBuffer sb = new StringBuffer();
sb.append(CSV_HEAD);
sb.append("\n");

if(requestType.isEmpty()){
	return sb.toString();
}
if (requestList != null) {
	if("gridContainerDiv_all_request_types".equals(requestType)){
		fillAllHistoryListValue(sb, requestList, locale, timeZoneOffset);
	}else if("gridContainerDiv_change_requests".equals(requestType)){
		fillCMHistoryListValue(sb, requestList, locale, timeZoneOffset);
	}else if("gridContainerDiv_supplies_requests".equals(requestType)){
		fillSupplyHistoryListValue(sb, requestList, locale, timeZoneOffset);
	}else if("gridContainerDiv_service_requests".equals(requestType)){
		for (int i = 0; i < requestList.size(); i++) {
			ServiceRequest serviceRequest = null;
			serviceRequest = requestList.get(i);
			fillServiceRequestValue(sb, serviceRequest, locale);
		}
	}
}

	return sb.toString();
}

////////////////////////////////////End/////////////////////////
	
	private static void fillServiceRequestValue(StringBuffer sb, ServiceRequest serviceRequest, Locale locale){
		fillValue(sb, serviceRequest.getServiceRequestNumber());
		fillValue(sb, formatSRDate(serviceRequest.getServiceRequestDate()));
		//fillValue(sb, "'"+serviceRequest.getAsset().getSerialNumber());
		fillValue(sb, serviceRequest.getAsset().getSerialNumber());//changed for LEX:AIR00060473
		//fillValue(sb, serviceRequest.getAsset().getProductLine());
		fillValue(sb, serviceRequest.getAsset().getModelNumber());   //Changed for LEX:AIR00072372 by Arunava
		fillValue(sb, serviceRequest.getProblemDescription());
		
		//Commented because physicallocation1, 2 and 3 have been removed from Asset bean
		// - office number,county,district,account name : Added for MPS phase 2
		fillValue(sb, serviceRequest.getResolutionCode());
		fillValue(sb, serviceRequest.getServiceRequestStatus());
		fillValue(sb, serviceRequest.getServiceAddress().getAddressName());
		fillValue(sb, serviceRequest.getServiceAddress().getOfficeNumber());
		
		fillValue(sb, serviceRequest.getServiceAddress().getCity());
		fillValue(sb, serviceRequest.getServiceAddress().getState());
		fillValue(sb, serviceRequest.getServiceAddress().getProvince());
		fillValue(sb, serviceRequest.getServiceAddress().getCounty());
		fillValue(sb, serviceRequest.getServiceAddress().getDistrict());
		fillValue(sb, serviceRequest.getServiceAddress().getPostalCode());
		fillValue(sb, serviceRequest.getServiceAddress().getCountry());
		// added Dwijen
		fillValue(sb, serviceRequest.getHelpdeskReferenceNumber());
		// Added Dwijen
		fillValue(sb, serviceRequest.getPrimaryContact().getFirstName());
		fillValue(sb, serviceRequest.getPrimaryContact().getLastName());
		fillValue(sb, serviceRequest.getPrimaryContact().getEmailAddress());
		fillValue(sb, serviceRequest.getPrimaryContact().getWorkPhone());
		//CI-6 added last four line : Start
		fillValue(sb, serviceRequest.getContractType());
		fillValue(sb, serviceRequest.getAsset().getDeviceTag());
		fillValue(sb, serviceRequest.getAsset().getHostName());
		fillValue(sb, serviceRequest.getCostCenter());
		fillValue(sb, serviceRequest.getAccountName());
		//CI-6 added last four line : End
		
		//added for OPS portal:Start
		fillValue(sb, serviceRequest.getRequestStatus());
		fillValue(sb, serviceRequest.getSubStatus());
		fillValue(sb, serviceRequest.getSeverity(),"");
		//added for OPS portal:End
	}
	
	private static void fillDeviceValue(StringBuffer sb, Asset device) {
		// changed for Page Counts Grid Break down 
		fillValue(sb, device.getSerialNumber());
		fillValue(sb, device.getProductLine());
		fillValue(sb, device.getAssetTag());
		fillValue(sb, device.getIpAddress());
		fillValue(sb, device.getInstallAddress().getAddressName());
		fillValue(sb, device.getInstallAddress().getOfficeNumber());
		fillValue(sb, device.getInstallAddress().getCity());
		fillValue(sb, device.getInstallAddress().getState());
		fillValue(sb, device.getInstallAddress().getProvince());
		fillValue(sb, device.getInstallAddress().getCounty());
		fillValue(sb, device.getInstallAddress().getDistrict());
		fillValue(sb, device.getInstallAddress().getCountry());
		fillValue(sb, device.getHostName());
		fillValue(sb, device.getMacAddress());
		fillValue(sb, device.getDeviceTag());
		fillValue(sb, device.getMachineTypeModel());
		fillValue(sb, device.getProductTLI());
		fillValue(sb, device.getPhysicalLocationAddress().getPhysicalLocation1());
		fillValue(sb, device.getAssetContact().getFirstName());
		fillValue(sb, device.getAssetContact().getLastName(),"");
		/*fillValue(sb, device.getSerialNumber());
		fillValue(sb, device.getProductLine());
		fillValue(sb, device.getAssetTag());
		fillValue(sb, device.getDeviceTag());
		fillValue(sb, device.getDevicePhase());
		fillValue(sb, device.getHostName());
		fillValue(sb, device.getIpAddress());
		fillValue(sb, device.getInstallAddress().getAddressName());
		fillValue(sb, device.getInstallAddress().getAddressLine1());
		fillValue(sb, device.getInstallAddress().getCity());
		fillValue(sb, device.getInstallAddress().getState());
		fillValue(sb, device.getInstallAddress().getProvince());
		fillValue(sb, device.getInstallAddress().getCountry(),"");*/
	}
	/**
	 * This method is used for Device Management CSV for MPS
	 * @param sb
	 * @param device
	 */
	 //Changed for CI Defect # 7768
	private static void fillDeviceCSV(StringBuffer sb, Asset device, String gVPage) {
		fillValue(sb, device.getSerialNumber());
		fillValue(sb, device.getDeviceTag());
		fillValue(sb, device.getProductLine());
		fillValue(sb, device.getAssetTag());
		fillValue(sb, device.getHostName());		
		fillValue(sb, device.getIpAddress());
		fillValue(sb, device.getAccount().getAccountName());
		fillValue(sb, device.getAssetType());
		fillValue(sb, device.getDevicePhase());
		fillValue(sb, device.getAssetCostCenter());
		fillValue(sb, device.getInstallAddress().getAddressName());
		fillValue(sb, device.getInstallAddress().getAddressLine1());
		fillValue(sb, device.getInstallAddress().getOfficeNumber());
		fillValue(sb, device.getInstallAddress().getCity());
		fillValue(sb, device.getInstallAddress().getState());
		fillValue(sb, device.getInstallAddress().getProvince());
		fillValue(sb, device.getInstallAddress().getCounty());
		fillValue(sb, device.getInstallAddress().getDistrict());
		fillValue(sb, device.getInstallAddress().getCountry());
		fillValue(sb, device.getInstallAddress().getPostalCode());
		fillValue(sb, device.getAssetContact().getFirstName());
		fillValue(sb, device.getAssetContact().getLastName());
		fillValue(sb, device.getAssetContact().getEmailAddress());
		if(gVPage.equalsIgnoreCase("true")){
			fillValue(sb, device.getAssetContact().getWorkPhone());
			if(device.isLbsAddressFlag())
			fillValue(sb, "Yes","");
			else
			fillValue(sb, "No","");
		}else{
			fillValue(sb, device.getAssetContact().getWorkPhone(),"");
		}
		
		
	}
	
	private static void fillValue(StringBuffer sb, String value){
		if (value != null) {
			sb.append(processBeforeFilling(value));
		}
		sb.append(",");
	}
	
	private static void fillValue(StringBuffer sb, String value, String flag){
		if (value != null) {
			sb.append(processBeforeFilling(value));
		}
		sb.append("\n");
	}
	

	private static String formatSRDate(Date date){
		if(date!=null){
			return new SimpleDateFormat("MM/dd/yyyy").format(date);
		}else {
			return "";
		}
	}

	/**
	 * Process before filling, append one more " before ", and enclose with "".

	 * @param value
	 * @return processed string
	 */
	private static String processBeforeFilling(String value) {
		return StringUtil.encloseWithDoubleQuote(StringUtil.appendDoubleQuote(value));
	}
	
	/**.
	 * Assembles the CM history details to CSV file.
	 * @param serviceRequestList List<ServiceRequest>
	 * @param locale Locale
	 * @return String
	 * @author sagar
	 */
	public static String assembleCMHistoryToCSV(
			List<ServiceRequest> serviceRequestList, String fileHeaderPropsKey,
			Locale locale, String serviceRequestType, float timeZoneOffset) {
		CSV_HEAD = DownloadFileLocalizationUtil.getLocalizedFileHeader(
				fileHeaderPropsKey, locale);
		LOGGER.info("DownloadFileUtil.assembleCMHistoryToCSV.CSV_HEAD:"+CSV_HEAD);
		StringBuffer sb = new StringBuffer();
		sb.append(CSV_HEAD);
		sb.append("\n");
		
		if(serviceRequestType.isEmpty()){
			return sb.toString();
		}
		
		if(serviceRequestType.trim().equalsIgnoreCase("ALL_REQUESTS")){
			fillAllHistoryListValue(sb, serviceRequestList, locale, timeZoneOffset);
		}else if(serviceRequestType.trim().equalsIgnoreCase("SUPPLY_REQUESTS")){
			fillSupplyHistoryListValue(sb, serviceRequestList, locale, timeZoneOffset);
		}else if(serviceRequestType.trim().equalsIgnoreCase("CHANGE_REQUESTS")){
			fillCMHistoryListValue(sb, serviceRequestList, locale, timeZoneOffset);
		}else if(serviceRequestType.trim().equalsIgnoreCase("HARDWARE_REQUESTS")){
			fillHardwareValues(sb, serviceRequestList, locale, timeZoneOffset);
		}
		
			return sb.toString();
	}
	
	/**.
	 * Fills All history values to string buffer
	 * @param sb
	 * @param serviceRequest
	 * @param locale
	 * @author sagar
	 */
	private static void fillAllHistoryListValue(StringBuffer sb,
			List<ServiceRequest> serviceRequestList, Locale locale,float timeZoneOffset) {

		if (serviceRequestList != null) {
			for (int i = 0; i < serviceRequestList.size(); i++) {
				ServiceRequest serviceRequest = null;
				serviceRequest = serviceRequestList.get(i);
				
				fillValue(sb, serviceRequest.getServiceRequestNumber());
				//fillValue(sb, formatSRDate(serviceRequest.getServiceRequestDate()));
				/*if(timeZoneOffset !=null ){
					try {
						TimezoneUtil.adjustDate(serviceRequest.getServiceRequestDate(),(timeZoneOffset));
					} catch (NumberFormatException e) {					
						e.printStackTrace();
					}
				}*/
				//logger.info("locale :--->"+locale.getCountry());
				//logger.info("timeZoneOffset :--->"+timeZoneOffset);
				TimezoneUtil.adjustDate(serviceRequest.getServiceRequestDate(),(timeZoneOffset));
				fillValue(sb,DateLocalizationUtil.localizeDateTime(serviceRequest.getServiceRequestDate(), true, locale));
				
				fillValue(sb,(serviceRequest.getServiceRequestType()== null)? "":serviceRequest.getServiceRequestType().getValue());
				fillValue(sb, (serviceRequest.getArea()== null)? "":serviceRequest.getArea().getValue());			
				//fillValue(sb, (serviceRequest.getServiceRequestStatus()== null)? "":serviceRequest.getServiceRequestStatus());
				fillValue(sb, (serviceRequest.getStatusType().getValue()== null)? "":serviceRequest.getStatusType().getValue());
				fillValue(sb, (serviceRequest.getAsset()== null)? "":serviceRequest.getAsset().getSerialNumber());
				fillValue(sb, (serviceRequest.getCostCenter()== null)? "":serviceRequest.getCostCenter());
				// - account name : added for MPS Phase 2
				fillValue(sb, (serviceRequest == null)? "":serviceRequest.getAccountName());
				fillValue(sb, (serviceRequest.getPrimaryContact()== null)? "":serviceRequest.getPrimaryContact().getFirstName());
				fillValue(sb, (serviceRequest.getPrimaryContact()== null)? "":serviceRequest.getPrimaryContact().getLastName());
				fillValue(sb, (serviceRequest.getPrimaryContact()== null)? "":serviceRequest.getPrimaryContact().getEmailAddress());
				fillValue(sb, (serviceRequest.getPrimaryContact()== null)? "":serviceRequest.getPrimaryContact().getWorkPhone());
				
				fillValue(sb, (serviceRequest.getRequestor()== null)? "":serviceRequest.getRequestor().getFirstName());
				fillValue(sb, (serviceRequest.getRequestor()== null)? "":serviceRequest.getRequestor().getLastName());
				fillValue(sb, (serviceRequest.getRequestor()== null)? "":serviceRequest.getRequestor().getEmailAddress());
				fillValue(sb, (serviceRequest.getRequestor()== null)? "":serviceRequest.getRequestor().getWorkPhone());
				
				
				
				
				sb.append("\n");
			}
			
		} 
		
	}
	
	/**.
	 * Fills Supply history values to string buffer
	 * @param sb
	 * @param serviceRequest
	 * @param locale
	 * @author sagar
	 */
	private static void fillSupplyHistoryListValue(StringBuffer sb,
			List<ServiceRequest> serviceRequestList, Locale locale, float timeZoneOffset) {

		if (serviceRequestList != null) {
			for (int i = 0; i < serviceRequestList.size(); i++) {
				ServiceRequest serviceRequest = null;
				serviceRequest = serviceRequestList.get(i);
				
				
				//Changes MPs 2.1
				fillValue(sb,(serviceRequest.getExpediteOrder() != null && serviceRequest.getExpediteOrder())? "Y":"");
				//Ends Changes MPs 2.1
				fillValue(sb, serviceRequest.getServiceRequestNumber());
				//fillValue(sb, formatSRDate(serviceRequest.getServiceRequestDate()));
				/*if(timeZoneOffset !=null ){
					try {
						TimezoneUtil.adjustDate(serviceRequest.getServiceRequestDate(),(Integer.parseInt(timeZoneOffset)));
					} catch (NumberFormatException e) {					
						e.printStackTrace();
					}
				}*/
				TimezoneUtil.adjustDate(serviceRequest.getServiceRequestDate(),(timeZoneOffset));
				fillValue(sb,DateLocalizationUtil.localizeDateTime(serviceRequest.getServiceRequestDate(), true, locale));							
				
				fillValue(sb, (serviceRequest.getArea()== null)? "":serviceRequest.getArea().getValue());
				fillValue(sb,(serviceRequest.getSubArea()== null)? "":serviceRequest.getSubArea().getValue());
				fillValue(sb, (serviceRequest.getAsset()== null)? "":serviceRequest.getAsset().getSerialNumber());
				//Changes MPs 2.1
				fillValue(sb, (serviceRequest.getAsset() == null)? "":serviceRequest.getAsset().getDeviceTag());
				//Ends Changes MPs 2.1
				//fillValue(sb, (serviceRequest.getServiceRequestStatus()== null)? "":serviceRequest.getServiceRequestStatus());
				fillValue(sb, (serviceRequest.getStatusType().getValue()== null)? "":serviceRequest.getStatusType().getValue());
				fillValue(sb, (serviceRequest.getAsset()== null)? "":serviceRequest.getAsset().getModelNumber());
				fillValue(sb, (serviceRequest.getPoNumber()== null)? "":serviceRequest.getPoNumber());
				fillValue(sb, (serviceRequest.getCostCenter()== null)? "":serviceRequest.getCostCenter());
				//Changes MPs 2.1
				fillValue(sb, (serviceRequest == null)? "":serviceRequest.getAccountName());
				//Ends Changes MPs 2.1
				// - office number,county,district,account name,device tag : Added for MPS Phase 2
				
				fillValue(sb, (serviceRequest.getServiceAddress()== null)? "":serviceRequest.getServiceAddress().getAddressName());
				fillValue(sb, (serviceRequest.getServiceAddress()== null)? "":serviceRequest.getServiceAddress().getStoreFrontName());
				fillValue(sb, (serviceRequest.getServiceAddress()== null)? "":serviceRequest.getServiceAddress().getAddressLine1());
				fillValue(sb, (serviceRequest.getServiceAddress()== null)? "":serviceRequest.getServiceAddress().getOfficeNumber());
				fillValue(sb, (serviceRequest.getServiceAddress()== null)? "":serviceRequest.getServiceAddress().getCity());
				fillValue(sb, (serviceRequest.getServiceAddress()== null)? "":serviceRequest.getServiceAddress().getState());
				fillValue(sb, (serviceRequest.getServiceAddress()== null)? "":serviceRequest.getServiceAddress().getProvince());
				fillValue(sb, (serviceRequest.getServiceAddress()== null)? "":serviceRequest.getServiceAddress().getCounty());
				fillValue(sb, (serviceRequest.getServiceAddress()== null)? "":serviceRequest.getServiceAddress().getDistrict());
				fillValue(sb, (serviceRequest.getServiceAddress()== null)? "":serviceRequest.getServiceAddress().getCountry());
				fillValue(sb, (serviceRequest.getServiceAddress()== null)? "":serviceRequest.getServiceAddress().getPostalCode());
				
				fillValue(sb, (serviceRequest.getPrimaryContact()== null)? "":serviceRequest.getPrimaryContact().getFirstName());
				fillValue(sb, (serviceRequest.getPrimaryContact()== null)? "":serviceRequest.getPrimaryContact().getLastName());
				fillValue(sb, (serviceRequest.getPrimaryContact()== null)? "":serviceRequest.getPrimaryContact().getEmailAddress());
				fillValue(sb, (serviceRequest.getPrimaryContact()== null)? "":serviceRequest.getPrimaryContact().getWorkPhone());
				
				fillValue(sb, (serviceRequest.getRequestor()== null)? "":serviceRequest.getRequestor().getFirstName());
				fillValue(sb, (serviceRequest.getRequestor()== null)? "":serviceRequest.getRequestor().getLastName());
				fillValue(sb, (serviceRequest.getRequestor()== null)? "":serviceRequest.getRequestor().getEmailAddress());
				fillValue(sb, (serviceRequest.getRequestor()== null)? "":serviceRequest.getRequestor().getWorkPhone());
			
				fillValue(sb, (serviceRequest.getRequestStatus()== null)? "":serviceRequest.getRequestStatus());
				fillValue(sb, (serviceRequest.getSubStatus()== null)? "":serviceRequest.getSubStatus());
				fillValue(sb, (serviceRequest.getSeverity()== null)? "":serviceRequest.getSeverity());
				
				
				fillValue(sb, (serviceRequest.getAgreementName()== null)? "":serviceRequest.getAgreementName());
				fillValue(sb, (serviceRequest.getAgreementNumber()== null)? "":serviceRequest.getAgreementNumber());
				
				
				
				sb.append("\n");
				
			}
			
		} 
			
	}
	
	/*Changes for hardware csv */
	private static void fillHardwareValues(StringBuffer sb,
			List<ServiceRequest> serviceRequestList, Locale locale, float timeZoneOffset) {

		if (serviceRequestList != null) {
			for (int i = 0; i < serviceRequestList.size(); i++) {
				ServiceRequest serviceRequest = null;
				serviceRequest = serviceRequestList.get(i);
				
				fillValue(sb, serviceRequest.getServiceRequestNumber());
				TimezoneUtil.adjustDate(serviceRequest.getServiceRequestDate(),(timeZoneOffset));
				fillValue(sb,DateLocalizationUtil.localizeDateTime(serviceRequest.getServiceRequestDate(), true, locale));							
				
				fillValue(sb, (serviceRequest.getArea()== null)? "":serviceRequest.getArea().getValue());
				fillValue(sb,(serviceRequest.getSubArea()== null)? "":serviceRequest.getSubArea().getValue());
				fillValue(sb, (serviceRequest.getStatusType().getValue()== null)? "":serviceRequest.getStatusType().getValue());
				fillValue(sb, (serviceRequest.getPoNumber()== null)? "":serviceRequest.getPoNumber());
				fillValue(sb, (serviceRequest.getCostCenter()== null)? "":serviceRequest.getCostCenter());
				
				// - office number,county,district,account name,device tag : Added for MPS Phase 2
				
				fillValue(sb, (serviceRequest == null)? "":serviceRequest.getAccountName());
				fillValue(sb, (serviceRequest.getServiceAddress()== null)? "":serviceRequest.getServiceAddress().getAddressName());
				fillValue(sb, (serviceRequest.getServiceAddress()== null)? "":serviceRequest.getServiceAddress().getOfficeNumber());//House Number added for CI Defect #9183
				fillValue(sb, (serviceRequest.getServiceAddress()== null)? "":serviceRequest.getServiceAddress().getStoreFrontName());
				fillValue(sb, (serviceRequest.getServiceAddress()== null)? "":serviceRequest.getServiceAddress().getAddressLine1());
				//fillValue(sb, (serviceRequest.getServiceAddress()== null)? "":serviceRequest.getServiceAddress().getOfficeNumber());
				fillValue(sb, (serviceRequest.getServiceAddress()== null)? "":serviceRequest.getServiceAddress().getCity());
				fillValue(sb, (serviceRequest.getServiceAddress()== null)? "":serviceRequest.getServiceAddress().getState());
				fillValue(sb, (serviceRequest.getServiceAddress()== null)? "":serviceRequest.getServiceAddress().getProvince());
				fillValue(sb, (serviceRequest.getServiceAddress()== null)? "":serviceRequest.getServiceAddress().getCounty());
				fillValue(sb, (serviceRequest.getServiceAddress()== null)? "":serviceRequest.getServiceAddress().getDistrict());
				fillValue(sb, (serviceRequest.getServiceAddress()== null)? "":serviceRequest.getServiceAddress().getCountry());
				fillValue(sb, (serviceRequest.getServiceAddress()== null)? "":serviceRequest.getServiceAddress().getPostalCode());
				
				fillValue(sb, (serviceRequest.getPrimaryContact()== null)? "":serviceRequest.getPrimaryContact().getFirstName());
				fillValue(sb, (serviceRequest.getPrimaryContact()== null)? "":serviceRequest.getPrimaryContact().getLastName());
				fillValue(sb, (serviceRequest.getPrimaryContact()== null)? "":serviceRequest.getPrimaryContact().getEmailAddress());
				fillValue(sb, (serviceRequest.getPrimaryContact()== null)? "":serviceRequest.getPrimaryContact().getWorkPhone());
				
				fillValue(sb, (serviceRequest.getRequestor()== null)? "":serviceRequest.getRequestor().getFirstName());
				fillValue(sb, (serviceRequest.getRequestor()== null)? "":serviceRequest.getRequestor().getLastName());
				fillValue(sb, (serviceRequest.getRequestor()== null)? "":serviceRequest.getRequestor().getEmailAddress());
				fillValue(sb, (serviceRequest.getRequestor()== null)? "":serviceRequest.getRequestor().getWorkPhone());
				
				fillValue(sb, (serviceRequest.getProjectName()== null)? "":serviceRequest.getProjectName());
				fillValue(sb, (serviceRequest.getProjectPhase()== null)? "":serviceRequest.getProjectPhase());
				
				
				
				
				
				
				sb.append("\n");
				
			}
			
		} 
			
	}
	/**.
	 * Fills CM history values to string buffer
	 * @param sb
	 * @param serviceRequest
	 * @param locale
	 * @author sagar
	 */
	private static void fillCMHistoryListValue(StringBuffer sb,
			List<ServiceRequest> serviceRequestList, Locale locale, float timeZoneOffset) {

		if (serviceRequestList != null) {
			for (int i = 0; i < serviceRequestList.size(); i++) {
				ServiceRequest serviceRequest = null;
				serviceRequest = serviceRequestList.get(i);
				
				fillValue(sb, serviceRequest.getServiceRequestNumber());
				//fillValue(sb, formatSRDate(serviceRequest.getServiceRequestDate()));
				/*if(timeZoneOffset !=null ){
					try {
						TimezoneUtil.adjustDate(serviceRequest.getServiceRequestDate(),(Integer.parseInt(timeZoneOffset)));
					} catch (NumberFormatException e) {					
						e.printStackTrace();
					}
				}*/
				
				TimezoneUtil.adjustDate(serviceRequest.getServiceRequestDate(),(timeZoneOffset));
				fillValue(sb,DateLocalizationUtil.localizeDateTime(serviceRequest.getServiceRequestDate(), true, locale));							
				
				fillValue(sb, (serviceRequest.getArea()== null)? "":serviceRequest.getArea().getValue());
				fillValue(sb,(serviceRequest.getSubArea()== null)? "":serviceRequest.getSubArea().getValue());
				fillValue(sb, (serviceRequest.getAsset()== null)? "":serviceRequest.getAsset().getSerialNumber());
				fillValue(sb, (serviceRequest.getAsset()== null)? "":serviceRequest.getAsset().getDeviceTag());
				//fillValue(sb, (serviceRequest.getServiceRequestStatus()== null)? "":serviceRequest.getServiceRequestStatus());
				fillValue(sb, (serviceRequest.getStatusType().getValue()== null)? "":serviceRequest.getStatusType().getValue());
				fillValue(sb, (serviceRequest.getAsset()== null)? "":serviceRequest.getAsset().getModelNumber());
				
				fillValue(sb, (serviceRequest.getReferenceNumber()== null)? "":serviceRequest.getReferenceNumber());
				fillValue(sb, (serviceRequest.getCostCenter()== null)? "":serviceRequest.getCostCenter());
				fillValue(sb, (serviceRequest == null)? "":serviceRequest.getAccountName());
				// - office number,county,district,account name,device tag : added for MPS Phase 2
				fillValue(sb, (serviceRequest.getServiceAddress()== null)? "":serviceRequest.getServiceAddress().getAddressName());
				fillValue(sb, (serviceRequest.getServiceAddress()== null)? "":serviceRequest.getServiceAddress().getStoreFrontName());
				fillValue(sb, (serviceRequest.getServiceAddress()== null)? "":serviceRequest.getServiceAddress().getAddressLine1());
				fillValue(sb, (serviceRequest.getServiceAddress()== null)? "":serviceRequest.getServiceAddress().getOfficeNumber());
				fillValue(sb, (serviceRequest.getServiceAddress()== null)? "":serviceRequest.getServiceAddress().getCity());
				fillValue(sb, (serviceRequest.getServiceAddress()== null)? "":serviceRequest.getServiceAddress().getState());
				fillValue(sb, (serviceRequest.getServiceAddress()== null)? "":serviceRequest.getServiceAddress().getProvince());
				fillValue(sb, (serviceRequest.getServiceAddress()== null)? "":serviceRequest.getServiceAddress().getCounty());
				fillValue(sb, (serviceRequest.getServiceAddress()== null)? "":serviceRequest.getServiceAddress().getDistrict());
				fillValue(sb, (serviceRequest.getServiceAddress()== null)? "":serviceRequest.getServiceAddress().getCountry());
				fillValue(sb, (serviceRequest.getServiceAddress()== null)? "":serviceRequest.getServiceAddress().getPostalCode());
				
				fillValue(sb, (serviceRequest.getPrimaryContact()== null)? "":serviceRequest.getPrimaryContact().getFirstName());
				fillValue(sb, (serviceRequest.getPrimaryContact()== null)? "":serviceRequest.getPrimaryContact().getLastName());
				fillValue(sb, (serviceRequest.getPrimaryContact()== null)? "":serviceRequest.getPrimaryContact().getEmailAddress());
				fillValue(sb, (serviceRequest.getPrimaryContact()== null)? "":serviceRequest.getPrimaryContact().getWorkPhone());
				
				fillValue(sb, (serviceRequest.getRequestor()== null)? "":serviceRequest.getRequestor().getFirstName());
				fillValue(sb, (serviceRequest.getRequestor()== null)? "":serviceRequest.getRequestor().getLastName());
				fillValue(sb, (serviceRequest.getRequestor()== null)? "":serviceRequest.getRequestor().getEmailAddress());
				fillValue(sb, (serviceRequest.getRequestor()== null)? "":serviceRequest.getRequestor().getWorkPhone());
				fillValue(sb, (serviceRequest.getRequestor()== null)? "":serviceRequest.getCoveredService());
				
				
				fillValue(sb, (serviceRequest.getRequestStatus()== null)? "":serviceRequest.getRequestStatus());
				fillValue(sb, (serviceRequest.getSubStatus()== null)? "":serviceRequest.getSubStatus());
				fillValue(sb, (serviceRequest.getSeverity()== null)? "":serviceRequest.getSeverity());
				fillValue(sb, (serviceRequest.getProjectName()== null)? "":serviceRequest.getProjectName());
				fillValue(sb, (serviceRequest.getProjectPhase()== null)? "":serviceRequest.getProjectPhase());
				
				fillValue(sb, (serviceRequest.getAgreementNumber()== null)? "":serviceRequest.getAgreementName());
				fillValue(sb, (serviceRequest.getAgreementNumber()== null)? "":serviceRequest.getAgreementNumber());
				
				
				
				
				sb.append("\n");
			}
			
		} 
		
	}
	/**
	 * Assemble csv with asset list for order management.

	 * @param assetList a list of device

	 * @param locale
	 * @return the StringBuffer for the csv file
	 */
	public static String assembleToAssetListCSV(List<Asset> assetList, Locale locale) {

		// TODO since the we are not quite clear about the detail of Device List, not
		// all the fields are populated.
		CSV_HEAD = DownloadFileLocalizationUtil.getAssetListCSVFileHeader(locale);

		StringBuffer sb = new StringBuffer();
		sb.append(CSV_HEAD);
		sb.append("\n");
		if (assetList != null) {
			for (int i = 0; i < assetList.size(); i++) {
				Asset asset = null;
				asset = assetList.get(i);
				fillAssetValue(sb, asset);
			}
			return sb.toString();
		} else
			return sb.toString();
	}
	private static void fillAssetValue(StringBuffer sb, Asset asset) {
		fillValue(sb, asset.getSerialNumber());
		fillValue(sb, asset.getProductLine());
		fillValue(sb, asset.getAssetTag());
		fillValue(sb, asset.getHostName());
		fillValue(sb, asset.getIpAddress());
		fillValue(sb, asset.getInstallAddress().getAddressName());
		//fillValue(sb, asset.getAgreementType());
		fillValue(sb, asset.getDevicePhase());
		fillValue(sb, asset.getInstallAddress().getAddressLine1());
		fillValue(sb, asset.getInstallAddress().getCity());
		fillValue(sb, asset.getInstallAddress().getState());
		fillValue(sb, asset.getInstallAddress().getProvince());
		fillValue(sb, asset.getInstallAddress().getCountry(),"");
		fillValue(sb, asset.getInstallAddress().getPostalCode());
		fillValue(sb, asset.getAssetContact().getFirstName());
		fillValue(sb, asset.getAssetContact().getLastName());
		fillValue(sb, asset.getAssetContact().getEmailAddress());
		fillValue(sb, asset.getAssetContact().getWorkPhone());
	}
	
	public static String fillInvoiceList(String csvHeader,List<Invoice> invoiceList,float timezoneOffset,Locale locale){
	
		StringBuffer sb = new StringBuffer();
		sb.append(csvHeader);
		sb.append("\n");
		
		int size=invoiceList.size();
		for (int i = 0; i < size; i++) {
			Invoice invoice=invoiceList.get(i);
			
			
			
			fillValue(sb,invoice.getInvoiceNumber());
			String invoiceDate =  DateLocalizationUtil.localizeDateTime(DateUtil.adjustDateWithOffset(invoice.getInvoiceDate(), timezoneOffset), false, locale);
			fillValue(sb,invoiceDate);
			
			String dueDate =  DateLocalizationUtil.localizeDateTime(DateUtil.adjustDateWithOffset(invoice.getDueDate(), timezoneOffset), false, locale);
			fillValue(sb,dueDate);
			
			String paidDate =  DateLocalizationUtil.localizeDateTime(DateUtil.adjustDateWithOffset(invoice.getPaidDate(), timezoneOffset), false, locale);
			fillValue(sb,paidDate);
			
			
			
			fillValue(sb,invoice.getStatus());
			fillValue(sb,invoice.getAmount().toString()+"("+invoice.getCurrencyType()+")");
			
			
			
			sb.append("\n");
		}
		return sb.toString();
	}
	//Added for CI BRD 14-02-01-STARTS
	private static void fillRequestCSV(StringBuffer sb, ServiceRequest serviceRequest) {
		fillValue(sb, serviceRequest.getServiceRequestNumber());
		fillValue(sb, serviceRequest.getServiceRequestDate().toString());
		fillValue(sb, serviceRequest.getServiceRequestType().toString());
		fillValue(sb, serviceRequest.getArea().toString());
		fillValue(sb, serviceRequest.getServiceRequestStatus());
		fillValue(sb, serviceRequest.getAsset().getSerialNumber());
		fillValue(sb, serviceRequest.getCostCenter());
		fillValue(sb, serviceRequest.getAccountName());
		fillValue(sb, serviceRequest.getPrimaryContact().getFirstName());
		fillValue(sb, serviceRequest.getPrimaryContact().getLastName());
		fillValue(sb, serviceRequest.getPrimaryContact().getEmailAddress());
		fillValue(sb, serviceRequest.getPrimaryContact().getWorkPhone());
		fillValue(sb, serviceRequest.getRequestor().getFirstName());
		fillValue(sb, serviceRequest.getRequestor().getLastName());
		fillValue(sb, serviceRequest.getRequestor().getEmailAddress());
		fillValue(sb, serviceRequest.getRequestor().getWorkPhone());
		
		
		
	}
	//Added for CI BRD 14-02-01-ENDS

}
