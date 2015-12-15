package com.lexmark.services.reports.bean;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ScheduleReportInfoBean {

	private String boUser;
	private String boPassword;
	private String boCmsName;
	private String boAuthType;

	private String scheduleId;
	private String reportSourceId;
	private String formatType;
	private String destination;
	private String title;
	private Map<String, String> parameterMap = new HashMap<String, String>();

	private String scheduleType;
	private Date startDate;
	private Date endDate;
	private String recurrenceSiId;
	private String recurrenceInstanceSiId;
	private String nfsfileLocation;

	private int wsStatusCode;
	private String wsStatusMessage;
	private String repDefName;
	
    private String isMDM;
    
    private String intervalInDays;
    private String dayOfWeek;
    private String dayOfMonth;
    private String intervalType; //DAILY,MONTHLY,WEEKLY

	public String getBoUser() {
		return boUser;
	}

	public void setBoUser(String boUser) {
		this.boUser = boUser;
	}

	public String getBoPassword() {
		return boPassword;
	}

	public void setBoPassword(String boPassword) {
		this.boPassword = boPassword;
	}

	public String getBoCmsName() {
		return boCmsName;
	}

	public void setBoCmsName(String boCmsName) {
		this.boCmsName = boCmsName;
	}

	public String getBoAuthType() {
		return boAuthType;
	}

	public void setBoAuthType(String boAuthType) {
		this.boAuthType = boAuthType;
	}

	public String getReportSourceId() {
		return reportSourceId;
	}

	public void setReportSourceId(String reportSourceId) {
		this.reportSourceId = reportSourceId;
	}

	public String getFormatType() {
		return formatType;
	}

	public void setFormatType(String formatType) {
		this.formatType = formatType;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getScheduleType() {
		return scheduleType;
	}

	public void setScheduleType(String scheduleType) {
		this.scheduleType = scheduleType;
	}

	public String getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(String scheduleId) {
		this.scheduleId = scheduleId;
	}

	public Map<String, String> getParameterMap() {
		return parameterMap;
	}

	public void setParameterMap(Map<String, String> parameterMap) {
		this.parameterMap = parameterMap;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getRecurrenceSiId() {
		return recurrenceSiId;
	}

	public void setRecurrenceSiId(String recurrenceSiId) {
		this.recurrenceSiId = recurrenceSiId;
	}

	public String getRecurrenceInstanceSiId() {
		return recurrenceInstanceSiId;
	}

	public void setRecurrenceInstanceSiId(String recurrenceInstanceSiId) {
		this.recurrenceInstanceSiId = recurrenceInstanceSiId;
	}

	public String getWsStatusMessage() {
		return wsStatusMessage;
	}

	public void setWsStatusMessage(String wsStatusMessage) {
		this.wsStatusMessage = wsStatusMessage;
	}

	public int getWsStatusCode() {
		return wsStatusCode;
	}

	public void setWsStatusCode(int wsStatusCode) {
		this.wsStatusCode = wsStatusCode;
	}

	public String getNfsfileLocation() {
		return nfsfileLocation;
	}

	public void setNfsfileLocation(String nfsfileLocation) {
		this.nfsfileLocation = nfsfileLocation;
	}
	
	public String getRepDefName() {
		return repDefName;
	}

	public void setRepDefName(String repDefName) {
		this.repDefName = repDefName;
	}

	public String getIsMDM() {
		return isMDM;
	}

	public void setIsMDM(String isMDM) {
		this.isMDM = isMDM;
	}

	public String getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public String getIntervalType() {
		return intervalType;
	}

	public void setIntervalType(String intervalType) {
		this.intervalType = intervalType;
	}

	public String getIntervalInDays() {
		return intervalInDays;
	}

	public void setIntervalInDays(String intervalInDays) {
		this.intervalInDays = intervalInDays;
	}

	public String getDayOfMonth() {
		return dayOfMonth;
	}

	public void setDayOfMonth(String dayOfMonth) {
		this.dayOfMonth = dayOfMonth;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
}
