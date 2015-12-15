/**
 * ZG_GET_AR_DETAILS_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SAPWebServices.AR.serviceRequests.client;

public interface ZG_GET_AR_DETAILS_PortType extends java.rmi.Remote {
    public void ZG_AR_GET_DETAILS(java.lang.String i_COMPANY_CODE, java.lang.String i_CUSTOMER, java.lang.String i_INVOICE, com.lexmark.SAPWebServices.AR.serviceRequests.client.holders.TABLE_OF_BAPIRET2Holder RETURN, com.lexmark.SAPWebServices.AR.serviceRequests.client.holders.TABLE_OF_ZGGET_AP_DATAHolder t_AR_DATA) throws java.rmi.RemoteException;
}
