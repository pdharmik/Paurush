/**
 * ClaimsServiceRequestData.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.webServices.warrantyClaimsServiceRequest.client1;

public class ClaimsServiceRequestData  implements java.io.Serializable {
    private java.lang.String claimId;

    private java.lang.String serviceRequestNumber;

    private java.lang.String activityId;

    private java.lang.String claimDate;

    private java.lang.String claimStatus;

    private java.lang.String claimSource;

    private java.lang.String serviceRequestType;

    private com.lexmark.webServices.warrantyClaimsServiceRequest.client1.SiebelContact requester;

    private com.lexmark.webServices.warrantyClaimsServiceRequest.client1.SiebelContact primaryContact;

    private com.lexmark.webServices.warrantyClaimsServiceRequest.client1.SiebelAssetInformation assetInformation;

    private com.lexmark.webServices.warrantyClaimsServiceRequest.client1.SiebelServiceProviderInformation serviceProviderInformation;

    private com.lexmark.webServices.warrantyClaimsServiceRequest.client1.SiebelServiceRequestDetails claimDetails;

    private com.lexmark.webServices.warrantyClaimsServiceRequest.client1.SiebelContact technician;

    private com.lexmark.webServices.warrantyClaimsServiceRequest.client1.SiebelRecommendedPartsList[] recommendPartsList;

    private com.lexmark.webServices.warrantyClaimsServiceRequest.client1.SiebelReturnPartsList[] returnPartsList;

    private com.lexmark.webServices.warrantyClaimsServiceRequest.client1.SiebelActivityNotes[] activityNotes;

    private java.lang.String repairCompleteFlag;

    private com.lexmark.webServices.warrantyClaimsServiceRequest.client1.SiebelDebriefInformation debriefInformation;

    private com.lexmark.webServices.warrantyClaimsServiceRequest.client1.SiebelPaymentRequestList[] paymentRequestList;

    public ClaimsServiceRequestData() {
    }

    public ClaimsServiceRequestData(
           java.lang.String claimId,
           java.lang.String serviceRequestNumber,
           java.lang.String activityId,
           java.lang.String claimDate,
           java.lang.String claimStatus,
           java.lang.String claimSource,
           java.lang.String serviceRequestType,
           com.lexmark.webServices.warrantyClaimsServiceRequest.client1.SiebelContact requester,
           com.lexmark.webServices.warrantyClaimsServiceRequest.client1.SiebelContact primaryContact,
           com.lexmark.webServices.warrantyClaimsServiceRequest.client1.SiebelAssetInformation assetInformation,
           com.lexmark.webServices.warrantyClaimsServiceRequest.client1.SiebelServiceProviderInformation serviceProviderInformation,
           com.lexmark.webServices.warrantyClaimsServiceRequest.client1.SiebelServiceRequestDetails claimDetails,
           com.lexmark.webServices.warrantyClaimsServiceRequest.client1.SiebelContact technician,
           com.lexmark.webServices.warrantyClaimsServiceRequest.client1.SiebelRecommendedPartsList[] recommendPartsList,
           com.lexmark.webServices.warrantyClaimsServiceRequest.client1.SiebelReturnPartsList[] returnPartsList,
           com.lexmark.webServices.warrantyClaimsServiceRequest.client1.SiebelActivityNotes[] activityNotes,
           java.lang.String repairCompleteFlag,
           com.lexmark.webServices.warrantyClaimsServiceRequest.client1.SiebelDebriefInformation debriefInformation,
           com.lexmark.webServices.warrantyClaimsServiceRequest.client1.SiebelPaymentRequestList[] paymentRequestList) {
           this.claimId = claimId;
           this.serviceRequestNumber = serviceRequestNumber;
           this.activityId = activityId;
           this.claimDate = claimDate;
           this.claimStatus = claimStatus;
           this.claimSource = claimSource;
           this.serviceRequestType = serviceRequestType;
           this.requester = requester;
           this.primaryContact = primaryContact;
           this.assetInformation = assetInformation;
           this.serviceProviderInformation = serviceProviderInformation;
           this.claimDetails = claimDetails;
           this.technician = technician;
           this.recommendPartsList = recommendPartsList;
           this.returnPartsList = returnPartsList;
           this.activityNotes = activityNotes;
           this.repairCompleteFlag = repairCompleteFlag;
           this.debriefInformation = debriefInformation;
           this.paymentRequestList = paymentRequestList;
    }


    /**
     * Gets the claimId value for this ClaimsServiceRequestData.
     * 
     * @return claimId
     */
    public java.lang.String getClaimId() {
        return claimId;
    }


    /**
     * Sets the claimId value for this ClaimsServiceRequestData.
     * 
     * @param claimId
     */
    public void setClaimId(java.lang.String claimId) {
        this.claimId = claimId;
    }


    /**
     * Gets the serviceRequestNumber value for this ClaimsServiceRequestData.
     * 
     * @return serviceRequestNumber
     */
    public java.lang.String getServiceRequestNumber() {
        return serviceRequestNumber;
    }


    /**
     * Sets the serviceRequestNumber value for this ClaimsServiceRequestData.
     * 
     * @param serviceRequestNumber
     */
    public void setServiceRequestNumber(java.lang.String serviceRequestNumber) {
        this.serviceRequestNumber = serviceRequestNumber;
    }


    /**
     * Gets the activityId value for this ClaimsServiceRequestData.
     * 
     * @return activityId
     */
    public java.lang.String getActivityId() {
        return activityId;
    }


    /**
     * Sets the activityId value for this ClaimsServiceRequestData.
     * 
     * @param activityId
     */
    public void setActivityId(java.lang.String activityId) {
        this.activityId = activityId;
    }


    /**
     * Gets the claimDate value for this ClaimsServiceRequestData.
     * 
     * @return claimDate
     */
    public java.lang.String getClaimDate() {
        return claimDate;
    }


    /**
     * Sets the claimDate value for this ClaimsServiceRequestData.
     * 
     * @param claimDate
     */
    public void setClaimDate(java.lang.String claimDate) {
        this.claimDate = claimDate;
    }


    /**
     * Gets the claimStatus value for this ClaimsServiceRequestData.
     * 
     * @return claimStatus
     */
    public java.lang.String getClaimStatus() {
        return claimStatus;
    }


    /**
     * Sets the claimStatus value for this ClaimsServiceRequestData.
     * 
     * @param claimStatus
     */
    public void setClaimStatus(java.lang.String claimStatus) {
        this.claimStatus = claimStatus;
    }


    /**
     * Gets the claimSource value for this ClaimsServiceRequestData.
     * 
     * @return claimSource
     */
    public java.lang.String getClaimSource() {
        return claimSource;
    }


    /**
     * Sets the claimSource value for this ClaimsServiceRequestData.
     * 
     * @param claimSource
     */
    public void setClaimSource(java.lang.String claimSource) {
        this.claimSource = claimSource;
    }


    /**
     * Gets the serviceRequestType value for this ClaimsServiceRequestData.
     * 
     * @return serviceRequestType
     */
    public java.lang.String getServiceRequestType() {
        return serviceRequestType;
    }


    /**
     * Sets the serviceRequestType value for this ClaimsServiceRequestData.
     * 
     * @param serviceRequestType
     */
    public void setServiceRequestType(java.lang.String serviceRequestType) {
        this.serviceRequestType = serviceRequestType;
    }


    /**
     * Gets the requester value for this ClaimsServiceRequestData.
     * 
     * @return requester
     */
    public com.lexmark.webServices.warrantyClaimsServiceRequest.client1.SiebelContact getRequester() {
        return requester;
    }


    /**
     * Sets the requester value for this ClaimsServiceRequestData.
     * 
     * @param requester
     */
    public void setRequester(com.lexmark.webServices.warrantyClaimsServiceRequest.client1.SiebelContact requester) {
        this.requester = requester;
    }


    /**
     * Gets the primaryContact value for this ClaimsServiceRequestData.
     * 
     * @return primaryContact
     */
    public com.lexmark.webServices.warrantyClaimsServiceRequest.client1.SiebelContact getPrimaryContact() {
        return primaryContact;
    }


    /**
     * Sets the primaryContact value for this ClaimsServiceRequestData.
     * 
     * @param primaryContact
     */
    public void setPrimaryContact(com.lexmark.webServices.warrantyClaimsServiceRequest.client1.SiebelContact primaryContact) {
        this.primaryContact = primaryContact;
    }


    /**
     * Gets the assetInformation value for this ClaimsServiceRequestData.
     * 
     * @return assetInformation
     */
    public com.lexmark.webServices.warrantyClaimsServiceRequest.client1.SiebelAssetInformation getAssetInformation() {
        return assetInformation;
    }


    /**
     * Sets the assetInformation value for this ClaimsServiceRequestData.
     * 
     * @param assetInformation
     */
    public void setAssetInformation(com.lexmark.webServices.warrantyClaimsServiceRequest.client1.SiebelAssetInformation assetInformation) {
        this.assetInformation = assetInformation;
    }


    /**
     * Gets the serviceProviderInformation value for this ClaimsServiceRequestData.
     * 
     * @return serviceProviderInformation
     */
    public com.lexmark.webServices.warrantyClaimsServiceRequest.client1.SiebelServiceProviderInformation getServiceProviderInformation() {
        return serviceProviderInformation;
    }


    /**
     * Sets the serviceProviderInformation value for this ClaimsServiceRequestData.
     * 
     * @param serviceProviderInformation
     */
    public void setServiceProviderInformation(com.lexmark.webServices.warrantyClaimsServiceRequest.client1.SiebelServiceProviderInformation serviceProviderInformation) {
        this.serviceProviderInformation = serviceProviderInformation;
    }


    /**
     * Gets the claimDetails value for this ClaimsServiceRequestData.
     * 
     * @return claimDetails
     */
    public com.lexmark.webServices.warrantyClaimsServiceRequest.client1.SiebelServiceRequestDetails getClaimDetails() {
        return claimDetails;
    }


    /**
     * Sets the claimDetails value for this ClaimsServiceRequestData.
     * 
     * @param claimDetails
     */
    public void setClaimDetails(com.lexmark.webServices.warrantyClaimsServiceRequest.client1.SiebelServiceRequestDetails claimDetails) {
        this.claimDetails = claimDetails;
    }


    /**
     * Gets the technician value for this ClaimsServiceRequestData.
     * 
     * @return technician
     */
    public com.lexmark.webServices.warrantyClaimsServiceRequest.client1.SiebelContact getTechnician() {
        return technician;
    }


    /**
     * Sets the technician value for this ClaimsServiceRequestData.
     * 
     * @param technician
     */
    public void setTechnician(com.lexmark.webServices.warrantyClaimsServiceRequest.client1.SiebelContact technician) {
        this.technician = technician;
    }


    /**
     * Gets the recommendPartsList value for this ClaimsServiceRequestData.
     * 
     * @return recommendPartsList
     */
    public com.lexmark.webServices.warrantyClaimsServiceRequest.client1.SiebelRecommendedPartsList[] getRecommendPartsList() {
        return recommendPartsList;
    }


    /**
     * Sets the recommendPartsList value for this ClaimsServiceRequestData.
     * 
     * @param recommendPartsList
     */
    public void setRecommendPartsList(com.lexmark.webServices.warrantyClaimsServiceRequest.client1.SiebelRecommendedPartsList[] recommendPartsList) {
        this.recommendPartsList = recommendPartsList;
    }

    public com.lexmark.webServices.warrantyClaimsServiceRequest.client1.SiebelRecommendedPartsList getRecommendPartsList(int i) {
        return this.recommendPartsList[i];
    }

    public void setRecommendPartsList(int i, com.lexmark.webServices.warrantyClaimsServiceRequest.client1.SiebelRecommendedPartsList _value) {
        this.recommendPartsList[i] = _value;
    }


    /**
     * Gets the returnPartsList value for this ClaimsServiceRequestData.
     * 
     * @return returnPartsList
     */
    public com.lexmark.webServices.warrantyClaimsServiceRequest.client1.SiebelReturnPartsList[] getReturnPartsList() {
        return returnPartsList;
    }


    /**
     * Sets the returnPartsList value for this ClaimsServiceRequestData.
     * 
     * @param returnPartsList
     */
    public void setReturnPartsList(com.lexmark.webServices.warrantyClaimsServiceRequest.client1.SiebelReturnPartsList[] returnPartsList) {
        this.returnPartsList = returnPartsList;
    }

    public com.lexmark.webServices.warrantyClaimsServiceRequest.client1.SiebelReturnPartsList getReturnPartsList(int i) {
        return this.returnPartsList[i];
    }

    public void setReturnPartsList(int i, com.lexmark.webServices.warrantyClaimsServiceRequest.client1.SiebelReturnPartsList _value) {
        this.returnPartsList[i] = _value;
    }


    /**
     * Gets the activityNotes value for this ClaimsServiceRequestData.
     * 
     * @return activityNotes
     */
    public com.lexmark.webServices.warrantyClaimsServiceRequest.client1.SiebelActivityNotes[] getActivityNotes() {
        return activityNotes;
    }


    /**
     * Sets the activityNotes value for this ClaimsServiceRequestData.
     * 
     * @param activityNotes
     */
    public void setActivityNotes(com.lexmark.webServices.warrantyClaimsServiceRequest.client1.SiebelActivityNotes[] activityNotes) {
        this.activityNotes = activityNotes;
    }

    public com.lexmark.webServices.warrantyClaimsServiceRequest.client1.SiebelActivityNotes getActivityNotes(int i) {
        return this.activityNotes[i];
    }

    public void setActivityNotes(int i, com.lexmark.webServices.warrantyClaimsServiceRequest.client1.SiebelActivityNotes _value) {
        this.activityNotes[i] = _value;
    }


    /**
     * Gets the repairCompleteFlag value for this ClaimsServiceRequestData.
     * 
     * @return repairCompleteFlag
     */
    public java.lang.String getRepairCompleteFlag() {
        return repairCompleteFlag;
    }


    /**
     * Sets the repairCompleteFlag value for this ClaimsServiceRequestData.
     * 
     * @param repairCompleteFlag
     */
    public void setRepairCompleteFlag(java.lang.String repairCompleteFlag) {
        this.repairCompleteFlag = repairCompleteFlag;
    }


    /**
     * Gets the debriefInformation value for this ClaimsServiceRequestData.
     * 
     * @return debriefInformation
     */
    public com.lexmark.webServices.warrantyClaimsServiceRequest.client1.SiebelDebriefInformation getDebriefInformation() {
        return debriefInformation;
    }


    /**
     * Sets the debriefInformation value for this ClaimsServiceRequestData.
     * 
     * @param debriefInformation
     */
    public void setDebriefInformation(com.lexmark.webServices.warrantyClaimsServiceRequest.client1.SiebelDebriefInformation debriefInformation) {
        this.debriefInformation = debriefInformation;
    }


    /**
     * Gets the paymentRequestList value for this ClaimsServiceRequestData.
     * 
     * @return paymentRequestList
     */
    public com.lexmark.webServices.warrantyClaimsServiceRequest.client1.SiebelPaymentRequestList[] getPaymentRequestList() {
        return paymentRequestList;
    }


    /**
     * Sets the paymentRequestList value for this ClaimsServiceRequestData.
     * 
     * @param paymentRequestList
     */
    public void setPaymentRequestList(com.lexmark.webServices.warrantyClaimsServiceRequest.client1.SiebelPaymentRequestList[] paymentRequestList) {
        this.paymentRequestList = paymentRequestList;
    }

    public com.lexmark.webServices.warrantyClaimsServiceRequest.client1.SiebelPaymentRequestList getPaymentRequestList(int i) {
        return this.paymentRequestList[i];
    }

    public void setPaymentRequestList(int i, com.lexmark.webServices.warrantyClaimsServiceRequest.client1.SiebelPaymentRequestList _value) {
        this.paymentRequestList[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ClaimsServiceRequestData)) return false;
        ClaimsServiceRequestData other = (ClaimsServiceRequestData) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.claimId==null && other.getClaimId()==null) || 
             (this.claimId!=null &&
              this.claimId.equals(other.getClaimId()))) &&
            ((this.serviceRequestNumber==null && other.getServiceRequestNumber()==null) || 
             (this.serviceRequestNumber!=null &&
              this.serviceRequestNumber.equals(other.getServiceRequestNumber()))) &&
            ((this.activityId==null && other.getActivityId()==null) || 
             (this.activityId!=null &&
              this.activityId.equals(other.getActivityId()))) &&
            ((this.claimDate==null && other.getClaimDate()==null) || 
             (this.claimDate!=null &&
              this.claimDate.equals(other.getClaimDate()))) &&
            ((this.claimStatus==null && other.getClaimStatus()==null) || 
             (this.claimStatus!=null &&
              this.claimStatus.equals(other.getClaimStatus()))) &&
            ((this.claimSource==null && other.getClaimSource()==null) || 
             (this.claimSource!=null &&
              this.claimSource.equals(other.getClaimSource()))) &&
            ((this.serviceRequestType==null && other.getServiceRequestType()==null) || 
             (this.serviceRequestType!=null &&
              this.serviceRequestType.equals(other.getServiceRequestType()))) &&
            ((this.requester==null && other.getRequester()==null) || 
             (this.requester!=null &&
              this.requester.equals(other.getRequester()))) &&
            ((this.primaryContact==null && other.getPrimaryContact()==null) || 
             (this.primaryContact!=null &&
              this.primaryContact.equals(other.getPrimaryContact()))) &&
            ((this.assetInformation==null && other.getAssetInformation()==null) || 
             (this.assetInformation!=null &&
              this.assetInformation.equals(other.getAssetInformation()))) &&
            ((this.serviceProviderInformation==null && other.getServiceProviderInformation()==null) || 
             (this.serviceProviderInformation!=null &&
              this.serviceProviderInformation.equals(other.getServiceProviderInformation()))) &&
            ((this.claimDetails==null && other.getClaimDetails()==null) || 
             (this.claimDetails!=null &&
              this.claimDetails.equals(other.getClaimDetails()))) &&
            ((this.technician==null && other.getTechnician()==null) || 
             (this.technician!=null &&
              this.technician.equals(other.getTechnician()))) &&
            ((this.recommendPartsList==null && other.getRecommendPartsList()==null) || 
             (this.recommendPartsList!=null &&
              java.util.Arrays.equals(this.recommendPartsList, other.getRecommendPartsList()))) &&
            ((this.returnPartsList==null && other.getReturnPartsList()==null) || 
             (this.returnPartsList!=null &&
              java.util.Arrays.equals(this.returnPartsList, other.getReturnPartsList()))) &&
            ((this.activityNotes==null && other.getActivityNotes()==null) || 
             (this.activityNotes!=null &&
              java.util.Arrays.equals(this.activityNotes, other.getActivityNotes()))) &&
            ((this.repairCompleteFlag==null && other.getRepairCompleteFlag()==null) || 
             (this.repairCompleteFlag!=null &&
              this.repairCompleteFlag.equals(other.getRepairCompleteFlag()))) &&
            ((this.debriefInformation==null && other.getDebriefInformation()==null) || 
             (this.debriefInformation!=null &&
              this.debriefInformation.equals(other.getDebriefInformation()))) &&
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
        if (getClaimId() != null) {
            _hashCode += getClaimId().hashCode();
        }
        if (getServiceRequestNumber() != null) {
            _hashCode += getServiceRequestNumber().hashCode();
        }
        if (getActivityId() != null) {
            _hashCode += getActivityId().hashCode();
        }
        if (getClaimDate() != null) {
            _hashCode += getClaimDate().hashCode();
        }
        if (getClaimStatus() != null) {
            _hashCode += getClaimStatus().hashCode();
        }
        if (getClaimSource() != null) {
            _hashCode += getClaimSource().hashCode();
        }
        if (getServiceRequestType() != null) {
            _hashCode += getServiceRequestType().hashCode();
        }
        if (getRequester() != null) {
            _hashCode += getRequester().hashCode();
        }
        if (getPrimaryContact() != null) {
            _hashCode += getPrimaryContact().hashCode();
        }
        if (getAssetInformation() != null) {
            _hashCode += getAssetInformation().hashCode();
        }
        if (getServiceProviderInformation() != null) {
            _hashCode += getServiceProviderInformation().hashCode();
        }
        if (getClaimDetails() != null) {
            _hashCode += getClaimDetails().hashCode();
        }
        if (getTechnician() != null) {
            _hashCode += getTechnician().hashCode();
        }
        if (getRecommendPartsList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getRecommendPartsList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getRecommendPartsList(), i);
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
        if (getRepairCompleteFlag() != null) {
            _hashCode += getRepairCompleteFlag().hashCode();
        }
        if (getDebriefInformation() != null) {
            _hashCode += getDebriefInformation().hashCode();
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
        new org.apache.axis.description.TypeDesc(ClaimsServiceRequestData.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/warrantyClaimsServiceRequestWS", "ClaimsServiceRequestData"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("claimId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ClaimId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serviceRequestNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ServiceRequestNumber"));
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
        elemField.setFieldName("claimDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ClaimDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("claimStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ClaimStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("claimSource");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ClaimSource"));
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
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/warrantyClaimsServiceRequestWS", "SiebelContact"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("primaryContact");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PrimaryContact"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/warrantyClaimsServiceRequestWS", "SiebelContact"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("assetInformation");
        elemField.setXmlName(new javax.xml.namespace.QName("", "AssetInformation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/warrantyClaimsServiceRequestWS", "SiebelAssetInformation"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serviceProviderInformation");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ServiceProviderInformation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/warrantyClaimsServiceRequestWS", "SiebelServiceProviderInformation"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("claimDetails");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ClaimDetails"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/warrantyClaimsServiceRequestWS", "SiebelServiceRequestDetails"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("technician");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Technician"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/warrantyClaimsServiceRequestWS", "SiebelContact"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("recommendPartsList");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RecommendPartsList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/warrantyClaimsServiceRequestWS", "SiebelRecommendedPartsList"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("returnPartsList");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ReturnPartsList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/warrantyClaimsServiceRequestWS", "SiebelReturnPartsList"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("activityNotes");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ActivityNotes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/warrantyClaimsServiceRequestWS", "SiebelActivityNotes"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("repairCompleteFlag");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RepairCompleteFlag"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("debriefInformation");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DebriefInformation"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/warrantyClaimsServiceRequestWS", "SiebelDebriefInformation"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("paymentRequestList");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PaymentRequestList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/warrantyClaimsServiceRequestWS", "SiebelPaymentRequestList"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
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
