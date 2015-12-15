package com.lexmark.contract;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.lexmark.contract.api.ContractBase;

public class ServiceRequestCategoryCountsContract extends ContractBase implements Serializable {
	private static final long serialVersionUID = -808825248695123594L;
	private String mdmId;
	private String mdmLevel;
	private List<String> status = new ArrayList<String>(0);
	private String contactId;
	private String chlNodeId; 
	private Locale locale;
	
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
	public String getContactId() {
		return contactId;
	}
	public void setContactId(String contactId) {
		this.contactId = contactId;
	}
	public Locale getLocale() {
		return locale;
	}
	public void setLocale(Locale locale) {
		this.locale = locale;
	}
	public List<String> getStatus() {
		return status;
	}
	public void setStatus(List<String> status) {
		this.status = status;
	}
	public String getChlNodeId() {
		return chlNodeId;
	}
	public void setChlNodeId(String chlNodeId) {
		this.chlNodeId = chlNodeId;
	}
}
