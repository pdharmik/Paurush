package com.lexmark.domain;

import java.io.Serializable;

public class UserDetails implements Serializable {
    private static final long serialVersionUID = 877635713641960634L;
    
    //For Customer Portal
    private boolean showServiceReq;
	private boolean createServiceReq;
	private boolean showAccMgmtReq;
	private boolean createAccMgmtReq;
	private boolean showPageCount;
	private boolean updatePageCount;
	private boolean showConsumableReq;
	private boolean updateConsumableReq;
	private boolean showCmsConsumableReq;
	
	private boolean showHardwareReq;
	private boolean updateHardwareReq;
	private boolean showCustomerInvoices;
	private boolean mpsCust;
	private boolean cssCust;
	
	private boolean showManualMeterRead;
	private boolean updateManualMeterRead;

	//For Partner Portal
	private boolean uploadClaim;
	private boolean uploadRequest;
	private boolean fulfillServiceOrder;
	private boolean createConsumableReq4Customer;
	private boolean massUploadServiceOrders;
	private boolean partnerRequestTabHide;
	private boolean viewAllCustomerOrderFlag;
	private boolean shipToDefault;
	private boolean CreateChildSR;
	private boolean alliancePartner;
	//added for LBS
	private boolean fleetMngtFlag;
	private boolean deviceFinder;
	
	public boolean isAlliancePartner() {
		return alliancePartner;
	}
	public void setAlliancePartner(boolean alliancePartner) {
		this.alliancePartner = alliancePartner;
	}
	public boolean isCreateChildSR() {
		return CreateChildSR;
	}
	public void setCreateChildSR(boolean createChildSR) {
		CreateChildSR = createChildSR;
	}
	public boolean isViewAllCustomerOrderFlag() {
		return viewAllCustomerOrderFlag;
	}
	public void setViewAllCustomerOrderFlag(boolean viewAllCustomerOrderFlag) {
		this.viewAllCustomerOrderFlag = viewAllCustomerOrderFlag;
	}
	public boolean isPartnerRequestTabHide() {
		return partnerRequestTabHide;
	}
	public void setPartnerRequestTabHide(boolean partnerRequestTabHide) {
		this.partnerRequestTabHide = partnerRequestTabHide;
	}
	public boolean isShowServiceReq() {
		return showServiceReq;
	}
	public void setShowServiceReq(boolean showServiceReq) {
		this.showServiceReq = showServiceReq;
	}
	public boolean isCreateServiceReq() {
		return createServiceReq;
	}
	public void setCreateServiceReq(boolean createServiceReq) {
		this.createServiceReq = createServiceReq;
	}
	public boolean isShowAccMgmtReq() {
		return showAccMgmtReq;
	}
	public void setShowAccMgmtReq(boolean showAccMgmtReq) {
		this.showAccMgmtReq = showAccMgmtReq;
	}
	public boolean isCreateAccMgmtReq() {
		return createAccMgmtReq;
	}
	public void setCreateAccMgmtReq(boolean createAccMgmtReq) {
		this.createAccMgmtReq = createAccMgmtReq;
	}
	public boolean isShowPageCount() {
		return showPageCount;
	}
	public void setShowPageCount(boolean showPageCount) {
		this.showPageCount = showPageCount;
	}
	public boolean isUpdatePageCount() {
		return updatePageCount;
	}
	public void setUpdatePageCount(boolean updatePageCount) {
		this.updatePageCount = updatePageCount;
	}
	public boolean isShowConsumableReq() {
		return showConsumableReq;
	}
	public void setShowConsumableReq(boolean showConsumableReq) {
		this.showConsumableReq = showConsumableReq;
	}
	public boolean isUpdateConsumableReq() {
		return updateConsumableReq;
	}
	public void setUpdateConsumableReq(boolean updateConsumableReq) {
		this.updateConsumableReq = updateConsumableReq;
	}

	public boolean isShowCmsConsumableReq() {
		return showCmsConsumableReq;
	}
	public void setShowCmsConsumableReq(boolean showCmsConsumableReq) {
		this.showCmsConsumableReq = showCmsConsumableReq;
	}
	public boolean isShowHardwareReq() {
		return showHardwareReq;
	}
	public void setShowHardwareReq(boolean showHardwareReq) {
		this.showHardwareReq = showHardwareReq;
	}
	public boolean isUpdateHardwareReq() {
		return updateHardwareReq;
	}
	public void setUpdateHardwareReq(boolean updateHardwareReq) {
		this.updateHardwareReq = updateHardwareReq;
	}
	public boolean isShowCustomerInvoices() {
		return showCustomerInvoices;
	}
	public void setShowCustomerInvoices(boolean showCustomerInvoices) {
		this.showCustomerInvoices = showCustomerInvoices;
	}
	public boolean isMpsCust() {
		return mpsCust;
	}
	public void setMpsCust(boolean mpsCust) {
		this.mpsCust = mpsCust;
	}	
	public boolean isCssCust() {
		return cssCust;
	}
	public void setCssCust(boolean cssCust) {
		this.cssCust = cssCust;
	}
	public boolean isShowManualMeterRead() {
		return showManualMeterRead;
	}
	public void setShowManualMeterRead(boolean showManualMeterRead) {
		this.showManualMeterRead = showManualMeterRead;
	}
	public boolean isUpdateManualMeterRead() {
		return updateManualMeterRead;
	}
	public void setUpdateManualMeterRead(boolean updateManualMeterRead) {
		this.updateManualMeterRead = updateManualMeterRead;
	}
	public boolean isUploadClaim() {
		return uploadClaim;
	}
	public void setUploadClaim(boolean uploadClaim) {
		this.uploadClaim = uploadClaim;
	}
	public boolean isUploadRequest() {
		return uploadRequest;
	}
	public void setUploadRequest(boolean uploadRequest) {
		this.uploadRequest = uploadRequest;
	}
	public boolean isFulfillServiceOrder() {
		return fulfillServiceOrder;
	}
	public void setFulfillServiceOrder(boolean fulfillServiceOrder) {
		this.fulfillServiceOrder = fulfillServiceOrder;
	}
	public boolean isCreateConsumableReq4Customer() {
		return createConsumableReq4Customer;
	}
	public void setCreateConsumableReq4Customer(boolean createConsumableReq4Customer) {
		this.createConsumableReq4Customer = createConsumableReq4Customer;
	}
	public boolean isMassUploadServiceOrders() {
		return massUploadServiceOrders;
	}
	public void setMassUploadServiceOrders(boolean massUploadServiceOrders) {
		this.massUploadServiceOrders = massUploadServiceOrders;
	}
	/**
	 * @param shipToDefault the shipToDefault to set
	 */
	public void setShipToDefault(boolean shipToDefault) {
		this.shipToDefault = shipToDefault;
	}
	/**
	 * @return the shipToDefault
	 */
	public boolean getShipToDefault() {
		return shipToDefault;
	}
	public boolean isFleetMngtFlag() {
		return fleetMngtFlag;
	}
	public void setFleetMngtFlag(boolean fleetMngtFlag) {
		this.fleetMngtFlag = fleetMngtFlag;
	}
	public boolean isDeviceFinder() {
		return deviceFinder;
	}
	public void setDeviceFinder(boolean deviceFinder) {
		this.deviceFinder = deviceFinder;
	}
	
	
    
}
