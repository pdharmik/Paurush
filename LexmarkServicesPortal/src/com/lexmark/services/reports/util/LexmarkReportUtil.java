package com.lexmark.services.reports.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TimeZone;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.portlet.ActionRequest;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lexmark.constants.LexmarkConstants;
import com.lexmark.domain.GlobalAccount;
import com.lexmark.domain.ReportScheduleParameter;
import com.lexmark.result.ReportListRow;
import com.lexmark.result.ScheduleReportResult;
import com.lexmark.service.api.GlobalService;
import com.lexmark.service.impl.real.GlobalServiceFacadeImpl;
import com.lexmark.services.form.ScheduleReportForm;
import com.lexmark.services.reports.bean.DocDelDownloadInfo;
import com.lexmark.services.reports.bean.DocListInfo;
import com.lexmark.services.reports.bean.DocumentInformation;
import com.lexmark.services.reports.bean.DocumentList;
import com.lexmark.services.reports.bean.ReportListRecord;
import com.lexmark.services.reports.bean.ScheduleReportInfoBean;
import com.lexmark.services.reports.enums.ScheduleEnum;
import com.lexmark.services.reports.service.LexmarkReportServiceImpl;
import com.lexmark.services.util.ContractFactory;
import com.lexmark.services.util.PortalSessionUtil;
import com.lexmark.util.LocaleUtil;
import com.lexmark.util.TimezoneUtil;

public class LexmarkReportUtil {

	private static HashMap<String, String> fileTypeMap = null;
	public static final String DOCUMENT_TYPE_OTHER = "other";
	public static final String DOCUMENT_TYPE_REPORT = "REPORT";
	public static final String DOCUMENT_TYPE_DOCUMENT = "DOCUMENT";
	private static final String FILE_EXTENSION_KEY_PREFIX = "imageNow.FileTypeForFileExtension.";
	private static final String FILE_TYPE_ICON_KEY_PREFIX = "imageNow.IconForFileType.";
	private static String CONFIG_FILE_NAME = "/imageNow.properties";
	private static final SimpleDateFormat FILE_CONTENT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

	public static String DRAWER = getConfigProperties().getProperty("imageNow.Drawer");
	public static String CREATE_DOC_URL = getConfigProperties().getProperty("imageNow.createDocURL");
	public static String BASEURL = LexmarkReportUtil.getConfigProperties().getProperty("imageNow.integrationServerURL");
	public static String LIST_DOC_URL = LexmarkReportUtil.getConfigProperties().getProperty("imageNow.listDocURL");
	public static String USERNAME = LexmarkReportUtil.getConfigProperties().getProperty("imageNow.username");
	public static String PASSWORD = LexmarkReportUtil.getConfigProperties().getProperty("imageNow.password");
	public static String REPORT_TYPE = LexmarkReportUtil.getConfigProperties().getProperty("imageNow.ReportType");
	public static String SECURED_TYPE = LexmarkReportUtil.getConfigProperties().getProperty("imageNow.SecuredType");
	public static String PUBLIC_TYPE = LexmarkReportUtil.getConfigProperties().getProperty("imageNow.PublicType");

	private static String PROPERTY_LIST = LexmarkReportUtil.getConfigProperties().getProperty("imageNow.PropertyList");
	private static String FIELD_LIST = LexmarkReportUtil.getConfigProperties().getProperty("imageNow.FieldList");
	private static final String KEY_ALGORITHM = LexmarkReportUtil.getConfigProperties().getProperty("imageNow.KeyAlgorithm");

	public static String BO_AUTH_TYPE = LexmarkReportUtil.getConfigProperties().getProperty("boAuthType");
	public static String BO_PASSWORD = LexmarkReportUtil.getConfigProperties().getProperty("boPassword");
	public static String BO_USER = LexmarkReportUtil.getConfigProperties().getProperty("boUser");
	public static String BO_CMS_NAME = LexmarkReportUtil.getConfigProperties().getProperty("boCmsName");
	public static String BO_DESTINATION = LexmarkReportUtil.getConfigProperties().getProperty("boDestination");

	public static String SERVER_TIMEZONE = "GMT-5";
	private static SecretKey key = null;
	public static final DateTimeFormatter ISO_FORMAT = ISODateTimeFormat.dateTime().withZone(DateTimeZone.UTC);

	public final static String FILE_NAME = "FileName";
	public final static String OBJECT_NAME = "ObjectName";
	public final static String LOCALE = "Locale";
	public final static String MDM_ID = "MdmId";
	public final static String MDM_LEVEL = "MdmLevel";
	public final static String USER_NUMBER = "UserNumber";
	public final static String DEFINITION_ID = "DefinitionId";
	public final static String LEGAL_NAME = "LegalName";
	public final static String ACONTENT_TYPE = "AContentType";

	private static HashMap<String, String> fileTypeIconMap = null;

	private static final Logger logger = LogManager.getLogger(LexmarkReportUtil.class);

	private static Properties imageNowProperty;

	public static Properties getConfigProperties() {
		if (imageNowProperty == null) {
			Properties props = new Properties();
			InputStream in;
			try {
				in = LexmarkReportUtil.class.getResourceAsStream(CONFIG_FILE_NAME);
				props.load(in);
				in.close();
			} catch (IOException e) {
				new RuntimeException("Fail to get imageNow.properties Property configuration file.", e);
			}

			imageNowProperty = props;
		}
		return imageNowProperty;
	}

	public static DocumentInformation getDocumentInfo(String docType, Date fileContentDate, ActionRequest request, String definitionId, GlobalService globalService, String filePath,
			String reportUniqueId, byte[] fileContentInBytes) {

		DocumentInformation docInfo = new DocumentInformation();

		docInfo.setDrawerId(DRAWER);
		if (DOCUMENT_TYPE_REPORT.equalsIgnoreCase(docType)) {
			docInfo.setType(REPORT_TYPE);
		} else {
			docInfo.setType(SECURED_TYPE);
		}
		docInfo.setField5(ISO_FORMAT.print(new DateTime(fileContentDate)));
		docInfo.setPropertyMap(getPropertyMap(request, definitionId, globalService, filePath, fileContentDate, reportUniqueId));
		docInfo.setFileContentInBytes(fileContentInBytes);
		return docInfo;

	}

	public static DocListInfo getDocListInfo(int definitionId) {

		logger.info("Start-------LexmarkReportUtil-----getDocListInfo");
		DocListInfo docListInfo = new DocListInfo();

		docListInfo.setDefinitionId(Integer.toString(definitionId));

		if (definitionId != -1) {
			docListInfo.setDocType(SECURED_TYPE);
		} else {
			docListInfo.setDocType(PUBLIC_TYPE);
		}
		docListInfo.setDrawerName(DRAWER);
		docListInfo.setPropertyList(PROPERTY_LIST);
		docListInfo.setFieldList(FIELD_LIST);

		logger.info("End-------LexmarkReportUtil-----getDocListInfo");
		return docListInfo;

	}

	public static DocDelDownloadInfo getDocDelDownloadInfo(String documentId) {

		logger.info("Start-------LexmarkReportUtil-----getDocDelDownloadInfo");
		DocDelDownloadInfo docDelDownloadInfo = new DocDelDownloadInfo();

		docDelDownloadInfo.setDocumentId(documentId);
		logger.info("End-------LexmarkReportUtil-----getDocDelDownloadInfo");
		return docDelDownloadInfo;
	}

	private static String getFileNameFromPath(String filePath) {
		if (filePath == null)
			return "";

		String filePathUniStyle = filePath.trim().replace("\\", "/");
		String fileName = filePathUniStyle.substring(filePathUniStyle.lastIndexOf("/") + 1);
		return fileName;
	}

	public static String getDocumentTypeFromFilePath(String filePath) {
		if (filePath == null || filePath.lastIndexOf(".") < 0)
			return LexmarkReportUtil.DOCUMENT_TYPE_OTHER;
		String fileExt = filePath.trim().substring(filePath.lastIndexOf(".") + 1).toLowerCase();
		return getDocumentType(fileExt);
	}

	public static String getDocumentType(String fileExtension) {
		if (fileTypeMap == null) {
			initFileTypeMap();
		}
		String type = fileTypeMap.get(fileExtension);
		if (type == null) {
			return DOCUMENT_TYPE_OTHER;
		}
		return type;
	}

	private static void initFileTypeMap() {
		fileTypeMap = new HashMap<String, String>();
		Properties props = getConfigProperties();
		for (Object key : props.keySet()) {
			String keyString = (String) key;
			if (keyString.indexOf(FILE_EXTENSION_KEY_PREFIX) > -1) {
				String value = props.getProperty(keyString);
				String fileExtensionKey = keyString.substring(FILE_EXTENSION_KEY_PREFIX.length());
				fileTypeMap.put(fileExtensionKey, value);
			}
		}
	}

	public static String mergeFileName(String fileName, Date fileContentDate, String uniqueId) {
		String nameWithoutExtention = "";
		String fileExtentionWithDot = "";
		if (fileName.trim().lastIndexOf(".") < 0) {
			nameWithoutExtention = fileName.trim();
			fileExtentionWithDot = "";
		} else {
			nameWithoutExtention = fileName.trim().substring(0, fileName.trim().lastIndexOf("."));
			fileExtentionWithDot = fileName.trim().substring(fileName.lastIndexOf("."));
		}
		StringBuilder sb = new StringBuilder();
		sb.append(FILE_CONTENT_DATE_FORMAT.format(fileContentDate)).append(" ").append(nameWithoutExtention).append("-").append(uniqueId).append(fileExtentionWithDot);
		return sb.toString();
	}

	public static String getFileTypeIcon(String fileType) {
		if (fileTypeIconMap == null) {
			fileTypeIconMap = new HashMap<String, String>();
			Properties props = getConfigProperties();
			for (Object key : props.keySet()) {
				String keyString = (String) key;
				if (keyString.indexOf(FILE_TYPE_ICON_KEY_PREFIX) > -1) {
					String value = props.getProperty(keyString);
					String fileExtensionKey = keyString.substring(FILE_TYPE_ICON_KEY_PREFIX.length());
					fileTypeIconMap.put(fileExtensionKey, value);
				}
			}
		}
		String icon = fileTypeIconMap.get(fileType);
		if (icon == null) {
			return fileTypeIconMap.get(DOCUMENT_TYPE_OTHER);
		}
		return icon;
	}

	public static Object getXmltoObject(String xmlContent, int classNbr) throws JAXBException {
		ByteArrayInputStream xmlContentBytes = new ByteArrayInputStream(xmlContent.getBytes());
		JAXBContext context = null;
		Unmarshaller unmarshaller = null;
		Object response = null;

		switch (classNbr) {

		case 1:
			context = JAXBContext.newInstance(DocumentList.class);
			unmarshaller = context.createUnmarshaller();
			DocumentList docListObj = (DocumentList) unmarshaller.unmarshal(xmlContentBytes);
			response = docListObj;
			break;
		}

		return response;
	}

	public static <T> T jsonToObject(String jsonInput, Class<T> type) {
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		return gson.fromJson(jsonInput, type);
	}

	public static String objectToJson(Object obj) {
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();

		return gson.toJson(obj);
	}

	public static String processHttpResponse(HttpResponse response) throws IllegalStateException, IOException {
		String line = "";
		StringBuffer result = new StringBuffer();
		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

		while ((line = rd.readLine()) != null) {
			result.append(line);
		}

		return result.toString();
	}

	public static HashMap<String, String> getPropertyMap(ActionRequest request, String definitionId, GlobalService globalService, String filePath, Date fileContentDate, String reportUniqueId) {

		String fileName = getFileNameFromPath(filePath);
		String objectName = mergeFileName(fileName, fileContentDate, reportUniqueId);
		String locale = LocaleUtil.getSupportLocaleCode(request.getLocale());
		String mdmId = PortalSessionUtil.getMdmId(request.getPortletSession());
		String mdmLevel = PortalSessionUtil.getMdmLevel(request.getPortletSession());
		String userNbr = PortalSessionUtil.getServiceUser(request.getPortletSession()).getUserNumber();

		GlobalServiceFacadeImpl globalServiceFacade = new GlobalServiceFacadeImpl();
		globalServiceFacade.setGlobalService(globalService);
		GlobalAccount account = globalServiceFacade.retriveGlobalAccount(mdmId, mdmLevel);
		String legalName = account.getLegalName();
		String aContentType = getDocumentTypeFromFilePath(filePath);

		HashMap<String, String> propMap = new HashMap<String, String>();
		propMap.put(FILE_NAME, fileName);
		propMap.put(OBJECT_NAME, objectName);
		propMap.put(LOCALE, locale);
		propMap.put(MDM_ID, mdmId);
		propMap.put(MDM_LEVEL, mdmLevel);
		propMap.put(USER_NUMBER, userNbr);
		propMap.put(DEFINITION_ID, definitionId);
		propMap.put(LEGAL_NAME, legalName);
		propMap.put(ACONTENT_TYPE, aContentType);

		return propMap;
	}

	public static void initializeKey() {
		byte[] keyBytes = Base64.decodeBase64(LexmarkReportUtil.getConfigProperties().getProperty("imageNow.Key"));
		key = new SecretKeySpec(keyBytes, KEY_ALGORITHM);
	}

	public static String encryptURL(String url) {
		StringBuffer urlStringBuffer = new StringBuffer(url);
		try {

			initializeKey();
			Mac mac = Mac.getInstance("HMACsha1");
			mac.init(key);
			String ts = ISO_FORMAT.print(new DateTime((System.currentTimeMillis() + 2 * 60 * 1000)));
			logger.info("Timestamp" + ts);
			urlStringBuffer.append("?");
			urlStringBuffer.append("ts=").append(ts);
			byte[] hmac = mac.doFinal(urlStringBuffer.toString().getBytes("UTF-8"));
			urlStringBuffer.append("&token=").append(Base64.encodeBase64URLSafeString(hmac));
		} catch (NoSuchAlgorithmException e) {
			logger.error("error occured " + e.getCause());
		} catch (UnsupportedEncodingException e) {
			logger.error("error occured " + e.getCause());
		} catch (InvalidKeyException e) {
			logger.error("error occured " + e.getCause());
		}

		return urlStringBuffer.toString();
	}

	public static int getImageNowServerTimeOffset() {
		TimeZone tz = TimeZone.getTimeZone(SERVER_TIMEZONE);
		Calendar calendar = Calendar.getInstance(tz);
		return calendar.get(Calendar.ZONE_OFFSET) / LexmarkConstants.MILLISECONDS_IN_HOUR;
	}

	public static void adjustReportsDate(List<ReportListRecord> reportListRows, float timeZoneOffset) {
		for (ReportListRecord row : reportListRows) {
			Date createdDate = row.getCreatedDate();
			logger.debug("Input Date/ Before Modification*****************************"+createdDate);
			TimezoneUtil.adjustDate(createdDate, timeZoneOffset);
			row.setCreatedDate(createdDate);
		}
	}

	public static void adjustReportsScheduleDate(List<ReportListRecord> reportListRows, float timeZoneOffset) {
		for (ReportListRecord row : reportListRows) {
			Date schedulecreatedDate = row.getScheduleCreateDate();
			TimezoneUtil.adjustDate(schedulecreatedDate, timeZoneOffset);
			row.setScheduleCreateDate(schedulecreatedDate);
		}
	}

	public static ScheduleReportInfoBean getScheduleReportInfoBean(ScheduleReportInfoBean scheduleReportInfoBean, ScheduleReportResult result, String cntry, String lang, String MDMID_LDAP) {
		if (result.getSchedule().getRunFrequency().equals(ScheduleReportForm.RUN_NOW_FREQUENCY)) {
			scheduleReportInfoBean.setScheduleType(ScheduleEnum.RUNNOW.getValue());
		} else {
			scheduleReportInfoBean.setScheduleType(ScheduleEnum.SCHEDULE.getValue());
			if (result.getSchedule().getRunFrequency().equals(ScheduleReportForm.DAILY_FREQUENCY)) {
				scheduleReportInfoBean.setIntervalType(ScheduleEnum.DAILY.getValue());
				scheduleReportInfoBean.setIntervalInDays(String.valueOf(result.getSchedule().getRunInterval()));
			} else if (result.getSchedule().getRunFrequency().equals(ScheduleReportForm.WEEKLY_FREQUENCY)) {
				scheduleReportInfoBean.setIntervalType(ScheduleEnum.WEEKLY.getValue());
				scheduleReportInfoBean.setDayOfWeek(String.valueOf(result.getSchedule().getDayOfWeek()));

			} else if (result.getSchedule().getRunFrequency().equals(ScheduleReportForm.MONTHLY_FREQUENCY)) {
				scheduleReportInfoBean.setIntervalType(ScheduleEnum.MONTHLY.getValue());
				scheduleReportInfoBean.setDayOfMonth(String.valueOf(result.getSchedule().getDayOfMonth()));

			}
         scheduleReportInfoBean.setStartDate(adjustDateWithPreferedTimeZone(result.getSchedule().getEffectiveDate(), result.getSchedule().getPreferedTimezone()));
         scheduleReportInfoBean.setEndDate(adjustDateWithPreferedTimeZone(result.getSchedule().getExpirationDate(), result.getSchedule().getPreferedTimezone()));
		}
		scheduleReportInfoBean.setBoAuthType(BO_AUTH_TYPE);
		scheduleReportInfoBean.setBoPassword(BO_PASSWORD);
		scheduleReportInfoBean.setBoUser(BO_USER);
		scheduleReportInfoBean.setBoCmsName(BO_CMS_NAME);

		scheduleReportInfoBean.setDestination(BO_DESTINATION);
		scheduleReportInfoBean.setScheduleId(result.getSchedule().getId().toString());
		if (result.getSchedule().getReportScheduleParameters() != null) {

			Map<String, String> parameterMap = new HashMap<String, String>();
			for (ReportScheduleParameter reportScheduleParameter : result.getSchedule().getReportScheduleParameters()) {
				parameterMap.put(reportScheduleParameter.getName(), reportScheduleParameter.getValue());
			}
			if ((scheduleReportInfoBean.getIsMDM()).equals("T")) {

				parameterMap.put("MDM_ID", MDMID_LDAP);
				parameterMap.put("MDM_LEVEL", result.getSchedule().getMdmLevel());
				parameterMap.put("cntry", cntry);
				parameterMap.put("lang", lang);
				parameterMap.put("CHL", LexmarkReportServiceImpl.retrieveCHL(result.getSchedule().getUserNumber()));
				parameterMap.put("LBS Account", result.getSchedule().getIsLBSAccount());
			}

			scheduleReportInfoBean.setParameterMap(parameterMap);
		}
		scheduleReportInfoBean.setTitle(result.getSchedule().getId().toString());

		return scheduleReportInfoBean;
	}

	private static Date adjustDateWithPreferedTimeZone(Date inputDate, String preferedTimezone){
               int timeZoneOffSet = 0;
               Calendar inputDateCal = Calendar.getInstance();
                               
               Calendar cal = Calendar.getInstance();
               if (preferedTimezone != null && inputDate != null) {
                       inputDateCal.setTime(inputDate);
                       try {
                               timeZoneOffSet = Integer.parseInt(preferedTimezone);
                               logger.debug("Preferred Time Zone off set : " + timeZoneOffSet);
                               cal.set(Calendar.YEAR, inputDateCal.get(Calendar.YEAR));
                               cal.set(Calendar.MONTH, inputDateCal.get(Calendar.MONTH));
                               cal.set(Calendar.DAY_OF_MONTH, inputDateCal.get(Calendar.DAY_OF_MONTH));

                               cal.set(Calendar.HOUR_OF_DAY, inputDateCal.get(Calendar.HOUR_OF_DAY));
                               cal.set(Calendar.MINUTE, inputDateCal.get(Calendar.MINUTE));
                               cal.set(Calendar.SECOND, inputDateCal.get(Calendar.SECOND));
                               cal.set(Calendar.ZONE_OFFSET, timeZoneOffSet * 60 * 60 * 1000);
                               logger.debug("Time (in UTC) corrected with prefered time zone : " + cal.getTime());
                               inputDate = cal.getTime();
                               if(TimezoneUtil.getCurrentDateInUTC(inputDate).before(TimezoneUtil.getCurrentDateInUTC())){
                            	   inputDate = getFutureDate(TimezoneUtil.getCurrentDateInUTC());
                               }
                       } catch (Exception e) {
                               logger.error("error occured in adjustDateWithPreferedTimeZone : " + e);                                
                       }
               }
               return inputDate;
       }

	private static Date getFutureDate(Date date) { // adding 10 sec
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MINUTE, 5);
		return cal.getTime();
	}

	public static ScheduleReportInfoBean getScheduleReportInfoBeanForRerun(ScheduleReportInfoBean scheduleReportInfoBean, ScheduleReportResult result) {
		if (result.getSchedule().getRunFrequency().equals(ScheduleReportForm.RUN_NOW_FREQUENCY)) {
			scheduleReportInfoBean.setScheduleType(ScheduleEnum.RUNNOW.getValue());
		} else {
			scheduleReportInfoBean.setScheduleType(ScheduleEnum.SCHEDULE.getValue());
			if (result.getSchedule().getRunFrequency().equals(ScheduleReportForm.DAILY_FREQUENCY)) {
				scheduleReportInfoBean.setIntervalType(ScheduleEnum.DAILY.getValue());
				scheduleReportInfoBean.setIntervalInDays(String.valueOf(result.getSchedule().getRunInterval()));
				
			} else if (result.getSchedule().getRunFrequency().equals(ScheduleReportForm.WEEKLY_FREQUENCY)) {
				scheduleReportInfoBean.setIntervalType(ScheduleEnum.WEEKLY.getValue());
				scheduleReportInfoBean.setDayOfWeek(String.valueOf(result.getSchedule().getDayOfWeek()));

			} else if (result.getSchedule().getRunFrequency().equals(ScheduleReportForm.MONTHLY_FREQUENCY)) {
				scheduleReportInfoBean.setIntervalType(ScheduleEnum.MONTHLY.getValue());
				scheduleReportInfoBean.setDayOfMonth(String.valueOf(result.getSchedule().getDayOfMonth()));

			}
			scheduleReportInfoBean.setStartDate(result.getSchedule().getEffectiveDate());
			scheduleReportInfoBean.setEndDate(result.getSchedule().getExpirationDate());

		}
		scheduleReportInfoBean.setBoAuthType(BO_AUTH_TYPE);
		scheduleReportInfoBean.setBoPassword(BO_PASSWORD);
		scheduleReportInfoBean.setBoUser(BO_USER);
		scheduleReportInfoBean.setBoCmsName(BO_CMS_NAME);

		scheduleReportInfoBean.setDestination(BO_DESTINATION);
		scheduleReportInfoBean.setScheduleId(result.getSchedule().getId().toString());
		if (result.getSchedule().getReportScheduleParameters() != null) {

			Map<String, String> parameterMap = new HashMap<String, String>();
			for (ReportScheduleParameter reportScheduleParameter : result.getSchedule().getReportScheduleParameters()) {
				parameterMap.put(reportScheduleParameter.getName(), reportScheduleParameter.getValue());
			}
			scheduleReportInfoBean.setParameterMap(parameterMap);
		}
		scheduleReportInfoBean.setTitle(result.getSchedule().getId().toString());

		return scheduleReportInfoBean;
	}

	public static void sortReportListWithSchesuleDate(List<ReportListRecord> reportListRows) {
		Comparator<ReportListRecord> reportListRowComparator = new ReportListRowsComparator();
		try {
			Collections.sort(reportListRows, reportListRowComparator);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
