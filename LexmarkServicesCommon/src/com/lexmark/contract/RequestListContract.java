package com.lexmark.contract;

import java.util.Map.Entry;

import com.lexmark.contract.api.MdmSearchContractBase;

public class RequestListContract extends MdmSearchContractBase implements Cloneable{

	private static final long serialVersionUID = 2646484696553755543L;

	private String chlNodeId;
	private String status;
	private String contactId;
	private boolean showAllFlag;
	private boolean assetFavoriteFlag;
	private String assetType;
	private String serviceRequestNumber;
	private boolean vendorFlag;
	private String vendorAccountId;
	private boolean changeRequestFlag;
	private boolean hardwareRequestFlag;
	//Added for CI BRD 14-02-01
	private String entitlementEndDate;
	private String pageName;
	private boolean hardwareRequestsPermission;
	private boolean changeRequestsPermission;
	private boolean viewAllCustomerOrder;
	private String accountId;
	private String soldTo;//Added for B2B Punchout
	private boolean alliancePartner; //flag created for Dell/Boeing split calls
	private String assetId;
	private boolean opsPage;
	private String serviceType;
	
	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public boolean isOpsPage() {
		return opsPage;
	}

	public void setOpsPage(boolean opsPage) {
		this.opsPage = opsPage;
	}
	
	public boolean isAlliancePartner() {
		return alliancePartner;
	}

	public void setAlliancePartner(boolean alliancePartner) {
		this.alliancePartner = alliancePartner;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public boolean isViewAllCustomerOrder() {
		return viewAllCustomerOrder;
	}

	public void setViewAllCustomerOrder(boolean viewAllCustomerOrder) {
		this.viewAllCustomerOrder = viewAllCustomerOrder;
	}

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public String getChlNodeId() {
		return chlNodeId;
	}

	public void setChlNodeId(String chlNodeId) {
		this.chlNodeId = chlNodeId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getContactId() {
		return contactId;
	}

	public void setContactId(String contactId) {
		this.contactId = contactId;
	}

	public String getAssetType() {
		return assetType;
	}

	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}

	public String getServiceRequestNumber() {
		return serviceRequestNumber;
	}

	public void setServiceRequestNumber(String serviceRequestNumber) {
		this.serviceRequestNumber = serviceRequestNumber;
	}

	public boolean isShowAllFlag() {
		return showAllFlag;
	}

	public void setShowAllFlag(boolean showAllFlag) {
		this.showAllFlag = showAllFlag;
	}

	public boolean isAssetFavoriteFlag() {
		return assetFavoriteFlag;
	}

	public void setAssetFavoriteFlag(boolean assetFavoriteFlag) {
		this.assetFavoriteFlag = assetFavoriteFlag;
	}

	public void setVendorFlag(boolean vendorFlag) {
		this.vendorFlag = vendorFlag;
	}

	public boolean isVendorFlag() {
		return vendorFlag;
	}

	public String getVendorAccountId() {
		return vendorAccountId;
	}

	public void setVendorAccountId(String vendorAccountId) {
		this.vendorAccountId = vendorAccountId;
	}

	public boolean isChangeRequestFlag() {
		return changeRequestFlag;
	}

	public void setChangeRequestFlag(boolean changeRequestFlag) {
		this.changeRequestFlag = changeRequestFlag;
	}

	public boolean isHardwareRequestFlag() {
		return hardwareRequestFlag;
	}

	public void setHardwareRequestFlag(boolean hardwareRequestFlag) {
		this.hardwareRequestFlag = hardwareRequestFlag;
	}
	//Added for CI BRD 14-02-01-STARTS
	public void setEntitlementEndDate(String entitlementEndDate) {
		this.entitlementEndDate = entitlementEndDate;
	}

	public String getEntitlementEndDate() {
		return entitlementEndDate;
	}
	//Added for CI BRD 14-02-01-ENDS
	
	@Override
	public RequestListContract clone() {
		RequestListContract newContract = new RequestListContract();
		// ActivityListContract
		newContract.setStatus(this.getStatus());
		newContract.setAssetFavoriteFlag(this.isAssetFavoriteFlag());
		newContract.setAssetType(this.getAssetType());
		newContract.setChangeRequestFlag(this.isChangeRequestFlag());
		newContract.setChlNodeId(this.getChlNodeId());
		newContract.setContactId(this.getContactId());
		newContract.setEntitlementEndDate(this.getEntitlementEndDate());
		newContract.setHardwareRequestFlag(this.isHardwareRequestFlag());
		newContract.setIncrement(this.getIncrement());
		newContract.setMdmId(this.getMdmId());
		newContract.setMdmLevel(this.getMdmLevel());
		newContract.setNewQueryIndicator(this.isNewQueryIndicator());
		newContract.setPageName(this.getPageName());
		newContract.setServiceRequestNumber(this.getServiceRequestNumber());
		newContract.setShowAllFlag(this.isShowAllFlag());
		newContract.setStartRecordNumber(this.getStartRecordNumber());
		newContract.setStatus(this.getStatus());
		newContract.setVendorAccountId(this.getVendorAccountId());
		newContract.setVendorFlag(this.isVendorFlag());
		newContract.setHardwareRequestsPermission(this.isHardwareRequestsPermission());
		newContract.setChangeRequestsPermission(this.isChangeRequestsPermission());
		

		for (Entry<String, Object> entry : this.getFilterCriteria().entrySet())
			newContract.getFilterCriteria().put(entry.getKey(), entry.getValue());
		for (Entry<String, Object> entry : this.getSearchCriteria().entrySet())
			newContract.getSearchCriteria().put(entry.getKey(), entry.getValue());
		for (Entry<String, Object> entry : this.getSortCriteria().entrySet())
			newContract.getSortCriteria().put(entry.getKey(), entry.getValue());

		newContract.setSessionHandle(this.getSessionHandle());

		return newContract;
	}

	public boolean isHardwareRequestsPermission() {
		return hardwareRequestsPermission;
	}

	public void setHardwareRequestsPermission(boolean hardwareRequestsPermission) {
		this.hardwareRequestsPermission = hardwareRequestsPermission;
	}

	public boolean isChangeRequestsPermission() {
		return changeRequestsPermission;
	}

	public void setChangeRequestsPermission(boolean changeRequestsPermission) {
		this.changeRequestsPermission = changeRequestsPermission;
	}

	public void setSoldTo(String soldTo) {
		this.soldTo = soldTo;
	}

	public String getSoldTo() {
		return soldTo;
	}

	public String getAssetId() {
		return assetId;
	}

	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}
	
}
