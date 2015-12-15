package com.lexmark.services.form;

import java.util.List;
import java.util.Map;

import com.lexmark.domain.Account;
import com.lexmark.domain.Asset;
import com.lexmark.domain.CannedQuery;
import com.lexmark.domain.GenericAddress;
import com.lexmark.service.impl.real.domain.LBSCHLMapping;

/**
 * @author wipro 
 *
 */
public class FleetManagementForm {
	
	//private String addressJson;
	private String assetId;
	private String mdmId;
	private String mdmLevel;
	private String contactId;
	private String defaultFieldsView;
	private boolean employee;
	//private GenericAddress addressFromMap;
	private String countryString;
	private String buildingTypes;
	private boolean showChangeMgmt;
	private boolean showService;
	private boolean showSupplies;
	private GenericAddress moveToAddress;
	private GenericAddress installAddress;
	private String supportDwnldURL;
	private String controlPanelURL;
	private List<CannedQuery> cannedQueries;
	private String filterPreferences;//This will contain the first prefences available in the DB for the particular USER
	private String l5HeirarchyParentChain; 
	private String backInfo;
	private boolean opsUser;
	
	private String emailAddress;
	private String endPointURL;//LBS endpoint url.
	private String multiAssetRequestType;
	private String multiAssetList;
	
	private Account account; // added to get account info in multi select flow
	private List<Asset> assetDetailsList; //added for multi select
	
	private boolean showDeviceStatus;
	private boolean showDeviceUtilization;
	private List<String> roleList;
	private boolean showLod;//Flag used for showing address & floor lod
	private	String companyName;
	/**
	 * @return the showDeviceStatus
	 */
	public boolean isShowDeviceStatus() {
		return showDeviceStatus;
	}
	/**
	 * @param showDeviceStatus the showDeviceStatus to set
	 */
	public void setShowDeviceStatus(boolean showDeviceStatus) {
		this.showDeviceStatus = showDeviceStatus;
	}
	/**
	 * @return the showDeviceUtilization
	 */
	public boolean isShowDeviceUtilization() {
		return showDeviceUtilization;
	}
	/**
	 * @param showDeviceUtilization the showDeviceUtilization to set
	 */
	public void setShowDeviceUtilization(boolean showDeviceUtilization) {
		this.showDeviceUtilization = showDeviceUtilization;
	}
	/**
	 * @return the roleList
	 */
	public List<String> getRoleList() {
		return roleList;
	}
	/**
	 * @param roleList the roleList to set
	 */
	public void setRoleList(List<String> roleList) {
		this.roleList = roleList;
	}
	/**
	 * 
	 * @param mdmLevel 
	 */
	public void setMdmLevel(String mdmLevel) {
		this.mdmLevel = mdmLevel;
	}
	/**
	 * 
	 * @return String 
	 */
	public String getMdmLevel() {
		return mdmLevel;
	}
	/**
	 * 
	 * @param mdmId 
	 */
	public void setMdmId(String mdmId) {
		this.mdmId = mdmId;
	}
	/**
	 * 
	 * @return String 
	 */
	public String getMdmId() {
		return mdmId;
	}
/**
 * 
 * @return String 
 */
	public String getAssetId() {
		return assetId;
	}
/**
 * 
 * @param assetId 
 */
	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}
	/**
	 * 
	 * @param countryString 
	 */
	public void setCountryString(String countryString) {
		this.countryString = countryString;
	}
	/**
	 *  
	 * @return String 
	 */
	public String getCountryString() {
		return countryString;
	}
	/**
	 * @param defaultFieldsView the defaultFieldsView to set
	 */
	public void setDefaultFieldsView(String defaultFieldsView) {
		this.defaultFieldsView = defaultFieldsView;
	}
	/**
	 * @return the defaultFieldsView
	 */
	public String getDefaultFieldsView() {
		return defaultFieldsView;
	}
	/**
	 * @param employee the employee to set
	 */
	public void setEmployee(boolean employee) {
		this.employee = employee;
	}
	/**
	 * @return the employee
	 */
	public boolean isEmployee() {
		return employee;
	}
	/**
	 * @param showChangeMgmt the showChangeMgmt to set
	 */
	public void setShowChangeMgmt(boolean showChangeMgmt) {
		this.showChangeMgmt = showChangeMgmt;
	}
	/**
	 * @return the showChangeMgmt
	 */
	public boolean isShowChangeMgmt() {
		return showChangeMgmt;
	}
	/**
	 * @param showService the showService to set
	 */
	public void setShowService(boolean showService) {
		this.showService = showService;
	}
	/**
	 * @return the showService
	 */
	public boolean isShowService() {
		return showService;
	}
	/**
	 * @param showSupplies the showSupplies to set
	 */
	public void setShowSupplies(boolean showSupplies) {
		this.showSupplies = showSupplies;
	}
	/**
	 * @return the showSupplies
	 */
	public boolean isShowSupplies() {
		return showSupplies;
	}
	
	/**
	 * @param moveToAddress the moveToAddress to set
	 */
	public void setMoveToAddress(GenericAddress moveToAddress) {
		this.moveToAddress = moveToAddress;
	}
	/**
	 * @return the moveToAddress
	 */
	public GenericAddress getMoveToAddress() {
		return moveToAddress;
	}
	/**
	 * @param filterPreferences the filterPreferences to set
	 */
	public void setFilterPreferences(String filterPreferences) {
		this.filterPreferences = filterPreferences;
	}
	/**
	 * @return the filterPreferences
	 */
	public String getFilterPreferences() {
		return filterPreferences;
	}
	/**
	 * @param contactId the contactId to set
	 */
	public void setContactId(String contactId) {
		this.contactId = contactId;
	}
	/**
	 * @return the contactId
	 */
	public String getContactId() {
		return contactId;
	}
	/**
	 * @param supportDwnldURL the supportDwnldURL to set
	 */
	public void setSupportDwnldURL(String supportDwnldURL) {
		this.supportDwnldURL = supportDwnldURL;
	}
	/**
	 * @return the supportDwnldURL
	 */
	public String getSupportDwnldURL() {
		return supportDwnldURL;
	}
	/**
	 * @param controlPanelURL the controlPanelURL to set
	 */
	public void setControlPanelURL(String controlPanelURL) {
		this.controlPanelURL = controlPanelURL;
	}
	/**
	 * @return the controlPanelURL
	 */
	public String getControlPanelURL() {
		return controlPanelURL;
	}
	/**
	 * @param cannedQueries the cannedQueries to set
	 */
	public void setCannedQueries(List<CannedQuery> cannedQueries) {
		this.cannedQueries = cannedQueries;
	}
	/**
	 * @return the cannedQueries
	 */
	public List<CannedQuery> getCannedQueries() {
		return cannedQueries;
	}
	/**
	 * @param l5HeirarchyParentChain the l5HeirarchyParentChain to set
	 */
	public void setL5HeirarchyParentChain(String l5HeirarchyParentChain) {
		this.l5HeirarchyParentChain = l5HeirarchyParentChain;
	}
	/**
	 * @return the l5HeirarchyParentChain
	 */
	public String getL5HeirarchyParentChain() {
		return l5HeirarchyParentChain;
	}
	/**
	 * @param emailAddress the emailAddress to set
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	/**
	 * @return the emailAddress
	 */
	public String getEmailAddress() {
		return emailAddress;
	}
	/**
	 * @param opsUser the opsUser to set
	 */
	public void setOpsUser(boolean opsUser) {
		this.opsUser = opsUser;
	}
	/**
	 * @return the opsUser
	 */
	public boolean isOpsUser() {
		return opsUser;
	}
	/**
	 * @param endPointURL the endPointURL to set
	 */
	public void setEndPointURL(String endPointURL) {
		this.endPointURL = endPointURL;
	}
	/**
	 * @return the endPointURL
	 */
	public String getEndPointURL() {
		return endPointURL;
	}
	/**
	 * @param backInfo the backInfo to set
	 */
	public void setBackInfo(String backInfo) {
		this.backInfo = backInfo;
	}
	/**
	 * @return the backInfo
	 */
	public String getBackInfo() {
		return backInfo;
	}
	/**
	 * @param buildingTypes the buildingTypes to set
	 */
	public void setBuildingTypes(String buildingTypes) {
		this.buildingTypes = buildingTypes;
	}
	/**
	 * @return the buildingTypes
	 */
	public String getBuildingTypes() {
		return buildingTypes;
	}
	/**
	 * @return the multiAssetList
	 */
	public String getMultiAssetList() {
		return multiAssetList;
	}	
	/**
	 * @param multiAssetList the multiAssetList to set
	 */
	public void setMultiAssetList(String multiAssetList) {
		this.multiAssetList = multiAssetList;
	}
	/**
	 * @param multiAssetRequestType the multiAssetRequestType to set
	 */
	public void setMultiAssetRequestType(String multiAssetRequestType) {
		this.multiAssetRequestType = multiAssetRequestType;
	}
	/**
	 * @return the multiAssetRequestType
	 */
	public String getMultiAssetRequestType() {
		return multiAssetRequestType;
	}
	public List<Asset> getAssetDetailsList() {
		return assetDetailsList;
	}
	public void setAssetDetailsList(List<Asset> assetDetailsList) {
		this.assetDetailsList = assetDetailsList;
	}
	public void setInstallAddress(GenericAddress installAddress) {
		this.installAddress = installAddress;
	}
	public GenericAddress getInstallAddress() {
		return installAddress;
	}
	/**
	 * @param showLod the showLod to set
	 */
	public void setShowLod(boolean showLod) {
		this.showLod = showLod;
	}
	/**
	 * @return the showLod
	 */
	public boolean isShowLod() {
		return showLod;
	}
	/**
	 * @param account the account to set
	 */
	public void setAccount(Account account) {
		this.account = account;
	}
	/**
	 * @return the account
	 */
	public Account getAccount() {
		return account;
	}
	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}
	/**
	 * @param companyName the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
}
