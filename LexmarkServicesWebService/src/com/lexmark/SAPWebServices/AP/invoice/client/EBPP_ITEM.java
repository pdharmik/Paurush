/**
 * EBPP_ITEM.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SAPWebServices.AP.invoice.client;

public class EBPP_ITEM  implements java.io.Serializable {
    private java.lang.String DOCID;

    private java.lang.String DUE_DATE;

    private java.lang.String CURRENCY;

    private java.math.BigDecimal AMOUNT;

    private java.math.BigDecimal OPENAMOUNT;

    private java.lang.String NON_PAYABLE;

    private java.lang.String STATUS;

    private java.lang.String PAYMENTGROUP;

    private java.lang.String DOCT1;

    private java.lang.String DOCTYPE;

    private java.lang.String CURRENCY_ORI;

    private java.math.BigDecimal AMOUNT_ORI;

    private java.math.BigDecimal OPENAMOUNT_ORI;

    private java.lang.String POSTING_DATE;

    public EBPP_ITEM() {
    }

    public EBPP_ITEM(
           java.lang.String DOCID,
           java.lang.String DUE_DATE,
           java.lang.String CURRENCY,
           java.math.BigDecimal AMOUNT,
           java.math.BigDecimal OPENAMOUNT,
           java.lang.String NON_PAYABLE,
           java.lang.String STATUS,
           java.lang.String PAYMENTGROUP,
           java.lang.String DOCT1,
           java.lang.String DOCTYPE,
           java.lang.String CURRENCY_ORI,
           java.math.BigDecimal AMOUNT_ORI,
           java.math.BigDecimal OPENAMOUNT_ORI,
           java.lang.String POSTING_DATE) {
           this.DOCID = DOCID;
           this.DUE_DATE = DUE_DATE;
           this.CURRENCY = CURRENCY;
           this.AMOUNT = AMOUNT;
           this.OPENAMOUNT = OPENAMOUNT;
           this.NON_PAYABLE = NON_PAYABLE;
           this.STATUS = STATUS;
           this.PAYMENTGROUP = PAYMENTGROUP;
           this.DOCT1 = DOCT1;
           this.DOCTYPE = DOCTYPE;
           this.CURRENCY_ORI = CURRENCY_ORI;
           this.AMOUNT_ORI = AMOUNT_ORI;
           this.OPENAMOUNT_ORI = OPENAMOUNT_ORI;
           this.POSTING_DATE = POSTING_DATE;
    }


    /**
     * Gets the DOCID value for this EBPP_ITEM.
     * 
     * @return DOCID
     */
    public java.lang.String getDOCID() {
        return DOCID;
    }


    /**
     * Sets the DOCID value for this EBPP_ITEM.
     * 
     * @param DOCID
     */
    public void setDOCID(java.lang.String DOCID) {
        this.DOCID = DOCID;
    }


    /**
     * Gets the DUE_DATE value for this EBPP_ITEM.
     * 
     * @return DUE_DATE
     */
    public java.lang.String getDUE_DATE() {
        return DUE_DATE;
    }


    /**
     * Sets the DUE_DATE value for this EBPP_ITEM.
     * 
     * @param DUE_DATE
     */
    public void setDUE_DATE(java.lang.String DUE_DATE) {
        this.DUE_DATE = DUE_DATE;
    }


    /**
     * Gets the CURRENCY value for this EBPP_ITEM.
     * 
     * @return CURRENCY
     */
    public java.lang.String getCURRENCY() {
        return CURRENCY;
    }


    /**
     * Sets the CURRENCY value for this EBPP_ITEM.
     * 
     * @param CURRENCY
     */
    public void setCURRENCY(java.lang.String CURRENCY) {
        this.CURRENCY = CURRENCY;
    }


    /**
     * Gets the AMOUNT value for this EBPP_ITEM.
     * 
     * @return AMOUNT
     */
    public java.math.BigDecimal getAMOUNT() {
        return AMOUNT;
    }


    /**
     * Sets the AMOUNT value for this EBPP_ITEM.
     * 
     * @param AMOUNT
     */
    public void setAMOUNT(java.math.BigDecimal AMOUNT) {
        this.AMOUNT = AMOUNT;
    }


    /**
     * Gets the OPENAMOUNT value for this EBPP_ITEM.
     * 
     * @return OPENAMOUNT
     */
    public java.math.BigDecimal getOPENAMOUNT() {
        return OPENAMOUNT;
    }


    /**
     * Sets the OPENAMOUNT value for this EBPP_ITEM.
     * 
     * @param OPENAMOUNT
     */
    public void setOPENAMOUNT(java.math.BigDecimal OPENAMOUNT) {
        this.OPENAMOUNT = OPENAMOUNT;
    }


    /**
     * Gets the NON_PAYABLE value for this EBPP_ITEM.
     * 
     * @return NON_PAYABLE
     */
    public java.lang.String getNON_PAYABLE() {
        return NON_PAYABLE;
    }


    /**
     * Sets the NON_PAYABLE value for this EBPP_ITEM.
     * 
     * @param NON_PAYABLE
     */
    public void setNON_PAYABLE(java.lang.String NON_PAYABLE) {
        this.NON_PAYABLE = NON_PAYABLE;
    }


    /**
     * Gets the STATUS value for this EBPP_ITEM.
     * 
     * @return STATUS
     */
    public java.lang.String getSTATUS() {
        return STATUS;
    }


    /**
     * Sets the STATUS value for this EBPP_ITEM.
     * 
     * @param STATUS
     */
    public void setSTATUS(java.lang.String STATUS) {
        this.STATUS = STATUS;
    }


    /**
     * Gets the PAYMENTGROUP value for this EBPP_ITEM.
     * 
     * @return PAYMENTGROUP
     */
    public java.lang.String getPAYMENTGROUP() {
        return PAYMENTGROUP;
    }


    /**
     * Sets the PAYMENTGROUP value for this EBPP_ITEM.
     * 
     * @param PAYMENTGROUP
     */
    public void setPAYMENTGROUP(java.lang.String PAYMENTGROUP) {
        this.PAYMENTGROUP = PAYMENTGROUP;
    }


    /**
     * Gets the DOCT1 value for this EBPP_ITEM.
     * 
     * @return DOCT1
     */
    public java.lang.String getDOCT1() {
        return DOCT1;
    }


    /**
     * Sets the DOCT1 value for this EBPP_ITEM.
     * 
     * @param DOCT1
     */
    public void setDOCT1(java.lang.String DOCT1) {
        this.DOCT1 = DOCT1;
    }


    /**
     * Gets the DOCTYPE value for this EBPP_ITEM.
     * 
     * @return DOCTYPE
     */
    public java.lang.String getDOCTYPE() {
        return DOCTYPE;
    }


    /**
     * Sets the DOCTYPE value for this EBPP_ITEM.
     * 
     * @param DOCTYPE
     */
    public void setDOCTYPE(java.lang.String DOCTYPE) {
        this.DOCTYPE = DOCTYPE;
    }


    /**
     * Gets the CURRENCY_ORI value for this EBPP_ITEM.
     * 
     * @return CURRENCY_ORI
     */
    public java.lang.String getCURRENCY_ORI() {
        return CURRENCY_ORI;
    }


    /**
     * Sets the CURRENCY_ORI value for this EBPP_ITEM.
     * 
     * @param CURRENCY_ORI
     */
    public void setCURRENCY_ORI(java.lang.String CURRENCY_ORI) {
        this.CURRENCY_ORI = CURRENCY_ORI;
    }


    /**
     * Gets the AMOUNT_ORI value for this EBPP_ITEM.
     * 
     * @return AMOUNT_ORI
     */
    public java.math.BigDecimal getAMOUNT_ORI() {
        return AMOUNT_ORI;
    }


    /**
     * Sets the AMOUNT_ORI value for this EBPP_ITEM.
     * 
     * @param AMOUNT_ORI
     */
    public void setAMOUNT_ORI(java.math.BigDecimal AMOUNT_ORI) {
        this.AMOUNT_ORI = AMOUNT_ORI;
    }


    /**
     * Gets the OPENAMOUNT_ORI value for this EBPP_ITEM.
     * 
     * @return OPENAMOUNT_ORI
     */
    public java.math.BigDecimal getOPENAMOUNT_ORI() {
        return OPENAMOUNT_ORI;
    }


    /**
     * Sets the OPENAMOUNT_ORI value for this EBPP_ITEM.
     * 
     * @param OPENAMOUNT_ORI
     */
    public void setOPENAMOUNT_ORI(java.math.BigDecimal OPENAMOUNT_ORI) {
        this.OPENAMOUNT_ORI = OPENAMOUNT_ORI;
    }


    /**
     * Gets the POSTING_DATE value for this EBPP_ITEM.
     * 
     * @return POSTING_DATE
     */
    public java.lang.String getPOSTING_DATE() {
        return POSTING_DATE;
    }


    /**
     * Sets the POSTING_DATE value for this EBPP_ITEM.
     * 
     * @param POSTING_DATE
     */
    public void setPOSTING_DATE(java.lang.String POSTING_DATE) {
        this.POSTING_DATE = POSTING_DATE;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EBPP_ITEM)) return false;
        EBPP_ITEM other = (EBPP_ITEM) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.DOCID==null && other.getDOCID()==null) || 
             (this.DOCID!=null &&
              this.DOCID.equals(other.getDOCID()))) &&
            ((this.DUE_DATE==null && other.getDUE_DATE()==null) || 
             (this.DUE_DATE!=null &&
              this.DUE_DATE.equals(other.getDUE_DATE()))) &&
            ((this.CURRENCY==null && other.getCURRENCY()==null) || 
             (this.CURRENCY!=null &&
              this.CURRENCY.equals(other.getCURRENCY()))) &&
            ((this.AMOUNT==null && other.getAMOUNT()==null) || 
             (this.AMOUNT!=null &&
              this.AMOUNT.equals(other.getAMOUNT()))) &&
            ((this.OPENAMOUNT==null && other.getOPENAMOUNT()==null) || 
             (this.OPENAMOUNT!=null &&
              this.OPENAMOUNT.equals(other.getOPENAMOUNT()))) &&
            ((this.NON_PAYABLE==null && other.getNON_PAYABLE()==null) || 
             (this.NON_PAYABLE!=null &&
              this.NON_PAYABLE.equals(other.getNON_PAYABLE()))) &&
            ((this.STATUS==null && other.getSTATUS()==null) || 
             (this.STATUS!=null &&
              this.STATUS.equals(other.getSTATUS()))) &&
            ((this.PAYMENTGROUP==null && other.getPAYMENTGROUP()==null) || 
             (this.PAYMENTGROUP!=null &&
              this.PAYMENTGROUP.equals(other.getPAYMENTGROUP()))) &&
            ((this.DOCT1==null && other.getDOCT1()==null) || 
             (this.DOCT1!=null &&
              this.DOCT1.equals(other.getDOCT1()))) &&
            ((this.DOCTYPE==null && other.getDOCTYPE()==null) || 
             (this.DOCTYPE!=null &&
              this.DOCTYPE.equals(other.getDOCTYPE()))) &&
            ((this.CURRENCY_ORI==null && other.getCURRENCY_ORI()==null) || 
             (this.CURRENCY_ORI!=null &&
              this.CURRENCY_ORI.equals(other.getCURRENCY_ORI()))) &&
            ((this.AMOUNT_ORI==null && other.getAMOUNT_ORI()==null) || 
             (this.AMOUNT_ORI!=null &&
              this.AMOUNT_ORI.equals(other.getAMOUNT_ORI()))) &&
            ((this.OPENAMOUNT_ORI==null && other.getOPENAMOUNT_ORI()==null) || 
             (this.OPENAMOUNT_ORI!=null &&
              this.OPENAMOUNT_ORI.equals(other.getOPENAMOUNT_ORI()))) &&
            ((this.POSTING_DATE==null && other.getPOSTING_DATE()==null) || 
             (this.POSTING_DATE!=null &&
              this.POSTING_DATE.equals(other.getPOSTING_DATE())));
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
        if (getDOCID() != null) {
            _hashCode += getDOCID().hashCode();
        }
        if (getDUE_DATE() != null) {
            _hashCode += getDUE_DATE().hashCode();
        }
        if (getCURRENCY() != null) {
            _hashCode += getCURRENCY().hashCode();
        }
        if (getAMOUNT() != null) {
            _hashCode += getAMOUNT().hashCode();
        }
        if (getOPENAMOUNT() != null) {
            _hashCode += getOPENAMOUNT().hashCode();
        }
        if (getNON_PAYABLE() != null) {
            _hashCode += getNON_PAYABLE().hashCode();
        }
        if (getSTATUS() != null) {
            _hashCode += getSTATUS().hashCode();
        }
        if (getPAYMENTGROUP() != null) {
            _hashCode += getPAYMENTGROUP().hashCode();
        }
        if (getDOCT1() != null) {
            _hashCode += getDOCT1().hashCode();
        }
        if (getDOCTYPE() != null) {
            _hashCode += getDOCTYPE().hashCode();
        }
        if (getCURRENCY_ORI() != null) {
            _hashCode += getCURRENCY_ORI().hashCode();
        }
        if (getAMOUNT_ORI() != null) {
            _hashCode += getAMOUNT_ORI().hashCode();
        }
        if (getOPENAMOUNT_ORI() != null) {
            _hashCode += getOPENAMOUNT_ORI().hashCode();
        }
        if (getPOSTING_DATE() != null) {
            _hashCode += getPOSTING_DATE().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EBPP_ITEM.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "EBPP_ITEM"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DOCID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DOCID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DUE_DATE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DUE_DATE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CURRENCY");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CURRENCY"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("AMOUNT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "AMOUNT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("OPENAMOUNT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "OPENAMOUNT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NON_PAYABLE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "NON_PAYABLE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("STATUS");
        elemField.setXmlName(new javax.xml.namespace.QName("", "STATUS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PAYMENTGROUP");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PAYMENTGROUP"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DOCT1");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DOCT1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DOCTYPE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DOCTYPE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CURRENCY_ORI");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CURRENCY_ORI"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("AMOUNT_ORI");
        elemField.setXmlName(new javax.xml.namespace.QName("", "AMOUNT_ORI"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("OPENAMOUNT_ORI");
        elemField.setXmlName(new javax.xml.namespace.QName("", "OPENAMOUNT_ORI"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("POSTING_DATE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "POSTING_DATE"));
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
