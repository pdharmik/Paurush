package com.businessobjects.www;

public class Realtime_Service_AdminProxy implements com.businessobjects.www.Realtime_Service_Admin {
  private String _endpoint = null;
  private com.businessobjects.www.Realtime_Service_Admin realtime_Service_Admin = null;
  
  public Realtime_Service_AdminProxy() {
    _initRealtime_Service_AdminProxy();
  }
  
  public Realtime_Service_AdminProxy(String endpoint) {
    _endpoint = endpoint;
    _initRealtime_Service_AdminProxy();
  }
  
  private void _initRealtime_Service_AdminProxy() {
    try {
      realtime_Service_Admin = (new com.businessobjects.www.DataServices_ServerLocator()).getRealtime_Service_Admin();
      if (realtime_Service_Admin != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)realtime_Service_Admin)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)realtime_Service_Admin)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (realtime_Service_Admin != null)
      ((javax.xml.rpc.Stub)realtime_Service_Admin)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.businessobjects.www.Realtime_Service_Admin getRealtime_Service_Admin() {
    if (realtime_Service_Admin == null)
      _initRealtime_Service_AdminProxy();
    return realtime_Service_Admin;
  }
  
  public com.businessobjects.www.DataServices.ServerX_xsd.ListOfRealtimeServicesResponse get_RTService_List(com.businessobjects.www.DataServices.ServerX_xsd.GetListOfRTServicesRequest getListOfRTServicesRequest) throws java.rmi.RemoteException{
    if (realtime_Service_Admin == null)
      _initRealtime_Service_AdminProxy();
    return realtime_Service_Admin.get_RTService_List(getListOfRTServicesRequest);
  }
  
  public com.businessobjects.www.DataServices.ServerX_xsd.RunRealtimeServiceResponse run_Realtime_Service(com.businessobjects.www.DataServices.ServerX_xsd.RunRealtimeServiceRequest runRealtimeServiceRequest) throws java.rmi.RemoteException{
    if (realtime_Service_Admin == null)
      _initRealtime_Service_AdminProxy();
    return realtime_Service_Admin.run_Realtime_Service(runRealtimeServiceRequest);
  }
  
  public com.businessobjects.www.DataServices.ServerX_xsd.GetMsgFormatResponse get_RTMsg_Format(com.businessobjects.www.DataServices.ServerX_xsd.GetMsgFormatRequest getMsgFormatRequest) throws java.rmi.RemoteException{
    if (realtime_Service_Admin == null)
      _initRealtime_Service_AdminProxy();
    return realtime_Service_Admin.get_RTMsg_Format(getMsgFormatRequest);
  }
  
  
}