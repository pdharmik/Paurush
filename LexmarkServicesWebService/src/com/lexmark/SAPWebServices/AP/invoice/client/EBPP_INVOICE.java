/**
 * EBPP_INVOICE.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SAPWebServices.AP.invoice.client;

public class EBPP_INVOICE  implements java.io.Serializable {
    private java.lang.String XMARK;

    private java.lang.String INVID;

    private java.lang.String DUE_DATE;

    private java.lang.String CURRENCY;

    private java.math.BigDecimal AMOUNT;

    private java.math.BigDecimal OPENAMOUNT;

    private java.lang.String NON_PAYABLE;

    private java.lang.String STATUS;

    private java.lang.String PAYMENTGROUP;

    private java.lang.String REFID;

    private java.lang.String IS_DISPUTED;

    private java.lang.String IS_DISPUTEABLE;

    private java.lang.String LINCT;

    private java.lang.String INVCLASS;

    private java.lang.String DISPLAYSTATUS;

    private java.lang.String INV_DATE;

    private java.lang.String NON_PARTIALPAY;

    private java.lang.String NON_STOPABLE;

    private java.lang.String CURRENCY_ORI;

    private java.math.BigDecimal AMOUNT_ORI;

    private java.math.BigDecimal OPENAMOUNT_ORI;

    private java.math.BigDecimal DISCOUNT;

    private java.lang.String DISC_DATE;

    private java.math.BigDecimal DISC_PERC;

    private java.lang.String XPDF;

    private java.lang.String XXML;

    private java.lang.String XHTML;

    private java.lang.String XRAW;

    private java.lang.String XIBU;

    private java.lang.String XCUST1;

    private java.lang.String XCUST2;

    private java.lang.String XMULTARCH;

    private java.lang.String XTIF;

    private java.lang.String XOTHERTYPE;

    private java.lang.String INVOICE_PATH;

    private java.lang.String XURL;

    private java.lang.String x_NEW_BILL;

    private java.lang.String x_NEW_CONTACT;

    private java.lang.String BELNR;

    private java.lang.String GJAHR;

    private java.lang.String BUZEI;

    private java.lang.String BUDAT;

    private java.lang.String XBLNR;

    private java.lang.String VBELN;

    private java.lang.String BUKRS;

    private java.lang.String SHKZG;

    private java.lang.String BLDAT;

    private java.lang.String DOCT1;

    private java.lang.String PAYINFO;

    private java.lang.String INVGROUP;

    private java.lang.String EXTERNAL_INV_DESCR;

    private java.lang.String IS_PARKED;

    private java.lang.String PAYMENT_BLOCK;

    private java.lang.String XREF1;

    private java.lang.String XREF2;

    private java.lang.String XREF3;

    private java.lang.String ZZZUONR;

    private java.lang.String ZZREBZG;

    public EBPP_INVOICE() {
    }

    public EBPP_INVOICE(
           java.lang.String XMARK,
           java.lang.String INVID,
           java.lang.String DUE_DATE,
           java.lang.String CURRENCY,
           java.math.BigDecimal AMOUNT,
           java.math.BigDecimal OPENAMOUNT,
           java.lang.String NON_PAYABLE,
           java.lang.String STATUS,
           java.lang.String PAYMENTGROUP,
           java.lang.String REFID,
           java.lang.String IS_DISPUTED,
           java.lang.String IS_DISPUTEABLE,
           java.lang.String LINCT,
           java.lang.String INVCLASS,
           java.lang.String DISPLAYSTATUS,
           java.lang.String INV_DATE,
           java.lang.String NON_PARTIALPAY,
           java.lang.String NON_STOPABLE,
           java.lang.String CURRENCY_ORI,
           java.math.BigDecimal AMOUNT_ORI,
           java.math.BigDecimal OPENAMOUNT_ORI,
           java.math.BigDecimal DISCOUNT,
           java.lang.String DISC_DATE,
           java.math.BigDecimal DISC_PERC,
           java.lang.String XPDF,
           java.lang.String XXML,
           java.lang.String XHTML,
           java.lang.String XRAW,
           java.lang.String XIBU,
           java.lang.String XCUST1,
           java.lang.String XCUST2,
           java.lang.String XMULTARCH,
           java.lang.String XTIF,
           java.lang.String XOTHERTYPE,
           java.lang.String INVOICE_PATH,
           java.lang.String XURL,
           java.lang.String x_NEW_BILL,
           java.lang.String x_NEW_CONTACT,
           java.lang.String BELNR,
           java.lang.String GJAHR,
           java.lang.String BUZEI,
           java.lang.String BUDAT,
           java.lang.String XBLNR,
           java.lang.String VBELN,
           java.lang.String BUKRS,
           java.lang.String SHKZG,
           java.lang.String BLDAT,
           java.lang.String DOCT1,
           java.lang.String PAYINFO,
           java.lang.String INVGROUP,
           java.lang.String EXTERNAL_INV_DESCR,
           java.lang.String IS_PARKED,
           java.lang.String PAYMENT_BLOCK,
           java.lang.String XREF1,
           java.lang.String XREF2,
           java.lang.String XREF3,
           java.lang.String ZZZUONR,
           java.lang.String ZZREBZG) {
           this.XMARK = XMARK;
           this.INVID = INVID;
           this.DUE_DATE = DUE_DATE;
           this.CURRENCY = CURRENCY;
           this.AMOUNT = AMOUNT;
           this.OPENAMOUNT = OPENAMOUNT;
           this.NON_PAYABLE = NON_PAYABLE;
           this.STATUS = STATUS;
           this.PAYMENTGROUP = PAYMENTGROUP;
           this.REFID = REFID;
           this.IS_DISPUTED = IS_DISPUTED;
           this.IS_DISPUTEABLE = IS_DISPUTEABLE;
           this.LINCT = LINCT;
           this.INVCLASS = INVCLASS;
           this.DISPLAYSTATUS = DISPLAYSTATUS;
           this.INV_DATE = INV_DATE;
           this.NON_PARTIALPAY = NON_PARTIALPAY;
           this.NON_STOPABLE = NON_STOPABLE;
           this.CURRENCY_ORI = CURRENCY_ORI;
           this.AMOUNT_ORI = AMOUNT_ORI;
           this.OPENAMOUNT_ORI = OPENAMOUNT_ORI;
           this.DISCOUNT = DISCOUNT;
           this.DISC_DATE = DISC_DATE;
           this.DISC_PERC = DISC_PERC;
           this.XPDF = XPDF;
           this.XXML = XXML;
           this.XHTML = XHTML;
           this.XRAW = XRAW;
           this.XIBU = XIBU;
           this.XCUST1 = XCUST1;
           this.XCUST2 = XCUST2;
           this.XMULTARCH = XMULTARCH;
           this.XTIF = XTIF;
           this.XOTHERTYPE = XOTHERTYPE;
           this.INVOICE_PATH = INVOICE_PATH;
           this.XURL = XURL;
           this.x_NEW_BILL = x_NEW_BILL;
           this.x_NEW_CONTACT = x_NEW_CONTACT;
           this.BELNR = BELNR;
           this.GJAHR = GJAHR;
           this.BUZEI = BUZEI;
           this.BUDAT = BUDAT;
           this.XBLNR = XBLNR;
           this.VBELN = VBELN;
           this.BUKRS = BUKRS;
           this.SHKZG = SHKZG;
           this.BLDAT = BLDAT;
           this.DOCT1 = DOCT1;
           this.PAYINFO = PAYINFO;
           this.INVGROUP = INVGROUP;
           this.EXTERNAL_INV_DESCR = EXTERNAL_INV_DESCR;
           this.IS_PARKED = IS_PARKED;
           this.PAYMENT_BLOCK = PAYMENT_BLOCK;
           this.XREF1 = XREF1;
           this.XREF2 = XREF2;
           this.XREF3 = XREF3;
           this.ZZZUONR = ZZZUONR;
           this.ZZREBZG = ZZREBZG;
    }


    /**
     * Gets the XMARK value for this EBPP_INVOICE.
     * 
     * @return XMARK
     */
    public java.lang.String getXMARK() {
        return XMARK;
    }


    /**
     * Sets the XMARK value for this EBPP_INVOICE.
     * 
     * @param XMARK
     */
    public void setXMARK(java.lang.String XMARK) {
        this.XMARK = XMARK;
    }


    /**
     * Gets the INVID value for this EBPP_INVOICE.
     * 
     * @return INVID
     */
    public java.lang.String getINVID() {
        return INVID;
    }


    /**
     * Sets the INVID value for this EBPP_INVOICE.
     * 
     * @param INVID
     */
    public void setINVID(java.lang.String INVID) {
        this.INVID = INVID;
    }


    /**
     * Gets the DUE_DATE value for this EBPP_INVOICE.
     * 
     * @return DUE_DATE
     */
    public java.lang.String getDUE_DATE() {
        return DUE_DATE;
    }


    /**
     * Sets the DUE_DATE value for this EBPP_INVOICE.
     * 
     * @param DUE_DATE
     */
    public void setDUE_DATE(java.lang.String DUE_DATE) {
        this.DUE_DATE = DUE_DATE;
    }


    /**
     * Gets the CURRENCY value for this EBPP_INVOICE.
     * 
     * @return CURRENCY
     */
    public java.lang.String getCURRENCY() {
        return CURRENCY;
    }


    /**
     * Sets the CURRENCY value for this EBPP_INVOICE.
     * 
     * @param CURRENCY
     */
    public void setCURRENCY(java.lang.String CURRENCY) {
        this.CURRENCY = CURRENCY;
    }


    /**
     * Gets the AMOUNT value for this EBPP_INVOICE.
     * 
     * @return AMOUNT
     */
    public java.math.BigDecimal getAMOUNT() {
        return AMOUNT;
    }


    /**
     * Sets the AMOUNT value for this EBPP_INVOICE.
     * 
     * @param AMOUNT
     */
    public void setAMOUNT(java.math.BigDecimal AMOUNT) {
        this.AMOUNT = AMOUNT;
    }


    /**
     * Gets the OPENAMOUNT value for this EBPP_INVOICE.
     * 
     * @return OPENAMOUNT
     */
    public java.math.BigDecimal getOPENAMOUNT() {
        return OPENAMOUNT;
    }


    /**
     * Sets the OPENAMOUNT value for this EBPP_INVOICE.
     * 
     * @param OPENAMOUNT
     */
    public void setOPENAMOUNT(java.math.BigDecimal OPENAMOUNT) {
        this.OPENAMOUNT = OPENAMOUNT;
    }


    /**
     * Gets the NON_PAYABLE value for this EBPP_INVOICE.
     * 
     * @return NON_PAYABLE
     */
    public java.lang.String getNON_PAYABLE() {
        return NON_PAYABLE;
    }


    /**
     * Sets the NON_PAYABLE value for this EBPP_INVOICE.
     * 
     * @param NON_PAYABLE
     */
    public void setNON_PAYABLE(java.lang.String NON_PAYABLE) {
        this.NON_PAYABLE = NON_PAYABLE;
    }


    /**
     * Gets the STATUS value for this EBPP_INVOICE.
     * 
     * @return STATUS
     */
    public java.lang.String getSTATUS() {
        return STATUS;
    }


    /**
     * Sets the STATUS value for this EBPP_INVOICE.
     * 
     * @param STATUS
     */
    public void setSTATUS(java.lang.String STATUS) {
        this.STATUS = STATUS;
    }


    /**
     * Gets the PAYMENTGROUP value for this EBPP_INVOICE.
     * 
     * @return PAYMENTGROUP
     */
    public java.lang.String getPAYMENTGROUP() {
        return PAYMENTGROUP;
    }


    /**
     * Sets the PAYMENTGROUP value for this EBPP_INVOICE.
     * 
     * @param PAYMENTGROUP
     */
    public void setPAYMENTGROUP(java.lang.String PAYMENTGROUP) {
        this.PAYMENTGROUP = PAYMENTGROUP;
    }


    /**
     * Gets the REFID value for this EBPP_INVOICE.
     * 
     * @return REFID
     */
    public java.lang.String getREFID() {
        return REFID;
    }


    /**
     * Sets the REFID value for this EBPP_INVOICE.
     * 
     * @param REFID
     */
    public void setREFID(java.lang.String REFID) {
        this.REFID = REFID;
    }


    /**
     * Gets the IS_DISPUTED value for this EBPP_INVOICE.
     * 
     * @return IS_DISPUTED
     */
    public java.lang.String getIS_DISPUTED() {
        return IS_DISPUTED;
    }


    /**
     * Sets the IS_DISPUTED value for this EBPP_INVOICE.
     * 
     * @param IS_DISPUTED
     */
    public void setIS_DISPUTED(java.lang.String IS_DISPUTED) {
        this.IS_DISPUTED = IS_DISPUTED;
    }


    /**
     * Gets the IS_DISPUTEABLE value for this EBPP_INVOICE.
     * 
     * @return IS_DISPUTEABLE
     */
    public java.lang.String getIS_DISPUTEABLE() {
        return IS_DISPUTEABLE;
    }


    /**
     * Sets the IS_DISPUTEABLE value for this EBPP_INVOICE.
     * 
     * @param IS_DISPUTEABLE
     */
    public void setIS_DISPUTEABLE(java.lang.String IS_DISPUTEABLE) {
        this.IS_DISPUTEABLE = IS_DISPUTEABLE;
    }


    /**
     * Gets the LINCT value for this EBPP_INVOICE.
     * 
     * @return LINCT
     */
    public java.lang.String getLINCT() {
        return LINCT;
    }


    /**
     * Sets the LINCT value for this EBPP_INVOICE.
     * 
     * @param LINCT
     */
    public void setLINCT(java.lang.String LINCT) {
        this.LINCT = LINCT;
    }


    /**
     * Gets the INVCLASS value for this EBPP_INVOICE.
     * 
     * @return INVCLASS
     */
    public java.lang.String getINVCLASS() {
        return INVCLASS;
    }


    /**
     * Sets the INVCLASS value for this EBPP_INVOICE.
     * 
     * @param INVCLASS
     */
    public void setINVCLASS(java.lang.String INVCLASS) {
        this.INVCLASS = INVCLASS;
    }


    /**
     * Gets the DISPLAYSTATUS value for this EBPP_INVOICE.
     * 
     * @return DISPLAYSTATUS
     */
    public java.lang.String getDISPLAYSTATUS() {
        return DISPLAYSTATUS;
    }


    /**
     * Sets the DISPLAYSTATUS value for this EBPP_INVOICE.
     * 
     * @param DISPLAYSTATUS
     */
    public void setDISPLAYSTATUS(java.lang.String DISPLAYSTATUS) {
        this.DISPLAYSTATUS = DISPLAYSTATUS;
    }


    /**
     * Gets the INV_DATE value for this EBPP_INVOICE.
     * 
     * @return INV_DATE
     */
    public java.lang.String getINV_DATE() {
        return INV_DATE;
    }


    /**
     * Sets the INV_DATE value for this EBPP_INVOICE.
     * 
     * @param INV_DATE
     */
    public void setINV_DATE(java.lang.String INV_DATE) {
        this.INV_DATE = INV_DATE;
    }


    /**
     * Gets the NON_PARTIALPAY value for this EBPP_INVOICE.
     * 
     * @return NON_PARTIALPAY
     */
    public java.lang.String getNON_PARTIALPAY() {
        return NON_PARTIALPAY;
    }


    /**
     * Sets the NON_PARTIALPAY value for this EBPP_INVOICE.
     * 
     * @param NON_PARTIALPAY
     */
    public void setNON_PARTIALPAY(java.lang.String NON_PARTIALPAY) {
        this.NON_PARTIALPAY = NON_PARTIALPAY;
    }


    /**
     * Gets the NON_STOPABLE value for this EBPP_INVOICE.
     * 
     * @return NON_STOPABLE
     */
    public java.lang.String getNON_STOPABLE() {
        return NON_STOPABLE;
    }


    /**
     * Sets the NON_STOPABLE value for this EBPP_INVOICE.
     * 
     * @param NON_STOPABLE
     */
    public void setNON_STOPABLE(java.lang.String NON_STOPABLE) {
        this.NON_STOPABLE = NON_STOPABLE;
    }


    /**
     * Gets the CURRENCY_ORI value for this EBPP_INVOICE.
     * 
     * @return CURRENCY_ORI
     */
    public java.lang.String getCURRENCY_ORI() {
        return CURRENCY_ORI;
    }


    /**
     * Sets the CURRENCY_ORI value for this EBPP_INVOICE.
     * 
     * @param CURRENCY_ORI
     */
    public void setCURRENCY_ORI(java.lang.String CURRENCY_ORI) {
        this.CURRENCY_ORI = CURRENCY_ORI;
    }


    /**
     * Gets the AMOUNT_ORI value for this EBPP_INVOICE.
     * 
     * @return AMOUNT_ORI
     */
    public java.math.BigDecimal getAMOUNT_ORI() {
        return AMOUNT_ORI;
    }


    /**
     * Sets the AMOUNT_ORI value for this EBPP_INVOICE.
     * 
     * @param AMOUNT_ORI
     */
    public void setAMOUNT_ORI(java.math.BigDecimal AMOUNT_ORI) {
        this.AMOUNT_ORI = AMOUNT_ORI;
    }


    /**
     * Gets the OPENAMOUNT_ORI value for this EBPP_INVOICE.
     * 
     * @return OPENAMOUNT_ORI
     */
    public java.math.BigDecimal getOPENAMOUNT_ORI() {
        return OPENAMOUNT_ORI;
    }


    /**
     * Sets the OPENAMOUNT_ORI value for this EBPP_INVOICE.
     * 
     * @param OPENAMOUNT_ORI
     */
    public void setOPENAMOUNT_ORI(java.math.BigDecimal OPENAMOUNT_ORI) {
        this.OPENAMOUNT_ORI = OPENAMOUNT_ORI;
    }


    /**
     * Gets the DISCOUNT value for this EBPP_INVOICE.
     * 
     * @return DISCOUNT
     */
    public java.math.BigDecimal getDISCOUNT() {
        return DISCOUNT;
    }


    /**
     * Sets the DISCOUNT value for this EBPP_INVOICE.
     * 
     * @param DISCOUNT
     */
    public void setDISCOUNT(java.math.BigDecimal DISCOUNT) {
        this.DISCOUNT = DISCOUNT;
    }


    /**
     * Gets the DISC_DATE value for this EBPP_INVOICE.
     * 
     * @return DISC_DATE
     */
    public java.lang.String getDISC_DATE() {
        return DISC_DATE;
    }


    /**
     * Sets the DISC_DATE value for this EBPP_INVOICE.
     * 
     * @param DISC_DATE
     */
    public void setDISC_DATE(java.lang.String DISC_DATE) {
        this.DISC_DATE = DISC_DATE;
    }


    /**
     * Gets the DISC_PERC value for this EBPP_INVOICE.
     * 
     * @return DISC_PERC
     */
    public java.math.BigDecimal getDISC_PERC() {
        return DISC_PERC;
    }


    /**
     * Sets the DISC_PERC value for this EBPP_INVOICE.
     * 
     * @param DISC_PERC
     */
    public void setDISC_PERC(java.math.BigDecimal DISC_PERC) {
        this.DISC_PERC = DISC_PERC;
    }


    /**
     * Gets the XPDF value for this EBPP_INVOICE.
     * 
     * @return XPDF
     */
    public java.lang.String getXPDF() {
        return XPDF;
    }


    /**
     * Sets the XPDF value for this EBPP_INVOICE.
     * 
     * @param XPDF
     */
    public void setXPDF(java.lang.String XPDF) {
        this.XPDF = XPDF;
    }


    /**
     * Gets the XXML value for this EBPP_INVOICE.
     * 
     * @return XXML
     */
    public java.lang.String getXXML() {
        return XXML;
    }


    /**
     * Sets the XXML value for this EBPP_INVOICE.
     * 
     * @param XXML
     */
    public void setXXML(java.lang.String XXML) {
        this.XXML = XXML;
    }


    /**
     * Gets the XHTML value for this EBPP_INVOICE.
     * 
     * @return XHTML
     */
    public java.lang.String getXHTML() {
        return XHTML;
    }


    /**
     * Sets the XHTML value for this EBPP_INVOICE.
     * 
     * @param XHTML
     */
    public void setXHTML(java.lang.String XHTML) {
        this.XHTML = XHTML;
    }


    /**
     * Gets the XRAW value for this EBPP_INVOICE.
     * 
     * @return XRAW
     */
    public java.lang.String getXRAW() {
        return XRAW;
    }


    /**
     * Sets the XRAW value for this EBPP_INVOICE.
     * 
     * @param XRAW
     */
    public void setXRAW(java.lang.String XRAW) {
        this.XRAW = XRAW;
    }


    /**
     * Gets the XIBU value for this EBPP_INVOICE.
     * 
     * @return XIBU
     */
    public java.lang.String getXIBU() {
        return XIBU;
    }


    /**
     * Sets the XIBU value for this EBPP_INVOICE.
     * 
     * @param XIBU
     */
    public void setXIBU(java.lang.String XIBU) {
        this.XIBU = XIBU;
    }


    /**
     * Gets the XCUST1 value for this EBPP_INVOICE.
     * 
     * @return XCUST1
     */
    public java.lang.String getXCUST1() {
        return XCUST1;
    }


    /**
     * Sets the XCUST1 value for this EBPP_INVOICE.
     * 
     * @param XCUST1
     */
    public void setXCUST1(java.lang.String XCUST1) {
        this.XCUST1 = XCUST1;
    }


    /**
     * Gets the XCUST2 value for this EBPP_INVOICE.
     * 
     * @return XCUST2
     */
    public java.lang.String getXCUST2() {
        return XCUST2;
    }


    /**
     * Sets the XCUST2 value for this EBPP_INVOICE.
     * 
     * @param XCUST2
     */
    public void setXCUST2(java.lang.String XCUST2) {
        this.XCUST2 = XCUST2;
    }


    /**
     * Gets the XMULTARCH value for this EBPP_INVOICE.
     * 
     * @return XMULTARCH
     */
    public java.lang.String getXMULTARCH() {
        return XMULTARCH;
    }


    /**
     * Sets the XMULTARCH value for this EBPP_INVOICE.
     * 
     * @param XMULTARCH
     */
    public void setXMULTARCH(java.lang.String XMULTARCH) {
        this.XMULTARCH = XMULTARCH;
    }


    /**
     * Gets the XTIF value for this EBPP_INVOICE.
     * 
     * @return XTIF
     */
    public java.lang.String getXTIF() {
        return XTIF;
    }


    /**
     * Sets the XTIF value for this EBPP_INVOICE.
     * 
     * @param XTIF
     */
    public void setXTIF(java.lang.String XTIF) {
        this.XTIF = XTIF;
    }


    /**
     * Gets the XOTHERTYPE value for this EBPP_INVOICE.
     * 
     * @return XOTHERTYPE
     */
    public java.lang.String getXOTHERTYPE() {
        return XOTHERTYPE;
    }


    /**
     * Sets the XOTHERTYPE value for this EBPP_INVOICE.
     * 
     * @param XOTHERTYPE
     */
    public void setXOTHERTYPE(java.lang.String XOTHERTYPE) {
        this.XOTHERTYPE = XOTHERTYPE;
    }


    /**
     * Gets the INVOICE_PATH value for this EBPP_INVOICE.
     * 
     * @return INVOICE_PATH
     */
    public java.lang.String getINVOICE_PATH() {
        return INVOICE_PATH;
    }


    /**
     * Sets the INVOICE_PATH value for this EBPP_INVOICE.
     * 
     * @param INVOICE_PATH
     */
    public void setINVOICE_PATH(java.lang.String INVOICE_PATH) {
        this.INVOICE_PATH = INVOICE_PATH;
    }


    /**
     * Gets the XURL value for this EBPP_INVOICE.
     * 
     * @return XURL
     */
    public java.lang.String getXURL() {
        return XURL;
    }


    /**
     * Sets the XURL value for this EBPP_INVOICE.
     * 
     * @param XURL
     */
    public void setXURL(java.lang.String XURL) {
        this.XURL = XURL;
    }


    /**
     * Gets the x_NEW_BILL value for this EBPP_INVOICE.
     * 
     * @return x_NEW_BILL
     */
    public java.lang.String getX_NEW_BILL() {
        return x_NEW_BILL;
    }


    /**
     * Sets the x_NEW_BILL value for this EBPP_INVOICE.
     * 
     * @param x_NEW_BILL
     */
    public void setX_NEW_BILL(java.lang.String x_NEW_BILL) {
        this.x_NEW_BILL = x_NEW_BILL;
    }


    /**
     * Gets the x_NEW_CONTACT value for this EBPP_INVOICE.
     * 
     * @return x_NEW_CONTACT
     */
    public java.lang.String getX_NEW_CONTACT() {
        return x_NEW_CONTACT;
    }


    /**
     * Sets the x_NEW_CONTACT value for this EBPP_INVOICE.
     * 
     * @param x_NEW_CONTACT
     */
    public void setX_NEW_CONTACT(java.lang.String x_NEW_CONTACT) {
        this.x_NEW_CONTACT = x_NEW_CONTACT;
    }


    /**
     * Gets the BELNR value for this EBPP_INVOICE.
     * 
     * @return BELNR
     */
    public java.lang.String getBELNR() {
        return BELNR;
    }


    /**
     * Sets the BELNR value for this EBPP_INVOICE.
     * 
     * @param BELNR
     */
    public void setBELNR(java.lang.String BELNR) {
        this.BELNR = BELNR;
    }


    /**
     * Gets the GJAHR value for this EBPP_INVOICE.
     * 
     * @return GJAHR
     */
    public java.lang.String getGJAHR() {
        return GJAHR;
    }


    /**
     * Sets the GJAHR value for this EBPP_INVOICE.
     * 
     * @param GJAHR
     */
    public void setGJAHR(java.lang.String GJAHR) {
        this.GJAHR = GJAHR;
    }


    /**
     * Gets the BUZEI value for this EBPP_INVOICE.
     * 
     * @return BUZEI
     */
    public java.lang.String getBUZEI() {
        return BUZEI;
    }


    /**
     * Sets the BUZEI value for this EBPP_INVOICE.
     * 
     * @param BUZEI
     */
    public void setBUZEI(java.lang.String BUZEI) {
        this.BUZEI = BUZEI;
    }


    /**
     * Gets the BUDAT value for this EBPP_INVOICE.
     * 
     * @return BUDAT
     */
    public java.lang.String getBUDAT() {
        return BUDAT;
    }


    /**
     * Sets the BUDAT value for this EBPP_INVOICE.
     * 
     * @param BUDAT
     */
    public void setBUDAT(java.lang.String BUDAT) {
        this.BUDAT = BUDAT;
    }


    /**
     * Gets the XBLNR value for this EBPP_INVOICE.
     * 
     * @return XBLNR
     */
    public java.lang.String getXBLNR() {
        return XBLNR;
    }


    /**
     * Sets the XBLNR value for this EBPP_INVOICE.
     * 
     * @param XBLNR
     */
    public void setXBLNR(java.lang.String XBLNR) {
        this.XBLNR = XBLNR;
    }


    /**
     * Gets the VBELN value for this EBPP_INVOICE.
     * 
     * @return VBELN
     */
    public java.lang.String getVBELN() {
        return VBELN;
    }


    /**
     * Sets the VBELN value for this EBPP_INVOICE.
     * 
     * @param VBELN
     */
    public void setVBELN(java.lang.String VBELN) {
        this.VBELN = VBELN;
    }


    /**
     * Gets the BUKRS value for this EBPP_INVOICE.
     * 
     * @return BUKRS
     */
    public java.lang.String getBUKRS() {
        return BUKRS;
    }


    /**
     * Sets the BUKRS value for this EBPP_INVOICE.
     * 
     * @param BUKRS
     */
    public void setBUKRS(java.lang.String BUKRS) {
        this.BUKRS = BUKRS;
    }


    /**
     * Gets the SHKZG value for this EBPP_INVOICE.
     * 
     * @return SHKZG
     */
    public java.lang.String getSHKZG() {
        return SHKZG;
    }


    /**
     * Sets the SHKZG value for this EBPP_INVOICE.
     * 
     * @param SHKZG
     */
    public void setSHKZG(java.lang.String SHKZG) {
        this.SHKZG = SHKZG;
    }


    /**
     * Gets the BLDAT value for this EBPP_INVOICE.
     * 
     * @return BLDAT
     */
    public java.lang.String getBLDAT() {
        return BLDAT;
    }


    /**
     * Sets the BLDAT value for this EBPP_INVOICE.
     * 
     * @param BLDAT
     */
    public void setBLDAT(java.lang.String BLDAT) {
        this.BLDAT = BLDAT;
    }


    /**
     * Gets the DOCT1 value for this EBPP_INVOICE.
     * 
     * @return DOCT1
     */
    public java.lang.String getDOCT1() {
        return DOCT1;
    }


    /**
     * Sets the DOCT1 value for this EBPP_INVOICE.
     * 
     * @param DOCT1
     */
    public void setDOCT1(java.lang.String DOCT1) {
        this.DOCT1 = DOCT1;
    }


    /**
     * Gets the PAYINFO value for this EBPP_INVOICE.
     * 
     * @return PAYINFO
     */
    public java.lang.String getPAYINFO() {
        return PAYINFO;
    }


    /**
     * Sets the PAYINFO value for this EBPP_INVOICE.
     * 
     * @param PAYINFO
     */
    public void setPAYINFO(java.lang.String PAYINFO) {
        this.PAYINFO = PAYINFO;
    }


    /**
     * Gets the INVGROUP value for this EBPP_INVOICE.
     * 
     * @return INVGROUP
     */
    public java.lang.String getINVGROUP() {
        return INVGROUP;
    }


    /**
     * Sets the INVGROUP value for this EBPP_INVOICE.
     * 
     * @param INVGROUP
     */
    public void setINVGROUP(java.lang.String INVGROUP) {
        this.INVGROUP = INVGROUP;
    }


    /**
     * Gets the EXTERNAL_INV_DESCR value for this EBPP_INVOICE.
     * 
     * @return EXTERNAL_INV_DESCR
     */
    public java.lang.String getEXTERNAL_INV_DESCR() {
        return EXTERNAL_INV_DESCR;
    }


    /**
     * Sets the EXTERNAL_INV_DESCR value for this EBPP_INVOICE.
     * 
     * @param EXTERNAL_INV_DESCR
     */
    public void setEXTERNAL_INV_DESCR(java.lang.String EXTERNAL_INV_DESCR) {
        this.EXTERNAL_INV_DESCR = EXTERNAL_INV_DESCR;
    }


    /**
     * Gets the IS_PARKED value for this EBPP_INVOICE.
     * 
     * @return IS_PARKED
     */
    public java.lang.String getIS_PARKED() {
        return IS_PARKED;
    }


    /**
     * Sets the IS_PARKED value for this EBPP_INVOICE.
     * 
     * @param IS_PARKED
     */
    public void setIS_PARKED(java.lang.String IS_PARKED) {
        this.IS_PARKED = IS_PARKED;
    }


    /**
     * Gets the PAYMENT_BLOCK value for this EBPP_INVOICE.
     * 
     * @return PAYMENT_BLOCK
     */
    public java.lang.String getPAYMENT_BLOCK() {
        return PAYMENT_BLOCK;
    }


    /**
     * Sets the PAYMENT_BLOCK value for this EBPP_INVOICE.
     * 
     * @param PAYMENT_BLOCK
     */
    public void setPAYMENT_BLOCK(java.lang.String PAYMENT_BLOCK) {
        this.PAYMENT_BLOCK = PAYMENT_BLOCK;
    }


    /**
     * Gets the XREF1 value for this EBPP_INVOICE.
     * 
     * @return XREF1
     */
    public java.lang.String getXREF1() {
        return XREF1;
    }


    /**
     * Sets the XREF1 value for this EBPP_INVOICE.
     * 
     * @param XREF1
     */
    public void setXREF1(java.lang.String XREF1) {
        this.XREF1 = XREF1;
    }


    /**
     * Gets the XREF2 value for this EBPP_INVOICE.
     * 
     * @return XREF2
     */
    public java.lang.String getXREF2() {
        return XREF2;
    }


    /**
     * Sets the XREF2 value for this EBPP_INVOICE.
     * 
     * @param XREF2
     */
    public void setXREF2(java.lang.String XREF2) {
        this.XREF2 = XREF2;
    }


    /**
     * Gets the XREF3 value for this EBPP_INVOICE.
     * 
     * @return XREF3
     */
    public java.lang.String getXREF3() {
        return XREF3;
    }


    /**
     * Sets the XREF3 value for this EBPP_INVOICE.
     * 
     * @param XREF3
     */
    public void setXREF3(java.lang.String XREF3) {
        this.XREF3 = XREF3;
    }


    /**
     * Gets the ZZZUONR value for this EBPP_INVOICE.
     * 
     * @return ZZZUONR
     */
    public java.lang.String getZZZUONR() {
        return ZZZUONR;
    }


    /**
     * Sets the ZZZUONR value for this EBPP_INVOICE.
     * 
     * @param ZZZUONR
     */
    public void setZZZUONR(java.lang.String ZZZUONR) {
        this.ZZZUONR = ZZZUONR;
    }


    /**
     * Gets the ZZREBZG value for this EBPP_INVOICE.
     * 
     * @return ZZREBZG
     */
    public java.lang.String getZZREBZG() {
        return ZZREBZG;
    }


    /**
     * Sets the ZZREBZG value for this EBPP_INVOICE.
     * 
     * @param ZZREBZG
     */
    public void setZZREBZG(java.lang.String ZZREBZG) {
        this.ZZREBZG = ZZREBZG;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EBPP_INVOICE)) return false;
        EBPP_INVOICE other = (EBPP_INVOICE) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.XMARK==null && other.getXMARK()==null) || 
             (this.XMARK!=null &&
              this.XMARK.equals(other.getXMARK()))) &&
            ((this.INVID==null && other.getINVID()==null) || 
             (this.INVID!=null &&
              this.INVID.equals(other.getINVID()))) &&
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
            ((this.REFID==null && other.getREFID()==null) || 
             (this.REFID!=null &&
              this.REFID.equals(other.getREFID()))) &&
            ((this.IS_DISPUTED==null && other.getIS_DISPUTED()==null) || 
             (this.IS_DISPUTED!=null &&
              this.IS_DISPUTED.equals(other.getIS_DISPUTED()))) &&
            ((this.IS_DISPUTEABLE==null && other.getIS_DISPUTEABLE()==null) || 
             (this.IS_DISPUTEABLE!=null &&
              this.IS_DISPUTEABLE.equals(other.getIS_DISPUTEABLE()))) &&
            ((this.LINCT==null && other.getLINCT()==null) || 
             (this.LINCT!=null &&
              this.LINCT.equals(other.getLINCT()))) &&
            ((this.INVCLASS==null && other.getINVCLASS()==null) || 
             (this.INVCLASS!=null &&
              this.INVCLASS.equals(other.getINVCLASS()))) &&
            ((this.DISPLAYSTATUS==null && other.getDISPLAYSTATUS()==null) || 
             (this.DISPLAYSTATUS!=null &&
              this.DISPLAYSTATUS.equals(other.getDISPLAYSTATUS()))) &&
            ((this.INV_DATE==null && other.getINV_DATE()==null) || 
             (this.INV_DATE!=null &&
              this.INV_DATE.equals(other.getINV_DATE()))) &&
            ((this.NON_PARTIALPAY==null && other.getNON_PARTIALPAY()==null) || 
             (this.NON_PARTIALPAY!=null &&
              this.NON_PARTIALPAY.equals(other.getNON_PARTIALPAY()))) &&
            ((this.NON_STOPABLE==null && other.getNON_STOPABLE()==null) || 
             (this.NON_STOPABLE!=null &&
              this.NON_STOPABLE.equals(other.getNON_STOPABLE()))) &&
            ((this.CURRENCY_ORI==null && other.getCURRENCY_ORI()==null) || 
             (this.CURRENCY_ORI!=null &&
              this.CURRENCY_ORI.equals(other.getCURRENCY_ORI()))) &&
            ((this.AMOUNT_ORI==null && other.getAMOUNT_ORI()==null) || 
             (this.AMOUNT_ORI!=null &&
              this.AMOUNT_ORI.equals(other.getAMOUNT_ORI()))) &&
            ((this.OPENAMOUNT_ORI==null && other.getOPENAMOUNT_ORI()==null) || 
             (this.OPENAMOUNT_ORI!=null &&
              this.OPENAMOUNT_ORI.equals(other.getOPENAMOUNT_ORI()))) &&
            ((this.DISCOUNT==null && other.getDISCOUNT()==null) || 
             (this.DISCOUNT!=null &&
              this.DISCOUNT.equals(other.getDISCOUNT()))) &&
            ((this.DISC_DATE==null && other.getDISC_DATE()==null) || 
             (this.DISC_DATE!=null &&
              this.DISC_DATE.equals(other.getDISC_DATE()))) &&
            ((this.DISC_PERC==null && other.getDISC_PERC()==null) || 
             (this.DISC_PERC!=null &&
              this.DISC_PERC.equals(other.getDISC_PERC()))) &&
            ((this.XPDF==null && other.getXPDF()==null) || 
             (this.XPDF!=null &&
              this.XPDF.equals(other.getXPDF()))) &&
            ((this.XXML==null && other.getXXML()==null) || 
             (this.XXML!=null &&
              this.XXML.equals(other.getXXML()))) &&
            ((this.XHTML==null && other.getXHTML()==null) || 
             (this.XHTML!=null &&
              this.XHTML.equals(other.getXHTML()))) &&
            ((this.XRAW==null && other.getXRAW()==null) || 
             (this.XRAW!=null &&
              this.XRAW.equals(other.getXRAW()))) &&
            ((this.XIBU==null && other.getXIBU()==null) || 
             (this.XIBU!=null &&
              this.XIBU.equals(other.getXIBU()))) &&
            ((this.XCUST1==null && other.getXCUST1()==null) || 
             (this.XCUST1!=null &&
              this.XCUST1.equals(other.getXCUST1()))) &&
            ((this.XCUST2==null && other.getXCUST2()==null) || 
             (this.XCUST2!=null &&
              this.XCUST2.equals(other.getXCUST2()))) &&
            ((this.XMULTARCH==null && other.getXMULTARCH()==null) || 
             (this.XMULTARCH!=null &&
              this.XMULTARCH.equals(other.getXMULTARCH()))) &&
            ((this.XTIF==null && other.getXTIF()==null) || 
             (this.XTIF!=null &&
              this.XTIF.equals(other.getXTIF()))) &&
            ((this.XOTHERTYPE==null && other.getXOTHERTYPE()==null) || 
             (this.XOTHERTYPE!=null &&
              this.XOTHERTYPE.equals(other.getXOTHERTYPE()))) &&
            ((this.INVOICE_PATH==null && other.getINVOICE_PATH()==null) || 
             (this.INVOICE_PATH!=null &&
              this.INVOICE_PATH.equals(other.getINVOICE_PATH()))) &&
            ((this.XURL==null && other.getXURL()==null) || 
             (this.XURL!=null &&
              this.XURL.equals(other.getXURL()))) &&
            ((this.x_NEW_BILL==null && other.getX_NEW_BILL()==null) || 
             (this.x_NEW_BILL!=null &&
              this.x_NEW_BILL.equals(other.getX_NEW_BILL()))) &&
            ((this.x_NEW_CONTACT==null && other.getX_NEW_CONTACT()==null) || 
             (this.x_NEW_CONTACT!=null &&
              this.x_NEW_CONTACT.equals(other.getX_NEW_CONTACT()))) &&
            ((this.BELNR==null && other.getBELNR()==null) || 
             (this.BELNR!=null &&
              this.BELNR.equals(other.getBELNR()))) &&
            ((this.GJAHR==null && other.getGJAHR()==null) || 
             (this.GJAHR!=null &&
              this.GJAHR.equals(other.getGJAHR()))) &&
            ((this.BUZEI==null && other.getBUZEI()==null) || 
             (this.BUZEI!=null &&
              this.BUZEI.equals(other.getBUZEI()))) &&
            ((this.BUDAT==null && other.getBUDAT()==null) || 
             (this.BUDAT!=null &&
              this.BUDAT.equals(other.getBUDAT()))) &&
            ((this.XBLNR==null && other.getXBLNR()==null) || 
             (this.XBLNR!=null &&
              this.XBLNR.equals(other.getXBLNR()))) &&
            ((this.VBELN==null && other.getVBELN()==null) || 
             (this.VBELN!=null &&
              this.VBELN.equals(other.getVBELN()))) &&
            ((this.BUKRS==null && other.getBUKRS()==null) || 
             (this.BUKRS!=null &&
              this.BUKRS.equals(other.getBUKRS()))) &&
            ((this.SHKZG==null && other.getSHKZG()==null) || 
             (this.SHKZG!=null &&
              this.SHKZG.equals(other.getSHKZG()))) &&
            ((this.BLDAT==null && other.getBLDAT()==null) || 
             (this.BLDAT!=null &&
              this.BLDAT.equals(other.getBLDAT()))) &&
            ((this.DOCT1==null && other.getDOCT1()==null) || 
             (this.DOCT1!=null &&
              this.DOCT1.equals(other.getDOCT1()))) &&
            ((this.PAYINFO==null && other.getPAYINFO()==null) || 
             (this.PAYINFO!=null &&
              this.PAYINFO.equals(other.getPAYINFO()))) &&
            ((this.INVGROUP==null && other.getINVGROUP()==null) || 
             (this.INVGROUP!=null &&
              this.INVGROUP.equals(other.getINVGROUP()))) &&
            ((this.EXTERNAL_INV_DESCR==null && other.getEXTERNAL_INV_DESCR()==null) || 
             (this.EXTERNAL_INV_DESCR!=null &&
              this.EXTERNAL_INV_DESCR.equals(other.getEXTERNAL_INV_DESCR()))) &&
            ((this.IS_PARKED==null && other.getIS_PARKED()==null) || 
             (this.IS_PARKED!=null &&
              this.IS_PARKED.equals(other.getIS_PARKED()))) &&
            ((this.PAYMENT_BLOCK==null && other.getPAYMENT_BLOCK()==null) || 
             (this.PAYMENT_BLOCK!=null &&
              this.PAYMENT_BLOCK.equals(other.getPAYMENT_BLOCK()))) &&
            ((this.XREF1==null && other.getXREF1()==null) || 
             (this.XREF1!=null &&
              this.XREF1.equals(other.getXREF1()))) &&
            ((this.XREF2==null && other.getXREF2()==null) || 
             (this.XREF2!=null &&
              this.XREF2.equals(other.getXREF2()))) &&
            ((this.XREF3==null && other.getXREF3()==null) || 
             (this.XREF3!=null &&
              this.XREF3.equals(other.getXREF3()))) &&
            ((this.ZZZUONR==null && other.getZZZUONR()==null) || 
             (this.ZZZUONR!=null &&
              this.ZZZUONR.equals(other.getZZZUONR()))) &&
            ((this.ZZREBZG==null && other.getZZREBZG()==null) || 
             (this.ZZREBZG!=null &&
              this.ZZREBZG.equals(other.getZZREBZG())));
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
        if (getXMARK() != null) {
            _hashCode += getXMARK().hashCode();
        }
        if (getINVID() != null) {
            _hashCode += getINVID().hashCode();
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
        if (getREFID() != null) {
            _hashCode += getREFID().hashCode();
        }
        if (getIS_DISPUTED() != null) {
            _hashCode += getIS_DISPUTED().hashCode();
        }
        if (getIS_DISPUTEABLE() != null) {
            _hashCode += getIS_DISPUTEABLE().hashCode();
        }
        if (getLINCT() != null) {
            _hashCode += getLINCT().hashCode();
        }
        if (getINVCLASS() != null) {
            _hashCode += getINVCLASS().hashCode();
        }
        if (getDISPLAYSTATUS() != null) {
            _hashCode += getDISPLAYSTATUS().hashCode();
        }
        if (getINV_DATE() != null) {
            _hashCode += getINV_DATE().hashCode();
        }
        if (getNON_PARTIALPAY() != null) {
            _hashCode += getNON_PARTIALPAY().hashCode();
        }
        if (getNON_STOPABLE() != null) {
            _hashCode += getNON_STOPABLE().hashCode();
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
        if (getDISCOUNT() != null) {
            _hashCode += getDISCOUNT().hashCode();
        }
        if (getDISC_DATE() != null) {
            _hashCode += getDISC_DATE().hashCode();
        }
        if (getDISC_PERC() != null) {
            _hashCode += getDISC_PERC().hashCode();
        }
        if (getXPDF() != null) {
            _hashCode += getXPDF().hashCode();
        }
        if (getXXML() != null) {
            _hashCode += getXXML().hashCode();
        }
        if (getXHTML() != null) {
            _hashCode += getXHTML().hashCode();
        }
        if (getXRAW() != null) {
            _hashCode += getXRAW().hashCode();
        }
        if (getXIBU() != null) {
            _hashCode += getXIBU().hashCode();
        }
        if (getXCUST1() != null) {
            _hashCode += getXCUST1().hashCode();
        }
        if (getXCUST2() != null) {
            _hashCode += getXCUST2().hashCode();
        }
        if (getXMULTARCH() != null) {
            _hashCode += getXMULTARCH().hashCode();
        }
        if (getXTIF() != null) {
            _hashCode += getXTIF().hashCode();
        }
        if (getXOTHERTYPE() != null) {
            _hashCode += getXOTHERTYPE().hashCode();
        }
        if (getINVOICE_PATH() != null) {
            _hashCode += getINVOICE_PATH().hashCode();
        }
        if (getXURL() != null) {
            _hashCode += getXURL().hashCode();
        }
        if (getX_NEW_BILL() != null) {
            _hashCode += getX_NEW_BILL().hashCode();
        }
        if (getX_NEW_CONTACT() != null) {
            _hashCode += getX_NEW_CONTACT().hashCode();
        }
        if (getBELNR() != null) {
            _hashCode += getBELNR().hashCode();
        }
        if (getGJAHR() != null) {
            _hashCode += getGJAHR().hashCode();
        }
        if (getBUZEI() != null) {
            _hashCode += getBUZEI().hashCode();
        }
        if (getBUDAT() != null) {
            _hashCode += getBUDAT().hashCode();
        }
        if (getXBLNR() != null) {
            _hashCode += getXBLNR().hashCode();
        }
        if (getVBELN() != null) {
            _hashCode += getVBELN().hashCode();
        }
        if (getBUKRS() != null) {
            _hashCode += getBUKRS().hashCode();
        }
        if (getSHKZG() != null) {
            _hashCode += getSHKZG().hashCode();
        }
        if (getBLDAT() != null) {
            _hashCode += getBLDAT().hashCode();
        }
        if (getDOCT1() != null) {
            _hashCode += getDOCT1().hashCode();
        }
        if (getPAYINFO() != null) {
            _hashCode += getPAYINFO().hashCode();
        }
        if (getINVGROUP() != null) {
            _hashCode += getINVGROUP().hashCode();
        }
        if (getEXTERNAL_INV_DESCR() != null) {
            _hashCode += getEXTERNAL_INV_DESCR().hashCode();
        }
        if (getIS_PARKED() != null) {
            _hashCode += getIS_PARKED().hashCode();
        }
        if (getPAYMENT_BLOCK() != null) {
            _hashCode += getPAYMENT_BLOCK().hashCode();
        }
        if (getXREF1() != null) {
            _hashCode += getXREF1().hashCode();
        }
        if (getXREF2() != null) {
            _hashCode += getXREF2().hashCode();
        }
        if (getXREF3() != null) {
            _hashCode += getXREF3().hashCode();
        }
        if (getZZZUONR() != null) {
            _hashCode += getZZZUONR().hashCode();
        }
        if (getZZREBZG() != null) {
            _hashCode += getZZREBZG().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EBPP_INVOICE.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:sap-com:document:sap:rfc:functions", "EBPP_INVOICE"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("XMARK");
        elemField.setXmlName(new javax.xml.namespace.QName("", "XMARK"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("INVID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "INVID"));
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
        elemField.setFieldName("REFID");
        elemField.setXmlName(new javax.xml.namespace.QName("", "REFID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("IS_DISPUTED");
        elemField.setXmlName(new javax.xml.namespace.QName("", "IS_DISPUTED"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("IS_DISPUTEABLE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "IS_DISPUTEABLE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("LINCT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "LINCT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("INVCLASS");
        elemField.setXmlName(new javax.xml.namespace.QName("", "INVCLASS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DISPLAYSTATUS");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DISPLAYSTATUS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("INV_DATE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "INV_DATE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NON_PARTIALPAY");
        elemField.setXmlName(new javax.xml.namespace.QName("", "NON_PARTIALPAY"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NON_STOPABLE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "NON_STOPABLE"));
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
        elemField.setFieldName("DISCOUNT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DISCOUNT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DISC_DATE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DISC_DATE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DISC_PERC");
        elemField.setXmlName(new javax.xml.namespace.QName("", "DISC_PERC"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "decimal"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("XPDF");
        elemField.setXmlName(new javax.xml.namespace.QName("", "XPDF"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("XXML");
        elemField.setXmlName(new javax.xml.namespace.QName("", "XXML"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("XHTML");
        elemField.setXmlName(new javax.xml.namespace.QName("", "XHTML"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("XRAW");
        elemField.setXmlName(new javax.xml.namespace.QName("", "XRAW"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("XIBU");
        elemField.setXmlName(new javax.xml.namespace.QName("", "XIBU"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("XCUST1");
        elemField.setXmlName(new javax.xml.namespace.QName("", "XCUST1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("XCUST2");
        elemField.setXmlName(new javax.xml.namespace.QName("", "XCUST2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("XMULTARCH");
        elemField.setXmlName(new javax.xml.namespace.QName("", "XMULTARCH"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("XTIF");
        elemField.setXmlName(new javax.xml.namespace.QName("", "XTIF"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("XOTHERTYPE");
        elemField.setXmlName(new javax.xml.namespace.QName("", "XOTHERTYPE"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("INVOICE_PATH");
        elemField.setXmlName(new javax.xml.namespace.QName("", "INVOICE_PATH"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("XURL");
        elemField.setXmlName(new javax.xml.namespace.QName("", "XURL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("x_NEW_BILL");
        elemField.setXmlName(new javax.xml.namespace.QName("", "X_NEW_BILL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("x_NEW_CONTACT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "X_NEW_CONTACT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("BELNR");
        elemField.setXmlName(new javax.xml.namespace.QName("", "BELNR"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("GJAHR");
        elemField.setXmlName(new javax.xml.namespace.QName("", "GJAHR"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("BUZEI");
        elemField.setXmlName(new javax.xml.namespace.QName("", "BUZEI"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("BUDAT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "BUDAT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("XBLNR");
        elemField.setXmlName(new javax.xml.namespace.QName("", "XBLNR"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("VBELN");
        elemField.setXmlName(new javax.xml.namespace.QName("", "VBELN"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("BUKRS");
        elemField.setXmlName(new javax.xml.namespace.QName("", "BUKRS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("SHKZG");
        elemField.setXmlName(new javax.xml.namespace.QName("", "SHKZG"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("BLDAT");
        elemField.setXmlName(new javax.xml.namespace.QName("", "BLDAT"));
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
        elemField.setFieldName("PAYINFO");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PAYINFO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("INVGROUP");
        elemField.setXmlName(new javax.xml.namespace.QName("", "INVGROUP"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("EXTERNAL_INV_DESCR");
        elemField.setXmlName(new javax.xml.namespace.QName("", "EXTERNAL_INV_DESCR"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("IS_PARKED");
        elemField.setXmlName(new javax.xml.namespace.QName("", "IS_PARKED"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("PAYMENT_BLOCK");
        elemField.setXmlName(new javax.xml.namespace.QName("", "PAYMENT_BLOCK"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("XREF1");
        elemField.setXmlName(new javax.xml.namespace.QName("", "XREF1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("XREF2");
        elemField.setXmlName(new javax.xml.namespace.QName("", "XREF2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("XREF3");
        elemField.setXmlName(new javax.xml.namespace.QName("", "XREF3"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ZZZUONR");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ZZZUONR"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ZZREBZG");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ZZREBZG"));
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
