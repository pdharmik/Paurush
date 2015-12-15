package com.lexmark.SiebelShared.source.updateServiceOrders.client;

public class SalesOrderWS_PortTypeProxy implements com.lexmark.SiebelShared.source.updateServiceOrders.client.SalesOrderWS_PortType {
  private String _endpoint = null;
  private com.lexmark.SiebelShared.source.updateServiceOrders.client.SalesOrderWS_PortType salesOrderWS_PortType = null;
  
  public SalesOrderWS_PortTypeProxy() {
    _initSalesOrderWS_PortTypeProxy();
  }
  
  public SalesOrderWS_PortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initSalesOrderWS_PortTypeProxy();
  }
  
  private void _initSalesOrderWS_PortTypeProxy() {
    try {
      salesOrderWS_PortType = (new com.lexmark.SiebelShared.source.updateServiceOrders.client.SalesOrderWSLocator()).getSalesOrder_salesOrderWS_Port();
      if (salesOrderWS_PortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)salesOrderWS_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)salesOrderWS_PortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (salesOrderWS_PortType != null)
      ((javax.xml.rpc.Stub)salesOrderWS_PortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.lexmark.SiebelShared.source.updateServiceOrders.client.SalesOrderWS_PortType getSalesOrderWS_PortType() {
    if (salesOrderWS_PortType == null)
      _initSalesOrderWS_PortTypeProxy();
    return salesOrderWS_PortType;
  }
  
  public void processOrderStatus(java.lang.String debug, com.lexmark.SiebelShared.source.updateServiceOrders.client.SalesOrderStatusWSInput salesOrderStatusWSInput) throws java.rmi.RemoteException{
    if (salesOrderWS_PortType == null)
      _initSalesOrderWS_PortTypeProxy();
    salesOrderWS_PortType.processOrderStatus(debug, salesOrderStatusWSInput);
  }
  
  
}