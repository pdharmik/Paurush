package com.lexmark.SAPWebServices.AR.serviceRequests.client;

public class ZG_GET_AR_DETAILSProxy implements com.lexmark.SAPWebServices.AR.serviceRequests.client.ZG_GET_AR_DETAILS_PortType {
  private String _endpoint = null;
  private com.lexmark.SAPWebServices.AR.serviceRequests.client.ZG_GET_AR_DETAILS_PortType zG_GET_AR_DETAILS_PortType = null;
  
  public ZG_GET_AR_DETAILSProxy() {
    _initZG_GET_AR_DETAILSProxy();
  }
  
  public ZG_GET_AR_DETAILSProxy(String endpoint) {
    _endpoint = endpoint;
    _initZG_GET_AR_DETAILSProxy();
  }
  
  private void _initZG_GET_AR_DETAILSProxy() {
    try {
      zG_GET_AR_DETAILS_PortType = (new com.lexmark.SAPWebServices.AR.serviceRequests.client.ZG_GET_AR_DETAILS_ServiceLocator()).getZG_GET_AR_DETAILS_BN();
      if (zG_GET_AR_DETAILS_PortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)zG_GET_AR_DETAILS_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)zG_GET_AR_DETAILS_PortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (zG_GET_AR_DETAILS_PortType != null)
      ((javax.xml.rpc.Stub)zG_GET_AR_DETAILS_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.lexmark.SAPWebServices.AR.serviceRequests.client.ZG_GET_AR_DETAILS_PortType getZG_GET_AR_DETAILS_PortType() {
    if (zG_GET_AR_DETAILS_PortType == null)
      _initZG_GET_AR_DETAILSProxy();
    return zG_GET_AR_DETAILS_PortType;
  }
  
  public void ZG_AR_GET_DETAILS(java.lang.String i_COMPANY_CODE, java.lang.String i_CUSTOMER, java.lang.String i_INVOICE, com.lexmark.SAPWebServices.AR.serviceRequests.client.holders.TABLE_OF_BAPIRET2Holder RETURN, com.lexmark.SAPWebServices.AR.serviceRequests.client.holders.TABLE_OF_ZGGET_AP_DATAHolder t_AR_DATA) throws java.rmi.RemoteException{
    if (zG_GET_AR_DETAILS_PortType == null)
      _initZG_GET_AR_DETAILSProxy();
    zG_GET_AR_DETAILS_PortType.ZG_AR_GET_DETAILS(i_COMPANY_CODE, i_CUSTOMER, i_INVOICE, RETURN, t_AR_DATA);
  }
  
  
}