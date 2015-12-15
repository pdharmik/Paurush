/**
 * ZG_AP_GET_DATA_PortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.lexmark.SAPWebServices.AP.invoice.client;

public interface ZG_AP_GET_DATA_PortType extends java.rmi.Remote {
    public void APAR_EBPP_GET_DATA(com.lexmark.SAPWebServices.AP.invoice.client.EBPP_ADDSEL i_ADDSEL, com.lexmark.SAPWebServices.AP.invoice.client.EBPP_CONTROL i_CONTROLDATA, com.lexmark.SAPWebServices.AP.invoice.client.FDM_EBPP_DMSELECTION i_DMSELECTION, com.lexmark.SAPWebServices.AP.invoice.client.EBPP_PARTNER i_PARTNER, com.lexmark.SAPWebServices.AP.invoice.client.holders.TABLE_OF_EBPP_ALLOCATIONHolder t_ALLOCATION, com.lexmark.SAPWebServices.AP.invoice.client.holders.TABLE_OF_FIN_BANKIDHolder t_BANKS, com.lexmark.SAPWebServices.AP.invoice.client.holders.TABLE_OF_APAR_EBPP_CARDHolder t_CARDS, com.lexmark.SAPWebServices.AP.invoice.client.holders.TABLE_OF_APAR_EBPP_CARDTYPESHolder t_CARDTYPES, com.lexmark.SAPWebServices.AP.invoice.client.holders.TABLE_OF_FDM_EBPP_DMDISPUTESHolder t_DISPUTES, com.lexmark.SAPWebServices.AP.invoice.client.holders.TABLE_OF_EBPP_DM_ALLOCATIONHolder t_DISPUTES_ALLOCATION, com.lexmark.SAPWebServices.AP.invoice.client.holders.TABLE_OF_EBPP_INIT_ADD_DATAHolder t_INIT_DATA, com.lexmark.SAPWebServices.AP.invoice.client.holders.TABLE_OF_EBPP_INVOICEHolder t_INVOICES, com.lexmark.SAPWebServices.AP.invoice.client.holders.TABLE_OF_EBPP_ITEMHolder t_ITEMS, com.lexmark.SAPWebServices.AP.invoice.client.holders.TABLE_OF_EBPP_MESSAGESHolder t_MESSAGES, com.lexmark.SAPWebServices.AP.invoice.client.holders.TABLE_OF_EBPP_MYPAYMENTSHolder t_MYPAYMENTS, com.lexmark.SAPWebServices.AP.invoice.client.holders.TABLE_OF_EBPP_PAYALLOCATIONHolder t_PAYALLOCATION, com.lexmark.SAPWebServices.AP.invoice.client.holders.TABLE_OF_EBPP_PAYEXPLANATIONHolder t_PAYEXPLANATION, com.lexmark.SAPWebServices.AP.invoice.client.holders.TABLE_OF_EBPP_TOTALSHolder t_TOTALS, com.lexmark.SAPWebServices.AP.invoice.client.holders.EBPP_CREDITSHolder e_CREDITS, com.lexmark.SAPWebServices.AP.invoice.client.holders.EBPP_PARTNERHolder e_PARTNER, javax.xml.rpc.holders.IntHolder e_RETURNCODE, com.lexmark.SAPWebServices.AP.invoice.client.holders.BAPIRET1Holder RETURN) throws java.rmi.RemoteException;
}
