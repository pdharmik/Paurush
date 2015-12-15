/**
 * Copyright@ 2013. This document has been created & written by
 * Wipro technologies for Lexmark and this is copyright to Lexmark
 *
 * File         	: HardwareDebriefForm.java
 * Package     		: com.lexmark.form
 * Creation Date 	: 
 *
 * Modification History:
 *
 * ---------------------------------------------------------------------
 * Author 			Date				Version  		Comments
 * ---------------------------------------------------------------------
 *
 */
package com.lexmark.form;

import java.util.Map;

import com.lexmark.domain.Activity;

/**
 * @author wipro
 * @version 2.1
 *
 */
public class HardwareDebriefForm extends BaseForm {
	
	private Activity activity;
	private Activity userEnteredActivity;
	
	
	private int noOfInitialParts;
	private int noOfAddParts;
	
	private int noOfPageCounts;
	private String addPartsListStr;
	private String technicianFullName;
	private Map<String, String> requestStatusMap;
	private Map<String, String> pageCountsMap;
	private Map<String,String> contactTypeMap;
	private Map<String,String> faxConnectedTypeMap;
	private Map<String,String> specialUsageMap;
	
	private Float timezoneOffset;
	private Map<String,String> recommendedPartlistStatus;
	private Map<String,String> deviceWorkingConditionMap;
	private String srType;
	
	private String backURL;
	private boolean saveCloseoutFlag;
	private boolean offlineDebrief;
	
	/**
	 * @return srType 
	 */
	public String getSrType() {
		return srType;
	}

	/**
	 * @param srType 
	 */ 
	public void setSrType(String srType) {
		this.srType = srType;
	}

	/**
	 * @return activity 
	 */
	public Activity getActivity() {
		return activity;
	}

	/**
	 * @param activity 
	 */
	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	

	/**
	 * @return noOfInitialParts 
	 */
	public int getNoOfInitialParts() {
		return noOfInitialParts;
	}

	/**
	 * @param noOfInitialParts 
	 */
	public void setNoOfInitialParts(int noOfInitialParts) {
		this.noOfInitialParts = noOfInitialParts;
	}

	/**
	 * @return noOfAddParts 
	 */
	public int getNoOfAddParts() {
		return noOfAddParts;
	}

	/**
	 * @param noOfAddParts 
	 */
	public void setNoOfAddParts(int noOfAddParts) {
		this.noOfAddParts = noOfAddParts;
	}

	
	/**
	 * @return noOfPageCounts 
	 */
	public int getNoOfPageCounts() {
		return noOfPageCounts;
	}

	/**
	 * @param noOfPageCounts 
	 */
	public void setNoOfPageCounts(int noOfPageCounts) {
		this.noOfPageCounts = noOfPageCounts;
	}

	/**
	 * @return addPartsListStr 
	 */
	public String getAddPartsListStr() {
		return addPartsListStr;
	}

	/**
	 * @param addPartsListStr 
	 */
	public void setAddPartsListStr(String addPartsListStr) {
		this.addPartsListStr = addPartsListStr;
	}

	/**
	 * @return technicianFullName 
	 */
	public String getTechnicianFullName() {
		return technicianFullName;
	}

	/**
	 * @param technicianFullName 
	 */
	public void setTechnicianFullName(String technicianFullName) {
		this.technicianFullName = technicianFullName;
	}

	/**
	 * @return requestStatusMap 
	 */
	public Map<String, String> getRequestStatusMap() {
		return requestStatusMap;
	}

	/**
	 * @param requestStatusMap 
	 */
	public void setRequestStatusMap(Map<String, String> requestStatusMap) {
		this.requestStatusMap = requestStatusMap;
	}

	/**
	 * @return pageCountsMap 
	 */
	public Map<String, String> getPageCountsMap() {
		return pageCountsMap;
	}

	/**
	 * @param pageCountsMap 
	 */
	public void setPageCountsMap(Map<String, String> pageCountsMap) {
		this.pageCountsMap = pageCountsMap;
	}

	/**
	 * @param contactTypeMap 
	 */
	public void setContactTypeMap(Map<String,String> contactTypeMap) {
		this.contactTypeMap = contactTypeMap;
	}

	/**
	 * @return contactTypeMap 
	 */
	public Map<String,String> getContactTypeMap() {
		return contactTypeMap;
	}

	/**
	 * @param timezoneOffset 
	 */
	public void setTimezoneOffset(Float timezoneOffset) {
		this.timezoneOffset = timezoneOffset;
	}

	/**
	 * @return timezoneOffset 
	 */
	public Float getTimezoneOffset() {
		return timezoneOffset;
	}

	/**
	 * @param userEnteredActivity 
	 */
	public void setUserEnteredActivity(Activity userEnteredActivity) {
		this.userEnteredActivity = userEnteredActivity;
	}

	/**
	 * @return userEnteredActivity 
	 */
	public Activity getUserEnteredActivity() {
		return userEnteredActivity;
	}

	/**
	 * @param recommendedPartlistStatus 
	 */
	public void setRecommendedPartlistStatus(
			Map<String,String> recommendedPartlistStatus) {
		this.recommendedPartlistStatus = recommendedPartlistStatus;
	}

	/**
	 * @return recommendedPartlistStatus 
	 */
	public Map<String,String> getRecommendedPartlistStatus() {
		return recommendedPartlistStatus;
	}

	/**
	 * @param deviceWorkingConditionMap 
	 */
	public void setDeviceWorkingConditionMap(
			Map<String,String> deviceWorkingConditionMap) {
		this.deviceWorkingConditionMap = deviceWorkingConditionMap;
	}

	/**
	 * @return deviceWorkingConditionMap 
	 */
	public Map<String,String> getDeviceWorkingConditionMap() {
		return deviceWorkingConditionMap;
	}

	
	/**
	 * @param backURL 
	 */
	public void setBackURL(String backURL) {
		this.backURL = backURL;
	}

	/**
	 * @return backURL 
	 */
	public String getBackURL() {
		return backURL;
	}

	/**
	 * @param saveCloseoutFlag 
	 */
	public void setSaveCloseoutFlag(boolean saveCloseoutFlag) {
		this.saveCloseoutFlag = saveCloseoutFlag;
	}

	/**
	 * @return saveCloseoutFlag 
	 */
	public boolean isSaveCloseoutFlag() {
		return saveCloseoutFlag;
	}

	/**
	 * @param offlineDebrief 
	 */
	public void setOfflineDebrief(boolean offlineDebrief) {
		this.offlineDebrief = offlineDebrief;
	}

	/**
	 * @return offlineDebrief 
	 */
	public boolean isOfflineDebrief() {
		return offlineDebrief;
	}

	/**
	 * @param faxConnectedTypeMap 
	 */
	public void setFaxConnectedTypeMap(Map<String,String> faxConnectedTypeMap) {
		this.faxConnectedTypeMap = faxConnectedTypeMap;
	}

	/**
	 * @return faxConnectedTypeMap 
	 */
	public Map<String,String> getFaxConnectedTypeMap() {
		return faxConnectedTypeMap;
	}
	/**
	 * @param specialUsageMap 
	 */
	public Map<String, String> getSpecialUsageMap() {
		return specialUsageMap;
	}
	/**
	 * @return setSpecialUsageMap 
	 */
	public void setSpecialUsageMap(Map<String, String> specialUsageMap) {
		this.specialUsageMap = specialUsageMap;
	}

	

	
	
}
