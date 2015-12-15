package com.lexmark.SAPWebServices.AP.serviceRequest.client;

public class ZG_GET_AP_DETAILSProxy implements com.lexmark.SAPWebServices.AP.serviceRequest.client.ZG_GET_AP_DETAILS_PortType {
  private String _endpoint = null;
  private com.lexmark.SAPWebServices.AP.serviceRequest.client.ZG_GET_AP_DETAILS_PortType zG_GET_AP_DETAILS_PortType = null;
  
  public ZG_GET_AP_DETAILSProxy() {
    _initZG_GET_AP_DETAILSProxy();
  }
  
  public ZG_GET_AP_DETAILSProxy(String endpoint) {
    _endpoint = endpoint;
    _initZG_GET_AP_DETAILSProxy();
  }
  
  private void _initZG_GET_AP_DETAILSProxy() {
    try {
      zG_GET_AP_DETAILS_PortType = (new com.lexmark.SAPWebServices.AP.serviceRequest.client.ZG_GET_AP_DETAILS_ServiceLocator()).getZG_GET_AP_DETAILS_BN();
      if (zG_GET_AP_DETAILS_PortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)zG_GET_AP_DETAILS_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)zG_GET_AP_DETAILS_PortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (zG_GET_AP_DETAILS_PortType != null)
      ((javax.xml.rpc.Stub)zG_GET_AP_DETAILS_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.lexmark.SAPWebServices.AP.serviceRequest.client.ZG_GET_AP_DETAILS_PortType getZG_GET_AP_DETAILS_PortType() {
    if (zG_GET_AP_DETAILS_PortType == null)
      _initZG_GET_AP_DETAILSProxy();
    return zG_GET_AP_DETAILS_PortType;
  }
  
  public void ZG_AP_GET_DETAILS(java.lang.String i_COMPANY_CODE, java.lang.String i_INVOICE, java.lang.String i_VENDOR, com.lexmark.SAPWebServices.AP.serviceRequest.client.holders.TABLE_OF_BAPIRET2Holder RETURN, com.lexmark.SAPWebServices.AP.serviceRequest.client.holders.TABLE_OF_ZGGET_AP_DATAHolder t_AP_DATA) throws java.rmi.RemoteException{
    if (zG_GET_AP_DETAILS_PortType == null)
      _initZG_GET_AP_DETAILSProxy();
    zG_GET_AP_DETAILS_PortType.ZG_AP_GET_DETAILS(i_COMPANY_CODE, i_INVOICE, i_VENDOR, RETURN, t_AP_DATA);
  }
  
  
}