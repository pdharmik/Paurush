package com.lexmark.contract;

import java.io.Serializable;

public class ObieeReportParameterListContract implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7099929161947095861L;
	private Integer reportDefinitionId;
	private String contactId;
	public String getContactId() {
		return contactId;
	}
	public void setContactId(String contactId) {
		this.contactId = contactId;
	}
	public Integer getReportDefinitionId() {
		return reportDefinitionId;
	}
	public void setReportDefinitionId(Integer reportDefinitionId) {
		this.reportDefinitionId = reportDefinitionId;
	}
	
	
}
