/**
 * FDM_EBPP_DMDISPUTES.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SAPWebServices.AP.invoice.client;

public class FDM_EBPP_DMDISPUTES  implements java.io.Serializable {
    private java.lang.String CASE_GUID;

    private java.lang.String EXT_KEY;

    private java.lang.String EXT_REF;

    private java.math.BigDecimal DISPUTED_AMT;

    private java.lang.String DISPUTE_CURR;

    private java.lang.String REASON;

    private java.lang.String REASON_TEXT;

    private java.lang.String FDM_STAT_ORDERNO_TEXT;

    private java.math.BigDecimal CREATE_TIME;

    private java.lang.String CHANGEABLE;

    private java.lang.String CONTACT_NAME;

    private java.lang.String CONTACT_MAIL;

    private java.lang.String CONTACT_TEL;

    private java.lang.String CONTACT_FAX;

    private java.lang.String CONTACT_FAXC;

    private java.lang.String CONTACT_KEY;

    public FDM_EBPP_DMDISPUTES() {
    }

    public FDM_EBPP_DMDISPUTES(
           java.lang.String CASE_GUID,
           java.lang.String EXT_KEY,
           java.lang.String EXT_REF,
           java.math.BigDecimal DISPUTED_AMT,
           java.lang.String DISPUTE_CURR,
           java.lang.String REASON,
           java.lang.String REASON_TEXT,
           java.lang.String FDM_STAT_ORDERNO_TEXT,
           java.math.BigDecimal CREATE_TIME,
           java.lang.String CHANGEABLE,
           java.lang.String CONTACT_NAME,
           java.lang.String CONTACT_MAIL,
           java.lang.String CONTACT_TEL,
           java.lang.String CONTACT_FAX,
           java.lang.String CONTACT_FAXC,
           java.lang.String CONTACT_KEY) {
           this.CASE_GUID = CASE_GUID;
           this.EXT_KEY = EXT_KEY;
           this.EXT_REF = EXT_REF;
           this.DISPUTED_AMT = DISPUTED_AMT;
           this.DISPUTE_CURR = DISPUTE_CURR;
           this.REASON = REASON;
           this.REASON_TEXT = REASON_TEXT;
           this.FDM_STAT_ORDERNO_TEXT = FDM_STAT_ORDERNO_TEXT;
           this.CREATE_TIME = CREATE_TIME;
           this.CHANGEABLE = CHANGEABLE;
           this.CONTACT_NAME = CONTACT_NAME;
           this.CONTACT_MAIL = CONTACT_MAIL;
           this.CONTACT_TEL = CONTACT_TEL;
           this.CONTACT_FAX = CONTACT_FAX;
           this.CONTACT_FAXC = CONTACT_FAXC;
           this.CONTACT_KEY = CONTACT_KEY;
    }


    /**
     * Gets the CASE_GUID value for this FDM_EBPP_DMDISPUTES.
     * 
     * @return CASE_GUID
     */
    public java.lang.String getCASE_GUID() {
        return CASE_GUID;
    }


    /**
     * Sets the CASE_GUID value for this FDM_EBPP_DMDISPUTES.
     * 
     * @param CASE_GUID
     */
    public void setCASE_GUID(java.lang.String CASE_GUID) {
        this.CASE_GUID = CASE_GUID;
    }


    /**
     * Gets the EXT_KEY value for this FDM_EBPP_DMDISPUTES.
     * 
     * @return EXT_KEY
     */
    public java.lang.String getEXT_KEY() {
        return EXT_KEY;
    }


    /**
     * Sets the EXT_KEY value for this FDM_EBPP_DMDISPUTES.
     * 
     * @param EXT_KEY
     */
    public void setEXT_KEY(java.lang.String EXT_KEY) {
        this.EXT_KEY = EXT_KEY;
    }


    /**
     * Gets the EXT_REF value for this FDM_EBPP_DMDISPUTES.
     * 
     * @return EXT_REF
     */
    public java.lang.String getEXT_REF() {
        return EXT_REF;
    }


    /**
     * Sets the EXT_REF value for this FDM_EBPP_DMDISPUTES.
     * 
     * @param EXT_REF
     */
    public void setEXT_REF(java.lang.String EXT_REF) {
        this.EXT_REF = EXT_REF;
    }


    /**
     * Gets the DISPUTED_AMT value for this FDM_EBPP_DMDISPUTES.
     * 
     * @return DISPUTED_AMT
     */
    public java.math.BigDecimal getDISPUTED_AMT() {
        return DISPUTED_AMT;
    }


    /**
     * Sets the DISPUTED_AMT value for this FDM_EBPP_DMDISPUTES.
     * 
     * @param DISPUTED_AMT
     */
    public void setDISPUTED_AMT(java.math.BigDecimal DISPUTED_AMT) {
        this.DISPUTED_AMT = DISPUTED_AMT;
    }


    /**
     * Gets the DISPUTE_CURR value for this FDM_EBPP_DMDISPUTES.
     * 
     * @return DISPUTE_CURR
     */
    public java.lang.String getDISPUTE_CURR() {
        return DISPUTE_CURR;
    }


    /**
     * Sets the DISPUTE_CURR value for this FDM_EBPP_DMDISPUTES.
     * 
     * @param DISPUTE_CURR
     */
    public void setDISPUTE_CURR(java.lang.String DISPUTE_CURR) {
        this.DISPUTE_CURR = DISPUTE_CURR;
    }


    /**
     * Gets the REASON value for this FDM_EBPP_DMDISPUTES.
     * 
     * @return REASON
     */
    public java.lang.String getREASON() {
        return REASON;
    }


    /**
     * Sets the REASON value for this FDM_EBPP_DMDISPUTES.
     * 
     * @param REASON
     */
    public void setREASON(java.lang.String REASON) {
        this.REASON = REASON;
    }


    /**
     * Gets the REASON_TEXT value for this FDM_EBPP_DMDISPUTES.
     * 
     * @return REASON_TEXT
     */
    public java.lang.String getREASON_TEXT() {
        return REASON_TEXT;
    }


    /**
     * Sets the REASON_TEXT value for this FDM_EBPP_DMDISPUTES.
     * 
     * @param REASON_TEXT
     */
    public void setREASON_TEXT(java.lang.String REASON_TEXT) {
        this.REASON_TEXT = REASON_TEXT;
    }


    /**
     * Gets the FDM_STAT_ORDERNO_TEXT value for this FDM_EBPP_DMDISPUTES.
     * 
     * @return FDM_STAT_ORDERNO_TEXT
     */
    public java.lang.String getFDM_STAT_ORDERNO_TEXT() {
        return FDM_STAT_ORDERNO_TEXT;
    }


    /**
     * Sets the FDM_STAT_ORDERNO_TEXT value for this FDM_EBPP_DMDISPUTES.
     * 
     * @param FDM_STAT_ORDERNO_TEXT
     */
    public void setFDM_STAT_ORDERNO_TEXT(java.lang.String FDM_STAT_ORDERNO_TEXT) {
        this.FDM_STAT_ORDERNO_TEXT = FDM_STAT_ORDERNO_TEXT;
    }


    /**
     * Gets the CREATE_TIME value for this FDM_EBPP_DMDISPUTES.
     * 
     * @return CREATE_TIME
     */
    public java.math.BigDecimal getCREATE_TIME() {
        return CREATE_TIME;
    }


    /**
     * Sets the CREATE_TIME value for this FDM_EBPP_DMDISPUTES.
     * 
     * @param CREATE_TIME
     */
    public void setCREATE_TIME(java.math.BigDecimal CREATE_TIME) {
        this.CREATE_TIME = CREATE_TIME;
    }


    /**
     * Gets the CHANGEABLE value for this FDM_EBPP_DMDISPUTES.
     * 
     * @return CHANGEABLE
     */
    public java.lang.String getCHANGEABLE() {
        return CHANGEABLE;
    }


    /**
     * Sets the CHANGEABLE value for this FDM_EBPP_DMDISPUTES.
     * 
     * @param CHANGEABLE
     */
    public void setCHANGEABLE(java.lang.String CHANGEABLE) {
        this.CHANGEABLE = CHANGEABLE;
    }


    /**
     * Gets the CONTACT_NAME value for this FDM_EBPP_DMDISPUTES.
     * 
     * @return CONTACT_NAME
     */
    public java.lang.String getCONTACT_NAME() {
        return CONTACT_NAME;
    }


    /**
     * Sets the CONTACT_NAME value for this FDM_EBPP_DMDISPUTES.
     * 
     * @param CONTACT_NAME
     */
    public void setCONTACT_NAME(java.lang.String CONTACT_NAME) {
        this.CONTACT_NAME = CONTACT_NAME;
    }


    /**
     * Gets the CONTACT_MAIL value for this FDM_EBPP_DMDISPUTES.
     * 
     * @return CONTACT_MAIL
     */
    public java.lang.String getCONTACT_MAIL() {
        return CONTACT_MAIL;
    }


    /**
     * Sets the CONTACT_MAIL value for this FDM_EBPP_DMDISPUTES.
     * 
     * @param CONTACT_MAIL
     */
    public void setCONTACT_MAIL(java.lang.String CONTACT_MAIL) {
        this.CONTACT_MAIL = CONTACT_MAIL;
    }


    /**
     * Gets the CONTACT_TEL value for this FDM_EBPP_DMDISPUTES.
     * 
     * @return CONTACT_TEL
     */
    public java.lang.String getCONTACT_TEL() {
        return CONTACT_TEL;
    }


    /**
     * Sets the CONTACT_TEL value for this FDM_EBPP_DMDISPUTES.
     * 
     * @param CONTACT_TEL
     */
    public void setCONTACT_TEL(java.lang.String CONTACT_TEL) {
        this.CONTACT_TEL = CONTACT_TEL;
    }


    /**
     * Gets the CONTACT_FAX value for this FDM_EBPP_DMDISPUTES.
     * 
     * @return CONTACT_FAX
     */
    public java.lang.String getCONTACT_FAX() {
        return CONTACT_FAX;
    }


    /**
     * Sets the CONTACT_FAX value for this FDM_EBPP_DMDISPUTES.
     * 
     * @param CONTACT_FAX
     */
    public void setCONTACT_FAX(java.lang.String CONTACT_FAX) {
        this.CONTACT_FAX = CONTACT_FAX;
    }


    /**
     * Gets the CONTACT_FAXC value for this FDM_EBPP_DMDISPUTES.
     * 
     * @return CONTACT_FAXC
     */
    public java.lang.String getCONTACT_FAXC() {
        return CONTACT_FAXC;
    }


    /**
     * Sets the CONTACT_FAXC value for this FDM_EBPP_DMDISPUTES.
     * 
     * @param CONTACT_FAXC
     */
    public void setCONTACT_FAXC(java.lang.String CONTACT_FAXC) {
        this.CONTACT_FAXC = CONTACT_FAXC;
    }


    /**
     * Gets the CONTACT_KEY value for this FDM_EBPP_DMDISPUTES.
     * 
     * @return CONTACT_KEY
     */
    public java.lang.String getCONTACT_KEY() {
        return CONTACT_KEY;
    }


    /**
     * Sets the CONTACT_KEY value for this FDM_EBPP_DMDISPUTES.
     * 
     * @param CONTACT_KEY
     */
    public void setCONTACT_KEY(java.lang.String CONTACT_KEY) {
        this.CONTACT_KEY = CONTACT_KEY;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof FDM_EBPP_DMDISPUTES)) return false;
        FDM_EBPP_DMDISPUTES other = (FDM_EBPP_DMDISPUTES) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.CASE_GUID==null && other.getCASE_GUID()==null) || 
             (this.CASE_GUID!=null &&
              this.CASE_GUID.equals(other.getCASE_GUID()))) &&
            ((this.EXT_KEY==null && other.getEXT_KEY()==null) || 
             (this.EXT_KEY!=null &&
              this.EXT_KEY.equals(other.getEXT_KEY()))) &&
            ((this.EXT_REF==null && other.getEXT_REF()==null) || 
             (this.EXT_REF!=null &&
              this.EXT_REF.equals(other.getEXT_REF()))) &&
            ((this.DISPUTED_AMT==null && other.getDISPUTED_AMT()==null) || 
             (this.DISPUTED_AMT!=null &&
              this.DISPUTED_AMT.equals(other.getDISPUTED_AMT()))) &&
            ((this.DISPUTE_CURR==null && other.getDISPUTE_CURR()==null) || 
             (this.DISPUTE_CURR!=null &&
              this.DISPUTE_CURR.equals(other.getDISPUTE_CURR()))) &&
            ((this.REASON==null && other.getREASON()==null) || 
             (this.REASON!=null &&
              this.REASON.equals(other.getREASON()))) &&
            ((this.REASON_TEXT==null && other.getREASON_TEXT()==null) || 
             (this.REASON_TEXT!=null &&
              this.REASON_TEXT.equals(other.getREASON_TEXT()))) &&
            ((this.FDM_STAT_ORDERNO_TEXT==null && other.getFDM_STAT_ORDERNO_TEXT()==null) || 
             (this.FDM_STAT_ORDERNO_TEXT!=null &&
              this.FDM_STAT_ORDERNO_TEXT.equals(other.getFDM_STAT_ORDERNO_TEXT()))) &&
            ((this.CREATE_TIME==null && other.getCREATE_TIME()==null) || 
             (this.CREATE_TIME!=null &&
              this.CREATE_TIME.equals(other.getCREATE_TIME()))) &&
            ((this.CHANGEABLE==null && other.getCHANGEABLE()==null) || 
             (this.CHANGEABLE!=null &&
              this.CHANGEABLE.equals(other.getCHANGEABLE()))) &&
            ((this.CONTACT_NAME==null && other.getCONTACT_NAME()==null) || 
             (this.CONTACT_NAME!=null &&
              this.CONTACT_NAME.equals(other.getCONTACT_NAME()))) &&
            ((this.CONTACT_MAIL==null && other.getCONTACT_MAIL()==null) || 
             (this.CONTACT_MAIL!=null &&
              this.CONTACT_MAIL.equals(other.getCONTACT_MAIL()))) &&
            ((this.CONTACT_TEL==null && other.getCONTACT_TEL()==null) || 
             (this.CONTACT_TEL!=null &&
              this.CONTACT_TEL.equals(other.getCONTACT_TEL()))) &&
            ((this.CONTACT_FAX==null && other.getCONTACT_FAX()==null) || 
             (this.CONTACT_FAX!=null &&
              this.CONTACT_FAX.equals(other.getCONTACT_FAX()))) &&
            ((this.CONTACT_FAXC==null && other.getCONTACT_FAXC()==null) || 
             (this.CONTACT_FAXC!=null &&
              this.CONTACT_FAXC.equals(other.getCONTACT_FAXC()))) &&
            ((this.CONTACT_KEY==null && other.getCONTACT_KEY()==null) || 
             (this.CONTACT_KEY!=null &&
              this.CONTACT_KEY.equals(other.getCONTACT_KEY())));
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
        if (getCASE_GUID() != null) {
            _hashCode += getCASE_GUID().hashCode();
        }
        if (getEXT_KEY() != null) {
            _hashCode += getEXT_KEY().hashCode();
        }
        if (getEXT_REF() != null) {
            _hashCode += getEXT_REF().hashCode();
        }
        if (getDISPUTED_AMT() != null) {
            _hashCode += getDISPUTED_AMT().hashCode();
        }
        if (getDISPUTE_CURR() != null) {
            _hashCode += getDISPUTE_CURR().hashCode();
        }
        if (getREASON() != null) {
            _hashCode += getREASON().hashCode();
        }
        if (getREASON_TEXT() != null) {
            _hashCode += getREASON_TEXT().hashCode();
        }
        if (getFDM_STAT_ORDERNO_TEXT() != null) {
            _hashCode += getFDM_STAT_ORDERNO_TEXT().hashCode();
        }
        if (getCREATE_TIME() != null) {
            _hashCode += getCREATE_TIME().hashCode();
        }
        if (getCHANGEABLE() != null) {
            _hashCode += getCHANGEABLE().hashCode();
        }
        if (getCONTACT_NAME() != null) {
            _hashCode += getCONTACT_NAME().hashCode();
        }
        if (getCONTACT_MAIL() != null) {
            _hashCode += getCONTACT_MAIL().hashCode();
        }
        if (getCONTACT_TEL() != null) {
            _hashCode += getCONTACT_TEL().hashCode();
        }
        if (getCONTACT_FAX() != null) {
            _hashCode += getCONTACT_FAX().hashCode();
        }
        if (getCONTACT_FAXC() != null) {
            _hashCode += getCONTACT_FAXC().hashCode();
        }
        if (getCONTACT_KEY() != null) {
            _hashCode += getCONTACT_KEY().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(FDM_EBPP_DMDISPUTES.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "FDM_EBPP_DMDISPUTES"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CASE_GUID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CASE_GUID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("EXT_KEY");
        elemField.setXmlName(new javax.xml.namespace.QName("", "EXT_KEY"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("EXT_REF");
        elemField.setXmlName(new javax.xml.namespace.QName("", "EXT_REF"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DISPUTED_AMT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DISPUTED_AMT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DISPUTE_CURR");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DISPUTE_CURR"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("REASON");
        elemField.setXmlName(new javax.xml.namespace.QName("", "REASON"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("REASON_TEXT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "REASON_TEXT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FDM_STAT_ORDERNO_TEXT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "FDM_STAT_ORDERNO_TEXT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CREATE_TIME");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CREATE_TIME"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CHANGEABLE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CHANGEABLE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CONTACT_NAME");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CONTACT_NAME"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CONTACT_MAIL");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CONTACT_MAIL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CONTACT_TEL");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CONTACT_TEL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CONTACT_FAX");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CONTACT_FAX"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CONTACT_FAXC");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CONTACT_FAXC"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CONTACT_KEY");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CONTACT_KEY"));
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
