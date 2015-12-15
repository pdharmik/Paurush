package com.lexmark.reportScheduler.server;

import java.io.File;
import java.io.RandomAccessFile;
import java.net.URL;
import java.nio.channels.FileLock;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.CountDownLatch;
import org.apache.logging.log4j.Logger;

import org.apache.log4j.PropertyConfigurator;
import org.apache.logging.log4j.LogManager;

import com.lexmark.constants.JobTypeEnum;
import com.lexmark.constants.ReportSelectionFlagEnum;
import com.lexmark.domain.ReportJob;
import com.lexmark.reportScheduler.domain.ReportStatistics;
import com.lexmark.reportScheduler.util.BeanFactoryUtil;
import com.lexmark.reportScheduler.util.DateUtil;
import com.lexmark.reportScheduler.util.MailUtil;
import com.lexmark.service.api.ReportScheduleService;
import com.lexmark.util.report.PropertiesUtil;
import org.apache.logging.log4j.LogManager;

public class Scheduler {
	private static Logger logger = LogManager.getLogger(Scheduler.class);
	/**
	 * @param args
	 */
	
	private static final int DEFAULT_ERROR_CODE = 1;
	private Date startDateTime;
	private Date endDateTime;
	private Date kickoffDateTime;
	private Date expirationDateTime;
	private String jobBatchId;
	private JobTypeEnum  reportJobType;
	
	private ReportEngine[] reportEngines;
	private ReportScheduleService scheduleService;
	private ReportStatistics  schedulerStatics =  new ReportStatistics();
	private String stdOutMessage;

	private SchedulerParameters schedulerParameters;
	private DerivedValue derivedValue = new DerivedValue();


	public Scheduler(SchedulerParameters schedulerParameters) {
		this.schedulerParameters = schedulerParameters;
		scheduleService = (ReportScheduleService)BeanFactoryUtil.getBean("reportScheduleService");
	}
	
	public ReportStatistics getReportStatistics() {
		return schedulerStatics;
	}
	
	private static final String SUBMITNOW_LOG_CONFIGURATION_FILE = "log4j_runNow.properties";
	private static final String SCHEDULE_LOG_CONFIGURATION_FILE = "log4j.properties";
	private static void setDifferentLogFile(String[] args) {
		boolean submitNow = false;
		for(String s : args) {
			if(s != null && ReportSelectionFlagEnum.SUBMITNOW.getType().equalsIgnoreCase(s)) {
				submitNow = true;
			}
		}
		URL url = ClassLoader.getSystemResource(SCHEDULE_LOG_CONFIGURATION_FILE);
		if(submitNow) {
			url = ClassLoader.getSystemResource(SUBMITNOW_LOG_CONFIGURATION_FILE);
		}
		PropertyConfigurator.configure(url);
	}
	
	public static void main(String[] args) throws RuntimeException {
		logger.debug("Start of the report scheduler code main() method new-->>>");
		// Since in the scheduler all time is base on GMT,  set the default time zone as GMT time zone.
		TimeZone.setDefault(TimeZone.getTimeZone("GMT"));
		logger.debug("After setting the timezone---->>>>");
		// set different log file for submitNow and schedule
		//setDifferentLogFile(args); //This is commented out
		logger.debug("The arguments are --->>>"+args[0]);
		SchedulerParameters schedulerParameters = new SchedulerParameters(args);
		Scheduler scheduler = new Scheduler(schedulerParameters);
		// Check if there is an instance running, if so exit application.
		
		scheduler.checkInstance(schedulerParameters.getSelectionFlag());		
		try {
			logger.debug("Instance checked correctly: Starting to Run the job");
			scheduler.run();
			logger.debug("Instance checked correctly:  job run completed");
		}catch(Exception ex) {
			scheduler.getReportStatistics().setReturnCode(DEFAULT_ERROR_CODE);
		}
		scheduler.populateParameterAndDerivedValue();
		
		// email notification
		scheduler.emailSchedulerResults();
		// printout std out
		scheduler.printOutStdOutMessage();
		
		
		//--------------------------- add start for my boxi -------------------------------------
		// call to check reports in shared folder
		new CheckReports().start();
		
	}
	
	private void checkInstance(String selectionFlag){
		String lockFile = selectionFlag + ".running";
		logger.debug("The name of the file is--->>>>"+lockFile);
		if(!lockInstance(lockFile)) {
			String errorInfo = "there is already an instance of " + selectionFlag + " runnning!"; 

			logger.error(errorInfo);
			logger.debug(errorInfo);

			Calendar calendarDateTime =  Calendar.getInstance(TimeZone.getTimeZone("GMT"));
			Date current = calendarDateTime.getTime();
			String content = errorInfo + "\n" + DateUtil.formatDateTime(current);
			sendMail(errorInfo, content);

			System.exit(DEFAULT_ERROR_CODE);
		}
		logger.debug("Exiting from checkInstance method--->>>>");
	}
	
	private static String getRunningTime() {
		Calendar calendarDateTime =  Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		Date current = calendarDateTime.getTime();
		return "Instance is running from:" + DateUtil.formatDateTime(current) + "\n";
		
	}
	private static boolean lockInstance(final String lockFile) {
		try {
			final File file = new File(lockFile);
			final RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
			logger.debug("New randomAccessFile created --->"+randomAccessFile);
			randomAccessFile.writeUTF(getRunningTime());
			final FileLock fileLock = randomAccessFile.getChannel().tryLock();
			logger.debug("Checking the fileLock value -->"+fileLock);
			if (fileLock != null) {
				logger.debug("---fileLock---"+fileLock);
				Runtime.getRuntime().addShutdownHook(new Thread() {
					public void run() {
						try {
							logger.debug("The Lockfile is not null --->");
							fileLock.release();
							randomAccessFile.close();
							file.delete();
							logger.debug("Finally one locak file deleted and next to create --->");
						} catch (Exception e) {
							logger.error("Unable to remove lock file: " + lockFile, e);
						}
					}
				});
				return true;
			}
		} catch (Exception e) {
			logger.error("Unable to create and/or lock file: " + lockFile, e);
		}
		return false;
	}
	
	public void run(){
		// initialize the kick-off and expiration time
		initialGlobalParameters();
		logger.debug("Inside run()");
		List<ReportJob> reportJobList = scheduleService.getQulifiedReportJobs(startDateTime, endDateTime, 
				schedulerParameters.getSelectionFlag(), schedulerParameters.getReportRunKey());
		
		if(reportJobList == null || reportJobList.size() == 0) {
			logger.debug("There is no report job need to run");
			return;
		}

		int threadNumber = Math.min(schedulerParameters.getReportEngineThreads(), reportJobList.size());
		// Prioritize the list and split them into threadNumber
		ReportJobsSplitter reportJobsSplitter = new ReportJobsSplitter(reportJobList, threadNumber);
		List<ReportJob>[] reportJobListArray = reportJobsSplitter.split();

		// set rerun flag
		if(!schedulerParameters.getSelectionFlag().equalsIgnoreCase(ReportSelectionFlagEnum.UNRUN.getType())) {
			scheduleService.updateReRunFlag();
		}
		
		// Get the batch id for current run 
		jobBatchId = scheduleService.getJobBatchId();
		
		for(ReportJob job: reportJobList) {
			logger.debug("Inserting the data---->>>>");
			job.setJobBatchId(jobBatchId);
			//Insert the report log record for every report need to run 
			//including rerun and unrun 
			logger.debug("Insert report run log records for report " + job.getReportScheduleId());
			scheduleService.insertReportLog(job);
		}

		reportEngines = new ReportEngine[threadNumber];
		CountDownLatch threadSignal = new CountDownLatch(threadNumber); 
		for (int i = 0; i<threadNumber; i++) {
			reportEngines[i] = new ReportEngine(threadSignal, jobBatchId, i, expirationDateTime);
			reportEngines[i].start();  
		}  
		// 
		try 
		{
			threadSignal.await();
		}catch (InterruptedException ex) {
			
		}
		
		// GOES here , all the report engine will finish running
		logger.debug("all threads are finished");  
		
		// summarize statistics information 
		for (int i = 0; i<threadNumber; i++) {
			for(ReportJob j: reportEngines[i].getJobList()) {
				schedulerStatics.summarizeOneReportJobMessage(j);
			}
		}  
	}
	
	public void initialGlobalParameters() {
		logger.debug("inside initial global parameters");
		Calendar calendarDateTime = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		calendarDateTime.setTime(schedulerParameters.getIntervalStartDate());

		Calendar calendarTime = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		calendarTime.setTime(schedulerParameters.getIntervalStartTime());
		calendarDateTime.set(Calendar.HOUR_OF_DAY, calendarTime.get(Calendar.HOUR_OF_DAY));
		calendarDateTime.set(Calendar.MINUTE, calendarTime.get(Calendar.MINUTE));
		calendarDateTime.set(Calendar.SECOND, calendarTime.get(Calendar.SECOND));
		
		startDateTime = calendarDateTime.getTime();
		
		calendarDateTime.setTime(schedulerParameters.getIntervalEndDate());
		calendarTime.setTime(schedulerParameters.getIntervalEndTime());

		calendarDateTime.set(Calendar.HOUR_OF_DAY, calendarTime.get(Calendar.HOUR_OF_DAY));
		calendarDateTime.set(Calendar.MINUTE, calendarTime.get(Calendar.MINUTE));
		calendarDateTime.set(Calendar.SECOND, 59);     // END TIME 
		
		endDateTime = calendarDateTime.getTime();
		
		calendarDateTime =  Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		kickoffDateTime = calendarDateTime.getTime();
		calendarDateTime.add(Calendar.MINUTE, schedulerParameters.getRunTimeLimit());
		expirationDateTime = calendarDateTime.getTime();
		jobBatchId = scheduleService.getJobBatchId();
		
		if(ReportSelectionFlagEnum.SUBMITNOW.getType().equalsIgnoreCase(schedulerParameters.getSelectionFlag())) {
			this.reportJobType = JobTypeEnum.SUBMITNOW;
		}else {
			this.reportJobType = JobTypeEnum.SCHEDULED;
		}

		derivedValue.setStartDateTime(startDateTime);
		derivedValue.setEndDateTime(endDateTime);
		derivedValue.setKickoffDateTime(kickoffDateTime);
		derivedValue.setExpirationDateTime(expirationDateTime);
		derivedValue.setJobBatchId(jobBatchId);
		derivedValue.setReportJobType(reportJobType);
		logger.debug("exiting initial global parameters");
	}
	
	public void populateParameterAndDerivedValue() {
		logger.debug("populate the stdout for current batch- " + jobBatchId);
		
		schedulerStatics.populateParameterAndDerivedValue(schedulerParameters, derivedValue);
	}
	
	public void printOutStdOutMessage() {
		stdOutMessage = schedulerStatics.getStdOutMessage();
		logger.debug(stdOutMessage);
	}
	
	/**
	 * Send out the schedule result to administrators
	 * the receivers will be configured in the properties file
	 */
	public void emailSchedulerResults() {
		// only there are some reports selected and some error happends
		if(schedulerStatics.getReportsSelectedCount() > 0 
				&& schedulerStatics.getReportsCompletedCount() < schedulerStatics.getReportsSelectedCount()) {
			String subject = schedulerStatics.getEmailSubject();
			String content = schedulerStatics.getStdOutMessage();
			sendMail(subject, content);
		}
	}
	
	private void sendMail(String subject, String content) {
		try {
			MailUtil.sendMail(PropertiesUtil.SCHEDULER_RESULT_MAIL_LIST,  subject, content);
		} catch(Exception ex) {
			logger.error(ex.getMessage());
		}
	}
	
	public SchedulerParameters getSchedulerParameters() {
		return schedulerParameters;
	}

	public DerivedValue getDerivedValue() {
		return derivedValue;
	}

	
	public static class DerivedValue {
		private Date startDateTime;
		private Date endDateTime;
		private Date kickoffDateTime;
		private Date expirationDateTime;
		private String jobBatchId;
		private JobTypeEnum  reportJobType;
		public Date getStartDateTime() {
			return startDateTime;
		}
		public void setStartDateTime(Date startDateTime) {
			this.startDateTime = startDateTime;
		}
		public Date getEndDateTime() {
			return endDateTime;
		}
		public void setEndDateTime(Date endDateTime) {
			this.endDateTime = endDateTime;
		}
		public Date getKickoffDateTime() {
			return kickoffDateTime;
		}
		public void setKickoffDateTime(Date kickoffDateTime) {
			this.kickoffDateTime = kickoffDateTime;
		}
		public Date getExpirationDateTime() {
			return expirationDateTime;
		}
		public void setExpirationDateTime(Date expirationDateTime) {
			this.expirationDateTime = expirationDateTime;
		}
		public String getJobBatchId() {
			return jobBatchId;
		}
		public void setJobBatchId(String jobBatchId) {
			this.jobBatchId = jobBatchId;
		}
		public JobTypeEnum getReportJobType() {
			return reportJobType;
		}
		public void setReportJobType(JobTypeEnum reportJobType) {
			this.reportJobType = reportJobType;
		}
		
		
	}
}
