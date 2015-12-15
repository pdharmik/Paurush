package com.lexmark.service.impl.real.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * mapping file: "do-requesttype-mapping.xml"
 */
public class RequestTypeDo extends AccountBasedDo implements Serializable {
	private static final long serialVersionUID = -6987829115478908724L;

	// query data
	private String agreementId;
	private String mdmLevel;
	private String chlNodeId;
	private String chlNodeName;
	private String assetId;
	private String agreementMdmLevel1AccountId;
	private String agreementMdmLevel2AccountId;
	private String agreementMdmLevel3AccountId;
	private String agreementMdmLevel4AccountId;
	private String agreementMdmLevel5AccountId;
	private String vendorMdmLevel1AccountId;
	private String vendorMdmLevel2AccountId;
	private String vendorMdmLevel3AccountId;
	private String vendorMdmLevel4AccountId;
	// result data
	private String requestNumber;
	private String serviceRequestType;
	private Date serviceRequestDate;
	private String statusType;
	private String serialNumber;
	private String productModel;
	private String problemDescription;
	private Boolean expediteOrder;
	private String area;
	private String subArea;
	private String helpDeskReferenceNumber;
	private String eta;
	private String poNumber;
	private String contractType;
	private String vendorAccountId;
	// primary contract
	private String primaryContactFirstName;
	private String primaryContactLastName;
	private String primaryContactEmailAddress;
	private String primaryContactWorkPhone;
	// requestor contract
	private String requestorContactFirstName;
	private String requestorContactLastName;
	private String requestorContactEmailAddress;
	private String requestorContactWorkPhone;	
	// service address
	private String serviceAddressLine1;
	private String serviceAddressLine2;
	private String serviceAddressLine3;
	private String serviceCity;
	private String serviceState;
	private String servicePostalCode;
	private String serviceProvince;
	private String serviceCountry;
	private String serviceAddressName;
	private String costCenter;
	private String accountName;
	private String updatedSrNumber;
	private String hostName;
	private String deviceTag;
	private ArrayList<RequestTypePartDo> parts;
	
	private String contactId;
	private String primaryContactId;
	private String alternateContactId;
	private String assetCostCenter;
	private String orderId;
	private String serviceRequestSLA;
	private String serviceRequestETA;
	private String resolutionCode;
	private String county;
	private String district;
	private String officeNumber;
    private String storeFrontName;
	private String customerReportingName;
	private String coveredServiceType;
	private String serviceOverrideType;
	private String serviceRequestStatus;
	// New MPS fields for retrieveRequestList call. USed only if assetid is null 				
	
	private String mpsDeviceTag;
	private String mpsSerialNumber;
	private String mpsHostName;
	private String madcCostCenter;
	private Date installDate;
	private Date madcInstallDate;
	private String ipAddress;
	private String madcIPAddress;
	private String productTLI;
	private String madcProductTLI;
	private String webOrderNumber;
	//OPS
	private String subStatus;
	private String severity;
	private String projectName;
	private String projectPhase;
	private String agreementNumber;
	private String agreementName;
	
	public String getAgreementNumber() {
		return agreementNumber;
	}

	public void setAgreementNumber(String agreementNumber) {
		this.agreementNumber = agreementNumber;
	}

	public String getAgreementName() {
		return agreementName;
	}

	public void setAgreementName(String agreementName) {
		this.agreementName = agreementName;
	}

	public String getMpsHostName() {
		return mpsHostName;
	}

	public void setMpsHostName(String mpsHostName) {
		this.mpsHostName = mpsHostName;
	}

	public String getMpsDeviceTag() {
		return mpsDeviceTag;
	}

	public void setMpsDeviceTag(String mpsDeviceTag) {
		this.mpsDeviceTag = mpsDeviceTag;
	}

	public String getMpsSerialNumber() {
		return mpsSerialNumber;
	}

	public void setMpsSerialNumber(String mpsSerialNumber) {
		this.mpsSerialNumber = mpsSerialNumber;
	}

	
	
	public String getAgreementId() {
		return agreementId;
	}

	public void setAgreementId(String agreementId) {
		this.agreementId = agreementId;
	}

	public String getRequestNumber() {
		return requestNumber;
	}

	public void setRequestNumber(String requestNumber) {
		this.requestNumber = requestNumber;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getProductModel() {
		return productModel;
	}

	public void setProductModel(String productModel) {
		this.productModel = productModel;
	}

	public String getHelpDeskReferenceNumber() {
		return helpDeskReferenceNumber;
	}

	public void setHelpDeskReferenceNumber(String helpDeskReferenceNumber) {
		this.helpDeskReferenceNumber = helpDeskReferenceNumber;
	}

	public String getEta() {
		return eta;
	}

	public void setEta(String eta) {
		this.eta = eta;
	}

	public String getServiceAddressLine1() {
		return serviceAddressLine1;
	}

	public void setServiceAddressLine1(String serviceAddressLine1) {
		this.serviceAddressLine1 = serviceAddressLine1;
	}

	public String getServiceAddressLine2() {
		return serviceAddressLine2;
	}

	public void setServiceAddressLine2(String serviceAddressLine2) {
		this.serviceAddressLine2 = serviceAddressLine2;
	}

	public String getServiceAddressLine3() {
		return serviceAddressLine3;
	}

	public void setServiceAddressLine3(String serviceAddressLine3) {
		this.serviceAddressLine3 = serviceAddressLine3;
	}

	public String getServiceCity() {
		return serviceCity;
	}

	public void setServiceCity(String serviceCity) {
		this.serviceCity = serviceCity;
	}

	public String getServiceState() {
		return serviceState;
	}

	public void setServiceState(String serviceState) {
		this.serviceState = serviceState;
	}

	public String getServicePostalCode() {
		return servicePostalCode;
	}

	public void setServicePostalCode(String servicePostalCode) {
		this.servicePostalCode = servicePostalCode;
	}

	public String getServiceProvince() {
		return serviceProvince;
	}

	public void setServiceProvince(String serviceProvince) {
		this.serviceProvince = serviceProvince;
	}

	public String getServiceCountry() {
		return serviceCountry;
	}

	public void setServiceCountry(String serviceCountry) {
		this.serviceCountry = serviceCountry;
	}

	public String getServiceAddressName() {
		return serviceAddressName;
	}

	public void setServiceAddressName(String serviceAddressName) {
		this.serviceAddressName = serviceAddressName;
	}

	public String getPrimaryContactFirstName() {
		return primaryContactFirstName;
	}

	public void setPrimaryContactFirstName(String primaryContactFirstName) {
		this.primaryContactFirstName = primaryContactFirstName;
	}

	public String getPrimaryContactLastName() {
		return primaryContactLastName;
	}

	public void setPrimaryContactLastName(String primaryContactLastName) {
		this.primaryContactLastName = primaryContactLastName;
	}

	public String getPrimaryContactEmailAddress() {
		return primaryContactEmailAddress;
	}

	public void setPrimaryContactEmailAddress(String primaryContactEmailAddress) {
		this.primaryContactEmailAddress = primaryContactEmailAddress;
	}

	public String getPrimaryContactWorkPhone() {
		return primaryContactWorkPhone;
	}

	public void setPrimaryContactWorkPhone(String primaryContactWorkPhone) {
		this.primaryContactWorkPhone = primaryContactWorkPhone;
	}

	public String getProblemDescription() {
		return problemDescription;
	}

	public void setProblemDescription(String problemDescription) {
		this.problemDescription = problemDescription;
	}

	public void setExpediteOrder(Boolean expediteOrder) {
		this.expediteOrder = expediteOrder;
	}

	public String getServiceRequestType() {
		return serviceRequestType;
	}

	public void setServiceRequestType(String serviceRequestType) {
		this.serviceRequestType = serviceRequestType;
	}

	public Date getServiceRequestDate() {
		return serviceRequestDate;
	}

	public void setServiceRequestDate(Date serviceRequestDate) {
		this.serviceRequestDate = serviceRequestDate;
	}

	public String getStatusType() {
		return statusType;
	}

	public void setStatusType(String statusType) {
		this.statusType = statusType;
	}

	public String getPoNumber() {
		return poNumber;
	}

	public void setPoNumber(String poNumber) {
		this.poNumber = poNumber;
	}

	public String getContractType() {
		return contractType;
	}

	public void setContractType(String contractType) {
		this.contractType = contractType;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getSubArea() {
		return subArea;
	}

	public void setSubArea(String subArea) {
		this.subArea = subArea;
	}

	public ArrayList<RequestTypePartDo> getParts() {
		return parts;
	}

	public void setParts(ArrayList<RequestTypePartDo> parts) {
		this.parts = parts;
	}

	public String getRequestorContactFirstName() {
		return requestorContactFirstName;
	}

	public void setRequestorContactFirstName(String requestorContactFirstName) {
		this.requestorContactFirstName = requestorContactFirstName;
	}

	public String getRequestorContactLastName() {
		return requestorContactLastName;
	}

	public void setRequestorContactLastName(String requestorContactLastName) {
		this.requestorContactLastName = requestorContactLastName;
	}

	public String getRequestorContactEmailAddress() {
		return requestorContactEmailAddress;
	}

	public void setRequestorContactEmailAddress(String requestorContactEmailAddress) {
		this.requestorContactEmailAddress = requestorContactEmailAddress;
	}

	public String getRequestorContactWorkPhone() {
		return requestorContactWorkPhone;
	}

	public void setRequestorContactWorkPhone(String requestorContactWorkPhone) {
		this.requestorContactWorkPhone = requestorContactWorkPhone;
	}

	public Boolean getExpediteOrder() {
		return expediteOrder;
	}

	public void setAgreementMdmLevel5AccountId(
			String agreementMdmLevel5AccountId) {
		this.agreementMdmLevel5AccountId = agreementMdmLevel5AccountId;
	}

	public String getAgreementMdmLevel5AccountId() {
		return agreementMdmLevel5AccountId;
	}

	public String getMdmLevel() {
		return mdmLevel;
	}

	public void setMdmLevel(String mdmLevel) {
		this.mdmLevel = mdmLevel;
	}

	public String getAgreementMdmLevel1AccountId() {
		return agreementMdmLevel1AccountId;
	}

	public void setAgreementMdmLevel1AccountId(String agreementMdmLevel1AccountId) {
		this.agreementMdmLevel1AccountId = agreementMdmLevel1AccountId;
	}

	public String getAgreementMdmLevel2AccountId() {
		return agreementMdmLevel2AccountId;
	}

	public void setAgreementMdmLevel2AccountId(String agreementMdmLevel2AccountId) {
		this.agreementMdmLevel2AccountId = agreementMdmLevel2AccountId;
	}

	public String getAgreementMdmLevel3AccountId() {
		return agreementMdmLevel3AccountId;
	}

	public void setAgreementMdmLevel3AccountId(String agreementMdmLevel3AccountId) {
		this.agreementMdmLevel3AccountId = agreementMdmLevel3AccountId;
	}

	public String getAgreementMdmLevel4AccountId() {
		return agreementMdmLevel4AccountId;
	}

	public void setAgreementMdmLevel4AccountId(String agreementMdmLevel4AccountId) {
		this.agreementMdmLevel4AccountId = agreementMdmLevel4AccountId;
	}

	public String getChlNodeId() {
		return chlNodeId;
	}

	public void setChlNodeId(String chlNodeId) {
		this.chlNodeId = chlNodeId;
	}

	public String getChlNodeName() {
		return chlNodeName;
	}

	public void setChlNodeName(String chlNodeName) {
		this.chlNodeName = chlNodeName;
	}

	public String getAssetId() {
		return assetId;
	}

	public void setAssetId(String assetId) {
		this.assetId = assetId;
	}

	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}

	public String getCostCenter() {
		return costCenter;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setVendorAccountId(String vendorAccountId) {
		this.vendorAccountId = vendorAccountId;
	}

	public String getVendorAccountId() {
		return vendorAccountId;
	}

	public void setUpdatedSrNumber(String updatedSrNumber) {
		this.updatedSrNumber = updatedSrNumber;
	}

	public String getUpdatedSrNumber() {
		return updatedSrNumber;
	}

	public String getVendorMdmLevel1AccountId() {
		return vendorMdmLevel1AccountId;
	}

	public void setVendorMdmLevel1AccountId(String vendorMdmLevel1AccountId) {
		this.vendorMdmLevel1AccountId = vendorMdmLevel1AccountId;
	}

	public String getVendorMdmLevel2AccountId() {
		return vendorMdmLevel2AccountId;
	}

	public void setVendorMdmLevel2AccountId(String vendorMdmLevel2AccountId) {
		this.vendorMdmLevel2AccountId = vendorMdmLevel2AccountId;
	}

	public String getVendorMdmLevel3AccountId() {
		return vendorMdmLevel3AccountId;
	}

	public void setVendorMdmLevel3AccountId(String vendorMdmLevel3AccountId) {
		this.vendorMdmLevel3AccountId = vendorMdmLevel3AccountId;
	}

	public String getVendorMdmLevel4AccountId() {
		return vendorMdmLevel4AccountId;
	}

	public void setVendorMdmLevel4AccountId(String vendorMdmLevel4AccountId) {
		this.vendorMdmLevel4AccountId = vendorMdmLevel4AccountId;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getDeviceTag() {
		return deviceTag;
	}

	public void setDeviceTag(String deviceTag) {
		this.deviceTag = deviceTag;
	}

    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public String getPrimaryContactId() {
        return primaryContactId;
    }

    public void setPrimaryContactId(String primaryContactId) {
        this.primaryContactId = primaryContactId;
    }

    public String getAlternateContactId() {
        return alternateContactId;
    }

    public void setAlternateContactId(String alternateContactId) {
        this.alternateContactId = alternateContactId;
    }

	public void setAssetCostCenter(String assetCostCenter) {
		this.assetCostCenter = assetCostCenter;
	}

	public String getAssetCostCenter() {
		return assetCostCenter;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderId() {
		return orderId;
	}

	public String getServiceRequestSLA() {
		return serviceRequestSLA;
	}

	public void setServiceRequestSLA(String serviceRequestSLA) {
		this.serviceRequestSLA = serviceRequestSLA;
	}

	public String getServiceRequestETA() {
		return serviceRequestETA;
	}

	public void setServiceRequestETA(String serviceRequestETA) {
		this.serviceRequestETA = serviceRequestETA;
	}

	public String getResolutionCode() {
		return resolutionCode;
	}

	public void setResolutionCode(String resolutionCode) {
		this.resolutionCode = resolutionCode;
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

	public String getStoreFrontName() {
		return storeFrontName;
	}

	public void setStoreFrontName(String storeFrontName) {
		this.storeFrontName = storeFrontName;
	}

	public String getCustomerReportingName() {
		return customerReportingName;
	}

	public void setCustomerReportingName(String customerReportingName) {
		this.customerReportingName = customerReportingName;
	}

	public String getCoveredServiceType() {
		return coveredServiceType;
	}

	public void setCoveredServiceType(String coveredServiceType) {
		this.coveredServiceType = coveredServiceType;
	}

	public String getServiceOverrideType() {
		return serviceOverrideType;
	}

	public void setServiceOverrideType(String serviceOverrideType) {
		this.serviceOverrideType = serviceOverrideType;
	}

	public String getServiceRequestStatus() {
		return serviceRequestStatus;
	}

	public void setServiceRequestStatus(String serviceRequestStatus) {
		this.serviceRequestStatus = serviceRequestStatus;
	}

	public String getMadcCostCenter() {
		return madcCostCenter;
	}

	public void setMadcCostCenter(String madcCostCenter) {
		this.madcCostCenter = madcCostCenter;
	}

	public Date getInstallDate() {
		return installDate;
	}

	public void setInstallDate(Date installDate) {
		this.installDate = installDate;
	}

	public Date getMadcInstallDate() {
		return madcInstallDate;
	}

	public void setMadcInstallDate(Date madcInstallDate) {
		this.madcInstallDate = madcInstallDate;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getMadcIPAddress() {
		return madcIPAddress;
	}

	public void setMadcIPAddress(String madcIPAddress) {
		this.madcIPAddress = madcIPAddress;
	}

	public String getProductTLI() {
		return productTLI;
	}

	public void setProductTLI(String productTLI) {
		this.productTLI = productTLI;
	}

	public String getMadcProductTLI() {
		return madcProductTLI;
	}

	public void setMadcProductTLI(String madcProductTLI) {
		this.madcProductTLI = madcProductTLI;
	}

	public String getWebOrderNumber() {
		return webOrderNumber;
	}

	public void setWebOrderNumber(String webOrderNumber) {
		this.webOrderNumber = webOrderNumber;
	}

	public String getSubStatus() {
		return subStatus;
	}

	public void setSubStatus(String subStatus) {
		this.subStatus = subStatus;
	}

	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectPhase() {
		return projectPhase;
	}

	public void setProjectPhase(String projectPhase) {
		this.projectPhase = projectPhase;
	}
	
}
