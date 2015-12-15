package com.businessobjects.www;

public class Connection_OperationsProxy implements com.businessobjects.www.Connection_Operations {
  private String _endpoint = null;
  private com.businessobjects.www.Connection_Operations connection_Operations = null;
  
  public Connection_OperationsProxy() {
    _initConnection_OperationsProxy();
  }
  
  public Connection_OperationsProxy(String endpoint) {
    _endpoint = endpoint;
    _initConnection_OperationsProxy();
  }
  
  private void _initConnection_OperationsProxy() {
    try {
      connection_Operations = (new com.businessobjects.www.DataServices_ServerLocator()).getConnection_Operations();
      if (connection_Operations != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)connection_Operations)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)connection_Operations)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (connection_Operations != null)
      ((javax.xml.rpc.Stub)connection_Operations)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.businessobjects.www.Connection_Operations getConnection_Operations() {
    if (connection_Operations == null)
      _initConnection_OperationsProxy();
    return connection_Operations;
  }
  
  public com.businessobjects.www.DataServices.ServerX_xsd.ResponseStatus logout(com.businessobjects.www.DataServices.ServerX_xsd.Logout_Input logout_Input) throws java.rmi.RemoteException{
    if (connection_Operations == null)
      _initConnection_OperationsProxy();
    return connection_Operations.logout(logout_Input);
  }
  
  public com.businessobjects.www.DataServices.ServerX_xsd.PingVersion ping(com.businessobjects.www.DataServices.ServerX_xsd.Ping_Input ping_Input) throws java.rmi.RemoteException{
    if (connection_Operations == null)
      _initConnection_OperationsProxy();
    return connection_Operations.ping(ping_Input);
  }
  
  public com.businessobjects.www.DataServices.ServerX_xsd.Session logon(com.businessobjects.www.DataServices.ServerX_xsd.LogonRequest logonRequest) throws java.rmi.RemoteException{
    if (connection_Operations == null)
      _initConnection_OperationsProxy();
    return connection_Operations.logon(logonRequest);
  }
  
  
}