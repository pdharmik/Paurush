package com.lexmark.domain;

import java.io.Serializable;

public class ReportNotifierTO implements Serializable{
	private static final long serialVersionUID = -3262041428610416978L;
	
	private String runlogId;
	private Integer definitionId;
	private Integer scheduleId;
	private String aWebUrl;
	private String localeCode;
	private String userNumber;
	
	private String fileType;
	private String fileDataLink;
	
	private String userEmail;
	
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getFileDataLink() {
		return fileDataLink;
	}
	public void setFileDataLink(String fileDataLink) {
		this.fileDataLink = fileDataLink;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public String getRunlogId() {
		return runlogId;
	}
	public void setRunlogId(String runlogId) {
		this.runlogId = runlogId;
	}
	public Integer getDefinitionId() {
		return definitionId;
	}
	public void setDefinitionId(Integer definitionId) {
		this.definitionId = definitionId;
	}
	public Integer getScheduleId() {
		return scheduleId;
	}
	public void setScheduleId(Integer scheduleId) {
		this.scheduleId = scheduleId;
	}
	public String getAWebUrl() {
		return aWebUrl;
	}
	public void setAWebUrl(String webUrl) {
		aWebUrl = webUrl;
	}
	public String getLocaleCode() {
		return localeCode;
	}
	public void setLocaleCode(String localeCode) {
		this.localeCode = localeCode;
	}
	public String getUserNumber() {
		return userNumber;
	}
	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}
	
	

}
