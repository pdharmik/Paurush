package com.siebel.analytics.web.soap.v5;

public class IBotServiceSoapProxy implements com.siebel.analytics.web.soap.v5.IBotServiceSoap {
  private String _endpoint = null;
  private com.siebel.analytics.web.soap.v5.IBotServiceSoap iBotServiceSoap = null;
  
  public IBotServiceSoapProxy() {
    _initIBotServiceSoapProxy();
  }
  
  public IBotServiceSoapProxy(String endpoint) {
    _endpoint = endpoint;
    _initIBotServiceSoapProxy();
  }
  
  private void _initIBotServiceSoapProxy() {
    try {
      iBotServiceSoap = (new com.siebel.analytics.web.soap.v5.IBotServiceLocator()).getIBotServiceSoap();
      if (iBotServiceSoap != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)iBotServiceSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)iBotServiceSoap)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (iBotServiceSoap != null)
      ((javax.xml.rpc.Stub)iBotServiceSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.siebel.analytics.web.soap.v5.IBotServiceSoap getIBotServiceSoap() {
    if (iBotServiceSoap == null)
      _initIBotServiceSoapProxy();
    return iBotServiceSoap;
  }
  
  public void executeIBotNow(java.lang.String path, java.lang.String sessionID) throws java.rmi.RemoteException{
    if (iBotServiceSoap == null)
      _initIBotServiceSoapProxy();
    iBotServiceSoap.executeIBotNow(path, sessionID);
  }
  
  
}