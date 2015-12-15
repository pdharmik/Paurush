/**
 * ZGRFC_MPS_INVOICE_LIST_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SAPWebServices.customerInvoice;

public interface ZGRFC_MPS_INVOICE_LIST_PortType extends java.rmi.Remote {
    public void ZGRFC_MPS_INVOICE_LIST(java.lang.String IS_BUKRS, java.lang.String IS_FROM_DATE, java.lang.String IS_INDICATOR, java.lang.String IS_INVOICE_NO, java.lang.String IS_KUNNR, java.lang.String IS_TO_DATE, com.lexmark.SAPWebServices.customerInvoice.holders.TABLE_OF_ZGSF_MPS_INVOICE_LISTHolder IT_FINAL) throws java.rmi.RemoteException;
}
