package com.lexmark.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author vpetruchok
 * @version 1.0, 2012-07-17
 */
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    private String vendorId;
    private String status;
    private String contactMethod;
    private String orderNumber;
    private Date requestedDeliveryDate;
    private String requestNumber;
    private String customerAccount;
    private Date orderDate;
    private AccountContact primaryContact;
    private AccountContact secondaryContact;
    private String responseMetric;
    private Date customerRequestedResponseDate;
    private String serviceProviderReferenceNumber;
    private String specialInstructions;
    private String mustResolveBy;
    private String specialHandling;
	private String serviceRequestNumber;
    private GenericAddress shippingAddress;
    private AccountContact customerContact;
    private Asset asset;
    private GenericAddress customerAddress;
    private List<ServiceRequestOrderLineItem> pendingShipments; // orderDetail 
    private List<ServiceRequestOrderLineItem> processedParts; // orderDetail
    private String id;
	private List<ServiceRequestActivity> emailActivities;
	private Date createdDate;
	private String fulfillmentStatus;
	private String attentionTo;
	private String assetIdentifier;
	private String insideLocation;
	private String specialHandlingInstructions;
	private String requestType;
	private String orderType;
	//OPS
	private String subStatus;
	private String severity;
	private List<ServiceRequestOrderLineItem> orderLineItems;
	
    
	public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public Date getRequestedDeliveryDate() {
        return requestedDeliveryDate;
    }

    public void setRequestedDeliveryDate(Date requestedDeliveryDate) {
        this.requestedDeliveryDate = requestedDeliveryDate;
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

    public List<ServiceRequestOrderLineItem> getPendingShipments() {
        return pendingShipments;
    }

    public void setPendingShipments(List<ServiceRequestOrderLineItem> pendingShipments) {
        this.pendingShipments = pendingShipments;
    }

    public List<ServiceRequestOrderLineItem> getProcessedParts() {
        return processedParts;
    }

    public void setProcessedParts(List<ServiceRequestOrderLineItem> processedParts) {
        this.processedParts = processedParts;
    }

//    public List<ServiceRequestOrderLineItem> getOrderLineItems() {
//        return orderLineItems;
//    }
//
//    public void setOrderLineItems(List<ServiceRequestOrderLineItem> orderLineItems) {
//        this.orderLineItems = orderLineItems;
//    }

	public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getOrderDate() {
        return orderDate;
    }


//	public int getId() {
//		return id;
//	}
//
//	public void setId(int id) {
//		this.id = id;
//	}
    
    public String getId() {
        return id;
    }

    
    public void setId(String id) {
        this.id = id;
    }

	public void setSecondaryContact(AccountContact secondaryContact) {
		this.secondaryContact = secondaryContact;
	}

	public AccountContact getSecondaryContact() {
		return secondaryContact;
	}

	public AccountContact getPrimaryContact() {
		return primaryContact;
	}

	public void setPrimaryContact(AccountContact primaryContact) {
		this.primaryContact = primaryContact;
	}

	public GenericAddress getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(GenericAddress shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
    public String getSpecialInstructions() {
		return specialInstructions;
	}

	public void setSpecialInstructions(String specialInstructions) {
		this.specialInstructions = specialInstructions;
	}

	public String getMustResolveBy() {
		return mustResolveBy;
	}

	public void setMustResolveBy(String mustResolveBy) {
		this.mustResolveBy = mustResolveBy;
	}

	public String getSpecialHandling() {
		return specialHandling;
	}

	public void setSpecialHandling(String specialHandling) {
		this.specialHandling = specialHandling;
	}

	public void setEmailActivities(List<ServiceRequestActivity> emailActivities) {
		this.emailActivities = emailActivities;
	}

	public List<ServiceRequestActivity> getEmailActivities() {
		return emailActivities;
	}

	public AccountContact getCustomerContact() {
		return customerContact;
	}

	public void setCustomerContact(AccountContact customerContact) {
		this.customerContact = customerContact;
	}

	public Asset getAsset() {
		return asset;
	}

	public void setAsset(Asset asset) {
		this.asset = asset;
	}

	public GenericAddress getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(GenericAddress customerAddress) {
		this.customerAddress = customerAddress;
	}

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

	public String getFulfillmentStatus() {
		return fulfillmentStatus;
	}

	public void setFulfillmentStatus(String fulfillmentStatus) {
		this.fulfillmentStatus = fulfillmentStatus;
	}

	public String getAttentionTo() {
		return attentionTo;
	}

	public void setAttentionTo(String attentionTo) {
		this.attentionTo = attentionTo;
	}

	public String getAssetIdentifier() {
		return assetIdentifier;
	}

	public void setAssetIdentifier(String assetIdentifier) {
		this.assetIdentifier = assetIdentifier;
	}

	public String getInsideLocation() {
		return insideLocation;
	}

	public void setInsideLocation(String insideLocation) {
		this.insideLocation = insideLocation;
	}

	public String getSpecialHandlingInstructions() {
		return specialHandlingInstructions;
	}

	public void setSpecialHandlingInstructions(String specialHandlingInstructions) {
		this.specialHandlingInstructions = specialHandlingInstructions;
	}

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

	public List<ServiceRequestOrderLineItem> getOrderLineItems() {
		return orderLineItems;
	}

	public void setOrderLineItems(List<ServiceRequestOrderLineItem> orderLineItems) {
		this.orderLineItems = orderLineItems;
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
}
