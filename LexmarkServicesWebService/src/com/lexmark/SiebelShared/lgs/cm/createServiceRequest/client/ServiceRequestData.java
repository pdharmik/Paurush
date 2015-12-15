/**
 * ServiceRequestData.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client;

public class ServiceRequestData  implements java.io.Serializable {
    private java.lang.String serviceRequestNumber;

    private java.lang.String serviceRequestId;

    private java.lang.String serviceRequestDate;

    private java.lang.String serviceRequestStatus;

    private java.lang.String serviceRequestSource;

    private java.lang.String serviceRequestRegion;

    private java.lang.String serviceRequestType;

    private com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.Requester2 requester;

    private com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.CustomerInformation customerInformation;

    private com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.PrimaryContact2 primaryContact;

    private com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.SecondaryContact2 secondaryContact;

    private com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.AssetInformation assetInformation;

    private java.lang.String problemDescription;

    private java.lang.String requestedService;

    private com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.SelectedServiceDetails[] selectedServiceDetails;

    private com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ServiceAddress serviceAddress;

    private java.lang.String trackingNumber;

    private java.lang.String carrier;

    private java.lang.String shipDate;

    private java.lang.String referenceNumber;

    private java.lang.String returnTrackingNumber;

    private com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ReturnedAssetInformation returnedAssetInformation;

    private java.lang.String optionExchangeAdditionalDescription;

    private com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ActivityDetails[] activityDetails;

    private com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ServiceProviderInformation serviceProviderInformation;

    public ServiceRequestData() {
    }

    public ServiceRequestData(
           java.lang.String serviceRequestNumber,
           java.lang.String serviceRequestId,
           java.lang.String serviceRequestDate,
           java.lang.String serviceRequestStatus,
           java.lang.String serviceRequestSource,
           java.lang.String serviceRequestRegion,
           java.lang.String serviceRequestType,
           com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.Requester2 requester,
           com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.CustomerInformation customerInformation,
           com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.PrimaryContact2 primaryContact,
           com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.SecondaryContact2 secondaryContact,
           com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.AssetInformation assetInformation,
           java.lang.String problemDescription,
           java.lang.String requestedService,
           com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.SelectedServiceDetails[] selectedServiceDetails,
           com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ServiceAddress serviceAddress,
           java.lang.String trackingNumber,
           java.lang.String carrier,
           java.lang.String shipDate,
           java.lang.String referenceNumber,
           java.lang.String returnTrackingNumber,
           com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ReturnedAssetInformation returnedAssetInformation,
           java.lang.String optionExchangeAdditionalDescription,
           com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ActivityDetails[] activityDetails,
           com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ServiceProviderInformation serviceProviderInformation) {
           this.serviceRequestNumber = serviceRequestNumber;
           this.serviceRequestId = serviceRequestId;
           this.serviceRequestDate = serviceRequestDate;
           this.serviceRequestStatus = serviceRequestStatus;
           this.serviceRequestSource = serviceRequestSource;
           this.serviceRequestRegion = serviceRequestRegion;
           this.serviceRequestType = serviceRequestType;
           this.requester = requester;
           this.customerInformation = customerInformation;
           this.primaryContact = primaryContact;
           this.secondaryContact = secondaryContact;
           this.assetInformation = assetInformation;
           this.problemDescription = problemDescription;
           this.requestedService = requestedService;
           this.selectedServiceDetails = selectedServiceDetails;
           this.serviceAddress = serviceAddress;
           this.trackingNumber = trackingNumber;
           this.carrier = carrier;
           this.shipDate = shipDate;
           this.referenceNumber = referenceNumber;
           this.returnTrackingNumber = returnTrackingNumber;
           this.returnedAssetInformation = returnedAssetInformation;
           this.optionExchangeAdditionalDescription = optionExchangeAdditionalDescription;
           this.activityDetails = activityDetails;
           this.serviceProviderInformation = serviceProviderInformation;
    }


    /**
     * Gets the serviceRequestNumber value for this ServiceRequestData.
     * 
     * @return serviceRequestNumber
     */
    public java.lang.String getServiceRequestNumber() {
        return serviceRequestNumber;
    }


    /**
     * Sets the serviceRequestNumber value for this ServiceRequestData.
     * 
     * @param serviceRequestNumber
     */
    public void setServiceRequestNumber(java.lang.String serviceRequestNumber) {
        this.serviceRequestNumber = serviceRequestNumber;
    }


    /**
     * Gets the serviceRequestId value for this ServiceRequestData.
     * 
     * @return serviceRequestId
     */
    public java.lang.String getServiceRequestId() {
        return serviceRequestId;
    }


    /**
     * Sets the serviceRequestId value for this ServiceRequestData.
     * 
     * @param serviceRequestId
     */
    public void setServiceRequestId(java.lang.String serviceRequestId) {
        this.serviceRequestId = serviceRequestId;
    }


    /**
     * Gets the serviceRequestDate value for this ServiceRequestData.
     * 
     * @return serviceRequestDate
     */
    public java.lang.String getServiceRequestDate() {
        return serviceRequestDate;
    }


    /**
     * Sets the serviceRequestDate value for this ServiceRequestData.
     * 
     * @param serviceRequestDate
     */
    public void setServiceRequestDate(java.lang.String serviceRequestDate) {
        this.serviceRequestDate = serviceRequestDate;
    }


    /**
     * Gets the serviceRequestStatus value for this ServiceRequestData.
     * 
     * @return serviceRequestStatus
     */
    public java.lang.String getServiceRequestStatus() {
        return serviceRequestStatus;
    }


    /**
     * Sets the serviceRequestStatus value for this ServiceRequestData.
     * 
     * @param serviceRequestStatus
     */
    public void setServiceRequestStatus(java.lang.String serviceRequestStatus) {
        this.serviceRequestStatus = serviceRequestStatus;
    }


    /**
     * Gets the serviceRequestSource value for this ServiceRequestData.
     * 
     * @return serviceRequestSource
     */
    public java.lang.String getServiceRequestSource() {
        return serviceRequestSource;
    }


    /**
     * Sets the serviceRequestSource value for this ServiceRequestData.
     * 
     * @param serviceRequestSource
     */
    public void setServiceRequestSource(java.lang.String serviceRequestSource) {
        this.serviceRequestSource = serviceRequestSource;
    }


    /**
     * Gets the serviceRequestRegion value for this ServiceRequestData.
     * 
     * @return serviceRequestRegion
     */
    public java.lang.String getServiceRequestRegion() {
        return serviceRequestRegion;
    }


    /**
     * Sets the serviceRequestRegion value for this ServiceRequestData.
     * 
     * @param serviceRequestRegion
     */
    public void setServiceRequestRegion(java.lang.String serviceRequestRegion) {
        this.serviceRequestRegion = serviceRequestRegion;
    }


    /**
     * Gets the serviceRequestType value for this ServiceRequestData.
     * 
     * @return serviceRequestType
     */
    public java.lang.String getServiceRequestType() {
        return serviceRequestType;
    }


    /**
     * Sets the serviceRequestType value for this ServiceRequestData.
     * 
     * @param serviceRequestType
     */
    public void setServiceRequestType(java.lang.String serviceRequestType) {
        this.serviceRequestType = serviceRequestType;
    }


    /**
     * Gets the requester value for this ServiceRequestData.
     * 
     * @return requester
     */
    public com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.Requester2 getRequester() {
        return requester;
    }


    /**
     * Sets the requester value for this ServiceRequestData.
     * 
     * @param requester
     */
    public void setRequester(com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.Requester2 requester) {
        this.requester = requester;
    }


    /**
     * Gets the customerInformation value for this ServiceRequestData.
     * 
     * @return customerInformation
     */
    public com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.CustomerInformation getCustomerInformation() {
        return customerInformation;
    }


    /**
     * Sets the customerInformation value for this ServiceRequestData.
     * 
     * @param customerInformation
     */
    public void setCustomerInformation(com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.CustomerInformation customerInformation) {
        this.customerInformation = customerInformation;
    }


    /**
     * Gets the primaryContact value for this ServiceRequestData.
     * 
     * @return primaryContact
     */
    public com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.PrimaryContact2 getPrimaryContact() {
        return primaryContact;
    }


    /**
     * Sets the primaryContact value for this ServiceRequestData.
     * 
     * @param primaryContact
     */
    public void setPrimaryContact(com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.PrimaryContact2 primaryContact) {
        this.primaryContact = primaryContact;
    }


    /**
     * Gets the secondaryContact value for this ServiceRequestData.
     * 
     * @return secondaryContact
     */
    public com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.SecondaryContact2 getSecondaryContact() {
        return secondaryContact;
    }


    /**
     * Sets the secondaryContact value for this ServiceRequestData.
     * 
     * @param secondaryContact
     */
    public void setSecondaryContact(com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.SecondaryContact2 secondaryContact) {
        this.secondaryContact = secondaryContact;
    }


    /**
     * Gets the assetInformation value for this ServiceRequestData.
     * 
     * @return assetInformation
     */
    public com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.AssetInformation getAssetInformation() {
        return assetInformation;
    }


    /**
     * Sets the assetInformation value for this ServiceRequestData.
     * 
     * @param assetInformation
     */
    public void setAssetInformation(com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.AssetInformation assetInformation) {
        this.assetInformation = assetInformation;
    }


    /**
     * Gets the problemDescription value for this ServiceRequestData.
     * 
     * @return problemDescription
     */
    public java.lang.String getProblemDescription() {
        return problemDescription;
    }


    /**
     * Sets the problemDescription value for this ServiceRequestData.
     * 
     * @param problemDescription
     */
    public void setProblemDescription(java.lang.String problemDescription) {
        this.problemDescription = problemDescription;
    }


    /**
     * Gets the requestedService value for this ServiceRequestData.
     * 
     * @return requestedService
     */
    public java.lang.String getRequestedService() {
        return requestedService;
    }


    /**
     * Sets the requestedService value for this ServiceRequestData.
     * 
     * @param requestedService
     */
    public void setRequestedService(java.lang.String requestedService) {
        this.requestedService = requestedService;
    }


    /**
     * Gets the selectedServiceDetails value for this ServiceRequestData.
     * 
     * @return selectedServiceDetails
     */
    public com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.SelectedServiceDetails[] getSelectedServiceDetails() {
        return selectedServiceDetails;
    }


    /**
     * Sets the selectedServiceDetails value for this ServiceRequestData.
     * 
     * @param selectedServiceDetails
     */
    public void setSelectedServiceDetails(com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.SelectedServiceDetails[] selectedServiceDetails) {
        this.selectedServiceDetails = selectedServiceDetails;
    }


    /**
     * Gets the serviceAddress value for this ServiceRequestData.
     * 
     * @return serviceAddress
     */
    public com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ServiceAddress getServiceAddress() {
        return serviceAddress;
    }


    /**
     * Sets the serviceAddress value for this ServiceRequestData.
     * 
     * @param serviceAddress
     */
    public void setServiceAddress(com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ServiceAddress serviceAddress) {
        this.serviceAddress = serviceAddress;
    }


    /**
     * Gets the trackingNumber value for this ServiceRequestData.
     * 
     * @return trackingNumber
     */
    public java.lang.String getTrackingNumber() {
        return trackingNumber;
    }


    /**
     * Sets the trackingNumber value for this ServiceRequestData.
     * 
     * @param trackingNumber
     */
    public void setTrackingNumber(java.lang.String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }


    /**
     * Gets the carrier value for this ServiceRequestData.
     * 
     * @return carrier
     */
    public java.lang.String getCarrier() {
        return carrier;
    }


    /**
     * Sets the carrier value for this ServiceRequestData.
     * 
     * @param carrier
     */
    public void setCarrier(java.lang.String carrier) {
        this.carrier = carrier;
    }


    /**
     * Gets the shipDate value for this ServiceRequestData.
     * 
     * @return shipDate
     */
    public java.lang.String getShipDate() {
        return shipDate;
    }


    /**
     * Sets the shipDate value for this ServiceRequestData.
     * 
     * @param shipDate
     */
    public void setShipDate(java.lang.String shipDate) {
        this.shipDate = shipDate;
    }


    /**
     * Gets the referenceNumber value for this ServiceRequestData.
     * 
     * @return referenceNumber
     */
    public java.lang.String getReferenceNumber() {
        return referenceNumber;
    }


    /**
     * Sets the referenceNumber value for this ServiceRequestData.
     * 
     * @param referenceNumber
     */
    public void setReferenceNumber(java.lang.String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }


    /**
     * Gets the returnTrackingNumber value for this ServiceRequestData.
     * 
     * @return returnTrackingNumber
     */
    public java.lang.String getReturnTrackingNumber() {
        return returnTrackingNumber;
    }


    /**
     * Sets the returnTrackingNumber value for this ServiceRequestData.
     * 
     * @param returnTrackingNumber
     */
    public void setReturnTrackingNumber(java.lang.String returnTrackingNumber) {
        this.returnTrackingNumber = returnTrackingNumber;
    }


    /**
     * Gets the returnedAssetInformation value for this ServiceRequestData.
     * 
     * @return returnedAssetInformation
     */
    public com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ReturnedAssetInformation getReturnedAssetInformation() {
        return returnedAssetInformation;
    }


    /**
     * Sets the returnedAssetInformation value for this ServiceRequestData.
     * 
     * @param returnedAssetInformation
     */
    public void setReturnedAssetInformation(com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ReturnedAssetInformation returnedAssetInformation) {
        this.returnedAssetInformation = returnedAssetInformation;
    }


    /**
     * Gets the optionExchangeAdditionalDescription value for this ServiceRequestData.
     * 
     * @return optionExchangeAdditionalDescription
     */
    public java.lang.String getOptionExchangeAdditionalDescription() {
        return optionExchangeAdditionalDescription;
    }


    /**
     * Sets the optionExchangeAdditionalDescription value for this ServiceRequestData.
     * 
     * @param optionExchangeAdditionalDescription
     */
    public void setOptionExchangeAdditionalDescription(java.lang.String optionExchangeAdditionalDescription) {
        this.optionExchangeAdditionalDescription = optionExchangeAdditionalDescription;
    }


    /**
     * Gets the activityDetails value for this ServiceRequestData.
     * 
     * @return activityDetails
     */
    public com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ActivityDetails[] getActivityDetails() {
        return activityDetails;
    }


    /**
     * Sets the activityDetails value for this ServiceRequestData.
     * 
     * @param activityDetails
     */
    public void setActivityDetails(com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ActivityDetails[] activityDetails) {
        this.activityDetails = activityDetails;
    }


    /**
     * Gets the serviceProviderInformation value for this ServiceRequestData.
     * 
     * @return serviceProviderInformation
     */
    public com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ServiceProviderInformation getServiceProviderInformation() {
        return serviceProviderInformation;
    }


    /**
     * Sets the serviceProviderInformation value for this ServiceRequestData.
     * 
     * @param serviceProviderInformation
     */
    public void setServiceProviderInformation(com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client.ServiceProviderInformation serviceProviderInformation) {
        this.serviceProviderInformation = serviceProviderInformation;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ServiceRequestData)) return false;
        ServiceRequestData other = (ServiceRequestData) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.serviceRequestNumber==null && other.getServiceRequestNumber()==null) || 
             (this.serviceRequestNumber!=null &&
              this.serviceRequestNumber.equals(other.getServiceRequestNumber()))) &&
            ((this.serviceRequestId==null && other.getServiceRequestId()==null) || 
             (this.serviceRequestId!=null &&
              this.serviceRequestId.equals(other.getServiceRequestId()))) &&
            ((this.serviceRequestDate==null && other.getServiceRequestDate()==null) || 
             (this.serviceRequestDate!=null &&
              this.serviceRequestDate.equals(other.getServiceRequestDate()))) &&
            ((this.serviceRequestStatus==null && other.getServiceRequestStatus()==null) || 
             (this.serviceRequestStatus!=null &&
              this.serviceRequestStatus.equals(other.getServiceRequestStatus()))) &&
            ((this.serviceRequestSource==null && other.getServiceRequestSource()==null) || 
             (this.serviceRequestSource!=null &&
              this.serviceRequestSource.equals(other.getServiceRequestSource()))) &&
            ((this.serviceRequestRegion==null && other.getServiceRequestRegion()==null) || 
             (this.serviceRequestRegion!=null &&
              this.serviceRequestRegion.equals(other.getServiceRequestRegion()))) &&
            ((this.serviceRequestType==null && other.getServiceRequestType()==null) || 
             (this.serviceRequestType!=null &&
              this.serviceRequestType.equals(other.getServiceRequestType()))) &&
            ((this.requester==null && other.getRequester()==null) || 
             (this.requester!=null &&
              this.requester.equals(other.getRequester()))) &&
            ((this.customerInformation==null && other.getCustomerInformation()==null) || 
             (this.customerInformation!=null &&
              this.customerInformation.equals(other.getCustomerInformation()))) &&
            ((this.primaryContact==null && other.getPrimaryContact()==null) || 
             (this.primaryContact!=null &&
              this.primaryContact.equals(other.getPrimaryContact()))) &&
            ((this.secondaryContact==null && other.getSecondaryContact()==null) || 
             (this.secondaryContact!=null &&
              this.secondaryContact.equals(other.getSecondaryContact()))) &&
            ((this.assetInformation==null && other.getAssetInformation()==null) || 
             (this.assetInformation!=null &&
              this.assetInformation.equals(other.getAssetInformation()))) &&
            ((this.problemDescription==null && other.getProblemDescription()==null) || 
             (this.problemDescription!=null &&
              this.problemDescription.equals(other.getProblemDescription()))) &&
            ((this.requestedService==null && other.getRequestedService()==null) || 
             (this.requestedService!=null &&
              this.requestedService.equals(other.getRequestedService()))) &&
            ((this.selectedServiceDetails==null && other.getSelectedServiceDetails()==null) || 
             (this.selectedServiceDetails!=null &&
              java.util.Arrays.equals(this.selectedServiceDetails, other.getSelectedServiceDetails()))) &&
            ((this.serviceAddress==null && other.getServiceAddress()==null) || 
             (this.serviceAddress!=null &&
              this.serviceAddress.equals(other.getServiceAddress()))) &&
            ((this.trackingNumber==null && other.getTrackingNumber()==null) || 
             (this.trackingNumber!=null &&
              this.trackingNumber.equals(other.getTrackingNumber()))) &&
            ((this.carrier==null && other.getCarrier()==null) || 
             (this.carrier!=null &&
              this.carrier.equals(other.getCarrier()))) &&
            ((this.shipDate==null && other.getShipDate()==null) || 
             (this.shipDate!=null &&
              this.shipDate.equals(other.getShipDate()))) &&
            ((this.referenceNumber==null && other.getReferenceNumber()==null) || 
             (this.referenceNumber!=null &&
              this.referenceNumber.equals(other.getReferenceNumber()))) &&
            ((this.returnTrackingNumber==null && other.getReturnTrackingNumber()==null) || 
             (this.returnTrackingNumber!=null &&
              this.returnTrackingNumber.equals(other.getReturnTrackingNumber()))) &&
            ((this.returnedAssetInformation==null && other.getReturnedAssetInformation()==null) || 
             (this.returnedAssetInformation!=null &&
              this.returnedAssetInformation.equals(other.getReturnedAssetInformation()))) &&
            ((this.optionExchangeAdditionalDescription==null && other.getOptionExchangeAdditionalDescription()==null) || 
             (this.optionExchangeAdditionalDescription!=null &&
              this.optionExchangeAdditionalDescription.equals(other.getOptionExchangeAdditionalDescription()))) &&
            ((this.activityDetails==null && other.getActivityDetails()==null) || 
             (this.activityDetails!=null &&
              java.util.Arrays.equals(this.activityDetails, other.getActivityDetails()))) &&
            ((this.serviceProviderInformation==null && other.getServiceProviderInformation()==null) || 
             (this.serviceProviderInformation!=null &&
              this.serviceProviderInformation.equals(other.getServiceProviderInformation())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getServiceRequestNumber() != null) {
            _hashCode += getServiceRequestNumber().hashCode();
        }
        if (getServiceRequestId() != null) {
            _hashCode += getServiceRequestId().hashCode();
        }
        if (getServiceRequestDate() != null) {
            _hashCode += getServiceRequestDate().hashCode();
        }
        if (getServiceRequestStatus() != null) {
            _hashCode += getServiceRequestStatus().hashCode();
        }
        if (getServiceRequestSource() != null) {
            _hashCode += getServiceRequestSource().hashCode();
        }
        if (getServiceRequestRegion() != null) {
            _hashCode += getServiceRequestRegion().hashCode();
        }
        if (getServiceRequestType() != null) {
            _hashCode += getServiceRequestType().hashCode();
        }
        if (getRequester() != null) {
            _hashCode += getRequester().hashCode();
        }
        if (getCustomerInformation() != null) {
            _hashCode += getCustomerInformation().hashCode();
        }
        if (getPrimaryContact() != null) {
            _hashCode += getPrimaryContact().hashCode();
        }
        if (getSecondaryContact() != null) {
            _hashCode += getSecondaryContact().hashCode();
        }
        if (getAssetInformation() != null) {
            _hashCode += getAssetInformation().hashCode();
        }
        if (getProblemDescription() != null) {
            _hashCode += getProblemDescription().hashCode();
        }
        if (getRequestedService() != null) {
            _hashCode += getRequestedService().hashCode();
        }
        if (getSelectedServiceDetails() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getSelectedServiceDetails());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getSelectedServiceDetails(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getServiceAddress() != null) {
            _hashCode += getServiceAddress().hashCode();
        }
        if (getTrackingNumber() != null) {
            _hashCode += getTrackingNumber().hashCode();
        }
        if (getCarrier() != null) {
            _hashCode += getCarrier().hashCode();
        }
        if (getShipDate() != null) {
            _hashCode += getShipDate().hashCode();
        }
        if (getReferenceNumber() != null) {
            _hashCode += getReferenceNumber().hashCode();
        }
        if (getReturnTrackingNumber() != null) {
            _hashCode += getReturnTrackingNumber().hashCode();
        }
        if (getReturnedAssetInformation() != null) {
            _hashCode += getReturnedAssetInformation().hashCode();
        }
        if (getOptionExchangeAdditionalDescription() != null) {
            _hashCode += getOptionExchangeAdditionalDescription().hashCode();
        }
        if (getActivityDetails() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getActivityDetails());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getActivityDetails(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getServiceProviderInformation() != null) {
            _hashCode += getServiceProviderInformation().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ServiceRequestData.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ServiceRequestData"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serviceRequestNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ServiceRequestNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serviceRequestId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ServiceRequestId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serviceRequestDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ServiceRequestDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serviceRequestStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ServiceRequestStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serviceRequestSource");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ServiceRequestSource"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serviceRequestRegion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ServiceRequestRegion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serviceRequestType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ServiceRequestType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("requester");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Requester"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "Requester2"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("customerInformation");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CustomerInformation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "CustomerInformation"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("primaryContact");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PrimaryContact"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "PrimaryContact2"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("secondaryContact");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SecondaryContact"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "SecondaryContact2"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("assetInformation");
        elemField.setXmlName(new javax.xml.namespace.QName("", "AssetInformation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "AssetInformation"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("problemDescription");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ProblemDescription"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("requestedService");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RequestedService"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("selectedServiceDetails");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SelectedServiceDetails"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "SelectedServiceDetails"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("", "ArrayOfSelectedServiceDetailsItem"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serviceAddress");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ServiceAddress"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ServiceAddress"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("trackingNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TrackingNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("carrier");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Carrier"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("shipDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ShipDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("referenceNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ReferenceNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("returnTrackingNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ReturnTrackingNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("returnedAssetInformation");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ReturnedAssetInformation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ReturnedAssetInformation"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("optionExchangeAdditionalDescription");
        elemField.setXmlName(new javax.xml.namespace.QName("", "optionExchangeAdditionalDescription"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("activityDetails");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ActivityDetails"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ActivityDetails"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("", "ArrayOfActivityDetailsItem"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serviceProviderInformation");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ServiceProviderInformation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ServiceProviderInformation"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
