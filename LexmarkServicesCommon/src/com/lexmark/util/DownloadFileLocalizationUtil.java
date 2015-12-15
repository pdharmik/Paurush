package com.lexmark.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class DownloadFileLocalizationUtil {
	public static String bundleName = "com.lexmark.services.resources.messages";
	private static String serviceRequestFileName = "serviceRequest.fileName";
	private static String serviceRequestHeader = "serviceRequest.csv.header";
	private static String serviceRequestPDFHeader = "serviceRequest.pdf.header";
	private static String deviceListHeader = "deviceFinder.csv.header";
	private static String deviceListHeaderGridView = "deviceFinder.csv.gridView.header";
	private static String deviceListFileName = "deviceFinder.fileName";
	private static String meterReadFileName = "meterRead.fileName";
	private static String assetListFileName = "assetList.fileName";
	private static String assetListHeader = "assetList.csv.header";
	//Added for CI 14-02-01
	private static String deviceReqListFileName = "Default.Download.FileName";
	// Added for Page Counts Grid CSV Header
	private static String pageCountsListCSVHeader = "pageCounts.csv.header";
	
	

	/**. pdf file header to be downloaded for change management history */ 
	private static String cmHistoryPDFHeader = "changemanagementHistory.pdf.header";
	/**. csv file header to be downloaded for change management history */ 
	private static String cmHistoryCSVHeader = "changemanagementHistory.csv.header";
	/**. csv file name change management history */ 
	private static String DefaultFileName = "Default.Download.FileName";
	
	
	/**
	 * @return the cmHistoryPDFHeader
	 */
	public static String getCmHistoryPDFHeader() {
		return cmHistoryPDFHeader;
	}

	/**
	 * @param cmHistoryPDFHeader the cmHistoryPDFHeader to set
	 */
	public static void setCmHistoryPDFHeader(String cmHistoryPDFHeader) {
		DownloadFileLocalizationUtil.cmHistoryPDFHeader = cmHistoryPDFHeader;
	}

	/**
	 * @return the cmHistoryCSVHeader
	 */
	public static String getCmHistoryCSVHeader() {
		return cmHistoryCSVHeader;
	}

	/**
	 * @param cmHistoryCSVHeader the cmHistoryCSVHeader to set
	 */
	public static void setCmHistoryCSVHeader(String cmHistoryCSVHeader) {
		DownloadFileLocalizationUtil.cmHistoryCSVHeader = cmHistoryCSVHeader;
	}

	
	private static String getPropertyValue(String bundleName, String _label, Locale locale){
		String _value = null;
		ResourceBundle bundle = PropertiesMessageUtil.createUTF8ResourceBundle(ResourceBundle.getBundle(bundleName, locale, Thread.currentThread().getContextClassLoader()));
		try{
			_value = bundle.getString(_label);
		}catch(MissingResourceException e){
			_value = "!! Error:" + _label +" not found !!";
		}
		return _value;
	}
	
	public static String getPropertyLocaleValue(String _label, Locale locale){
		String _value = null;
		_value = getPropertyValue(bundleName, _label, locale);
		return _value;
	}
	
	private static String getCSVHeader(Locale locale, String _label, boolean isDistributor){
		String CSVHeader = null;
		CSVHeader = getPropertyValue(bundleName, _label, locale);
		return CSVHeader;	
	}
	private static String getPDFHeader(Locale locale, String _label, boolean isDistributor){
		String CSVHeader = null;
		CSVHeader = getPropertyValue(bundleName, _label, locale);
		return CSVHeader;	
	}
	private static String[] getXLSHeader(Locale locale, String _label, boolean isDistributor){
		String CSVHeader = null;
		if(!isDistributor)
			_label = "agreement.noneDistributor.header";
		CSVHeader = getPropertyValue(bundleName, _label, locale);
		return CSVHeader.split(",");	
	}
		
	
	private static String getServiceRequestsFileName(Locale locale){	
		 return encodeDecodeFileName(getPropertyValue(bundleName, serviceRequestFileName, locale));
	}
	
	public static String getDeviceListFileName(Locale locale){	
		 return encodeDecodeFileName(getPropertyValue(bundleName, deviceListFileName, locale));
	}
	//Added for CI 14-02-01
	public static String getDeviceReqListFileName(Locale locale){	
		 return encodeDecodeFileName(getPropertyValue(bundleName, deviceReqListFileName, locale));
	}
	
	public static String getMeterReadFileName(Locale locale){	
		 return encodeDecodeFileName(getPropertyValue(bundleName, meterReadFileName, locale));
	}
	
	public static String getServiceRequestsCSVFileHeader(Locale locale){
		return getCSVHeader(locale, serviceRequestHeader, true);
	}
	
	public static String getDeviceListCSVFileHeader(Locale locale){
		return getCSVHeader(locale, deviceListHeader, true);
	}
	
	public static String getDeviceListGridViewCSVFileHeader(Locale locale){
		return getCSVHeader(locale, deviceListHeaderGridView, true);
	}
	
	//Added for CI 14-02-01 deviceListHeaderGridView
	public static String getDeviceInfoRequestListCSVFileHeader(Locale locale){
		return getCSVHeader(locale, deviceListHeader, true);
	}
	
	public static String[] getClaimsXLSFileHeader(Locale locale){
		return getXLSHeader(locale, serviceRequestHeader, true);
	}
	
	public static String getServiceRequestsCSVFileName(Locale locale){
		return encodeDecodeFileName(getServiceRequestsFileName(locale).trim())+".csv";
	}
	public static String getServiceRequestsPDFFileName(Locale locale) {
		return encodeDecodeFileName(getServiceRequestsFileName(locale).trim())+".pdf";
	}
	
	public static String getClaimsXLSFileName(Locale locale) {
		return encodeDecodeFileName(getServiceRequestsFileName(locale).trim())+".xls";
	}

	public static String[] getServiceRequestsPDFHeaders(Locale locale) {
		return getPDFHeader(locale, serviceRequestPDFHeader, true).split(",");
	}
	
	/**.
	 * This method retrieves the file name from resource bundle as per the 
	 * Locale and file name property key passed as argument and returns it 
	 * appending the file extension also passed as argument. If No file name
	 * retrieved, returns the default file name.
	 * @param fileTypeExtn
	 * @param fileNamePropsKey
	 * @param locale
	 * @return
	 * @author sagar
	 * @throws UnsupportedEncodingException  
	 */
	public static String getLocalizedFileName(String fileTypeExtn, 
			String fileNamePropsKey, Locale locale)   {
		//if(!fileTypeExtn.isEmpty() && fileNamePropsKey.isEmpty()){
			String _val = getPropertyValue(bundleName, fileNamePropsKey, locale);
			if(!_val.isEmpty()){
				
				return encodeDecodeFileName(_val.trim())+ "." + fileTypeExtn;
			}			
		//}
		return DefaultFileName+"."+fileTypeExtn;
		
	}
	
	/**.
	 * This method retrieves the file header from resource bundle as per the 
	 * Locale and file header property key passed as argument and returns it.
	 * 
	 * @param fileHeaderPropsKey
	 * @param locale
	 * @return String
	 * @author sagar
	 */
	public static String getLocalizedFileHeader(String fileHeaderPropsKey, 
			Locale locale){	
		 return getPropertyValue(bundleName, fileHeaderPropsKey, locale);
	}
	public static String getAssetListFileName(Locale locale) throws UnsupportedEncodingException{	
		 return encodeDecodeFileName(getPropertyValue(bundleName, assetListFileName, locale));
	}
	public static String getAssetListCSVFileHeader(Locale locale){
		return getCSVHeader(locale, assetListHeader, true);
	}
	public static String encodeDecodeFileName(String fileName){
		try{
			return URLDecoder.decode(URLEncoder.encode(fileName, "UTF-8"), "ISO8859_1");
		}catch(UnsupportedEncodingException ue){
			return fileName;
		}
		 
	}
	
	// added for Page counts Grid CSV Header
	public static String getPageCountsListCSVFileHeader(Locale locale){
		return getCSVHeader(locale, pageCountsListCSVHeader, true);
	}
}
