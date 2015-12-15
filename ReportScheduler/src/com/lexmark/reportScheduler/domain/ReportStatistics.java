package com.lexmark.reportScheduler.domain;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Date;

import com.lexmark.constants.LexmarkConstants;
import com.lexmark.domain.ReportJob;
import com.lexmark.enums.RunLogStatusEnum;
import com.lexmark.reportScheduler.server.SchedulerParameters;
import com.lexmark.reportScheduler.server.Scheduler.DerivedValue;
import com.lexmark.reportScheduler.util.DateUtil;
import com.lexmark.util.report.PropertiesUtil;

public class ReportStatistics implements Serializable{
	private static final long serialVersionUID = 253185712569429505L;
	private int reportsSelectedCount;
	private int reportsCompletedCount ;
	private int reportsFailedBuildCount;
	private int reportsFailedLoadCount;
	private int reportsDeferredCount;
	private int returnCode;
	private String returnMessage;

	private StringBuilder  parameterAndDerivedList = new StringBuilder();
	private StringBuilder  reportJobLogList = new StringBuilder();

	public int getReportsSelectedCount() {
		return reportsSelectedCount;
	}
	public void setReportsSelectedCount(int reportsSelectedCount) {
		this.reportsSelectedCount = reportsSelectedCount;
	}
	public int getReportsCompletedCount() {
		return reportsCompletedCount;
	}
	public void setReportsCompletedCount(int reportsCompletedCount) {
		this.reportsCompletedCount = reportsCompletedCount;
	}
	public int getReportsFailedBuildCount() {
		return reportsFailedBuildCount;
	}
	public void setReportsFailedBuildCount(int reportsFailedBuildCount) {
		this.reportsFailedBuildCount = reportsFailedBuildCount;
	}
	public int getReportsFailedLoadCount() {
		return reportsFailedLoadCount;
	}
	public void setReportsFailedLoadCount(int reportsFailedLoadCount) {
		this.reportsFailedLoadCount = reportsFailedLoadCount;
	}
	public int getReportsDeferredCount() {
		return reportsDeferredCount;
	}
	public void setReportsDeferredCount(int reportsDeferredCount) {
		this.reportsDeferredCount = reportsDeferredCount;
	}
	public void summarizeOneReportJobMessage(ReportJob job) {
		reportsSelectedCount ++;
		if(job.getStatusCode().equalsIgnoreCase(RunLogStatusEnum.WAITFORPUB.getStatusCode())) {
			reportsCompletedCount++;
		} else if(job.getStatusCode().equalsIgnoreCase(RunLogStatusEnum.BUILDERROR.getStatusCode())) {
			reportsFailedBuildCount++;
		} else if(job.getStatusCode().equalsIgnoreCase(RunLogStatusEnum.LOADERROR.getStatusCode())) {
			reportsFailedLoadCount++;
		} else if(job.getStatusCode().equalsIgnoreCase(RunLogStatusEnum.DEFERRED.getStatusCode())) {
			reportsDeferredCount++;
		}
		// 1,{JOB_RUNLOG_ID}, {USERNUMBER}, {REPORT_SCHEDULE_ID}, {BATCH_ID}, {THREAD_GROUP}, 
		//{STATUS_CODE_CODE}, "{ERROR_LOG}",{RUN_DATE_TIME}, {BUILD_STARTED_ON}, {BUILD_FINISHED_ON}, "{BUILD_STATUS_CODE_MSG}", 
		//{LOAD_STARTED_ON}, {LOAD_FINISHED_ON}, "{LOAD_STATUS_CODE_MSG}", {XLS_FILE_OBJECT_ID}, {PDF_FILE_OBJECT_ID}
		reportJobLogList.append(reportsSelectedCount).append(",")
			  .append(job.getJobRunLogId()).append(",")
			  .append(job.getUserNumber()).append(",")
			  .append(job.getReportScheduleId()).append(",")
			  .append(job.getJobBatchId()).append(",")
			  .append(job.getThreadGroup()).append(",")
			  .append(job.getStatusCode()).append(",")
			  .append(DateUtil.formatDateTime(job.getRunDateTime())).append(",")
			  .append(DateUtil.formatDateTime(job.getBuildStartOn())).append(",")
			  .append(DateUtil.formatDateTime(job.getBuildFinishOn())).append(",")
			  .append(job.getBuildStatusCodeMessage()).append(",")
			  .append(DateUtil.formatDateTime(job.getLoadStartOn())).append(",")
			  .append(DateUtil.formatDateTime(job.getLoadFinishOn())).append(",")
			  .append(job.getLoadStatusCodeMessage()).append(",")
			  .append(job.getFileObjectId()).append(";\n");
	}
	
	public void setReturnCode(int code) {
		returnCode = code;
	}
	
	public int getReturnCode() {
		Integer threshhold = 5;
		try
		{
			threshhold = Integer.valueOf(PropertiesUtil.SCHEDULER_ERROR_THREASHHOLD);
		}
		catch(Exception ex) {}
		if(reportsSelectedCount >= threshhold && reportsCompletedCount == 0) {
			returnCode = 1;
		}
		return returnCode;
	}
	
	public String getReportJobList() {
		return reportJobLogList.toString();
	}
	
	private static final String ERROR_MESSAGE_TEMPLATE = "{0} GMT, ReturnCode=({1}), {2}  Reports Selected,  {3}  completed OK, " + 
		"{4}  Failed to Build, {5}  Failed Documentnum Load, {6}  Deferred for RunTime Exceeded";
	public String getReturnMessage() {
		if(returnMessage == null) {
			returnMessage = MessageFormat.format(ERROR_MESSAGE_TEMPLATE, new Object[]{
					DateUtil.formatDateTime(new Date()), returnCode, reportsSelectedCount, reportsCompletedCount,
					reportsFailedBuildCount, reportsFailedLoadCount, reportsDeferredCount
			});	
		}
		return returnMessage;
	}
	
	public String getFlagCode() {
//		If the ReturnCode = 1, then flag as ERROR
//		If the number of Reports Select =  Reports Completed OK 
//		If any report deferred or failed to build or load, then flag as WARNING
		if(returnCode == 1) {
			return "ERROR";
		}
		if(reportsSelectedCount == reportsCompletedCount) {
			return "OK";
		}
		return "WARNING";
	}
	
	private static final String SUBJECT_TEMPLATE = "{0} - Portal ReportEngine - {1}";
	
	public String getEmailSubject() {
		return MessageFormat.format(ERROR_MESSAGE_TEMPLATE, new Object[]{ getFlagCode(), getReturnMessage()});
	}
	
	public void populateParameterAndDerivedValue(SchedulerParameters schedulerParameters, DerivedValue derivedValue) {
	
		parameterAndDerivedList.append("\nJob Invocation Parameters - " + LexmarkConstants.PORTLET_BUILD_VERSION + "\n")
		.append("IntervalStartDate =").append(DateUtil.formatGMTDate(schedulerParameters.getIntervalStartDate())).append("\n")
		.append("IntervalStartTime =").append(DateUtil.formatGMTTime(schedulerParameters.getIntervalStartTime())).append("\n")
		.append("IntervalEndDate =").append(DateUtil.formatGMTDate(schedulerParameters.getIntervalEndDate())).append("\n")
		.append("IntervalEndTime =").append(DateUtil.formatGMTTime(schedulerParameters.getIntervalEndTime())).append("\n")
		.append("ReportEngineThreads =").append(schedulerParameters.getReportEngineThreads()).append("\n")
		.append("RunTimeLimit =").append(schedulerParameters.getRunTimeLimit()).append(" minutes\n")
		.append("SelectionFlag =").append(schedulerParameters.getSelectionFlag()).append("\n")
		.append("JobRunLogId=").append(schedulerParameters.getReportRunKey()).append("\n\n")

		.append("Derived Values\n")
		.append("P_StartDateTime =").append(DateUtil.formatDateTime(derivedValue.getStartDateTime())).append("\n")
		.append("P_EndDateTime =").append(DateUtil.formatDateTime(derivedValue.getEndDateTime())).append("\n")
		.append("P_KickOffDateTime =").append(DateUtil.formatDateTime(derivedValue.getKickoffDateTime())).append("\n")
		.append("P_ExpirationDateTime =").append(DateUtil.formatDateTime(derivedValue.getExpirationDateTime())).append("\n\n");
	}
	
	public String getStdOutMessage() {
		StringBuilder sb = new StringBuilder();
		sb.append("Return Message\n");
		sb.append(getReturnMessage());
		
		sb.append(parameterAndDerivedList.toString());
		
		sb.append("Report Job Log List\n");
		sb.append(getReportJobList());
		return sb.toString();
	}
	
	
}
