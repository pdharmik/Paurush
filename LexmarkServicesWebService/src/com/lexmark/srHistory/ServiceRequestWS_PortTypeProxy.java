package com.lexmark.srHistory;

public class ServiceRequestWS_PortTypeProxy implements com.lexmark.srHistory.ServiceRequestWS_PortType {
  private String _endpoint = null;
  private com.lexmark.srHistory.ServiceRequestWS_PortType serviceRequestWS_PortType = null;
  
  public ServiceRequestWS_PortTypeProxy() {
    _initServiceRequestWS_PortTypeProxy();
  }
  
  public ServiceRequestWS_PortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initServiceRequestWS_PortTypeProxy();
  }
  
  private void _initServiceRequestWS_PortTypeProxy() {
    try {
      serviceRequestWS_PortType = (new com.lexmark.srHistory.LXKServiceRequestWebservicesServiceRequestWSLocator()).getLXKServiceRequest_webservices_serviceRequestWS_Port();
      if (serviceRequestWS_PortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)serviceRequestWS_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)serviceRequestWS_PortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (serviceRequestWS_PortType != null)
      ((javax.xml.rpc.Stub)serviceRequestWS_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.lexmark.srHistory.ServiceRequestWS_PortType getServiceRequestWS_PortType() {
    if (serviceRequestWS_PortType == null)
      _initServiceRequestWS_PortTypeProxy();
    return serviceRequestWS_PortType;
  }
  
  public com.lexmark.srHistory.AssetServiceRequestHistory[] getServiceRequestHistory(java.lang.String debug, com.lexmark.srHistory.ServiceRequestHistoryWSInput serviceRequestHistoryWSInput) throws java.rmi.RemoteException{
    if (serviceRequestWS_PortType == null)
      _initServiceRequestWS_PortTypeProxy();
    return serviceRequestWS_PortType.getServiceRequestHistory(debug, serviceRequestHistoryWSInput);
  }
  
  public com.lexmark.srHistory.ServiceRequestHistory getServiceRequestDetails(java.lang.String debug, com.lexmark.srHistory.ServiceRequestDetailsWSInput serviceRequestDetailsWSInput) throws java.rmi.RemoteException{
    if (serviceRequestWS_PortType == null)
      _initServiceRequestWS_PortTypeProxy();
    return serviceRequestWS_PortType.getServiceRequestDetails(debug, serviceRequestDetailsWSInput);
  }
  
  
}