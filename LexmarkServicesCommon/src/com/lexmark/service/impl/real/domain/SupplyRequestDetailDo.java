package com.lexmark.service.impl.real.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

import com.amind.common.domain.BaseEntity;

/**
 * The mapping file: do-supplyrequestdetail-mapping.xml
 * 
 * @see do-supplyrequestdetailactivity-mapping.xml
 * @see do-supplyrequestdetailattachment-mapping.xml
 * @see do-supplyrequestdetailorderlineitem-mapping.xml
 * 
 * @see do-supplyrequestdetailshipment-mapping.xml
 * @see do-supplyrequestdetailshipmentitem-mapping.xml
 * @see do-supplyrequestdetailshipmentitemorderentry-mapping.xml
 * 
 * @see do-servicerequestdetail-mapping.xml
 */
public class SupplyRequestDetailDo extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String serviceRequestNumber;
    private Date serviceRequestDate;

    // Asset
    private String serialNumber;
    private String assetTag;
    private String deviceTag;
    private String ipAddress;
    private String problemDescription;
    private Date requestedEffectiveDate;
    //

    private String customerReferenceId;
    private Boolean requestExpediteOrder;
    private String status;
    private String area;
    private String subArea;

    // service address
    private String serviceAddress1;
    private String serviceAddress2;
    private String serviceAddress3;
    private String serviceCity;
    private String serviceState;
    private String servicePostalCode;
    private String serviceProvince;
    private String serviceCountry;
    private String serviceAddressName;
    private String serviceAddressId;
    private String storeFrontName;

    // requestor contact
    private String requestorContactFirstName;
    private String requestorContactLastName;
    private String requestorContactEmailAddress;
    private String requestorContactWorkPhone;

    // secondary contact
    private String secondaryContactFirstName;
    private String secondaryContactLastName;
    private String secondaryContactWorkPhone;
    private String secondaryContactEmailAddress;
    private String secondaryContactEmail;

    // primary contact
    private String primaryContactFirstName;
    private String primaryContactLastName;
    private String primaryContactEmailAddress;
    private String primaryContactWorkPhone;

    // don't need for RequestTypeService
    private String requestorContactDepartment;
    private String requestorContactEmail;
    private String secondaryContactDepartment;
    private String primaryContactDepartment;
    private String modelNumber;
    private String installAddress1;
    private String installAddress2;
    private String installAddress3;
    private String installCity;
    private String installCountry;
    private String installPostalCode;
    private String installProvince;
    private String installState;
    private String installAddressId;
    private String serviceRequestStatus;
    private String requestedService;
    private String referenceNumber;
    private String optionExchangeOtherDescription;
    private String primaryContactId;
    private String requestorContactId;
    private String secondaryContactId;
    private String entitlementDescription;
    private String entitlementId;
    private Date statusDate;
    private String assetId;
    private String productLine;
    private String requestedServiceOverride;
    private String installAddressName;
    private String accountId;
    private String latestAssetId;
    private String productTLI;

    private String serviceRequestETA;
    private String serviceRequestSLA;
    private String resolutionCode; // added by nelson for Ci5
    private String additionalDetails;
    private String customerPONumber;
    private String attachmentNotes;
    private String portalSpecialInstruction;
    private String defaultSpecialInstruction;
    private String location;
    private String installPhysicalLocation1;
    private String installPhysicalLocation2;
    private String installPhysicalLocation3;
    private Date installDate;
    private String hostName;
    private String serviceLocation;
    private String eta;
	private String accoutName;
	private String accountCountry;
	private String orderSource;
    private ArrayList<SupplyRequestDetailActivityDo> actions = new ArrayList<SupplyRequestDetailActivityDo>();
    private ArrayList<SupplyRequestDetailAttachmentDo> attachments = new ArrayList<SupplyRequestDetailAttachmentDo>();
    private ArrayList<SupplyRequestDetailOrderItemDo> orderItems = new ArrayList<SupplyRequestDetailOrderItemDo>();
    private ArrayList<SupplyRequestDetailPageCountDo> pageCounts = new ArrayList<SupplyRequestDetailPageCountDo>();
    private ArrayList<SupplyRequestDetailCustomerOrderItemDo> customerOrderLineItems = new ArrayList<SupplyRequestDetailCustomerOrderItemDo>();
    private BigDecimal itemSubTotalBeforeTax;
	private ArrayList<SupplyRequestDetailPaymentDo> paymentDetails = new ArrayList<SupplyRequestDetailPaymentDo>();
	
	private String county;
	private String serviceCounty;
	private String district;
	private String officeNumber;
    private String billingModel;
    private String paymentMethod;
    private String soldToNumber;
    
    private String billToAddressName;
    private String billToStreetAddress;
    private String billToStreetAddress2; 
    private String billToRegion;
    private String billToCountryCode;
    private String billToHouseNo;
    
	private String countryIsoCode;
	private String region;
	private String assetCostCenter;
	private String chlNodeValue;
	private String chlNodeId;
	private ArrayList<ServiceRequestDetailContactDo> deviceContacts;
	
	private String customerReportingName;
	
	private String assetMoveToAddressGrouped;
	private String assetMoveToAddressGroupedMADC;
	
	private String madcAssetCostCenter;
	private String madcProductTLI;
	private String madcSerialNumber;
	
	private String assetBasedSRHostName;
	private String assetBasedIPAddress;
	private Date assetBasedInstallDate;
	
	private String helpdeskReferenceNumber;
	private String typeIdentifier;
	
	//Move From Address fields for MPS CR 11055
	private String moveFromMadcAddressID;
	private String moveFromMadcAddressLine1;
	
	private String moveFromMadcAddressLine2;
	private String moveFromMadcCity;
	private String moveFromMadcCountry;
	private String moveFromMadcISOCountryCode;
	private String moveFromMadcCounty;
	private String moveFromMadcDistrict;
	private String moveFromMadcState;
	private String moveFromMadcStateFullName;
	private String moveFromMadcHouseNum;
	private String moveFromMadcFirstLogicErrorMessage;
	private String moveFromMadcLatitude;
	private String moveFromMadcLongitude;
	private String moveFromMadcRegion;
	private String moveFromMadcPostalCode;
	private String moveFromMadcProvince;
	private String moveFromMadcBuilding;
	private String moveFromMadcFloor;
	private String moveFromMadcOffice;
	private String serviceOverrideType;
	private String fccc;
	//OPS
	private String agreementName;
	private String agreementNumber;
	private String customerRequestDateTime;
	private String committedDateTime;
	private String requestStatus;
	private String subStatus;
	private String severity;
	private String projectName;
	private String projectPhase;
	
	private String addressName;
	private String madcAddressName;
	private String LBSIdentifierFlag;
	private String coordinatesX;
	private String coordinatesY;
	private String lbsDeviceinfo;
	private String lbsLexmarkInstall;
	
	public String getLbsDeviceinfo() {
		return lbsDeviceinfo;
	}

	public void setLbsDeviceinfo(String lbsDeviceinfo) {
		this.lbsDeviceinfo = lbsDeviceinfo;
	}

	public String getLbsLexmarkInstall() {
		return lbsLexmarkInstall;
	}

	public void setLbsLexmarkInstall(String lbsLexmarkInstall) {
		this.lbsLexmarkInstall = lbsLexmarkInstall;
	}

	public String getMadcAddressName() {
		return madcAddressName;
	}

	public void setMadcAddressName(String madcAddressName) {
		this.madcAddressName = madcAddressName;
	}
	
    public String getFccc() {
		return fccc;
	}

	public void setFccc(String fccc) {
		this.fccc = fccc;
	}

	public String getServiceOverrideType() {
		return serviceOverrideType;
	}

	public void setServiceOverrideType(String serviceOverrideType) {
		this.serviceOverrideType = serviceOverrideType;
	}

	public String getServiceRequestETA() {
        return serviceRequestETA;
    }

    public void setServiceRequestETA(String serviceRequestETA) {
        this.serviceRequestETA = serviceRequestETA;
    }

    public String getServiceRequestSLA() {
        return serviceRequestSLA;
    }

    public void setServiceRequestSLA(String serviceRequestSLA) {
        this.serviceRequestSLA = serviceRequestSLA;
    }
    public String getEta() {
        return eta;
    }

    public void setEta(String eta) {
        this.eta = eta;
    }

    public String getProductTLI() {
        return productTLI;
    }

    public void setProductTLI(String productTLI) {
        this.productTLI = productTLI;
    }

    public String getLatestAssetId() {
        return latestAssetId;
    }

    public void setLatestAssetId(String latestAssetId) {
        this.latestAssetId = latestAssetId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getServiceAddressName() {
        return serviceAddressName;
    }

    public void setServiceAddressName(String serviceAddressName) {
        this.serviceAddressName = serviceAddressName;
    }

    public String getInstallAddressName() {
        return installAddressName;
    }

    public void setInstallAddressName(String installAddressName) {
        this.installAddressName = installAddressName;
    }

    public String getRequestedServiceOverride() {
        return requestedServiceOverride;
    }

    public void setRequestedServiceOverride(String requestedServiceOverride) {
        this.requestedServiceOverride = requestedServiceOverride;
    }

    public String getProductLine() {
        return productLine;
    }

    public void setProductLine(String productLine) {
        this.productLine = productLine;
    }

    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getStatusDate() {
        return statusDate;
    }

    public void setStatusDate(Date statusDate) {
        this.statusDate = statusDate;
    }

    public String getEntitlementDescription() {
        return entitlementDescription;
    }

    public void setEntitlementDescription(String entitlementDescription) {
        this.entitlementDescription = entitlementDescription;
    }

    public String getEntitlementId() {
        return entitlementId;
    }

    public void setEntitlementId(String entitlementId) {
        this.entitlementId = entitlementId;
    }

    public String getPrimaryContactDepartment() {
        return primaryContactDepartment;
    }

    public void setPrimaryContactDepartment(String primaryContactDepartment) {
        this.primaryContactDepartment = primaryContactDepartment;
    }

    public String getPrimaryContactEmailAddress() {
        return primaryContactEmailAddress;
    }

    public void setPrimaryContactEmailAddress(String primaryContactEmailAddress) {
        this.primaryContactEmailAddress = primaryContactEmailAddress;
    }

    public String getRequestorContactDepartment() {
        return requestorContactDepartment;
    }

    public void setRequestorContactDepartment(String requestorContactDepartment) {
        this.requestorContactDepartment = requestorContactDepartment;
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

    public String getRequestorContactWorkPhone() {
        return requestorContactWorkPhone;
    }

    public void setRequestorContactWorkPhone(String requestorContactWorkPhone) {
        this.requestorContactWorkPhone = requestorContactWorkPhone;
    }

    public String getSecondaryContactDepartment() {
        return secondaryContactDepartment;
    }

    public void setSecondaryContactDepartment(String secondaryContactDepartment) {
        this.secondaryContactDepartment = secondaryContactDepartment;
    }

    public String getSecondaryContactFirstName() {
        return secondaryContactFirstName;
    }

    public void setSecondaryContactFirstName(String secondaryContactFirstName) {
        this.secondaryContactFirstName = secondaryContactFirstName;
    }

    public String getSecondaryContactLastName() {
        return secondaryContactLastName;
    }

    public void setSecondaryContactLastName(String secondaryContactLastName) {
        this.secondaryContactLastName = secondaryContactLastName;
    }

    public String getSecondaryContactWorkPhone() {
        return secondaryContactWorkPhone;
    }

    public void setSecondaryContactWorkPhone(String secondaryContactWorkPhone) {
        this.secondaryContactWorkPhone = secondaryContactWorkPhone;
    }

    public String getPrimaryContactWorkPhone() {
        return primaryContactWorkPhone;
    }

    public void setPrimaryContactWorkPhone(String primaryContactWorkPhone) {
        this.primaryContactWorkPhone = primaryContactWorkPhone;
    }

    public String getServiceAddress1() {
        return serviceAddress1;
    }

    public void setServiceAddress1(String serviceAddress1) {
        this.serviceAddress1 = serviceAddress1;
    }

    public String getServiceAddress2() {
        return serviceAddress2;
    }

    public void setServiceAddress2(String serviceAddress2) {
        this.serviceAddress2 = serviceAddress2;
    }

    public String getServiceAddress3() {
        return serviceAddress3;
    }

    public void setServiceAddress3(String serviceAddress3) {
        this.serviceAddress3 = serviceAddress3;
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

    public String getServiceCountry() {
        return serviceCountry;
    }

    public void setServiceCountry(String serviceCountry) {
        this.serviceCountry = serviceCountry;
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

    public String getAssetTag() {
        return assetTag;
    }

    public void setAssetTag(String assetTag) {
        this.assetTag = assetTag;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getModelNumber() {
        return modelNumber;
    }

    public void setModelNumber(String modelNumber) {
        this.modelNumber = modelNumber;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getInstallAddress1() {
        return installAddress1;
    }

    public void setInstallAddress1(String installAddress1) {
        this.installAddress1 = installAddress1;
    }

    public String getInstallAddress2() {
        return installAddress2;
    }

    public void setInstallAddress2(String installAddress2) {
        this.installAddress2 = installAddress2;
    }

    public String getInstallAddress3() {
        return installAddress3;
    }

    public void setInstallAddress3(String installAddress3) {
        this.installAddress3 = installAddress3;
    }

    public String getInstallCity() {
        return installCity;
    }

    public void setInstallCity(String installCity) {
        this.installCity = installCity;
    }

    public String getInstallCountry() {
        return installCountry;
    }

    public void setInstallCountry(String installCountry) {
        this.installCountry = installCountry;
    }

    public String getInstallPostalCode() {
        return installPostalCode;
    }

    public void setInstallPostalCode(String installPostalCode) {
        this.installPostalCode = installPostalCode;
    }

    public String getInstallProvince() {
        return installProvince;
    }

    public void setInstallProvince(String installProvince) {
        this.installProvince = installProvince;
    }

    public String getInstallState() {
        return installState;
    }

    public void setInstallState(String installState) {
        this.installState = installState;
    }

    public String getPrimaryContactId() {
        return primaryContactId;
    }

    public void setPrimaryContactId(String primaryContactId) {
        this.primaryContactId = primaryContactId;
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

    public String getRequestorContactId() {
        return requestorContactId;
    }

    public void setRequestorContactId(String requestorContactId) {
        this.requestorContactId = requestorContactId;
    }

    public String getSecondaryContactId() {
        return secondaryContactId;
    }

    public void setSecondaryContactId(String secondaryContactId) {
        this.secondaryContactId = secondaryContactId;
    }

    public ArrayList<SupplyRequestDetailActivityDo> getActions() {
        return actions;
    }

    public void setActions(ArrayList<SupplyRequestDetailActivityDo> actions) {
        this.actions = actions;
    }

    public String getServiceRequestNumber() {
        return serviceRequestNumber;
    }

    public void setServiceRequestNumber(String serviceRequestNumber) {
        this.serviceRequestNumber = serviceRequestNumber;
    }

    public Date getServiceRequestDate() {
        return serviceRequestDate;
    }

    public void setServiceRequestDate(Date serviceRequestDate) {
        this.serviceRequestDate = serviceRequestDate;
    }

    public String getServiceRequestStatus() {
        return serviceRequestStatus;
    }

    public void setServiceRequestStatus(String serviceRequestStatus) {
        this.serviceRequestStatus = serviceRequestStatus;
    }

    public String getRequestedService() {
        return requestedService;
    }

    public void setRequestedService(String requestedService) {
        this.requestedService = requestedService;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public String getOptionExchangeOtherDescription() {
        return optionExchangeOtherDescription;
    }

    public void setOptionExchangeOtherDescription(String optionExchangeOtherDescription) {
        this.optionExchangeOtherDescription = optionExchangeOtherDescription;
    }

    public String getCustomerReferenceId() {
        return customerReferenceId;
    }

    public void setCustomerReferenceId(String customerReferenceId) {
        this.customerReferenceId = customerReferenceId;
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

    public String getRequestorContactEmailAddress() {
        return requestorContactEmailAddress;
    }

    public void setRequestorContactEmailAddress(String requestorContactEmailAddress) {
        this.requestorContactEmailAddress = requestorContactEmailAddress;
    }

    public String getSecondaryContactEmailAddress() {
        return secondaryContactEmailAddress;
    }

    public void setSecondaryContactEmailAddress(String secondaryContactEmailAddress) {
        this.secondaryContactEmailAddress = secondaryContactEmailAddress;
    }

    public Boolean isRequestExpediteOrder() {
        return requestExpediteOrder;
    }

    public void setRequestExpediteOrder(Boolean requestExpediteOrder) {
        this.requestExpediteOrder = requestExpediteOrder;
    }

    public String getSecondaryContactEmail() {
        return secondaryContactEmail;
    }

    public void setSecondaryContactEmail(String secondaryContactEmail) {
        this.secondaryContactEmail = secondaryContactEmail;
    }

    public String getRequestorContactEmail() {
        return requestorContactEmail;
    }

    public void setRequestorContactEmail(String requestorContactEmail) {
        this.requestorContactEmail = requestorContactEmail;
    }

    public String getResolutionCode() {
        return resolutionCode;
    }

    public void setResolutionCode(String resolutionCode) {
        this.resolutionCode = resolutionCode;
    }

    public Date getRequestedEffectiveDate() {
        return requestedEffectiveDate;
    }

    public void setRequestedEffectiveDate(Date requestedEffectiveDate) {
        this.requestedEffectiveDate = requestedEffectiveDate;
    }

    public ArrayList<SupplyRequestDetailAttachmentDo> getAttachments() {
        return attachments;
    }

    public void setAttachments(ArrayList<SupplyRequestDetailAttachmentDo> attachments) {
        this.attachments = attachments;
    }

    public void setAdditionalDetails(String additionalDetails) {
        this.additionalDetails = additionalDetails;
    }

    public String getAdditionalDetails() {
        return additionalDetails;
    }

	public ArrayList<SupplyRequestDetailOrderItemDo> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(ArrayList<SupplyRequestDetailOrderItemDo> orderItems) {
		this.orderItems = orderItems;
	}

	public void setCustomerPONumber(String customerPONumber) {
		this.customerPONumber = customerPONumber;
	}

	public String getCustomerPONumber() {
		return customerPONumber;
	}

	public void setAttachmentNotes(String attachmentNotes) {
		this.attachmentNotes = attachmentNotes;
	}

	public String getAttachmentNotes() {
		return attachmentNotes;
	}

	public void setPortalSpecialInstruction(String portalSpecialInstruction) {
		this.portalSpecialInstruction = portalSpecialInstruction;
	}

	public String getPortalSpecialInstruction() {
		return portalSpecialInstruction;
	}

	public ArrayList<SupplyRequestDetailPageCountDo> getPageCounts() {
		return pageCounts;
	}

	public void setPageCounts(ArrayList<SupplyRequestDetailPageCountDo> pageCounts) {
		this.pageCounts = pageCounts;
	}

	public String getDefaultSpecialInstruction() {
		return defaultSpecialInstruction;
	}

	public void setDefaultSpecialInstruction(String defaultSpecialInstruction) {
		this.defaultSpecialInstruction = defaultSpecialInstruction;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getInstallPhysicalLocation1() {
		return installPhysicalLocation1;
	}

	public void setInstallPhysicalLocation1(String installPhysicalLocation1) {
		this.installPhysicalLocation1 = installPhysicalLocation1;
	}

	public String getInstallPhysicalLocation2() {
		return installPhysicalLocation2;
	}

	public void setInstallPhysicalLocation2(String installPhysicalLocation2) {
		this.installPhysicalLocation2 = installPhysicalLocation2;
	}

	public String getInstallPhysicalLocation3() {
		return installPhysicalLocation3;
	}

	public void setInstallPhysicalLocation3(String installPhysicalLocation3) {
		this.installPhysicalLocation3 = installPhysicalLocation3;
	}

	public Date getInstallDate() {
		return installDate;
	}

	public void setInstallDate(Date installDate) {
		this.installDate = installDate;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public ArrayList<SupplyRequestDetailCustomerOrderItemDo> getCustomerOrderLineItems() {
		return customerOrderLineItems;
	}

	public void setCustomerOrderLineItems(
			ArrayList<SupplyRequestDetailCustomerOrderItemDo> customerOrderLineItems) {
		this.customerOrderLineItems = customerOrderLineItems;
	}

	public void setServiceLocation(String serviceLocation) {
		this.serviceLocation = serviceLocation;
	}

	public String getServiceLocation() {
		return serviceLocation;
	}

	public void setServiceAddressId(String serviceAddressId) {
		this.serviceAddressId = serviceAddressId;
	}

	public String getServiceAddressId() {
		return serviceAddressId;
	}

	public String getStoreFrontName() {
        return storeFrontName;
    }

    public void setStoreFrontName(String storeFrontName) {
        this.storeFrontName = storeFrontName;
    }

    public void setAccoutName(String accoutName) {
		this.accoutName = accoutName;
	}

	public String getAccoutName() {
		return accoutName;
	}

	public void setAccountCountry(String accountCountry) {
		this.accountCountry = accountCountry;
	}

	public String getAccountCountry() {
		return accountCountry;
	}

	public void setOrderSource(String orderSource) {
		this.orderSource = orderSource;
	}

	public String getOrderSource() {
		return orderSource;
	}

	public BigDecimal getItemSubTotalBeforeTax() {
		return itemSubTotalBeforeTax;
	}

	public void setItemSubTotalBeforeTax(BigDecimal itemSubTotalBeforeTax) {
		this.itemSubTotalBeforeTax = itemSubTotalBeforeTax;
	}
	public String getServiceCounty() {
		return serviceCounty;
	}
	public void setServiceCounty(String serviceCounty) {
		this.serviceCounty = serviceCounty;
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

	public String getBillingModel() {
		return billingModel;
	}

	public void setBillingModel(String billingModel) {
		this.billingModel = billingModel;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getSoldToNumber() {
		return soldToNumber;
	}

	public void setSoldToNumber(String soldToNumber) {
		this.soldToNumber = soldToNumber;
	}

	public ArrayList<SupplyRequestDetailPaymentDo> getPaymentDetails() {
		return paymentDetails;
	}

	public void setPaymentDetails(
			ArrayList<SupplyRequestDetailPaymentDo> paymentDetails) {
		this.paymentDetails = paymentDetails;
	}

	public String getBillToAddressName() {
		return billToAddressName;
	}

	public void setBillToAddressName(String billToAddressName) {
		this.billToAddressName = billToAddressName;
	}

	public String getBillToStreetAddress() {
		return billToStreetAddress;
	}

	public void setBillToStreetAddress(String billToStreetAddress) {
		this.billToStreetAddress = billToStreetAddress;
	}

	public String getBillToStreetAddress2() {
		return billToStreetAddress2;
	}

	public void setBillToStreetAddress2(String billToStreetAddress2) {
		this.billToStreetAddress2 = billToStreetAddress2;
	}

	public String getBillToRegion() {
		return billToRegion;
	}

	public void setBillToRegion(String billToRegion) {
		this.billToRegion = billToRegion;
	}

	public String getBillToCountryCode() {
		return billToCountryCode;
	}

	public void setBillToCountryCode(String billToCountryCode) {
		this.billToCountryCode = billToCountryCode;
	}

	public String getBillToHouseNo() {
		return billToHouseNo;
	}

	public void setBillToHouseNo(String billToHouseNo) {
		this.billToHouseNo = billToHouseNo;
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

	public String getAssetCostCenter() {
		return assetCostCenter;
	}

	public void setAssetCostCenter(String assetCostCenter) {
		this.assetCostCenter = assetCostCenter;
	}

	public String getChlNodeValue() {
		return chlNodeValue;
	}

	public void setChlNodeValue(String chlNodeValue) {
		this.chlNodeValue = chlNodeValue;
	}

	public ArrayList<ServiceRequestDetailContactDo> getDeviceContacts() {
		return deviceContacts;
	}

	public void setDeviceContacts(
			ArrayList<ServiceRequestDetailContactDo> deviceContacts) {
		this.deviceContacts = deviceContacts;
	}

	public String getInstallAddressId() {
		return installAddressId;
	}

	public void setInstallAddressId(String installAddressId) {
		this.installAddressId = installAddressId;
	}

	public String getCustomerReportingName() {
		return customerReportingName;
	}

	public void setCustomerReportingName(String customerReportingName) {
		this.customerReportingName = customerReportingName;
	}

	public String getDeviceTag() {
		return deviceTag;
	}

	public void setDeviceTag(String deviceTag) {
		this.deviceTag = deviceTag;
	}

	public String getAssetMoveToAddressGrouped() {
		return assetMoveToAddressGrouped;
	}

	public void setAssetMoveToAddressGrouped(String assetMoveToAddressGrouped) {
		this.assetMoveToAddressGrouped = assetMoveToAddressGrouped;
	}

	public String getAssetMoveToAddressGroupedMADC() {
		return assetMoveToAddressGroupedMADC;
	}

	public void setAssetMoveToAddressGroupedMADC(
			String assetMoveToAddressGroupedMADC) {
		this.assetMoveToAddressGroupedMADC = assetMoveToAddressGroupedMADC;
	}

	public String getMadcAssetCostCenter() {
		return madcAssetCostCenter;
	}

	public void setMadcAssetCostCenter(String madcAssetCostCenter) {
		this.madcAssetCostCenter = madcAssetCostCenter;
	}

	public String getMadcProductTLI() {
		return madcProductTLI;
	}

	public void setMadcProductTLI(String madcProductTLI) {
		this.madcProductTLI = madcProductTLI;
	}

	public String getMadcSerialNumber() {
		return madcSerialNumber;
	}

	public void setMadcSerialNumber(String madcSerialNumber) {
		this.madcSerialNumber = madcSerialNumber;
	}

	public String getAssetBasedSRHostName() {
		return assetBasedSRHostName;
	}

	public void setAssetBasedSRHostName(String assetBasedSRHostName) {
		this.assetBasedSRHostName = assetBasedSRHostName;
	}

	public String getAssetBasedIPAddress() {
		return assetBasedIPAddress;
	}

	public void setAssetBasedIPAddress(String assetBasedIPAddress) {
		this.assetBasedIPAddress = assetBasedIPAddress;
	}

	public Date getAssetBasedInstallDate() {
		return assetBasedInstallDate;
	}

	public void setAssetBasedInstallDate(Date assetBasedInstallDate) {
		this.assetBasedInstallDate = assetBasedInstallDate;
	}

	public String getChlNodeId() {
		return chlNodeId;
	}

	public void setChlNodeId(String chlNodeId) {
		this.chlNodeId = chlNodeId;
	}

	public String getHelpdeskReferenceNumber() {
		return helpdeskReferenceNumber;
	}

	public void setHelpdeskReferenceNumber(String helpdeskReferenceNumber) {
		this.helpdeskReferenceNumber = helpdeskReferenceNumber;
	}

	public String getTypeIdentifier() {
		return typeIdentifier;
	}

	public void setTypeIdentifier(String typeIdentifier) {
		this.typeIdentifier = typeIdentifier;
	}
	public String getMoveFromMadcAddressID() {
		return moveFromMadcAddressID;
	}

	public void setMoveFromMadcAddressID(String moveFromMadcAddressID) {
		this.moveFromMadcAddressID = moveFromMadcAddressID;
	}

	public String getMoveFromMadcAddressLine1() {
		return moveFromMadcAddressLine1;
	}

	public void setMoveFromMadcAddressLine1(String moveFromMadcAddressLine1) {
		this.moveFromMadcAddressLine1 = moveFromMadcAddressLine1;
	}

	public String getMoveFromMadcAddressLine2() {
		return moveFromMadcAddressLine2;
	}

	public void setMoveFromMadcAddressLine2(String moveFromMadcAddressLine2) {
		this.moveFromMadcAddressLine2 = moveFromMadcAddressLine2;
	}

	public String getMoveFromMadcCity() {
		return moveFromMadcCity;
	}

	public void setMoveFromMadcCity(String moveFromMadcCity) {
		this.moveFromMadcCity = moveFromMadcCity;
	}

	public String getMoveFromMadcCountry() {
		return moveFromMadcCountry;
	}

	public void setMoveFromMadcCountry(String moveFromMadcCountry) {
		this.moveFromMadcCountry = moveFromMadcCountry;
	}

	public String getMoveFromMadcISOCountryCode() {
		return moveFromMadcISOCountryCode;
	}

	public void setMoveFromMadcISOCountryCode(String moveFromMadcISOCountryCode) {
		this.moveFromMadcISOCountryCode = moveFromMadcISOCountryCode;
	}

	public String getMoveFromMadcCounty() {
		return moveFromMadcCounty;
	}

	public void setMoveFromMadcCounty(String moveFromMadcCounty) {
		this.moveFromMadcCounty = moveFromMadcCounty;
	}

	public String getMoveFromMadcDistrict() {
		return moveFromMadcDistrict;
	}

	public void setMoveFromMadcDistrict(String moveFromMadcDistrict) {
		this.moveFromMadcDistrict = moveFromMadcDistrict;
	}

	public String getMoveFromMadcState() {
		return moveFromMadcState;
	}

	public void setMoveFromMadcState(String moveFromMadcState) {
		this.moveFromMadcState = moveFromMadcState;
	}

	public String getMoveFromMadcStateFullName() {
		return moveFromMadcStateFullName;
	}

	public void setMoveFromMadcStateFullName(String moveFromMadcStateFullName) {
		this.moveFromMadcStateFullName = moveFromMadcStateFullName;
	}

	public String getMoveFromMadcHouseNum() {
		return moveFromMadcHouseNum;
	}

	public void setMoveFromMadcHouseNum(String moveFromMadcHouseNum) {
		this.moveFromMadcHouseNum = moveFromMadcHouseNum;
	}

	public String getMoveFromMadcFirstLogicErrorMessage() {
		return moveFromMadcFirstLogicErrorMessage;
	}

	public void setMoveFromMadcFirstLogicErrorMessage(
			String moveFromMadcFirstLogicErrorMessage) {
		this.moveFromMadcFirstLogicErrorMessage = moveFromMadcFirstLogicErrorMessage;
	}

	public String getMoveFromMadcLatitude() {
		return moveFromMadcLatitude;
	}

	public void setMoveFromMadcLatitude(String moveFromMadcLatitude) {
		this.moveFromMadcLatitude = moveFromMadcLatitude;
	}

	public String getMoveFromMadcLongitude() {
		return moveFromMadcLongitude;
	}

	public void setMoveFromMadcLongitude(String moveFromMadcLongitude) {
		this.moveFromMadcLongitude = moveFromMadcLongitude;
	}

	public String getMoveFromMadcRegion() {
		return moveFromMadcRegion;
	}

	public void setMoveFromMadcRegion(String moveFromMadcRegion) {
		this.moveFromMadcRegion = moveFromMadcRegion;
	}

	public String getMoveFromMadcPostalCode() {
		return moveFromMadcPostalCode;
	}

	public void setMoveFromMadcPostalCode(String moveFromMadcPostalCode) {
		this.moveFromMadcPostalCode = moveFromMadcPostalCode;
	}

	public String getMoveFromMadcProvince() {
		return moveFromMadcProvince;
	}

	public void setMoveFromMadcProvince(String moveFromMadcProvince) {
		this.moveFromMadcProvince = moveFromMadcProvince;
	}

	public String getMoveFromMadcBuilding() {
		return moveFromMadcBuilding;
	}

	public void setMoveFromMadcBuilding(String moveFromMadcBuilding) {
		this.moveFromMadcBuilding = moveFromMadcBuilding;
	}

	public String getMoveFromMadcFloor() {
		return moveFromMadcFloor;
	}

	public void setMoveFromMadcFloor(String moveFromMadcFloor) {
		this.moveFromMadcFloor = moveFromMadcFloor;
	}

	public String getMoveFromMadcOffice() {
		return moveFromMadcOffice;
	}

	public void setMoveFromMadcOffice(String moveFromMadcOffice) {
		this.moveFromMadcOffice = moveFromMadcOffice;
	}

	public Boolean getRequestExpediteOrder() {
		return requestExpediteOrder;
	}

	public String getAgreementName() {
		return agreementName;
	}

	public void setAgreementName(String agreementName) {
		this.agreementName = agreementName;
	}

	public String getAgreementNumber() {
		return agreementNumber;
	}

	public void setAgreementNumber(String agreementNumber) {
		this.agreementNumber = agreementNumber;
	}

	public String getCustomerRequestDateTime() {
		return customerRequestDateTime;
	}

	public void setCustomerRequestDateTime(String customerRequestDateTime) {
		this.customerRequestDateTime = customerRequestDateTime;
	}

	public String getCommittedDateTime() {
		return committedDateTime;
	}

	public void setCommittedDateTime(String committedDateTime) {
		this.committedDateTime = committedDateTime;
	}

	public String getRequestStatus() {
		return requestStatus;
	}

	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
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

	public String getAddressName() {
		return addressName;
	}

	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}

	public String getLBSIdentifierFlag() {
		return LBSIdentifierFlag;
	}

	public void setLBSIdentifierFlag(String lBSIdentifierFlag) {
		LBSIdentifierFlag = lBSIdentifierFlag;
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

	public String getCoordinatesX() {
		return coordinatesX;
	}

	public void setCoordinatesX(String coordinatesX) {
		this.coordinatesX = coordinatesX;
	}

	public String getCoordinatesY() {
		return coordinatesY;
	}

	public void setCoordinatesY(String coordinatesY) {
		this.coordinatesY = coordinatesY;
	}

}
