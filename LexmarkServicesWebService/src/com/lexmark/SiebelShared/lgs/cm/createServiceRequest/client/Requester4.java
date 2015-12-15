/**
 * Requester4.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SiebelShared.lgs.cm.createServiceRequest.client;

public class Requester4  implements java.io.Serializable {
    private java.lang.String contactId;

    private java.lang.String firstName;

    private java.lang.String lastName;

    private java.lang.String department;

    private java.lang.String workPhone;

    private java.lang.String emailAddress;

    private java.lang.String updateContactFlag;

    private java.lang.String newContactFlag;

    private java.lang.String userFavoriteFlag;

    public Requester4() {
    }

    public Requester4(
           java.lang.String contactId,
           java.lang.String firstName,
           java.lang.String lastName,
           java.lang.String department,
           java.lang.String workPhone,
           java.lang.String emailAddress,
           java.lang.String updateContactFlag,
           java.lang.String newContactFlag,
           java.lang.String userFavoriteFlag) {
           this.contactId = contactId;
           this.firstName = firstName;
           this.lastName = lastName;
           this.department = department;
           this.workPhone = workPhone;
           this.emailAddress = emailAddress;
           this.updateContactFlag = updateContactFlag;
           this.newContactFlag = newContactFlag;
           this.userFavoriteFlag = userFavoriteFlag;
    }


    /**
     * Gets the contactId value for this Requester4.
     * 
     * @return contactId
     */
    public java.lang.String getContactId() {
        return contactId;
    }


    /**
     * Sets the contactId value for this Requester4.
     * 
     * @param contactId
     */
    public void setContactId(java.lang.String contactId) {
        this.contactId = contactId;
    }


    /**
     * Gets the firstName value for this Requester4.
     * 
     * @return firstName
     */
    public java.lang.String getFirstName() {
        return firstName;
    }


    /**
     * Sets the firstName value for this Requester4.
     * 
     * @param firstName
     */
    public void setFirstName(java.lang.String firstName) {
        this.firstName = firstName;
    }


    /**
     * Gets the lastName value for this Requester4.
     * 
     * @return lastName
     */
    public java.lang.String getLastName() {
        return lastName;
    }


    /**
     * Sets the lastName value for this Requester4.
     * 
     * @param lastName
     */
    public void setLastName(java.lang.String lastName) {
        this.lastName = lastName;
    }


    /**
     * Gets the department value for this Requester4.
     * 
     * @return department
     */
    public java.lang.String getDepartment() {
        return department;
    }


    /**
     * Sets the department value for this Requester4.
     * 
     * @param department
     */
    public void setDepartment(java.lang.String department) {
        this.department = department;
    }


    /**
     * Gets the workPhone value for this Requester4.
     * 
     * @return workPhone
     */
    public java.lang.String getWorkPhone() {
        return workPhone;
    }


    /**
     * Sets the workPhone value for this Requester4.
     * 
     * @param workPhone
     */
    public void setWorkPhone(java.lang.String workPhone) {
        this.workPhone = workPhone;
    }


    /**
     * Gets the emailAddress value for this Requester4.
     * 
     * @return emailAddress
     */
    public java.lang.String getEmailAddress() {
        return emailAddress;
    }


    /**
     * Sets the emailAddress value for this Requester4.
     * 
     * @param emailAddress
     */
    public void setEmailAddress(java.lang.String emailAddress) {
        this.emailAddress = emailAddress;
    }


    /**
     * Gets the updateContactFlag value for this Requester4.
     * 
     * @return updateContactFlag
     */
    public java.lang.String getUpdateContactFlag() {
        return updateContactFlag;
    }


    /**
     * Sets the updateContactFlag value for this Requester4.
     * 
     * @param updateContactFlag
     */
    public void setUpdateContactFlag(java.lang.String updateContactFlag) {
        this.updateContactFlag = updateContactFlag;
    }


    /**
     * Gets the newContactFlag value for this Requester4.
     * 
     * @return newContactFlag
     */
    public java.lang.String getNewContactFlag() {
        return newContactFlag;
    }


    /**
     * Sets the newContactFlag value for this Requester4.
     * 
     * @param newContactFlag
     */
    public void setNewContactFlag(java.lang.String newContactFlag) {
        this.newContactFlag = newContactFlag;
    }


    /**
     * Gets the userFavoriteFlag value for this Requester4.
     * 
     * @return userFavoriteFlag
     */
    public java.lang.String getUserFavoriteFlag() {
        return userFavoriteFlag;
    }


    /**
     * Sets the userFavoriteFlag value for this Requester4.
     * 
     * @param userFavoriteFlag
     */
    public void setUserFavoriteFlag(java.lang.String userFavoriteFlag) {
        this.userFavoriteFlag = userFavoriteFlag;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Requester4)) return false;
        Requester4 other = (Requester4) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.contactId==null && other.getContactId()==null) || 
             (this.contactId!=null &&
              this.contactId.equals(other.getContactId()))) &&
            ((this.firstName==null && other.getFirstName()==null) || 
             (this.firstName!=null &&
              this.firstName.equals(other.getFirstName()))) &&
            ((this.lastName==null && other.getLastName()==null) || 
             (this.lastName!=null &&
              this.lastName.equals(other.getLastName()))) &&
            ((this.department==null && other.getDepartment()==null) || 
             (this.department!=null &&
              this.department.equals(other.getDepartment()))) &&
            ((this.workPhone==null && other.getWorkPhone()==null) || 
             (this.workPhone!=null &&
              this.workPhone.equals(other.getWorkPhone()))) &&
            ((this.emailAddress==null && other.getEmailAddress()==null) || 
             (this.emailAddress!=null &&
              this.emailAddress.equals(other.getEmailAddress()))) &&
            ((this.updateContactFlag==null && other.getUpdateContactFlag()==null) || 
             (this.updateContactFlag!=null &&
              this.updateContactFlag.equals(other.getUpdateContactFlag()))) &&
            ((this.newContactFlag==null && other.getNewContactFlag()==null) || 
             (this.newContactFlag!=null &&
              this.newContactFlag.equals(other.getNewContactFlag()))) &&
            ((this.userFavoriteFlag==null && other.getUserFavoriteFlag()==null) || 
             (this.userFavoriteFlag!=null &&
              this.userFavoriteFlag.equals(other.getUserFavoriteFlag())));
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
        if (getContactId() != null) {
            _hashCode += getContactId().hashCode();
        }
        if (getFirstName() != null) {
            _hashCode += getFirstName().hashCode();
        }
        if (getLastName() != null) {
            _hashCode += getLastName().hashCode();
        }
        if (getDepartment() != null) {
            _hashCode += getDepartment().hashCode();
        }
        if (getWorkPhone() != null) {
            _hashCode += getWorkPhone().hashCode();
        }
        if (getEmailAddress() != null) {
            _hashCode += getEmailAddress().hashCode();
        }
        if (getUpdateContactFlag() != null) {
            _hashCode += getUpdateContactFlag().hashCode();
        }
        if (getNewContactFlag() != null) {
            _hashCode += getNewContactFlag().hashCode();
        }
        if (getUserFavoriteFlag() != null) {
            _hashCode += getUserFavoriteFlag().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Requester4.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "Requester4"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contactId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ContactId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("firstName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "FirstName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lastName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "LastName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("department");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Department"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("workPhone");
        elemField.setXmlName(new javax.xml.namespace.QName("", "WorkPhone"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("emailAddress");
        elemField.setXmlName(new javax.xml.namespace.QName("", "EmailAddress"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("updateContactFlag");
        elemField.setXmlName(new javax.xml.namespace.QName("", "UpdateContactFlag"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("newContactFlag");
        elemField.setXmlName(new javax.xml.namespace.QName("", "NewContactFlag"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("userFavoriteFlag");
        elemField.setXmlName(new javax.xml.namespace.QName("", "UserFavoriteFlag"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
