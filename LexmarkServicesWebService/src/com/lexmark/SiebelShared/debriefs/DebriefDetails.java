/**
 * DebriefDetails.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SiebelShared.debriefs;

public class DebriefDetails  implements java.io.Serializable {
    private java.lang.String actualStartDate;

    private java.lang.String actualEndDate;

    private java.lang.String debriefStatus;

    private java.lang.String technicianName;

    private java.lang.String technicianComments;

    private java.lang.String travelDistance;

    private java.lang.String travelDistanceUnitOfMeasure;

    private java.lang.String travelDuration;

    private java.lang.String travelDurationUnitOfMeasure;

    private java.lang.String responseMetricUnitOfMeasure;

    private java.lang.String additionalServiceRequiredFlag;

    private java.lang.String assetStoredDate;

    private java.lang.String moveToAddressComment;

    private com.lexmark.SiebelShared.debriefs.RecommendedPartsList[] recommendedPartsList;

    private com.lexmark.SiebelShared.debriefs.AdditionalPartsList[] additionalPartsList;

    private com.lexmark.SiebelShared.debriefs.AdditionalExpenses[] additionalExpenses;

    private com.lexmark.SiebelShared.debriefs.SiebelPaymentRequestList[] paymentRequestsList;

    public DebriefDetails() {
    }

    public DebriefDetails(
           java.lang.String actualStartDate,
           java.lang.String actualEndDate,
           java.lang.String debriefStatus,
           java.lang.String technicianName,
           java.lang.String technicianComments,
           java.lang.String travelDistance,
           java.lang.String travelDistanceUnitOfMeasure,
           java.lang.String travelDuration,
           java.lang.String travelDurationUnitOfMeasure,
           java.lang.String responseMetricUnitOfMeasure,
           java.lang.String additionalServiceRequiredFlag,
           java.lang.String assetStoredDate,
           java.lang.String moveToAddressComment,
           com.lexmark.SiebelShared.debriefs.RecommendedPartsList[] recommendedPartsList,
           com.lexmark.SiebelShared.debriefs.AdditionalPartsList[] additionalPartsList,
           com.lexmark.SiebelShared.debriefs.AdditionalExpenses[] additionalExpenses,
           com.lexmark.SiebelShared.debriefs.SiebelPaymentRequestList[] paymentRequestsList) {
           this.actualStartDate = actualStartDate;
           this.actualEndDate = actualEndDate;
           this.debriefStatus = debriefStatus;
           this.technicianName = technicianName;
           this.technicianComments = technicianComments;
           this.travelDistance = travelDistance;
           this.travelDistanceUnitOfMeasure = travelDistanceUnitOfMeasure;
           this.travelDuration = travelDuration;
           this.travelDurationUnitOfMeasure = travelDurationUnitOfMeasure;
           this.responseMetricUnitOfMeasure = responseMetricUnitOfMeasure;
           this.additionalServiceRequiredFlag = additionalServiceRequiredFlag;
           this.assetStoredDate = assetStoredDate;
           this.moveToAddressComment = moveToAddressComment;
           this.recommendedPartsList = recommendedPartsList;
           this.additionalPartsList = additionalPartsList;
           this.additionalExpenses = additionalExpenses;
           this.paymentRequestsList = paymentRequestsList;
    }


    /**
     * Gets the actualStartDate value for this DebriefDetails.
     * 
     * @return actualStartDate
     */
    public java.lang.String getActualStartDate() {
        return actualStartDate;
    }


    /**
     * Sets the actualStartDate value for this DebriefDetails.
     * 
     * @param actualStartDate
     */
    public void setActualStartDate(java.lang.String actualStartDate) {
        this.actualStartDate = actualStartDate;
    }


    /**
     * Gets the actualEndDate value for this DebriefDetails.
     * 
     * @return actualEndDate
     */
    public java.lang.String getActualEndDate() {
        return actualEndDate;
    }


    /**
     * Sets the actualEndDate value for this DebriefDetails.
     * 
     * @param actualEndDate
     */
    public void setActualEndDate(java.lang.String actualEndDate) {
        this.actualEndDate = actualEndDate;
    }


    /**
     * Gets the debriefStatus value for this DebriefDetails.
     * 
     * @return debriefStatus
     */
    public java.lang.String getDebriefStatus() {
        return debriefStatus;
    }


    /**
     * Sets the debriefStatus value for this DebriefDetails.
     * 
     * @param debriefStatus
     */
    public void setDebriefStatus(java.lang.String debriefStatus) {
        this.debriefStatus = debriefStatus;
    }


    /**
     * Gets the technicianName value for this DebriefDetails.
     * 
     * @return technicianName
     */
    public java.lang.String getTechnicianName() {
        return technicianName;
    }


    /**
     * Sets the technicianName value for this DebriefDetails.
     * 
     * @param technicianName
     */
    public void setTechnicianName(java.lang.String technicianName) {
        this.technicianName = technicianName;
    }


    /**
     * Gets the technicianComments value for this DebriefDetails.
     * 
     * @return technicianComments
     */
    public java.lang.String getTechnicianComments() {
        return technicianComments;
    }


    /**
     * Sets the technicianComments value for this DebriefDetails.
     * 
     * @param technicianComments
     */
    public void setTechnicianComments(java.lang.String technicianComments) {
        this.technicianComments = technicianComments;
    }


    /**
     * Gets the travelDistance value for this DebriefDetails.
     * 
     * @return travelDistance
     */
    public java.lang.String getTravelDistance() {
        return travelDistance;
    }


    /**
     * Sets the travelDistance value for this DebriefDetails.
     * 
     * @param travelDistance
     */
    public void setTravelDistance(java.lang.String travelDistance) {
        this.travelDistance = travelDistance;
    }


    /**
     * Gets the travelDistanceUnitOfMeasure value for this DebriefDetails.
     * 
     * @return travelDistanceUnitOfMeasure
     */
    public java.lang.String getTravelDistanceUnitOfMeasure() {
        return travelDistanceUnitOfMeasure;
    }


    /**
     * Sets the travelDistanceUnitOfMeasure value for this DebriefDetails.
     * 
     * @param travelDistanceUnitOfMeasure
     */
    public void setTravelDistanceUnitOfMeasure(java.lang.String travelDistanceUnitOfMeasure) {
        this.travelDistanceUnitOfMeasure = travelDistanceUnitOfMeasure;
    }


    /**
     * Gets the travelDuration value for this DebriefDetails.
     * 
     * @return travelDuration
     */
    public java.lang.String getTravelDuration() {
        return travelDuration;
    }


    /**
     * Sets the travelDuration value for this DebriefDetails.
     * 
     * @param travelDuration
     */
    public void setTravelDuration(java.lang.String travelDuration) {
        this.travelDuration = travelDuration;
    }


    /**
     * Gets the travelDurationUnitOfMeasure value for this DebriefDetails.
     * 
     * @return travelDurationUnitOfMeasure
     */
    public java.lang.String getTravelDurationUnitOfMeasure() {
        return travelDurationUnitOfMeasure;
    }


    /**
     * Sets the travelDurationUnitOfMeasure value for this DebriefDetails.
     * 
     * @param travelDurationUnitOfMeasure
     */
    public void setTravelDurationUnitOfMeasure(java.lang.String travelDurationUnitOfMeasure) {
        this.travelDurationUnitOfMeasure = travelDurationUnitOfMeasure;
    }


    /**
     * Gets the responseMetricUnitOfMeasure value for this DebriefDetails.
     * 
     * @return responseMetricUnitOfMeasure
     */
    public java.lang.String getResponseMetricUnitOfMeasure() {
        return responseMetricUnitOfMeasure;
    }


    /**
     * Sets the responseMetricUnitOfMeasure value for this DebriefDetails.
     * 
     * @param responseMetricUnitOfMeasure
     */
    public void setResponseMetricUnitOfMeasure(java.lang.String responseMetricUnitOfMeasure) {
        this.responseMetricUnitOfMeasure = responseMetricUnitOfMeasure;
    }


    /**
     * Gets the additionalServiceRequiredFlag value for this DebriefDetails.
     * 
     * @return additionalServiceRequiredFlag
     */
    public java.lang.String getAdditionalServiceRequiredFlag() {
        return additionalServiceRequiredFlag;
    }


    /**
     * Sets the additionalServiceRequiredFlag value for this DebriefDetails.
     * 
     * @param additionalServiceRequiredFlag
     */
    public void setAdditionalServiceRequiredFlag(java.lang.String additionalServiceRequiredFlag) {
        this.additionalServiceRequiredFlag = additionalServiceRequiredFlag;
    }


    /**
     * Gets the assetStoredDate value for this DebriefDetails.
     * 
     * @return assetStoredDate
     */
    public java.lang.String getAssetStoredDate() {
        return assetStoredDate;
    }


    /**
     * Sets the assetStoredDate value for this DebriefDetails.
     * 
     * @param assetStoredDate
     */
    public void setAssetStoredDate(java.lang.String assetStoredDate) {
        this.assetStoredDate = assetStoredDate;
    }


    /**
     * Gets the moveToAddressComment value for this DebriefDetails.
     * 
     * @return moveToAddressComment
     */
    public java.lang.String getMoveToAddressComment() {
        return moveToAddressComment;
    }


    /**
     * Sets the moveToAddressComment value for this DebriefDetails.
     * 
     * @param moveToAddressComment
     */
    public void setMoveToAddressComment(java.lang.String moveToAddressComment) {
        this.moveToAddressComment = moveToAddressComment;
    }


    /**
     * Gets the recommendedPartsList value for this DebriefDetails.
     * 
     * @return recommendedPartsList
     */
    public com.lexmark.SiebelShared.debriefs.RecommendedPartsList[] getRecommendedPartsList() {
        return recommendedPartsList;
    }


    /**
     * Sets the recommendedPartsList value for this DebriefDetails.
     * 
     * @param recommendedPartsList
     */
    public void setRecommendedPartsList(com.lexmark.SiebelShared.debriefs.RecommendedPartsList[] recommendedPartsList) {
        this.recommendedPartsList = recommendedPartsList;
    }

    public com.lexmark.SiebelShared.debriefs.RecommendedPartsList getRecommendedPartsList(int i) {
        return this.recommendedPartsList[i];
    }

    public void setRecommendedPartsList(int i, com.lexmark.SiebelShared.debriefs.RecommendedPartsList _value) {
        this.recommendedPartsList[i] = _value;
    }


    /**
     * Gets the additionalPartsList value for this DebriefDetails.
     * 
     * @return additionalPartsList
     */
    public com.lexmark.SiebelShared.debriefs.AdditionalPartsList[] getAdditionalPartsList() {
        return additionalPartsList;
    }


    /**
     * Sets the additionalPartsList value for this DebriefDetails.
     * 
     * @param additionalPartsList
     */
    public void setAdditionalPartsList(com.lexmark.SiebelShared.debriefs.AdditionalPartsList[] additionalPartsList) {
        this.additionalPartsList = additionalPartsList;
    }

    public com.lexmark.SiebelShared.debriefs.AdditionalPartsList getAdditionalPartsList(int i) {
        return this.additionalPartsList[i];
    }

    public void setAdditionalPartsList(int i, com.lexmark.SiebelShared.debriefs.AdditionalPartsList _value) {
        this.additionalPartsList[i] = _value;
    }


    /**
     * Gets the additionalExpenses value for this DebriefDetails.
     * 
     * @return additionalExpenses
     */
    public com.lexmark.SiebelShared.debriefs.AdditionalExpenses[] getAdditionalExpenses() {
        return additionalExpenses;
    }


    /**
     * Sets the additionalExpenses value for this DebriefDetails.
     * 
     * @param additionalExpenses
     */
    public void setAdditionalExpenses(com.lexmark.SiebelShared.debriefs.AdditionalExpenses[] additionalExpenses) {
        this.additionalExpenses = additionalExpenses;
    }

    public com.lexmark.SiebelShared.debriefs.AdditionalExpenses getAdditionalExpenses(int i) {
        return this.additionalExpenses[i];
    }

    public void setAdditionalExpenses(int i, com.lexmark.SiebelShared.debriefs.AdditionalExpenses _value) {
        this.additionalExpenses[i] = _value;
    }


    /**
     * Gets the paymentRequestsList value for this DebriefDetails.
     * 
     * @return paymentRequestsList
     */
    public com.lexmark.SiebelShared.debriefs.SiebelPaymentRequestList[] getPaymentRequestsList() {
        return paymentRequestsList;
    }


    /**
     * Sets the paymentRequestsList value for this DebriefDetails.
     * 
     * @param paymentRequestsList
     */
    public void setPaymentRequestsList(com.lexmark.SiebelShared.debriefs.SiebelPaymentRequestList[] paymentRequestsList) {
        this.paymentRequestsList = paymentRequestsList;
    }

    public com.lexmark.SiebelShared.debriefs.SiebelPaymentRequestList getPaymentRequestsList(int i) {
        return this.paymentRequestsList[i];
    }

    public void setPaymentRequestsList(int i, com.lexmark.SiebelShared.debriefs.SiebelPaymentRequestList _value) {
        this.paymentRequestsList[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DebriefDetails)) return false;
        DebriefDetails other = (DebriefDetails) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.actualStartDate==null && other.getActualStartDate()==null) || 
             (this.actualStartDate!=null &&
              this.actualStartDate.equals(other.getActualStartDate()))) &&
            ((this.actualEndDate==null && other.getActualEndDate()==null) || 
             (this.actualEndDate!=null &&
              this.actualEndDate.equals(other.getActualEndDate()))) &&
            ((this.debriefStatus==null && other.getDebriefStatus()==null) || 
             (this.debriefStatus!=null &&
              this.debriefStatus.equals(other.getDebriefStatus()))) &&
            ((this.technicianName==null && other.getTechnicianName()==null) || 
             (this.technicianName!=null &&
              this.technicianName.equals(other.getTechnicianName()))) &&
            ((this.technicianComments==null && other.getTechnicianComments()==null) || 
             (this.technicianComments!=null &&
              this.technicianComments.equals(other.getTechnicianComments()))) &&
            ((this.travelDistance==null && other.getTravelDistance()==null) || 
             (this.travelDistance!=null &&
              this.travelDistance.equals(other.getTravelDistance()))) &&
            ((this.travelDistanceUnitOfMeasure==null && other.getTravelDistanceUnitOfMeasure()==null) || 
             (this.travelDistanceUnitOfMeasure!=null &&
              this.travelDistanceUnitOfMeasure.equals(other.getTravelDistanceUnitOfMeasure()))) &&
            ((this.travelDuration==null && other.getTravelDuration()==null) || 
             (this.travelDuration!=null &&
              this.travelDuration.equals(other.getTravelDuration()))) &&
            ((this.travelDurationUnitOfMeasure==null && other.getTravelDurationUnitOfMeasure()==null) || 
             (this.travelDurationUnitOfMeasure!=null &&
              this.travelDurationUnitOfMeasure.equals(other.getTravelDurationUnitOfMeasure()))) &&
            ((this.responseMetricUnitOfMeasure==null && other.getResponseMetricUnitOfMeasure()==null) || 
             (this.responseMetricUnitOfMeasure!=null &&
              this.responseMetricUnitOfMeasure.equals(other.getResponseMetricUnitOfMeasure()))) &&
            ((this.additionalServiceRequiredFlag==null && other.getAdditionalServiceRequiredFlag()==null) || 
             (this.additionalServiceRequiredFlag!=null &&
              this.additionalServiceRequiredFlag.equals(other.getAdditionalServiceRequiredFlag()))) &&
            ((this.assetStoredDate==null && other.getAssetStoredDate()==null) || 
             (this.assetStoredDate!=null &&
              this.assetStoredDate.equals(other.getAssetStoredDate()))) &&
            ((this.moveToAddressComment==null && other.getMoveToAddressComment()==null) || 
             (this.moveToAddressComment!=null &&
              this.moveToAddressComment.equals(other.getMoveToAddressComment()))) &&
            ((this.recommendedPartsList==null && other.getRecommendedPartsList()==null) || 
             (this.recommendedPartsList!=null &&
              java.util.Arrays.equals(this.recommendedPartsList, other.getRecommendedPartsList()))) &&
            ((this.additionalPartsList==null && other.getAdditionalPartsList()==null) || 
             (this.additionalPartsList!=null &&
              java.util.Arrays.equals(this.additionalPartsList, other.getAdditionalPartsList()))) &&
            ((this.additionalExpenses==null && other.getAdditionalExpenses()==null) || 
             (this.additionalExpenses!=null &&
              java.util.Arrays.equals(this.additionalExpenses, other.getAdditionalExpenses()))) &&
            ((this.paymentRequestsList==null && other.getPaymentRequestsList()==null) || 
             (this.paymentRequestsList!=null &&
              java.util.Arrays.equals(this.paymentRequestsList, other.getPaymentRequestsList())));
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
        if (getActualStartDate() != null) {
            _hashCode += getActualStartDate().hashCode();
        }
        if (getActualEndDate() != null) {
            _hashCode += getActualEndDate().hashCode();
        }
        if (getDebriefStatus() != null) {
            _hashCode += getDebriefStatus().hashCode();
        }
        if (getTechnicianName() != null) {
            _hashCode += getTechnicianName().hashCode();
        }
        if (getTechnicianComments() != null) {
            _hashCode += getTechnicianComments().hashCode();
        }
        if (getTravelDistance() != null) {
            _hashCode += getTravelDistance().hashCode();
        }
        if (getTravelDistanceUnitOfMeasure() != null) {
            _hashCode += getTravelDistanceUnitOfMeasure().hashCode();
        }
        if (getTravelDuration() != null) {
            _hashCode += getTravelDuration().hashCode();
        }
        if (getTravelDurationUnitOfMeasure() != null) {
            _hashCode += getTravelDurationUnitOfMeasure().hashCode();
        }
        if (getResponseMetricUnitOfMeasure() != null) {
            _hashCode += getResponseMetricUnitOfMeasure().hashCode();
        }
        if (getAdditionalServiceRequiredFlag() != null) {
            _hashCode += getAdditionalServiceRequiredFlag().hashCode();
        }
        if (getAssetStoredDate() != null) {
            _hashCode += getAssetStoredDate().hashCode();
        }
        if (getMoveToAddressComment() != null) {
            _hashCode += getMoveToAddressComment().hashCode();
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
        if (getAdditionalPartsList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAdditionalPartsList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAdditionalPartsList(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getAdditionalExpenses() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAdditionalExpenses());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAdditionalExpenses(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getPaymentRequestsList() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getPaymentRequestsList());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getPaymentRequestsList(), i);
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
        new org.apache.axis.description.TypeDesc(DebriefDetails.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dashrwmis002.lex.lexmark.com/LXKAssetManagementStatus.webServices.provider:AssetManagementStatus", "DebriefDetails"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("actualStartDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ActualStartDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("actualEndDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ActualEndDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("debriefStatus");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DebriefStatus"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("technicianName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TechnicianName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("technicianComments");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TechnicianComments"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("travelDistance");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TravelDistance"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("travelDistanceUnitOfMeasure");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TravelDistanceUnitOfMeasure"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("travelDuration");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TravelDuration"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("travelDurationUnitOfMeasure");
        elemField.setXmlName(new javax.xml.namespace.QName("", "TravelDurationUnitOfMeasure"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("responseMetricUnitOfMeasure");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ResponseMetricUnitOfMeasure"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("additionalServiceRequiredFlag");
        elemField.setXmlName(new javax.xml.namespace.QName("", "AdditionalServiceRequiredFlag"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("assetStoredDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "AssetStoredDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("moveToAddressComment");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MoveToAddressComment"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("recommendedPartsList");
        elemField.setXmlName(new javax.xml.namespace.QName("", "RecommendedPartsList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dashrwmis002.lex.lexmark.com/LXKAssetManagementStatus.webServices.provider:AssetManagementStatus", "RecommendedPartsList"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("additionalPartsList");
        elemField.setXmlName(new javax.xml.namespace.QName("", "AdditionalPartsList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dashrwmis002.lex.lexmark.com/LXKAssetManagementStatus.webServices.provider:AssetManagementStatus", "AdditionalPartsList"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("additionalExpenses");
        elemField.setXmlName(new javax.xml.namespace.QName("", "AdditionalExpenses"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dashrwmis002.lex.lexmark.com/LXKAssetManagementStatus.webServices.provider:AssetManagementStatus", "AdditionalExpenses"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("paymentRequestsList");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PaymentRequestsList"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dashrwmis002.lex.lexmark.com/LXKAssetManagementStatus.webServices.provider:AssetManagementStatus", "SiebelPaymentRequestList"));
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
