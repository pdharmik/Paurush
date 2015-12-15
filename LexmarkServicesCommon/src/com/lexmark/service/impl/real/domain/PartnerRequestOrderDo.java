package com.lexmark.service.impl.real.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import com.amind.common.domain.BaseEntity;

/**
 * The mapping file: do-partnerrequestorder-mapping.xml
 * 
 * 
 * @author vpetruchok
 * @version 1.0, 2012-06-22
 */
public class PartnerRequestOrderDo extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String BO = "LXK MPS Order Entry - Order";
    public static final String BC = "LXK MPS Order Entry - Order";

    private String mdmLevel1AccountId;
    private String mdmLevel2AccountId;
    private String mdmLevel3AccountId;
    private String mdmLevel4AccountId;
    private String mdmLevel5AccountId;
    private String mdmLevel;
    private String status;
    private String mpsStatus;
    private String contactMethod;
    private String orderNumber;
    private Date createdDate;
    private String requestNumber;
    private String customerAccount;

    // AccountContact CustomerContact
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String emailAddress;

    private String responseMetric;
    private Date customerRequestedResponseDate;
    private String serviceProviderReferenceNumber;

    private String serviceRequestNumber;

    // Asset asset
    private String serialNumber;
    private String machineTypeModel;
    private String productLine;

    // GenericAddress CustomerAddress
    private String addressId;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String city;
    private String state;
    private String postalCode;
    private String province;

    // PartLineItem.PartList
    private String partNumber;
    private String description;
    private String partType;
    private String orderedQuantity;
    private String readyForBilling;
    private String mpsRequestFlag;
    private ArrayList<OrderLineItemDo> orderLineItems;
    
	private String county;
	private String district;
	private String officeNumber;

    public String getMdmLevel1AccountId() {
        return mdmLevel1AccountId;
    }

    public void setMdmLevel1AccountId(String mdmLevel1AccountId) {
        this.mdmLevel1AccountId = mdmLevel1AccountId;
    }

    public String getMdmLevel2AccountId() {
        return mdmLevel2AccountId;
    }

    public void setMdmLevel2AccountId(String mdmLevel2AccountId) {
        this.mdmLevel2AccountId = mdmLevel2AccountId;
    }

    public String getMdmLevel3AccountId() {
        return mdmLevel3AccountId;
    }

    public void setMdmLevel3AccountId(String mdmLevel3AccountId) {
        this.mdmLevel3AccountId = mdmLevel3AccountId;
    }

    public String getMdmLevel4AccountId() {
        return mdmLevel4AccountId;
    }

    public void setMdmLevel4AccountId(String mdmLevel4AccountId) {
        this.mdmLevel4AccountId = mdmLevel4AccountId;
    }

    public String getMdmLevel5AccountId() {
        return mdmLevel5AccountId;
    }

    public void setMdmLevel5AccountId(String mdmLevel5AccountId) {
        this.mdmLevel5AccountId = mdmLevel5AccountId;
    }

    public String getMdmLevel() {
        return mdmLevel;
    }

    public void setMdmLevel(String mdmLevel) {
        this.mdmLevel = mdmLevel;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMpsStatus() {
        return mpsStatus;
    }

    public void setMpsStatus(String mpsStatus) {
        this.mpsStatus = mpsStatus;
    }

    public String getContactMethod() {
        return contactMethod;
    }

    public void setContactMethod(String contactMethod) {
        this.contactMethod = contactMethod;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }
    
    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getRequestNumber() {
        return requestNumber;
    }

    public void setRequestNumber(String requestNumber) {
        this.requestNumber = requestNumber;
    }

    public String getCustomerAccount() {
        return customerAccount;
    }

    public void setCustomerAccount(String customerAccount) {
        this.customerAccount = customerAccount;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getResponseMetric() {
        return responseMetric;
    }

    public void setResponseMetric(String responseMetric) {
        this.responseMetric = responseMetric;
    }

    public Date getCustomerRequestedResponseDate() {
        return customerRequestedResponseDate;
    }

    public void setCustomerRequestedResponseDate(Date customerRequestedResponseDate) {
        this.customerRequestedResponseDate = customerRequestedResponseDate;
    }

    public String getServiceProviderReferenceNumber() {
        return serviceProviderReferenceNumber;
    }

    public void setServiceProviderReferenceNumber(String serviceProviderReferenceNumber) {
        this.serviceProviderReferenceNumber = serviceProviderReferenceNumber;
    }

    public String getServiceRequestNumber() {
        return serviceRequestNumber;
    }

    public void setServiceRequestNumber(String serviceRequestNumber) {
        this.serviceRequestNumber = serviceRequestNumber;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getMachineTypeModel() {
        return machineTypeModel;
    }

    public void setMachineTypeModel(String machineTypeModel) {
        this.machineTypeModel = machineTypeModel;
    }

    public String getProductLine() {
        return productLine;
    }

    public void setProductLine(String productLine) {
        this.productLine = productLine;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getAddressLine3() {
		return addressLine3;
	}

	public void setAddressLine3(String addressLine3) {
		this.addressLine3 = addressLine3;
	}

	public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPartType() {
        return partType;
    }

    public void setPartType(String partType) {
        this.partType = partType;
    }

    public String getOrderedQuantity() {
        return orderedQuantity;
    }

    public void setOrderedQuantity(String orderedQuantity) {
        this.orderedQuantity = orderedQuantity;
    }

    
    public ArrayList<OrderLineItemDo> getOrderLineItems() {
        return orderLineItems;
    }
    

    public void setOrderLineItems(ArrayList<OrderLineItemDo> orderLineItems) {
        this.orderLineItems = orderLineItems;
    }

	public void setReadyForBilling(String readyForBilling) {
		this.readyForBilling = readyForBilling;
	}

	public String getReadyForBilling() {
		return readyForBilling;
	}

	public void setMpsRequestFlag(String mpsRequestFlag) {
		this.mpsRequestFlag = mpsRequestFlag;
	}

	public String getMpsRequestFlag() {
		return mpsRequestFlag;
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
	
}
