package com.lexmark.service.impl.real.domain;

import java.util.ArrayList;

/**
 * mapping file: "do-accountdo-mapping.xml"
 */
public class AccountDo extends AccountBasedDo {
	private static final long serialVersionUID = 3629931038080017400L;

	private String createServiceRequest;
	private String manualMeterRead;
	private String usesConsumables;
	private String functionalClassification;
	private String mdmLevel;
	
	private String agreementId;
	private String agreementName;
	
	// address
	private String address1;
	private String address2;
	private String address3;
	private String city;
	private String country;
	private String postalCode;
	private String province;
	private String state;

	private String changeManagment;
	private String addressFlag;
	private String specialHandlingInstruction;
	private String upsCode;
	private String expediteOrderFlag;
	private boolean invoiceFlag;
	private boolean massUploadFlag;
	private String hardwareFlag;
	
	private String county;
	private String district;
	private String officeNumber;
	private String alliancePartner; 
	private String shipToDefault;
	private String lbsDisplayWeb;
	private String lbsFlag;
	private String b2bFlag;
	private String deviceStatus;
	private String lbsUtilization;

	public String getDeviceStatus() {
		return deviceStatus;
	}

	public void setDeviceStatus(String deviceStatus) {
		this.deviceStatus = deviceStatus;
	}

	
	public String getLbsUtilization() {
		return lbsUtilization;
	}

	public void setLbsUtilization(String lbsUtilization) {
		this.lbsUtilization = lbsUtilization;
	}

	public String getLbsFlag() {
		return lbsFlag;
	}

	public void setLbsFlag(String lbsFlag) {
		this.lbsFlag = lbsFlag;
}
	
	public String getShipToDefault() {
		return shipToDefault;
	}

	public void setShipToDefault(String shipToDefault) {
		this.shipToDefault = shipToDefault;
	}

	public String getAlliancePartner() {
		return alliancePartner;
	}

	public void setAlliancePartner(String alliancePartner) {
		this.alliancePartner = alliancePartner;
	}

	public String getFunctionalClassification() {
		return functionalClassification;
	}

	public void setFunctionalClassification(String functionalClassification) {
		this.functionalClassification = functionalClassification;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getAddress3() {
		return address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCreateServiceRequest() {
		return createServiceRequest;
	}

	public void setCreateServiceRequest(String createServiceRequest) {
		this.createServiceRequest = createServiceRequest;
	}

	public String getManualMeterRead() {
		return manualMeterRead;
	}

	public void setManualMeterRead(String manualMeterRead) {
		this.manualMeterRead = manualMeterRead;
	}

	public String getUsesConsumables() {
		return usesConsumables;
	}

	public void setUsesConsumables(String usesConsumables) {
		this.usesConsumables = usesConsumables;
	}

	public String getAgreementName() {
		return agreementName;
	}

	public void setAgreementName(String agreementName) {
		this.agreementName = agreementName;
	}

	public void setMdmLevel(String mdmLevel) {
		this.mdmLevel = mdmLevel;
	}

	public String getMdmLevel() {
		return mdmLevel;
	}

	public String getAgreementId() {
		return agreementId;
	}

	public void setAgreementId(String agreementId) {
		this.agreementId = agreementId;
	}

	public void setChangeManagment(String changeManagment) {
		this.changeManagment = changeManagment;
	}

	public String getChangeManagment() {
		return changeManagment;
	}

	public String getAddressFlag() {
		return addressFlag;
	}

	public void setAddressFlag(String addressFlag) {
		this.addressFlag = addressFlag;
	}

	public void setSpecialHandlingInstruction(String specialHandlingInstruction) {
		this.specialHandlingInstruction = specialHandlingInstruction;
	}

	public String getSpecialHandlingInstruction() {
		return specialHandlingInstruction;
	}

	public void setUpsCode(String upsCode) {
		this.upsCode = upsCode;
	}

	public String getUpsCode() {
		return upsCode;
	}

    public String getExpediteOrderFlag() {
        return expediteOrderFlag;
    }

    public void setExpediteOrderFlag(String expediteOrderFlag) {
        this.expediteOrderFlag = expediteOrderFlag;
    }

	public boolean isInvoiceFlag() {
		return invoiceFlag;
	}

	public void setInvoiceFlag(boolean invoiceFlag) {
		this.invoiceFlag = invoiceFlag;
	}

	public boolean isMassUploadFlag() {
		return massUploadFlag;
	}

	public void setMassUploadFlag(boolean massUploadFlag) {
		this.massUploadFlag = massUploadFlag;
	}

	public String getHardwareFlag() {
		return hardwareFlag;
	}

	public void setHardwareFlag(String hardwareFlag) {
		this.hardwareFlag = hardwareFlag;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getOfficeNumber() {
		return officeNumber;
	}

	public void setOfficeNumber(String officeNumber) {
		this.officeNumber = officeNumber;
	}

	public String getLbsDisplayWeb() {
		return lbsDisplayWeb;
	}

	public void setLbsDisplayWeb(String lbsDisplayWeb) {
		this.lbsDisplayWeb = lbsDisplayWeb;
	}

	public String getB2bFlag() {
		return b2bFlag;
	}

	public void setB2bFlag(String b2bFlag) {
		this.b2bFlag = b2bFlag;
	}
	
}
