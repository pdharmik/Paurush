/**
 * Data.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.webServices.processCustomerContactCM;

public class Data  implements java.io.Serializable {
    private java.lang.String contactId;

    private java.lang.String customerName;

    private java.lang.String customerMDMId;

    private java.lang.String MDMCustomerLevel;

    private java.lang.String employeeId;

    private java.lang.String contactType;

    private java.lang.String firstName;

    private java.lang.String lastName;

    private java.lang.String emailAddress;

    private java.lang.String workPhoneNumber;

    private java.lang.String homePhoneNumber;

    private java.lang.String faxNumber;

    private java.lang.String mobileNumber;

    private java.lang.String alternatePhoneNumber;

    private java.lang.String language;

    private java.lang.String contactReferenceId;

    private com.lexmark.webServices.processCustomerContactCM.PhysicalAddress physicalAddress;

    private com.lexmark.webServices.processCustomerContactCM.Roles[] roles;

    public Data() {
    }

    public Data(
           java.lang.String contactId,
           java.lang.String customerName,
           java.lang.String customerMDMId,
           java.lang.String MDMCustomerLevel,
           java.lang.String employeeId,
           java.lang.String contactType,
           java.lang.String firstName,
           java.lang.String lastName,
           java.lang.String emailAddress,
           java.lang.String workPhoneNumber,
           java.lang.String homePhoneNumber,
           java.lang.String faxNumber,
           java.lang.String mobileNumber,
           java.lang.String alternatePhoneNumber,
           java.lang.String language,
           java.lang.String contactReferenceId,
           com.lexmark.webServices.processCustomerContactCM.PhysicalAddress physicalAddress,
           com.lexmark.webServices.processCustomerContactCM.Roles[] roles) {
           this.contactId = contactId;
           this.customerName = customerName;
           this.customerMDMId = customerMDMId;
           this.MDMCustomerLevel = MDMCustomerLevel;
           this.employeeId = employeeId;
           this.contactType = contactType;
           this.firstName = firstName;
           this.lastName = lastName;
           this.emailAddress = emailAddress;
           this.workPhoneNumber = workPhoneNumber;
           this.homePhoneNumber = homePhoneNumber;
           this.faxNumber = faxNumber;
           this.mobileNumber = mobileNumber;
           this.alternatePhoneNumber = alternatePhoneNumber;
           this.language = language;
           this.contactReferenceId = contactReferenceId;
           this.physicalAddress = physicalAddress;
           this.roles = roles;
    }


    /**
     * Gets the contactId value for this Data.
     * 
     * @return contactId
     */
    public java.lang.String getContactId() {
        return contactId;
    }


    /**
     * Sets the contactId value for this Data.
     * 
     * @param contactId
     */
    public void setContactId(java.lang.String contactId) {
        this.contactId = contactId;
    }


    /**
     * Gets the customerName value for this Data.
     * 
     * @return customerName
     */
    public java.lang.String getCustomerName() {
        return customerName;
    }


    /**
     * Sets the customerName value for this Data.
     * 
     * @param customerName
     */
    public void setCustomerName(java.lang.String customerName) {
        this.customerName = customerName;
    }


    /**
     * Gets the customerMDMId value for this Data.
     * 
     * @return customerMDMId
     */
    public java.lang.String getCustomerMDMId() {
        return customerMDMId;
    }


    /**
     * Sets the customerMDMId value for this Data.
     * 
     * @param customerMDMId
     */
    public void setCustomerMDMId(java.lang.String customerMDMId) {
        this.customerMDMId = customerMDMId;
    }


    /**
     * Gets the MDMCustomerLevel value for this Data.
     * 
     * @return MDMCustomerLevel
     */
    public java.lang.String getMDMCustomerLevel() {
        return MDMCustomerLevel;
    }


    /**
     * Sets the MDMCustomerLevel value for this Data.
     * 
     * @param MDMCustomerLevel
     */
    public void setMDMCustomerLevel(java.lang.String MDMCustomerLevel) {
        this.MDMCustomerLevel = MDMCustomerLevel;
    }


    /**
     * Gets the employeeId value for this Data.
     * 
     * @return employeeId
     */
    public java.lang.String getEmployeeId() {
        return employeeId;
    }


    /**
     * Sets the employeeId value for this Data.
     * 
     * @param employeeId
     */
    public void setEmployeeId(java.lang.String employeeId) {
        this.employeeId = employeeId;
    }


    /**
     * Gets the contactType value for this Data.
     * 
     * @return contactType
     */
    public java.lang.String getContactType() {
        return contactType;
    }


    /**
     * Sets the contactType value for this Data.
     * 
     * @param contactType
     */
    public void setContactType(java.lang.String contactType) {
        this.contactType = contactType;
    }


    /**
     * Gets the firstName value for this Data.
     * 
     * @return firstName
     */
    public java.lang.String getFirstName() {
        return firstName;
    }


    /**
     * Sets the firstName value for this Data.
     * 
     * @param firstName
     */
    public void setFirstName(java.lang.String firstName) {
        this.firstName = firstName;
    }


    /**
     * Gets the lastName value for this Data.
     * 
     * @return lastName
     */
    public java.lang.String getLastName() {
        return lastName;
    }


    /**
     * Sets the lastName value for this Data.
     * 
     * @param lastName
     */
    public void setLastName(java.lang.String lastName) {
        this.lastName = lastName;
    }


    /**
     * Gets the emailAddress value for this Data.
     * 
     * @return emailAddress
     */
    public java.lang.String getEmailAddress() {
        return emailAddress;
    }


    /**
     * Sets the emailAddress value for this Data.
     * 
     * @param emailAddress
     */
    public void setEmailAddress(java.lang.String emailAddress) {
        this.emailAddress = emailAddress;
    }


    /**
     * Gets the workPhoneNumber value for this Data.
     * 
     * @return workPhoneNumber
     */
    public java.lang.String getWorkPhoneNumber() {
        return workPhoneNumber;
    }


    /**
     * Sets the workPhoneNumber value for this Data.
     * 
     * @param workPhoneNumber
     */
    public void setWorkPhoneNumber(java.lang.String workPhoneNumber) {
        this.workPhoneNumber = workPhoneNumber;
    }


    /**
     * Gets the homePhoneNumber value for this Data.
     * 
     * @return homePhoneNumber
     */
    public java.lang.String getHomePhoneNumber() {
        return homePhoneNumber;
    }


    /**
     * Sets the homePhoneNumber value for this Data.
     * 
     * @param homePhoneNumber
     */
    public void setHomePhoneNumber(java.lang.String homePhoneNumber) {
        this.homePhoneNumber = homePhoneNumber;
    }


    /**
     * Gets the faxNumber value for this Data.
     * 
     * @return faxNumber
     */
    public java.lang.String getFaxNumber() {
        return faxNumber;
    }


    /**
     * Sets the faxNumber value for this Data.
     * 
     * @param faxNumber
     */
    public void setFaxNumber(java.lang.String faxNumber) {
        this.faxNumber = faxNumber;
    }


    /**
     * Gets the mobileNumber value for this Data.
     * 
     * @return mobileNumber
     */
    public java.lang.String getMobileNumber() {
        return mobileNumber;
    }


    /**
     * Sets the mobileNumber value for this Data.
     * 
     * @param mobileNumber
     */
    public void setMobileNumber(java.lang.String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }


    /**
     * Gets the alternatePhoneNumber value for this Data.
     * 
     * @return alternatePhoneNumber
     */
    public java.lang.String getAlternatePhoneNumber() {
        return alternatePhoneNumber;
    }


    /**
     * Sets the alternatePhoneNumber value for this Data.
     * 
     * @param alternatePhoneNumber
     */
    public void setAlternatePhoneNumber(java.lang.String alternatePhoneNumber) {
        this.alternatePhoneNumber = alternatePhoneNumber;
    }


    /**
     * Gets the language value for this Data.
     * 
     * @return language
     */
    public java.lang.String getLanguage() {
        return language;
    }


    /**
     * Sets the language value for this Data.
     * 
     * @param language
     */
    public void setLanguage(java.lang.String language) {
        this.language = language;
    }


    /**
     * Gets the contactReferenceId value for this Data.
     * 
     * @return contactReferenceId
     */
    public java.lang.String getContactReferenceId() {
        return contactReferenceId;
    }


    /**
     * Sets the contactReferenceId value for this Data.
     * 
     * @param contactReferenceId
     */
    public void setContactReferenceId(java.lang.String contactReferenceId) {
        this.contactReferenceId = contactReferenceId;
    }


    /**
     * Gets the physicalAddress value for this Data.
     * 
     * @return physicalAddress
     */
    public com.lexmark.webServices.processCustomerContactCM.PhysicalAddress getPhysicalAddress() {
        return physicalAddress;
    }


    /**
     * Sets the physicalAddress value for this Data.
     * 
     * @param physicalAddress
     */
    public void setPhysicalAddress(com.lexmark.webServices.processCustomerContactCM.PhysicalAddress physicalAddress) {
        this.physicalAddress = physicalAddress;
    }


    /**
     * Gets the roles value for this Data.
     * 
     * @return roles
     */
    public com.lexmark.webServices.processCustomerContactCM.Roles[] getRoles() {
        return roles;
    }


    /**
     * Sets the roles value for this Data.
     * 
     * @param roles
     */
    public void setRoles(com.lexmark.webServices.processCustomerContactCM.Roles[] roles) {
        this.roles = roles;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Data)) return false;
        Data other = (Data) obj;
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
            ((this.customerName==null && other.getCustomerName()==null) || 
             (this.customerName!=null &&
              this.customerName.equals(other.getCustomerName()))) &&
            ((this.customerMDMId==null && other.getCustomerMDMId()==null) || 
             (this.customerMDMId!=null &&
              this.customerMDMId.equals(other.getCustomerMDMId()))) &&
            ((this.MDMCustomerLevel==null && other.getMDMCustomerLevel()==null) || 
             (this.MDMCustomerLevel!=null &&
              this.MDMCustomerLevel.equals(other.getMDMCustomerLevel()))) &&
            ((this.employeeId==null && other.getEmployeeId()==null) || 
             (this.employeeId!=null &&
              this.employeeId.equals(other.getEmployeeId()))) &&
            ((this.contactType==null && other.getContactType()==null) || 
             (this.contactType!=null &&
              this.contactType.equals(other.getContactType()))) &&
            ((this.firstName==null && other.getFirstName()==null) || 
             (this.firstName!=null &&
              this.firstName.equals(other.getFirstName()))) &&
            ((this.lastName==null && other.getLastName()==null) || 
             (this.lastName!=null &&
              this.lastName.equals(other.getLastName()))) &&
            ((this.emailAddress==null && other.getEmailAddress()==null) || 
             (this.emailAddress!=null &&
              this.emailAddress.equals(other.getEmailAddress()))) &&
            ((this.workPhoneNumber==null && other.getWorkPhoneNumber()==null) || 
             (this.workPhoneNumber!=null &&
              this.workPhoneNumber.equals(other.getWorkPhoneNumber()))) &&
            ((this.homePhoneNumber==null && other.getHomePhoneNumber()==null) || 
             (this.homePhoneNumber!=null &&
              this.homePhoneNumber.equals(other.getHomePhoneNumber()))) &&
            ((this.faxNumber==null && other.getFaxNumber()==null) || 
             (this.faxNumber!=null &&
              this.faxNumber.equals(other.getFaxNumber()))) &&
            ((this.mobileNumber==null && other.getMobileNumber()==null) || 
             (this.mobileNumber!=null &&
              this.mobileNumber.equals(other.getMobileNumber()))) &&
            ((this.alternatePhoneNumber==null && other.getAlternatePhoneNumber()==null) || 
             (this.alternatePhoneNumber!=null &&
              this.alternatePhoneNumber.equals(other.getAlternatePhoneNumber()))) &&
            ((this.language==null && other.getLanguage()==null) || 
             (this.language!=null &&
              this.language.equals(other.getLanguage()))) &&
            ((this.contactReferenceId==null && other.getContactReferenceId()==null) || 
             (this.contactReferenceId!=null &&
              this.contactReferenceId.equals(other.getContactReferenceId()))) &&
            ((this.physicalAddress==null && other.getPhysicalAddress()==null) || 
             (this.physicalAddress!=null &&
              this.physicalAddress.equals(other.getPhysicalAddress()))) &&
            ((this.roles==null && other.getRoles()==null) || 
             (this.roles!=null &&
              java.util.Arrays.equals(this.roles, other.getRoles())));
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
        if (getCustomerName() != null) {
            _hashCode += getCustomerName().hashCode();
        }
        if (getCustomerMDMId() != null) {
            _hashCode += getCustomerMDMId().hashCode();
        }
        if (getMDMCustomerLevel() != null) {
            _hashCode += getMDMCustomerLevel().hashCode();
        }
        if (getEmployeeId() != null) {
            _hashCode += getEmployeeId().hashCode();
        }
        if (getContactType() != null) {
            _hashCode += getContactType().hashCode();
        }
        if (getFirstName() != null) {
            _hashCode += getFirstName().hashCode();
        }
        if (getLastName() != null) {
            _hashCode += getLastName().hashCode();
        }
        if (getEmailAddress() != null) {
            _hashCode += getEmailAddress().hashCode();
        }
        if (getWorkPhoneNumber() != null) {
            _hashCode += getWorkPhoneNumber().hashCode();
        }
        if (getHomePhoneNumber() != null) {
            _hashCode += getHomePhoneNumber().hashCode();
        }
        if (getFaxNumber() != null) {
            _hashCode += getFaxNumber().hashCode();
        }
        if (getMobileNumber() != null) {
            _hashCode += getMobileNumber().hashCode();
        }
        if (getAlternatePhoneNumber() != null) {
            _hashCode += getAlternatePhoneNumber().hashCode();
        }
        if (getLanguage() != null) {
            _hashCode += getLanguage().hashCode();
        }
        if (getContactReferenceId() != null) {
            _hashCode += getContactReferenceId().hashCode();
        }
        if (getPhysicalAddress() != null) {
            _hashCode += getPhysicalAddress().hashCode();
        }
        if (getRoles() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getRoles());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getRoles(), i);
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
        new org.apache.axis.description.TypeDesc(Data.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/LXKCustomerContact/webServices/provider/processCustomerContactWS", "Data"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contactId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ContactId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("customerName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CustomerName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("customerMDMId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CustomerMDMId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("MDMCustomerLevel");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MDMCustomerLevel"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("employeeId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "EmployeeId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contactType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ContactType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("firstName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "FirstName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("lastName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "LastName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
        elemField.setFieldName("workPhoneNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "WorkPhoneNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("homePhoneNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "HomePhoneNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("faxNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "FaxNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mobileNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "MobileNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("alternatePhoneNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "AlternatePhoneNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("language");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Language"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contactReferenceId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ContactReferenceId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("physicalAddress");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PhysicalAddress"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/LXKCustomerContact/webServices/provider/processCustomerContactWS", "PhysicalAddress"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("roles");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Roles"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/LXKCustomerContact/webServices/provider/processCustomerContactWS", "Roles"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("", "ArrayOfRolesItem"));
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
