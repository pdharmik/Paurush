package com.lexmark.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.lexmark.service.impl.real.domain.PartnerAccountTypeDo;

public class Account implements Serializable {
    private static final long serialVersionUID = 877635713641960634L;
    private String accountId;
    private String accountName;
    private GenericAddress address;
    private String manualMeterRead;
    private String createServiceRequest;
    private String userConsumables;
    private AssetIdentifier primaryAssetIdentifier;
    private String accountType;
    private boolean orderPartsFlag;
    private String createShipToAddressFlag;  // Changed for CI-5 2098
    private boolean createClaimFlag;
    private boolean allowAdditionalPaymentRequestFlag;
    private String partQuantityOrderLimit;
    private boolean manualMeterReadFlag;
    private boolean createServiceRequestFlag;
    private boolean usesConsumablesFlag;
    private boolean activityUploadFlag;
    private boolean invoiceUploadFlag;
    private boolean debriefUploadFlag;
    private boolean viewInvoiceFlag;
    private String organizationID;
    private String addressStatus;
    private boolean indirectPartnerFlag;
    private boolean directPartnerFlag;
    
    private boolean logisticsPartnerFlag;
    private String defaultCurrency;
    private boolean uploadClaimFlag;
    private boolean uploadRequestFlag;
    private String uploadClaim;
    private String uploadRequest;
    private boolean orderPartsDebriefFlag;
    private String accountOrganization;
    private String accountSite;
    private String countryCode;
    private String displayName;

    // added for AmindContractedServiceRequestService.retrieveSiebelAccountList()
    private String agreementId;
    private String agreementName;
    private List<Agreement> agreements;
    private String vendorAccountId;
    private boolean createOrderFlag;
    private boolean viewOrderFlag;
    private String accountMgmtRequest;
    private boolean addressFlag;
    private String specialHandlingInstruction;
    private boolean accessServiceOrderFlag;
    private boolean createRequestsForCustomerFlag;
    private String upsCode;
    private boolean catalogEntitlementFlag; 
    private boolean assetEntitlementFlag; 
    private boolean catalogExpediteFlag; 
    private boolean assetExpediteFlag; 
    private String hardwareRequestFlag;
    private String showPriceFlag;
    private boolean poNumberFlag;
    private boolean creditNumberFlag;
    private List<String> soldToNumbers;
    private List<String> paymentTypes;
    private boolean massUploadFlag;
    private boolean accountSplitterFlag;
    private boolean invoiceFlag;
	private String contractNumber;
	private String contractLine;    
    private List<String> salesOrgs;
	private String organizationName;
	private boolean debriefInstallFlag;
	private boolean massUploadConsumablesFlag;
	private boolean massUploadInstallDebriefFlag;
	private boolean partnerRequestTabHideFlag;
	private String partnerCountry;
	private boolean viewAllCustomerOrderFlag;
	private boolean createChildSR;
	private List<PartnerAccountTypeDo> partnerTypeList;
	private String shipToDefault;	
	private boolean alliancePartner;
	private boolean lbsDisplayWeb;
	private String lbsFlag;
	private String b2bFlag;
	private String mpsQuantity;
	private boolean maintenanceKit;
	private String entitlementType;
	private String quantityServices;
	private String quantitySupplies;
	
	public String getQuantityServices() {
		return quantityServices;
	}
	public void setQuantityServices(String quantityServices) {
		this.quantityServices = quantityServices;
	}
	public String getQuantitySupplies() {
		return quantitySupplies;
	}
	public void setQuantitySupplies(String quantitySupplies) {
		this.quantitySupplies = quantitySupplies;
	}
	public String getEntitlementType() {
		return entitlementType;
	}
	public void setEntitlementType(String entitlementType) {
		this.entitlementType = entitlementType;
	}
	public boolean isMaintenanceKit() {
		return maintenanceKit;
	}
	public void setMaintenanceKit(boolean maintenanceKit) {
		this.maintenanceKit = maintenanceKit;
	}
	public String getMpsQuantity() {
		return mpsQuantity;
	}
	public void setMpsQuantity(String mpsQuantity) {
		this.mpsQuantity = mpsQuantity;
	}
		
	public String getLbsFlag() {
			return lbsFlag;
	}

	public void setLbsFlag(String lbsFlag) {
			this.lbsFlag = lbsFlag;
	}
	
	public String getB2bFlag() {
		return b2bFlag;
	}
	public void setB2bFlag(String b2bFlag) {
		this.b2bFlag = b2bFlag;
	}
	public boolean isAlliancePartner() {
		return alliancePartner;
	}

	public void setAlliancePartner(boolean alliancePartner) {
		this.alliancePartner = alliancePartner;
	}


	public boolean isMassUploadConsumablesFlag() {
		return massUploadConsumablesFlag;
	}

	public void setMassUploadConsumablesFlag(boolean massUploadConsumablesFlag) {
		this.massUploadConsumablesFlag = massUploadConsumablesFlag;
	}
	
	public boolean isDebriefInstallFlag() {
		return debriefInstallFlag;
	}

	public void setDebriefInstallFlag(boolean debriefInstallFlag) {
		this.debriefInstallFlag = debriefInstallFlag;
	}
  
	public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public GenericAddress getAddress() {
        return address;
    }

    public void setAddress(GenericAddress address) {
        this.address = address;
    }

    public AssetIdentifier getPrimaryAssetIdentifier() {
        return primaryAssetIdentifier;
    }

    public void setPrimaryAssetIdentifier(AssetIdentifier primaryAssetIdentifier) {
        this.primaryAssetIdentifier = primaryAssetIdentifier;
    }

    public String getManualMeterRead() {
        return manualMeterRead;
    }

    public void setManualMeterRead(String manualMeterRead) {
        this.manualMeterRead = manualMeterRead;
    }

    public String getCreateServiceRequest() {
        return createServiceRequest;
    }

    public void setCreateServiceRequest(String createServiceRequest) {
        this.createServiceRequest = createServiceRequest;
    }

    public String getUserConsumables() {
        return userConsumables;
    }

    public void setUserConsumables(String userConsumables) {
        this.userConsumables = userConsumables;
    }

    public boolean isOrderPartsFlag() {
        return orderPartsFlag;
    }

    public void setOrderPartsFlag(boolean orderPartsFlag) {
        this.orderPartsFlag = orderPartsFlag;
    }

    public String getCreateShipToAddressFlag() {
        return createShipToAddressFlag;
    }

    public void setCreateShipToAddressFlag(String createShipToAddressFlag) {
        this.createShipToAddressFlag = createShipToAddressFlag;
    }

    public boolean isCreateClaimFlag() {
        return createClaimFlag;
    }

    public void setCreateClaimFlag(boolean createClaimFlag) {
        this.createClaimFlag = createClaimFlag;
    }

    public boolean isAllowAdditionalPaymentRequestFlag() {
        return allowAdditionalPaymentRequestFlag;
    }

    public void setAllowAdditionalPaymentRequestFlag(
            boolean allowAdditionalPaymentRequestFlag) {
        this.allowAdditionalPaymentRequestFlag = allowAdditionalPaymentRequestFlag;
    }

    public String getPartQuantityOrderLimit() {
        return partQuantityOrderLimit;
    }

    public void setPartQuantityOrderLimit(String partQuantityOrderLimit) {
        this.partQuantityOrderLimit = partQuantityOrderLimit;
    }

    public boolean isManualMeterReadFlag() {
        return manualMeterReadFlag;
    }

    public void setManualMeterReadFlag(boolean manualMeterReadFlag) {
        this.manualMeterReadFlag = manualMeterReadFlag;
    }

    public boolean isCreateServiceRequestFlag() {
        return createServiceRequestFlag;
    }

    public void setCreateServiceRequestFlag(boolean createServiceRequestFlag) {
        this.createServiceRequestFlag = createServiceRequestFlag;
    }

    public boolean isUsesConsumablesFlag() {
        return usesConsumablesFlag;
    }

    public void setUsesConsumablesFlag(boolean usesConsumablesFlag) {
        this.usesConsumablesFlag = usesConsumablesFlag;
    }

    public boolean isActivityUploadFlag() {
        return activityUploadFlag;
    }

    public void setActivityUploadFlag(boolean activityUploadFlag) {
        this.activityUploadFlag = activityUploadFlag;
    }

    public boolean isInvoiceUploadFlag() {
        return invoiceUploadFlag;
    }

    public void setInvoiceUploadFlag(boolean invoiceUploadFlag) {
        this.invoiceUploadFlag = invoiceUploadFlag;
    }

    public boolean isDebriefUploadFlag() {
        return debriefUploadFlag;
    }

    public void setDebriefUploadFlag(boolean debriefUploadFlag) {
        this.debriefUploadFlag = debriefUploadFlag;
    }

    public boolean isViewInvoiceFlag() {
        return viewInvoiceFlag;
    }

    public void setViewInvoiceFlag(boolean viewInvoiceFlag) {
        this.viewInvoiceFlag = viewInvoiceFlag;
    }

    public String getOrganizationID() {
        return organizationID;
    }

    public void setOrganizationID(String organizationID) {
        this.organizationID = organizationID;
    }

    public String getAddressStatus() {
        return addressStatus;
    }

    public void setAddressStatus(String addressStatus) {
        this.addressStatus = addressStatus;
    }

    public boolean isIndirectPartnerFlag() {
        return indirectPartnerFlag;
    }

    public void setIndirectPartnerFlag(boolean indirectPartnerFlag) {
        this.indirectPartnerFlag = indirectPartnerFlag;
    }

    public boolean isDirectPartnerFlag() {
        return directPartnerFlag;
    }

    public void setDirectPartnerFlag(boolean directPartnerFlag) {
        this.directPartnerFlag = directPartnerFlag;
    }

    public boolean isLogisticsPartnerFlag() {
        return logisticsPartnerFlag;
    }

    public void setLogisticsPartnerFlag(boolean logisticsPartnerFlag) {
        this.logisticsPartnerFlag = logisticsPartnerFlag;
    }

    public String getDefaultCurrency() {
        return defaultCurrency;
    }

    public void setDefaultCurrency(String defaultCurrency) {
        this.defaultCurrency = defaultCurrency;
    }

    public void setUploadRequestFlag(boolean uploadRequestFlag) {
        this.uploadRequestFlag = uploadRequestFlag;
    }

    public String getUploadRequestFlag() {
        return uploadRequest;
    }

    public boolean isUploadRequestFlag() {
        return uploadRequestFlag;
    }

    public void setUploadRequest(String uploadRequest) {
        this.uploadRequest = uploadRequest;
    }

    public void setUploadClaimFlag(boolean uploadClaimFlag) {
        this.uploadClaimFlag = uploadClaimFlag;
    }

    public String getUploadClaimFlag() {
        return uploadClaim;
    }

    public boolean isUploadClaimFlag() {
        return uploadClaimFlag;
    }

    public void setUpladClaim(String uploadClaim) {
        this.uploadClaim = uploadClaim;
    }

    public void setOrderPartsDebriefFlag(boolean orderPartsDebriefFlag) {
        this.orderPartsDebriefFlag = orderPartsDebriefFlag;
    }

    public boolean isOrderPartsDebriefFlag() {
        return orderPartsDebriefFlag;
    }

    public String getUploadClaim() {
        return uploadClaim;
    }

    public void setUploadClaim(String uploadClaim) {
        this.uploadClaim = uploadClaim;
    }

    public String getAccountOrganization() {
        return accountOrganization;
    }

    public void setAccountOrganization(String accountOrganization) {
        this.accountOrganization = accountOrganization;
    }

    public String getAccountSite() {
        return accountSite;
    }

    public void setAccountSite(String accountSite) {
        this.accountSite = accountSite;
    }

    public String getUploadRequest() {
        return uploadRequest;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getAgreementName() {
        return agreementName;
    }

    public void setAgreementName(String agreementName) {
        this.agreementName = agreementName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getAgreementId() {
        return agreementId;
    }

    public void setAgreementId(String agreementId) {
        this.agreementId = agreementId;
    }

    /**
     * @return the agreements
     */
    public List<Agreement> getAgreements() {
        return agreements;
    }

    /**
     * @param agreements
     *            the agreements to set
     */
    public void setAgreements(List<Agreement> agreements) {
        this.agreements = agreements;
    }

    public String getVendorAccountId() {
        return vendorAccountId;
    }

    public void setVendorAccountId(String vendorAccountId) {
        this.vendorAccountId = vendorAccountId;
    }

    public boolean isCreateOrderFlag() {
        return createOrderFlag;
    }

    public void setCreateOrderFlag(boolean createOrderFlag) {
        this.createOrderFlag = createOrderFlag;
    }

    public boolean isViewOrderFlag() {
        return viewOrderFlag;
    }

    public void setViewOrderFlag(boolean viewOrderFlag) {
        this.viewOrderFlag = viewOrderFlag;
    }

    public String getAccountMgmtRequest() {
        return accountMgmtRequest;
    }

    public void setAccountMgmtRequest(String accountMgmtRequest) {
        this.accountMgmtRequest = accountMgmtRequest;
    }

    public void setAddressFlag(boolean addressFlag) {
        this.addressFlag = addressFlag;
    }

    public boolean isAddressFlag() {
        return addressFlag;
    }

    public void setSpecialHandlingInstruction(String specialHandlingInstruction) {
        this.specialHandlingInstruction = specialHandlingInstruction;
    }

    public String getSpecialHandlingInstruction() {
        return specialHandlingInstruction;
    }

    public void setAccessServiceOrderFlag(boolean accessServiceOrderFlag) {
        this.accessServiceOrderFlag = accessServiceOrderFlag;
    }

    public boolean isAccessServiceOrderFlag() {
        return accessServiceOrderFlag;
    }

    public void setCreateRequestsForCustomerFlag(
            boolean createRequestsForCustomerFlag) {
        this.createRequestsForCustomerFlag = createRequestsForCustomerFlag;
    }

    public boolean isCreateRequestsForCustomerFlag() {
        return createRequestsForCustomerFlag;
    }

    public void setUpsCode(String upsCode) {
        this.upsCode = upsCode;
    }

    public String getUpsCode() {
        return upsCode;
    }

    public boolean isCatalogEntitlementFlag() {
        return catalogEntitlementFlag;
    }

    public void setCatalogEntitlementFlag(boolean catalogEntitlementFlag) {
        this.catalogEntitlementFlag = catalogEntitlementFlag;
    }

	public boolean isAssetEntitlementFlag() {
		return assetEntitlementFlag;
	}

	public void setAssetEntitlementFlag(boolean assetEntitlementFlag) {
		this.assetEntitlementFlag = assetEntitlementFlag;
	}

    public boolean isCatalogExpediteFlag() {
        return catalogExpediteFlag;
    }

    public void setCatalogExpediteFlag(boolean catalogExpediteFlag) {
        this.catalogExpediteFlag = catalogExpediteFlag;
    }

	public void setAssetExpediteFlag(boolean assetExpediteFlag) {
		this.assetExpediteFlag = assetExpediteFlag;
	}

	public boolean isAssetExpediteFlag() {
		return assetExpediteFlag;
	}

	public List<String> getSoldToNumbers() {
		return soldToNumbers;
	}

	public void setSoldToNumbers(List<String> soldToNumbers) {
		this.soldToNumbers = soldToNumbers;
	}

	public List<String> getPaymentTypes() {
		return paymentTypes;
	}

	public void setPaymentTypes(List<String> paymentTypes) {
		this.paymentTypes = paymentTypes;
	}

	public String isHardwareRequestFlag() {
		return hardwareRequestFlag;
	}

	public void setHardwareRequestFlag(String hardwareRequestFlag) {
		this.hardwareRequestFlag = hardwareRequestFlag;
	}

	public String getShowPriceFlag() {
		return showPriceFlag;
	}

	public void setShowPriceFlag(String showPriceFlag) {
		this.showPriceFlag = showPriceFlag;
	}

	public boolean isPoNumberFlag() {
		return poNumberFlag;
	}

	public void setPoNumberFlag(boolean poNumberFlag) {
		this.poNumberFlag = poNumberFlag;
	}

	public boolean isCreditNumberFlag() {
		return creditNumberFlag;
	}

	public void setCreditNumberFlag(boolean creditNumberFlag) {
		this.creditNumberFlag = creditNumberFlag;
	}

	public List<String> getSalesOrgs() {
		return salesOrgs;
	}

	public void setSalesOrgs(List<String> salesOrgs) {
		this.salesOrgs = salesOrgs;
	}

	public boolean isMassUploadFlag() {
		return massUploadFlag;
	}

	public void setMassUploadFlag(boolean massUploadFlag) {
		this.massUploadFlag = massUploadFlag;
	}

	public boolean isAccountSplitterFlag() {
		return accountSplitterFlag;
	}

	public void setAccountSplitterFlag(boolean accountSplitterFlag) {
		this.accountSplitterFlag = accountSplitterFlag;
	}

	public boolean isInvoiceFlag() {
		return invoiceFlag;
	}

	public void setInvoiceFlag(boolean invoiceFlag) {
		this.invoiceFlag = invoiceFlag;
	}

	public String getContractNumber() {
		return contractNumber;
	}

	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}

	public String getContractLine() {
		return contractLine;
	}

	public void setContractLine(String contractLine) {
		this.contractLine = contractLine;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public boolean isPartnerRequestTabHideFlag() {
		return partnerRequestTabHideFlag;
	}

	public void setPartnerRequestTabHideFlag(boolean partnerRequestTabHideFlag) {
		this.partnerRequestTabHideFlag = partnerRequestTabHideFlag;
	}

	public void setMassUploadInstallDebriefFlag(boolean massUploadInstallDebriefFlag) {
		this.massUploadInstallDebriefFlag = massUploadInstallDebriefFlag;
	}

	public boolean isMassUploadInstallDebriefFlag() {
		return massUploadInstallDebriefFlag;
	}

	public String getPartnerCountry() {
		return partnerCountry;
	}

	public void setPartnerCountry(String partnerCountry) {
		this.partnerCountry = partnerCountry;
	}

	public boolean isViewAllCustomerOrderFlag() {
		return viewAllCustomerOrderFlag;
	}

	public void setViewAllCustomerOrderFlag(boolean viewAllCustomerOrderFlag) {
		this.viewAllCustomerOrderFlag = viewAllCustomerOrderFlag;
	}

	public boolean isCreateChildSR() {
		return createChildSR;
	}

	public void setCreateChildSR(boolean createChildSR) {
		this.createChildSR = createChildSR;
	}

	public List<PartnerAccountTypeDo> getPartnerTypeList() {
		return partnerTypeList;
	}

	public void setPartnerTypeList(List<PartnerAccountTypeDo> partnerTypeList) {
		this.partnerTypeList = partnerTypeList;
	}

	public String getShipToDefault() {
		return shipToDefault;
	}

	public void setShipToDefault(String shipToDefault) {
		this.shipToDefault = shipToDefault;
	}

	public boolean isLbsDisplayWeb() {
		return lbsDisplayWeb;
	}

	public void setLbsDisplayWeb(boolean lbsDisplayWeb) {
		this.lbsDisplayWeb = lbsDisplayWeb;
	}
	
}
