package com.lexmark.SAPWebServices.AP.invoice.client;

public class ZG_AP_GET_DATAProxy implements com.lexmark.SAPWebServices.AP.invoice.client.ZG_AP_GET_DATA_PortType {
  private String _endpoint = null;
  private com.lexmark.SAPWebServices.AP.invoice.client.ZG_AP_GET_DATA_PortType zG_AP_GET_DATA_PortType = null;
  
  public ZG_AP_GET_DATAProxy() {
    _initZG_AP_GET_DATAProxy();
  }
  
  public ZG_AP_GET_DATAProxy(String endpoint) {
    _endpoint = endpoint;
    _initZG_AP_GET_DATAProxy();
  }
  
  private void _initZG_AP_GET_DATAProxy() {
    try {
      zG_AP_GET_DATA_PortType = (new com.lexmark.SAPWebServices.AP.invoice.client.ZG_AP_GET_DATA_ServiceLocator()).getZG_AP_GET_DATA();
      if (zG_AP_GET_DATA_PortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)zG_AP_GET_DATA_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)zG_AP_GET_DATA_PortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (zG_AP_GET_DATA_PortType != null)
      ((javax.xml.rpc.Stub)zG_AP_GET_DATA_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.lexmark.SAPWebServices.AP.invoice.client.ZG_AP_GET_DATA_PortType getZG_AP_GET_DATA_PortType() {
    if (zG_AP_GET_DATA_PortType == null)
      _initZG_AP_GET_DATAProxy();
    return zG_AP_GET_DATA_PortType;
  }
  
  public void APAR_EBPP_GET_DATA(com.lexmark.SAPWebServices.AP.invoice.client.EBPP_ADDSEL i_ADDSEL, com.lexmark.SAPWebServices.AP.invoice.client.EBPP_CONTROL i_CONTROLDATA, com.lexmark.SAPWebServices.AP.invoice.client.FDM_EBPP_DMSELECTION i_DMSELECTION, com.lexmark.SAPWebServices.AP.invoice.client.EBPP_PARTNER i_PARTNER, com.lexmark.SAPWebServices.AP.invoice.client.holders.TABLE_OF_EBPP_ALLOCATIONHolder t_ALLOCATION, com.lexmark.SAPWebServices.AP.invoice.client.holders.TABLE_OF_FIN_BANKIDHolder t_BANKS, com.lexmark.SAPWebServices.AP.invoice.client.holders.TABLE_OF_APAR_EBPP_CARDHolder t_CARDS, com.lexmark.SAPWebServices.AP.invoice.client.holders.TABLE_OF_APAR_EBPP_CARDTYPESHolder t_CARDTYPES, com.lexmark.SAPWebServices.AP.invoice.client.holders.TABLE_OF_FDM_EBPP_DMDISPUTESHolder t_DISPUTES, com.lexmark.SAPWebServices.AP.invoice.client.holders.TABLE_OF_EBPP_DM_ALLOCATIONHolder t_DISPUTES_ALLOCATION, com.lexmark.SAPWebServices.AP.invoice.client.holders.TABLE_OF_EBPP_INIT_ADD_DATAHolder t_INIT_DATA, com.lexmark.SAPWebServices.AP.invoice.client.holders.TABLE_OF_EBPP_INVOICEHolder t_INVOICES, com.lexmark.SAPWebServices.AP.invoice.client.holders.TABLE_OF_EBPP_ITEMHolder t_ITEMS, com.lexmark.SAPWebServices.AP.invoice.client.holders.TABLE_OF_EBPP_MESSAGESHolder t_MESSAGES, com.lexmark.SAPWebServices.AP.invoice.client.holders.TABLE_OF_EBPP_MYPAYMENTSHolder t_MYPAYMENTS, com.lexmark.SAPWebServices.AP.invoice.client.holders.TABLE_OF_EBPP_PAYALLOCATIONHolder t_PAYALLOCATION, com.lexmark.SAPWebServices.AP.invoice.client.holders.TABLE_OF_EBPP_PAYEXPLANATIONHolder t_PAYEXPLANATION, com.lexmark.SAPWebServices.AP.invoice.client.holders.TABLE_OF_EBPP_TOTALSHolder t_TOTALS, com.lexmark.SAPWebServices.AP.invoice.client.holders.EBPP_CREDITSHolder e_CREDITS, com.lexmark.SAPWebServices.AP.invoice.client.holders.EBPP_PARTNERHolder e_PARTNER, javax.xml.rpc.holders.IntHolder e_RETURNCODE, com.lexmark.SAPWebServices.AP.invoice.client.holders.BAPIRET1Holder RETURN) throws java.rmi.RemoteException{
    if (zG_AP_GET_DATA_PortType == null)
      _initZG_AP_GET_DATAProxy();
    zG_AP_GET_DATA_PortType.APAR_EBPP_GET_DATA(i_ADDSEL, i_CONTROLDATA, i_DMSELECTION, i_PARTNER, t_ALLOCATION, t_BANKS, t_CARDS, t_CARDTYPES, t_DISPUTES, t_DISPUTES_ALLOCATION, t_INIT_DATA, t_INVOICES, t_ITEMS, t_MESSAGES, t_MYPAYMENTS, t_PAYALLOCATION, t_PAYEXPLANATION, t_TOTALS, e_CREDITS, e_PARTNER, e_RETURNCODE, RETURN);
  }
  
  
}