package com.lexmark.contract;

import java.io.Serializable;
import java.util.List;

public class SaveServicesUserContract implements Serializable {

	private static final long serialVersionUID = 7795706470855139240L;
	private String mdmId;
	private String mdmLevel;
	private String chlNodeId;
	private List<String> userNumberList;
	
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
	public String getChlNodeId() {
		return chlNodeId;
	}
	public void setChlNodeId(String chlNodeId) {
		this.chlNodeId = chlNodeId;
	}
	public List<String> getUserNumberList() {
		return userNumberList;
	}
	public void setUserNumberList(List<String> userNumberList) {
		this.userNumberList = userNumberList;
	}
}
