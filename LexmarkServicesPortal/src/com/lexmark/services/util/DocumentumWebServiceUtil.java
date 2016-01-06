package com.lexmark.services.util;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;
import java.util.TimeZone;
import java.util.regex.Pattern;

import javax.portlet.ActionRequest;
import javax.portlet.PortletSession;
import javax.xml.ws.soap.SOAPFaultException;

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.domain.GlobalAccount;
import com.lexmark.domain.LexmarkTransaction;
import com.lexmark.service.api.GlobalService;
import com.lexmark.service.impl.real.GlobalServiceFacadeImpl;
import com.lexmark.util.LocaleUtil;
import com.lexmark.util.PerformanceTracker;

public class DocumentumWebServiceUtil {

 	private static String CONFIG_FILE_NAME = "/documentumWebService.properties";
	private static final String FILE_EXTENSION_KEY_PREFIX= "documentum.FileTypeForFileExtension.";
 	private static final String FILE_TYPE_INCO_KEY_PREFIX = "documentum.IconForFileType.";
	private static final SimpleDateFormat  FILE_CONTENT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
 	private static HashMap<String, String>  fileTypeMap;
	private static HashMap<String, String>  fileTypeIconMap;
	
	/**
	 * 
	 */
	public static final String DOCUMENT_TYPE_REPORT = "REPORT";
	/**
	 * 
	 */
	public static final String DOCUMENT_TYPE_DOCUMENT = "DOCUMENT";
	/**
	 * 
	 */
	public static final String DOCUMENT_TYPE_OTHER = "other";
 	/**
 	 * 
 	 */
 	public static String REPOSITORY_NAME=  getConfigProperties().getProperty("documentum.RepositoryName");
	/**
	 * 
	 */
	public static String SUPERUSER_NAME=   getConfigProperties().getProperty("documentum.UserId");
	/**
	 * 
	 */
	public static String APPLICATION_NAME= getConfigProperties().getProperty("documentum.ApplicationName");
	/**
	 * 
	 */
	public static String PASSWORD=         getConfigProperties().getProperty("documentum.Password");
 	/**
 	 * 
 	 */
 	public static String SERVICE_END_POINT=getConfigProperties().getProperty("documentum.Service");
 	/**
 	 * 
 	 */
 	public static String REPORT_FOLDER_PATH = getConfigProperties().getProperty("documentum.ReportFolder");
 	/**
 	 * 
 	 */
 	public static String DOCUMENT_FOLDER_PATH = getConfigProperties().getProperty("documentum.DocumentFolder");
 	/**
 	 * 
 	 */
 	public static String OBJECT_TYPE = "portal_services_document";
 	/**
 	 * 
 	 */
 	public static String DOCUMENT_SERVER_TIMEZONE =  getConfigProperties().getProperty("documentum.ServerTimeZone");

 	
 	
 	private static Properties documentumProperty;
	/**
	 * @return Properties 
	 */
	public static Properties getConfigProperties() {
		if(documentumProperty == null) {
			Properties props = new Properties();
			InputStream in=null;
			try {
				in = DocumentumWebServiceUtil.class.getResourceAsStream(CONFIG_FILE_NAME);
				props.load(in);
				in.close();
			} catch (IOException e) {
				new RuntimeException("Fail to get reportScheduler.properties Property configuration file.", e);
			}

			documentumProperty =  props;
		}
		return documentumProperty;
	} 
 	
 	/**
 	 * @param filePath 
 	 * @return String 
 	 */
 	private static String getFileNameFromPath(String filePath){
 		if(filePath == null){
 			return "";
 		}
 		String filePathUniStyle = filePath.trim().replace("\\", "/");
 		String fileName = filePathUniStyle.substring(filePathUniStyle.lastIndexOf("/") + 1);
 		return fileName;
 	}
 	
 	/**
 	 * @param filePath 
 	 * @return String 
 	 */
 	public static String getDocumentTypeFromFilePath(String filePath){
 		if(filePath == null || filePath.lastIndexOf(".") < 0){
 			return DocumentumWebServiceUtil.DOCUMENT_TYPE_OTHER;
 		}String fileExt = filePath.trim().substring(filePath.lastIndexOf(".") + 1).toLowerCase();
 		return getDocumentType(fileExt);
 	}
 	
 	/**
 	 * @return int 
 	 */
 	public static int getDocumentumServerTimeOffset() {
 		TimeZone tz = TimeZone.getTimeZone(DOCUMENT_SERVER_TIMEZONE);
		Calendar calendar = Calendar.getInstance(tz);
		return calendar.get(Calendar.ZONE_OFFSET) / LexmarkConstants.MILLISECONDS_IN_HOUR;
 	}
 	
 	/**
 	 * @param fileName 
 	 * @param fileContentDate 
 	 * @param uniqueId 
 	 * @return String 
 	 */
 	public static String mergeFileName(String fileName, Date fileContentDate, String uniqueId) {
 		String nameWithoutExtention = "";
 		String fileExtentionWithDot = "";
 		if(fileName.trim().lastIndexOf(".")<0) {
 			nameWithoutExtention = fileName.trim();
 			fileExtentionWithDot = "";
 		} else {
 			nameWithoutExtention = fileName.trim().substring(0, fileName.trim().lastIndexOf("."));
 			fileExtentionWithDot = fileName.trim().substring(fileName.lastIndexOf("."));
 		}
 		StringBuilder sb = new StringBuilder();
 		sb.append(FILE_CONTENT_DATE_FORMAT.format(fileContentDate))
 		  .append(" ")
 		  .append(nameWithoutExtention)
 		  .append("-")
 		  .append(uniqueId)
 		  .append(fileExtentionWithDot);
 		return sb.toString(); 
 	}
 	
 	/**
 	 * @param request 
 	 * @param filePath 
 	 * @param definitionId 
 	 * @param reportUniqueId 
 	 * @param docType 
 	 * @param globalService 
 	 * @param fileContentDate 
 	 * @return DocumentInfo 
 	 */
 	/*public static DocumentInfo getDocumentInfo(ActionRequest request, String filePath, String definitionId, String reportUniqueId,  String docType, GlobalService globalService, Date fileContentDate){
		DocumentInfo metaData = new DocumentInfo();
		
		String localeCode = LocaleUtil.getSupportLocaleCode(request.getLocale());
		String fileName = getFileNameFromPath(filePath);
		String fileType = getDocumentTypeFromFilePath(filePath);
		
		metaData.setLocale(localeCode);
		metaData.setAContentType(fileType);
		metaData.setFileName(fileName);
		metaData.setObjectName(mergeFileName(fileName, fileContentDate, reportUniqueId));
		metaData.setFileContentDate(fileContentDate);
		if(DOCUMENT_TYPE_REPORT.equalsIgnoreCase(docType)) {
			metaData.setRFolderPath(REPORT_FOLDER_PATH);
		} else {
			metaData.setRFolderPath(DOCUMENT_FOLDER_PATH);
		}
		metaData.setRObjectType(OBJECT_TYPE);
		metaData.setDefinitionId(definitionId);
		String mdmId = PortalSessionUtil.getMdmId(request.getPortletSession());
		metaData.setMdmId(mdmId);
		String mdmLevel = PortalSessionUtil.getMdmLevel(request.getPortletSession());
		metaData.setMdmlevel(mdmLevel);
		metaData.setUsernumber(PortalSessionUtil.getServiceUser(request.getPortletSession()).getUserNumber());
		
		GlobalServiceFacadeImpl globalServiceFacade = new GlobalServiceFacadeImpl();
		globalServiceFacade.setGlobalService(globalService);
		PortletSession session = request.getPortletSession();
		LexmarkTransaction lexmarkTran = PerformanceTracker.startTracking(LexmarkConstants.TARGET_SYSTEM_SIEBEL, "retriveGlobalAccount"
				, mdmId, PortalSessionUtil.getServiceUser(session).getEmailAddress());
		GlobalAccount account = globalServiceFacade.retriveGlobalAccount(mdmId, mdmLevel);
		PerformanceTracker.endTracking(lexmarkTran);
		metaData.setLegalName(account.getLegalName());

		return metaData;
 	}*/

 	/**
 	 * @param runtimeException 
 	 * @return boolean 
 	 */
 	public static boolean isFileFormatSupported(RuntimeException runtimeException) {
 		
			if(runtimeException.getCause() instanceof SOAPFaultException) {
				SOAPFaultException soapException = (SOAPFaultException) runtimeException.getCause();
				if(isFormatWrong(soapException.getMessage())) {
					return false;
				}
			}
			return true;
 	}
 	
	/**
	 * @param message 
	 * @return boolean 
	 */
	private static boolean isFormatWrong(String message) {
		String regEx="Format .*is invalid";
		return Pattern.compile(regEx).matcher(message).find();
	}
 	
 	/**
 	 * @param fileExtension 
 	 * @return String 
 	 */
 	public static String getDocumentType(String fileExtension) {
 		if(fileTypeMap == null) {
 			initFileTypeMap();
 		}
 		String type = fileTypeMap.get(fileExtension);
 		if(type == null) {
 			return DOCUMENT_TYPE_OTHER;
 		}
 		return type;
 	}
 	
 	/** 
 	 *  
 	 */
 	private static void initFileTypeMap() {
		fileTypeMap = new HashMap<String, String>();
			Properties props = getConfigProperties();
			for(Object key : props.keySet()) {
				String keyString = (String) key;
				if(keyString.indexOf(FILE_EXTENSION_KEY_PREFIX) > -1) {
					String value = props.getProperty(keyString);
					String fileExtensionKey = keyString.substring(FILE_EXTENSION_KEY_PREFIX.length());
					fileTypeMap.put(fileExtensionKey, value);
				}
			}
 	}

 	/**
 	 * @param fileType 
 	 * @return String 
 	 */
 	public static String getFileTypeIcon(String fileType) {
 		if(fileTypeIconMap == null) {
 			fileTypeIconMap = new HashMap<String, String>();
 			Properties props = getConfigProperties();
 			for(Object key : props.keySet()) {
 				String keyString = (String) key;
 				if(keyString.indexOf(FILE_TYPE_INCO_KEY_PREFIX) > -1) {
 					String value = props.getProperty(keyString);
 					String fileExtensionKey = keyString.substring(FILE_TYPE_INCO_KEY_PREFIX.length());
 					fileTypeIconMap.put(fileExtensionKey, value);
 				}
 			}
 		}
 		String icon = fileTypeIconMap.get(fileType);
 		if(icon == null) {
 			return fileTypeIconMap.get(DOCUMENT_TYPE_OTHER);
 		}
 		return icon;
 	}
 	
 	/**
 	 * @return Set<String> 
 	 */
 	public static Set<String> getSupportFileExtensions() {
 		if(fileTypeMap == null) {
 			initFileTypeMap();
 		}
 		return fileTypeMap.keySet(); 
 	}
}
