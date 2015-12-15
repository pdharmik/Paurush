package com.lexmark.webServices.processCustomerContactCM;

public class ProcessCustomerContactWS_PortTypeProxy implements com.lexmark.webServices.processCustomerContactCM.ProcessCustomerContactWS_PortType {
  private String _endpoint = null;
  private com.lexmark.webServices.processCustomerContactCM.ProcessCustomerContactWS_PortType processCustomerContactWS_PortType = null;
  
  public ProcessCustomerContactWS_PortTypeProxy() {
    _initProcessCustomerContactWS_PortTypeProxy();
  }
  
  public ProcessCustomerContactWS_PortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initProcessCustomerContactWS_PortTypeProxy();
  }
  
  private void _initProcessCustomerContactWS_PortTypeProxy() {
    try {
      processCustomerContactWS_PortType = (new com.lexmark.webServices.processCustomerContactCM.ProcessCustomerContactWSLocator()).getLXKCustomerContact_webServices_provider_processCustomerContactWS_Port();
      if (processCustomerContactWS_PortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)processCustomerContactWS_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)processCustomerContactWS_PortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (processCustomerContactWS_PortType != null)
      ((javax.xml.rpc.Stub)processCustomerContactWS_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.lexmark.webServices.processCustomerContactCM.ProcessCustomerContactWS_PortType getProcessCustomerContactWS_PortType() {
    if (processCustomerContactWS_PortType == null)
      _initProcessCustomerContactWS_PortTypeProxy();
    return processCustomerContactWS_PortType;
  }
  
  public com.lexmark.webServices.processCustomerContactCM.PublishContactsResponse processCustomerContact(com.lexmark.webServices.processCustomerContactCM.PublishContacts parameters) throws java.rmi.RemoteException{
    if (processCustomerContactWS_PortType == null)
      _initProcessCustomerContactWS_PortTypeProxy();
    return processCustomerContactWS_PortType.processCustomerContact(parameters);
  }
  
  
}