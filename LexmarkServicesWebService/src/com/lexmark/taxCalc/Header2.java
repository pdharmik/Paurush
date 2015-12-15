/**
 * Header2.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.taxCalc;

public class Header2  implements java.io.Serializable {
    private java.lang.String sourceReferenceId;

    private java.lang.String soldToNumber;

    private java.lang.String shipToNumber;

    private java.lang.String salesOrganization;

    private java.lang.String salesOffice;

    private java.lang.String salesGroup;

    private java.lang.String city;

    private java.lang.String county;

    private java.lang.String region;

    private java.lang.String country;

    private java.lang.String postalCode;

    private java.lang.String currency;

    public Header2() {
    }

    public Header2(
           java.lang.String sourceReferenceId,
           java.lang.String soldToNumber,
           java.lang.String shipToNumber,
           java.lang.String salesOrganization,
           java.lang.String salesOffice,
           java.lang.String salesGroup,
           java.lang.String city,
           java.lang.String county,
           java.lang.String region,
           java.lang.String country,
           java.lang.String postalCode,
           java.lang.String currency) {
           this.sourceReferenceId = sourceReferenceId;
           this.soldToNumber = soldToNumber;
           this.shipToNumber = shipToNumber;
           this.salesOrganization = salesOrganization;
           this.salesOffice = salesOffice;
           this.salesGroup = salesGroup;
           this.city = city;
           this.county = county;
           this.region = region;
           this.country = country;
           this.postalCode = postalCode;
           this.currency = currency;
    }


    /**
     * Gets the sourceReferenceId value for this Header2.
     * 
     * @return sourceReferenceId
     */
    public java.lang.String getSourceReferenceId() {
        return sourceReferenceId;
    }


    /**
     * Sets the sourceReferenceId value for this Header2.
     * 
     * @param sourceReferenceId
     */
    public void setSourceReferenceId(java.lang.String sourceReferenceId) {
        this.sourceReferenceId = sourceReferenceId;
    }


    /**
     * Gets the soldToNumber value for this Header2.
     * 
     * @return soldToNumber
     */
    public java.lang.String getSoldToNumber() {
        return soldToNumber;
    }


    /**
     * Sets the soldToNumber value for this Header2.
     * 
     * @param soldToNumber
     */
    public void setSoldToNumber(java.lang.String soldToNumber) {
        this.soldToNumber = soldToNumber;
    }


    /**
     * Gets the shipToNumber value for this Header2.
     * 
     * @return shipToNumber
     */
    public java.lang.String getShipToNumber() {
        return shipToNumber;
    }


    /**
     * Sets the shipToNumber value for this Header2.
     * 
     * @param shipToNumber
     */
    public void setShipToNumber(java.lang.String shipToNumber) {
        this.shipToNumber = shipToNumber;
    }


    /**
     * Gets the salesOrganization value for this Header2.
     * 
     * @return salesOrganization
     */
    public java.lang.String getSalesOrganization() {
        return salesOrganization;
    }


    /**
     * Sets the salesOrganization value for this Header2.
     * 
     * @param salesOrganization
     */
    public void setSalesOrganization(java.lang.String salesOrganization) {
        this.salesOrganization = salesOrganization;
    }


    /**
     * Gets the salesOffice value for this Header2.
     * 
     * @return salesOffice
     */
    public java.lang.String getSalesOffice() {
        return salesOffice;
    }


    /**
     * Sets the salesOffice value for this Header2.
     * 
     * @param salesOffice
     */
    public void setSalesOffice(java.lang.String salesOffice) {
        this.salesOffice = salesOffice;
    }


    /**
     * Gets the salesGroup value for this Header2.
     * 
     * @return salesGroup
     */
    public java.lang.String getSalesGroup() {
        return salesGroup;
    }


    /**
     * Sets the salesGroup value for this Header2.
     * 
     * @param salesGroup
     */
    public void setSalesGroup(java.lang.String salesGroup) {
        this.salesGroup = salesGroup;
    }


    /**
     * Gets the city value for this Header2.
     * 
     * @return city
     */
    public java.lang.String getCity() {
        return city;
    }


    /**
     * Sets the city value for this Header2.
     * 
     * @param city
     */
    public void setCity(java.lang.String city) {
        this.city = city;
    }


    /**
     * Gets the county value for this Header2.
     * 
     * @return county
     */
    public java.lang.String getCounty() {
        return county;
    }


    /**
     * Sets the county value for this Header2.
     * 
     * @param county
     */
    public void setCounty(java.lang.String county) {
        this.county = county;
    }


    /**
     * Gets the region value for this Header2.
     * 
     * @return region
     */
    public java.lang.String getRegion() {
        return region;
    }


    /**
     * Sets the region value for this Header2.
     * 
     * @param region
     */
    public void setRegion(java.lang.String region) {
        this.region = region;
    }


    /**
     * Gets the country value for this Header2.
     * 
     * @return country
     */
    public java.lang.String getCountry() {
        return country;
    }


    /**
     * Sets the country value for this Header2.
     * 
     * @param country
     */
    public void setCountry(java.lang.String country) {
        this.country = country;
    }


    /**
     * Gets the postalCode value for this Header2.
     * 
     * @return postalCode
     */
    public java.lang.String getPostalCode() {
        return postalCode;
    }


    /**
     * Sets the postalCode value for this Header2.
     * 
     * @param postalCode
     */
    public void setPostalCode(java.lang.String postalCode) {
        this.postalCode = postalCode;
    }


    /**
     * Gets the currency value for this Header2.
     * 
     * @return currency
     */
    public java.lang.String getCurrency() {
        return currency;
    }


    /**
     * Sets the currency value for this Header2.
     * 
     * @param currency
     */
    public void setCurrency(java.lang.String currency) {
        this.currency = currency;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Header2)) return false;
        Header2 other = (Header2) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.sourceReferenceId==null && other.getSourceReferenceId()==null) || 
             (this.sourceReferenceId!=null &&
              this.sourceReferenceId.equals(other.getSourceReferenceId()))) &&
            ((this.soldToNumber==null && other.getSoldToNumber()==null) || 
             (this.soldToNumber!=null &&
              this.soldToNumber.equals(other.getSoldToNumber()))) &&
            ((this.shipToNumber==null && other.getShipToNumber()==null) || 
             (this.shipToNumber!=null &&
              this.shipToNumber.equals(other.getShipToNumber()))) &&
            ((this.salesOrganization==null && other.getSalesOrganization()==null) || 
             (this.salesOrganization!=null &&
              this.salesOrganization.equals(other.getSalesOrganization()))) &&
            ((this.salesOffice==null && other.getSalesOffice()==null) || 
             (this.salesOffice!=null &&
              this.salesOffice.equals(other.getSalesOffice()))) &&
            ((this.salesGroup==null && other.getSalesGroup()==null) || 
             (this.salesGroup!=null &&
              this.salesGroup.equals(other.getSalesGroup()))) &&
            ((this.city==null && other.getCity()==null) || 
             (this.city!=null &&
              this.city.equals(other.getCity()))) &&
            ((this.county==null && other.getCounty()==null) || 
             (this.county!=null &&
              this.county.equals(other.getCounty()))) &&
            ((this.region==null && other.getRegion()==null) || 
             (this.region!=null &&
              this.region.equals(other.getRegion()))) &&
            ((this.country==null && other.getCountry()==null) || 
             (this.country!=null &&
              this.country.equals(other.getCountry()))) &&
            ((this.postalCode==null && other.getPostalCode()==null) || 
             (this.postalCode!=null &&
              this.postalCode.equals(other.getPostalCode()))) &&
            ((this.currency==null && other.getCurrency()==null) || 
             (this.currency!=null &&
              this.currency.equals(other.getCurrency())));
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
        if (getSourceReferenceId() != null) {
            _hashCode += getSourceReferenceId().hashCode();
        }
        if (getSoldToNumber() != null) {
            _hashCode += getSoldToNumber().hashCode();
        }
        if (getShipToNumber() != null) {
            _hashCode += getShipToNumber().hashCode();
        }
        if (getSalesOrganization() != null) {
            _hashCode += getSalesOrganization().hashCode();
        }
        if (getSalesOffice() != null) {
            _hashCode += getSalesOffice().hashCode();
        }
        if (getSalesGroup() != null) {
            _hashCode += getSalesGroup().hashCode();
        }
        if (getCity() != null) {
            _hashCode += getCity().hashCode();
        }
        if (getCounty() != null) {
            _hashCode += getCounty().hashCode();
        }
        if (getRegion() != null) {
            _hashCode += getRegion().hashCode();
        }
        if (getCountry() != null) {
            _hashCode += getCountry().hashCode();
        }
        if (getPostalCode() != null) {
            _hashCode += getPostalCode().hashCode();
        }
        if (getCurrency() != null) {
            _hashCode += getCurrency().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Header2.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dlexrwmis001.lex.lexmark.com/LXKTaxCalculationWS/webservices/provider/taxCalculateWS", "Header2"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sourceReferenceId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SourceReferenceId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("soldToNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SoldToNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("shipToNumber");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ShipToNumber"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("salesOrganization");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SalesOrganization"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("salesOffice");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SalesOffice"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("salesGroup");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SalesGroup"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("city");
        elemField.setXmlName(new javax.xml.namespace.QName("", "City"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("county");
        elemField.setXmlName(new javax.xml.namespace.QName("", "County"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("region");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Region"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("country");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Country"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("postalCode");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PostalCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("currency");
        elemField.setXmlName(new javax.xml.namespace.QName("", "Currency"));
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
