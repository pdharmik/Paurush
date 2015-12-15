/**
 * ServiceRequestData4.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SiebelShared.lgs.om.createServiceRequest.client;

public class ServiceRequestData4  implements java.io.Serializable {
    private java.lang.String serviceRequestNumber;

    private java.lang.String serviceRequestId;

    private java.lang.String activityId;

    private java.lang.String serviceRequestDate;

    private java.lang.String serviceRequestStatusOverall;

    private java.lang.String serviceRequestStatusDetail;

    private java.lang.String serviceRequestSource;

    private java.lang.String serviceRequestType;

    private java.lang.String relatedSourceReferenceNumber;

    private com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.SiebelAssetInformation assetInformation;

    private com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.SiebelContact requester;

    private com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.SiebelContact primaryContact;

    private com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.SiebelContact secondaryContact;

    private com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.SiebelMDMCustomerInformation customerInformation;

    private com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.SiebelAddress serviceAddress;

    private com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.SiebelContact technician;

    private com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.DebriefDetails debriefDetails;

    private com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.SiebelRecommendedPartsList[] recommendedPartsList;

    private com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.SiebelReturnPartsList[] returnPartsList;

    private com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.SiebelActivityNotes[] activityNotes;

    private com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.SiebelPaymentRequestList[] paymentRequestList;

    public ServiceRequestData4() {
    }

    public ServiceRequestData4(
           java.lang.String serviceRequestNumber,
           java.lang.String serviceRequestId,
           java.lang.String activityId,
           java.lang.String serviceRequestDate,
           java.lang.String serviceRequestStatusOverall,
           java.lang.String serviceRequestStatusDetail,
           java.lang.String serviceRequestSource,
           java.lang.String serviceRequestType,
           java.lang.String relatedSourceReferenceNumber,
           com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.SiebelAssetInformation assetInformation,
           com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.SiebelContact requester,
           com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.SiebelContact primaryContact,
           com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.SiebelContact secondaryContact,
           com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.SiebelMDMCustomerInformation customerInformation,
           com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.SiebelAddress serviceAddress,
           com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.SiebelContact technician,
           com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.DebriefDetails debriefDetails,
           com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.SiebelRecommendedPartsList[] recommendedPartsList,
           com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.SiebelReturnPartsList[] returnPartsList,
           com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.SiebelActivityNotes[] activityNotes,
           com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.SiebelPaymentRequestList[] paymentRequestList) {
           this.serviceRequestNumber = serviceRequestNumber;
           this.serviceRequestId = serviceRequestId;
           this.activityId = activityId;
           this.serviceRequestDate = serviceRequestDate;
           this.serviceRequestStatusOverall = serviceRequestStatusOverall;
           this.serviceRequestStatusDetail = serviceRequestStatusDetail;
           this.serviceRequestSource = serviceRequestSource;
           this.serviceRequestType = serviceRequestType;
           this.relatedSourceReferenceNumber = relatedSourceReferenceNumber;
           this.assetInformation = assetInformation;
           this.requester = requester;
           this.primaryContact = primaryContact;
           this.secondaryContact = secondaryContact;
           this.customerInformation = customerInformation;
           this.serviceAddress = serviceAddress;
           this.technician = technician;
           this.debriefDetails = debriefDetails;
           this.recommendedPartsList = recommendedPartsList;
           this.returnPartsList = returnPartsList;
           this.activityNotes = activityNotes;
           this.paymentRequestList = paymentRequestList;
    }


    /**
     * Gets the serviceRequestNumber value for this ServiceRequestData4.
     * 
     * @return serviceRequestNumber
     */
    public java.lang.String getServiceRequestNumber() {
        return serviceRequestNumber;
    }


    /**
     * Sets the serviceRequestNumber value for this ServiceRequestData4.
     * 
     * @param serviceRequestNumber
     */
    public void setServiceRequestNumber(java.lang.String serviceRequestNumber) {
        this.serviceRequestNumber = serviceRequestNumber;
    }


    /**
     * Gets the serviceRequestId value for this ServiceRequestData4.
     * 
     * @return serviceRequestId
     */
    public java.lang.String getServiceRequestId() {
        return serviceRequestId;
    }


    /**
     * Sets the serviceRequestId value for this ServiceRequestData4.
     * 
     * @param serviceRequestId
     */
    public void setServiceRequestId(java.lang.String serviceRequestId) {
        this.serviceRequestId = serviceRequestId;
    }


    /**
     * Gets the activityId value for this ServiceRequestData4.
     * 
     * @return activityId
     */
    public java.lang.String getActivityId() {
        return activityId;
    }


    /**
     * Sets the activityId value for this ServiceRequestData4.
     * 
     * @param activityId
     */
    public void setActivityId(java.lang.String activityId) {
        this.activityId = activityId;
    }


    /**
     * Gets the serviceRequestDate value for this ServiceRequestData4.
     * 
     * @return serviceRequestDate
     */
    public java.lang.String getServiceRequestDate() {
        return serviceRequestDate;
    }


    /**
     * Sets the serviceRequestDate value for this ServiceRequestData4.
     * 
     * @param serviceRequestDate
     */
    public void setServiceRequestDate(java.lang.String serviceRequestDate) {
        this.serviceRequestDate = serviceRequestDate;
    }


    /**
     * Gets the serviceRequestStatusOverall value for this ServiceRequestData4.
     * 
     * @return serviceRequestStatusOverall
     */
    public java.lang.String getServiceRequestStatusOverall() {
        return serviceRequestStatusOverall;
    }


    /**
     * Sets the serviceRequestStatusOverall value for this ServiceRequestData4.
     * 
     * @param serviceRequestStatusOverall
     */
    public void setServiceRequestStatusOverall(java.lang.String serviceRequestStatusOverall) {
        this.serviceRequestStatusOverall = serviceRequestStatusOverall;
    }


    /**
     * Gets the serviceRequestStatusDetail value for this ServiceRequestData4.
     * 
     * @return serviceRequestStatusDetail
     */
    public java.lang.String getServiceRequestStatusDetail() {
        return serviceRequestStatusDetail;
    }


    /**
     * Sets the serviceRequestStatusDetail value for this ServiceRequestData4.
     * 
     * @param serviceRequestStatusDetail
     */
    public void setServiceRequestStatusDetail(java.lang.String serviceRequestStatusDetail) {
        this.serviceRequestStatusDetail = serviceRequestStatusDetail;
    }


    /**
     * Gets the serviceRequestSource value for this ServiceRequestData4.
     * 
     * @return serviceRequestSource
     */
    public java.lang.String getServiceRequestSource() {
        return serviceRequestSource;
    }


    /**
     * Sets the serviceRequestSource value for this ServiceRequestData4.
     * 
     * @param serviceRequestSource
     */
    public void setServiceRequestSource(java.lang.String serviceRequestSource) {
        this.serviceRequestSource = serviceRequestSource;
    }


    /**
     * Gets the serviceRequestType value for this ServiceRequestData4.
     * 
     * @return serviceRequestType
     */
    public java.lang.String getServiceRequestType() {
        return serviceRequestType;
    }


    /**
     * Sets the serviceRequestType value for this ServiceRequestData4.
     * 
     * @param serviceRequestType
     */
    public void setServiceRequestType(java.lang.String serviceRequestType) {
        this.serviceRequestType = serviceRequestType;
    }


    /**
     * Gets the relatedSourceReferenceNumber value for this ServiceRequestData4.
     * 
     * @return relatedSourceReferenceNumber
     */
    public java.lang.String getRelatedSourceReferenceNumber() {
        return relatedSourceReferenceNumber;
    }


    /**
     * Sets the relatedSourceReferenceNumber value for this ServiceRequestData4.
     * 
     * @param relatedSourceReferenceNumber
     */
    public void setRelatedSourceReferenceNumber(java.lang.String relatedSourceReferenceNumber) {
        this.relatedSourceReferenceNumber = relatedSourceReferenceNumber;
    }


    /**
     * Gets the assetInformation value for this ServiceRequestData4.
     * 
     * @return assetInformation
     */
    public com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.SiebelAssetInformation getAssetInformation() {
        return assetInformation;
    }


    /**
     * Sets the assetInformation value for this ServiceRequestData4.
     * 
     * @param assetInformation
     */
    public void setAssetInformation(com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.SiebelAssetInformation assetInformation) {
        this.assetInformation = assetInformation;
    }


    /**
     * Gets the requester value for this ServiceRequestData4.
     * 
     * @return requester
     */
    public com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.SiebelContact getRequester() {
        return requester;
    }


    /**
     * Sets the requester value for this ServiceRequestData4.
     * 
     * @param requester
     */
    public void setRequester(com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.SiebelContact requester) {
        this.requester = requester;
    }


    /**
     * Gets the primaryContact value for this ServiceRequestData4.
     * 
     * @return primaryContact
     */
    public com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.SiebelContact getPrimaryContact() {
        return primaryContact;
    }


    /**
     * Sets the primaryContact value for this ServiceRequestData4.
     * 
     * @param primaryContact
     */
    public void setPrimaryContact(com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.SiebelContact primaryContact) {
        this.primaryContact = primaryContact;
    }


    /**
     * Gets the secondaryContact value for this ServiceRequestData4.
     * 
     * @return secondaryContact
     */
    public com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.SiebelContact getSecondaryContact() {
        return secondaryContact;
    }


    /**
     * Sets the secondaryContact value for this ServiceRequestData4.
     * 
     * @param secondaryContact
     */
    public void setSecondaryContact(com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.SiebelContact secondaryContact) {
        this.secondaryContact = secondaryContact;
    }


    /**
     * Gets the customerInformation value for this ServiceRequestData4.
     * 
     * @return customerInformation
     */
    public com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.SiebelMDMCustomerInformation getCustomerInformation() {
        return customerInformation;
    }


    /**
     * Sets the customerInformation value for this ServiceRequestData4.
     * 
     * @param customerInformation
     */
    public void setCustomerInformation(com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.SiebelMDMCustomerInformation customerInformation) {
        this.customerInformation = customerInformation;
    }


    /**
     * Gets the serviceAddress value for this ServiceRequestData4.
     * 
     * @return serviceAddress
     */
    public com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.SiebelAddress getServiceAddress() {
        return serviceAddress;
    }


    /**
     * Sets the serviceAddress value for this ServiceRequestData4.
     * 
     * @param serviceAddress
     */
    public void setServiceAddress(com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.SiebelAddress serviceAddress) {
        this.serviceAddress = serviceAddress;
    }


    /**
     * Gets the technician value for this ServiceRequestData4.
     * 
     * @return technician
     */
    public com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.SiebelContact getTechnician() {
        return technician;
    }


    /**
     * Sets the technician value for this ServiceRequestData4.
     * 
     * @param technician
     */
    public void setTechnician(com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.SiebelContact technician) {
        this.technician = technician;
    }


    /**
     * Gets the debriefDetails value for this ServiceRequestData4.
     * 
     * @return debriefDetails
     */
    public com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.DebriefDetails getDebriefDetails() {
        return debriefDetails;
    }


    /**
     * Sets the debriefDetails value for this ServiceRequestData4.
     * 
     * @param debriefDetails
     */
    public void setDebriefDetails(com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.DebriefDetails debriefDetails) {
        this.debriefDetails = debriefDetails;
    }


    /**
     * Gets the recommendedPartsList value for this ServiceRequestData4.
     * 
     * @return recommendedPartsList
     */
    public com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.SiebelRecommendedPartsList[] getRecommendedPartsList() {
        return recommendedPartsList;
    }


    /**
     * Sets the recommendedPartsList value for this ServiceRequestData4.
     * 
     * @param recommendedPartsList
     */
    public void setRecommendedPartsList(com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.SiebelRecommendedPartsList[] recommendedPartsList) {
        this.recommendedPartsList = recommendedPartsList;
    }


    /**
     * Gets the returnPartsList value for this ServiceRequestData4.
     * 
     * @return returnPartsList
     */
    public com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.SiebelReturnPartsList[] getReturnPartsList() {
        return returnPartsList;
    }


    /**
     * Sets the returnPartsList value for this ServiceRequestData4.
     * 
     * @param returnPartsList
     */
    public void setReturnPartsList(com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.SiebelReturnPartsList[] returnPartsList) {
        this.returnPartsList = returnPartsList;
    }


    /**
     * Gets the activityNotes value for this ServiceRequestData4.
     * 
     * @return activityNotes
     */
    public com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.SiebelActivityNotes[] getActivityNotes() {
        return activityNotes;
    }


    /**
     * Sets the activityNotes value for this ServiceRequestData4.
     * 
     * @param activityNotes
     */
    public void setActivityNotes(com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.SiebelActivityNotes[] activityNotes) {
        this.activityNotes = activityNotes;
    }


    /**
     * Gets the paymentRequestList value for this ServiceRequestData4.
     * 
     * @return paymentRequestList
     */
    public com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.SiebelPaymentRequestList[] getPaymentRequestList() {
        return paymentRequestList;
    }


    /**
     * Sets the paymentRequestList value for this ServiceRequestData4.
     * 
     * @param paymentRequestList
     */
    public void setPaymentRequestList(com.lexmark.SiebelShared.lgs.om.createServiceRequest.client.SiebelPaymentRequestList[] paymentRequestList) {
        this.paymentRequestList = paymentRequestList;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ServiceRequestData4)) return false;
        ServiceRequestData4 other = (ServiceRequestData4) obj;
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
            ((this.activityId==null && other.getActivityId()==null) || 
             (this.activityId!=null &&
              this.activityId.equals(other.getActivityId()))) &&
            ((this.serviceRequestDate==null && other.getServiceRequestDate()==null) || 
             (this.serviceRequestDate!=null &&
              this.serviceRequestDate.equals(other.getServiceRequestDate()))) &&
            ((this.serviceRequestStatusOverall==null && other.getServiceRequestStatusOverall()==null) || 
             (this.serviceRequestStatusOverall!=null &&
              this.serviceRequestStatusOverall.equals(other.getServiceRequestStatusOverall()))) &&
            ((this.serviceRequestStatusDetail==null && other.getServiceRequestStatusDetail()==null) || 
             (this.serviceRequestStatusDetail!=null &&
              this.serviceRequestStatusDetail.equals(other.getServiceRequestStatusDetail()))) &&
            ((this.serviceRequestSource==null && other.getServiceRequestSource()==null) || 
             (this.serviceRequestSource!=null &&
              this.serviceRequestSource.equals(other.getServiceRequestSource()))) &&
            ((this.serviceRequestType==null && other.getServiceRequestType()==null) || 
             (this.serviceRequestType!=null &&
              this.serviceRequestType.equals(other.getServiceRequestType()))) &&
            ((this.relatedSourceReferenceNumber==null && other.getRelatedSourceReferenceNumber()==null) || 
             (this.relatedSourceReferenceNumber!=null &&
              this.relatedSourceReferenceNumber.equals(other.getRelatedSourceReferenceNumber()))) &&
            ((this.assetInformation==null && other.getAssetInformation()==null) || 
             (this.assetInformation!=null &&
              this.assetInformation.equals(other.getAssetInformation()))) &&
            ((this.requester==null && other.getRequester()==null) || 
             (this.requester!=null &&
              this.requester.equals(other.getRequester()))) &&
            ((this.primaryContact==null && other.getPrimaryContact()==null) || 
             (this.primaryContact!=null &&
              this.primaryContact.equals(other.getPrimaryContact()))) &&
            ((this.secondaryContact==null && other.getSecondaryContact()==null) || 
             (this.secondaryContact!=null &&
              this.secondaryContact.equals(other.getSecondaryContact()))) &&
            ((this.customerInformation==null && other.getCustomerInformation()==null) || 
             (this.customerInformation!=null &&
              this.customerInformation.equals(other.getCustomerInformation()))) &&
            ((this.serviceAddress==null && other.getServiceAddress()==null) || 
             (this.serviceAddress!=null &&
              this.serviceAddress.equals(other.getServiceAddress()))) &&
            ((this.technician==null && other.getTechnician()==null) || 
             (this.technician!=null &&
              this.technician.equals(other.getTechnician()))) &&
            ((this.debriefDetails==null && other.getDebriefDetails()==null) || 
             (this.debriefDetails!=null &&
              this.debriefDetails.equals(other.getDebriefDetails()))) &&
            ((this.recommendedPartsList==null && other.getRecommendedPartsList()==null) || 
             (this.recommendedPartsList!=null &&
              java.util.Arrays.equals(this.recommendedPartsList, other.getRecommendedPartsList()))) &&
            ((this.returnPartsList==null && other.getReturnPartsList()==null) || 
             (this.returnPartsList!=null &&
              java.util.Arrays.equals(this.returnPartsList, other.getReturnPartsList()))) &&
            ((this.activityNotes==null && other.getActivityNotes()==null) || 
             (this.activityNotes!=null &&
              java.util.Arrays.equals(this.activityNotes, other.getActivityNotes()))) &&
            ((this.paymentRequestList==null && other.getPaymentRequestList()==null) || 
             (this.paymentRequestList!=null &&
              java.util.Arrays.equals(this.paymentRequestList, other.getPaymentRequestList())));
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
        if (getActivityId() != null) {
            _hashCode += getActivityId().hashCode();
        }
        if (getServiceRequestDate() != null) {
            _hashCode += getServiceRequestDate().hashCode();
        }
        if (getServiceRequestStatusOverall() != null) {
            _hashCode += getServiceRequestStatusOverall().hashCode();
        }
        if (getServiceRequestStatusDetail() != null) {
            _hashCode += getServiceRequestStatusDetail().hashCode();
        }
        if (getServiceRequestSource() != null) {
            _hashCode += getServiceRequestSource().hashCode();
        }
        if (getServiceRequestType() != null) {
            _hashCode += getServiceRequestType().hashCode();
        }
        if (getRelatedSourceReferenceNumber() != null) {
            _hashCode += getRelatedSourceReferenceNumber().hashCode();
        }
        if (getAssetInformation() != null) {
            _hashCode += getAssetInformation().hashCode();
        }
        if (getRequester() != null) {
            _hashCode += getRequester().hashCode();
        }
        if (getPrimaryContact() != null) {
            _hashCode += getPrimaryContact().hashCode();
        }
        if (getSecondaryContact() != null) {
            _hashCode += getSecondaryContact().hashCode();
        }
        if (getCustomerInformation() != null) {
            _hashCode += getCustomerInformation().hashCode();
        }
        if (getServiceAddress() != null) {
            _hashCode += getServiceAddress().hashCode();
        }
        if (getTechnician() != null) {
            _hashCode += getTechnician().hashCode();
        }
        if (getDebriefDetails() != null) {
            _hashCode += getDebriefDetails().hashCode();
        }
        if (getRecommendedPartsList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getRecommendedPartsList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getRecommendedPartsList(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getReturnPartsList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getReturnPartsList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getReturnPartsList(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getActivityNotes() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getActivityNotes());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getActivityNotes(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getPaymentRequestList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPaymentRequestList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPaymentRequestList(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ServiceRequestData4.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "ServiceRequestData4"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serviceRequestNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ServiceRequestNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
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
        elemField.setFieldName("activityId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ActivityId"));
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
        elemField.setFieldName("serviceRequestStatusOverall");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ServiceRequestStatusOverall"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serviceRequestStatusDetail");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ServiceRequestStatusDetail"));
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
        elemField.setFieldName("serviceRequestType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ServiceRequestType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("relatedSourceReferenceNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RelatedSourceReferenceNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("assetInformation");
        elemField.setXmlName(new javax.xml.namespace.QName("", "AssetInformation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "SiebelAssetInformation"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("requester");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Requester"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "SiebelContact"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("primaryContact");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PrimaryContact"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "SiebelContact"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("secondaryContact");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SecondaryContact"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "SiebelContact"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("customerInformation");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CustomerInformation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "SiebelMDMCustomerInformation"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serviceAddress");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ServiceAddress"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "SiebelAddress"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("technician");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Technician"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "SiebelContact"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("debriefDetails");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DebriefDetails"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "DebriefDetails"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("recommendedPartsList");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RecommendedPartsList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "SiebelRecommendedPartsList"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("", "ArrayOfSiebelRecommendedPartsListItem"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("returnPartsList");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ReturnPartsList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "SiebelReturnPartsList"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("", "ArrayOfSiebelReturnPartsListItem"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("activityNotes");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ActivityNotes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "SiebelActivityNotes"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("", "ArrayOfSiebelActivityNotesItem"));
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("paymentRequestList");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PaymentRequestList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "SiebelPaymentRequestList"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("", "ArrayOfSiebelPaymentRequestListItem"));
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
