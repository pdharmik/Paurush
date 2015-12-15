package com.lexmark.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.lexmark.constants.JobTypeEnum;
import com.lexmark.constants.ReportFileType;
import com.lexmark.enums.ReportRunFrequencyEnum;

public class ReportJob implements Serializable {

	private static final long serialVersionUID = 4483127551577318662L;
	
	public int getOverdueMinutes() {
		return overdueMinutes;
	}
	public void setOverdueMinutes(int overdueMinutes) {
		this.overdueMinutes = overdueMinutes;
	}
	public int getThreadGroup() {
		return threadGroup;
	}
	public void setThreadGroup(int threadGroup) {
		this.threadGroup = threadGroup;
	}
	public String getReportRunKey() {
		return reportRunKey;
	}
	public void setReportRunKey(String reportRunKey) {
		this.reportRunKey = reportRunKey;
	}
	public String getJobBatchId() {
		return jobBatchId;
	}
	public void setJobBatchId(String jobBatchId) {
		this.jobBatchId = jobBatchId;
	}
	public ReportRunFrequencyEnum getRunFrequency() {
		return runFrequency;
	}
	public void setRunFrequency(ReportRunFrequencyEnum runFrequency) {
		this.runFrequency = runFrequency;
	}
	
	private Long jobRunLogId;
	private String mdmId;
	private String mdmLevel;
	private String legalName;
	private int overdueMinutes;
	private int threadGroup;
	private String reportRunKey;
	private String jobBatchId;
	private ReportRunFrequencyEnum runFrequency;
	private String reportDefinitionId;
	private String reportSourceId;
	private Integer reportScheduleId;
	private List<ReportParameters> parameterList = new ArrayList<ReportParameters>();
	private String statusCode;
	private String errorMessage;
	private String fileDataLink;
	private JobTypeEnum jobType = JobTypeEnum.SCHEDULED;
	private ReportFileType fileType;
	
	// ISLBSACCOUNT added
	private String isLBSAccount;
	
	/* Statistics information  */
	private String userNumber;
	private String userEmail;
	private Date runDateTime;
	private Date buildStartOn;
	private Date buildFinishOn;
	private String buildStatusCodeMessage;
	private Date loadStartOn;
	private Date loadFinishOn;
	private String fileObjectId;
	private String loadStatusCodeMessage;
	private boolean isSendMDMParameter = false;
	public boolean isSendMDMParameter() {
		return isSendMDMParameter;
	}
	public void setSendMDMParameter(boolean isSendMDMParameter) {
		this.isSendMDMParameter = isSendMDMParameter;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getReportDefinitionId() {
		return reportDefinitionId;
	}
	public void setReportDefinitionId(String reportDefinitionId) {
		this.reportDefinitionId = reportDefinitionId;
	}
	
	public List<ReportParameters> getParameterList() {
		return parameterList;
	}
	public void setParameterList(List<ReportParameters> parameterList) {
		this.parameterList = parameterList;
	}
	public String getFileDataLink() {
		return fileDataLink;
	}
	public void setFileDataLink(String fileDataLink) {
		this.fileDataLink = fileDataLink;
	}
	public Long getJobRunLogId() {
		return jobRunLogId;
	}
	public void setJobRunLogId(Long jobRunLogId) {
		this.jobRunLogId = jobRunLogId;
	}
	public JobTypeEnum getJobType() {
		return jobType;
	}
	public void setJobType(JobTypeEnum jobType) {
		this.jobType = jobType;
	}
	public String getMdmId() {
		return mdmId;
	}
	public void setMdmId(String mdmId) {
		this.mdmId = mdmId;
	}
	public String getMdmLevel() {
		return mdmLevel;
	}
	public void setMdmLevel(String mdmLevel) {
		this.mdmLevel = mdmLevel;
	}
	public Integer getReportScheduleId() {
		return reportScheduleId;
	}
	public void setReportScheduleId(Integer reportScheduleId) {
		this.reportScheduleId = reportScheduleId;
	}
	public ReportFileType getFileType() {
		return fileType;
	}
	public void setFileType(ReportFileType fileType) {
		this.fileType = fileType;
	}
	public String getReportSourceId() {
		return reportSourceId;
	}
	public void setReportSourceId(String reportSourceId) {
		this.reportSourceId = reportSourceId;
	}
	public String getLegalName() {
		return legalName;
	}
	public void setLegalName(String legalName) {
		this.legalName = legalName;
	}
	public Date getRunDateTime() {
		return runDateTime;
	}
	public void setRunDateTime(Date runDateTime) {
		this.runDateTime = runDateTime;
	}
	public Date getBuildStartOn() {
		return buildStartOn;
	}
	public void setBuildStartOn(Date buildStartOn) {
		this.buildStartOn = buildStartOn;
	}
	public Date getBuildFinishOn() {
		return buildFinishOn;
	}
	public void setBuildFinishOn(Date buildFinishOn) {
		this.buildFinishOn = buildFinishOn;
	}
	public String getBuildStatusCodeMessage() {
		return buildStatusCodeMessage;
	}
	public void setBuildStatusCodeMessage(String buildStatusCodeMessage) {
		this.buildStatusCodeMessage = buildStatusCodeMessage;
	}
	public Date getLoadStartOn() {
		return loadStartOn;
	}
	public void setLoadStartOn(Date loadStartOn) {
		this.loadStartOn = loadStartOn;
	}
	public Date getLoadFinishOn() {
		return loadFinishOn;
	}
	public void setLoadFinishOn(Date loadFinishOn) {
		this.loadFinishOn = loadFinishOn;
	}
	public String getLoadStatusCodeMessage() {
		return loadStatusCodeMessage;
	}
	public void setLoadStatusCodeMessage(String loadStatusCodeMessage) {
		this.loadStatusCodeMessage = loadStatusCodeMessage;
	}
	public String getFileObjectId() {
		return fileObjectId;
	}
	public void setFileObjectId(String fileObjectId) {
		this.fileObjectId = fileObjectId;
	}
	public String getUserNumber() {
		return userNumber;
	}
	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getIsLBSAccount() {
		return isLBSAccount;
	}
	public void setIsLBSAccount(String isLBSAccount) {
		this.isLBSAccount = isLBSAccount;
	}
	
}
