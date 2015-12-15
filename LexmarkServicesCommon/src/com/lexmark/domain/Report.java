package com.lexmark.domain;

import java.util.Date;

public class Report extends Document {

	/**
	 * 
	 */
	private static final long serialVersionUID = -858655662800735069L;
	private Date runTime;
	private String statusCode;
	private String errorMessage;
	private int errorCode;
	private String userNumber;
	private String legalName;
	private String jobRunLogId;
	private String tooltipText;
	private String userEmail;
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getTooltipText() {
		return tooltipText;
	}
	public void setTooltipText(String tooltipText) {
		this.tooltipText = tooltipText;
	}
	public String getJobRunLogId() {
		return jobRunLogId;
	}
	public void setJobRunLogId(String jobRunLogId) {
		this.jobRunLogId = jobRunLogId;
	}
	public Date getRunTime() {
		return runTime;
	}
	public void setRunTime(Date runTime) {
		this.runTime = runTime;
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
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public String getUserNumber() {
		return userNumber;
	}
	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}
	public String getLegalName() {
		return legalName;
	}
	public void setLegalName(String legalName) {
		this.legalName = legalName;
	}
}
