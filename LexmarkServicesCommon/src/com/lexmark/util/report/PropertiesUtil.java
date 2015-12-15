package com.lexmark.util.report;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.TimeZone;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class PropertiesUtil {
	private static final String CONFIG_FILE_NAME = "/reportScheduler.properties";
	private static Logger logger = LogManager.getLogger(PropertiesUtil.class);
	
	public static final String  BOXI_URL_HEADER = getConfigProperties().getProperty("boxiUrlHeader"); 
	public static final String  BOXI_SERVICE = getConfigProperties().getProperty("boxiService");
	public static final String  BOXI_REPORT_MODE = getConfigProperties().getProperty("boxiReportMode"); 
	public static final String  BOXI_USER_ID = getConfigProperties().getProperty("boxiUserId"); 
	public static final String  BOXI_USER_PASSWORD = getConfigProperties().getProperty("boxiUserPassword"); 
	public static final String  BOXI_AUTH_TYPE = getConfigProperties().getProperty("boxiAuthType"); 
	
	public static final String  SCHEDULER_FINISH_TIME = getConfigProperties().getProperty("schedulerFinishTime"); 
	public static final String  SCHEDULER_ERROR_THREASHHOLD = getConfigProperties().getProperty("schedulerErrorThreshhold"); 

	public static final String  SCHEDULER_RESULT_MAIL_LIST  = getConfigProperties().getProperty("schedulerResultEmailList"); 
	
	
	public static final String  ALL_REPORT_FILE_TYPES = getConfigProperties().getProperty("scheduler.report.types");
	public static String DBDriverName = getConfigProperties().getProperty("dbDriverName"); 
	public static String DBConnStr = getConfigProperties().getProperty("dbConnStr"); 
	public static String DBUsername = getConfigProperties().getProperty("dbUserName"); 
	public static String DBPassword = getConfigProperties().getProperty("dbPassword"); 
	
	//Documentum static constants
	
	public static final String DOCUMENTUM_SERVICE_END_POINT = getConfigProperties().getProperty("documentum.Service"); 
	public static final String DOCUMENTUM_REPOSITORYNAME=   getConfigProperties().getProperty("documentum.RepositoryName"); 
	public static final String DOCUMENTUM_APPLICATIONNAME=  getConfigProperties().getProperty("documentum.ApplicationName");
	public static final String DOCUMENTUM_USERID=           getConfigProperties().getProperty("documentum.UserId");         
	public static final String DOCUMENTUM_PASSWORD=         getConfigProperties().getProperty("documentum.Password");       
	public static final String DOCUMENTUM_REPORTFOLDER=     getConfigProperties().getProperty("documentum.ReportFolder");   
	public static final String DOCUMENTUM_REPORTOVERWRITE=  getConfigProperties().getProperty("documentum.ReportOverwrite");	
	

	public static final String LDAP_URL =         getConfigProperties().getProperty("LDAP.url");       
	public static final String LDAP_USERNAME =     getConfigProperties().getProperty("LDAP.userName");   
	public static final String LDAP_PASSWORD =  getConfigProperties().getProperty("LDAP.password");	
	
	private static  Integer defaultSchedulerFinishHour = null;
	private static  Integer defaultSchedulerFinishMinute = null;
	public static Integer getDefaultSchedulerFinishHour() {
		if(defaultSchedulerFinishHour== null) {
			initSchedulerFinishTime();
		}
		return defaultSchedulerFinishHour;
	}

	public static Integer getDefaultSchedulerFinishMinute() {
		if(defaultSchedulerFinishMinute== null) {
			initSchedulerFinishTime();
		}
		return defaultSchedulerFinishMinute;
	}

	private static void initSchedulerFinishTime() {
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date date = calendar.getTime();
		SimpleDateFormat dateFormatter =
			new SimpleDateFormat("HHmm");
		try {
			dateFormatter.setTimeZone(TimeZone.getTimeZone("GMT"));
			date = dateFormatter.parse(SCHEDULER_FINISH_TIME);
			calendar.setTime(date);
			defaultSchedulerFinishHour =  calendar.get(Calendar.HOUR);
			defaultSchedulerFinishMinute =  calendar.get(Calendar.MINUTE);
		}catch (ParseException pe) {
			defaultSchedulerFinishHour = 8;
			defaultSchedulerFinishMinute = 0;
		}
	}
	
	private static Properties reportSchedulerProperty;

	static {
		reportSchedulerProperty = getConfigProperties();
	}

	public static String get(String key) {
		try {
			return reportSchedulerProperty.getProperty(key);
		} catch (Exception e) {
			logger.error(CONFIG_FILE_NAME + " has configuration error"); 
		}
		return null;
	}
	
	public static Properties getConfigProperties() {
		if(reportSchedulerProperty == null) {
			Properties props = new Properties();
			InputStream in;
			try {
				in = PropertiesUtil.class.getResourceAsStream(CONFIG_FILE_NAME);
				props.load(in);
				in.close();
			} catch (IOException e) {
				new RuntimeException("Fail to get reportScheduler.properties Property configuration file.", e);
			}

			reportSchedulerProperty =  props;
		}
		return reportSchedulerProperty;
	} 
	
	
}
