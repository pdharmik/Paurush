/**
 * ZG_GET_AP_DETAILS_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SAPWebServices.AP.serviceRequest.client;

public interface ZG_GET_AP_DETAILS_PortType extends java.rmi.Remote {
    public void ZG_AP_GET_DETAILS(java.lang.String i_COMPANY_CODE, java.lang.String i_INVOICE, java.lang.String i_VENDOR, com.lexmark.SAPWebServices.AP.serviceRequest.client.holders.TABLE_OF_BAPIRET2Holder RETURN, com.lexmark.SAPWebServices.AP.serviceRequest.client.holders.TABLE_OF_ZGGET_AP_DATAHolder t_AP_DATA) throws java.rmi.RemoteException;
}
