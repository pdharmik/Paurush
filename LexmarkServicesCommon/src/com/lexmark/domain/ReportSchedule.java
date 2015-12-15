package com.lexmark.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReportSchedule implements Serializable {

	private static final long serialVersionUID = 6108014561678689427L;

	private Integer id;
	private String userNumber;
	private String recepientEmail;
	private String runFrequency;
	private Integer runInterval;
	private Date effectiveDate;
	private Date expirationDate;
	private Integer dayOfWeek;
	private Integer dayOfMonth;
	private Integer reportDefinitionId;
	private String preferedTimezone;
	private String country;
	private String emailReminderFlag;
	private Integer customLeadMinutes;
	private String mdmId;					// added by nelson for employee report
	private String mdmLevel;                // added by nelson for employee report
	private String isLBSAccount;              // added for LBS account
	
	
	private List<ReportScheduleParameter> reportScheduleParameters = new ArrayList<ReportScheduleParameter>();
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserNumber() {
		return userNumber;
	}
	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}
	public String getRecepientEmail() {
		return recepientEmail;
	}
	public void setRecepientEmail(String recepientEmail) {
		this.recepientEmail = recepientEmail;
	}
	public String getRunFrequency() {
		return runFrequency;
	}
	public void setRunFrequency(String runFrequency) {
		this.runFrequency = runFrequency;
	}
	public Integer getRunInterval() {
		return runInterval;
	}
	public void setRunInterval(Integer runInterval) {
		this.runInterval = runInterval;
	}
	public Date getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	public Date getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}
	public Integer getDayOfWeek() {
		return dayOfWeek;
	}
	public void setDayOfWeek(Integer dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}
	public Integer getDayOfMonth() {
		return dayOfMonth;
	}
	public void setDayOfMonth(Integer dayOfMonth) {
		this.dayOfMonth = dayOfMonth;
	}
	public Integer getReportDefinitionId() {
		return reportDefinitionId;
	}
	public void setReportDefinitionId(Integer reportDefinitionId) {
		this.reportDefinitionId = reportDefinitionId;
	}
	public List<ReportScheduleParameter> getReportScheduleParameters() {
		return reportScheduleParameters;
	}
	public void setReportScheduleParameters(
			List<ReportScheduleParameter> reportScheduleParameters) {
		this.reportScheduleParameters = reportScheduleParameters;
	}
	public String getPreferedTimezone() {
		return preferedTimezone;
	}
	public void setPreferedTimezone(String preferedTimezone) {
		this.preferedTimezone = preferedTimezone;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getEmailReminderFlag() {
		return emailReminderFlag;
	}
	public void setEmailReminderFlag(String emailReminderFlag) {
		this.emailReminderFlag = emailReminderFlag;
	}
	public Integer getCustomLeadMinutes() {
		return customLeadMinutes;
	}
	public void setCustomLeadMinutes(Integer customLeadMinutes) {
		this.customLeadMinutes = customLeadMinutes;
	}

	//added by nelson for employee report
	public void setMdmId(String mdmId) {
		this.mdmId = mdmId;
	}
	
	public String getMdmId() {
		return mdmId;
	}

	public String getMdmLevel() {
		return mdmLevel;
	}
	public void setMdmLevel(String mdmLevel) {
		this.mdmLevel = mdmLevel;
	}
	//end of addition by nelson for employee report
	public String getIsLBSAccount() {
		return isLBSAccount;
	}
	public void setIsLBSAccount(String isLBSAccount) {
		this.isLBSAccount = isLBSAccount;
	}

}
