/**
 * DataSetRecord.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.businessobjects.service.Service_Realtime_DQ_Siebel_account_match.input;

public class DataSetRecord  implements java.io.Serializable {
    private java.lang.String id;

    private java.lang.String location;

    private java.lang.String name;

    private java.lang.String city;

    private java.lang.String country;

    private java.lang.String postalCode;

    private java.lang.String state;

    private java.lang.String streetAddress;

    private java.lang.String streetAddress2;

    private java.lang.String officeNo;

    private java.lang.String DQ_Driver;

    public DataSetRecord() {
    }

    public DataSetRecord(
           java.lang.String id,
           java.lang.String location,
           java.lang.String name,
           java.lang.String city,
           java.lang.String country,
           java.lang.String postalCode,
           java.lang.String state,
           java.lang.String streetAddress,
           java.lang.String streetAddress2,
           java.lang.String officeNo,
           java.lang.String DQ_Driver) {
           this.id = id;
           this.location = location;
           this.name = name;
           this.city = city;
           this.country = country;
           this.postalCode = postalCode;
           this.state = state;
           this.streetAddress = streetAddress;
           this.streetAddress2 = streetAddress2;
           this.officeNo = officeNo;
           this.DQ_Driver = DQ_Driver;
    }


    /**
     * Gets the id value for this DataSetRecord.
     * 
     * @return id
     */
    public java.lang.String getId() {
        return id;
    }


    /**
     * Sets the id value for this DataSetRecord.
     * 
     * @param id
     */
    public void setId(java.lang.String id) {
        this.id = id;
    }


    /**
     * Gets the location value for this DataSetRecord.
     * 
     * @return location
     */
    public java.lang.String getLocation() {
        return location;
    }


    /**
     * Sets the location value for this DataSetRecord.
     * 
     * @param location
     */
    public void setLocation(java.lang.String location) {
        this.location = location;
    }


    /**
     * Gets the name value for this DataSetRecord.
     * 
     * @return name
     */
    public java.lang.String getName() {
        return name;
    }


    /**
     * Sets the name value for this DataSetRecord.
     * 
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }


    /**
     * Gets the city value for this DataSetRecord.
     * 
     * @return city
     */
    public java.lang.String getCity() {
        return city;
    }


    /**
     * Sets the city value for this DataSetRecord.
     * 
     * @param city
     */
    public void setCity(java.lang.String city) {
        this.city = city;
    }


    /**
     * Gets the country value for this DataSetRecord.
     * 
     * @return country
     */
    public java.lang.String getCountry() {
        return country;
    }


    /**
     * Sets the country value for this DataSetRecord.
     * 
     * @param country
     */
    public void setCountry(java.lang.String country) {
        this.country = country;
    }


    /**
     * Gets the postalCode value for this DataSetRecord.
     * 
     * @return postalCode
     */
    public java.lang.String getPostalCode() {
        return postalCode;
    }


    /**
     * Sets the postalCode value for this DataSetRecord.
     * 
     * @param postalCode
     */
    public void setPostalCode(java.lang.String postalCode) {
        this.postalCode = postalCode;
    }


    /**
     * Gets the state value for this DataSetRecord.
     * 
     * @return state
     */
    public java.lang.String getState() {
        return state;
    }


    /**
     * Sets the state value for this DataSetRecord.
     * 
     * @param state
     */
    public void setState(java.lang.String state) {
        this.state = state;
    }


    /**
     * Gets the streetAddress value for this DataSetRecord.
     * 
     * @return streetAddress
     */
    public java.lang.String getStreetAddress() {
        return streetAddress;
    }


    /**
     * Sets the streetAddress value for this DataSetRecord.
     * 
     * @param streetAddress
     */
    public void setStreetAddress(java.lang.String streetAddress) {
        this.streetAddress = streetAddress;
    }


    /**
     * Gets the streetAddress2 value for this DataSetRecord.
     * 
     * @return streetAddress2
     */
    public java.lang.String getStreetAddress2() {
        return streetAddress2;
    }


    /**
     * Sets the streetAddress2 value for this DataSetRecord.
     * 
     * @param streetAddress2
     */
    public void setStreetAddress2(java.lang.String streetAddress2) {
        this.streetAddress2 = streetAddress2;
    }


    /**
     * Gets the officeNo value for this DataSetRecord.
     * 
     * @return officeNo
     */
    public java.lang.String getOfficeNo() {
        return officeNo;
    }


    /**
     * Sets the officeNo value for this DataSetRecord.
     * 
     * @param officeNo
     */
    public void setOfficeNo(java.lang.String officeNo) {
        this.officeNo = officeNo;
    }


    /**
     * Gets the DQ_Driver value for this DataSetRecord.
     * 
     * @return DQ_Driver
     */
    public java.lang.String getDQ_Driver() {
        return DQ_Driver;
    }


    /**
     * Sets the DQ_Driver value for this DataSetRecord.
     * 
     * @param DQ_Driver
     */
    public void setDQ_Driver(java.lang.String DQ_Driver) {
        this.DQ_Driver = DQ_Driver;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DataSetRecord)) return false;
        DataSetRecord other = (DataSetRecord) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.location==null && other.getLocation()==null) || 
             (this.location!=null &&
              this.location.equals(other.getLocation()))) &&
            ((this.name==null && other.getName()==null) || 
             (this.name!=null &&
              this.name.equals(other.getName()))) &&
            ((this.city==null && other.getCity()==null) || 
             (this.city!=null &&
              this.city.equals(other.getCity()))) &&
            ((this.country==null && other.getCountry()==null) || 
             (this.country!=null &&
              this.country.equals(other.getCountry()))) &&
            ((this.postalCode==null && other.getPostalCode()==null) || 
             (this.postalCode!=null &&
              this.postalCode.equals(other.getPostalCode()))) &&
            ((this.state==null && other.getState()==null) || 
             (this.state!=null &&
              this.state.equals(other.getState()))) &&
            ((this.streetAddress==null && other.getStreetAddress()==null) || 
             (this.streetAddress!=null &&
              this.streetAddress.equals(other.getStreetAddress()))) &&
            ((this.streetAddress2==null && other.getStreetAddress2()==null) || 
             (this.streetAddress2!=null &&
              this.streetAddress2.equals(other.getStreetAddress2()))) &&
            ((this.officeNo==null && other.getOfficeNo()==null) || 
             (this.officeNo!=null &&
              this.officeNo.equals(other.getOfficeNo()))) &&
            ((this.DQ_Driver==null && other.getDQ_Driver()==null) || 
             (this.DQ_Driver!=null &&
              this.DQ_Driver.equals(other.getDQ_Driver())));
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
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getLocation() != null) {
            _hashCode += getLocation().hashCode();
        }
        if (getName() != null) {
            _hashCode += getName().hashCode();
        }
        if (getCity() != null) {
            _hashCode += getCity().hashCode();
        }
        if (getCountry() != null) {
            _hashCode += getCountry().hashCode();
        }
        if (getPostalCode() != null) {
            _hashCode += getPostalCode().hashCode();
        }
        if (getState() != null) {
            _hashCode += getState().hashCode();
        }
        if (getStreetAddress() != null) {
            _hashCode += getStreetAddress().hashCode();
        }
        if (getStreetAddress2() != null) {
            _hashCode += getStreetAddress2().hashCode();
        }
        if (getOfficeNo() != null) {
            _hashCode += getOfficeNo().hashCode();
        }
        if (getDQ_Driver() != null) {
            _hashCode += getDQ_Driver().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DataSetRecord.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://businessobjects.com/service/Service_Realtime_DQ_Siebel_account_match/input", ">>DataSet>Record"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobjects.com/service/Service_Realtime_DQ_Siebel_account_match/input", "Id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("location");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobjects.com/service/Service_Realtime_DQ_Siebel_account_match/input", "Location"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("name");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobjects.com/service/Service_Realtime_DQ_Siebel_account_match/input", "Name"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("city");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobjects.com/service/Service_Realtime_DQ_Siebel_account_match/input", "City"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("country");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobjects.com/service/Service_Realtime_DQ_Siebel_account_match/input", "Country"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("postalCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobjects.com/service/Service_Realtime_DQ_Siebel_account_match/input", "PostalCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("state");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobjects.com/service/Service_Realtime_DQ_Siebel_account_match/input", "State"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("streetAddress");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobjects.com/service/Service_Realtime_DQ_Siebel_account_match/input", "StreetAddress"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("streetAddress2");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobjects.com/service/Service_Realtime_DQ_Siebel_account_match/input", "StreetAddress2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("officeNo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobjects.com/service/Service_Realtime_DQ_Siebel_account_match/input", "OfficeNo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DQ_Driver");
        elemField.setXmlName(new javax.xml.namespace.QName("http://businessobjects.com/service/Service_Realtime_DQ_Siebel_account_match/input", "DQ_Driver"));
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
