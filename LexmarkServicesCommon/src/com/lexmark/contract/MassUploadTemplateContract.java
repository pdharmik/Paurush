package com.lexmark.contract;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.lexmark.contract.api.SearchContractBase;

public class MassUploadTemplateContract implements Serializable {

	private static final long serialVersionUID = 1L;

	private String status;
	private String mdmId;
	private String mdmLevel;
	private Map<String, Object> filterCriteria = new HashMap<String, Object>();
	private Map<String, Object> sortCriteria = new HashMap<String, Object>();
	private boolean massUploadRequest;//Added for June Release 14.6 for Defect 12103
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public Map<String, Object> getFilterCriteria() {
		return filterCriteria;
	}
	public void setFilterCriteria(Map<String, Object> filterCriteria) {
		this.filterCriteria = filterCriteria;
	}
	public Map<String, Object> getSortCriteria() {
		return sortCriteria;
	}
	public void setSortCriteria(Map<String, Object> sortCriteria) {
		this.sortCriteria = sortCriteria;
	}
	public void setMassUploadRequest(boolean massUploadRequest) {
		this.massUploadRequest = massUploadRequest;
	}
	public boolean isMassUploadRequest() {
		return massUploadRequest;
	}
}
