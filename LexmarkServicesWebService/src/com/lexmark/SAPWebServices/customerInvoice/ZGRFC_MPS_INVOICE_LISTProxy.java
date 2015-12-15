package com.lexmark.SAPWebServices.customerInvoice;

public class ZGRFC_MPS_INVOICE_LISTProxy implements com.lexmark.SAPWebServices.customerInvoice.ZGRFC_MPS_INVOICE_LIST_PortType {
  private String _endpoint = null;
  private com.lexmark.SAPWebServices.customerInvoice.ZGRFC_MPS_INVOICE_LIST_PortType zGRFC_MPS_INVOICE_LIST_PortType = null;
  
  public ZGRFC_MPS_INVOICE_LISTProxy() {
    _initZGRFC_MPS_INVOICE_LISTProxy();
  }
  
  public ZGRFC_MPS_INVOICE_LISTProxy(String endpoint) {
    _endpoint = endpoint;
    _initZGRFC_MPS_INVOICE_LISTProxy();
  }
  
  private void _initZGRFC_MPS_INVOICE_LISTProxy() {
    try {
      zGRFC_MPS_INVOICE_LIST_PortType = (new com.lexmark.SAPWebServices.customerInvoice.ZGRFC_MPS_INVOICE_LIST_ServiceLocator()).getZGRFC_MPS_INVOICE_LIST();
      if (zGRFC_MPS_INVOICE_LIST_PortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)zGRFC_MPS_INVOICE_LIST_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)zGRFC_MPS_INVOICE_LIST_PortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (zGRFC_MPS_INVOICE_LIST_PortType != null)
      ((javax.xml.rpc.Stub)zGRFC_MPS_INVOICE_LIST_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.lexmark.SAPWebServices.customerInvoice.ZGRFC_MPS_INVOICE_LIST_PortType getZGRFC_MPS_INVOICE_LIST_PortType() {
    if (zGRFC_MPS_INVOICE_LIST_PortType == null)
      _initZGRFC_MPS_INVOICE_LISTProxy();
    return zGRFC_MPS_INVOICE_LIST_PortType;
  }
  
  public void ZGRFC_MPS_INVOICE_LIST(java.lang.String IS_BUKRS, java.lang.String IS_FROM_DATE, java.lang.String IS_INDICATOR, java.lang.String IS_INVOICE_NO, java.lang.String IS_KUNNR, java.lang.String IS_TO_DATE, com.lexmark.SAPWebServices.customerInvoice.holders.TABLE_OF_ZGSF_MPS_INVOICE_LISTHolder IT_FINAL) throws java.rmi.RemoteException{
    if (zGRFC_MPS_INVOICE_LIST_PortType == null)
      _initZGRFC_MPS_INVOICE_LISTProxy();
    zGRFC_MPS_INVOICE_LIST_PortType.ZGRFC_MPS_INVOICE_LIST(IS_BUKRS, IS_FROM_DATE, IS_INDICATOR, IS_INVOICE_NO, IS_KUNNR, IS_TO_DATE, IT_FINAL);
  }
  
  
}