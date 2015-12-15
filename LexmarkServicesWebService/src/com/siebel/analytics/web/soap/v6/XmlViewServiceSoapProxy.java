package com.siebel.analytics.web.soap.v6;

public class XmlViewServiceSoapProxy implements com.siebel.analytics.web.soap.v6.XmlViewServiceSoap {
  private String _endpoint = null;
  private com.siebel.analytics.web.soap.v6.XmlViewServiceSoap xmlViewServiceSoap = null;
  
  public XmlViewServiceSoapProxy() {
    _initXmlViewServiceSoapProxy();
  }
  
  public XmlViewServiceSoapProxy(String endpoint) {
    _endpoint = endpoint;
    _initXmlViewServiceSoapProxy();
  }
  
  private void _initXmlViewServiceSoapProxy() {
    try {
      xmlViewServiceSoap = (new com.siebel.analytics.web.soap.v6.XmlViewServiceLocator()).getXmlViewServiceSoap();
      if (xmlViewServiceSoap != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)xmlViewServiceSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)xmlViewServiceSoap)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (xmlViewServiceSoap != null)
      ((javax.xml.rpc.Stub)xmlViewServiceSoap)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.siebel.analytics.web.soap.v6.XmlViewServiceSoap getXmlViewServiceSoap() {
    if (xmlViewServiceSoap == null)
      _initXmlViewServiceSoapProxy();
    return xmlViewServiceSoap;
  }
  
  public com.siebel.analytics.web.soap.v6.QueryResults executeXMLQuery(com.siebel.analytics.web.soap.v6.ReportRef report, com.siebel.analytics.web.soap.v6.XMLQueryOutputFormat outputFormat, com.siebel.analytics.web.soap.v6.XMLQueryExecutionOptions executionOptions, com.siebel.analytics.web.soap.v6.ReportParams reportParams, java.lang.String sessionID) throws java.rmi.RemoteException{
    if (xmlViewServiceSoap == null)
      _initXmlViewServiceSoapProxy();
    return xmlViewServiceSoap.executeXMLQuery(report, outputFormat, executionOptions, reportParams, sessionID);
  }
  
  public java.lang.String upgradeXML(java.lang.String xml, java.lang.String sessionID) throws java.rmi.RemoteException{
    if (xmlViewServiceSoap == null)
      _initXmlViewServiceSoapProxy();
    return xmlViewServiceSoap.upgradeXML(xml, sessionID);
  }
  
  public com.siebel.analytics.web.soap.v6.QueryResults executeSQLQuery(java.lang.String sql, com.siebel.analytics.web.soap.v6.XMLQueryOutputFormat outputFormat, com.siebel.analytics.web.soap.v6.XMLQueryExecutionOptions executionOptions, java.lang.String sessionID) throws java.rmi.RemoteException{
    if (xmlViewServiceSoap == null)
      _initXmlViewServiceSoapProxy();
    return xmlViewServiceSoap.executeSQLQuery(sql, outputFormat, executionOptions, sessionID);
  }
  
  public com.siebel.analytics.web.soap.v6.QueryResults fetchNext(java.lang.String queryID, java.lang.String sessionID) throws java.rmi.RemoteException{
    if (xmlViewServiceSoap == null)
      _initXmlViewServiceSoapProxy();
    return xmlViewServiceSoap.fetchNext(queryID, sessionID);
  }
  
  public void cancelQuery(java.lang.String queryID, java.lang.String sessionID) throws java.rmi.RemoteException{
    if (xmlViewServiceSoap == null)
      _initXmlViewServiceSoapProxy();
    xmlViewServiceSoap.cancelQuery(queryID, sessionID);
  }
  
  public java.lang.String[] getPromptedFilters(com.siebel.analytics.web.soap.v6.ReportRef report, java.lang.String sessionID) throws java.rmi.RemoteException{
    if (xmlViewServiceSoap == null)
      _initXmlViewServiceSoapProxy();
    return xmlViewServiceSoap.getPromptedFilters(report, sessionID);
  }
  
  
}