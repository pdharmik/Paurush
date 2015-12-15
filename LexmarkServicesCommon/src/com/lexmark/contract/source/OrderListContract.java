package com.lexmark.contract.source;


import java.io.Serializable;
import java.util.Map.Entry;

import com.lexmark.contract.api.SearchContractBase;

public class OrderListContract extends SearchContractBase implements Serializable, Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String status;
	private String queryType;
	private String serviceRequestType;
	private boolean employeeFlag;
	private String employeeId;
	private String mdmId;
	private String mdmLevel;
	private boolean pendingDebriefFlag;
	private boolean massUploadRequest;//Added for June Release 14.6 for Defect 12103
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

	public boolean isEmployeeFlag() {
		return employeeFlag;
	}

	public void setEmployeeFlag(boolean employeeFlag) {
		this.employeeFlag = employeeFlag;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getQueryType() {
		return queryType;
	}

	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}

	public String getServiceRequestType() {
		return serviceRequestType;
	}

	public void setServiceRequestType(String serviceRequestType) {
		this.serviceRequestType = serviceRequestType;
	}

	@Override
	public OrderListContract clone() {
		OrderListContract newContract = new OrderListContract();
		// ActivityListContract
		newContract.setStatus(this.getStatus());
		newContract.setQueryType(this.getQueryType());
		newContract.setServiceRequestType(this.getServiceRequestType());
		newContract.setEmployeeFlag(this.isEmployeeFlag());
		newContract.setEmployeeId(this.getEmployeeId());
		newContract.setMdmId(this.getMdmId());
		newContract.setMdmLevel(this.getMdmLevel());
		// SearchContractBase
		newContract.setIncrement(this.getIncrement());
		newContract.setNewQueryIndicator(this.isNewQueryIndicator());
		newContract.setStartRecordNumber(this.getStartRecordNumber());
		newContract.setMassUploadRequest(this.isMassUploadRequest());
		
		for (Entry<String, Object> entry : this.getFilterCriteria().entrySet())
			newContract.getFilterCriteria().put(entry.getKey(), entry.getValue());
		for (Entry<String, Object> entry : this.getSearchCriteria().entrySet())
			newContract.getSearchCriteria().put(entry.getKey(), entry.getValue());
		for (Entry<String, Object> entry : this.getSortCriteria().entrySet())
			newContract.getSortCriteria().put(entry.getKey(), entry.getValue());

		newContract.setSessionHandle(this.getSessionHandle());

		return newContract;
	}

	public boolean isPendingDebriefFlag() {
		return pendingDebriefFlag;
	}

	public void setPendingDebriefFlag(boolean pendingDebriefFlag) {
		this.pendingDebriefFlag = pendingDebriefFlag;
	}

	public void setMassUploadRequest(boolean massUploadRequest) {
		this.massUploadRequest = massUploadRequest;
	}

	public boolean isMassUploadRequest() {
		return massUploadRequest;
	}
}
