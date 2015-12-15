package com.lexmark.form;

import java.util.Map;

public class RequestListForm {
	private boolean createClaimFlag;
	private boolean uploadClaimFlag;
	private boolean uploadRequestFlag;
	private boolean displayUnassignedReqeustsFlag;
	private boolean displayRequestTypeFilterFlag;
	private Map<String, String> requestTypeMap;

	private Map<String, String> requestStatusMap;
	
	/*Added for Partner Debrief Substatus List MPS 2.1*/
	private Map<String, String> debriefRequestStatusMap;
	
	private Map<String, String> claimRequestStatusMap;
	private Map<String, String> serviceRequestStatusMap;

	private Map<String, String> serviceTypeMap;

	private Map<String, String> requestStatusDetailMap;
	private Map<String, String> claimRequestStatusDetailMap;
	private Map<String, String> serviceRequestStatusDetailMap;
	private Map<String, String> allowChildSR;
	
	private boolean hardwareDebriefFlag;

	public Map<String, String> getClaimRequestStatusMap() {
		return claimRequestStatusMap;
	}

	public void setClaimRequestStatusMap(Map<String, String> claimRequestStatusMap) {
		this.claimRequestStatusMap = claimRequestStatusMap;
	}

	public Map<String, String> getServiceRequestStatusMap() {
		return serviceRequestStatusMap;
	}

	public void setServiceRequestStatusMap(Map<String, String> serviceRequestStatusMap) {
		this.serviceRequestStatusMap = serviceRequestStatusMap;
	}


	public boolean isCreateClaimFlag() {
		return createClaimFlag;
	}

	public void setCreateClaimFlag(boolean createClaimFlag) {
		this.createClaimFlag = createClaimFlag;
	}

	public boolean isDisplayUnassignedReqeustsFlag() {
		return displayUnassignedReqeustsFlag;
	}

	public void setDisplayUnassignedReqeustsFlag(boolean displayUnassignedReqeustsFlag) {
		this.displayUnassignedReqeustsFlag = displayUnassignedReqeustsFlag;
	}

	public boolean isDisplayRequestTypeFilterFlag() {
		return displayRequestTypeFilterFlag;
	}

	public void setDisplayRequestTypeFilterFlag(boolean displayRequestTypeFilterFlag) {
		this.displayRequestTypeFilterFlag = displayRequestTypeFilterFlag;
	}

	public Map<String, String> getRequestTypeMap() {
		return requestTypeMap;
	}

	public void setRequestTypeMap(Map<String, String> requestTypeMap) {
		this.requestTypeMap = requestTypeMap;
	}

	public Map<String, String> getRequestStatusMap() {
		return requestStatusMap;
	}

	public void setRequestStatusMap(Map<String, String> requestStatusMap) {
		this.requestStatusMap = requestStatusMap;
	}

	public Map<String, String> getServiceTypeMap() {
		return serviceTypeMap;
	}

	public void setServiceTypeMap(Map<String, String> serviceTypeMap) {
		this.serviceTypeMap = serviceTypeMap;
	}

	public Map<String, String> getRequestStatusDetailMap() {
		return requestStatusDetailMap;
	}

	public void setRequestStatusDetailMap(Map<String, String> requestStatusDetailMap) {
		this.requestStatusDetailMap = requestStatusDetailMap;
	}

	public Map<String, String> getClaimRequestStatusDetailMap() {
		return claimRequestStatusDetailMap;
	}

	public void setClaimRequestStatusDetailMap(Map<String, String> claimRequestStatusDetailMap) {
		this.claimRequestStatusDetailMap = claimRequestStatusDetailMap;
	}

	public Map<String, String> getServiceRequestStatusDetailMap() {
		return serviceRequestStatusDetailMap;
	}

	public void setServiceRequestStatusDetailMap(Map<String, String> serviceRequestStatusDetailMap) {
		this.serviceRequestStatusDetailMap = serviceRequestStatusDetailMap;
	}

	/**
	 * @param uploadClaimFlag the uploadClaimFlag to set
	 */
	public void setUploadClaimFlag(boolean uploadClaimFlag) {
		this.uploadClaimFlag = uploadClaimFlag;
	}

	/**
	 * @return the uploadClaimFlag
	 */
	public boolean isUploadClaimFlag() {
		return uploadClaimFlag;
	}

	/**
	 * @param uploadRequestFlag the uploadRequestFlag to set
	 */
	public void setUploadRequestFlag(boolean uploadRequestFlag) {
		this.uploadRequestFlag = uploadRequestFlag;
	}

	/**
	 * @return the uploadRequestFlag
	 */
	public boolean isUploadRequestFlag() {
		return uploadRequestFlag;
	}
	
	/**
	 * @param hardwareDebriefFlag the hardwareDebriefFlag to set
	 */
	public void setHardwareDebriefFlag(boolean hardwareDebriefFlag) {
		this.hardwareDebriefFlag = hardwareDebriefFlag;
	}

	/**
	 * @return the uploadRequestFlag
	 */
	public boolean isHardwareDebriefFlag() {
		return hardwareDebriefFlag;
	}

	/*Added for Partner Debrief Substatus List MPS 2.1*/
	public Map<String, String> getDebriefRequestStatusMap() {
		return debriefRequestStatusMap;
	}

	public void setDebriefRequestStatusMap(Map<String, String> debriefRequestStatusMap) {
		this.debriefRequestStatusMap = debriefRequestStatusMap;
	}
	/*End Partner Debrief Substatus List MPS 2.1*/
	
	public Map<String, String> getAllowChildSR() {
		return allowChildSR;
	}

	public void setAllowChildSR(Map<String, String> allowChildSR) {
		this.allowChildSR = allowChildSR;
	}
}
