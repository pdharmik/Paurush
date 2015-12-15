/**
 * PageCountData.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SiebelShared.lgs.om.createServiceRequest.client;

public class PageCountData  implements java.io.Serializable {
    private java.lang.String pageCountType;

    private java.lang.String pageCount;

    private java.lang.String readingDateTime;

    public PageCountData() {
    }

    public PageCountData(
           java.lang.String pageCountType,
           java.lang.String pageCount,
           java.lang.String readingDateTime) {
           this.pageCountType = pageCountType;
           this.pageCount = pageCount;
           this.readingDateTime = readingDateTime;
    }


    /**
     * Gets the pageCountType value for this PageCountData.
     * 
     * @return pageCountType
     */
    public java.lang.String getPageCountType() {
        return pageCountType;
    }


    /**
     * Sets the pageCountType value for this PageCountData.
     * 
     * @param pageCountType
     */
    public void setPageCountType(java.lang.String pageCountType) {
        this.pageCountType = pageCountType;
    }


    /**
     * Gets the pageCount value for this PageCountData.
     * 
     * @return pageCount
     */
    public java.lang.String getPageCount() {
        return pageCount;
    }


    /**
     * Sets the pageCount value for this PageCountData.
     * 
     * @param pageCount
     */
    public void setPageCount(java.lang.String pageCount) {
        this.pageCount = pageCount;
    }


    /**
     * Gets the readingDateTime value for this PageCountData.
     * 
     * @return readingDateTime
     */
    public java.lang.String getReadingDateTime() {
        return readingDateTime;
    }


    /**
     * Sets the readingDateTime value for this PageCountData.
     * 
     * @param readingDateTime
     */
    public void setReadingDateTime(java.lang.String readingDateTime) {
        this.readingDateTime = readingDateTime;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PageCountData)) return false;
        PageCountData other = (PageCountData) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.pageCountType==null && other.getPageCountType()==null) || 
             (this.pageCountType!=null &&
              this.pageCountType.equals(other.getPageCountType()))) &&
            ((this.pageCount==null && other.getPageCount()==null) || 
             (this.pageCount!=null &&
              this.pageCount.equals(other.getPageCount()))) &&
            ((this.readingDateTime==null && other.getReadingDateTime()==null) || 
             (this.readingDateTime!=null &&
              this.readingDateTime.equals(other.getReadingDateTime())));
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
        if (getPageCountType() != null) {
            _hashCode += getPageCountType().hashCode();
        }
        if (getPageCount() != null) {
            _hashCode += getPageCount().hashCode();
        }
        if (getReadingDateTime() != null) {
            _hashCode += getReadingDateTime().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PageCountData.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://dlxkswmis1.lex.lexmark.com/ServiceRequest/serviceRequestWS", "PageCountData"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pageCountType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PageCountType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pageCount");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PageCount"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("readingDateTime");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ReadingDateTime"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
