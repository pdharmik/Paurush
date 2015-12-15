/**
 * EBPP_CONTROL.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SAPWebServices.AP.invoice.client;

public class EBPP_CONTROL  implements java.io.Serializable {
    private java.lang.String DUE_DATE_LOW;

    private java.lang.String DUE_DATE_HIGH;

    private java.lang.String OPEN_ITEMS;

    private java.lang.String CLEARED_ITEMS;

    private java.lang.String PARKED_ITEMS;

    private java.lang.String SELECT_FOR_PAYMENTS;

    private java.lang.String PAYMENT_TYPE;

    private java.lang.String PAYMENT_ID;

    private java.lang.String ONLY_INVOICES;

    private java.lang.String CHECK_DISPUTES;

    private java.lang.String PREVIOUS_BALANCE;

    private java.lang.String DISPLAY_CURRENCY;

    private java.lang.String FILTER_FIELD;

    private java.lang.String FILTER_SCREEN;

    private java.lang.String FILTERVALUE_LOW;

    private java.lang.String FILTERVALUE_HIGH;

    private java.math.BigDecimal FILTERVALUE_AMOUNTLOW;

    private java.math.BigDecimal FILTERVALUE_AMOUNTHIGH;

    private int FILTER_MAX_ITEMS;

    public EBPP_CONTROL() {
    }

    public EBPP_CONTROL(
           java.lang.String DUE_DATE_LOW,
           java.lang.String DUE_DATE_HIGH,
           java.lang.String OPEN_ITEMS,
           java.lang.String CLEARED_ITEMS,
           java.lang.String PARKED_ITEMS,
           java.lang.String SELECT_FOR_PAYMENTS,
           java.lang.String PAYMENT_TYPE,
           java.lang.String PAYMENT_ID,
           java.lang.String ONLY_INVOICES,
           java.lang.String CHECK_DISPUTES,
           java.lang.String PREVIOUS_BALANCE,
           java.lang.String DISPLAY_CURRENCY,
           java.lang.String FILTER_FIELD,
           java.lang.String FILTER_SCREEN,
           java.lang.String FILTERVALUE_LOW,
           java.lang.String FILTERVALUE_HIGH,
           java.math.BigDecimal FILTERVALUE_AMOUNTLOW,
           java.math.BigDecimal FILTERVALUE_AMOUNTHIGH,
           int FILTER_MAX_ITEMS) {
           this.DUE_DATE_LOW = DUE_DATE_LOW;
           this.DUE_DATE_HIGH = DUE_DATE_HIGH;
           this.OPEN_ITEMS = OPEN_ITEMS;
           this.CLEARED_ITEMS = CLEARED_ITEMS;
           this.PARKED_ITEMS = PARKED_ITEMS;
           this.SELECT_FOR_PAYMENTS = SELECT_FOR_PAYMENTS;
           this.PAYMENT_TYPE = PAYMENT_TYPE;
           this.PAYMENT_ID = PAYMENT_ID;
           this.ONLY_INVOICES = ONLY_INVOICES;
           this.CHECK_DISPUTES = CHECK_DISPUTES;
           this.PREVIOUS_BALANCE = PREVIOUS_BALANCE;
           this.DISPLAY_CURRENCY = DISPLAY_CURRENCY;
           this.FILTER_FIELD = FILTER_FIELD;
           this.FILTER_SCREEN = FILTER_SCREEN;
           this.FILTERVALUE_LOW = FILTERVALUE_LOW;
           this.FILTERVALUE_HIGH = FILTERVALUE_HIGH;
           this.FILTERVALUE_AMOUNTLOW = FILTERVALUE_AMOUNTLOW;
           this.FILTERVALUE_AMOUNTHIGH = FILTERVALUE_AMOUNTHIGH;
           this.FILTER_MAX_ITEMS = FILTER_MAX_ITEMS;
    }


    /**
     * Gets the DUE_DATE_LOW value for this EBPP_CONTROL.
     * 
     * @return DUE_DATE_LOW
     */
    public java.lang.String getDUE_DATE_LOW() {
        return DUE_DATE_LOW;
    }


    /**
     * Sets the DUE_DATE_LOW value for this EBPP_CONTROL.
     * 
     * @param DUE_DATE_LOW
     */
    public void setDUE_DATE_LOW(java.lang.String DUE_DATE_LOW) {
        this.DUE_DATE_LOW = DUE_DATE_LOW;
    }


    /**
     * Gets the DUE_DATE_HIGH value for this EBPP_CONTROL.
     * 
     * @return DUE_DATE_HIGH
     */
    public java.lang.String getDUE_DATE_HIGH() {
        return DUE_DATE_HIGH;
    }


    /**
     * Sets the DUE_DATE_HIGH value for this EBPP_CONTROL.
     * 
     * @param DUE_DATE_HIGH
     */
    public void setDUE_DATE_HIGH(java.lang.String DUE_DATE_HIGH) {
        this.DUE_DATE_HIGH = DUE_DATE_HIGH;
    }


    /**
     * Gets the OPEN_ITEMS value for this EBPP_CONTROL.
     * 
     * @return OPEN_ITEMS
     */
    public java.lang.String getOPEN_ITEMS() {
        return OPEN_ITEMS;
    }


    /**
     * Sets the OPEN_ITEMS value for this EBPP_CONTROL.
     * 
     * @param OPEN_ITEMS
     */
    public void setOPEN_ITEMS(java.lang.String OPEN_ITEMS) {
        this.OPEN_ITEMS = OPEN_ITEMS;
    }


    /**
     * Gets the CLEARED_ITEMS value for this EBPP_CONTROL.
     * 
     * @return CLEARED_ITEMS
     */
    public java.lang.String getCLEARED_ITEMS() {
        return CLEARED_ITEMS;
    }


    /**
     * Sets the CLEARED_ITEMS value for this EBPP_CONTROL.
     * 
     * @param CLEARED_ITEMS
     */
    public void setCLEARED_ITEMS(java.lang.String CLEARED_ITEMS) {
        this.CLEARED_ITEMS = CLEARED_ITEMS;
    }


    /**
     * Gets the PARKED_ITEMS value for this EBPP_CONTROL.
     * 
     * @return PARKED_ITEMS
     */
    public java.lang.String getPARKED_ITEMS() {
        return PARKED_ITEMS;
    }


    /**
     * Sets the PARKED_ITEMS value for this EBPP_CONTROL.
     * 
     * @param PARKED_ITEMS
     */
    public void setPARKED_ITEMS(java.lang.String PARKED_ITEMS) {
        this.PARKED_ITEMS = PARKED_ITEMS;
    }


    /**
     * Gets the SELECT_FOR_PAYMENTS value for this EBPP_CONTROL.
     * 
     * @return SELECT_FOR_PAYMENTS
     */
    public java.lang.String getSELECT_FOR_PAYMENTS() {
        return SELECT_FOR_PAYMENTS;
    }


    /**
     * Sets the SELECT_FOR_PAYMENTS value for this EBPP_CONTROL.
     * 
     * @param SELECT_FOR_PAYMENTS
     */
    public void setSELECT_FOR_PAYMENTS(java.lang.String SELECT_FOR_PAYMENTS) {
        this.SELECT_FOR_PAYMENTS = SELECT_FOR_PAYMENTS;
    }


    /**
     * Gets the PAYMENT_TYPE value for this EBPP_CONTROL.
     * 
     * @return PAYMENT_TYPE
     */
    public java.lang.String getPAYMENT_TYPE() {
        return PAYMENT_TYPE;
    }


    /**
     * Sets the PAYMENT_TYPE value for this EBPP_CONTROL.
     * 
     * @param PAYMENT_TYPE
     */
    public void setPAYMENT_TYPE(java.lang.String PAYMENT_TYPE) {
        this.PAYMENT_TYPE = PAYMENT_TYPE;
    }


    /**
     * Gets the PAYMENT_ID value for this EBPP_CONTROL.
     * 
     * @return PAYMENT_ID
     */
    public java.lang.String getPAYMENT_ID() {
        return PAYMENT_ID;
    }


    /**
     * Sets the PAYMENT_ID value for this EBPP_CONTROL.
     * 
     * @param PAYMENT_ID
     */
    public void setPAYMENT_ID(java.lang.String PAYMENT_ID) {
        this.PAYMENT_ID = PAYMENT_ID;
    }


    /**
     * Gets the ONLY_INVOICES value for this EBPP_CONTROL.
     * 
     * @return ONLY_INVOICES
     */
    public java.lang.String getONLY_INVOICES() {
        return ONLY_INVOICES;
    }


    /**
     * Sets the ONLY_INVOICES value for this EBPP_CONTROL.
     * 
     * @param ONLY_INVOICES
     */
    public void setONLY_INVOICES(java.lang.String ONLY_INVOICES) {
        this.ONLY_INVOICES = ONLY_INVOICES;
    }


    /**
     * Gets the CHECK_DISPUTES value for this EBPP_CONTROL.
     * 
     * @return CHECK_DISPUTES
     */
    public java.lang.String getCHECK_DISPUTES() {
        return CHECK_DISPUTES;
    }


    /**
     * Sets the CHECK_DISPUTES value for this EBPP_CONTROL.
     * 
     * @param CHECK_DISPUTES
     */
    public void setCHECK_DISPUTES(java.lang.String CHECK_DISPUTES) {
        this.CHECK_DISPUTES = CHECK_DISPUTES;
    }


    /**
     * Gets the PREVIOUS_BALANCE value for this EBPP_CONTROL.
     * 
     * @return PREVIOUS_BALANCE
     */
    public java.lang.String getPREVIOUS_BALANCE() {
        return PREVIOUS_BALANCE;
    }


    /**
     * Sets the PREVIOUS_BALANCE value for this EBPP_CONTROL.
     * 
     * @param PREVIOUS_BALANCE
     */
    public void setPREVIOUS_BALANCE(java.lang.String PREVIOUS_BALANCE) {
        this.PREVIOUS_BALANCE = PREVIOUS_BALANCE;
    }


    /**
     * Gets the DISPLAY_CURRENCY value for this EBPP_CONTROL.
     * 
     * @return DISPLAY_CURRENCY
     */
    public java.lang.String getDISPLAY_CURRENCY() {
        return DISPLAY_CURRENCY;
    }


    /**
     * Sets the DISPLAY_CURRENCY value for this EBPP_CONTROL.
     * 
     * @param DISPLAY_CURRENCY
     */
    public void setDISPLAY_CURRENCY(java.lang.String DISPLAY_CURRENCY) {
        this.DISPLAY_CURRENCY = DISPLAY_CURRENCY;
    }


    /**
     * Gets the FILTER_FIELD value for this EBPP_CONTROL.
     * 
     * @return FILTER_FIELD
     */
    public java.lang.String getFILTER_FIELD() {
        return FILTER_FIELD;
    }


    /**
     * Sets the FILTER_FIELD value for this EBPP_CONTROL.
     * 
     * @param FILTER_FIELD
     */
    public void setFILTER_FIELD(java.lang.String FILTER_FIELD) {
        this.FILTER_FIELD = FILTER_FIELD;
    }


    /**
     * Gets the FILTER_SCREEN value for this EBPP_CONTROL.
     * 
     * @return FILTER_SCREEN
     */
    public java.lang.String getFILTER_SCREEN() {
        return FILTER_SCREEN;
    }


    /**
     * Sets the FILTER_SCREEN value for this EBPP_CONTROL.
     * 
     * @param FILTER_SCREEN
     */
    public void setFILTER_SCREEN(java.lang.String FILTER_SCREEN) {
        this.FILTER_SCREEN = FILTER_SCREEN;
    }


    /**
     * Gets the FILTERVALUE_LOW value for this EBPP_CONTROL.
     * 
     * @return FILTERVALUE_LOW
     */
    public java.lang.String getFILTERVALUE_LOW() {
        return FILTERVALUE_LOW;
    }


    /**
     * Sets the FILTERVALUE_LOW value for this EBPP_CONTROL.
     * 
     * @param FILTERVALUE_LOW
     */
    public void setFILTERVALUE_LOW(java.lang.String FILTERVALUE_LOW) {
        this.FILTERVALUE_LOW = FILTERVALUE_LOW;
    }


    /**
     * Gets the FILTERVALUE_HIGH value for this EBPP_CONTROL.
     * 
     * @return FILTERVALUE_HIGH
     */
    public java.lang.String getFILTERVALUE_HIGH() {
        return FILTERVALUE_HIGH;
    }


    /**
     * Sets the FILTERVALUE_HIGH value for this EBPP_CONTROL.
     * 
     * @param FILTERVALUE_HIGH
     */
    public void setFILTERVALUE_HIGH(java.lang.String FILTERVALUE_HIGH) {
        this.FILTERVALUE_HIGH = FILTERVALUE_HIGH;
    }


    /**
     * Gets the FILTERVALUE_AMOUNTLOW value for this EBPP_CONTROL.
     * 
     * @return FILTERVALUE_AMOUNTLOW
     */
    public java.math.BigDecimal getFILTERVALUE_AMOUNTLOW() {
        return FILTERVALUE_AMOUNTLOW;
    }


    /**
     * Sets the FILTERVALUE_AMOUNTLOW value for this EBPP_CONTROL.
     * 
     * @param FILTERVALUE_AMOUNTLOW
     */
    public void setFILTERVALUE_AMOUNTLOW(java.math.BigDecimal FILTERVALUE_AMOUNTLOW) {
        this.FILTERVALUE_AMOUNTLOW = FILTERVALUE_AMOUNTLOW;
    }


    /**
     * Gets the FILTERVALUE_AMOUNTHIGH value for this EBPP_CONTROL.
     * 
     * @return FILTERVALUE_AMOUNTHIGH
     */
    public java.math.BigDecimal getFILTERVALUE_AMOUNTHIGH() {
        return FILTERVALUE_AMOUNTHIGH;
    }


    /**
     * Sets the FILTERVALUE_AMOUNTHIGH value for this EBPP_CONTROL.
     * 
     * @param FILTERVALUE_AMOUNTHIGH
     */
    public void setFILTERVALUE_AMOUNTHIGH(java.math.BigDecimal FILTERVALUE_AMOUNTHIGH) {
        this.FILTERVALUE_AMOUNTHIGH = FILTERVALUE_AMOUNTHIGH;
    }


    /**
     * Gets the FILTER_MAX_ITEMS value for this EBPP_CONTROL.
     * 
     * @return FILTER_MAX_ITEMS
     */
    public int getFILTER_MAX_ITEMS() {
        return FILTER_MAX_ITEMS;
    }


    /**
     * Sets the FILTER_MAX_ITEMS value for this EBPP_CONTROL.
     * 
     * @param FILTER_MAX_ITEMS
     */
    public void setFILTER_MAX_ITEMS(int FILTER_MAX_ITEMS) {
        this.FILTER_MAX_ITEMS = FILTER_MAX_ITEMS;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EBPP_CONTROL)) return false;
        EBPP_CONTROL other = (EBPP_CONTROL) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.DUE_DATE_LOW==null && other.getDUE_DATE_LOW()==null) || 
             (this.DUE_DATE_LOW!=null &&
              this.DUE_DATE_LOW.equals(other.getDUE_DATE_LOW()))) &&
            ((this.DUE_DATE_HIGH==null && other.getDUE_DATE_HIGH()==null) || 
             (this.DUE_DATE_HIGH!=null &&
              this.DUE_DATE_HIGH.equals(other.getDUE_DATE_HIGH()))) &&
            ((this.OPEN_ITEMS==null && other.getOPEN_ITEMS()==null) || 
             (this.OPEN_ITEMS!=null &&
              this.OPEN_ITEMS.equals(other.getOPEN_ITEMS()))) &&
            ((this.CLEARED_ITEMS==null && other.getCLEARED_ITEMS()==null) || 
             (this.CLEARED_ITEMS!=null &&
              this.CLEARED_ITEMS.equals(other.getCLEARED_ITEMS()))) &&
            ((this.PARKED_ITEMS==null && other.getPARKED_ITEMS()==null) || 
             (this.PARKED_ITEMS!=null &&
              this.PARKED_ITEMS.equals(other.getPARKED_ITEMS()))) &&
            ((this.SELECT_FOR_PAYMENTS==null && other.getSELECT_FOR_PAYMENTS()==null) || 
             (this.SELECT_FOR_PAYMENTS!=null &&
              this.SELECT_FOR_PAYMENTS.equals(other.getSELECT_FOR_PAYMENTS()))) &&
            ((this.PAYMENT_TYPE==null && other.getPAYMENT_TYPE()==null) || 
             (this.PAYMENT_TYPE!=null &&
              this.PAYMENT_TYPE.equals(other.getPAYMENT_TYPE()))) &&
            ((this.PAYMENT_ID==null && other.getPAYMENT_ID()==null) || 
             (this.PAYMENT_ID!=null &&
              this.PAYMENT_ID.equals(other.getPAYMENT_ID()))) &&
            ((this.ONLY_INVOICES==null && other.getONLY_INVOICES()==null) || 
             (this.ONLY_INVOICES!=null &&
              this.ONLY_INVOICES.equals(other.getONLY_INVOICES()))) &&
            ((this.CHECK_DISPUTES==null && other.getCHECK_DISPUTES()==null) || 
             (this.CHECK_DISPUTES!=null &&
              this.CHECK_DISPUTES.equals(other.getCHECK_DISPUTES()))) &&
            ((this.PREVIOUS_BALANCE==null && other.getPREVIOUS_BALANCE()==null) || 
             (this.PREVIOUS_BALANCE!=null &&
              this.PREVIOUS_BALANCE.equals(other.getPREVIOUS_BALANCE()))) &&
            ((this.DISPLAY_CURRENCY==null && other.getDISPLAY_CURRENCY()==null) || 
             (this.DISPLAY_CURRENCY!=null &&
              this.DISPLAY_CURRENCY.equals(other.getDISPLAY_CURRENCY()))) &&
            ((this.FILTER_FIELD==null && other.getFILTER_FIELD()==null) || 
             (this.FILTER_FIELD!=null &&
              this.FILTER_FIELD.equals(other.getFILTER_FIELD()))) &&
            ((this.FILTER_SCREEN==null && other.getFILTER_SCREEN()==null) || 
             (this.FILTER_SCREEN!=null &&
              this.FILTER_SCREEN.equals(other.getFILTER_SCREEN()))) &&
            ((this.FILTERVALUE_LOW==null && other.getFILTERVALUE_LOW()==null) || 
             (this.FILTERVALUE_LOW!=null &&
              this.FILTERVALUE_LOW.equals(other.getFILTERVALUE_LOW()))) &&
            ((this.FILTERVALUE_HIGH==null && other.getFILTERVALUE_HIGH()==null) || 
             (this.FILTERVALUE_HIGH!=null &&
              this.FILTERVALUE_HIGH.equals(other.getFILTERVALUE_HIGH()))) &&
            ((this.FILTERVALUE_AMOUNTLOW==null && other.getFILTERVALUE_AMOUNTLOW()==null) || 
             (this.FILTERVALUE_AMOUNTLOW!=null &&
              this.FILTERVALUE_AMOUNTLOW.equals(other.getFILTERVALUE_AMOUNTLOW()))) &&
            ((this.FILTERVALUE_AMOUNTHIGH==null && other.getFILTERVALUE_AMOUNTHIGH()==null) || 
             (this.FILTERVALUE_AMOUNTHIGH!=null &&
              this.FILTERVALUE_AMOUNTHIGH.equals(other.getFILTERVALUE_AMOUNTHIGH()))) &&
            this.FILTER_MAX_ITEMS == other.getFILTER_MAX_ITEMS();
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
        if (getDUE_DATE_LOW() != null) {
            _hashCode += getDUE_DATE_LOW().hashCode();
        }
        if (getDUE_DATE_HIGH() != null) {
            _hashCode += getDUE_DATE_HIGH().hashCode();
        }
        if (getOPEN_ITEMS() != null) {
            _hashCode += getOPEN_ITEMS().hashCode();
        }
        if (getCLEARED_ITEMS() != null) {
            _hashCode += getCLEARED_ITEMS().hashCode();
        }
        if (getPARKED_ITEMS() != null) {
            _hashCode += getPARKED_ITEMS().hashCode();
        }
        if (getSELECT_FOR_PAYMENTS() != null) {
            _hashCode += getSELECT_FOR_PAYMENTS().hashCode();
        }
        if (getPAYMENT_TYPE() != null) {
            _hashCode += getPAYMENT_TYPE().hashCode();
        }
        if (getPAYMENT_ID() != null) {
            _hashCode += getPAYMENT_ID().hashCode();
        }
        if (getONLY_INVOICES() != null) {
            _hashCode += getONLY_INVOICES().hashCode();
        }
        if (getCHECK_DISPUTES() != null) {
            _hashCode += getCHECK_DISPUTES().hashCode();
        }
        if (getPREVIOUS_BALANCE() != null) {
            _hashCode += getPREVIOUS_BALANCE().hashCode();
        }
        if (getDISPLAY_CURRENCY() != null) {
            _hashCode += getDISPLAY_CURRENCY().hashCode();
        }
        if (getFILTER_FIELD() != null) {
            _hashCode += getFILTER_FIELD().hashCode();
        }
        if (getFILTER_SCREEN() != null) {
            _hashCode += getFILTER_SCREEN().hashCode();
        }
        if (getFILTERVALUE_LOW() != null) {
            _hashCode += getFILTERVALUE_LOW().hashCode();
        }
        if (getFILTERVALUE_HIGH() != null) {
            _hashCode += getFILTERVALUE_HIGH().hashCode();
        }
        if (getFILTERVALUE_AMOUNTLOW() != null) {
            _hashCode += getFILTERVALUE_AMOUNTLOW().hashCode();
        }
        if (getFILTERVALUE_AMOUNTHIGH() != null) {
            _hashCode += getFILTERVALUE_AMOUNTHIGH().hashCode();
        }
        _hashCode += getFILTER_MAX_ITEMS();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EBPP_CONTROL.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "EBPP_CONTROL"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DUE_DATE_LOW");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DUE_DATE_LOW"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DUE_DATE_HIGH");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DUE_DATE_HIGH"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("OPEN_ITEMS");
        elemField.setXmlName(new javax.xml.namespace.QName("", "OPEN_ITEMS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CLEARED_ITEMS");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CLEARED_ITEMS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PARKED_ITEMS");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PARKED_ITEMS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SELECT_FOR_PAYMENTS");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SELECT_FOR_PAYMENTS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PAYMENT_TYPE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PAYMENT_TYPE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PAYMENT_ID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PAYMENT_ID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ONLY_INVOICES");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ONLY_INVOICES"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CHECK_DISPUTES");
        elemField.setXmlName(new javax.xml.namespace.QName("", "CHECK_DISPUTES"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PREVIOUS_BALANCE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PREVIOUS_BALANCE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DISPLAY_CURRENCY");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DISPLAY_CURRENCY"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FILTER_FIELD");
        elemField.setXmlName(new javax.xml.namespace.QName("", "FILTER_FIELD"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FILTER_SCREEN");
        elemField.setXmlName(new javax.xml.namespace.QName("", "FILTER_SCREEN"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FILTERVALUE_LOW");
        elemField.setXmlName(new javax.xml.namespace.QName("", "FILTERVALUE_LOW"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FILTERVALUE_HIGH");
        elemField.setXmlName(new javax.xml.namespace.QName("", "FILTERVALUE_HIGH"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FILTERVALUE_AMOUNTLOW");
        elemField.setXmlName(new javax.xml.namespace.QName("", "FILTERVALUE_AMOUNTLOW"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FILTERVALUE_AMOUNTHIGH");
        elemField.setXmlName(new javax.xml.namespace.QName("", "FILTERVALUE_AMOUNTHIGH"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FILTER_MAX_ITEMS");
        elemField.setXmlName(new javax.xml.namespace.QName("", "FILTER_MAX_ITEMS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
