package com.lexmark.service.impl.real.domain;

import java.util.ArrayList;

/**
 * The mapping file:
 * do-ordersuppliesassetdetaildo-mapping.xml
 * 
 * @author vpetruchok
 * @version 1.0, Mar 21, 2012
 */
@SuppressWarnings("serial")
public class OrderSuppliesAssetDetailDo extends OrderSuppliesAssetDo {

    private String modelNumber;
    private String contactFirstName;
    private String contactLastName;
    private String contactMiddleName;
    private String contactEmailAddress;
    private String contactWorkPhone;
    private String contactAlteratePhone;
    private String accountId;
    private String accountName;
    private ArrayList<OrderSuppliesAssetPartDetailDo> parts;
    private String languageName;
    private String assetCostCenter;
    private String description;
    private String defaultSpecialInstruction;
    private String notes;
    private String addressFlag;
    private String storeFrontName;
    private ArrayList<OrderSuppliesAssetPageCountDo> pageCounts;
    private String meterType;
    private ArrayList<OrderSuppliesAssetAgreementEntitlementDo> agreementEntitlements;
    private ArrayList<OrderSuppliesAssetOrderPartDo> orderParts;
    private String paymentMethod;
    private String splitterFlag;
    private String organizationName;
    private String countryIsoCode;
    private String region;
    private ArrayList<AssetDetailContactDo> deviceContacts;
    private String chlNodeValue;
    private String contractNumber;
    private String chlNodeId;
    private String accountSplitterFlag;
    private Boolean lbsAddressFlag;
    private String descriptionLocalLang;
    private String modelMDM;
    private String lbsLongitude;
    private String lbsLatitude;
    private String coordinatesXPreDebriefRFV;
    private String coordinatesYPreDebriefRFV;
    private String levelOfDetails;
    
    public String getLbsLongitude() {
		return lbsLongitude;
	}

	public void setLbsLongitude(String lbsLongitude) {
		this.lbsLongitude = lbsLongitude;
	}

	public String getLbsLatitude() {
		return lbsLatitude;
	}

	public void setLbsLatitude(String lbsLatitude) {
		this.lbsLatitude = lbsLatitude;
	}

    
    public String getModelNumber() {
        return modelNumber;
    }

    public void setModelNumber(String modelNumber) {
        this.modelNumber = modelNumber;
    }

    public String getContactFirstName() {
        return contactFirstName;
    }

    public void setContactFirstName(String contactFirstName) {
        this.contactFirstName = contactFirstName;
    }

    public String getContactLastName() {
        return contactLastName;
    }

    public void setContactLastName(String contactLastName) {
        this.contactLastName = contactLastName;
    }

    public String getContactMiddleName() {
        return contactMiddleName;
    }

    public void setContactMiddleName(String contactMiddleName) {
        this.contactMiddleName = contactMiddleName;
    }

    public String getContactEmailAddress() {
        return contactEmailAddress;
    }

    public void setContactEmailAddress(String contactEmailAddress) {
        this.contactEmailAddress = contactEmailAddress;
    }

    public String getContactWorkPhone() {
        return contactWorkPhone;
    }

    public void setContactWorkPhone(String contactWorkPhone) {
        this.contactWorkPhone = contactWorkPhone;
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

    public ArrayList<OrderSuppliesAssetPartDetailDo> getParts() {
        return parts;
    }

    public void setParts(ArrayList<OrderSuppliesAssetPartDetailDo> parts) {
        this.parts = parts;
    }

    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }

    public String getAssetCostCenter() {
        return assetCostCenter;
    }

    public void setAssetCostCenter(String costCenter) {
        this.assetCostCenter = costCenter;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDefaultSpecialInstruction() {
        return defaultSpecialInstruction;
    }

    public void setDefaultSpecialInstruction(String defaultSpecialInstruction) {
        this.defaultSpecialInstruction = defaultSpecialInstruction;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getAddressFlag() {
        return addressFlag;
    }

    public void setAddressFlag(String addressFlag) {
        this.addressFlag = addressFlag;
    }

    public String getStoreFrontName() {
        return storeFrontName;
    }

    public void setStoreFrontName(String storeFrontName) {
        this.storeFrontName = storeFrontName;
    }

    public ArrayList<OrderSuppliesAssetPageCountDo> getPageCounts() {
        return pageCounts;
    }

    public void setPageCounts(ArrayList<OrderSuppliesAssetPageCountDo> pageCounts) {
        this.pageCounts = pageCounts;
    }

    public String getMeterType() {
        return meterType;
    }

    public void setMeterType(String meterType) {
        this.meterType = meterType;
    }

	public void setContactAlteratePhone(String contactAlteratePhone) {
		this.contactAlteratePhone = contactAlteratePhone;
	}

	public String getContactAlteratePhone() {
		return contactAlteratePhone;
	}

    public ArrayList<OrderSuppliesAssetAgreementEntitlementDo> getAgreementEntitlements() {
        return agreementEntitlements;
    }

    public void setAgreementEntitlements(ArrayList<OrderSuppliesAssetAgreementEntitlementDo> agreementEntitlements) {
        this.agreementEntitlements = agreementEntitlements;
    }

    public ArrayList<OrderSuppliesAssetOrderPartDo> getOrderParts() {
        return orderParts;
    }

    public void setOrderParts(ArrayList<OrderSuppliesAssetOrderPartDo> orderParts) {
        this.orderParts = orderParts;
    }

	public String getSplitterFlag() {
		return splitterFlag;
	}

	public void setSplitterFlag(String splitterFlag) {
		this.splitterFlag = splitterFlag;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getCountryIsoCode() {
		return countryIsoCode;
	}

	public void setCountryIsoCode(String countryIsoCode) {
		this.countryIsoCode = countryIsoCode;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public ArrayList<AssetDetailContactDo> getDeviceContacts() {
		return deviceContacts;
	}

	public void setDeviceContacts(ArrayList<AssetDetailContactDo> deviceContacts) {
		this.deviceContacts = deviceContacts;
	}

	public String getChlNodeValue() {
		return chlNodeValue;
	}

	public void setChlNodeValue(String chlNodeValue) {
		this.chlNodeValue = chlNodeValue;
	}

	public String getContractNumber() {
		return contractNumber;
	}

	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}

	public String getChlNodeId() {
		return chlNodeId;
	}

	public void setChlNodeId(String chlNodeId) {
		this.chlNodeId = chlNodeId;
	}

	public String getAccountSplitterFlag() {
		return accountSplitterFlag;
	}

	public void setAccountSplitterFlag(String accountSplitterFlag) {
		this.accountSplitterFlag = accountSplitterFlag;
	}

	public Boolean isLbsAddressFlag() {
		return lbsAddressFlag;
	}

	public void setLbsAddressFlag(Boolean lbsAddressFlag) {
		this.lbsAddressFlag = lbsAddressFlag;
	}
	
	public String getDescriptionLocalLang() {
		return descriptionLocalLang;
	}

	public void setDescriptionLocalLang(String descriptionLocalLang) {
		this.descriptionLocalLang = descriptionLocalLang;
	}

	public String getModelMDM() {
		return modelMDM;
	}

	public void setModelMDM(String modelMDM) {
		this.modelMDM = modelMDM;
	}

	public String getCoordinatesXPreDebriefRFV() {
		return coordinatesXPreDebriefRFV;
	}

	public void setCoordinatesXPreDebriefRFV(String coordinatesXPreDebriefRFV) {
		this.coordinatesXPreDebriefRFV = coordinatesXPreDebriefRFV;
	}

	public String getCoordinatesYPreDebriefRFV() {
		return coordinatesYPreDebriefRFV;
	}

	public void setCoordinatesYPreDebriefRFV(String coordinatesYPreDebriefRFV) {
		this.coordinatesYPreDebriefRFV = coordinatesYPreDebriefRFV;
	}

	public String getLevelOfDetails() {
		return levelOfDetails;
	}

	public void setLevelOfDetails(String levelOfDetails) {
		this.levelOfDetails = levelOfDetails;
	}
}
